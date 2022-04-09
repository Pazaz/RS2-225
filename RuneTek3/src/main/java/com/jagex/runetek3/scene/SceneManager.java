package com.jagex.runetek3.scene;

import com.jagex.runetek3.formats.FloorType;
import com.jagex.runetek3.formats.LocType;
import com.jagex.runetek3.graphics.Draw3D;
import com.jagex.runetek3.graphics.Model;
import com.jagex.runetek3.graphics.SeqType;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.LinkedList;

public class SceneManager {

    private static final int[] WALL_ROTATION_TYPE1 = {0x1, 0x2, 0x4, 0x8};
    private static final int[] WALL_ROTATION_TYPE2 = {0x10, 0x20, 0x40, 0x80};
    private static final int[] WALL_DECO_ROT_SIZE_X_DIR = {1, 0, -1, 0};
    private static final int[] WALL_DECO_ROT_SIZE_Y_DIR = {0, -1, 0, 1};
    public static boolean lowMemory = true;
    public static int levelBuilt;
    public static boolean occlusionEnabled;
    public static int rand1 = (int) (Math.random() * 17D) - 8;
    public static int rand2 = (int) (Math.random() * 33D) - 16;
    public int tileCountX;
    public int tileCountZ;
    public int[][][] heightmap;
    public byte[][][] renderFlags;
    public byte[][][] planeUnderlayFloorIndices;
    public byte[][][] planeOverlayFloorIndices;
    public byte[][][] planeOverlayTypes;
    public byte[][][] planeOverlayRotations;
    public byte[][][] shadowmap;
    public int[][] lightmap;
    public int[] blendedHue;
    public int[] blendedSaturation;
    public int[] blendedLightness;
    public int[] blendedHueMultiplier;
    public int[] blendDirectionTracker;
    public int[][][] occludeFlags;
    public SceneManager(int sizeY, byte[][][] renderFlags, int sizeX, int[][][] heightmap) {
        tileCountX = sizeX;
        tileCountZ = sizeY;
        this.heightmap = heightmap;
        this.renderFlags = renderFlags;
        planeUnderlayFloorIndices = new byte[4][tileCountX][tileCountZ];
        planeOverlayFloorIndices = new byte[4][tileCountX][tileCountZ];
        planeOverlayTypes = new byte[4][tileCountX][tileCountZ];
        planeOverlayRotations = new byte[4][tileCountX][tileCountZ];
        occludeFlags = new int[4][tileCountX + 1][tileCountZ + 1];
        shadowmap = new byte[4][tileCountX + 1][tileCountZ + 1];
        lightmap = new int[tileCountX + 1][tileCountZ + 1];
        blendedHue = new int[tileCountZ];
        blendedSaturation = new int[tileCountZ];
        blendedLightness = new int[tileCountZ];
        blendedHueMultiplier = new int[tileCountZ];
        blendDirectionTracker = new int[tileCountZ];
    }

    public static int getPerlinNoise(int i, int j) {
        int k = (getSmoothNoise(i + 45365, j + 0x16713, 4) - 128) + (getSmoothNoise(i + 10294, j + 37821, 2) - 128 >> 1)
            + (getSmoothNoise(i, j, 1) - 128 >> 2);
        k = (int) ((double) k * 0.29999999999999999D) + 35;
        if (k < 10)
            k = 10;
        else if (k > 60)
            k = 60;
        return k;
    }

    public static int getSmoothNoise(int i, int j, int k) {
        int l = i / k;
        int i1 = i & k - 1;
        int j1 = j / k;
        int k1 = j & k - 1;
        int l1 = getSmoothNoise2D(l, j1);
        int i2 = getSmoothNoise2D(l + 1, j1);
        int j2 = getSmoothNoise2D(l, j1 + 1);
        int k2 = getSmoothNoise2D(l + 1, j1 + 1);
        int l2 = getCosineLerp(l1, i2, i1, k);
        int i3 = getCosineLerp(j2, k2, i1, k);
        return getCosineLerp(l2, i3, k1, k);
    }

    public static int getCosineLerp(int i, int j, int k, int l) {
        int i1 = 0x10000 - Draw3D.cos[(k * 1024) / l] >> 1;
        return (i * (0x10000 - i1) >> 16) + (j * i1 >> 16);
    }

    public static int getSmoothNoise2D(int i, int j) {
        int k = getNoise(i - 1, j - 1) + getNoise(i + 1, j - 1) + getNoise(i - 1, j + 1) + getNoise(i + 1, j + 1);
        int l = getNoise(i - 1, j) + getNoise(i + 1, j) + getNoise(i, j - 1) + getNoise(i, j + 1);
        int i1 = getNoise(i, j);
        return k / 16 + l / 8 + i1 / 4;
    }

    public static int getNoise(int i, int j) {
        int k = i + j * 57;
        k = k << 13 ^ k;
        int l = k * (k * k * 15731 + 0xc0ae5) + 0x5208dd0d & 0x7fffffff;
        return l >> 19 & 0xff;
    }

