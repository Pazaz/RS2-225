// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class38_Sub7_Sub3_Sub2 extends Class38_Sub7_Sub3 {

    public void method470(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        class38_sub2_sub3.anInt1329 = 0;
        anInt1507 = class38_sub2_sub3.method446();
        anInt1508 = class38_sub2_sub3.method446();
        for (int i = 0; i < 12; i++) {
            int j = class38_sub2_sub3.method446();
            if (j == 0) {
                anIntArray1509[i] = 0;
            } else {
                int l = class38_sub2_sub3.method446();
                anIntArray1509[i] = (j << 8) + l;
            }
        }

        for (int k = 0; k < 5; k++) {
            int i1 = class38_sub2_sub3.method446();
            if (i1 < 0 || i1 >= client.anIntArrayArray942[k].length)
                i1 = 0;
            anIntArray1510[k] = i1;
        }

        if (flag)
            return;
        super.anInt1385 = class38_sub2_sub3.method448();
        if (super.anInt1385 == 65535)
            super.anInt1385 = -1;
        super.anInt1386 = class38_sub2_sub3.method448();
        if (super.anInt1386 == 65535)
            super.anInt1386 = -1;
        super.anInt1387 = class38_sub2_sub3.method448();
        if (super.anInt1387 == 65535)
            super.anInt1387 = -1;
        super.anInt1388 = class38_sub2_sub3.method448();
        if (super.anInt1388 == 65535)
            super.anInt1388 = -1;
        super.anInt1389 = class38_sub2_sub3.method448();
        if (super.anInt1389 == 65535)
            super.anInt1389 = -1;
        super.anInt1390 = class38_sub2_sub3.method448();
        if (super.anInt1390 == 65535)
            super.anInt1390 = -1;
        super.anInt1391 = class38_sub2_sub3.method448();
        if (super.anInt1391 == 65535)
            super.anInt1391 = -1;
        aString1505 = Class40.method479(0, Class40.method476(class38_sub2_sub3.method452(603), false));
        anInt1511 = class38_sub2_sub3.method446();
        aBoolean1506 = true;
        aLong1512 = 0L;
        for (int j1 = 0; j1 < 12; j1++) {
            aLong1512 <<= 4;
            if (anIntArray1509[j1] >= 256)
                aLong1512 += anIntArray1509[j1] - 256;
        }

        if (anIntArray1509[0] >= 256)
            aLong1512 += anIntArray1509[0] - 256 >> 4;
        if (anIntArray1509[1] >= 256)
            aLong1512 += anIntArray1509[1] - 256 >> 8;
        for (int k1 = 0; k1 < 5; k1++) {
            aLong1512 <<= 3;
            aLong1512 += anIntArray1510[k1];
        }

        aLong1512 <<= 1;
        aLong1512 += anInt1507;
    }

    public Class38_Sub2_Sub1 method462(boolean flag) {
        if (!aBoolean1506)
            return null;
        Class38_Sub2_Sub1 class38_sub2_sub1 = method471(false);
        super.anInt1425 = class38_sub2_sub1.anInt1247;
        class38_sub2_sub1.aBoolean1256 = true;
        if (aBoolean1524)
            return class38_sub2_sub1;
        if (super.anInt1412 != -1 && super.anInt1413 != -1) {
            Class20 class20 = Class20.aClass20Array387[super.anInt1412];
            Class38_Sub2_Sub1 class38_sub2_sub1_2 = new Class38_Sub2_Sub1(class20.method226(), true,
                    !class20.aBoolean392, anInt1503, false);
            class38_sub2_sub1_2.method363(-super.anInt1416, 0, -122, 0);
            class38_sub2_sub1_2.method357(4);
            class38_sub2_sub1_2.method358(-16599, class20.aClass18_391.anIntArray366[super.anInt1413]);
            class38_sub2_sub1_2.anIntArrayArray1255 = null;
            class38_sub2_sub1_2.anIntArrayArray1254 = null;
            if (class20.anInt395 != 128 || class20.anInt396 != 128)
                class38_sub2_sub1_2.method366(class20.anInt395, 2, class20.anInt396, class20.anInt395);
            class38_sub2_sub1_2.method367(64 + class20.anInt398, 850 + class20.anInt399, -30, -50, -30, true);
            Class38_Sub2_Sub1[] aclass38_sub2_sub1_1 = {
                    class38_sub2_sub1, class38_sub2_sub1_2
            };
            class38_sub2_sub1 = new Class38_Sub2_Sub1(aclass38_sub2_sub1_1, (byte) -31, 2, true);
        }
        if (aClass38_Sub2_Sub1_1519 != null) {
            if (client.anInt955 >= anInt1515)
                aClass38_Sub2_Sub1_1519 = null;
            if (client.anInt955 >= anInt1514 && client.anInt955 < anInt1515) {
                Class38_Sub2_Sub1 class38_sub2_sub1_1 = aClass38_Sub2_Sub1_1519;
                class38_sub2_sub1_1.method363(anInt1517 - anInt1513, anInt1516 - super.anInt1380, -122,
                        anInt1518 - super.anInt1381);
                if (super.anInt1426 == 512) {
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                } else if (super.anInt1426 == 1024) {
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                } else if (super.anInt1426 == 1536)
                    class38_sub2_sub1_1.method361(0);
                Class38_Sub2_Sub1[] aclass38_sub2_sub1 = {
                        class38_sub2_sub1, class38_sub2_sub1_1
                };
                class38_sub2_sub1 = new Class38_Sub2_Sub1(aclass38_sub2_sub1, (byte) -31, 2, true);
                if (super.anInt1426 == 512)
                    class38_sub2_sub1_1.method361(0);
                else if (super.anInt1426 == 1024) {
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                } else if (super.anInt1426 == 1536) {
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                }
                class38_sub2_sub1_1.method363(anInt1513 - anInt1517, super.anInt1380 - anInt1516, -122,
                        super.anInt1381 - anInt1518);
            }
        }
        class38_sub2_sub1.aBoolean1256 = true;
        if (!flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        return class38_sub2_sub1;
    }

    public Class38_Sub2_Sub1 method471(boolean flag) {
        long l = aLong1512;
        int i = -1;
        int j = -1;
        int k = -1;
        int i1 = -1;
        if (super.anInt1407 >= 0 && super.anInt1410 == 0) {
            Class18 class18 = Class18.aClass18Array364[super.anInt1407];
            i = class18.anIntArray366[super.anInt1408];
            if (super.anInt1404 >= 0 && super.anInt1404 != super.anInt1385)
                j = Class18.aClass18Array364[super.anInt1404].anIntArray366[super.anInt1405];
            if (class18.anInt373 >= 0) {
                k = class18.anInt373;
                l += k - anIntArray1509[5] << 40;
            }
            if (class18.anInt374 >= 0) {
                i1 = class18.anInt374;
                l += i1 - anIntArray1509[3] << 48;
            }
        } else if (super.anInt1404 >= 0)
            i = Class18.aClass18Array364[super.anInt1404].anIntArray366[super.anInt1405];
        Class38_Sub2_Sub1 class38_sub2_sub1 = (Class38_Sub2_Sub1) aClass34_1525.method341(l);
        if (class38_sub2_sub1 == null) {
            Class38_Sub2_Sub1[] aclass38_sub2_sub1 = new Class38_Sub2_Sub1[12];
            int j1 = 0;
            for (int k1 = 0; k1 < 12; k1++) {
                int l1 = anIntArray1509[k1];
                if (i1 >= 0 && k1 == 3)
                    l1 = i1;
                if (k >= 0 && k1 == 5)
                    l1 = k;
                if (l1 >= 256 && l1 < 512)
                    aclass38_sub2_sub1[j1++] = Class12.aClass12Array247[l1 - 256].method216();
                if (l1 >= 512) {
                    Class4 class4 = Class4.method169(l1 - 512);
                    Class38_Sub2_Sub1 class38_sub2_sub1_2 = class4.method175((byte) 6, anInt1507);
                    if (class38_sub2_sub1_2 != null)
                        aclass38_sub2_sub1[j1++] = class38_sub2_sub1_2;
                }
            }

            class38_sub2_sub1 = new Class38_Sub2_Sub1(0, aclass38_sub2_sub1, j1);
            for (int i2 = 0; i2 < 5; i2++)
                if (anIntArray1510[i2] != 0) {
                    class38_sub2_sub1.method364(client.anIntArrayArray942[i2][0],
                            client.anIntArrayArray942[i2][anIntArray1510[i2]]);
                    if (i2 == 1)
                        class38_sub2_sub1.method364(client.anIntArray1073[0],
                                client.anIntArray1073[anIntArray1510[i2]]);
                }

            class38_sub2_sub1.method357(4);
            class38_sub2_sub1.method367(64, 850, -30, -50, -30, true);
            aClass34_1525.method342(6, l, class38_sub2_sub1);
        }
        if (aBoolean1524)
            return class38_sub2_sub1;
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = new Class38_Sub2_Sub1(0, class38_sub2_sub1, true);
        if (flag)
            aBoolean1504 = !aBoolean1504;
        if (i != -1 && j != -1)
            class38_sub2_sub1_1.method359(j, 3, i, Class18.aClass18Array364[super.anInt1407].anIntArray370);
        else if (i != -1)
            class38_sub2_sub1_1.method358(-16599, i);
        class38_sub2_sub1_1.method354(2992);
        class38_sub2_sub1_1.anIntArrayArray1255 = null;
        class38_sub2_sub1_1.anIntArrayArray1254 = null;
        return class38_sub2_sub1_1;
    }

    public Class38_Sub2_Sub1 method472(int i) {
        if (!aBoolean1506)
            return null;
        Class38_Sub2_Sub1[] aclass38_sub2_sub1 = new Class38_Sub2_Sub1[12];
        int j = 0;
        for (int k = 0; k < 12; k++) {
            int l = anIntArray1509[k];
            if (l >= 256 && l < 512)
                aclass38_sub2_sub1[j++] = Class12.aClass12Array247[l - 256].method217(false);
            if (l >= 512) {
                Class38_Sub2_Sub1 class38_sub2_sub1_1 = Class4.method169(l - 512).method176(-22246, anInt1507);
                if (class38_sub2_sub1_1 != null)
                    aclass38_sub2_sub1[j++] = class38_sub2_sub1_1;
            }
        }

        Class38_Sub2_Sub1 class38_sub2_sub1 = new Class38_Sub2_Sub1(0, aclass38_sub2_sub1, j);
        for (int i1 = 0; i1 < 5; i1++)
            if (anIntArray1510[i1] != 0) {
                class38_sub2_sub1.method364(client.anIntArrayArray942[i1][0],
                        client.anIntArrayArray942[i1][anIntArray1510[i1]]);
                if (i1 == 1)
                    class38_sub2_sub1.method364(client.anIntArray1073[0], client.anIntArray1073[anIntArray1510[i1]]);
            }

        while (i >= 0)
            throw new NullPointerException();
        return class38_sub2_sub1;
    }

    public boolean method468(boolean flag) {
        if (flag)
            aBoolean1504 = !aBoolean1504;
        return aBoolean1506;
    }

    public Class38_Sub7_Sub3_Sub2() {
        aBoolean1504 = false;
        aBoolean1506 = false;
        anIntArray1509 = new int[12];
        anIntArray1510 = new int[5];
        aBoolean1524 = false;
    }

    public int anInt1503;
    public boolean aBoolean1504;
    public String aString1505;
    public boolean aBoolean1506;
    public int anInt1507;
    public int anInt1508;
    public int[] anIntArray1509;
    public int[] anIntArray1510;
    public int anInt1511;
    public long aLong1512;
    public int anInt1513;
    public int anInt1514;
    public int anInt1515;
    public int anInt1516;
    public int anInt1517;
    public int anInt1518;
    public Class38_Sub2_Sub1 aClass38_Sub2_Sub1_1519;
    public int anInt1520;
    public int anInt1521;
    public int anInt1522;
    public int anInt1523;
    public boolean aBoolean1524;
    public static Class34 aClass34_1525 = new Class34((byte) 0, 200);

}
