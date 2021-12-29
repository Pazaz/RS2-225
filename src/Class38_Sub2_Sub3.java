// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.math.BigInteger;
import sign.signlink;

public class Class38_Sub2_Sub3 extends Class38_Sub2 {

    public static Class38_Sub2_Sub3 method433(int i, int j) {
        synchronized (aClass27_1338) {
            Class38_Sub2_Sub3 class38_sub2_sub3_2 = null;
            if (i == 0 && anInt1334 > 0) {
                anInt1334--;
                class38_sub2_sub3_2 = (Class38_Sub2_Sub3) aClass27_1337.method269();
            } else if (i == 1 && anInt1335 > 0) {
                anInt1335--;
                class38_sub2_sub3_2 = (Class38_Sub2_Sub3) aClass27_1338.method269();
            } else if (i == 2 && anInt1336 > 0) {
                anInt1336--;
                class38_sub2_sub3_2 = (Class38_Sub2_Sub3) aClass27_1339.method269();
            }
            if (class38_sub2_sub3_2 != null) {
                class38_sub2_sub3_2.anInt1329 = 0;
                Class38_Sub2_Sub3 class38_sub2_sub3 = class38_sub2_sub3_2;
                return class38_sub2_sub3;
            }
        }
        if (j >= 0)
            aBoolean1326 = !aBoolean1326;
        Class38_Sub2_Sub3 class38_sub2_sub3_1 = new Class38_Sub2_Sub3(anInt1319);
        class38_sub2_sub3_1.anInt1329 = 0;
        if (i == 0)
            class38_sub2_sub3_1.aByteArray1328 = new byte[100];
        else if (i == 1)
            class38_sub2_sub3_1.aByteArray1328 = new byte[5000];
        else
            class38_sub2_sub3_1.aByteArray1328 = new byte[30000];
        return class38_sub2_sub3_1;
    }

    public void method434(byte byte0) {
        synchronized (aClass27_1338) {
            anInt1329 = 0;
            if (aByteArray1328.length == 100 && anInt1334 < 1000) {
                aClass27_1337.method267(this);
                anInt1334++;
                return;
            }
            if (aByteArray1328.length == 5000 && anInt1335 < 250) {
                aClass27_1338.method267(this);
                anInt1335++;
                return;
            }
            if (aByteArray1328.length == 30000 && anInt1336 < 50) {
                aClass27_1339.method267(this);
                anInt1336++;
                return;
            }
        }
        if (byte0 != 8)
            anInt1319 = -173;
    }

    public Class38_Sub2_Sub3(int i) {
        aByte1320 = -34;
        aByte1321 = -106;
        anInt1322 = 3;
        anInt1323 = -506;
        aBoolean1324 = true;
        anInt1325 = 4277;
        anInt1327 = -178;
        if (i != 40946)
            aBoolean1326 = !aBoolean1326;
    }

    public Class38_Sub2_Sub3(int i, byte abyte0[]) {
        aByte1320 = -34;
        aByte1321 = -106;
        anInt1322 = 3;
        anInt1323 = -506;
        aBoolean1324 = true;
        anInt1325 = 4277;
        anInt1327 = -178;
        aByteArray1328 = abyte0;
        anInt1329 = 0;
        i = 15 / i;
    }

    public void method435(byte byte0, int i) {
        aByteArray1328[anInt1329++] = (byte) (i + aClass37_1333.method346());
        if (byte0 != aByte1320)
            aBoolean1326 = !aBoolean1326;
    }

    public void method436(int i) {
        aByteArray1328[anInt1329++] = (byte) i;
    }

    public void method437(int i) {
        aByteArray1328[anInt1329++] = (byte) (i >> 8);
        aByteArray1328[anInt1329++] = (byte) i;
    }

    public void method438(boolean flag, int i) {
        if (!flag)
            aBoolean1326 = !aBoolean1326;
        aByteArray1328[anInt1329++] = (byte) i;
        aByteArray1328[anInt1329++] = (byte) (i >> 8);
    }

