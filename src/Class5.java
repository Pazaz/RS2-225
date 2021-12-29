// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.io.*;
import java.net.Socket;
import sign.signlink;

public class Class5
        implements Runnable {

    public Class5(Applet_Sub1 applet_sub1, byte byte0, Socket socket)
            throws IOException {
        aBoolean180 = false;
        aBoolean184 = false;
        aBoolean189 = false;
        aBoolean190 = false;
        if (byte0 == 2)
            byte0 = 0;
        else
            aBoolean180 = !aBoolean180;
        anApplet_Sub1_185 = applet_sub1;
        aSocket183 = socket;
        aSocket183.setSoTimeout(30000);
        aSocket183.setTcpNoDelay(true);
        anInputStream181 = aSocket183.getInputStream();
        anOutputStream182 = aSocket183.getOutputStream();
    }

    public void method177() {
        aBoolean184 = true;
        try {
            if (anInputStream181 != null)
                anInputStream181.close();
            if (anOutputStream182 != null)
                anOutputStream182.close();
            if (aSocket183 != null)
                aSocket183.close();
        } catch (IOException _ex) {
            System.out.println("Error closing stream");
        }
        aBoolean189 = false;
        synchronized (this) {
            notify();
        }
        aByteArray186 = null;
    }

    public int method178()
            throws IOException {
        if (aBoolean184)
            return 0;
        else
            return anInputStream181.read();
    }

    public int method179()
            throws IOException {
        if (aBoolean184)
            return 0;
        else
            return anInputStream181.available();
    }

    public void method180(byte abyte0[], int i, int j)
            throws IOException {
        if (aBoolean184)
            return;
        int k;
        for (; j > 0; j -= k) {
            k = anInputStream181.read(abyte0, i, j);
            if (k <= 0)
                throw new IOException("EOF");
            i += k;
        }

    }

    public void method181(byte abyte0[], int i, boolean flag, int j)
            throws IOException {
        if (!flag)
            return;
        if (aBoolean184)
            return;
        if (aBoolean190) {
            aBoolean190 = false;
            throw new IOException("Error in writer thread");
        }
        if (aByteArray186 == null)
            aByteArray186 = new byte[5000];
        synchronized (this) {
            for (int k = 0; k < i; k++) {
                aByteArray186[anInt188] = abyte0[k + j];
                anInt188 = (anInt188 + 1) % 5000;
                if (anInt188 == (anInt187 + 4900) % 5000)
                    throw new IOException("buffer overflow");
            }

            if (!aBoolean189) {
                aBoolean189 = true;
                anApplet_Sub1_185.method12(this, 2);
            }
            notify();
        }
    }

    public void run() {
        while (aBoolean189) {
            int i;
            int j;
            synchronized (this) {
                if (anInt188 == anInt187)
                    try {
                        wait();
                    } catch (InterruptedException _ex) {
                    }
                if (!aBoolean189)
                    return;
                j = anInt187;
                if (anInt188 >= anInt187)
                    i = anInt188 - anInt187;
                else
                    i = 5000 - anInt187;
            }
            if (i > 0) {
                try {
                    anOutputStream182.write(aByteArray186, j, i);
                } catch (IOException _ex) {
                    aBoolean190 = true;
                }
                anInt187 = (anInt187 + i) % 5000;
                try {
                    if (anInt188 == anInt187)
                        anOutputStream182.flush();
                } catch (IOException _ex) {
                    aBoolean190 = true;
                }
            }
        }
    }

    public boolean aBoolean180;
    public InputStream anInputStream181;
    public OutputStream anOutputStream182;
    public Socket aSocket183;
    public boolean aBoolean184;
    public Applet_Sub1 anApplet_Sub1_185;
    public byte aByteArray186[];
    public int anInt187;
    public int anInt188;
    public boolean aBoolean189;
    public boolean aBoolean190;
}
