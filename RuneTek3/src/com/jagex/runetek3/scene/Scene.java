package com.jagex.runetek3.scene;

import com.jagex.runetek3.formats.Model;
import com.jagex.runetek3.graphics.Draw2D;
import com.jagex.runetek3.graphics.Draw3D;
import com.jagex.runetek3.util.LinkedList;

public class Scene {

    public Scene(int[][][] heightmap, int tileCountZ, int maxLevel, int tileCountX) {
        locs = new Loc[5000];
        vertexAMergeIndex = new int[10000];
        vertexBMergeIndex = new int[10000];
        this.maxLevel = maxLevel;
        this.tileCountX = tileCountX;
        this.tileCountZ = tileCountZ;
        levelTiles = new Tile[maxLevel][tileCountX][tileCountZ];
        levelTileCycles = new int[maxLevel][tileCountX + 1][tileCountZ + 1];
        this.heightmap = heightmap;
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
        for (int plane = 0; plane < maxLevel; plane++) {
            for (int stx = 0; stx < tileCountX; stx++) {
                for (int stz = 0; stz < tileCountZ; stz++) {
                    levelTiles[plane][stx][stz] = null;
                }
            }
        }

        for (int i = 0; i < MAX_OCCLUDER_LEVELS; i++) {
            for (int j = 0; j < levelOccluderCount[i]; j++) {
                levelOccluders[i][j] = null;
            }

            levelOccluderCount[i] = 0;
        }

        for (int n = 0; n < locCount; n++) {
            locs[n] = null;
        }

        locCount = 0;
        for (int n = 0; n < locBuffer.length; n++) {
            locBuffer[n] = null;
        }
    }

    public void setup(int level) {
        minLevel = level;

        for (int stx = 0; stx < tileCountX; stx++) {
            for (int stz = 0; stz < tileCountZ; stz++) {
                levelTiles[level][stx][stz] = new Tile(level, stx, stz);
            }
        }
    }

    public void setBridge(int stz, int stx) {
        Tile ground = levelTiles[0][stx][stz];

        for (int plane = 0; plane < 3; plane++) {
            levelTiles[plane][stx][stz] = levelTiles[plane + 1][stx][stz];
            if (levelTiles[plane][stx][stz] != null) {
                levelTiles[plane][stx][stz].level--;
            }
        }

        if (levelTiles[0][stx][stz] == null) {
            levelTiles[0][stx][stz] = new Tile(0, stx, stz);
        }

        levelTiles[0][stx][stz].bridge = ground;
        levelTiles[3][stx][stz] = null;
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

    public void addTile(int plane, int stx, int stz, int type, int rotation, int texture, int swY,
                        int seY, int neY, int nwY, int swColor1, int seColor1, int neColor1, int nwColor1,
                        int swColor2, int seColor2, int neColor2, int nwColor2, int underlayRGB, int rgb) {
        if (type == 0) {
            TileUnderlay t = new TileUnderlay(swColor1, seColor1, neColor1, nwColor1, -1, underlayRGB, false);
            for (int p = plane; p >= 0; p--) {
                if (levelTiles[p][stx][stz] == null) {
                    levelTiles[p][stx][stz] = new Tile(p, stx, stz);
                }
            }

            levelTiles[plane][stx][stz].underlay = t;
        } else if (type == 1) {
            TileUnderlay t = new TileUnderlay(swColor2, seColor2, neColor2, nwColor2, texture, rgb,
                swY == seY && swY == neY && swY == nwY);
            for (int p = plane; p >= 0; p--) {
                if (levelTiles[p][stx][stz] == null) {
                    levelTiles[p][stx][stz] = new Tile(p, stx, stz);
                }
            }

            levelTiles[plane][stx][stz].underlay = t;
        } else {
            TileOverlay t = new TileOverlay(stx, type, seColor2, seY, neColor1, rotation, swColor1, nwY, rgb, swColor2,
                texture, nwColor2, underlayRGB, neY, neColor2, nwColor1, swY, stz, seColor1);
            for (int p = plane; p >= 0; p--) {
                if (levelTiles[p][stx][stz] == null) {
                    levelTiles[p][stx][stz] = new Tile(p, stx, stz);
                }
            }

            levelTiles[plane][stx][stz].overlay = t;
        }
    }

    public void addGroundDecoration(Model m, int tileX, int bitset, int tileZ, int level, byte info, int tileY) {
        GroundDecoration l = new GroundDecoration();
        l.model = m;
        l.sceneX = tileX * 128 + 64;
        l.sceneZ = tileZ * 128 + 64;
        l.sceneY = tileY;
        l.bitset = bitset;
        l.info = info;

        if (levelTiles[level][tileX][tileZ] == null) {
            levelTiles[level][tileX][tileZ] = new Tile(level, tileX, tileZ);
        }

        levelTiles[level][tileX][tileZ].groundDecoration = l;
    }

    public void addObject(Model m0, Model m1, int sceneY, int level, int bitset, int tileZ, int tileX, Model m2) {
        ObjEntity l = new ObjEntity();
        l.entity0 = m0;
        l.x = tileX * 128 + 64;
        l.z = tileZ * 128 + 64;
        l.y = sceneY;
        l.bitset = bitset;
        l.entity1 = m1;
        l.entity2 = m2;

        int maxY = 0;
        Tile t = levelTiles[level][tileX][tileZ];
        if (t != null) {
            for (int n = 0; n < t.locationCount; n++) {
                int y = t.locs[n].model.anInt1251;
                if (y > maxY) {
                    maxY = y;
                }
            }
        }

        l.offsetY = maxY;

        if (levelTiles[level][tileX][tileZ] == null) {
            levelTiles[level][tileX][tileZ] = new Tile(level, tileX, tileZ);
        }

        levelTiles[level][tileX][tileZ].objEntity = l;
    }

    public void addWall(int type1, int sceneY, int level, int type0, Model m1, Model m2, int tileX, int bitset, int tileZ, byte info) {
        if (m1 == null && m2 == null) {
            return;
        }

        Wall w = new Wall();
        w.bitset = bitset;
        w.info = info;
        w.x = tileX * 128 + 64;
        w.z = tileZ * 128 + 64;
        w.y = sceneY;
        w.entity0 = m1;
        w.entity1 = m2;
        w.type0 = type0;
        w.type1 = type1;

        for (int l = level; l >= 0; l--) {
            if (levelTiles[l][tileX][tileZ] == null) {
                levelTiles[l][tileX][tileZ] = new Tile(l, tileX, tileZ);
            }
        }

        levelTiles[level][tileX][tileZ].wall = w;
    }

    public void addWallDecoration(int sceneY, int tileZ, int tileSizeZ, int bitset, int rotation, int type, int tileSizeX, int tileX, Model model, byte info, int level) {
        if (model == null) {
            return;
        }

        WallDecoration d = new WallDecoration();
        d.bitset = bitset;
        d.info = info;
        d.sceneX = tileX * 128 + 64 + tileSizeX;
        d.sceneZ = tileZ * 128 + 64 + tileSizeZ;
        d.sceneY = sceneY;
        d.model = model;
        d.type = type;
        d.rotation = rotation;

        for (int l = level; l >= 0; l--) {
            if (levelTiles[l][tileX][tileZ] == null) {
                levelTiles[l][tileX][tileZ] = new Tile(l, tileX, tileZ);
            }
        }

        levelTiles[level][tileX][tileZ].wallDecoration = d;
    }

    public boolean addLocation(int y, int level, Entity entity, int bitset, int minTileZ, int minTileX, int tileSizeX, byte info, Model m, int yaw, int tileSizeZ) {
        if (m == null && entity == null) {
            return true;
        }

        int x = minTileX * 128 + 64 * tileSizeX;
        int z = minTileZ * 128 + 64 * tileSizeZ;
        return add(level, minTileX, minTileZ, tileSizeX, tileSizeZ, x, z, y, m, entity, yaw, false, bitset, info);
    }

    public boolean add(int sceneZ, int size, int yaw, int sceneX, int bitset, boolean renderPadding, Model m, Entity e, int sceneY, int level) {
        if (m == null && e == null) {
            return true;
        }

        int minX = sceneX - size;
        int minZ = sceneZ - size;
        int maxX = sceneX + size;
        int maxZ = sceneZ + size;

        if (renderPadding) {
            if (yaw > 640 && yaw < 1408) {
                maxZ += 128;
            }

            if (yaw > 1152 && yaw < 1920) {
                maxX += 128;
            }

            if (yaw > 1664 || yaw < 384) {
                minZ -= 128;
            }

            if (yaw > 128 && yaw < 896) {
                minX -= 128;
            }
        }

        minX /= 128;
        minZ /= 128;
        maxX /= 128;
        maxZ /= 128;

        return add(level, minX, minZ, (maxX - minX) + 1, (maxZ - minZ) + 1, sceneX, sceneZ, sceneY, m, e, yaw, true, bitset, (byte) 0);
    }

    public boolean add(int sizeX, Model m, int sceneZ, int sceneY, int bitset, int yaw, int tileZ, int tileX, Entity e, int level, int sizeZ, int sceneX) {
        if (m == null && e == null) {
            return true;
        } else {
            return add(level, tileX, tileZ, (sizeX - tileX) + 1, (sizeZ - tileZ) + 1, sceneX, sceneZ, sceneY, m, e, yaw, true, bitset, (byte) 0);
        }
    }

    public boolean add(int level, int tileX, int tileZ, int sizeX, int sizeY, int sceneX, int sceneZ,
                       int sceneY, Model m, Entity e, int yaw, boolean temporary, int bitset,
                       byte info) {
        if (m == null && e == null) {
            return false;
        }

        for (int x = tileX; x < tileX + sizeX; x++) {
            for (int z = tileZ; z < tileZ + sizeY; z++) {
                if (x < 0 || z < 0 || x >= tileCountX || z >= tileCountZ) {
                    return false;
                }

                Tile tile = levelTiles[level][x][z];
                if (tile != null && tile.locationCount >= 5) {
                    return false;
                }
            }
        }

        Loc loc = new Loc();
        loc.bitset = bitset;
        loc.info = info;
        loc.plane = level;
        loc.x = sceneX;
        loc.z = sceneZ;
        loc.y = sceneY;
        loc.model = m;
        loc.entity = e;
        loc.yaw = yaw;
        loc.minSceneTileX = tileX;
        loc.minSceneTileZ = tileZ;
        loc.maxSceneTileX = (tileX + sizeX) - 1;
        loc.maxSceneTileZ = (tileZ + sizeY) - 1;

        for (int x = tileX; x < tileX + sizeX; x++) {
            for (int y = tileZ; y < tileZ + sizeY; y++) {
                int flags = 0;

                if (x > tileX) {
                    flags++;
                }

                if (x < (tileX + sizeX) - 1) {
                    flags += 4;
                }

                if (y > tileZ) {
                    flags += 8;
                }

                if (y < (tileZ + sizeY) - 1) {
                    flags += 2;
                }

                for (int l = level; l >= 0; l--) {
                    if (levelTiles[l][x][y] == null) {
                        levelTiles[l][x][y] = new Tile(l, x, y);
                    }
                }

                Tile tile = levelTiles[level][x][y];
                tile.locs[tile.locationCount] = loc;
                tile.locFlags[tile.locationCount] = flags;
                tile.flags |= flags;
                tile.locationCount++;
            }
        }

        if (temporary) {
            locs[locCount++] = loc;
        }

        return true;
    }

    public void clearFrameLocs() {
        for (int n = 0; n < locCount; n++) {
            Loc loc = locs[n];
            removeLocation(loc);
            locs[n] = null;
        }

        locCount = 0;
    }

    public void removeLocation(Loc loc) {
        for (int x = loc.minSceneTileX; x <= loc.maxSceneTileX; x++) {
            for (int z = loc.minSceneTileZ; z <= loc.maxSceneTileZ; z++) {
                Tile t = levelTiles[loc.plane][x][z];

                if (t != null) {
                    for (int n = 0; n < t.locationCount; n++) {
                        if (t.locs[n] != loc) {
                            continue;
                        }

                        t.locationCount--;
                        for (int m = n; m < t.locationCount; m++) {
                            t.locs[m] = t.locs[m + 1];
                            t.locFlags[m] = t.locFlags[m + 1];
                        }

                        t.locs[t.locationCount] = null;
                        break;
                    }

                    t.flags = 0;
                    for (int n = 0; n < t.locationCount; n++) {
                        t.flags |= t.locFlags[n];
                    }
                }
            }
        }
    }

    public void setLocModel(int x, Model m, int level, int z) {
        if (m == null) {
            return;
        }

        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return;
        }

        for (int n = 0; n < t.locationCount; n++) {
            Loc loc = t.locs[n];
            if ((loc.bitset >> 29 & 3) == 2) {
                loc.model = m;
                return;
            }
        }
    }

