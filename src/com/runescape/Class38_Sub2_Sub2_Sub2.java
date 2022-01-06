package com.runescape;

import java.awt.*;
import java.awt.image.PixelGrabber;

public class Class38_Sub2_Sub2_Sub2 extends Class38_Sub2_Sub2 {

    public Class38_Sub2_Sub2_Sub2(int i, int j) {
        aBoolean1460 = false;
        aBoolean1462 = false;
        anInt1463 = 15223;
        aByte1464 = 5;
        anIntArray1465 = new int[i * j];
        anInt1466 = anInt1470 = i;
        anInt1467 = anInt1471 = j;
        anInt1468 = anInt1469 = 0;
    }

    public Class38_Sub2_Sub2_Sub2(byte[] abyte0, Component component) {
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
            anInt1470 = anInt1466;
            anInt1471 = anInt1467;
            anInt1468 = 0;
            anInt1469 = 0;
            anIntArray1465 = new int[anInt1466 * anInt1467];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, anInt1466, anInt1467, anIntArray1465, 0,
                    anInt1466);
            pixelgrabber.grabPixels();
            return;
        } catch (Exception _ex) {
            System.out.println("Error converting jpg");
        }
    }

    public Class38_Sub2_Sub2_Sub2(Class39 class39, String s, int i) {
        aBoolean1460 = false;
        aBoolean1462 = false;
        anInt1463 = 15223;
        aByte1464 = 5;
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, class39.method474(s + ".dat", null, (byte) 2));
        Class38_Sub2_Sub3 class38_sub2_sub3_1 = new Class38_Sub2_Sub3(363,
                class39.method474("index.dat", null, (byte) 2));
        class38_sub2_sub3_1.anInt1329 = class38_sub2_sub3.method448();
        anInt1470 = class38_sub2_sub3_1.method448();
        anInt1471 = class38_sub2_sub3_1.method448();
        int j = class38_sub2_sub3_1.method446();
        int[] ai = new int[j];
        for (int k = 0; k < j - 1; k++) {
            ai[k + 1] = class38_sub2_sub3_1.method450();
            if (ai[k + 1] == 0)
                ai[k + 1] = 1;
        }

        for (int l = 0; l < i; l++) {
            class38_sub2_sub3_1.anInt1329 += 2;
            class38_sub2_sub3.anInt1329 += class38_sub2_sub3_1.method448() * class38_sub2_sub3_1.method448();
            class38_sub2_sub3_1.anInt1329++;
        }

        anInt1468 = class38_sub2_sub3_1.method446();
        anInt1469 = class38_sub2_sub3_1.method446();
        anInt1466 = class38_sub2_sub3_1.method448();
        anInt1467 = class38_sub2_sub3_1.method448();
        int i1 = class38_sub2_sub3_1.method446();
        int j1 = anInt1466 * anInt1467;
        anIntArray1465 = new int[j1];
        if (i1 == 0) {
            for (int k1 = 0; k1 < j1; k1++)
                anIntArray1465[k1] = ai[class38_sub2_sub3.method446()];

            return;
        }
        if (i1 == 1) {
            for (int l1 = 0; l1 < anInt1466; l1++) {
                for (int i2 = 0; i2 < anInt1467; i2++)
                    anIntArray1465[l1 + i2 * anInt1466] = ai[class38_sub2_sub3.method446()];

            }

        }
    }

    public void method401(byte byte0) {
        if (byte0 != 62) {
            for (int i = 1; i > 0; i++)
                ;
        }
        Class38_Sub2_Sub2.init(anInt1466, anIntArray1465, -657, anInt1467);
    }

    public void method402(int i, int j, int k, boolean flag) {
        for (int l = 0; l < anIntArray1465.length; l++) {
            int i1 = anIntArray1465[l];
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
                anIntArray1465[l] = (j1 << 16) + (k1 << 8) + l1;
            }
        }

        if (flag)
            ;
    }

    public void method403(int i, int j, int k) {
        j += anInt1468;
        k += anInt1469;
        int l = j + k * Class38_Sub2_Sub2.anInt1309;
        int i1 = 0;
        int j1 = anInt1467;
        int k1 = anInt1466;
        int l1 = Class38_Sub2_Sub2.anInt1309 - k1;
        int i2 = 0;
        if (k < Class38_Sub2_Sub2.anInt1311) {
            int j2 = Class38_Sub2_Sub2.anInt1311 - k;
            j1 -= j2;
            k = Class38_Sub2_Sub2.anInt1311;
            i1 += j2 * k1;
            l += j2 * Class38_Sub2_Sub2.anInt1309;
        }
        if (k + j1 > Class38_Sub2_Sub2.anInt1312)
            j1 -= (k + j1) - Class38_Sub2_Sub2.anInt1312;
        if (j < Class38_Sub2_Sub2.anInt1313) {
            int k2 = Class38_Sub2_Sub2.anInt1313 - j;
            k1 -= k2;
            j = Class38_Sub2_Sub2.anInt1313;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (j + k1 > Class38_Sub2_Sub2.anInt1314) {
            int l2 = (j + k1) - Class38_Sub2_Sub2.anInt1314;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (k1 <= 0 || j1 <= 0)
            return;
        method404(15223, anIntArray1465, l1, j1, i1, i2, l, k1, Class38_Sub2_Sub2.anIntArray1308);
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
        int k = j + i * Class38_Sub2_Sub2.anInt1309;
        int l = 0;
        int i1 = anInt1467;
        int j1 = anInt1466;
        int k1 = Class38_Sub2_Sub2.anInt1309 - j1;
        int l1 = 0;
        if (i < Class38_Sub2_Sub2.anInt1311) {
            int i2 = Class38_Sub2_Sub2.anInt1311 - i;
            i1 -= i2;
            i = Class38_Sub2_Sub2.anInt1311;
            l += i2 * j1;
            k += i2 * Class38_Sub2_Sub2.anInt1309;
        }
        if (i + i1 > Class38_Sub2_Sub2.anInt1312)
            i1 -= (i + i1) - Class38_Sub2_Sub2.anInt1312;
        if (j < Class38_Sub2_Sub2.anInt1313) {
            int j2 = Class38_Sub2_Sub2.anInt1313 - j;
            j1 -= j2;
            j = Class38_Sub2_Sub2.anInt1313;
            l += j2;
            k += j2;
            l1 += j2;
            k1 += j2;
        }
        if (j + j1 > Class38_Sub2_Sub2.anInt1314) {
            int k2 = (j + j1) - Class38_Sub2_Sub2.anInt1314;
            j1 -= k2;
            l1 += k2;
            k1 += k2;
        }
        if (j1 <= 0 || i1 <= 0) {
            return;
        } else {
            method406(Class38_Sub2_Sub2.anIntArray1308, anIntArray1465, 0, l, k, j1, i1, k1, l1);
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

    public void method407(int i, int j, int k, int l, int i1) {
        if (l != 17713)
            return;
        try {
            int j1 = anInt1466;
            int k1 = anInt1467;
            int l1 = 0;
            int i2 = 0;
            int j2 = (j1 << 16) / k;
            int k2 = (k1 << 16) / i;
            int l2 = anInt1470;
            int i3 = anInt1471;
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
            int j3 = i1 + j * Class38_Sub2_Sub2.anInt1309;
            int k3 = Class38_Sub2_Sub2.anInt1309 - k;
            if (j < Class38_Sub2_Sub2.anInt1311) {
                int l3 = Class38_Sub2_Sub2.anInt1311 - j;
                i -= l3;
                j = 0;
                j3 += l3 * Class38_Sub2_Sub2.anInt1309;
                i2 += k2 * l3;
            }
            if (j + i > Class38_Sub2_Sub2.anInt1312)
                i -= (j + i) - Class38_Sub2_Sub2.anInt1312;
            if (i1 < Class38_Sub2_Sub2.anInt1313) {
                int i4 = Class38_Sub2_Sub2.anInt1313 - i1;
                k -= i4;
                i1 = 0;
                j3 += i4;
                l1 += j2 * i4;
                k3 += i4;
            }
            if (i1 + k > Class38_Sub2_Sub2.anInt1314) {
                int j4 = (i1 + k) - Class38_Sub2_Sub2.anInt1314;
                k -= j4;
                k3 += j4;
            }
            method408(l1, j2, Class38_Sub2_Sub2.anIntArray1308, 0, k2, i2, 0, anIntArray1465, k3, j3, i, j1, k);
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
        int i1 = j + k * Class38_Sub2_Sub2.anInt1309;
        int j1 = 0;
        int k1 = anInt1467;
        int l1 = anInt1466;
        int i2 = Class38_Sub2_Sub2.anInt1309 - l1;
        int j2 = 0;
        if (k < Class38_Sub2_Sub2.anInt1311) {
            int k2 = Class38_Sub2_Sub2.anInt1311 - k;
            k1 -= k2;
            k = Class38_Sub2_Sub2.anInt1311;
            j1 += k2 * l1;
            i1 += k2 * Class38_Sub2_Sub2.anInt1309;
        }
        if (k + k1 > Class38_Sub2_Sub2.anInt1312)
            k1 -= (k + k1) - Class38_Sub2_Sub2.anInt1312;
        if (j < Class38_Sub2_Sub2.anInt1313) {
            int l2 = Class38_Sub2_Sub2.anInt1313 - j;
            l1 -= l2;
            j = Class38_Sub2_Sub2.anInt1313;
            j1 += l2;
            i1 += l2;
            j2 += l2;
            i2 += l2;
        }
        if (j + l1 > Class38_Sub2_Sub2.anInt1314) {
            int i3 = (j + l1) - Class38_Sub2_Sub2.anInt1314;
            l1 -= i3;
            j2 += i3;
            i2 += i3;
        }
        if (l1 <= 0 || k1 <= 0) {
            return;
        } else {
            method410(i1, 0, anIntArray1465, i, k1, Class38_Sub2_Sub2.anIntArray1308, j1, (byte) 8, l1, i2, j2);
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
            int k3 = k1 + l1 * Class38_Sub2_Sub2.anInt1309;
            for (l1 = 0; l1 < k; l1++) {
                int l3 = ai[l1];
                int i4 = k3 + l3;
                int j4 = i3 + l2 * l3;
                int k4 = j3 - k2 * l3;
                for (k1 = -ai1[l1]; k1 < 0; k1++) {
                    Class38_Sub2_Sub2.anIntArray1308[i4++] = anIntArray1465[(j4 >> 16) + (k4 >> 16) * anInt1466];
                    j4 += l2;
                    k4 -= k2;
                }

                i3 += k2;
                j3 += l2;
                k3 += Class38_Sub2_Sub2.anInt1309;
            }

            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void method412(Class38_Sub2_Sub2_Sub3 class38_sub2_sub2_sub3, int i, int j, byte byte0) {
        j += anInt1468;
        i += anInt1469;
        int k = j + i * Class38_Sub2_Sub2.anInt1309;
        int l = 0;
        if (byte0 != -15)
            return;
        int i1 = anInt1467;
        int j1 = anInt1466;
        int k1 = Class38_Sub2_Sub2.anInt1309 - j1;
        int l1 = 0;
        if (i < Class38_Sub2_Sub2.anInt1311) {
            int i2 = Class38_Sub2_Sub2.anInt1311 - i;
            i1 -= i2;
            i = Class38_Sub2_Sub2.anInt1311;
            l += i2 * j1;
            k += i2 * Class38_Sub2_Sub2.anInt1309;
        }
        if (i + i1 > Class38_Sub2_Sub2.anInt1312)
            i1 -= (i + i1) - Class38_Sub2_Sub2.anInt1312;
        if (j < Class38_Sub2_Sub2.anInt1313) {
            int j2 = Class38_Sub2_Sub2.anInt1313 - j;
            j1 -= j2;
            j = Class38_Sub2_Sub2.anInt1313;
            l += j2;
            k += j2;
            l1 += j2;
            k1 += j2;
        }
        if (j + j1 > Class38_Sub2_Sub2.anInt1314) {
            int k2 = (j + j1) - Class38_Sub2_Sub2.anInt1314;
            j1 -= k2;
            l1 += k2;
            k1 += k2;
        }
        if (j1 <= 0 || i1 <= 0) {
            return;
        } else {
            method413(j1, l1, 0, -478, i1, l, Class38_Sub2_Sub2.anIntArray1308, anIntArray1465, k,
                    class38_sub2_sub2_sub3.aByteArray1476, k1);
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
    public int[] anIntArray1465;
    public int anInt1466;
    public int anInt1467;
    public int anInt1468;
    public int anInt1469;
    public int anInt1470;
    public int anInt1471;
}
