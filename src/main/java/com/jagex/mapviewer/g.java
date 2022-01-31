package com.jagex.mapviewer;

public final class g extends e
{

    public void acf()
    {
        e.ajj(agk, agl, agm);
    }

    public void acg(int i, int k)
    {
        i += agn;
        k += aha;
        int l = i + k * e.bbf;
        int i1 = 0;
        int j1 = agm;
        int k1 = agl;
        int l1 = e.bbf - k1;
        int i2 = 0;
        if(k < e.bbh)
        {
            int j2 = e.bbh - k;
            j1 -= j2;
            k = e.bbh;
            i1 += j2 * k1;
            l += j2 * e.bbf;
        }
        if(k + j1 > e.bbi)
            j1 -= (k + j1) - e.bbi;
        if(i < e.bbj)
        {
            int k2 = e.bbj - i;
            k1 -= k2;
            i = e.bbj;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if(i + k1 > e.bbk)
        {
            int l2 = (i + k1) - e.bbk;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if(k1 <= 0 || j1 <= 0)
        {
            return;
        } else
        {
            ach(e.bbe, agk, 0, i1, l, k1, j1, l1, i2);
            return;
        }
    }

    private void ach(int ai[], int ai1[], int i, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        int i2 = -(i1 >> 2);
        i1 = -(i1 & 3);
        for(int j2 = -j1; j2 < 0; j2++)
        {
            for(int k2 = i2; k2 < 0; k2++)
            {
                i = ai1[k++];
                if(i != 0)
                    ai[l++] = i;
                else
                    l++;
                i = ai1[k++];
                if(i != 0)
                    ai[l++] = i;
                else
                    l++;
                i = ai1[k++];
                if(i != 0)
                    ai[l++] = i;
                else
                    l++;
                i = ai1[k++];
                if(i != 0)
                    ai[l++] = i;
                else
                    l++;
            }

            for(int l2 = i1; l2 < 0; l2++)
            {
                i = ai1[k++];
                if(i != 0)
                    ai[l++] = i;
                else
                    l++;
            }

            l += k1;
            k += l1;
        }

    }

    public g(int i, int k)
    {
        agk = new int[i * k];
        agl = ahb = i;
        agm = ahc = k;
        agn = aha = 0;
    }

    public g(o o1, String s, int i)
    {
        j j1 = new j(o1.abl((new StringBuilder()).append(s).append(".dat").toString(), null));
        j j2 = new j(o1.abl("index.dat", null));
        j2.ala = j1.aik();
        ahb = j2.aik();
        ahc = j2.aik();
        int k = j2.aii();
        int ai[] = new int[k];
        for(int l = 0; l < k - 1; l++)
        {
            ai[l + 1] = j2.aim();
            if(ai[l + 1] == 0)
                ai[l + 1] = 1;
        }

        for(int i1 = 0; i1 < i; i1++)
        {
            j2.ala += 2;
            j1.ala += j2.aik() * j2.aik();
            j2.ala++;
        }

        agn = j2.aii();
        aha = j2.aii();
        agl = j2.aik();
        agm = j2.aik();
        int k1 = j2.aii();
        int l1 = agl * agm;
        agk = new int[l1];
        if(k1 == 0)
        {
            for(int i2 = 0; i2 < l1; i2++)
                agk[i2] = ai[j1.aii()];

        } else
        if(k1 == 1)
        {
            for(int k2 = 0; k2 < agl; k2++)
            {
                for(int l2 = 0; l2 < agm; l2++)
                    agk[k2 + l2 * agl] = ai[j1.aii()];

            }

        }
    }

    public void ack(int i, int k)
    {
        i += agn;
        k += aha;
        int l = i + k * e.bbf;
        int i1 = 0;
        int j1 = agm;
        int k1 = agl;
        int l1 = e.bbf - k1;
        int i2 = 0;
        if(k < e.bbh)
        {
            int j2 = e.bbh - k;
            j1 -= j2;
            k = e.bbh;
            i1 += j2 * k1;
            l += j2 * e.bbf;
        }
        if(k + j1 > e.bbi)
            j1 -= (k + j1) - e.bbi;
        if(i < e.bbj)
        {
            int k2 = e.bbj - i;
            k1 -= k2;
            i = e.bbj;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if(i + k1 > e.bbk)
        {
            int l2 = (i + k1) - e.bbk;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if(k1 <= 0 || j1 <= 0)
        {
            return;
        } else
        {
            acl(e.bbe, agk, i1, l, k1, j1, l1, i2);
            return;
        }
    }

    private void acl(int ai[], int ai1[], int i, int k, int l, int i1, int j1, 
            int k1)
    {
        int l1 = -(l >> 2);
        l = -(l & 3);
        for(int i2 = -i1; i2 < 0; i2++)
        {
            for(int j2 = l1; j2 < 0; j2++)
            {
                ai[k++] = ai1[i++];
                ai[k++] = ai1[i++];
                ai[k++] = ai1[i++];
                ai[k++] = ai1[i++];
            }

            for(int k2 = l; k2 < 0; k2++)
                ai[k++] = ai1[i++];

            k += j1;
            i += k1;
        }

    }

    public int agk[];
    public int agl;
    public int agm;
    public int agn;
    public int aha;
    public int ahb;
    public int ahc;
}
