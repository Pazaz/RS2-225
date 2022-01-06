// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class3 {

    public Class3(int i, byte[][][] abyte0, int j, int[][][] ai, int k) {
        aBoolean106 = true;
        anInt107 = 8;
        anInt111 = j;
        anInt112 = i;
        anIntArrayArrayArray113 = ai;
        aByteArrayArrayArray114 = abyte0;
        aByteArrayArrayArray115 = new byte[4][anInt111][anInt112];
        aByteArrayArrayArray116 = new byte[4][anInt111][anInt112];
        aByteArrayArrayArray117 = new byte[4][anInt111][anInt112];
        aByteArrayArrayArray118 = new byte[4][anInt111][anInt112];
        anIntArrayArrayArray126 = new int[4][anInt111 + 1][anInt112 + 1];
        aByteArrayArrayArray119 = new byte[4][anInt111 + 1][anInt112 + 1];
        anIntArrayArray120 = new int[anInt111 + 1][anInt112 + 1];
        anIntArray121 = new int[anInt112];
        anIntArray122 = new int[anInt112];
        if (k != -35388)
            anInt107 = 28;
        anIntArray123 = new int[anInt112];
        anIntArray124 = new int[anInt112];
        anIntArray125 = new int[anInt112];
    }

    public void method152(int i, int j, int k, int l, int i1) {
        byte byte0 = 0;
        if (k != 3)
            anInt107 = 123;
        for (int j1 = 0; j1 < Class10.anInt222; j1++) {
            if (!Class10.aClass10Array223[j1].aString228.equalsIgnoreCase("water"))
                continue;
            byte0 = (byte) (j1 + 1);
            break;
        }

        for (int k1 = j; k1 < j + i1; k1++) {
            for (int l1 = i; l1 < i + l; l1++)
                if (l1 >= 0 && l1 < anInt111 && k1 >= 0 && k1 < anInt112) {
                    aByteArrayArrayArray116[0][l1][k1] = byte0;
                    for (int i2 = 0; i2 < 4; i2++) {
                        anIntArrayArrayArray113[i2][l1][k1] = 0;
                        aByteArrayArrayArray114[i2][l1][k1] = 0;
                    }

                }

        }

    }

    public void method153(byte[] abyte0, int i, int j, int k, int l, int i1) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, abyte0);
        if (j != 1)
            aBoolean106 = !aBoolean106;
        for (int j1 = 0; j1 < 4; j1++) {
            for (int k1 = 0; k1 < 64; k1++) {
                for (int l1 = 0; l1 < 64; l1++) {
                    int i2 = k1 + l;
                    int j2 = l1 + k;
                    if (i2 >= 0 && i2 < 104 && j2 >= 0 && j2 < 104) {
                        aByteArrayArrayArray114[j1][i2][j2] = 0;
                        do {
                            int k2 = class38_sub2_sub3.method446();
                            if (k2 == 0) {
                                if (j1 == 0)
                                    anIntArrayArrayArray113[0][i2][j2] = -method158(0xe3b7b + i2 + i, 0x87cce + j2 + i1)
                                            * 8;
                                else
                                    anIntArrayArrayArray113[j1][i2][j2] = anIntArrayArrayArray113[j1 - 1][i2][j2] - 240;
                                break;
                            }
                            if (k2 == 1) {
                                int i3 = class38_sub2_sub3.method446();
                                if (i3 == 1)
                                    i3 = 0;
                                if (j1 == 0)
                                    anIntArrayArrayArray113[0][i2][j2] = -i3 * 8;
                                else
                                    anIntArrayArrayArray113[j1][i2][j2] = anIntArrayArrayArray113[j1 - 1][i2][j2]
                                            - i3 * 8;
                                break;
                            }
                            if (k2 <= 49) {
                                aByteArrayArrayArray116[j1][i2][j2] = class38_sub2_sub3.method447();
                                aByteArrayArrayArray117[j1][i2][j2] = (byte) ((k2 - 2) / 4);
                                aByteArrayArrayArray118[j1][i2][j2] = (byte) (k2 - 2 & 3);
                            } else if (k2 <= 81)
                                aByteArrayArrayArray114[j1][i2][j2] = (byte) (k2 - 49);
                            else
                                aByteArrayArrayArray115[j1][i2][j2] = (byte) (k2 - 81);
                        } while (true);
                    } else {
                        do {
                            int l2 = class38_sub2_sub3.method446();
                            if (l2 == 0)
                                break;
                            if (l2 == 1) {
                                class38_sub2_sub3.method446();
                                break;
                            }
                            if (l2 <= 49)
                                class38_sub2_sub3.method446();
                        } while (true);
                    }
                }

            }

        }

    }

    public void method154(byte[] abyte0, Class32 class32, Class8[] aclass8, Class27 class27, boolean flag, int i,
                          int j) {
        label0:
        {
            Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, abyte0);
            if (!flag)
                aBoolean106 = !aBoolean106;
            int k = -1;
            do {
                int l = class38_sub2_sub3.method460();
                if (l == 0)
                    break label0;
                k += l;
                int i1 = 0;
                do {
                    int j1 = class38_sub2_sub3.method460();
                    if (j1 == 0)
                        break;
                    i1 += j1 - 1;
                    int k1 = i1 & 0x3f;
                    int l1 = i1 >> 6 & 0x3f;
                    int i2 = i1 >> 12;
                    int j2 = class38_sub2_sub3.method446();
                    int k2 = j2 >> 2;
                    int l2 = j2 & 3;
                    int i3 = l1 + j;
                    int j3 = k1 + i;
                    if (i3 > 0 && j3 > 0 && i3 < 103 && j3 < 103) {
                        int k3 = i2;
                        if ((aByteArrayArrayArray114[1][i3][j3] & 2) == 2)
                            k3--;
                        Class8 class8 = null;
                        if (k3 >= 0)
                            class8 = aclass8[k3];
                        method155(class8, true, i2, j3, l2, k2, class32, class27, k, i3);
                    }
                } while (true);
            } while (true);
        }
    }

    public void method155(Class8 class8, boolean flag, int i, int j, int k, int l, Class32 class32,
                          Class27 class27, int i1, int j1) {
        if (!flag)
            return;
        if (aBoolean108) {
            if ((aByteArrayArrayArray114[i][j1][j] & 0x10) != 0)
                return;
            if (method157(i, (byte) 10, j1, j) != anInt109)
                return;
        }
        int k1 = anIntArrayArrayArray113[i][j1][j];
        int l1 = anIntArrayArrayArray113[i][j1 + 1][j];
        int i2 = anIntArrayArrayArray113[i][j1 + 1][j + 1];
        int j2 = anIntArrayArrayArray113[i][j1][j + 1];
        int k2 = k1 + l1 + i2 + j2 >> 2;
        Class1 class1 = Class1.method142(i1);
        int l2 = j1 + (j << 7) + (i1 << 14) + 0x40000000;
        if (!class1.aBoolean52)
            l2 += 0x80000000;
        byte byte0 = (byte) ((k << 6) + l);
        if (l == 22) {
            if (aBoolean108 && !class1.aBoolean52 && !class1.aBoolean73)
                return;
            Class38_Sub2_Sub1 class38_sub2_sub1 = class1.method145(22, k, k1, l1, i2, j2, -1);
            class32.method287(class38_sub2_sub1, (byte) 6, j1, l2, j, i, byte0, k2);
            if (class1.aBoolean50 && class1.aBoolean52 && class8 != null)
                class8.method199((byte) 12, j, j1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 3, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 10 || l == 11) {
            Class38_Sub2_Sub1 class38_sub2_sub1_1 = class1.method145(10, k, k1, l1, i2, j2, -1);
            if (class38_sub2_sub1_1 != null) {
                int j4 = 0;
                if (l == 11)
                    j4 += 256;
                int k3;
                int i4;
                if (k == 1 || k == 3) {
                    k3 = class1.anInt49;
                    i4 = class1.anInt48;
                } else {
                    k3 = class1.anInt48;
                    i4 = class1.anInt49;
                }
                if (class32.method291(k2, 775, i, null, l2, j, j1, k3, byte0, class38_sub2_sub1_1, j4, i4)
                        && class1.aBoolean65) {
                    for (int k4 = 0; k4 <= k3; k4++) {
                        for (int l4 = 0; l4 <= i4; l4++) {
                            int i5 = class38_sub2_sub1_1.anInt1246 / 4;
                            if (i5 > 30)
                                i5 = 30;
                            if (i5 > aByteArrayArrayArray119[i][j1 + k4][j + l4])
                                aByteArrayArrayArray119[i][j1 + k4][j + l4] = (byte) i5;
                        }

                    }

                }
            }
            if (class1.aBoolean50 && class8 != null)
                class8.method198(k, class1.anInt49, class1.anInt48, j1, 9, j, class1.aBoolean51);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 2, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l >= 12) {
            Class38_Sub2_Sub1 class38_sub2_sub1_2 = class1.method145(l, k, k1, l1, i2, j2, -1);
            class32.method291(k2, 775, i, null, l2, j, j1, 1, byte0, class38_sub2_sub1_2, 0, 1);
            if (l >= 12 && l <= 17 && l != 13 && i > 0)
                anIntArrayArrayArray126[i][j1][j] |= 0x924;
            if (class1.aBoolean50 && class8 != null)
                class8.method198(k, class1.anInt49, class1.anInt48, j1, 9, j, class1.aBoolean51);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 2, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 0) {
            Class38_Sub2_Sub1 class38_sub2_sub1_3 = class1.method145(0, k, k1, l1, i2, j2, -1);
            class32.method289(0, k2, i, anIntArray127[k], 8, class38_sub2_sub1_3, null, j1, l2, j, byte0);
            if (k == 0) {
                if (class1.aBoolean65) {
                    aByteArrayArrayArray119[i][j1][j] = 50;
                    aByteArrayArrayArray119[i][j1][j + 1] = 50;
                }
                if (class1.aBoolean55)
                    anIntArrayArrayArray126[i][j1][j] |= 0x249;
            } else if (k == 1) {
                if (class1.aBoolean65) {
                    aByteArrayArrayArray119[i][j1][j + 1] = 50;
                    aByteArrayArrayArray119[i][j1 + 1][j + 1] = 50;
                }
                if (class1.aBoolean55)
                    anIntArrayArrayArray126[i][j1][j + 1] |= 0x492;
            } else if (k == 2) {
                if (class1.aBoolean65) {
                    aByteArrayArrayArray119[i][j1 + 1][j] = 50;
                    aByteArrayArrayArray119[i][j1 + 1][j + 1] = 50;
                }
                if (class1.aBoolean55)
                    anIntArrayArrayArray126[i][j1 + 1][j] |= 0x249;
            } else if (k == 3) {
                if (class1.aBoolean65) {
                    aByteArrayArrayArray119[i][j1][j] = 50;
                    aByteArrayArrayArray119[i][j1 + 1][j] = 50;
                }
                if (class1.aBoolean55)
                    anIntArrayArrayArray126[i][j1][j] |= 0x492;
            }
            if (class1.aBoolean50 && class8 != null)
                class8.method197(true, k, j, j1, class1.aBoolean51, l);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 0, Class18.aClass18Array364[class1.anInt56], j, j1));
            if (class1.anInt57 != 16)
                class32.method298(i, j, j1, class1.anInt57, (byte) 6);
            return;
        }
        if (l == 1) {
            Class38_Sub2_Sub1 class38_sub2_sub1_4 = class1.method145(1, k, k1, l1, i2, j2, -1);
            class32.method289(0, k2, i, anIntArray128[k], 8, class38_sub2_sub1_4, null, j1, l2, j, byte0);
            if (class1.aBoolean65)
                if (k == 0)
                    aByteArrayArrayArray119[i][j1][j + 1] = 50;
                else if (k == 1)
                    aByteArrayArrayArray119[i][j1 + 1][j + 1] = 50;
                else if (k == 2)
                    aByteArrayArrayArray119[i][j1 + 1][j] = 50;
                else if (k == 3)
                    aByteArrayArrayArray119[i][j1][j] = 50;
            if (class1.aBoolean50 && class8 != null)
                class8.method197(true, k, j, j1, class1.aBoolean51, l);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 0, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 2) {
            int i3 = k + 1 & 3;
            Class38_Sub2_Sub1 class38_sub2_sub1_11 = class1.method145(2, 4 + k, k1, l1, i2, j2, -1);
            Class38_Sub2_Sub1 class38_sub2_sub1_12 = class1.method145(2, i3, k1, l1, i2, j2, -1);
            class32.method289(anIntArray127[i3], k2, i, anIntArray127[k], 8, class38_sub2_sub1_11, class38_sub2_sub1_12,
                    j1, l2, j, byte0);
            if (class1.aBoolean55)
                if (k == 0) {
                    anIntArrayArrayArray126[i][j1][j] |= 0x249;
                    anIntArrayArrayArray126[i][j1][j + 1] |= 0x492;
                } else if (k == 1) {
                    anIntArrayArrayArray126[i][j1][j + 1] |= 0x492;
                    anIntArrayArrayArray126[i][j1 + 1][j] |= 0x249;
                } else if (k == 2) {
                    anIntArrayArrayArray126[i][j1 + 1][j] |= 0x249;
                    anIntArrayArrayArray126[i][j1][j] |= 0x492;
                } else if (k == 3) {
                    anIntArrayArrayArray126[i][j1][j] |= 0x492;
                    anIntArrayArrayArray126[i][j1][j] |= 0x249;
                }
            if (class1.aBoolean50 && class8 != null)
                class8.method197(true, k, j, j1, class1.aBoolean51, l);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 0, Class18.aClass18Array364[class1.anInt56], j, j1));
            if (class1.anInt57 != 16)
                class32.method298(i, j, j1, class1.anInt57, (byte) 6);
            return;
        }
        if (l == 3) {
            Class38_Sub2_Sub1 class38_sub2_sub1_5 = class1.method145(3, k, k1, l1, i2, j2, -1);
            class32.method289(0, k2, i, anIntArray128[k], 8, class38_sub2_sub1_5, null, j1, l2, j, byte0);
            if (class1.aBoolean65)
                if (k == 0)
                    aByteArrayArrayArray119[i][j1][j + 1] = 50;
                else if (k == 1)
                    aByteArrayArrayArray119[i][j1 + 1][j + 1] = 50;
                else if (k == 2)
                    aByteArrayArrayArray119[i][j1 + 1][j] = 50;
                else if (k == 3)
                    aByteArrayArrayArray119[i][j1][j] = 50;
            if (class1.aBoolean50 && class8 != null)
                class8.method197(true, k, j, j1, class1.aBoolean51, l);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 0, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 9) {
            Class38_Sub2_Sub1 class38_sub2_sub1_6 = class1.method145(l, k, k1, l1, i2, j2, -1);
            class32.method291(k2, 775, i, null, l2, j, j1, 1, byte0, class38_sub2_sub1_6, 0, 1);
            if (class1.aBoolean50 && class8 != null)
                class8.method198(k, class1.anInt49, class1.anInt48, j1, 9, j, class1.aBoolean51);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 2, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 4) {
            Class38_Sub2_Sub1 class38_sub2_sub1_7 = class1.method145(4, 0, k1, l1, i2, j2, -1);
            class32.method290(k2, j, 0, l2, k * 512, anIntArray127[k], 465, 0, j1, class38_sub2_sub1_7, byte0, i);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 1, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 5) {
            int j3 = 16;
            int l3 = class32.method308(i, j1, j);
            if (l3 > 0)
                j3 = Class1.method142(l3 >> 14 & 0x7fff).anInt57;
            Class38_Sub2_Sub1 class38_sub2_sub1_13 = class1.method145(4, 0, k1, l1, i2, j2, -1);
            class32.method290(k2, j, anIntArray130[k] * j3, l2, k * 512, anIntArray127[k], 465, anIntArray129[k] * j3,
                    j1, class38_sub2_sub1_13, byte0, i);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 1, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 6) {
            Class38_Sub2_Sub1 class38_sub2_sub1_8 = class1.method145(4, 0, k1, l1, i2, j2, -1);
            class32.method290(k2, j, 0, l2, k, 256, 465, 0, j1, class38_sub2_sub1_8, byte0, i);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 1, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 7) {
            Class38_Sub2_Sub1 class38_sub2_sub1_9 = class1.method145(4, 0, k1, l1, i2, j2, -1);
            class32.method290(k2, j, 0, l2, k, 512, 465, 0, j1, class38_sub2_sub1_9, byte0, i);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 1, Class18.aClass18Array364[class1.anInt56], j, j1));
            return;
        }
        if (l == 8) {
            Class38_Sub2_Sub1 class38_sub2_sub1_10 = class1.method145(4, 0, k1, l1, i2, j2, -1);
            class32.method290(k2, j, 0, l2, k, 768, 465, 0, j1, class38_sub2_sub1_10, byte0, i);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, i1, i, 0, 1, Class18.aClass18Array364[class1.anInt56], j, j1));
        }
    }

    public void method156(Class32 class32, int i, Class8[] aclass8) {
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 104; k++) {
                for (int i1 = 0; i1 < 104; i1++)
                    if ((aByteArrayArrayArray114[j][k][i1] & 1) == 1) {
                        int l1 = j;
                        if ((aByteArrayArrayArray114[1][k][i1] & 2) == 2)
                            l1--;
                        if (l1 >= 0)
                            aclass8[l1].method199((byte) 12, i1, k);
                    }

            }

        }

        anInt131 += (int) (Math.random() * 5D) - 2;
        if (anInt131 < -8)
            anInt131 = -8;
        if (anInt131 > 8)
            anInt131 = 8;
        anInt132 += (int) (Math.random() * 5D) - 2;
        if (anInt132 < -16)
            anInt132 = -16;
        if (anInt132 > 16)
            anInt132 = 16;
        for (int l = 0; l < 4; l++) {
            byte[][] abyte0 = aByteArrayArrayArray119[l];
            byte byte0 = 96;
            char c = '\u0300';
            byte byte1 = -50;
            byte byte2 = -10;
            byte byte3 = -50;
            int k3 = (int) Math.sqrt(byte1 * byte1 + byte2 * byte2 + byte3 * byte3);
            int i4 = c * k3 >> 8;
            for (int k4 = 1; k4 < anInt112 - 1; k4++) {
                for (int k5 = 1; k5 < anInt111 - 1; k5++) {
                    int l6 = anIntArrayArrayArray113[l][k5 + 1][k4] - anIntArrayArrayArray113[l][k5 - 1][k4];
                    int i8 = anIntArrayArrayArray113[l][k5][k4 + 1] - anIntArrayArrayArray113[l][k5][k4 - 1];
                    int k9 = (int) Math.sqrt(l6 * l6 + 0x10000 + i8 * i8);
                    int l12 = (l6 << 8) / k9;
                    int i14 = 0x10000 / k9;
                    int k15 = (i8 << 8) / k9;
                    int k16 = byte0 + (byte1 * l12 + byte2 * i14 + byte3 * k15) / i4;
                    int k17 = (abyte0[k5 - 1][k4] >> 2) + (abyte0[k5 + 1][k4] >> 3) + (abyte0[k5][k4 - 1] >> 2)
                            + (abyte0[k5][k4 + 1] >> 3) + (abyte0[k5][k4] >> 1);
                    anIntArrayArray120[k5][k4] = k16 - k17;
                }

            }

            for (int l5 = 0; l5 < anInt112; l5++) {
                anIntArray121[l5] = 0;
                anIntArray122[l5] = 0;
                anIntArray123[l5] = 0;
                anIntArray124[l5] = 0;
                anIntArray125[l5] = 0;
            }

            for (int i7 = -5; i7 < anInt111 + 5; i7++) {
                for (int j8 = 0; j8 < anInt112; j8++) {
                    int l9 = i7 + 5;
                    if (l9 >= 0 && l9 < anInt111) {
                        int i13 = aByteArrayArrayArray115[l][l9][j8] & 0xff;
                        if (i13 > 0) {
                            Class10 class10 = Class10.aClass10Array223[i13 - 1];
                            anIntArray121[j8] += class10.anInt232;
                            anIntArray122[j8] += class10.anInt230;
                            anIntArray123[j8] += class10.anInt231;
                            anIntArray124[j8] += class10.anInt233;
                            anIntArray125[j8]++;
                        }
                    }
                    int j13 = i7 - 5;
                    if (j13 >= 0 && j13 < anInt111) {
                        int j14 = aByteArrayArrayArray115[l][j13][j8] & 0xff;
                        if (j14 > 0) {
                            Class10 class10_1 = Class10.aClass10Array223[j14 - 1];
                            anIntArray121[j8] -= class10_1.anInt232;
                            anIntArray122[j8] -= class10_1.anInt230;
                            anIntArray123[j8] -= class10_1.anInt231;
                            anIntArray124[j8] -= class10_1.anInt233;
                            anIntArray125[j8]--;
                        }
                    }
                }

                if (i7 >= 1 && i7 < anInt111 - 1) {
                    int i10 = 0;
                    int k13 = 0;
                    int k14 = 0;
                    int l15 = 0;
                    int l16 = 0;
                    for (int l17 = -5; l17 < anInt112 + 5; l17++) {
                        int k18 = l17 + 5;
                        if (k18 >= 0 && k18 < anInt112) {
                            i10 += anIntArray121[k18];
                            k13 += anIntArray122[k18];
                            k14 += anIntArray123[k18];
                            l15 += anIntArray124[k18];
                            l16 += anIntArray125[k18];
                        }
                        int l18 = l17 - 5;
                        if (l18 >= 0 && l18 < anInt112) {
                            i10 -= anIntArray121[l18];
                            k13 -= anIntArray122[l18];
                            k14 -= anIntArray123[l18];
                            l15 -= anIntArray124[l18];
                            l16 -= anIntArray125[l18];
                        }
                        if (l17 >= 1 && l17 < anInt112 - 1
                                && (!aBoolean108 || (aByteArrayArrayArray114[l][i7][l17] & 0x10) == 0
                                && method157(l, (byte) 10, i7, l17) == anInt109)) {
                            int i19 = aByteArrayArrayArray115[l][i7][l17] & 0xff;
                            int j19 = aByteArrayArrayArray116[l][i7][l17] & 0xff;
                            if (i19 > 0 || j19 > 0) {
                                int k19 = anIntArrayArrayArray113[l][i7][l17];
                                int l19 = anIntArrayArrayArray113[l][i7 + 1][l17];
                                int i20 = anIntArrayArrayArray113[l][i7 + 1][l17 + 1];
                                int j20 = anIntArrayArrayArray113[l][i7][l17 + 1];
                                int k20 = anIntArrayArray120[i7][l17];
                                int l20 = anIntArrayArray120[i7 + 1][l17];
                                int i21 = anIntArrayArray120[i7 + 1][l17 + 1];
                                int j21 = anIntArrayArray120[i7][l17 + 1];
                                int k21 = -1;
                                int l21 = -1;
                                if (i19 > 0) {
                                    int i22 = (i10 * 256) / l15;
                                    int k22 = k13 / l16;
                                    int i23 = k14 / l16;
                                    k21 = method165(i22, k22, i23);
                                    i22 = i22 + anInt131 & 0xff;
                                    i23 += anInt132;
                                    if (i23 < 0)
                                        i23 = 0;
                                    else if (i23 > 255)
                                        i23 = 255;
                                    l21 = method165(i22, k22, i23);
                                }
                                if (l > 0) {
                                    boolean flag = i19 != 0 || aByteArrayArrayArray117[l][i7][l17] == 0;
                                    if (j19 > 0 && !Class10.aClass10Array223[j19 - 1].aBoolean227)
                                        flag = false;
                                    if (flag && k19 == l19 && k19 == i20 && k19 == j20)
                                        anIntArrayArrayArray126[l][i7][l17] |= 0x924;
                                }
                                int j22 = 0;
                                if (k21 != -1)
                                    j22 = Class38_Sub2_Sub2_Sub1.anIntArray1458[method163(l21, 96)];
                                if (j19 == 0) {
                                    class32.method286(l, i7, l17, 0, 0, -1, k19, l19, i20, j20, method163(k21, k20),
                                            method163(k21, l20), method163(k21, i21), method163(k21, j21), 0, 0, 0, 0,
                                            j22, 0);
                                } else {
                                    int l22 = aByteArrayArrayArray117[l][i7][l17] + 1;
                                    byte byte4 = aByteArrayArrayArray118[l][i7][l17];
                                    Class10 class10_2 = Class10.aClass10Array223[j19 - 1];
                                    int j23 = class10_2.anInt225;
                                    int k23;
                                    int l23;
                                    if (j23 >= 0) {
                                        l23 = Class38_Sub2_Sub2_Sub1.method390(787, j23);
                                        k23 = -1;
                                    } else if (class10_2.anInt224 == 0xff00ff) {
                                        l23 = 0;
                                        k23 = -2;
                                        j23 = -1;
                                    } else {
                                        k23 = method165(class10_2.anInt229, class10_2.anInt230, class10_2.anInt231);
                                        l23 = Class38_Sub2_Sub2_Sub1.anIntArray1458[method164(class10_2.anInt234, 96)];
                                    }
                                    class32.method286(l, i7, l17, l22, byte4, j23, k19, l19, i20, j20,
                                            method163(k21, k20), method163(k21, l20), method163(k21, i21),
                                            method163(k21, j21), method164(k23, k20), method164(k23, l20),
                                            method164(k23, i21), method164(k23, j21), j22, l23);
                                }
                            }
                        }
                    }

                }
            }

            for (int k8 = 1; k8 < anInt112 - 1; k8++) {
                for (int j10 = 1; j10 < anInt111 - 1; j10++)
                    class32.method285(l, j10, k8, method157(l, (byte) 10, j10, k8));

            }

        }

        while (i >= 0) {
            for (int j1 = 1; j1 > 0; j1++)
                ;
        }
        if (!aBoolean110)
            class32.method313(-10, 64, -50, 768, -50, false);
        for (int k1 = 0; k1 < anInt111; k1++) {
            for (int i2 = 0; i2 < anInt112; i2++)
                if ((aByteArrayArrayArray114[1][k1][i2] & 2) == 2)
                    class32.method283(i2, k1, (byte) -41);

        }

        if (aBoolean110)
            return;
        int j2 = 1;
        int k2 = 2;
        int l2 = 4;
        for (int i3 = 0; i3 < 4; i3++) {
            if (i3 > 0) {
                j2 <<= 3;
                k2 <<= 3;
                l2 <<= 3;
            }
            for (int j3 = 0; j3 <= i3; j3++) {
                for (int l3 = 0; l3 <= anInt112; l3++) {
                    for (int j4 = 0; j4 <= anInt111; j4++) {
                        if ((anIntArrayArrayArray126[j3][j4][l3] & j2) != 0) {
                            int l4 = l3;
                            int i6 = l3;
                            int j7 = j3;
                            int l8 = j3;
                            for (; l4 > 0 && (anIntArrayArrayArray126[j3][j4][l4 - 1] & j2) != 0; l4--)
                                ;
                            for (; i6 < anInt112 && (anIntArrayArrayArray126[j3][j4][i6 + 1] & j2) != 0; i6++)
                                ;
                            label0:
                            for (; j7 > 0; j7--) {
                                for (int k10 = l4; k10 <= i6; k10++)
                                    if ((anIntArrayArrayArray126[j7 - 1][j4][k10] & j2) == 0)
                                        break label0;

                            }

                            label1:
                            for (; l8 < i3; l8++) {
                                for (int l10 = l4; l10 <= i6; l10++)
                                    if ((anIntArrayArrayArray126[l8 + 1][j4][l10] & j2) == 0)
                                        break label1;

                            }

                            int i11 = ((l8 + 1) - j7) * ((i6 - l4) + 1);
                            if (i11 >= 8) {
                                char c1 = '\360';
                                int l14 = anIntArrayArrayArray113[l8][j4][l4] - c1;
                                int i16 = anIntArrayArrayArray113[j7][j4][l4];
                                Class32.method284(i6 * 128 + 128, j4 * 128, -802, i16, 1, j4 * 128, i3, l14, l4 * 128);
                                for (int i17 = j7; i17 <= l8; i17++) {
                                    for (int i18 = l4; i18 <= i6; i18++)
                                        anIntArrayArrayArray126[i17][j4][i18] &= ~j2;

                                }

                            }
                        }
                        if ((anIntArrayArrayArray126[j3][j4][l3] & k2) != 0) {
                            int i5 = j4;
                            int j6 = j4;
                            int k7 = j3;
                            int i9 = j3;
                            for (; i5 > 0 && (anIntArrayArrayArray126[j3][i5 - 1][l3] & k2) != 0; i5--)
                                ;
                            for (; j6 < anInt111 && (anIntArrayArrayArray126[j3][j6 + 1][l3] & k2) != 0; j6++)
                                ;
                            label2:
                            for (; k7 > 0; k7--) {
                                for (int j11 = i5; j11 <= j6; j11++)
                                    if ((anIntArrayArrayArray126[k7 - 1][j11][l3] & k2) == 0)
                                        break label2;

                            }

                            label3:
                            for (; i9 < i3; i9++) {
                                for (int k11 = i5; k11 <= j6; k11++)
                                    if ((anIntArrayArrayArray126[i9 + 1][k11][l3] & k2) == 0)
                                        break label3;

                            }

                            int l11 = ((i9 + 1) - k7) * ((j6 - i5) + 1);
                            if (l11 >= 8) {
                                char c2 = '\360';
                                int i15 = anIntArrayArrayArray113[i9][i5][l3] - c2;
                                int j16 = anIntArrayArrayArray113[k7][i5][l3];
                                Class32.method284(l3 * 128, i5 * 128, -802, j16, 2, j6 * 128 + 128, i3, i15, l3 * 128);
                                for (int j17 = k7; j17 <= i9; j17++) {
                                    for (int j18 = i5; j18 <= j6; j18++)
                                        anIntArrayArrayArray126[j17][j18][l3] &= ~k2;

                                }

                            }
                        }
                        if ((anIntArrayArrayArray126[j3][j4][l3] & l2) != 0) {
                            int j5 = j4;
                            int k6 = j4;
                            int l7 = l3;
                            int j9 = l3;
                            for (; l7 > 0 && (anIntArrayArrayArray126[j3][j4][l7 - 1] & l2) != 0; l7--)
                                ;
                            for (; j9 < anInt112 && (anIntArrayArrayArray126[j3][j4][j9 + 1] & l2) != 0; j9++)
                                ;
                            label4:
                            for (; j5 > 0; j5--) {
                                for (int i12 = l7; i12 <= j9; i12++)
                                    if ((anIntArrayArrayArray126[j3][j5 - 1][i12] & l2) == 0)
                                        break label4;

                            }

                            label5:
                            for (; k6 < anInt111; k6++) {
                                for (int j12 = l7; j12 <= j9; j12++)
                                    if ((anIntArrayArrayArray126[j3][k6 + 1][j12] & l2) == 0)
                                        break label5;

                            }

                            if (((k6 - j5) + 1) * ((j9 - l7) + 1) >= 4) {
                                int k12 = anIntArrayArrayArray113[j3][j5][l7];
                                Class32.method284(j9 * 128 + 128, j5 * 128, -802, k12, 4, k6 * 128 + 128, i3, k12,
                                        l7 * 128);
                                for (int l13 = j5; l13 <= k6; l13++) {
                                    for (int j15 = l7; j15 <= j9; j15++)
                                        anIntArrayArrayArray126[j3][l13][j15] &= ~l2;

                                }

                            }
                        }
                    }

                }

            }

        }

    }

    public int method157(int i, byte byte0, int j, int k) {
        if (byte0 != 10)
            anInt107 = 29;
        if ((aByteArrayArrayArray114[i][j][k] & 8) != 0)
            return 0;
        if (i > 0 && (aByteArrayArrayArray114[1][j][k] & 2) != 0)
            return i - 1;
        else
            return i;
    }

    public static int method158(int i, int j) {
        int k = (method159(i + 45365, j + 0x16713, 4) - 128) + (method159(i + 10294, j + 37821, 2) - 128 >> 1)
                + (method159(i, j, 1) - 128 >> 2);
        k = (int) ((double) k * 0.29999999999999999D) + 35;
        if (k < 10)
            k = 10;
        else if (k > 60)
            k = 60;
        return k;
    }

    public static int method159(int i, int j, int k) {
        int l = i / k;
        int i1 = i & k - 1;
        int j1 = j / k;
        int k1 = j & k - 1;
        int l1 = method161(l, j1);
        int i2 = method161(l + 1, j1);
        int j2 = method161(l, j1 + 1);
        int k2 = method161(l + 1, j1 + 1);
        int l2 = method160(l1, i2, i1, k);
        int i3 = method160(j2, k2, i1, k);
        return method160(l2, i3, k1, k);
    }

    public static int method160(int i, int j, int k, int l) {
        int i1 = 0x10000 - Class38_Sub2_Sub2_Sub1.anIntArray1447[(k * 1024) / l] >> 1;
        return (i * (0x10000 - i1) >> 16) + (j * i1 >> 16);
    }

    public static int method161(int i, int j) {
        int k = method162(i - 1, j - 1) + method162(i + 1, j - 1) + method162(i - 1, j + 1) + method162(i + 1, j + 1);
        int l = method162(i - 1, j) + method162(i + 1, j) + method162(i, j - 1) + method162(i, j + 1);
        int i1 = method162(i, j);
        return k / 16 + l / 8 + i1 / 4;
    }

    public static int method162(int i, int j) {
        int k = i + j * 57;
        k = k << 13 ^ k;
        int l = k * (k * k * 15731 + 0xc0ae5) + 0x5208dd0d & 0x7fffffff;
        return l >> 19 & 0xff;
    }

    public static int method163(int i, int j) {
        if (i == -1)
            return 0xbc614e;
        j = (j * (i & 0x7f)) / 128;
        if (j < 2)
            j = 2;
        else if (j > 126)
            j = 126;
        return (i & 0xff80) + j;
    }

    public int method164(int i, int j) {
        if (i == -2)
            return 0xbc614e;
        if (i == -1) {
            if (j < 0)
                j = 0;
            else if (j > 127)
                j = 127;
            j = 127 - j;
            return j;
        }
        j = (j * (i & 0x7f)) / 128;
        if (j < 2)
            j = 2;
        else if (j > 126)
            j = 126;
        return (i & 0xff80) + j;
    }

    public int method165(int i, int j, int k) {
        if (k > 179)
            j /= 2;
        if (k > 192)
            j /= 2;
        if (k > 217)
            j /= 2;
        if (k > 243)
            j /= 2;
        int l = (i / 4 << 10) + (j / 32 << 7) + k / 2;
        return l;
    }

    public static void method166(int i, Class27 class27, Class8 class8, int j, int k, int[][][] ai, int l, int i1,
                                 int j1, int k1, Class32 class32, int l1) {
        if (l != 0)
            aBoolean105 = !aBoolean105;
        int i2 = ai[l1][i][j];
        int j2 = ai[l1][i + 1][j];
        int k2 = ai[l1][i + 1][j + 1];
        int l2 = ai[l1][i][j + 1];
        int i3 = i2 + j2 + k2 + l2 >> 2;
        Class1 class1 = Class1.method142(j1);
        int j3 = i + (j << 7) + (j1 << 14) + 0x40000000;
        if (!class1.aBoolean52)
            j3 += 0x80000000;
        byte byte0 = (byte) ((k << 6) + k1);
        if (k1 == 22) {
            Class38_Sub2_Sub1 class38_sub2_sub1 = class1.method145(22, k, i2, j2, k2, l2, -1);
            class32.method287(class38_sub2_sub1, (byte) 6, i, j3, j, i1, byte0, i3);
            if (class1.aBoolean50 && class1.aBoolean52)
                class8.method199((byte) 12, j, i);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 3, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 10 || k1 == 11) {
            Class38_Sub2_Sub1 class38_sub2_sub1_1 = class1.method145(10, k, i2, j2, k2, l2, -1);
            if (class38_sub2_sub1_1 != null) {
                int l4 = 0;
                if (k1 == 11)
                    l4 += 256;
                int i4;
                int k4;
                if (k == 1 || k == 3) {
                    i4 = class1.anInt49;
                    k4 = class1.anInt48;
                } else {
                    i4 = class1.anInt48;
                    k4 = class1.anInt49;
                }
                class32.method291(i3, 775, i1, null, j3, j, i, i4, byte0, class38_sub2_sub1_1, l4, k4);
            }
            if (class1.aBoolean50)
                class8.method198(k, class1.anInt49, class1.anInt48, i, 9, j, class1.aBoolean51);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 2, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 >= 12) {
            Class38_Sub2_Sub1 class38_sub2_sub1_2 = class1.method145(k1, k, i2, j2, k2, l2, -1);
            class32.method291(i3, 775, i1, null, j3, j, i, 1, byte0, class38_sub2_sub1_2, 0, 1);
            if (class1.aBoolean50)
                class8.method198(k, class1.anInt49, class1.anInt48, i, 9, j, class1.aBoolean51);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 2, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 0) {
            Class38_Sub2_Sub1 class38_sub2_sub1_3 = class1.method145(0, k, i2, j2, k2, l2, -1);
            class32.method289(0, i3, i1, anIntArray127[k], 8, class38_sub2_sub1_3, null, i, j3, j, byte0);
            if (class1.aBoolean50)
                class8.method197(true, k, j, i, class1.aBoolean51, k1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 0, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 1) {
            Class38_Sub2_Sub1 class38_sub2_sub1_4 = class1.method145(1, k, i2, j2, k2, l2, -1);
            class32.method289(0, i3, i1, anIntArray128[k], 8, class38_sub2_sub1_4, null, i, j3, j, byte0);
            if (class1.aBoolean50)
                class8.method197(true, k, j, i, class1.aBoolean51, k1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 0, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 2) {
            int k3 = k + 1 & 3;
            Class38_Sub2_Sub1 class38_sub2_sub1_11 = class1.method145(2, 4 + k, i2, j2, k2, l2, -1);
            Class38_Sub2_Sub1 class38_sub2_sub1_12 = class1.method145(2, k3, i2, j2, k2, l2, -1);
            class32.method289(anIntArray127[k3], i3, i1, anIntArray127[k], 8, class38_sub2_sub1_11,
                    class38_sub2_sub1_12, i, j3, j, byte0);
            if (class1.aBoolean50)
                class8.method197(true, k, j, i, class1.aBoolean51, k1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 0, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 3) {
            Class38_Sub2_Sub1 class38_sub2_sub1_5 = class1.method145(3, k, i2, j2, k2, l2, -1);
            class32.method289(0, i3, i1, anIntArray128[k], 8, class38_sub2_sub1_5, null, i, j3, j, byte0);
            if (class1.aBoolean50)
                class8.method197(true, k, j, i, class1.aBoolean51, k1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 0, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 9) {
            Class38_Sub2_Sub1 class38_sub2_sub1_6 = class1.method145(k1, k, i2, j2, k2, l2, -1);
            class32.method291(i3, 775, i1, null, j3, j, i, 1, byte0, class38_sub2_sub1_6, 0, 1);
            if (class1.aBoolean50)
                class8.method198(k, class1.anInt49, class1.anInt48, i, 9, j, class1.aBoolean51);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 2, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 4) {
            Class38_Sub2_Sub1 class38_sub2_sub1_7 = class1.method145(4, 0, i2, j2, k2, l2, -1);
            class32.method290(i3, j, 0, j3, k * 512, anIntArray127[k], 465, 0, i, class38_sub2_sub1_7, byte0, i1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 1, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 5) {
            int l3 = 16;
            int j4 = class32.method308(i1, i, j);
            if (j4 > 0)
                l3 = Class1.method142(j4 >> 14 & 0x7fff).anInt57;
            Class38_Sub2_Sub1 class38_sub2_sub1_13 = class1.method145(4, 0, i2, j2, k2, l2, -1);
            class32.method290(i3, j, anIntArray130[k] * l3, j3, k * 512, anIntArray127[k], 465, anIntArray129[k] * l3,
                    i, class38_sub2_sub1_13, byte0, i1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 1, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 6) {
            Class38_Sub2_Sub1 class38_sub2_sub1_8 = class1.method145(4, 0, i2, j2, k2, l2, -1);
            class32.method290(i3, j, 0, j3, k, 256, 465, 0, i, class38_sub2_sub1_8, byte0, i1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 1, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 7) {
            Class38_Sub2_Sub1 class38_sub2_sub1_9 = class1.method145(4, 0, i2, j2, k2, l2, -1);
            class32.method290(i3, j, 0, j3, k, 512, 465, 0, i, class38_sub2_sub1_9, byte0, i1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 1, Class18.aClass18Array364[class1.anInt56], j, i));
            return;
        }
        if (k1 == 8) {
            Class38_Sub2_Sub1 class38_sub2_sub1_10 = class1.method145(4, 0, i2, j2, k2, l2, -1);
            class32.method290(i3, j, 0, j3, k, 768, 465, 0, i, class38_sub2_sub1_10, byte0, i1);
            if (class1.anInt56 != -1)
                class27.method267(new Class38_Sub5(true, j1, i1, 0, 1, Class18.aClass18Array364[class1.anInt56], j, i));
        }
    }

    public static boolean aBoolean105 = true;
    public boolean aBoolean106;
    public int anInt107;
    public static boolean aBoolean108 = true;
    public static int anInt109;
    public static boolean aBoolean110;
    public int anInt111;
    public int anInt112;
    public int[][][] anIntArrayArrayArray113;
    public byte[][][] aByteArrayArrayArray114;
    public byte[][][] aByteArrayArrayArray115;
    public byte[][][] aByteArrayArrayArray116;
    public byte[][][] aByteArrayArrayArray117;
    public byte[][][] aByteArrayArrayArray118;
    public byte[][][] aByteArrayArrayArray119;
    public int[][] anIntArrayArray120;
    public int[] anIntArray121;
    public int[] anIntArray122;
    public int[] anIntArray123;
    public int[] anIntArray124;
    public int[] anIntArray125;
    public int[][][] anIntArrayArrayArray126;
    public static final int[] anIntArray127 = {
            1, 2, 4, 8
    };
    public static final int[] anIntArray128 = {
            16, 32, 64, 128
    };
    public static final int[] anIntArray129 = {
            1, 0, -1, 0
    };
    public static final int[] anIntArray130 = {
            0, -1, 0, 1
    };
    public static int anInt131 = (int) (Math.random() * 17D) - 8;
    public static int anInt132 = (int) (Math.random() * 33D) - 16;

}
