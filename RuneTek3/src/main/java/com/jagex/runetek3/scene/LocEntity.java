package com.jagex.runetek3.scene;

import com.jagex.runetek3.graphics.SeqType;
import com.jagex.runetek3.util.Node;

public class LocEntity extends Node {

    public int level;
    public int classType;
    public int tileX;
    public int tileZ;
    public int locIndex;
    public SeqType seq;
    public int seqFrame;
    public int seqCycle;
    public LocEntity(int locIndex, int level, int classType, int tileX, int tileZ, SeqType seq, boolean animated) {
        this.level = level;
        this.classType = classType;
        this.tileX = tileX;
        this.tileZ = tileZ;
        this.locIndex = locIndex;
        this.seq = seq;

        if (animated && seq.delay != -1) {
            seqFrame = (int) (Math.random() * (double) this.seq.frameCount);
            seqCycle = (int) (Math.random() * (double) this.seq.frameDelay[seqFrame]);
        } else {
            seqFrame = -1;
            seqCycle = 0;
        }
    }
}
