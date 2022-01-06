package com.runescape;

public class Scene {

    public Scene(int i, int[][][] ai, int j, int k, int l) {
        aBoolean519 = false;
        aByte520 = 6;
        aByte522 = 1;
        locArray1 = new Loc[5000];
        anIntArray576 = new int[10000];
        anIntArray577 = new int[10000];
        anInt527 = k;
        anInt528 = l;
        anInt529 = j;
        tile3dArray = new Tile[k][l][j];
        anIntArrayArrayArray535 = new int[k][l + 1][j + 1];
        i = 27 / i;
        anIntArrayArrayArray530 = ai;
        method281(742);
    }

    public static void method280(boolean flag) {
        locArray2 = null;
        anIntArray563 = null;
        if (!flag)
            aBoolean525 = !aBoolean525;
        occluder2dArray = null;
        linkedList = null;
        aBooleanArrayArrayArrayArray581 = null;
        aBooleanArrayArray582 = null;
    }

    public void method281(int i) {
        for (int j = 0; j < anInt527; j++) {
            for (int k = 0; k < anInt528; k++) {
                for (int i1 = 0; i1 < anInt529; i1++)
                    tile3dArray[j][k][i1] = null;

            }

        }

        i = 38 / i;
        for (int l = 0; l < anInt562; l++) {
            for (int j1 = 0; j1 < anIntArray563[l]; j1++)
                occluder2dArray[l][j1] = null;

            anIntArray563[l] = 0;
        }

        for (int k1 = 0; k1 < anInt533; k1++)
            locArray1[k1] = null;

        anInt533 = 0;
        for (int l1 = 0; l1 < locArray2.length; l1++)
            locArray2[l1] = null;

    }

    public void method282(int i, int j) {
        anInt532 = j;
        for (int k = 0; k < anInt528; k++) {
            for (int l = 0; l < anInt529; l++)
                tile3dArray[j][k][l] = new Tile(j, k, l);

        }

        if (i != 0) {
            for (int i1 = 1; i1 > 0; i1++)
                ;
        }
    }

    public void method283(int i, int j, byte byte0) {
        Tile tile = tile3dArray[0][j][i];
        for (int k = 0; k < 3; k++) {
            tile3dArray[k][j][i] = tile3dArray[k + 1][j][i];
            if (tile3dArray[k][j][i] != null)
                tile3dArray[k][j][i].level--;
        }

        if (tile3dArray[0][j][i] == null)
            tile3dArray[0][j][i] = new Tile(0, j, i);
        tile3dArray[0][j][i].bridge = tile;
        tile3dArray[3][j][i] = null;
        if (byte0 != -41) {
            for (int l = 1; l > 0; l++)
                ;
        }
    }

    public static void method284(int i, int j, int k, int l, int i1, int j1, int k1, int l1,
                                 int i2) {
        Occluder occluder = new Occluder();
        occluder.minTileX = j / 128;
        occluder.maxTileX = j1 / 128;
        occluder.minTileZ = i2 / 128;
        occluder.maxTileZ = i / 128;
        occluder.type = i1;
        occluder.minX = j;
        occluder.maxX = j1;
        occluder.minZ = i2;
        while (k >= 0)
            anInt524 = 127;
        occluder.maxZ = i;
        occluder.minY = l1;
        occluder.maxY = l;
        occluder2dArray[k1][anIntArray563[k1]++] = occluder;
    }

    public void method285(int i, int j, int k, int l) {
        Tile tile = tile3dArray[i][j][k];
        if (tile == null) {
            return;
        } else {
            tile3dArray[i][j][k].physicalLevel = l;
            return;
        }
    }

    public void method286(int i, int j, int k, int l, int i1, int j1, int k1,
                          int l1, int i2, int j2, int k2, int l2, int i3, int j3,
                          int k3, int l3, int i4, int j4, int k4, int l4) {
        if (l == 0) {
            TileUnderlay tileUnderlay = new TileUnderlay(k2, l2, i3, j3, -1, k4, false);
            for (int i5 = i; i5 >= 0; i5--)
                if (tile3dArray[i5][j][k] == null)
                    tile3dArray[i5][j][k] = new Tile(i5, j, k);

            tile3dArray[i][j][k].underlay = tileUnderlay;
            return;
        }
        if (l == 1) {
            TileUnderlay tileUnderlay_1 = new TileUnderlay(k3, l3, i4, j4, j1, l4, k1 == l1 && k1 == i2 && k1 == j2);
            for (int j5 = i; j5 >= 0; j5--)
                if (tile3dArray[j5][j][k] == null)
                    tile3dArray[j5][j][k] = new Tile(j5, j, k);

            tile3dArray[i][j][k].underlay = tileUnderlay_1;
            return;
        }
        Class15 class15 = new Class15(j, l, l3, l1, i3, i1, k2, j2, l4, k3, j1, j4, k4, i2, i4, j3, 10659, k1, k, l2);
        for (int k5 = i; k5 >= 0; k5--)
            if (tile3dArray[k5][j][k] == null)
                tile3dArray[k5][j][k] = new Tile(k5, j, k);

        tile3dArray[i][j][k].overlay = class15;
    }

    public void method287(Model class38_sub2_sub1, byte byte0, int i, int j, int k, int l, byte byte1,
                          int i1) {
        GroundDecoration groundDecoration = new GroundDecoration();
        groundDecoration.model = class38_sub2_sub1;
        groundDecoration.sceneX = i * 128 + 64;
        groundDecoration.sceneZ = k * 128 + 64;
        groundDecoration.sceneY = i1;
        groundDecoration.bitset = j;
        groundDecoration.info = byte1;
        if (tile3dArray[l][i][k] == null)
            tile3dArray[l][i][k] = new Tile(l, i, k);
        tile3dArray[l][i][k].groundDecoration = groundDecoration;
        if (byte0 == aByte520) {
            byte0 = 0;
            return;
        } else {
            aBoolean525 = !aBoolean525;
            return;
        }
    }

    public void method288(Model class38_sub2_sub1, Model class38_sub2_sub1_1, int i, int j,
                          int k, int l, int i1,
                          Model class38_sub2_sub1_2, int j1) {
        ObjEntity objEntity = new ObjEntity();
        objEntity.entity0 = class38_sub2_sub1;
        j1 = 90 / j1;
        objEntity.x = i1 * 128 + 64;
        objEntity.z = l * 128 + 64;
        objEntity.y = i;
        objEntity.bitset = k;
        objEntity.entity1 = class38_sub2_sub1_1;
        objEntity.entity2 = class38_sub2_sub1_2;
        int k1 = 0;
        Tile tile = tile3dArray[j][i1][l];
        if (tile != null) {
            for (int l1 = 0; l1 < tile.locationCount; l1++) {
                int i2 = tile.locs[l1].model.anInt1251;
                if (i2 > k1)
                    k1 = i2;
            }

        }
        objEntity.offsetY = k1;
        if (tile3dArray[j][i1][l] == null)
            tile3dArray[j][i1][l] = new Tile(j, i1, l);
        tile3dArray[j][i1][l].objEntity = objEntity;
    }

    public void method289(int i, int j, int k, int l, int i1, Model class38_sub2_sub1,
                          Model class38_sub2_sub1_1,
                          int j1, int k1, int l1, byte byte0) {
        if (class38_sub2_sub1 == null && class38_sub2_sub1_1 == null)
            return;
        Wall wall = new Wall();
        wall.bitset = k1;
        wall.info = byte0;
        wall.x = j1 * 128 + 64;
        wall.z = l1 * 128 + 64;
        wall.y = j;
        wall.entity0 = class38_sub2_sub1;
        if (i1 != 8)
            aBoolean525 = !aBoolean525;
        wall.entity1 = class38_sub2_sub1_1;
        wall.type0 = l;
        wall.type1 = i;
        for (int i2 = k; i2 >= 0; i2--)
            if (tile3dArray[i2][j1][l1] == null)
                tile3dArray[i2][j1][l1] = new Tile(i2, j1, l1);

        tile3dArray[k][j1][l1].wall = wall;
    }

    public void method290(int i, int j, int k, int l, int i1, int j1, int k1,
                          int l1, int i2, Model class38_sub2_sub1, byte byte0, int j2) {
        k1 = 66 / k1;
        if (class38_sub2_sub1 == null)
            return;
        WallDecoration wallDecoration = new WallDecoration();
        wallDecoration.bitset = l;
        wallDecoration.info = byte0;
        wallDecoration.sceneX = i2 * 128 + 64 + l1;
        wallDecoration.sceneZ = j * 128 + 64 + k;
        wallDecoration.sceneY = i;
        wallDecoration.model = class38_sub2_sub1;
        wallDecoration.type = j1;
        wallDecoration.rotation = i1;
        for (int k2 = j2; k2 >= 0; k2--)
            if (tile3dArray[k2][i2][j] == null)
                tile3dArray[k2][i2][j] = new Tile(k2, i2, j);

        tile3dArray[j2][i2][j].wallDecoration = wallDecoration;
    }

    public boolean method291(int i, int j, int k, Entity entity, int l, int i1, int j1,
                             int k1, byte byte0, Model class38_sub2_sub1, int l1, int i2) {
        j = 79 / j;
        if (class38_sub2_sub1 == null && entity == null) {
            return true;
        } else {
            int j2 = j1 * 128 + 64 * k1;
            int k2 = i1 * 128 + 64 * i2;
            return method294(k, j1, i1, k1, i2, j2, k2, i, class38_sub2_sub1, entity, l1, false, l, byte0);
        }
    }

    public boolean method292(int i, int j, int k, int l, int i1, int j1, boolean flag,
                             Model class38_sub2_sub1, Entity entity, int k1, int l1) {
        if (class38_sub2_sub1 == null && entity == null)
            return true;
        int i2 = i1 - k;
        int j2 = j - k;
        int k2 = i1 + k;
        int l2 = j + k;
        if (flag) {
            if (l > 640 && l < 1408)
                l2 += 128;
            if (l > 1152 && l < 1920)
                k2 += 128;
            if (l > 1664 || l < 384)
                j2 -= 128;
            if (l > 128 && l < 896)
                i2 -= 128;
        }
        i2 /= 128;
        if (i != -44713)
            aBoolean525 = !aBoolean525;
        j2 /= 128;
        k2 /= 128;
        l2 /= 128;
        return method294(l1, i2, j2, (k2 - i2) + 1, (l2 - j2) + 1, i1, j, k1, class38_sub2_sub1, entity, l, true,
                j1, (byte) 0);
    }

    public boolean method293(int i, int j, Model class38_sub2_sub1, int k, int l, int i1, int j1,
                             int k1, int l1, Entity entity, boolean flag, int i2, int j2, int k2) {
        if (flag) {
            for (int l2 = 1; l2 > 0; l2++)
                ;
        }
        if (class38_sub2_sub1 == null && entity == null)
            return true;
        else
            return method294(i2, l1, k1, (i - l1) + 1, (j2 - k1) + 1, k2, k, l, class38_sub2_sub1, entity, j1,
                    true, i1, (byte) 0);
    }

