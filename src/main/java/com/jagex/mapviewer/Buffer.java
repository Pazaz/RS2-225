package com.jagex.mapviewer;

import com.jagex.runetek3.util.CacheableNode;

public class Buffer extends CacheableNode
{

    public int aih()
    {
        ala += 4;
        return ((akn[ala - 4] & 0xff) << 24) + ((akn[ala - 3] & 0xff) << 16) + ((akn[ala - 2] & 0xff) << 8) + (akn[ala - 1] & 0xff);
    }

    public int aii()
    {
        return akn[ala++] & 0xff;
    }

    public byte aij()
    {
        return akn[ala++];
    }

    public int aik()
    {
        ala += 2;
        return ((akn[ala - 2] & 0xff) << 8) + (akn[ala - 1] & 0xff);
    }

    public String ail()
    {
        int i = ala;
        while(akn[ala++] != 10) ;
        return new String(akn, i, ala - i - 1);
    }

    public int aim()
    {
        ala += 3;
        return ((akn[ala - 3] & 0xff) << 16) + ((akn[ala - 2] & 0xff) << 8) + (akn[ala - 1] & 0xff);
    }

    private Buffer()
    {
    }

    public Buffer(byte abyte0[])
    {
        akn = abyte0;
        ala = 0;
    }

    public byte akn[];
    public int ala;
    private static int alb[];
    private static int alc[] = {
        0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 
        1023, 2047, 4095, 8191, 16383, 32767, 65535, 0x1ffff, 0x3ffff, 0x7ffff, 
        0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 
        0x3fffffff, 0x7fffffff, -1
    };
    private static int ald = 0;
    private static int ale = 0;
    private static int alf = 0;
    private static char alj[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
        '8', '9', '+', '/'
    };
    public static boolean alk;

    static 
    {
        alb = new int[256];
        for(int i = 0; i < 256; i++)
        {
            int l = i;
            for(int i1 = 0; i1 < 8; i1++)
                if((l & 1) == 1)
                    l = l >>> 1 ^ 0xedb88320;
                else
                    l >>>= 1;

            alb[i] = l;
        }

    }
}
