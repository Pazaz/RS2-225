// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class15 {

    public Class15(int i, int j, int k, int l, int i1, int j1, int k1,
            int l1, int i2, int j2, int k2, int l2, int i3, int j3,
            int k3, int l3, int i4, int j4, int k4, int l4) {
        aBoolean333 = true;
        if (j4 != l || j4 != j3 || j4 != l1)
            aBoolean333 = false;
        anInt334 = j;
        anInt335 = j1;
        anInt336 = i3;
        anInt337 = i2;
        char c = '\200';
        int i5 = c / 2;
        int j5 = c / 4;
        int k5 = (c * 3) / 4;
        int ai[] = anIntArrayArray346[j];
        int l5 = ai.length;
        anIntArray323 = new int[l5];
        anIntArray324 = new int[l5];
        anIntArray325 = new int[l5];
        int ai1[] = new int[l5];
        int ai2[] = new int[l5];
        int i6 = i * c;
        int j6 = k4 * c;
        for (int k6 = 0; k6 < l5; k6++) {
            int l6 = ai[k6];
            if ((l6 & 1) == 0 && l6 <= 8)
                l6 = (l6 - j1 - j1 - 1 & 7) + 1;
            if (l6 > 8 && l6 <= 12)
                l6 = (l6 - 9 - j1 & 3) + 9;
            if (l6 > 12 && l6 <= 16)
                l6 = (l6 - 13 - j1 & 3) + 13;
            int i7;
            int k7;
            int i8;
            int k8;
            int j9;
            if (l6 == 1) {
                i7 = i6;
                k7 = j6;
                i8 = j4;
                k8 = k1;
                j9 = j2;
            } else if (l6 == 2) {
                i7 = i6 + i5;
                k7 = j6;
                i8 = j4 + l >> 1;
                k8 = k1 + l4 >> 1;
                j9 = j2 + k >> 1;
            } else if (l6 == 3) {
                i7 = i6 + c;
                k7 = j6;
                i8 = l;
                k8 = l4;
                j9 = k;
            } else if (l6 == 4) {
                i7 = i6 + c;
                k7 = j6 + i5;
                i8 = l + j3 >> 1;
                k8 = l4 + i1 >> 1;
                j9 = k + k3 >> 1;
            } else if (l6 == 5) {
                i7 = i6 + c;
                k7 = j6 + c;
                i8 = j3;
                k8 = i1;
                j9 = k3;
            } else if (l6 == 6) {
                i7 = i6 + i5;
                k7 = j6 + c;
                i8 = j3 + l1 >> 1;
                k8 = i1 + l3 >> 1;
                j9 = k3 + l2 >> 1;
            } else if (l6 == 7) {
                i7 = i6;
                k7 = j6 + c;
                i8 = l1;
                k8 = l3;
                j9 = l2;
            } else if (l6 == 8) {
                i7 = i6;
                k7 = j6 + i5;
                i8 = l1 + j4 >> 1;
                k8 = l3 + k1 >> 1;
                j9 = l2 + j2 >> 1;
            } else if (l6 == 9) {
                i7 = i6 + i5;
                k7 = j6 + j5;
                i8 = j4 + l >> 1;
                k8 = k1 + l4 >> 1;
                j9 = j2 + k >> 1;
            } else if (l6 == 10) {
                i7 = i6 + k5;
                k7 = j6 + i5;
                i8 = l + j3 >> 1;
                k8 = l4 + i1 >> 1;
                j9 = k + k3 >> 1;
            } else if (l6 == 11) {
                i7 = i6 + i5;
                k7 = j6 + k5;
                i8 = j3 + l1 >> 1;
                k8 = i1 + l3 >> 1;
                j9 = k3 + l2 >> 1;
            } else if (l6 == 12) {
                i7 = i6 + j5;
                k7 = j6 + i5;
                i8 = l1 + j4 >> 1;
                k8 = l3 + k1 >> 1;
                j9 = l2 + j2 >> 1;
            } else if (l6 == 13) {
                i7 = i6 + j5;
                k7 = j6 + j5;
                i8 = j4;
                k8 = k1;
                j9 = j2;
            } else if (l6 == 14) {
                i7 = i6 + k5;
                k7 = j6 + j5;
                i8 = l;
                k8 = l4;
                j9 = k;
            } else if (l6 == 15) {
                i7 = i6 + k5;
                k7 = j6 + k5;
                i8 = j3;
                k8 = i1;
                j9 = k3;
            } else {
                i7 = i6 + j5;
                k7 = j6 + k5;
                i8 = l1;
                k8 = l3;
                j9 = l2;
            }
            anIntArray323[k6] = i7;
            anIntArray324[k6] = i8;
            anIntArray325[k6] = k7;
            ai1[k6] = k8;
            ai2[k6] = j9;
        }

        int ai3[] = anIntArrayArray347[j];
        int j7 = ai3.length / 4;
        anIntArray329 = new int[j7];
        anIntArray330 = new int[j7];
        anIntArray331 = new int[j7];
        anIntArray326 = new int[j7];
        anIntArray327 = new int[j7];
        if (i4 != 10659)
            throw new NullPointerException();
        anIntArray328 = new int[j7];
        if (k2 != -1)
            anIntArray332 = new int[j7];
        int l7 = 0;
        for (int j8 = 0; j8 < j7; j8++) {
            int l8 = ai3[l7];
            int k9 = ai3[l7 + 1];
            int i10 = ai3[l7 + 2];
            int j10 = ai3[l7 + 3];
            l7 += 4;
            if (k9 < 4)
                k9 = k9 - j1 & 3;
            if (i10 < 4)
                i10 = i10 - j1 & 3;
            if (j10 < 4)
                j10 = j10 - j1 & 3;
            anIntArray329[j8] = k9;
            anIntArray330[j8] = i10;
            anIntArray331[j8] = j10;
            if (l8 == 0) {
                anIntArray326[j8] = ai1[k9];
                anIntArray327[j8] = ai1[i10];
                anIntArray328[j8] = ai1[j10];
                if (anIntArray332 != null)
                    anIntArray332[j8] = -1;
            } else {
                anIntArray326[j8] = ai2[k9];
                anIntArray327[j8] = ai2[i10];
                anIntArray328[j8] = ai2[j10];
                if (anIntArray332 != null)
                    anIntArray332[j8] = k2;
            }
        }

        int i9 = j4;
        int l9 = l;
        if (l < i9)
            i9 = l;
        if (l > l9)
            l9 = l;
        if (j3 < i9)
            i9 = j3;
        if (j3 > l9)
            l9 = j3;
        if (l1 < i9)
            i9 = l1;
        if (l1 > l9)
            l9 = l1;
        i9 /= 14;
        l9 /= 14;
    }

    public int anIntArray323[];
    public int anIntArray324[];
    public int anIntArray325[];
    public int anIntArray326[];
    public int anIntArray327[];
    public int anIntArray328[];
    public int anIntArray329[];
    public int anIntArray330[];
    public int anIntArray331[];
    public int anIntArray332[];
    public boolean aBoolean333;
    public int anInt334;
    public int anInt335;
    public int anInt336;
    public int anInt337;
    public static int anIntArray338[] = new int[6];
    public static int anIntArray339[] = new int[6];
    public static int anIntArray340[] = new int[6];
    public static int anIntArray341[] = new int[6];
    public static int anIntArray342[] = new int[6];
    public static int anIntArray343[] = {
            1, 0
    };
    public static int anIntArray344[] = {
            2, 1
    };
    public static int anIntArray345[] = {
            3, 3
    };
    public static final int anIntArrayArray346[][] = {
            {
                    1, 3, 5, 7
            },
            {
                    1, 3, 5, 7
            },
            {
                    1, 3, 5, 7
            },
            {
                    1, 3, 5, 7, 6
            },
            {
                    1, 3, 5, 7, 6
            },
            {
                    1, 3, 5, 7, 6
            },
            {
                    1, 3, 5, 7, 6
            },
            {
                    1, 3, 5, 7, 2, 6
            },
            {
                    1, 3, 5, 7, 2, 8
            },
            {
                    1, 3, 5, 7, 2, 8
            },
            {
                    1, 3, 5, 7, 11, 12
            },
            {
                    1, 3, 5, 7, 11, 12
            },
            {
                    1, 3, 5, 7, 13, 14
            }
    };
    public static final int anIntArrayArray347[][] = {
            {
                    0, 1, 2, 3, 0, 0, 1, 3
            },
            {
                    1, 1, 2, 3, 1, 0, 1, 3
            },
            {
                    0, 1, 2, 3, 1, 0, 1, 3
            },
            {
                    0, 0, 1, 2, 0, 0, 2, 4, 1, 0,
                    4, 3
            },
            {
                    0, 0, 1, 4, 0, 0, 4, 3, 1, 1,
                    2, 4
            },
            {
                    0, 0, 4, 3, 1, 0, 1, 2, 1, 0,
                    2, 4
            },
            {
                    0, 1, 2, 4, 1, 0, 1, 4, 1, 0,
                    4, 3
            },
            {
                    0, 4, 1, 2, 0, 4, 2, 5, 1, 0,
                    4, 5, 1, 0, 5, 3
            },
            {
                    0, 4, 1, 2, 0, 4, 2, 3, 0, 4,
                    3, 5, 1, 0, 4, 5
            },
            {
                    0, 0, 4, 5, 1, 4, 1, 2, 1, 4,
                    2, 3, 1, 4, 3, 5
            },
            {
                    0, 0, 1, 5, 0, 1, 4, 5, 0, 1,
                    2, 4, 1, 0, 5, 3, 1, 5, 4, 3,
                    1, 4, 2, 3
            },
            {
                    1, 0, 1, 5, 1, 1, 4, 5, 1, 1,
                    2, 4, 0, 0, 5, 3, 0, 5, 4, 3,
                    0, 4, 2, 3
            },
            {
                    1, 0, 5, 4, 1, 0, 1, 5, 0, 0,
                    4, 3, 0, 4, 5, 3, 0, 5, 2, 3,
                    0, 1, 2, 5
            }
    };

}