    public boolean method294(int i, int j, int k, int l, int i1, int j1, int k1,
                             int l1, Model class38_sub2_sub1, Entity entity, int i2, boolean flag, int j2,
                             byte byte0) {
        if (class38_sub2_sub1 == null && entity == null)
            return false;
        for (int k2 = j; k2 < j + l; k2++) {
            for (int l2 = k; l2 < k + i1; l2++) {
                if (k2 < 0 || l2 < 0 || k2 >= anInt528 || l2 >= anInt529)
                    return false;
                Tile tile = tile3dArray[i][k2][l2];
                if (tile != null && tile.locationCount >= 5)
                    return false;
            }

        }

        Loc loc = new Loc();
        loc.bitset = j2;
        loc.info = byte0;
        loc.plane = i;
        loc.x = j1;
        loc.z = k1;
        loc.y = l1;
        loc.model = class38_sub2_sub1;
        loc.entity = entity;
        loc.yaw = i2;
        loc.minSceneTileX = j;
        loc.minSceneTileZ = k;
        loc.maxSceneTileX = (j + l) - 1;
        loc.maxSceneTileZ = (k + i1) - 1;
        for (int i3 = j; i3 < j + l; i3++) {
            for (int j3 = k; j3 < k + i1; j3++) {
                int k3 = 0;
                if (i3 > j)
                    k3++;
                if (i3 < (j + l) - 1)
                    k3 += 4;
                if (j3 > k)
                    k3 += 8;
                if (j3 < (k + i1) - 1)
                    k3 += 2;
                for (int l3 = i; l3 >= 0; l3--)
                    if (tile3dArray[l3][i3][j3] == null)
                        tile3dArray[l3][i3][j3] = new Tile(l3, i3, j3);

                Tile tile = tile3dArray[i][i3][j3];
                tile.locs[tile.locationCount] = loc;
                tile.locFlags[tile.locationCount] = k3;
                tile.flags |= k3;
                tile.locationCount++;
            }

        }

        if (flag)
            locArray1[anInt533++] = loc;
        return true;
    }

    public void method295(int i) {
        for (int j = 0; j < anInt533; j++) {
            Loc loc = locArray1[j];
            method296(loc, (byte) 1);
            locArray1[j] = null;
        }

        if (i != 0)
            aBoolean519 = !aBoolean519;
        anInt533 = 0;
    }

    public void method296(Loc loc, byte byte0) {
        for (int i = loc.minSceneTileX; i <= loc.maxSceneTileX; i++) {
            for (int j = loc.minSceneTileZ; j <= loc.maxSceneTileZ; j++) {
                Tile tile = tile3dArray[loc.plane][i][j];
                if (tile != null) {
                    for (int k = 0; k < tile.locationCount; k++) {
                        if (tile.locs[k] != loc)
                            continue;
                        tile.locationCount--;
                        for (int l = k; l < tile.locationCount; l++) {
                            tile.locs[l] = tile.locs[l + 1];
                            tile.locFlags[l] = tile.locFlags[l + 1];
                        }

                        tile.locs[tile.locationCount] = null;
                        break;
                    }

                    tile.flags = 0;
                    for (int i1 = 0; i1 < tile.locationCount; i1++)
                        tile.flags |= tile.locFlags[i1];

                }
            }

        }

        if (byte0 == aByte522)
            byte0 = 0;
    }

    public void method297(int i, Model class38_sub2_sub1, int j, int k, int l) {
        if (class38_sub2_sub1 == null)
            return;
        Tile tile = tile3dArray[k][i][l];
        if (j < 1 || j > 1) {
            for (int i1 = 1; i1 > 0; i1++)
                ;
        }
        if (tile == null)
            return;
        for (int j1 = 0; j1 < tile.locationCount; j1++) {
            Loc loc = tile.locs[j1];
            if ((loc.bitset >> 29 & 3) == 2) {
                loc.model = class38_sub2_sub1;
                return;
            }
        }

    }

    public void method298(int i, int j, int k, int l, byte byte0) {
        Tile tile = tile3dArray[i][k][j];
        if (byte0 != 6) {
            for (int i1 = 1; i1 > 0; i1++)
                ;
        }
        if (tile == null)
            return;
        WallDecoration wallDecoration = tile.wallDecoration;
        if (wallDecoration == null) {
            return;
        } else {
            int j1 = k * 128 + 64;
            int k1 = j * 128 + 64;
            wallDecoration.sceneX = j1 + ((wallDecoration.sceneX - j1) * l) / 16;
            wallDecoration.sceneZ = k1 + ((wallDecoration.sceneZ - k1) * l) / 16;
            return;
        }
    }

    public void method299(int i, int j, int k, Model class38_sub2_sub1, int l) {
        i = 34 / i;
        if (class38_sub2_sub1 == null)
            return;
        Tile tile = tile3dArray[l][k][j];
        if (tile == null)
            return;
        WallDecoration wallDecoration = tile.wallDecoration;
        if (wallDecoration == null) {
            return;
        } else {
            wallDecoration.model = class38_sub2_sub1;
            return;
        }
    }

    public void method300(Model class38_sub2_sub1, int i, int j, int k, int l) {
        if (j != -48639)
            return;
        if (class38_sub2_sub1 == null)
            return;
        Tile tile = tile3dArray[l][k][i];
        if (tile == null)
            return;
        GroundDecoration groundDecoration = tile.groundDecoration;
        if (groundDecoration == null) {
            return;
        } else {
            groundDecoration.model = class38_sub2_sub1;
            return;
        }
    }

    public void method301(int i, Model class38_sub2_sub1, int j, int k, int l) {
        if (i != 35568) {
            for (int i1 = 1; i1 > 0; i1++)
                ;
        }
        if (class38_sub2_sub1 == null)
            return;
        Tile tile = tile3dArray[l][k][j];
        if (tile == null)
            return;
        Wall wall = tile.wall;
        if (wall == null) {
            return;
        } else {
            wall.entity0 = class38_sub2_sub1;
            return;
        }
    }

    public void method302(Model class38_sub2_sub1, Model class38_sub2_sub1_1, int i,
                          boolean flag, int j, int k) {
        if (class38_sub2_sub1 == null)
            return;
        Tile tile = tile3dArray[k][j][i];
        if (tile == null)
            return;
        Wall wall = tile.wall;
        if (wall == null)
            return;
        wall.entity0 = class38_sub2_sub1;
        wall.entity1 = class38_sub2_sub1_1;
        if (flag)
            aBoolean519 = !aBoolean519;
    }

    public void method303(int i, int j, int k, int l) {
        Tile tile = tile3dArray[j][i][k];
        if (l != 1)
            return;
        if (tile == null) {
            return;
        } else {
            tile.wall = null;
            return;
        }
    }

    public void method304(int i, int j, int k, int l) {
        Tile tile = tile3dArray[i][l][j];
        if (k < 0 || k > 0)
            aBoolean519 = !aBoolean519;
        if (tile == null) {
            return;
        } else {
            tile.wallDecoration = null;
            return;
        }
    }

    public void method305(int i, int j, int k, int l) {
        Tile tile = tile3dArray[l][i][j];
        if (tile == null)
            return;
        for (int i1 = 0; i1 < tile.locationCount; i1++) {
            Loc loc = tile.locs[i1];
            if ((loc.bitset >> 29 & 3) == 2 && loc.minSceneTileX == i && loc.minSceneTileZ == j) {
                method296(loc, (byte) 1);
                return;
            }
        }

        if (k >= 0) {
            for (int j1 = 1; j1 > 0; j1++)
                ;
        }
    }

    public void method306(int i, int j, int k, int l) {
        if (j != 0)
            aBoolean525 = !aBoolean525;
        Tile tile = tile3dArray[i][k][l];
        if (tile == null) {
            return;
        } else {
            tile.groundDecoration = null;
            return;
        }
    }

    public void method307(int i, int j, int k) {
        Tile tile = tile3dArray[i][j][k];
        if (tile == null) {
            return;
        } else {
            tile.objEntity = null;
            return;
        }
    }

    public int method308(int i, int j, int k) {
        Tile tile = tile3dArray[i][j][k];
        if (tile == null || tile.wall == null)
            return 0;
        else
            return tile.wall.bitset;
    }

    public int method309(int i, int j, int k, int l) {
        if (k != 3)
            aBoolean519 = !aBoolean519;
        Tile tile = tile3dArray[i][l][j];
        if (tile == null || tile.wallDecoration == null)
            return 0;
        else
            return tile.wallDecoration.bitset;
    }

    public int method310(int i, int j, int k) {
        Tile tile = tile3dArray[i][j][k];
        if (tile == null)
            return 0;
        for (int l = 0; l < tile.locationCount; l++) {
            Loc loc = tile.locs[l];
            if ((loc.bitset >> 29 & 3) == 2 && loc.minSceneTileX == j && loc.minSceneTileZ == k)
                return loc.bitset;
        }

        return 0;
    }

    public int method311(int i, int j, int k) {
        Tile tile = tile3dArray[i][j][k];
        if (tile == null || tile.groundDecoration == null)
            return 0;
        else
            return tile.groundDecoration.bitset;
    }

    public int method312(int i, int j, int k, int l) {
        Tile tile = tile3dArray[i][j][k];
        if (tile == null)
            return -1;
        if (tile.wall != null && tile.wall.bitset == l)
            return tile.wall.info & 0xff;
        if (tile.wallDecoration != null && tile.wallDecoration.bitset == l)
            return tile.wallDecoration.info & 0xff;
        if (tile.groundDecoration != null && tile.groundDecoration.bitset == l)
            return tile.groundDecoration.info & 0xff;
        for (int i1 = 0; i1 < tile.locationCount; i1++)
            if (tile.locs[i1].bitset == l)
                return tile.locs[i1].info & 0xff;

        return -1;
    }

    public void method313(int i, int j, int k, int l, int i1, boolean flag) {
        int j1 = (int) Math.sqrt(k * k + i * i + i1 * i1);
        if (flag)
            anInt523 = -77;
        int k1 = l * j1 >> 8;
        for (int l1 = 0; l1 < anInt527; l1++) {
            for (int i2 = 0; i2 < anInt528; i2++) {
                for (int j2 = 0; j2 < anInt529; j2++) {
                    Tile tile = tile3dArray[l1][i2][j2];
                    if (tile != null) {
                        Wall wall = tile.wall;
                        if (wall != null && wall.entity0 != null
                                && wall.entity0.vertexNormalArray1 != null) {
                            method315(i2, 1, 1, l1, 872, wall.entity0, j2);
                            if (wall.entity1 != null
                                    && wall.entity1.vertexNormalArray1 != null) {
                                method315(i2, 1, 1, l1, 872, wall.entity1, j2);
                                method316(wall.entity0, wall.entity1, 0, 0, 0,
                                        false);
                                wall.entity1.method368(j, k1, k, i, i1);
                            }
                            wall.entity0.method368(j, k1, k, i, i1);
                        }
                        for (int k2 = 0; k2 < tile.locationCount; k2++) {
                            Loc loc = tile.locs[k2];
                            if (loc != null && loc.model != null
                                    && loc.model.vertexNormalArray1 != null) {
                                method315(i2, (loc.maxSceneTileX - loc.minSceneTileX) + 1,
                                        (loc.maxSceneTileZ - loc.minSceneTileZ) + 1, l1, 872,
                                        loc.model, j2);
                                loc.model.method368(j, k1, k, i, i1);
                            }
                        }

                        GroundDecoration groundDecoration = tile.groundDecoration;
                        if (groundDecoration != null && groundDecoration.model.vertexNormalArray1 != null) {
                            method314((byte) -70, l1, j2, groundDecoration.model, i2);
                            groundDecoration.model.method368(j, k1, k, i, i1);
                        }
                    }
                }

            }

        }

    }

