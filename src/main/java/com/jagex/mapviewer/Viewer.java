package com.jagex.mapviewer;

import com.jagex.runetek3.util.Signlink;

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;

public class Viewer extends GameShell
{

    public static void main(String args[])
    {
        Viewer mapview1 = new Viewer();
        mapview1.agc(635, 503);
    }

    public void aab()
    {
        agj(635, 503);
    }

    public void aga()
    {
        FileArchive o1 = abb();
        afc(100, "Please wait... Rendering Map");
        Buffer j1 = new Buffer(o1.abl("size.dat", null));
        aaj = j1.aik();
        aak = j1.aik();
        aal = j1.aik();
        aam = j1.aik();
        aen = 3200 - aaj;
        afa = (aak + aam) - 3200;
        adi = 180;
        adj = (aal * adi) / aam;
        adk = 635 - adj - 5;
        adl = 503 - adi - 20;
        j1 = new Buffer(o1.abl("labels.dat", null));
        aef = j1.aik();
        for(int k = 0; k < aef; k++)
        {
            aeh[k] = j1.ail();
            aei[k] = j1.aik();
            aej[k] = j1.aik();
            aek[k] = j1.aii();
        }

        j1 = new Buffer(o1.abl("floorcol.dat", null));
        int i1 = j1.aik();
        aan = new int[i1 + 1];
        aba = new int[i1 + 1];
        for(int k1 = 0; k1 < i1; k1++)
        {
            aan[k1 + 1] = j1.aih();
            aba[k1 + 1] = j1.aih();
        }

        byte abyte0[] = o1.abl("underlay.dat", null);
        byte abyte1[][] = new byte[aal][aam];
        aae(abyte0, abyte1);
        byte abyte2[] = o1.abl("overlay.dat", null);
        abc = new int[aal][aam];
        abd = new byte[aal][aam];
        aaf(abyte2, abc, abd);
        byte abyte3[] = o1.abl("loc.dat", null);
        abe = new byte[aal][aam];
        abg = new byte[aal][aam];
        abf = new byte[aal][aam];
        aad(abyte3, abe, abg, abf);
        try
        {
            for(int l1 = 0; l1 < 100; l1++)
                abh[l1] = new IndexedSprite(o1, "mapscene", l1);

        }
        catch(Exception exception) { }
        try
        {
            for(int i2 = 0; i2 < 100; i2++)
                abi[i2] = new Sprite(o1, "mapfunction", i2);

        }
        catch(Exception exception1) { }
        abj = new IndexedFont(o1, "b12_full", false);
        abk = new DrawText(11, true, this);
        abl = new DrawText(12, true, this);
        abm = new DrawText(14, true, this);
        abn = new DrawText(17, true, this);
        aca = new DrawText(19, true, this);
        acb = new DrawText(22, true, this);
        acc = new DrawText(26, true, this);
        acd = new DrawText(30, true, this);
        abb = new int[aal][aam];
        aag(abyte1, abb);
        adn = new Sprite(adj, adi);
        adn.acf();
        aan(0, 0, aal, aam, 0, 0, adj, adi);
        Draw2D.aji(0, 0, adj, adi, 0);
        Draw2D.aji(1, 1, adj - 2, adi - 2, aab);
        super.ajb.acm();
    }

    private void aad(byte abyte0[], byte abyte1[][], byte abyte2[][], byte abyte3[][])
    {
        for(int k = 0; k < abyte0.length;)
        {
            int i1 = (abyte0[k++] & 0xff) * 64 - aaj;
            int j1 = (abyte0[k++] & 0xff) * 64 - aak;
            if(i1 > 0 && j1 > 0 && i1 + 64 < aal && j1 + 64 < aam)
            {
                int k1 = 0;
                while(k1 < 64) 
                {
                    byte abyte4[] = abyte1[k1 + i1];
                    byte abyte5[] = abyte2[k1 + i1];
                    byte abyte6[] = abyte3[k1 + i1];
                    int j2 = aam - j1 - 1;
                    for(int k2 = -64; k2 < 0; k2++)
                    {
                        do
                        {
                            int l2 = abyte0[k++] & 0xff;
                            if(l2 == 0)
                                break;
                            if(l2 < 29)
                                abyte4[j2] = (byte)l2;
                            else
                            if(l2 < 160)
                            {
                                abyte5[j2] = (byte)(l2 - 28);
                            } else
                            {
                                abyte6[j2] = (byte)(l2 - 159);
                                aci[ach] = k1 + i1;
                                acj[ach] = j2;
                                ack[ach] = l2 - 160;
                                ach++;
                            }
                        } while(true);
                        j2--;
                    }

                    k1++;
                }
            } else
            {
                int l1 = 0;
                while(l1 < 64) 
                {
                    for(int i2 = -64; i2 < 0; i2++)
                    {
                        byte byte0;
                        do
                            byte0 = abyte0[k++];
                        while(byte0 != 0);
                    }

                    l1++;
                }
            }
        }

    }

    private void aae(byte abyte0[], byte abyte1[][])
    {
        for(int k = 0; k < abyte0.length;)
        {
            int i1 = (abyte0[k++] & 0xff) * 64 - aaj;
            int j1 = (abyte0[k++] & 0xff) * 64 - aak;
            if(i1 > 0 && j1 > 0 && i1 + 64 < aal && j1 + 64 < aam)
            {
                int k1 = 0;
                while(k1 < 64) 
                {
                    byte abyte2[] = abyte1[k1 + i1];
                    int l1 = aam - j1 - 1;
                    for(int i2 = -64; i2 < 0; i2++)
                        abyte2[l1--] = abyte0[k++];

                    k1++;
                }
            } else
            {
                k += 4096;
            }
        }

    }

