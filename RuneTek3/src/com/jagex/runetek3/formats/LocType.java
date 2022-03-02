package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Cache;

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
        models = new Cache(500);
        builtModels = new Cache(30);
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
        // if the model ID does not exist (i.e. loading newer revision maps), fail gracefully
        if (index > count - 1) {
            return null;
        }
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
        translateX = 0;
        translateY = 0;
        translateZ = 0;
        aBoolean73 = false;
    }

    public void read(Buffer buffer) {
        int bool = -1;

        do {
            int opcode = buffer.readByte();

            if (opcode == 0) {
                break;
            } else if (opcode == 1) {
                int count = buffer.readByte();
                modelTypes = new int[count];
                modelIndices = new int[count];

                for (int n = 0; n < count; n++) {
                    modelIndices[n] = buffer.readWord();
                    modelTypes[n] = buffer.readByte();
                }
            } else if (opcode == 2) {
                name = buffer.readString();
            } else if (opcode == 3) {
                description = buffer.readString();
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
                translateX = buffer.readWordSigned();
            } else if (opcode == 71) {
                translateY = buffer.readWordSigned();
            } else if (opcode == 72) {
                translateZ = buffer.readWordSigned();
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

    public Model getModel(int type, int rotation, int southwestY, int southeastY, int northeastY, int northwestY, int seqFrame) {
        int modelType = -1;
        for (int i = 0; i < modelTypes.length; i++) {
            if (modelTypes[i] != type) {
                continue;
            }
            modelType = i;
            break;
        }

        if (modelType == -1) {
            return null;
        }

        long uid = ((long) index << 6) + ((long) modelType << 3) + rotation + ((long) (seqFrame + 1) << 32);
        if (aBoolean35) {
            uid = 0L;
        }

        Model m = (Model) builtModels.get(uid);
        if (m != null) {
            if (aBoolean35) {
                return m;
            }

            if (adjustToTerrain || flatShaded) {
                m = new Model(m, adjustToTerrain, flatShaded);
            }

            if (adjustToTerrain) {
                int j2 = (southwestY + southeastY + northeastY + northwestY) / 4;
                for (int i3 = 0; i3 < m.vertexCount; i3++) {
                    int j3 = m.vertexX[i3];
                    int k3 = m.vertexZ[i3];
                    int l3 = southwestY + ((southeastY - southwestY) * (j3 + 64)) / 128;
                    int i4 = northwestY + ((northeastY - northwestY) * (j3 + 64)) / 128;
                    int j4 = l3 + ((i4 - l3) * (k3 + 64)) / 128;
                    m.vertexY[i3] += j4 - j2;
                }

                m.calculateYBoundaries2();
            }
            return m;
        }

        if (modelType >= modelIndices.length) {
            return null;
        }

        int modelIndex = modelIndices[modelType];
        if (modelIndex == -1) {
            return null;
        }

        boolean flipBackwards = rotateCounterClockwise ^ (rotation > 3);
        if (flipBackwards) {
            modelIndex += 0x10000;
        }

        Model m2 = (Model) models.get(modelIndex);
        if (m2 == null) {
            m2 = new Model(modelIndex & 0xffff);
            if (flipBackwards) {
                m2.flipBackwards();
            }
            models.put(modelIndex, m2);
        }

        boolean rescale = scaleX != 128 || scaleY != 128 || scaleZ != 128;
        boolean move = translateX != 0 || translateY != 0 || translateZ != 0;
        Model m3 = new Model(m2, oldColors == null, !disposeAlpha, rotation == 0 && seqFrame == -1 && !rescale && !move);

        if (seqFrame != -1) {
            m3.applyGroups();
            m3.applyFrame(seqFrame);
            m3.skinTriangle = null;
            m3.labelVertices = null;
        }

        while (rotation-- > 0) {
            m3.rotateCounterClockwise();
        }

        if (oldColors != null) {
            for (int n = 0; n < oldColors.length; n++) {
                m3.recolor(oldColors[n], newColors[n]);
            }
        }

        if (rescale) {
            m3.scale(scaleZ, scaleY, scaleX);
        }

        if (move) {
            m3.translate(translateY, translateX, translateZ);
        }

        m3.applyLighting(64 + brightness, 768 + specular * 5, -50, -10, -50, !flatShaded);

        if (hasCollision) {
            m3.anInt1251 = m3.maxBoundY;
        }

        builtModels.put(uid, m3);

        if (adjustToTerrain || flatShaded) {
            m3 = new Model(m3, adjustToTerrain, flatShaded);
        }

        if (adjustToTerrain) {
            int averageY = (southwestY + southeastY + northeastY + northwestY) / 4;
            for (int v = 0; v < m3.vertexCount; v++) {
                int x = m3.vertexX[v];
                int z = m3.vertexZ[v];
                int averageY1 = southwestY + ((southeastY - southwestY) * (x + 64)) / 128;
                int averageY2 = northwestY + ((northeastY - northwestY) * (x + 64)) / 128;
                int y = averageY1 + ((averageY2 - averageY1) * (z + 64)) / 128;
                m3.vertexY[v] += y - averageY;
            }

            m3.calculateYBoundaries2();
        }

        return m3;
    }

    public LocType() {
        index = -1;
    }

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
    public String description;
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
    public int translateX;
    public int translateY;
    public int translateZ;
    public int interactionSideFlags;
    public boolean aBoolean73;
    public static Cache models = new Cache(500);
    public static Cache builtModels = new Cache(30);

}