package com.runescape.cache;

import com.runescape.util.Buffer;
import com.runescape.util.Cache;

public class LocType {

    public static void load(FileArchive fileArchive) {
        data = new Buffer(fileArchive.read("loc.dat", null));
        Buffer buffer = new Buffer(fileArchive.read("loc.idx", null));

        count = buffer.readWord();
        offsets = new int[count];

        int i = 2;
        for (int n = 0; n < count; n++) {
            offsets[n] = i;
            i += buffer.readWord();
        }

        cache = new LocType[10];

        for (int n = 0; n < 10; n++) {
            cache[n] = new LocType();
        }
    }

    public static void unload() {
        models = null;
        builtModels = null;
        offsets = null;
        cache = null;
        data = null;
    }

    public static LocType get(int index) {
        for (int n = 0; n < 10; n++) {
            if (cache[n].index == index) {
                return cache[n];
            }
        }

        position = (position + 1) % 10;
        LocType locType = cache[position];
        data.offset = offsets[index];
        locType.index = index;
        locType.reset();
        locType.read(data);
        return locType;
    }

    public void reset() {
        modelIndices = null;
        modelTypes = null;
        name = null;
        description = null;
        oldColors = null;
        newColors = null;
        sizeX = 1;
        sizeZ = 1;
        hasCollision = true;
        isSolid = true;
        interactable = false;
        adjustToTerrain = false;
        flatShaded = false;
        culls = false;
        animationIndex = -1;
        thickness = 16;
        brightness = 0;
        specular = 0;
        actions = null;
        disposeAlpha = false;
        mapfunction = -1;
        mapscene = -1;
        rotateCounterClockwise = false;
        hasShadow = true;
        scaleX = 128;
        scaleY = 128;
        scaleZ = 128;
        interactionSideFlags = 0;
        anInt69 = 0;
        anInt70 = 0;
        anInt71 = 0;
        aBoolean73 = false;
    }

    public void read(Buffer buffer) {
        int bool = -1;

        do {
            int opcode = buffer.readByte();

            if (opcode == 0) {
                break;
            } else if (opcode == 1) {
                int n = buffer.readByte();
                modelTypes = new int[n];
                modelIndices = new int[n];

                for (int j1 = 0; j1 < n; j1++) {
                    modelIndices[j1] = buffer.readWord();
                    modelTypes[j1] = buffer.readByte();
                }
            } else if (opcode == 2) {
                name = buffer.readString();
            } else if (opcode == 3) {
                description = buffer.readStringRaw();
            } else if (opcode == 14) {
                sizeX = buffer.readByte();
            } else if (opcode == 15) {
                sizeZ = buffer.readByte();
            } else if (opcode == 17) {
                hasCollision = false;
            } else if (opcode == 18) {
                isSolid = false;
            } else if (opcode == 19) {
                bool = buffer.readByte();

                if (bool == 1) {
                    interactable = true;
                }
            } else if (opcode == 21) {
                adjustToTerrain = true;
            } else if (opcode == 22) {
                flatShaded = true;
            } else if (opcode == 23) {
                culls = true;
            } else if (opcode == 24) {
                animationIndex = buffer.readWord();
                
                if (animationIndex == 65535) {
                    animationIndex = -1;
                }
            } else if (opcode == 25) {
                disposeAlpha = true;
            } else if (opcode == 28) {
                thickness = buffer.readByte();
            } else if (opcode == 29) {
                brightness = buffer.readByteSigned();
            } else if (opcode == 39) {
                specular = buffer.readByteSigned();
            } else if (opcode >= 30 && opcode < 39) {
                if (actions == null) {
                    actions = new String[5];
                }

                actions[opcode - 30] = buffer.readString();
                if (actions[opcode - 30].equalsIgnoreCase("hidden")) {
                    actions[opcode - 30] = null;
                }
            } else if (opcode == 40) {
                int n = buffer.readByte();
                oldColors = new int[n];
                newColors = new int[n];
                for (int m = 0; m < n; m++) {
                    oldColors[m] = buffer.readWord();
                    newColors[m] = buffer.readWord();
                }
            } else if (opcode == 60) {
                mapfunction = buffer.readWord();
            } else if (opcode == 62) {
                rotateCounterClockwise = true;
            } else if (opcode == 64) {
                hasShadow = false;
            } else if (opcode == 65) {
                scaleX = buffer.readWord();
            } else if (opcode == 66) {
                scaleY = buffer.readWord();
            } else if (opcode == 67) {
                scaleZ = buffer.readWord();
            } else if (opcode == 68) {
                mapscene = buffer.readWord();
            } else if (opcode == 69) {
                interactionSideFlags = buffer.readByte();
            } else if (opcode == 70) {
                anInt69 = buffer.readWordSigned();
            } else if (opcode == 71) {
                anInt70 = buffer.readWordSigned();
            } else if (opcode == 72) {
                anInt71 = buffer.readWordSigned();
            } else if (opcode == 73) {
                aBoolean73 = true;
            }
        } while (true);

        if (modelTypes == null) {
            modelTypes = new int[0];
        }

        if (bool == -1) {
            interactable = modelTypes.length > 0 && modelTypes[0] == 10;
            if (actions != null) {
                interactable = true;
            }
        }
    }

