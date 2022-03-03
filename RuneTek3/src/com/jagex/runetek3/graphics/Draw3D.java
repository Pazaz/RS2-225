package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;

public class Draw3D extends Draw2D {

    public static boolean lowMemory = true;
    public static boolean testX;
    public static boolean opaque;
    public static boolean jagged = true;
    public static int alpha;
    public static int centerX;
    public static int centerY;
    public static int[] reciprical15;
    public static int[] reciprical16;
    public static int[] sin;
    public static int[] cos;
    public static int[] offsets;
    public static int textureCount;
    public static IndexedSprite[] textures = new IndexedSprite[50];
    public static boolean[] textureHasTransparency = new boolean[50];
    public static int[] textureColors = new int[50];
    public static int poolSize;
    public static int[][] texelPool;
    public static int[][] activeTexels = new int[50][];
    public static int[] textureCycles = new int[50];
    public static int cycle;
    public static int[] palette = new int[0x10000];
    public static int[][] texturePalettes = new int[50][];

    static {
        reciprical15 = new int[512];
        reciprical16 = new int[2048];

        sin = new int[2048];
        cos = new int[2048];

        for (int i = 1; i < 512; i++) {
            reciprical15[i] = 0x8000 / i;
        }

        for (int j = 1; j < 2048; j++) {
            reciprical16[j] = 0x10000 / j;
        }

        for (int k = 0; k < 2048; k++) {
            sin[k] = (int) (65536D * Math.sin((double) k * 0.0030679614999999999D));
            cos[k] = (int) (65536D * Math.cos((double) k * 0.0030679614999999999D));
        }
    }

    public static void unload() {
        reciprical15 = null;
        sin = null;
        cos = null;
        offsets = null;
        textures = null;
        textureHasTransparency = null;
        textureColors = null;
        texelPool = null;
        activeTexels = null;
        textureCycles = null;
        palette = null;
        texturePalettes = null;
    }

    public static void prepareOffsets() {
        offsets = new int[height];
        for (int n = 0; n < height; n++) {
            offsets[n] = width * n;
        }

        centerX = width / 2;
        centerY = height / 2;
    }

    public static void prepareOffsets(int height, int width) {
        offsets = new int[height];
        for (int n = 0; n < height; n++) {
            offsets[n] = width * n;
        }

        centerX = width / 2;
        centerY = height / 2;
    }

    public static void clearPools() {
        texelPool = null;

        for (int id = 0; id < 50; id++) {
            activeTexels[id] = null;
        }
    }

    public static void setupPools(int size) {
        if (texelPool != null) {
            return;
        }

        poolSize = size;

        if (lowMemory) {
            texelPool = new int[poolSize][64 * 64 * 4];
        } else {
            texelPool = new int[poolSize][128 * 128 * 4];
        }

        for (int t = 0; t < 50; t++) {
            activeTexels[t] = null;
        }
    }

    public static void unpackTextures(FileArchive fileArchive) {
        textureCount = 0;

        for (int id = 0; id < 50; id++) {
            try {
                textures[id] = new IndexedSprite(fileArchive, String.valueOf(id), 0);

                if (lowMemory && textures[id].clipWidth == 128) {
                    textures[id].shrink();
                } else {
                    textures[id].crop();
                }

                textureCount++;
            } catch (Exception _ex) {
            }
        }
    }

    public static int getAverageTextureRGB(int id) {
        if (textureColors[id] != 0) {
            return textureColors[id];
        }

        int r = 0;
        int g = 0;
        int b = 0;
        int length = texturePalettes[id].length;

        for (int i = 0; i < length; i++) {
            r += texturePalettes[id][i] >> 16 & 0xff;
            g += texturePalettes[id][i] >> 8 & 0xff;
            b += texturePalettes[id][i] & 0xff;
        }

        int rgb = (r / length << 16) + (g / length << 8) + b / length;
        rgb = powRGB(rgb, 1.3999999999999999D);
        if (rgb == 0) {
            rgb = 1;
        }

        textureColors[id] = rgb;
        return rgb;
    }

    public static void updateTexture(int id) {
        if (activeTexels[id] != null) {
            texelPool[poolSize++] = activeTexels[id];
            activeTexels[id] = null;
        }
    }

    public static int[] getTexels(int id) {
        textureCycles[id] = cycle++;
        if (activeTexels[id] != null) {
            return activeTexels[id];
        }

        int[] texels;
        if (poolSize > 0) {
            texels = texelPool[--poolSize];
            texelPool[poolSize] = null;
        } else {
            int cycle = 0;
            int selected = -1;

            for (int t = 0; t < textureCount; t++) {
                if (activeTexels[t] != null && (textureCycles[t] < cycle || selected == -1)) {
                    cycle = textureCycles[t];
                    selected = t;
                }
            }

            texels = activeTexels[selected];
            activeTexels[selected] = null;
        }

        activeTexels[id] = texels;
        IndexedSprite texture = textures[id];
        int[] palette = texturePalettes[id];

        if (lowMemory) {
            textureHasTransparency[id] = false;

            for (int i = 0; i < 64 * 64; i++) {
                int rgb = texels[i] = palette[texture.pixels[i]] & 0xf8f8ff;
                if (rgb == 0) {
                    textureHasTransparency[id] = true;
                }

                texels[64 * 64 + i] = rgb - (rgb >>> 3) & 0xf8f8ff;
                texels[64 * 64 * 2 + i] = rgb - (rgb >>> 2) & 0xf8f8ff;
                texels[64 * 64 * 3 + i] = rgb - (rgb >>> 2) - (rgb >>> 3) & 0xf8f8ff;
            }
        } else {
            if (texture.width == 64) {
                // upscale 64x64 textures to 128x128
                for (int j1 = 0; j1 < 128; j1++) {
                    for (int j2 = 0; j2 < 128; j2++) {
                        texels[j2 + (j1 << 7)] = palette[texture.pixels[(j2 >> 1) + ((j1 >> 1) << 6)]];
                    }
                }
            } else {
                for (int k1 = 0; k1 < 128 * 128; k1++) {
                    texels[k1] = palette[texture.pixels[k1]];
                }
            }

            textureHasTransparency[id] = false;
            for (int l1 = 0; l1 < 128 * 128; l1++) {
                texels[l1] &= 0xf8f8ff;

                int rgb = texels[l1];
                if (rgb == 0) {
                    textureHasTransparency[id] = true;
                }

                texels[128 * 128 + l1] = rgb - (rgb >>> 3) & 0xf8f8ff;
                texels[128 * 128 * 2 + l1] = rgb - (rgb >>> 2) & 0xf8f8ff;
                texels[128 * 128 * 3 + l1] = rgb - (rgb >>> 2) - (rgb >>> 3) & 0xf8f8ff;
            }

        }
        return texels;
    }

