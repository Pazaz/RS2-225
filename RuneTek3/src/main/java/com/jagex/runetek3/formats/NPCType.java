package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.graphics.Model;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Cache;

public class NPCType {

    public static int count;
    public static int[] offsets;
    public static Buffer data;
    public static NPCType[] cache;
    public static int cacheIndex;
    public static Cache models = new Cache(30);
    public boolean aBoolean76;
    public long index;
    public String name;
    public String description;
    public byte size;
    public int[] modelIndices;
    public int[] headModelIndices;
    public int standSeq;
    public int walkSeq;
    public int turnAroundSeq;
    public int turnRightSeq;
    public int turnLeftSeq;
    public boolean disposeAlpha;
    public int[] oldColors;
    public int[] newColors;
    public String[] options;
    public int anInt97;
    public int anInt98;
    public int anInt99;
    public boolean showOnMinimap;
    public int level;
    public int scaleX;
    public int scaleY;
    public NPCType() {
        aBoolean76 = false;
        index = -1L;
        size = 1;
        standSeq = -1;
        walkSeq = -1;
        turnAroundSeq = -1;
        turnRightSeq = -1;
        turnLeftSeq = -1;
        disposeAlpha = false;
        anInt97 = -1;
        anInt98 = -1;
        anInt99 = -1;
        showOnMinimap = true;
        level = -1;
        scaleX = 128;
        scaleY = 128;
    }

    public static void load(FileArchive fileArchive) {
        data = new Buffer(fileArchive.read("npc.dat", null));
        Buffer idx = new Buffer(fileArchive.read("npc.idx", null));
        count = idx.g2();
        offsets = new int[count];

        int off = 2;
        for (int n = 0; n < count; n++) {
            offsets[n] = off;
            off += idx.g2();
        }

        cache = new NPCType[20];
        for (int n = 0; n < 20; n++) {
            cache[n] = new NPCType();
        }
    }

    public static void unload() {
        models = new Cache(30);
        offsets = null;
        cache = null;
        data = null;
    }

    public static NPCType get(int index) {
        for (int n = 0; n < 20; n++) {
            if (cache[n].index == (long) index) {
                return cache[n];
            }
        }

        try {
            cacheIndex = (cacheIndex + 1) % 20;
            NPCType npcType = cache[cacheIndex] = new NPCType();
            data.offset = offsets[index];
            npcType.index = index;
            npcType.read(data);
            return npcType;
        } catch (Exception ex) {
            return null;
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.g1();

            if (opcode == 0) {
                return;
            } else if (opcode == 1) {
                int count = buffer.g1();
                modelIndices = new int[count];
                for (int n = 0; n < count; n++) {
                    modelIndices[n] = buffer.g2();
                }
            } else if (opcode == 2) {
                name = buffer.gString();
            } else if (opcode == 3) {
                description = buffer.gString();
            } else if (opcode == 12) {
                size = buffer.g1s();
            } else if (opcode == 13) {
                standSeq = buffer.g2();
            } else if (opcode == 14) {
                walkSeq = buffer.g2();
            } else if (opcode == 16) {
                disposeAlpha = true;
            } else if (opcode == 17) {
                walkSeq = buffer.g2();
                turnAroundSeq = buffer.g2();
                turnRightSeq = buffer.g2();
                turnLeftSeq = buffer.g2();
            } else if (opcode >= 30 && opcode < 40) {
                if (options == null) {
                    options = new String[5];
                }

                options[opcode - 30] = buffer.gString();
                if (options[opcode - 30].equalsIgnoreCase("hidden")) {
                    options[opcode - 30] = null;
                }
            } else if (opcode == 40) {
                int count = buffer.g1();
                oldColors = new int[count];
                newColors = new int[count];
                for (int n = 0; n < count; n++) {
                    oldColors[n] = buffer.g2();
                    newColors[n] = buffer.g2();
                }
            } else if (opcode == 60) {
                int count = buffer.g1();
                headModelIndices = new int[count];
                for (int n = 0; n < count; n++) {
                    headModelIndices[n] = buffer.g2();
                }
            } else if (opcode == 90) {
                buffer.g2();
            } else if (opcode == 91) {
                buffer.g2();
            } else if (opcode == 92) {
                buffer.g2();
            } else if (opcode == 93) {
                showOnMinimap = false;
            } else if (opcode == 95) {
                level = buffer.g2();
            } else if (opcode == 97) {
                scaleX = buffer.g2();
            } else if (opcode == 98) {
                scaleY = buffer.g2();
            }
        } while (true);
    }

    public Model getModel(int primaryFrame, int secondaryFrame, int[] labelGroups) {
        Model model = (Model) models.get(index);
        if (model == null) {
            Model[] models = new Model[modelIndices.length];
            for (int n = 0; n < modelIndices.length; n++) {
                models[n] = new Model(modelIndices[n]);
            }

            if (models.length == 1) {
                model = models[0];
            } else {
                model = new Model(models, models.length);
            }

            if (oldColors != null) {
                for (int n = 0; n < oldColors.length; n++) {
                    model.recolor(oldColors[n], newColors[n]);
                }
            }

            model.applyGroups();
            model.applyLighting(64, 850, -30, -50, -30, true);
            NPCType.models.put(index, model);
        }

        model = new Model(model, !disposeAlpha);

        if (primaryFrame != -1 && secondaryFrame != -1) {
            model.applyFrames(secondaryFrame, primaryFrame, labelGroups);
        } else if (primaryFrame != -1) {
            model.applyFrame(primaryFrame);
        }

        if (scaleX != 128 || scaleY != 128) {
            model.scale(scaleX, scaleY, scaleX);
        }

        model.calculateYBoundaries();
        model.skinTriangle = null;
        model.labelVertices = null;

        if (size == 1) {
            model.pickable = true;
        }

        return model;
    }

    public Model getHeadModel() {
        if (headModelIndices == null) {
            return null;
        }

        Model[] models = new Model[headModelIndices.length];
        for (int n = 0; n < headModelIndices.length; n++) {
            models[n] = new Model(headModelIndices[n]);
        }

        Model model;
        if (models.length == 1) {
            model = models[0];
        } else {
            model = new Model(models, models.length);
        }

        if (oldColors != null) {
            for (int n = 0; n < oldColors.length; n++) {
                model.recolor(oldColors[n], newColors[n]);
            }
        }

        return model;
    }

}