    public void method314(byte byte0, int i, int j, Model class38_sub2_sub1, int k) {
        if (byte0 != -70)
            anInt523 = -417;
        if (k < anInt528) {
            Tile tile = tile3dArray[i][k + 1][j];
            if (tile != null && tile.groundDecoration != null
                    && tile.groundDecoration.model.vertexNormalArray1 != null)
                method316(class38_sub2_sub1, tile.groundDecoration.model, 128, 0, 0, true);
        }
        if (j < anInt528) {
            Tile tile = tile3dArray[i][k][j + 1];
            if (tile != null && tile.groundDecoration != null
                    && tile.groundDecoration.model.vertexNormalArray1 != null)
                method316(class38_sub2_sub1, tile.groundDecoration.model, 0, 0, 128, true);
        }
        if (k < anInt528 && j < anInt529) {
            Tile tile_2 = tile3dArray[i][k + 1][j + 1];
            if (tile_2 != null && tile_2.groundDecoration != null
                    && tile_2.groundDecoration.model.vertexNormalArray1 != null)
                method316(class38_sub2_sub1, tile_2.groundDecoration.model, 128, 0, 128, true);
        }
        if (k < anInt528 && j > 0) {
            Tile tile_3 = tile3dArray[i][k + 1][j - 1];
            if (tile_3 != null && tile_3.groundDecoration != null
                    && tile_3.groundDecoration.model.vertexNormalArray1 != null)
                method316(class38_sub2_sub1, tile_3.groundDecoration.model, 128, 0, -128, true);
        }
    }

    public void method315(int i, int j, int k, int l, int i1, Model class38_sub2_sub1, int j1) {
        i1 = 21 / i1;
        boolean flag = true;
        int k1 = i;
        int l1 = i + j;
        int i2 = j1 - 1;
        int j2 = j1 + k;
        for (int k2 = l; k2 <= l + 1; k2++)
            if (k2 != anInt527) {
                for (int l2 = k1; l2 <= l1; l2++)
                    if (l2 >= 0 && l2 < anInt528) {
                        for (int i3 = i2; i3 <= j2; i3++)
                            if (i3 >= 0 && i3 < anInt529 && (!flag || l2 >= l1 || i3 >= j2 || i3 < j1 && l2 != i)) {
                                Tile tile = tile3dArray[k2][l2][i3];
                                if (tile != null) {
                                    int j3 = (anIntArrayArrayArray530[k2][l2][i3]
                                            + anIntArrayArrayArray530[k2][l2 + 1][i3]
                                            + anIntArrayArrayArray530[k2][l2][i3 + 1]
                                            + anIntArrayArrayArray530[k2][l2 + 1][i3 + 1]) / 4
                                            - (anIntArrayArrayArray530[l][i][j1] + anIntArrayArrayArray530[l][i + 1][j1]
                                            + anIntArrayArrayArray530[l][i][j1 + 1]
                                            + anIntArrayArrayArray530[l][i + 1][j1 + 1]) / 4;
                                    Wall wall = tile.wall;
                                    if (wall != null && wall.entity0 != null
                                            && wall.entity0.vertexNormalArray1 != null)
                                        method316(class38_sub2_sub1, wall.entity0,
                                                (l2 - i) * 128 + (1 - j) * 64, j3, (i3 - j1) * 128 + (1 - k) * 64,
                                                flag);
                                    if (wall != null && wall.entity1 != null
                                            && wall.entity1.vertexNormalArray1 != null)
                                        method316(class38_sub2_sub1, wall.entity1,
                                                (l2 - i) * 128 + (1 - j) * 64, j3, (i3 - j1) * 128 + (1 - k) * 64,
                                                flag);
                                    for (int k3 = 0; k3 < tile.locationCount; k3++) {
                                        Loc loc = tile.locs[k3];
                                        if (loc != null && loc.model != null
                                                && loc.model.vertexNormalArray1 != null) {
                                            int l3 = (loc.maxSceneTileX - loc.minSceneTileX) + 1;
                                            int i4 = (loc.maxSceneTileZ - loc.minSceneTileZ) + 1;
                                            method316(class38_sub2_sub1, loc.model,
                                                    (loc.minSceneTileX - i) * 128 + (l3 - j) * 64, j3,
                                                    (loc.minSceneTileZ - j1) * 128 + (i4 - k) * 64, flag);
                                        }
                                    }

                                }
                            }

                    }

                k1--;
                flag = false;
            }

    }

    public void method316(Model class38_sub2_sub1, Model class38_sub2_sub1_1, int i, int j,
                          int k, boolean flag) {
        anInt578++;
        int l = 0;
        int[] ai = class38_sub2_sub1_1.anIntArray1223;
        int i1 = class38_sub2_sub1_1.anInt1222;
        for (int j1 = 0; j1 < class38_sub2_sub1.anInt1222; j1++) {
            VertexNormal vertexNormal = class38_sub2_sub1.vertexNormalArray1[j1];
            VertexNormal vertexNormal_1 = class38_sub2_sub1.vertexNormalArray2[j1];
            if (vertexNormal_1.w != 0) {
                int i2 = class38_sub2_sub1.anIntArray1224[j1] - j;
                if (i2 <= class38_sub2_sub1_1.anInt1248) {
                    int j2 = class38_sub2_sub1.anIntArray1223[j1] - i;
                    if (j2 >= class38_sub2_sub1_1.anInt1242 && j2 <= class38_sub2_sub1_1.anInt1243) {
                        int k2 = class38_sub2_sub1.anIntArray1225[j1] - k;
                        if (k2 >= class38_sub2_sub1_1.anInt1245 && k2 <= class38_sub2_sub1_1.anInt1244) {
                            for (int l2 = 0; l2 < i1; l2++) {
                                VertexNormal vertexNormal_2 = class38_sub2_sub1_1.vertexNormalArray1[l2];
                                VertexNormal vertexNormal_3 = class38_sub2_sub1_1.vertexNormalArray2[l2];
                                if (j2 == ai[l2] && k2 == class38_sub2_sub1_1.anIntArray1225[l2]
                                        && i2 == class38_sub2_sub1_1.anIntArray1224[l2] && vertexNormal_3.w != 0) {
                                    vertexNormal.x += vertexNormal_3.x;
                                    vertexNormal.y += vertexNormal_3.y;
                                    vertexNormal.z += vertexNormal_3.z;
                                    vertexNormal.w += vertexNormal_3.w;
                                    vertexNormal_2.x += vertexNormal_1.x;
                                    vertexNormal_2.y += vertexNormal_1.y;
                                    vertexNormal_2.z += vertexNormal_1.z;
                                    vertexNormal_2.w += vertexNormal_1.w;
                                    l++;
                                    anIntArray576[j1] = anInt578;
                                    anIntArray577[l2] = anInt578;
                                }
                            }

                        }
                    }
                }
            }
        }

        if (l < 3 || !flag)
            return;
        for (int k1 = 0; k1 < class38_sub2_sub1.anInt1226; k1++)
            if (anIntArray576[class38_sub2_sub1.anIntArray1227[k1]] == anInt578
                    && anIntArray576[class38_sub2_sub1.anIntArray1228[k1]] == anInt578
                    && anIntArray576[class38_sub2_sub1.anIntArray1229[k1]] == anInt578)
                class38_sub2_sub1.anIntArray1233[k1] = -1;

        for (int l1 = 0; l1 < class38_sub2_sub1_1.anInt1226; l1++)
            if (anIntArray577[class38_sub2_sub1_1.anIntArray1227[l1]] == anInt578
                    && anIntArray577[class38_sub2_sub1_1.anIntArray1228[l1]] == anInt578
                    && anIntArray577[class38_sub2_sub1_1.anIntArray1229[l1]] == anInt578)
                class38_sub2_sub1_1.anIntArray1233[l1] = -1;

    }

    public void method317(int[] ai, int i, int j, int k, int l, int i1) {
        Tile tile = tile3dArray[k][l][i1];
        if (tile == null)
            return;
        TileUnderlay tileUnderlay = tile.underlay;
        if (tileUnderlay != null) {
            int j1 = tileUnderlay.color;
            if (j1 == 0)
                return;
            for (int k1 = 0; k1 < 4; k1++) {
                ai[i] = j1;
                ai[i + 1] = j1;
                ai[i + 2] = j1;
                ai[i + 3] = j1;
                i += j;
            }

            return;
        }
        Class15 class15 = tile.overlay;
        if (class15 == null)
            return;
        int l1 = class15.anInt334;
        int i2 = class15.anInt335;
        int j2 = class15.anInt336;
        int k2 = class15.anInt337;
        int[] ai1 = anIntArrayArray579[l1];
        int[] ai2 = anIntArrayArray580[i2];
        int l2 = 0;
        if (j2 != 0) {
            for (int i3 = 0; i3 < 4; i3++) {
                ai[i] = ai1[ai2[l2++]] != 0 ? k2 : j2;
                ai[i + 1] = ai1[ai2[l2++]] != 0 ? k2 : j2;
                ai[i + 2] = ai1[ai2[l2++]] != 0 ? k2 : j2;
                ai[i + 3] = ai1[ai2[l2++]] != 0 ? k2 : j2;
                i += j;
            }

            return;
        }
        for (int j3 = 0; j3 < 4; j3++) {
            if (ai1[ai2[l2++]] != 0)
                ai[i] = k2;
            if (ai1[ai2[l2++]] != 0)
                ai[i + 1] = k2;
            if (ai1[ai2[l2++]] != 0)
                ai[i + 2] = k2;
            if (ai1[ai2[l2++]] != 0)
                ai[i + 3] = k2;
            i += j;
        }

    }

    public static void method318(int[] ai, int i, int j, byte byte0, int k, int l) {
        anInt585 = 0;
        anInt586 = 0;
        anInt587 = j;
        anInt588 = k;
        anInt583 = j / 2;
        if (byte0 != 15)
            anInt523 = 29;
        anInt584 = k / 2;
        boolean[][][][] aflag = new boolean[9][32][53][53];
        for (int i1 = 128; i1 <= 384; i1 += 32) {
            for (int j1 = 0; j1 < 2048; j1 += 64) {
                anInt548 = Model.anIntArray1300[i1];
                anInt549 = Model.anIntArray1301[i1];
                anInt550 = Model.anIntArray1300[j1];
                anInt551 = Model.anIntArray1301[j1];
                int l1 = (i1 - 128) / 32;
                int j2 = j1 / 64;
                for (int l2 = -26; l2 <= 26; l2++) {
                    for (int j3 = -26; j3 <= 26; j3++) {
                        int k3 = l2 * 128;
                        int i4 = j3 * 128;
                        boolean flag1 = false;
                        for (int k4 = -l; k4 <= i; k4 += 128) {
                            if (!method319(k3, i4, ai[l1] + k4, -268))
                                continue;
                            flag1 = true;
                            break;
                        }

                        aflag[l1][j2][l2 + 25 + 1][j3 + 25 + 1] = flag1;
                    }

                }

            }

        }

        for (int k1 = 0; k1 < 8; k1++) {
            for (int i2 = 0; i2 < 32; i2++) {
                for (int k2 = -25; k2 < 25; k2++) {
                    for (int i3 = -25; i3 < 25; i3++) {
                        boolean flag = false;
                        label0:
                        for (int l3 = -1; l3 <= 1; l3++) {
                            for (int j4 = -1; j4 <= 1; j4++) {
                                if (aflag[k1][i2][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1])
                                    flag = true;
                                else if (aflag[k1][(i2 + 1) % 31][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1])
                                    flag = true;
                                else if (aflag[k1 + 1][i2][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1]) {
                                    flag = true;
                                } else {
                                    if (!aflag[k1 + 1][(i2 + 1) % 31][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1])
                                        continue;
                                    flag = true;
                                }
                                break label0;
                            }

                        }

                        aBooleanArrayArrayArrayArray581[k1][i2][k2 + 25][i3 + 25] = flag;
                    }

                }

            }

        }

    }

