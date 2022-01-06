// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class37 {

    public Class37(byte byte0, int[] ai) {
        anIntArray650 = new int[256];
        anIntArray649 = new int[256];
        for (int i = 0; i < ai.length; i++)
            anIntArray649[i] = ai[i];

        if (byte0 == 1)
            byte0 = 0;
        else
            throw new NullPointerException();
        method348();
    }

    public int method346() {
        if (anInt648-- == 0) {
            method347();
            anInt648 = 255;
        }
        return anIntArray649[anInt648];
    }

    public void method347() {
        anInt652 += ++anInt653;
        for (int i = 0; i < 256; i++) {
            int j = anIntArray650[i];
            switch (i & 3) {
                case 0: // '\0'
                    anInt651 ^= anInt651 << 13;
                    break;

                case 1: // '\001'
                    anInt651 ^= anInt651 >>> 6;
                    break;

                case 2: // '\002'
                    anInt651 ^= anInt651 << 2;
                    break;

                case 3: // '\003'
                    anInt651 ^= anInt651 >>> 16;
                    break;
            }
            anInt651 += anIntArray650[i + 128 & 0xff];
            int k;
            anIntArray650[i] = k = anIntArray650[(j & 0x3fc) >> 2] + anInt651 + anInt652;
            anIntArray649[i] = anInt652 = anIntArray650[(k >> 8 & 0x3fc) >> 2] + j;
        }

    }

    public void method348() {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l = i1 = j1 = k1 = l1 = i2 = j2 = k2 = 0x9e3779b9;
        for (int i = 0; i < 4; i++) {
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
        }

        for (int j = 0; j < 256; j += 8) {
            l += anIntArray649[j];
            i1 += anIntArray649[j + 1];
            j1 += anIntArray649[j + 2];
            k1 += anIntArray649[j + 3];
            l1 += anIntArray649[j + 4];
            i2 += anIntArray649[j + 5];
            j2 += anIntArray649[j + 6];
            k2 += anIntArray649[j + 7];
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
            anIntArray650[j] = l;
            anIntArray650[j + 1] = i1;
            anIntArray650[j + 2] = j1;
            anIntArray650[j + 3] = k1;
            anIntArray650[j + 4] = l1;
            anIntArray650[j + 5] = i2;
            anIntArray650[j + 6] = j2;
            anIntArray650[j + 7] = k2;
        }

        for (int k = 0; k < 256; k += 8) {
            l += anIntArray650[k];
            i1 += anIntArray650[k + 1];
            j1 += anIntArray650[k + 2];
            k1 += anIntArray650[k + 3];
            l1 += anIntArray650[k + 4];
            i2 += anIntArray650[k + 5];
            j2 += anIntArray650[k + 6];
            k2 += anIntArray650[k + 7];
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
            anIntArray650[k] = l;
            anIntArray650[k + 1] = i1;
            anIntArray650[k + 2] = j1;
            anIntArray650[k + 3] = k1;
            anIntArray650[k + 4] = l1;
            anIntArray650[k + 5] = i2;
            anIntArray650[k + 6] = j2;
            anIntArray650[k + 7] = k2;
        }

        method347();
        anInt648 = 256;
    }

    public int anInt648;
    public int[] anIntArray649;
    public int[] anIntArray650;
    public int anInt651;
    public int anInt652;
    public int anInt653;
}
