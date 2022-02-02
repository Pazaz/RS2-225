package com.jagex.mapviewer;

import java.awt.*;
import java.awt.image.PixelGrabber;

public class DrawText extends Draw2D
{

    private void ahk(Font font, FontMetrics fontmetrics, char c, int i, boolean flag, GameShell a1)
    {
        int j = fontmetrics.charWidth(c);
        int k = j;
        if(flag)
            try
            {
                if(c == '/')
                    flag = false;
                if(c == 'f' || c == 't' || c == 'w' || c == 'v' || c == 'k' || c == 'x' || c == 'y' || c == 'A' || c == 'V' || c == 'W')
                    j++;
            }
            catch(Exception exception) { }
        int l = fontmetrics.getMaxAscent();
        int i1 = fontmetrics.getMaxAscent() + fontmetrics.getMaxDescent();
        int j1 = fontmetrics.getHeight();
        Image image = a1.getBaseComponent().createImage(j, i1);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, j, i1);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString((new StringBuilder()).append(c).append("").toString(), 0, l);
        if(flag)
            g.drawString((new StringBuilder()).append(c).append("").toString(), 1, l);
        int ai[] = new int[j * i1];
        PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, j, i1, ai, 0, j);
        try
        {
            pixelgrabber.grabPixels();
        }
        catch(Exception exception1) { }
        image.flush();
        image = null;
        int k1 = 0;
        int l1 = 0;
        int i2 = j;
        int j2 = i1;
label0:
        for(int k2 = 0; k2 < i1; k2++)
        {
            int l3 = 0;
            do
            {
                if(l3 >= j)
                    continue label0;
                int i5 = ai[l3 + k2 * j];
                if((i5 & 0xffffff) != 0)
                {
                    l1 = k2;
                    break label0;
                }
                l3++;
            } while(true);
        }

label1:
        for(int l2 = 0; l2 < j; l2++)
        {
            int i4 = 0;
            do
            {
                if(i4 >= i1)
                    continue label1;
                int j5 = ai[l2 + i4 * j];
                if((j5 & 0xffffff) != 0)
                {
                    k1 = l2;
                    break label1;
                }
                i4++;
            } while(true);
        }

label2:
        for(int i3 = i1 - 1; i3 >= 0; i3--)
        {
            int j4 = 0;
            do
            {
                if(j4 >= j)
                    continue label2;
                int k5 = ai[j4 + i3 * j];
                if((k5 & 0xffffff) != 0)
                {
                    j2 = i3 + 1;
                    break label2;
                }
                j4++;
            } while(true);
        }

