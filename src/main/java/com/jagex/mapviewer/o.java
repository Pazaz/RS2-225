package com.jagex.mapviewer;

public final class o
{

    public o(byte abyte0[])
    {
        abk(abyte0);
    }

    private void abk(byte abyte0[])
    {
        j j1 = new j(abyte0);
        int i = j1.aim();
        int k = j1.aim();
        if(k != i)
        {
            byte abyte1[] = new byte[i];
            m.aem(abyte1, i, abyte0, k, 6);
            afc = abyte1;
            j1 = new j(afc);
            afi = true;
        } else
        {
            afc = abyte0;
            afi = false;
        }
        afd = j1.aik();
        afe = new int[afd];
        aff = new int[afd];
        afg = new int[afd];
        afh = new int[afd];
        int l = j1.ala + afd * 10;
        for(int i1 = 0; i1 < afd; i1++)
        {
            afe[i1] = j1.aih();
            aff[i1] = j1.aim();
            afg[i1] = j1.aim();
            afh[i1] = l;
            l += afg[i1];
        }

    }

    public byte[] abl(String s, byte abyte0[])
    {
        int i = 0;
        s = s.toUpperCase();
        for(int k = 0; k < s.length(); k++)
            i = (i * 61 + s.charAt(k)) - 32;

        for(int l = 0; l < afd; l++)
            if(afe[l] == i)
            {
                if(abyte0 == null)
                    abyte0 = new byte[aff[l]];
                if(!afi)
                {
                    m.aem(abyte0, aff[l], afc, afg[l], afh[l]);
                } else
                {
                    for(int i1 = 0; i1 < aff[l]; i1++)
                        abyte0[i1] = afc[afh[l] + i1];

                }
                return abyte0;
            }

        return null;
    }

    public byte afc[];
    public int afd;
    public int afe[];
    public int aff[];
    public int afg[];
    public int afh[];
    private boolean afi;
}
