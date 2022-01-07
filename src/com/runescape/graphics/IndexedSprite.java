package com.runescape.graphics;

import com.runescape.cache.FileArchive;
import com.runescape.util.Buffer;

public class IndexedSprite extends Draw2D {

    public IndexedSprite(FileArchive fileArchive, String s, int i) {
        aBoolean1472 = false;
        anInt1473 = 45861;
        aByte1474 = 5;
        aBoolean1475 = true;
        Buffer class38_sub2_sub3 = new Buffer(fileArchive.read(s + ".dat", null));
        Buffer class38_sub2_sub3_1 = new Buffer(
                fileArchive.read("index.dat", null));
        class38_sub2_sub3_1.offset = class38_sub2_sub3.readWord();
        clipWidth = class38_sub2_sub3_1.readWord();
        anInt1483 = class38_sub2_sub3_1.readWord();
        int j = class38_sub2_sub3_1.readByte();
        anIntArray1477 = new int[j];
        for (int k = 0; k < j - 1; k++)
            anIntArray1477[k + 1] = class38_sub2_sub3_1.readSWord();

        for (int l = 0; l < i; l++) {
            class38_sub2_sub3_1.offset += 2;
            class38_sub2_sub3.offset += class38_sub2_sub3_1.readWord() * class38_sub2_sub3_1.readWord();
            class38_sub2_sub3_1.offset++;
        }

        anInt1480 = class38_sub2_sub3_1.readByte();
        anInt1481 = class38_sub2_sub3_1.readByte();
        width = class38_sub2_sub3_1.readWord();
        anInt1479 = class38_sub2_sub3_1.readWord();
        int i1 = class38_sub2_sub3_1.readByte();
        int j1 = width * anInt1479;
        pixels = new byte[j1];
        if (i1 == 0) {
            for (int k1 = 0; k1 < j1; k1++)
                pixels[k1] = class38_sub2_sub3.readByteSigned();

            return;
        }
        if (i1 == 1) {
            for (int l1 = 0; l1 < width; l1++) {
                for (int i2 = 0; i2 < anInt1479; i2++)
                    pixels[l1 + i2 * width] = class38_sub2_sub3.readByteSigned();

            }

        }
    }

    public void shrink(boolean flag) {
        clipWidth /= 2;
        anInt1483 /= 2;
        byte[] abyte0 = new byte[clipWidth * anInt1483];
        int i = 0;
        for (int j = 0; j < anInt1479; j++) {
            for (int k = 0; k < width; k++)
                abyte0[(k + anInt1480 >> 1) + (j + anInt1481 >> 1) * clipWidth] = pixels[i++];

        }

        if (flag)
            anInt1473 = 197;
        pixels = abyte0;
        width = clipWidth;
        anInt1479 = anInt1483;
        anInt1480 = 0;
        anInt1481 = 0;
    }

    public void crop(int i) {
        if (width == clipWidth && anInt1479 == anInt1483)
            return;
        byte[] abyte0 = new byte[clipWidth * anInt1483];
        int j = 0;
        if (i != 0)
            aBoolean1472 = !aBoolean1472;
        for (int k = 0; k < anInt1479; k++) {
            for (int l = 0; l < width; l++)
                abyte0[l + anInt1480 + (k + anInt1481) * clipWidth] = pixels[j++];

        }

        pixels = abyte0;
        width = clipWidth;
        anInt1479 = anInt1483;
        anInt1480 = 0;
        anInt1481 = 0;
    }

    public void method416(int i) {
        byte[] abyte0 = new byte[width * anInt1479];
        int j = 0;
        for (int k = 0; k < anInt1479; k++) {
            for (int l = width - 1; l >= 0; l--)
                abyte0[j++] = pixels[l + k * width];

        }

        pixels = abyte0;
        for (anInt1480 = clipWidth - width - anInt1480; i >= 0; )
            return;

    }

    public void method417(byte byte0) {
        byte[] abyte0 = new byte[width * anInt1479];
        if (byte0 != -74) {
            for (int i = 1; i > 0; i++)
                ;
        }
        int j = 0;
        for (int k = anInt1479 - 1; k >= 0; k--) {
            for (int l = 0; l < width; l++)
                abyte0[j++] = pixels[l + k * width];

        }

        pixels = abyte0;
        anInt1481 = anInt1483 - anInt1479 - anInt1481;
    }

    public void method418(int i, int j, int k, boolean flag) {
        for (int l = 0; l < anIntArray1477.length; l++) {
            int i1 = anIntArray1477[l] >> 16 & 0xff;
            i1 += i;
            if (i1 < 0)
                i1 = 0;
            else if (i1 > 255)
                i1 = 255;
            int k1 = anIntArray1477[l] >> 8 & 0xff;
            k1 += j;
            if (k1 < 0)
                k1 = 0;
            else if (k1 > 255)
                k1 = 255;
            int l1 = anIntArray1477[l] & 0xff;
            l1 += k;
            if (l1 < 0)
                l1 = 0;
            else if (l1 > 255)
                l1 = 255;
            anIntArray1477[l] = (i1 << 16) + (k1 << 8) + l1;
        }

        if (!flag) {
            for (int j1 = 1; j1 > 0; j1++)
                ;
        }
    }

    public void method419(int i, int j, boolean flag) {
        j += anInt1480;
        i += anInt1481;
        int k = j + i * Draw2D.width;
        int l = 0;
        int i1 = anInt1479;
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
        if (j1 <= 0 || i1 <= 0) {
            return;
        } else {
            method420(Draw2D.dest, l, l1, pixels, i1, 0, j1, k, k1, anIntArray1477);
            if (!flag)
                ;
            return;
        }
    }

    public void method420(int[] ai, int i, int j, byte[] abyte0, int k, int l, int i1,
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

    public boolean aBoolean1472;
    public int anInt1473;
    public byte aByte1474;
    public boolean aBoolean1475;
    public byte[] pixels;
    public int[] anIntArray1477;
    public int width;
    public int anInt1479;
    public int anInt1480;
    public int anInt1481;
    public int clipWidth;
    public int anInt1483;
}