    public static boolean method319(int i, int j, int k, int l) {
        int i1 = j * anInt550 + i * anInt551 >> 16;
        int j1 = j * anInt551 - i * anInt550 >> 16;
        int k1 = k * anInt548 + j1 * anInt549 >> 16;
        int l1 = k * anInt549 - j1 * anInt548 >> 16;
        while (l >= 0) {
            for (int i2 = 1; i2 > 0; i2++)
                ;
        }
        if (k1 < 50 || k1 > 3500)
            return false;
        int j2 = anInt583 + (i1 << 9) / k1;
        int k2 = anInt584 + (l1 << 9) / k1;
        return j2 >= anInt585 && j2 <= anInt587 && k2 >= anInt586 && k2 <= anInt588;
    }

    public void method320(int i, int j, int k) {
        aBoolean557 = true;
        anInt558 = k;
        if (i < 4 || i > 4) {
            return;
        } else {
            anInt559 = j;
            anInt560 = -1;
            anInt561 = -1;
            return;
        }
    }

    public void method321(int i, int j, int k, int l, int i1, int j1, int k1) {
        if (j < 0)
            j = 0;
        else if (j >= anInt528 * 128)
            j = anInt528 * 128 - 1;
        if (j1 < 0)
            j1 = 0;
        else if (j1 >= anInt529 * 128)
            j1 = anInt529 * 128 - 1;
        anInt538++;
        anInt548 = Model.anIntArray1300[l];
        anInt549 = Model.anIntArray1301[l];
        anInt550 = Model.anIntArray1300[i];
        anInt551 = Model.anIntArray1301[i];
        aBooleanArrayArray582 = aBooleanArrayArrayArrayArray581[(l - 128) / 32][i / 64];
        anInt545 = j;
        anInt546 = i1;
        anInt547 = j1;
        anInt543 = j / 128;
        anInt544 = j1 / 128;
        anInt537 = k;
        anInt539 = anInt543 - 25;
        if (anInt539 < 0)
            anInt539 = 0;
        anInt541 = anInt544 - 25;
        if (anInt541 < 0)
            anInt541 = 0;
        anInt540 = anInt543 + 25;
        if (k1 != 0)
            anInt521 = -462;
        if (anInt540 > anInt528)
            anInt540 = anInt528;
        anInt542 = anInt544 + 25;
        if (anInt542 > anInt529)
            anInt542 = anInt529;
        method327(false);
        anInt536 = 0;
        for (int l1 = anInt532; l1 < anInt527; l1++) {
            Tile[][] aclass38_sub1 = tile3dArray[l1];
            for (int j2 = anInt539; j2 < anInt540; j2++) {
                for (int l2 = anInt541; l2 < anInt542; l2++) {
                    Tile tile = aclass38_sub1[j2][l2];
                    if (tile != null)
                        if (tile.physicalLevel > k
                                || !aBooleanArrayArray582[(j2 - anInt543) + 25][(l2 - anInt544) + 25]
                                && anIntArrayArrayArray530[l1][j2][l2] - i1 < 2000) {
                            tile.draw = false;
                            tile.isVisible = false;
                            tile.wallCullDirection = 0;
                        } else {
                            tile.draw = true;
                            tile.isVisible = true;
                            tile.drawLocations = tile.locationCount > 0;
                            anInt536++;
                        }
                }

            }

        }

        for (int i2 = anInt532; i2 < anInt527; i2++) {
            Tile[][] aclass38_sub1_1 = tile3dArray[i2];
            for (int i3 = -25; i3 <= 0; i3++) {
                int j3 = anInt543 + i3;
                int l3 = anInt543 - i3;
                if (j3 >= anInt539 || l3 < anInt540) {
                    for (int j4 = -25; j4 <= 0; j4++) {
                        int l4 = anInt544 + j4;
                        int j5 = anInt544 - j4;
                        if (j3 >= anInt539) {
                            if (l4 >= anInt541) {
                                Tile tile = aclass38_sub1_1[j3][l4];
                                if (tile != null && tile.draw)
                                    method322(tile, true);
                            }
                            if (j5 < anInt542) {
                                Tile tile_2 = aclass38_sub1_1[j3][j5];
                                if (tile_2 != null && tile_2.draw)
                                    method322(tile_2, true);
                            }
                        }
                        if (l3 < anInt540) {
                            if (l4 >= anInt541) {
                                Tile tile_3 = aclass38_sub1_1[l3][l4];
                                if (tile_3 != null && tile_3.draw)
                                    method322(tile_3, true);
                            }
                            if (j5 < anInt542) {
                                Tile tile_4 = aclass38_sub1_1[l3][j5];
                                if (tile_4 != null && tile_4.draw)
                                    method322(tile_4, true);
                            }
                        }
                        if (anInt536 == 0) {
                            aBoolean557 = false;
                            return;
                        }
                    }

                }
            }

        }

        for (int k2 = anInt532; k2 < anInt527; k2++) {
            Tile[][] aclass38_sub1_2 = tile3dArray[k2];
            for (int k3 = -25; k3 <= 0; k3++) {
                int i4 = anInt543 + k3;
                int k4 = anInt543 - k3;
                if (i4 >= anInt539 || k4 < anInt540) {
                    for (int i5 = -25; i5 <= 0; i5++) {
                        int k5 = anInt544 + i5;
                        int l5 = anInt544 - i5;
                        if (i4 >= anInt539) {
                            if (k5 >= anInt541) {
                                Tile tile_5 = aclass38_sub1_2[i4][k5];
                                if (tile_5 != null && tile_5.draw)
                                    method322(tile_5, false);
                            }
                            if (l5 < anInt542) {
                                Tile tile_6 = aclass38_sub1_2[i4][l5];
                                if (tile_6 != null && tile_6.draw)
                                    method322(tile_6, false);
                            }
                        }
                        if (k4 < anInt540) {
                            if (k5 >= anInt541) {
                                Tile tile_7 = aclass38_sub1_2[k4][k5];
                                if (tile_7 != null && tile_7.draw)
                                    method322(tile_7, false);
                            }
                            if (l5 < anInt542) {
                                Tile tile_8 = aclass38_sub1_2[k4][l5];
                                if (tile_8 != null && tile_8.draw)
                                    method322(tile_8, false);
                            }
                        }
                        if (anInt536 == 0) {
                            aBoolean557 = false;
                            return;
                        }
                    }

                }
            }

        }

    }

