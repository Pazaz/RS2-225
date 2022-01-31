package com.jagex.mapviewer;

import java.io.PrintStream;

public final class m
{

    public m()
    {
    }

    private static byte aef(n n1)
    {
        return (byte)aen(8, n1);
    }

    private static void aeg(n n1)
    {
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
        boolean flag19 = false;
        int i = 0;
        int ai[] = null;
        int ai1[] = null;
        int ai2[] = null;
        n1.ani = 1;
        if(n.bad == null)
            n.bad = new int[n1.ani * 0x186a0];
        for(boolean flag20 = true; flag20;)
        {
            byte byte0 = aef(n1);
            if(byte0 == 23)
                return;
            byte0 = aef(n1);
            byte0 = aef(n1);
            byte0 = aef(n1);
            byte0 = aef(n1);
            byte0 = aef(n1);
            n1.anj++;
            byte0 = aef(n1);
            byte0 = aef(n1);
            byte0 = aef(n1);
            byte0 = aef(n1);
            byte0 = aej(n1);
            if(byte0 != 0)
                n1.anf = true;
            else
                n1.anf = false;
            if(n1.anf)
                System.out.println("PANIC! RANDOMISED BLOCK!");
            n1.ank = 0;
            byte0 = aef(n1);
            n1.ank = n1.ank << 8 | byte0 & 0xff;
            byte0 = aef(n1);
            n1.ank = n1.ank << 8 | byte0 & 0xff;
            byte0 = aef(n1);
            n1.ank = n1.ank << 8 | byte0 & 0xff;
            for(int j = 0; j < 16; j++)
            {
                byte byte1 = aej(n1);
                if(byte1 == 1)
                    n1.bag[j] = true;
                else
                    n1.bag[j] = false;
            }

            for(int k = 0; k < 256; k++)
                n1.baf[k] = false;

            for(int l = 0; l < 16; l++)
            {
                if(!n1.bag[l])
                    continue;
                for(int j1 = 0; j1 < 16; j1++)
                {
                    byte byte2 = aej(n1);
                    if(byte2 == 1)
                        n1.baf[l * 16 + j1] = true;
                }

            }

            aei(n1);
            int i1 = n1.bae + 2;
            int k1 = aen(3, n1);
            int l1 = aen(15, n1);
            for(int i2 = 0; i2 < l1; i2++)
            {
                int j2 = 0;
                do
                {
                    byte byte4 = aej(n1);
                    if(byte4 == 0)
                        break;
                    j2++;
                } while(true);
                n1.bal[i2] = (byte)j2;
            }

            byte abyte0[] = new byte[6];
            for(byte byte3 = 0; byte3 < k1; byte3++)
                abyte0[byte3] = byte3;

            for(int k2 = 0; k2 < l1; k2++)
            {
                byte byte5 = n1.bal[k2];
                byte byte7 = abyte0[byte5];
                for(; byte5 > 0; byte5--)
                    abyte0[byte5] = abyte0[byte5 - 1];

                abyte0[0] = byte7;
                n1.bak[k2] = byte7;
            }

            for(int l2 = 0; l2 < k1; l2++)
            {
                int k3 = aen(5, n1);
                for(int i4 = 0; i4 < i1; i4++)
                {
                    do
                    {
                        byte byte8 = aej(n1);
                        if(byte8 == 0)
                            break;
                        byte8 = aej(n1);
                        if(byte8 == 0)
                            k3++;
                        else
                            k3--;
                    } while(true);
                    n1.bam[l2][i4] = (byte)k3;
                }

            }

            for(int i3 = 0; i3 < k1; i3++)
            {
                byte byte6 = 32;
                int j4 = 0;
                for(int l4 = 0; l4 < i1; l4++)
                {
                    if(n1.bam[i3][l4] > j4)
                        j4 = n1.bam[i3][l4];
                    if(n1.bam[i3][l4] < byte6)
                        byte6 = n1.bam[i3][l4];
                }

                ael(n1.ban[i3], n1.bba[i3], n1.bbb[i3], n1.bam[i3], byte6, j4, i1);
                n1.bbc[i3] = byte6;
            }

            int j3 = n1.bae + 1;
            int l3 = 0x186a0 * n1.ani;
            int k4 = -1;
            int i5 = 0;
            for(int j5 = 0; j5 <= 255; j5++)
                n1.ann[j5] = 0;

            int k5 = 4095;
            for(int l5 = 15; l5 >= 0; l5--)
            {
                for(int j6 = 15; j6 >= 0; j6--)
                {
                    n1.bai[k5] = (byte)(l5 * 16 + j6);
                    k5--;
                }

                n1.baj[l5] = k5 + 1;
            }

            int i6 = 0;
            if(i5 == 0)
            {
                k4++;
                i5 = 50;
                byte byte9 = n1.bak[k4];
                i = n1.bbc[byte9];
                ai = n1.ban[byte9];
                ai2 = n1.bbb[byte9];
                ai1 = n1.bba[byte9];
            }
            i5--;
            int k6 = i;
            int l6;
            byte byte10;
            for(l6 = aen(k6, n1); l6 > ai[k6]; l6 = l6 << 1 | byte10)
            {
                k6++;
                byte10 = aej(n1);
            }

            for(int i7 = ai2[l6 - ai1[k6]]; i7 != j3;)
                if(i7 == 0 || i7 == 1)
                {
                    int i8 = -1;
                    int k8 = 1;
                    do
                    {
                        if(i7 == 0)
                            i8 += 1 * k8;
                        else
                        if(i7 == 1)
                            i8 += 2 * k8;
                        k8 *= 2;
                        if(i5 == 0)
                        {
                            k4++;
                            i5 = 50;
                            byte byte13 = n1.bak[k4];
                            i = n1.bbc[byte13];
                            ai = n1.ban[byte13];
                            ai2 = n1.bbb[byte13];
                            ai1 = n1.bba[byte13];
                        }
                        i5--;
                        int l8 = i;
                        int l9;
                        byte byte16;
                        for(l9 = aen(l8, n1); l9 > ai[l8]; l9 = l9 << 1 | byte16)
                        {
                            l8++;
                            byte16 = aej(n1);
                        }

                        i7 = ai2[l9 - ai1[l8]];
                    } while(i7 == 0 || i7 == 1);
                    i8++;
                    byte byte14 = n1.bah[n1.bai[n1.baj[0]] & 0xff];
                    n1.ann[byte14 & 0xff] += i8;
                    while(i8 > 0) 
                    {
                        n.bad[i6] = byte14 & 0xff;
                        i6++;
                        i8--;
                    }
                } else
                {
                    int j8 = i7 - 1;
                    byte byte12;
                    if(j8 < 16)
                    {
                        int i9 = n1.baj[0];
                        byte12 = n1.bai[i9 + j8];
                        for(; j8 > 3; j8 -= 4)
                        {
                            int i10 = i9 + j8;
                            n1.bai[i10] = n1.bai[i10 - 1];
                            n1.bai[i10 - 1] = n1.bai[i10 - 2];
                            n1.bai[i10 - 2] = n1.bai[i10 - 3];
                            n1.bai[i10 - 3] = n1.bai[i10 - 4];
                        }

                        for(; j8 > 0; j8--)
                            n1.bai[i9 + j8] = n1.bai[(i9 + j8) - 1];

                        n1.bai[i9] = byte12;
                    } else
                    {
                        int j9 = j8 / 16;
                        int j10 = j8 % 16;
                        int l10 = n1.baj[j9] + j10;
                        byte12 = n1.bai[l10];
                        for(; l10 > n1.baj[j9]; l10--)
                            n1.bai[l10] = n1.bai[l10 - 1];

                        n1.baj[j9]++;
                        for(; j9 > 0; j9--)
                        {
                            n1.baj[j9]--;
                            n1.bai[n1.baj[j9]] = n1.bai[(n1.baj[j9 - 1] + 16) - 1];
                        }

                        n1.baj[0]--;
                        n1.bai[n1.baj[0]] = byte12;
                        if(n1.baj[0] == 0)
                        {
                            int i11 = 4095;
                            for(int j11 = 15; j11 >= 0; j11--)
                            {
                                for(int k11 = 15; k11 >= 0; k11--)
                                {
                                    n1.bai[i11] = n1.bai[n1.baj[j11] + k11];
                                    i11--;
                                }

                                n1.baj[j11] = i11 + 1;
                            }

                        }
                    }
                    n1.ann[n1.bah[byte12 & 0xff] & 0xff]++;
                    n.bad[i6] = n1.bah[byte12 & 0xff] & 0xff;
                    i6++;
                    if(i5 == 0)
                    {
                        k4++;
                        i5 = 50;
                        byte byte15 = n1.bak[k4];
                        i = n1.bbc[byte15];
                        ai = n1.ban[byte15];
                        ai2 = n1.bbb[byte15];
                        ai1 = n1.bba[byte15];
                    }
                    i5--;
                    int k9 = i;
                    int k10;
                    byte byte17;
                    for(k10 = aen(k9, n1); k10 > ai[k9]; k10 = k10 << 1 | byte17)
                    {
                        k9++;
                        byte17 = aej(n1);
                    }

                    i7 = ai2[k10 - ai1[k9]];
                }

            n1.ane = 0;
            n1.and = 0;
            n1.bab[0] = 0;
            for(int j7 = 1; j7 <= 256; j7++)
                n1.bab[j7] = n1.ann[j7 - 1];

            for(int k7 = 1; k7 <= 256; k7++)
                n1.bab[k7] += n1.bab[k7 - 1];

            for(int l7 = 0; l7 < i6; l7++)
            {
                byte byte11 = (byte)(n.bad[l7] & 0xff);
                n.bad[n1.bab[byte11 & 0xff]] |= l7 << 8;
                n1.bab[byte11 & 0xff]++;
            }

            n1.anl = n.bad[n1.ank] >> 8;
            n1.baa = 0;
            n1.anl = n.bad[n1.anl];
            n1.anm = (byte)(n1.anl & 0xff);
            n1.anl >>= 8;
            n1.baa++;
            n1.bbd = i6;
            aeh(n1);
            if(n1.baa == n1.bbd + 1 && n1.ane == 0)
                flag20 = true;
            else
                flag20 = false;
        }

    }

