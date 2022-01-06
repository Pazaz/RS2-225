package com.runescape;

public class BZip2InputStream {

    public static int read(byte[] dest, int destLen, byte[] src, int srcLen, int srcOffset) {
        synchronized (context) {
            context.src = src;
            context.srcOff = srcOffset;
            context.dest = dest;
            context.anInt611 = 0;
            context.srcLen = srcLen;
            context.destLen = destLen;
            context.anInt619 = 0;
            context.anInt618 = 0;
            context.anInt608 = 0;
            context.anInt609 = 0;
            context.anInt613 = 0;
            context.anInt614 = 0;
            context.anInt621 = 0;
            method335(context);
            destLen -= context.destLen;
            int l = destLen;
            return l;
        }
    }

    public static void method334(BZip2Context context) {
        byte byte4 = context.aByte615;
        int i = context.anInt616;
        int j = context.anInt626;
        int k = context.anInt624;
        int[] ai = BZip2Context.anIntArray629;
        int l = context.anInt623;
        byte[] abyte0 = context.dest;
        int i1 = context.anInt611;
        int j1 = context.destLen;
        int k1 = j1;
        int l1 = context.anInt643 + 1;
        label0:
        do {
            if (i > 0) {
                do {
                    if (j1 == 0)
                        break label0;
                    if (i == 1)
                        break;
                    abyte0[i1] = byte4;
                    i--;
                    i1++;
                    j1--;
                } while (true);
                if (j1 == 0) {
                    i = 1;
                    break;
                }
                abyte0[i1] = byte4;
                i1++;
                j1--;
            }
            boolean flag = true;
            while (flag) {
                flag = false;
                if (j == l1) {
                    i = 0;
                    break label0;
                }
                byte4 = (byte) k;
                l = ai[l];
                byte byte0 = (byte) (l & 0xff);
                l >>= 8;
                j++;
                if (byte0 != k) {
                    k = byte0;
                    if (j1 == 0) {
                        i = 1;
                    } else {
                        abyte0[i1] = byte4;
                        i1++;
                        j1--;
                        flag = true;
                        continue;
                    }
                    break label0;
                }
                if (j != l1)
                    continue;
                if (j1 == 0) {
                    i = 1;
                    break label0;
                }
                abyte0[i1] = byte4;
                i1++;
                j1--;
                flag = true;
            }
            i = 2;
            l = ai[l];
            byte byte1 = (byte) (l & 0xff);
            l >>= 8;
            if (++j != l1)
                if (byte1 != k) {
                    k = byte1;
                } else {
                    i = 3;
                    l = ai[l];
                    byte byte2 = (byte) (l & 0xff);
                    l >>= 8;
                    if (++j != l1)
                        if (byte2 != k) {
                            k = byte2;
                        } else {
                            l = ai[l];
                            byte byte3 = (byte) (l & 0xff);
                            l >>= 8;
                            j++;
                            i = (byte3 & 0xff) + 4;
                            l = ai[l];
                            k = (byte) (l & 0xff);
                            l >>= 8;
                            j++;
                        }
                }
        } while (true);
        int i2 = context.anInt613;
        context.anInt613 += k1 - j1;
        if (context.anInt613 < i2)
            context.anInt614++;
        context.aByte615 = byte4;
        context.anInt616 = i;
        context.anInt626 = j;
        context.anInt624 = k;
        BZip2Context.anIntArray629 = ai;
        context.anInt623 = l;
        context.dest = abyte0;
        context.anInt611 = i1;
        context.destLen = j1;
    }

