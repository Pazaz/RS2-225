package com.runescape.cache;

import com.runescape.util.Buffer;
import com.runescape.util.Cache;

public class SpotAnimType {

    public static void method224(FileArchive fileArchive, int i) {
        i = 91 / i;
        Buffer class38_sub2_sub3 = new Buffer(363,
                fileArchive.read("spotanim.dat", null));
        anInt386 = class38_sub2_sub3.method448();
        if (spotAnimTypes == null)
            spotAnimTypes = new SpotAnimType[anInt386];
        for (int j = 0; j < anInt386; j++) {
            if (spotAnimTypes[j] == null)
                spotAnimTypes[j] = new SpotAnimType();
            spotAnimTypes[j].anInt388 = j;
            spotAnimTypes[j].method225(false, class38_sub2_sub3);
        }

    }

    public void method225(boolean flag, Buffer class38_sub2_sub3) {
        if (flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        do {
            int j = class38_sub2_sub3.method446();
            if (j == 0)
                return;
            if (j == 1)
                anInt389 = class38_sub2_sub3.method448();
            else if (j == 2) {
                anInt390 = class38_sub2_sub3.method448();
                if (SeqType.seqTypes != null)
                    seq = SeqType.seqTypes[anInt390];
            } else if (j == 3)
                disposeAlpha = true;
            else if (j == 4)
                anInt395 = class38_sub2_sub3.method448();
            else if (j == 5)
                anInt396 = class38_sub2_sub3.method448();
            else if (j == 6)
                anInt397 = class38_sub2_sub3.method448();
            else if (j == 7)
                anInt398 = class38_sub2_sub3.method446();
            else if (j == 8)
                anInt399 = class38_sub2_sub3.method446();
            else if (j >= 40 && j < 50)
                anIntArray393[j - 40] = class38_sub2_sub3.method448();
            else if (j >= 50 && j < 60)
                anIntArray394[j - 50] = class38_sub2_sub3.method448();
            else
                System.out.println("Error unrecognised spotanim config code: " + j);
        } while (true);
    }

    public Model getModel() {
        Model class38_sub2_sub1 = (Model) cache.method341(anInt388);
        if (class38_sub2_sub1 != null)
            return class38_sub2_sub1;
        class38_sub2_sub1 = new Model(false, anInt389);
        for (int i = 0; i < 6; i++)
            if (anIntArray393[0] != 0)
                class38_sub2_sub1.method364(anIntArray393[i], anIntArray394[i]);

        cache.method342(6, anInt388, class38_sub2_sub1);
        return class38_sub2_sub1;
    }

    public SpotAnimType() {
        anInt390 = -1;
        disposeAlpha = false;
        anIntArray393 = new int[6];
        anIntArray394 = new int[6];
        anInt395 = 128;
        anInt396 = 128;
    }

    public static int anInt385 = 473;
    public static int anInt386;
    public static SpotAnimType[] spotAnimTypes;
    public int anInt388;
    public int anInt389;
    public int anInt390;
    public SeqType seq;
    public boolean disposeAlpha;
    public int[] anIntArray393;
    public int[] anIntArray394;
    public int anInt395;
    public int anInt396;
    public int anInt397;
    public int anInt398;
    public int anInt399;
    public static Cache cache = new Cache((byte) 0, 30);

}
