package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;

public class IndexedSprite extends Draw2D {

    public byte[] pixels;
    public int[] palette;
    public int width;
    public int height;
    public int clipX;
    public int clipY;
    public int clipWidth;
    public int clipHeight;

    public IndexedSprite(byte[] src, int width, int height) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(src);
            BufferedImage image = ImageIO.read(bis);
            bis.close();
            this.width = width;
            this.height = height;
            clipX = 0;
            clipY = 0;
            clipWidth = width;
            clipHeight = height;
            int[] data = new int[width * height];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, width, height, data, 0, width);
            pixelgrabber.grabPixels();
            this.pixels = new byte[width * height];
            // generate palette
            palette = new int[0xFF];
            byte last = 1;
            for (int i = 0; i < data.length; ++i) {
                int color = data[i] & 0xFFFFFF;
                int pointer = -1;
                for (int j = 0; j < palette.length; ++j) {
                    if (palette[j] == color) {
                        pointer = j;
                        break;
                    }
                }
                if (pointer == -1) {
                    pointer = last;
                    palette[pointer] = color;
                    last++;
                }
                palette[pointer] = color;
                pixels[i] = (byte)pointer;
            }
        } catch (Exception _ex) {
            _ex.printStackTrace();
            System.out.println("Error converting image");
        }
    }

    public IndexedSprite(FileArchive archive, String name, int index) {
        Buffer data = new Buffer(archive.read(name + ".dat"));
        Buffer idx = new Buffer(archive.read("index.dat"));

        idx.offset = data.g2();
        clipWidth = idx.g2();
        clipHeight = idx.g2();

        palette = new int[idx.g1()];
        for (int n = 0; n < palette.length - 1; n++) {
            palette[n + 1] = idx.g3();
        }

        for (int n = 0; n < index; n++) {
            idx.offset += 2;
            data.offset += idx.g2() * idx.g2();
            idx.offset++;
        }

        clipX = idx.g1();
        clipY = idx.g1();
        width = idx.g2();
        height = idx.g2();

        int type = idx.g1();
        pixels = new byte[width * height];

        if (type == 0) {
            for (int n = 0; n < pixels.length; n++) {
                pixels[n] = data.g1s();
            }
        } else if (type == 1) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    pixels[x + y * width] = data.g1s();
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

    public void draw(int x, int y) {
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
            copyImage(w, h, palette, pixels, srcOff, srcStep, Draw2D.dest, dstOff, dstStep);
        }
    }

    public void copyImage(int w, int h, int[] palette, byte[] src, int srcOff, int srcStep, int[] dst,
                          int dstOff, int dstStep) {
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

    public void clip(int i, int k, int l, int i1) {
        try {
            int j1 = width;
            int k1 = height;
            int l1 = 0;
            int i2 = 0;
            int j2 = (j1 << 16) / l;
            int k2 = (k1 << 16) / i1;
            int l2 = clipWidth;
            int i3 = clipHeight;
            j2 = (l2 << 16) / l;
            k2 = (i3 << 16) / i1;
            i += ((clipX * l + l2) - 1) / l2;
            k += ((clipY * i1 + i3) - 1) / i3;
            if ((clipX * l) % l2 != 0)
                l1 = (l2 - (clipX * l) % l2 << 16) / l;
            if ((clipY * i1) % i3 != 0)
                i2 = (i3 - (clipY * i1) % i3 << 16) / i1;
            l = (l * (width - (l1 >> 16))) / l2;
            i1 = (i1 * (height - (i2 >> 16))) / i3;
            int j3 = i + k * Draw2D.width;
            int k3 = Draw2D.width - l;
            if (k < Draw2D.top) {
                int l3 = Draw2D.top - k;
                i1 -= l3;
                k = 0;
                j3 += l3 * Draw2D.width;
                i2 += k2 * l3;
            }
            if (k + i1 > Draw2D.bottom)
                i1 -= (k + i1) - Draw2D.bottom;
            if (i < Draw2D.left) {
                int i4 = Draw2D.left - i;
                l -= i4;
                i = 0;
                j3 += i4;
                l1 += j2 * i4;
                k3 += i4;
            }
            if (i + l > Draw2D.right) {
                int j4 = (i + l) - Draw2D.right;
                l -= j4;
                k3 += j4;
            }
            plot_scale(Draw2D.dest, pixels, palette, l1, i2, j3, k3, l, i1, j2, k2, j1);
        } catch (Exception exception) {
            System.out.println("error in sprite clipping routine");
        }
    }

    private void plot_scale(int[] ai, byte[] abyte0, int[] ai1, int i, int k, int l, int i1,
                            int j1, int k1, int l1, int i2, int j2) {
        try {
            int k2 = i;
            for (int l2 = -k1; l2 < 0; l2++) {
                int i3 = (k >> 16) * j2;
                for (int j3 = -j1; j3 < 0; j3++) {
                    byte byte0 = abyte0[(i >> 16) + i3];
                    if (byte0 != 0)
                        ai[l++] = ai1[byte0 & 0xff];
                    else
                        l++;
                    i += l1;
                }

                k += i2;
                i = k2;
                l += i1;
            }
        } catch (Exception exception) {
            System.out.println("error in plot_scale");
        }
    }
}
