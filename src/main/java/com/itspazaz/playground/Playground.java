package com.itspazaz.playground;

import com.jagex.runescape.formats.*;
import com.jagex.runetek3.GameShell;
import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.formats.Model;
import com.jagex.runetek3.formats.SeqBase;
import com.jagex.runetek3.formats.SeqFrame;
import com.jagex.runetek3.formats.SeqType;
import com.jagex.runetek3.graphics.*;
import com.jagex.runetek3.sound.SoundTrack;
import com.jagex.runetek3.util.BZip2InputStream;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Signlink;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.net.InetAddress;

public class Playground extends GameShell {
    private IndexedFont p11, p12, b12, q8;

    private Sprite sprite;
    private ObjType obj;
    private Model model;
    private int pitch;
    private int yaw;
    private int roll;

    private boolean textInputEnabled = false;
    private String textInput = "";
    private Camera camera = new Camera();
    private int frame;
    private String status = "";
    private String loadType = "Obj";

    private static int COLOR_BACKGROUND = 0x7F555555;
    private static int COLOR_TEXT = 0xFFFF00;

    public static void main(String[] args) {
        try {
            Draw3D.lowMemory = false;

            Signlink.startpriv(InetAddress.getLocalHost());

            Playground program = new Playground();
            program.initFrame(768, 1024, "Playground");
        } catch (Exception ex) {}
    }

    @Override
    public void load() {
        setLoopRate(165);
        drawArea.bind();
        Draw3D.prepareOffsets();

        // play some background music
        playSong("autumn voyage");

        // load some cache files
        loadTitle();
        loadMedia();
        loadTextures();
        loadModels();
        loadConfig(); // has to come after models
        loadWordEnc();
        loadSounds();

        showProgress("Finished loading", 90);
    }

    @Override
    public void unload() {
        Signlink.midifade = 0;
        Signlink.midi = "stop";
        LocType.unload();
        NpcType.unload();
        ObjType.unload();
        FloType.instances = null;
        IdkType.instances = null;
        InterfaceComponent.instances = null;
        SeqType.animations = null;
        SpotAnimType.instances = null;
        SpotAnimType.models = null;
        VarpType.instances = null;
        drawArea = null;
        Draw3D.unload();
        Model.unload();
        SeqBase.instance = null;
        SeqFrame.instance = null;
        System.gc();
    }

    @Override
    public void update() {
        pollKeyDown();
        pollKeyPressed();
    }

    private void pollKeyDown() {
        if (!textInputEnabled) {
            // Rotate X axis
            if (keyDown[GameShell.KEY_UP] == 1) {
                pitch--; // flipped to be more natural feeling
            } else if (keyDown[GameShell.KEY_DOWN] == 1) {
                pitch++;
            }

            // Rotate on Y axis
            if (keyDown[GameShell.KEY_LEFT] == 1) {
                yaw++;
            } else if (keyDown[GameShell.KEY_RIGHT] == 1) {
                yaw--;
            }

            // Rotate on Z axis
            if (keyDown['.'] == 1) {
                roll++;
            } else if (keyDown['/'] == 1) {
                roll--;
            }

            // Zoom in/out
            if (keyDown['e'] == 1) {
                camera.z++;
            } else if (keyDown['q'] == 1) {
                camera.z--;
            }

            // Move up/down
            if (keyDown['w'] == 1) {
                camera.y++;
            } else if (keyDown['s'] == 1) {
                camera.y--;
            }

            // Move left/right
            if (keyDown['d'] == 1) {
                camera.x++;
            } else if (keyDown['a'] == 1) {
                camera.x--;
            }

            // Rotate camera (not model)
            if (keyDown['c'] == 1) {
                camera.pitch++;
            } else if (keyDown['v'] == 1) {
                camera.pitch--;
            }

            // Sanity checks
            if (pitch > 2047) {
                pitch = 0;
            } else if (pitch < 0) {
                pitch = 2047;
            }

            if (yaw > 2047) {
                yaw = 0;
            } else if (yaw < 0) {
                yaw = 2047;
            }

            if (roll > 2047) {
                roll = 0;
            } else if (roll < 0) {
                roll = 2047;
            }
        }
    }

    private void pollKeyPressed() {
        while (true) {
            int key = pollKey();
            if (key == -1) {
                break;
            }

            // Export to PNG
            if (key == GameShell.KEY_ENTER) {
                if (textInputEnabled) {
                    try {
                        if (loadType == "Obj") {
                            int id = Integer.parseInt(textInput);
                            sprite = ObjType.getSprite(id, 10000);
                            obj = ObjType.get(id);
                            if (obj.certificateId != -1) {
                                obj = ObjType.get(obj.linkedId);
                            }
                            pitch = 0;
                            yaw = obj.iconYaw;
                            roll = obj.iconRoll;
                            camera.pitch = obj.iconCameraPitch;
                            camera.x = 0;
                            camera.y = 0;
                            camera.z = 0;
                            model = obj.getModel(1);
                            status = "Loaded object " + id;
                        }
                    } catch (Exception ex) {
                        status = "Failed to load: " + textInput;
                    }
                }

                textInputEnabled = !textInputEnabled;
                textInput = "";
            }

            if (textInputEnabled) {
                if (key >= ' ' && key < 'z') {
                    textInput += (char)key;
                } else if (key == GameShell.KEY_DELETE && textInput.length() > 0) {
                    textInput = textInput.substring(0, textInput.length() - 1);
                }
            } else {
                if (key == '\\') {
                    exportImage(drawArea.pixels, "dump/test" + frame);
                    status = "Exported image as dump/test" + frame + ".png";
                    frame++;
                } else if (key == 'j') {
                    Draw3D.jagged = !Draw3D.jagged;
                    status = "Jagged: " + Draw3D.jagged;
                }
            }
        }
    }

