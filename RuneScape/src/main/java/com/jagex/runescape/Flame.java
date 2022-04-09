package com.jagex.runescape;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.graphics.IndexedSprite;
import com.jagex.runetek3.graphics.Sprite;

import java.util.Arrays;

public class Flame implements Runnable {
    public boolean flameActive = false;
    public int[] flameBuffer1;
    public int[] flameBuffer2;
    public int flameCycle1;
    public int flameCycle2;
    public int[] flameGradient;
    public int[] flameGradientRed;
    public int[] flameGradientGreen;
    public int[] flameGradientViolet;
    public int[] flameShiftX = new int[256];
    public boolean flamesThreadActive = false;
    public boolean startFlamesThread = false;
    public int flameOffset;
    public int[] flameIntensity;
    public int[] flameIntensityBuffer;

    public Sprite imageFlamesLeft;
    public Sprite imageFlamesRight;
    public IndexedSprite[] imageRunes;

    public void load(FileArchive titleArchive) {
        imageRunes = new IndexedSprite[12];
        for (int j = 0; j < 12; j++) {
            imageRunes[j] = new IndexedSprite(titleArchive, "runes", j);
        }

        imageFlamesLeft = new Sprite(128, 265);
        imageFlamesRight = new Sprite(128, 265);
        System.arraycopy(Game.instance.titleLeft.pixels, 0, imageFlamesLeft.pixels, 0, 33920);
        System.arraycopy(Game.instance.titleRight.pixels, 0, imageFlamesRight.pixels, 0, 33920);

        flameGradientRed = new int[256];
        for (int i1 = 0; i1 < 64; i1++) {
            flameGradientRed[i1] = i1 * 0x40000;
        }
        for (int j1 = 0; j1 < 64; j1++) {
            flameGradientRed[j1 + 64] = 0xff0000 + (1024 * j1);
        }
        for (int k1 = 0; k1 < 64; k1++) {
            flameGradientRed[k1 + 128] = 0xffff00 + (4 * k1);
        }
        for (int l1 = 0; l1 < 64; l1++) {
            flameGradientRed[l1 + 192] = 0xffffff;
        }

        flameGradientGreen = new int[256];
        for (int i2 = 0; i2 < 64; i2++) {
            flameGradientGreen[i2] = i2 * 0x400;
        }
        for (int j2 = 0; j2 < 64; j2++) {
            flameGradientGreen[j2 + 64] = 0xff00 + (4 * j2);
        }
        for (int k2 = 0; k2 < 64; k2++) {
            flameGradientGreen[k2 + 128] = 0xffff + (0x40000 * k2);
        }
        for (int l2 = 0; l2 < 64; l2++) {
            flameGradientGreen[l2 + 192] = 0xffffff;
        }

        flameGradientViolet = new int[256];
        for (int i3 = 0; i3 < 64; i3++) {
            flameGradientViolet[i3] = i3 * 4;
        }
        for (int j3 = 0; j3 < 64; j3++) {
            flameGradientViolet[j3 + 64] = 0xff + (0x40000 * j3);
        }
        for (int k3 = 0; k3 < 64; k3++) {
            flameGradientViolet[k3 + 128] = 0xff00ff + (1024 * k3);
        }
        for (int l3 = 0; l3 < 64; l3++) {
            flameGradientViolet[l3 + 192] = 0xffffff;
        }

        flameGradient = new int[256];
        flameBuffer1 = new int[32768];
        flameBuffer2 = new int[32768];
        updateFlameDissolve(null);
        flameIntensity = new int[32768];
        flameIntensityBuffer = new int[32768];

        start();
    }

    public void start() {
        if (!flameActive) {
            startFlamesThread = true;
            flameActive = true;
            Game.instance.startThread(this, 1);
        }
    }

    public void unload() {
        imageRunes = null;
        flameGradient = null;
        flameGradientRed = null;
        flameGradientGreen = null;
        flameGradientViolet = null;
        flameBuffer1 = null;
        flameBuffer2 = null;
        flameIntensity = null;
        flameIntensityBuffer = null;
        imageFlamesLeft = null;
        imageFlamesRight = null;

        // wait for thread to close
        flameActive = false;
        while (flamesThreadActive) {
            flameActive = false;

            try {
                Thread.sleep(50L);
            } catch (Exception ignored) {
            }
        }
    }

