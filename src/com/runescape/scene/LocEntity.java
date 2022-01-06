package com.runescape.scene;

import com.runescape.util.Node;
import com.runescape.cache.SeqType;

public class LocEntity extends Node {

    public LocEntity(boolean flag, int i, int j, int k, int l, SeqType seqType, int i1,
                     int j1) {
        anInt1206 = j;
        anInt1207 = l;
        anInt1208 = j1;
        anInt1209 = i1;
        if (k != 0)
            throw new NullPointerException();
        anInt1210 = i;
        this.seqType = seqType;
        if (flag && seqType.anInt369 != -1) {
            anInt1212 = (int) (Math.random() * (double) this.seqType.anInt365);
            anInt1213 = (int) (Math.random() * (double) this.seqType.anIntArray368[anInt1212]);
            return;
        } else {
            anInt1212 = -1;
            anInt1213 = 0;
            return;
        }
    }

    public int anInt1206;
    public int anInt1207;
    public int anInt1208;
    public int anInt1209;
    public int anInt1210;
    public SeqType seqType;
    public int anInt1212;
    public int anInt1213;
}
