package com.jagex.runescape.scene;

import com.jagex.runescape.util.Node;

public class Tile extends Node {

    public Tile(int level, int x, int z) {
        locs = new Loc[5];
        locFlags = new int[5];
        renderLevel = this.level = level;
        this.x = x;
        this.z = z;
    }

    public int level;
    public int x;
    public int z;
    public int renderLevel;
    public TileUnderlay underlay;
    public TileOverlay overlay;
    public Wall wall;
    public WallDecoration wallDecoration;
    public GroundDecoration groundDecoration;
    public ObjEntity objEntity;
    public int locationCount;
    public Loc[] locs;
    public int[] locFlags;
    public int flags;
    public int physicalLevel;
    public boolean draw;
    public boolean isVisible;
    public boolean drawLocations;
    public int wallCullDirection;
    public int wallUncullDirection;
    public int wallCullOppositeDirection;
    public int wallDrawFlags;
    public Tile bridge;
}
