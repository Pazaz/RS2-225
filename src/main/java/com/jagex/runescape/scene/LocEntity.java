package com.jagex.runescape.scene;

import com.jagex.runescape.cache.SeqType;
import com.jagex.runescape.util.Node;

public class LocEntity extends Node {

    public LocEntity(boolean flag, int locIndex, int level, int classType, SeqType seq, int tileZ, int tileX) {
        this.level = level;
        this.classType = classType;
        this.tileX = tileX;
        this.tileZ = tileZ;
        this.locIndex = locIndex;
        this.seq = seq;

        if (flag && seq.delta != -1) {
            seqFrame = (int) (Math.random() * (double) this.seq.frameCount);
            seqCycle = (int) (Math.random() * (double) this.seq.frameDelay[seqFrame]);
        } else {
            seqFrame = -1;
            seqCycle = 0;
        }
    }

    public int level;
    public int classType;
    public int tileX;
    public int tileZ;
    public int locIndex;
    public SeqType seq;
    public int seqFrame;
    public int seqCycle;
}
