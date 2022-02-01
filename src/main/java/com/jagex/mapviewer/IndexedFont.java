package com.jagex.mapviewer;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

import java.util.Random;

public class IndexedFont extends Draw2D
{

    public int adm(String s)
    {
        if(s == null)
            return 0;
        int k = 0;
        for(int l = 0; l < s.length(); l++)
            k += aib[s.charAt(l)];

        return k;
    }

    public void adn(String s, int k, int l, int i1)
    {
        if(s == null)
            return;
        l -= aic;
        for(int j1 = 0; j1 < s.length(); j1++)
        {
            char c = s.charAt(j1);
            if(c != ' ')
                aed(ahk[c], k + ahn[c], l + aia[c], ahl[c], ahm[c], i1);
            k += aib[c];
        }

    }

    public IndexedFont(FileArchive o1, String s, boolean flag)
    {
        ahk = new byte[256][];
        ahl = new int[256];
        ahm = new int[256];
        ahn = new int[256];
        aia = new int[256];
        aib = new int[256];
        aic = 0;
        aid = new Random();
        aie = false;
        Buffer j1 = new Buffer(o1.read((new StringBuilder()).append(s).append(".dat").toString(), null));
        Buffer j2 = new Buffer(o1.read("index.dat", null));
        byte byte0 = -1;
        j2.offset = j1.readWord() + 4;
        int k = j2.readByte();
        if(k > 0)
            j2.offset += 3 * (k - 1);
        for(int l = 0; l < 256; l++)
        {
            int i1 = l;
            ahn[l] = j2.readByte();
            aia[l] = j2.readByte();
            int k1 = ahl[l] = j2.readWord();
            int l1 = ahm[l] = j2.readWord();
            int i2 = j2.readByte();
            int k2 = k1 * l1;
            ahk[l] = new byte[k2];
            if(i2 == 0)
            {
                for(int l2 = 0; l2 < k2; l2++)
                    ahk[l][l2] = j1.readByteSigned();

            } else
            if(i2 == 1)
            {
                for(int i3 = 0; i3 < k1; i3++)
                {
                    for(int k3 = 0; k3 < l1; k3++)
                        ahk[l][i3 + k3 * k1] = j1.readByteSigned();

                }

            }
            if(l1 > aic && l < 128)
                aic = l1;
            ahn[l] = 1;
            aib[l] = k1 + 2;
            int j3 = 0;
            for(int l3 = l1 / 7; l3 < l1; l3++)
                j3 += ahk[l][l3 * k1];

            if(j3 <= l1 / 7)
            {
                aib[l]--;
                ahn[l] = 0;
            }
            j3 = 0;
            for(int i4 = l1 / 7; i4 < l1; i4++)
                j3 += ahk[l][(k1 - 1) + i4 * k1];

            if(j3 <= l1 / 7)
                aib[l]--;
        }

        if(flag)
            aib[32] = aib[73];
        else
            aib[32] = aib[105];
    }

    public void aeb(String s, int k, int l, int i1)
    {
        adn(s, k - adm(s), l, i1);
    }

    public void aec(String s, int k, int l, int i1)
    {
        adn(s, k - adm(s) / 2, l, i1);
    }

    private void aed(byte abyte0[], int k, int l, int i1, int j1, int k1)
    {
        int l1 = k + l * Draw2D.bbf;
        int i2 = Draw2D.bbf - i1;
        int j2 = 0;
        int k2 = 0;
        if(l < Draw2D.bbh)
        {
            int l2 = Draw2D.bbh - l;
            j1 -= l2;
            l = Draw2D.bbh;
            k2 += l2 * i1;
            l1 += l2 * Draw2D.bbf;
        }
        if(l + j1 >= Draw2D.bbi)
            j1 -= ((l + j1) - Draw2D.bbi) + 1;
        if(k < Draw2D.bbj)
        {
            int i3 = Draw2D.bbj - k;
            i1 -= i3;
            k = Draw2D.bbj;
            k2 += i3;
            l1 += i3;
            j2 += i3;
            i2 += i3;
        }
        if(k + i1 >= Draw2D.bbk)
        {
            int j3 = ((k + i1) - Draw2D.bbk) + 1;
            i1 -= j3;
            j2 += j3;
            i2 += j3;
        }
        if(i1 <= 0 || j1 <= 0)
        {
            return;
        } else
        {
            aee(Draw2D.bbe, abyte0, k1, k2, l1, i1, j1, i2, j2);
            return;
        }
    }

    private void aee(int ai[], byte abyte0[], int k, int l, int i1, int j1, int k1, 
            int l1, int i2)
    {
        int j2 = -(j1 >> 2);
        j1 = -(j1 & 3);
        for(int k2 = -k1; k2 < 0; k2++)
        {
            for(int l2 = j2; l2 < 0; l2++)
            {
                if(abyte0[l++] != 0)
                    ai[i1++] = k;
                else
                    i1++;
                if(abyte0[l++] != 0)
                    ai[i1++] = k;
                else
                    i1++;
                if(abyte0[l++] != 0)
                    ai[i1++] = k;
                else
                    i1++;
                if(abyte0[l++] != 0)
                    ai[i1++] = k;
                else
                    i1++;
            }

            for(int i3 = j1; i3 < 0; i3++)
                if(abyte0[l++] != 0)
                    ai[i1++] = k;
                else
                    i1++;

            i1 += l1;
            l += i2;
        }

    }

    byte ahk[][];
    int ahl[];
    int ahm[];
    int ahn[];
    int aia[];
    public int aib[];
    public int aic;
    Random aid;
    boolean aie;
}