    public void method322(Tile tile_1, boolean flag) {
        linkedList.method267(tile_1);
        do {
            Tile tile;
            do {
                tile = (Tile) linkedList.method269();
                if (tile == null)
                    return;
            } while (!tile.isVisible);
            int i = tile.x;
            int j = tile.z;
            int k = tile.level;
            int l = tile.renderLevel;
            Tile[][] aclass38_sub1 = tile3dArray[k];
            if (tile.draw) {
                if (flag) {
                    if (k > 0) {
                        Tile tile_2 = tile3dArray[k - 1][i][j];
                        if (tile_2 != null && tile_2.isVisible)
                            continue;
                    }
                    if (i <= anInt543 && i > anInt539) {
                        Tile tile_3 = aclass38_sub1[i - 1][j];
                        if (tile_3 != null && tile_3.isVisible
                                && (tile_3.draw || (tile.flags & 1) == 0))
                            continue;
                    }
                    if (i >= anInt543 && i < anInt540 - 1) {
                        Tile tile_4 = aclass38_sub1[i + 1][j];
                        if (tile_4 != null && tile_4.isVisible
                                && (tile_4.draw || (tile.flags & 4) == 0))
                            continue;
                    }
                    if (j <= anInt544 && j > anInt541) {
                        Tile tile_5 = aclass38_sub1[i][j - 1];
                        if (tile_5 != null && tile_5.isVisible
                                && (tile_5.draw || (tile.flags & 8) == 0))
                            continue;
                    }
                    if (j >= anInt544 && j < anInt542 - 1) {
                        Tile tile_6 = aclass38_sub1[i][j + 1];
                        if (tile_6 != null && tile_6.isVisible
                                && (tile_6.draw || (tile.flags & 2) == 0))
                            continue;
                    }
                } else {
                    flag = true;
                }
                tile.draw = false;
                if (tile.bridge != null) {
                    Tile tile_7 = tile.bridge;
                    if (tile_7.underlay != null) {
                        if (!method328(0, i, j))
                            method323(tile_7.underlay, 0, anInt548, anInt549, anInt550, anInt551, i, j);
                    } else if (tile_7.overlay != null && !method328(0, i, j))
                        method324(anInt550, j, tile_7.overlay, i, anInt549, anInt548, anInt551, true);
                    Wall wall = tile_7.wall;
                    if (wall != null)
                        wall.entity0.method371(0, anInt548, anInt549, anInt550, anInt551,
                                wall.x - anInt545, wall.y - anInt546, wall.z - anInt547,
                                wall.bitset);
                    for (int i2 = 0; i2 < tile_7.locationCount; i2++) {
                        Loc loc = tile_7.locs[i2];
                        if (loc != null) {
                            Model class38_sub2_sub1 = loc.model;
                            if (class38_sub2_sub1 == null)
                                class38_sub2_sub1 = loc.entity.getDrawMethod();
                            class38_sub2_sub1.method371(loc.yaw, anInt548, anInt549, anInt550, anInt551,
                                    loc.x - anInt545, loc.y - anInt546,
                                    loc.z - anInt547, loc.bitset);
                        }
                    }

                }
                boolean flag1 = false;
                if (tile.underlay != null) {
                    if (!method328(l, i, j)) {
                        flag1 = true;
                        method323(tile.underlay, l, anInt548, anInt549, anInt550, anInt551, i, j);
                    }
                } else if (tile.overlay != null && !method328(l, i, j)) {
                    flag1 = true;
                    method324(anInt550, j, tile.overlay, i, anInt549, anInt548, anInt551, true);
                }
                int j1 = 0;
                int j2 = 0;
                Wall wall_3 = tile.wall;
                WallDecoration wallDecoration_1 = tile.wallDecoration;
                if (wall_3 != null || wallDecoration_1 != null) {
                    if (anInt543 == i)
                        j1++;
                    else if (anInt543 < i)
                        j1 += 2;
                    if (anInt544 == j)
                        j1 += 3;
                    else if (anInt544 > j)
                        j1 += 6;
                    j2 = anIntArray568[j1];
                    tile.wallDrawFlags = anIntArray570[j1];
                }
                if (wall_3 != null) {
                    if ((wall_3.type0 & anIntArray569[j1]) != 0) {
                        if (wall_3.type0 == 16) {
                            tile.wallCullDirection = 3;
                            tile.wallUncullDirection = anIntArray571[j1];
                            tile.wallCullOppositeDirection = 3 - tile.wallUncullDirection;
                        } else if (wall_3.type0 == 32) {
                            tile.wallCullDirection = 6;
                            tile.wallUncullDirection = anIntArray572[j1];
                            tile.wallCullOppositeDirection = 6 - tile.wallUncullDirection;
                        } else if (wall_3.type0 == 64) {
                            tile.wallCullDirection = 12;
                            tile.wallUncullDirection = anIntArray573[j1];
                            tile.wallCullOppositeDirection = 12 - tile.wallUncullDirection;
                        } else {
                            tile.wallCullDirection = 9;
                            tile.wallUncullDirection = anIntArray574[j1];
                            tile.wallCullOppositeDirection = 9 - tile.wallUncullDirection;
                        }
                    } else {
                        tile.wallCullDirection = 0;
                    }
                    if ((wall_3.type0 & j2) != 0 && !method329(l, i, j, wall_3.type0))
                        wall_3.entity0.method371(0, anInt548, anInt549, anInt550, anInt551,
                                wall_3.x - anInt545, wall_3.y - anInt546,
                                wall_3.z - anInt547, wall_3.bitset);
                    if ((wall_3.type1 & j2) != 0 && !method329(l, i, j, wall_3.type1))
                        wall_3.entity1.method371(0, anInt548, anInt549, anInt550, anInt551,
                                wall_3.x - anInt545, wall_3.y - anInt546,
                                wall_3.z - anInt547, wall_3.bitset);
                }
                if (wallDecoration_1 != null && !method330(l, i, j, wallDecoration_1.model.anInt1247))
                    if ((wallDecoration_1.type & j2) != 0)
                        wallDecoration_1.model.method371(wallDecoration_1.rotation, anInt548, anInt549, anInt550,
                                anInt551, wallDecoration_1.sceneX - anInt545, wallDecoration_1.sceneY - anInt546,
                                wallDecoration_1.sceneZ - anInt547, wallDecoration_1.bitset);
                    else if ((wallDecoration_1.type & 0x300) != 0) {
                        int j4 = wallDecoration_1.sceneX - anInt545;
                        int l5 = wallDecoration_1.sceneY - anInt546;
                        int k6 = wallDecoration_1.sceneZ - anInt547;
                        int k7 = wallDecoration_1.rotation;
                        int l8;
                        if (k7 == 1 || k7 == 2)
                            l8 = -j4;
                        else
                            l8 = j4;
                        int l9;
                        if (k7 == 2 || k7 == 3)
                            l9 = -k6;
                        else
                            l9 = k6;
                        if ((wallDecoration_1.type & 0x100) != 0 && l9 < l8) {
                            int i10 = j4 + anIntArray553[k7];
                            int k10 = k6 + anIntArray554[k7];
                            wallDecoration_1.model.method371(k7 * 512 + 256, anInt548, anInt549, anInt550,
                                    anInt551, i10, l5, k10, wallDecoration_1.bitset);
                        }
                        if ((wallDecoration_1.type & 0x200) != 0 && l9 > l8) {
                            int j10 = j4 + anIntArray555[k7];
                            int l10 = k6 + anIntArray556[k7];
                            wallDecoration_1.model.method371(k7 * 512 + 1280 & 0x7ff, anInt548, anInt549,
                                    anInt550, anInt551, j10, l5, l10, wallDecoration_1.bitset);
                        }
                    }
                if (flag1) {
                    GroundDecoration groundDecoration = tile.groundDecoration;
                    if (groundDecoration != null)
                        groundDecoration.model.method371(0, anInt548, anInt549, anInt550, anInt551,
                                groundDecoration.sceneX - anInt545, groundDecoration.sceneY - anInt546, groundDecoration.sceneZ - anInt547,
                                groundDecoration.bitset);
                    ObjEntity objEntity_1 = tile.objEntity;
                    if (objEntity_1 != null && objEntity_1.offsetY == 0) {
                        if (objEntity_1.entity1 != null)
                            objEntity_1.entity1.method371(0, anInt548, anInt549, anInt550, anInt551,
                                    objEntity_1.x - anInt545, objEntity_1.y - anInt546,
                                    objEntity_1.z - anInt547, objEntity_1.bitset);
                        if (objEntity_1.entity2 != null)
                            objEntity_1.entity2.method371(0, anInt548, anInt549, anInt550, anInt551,
                                    objEntity_1.x - anInt545, objEntity_1.y - anInt546,
                                    objEntity_1.z - anInt547, objEntity_1.bitset);
                        if (objEntity_1.entity0 != null)
                            objEntity_1.entity0.method371(0, anInt548, anInt549, anInt550, anInt551,
                                    objEntity_1.x - anInt545, objEntity_1.y - anInt546,
                                    objEntity_1.z - anInt547, objEntity_1.bitset);
                    }
                }
                int k4 = tile.flags;
                if (k4 != 0) {
                    if (i < anInt543 && (k4 & 4) != 0) {
                        Tile tile_17 = aclass38_sub1[i + 1][j];
                        if (tile_17 != null && tile_17.isVisible)
                            linkedList.method267(tile_17);
                    }
                    if (j < anInt544 && (k4 & 2) != 0) {
                        Tile tile_18 = aclass38_sub1[i][j + 1];
                        if (tile_18 != null && tile_18.isVisible)
                            linkedList.method267(tile_18);
                    }
                    if (i > anInt543 && (k4 & 1) != 0) {
                        Tile tile_19 = aclass38_sub1[i - 1][j];
                        if (tile_19 != null && tile_19.isVisible)
                            linkedList.method267(tile_19);
                    }
                    if (j > anInt544 && (k4 & 8) != 0) {
                        Tile tile_20 = aclass38_sub1[i][j - 1];
                        if (tile_20 != null && tile_20.isVisible)
                            linkedList.method267(tile_20);
                    }
                }
            }
            if (tile.wallCullDirection != 0) {
                boolean flag2 = true;
                for (int k1 = 0; k1 < tile.locationCount; k1++) {
                    if (tile.locs[k1].cycle == anInt538 || (tile.locFlags[k1]
                            & tile.wallCullDirection) != tile.wallUncullDirection)
                        continue;
                    flag2 = false;
                    break;
                }

                if (flag2) {
                    Wall wall_1 = tile.wall;
                    if (!method329(l, i, j, wall_1.type0))
                        wall_1.entity0.method371(0, anInt548, anInt549, anInt550, anInt551,
                                wall_1.x - anInt545, wall_1.y - anInt546,
                                wall_1.z - anInt547, wall_1.bitset);
                    tile.wallCullDirection = 0;
                }
            }
            if (tile.drawLocations) {
                int i1 = tile.locationCount;
                tile.drawLocations = false;
                int l1 = 0;
                label0:
                for (int k2 = 0; k2 < i1; k2++) {
                    Loc loc_1 = tile.locs[k2];
                    if (loc_1.cycle == anInt538)
                        continue;
                    for (int k3 = loc_1.minSceneTileX; k3 <= loc_1.maxSceneTileX; k3++) {
                        for (int l4 = loc_1.minSceneTileZ; l4 <= loc_1.maxSceneTileZ; l4++) {
                            Tile tile_21 = aclass38_sub1[k3][l4];
                            if (tile_21.draw) {
                                tile.drawLocations = true;
                            } else {
                                if (tile_21.wallCullDirection == 0)
                                    continue;
                                int l6 = 0;
                                if (k3 > loc_1.minSceneTileX)
                                    l6++;
                                if (k3 < loc_1.maxSceneTileX)
                                    l6 += 4;
                                if (l4 > loc_1.minSceneTileZ)
                                    l6 += 8;
                                if (l4 < loc_1.maxSceneTileZ)
                                    l6 += 2;
                                if ((l6 & tile_21.wallCullDirection) != tile.wallCullOppositeDirection)
                                    continue;
                                tile.drawLocations = true;
                            }
                            continue label0;
                        }

                    }

                    locArray2[l1++] = loc_1;
                    int i5 = anInt543 - loc_1.minSceneTileX;
                    int i6 = loc_1.maxSceneTileX - anInt543;
                    if (i6 > i5)
                        i5 = i6;
                    int i7 = anInt544 - loc_1.minSceneTileZ;
                    int l7 = loc_1.maxSceneTileZ - anInt544;
                    if (l7 > i7)
                        loc_1.distance = i5 + l7;
                    else
                        loc_1.distance = i5 + i7;
                }

                while (l1 > 0) {
                    int i3 = -50;
                    int l3 = -1;
                    for (int j5 = 0; j5 < l1; j5++) {
                        Loc loc_2 = locArray2[j5];
                        if (loc_2.distance > i3 && loc_2.cycle != anInt538) {
                            i3 = loc_2.distance;
                            l3 = j5;
                        }
                    }

                    if (l3 == -1)
                        break;
                    Loc loc_3 = locArray2[l3];
                    loc_3.cycle = anInt538;
                    Model class38_sub2_sub1_1 = loc_3.model;
                    if (class38_sub2_sub1_1 == null)
                        class38_sub2_sub1_1 = loc_3.entity.getDrawMethod();
                    if (!method331(l, loc_3.minSceneTileX, loc_3.maxSceneTileX, loc_3.minSceneTileZ, loc_3.maxSceneTileZ,
                            class38_sub2_sub1_1.anInt1247))
                        class38_sub2_sub1_1.method371(loc_3.yaw, anInt548, anInt549, anInt550, anInt551,
                                loc_3.x - anInt545, loc_3.y - anInt546,
                                loc_3.z - anInt547, loc_3.bitset);
                    for (int i8 = loc_3.minSceneTileX; i8 <= loc_3.maxSceneTileX; i8++) {
                        for (int i9 = loc_3.minSceneTileZ; i9 <= loc_3.maxSceneTileZ; i9++) {
                            Tile tile_22 = aclass38_sub1[i8][i9];
                            if (tile_22.wallCullDirection != 0)
                                linkedList.method267(tile_22);
                            else if ((i8 != i || i9 != j) && tile_22.isVisible)
                                linkedList.method267(tile_22);
                        }

                    }

                }
                if (tile.drawLocations)
                    continue;
            }
            if (!tile.isVisible || tile.wallCullDirection != 0)
                continue;
            if (i <= anInt543 && i > anInt539) {
                Tile tile_8 = aclass38_sub1[i - 1][j];
                if (tile_8 != null && tile_8.isVisible)
                    continue;
            }
            if (i >= anInt543 && i < anInt540 - 1) {
                Tile tile_9 = aclass38_sub1[i + 1][j];
                if (tile_9 != null && tile_9.isVisible)
                    continue;
            }
            if (j <= anInt544 && j > anInt541) {
                Tile tile_10 = aclass38_sub1[i][j - 1];
                if (tile_10 != null && tile_10.isVisible)
                    continue;
            }
            if (j >= anInt544 && j < anInt542 - 1) {
                Tile tile_11 = aclass38_sub1[i][j + 1];
                if (tile_11 != null && tile_11.isVisible)
                    continue;
            }
            tile.isVisible = false;
            anInt536--;
            ObjEntity objEntity = tile.objEntity;
            if (objEntity != null && objEntity.offsetY != 0) {
                if (objEntity.entity1 != null)
                    objEntity.entity1.method371(0, anInt548, anInt549, anInt550, anInt551,
                            objEntity.x - anInt545, objEntity.y - anInt546 - objEntity.offsetY,
                            objEntity.z - anInt547, objEntity.bitset);
                if (objEntity.entity2 != null)
                    objEntity.entity2.method371(0, anInt548, anInt549, anInt550, anInt551,
                            objEntity.x - anInt545, objEntity.y - anInt546 - objEntity.offsetY,
                            objEntity.z - anInt547, objEntity.bitset);
                if (objEntity.entity0 != null)
                    objEntity.entity0.method371(0, anInt548, anInt549, anInt550, anInt551,
                            objEntity.x - anInt545, objEntity.y - anInt546 - objEntity.offsetY,
                            objEntity.z - anInt547, objEntity.bitset);
            }
            if (tile.wallDrawFlags != 0) {
                WallDecoration wallDecoration = tile.wallDecoration;
                if (wallDecoration != null && !method330(l, i, j, wallDecoration.model.anInt1247))
                    if ((wallDecoration.type & tile.wallDrawFlags) != 0)
                        wallDecoration.model.method371(wallDecoration.rotation, anInt548, anInt549, anInt550,
                                anInt551, wallDecoration.sceneX - anInt545, wallDecoration.sceneY - anInt546,
                                wallDecoration.sceneZ - anInt547, wallDecoration.bitset);
                    else if ((wallDecoration.type & 0x300) != 0) {
                        int l2 = wallDecoration.sceneX - anInt545;
                        int j3 = wallDecoration.sceneY - anInt546;
                        int i4 = wallDecoration.sceneZ - anInt547;
                        int k5 = wallDecoration.rotation;
                        int j6;
                        if (k5 == 1 || k5 == 2)
                            j6 = -l2;
                        else
                            j6 = l2;
                        int j7;
                        if (k5 == 2 || k5 == 3)
                            j7 = -i4;
                        else
                            j7 = i4;
                        if ((wallDecoration.type & 0x100) != 0 && j7 >= j6) {
                            int j8 = l2 + anIntArray553[k5];
                            int j9 = i4 + anIntArray554[k5];
                            wallDecoration.model.method371(k5 * 512 + 256, anInt548, anInt549, anInt550,
                                    anInt551, j8, j3, j9, wallDecoration.bitset);
                        }
                        if ((wallDecoration.type & 0x200) != 0 && j7 <= j6) {
                            int k8 = l2 + anIntArray555[k5];
                            int k9 = i4 + anIntArray556[k5];
                            wallDecoration.model.method371(k5 * 512 + 1280 & 0x7ff, anInt548, anInt549,
                                    anInt550, anInt551, k8, j3, k9, wallDecoration.bitset);
                        }
                    }
                Wall wall_2 = tile.wall;
                if (wall_2 != null) {
                    if ((wall_2.type1 & tile.wallDrawFlags) != 0 && !method329(l, i, j, wall_2.type1))
                        wall_2.entity1.method371(0, anInt548, anInt549, anInt550, anInt551,
                                wall_2.x - anInt545, wall_2.y - anInt546,
                                wall_2.z - anInt547, wall_2.bitset);
                    if ((wall_2.type0 & tile.wallDrawFlags) != 0 && !method329(l, i, j, wall_2.type0))
                        wall_2.entity0.method371(0, anInt548, anInt549, anInt550, anInt551,
                                wall_2.x - anInt545, wall_2.y - anInt546,
                                wall_2.z - anInt547, wall_2.bitset);
                }
            }
            if (k < anInt527 - 1) {
                Tile tile_12 = tile3dArray[k + 1][i][j];
                if (tile_12 != null && tile_12.isVisible)
                    linkedList.method267(tile_12);
            }
            if (i < anInt543) {
                Tile tile_13 = aclass38_sub1[i + 1][j];
                if (tile_13 != null && tile_13.isVisible)
                    linkedList.method267(tile_13);
            }
            if (j < anInt544) {
                Tile tile_14 = aclass38_sub1[i][j + 1];
                if (tile_14 != null && tile_14.isVisible)
                    linkedList.method267(tile_14);
            }
            if (i > anInt543) {
                Tile tile_15 = aclass38_sub1[i - 1][j];
                if (tile_15 != null && tile_15.isVisible)
                    linkedList.method267(tile_15);
            }
            if (j > anInt544) {
                Tile tile_16 = aclass38_sub1[i][j - 1];
                if (tile_16 != null && tile_16.isVisible)
                    linkedList.method267(tile_16);
            }
        } while (true);
    }

