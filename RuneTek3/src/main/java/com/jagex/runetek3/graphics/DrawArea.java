package com.jagex.runetek3.graphics;

import java.awt.*;
import java.awt.image.*;

public class DrawArea {

    public int[] pixels;
    public float[] luma;
    public int width;
    public int height;
    public BufferedImage image;

    public DrawArea(int width, int height) {
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
        this.luma = new float[width * height];
        bind();
    }

    public void bind() {
        Draw2D.prepare(width, pixels, height);
    }

    public void drawImage(int y, Graphics g, int x) {
        g.drawImage(image, x, y, null);
    }

    float rgb2luminance(int color) {
        float r = (color >> 16) & 0xFF;
        float g = (color >> 8) & 0xFF;
        float b = color & 0xFF;
        return 0.2126f * r + 0.7152f * g + 0.0722f * b; // linear approximation
    }

    float rgb2luma(int color) {
        float r = (color >> 16) & 0xFF;
        float g = (color >> 8) & 0xFF;
        float b = color & 0xFF;
        return (float)Math.sqrt(0.299f * r + 0.587f * g + 0.114f * b); // gamma corrected
    }

    public void greyscale() {
        for (int i = 0; i < width * height; ++i) {
            float lum = rgb2luminance(pixels[i]);
            int r = (int)lum & 0xFF;
            int g = (int)lum & 0xFF;
            int b = (int)lum & 0xFF;
            pixels[i] = r << 16 | g << 8 | b;
        }
    }

    // 0.0833 - upper limit (default, the start of visible unfiltered edges)
    // 0.0625 - high quality (faster)
    // 0.0312 - visible limit (slower)
    static final float FXAA_THRESHOLD_MAX = 0.0312f;

    // 0.333 - too little (faster)
    // 0.250 - low quality
    // 0.166 - default
    // 0.125 - high quality
    // 0.063 - overkill (slower)
    static final float FXAA_THRESHOLD_MIN = 0.063f;

    static final float SUBPIXEL_BLEND_FACTOR = 0.75f;

    float saturate(float x) {
        return Math.max(0, Math.min(1, x));
    }

    float smoothstep(float a, float b, float x) {
        float t = saturate((x - a) / (b - a));
        return t * t * (3.0f - (2.0f * t));
    }

    public void scanline() {
        for (int y = 0; y < height; y++) {
            int channel = 0;

            for (int x = 0; x < width; ++x) {
                int pixel = x + y * width;
                int color = pixels[pixel];

                int r = (color >> 16) & 0xFF;
                int g = (color >> 8) & 0xFF;
                int b = color & 0xFF;

                if (y % 2 == 0) {
                    float scanlineMul = 0.9f;
                    r -= scanlineMul * r;
                    g -= scanlineMul * g;
                    b -= scanlineMul * b;
                } else {
                    if (r > 250) {
                        r *= 0.9;
                    }
                    if (g > 250) {
                        g *= 0.9;
                    }
                    if (b > 250) {
                        b *= 0.9;
                    }
                }

                if (channel == 0) {
                    r *= 1.2;
                    if (r > 255) {
                        r = 255;
                    }
                } else if (channel == 1) {
                    g *= 1.2;
                    if (g > 255) {
                        g = 255;
                    }
                } else if (channel == 2) {
                    b *= 1.2;
                    if (b > 255) {
                        b = 255;
                    }
                }

                channel++;
                if (channel > 2) {
                    channel = 0;
                }

                pixels[pixel] = r << 16 | g << 8 | b;
            }
        }
    }

