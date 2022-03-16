package com.itspazaz.playground;

import com.jagex.runetek3.Applet;
import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.formats.*;
import com.jagex.runetek3.graphics.*;
import com.jagex.runetek3.graphics.Component;
import com.jagex.runetek3.graphics.Font;
import com.jagex.runetek3.scene.NPCEntity;
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

public class Playground extends Applet {
    private Font p11, p12, b12, q8;

    private Component component;

    private Sprite sprite;
    private ObjType obj;

    private NPCEntity npc = new NPCEntity();
    private boolean npcHead = false;

    private LocType loc;
    private int locFrame = 0;
    private int locDelay = 0;
    private int animCycle = 0;

    private Model model;
    private int pitch;
    private int yaw;
    private int roll;
    private static int modifier = 1;

    private boolean textInputEnabled = false;
    private String textInput = "";
    private Camera camera = new Camera();
    private int exported;
    private String status = "";
    private String loadType = "Obj";
    private int loadId = 0;
    private int loadSeq = 0;
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
            program.initFrame(720, 1280, "Playground");
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
        Signlink.midifade = false;
        Signlink.midi = "stop";
        LocType.unload();
        NPCType.unload();
        ObjType.unload();
        FloorType.instances = null;
        IDKType.instances = null;
        Component.instances = null;
        SeqType.instances = null;
        SpotAnimType.instances = null;
        SpotAnimType.models = null;
        VarType.instances = null;
        drawArea = null;
        Draw3D.unload();
        Model.unload();
        SeqBase.instances = null;
        SeqFrame.instances = null;
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
            if (keyDown[Applet.KEY_UP] == 1) {
                pitch -= modifier; // flipped to be more natural feeling
            } else if (keyDown[Applet.KEY_DOWN] == 1) {
                pitch += modifier;
            }

