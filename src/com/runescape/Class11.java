package com.runescape;

public class Class11 {

    public static void method213(boolean flag, FileArchive fileArchive) {
        if (flag)
            throw new NullPointerException();
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363,
                fileArchive.read("frame_head.dat", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_1 = new Class38_Sub2_Sub3(363,
                fileArchive.read("frame_tran1.dat", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_2 = new Class38_Sub2_Sub3(363,
                fileArchive.read("frame_tran2.dat", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_3 = new Class38_Sub2_Sub3(363,
                fileArchive.read("frame_del.dat", null));
        int i = class38_sub2_sub3.method448();
        int j = class38_sub2_sub3.method448();
        aClass11Array235 = new Class11[j + 1];
        int[] ai = new int[500];
        int[] ai1 = new int[500];
        int[] ai2 = new int[500];
        int[] ai3 = new int[500];
        for (int k = 0; k < i; k++) {
            int l = class38_sub2_sub3.method448();
            Class11 class11 = aClass11Array235[l] = new Class11();
            class11.anInt236 = class38_sub2_sub3_3.method446();
            int i1 = class38_sub2_sub3.method448();
            SeqBase seqBase = SeqBase.seqBaseArray[i1];
            class11.seqBase = seqBase;
            int j1 = class38_sub2_sub3.method446();
            int k1 = -1;
            int l1 = 0;
            for (int i2 = 0; i2 < j1; i2++) {
                int j2 = class38_sub2_sub3_1.method446();
                if (j2 > 0) {
                    if (seqBase.anIntArray218[i2] != 0) {
                        for (int l2 = i2 - 1; l2 > k1; l2--) {
                            if (seqBase.anIntArray218[l2] != 0)
                                continue;
                            ai[l1] = l2;
                            ai1[l1] = 0;
                            ai2[l1] = 0;
                            ai3[l1] = 0;
                            l1++;
                            break;
                        }

                    }
                    ai[l1] = i2;
                    char c = '\0';
                    if (seqBase.anIntArray218[ai[l1]] == 3)
                        c = '\200';
                    if ((j2 & 1) != 0)
                        ai1[l1] = class38_sub2_sub3_2.method459();
                    else
                        ai1[l1] = c;
                    if ((j2 & 2) != 0)
                        ai2[l1] = class38_sub2_sub3_2.method459();
                    else
                        ai2[l1] = c;
                    if ((j2 & 4) != 0)
                        ai3[l1] = class38_sub2_sub3_2.method459();
                    else
                        ai3[l1] = c;
                    k1 = i2;
                    l1++;
                }
            }

            class11.anInt238 = l1;
            class11.anIntArray239 = new int[l1];
            class11.anIntArray240 = new int[l1];
            class11.anIntArray241 = new int[l1];
            class11.anIntArray242 = new int[l1];
            for (int k2 = 0; k2 < l1; k2++) {
                class11.anIntArray239[k2] = ai[k2];
                class11.anIntArray240[k2] = ai1[k2];
                class11.anIntArray241[k2] = ai2[k2];
                class11.anIntArray242[k2] = ai3[k2];
            }

        }

    }

    public Class11() {
    }

    public static Class11[] aClass11Array235;
    public int anInt236;
    public SeqBase seqBase;
    public int anInt238;
    public int[] anIntArray239;
    public int[] anIntArray240;
    public int[] anIntArray241;
    public int[] anIntArray242;
}