    public void method323(TileUnderlay tileUnderlay, int i, int j, int k, int l, int i1, int j1,
                          int k1) {
        int l1;
        int i2 = l1 = (j1 << 7) - anInt545;
        int j2;
        int k2 = j2 = (k1 << 7) - anInt547;
        int l2;
        int i3 = l2 = i2 + 128;
        int j3;
        int k3 = j3 = k2 + 128;
        int l3 = anIntArrayArrayArray530[i][j1][k1] - anInt546;
        int i4 = anIntArrayArrayArray530[i][j1 + 1][k1] - anInt546;
        int j4 = anIntArrayArrayArray530[i][j1 + 1][k1 + 1] - anInt546;
        int k4 = anIntArrayArrayArray530[i][j1][k1 + 1] - anInt546;
        int l4 = k2 * l + i2 * i1 >> 16;
        k2 = k2 * i1 - i2 * l >> 16;
        i2 = l4;
        l4 = l3 * k - k2 * j >> 16;
        k2 = l3 * j + k2 * k >> 16;
        l3 = l4;
        if (k2 < 50)
            return;
        l4 = j2 * l + i3 * i1 >> 16;
        j2 = j2 * i1 - i3 * l >> 16;
        i3 = l4;
        l4 = i4 * k - j2 * j >> 16;
        j2 = i4 * j + j2 * k >> 16;
        i4 = l4;
        if (j2 < 50)
            return;
        l4 = k3 * l + l2 * i1 >> 16;
        k3 = k3 * i1 - l2 * l >> 16;
        l2 = l4;
        l4 = j4 * k - k3 * j >> 16;
        k3 = j4 * j + k3 * k >> 16;
        j4 = l4;
        if (k3 < 50)
            return;
        l4 = j3 * l + l1 * i1 >> 16;
        j3 = j3 * i1 - l1 * l >> 16;
        l1 = l4;
        l4 = k4 * k - j3 * j >> 16;
        j3 = k4 * j + j3 * k >> 16;
        k4 = l4;
        if (j3 < 50)
            return;
        int i5 = Draw3D.anInt1442 + (i2 << 9) / k2;
        int j5 = Draw3D.anInt1443 + (l3 << 9) / k2;
        int k5 = Draw3D.anInt1442 + (i3 << 9) / j2;
        int l5 = Draw3D.anInt1443 + (i4 << 9) / j2;
        int i6 = Draw3D.anInt1442 + (l2 << 9) / k3;
        int j6 = Draw3D.anInt1443 + (j4 << 9) / k3;
        int k6 = Draw3D.anInt1442 + (l1 << 9) / j3;
        int l6 = Draw3D.anInt1443 + (k4 << 9) / j3;
        Draw3D.anInt1441 = 0;
        if ((i6 - k6) * (l5 - l6) - (j6 - l6) * (k5 - k6) > 0) {
            Draw3D.aBoolean1438 = i6 < 0 || k6 < 0 || k5 < 0 || i6 > Draw2D.anInt1315 || k6 > Draw2D.anInt1315
                    || k5 > Draw2D.anInt1315;
            if (aBoolean557 && method326(anInt558, anInt559, j6, l6, l5, i6, k6, k5)) {
                anInt560 = j1;
                anInt561 = k1;
            }
            if (tileUnderlay.textureIndex == -1) {
                if (tileUnderlay.northeastColor != 0xbc614e)
                    Draw3D.method395(j6, l6, l5, i6, k6, k5, tileUnderlay.northeastColor, tileUnderlay.northwestColor,
                            tileUnderlay.southeastColor);
            } else if (!aBoolean526) {
                if (tileUnderlay.isFlat)
                    Draw3D.method399(j6, l6, l5, i6, k6, k5, tileUnderlay.northeastColor, tileUnderlay.northwestColor,
                            tileUnderlay.southeastColor, i2, i3, l1, l3, i4, k4, k2, j2, j3, tileUnderlay.textureIndex);
                else
                    Draw3D.method399(j6, l6, l5, i6, k6, k5, tileUnderlay.northeastColor, tileUnderlay.northwestColor,
                            tileUnderlay.southeastColor, l2, l1, i3, j4, k4, i4, k3, j3, j2, tileUnderlay.textureIndex);
            } else {
                int i7 = anIntArray575[tileUnderlay.textureIndex];
                Draw3D.method395(j6, l6, l5, i6, k6, k5, method325(tileUnderlay.northeastColor, i7, 9),
                        method325(tileUnderlay.northwestColor, i7, 9), method325(tileUnderlay.southeastColor, i7, 9));
            }
        }
        if ((i5 - k5) * (l6 - l5) - (j5 - l5) * (k6 - k5) > 0) {
            Draw3D.aBoolean1438 = i5 < 0 || k5 < 0 || k6 < 0 || i5 > Draw2D.anInt1315 || k5 > Draw2D.anInt1315
                    || k6 > Draw2D.anInt1315;
            if (aBoolean557 && method326(anInt558, anInt559, j5, l5, l6, i5, k5, k6)) {
                anInt560 = j1;
                anInt561 = k1;
            }
            if (tileUnderlay.textureIndex == -1) {
                if (tileUnderlay.southwestColor != 0xbc614e) {
                    Draw3D.method395(j5, l5, l6, i5, k5, k6, tileUnderlay.southwestColor, tileUnderlay.southeastColor,
                            tileUnderlay.northwestColor);
                    return;
                }
            } else {
                if (!aBoolean526) {
                    Draw3D.method399(j5, l5, l6, i5, k5, k6, tileUnderlay.southwestColor, tileUnderlay.southeastColor,
                            tileUnderlay.northwestColor, i2, i3, l1, l3, i4, k4, k2, j2, j3, tileUnderlay.textureIndex);
                    return;
                }
                int j7 = anIntArray575[tileUnderlay.textureIndex];
                Draw3D.method395(j5, l5, l6, i5, k5, k6, method325(tileUnderlay.southwestColor, j7, 9),
                        method325(tileUnderlay.southeastColor, j7, 9), method325(tileUnderlay.northwestColor, j7, 9));
            }
        }
    }