    public static void setBrightness(double brightness) {
        brightness += Math.random() * 0.029999999999999999D - 0.014999999999999999D;

        int offset = 0;
        for (int y = 0; y < 512; y++) {
            double hue = (double) (y / 8) / 64D + 0.0078125D;
            double saturation = (double) (y & 7) / 8D + 0.0625D;

            for (int x = 0; x < 128; x++) {
                double lightness = (double) x / 128D;

                double r = lightness;
                double g = lightness;
                double b = lightness;

                if (saturation != 0.0D) {
                    double q;

                    if (lightness < 0.5D) {
                        q = lightness * (1.0D + saturation);
                    } else {
                        q = (lightness + saturation) - lightness * saturation;
                    }

                    double p = 2D * lightness - q;
                    double t = hue + 0.33333333333333331D;
                    if (t > 1.0D) {
                        t--;
                    }

                    double d11 = hue - 0.33333333333333331D;
                    if (d11 < 0.0D) {
                        d11++;
                    }

                    if (6D * t < 1.0D) {
                        r = p + (q - p) * 6D * t;
                    } else if (2D * t < 1.0D) {
                        r = q;
                    } else if (3D * t < 2D) {
                        r = p + (q - p) * (0.66666666666666663D - t) * 6D;
                    } else {
                        r = p;
                    }

                    if (6D * hue < 1.0D) {
                        g = p + (q - p) * 6D * hue;
                    } else if (2D * hue < 1.0D) {
                        g = q;
                    } else if (3D * hue < 2D) {
                        g = p + (q - p) * (0.66666666666666663D - hue) * 6D;
                    } else {
                        g = p;
                    }

                    if (6D * d11 < 1.0D) {
                        b = p + (q - p) * 6D * d11;
                    } else if (2D * d11 < 1.0D) {
                        b = q;
                    } else if (3D * d11 < 2D) {
                        b = p + (q - p) * (0.66666666666666663D - d11) * 6D;
                    } else {
                        b = p;
                    }
                }

                int intR = (int) (r * 256D);
                int intG = (int) (g * 256D);
                int intB = (int) (b * 256D);

                int rgb = (intR << 16) + (intG << 8) + intB;
                rgb = powRGB(rgb, brightness);
                palette[offset++] = rgb;
            }
        }

        for (int id = 0; id < 50; id++) {
            if (textures[id] != null) {
                int[] palette = textures[id].palette;
                texturePalettes[id] = new int[palette.length];

                for (int i = 0; i < palette.length; i++) {
                    texturePalettes[id][i] = powRGB(palette[i], brightness);
                }
            }
        }

        for (int id = 0; id < 50; id++) {
            updateTexture(id);
        }
    }

    public static int powRGB(int rgb, double brightness) {
        double r = (double) (rgb >> 16) / 256D;
        double g = (double) (rgb >> 8 & 0xff) / 256D;
        double b = (double) (rgb & 0xff) / 256D;

        r = Math.pow(r, brightness);
        g = Math.pow(g, brightness);
        b = Math.pow(b, brightness);

        int intR = (int) (r * 256D);
        int intG = (int) (g * 256D);
        int intB = (int) (b * 256D);

        return (intR << 16) + (intG << 8) + intB;
    }

