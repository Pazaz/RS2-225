// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.io.PrintStream;

public class Class33 {

    public static int method333(byte abyte0[], int i, byte abyte1[], int j, int k) {
        synchronized (aClass35_589) {
            aClass35_589.aByteArray605 = abyte1;
            aClass35_589.anInt606 = k;
            aClass35_589.aByteArray610 = abyte0;
            aClass35_589.anInt611 = 0;
            aClass35_589.anInt607 = j;
            aClass35_589.anInt612 = i;
            aClass35_589.anInt619 = 0;
            aClass35_589.anInt618 = 0;
            aClass35_589.anInt608 = 0;
            aClass35_589.anInt609 = 0;
            aClass35_589.anInt613 = 0;
            aClass35_589.anInt614 = 0;
            aClass35_589.anInt621 = 0;
            method335(aClass35_589);
            i -= aClass35_589.anInt612;
            int l = i;
            return l;
        }
    }

    public static void method334(Class35 class35) {
        byte byte4 = class35.aByte615;
        int i = class35.anInt616;
        int j = class35.anInt626;
        int k = class35.anInt624;
        int ai[] = Class35.anIntArray629;
        int l = class35.anInt623;
        byte abyte0[] = class35.aByteArray610;
        int i1 = class35.anInt611;
        int j1 = class35.anInt612;
        int k1 = j1;
        int l1 = class35.anInt643 + 1;
        label0: do {
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
        int i2 = class35.anInt613;
        class35.anInt613 += k1 - j1;
        if (class35.anInt613 < i2)
            class35.anInt614++;
        class35.aByte615 = byte4;
        class35.anInt616 = i;
        class35.anInt626 = j;
        class35.anInt624 = k;
        Class35.anIntArray629 = ai;
        class35.anInt623 = l;
        class35.aByteArray610 = abyte0;
        class35.anInt611 = i1;
        class35.anInt612 = j1;
    }

    public static void method335(Class35 class35) {
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
        int ai[] = null;
        int ai1[] = null;
        int ai2[] = null;
        class35.anInt620 = 1;
        if (Class35.anIntArray629 == null)
            Class35.anIntArray629 = new int[class35.anInt620 * 0x186a0];
        boolean flag19 = true;
        while (flag19) {
            byte byte0 = method336(class35);
            if (byte0 == 23)
                return;
            byte0 = method336(class35);
            byte0 = method336(class35);
            byte0 = method336(class35);
            byte0 = method336(class35);
            byte0 = method336(class35);
            class35.anInt621++;
            byte0 = method336(class35);
            byte0 = method336(class35);
            byte0 = method336(class35);
            byte0 = method336(class35);
            byte0 = method337(class35);
            if (byte0 != 0)
                class35.aBoolean617 = true;
            else
                class35.aBoolean617 = false;
            if (class35.aBoolean617)
                System.out.println("PANIC! RANDOMISED BLOCK!");
            class35.anInt622 = 0;
            byte0 = method336(class35);
            class35.anInt622 = class35.anInt622 << 8 | byte0 & 0xff;
            byte0 = method336(class35);
            class35.anInt622 = class35.anInt622 << 8 | byte0 & 0xff;
            byte0 = method336(class35);
            class35.anInt622 = class35.anInt622 << 8 | byte0 & 0xff;
            for (int j = 0; j < 16; j++) {
                byte byte1 = method337(class35);
                if (byte1 == 1)
                    class35.aBooleanArray632[j] = true;
                else
                    class35.aBooleanArray632[j] = false;
            }

            for (int k = 0; k < 256; k++)
                class35.aBooleanArray631[k] = false;

            for (int l = 0; l < 16; l++)
                if (class35.aBooleanArray632[l]) {
                    for (int i3 = 0; i3 < 16; i3++) {
                        byte byte2 = method337(class35);
                        if (byte2 == 1)
                            class35.aBooleanArray631[l * 16 + i3] = true;
                    }

                }

            method339(class35);
            int i4 = class35.anInt630 + 2;
            int j4 = method338(3, class35);
            int k4 = method338(15, class35);
            for (int i1 = 0; i1 < k4; i1++) {
                int j3 = 0;
                do {
                    byte byte3 = method337(class35);
                    if (byte3 == 0)
                        break;
                    j3++;
                } while (true);
                class35.aByteArray637[i1] = (byte) j3;
            }

            byte abyte0[] = new byte[6];
            for (byte byte16 = 0; byte16 < j4; byte16++)
                abyte0[byte16] = byte16;

            for (int j1 = 0; j1 < k4; j1++) {
                byte byte17 = class35.aByteArray637[j1];
                byte byte15 = abyte0[byte17];
                for (; byte17 > 0; byte17--)
                    abyte0[byte17] = abyte0[byte17 - 1];

                abyte0[0] = byte15;
                class35.aByteArray636[j1] = byte15;
            }

            for (int k3 = 0; k3 < j4; k3++) {
                int l6 = method338(5, class35);
                for (int k1 = 0; k1 < i4; k1++) {
                    do {
                        byte byte4 = method337(class35);
                        if (byte4 == 0)
                            break;
                        byte4 = method337(class35);
                        if (byte4 == 0)
                            l6++;
                        else
                            l6--;
                    } while (true);
                    class35.aByteArrayArray638[k3][k1] = (byte) l6;
                }

            }

            for (int l3 = 0; l3 < j4; l3++) {
                byte byte8 = 32;
                int i = 0;
                for (int l1 = 0; l1 < i4; l1++) {
                    if (class35.aByteArrayArray638[l3][l1] > i)
                        i = class35.aByteArrayArray638[l3][l1];
                    if (class35.aByteArrayArray638[l3][l1] < byte8)
                        byte8 = class35.aByteArrayArray638[l3][l1];
                }

                method340(class35.anIntArrayArray639[l3], class35.anIntArrayArray640[l3],
                        class35.anIntArrayArray641[l3], class35.aByteArrayArray638[l3], byte8, i, i4);
                class35.anIntArray642[l3] = byte8;
            }

            int l4 = class35.anInt630 + 1;
            int l5 = 0x186a0 * class35.anInt620;
            int i5 = -1;
            int j5 = 0;
            for (int i2 = 0; i2 <= 255; i2++)
                class35.anIntArray625[i2] = 0;

            int j9 = 4095;
            for (int l8 = 15; l8 >= 0; l8--) {
                for (int i9 = 15; i9 >= 0; i9--) {
                    class35.aByteArray634[j9] = (byte) (l8 * 16 + i9);
                    j9--;
                }

                class35.anIntArray635[l8] = j9 + 1;
            }

            int i6 = 0;
            if (j5 == 0) {
                i5++;
                j5 = 50;
                byte byte12 = class35.aByteArray636[i5];
                k8 = class35.anIntArray642[byte12];
                ai = class35.anIntArrayArray639[byte12];
                ai2 = class35.anIntArrayArray641[byte12];
                ai1 = class35.anIntArrayArray640[byte12];
            }
            j5--;
            int i7 = k8;
            int l7;
            byte byte9;
            for (l7 = method338(i7, class35); l7 > ai[i7]; l7 = l7 << 1 | byte9) {
                i7++;
                byte9 = method337(class35);
            }

            for (int k5 = ai2[l7 - ai1[i7]]; k5 != l4;)
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
                            byte byte13 = class35.aByteArray636[i5];
                            k8 = class35.anIntArray642[byte13];
                            ai = class35.anIntArrayArray639[byte13];
                            ai2 = class35.anIntArrayArray641[byte13];
                            ai1 = class35.anIntArrayArray640[byte13];
                        }
                        j5--;
                        int j7 = k8;
                        int i8;
                        byte byte10;
                        for (i8 = method338(j7, class35); i8 > ai[j7]; i8 = i8 << 1 | byte10) {
                            j7++;
                            byte10 = method337(class35);
                        }

                        k5 = ai2[i8 - ai1[j7]];
                    } while (k5 == 0 || k5 == 1);
                    j6++;
                    byte byte5 = class35.aByteArray633[class35.aByteArray634[class35.anIntArray635[0]] & 0xff];
                    class35.anIntArray625[byte5 & 0xff] += j6;
                    for (; j6 > 0; j6--) {
                        Class35.anIntArray629[i6] = byte5 & 0xff;
                        i6++;
                    }

                } else {
                    int j11 = k5 - 1;
                    byte byte6;
                    if (j11 < 16) {
                        int j10 = class35.anIntArray635[0];
                        byte6 = class35.aByteArray634[j10 + j11];
                        for (; j11 > 3; j11 -= 4) {
                            int k11 = j10 + j11;
                            class35.aByteArray634[k11] = class35.aByteArray634[k11 - 1];
                            class35.aByteArray634[k11 - 1] = class35.aByteArray634[k11 - 2];
                            class35.aByteArray634[k11 - 2] = class35.aByteArray634[k11 - 3];
                            class35.aByteArray634[k11 - 3] = class35.aByteArray634[k11 - 4];
                        }

                        for (; j11 > 0; j11--)
                            class35.aByteArray634[j10 + j11] = class35.aByteArray634[(j10 + j11) - 1];

                        class35.aByteArray634[j10] = byte6;
                    } else {
                        int l10 = j11 / 16;
                        int i11 = j11 % 16;
                        int k10 = class35.anIntArray635[l10] + i11;
                        byte6 = class35.aByteArray634[k10];
                        for (; k10 > class35.anIntArray635[l10]; k10--)
                            class35.aByteArray634[k10] = class35.aByteArray634[k10 - 1];

                        class35.anIntArray635[l10]++;
                        for (; l10 > 0; l10--) {
                            class35.anIntArray635[l10]--;
                            class35.aByteArray634[class35.anIntArray635[l10]] = class35.aByteArray634[(class35.anIntArray635[l10
                                    - 1] + 16) - 1];
                        }

                        class35.anIntArray635[0]--;
                        class35.aByteArray634[class35.anIntArray635[0]] = byte6;
                        if (class35.anIntArray635[0] == 0) {
                            int i10 = 4095;
                            for (int k9 = 15; k9 >= 0; k9--) {
                                for (int l9 = 15; l9 >= 0; l9--) {
                                    class35.aByteArray634[i10] = class35.aByteArray634[class35.anIntArray635[k9] + l9];
                                    i10--;
                                }

                                class35.anIntArray635[k9] = i10 + 1;
                            }

                        }
                    }
                    class35.anIntArray625[class35.aByteArray633[byte6 & 0xff] & 0xff]++;
                    Class35.anIntArray629[i6] = class35.aByteArray633[byte6 & 0xff] & 0xff;
                    i6++;
                    if (j5 == 0) {
                        i5++;
                        j5 = 50;
                        byte byte14 = class35.aByteArray636[i5];
                        k8 = class35.anIntArray642[byte14];
                        ai = class35.anIntArrayArray639[byte14];
                        ai2 = class35.anIntArrayArray641[byte14];
                        ai1 = class35.anIntArrayArray640[byte14];
                    }
                    j5--;
                    int k7 = k8;
                    int j8;
                    byte byte11;
                    for (j8 = method338(k7, class35); j8 > ai[k7]; j8 = j8 << 1 | byte11) {
                        k7++;
                        byte11 = method337(class35);
                    }

                    k5 = ai2[j8 - ai1[k7]];
                }