    public Model getModel(int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = -1;
        for (int i2 = 0; i2 < modelTypes.length; i2++) {
            if (modelTypes[i2] != i)
                continue;
            l1 = i2;
            break;
        }

        if (l1 == -1)
            return null;

        long l2 = ((long) index << 6) + ((long) l1 << 3) + j + ((long) (k1 + 1) << 32);
        if (aBoolean35) {
            l2 = 0L;
        }

        Model class38_sub2_sub1 = (Model) builtModels.get(l2);
        if (class38_sub2_sub1 != null) {
            if (aBoolean35) {
                return class38_sub2_sub1;
            }

            if (adjustToTerrain || flatShaded) {
                class38_sub2_sub1 = new Model(class38_sub2_sub1, (byte) -31, adjustToTerrain, flatShaded);
            }

            if (adjustToTerrain) {
                int j2 = (k + l + i1 + j1) / 4;
                for (int i3 = 0; i3 < class38_sub2_sub1.anInt1222; i3++) {
                    int j3 = class38_sub2_sub1.anIntArray1223[i3];
                    int k3 = class38_sub2_sub1.anIntArray1225[i3];
                    int l3 = k + ((l - k) * (j3 + 64)) / 128;
                    int i4 = j1 + ((i1 - j1) * (j3 + 64)) / 128;
                    int j4 = l3 + ((i4 - l3) * (k3 + 64)) / 128;
                    class38_sub2_sub1.anIntArray1224[i3] += j4 - j2;
                }

                class38_sub2_sub1.method355(anInt34);
            }
            return class38_sub2_sub1;
        }

        if (l1 >= modelIndices.length) {
            return null;
        }

        int k2 = modelIndices[l1];
        if (k2 == -1) {
            return null;
        }

        boolean flag = rotateCounterClockwise ^ (j > 3);
        if (flag) {
            k2 += 0x10000;
        }

        Model class38_sub2_sub1_1 = (Model) models.get(k2);
        if (class38_sub2_sub1_1 == null) {
            class38_sub2_sub1_1 = new Model(false, k2 & 0xffff);
            if (flag) {
                class38_sub2_sub1_1.method365(-725);
            }
            models.put(k2, class38_sub2_sub1_1);
        }

        boolean flag1;
        flag1 = scaleX != 128 || scaleY != 128 || scaleZ != 128;

        boolean flag2;
        flag2 = anInt69 != 0 || anInt70 != 0 || anInt71 != 0;
        Model model = new Model(class38_sub2_sub1_1, oldColors == null, !disposeAlpha, anInt33, j == 0 && k1 == -1 && !flag1 && !flag2);

        if (k1 != -1) {
            model.applyGroups(4);
            model.applyFrame(-16599, k1);
            model.skinTriangle = null;
            model.labelVertices = null;
        }

        while (j-- > 0) {
            model.method361(0);
        }

        if (oldColors != null) {
            for (int k4 = 0; k4 < oldColors.length; k4++) {
                model.recolor(oldColors[k4], newColors[k4]);
            }
        }

        if (flag1) {
            model.scale(scaleZ, 2, scaleY, scaleX);
        }

        if (flag2) {
            model.translate(anInt70, anInt69, -122, anInt71);
        }

        model.applyLighting(64 + brightness, 768 + specular * 5, -50, -10, -50, !flatShaded);

        if (hasCollision) {
            model.anInt1251 = model.minY;
        }

        builtModels.put(l2, model);

        if (adjustToTerrain || flatShaded) {
            model = new Model(model, (byte) -31, adjustToTerrain, flatShaded);
        }

        if (adjustToTerrain) {
            int l4 = (k + l + i1 + j1) / 4;
            for (int i5 = 0; i5 < model.anInt1222; i5++) {
                int j5 = model.anIntArray1223[i5];
                int k5 = model.anIntArray1225[i5];
                int l5 = k + ((l - k) * (j5 + 64)) / 128;
                int i6 = j1 + ((i1 - j1) * (j5 + 64)) / 128;
                int j6 = l5 + ((i6 - l5) * (k5 + 64)) / 128;
                model.anIntArray1224[i5] += j6 - l4;
            }

            model.method355(anInt34);
        }

        return model;
    }

    public LocType() {
        index = -1;
    }

    public static int anInt33;
    public static int anInt34;
    public static boolean aBoolean35;
    public static int count;
    public static int[] offsets;
    public static Buffer data;
    public static LocType[] cache;
    public static int position;
    public int index;
    public int[] modelIndices;
    public int[] modelTypes;
    public String name;
    public byte[] description;
    public int[] oldColors;
    public int[] newColors;
    public int sizeX;
    public int sizeZ;
    public boolean hasCollision;
    public boolean isSolid;
    public boolean interactable;
    public boolean adjustToTerrain;
    public boolean flatShaded;
    public boolean culls;
    public int animationIndex;
    public int thickness;
    public byte brightness;
    public byte specular;
    public String[] actions;
    public boolean disposeAlpha;
    public int mapfunction;
    public int mapscene;
    public boolean rotateCounterClockwise;
    public boolean hasShadow;
    public int scaleX;
    public int scaleY;
    public int scaleZ;
    public int anInt69;
    public int anInt70;
    public int anInt71;
    public int interactionSideFlags;
    public boolean aBoolean73;
    public static Cache models = new Cache(500);
    public static Cache builtModels = new Cache(30);

}
