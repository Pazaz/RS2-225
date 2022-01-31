package com.jagex.runescape.graphics;

import com.jagex.runescape.util.CacheableNode;

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

    public static void setBounds(int y1, int y0, int x1, int x0) {
        if (x0 < 0) {
            x0 = 0;
        }

        if (y0 < 0) {
            y0 = 0;
        }

        if (x1 > width) {
            x1 = width;
        }

        if (y1 > height) {
            y1 = height;
        }

        left = x0;
        top = y0;
        right = x1;
        bottom = y1;
        rightX = right - 1;
        centerX = right / 2;
        centerY = bottom / 2;
    }

    public static void clear() {
        int len = width * height;
        for (int i = 0; i < len; i++) {
            dest[i] = 0;
        }
    }

    public static void fillRect(int y, int x, int rgb, int w, int h) {
        if (x < left) {
            w -= left - x;
            x = left;
        }

        if (y < top) {
            h -= top - y;
            y = top;
        }

        if (x + w > right) {
            w = right - x;
        }

        if (y + h > bottom) {
            h = bottom - y;
        }

        int off = x + y * Draw2D.width;
        for (int col = -w; col < 0; ++col) {
            dest[off++] = rgb;
        }

        for (int row = 0; row < h; ++row) {
            System.arraycopy(dest, off - w, dest, (off - w) + Draw2D.width * row, w);
        }
    }

    public static void drawRect(int x, int rgb, int h, int y, int len) {
        drawHorizontalLine(rgb, y, len, x);
        drawHorizontalLine(rgb, (y + h) - 1, len, x);
        drawVerticalLine(rgb, y, h, x);
        drawVerticalLine(rgb, y, h, (x + len) - 1);
    }

    public static void drawHorizontalLine(int rgb, int y, int len, int x) {
        if (y < top || y >= bottom) {
            return;
        }

        if (x < left) {
            len -= left - x;
            x = left;
        }

        if (x + len > right) {
            len = right - x;
        }

        int off = x + y * width;
        for (int w = 0; w < len; w++) {
            dest[off + w] = rgb;
        }
    }

    public static void drawVerticalLine(int rgb, int y, int len, int x) {
        if (x < left || x >= right) {
            return;
        }

        if (y < top) {
            len -= top - y;
            y = top;
        }

        if (y + len > bottom) {
            len = bottom - y;
        }

        int off = x + y * width;
        for (int v = 0; v < len; v++) {
            dest[off + v * width] = rgb;
        }
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
