package com.runescape.util;

public class InputTracking {

    public static synchronized void method182(int i) {
        aClass38_Sub2_Sub3_199 = Buffer.reserve(1);
        aClass38_Sub2_Sub3_200 = null;
        aLong201 = System.currentTimeMillis();
        if (i != -31717)
            aBoolean197 = !aBoolean197;
        aBoolean198 = true;
    }

    public static synchronized void method183(byte byte0) {
        aBoolean198 = false;
        aClass38_Sub2_Sub3_199 = null;
        if (byte0 != aByte194) {
            return;
        } else {
            aClass38_Sub2_Sub3_200 = null;
            return;
        }
    }

    public static synchronized Buffer method184(int i) {
        Buffer class38_sub2_sub3 = null;
        if (aClass38_Sub2_Sub3_200 != null && aBoolean198)
            class38_sub2_sub3 = aClass38_Sub2_Sub3_200;
        aClass38_Sub2_Sub3_200 = null;
        if (i >= 0)
            aBoolean196 = !aBoolean196;
        return class38_sub2_sub3;
    }

    public static synchronized Buffer method185(int i) {
        if (i <= 0)
            aBoolean197 = !aBoolean197;
        Buffer class38_sub2_sub3 = null;
        if (aClass38_Sub2_Sub3_199 != null && aClass38_Sub2_Sub3_199.offset > 0 && aBoolean198)
            class38_sub2_sub3 = aClass38_Sub2_Sub3_199;
        method183((byte) 65);
        return class38_sub2_sub3;
    }

    public static synchronized void method186(int i, int j) {
        if (i <= 0)
            aBoolean196 = !aBoolean196;
        if (aClass38_Sub2_Sub3_199.offset + j >= 500) {
            Buffer class38_sub2_sub3 = aClass38_Sub2_Sub3_199;
            aClass38_Sub2_Sub3_199 = Buffer.reserve(1);
            aClass38_Sub2_Sub3_200 = class38_sub2_sub3;
        }
    }

    public static synchronized void method187(int i, int j, int k, byte byte0) {
        if (!aBoolean198)
            return;
        if (i < 0 || i >= 789 || k < 0 || k >= 532)
            return;
        anInt202++;
        long l = System.currentTimeMillis();
        long l1 = (l - aLong201) / 10L;
        if (l1 > 250L)
            l1 = 250L;
        aLong201 = l;
        method186(anInt195, 5);
        if (byte0 != 4)
            return;
        if (j == 1)
            aClass38_Sub2_Sub3_199.writeByte(1);
        else
            aClass38_Sub2_Sub3_199.writeByte(2);
        aClass38_Sub2_Sub3_199.writeByte((int) l1);
        aClass38_Sub2_Sub3_199.writeSWord(i + (k << 10));
    }

    public static synchronized void method188(int i, int j) {
        if (!aBoolean198)
            return;
        anInt202++;
        long l = System.currentTimeMillis();
        long l1 = (l - aLong201) / 10L;
        if (l1 > 250L)
            l1 = 250L;
        aLong201 = l;
        if (j != 0)
            return;
        method186(anInt195, 2);
        if (i == 1)
            aClass38_Sub2_Sub3_199.writeByte(3);
        else
            aClass38_Sub2_Sub3_199.writeByte(4);
        aClass38_Sub2_Sub3_199.writeByte((int) l1);
    }

    public static synchronized void method189(int i, boolean flag, int j) {
        if (!aBoolean198)
            return;
        if (j < 0 || j >= 789 || i < 0 || i >= 532)
            return;
        long l = System.currentTimeMillis();
        if (!flag)
            anInt195 = 445;
        if (l - aLong203 >= 50L) {
            aLong203 = l;
            anInt202++;
            long l1 = (l - aLong201) / 10L;
            if (l1 > 250L)
                l1 = 250L;
            aLong201 = l;
            if (j - anInt204 < 8 && j - anInt204 >= -8 && i - anInt205 < 8 && i - anInt205 >= -8) {
                method186(anInt195, 3);
                aClass38_Sub2_Sub3_199.writeByte(5);
                aClass38_Sub2_Sub3_199.writeByte((int) l1);
                aClass38_Sub2_Sub3_199.writeByte((j - anInt204) + 8 + ((i - anInt205) + 8 << 4));
            } else if (j - anInt204 < 128 && j - anInt204 >= -128 && i - anInt205 < 128 && i - anInt205 >= -128) {
                method186(anInt195, 4);
                aClass38_Sub2_Sub3_199.writeByte(6);
                aClass38_Sub2_Sub3_199.writeByte((int) l1);
                aClass38_Sub2_Sub3_199.writeByte((j - anInt204) + 128);
                aClass38_Sub2_Sub3_199.writeByte((i - anInt205) + 128);
            } else {
                method186(anInt195, 5);
                aClass38_Sub2_Sub3_199.writeByte(7);
                aClass38_Sub2_Sub3_199.writeByte((int) l1);
                aClass38_Sub2_Sub3_199.writeSWord(j + (i << 10));
            }
            anInt204 = j;
            anInt205 = i;
        }
    }

