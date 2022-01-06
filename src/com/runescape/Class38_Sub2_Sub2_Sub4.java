package com.runescape;

import java.util.Random;

public class Class38_Sub2_Sub2_Sub4 extends Class38_Sub2_Sub2 {

    public Class38_Sub2_Sub2_Sub4(FileArchive fileArchive, String s, int i) {
        aBoolean1484 = true;
        aByte1485 = 8;
        aByte1486 = 6;
        aByte1487 = 2;
        anInt1488 = -708;
        anInt1489 = 997;
        aByteArrayArray1490 = new byte[94][];
        anIntArray1491 = new int[94];
        anIntArray1492 = new int[94];
        anIntArray1493 = new int[94];
        anIntArray1494 = new int[94];
        anIntArray1495 = new int[95];
        anIntArray1496 = new int[256];
        aRandom1498 = new Random();
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, fileArchive.read(s + ".dat", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_1 = new Class38_Sub2_Sub3(363,
                fileArchive.read("index.dat", null));
        class38_sub2_sub3_1.offset = class38_sub2_sub3.method448() + 4;
        int j = class38_sub2_sub3_1.method446();
        if (j > 0)
            class38_sub2_sub3_1.offset += 3 * (j - 1);
        for (int k = 0; k < 94; k++) {
            anIntArray1493[k] = class38_sub2_sub3_1.method446();
            anIntArray1494[k] = class38_sub2_sub3_1.method446();
            int l = anIntArray1491[k] = class38_sub2_sub3_1.method448();
            int j1 = anIntArray1492[k] = class38_sub2_sub3_1.method448();
            int k1 = class38_sub2_sub3_1.method446();
            int l1 = l * j1;
            aByteArrayArray1490[k] = new byte[l1];
            if (k1 == 0) {
                for (int i2 = 0; i2 < l1; i2++)
                    aByteArrayArray1490[k][i2] = class38_sub2_sub3.method447();

            } else if (k1 == 1) {
                for (int j2 = 0; j2 < l; j2++) {
                    for (int l2 = 0; l2 < j1; l2++)
                        aByteArrayArray1490[k][j2 + l2 * l] = class38_sub2_sub3.method447();

                }

            }
            if (j1 > anInt1497)
                anInt1497 = j1;
            anIntArray1493[k] = 1;
            anIntArray1495[k] = l + 2;
            int k2 = 0;
            for (int i3 = j1 / 7; i3 < j1; i3++)
                k2 += aByteArrayArray1490[k][i3 * l];

            if (k2 <= j1 / 7) {
                anIntArray1495[k]--;
                anIntArray1493[k] = 0;
            }
            k2 = 0;
            for (int j3 = j1 / 7; j3 < j1; j3++)
                k2 += aByteArrayArray1490[k][(l - 1) + j3 * l];

            if (k2 <= j1 / 7)
                anIntArray1495[k]--;
        }

        i = 9 / i;
        anIntArray1495[94] = anIntArray1495[8];
        for (int i1 = 0; i1 < 256; i1++)
            anIntArray1496[i1] = anIntArray1495[anIntArray1499[i1]];

    }

    public void method421(int i, byte byte0, int j, String s, int k) {
        if (byte0 != 6)
            anInt1489 = 140;
        method424(k - method423(false, s) / 2, i, false, j, s);
    }

    public void method422(int i, int j, boolean flag, int k, String s, int l) {
        method426(i - method423(false, s) / 2, 6, k, s, flag, j);
        if (l != 0)
            aBoolean1484 = !aBoolean1484;
    }

    public int method423(boolean flag, String s) {
        if (s == null)
            return 0;
        int i = 0;
        if (flag)
            return anInt1488;
        for (int j = 0; j < s.length(); j++)
            if (s.charAt(j) == '@' && j + 4 < s.length() && s.charAt(j + 4) == '@')
                j += 4;
            else
                i += anIntArray1496[s.charAt(j)];

        return i;
    }

    public void method424(int i, int j, boolean flag, int k, String s) {
        if (s == null)
            return;
        j -= anInt1497;
        if (flag)
            aBoolean1484 = !aBoolean1484;
        for (int l = 0; l < s.length(); l++) {
            int i1 = anIntArray1499[s.charAt(l)];
            if (i1 != 94)
                method429(aByteArrayArray1490[i1], i + anIntArray1493[i1], j + anIntArray1494[i1], anIntArray1491[i1],
                        anIntArray1492[i1], k);
            i += anIntArray1495[i1];
        }

    }

    public void method425(int i, byte byte0, int j, int k, int l, String s) {
        if (s == null)
            return;
        j -= method423(false, s) / 2;
        k -= anInt1497;
        if (byte0 != aByte1485) {
            for (int i1 = 1; i1 > 0; i1++)
                ;
        }
        for (int j1 = 0; j1 < s.length(); j1++) {
            int k1 = anIntArray1499[s.charAt(j1)];
            if (k1 != 94)
                method429(aByteArrayArray1490[k1], j + anIntArray1493[k1],
                        k + anIntArray1494[k1] + (int) (Math.sin((double) j1 / 2D + (double) i / 5D) * 5D),
                        anIntArray1491[k1], anIntArray1492[k1], l);
            j += anIntArray1495[k1];
        }

    }

    public void method426(int i, int j, int k, String s, boolean flag, int l) {
        if (s == null)
            return;
        k -= anInt1497;
        for (int i1 = 0; i1 < s.length(); i1++)
            if (s.charAt(i1) == '@' && i1 + 4 < s.length() && s.charAt(i1 + 4) == '@') {
                l = method428(0, s.substring(i1 + 1, i1 + 4));
                i1 += 4;
            } else {
                int j1 = anIntArray1499[s.charAt(i1)];
                if (j1 != 94) {
                    if (flag)
                        method429(aByteArrayArray1490[j1], i + anIntArray1493[j1] + 1, k + anIntArray1494[j1] + 1,
                                anIntArray1491[j1], anIntArray1492[j1], 0);
                    method429(aByteArrayArray1490[j1], i + anIntArray1493[j1], k + anIntArray1494[j1],
                            anIntArray1491[j1], anIntArray1492[j1], l);
                }
                i += anIntArray1495[j1];
            }

        if (j == 6)
            ;
    }

    public void method427(int i, boolean flag, byte byte0, int j, int k, String s, int l) {
        if (s == null)
            return;
        aRandom1498.setSeed(i);
        int i1 = 192 + (aRandom1498.nextInt() & 0x1f);
        j -= anInt1497;
        if (byte0 != -121)
            anInt1489 = 341;
        for (int j1 = 0; j1 < s.length(); j1++)
            if (s.charAt(j1) == '@' && j1 + 4 < s.length() && s.charAt(j1 + 4) == '@') {
                k = method428(0, s.substring(j1 + 1, j1 + 4));
                j1 += 4;
            } else {
                int k1 = anIntArray1499[s.charAt(j1)];
                if (k1 != 94) {
                    if (flag)
                        method431(aByteArrayArray1490[k1], (byte) 6, l + anIntArray1493[k1] + 1, anIntArray1492[k1], 0,
                                j + anIntArray1494[k1] + 1, 192, anIntArray1491[k1]);
                    method431(aByteArrayArray1490[k1], (byte) 6, l + anIntArray1493[k1], anIntArray1492[k1], k,
                            j + anIntArray1494[k1], i1, anIntArray1491[k1]);
                }
                l += anIntArray1495[k1];
                if ((aRandom1498.nextInt() & 3) == 0)
                    l++;
            }

    }

    public int method428(int i, String s) {
        if (i != 0)
            anInt1488 = 450;
        if (s.equals("red"))
            return 0xff0000;
        if (s.equals("gre"))
            return 65280;
        if (s.equals("blu"))
            return 255;
        if (s.equals("yel"))
            return 0xffff00;
        if (s.equals("cya"))
            return 65535;
        if (s.equals("mag"))
            return 0xff00ff;
        if (s.equals("whi"))
            return 0xffffff;
        if (s.equals("bla"))
            return 0;
        if (s.equals("lre"))
            return 0xff9040;
        if (s.equals("dre"))
            return 0x800000;
        if (s.equals("dbl"))
            return 128;
        if (s.equals("or1"))
            return 0xffb000;
        if (s.equals("or2"))
            return 0xff7000;
        if (s.equals("or3"))
            return 0xff3000;
        if (s.equals("gr1"))
            return 0xc0ff00;
        if (s.equals("gr2"))
            return 0x80ff00;
        return !s.equals("gr3") ? 0 : 0x40ff00;
    }

    public void method429(byte[] abyte0, int i, int j, int k, int l, int i1) {
        int j1 = i + j * Class38_Sub2_Sub2.anInt1309;
        int k1 = Class38_Sub2_Sub2.anInt1309 - k;
        int l1 = 0;
        int i2 = 0;
        if (j < Class38_Sub2_Sub2.anInt1311) {
            int j2 = Class38_Sub2_Sub2.anInt1311 - j;
            l -= j2;
            j = Class38_Sub2_Sub2.anInt1311;
            i2 += j2 * k;
            j1 += j2 * Class38_Sub2_Sub2.anInt1309;
        }
        if (j + l >= Class38_Sub2_Sub2.anInt1312)
            l -= ((j + l) - Class38_Sub2_Sub2.anInt1312) + 1;
        if (i < Class38_Sub2_Sub2.anInt1313) {
            int k2 = Class38_Sub2_Sub2.anInt1313 - i;
            k -= k2;
            i = Class38_Sub2_Sub2.anInt1313;
            i2 += k2;
            j1 += k2;
            l1 += k2;
            k1 += k2;
        }
        if (i + k >= Class38_Sub2_Sub2.anInt1314) {
            int l2 = ((i + k) - Class38_Sub2_Sub2.anInt1314) + 1;
            k -= l2;
            l1 += l2;
            k1 += l2;
        }
        if (k <= 0 || l <= 0) {
            return;
        } else {
            method430(Class38_Sub2_Sub2.anIntArray1308, abyte0, i1, i2, j1, k, l, k1, l1);
            return;
        }
    }

    public void method430(int[] ai, byte[] abyte0, int i, int j, int k, int l, int i1,
                          int j1, int k1) {
        int l1 = -(l >> 2);
        l = -(l & 3);
        for (int i2 = -i1; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;
            }

            for (int k2 = l; k2 < 0; k2++)
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;

            k += j1;
            j += k1;
        }

    }