    private void aaf(byte abyte0[], int ai[][], byte abyte1[][])
    {
        for(int k = 0; k < abyte0.length;)
        {
            int i1 = (abyte0[k++] & 0xff) * 64 - aaj;
            int j1 = (abyte0[k++] & 0xff) * 64 - aak;
            if(i1 > 0 && j1 > 0 && i1 + 64 < aal && j1 + 64 < aam)
            {
                int k1 = 0;
                while(k1 < 64) 
                {
                    int ai1[] = ai[k1 + i1];
                    byte abyte2[] = abyte1[k1 + i1];
                    int i2 = aam - j1 - 1;
                    for(int j2 = -64; j2 < 0; j2++)
                    {
                        byte byte1 = abyte0[k++];
                        if(byte1 != 0)
                        {
                            abyte2[i2] = abyte0[k++];
                            ai1[i2--] = aba[byte1];
                        } else
                        {
                            ai1[i2--] = 0;
                        }
                    }

                    k1++;
                }
            } else
            {
                int l1 = -4096;
                while(l1 < 0) 
                {
                    byte byte0 = abyte0[k++];
                    if(byte0 != 0)
                        k++;
                    l1++;
                }
            }
        }

    }

    private void aag(byte abyte0[][], int ai[][])
    {
        int k = aal;
        int i1 = aam;
        int ai1[] = new int[i1];
        for(int j1 = 0; j1 < i1; j1++)
            ai1[j1] = 0;

        for(int k1 = 5; k1 < k - 5; k1++)
        {
            byte abyte1[] = abyte0[k1 + 5];
            byte abyte2[] = abyte0[k1 - 5];
            for(int l1 = 0; l1 < i1; l1++)
                ai1[l1] += aan[abyte1[l1] & 0xff] - aan[abyte2[l1] & 0xff];

            if(k1 <= 10 || k1 >= k - 10)
                continue;
            int i2 = 0;
            int j2 = 0;
            int k2 = 0;
            int ai2[] = ai[k1];
            for(int l2 = 5; l2 < i1 - 5; l2++)
            {
                int i3 = ai1[l2 - 5];
                int j3 = ai1[l2 + 5];
                i2 += (j3 >> 20) - (i3 >> 20);
                j2 += (j3 >> 10 & 0x3ff) - (i3 >> 10 & 0x3ff);
                k2 += (j3 & 0x3ff) - (i3 & 0x3ff);
                if(k2 > 0)
                    ai2[l2] = aah((double)i2 / 8533D, (double)j2 / 8533D, (double)k2 / 8533D);
            }

        }

    }

    private int aah(double d, double d1, double d2)
    {
        double d3 = d2;
        double d4 = d2;
        double d5 = d2;
        if(d1 != 0.0D)
        {
            double d6;
            if(d2 < 0.5D)
                d6 = d2 * (1.0D + d1);
            else
                d6 = (d2 + d1) - d2 * d1;
            double d7 = 2D * d2 - d6;
            double d8 = d + 0.33333333333333331D;
            if(d8 > 1.0D)
                d8--;
            double d9 = d;
            double d10 = d - 0.33333333333333331D;
            if(d10 < 0.0D)
                d10++;
            if(6D * d8 < 1.0D)
                d3 = d7 + (d6 - d7) * 6D * d8;
            else
            if(2D * d8 < 1.0D)
                d3 = d6;
            else
            if(3D * d8 < 2D)
                d3 = d7 + (d6 - d7) * (0.66666666666666663D - d8) * 6D;
            else
                d3 = d7;
            if(6D * d9 < 1.0D)
                d4 = d7 + (d6 - d7) * 6D * d9;
            else
            if(2D * d9 < 1.0D)
                d4 = d6;
            else
            if(3D * d9 < 2D)
                d4 = d7 + (d6 - d7) * (0.66666666666666663D - d9) * 6D;
            else
                d4 = d7;
            if(6D * d10 < 1.0D)
                d5 = d7 + (d6 - d7) * 6D * d10;
            else
            if(2D * d10 < 1.0D)
                d5 = d6;
            else
            if(3D * d10 < 2D)
                d5 = d7 + (d6 - d7) * (0.66666666666666663D - d10) * 6D;
            else
                d5 = d7;
        }
        int k = (int)(d3 * 256D);
        int i1 = (int)(d4 * 256D);
        int j1 = (int)(d5 * 256D);
        int k1 = (k << 16) + (i1 << 8) + j1;
        return k1;
    }

    public void ahb()
    {
        try
        {
            aan = null;
            aba = null;
            abb = (int[][])null;
            abc = (int[][])null;
            abd = (byte[][])null;
            abe = (byte[][])null;
            abf = (byte[][])null;
            abg = (byte[][])null;
            abh = null;
            abi = null;
            abj = null;
            ace = null;
            acf = null;
            acg = null;
            aci = null;
            acj = null;
            ack = null;
            adn = null;
            aeh = null;
            aei = null;
            aej = null;
            aek = null;
            afb = null;
            System.gc();
            return;
        }
        catch(Throwable throwable)
        {
            return;
        }
    }

