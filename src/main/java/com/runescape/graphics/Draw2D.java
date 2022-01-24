package com.runescape.graphics;

import com.runescape.util.CacheableNode;

public class Draw2D extends CacheableNode {

    public static void prepare(int width, int[] data, int height) {
        dest = data;
        Draw2D.width = width;
        Draw2D.height = height;
        setBounds(height, 0, width, 0);
    }

    public static void resetBounds() {
        left = 0;
        top = 0;
        right = width;
        bottom = height;
        rightX = right - 1;
        centerX = right / 2;
    }

    public static void setBounds(int i, int j, int k, int i1) {
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
        rightX = right - 1;
        centerX = right / 2;
        centerY = bottom / 2;
    }

    public static void clear() {
        int j = width * height;
        for (int k = 0; k < j; k++) {
            dest[k] = 0;
        }
    }

    public static void fillRect(int i, int j, int k, int l, int i1) {
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

    public static void drawRect(int j, int k, int l, int i1, int j1) {
        drawHorizontalLine(k, i1, j1, j);
        drawHorizontalLine(k, (i1 + l) - 1, j1, j);
        drawVerticalLine(k, i1, l, j);
        drawVerticalLine(k, i1, l, (j + j1) - 1);
    }

    public static void drawHorizontalLine(int i, int k, int l, int i1) {
        if (k < top || k >= bottom)
            return;
        if (i1 < left) {
            l -= left - i1;
            i1 = left;
        }
        if (i1 + l > right)
            l = right - i1;
        int j1 = i1 + k * width;
        for (int k1 = 0; k1 < l; k1++)
            dest[j1 + k1] = i;
    }

    public static void drawVerticalLine(int i, int k, int l, int i1) {
        if (i1 < left || i1 >= right)
            return;
        if (k < top) {
            l -= top - k;
            k = top;
        }
        if (k + l > bottom)
            l = bottom - k;
        int j1 = i1 + k * width;
        for (int k1 = 0; k1 < l; k1++)
            dest[j1 + k1 * width] = i;

    }

    public Draw2D() {
    }

    public static int[] dest;
    public static int width;
    public static int height;
    public static int top;
    public static int bottom;
    public static int left;
    public static int right;
    public static int rightX;
    public static int centerX;
    public static int centerY;

}