    public static void fillGouraudTriangle(int yA, int yB, int yC, int xA, int xB, int xC, int colorA, int colorB,
                                           int colorC) {
        int xStepAB = 0;
        int colorStepAB = 0;
        if (yB != yA) {
            xStepAB = (xB - xA << 16) / (yB - yA);
            colorStepAB = (colorB - colorA << 15) / (yB - yA);
        }

        int xStepBC = 0;
        int colorStepBC = 0;
        if (yC != yB) {
            xStepBC = (xC - xB << 16) / (yC - yB);
            colorStepBC = (colorC - colorB << 15) / (yC - yB);
        }

        int xStepAC = 0;
        int colorStepAC = 0;
        if (yC != yA) {
            xStepAC = (xA - xC << 16) / (yA - yC);
            colorStepAC = (colorA - colorC << 15) / (yA - yC);
        }

        if (yA <= yB && yA <= yC) {
            if (yA >= bottom)
                return;
            if (yB > bottom)
                yB = bottom;
            if (yC > bottom)
                yC = bottom;
            if (yB < yC) {
                xC = xA <<= 16;
                colorC = colorA <<= 15;
                if (yA < 0) {
                    xC -= xStepAC * yA;
                    xA -= xStepAB * yA;
                    colorC -= colorStepAC * yA;
                    colorA -= colorStepAB * yA;
                    yA = 0;
                }
                xB <<= 16;
                colorB <<= 15;
                if (yB < 0) {
                    xB -= xStepBC * yB;
                    colorB -= colorStepBC * yB;
                    yB = 0;
                }
                if (yA != yB && xStepAC < xStepAB || yA == yB && xStepAC > xStepBC) {
                    yC -= yB;
                    yB -= yA;
                    for (yA = offsets[yA]; --yB >= 0; yA += width) {
                        drawGouraudScanline(dest, yA, xC >> 16, xA >> 16, colorC >> 7, colorA >> 7);
                        xC += xStepAC;
                        xA += xStepAB;
                        colorC += colorStepAC;
                        colorA += colorStepAB;
                    }

                    while (--yC >= 0) {
                        drawGouraudScanline(dest, yA, xC >> 16, xB >> 16, colorC >> 7, colorB >> 7);
                        xC += xStepAC;
                        xB += xStepBC;
                        colorC += colorStepAC;
                        colorB += colorStepBC;
                        yA += width;
                    }
                    return;
                }
                yC -= yB;
                yB -= yA;
                for (yA = offsets[yA]; --yB >= 0; yA += width) {
                    drawGouraudScanline(dest, yA, xA >> 16, xC >> 16, colorA >> 7, colorC >> 7);
                    xC += xStepAC;
                    xA += xStepAB;
                    colorC += colorStepAC;
                    colorA += colorStepAB;
                }

                while (--yC >= 0) {
                    drawGouraudScanline(dest, yA, xB >> 16, xC >> 16, colorB >> 7, colorC >> 7);
                    xC += xStepAC;
                    xB += xStepBC;
                    colorC += colorStepAC;
                    colorB += colorStepBC;
                    yA += width;
                }
                return;
            }
            xB = xA <<= 16;
            colorB = colorA <<= 15;
            if (yA < 0) {
                xB -= xStepAC * yA;
                xA -= xStepAB * yA;
                colorB -= colorStepAC * yA;
                colorA -= colorStepAB * yA;
                yA = 0;
            }
            xC <<= 16;
            colorC <<= 15;
            if (yC < 0) {
                xC -= xStepBC * yC;
                colorC -= colorStepBC * yC;
                yC = 0;
            }
            if (yA != yC && xStepAC < xStepAB || yA == yC && xStepBC > xStepAB) {
                yB -= yC;
                yC -= yA;
                for (yA = offsets[yA]; --yC >= 0; yA += width) {
                    drawGouraudScanline(dest, yA, xB >> 16, xA >> 16, colorB >> 7, colorA >> 7);
                    xB += xStepAC;
                    xA += xStepAB;
                    colorB += colorStepAC;
                    colorA += colorStepAB;
                }

                while (--yB >= 0) {
                    drawGouraudScanline(dest, yA, xC >> 16, xA >> 16, colorC >> 7, colorA >> 7);
                    xC += xStepBC;
                    xA += xStepAB;
                    colorC += colorStepBC;
                    colorA += colorStepAB;
                    yA += width;
                }
                return;
            }
            yB -= yC;
            yC -= yA;
            for (yA = offsets[yA]; --yC >= 0; yA += width) {
                drawGouraudScanline(dest, yA, xA >> 16, xB >> 16, colorA >> 7, colorB >> 7);
                xB += xStepAC;
                xA += xStepAB;
                colorB += colorStepAC;
                colorA += colorStepAB;
            }

            while (--yB >= 0) {
                drawGouraudScanline(dest, yA, xA >> 16, xC >> 16, colorA >> 7, colorC >> 7);
                xC += xStepBC;
                xA += xStepAB;
                colorC += colorStepBC;
                colorA += colorStepAB;
                yA += width;
            }
            return;
        }
        if (yB <= yC) {
            if (yB >= bottom)
                return;
            if (yC > bottom)
                yC = bottom;
            if (yA > bottom)
                yA = bottom;
            if (yC < yA) {
                xA = xB <<= 16;
                colorA = colorB <<= 15;
                if (yB < 0) {
                    xA -= xStepAB * yB;
                    xB -= xStepBC * yB;
                    colorA -= colorStepAB * yB;
                    colorB -= colorStepBC * yB;
                    yB = 0;
                }
                xC <<= 16;
                colorC <<= 15;
                if (yC < 0) {
                    xC -= xStepAC * yC;
                    colorC -= colorStepAC * yC;
                    yC = 0;
                }
                if (yB != yC && xStepAB < xStepBC || yB == yC && xStepAB > xStepAC) {
                    yA -= yC;
                    yC -= yB;
                    for (yB = offsets[yB]; --yC >= 0; yB += width) {
                        drawGouraudScanline(dest, yB, xA >> 16, xB >> 16, colorA >> 7, colorB >> 7);
                        xA += xStepAB;
                        xB += xStepBC;
                        colorA += colorStepAB;
                        colorB += colorStepBC;
                    }

                    while (--yA >= 0) {
                        drawGouraudScanline(dest, yB, xA >> 16, xC >> 16, colorA >> 7, colorC >> 7);
                        xA += xStepAB;
                        xC += xStepAC;
                        colorA += colorStepAB;
                        colorC += colorStepAC;
                        yB += width;
                    }
                    return;
                }
                yA -= yC;
                yC -= yB;
                for (yB = offsets[yB]; --yC >= 0; yB += width) {
                    drawGouraudScanline(dest, yB, xB >> 16, xA >> 16, colorB >> 7, colorA >> 7);
                    xA += xStepAB;
                    xB += xStepBC;
                    colorA += colorStepAB;
                    colorB += colorStepBC;
                }

                while (--yA >= 0) {
                    drawGouraudScanline(dest, yB, xC >> 16, xA >> 16, colorC >> 7, colorA >> 7);
                    xA += xStepAB;
                    xC += xStepAC;
                    colorA += colorStepAB;
                    colorC += colorStepAC;
                    yB += width;
                }
                return;
            }
            xC = xB <<= 16;
            colorC = colorB <<= 15;
            if (yB < 0) {
                xC -= xStepAB * yB;
                xB -= xStepBC * yB;
                colorC -= colorStepAB * yB;
                colorB -= colorStepBC * yB;
                yB = 0;
            }
            xA <<= 16;
            colorA <<= 15;
            if (yA < 0) {
                xA -= xStepAC * yA;
                colorA -= colorStepAC * yA;
                yA = 0;
            }
            if (xStepAB < xStepBC) {
                yC -= yA;
                yA -= yB;
                for (yB = offsets[yB]; --yA >= 0; yB += width) {
                    drawGouraudScanline(dest, yB, xC >> 16, xB >> 16, colorC >> 7, colorB >> 7);
                    xC += xStepAB;
                    xB += xStepBC;
                    colorC += colorStepAB;
                    colorB += colorStepBC;
                }

                while (--yC >= 0) {
                    drawGouraudScanline(dest, yB, xA >> 16, xB >> 16, colorA >> 7, colorB >> 7);
                    xA += xStepAC;
                    xB += xStepBC;
                    colorA += colorStepAC;
                    colorB += colorStepBC;
                    yB += width;
                }
                return;
            }
            yC -= yA;
            yA -= yB;
            for (yB = offsets[yB]; --yA >= 0; yB += width) {
                drawGouraudScanline(dest, yB, xB >> 16, xC >> 16, colorB >> 7, colorC >> 7);
                xC += xStepAB;
                xB += xStepBC;
                colorC += colorStepAB;
                colorB += colorStepBC;
            }

            while (--yC >= 0) {
                drawGouraudScanline(dest, yB, xB >> 16, xA >> 16, colorB >> 7, colorA >> 7);
                xA += xStepAC;
                xB += xStepBC;
                colorA += colorStepAC;
                colorB += colorStepBC;
                yB += width;
            }
            return;
        }
        if (yC >= bottom)
            return;
        if (yA > bottom)
            yA = bottom;
        if (yB > bottom)
            yB = bottom;
        if (yA < yB) {
            xB = xC <<= 16;
            colorB = colorC <<= 15;
            if (yC < 0) {
                xB -= xStepBC * yC;
                xC -= xStepAC * yC;
                colorB -= colorStepBC * yC;
                colorC -= colorStepAC * yC;
                yC = 0;
            }
            xA <<= 16;
            colorA <<= 15;
            if (yA < 0) {
                xA -= xStepAB * yA;
                colorA -= colorStepAB * yA;
                yA = 0;
            }
            if (xStepBC < xStepAC) {
                yB -= yA;
                yA -= yC;
                for (yC = offsets[yC]; --yA >= 0; yC += width) {
                    drawGouraudScanline(dest, yC, xB >> 16, xC >> 16, colorB >> 7, colorC >> 7);
                    xB += xStepBC;
                    xC += xStepAC;
                    colorB += colorStepBC;
                    colorC += colorStepAC;
                }

                while (--yB >= 0) {
                    drawGouraudScanline(dest, yC, xB >> 16, xA >> 16, colorB >> 7, colorA >> 7);
                    xB += xStepBC;
                    xA += xStepAB;
                    colorB += colorStepBC;
                    colorA += colorStepAB;
                    yC += width;
                }
                return;
            }
            yB -= yA;
            yA -= yC;
            for (yC = offsets[yC]; --yA >= 0; yC += width) {
                drawGouraudScanline(dest, yC, xC >> 16, xB >> 16, colorC >> 7, colorB >> 7);
                xB += xStepBC;
                xC += xStepAC;
                colorB += colorStepBC;
                colorC += colorStepAC;
            }

            while (--yB >= 0) {
                drawGouraudScanline(dest, yC, xA >> 16, xB >> 16, colorA >> 7, colorB >> 7);
                xB += xStepBC;
                xA += xStepAB;
                colorB += colorStepBC;
                colorA += colorStepAB;
                yC += width;
            }
            return;
        }
        xA = xC <<= 16;
        colorA = colorC <<= 15;
        if (yC < 0) {
            xA -= xStepBC * yC;
            xC -= xStepAC * yC;
            colorA -= colorStepBC * yC;
            colorC -= colorStepAC * yC;
            yC = 0;
        }
        xB <<= 16;
        colorB <<= 15;
        if (yB < 0) {
            xB -= xStepAB * yB;
            colorB -= colorStepAB * yB;
            yB = 0;
        }
        if (xStepBC < xStepAC) {
            yA -= yB;
            yB -= yC;
            for (yC = offsets[yC]; --yB >= 0; yC += width) {
                drawGouraudScanline(dest, yC, xA >> 16, xC >> 16, colorA >> 7, colorC >> 7);
                xA += xStepBC;
                xC += xStepAC;
                colorA += colorStepBC;
                colorC += colorStepAC;
            }

            while (--yA >= 0) {
                drawGouraudScanline(dest, yC, xB >> 16, xC >> 16, colorB >> 7, colorC >> 7);
                xB += xStepAB;
                xC += xStepAC;
                colorB += colorStepAB;
                colorC += colorStepAC;
                yC += width;
            }
            return;
        }
        yA -= yB;
        yB -= yC;
        for (yC = offsets[yC]; --yB >= 0; yC += width) {
            drawGouraudScanline(dest, yC, xC >> 16, xA >> 16, colorC >> 7, colorA >> 7);
            xA += xStepBC;
            xC += xStepAC;
            colorA += colorStepBC;
            colorC += colorStepAC;
        }

        while (--yA >= 0) {
            drawGouraudScanline(dest, yC, xC >> 16, xB >> 16, colorC >> 7, colorB >> 7);
            xB += xStepAB;
            xC += xStepAC;
            colorB += colorStepAB;
            colorC += colorStepAC;
            yC += width;
        }
    }