    public void ahd()
    {
        if(super.ake[1] == 1)
        {
            aen = (int)((double)aen - 16D / ael);
            aah = true;
        }
        if(super.ake[2] == 1)
        {
            aen = (int)((double)aen + 16D / ael);
            aah = true;
        }
        if(super.ake[3] == 1)
        {
            afa = (int)((double)afa - 16D / ael);
            aah = true;
        }
        if(super.ake[4] == 1)
        {
            afa = (int)((double)afa + 16D / ael);
            aah = true;
        }
        int k = 1;
        do
        {
            if(k <= 0)
                break;
            k = agd();
            if(k == 49)
            {
                aem = 3D;
                aah = true;
            }
            if(k == 50)
            {
                aem = 4D;
                aah = true;
            }
            if(k == 51)
            {
                aem = 6D;
                aah = true;
            }
            if(k == 52)
            {
                aem = 8D;
                aah = true;
            }
            if(k == 107 || k == 75)
            {
                add = !add;
                aah = true;
            }
            if(k == 111 || k == 79)
            {
                adm = !adm;
                aah = true;
            }
            if(super.ajd != null && k == 101)
            {
                System.out.println("Starting export...");
                Sprite g1 = new Sprite(aal * 2, aam * 2);
                g1.acf();
                aan(0, 0, aal, aam, 0, 0, aal * 2, aam * 2);
                super.ajb.acm();
                int l1 = g1.agk.length;
                byte abyte0[] = new byte[l1 * 3];
                int k2 = 0;
                for(int l2 = 0; l2 < l1; l2++)
                {
                    int i3 = g1.agk[l2];
                    abyte0[k2++] = (byte)(i3 >> 16);
                    abyte0[k2++] = (byte)(i3 >> 8);
                    abyte0[k2++] = (byte)i3;
                }

                System.out.println("Saving to disk");
                try
                {
                    BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream((new StringBuilder()).append("map-").append(aal * 2).append("-").append(aam * 2).append("-rgb.raw").toString()));
                    bufferedoutputstream.write(abyte0);
                    bufferedoutputstream.close();
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                System.out.println((new StringBuilder()).append("Done export: ").append(aal * 2).append(",").append(aam * 2).toString());
            }
        } while(true);
        if(super.aka == 1)
        {
            aea = super.akb;
            aeb = super.akc;
            aec = aen;
            aed = afa;
            if(super.akb > 170 && super.akb < 220 && super.akc > 471 && super.akc < 503)
            {
                aem = 3D;
                aea = -1;
            }
            if(super.akb > 230 && super.akb < 280 && super.akc > 471 && super.akc < 503)
            {
                aem = 4D;
                aea = -1;
            }
            if(super.akb > 290 && super.akb < 340 && super.akc > 471 && super.akc < 503)
            {
                aem = 6D;
                aea = -1;
            }
            if(super.akb > 350 && super.akb < 400 && super.akc > 471 && super.akc < 503)
            {
                aem = 8D;
                aea = -1;
            }
            if(super.akb > acl && super.akc > acm + ada && super.akb < acl + acn && super.akc < 503)
            {
                add = !add;
                aea = -1;
            }
            if(super.akb > adk && super.akc > adl + adi && super.akb < adk + adj && super.akc < 503)
            {
                adm = !adm;
                aea = -1;
            }
            if(add)
            {
                if(super.akb > acl && super.akc > acm && super.akb < acl + acn && super.akc < acm + ada)
                    aea = -1;
                if(super.akb > acl && super.akc > acm && super.akb < acl + acn && super.akc < acm + 18 && adc > 0)
                    adc -= 25;
                if(super.akb > acl && super.akc > (acm + ada) - 18 && super.akb < acl + acn && super.akc < acm + ada && adc < 50)
                    adc += 25;
            }
            aah = true;
        }
        if(add)
        {
            ade = -1;
            if(super.aji > acl && super.aji < acl + acn)
            {
                k = acm + 21 + 5;
                for(int i1 = 0; i1 < 25; i1++)
                {
                    if(i1 + adb < afb.length && afb[i1 + adb].equals("???"))
                        continue;
                    if(super.ajj >= k && super.ajj < k + 17)
                    {
                        ade = i1 + adb;
                        if(super.aka == 1)
                        {
                            adg = i1 + adb;
                            adh = 50;
                        }
                    }
                    k += 17;
                }

            }
            if(ade != adf)
            {
                adf = ade;
                aah = true;
            }
        }
        if((super.ajh == 1 || super.aka == 1) && adm)
        {
            k = super.akb;
            int j1 = super.akc;
            if(super.ajh == 1)
            {
                k = super.aji;
                j1 = super.ajj;
            }
            if(k > adk && j1 > adl && k < adk + adj && j1 < adl + adi)
            {
                aen = ((k - adk) * aal) / adj;
                afa = ((j1 - adl) * aam) / adi;
                aea = -1;
                aah = true;
            }
        }
        if(super.ajh == 1 && aea != -1)
        {
            aen = aec + (int)(((double)(aea - super.aji) * 2D) / aem);
            afa = aed + (int)(((double)(aeb - super.ajj) * 2D) / aem);
            aah = true;
        }
        if(ael < aem)
        {
            aah = true;
            ael += ael / 30D;
            if(ael > aem)
                ael = aem;
        }
        if(ael > aem)
        {
            aah = true;
            ael -= ael / 30D;
            if(ael < aem)
                ael = aem;
        }
        if(adb < adc)
        {
            aah = true;
            adb++;
        }
        if(adb > adc)
        {
            aah = true;
            adb--;
        }
        if(adh > 0)
        {
            aah = true;
            adh--;
        }
        k = aen - (int)(635D / ael);
        int k1 = afa - (int)(503D / ael);
        int i2 = aen + (int)(635D / ael);
        int j2 = afa + (int)(503D / ael);
        if(k < 48)
            aen = 48 + (int)(635D / ael);
        if(k1 < 48)
            afa = 48 + (int)(503D / ael);
        if(i2 > aal - 48)
            aen = aal - 48 - (int)(635D / ael);
        if(j2 > aam - 48)
            afa = aam - 48 - (int)(503D / ael);
    }