    public void fxaa() {
        // calculate luma for every pixel
        for (int i = 0; i < width * height; ++i) {
            luma[i] = rgb2luma(pixels[i]);
        }

        // check whether edges need to be blended for every pixel
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int pixel = x + y * width;

                // check neighbors
                float lumaN;
                if (y > 0) {
                    lumaN = luma[pixel - width];
                } else {
                    lumaN = luma[pixel];
                }
                float lumaE;
                if (x < width - 1) {
                    lumaE = luma[pixel + 1];
                } else {
                    lumaE = luma[pixel];
                }
                float lumaS;
                if (y < height - 1) {
                    lumaS = luma[pixel + width];
                } else {
                    lumaS = luma[pixel];
                }
                float lumaW;
                if (x > 0) {
                    lumaW = luma[pixel - 1];
                } else {
                    lumaW = luma[pixel];
                }
                float lumaM = luma[pixel];

                float lumaMin = Math.min(lumaM, Math.min(Math.min(lumaN, lumaS), Math.min(lumaW, lumaE)));
                float lumaMax = Math.max(lumaM, Math.max(Math.max(lumaN, lumaS), Math.max(lumaW, lumaE)));
                float lumaRange = lumaMax - lumaMin;

                // CanSkipFXAA - contrast threshold
                if (lumaRange < Math.max(FXAA_THRESHOLD_MAX, lumaMax * FXAA_THRESHOLD_MIN)) {
//                    pixels[pixel] = 0;
                    continue;
                }

                // GetLumaNeighborhood
                float lumaNW;
                if (x > 0 && y > 0) {
                    lumaNW = luma[pixel - width - 1];
                } else {
                    lumaNW = luma[pixel];
                }
                float lumaNE;
                if (x < width - 1 && y > 0) {
                    lumaNE = luma[pixel - width + 1];
                } else {
                    lumaNE = luma[pixel];
                }
                float lumaSW;
                if (x > 0 && y < height - 1) {
                    lumaSW = luma[pixel + width - 1];
                } else {
                    lumaSW = luma[pixel];
                }
                float lumaSE;
                if (x < width - 1 && y < height - 1) {
                    lumaSE = luma[pixel + width + 1];
                } else {
                    lumaSE = luma[pixel];
                }

                // detect edge
                float horizontal = 2.0f * Math.abs(lumaN + lumaS - 2.0f * lumaM) +
                                         Math.abs(lumaNE + lumaSE - 2.0f * lumaE) +
                                         Math.abs(lumaNW + lumaSW - 2.0f * lumaW);
                float vertical = 2.0f * Math.abs(lumaE + lumaW - 2.0f * lumaM) +
                                       Math.abs(lumaNE + lumaNW - 2.0f * lumaN) +
                                       Math.abs(lumaSE + lumaSW - 2.0f * lumaS);
                boolean isHorizontal = horizontal >= vertical;

                int offX = 0;
                int offY = 0;
                float lumaPos;
                float lumaNeg;

                // IsHorizontalEdge
                if (isHorizontal) {
                    offX = 1;
                    lumaPos = lumaN;
                    lumaNeg = lumaS;
                } else {
                    offY = 1;
                    lumaPos = lumaE;
                    lumaNeg = lumaW;
                }

                // GetFXAAEdge
                float gradientP = Math.abs(lumaPos - lumaM);
                float gradientN = Math.abs(lumaNeg - lumaM);

//                float lumaGradient;
//                float otherLuma;
                if (gradientP < gradientN) {
                    offX = -offX;
                    offY = -offY;
//                    lumaGradient = gradientN;
//                    otherLuma = lumaNeg;
                } else {
//                    lumaGradient = gradientP;
//                    otherLuma = lumaPos;
                }

                // GetEdgeBlendFactor
//                float edgeLuma = 0.5f * (lumaM + otherLuma);
//                float gradientThreshold = 0.25f * lumaGradient;
//
//                int edgeX = offX;
//                int edgeY = offY;
//                if (isHorizontal) {
//                    edgeX += offX;
//                } else {
//                    edgeY += offY;
//                }
//
//                if (x + edgeX >= 0 && x + edgeX <= width - 1 && y + edgeY >= 0 && y + edgeY <= height - 1) {
//                    float lumaGradientP = Math.abs(luma[(x + edgeX) + (y + edgeY) * width] - edgeLuma);
//                    boolean atEndP = lumaGradientP >= gradientThreshold;
//
//                    for (int i = 0; i < 99 && !atEndP; i++) {
//                        if (isHorizontal) {
//                            edgeX += offX;
//                        } else {
//                            edgeY += offY;
//                        }
//                        if (x + edgeX >= 0 && x + edgeX <= width - 1 && y + edgeY >= 0 && y + edgeY <= height - 1) {
//                            lumaGradientP = Math.abs(luma[(x + edgeX) + (y + edgeY) * width] - edgeLuma);
//                            atEndP = lumaGradientP >= gradientThreshold;
//                        } else {
//                            atEndP = true;
//                        }
//                    }
//
//                    float distanceToEndP;
//                    if (isHorizontal) {
//                        distanceToEndP = Math.abs(edgeX - offX);
//                    } else {
//                        distanceToEndP = Math.abs(edgeY - offY);
//                    }
//
//                    int r = (int)(10 * distanceToEndP);
//                    pixels[pixel] = (r & 0xFF) << 16 | (r & 0xFF) << 8 | (r & 0xFF);
//                } else {
//                    pixels[pixel] = 0;
//                }

                if (x + offX < 0 || x + offX > width - 1 || y + offY < 0 || y + offY > height - 1) {
                    continue;
                }

                // GetSubpixelBlendFactor - get blend factor
                float blendFactor = 2.0f * (lumaN + lumaE + lumaS + lumaW);
                blendFactor += lumaNE + lumaNW + lumaSE + lumaSW;
                blendFactor *= 1.0f / 12.0f;
                blendFactor = Math.abs(blendFactor - lumaM);
                blendFactor = saturate(blendFactor / lumaRange);
                blendFactor = smoothstep(0, 1, blendFactor);
                blendFactor *= SUBPIXEL_BLEND_FACTOR;
                float invBlendFactor = 1.0f - blendFactor;

                // FXAAPassFragment - blend
                int color1 = pixels[pixel];
                int color2 = pixels[(x + offX) + (y + offY) * width];

                int r = ((int)((color1 >> 16 & 0xFF) * blendFactor) + (int)((color2 >> 16 & 0xFF) * invBlendFactor)) & 0xFF;
                int g = ((int)((color1 >> 8 & 0xFF) * blendFactor) + (int)((color2 >> 8 & 0xFF) * invBlendFactor)) & 0xFF;
                int b = ((int)((color1 & 0xFF) * blendFactor) + (int)((color2 & 0xFF) * invBlendFactor)) & 0xFF;

                pixels[pixel] = (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF);
            }
        }
    }

}
