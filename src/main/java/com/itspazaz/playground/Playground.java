package com.itspazaz.playground;

import com.jagex.runescape.formats.*;
import com.jagex.runetek3.GameShell;
import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.formats.Model;
import com.jagex.runetek3.formats.SeqBase;
import com.jagex.runetek3.formats.SeqFrame;
import com.jagex.runetek3.formats.SeqType;
import com.jagex.runetek3.graphics.*;
import com.jagex.runetek3.scene.NpcEntity;
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
    private NpcEntity npc = new NpcEntity();
    private Model model;
    private int pitch;
    private int yaw;
    private int roll;
    private static int modifier = 1;

    private boolean textInputEnabled = false;
    private String textInput = "";
    private Camera camera = new Camera();
    private int frame;
    private String status = "";
    private String loadType = "Obj";
    private boolean drawText = true;

    private static int COLOR_BACKGROUND = 0x7F555555;
    private static int COLOR_TEXT = 0xFFFF00;

    private static String[] SONGS = {
        "adventure_mi",  "alone_mid",    "al_kharid_mi", "arabian2_mid",
        "arabian3_mid",  "arabian_mid",  "arabique_mid", "army_of_dark",
        "arrival_mid",   "attack2_mid",  "attention_mi", "autumn_voyag",
        "beyond_mid",    "book_of_spel", "camelot_mid",  "cave_backgro",
        "crystal_cave",  "crystal_swor", "dangerous_mi", "dark2_mid",
        "deep_wildy_m",  "desert_voyag", "doorways_mid", "dream1_mid",
        "duel_arena_m",  "egypt_mid",    "emperor_mid",  "expanse_mid",
        "fanfare3_mid",  "fanfare_mid",  "fishing_mid",  "flute_salad_",
        "forever_mid",   "gaol_mid",     "garden_mid",   "gnome_mid",
        "gnome_theme_",  "gnome_villag", "greatness_mi", "harmony_mid",
        "heart_and_mi",  "horizon_mid",  "ice_melody_m", "inspiration_",
        "jungle_islan",  "knightly_mid", "lasting_mid",  "legion_mid",
        "lightness_mi",  "lightwalk_mi", "long_ago_mid", "march2_mid",
        "medieval_mid",  "mellow_mid",   "miles_away_m", "miracle_danc",
        "neverland_mi",  "newbie_melod", "nightfall_mi", "overture_mid",
        "parade_mid",    "quest_mid",    "reggae_mid",   "rune_essence",
        "sad_meadow_m",  "scape_cave_m", "scape_main_m", "scape_sad1_m",
        "scape_soft_m",  "scape_wild1_", "sea_shanty2_", "sea_shanty_m",
        "serenade_mid",  "serene_mid",   "shine_mid",    "spirit_mid",
        "splendour_mid", "spooky2_mid",  "start_mid",    "still_night_",
        "talking_fore",  "the_shadow_m", "the_tower_mi", "tomorrow_mid",
        "tribal2_mid",   "troubled_mid", "unknown_land", "vision_mid",
        "wander_mid",    "waterfall_mi", "wilderness2_", "wilderness4_",
        "wonderous_mi",  "wonder_mid",   "workshop_mid", "yesteryear_m"
    };

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
        drawArea.bind();
        Draw3D.prepareOffsets();

        // play some background music
        playSong(SONGS[(int)Math.floor(Math.random() * SONGS.length)]);

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
            // Change modifier
            if (keyDown['['] == 1) {
                modifier--;
            } else if (keyDown[']'] == 1) {
                modifier++;
            }

            // Rotate X axis
            if (keyDown[GameShell.KEY_UP] == 1) {
                pitch -= modifier; // flipped to be more natural feeling
            } else if (keyDown[GameShell.KEY_DOWN] == 1) {
                pitch += modifier;
            }

            // Rotate on Y axis
            if (keyDown[GameShell.KEY_LEFT] == 1) {
                yaw += modifier;
            } else if (keyDown[GameShell.KEY_RIGHT] == 1) {
                yaw -= modifier;
            }

            // Rotate on Z axis
            if (keyDown['.'] == 1) {
                roll += modifier;
            } else if (keyDown['/'] == 1) {
                roll -= modifier;
            }

            // Zoom in/out
            if (keyDown['e'] == 1) {
                camera.z += modifier;
            } else if (keyDown['q'] == 1) {
                camera.z -= modifier;
            }

            // Move up/down
            if (keyDown['w'] == 1) {
                camera.y += modifier;
            } else if (keyDown['s'] == 1) {
                camera.y -= modifier;
            }

            // Move left/right
            if (keyDown['d'] == 1) {
                camera.x += modifier;
            } else if (keyDown['a'] == 1) {
                camera.x -= modifier;
            }

            // Rotate camera (not model)
            if (keyDown['c'] == 1) {
                camera.pitch += modifier;
            } else if (keyDown['v'] == 1) {
                camera.pitch -= modifier;
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

            if (key == GameShell.KEY_ENTER) {
                if (textInputEnabled) {
                    try {
                        if (loadType.equals("Obj")) {
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
                        } else if (loadType.equals("Npc")) {
                            if (textInput.startsWith("a")) {
                                int id = Integer.parseInt(textInput.substring(1));
                                npc.primarySeq = id;
                                npc.primarySeqFrame = 0;
                                npc.primarySeqDelay = 0;
                                status = "Applied NPC animation " + id;
                            } else {
                                int id = Integer.parseInt(textInput);
                                npc.info = NpcType.get(id);
                                npc.primarySeq = 0;
                                pitch = 0;
                                yaw = 169;
                                roll = 0;
                                camera.pitch = 128;
                                camera.x = 5;
                                camera.z = 180;
                                camera.y = 195;
                                model = npc.getModel();
                                status = "Loaded NPC " + id;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
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
                } else if (key == '1') {
                    loadType = "Obj";
                    sprite = null;
                    obj = null;
                    npc.info = null;
                    model = null;
                    status = "Switched to Obj";
                } else if (key == '2') {
                    loadType = "Npc";
                    sprite = null;
                    obj = null;
                    npc.info = null;
                    model = null;
                    status = "Switched to Npc";
                } else if (key == 'h') {
                    drawText = !drawText;
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

        boolean newFrame = false;

        // Draw a model
        try {
            if (obj != null || npc.info != null) {
                if (loadType.equals("Obj")) {
                    int sinPitch = Draw3D.sin[obj.iconCameraPitch] * obj.iconZoom >> 16;
                    int cosPitch = Draw3D.cos[obj.iconCameraPitch] * obj.iconZoom >> 16;
                    model.draw(pitch, yaw, roll, camera.pitch, obj.iconX + camera.x, sinPitch + model.maxBoundY / 2 + obj.iconY + camera.z, cosPitch + obj.iconY + camera.y);

                    if (drawText) {
                        // Draw sprite
                        sprite.draw(0, gameWidth - 33);
                        p12.drawRightAligned(sprite.height + p12.height, COLOR_TEXT, obj.name, gameWidth, true);

                        // Draw model/camera data
                        p12.drawRightAligned(Draw2D.height - 6, COLOR_TEXT, pitch + "," + yaw + "," + roll + "," + camera.pitch + "," + (obj.iconX + camera.x) + "," + (sinPitch + model.maxBoundY / 2 + obj.iconY + camera.z) + "," + (cosPitch + obj.iconY + camera.y), gameWidth - 4, true);
                    }
                } else if (loadType.equals("Npc")) {
                    if (npc.primarySeq != 0 && npc.primarySeqDelay == 0) {
                        SeqType seq = SeqType.animations[npc.primarySeq];
                        npc.primarySeqFrame++;
                        if (npc.primarySeqFrame > seq.primaryFrames.length - 1) {
                            npc.primarySeqFrame = 0;
                        }
                        model = npc.getModel();
                        npc.primarySeqDelay = 4;
                        newFrame = true;
                    } else if (npc.primarySeqDelay > 0) {
                        npc.primarySeqDelay--;
                    }

                    model.draw(pitch, yaw, roll, camera.pitch, camera.x, camera.z, camera.y);

                    if (drawText) {
                        // Draw model name
                        p12.drawRightAligned(p12.height + 3, COLOR_TEXT, npc.info.name, gameWidth - 3, true);

                        // Draw model/camera data
                        p12.drawRightAligned(Draw2D.height - 6, COLOR_TEXT, pitch + "," + yaw + "," + roll + "," + camera.pitch + "," + camera.x + "," + camera.z + "," + camera.y, gameWidth - 4, true);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            status = "Failed to draw model";
        }

        // Debug text
        if (drawText) {
            int y = p12.height;
            p12.draw(3, y, COLOR_TEXT, "FPS: " + fps, true);
            y += p12.height + 2;

            p12.draw(3, y, COLOR_TEXT, status, true);
            y += p12.height + 2;

            if (textInputEnabled) {
                p12.draw(3, y, COLOR_TEXT, loadType + "> " + textInput, true);
            }

            y = Draw2D.height - p12.height / 2;
            p12.draw(3, y, COLOR_TEXT, "Help: Press Enter to enter a (" + loadType + ") ID", true);
        }

        if (newFrame) {
            exportImage(drawArea.pixels, "dump/test" + frame);
            frame++;
        }

        // Render the frame
        drawArea.drawImage(0, graphics, 0);
    }

    @Override
    public void refresh() {
    }

    private void playSong(String name) {
        System.out.println("Playing song " + name);
        try {
            byte[] src = Signlink.cacheload(name);
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
