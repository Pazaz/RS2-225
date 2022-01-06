package com.runescape;

public class Class38_Sub5 extends Node {

    public Class38_Sub5(boolean flag, int i, int j, int k, int l, Class18 class18, int i1,
                        int j1) {
        anInt1206 = j;
        anInt1207 = l;
        anInt1208 = j1;
        anInt1209 = i1;
        if (k != 0)
            throw new NullPointerException();
        anInt1210 = i;
        aClass18_1211 = class18;
        if (flag && class18.anInt369 != -1) {
            anInt1212 = (int) (Math.random() * (double) aClass18_1211.anInt365);
            anInt1213 = (int) (Math.random() * (double) aClass18_1211.anIntArray368[anInt1212]);
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
    public Class18 aClass18_1211;
    public int anInt1212;
    public int anInt1213;
}