    @Override
    public void draw() {
        // Clear the old buffer
        Draw2D.clear();

        // Draw the background
        Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);

        // Draw a model
        try {
            if (model != null) {
                int sinPitch = Draw3D.sin[obj.iconCameraPitch] * obj.iconZoom >> 16;
                int cosPitch = Draw3D.cos[obj.iconCameraPitch] * obj.iconZoom >> 16;
                model.draw(pitch, yaw, roll, camera.pitch, obj.iconX + camera.x, sinPitch + model.maxBoundY / 2 + obj.iconY + camera.z, cosPitch + obj.iconY + camera.y);

                sprite.draw(0, gameWidth - 33);
                p12.drawRightAligned(sprite.height + p12.height, COLOR_TEXT, obj.name, gameWidth, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            status = "Failed to draw model";
        }

        // Debug text
        int y = p12.height;
        p12.draw(0, y, COLOR_TEXT, "FPS: " + fps, true);
        y += p12.height + 2;

        p12.draw(0, y, COLOR_TEXT, status, true);
        y += p12.height + 2;

        if (textInputEnabled) {
            p12.draw(0, y, COLOR_TEXT, loadType + "> " + textInput, true);
        }

        y = Draw2D.height - p12.height / 2;
        p12.draw(0, y, COLOR_TEXT, "Help: Press Enter to enter a (" + loadType + ") ID", true);

        // Render the frame
        drawArea.drawImage(0, graphics, 0);
    }

    @Override
    public void refresh() {
    }

    private void playSong(String name) {
        try {
            byte[] src = Signlink.cacheload(name + ".mid");
            int length = (new Buffer(src)).readDWord();
            byte[] decompressed = new byte[length];
            BZip2InputStream.read(decompressed, length, src, 0, 4);
            Signlink.midifade = 0;
            Signlink.midisave(decompressed, length);
        } catch (Exception ex) {}
    }

    private void loadTitle() {
        showProgress("Loading title", 10);

        FileArchive title = new FileArchive(Signlink.cacheload("title"));
        p11 = new IndexedFont(title, "p11");
        p12 = new IndexedFont(title, "p12");
        b12 = new IndexedFont(title, "b12");
        q8 = new IndexedFont(title, "q8");
    }

    private void loadModels() {
        showProgress("Loading title", 50);

        FileArchive models = new FileArchive(Signlink.cacheload("models"));
        Model.load(models);
        SeqBase.load(models);
        SeqFrame.load(models);
    }

    private void loadTextures() {
        showProgress("Loading title", 40);

        FileArchive textures = new FileArchive(Signlink.cacheload("textures"));
        Draw3D.unpackTextures(textures);
        Draw3D.setBrightness(0.80000000000000004D);
        Draw3D.setupPools(20);
    }

    private void loadConfig() {
        showProgress("Loading title", 60);

        FileArchive config = new FileArchive(Signlink.cacheload("config"));
        SeqType.load(config);
        LocType.load(config);
        FloType.load(config);
        ObjType.load(config);
        NpcType.load(config);
        IdkType.load(config);
        SpotAnimType.load(config);
        VarpType.load(config);
    }

    private void loadMedia() {
        showProgress("Loading media", 20);

        FileArchive media = new FileArchive(Signlink.cacheload("media"));

        loadInterface(media);
    }

    private void loadInterface(FileArchive media) {
        showProgress("Loading interface", 30);

        FileArchive archive = new FileArchive(Signlink.cacheload("interface"));
        IndexedFont[] fonts = { p11, p12, b12, q8 };
        InterfaceComponent.load(media, fonts, archive);
    }

    private void loadWordEnc() {
        showProgress("Loading title", 70);

        FileArchive wordenc = new FileArchive(Signlink.cacheload("wordenc"));
        WordEncoding.load(wordenc);
    }

    private void loadSounds() {
        showProgress("Loading title", 80);

        FileArchive sounds = new FileArchive(Signlink.cacheload("sounds"));
        byte[] src = sounds.read("sounds.dat", null);
        Buffer buffer = new Buffer(src);
        SoundTrack.load(buffer);
    }

    private void exportImage(int[] pixels, String filename) {
        byte[] raw = new byte[pixels.length * 4];
        int offset = 0;
        for (int n = 0; n < pixels.length; n++) {
            int rgb = pixels[n];

            raw[offset++] = (byte) (rgb >> 16); // red
            raw[offset++] = (byte) (rgb >> 8); // green
            raw[offset++] = (byte) (rgb); // blue

            // set transparency for background color
            if (rgb >> 24 == 0x7F) {
                raw[offset++] = (byte) 0;
            } else {
                raw[offset++] = (byte) 0xFF;
            }
        }

        try {
            DataBuffer buffer = new DataBufferByte(raw, raw.length);
            int samplesPerPixel = 4;
            int[] bandOffsets = { 0, 1, 2, 3 };
            ColorModel colorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), true, false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
            WritableRaster raster = Raster.createInterleavedRaster(buffer, gameWidth, gameHeight, samplesPerPixel * gameWidth, samplesPerPixel, bandOffsets, null);
            BufferedImage image = new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
            ImageIO.write(image, "PNG", new File(filename + ".png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