    public void method439(int i) {
        aByteArray1328[anInt1329++] = (byte) (i >> 16);
        aByteArray1328[anInt1329++] = (byte) (i >> 8);
        aByteArray1328[anInt1329++] = (byte) i;
    }

    public void method440(int i) {
        aByteArray1328[anInt1329++] = (byte) (i >> 24);
        aByteArray1328[anInt1329++] = (byte) (i >> 16);
        aByteArray1328[anInt1329++] = (byte) (i >> 8);
        aByteArray1328[anInt1329++] = (byte) i;
    }

    public void method441(boolean flag, int i) {
        aByteArray1328[anInt1329++] = (byte) i;
        aByteArray1328[anInt1329++] = (byte) (i >> 8);
        aByteArray1328[anInt1329++] = (byte) (i >> 16);
        if (flag) {
            return;
        } else {
            aByteArray1328[anInt1329++] = (byte) (i >> 24);
            return;
        }
    }

    public void method442(boolean flag, long l) {
        aByteArray1328[anInt1329++] = (byte) (int) (l >> 56);
        aByteArray1328[anInt1329++] = (byte) (int) (l >> 48);
        aByteArray1328[anInt1329++] = (byte) (int) (l >> 40);
        aByteArray1328[anInt1329++] = (byte) (int) (l >> 32);
        aByteArray1328[anInt1329++] = (byte) (int) (l >> 24);
        aByteArray1328[anInt1329++] = (byte) (int) (l >> 16);
        aByteArray1328[anInt1329++] = (byte) (int) (l >> 8);
        if (!flag) {
            return;
        } else {
            aByteArray1328[anInt1329++] = (byte) (int) l;
            return;
        }
    }

    public void method443(String s) {
        s.getBytes(0, s.length(), aByteArray1328, anInt1329);
        anInt1329 += s.length();
        aByteArray1328[anInt1329++] = 10;
    }

    public void method444(byte abyte0[], int i, int j, byte byte0) {
        if (byte0 != aByte1321) {
            for (int k = 1; k > 0; k++)
                ;
        }
        for (int l = j; l < j + i; l++)
            aByteArray1328[anInt1329++] = abyte0[l];

    }

    public void method445(int i, int j) {
        if (i != 0)
            aBoolean1326 = !aBoolean1326;
        aByteArray1328[anInt1329 - j - 1] = (byte) j;
    }

    public int method446() {
        return aByteArray1328[anInt1329++] & 0xff;
    }

    public byte method447() {
        return aByteArray1328[anInt1329++];
    }

    public int method448() {
        anInt1329 += 2;
        return ((aByteArray1328[anInt1329 - 2] & 0xff) << 8) + (aByteArray1328[anInt1329 - 1] & 0xff);
    }

    public int method449() {
        anInt1329 += 2;
        int i = ((aByteArray1328[anInt1329 - 2] & 0xff) << 8) + (aByteArray1328[anInt1329 - 1] & 0xff);
        if (i > 32767)
            i -= 0x10000;
        return i;
    }

    public int method450() {
        anInt1329 += 3;
        return ((aByteArray1328[anInt1329 - 3] & 0xff) << 16) + ((aByteArray1328[anInt1329 - 2] & 0xff) << 8)
                + (aByteArray1328[anInt1329 - 1] & 0xff);
    }

    public int method451() {
        anInt1329 += 4;
        return ((aByteArray1328[anInt1329 - 4] & 0xff) << 24) + ((aByteArray1328[anInt1329 - 3] & 0xff) << 16)
                + ((aByteArray1328[anInt1329 - 2] & 0xff) << 8) + (aByteArray1328[anInt1329 - 1] & 0xff);
    }

    public long method452(int i) {
        long l = (long) method451() & 0xffffffffL;
        i = 87 / i;
        long l1 = (long) method451() & 0xffffffffL;
        return (l << 32) + l1;
    }

    public String method453() {
        int i = anInt1329;
        while (aByteArray1328[anInt1329++] != 10)
            ;
        return new String(aByteArray1328, i, anInt1329 - i - 1);
    }