label3:
        for(int j3 = j - 1; j3 >= 0; j3--)
        {
            int k4 = 0;
            do
            {
                if(k4 >= i1)
                    continue label3;
                int l5 = ai[j3 + k4 * j];
                if((l5 & 0xffffff) != 0)
                {
                    i2 = j3 + 1;
                    break label3;
                }
                k4++;
            } while(true);
        }

        akl[i * 9] = (byte)(akk / 16384);
        akl[i * 9 + 1] = (byte)(akk / 128 & 0x7f);
        akl[i * 9 + 2] = (byte)(akk & 0x7f);
        akl[i * 9 + 3] = (byte)(i2 - k1);
        akl[i * 9 + 4] = (byte)(j2 - l1);
        akl[i * 9 + 5] = (byte)k1;
        akl[i * 9 + 6] = (byte)(l - l1);
        akl[i * 9 + 7] = (byte)k;
        akl[i * 9 + 8] = (byte)j1;
        for(int k3 = l1; k3 < j2; k3++)
        {
            for(int l4 = k1; l4 < i2; l4++)
            {
                int i6 = ai[l4 + k3 * j] & 0xff;
                if(i6 > 30 && i6 < 230)
                    akj = true;
                akl[akk++] = (byte)i6;
            }

        }

    }

    public void ahl(String s, int i, int j, int k, boolean flag)
    {
        try
        {
            if(akj || k == 0)
                flag = false;
            for(int l = 0; l < s.length(); l++)
            {
                int i1 = akm[s.charAt(l)];
                if(flag)
                {
                    aie(i1, i + 1, j, 0, akl, akj);
                    aie(i1, i, j + 1, 0, akl, akj);
                }
                aie(i1, i, j, k, akl, akj);
                i += akl[i1 + 7];
            }

        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("drawstring: ").append(exception).toString());
            exception.printStackTrace();
        }
    }

    public DrawText(int i, boolean flag, GameShell a1)
    {
        akj = false;
        akk = 0;
        akl = new byte[0x186a0];
        akk = 855;
        akj = false;
        Font font = new Font("Helvetica", flag ? 1 : 0, i);
        FontMetrics fontmetrics = a1.getFontMetrics(font);
        for(int j = 0; j < 95; j++)
            ahk(font, fontmetrics, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".charAt(j), j, false, a1);

        if(flag && akj)
        {
            akk = 855;
            akj = false;
            Font font1 = new Font("Helvetica", 0, i);
            FontMetrics fontmetrics1 = a1.getFontMetrics(font1);
            for(int l = 0; l < 95; l++)
                ahk(font1, fontmetrics1, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".charAt(l), l, false, a1);

            if(!akj)
            {
                akk = 855;
                akj = false;
                for(int i1 = 0; i1 < 95; i1++)
                    ahk(font1, fontmetrics1, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".charAt(i1), i1, true, a1);

            }
        }
        byte abyte0[] = new byte[akk];
        for(int k = 0; k < akk; k++)
            abyte0[k] = akl[k];

        akl = abyte0;
    }

    public void ahn(String s, int i, int j, int k, boolean flag)
    {
        int l = aic(s) / 2;
        int i1 = aig();
        if(i - l > Draw2D.right)
            return;
        if(i + l < Draw2D.left)
            return;
        if(j - i1 > Draw2D.bottom)
            return;
        if(j < 0)
        {
            return;
        } else
        {
            ahl(s, i - l, j, k, flag);
            return;
        }
    }

    private void aia(int ai[], byte abyte0[], int i, int j, int k, int l, int i1, 
            int j1, int k1)
    {
        for(int l1 = -i1; l1 < 0; l1++)
        {
            for(int i2 = -l; i2 < 0; i2++)
            {
                int j2 = abyte0[j++] & 0xff;
                if(j2 > 30)
                {
                    if(j2 >= 230)
                    {
                        ai[k++] = i;
                    } else
                    {
                        int k2 = ai[k];
                        ai[k++] = ((i & 0xff00ff) * j2 + (k2 & 0xff00ff) * (256 - j2) & 0xff00ff00) + ((i & 0xff00) * j2 + (k2 & 0xff00) * (256 - j2) & 0xff0000) >> 8;
                    }
                } else
                {
                    k++;
                }
            }

            k += j1;
            j += k1;
        }

    }

    public int aib()
    {
        return akl[8] - 1;
    }

    public int aic(String s)
    {
        int i = 0;
        for(int j = 0; j < s.length(); j++)
        {
            if(s.charAt(j) == '@' && j + 4 < s.length() && s.charAt(j + 4) == '@')
            {
                j += 4;
                continue;
            }
            if(s.charAt(j) == '~' && j + 4 < s.length() && s.charAt(j + 4) == '~')
                j += 4;
            else
                i += akl[akm[s.charAt(j)] + 7];
        }

        return i;
    }

    private void aie(int i, int j, int k, int l, byte abyte0[], boolean flag)
    {
        int i1 = j + abyte0[i + 5];
        int j1 = k - abyte0[i + 6];
        int k1 = abyte0[i + 3];
        int l1 = abyte0[i + 4];
        int i2 = abyte0[i] * 16384 + abyte0[i + 1] * 128 + abyte0[i + 2];
        int j2 = i1 + j1 * Draw2D.width;
        int k2 = Draw2D.width - k1;
        int l2 = 0;
        if(j1 < Draw2D.top)
        {
            int i3 = Draw2D.top - j1;
            l1 -= i3;
            j1 = Draw2D.top;
            i2 += i3 * k1;
            j2 += i3 * Draw2D.width;
        }
        if(j1 + l1 >= Draw2D.bottom)
            l1 -= ((j1 + l1) - Draw2D.bottom) + 1;
        if(i1 < Draw2D.left)
        {
            int j3 = Draw2D.left - i1;
            k1 -= j3;
            i1 = Draw2D.left;
            i2 += j3;
            j2 += j3;
            l2 += j3;
            k2 += j3;
        }
        if(i1 + k1 >= Draw2D.right)
        {
            int k3 = ((i1 + k1) - Draw2D.right) + 1;
            k1 -= k3;
            l2 += k3;
            k2 += k3;
        }
        if(k1 > 0 && l1 > 0)
            if(flag)
                aia(Draw2D.pixels, abyte0, l, i2, j2, k1, l1, k2, l2);
            else
                aif(Draw2D.pixels, abyte0, l, i2, j2, k1, l1, k2, l2);
    }

    private void aif(int ai[], byte abyte0[], int i, int j, int k, int l, int i1, 
            int j1, int k1)
    {
        try
        {
            int l1 = -(l >> 2);
            l = -(l & 3);
            for(int i2 = -i1; i2 < 0; i2++)
            {
                for(int j2 = l1; j2 < 0; j2++)
                {
                    if(abyte0[j++] != 0)
                        ai[k++] = i;
                    else
                        k++;
                    if(abyte0[j++] != 0)
                        ai[k++] = i;
                    else
                        k++;
                    if(abyte0[j++] != 0)
                        ai[k++] = i;
                    else
                        k++;
                    if(abyte0[j++] != 0)
                        ai[k++] = i;
                    else
                        k++;
                }

                for(int k2 = l; k2 < 0; k2++)
                    if(abyte0[j++] != 0)
                        ai[k++] = i;
                    else
                        k++;

                k += j1;
                j += k1;
            }

        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("plotletter: ").append(exception).toString());
            exception.printStackTrace();
        }
    }

    public int aig()
    {
        return akl[6];
    }

    boolean akj;
    int akk;
    byte akl[];
    static int akm[];

    static 
    {
        akm = new int[256];
        for(int i = 0; i < 256; i++)
        {
            int j = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".indexOf(i);
            if(j == -1)
                j = 74;
            akm[i] = j * 9;
        }

    }
}
