// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class38_Sub2_Sub2 extends Class38_Sub2 {

    public static void method376(int i, int[] ai, int j, int k) {
        anIntArray1308 = ai;
        anInt1309 = i;
        while (j >= 0)
            anInt1305 = -151;
        anInt1310 = k;
        method378(k, 0, i, 789, 0);
    }

    public static void method377(int i) {
        anInt1313 = 0;
        anInt1311 = 0;
        anInt1314 = anInt1309;
        if (i != 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        anInt1312 = anInt1310;
        anInt1315 = anInt1314 - 1;
        anInt1316 = anInt1314 / 2;
    }

    public static void method378(int i, int j, int k, int l, int i1) {
        if (i1 < 0)
            i1 = 0;
        if (j < 0)
            j = 0;
        if (k > anInt1309)
            k = anInt1309;
        if (i > anInt1310)
            i = anInt1310;
        anInt1313 = i1;
        anInt1311 = j;
        anInt1314 = k;
        anInt1312 = i;
        anInt1315 = anInt1314 - 1;
        anInt1316 = anInt1314 / 2;
        if (l <= 0) {
            for (int j1 = 1; j1 > 0; j1++)
                ;
        }
        anInt1317 = anInt1312 / 2;
    }

    public static void method379(int i) {
        i = 87 / i;
        int j = anInt1309 * anInt1310;
        for (int k = 0; k < j; k++)
            anIntArray1308[k] = 0;

    }

    public static void method380(int i, int j, int k, byte byte0, int l, int i1) {
        if (byte0 != 93)
            anInt1305 = 289;
        if (j < anInt1313) {
            l -= anInt1313 - j;
            j = anInt1313;
        }
        if (i < anInt1311) {
            i1 -= anInt1311 - i;
            i = anInt1311;
        }
        if (j + l > anInt1314)
            l = anInt1314 - j;
        if (i + i1 > anInt1312)
            i1 = anInt1312 - i;
        int j1 = anInt1309 - l;
        int k1 = j + i * anInt1309;
        for (int l1 = -i1; l1 < 0; l1++) {
            for (int i2 = -l; i2 < 0; i2++)
                anIntArray1308[k1++] = k;

            k1 += j1;
        }

    }

    public static void method381(int i, int j, int k, int l, int i1, int j1) {
        if (i < 3 || i > 3) {
            return;
        } else {
            method382(k, 0, i1, j1, j);
            method382(k, 0, (i1 + l) - 1, j1, j);
            method383(k, anInt1306, i1, l, j);
            method383(k, anInt1306, i1, l, (j + j1) - 1);
            return;
        }
    }

    public static void method382(int i, int j, int k, int l, int i1) {
        if (k < anInt1311 || k >= anInt1312)
            return;
        if (i1 < anInt1313) {
            l -= anInt1313 - i1;
            i1 = anInt1313;
        }
        if (i1 + l > anInt1314)
            l = anInt1314 - i1;
        int j1 = i1 + k * anInt1309;
        if (j != 0)
            return;
        for (int k1 = 0; k1 < l; k1++)
            anIntArray1308[j1 + k1] = i;

    }

    public static void method383(int i, int j, int k, int l, int i1) {
        if (i1 < anInt1313 || i1 >= anInt1314)
            return;
        if (k < anInt1311) {
            l -= anInt1311 - k;
            k = anInt1311;
        }
        if (k + l > anInt1312)
            l = anInt1312 - k;
        int j1 = i1 + k * anInt1309;
        if (j != 0)
            anInt1306 = 71;
        for (int k1 = 0; k1 < l; k1++)
            anIntArray1308[j1 + k1 * anInt1309] = i;

    }

    public Class38_Sub2_Sub2() {
    }

    public static int anInt1305;
    public static int anInt1306;
    public static boolean aBoolean1307 = true;
    public static int[] anIntArray1308;
    public static int anInt1309;
    public static int anInt1310;
    public static int anInt1311;
    public static int anInt1312;
    public static int anInt1313;
    public static int anInt1314;
    public static int anInt1315;
    public static int anInt1316;
    public static int anInt1317;
    public static int anInt1318;

}