            class35.anInt616 = 0;
            class35.aByte615 = 0;
            class35.anIntArray627[0] = 0;
            for (int j2 = 1; j2 <= 256; j2++)
                class35.anIntArray627[j2] = class35.anIntArray625[j2 - 1];

            for (int k2 = 1; k2 <= 256; k2++)
                class35.anIntArray627[k2] += class35.anIntArray627[k2 - 1];

            for (int l2 = 0; l2 < i6; l2++) {
                byte byte7 = (byte) (Class35.anIntArray629[l2] & 0xff);
                Class35.anIntArray629[class35.anIntArray627[byte7 & 0xff]] |= l2 << 8;
                class35.anIntArray627[byte7 & 0xff]++;
            }

            class35.anInt623 = Class35.anIntArray629[class35.anInt622] >> 8;
            class35.anInt626 = 0;
            class35.anInt623 = Class35.anIntArray629[class35.anInt623];
            class35.anInt624 = (byte) (class35.anInt623 & 0xff);
            class35.anInt623 >>= 8;
            class35.anInt626++;
            class35.anInt643 = i6;
            method334(class35);
            if (class35.anInt626 == class35.anInt643 + 1 && class35.anInt616 == 0)
                flag19 = true;
            else
                flag19 = false;
        }
    }

    public static byte method336(Class35 class35) {
        return (byte) method338(8, class35);
    }

    public static byte method337(Class35 class35) {
        return (byte) method338(1, class35);
    }

    public static int method338(int i, Class35 class35) {
        int j;
        do {
            if (class35.anInt619 >= i) {
                int k = class35.anInt618 >> class35.anInt619 - i & (1 << i) - 1;
                class35.anInt619 -= i;
                j = k;
                break;
            }
            class35.anInt618 = class35.anInt618 << 8 | class35.aByteArray605[class35.anInt606] & 0xff;
            class35.anInt619 += 8;
            class35.anInt606++;
            class35.anInt607--;
            class35.anInt608++;
            if (class35.anInt608 == 0)
                class35.anInt609++;
        } while (true);
        return j;
    }

    public static void method339(Class35 class35) {
        class35.anInt630 = 0;
        for (int i = 0; i < 256; i++)
            if (class35.aBooleanArray631[i]) {
                class35.aByteArray633[class35.anInt630] = (byte) i;
                class35.anInt630++;
            }

    }

    public static void method340(int ai[], int ai1[], int ai2[], byte abyte0[], int i, int j, int k) {
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

    public static Class35 aClass35_589 = new Class35();

}
