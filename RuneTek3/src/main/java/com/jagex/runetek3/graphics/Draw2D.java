package com.jagex.runetek3.graphics;

import com.jagex.runetek3.util.CacheableNode;

public class Draw2D extends CacheableNode {

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

    public Draw2D() {
    }

    public static void prepare(int width, int height, int[] data) {
        dest = data;
        Draw2D.width = width;
        Draw2D.height = height;
        setBounds(0, 0, width, height);
    }

    public static void resetBounds() {
        left = 0;
        top = 0;
        right = width;
        bottom = height;
        rightX = right;
        centerX = right / 2;
    }

    public static void setBounds(int x0, int y0, int x1, int y1) {
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
        rightX = right;
        centerX = right / 2;
        centerY = bottom / 2;
    }

    public static void clear() {
        int len = width * height;
        for (int i = 0; i < len; i++) {
            dest[i] = 0;
        }
    }

    public static void fillRect(int x, int y, int w, int h, int rgb) {
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

    public static void drawRect(int x, int y, int w, int h, int rgb) {
        drawHorizontalLine(x, y, w, rgb);
        drawHorizontalLine(x, (y + h) - 1, w, rgb);
        drawVerticalLine(x, y, h, rgb);
        drawVerticalLine((x + w) - 1, y, h, rgb);
    }

    public static void drawHorizontalLine(int x, int y, int len, int rgb) {
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

    public static void drawVerticalLine(int x, int y, int len, int rgb) {
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

    // new
    public static void fillCircle(int xCenter, int yCenter, int yRadius, int rgb, int alpha) {
        int invAlpha = 256 - alpha;
        int r0 = (rgb >> 16 & 0xff) * alpha;
        int g0 = (rgb >> 8 & 0xff) * alpha;
        int b0 = (rgb & 0xff) * alpha;

        int yStart = yCenter - yRadius;
        if (yStart < 0) {
            yStart = 0;
        }

        int yEnd = yCenter + yRadius;
        if (yEnd >= Draw2D.height) {
            yEnd = Draw2D.height - 1;
        }

        for (int y = yStart; y <= yEnd; y++) {
            int midpoint = y - yCenter;
            int xRadius = (int) Math.sqrt(yRadius * yRadius - midpoint * midpoint);

            int xStart = xCenter - xRadius;
            if (xStart < 0) {
                xStart = 0;
            }

            int xEnd = xCenter + xRadius;
            if (xEnd >= Draw2D.width) {
                xEnd = Draw2D.width - 1;
            }

            int offset = xStart + y * width;
            for (int l3 = xStart; l3 <= xEnd; l3++) {
                int r1 = (dest[offset] >> 16 & 0xff) * invAlpha;
                int g1 = (dest[offset] >> 8 & 0xff) * invAlpha;
                int b1 = (dest[offset] & 0xff) * invAlpha;
                dest[offset++] = ((r0 + r1 >> 8) << 16) + ((g0 + b1 >> 8) << 8) + (b0 + g1 >> 8);
            }
        }
    }

    // new
    public static void fillRect(int x, int y, int w, int h, int rgb, int alpha) {
        if (x < left) {
            w -= left - x;
            x = left;
        }

        if (y < top) {
            h -= top - y;
            y = top;
        }

        if ((x + w) > right) {
            w = right - x;
        }

        if ((y + h) > bottom) {
            h = bottom - y;
        }

        int invAlpha = 256 - alpha;
        int r0 = ((rgb >> 16) & 0xff) * alpha;
        int g0 = ((rgb >> 8) & 0xff) * alpha;
        int b0 = (rgb & 0xff) * alpha;

        int step = width - w;
        int offset = x + (y * width);

        for (int i = 0; i < h; i++) {
            for (int j = -w; j < 0; j++) {
                int r1 = ((dest[offset] >> 16) & 0xff) * invAlpha;
                int g1 = ((dest[offset] >> 8) & 0xff) * invAlpha;
                int b1 = (dest[offset] & 0xff) * invAlpha;
                dest[offset++] = (((r0 + r1) >> 8) << 16) + (((g0 + g1) >> 8) << 8) + ((b0 + b1) >> 8);
            }
            offset += step;
        }
    }

}