    public static synchronized void method190(int i, boolean flag) {
        if (!aBoolean198)
            return;
        anInt202++;
        long l = System.currentTimeMillis();
        long l1 = (l - aLong201) / 10L;
        if (l1 > 250L)
            l1 = 250L;
        aLong201 = l;
        if (i == 1000)
            i = 11;
        if (i == 1001)
            i = 12;
        if (i == 1002)
            i = 14;
        if (i == 1003)
            i = 15;
        if (i >= 1008)
            i -= 992;
        method186(anInt195, 3);
        if (!flag) {
            for (int j = 1; j > 0; j++)
                ;
        }
        aClass38_Sub2_Sub3_199.writeByte(8);
        aClass38_Sub2_Sub3_199.writeByte((int) l1);
        aClass38_Sub2_Sub3_199.writeByte(i);
    }

    public static synchronized void method191(int i, int j) {
        if (!aBoolean198)
            return;
        anInt202++;
        long l = System.currentTimeMillis();
        long l1 = (l - aLong201) / 10L;
        if (l1 > 250L)
            l1 = 250L;
        aLong201 = l;
        if (i == 1000)
            i = 11;
        if (i == 1001)
            i = 12;
        if (i == 1002)
            i = 14;
        if (i == 1003)
            i = 15;
        if (i >= 1008)
            i -= 992;
        method186(anInt195, 3);
        aClass38_Sub2_Sub3_199.writeByte(9);
        aClass38_Sub2_Sub3_199.writeByte((int) l1);
        aClass38_Sub2_Sub3_199.writeByte(i);
        if (j == 1)
            ;
    }

    public static synchronized void method192(int i) {
        if (!aBoolean198)
            return;
        anInt202++;
        if (i >= 0)
            return;
        long l = System.currentTimeMillis();
        long l1 = (l - aLong201) / 10L;
        if (l1 > 250L)
            l1 = 250L;
        aLong201 = l;
        method186(anInt195, 2);
        aClass38_Sub2_Sub3_199.writeByte(10);
        aClass38_Sub2_Sub3_199.writeByte((int) l1);
    }

    public static synchronized void method193(int i) {
        if (!aBoolean198)
            return;
        anInt202++;
        long l = System.currentTimeMillis();
        long l1 = (l - aLong201) / 10L;
        if (l1 > 250L)
            l1 = 250L;
        aLong201 = l;
        method186(anInt195, 2);
        if (i != 0)
            aBoolean197 = !aBoolean197;
        aClass38_Sub2_Sub3_199.writeByte(11);
        aClass38_Sub2_Sub3_199.writeByte((int) l1);
    }

    public static synchronized void method194(int i) {
        if (!aBoolean198)
            return;
        anInt202++;
        long l = System.currentTimeMillis();
        long l1 = (l - aLong201) / 10L;
        if (l1 > 250L)
            l1 = 250L;
        aLong201 = l;
        method186(anInt195, 2);
        while (i >= 0)
            return;
        aClass38_Sub2_Sub3_199.writeByte(12);
        aClass38_Sub2_Sub3_199.writeByte((int) l1);
    }

    public static synchronized void method195(boolean flag) {
        if (flag)
            return;
        if (!aBoolean198)
            return;
        anInt202++;
        long l = System.currentTimeMillis();
        long l1 = (l - aLong201) / 10L;
        if (l1 > 250L)
            l1 = 250L;
        aLong201 = l;
        method186(anInt195, 2);
        aClass38_Sub2_Sub3_199.writeByte(13);
        aClass38_Sub2_Sub3_199.writeByte((int) l1);
    }

    public static byte aByte194 = 65;
    public static int anInt195 = 78;
    public static boolean aBoolean196;
    public static boolean aBoolean197;
    public static boolean aBoolean198;
    public static Buffer aClass38_Sub2_Sub3_199 = null;
    public static Buffer aClass38_Sub2_Sub3_200 = null;
    public static long aLong201;
    public static int anInt202;
    public static long aLong203;
    public static int anInt204;
    public static int anInt205;

}
