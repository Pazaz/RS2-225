package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

import java.util.Random;

public class IndexedFont extends Draw2D {

    public IndexedFont(FileArchive archive, String name) {
        pixels = new byte[94][];
        charWidth = new int[94];
        charHeight = new int[94];
        charOffsetX = new int[94];
        charOffsetY = new int[94];
        charSpace = new int[95];
        drawWidth = new int[256];
        random = new Random();
        Buffer data = new Buffer(archive.read(name + ".dat", null));
        Buffer idx = new Buffer(archive.read("index.dat", null));
        idx.offset = data.readWord() + 4;
        int off = idx.readByte();
        if (off > 0)
            idx.offset += 3 * (off - 1);
        for (int n = 0; n < 94; n++) {
            charOffsetX[n] = idx.readByte();
            charOffsetY[n] = idx.readByte();
            int w = charWidth[n] = idx.readWord();
            int h = charHeight[n] = idx.readWord();
            int type = idx.readByte();
            int len = w * h;
            pixels[n] = new byte[len];
            if (type == 0) {
                for (int i2 = 0; i2 < len; i2++)
                    pixels[n][i2] = data.readByteSigned();
            } else if (type == 1) {
                for (int j2 = 0; j2 < w; j2++) {
                    for (int l2 = 0; l2 < h; l2++)
                        pixels[n][j2 + l2 * w] = data.readByteSigned();
                }
            }
            if (h > height)
                height = h;
            charOffsetX[n] = 1;
            charSpace[n] = w + 2;

            int i = 0;
            for (int y = h / 7; y < h; y++)
                i += pixels[n][y * w];
            if (i <= h / 7) {
                charSpace[n]--;
                charOffsetX[n] = 0;
            }

            i = 0;
            for (int y = h / 7; y < h; y++)
                i += pixels[n][(w - 1) + y * w];
            if (i <= h / 7)
                charSpace[n]--;
        }

        charSpace[94] = charSpace[8];
        for (int c = 0; c < 256; c++)
            drawWidth[c] = charSpace[CHAR_LOOKUP[c]];
    }

    public void drawCentered(int y, int rgb, String str, int x) {
        draw(x - stringWidth(str) / 2, y, rgb, str);
    }

    public void drawRightAligned(int y, int rgb, String str, int x, boolean shadow) {
        draw(x - stringWidth(str), y, str, shadow, rgb);
    }

    public void drawCentered(int x, int rgb, boolean shadow, int y, String str) {
        draw(x - stringWidth(str) / 2, y, str, shadow, rgb);
    }

