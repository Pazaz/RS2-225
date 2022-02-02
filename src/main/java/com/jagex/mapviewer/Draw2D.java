package com.jagex.mapviewer;

import com.jagex.runetek3.util.CacheableNode;

public class Draw2D extends CacheableNode
{

    public static void drawLineX(int i, int j, int k, int l)
    {
        if(j < top || j >= bottom)
            return;
        if(i < left)
        {
            k -= left - i;
            i = left;
        }
        if(i + k > right)
            k = right - i;
        int i1 = i + j * width;
        for(int j1 = 0; j1 < k; j1++)
            pixels[i1 + j1] = l;

    }

    public static void drawLineY(int i, int j, int k, int l)
    {
        if(i < left || i >= right)
            return;
        if(j < top)
        {
            k -= top - j;
            j = top;
        }
        if(j + k > bottom)
            k = bottom - j;
        int i1 = i + j * width;
        for(int j1 = 0; j1 < k; j1++)
            pixels[i1 + j1 * width] = l;

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
        if(k > width)
            k = width;
        if(l > bbg)
            l = bbg;
        left = i;
        top = j;
        right = k;
        bottom = l;
        bbl = right - 1;
        bbm = right / 2;
        bbn = bottom / 2;
    }

    public static void fillRect(int i, int j, int k, int l, int i1, int j1)
    {
        if(i < left)
        {
            k -= left - i;
            i = left;
        }
        if(j < top)
        {
            l -= top - j;
            j = top;
        }
        if(i + k > right)
            k = right - i;
        if(j + l > bottom)
            l = bottom - j;
        int k1 = 256 - j1;
        int l1 = (i1 >> 16 & 0xff) * j1;
        int i2 = (i1 >> 8 & 0xff) * j1;
        int j2 = (i1 & 0xff) * j1;
        int k2 = width - k;
        int l2 = i + j * width;
        for(int i3 = 0; i3 < l; i3++)
        {
            for(int j3 = -k; j3 < 0; j3++)
            {
                int k3 = (pixels[l2] >> 16 & 0xff) * k1;
                int l3 = (pixels[l2] >> 8 & 0xff) * k1;
                int i4 = (pixels[l2] & 0xff) * k1;
                int j4 = ((l1 + k3 >> 8) << 16) + ((i2 + l3 >> 8) << 8) + (j2 + i4 >> 8);
                pixels[l2++] = j4;
            }

            l2 += k2;
        }

    }

    public static void drawRect(int i, int j, int k, int l, int i1)
    {
        drawLineX(i, j, k, i1);
        drawLineX(i, (j + l) - 1, k, i1);
        drawLineY(i, j, l, i1);
        drawLineY((i + k) - 1, j, l, i1);
    }

    public static void prepare(int ai[], int i, int j)
    {
        pixels = ai;
        width = i;
        bbg = j;
        ajg(0, 0, i, j);
    }

    public static void fillRect(int i, int j, int k, int l, int i1)
    {
        if(i < left)
        {
            k -= left - i;
            i = left;
        }
        if(j < top)
        {
            l -= top - j;
            j = top;
        }
        if(i + k > right)
            k = right - i;
        if(j + l > bottom)
            l = bottom - j;
        int j1 = width - k;
        boolean flag = true;
        int k1 = i + j * width;
        for(int l1 = -l; l1 < 0; l1++)
        {
            for(int i2 = -k; i2 < 0; i2++)
                pixels[k1++] = i1;

            k1 += j1;
        }

    }

    public static void clear()
    {
        int i = width * bbg;
        for(int j = 0; j < i; j++)
            pixels[j] = 0;

    }

    public static void fillCircle(int i, int j, int k, int l, int i1)
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
            if(l3 >= width)
                l3 = width - 1;
            int i4 = k3 + l2 * width;
            for(int j4 = k3; j4 <= l3; j4++)
            {
                int k4 = (pixels[i4] >> 16 & 0xff) * j1;
                int l4 = (pixels[i4] >> 8 & 0xff) * j1;
                int i5 = (pixels[i4] & 0xff) * j1;
                int j5 = ((k1 + k4 >> 8) << 16) + ((l1 + l4 >> 8) << 8) + (i2 + i5 >> 8);
                pixels[i4++] = j5;
            }

        }

    }

    public static int pixels[];
    public static int width;
    public static int bbg;
    public static int top = 0;
    public static int bottom = 0;
    public static int left = 0;
    public static int right = 0;
    public static int bbl;
    public static int bbm;
    public static int bbn;
    public static int bca;

}