    public void run() {
        flamesThreadActive = true;

        try {
            long last = System.currentTimeMillis();
            int loop = 0;
            int sleepTime = 20;
            while (flameActive) {
                if (Game.instance.titleDrawn) {
                    updateFlames();
                    updateFlames();
                    drawFlames();
                    if (++loop > 10) {
                        long now = System.currentTimeMillis();
                        int delta = (int) (now - last) / 10 - sleepTime;
                        sleepTime = 40 - delta;
                        if (sleepTime < 5) {
                            sleepTime = 5;
                        }
                        loop = 0;
                        last = now;
                    }
                }

                try {
                    Thread.sleep(sleepTime);
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }

        flamesThreadActive = false;
    }

    public void updateFlameDissolve(IndexedSprite indexedSprite) {
        int j = 256;
        Arrays.fill(flameBuffer1, 0);

        for (int l = 0; l < 5000; l++) {
            int i1 = (int) (Math.random() * 128D * (double) j);
            flameBuffer1[i1] = (int) (Math.random() * 256D);
        }

        for (int j1 = 0; j1 < 20; j1++) {
            for (int k1 = 1; k1 < j - 1; k1++) {
                for (int i2 = 1; i2 < 127; i2++) {
                    int k2 = i2 + (k1 << 7);
                    flameBuffer2[k2] = (flameBuffer1[k2 - 1] + flameBuffer1[k2 + 1] + flameBuffer1[k2 - 128] + flameBuffer1[k2 + 128]) / 4;
                }
            }

            int[] ai = flameBuffer1;
            flameBuffer1 = flameBuffer2;
            flameBuffer2 = ai;
        }

        if (indexedSprite != null) {
            int l1 = 0;
            for (int j2 = 0; j2 < indexedSprite.height; j2++) {
                for (int l2 = 0; l2 < indexedSprite.width; l2++) {
                    if (indexedSprite.pixels[l1++] != 0) {
                        int i3 = l2 + 16 + indexedSprite.clipX;
                        int j3 = j2 + 16 + indexedSprite.clipY;
                        int k3 = i3 + (j3 << 7);
                        flameBuffer1[k3] = 0;
                    }
                }
            }
        }
    }

    public void updateFlames() {
        int c = 256;
        for (int i = 10; i < 117; i++) {
            int j = (int) (Math.random() * 100D);
            if (j < 50) {
                flameIntensity[i + (c - 2 << 7)] = 255;
            }
        }

        for (int k = 0; k < 100; k++) {
            int l = (int) (Math.random() * 124D) + 2;
            int j1 = (int) (Math.random() * 128D) + 128;
            int j2 = l + (j1 << 7);
            flameIntensity[j2] = 192;
        }

        for (int i1 = 1; i1 < c - 1; i1++) {
            for (int k1 = 1; k1 < 127; k1++) {
                int k2 = k1 + (i1 << 7);
                flameIntensityBuffer[k2] = (flameIntensity[k2 - 1] + flameIntensity[k2 + 1] + flameIntensity[k2 - 128] + flameIntensity[k2 + 128]) / 4;
            }
        }

        flameOffset += 128;

        if (flameOffset > flameBuffer1.length) {
            flameOffset -= flameBuffer1.length;
            int l1 = (int) (Math.random() * 12D);
            updateFlameDissolve(imageRunes[l1]);
        }

        for (int i2 = 1; i2 < c - 1; i2++) {
            for (int l2 = 1; l2 < 127; l2++) {
                int j3 = l2 + (i2 << 7);
                int l3 = flameIntensityBuffer[j3 + 128] - flameBuffer1[j3 + flameOffset & flameBuffer1.length - 1] / 5;
                if (l3 < 0) {
                    l3 = 0;
                }
                flameIntensity[j3] = l3;
            }
        }

        System.arraycopy(flameShiftX, 1, flameShiftX, 0, c - 1);

        flameShiftX[c - 1] = (int) (Math.sin((double) Game.clientClock / 14D) * 16D + Math.sin((double) Game.clientClock / 15D) * 14D + Math.sin((double) Game.instance.clientClock / 16D) * 12D);

        if (flameCycle1 > 0) {
            flameCycle1 -= 4;
        }

        if (flameCycle2 > 0) {
            flameCycle2 -= 4;
        }

        if (flameCycle1 == 0 && flameCycle2 == 0) {
            int k3 = (int) (Math.random() * 2000D);
            if (k3 == 0) {
                flameCycle1 = 1024;
            }
            if (k3 == 1) {
                flameCycle2 = 1024;
            }
        }
    }

    public void drawFlames() {
        int c = 256;

        if (flameCycle1 > 0) {
            for (int i = 0; i < 256; i++)
                if (flameCycle1 > 768)
                    flameGradient[i] = mix(flameGradientRed[i], 1024 - flameCycle1, flameGradientGreen[i]);
                else if (flameCycle1 > 256)
                    flameGradient[i] = flameGradientGreen[i];
                else
                    flameGradient[i] = mix(flameGradientGreen[i], 256 - flameCycle1, flameGradientRed[i]);
        } else if (flameCycle2 > 0) {
            for (int j = 0; j < 256; j++)
                if (flameCycle2 > 768)
                    flameGradient[j] = mix(flameGradientRed[j], 1024 - flameCycle2, flameGradientViolet[j]);
                else if (flameCycle2 > 256)
                    flameGradient[j] = flameGradientViolet[j];
                else
                    flameGradient[j] = mix(flameGradientViolet[j], 256 - flameCycle2, flameGradientRed[j]);
        } else {
            System.arraycopy(flameGradientRed, 0, flameGradient, 0, 256);
        }

        System.arraycopy(imageFlamesLeft.pixels, 0, Game.instance.titleLeft.pixels, 0, 33920);

        int i1 = 0;
        int j1 = 1152;
        for (int k1 = 1; k1 < c - 1; k1++) {
            int l1 = (flameShiftX[k1] * (c - k1)) / c;
            int j2 = 22 + l1;
            if (j2 < 0) {
                j2 = 0;
            }

            i1 += j2;
            for (int l2 = j2; l2 < 128; l2++) {
                int j3 = flameIntensity[i1++];
                if (j3 != 0) {
                    int l3 = j3;
                    int j4 = 256 - j3;
                    j3 = flameGradient[j3];
                    int l4 = Game.instance.titleLeft.pixels[j1];
                    Game.instance.titleLeft.pixels[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00) + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            j1 += j2;
        }

        Game.instance.titleLeft.drawImage(Game.instance.graphics, 0, 0);
        System.arraycopy(imageFlamesRight.pixels, 0, Game.instance.titleRight.pixels, 0, 33920);

        i1 = 0;
        j1 = 1176;
        for (int k2 = 1; k2 < c - 1; k2++) {
            int i3 = (flameShiftX[k2] * (c - k2)) / c;
            int k3 = 103 - i3;
            j1 += i3;
            for (int i4 = 0; i4 < k3; i4++) {
                int k4 = flameIntensity[i1++];
                if (k4 != 0) {
                    int i5 = k4;
                    int j5 = 256 - k4;
                    k4 = flameGradient[k4];
                    int k5 = Game.instance.titleRight.pixels[j1];
                    Game.instance.titleRight.pixels[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00) + ((k4 & 0xff00) * i5 + (k5 & 0xff00) * j5 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            i1 += 128 - k3;
            j1 += 128 - k3 - i3;
        }

        Game.instance.titleRight.drawImage(Game.instance.graphics, 661, 0);
    }

    public int mix(int i, int j, int k) {
        int i1 = 256 - j;
        return ((i & 0xff00ff) * i1 + (k & 0xff00ff) * j & 0xff00ff00) + ((i & 0xff00) * i1 + (k & 0xff00) * j & 0xff0000) >> 8;
    }
}
