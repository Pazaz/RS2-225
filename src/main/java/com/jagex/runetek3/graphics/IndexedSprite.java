package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

public class IndexedSprite extends Draw2D {

    public IndexedSprite(FileArchive archive, String name, int index) {
        Buffer data = new Buffer(archive.read(name + ".dat", null));
        Buffer idx = new Buffer(archive.read("index.dat", null));

        idx.offset = data.readWord();
        clipWidth = idx.readWord();
        clipHeight = idx.readWord();

        palette = new int[idx.readByte()];
        for (int n = 0; n < palette.length - 1; n++) {
            palette[n + 1] = idx.readSWord();
        }

        for (int n = 0; n < index; n++) {
            idx.offset += 2;
            data.offset += idx.readWord() * idx.readWord();
            idx.offset++;
        }

        clipX = idx.readByte();
        clipY = idx.readByte();
        width = idx.readWord();
        height = idx.readWord();

        int type = idx.readByte();
        pixels = new byte[width * height];

        if (type == 0) {
            for (int n = 0; n < pixels.length; n++) {
                pixels[n] = data.readByteSigned();
            }
        } else if (type == 1) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    pixels[x + y * width] = data.readByteSigned();
                }
            }
        }
    }

    public void shrink() {
        clipWidth /= 2;
        clipHeight /= 2;

        byte[] newPixels = new byte[clipWidth * clipHeight];
        int i = 0;
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                newPixels[(k + clipX >> 1) + (j + clipY >> 1) * clipWidth] = pixels[i++];
            }
        }

        pixels = newPixels;
        width = clipWidth;
        height = clipHeight;
        clipX = 0;
        clipY = 0;
    }

    public void crop() {
        if (width == clipWidth && height == clipHeight) {
            return;
        }

        byte[] newPixels = new byte[clipWidth * clipHeight];
        int off = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newPixels[x + clipX + (y + clipY) * clipWidth] = pixels[off++];
            }
        }

        pixels = newPixels;
        width = clipWidth;
        height = clipHeight;
        clipX = 0;
        clipY = 0;
    }

    public void flipHorizontally() {
        byte[] flipped = new byte[width * height];
        int off = 0;

        for (int y = 0; y < height; y++) {
            for (int x = width - 1; x >= 0; x--) {
                flipped[off++] = pixels[x + y * width];
            }
        }

        pixels = flipped;
        clipX = clipWidth - width - clipX;
    }

    public void flipVertically() {
        byte[] flipped = new byte[width * height];

        int off = 0;
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                flipped[off++] = pixels[x + y * width];
            }
        }

        pixels = flipped;
        clipY = clipHeight - height - clipY;
    }

    public void translate(int r, int g, int b) {
        for (int i = 0; i < palette.length; i++) {
            int red = palette[i] >> 16 & 0xff;
            red += r;
            if (red < 0)
                red = 0;
            else if (red > 255)
                red = 255;
            int green = palette[i] >> 8 & 0xff;
            green += g;
            if (green < 0)
                green = 0;
            else if (green > 255)
                green = 255;
            int blue = palette[i] & 0xff;
            blue += b;
            if (blue < 0)
                blue = 0;
            else if (blue > 255)
                blue = 255;
            palette[i] = (red << 16) + (green << 8) + blue;
        }
    }

    public void draw(int y, int x) {
        x += clipX;
        y += clipY;

        int dstOff = x + y * Draw2D.width;
        int srcOff = 0;

        int h = height;
        int w = width;
        
        int dstStep = Draw2D.width - w;
        int srcStep = 0;
        
        if (y < Draw2D.top) {
            int trim = Draw2D.top - y;
            h -= trim;
            y = Draw2D.top;
            srcOff += trim * w;
            dstOff += trim * Draw2D.width;
        }

        if (y + h > Draw2D.bottom) {
            h -= (y + h) - Draw2D.bottom;
        }

        if (x < Draw2D.left) {
            int trim = Draw2D.left - x;
            w -= trim;
            x = Draw2D.left;
            srcOff += trim;
            dstOff += trim;
            srcStep += trim;
            dstStep += trim;
        }

        if (x + w > Draw2D.right) {
            int trim = (x + w) - Draw2D.right;
            w -= trim;
            srcStep += trim;
            dstStep += trim;
        }

        if (w > 0 && h > 0) {
            copyImage(Draw2D.dest, srcOff, srcStep, pixels, h, w, dstOff, dstStep, palette);
        }
    }

    public void copyImage(int[] dst, int srcOff, int srcStep, byte[] src, int h, int w,
                          int dstOff, int dstStep, int[] palette) {
        int hw = -(w >> 2);
        w = -(w & 3);
        
        for (int y = -h; y < 0; y++) {
            for (int x = hw; x < 0; x++) {
                byte p = src[srcOff++];
                if (p != 0)
                    dst[dstOff++] = palette[p & 0xff];
                else
                    dstOff++;
                p = src[srcOff++];
                if (p != 0)
                    dst[dstOff++] = palette[p & 0xff];
                else
                    dstOff++;
                p = src[srcOff++];
                if (p != 0)
                    dst[dstOff++] = palette[p & 0xff];
                else
                    dstOff++;
                p = src[srcOff++];
                if (p != 0)
                    dst[dstOff++] = palette[p & 0xff];
                else
                    dstOff++;
            }

            for (int x = w; x < 0; x++) {
                byte p = src[srcOff++];
                if (p != 0)
                    dst[dstOff++] = palette[p & 0xff];
                else
                    dstOff++;
            }

            dstOff += dstStep;
            srcOff += srcStep;
        }
    }

    public byte[] pixels;
    public int[] palette;
    public int width;
    public int height;
    public int clipX;
    public int clipY;
    public int clipWidth;
    public int clipHeight;
}