    public void method431(byte[] abyte0, byte byte0, int i, int j, int k, int l, int i1,
                          int j1) {
        if (byte0 != aByte1486)
            return;
        int k1 = i + l * Class38_Sub2_Sub2.anInt1309;
        int l1 = Class38_Sub2_Sub2.anInt1309 - j1;
        int i2 = 0;
        int j2 = 0;
        if (l < Class38_Sub2_Sub2.anInt1311) {
            int k2 = Class38_Sub2_Sub2.anInt1311 - l;
            j -= k2;
            l = Class38_Sub2_Sub2.anInt1311;
            j2 += k2 * j1;
            k1 += k2 * Class38_Sub2_Sub2.anInt1309;
        }
        if (l + j >= Class38_Sub2_Sub2.anInt1312)
            j -= ((l + j) - Class38_Sub2_Sub2.anInt1312) + 1;
        if (i < Class38_Sub2_Sub2.anInt1313) {
            int l2 = Class38_Sub2_Sub2.anInt1313 - i;
            j1 -= l2;
            i = Class38_Sub2_Sub2.anInt1313;
            j2 += l2;
            k1 += l2;
            i2 += l2;
            l1 += l2;
        }
        if (i + j1 >= Class38_Sub2_Sub2.anInt1314) {
            int i3 = ((i + j1) - Class38_Sub2_Sub2.anInt1314) + 1;
            j1 -= i3;
            i2 += i3;
            l1 += i3;
        }
        if (j1 <= 0 || j <= 0) {
            return;
        } else {
            method432(j, k1, j1, Class38_Sub2_Sub2.anIntArray1308, abyte0, i1, j2, l1, i2, (byte) 2, k);
            return;
        }
    }

