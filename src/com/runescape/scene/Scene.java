package com.runescape.scene;

import com.runescape.cache.Model;
import com.runescape.graphics.Draw2D;
import com.runescape.graphics.Draw3D;
import com.runescape.util.LinkedList;

public class Scene {

    public Scene(int[][][] ai, int tileCountZ, int maxLevel, int tileCountX) {
        locs = new Loc[5000];
        vertexAMergeIndex = new int[10000];
        vertexBMergeIndex = new int[10000];
        this.maxLevel = maxLevel;
        this.tileCountX = tileCountX;
        this.tileCountZ = tileCountZ;
        levelTiles = new Tile[maxLevel][tileCountX][tileCountZ];
        levelTileCycles = new int[maxLevel][tileCountX + 1][tileCountZ + 1];
        heightmap = ai;
        reset();
    }

    public static void unload() {
        locBuffer = null;
        levelOccluderCount = null;
        levelOccluders = null;
        tileQueue = null;
        visibilityMaps = null;
        visibilityMap = null;
    }

    public void reset() {
        for (int j = 0; j < maxLevel; j++) {
            for (int k = 0; k < tileCountX; k++) {
                for (int i1 = 0; i1 < tileCountZ; i1++) {
                    levelTiles[j][k][i1] = null;
                }
            }
        }

        for (int l = 0; l < MAX_OCCLUDER_LEVELS; l++) {
            for (int j1 = 0; j1 < levelOccluderCount[l]; j1++) {
                levelOccluders[l][j1] = null;
            }

            levelOccluderCount[l] = 0;
        }

        for (int k1 = 0; k1 < locCount; k1++) {
            locs[k1] = null;
        }

        locCount = 0;
        for (int l1 = 0; l1 < locBuffer.length; l1++) {
            locBuffer[l1] = null;
        }
    }

    public void setup(int level) {
        minLevel = level;

        for (int x = 0; x < tileCountX; x++) {
            for (int z = 0; z < tileCountZ; z++) {
                levelTiles[level][x][z] = new Tile(level, x, z);
            }
        }
    }

    public void setBridge(int i, int j) {
        Tile tile = levelTiles[0][j][i];
        for (int k = 0; k < 3; k++) {
            levelTiles[k][j][i] = levelTiles[k + 1][j][i];
            if (levelTiles[k][j][i] != null) {
                levelTiles[k][j][i].level--;
            }
        }

        if (levelTiles[0][j][i] == null) {
            levelTiles[0][j][i] = new Tile(0, j, i);
        }

        levelTiles[0][j][i].bridge = tile;
        levelTiles[3][j][i] = null;
    }

    public static void addOcclude(int maxZ, int minX, int maxY, int type, int maxX, int level, int minY, int minZ) {
        Occluder o = new Occluder();
        o.minTileX = minX / 128;
        o.maxTileX = maxX / 128;
        o.minTileZ = minZ / 128;
        o.maxTileZ = maxZ / 128;
        o.type = type;
        o.minX = minX;
        o.maxX = maxX;
        o.minZ = minZ;
        o.maxZ = maxZ;
        o.minY = minY;
        o.maxY = maxY;
        levelOccluders[level][levelOccluderCount[level]++] = o;
    }

    public void setPhysicalLevel(int level, int tileX, int tileZ, int physicalLevel) {
        Tile tile = levelTiles[level][tileX][tileZ];
        if (tile != null) {
            levelTiles[level][tileX][tileZ].physicalLevel = physicalLevel;
        }
    }

    public void addTile(int i, int j, int k, int type, int i1, int j1, int k1,
                        int l1, int i2, int j2, int k2, int l2, int i3, int j3,
                        int k3, int l3, int i4, int j4, int k4, int l4) {
        if (type == 0) {
            TileUnderlay t = new TileUnderlay(k2, l2, i3, j3, -1, k4, false);
            for (int l = i; l >= 0; l--) {
                if (levelTiles[l][j][k] == null) {
                    levelTiles[l][j][k] = new Tile(l, j, k);
                }
            }

            levelTiles[i][j][k].underlay = t;
            return;
        }

        if (type == 1) {
            TileUnderlay t = new TileUnderlay(k3, l3, i4, j4, j1, l4, k1 == l1 && k1 == i2 && k1 == j2);
            for (int l = i; l >= 0; l--) {
                if (levelTiles[l][j][k] == null) {
                    levelTiles[l][j][k] = new Tile(l, j, k);
                }
            }

            levelTiles[i][j][k].underlay = t;
            return;
        }

        TileOverlay t = new TileOverlay(j, type, l3, l1, i3, i1, k2, j2, l4, k3, j1, j4, k4, i2, i4, j3, k1, k, l2);
        for (int l = i; l >= 0; l--) {
            if (levelTiles[l][j][k] == null) {
                levelTiles[l][j][k] = new Tile(l, j, k);
            }
        }

        levelTiles[i][j][k].overlay = t;
    }

    public void addGroundDecoration(Model m, int tileX, int bitset, int tileZ, int level, byte info, int tileY) {
        GroundDecoration gd = new GroundDecoration();
        gd.model = m;
        gd.sceneX = tileX * 128 + 64;
        gd.sceneZ = tileZ * 128 + 64;
        gd.sceneY = tileY;
        gd.bitset = bitset;
        gd.info = info;

        if (levelTiles[level][tileX][tileZ] == null) {
            levelTiles[level][tileX][tileZ] = new Tile(level, tileX, tileZ);
        }

        levelTiles[level][tileX][tileZ].groundDecoration = gd;
    }

    public void addObject(Model m, Model class38_sub2_sub1_1, int i, int j,
                          int k, int l, int i1,
                          Model class38_sub2_sub1_2) {
        ObjEntity objEntity = new ObjEntity();
        objEntity.entity0 = m;
        objEntity.x = i1 * 128 + 64;
        objEntity.z = l * 128 + 64;
        objEntity.y = i;
        objEntity.bitset = k;
        objEntity.entity1 = class38_sub2_sub1_1;
        objEntity.entity2 = class38_sub2_sub1_2;
        int k1 = 0;
        Tile tile = levelTiles[j][i1][l];
        if (tile != null) {
            for (int l1 = 0; l1 < tile.locationCount; l1++) {
                int i2 = tile.locs[l1].model.anInt1251;
                if (i2 > k1)
                    k1 = i2;
            }

        }
        objEntity.offsetY = k1;
        if (levelTiles[j][i1][l] == null)
            levelTiles[j][i1][l] = new Tile(j, i1, l);
        levelTiles[j][i1][l].objEntity = objEntity;
    }

    public void addWall(int i, int j, int k, int l, Model m1,
                        Model m2,
                        int j1, int k1, int l1, byte byte0) {
        if (m1 == null && m2 == null) {
            return;
        }

        Wall wall = new Wall();
        wall.bitset = k1;
        wall.info = byte0;
        wall.x = j1 * 128 + 64;
        wall.z = l1 * 128 + 64;
        wall.y = j;
        wall.entity0 = m1;
        wall.entity1 = m2;
        wall.type0 = l;
        wall.type1 = i;
        for (int i2 = k; i2 >= 0; i2--)
            if (levelTiles[i2][j1][l1] == null)
                levelTiles[i2][j1][l1] = new Tile(i2, j1, l1);

        levelTiles[k][j1][l1].wall = wall;
    }

    public void addWallDecoration(int i, int j, int k, int l, int i1, int j1,
                                  int l1, int i2, Model model, byte info, int j2) {
        if (model == null)
            return;
        WallDecoration wd = new WallDecoration();
        wd.bitset = l;
        wd.info = info;
        wd.sceneX = i2 * 128 + 64 + l1;
        wd.sceneZ = j * 128 + 64 + k;
        wd.sceneY = i;
        wd.model = model;
        wd.type = j1;
        wd.rotation = i1;
        for (int k2 = j2; k2 >= 0; k2--)
            if (levelTiles[k2][i2][j] == null)
                levelTiles[k2][i2][j] = new Tile(k2, i2, j);

        levelTiles[j2][i2][j].wallDecoration = wd;
    }

    public boolean addLocation(int i, int k, Entity entity, int l, int i1, int j1,
                               int k1, byte byte0, Model m, int l1, int i2) {
        if (m == null && entity == null) {
            return true;
        }

        int j2 = j1 * 128 + 64 * k1;
        int k2 = i1 * 128 + 64 * i2;
        return add(k, j1, i1, k1, i2, j2, k2, i, m, entity, l1, false, l, byte0);
    }

    public boolean add(int j, int k, int l, int i1, int j1, boolean flag,
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
        j2 /= 128;
        k2 /= 128;
        l2 /= 128;
        return add(l1, i2, j2, (k2 - i2) + 1, (l2 - j2) + 1, i1, j, k1, class38_sub2_sub1, entity, l, true,
            j1, (byte) 0);
    }

    public boolean add(int i, Model class38_sub2_sub1, int k, int l, int i1, int j1,
                       int k1, int l1, Entity entity, int i2, int j2, int k2) {
        if (class38_sub2_sub1 == null && entity == null)
            return true;
        else
            return add(i2, l1, k1, (i - l1) + 1, (j2 - k1) + 1, k2, k, l, class38_sub2_sub1, entity, j1,
                true, i1, (byte) 0);
    }