    public void method298(int level, int z, int x, int l) {
        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return;
        }

        WallDecoration d = t.wallDecoration;
        if (d != null) {
            int sceneX = x * 128 + 64;
            int sceneZ = z * 128 + 64;
            d.sceneX = sceneX + ((d.sceneX - sceneX) * l) / 16;
            d.sceneZ = sceneZ + ((d.sceneZ - sceneZ) * l) / 16;
        }
    }

    public void setWallDecorationModel(int z, int x, Model m, int level) {
        if (m == null) {
            return;
        }

        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return;
        }

        WallDecoration l = t.wallDecoration;
        if (l != null) {
            l.model = m;
        }
    }

    public void setGroundDecorationModel(Model m, int z, int x, int level) {
        if (m == null) {
            return;
        }

        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return;
        }

        GroundDecoration d = t.groundDecoration;
        if (d != null) {
            d.model = m;
        }
    }

    public void setWallModel(Model m, int z, int x, int level) {
        if (m == null) {
            return;
        }

        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return;
        }

        Wall w = t.wall;
        if (w != null) {
            w.entity0 = m;
        }
    }

    public void setWallModels(Model m1, Model m2, int z, int x, int level) {
        if (m1 == null) {
            return;
        }

        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return;
        }

        Wall w = t.wall;
        if (w == null) {
            return;
        }

        w.entity0 = m1;
        w.entity1 = m2;
    }

    public void removeWall(int x, int level, int z) {
        Tile t = levelTiles[level][x][z];
        if (t != null) {
            t.wall = null;
        }
    }

    public void removeWallDecoration(int level, int z, int x) {
        Tile t = levelTiles[level][x][z];
        if (t != null) {
            t.wallDecoration = null;
        }
    }

    public void removeLocations(int x, int z, int level) {
        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return;
        }

        for (int n = 0; n < t.locationCount; n++) {
            Loc loc = t.locs[n];
            if ((loc.bitset >> 29 & 3) == 2 && loc.minSceneTileX == x && loc.minSceneTileZ == z) {
                removeLocation(loc);
                return;
            }
        }
    }

    public void removeGroundDecoration(int level, int x, int z) {
        Tile t = levelTiles[level][x][z];
        if (t != null) {
            t.groundDecoration = null;
        }
    }

    public void removeObject(int level, int x, int z) {
        Tile t = levelTiles[level][x][z];
        if (t != null) {
            t.objEntity = null;
        }
    }

    public int getWallBitset(int level, int x, int z) {
        Tile tile = levelTiles[level][x][z];
        if (tile == null || tile.wall == null) {
            return 0;
        } else {
            return tile.wall.bitset;
        }
    }

    public int getWallDecorationBitset(int level, int z, int x) {
        Tile tile = levelTiles[level][x][z];
        if (tile == null || tile.wallDecoration == null) {
            return 0;
        } else {
            return tile.wallDecoration.bitset;
        }
    }

    public int getLocationBitset(int level, int x, int z) {
        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return 0;
        }

        for (int l = 0; l < t.locationCount; l++) {
            Loc loc = t.locs[l];
            if ((loc.bitset >> 29 & 3) == 2 && loc.minSceneTileX == x && loc.minSceneTileZ == z) {
                return loc.bitset;
            }
        }

        return 0;
    }

    public int getGroundDecorationBitset(int level, int x, int z) {
        Tile t = levelTiles[level][x][z];
        if (t == null || t.groundDecoration == null) {
            return 0;
        } else {
            return t.groundDecoration.bitset;
        }
    }

    public int getInfo(int level, int x, int z, int bitset) {
        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return -1;
        }

        if (t.wall != null && t.wall.bitset == bitset) {
            return t.wall.info & 0xff;
        }

        if (t.wallDecoration != null && t.wallDecoration.bitset == bitset) {
            return t.wallDecoration.info & 0xff;
        }

        if (t.groundDecoration != null && t.groundDecoration.bitset == bitset) {
            return t.groundDecoration.info & 0xff;
        }

        for (int n = 0; n < t.locationCount; n++) {
            if (t.locs[n].bitset == bitset) {
                return t.locs[n].info & 0xff;
            }
        }

        return -1;
    }

    public void applyLighting(int lightY, int lightness, int lightX, int baseIntensity, int lightZ) {
        int length = (int) Math.sqrt(lightX * lightX + lightY * lightY + lightZ * lightZ);
        int intensity = baseIntensity * length >> 8;

        for (int level = 0; level < maxLevel; level++) {
            for (int tileX = 0; tileX < tileCountX; tileX++) {
                for (int tileZ = 0; tileZ < tileCountZ; tileZ++) {
                    Tile t = levelTiles[level][tileX][tileZ];

                    if (t != null) {
                        Wall w = t.wall;
                        if (w != null && w.entity0 != null && w.entity0.vertexNormals != null) {
                            mergeLocNormals(tileX, 1, 1, level, w.entity0, tileZ);

                            if (w.entity1 != null && w.entity1.vertexNormals != null) {
                                mergeLocNormals(tileX, 1, 1, level, w.entity1, tileZ);
                                mergeNormals(w.entity0, w.entity1, 0, 0, 0, false);
                                w.entity1.calculateLighting(lightness, intensity, lightX, lightY, lightZ);
                            }

                            w.entity0.calculateLighting(lightness, intensity, lightX, lightY, lightZ);
                        }

                        for (int n = 0; n < t.locationCount; n++) {
                            Loc loc = t.locs[n];
                            if (loc != null && loc.model != null && loc.model.vertexNormals != null) {
                                mergeLocNormals(tileX, (loc.maxSceneTileX - loc.minSceneTileX) + 1, (loc.maxSceneTileZ - loc.minSceneTileZ) + 1, level, loc.model, tileZ);
                                loc.model.calculateLighting(lightness, intensity, lightX, lightY, lightZ);
                            }
                        }

                        GroundDecoration d = t.groundDecoration;
                        if (d != null && d.model != null && d.model.vertexNormals != null) {
                            mergeGroundDecorationNormals(level, tileZ, d.model, tileX);
                            d.model.calculateLighting(lightness, intensity, lightX, lightY, lightZ);
                        }
                    }
                }
            }
        }
    }

    public void mergeGroundDecorationNormals(int level, int z, Model m, int x) {
        if (x < tileCountX) {
            Tile t = levelTiles[level][x + 1][z];
            if (t != null && t.groundDecoration != null && t.groundDecoration.model.vertexNormals != null) {
                mergeNormals(m, t.groundDecoration.model, 128, 0, 0, true);
            }
        }

        if (z < tileCountX) {
            Tile t = levelTiles[level][x][z + 1];
            if (t != null && t.groundDecoration != null && t.groundDecoration.model.vertexNormals != null) {
                mergeNormals(m, t.groundDecoration.model, 0, 0, 128, true);
            }
        }

        if (x < tileCountX && z < tileCountZ) {
            Tile t = levelTiles[level][x + 1][z + 1];
            if (t != null && t.groundDecoration != null && t.groundDecoration.model.vertexNormals != null) {
                mergeNormals(m, t.groundDecoration.model, 128, 0, 128, true);
            }
        }

        if (x < tileCountX && z > 0) {
            Tile t = levelTiles[level][x + 1][z - 1];
            if (t != null && t.groundDecoration != null && t.groundDecoration.model.vertexNormals != null) {
                mergeNormals(m, t.groundDecoration.model, 128, 0, -128, true);
            }
        }
    }

    public void mergeLocNormals(int tileX, int locTileSizeX, int locTileSizeZ, int level, Model m, int tileZ) {
        boolean hideTriangles = true;

        int minTileX = tileX;
        int maxTileX = tileX + locTileSizeX;

        int minTileZ = tileZ - 1;
        int maxTileZ = tileZ + locTileSizeZ;

        for (int l = level; l <= level + 1; l++) {
            if (l == maxLevel) {
                continue;
            }

            for (int x = minTileX; x <= maxTileX; x++) {
                if (x < 0 || x >= tileCountX) {
                    continue;
                }

                for (int z = minTileZ; z <= maxTileZ; z++) {
                    if (z < 0 || z >= tileCountZ || (hideTriangles && x < maxTileX && z < maxTileZ && (z >= tileZ || x == tileX))) {
                        continue;
                    }

                    Tile t = levelTiles[l][x][z];
                    if (t != null) {
                        int averageY = (heightmap[l][x][z]
                            + heightmap[l][x + 1][z]
                            + heightmap[l][x][z + 1]
                            + heightmap[l][x + 1][z + 1]) / 4
                            - (heightmap[level][tileX][tileZ] + heightmap[level][tileX + 1][tileZ]
                            + heightmap[level][tileX][tileZ + 1]
                            + heightmap[level][tileX + 1][tileZ + 1]) / 4;

                        Wall w = t.wall;
                        if (w != null && w.entity0 != null && w.entity0.vertexNormals != null) {
                            mergeNormals(m, w.entity0, (x - tileX) * 128 + (1 - locTileSizeX) * 64, averageY, (z - tileZ) * 128 + (1 - locTileSizeZ) * 64, hideTriangles);
                        }

                        if (w != null && w.entity1 != null && w.entity1.vertexNormals != null) {
                            mergeNormals(m, w.entity1, (x - tileX) * 128 + (1 - locTileSizeX) * 64, averageY, (z - tileZ) * 128 + (1 - locTileSizeZ) * 64, hideTriangles);
                        }

                        for (int n = 0; n < t.locationCount; n++) {
                            Loc loc = t.locs[n];

                            if (loc != null && loc.model != null && loc.model.vertexNormals != null) {
                                int tileSizeX = (loc.maxSceneTileX - loc.minSceneTileX) + 1;
                                int tileSizeZ = (loc.maxSceneTileZ - loc.minSceneTileZ) + 1;
                                mergeNormals(m, loc.model, (loc.minSceneTileX - tileX) * 128 + (tileSizeX - locTileSizeX) * 64, averageY, (loc.minSceneTileZ - tileZ) * 128 + (tileSizeZ - locTileSizeZ) * 64, hideTriangles);
                            }
                        }
                    }
                }
            }

            minTileX--;
            hideTriangles = false;
        }
    }

    public void mergeNormals(Model a, Model b, int offsetX, int offsetY, int offsetZ, boolean hideTriangles) {
        normalMergeIndex++;
        int counter = 0;

        for (int vertexA = 0; vertexA < a.vertexCount; vertexA++) {
            VertexNormal normalA = a.vertexNormals[vertexA];
            VertexNormal unmodifiedNormalA = a.unmodifiedVertexNormals[vertexA];

            if (unmodifiedNormalA.magnitude == 0) {
                continue;
            }

            int vertexYA = a.vertexY[vertexA] - offsetY;
            if (vertexYA > b.minBoundY) {
                continue;
            }

            int vertexXA = a.vertexX[vertexA] - offsetX;
            if (vertexXA < b.minBoundX || vertexXA > b.maxBoundX) {
                continue;
            }

            int vertexZA = a.vertexZ[vertexA] - offsetZ;
            if (vertexZA < b.minBoundZ || vertexZA > b.maxBoundZ) {
                continue;
            }

            for (int vertexB = 0; vertexB < b.vertexCount; vertexB++) {
                VertexNormal normalB = b.vertexNormals[vertexB];
                VertexNormal unmodifiedVertexNormalB = b.unmodifiedVertexNormals[vertexB];

                if (vertexXA == b.vertexX[vertexB] && vertexZA == b.vertexZ[vertexB] && vertexYA == b.vertexY[vertexB] && unmodifiedVertexNormalB.magnitude != 0) {
                    normalA.x += unmodifiedVertexNormalB.x;
                    normalA.y += unmodifiedVertexNormalB.y;
                    normalA.z += unmodifiedVertexNormalB.z;
                    normalA.magnitude += unmodifiedVertexNormalB.magnitude;

                    normalB.x += unmodifiedNormalA.x;
                    normalB.y += unmodifiedNormalA.y;
                    normalB.z += unmodifiedNormalA.z;
                    normalB.magnitude += unmodifiedNormalA.magnitude;

                    counter++;
                    vertexAMergeIndex[vertexA] = normalMergeIndex;
                    vertexBMergeIndex[vertexB] = normalMergeIndex;
                }
            }
        }

        if (counter < 3 || !hideTriangles) {
            return;
        }

        for (int t = 0; t < a.triangleCount; t++) {
            if (vertexAMergeIndex[a.triangleVertexA[t]] == normalMergeIndex && vertexAMergeIndex[a.triangleVertexB[t]] == normalMergeIndex && vertexAMergeIndex[a.triangleVertexC[t]] == normalMergeIndex) {
                a.triangleInfo[t] = -1;
            }
        }

        for (int t = 0; t < b.triangleCount; t++) {
            if (vertexBMergeIndex[b.triangleVertexA[t]] == normalMergeIndex && vertexBMergeIndex[b.triangleVertexB[t]] == normalMergeIndex && vertexBMergeIndex[b.triangleVertexC[t]] == normalMergeIndex) {
                b.triangleInfo[t] = -1;
            }
        }
    }

    public void drawMinimapTile(int[] dst, int dstOff, int dstStep, int level, int x, int z) {
        Tile t = levelTiles[level][x][z];
        if (t == null) {
            return;
        }

        TileUnderlay underlay = t.underlay;
        if (underlay != null) {
            int rgb = underlay.color;
            if (rgb == 0) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                dst[dstOff] = rgb;
                dst[dstOff + 1] = rgb;
                dst[dstOff + 2] = rgb;
                dst[dstOff + 3] = rgb;
                dstOff += dstStep;
            }

            return;
        }

        TileOverlay overlay = t.overlay;
        if (overlay == null) {
            return;
        }

        int shape = overlay.shape;
        int rotation = overlay.rotation;
        int underlayRGB = overlay.underlayRGB;
        int overlayRGB = overlay.overlayRGB;
        int[] mask = TILE_MASK_2D[shape];
        int[] rotated = TILE_ROTATION_2D[rotation];
        int srcOff = 0;

        if (underlayRGB != 0) {
            for (int i = 0; i < 4; i++) {
                dst[dstOff] = mask[rotated[srcOff++]] != 0 ? overlayRGB : underlayRGB;
                dst[dstOff + 1] = mask[rotated[srcOff++]] != 0 ? overlayRGB : underlayRGB;
                dst[dstOff + 2] = mask[rotated[srcOff++]] != 0 ? overlayRGB : underlayRGB;
                dst[dstOff + 3] = mask[rotated[srcOff++]] != 0 ? overlayRGB : underlayRGB;
                dstOff += dstStep;
            }
        } else {
            for (int n = 0; n < 4; n++) {
                if (mask[rotated[srcOff++]] != 0) {
                    dst[dstOff] = overlayRGB;
                }

                if (mask[rotated[srcOff++]] != 0) {
                    dst[dstOff + 1] = overlayRGB;
                }

                if (mask[rotated[srcOff++]] != 0) {
                    dst[dstOff + 2] = overlayRGB;
                }

                if (mask[rotated[srcOff++]] != 0) {
                    dst[dstOff + 3] = overlayRGB;
                }

                dstOff += dstStep;
            }
        }
    }

    public static void init(int[] pitchZ, int maxZ, int width, int height, int minZ) {
        viewportLeft = 0;
        viewportTop = 0;
        viewportRight = width;
        viewportBottom = height;
        viewportCenterX = width / 2;
        viewportCenterY = height / 2;

        boolean[][][][] visibilityMap = new boolean[9][32][53][53];

        for (int pitch = 128; pitch <= 384; pitch += 32) {
            for (int yaw = 0; yaw < 2048; yaw += 64) {
                pitchSin = Model.sin[pitch];
                pitchCos = Model.cos[pitch];
                yawSin = Model.sin[yaw];
                yawCos = Model.cos[yaw];

                int pitchIndex = (pitch - 128) / 32;
                int yawIndex = yaw / 64;

                for (int x = -26; x <= 26; x++) {
                    for (int y = -26; y <= 26; y++) {
                        int sceneX = x * 128;
                        int sceneY = y * 128;
                        boolean visible = false;

                        for (int sceneZ = -minZ; sceneZ <= maxZ; sceneZ += 128) {
                            if (!isPointVisible(sceneX, sceneY, pitchZ[pitchIndex] + sceneZ)) {
                                continue;
                            }

                            visible = true;
                            break;
                        }

                        visibilityMap[pitchIndex][yawIndex][x + 25 + 1][y + 25 + 1] = visible;
                    }
                }
            }
        }

        for (int pitch = 0; pitch < 8; pitch++) {
            for (int yaw = 0; yaw < 32; yaw++) {
                for (int x = -25; x < 25; x++) {
                    for (int y = -25; y < 25; y++) {
                        boolean visible = false;

                        loop:
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dy = -1; dy <= 1; dy++) {
                                if (visibilityMap[pitch][yaw][x + dx + 25 + 1][y + dy + 25 + 1]) {
                                    visible = true;
                                } else if (visibilityMap[pitch][(yaw + 1) % 31][x + dx + 25 + 1][y + dy + 25 + 1]) {
                                    visible = true;
                                } else if (visibilityMap[pitch + 1][yaw][x + dx + 25 + 1][y + dy + 25 + 1]) {
                                    visible = true;
                                } else {
                                    if (!visibilityMap[pitch + 1][(yaw + 1) % 31][x + dx + 25 + 1][y + dy + 25 + 1]) {
                                        continue;
                                    }

                                    visible = true;
                                }

                                break loop;
                            }

                        }

                        visibilityMaps[pitch][yaw][x + 25][y + 25] = visible;
                    }
                }
            }
        }
    }

    public static boolean isPointVisible(int sceneX, int sceneY, int sceneZ) {
        int x = sceneY * yawSin + sceneX * yawCos >> 16;
        int w = sceneY * yawCos - sceneX * yawSin >> 16;
        int z = sceneZ * pitchSin + w * pitchCos >> 16;
        int y = sceneZ * pitchCos - w * pitchSin >> 16;

        if (z < 50 || z > 3500) {
            return false;
        }

        int screenX = viewportCenterX + (x << 9) / z;
        int screenY = viewportCenterY + (y << 9) / z;

        return screenX >= viewportLeft && screenX <= viewportRight && screenY >= viewportTop && screenY <= viewportBottom;
    }

    public void setClick(int clickY, int clickX) {
        checkClick = true;
        Scene.clickX = clickX;
        Scene.clickY = clickY;
        clickedTileX = -1;
        clickedTileZ = -1;
    }

    public void draw(int yaw, int x2, int plane, int pitch, int y2, int z2) {
        if (x2 < 0) {
            x2 = 0;
        } else if (x2 >= tileCountX * 128) {
            x2 = tileCountX * 128 - 1;
        }

        if (z2 < 0) {
            z2 = 0;
        } else if (z2 >= tileCountZ * 128) {
            z2 = tileCountZ * 128 - 1;
        }

        activeLevel++;

        pitchSin = Model.sin[pitch];
        pitchCos = Model.cos[pitch];

        yawSin = Model.sin[yaw];
        yawCos = Model.cos[yaw];

        visibilityMap = visibilityMaps[(pitch - 128) / 32][yaw / 64];

        cameraX2 = x2;
        cameraY2 = y2;
        cameraZ2 = z2;

        screenCenterX = x2 / 128;
        screenCenterY = z2 / 128;

        tileUpdateCount = plane;

        minTileX = screenCenterX - 25;
        if (minTileX < 0) {
            minTileX = 0;
        }

        minTileY = screenCenterY - 25;
        if (minTileY < 0) {
            minTileY = 0;
        }

        maxTileX = screenCenterX + 25;
        if (maxTileX > tileCountX) {
            maxTileX = tileCountX;
        }

        maxTileZ = screenCenterY + 25;
        if (maxTileZ > tileCountZ) {
            maxTileZ = tileCountZ;
        }

        updateOccluders();
        lastTileUpdateCount = 0;

        for (int z = minLevel; z < maxLevel; z++) {
            Tile[][] tiles = levelTiles[z];
            for (int x = minTileX; x < maxTileX; x++) {
                for (int y = minTileY; y < maxTileZ; y++) {
                    Tile tile = tiles[x][y];
                    if (tile == null) {
                        continue;
                    }

                    if (tile.physicalLevel > plane || !visibilityMap[(x - screenCenterX) + 25][(y - screenCenterY) + 25] && heightmap[z][x][y] - y2 < 2000) {
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

        for (int z = minLevel; z < maxLevel; z++) {
            Tile[][] tiles = levelTiles[z];
            for (int x = -25; x <= 0; x++) {
                int minX = screenCenterX + x;
                int maxX = screenCenterX - x;
                if (minX >= minTileX || maxX < maxTileX) {
                    for (int y = -25; y <= 0; y++) {
                        int minY = screenCenterY + y;
                        int maxY = screenCenterY - y;
                        if (minX >= minTileX) {
                            if (minY >= minTileY) {
                                Tile tile = tiles[minX][minY];
                                if (tile != null && tile.draw) {
                                    draw(tile, true);
                                }
                            }

                            if (maxY < maxTileZ) {
                                Tile tile = tiles[minX][maxY];
                                if (tile != null && tile.draw) {
                                    draw(tile, true);
                                }
                            }
                        }

                        if (maxX < maxTileX) {
                            if (minY >= minTileY) {
                                Tile tile = tiles[maxX][minY];
                                if (tile != null && tile.draw) {
                                    draw(tile, true);
                                }
                            }

                            if (maxY < maxTileZ) {
                                Tile tile = tiles[maxX][maxY];
                                if (tile != null && tile.draw) {
                                    draw(tile, true);
                                }
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

        for (int z = minLevel; z < maxLevel; z++) {
            Tile[][] tiles = levelTiles[z];
            for (int x = -25; x <= 0; x++) {
                int minX = screenCenterX + x;
                int maxX = screenCenterX - x;
                if (minX >= minTileX || maxX < maxTileX) {
                    for (int y = -25; y <= 0; y++) {
                        int minY = screenCenterY + y;
                        int maxY = screenCenterY - y;
                        if (minX >= minTileX) {
                            if (minY >= minTileY) {
                                Tile tile = tiles[minX][minY];
                                if (tile != null && tile.draw) {
                                    draw(tile, false);
                                }
                            }
                            if (maxY < maxTileZ) {
                                Tile tile = tiles[minX][maxY];
                                if (tile != null && tile.draw) {
                                    draw(tile, false);
                                }
                            }
                        }

                        if (maxX < maxTileX) {
                            if (minY >= minTileY) {
                                Tile tile = tiles[maxX][minY];
                                if (tile != null && tile.draw) {
                                    draw(tile, false);
                                }
                            }
                            if (maxY < maxTileZ) {
                                Tile tile = tiles[maxX][maxY];
                                if (tile != null && tile.draw) {
                                    draw(tile, false);
                                }
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

    public void draw(Tile t, boolean bool) {
        tileQueue.pushNext(t);

        do {
            do {
                t = (Tile) tileQueue.poll();
                if (t == null) {
                    return;
                }
            } while (!t.isVisible);

            int x = t.x;
            int z = t.z;
            int level = t.level;
            int renderLevel = t.renderLevel;
            Tile[][] tiles = levelTiles[level];

            if (t.draw) {
                if (bool) {
                    if (level > 0) {
                        Tile t0 = levelTiles[level - 1][x][z];
                        if (t0 != null && t0.isVisible) {
                            continue;
                        }
                    }

                    if (x <= screenCenterX && x > minTileX) {
                        Tile t0 = tiles[x - 1][z];
                        if (t0 != null && t0.isVisible && (t0.draw || (t.flags & 1) == 0)) {
                            continue;
                        }
                    }

                    if (x >= screenCenterX && x < maxTileX - 1) {
                        Tile t0 = tiles[x + 1][z];
                        if (t0 != null && t0.isVisible && (t0.draw || (t.flags & 4) == 0)) {
                            continue;
                        }
                    }

                    if (z <= screenCenterY && z > minTileY) {
                        Tile t0 = tiles[x][z - 1];
                        if (t0 != null && t0.isVisible && (t0.draw || (t.flags & 8) == 0)) {
                            continue;
                        }
                    }

                    if (z >= screenCenterY && z < maxTileZ - 1) {
                        Tile t0 = tiles[x][z + 1];
                        if (t0 != null && t0.isVisible && (t0.draw || (t.flags & 2) == 0)) {
                            continue;
                        }
                    }
                } else {
                    bool = true;
                }

                t.draw = false;

                if (t.bridge != null) {
                    Tile b = t.bridge;
                    if (b.underlay != null) {
                        if (!isTileOccluded(0, x, z)) {
                            drawTileUnderlay(b.underlay, 0, pitchSin, pitchCos, yawSin, yawCos, x, z);
                        }
                    } else if (b.overlay != null && !isTileOccluded(0, x, z)) {
                        drawTileOverlay(yawSin, z, b.overlay, x, pitchCos, pitchSin, yawCos);
                    }

                    Wall w = b.wall;
                    if (w != null) {
                        w.entity0.draw(0, pitchSin, pitchCos, yawSin, yawCos, w.x - cameraX2, w.y - cameraY2, w.z - cameraZ2, w.bitset);
                    }

                    for (int n = 0; n < b.locationCount; n++) {
                        Loc l = b.locs[n];
                        if (l != null) {
                            Model m = l.model;
                            if (m == null) {
                                m = l.entity.getDrawMethod();
                            }

                            m.draw(l.yaw, pitchSin, pitchCos, yawSin, yawCos, l.x - cameraX2, l.y - cameraY2, l.z - cameraZ2, l.bitset);
                        }
                    }
                }

                boolean visible = false;
                if (t.underlay != null) {
                    if (!isTileOccluded(renderLevel, x, z)) {
                        visible = true;
                        drawTileUnderlay(t.underlay, renderLevel, pitchSin, pitchCos, yawSin, yawCos, x, z);
                    }
                } else if (t.overlay != null && !isTileOccluded(renderLevel, x, z)) {
                    visible = true;
                    drawTileOverlay(yawSin, z, t.overlay, x, pitchCos, pitchSin, yawCos);
                }

                int direction = 0;
                int wallDrawFlags = 0;
                Wall w = t.wall;
                WallDecoration d = t.wallDecoration;

                if (w != null || d != null) {
                    if (screenCenterX == x) {
                        direction++;
                    } else if (screenCenterX < x) {
                        direction += 2;
                    }

                    if (screenCenterY == z) {
                        direction += 3;
                    } else if (screenCenterY > z) {
                        direction += 6;
                    }

                    wallDrawFlags = TILE_WALL_DRAW_FLAGS_0[direction];
                    t.wallDrawFlags = TILE_WALL_DRAW_FLAGS_1[direction];
                }

                if (w != null) {
                    if ((w.type0 & WALL_DRAW_FLAGS[direction]) != 0) {
                        if (w.type0 == 16) {
                            t.wallCullDirection = 0b0011;
                            t.wallUncullDirection = WALL_UNCULL_FLAGS_0[direction];
                            t.wallCullOppositeDirection = 0b0011 - t.wallUncullDirection;
                        } else if (w.type0 == 32) {
                            t.wallCullDirection = 0b0110;
                            t.wallUncullDirection = WALL_UNCULL_FLAGS_1[direction];
                            t.wallCullOppositeDirection = 0b0110 - t.wallUncullDirection;
                        } else if (w.type0 == 64) {
                            t.wallCullDirection = 0b1100;
                            t.wallUncullDirection = WALL_UNCULL_FLAGS_2[direction];
                            t.wallCullOppositeDirection = 0b1100 - t.wallUncullDirection;
                        } else {
                            t.wallCullDirection = 0b1001;
                            t.wallUncullDirection = WALL_UNCULL_FLAGS_3[direction];
                            t.wallCullOppositeDirection = 0b1001 - t.wallUncullDirection;
                        }
                    } else {
                        t.wallCullDirection = 0b0000;
                    }

                    if ((w.type0 & wallDrawFlags) != 0 && !isWallOccluded(renderLevel, x, z, w.type0)) {
                        w.entity0.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                            w.x - cameraX2, w.y - cameraY2,
                            w.z - cameraZ2, w.bitset);
                    }

                    if ((w.type1 & wallDrawFlags) != 0 && !isWallOccluded(renderLevel, x, z, w.type1)) {
                        w.entity1.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                            w.x - cameraX2, w.y - cameraY2,
                            w.z - cameraZ2, w.bitset);
                    }
                }

                if (d != null && !isOccluded(renderLevel, x, z, d.model.maxBoundY)) {
                    if ((d.type & wallDrawFlags) != 0) {
                        d.model.draw(d.rotation, pitchSin, pitchCos, yawSin,
                            yawCos, d.sceneX - cameraX2, d.sceneY - cameraY2,
                            d.sceneZ - cameraZ2, d.bitset);
                    } else if ((d.type & 0x300) != 0) {
                        int relativeX = d.sceneX - cameraX2;
                        int relativeY = d.sceneY - cameraY2;
                        int relativeZ = d.sceneZ - cameraZ2;
                        int rotation = d.rotation;

                        int rx;
                        if (rotation == 1 || rotation == 2) {
                            rx = -relativeX;
                        } else {
                            rx = relativeX;
                        }

                        int rz;
                        if (rotation == 2 || rotation == 3) {
                            rz = -relativeZ;
                        } else {
                            rz = relativeZ;
                        }

                        if ((d.type & 0x100) != 0 && rz < rx) {
                            int i10 = relativeX + DECO_TYPE1_OFFSET_X[rotation];
                            int k10 = relativeZ + DECO_TYPE1_OFFSET_Z[rotation];
                            d.model.draw(rotation * 512 + 256, pitchSin, pitchCos, yawSin, yawCos, i10, relativeY, k10, d.bitset);
                        }

                        if ((d.type & 0x200) != 0 && rz > rx) {
                            int j10 = relativeX + DECO_TYPE2_OFFSET_X[rotation];
                            int l10 = relativeZ + DECO_TYPE2_OFFSET_Z[rotation];
                            d.model.draw(rotation * 512 + 1280 & 0x7ff, pitchSin, pitchCos, yawSin, yawCos, j10, relativeY, l10, d.bitset);
                        }
                    }
                }

                if (visible) {
                    GroundDecoration decoration = t.groundDecoration;
                    if (decoration != null && decoration.model != null) {
                        decoration.model.draw(0, pitchSin, pitchCos, yawSin, yawCos, decoration.sceneX - cameraX2, decoration.sceneY - cameraY2, decoration.sceneZ - cameraZ2, decoration.bitset);
                    }

                    ObjEntity o = t.objEntity;
                    if (o != null && o.offsetY == 0) {
                        if (o.entity1 != null) {
                            o.entity1.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                                o.x - cameraX2, o.y - cameraY2,
                                o.z - cameraZ2, o.bitset);
                        }

                        if (o.entity2 != null) {
                            o.entity2.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                                o.x - cameraX2, o.y - cameraY2,
                                o.z - cameraZ2, o.bitset);
                        }

                        if (o.entity0 != null) {
                            o.entity0.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                                o.x - cameraX2, o.y - cameraY2,
                                o.z - cameraZ2, o.bitset);
                        }
                    }
                }

                int flags = t.flags;
                if (flags != 0) {
                    if (x < screenCenterX && (flags & 4) != 0) {
                        Tile t0 = tiles[x + 1][z];
                        if (t0 != null && t0.isVisible) {
                            tileQueue.pushNext(t0);
                        }
                    }

                    if (z < screenCenterY && (flags & 2) != 0) {
                        Tile t0 = tiles[x][z + 1];
                        if (t0 != null && t0.isVisible) {
                            tileQueue.pushNext(t0);
                        }
                    }

                    if (x > screenCenterX && (flags & 1) != 0) {
                        Tile t0 = tiles[x - 1][z];
                        if (t0 != null && t0.isVisible) {
                            tileQueue.pushNext(t0);
                        }
                    }

                    if (z > screenCenterY && (flags & 8) != 0) {
                        Tile t0 = tiles[x][z - 1];
                        if (t0 != null && t0.isVisible) {
                            tileQueue.pushNext(t0);
                        }
                    }
                }
            }

            if (t.wallCullDirection != 0) {
                boolean visible = true;

                for (int n = 0; n < t.locationCount; n++) {
                    if (t.locs[n].cycle == activeLevel || (t.locFlags[n] & t.wallCullDirection) != t.wallUncullDirection) {
                        continue;
                    }

                    visible = false;
                    break;
                }

                if (visible) {
                    Wall w = t.wall;
                    if (!isWallOccluded(renderLevel, x, z, w.type0)) {
                        w.entity0.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                            w.x - cameraX2, w.y - cameraY2,
                            w.z - cameraZ2, w.bitset);
                    }
                    t.wallCullDirection = 0;
                }
            }

            if (t.drawLocations) {
                int locCount = t.locationCount;
                t.drawLocations = false;
                int locBufferSize = 0;

                loop:
                for (int n = 0; n < locCount; n++) {
                    Loc l = t.locs[n];
                    if (l.cycle == activeLevel) {
                        continue;
                    }

                    for (int x0 = l.minSceneTileX; x0 <= l.maxSceneTileX; x0++) {
                        for (int z0 = l.minSceneTileZ; z0 <= l.maxSceneTileZ; z0++) {
                            Tile t0 = tiles[x0][z0];

                            if (t0.draw) {
                                t.drawLocations = true;
                            } else {
                                if (t0.wallCullDirection == 0) {
                                    continue;
                                }

                                int flags = 0;

                                if (x0 > l.minSceneTileX) {
                                    flags += 0b0001;
                                }

                                if (x0 < l.maxSceneTileX) {
                                    flags += 0b0100;
                                }

                                if (z0 > l.minSceneTileZ) {
                                    flags += 0b1000;
                                }
                                if (z0 < l.maxSceneTileZ) {
                                    flags += 0b0010;
                                }

                                if ((flags & t0.wallCullDirection) != t.wallCullOppositeDirection) {
                                    continue;
                                }

                                t.drawLocations = true;
                            }
                            continue loop;
                        }
                    }

                    locBuffer[locBufferSize++] = l;

                    int dx0 = screenCenterX - l.minSceneTileX;
                    int dx1 = l.maxSceneTileX - screenCenterX;
                    if (dx1 > dx0) {
                        dx0 = dx1;
                    }

                    int dz0 = screenCenterY - l.minSceneTileZ;
                    int dz1 = l.maxSceneTileZ - screenCenterY;
                    if (dz1 > dz0) {
                        l.distance = dx0 + dz1;
                    } else {
                        l.distance = dx0 + dz0;
                    }
                }

                while (locBufferSize > 0) {
                    int maxPriority = -50;
                    int index = -1;

                    for (int n = 0; n < locBufferSize; n++) {
                        Loc l = locBuffer[n];
                        if (l.distance > maxPriority && l.cycle != activeLevel) {
                            maxPriority = l.distance;
                            index = n;
                        }
                    }

                    if (index == -1) {
                        break;
                    }

                    Loc l = locBuffer[index];
                    l.cycle = activeLevel;
                    Model m = l.model;
                    if (m == null) {
                        m = l.entity.getDrawMethod();
                    }

                    if (!isAreaOccluded(renderLevel, l.minSceneTileX, l.maxSceneTileX, l.minSceneTileZ, l.maxSceneTileZ,
                        m.maxBoundY)) {
                        m.draw(l.yaw, pitchSin, pitchCos, yawSin, yawCos,
                            l.x - cameraX2, l.y - cameraY2,
                            l.z - cameraZ2, l.bitset);
                    }

                    for (int x0 = l.minSceneTileX; x0 <= l.maxSceneTileX; x0++) {
                        for (int z0 = l.minSceneTileZ; z0 <= l.maxSceneTileZ; z0++) {
                            Tile t0 = tiles[x0][z0];
                            if (t0.wallCullDirection != 0) {
                                tileQueue.pushNext(t0);
                            } else if ((x0 != x || z0 != z) && t0.isVisible) {
                                tileQueue.pushNext(t0);
                            }
                        }
                    }
                }

                if (t.drawLocations) {
                    continue;
                }
            }

            if (!t.isVisible || t.wallCullDirection != 0) {
                continue;
            }

            if (x <= screenCenterX && x > minTileX) {
                Tile tile_8 = tiles[x - 1][z];
                if (tile_8 != null && tile_8.isVisible) {
                    continue;
                }
            }

            if (x >= screenCenterX && x < maxTileX - 1) {
                Tile t0 = tiles[x + 1][z];
                if (t0 != null && t0.isVisible)
                    continue;
            }

            if (z <= screenCenterY && z > minTileY) {
                Tile t0 = tiles[x][z - 1];
                if (t0 != null && t0.isVisible)
                    continue;
            }

            if (z >= screenCenterY && z < maxTileZ - 1) {
                Tile t0 = tiles[x][z + 1];
                if (t0 != null && t0.isVisible)
                    continue;
            }

            t.isVisible = false;
            lastTileUpdateCount--;

            ObjEntity o = t.objEntity;
            if (o != null && o.offsetY != 0) {
                if (o.entity1 != null) {
                    o.entity1.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                        o.x - cameraX2, o.y - cameraY2 - o.offsetY,
                        o.z - cameraZ2, o.bitset);
                }

                if (o.entity2 != null) {
                    o.entity2.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                        o.x - cameraX2, o.y - cameraY2 - o.offsetY,
                        o.z - cameraZ2, o.bitset);
                }

                if (o.entity0 != null) {
                    o.entity0.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                        o.x - cameraX2, o.y - cameraY2 - o.offsetY,
                        o.z - cameraZ2, o.bitset);
                }
            }

            if (t.wallDrawFlags != 0) {
                WallDecoration decoration = t.wallDecoration;
                if (decoration != null && !isOccluded(renderLevel, x, z, decoration.model.maxBoundY)) {
                    if ((decoration.type & t.wallDrawFlags) != 0) {
                        decoration.model.draw(decoration.rotation, pitchSin, pitchCos, yawSin,
                            yawCos, decoration.sceneX - cameraX2, decoration.sceneY - cameraY2,
                            decoration.sceneZ - cameraZ2, decoration.bitset);
                    } else if ((decoration.type & 0x300) != 0) {
                        int relativeX = decoration.sceneX - cameraX2;
                        int relativeY = decoration.sceneY - cameraY2;
                        int relativeZ = decoration.sceneZ - cameraZ2;
                        int rotation = decoration.rotation;

                        int rx;
                        if (rotation == 1 || rotation == 2) {
                            rx = -relativeX;
                        } else {
                            rx = relativeX;
                        }

                        int rz;
                        if (rotation == 2 || rotation == 3) {
                            rz = -relativeZ;
                        } else {
                            rz = relativeZ;
                        }

                        if ((decoration.type & 0x100) != 0 && rz >= rx) {
                            int j8 = relativeX + DECO_TYPE1_OFFSET_X[rotation];
                            int j9 = relativeZ + DECO_TYPE1_OFFSET_Z[rotation];
                            decoration.model.draw(rotation * 512 + 256, pitchSin, pitchCos, yawSin,
                                yawCos, j8, relativeY, j9, decoration.bitset);
                        }

                        if ((decoration.type & 0x200) != 0 && rz <= rx) {
                            int k8 = relativeX + DECO_TYPE2_OFFSET_X[rotation];
                            int k9 = relativeZ + DECO_TYPE2_OFFSET_Z[rotation];
                            decoration.model.draw(rotation * 512 + 1280 & 0x7ff, pitchSin, pitchCos,
                                yawSin, yawCos, k8, relativeY, k9, decoration.bitset);
                        }
                    }
                }

                Wall w = t.wall;
                if (w != null) {
                    if ((w.type1 & t.wallDrawFlags) != 0 && !isWallOccluded(renderLevel, x, z, w.type1)) {
                        w.entity1.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                            w.x - cameraX2, w.y - cameraY2,
                            w.z - cameraZ2, w.bitset);
                    }

                    if ((w.type0 & t.wallDrawFlags) != 0 && !isWallOccluded(renderLevel, x, z, w.type0)) {
                        w.entity0.draw(0, pitchSin, pitchCos, yawSin, yawCos,
                            w.x - cameraX2, w.y - cameraY2,
                            w.z - cameraZ2, w.bitset);
                    }
                }
            }

            if (level < maxLevel - 1) {
                Tile t0 = levelTiles[level + 1][x][z];
                if (t0 != null && t0.isVisible) {
                    tileQueue.pushNext(t0);
                }
            }

            if (x < screenCenterX) {
                Tile t0 = tiles[x + 1][z];
                if (t0 != null && t0.isVisible) {
                    tileQueue.pushNext(t0);
                }
            }

            if (z < screenCenterY) {
                Tile t0 = tiles[x][z + 1];
                if (t0 != null && t0.isVisible) {
                    tileQueue.pushNext(t0);
                }
            }

            if (x > screenCenterX) {
                Tile t0 = tiles[x - 1][z];
                if (t0 != null && t0.isVisible) {
                    tileQueue.pushNext(t0);
                }
            }

            if (z > screenCenterY) {
                Tile t0 = tiles[x][z - 1];
                if (t0 != null && t0.isVisible) {
                    tileQueue.pushNext(t0);
                }
            }
        } while (true);
    }

    public void drawTileUnderlay(TileUnderlay tile, int z, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y) {
        int rx3;
        int rx0 = rx3 = (x << 7) - cameraX2;
        int rz1;
        int rz0 = rz1 = (y << 7) - cameraZ2;
        int rx2;
        int rx1 = rx2 = rx0 + 128;
        int rz3;
        int rz2 = rz3 = rz0 + 128;

        int ry0 = heightmap[z][x][y] - cameraY2;
        int ry1 = heightmap[z][x + 1][y] - cameraY2;
        int ry2 = heightmap[z][x + 1][y + 1] - cameraY2;
        int ry3 = heightmap[z][x][y + 1] - cameraY2;

        int w = rz0 * yawSin + rx0 * yawCos >> 16;
        rz0 = rz0 * yawCos - rx0 * yawSin >> 16;
        rx0 = w;

        w = ry0 * pitchCos - rz0 * pitchSin >> 16;
        rz0 = ry0 * pitchSin + rz0 * pitchCos >> 16;
        ry0 = w;

        if (rz0 < 50) {
            return;
        }

        w = rz1 * yawSin + rx1 * yawCos >> 16;
        rz1 = rz1 * yawCos - rx1 * yawSin >> 16;
        rx1 = w;

        w = ry1 * pitchCos - rz1 * pitchSin >> 16;
        rz1 = ry1 * pitchSin + rz1 * pitchCos >> 16;
        ry1 = w;

        if (rz1 < 50) {
            return;
        }

        w = rz2 * yawSin + rx2 * yawCos >> 16;
        rz2 = rz2 * yawCos - rx2 * yawSin >> 16;
        rx2 = w;

        w = ry2 * pitchCos - rz2 * pitchSin >> 16;
        rz2 = ry2 * pitchSin + rz2 * pitchCos >> 16;
        ry2 = w;

        if (rz2 < 50) {
            return;
        }

        w = rz3 * yawSin + rx3 * yawCos >> 16;
        rz3 = rz3 * yawCos - rx3 * yawSin >> 16;
        rx3 = w;

        w = ry3 * pitchCos - rz3 * pitchSin >> 16;
        rz3 = ry3 * pitchSin + rz3 * pitchCos >> 16;
        ry3 = w;

        if (rz3 < 50) {
            return;
        }

        int x0 = Draw3D.centerX + (rx0 << 9) / rz0;
        int y0 = Draw3D.centerY + (ry0 << 9) / rz0;
        int x1 = Draw3D.centerX + (rx1 << 9) / rz1;
        int y1 = Draw3D.centerY + (ry1 << 9) / rz1;
        int x2 = Draw3D.centerX + (rx2 << 9) / rz2;
        int y2 = Draw3D.centerY + (ry2 << 9) / rz2;
        int x3 = Draw3D.centerX + (rx3 << 9) / rz3;
        int y3 = Draw3D.centerY + (ry3 << 9) / rz3;

        Draw3D.alpha = 0;

        if ((x2 - x3) * (y1 - y3) - (y2 - y3) * (x1 - x3) > 0) {
            Draw3D.testX = x2 < 0 || x3 < 0 || x1 < 0 || x2 > Draw2D.rightX || x3 > Draw2D.rightX || x1 > Draw2D.rightX;

            if (checkClick && withinTriangle(clickX, clickY, y2, y3, y1, x2, x3, x1)) {
                clickedTileX = x;
                clickedTileZ = y;
            }

            if (tile.textureIndex == -1) {
                if (tile.northeastColor != 12345678) {
                    Draw3D.fillGouraudTriangle(y2, y3, y1, x2, x3, x1, tile.northeastColor, tile.northwestColor, tile.southeastColor);
                }
            } else if (!lowMemory) {
                if (tile.isFlat) {
                    Draw3D.fillTexturedTriangle(y2, y3, y1, x2, x3, x1, tile.northeastColor, tile.northwestColor, tile.southeastColor, rx0, rx1, rx3, ry0, ry1, ry3, rz0, rz1, rz3, tile.textureIndex);
                } else {
                    Draw3D.fillTexturedTriangle(y2, y3, y1, x2, x3, x1, tile.northeastColor, tile.northwestColor, tile.southeastColor, rx2, rx3, rx1, ry2, ry3, ry1, rz2, rz3, rz1, tile.textureIndex);
                }
            } else {
                int hsl = TEXTURE_HSL[tile.textureIndex];
                Draw3D.fillGouraudTriangle(y2, y3, y1, x2, x3, x1, adjustHSLLightness(tile.northeastColor, hsl), adjustHSLLightness(tile.northwestColor, hsl), adjustHSLLightness(tile.southeastColor, hsl));
            }
        }

        if ((x0 - x1) * (y3 - y1) - (y0 - y1) * (x3 - x1) > 0) {
            Draw3D.testX = x0 < 0 || x1 < 0 || x3 < 0 || x0 > Draw2D.rightX || x1 > Draw2D.rightX || x3 > Draw2D.rightX;
            if (checkClick && withinTriangle(clickX, clickY, y0, y1, y3, x0, x1, x3)) {
                clickedTileX = x;
                clickedTileZ = y;
            }

            if (tile.textureIndex == -1) {
                if (tile.southwestColor != 12345678) {
                    Draw3D.fillGouraudTriangle(y0, y1, y3, x0, x1, x3, tile.southwestColor, tile.southeastColor, tile.northwestColor);
                }
            } else if (!lowMemory) {
                Draw3D.fillTexturedTriangle(y0, y1, y3, x0, x1, x3, tile.southwestColor, tile.southeastColor, tile.northwestColor, rx0, rx1, rx3, ry0, ry1, ry3, rz0, rz1, rz3, tile.textureIndex);
            } else {
                int hsl = TEXTURE_HSL[tile.textureIndex];
                Draw3D.fillGouraudTriangle(y0, y1, y3, x0, x1, x3, adjustHSLLightness(tile.southwestColor, hsl), adjustHSLLightness(tile.southeastColor, hsl), adjustHSLLightness(tile.northwestColor, hsl));
            }
        }
    }

    public void drawTileOverlay(int yawSin, int y, TileOverlay tile, int x, int pitchCos, int pitchSin, int yawCos) {
        int count = tile.vertexX.length;

        for (int v = 0; v < count; v++) {
            int sceneX = tile.vertexX[v] - cameraX2;
            int sceneY = tile.vertexY[v] - cameraY2;
            int sceneZ = tile.vertexZ[v] - cameraZ2;

            int w = sceneZ * yawSin + sceneX * yawCos >> 16;
            sceneZ = sceneZ * yawCos - sceneX * yawSin >> 16;
            sceneX = w;

            w = sceneY * pitchCos - sceneZ * pitchSin >> 16;
            sceneZ = sceneY * pitchSin + sceneZ * pitchCos >> 16;
            sceneY = w;

            if (sceneZ < 50) {
                return;
            }

            if (tile.triangleTextureIndex != null) {
                TileOverlay.vertexSceneX[v] = sceneX;
                TileOverlay.vertexSceneY[v] = sceneY;
                TileOverlay.vertexSceneZ[v] = sceneZ;
            }

            TileOverlay.tmpScreenX[v] = Draw3D.centerX + (sceneX << 9) / sceneZ;
            TileOverlay.tmpScreenY[v] = Draw3D.centerY + (sceneY << 9) / sceneZ;
        }

        Draw3D.alpha = 0;
        count = tile.triangleVertexA.length;

        for (int t = 0; t < count; t++) {
            int a = tile.triangleVertexA[t];
            int b = tile.triangleVertexB[t];
            int c = tile.triangleVertexC[t];

            int x0 = TileOverlay.tmpScreenX[a];
            int x1 = TileOverlay.tmpScreenX[b];
            int x2 = TileOverlay.tmpScreenX[c];

            int y0 = TileOverlay.tmpScreenY[a];
            int y1 = TileOverlay.tmpScreenY[b];
            int y2 = TileOverlay.tmpScreenY[c];

            if ((x0 - x1) * (y2 - y1) - (y0 - y1) * (x2 - x1) > 0) {
                Draw3D.testX = x0 < 0 || x1 < 0 || x2 < 0 || x0 > Draw2D.rightX || x1 > Draw2D.rightX || x2 > Draw2D.rightX;
                if (checkClick && withinTriangle(clickX, clickY, y0, y1, y2, x0, x1, x2)) {
                    clickedTileX = x;
                    clickedTileZ = y;
                }

                if (tile.triangleTextureIndex == null || tile.triangleTextureIndex[t] == -1) {
                    if (tile.triangleColorA[t] != 12345678) {
                        Draw3D.fillGouraudTriangle(y0, y1, y2, x0, x1, x2, tile.triangleColorA[t], tile.triangleColorB[t], tile.triangleColorC[t]);
                    }
                } else if (!lowMemory) {
                    if (tile.isFlat) {
                        Draw3D.fillTexturedTriangle(y0, y1, y2, x0, x1, x2, tile.triangleColorA[t],
                            tile.triangleColorB[t], tile.triangleColorC[t], TileOverlay.vertexSceneX[0],
                            TileOverlay.vertexSceneX[1], TileOverlay.vertexSceneX[3], TileOverlay.vertexSceneY[0],
                            TileOverlay.vertexSceneY[1], TileOverlay.vertexSceneY[3], TileOverlay.vertexSceneZ[0],
                            TileOverlay.vertexSceneZ[1], TileOverlay.vertexSceneZ[3], tile.triangleTextureIndex[t]);
                    } else {
                        Draw3D.fillTexturedTriangle(y0, y1, y2, x0, x1, x2, tile.triangleColorA[t],
                            tile.triangleColorB[t], tile.triangleColorC[t], TileOverlay.vertexSceneX[a],
                            TileOverlay.vertexSceneX[b], TileOverlay.vertexSceneX[c], TileOverlay.vertexSceneY[a],
                            TileOverlay.vertexSceneY[b], TileOverlay.vertexSceneY[c], TileOverlay.vertexSceneZ[a],
                            TileOverlay.vertexSceneZ[b], TileOverlay.vertexSceneZ[c], tile.triangleTextureIndex[t]);
                    }
                } else {
                    int hsl = TEXTURE_HSL[tile.triangleTextureIndex[t]];
                    Draw3D.fillGouraudTriangle(y0, y1, y2, x0, x1, x2,
                        adjustHSLLightness(tile.triangleColorA[t], hsl), adjustHSLLightness(tile.triangleColorB[t], hsl),
                        adjustHSLLightness(tile.triangleColorC[t], hsl));
                }
            }
        }
    }

    public int adjustHSLLightness(int lightness, int hsl) {
        lightness = 127 - lightness;
        lightness = (lightness * (hsl & 0x7f)) / 160;
        if (lightness < 2) {
            lightness = 2;
        } else if (lightness > 126) {
            lightness = 126;
        }
        return (hsl & 0xff80) + lightness;
    }

    public boolean withinTriangle(int x, int y, int y0, int y1, int y2, int x0, int x1, int x2) {
        if (y < y0 && y < y1 && y < y2) {
            return false;
        }

        if (y > y0 && y > y1 && y > y2) {
            return false;
        }

        if (x < x0 && x < x1 && x < x2) {
            return false;
        }

        if (x > x0 && x > x1 && x > x2) {
            return false;
        }

        int a = (y - y0) * (x1 - x0) - (x - x0) * (y1 - y0);
        int b = (y - y2) * (x0 - x2) - (x - x2) * (y0 - y2);
        int c = (y - y1) * (x2 - x1) - (x - x1) * (y2 - y1);

        return a * c > 0 && c * b > 0;
    }

    public void updateOccluders() {
        int count = levelOccluderCount[tileUpdateCount];
        Occluder[] occluders = levelOccluders[tileUpdateCount];
        activeOccluderCount = 0;

        for (int n = 0; n < count; n++) {
            Occluder o = occluders[n];
            if (o.type == 1) {
                int x = (o.minTileX - screenCenterX) + 25;
                if (x < 0 || x > 50) {
                    continue;
                }

                int minZ = (o.minTileZ - screenCenterY) + 25;
                if (minZ < 0) {
                    minZ = 0;
                }

                int maxZ = (o.maxTileZ - screenCenterY) + 25;
                if (maxZ > 50) {
                    maxZ = 50;
                }

                boolean visible = false;
                while (minZ <= maxZ) {
                    if (visibilityMap[x][minZ++]) {
                        visible = true;
                        break;
                    }
                }

                if (!visible) {
                    continue;
                }

                int dx = cameraX2 - o.minX;
                if (dx > 32) {
                    o.testDirection = 1;
                } else {
                    if (dx >= -32) {
                        continue;
                    }

                    o.testDirection = 2;
                    dx = -dx;
                }

                o.minNormalZ = (o.minZ - cameraZ2 << 8) / dx;
                o.maxNormalZ = (o.maxZ - cameraZ2 << 8) / dx;
                o.minNormalY = (o.minY - cameraY2 << 8) / dx;
                o.maxNormalY = (o.maxY - cameraY2 << 8) / dx;
                activeOccluders[activeOccluderCount++] = o;
                continue;
            }

            if (o.type == 2) {
                int z = (o.minTileZ - screenCenterY) + 25;
                if (z < 0 || z > 50) {
                    continue;
                }

                int minX = (o.minTileX - screenCenterX) + 25;
                if (minX < 0) {
                    minX = 0;
                }

                int maxX = (o.maxTileX - screenCenterX) + 25;
                if (maxX > 50) {
                    maxX = 50;
                }

                boolean visible = false;
                while (minX <= maxX) {
                    if (visibilityMap[minX++][z]) {
                        visible = true;
                        break;
                    }
                }

                if (!visible) {
                    continue;
                }

                int dz = cameraZ2 - o.minZ;
                if (dz > 32) {
                    o.testDirection = 3;
                } else {
                    if (dz >= -32) {
                        continue;
                    }

                    o.testDirection = 4;
                    dz = -dz;
                }
                o.minNormalX = (o.minX - cameraX2 << 8) / dz;
                o.maxNormalX = (o.maxX - cameraX2 << 8) / dz;
                o.minNormalY = (o.minY - cameraY2 << 8) / dz;
                o.maxNormalY = (o.maxY - cameraY2 << 8) / dz;
                activeOccluders[activeOccluderCount++] = o;
            } else if (o.type == 4) {
                int y = o.minY - cameraY2;
                if (y > 128) {
                    int minZ = (o.minTileZ - screenCenterY) + 25;
                    if (minZ < 0) {
                        minZ = 0;
                    }

                    int maxZ = (o.maxTileZ - screenCenterY) + 25;
                    if (maxZ > 50) {
                        maxZ = 50;
                    }

                    if (minZ <= maxZ) {
                        int minX = (o.minTileX - screenCenterX) + 25;
                        if (minX < 0) {
                            minX = 0;
                        }

                        int maxX = (o.maxTileX - screenCenterX) + 25;
                        if (maxX > 50) {
                            maxX = 50;
                        }

                        boolean visible = false;
                        visibilityCheck:
                        for (int x = minX; x <= maxX; x++) {
                            for (int z = minZ; z <= maxZ; z++) {
                                if (!visibilityMap[x][z]) {
                                    continue;
                                }

                                visible = true;
                                break visibilityCheck;
                            }
                        }

                        if (visible) {
                            o.testDirection = 5;
                            o.minNormalX = (o.minX - cameraX2 << 8) / y;
                            o.maxNormalX = (o.maxX - cameraX2 << 8) / y;
                            o.minNormalZ = (o.minZ - cameraZ2 << 8) / y;
                            o.maxNormalZ = (o.maxZ - cameraZ2 << 8) / y;
                            activeOccluders[activeOccluderCount++] = o;
                        }
                    }
                }
            }
        }
    }

    public boolean isTileOccluded(int level, int tileX, int tileZ) {
        int cycle = levelTileCycles[level][tileX][tileZ];
        if (cycle == -activeLevel) {
            return false;
        }

        if (cycle == activeLevel) {
            return true;
        }

        int sceneX = tileX << 7;
        int sceneZ = tileZ << 7;

        if (isOccluded(sceneX + 1, heightmap[level][tileX][tileZ], sceneZ + 1)
            && isOccluded((sceneX + 128) - 1, heightmap[level][tileX + 1][tileZ], sceneZ + 1)
            && isOccluded((sceneX + 128) - 1, heightmap[level][tileX + 1][tileZ + 1], (sceneZ + 128) - 1)
            && isOccluded(sceneX + 1, heightmap[level][tileX][tileZ + 1], (sceneZ + 128) - 1)) {
            levelTileCycles[level][tileX][tileZ] = activeLevel;
            return true;
        } else {
            levelTileCycles[level][tileX][tileZ] = -activeLevel;
            return false;
        }
    }

    public boolean isWallOccluded(int level, int tileX, int tileZ, int type) {
        if (!isTileOccluded(level, tileX, tileZ)) {
            return false;
        }

        int x = tileX << 7;
        int z = tileZ << 7;
        int y = heightmap[level][tileX][tileZ] - 1;

        int ly0 = y - 120;
        int ly1 = y - 230;
        int ly2 = y - 238;

        if (type < 16) {
            if (type == 1) {
                if (x > cameraX2) {
                    if (!isOccluded(x, y, z))
                        return false;
                    if (!isOccluded(x, y, z + 128))
                        return false;
                }
                if (level > 0) {
                    if (!isOccluded(x, ly0, z))
                        return false;
                    if (!isOccluded(x, ly0, z + 128))
                        return false;
                }
                if (!isOccluded(x, ly1, z))
                    return false;
                return isOccluded(x, ly1, z + 128);
            }
            if (type == 2) {
                if (z < cameraZ2) {
                    if (!isOccluded(x, y, z + 128))
                        return false;
                    if (!isOccluded(x + 128, y, z + 128))
                        return false;
                }
                if (level > 0) {
                    if (!isOccluded(x, ly0, z + 128))
                        return false;
                    if (!isOccluded(x + 128, ly0, z + 128))
                        return false;
                }
                if (!isOccluded(x, ly1, z + 128))
                    return false;
                return isOccluded(x + 128, ly1, z + 128);
            }
            if (type == 4) {
                if (x < cameraX2) {
                    if (!isOccluded(x + 128, y, z))
                        return false;
                    if (!isOccluded(x + 128, y, z + 128))
                        return false;
                }
                if (level > 0) {
                    if (!isOccluded(x + 128, ly0, z))
                        return false;
                    if (!isOccluded(x + 128, ly0, z + 128))
                        return false;
                }
                if (!isOccluded(x + 128, ly1, z))
                    return false;
                return isOccluded(x + 128, ly1, z + 128);
            }
            if (type == 8) {
                if (z > cameraZ2) {
                    if (!isOccluded(x, y, z))
                        return false;
                    if (!isOccluded(x + 128, y, z))
                        return false;
                }
                if (level > 0) {
                    if (!isOccluded(x, ly0, z))
                        return false;
                    if (!isOccluded(x + 128, ly0, z))
                        return false;
                }
                if (!isOccluded(x, ly1, z))
                    return false;
                return isOccluded(x + 128, ly1, z);
            }
        }
        if (!isOccluded(x + 64, ly2, z + 64))
            return false;
        if (type == 16)
            return isOccluded(x, ly1, z + 128);
        if (type == 32)
            return isOccluded(x + 128, ly1, z + 128);
        if (type == 64)
            return isOccluded(x + 128, ly1, z);
        if (type == 128) {
            return isOccluded(x, ly1, z);
        } else {
            System.out.println("Warning unsupported wall type");
            return true;
        }
    }

    public boolean isOccluded(int level, int tileX, int tileZ, int height) {
        if (!isTileOccluded(level, tileX, tileZ))
            return false;
        int sceneX = tileX << 7;
        int sceneZ = tileZ << 7;
        return isOccluded(sceneX + 1, heightmap[level][tileX][tileZ] - height, sceneZ + 1)
            && isOccluded((sceneX + 128) - 1, heightmap[level][tileX + 1][tileZ] - height, sceneZ + 1)
            && isOccluded((sceneX + 128) - 1, heightmap[level][tileX + 1][tileZ + 1] - height, (sceneZ + 128) - 1)
            && isOccluded(sceneX + 1, heightmap[level][tileX][tileZ + 1] - height, (sceneZ + 128) - 1);
    }

    public boolean isAreaOccluded(int level, int minTileX, int maxTileX, int minTileZ, int maxTileZ, int height) {
        if (minTileX == maxTileX && minTileZ == maxTileZ) {
            if (!isTileOccluded(level, minTileX, minTileZ))
                return false;
            int x = minTileX << 7;
            int z = minTileZ << 7;
            return isOccluded(x + 1, heightmap[level][minTileX][minTileZ] - height, z + 1)
                && isOccluded((x + 128) - 1, heightmap[level][minTileX + 1][minTileZ] - height, z + 1)
                && isOccluded((x + 128) - 1, heightmap[level][minTileX + 1][minTileZ + 1] - height, (z + 128) - 1)
                && isOccluded(x + 1, heightmap[level][minTileX][minTileZ + 1] - height, (z + 128) - 1);
        }

        for (int x = minTileX; x <= maxTileX; x++) {
            for (int z = minTileZ; z <= maxTileZ; z++) {
                if (levelTileCycles[level][x][z] == -activeLevel) {
                    return false;
                }
            }
        }

        int minX = (minTileX << 7) + 1;
        int minZ = (minTileZ << 7) + 2;
        int minY = heightmap[level][minTileX][minTileZ] - height;
        if (!isOccluded(minX, minY, minZ))
            return false;

        int maxX = (maxTileX << 7) - 1;
        if (!isOccluded(maxX, minY, minZ))
            return false;

        int maxZ = (maxTileZ << 7) - 1;
        if (!isOccluded(minX, minY, maxZ))
            return false;

        return isOccluded(maxX, minY, maxZ);
    }

    public boolean isOccluded(int x, int y, int z) {
        for (int n = 0; n < activeOccluderCount; n++) {
            Occluder o = activeOccluders[n];
            if (o.testDirection == 1) {
                int dx = o.minX - x;
                if (dx > 0) {
                    int minZ = o.minZ + (o.minNormalZ * dx >> 8);
                    int maxZ = o.maxZ + (o.maxNormalZ * dx >> 8);
                    int minY = o.minY + (o.minNormalY * dx >> 8);
                    int maxY = o.maxY + (o.maxNormalY * dx >> 8);
                    if (z >= minZ && z <= maxZ && y >= minY && y <= maxY)
                        return true;
                }
            } else if (o.testDirection == 2) {
                int dx = x - o.minX;
                if (dx > 0) {
                    int minZ = o.minZ + (o.minNormalZ * dx >> 8);
                    int maxZ = o.maxZ + (o.maxNormalZ * dx >> 8);
                    int minY = o.minY + (o.minNormalY * dx >> 8);
                    int maxY = o.maxY + (o.maxNormalY * dx >> 8);
                    if (z >= minZ && z <= maxZ && y >= minY && y <= maxY)
                        return true;
                }
            } else if (o.testDirection == 3) {
                int dz = o.minZ - z;
                if (dz > 0) {
                    int minZ = o.minX + (o.minNormalX * dz >> 8);
                    int maxZ = o.maxX + (o.maxNormalX * dz >> 8);
                    int minY = o.minY + (o.minNormalY * dz >> 8);
                    int maxY = o.maxY + (o.maxNormalY * dz >> 8);
                    if (x >= minZ && x <= maxZ && y >= minY && y <= maxY)
                        return true;
                }
            } else if (o.testDirection == 4) {
                int dz = z - o.minZ;
                if (dz > 0) {
                    int minX = o.minX + (o.minNormalX * dz >> 8);
                    int maxX = o.maxX + (o.maxNormalX * dz >> 8);
                    int minY = o.minY + (o.minNormalY * dz >> 8);
                    int maxY = o.maxY + (o.maxNormalY * dz >> 8);
                    if (x >= minX && x <= maxX && y >= minY && y <= maxY)
                        return true;
                }
            } else if (o.testDirection == 5) {
                int dy = y - o.minY;
                if (dy > 0) {
                    int minX = o.minX + (o.minNormalX * dy >> 8);
                    int maxX = o.maxX + (o.maxNormalX * dy >> 8);
                    int minZ = o.minZ + (o.minNormalZ * dy >> 8);
                    int maxZ = o.maxZ + (o.maxNormalZ * dy >> 8);
                    if (x >= minX && x <= maxX && z >= minZ && z <= maxZ)
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
    public static int minTileX;
    public static int maxTileX;
    public static int minTileY;
    public static int maxTileZ;
    public static int screenCenterX;
    public static int screenCenterY;
    public static int cameraX2;
    public static int cameraY2;
    public static int cameraZ2;
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

    public int[][] TILE_MASK_2D = {
        new int[16],
        {
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