    private static void aeh(n n1)
    {
        byte byte0 = n1.and;
        int i = n1.ane;
        int j = n1.baa;
        int k = n1.anm;
        int ai[] = n.bad;
        int l = n1.anl;
        byte abyte0[] = n1.amm;
        int i1 = n1.amn;
        int j1 = n1.ana;
        int k1 = j1;
        int l1 = n1.bbd + 1;
label0:
        do
        {
            if(i > 0)
            {
                do
                {
                    if(j1 == 0)
                        break label0;
                    if(i == 1)
                        break;
                    abyte0[i1] = byte0;
                    i--;
                    i1++;
                    j1--;
                } while(true);
                if(j1 == 0)
                {
                    i = 1;
                    break;
                }
                abyte0[i1] = byte0;
                i1++;
                j1--;
            }
            boolean flag = true;
            do
            {
                if(!flag)
                    break;
                flag = false;
                if(j == l1)
                {
                    i = 0;
                    break label0;
                }
                byte0 = (byte)k;
                l = ai[l];
                byte byte1 = (byte)(l & 0xff);
                l >>= 8;
                j++;
                if(byte1 != k)
                {
                    k = byte1;
                    if(j1 == 0)
                    {
                        i = 1;
                    } else
                    {
                        abyte0[i1] = byte0;
                        i1++;
                        j1--;
                        flag = true;
                        continue;
                    }
                    break label0;
                }
                if(j != l1)
                    continue;
                if(j1 == 0)
                {
                    i = 1;
                    break label0;
                }
                abyte0[i1] = byte0;
                i1++;
                j1--;
                flag = true;
            } while(true);
            i = 2;
            l = ai[l];
            byte byte2 = (byte)(l & 0xff);
            l >>= 8;
            if(++j != l1)
                if(byte2 != k)
                {
                    k = byte2;
                } else
                {
                    i = 3;
                    l = ai[l];
                    byte byte3 = (byte)(l & 0xff);
                    l >>= 8;
                    if(++j != l1)
                        if(byte3 != k)
                        {
                            k = byte3;
                        } else
                        {
                            l = ai[l];
                            byte byte4 = (byte)(l & 0xff);
                            l >>= 8;
                            j++;
                            i = (byte4 & 0xff) + 4;
                            l = ai[l];
                            k = (byte)(l & 0xff);
                            l >>= 8;
                            j++;
                        }
                }
        } while(true);
        int i2 = n1.anb;
        n1.anb += k1 - j1;
        if(n1.anb < i2)
            n1.anc++;
        n1.and = byte0;
        n1.ane = i;
        n1.baa = j;
        n1.anm = k;
        n.bad = ai;
        n1.anl = l;
        n1.amm = abyte0;
        n1.amn = i1;
        n1.ana = j1;
    }

