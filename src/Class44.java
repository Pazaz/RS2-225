// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class44 {

    public static void method493() {
        anIntArray716 = new int[32768];
        for (int i = 0; i < 32768; i++)
            if (Math.random() > 0.5D)
                anIntArray716[i] = 1;
            else
                anIntArray716[i] = -1;

        anIntArray717 = new int[32768];
        for (int j = 0; j < 32768; j++)
            anIntArray717[j] = (int) (Math.sin((double) j / 5215.1903000000002D) * 16384D);

        anIntArray715 = new int[0x35d54];
    }

    public int[] method494(int i, int j) {
        for (int k = 0; k < i; k++)
            anIntArray715[k] = 0;

        if (j < 10)
            return anIntArray715;
        double d = (double) i / ((double) j + 0.0D);
        aClass42_700.method485(anInt698);
        aClass42_701.method485(anInt698);
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        if (aClass42_702 != null) {
            aClass42_702.method485(anInt698);
            aClass42_703.method485(anInt698);
            l = (int) (((double) (aClass42_702.anInt682 - aClass42_702.anInt681) * 32.768000000000001D) / d);
            i1 = (int) (((double) aClass42_702.anInt681 * 32.768000000000001D) / d);
        }
        int k1 = 0;
        int l1 = 0;
        int i2 = 0;
        if (aClass42_704 != null) {
            aClass42_704.method485(anInt698);
            aClass42_705.method485(anInt698);
            k1 = (int) (((double) (aClass42_704.anInt682 - aClass42_704.anInt681) * 32.768000000000001D) / d);
            l1 = (int) (((double) aClass42_704.anInt681 * 32.768000000000001D) / d);
        }
        for (int j2 = 0; j2 < 5; j2++)
            if (anIntArray708[j2] != 0) {
                anIntArray718[j2] = 0;
                anIntArray719[j2] = (int) ((double) anIntArray710[j2] * d);
                anIntArray720[j2] = (anIntArray708[j2] << 14) / 100;
                anIntArray721[j2] = (int) (((double) (aClass42_700.anInt682 - aClass42_700.anInt681)
                        * 32.768000000000001D * Math.pow(1.0057929410678534D, anIntArray709[j2])) / d);
                anIntArray722[j2] = (int) (((double) aClass42_700.anInt681 * 32.768000000000001D) / d);
            }

        for (int k2 = 0; k2 < i; k2++) {
            int l2 = aClass42_700.method486(true, i);
            int l3 = aClass42_701.method486(true, i);
            if (aClass42_702 != null) {
                int k4 = aClass42_702.method486(true, i);
                int j5 = aClass42_703.method486(true, i);
                l2 += method495(-15143, j5, j1, aClass42_702.anInt683) >> 1;
                j1 += (k4 * l >> 16) + i1;
            }
            if (aClass42_704 != null) {
                int l4 = aClass42_704.method486(true, i);
                int k5 = aClass42_705.method486(true, i);
                l3 = l3 * ((method495(-15143, k5, i2, aClass42_704.anInt683) >> 1) + 32768) >> 15;
                i2 += (l4 * k1 >> 16) + l1;
            }
            for (int i5 = 0; i5 < 5; i5++)
                if (anIntArray708[i5] != 0) {
                    int l5 = k2 + anIntArray719[i5];
                    if (l5 < i) {
                        anIntArray715[l5] += method495(-15143, l3 * anIntArray720[i5] >> 15, anIntArray718[i5],
                                aClass42_700.anInt683);
                        anIntArray718[i5] += (l2 * anIntArray721[i5] >> 16) + anIntArray722[i5];
                    }
                }

        }

        if (aClass42_706 != null) {
            aClass42_706.method485(anInt698);
            aClass42_707.method485(anInt698);
            int i3 = 0;
            boolean flag = false;
            boolean flag1 = true;
            for (int i6 = 0; i6 < i; i6++) {
                int j6 = aClass42_706.method486(true, i);
                int k6 = aClass42_707.method486(true, i);
                int i4;
                if (flag1)
                    i4 = aClass42_706.anInt681 + ((aClass42_706.anInt682 - aClass42_706.anInt681) * j6 >> 8);
                else
                    i4 = aClass42_706.anInt681 + ((aClass42_706.anInt682 - aClass42_706.anInt681) * k6 >> 8);
                if ((i3 += 256) >= i4) {
                    i3 = 0;
                    flag1 = !flag1;
                }
                if (flag1)
                    anIntArray715[i6] = 0;
            }

        }
        if (anInt711 > 0 && anInt712 > 0) {
            int j3 = (int) ((double) anInt711 * d);
            for (int j4 = j3; j4 < i; j4++)
                anIntArray715[j4] += (anIntArray715[j4 - j3] * anInt712) / 100;

        }
        for (int k3 = 0; k3 < i; k3++) {
            if (anIntArray715[k3] < -32768)
                anIntArray715[k3] = -32768;
            if (anIntArray715[k3] > 32767)
                anIntArray715[k3] = 32767;
        }

        return anIntArray715;
    }

    public int method495(int i, int j, int k, int l) {
        if (i != anInt699)
            return 2;
        if (l == 1)
            if ((k & 0x7fff) < 16384)
                return j;
            else
                return -j;
        if (l == 2)
            return anIntArray717[k & 0x7fff] * j >> 14;
        if (l == 3)
            return ((k & 0x7fff) * j >> 14) - j;
        if (l == 4)
            return anIntArray716[k / 2607 & 0x7fff] * j;
        else
            return 0;
    }

    public void method496(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        aClass42_700 = new Class42();
        aClass42_700.method484(false, class38_sub2_sub3);
        aClass42_701 = new Class42();
        aClass42_701.method484(false, class38_sub2_sub3);
        int i = class38_sub2_sub3.method446();
        if (flag)
            throw new NullPointerException();
        if (i != 0) {
            class38_sub2_sub3.anInt1329--;
            aClass42_702 = new Class42();
            aClass42_702.method484(false, class38_sub2_sub3);
            aClass42_703 = new Class42();
            aClass42_703.method484(false, class38_sub2_sub3);
        }
        i = class38_sub2_sub3.method446();
        if (i != 0) {
            class38_sub2_sub3.anInt1329--;
            aClass42_704 = new Class42();
            aClass42_704.method484(false, class38_sub2_sub3);
            aClass42_705 = new Class42();
            aClass42_705.method484(false, class38_sub2_sub3);
        }
        i = class38_sub2_sub3.method446();
        if (i != 0) {
            class38_sub2_sub3.anInt1329--;
            aClass42_706 = new Class42();
            aClass42_706.method484(false, class38_sub2_sub3);
            aClass42_707 = new Class42();
            aClass42_707.method484(false, class38_sub2_sub3);
        }
        for (int j = 0; j < 10; j++) {
            int k = class38_sub2_sub3.method460();
            if (k == 0)
                break;
            anIntArray708[j] = k;
            anIntArray709[j] = class38_sub2_sub3.method459();
            anIntArray710[j] = class38_sub2_sub3.method460();
        }

        anInt711 = class38_sub2_sub3.method460();
        anInt712 = class38_sub2_sub3.method460();
        anInt713 = class38_sub2_sub3.method448();
        anInt714 = class38_sub2_sub3.method448();
    }

    public Class44() {
        anInt699 = -15143;
        anIntArray708 = new int[5];
        anIntArray709 = new int[5];
        anIntArray710 = new int[5];
        anInt712 = 100;
        anInt713 = 500;
    }

    public static int anInt698 = 8;
    public int anInt699;
    public Class42 aClass42_700;
    public Class42 aClass42_701;
    public Class42 aClass42_702;
    public Class42 aClass42_703;
    public Class42 aClass42_704;
    public Class42 aClass42_705;
    public Class42 aClass42_706;
    public Class42 aClass42_707;
    public int anIntArray708[];
    public int anIntArray709[];
    public int anIntArray710[];
    public int anInt711;
    public int anInt712;
    public int anInt713;
    public int anInt714;
    public static int anIntArray715[];
    public static int anIntArray716[];
    public static int anIntArray717[];
    public static int anIntArray718[] = new int[5];
    public static int anIntArray719[] = new int[5];
    public static int anIntArray720[] = new int[5];
    public static int anIntArray721[] = new int[5];
    public static int anIntArray722[] = new int[5];
    public static boolean aBoolean723;

}
