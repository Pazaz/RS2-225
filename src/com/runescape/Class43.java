package com.runescape;

public class Class43 {

    public static void method487(Class38_Sub2_Sub3 class38_sub2_sub3, int i) {
        aByteArray693 = new byte[0x6baa8];
        aClass38_Sub2_Sub3_694 = new Class38_Sub2_Sub3(363, aByteArray693);
        i = 87 / i;
        SoundTone.init();
        do {
            int j = class38_sub2_sub3.method448();
            if (j == 65535)
                return;
            aClass43Array691[j] = new Class43();
            aClass43Array691[j].method489(false, class38_sub2_sub3);
            anIntArray692[j] = aClass43Array691[j].method490((byte) 7);
        } while (true);
    }

    public static Class38_Sub2_Sub3 method488(byte byte0, int i, int j) {
        if (byte0 != -16)
            anInt690 = -83;
        if (aClass43Array691[j] != null) {
            Class43 class43 = aClass43Array691[j];
            return class43.method491(true, i);
        } else {
            return null;
        }
    }

    public void method489(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        for (int i = 0; i < 10; i++) {
            int j = class38_sub2_sub3.method446();
            if (j != 0) {
                class38_sub2_sub3.anInt1329--;
                soundTones[i] = new SoundTone();
                soundTones[i].read(class38_sub2_sub3);
            }
        }

        if (flag)
            anInt690 = -307;
        anInt696 = class38_sub2_sub3.method448();
        anInt697 = class38_sub2_sub3.method448();
    }

    public int method490(byte byte0) {
        int i = 0x98967f;
        for (int j = 0; j < 10; j++)
            if (soundTones[j] != null && soundTones[j].start / 20 < i)
                i = soundTones[j].start / 20;

        if (byte0 == 7)
            byte0 = 0;
        else
            anInt690 = -8;
        if (anInt696 < anInt697 && anInt696 / 20 < i)
            i = anInt696 / 20;
        if (i == 0x98967f || i == 0)
            return 0;
        for (int k = 0; k < 10; k++)
            if (soundTones[k] != null)
                soundTones[k].start -= i * 20;

        if (anInt696 < anInt697) {
            anInt696 -= i * 20;
            anInt697 -= i * 20;
        }
        return i;
    }

    public Class38_Sub2_Sub3 method491(boolean flag, int i) {
        int j = method492(i);
        aClass38_Sub2_Sub3_694.anInt1329 = 0;
        aClass38_Sub2_Sub3_694.method440(0x52494646);
        aClass38_Sub2_Sub3_694.method441(false, 36 + j);
        aClass38_Sub2_Sub3_694.method440(0x57415645);
        aClass38_Sub2_Sub3_694.method440(0x666d7420);
        aClass38_Sub2_Sub3_694.method441(false, 16);
        if (!flag) {
            for (int k = 1; k > 0; k++)
                ;
        }
        aClass38_Sub2_Sub3_694.method438(aBoolean689, 1);
        aClass38_Sub2_Sub3_694.method438(aBoolean689, 1);
        aClass38_Sub2_Sub3_694.method441(false, 22050);
        aClass38_Sub2_Sub3_694.method441(false, 22050);
        aClass38_Sub2_Sub3_694.method438(aBoolean689, 1);
        aClass38_Sub2_Sub3_694.method438(aBoolean689, 8);
        aClass38_Sub2_Sub3_694.method440(0x64617461);
        aClass38_Sub2_Sub3_694.method441(false, j);
        aClass38_Sub2_Sub3_694.anInt1329 += j;
        return aClass38_Sub2_Sub3_694;
    }

    public int method492(int i) {
        int j = 0;
        for (int k = 0; k < 10; k++)
            if (soundTones[k] != null && soundTones[k].length + soundTones[k].start > j)
                j = soundTones[k].length + soundTones[k].start;

        if (j == 0)
            return 0;
        int l = (22050 * j) / 1000;
        int i1 = (22050 * anInt696) / 1000;
        int j1 = (22050 * anInt697) / 1000;
        if (i1 < 0 || i1 > l || j1 < 0 || j1 > l || i1 >= j1)
            i = 0;
        int k1 = l + (j1 - i1) * (i - 1);
        for (int l1 = 44; l1 < k1 + 44; l1++)
            aByteArray693[l1] = -128;

        for (int i2 = 0; i2 < 10; i2++)
            if (soundTones[i2] != null) {
                int j2 = (soundTones[i2].length * 22050) / 1000;
                int i3 = (soundTones[i2].start * 22050) / 1000;
                int[] ai = soundTones[i2].generate(j2, soundTones[i2].length);
                for (int l3 = 0; l3 < j2; l3++)
                    aByteArray693[l3 + i3 + 44] += (byte) (ai[l3] >> 8);

            }

        if (i > 1) {
            i1 += 44;
            j1 += 44;
            l += 44;
            int k2 = (k1 += 44) - l;
            for (int j3 = l - 1; j3 >= j1; j3--)
                aByteArray693[j3 + k2] = aByteArray693[j3];

            for (int k3 = 1; k3 < i; k3++) {
                int l2 = (j1 - i1) * k3;
                for (int i4 = i1; i4 < j1; i4++)
                    aByteArray693[i4 + l2] = aByteArray693[i4];

            }

            k1 -= 44;
        }
        return k1;
    }

    public Class43() {
        aBoolean689 = true;
        soundTones = new SoundTone[10];
    }

    public boolean aBoolean689;
    public static int anInt690 = 473;
    public static Class43[] aClass43Array691 = new Class43[1000];
    public static int[] anIntArray692 = new int[1000];
    public static byte[] aByteArray693;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_694;
    public SoundTone[] soundTones;
    public int anInt696;
    public int anInt697;

}