    public static void method335(BZip2Context context) {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        boolean flag6 = false;
        boolean flag7 = false;
        boolean flag8 = false;
        boolean flag9 = false;
        boolean flag10 = false;
        boolean flag11 = false;
        boolean flag12 = false;
        boolean flag13 = false;
        boolean flag14 = false;
        boolean flag15 = false;
        boolean flag16 = false;
        boolean flag17 = false;
        boolean flag18 = false;
        int k8 = 0;
        int[] ai = null;
        int[] ai1 = null;
        int[] ai2 = null;
        context.anInt620 = 1;
        if (BZip2Context.anIntArray629 == null)
            BZip2Context.anIntArray629 = new int[context.anInt620 * 0x186a0];
        boolean flag19 = true;
        while (flag19) {
            byte byte0 = method336(context);
            if (byte0 == 23)
                return;
            byte0 = method336(context);
            byte0 = method336(context);
            byte0 = method336(context);
            byte0 = method336(context);
            byte0 = method336(context);
            context.anInt621++;
            byte0 = method336(context);
            byte0 = method336(context);
            byte0 = method336(context);
            byte0 = method336(context);
            byte0 = method337(context);
            context.aBoolean617 = byte0 != 0;
            if (context.aBoolean617)
                System.out.println("PANIC! RANDOMISED BLOCK!");
            context.anInt622 = 0;
            byte0 = method336(context);
            context.anInt622 = context.anInt622 << 8 | byte0 & 0xff;
            byte0 = method336(context);
            context.anInt622 = context.anInt622 << 8 | byte0 & 0xff;
            byte0 = method336(context);
            context.anInt622 = context.anInt622 << 8 | byte0 & 0xff;
            for (int j = 0; j < 16; j++) {
                byte byte1 = method337(context);
                context.aBooleanArray632[j] = byte1 == 1;
            }

            for (int k = 0; k < 256; k++)
                context.aBooleanArray631[k] = false;

            for (int l = 0; l < 16; l++)
                if (context.aBooleanArray632[l]) {
                    for (int i3 = 0; i3 < 16; i3++) {
                        byte byte2 = method337(context);
                        if (byte2 == 1)
                            context.aBooleanArray631[l * 16 + i3] = true;
                    }

                }

            method339(context);
            int i4 = context.anInt630 + 2;
            int j4 = method338(3, context);
            int k4 = method338(15, context);
            for (int i1 = 0; i1 < k4; i1++) {
                int j3 = 0;
                do {
                    byte byte3 = method337(context);
                    if (byte3 == 0)
                        break;
                    j3++;
                } while (true);
                context.aByteArray637[i1] = (byte) j3;
            }

            byte[] abyte0 = new byte[6];
            for (byte byte16 = 0; byte16 < j4; byte16++)
                abyte0[byte16] = byte16;

            for (int j1 = 0; j1 < k4; j1++) {
                byte byte17 = context.aByteArray637[j1];
                byte byte15 = abyte0[byte17];
                for (; byte17 > 0; byte17--)
                    abyte0[byte17] = abyte0[byte17 - 1];

                abyte0[0] = byte15;
                context.aByteArray636[j1] = byte15;
            }

            for (int k3 = 0; k3 < j4; k3++) {
                int l6 = method338(5, context);
                for (int k1 = 0; k1 < i4; k1++) {
                    do {
                        byte byte4 = method337(context);
                        if (byte4 == 0)
                            break;
                        byte4 = method337(context);
                        if (byte4 == 0)
                            l6++;
                        else
                            l6--;
                    } while (true);
                    context.aByteArrayArray638[k3][k1] = (byte) l6;
                }

            }

            for (int l3 = 0; l3 < j4; l3++) {
                byte byte8 = 32;
                int i = 0;
                for (int l1 = 0; l1 < i4; l1++) {
                    if (context.aByteArrayArray638[l3][l1] > i)
                        i = context.aByteArrayArray638[l3][l1];
                    if (context.aByteArrayArray638[l3][l1] < byte8)
                        byte8 = context.aByteArrayArray638[l3][l1];
                }

                method340(context.anIntArrayArray639[l3], context.anIntArrayArray640[l3],
                        context.anIntArrayArray641[l3], context.aByteArrayArray638[l3], byte8, i, i4);
                context.anIntArray642[l3] = byte8;
            }

            int l4 = context.anInt630 + 1;
            int l5 = 0x186a0 * context.anInt620;
            int i5 = -1;
            int j5 = 0;
            for (int i2 = 0; i2 <= 255; i2++)
                context.anIntArray625[i2] = 0;

            int j9 = 4095;
            for (int l8 = 15; l8 >= 0; l8--) {
                for (int i9 = 15; i9 >= 0; i9--) {
                    context.aByteArray634[j9] = (byte) (l8 * 16 + i9);
                    j9--;
                }

                context.anIntArray635[l8] = j9 + 1;
            }

            int i6 = 0;
            if (j5 == 0) {
                i5++;
                j5 = 50;
                byte byte12 = context.aByteArray636[i5];
                k8 = context.anIntArray642[byte12];
                ai = context.anIntArrayArray639[byte12];
                ai2 = context.anIntArrayArray641[byte12];
                ai1 = context.anIntArrayArray640[byte12];
            }
            j5--;
            int i7 = k8;
            int l7;
            byte byte9;
            for (l7 = method338(i7, context); l7 > ai[i7]; l7 = l7 << 1 | byte9) {
                i7++;
                byte9 = method337(context);
            }

            for (int k5 = ai2[l7 - ai1[i7]]; k5 != l4; )
                if (k5 == 0 || k5 == 1) {
                    int j6 = -1;
                    int k6 = 1;
                    do {
                        if (k5 == 0)
                            j6 += k6;
                        else if (k5 == 1)
                            j6 += 2 * k6;
                        k6 *= 2;
                        if (j5 == 0) {
                            i5++;
                            j5 = 50;
                            byte byte13 = context.aByteArray636[i5];
                            k8 = context.anIntArray642[byte13];
                            ai = context.anIntArrayArray639[byte13];
                            ai2 = context.anIntArrayArray641[byte13];
                            ai1 = context.anIntArrayArray640[byte13];
                        }
                        j5--;
                        int j7 = k8;
                        int i8;
                        byte byte10;
                        for (i8 = method338(j7, context); i8 > ai[j7]; i8 = i8 << 1 | byte10) {
                            j7++;
                            byte10 = method337(context);
                        }

                        k5 = ai2[i8 - ai1[j7]];
                    } while (k5 == 0 || k5 == 1);
                    j6++;
                    byte byte5 = context.aByteArray633[context.aByteArray634[context.anIntArray635[0]] & 0xff];
                    context.anIntArray625[byte5 & 0xff] += j6;
                    for (; j6 > 0; j6--) {
                        BZip2Context.anIntArray629[i6] = byte5 & 0xff;
                        i6++;
                    }

                } else {
                    int j11 = k5 - 1;
                    byte byte6;
                    if (j11 < 16) {
                        int j10 = context.anIntArray635[0];
                        byte6 = context.aByteArray634[j10 + j11];
                        for (; j11 > 3; j11 -= 4) {
                            int k11 = j10 + j11;
                            context.aByteArray634[k11] = context.aByteArray634[k11 - 1];
                            context.aByteArray634[k11 - 1] = context.aByteArray634[k11 - 2];
                            context.aByteArray634[k11 - 2] = context.aByteArray634[k11 - 3];
                            context.aByteArray634[k11 - 3] = context.aByteArray634[k11 - 4];
                        }

                        for (; j11 > 0; j11--)
                            context.aByteArray634[j10 + j11] = context.aByteArray634[(j10 + j11) - 1];

                        context.aByteArray634[j10] = byte6;
                    } else {
                        int l10 = j11 / 16;
                        int i11 = j11 % 16;
                        int k10 = context.anIntArray635[l10] + i11;
                        byte6 = context.aByteArray634[k10];
                        for (; k10 > context.anIntArray635[l10]; k10--)
                            context.aByteArray634[k10] = context.aByteArray634[k10 - 1];

                        context.anIntArray635[l10]++;
                        for (; l10 > 0; l10--) {
                            context.anIntArray635[l10]--;
                            context.aByteArray634[context.anIntArray635[l10]] = context.aByteArray634[(context.anIntArray635[l10
                                    - 1] + 16) - 1];
                        }

                        context.anIntArray635[0]--;
                        context.aByteArray634[context.anIntArray635[0]] = byte6;
                        if (context.anIntArray635[0] == 0) {
                            int i10 = 4095;
                            for (int k9 = 15; k9 >= 0; k9--) {
                                for (int l9 = 15; l9 >= 0; l9--) {
                                    context.aByteArray634[i10] = context.aByteArray634[context.anIntArray635[k9] + l9];
                                    i10--;
                                }

                                context.anIntArray635[k9] = i10 + 1;
                            }

                        }
                    }
                    context.anIntArray625[context.aByteArray633[byte6 & 0xff] & 0xff]++;
                    BZip2Context.anIntArray629[i6] = context.aByteArray633[byte6 & 0xff] & 0xff;
                    i6++;
                    if (j5 == 0) {
                        i5++;
                        j5 = 50;
                        byte byte14 = context.aByteArray636[i5];
                        k8 = context.anIntArray642[byte14];
                        ai = context.anIntArrayArray639[byte14];
                        ai2 = context.anIntArrayArray641[byte14];
                        ai1 = context.anIntArrayArray640[byte14];
                    }
                    j5--;
                    int k7 = k8;
                    int j8;
                    byte byte11;
                    for (j8 = method338(k7, context); j8 > ai[k7]; j8 = j8 << 1 | byte11) {
                        k7++;
                        byte11 = method337(context);
                    }

                    k5 = ai2[j8 - ai1[k7]];
                }

            context.anInt616 = 0;
            context.aByte615 = 0;
            context.anIntArray627[0] = 0;
            for (int j2 = 1; j2 <= 256; j2++)
                context.anIntArray627[j2] = context.anIntArray625[j2 - 1];

            for (int k2 = 1; k2 <= 256; k2++)
                context.anIntArray627[k2] += context.anIntArray627[k2 - 1];

            for (int l2 = 0; l2 < i6; l2++) {
                byte byte7 = (byte) (BZip2Context.anIntArray629[l2] & 0xff);
                BZip2Context.anIntArray629[context.anIntArray627[byte7 & 0xff]] |= l2 << 8;
                context.anIntArray627[byte7 & 0xff]++;
            }

            context.anInt623 = BZip2Context.anIntArray629[context.anInt622] >> 8;
            context.anInt626 = 0;
            context.anInt623 = BZip2Context.anIntArray629[context.anInt623];
            context.anInt624 = (byte) (context.anInt623 & 0xff);
            context.anInt623 >>= 8;
            context.anInt626++;
            context.anInt643 = i6;
            method334(context);
            flag19 = context.anInt626 == context.anInt643 + 1 && context.anInt616 == 0;
        }
    }