    public static int adjustHSLLightness1(int i, int j) {
        if (i == -1)
            return 0xbc614e;
        j = (j * (i & 0x7f)) / 128;
        if (j < 2)
            j = 2;
        else if (j > 126)
            j = 126;
        return (i & 0xff80) + j;
    }

    public static void addLoc(int x, LinkedList linkedList, CollisionMap collisionMap, int z, int k, int[][][] data, int level,
                              int loc, int type, MapSquare mapSquare, int plane) {
        int i2 = data[plane][x][z];
        int j2 = data[plane][x + 1][z];
        int k2 = data[plane][x + 1][z + 1];
        int l2 = data[plane][x][z + 1];
        int i3 = i2 + j2 + k2 + l2 >> 2;
        LocType locType = LocType.get(loc);
        // object does not exist
        if (locType == null) {
            // now would be the best time to notify the user, with a little popup stating "map load warning" perhaps
            return;
        }
        int j3 = x + (z << 7) + (loc << 14) + 0x40000000;
        if (!locType.interactable)
            j3 += 0x80000000;
        byte byte0 = (byte) ((k << 6) + type);
        if (type == 22) {
            Model m = locType.getModel(22, k, i2, j2, k2, l2, -1);
            mapSquare.addGroundDecoration(m, x, i3, z, level, byte0, j3);
            if (locType.hasCollision && locType.interactable)
                collisionMap.setBlocked(x, z);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 3, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 10 || type == 11) {
            Model m = locType.getModel(10, k, i2, j2, k2, l2, -1);
            if (m != null) {
                int l4 = 0;
                if (type == 11)
                    l4 += 256;
                int i4;
                int k4;
                if (k == 1 || k == 3) {
                    i4 = locType.sizeZ;
                    k4 = locType.sizeX;
                } else {
                    i4 = locType.sizeX;
                    k4 = locType.sizeZ;
                }
                mapSquare.addLocation(m, null, x, z, i4, k4, i3, level, l4, j3, byte0);
            }
            if (locType.hasCollision)
                collisionMap.setLoc(x, z, locType.sizeX, locType.sizeZ, k, locType.isSolid);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 2, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type >= 12) {
            Model m = locType.getModel(type, k, i2, j2, k2, l2, -1);
            mapSquare.addLocation(m, null, x, z, 1, 1, i3, level, 0, j3, byte0);
            if (locType.hasCollision)
                collisionMap.setLoc(x, z, locType.sizeX, locType.sizeZ, k, locType.isSolid);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 2, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 0) {
            Model m = locType.getModel(0, k, i2, j2, k2, l2, -1);
            mapSquare.addWall(m, null, WALL_ROTATION_TYPE1[k], 0, x, z, i3, level, j3, byte0);
            if (locType.hasCollision)
                collisionMap.setWall(x, z, type, k, locType.isSolid);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 0, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 1) {
            Model m = locType.getModel(1, k, i2, j2, k2, l2, -1);
            mapSquare.addWall(m, null, WALL_ROTATION_TYPE2[k], 0, x, z, i3, level, j3, byte0);
            if (locType.hasCollision)
                collisionMap.setWall(x, z, type, k, locType.isSolid);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 0, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 2) {
            int k3 = k + 1 & 3;
            Model m1 = locType.getModel(2, 4 + k, i2, j2, k2, l2, -1);
            Model m2 = locType.getModel(2, k3, i2, j2, k2, l2, -1);
            mapSquare.addWall(m1, m2, WALL_ROTATION_TYPE1[k], WALL_ROTATION_TYPE1[k3], x, z, i3, level,
                j3, byte0);
            if (locType.hasCollision)
                collisionMap.setWall(x, z, type, k, locType.isSolid);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 0, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 3) {
            Model m = locType.getModel(3, k, i2, j2, k2, l2, -1);
            mapSquare.addWall(m, null, WALL_ROTATION_TYPE2[k], 0, x, z, i3, level, j3, byte0);
            if (locType.hasCollision)
                collisionMap.setWall(x, z, type, k, locType.isSolid);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 0, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 9) {
            Model m = locType.getModel(type, k, i2, j2, k2, l2, -1);
            mapSquare.addLocation(m, null, x, z, 1, 1, i3, level, 0, j3, byte0);
            if (locType.hasCollision)
                collisionMap.setLoc(x, z, locType.sizeX, locType.sizeZ, k, locType.isSolid);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 2, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 4) {
            Model m = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            mapSquare.addWallDecoration(m, x, z, i3, 0, 0, j3, k * 512, WALL_ROTATION_TYPE1[k], byte0, level);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 1, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 5) {
            int l3 = 16;
            int j4 = mapSquare.getWallBitset(x, z, level);
            if (j4 > 0)
                l3 = LocType.get(j4 >> 14 & 0x7fff).thickness;
            Model m = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            mapSquare.addWallDecoration(m, x, z, i3, WALL_DECO_ROT_SIZE_X_DIR[k] * l3, WALL_DECO_ROT_SIZE_Y_DIR[k] * l3, j3, k * 512, WALL_ROTATION_TYPE1[k],
                byte0, level);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 1, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 6) {
            Model m = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            mapSquare.addWallDecoration(m, x, z, i3, 0, 0, j3, k, 256, byte0, level);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 1, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 7) {
            Model m = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            mapSquare.addWallDecoration(m, x, z, i3, 0, 0, j3, k, 512, byte0, level);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 1, x, z, SeqType.instances[locType.seqIndex], true));
            return;
        }
        if (type == 8) {
            Model m = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            mapSquare.addWallDecoration(m, x, z, i3, 0, 0, j3, k, 768, byte0, level);
            if (locType.seqIndex != -1)
                linkedList.pushNext(new LocEntity(loc, level, 1, x, z, SeqType.instances[locType.seqIndex], true));
        }
    }

    public void clearLandscape(int i, int j, int l, int i1) {
        byte byte0 = 0;
        for (int j1 = 0; j1 < FloorType.count; j1++) {
            if (!FloorType.instances[j1].name.equalsIgnoreCase("water")) {
                continue;
            }
            byte0 = (byte) (j1 + 1);
            break;
        }

        for (int k1 = j; k1 < j + i1; k1++) {
            for (int l1 = i; l1 < i + l; l1++) {
                if (l1 >= 0 && l1 < tileCountX && k1 >= 0 && k1 < tileCountZ) {
                    planeOverlayFloorIndices[0][l1][k1] = byte0;
                    for (int i2 = 0; i2 < 4; i2++) {
                        heightmap[i2][l1][k1] = 0;
                        renderFlags[i2][l1][k1] = 0;
                    }
                }
            }
        }
    }

    public void readLandscape(byte[] src, int i, int startZ, int startX, int i1) {
        Buffer b = new Buffer(src);
        for (int p = 0; p < 4; p++) {
            for (int x = 0; x < 64; x++) {
                for (int z = 0; z < 64; z++) {
                    int localX = x + startX;
                    int localZ = z + startZ;
                    if (localX >= 0 && localX < 104 && localZ >= 0 && localZ < 104) {
                        renderFlags[p][localX][localZ] = 0;
                        do {
                            int opcode = b.g1();
                            if (opcode == 0) {
                                if (p == 0)
                                    heightmap[0][localX][localZ] = -getPerlinNoise(0xe3b7b + localX + i, 0x87cce + localZ + i1) * 8;
                                else
                                    heightmap[p][localX][localZ] = heightmap[p - 1][localX][localZ] - 240;
                                break;
                            }
                            if (opcode == 1) {
                                int i3 = b.g1();
                                if (i3 == 1)
                                    i3 = 0;
                                if (p == 0)
                                    heightmap[0][localX][localZ] = -i3 * 8;
                                else
                                    heightmap[p][localX][localZ] = heightmap[p - 1][localX][localZ] - i3 * 8;
                                break;
                            }
                            if (opcode <= 49) {
                                planeOverlayFloorIndices[p][localX][localZ] = b.g1s();
                                planeOverlayTypes[p][localX][localZ] = (byte) ((opcode - 2) / 4);
                                planeOverlayRotations[p][localX][localZ] = (byte) (opcode - 2 & 3);
                            } else if (opcode <= 81)
                                renderFlags[p][localX][localZ] = (byte) (opcode - 49);
                            else
                                planeUnderlayFloorIndices[p][localX][localZ] = (byte) (opcode - 81);
                        } while (true);
                    } else {
                        do {
                            int opcode = b.g1();
                            if (opcode == 0) {
                                break;
                            }
                            if (opcode == 1) {
                                b.g1();
                                break;
                            }
                            if (opcode <= 49) {
                                b.g1();
                            }
                        } while (true);
                    }
                }
            }
        }
    }

    public void readLocs(byte[] src, MapSquare mapSquare, CollisionMap[] collisionMaps, LinkedList linkedList, int startZ, int startX) {
        Buffer b = new Buffer(src);
        int locType = -1;
        do {
            int locTypeOff = b.gSmart1or2();
            if (locTypeOff == 0) {
                break;
            }
            locType += locTypeOff;

            int locInfo = 0;
            do {
                int locInfoOff = b.gSmart1or2();
                if (locInfoOff == 0) {
                    break;
                }
                locInfo += locInfoOff - 1;

                int localZ = locInfo & 0x3f;
                int localX = locInfo >> 6 & 0x3f;
                int p = locInfo >> 12;
                int x = localX + startX;
                int z = localZ + startZ;

                int objInfo = b.g1();
                int objType = objInfo >> 2;
                int orientation = objInfo & 3;

                if (x > 0 && z > 0 && x < 103 && z < 103) {
                    int plane = p;
                    if ((renderFlags[1][x][z] & 2) == 2) {
                        plane--;
                    }
                    CollisionMap collisionMap = null;
                    if (plane >= 0) {
                        collisionMap = collisionMaps[plane];
                    }
                    addLoc(collisionMap, p, z, orientation, objType, mapSquare, linkedList, locType, x);
                }
            } while (true);
        } while (true);
    }

    public void addLoc(CollisionMap collisionMap, int p, int z, int orientation, int objType, MapSquare mapSquare,
                       LinkedList locList, int locType, int x) {
        if (lowMemory) {
            if ((renderFlags[p][x][z] & 0x10) != 0) {
                return;
            }

            if (getRenderLevel(p, x, z) != levelBuilt) {
                return;
            }
        }

        int currentRegion = heightmap[p][x][z];
        int northRegion = heightmap[p][x + 1][z];
        int northEastRegion = heightmap[p][x + 1][z + 1];
        int southRegion = heightmap[p][x][z + 1];
        int average = currentRegion + northRegion + northEastRegion + southRegion >> 2;
        LocType loc = LocType.get(locType);
        // object does not exist
        if (loc == null) {
            // now would be the best time to notify the user, with a little popup stating "map load warning" perhaps
            return;
        }
        int flag = x + (z << 7) + (locType << 14) + 0x40000000;
        if (!loc.interactable) {
            flag += 0x80000000;
        }
        byte uid = (byte) ((orientation << 6) + objType);
        if (objType == 22) {
            if (lowMemory && !loc.interactable && !loc.obstructsGround)
                return;
            Model m = loc.getModel(22, orientation, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addGroundDecoration(m, x, average, z, p, uid, flag);
            if (loc.hasCollision && loc.interactable && collisionMap != null)
                collisionMap.setBlocked(x, z);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 3, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 10 || objType == 11) {
            Model m = loc.getModel(10, orientation, currentRegion, northRegion, northEastRegion, southRegion, -1);
            if (m != null) {
                int j4 = 0;
                if (objType == 11)
                    j4 += 256;
                int k3;
                int i4;
                if (orientation == 1 || orientation == 3) {
                    k3 = loc.sizeZ;
                    i4 = loc.sizeX;
                } else {
                    k3 = loc.sizeX;
                    i4 = loc.sizeZ;
                }
                if (mapSquare.addLocation(m, null, x, z, k3, i4, average, p, j4, flag, uid)
                    && loc.hasShadow) {
                    for (int k4 = 0; k4 <= k3; k4++) {
                        for (int l4 = 0; l4 <= i4; l4++) {
                            int i5 = m.lengthXZ / 4;
                            if (i5 > 30)
                                i5 = 30;
                            if (i5 > shadowmap[p][x + k4][z + l4])
                                shadowmap[p][x + k4][z + l4] = (byte) i5;
                        }
                    }
                }
            }
            if (loc.hasCollision && collisionMap != null)
                collisionMap.setLoc(x, z, loc.sizeX, loc.sizeZ, orientation, loc.isSolid);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 2, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType >= 12) {
            Model m = loc.getModel(objType, orientation, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addLocation(m, null, x, z, 1, 1, average, p, 0, flag, uid);
            if (objType <= 17 && objType != 13 && p > 0)
                occludeFlags[p][x][z] |= 0x924;
            if (loc.hasCollision && collisionMap != null)
                collisionMap.setLoc(x, z, loc.sizeX, loc.sizeZ, orientation, loc.isSolid);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 2, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 0) {
            Model m = loc.getModel(0, orientation, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWall(m, null, WALL_ROTATION_TYPE1[orientation], 0, x, z, average, p, flag, uid);
            if (orientation == 0) {
                if (loc.hasShadow) {
                    shadowmap[p][x][z] = 50;
                    shadowmap[p][x][z + 1] = 50;
                }
                if (loc.culls) {
                    occludeFlags[p][x][z] |= 0x249;
                }
            } else if (orientation == 1) {
                if (loc.hasShadow) {
                    shadowmap[p][x][z + 1] = 50;
                    shadowmap[p][x + 1][z + 1] = 50;
                }
                if (loc.culls) {
                    occludeFlags[p][x][z + 1] |= 0x492;
                }
            } else if (orientation == 2) {
                if (loc.hasShadow) {
                    shadowmap[p][x + 1][z] = 50;
                    shadowmap[p][x + 1][z + 1] = 50;
                }
                if (loc.culls) {
                    occludeFlags[p][x + 1][z] |= 0x249;
                }
            } else if (orientation == 3) {
                if (loc.hasShadow) {
                    shadowmap[p][x][z] = 50;
                    shadowmap[p][x + 1][z] = 50;
                }
                if (loc.culls) {
                    occludeFlags[p][x][z] |= 0x492;
                }
            }
            if (loc.hasCollision && collisionMap != null) {
                collisionMap.setWall(x, z, objType, orientation, loc.isSolid);
            }
            if (loc.seqIndex != -1) {
                locList.pushNext(new LocEntity(locType, p, 0, x, z, SeqType.instances[loc.seqIndex], true));
            }
            if (loc.thickness != 16) {
                mapSquare.method298(loc.thickness, x, z, p);
            }
            return;
        }
        if (objType == 1) {
            Model m = loc.getModel(1, orientation, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWall(m, null, WALL_ROTATION_TYPE2[orientation], 0, x, z, average, p, flag, uid);
            if (loc.hasShadow)
                if (orientation == 0)
                    shadowmap[p][x][z + 1] = 50;
                else if (orientation == 1)
                    shadowmap[p][x + 1][z + 1] = 50;
                else if (orientation == 2)
                    shadowmap[p][x + 1][z] = 50;
                else if (orientation == 3)
                    shadowmap[p][x][z] = 50;
            if (loc.hasCollision && collisionMap != null)
                collisionMap.setWall(x, z, objType, orientation, loc.isSolid);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 0, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 2) {
            int i3 = orientation + 1 & 3;
            Model m1 = loc.getModel(2, 4 + orientation, currentRegion, northRegion, northEastRegion, southRegion, -1);
            Model m2 = loc.getModel(2, i3, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWall(m1, m2, WALL_ROTATION_TYPE1[orientation], WALL_ROTATION_TYPE1[i3], x, z, average, p,
                flag, uid);
            if (loc.culls)
                if (orientation == 0) {
                    occludeFlags[p][x][z] |= 0x249;
                    occludeFlags[p][x][z + 1] |= 0x492;
                } else if (orientation == 1) {
                    occludeFlags[p][x][z + 1] |= 0x492;
                    occludeFlags[p][x + 1][z] |= 0x249;
                } else if (orientation == 2) {
                    occludeFlags[p][x + 1][z] |= 0x249;
                    occludeFlags[p][x][z] |= 0x492;
                } else if (orientation == 3) {
                    occludeFlags[p][x][z] |= 0x492;
                    occludeFlags[p][x][z] |= 0x249;
                }
            if (loc.hasCollision && collisionMap != null)
                collisionMap.setWall(x, z, objType, orientation, loc.isSolid);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 0, x, z, SeqType.instances[loc.seqIndex], true));
            if (loc.thickness != 16)
                mapSquare.method298(loc.thickness, x, z, p);
            return;
        }
        if (objType == 3) {
            Model m = loc.getModel(3, orientation, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWall(m, null, WALL_ROTATION_TYPE2[orientation], 0, x, z, average, p, flag, uid);
            if (loc.hasShadow)
                if (orientation == 0)
                    shadowmap[p][x][z + 1] = 50;
                else if (orientation == 1)
                    shadowmap[p][x + 1][z + 1] = 50;
                else if (orientation == 2)
                    shadowmap[p][x + 1][z] = 50;
                else if (orientation == 3)
                    shadowmap[p][x][z] = 50;
            if (loc.hasCollision && collisionMap != null)
                collisionMap.setWall(x, z, objType, orientation, loc.isSolid);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 0, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 9) {
            Model m = loc.getModel(objType, orientation, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addLocation(m, null, x, z, 1, 1, average, p, 0, flag, uid);
            if (loc.hasCollision && collisionMap != null)
                collisionMap.setLoc(x, z, loc.sizeX, loc.sizeZ, orientation, loc.isSolid);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 2, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 4) {
            Model m = loc.getModel(4, 0, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWallDecoration(m, x, z, average, 0, 0, flag, orientation * 512, WALL_ROTATION_TYPE1[orientation], uid, p);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 1, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 5) {
            int j3 = 16;
            int l3 = mapSquare.getWallBitset(x, z, p);
            if (l3 > 0)
                j3 = LocType.get(l3 >> 14 & 0x7fff).thickness;
            Model m = loc.getModel(4, 0, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWallDecoration(m, x, z, average, WALL_DECO_ROT_SIZE_X_DIR[orientation] * j3, WALL_DECO_ROT_SIZE_Y_DIR[orientation] * j3, flag, orientation * 512, WALL_ROTATION_TYPE1[orientation],
                uid, p);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 1, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 6) {
            Model m = loc.getModel(4, 0, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWallDecoration(m, x, z, average, 0, 0, flag, orientation, 256, uid, p);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 1, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 7) {
            Model m = loc.getModel(4, 0, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWallDecoration(m, x, z, average, 0, 0, flag, orientation, 512, uid, p);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 1, x, z, SeqType.instances[loc.seqIndex], true));
            return;
        }
        if (objType == 8) {
            Model m = loc.getModel(4, 0, currentRegion, northRegion, northEastRegion, southRegion, -1);
            mapSquare.addWallDecoration(m, x, z, average, 0, 0, flag, orientation, 768, uid, p);
            if (loc.seqIndex != -1)
                locList.pushNext(new LocEntity(locType, p, 1, x, z, SeqType.instances[loc.seqIndex], true));
        }
    }

    public void buildLandscape(MapSquare mapSquare, CollisionMap[] planeCollisions) {
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 104; k++) {
                for (int i1 = 0; i1 < 104; i1++)
                    if ((renderFlags[j][k][i1] & 1) == 1) {
                        int l1 = j;
                        if ((renderFlags[1][k][i1] & 2) == 2)
                            l1--;
                        if (l1 >= 0)
                            planeCollisions[l1].setBlocked(k, i1);
                    }
            }
        }

        rand1 += (int) (Math.random() * 5D) - 2;
        if (rand1 < -8)
            rand1 = -8;
        if (rand1 > 8)
            rand1 = 8;
        rand2 += (int) (Math.random() * 5D) - 2;
        if (rand2 < -16)
            rand2 = -16;
        if (rand2 > 16)
            rand2 = 16;
        for (int p = 0; p < 4; p++) {
            byte[][] abyte0 = shadowmap[p];
            byte byte0 = 96;
            char c = '\u0300';
            byte byte1 = -50;
            byte byte2 = -10;
            byte byte3 = -50;
            int k3 = (int) Math.sqrt(byte1 * byte1 + byte2 * byte2 + byte3 * byte3);
            int i4 = c * k3 >> 8;
            for (int k4 = 1; k4 < tileCountZ - 1; k4++) {
                for (int k5 = 1; k5 < tileCountX - 1; k5++) {
                    int l6 = heightmap[p][k5 + 1][k4] - heightmap[p][k5 - 1][k4];
                    int i8 = heightmap[p][k5][k4 + 1] - heightmap[p][k5][k4 - 1];
                    int k9 = (int) Math.sqrt(l6 * l6 + 0x10000 + i8 * i8);
                    int l12 = (l6 << 8) / k9;
                    int i14 = 0x10000 / k9;
                    int k15 = (i8 << 8) / k9;
                    int k16 = byte0 + (byte1 * l12 + byte2 * i14 + byte3 * k15) / i4;
                    int k17 = (abyte0[k5 - 1][k4] >> 2) + (abyte0[k5 + 1][k4] >> 3) + (abyte0[k5][k4 - 1] >> 2)
                        + (abyte0[k5][k4 + 1] >> 3) + (abyte0[k5][k4] >> 1);
                    lightmap[k5][k4] = k16 - k17;
                }

            }

            for (int l5 = 0; l5 < tileCountZ; l5++) {
                blendedHue[l5] = 0;
                blendedSaturation[l5] = 0;
                blendedLightness[l5] = 0;
                blendedHueMultiplier[l5] = 0;
                blendDirectionTracker[l5] = 0;
            }

            for (int i7 = -5; i7 < tileCountX + 5; i7++) {
                for (int j8 = 0; j8 < tileCountZ; j8++) {
                    int l9 = i7 + 5;
                    if (l9 >= 0 && l9 < tileCountX) {
                        int i13 = planeUnderlayFloorIndices[p][l9][j8] & 0xff;
                        if (i13 > 0) {
                            FloorType floorType = FloorType.instances[i13 - 1];
                            blendedHue[j8] += floorType.blendHue;
                            blendedSaturation[j8] += floorType.saturation;
                            blendedLightness[j8] += floorType.lightness;
                            blendedHueMultiplier[j8] += floorType.hsl16;
                            blendDirectionTracker[j8]++;
                        }
                    }
                    int j13 = i7 - 5;
                    if (j13 >= 0 && j13 < tileCountX) {
                        int j14 = planeUnderlayFloorIndices[p][j13][j8] & 0xff;
                        if (j14 > 0) {
                            FloorType floorType_1 = FloorType.instances[j14 - 1];
                            blendedHue[j8] -= floorType_1.blendHue;
                            blendedSaturation[j8] -= floorType_1.saturation;
                            blendedLightness[j8] -= floorType_1.lightness;
                            blendedHueMultiplier[j8] -= floorType_1.hsl16;
                            blendDirectionTracker[j8]--;
                        }
                    }
                }

                if (i7 >= 1 && i7 < tileCountX - 1) {
                    int hue = 0;
                    int saturation = 0;
                    int lightness = 0;
                    int hueMultiplier = 0;
                    int directionTracker = 0;
                    for (int l17 = -5; l17 < tileCountZ + 5; l17++) {
                        int k18 = l17 + 5;
                        if (k18 >= 0 && k18 < tileCountZ) {
                            hue += blendedHue[k18];
                            saturation += blendedSaturation[k18];
                            lightness += blendedLightness[k18];
                            hueMultiplier += blendedHueMultiplier[k18];
                            directionTracker += blendDirectionTracker[k18];
                        }
                        int l18 = l17 - 5;
                        if (l18 >= 0 && l18 < tileCountZ) {
                            hue -= blendedHue[l18];
                            saturation -= blendedSaturation[l18];
                            lightness -= blendedLightness[l18];
                            hueMultiplier -= blendedHueMultiplier[l18];
                            directionTracker -= blendDirectionTracker[l18];
                        }
                        if (l17 >= 1 && l17 < tileCountZ - 1
                            && (!lowMemory || (renderFlags[p][i7][l17] & 0x10) == 0
                            && getRenderLevel(p, i7, l17) == levelBuilt)) {
                            int underlayId = planeUnderlayFloorIndices[p][i7][l17] & 0xff;
                            int overlayId = planeOverlayFloorIndices[p][i7][l17] & 0xff;
                            if (overlayId > FloorType.count - 1) {
                                // must be loading a newer map for the flotype to not exist
                                overlayId = 1;
                            }
                            if (underlayId > 0 || overlayId > 0) {
                                int k19 = heightmap[p][i7][l17];
                                int l19 = heightmap[p][i7 + 1][l17];
                                int i20 = heightmap[p][i7 + 1][l17 + 1];
                                int j20 = heightmap[p][i7][l17 + 1];
                                int k20 = lightmap[i7][l17];
                                int l20 = lightmap[i7 + 1][l17];
                                int i21 = lightmap[i7 + 1][l17 + 1];
                                int j21 = lightmap[i7][l17 + 1];
                                int k21 = -1;
                                int l21 = -1;
                                if (underlayId > 0) {
                                    if (hueMultiplier == 0) {
                                        hueMultiplier = 1;
                                    }
                                    int i22 = (hue * 256) / hueMultiplier;
                                    int k22 = saturation / directionTracker;
                                    int i23 = lightness / directionTracker;
                                    k21 = hsl24To16(i22, k22, i23);
                                    i22 = i22 + rand1 & 0xff;
                                    i23 += rand2;
                                    if (i23 < 0)
                                        i23 = 0;
                                    else if (i23 > 255)
                                        i23 = 255;
                                    l21 = hsl24To16(i22, k22, i23);
                                }
                                if (p > 0) {
                                    boolean flag = underlayId != 0 || planeOverlayTypes[p][i7][l17] == 0;
                                    if (overlayId > 0 && !FloorType.instances[overlayId - 1].occlude)
                                        flag = false;
                                    if (flag && k19 == l19 && k19 == i20 && k19 == j20)
                                        occludeFlags[p][i7][l17] |= 0x924;
                                }
                                int j22 = 0;
                                if (k21 != -1)
                                    j22 = Draw3D.palette[adjustHSLLightness1(l21, 96)];
                                if (overlayId == 0) {
                                    mapSquare.addTile(p, i7, l17, 0, 0, -1, k19, l19, i20, j20, adjustHSLLightness1(k21, k20),
                                        adjustHSLLightness1(k21, l20), adjustHSLLightness1(k21, i21), adjustHSLLightness1(k21, j21), 0, 0, 0, 0,
                                        j22, 0);
                                } else {
                                    int l22 = planeOverlayTypes[p][i7][l17] + 1;
                                    byte byte4 = planeOverlayRotations[p][i7][l17];
                                    if (overlayId > FloorType.count) {
                                        // when loading newer maps, it's possible to have less flotypes defined
                                        overlayId = FloorType.count;
                                    }
                                    FloorType overlay = FloorType.instances[overlayId - 1];
                                    int j23 = overlay.textureIndex;
                                    int k23;
                                    int l23;
                                    if (j23 >= 0) {
                                        l23 = Draw3D.getAverageTextureRGB(j23);
                                        k23 = -1;
                                    } else if (overlay.rgb == 0xff00ff) {
                                        l23 = 0;
                                        k23 = -2;
                                        j23 = -1;
                                    } else {
                                        k23 = hsl24To16(overlay.hue, overlay.saturation, overlay.lightness);
                                        l23 = Draw3D.palette[adjustHSLLightness0(overlay.blendHueMultiplier, 96)];
                                    }
                                    mapSquare.addTile(p, i7, l17, l22, byte4, j23, k19, l19, i20, j20,
                                        adjustHSLLightness1(k21, k20), adjustHSLLightness1(k21, l20), adjustHSLLightness1(k21, i21),
                                        adjustHSLLightness1(k21, j21), adjustHSLLightness0(k23, k20), adjustHSLLightness0(k23, l20),
                                        adjustHSLLightness0(k23, i21), adjustHSLLightness0(k23, j21), j22, l23);
                                }
                            }
                        }
                    }

                }
            }

            for (int k8 = 1; k8 < tileCountZ - 1; k8++) {
                for (int j10 = 1; j10 < tileCountX - 1; j10++)
                    mapSquare.setPhysicalLevel(p, j10, k8, getRenderLevel(p, j10, k8));

            }

        }

        if (!occlusionEnabled) {
            mapSquare.applyLighting(-50, -10, -50, 64, 768);
        }

        for (int k1 = 0; k1 < tileCountX; k1++) {
            for (int i2 = 0; i2 < tileCountZ; i2++) {
                if ((renderFlags[1][k1][i2] & 2) == 2) {
                    mapSquare.setBridge(k1, i2);
                }
            }
        }

        if (occlusionEnabled) {
            return;
        }

        int j2 = 1;
        int k2 = 2;
        int l2 = 4;
        for (int i3 = 0; i3 < 4; i3++) {
            if (i3 > 0) {
                j2 <<= 3;
                k2 <<= 3;
                l2 <<= 3;
            }
            for (int j3 = 0; j3 <= i3; j3++) {
                for (int l3 = 0; l3 <= tileCountZ; l3++) {
                    for (int j4 = 0; j4 <= tileCountX; j4++) {
                        if ((occludeFlags[j3][j4][l3] & j2) != 0) {
                            int l4 = l3;
                            int i6 = l3;
                            int j7 = j3;
                            int l8 = j3;
                            for (; l4 > 0 && (occludeFlags[j3][j4][l4 - 1] & j2) != 0; l4--)
                                ;
                            for (; i6 < tileCountZ && (occludeFlags[j3][j4][i6 + 1] & j2) != 0; i6++)
                                ;
                            label0:
                            for (; j7 > 0; j7--) {
                                for (int k10 = l4; k10 <= i6; k10++)
                                    if ((occludeFlags[j7 - 1][j4][k10] & j2) == 0)
                                        break label0;

                            }

                            label1:
                            for (; l8 < i3; l8++) {
                                for (int l10 = l4; l10 <= i6; l10++)
                                    if ((occludeFlags[l8 + 1][j4][l10] & j2) == 0)
                                        break label1;

                            }

                            int i11 = ((l8 + 1) - j7) * ((i6 - l4) + 1);
                            if (i11 >= 8) {
                                char c1 = '\360';
                                int l14 = heightmap[l8][j4][l4] - c1;
                                int i16 = heightmap[j7][j4][l4];
                                MapSquare.addOcclude(j4 * 128, l14, l4 * 128, j4 * 128, i6 * 128 + 128, i16, 1, i3);
                                for (int i17 = j7; i17 <= l8; i17++) {
                                    for (int i18 = l4; i18 <= i6; i18++)
                                        occludeFlags[i17][j4][i18] &= ~j2;

                                }

                            }
                        }
                        if ((occludeFlags[j3][j4][l3] & k2) != 0) {
                            int i5 = j4;
                            int j6 = j4;
                            int k7 = j3;
                            int i9 = j3;
                            for (; i5 > 0 && (occludeFlags[j3][i5 - 1][l3] & k2) != 0; i5--)
                                ;
                            for (; j6 < tileCountX && (occludeFlags[j3][j6 + 1][l3] & k2) != 0; j6++)
                                ;
                            label2:
                            for (; k7 > 0; k7--) {
                                for (int j11 = i5; j11 <= j6; j11++)
                                    if ((occludeFlags[k7 - 1][j11][l3] & k2) == 0)
                                        break label2;

                            }

                            label3:
                            for (; i9 < i3; i9++) {
                                for (int k11 = i5; k11 <= j6; k11++)
                                    if ((occludeFlags[i9 + 1][k11][l3] & k2) == 0)
                                        break label3;

                            }

                            int l11 = ((i9 + 1) - k7) * ((j6 - i5) + 1);
                            if (l11 >= 8) {
                                char c2 = '\360';
                                int i15 = heightmap[i9][i5][l3] - c2;
                                int j16 = heightmap[k7][i5][l3];
                                MapSquare.addOcclude(i5 * 128, i15, l3 * 128, j6 * 128 + 128, l3 * 128, j16, 2, i3);
                                for (int j17 = k7; j17 <= i9; j17++) {
                                    for (int j18 = i5; j18 <= j6; j18++)
                                        occludeFlags[j17][j18][l3] &= ~k2;

                                }

                            }
                        }
                        if ((occludeFlags[j3][j4][l3] & l2) != 0) {
                            int j5 = j4;
                            int k6 = j4;
                            int l7 = l3;
                            int j9 = l3;
                            for (; l7 > 0 && (occludeFlags[j3][j4][l7 - 1] & l2) != 0; l7--)
                                ;
                            for (; j9 < tileCountZ && (occludeFlags[j3][j4][j9 + 1] & l2) != 0; j9++)
                                ;
                            label4:
                            for (; j5 > 0; j5--) {
                                for (int i12 = l7; i12 <= j9; i12++)
                                    if ((occludeFlags[j3][j5 - 1][i12] & l2) == 0)
                                        break label4;

                            }

                            label5:
                            for (; k6 < tileCountX; k6++) {
                                for (int j12 = l7; j12 <= j9; j12++)
                                    if ((occludeFlags[j3][k6 + 1][j12] & l2) == 0)
                                        break label5;

                            }

                            if (((k6 - j5) + 1) * ((j9 - l7) + 1) >= 4) {
                                int k12 = heightmap[j3][j5][l7];
                                MapSquare.addOcclude(j5 * 128, k12, l7 * 128, k6 * 128 + 128, j9 * 128 + 128, k12, 4, i3
                                );
                                for (int l13 = j5; l13 <= k6; l13++) {
                                    for (int j15 = l7; j15 <= j9; j15++)
                                        occludeFlags[j3][l13][j15] &= ~l2;

                                }

                            }
                        }
                    }

                }

            }

        }

    }

    public int getRenderLevel(int i, int j, int k) {
        if ((renderFlags[i][j][k] & 8) != 0)
            return 0;
        if (i > 0 && (renderFlags[1][j][k] & 2) != 0)
            return i - 1;
        else
            return i;
    }

    public int adjustHSLLightness0(int i, int j) {
        if (i == -2)
            return 0xbc614e;
        if (i == -1) {
            if (j < 0)
                j = 0;
            else if (j > 127)
                j = 127;
            j = 127 - j;
            return j;
        }
        j = (j * (i & 0x7f)) / 128;
        if (j < 2)
            j = 2;
        else if (j > 126)
            j = 126;
        return (i & 0xff80) + j;
    }

    public int hsl24To16(int i, int j, int k) {
        if (k > 179)
            j /= 2;
        if (k > 192)
            j /= 2;
        if (k > 217)
            j /= 2;
        if (k > 243)
            j /= 2;
        int l = (i / 4 << 10) + (j / 32 << 7) + k / 2;
        return l;
    }

}
