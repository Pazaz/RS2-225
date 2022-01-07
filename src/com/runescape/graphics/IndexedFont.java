package com.runescape.graphics;

import com.runescape.cache.FileArchive;
import com.runescape.util.Buffer;

import java.util.Random;

public class IndexedFont extends Draw2D {

    public IndexedFont(FileArchive fileArchive, String s, int i) {
        pixels = new byte[94][];
        charWidth = new int[94];
        charHeight = new int[94];
        charOffsetX = new int[94];
        charOffsetY = new int[94];
        charSpace = new int[95];
        drawWidth = new int[256];
        random = new Random();
        Buffer data = new Buffer(fileArchive.read(s + ".dat", null));
        Buffer idx = new Buffer(
                fileArchive.read("index.dat", null));
        idx.offset = data.readWord() + 4;
        int j = idx.readByte();
        if (j > 0)
            idx.offset += 3 * (j - 1);
        for (int k = 0; k < 94; k++) {
            charOffsetX[k] = idx.readByte();
            charOffsetY[k] = idx.readByte();
            int l = charWidth[k] = idx.readWord();
            int j1 = charHeight[k] = idx.readWord();
            int k1 = idx.readByte();
            int l1 = l * j1;
            pixels[k] = new byte[l1];
            if (k1 == 0) {
                for (int i2 = 0; i2 < l1; i2++)
                    pixels[k][i2] = data.readByteSigned();

            } else if (k1 == 1) {
                for (int j2 = 0; j2 < l; j2++) {
                    for (int l2 = 0; l2 < j1; l2++)
                        pixels[k][j2 + l2 * l] = data.readByteSigned();

                }

            }
            if (j1 > height)
                height = j1;
            charOffsetX[k] = 1;
            charSpace[k] = l + 2;
            int k2 = 0;
            for (int i3 = j1 / 7; i3 < j1; i3++)
                k2 += pixels[k][i3 * l];

            if (k2 <= j1 / 7) {
                charSpace[k]--;
                charOffsetX[k] = 0;
            }
            k2 = 0;
            for (int j3 = j1 / 7; j3 < j1; j3++)
                k2 += pixels[k][(l - 1) + j3 * l];

            if (k2 <= j1 / 7)
                charSpace[k]--;
        }

        i = 9 / i;
        charSpace[94] = charSpace[8];
        for (int i1 = 0; i1 < 256; i1++)
            drawWidth[i1] = charSpace[CHAR_LOOKUP[i1]];

    }

    public void drawRightAligned(int i, int j, String s, int k) {
        draw(k - stringWidth(s) / 2, i, j, s);
    }

    public void drawCentered(int i, int j, boolean flag, int k, String s) {
        draw(i - stringWidth(s) / 2, k, s, flag, j);
    }