    public static byte method336(BZip2Context context) {
        return (byte) method338(8, context);
    }

    public static byte method337(BZip2Context context) {
        return (byte) method338(1, context);
    }

    public static int method338(int i, BZip2Context context) {
        int j;
        do {
            if (context.anInt619 >= i) {
                int k = context.anInt618 >> context.anInt619 - i & (1 << i) - 1;
                context.anInt619 -= i;
                j = k;
                break;
            }
            context.anInt618 = context.anInt618 << 8 | context.src[context.srcOff] & 0xff;
            context.anInt619 += 8;
            context.srcOff++;
            context.srcLen--;
            context.anInt608++;
            if (context.anInt608 == 0)
                context.anInt609++;
        } while (true);
        return j;
    }

    public static void method339(BZip2Context context) {
        context.anInt630 = 0;
        for (int i = 0; i < 256; i++)
            if (context.aBooleanArray631[i]) {
                context.aByteArray633[context.anInt630] = (byte) i;
                context.anInt630++;
            }

    }

    public static void method340(int[] ai, int[] ai1, int[] ai2, byte[] abyte0, int i, int j, int k) {
        int l = 0;
        for (int i1 = i; i1 <= j; i1++) {
            for (int l2 = 0; l2 < k; l2++)
                if (abyte0[l2] == i1) {
                    ai2[l] = l2;
                    l++;
                }

        }

        for (int j1 = 0; j1 < 23; j1++)
            ai1[j1] = 0;

        for (int k1 = 0; k1 < k; k1++)
            ai1[abyte0[k1] + 1]++;

        for (int l1 = 1; l1 < 23; l1++)
            ai1[l1] += ai1[l1 - 1];

        for (int i2 = 0; i2 < 23; i2++)
            ai[i2] = 0;

        int i3 = 0;
        for (int j2 = i; j2 <= j; j2++) {
            i3 += ai1[j2 + 1] - ai1[j2];
            ai[j2] = i3 - 1;
            i3 <<= 1;
        }

        for (int k2 = i + 1; k2 <= j; k2++)
            ai1[k2] = (ai[k2 - 1] + 1 << 1) - ai1[k2];

    }

    public static BZip2Context context = new BZip2Context();

}
