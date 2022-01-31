package com.jagex.runescape.scene;

import com.jagex.runescape.util.Node;

public class TemporaryLoc extends Node {

    public TemporaryLoc(int level, int rotation, int tileZ, int lastCycle, int type, int locIndex,
                        int tileX, int classType) {
        this.level = level;
        this.classType = classType;
        this.tileX = tileX;
        this.tileZ = tileZ;
        this.locIndex = locIndex;
        this.rotation = rotation;
        this.type = type;
        this.lastCycle = lastCycle;
    }

    public int level;
    public int classType;
    public int tileX;
    public int tileZ;
    public int locIndex;
    public int rotation;
    public int type;
    public int lastCycle;
}
