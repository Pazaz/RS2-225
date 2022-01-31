package com.jagex.runescape.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

public class FloType {

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("flo.dat", null));

        count = buffer.readWord();

        if (instances == null) {
            instances = new FloType[count];
        }

        for (int j = 0; j < count; j++) {
            if (instances[j] == null) {
                instances[j] = new FloType();
            }

            instances[j].read(buffer);
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.readByte();
            switch (opcode) {
                case 0:
                    return;
                case 1:
                    rgb = buffer.readSWord();
                    setColor(rgb);
                    break;
                case 2:
                    textureIndex = buffer.readByte();
                    break;
                case 3:
                    break;
                case 5:
                    occlude = false;
                    break;
                case 6:
                    name = buffer.readString();
                    break;
                default:
                    System.out.println("Error unrecognised config code: " + opcode);
                    break;
            }
        } while (true);
    }

    public void setColor(int j) {
        double r = (double) (j >> 16 & 0xff) / 256D;
        double g = (double) (j >> 8 & 0xff) / 256D;
        double b = (double) (j & 0xff) / 256D;

        double min = Math.min(Math.min(r, g), b);
        double max = Math.max(Math.max(r, g), b);

        double h = 0.0D;
        double s = 0.0D;
        double l = (min + max) / 2D;

        if (min != max) {
            if (l < 0.5D) {
                s = (max - min) / (max + min);
            } else if (l >= 0.5D) {
                s = (max - min) / (2D - max - min);
            }

            if (r == max) {
                h = (g - b) / (max - min);
            } else if (g == max) {
                h = 2D + (b - r) / (max - min);
            } else if (b == max) {
                h = 4D + (r - g) / (max - min);
            }
        }

        h /= 6D;

        hue = (int) (h * 256D);
        saturation = (int) (s * 256D);
        lightness = (int) (l * 256D);

        if (saturation < 0) {
            saturation = 0;
        } else if (saturation > 255) {
            saturation = 255;
        }

        if (lightness < 0) {
            lightness = 0;
        } else if (lightness > 255) {
            lightness = 255;
        }

        if (l > 0.5D) {
            hsl16 = (int) ((1.0D - l) * s * 512D);
        } else {
            hsl16 = (int) (l * s * 512D);
        }

        if (hsl16 < 1) {
            hsl16 = 1;
        }

        blendHue = (int) (h * (double) hsl16);

        int randHue = (hue + (int) (Math.random() * 16D)) - 8;
        if (randHue < 0) {
            randHue = 0;
        } else if (randHue > 255) {
            randHue = 255;
        }

        int randSaturation;
        randSaturation = (saturation + (int) (Math.random() * 48D)) - 24;
        if (randSaturation < 0) {
            randSaturation = 0;
        } else if (randSaturation > 255) {
            randSaturation = 255;
        }

        int randLightness = (lightness + (int) (Math.random() * 48D)) - 24;
        if (randLightness < 0) {
            randLightness = 0;
        } else if (randLightness > 255) {
            randLightness = 255;
        }

        blendHueMultiplier = setHsl16(randHue, randSaturation, randLightness);
    }

    public int setHsl16(int h, int s, int l) {
        if (l > 179) {
            s /= 2;
        }

        if (l > 192) {
            s /= 2;
        }

        if (l > 217) {
            s /= 2;
        }

        if (l > 243) {
            s /= 2;
        }

        return (h / 4 << 10) + (s / 32 << 7) + l / 2;
    }

    public FloType() {
        textureIndex = -1;
        occlude = true;
    }

    public static int count;
    public static FloType[] instances;
    public int rgb;
    public int textureIndex;
    public boolean occlude;
    public String name;
    public int hue;
    public int saturation;
    public int lightness;
    public int blendHue;
    public int hsl16;
    public int blendHueMultiplier;

}
