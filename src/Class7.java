// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class7 {

    public static synchronized void method182(int i) {
        aClass38_Sub2_Sub3_199 = Class38_Sub2_Sub3.method433(1, -737);
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

    public static synchronized Class38_Sub2_Sub3 method184(int i) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = null;
        if (aClass38_Sub2_Sub3_200 != null && aBoolean198)
            class38_sub2_sub3 = aClass38_Sub2_Sub3_200;
        aClass38_Sub2_Sub3_200 = null;
        if (i >= 0)
            aBoolean196 = !aBoolean196;
        return class38_sub2_sub3;
    }

    public static synchronized Class38_Sub2_Sub3 method185(int i) {
        if (i <= 0)
            aBoolean197 = !aBoolean197;
        Class38_Sub2_Sub3 class38_sub2_sub3 = null;
        if (aClass38_Sub2_Sub3_199 != null && aClass38_Sub2_Sub3_199.anInt1329 > 0 && aBoolean198)
            class38_sub2_sub3 = aClass38_Sub2_Sub3_199;
        method183((byte) 65);
        return class38_sub2_sub3;
    }

    public static synchronized void method186(int i, int j) {
        if (i <= 0)
            aBoolean196 = !aBoolean196;
        if (aClass38_Sub2_Sub3_199.anInt1329 + j >= 500) {
            Class38_Sub2_Sub3 class38_sub2_sub3 = aClass38_Sub2_Sub3_199;
            aClass38_Sub2_Sub3_199 = Class38_Sub2_Sub3.method433(1, -737);
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
            aClass38_Sub2_Sub3_199.method436(1);
        else
            aClass38_Sub2_Sub3_199.method436(2);
        aClass38_Sub2_Sub3_199.method436((int) l1);
        aClass38_Sub2_Sub3_199.method439(i + (k << 10));
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
            aClass38_Sub2_Sub3_199.method436(3);
        else
            aClass38_Sub2_Sub3_199.method436(4);
        aClass38_Sub2_Sub3_199.method436((int) l1);
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
                aClass38_Sub2_Sub3_199.method436(5);
                aClass38_Sub2_Sub3_199.method436((int) l1);
                aClass38_Sub2_Sub3_199.method436((j - anInt204) + 8 + ((i - anInt205) + 8 << 4));
            } else if (j - anInt204 < 128 && j - anInt204 >= -128 && i - anInt205 < 128 && i - anInt205 >= -128) {
                method186(anInt195, 4);
                aClass38_Sub2_Sub3_199.method436(6);
                aClass38_Sub2_Sub3_199.method436((int) l1);
                aClass38_Sub2_Sub3_199.method436((j - anInt204) + 128);
                aClass38_Sub2_Sub3_199.method436((i - anInt205) + 128);
            } else {
                method186(anInt195, 5);
                aClass38_Sub2_Sub3_199.method436(7);
                aClass38_Sub2_Sub3_199.method436((int) l1);
                aClass38_Sub2_Sub3_199.method439(j + (i << 10));
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
        aClass38_Sub2_Sub3_199.method436(8);
        aClass38_Sub2_Sub3_199.method436((int) l1);
        aClass38_Sub2_Sub3_199.method436(i);
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
        aClass38_Sub2_Sub3_199.method436(9);
        aClass38_Sub2_Sub3_199.method436((int) l1);
        aClass38_Sub2_Sub3_199.method436(i);
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
        aClass38_Sub2_Sub3_199.method436(10);
        aClass38_Sub2_Sub3_199.method436((int) l1);
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
        aClass38_Sub2_Sub3_199.method436(11);
        aClass38_Sub2_Sub3_199.method436((int) l1);
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
        aClass38_Sub2_Sub3_199.method436(12);
        aClass38_Sub2_Sub3_199.method436((int) l1);
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
        aClass38_Sub2_Sub3_199.method436(13);
        aClass38_Sub2_Sub3_199.method436((int) l1);
    }

    public static byte aByte194 = 65;
    public static int anInt195 = 78;
    public static boolean aBoolean196;
    public static boolean aBoolean197;
    public static boolean aBoolean198;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_199 = null;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_200 = null;
    public static long aLong201;
    public static int anInt202;
    public static long aLong203;
    public static int anInt204;
    public static int anInt205;

}
