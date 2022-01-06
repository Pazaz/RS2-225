package com.runescape.scene;

import com.runescape.util.Node;
import com.runescape.cache.SeqType;

public class LocEntity extends Node {

    public LocEntity(boolean flag, int i, int j, int l, SeqType seq, int i1, int j1) {
        anInt1206 = j;
        anInt1207 = l;
        anInt1208 = j1;
        anInt1209 = i1;
        anInt1210 = i;
        this.seq = seq;

        if (flag && seq.loopOffset != -1) {
            currentFrameId = (int) (Math.random() * (double) this.seq.frameCount);
            currentFrameDuration = (int) (Math.random() * (double) this.seq.instances[currentFrameId]);
        } else {
            currentFrameId = -1;
            currentFrameDuration = 0;
        }
    }

    public int anInt1206;
    public int anInt1207;
    public int anInt1208;
    public int anInt1209;
    public int anInt1210;
    public SeqType seq;
    public int currentFrameId;
    public int currentFrameDuration;
}
