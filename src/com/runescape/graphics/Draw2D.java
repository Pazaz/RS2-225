package com.runescape.graphics;

import com.runescape.util.CacheableNode;

public class Draw2D extends CacheableNode {

    public static void init(int i, int[] ai, int j, int k) {
        dest = ai;
        width = i;
        while (j >= 0)
            anInt1305 = -151;
        height = k;
        setBounds(k, 0, i, 789, 0);
    }

    public static void method377(int i) {
        left = 0;
        top = 0;
        right = width;
        if (i != 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        bottom = height;
        anInt1315 = right - 1;
        anInt1316 = right / 2;
    }

    public static void setBounds(int i, int j, int k, int l, int i1) {
        if (i1 < 0)
            i1 = 0;
        if (j < 0)
            j = 0;
        if (k > width)
            k = width;
        if (i > height)
            i = height;
        left = i1;
        top = j;
        right = k;
        bottom = i;
        anInt1315 = right - 1;
        anInt1316 = right / 2;
        if (l <= 0) {
            for (int j1 = 1; j1 > 0; j1++)
                ;
        }
        anInt1317 = bottom / 2;
    }

    public static void method379(int i) {
        i = 87 / i;
        int j = width * height;
        for (int k = 0; k < j; k++)
            dest[k] = 0;

    }

    public static void fillRect(int i, int j, int k, byte byte0, int l, int i1) {
        if (byte0 != 93)
            anInt1305 = 289;
        if (j < left) {
            l -= left - j;
            j = left;
        }
        if (i < top) {
            i1 -= top - i;
            i = top;
        }
        if (j + l > right)
            l = right - j;
        if (i + i1 > bottom)
            i1 = bottom - i;
        int j1 = width - l;
        int k1 = j + i * width;
        for (int l1 = -i1; l1 < 0; l1++) {
            for (int i2 = -l; i2 < 0; i2++)
                dest[k1++] = k;

            k1 += j1;
        }

    }

    public static void method381(int i, int j, int k, int l, int i1, int j1) {
        if (i < 3 || i > 3) {
            return;
        } else {
            method382(k, 0, i1, j1, j);
            method382(k, 0, (i1 + l) - 1, j1, j);
            method383(k, anInt1306, i1, l, j);
            method383(k, anInt1306, i1, l, (j + j1) - 1);
            return;
        }
    }

    public static void method382(int i, int j, int k, int l, int i1) {
        if (k < top || k >= bottom)
            return;
        if (i1 < left) {
            l -= left - i1;
            i1 = left;
        }
        if (i1 + l > right)
            l = right - i1;
        int j1 = i1 + k * width;
        if (j != 0)
            return;
        for (int k1 = 0; k1 < l; k1++)
            dest[j1 + k1] = i;

    }

    public static void method383(int i, int j, int k, int l, int i1) {
        if (i1 < left || i1 >= right)
            return;
        if (k < top) {
            l -= top - k;
            k = top;
        }
        if (k + l > bottom)
            l = bottom - k;
        int j1 = i1 + k * width;
        if (j != 0)
            anInt1306 = 71;
        for (int k1 = 0; k1 < l; k1++)
            dest[j1 + k1 * width] = i;

    }

    public Draw2D() {
    }

    public static int anInt1305;
    public static int anInt1306;
    public static boolean aBoolean1307 = true;
    public static int[] dest;
    public static int width;
    public static int height;
    public static int top;
    public static int bottom;
    public static int left;
    public static int right;
    public static int anInt1315;
    public static int anInt1316;
    public static int anInt1317;
    public static int anInt1318;

}