    public void method324(int i, int j, Class15 class15, int k, int l, int i1, int j1,
                          boolean flag) {
        int k1 = class15.anIntArray323.length;
        for (int l1 = 0; l1 < k1; l1++) {
            int i2 = class15.anIntArray323[l1] - anInt545;
            int k2 = class15.anIntArray324[l1] - anInt546;
            int i3 = class15.anIntArray325[l1] - anInt547;
            int k3 = i3 * i + i2 * j1 >> 16;
            i3 = i3 * j1 - i2 * i >> 16;
            i2 = k3;
            k3 = k2 * l - i3 * i1 >> 16;
            i3 = k2 * i1 + i3 * l >> 16;
            k2 = k3;
            if (i3 < 50)
                return;
            if (class15.anIntArray332 != null) {
                Class15.anIntArray340[l1] = i2;
                Class15.anIntArray341[l1] = k2;
                Class15.anIntArray342[l1] = i3;
            }
            Class15.anIntArray338[l1] = Draw3D.anInt1442 + (i2 << 9) / i3;
            Class15.anIntArray339[l1] = Draw3D.anInt1443 + (k2 << 9) / i3;
        }

        Draw3D.anInt1441 = 0;
        k1 = class15.anIntArray329.length;
        if (!flag)
            return;
        for (int j2 = 0; j2 < k1; j2++) {
            int l2 = class15.anIntArray329[j2];
            int j3 = class15.anIntArray330[j2];
            int l3 = class15.anIntArray331[j2];
            int i4 = Class15.anIntArray338[l2];
            int j4 = Class15.anIntArray338[j3];
            int k4 = Class15.anIntArray338[l3];
            int l4 = Class15.anIntArray339[l2];
            int i5 = Class15.anIntArray339[j3];
            int j5 = Class15.anIntArray339[l3];
            if ((i4 - j4) * (j5 - i5) - (l4 - i5) * (k4 - j4) > 0) {
                Draw3D.aBoolean1438 = i4 < 0 || j4 < 0 || k4 < 0 || i4 > Draw2D.anInt1315 || j4 > Draw2D.anInt1315
                        || k4 > Draw2D.anInt1315;
                if (aBoolean557 && method326(anInt558, anInt559, l4, i5, j5, i4, j4, k4)) {
                    anInt560 = k;
                    anInt561 = j;
                }
                if (class15.anIntArray332 == null || class15.anIntArray332[j2] == -1) {
                    if (class15.anIntArray326[j2] != 0xbc614e)
                        Draw3D.method395(l4, i5, j5, i4, j4, k4, class15.anIntArray326[j2],
                                class15.anIntArray327[j2], class15.anIntArray328[j2]);
                } else if (!aBoolean526) {
                    if (class15.aBoolean333)
                        Draw3D.method399(l4, i5, j5, i4, j4, k4, class15.anIntArray326[j2],
                                class15.anIntArray327[j2], class15.anIntArray328[j2], Class15.anIntArray340[0],
                                Class15.anIntArray340[1], Class15.anIntArray340[3], Class15.anIntArray341[0],
                                Class15.anIntArray341[1], Class15.anIntArray341[3], Class15.anIntArray342[0],
                                Class15.anIntArray342[1], Class15.anIntArray342[3], class15.anIntArray332[j2]);
                    else
                        Draw3D.method399(l4, i5, j5, i4, j4, k4, class15.anIntArray326[j2],
                                class15.anIntArray327[j2], class15.anIntArray328[j2], Class15.anIntArray340[l2],
                                Class15.anIntArray340[j3], Class15.anIntArray340[l3], Class15.anIntArray341[l2],
                                Class15.anIntArray341[j3], Class15.anIntArray341[l3], Class15.anIntArray342[l2],
                                Class15.anIntArray342[j3], Class15.anIntArray342[l3], class15.anIntArray332[j2]);
                } else {
                    int k5 = anIntArray575[class15.anIntArray332[j2]];
                    Draw3D.method395(l4, i5, j5, i4, j4, k4,
                            method325(class15.anIntArray326[j2], k5, 9), method325(class15.anIntArray327[j2], k5, 9),
                            method325(class15.anIntArray328[j2], k5, 9));
                }
            }
        }

    }

    public int method325(int i, int j, int k) {
        i = 127 - i;
        if (k < 9 || k > 9)
            return 4;
        i = (i * (j & 0x7f)) / 160;
        if (i < 2)
            i = 2;
        else if (i > 126)
            i = 126;
        return (j & 0xff80) + i;
    }

    public boolean method326(int i, int j, int k, int l, int i1, int j1, int k1,
                             int l1) {
        if (j < k && j < l && j < i1)
            return false;
        if (j > k && j > l && j > i1)
            return false;
        if (i < j1 && i < k1 && i < l1)
            return false;
        if (i > j1 && i > k1 && i > l1)
            return false;
        int i2 = (j - k) * (k1 - j1) - (i - j1) * (l - k);
        int j2 = (j - i1) * (j1 - l1) - (i - l1) * (k - i1);
        int k2 = (j - l) * (l1 - k1) - (i - k1) * (i1 - l);
        return i2 * k2 > 0 && k2 * j2 > 0;
    }

    public void method327(boolean flag) {
        int i = anIntArray563[anInt537];
        Occluder[] aclass23 = occluder2dArray[anInt537];
        anInt565 = 0;
        for (int j = 0; j < i; j++) {
            Occluder occluder = aclass23[j];
            if (occluder.type == 1) {
                int k = (occluder.minTileX - anInt543) + 25;
                if (k < 0 || k > 50)
                    continue;
                int j1 = (occluder.minTileZ - anInt544) + 25;
                if (j1 < 0)
                    j1 = 0;
                int i2 = (occluder.maxTileZ - anInt544) + 25;
                if (i2 > 50)
                    i2 = 50;
                boolean flag1 = false;
                while (j1 <= i2)
                    if (aBooleanArrayArray582[k][j1++]) {
                        flag1 = true;
                        break;
                    }
                if (!flag1)
                    continue;
                int i3 = anInt545 - occluder.minX;
                if (i3 > 32) {
                    occluder.testDirection = 1;
                } else {
                    if (i3 >= -32)
                        continue;
                    occluder.testDirection = 2;
                    i3 = -i3;
                }
                occluder.minNormalZ = (occluder.minZ - anInt547 << 8) / i3;
                occluder.maxNormalZ = (occluder.maxZ - anInt547 << 8) / i3;
                occluder.minNormalY = (occluder.minY - anInt546 << 8) / i3;
                occluder.maxNormalY = (occluder.maxY - anInt546 << 8) / i3;
                occluderArray[anInt565++] = occluder;
                continue;
            }
            if (occluder.type == 2) {
                int l = (occluder.minTileZ - anInt544) + 25;
                if (l < 0 || l > 50)
                    continue;
                int k1 = (occluder.minTileX - anInt543) + 25;
                if (k1 < 0)
                    k1 = 0;
                int j2 = (occluder.maxTileX - anInt543) + 25;
                if (j2 > 50)
                    j2 = 50;
                boolean flag2 = false;
                while (k1 <= j2)
                    if (aBooleanArrayArray582[k1++][l]) {
                        flag2 = true;
                        break;
                    }
                if (!flag2)
                    continue;
                int j3 = anInt547 - occluder.minZ;
                if (j3 > 32) {
                    occluder.testDirection = 3;
                } else {
                    if (j3 >= -32)
                        continue;
                    occluder.testDirection = 4;
                    j3 = -j3;
                }
                occluder.minNormalX = (occluder.minX - anInt545 << 8) / j3;
                occluder.maxNormalX = (occluder.maxX - anInt545 << 8) / j3;
                occluder.minNormalY = (occluder.minY - anInt546 << 8) / j3;
                occluder.maxNormalY = (occluder.maxY - anInt546 << 8) / j3;
                occluderArray[anInt565++] = occluder;
            } else if (occluder.type == 4) {
                int i1 = occluder.minY - anInt546;
                if (i1 > 128) {
                    int l1 = (occluder.minTileZ - anInt544) + 25;
                    if (l1 < 0)
                        l1 = 0;
                    int k2 = (occluder.maxTileZ - anInt544) + 25;
                    if (k2 > 50)
                        k2 = 50;
                    if (l1 <= k2) {
                        int l2 = (occluder.minTileX - anInt543) + 25;
                        if (l2 < 0)
                            l2 = 0;
                        int k3 = (occluder.maxTileX - anInt543) + 25;
                        if (k3 > 50)
                            k3 = 50;
                        boolean flag3 = false;
                        label0:
                        for (int l3 = l2; l3 <= k3; l3++) {
                            for (int i4 = l1; i4 <= k2; i4++) {
                                if (!aBooleanArrayArray582[l3][i4])
                                    continue;
                                flag3 = true;
                                break label0;
                            }

                        }

                        if (flag3) {
                            occluder.testDirection = 5;
                            occluder.minNormalX = (occluder.minX - anInt545 << 8) / i1;
                            occluder.maxNormalX = (occluder.maxX - anInt545 << 8) / i1;
                            occluder.minNormalZ = (occluder.minZ - anInt547 << 8) / i1;
                            occluder.maxNormalZ = (occluder.maxZ - anInt547 << 8) / i1;
                            occluderArray[anInt565++] = occluder;
                        }
                    }
                }
            }
        }

        if (!flag)
            ;
    }

    public boolean method328(int i, int j, int k) {
        int l = anIntArrayArrayArray535[i][j][k];
        if (l == -anInt538)
            return false;
        if (l == anInt538)
            return true;
        int i1 = j << 7;
        int j1 = k << 7;
        if (method332(i1 + 1, anIntArrayArrayArray530[i][j][k], j1 + 1)
                && method332((i1 + 128) - 1, anIntArrayArrayArray530[i][j + 1][k], j1 + 1)
                && method332((i1 + 128) - 1, anIntArrayArrayArray530[i][j + 1][k + 1], (j1 + 128) - 1)
                && method332(i1 + 1, anIntArrayArrayArray530[i][j][k + 1], (j1 + 128) - 1)) {
            anIntArrayArrayArray535[i][j][k] = anInt538;
            return true;
        } else {
            anIntArrayArrayArray535[i][j][k] = -anInt538;
            return false;
        }
    }

    public boolean method329(int i, int j, int k, int l) {
        if (!method328(i, j, k))
            return false;
        int i1 = j << 7;
        int j1 = k << 7;
        int k1 = anIntArrayArrayArray530[i][j][k] - 1;
        int l1 = k1 - 120;
        int i2 = k1 - 230;
        int j2 = k1 - 238;
        if (l < 16) {
            if (l == 1) {
                if (i1 > anInt545) {
                    if (!method332(i1, k1, j1))
                        return false;
                    if (!method332(i1, k1, j1 + 128))
                        return false;
                }
                if (i > 0) {
                    if (!method332(i1, l1, j1))
                        return false;
                    if (!method332(i1, l1, j1 + 128))
                        return false;
                }
                if (!method332(i1, i2, j1))
                    return false;
                return method332(i1, i2, j1 + 128);
            }
            if (l == 2) {
                if (j1 < anInt547) {
                    if (!method332(i1, k1, j1 + 128))
                        return false;
                    if (!method332(i1 + 128, k1, j1 + 128))
                        return false;
                }
                if (i > 0) {
                    if (!method332(i1, l1, j1 + 128))
                        return false;
                    if (!method332(i1 + 128, l1, j1 + 128))
                        return false;
                }
                if (!method332(i1, i2, j1 + 128))
                    return false;
                return method332(i1 + 128, i2, j1 + 128);
            }
            if (l == 4) {
                if (i1 < anInt545) {
                    if (!method332(i1 + 128, k1, j1))
                        return false;
                    if (!method332(i1 + 128, k1, j1 + 128))
                        return false;
                }
                if (i > 0) {
                    if (!method332(i1 + 128, l1, j1))
                        return false;
                    if (!method332(i1 + 128, l1, j1 + 128))
                        return false;
                }
                if (!method332(i1 + 128, i2, j1))
                    return false;
                return method332(i1 + 128, i2, j1 + 128);
            }
            if (l == 8) {
                if (j1 > anInt547) {
                    if (!method332(i1, k1, j1))
                        return false;
                    if (!method332(i1 + 128, k1, j1))
                        return false;
                }
                if (i > 0) {
                    if (!method332(i1, l1, j1))
                        return false;
                    if (!method332(i1 + 128, l1, j1))
                        return false;
                }
                if (!method332(i1, i2, j1))
                    return false;
                return method332(i1 + 128, i2, j1);
            }
        }
        if (!method332(i1 + 64, j2, j1 + 64))
            return false;
        if (l == 16)
            return method332(i1, i2, j1 + 128);
        if (l == 32)
            return method332(i1 + 128, i2, j1 + 128);
        if (l == 64)
            return method332(i1 + 128, i2, j1);
        if (l == 128) {
            return method332(i1, i2, j1);
        } else {
            System.out.println("Warning unsupported wall type");
            return true;
        }
    }