    public boolean add(int i, int j, int k, int l, int i1, int j1, int k1,
                       int l1, Model m, Entity entity, int i2, boolean temporary, int j2,
                       byte byte0) {
        if (m == null && entity == null) {
            return false;
        }

        for (int k2 = j; k2 < j + l; k2++) {
            for (int l2 = k; l2 < k + i1; l2++) {
                if (k2 < 0 || l2 < 0 || k2 >= tileCountX || l2 >= tileCountZ)
                    return false;
                Tile tile = levelTiles[i][k2][l2];
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
        loc.model = m;
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
                    if (levelTiles[l3][i3][j3] == null)
                        levelTiles[l3][i3][j3] = new Tile(l3, i3, j3);

                Tile tile = levelTiles[i][i3][j3];
                tile.locs[tile.locationCount] = loc;
                tile.locFlags[tile.locationCount] = k3;
                tile.flags |= k3;
                tile.locationCount++;
            }

        }

        if (temporary)
            locs[locCount++] = loc;
        return true;
    }

    public void clearFrameLocs() {
        for (int j = 0; j < locCount; j++) {
            Loc loc = locs[j];
            removeLocation(loc);
            locs[j] = null;
        }

        locCount = 0;
    }

    public void removeLocation(Loc loc) {
        for (int i = loc.minSceneTileX; i <= loc.maxSceneTileX; i++) {
            for (int j = loc.minSceneTileZ; j <= loc.maxSceneTileZ; j++) {
                Tile tile = levelTiles[loc.plane][i][j];
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
    }

    public void setLocModel(int i, Model class38_sub2_sub1, int k, int l) {
        if (class38_sub2_sub1 == null)
            return;
        Tile tile = levelTiles[k][i][l];
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

    public void method298(int i, int j, int k, int l) {
        Tile tile = levelTiles[i][k][j];
        if (tile == null)
            return;
        WallDecoration wallDecoration = tile.wallDecoration;
        if (wallDecoration != null) {
            int j1 = k * 128 + 64;
            int k1 = j * 128 + 64;
            wallDecoration.sceneX = j1 + ((wallDecoration.sceneX - j1) * l) / 16;
            wallDecoration.sceneZ = k1 + ((wallDecoration.sceneZ - k1) * l) / 16;
        }
    }

    public void setWallDecorationModel(int j, int k, Model m, int l) {
        if (m == null)
            return;
        Tile tile = levelTiles[l][k][j];
        if (tile == null)
            return;
        WallDecoration wallDecoration = tile.wallDecoration;
        if (wallDecoration == null) {
            return;
        } else {
            wallDecoration.model = m;
            return;
        }
    }

    public void setGroundDecorationModel(Model m, int i, int k, int l) {
        if (m == null)
            return;
        Tile tile = levelTiles[l][k][i];
        if (tile == null)
            return;
        GroundDecoration groundDecoration = tile.groundDecoration;
        if (groundDecoration == null) {
            return;
        } else {
            groundDecoration.model = m;
            return;
        }
    }

    public void setWallModel(Model class38_sub2_sub1, int j, int k, int l) {
        if (class38_sub2_sub1 == null)
            return;
        Tile tile = levelTiles[l][k][j];
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

    public void setWallModels(Model m1, Model m2, int i, int j, int k) {
        if (m1 == null)
            return;
        Tile tile = levelTiles[k][j][i];
        if (tile == null)
            return;
        Wall wall = tile.wall;
        if (wall == null)
            return;
        wall.entity0 = m1;
        wall.entity1 = m2;
    }

    public void removeWall(int i, int j, int k) {
        Tile t = levelTiles[j][i][k];
        if (t != null) {
            t.wall = null;
        }
    }

    public void removeWallDecoration(int i, int j, int l) {
        Tile tile = levelTiles[i][l][j];
        if (tile != null) {
            tile.wallDecoration = null;
        }
    }

    public void removeLocations(int i, int j, int l) {
        Tile tile = levelTiles[l][i][j];
        if (tile == null)
            return;
        for (int i1 = 0; i1 < tile.locationCount; i1++) {
            Loc loc = tile.locs[i1];
            if ((loc.bitset >> 29 & 3) == 2 && loc.minSceneTileX == i && loc.minSceneTileZ == j) {
                removeLocation(loc);
                return;
            }
        }
    }

    public void removeGroundDecoration(int i, int k, int l) {
        Tile tile = levelTiles[i][k][l];
        if (tile != null) {
            tile.groundDecoration = null;
        }
    }

    public void removeObject(int i, int j, int k) {
        Tile tile = levelTiles[i][j][k];
        if (tile != null) {
            tile.objEntity = null;
        }
    }

    public int getWallBitset(int i, int j, int k) {
        Tile tile = levelTiles[i][j][k];
        if (tile == null || tile.wall == null)
            return 0;
        else
            return tile.wall.bitset;
    }

    public int getWallDecorationBitset(int i, int j, int l) {
        Tile tile = levelTiles[i][l][j];
        if (tile == null || tile.wallDecoration == null)
            return 0;
        else
            return tile.wallDecoration.bitset;
    }

    public int getLocationBitset(int i, int j, int k) {
        Tile tile = levelTiles[i][j][k];
        if (tile == null)
            return 0;
        for (int l = 0; l < tile.locationCount; l++) {
            Loc loc = tile.locs[l];
            if ((loc.bitset >> 29 & 3) == 2 && loc.minSceneTileX == j && loc.minSceneTileZ == k)
                return loc.bitset;
        }

        return 0;
    }

    public int getGroundDecorationBitset(int i, int j, int k) {
        Tile tile = levelTiles[i][j][k];
        if (tile == null || tile.groundDecoration == null)
            return 0;
        else
            return tile.groundDecoration.bitset;
    }

    public int getInfo(int i, int j, int k, int l) {
        Tile tile = levelTiles[i][j][k];
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

    public void applyLighting(int i, int j, int k, int l, int i1) {
        int j1 = (int) Math.sqrt(k * k + i * i + i1 * i1);
        int k1 = l * j1 >> 8;
        for (int l1 = 0; l1 < maxLevel; l1++) {
            for (int i2 = 0; i2 < tileCountX; i2++) {
                for (int j2 = 0; j2 < tileCountZ; j2++) {
                    Tile tile = levelTiles[l1][i2][j2];
                    if (tile != null) {
                        Wall wall = tile.wall;
                        if (wall != null && wall.entity0 != null
                            && wall.entity0.vertexNormals != null) {
                            mergeLocNormals(i2, 1, 1, l1, wall.entity0, j2);
                            if (wall.entity1 != null
                                && wall.entity1.vertexNormals != null) {
                                mergeLocNormals(i2, 1, 1, l1, wall.entity1, j2);
                                mergeNormals(wall.entity0, wall.entity1, 0, 0, 0,
                                    false);
                                wall.entity1.calculateLighting(j, k1, k, i, i1);
                            }
                            wall.entity0.calculateLighting(j, k1, k, i, i1);
                        }
                        for (int k2 = 0; k2 < tile.locationCount; k2++) {
                            Loc loc = tile.locs[k2];
                            if (loc != null && loc.model != null
                                && loc.model.vertexNormals != null) {
                                mergeLocNormals(i2, (loc.maxSceneTileX - loc.minSceneTileX) + 1,
                                    (loc.maxSceneTileZ - loc.minSceneTileZ) + 1, l1,
                                    loc.model, j2);
                                loc.model.calculateLighting(j, k1, k, i, i1);
                            }
                        }

                        GroundDecoration groundDecoration = tile.groundDecoration;
                        if (groundDecoration != null && groundDecoration.model.vertexNormals != null) {
                            mergeGroundDecorationNormals(l1, j2, groundDecoration.model, i2);
                            groundDecoration.model.calculateLighting(j, k1, k, i, i1);
                        }
                    }
                }

            }

        }

    }

    public void mergeGroundDecorationNormals(int i, int j, Model class38_sub2_sub1, int k) {
        if (k < tileCountX) {
            Tile tile = levelTiles[i][k + 1][j];
            if (tile != null && tile.groundDecoration != null
                && tile.groundDecoration.model.vertexNormals != null)
                mergeNormals(class38_sub2_sub1, tile.groundDecoration.model, 128, 0, 0, true);
        }
        if (j < tileCountX) {
            Tile tile = levelTiles[i][k][j + 1];
            if (tile != null && tile.groundDecoration != null
                && tile.groundDecoration.model.vertexNormals != null)
                mergeNormals(class38_sub2_sub1, tile.groundDecoration.model, 0, 0, 128, true);
        }
        if (k < tileCountX && j < tileCountZ) {
            Tile tile_2 = levelTiles[i][k + 1][j + 1];
            if (tile_2 != null && tile_2.groundDecoration != null
                && tile_2.groundDecoration.model.vertexNormals != null)
                mergeNormals(class38_sub2_sub1, tile_2.groundDecoration.model, 128, 0, 128, true);
        }
        if (k < tileCountX && j > 0) {
            Tile tile_3 = levelTiles[i][k + 1][j - 1];
            if (tile_3 != null && tile_3.groundDecoration != null
                && tile_3.groundDecoration.model.vertexNormals != null)
                mergeNormals(class38_sub2_sub1, tile_3.groundDecoration.model, 128, 0, -128, true);
        }
    }

    public void mergeLocNormals(int i, int j, int k, int l, Model class38_sub2_sub1, int j1) {
        boolean flag = true;
        int k1 = i;
        int l1 = i + j;
        int i2 = j1 - 1;
        int j2 = j1 + k;
        for (int k2 = l; k2 <= l + 1; k2++)
            if (k2 != maxLevel) {
                for (int l2 = k1; l2 <= l1; l2++)
                    if (l2 >= 0 && l2 < tileCountX) {
                        for (int i3 = i2; i3 <= j2; i3++)
                            if (i3 >= 0 && i3 < tileCountZ && (!flag || l2 >= l1 || i3 >= j2 || i3 < j1 && l2 != i)) {
                                Tile tile = levelTiles[k2][l2][i3];
                                if (tile != null) {
                                    int j3 = (heightmap[k2][l2][i3]
                                        + heightmap[k2][l2 + 1][i3]
                                        + heightmap[k2][l2][i3 + 1]
                                        + heightmap[k2][l2 + 1][i3 + 1]) / 4
                                        - (heightmap[l][i][j1] + heightmap[l][i + 1][j1]
                                        + heightmap[l][i][j1 + 1]
                                        + heightmap[l][i + 1][j1 + 1]) / 4;
                                    Wall wall = tile.wall;
                                    if (wall != null && wall.entity0 != null
                                        && wall.entity0.vertexNormals != null)
                                        mergeNormals(class38_sub2_sub1, wall.entity0,
                                            (l2 - i) * 128 + (1 - j) * 64, j3, (i3 - j1) * 128 + (1 - k) * 64,
                                            flag);
                                    if (wall != null && wall.entity1 != null
                                        && wall.entity1.vertexNormals != null)
                                        mergeNormals(class38_sub2_sub1, wall.entity1,
                                            (l2 - i) * 128 + (1 - j) * 64, j3, (i3 - j1) * 128 + (1 - k) * 64,
                                            flag);
                                    for (int k3 = 0; k3 < tile.locationCount; k3++) {
                                        Loc loc = tile.locs[k3];
                                        if (loc != null && loc.model != null
                                            && loc.model.vertexNormals != null) {
                                            int l3 = (loc.maxSceneTileX - loc.minSceneTileX) + 1;
                                            int i4 = (loc.maxSceneTileZ - loc.minSceneTileZ) + 1;
                                            mergeNormals(class38_sub2_sub1, loc.model,
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

    public void mergeNormals(Model class38_sub2_sub1, Model class38_sub2_sub1_1, int i, int j,
                             int k, boolean hideTriangles) {
        normalMergeIndex++;
        int counter = 0;
        int[] ai = class38_sub2_sub1_1.vertexX;
        int i1 = class38_sub2_sub1_1.vertexCount;
        for (int j1 = 0; j1 < class38_sub2_sub1.vertexCount; j1++) {
            VertexNormal vertexNormal = class38_sub2_sub1.vertexNormals[j1];
            VertexNormal vertexNormal_1 = class38_sub2_sub1.unmodifiedVertexNormals[j1];
            if (vertexNormal_1.magnitude != 0) {
                int i2 = class38_sub2_sub1.vertexY[j1] - j;
                if (i2 <= class38_sub2_sub1_1.minBoundY) {
                    int j2 = class38_sub2_sub1.vertexX[j1] - i;
                    if (j2 >= class38_sub2_sub1_1.minBoundX && j2 <= class38_sub2_sub1_1.maxBoundX) {
                        int k2 = class38_sub2_sub1.vertexZ[j1] - k;
                        if (k2 >= class38_sub2_sub1_1.minBoundZ && k2 <= class38_sub2_sub1_1.maxBoundZ) {
                            for (int l2 = 0; l2 < i1; l2++) {
                                VertexNormal vertexNormal_2 = class38_sub2_sub1_1.vertexNormals[l2];
                                VertexNormal vertexNormal_3 = class38_sub2_sub1_1.unmodifiedVertexNormals[l2];
                                if (j2 == ai[l2] && k2 == class38_sub2_sub1_1.vertexZ[l2]
                                    && i2 == class38_sub2_sub1_1.vertexY[l2] && vertexNormal_3.magnitude != 0) {
                                    vertexNormal.x += vertexNormal_3.x;
                                    vertexNormal.y += vertexNormal_3.y;
                                    vertexNormal.z += vertexNormal_3.z;
                                    vertexNormal.magnitude += vertexNormal_3.magnitude;
                                    vertexNormal_2.x += vertexNormal_1.x;
                                    vertexNormal_2.y += vertexNormal_1.y;
                                    vertexNormal_2.z += vertexNormal_1.z;
                                    vertexNormal_2.magnitude += vertexNormal_1.magnitude;
                                    counter++;
                                    vertexAMergeIndex[j1] = normalMergeIndex;
                                    vertexBMergeIndex[l2] = normalMergeIndex;
                                }
                            }

                        }
                    }
                }
            }
        }

        if (counter < 3 || !hideTriangles)
            return;
        for (int k1 = 0; k1 < class38_sub2_sub1.triangleCount; k1++)
            if (vertexAMergeIndex[class38_sub2_sub1.triangleVertexA[k1]] == normalMergeIndex
                && vertexAMergeIndex[class38_sub2_sub1.triangleVertexB[k1]] == normalMergeIndex
                && vertexAMergeIndex[class38_sub2_sub1.triangleVertexC[k1]] == normalMergeIndex)
                class38_sub2_sub1.triangleInfo[k1] = -1;

        for (int l1 = 0; l1 < class38_sub2_sub1_1.triangleCount; l1++)
            if (vertexBMergeIndex[class38_sub2_sub1_1.triangleVertexA[l1]] == normalMergeIndex
                && vertexBMergeIndex[class38_sub2_sub1_1.triangleVertexB[l1]] == normalMergeIndex
                && vertexBMergeIndex[class38_sub2_sub1_1.triangleVertexC[l1]] == normalMergeIndex)
                class38_sub2_sub1_1.triangleInfo[l1] = -1;

    }

    public void drawMinimapTile(int[] ai, int i, int j, int k, int l, int i1) {
        Tile tile = levelTiles[k][l][i1];
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
        TileOverlay tileOverlay = tile.overlay;
        if (tileOverlay == null)
            return;
        int l1 = tileOverlay.shape;
        int i2 = tileOverlay.rotation;
        int j2 = tileOverlay.underlayRGB;
        int k2 = tileOverlay.overlayRGB;
        int[] ai1 = TILE_MASK_2D[l1];
        int[] ai2 = TILE_ROTATION_2D[i2];
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

    public static void init(int[] ai, int i, int j, int k, int l) {
        viewportLeft = 0;
        viewportTop = 0;
        viewportRight = j;
        viewportBottom = k;
        viewportCenterX = j / 2;
        viewportCenterY = k / 2;
        boolean[][][][] visibilityMap = new boolean[9][32][53][53];
        for (int i1 = 128; i1 <= 384; i1 += 32) {
            for (int j1 = 0; j1 < 2048; j1 += 64) {
                pitchSin = Model.sin[i1];
                pitchCos = Model.cos[i1];
                yawSin = Model.sin[j1];
                yawCos = Model.cos[j1];
                int l1 = (i1 - 128) / 32;
                int j2 = j1 / 64;
                for (int l2 = -26; l2 <= 26; l2++) {
                    for (int j3 = -26; j3 <= 26; j3++) {
                        int k3 = l2 * 128;
                        int i4 = j3 * 128;
                        boolean flag1 = false;
                        for (int k4 = -l; k4 <= i; k4 += 128) {
                            if (!isPointVisible(k3, i4, ai[l1] + k4))
                                continue;
                            flag1 = true;
                            break;
                        }

                        visibilityMap[l1][j2][l2 + 25 + 1][j3 + 25 + 1] = flag1;
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
                                if (visibilityMap[k1][i2][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1])
                                    flag = true;
                                else if (visibilityMap[k1][(i2 + 1) % 31][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1])
                                    flag = true;
                                else if (visibilityMap[k1 + 1][i2][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1]) {
                                    flag = true;
                                } else {
                                    if (!visibilityMap[k1 + 1][(i2 + 1) % 31][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1])
                                        continue;
                                    flag = true;
                                }
                                break label0;
                            }

                        }

                        visibilityMaps[k1][i2][k2 + 25][i3 + 25] = flag;
                    }

                }

            }
        }

    }

    public static boolean isPointVisible(int i, int j, int k) {
        int i1 = j * yawSin + i * yawCos >> 16;
        int j1 = j * yawCos - i * yawSin >> 16;
        int k1 = k * pitchSin + j1 * pitchCos >> 16;
        int l1 = k * pitchCos - j1 * pitchSin >> 16;
        if (k1 < 50 || k1 > 3500)
            return false;
        int j2 = viewportCenterX + (i1 << 9) / k1;
        int k2 = viewportCenterY + (l1 << 9) / k1;
        return j2 >= viewportLeft && j2 <= viewportRight && k2 >= viewportTop && k2 <= viewportBottom;
    }

    public void setClick(int i, int j, int k) {
        checkClick = true;
        clickX = k;
        clickY = j;
        clickedTileX = -1;
        clickedTileZ = -1;
    }

    public void draw(int i, int j, int k, int l, int i1, int j1) {
        if (j < 0)
            j = 0;
        else if (j >= tileCountX * 128)
            j = tileCountX * 128 - 1;
        if (j1 < 0)
            j1 = 0;
        else if (j1 >= tileCountZ * 128)
            j1 = tileCountZ * 128 - 1;
        activeLevel++;
        pitchSin = Model.sin[l];
        pitchCos = Model.cos[l];
        yawSin = Model.sin[i];
        yawCos = Model.cos[i];
        visibilityMap = visibilityMaps[(l - 128) / 32][i / 64];
        cameraTileZ = j;
        cameraX = i1;
        cameraY = j1;
        maxTileZ = j / 128;
        cameraTileX = j1 / 128;
        tileUpdateCount = k;
        cycle = maxTileZ - 25;
        if (cycle < 0)
            cycle = 0;
        maxTileX = cameraTileX - 25;
        if (maxTileX < 0)
            maxTileX = 0;
        minTileX = maxTileZ + 25;
        if (minTileX > tileCountX)
            minTileX = tileCountX;
        minTileZ = cameraTileX + 25;
        if (minTileZ > tileCountZ)
            minTileZ = tileCountZ;
        updateOccluders();
        lastTileUpdateCount = 0;
        for (int l1 = minLevel; l1 < maxLevel; l1++) {
            Tile[][] aclass38_sub1 = levelTiles[l1];
            for (int j2 = cycle; j2 < minTileX; j2++) {
                for (int l2 = maxTileX; l2 < minTileZ; l2++) {
                    Tile tile = aclass38_sub1[j2][l2];
                    if (tile != null)
                        if (tile.physicalLevel > k
                            || !visibilityMap[(j2 - maxTileZ) + 25][(l2 - cameraTileX) + 25]
                            && heightmap[l1][j2][l2] - i1 < 2000) {
                            tile.draw = false;
                            tile.isVisible = false;
                            tile.wallCullDirection = 0;
                        } else {
                            tile.draw = true;
                            tile.isVisible = true;
                            tile.drawLocations = tile.locationCount > 0;
                            lastTileUpdateCount++;
                        }
                }

            }

        }

        for (int i2 = minLevel; i2 < maxLevel; i2++) {
            Tile[][] aclass38_sub1_1 = levelTiles[i2];
            for (int i3 = -25; i3 <= 0; i3++) {
                int j3 = maxTileZ + i3;
                int l3 = maxTileZ - i3;
                if (j3 >= cycle || l3 < minTileX) {
                    for (int j4 = -25; j4 <= 0; j4++) {
                        int l4 = cameraTileX + j4;
                        int j5 = cameraTileX - j4;
                        if (j3 >= cycle) {
                            if (l4 >= maxTileX) {
                                Tile tile = aclass38_sub1_1[j3][l4];
                                if (tile != null && tile.draw)
                                    draw(tile, true);
                            }
                            if (j5 < minTileZ) {
                                Tile tile_2 = aclass38_sub1_1[j3][j5];
                                if (tile_2 != null && tile_2.draw)
                                    draw(tile_2, true);
                            }
                        }
                        if (l3 < minTileX) {
                            if (l4 >= maxTileX) {
                                Tile tile_3 = aclass38_sub1_1[l3][l4];
                                if (tile_3 != null && tile_3.draw)
                                    draw(tile_3, true);
                            }
                            if (j5 < minTileZ) {
                                Tile tile_4 = aclass38_sub1_1[l3][j5];
                                if (tile_4 != null && tile_4.draw)
                                    draw(tile_4, true);
                            }
                        }
                        if (lastTileUpdateCount == 0) {
                            checkClick = false;
                            return;
                        }
                    }

                }
            }

        }

        for (int k2 = minLevel; k2 < maxLevel; k2++) {
            Tile[][] aclass38_sub1_2 = levelTiles[k2];
            for (int k3 = -25; k3 <= 0; k3++) {
                int i4 = maxTileZ + k3;
                int k4 = maxTileZ - k3;
                if (i4 >= cycle || k4 < minTileX) {
                    for (int i5 = -25; i5 <= 0; i5++) {
                        int k5 = cameraTileX + i5;
                        int l5 = cameraTileX - i5;
                        if (i4 >= cycle) {
                            if (k5 >= maxTileX) {
                                Tile tile_5 = aclass38_sub1_2[i4][k5];
                                if (tile_5 != null && tile_5.draw)
                                    draw(tile_5, false);
                            }
                            if (l5 < minTileZ) {
                                Tile tile_6 = aclass38_sub1_2[i4][l5];
                                if (tile_6 != null && tile_6.draw)
                                    draw(tile_6, false);
                            }
                        }
                        if (k4 < minTileX) {
                            if (k5 >= maxTileX) {
                                Tile tile_7 = aclass38_sub1_2[k4][k5];
                                if (tile_7 != null && tile_7.draw)
                                    draw(tile_7, false);
                            }
                            if (l5 < minTileZ) {
                                Tile tile_8 = aclass38_sub1_2[k4][l5];
                                if (tile_8 != null && tile_8.draw)
                                    draw(tile_8, false);
                            }
                        }
                        if (lastTileUpdateCount == 0) {
                            checkClick = false;
                            return;
                        }
                    }

                }
            }

        }

    }

    public void draw(Tile tile_1, boolean flag) {
        tileQueue.pushNext(tile_1);
        do {
            Tile tile;
            do {
                tile = (Tile) tileQueue.poll();
                if (tile == null)
                    return;
            } while (!tile.isVisible);
            int i = tile.x;
            int j = tile.z;
            int k = tile.level;
            int l = tile.renderLevel;
            Tile[][] aclass38_sub1 = levelTiles[k];
            if (tile.draw) {
                if (flag) {
                    if (k > 0) {
                        Tile tile_2 = levelTiles[k - 1][i][j];
                        if (tile_2 != null && tile_2.isVisible)
                            continue;
                    }
                    if (i <= maxTileZ && i > cycle) {
                        Tile tile_3 = aclass38_sub1[i - 1][j];
                        if (tile_3 != null && tile_3.isVisible
                            && (tile_3.draw || (tile.flags & 1) == 0))
                            continue;
                    }
                    if (i >= maxTileZ && i < minTileX - 1) {
                        Tile tile_4 = aclass38_sub1[i + 1][j];
                        if (tile_4 != null && tile_4.isVisible
                            && (tile_4.draw || (tile.flags & 4) == 0))
                            continue;
                    }
                    if (j <= cameraTileX && j > maxTileX) {
                        Tile tile_5 = aclass38_sub1[i][j - 1];
                        if (tile_5 != null && tile_5.isVisible
                            && (tile_5.draw || (tile.flags & 8) == 0))
                            continue;
                    }
                    if (j >= cameraTileX && j < minTileZ - 1) {
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
                        if (!isTileOccluded(0, i, j))
                            drawTileUnderlay(tile_7.underlay, 0, pitchSin, pitchCos, yawSin, yawCos, i, j);
                    } else if (tile_7.overlay != null && !isTileOccluded(0, i, j))
                        drawTileOverlay(yawSin, j, tile_7.overlay, i, pitchCos, pitchSin, yawCos, true);
                    Wall wall = tile_7.wall;
                    if (wall != null)
                        wall.entity0.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                            wall.x - cameraTileZ, wall.y - cameraX, wall.z - cameraY,
                            wall.bitset);
                    for (int i2 = 0; i2 < tile_7.locationCount; i2++) {
                        Loc loc = tile_7.locs[i2];
                        if (loc != null) {
                            Model class38_sub2_sub1 = loc.model;
                            if (class38_sub2_sub1 == null)
                                class38_sub2_sub1 = loc.entity.getDrawMethod();
                            class38_sub2_sub1.method371(loc.yaw, pitchSin, pitchCos, yawSin, yawCos,
                                loc.x - cameraTileZ, loc.y - cameraX,
                                loc.z - cameraY, loc.bitset);
                        }
                    }

                }
                boolean flag1 = false;
                if (tile.underlay != null) {
                    if (!isTileOccluded(l, i, j)) {
                        flag1 = true;
                        drawTileUnderlay(tile.underlay, l, pitchSin, pitchCos, yawSin, yawCos, i, j);
                    }
                } else if (tile.overlay != null && !isTileOccluded(l, i, j)) {
                    flag1 = true;
                    drawTileOverlay(yawSin, j, tile.overlay, i, pitchCos, pitchSin, yawCos, true);
                }
                int j1 = 0;
                int j2 = 0;
                Wall wall_3 = tile.wall;
                WallDecoration wallDecoration_1 = tile.wallDecoration;
                if (wall_3 != null || wallDecoration_1 != null) {
                    if (maxTileZ == i)
                        j1++;
                    else if (maxTileZ < i)
                        j1 += 2;
                    if (cameraTileX == j)
                        j1 += 3;
                    else if (cameraTileX > j)
                        j1 += 6;
                    j2 = TILE_WALL_DRAW_FLAGS_0[j1];
                    tile.wallDrawFlags = TILE_WALL_DRAW_FLAGS_1[j1];
                }
                if (wall_3 != null) {
                    if ((wall_3.type0 & WALL_DRAW_FLAGS[j1]) != 0) {
                        if (wall_3.type0 == 16) {
                            tile.wallCullDirection = 3;
                            tile.wallUncullDirection = WALL_UNCULL_FLAGS_0[j1];
                            tile.wallCullOppositeDirection = 3 - tile.wallUncullDirection;
                        } else if (wall_3.type0 == 32) {
                            tile.wallCullDirection = 6;
                            tile.wallUncullDirection = WALL_UNCULL_FLAGS_1[j1];
                            tile.wallCullOppositeDirection = 6 - tile.wallUncullDirection;
                        } else if (wall_3.type0 == 64) {
                            tile.wallCullDirection = 12;
                            tile.wallUncullDirection = WALL_UNCULL_FLAGS_2[j1];
                            tile.wallCullOppositeDirection = 12 - tile.wallUncullDirection;
                        } else {
                            tile.wallCullDirection = 9;
                            tile.wallUncullDirection = WALL_UNCULL_FLAGS_3[j1];
                            tile.wallCullOppositeDirection = 9 - tile.wallUncullDirection;
                        }
                    } else {
                        tile.wallCullDirection = 0;
                    }
                    if ((wall_3.type0 & j2) != 0 && !isWallOccluded(l, i, j, wall_3.type0))
                        wall_3.entity0.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                            wall_3.x - cameraTileZ, wall_3.y - cameraX,
                            wall_3.z - cameraY, wall_3.bitset);
                    if ((wall_3.type1 & j2) != 0 && !isWallOccluded(l, i, j, wall_3.type1))
                        wall_3.entity1.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                            wall_3.x - cameraTileZ, wall_3.y - cameraX,
                            wall_3.z - cameraY, wall_3.bitset);
                }
                if (wallDecoration_1 != null && !isOccluded(l, i, j, wallDecoration_1.model.maxBoundY))
                    if ((wallDecoration_1.type & j2) != 0)
                        wallDecoration_1.model.method371(wallDecoration_1.rotation, pitchSin, pitchCos, yawSin,
                            yawCos, wallDecoration_1.sceneX - cameraTileZ, wallDecoration_1.sceneY - cameraX,
                            wallDecoration_1.sceneZ - cameraY, wallDecoration_1.bitset);
                    else if ((wallDecoration_1.type & 0x300) != 0) {
                        int j4 = wallDecoration_1.sceneX - cameraTileZ;
                        int l5 = wallDecoration_1.sceneY - cameraX;
                        int k6 = wallDecoration_1.sceneZ - cameraY;
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
                            int i10 = j4 + DECO_TYPE1_OFFSET_X[k7];
                            int k10 = k6 + DECO_TYPE1_OFFSET_Z[k7];
                            wallDecoration_1.model.method371(k7 * 512 + 256, pitchSin, pitchCos, yawSin,
                                yawCos, i10, l5, k10, wallDecoration_1.bitset);
                        }
                        if ((wallDecoration_1.type & 0x200) != 0 && l9 > l8) {
                            int j10 = j4 + DECO_TYPE2_OFFSET_X[k7];
                            int l10 = k6 + DECO_TYPE2_OFFSET_Z[k7];
                            wallDecoration_1.model.method371(k7 * 512 + 1280 & 0x7ff, pitchSin, pitchCos,
                                yawSin, yawCos, j10, l5, l10, wallDecoration_1.bitset);
                        }
                    }
                if (flag1) {
                    GroundDecoration groundDecoration = tile.groundDecoration;
                    if (groundDecoration != null)
                        groundDecoration.model.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                            groundDecoration.sceneX - cameraTileZ, groundDecoration.sceneY - cameraX, groundDecoration.sceneZ - cameraY,
                            groundDecoration.bitset);
                    ObjEntity objEntity_1 = tile.objEntity;
                    if (objEntity_1 != null && objEntity_1.offsetY == 0) {
                        if (objEntity_1.entity1 != null)
                            objEntity_1.entity1.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                                objEntity_1.x - cameraTileZ, objEntity_1.y - cameraX,
                                objEntity_1.z - cameraY, objEntity_1.bitset);
                        if (objEntity_1.entity2 != null)
                            objEntity_1.entity2.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                                objEntity_1.x - cameraTileZ, objEntity_1.y - cameraX,
                                objEntity_1.z - cameraY, objEntity_1.bitset);
                        if (objEntity_1.entity0 != null)
                            objEntity_1.entity0.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                                objEntity_1.x - cameraTileZ, objEntity_1.y - cameraX,
                                objEntity_1.z - cameraY, objEntity_1.bitset);
                    }
                }
                int k4 = tile.flags;
                if (k4 != 0) {
                    if (i < maxTileZ && (k4 & 4) != 0) {
                        Tile tile_17 = aclass38_sub1[i + 1][j];
                        if (tile_17 != null && tile_17.isVisible)
                            tileQueue.pushNext(tile_17);
                    }
                    if (j < cameraTileX && (k4 & 2) != 0) {
                        Tile tile_18 = aclass38_sub1[i][j + 1];
                        if (tile_18 != null && tile_18.isVisible)
                            tileQueue.pushNext(tile_18);
                    }
                    if (i > maxTileZ && (k4 & 1) != 0) {
                        Tile tile_19 = aclass38_sub1[i - 1][j];
                        if (tile_19 != null && tile_19.isVisible)
                            tileQueue.pushNext(tile_19);
                    }
                    if (j > cameraTileX && (k4 & 8) != 0) {
                        Tile tile_20 = aclass38_sub1[i][j - 1];
                        if (tile_20 != null && tile_20.isVisible)
                            tileQueue.pushNext(tile_20);
                    }
                }
            }
            if (tile.wallCullDirection != 0) {
                boolean flag2 = true;
                for (int k1 = 0; k1 < tile.locationCount; k1++) {
                    if (tile.locs[k1].cycle == activeLevel || (tile.locFlags[k1]
                        & tile.wallCullDirection) != tile.wallUncullDirection)
                        continue;
                    flag2 = false;
                    break;
                }

                if (flag2) {
                    Wall wall_1 = tile.wall;
                    if (!isWallOccluded(l, i, j, wall_1.type0))
                        wall_1.entity0.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                            wall_1.x - cameraTileZ, wall_1.y - cameraX,
                            wall_1.z - cameraY, wall_1.bitset);
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
                    if (loc_1.cycle == activeLevel)
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

                    locBuffer[l1++] = loc_1;
                    int i5 = maxTileZ - loc_1.minSceneTileX;
                    int i6 = loc_1.maxSceneTileX - maxTileZ;
                    if (i6 > i5)
                        i5 = i6;
                    int i7 = cameraTileX - loc_1.minSceneTileZ;
                    int l7 = loc_1.maxSceneTileZ - cameraTileX;
                    if (l7 > i7)
                        loc_1.distance = i5 + l7;
                    else
                        loc_1.distance = i5 + i7;
                }

                while (l1 > 0) {
                    int i3 = -50;
                    int l3 = -1;
                    for (int j5 = 0; j5 < l1; j5++) {
                        Loc loc_2 = locBuffer[j5];
                        if (loc_2.distance > i3 && loc_2.cycle != activeLevel) {
                            i3 = loc_2.distance;
                            l3 = j5;
                        }
                    }

                    if (l3 == -1)
                        break;
                    Loc loc_3 = locBuffer[l3];
                    loc_3.cycle = activeLevel;
                    Model class38_sub2_sub1_1 = loc_3.model;
                    if (class38_sub2_sub1_1 == null)
                        class38_sub2_sub1_1 = loc_3.entity.getDrawMethod();
                    if (!isAreaOccluded(l, loc_3.minSceneTileX, loc_3.maxSceneTileX, loc_3.minSceneTileZ, loc_3.maxSceneTileZ,
                        class38_sub2_sub1_1.maxBoundY))
                        class38_sub2_sub1_1.method371(loc_3.yaw, pitchSin, pitchCos, yawSin, yawCos,
                            loc_3.x - cameraTileZ, loc_3.y - cameraX,
                            loc_3.z - cameraY, loc_3.bitset);
                    for (int i8 = loc_3.minSceneTileX; i8 <= loc_3.maxSceneTileX; i8++) {
                        for (int i9 = loc_3.minSceneTileZ; i9 <= loc_3.maxSceneTileZ; i9++) {
                            Tile tile_22 = aclass38_sub1[i8][i9];
                            if (tile_22.wallCullDirection != 0)
                                tileQueue.pushNext(tile_22);
                            else if ((i8 != i || i9 != j) && tile_22.isVisible)
                                tileQueue.pushNext(tile_22);
                        }

                    }

                }
                if (tile.drawLocations)
                    continue;
            }
            if (!tile.isVisible || tile.wallCullDirection != 0)
                continue;
            if (i <= maxTileZ && i > cycle) {
                Tile tile_8 = aclass38_sub1[i - 1][j];
                if (tile_8 != null && tile_8.isVisible)
                    continue;
            }
            if (i >= maxTileZ && i < minTileX - 1) {
                Tile tile_9 = aclass38_sub1[i + 1][j];
                if (tile_9 != null && tile_9.isVisible)
                    continue;
            }
            if (j <= cameraTileX && j > maxTileX) {
                Tile tile_10 = aclass38_sub1[i][j - 1];
                if (tile_10 != null && tile_10.isVisible)
                    continue;
            }
            if (j >= cameraTileX && j < minTileZ - 1) {
                Tile tile_11 = aclass38_sub1[i][j + 1];
                if (tile_11 != null && tile_11.isVisible)
                    continue;
            }
            tile.isVisible = false;
            lastTileUpdateCount--;
            ObjEntity objEntity = tile.objEntity;
            if (objEntity != null && objEntity.offsetY != 0) {
                if (objEntity.entity1 != null)
                    objEntity.entity1.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                        objEntity.x - cameraTileZ, objEntity.y - cameraX - objEntity.offsetY,
                        objEntity.z - cameraY, objEntity.bitset);
                if (objEntity.entity2 != null)
                    objEntity.entity2.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                        objEntity.x - cameraTileZ, objEntity.y - cameraX - objEntity.offsetY,
                        objEntity.z - cameraY, objEntity.bitset);
                if (objEntity.entity0 != null)
                    objEntity.entity0.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                        objEntity.x - cameraTileZ, objEntity.y - cameraX - objEntity.offsetY,
                        objEntity.z - cameraY, objEntity.bitset);
            }
            if (tile.wallDrawFlags != 0) {
                WallDecoration wallDecoration = tile.wallDecoration;
                if (wallDecoration != null && !isOccluded(l, i, j, wallDecoration.model.maxBoundY))
                    if ((wallDecoration.type & tile.wallDrawFlags) != 0)
                        wallDecoration.model.method371(wallDecoration.rotation, pitchSin, pitchCos, yawSin,
                            yawCos, wallDecoration.sceneX - cameraTileZ, wallDecoration.sceneY - cameraX,
                            wallDecoration.sceneZ - cameraY, wallDecoration.bitset);
                    else if ((wallDecoration.type & 0x300) != 0) {
                        int l2 = wallDecoration.sceneX - cameraTileZ;
                        int j3 = wallDecoration.sceneY - cameraX;
                        int i4 = wallDecoration.sceneZ - cameraY;
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
                            int j8 = l2 + DECO_TYPE1_OFFSET_X[k5];
                            int j9 = i4 + DECO_TYPE1_OFFSET_Z[k5];
                            wallDecoration.model.method371(k5 * 512 + 256, pitchSin, pitchCos, yawSin,
                                yawCos, j8, j3, j9, wallDecoration.bitset);
                        }
                        if ((wallDecoration.type & 0x200) != 0 && j7 <= j6) {
                            int k8 = l2 + DECO_TYPE2_OFFSET_X[k5];
                            int k9 = i4 + DECO_TYPE2_OFFSET_Z[k5];
                            wallDecoration.model.method371(k5 * 512 + 1280 & 0x7ff, pitchSin, pitchCos,
                                yawSin, yawCos, k8, j3, k9, wallDecoration.bitset);
                        }
                    }
                Wall wall_2 = tile.wall;
                if (wall_2 != null) {
                    if ((wall_2.type1 & tile.wallDrawFlags) != 0 && !isWallOccluded(l, i, j, wall_2.type1))
                        wall_2.entity1.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                            wall_2.x - cameraTileZ, wall_2.y - cameraX,
                            wall_2.z - cameraY, wall_2.bitset);
                    if ((wall_2.type0 & tile.wallDrawFlags) != 0 && !isWallOccluded(l, i, j, wall_2.type0))
                        wall_2.entity0.method371(0, pitchSin, pitchCos, yawSin, yawCos,
                            wall_2.x - cameraTileZ, wall_2.y - cameraX,
                            wall_2.z - cameraY, wall_2.bitset);
                }
            }
            if (k < maxLevel - 1) {
                Tile tile_12 = levelTiles[k + 1][i][j];
                if (tile_12 != null && tile_12.isVisible)
                    tileQueue.pushNext(tile_12);
            }
            if (i < maxTileZ) {
                Tile tile_13 = aclass38_sub1[i + 1][j];
                if (tile_13 != null && tile_13.isVisible)
                    tileQueue.pushNext(tile_13);
            }
            if (j < cameraTileX) {
                Tile tile_14 = aclass38_sub1[i][j + 1];
                if (tile_14 != null && tile_14.isVisible)
                    tileQueue.pushNext(tile_14);
            }
            if (i > maxTileZ) {
                Tile tile_15 = aclass38_sub1[i - 1][j];
                if (tile_15 != null && tile_15.isVisible)
                    tileQueue.pushNext(tile_15);
            }
            if (j > cameraTileX) {
                Tile tile_16 = aclass38_sub1[i][j - 1];
                if (tile_16 != null && tile_16.isVisible)
                    tileQueue.pushNext(tile_16);
            }
        } while (true);
    }

    public void drawTileUnderlay(TileUnderlay tileUnderlay, int i, int j, int k, int l, int i1, int j1,
                                 int k1) {
        int l1;
        int i2 = l1 = (j1 << 7) - cameraTileZ;
        int j2;
        int k2 = j2 = (k1 << 7) - cameraY;
        int l2;
        int i3 = l2 = i2 + 128;
        int j3;
        int k3 = j3 = k2 + 128;
        int l3 = heightmap[i][j1][k1] - cameraX;
        int i4 = heightmap[i][j1 + 1][k1] - cameraX;
        int j4 = heightmap[i][j1 + 1][k1 + 1] - cameraX;
        int k4 = heightmap[i][j1][k1 + 1] - cameraX;
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
        int i5 = Draw3D.centerX + (i2 << 9) / k2;
        int j5 = Draw3D.centerY + (l3 << 9) / k2;
        int k5 = Draw3D.centerX + (i3 << 9) / j2;
        int l5 = Draw3D.centerY + (i4 << 9) / j2;
        int i6 = Draw3D.centerX + (l2 << 9) / k3;
        int j6 = Draw3D.centerY + (j4 << 9) / k3;
        int k6 = Draw3D.centerX + (l1 << 9) / j3;
        int l6 = Draw3D.centerY + (k4 << 9) / j3;
        Draw3D.alpha = 0;
        if ((i6 - k6) * (l5 - l6) - (j6 - l6) * (k5 - k6) > 0) {
            Draw3D.testX = i6 < 0 || k6 < 0 || k5 < 0 || i6 > Draw2D.rightX || k6 > Draw2D.rightX
                || k5 > Draw2D.rightX;
            if (checkClick && withinTriangle(clickX, clickY, j6, l6, l5, i6, k6, k5)) {
                clickedTileX = j1;
                clickedTileZ = k1;
            }
            if (tileUnderlay.textureIndex == -1) {
                if (tileUnderlay.northeastColor != 0xbc614e)
                    Draw3D.fillGouraudScanline(j6, l6, l5, i6, k6, k5, tileUnderlay.northeastColor, tileUnderlay.northwestColor,
                        tileUnderlay.southeastColor);
            } else if (!lowMemory) {
                if (tileUnderlay.isFlat)
                    Draw3D.fillTexturedTriangle(j6, l6, l5, i6, k6, k5, tileUnderlay.northeastColor, tileUnderlay.northwestColor,
                        tileUnderlay.southeastColor, i2, i3, l1, l3, i4, k4, k2, j2, j3, tileUnderlay.textureIndex);
                else
                    Draw3D.fillTexturedTriangle(j6, l6, l5, i6, k6, k5, tileUnderlay.northeastColor, tileUnderlay.northwestColor,
                        tileUnderlay.southeastColor, l2, l1, i3, j4, k4, i4, k3, j3, j2, tileUnderlay.textureIndex);
            } else {
                int i7 = TEXTURE_HSL[tileUnderlay.textureIndex];
                Draw3D.fillGouraudScanline(j6, l6, l5, i6, k6, k5, adjustHSLLightness(tileUnderlay.northeastColor, i7),
                    adjustHSLLightness(tileUnderlay.northwestColor, i7), adjustHSLLightness(tileUnderlay.southeastColor, i7));
            }
        }
        if ((i5 - k5) * (l6 - l5) - (j5 - l5) * (k6 - k5) > 0) {
            Draw3D.testX = i5 < 0 || k5 < 0 || k6 < 0 || i5 > Draw2D.rightX || k5 > Draw2D.rightX
                || k6 > Draw2D.rightX;
            if (checkClick && withinTriangle(clickX, clickY, j5, l5, l6, i5, k5, k6)) {
                clickedTileX = j1;
                clickedTileZ = k1;
            }
            if (tileUnderlay.textureIndex == -1) {
                if (tileUnderlay.southwestColor != 0xbc614e) {
                    Draw3D.fillGouraudScanline(j5, l5, l6, i5, k5, k6, tileUnderlay.southwestColor, tileUnderlay.southeastColor,
                        tileUnderlay.northwestColor);
                    return;
                }
            } else {
                if (!lowMemory) {
                    Draw3D.fillTexturedTriangle(j5, l5, l6, i5, k5, k6, tileUnderlay.southwestColor, tileUnderlay.southeastColor,
                        tileUnderlay.northwestColor, i2, i3, l1, l3, i4, k4, k2, j2, j3, tileUnderlay.textureIndex);
                    return;
                }
                int j7 = TEXTURE_HSL[tileUnderlay.textureIndex];
                Draw3D.fillGouraudScanline(j5, l5, l6, i5, k5, k6, adjustHSLLightness(tileUnderlay.southwestColor, j7),
                    adjustHSLLightness(tileUnderlay.southeastColor, j7), adjustHSLLightness(tileUnderlay.northwestColor, j7));
            }
        }
    }

    public void drawTileOverlay(int i, int j, TileOverlay tileOverlay, int k, int l, int i1, int j1,
                                boolean flag) {
        int k1 = tileOverlay.vertexX.length;
        for (int l1 = 0; l1 < k1; l1++) {
            int i2 = tileOverlay.vertexX[l1] - cameraTileZ;
            int k2 = tileOverlay.vertexY[l1] - cameraX;
            int i3 = tileOverlay.vertexZ[l1] - cameraY;
            int k3 = i3 * i + i2 * j1 >> 16;
            i3 = i3 * j1 - i2 * i >> 16;
            i2 = k3;
            k3 = k2 * l - i3 * i1 >> 16;
            i3 = k2 * i1 + i3 * l >> 16;
            k2 = k3;
            if (i3 < 50)
                return;
            if (tileOverlay.triangleTextureIndex != null) {
                TileOverlay.vertexSceneX[l1] = i2;
                TileOverlay.vertexSceneY[l1] = k2;
                TileOverlay.vertexSceneZ[l1] = i3;
            }
            TileOverlay.tmpScreenX[l1] = Draw3D.centerX + (i2 << 9) / i3;
            TileOverlay.tmpScreenY[l1] = Draw3D.centerY + (k2 << 9) / i3;
        }

        Draw3D.alpha = 0;
        k1 = tileOverlay.triangleVertexA.length;
        if (!flag)
            return;
        for (int j2 = 0; j2 < k1; j2++) {
            int l2 = tileOverlay.triangleVertexA[j2];
            int j3 = tileOverlay.triangleVertexB[j2];
            int l3 = tileOverlay.triangleVertexC[j2];
            int i4 = TileOverlay.tmpScreenX[l2];
            int j4 = TileOverlay.tmpScreenX[j3];
            int k4 = TileOverlay.tmpScreenX[l3];
            int l4 = TileOverlay.tmpScreenY[l2];
            int i5 = TileOverlay.tmpScreenY[j3];
            int j5 = TileOverlay.tmpScreenY[l3];
            if ((i4 - j4) * (j5 - i5) - (l4 - i5) * (k4 - j4) > 0) {
                Draw3D.testX = i4 < 0 || j4 < 0 || k4 < 0 || i4 > Draw2D.rightX || j4 > Draw2D.rightX
                    || k4 > Draw2D.rightX;
                if (checkClick && withinTriangle(clickX, clickY, l4, i5, j5, i4, j4, k4)) {
                    clickedTileX = k;
                    clickedTileZ = j;
                }
                if (tileOverlay.triangleTextureIndex == null || tileOverlay.triangleTextureIndex[j2] == -1) {
                    if (tileOverlay.triangleColorA[j2] != 0xbc614e)
                        Draw3D.fillGouraudScanline(l4, i5, j5, i4, j4, k4, tileOverlay.triangleColorA[j2],
                            tileOverlay.triangleColorB[j2], tileOverlay.triangleColorC[j2]);
                } else if (!lowMemory) {
                    if (tileOverlay.isFlat)
                        Draw3D.fillTexturedTriangle(l4, i5, j5, i4, j4, k4, tileOverlay.triangleColorA[j2],
                            tileOverlay.triangleColorB[j2], tileOverlay.triangleColorC[j2], TileOverlay.vertexSceneX[0],
                            TileOverlay.vertexSceneX[1], TileOverlay.vertexSceneX[3], TileOverlay.vertexSceneY[0],
                            TileOverlay.vertexSceneY[1], TileOverlay.vertexSceneY[3], TileOverlay.vertexSceneZ[0],
                            TileOverlay.vertexSceneZ[1], TileOverlay.vertexSceneZ[3], tileOverlay.triangleTextureIndex[j2]);
                    else
                        Draw3D.fillTexturedTriangle(l4, i5, j5, i4, j4, k4, tileOverlay.triangleColorA[j2],
                            tileOverlay.triangleColorB[j2], tileOverlay.triangleColorC[j2], TileOverlay.vertexSceneX[l2],
                            TileOverlay.vertexSceneX[j3], TileOverlay.vertexSceneX[l3], TileOverlay.vertexSceneY[l2],
                            TileOverlay.vertexSceneY[j3], TileOverlay.vertexSceneY[l3], TileOverlay.vertexSceneZ[l2],
                            TileOverlay.vertexSceneZ[j3], TileOverlay.vertexSceneZ[l3], tileOverlay.triangleTextureIndex[j2]);
                } else {
                    int k5 = TEXTURE_HSL[tileOverlay.triangleTextureIndex[j2]];
                    Draw3D.fillGouraudScanline(l4, i5, j5, i4, j4, k4,
                        adjustHSLLightness(tileOverlay.triangleColorA[j2], k5), adjustHSLLightness(tileOverlay.triangleColorB[j2], k5),
                        adjustHSLLightness(tileOverlay.triangleColorC[j2], k5));
                }
            }
        }

    }

    public int adjustHSLLightness(int i, int j) {
        i = 127 - i;
        i = (i * (j & 0x7f)) / 160;
        if (i < 2)
            i = 2;
        else if (i > 126)
            i = 126;
        return (j & 0xff80) + i;
    }

    public boolean withinTriangle(int i, int j, int k, int l, int i1, int j1, int k1,
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

    public void updateOccluders() {
        int i = levelOccluderCount[tileUpdateCount];
        Occluder[] aclass23 = levelOccluders[tileUpdateCount];
        activeOccluderCount = 0;
        for (int j = 0; j < i; j++) {
            Occluder occluder = aclass23[j];
            if (occluder.type == 1) {
                int k = (occluder.minTileX - maxTileZ) + 25;
                if (k < 0 || k > 50)
                    continue;
                int j1 = (occluder.minTileZ - cameraTileX) + 25;
                if (j1 < 0)
                    j1 = 0;
                int i2 = (occluder.maxTileZ - cameraTileX) + 25;
                if (i2 > 50)
                    i2 = 50;
                boolean flag1 = false;
                while (j1 <= i2)
                    if (visibilityMap[k][j1++]) {
                        flag1 = true;
                        break;
                    }
                if (!flag1)
                    continue;
                int i3 = cameraTileZ - occluder.minX;
                if (i3 > 32) {
                    occluder.testDirection = 1;
                } else {
                    if (i3 >= -32)
                        continue;
                    occluder.testDirection = 2;
                    i3 = -i3;
                }
                occluder.minNormalZ = (occluder.minZ - cameraY << 8) / i3;
                occluder.maxNormalZ = (occluder.maxZ - cameraY << 8) / i3;
                occluder.minNormalY = (occluder.minY - cameraX << 8) / i3;
                occluder.maxNormalY = (occluder.maxY - cameraX << 8) / i3;
                activeOccluders[activeOccluderCount++] = occluder;
                continue;
            }
            if (occluder.type == 2) {
                int l = (occluder.minTileZ - cameraTileX) + 25;
                if (l < 0 || l > 50)
                    continue;
                int k1 = (occluder.minTileX - maxTileZ) + 25;
                if (k1 < 0)
                    k1 = 0;
                int j2 = (occluder.maxTileX - maxTileZ) + 25;
                if (j2 > 50)
                    j2 = 50;
                boolean flag2 = false;
                while (k1 <= j2)
                    if (visibilityMap[k1++][l]) {
                        flag2 = true;
                        break;
                    }
                if (!flag2)
                    continue;
                int j3 = cameraY - occluder.minZ;
                if (j3 > 32) {
                    occluder.testDirection = 3;
                } else {
                    if (j3 >= -32)
                        continue;
                    occluder.testDirection = 4;
                    j3 = -j3;
                }
                occluder.minNormalX = (occluder.minX - cameraTileZ << 8) / j3;
                occluder.maxNormalX = (occluder.maxX - cameraTileZ << 8) / j3;
                occluder.minNormalY = (occluder.minY - cameraX << 8) / j3;
                occluder.maxNormalY = (occluder.maxY - cameraX << 8) / j3;
                activeOccluders[activeOccluderCount++] = occluder;
            } else if (occluder.type == 4) {
                int i1 = occluder.minY - cameraX;
                if (i1 > 128) {
                    int l1 = (occluder.minTileZ - cameraTileX) + 25;
                    if (l1 < 0)
                        l1 = 0;
                    int k2 = (occluder.maxTileZ - cameraTileX) + 25;
                    if (k2 > 50)
                        k2 = 50;
                    if (l1 <= k2) {
                        int l2 = (occluder.minTileX - maxTileZ) + 25;
                        if (l2 < 0)
                            l2 = 0;
                        int k3 = (occluder.maxTileX - maxTileZ) + 25;
                        if (k3 > 50)
                            k3 = 50;
                        boolean flag3 = false;
                        label0:
                        for (int l3 = l2; l3 <= k3; l3++) {
                            for (int i4 = l1; i4 <= k2; i4++) {
                                if (!visibilityMap[l3][i4])
                                    continue;
                                flag3 = true;
                                break label0;
                            }

                        }

                        if (flag3) {
                            occluder.testDirection = 5;
                            occluder.minNormalX = (occluder.minX - cameraTileZ << 8) / i1;
                            occluder.maxNormalX = (occluder.maxX - cameraTileZ << 8) / i1;
                            occluder.minNormalZ = (occluder.minZ - cameraY << 8) / i1;
                            occluder.maxNormalZ = (occluder.maxZ - cameraY << 8) / i1;
                            activeOccluders[activeOccluderCount++] = occluder;
                        }
                    }
                }
            }
        }
    }

    public boolean isTileOccluded(int i, int j, int k) {
        int l = levelTileCycles[i][j][k];
        if (l == -activeLevel)
            return false;
        if (l == activeLevel)
            return true;
        int i1 = j << 7;
        int j1 = k << 7;
        if (isOccluded(i1 + 1, heightmap[i][j][k], j1 + 1)
            && isOccluded((i1 + 128) - 1, heightmap[i][j + 1][k], j1 + 1)
            && isOccluded((i1 + 128) - 1, heightmap[i][j + 1][k + 1], (j1 + 128) - 1)
            && isOccluded(i1 + 1, heightmap[i][j][k + 1], (j1 + 128) - 1)) {
            levelTileCycles[i][j][k] = activeLevel;
            return true;
        } else {
            levelTileCycles[i][j][k] = -activeLevel;
            return false;
        }
    }

    public boolean isWallOccluded(int i, int j, int k, int l) {
        if (!isTileOccluded(i, j, k))
            return false;
        int i1 = j << 7;
        int j1 = k << 7;
        int k1 = heightmap[i][j][k] - 1;
        int l1 = k1 - 120;
        int i2 = k1 - 230;
        int j2 = k1 - 238;
        if (l < 16) {
            if (l == 1) {
                if (i1 > cameraTileZ) {
                    if (!isOccluded(i1, k1, j1))
                        return false;
                    if (!isOccluded(i1, k1, j1 + 128))
                        return false;
                }
                if (i > 0) {
                    if (!isOccluded(i1, l1, j1))
                        return false;
                    if (!isOccluded(i1, l1, j1 + 128))
                        return false;
                }
                if (!isOccluded(i1, i2, j1))
                    return false;
                return isOccluded(i1, i2, j1 + 128);
            }
            if (l == 2) {
                if (j1 < cameraY) {
                    if (!isOccluded(i1, k1, j1 + 128))
                        return false;
                    if (!isOccluded(i1 + 128, k1, j1 + 128))
                        return false;
                }
                if (i > 0) {
                    if (!isOccluded(i1, l1, j1 + 128))
                        return false;
                    if (!isOccluded(i1 + 128, l1, j1 + 128))
                        return false;
                }
                if (!isOccluded(i1, i2, j1 + 128))
                    return false;
                return isOccluded(i1 + 128, i2, j1 + 128);
            }
            if (l == 4) {
                if (i1 < cameraTileZ) {
                    if (!isOccluded(i1 + 128, k1, j1))
                        return false;
                    if (!isOccluded(i1 + 128, k1, j1 + 128))
                        return false;
                }
                if (i > 0) {
                    if (!isOccluded(i1 + 128, l1, j1))
                        return false;
                    if (!isOccluded(i1 + 128, l1, j1 + 128))
                        return false;
                }
                if (!isOccluded(i1 + 128, i2, j1))
                    return false;
                return isOccluded(i1 + 128, i2, j1 + 128);
            }
            if (l == 8) {
                if (j1 > cameraY) {
                    if (!isOccluded(i1, k1, j1))
                        return false;
                    if (!isOccluded(i1 + 128, k1, j1))
                        return false;
                }
                if (i > 0) {
                    if (!isOccluded(i1, l1, j1))
                        return false;
                    if (!isOccluded(i1 + 128, l1, j1))
                        return false;
                }
                if (!isOccluded(i1, i2, j1))
                    return false;
                return isOccluded(i1 + 128, i2, j1);
            }
        }
        if (!isOccluded(i1 + 64, j2, j1 + 64))
            return false;
        if (l == 16)
            return isOccluded(i1, i2, j1 + 128);
        if (l == 32)
            return isOccluded(i1 + 128, i2, j1 + 128);
        if (l == 64)
            return isOccluded(i1 + 128, i2, j1);
        if (l == 128) {
            return isOccluded(i1, i2, j1);
        } else {
            System.out.println("Warning unsupported wall type");
            return true;
        }
    }

    public boolean isOccluded(int i, int j, int k, int l) {
        if (!isTileOccluded(i, j, k))
            return false;
        int i1 = j << 7;
        int j1 = k << 7;
        return isOccluded(i1 + 1, heightmap[i][j][k] - l, j1 + 1)
            && isOccluded((i1 + 128) - 1, heightmap[i][j + 1][k] - l, j1 + 1)
            && isOccluded((i1 + 128) - 1, heightmap[i][j + 1][k + 1] - l, (j1 + 128) - 1)
            && isOccluded(i1 + 1, heightmap[i][j][k + 1] - l, (j1 + 128) - 1);
    }

    public boolean isAreaOccluded(int i, int j, int k, int l, int i1, int j1) {
        if (j == k && l == i1) {
            if (!isTileOccluded(i, j, l))
                return false;
            int k1 = j << 7;
            int i2 = l << 7;
            return isOccluded(k1 + 1, heightmap[i][j][l] - j1, i2 + 1)
                && isOccluded((k1 + 128) - 1, heightmap[i][j + 1][l] - j1, i2 + 1)
                && isOccluded((k1 + 128) - 1, heightmap[i][j + 1][l + 1] - j1, (i2 + 128) - 1)
                && isOccluded(k1 + 1, heightmap[i][j][l + 1] - j1, (i2 + 128) - 1);
        }
        for (int l1 = j; l1 <= k; l1++) {
            for (int j2 = l; j2 <= i1; j2++)
                if (levelTileCycles[i][l1][j2] == -activeLevel)
                    return false;

        }

        int k2 = (j << 7) + 1;
        int l2 = (l << 7) + 2;
        int i3 = heightmap[i][j][l] - j1;
        if (!isOccluded(k2, i3, l2))
            return false;
        int j3 = (k << 7) - 1;
        if (!isOccluded(j3, i3, l2))
            return false;
        int k3 = (i1 << 7) - 1;
        if (!isOccluded(k2, i3, k3))
            return false;
        return isOccluded(j3, i3, k3);
    }

    public boolean isOccluded(int i, int j, int k) {
        for (int l = 0; l < activeOccluderCount; l++) {
            Occluder occluder = activeOccluders[l];
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

    public static boolean lowMemory = true;
    public int maxLevel;
    public int tileCountX;
    public int tileCountZ;
    public int[][][] heightmap;
    public Tile[][][] levelTiles;
    public int minLevel;
    public int locCount;
    public Loc[] locs;
    public int[][][] levelTileCycles;
    public static int lastTileUpdateCount;
    public static int tileUpdateCount;
    public static int activeLevel;
    public static int cycle;
    public static int minTileX;
    public static int maxTileX;
    public static int minTileZ;
    public static int maxTileZ;
    public static int cameraTileX;
    public static int cameraTileZ;
    public static int cameraX;
    public static int cameraY;
    public static int pitchSin;
    public static int pitchCos;
    public static int yawSin;
    public static int yawCos;
    public static Loc[] locBuffer = new Loc[100];

    public static final int[] DECO_TYPE1_OFFSET_X = {53, -53, -53, 53};
    public static final int[] DECO_TYPE1_OFFSET_Z = {-53, -53, 53, 53};
    public static final int[] DECO_TYPE2_OFFSET_X = {-45, 45, 45, -45};
    public static final int[] DECO_TYPE2_OFFSET_Z = {45, 45, -45, -45};

    public static boolean checkClick;
    public static int clickX;
    public static int clickY;
    public static int clickedTileX = -1;
    public static int clickedTileZ = -1;
    public static int MAX_OCCLUDER_LEVELS;
    public static int[] levelOccluderCount;
    public static Occluder[][] levelOccluders;
    public static int activeOccluderCount;
    public static Occluder[] activeOccluders = new Occluder[500];
    public static LinkedList tileQueue = new LinkedList();

    public static final int[] TILE_WALL_DRAW_FLAGS_0 = {19, 55, 38, 155, 255, 110, 137, 205, 76};
    public static final int[] WALL_DRAW_FLAGS = {160, 192, 80, 96, 0, 144, 80, 48, 160};
    public static final int[] TILE_WALL_DRAW_FLAGS_1 = {76, 8, 137, 4, 0, 1, 38, 2, 19};

    public static final int[] WALL_UNCULL_FLAGS_0 = {0, 0, 2, 0, 0, 2, 1, 1, 0};
    public static final int[] WALL_UNCULL_FLAGS_1 = {2, 0, 0, 2, 0, 0, 0, 4, 4};
    public static final int[] WALL_UNCULL_FLAGS_2 = {0, 4, 4, 8, 0, 0, 8, 0, 0};
    public static final int[] WALL_UNCULL_FLAGS_3 = {1, 1, 0, 0, 0, 8, 0, 0, 8};

    public static final int[] TEXTURE_HSL = {
        41, 39248, 41, 4643, 41, 41, 41, 41, 41, 41,
        41, 41, 41, 41, 41, 43086, 41, 41, 41, 41,
        41, 41, 41, 8602, 41, 28992, 41, 41, 41, 41,
        41, 5056, 41, 41, 41, 41, 41, 41, 41, 41,
        41, 41, 41, 41, 41, 41, 3131, 41, 41, 41
    };

    public int[] vertexAMergeIndex;
    public int[] vertexBMergeIndex;
    public int normalMergeIndex;

    public int[][] TILE_MASK_2D = {new int[16], {
        1, 1, 1, 1,
        1, 1, 1, 1,
        1, 1, 1, 1,
        1, 1, 1, 1
    }, {
        1, 0, 0, 0,
        1, 1, 0, 0,
        1, 1, 1, 0,
        1, 1, 1, 1
    }, {
        1, 1, 0, 0,
        1, 1, 0, 0,
        1, 0, 0, 0,
        1, 0, 0, 0
    }, {
        0, 0, 1, 1,
        0, 0, 1, 1,
        0, 0, 0, 1,
        0, 0, 0, 1
    }, {
        0, 1, 1, 1,
        0, 1, 1, 1,
        1, 1, 1, 1,
        1, 1, 1, 1
    }, {
        1, 1, 1, 0,
        1, 1, 1, 0,
        1, 1, 1, 1,
        1, 1, 1, 1
    }, {
        1, 1, 0, 0,
        1, 1, 0, 0,
        1, 1, 0, 0,
        1, 1, 0, 0
    }, {
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 0, 0, 0,
        1, 1, 0, 0
    }, {
        1, 1, 1, 1,
        1, 1, 1, 1,
        0, 1, 1, 1,
        0, 0, 1, 1
    }, {
        1, 1, 1, 1,
        1, 1, 0, 0,
        1, 0, 0, 0,
        1, 0, 0, 0
    }, {
        0, 0, 0, 0,
        0, 0, 1, 1,
        0, 1, 1, 1,
        0, 1, 1, 1
    }, {
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 1, 1, 0,
        1, 1, 1, 1
    }
    };

    public int[][] TILE_ROTATION_2D = {
        {
            0, 1, 2, 3,
            4, 5, 6, 7,
            8, 9, 10, 11,
            12, 13, 14, 15
        }, {
        12, 8, 4, 0,
        13, 9, 5, 1,
        14, 10, 6, 2,
        15, 11, 7, 3
    }, {
        15, 14, 13, 12,
        11, 10, 9, 8,
        7, 6, 5, 4,
        3, 2, 1, 0
    }, {
        3, 7, 11, 15,
        2, 6, 10, 14,
        1, 5, 9, 13,
        0, 4, 8, 12
    }
    };

    public static boolean[][][][] visibilityMaps = new boolean[8][32][51][51];
    public static boolean[][] visibilityMap;
    public static int viewportCenterX;
    public static int viewportCenterY;
    public static int viewportLeft;
    public static int viewportTop;
    public static int viewportRight;
    public static int viewportBottom;

    static {
        MAX_OCCLUDER_LEVELS = 4;
        levelOccluderCount = new int[MAX_OCCLUDER_LEVELS];
        levelOccluders = new Occluder[MAX_OCCLUDER_LEVELS][500];
    }
}