            // Rotate on Y axis
            if (keyDown[Applet.KEY_LEFT] == 1) {
                yaw += modifier;
            } else if (keyDown[Applet.KEY_RIGHT] == 1) {
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

            if (key == Applet.KEY_ENTER) {
                if (textInputEnabled) {
                    try {
                        if (loadType.equals("Obj")) {
                            loadId = Integer.parseInt(textInput);
                            sprite = ObjType.getSprite(loadId, 10000);
                            obj = ObjType.get(loadId);
                            if (obj.certificateId != -1) {
                                obj = ObjType.get(obj.linkedId);
                            }
                            loadSeq = 0;
                            pitch = 0;
                            yaw = obj.iconYaw;
                            roll = obj.iconRoll;
                            camera.pitch = obj.iconCameraPitch;
                            camera.x = 0;
                            camera.y = 0;
                            camera.z = 0;
                            model = obj.getModel(1);
                            status = "Loaded Obj " + loadId;
                        } else if (loadType.equals("Npc")) {
                            if (textInput.startsWith("a")) {
                                int id = Integer.parseInt(textInput.substring(1));
                                if (component != null && npcHead) {
                                    component.seqId = id;
                                }
                                npc.primarySeq = id;
                                npc.primarySeqFrame = 0;
                                npc.primarySeqDelay = 0;
                                loadSeq = id;
                                animCycle = 0;
                                status = "Applied NPC animation " + id;
                            } else if (textInput.startsWith("h")) {
                                npcHead = !npcHead;
                                if (npcHead) {
                                    component = Component.instances[4901];
                                    component.modelDisabled = npc.info.getHeadModel();
                                    model = component.getModel(-1, -1, false);
                                    npc.primarySeq = 0;
                                    npc.primarySeqFrame = 0;
                                    npc.primarySeqDelay = 0;
                                    loadSeq = 0;
                                    animCycle = 0;
                                    pitch = 1963;
                                    yaw = 1963;
                                    roll = 0;
                                    camera.pitch = 128;
                                    camera.x = 0;
                                    camera.z = 84;
                                    camera.y = 232;
                                } else {
                                    model = npc.getModel();
                                    npc.primarySeq = 0;
                                    loadSeq = 0;
                                    animCycle = 0;
                                    pitch = 0;
                                    yaw = 169;
                                    roll = 0;
                                    camera.pitch = 128;
                                    camera.x = 5;
                                    camera.z = 180;
                                    camera.y = 195;
                                }
                            } else if (textInput.equals("stand")) {
                                npc.primarySeq = npc.info.standSeq;
                                npc.primarySeqFrame = 0;
                                npc.primarySeqDelay = 0;
                                animCycle = 0;
                                loadSeq = npc.info.standSeq;
                            } else if (textInput.equals("walk")) {
                                npc.primarySeq = npc.info.walkSeq;
                                npc.primarySeqFrame = 0;
                                npc.primarySeqDelay = 0;
                                animCycle = 0;
                                loadSeq = npc.info.walkSeq;
                            } else {
                                loadId = Integer.parseInt(textInput);
                                npc.info = NPCType.get(loadId);
                                npc.primarySeq = npc.info.standSeq;
                                loadSeq = 0;
                                animCycle = 0;
                                pitch = 0;
                                yaw = 169;
                                roll = 0;
                                camera.pitch = 128;
                                camera.x = 5;
                                camera.z = 180;
                                camera.y = 195;
                                model = npc.getModel();
                                status = "Loaded NPC " + loadId;
                            }
                        } else if (loadType.equals("Loc")) {
                            if (textInput.startsWith("a")) {
                                int id = Integer.parseInt(textInput.substring(1));
                                loc.animationIndex = id;
                                locFrame = 0;
                                locDelay = 0;
                                loadSeq = id;
                                animCycle = 0;
                                status = "Applied Loc animation " + id;
                            } else {
                                loadId = Integer.parseInt(textInput);
                                loc = LocType.get(loadId);
                                loc.delayShading = false;
                                locFrame = 0;
                                locDelay = 0;
                                loadSeq = 0;
                                animCycle = 0;
                                pitch = 0;
                                yaw = 143;
                                roll = 0;
                                camera.pitch = 192;
                                camera.x = 0;
                                camera.z = 357;
                                camera.y = 427;
                                model = loc.getModel(loc.modelTypes[0], 0, -400, -400, -400, -400, -1);
                                status = "Loaded Loc " + loadId;
                            }
                        } else if (loadType.equals("Model")) {
                            model = new Model(Integer.parseInt(textInput));
                            model.applyLighting(64, 768, -50, -10, -50, true);
                            model.pickable = true;

                            pitch = 0;
                            yaw = 0;
                            roll = 0;
                            camera.pitch = 0;
                            camera.x = 0;
                            camera.z = 0;
                            camera.y = 420;
                            status = "Loaded Model " + model.id;
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
                } else if (key == Applet.KEY_DELETE && textInput.length() > 0) {
                    textInput = textInput.substring(0, textInput.length() - 1);
                }
            } else {
                if (key == '\\') {
                    String name = "dump/" + exported + "." + loadType + "-" + loadId;
                    if (loadSeq != 0) {
                        name += "." + loadSeq;
                    }
                    exportImage(drawArea.pixels, name);
                    status = "Exported image as " + name + ".png";
                    exported++;
                    animCycle = 0;
                } else if (key == 'j') {
                    Draw3D.jagged = !Draw3D.jagged;
                    status = "Jagged: " + Draw3D.jagged;
                } else if (key == 'h') {
                    drawText = !drawText;
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
                } else if (key == '3') {
                    loadType = "Loc";
                    sprite = null;
                    obj = null;
                    npc.info = null;
                    model = null;
                    loc = null;
                    status = "Switched to Loc";
                } else if (key == '4') {
                    loadType = "Model";
                    sprite = null;
                    obj = null;
                    npc.info = null;
                    model = null;
                    loc = null;
                    status = "Switched to Model";
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
            if (model != null) {
                if (loadType.equals("Obj")) {
                    int sinPitch = Draw3D.sin[obj.iconCameraPitch] * obj.iconZoom >> 16;
                    int cosPitch = Draw3D.cos[obj.iconCameraPitch] * obj.iconZoom >> 16;
                    model.draw(pitch, yaw, roll, camera.pitch, obj.iconX + camera.x, sinPitch + model.maxBoundY / 2 + obj.iconY + camera.z, cosPitch + obj.iconY + camera.y);

                    // Used to copy and paste
                    // System.out.println(pitch + "," + yaw + "," + roll + "," + camera.pitch + "," + (obj.iconX + camera.x) + "," +  (sinPitch + model.maxBoundY / 2 + obj.iconY + camera.z) + "," + (cosPitch + obj.iconY + camera.y));

                    if (drawText) {
                        // Draw sprite
                        sprite.draw(0, gameWidth - 33);
                        p12.drawRightAligned(sprite.height + p12.height, COLOR_TEXT, obj.name, gameWidth, true);

                        // Draw model/camera data
                        p12.drawRightAligned(Draw2D.height - 6, COLOR_TEXT, pitch + "," + yaw + "," + roll + "," + camera.pitch + "," + (obj.iconX + camera.x) + "," + (sinPitch + model.maxBoundY / 2 + obj.iconY + camera.z) + "," + (cosPitch + obj.iconY + camera.y), gameWidth - 4, true);
                    }
                } else if (loadType.equals("Npc")) {
                    if (npc.primarySeq != -1 && npc.primarySeqDelay == 0) {
                        SeqType seq = SeqType.instances[npc.primarySeq];
                        npc.primarySeqFrame++;
                        if (npc.primarySeqFrame > seq.primaryFrames.length - 1) {
                            animCycle++;
                            npc.primarySeqFrame = 0;
                        }
                        if (npcHead) {
                            model = component.getModel(SeqType.instances[npc.primarySeq].primaryFrames[npc.primarySeqFrame], -1, false);
                        } else {
                            model = npc.getModel();
                        }
                        npc.primarySeqDelay = 6;
                        if (animCycle == 0) {
                            newFrame = true;
                        }
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
                } else if (loadType.equals("Loc")) {
                    if (loc.animationIndex != -1 && locDelay == 0) {
                        SeqType seq = SeqType.instances[loc.animationIndex];
                        locFrame++;
                        if (locFrame > seq.primaryFrames.length - 1) {
                            animCycle++;
                            locFrame = 0;
                        }
                        model = loc.getModel(loc.modelTypes[0], 0, -400, -400, -400, -400, seq.primaryFrames[locFrame]);
                        locDelay = 4;
                        if (animCycle == 0) {
                            newFrame = true;
                        }
                    } else if (locDelay > 0) {
                        locDelay--;
                    }

                    model.draw(pitch, yaw, roll, camera.pitch, camera.x, camera.z, camera.y);

                    if (drawText) {
                        // Draw model name
                        p12.drawRightAligned(p12.height + 3, COLOR_TEXT, loc.name, gameWidth - 3, true);

                        // Draw model/camera data
                        p12.drawRightAligned(Draw2D.height - 6, COLOR_TEXT, pitch + "," + yaw + "," + roll + "," + camera.pitch + "," + camera.x + "," + camera.z + "," + camera.y, gameWidth - 4, true);
                    }
                } else if (loadType.equals("Model")) {
                    model.draw(pitch, yaw, roll, camera.pitch, camera.x, camera.z, camera.y);

                    if (drawText) {
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
            String name = "dump/" + exported + "." + loadType + "-" + loadId;
            if (loadSeq != 0) {
                name += "." + loadSeq;
            }
            exportImage(drawArea.pixels, name);
            exported++;
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
            byte[] src = Signlink.cacheload(name, false);
            int length = (new Buffer(src)).g4();
            byte[] decompressed = new byte[length];
            BZip2InputStream.read(decompressed, length, src, 0, 4);
            Signlink.midifade = false;
            Signlink.midisave(decompressed, length);
        } catch (Exception ex) {}
    }

    private void loadTitle() {
        showProgress("Loading title", 10);

        try {
            FileArchive title = new FileArchive(Signlink.cacheload("title", false));
            p11 = new Font(title, "p11");
            p12 = new Font(title, "p12");
            b12 = new Font(title, "b12");
            q8 = new Font(title, "q8");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void loadModels() {
        showProgress("Loading models", 50);

        FileArchive models = new FileArchive(Signlink.cacheload("models", false));
        Model.load(models);
        SeqBase.load(models);
        SeqFrame.load(models);
    }

    private void loadTextures() {
        showProgress("Loading textures", 40);

        FileArchive textures = new FileArchive(Signlink.cacheload("textures", false));
        Draw3D.unpackTextures(textures);
        Draw3D.setBrightness(0.80000000000000004D);
        Draw3D.setupPools(20);
    }

    private void loadConfig() {
        showProgress("Loading config", 60);

        FileArchive config = new FileArchive(Signlink.cacheload("config", false));
        SeqType.load(config);
        LocType.load(config);
        FloorType.load(config);
        ObjType.load(config);
        NPCType.load(config);
        IDKType.load(config);
        SpotAnimType.load(config);
        VarType.load(config);
    }

    private void loadMedia() {
        showProgress("Loading media", 20);

        FileArchive media = new FileArchive(Signlink.cacheload("media", false));

        loadInterface(media);
    }

    private void loadInterface(FileArchive media) {
        showProgress("Loading interface", 30);

        FileArchive archive = new FileArchive(Signlink.cacheload("interface", false));
        Font[] fonts = { p11, p12, b12, q8 };
        Component.load(media, fonts, archive);
    }

    private void loadWordEnc() {
        showProgress("Loading word encoding", 70);

        FileArchive wordenc = new FileArchive(Signlink.cacheload("wordenc", false));
        WordPack.load(wordenc);
    }

    private void loadSounds() {
        showProgress("Loading sounds", 80);

        FileArchive sounds = new FileArchive(Signlink.cacheload("sounds", false));
        byte[] src = sounds.read("sounds.dat");
        Buffer buffer = new Buffer(src);
        SoundTrack.load(buffer);
    }

    private void exportImage(int[] pixels, String filename) {
        byte[] raw = new byte[pixels.length * 4];
        int offset = 0;
        for (int rgb : pixels) {
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
