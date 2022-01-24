package com.runescape.graphics;

import com.runescape.cache.FileArchive;
import com.runescape.util.Buffer;

public class IndexedSprite extends Draw2D {

    public IndexedSprite(FileArchive fileArchive, String s, int i) {
        Buffer data = new Buffer(fileArchive.read(s + ".dat", null));
        Buffer idx = new Buffer(fileArchive.read("index.dat", null));
        idx.offset = data.readWord();
        clipWidth = idx.readWord();
        clipHeight = idx.readWord();
        int j = idx.readByte();
        palette = new int[j];
        for (int k = 0; k < j - 1; k++)
            palette[k + 1] = idx.readSWord();

        for (int l = 0; l < i; l++) {
            idx.offset += 2;
            data.offset += idx.readWord() * idx.readWord();
            idx.offset++;
        }

        clipX = idx.readByte();
        clipY = idx.readByte();
        width = idx.readWord();
        height = idx.readWord();
        int i1 = idx.readByte();
        int j1 = width * height;
        pixels = new byte[j1];
        if (i1 == 0) {
            for (int k1 = 0; k1 < j1; k1++)
                pixels[k1] = data.readByteSigned();

            return;
        }
        if (i1 == 1) {
            for (int l1 = 0; l1 < width; l1++) {
                for (int i2 = 0; i2 < height; i2++)
                    pixels[l1 + i2 * width] = data.readByteSigned();
            }
        }
    }

    public void shrink() {
        clipWidth /= 2;
        clipHeight /= 2;

        byte[] abyte0 = new byte[clipWidth * clipHeight];
        int i = 0;
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++)
                abyte0[(k + clipX >> 1) + (j + clipY >> 1) * clipWidth] = pixels[i++];
        }

        pixels = abyte0;
        width = clipWidth;
        height = clipHeight;
        clipX = 0;
        clipY = 0;
    }

    public void crop() {
        if (width == clipWidth && height == clipHeight)
            return;
        byte[] abyte0 = new byte[clipWidth * clipHeight];
        int j = 0;
        for (int k = 0; k < height; k++) {
            for (int l = 0; l < width; l++)
                abyte0[l + clipX + (k + clipY) * clipWidth] = pixels[j++];
        }

        pixels = abyte0;
        width = clipWidth;
        height = clipHeight;
        clipX = 0;
        clipY = 0;
    }

    public void flipHorizontally() {
        byte[] abyte0 = new byte[width * height];
        int j = 0;
        for (int k = 0; k < height; k++) {
            for (int l = width - 1; l >= 0; l--)
                abyte0[j++] = pixels[l + k * width];
        }

        pixels = abyte0;
        clipX = clipWidth - width - clipX;
    }

    public void flipVertically() {
        byte[] abyte0 = new byte[width * height];
        int j = 0;
        for (int k = height - 1; k >= 0; k--) {
            for (int l = 0; l < width; l++)
                abyte0[j++] = pixels[l + k * width];
        }

        pixels = abyte0;
        clipY = clipHeight - height - clipY;
    }

    public void translate(int i, int j, int k) {
        for (int l = 0; l < palette.length; l++) {
            int i1 = palette[l] >> 16 & 0xff;
            i1 += i;
            if (i1 < 0)
                i1 = 0;
            else if (i1 > 255)
                i1 = 255;
            int k1 = palette[l] >> 8 & 0xff;
            k1 += j;
            if (k1 < 0)
                k1 = 0;
            else if (k1 > 255)
                k1 = 255;
            int l1 = palette[l] & 0xff;
            l1 += k;
            if (l1 < 0)
                l1 = 0;
            else if (l1 > 255)
                l1 = 255;
            palette[l] = (i1 << 16) + (k1 << 8) + l1;
        }
    }

    public void draw(int i, int j) {
        j += clipX;
        i += clipY;
        int k = j + i * Draw2D.width;
        int l = 0;
        int i1 = height;
        int j1 = width;
        int k1 = Draw2D.width - j1;
        int l1 = 0;
        if (i < Draw2D.top) {
            int i2 = Draw2D.top - i;
            i1 -= i2;
            i = Draw2D.top;
            l += i2 * j1;
            k += i2 * Draw2D.width;
        }
        if (i + i1 > Draw2D.bottom)
            i1 -= (i + i1) - Draw2D.bottom;
        if (j < Draw2D.left) {
            int j2 = Draw2D.left - j;
            j1 -= j2;
            j = Draw2D.left;
            l += j2;
            k += j2;
            l1 += j2;
            k1 += j2;
        }
        if (j + j1 > Draw2D.right) {
            int k2 = (j + j1) - Draw2D.right;
            j1 -= k2;
            l1 += k2;
            k1 += k2;
        }
        if (j1 > 0 && i1 > 0) {
            copyImage(Draw2D.dest, l, l1, pixels, i1, 0, j1, k, k1, palette);
        }
    }

    public void copyImage(int[] ai, int i, int j, byte[] abyte0, int k, int l, int i1,
                          int j1, int k1, int[] ai1) {
        int l1 = -(i1 >> 2);
        i1 = -(i1 & 3);
        if (l != 0)
            return;
        for (int i2 = -k; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                byte byte0 = abyte0[i++];
                if (byte0 != 0)
                    ai[j1++] = ai1[byte0 & 0xff];
                else
                    j1++;
                byte0 = abyte0[i++];
                if (byte0 != 0)
                    ai[j1++] = ai1[byte0 & 0xff];
                else
                    j1++;
                byte0 = abyte0[i++];
                if (byte0 != 0)
                    ai[j1++] = ai1[byte0 & 0xff];
                else
                    j1++;
                byte0 = abyte0[i++];
                if (byte0 != 0)
                    ai[j1++] = ai1[byte0 & 0xff];
                else
                    j1++;
            }

            for (int k2 = i1; k2 < 0; k2++) {
                byte byte1 = abyte0[i++];
                if (byte1 != 0)
                    ai[j1++] = ai1[byte1 & 0xff];
                else
                    j1++;
            }

            j1 += k1;
            i += j;
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
