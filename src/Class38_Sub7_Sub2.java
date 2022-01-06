// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class38_Sub7_Sub2 extends Class38_Sub7 {

    public Class38_Sub7_Sub2(int i, int j, boolean flag, int k, int l, int i1, int j1,
                             int k1) {
        aBoolean1377 = false;
        aClass20_1369 = Class20.aClass20Array387[j];
        anInt1371 = j1;
        if (flag)
            anInt1368 = -147;
        anInt1372 = i;
        anInt1373 = k;
        anInt1374 = i1;
        anInt1370 = k1 + l;
        aBoolean1377 = false;
    }

    public void method465(int i, int j) {
        if (j != 0)
            anInt1368 = -255;
        for (anInt1376 += i; anInt1376 > aClass20_1369.aClass18_391.anIntArray368[anInt1375]; ) {
            anInt1376 -= aClass20_1369.aClass18_391.anIntArray368[anInt1375] + 1;
            anInt1375++;
            if (anInt1375 >= aClass20_1369.aClass18_391.anInt365
                    && (anInt1375 < 0 || anInt1375 >= aClass20_1369.aClass18_391.anInt365)) {
                anInt1375 = 0;
                aBoolean1377 = true;
            }
        }

    }

    public Class38_Sub2_Sub1 method462(boolean flag) {
        Class38_Sub2_Sub1 class38_sub2_sub1 = aClass20_1369.method226();
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = new Class38_Sub2_Sub1(class38_sub2_sub1, true,
                !aClass20_1369.aBoolean392, anInt1367, false);
        if (!flag)
            throw new NullPointerException();
        if (!aBoolean1377) {
            class38_sub2_sub1_1.method357(4);
            class38_sub2_sub1_1.method358(-16599, aClass20_1369.aClass18_391.anIntArray366[anInt1375]);
            class38_sub2_sub1_1.anIntArrayArray1255 = null;
            class38_sub2_sub1_1.anIntArrayArray1254 = null;
        }
        if (aClass20_1369.anInt395 != 128 || aClass20_1369.anInt396 != 128)
            class38_sub2_sub1_1.method366(aClass20_1369.anInt395, 2, aClass20_1369.anInt396, aClass20_1369.anInt395);
        if (aClass20_1369.anInt397 != 0) {
            if (aClass20_1369.anInt397 == 90)
                class38_sub2_sub1_1.method361(0);
            if (aClass20_1369.anInt397 == 180) {
                class38_sub2_sub1_1.method361(0);
                class38_sub2_sub1_1.method361(0);
            }
            if (aClass20_1369.anInt397 == 270) {
                class38_sub2_sub1_1.method361(0);
                class38_sub2_sub1_1.method361(0);
                class38_sub2_sub1_1.method361(0);
            }
        }
        class38_sub2_sub1_1.method367(64 + aClass20_1369.anInt398, 850 + aClass20_1369.anInt399, -30, -50, -30, true);
        return class38_sub2_sub1_1;
    }

    public int anInt1367;
    public int anInt1368;
    public Class20 aClass20_1369;
    public int anInt1370;
    public int anInt1371;
    public int anInt1372;
    public int anInt1373;
    public int anInt1374;
    public int anInt1375;
    public int anInt1376;
    public boolean aBoolean1377;
}
