package com.runescape;

public class VarpType {

    public static void method227(FileArchive fileArchive, int i) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, fileArchive.read("varp.dat", null));
        anInt421 = 0;
        i = 28 / i;
        anInt419 = class38_sub2_sub3.method448();
        if (varpTypes == null)
            varpTypes = new VarpType[anInt419];
        if (anIntArray422 == null)
            anIntArray422 = new int[anInt419];
        for (int j = 0; j < anInt419; j++) {
            if (varpTypes[j] == null)
                varpTypes[j] = new VarpType();
            varpTypes[j].method228(anInt418, j, class38_sub2_sub3);
        }

    }

    public void method228(int i, int j, Class38_Sub2_Sub3 class38_sub2_sub3) {
        if (i != 13703)
            return;
        do {
            int k = class38_sub2_sub3.method446();
            if (k == 0)
                return;
            if (k == 1)
                anInt424 = class38_sub2_sub3.method446();
            else if (k == 2)
                anInt425 = class38_sub2_sub3.method446();
            else if (k == 3) {
                aBoolean426 = true;
                anIntArray422[anInt421++] = j;
            } else if (k == 4)
                aBoolean427 = false;
            else if (k == 5)
                anInt428 = class38_sub2_sub3.method448();
            else if (k == 6)
                aBoolean429 = true;
            else if (k == 7)
                anInt430 = class38_sub2_sub3.method451();
            else if (k == 8)
                aBoolean431 = true;
            else if (k == 10)
                aString423 = class38_sub2_sub3.method453();
            else
                System.out.println("Error unrecognised config code: " + k);
        } while (true);
    }

    public VarpType() {
        aBoolean426 = false;
        aBoolean427 = true;
        aBoolean429 = false;
        aBoolean431 = false;
    }

    public static int anInt417 = 473;
    public static int anInt418 = 13703;
    public static int anInt419;
    public static VarpType[] varpTypes;
    public static int anInt421;
    public static int[] anIntArray422;
    public String aString423;
    public int anInt424;
    public int anInt425;
    public boolean aBoolean426;
    public boolean aBoolean427;
    public int anInt428;
    public boolean aBoolean429;
    public int anInt430;
    public boolean aBoolean431;

}
