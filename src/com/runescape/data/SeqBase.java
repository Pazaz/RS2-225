package com.runescape.data;

import com.runescape.io.Buffer;
import com.runescape.io.FileArchive;

public class SeqBase {

    public static void method208(boolean flag, FileArchive class39) {
        Buffer class38_sub2_sub3 = new Buffer(363,
                class39.method474("base_head.dat", null, (byte) 2));
        Buffer class38_sub2_sub3_1 = new Buffer(363,
                class39.method474("base_type.dat", null, (byte) 2));
        Buffer class38_sub2_sub3_2 = new Buffer(363,
                class39.method474("base_label.dat", null, (byte) 2));
        int i = class38_sub2_sub3.method448();
        int j = class38_sub2_sub3.method448();
        aClass9Array216 = new SeqBase[j + 1];
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

            aClass9Array216[l] = new SeqBase();
            aClass9Array216[l].anInt217 = i1;
            aClass9Array216[l].anIntArray218 = ai;
            aClass9Array216[l].anIntArrayArray219 = ai1;
        }

    }

    public SeqBase() {
    }

    public static SeqBase[] aClass9Array216;
    public int anInt217;
    public int[] anIntArray218;
    public int[][] anIntArrayArray219;
}
