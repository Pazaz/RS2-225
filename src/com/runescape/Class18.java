package com.runescape;

public class Class18 {

    public static void method222(Class39 class39, int i) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, class39.method474("seq.dat", null, (byte) 2));
        if (i <= 0)
            aBoolean361 = !aBoolean361;
        anInt363 = class38_sub2_sub3.method448();
        if (aClass18Array364 == null)
            aClass18Array364 = new Class18[anInt363];
        for (int j = 0; j < anInt363; j++) {
            if (aClass18Array364[j] == null)
                aClass18Array364[j] = new Class18();
            aClass18Array364[j].method223(false, class38_sub2_sub3);
        }

    }

    public void method223(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        if (flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        do {
            int j = class38_sub2_sub3.method446();
            if (j == 0)
                break;
            if (j == 1) {
                anInt365 = class38_sub2_sub3.method446();
                primaryFrames = new int[anInt365];
                anIntArray367 = new int[anInt365];
                anIntArray368 = new int[anInt365];
                for (int k = 0; k < anInt365; k++) {
                    primaryFrames[k] = class38_sub2_sub3.method448();
                    anIntArray367[k] = class38_sub2_sub3.method448();
                    if (anIntArray367[k] == 65535)
                        anIntArray367[k] = -1;
                    anIntArray368[k] = class38_sub2_sub3.method448();
                    if (anIntArray368[k] == 0)
                        anIntArray368[k] = Class11.aClass11Array235[primaryFrames[k]].anInt236;
                    if (anIntArray368[k] == 0)
                        anIntArray368[k] = 1;
                }

            } else if (j == 2)
                anInt369 = class38_sub2_sub3.method448();
            else if (j == 3) {
                int l = class38_sub2_sub3.method446();
                anIntArray370 = new int[l + 1];
                for (int i1 = 0; i1 < l; i1++)
                    anIntArray370[i1] = class38_sub2_sub3.method446();

                anIntArray370[l] = 0x98967f;
            } else if (j == 4)
                aBoolean371 = true;
            else if (j == 5)
                anInt372 = class38_sub2_sub3.method446();
            else if (j == 6)
                anInt373 = class38_sub2_sub3.method448();
            else if (j == 7)
                anInt374 = class38_sub2_sub3.method448();
            else if (j == 8)
                anInt375 = class38_sub2_sub3.method446();
            else
                System.out.println("Error unrecognised seq config code: " + j);
        } while (true);
        if (anInt365 == 0) {
            anInt365 = 1;
            primaryFrames = new int[1];
            primaryFrames[0] = -1;
            anIntArray367 = new int[1];
            anIntArray367[0] = -1;
            anIntArray368 = new int[1];
            anIntArray368[0] = -1;
        }
    }

    public Class18() {
        anInt369 = -1;
        aBoolean371 = false;
        anInt372 = 5;
        anInt373 = -1;
        anInt374 = -1;
        anInt375 = 99;
    }

    public static boolean aBoolean361 = true;
    public static int anInt362 = 473;
    public static int anInt363;
    public static Class18[] aClass18Array364;
    public int anInt365;
    public int[] primaryFrames;
    public int[] anIntArray367;
    public int[] anIntArray368;
    public int anInt369;
    public int[] anIntArray370;
    public boolean aBoolean371;
    public int anInt372;
    public int anInt373;
    public int anInt374;
    public int anInt375;
    public static boolean aBoolean376;

}
