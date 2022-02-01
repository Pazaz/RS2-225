package com.jagex.mapviewer;

import com.jagex.runetek3.util.Buffer;

public class Sprite extends Draw2D
{

    public void acf()
    {
        Draw2D.ajj(agk, agl, agm);
    }

    public void acg(int i, int k)
    {
        i += agn;
        k += aha;
        int l = i + k * Draw2D.bbf;
        int i1 = 0;
        int j1 = agm;
        int k1 = agl;
        int l1 = Draw2D.bbf - k1;
        int i2 = 0;
        if(k < Draw2D.bbh)
        {
            int j2 = Draw2D.bbh - k;
            j1 -= j2;
            k = Draw2D.bbh;
            i1 += j2 * k1;
            l += j2 * Draw2D.bbf;
        }
        if(k + j1 > Draw2D.bbi)
            j1 -= (k + j1) - Draw2D.bbi;
        if(i < Draw2D.bbj)
        {
            int k2 = Draw2D.bbj - i;
            k1 -= k2;
            i = Draw2D.bbj;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if(i + k1 > Draw2D.bbk)
        {
            int l2 = (i + k1) - Draw2D.bbk;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if(k1 <= 0 || j1 <= 0)
        {
            return;
        } else
        {
            ach(Draw2D.bbe, agk, 0, i1, l, k1, j1, l1, i2);
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

    public Sprite(int i, int k)
    {
        agk = new int[i * k];
        agl = ahb = i;
        agm = ahc = k;
        agn = aha = 0;
    }

    public Sprite(FileArchive o1, String s, int i)
    {
        Buffer j1 = new Buffer(o1.abl((new StringBuilder()).append(s).append(".dat").toString(), null));
        Buffer j2 = new Buffer(o1.abl("index.dat", null));
        j2.offset = j1.readWord();
        ahb = j2.readWord();
        ahc = j2.readWord();
        int k = j2.readByte();
        int ai[] = new int[k];
        for(int l = 0; l < k - 1; l++)
        {
            ai[l + 1] = j2.readSWord();
            if(ai[l + 1] == 0)
                ai[l + 1] = 1;
        }

        for(int i1 = 0; i1 < i; i1++)
        {
            j2.offset += 2;
            j1.offset += j2.readWord() * j2.readWord();
            j2.offset++;
        }

        agn = j2.readByte();
        aha = j2.readByte();
        agl = j2.readWord();
        agm = j2.readWord();
        int k1 = j2.readByte();
        int l1 = agl * agm;
        agk = new int[l1];
        if(k1 == 0)
        {
            for(int i2 = 0; i2 < l1; i2++)
                agk[i2] = ai[j1.readByte()];

        } else
        if(k1 == 1)
        {
            for(int k2 = 0; k2 < agl; k2++)
            {
                for(int l2 = 0; l2 < agm; l2++)
                    agk[k2 + l2 * agl] = ai[j1.readByte()];

            }

        }
    }

    public void ack(int i, int k)
    {
        i += agn;
        k += aha;
        int l = i + k * Draw2D.bbf;
        int i1 = 0;
        int j1 = agm;
        int k1 = agl;
        int l1 = Draw2D.bbf - k1;
        int i2 = 0;
        if(k < Draw2D.bbh)
        {
            int j2 = Draw2D.bbh - k;
            j1 -= j2;
            k = Draw2D.bbh;
            i1 += j2 * k1;
            l += j2 * Draw2D.bbf;
        }
        if(k + j1 > Draw2D.bbi)
            j1 -= (k + j1) - Draw2D.bbi;
        if(i < Draw2D.bbj)
        {
            int k2 = Draw2D.bbj - i;
            k1 -= k2;
            i = Draw2D.bbj;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if(i + k1 > Draw2D.bbk)
        {
            int l2 = (i + k1) - Draw2D.bbk;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if(k1 <= 0 || j1 <= 0)
        {
            return;
        } else
        {
            acl(Draw2D.bbe, agk, i1, l, k1, j1, l1, i2);
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
