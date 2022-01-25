package com.runescape.graphics;

import com.runescape.cache.FileArchive;
import com.runescape.util.Buffer;

import java.awt.*;
import java.awt.image.PixelGrabber;

public class Sprite extends Draw2D {

    public Sprite(int w, int h) {
        pixels = new int[w * h];
        width = cropW = w;
        height = cropH = h;
        clipX = clipY = 0;
    }

    public Sprite(byte[] src, Component c) {
        try {
            Image image = Toolkit.getDefaultToolkit().createImage(src);
            MediaTracker mediatracker = new MediaTracker(c);
            mediatracker.addImage(image, 0);
            mediatracker.waitForAll();
            width = image.getWidth(c);
            height = image.getHeight(c);
            cropW = width;
            cropH = height;
            clipX = 0;
            clipY = 0;
            pixels = new int[width * height];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
            pixelgrabber.grabPixels();
        } catch (Exception _ex) {
            System.out.println("Error converting jpg");
        }
    }

    public Sprite(FileArchive archive, String name, int index) {
        Buffer data = new Buffer(archive.read(name + ".dat", null));
        Buffer idx = new Buffer(archive.read("index.dat", null));

        idx.offset = data.readWord();
        cropW = idx.readWord();
        cropH = idx.readWord();

        int[] palette = new int[idx.readByte()];
        for (int k = 0; k < palette.length - 1; k++) {
            palette[k + 1] = idx.readSWord();
            if (palette[k + 1] == 0)
                palette[k + 1] = 1;
        }

        for (int i = 0; i < index; i++) {
            idx.offset += 2;
            data.offset += idx.readWord() * idx.readWord();
            idx.offset++;
        }

        clipX = idx.readByte();
        clipY = idx.readByte();
        width = idx.readWord();
        height = idx.readWord();

        int type = idx.readByte();
        int len = width * height;
        pixels = new int[len];

        if (type == 0) {
            for (int i = 0; i < len; i++) {
                pixels[i] = palette[data.readByte()];
            }
        } else if (type == 1) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    pixels[x + y * width] = palette[data.readByte()];
                }
            }
        }
    }

    public void prepare() {
        Draw2D.prepare(width, pixels, height);
    }

    public void translate(int r, int g, int b) {
        for (int i = 0; i < pixels.length; i++) {
            int rgb = pixels[i];
            if (rgb != 0) {
                int red = rgb >> 16 & 0xff;
                red += r;
                if (red < 1)
                    red = 1;
                else if (red > 255)
                    red = 255;
                int green = rgb >> 8 & 0xff;
                green += g;
                if (green < 1)
                    green = 1;
                else if (green > 255)
                    green = 255;
                int blue = rgb & 0xff;
                blue += b;
                if (blue < 1)
                    blue = 1;
                else if (blue > 255)
                    blue = 255;
                pixels[i] = (red << 16) + (green << 8) + blue;
            }
        }
    }

    public void drawOpaque(int x, int y) {
        x += clipX;
        y += clipY;
        int dstOff = x + y * Draw2D.width;
        int srcOff = 0;
        int h = height;
        int w = width;
        int dstStep = Draw2D.width - w;
        int srcStep = 0;
        if (y < Draw2D.top) {
            int cutoff = Draw2D.top - y;
            h -= cutoff;
            y = Draw2D.top;
            srcOff += cutoff * w;
            dstOff += cutoff * Draw2D.width;
        }
        if (y + h > Draw2D.bottom)
            h -= (y + h) - Draw2D.bottom;
        if (x < Draw2D.left) {
            int cutoff = Draw2D.left - x;
            w -= cutoff;
            x = Draw2D.left;
            srcOff += cutoff;
            dstOff += cutoff;
            srcStep += cutoff;
            dstStep += cutoff;
        }
        if (x + w > Draw2D.right) {
            int cutoff = (x + w) - Draw2D.right;
            w -= cutoff;
            srcStep += cutoff;
            dstStep += cutoff;
        }
        if (w <= 0 || h <= 0)
            return;
        copyImage(pixels, dstStep, h, srcOff, srcStep, dstOff, w, Draw2D.dest);
    }

    public void copyImage(int[] src, int dstStep, int h, int srcOff, int srcStep, int dstOff,
                          int w, int[] dst) {
        int hw = -(w >> 2);
        w = -(w & 3);
        for (int y = -h; y < 0; y++) {
            for (int x = hw; x < 0; x++) {
                dst[dstOff++] = src[srcOff++];
                dst[dstOff++] = src[srcOff++];
                dst[dstOff++] = src[srcOff++];
                dst[dstOff++] = src[srcOff++];
            }

            for (int x = w; x < 0; x++)
                dst[dstOff++] = src[srcOff++];

            dstOff += dstStep;
            srcOff += srcStep;
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
            int cutoff = Draw2D.top - y;
            h -= cutoff;
            y = Draw2D.top;
            srcOff += cutoff * w;
            dstOff += cutoff * Draw2D.width;
        }
        if (y + h > Draw2D.bottom)
            h -= (y + h) - Draw2D.bottom;
        if (x < Draw2D.left) {
            int cutoff = Draw2D.left - x;
            w -= cutoff;
            x = Draw2D.left;
            srcOff += cutoff;
            dstOff += cutoff;
            srcStep += cutoff;
            dstStep += cutoff;
        }
        if (x + w > Draw2D.right) {
            int cutoff = (x + w) - Draw2D.right;
            w -= cutoff;
            srcStep += cutoff;
            dstStep += cutoff;
        }
        if (w > 0 && h > 0) {
            copyImage(Draw2D.dest, pixels, srcOff, dstOff, w, h, dstStep, srcStep);
        }
    }

    public void copyImage(int[] dst, int[] src, int srcOff, int dstOff, int w, int h,
                          int dstStep, int srcStep) {
        int hw = -(w >> 2);
        w = -(w & 3);
        for (int x = -h; x < 0; x++) {
            int rgb;
            for (int y = hw; y < 0; y++) {
                rgb = src[srcOff++];
                if (rgb != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                rgb = src[srcOff++];
                if (rgb != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                rgb = src[srcOff++];
                if (rgb != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                rgb = src[srcOff++];
                if (rgb != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
            }

            for (int y = w; y < 0; y++) {
                rgb = src[srcOff++];
                if (rgb != 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
            }

            dstOff += dstStep;
            srcOff += srcStep;
        }
    }

    public void draw(int i, int j, int k, int i1) {
        try {
            int w = width;
            int h = height;
            int l1 = 0;
            int i2 = 0;
            int j2 = (w << 16) / k;
            int k2 = (h << 16) / i;
            int l2 = cropW;
            int i3 = cropH;
            i1 += ((clipX * k + l2) - 1) / l2;
            j += ((clipY * i + i3) - 1) / i3;
            if ((clipX * k) % l2 != 0)
                l1 = (l2 - (clipX * k) % l2 << 16) / k;
            if ((clipY * i) % i3 != 0)
                i2 = (i3 - (clipY * i) % i3 << 16) / i;
            k = (k * (width - (l1 >> 16))) / l2;
            i = (i * (height - (i2 >> 16))) / i3;
            int dstOff = i1 + j * Draw2D.width;
            int dstStep = Draw2D.width - k;
            if (j < Draw2D.top) {
                int l3 = Draw2D.top - j;
                i -= l3;
                j = 0;
                dstOff += l3 * Draw2D.width;
                i2 += k2 * l3;
            }
            if (j + i > Draw2D.bottom)
                i -= (j + i) - Draw2D.bottom;
            if (i1 < Draw2D.left) {
                int i4 = Draw2D.left - i1;
                k -= i4;
                i1 = 0;
                dstOff += i4;
                l1 += j2 * i4;
                dstStep += i4;
            }
            if (i1 + k > Draw2D.right) {
                int j4 = (i1 + k) - Draw2D.right;
                k -= j4;
                dstStep += j4;
            }
            copyImage(l1, j2, Draw2D.dest, k2, i2, pixels, dstStep, dstOff, i, w, k);
        } catch (Exception _ex) {
            System.out.println("error in sprite clipping routine");
        }
    }

    public void copyImage(int i, int j, int[] dst, int l, int i1,
                          int[] src, int dstStep, int dstOff, int h, int j2, int w) {
        try {
            int i3 = i;
            for (int y = -h; y < 0; y++) {
                int k3 = (i1 >> 16) * j2;
                for (int x = -w; x < 0; x++) {
                    int rgb = src[(i >> 16) + k3];
                    if (rgb != 0)
                        dst[dstOff++] = rgb;
                    else
                        dstOff++;
                    i += j;
                }

                i1 += l;
                i = i3;
                dstOff += dstStep;
            }
        } catch (Exception _ex) {
            System.out.println("error in plot_scale");
        }
    }

    public void draw(int alpha, int x, int y) {
        x += clipX;
        y += clipY;
        int dstOff = x + y * Draw2D.width;
        int srcOff = 0;
        int h = height;
        int w = width;
        int dstStep = Draw2D.width - w;
        int srcStep = 0;
        if (y < Draw2D.top) {
            int cutoff = Draw2D.top - y;
            h -= cutoff;
            y = Draw2D.top;
            srcOff += cutoff * w;
            dstOff += cutoff * Draw2D.width;
        }
        if (y + h > Draw2D.bottom)
            h -= (y + h) - Draw2D.bottom;
        if (x < Draw2D.left) {
            int cutoff = Draw2D.left - x;
            w -= cutoff;
            x = Draw2D.left;
            srcOff += cutoff;
            dstOff += cutoff;
            srcStep += cutoff;
            dstStep += cutoff;
        }
        if (x + w > Draw2D.right) {
            int cutoff = (x + w) - Draw2D.right;
            w -= cutoff;
            srcStep += cutoff;
            dstStep += cutoff;
        }
        if (w > 0 && h > 0) {
            copyPixelsAlpha(dstOff, pixels, alpha, h, Draw2D.dest, srcOff, w, dstStep, srcStep);
        }
    }

    public void copyPixelsAlpha(int dstOff, int[] src, int alpha, int h, int[] dst, int srcOff,
                                int w, int dstStep, int srcStep) {
        int opacity = 256 - alpha;
        for (int y = -h; y < 0; y++) {
            for (int x = -w; x < 0; x++) {
                int rgb = src[srcOff++];
                if (rgb != 0) {
                    int dstRGB = dst[dstOff];
                    dst[dstOff++] = ((rgb & 0xff00ff) * alpha + (dstRGB & 0xff00ff) * opacity & 0xff00ff00)
                        + ((rgb & 0xff00) * alpha + (dstRGB & 0xff00) * opacity & 0xff0000) >> 8;
                } else {
                    dstOff++;
                }
            }

            dstOff += dstStep;
            srcOff += srcStep;
        }
    }

    public void drawRotatedMasked(int theta, int w, int[] lineStart, int h, int anchorY, int zoom, int anchorX,
                                  int x, int y, int[] lineWidth) {
        try {
            int centerX = -w / 2;
            int centerY = -h / 2;
            int sin = (int) (Math.sin((double) theta / 326.11000000000001D) * 65536D);
            int cos = (int) (Math.cos((double) theta / 326.11000000000001D) * 65536D);
            sin = sin * zoom >> 8;
            cos = cos * zoom >> 8;
            int originX = (anchorX << 16) + (centerY * sin + centerX * cos);
            int originY = (anchorY << 16) + (centerY * cos - centerX * sin);
            int origin = x + y * Draw2D.width;
            for (y = 0; y < h; y++) {
                int start = lineStart[y];
                int dstOff = origin + start;
                int dstX = originX + cos * start;
                int dstY = originY - sin * start;
                for (x = -lineWidth[y]; x < 0; x++) {
                    int x1 = dstX >> 16;
                    int y1 = dstY >> 16;
                    int x2 = x1 + 1;
                    int y2 = y1 + 1;
                    int sampleColor1 = pixels[x1 + y1 * this.width];
                    int sampleColor2 = pixels[x2 + y1 * this.width];
                    int sampleColor3 = pixels[x1 + y2 * this.width];
                    int sampleColor4 = pixels[x2 + y2 * this.width];
                    int x1Distance = (dstX >> 8) - (x1 << 8);
                    int y1Distance = (dstY >> 8) - (y1 << 8);
                    int x2Distance = (x2 << 8) - (dstX >> 8);
                    int y2Distance = (y2 << 8) - (dstY >> 8);
                    int sampleAlpha1 = x2Distance * y2Distance;
                    int sampleAlpha2 = x1Distance * y2Distance;
                    int sampleAlpha3 = x2Distance * y1Distance;
                    int sampleAlpha4 = x1Distance * y1Distance;
                    int red = (sampleColor1 >> 16 & 0xff) * sampleAlpha1 + (sampleColor2 >> 16 & 0xff) * sampleAlpha2 + (sampleColor3 >> 16 & 0xff) * sampleAlpha3 + (sampleColor4 >> 16 & 0xff) * sampleAlpha4 & 0xff0000;
                    int green = (sampleColor1 >> 8 & 0xff) * sampleAlpha1 + (sampleColor2 >> 8 & 0xff) * sampleAlpha2 + (sampleColor3 >> 8 & 0xff) * sampleAlpha3 + (sampleColor4 >> 8 & 0xff) * sampleAlpha4 >> 8 & 0xff00;
                    int blue = (sampleColor1 & 0xff) * sampleAlpha1 + (sampleColor2 & 0xff) * sampleAlpha2 + (sampleColor3 & 0xff) * sampleAlpha3 + (sampleColor4 & 0xff) * sampleAlpha4 >> 16;
                    Draw2D.dest[dstOff++] = red | green | blue;
                    dstX += cos;
                    dstY -= sin;
                }

                originX += sin;
                originY += cos;
                origin += Draw2D.width;
            }
        } catch (Exception _ex) {
        }
    }

    public void drawMasked(IndexedSprite mask, int y, int x) {
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
        if (y + h > Draw2D.bottom)
            h -= (y + h) - Draw2D.bottom;
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
            copyPixelsMasked(w, srcStep, h, srcOff, Draw2D.dest, pixels, dstOff, mask.pixels, dstStep);
        }
    }

    public void copyPixelsMasked(int w, int srcStep, int h, int srcOff, int[] dst,
                                 int[] src, int dstOff, byte[] mask, int dstStep) {
        int quarterW = -(w >> 2);
        w = -(w & 3);
        for (int y = -h; y < 0; y++) {
            int rgb;
            for (int x = quarterW; x < 0; x++) {
                rgb = src[srcOff++];
                if (rgb != 0 && mask[dstOff] == 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                rgb = src[srcOff++];
                if (rgb != 0 && mask[dstOff] == 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                rgb = src[srcOff++];
                if (rgb != 0 && mask[dstOff] == 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
                rgb = src[srcOff++];
                if (rgb != 0 && mask[dstOff] == 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
            }

            for (int x = w; x < 0; x++) {
                rgb = src[srcOff++];
                if (rgb != 0 && mask[dstOff] == 0)
                    dst[dstOff++] = rgb;
                else
                    dstOff++;
            }

            dstOff += dstStep;
            srcOff += srcStep;
        }
    }

    public int[] pixels;
    public int width;
    public int height;
    public int clipX;
    public int clipY;
    public int cropW;
    public int cropH;
}