    public static void drawGouraudScanline(int[] dst, int dstOff, int leftX, int rightX, int leftColor, int rightColor) {
        int color;
        int length;

        if (jagged) {
            int colorStep;

            if (testX) {
                if (rightX - leftX > 3)
                    colorStep = (rightColor - leftColor) / (rightX - leftX);
                else
                    colorStep = 0;
                if (rightX > Draw2D.rightX)
                    rightX = Draw2D.rightX;
                if (leftX < 0) {
                    leftColor -= leftX * colorStep;
                    leftX = 0;
                }
                if (leftX >= rightX)
                    return;
                dstOff += leftX;
                length = rightX - leftX >> 2;
                colorStep <<= 2;
            } else {
                if (leftX >= rightX)
                    return;
                dstOff += leftX;
                length = rightX - leftX >> 2;
                if (length > 0)
                    colorStep = (rightColor - leftColor) * reciprical15[length] >> 15;
                else
                    colorStep = 0;
            }
            if (alpha == 0) {
                while (--length >= 0) {
                    color = palette[leftColor >> 8];
                    leftColor += colorStep;
                    dst[dstOff++] = color;
                    dst[dstOff++] = color;
                    dst[dstOff++] = color;
                    dst[dstOff++] = color;
                }
                length = rightX - leftX & 3;
                if (length > 0) {
                    color = palette[leftColor >> 8];
                    do
                        dst[dstOff++] = color;
                    while (--length > 0);
                    return;
                }
            } else {
                int alpha = Draw3D.alpha;
                int invAlpha = 256 - Draw3D.alpha;
                while (--length >= 0) {
                    color = palette[leftColor >> 8];
                    leftColor += colorStep;
                    color = ((color & 0xff00ff) * invAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * invAlpha >> 8 & 0xff00);
                    for (int i = 0; i < 4; ++i) {
                        dst[dstOff] = color + ((dst[dstOff] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((dst[dstOff] & 0xff00) * alpha >> 8 & 0xff00);
                        dstOff++; // incrementing here instead fixes a transparency bug
                    }
                }
                length = rightX - leftX & 3;
                if (length > 0) {
                    color = palette[leftColor >> 8];
                    color = ((color & 0xff00ff) * invAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * invAlpha >> 8 & 0xff00);
                    do {
                        dst[dstOff] = color + ((dst[dstOff] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((dst[dstOff] & 0xff00) * alpha >> 8 & 0xff00);
                        dstOff++; // incrementing here instead fixes a transparency bug
                    } while (--length > 0);
                }
            }
            return;
        }
        if (leftX >= rightX)
            return;
        int colorStep = (rightColor - leftColor) / (rightX - leftX);
        if (testX) {
            if (rightX > Draw2D.rightX)
                rightX = Draw2D.rightX;
            if (leftX < 0) {
                leftColor -= leftX * colorStep;
                leftX = 0;
            }
            if (leftX >= rightX)
                return;
        }
        dstOff += leftX;
        length = rightX - leftX;
        if (alpha == 0) {
            do {
                dst[dstOff++] = palette[leftColor >> 8];
                leftColor += colorStep;
            } while (--length > 0);
            return;
        }
        int alpha = Draw3D.alpha;
        int invAlpha = 256 - Draw3D.alpha;
        do {
            color = palette[leftColor >> 8];
            leftColor += colorStep;
            color = ((color & 0xff00ff) * invAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * invAlpha >> 8 & 0xff00);
            dst[dstOff++] = color + ((dst[dstOff] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((dst[dstOff] & 0xff00) * alpha >> 8 & 0xff00);
        } while (--length > 0);
    }

    public static void fillTriangle(int yA, int yB, int yC, int xA, int xB, int xC, int color) {
        int mAB = 0;
        if (yB != yA)
            mAB = (xB - xA << 16) / (yB - yA);
        int mBC = 0;
        if (yC != yB)
            mBC = (xC - xB << 16) / (yC - yB);
        int mCA = 0;
        if (yC != yA)
            mCA = (xA - xC << 16) / (yA - yC);
        if (yA <= yB && yA <= yC) {
            if (yA >= bottom)
                return;
            if (yB > bottom)
                yB = bottom;
            if (yC > bottom)
                yC = bottom;
            if (yB < yC) {
                xC = xA <<= 16;
                if (yA < 0) {
                    xC -= mCA * yA;
                    xA -= mAB * yA;
                    yA = 0;
                }
                xB <<= 16;
                if (yB < 0) {
                    xB -= mBC * yB;
                    yB = 0;
                }
                if (yA != yB && mCA < mAB || yA == yB && mCA > mBC) {
                    yC -= yB;
                    yB -= yA;
                    for (yA = offsets[yA]; --yB >= 0; yA += width) {
                        drawScanline(dest, yA, color, xC >> 16, xA >> 16);
                        xC += mCA;
                        xA += mAB;
                    }

                    while (--yC >= 0) {
                        drawScanline(dest, yA, color, xC >> 16, xB >> 16);
                        xC += mCA;
                        xB += mBC;
                        yA += width;
                    }
                    return;
                }
                yC -= yB;
                yB -= yA;
                for (yA = offsets[yA]; --yB >= 0; yA += width) {
                    drawScanline(dest, yA, color, xA >> 16, xC >> 16);
                    xC += mCA;
                    xA += mAB;
                }

                while (--yC >= 0) {
                    drawScanline(dest, yA, color, xB >> 16, xC >> 16);
                    xC += mCA;
                    xB += mBC;
                    yA += width;
                }
                return;
            }
            xB = xA <<= 16;
            if (yA < 0) {
                xB -= mCA * yA;
                xA -= mAB * yA;
                yA = 0;
            }
            xC <<= 16;
            if (yC < 0) {
                xC -= mBC * yC;
                yC = 0;
            }
            if (yA != yC && mCA < mAB || yA == yC && mBC > mAB) {
                yB -= yC;
                yC -= yA;
                for (yA = offsets[yA]; --yC >= 0; yA += width) {
                    drawScanline(dest, yA, color, xB >> 16, xA >> 16);
                    xB += mCA;
                    xA += mAB;
                }

                while (--yB >= 0) {
                    drawScanline(dest, yA, color, xC >> 16, xA >> 16);
                    xC += mBC;
                    xA += mAB;
                    yA += width;
                }
                return;
            }
            yB -= yC;
            yC -= yA;
            for (yA = offsets[yA]; --yC >= 0; yA += width) {
                drawScanline(dest, yA, color, xA >> 16, xB >> 16);
                xB += mCA;
                xA += mAB;
            }

            while (--yB >= 0) {
                drawScanline(dest, yA, color, xA >> 16, xC >> 16);
                xC += mBC;
                xA += mAB;
                yA += width;
            }
            return;
        }
        if (yB <= yC) {
            if (yB >= bottom)
                return;
            if (yC > bottom)
                yC = bottom;
            if (yA > bottom)
                yA = bottom;
            if (yC < yA) {
                xA = xB <<= 16;
                if (yB < 0) {
                    xA -= mAB * yB;
                    xB -= mBC * yB;
                    yB = 0;
                }
                xC <<= 16;
                if (yC < 0) {
                    xC -= mCA * yC;
                    yC = 0;
                }
                if (yB != yC && mAB < mBC || yB == yC && mAB > mCA) {
                    yA -= yC;
                    yC -= yB;
                    for (yB = offsets[yB]; --yC >= 0; yB += width) {
                        drawScanline(dest, yB, color, xA >> 16, xB >> 16);
                        xA += mAB;
                        xB += mBC;
                    }

                    while (--yA >= 0) {
                        drawScanline(dest, yB, color, xA >> 16, xC >> 16);
                        xA += mAB;
                        xC += mCA;
                        yB += width;
                    }
                    return;
                }
                yA -= yC;
                yC -= yB;
                for (yB = offsets[yB]; --yC >= 0; yB += width) {
                    drawScanline(dest, yB, color, xB >> 16, xA >> 16);
                    xA += mAB;
                    xB += mBC;
                }

                while (--yA >= 0) {
                    drawScanline(dest, yB, color, xC >> 16, xA >> 16);
                    xA += mAB;
                    xC += mCA;
                    yB += width;
                }
                return;
            }
            xC = xB <<= 16;
            if (yB < 0) {
                xC -= mAB * yB;
                xB -= mBC * yB;
                yB = 0;
            }
            xA <<= 16;
            if (yA < 0) {
                xA -= mCA * yA;
                yA = 0;
            }
            if (mAB < mBC) {
                yC -= yA;
                yA -= yB;
                for (yB = offsets[yB]; --yA >= 0; yB += width) {
                    drawScanline(dest, yB, color, xC >> 16, xB >> 16);
                    xC += mAB;
                    xB += mBC;
                }

                while (--yC >= 0) {
                    drawScanline(dest, yB, color, xA >> 16, xB >> 16);
                    xA += mCA;
                    xB += mBC;
                    yB += width;
                }
                return;
            }
            yC -= yA;
            yA -= yB;
            for (yB = offsets[yB]; --yA >= 0; yB += width) {
                drawScanline(dest, yB, color, xB >> 16, xC >> 16);
                xC += mAB;
                xB += mBC;
            }

            while (--yC >= 0) {
                drawScanline(dest, yB, color, xB >> 16, xA >> 16);
                xA += mCA;
                xB += mBC;
                yB += width;
            }
            return;
        }
        if (yC >= bottom)
            return;
        if (yA > bottom)
            yA = bottom;
        if (yB > bottom)
            yB = bottom;
        if (yA < yB) {
            xB = xC <<= 16;
            if (yC < 0) {
                xB -= mBC * yC;
                xC -= mCA * yC;
                yC = 0;
            }
            xA <<= 16;
            if (yA < 0) {
                xA -= mAB * yA;
                yA = 0;
            }
            if (mBC < mCA) {
                yB -= yA;
                yA -= yC;
                for (yC = offsets[yC]; --yA >= 0; yC += width) {
                    drawScanline(dest, yC, color, xB >> 16, xC >> 16);
                    xB += mBC;
                    xC += mCA;
                }

                while (--yB >= 0) {
                    drawScanline(dest, yC, color, xB >> 16, xA >> 16);
                    xB += mBC;
                    xA += mAB;
                    yC += width;
                }
                return;
            }
            yB -= yA;
            yA -= yC;
            for (yC = offsets[yC]; --yA >= 0; yC += width) {
                drawScanline(dest, yC, color, xC >> 16, xB >> 16);
                xB += mBC;
                xC += mCA;
            }

            while (--yB >= 0) {
                drawScanline(dest, yC, color, xA >> 16, xB >> 16);
                xB += mBC;
                xA += mAB;
                yC += width;
            }
            return;
        }
        xA = xC <<= 16;
        if (yC < 0) {
            xA -= mBC * yC;
            xC -= mCA * yC;
            yC = 0;
        }
        xB <<= 16;
        if (yB < 0) {
            xB -= mAB * yB;
            yB = 0;
        }
        if (mBC < mCA) {
            yA -= yB;
            yB -= yC;
            for (yC = offsets[yC]; --yB >= 0; yC += width) {
                drawScanline(dest, yC, color, xA >> 16, xC >> 16);
                xA += mBC;
                xC += mCA;
            }

            while (--yA >= 0) {
                drawScanline(dest, yC, color, xB >> 16, xC >> 16);
                xB += mAB;
                xC += mCA;
                yC += width;
            }
            return;
        }
        yA -= yB;
        yB -= yC;
        for (yC = offsets[yC]; --yB >= 0; yC += width) {
            drawScanline(dest, yC, color, xC >> 16, xA >> 16);
            xA += mBC;
            xC += mCA;
        }

        while (--yA >= 0) {
            drawScanline(dest, yC, color, xC >> 16, xB >> 16);
            xB += mAB;
            xC += mCA;
            yC += width;
        }
    }

    public static void drawScanline(int[] pixels, int offset, int color, int xA, int xB) {
        if (testX) {
            if (xB > rightX)
                xB = rightX;
            if (xA < 0)
                xA = 0;
        }
        if (xA >= xB)
            return;
        offset += xA;
        int length = xB - xA >> 2;
        if (alpha == 0) {
            while (--length >= 0) {
                pixels[offset++] = color;
                pixels[offset++] = color;
                pixels[offset++] = color;
                pixels[offset++] = color;
            }
            for (length = xB - xA & 3; --length >= 0; )
                pixels[offset++] = color;

            return;
        }
        int alpha = Draw3D.alpha;
        int invAlpha = 256 - Draw3D.alpha;
        color = ((color & 0xff00ff) * invAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * invAlpha >> 8 & 0xff00);
        while (--length >= 0) {
            pixels[offset++] = color + ((pixels[offset] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((pixels[offset] & 0xff00) * alpha >> 8 & 0xff00);
            pixels[offset++] = color + ((pixels[offset] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((pixels[offset] & 0xff00) * alpha >> 8 & 0xff00);
            pixels[offset++] = color + ((pixels[offset] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((pixels[offset] & 0xff00) * alpha >> 8 & 0xff00);
            pixels[offset++] = color + ((pixels[offset] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((pixels[offset] & 0xff00) * alpha >> 8 & 0xff00);
        }
        for (length = xB - xA & 3; --length >= 0; )
            pixels[offset++] = color + ((pixels[offset] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((pixels[offset] & 0xff00) * alpha >> 8 & 0xff00);
    }

    public static void fillTexturedTriangle(int yA, int yB, int yC, int xA, int xB, int xC, int lightnessA, int lightnessB,
                                            int lightnessC, int viewXA, int viewXB, int viewXC, int viewYA, int viewYB, int viewYC,
                                            int viewZA, int viewZB, int viewZC, int id) {
        int[] texels = getTexels(id);
        opaque = !textureHasTransparency[id];

        viewXB = viewXA - viewXB;
        viewYB = viewYA - viewYB;
        viewZB = viewZA - viewZB;

        viewXC -= viewXA;
        viewYC -= viewYA;
        viewZC -= viewZA;

        int l4 = viewXC * viewYA - viewYC * viewXA << 14;
        int i5 = viewYC * viewZA - viewZC * viewYA << 8;
        int j5 = viewZC * viewXA - viewXC * viewZA << 5;

        int k5 = viewXB * viewYA - viewYB * viewXA << 14;
        int l5 = viewYB * viewZA - viewZB * viewYA << 8;
        int i6 = viewZB * viewXA - viewXB * viewZA << 5;

        int j6 = viewYB * viewXC - viewXB * viewYC << 14;
        int k6 = viewZB * viewYC - viewYB * viewZC << 8;
        int l6 = viewXB * viewZC - viewZB * viewXC << 5;

        int xStepAB = 0;
        int lightnessStepAB = 0;
        if (yB != yA) {
            xStepAB = (xB - xA << 16) / (yB - yA);
            lightnessStepAB = (lightnessB - lightnessA << 16) / (yB - yA);
        }

        int xStepBC = 0;
        int lightnessStepBC = 0;
        if (yC != yB) {
            xStepBC = (xC - xB << 16) / (yC - yB);
            lightnessStepBC = (lightnessC - lightnessB << 16) / (yC - yB);
        }

        int xStepAC = 0;
        int lightnessStepAC = 0;
        if (yC != yA) {
            xStepAC = (xA - xC << 16) / (yA - yC);
            lightnessStepAC = (lightnessA - lightnessC << 16) / (yA - yC);
        }

        if (yA <= yB && yA <= yC) {
            if (yA >= bottom)
                return;
            if (yB > bottom)
                yB = bottom;
            if (yC > bottom)
                yC = bottom;
            if (yB < yC) {
                xC = xA <<= 16;
                lightnessC = lightnessA <<= 16;
                if (yA < 0) {
                    xC -= xStepAC * yA;
                    xA -= xStepAB * yA;
                    lightnessC -= lightnessStepAC * yA;
                    lightnessA -= lightnessStepAB * yA;
                    yA = 0;
                }
                xB <<= 16;
                lightnessB <<= 16;
                if (yB < 0) {
                    xB -= xStepBC * yB;
                    lightnessB -= lightnessStepBC * yB;
                    yB = 0;
                }
                int k8 = yA - centerY;
                l4 += j5 * k8;
                k5 += i6 * k8;
                j6 += l6 * k8;
                if (yA != yB && xStepAC < xStepAB || yA == yB && xStepAC > xStepBC) {
                    yC -= yB;
                    yB -= yA;
                    yA = offsets[yA];
                    while (--yB >= 0) {
                        drawTexturedScanline(dest, texels, 0, 0, yA, xC >> 16, xA >> 16, lightnessC >> 8, lightnessA >> 8,
                            l4, k5, j6, i5, l5, k6);
                        xC += xStepAC;
                        xA += xStepAB;
                        lightnessC += lightnessStepAC;
                        lightnessA += lightnessStepAB;
                        yA += width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    while (--yC >= 0) {
                        drawTexturedScanline(dest, texels, 0, 0, yA, xC >> 16, xB >> 16, lightnessC >> 8, lightnessB >> 8,
                            l4, k5, j6, i5, l5, k6);
                        xC += xStepAC;
                        xB += xStepBC;
                        lightnessC += lightnessStepAC;
                        lightnessB += lightnessStepBC;
                        yA += width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    return;
                }
                yC -= yB;
                yB -= yA;
                yA = offsets[yA];
                while (--yB >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yA, xA >> 16, xC >> 16, lightnessA >> 8, lightnessC >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xC += xStepAC;
                    xA += xStepAB;
                    lightnessC += lightnessStepAC;
                    lightnessA += lightnessStepAB;
                    yA += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--yC >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yA, xB >> 16, xC >> 16, lightnessB >> 8, lightnessC >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xC += xStepAC;
                    xB += xStepBC;
                    lightnessC += lightnessStepAC;
                    lightnessB += lightnessStepBC;
                    yA += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            xB = xA <<= 16;
            lightnessB = lightnessA <<= 16;
            if (yA < 0) {
                xB -= xStepAC * yA;
                xA -= xStepAB * yA;
                lightnessB -= lightnessStepAC * yA;
                lightnessA -= lightnessStepAB * yA;
                yA = 0;
            }
            xC <<= 16;
            lightnessC <<= 16;
            if (yC < 0) {
                xC -= xStepBC * yC;
                lightnessC -= lightnessStepBC * yC;
                yC = 0;
            }
            int l8 = yA - centerY;
            l4 += j5 * l8;
            k5 += i6 * l8;
            j6 += l6 * l8;
            if (yA != yC && xStepAC < xStepAB || yA == yC && xStepBC > xStepAB) {
                yB -= yC;
                yC -= yA;
                yA = offsets[yA];
                while (--yC >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yA, xB >> 16, xA >> 16, lightnessB >> 8, lightnessA >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xB += xStepAC;
                    xA += xStepAB;
                    lightnessB += lightnessStepAC;
                    lightnessA += lightnessStepAB;
                    yA += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--yB >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yA, xC >> 16, xA >> 16, lightnessC >> 8, lightnessA >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xC += xStepBC;
                    xA += xStepAB;
                    lightnessC += lightnessStepBC;
                    lightnessA += lightnessStepAB;
                    yA += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            yB -= yC;
            yC -= yA;
            yA = offsets[yA];
            while (--yC >= 0) {
                drawTexturedScanline(dest, texels, 0, 0, yA, xA >> 16, xB >> 16, lightnessA >> 8, lightnessB >> 8, l4, k5,
                    j6, i5, l5, k6);
                xB += xStepAC;
                xA += xStepAB;
                lightnessB += lightnessStepAC;
                lightnessA += lightnessStepAB;
                yA += width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--yB >= 0) {
                drawTexturedScanline(dest, texels, 0, 0, yA, xA >> 16, xC >> 16, lightnessA >> 8, lightnessC >> 8, l4, k5,
                    j6, i5, l5, k6);
                xC += xStepBC;
                xA += xStepAB;
                lightnessC += lightnessStepBC;
                lightnessA += lightnessStepAB;
                yA += width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        if (yB <= yC) {
            if (yB >= bottom)
                return;
            if (yC > bottom)
                yC = bottom;
            if (yA > bottom)
                yA = bottom;
            if (yC < yA) {
                xA = xB <<= 16;
                lightnessA = lightnessB <<= 16;
                if (yB < 0) {
                    xA -= xStepAB * yB;
                    xB -= xStepBC * yB;
                    lightnessA -= lightnessStepAB * yB;
                    lightnessB -= lightnessStepBC * yB;
                    yB = 0;
                }
                xC <<= 16;
                lightnessC <<= 16;
                if (yC < 0) {
                    xC -= xStepAC * yC;
                    lightnessC -= lightnessStepAC * yC;
                    yC = 0;
                }
                int i9 = yB - centerY;
                l4 += j5 * i9;
                k5 += i6 * i9;
                j6 += l6 * i9;
                if (yB != yC && xStepAB < xStepBC || yB == yC && xStepAB > xStepAC) {
                    yA -= yC;
                    yC -= yB;
                    yB = offsets[yB];
                    while (--yC >= 0) {
                        drawTexturedScanline(dest, texels, 0, 0, yB, xA >> 16, xB >> 16, lightnessA >> 8, lightnessB >> 8,
                            l4, k5, j6, i5, l5, k6);
                        xA += xStepAB;
                        xB += xStepBC;
                        lightnessA += lightnessStepAB;
                        lightnessB += lightnessStepBC;
                        yB += width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    while (--yA >= 0) {
                        drawTexturedScanline(dest, texels, 0, 0, yB, xA >> 16, xC >> 16, lightnessA >> 8, lightnessC >> 8,
                            l4, k5, j6, i5, l5, k6);
                        xA += xStepAB;
                        xC += xStepAC;
                        lightnessA += lightnessStepAB;
                        lightnessC += lightnessStepAC;
                        yB += width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    return;
                }
                yA -= yC;
                yC -= yB;
                yB = offsets[yB];
                while (--yC >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yB, xB >> 16, xA >> 16, lightnessB >> 8, lightnessA >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xA += xStepAB;
                    xB += xStepBC;
                    lightnessA += lightnessStepAB;
                    lightnessB += lightnessStepBC;
                    yB += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--yA >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yB, xC >> 16, xA >> 16, lightnessC >> 8, lightnessA >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xA += xStepAB;
                    xC += xStepAC;
                    lightnessA += lightnessStepAB;
                    lightnessC += lightnessStepAC;
                    yB += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            xC = xB <<= 16;
            lightnessC = lightnessB <<= 16;
            if (yB < 0) {
                xC -= xStepAB * yB;
                xB -= xStepBC * yB;
                lightnessC -= lightnessStepAB * yB;
                lightnessB -= lightnessStepBC * yB;
                yB = 0;
            }
            xA <<= 16;
            lightnessA <<= 16;
            if (yA < 0) {
                xA -= xStepAC * yA;
                lightnessA -= lightnessStepAC * yA;
                yA = 0;
            }
            int j9 = yB - centerY;
            l4 += j5 * j9;
            k5 += i6 * j9;
            j6 += l6 * j9;
            if (xStepAB < xStepBC) {
                yC -= yA;
                yA -= yB;
                yB = offsets[yB];
                while (--yA >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yB, xC >> 16, xB >> 16, lightnessC >> 8, lightnessB >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xC += xStepAB;
                    xB += xStepBC;
                    lightnessC += lightnessStepAB;
                    lightnessB += lightnessStepBC;
                    yB += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--yC >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yB, xA >> 16, xB >> 16, lightnessA >> 8, lightnessB >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xA += xStepAC;
                    xB += xStepBC;
                    lightnessA += lightnessStepAC;
                    lightnessB += lightnessStepBC;
                    yB += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            yC -= yA;
            yA -= yB;
            yB = offsets[yB];
            while (--yA >= 0) {
                drawTexturedScanline(dest, texels, 0, 0, yB, xB >> 16, xC >> 16, lightnessB >> 8, lightnessC >> 8, l4, k5,
                    j6, i5, l5, k6);
                xC += xStepAB;
                xB += xStepBC;
                lightnessC += lightnessStepAB;
                lightnessB += lightnessStepBC;
                yB += width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--yC >= 0) {
                drawTexturedScanline(dest, texels, 0, 0, yB, xB >> 16, xA >> 16, lightnessB >> 8, lightnessA >> 8, l4, k5,
                    j6, i5, l5, k6);
                xA += xStepAC;
                xB += xStepBC;
                lightnessA += lightnessStepAC;
                lightnessB += lightnessStepBC;
                yB += width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        if (yC >= bottom)
            return;
        if (yA > bottom)
            yA = bottom;
        if (yB > bottom)
            yB = bottom;
        if (yA < yB) {
            xB = xC <<= 16;
            lightnessB = lightnessC <<= 16;
            if (yC < 0) {
                xB -= xStepBC * yC;
                xC -= xStepAC * yC;
                lightnessB -= lightnessStepBC * yC;
                lightnessC -= lightnessStepAC * yC;
                yC = 0;
            }
            xA <<= 16;
            lightnessA <<= 16;
            if (yA < 0) {
                xA -= xStepAB * yA;
                lightnessA -= lightnessStepAB * yA;
                yA = 0;
            }
            int k9 = yC - centerY;
            l4 += j5 * k9;
            k5 += i6 * k9;
            j6 += l6 * k9;
            if (xStepBC < xStepAC) {
                yB -= yA;
                yA -= yC;
                yC = offsets[yC];
                while (--yA >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yC, xB >> 16, xC >> 16, lightnessB >> 8, lightnessC >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xB += xStepBC;
                    xC += xStepAC;
                    lightnessB += lightnessStepBC;
                    lightnessC += lightnessStepAC;
                    yC += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--yB >= 0) {
                    drawTexturedScanline(dest, texels, 0, 0, yC, xB >> 16, xA >> 16, lightnessB >> 8, lightnessA >> 8, l4,
                        k5, j6, i5, l5, k6);
                    xB += xStepBC;
                    xA += xStepAB;
                    lightnessB += lightnessStepBC;
                    lightnessA += lightnessStepAB;
                    yC += width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            yB -= yA;
            yA -= yC;
            yC = offsets[yC];
            while (--yA >= 0) {
                drawTexturedScanline(dest, texels, 0, 0, yC, xC >> 16, xB >> 16, lightnessC >> 8, lightnessB >> 8, l4, k5,
                    j6, i5, l5, k6);
                xB += xStepBC;
                xC += xStepAC;
                lightnessB += lightnessStepBC;
                lightnessC += lightnessStepAC;
                yC += width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--yB >= 0) {
                drawTexturedScanline(dest, texels, 0, 0, yC, xA >> 16, xB >> 16, lightnessA >> 8, lightnessB >> 8, l4, k5,
                    j6, i5, l5, k6);
                xB += xStepBC;
                xA += xStepAB;
                lightnessB += lightnessStepBC;
                lightnessA += lightnessStepAB;
                yC += width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        xA = xC <<= 16;
        lightnessA = lightnessC <<= 16;
        if (yC < 0) {
            xA -= xStepBC * yC;
            xC -= xStepAC * yC;
            lightnessA -= lightnessStepBC * yC;
            lightnessC -= lightnessStepAC * yC;
            yC = 0;
        }
        xB <<= 16;
        lightnessB <<= 16;
        if (yB < 0) {
            xB -= xStepAB * yB;
            lightnessB -= lightnessStepAB * yB;
            yB = 0;
        }
        int l9 = yC - centerY;
        l4 += j5 * l9;
        k5 += i6 * l9;
        j6 += l6 * l9;
        if (xStepBC < xStepAC) {
            yA -= yB;
            yB -= yC;
            yC = offsets[yC];
            while (--yB >= 0) {
                drawTexturedScanline(dest, texels, 0, 0, yC, xA >> 16, xC >> 16, lightnessA >> 8, lightnessC >> 8, l4, k5,
                    j6, i5, l5, k6);
                xA += xStepBC;
                xC += xStepAC;
                lightnessA += lightnessStepBC;
                lightnessC += lightnessStepAC;
                yC += width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--yA >= 0) {
                drawTexturedScanline(dest, texels, 0, 0, yC, xB >> 16, xC >> 16, lightnessB >> 8, lightnessC >> 8, l4, k5,
                    j6, i5, l5, k6);
                xB += xStepAB;
                xC += xStepAC;
                lightnessB += lightnessStepAB;
                lightnessC += lightnessStepAC;
                yC += width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        yA -= yB;
        yB -= yC;
        yC = offsets[yC];
        while (--yB >= 0) {
            drawTexturedScanline(dest, texels, 0, 0, yC, xC >> 16, xA >> 16, lightnessC >> 8, lightnessA >> 8, l4, k5, j6,
                i5, l5, k6);
            xA += xStepBC;
            xC += xStepAC;
            lightnessA += lightnessStepBC;
            lightnessC += lightnessStepAC;
            yC += width;
            l4 += j5;
            k5 += i6;
            j6 += l6;
        }
        while (--yA >= 0) {
            drawTexturedScanline(dest, texels, 0, 0, yC, xC >> 16, xB >> 16, lightnessC >> 8, lightnessB >> 8, l4, k5, j6,
                i5, l5, k6);
            xB += xStepAB;
            xC += xStepAC;
            lightnessB += lightnessStepAB;
            lightnessC += lightnessStepAC;
            yC += width;
            l4 += j5;
            k5 += i6;
            j6 += l6;
        }
    }

    public static void drawTexturedScanline(int[] dst, int[] texels, int uA, int vA, int off, int xA, int xB, int lightnessA,
                                            int lightnessB, int verticalA, int verticalB, int verticalC, int horizontalA, int horizontalB, int horizontalC) {
        if (xA >= xB)
            return;
        int lightnessSlope;
        int length;
        if (testX) {
            lightnessSlope = (lightnessB - lightnessA) / (xB - xA);
            if (xB > rightX)
                xB = rightX;
            if (xA < 0) {
                lightnessA -= xA * lightnessSlope;
                xA = 0;
            }
            if (xA >= xB)
                return;
            length = xB - xA >> 3;
            lightnessSlope <<= 12;
            lightnessA <<= 9;
        } else {
            if (xB - xA > 7) {
                length = xB - xA >> 3;
                lightnessSlope = (lightnessB - lightnessA) * reciprical15[length] >> 6;
            } else {
                length = 0;
                lightnessSlope = 0;
            }
            lightnessA <<= 9;
        }
        off += xA;
        if (lowMemory) {
            int uB = 0;
            int vB = 0;
            int delta = xA - centerX;
            verticalA += (horizontalA >> 3) * delta;
            verticalB += (horizontalB >> 3) * delta;
            verticalC += (horizontalC >> 3) * delta;
            int c = verticalC >> 12;
            if (c != 0) {
                uA = verticalA / c;
                vA = verticalB / c;
                if (uA < 0)
                    uA = 0;
                else if (uA > (63 << 6))
                    uA = 63 << 6;
            }
            verticalA += horizontalA;
            verticalB += horizontalB;
            verticalC += horizontalC;
            c = verticalC >> 12;
            if (c != 0) {
                uB = verticalA / c;
                vB = verticalB / c;
                if (uB < 7)
                    uB = 7;
                else if (uB > (63 << 6))
                    uB = 63 << 6;
            }
            int uStep = uB - uA >> 3;
            int vStep = vB - vA >> 3;
            uA += (lightnessA & (3 << 21)) >> 3;
            int lightness = lightnessA >> 23;
            if (opaque) {
                while (length-- > 0) {
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA += uStep;
                    vA += vStep;
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA += uStep;
                    vA += vStep;
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA += uStep;
                    vA += vStep;
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA += uStep;
                    vA += vStep;
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA += uStep;
                    vA += vStep;
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA += uStep;
                    vA += vStep;
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA += uStep;
                    vA += vStep;
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA = uB;
                    vA = vB;
                    verticalA += horizontalA;
                    verticalB += horizontalB;
                    verticalC += horizontalC;
                    c = verticalC >> 12;
                    if (c != 0) {
                        uB = verticalA / c;
                        vB = verticalB / c;
                        if (uB < 7)
                            uB = 7;
                        else if (uB > (63 << 6))
                            uB = 63 << 6;
                    }
                    uStep = uB - uA >> 3;
                    vStep = vB - vA >> 3;
                    lightnessA += lightnessSlope;
                    uA += (lightnessA & (3 << 21)) >> 3;
                    lightness = lightnessA >> 23;
                }
                length = xB - xA & 7;
                while (length-- > 0) {
                    dst[off++] = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness;
                    uA += uStep;
                    vA += vStep;
                }

                return;
            }
            while (length-- > 0) {
                int rgb;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA += uStep;
                vA += vStep;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA += uStep;
                vA += vStep;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA += uStep;
                vA += vStep;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA += uStep;
                vA += vStep;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA += uStep;
                vA += vStep;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA += uStep;
                vA += vStep;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA += uStep;
                vA += vStep;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA = uB;
                vA = vB;
                verticalA += horizontalA;
                verticalB += horizontalB;
                verticalC += horizontalC;
                c = verticalC >> 12;
                if (c != 0) {
                    uB = verticalA / c;
                    vB = verticalB / c;
                    if (uB < 7)
                        uB = 7;
                    else if (uB > (63 << 6))
                        uB = 63 << 6;
                }
                uStep = uB - uA >> 3;
                vStep = vB - vA >> 3;
                lightnessA += lightnessSlope;
                uA += (lightnessA & (3 << 21)) >> 3;
                lightness = lightnessA >> 23;
            }
            length = xB - xA & 7;
            while (length-- > 0) {
                int rgb;
                if ((rgb = texels[(vA & (63 << 6)) + (uA >> 6)] >>> lightness) != 0)
                    dst[off] = rgb;
                off++;
                uA += uStep;
                vA += vStep;
            }

            return;
        }
        int u2 = 0;
        int v2 = 0;
        int delta = xA - centerX;
        verticalA += (horizontalA >> 3) * delta;
        verticalB += (horizontalB >> 3) * delta;
        verticalC += (horizontalC >> 3) * delta;
        int realC = verticalC >> 14;
        if (realC != 0) {
            uA = verticalA / realC;
            vA = verticalB / realC;
            if (uA < 0)
                uA = 0;
            else if (uA > (127 << 7))
                uA = (127 << 7);
        }
        verticalA += horizontalA;
        verticalB += horizontalB;
        verticalC += horizontalC;
        realC = verticalC >> 14;
        if (realC != 0) {
            u2 = verticalA / realC;
            v2 = verticalB / realC;
            if (u2 < 7)
                u2 = 7;
            else if (u2 > (127 << 7))
                u2 = (127 << 7);
        }
        int deltaU = u2 - uA >> 3;
        int deltaV = v2 - vA >> 3;
        uA += lightnessA & (3 << 21);
        int lightness = lightnessA >> 23;
        if (opaque) {
            while (length-- > 0) {
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA += deltaU;
                vA += deltaV;
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA += deltaU;
                vA += deltaV;
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA += deltaU;
                vA += deltaV;
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA += deltaU;
                vA += deltaV;
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA += deltaU;
                vA += deltaV;
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA += deltaU;
                vA += deltaV;
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA += deltaU;
                vA += deltaV;
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA = u2;
                vA = v2;
                verticalA += horizontalA;
                verticalB += horizontalB;
                verticalC += horizontalC;
                realC = verticalC >> 14;
                if (realC != 0) {
                    u2 = verticalA / realC;
                    v2 = verticalB / realC;
                    if (u2 < 7)
                        u2 = 7;
                    else if (u2 > (127 << 7))
                        u2 = 127 << 7;
                }
                deltaU = u2 - uA >> 3;
                deltaV = v2 - vA >> 3;
                lightnessA += lightnessSlope;
                uA += lightnessA & (3 << 21);
                lightness = lightnessA >> 23;
            }
            for (length = xB - xA & 7; length-- > 0; ) {
                dst[off++] = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness;
                uA += deltaU;
                vA += deltaV;
            }

            return;
        }
        while (length-- > 0) {
            int rgb;
            if ((rgb = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA += deltaU;
            vA += deltaV;
            if ((rgb = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA += deltaU;
            vA += deltaV;
            if ((rgb = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA += deltaU;
            vA += deltaV;
            if ((rgb = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA += deltaU;
            vA += deltaV;
            if ((rgb = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA += deltaU;
            vA += deltaV;
            if ((rgb = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA += deltaU;
            vA += deltaV;
            if ((rgb = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA += deltaU;
            vA += deltaV;
            if ((rgb = texels[(vA & (127 << 7)) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA = u2;
            vA = v2;
            verticalA += horizontalA;
            verticalB += horizontalB;
            verticalC += horizontalC;
            realC = verticalC >> 14;
            if (realC != 0) {
                u2 = verticalA / realC;
                v2 = verticalB / realC;
                if (u2 < 7)
                    u2 = 7;
                else if (u2 > (127 << 7))
                    u2 = 127 << 7;
            }
            deltaU = u2 - uA >> 3;
            deltaV = v2 - vA >> 3;
            lightnessA += lightnessSlope;
            uA += lightnessA & (3 << 21);
            lightness = lightnessA >> 23;
        }
        length = xB - xA & 7;
        while (length-- > 0) {
            int rgb;
            if ((rgb = texels[(vA & 0x3f80) + (uA >> 7)] >>> lightness) != 0)
                dst[off] = rgb;
            off++;
            uA += deltaU;
            vA += deltaV;
        }
    }
}