    public int stringWidth(String s) {
        if (s == null) {
            return 0;
        }

        int w = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '@' && i + 4 < s.length() && s.charAt(i + 4) == '@') {
                i += 4;
            } else {
                w += drawWidth[s.charAt(i)];
            }
        }

        return w;
    }

    public void draw(int x, int y, int rgb, String s) {
        if (s == null) {
            return;
        }

        y -= height;
        for (int i = 0; i < s.length(); i++) {
            int c = CHAR_LOOKUP[s.charAt(i)];
            if (c != 94) {
                fillMaskedRect(pixels[c], x + charOffsetX[c], y + charOffsetY[c], charWidth[c], charHeight[c], rgb);
            }
            x += charSpace[c];
        }
    }

    public void draw(int x, int y, int rgb, String str, boolean shadow) {
        if (shadow) {
            draw(x + 1, y + 1, 0x000000, str);
        }
        draw(x, y, rgb, str);
    }

    public void drawCenteredWave(int phase, int x, int y, int rgb, String s) {
        if (s == null)
            return;
        x -= stringWidth(s) / 2;
        y -= height;
        for (int i = 0; i < s.length(); i++) {
            int c = CHAR_LOOKUP[s.charAt(i)];
            if (c != 94) {
                fillMaskedRect(pixels[c], x + charOffsetX[c],
                    y + charOffsetY[c] + (int) (Math.sin((double) i / 2D + (double) phase / 5D) * 5D),
                    charWidth[c], charHeight[c], rgb);
            }
            x += charSpace[c];
        }
    }

    public void draw(int x, int y, String s, boolean shadow, int rgb) {
        if (s == null)
            return;
        y -= height;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '@' && i + 4 < s.length() && s.charAt(i + 4) == '@') {
                rgb = evaluateTag(s.substring(i + 1, i + 4));
                i += 4;
            } else {
                int c = CHAR_LOOKUP[s.charAt(i)];
                if (c != 94) {
                    if (shadow) {
                        fillMaskedRect(pixels[c], x + charOffsetX[c] + 1, y + charOffsetY[c] + 1, charWidth[c], charHeight[c], 0);
                    }
                    fillMaskedRect(pixels[c], x + charOffsetX[c], y + charOffsetY[c], charWidth[c], charHeight[c], rgb);
                }
                x += charSpace[c];
            }
        }
    }

    public void drawTooltip(int seed, boolean shadow, int y, int rgb, String s, int x) {
        if (s == null)
            return;
        random.setSeed(seed);
        int alpha = 192 + (random.nextInt() & 0x1f);
        y -= height;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '@' && i + 4 < s.length() && s.charAt(i + 4) == '@') {
                rgb = evaluateTag(s.substring(i + 1, i + 4));
                i += 4;
            } else {
                int c = CHAR_LOOKUP[s.charAt(i)];
                if (c != 94) {
                    if (shadow) {
                        fillMaskedRect(pixels[c], x + charOffsetX[c] + 1, charHeight[c], 0, y + charOffsetY[c] + 1, 192, charWidth[c]);
                    }

                    fillMaskedRect(pixels[c], x + charOffsetX[c], charHeight[c], rgb, y + charOffsetY[c], alpha, charWidth[c]);
                }
                x += charSpace[c];
                if ((random.nextInt() & 3) == 0)
                    x++;
            }
        }
    }

    public int evaluateTag(String s) {
        if (s.equals("red"))
            return 0xff0000;
        if (s.equals("gre"))
            return 0xff00;
        if (s.equals("blu"))
            return 0xff;
        if (s.equals("yel"))
            return 0xffff00;
        if (s.equals("cya"))
            return 0xffff;
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
            return 0x80;
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

    public void fillMaskedRect(byte[] data, int x, int y, int w, int h, int rgb) {
        int dstOff = x + y * width;
        int dstStep = width - w;
        int srcStep = 0;
        int srcOff = 0;
        if (y < top) {
            int cutoff = top - y;
            h -= cutoff;
            y = top;
            srcOff += cutoff * w;
            dstOff += cutoff * width;
        }
        if (y + h >= bottom)
            h -= ((y + h) - bottom) + 1;
        if (x < left) {
            int cutoff = left - x;
            w -= cutoff;
            x = left;
            srcOff += cutoff;
            dstOff += cutoff;
            srcStep += cutoff;
            dstStep += cutoff;
        }
        if (x + w >= right) {
            int cutoff = ((x + w) - right) + 1;
            w -= cutoff;
            srcStep += cutoff;
            dstStep += cutoff;
        }
        fillMaskedRect(dest, data, rgb, srcOff, dstOff, w, h, dstStep, srcStep);
    }

    public void fillMaskedRect(int[] dst, byte[] src, int rgb, int srcOff, int dstOff, int w, int h,
                               int dstStep, int srcStep) {
        int hw = -(w >> 2);
        w = -(w & 3);

        for (int y = -h; y < 0; y++) {
            for (int x = hw; x < 0; x++) {
                if (src[srcOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                if (src[srcOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                if (src[srcOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                if (src[srcOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
            }

            for (int x = w; x < 0; x++)
                if (src[srcOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;

            dstOff += dstStep;
            srcOff += srcStep;
        }
    }

    public void fillMaskedRect(byte[] mask, int x, int h, int rgb, int y, int alpha, int w) {
        int dstOff = x + y * width;
        int dstStep = width - w;
        int maskStep = 0;
        int maskOff = 0;
        if (y < top) {
            int trim = top - y;
            h -= trim;
            y = top;
            maskOff += trim * w;
            dstOff += trim * width;
        }
        if (y + h >= bottom)
            h -= ((y + h) - bottom) + 1;
        if (x < left) {
            int trim = left - x;
            w -= trim;
            x = left;
            maskOff += trim;
            dstOff += trim;
            maskStep += trim;
            dstStep += trim;
        }
        if (x + w >= right) {
            int trim = ((x + w) - right) + 1;
            w -= trim;
            maskStep += trim;
            dstStep += trim;
        }
        fillMaskedRect(h, dstOff, w, dest, mask, alpha, maskOff, dstStep, maskStep, rgb);
    }

    public void fillMaskedRect(int h, int dstOff, int k, int[] dst, byte[] mask, int alpha, int maskOff,
                               int dstStep, int maskStep, int rgb) {
        rgb = ((rgb & 0xff00ff) * alpha & 0xff00ff00) + ((rgb & 0xff00) * alpha & 0xff0000) >> 8;
        alpha = 256 - alpha;

        for (int y = -h; y < 0; y++) {
            for (int x = -k; x < 0; x++) {
                if (mask[maskOff++] != 0) {
                    int dstRGB = dst[dstOff];
                    dst[dstOff++] = (((dstRGB & 0xff00ff) * alpha & 0xff00ff00) + ((dstRGB & 0xff00) * alpha & 0xff0000) >> 8) + rgb;
                } else {
                    dstOff++;
                }
            }

            dstOff += dstStep;
            maskOff += maskStep;
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
