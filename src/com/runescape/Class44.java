package com.runescape;

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
        soundEnvelope1.reset();
        soundEnvelope2.reset();
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        if (soundEnvelope3 != null) {
            soundEnvelope3.reset();
            soundEnvelope4.reset();
            l = (int) (((double) (soundEnvelope3.end - soundEnvelope3.start) * 32.768000000000001D) / d);
            i1 = (int) (((double) soundEnvelope3.start * 32.768000000000001D) / d);
        }
        int k1 = 0;
        int l1 = 0;
        int i2 = 0;
        if (soundEnvelope5 != null) {
            soundEnvelope5.reset();
            soundEnvelope6.reset();
            k1 = (int) (((double) (soundEnvelope5.end - soundEnvelope5.start) * 32.768000000000001D) / d);
            l1 = (int) (((double) soundEnvelope5.start * 32.768000000000001D) / d);
        }
        for (int j2 = 0; j2 < 5; j2++)
            if (anIntArray708[j2] != 0) {
                anIntArray718[j2] = 0;
                anIntArray719[j2] = (int) ((double) anIntArray710[j2] * d);
                anIntArray720[j2] = (anIntArray708[j2] << 14) / 100;
                anIntArray721[j2] = (int) (((double) (soundEnvelope1.end - soundEnvelope1.start)
                        * 32.768000000000001D * Math.pow(1.0057929410678534D, anIntArray709[j2])) / d);
                anIntArray722[j2] = (int) (((double) soundEnvelope1.start * 32.768000000000001D) / d);
            }

        for (int k2 = 0; k2 < i; k2++) {
            int l2 = soundEnvelope1.evaluate(i);
            int l3 = soundEnvelope2.evaluate(i);
            if (soundEnvelope3 != null) {
                int k4 = soundEnvelope3.evaluate(i);
                int j5 = soundEnvelope4.evaluate(i);
                l2 += method495(-15143, j5, j1, soundEnvelope3.form) >> 1;
                j1 += (k4 * l >> 16) + i1;
            }
            if (soundEnvelope5 != null) {
                int l4 = soundEnvelope5.evaluate(i);
                int k5 = soundEnvelope6.evaluate(i);
                l3 = l3 * ((method495(-15143, k5, i2, soundEnvelope5.form) >> 1) + 32768) >> 15;
                i2 += (l4 * k1 >> 16) + l1;
            }
            for (int i5 = 0; i5 < 5; i5++)
                if (anIntArray708[i5] != 0) {
                    int l5 = k2 + anIntArray719[i5];
                    if (l5 < i) {
                        anIntArray715[l5] += method495(-15143, l3 * anIntArray720[i5] >> 15, anIntArray718[i5],
                                soundEnvelope1.form);
                        anIntArray718[i5] += (l2 * anIntArray721[i5] >> 16) + anIntArray722[i5];
                    }
                }

        }

        if (soundEnvelope7 != null) {
            soundEnvelope7.reset();
            soundEnvelope8.reset();
            int i3 = 0;
            boolean flag = false;
            boolean flag1 = true;
            for (int i6 = 0; i6 < i; i6++) {
                int j6 = soundEnvelope7.evaluate(i);
                int k6 = soundEnvelope8.evaluate(i);
                int i4;
                if (flag1)
                    i4 = soundEnvelope7.start + ((soundEnvelope7.end - soundEnvelope7.start) * j6 >> 8);
                else
                    i4 = soundEnvelope7.start + ((soundEnvelope7.end - soundEnvelope7.start) * k6 >> 8);
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
        soundEnvelope1 = new SoundEnvelope();
        soundEnvelope1.readShape(class38_sub2_sub3);
        soundEnvelope2 = new SoundEnvelope();
        soundEnvelope2.readShape(class38_sub2_sub3);
        int i = class38_sub2_sub3.method446();
        if (flag)
            throw new NullPointerException();
        if (i != 0) {
            class38_sub2_sub3.anInt1329--;
            soundEnvelope3 = new SoundEnvelope();
            soundEnvelope3.readShape(class38_sub2_sub3);
            soundEnvelope4 = new SoundEnvelope();
            soundEnvelope4.readShape(class38_sub2_sub3);
        }
        i = class38_sub2_sub3.method446();
        if (i != 0) {
            class38_sub2_sub3.anInt1329--;
            soundEnvelope5 = new SoundEnvelope();
            soundEnvelope5.readShape(class38_sub2_sub3);
            soundEnvelope6 = new SoundEnvelope();
            soundEnvelope6.readShape(class38_sub2_sub3);
        }
        i = class38_sub2_sub3.method446();
        if (i != 0) {
            class38_sub2_sub3.anInt1329--;
            soundEnvelope7 = new SoundEnvelope();
            soundEnvelope7.readShape(class38_sub2_sub3);
            soundEnvelope8 = new SoundEnvelope();
            soundEnvelope8.readShape(class38_sub2_sub3);
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
    public SoundEnvelope soundEnvelope1;
    public SoundEnvelope soundEnvelope2;
    public SoundEnvelope soundEnvelope3;
    public SoundEnvelope soundEnvelope4;
    public SoundEnvelope soundEnvelope5;
    public SoundEnvelope soundEnvelope6;
    public SoundEnvelope soundEnvelope7;
    public SoundEnvelope soundEnvelope8;
    public int[] anIntArray708;
    public int[] anIntArray709;
    public int[] anIntArray710;
    public int anInt711;
    public int anInt712;
    public int anInt713;
    public int anInt714;
    public static int[] anIntArray715;
    public static int[] anIntArray716;
    public static int[] anIntArray717;
    public static int[] anIntArray718 = new int[5];
    public static int[] anIntArray719 = new int[5];
    public static int[] anIntArray720 = new int[5];
    public static int[] anIntArray721 = new int[5];
    public static int[] anIntArray722 = new int[5];
    public static boolean aBoolean723;

}