    public boolean method330(int i, int j, int k, int l) {
        if (!method328(i, j, k))
            return false;
        int i1 = j << 7;
        int j1 = k << 7;
        return method332(i1 + 1, anIntArrayArrayArray530[i][j][k] - l, j1 + 1)
                && method332((i1 + 128) - 1, anIntArrayArrayArray530[i][j + 1][k] - l, j1 + 1)
                && method332((i1 + 128) - 1, anIntArrayArrayArray530[i][j + 1][k + 1] - l, (j1 + 128) - 1)
                && method332(i1 + 1, anIntArrayArrayArray530[i][j][k + 1] - l, (j1 + 128) - 1);
    }

    public boolean method331(int i, int j, int k, int l, int i1, int j1) {
        if (j == k && l == i1) {
            if (!method328(i, j, l))
                return false;
            int k1 = j << 7;
            int i2 = l << 7;
            return method332(k1 + 1, anIntArrayArrayArray530[i][j][l] - j1, i2 + 1)
                    && method332((k1 + 128) - 1, anIntArrayArrayArray530[i][j + 1][l] - j1, i2 + 1)
                    && method332((k1 + 128) - 1, anIntArrayArrayArray530[i][j + 1][l + 1] - j1, (i2 + 128) - 1)
                    && method332(k1 + 1, anIntArrayArrayArray530[i][j][l + 1] - j1, (i2 + 128) - 1);
        }
        for (int l1 = j; l1 <= k; l1++) {
            for (int j2 = l; j2 <= i1; j2++)
                if (anIntArrayArrayArray535[i][l1][j2] == -anInt538)
                    return false;

        }

        int k2 = (j << 7) + 1;
        int l2 = (l << 7) + 2;
        int i3 = anIntArrayArrayArray530[i][j][l] - j1;
        if (!method332(k2, i3, l2))
            return false;
        int j3 = (k << 7) - 1;
        if (!method332(j3, i3, l2))
            return false;
        int k3 = (i1 << 7) - 1;
        if (!method332(k2, i3, k3))
            return false;
        return method332(j3, i3, k3);
    }

    public boolean method332(int i, int j, int k) {
        for (int l = 0; l < anInt565; l++) {
            Occluder occluder = occluderArray[l];
            if (occluder.testDirection == 1) {
                int i1 = occluder.minX - i;
                if (i1 > 0) {
                    int j2 = occluder.minZ + (occluder.minNormalZ * i1 >> 8);
                    int k3 = occluder.maxZ + (occluder.maxNormalZ * i1 >> 8);
                    int l4 = occluder.minY + (occluder.minNormalY * i1 >> 8);
                    int i6 = occluder.maxY + (occluder.maxNormalY * i1 >> 8);
                    if (k >= j2 && k <= k3 && j >= l4 && j <= i6)
                        return true;
                }
            } else if (occluder.testDirection == 2) {
                int j1 = i - occluder.minX;
                if (j1 > 0) {
                    int k2 = occluder.minZ + (occluder.minNormalZ * j1 >> 8);
                    int l3 = occluder.maxZ + (occluder.maxNormalZ * j1 >> 8);
                    int i5 = occluder.minY + (occluder.minNormalY * j1 >> 8);
                    int j6 = occluder.maxY + (occluder.maxNormalY * j1 >> 8);
                    if (k >= k2 && k <= l3 && j >= i5 && j <= j6)
                        return true;
                }
            } else if (occluder.testDirection == 3) {
                int k1 = occluder.minZ - k;
                if (k1 > 0) {
                    int l2 = occluder.minX + (occluder.minNormalX * k1 >> 8);
                    int i4 = occluder.maxX + (occluder.maxNormalX * k1 >> 8);
                    int j5 = occluder.minY + (occluder.minNormalY * k1 >> 8);
                    int k6 = occluder.maxY + (occluder.maxNormalY * k1 >> 8);
                    if (i >= l2 && i <= i4 && j >= j5 && j <= k6)
                        return true;
                }
            } else if (occluder.testDirection == 4) {
                int l1 = k - occluder.minZ;
                if (l1 > 0) {
                    int i3 = occluder.minX + (occluder.minNormalX * l1 >> 8);
                    int j4 = occluder.maxX + (occluder.maxNormalX * l1 >> 8);
                    int k5 = occluder.minY + (occluder.minNormalY * l1 >> 8);
                    int l6 = occluder.maxY + (occluder.maxNormalY * l1 >> 8);
                    if (i >= i3 && i <= j4 && j >= k5 && j <= l6)
                        return true;
                }
            } else if (occluder.testDirection == 5) {
                int i2 = j - occluder.minY;
                if (i2 > 0) {
                    int j3 = occluder.minX + (occluder.minNormalX * i2 >> 8);
                    int k4 = occluder.maxX + (occluder.maxNormalX * i2 >> 8);
                    int l5 = occluder.minZ + (occluder.minNormalZ * i2 >> 8);
                    int i7 = occluder.maxZ + (occluder.maxNormalZ * i2 >> 8);
                    if (i >= j3 && i <= k4 && k >= l5 && k <= i7)
                        return true;
                }
            }
        }

        return false;
    }

    public boolean aBoolean519;
    public byte aByte520;
    public int anInt521;
    public byte aByte522;
    public static int anInt523;
    public static int anInt524 = -546;
    public static boolean aBoolean525 = true;
    public static boolean aBoolean526 = true;
    public int anInt527;
    public int anInt528;
    public int anInt529;
    public int[][][] anIntArrayArrayArray530;
    public Tile[][][] tile3dArray;
    public int anInt532;
    public int anInt533;
    public Loc[] locArray1;
    public int[][][] anIntArrayArrayArray535;
    public static int anInt536;
    public static int anInt537;
    public static int anInt538;
    public static int anInt539;
    public static int anInt540;
    public static int anInt541;
    public static int anInt542;
    public static int anInt543;
    public static int anInt544;
    public static int anInt545;
    public static int anInt546;
    public static int anInt547;
    public static int anInt548;
    public static int anInt549;
    public static int anInt550;
    public static int anInt551;
    public static Loc[] locArray2 = new Loc[100];
    public static final int[] anIntArray553 = {
            53, -53, -53, 53
    };
    public static final int[] anIntArray554 = {
            -53, -53, 53, 53
    };
    public static final int[] anIntArray555 = {
            -45, 45, 45, -45
    };
    public static final int[] anIntArray556 = {
            45, 45, -45, -45
    };
    public static boolean aBoolean557;
    public static int anInt558;
    public static int anInt559;
    public static int anInt560 = -1;
    public static int anInt561 = -1;
    public static int anInt562;
    public static int[] anIntArray563;
    public static Occluder[][] occluder2dArray;
    public static int anInt565;
    public static Occluder[] occluderArray = new Occluder[500];
    public static LinkedList linkedList = new LinkedList();
    public static final int[] anIntArray568 = {
            19, 55, 38, 155, 255, 110, 137, 205, 76
    };
    public static final int[] anIntArray569 = {
            160, 192, 80, 96, 0, 144, 80, 48, 160
    };
    public static final int[] anIntArray570 = {
            76, 8, 137, 4, 0, 1, 38, 2, 19
    };
    public static final int[] anIntArray571 = {
            0, 0, 2, 0, 0, 2, 1, 1, 0
    };
    public static final int[] anIntArray572 = {
            2, 0, 0, 2, 0, 0, 0, 4, 4
    };
    public static final int[] anIntArray573 = {
            0, 4, 4, 8, 0, 0, 8, 0, 0
    };
    public static final int[] anIntArray574 = {
            1, 1, 0, 0, 0, 8, 0, 0, 8
    };
    public static final int[] anIntArray575 = {
            41, 39248, 41, 4643, 41, 41, 41, 41, 41, 41,
            41, 41, 41, 41, 41, 43086, 41, 41, 41, 41,
            41, 41, 41, 8602, 41, 28992, 41, 41, 41, 41,
            41, 5056, 41, 41, 41, 41, 41, 41, 41, 41,
            41, 41, 41, 41, 41, 41, 3131, 41, 41, 41
    };
    public int[] anIntArray576;
    public int[] anIntArray577;
    public int anInt578;
    public int[][] anIntArrayArray579 = {
            new int[16], {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1
    },
            {
                    1, 0, 0, 0, 1, 1, 0, 0, 1, 1,
                    1, 0, 1, 1, 1, 1
            },
            {
                    1, 1, 0, 0, 1, 1, 0, 0, 1, 0,
                    0, 0, 1, 0, 0, 0
            },
            {
                    0, 0, 1, 1, 0, 0, 1, 1, 0, 0,
                    0, 1, 0, 0, 0, 1
            },
            {
                    0, 1, 1, 1, 0, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1
            },
            {
                    1, 1, 1, 0, 1, 1, 1, 0, 1, 1,
                    1, 1, 1, 1, 1, 1
            },
            {
                    1, 1, 0, 0, 1, 1, 0, 0, 1, 1,
                    0, 0, 1, 1, 0, 0
            },
            {
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                    0, 0, 1, 1, 0, 0
            },
            {
                    1, 1, 1, 1, 1, 1, 1, 1, 0, 1,
                    1, 1, 0, 0, 1, 1
            },
            {
                    1, 1, 1, 1, 1, 1, 0, 0, 1, 0,
                    0, 0, 1, 0, 0, 0
            },
            {
                    0, 0, 0, 0, 0, 0, 1, 1, 0, 1,
                    1, 1, 0, 1, 1, 1
            },
            {
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                    1, 0, 1, 1, 1, 1
            }
    };
    public int[][] anIntArrayArray580 = {
            {
                    0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                    10, 11, 12, 13, 14, 15
            },
            {
                    12, 8, 4, 0, 13, 9, 5, 1, 14, 10,
                    6, 2, 15, 11, 7, 3
            },
            {
                    15, 14, 13, 12, 11, 10, 9, 8, 7, 6,
                    5, 4, 3, 2, 1, 0
            },
            {
                    3, 7, 11, 15, 2, 6, 10, 14, 1, 5,
                    9, 13, 0, 4, 8, 12
            }
    };
    public static boolean[][][][] aBooleanArrayArrayArrayArray581 = new boolean[8][32][51][51];
    public static boolean[][] aBooleanArrayArray582;
    public static int anInt583;
    public static int anInt584;
    public static int anInt585;
    public static int anInt586;
    public static int anInt587;
    public static int anInt588;

    static {
        anInt562 = 4;
        anIntArray563 = new int[anInt562];
        occluder2dArray = new Occluder[anInt562][500];
    }
}
