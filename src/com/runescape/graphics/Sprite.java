package com.runescape.graphics;

import com.runescape.cache.FileArchive;
import com.runescape.util.Buffer;

import java.awt.*;
import java.awt.image.PixelGrabber;

public class Sprite extends Draw2D {

    public Sprite(int i, int j) {
        aBoolean1460 = false;
        aBoolean1462 = false;
        anInt1463 = 15223;
        aByte1464 = 5;
        pixels = new int[i * j];
        anInt1466 = cropW = i;
        anInt1467 = cropH = j;
        anInt1468 = anInt1469 = 0;
    }

    public Sprite(byte[] abyte0, Component component) {
        aBoolean1460 = false;
        aBoolean1462 = false;
        anInt1463 = 15223;
        aByte1464 = 5;
        try {
            Image image = Toolkit.getDefaultToolkit().createImage(abyte0);
            MediaTracker mediatracker = new MediaTracker(component);
            mediatracker.addImage(image, 0);
            mediatracker.waitForAll();
            anInt1466 = image.getWidth(component);
            anInt1467 = image.getHeight(component);
            cropW = anInt1466;
            cropH = anInt1467;
            anInt1468 = 0;
            anInt1469 = 0;
            pixels = new int[anInt1466 * anInt1467];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, anInt1466, anInt1467, pixels, 0,
                    anInt1466);
            pixelgrabber.grabPixels();
            return;
        } catch (Exception _ex) {
            System.out.println("Error converting jpg");
        }
    }

    public Sprite(FileArchive fileArchive, String s, int i) {
        aBoolean1460 = false;
        aBoolean1462 = false;
        anInt1463 = 15223;
        aByte1464 = 5;
        Buffer class38_sub2_sub3 = new Buffer(fileArchive.read(s + ".dat", null));
        Buffer class38_sub2_sub3_1 = new Buffer(
                fileArchive.read("index.dat", null));
        class38_sub2_sub3_1.offset = class38_sub2_sub3.readWord();
        cropW = class38_sub2_sub3_1.readWord();
        cropH = class38_sub2_sub3_1.readWord();
        int j = class38_sub2_sub3_1.readByte();
        int[] ai = new int[j];
        for (int k = 0; k < j - 1; k++) {
            ai[k + 1] = class38_sub2_sub3_1.readSWord();
            if (ai[k + 1] == 0)
                ai[k + 1] = 1;
        }

        for (int l = 0; l < i; l++) {
            class38_sub2_sub3_1.offset += 2;
            class38_sub2_sub3.offset += class38_sub2_sub3_1.readWord() * class38_sub2_sub3_1.readWord();
            class38_sub2_sub3_1.offset++;
        }

        anInt1468 = class38_sub2_sub3_1.readByte();
        anInt1469 = class38_sub2_sub3_1.readByte();
        anInt1466 = class38_sub2_sub3_1.readWord();
        anInt1467 = class38_sub2_sub3_1.readWord();
        int i1 = class38_sub2_sub3_1.readByte();
        int j1 = anInt1466 * anInt1467;
        pixels = new int[j1];
        if (i1 == 0) {
            for (int k1 = 0; k1 < j1; k1++)
                pixels[k1] = ai[class38_sub2_sub3.readByte()];

            return;
        }
        if (i1 == 1) {
            for (int l1 = 0; l1 < anInt1466; l1++) {
                for (int i2 = 0; i2 < anInt1467; i2++)
                    pixels[l1 + i2 * anInt1466] = ai[class38_sub2_sub3.readByte()];

            }

        }
    }

    public void method401(byte byte0) {
        if (byte0 != 62) {
            for (int i = 1; i > 0; i++)
                ;
        }
        Draw2D.init(anInt1466, pixels, -657, anInt1467);
    }

    public void method402(int i, int j, int k, boolean flag) {
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

        if (flag)
            ;
    }

    public void method403(int i, int j, int k) {
        j += anInt1468;
        k += anInt1469;
        int l = j + k * Draw2D.width;
        int i1 = 0;
        int j1 = anInt1467;
        int k1 = anInt1466;
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
        method404(15223, pixels, l1, j1, i1, i2, l, k1, Draw2D.dest);
        if (i != 34676)
            anInt1461 = 117;
    }

    public void method404(int i, int[] ai, int j, int k, int l, int i1, int j1,
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

        if (i != anInt1463) {
            for (int k2 = 1; k2 > 0; k2++)
                ;
        }
    }

    public void method405(int i, int j, boolean flag) {
        if (flag)
            anInt1463 = 32;
        j += anInt1468;
        i += anInt1469;
        int k = j + i * Draw2D.width;
        int l = 0;
        int i1 = anInt1467;
        int j1 = anInt1466;
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
            method406(Draw2D.dest, pixels, 0, l, k, j1, i1, k1, l1);
            return;
        }
    }

    public void method406(int[] ai, int[] ai1, int i, int j, int k, int l, int i1,
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

    public void draw(int i, int j, int k, int l, int i1) {
        if (l != 17713)
            return;
        try {
            int j1 = anInt1466;
            int k1 = anInt1467;
            int l1 = 0;
            int i2 = 0;
            int j2 = (j1 << 16) / k;
            int k2 = (k1 << 16) / i;
            int l2 = cropW;
            int i3 = cropH;
            j2 = (l2 << 16) / k;
            k2 = (i3 << 16) / i;
            i1 += ((anInt1468 * k + l2) - 1) / l2;
            j += ((anInt1469 * i + i3) - 1) / i3;
            if ((anInt1468 * k) % l2 != 0)
                l1 = (l2 - (anInt1468 * k) % l2 << 16) / k;
            if ((anInt1469 * i) % i3 != 0)
                i2 = (i3 - (anInt1469 * i) % i3 << 16) / i;
            k = (k * (anInt1466 - (l1 >> 16))) / l2;
            i = (i * (anInt1467 - (i2 >> 16))) / i3;
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
            method408(l1, j2, Draw2D.dest, 0, k2, i2, 0, pixels, k3, j3, i, j1, k);
            return;
        } catch (Exception _ex) {
            System.out.println("error in sprite clipping routine");
        }
    }

    public void method408(int i, int j, int[] ai, int k, int l, int i1, int j1,
                          int[] ai1, int k1, int l1, int i2, int j2, int k2) {
        if (j1 != 0) {
            for (int l2 = 1; l2 > 0; l2++)
                ;
        }
        try {
            int i3 = i;
            for (int j3 = -i2; j3 < 0; j3++) {
                int k3 = (i1 >> 16) * j2;
                for (int l3 = -k2; l3 < 0; l3++) {
                    k = ai1[(i >> 16) + k3];
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

            return;
        } catch (Exception _ex) {
            System.out.println("error in plot_scale");
        }
    }

    public void method409(int i, int j, int k, byte byte0) {
        if (byte0 != -26) {
            for (int l = 1; l > 0; l++)
                ;
        }
        j += anInt1468;
        k += anInt1469;
        int i1 = j + k * Draw2D.width;
        int j1 = 0;
        int k1 = anInt1467;
        int l1 = anInt1466;
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
        if (l1 <= 0 || k1 <= 0) {
            return;
        } else {
            method410(i1, 0, pixels, i, k1, Draw2D.dest, j1, (byte) 8, l1, i2, j2);
            return;
        }
    }

    public void method410(int i, int j, int[] ai, int k, int l, int[] ai1, int i1,
                          byte byte0, int j1, int k1, int l1) {
        int i2 = 256 - k;
        if (byte0 != 8)
            aBoolean1460 = !aBoolean1460;
        for (int j2 = -l; j2 < 0; j2++) {
            for (int k2 = -j1; k2 < 0; k2++) {
                j = ai[i1++];
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

    public void method411(int i, int j, int[] ai, int k, int l, int i1, int j1,
                          int k1, int l1, boolean flag, int[] ai1) {
        if (flag)
            aBoolean1462 = !aBoolean1462;
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
                    Draw2D.dest[i4++] = pixels[(j4 >> 16) + (k4 >> 16) * anInt1466];
                    j4 += l2;
                    k4 -= k2;
                }

                i3 += k2;
                j3 += l2;
                k3 += Draw2D.width;
            }

            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void method412(IndexedSprite indexedSprite, int i, int j, byte byte0) {
        j += anInt1468;
        i += anInt1469;
        int k = j + i * Draw2D.width;
        int l = 0;
        if (byte0 != -15)
            return;
        int i1 = anInt1467;
        int j1 = anInt1466;
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
            method413(j1, l1, 0, -478, i1, l, Draw2D.dest, pixels, k,
                    indexedSprite.aByteArray1476, k1);
            return;
        }
    }

    public void method413(int i, int j, int k, int l, int i1, int j1, int[] ai,
                          int[] ai1, int k1, byte[] abyte0, int l1) {
        if (l >= 0)
            return;
        int i2 = -(i >> 2);
        i = -(i & 3);
        for (int j2 = -i1; j2 < 0; j2++) {
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

    public boolean aBoolean1460;
    public int anInt1461;
    public boolean aBoolean1462;
    public int anInt1463;
    public byte aByte1464;
    public int[] pixels;
    public int anInt1466;
    public int anInt1467;
    public int anInt1468;
    public int anInt1469;
    public int cropW;
    public int cropH;
}