    public byte[] method454(byte byte0) {
        int i = anInt1329;
        if (byte0 != 31)
            throw new NullPointerException();
        while (aByteArray1328[anInt1329++] != 10)
            ;
        byte abyte0[] = new byte[anInt1329 - i - 1];
        for (int j = i; j < anInt1329 - 1; j++)
            abyte0[j - i] = aByteArray1328[j];

        return abyte0;
    }

    public void method455(int i, int j, int k, byte abyte0[]) {
        if (j >= 0)
            anInt1323 = -432;
        for (int l = k; l < k + i; l++)
            abyte0[l] = aByteArray1328[anInt1329++];

    }

    public void method456(int i) {
        anInt1330 = anInt1329 * 8;
        if (i <= 0)
            aBoolean1324 = !aBoolean1324;
    }

    public int method457(int i, int j) {
        if (i < 9 || i > 9)
            return anInt1322;
        int k = anInt1330 >> 3;
        int l = 8 - (anInt1330 & 7);
        int i1 = 0;
        anInt1330 += j;
        for (; j > l; l = 8) {
            i1 += (aByteArray1328[k++] & anIntArray1332[l]) << j - l;
            j -= l;
        }

        if (j == l)
            i1 += aByteArray1328[k] & anIntArray1332[l];
        else
            i1 += aByteArray1328[k] >> l - j & anIntArray1332[j];
        return i1;
    }

    public void method458(int i) {
        if (i != anInt1325) {
            for (int j = 1; j > 0; j++)
                ;
        }
        anInt1329 = (anInt1330 + 7) / 8;
    }

    public int method459() {
        int i = aByteArray1328[anInt1329] & 0xff;
        if (i < 128)
            return method446() - 64;
        else
            return method448() - 49152;
    }

    public int method460() {
        int i = aByteArray1328[anInt1329] & 0xff;
        if (i < 128)
            return method446();
        else
            return method448() - 32768;
    }

    public void method461(BigInteger biginteger, BigInteger biginteger1, int i) {
        int j = anInt1329;
        anInt1329 = 0;
        byte abyte0[] = new byte[j];
        method455(j, -110, 0, abyte0);
        BigInteger biginteger2 = new BigInteger(abyte0);
        BigInteger biginteger3 = biginteger2.modPow(biginteger1, biginteger);
        byte abyte1[] = biginteger3.toByteArray();
        anInt1329 = 0;
        if (i != 24676) {
            return;
        } else {
            method436(abyte1.length);
            method444(abyte1, abyte1.length, 0, (byte) -106);
            return;
        }
    }

    public static int anInt1319 = 40946;
    public byte aByte1320;
    public byte aByte1321;
    public int anInt1322;
    public int anInt1323;
    public boolean aBoolean1324;
    public int anInt1325;
    public static boolean aBoolean1326 = true;
    public int anInt1327;
    public byte aByteArray1328[];
    public int anInt1329;
    public int anInt1330;
    public static int anIntArray1331[];
    public static final int anIntArray1332[] = {
            0, 1, 3, 7, 15, 31, 63, 127, 255, 511,
            1023, 2047, 4095, 8191, 16383, 32767, 65535, 0x1ffff, 0x3ffff, 0x7ffff,
            0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff,
            0x3fffffff, 0x7fffffff, -1
    };
    public Class37 aClass37_1333;
    public static int anInt1334;
    public static int anInt1335;
    public static int anInt1336;
    public static Class27 aClass27_1337 = new Class27(0);
    public static Class27 aClass27_1338 = new Class27(0);
    public static Class27 aClass27_1339 = new Class27(0);
    public static boolean aBoolean1340;

    static {
        anIntArray1331 = new int[256];
        for (int j = 0; j < 256; j++) {
            int i = j;
            for (int k = 0; k < 8; k++)
                if ((i & 1) == 1)
                    i = i >>> 1 ^ 0xedb88320;
                else
                    i >>>= 1;

            anIntArray1331[j] = i;
        }

    }
}
