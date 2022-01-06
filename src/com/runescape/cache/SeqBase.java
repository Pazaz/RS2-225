package com.runescape.cache;

import com.runescape.util.Buffer;

public class SeqBase {

    public static void method208(boolean flag, FileArchive fileArchive) {
        Buffer class38_sub2_sub3 = new Buffer(
                fileArchive.read("base_head.dat", null));
        Buffer class38_sub2_sub3_1 = new Buffer(
                fileArchive.read("base_type.dat", null));
        Buffer class38_sub2_sub3_2 = new Buffer(
                fileArchive.read("base_label.dat", null));
        int i = class38_sub2_sub3.readWord();
        int j = class38_sub2_sub3.readWord();
        seqBaseArray = new SeqBase[j + 1];
        if (flag)
            throw new NullPointerException();
        for (int k = 0; k < i; k++) {
            int l = class38_sub2_sub3.readWord();
            int i1 = class38_sub2_sub3.readByte();
            int[] ai = new int[i1];
            int[][] ai1 = new int[i1][];
            for (int j1 = 0; j1 < i1; j1++) {
                ai[j1] = class38_sub2_sub3_1.readByte();
                int k1 = class38_sub2_sub3_2.readByte();
                ai1[j1] = new int[k1];
                for (int l1 = 0; l1 < k1; l1++)
                    ai1[j1][l1] = class38_sub2_sub3_2.readByte();

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
