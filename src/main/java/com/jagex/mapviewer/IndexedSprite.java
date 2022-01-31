package com.jagex.mapviewer;

public final class IndexedSprite extends Draw2D
{

    public IndexedSprite(FileArchive o1, String s, int i)
    {
        Buffer j1 = new Buffer(o1.abl((new StringBuilder()).append(s).append(".dat").toString(), null));
        Buffer j2 = new Buffer(o1.abl("index.dat", null));
        j2.ala = j1.aik();
        agg = j2.aik();
        agh = j2.aik();
        int k = j2.aii();
        agb = new int[k];
        for(int l = 0; l < k - 1; l++)
            agb[l + 1] = j2.aim();

        for(int i1 = 0; i1 < i; i1++)
        {
            j2.ala += 2;
            j1.ala += j2.aik() * j2.aik();
            j2.ala++;
        }

        age = j2.aii();
        agf = j2.aii();
        agc = j2.aik();
        agd = j2.aik();
        int k1 = j2.aii();
        int l1 = agc * agd;
        aga = new byte[l1];
        if(k1 == 0)
        {
            for(int i2 = 0; i2 < l1; i2++)
                aga[i2] = j1.aij();

        } else
        if(k1 == 1)
        {
            for(int k2 = 0; k2 < agc; k2++)
            {
                for(int l2 = 0; l2 < agd; l2++)
                    aga[k2 + l2 * agc] = j1.aij();

            }

        }
    }

    public void acc(int i, int k, int l, int i1)
    {
        try
        {
            int j1 = agc;
            int k1 = agd;
            int l1 = 0;
            int i2 = 0;
            int j2 = (j1 << 16) / l;
            int k2 = (k1 << 16) / i1;
            int l2 = agg;
            int i3 = agh;
            j2 = (l2 << 16) / l;
            k2 = (i3 << 16) / i1;
            i += ((age * l + l2) - 1) / l2;
            k += ((agf * i1 + i3) - 1) / i3;
            if((age * l) % l2 != 0)
                l1 = (l2 - (age * l) % l2 << 16) / l;
            if((agf * i1) % i3 != 0)
                i2 = (i3 - (agf * i1) % i3 << 16) / i1;
            l = (l * (agc - (l1 >> 16))) / l2;
            i1 = (i1 * (agd - (i2 >> 16))) / i3;
            int j3 = i + k * Draw2D.bbf;
            int k3 = Draw2D.bbf - l;
            if(k < Draw2D.bbh)
            {
                int l3 = Draw2D.bbh - k;
                i1 -= l3;
                k = 0;
                j3 += l3 * Draw2D.bbf;
                i2 += k2 * l3;
            }
            if(k + i1 > Draw2D.bbi)
                i1 -= (k + i1) - Draw2D.bbi;
            if(i < Draw2D.bbj)
            {
                int i4 = Draw2D.bbj - i;
                l -= i4;
                i = 0;
                j3 += i4;
                l1 += j2 * i4;
                k3 += i4;
            }
            if(i + l > Draw2D.bbk)
            {
                int j4 = (i + l) - Draw2D.bbk;
                l -= j4;
                k3 += j4;
            }
            acd(Draw2D.bbe, aga, agb, l1, i2, j3, k3, l, i1, j2, k2, j1);
        }
        catch(Exception exception)
        {
            System.out.println("error in sprite clipping routine");
        }
    }

    private void acd(int ai[], byte abyte0[], int ai1[], int i, int k, int l, int i1, 
            int j1, int k1, int l1, int i2, int j2)
    {
        try
        {
            int k2 = i;
            for(int l2 = -k1; l2 < 0; l2++)
            {
                int i3 = (k >> 16) * j2;
                for(int j3 = -j1; j3 < 0; j3++)
                {
                    byte byte0 = abyte0[(i >> 16) + i3];
                    if(byte0 != 0)
                        ai[l++] = ai1[byte0 & 0xff];
                    else
                        l++;
                    i += l1;
                }

                k += i2;
                i = k2;
                l += i1;
            }

        }
        catch(Exception exception)
        {
            System.out.println("error in plot_scale");
        }
    }

    public byte aga[];
    public int agb[];
    public int agc;
    public int agd;
    public int age;
    public int agf;
    public int agg;
    public int agh;
}