    public void method432(int i, int j, int k, int[] ai, byte[] abyte0, int l, int i1,
                          int j1, int k1, byte byte0, int l1) {
        l1 = ((l1 & 0xff00ff) * l & 0xff00ff00) + ((l1 & 0xff00) * l & 0xff0000) >> 8;
        if (byte0 == aByte1487) {
            byte0 = 0;
        } else {
            for (int i2 = 1; i2 > 0; i2++)
                ;
        }
        l = 256 - l;
        for (int j2 = -i; j2 < 0; j2++) {
            for (int k2 = -k; k2 < 0; k2++)
                if (abyte0[i1++] != 0) {
                    int l2 = ai[j];
                    ai[j++] = (((l2 & 0xff00ff) * l & 0xff00ff00) + ((l2 & 0xff00) * l & 0xff0000) >> 8) + l1;
                } else {
                    j++;
                }

            j += j1;
            i1 += k1;
        }

    }

    public boolean aBoolean1484;
    public byte aByte1485;
    public byte aByte1486;
    public byte aByte1487;
    public int anInt1488;
    public int anInt1489;
    public byte[][] aByteArrayArray1490;
    public int[] anIntArray1491;
    public int[] anIntArray1492;
    public int[] anIntArray1493;
    public int[] anIntArray1494;
    public int[] anIntArray1495;
    public int[] anIntArray1496;
    public int anInt1497;
    public Random aRandom1498;
    public static int[] anIntArray1499;

    static {
        anIntArray1499 = new int[256];
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
        for (int i = 0; i < 256; i++) {
            int j = s.indexOf(i);
            if (j == -1)
                j = 74;
            anIntArray1499[i] = j;
        }

    }
}