    public void age()
    {
        if(aah)
        {
            aah = false;
            aai = 0;
            Draw2D.ajm();
            int k = aen - (int)(635D / ael);
            int i1 = afa - (int)(503D / ael);
            int j1 = aen + (int)(635D / ael);
            int k1 = afa + (int)(503D / ael);
            aan(k, i1, j1, k1, 0, 0, 635, 503);
            if(adm)
            {
                adn.ack(adk, adl);
                Draw2D.ajh(adk + (adj * k) / aal, adl + (adi * i1) / aam, ((j1 - k) * adj) / aal, ((k1 - i1) * adi) / aam, 0xff0000, 128);
                Draw2D.aji(adk + (adj * k) / aal, adl + (adi * i1) / aam, ((j1 - k) * adj) / aal, ((k1 - i1) * adi) / aam, 0xff0000);
                if(adh > 0 && adh % 10 < 5)
                {
                    for(int l1 = 0; l1 < ach; l1++)
                        if(ack[l1] == adg)
                        {
                            int j2 = adk + (adj * aci[l1]) / aal;
                            int l2 = adl + (adi * acj[l1]) / aam;
                            Draw2D.ajn(j2, l2, 2, 0xffff00, 256);
                        }

                }
            }
            if(add)
            {
                aam(acl, acm, acn, 18, 0x999999, 0x777777, 0x555555, "Prev page");
                aam(acl, acm + 18, acn, ada - 36, 0x999999, 0x777777, 0x555555, "");
                aam(acl, (acm + ada) - 18, acn, 18, 0x999999, 0x777777, 0x555555, "Next page");
                int i2 = acm + 3 + 18;
                for(int k2 = 0; k2 < 25; k2++)
                {
                    if(k2 + adb < abi.length && k2 + adb < afb.length)
                    {
                        if(afb[k2 + adb].equals("???"))
                            continue;
                        abi[k2 + adb].acg(acl + 3, i2);
                        abj.adn(afb[k2 + adb], acl + 21, i2 + 14, 0);
                        int i3 = 0xffffff;
                        if(ade == k2 + adb)
                            i3 = 0xbbaaaa;
                        if(adh > 0 && adh % 10 < 5 && adg == k2 + adb)
                            i3 = 0xffff00;
                        abj.adn(afb[k2 + adb], acl + 20, i2 + 13, i3);
                    }
                    i2 += 17;
                }

            }
            aam(adk, adl + adi, adj, 18, aab, aac, aad, "Overview");
            aam(acl, acm + ada, acn, 18, aab, aac, aad, "Key");
            if(aem == 3D)
                aam(170, 471, 50, 30, aae, aaf, aag, "37%");
            else
                aam(170, 471, 50, 30, aab, aac, aad, "37%");
            if(aem == 4D)
                aam(230, 471, 50, 30, aae, aaf, aag, "50%");
            else
                aam(230, 471, 50, 30, aab, aac, aad, "50%");
            if(aem == 6D)
                aam(290, 471, 50, 30, aae, aaf, aag, "75%");
            else
                aam(290, 471, 50, 30, aab, aac, aad, "75%");
            if(aem == 8D)
                aam(350, 471, 50, 30, aae, aaf, aag, "100%");
            else
                aam(350, 471, 50, 30, aab, aac, aad, "100%");
        }
        aai--;
        if(aai <= 0)
        {
            super.ajb.ade(super.aja, 0, 0);
            aai = 50;
        }
    }

    public void afb()
    {
        aai = 0;
    }

    private void aam(int k, int i1, int j1, int k1, int l1, int i2, int j2, 
            String s)
    {
        Draw2D.aji(k, i1, j1, k1, 0);
        k++;
        i1++;
        j1 -= 2;
        k1 -= 2;
        Draw2D.ajl(k, i1, j1, k1, i2);
        Draw2D.ajd(k, i1, j1, l1);
        Draw2D.aje(k, i1, k1, l1);
        Draw2D.ajd(k, (i1 + k1) - 1, j1, j2);
        Draw2D.aje((k + j1) - 1, i1, k1, j2);
        abj.aec(s, k + j1 / 2 + 1, i1 + k1 / 2 + 1 + 4, 0);
        abj.aec(s, k + j1 / 2, i1 + k1 / 2 + 4, 0xffffff);
    }

