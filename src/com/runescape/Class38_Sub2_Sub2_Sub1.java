package com.runescape;

public class Class38_Sub2_Sub2_Sub1 extends Class38_Sub2_Sub2 {

    public static void method384(boolean flag) {
        anIntArray1444 = null;
        anIntArray1444 = null;
        anIntArray1446 = null;
        anIntArray1447 = null;
        anIntArray1448 = null;
        aClass38_Sub2_Sub2_Sub3Array1450 = null;
        aBooleanArray1451 = null;
        anIntArray1452 = null;
        if (!flag)
            aBoolean1436 = !aBoolean1436;
        anIntArrayArray1454 = null;
        anIntArrayArray1455 = null;
        anIntArray1456 = null;
        anIntArray1458 = null;
        anIntArrayArray1459 = null;
    }

    public static void method385(int i) {
        while (i >= 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        anIntArray1448 = new int[Class38_Sub2_Sub2.anInt1310];
        for (int k = 0; k < Class38_Sub2_Sub2.anInt1310; k++)
            anIntArray1448[k] = Class38_Sub2_Sub2.anInt1309 * k;

        anInt1442 = Class38_Sub2_Sub2.anInt1309 / 2;
        anInt1443 = Class38_Sub2_Sub2.anInt1310 / 2;
    }

    public static void method386(int i, int j, int k) {
        if (k != 0)
            aBoolean1432 = !aBoolean1432;
        anIntArray1448 = new int[i];
        for (int l = 0; l < i; l++)
            anIntArray1448[l] = j * l;

        anInt1442 = j / 2;
        anInt1443 = i / 2;
    }

    public static void method387(boolean flag) {
        if (flag)
            return;
        anIntArrayArray1454 = null;
        for (int i = 0; i < 50; i++)
            anIntArrayArray1455[i] = null;

    }

    public static void method388(int i, int j) {
        while (j >= 0) {
            for (int k = 1; k > 0; k++)
                ;
        }
        if (anIntArrayArray1454 == null) {
            anInt1453 = i;
            if (aBoolean1437)
                anIntArrayArray1454 = new int[anInt1453][16384];
            else
                anIntArrayArray1454 = new int[anInt1453][0x10000];
            for (int l = 0; l < 50; l++)
                anIntArrayArray1455[l] = null;

        }
    }

    public static void method389(byte byte0, FileArchive fileArchive) {
        if (byte0 != 2)
            return;
        anInt1449 = 0;
        for (int i = 0; i < 50; i++)
            try {
                aClass38_Sub2_Sub2_Sub3Array1450[i] = new Class38_Sub2_Sub2_Sub3(fileArchive, String.valueOf(i), 0);
                if (aBoolean1437 && aClass38_Sub2_Sub2_Sub3Array1450[i].anInt1482 == 128)
                    aClass38_Sub2_Sub2_Sub3Array1450[i].method414(aBoolean1434);
                else
                    aClass38_Sub2_Sub2_Sub3Array1450[i].method415(0);
                anInt1449++;
            } catch (Exception _ex) {
            }

    }

    public static int method390(int i, int j) {
        i = 25 / i;
        if (anIntArray1452[j] != 0)
            return anIntArray1452[j];
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = anIntArrayArray1459[j].length;
        for (int k1 = 0; k1 < j1; k1++) {
            k += anIntArrayArray1459[j][k1] >> 16 & 0xff;
            l += anIntArrayArray1459[j][k1] >> 8 & 0xff;
            i1 += anIntArrayArray1459[j][k1] & 0xff;
        }

        int l1 = (k / j1 << 16) + (l / j1 << 8) + i1 / j1;
        l1 = method394(l1, 1.3999999999999999D);
        if (l1 == 0)
            l1 = 1;
        anIntArray1452[j] = l1;
        return l1;
    }

    public static void method391(int i, int j) {
        if (anIntArrayArray1455[i] == null) {
            return;
        } else {
            anIntArrayArray1454[anInt1453++] = anIntArrayArray1455[i];
            j = 11 / j;
            anIntArrayArray1455[i] = null;
            return;
        }
    }

    public static int[] method392(int i) {
        anIntArray1456[i] = anInt1457++;
        if (anIntArrayArray1455[i] != null)
            return anIntArrayArray1455[i];
        int[] ai;
        if (anInt1453 > 0) {
            ai = anIntArrayArray1454[--anInt1453];
            anIntArrayArray1454[anInt1453] = null;
        } else {
            int j = 0;
            int k = -1;
            for (int l = 0; l < anInt1449; l++)
                if (anIntArrayArray1455[l] != null && (anIntArray1456[l] < j || k == -1)) {
                    j = anIntArray1456[l];
                    k = l;
                }

            ai = anIntArrayArray1455[k];
            anIntArrayArray1455[k] = null;
        }
        anIntArrayArray1455[i] = ai;
        Class38_Sub2_Sub2_Sub3 class38_sub2_sub2_sub3 = aClass38_Sub2_Sub2_Sub3Array1450[i];
        int[] ai1 = anIntArrayArray1459[i];
        if (aBoolean1437) {
            aBooleanArray1451[i] = false;
            for (int i1 = 0; i1 < 4096; i1++) {
                int i2 = ai[i1] = ai1[class38_sub2_sub2_sub3.aByteArray1476[i1]] & 0xf8f8ff;
                if (i2 == 0)
                    aBooleanArray1451[i] = true;
                ai[4096 + i1] = i2 - (i2 >>> 3) & 0xf8f8ff;
                ai[8192 + i1] = i2 - (i2 >>> 2) & 0xf8f8ff;
                ai[12288 + i1] = i2 - (i2 >>> 2) - (i2 >>> 3) & 0xf8f8ff;
            }

        } else {
            if (class38_sub2_sub2_sub3.anInt1478 == 64) {
                for (int j1 = 0; j1 < 128; j1++) {
                    for (int j2 = 0; j2 < 128; j2++)
                        ai[j2 + (j1 << 7)] = ai1[class38_sub2_sub2_sub3.aByteArray1476[(j2 >> 1) + ((j1 >> 1) << 6)]];

                }

            } else {
                for (int k1 = 0; k1 < 16384; k1++)
                    ai[k1] = ai1[class38_sub2_sub2_sub3.aByteArray1476[k1]];

            }
            aBooleanArray1451[i] = false;
            for (int l1 = 0; l1 < 16384; l1++) {
                ai[l1] &= 0xf8f8ff;
                int k2 = ai[l1];
                if (k2 == 0)
                    aBooleanArray1451[i] = true;
                ai[16384 + l1] = k2 - (k2 >>> 3) & 0xf8f8ff;
                ai[32768 + l1] = k2 - (k2 >>> 2) & 0xf8f8ff;
                ai[49152 + l1] = k2 - (k2 >>> 2) - (k2 >>> 3) & 0xf8f8ff;
            }

        }
        return ai;
    }

    public static void method393(boolean flag, double d) {
        d += Math.random() * 0.029999999999999999D - 0.014999999999999999D;
        int i = 0;
        for (int j = 0; j < 512; j++) {
            double d1 = (double) (j / 8) / 64D + 0.0078125D;
            double d2 = (double) (j & 7) / 8D + 0.0625D;
            for (int j1 = 0; j1 < 128; j1++) {
                double d3 = (double) j1 / 128D;
                double d4 = d3;
                double d5 = d3;
                double d6 = d3;
                if (d2 != 0.0D) {
                    double d7;
                    if (d3 < 0.5D)
                        d7 = d3 * (1.0D + d2);
                    else
                        d7 = (d3 + d2) - d3 * d2;
                    double d8 = 2D * d3 - d7;
                    double d9 = d1 + 0.33333333333333331D;
                    if (d9 > 1.0D)
                        d9--;
                    double d10 = d1;
                    double d11 = d1 - 0.33333333333333331D;
                    if (d11 < 0.0D)
                        d11++;
                    if (6D * d9 < 1.0D)
                        d4 = d8 + (d7 - d8) * 6D * d9;
                    else if (2D * d9 < 1.0D)
                        d4 = d7;
                    else if (3D * d9 < 2D)
                        d4 = d8 + (d7 - d8) * (0.66666666666666663D - d9) * 6D;
                    else
                        d4 = d8;
                    if (6D * d10 < 1.0D)
                        d5 = d8 + (d7 - d8) * 6D * d10;
                    else if (2D * d10 < 1.0D)
                        d5 = d7;
                    else if (3D * d10 < 2D)
                        d5 = d8 + (d7 - d8) * (0.66666666666666663D - d10) * 6D;
                    else
                        d5 = d8;
                    if (6D * d11 < 1.0D)
                        d6 = d8 + (d7 - d8) * 6D * d11;
                    else if (2D * d11 < 1.0D)
                        d6 = d7;
                    else if (3D * d11 < 2D)
                        d6 = d8 + (d7 - d8) * (0.66666666666666663D - d11) * 6D;
                    else
                        d6 = d8;
                }
                int k1 = (int) (d4 * 256D);
                int l1 = (int) (d5 * 256D);
                int i2 = (int) (d6 * 256D);
                int j2 = (k1 << 16) + (l1 << 8) + i2;
                j2 = method394(j2, d);
                anIntArray1458[i++] = j2;
            }

        }

        for (int k = 0; k < 50; k++)
            if (aClass38_Sub2_Sub2_Sub3Array1450[k] != null) {
                int[] ai = aClass38_Sub2_Sub2_Sub3Array1450[k].anIntArray1477;
                anIntArrayArray1459[k] = new int[ai.length];
                for (int i1 = 0; i1 < ai.length; i1++)
                    anIntArrayArray1459[k][i1] = method394(ai[i1], d);

            }

        if (!flag)
            anInt1435 = -352;
        for (int l = 0; l < 50; l++)
            method391(l, 150);

    }

    public static int method394(int i, double d) {
        double d1 = (double) (i >> 16) / 256D;
        double d2 = (double) (i >> 8 & 0xff) / 256D;
        double d3 = (double) (i & 0xff) / 256D;
        d1 = Math.pow(d1, d);
        d2 = Math.pow(d2, d);
        d3 = Math.pow(d3, d);
        int j = (int) (d1 * 256D);
        int k = (int) (d2 * 256D);
        int l = (int) (d3 * 256D);
        return (j << 16) + (k << 8) + l;
    }

    public static void method395(int i, int j, int k, int l, int i1, int j1, int k1, int l1,
                                 int i2) {
        int j2 = 0;
        int k2 = 0;
        if (j != i) {
            j2 = (i1 - l << 16) / (j - i);
            k2 = (l1 - k1 << 15) / (j - i);
        }
        int l2 = 0;
        int i3 = 0;
        if (k != j) {
            l2 = (j1 - i1 << 16) / (k - j);
            i3 = (i2 - l1 << 15) / (k - j);
        }
        int j3 = 0;
        int k3 = 0;
        if (k != i) {
            j3 = (l - j1 << 16) / (i - k);
            k3 = (k1 - i2 << 15) / (i - k);
        }
        if (i <= j && i <= k) {
            if (i >= Class38_Sub2_Sub2.anInt1312)
                return;
            if (j > Class38_Sub2_Sub2.anInt1312)
                j = Class38_Sub2_Sub2.anInt1312;
            if (k > Class38_Sub2_Sub2.anInt1312)
                k = Class38_Sub2_Sub2.anInt1312;
            if (j < k) {
                j1 = l <<= 16;
                i2 = k1 <<= 15;
                if (i < 0) {
                    j1 -= j3 * i;
                    l -= j2 * i;
                    i2 -= k3 * i;
                    k1 -= k2 * i;
                    i = 0;
                }
                i1 <<= 16;
                l1 <<= 15;
                if (j < 0) {
                    i1 -= l2 * j;
                    l1 -= i3 * j;
                    j = 0;
                }
                if (i != j && j3 < j2 || i == j && j3 > l2) {
                    k -= j;
                    j -= i;
                    for (i = anIntArray1448[i]; --j >= 0; i += Class38_Sub2_Sub2.anInt1309) {
                        method396(Class38_Sub2_Sub2.anIntArray1308, i, 0, 0, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                        j1 += j3;
                        l += j2;
                        i2 += k3;
                        k1 += k2;
                    }

                    while (--k >= 0) {
                        method396(Class38_Sub2_Sub2.anIntArray1308, i, 0, 0, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                        j1 += j3;
                        i1 += l2;
                        i2 += k3;
                        l1 += i3;
                        i += Class38_Sub2_Sub2.anInt1309;
                    }
                    return;
                }
                k -= j;
                j -= i;
                for (i = anIntArray1448[i]; --j >= 0; i += Class38_Sub2_Sub2.anInt1309) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, i, 0, 0, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                    j1 += j3;
                    l += j2;
                    i2 += k3;
                    k1 += k2;
                }

                while (--k >= 0) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, i, 0, 0, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                    j1 += j3;
                    i1 += l2;
                    i2 += k3;
                    l1 += i3;
                    i += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            i1 = l <<= 16;
            l1 = k1 <<= 15;
            if (i < 0) {
                i1 -= j3 * i;
                l -= j2 * i;
                l1 -= k3 * i;
                k1 -= k2 * i;
                i = 0;
            }
            j1 <<= 16;
            i2 <<= 15;
            if (k < 0) {
                j1 -= l2 * k;
                i2 -= i3 * k;
                k = 0;
            }
            if (i != k && j3 < j2 || i == k && l2 > j2) {
                j -= k;
                k -= i;
                for (i = anIntArray1448[i]; --k >= 0; i += Class38_Sub2_Sub2.anInt1309) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, i, 0, 0, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                    i1 += j3;
                    l += j2;
                    l1 += k3;
                    k1 += k2;
                }

                while (--j >= 0) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, i, 0, 0, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                    j1 += l2;
                    l += j2;
                    i2 += i3;
                    k1 += k2;
                    i += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            j -= k;
            k -= i;
            for (i = anIntArray1448[i]; --k >= 0; i += Class38_Sub2_Sub2.anInt1309) {
                method396(Class38_Sub2_Sub2.anIntArray1308, i, 0, 0, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                i1 += j3;
                l += j2;
                l1 += k3;
                k1 += k2;
            }

            while (--j >= 0) {
                method396(Class38_Sub2_Sub2.anIntArray1308, i, 0, 0, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                j1 += l2;
                l += j2;
                i2 += i3;
                k1 += k2;
                i += Class38_Sub2_Sub2.anInt1309;
            }
            return;
        }
        if (j <= k) {
            if (j >= Class38_Sub2_Sub2.anInt1312)
                return;
            if (k > Class38_Sub2_Sub2.anInt1312)
                k = Class38_Sub2_Sub2.anInt1312;
            if (i > Class38_Sub2_Sub2.anInt1312)
                i = Class38_Sub2_Sub2.anInt1312;
            if (k < i) {
                l = i1 <<= 16;
                k1 = l1 <<= 15;
                if (j < 0) {
                    l -= j2 * j;
                    i1 -= l2 * j;
                    k1 -= k2 * j;
                    l1 -= i3 * j;
                    j = 0;
                }
                j1 <<= 16;
                i2 <<= 15;
                if (k < 0) {
                    j1 -= j3 * k;
                    i2 -= k3 * k;
                    k = 0;
                }
                if (j != k && j2 < l2 || j == k && j2 > j3) {
                    i -= k;
                    k -= j;
                    for (j = anIntArray1448[j]; --k >= 0; j += Class38_Sub2_Sub2.anInt1309) {
                        method396(Class38_Sub2_Sub2.anIntArray1308, j, 0, 0, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                        l += j2;
                        i1 += l2;
                        k1 += k2;
                        l1 += i3;
                    }

                    while (--i >= 0) {
                        method396(Class38_Sub2_Sub2.anIntArray1308, j, 0, 0, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                        l += j2;
                        j1 += j3;
                        k1 += k2;
                        i2 += k3;
                        j += Class38_Sub2_Sub2.anInt1309;
                    }
                    return;
                }
                i -= k;
                k -= j;
                for (j = anIntArray1448[j]; --k >= 0; j += Class38_Sub2_Sub2.anInt1309) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, j, 0, 0, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                    l += j2;
                    i1 += l2;
                    k1 += k2;
                    l1 += i3;
                }

                while (--i >= 0) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, j, 0, 0, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                    l += j2;
                    j1 += j3;
                    k1 += k2;
                    i2 += k3;
                    j += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            j1 = i1 <<= 16;
            i2 = l1 <<= 15;
            if (j < 0) {
                j1 -= j2 * j;
                i1 -= l2 * j;
                i2 -= k2 * j;
                l1 -= i3 * j;
                j = 0;
            }
            l <<= 16;
            k1 <<= 15;
            if (i < 0) {
                l -= j3 * i;
                k1 -= k3 * i;
                i = 0;
            }
            if (j2 < l2) {
                k -= i;
                i -= j;
                for (j = anIntArray1448[j]; --i >= 0; j += Class38_Sub2_Sub2.anInt1309) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, j, 0, 0, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                    j1 += j2;
                    i1 += l2;
                    i2 += k2;
                    l1 += i3;
                }

                while (--k >= 0) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, j, 0, 0, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                    l += j3;
                    i1 += l2;
                    k1 += k3;
                    l1 += i3;
                    j += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            k -= i;
            i -= j;
            for (j = anIntArray1448[j]; --i >= 0; j += Class38_Sub2_Sub2.anInt1309) {
                method396(Class38_Sub2_Sub2.anIntArray1308, j, 0, 0, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                j1 += j2;
                i1 += l2;
                i2 += k2;
                l1 += i3;
            }

            while (--k >= 0) {
                method396(Class38_Sub2_Sub2.anIntArray1308, j, 0, 0, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                l += j3;
                i1 += l2;
                k1 += k3;
                l1 += i3;
                j += Class38_Sub2_Sub2.anInt1309;
            }
            return;
        }
        if (k >= Class38_Sub2_Sub2.anInt1312)
            return;
        if (i > Class38_Sub2_Sub2.anInt1312)
            i = Class38_Sub2_Sub2.anInt1312;
        if (j > Class38_Sub2_Sub2.anInt1312)
            j = Class38_Sub2_Sub2.anInt1312;
        if (i < j) {
            i1 = j1 <<= 16;
            l1 = i2 <<= 15;
            if (k < 0) {
                i1 -= l2 * k;
                j1 -= j3 * k;
                l1 -= i3 * k;
                i2 -= k3 * k;
                k = 0;
            }
            l <<= 16;
            k1 <<= 15;
            if (i < 0) {
                l -= j2 * i;
                k1 -= k2 * i;
                i = 0;
            }
            if (l2 < j3) {
                j -= i;
                i -= k;
                for (k = anIntArray1448[k]; --i >= 0; k += Class38_Sub2_Sub2.anInt1309) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, k, 0, 0, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                    i1 += l2;
                    j1 += j3;
                    l1 += i3;
                    i2 += k3;
                }

                while (--j >= 0) {
                    method396(Class38_Sub2_Sub2.anIntArray1308, k, 0, 0, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                    i1 += l2;
                    l += j2;
                    l1 += i3;
                    k1 += k2;
                    k += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            j -= i;
            i -= k;
            for (k = anIntArray1448[k]; --i >= 0; k += Class38_Sub2_Sub2.anInt1309) {
                method396(Class38_Sub2_Sub2.anIntArray1308, k, 0, 0, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                i1 += l2;
                j1 += j3;
                l1 += i3;
                i2 += k3;
            }

            while (--j >= 0) {
                method396(Class38_Sub2_Sub2.anIntArray1308, k, 0, 0, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                i1 += l2;
                l += j2;
                l1 += i3;
                k1 += k2;
                k += Class38_Sub2_Sub2.anInt1309;
            }
            return;
        }
        l = j1 <<= 16;
        k1 = i2 <<= 15;
        if (k < 0) {
            l -= l2 * k;
            j1 -= j3 * k;
            k1 -= i3 * k;
            i2 -= k3 * k;
            k = 0;
        }
        i1 <<= 16;
        l1 <<= 15;
        if (j < 0) {
            i1 -= j2 * j;
            l1 -= k2 * j;
            j = 0;
        }
        if (l2 < j3) {
            i -= j;
            j -= k;
            for (k = anIntArray1448[k]; --j >= 0; k += Class38_Sub2_Sub2.anInt1309) {
                method396(Class38_Sub2_Sub2.anIntArray1308, k, 0, 0, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                l += l2;
                j1 += j3;
                k1 += i3;
                i2 += k3;
            }

            while (--i >= 0) {
                method396(Class38_Sub2_Sub2.anIntArray1308, k, 0, 0, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                i1 += j2;
                j1 += j3;
                l1 += k2;
                i2 += k3;
                k += Class38_Sub2_Sub2.anInt1309;
            }
            return;
        }
        i -= j;
        j -= k;
        for (k = anIntArray1448[k]; --j >= 0; k += Class38_Sub2_Sub2.anInt1309) {
            method396(Class38_Sub2_Sub2.anIntArray1308, k, 0, 0, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
            l += l2;
            j1 += j3;
            k1 += i3;
            i2 += k3;
        }

        while (--i >= 0) {
            method396(Class38_Sub2_Sub2.anIntArray1308, k, 0, 0, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
            i1 += j2;
            j1 += j3;
            l1 += k2;
            i2 += k3;
            k += Class38_Sub2_Sub2.anInt1309;
        }
    }

    public static void method396(int[] ai, int i, int j, int k, int l, int i1, int j1, int k1) {
        if (aBoolean1440) {
            int l1;
            if (aBoolean1438) {
                if (i1 - l > 3)
                    l1 = (k1 - j1) / (i1 - l);
                else
                    l1 = 0;
                if (i1 > Class38_Sub2_Sub2.anInt1315)
                    i1 = Class38_Sub2_Sub2.anInt1315;
                if (l < 0) {
                    j1 -= l * l1;
                    l = 0;
                }
                if (l >= i1)
                    return;
                i += l;
                k = i1 - l >> 2;
                l1 <<= 2;
            } else {
                if (l >= i1)
                    return;
                i += l;
                k = i1 - l >> 2;
                if (k > 0)
                    l1 = (k1 - j1) * anIntArray1444[k] >> 15;
                else
                    l1 = 0;
            }
            if (anInt1441 == 0) {
                while (--k >= 0) {
                    j = anIntArray1458[j1 >> 8];
                    j1 += l1;
                    ai[i++] = j;
                    ai[i++] = j;
                    ai[i++] = j;
                    ai[i++] = j;
                }
                k = i1 - l & 3;
                if (k > 0) {
                    j = anIntArray1458[j1 >> 8];
                    do
                        ai[i++] = j;
                    while (--k > 0);
                    return;
                }
            } else {
                int j2 = anInt1441;
                int l2 = 256 - anInt1441;
                while (--k >= 0) {
                    j = anIntArray1458[j1 >> 8];
                    j1 += l1;
                    j = ((j & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((j & 0xff00) * l2 >> 8 & 0xff00);
                    ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                    ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                    ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                    ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                }
                k = i1 - l & 3;
                if (k > 0) {
                    j = anIntArray1458[j1 >> 8];
                    j = ((j & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((j & 0xff00) * l2 >> 8 & 0xff00);
                    do
                        ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                    while (--k > 0);
                }
            }
            return;
        }
        if (l >= i1)
            return;
        int i2 = (k1 - j1) / (i1 - l);
        if (aBoolean1438) {
            if (i1 > Class38_Sub2_Sub2.anInt1315)
                i1 = Class38_Sub2_Sub2.anInt1315;
            if (l < 0) {
                j1 -= l * i2;
                l = 0;
            }
            if (l >= i1)
                return;
        }
        i += l;
        k = i1 - l;
        if (anInt1441 == 0) {
            do {
                ai[i++] = anIntArray1458[j1 >> 8];
                j1 += i2;
            } while (--k > 0);
            return;
        }
        int k2 = anInt1441;
        int i3 = 256 - anInt1441;
        do {
            j = anIntArray1458[j1 >> 8];
            j1 += i2;
            j = ((j & 0xff00ff) * i3 >> 8 & 0xff00ff) + ((j & 0xff00) * i3 >> 8 & 0xff00);
            ai[i++] = j + ((ai[i] & 0xff00ff) * k2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * k2 >> 8 & 0xff00);
        } while (--k > 0);
    }

    public static void method397(int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = 0;
        if (j != i)
            l1 = (i1 - l << 16) / (j - i);
        int i2 = 0;
        if (k != j)
            i2 = (j1 - i1 << 16) / (k - j);
        int j2 = 0;
        if (k != i)
            j2 = (l - j1 << 16) / (i - k);
        if (i <= j && i <= k) {
            if (i >= Class38_Sub2_Sub2.anInt1312)
                return;
            if (j > Class38_Sub2_Sub2.anInt1312)
                j = Class38_Sub2_Sub2.anInt1312;
            if (k > Class38_Sub2_Sub2.anInt1312)
                k = Class38_Sub2_Sub2.anInt1312;
            if (j < k) {
                j1 = l <<= 16;
                if (i < 0) {
                    j1 -= j2 * i;
                    l -= l1 * i;
                    i = 0;
                }
                i1 <<= 16;
                if (j < 0) {
                    i1 -= i2 * j;
                    j = 0;
                }
                if (i != j && j2 < l1 || i == j && j2 > i2) {
                    k -= j;
                    j -= i;
                    for (i = anIntArray1448[i]; --j >= 0; i += Class38_Sub2_Sub2.anInt1309) {
                        method398(Class38_Sub2_Sub2.anIntArray1308, i, k1, 0, j1 >> 16, l >> 16);
                        j1 += j2;
                        l += l1;
                    }

                    while (--k >= 0) {
                        method398(Class38_Sub2_Sub2.anIntArray1308, i, k1, 0, j1 >> 16, i1 >> 16);
                        j1 += j2;
                        i1 += i2;
                        i += Class38_Sub2_Sub2.anInt1309;
                    }
                    return;
                }
                k -= j;
                j -= i;
                for (i = anIntArray1448[i]; --j >= 0; i += Class38_Sub2_Sub2.anInt1309) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, i, k1, 0, l >> 16, j1 >> 16);
                    j1 += j2;
                    l += l1;
                }

                while (--k >= 0) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, i, k1, 0, i1 >> 16, j1 >> 16);
                    j1 += j2;
                    i1 += i2;
                    i += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            i1 = l <<= 16;
            if (i < 0) {
                i1 -= j2 * i;
                l -= l1 * i;
                i = 0;
            }
            j1 <<= 16;
            if (k < 0) {
                j1 -= i2 * k;
                k = 0;
            }
            if (i != k && j2 < l1 || i == k && i2 > l1) {
                j -= k;
                k -= i;
                for (i = anIntArray1448[i]; --k >= 0; i += Class38_Sub2_Sub2.anInt1309) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, i, k1, 0, i1 >> 16, l >> 16);
                    i1 += j2;
                    l += l1;
                }

                while (--j >= 0) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, i, k1, 0, j1 >> 16, l >> 16);
                    j1 += i2;
                    l += l1;
                    i += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            j -= k;
            k -= i;
            for (i = anIntArray1448[i]; --k >= 0; i += Class38_Sub2_Sub2.anInt1309) {
                method398(Class38_Sub2_Sub2.anIntArray1308, i, k1, 0, l >> 16, i1 >> 16);
                i1 += j2;
                l += l1;
            }

            while (--j >= 0) {
                method398(Class38_Sub2_Sub2.anIntArray1308, i, k1, 0, l >> 16, j1 >> 16);
                j1 += i2;
                l += l1;
                i += Class38_Sub2_Sub2.anInt1309;
            }
            return;
        }
        if (j <= k) {
            if (j >= Class38_Sub2_Sub2.anInt1312)
                return;
            if (k > Class38_Sub2_Sub2.anInt1312)
                k = Class38_Sub2_Sub2.anInt1312;
            if (i > Class38_Sub2_Sub2.anInt1312)
                i = Class38_Sub2_Sub2.anInt1312;
            if (k < i) {
                l = i1 <<= 16;
                if (j < 0) {
                    l -= l1 * j;
                    i1 -= i2 * j;
                    j = 0;
                }
                j1 <<= 16;
                if (k < 0) {
                    j1 -= j2 * k;
                    k = 0;
                }
                if (j != k && l1 < i2 || j == k && l1 > j2) {
                    i -= k;
                    k -= j;
                    for (j = anIntArray1448[j]; --k >= 0; j += Class38_Sub2_Sub2.anInt1309) {
                        method398(Class38_Sub2_Sub2.anIntArray1308, j, k1, 0, l >> 16, i1 >> 16);
                        l += l1;
                        i1 += i2;
                    }

                    while (--i >= 0) {
                        method398(Class38_Sub2_Sub2.anIntArray1308, j, k1, 0, l >> 16, j1 >> 16);
                        l += l1;
                        j1 += j2;
                        j += Class38_Sub2_Sub2.anInt1309;
                    }
                    return;
                }
                i -= k;
                k -= j;
                for (j = anIntArray1448[j]; --k >= 0; j += Class38_Sub2_Sub2.anInt1309) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, j, k1, 0, i1 >> 16, l >> 16);
                    l += l1;
                    i1 += i2;
                }

                while (--i >= 0) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, j, k1, 0, j1 >> 16, l >> 16);
                    l += l1;
                    j1 += j2;
                    j += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            j1 = i1 <<= 16;
            if (j < 0) {
                j1 -= l1 * j;
                i1 -= i2 * j;
                j = 0;
            }
            l <<= 16;
            if (i < 0) {
                l -= j2 * i;
                i = 0;
            }
            if (l1 < i2) {
                k -= i;
                i -= j;
                for (j = anIntArray1448[j]; --i >= 0; j += Class38_Sub2_Sub2.anInt1309) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, j, k1, 0, j1 >> 16, i1 >> 16);
                    j1 += l1;
                    i1 += i2;
                }

                while (--k >= 0) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, j, k1, 0, l >> 16, i1 >> 16);
                    l += j2;
                    i1 += i2;
                    j += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            k -= i;
            i -= j;
            for (j = anIntArray1448[j]; --i >= 0; j += Class38_Sub2_Sub2.anInt1309) {
                method398(Class38_Sub2_Sub2.anIntArray1308, j, k1, 0, i1 >> 16, j1 >> 16);
                j1 += l1;
                i1 += i2;
            }

            while (--k >= 0) {
                method398(Class38_Sub2_Sub2.anIntArray1308, j, k1, 0, i1 >> 16, l >> 16);
                l += j2;
                i1 += i2;
                j += Class38_Sub2_Sub2.anInt1309;
            }
            return;
        }
        if (k >= Class38_Sub2_Sub2.anInt1312)
            return;
        if (i > Class38_Sub2_Sub2.anInt1312)
            i = Class38_Sub2_Sub2.anInt1312;
        if (j > Class38_Sub2_Sub2.anInt1312)
            j = Class38_Sub2_Sub2.anInt1312;
        if (i < j) {
            i1 = j1 <<= 16;
            if (k < 0) {
                i1 -= i2 * k;
                j1 -= j2 * k;
                k = 0;
            }
            l <<= 16;
            if (i < 0) {
                l -= l1 * i;
                i = 0;
            }
            if (i2 < j2) {
                j -= i;
                i -= k;
                for (k = anIntArray1448[k]; --i >= 0; k += Class38_Sub2_Sub2.anInt1309) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, k, k1, 0, i1 >> 16, j1 >> 16);
                    i1 += i2;
                    j1 += j2;
                }

                while (--j >= 0) {
                    method398(Class38_Sub2_Sub2.anIntArray1308, k, k1, 0, i1 >> 16, l >> 16);
                    i1 += i2;
                    l += l1;
                    k += Class38_Sub2_Sub2.anInt1309;
                }
                return;
            }
            j -= i;
            i -= k;
            for (k = anIntArray1448[k]; --i >= 0; k += Class38_Sub2_Sub2.anInt1309) {
                method398(Class38_Sub2_Sub2.anIntArray1308, k, k1, 0, j1 >> 16, i1 >> 16);
                i1 += i2;
                j1 += j2;
            }

            while (--j >= 0) {
                method398(Class38_Sub2_Sub2.anIntArray1308, k, k1, 0, l >> 16, i1 >> 16);
                i1 += i2;
                l += l1;
                k += Class38_Sub2_Sub2.anInt1309;
            }
            return;
        }
        l = j1 <<= 16;
        if (k < 0) {
            l -= i2 * k;
            j1 -= j2 * k;
            k = 0;
        }
        i1 <<= 16;
        if (j < 0) {
            i1 -= l1 * j;
            j = 0;
        }
        if (i2 < j2) {
            i -= j;
            j -= k;
            for (k = anIntArray1448[k]; --j >= 0; k += Class38_Sub2_Sub2.anInt1309) {
                method398(Class38_Sub2_Sub2.anIntArray1308, k, k1, 0, l >> 16, j1 >> 16);
                l += i2;
                j1 += j2;
            }

            while (--i >= 0) {
                method398(Class38_Sub2_Sub2.anIntArray1308, k, k1, 0, i1 >> 16, j1 >> 16);
                i1 += l1;
                j1 += j2;
                k += Class38_Sub2_Sub2.anInt1309;
            }
            return;
        }
        i -= j;
        j -= k;
        for (k = anIntArray1448[k]; --j >= 0; k += Class38_Sub2_Sub2.anInt1309) {
            method398(Class38_Sub2_Sub2.anIntArray1308, k, k1, 0, j1 >> 16, l >> 16);
            l += i2;
            j1 += j2;
        }

        while (--i >= 0) {
            method398(Class38_Sub2_Sub2.anIntArray1308, k, k1, 0, j1 >> 16, i1 >> 16);
            i1 += l1;
            j1 += j2;
            k += Class38_Sub2_Sub2.anInt1309;
        }
    }

    public static void method398(int[] ai, int i, int j, int k, int l, int i1) {
        if (aBoolean1438) {
            if (i1 > Class38_Sub2_Sub2.anInt1315)
                i1 = Class38_Sub2_Sub2.anInt1315;
            if (l < 0)
                l = 0;
        }
        if (l >= i1)
            return;
        i += l;
        k = i1 - l >> 2;
        if (anInt1441 == 0) {
            while (--k >= 0) {
                ai[i++] = j;
                ai[i++] = j;
                ai[i++] = j;
                ai[i++] = j;
            }
            for (k = i1 - l & 3; --k >= 0; )
                ai[i++] = j;

            return;
        }
        int j1 = anInt1441;
        int k1 = 256 - anInt1441;
        j = ((j & 0xff00ff) * k1 >> 8 & 0xff00ff) + ((j & 0xff00) * k1 >> 8 & 0xff00);
        while (--k >= 0) {
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
        }
        for (k = i1 - l & 3; --k >= 0; )
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);

    }

    public static void method399(int i, int j, int k, int l, int i1, int j1, int k1, int l1,
                                 int i2, int j2, int k2, int l2, int i3, int j3, int k3,
                                 int l3, int i4, int j4, int k4) {
        int[] ai = method392(k4);
        aBoolean1439 = !aBooleanArray1451[k4];
        k2 = j2 - k2;
        j3 = i3 - j3;
        i4 = l3 - i4;
        l2 -= j2;
        k3 -= i3;
        j4 -= l3;
        int l4 = l2 * i3 - k3 * j2 << 14;
        int i5 = k3 * l3 - j4 * i3 << 8;
        int j5 = j4 * j2 - l2 * l3 << 5;
        int k5 = k2 * i3 - j3 * j2 << 14;
        int l5 = j3 * l3 - i4 * i3 << 8;
        int i6 = i4 * j2 - k2 * l3 << 5;
        int j6 = j3 * l2 - k2 * k3 << 14;
        int k6 = i4 * k3 - j3 * j4 << 8;
        int l6 = k2 * j4 - i4 * l2 << 5;
        int i7 = 0;
        int j7 = 0;
        if (j != i) {
            i7 = (i1 - l << 16) / (j - i);
            j7 = (l1 - k1 << 16) / (j - i);
        }
        int k7 = 0;
        int l7 = 0;
        if (k != j) {
            k7 = (j1 - i1 << 16) / (k - j);
            l7 = (i2 - l1 << 16) / (k - j);
        }
        int i8 = 0;
        int j8 = 0;
        if (k != i) {
            i8 = (l - j1 << 16) / (i - k);
            j8 = (k1 - i2 << 16) / (i - k);
        }
        if (i <= j && i <= k) {
            if (i >= Class38_Sub2_Sub2.anInt1312)
                return;
            if (j > Class38_Sub2_Sub2.anInt1312)
                j = Class38_Sub2_Sub2.anInt1312;
            if (k > Class38_Sub2_Sub2.anInt1312)
                k = Class38_Sub2_Sub2.anInt1312;
            if (j < k) {
                j1 = l <<= 16;
                i2 = k1 <<= 16;
                if (i < 0) {
                    j1 -= i8 * i;
                    l -= i7 * i;
                    i2 -= j8 * i;
                    k1 -= j7 * i;
                    i = 0;
                }
                i1 <<= 16;
                l1 <<= 16;
                if (j < 0) {
                    i1 -= k7 * j;
                    l1 -= l7 * j;
                    j = 0;
                }
                int k8 = i - anInt1443;
                l4 += j5 * k8;
                k5 += i6 * k8;
                j6 += l6 * k8;
                if (i != j && i8 < i7 || i == j && i8 > k7) {
                    k -= j;
                    j -= i;
                    i = anIntArray1448[i];
                    while (--j >= 0) {
                        method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8,
                                l4, k5, j6, i5, l5, k6);
                        j1 += i8;
                        l += i7;
                        i2 += j8;
                        k1 += j7;
                        i += Class38_Sub2_Sub2.anInt1309;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    while (--k >= 0) {
                        method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, i, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8,
                                l4, k5, j6, i5, l5, k6);
                        j1 += i8;
                        i1 += k7;
                        i2 += j8;
                        l1 += l7;
                        i += Class38_Sub2_Sub2.anInt1309;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    return;
                }
                k -= j;
                j -= i;
                i = anIntArray1448[i];
                while (--j >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    j1 += i8;
                    l += i7;
                    i2 += j8;
                    k1 += j7;
                    i += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--k >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, i, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    j1 += i8;
                    i1 += k7;
                    i2 += j8;
                    l1 += l7;
                    i += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            i1 = l <<= 16;
            l1 = k1 <<= 16;
            if (i < 0) {
                i1 -= i8 * i;
                l -= i7 * i;
                l1 -= j8 * i;
                k1 -= j7 * i;
                i = 0;
            }
            j1 <<= 16;
            i2 <<= 16;
            if (k < 0) {
                j1 -= k7 * k;
                i2 -= l7 * k;
                k = 0;
            }
            int l8 = i - anInt1443;
            l4 += j5 * l8;
            k5 += i6 * l8;
            j6 += l6 * l8;
            if (i != k && i8 < i7 || i == k && k7 > i7) {
                j -= k;
                k -= i;
                i = anIntArray1448[i];
                while (--k >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, i, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    i1 += i8;
                    l += i7;
                    l1 += j8;
                    k1 += j7;
                    i += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--j >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    j1 += k7;
                    l += i7;
                    i2 += l7;
                    k1 += j7;
                    i += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            j -= k;
            k -= i;
            i = anIntArray1448[i];
            while (--k >= 0) {
                method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, i, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5,
                        j6, i5, l5, k6);
                i1 += i8;
                l += i7;
                l1 += j8;
                k1 += j7;
                i += Class38_Sub2_Sub2.anInt1309;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--j >= 0) {
                method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5,
                        j6, i5, l5, k6);
                j1 += k7;
                l += i7;
                i2 += l7;
                k1 += j7;
                i += Class38_Sub2_Sub2.anInt1309;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        if (j <= k) {
            if (j >= Class38_Sub2_Sub2.anInt1312)
                return;
            if (k > Class38_Sub2_Sub2.anInt1312)
                k = Class38_Sub2_Sub2.anInt1312;
            if (i > Class38_Sub2_Sub2.anInt1312)
                i = Class38_Sub2_Sub2.anInt1312;
            if (k < i) {
                l = i1 <<= 16;
                k1 = l1 <<= 16;
                if (j < 0) {
                    l -= i7 * j;
                    i1 -= k7 * j;
                    k1 -= j7 * j;
                    l1 -= l7 * j;
                    j = 0;
                }
                j1 <<= 16;
                i2 <<= 16;
                if (k < 0) {
                    j1 -= i8 * k;
                    i2 -= j8 * k;
                    k = 0;
                }
                int i9 = j - anInt1443;
                l4 += j5 * i9;
                k5 += i6 * i9;
                j6 += l6 * i9;
                if (j != k && i7 < k7 || j == k && i7 > i8) {
                    i -= k;
                    k -= j;
                    j = anIntArray1448[j];
                    while (--k >= 0) {
                        method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8,
                                l4, k5, j6, i5, l5, k6);
                        l += i7;
                        i1 += k7;
                        k1 += j7;
                        l1 += l7;
                        j += Class38_Sub2_Sub2.anInt1309;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    while (--i >= 0) {
                        method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, j, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8,
                                l4, k5, j6, i5, l5, k6);
                        l += i7;
                        j1 += i8;
                        k1 += j7;
                        i2 += j8;
                        j += Class38_Sub2_Sub2.anInt1309;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    return;
                }
                i -= k;
                k -= j;
                j = anIntArray1448[j];
                while (--k >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    l += i7;
                    i1 += k7;
                    k1 += j7;
                    l1 += l7;
                    j += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--i >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, j, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    l += i7;
                    j1 += i8;
                    k1 += j7;
                    i2 += j8;
                    j += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            j1 = i1 <<= 16;
            i2 = l1 <<= 16;
            if (j < 0) {
                j1 -= i7 * j;
                i1 -= k7 * j;
                i2 -= j7 * j;
                l1 -= l7 * j;
                j = 0;
            }
            l <<= 16;
            k1 <<= 16;
            if (i < 0) {
                l -= i8 * i;
                k1 -= j8 * i;
                i = 0;
            }
            int j9 = j - anInt1443;
            l4 += j5 * j9;
            k5 += i6 * j9;
            j6 += l6 * j9;
            if (i7 < k7) {
                k -= i;
                i -= j;
                j = anIntArray1448[j];
                while (--i >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, j, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    j1 += i7;
                    i1 += k7;
                    i2 += j7;
                    l1 += l7;
                    j += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--k >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    l += i8;
                    i1 += k7;
                    k1 += j8;
                    l1 += l7;
                    j += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            k -= i;
            i -= j;
            j = anIntArray1448[j];
            while (--i >= 0) {
                method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, j, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5,
                        j6, i5, l5, k6);
                j1 += i7;
                i1 += k7;
                i2 += j7;
                l1 += l7;
                j += Class38_Sub2_Sub2.anInt1309;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--k >= 0) {
                method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5,
                        j6, i5, l5, k6);
                l += i8;
                i1 += k7;
                k1 += j8;
                l1 += l7;
                j += Class38_Sub2_Sub2.anInt1309;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        if (k >= Class38_Sub2_Sub2.anInt1312)
            return;
        if (i > Class38_Sub2_Sub2.anInt1312)
            i = Class38_Sub2_Sub2.anInt1312;
        if (j > Class38_Sub2_Sub2.anInt1312)
            j = Class38_Sub2_Sub2.anInt1312;
        if (i < j) {
            i1 = j1 <<= 16;
            l1 = i2 <<= 16;
            if (k < 0) {
                i1 -= k7 * k;
                j1 -= i8 * k;
                l1 -= l7 * k;
                i2 -= j8 * k;
                k = 0;
            }
            l <<= 16;
            k1 <<= 16;
            if (i < 0) {
                l -= i7 * i;
                k1 -= j7 * i;
                i = 0;
            }
            int k9 = k - anInt1443;
            l4 += j5 * k9;
            k5 += i6 * k9;
            j6 += l6 * k9;
            if (k7 < i8) {
                j -= i;
                i -= k;
                k = anIntArray1448[k];
                while (--i >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    i1 += k7;
                    j1 += i8;
                    l1 += l7;
                    i2 += j8;
                    k += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--j >= 0) {
                    method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, k, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4,
                            k5, j6, i5, l5, k6);
                    i1 += k7;
                    l += i7;
                    l1 += l7;
                    k1 += j7;
                    k += Class38_Sub2_Sub2.anInt1309;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            j -= i;
            i -= k;
            k = anIntArray1448[k];
            while (--i >= 0) {
                method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5,
                        j6, i5, l5, k6);
                i1 += k7;
                j1 += i8;
                l1 += l7;
                i2 += j8;
                k += Class38_Sub2_Sub2.anInt1309;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--j >= 0) {
                method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, k, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5,
                        j6, i5, l5, k6);
                i1 += k7;
                l += i7;
                l1 += l7;
                k1 += j7;
                k += Class38_Sub2_Sub2.anInt1309;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        l = j1 <<= 16;
        k1 = i2 <<= 16;
        if (k < 0) {
            l -= k7 * k;
            j1 -= i8 * k;
            k1 -= l7 * k;
            i2 -= j8 * k;
            k = 0;
        }
        i1 <<= 16;
        l1 <<= 16;
        if (j < 0) {
            i1 -= i7 * j;
            l1 -= j7 * j;
            j = 0;
        }
        int l9 = k - anInt1443;
        l4 += j5 * l9;
        k5 += i6 * l9;
        j6 += l6 * l9;
        if (k7 < i8) {
            i -= j;
            j -= k;
            k = anIntArray1448[k];
            while (--j >= 0) {
                method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, k, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5,
                        j6, i5, l5, k6);
                l += k7;
                j1 += i8;
                k1 += l7;
                i2 += j8;
                k += Class38_Sub2_Sub2.anInt1309;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--i >= 0) {
                method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5,
                        j6, i5, l5, k6);
                i1 += i7;
                j1 += i8;
                l1 += j7;
                i2 += j8;
                k += Class38_Sub2_Sub2.anInt1309;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        i -= j;
        j -= k;
        k = anIntArray1448[k];
        while (--j >= 0) {
            method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, k, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6,
                    i5, l5, k6);
            l += k7;
            j1 += i8;
            k1 += l7;
            i2 += j8;
            k += Class38_Sub2_Sub2.anInt1309;
            l4 += j5;
            k5 += i6;
            j6 += l6;
        }
        while (--i >= 0) {
            method400(Class38_Sub2_Sub2.anIntArray1308, ai, 0, 0, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6,
                    i5, l5, k6);
            i1 += i7;
            j1 += i8;
            l1 += j7;
            i2 += j8;
            k += Class38_Sub2_Sub2.anInt1309;
            l4 += j5;
            k5 += i6;
            j6 += l6;
        }
    }

    public static void method400(int[] ai, int[] ai1, int i, int j, int k, int l, int i1, int j1,
                                 int k1, int l1, int i2, int j2, int k2, int l2, int i3) {
        if (l >= i1)
            return;
        int j3;
        int k3;
        if (aBoolean1438) {
            j3 = (k1 - j1) / (i1 - l);
            if (i1 > Class38_Sub2_Sub2.anInt1315)
                i1 = Class38_Sub2_Sub2.anInt1315;
            if (l < 0) {
                j1 -= l * j3;
                l = 0;
            }
            if (l >= i1)
                return;
            k3 = i1 - l >> 3;
            j3 <<= 12;
            j1 <<= 9;
        } else {
            if (i1 - l > 7) {
                k3 = i1 - l >> 3;
                j3 = (k1 - j1) * anIntArray1444[k3] >> 6;
            } else {
                k3 = 0;
                j3 = 0;
            }
            j1 <<= 9;
        }
        k += l;
        if (aBoolean1437) {
            int i4 = 0;
            int k4 = 0;
            int k6 = l - anInt1442;
            l1 += (k2 >> 3) * k6;
            i2 += (l2 >> 3) * k6;
            j2 += (i3 >> 3) * k6;
            int i5 = j2 >> 12;
            if (i5 != 0) {
                i = l1 / i5;
                j = i2 / i5;
                if (i < 0)
                    i = 0;
                else if (i > 4032)
                    i = 4032;
            }
            l1 += k2;
            i2 += l2;
            j2 += i3;
            i5 = j2 >> 12;
            if (i5 != 0) {
                i4 = l1 / i5;
                k4 = i2 / i5;
                if (i4 < 7)
                    i4 = 7;
                else if (i4 > 4032)
                    i4 = 4032;
            }
            int i7 = i4 - i >> 3;
            int k7 = k4 - j >> 3;
            i += (j1 & 0x600000) >> 3;
            int i8 = j1 >> 23;
            if (aBoolean1439) {
                while (k3-- > 0) {
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i = i4;
                    j = k4;
                    l1 += k2;
                    i2 += l2;
                    j2 += i3;
                    int j5 = j2 >> 12;
                    if (j5 != 0) {
                        i4 = l1 / j5;
                        k4 = i2 / j5;
                        if (i4 < 7)
                            i4 = 7;
                        else if (i4 > 4032)
                            i4 = 4032;
                    }
                    i7 = i4 - i >> 3;
                    k7 = k4 - j >> 3;
                    j1 += j3;
                    i += (j1 & 0x600000) >> 3;
                    i8 = j1 >> 23;
                }
                for (k3 = i1 - l & 7; k3-- > 0; ) {
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                }

                return;
            }
            while (k3-- > 0) {
                int k8;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = k8;
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = k8;
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = k8;
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = k8;
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = k8;
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = k8;
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = k8;
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = k8;
                k++;
                i = i4;
                j = k4;
                l1 += k2;
                i2 += l2;
                j2 += i3;
                int k5 = j2 >> 12;
                if (k5 != 0) {
                    i4 = l1 / k5;
                    k4 = i2 / k5;
                    if (i4 < 7)
                        i4 = 7;
                    else if (i4 > 4032)
                        i4 = 4032;
                }
                i7 = i4 - i >> 3;
                k7 = k4 - j >> 3;
                j1 += j3;
                i += (j1 & 0x600000) >> 3;
                i8 = j1 >> 23;
            }
            for (k3 = i1 - l & 7; k3-- > 0; ) {
                int l8;
                if ((l8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
                    ai[k] = l8;
                k++;
                i += i7;
                j += k7;
            }

            return;
        }
        int j4 = 0;
        int l4 = 0;
        int l6 = l - anInt1442;
        l1 += (k2 >> 3) * l6;
        i2 += (l2 >> 3) * l6;
        j2 += (i3 >> 3) * l6;
        int l5 = j2 >> 14;
        if (l5 != 0) {
            i = l1 / l5;
            j = i2 / l5;
            if (i < 0)
                i = 0;
            else if (i > 16256)
                i = 16256;
        }
        l1 += k2;
        i2 += l2;
        j2 += i3;
        l5 = j2 >> 14;
        if (l5 != 0) {
            j4 = l1 / l5;
            l4 = i2 / l5;
            if (j4 < 7)
                j4 = 7;
            else if (j4 > 16256)
                j4 = 16256;
        }
        int j7 = j4 - i >> 3;
        int l7 = l4 - j >> 3;
        i += j1 & 0x600000;
        int j8 = j1 >> 23;
        if (aBoolean1439) {
            while (k3-- > 0) {
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i = j4;
                j = l4;
                l1 += k2;
                i2 += l2;
                j2 += i3;
                int i6 = j2 >> 14;
                if (i6 != 0) {
                    j4 = l1 / i6;
                    l4 = i2 / i6;
                    if (j4 < 7)
                        j4 = 7;
                    else if (j4 > 16256)
                        j4 = 16256;
                }
                j7 = j4 - i >> 3;
                l7 = l4 - j >> 3;
                j1 += j3;
                i += j1 & 0x600000;
                j8 = j1 >> 23;
            }
            for (k3 = i1 - l & 7; k3-- > 0; ) {
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
            }

            return;
        }
        while (k3-- > 0) {
            int i9;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = i9;
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = i9;
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = i9;
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = i9;
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = i9;
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = i9;
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = i9;
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = i9;
            k++;
            i = j4;
            j = l4;
            l1 += k2;
            i2 += l2;
            j2 += i3;
            int j6 = j2 >> 14;
            if (j6 != 0) {
                j4 = l1 / j6;
                l4 = i2 / j6;
                if (j4 < 7)
                    j4 = 7;
                else if (j4 > 16256)
                    j4 = 16256;
            }
            j7 = j4 - i >> 3;
            l7 = l4 - j >> 3;
            j1 += j3;
            i += j1 & 0x600000;
            j8 = j1 >> 23;
        }
        for (int l3 = i1 - l & 7; l3-- > 0; ) {
            int j9;
            if ((j9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
                ai[k] = j9;
            k++;
            i += j7;
            j += l7;
        }

    }

    public static boolean aBoolean1432;
    public static int anInt1433 = 787;
    public static boolean aBoolean1434;
    public static int anInt1435 = 473;
    public static boolean aBoolean1436 = true;
    public static boolean aBoolean1437 = true;
    public static boolean aBoolean1438;
    public static boolean aBoolean1439;
    public static boolean aBoolean1440 = true;
    public static int anInt1441;
    public static int anInt1442;
    public static int anInt1443;
    public static int[] anIntArray1444;
    public static int[] anIntArray1445;
    public static int[] anIntArray1446;
    public static int[] anIntArray1447;
    public static int[] anIntArray1448;
    public static int anInt1449;
    public static Class38_Sub2_Sub2_Sub3[] aClass38_Sub2_Sub2_Sub3Array1450 = new Class38_Sub2_Sub2_Sub3[50];
    public static boolean[] aBooleanArray1451 = new boolean[50];
    public static int[] anIntArray1452 = new int[50];
    public static int anInt1453;
    public static int[][] anIntArrayArray1454;
    public static int[][] anIntArrayArray1455 = new int[50][];
    public static int[] anIntArray1456 = new int[50];
    public static int anInt1457;
    public static int[] anIntArray1458 = new int[0x10000];
    public static int[][] anIntArrayArray1459 = new int[50][];

    static {
        anIntArray1444 = new int[512];
        anIntArray1445 = new int[2048];
        anIntArray1446 = new int[2048];
        anIntArray1447 = new int[2048];
        for (int i = 1; i < 512; i++)
            anIntArray1444[i] = 32768 / i;

        for (int j = 1; j < 2048; j++)
            anIntArray1445[j] = 0x10000 / j;

        for (int k = 0; k < 2048; k++) {
            anIntArray1446[k] = (int) (65536D * Math.sin((double) k * 0.0030679614999999999D));
            anIntArray1447[k] = (int) (65536D * Math.cos((double) k * 0.0030679614999999999D));
        }

    }
}
