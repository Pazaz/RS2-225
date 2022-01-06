package com.runescape;

public class Class20 {

    public static void method224(Class39 class39, int i) {
        i = 91 / i;
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363,
                class39.method474("spotanim.dat", null, (byte) 2));
        anInt386 = class38_sub2_sub3.method448();
        if (aClass20Array387 == null)
            aClass20Array387 = new Class20[anInt386];
        for (int j = 0; j < anInt386; j++) {
            if (aClass20Array387[j] == null)
                aClass20Array387[j] = new Class20();
            aClass20Array387[j].anInt388 = j;
            aClass20Array387[j].method225(false, class38_sub2_sub3);
        }

    }

    public void method225(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
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
                if (Class18.aClass18Array364 != null)
                    seq = Class18.aClass18Array364[anInt390];
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

    public Class38_Sub2_Sub1 getModel() {
        Class38_Sub2_Sub1 class38_sub2_sub1 = (Class38_Sub2_Sub1) aClass34_400.method341(anInt388);
        if (class38_sub2_sub1 != null)
            return class38_sub2_sub1;
        class38_sub2_sub1 = new Class38_Sub2_Sub1(false, anInt389);
        for (int i = 0; i < 6; i++)
            if (anIntArray393[0] != 0)
                class38_sub2_sub1.method364(anIntArray393[i], anIntArray394[i]);

        aClass34_400.method342(6, anInt388, class38_sub2_sub1);
        return class38_sub2_sub1;
    }

    public Class20() {
        anInt390 = -1;
        disposeAlpha = false;
        anIntArray393 = new int[6];
        anIntArray394 = new int[6];
        anInt395 = 128;
        anInt396 = 128;
    }

    public static int anInt385 = 473;
    public static int anInt386;
    public static Class20[] aClass20Array387;
    public int anInt388;
    public int anInt389;
    public int anInt390;
    public Class18 seq;
    public boolean disposeAlpha;
    public int[] anIntArray393;
    public int[] anIntArray394;
    public int anInt395;
    public int anInt396;
    public int anInt397;
    public int anInt398;
    public int anInt399;
    public static Class34 aClass34_400 = new Class34((byte) 0, 30);

}