    public int stringWidth(String s) {
        if (s == null) {
            return 0;
        }

        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '@' && j + 4 < s.length() && s.charAt(j + 4) == '@') {
                j += 4;
            } else {
                i += drawWidth[s.charAt(j)];
            }
        }

        return i;
    }

    public void draw(int i, int j, int k, String s) {
        if (s == null) {
            return;
        }

        j -= height;
        for (int l = 0; l < s.length(); l++) {
            int i1 = CHAR_LOOKUP[s.charAt(l)];
            if (i1 != 94) {
                fillMaskedRect(pixels[i1], i + charOffsetX[i1], j + charOffsetY[i1], charWidth[i1], charHeight[i1], k);
            }
            i += charSpace[i1];
        }
    }

    public void drawCenteredWave(int i, int j, int k, int l, String s) {
        if (s == null)
            return;
        j -= stringWidth(s) / 2;
        k -= height;
        for (int j1 = 0; j1 < s.length(); j1++) {
            int k1 = CHAR_LOOKUP[s.charAt(j1)];
            if (k1 != 94)
                fillMaskedRect(pixels[k1], j + charOffsetX[k1],
                        k + charOffsetY[k1] + (int) (Math.sin((double) j1 / 2D + (double) i / 5D) * 5D),
                        charWidth[k1], charHeight[k1], l);
            j += charSpace[k1];
        }

    }

    public void draw(int i, int k, String s, boolean shadow, int l) {
        if (s == null)
            return;
        k -= height;
        for (int i1 = 0; i1 < s.length(); i1++) {
            if (s.charAt(i1) == '@' && i1 + 4 < s.length() && s.charAt(i1 + 4) == '@') {
                l = evaluateTag(s.substring(i1 + 1, i1 + 4));
                i1 += 4;
            } else {
                int j1 = CHAR_LOOKUP[s.charAt(i1)];
                if (j1 != 94) {
                    if (shadow)
                        fillMaskedRect(pixels[j1], i + charOffsetX[j1] + 1, k + charOffsetY[j1] + 1,
                                charWidth[j1], charHeight[j1], 0);
                    fillMaskedRect(pixels[j1], i + charOffsetX[j1], k + charOffsetY[j1],
                            charWidth[j1], charHeight[j1], l);
                }
                i += charSpace[j1];
            }
        }
    }

    public void drawTooltip(int i, boolean flag, int j, int k, String s, int l) {
        if (s == null)
            return;
        random.setSeed(i);
        int i1 = 192 + (random.nextInt() & 0x1f);
        j -= height;
        for (int j1 = 0; j1 < s.length(); j1++)
            if (s.charAt(j1) == '@' && j1 + 4 < s.length() && s.charAt(j1 + 4) == '@') {
                k = evaluateTag(s.substring(j1 + 1, j1 + 4));
                j1 += 4;
            } else {
                int k1 = CHAR_LOOKUP[s.charAt(j1)];
                if (k1 != 94) {
                    if (flag)
                        fillMaskedRect(pixels[k1], l + charOffsetX[k1] + 1, charHeight[k1], 0,
                                j + charOffsetY[k1] + 1, 192, charWidth[k1]);
                    fillMaskedRect(pixels[k1], l + charOffsetX[k1], charHeight[k1], k,
                            j + charOffsetY[k1], i1, charWidth[k1]);
                }
                l += charSpace[k1];
                if ((random.nextInt() & 3) == 0)
                    l++;
            }

    }

    public int evaluateTag(String s) {
        if (s.equals("red"))
            return 0xff0000;
        if (s.equals("gre"))
            return 65280;
        if (s.equals("blu"))
            return 255;
        if (s.equals("yel"))
            return 0xffff00;
        if (s.equals("cya"))
            return 65535;
        if (s.equals("mag"))
            return 0xff00ff;
        if (s.equals("whi"))
            return 0xffffff;
        if (s.equals("bla"))
            return 0;
        if (s.equals("lre"))
            return 0xff9040;
        if (s.equals("dre"))
            return 0x800000;
        if (s.equals("dbl"))
            return 128;
        if (s.equals("or1"))
            return 0xffb000;
        if (s.equals("or2"))
            return 0xff7000;
        if (s.equals("or3"))
            return 0xff3000;
        if (s.equals("gr1"))
            return 0xc0ff00;
        if (s.equals("gr2"))
            return 0x80ff00;
        return !s.equals("gr3") ? 0 : 0x40ff00;
    }

    public void fillMaskedRect(byte[] abyte0, int i, int j, int k, int l, int i1) {
        int j1 = i + j * Draw2D.width;
        int k1 = Draw2D.width - k;
        int l1 = 0;
        int i2 = 0;
        if (j < Draw2D.top) {
            int j2 = Draw2D.top - j;
            l -= j2;
            j = Draw2D.top;
            i2 += j2 * k;
            j1 += j2 * Draw2D.width;
        }
        if (j + l >= Draw2D.bottom)
            l -= ((j + l) - Draw2D.bottom) + 1;
        if (i < Draw2D.left) {
            int k2 = Draw2D.left - i;
            k -= k2;
            i = Draw2D.left;
            i2 += k2;
            j1 += k2;
            l1 += k2;
            k1 += k2;
        }
        if (i + k >= Draw2D.right) {
            int l2 = ((i + k) - Draw2D.right) + 1;
            k -= l2;
            l1 += l2;
            k1 += l2;
        }
        fillMaskedRect(Draw2D.dest, abyte0, i1, i2, j1, k, l, k1, l1);
    }

    public void fillMaskedRect(int[] ai, byte[] abyte0, int i, int j, int k, int l, int i1,
                               int j1, int k1) {
        int l1 = -(l >> 2);
        l = -(l & 3);
        for (int i2 = -i1; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;
            }

            for (int k2 = l; k2 < 0; k2++)
                if (abyte0[j++] != 0)
                    ai[k++] = i;
                else
                    k++;

            k += j1;
            j += k1;
        }

    }

    public void fillMaskedRect(byte[] abyte0, int i, int j, int k, int l, int i1, int j1) {
        int k1 = i + l * Draw2D.width;
        int l1 = Draw2D.width - j1;
        int i2 = 0;
        int j2 = 0;
        if (l < Draw2D.top) {
            int k2 = Draw2D.top - l;
            j -= k2;
            l = Draw2D.top;
            j2 += k2 * j1;
            k1 += k2 * Draw2D.width;
        }
        if (l + j >= Draw2D.bottom)
            j -= ((l + j) - Draw2D.bottom) + 1;
        if (i < Draw2D.left) {
            int l2 = Draw2D.left - i;
            j1 -= l2;
            i = Draw2D.left;
            j2 += l2;
            k1 += l2;
            i2 += l2;
            l1 += l2;
        }
        if (i + j1 >= Draw2D.right) {
            int i3 = ((i + j1) - Draw2D.right) + 1;
            j1 -= i3;
            i2 += i3;
            l1 += i3;
        }
        fillMaskedRect(j, k1, j1, Draw2D.dest, abyte0, i1, j2, l1, i2, k);
    }

    public void fillMaskedRect(int i, int j, int k, int[] ai, byte[] abyte0, int l, int i1,
                               int j1, int k1, int l1) {
        l1 = ((l1 & 0xff00ff) * l & 0xff00ff00) + ((l1 & 0xff00) * l & 0xff0000) >> 8;
        l = 256 - l;
        for (int j2 = -i; j2 < 0; j2++) {
            for (int k2 = -k; k2 < 0; k2++)
                if (abyte0[i1++] != 0) {
                    int l2 = ai[j];
                    ai[j++] = (((l2 & 0xff00ff) * l & 0xff00ff00) + ((l2 & 0xff00) * l & 0xff0000) >> 8) + l1;
                } else {
                    j++;
                }

            j += j1;
            i1 += k1;
        }
    }

    public byte[][] pixels;
    public int[] charWidth;
    public int[] charHeight;
    public int[] charOffsetX;
    public int[] charOffsetY;
    public int[] charSpace;
    public int[] drawWidth;
    public int height;
    public Random random;
    public static int[] CHAR_LOOKUP;

    static {
        CHAR_LOOKUP = new int[256];
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
        for (int i = 0; i < 256; i++) {
            int j = s.indexOf(i);
            if (j == -1) {
                j = 74;
            }
            CHAR_LOOKUP[i] = j;
        }
    }
}
