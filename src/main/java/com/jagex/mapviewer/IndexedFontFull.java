package com.jagex.mapviewer;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

import java.util.Random;

public class IndexedFontFull extends Draw2D {

    public int stringWidth(String str) {
        if (str == null) {
            return 0;
        }

        int w = 0;
        for (int n = 0; n < str.length(); n++) {
            w += charAdvance[str.charAt(n)];
        }

        return w;
    }

    public void drawString(String str, int x, int y, int rgb) {
        if (str == null) {
            return;
        }
        
        y -= height;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                fillMaskedRect(charMask[c], x + charOffsetX[c], y + charOffsetY[c], charMaskWidth[c], charMaskHeight[c], rgb);
            }
            x += charAdvance[c];
        }
    }

    public IndexedFontFull(FileArchive archive, String name, boolean quill) {
        Buffer dat = new Buffer(archive.read((new StringBuilder()).append(name).append(".dat").toString(), null));
        Buffer idx = new Buffer(archive.read("index.dat", null));
        idx.offset = dat.readWord() + 4;
        
        int k = idx.readByte();
        if (k > 0) {
            idx.offset += 3 * (k - 1);
        }
        
        for (int c = 0; c < 256; c++) {
            charOffsetX[c] = idx.readByte();
            charOffsetY[c] = idx.readByte();
            
            int w = charMaskWidth[c] = idx.readWord();
            int h = charMaskHeight[c] = idx.readWord();
            int storeOrder = idx.readByte();

            int maskLength = w * h;
            charMask[c] = new byte[maskLength];

            if (storeOrder == 0) {
                for (int n = 0; n < maskLength; n++) {
                    charMask[c][n] = dat.readByteSigned();
                }
            } else if (storeOrder == 1) {
                for (int y = 0; y < w; y++) {
                    for (int x = 0; x < h; x++) {
                        charMask[c][y + x * w] = dat.readByteSigned();
                    }
                }
            }

            if (h > height && c < 128) {
                height = h;
            }

            charOffsetX[c] = 1;
            charAdvance[c] = w + 2;

            int acc = 0;
            for (int y = h / 7; y < h; y++) {
                acc += charMask[c][y * w];
            }

            if (acc <= h / 7) {
                charAdvance[c]--;
                charOffsetX[c] = 0;
            }

            acc = 0;
            for (int y = h / 7; y < h; y++) {
                acc += charMask[c][(w - 1) + y * w];
            }

            if (acc <= h / 7) {
                charAdvance[c]--;
            }
        }

        if (quill) {
            charAdvance[' '] = charAdvance['I'];
        } else {
            charAdvance[' '] = charAdvance['i'];
        }
    }

    public void drawStringRight(String s, int x, int y, int rgb) {
        drawString(s, x - stringWidth(s), y, rgb);
    }

    public void drawStringCenter(String s, int x, int y, int rgb) {
        drawString(s, x - stringWidth(s) / 2, y, rgb);
    }

    private void fillMaskedRect(byte[] mask, int x, int y, int w, int h, int rgb) {
        int dstOff = x + y * Draw2D.width;
        int dstStep = Draw2D.width - w;
        int maskStep = 0;
        int maskOff = 0;

        if (y < Draw2D.bbh) {
            int trim = Draw2D.bbh - y;
            h -= trim;
            y = Draw2D.bbh;
            maskOff += trim * w;
            dstOff += trim * Draw2D.width;
        }

        if (y + h >= Draw2D.bbi) {
            h -= ((y + h) - Draw2D.bbi) + 1;
        }

        if (x < Draw2D.bbj) {
            int trim = Draw2D.bbj - x;
            w -= trim;
            x = Draw2D.bbj;
            maskOff += trim;
            dstOff += trim;
            maskStep += trim;
            dstStep += trim;
        }

        if (x + w >= Draw2D.bbk) {
            int trim = ((x + w) - Draw2D.bbk) + 1;
            w -= trim;
            maskStep += trim;
            dstStep += trim;
        }

        if (w > 0 && h > 0) {
            fillMaskedRect(Draw2D.pixels, mask, rgb, maskOff, dstOff, w, h, dstStep, maskStep);
        }
    }

    private void fillMaskedRect(int[] dst, byte[] mask, int rgb, int mastOff, int dstOff, int w, int h,
                                int dstStep, int maskStep) {
        int halfW = -(w >> 2);
        w = -(w & 3);
        
        for (int y = -h; y < 0; y++) {
            for (int x = halfW; x < 0; x++) {
                if (mask[mastOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                if (mask[mastOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                if (mask[mastOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                if (mask[mastOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
            }

            for (int x = w; x < 0; x++)
                if (mask[mastOff++] != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;

            dstOff += dstStep;
            mastOff += maskStep;
        }
    }

    byte[][] charMask = new byte[256][];
    int[] charMaskWidth = new int[256];
    int[] charMaskHeight = new int[256];
    int[] charOffsetX = new int[256];
    int[] charOffsetY = new int[256];
    public int[] charAdvance = new int[256];
    public int height = 0;
    Random random = new Random();
    boolean strikethrough = false;
}
