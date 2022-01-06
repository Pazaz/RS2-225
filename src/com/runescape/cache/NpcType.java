package com.runescape.cache;

import com.runescape.util.Buffer;
import com.runescape.util.Cache;

public class NpcType {

    public static void load(FileArchive fileArchive) {
        data = new Buffer(363, fileArchive.read("npc.dat", null));
        Buffer idx = new Buffer(363, fileArchive.read("npc.idx", null));
        count = idx.method448();
        offsets = new int[count];

        int off = 2;
        for (int n = 0; n < count; n++) {
            offsets[n] = off;
            off += idx.method448();
        }

        cache = new NpcType[20];
        for (int n = 0; n < 20; n++) {
            cache[n] = new NpcType();
        }
    }

    public static void unload() {
        models = null;
        offsets = null;
        cache = null;
        data = null;
    }

    public static NpcType get(int index) {
        for (int n = 0; n < 20; n++) {
            if (cache[n].index == (long) index) {
                return cache[n];
            }
        }

        cacheIndex = (cacheIndex + 1) % 20;
        NpcType npcType = cache[cacheIndex] = new NpcType();
        data.offset = offsets[index];
        npcType.index = index;
        npcType.read(data);
        return npcType;
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.method446();

            if (opcode == 0) {
                return;
            } else if (opcode == 1) {
                int count = buffer.method446();
                modelIndices = new int[count];
                for (int n = 0; n < count; n++) {
                    modelIndices[n] = buffer.method448();
                }
            } else if (opcode == 2) {
                name = buffer.method453();
            } else if (opcode == 3) {
                description = buffer.method454((byte) 31);
            } else if (opcode == 12) {
                size = buffer.method447();
            } else if (opcode == 13) {
                standSeq = buffer.method448();
            } else if (opcode == 14) {
                walkSeq = buffer.method448();
            } else if (opcode == 16) {
                disposeAlpha = true;
            } else if (opcode == 17) {
                walkSeq = buffer.method448();
                turnAroundSeq = buffer.method448();
                turnRightSeq = buffer.method448();
                turnLeftSeq = buffer.method448();
            } else if (opcode >= 30 && opcode < 40) {
                if (options == null) {
                    options = new String[5];
                }

                options[opcode - 30] = buffer.method453();
                if (options[opcode - 30].equalsIgnoreCase("hidden")) {
                    options[opcode - 30] = null;
                }
            } else if (opcode == 40) {
                int count = buffer.method446();
                oldColors = new int[count];
                newColors = new int[count];
                for (int n = 0; n < count; n++) {
                    oldColors[n] = buffer.method448();
                    newColors[n] = buffer.method448();
                }
            } else if (opcode == 60) {
                int count = buffer.method446();
                headModelIndices = new int[count];
                for (int n = 0; n < count; n++) {
                    headModelIndices[n] = buffer.method448();
                }
            } else if (opcode == 90) {
                buffer.method448();
            } else if (opcode == 91) {
                buffer.method448();
            } else if (opcode == 92) {
                buffer.method448();
            } else if (opcode == 93) {
                showOnMinimap = false;
            } else if (opcode == 95) {
                level = buffer.method448();
            } else if (opcode == 97) {
                scaleX = buffer.method448();
            } else if (opcode == 98) {
                scaleY = buffer.method448();
            }
        } while (true);
    }

    public Model getModel(int primaryFrame, int secondaryFrame, int[] labelGroups) {
        Model model = (Model) models.get(index);
        if (model == null) {
            Model[] models = new Model[modelIndices.length];
            for (int n = 0; n < modelIndices.length; n++) {
                models[n] = new Model(false, modelIndices[n]);
            }

            if (models.length == 1) {
                model = models[0];
            } else {
                model = new Model(0, models, models.length);
            }

            if (oldColors != null) {
                for (int n = 0; n < oldColors.length; n++) {
                    model.recolor(oldColors[n], newColors[n]);
                }
            }

            model.applyGroups(4);
            model.applyLighting(64, 850, -30, -50, -30, true);
            NpcType.models.put(6, index, model);
        }

        model = new Model(0, model, !disposeAlpha);

        if (primaryFrame != -1 && secondaryFrame != -1) {
            model.method359(secondaryFrame, 3, primaryFrame, labelGroups);
        } else if (primaryFrame != -1) {
            model.applyFrame(-16599, primaryFrame);
        }

        if (scaleX != 128 || scaleY != 128) {
            model.scale(scaleX, 2, scaleY, scaleX);
        }

        model.calculateYBoundaries(2992);
        model.skinTriangle = null;
        model.labelVertices = null;

        if (size == 1) {
            model.pickable = true;
        }

        return model;
    }

    public Model getHeadModel(boolean flag) {
        if (headModelIndices == null) {
            return null;
        }

        Model[] models = new Model[headModelIndices.length];
        for (int n = 0; n < headModelIndices.length; n++) {
            models[n] = new Model(false, headModelIndices[n]);
        }

        Model model;
        if (models.length == 1) {
            model = models[0];
        } else {
            model = new Model(0, models, models.length);
        }

        if (oldColors != null) {
            for (int n = 0; n < oldColors.length; n++) {
                model.recolor(oldColors[n], newColors[n]);
            }
        }

        return model;
    }

    public NpcType() {
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

    public boolean aBoolean76;
    public static int count;
    public static int[] offsets;
    public static Buffer data;
    public static NpcType[] cache;
    public static int cacheIndex;
    public long index;
    public String name;
    public byte[] description;
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
    public static Cache models = new Cache((byte) 0, 30);

}
