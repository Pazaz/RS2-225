package com.runescape.scene;

import com.runescape.cache.FloType;
import com.runescape.cache.LocType;
import com.runescape.cache.Model;
import com.runescape.cache.SeqType;
import com.runescape.graphics.Draw3D;
import com.runescape.util.Buffer;
import com.runescape.util.LinkedList;

public class SceneBuilder {

    public SceneBuilder(int sizeY, byte[][][] renderFlags, int sizeX, int[][][] heightmap) {
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

    public void clearLandscape(int i, int j, int l, int i1) {
        byte byte0 = 0;
        for (int j1 = 0; j1 < FloType.count; j1++) {
            if (!FloType.instances[j1].name.equalsIgnoreCase("water"))
                continue;
            byte0 = (byte) (j1 + 1);
            break;
        }

        for (int k1 = j; k1 < j + i1; k1++) {
            for (int l1 = i; l1 < i + l; l1++)
                if (l1 >= 0 && l1 < tileCountX && k1 >= 0 && k1 < tileCountZ) {
                    planeOverlayFloorIndices[0][l1][k1] = byte0;
                    for (int i2 = 0; i2 < 4; i2++) {
                        heightmap[i2][l1][k1] = 0;
                        renderFlags[i2][l1][k1] = 0;
                    }

                }

        }

    }

    public void readLandscape(byte[] abyte0, int i, int k, int l, int i1) {
        Buffer class38_sub2_sub3 = new Buffer(abyte0);
        for (int j1 = 0; j1 < 4; j1++) {
            for (int k1 = 0; k1 < 64; k1++) {
                for (int l1 = 0; l1 < 64; l1++) {
                    int i2 = k1 + l;
                    int j2 = l1 + k;
                    if (i2 >= 0 && i2 < 104 && j2 >= 0 && j2 < 104) {
                        renderFlags[j1][i2][j2] = 0;
                        do {
                            int k2 = class38_sub2_sub3.readByte();
                            if (k2 == 0) {
                                if (j1 == 0)
                                    heightmap[0][i2][j2] = -getPerlinNoise(0xe3b7b + i2 + i, 0x87cce + j2 + i1)
                                            * 8;
                                else
                                    heightmap[j1][i2][j2] = heightmap[j1 - 1][i2][j2] - 240;
                                break;
                            }
                            if (k2 == 1) {
                                int i3 = class38_sub2_sub3.readByte();
                                if (i3 == 1)
                                    i3 = 0;
                                if (j1 == 0)
                                    heightmap[0][i2][j2] = -i3 * 8;
                                else
                                    heightmap[j1][i2][j2] = heightmap[j1 - 1][i2][j2]
                                            - i3 * 8;
                                break;
                            }
                            if (k2 <= 49) {
                                planeOverlayFloorIndices[j1][i2][j2] = class38_sub2_sub3.readByteSigned();
                                planeOverlayTypes[j1][i2][j2] = (byte) ((k2 - 2) / 4);
                                planeOverlayRotations[j1][i2][j2] = (byte) (k2 - 2 & 3);
                            } else if (k2 <= 81)
                                renderFlags[j1][i2][j2] = (byte) (k2 - 49);
                            else
                                planeUnderlayFloorIndices[j1][i2][j2] = (byte) (k2 - 81);
                        } while (true);
                    } else {
                        do {
                            int l2 = class38_sub2_sub3.readByte();
                            if (l2 == 0)
                                break;
                            if (l2 == 1) {
                                class38_sub2_sub3.readByte();
                                break;
                            }
                            if (l2 <= 49)
                                class38_sub2_sub3.readByte();
                        } while (true);
                    }
                }

            }

        }

    }

    public void readLocs(byte[] abyte0, Scene scene, CollisionMap[] aclass8, LinkedList linkedList, int i, int j) {
        label0:
        {
            Buffer class38_sub2_sub3 = new Buffer(abyte0);
            int k = -1;
            do {
                int l = class38_sub2_sub3.readSmart();
                if (l == 0)
                    break label0;
                k += l;
                int i1 = 0;
                do {
                    int j1 = class38_sub2_sub3.readSmart();
                    if (j1 == 0)
                        break;
                    i1 += j1 - 1;
                    int k1 = i1 & 0x3f;
                    int l1 = i1 >> 6 & 0x3f;
                    int i2 = i1 >> 12;
                    int j2 = class38_sub2_sub3.readByte();
                    int k2 = j2 >> 2;
                    int l2 = j2 & 3;
                    int i3 = l1 + j;
                    int j3 = k1 + i;
                    if (i3 > 0 && j3 > 0 && i3 < 103 && j3 < 103) {
                        int k3 = i2;
                        if ((renderFlags[1][i3][j3] & 2) == 2)
                            k3--;
                        CollisionMap collisionMap = null;
                        if (k3 >= 0)
                            collisionMap = aclass8[k3];
                        addLoc(collisionMap, i2, j3, l2, k2, scene, linkedList, k, i3);
                    }
                } while (true);
            } while (true);
        }
    }

    public void addLoc(CollisionMap collisionMap, int i, int j, int k, int l, Scene scene,
                       LinkedList linkedList, int i1, int j1) {
        if (lowMemory) {
            if ((renderFlags[i][j1][j] & 0x10) != 0)
                return;
            if (getRenderLevel(i, j1, j) != levelBuilt)
                return;
        }
        int k1 = heightmap[i][j1][j];
        int l1 = heightmap[i][j1 + 1][j];
        int i2 = heightmap[i][j1 + 1][j + 1];
        int j2 = heightmap[i][j1][j + 1];
        int k2 = k1 + l1 + i2 + j2 >> 2;
        LocType locType = LocType.get(i1);
        int l2 = j1 + (j << 7) + (i1 << 14) + 0x40000000;
        if (!locType.interactable)
            l2 += 0x80000000;
        byte byte0 = (byte) ((k << 6) + l);
        if (l == 22) {
            if (lowMemory && !locType.interactable && !locType.aBoolean73)
                return;
            Model class38_sub2_sub1 = locType.getModel(22, k, k1, l1, i2, j2, -1);
            scene.addGroundDecoration(class38_sub2_sub1, j1, l2, j, i, byte0, k2);
            if (locType.hasCollision && locType.interactable && collisionMap != null)
                collisionMap.setBlocked(j, j1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 3, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 10 || l == 11) {
            Model class38_sub2_sub1_1 = locType.getModel(10, k, k1, l1, i2, j2, -1);
            if (class38_sub2_sub1_1 != null) {
                int j4 = 0;
                if (l == 11)
                    j4 += 256;
                int k3;
                int i4;
                if (k == 1 || k == 3) {
                    k3 = locType.sizeZ;
                    i4 = locType.sizeX;
                } else {
                    k3 = locType.sizeX;
                    i4 = locType.sizeZ;
                }
                if (scene.addLocation(k2, i, null, l2, j, j1, k3, byte0, class38_sub2_sub1_1, j4, i4)
                        && locType.hasShadow) {
                    for (int k4 = 0; k4 <= k3; k4++) {
                        for (int l4 = 0; l4 <= i4; l4++) {
                            int i5 = class38_sub2_sub1_1.lengthXZ / 4;
                            if (i5 > 30)
                                i5 = 30;
                            if (i5 > shadowmap[i][j1 + k4][j + l4])
                                shadowmap[i][j1 + k4][j + l4] = (byte) i5;
                        }

                    }

                }
            }
            if (locType.hasCollision && collisionMap != null)
                collisionMap.setLoc(k, locType.sizeZ, locType.sizeX, j1, j, locType.isSolid);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 2, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l >= 12) {
            Model class38_sub2_sub1_2 = locType.getModel(l, k, k1, l1, i2, j2, -1);
            scene.addLocation(k2, i, null, l2, j, j1, 1, byte0, class38_sub2_sub1_2, 0, 1);
            if (l >= 12 && l <= 17 && l != 13 && i > 0)
                occludeFlags[i][j1][j] |= 0x924;
            if (locType.hasCollision && collisionMap != null)
                collisionMap.setLoc(k, locType.sizeZ, locType.sizeX, j1, j, locType.isSolid);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 2, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 0) {
            Model class38_sub2_sub1_3 = locType.getModel(0, k, k1, l1, i2, j2, -1);
            scene.addWall(0, k2, i, WALL_ROTATION_TYPE1[k], class38_sub2_sub1_3, null, j1, l2, j, byte0);
            if (k == 0) {
                if (locType.hasShadow) {
                    shadowmap[i][j1][j] = 50;
                    shadowmap[i][j1][j + 1] = 50;
                }
                if (locType.culls)
                    occludeFlags[i][j1][j] |= 0x249;
            } else if (k == 1) {
                if (locType.hasShadow) {
                    shadowmap[i][j1][j + 1] = 50;
                    shadowmap[i][j1 + 1][j + 1] = 50;
                }
                if (locType.culls)
                    occludeFlags[i][j1][j + 1] |= 0x492;
            } else if (k == 2) {
                if (locType.hasShadow) {
                    shadowmap[i][j1 + 1][j] = 50;
                    shadowmap[i][j1 + 1][j + 1] = 50;
                }
                if (locType.culls)
                    occludeFlags[i][j1 + 1][j] |= 0x249;
            } else if (k == 3) {
                if (locType.hasShadow) {
                    shadowmap[i][j1][j] = 50;
                    shadowmap[i][j1 + 1][j] = 50;
                }
                if (locType.culls)
                    occludeFlags[i][j1][j] |= 0x492;
            }
            if (locType.hasCollision && collisionMap != null)
                collisionMap.setWall(k, j, j1, locType.isSolid, l);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 0, SeqType.animations[locType.animationIndex], j, j1));
            if (locType.thickness != 16)
                scene.method298(i, j, j1, locType.thickness);
            return;
        }
        if (l == 1) {
            Model class38_sub2_sub1_4 = locType.getModel(1, k, k1, l1, i2, j2, -1);
            scene.addWall(0, k2, i, WALL_ROTATION_TYPE2[k], class38_sub2_sub1_4, null, j1, l2, j, byte0);
            if (locType.hasShadow)
                if (k == 0)
                    shadowmap[i][j1][j + 1] = 50;
                else if (k == 1)
                    shadowmap[i][j1 + 1][j + 1] = 50;
                else if (k == 2)
                    shadowmap[i][j1 + 1][j] = 50;
                else if (k == 3)
                    shadowmap[i][j1][j] = 50;
            if (locType.hasCollision && collisionMap != null)
                collisionMap.setWall(k, j, j1, locType.isSolid, l);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 0, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 2) {
            int i3 = k + 1 & 3;
            Model class38_sub2_sub1_11 = locType.getModel(2, 4 + k, k1, l1, i2, j2, -1);
            Model class38_sub2_sub1_12 = locType.getModel(2, i3, k1, l1, i2, j2, -1);
            scene.addWall(WALL_ROTATION_TYPE1[i3], k2, i, WALL_ROTATION_TYPE1[k], class38_sub2_sub1_11, class38_sub2_sub1_12,
                    j1, l2, j, byte0);
            if (locType.culls)
                if (k == 0) {
                    occludeFlags[i][j1][j] |= 0x249;
                    occludeFlags[i][j1][j + 1] |= 0x492;
                } else if (k == 1) {
                    occludeFlags[i][j1][j + 1] |= 0x492;
                    occludeFlags[i][j1 + 1][j] |= 0x249;
                } else if (k == 2) {
                    occludeFlags[i][j1 + 1][j] |= 0x249;
                    occludeFlags[i][j1][j] |= 0x492;
                } else if (k == 3) {
                    occludeFlags[i][j1][j] |= 0x492;
                    occludeFlags[i][j1][j] |= 0x249;
                }
            if (locType.hasCollision && collisionMap != null)
                collisionMap.setWall(k, j, j1, locType.isSolid, l);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 0, SeqType.animations[locType.animationIndex], j, j1));
            if (locType.thickness != 16)
                scene.method298(i, j, j1, locType.thickness);
            return;
        }
        if (l == 3) {
            Model class38_sub2_sub1_5 = locType.getModel(3, k, k1, l1, i2, j2, -1);
            scene.addWall(0, k2, i, WALL_ROTATION_TYPE2[k], class38_sub2_sub1_5, null, j1, l2, j, byte0);
            if (locType.hasShadow)
                if (k == 0)
                    shadowmap[i][j1][j + 1] = 50;
                else if (k == 1)
                    shadowmap[i][j1 + 1][j + 1] = 50;
                else if (k == 2)
                    shadowmap[i][j1 + 1][j] = 50;
                else if (k == 3)
                    shadowmap[i][j1][j] = 50;
            if (locType.hasCollision && collisionMap != null)
                collisionMap.setWall(k, j, j1, locType.isSolid, l);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 0, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 9) {
            Model class38_sub2_sub1_6 = locType.getModel(l, k, k1, l1, i2, j2, -1);
            scene.addLocation(k2, i, null, l2, j, j1, 1, byte0, class38_sub2_sub1_6, 0, 1);
            if (locType.hasCollision && collisionMap != null)
                collisionMap.setLoc(k, locType.sizeZ, locType.sizeX, j1, j, locType.isSolid);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 2, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 4) {
            Model class38_sub2_sub1_7 = locType.getModel(4, 0, k1, l1, i2, j2, -1);
            scene.addWallDecoration(k2, j, 0, l2, k * 512, WALL_ROTATION_TYPE1[k], 0, j1, class38_sub2_sub1_7, byte0, i);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 1, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 5) {
            int j3 = 16;
            int l3 = scene.getWallBitset(i, j1, j);
            if (l3 > 0)
                j3 = LocType.get(l3 >> 14 & 0x7fff).thickness;
            Model class38_sub2_sub1_13 = locType.getModel(4, 0, k1, l1, i2, j2, -1);
            scene.addWallDecoration(k2, j, WALL_DECO_ROT_SIZE_Y_DIR[k] * j3, l2, k * 512, WALL_ROTATION_TYPE1[k], WALL_DECO_ROT_SIZE_X_DIR[k] * j3,
                    j1, class38_sub2_sub1_13, byte0, i);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 1, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 6) {
            Model class38_sub2_sub1_8 = locType.getModel(4, 0, k1, l1, i2, j2, -1);
            scene.addWallDecoration(k2, j, 0, l2, k, 256, 0, j1, class38_sub2_sub1_8, byte0, i);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 1, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 7) {
            Model class38_sub2_sub1_9 = locType.getModel(4, 0, k1, l1, i2, j2, -1);
            scene.addWallDecoration(k2, j, 0, l2, k, 512, 0, j1, class38_sub2_sub1_9, byte0, i);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 1, SeqType.animations[locType.animationIndex], j, j1));
            return;
        }
        if (l == 8) {
            Model class38_sub2_sub1_10 = locType.getModel(4, 0, k1, l1, i2, j2, -1);
            scene.addWallDecoration(k2, j, 0, l2, k, 768, 0, j1, class38_sub2_sub1_10, byte0, i);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, i1, i, 1, SeqType.animations[locType.animationIndex], j, j1));
        }
    }

    public void buildLandscape(Scene scene, CollisionMap[] planeCollisions) {
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 104; k++) {
                for (int i1 = 0; i1 < 104; i1++)
                    if ((renderFlags[j][k][i1] & 1) == 1) {
                        int l1 = j;
                        if ((renderFlags[1][k][i1] & 2) == 2)
                            l1--;
                        if (l1 >= 0)
                            planeCollisions[l1].setBlocked(i1, k);
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
        for (int l = 0; l < 4; l++) {
            byte[][] abyte0 = shadowmap[l];
            byte byte0 = 96;
            char c = '\u0300';
            byte byte1 = -50;
            byte byte2 = -10;
            byte byte3 = -50;
            int k3 = (int) Math.sqrt(byte1 * byte1 + byte2 * byte2 + byte3 * byte3);
            int i4 = c * k3 >> 8;
            for (int k4 = 1; k4 < tileCountZ - 1; k4++) {
                for (int k5 = 1; k5 < tileCountX - 1; k5++) {
                    int l6 = heightmap[l][k5 + 1][k4] - heightmap[l][k5 - 1][k4];
                    int i8 = heightmap[l][k5][k4 + 1] - heightmap[l][k5][k4 - 1];
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
                        int i13 = planeUnderlayFloorIndices[l][l9][j8] & 0xff;
                        if (i13 > 0) {
                            FloType floType = FloType.instances[i13 - 1];
                            blendedHue[j8] += floType.blendHue;
                            blendedSaturation[j8] += floType.saturation;
                            blendedLightness[j8] += floType.lightness;
                            blendedHueMultiplier[j8] += floType.hsl16;
                            blendDirectionTracker[j8]++;
                        }
                    }
                    int j13 = i7 - 5;
                    if (j13 >= 0 && j13 < tileCountX) {
                        int j14 = planeUnderlayFloorIndices[l][j13][j8] & 0xff;
                        if (j14 > 0) {
                            FloType floType_1 = FloType.instances[j14 - 1];
                            blendedHue[j8] -= floType_1.blendHue;
                            blendedSaturation[j8] -= floType_1.saturation;
                            blendedLightness[j8] -= floType_1.lightness;
                            blendedHueMultiplier[j8] -= floType_1.hsl16;
                            blendDirectionTracker[j8]--;
                        }
                    }
                }

                if (i7 >= 1 && i7 < tileCountX - 1) {
                    int i10 = 0;
                    int k13 = 0;
                    int k14 = 0;
                    int l15 = 0;
                    int l16 = 0;
                    for (int l17 = -5; l17 < tileCountZ + 5; l17++) {
                        int k18 = l17 + 5;
                        if (k18 >= 0 && k18 < tileCountZ) {
                            i10 += blendedHue[k18];
                            k13 += blendedSaturation[k18];
                            k14 += blendedLightness[k18];
                            l15 += blendedHueMultiplier[k18];
                            l16 += blendDirectionTracker[k18];
                        }
                        int l18 = l17 - 5;
                        if (l18 >= 0 && l18 < tileCountZ) {
                            i10 -= blendedHue[l18];
                            k13 -= blendedSaturation[l18];
                            k14 -= blendedLightness[l18];
                            l15 -= blendedHueMultiplier[l18];
                            l16 -= blendDirectionTracker[l18];
                        }
                        if (l17 >= 1 && l17 < tileCountZ - 1
                                && (!lowMemory || (renderFlags[l][i7][l17] & 0x10) == 0
                                && getRenderLevel(l, i7, l17) == levelBuilt)) {
                            int i19 = planeUnderlayFloorIndices[l][i7][l17] & 0xff;
                            int j19 = planeOverlayFloorIndices[l][i7][l17] & 0xff;
                            if (i19 > 0 || j19 > 0) {
                                int k19 = heightmap[l][i7][l17];
                                int l19 = heightmap[l][i7 + 1][l17];
                                int i20 = heightmap[l][i7 + 1][l17 + 1];
                                int j20 = heightmap[l][i7][l17 + 1];
                                int k20 = lightmap[i7][l17];
                                int l20 = lightmap[i7 + 1][l17];
                                int i21 = lightmap[i7 + 1][l17 + 1];
                                int j21 = lightmap[i7][l17 + 1];
                                int k21 = -1;
                                int l21 = -1;
                                if (i19 > 0) {
                                    int i22 = (i10 * 256) / l15;
                                    int k22 = k13 / l16;
                                    int i23 = k14 / l16;
                                    k21 = hsl24To16(i22, k22, i23);
                                    i22 = i22 + rand1 & 0xff;
                                    i23 += rand2;
                                    if (i23 < 0)
                                        i23 = 0;
                                    else if (i23 > 255)
                                        i23 = 255;
                                    l21 = hsl24To16(i22, k22, i23);
                                }
                                if (l > 0) {
                                    boolean flag = i19 != 0 || planeOverlayTypes[l][i7][l17] == 0;
                                    if (j19 > 0 && !FloType.instances[j19 - 1].occlude)
                                        flag = false;
                                    if (flag && k19 == l19 && k19 == i20 && k19 == j20)
                                        occludeFlags[l][i7][l17] |= 0x924;
                                }
                                int j22 = 0;
                                if (k21 != -1)
                                    j22 = Draw3D.anIntArray1458[adjustHSLLightness1(l21, 96)];
                                if (j19 == 0) {
                                    scene.addTile(l, i7, l17, 0, 0, -1, k19, l19, i20, j20, adjustHSLLightness1(k21, k20),
                                            adjustHSLLightness1(k21, l20), adjustHSLLightness1(k21, i21), adjustHSLLightness1(k21, j21), 0, 0, 0, 0,
                                            j22, 0);
                                } else {
                                    int l22 = planeOverlayTypes[l][i7][l17] + 1;
                                    byte byte4 = planeOverlayRotations[l][i7][l17];
                                    FloType floType_2 = FloType.instances[j19 - 1];
                                    int j23 = floType_2.textureIndex;
                                    int k23;
                                    int l23;
                                    if (j23 >= 0) {
                                        l23 = Draw3D.method390(787, j23);
                                        k23 = -1;
                                    } else if (floType_2.rgb == 0xff00ff) {
                                        l23 = 0;
                                        k23 = -2;
                                        j23 = -1;
                                    } else {
                                        k23 = hsl24To16(floType_2.hue, floType_2.saturation, floType_2.lightness);
                                        l23 = Draw3D.anIntArray1458[adjustHSLLightness0(floType_2.blendHueMultiplier, 96)];
                                    }
                                    scene.addTile(l, i7, l17, l22, byte4, j23, k19, l19, i20, j20,
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
                    scene.setPhysicalLevel(l, j10, k8, getRenderLevel(l, j10, k8));

            }

        }

        if (!occlusionEnabled) {
            scene.applyLighting(-10, 64, -50, 768, -50);
        }

        for (int k1 = 0; k1 < tileCountX; k1++) {
            for (int i2 = 0; i2 < tileCountZ; i2++) {
                if ((renderFlags[1][k1][i2] & 2) == 2) {
                    scene.setBridge(i2, k1);
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
                                Scene.addOcclude(i6 * 128 + 128, j4 * 128, i16, 1, j4 * 128, i3, l14, l4 * 128);
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
                                Scene.addOcclude(l3 * 128, i5 * 128, j16, 2, j6 * 128 + 128, i3, i15, l3 * 128);
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
                                Scene.addOcclude(j9 * 128 + 128, j5 * 128, k12, 4, k6 * 128 + 128, i3, k12,
                                        l7 * 128);
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

    public static void addLoc(int i, LinkedList linkedList, CollisionMap collisionMap, int j, int k, int[][][] ai, int i1,
                              int j1, int k1, Scene scene, int l1) {
        int i2 = ai[l1][i][j];
        int j2 = ai[l1][i + 1][j];
        int k2 = ai[l1][i + 1][j + 1];
        int l2 = ai[l1][i][j + 1];
        int i3 = i2 + j2 + k2 + l2 >> 2;
        LocType locType = LocType.get(j1);
        int j3 = i + (j << 7) + (j1 << 14) + 0x40000000;
        if (!locType.interactable)
            j3 += 0x80000000;
        byte byte0 = (byte) ((k << 6) + k1);
        if (k1 == 22) {
            Model class38_sub2_sub1 = locType.getModel(22, k, i2, j2, k2, l2, -1);
            scene.addGroundDecoration(class38_sub2_sub1, i, j3, j, i1, byte0, i3);
            if (locType.hasCollision && locType.interactable)
                collisionMap.setBlocked(j, i);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 3, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 10 || k1 == 11) {
            Model class38_sub2_sub1_1 = locType.getModel(10, k, i2, j2, k2, l2, -1);
            if (class38_sub2_sub1_1 != null) {
                int l4 = 0;
                if (k1 == 11)
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
                scene.addLocation(i3, i1, null, j3, j, i, i4, byte0, class38_sub2_sub1_1, l4, k4);
            }
            if (locType.hasCollision)
                collisionMap.setLoc(k, locType.sizeZ, locType.sizeX, i, j, locType.isSolid);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 2, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 >= 12) {
            Model class38_sub2_sub1_2 = locType.getModel(k1, k, i2, j2, k2, l2, -1);
            scene.addLocation(i3, i1, null, j3, j, i, 1, byte0, class38_sub2_sub1_2, 0, 1);
            if (locType.hasCollision)
                collisionMap.setLoc(k, locType.sizeZ, locType.sizeX, i, j, locType.isSolid);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 2, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 0) {
            Model class38_sub2_sub1_3 = locType.getModel(0, k, i2, j2, k2, l2, -1);
            scene.addWall(0, i3, i1, WALL_ROTATION_TYPE1[k], class38_sub2_sub1_3, null, i, j3, j, byte0);
            if (locType.hasCollision)
                collisionMap.setWall(k, j, i, locType.isSolid, k1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 0, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 1) {
            Model class38_sub2_sub1_4 = locType.getModel(1, k, i2, j2, k2, l2, -1);
            scene.addWall(0, i3, i1, WALL_ROTATION_TYPE2[k], class38_sub2_sub1_4, null, i, j3, j, byte0);
            if (locType.hasCollision)
                collisionMap.setWall(k, j, i, locType.isSolid, k1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 0, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 2) {
            int k3 = k + 1 & 3;
            Model class38_sub2_sub1_11 = locType.getModel(2, 4 + k, i2, j2, k2, l2, -1);
            Model class38_sub2_sub1_12 = locType.getModel(2, k3, i2, j2, k2, l2, -1);
            scene.addWall(WALL_ROTATION_TYPE1[k3], i3, i1, WALL_ROTATION_TYPE1[k], class38_sub2_sub1_11,
                    class38_sub2_sub1_12, i, j3, j, byte0);
            if (locType.hasCollision)
                collisionMap.setWall(k, j, i, locType.isSolid, k1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 0, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 3) {
            Model class38_sub2_sub1_5 = locType.getModel(3, k, i2, j2, k2, l2, -1);
            scene.addWall(0, i3, i1, WALL_ROTATION_TYPE2[k], class38_sub2_sub1_5, null, i, j3, j, byte0);
            if (locType.hasCollision)
                collisionMap.setWall(k, j, i, locType.isSolid, k1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 0, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 9) {
            Model class38_sub2_sub1_6 = locType.getModel(k1, k, i2, j2, k2, l2, -1);
            scene.addLocation(i3, i1, null, j3, j, i, 1, byte0, class38_sub2_sub1_6, 0, 1);
            if (locType.hasCollision)
                collisionMap.setLoc(k, locType.sizeZ, locType.sizeX, i, j, locType.isSolid);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 2, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 4) {
            Model class38_sub2_sub1_7 = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            scene.addWallDecoration(i3, j, 0, j3, k * 512, WALL_ROTATION_TYPE1[k], 0, i, class38_sub2_sub1_7, byte0, i1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 1, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 5) {
            int l3 = 16;
            int j4 = scene.getWallBitset(i1, i, j);
            if (j4 > 0)
                l3 = LocType.get(j4 >> 14 & 0x7fff).thickness;
            Model class38_sub2_sub1_13 = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            scene.addWallDecoration(i3, j, WALL_DECO_ROT_SIZE_Y_DIR[k] * l3, j3, k * 512, WALL_ROTATION_TYPE1[k], WALL_DECO_ROT_SIZE_X_DIR[k] * l3,
                    i, class38_sub2_sub1_13, byte0, i1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 1, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 6) {
            Model class38_sub2_sub1_8 = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            scene.addWallDecoration(i3, j, 0, j3, k, 256, 0, i, class38_sub2_sub1_8, byte0, i1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 1, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 7) {
            Model class38_sub2_sub1_9 = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            scene.addWallDecoration(i3, j, 0, j3, k, 512, 0, i, class38_sub2_sub1_9, byte0, i1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 1, SeqType.animations[locType.animationIndex], j, i));
            return;
        }
        if (k1 == 8) {
            Model class38_sub2_sub1_10 = locType.getModel(4, 0, i2, j2, k2, l2, -1);
            scene.addWallDecoration(i3, j, 0, j3, k, 768, 0, i, class38_sub2_sub1_10, byte0, i1);
            if (locType.animationIndex != -1)
                linkedList.pushNext(new LocEntity(true, j1, i1, 1, SeqType.animations[locType.animationIndex], j, i));
        }
    }

    public static boolean lowMemory = true;
    public static int levelBuilt;
    public static boolean occlusionEnabled;
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

    private static final int[] WALL_ROTATION_TYPE1 = { 0x1, 0x2, 0x4, 0x8 };
    private static final int[] WALL_ROTATION_TYPE2 = { 0x10, 0x20, 0x40, 0x80 };
    private static final int[] WALL_DECO_ROT_SIZE_X_DIR = { 1, 0, -1, 0 };
    private static final int[] WALL_DECO_ROT_SIZE_Y_DIR = { 0, -1, 0, 1 };

    public static int rand1 = (int) (Math.random() * 17D) - 8;
    public static int rand2 = (int) (Math.random() * 33D) - 16;

}