    private void aan(int k, int i1, int j1, int k1, int l1, int i2, int j2, 
            int k2)
    {
        int l2 = j1 - k;
        int i3 = k1 - i1;
        int j3 = (j2 - l1 << 16) / l2;
        int k3 = (k2 - i2 << 16) / i3;
        for(int l3 = 0; l3 < l2; l3++)
        {
            int j4 = j3 * l3 >> 16;
            int l5 = j3 * (l3 + 1) >> 16;
            int l6 = l5 - j4;
            if(l6 <= 0)
                continue;
            j4 += l1;
            l5 += l1;
            int ai[] = abb[l3 + k];
            int ai1[] = abc[l3 + k];
            byte abyte1[] = abd[l3 + k];
            for(int k9 = 0; k9 < i3; k9++)
            {
                int j10 = k3 * k9 >> 16;
                int i11 = k3 * (k9 + 1) >> 16;
                int k11 = i11 - j10;
                if(k11 <= 0)
                    continue;
                j10 += i2;
                i11 += i2;
                int j12 = ai1[k9 + i1];
                if(j12 == 0)
                {
                    Draw2D.ajl(j4, j10, l5 - j4, i11 - j10, ai[k9 + i1]);
                    continue;
                }
                byte byte0 = abyte1[k9 + i1];
                int k13 = byte0 & 0xfc;
                if(k13 == 0 || l6 <= 1 || k11 <= 1)
                    Draw2D.ajl(j4, j10, l6, k11, j12);
                else
                    aba(Draw2D.bbe, j10 * Draw2D.bbf + j4, ai[k9 + i1], j12, l6, k11, k13 >> 2, byte0 & 3);
            }

        }

        if(j1 - k > j2 - l1)
            return;
        int i4 = 0;
        for(int k4 = 0; k4 < l2; k4++)
        {
            int i6 = j3 * k4 >> 16;
            int i7 = j3 * (k4 + 1) >> 16;
            int l7 = i7 - i6;
            if(l7 <= 0)
                continue;
            byte abyte0[] = abe[k4 + k];
            byte abyte2[] = abg[k4 + k];
            byte abyte3[] = abf[k4 + k];
            for(int k10 = 0; k10 < i3; k10++)
            {
                int j11 = k3 * k10 >> 16;
                int l11 = k3 * (k10 + 1) >> 16;
                int k12 = l11 - j11;
                if(k12 <= 0)
                    continue;
                int j13 = abyte0[k10 + i1] & 0xff;
                if(j13 != 0)
                {
                    int l13;
                    if(l7 == 1)
                        l13 = i6;
                    else
                        l13 = i7 - 1;
                    int j14;
                    if(k12 == 1)
                        j14 = j11;
                    else
                        j14 = l11 - 1;
                    int l14 = 0xcccccc;
                    if(j13 >= 5 && j13 <= 8 || j13 >= 13 && j13 <= 16 || j13 >= 21 && j13 <= 24 || j13 == 27 || j13 == 28)
                    {
                        l14 = 0xcc0000;
                        j13 -= 4;
                    }
                    if(j13 == 1)
                        Draw2D.aje(i6, j11, k12, l14);
                    else
                    if(j13 == 2)
                        Draw2D.ajd(i6, j11, l7, l14);
                    else
                    if(j13 == 3)
                        Draw2D.aje(l13, j11, k12, l14);
                    else
                    if(j13 == 4)
                        Draw2D.ajd(i6, j14, l7, l14);
                    else
                    if(j13 == 9)
                    {
                        Draw2D.aje(i6, j11, k12, 0xffffff);
                        Draw2D.ajd(i6, j11, l7, l14);
                    } else
                    if(j13 == 10)
                    {
                        Draw2D.aje(l13, j11, k12, 0xffffff);
                        Draw2D.ajd(i6, j11, l7, l14);
                    } else
                    if(j13 == 11)
                    {
                        Draw2D.aje(l13, j11, k12, 0xffffff);
                        Draw2D.ajd(i6, j14, l7, l14);
                    } else
                    if(j13 == 12)
                    {
                        Draw2D.aje(i6, j11, k12, 0xffffff);
                        Draw2D.ajd(i6, j14, l7, l14);
                    } else
                    if(j13 == 17)
                        Draw2D.ajd(i6, j11, 1, l14);
                    else
                    if(j13 == 18)
                        Draw2D.ajd(l13, j11, 1, l14);
                    else
                    if(j13 == 19)
                        Draw2D.ajd(l13, j14, 1, l14);
                    else
                    if(j13 == 20)
                        Draw2D.ajd(i6, j14, 1, l14);
                    else
                    if(j13 == 25)
                    {
                        for(int i15 = 0; i15 < k12; i15++)
                            Draw2D.ajd(i6 + i15, j14 - i15, 1, l14);

                    } else
                    if(j13 == 26)
                    {
                        for(int j15 = 0; j15 < k12; j15++)
                            Draw2D.ajd(i6 + j15, j11 + j15, 1, l14);

                    }
                }
                int i14 = abyte2[k10 + i1] & 0xff;
                if(i14 != 0)
                    abh[i14 - 1].acc(i6 - l7 / 2, j11 - k12 / 2, l7 * 2, k12 * 2);
                int k14 = abyte3[k10 + i1] & 0xff;
                if(k14 != 0)
                {
                    acg[i4] = k14 - 1;
                    ace[i4] = i6 + l7 / 2;
                    acf[i4] = j11 + k12 / 2;
                    i4++;
                }
            }

        }

        for(int l4 = 0; l4 < i4; l4++)
            if(abi[acg[l4]] != null)
                abi[acg[l4]].acg(ace[l4] - 7, acf[l4] - 7);

        if(adh > 0)
        {
            for(int i5 = 0; i5 < i4; i5++)
            {
                if(acg[i5] != adg)
                    continue;
                abi[acg[i5]].acg(ace[i5] - 7, acf[i5] - 7);
                if(adh % 10 < 5)
                {
                    Draw2D.ajn(ace[i5], acf[i5], 15, 0xffff00, 128);
                    Draw2D.ajn(ace[i5], acf[i5], 7, 0xffffff, 256);
                }
            }

        }
        if(ael == aem && aee)
        {
label0:
            for(int j5 = 0; j5 < aef; j5++)
            {
                int j6 = aei[j5];
                int j7 = aej[j5];
                j6 -= aaj;
                j7 = (aak + aam) - j7;
                int i8 = l1 + ((j2 - l1) * (j6 - k)) / (j1 - k);
                int k8 = i2 + ((k2 - i2) * (j7 - i1)) / (k1 - i1);
                int i9 = aek[j5];
                int l9 = 0xffffff;
                DrawText f1 = null;
                if(i9 == 0)
                {
                    if(ael == 3D)
                        f1 = abk;
                    if(ael == 4D)
                        f1 = abl;
                    if(ael == 6D)
                        f1 = abm;
                    if(ael == 8D)
                        f1 = abn;
                }
                if(i9 == 1)
                {
                    if(ael == 3D)
                        f1 = abm;
                    if(ael == 4D)
                        f1 = abn;
                    if(ael == 6D)
                        f1 = aca;
                    if(ael == 8D)
                        f1 = acb;
                }
                if(i9 == 2)
                {
                    l9 = 0xffaa00;
                    if(ael == 3D)
                        f1 = aca;
                    if(ael == 4D)
                        f1 = acb;
                    if(ael == 6D)
                        f1 = acc;
                    if(ael == 8D)
                        f1 = acd;
                }
                if(f1 == null)
                    continue;
                String s = aeh[j5];
                int i12 = 1;
                for(int l12 = 0; l12 < s.length(); l12++)
                    if(s.charAt(l12) == '/')
                        i12++;

                k8 -= (f1.aib() * (i12 - 1)) / 2;
                k8 += f1.aig() / 2;
                do
                {
                    int i13 = s.indexOf("/");
                    if(i13 == -1)
                    {
                        f1.ahn(s, i8, k8, l9, true);
                        continue label0;
                    }
                    String s1 = s.substring(0, i13);
                    f1.ahn(s1, i8, k8, l9, true);
                    k8 += f1.aib();
                    s = s.substring(i13 + 1);
                } while(true);
            }

        }
        if(aaa)
        {
            for(int k5 = aaj / 64; k5 < (aaj + aal) / 64; k5++)
            {
                for(int k6 = aak / 64; k6 < (aak + aam) / 64; k6++)
                {
                    int k7 = k5 * 64;
                    int j8 = k6 * 64;
                    k7 -= aaj;
                    j8 = (aak + aam) - j8;
                    int l8 = l1 + ((j2 - l1) * (k7 - k)) / (j1 - k);
                    int j9 = i2 + ((k2 - i2) * (j8 - 64 - i1)) / (k1 - i1);
                    int i10 = l1 + ((j2 - l1) * ((k7 + 64) - k)) / (j1 - k);
                    int l10 = i2 + ((k2 - i2) * (j8 - i1)) / (k1 - i1);
                    Draw2D.aji(l8, j9, i10 - l8, l10 - j9, 0xffffff);
                    abj.aeb((new StringBuilder()).append(k5).append("_").append(k6).toString(), i10 - 5, l10 - 5, 0xffffff);
                    if(k5 == 33 && k6 >= 71 && k6 <= 73)
                    {
                        abj.aec("u_pass", (i10 + l8) / 2, (l10 + j9) / 2, 0xff0000);
                        continue;
                    }
                    if(k5 >= 32 && k5 <= 34 && k6 >= 70 && k6 <= 74)
                        abj.aec("u_pass", (i10 + l8) / 2, (l10 + j9) / 2, 0xffff00);
                }

            }

        }
    }

