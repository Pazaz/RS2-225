package com.runescape.scene;

import com.runescape.cache.SeqType;
import com.runescape.util.Node;

public class LocEntity extends Node {

    public LocEntity(boolean flag, int i, int j, int l, SeqType seq, int i1, int j1) {
        anInt1206 = j;
        classType = l;
        anInt1208 = j1;
        anInt1209 = i1;
        locIndex = i;
        this.seq = seq;

        if (flag && seq.delta != -1) {
            currentFrameId = (int) (Math.random() * (double) this.seq.frameCount);
            currentFrameDuration = (int) (Math.random() * (double) this.seq.frameDelay[currentFrameId]);
        } else {
            currentFrameId = -1;
            currentFrameDuration = 0;
        }
    }

    public int anInt1206;
    public int classType;
    public int anInt1208;
    public int anInt1209;
    public int locIndex;
    public SeqType seq;
    public int currentFrameId;
    public int currentFrameDuration;
}