    private static void aei(n n1)
    {
        n1.bae = 0;
        for(int i = 0; i < 256; i++)
            if(n1.baf[i])
            {
                n1.bah[n1.bae] = (byte)i;
                n1.bae++;
            }

    }

    private static byte aej(n n1)
    {
        return (byte)aen(1, n1);
    }

    private static void ael(int ai[], int ai1[], int ai2[], byte abyte0[], int i, int j, int k)
    {
        int l = 0;
        for(int i1 = i; i1 <= j; i1++)
        {
            for(int k2 = 0; k2 < k; k2++)
                if(abyte0[k2] == i1)
                {
                    ai2[l] = k2;
                    l++;
                }

        }

        for(int j1 = 0; j1 < 23; j1++)
            ai1[j1] = 0;

        for(int k1 = 0; k1 < k; k1++)
            ai1[abyte0[k1] + 1]++;

        for(int l1 = 1; l1 < 23; l1++)
            ai1[l1] += ai1[l1 - 1];

        for(int i2 = 0; i2 < 23; i2++)
            ai[i2] = 0;

        int j2 = 0;
        for(int l2 = i; l2 <= j; l2++)
        {
            j2 += ai1[l2 + 1] - ai1[l2];
            ai[l2] = j2 - 1;
            j2 <<= 1;
        }

        for(int i3 = i + 1; i3 <= j; i3++)
            ai1[i3] = (ai[i3 - 1] + 1 << 1) - ai1[i3];

    }

    public static int aem(byte abyte0[], int i, byte abyte1[], int j, int k)
    {
        n n1 = aif;
        aif.amh = abyte1;
        aif.ami = k;
        aif.amm = abyte0;
        aif.amn = 0;
        aif.amj = j;
        aif.ana = i;
        aif.anh = 0;
        aif.ang = 0;
        aif.amk = 0;
        aif.aml = 0;
        aif.anb = 0;
        aif.anc = 0;
        aif.anj = 0;
        aeg(aif);
        i -= aif.ana;
        int l = i;
        return l;
    }

    private static int aen(int i, n n1)
    {
        int j;
        do
        {
            if(n1.anh >= i)
            {
                int k = n1.ang >> n1.anh - i & (1 << i) - 1;
                n1.anh -= i;
                j = k;
                break;
            }
            n1.ang = n1.ang << 8 | n1.amh[n1.ami] & 0xff;
            n1.anh += 8;
            n1.ami++;
            n1.amj--;
            n1.amk++;
            if(n1.amk == 0)
                n1.aml++;
        } while(true);
        return j;
    }

    private static n aif = new n();

}
