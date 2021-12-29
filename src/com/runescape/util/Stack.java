package com.runescape.util;

public class Stack {

    public Stack(int i) {
        anInt499 = 679;
        aByte500 = 2;
        aBoolean501 = true;
        aClass38_Sub2_502 = new CacheableNode();
        aClass38_Sub2_502.aClass38_Sub2_1184 = aClass38_Sub2_502;
        if (i < 5 || i > 5)
            anInt499 = -426;
        aClass38_Sub2_502.aClass38_Sub2_1185 = aClass38_Sub2_502;
    }

    public void method275(CacheableNode class38_sub2) {
        if (class38_sub2.aClass38_Sub2_1185 != null)
            class38_sub2.method350();
        class38_sub2.aClass38_Sub2_1185 = aClass38_Sub2_502.aClass38_Sub2_1185;
        class38_sub2.aClass38_Sub2_1184 = aClass38_Sub2_502;
        class38_sub2.aClass38_Sub2_1185.aClass38_Sub2_1184 = class38_sub2;
        class38_sub2.aClass38_Sub2_1184.aClass38_Sub2_1185 = class38_sub2;
    }

    public CacheableNode method276() {
        CacheableNode class38_sub2 = aClass38_Sub2_502.aClass38_Sub2_1184;
        if (class38_sub2 == aClass38_Sub2_502) {
            return null;
        } else {
            class38_sub2.method350();
            return class38_sub2;
        }
    }

    public int anInt499;
    public byte aByte500;
    public boolean aBoolean501;
    public CacheableNode aClass38_Sub2_502;
}
