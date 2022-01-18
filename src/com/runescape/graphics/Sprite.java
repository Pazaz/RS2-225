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

    public Sprite(FileArchive fileArchive, String name, int index) {
        Buffer data = new Buffer(fileArchive.read(name + ".dat", null));
        Buffer idx = new Buffer(fileArchive.read("index.dat", null));
        idx.offset = data.readWord();
        cropW = idx.readWord();
        cropH = idx.readWord();
        int j = idx.readByte();
        int[] ai = new int[j];
        for (int k = 0; k < j - 1; k++) {
            ai[k + 1] = idx.readSWord();
            if (ai[k + 1] == 0)
                ai[k + 1] = 1;
        }

        for (int l = 0; l < index; l++) {
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
        pixels = new int[j1];
        if (i1 == 0) {
            for (int k1 = 0; k1 < j1; k1++)
                pixels[k1] = ai[data.readByte()];
        } else if (i1 == 1) {
            for (int l1 = 0; l1 < width; l1++) {
                for (int i2 = 0; i2 < height; i2++)
                    pixels[l1 + i2 * width] = ai[data.readByte()];
            }
        }
    }

    public void prepare() {
        Draw2D.prepare(width, pixels, height);
    }

    public void translate(int i, int j, int k) {
        for (int l = 0; l < pixels.length; l++) {
            int i1 = pixels[l];
            if (i1 != 0) {
                int j1 = i1 >> 16 & 0xff;
                j1 += i;
                if (j1 < 1)
                    j1 = 1;
                else if (j1 > 255)
                    j1 = 255;
                int k1 = i1 >> 8 & 0xff;
                k1 += j;
                if (k1 < 1)
                    k1 = 1;
                else if (k1 > 255)
                    k1 = 255;
                int l1 = i1 & 0xff;
                l1 += k;
                if (l1 < 1)
                    l1 = 1;
                else if (l1 > 255)
                    l1 = 255;
                pixels[l] = (j1 << 16) + (k1 << 8) + l1;
            }
        }
    }

    public void drawOpaque(int j, int k) {
        j += clipX;
        k += clipY;
        int l = j + k * Draw2D.width;
        int i1 = 0;
        int j1 = height;
        int k1 = width;
        int l1 = Draw2D.width - k1;
        int i2 = 0;
        if (k < Draw2D.top) {
            int j2 = Draw2D.top - k;
            j1 -= j2;
            k = Draw2D.top;
            i1 += j2 * k1;
            l += j2 * Draw2D.width;
        }
        if (k + j1 > Draw2D.bottom)
            j1 -= (k + j1) - Draw2D.bottom;
        if (j < Draw2D.left) {
            int k2 = Draw2D.left - j;
            k1 -= k2;
            j = Draw2D.left;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (j + k1 > Draw2D.right) {
            int l2 = (j + k1) - Draw2D.right;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (k1 <= 0 || j1 <= 0)
            return;
        copyImage(pixels, l1, j1, i1, i2, l, k1, Draw2D.dest);
    }

    public void copyImage(int[] ai, int j, int k, int l, int i1, int j1,
                          int k1, int[] ai1) {
        int l1 = -(k1 >> 2);
        k1 = -(k1 & 3);
        for (int i2 = -k; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                ai1[j1++] = ai[l++];
                ai1[j1++] = ai[l++];
                ai1[j1++] = ai[l++];
                ai1[j1++] = ai[l++];
            }

            for (int l2 = k1; l2 < 0; l2++)
                ai1[j1++] = ai[l++];

            j1 += j;
            l += i1;
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
            copyImage(Draw2D.dest, pixels, 0, l, k, j1, i1, k1, l1);
        }
    }

    public void copyImage(int[] ai, int[] ai1, int i, int j, int k, int l, int i1,
                          int j1, int k1) {
        int l1 = -(l >> 2);
        l = -(l & 3);
        for (int i2 = -i1; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                i = ai1[j++];
                if (i != 0)
                    ai[k++] = i;
                else
                    k++;
                i = ai1[j++];
                if (i != 0)
                    ai[k++] = i;
                else
                    k++;
                i = ai1[j++];
                if (i != 0)
                    ai[k++] = i;
                else
                    k++;
                i = ai1[j++];
                if (i != 0)
                    ai[k++] = i;
                else
                    k++;
            }

            for (int k2 = l; k2 < 0; k2++) {
                i = ai1[j++];
                if (i != 0)
                    ai[k++] = i;
                else
                    k++;
            }

            k += j1;
            j += k1;
        }

    }

    public void draw(int i, int j, int k, int i1) {
        try {
            int j1 = width;
            int k1 = height;
            int l1 = 0;
            int i2 = 0;
            int j2 = (j1 << 16) / k;
            int k2 = (k1 << 16) / i;
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
            int j3 = i1 + j * Draw2D.width;
            int k3 = Draw2D.width - k;
            if (j < Draw2D.top) {
                int l3 = Draw2D.top - j;
                i -= l3;
                j = 0;
                j3 += l3 * Draw2D.width;
                i2 += k2 * l3;
            }
            if (j + i > Draw2D.bottom)
                i -= (j + i) - Draw2D.bottom;
            if (i1 < Draw2D.left) {
                int i4 = Draw2D.left - i1;
                k -= i4;
                i1 = 0;
                j3 += i4;
                l1 += j2 * i4;
                k3 += i4;
            }
            if (i1 + k > Draw2D.right) {
                int j4 = (i1 + k) - Draw2D.right;
                k -= j4;
                k3 += j4;
            }
            copyImage(l1, j2, Draw2D.dest, k2, i2, pixels, k3, j3, i, j1, k);
        } catch (Exception _ex) {
            System.out.println("error in sprite clipping routine");
        }
    }

    public void copyImage(int i, int j, int[] ai, int l, int i1,
                          int[] ai1, int k1, int l1, int i2, int j2, int k2) {
        try {
            int i3 = i;
            for (int j3 = -i2; j3 < 0; j3++) {
                int k3 = (i1 >> 16) * j2;
                for (int l3 = -k2; l3 < 0; l3++) {
                    int k = ai1[(i >> 16) + k3];
                    if (k != 0)
                        ai[l1++] = k;
                    else
                        l1++;
                    i += j;
                }

                i1 += l;
                i = i3;
                l1 += k1;
            }
        } catch (Exception _ex) {
            System.out.println("error in plot_scale");
        }
    }

    public void draw(int i, int j, int k) {
        j += clipX;
        k += clipY;
        int i1 = j + k * Draw2D.width;
        int j1 = 0;
        int k1 = height;
        int l1 = width;
        int i2 = Draw2D.width - l1;
        int j2 = 0;
        if (k < Draw2D.top) {
            int k2 = Draw2D.top - k;
            k1 -= k2;
            k = Draw2D.top;
            j1 += k2 * l1;
            i1 += k2 * Draw2D.width;
        }
        if (k + k1 > Draw2D.bottom)
            k1 -= (k + k1) - Draw2D.bottom;
        if (j < Draw2D.left) {
            int l2 = Draw2D.left - j;
            l1 -= l2;
            j = Draw2D.left;
            j1 += l2;
            i1 += l2;
            j2 += l2;
            i2 += l2;
        }
        if (j + l1 > Draw2D.right) {
            int i3 = (j + l1) - Draw2D.right;
            l1 -= i3;
            j2 += i3;
            i2 += i3;
        }
        if (l1 > 0 && k1 > 0) {
            copyPixelsAlpha(i1, pixels, i, k1, Draw2D.dest, j1, l1, i2, j2);
        }
    }

    public void copyPixelsAlpha(int i, int[] ai, int k, int l, int[] ai1, int i1,
                                int j1, int k1, int l1) {
        int i2 = 256 - k;
        for (int j2 = -l; j2 < 0; j2++) {
            for (int k2 = -j1; k2 < 0; k2++) {
                int j = ai[i1++];
                if (j != 0) {
                    int l2 = ai1[i];
                    ai1[i++] = ((j & 0xff00ff) * k + (l2 & 0xff00ff) * i2 & 0xff00ff00)
                        + ((j & 0xff00) * k + (l2 & 0xff00) * i2 & 0xff0000) >> 8;
                } else {
                    i++;
                }
            }

            i += k1;
            i1 += l1;
        }
    }

    public void drawRotatedMasked(int i, int j, int[] ai, int k, int l, int i1, int j1,
                                  int k1, int l1, int[] ai1) {
        try {
            int i2 = -j / 2;
            int j2 = -k / 2;
            int k2 = (int) (Math.sin((double) i / 326.11000000000001D) * 65536D);
            int l2 = (int) (Math.cos((double) i / 326.11000000000001D) * 65536D);
            k2 = k2 * i1 >> 8;
            l2 = l2 * i1 >> 8;
            int i3 = (j1 << 16) + (j2 * k2 + i2 * l2);
            int j3 = (l << 16) + (j2 * l2 - i2 * k2);
            int k3 = k1 + l1 * Draw2D.width;
            for (l1 = 0; l1 < k; l1++) {
                int l3 = ai[l1];
                int i4 = k3 + l3;
                int j4 = i3 + l2 * l3;
                int k4 = j3 - k2 * l3;
                for (k1 = -ai1[l1]; k1 < 0; k1++) {
                    Draw2D.dest[i4++] = pixels[(j4 >> 16) + (k4 >> 16) * width];
                    j4 += l2;
                    k4 -= k2;
                }

                i3 += k2;
                j3 += l2;
                k3 += Draw2D.width;
            }
        } catch (Exception _ex) {
        }
    }

    public void drawMasked(IndexedSprite indexedSprite, int i, int j) {
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
            copyPixelsMasked(j1, l1, i1, l, Draw2D.dest, pixels, k, indexedSprite.pixels, k1);
        }
    }

    public void copyPixelsMasked(int i, int j, int i1, int j1, int[] ai,
                                 int[] ai1, int k1, byte[] abyte0, int l1) {
        int i2 = -(i >> 2);
        i = -(i & 3);
        for (int j2 = -i1; j2 < 0; j2++) {
            int k;
            for (int k2 = i2; k2 < 0; k2++) {
                k = ai1[j1++];
                if (k != 0 && abyte0[k1] == 0)
                    ai[k1++] = k;
                else
                    k1++;
                k = ai1[j1++];
                if (k != 0 && abyte0[k1] == 0)
                    ai[k1++] = k;
                else
                    k1++;
                k = ai1[j1++];
                if (k != 0 && abyte0[k1] == 0)
                    ai[k1++] = k;
                else
                    k1++;
                k = ai1[j1++];
                if (k != 0 && abyte0[k1] == 0)
                    ai[k1++] = k;
                else
                    k1++;
            }

            for (int l2 = i; l2 < 0; l2++) {
                k = ai1[j1++];
                if (k != 0 && abyte0[k1] == 0)
                    ai[k1++] = k;
                else
                    k1++;
            }

            k1 += l1;
            j1 += j;
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
