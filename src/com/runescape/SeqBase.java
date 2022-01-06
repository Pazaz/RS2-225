package com.runescape;

public class SeqBase {

    public static void method208(boolean flag, FileArchive fileArchive) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363,
                fileArchive.read("base_head.dat", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_1 = new Class38_Sub2_Sub3(363,
                fileArchive.read("base_type.dat", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_2 = new Class38_Sub2_Sub3(363,
                fileArchive.read("base_label.dat", null));
        int i = class38_sub2_sub3.method448();
        int j = class38_sub2_sub3.method448();
        seqBaseArray = new SeqBase[j + 1];
        if (flag)
            throw new NullPointerException();
        for (int k = 0; k < i; k++) {
            int l = class38_sub2_sub3.method448();
            int i1 = class38_sub2_sub3.method446();
            int[] ai = new int[i1];
            int[][] ai1 = new int[i1][];
            for (int j1 = 0; j1 < i1; j1++) {
                ai[j1] = class38_sub2_sub3_1.method446();
                int k1 = class38_sub2_sub3_2.method446();
                ai1[j1] = new int[k1];
                for (int l1 = 0; l1 < k1; l1++)
                    ai1[j1][l1] = class38_sub2_sub3_2.method446();

            }

            seqBaseArray[l] = new SeqBase();
            seqBaseArray[l].anInt217 = i1;
            seqBaseArray[l].anIntArray218 = ai;
            seqBaseArray[l].anIntArrayArray219 = ai1;
        }

    }

    public SeqBase() {
    }

    public static SeqBase[] seqBaseArray;
    public int anInt217;
    public int[] anIntArray218;
    public int[][] anIntArrayArray219;
}
