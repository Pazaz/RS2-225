package com.runescape.util;

public class Cache {

    public Cache(byte byte0, int i) {
        aBoolean590 = false;
        aClass36_594 = new Hashtable(9, 1024);
        aClass29_595 = new Stack(anInt591);
        anInt592 = i;
        if (byte0 != 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        anInt593 = i;
    }

    public CacheableNode method341(long l) {
        CacheableNode class38_sub2 = (CacheableNode) aClass36_594.method344(l);
        if (class38_sub2 != null)
            aClass29_595.method275(class38_sub2);
        return class38_sub2;
    }

    public void method342(int i, long l, CacheableNode class38_sub2) {
        if (anInt593 == 0) {
            CacheableNode class38_sub2_1 = aClass29_595.method276();
            class38_sub2_1.method349();
            class38_sub2_1.method350();
        } else {
            anInt593--;
        }
        aClass36_594.method345(l, -566, class38_sub2);
        if (i < 6 || i > 6)
            aBoolean590 = !aBoolean590;
        aClass29_595.method275(class38_sub2);
    }

    public void method343() {
        do {
            CacheableNode class38_sub2 = aClass29_595.method276();
            if (class38_sub2 != null) {
                class38_sub2.method349();
                class38_sub2.method350();
            } else {
                anInt593 = anInt592;
                return;
            }
        } while (true);
    }

    public boolean aBoolean590;
    public static int anInt591 = 5;
    public int anInt592;
    public int anInt593;
    public Hashtable aClass36_594;
    public Stack aClass29_595;

}
