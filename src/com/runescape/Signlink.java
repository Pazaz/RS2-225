package com.runescape;

import java.applet.Applet;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class Signlink
        implements Runnable {

    public static void startpriv(InetAddress inetaddress) {
        threadliveid = (int) (Math.random() * 99999999D);
        if (active) {
            try {
                Thread.sleep(500L);
            } catch (Exception _ex) {
            }
            active = false;
        }
        socketreq = 0;
        threadreq = null;
        dnsreq = null;
        loadreq = null;
        savereq = null;
        urlreq = null;
        socketip = inetaddress;
        Thread thread = new Thread(new Signlink());
        thread.setDaemon(true);
        thread.start();
        while (!active)
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
    }

    public void run() {
        active = true;
        String s = findcachedir();
        uid = getuid(s);
        for (int i = threadliveid; threadliveid == i; ) {
            if (socketreq != 0) {
                try {
                    socket = new Socket(socketip, socketreq);
                } catch (Exception _ex) {
                    socket = null;
                }
                socketreq = 0;
            } else if (threadreq != null) {
                Thread thread = new Thread(threadreq);
                thread.setDaemon(true);
                thread.start();
                thread.setPriority(threadreqpri);
                threadreq = null;
            } else if (dnsreq != null) {
                try {
                    dns = InetAddress.getByName(dnsreq).getHostName();
                } catch (Exception _ex) {
                    dns = "unknown";
                }
                dnsreq = null;
            } else if (loadreq != null) {
                loadbuf = null;
                try {
                    File file = new File(s + loadreq);
                    if (file.exists()) {
                        int j = (int) file.length();
                        loadbuf = new byte[j];
                        DataInputStream datainputstream = new DataInputStream(new FileInputStream(s + loadreq));
                        datainputstream.readFully(loadbuf, 0, j);
                        datainputstream.close();
                    }
                } catch (Exception _ex) {
                }
                loadreq = null;
            } else if (savereq != null) {
                if (savebuf != null)
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream(s + savereq);
                        fileoutputstream.write(savebuf, 0, savelen);
                        fileoutputstream.close();
                    } catch (Exception _ex) {
                    }
                if (waveplay) {
                    wave = s + savereq;
                    waveplay = false;
                }
                if (midiplay) {
                    midi = s + savereq;
                    midiplay = false;
                }
                savereq = null;
            } else if (urlreq != null) {
                try {
                    urlstream = new DataInputStream((new URL(mainapp.getCodeBase(), urlreq)).openStream());
                } catch (Exception _ex) {
                    urlstream = null;
                }
                urlreq = null;
            }
            try {
                Thread.sleep(looprate);
            } catch (Exception _ex) {
            }
        }

    }

    public static String findcachedir() {
        String as[] = {
                "c:/windows/", "c:/winnt/", "d:/windows/", "d:/winnt/", "e:/windows/", "e:/winnt/", "f:/windows/", "f:/winnt/", "c:/", "~/",
                "/tmp/", ""
        };
        String s = ".file_store_32";
        for (int i = 0; i < as.length; i++)
            try {
                String s1 = as[i];
                if (s1.length() > 0) {
                    File file = new File(s1);
                    if (!file.exists())
                        continue;
                }
                File file1 = new File(s1 + s);
                if (file1.exists() || file1.mkdir())
                    return s1 + s + "/";
            } catch (Exception _ex) {
            }

        return null;
    }

    public static int getuid(String s) {
        try {
            File file = new File(s + "uid.dat");
            if (!file.exists() || file.length() < 4L) {
                DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(s + "uid.dat"));
                dataoutputstream.writeInt((int) (Math.random() * 99999999D));
                dataoutputstream.close();
            }
        } catch (Exception _ex) {
        }
        try {
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(s + "uid.dat"));
            int i = datainputstream.readInt();
            datainputstream.close();
            return i + 1;
        } catch (Exception _ex) {
            return 0;
        }
    }

    public static long gethash(String s) {
        s = s.trim();
        long l = 0L;
        for (int i = 0; i < s.length() && i < 12; i++) {
            char c = s.charAt(i);
            l *= 37L;
            if (c >= 'A' && c <= 'Z')
                l += (1 + c) - 65;
            else if (c >= 'a' && c <= 'z')
                l += (1 + c) - 97;
            else if (c >= '0' && c <= '9')
                l += (27 + c) - 48;
        }

        return l;
    }

    public static void looprate(int i) {
        looprate = i;
    }

    public static synchronized byte[] cacheload(String s) {
        if (!active)
            return null;
        for (loadreq = String.valueOf(gethash(s)); loadreq != null; )
            try {
                Thread.sleep(1L);
            } catch (Exception _ex) {
            }

        return loadbuf;
    }

    public static synchronized void cachesave(String s, byte abyte0[]) {
        if (!active)
            return;
        if (abyte0.length > 0x1e8480)
            return;
        while (savereq != null)
            try {
                Thread.sleep(1L);
            } catch (Exception _ex) {
            }
        savelen = abyte0.length;
        savebuf = abyte0;
        for (savereq = String.valueOf(gethash(s)); savereq != null; )
            try {
                Thread.sleep(1L);
            } catch (Exception _ex) {
            }

    }

    public static synchronized Socket opensocket(int i)
            throws IOException {
        for (socketreq = i; socketreq != 0; )
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }

        if (socket == null)
            throw new IOException("could not open socket");
        else
            return socket;
    }

    public static synchronized DataInputStream openurl(String s)
            throws IOException {
        for (urlreq = s; urlreq != null; )
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }

        if (urlstream == null)
            throw new IOException("could not open: " + s);
        else
            return urlstream;
    }

    public static synchronized void dnslookup(String s) {
        dns = s;
        dnsreq = s;
    }

    public static synchronized void startthread(Runnable runnable, int i) {
        threadreqpri = i;
        threadreq = runnable;
    }

    public static synchronized boolean wavesave(byte abyte0[], int i) {
        if (i > 0x1e8480)
            return false;
        if (savereq != null) {
            return false;
        } else {
            wavepos = (wavepos + 1) % 5;
            savelen = i;
            savebuf = abyte0;
            waveplay = true;
            savereq = "sound" + wavepos + ".wav";
            return true;
        }
    }

    public static synchronized boolean wavereplay() {
        if (savereq != null) {
            return false;
        } else {
            savebuf = null;
            waveplay = true;
            savereq = "sound" + wavepos + ".wav";
            return true;
        }
    }

    public static synchronized void midisave(byte abyte0[], int i) {
        if (i > 0x1e8480)
            return;
        if (savereq != null) {
            return;
        } else {
            midipos = (midipos + 1) % 5;
            savelen = i;
            savebuf = abyte0;
            midiplay = true;
            savereq = "jingle" + midipos + ".mid";
            return;
        }
    }

    public static void reporterror(String s) {
        if (!reporterror)
            return;
        if (!active)
            return;
        System.out.println("Error: " + s);
        try {
            s = s.replace('@', '_');
            s = s.replace('&', '_');
            s = s.replace('#', '_');
            DataInputStream datainputstream = openurl("reporterror" + 225 + ".cgi?error=" + errorname + " " + s);
            datainputstream.readLine();
            datainputstream.close();
            return;
        } catch (IOException _ex) {
            return;
        }
    }

    public Signlink() {
    }

    public static final int clientversion = 225;
    public static int uid;
    public static Applet mainapp;
    public static boolean sunjava;
    public static boolean active;
    public static int threadliveid;
    public static InetAddress socketip;
    public static int socketreq;
    public static Socket socket = null;
    public static int threadreqpri = 1;
    public static Runnable threadreq = null;
    public static String dnsreq = null;
    public static String dns = null;
    public static String loadreq = null;
    public static byte loadbuf[] = null;
    public static int savelen;
    public static String savereq = null;
    public static byte savebuf[] = null;
    public static String urlreq = null;
    public static DataInputStream urlstream = null;
    public static int looprate = 50;
    public static boolean midiplay;
    public static int midipos;
    public static String midi = null;
    public static int midivol;
    public static int midifade;
    public static boolean waveplay;
    public static int wavepos;
    public static String wave = null;
    public static int wavevol;
    public static boolean reporterror = true;
    public static String errorname = "";

}
