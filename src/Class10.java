// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.io.PrintStream;
import sign.signlink;

public class Class10 {

    public static void method209(Class39 class39, int i) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, class39.method474("flo.dat", null, (byte) 2));
        anInt222 = class38_sub2_sub3.method448();
        i = 35 / i;
        if (aClass10Array223 == null)
            aClass10Array223 = new Class10[anInt222];
        for (int j = 0; j < anInt222; j++) {
            if (aClass10Array223[j] == null)
                aClass10Array223[j] = new Class10();
            aClass10Array223[j].method210(false, class38_sub2_sub3);
        }

    }

    public void method210(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        if (flag)
            throw new NullPointerException();
        do {
            int i = class38_sub2_sub3.method446();
            if (i == 0)
                return;
            if (i == 1) {
                anInt224 = class38_sub2_sub3.method450();
                method211(anInt221, anInt224);
            } else if (i == 2)
                anInt225 = class38_sub2_sub3.method446();
            else if (i == 3)
                aBoolean226 = true;
            else if (i == 5)
                aBoolean227 = false;
            else if (i == 6)
                aString228 = class38_sub2_sub3.method453();
            else
                System.out.println("Error unrecognised config code: " + i);
        } while (true);
    }

    public void method211(int i, int j) {
        double d = (double) (j >> 16 & 0xff) / 256D;
        if (i >= 0) {
            for (int k = 1; k > 0; k++)
                ;
        }
        double d1 = (double) (j >> 8 & 0xff) / 256D;
        double d2 = (double) (j & 0xff) / 256D;
        double d3 = d;
        if (d1 < d3)
            d3 = d1;
        if (d2 < d3)
            d3 = d2;
        double d4 = d;
        if (d1 > d4)
            d4 = d1;
        if (d2 > d4)
            d4 = d2;
        double d5 = 0.0D;
        double d6 = 0.0D;
        double d7 = (d3 + d4) / 2D;
        if (d3 != d4) {
            if (d7 < 0.5D)
                d6 = (d4 - d3) / (d4 + d3);
            if (d7 >= 0.5D)
                d6 = (d4 - d3) / (2D - d4 - d3);
            if (d == d4)
                d5 = (d1 - d2) / (d4 - d3);
            else if (d1 == d4)
                d5 = 2D + (d2 - d) / (d4 - d3);
            else if (d2 == d4)
                d5 = 4D + (d - d1) / (d4 - d3);
        }
        d5 /= 6D;
        anInt229 = (int) (d5 * 256D);
        anInt230 = (int) (d6 * 256D);
        anInt231 = (int) (d7 * 256D);
        if (anInt230 < 0)
            anInt230 = 0;
        else if (anInt230 > 255)
            anInt230 = 255;
        if (anInt231 < 0)
            anInt231 = 0;
        else if (anInt231 > 255)
            anInt231 = 255;
        if (d7 > 0.5D)
            anInt233 = (int) ((1.0D - d7) * d6 * 512D);
        else
            anInt233 = (int) (d7 * d6 * 512D);
        if (anInt233 < 1)
            anInt233 = 1;
        anInt232 = (int) (d5 * (double) anInt233);
        int l = (anInt229 + (int) (Math.random() * 16D)) - 8;
        if (l < 0)
            l = 0;
        else if (l > 255)
            l = 255;
        int i1 = (anInt230 + (int) (Math.random() * 48D)) - 24;
        if (i1 < 0)
            i1 = 0;
        else if (i1 > 255)
            i1 = 255;
        int j1 = (anInt231 + (int) (Math.random() * 48D)) - 24;
        if (j1 < 0)
            j1 = 0;
        else if (j1 > 255)
            j1 = 255;
        anInt234 = method212(l, i1, j1);
    }

    public int method212(int i, int j, int k) {
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

    public Class10() {
        anInt225 = -1;
        aBoolean226 = false;
        aBoolean227 = true;
    }

    public static int anInt220 = 473;
    public static int anInt221 = -546;
    public static int anInt222;
    public static Class10 aClass10Array223[];
    public int anInt224;
    public int anInt225;
    public boolean aBoolean226;
    public boolean aBoolean227;
    public String aString228;
    public int anInt229;
    public int anInt230;
    public int anInt231;
    public int anInt232;
    public int anInt233;
    public int anInt234;

}
