package com.jagex.mapviewer;

public class Draw2D extends CacheableNode
{

    public static void ajd(int i, int j, int k, int l)
    {
        if(j < bbh || j >= bbi)
            return;
        if(i < bbj)
        {
            k -= bbj - i;
            i = bbj;
        }
        if(i + k > bbk)
            k = bbk - i;
        int i1 = i + j * bbf;
        for(int j1 = 0; j1 < k; j1++)
            bbe[i1 + j1] = l;

    }

    public static void aje(int i, int j, int k, int l)
    {
        if(i < bbj || i >= bbk)
            return;
        if(j < bbh)
        {
            k -= bbh - j;
            j = bbh;
        }
        if(j + k > bbi)
            k = bbi - j;
        int i1 = i + j * bbf;
        for(int j1 = 0; j1 < k; j1++)
            bbe[i1 + j1 * bbf] = l;

    }

    public Draw2D()
    {
    }

    public static void ajg(int i, int j, int k, int l)
    {
        if(i < 0)
            i = 0;
        if(j < 0)
            j = 0;
        if(k > bbf)
            k = bbf;
        if(l > bbg)
            l = bbg;
        bbj = i;
        bbh = j;
        bbk = k;
        bbi = l;
        bbl = bbk - 1;
        bbm = bbk / 2;
        bbn = bbi / 2;
    }

    public static void ajh(int i, int j, int k, int l, int i1, int j1)
    {
        if(i < bbj)
        {
            k -= bbj - i;
            i = bbj;
        }
        if(j < bbh)
        {
            l -= bbh - j;
            j = bbh;
        }
        if(i + k > bbk)
            k = bbk - i;
        if(j + l > bbi)
            l = bbi - j;
        int k1 = 256 - j1;
        int l1 = (i1 >> 16 & 0xff) * j1;
        int i2 = (i1 >> 8 & 0xff) * j1;
        int j2 = (i1 & 0xff) * j1;
        int k2 = bbf - k;
        int l2 = i + j * bbf;
        for(int i3 = 0; i3 < l; i3++)
        {
            for(int j3 = -k; j3 < 0; j3++)
            {
                int k3 = (bbe[l2] >> 16 & 0xff) * k1;
                int l3 = (bbe[l2] >> 8 & 0xff) * k1;
                int i4 = (bbe[l2] & 0xff) * k1;
                int j4 = ((l1 + k3 >> 8) << 16) + ((i2 + l3 >> 8) << 8) + (j2 + i4 >> 8);
                bbe[l2++] = j4;
            }

            l2 += k2;
        }

    }

    public static void aji(int i, int j, int k, int l, int i1)
    {
        ajd(i, j, k, i1);
        ajd(i, (j + l) - 1, k, i1);
        aje(i, j, l, i1);
        aje((i + k) - 1, j, l, i1);
    }

    public static void ajj(int ai[], int i, int j)
    {
        bbe = ai;
        bbf = i;
        bbg = j;
        ajg(0, 0, i, j);
    }

    public static void ajl(int i, int j, int k, int l, int i1)
    {
        if(i < bbj)
        {
            k -= bbj - i;
            i = bbj;
        }
        if(j < bbh)
        {
            l -= bbh - j;
            j = bbh;
        }
        if(i + k > bbk)
            k = bbk - i;
        if(j + l > bbi)
            l = bbi - j;
        int j1 = bbf - k;
        boolean flag = true;
        int k1 = i + j * bbf;
        for(int l1 = -l; l1 < 0; l1++)
        {
            for(int i2 = -k; i2 < 0; i2++)
                bbe[k1++] = i1;

            k1 += j1;
        }

    }

    public static void ajm()
    {
        int i = bbf * bbg;
        for(int j = 0; j < i; j++)
            bbe[j] = 0;

    }

    public static void ajn(int i, int j, int k, int l, int i1)
    {
        int j1 = 256 - i1;
        int k1 = (l >> 16 & 0xff) * i1;
        int l1 = (l >> 8 & 0xff) * i1;
        int i2 = (l & 0xff) * i1;
        int j2 = j - k;
        if(j2 < 0)
            j2 = 0;
        int k2 = j + k;
        if(k2 >= bbg)
            k2 = bbg - 1;
        for(int l2 = j2; l2 <= k2; l2++)
        {
            int i3 = l2 - j;
            int j3 = (int)Math.sqrt(k * k - i3 * i3);
            int k3 = i - j3;
            if(k3 < 0)
                k3 = 0;
            int l3 = i + j3;
            if(l3 >= bbf)
                l3 = bbf - 1;
            int i4 = k3 + l2 * bbf;
            for(int j4 = k3; j4 <= l3; j4++)
            {
                int k4 = (bbe[i4] >> 16 & 0xff) * j1;
                int l4 = (bbe[i4] >> 8 & 0xff) * j1;
                int i5 = (bbe[i4] & 0xff) * j1;
                int j5 = ((k1 + k4 >> 8) << 16) + ((l1 + l4 >> 8) << 8) + (i2 + i5 >> 8);
                bbe[i4++] = j5;
            }

        }

    }

    public static int bbe[];
    public static int bbf;
    public static int bbg;
    public static int bbh = 0;
    public static int bbi = 0;
    public static int bbj = 0;
    public static int bbk = 0;
    public static int bbl;
    public static int bbm;
    public static int bbn;
    public static int bca;

}
