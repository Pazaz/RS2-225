// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class8 {

    public Class8(int i, int j, int k) {
        aBoolean206 = false;
        aByte208 = 12;
        anInt209 = 27808;
        for (aBoolean210 = true; j >= 0; aBoolean210 = !aBoolean210)
            ;
        anInt211 = 0;
        anInt212 = 0;
        anInt213 = i;
        anInt214 = k;
        anIntArrayArray215 = new int[anInt213][anInt214];
        method196((byte) 74);
    }

    public void method196(byte byte0) {
        for (int i = 0; i < anInt213; i++) {
            for (int j = 0; j < anInt214; j++)
                if (i == 0 || j == 0 || i == anInt213 - 1 || j == anInt214 - 1)
                    anIntArrayArray215[i][j] = 0xffffff;
                else
                    anIntArrayArray215[i][j] = 0;

        }

        if (byte0 != 74) {
            for (int k = 1; k > 0; k++)
                ;
        }
    }

    public void method197(boolean flag, int i, int j, int k, boolean flag1, int l) {
        k -= anInt211;
        if (!flag)
            aBoolean206 = !aBoolean206;
        j -= anInt212;
        if (l == 0) {
            if (i == 0) {
                method200(k, j, 128);
                method200(k - 1, j, 8);
            }
            if (i == 1) {
                method200(k, j, 2);
                method200(k, j + 1, 32);
            }
            if (i == 2) {
                method200(k, j, 8);
                method200(k + 1, j, 128);
            }
            if (i == 3) {
                method200(k, j, 32);
                method200(k, j - 1, 2);
            }
        }
        if (l == 1 || l == 3) {
            if (i == 0) {
                method200(k, j, 1);
                method200(k - 1, j + 1, 16);
            }
            if (i == 1) {
                method200(k, j, 4);
                method200(k + 1, j + 1, 64);
            }
            if (i == 2) {
                method200(k, j, 16);
                method200(k + 1, j - 1, 1);
            }
            if (i == 3) {
                method200(k, j, 64);
                method200(k - 1, j - 1, 4);
            }
        }
        if (l == 2) {
            if (i == 0) {
                method200(k, j, 130);
                method200(k - 1, j, 8);
                method200(k, j + 1, 32);
            }
            if (i == 1) {
                method200(k, j, 10);
                method200(k, j + 1, 32);
                method200(k + 1, j, 128);
            }
            if (i == 2) {
                method200(k, j, 40);
                method200(k + 1, j, 128);
                method200(k, j - 1, 2);
            }
            if (i == 3) {
                method200(k, j, 160);
                method200(k, j - 1, 2);
                method200(k - 1, j, 8);
            }
        }
        if (flag1) {
            if (l == 0) {
                if (i == 0) {
                    method200(k, j, 0x10000);
                    method200(k - 1, j, 4096);
                }
                if (i == 1) {
                    method200(k, j, 1024);
                    method200(k, j + 1, 16384);
                }
                if (i == 2) {
                    method200(k, j, 4096);
                    method200(k + 1, j, 0x10000);
                }
                if (i == 3) {
                    method200(k, j, 16384);
                    method200(k, j - 1, 1024);
                }
            }
            if (l == 1 || l == 3) {
                if (i == 0) {
                    method200(k, j, 512);
                    method200(k - 1, j + 1, 8192);
                }
                if (i == 1) {
                    method200(k, j, 2048);
                    method200(k + 1, j + 1, 32768);
                }
                if (i == 2) {
                    method200(k, j, 8192);
                    method200(k + 1, j - 1, 512);
                }
                if (i == 3) {
                    method200(k, j, 32768);
                    method200(k - 1, j - 1, 2048);
                }
            }
            if (l == 2) {
                if (i == 0) {
                    method200(k, j, 0x10400);
                    method200(k - 1, j, 4096);
                    method200(k, j + 1, 16384);
                }
                if (i == 1) {
                    method200(k, j, 5120);
                    method200(k, j + 1, 16384);
                    method200(k + 1, j, 0x10000);
                }
                if (i == 2) {
                    method200(k, j, 20480);
                    method200(k + 1, j, 0x10000);
                    method200(k, j - 1, 1024);
                }
                if (i == 3) {
                    method200(k, j, 0x14000);
                    method200(k, j - 1, 1024);
                    method200(k - 1, j, 4096);
                }
            }
        }
    }

    public void method198(int i, int j, int k, int l, int i1, int j1, boolean flag) {
        int k1 = 256;
        if (flag)
            k1 += 0x20000;
        l -= anInt211;
        j1 -= anInt212;
        if (i == 1 || i == 3) {
            int l1 = k;
            k = j;
            j = l1;
        }
        for (int i2 = l; i2 < l + k; i2++)
            if (i2 >= 0 && i2 < anInt213) {
                for (int j2 = j1; j2 < j1 + j; j2++)
                    if (j2 >= 0 && j2 < anInt214)
                        method200(i2, j2, k1);

            }

        if (i1 != 9) {
            for (int k2 = 1; k2 > 0; k2++)
                ;
        }
    }

    public void method199(byte byte0, int i, int j) {
        if (byte0 != aByte208) {
            return;
        } else {
            j -= anInt211;
            i -= anInt212;
            anIntArrayArray215[j][i] |= 0x200000;
            return;
        }
    }

    public void method200(int i, int j, int k) {
        anIntArrayArray215[i][j] |= k;
    }

    public void method201(boolean flag, int i, int j, int k, int l, int i1) {
        l = 9 / l;
        j -= anInt211;
        k -= anInt212;
        if (i1 == 0) {
            if (i == 0) {
                method203(k, (byte) -9, j, 128);
                method203(k, (byte) -9, j - 1, 8);
            }
            if (i == 1) {
                method203(k, (byte) -9, j, 2);
                method203(k + 1, (byte) -9, j, 32);
            }
            if (i == 2) {
                method203(k, (byte) -9, j, 8);
                method203(k, (byte) -9, j + 1, 128);
            }
            if (i == 3) {
                method203(k, (byte) -9, j, 32);
                method203(k - 1, (byte) -9, j, 2);
            }
        }
        if (i1 == 1 || i1 == 3) {
            if (i == 0) {
                method203(k, (byte) -9, j, 1);
                method203(k + 1, (byte) -9, j - 1, 16);
            }
            if (i == 1) {
                method203(k, (byte) -9, j, 4);
                method203(k + 1, (byte) -9, j + 1, 64);
            }
            if (i == 2) {
                method203(k, (byte) -9, j, 16);
                method203(k - 1, (byte) -9, j + 1, 1);
            }
            if (i == 3) {
                method203(k, (byte) -9, j, 64);
                method203(k - 1, (byte) -9, j - 1, 4);
            }
        }
        if (i1 == 2) {
            if (i == 0) {
                method203(k, (byte) -9, j, 130);
                method203(k, (byte) -9, j - 1, 8);
                method203(k + 1, (byte) -9, j, 32);
            }
            if (i == 1) {
                method203(k, (byte) -9, j, 10);
                method203(k + 1, (byte) -9, j, 32);
                method203(k, (byte) -9, j + 1, 128);
            }
            if (i == 2) {
                method203(k, (byte) -9, j, 40);
                method203(k, (byte) -9, j + 1, 128);
                method203(k - 1, (byte) -9, j, 2);
            }
            if (i == 3) {
                method203(k, (byte) -9, j, 160);
                method203(k - 1, (byte) -9, j, 2);
                method203(k, (byte) -9, j - 1, 8);
            }
        }
        if (flag) {
            if (i1 == 0) {
                if (i == 0) {
                    method203(k, (byte) -9, j, 0x10000);
                    method203(k, (byte) -9, j - 1, 4096);
                }
                if (i == 1) {
                    method203(k, (byte) -9, j, 1024);
                    method203(k + 1, (byte) -9, j, 16384);
                }
                if (i == 2) {
                    method203(k, (byte) -9, j, 4096);
                    method203(k, (byte) -9, j + 1, 0x10000);
                }
                if (i == 3) {
                    method203(k, (byte) -9, j, 16384);
                    method203(k - 1, (byte) -9, j, 1024);
                }
            }
            if (i1 == 1 || i1 == 3) {
                if (i == 0) {
                    method203(k, (byte) -9, j, 512);
                    method203(k + 1, (byte) -9, j - 1, 8192);
                }
                if (i == 1) {
                    method203(k, (byte) -9, j, 2048);
                    method203(k + 1, (byte) -9, j + 1, 32768);
                }
                if (i == 2) {
                    method203(k, (byte) -9, j, 8192);
                    method203(k - 1, (byte) -9, j + 1, 512);
                }
                if (i == 3) {
                    method203(k, (byte) -9, j, 32768);
                    method203(k - 1, (byte) -9, j - 1, 2048);
                }
            }
            if (i1 == 2) {
                if (i == 0) {
                    method203(k, (byte) -9, j, 0x10400);
                    method203(k, (byte) -9, j - 1, 4096);
                    method203(k + 1, (byte) -9, j, 16384);
                }
                if (i == 1) {
                    method203(k, (byte) -9, j, 5120);
                    method203(k + 1, (byte) -9, j, 16384);
                    method203(k, (byte) -9, j + 1, 0x10000);
                }
                if (i == 2) {
                    method203(k, (byte) -9, j, 20480);
                    method203(k, (byte) -9, j + 1, 0x10000);
                    method203(k - 1, (byte) -9, j, 1024);
                }
                if (i == 3) {
                    method203(k, (byte) -9, j, 0x14000);
                    method203(k - 1, (byte) -9, j, 1024);
                    method203(k, (byte) -9, j - 1, 4096);
                }
            }
        }
    }

    public void method202(int i, int j, int k, int l, boolean flag, boolean flag1, int i1) {
        int j1 = 256;
        if (flag1)
            j1 += 0x20000;
        j -= anInt211;
        i -= anInt212;
        if (!flag)
            anInt207 = 67;
        if (k == 1 || k == 3) {
            int k1 = l;
            l = i1;
            i1 = k1;
        }
        for (int l1 = j; l1 < j + l; l1++)
            if (l1 >= 0 && l1 < anInt213) {
                for (int i2 = i; i2 < i + i1; i2++)
                    if (i2 >= 0 && i2 < anInt214)
                        method203(i2, (byte) -9, l1, j1);

            }

    }

    public void method203(int i, byte byte0, int j, int k) {
        anIntArrayArray215[j][i] &= 0xffffff - k;
        if (byte0 == -9)
            ;
    }

    public void method204(int i, int j, int k) {
        if (k != 0) {
            return;
        } else {
            j -= anInt211;
            i -= anInt212;
            anIntArrayArray215[j][i] &= 0xdfffff;
            return;
        }
    }

    public boolean method205(int i, int j, int k, int l, int i1, int j1, int k1) {
        if (k1 == j1 && i1 == k)
            return true;
        k1 -= anInt211;
        i1 -= anInt212;
        if (i != -7517)
            throw new NullPointerException();
        j1 -= anInt211;
        k -= anInt212;
        if (l == 0)
            if (j == 0) {
                if (k1 == j1 - 1 && i1 == k)
                    return true;
                if (k1 == j1 && i1 == k + 1 && (anIntArrayArray215[k1][i1] & 0x280120) == 0)
                    return true;
                if (k1 == j1 && i1 == k - 1 && (anIntArrayArray215[k1][i1] & 0x280102) == 0)
                    return true;
            } else if (j == 1) {
                if (k1 == j1 && i1 == k + 1)
                    return true;
                if (k1 == j1 - 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x280108) == 0)
                    return true;
                if (k1 == j1 + 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x280180) == 0)
                    return true;
            } else if (j == 2) {
                if (k1 == j1 + 1 && i1 == k)
                    return true;
                if (k1 == j1 && i1 == k + 1 && (anIntArrayArray215[k1][i1] & 0x280120) == 0)
                    return true;
                if (k1 == j1 && i1 == k - 1 && (anIntArrayArray215[k1][i1] & 0x280102) == 0)
                    return true;
            } else if (j == 3) {
                if (k1 == j1 && i1 == k - 1)
                    return true;
                if (k1 == j1 - 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x280108) == 0)
                    return true;
                if (k1 == j1 + 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x280180) == 0)
                    return true;
            }
        if (l == 2)
            if (j == 0) {
                if (k1 == j1 - 1 && i1 == k)
                    return true;
                if (k1 == j1 && i1 == k + 1)
                    return true;
                if (k1 == j1 + 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x280180) == 0)
                    return true;
                if (k1 == j1 && i1 == k - 1 && (anIntArrayArray215[k1][i1] & 0x280102) == 0)
                    return true;
            } else if (j == 1) {
                if (k1 == j1 - 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x280108) == 0)
                    return true;
                if (k1 == j1 && i1 == k + 1)
                    return true;
                if (k1 == j1 + 1 && i1 == k)
                    return true;
                if (k1 == j1 && i1 == k - 1 && (anIntArrayArray215[k1][i1] & 0x280102) == 0)
                    return true;
            } else if (j == 2) {
                if (k1 == j1 - 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x280108) == 0)
                    return true;
                if (k1 == j1 && i1 == k + 1 && (anIntArrayArray215[k1][i1] & 0x280120) == 0)
                    return true;
                if (k1 == j1 + 1 && i1 == k)
                    return true;
                if (k1 == j1 && i1 == k - 1)
                    return true;
            } else if (j == 3) {
                if (k1 == j1 - 1 && i1 == k)
                    return true;
                if (k1 == j1 && i1 == k + 1 && (anIntArrayArray215[k1][i1] & 0x280120) == 0)
                    return true;
                if (k1 == j1 + 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x280180) == 0)
                    return true;
                if (k1 == j1 && i1 == k - 1)
                    return true;
            }
        if (l == 9) {
            if (k1 == j1 && i1 == k + 1 && (anIntArrayArray215[k1][i1] & 0x20) == 0)
                return true;
            if (k1 == j1 && i1 == k - 1 && (anIntArrayArray215[k1][i1] & 2) == 0)
                return true;
            if (k1 == j1 - 1 && i1 == k && (anIntArrayArray215[k1][i1] & 8) == 0)
                return true;
            return k1 == j1 + 1 && i1 == k && (anIntArrayArray215[k1][i1] & 0x80) == 0;
        }
        return false;
    }

    public boolean method206(int i, int j, int k, int l, int i1, int j1, int k1) {
        if (k != anInt209)
            anInt207 = -218;
        if (l == i1 && j1 == k1)
            return true;
        l -= anInt211;
        j1 -= anInt212;
        i1 -= anInt211;
        k1 -= anInt212;
        if (j == 6 || j == 7) {
            if (j == 7)
                i = i + 2 & 3;
            if (i == 0) {
                if (l == i1 + 1 && j1 == k1 && (anIntArrayArray215[l][j1] & 0x80) == 0)
                    return true;
                if (l == i1 && j1 == k1 - 1 && (anIntArrayArray215[l][j1] & 2) == 0)
                    return true;
            } else if (i == 1) {
                if (l == i1 - 1 && j1 == k1 && (anIntArrayArray215[l][j1] & 8) == 0)
                    return true;
                if (l == i1 && j1 == k1 - 1 && (anIntArrayArray215[l][j1] & 2) == 0)
                    return true;
            } else if (i == 2) {
                if (l == i1 - 1 && j1 == k1 && (anIntArrayArray215[l][j1] & 8) == 0)
                    return true;
                if (l == i1 && j1 == k1 + 1 && (anIntArrayArray215[l][j1] & 0x20) == 0)
                    return true;
            } else if (i == 3) {
                if (l == i1 + 1 && j1 == k1 && (anIntArrayArray215[l][j1] & 0x80) == 0)
                    return true;
                if (l == i1 && j1 == k1 + 1 && (anIntArrayArray215[l][j1] & 0x20) == 0)
                    return true;
            }
        }
        if (j == 8) {
            if (l == i1 && j1 == k1 + 1 && (anIntArrayArray215[l][j1] & 0x20) == 0)
                return true;
            if (l == i1 && j1 == k1 - 1 && (anIntArrayArray215[l][j1] & 2) == 0)
                return true;
            if (l == i1 - 1 && j1 == k1 && (anIntArrayArray215[l][j1] & 8) == 0)
                return true;
            return l == i1 + 1 && j1 == k1 && (anIntArrayArray215[l][j1] & 0x80) == 0;
        }
        return false;
    }

    public boolean method207(int i, int j, int k, int l, int i1, int j1, int k1,
                             int l1) {
        int i2 = (l + k1) - 1;
        int j2 = (j1 + j) - 1;
        l1 = 17 / l1;
        if (k >= l && k <= i2 && i >= j1 && i <= j2)
            return true;
        if (k == l - 1 && i >= j1 && i <= j2 && (anIntArrayArray215[k - anInt211][i - anInt212] & 8) == 0
                && (i1 & 8) == 0)
            return true;
        if (k == i2 + 1 && i >= j1 && i <= j2 && (anIntArrayArray215[k - anInt211][i - anInt212] & 0x80) == 0
                && (i1 & 2) == 0)
            return true;
        if (i == j1 - 1 && k >= l && k <= i2 && (anIntArrayArray215[k - anInt211][i - anInt212] & 2) == 0
                && (i1 & 4) == 0)
            return true;
        return i == j2 + 1 && k >= l && k <= i2 && (anIntArrayArray215[k - anInt211][i - anInt212] & 0x20) == 0
                && (i1 & 1) == 0;
    }

    public boolean aBoolean206;
    public int anInt207;
    public byte aByte208;
    public int anInt209;
    public boolean aBoolean210;
    public int anInt211;
    public int anInt212;
    public int anInt213;
    public int anInt214;
    public int[][] anIntArrayArray215;
}
