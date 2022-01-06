package com.runescape;

public class Class38_Sub7_Sub1 extends Entity {

    public Class38_Sub7_Sub1(int i, int j, int k, int l, int i1, int j1, int k1,
                             int l1, int i2, int j2, int k2, int l2) {
        anInt1342 = -159;
        aBoolean1354 = false;
        aClass20_1343 = Class20.aClass20Array387[k2];
        if (i2 != 0) {
            throw new NullPointerException();
        } else {
            anInt1344 = i1;
            anInt1345 = l2;
            anInt1346 = k;
            anInt1347 = j2;
            anInt1349 = k1;
            anInt1350 = l;
            anInt1351 = j;
            anInt1352 = l1;
            anInt1353 = j1;
            anInt1348 = i;
            aBoolean1354 = false;
            return;
        }
    }

    public void method463(int i, int j, int k, int l, int i1) {
        if (!aBoolean1354) {
            double d = k - anInt1345;
            double d2 = j - anInt1346;
            double d3 = Math.sqrt(d * d + d2 * d2);
            aDouble1355 = (double) anInt1345 + (d * (double) anInt1352) / d3;
            aDouble1356 = (double) anInt1346 + (d2 * (double) anInt1352) / d3;
            aDouble1357 = anInt1347;
        }
        double d1 = (anInt1350 + 1) - i1;
        aDouble1358 = ((double) k - aDouble1355) / d1;
        aDouble1359 = ((double) j - aDouble1356) / d1;
        aDouble1360 = Math.sqrt(aDouble1358 * aDouble1358 + aDouble1359 * aDouble1359);
        if (!aBoolean1354)
            aDouble1361 = -aDouble1360 * Math.tan((double) anInt1351 * 0.02454369D);
        aDouble1362 = (2D * ((double) i - aDouble1357 - aDouble1361 * d1)) / (d1 * d1);
        if (l < 0)
            ;
    }

    public void method464(byte byte0, int i) {
        if (byte0 != -30)
            anInt1342 = -454;
        aBoolean1354 = true;
        aDouble1355 += aDouble1358 * (double) i;
        aDouble1356 += aDouble1359 * (double) i;
        aDouble1357 += aDouble1361 * (double) i + 0.5D * aDouble1362 * (double) i * (double) i;
        aDouble1361 += aDouble1362 * (double) i;
        anInt1363 = (int) (Math.atan2(aDouble1358, aDouble1359) * 325.94900000000001D) + 1024 & 0x7ff;
        anInt1364 = (int) (Math.atan2(aDouble1361, aDouble1360) * 325.94900000000001D) & 0x7ff;
        if (aClass20_1343.aClass18_391 != null)
            for (anInt1366 += i; anInt1366 > aClass20_1343.aClass18_391.anIntArray368[anInt1365]; ) {
                anInt1366 -= aClass20_1343.aClass18_391.anIntArray368[anInt1365] + 1;
                anInt1365++;
                if (anInt1365 >= aClass20_1343.aClass18_391.anInt365)
                    anInt1365 = 0;
            }

    }

    public Class38_Sub2_Sub1 method462(boolean flag) {
        Class38_Sub2_Sub1 class38_sub2_sub1 = aClass20_1343.method226();
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = new Class38_Sub2_Sub1(class38_sub2_sub1, true,
                !aClass20_1343.aBoolean392, anInt1341, false);
        if (!flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        if (aClass20_1343.aClass18_391 != null) {
            class38_sub2_sub1_1.method357(4);
            class38_sub2_sub1_1.method358(-16599, aClass20_1343.aClass18_391.anIntArray366[anInt1365]);
            class38_sub2_sub1_1.anIntArrayArray1255 = null;
            class38_sub2_sub1_1.anIntArrayArray1254 = null;
        }
        if (aClass20_1343.anInt395 != 128 || aClass20_1343.anInt396 != 128)
            class38_sub2_sub1_1.method366(aClass20_1343.anInt395, 2, aClass20_1343.anInt396, aClass20_1343.anInt395);
        class38_sub2_sub1_1.method362((byte) 7, anInt1364);
        class38_sub2_sub1_1.method367(64 + aClass20_1343.anInt398, 850 + aClass20_1343.anInt399, -30, -50, -30, true);
        return class38_sub2_sub1_1;
    }

    public int anInt1341;
    public int anInt1342;
    public Class20 aClass20_1343;
    public int anInt1344;
    public int anInt1345;
    public int anInt1346;
    public int anInt1347;
    public int anInt1348;
    public int anInt1349;
    public int anInt1350;
    public int anInt1351;
    public int anInt1352;
    public int anInt1353;
    public boolean aBoolean1354;
    public double aDouble1355;
    public double aDouble1356;
    public double aDouble1357;
    public double aDouble1358;
    public double aDouble1359;
    public double aDouble1360;
    public double aDouble1361;
    public double aDouble1362;
    public int anInt1363;
    public int anInt1364;
    public int anInt1365;
    public int anInt1366;
}