    private void aba(int ai[], int k, int i1, int j1, int k1, int l1, int i2, 
            int j2)
    {
        int k2 = Draw2D.bbf - k1;
        if(i2 == 9)
        {
            i2 = 1;
            j2 = j2 + 1 & 3;
        }
        if(i2 == 10)
        {
            i2 = 1;
            j2 = j2 + 3 & 3;
        }
        if(i2 == 11)
        {
            i2 = 8;
            j2 = j2 + 3 & 3;
        }
        if(i2 == 1)
        {
            if(j2 == 0)
            {
                for(int l2 = 0; l2 < l1; l2++)
                {
                    for(int l10 = 0; l10 < k1; l10++)
                        if(l10 <= l2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 1)
            {
                for(int i3 = l1 - 1; i3 >= 0; i3--)
                {
                    for(int i11 = 0; i11 < k1; i11++)
                        if(i11 <= i3)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 2)
            {
                for(int j3 = 0; j3 < l1; j3++)
                {
                    for(int j11 = 0; j11 < k1; j11++)
                        if(j11 >= j3)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 3)
            {
                for(int k3 = l1 - 1; k3 >= 0; k3--)
                {
                    for(int k11 = 0; k11 < k1; k11++)
                        if(k11 >= k3)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else
            {
                return;
            }
        }
        if(i2 == 2)
        {
            if(j2 == 0)
            {
                for(int l3 = l1 - 1; l3 >= 0; l3--)
                {
                    for(int l11 = 0; l11 < k1; l11++)
                        if(l11 <= l3 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 1)
            {
                for(int i4 = 0; i4 < l1; i4++)
                {
                    for(int i12 = 0; i12 < k1; i12++)
                        if(i12 >= i4 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 2)
            {
                for(int j4 = 0; j4 < l1; j4++)
                {
                    for(int j12 = k1 - 1; j12 >= 0; j12--)
                        if(j12 <= j4 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 3)
            {
                for(int k4 = l1 - 1; k4 >= 0; k4--)
                {
                    for(int k12 = k1 - 1; k12 >= 0; k12--)
                        if(k12 >= k4 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else
            {
                return;
            }
        }
        if(i2 == 3)
        {
            if(j2 == 0)
            {
                for(int l4 = l1 - 1; l4 >= 0; l4--)
                {
                    for(int l12 = k1 - 1; l12 >= 0; l12--)
                        if(l12 <= l4 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 1)
            {
                for(int i5 = l1 - 1; i5 >= 0; i5--)
                {
                    for(int i13 = 0; i13 < k1; i13++)
                        if(i13 >= i5 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 2)
            {
                for(int j5 = 0; j5 < l1; j5++)
                {
                    for(int j13 = 0; j13 < k1; j13++)
                        if(j13 <= j5 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 3)
            {
                for(int k5 = 0; k5 < l1; k5++)
                {
                    for(int k13 = k1 - 1; k13 >= 0; k13--)
                        if(k13 >= k5 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else
            {
                return;
            }
        }
        if(i2 == 4)
        {
            if(j2 == 0)
            {
                for(int l5 = l1 - 1; l5 >= 0; l5--)
                {
                    for(int l13 = 0; l13 < k1; l13++)
                        if(l13 >= l5 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 1)
            {
                for(int i6 = 0; i6 < l1; i6++)
                {
                    for(int i14 = 0; i14 < k1; i14++)
                        if(i14 <= i6 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 2)
            {
                for(int j6 = 0; j6 < l1; j6++)
                {
                    for(int j14 = k1 - 1; j14 >= 0; j14--)
                        if(j14 >= j6 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 3)
            {
                for(int k6 = l1 - 1; k6 >= 0; k6--)
                {
                    for(int k14 = k1 - 1; k14 >= 0; k14--)
                        if(k14 <= k6 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else
            {
                return;
            }
        }
        if(i2 == 5)
        {
            if(j2 == 0)
            {
                for(int l6 = l1 - 1; l6 >= 0; l6--)
                {
                    for(int l14 = k1 - 1; l14 >= 0; l14--)
                        if(l14 >= l6 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 1)
            {
                for(int i7 = l1 - 1; i7 >= 0; i7--)
                {
                    for(int i15 = 0; i15 < k1; i15++)
                        if(i15 <= i7 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 2)
            {
                for(int j7 = 0; j7 < l1; j7++)
                {
                    for(int j15 = 0; j15 < k1; j15++)
                        if(j15 >= j7 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 3)
            {
                for(int k7 = 0; k7 < l1; k7++)
                {
                    for(int k15 = k1 - 1; k15 >= 0; k15--)
                        if(k15 <= k7 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else
            {
                return;
            }
        }
        if(i2 == 6)
        {
            if(j2 == 0)
            {
                for(int l7 = 0; l7 < l1; l7++)
                {
                    for(int l15 = 0; l15 < k1; l15++)
                        if(l15 <= k1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 1)
            {
                for(int i8 = 0; i8 < l1; i8++)
                {
                    for(int i16 = 0; i16 < k1; i16++)
                        if(i8 <= l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 2)
            {
                for(int j8 = 0; j8 < l1; j8++)
                {
                    for(int j16 = 0; j16 < k1; j16++)
                        if(j16 >= k1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 3)
            {
                for(int k8 = 0; k8 < l1; k8++)
                {
                    for(int k16 = 0; k16 < k1; k16++)
                        if(k8 >= l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
        }
        if(i2 == 7)
        {
            if(j2 == 0)
            {
                for(int l8 = 0; l8 < l1; l8++)
                {
                    for(int l16 = 0; l16 < k1; l16++)
                        if(l16 <= l8 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 1)
            {
                for(int i9 = l1 - 1; i9 >= 0; i9--)
                {
                    for(int i17 = 0; i17 < k1; i17++)
                        if(i17 <= i9 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 2)
            {
                for(int j9 = l1 - 1; j9 >= 0; j9--)
                {
                    for(int j17 = k1 - 1; j17 >= 0; j17--)
                        if(j17 <= j9 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 3)
            {
                for(int k9 = 0; k9 < l1; k9++)
                {
                    for(int k17 = k1 - 1; k17 >= 0; k17--)
                        if(k17 <= k9 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
        }
        if(i2 == 8)
        {
            if(j2 == 0)
            {
                for(int l9 = 0; l9 < l1; l9++)
                {
                    for(int l17 = 0; l17 < k1; l17++)
                        if(l17 >= l9 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 1)
            {
                for(int i10 = l1 - 1; i10 >= 0; i10--)
                {
                    for(int i18 = 0; i18 < k1; i18++)
                        if(i18 >= i10 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 2)
            {
                for(int j10 = l1 - 1; j10 >= 0; j10--)
                {
                    for(int j18 = k1 - 1; j18 >= 0; j18--)
                        if(j18 >= j10 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if(j2 == 3)
            {
                for(int k10 = 0; k10 < l1; k10++)
                {
                    for(int k18 = k1 - 1; k18 >= 0; k18--)
                        if(k18 >= k10 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

            }
        }
    }

    private FileArchive abb()
    {
        byte abyte0[] = null;
        String s = null;
        try
        {
            s = Signlink.findcachedir();
            abyte0 = abe((new StringBuilder()).append(s).append("/worldmap.dat").toString());
            if(!abg(abyte0))
                abyte0 = null;
            if(abyte0 != null)
                return new FileArchive(abyte0);
        }
        catch(Throwable throwable) { }
        abyte0 = abc();
        if(s != null && abyte0 != null)
            try
            {
                abf((new StringBuilder()).append(s).append("/worldmap.dat").toString(), abyte0);
            }
            catch(Throwable throwable1) { }
        return new FileArchive(abyte0);
    }

    private byte[] abc()
    {
        System.out.println("Updating");
        afc(0, "Requesting map");
        try
        {
            String s = "";
            for(int k = 0; k < 10; k++)
                s = (new StringBuilder()).append(s).append(Signature.agi[k]).toString();

            DataInputStream datainputstream;
            if(super.ajd != null)
                datainputstream = new DataInputStream(new FileInputStream("worldmap.jag"));
            else
                datainputstream = new DataInputStream((new URL(getCodeBase(), (new StringBuilder()).append("worldmap").append(s).append(".jag").toString())).openStream());
            int i1 = 0;
            int j1 = 0;
            int k1 = 0x50a34;
            byte abyte0[] = new byte[k1];
            while(j1 < k1) 
            {
                int l1 = k1 - j1;
                if(l1 > 1000)
                    l1 = 1000;
                int i2 = datainputstream.read(abyte0, j1, l1);
                if(i2 < 0)
                    throw new IOException("EOF");
                j1 += i2;
                int j2 = (j1 * 100) / k1;
                if(j2 != i1)
                    afc(j2, (new StringBuilder()).append("Loading map - ").append(j2).append("%").toString());
                i1 = j2;
            }
            datainputstream.close();
            return abyte0;
        }
        catch(IOException ioexception)
        {
            System.out.println("Error loading");
            ioexception.printStackTrace();
            return null;
        }
    }

    private byte[] abe(String s)
        throws IOException
    {
        File file = new File(s);
        if(!file.exists())
        {
            return null;
        } else
        {
            int k = (int)file.length();
            byte abyte0[] = new byte[k];
            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
            datainputstream.readFully(abyte0, 0, k);
            datainputstream.close();
            return abyte0;
        }
    }

    private void abf(String s, byte abyte0[])
        throws IOException
    {
        FileOutputStream fileoutputstream = new FileOutputStream(s);
        fileoutputstream.write(abyte0, 0, abyte0.length);
        fileoutputstream.close();
    }

    private boolean abg(byte abyte0[])
        throws Exception
    {
        if(abyte0 == null)
            return false;
        MessageDigest messagedigest = MessageDigest.getInstance("SHA");
        messagedigest.reset();
        messagedigest.update(abyte0);
        byte abyte1[] = messagedigest.digest();
        for(int k = 0; k < 20; k++)
            if(abyte1[k] != Signature.agi[k])
                return false;

        return true;
    }

    public Viewer()
    {
        aab = 0x887755;
        aac = 0x776644;
        aad = 0x665533;
        aae = 0xaa0000;
        aaf = 0x990000;
        aag = 0x880000;
        aah = true;
        abh = new IndexedSprite[100];
        abi = new Sprite[100];
        ace = new int[2000];
        acf = new int[2000];
        acg = new int[2000];
        aci = new int[2000];
        acj = new int[2000];
        ack = new int[2000];
        acl = 5;
        acm = 13;
        acn = 140;
        ada = 470;
        add = false;
        ade = -1;
        adf = -1;
        adg = -1;
        adm = false;
        aeg = 1000;
        aeh = new String[aeg];
        aei = new int[aeg];
        aej = new int[aeg];
        aek = new int[aeg];
        ael = 4D;
        aem = 4D;
    }

    private static boolean aaa;
    private int aab;
    private int aac;
    private int aad;
    private int aae;
    private int aaf;
    private int aag;
    private boolean aah;
    private int aai;
    private static int aaj;
    private static int aak;
    private static int aal;
    private static int aam;
    private int aan[];
    private int aba[];
    private int abb[][];
    private int abc[][];
    private byte abd[][];
    private byte abe[][];
    private byte abf[][];
    private byte abg[][];
    private IndexedSprite abh[];
    private Sprite abi[];
    private IndexedFont abj;
    private DrawText abk;
    private DrawText abl;
    private DrawText abm;
    private DrawText abn;
    private DrawText aca;
    private DrawText acb;
    private DrawText acc;
    private DrawText acd;
    private int ace[];
    private int acf[];
    private int acg[];
    private int ach;
    private int aci[];
    private int acj[];
    private int ack[];
    private int acl;
    private int acm;
    private int acn;
    private int ada;
    private int adb;
    private int adc;
    private boolean add;
    private int ade;
    private int adf;
    private int adg;
    private int adh;
    private int adi;
    private int adj;
    private int adk;
    private int adl;
    private boolean adm;
    private Sprite adn;
    private int aea;
    private int aeb;
    private int aec;
    private int aed;
    private static boolean aee = true;
    private int aef;
    private int aeg;
    private String aeh[];
    private int aei[];
    private int aej[];
    private int aek[];
    private double ael;
    private double aem;
    private static int aen;
    private static int afa;
    private String afb[] = {
        "General Store", "Sword Shop", "Magic Shop", "Axe Shop", "Helmet Shop", "Bank", "Quest Start", "Amulet Shop", "Mining Site", "Furnace", 
        "Anvil", "Combat Training", "Dungeon", "Staff Shop", "Platebody Shop", "Platelegs Shop", "Scimitar Shop", "Archery Shop", "Shield Shop", "Altar", 
        "Herbalist", "Jewelery", "Gem Shop", "Crafting Shop", "Candle Shop", "Fishing Shop", "Fishing Spot", "Clothes Shop", "Apothecary", "Silk Trader", 
        "Kebab Seller", "Pub/Bar", "Mace Shop", "Tannery", "Rare Trees", "Spinning Wheel", "Food Shop", "Cookery Shop", "Mini-Game", "Water Source", 
        "Cooking Range", "Skirt Shop", "Potters Wheel", "Windmill", "Mining Shop", "Chainmail Shop", "Silver Shop", "Fur Trader", "Spice Shop", "Agility Training", 
        "Vegetable Store", "Slayer Master", "Hair Dressers", "Farming patch", "Makeover Mage", "Guide", "Transportation", "???", "Farming shop", "Loom", 
        "Brewery"
    };

}
