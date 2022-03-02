package com.jagex.runetek3.util;

import com.jagex.runetek3.cache.FileArchive;

import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.applet.Applet;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class Signlink implements Runnable {

    public static void startpriv(InetAddress address) {
        threadliveid = (int) (Math.random() * 99999999D);

        if (active) {
            try {
                Thread.sleep(500L);
            } catch (Exception _ex) {
            }
            active = false;
        }

        socketip = address;

        Thread thread = new Thread(new Signlink());
        thread.setDaemon(true);
        thread.start();

        while (!active) {
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
    }

    public void run() {
        if (!Signlink.lowMemory) {
            try {
                midiPlayer = new MidiPlayer();
            } catch (Exception ex) {
            }
        }

        active = true;
        String s = findcachedir();
        uid = getuid(s);
        for (int i = threadliveid; threadliveid == i; ) {
            audioLoop();

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
                    s = findcachedir();
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
                if (savebuf != null) {
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream(s + savereq);
                        fileoutputstream.write(savebuf, 0, savelen);
                        fileoutputstream.close();
                    } catch (Exception _ex) {
                    }
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

    public static void setSoundfont(FileArchive archive) {
        midiPlayer.setSoundfont(archive.read("soundfont", null));
    }

    // adapted from play_members.html's JS loop
    void audioLoop() {
        if (midiFadingIn) {
            midiFadeVol += 8;
            if (midiFadeVol > midivol) {
                midiFadeVol = midivol;
            }
            midiPlayer.setVolume(0, midiFadeVol);
            if (midiFadeVol == midivol) {
                midiFadingIn = false;
            }
        } else if (midiFadingOut) {
            midiFadeVol -= 8;
            if (midiFadeVol < 0) {
                midiFadeVol = 0;
            }
            midiPlayer.setVolume(0, midiFadeVol);
            if (midiFadeVol == 0) {
                midiFadingOut = false;
                midiFadingIn = true;
            }
        }

        if (midi != "none") {
            if (midi == "stop") {
                midiPlayer.stop();
            } else if (midi == "voladjust") {
                midiPlayer.setVolume(0, midivol);
            } else {
                playMidi(midi);
            }

            if (!midiFadingOut) {
                midi = "none";
            }
        }

        if (wave != "none") {
            AudioInputStream audioInputStream;

            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(wave));
            } catch (Exception e1) {
                e1.printStackTrace();
                return;
            }

            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if (auline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
                if (curPosition == Position.RIGHT) {
                    pan.setValue(1.0f);
                } else if (curPosition == Position.LEFT) {
                    pan.setValue(-1.0f);
                }
            }

            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0) {
                        auline.write(abData, 0, nBytesRead);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                auline.drain();
                auline.close();
            }

            wave = "none";
        }
    }

    public static void playMidi(String music) {
        if (midiFadingOut) {
            return;
        } else if (!midiFadingOut && !midiFadingIn && midifade && midiPlayer.running()) {
            midiFadingOut = true;
            midiFadeVol = midivol;
            return;
        }

        try {
            if (midifade && midiFadingIn) {
                midiFadingOut = false;
                midiFadingIn = true;
                midiFadeVol = 0;
                midiPlayer.play(MidiSystem.getSequence(new File(music)), midifade, midiFadeVol);
            } else {
                midiPlayer.play(MidiSystem.getSequence(new File(music)), midifade, midivol);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void printMemUsage() {
        Runtime runtime = Runtime.getRuntime();
        int mb = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L / 1024L);
        System.out.println("RAM: " + mb + " MB");
    }

    public static String findcachedir() {
        String[] directories = {
            ""
        };

        for (String directory : directories) {
            try {
                File file1 = new File(directory + cachedir);
                if (file1.exists() || file1.mkdir()) {
                    return directory + cachedir + "/";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    public static int getuid(String dir) {
        try {
            File file = new File(dir + "uid.dat");
            if (!file.exists() || file.length() < 4L) {
                DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(dir + "uid.dat"));
                dataoutputstream.writeInt((int) (Math.random() * 99999999D));
                dataoutputstream.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(dir + "uid.dat"));
            int i = datainputstream.readInt();
            datainputstream.close();
            return i + 1;
        } catch (Exception _ex) {
            _ex.printStackTrace();
            return 0;
        }
    }

    public static long gethash(String str) {
        str = str.trim();
        long l = 0L;
        for (int i = 0; i < str.length() && i < 12; i++) {
            char c = str.charAt(i);
            l *= 37L;
            if (c >= 'A' && c <= 'Z') {
                l += (1 + c) - 65;
            } else if (c >= 'a' && c <= 'z') {
                l += (1 + c) - 97;
            } else if (c >= '0' && c <= '9') {
                l += (27 + c) - 48;
            }
        }

        return l;
    }

    public static void looprate(int rate) {
        looprate = rate;
    }

    public static synchronized byte[] cacheload(String name, boolean rawName) {
        if (!active) {
            return null;
        }

        String value = name;
        if (!rawName) {
            value = String.valueOf(gethash(name));
        }
        for (loadreq = value ; loadreq != null; ) {
            try {
                Thread.sleep(1L);
            } catch (Exception _ex) {
            }
        }

        return loadbuf;
    }

    public static synchronized void cachesave(String name, byte[] src, boolean rawName) {
        if (!active) {
            return;
        }

        if (src.length > 20000000) {
            return;
        }

        while (savereq != null) {
            try {
                Thread.sleep(1L);
            } catch (Exception _ex) {
            }
        }

        String value = name;
        if (!rawName) {
            value = String.valueOf(gethash(name));
        }
        savelen = src.length;
        savebuf = src;
        for (savereq = value; savereq != null; ) {
            try {
                Thread.sleep(1L);
            } catch (Exception _ex) {
            }
        }
    }

    public static synchronized Socket opensocket(int port) throws IOException {
        for (socketreq = port; socketreq != 0; ) {
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }

        if (socket == null) {
            throw new IOException("could not open socket");
        } else {
            return socket;
        }
    }

    public static synchronized DataInputStream openurl(String url) throws IOException {
        for (urlreq = url; urlreq != null; ) {
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }

        if (urlstream == null) {
            throw new IOException("could not open: " + url);
        } else {
            return urlstream;
        }
    }

    public static synchronized void dnslookup(String s) {
        dns = s;
        dnsreq = s;
    }

    public static synchronized void startthread(Runnable runnable, int i) {
        threadreqpri = i;
        threadreq = runnable;
    }

    public static synchronized boolean wavesave(byte[] src, int len) {
        if (len > 2000000) {
            return false;
        }

        if (savereq != null) {
            return false;
        }

        wavepos = (wavepos + 1) % 5;
        savelen = len;
        savebuf = src;
        waveplay = true;
        savereq = "sound" + wavepos + ".wav";
        return true;
    }

    public static synchronized boolean wavereplay() {
        if (savereq != null) {
            return false;
        }

        savebuf = null;
        waveplay = true;
        savereq = "sound" + wavepos + ".wav";
        return true;
    }

    public static synchronized void midisave(byte[] src, int len) {
        if (len > 2000000) {
            return;
        }

        if (savereq != null) {
            return;
        }

        midipos = (midipos + 1) % 5;
        savelen = len;
        savebuf = src;
        midiplay = true;
        savereq = "jingle" + midipos + ".mid";
    }

    public static void reporterror(String err) {
        if (!reporterror) {
            return;
        }

        if (!active) {
            return;
        }

        System.out.println("Error: " + err);
//        try {
//            s = s.replace('@', '_');
//            s = s.replace('&', '_');
//            s = s.replace('#', '_');
//            DataInputStream datainputstream = openurl("reporterror" + 225 + ".cgi?error=" + errorname + " " + s);
//            datainputstream.readLine();
//            datainputstream.close();
//        } catch (IOException _ex) {
//        }
    }

    public Signlink() {
    }

    public static boolean lowMemory = false;
    public static int clientversion = 225;
    public static String cachedir = "." + clientversion + "_store";
    public static int uid;
    public static Applet mainapp;
    public static boolean sunjava;
    public static boolean active;
    public static int threadliveid;
    public static InetAddress socketip;

    public static int socketreq;
    public static Socket socket;

    public static int threadreqpri = 1;
    public static Runnable threadreq;

    public static String dnsreq;
    public static String dns;

    public static String loadreq;
    public static byte[] loadbuf;

    public static int savelen;
    public static String savereq;
    public static byte[] savebuf;

    public static String urlreq;
    public static DataInputStream urlstream;

    public static int looprate = 50;

    public static boolean midiplay;
    public static int midipos;
    public static String midi = "none";
    public static int midivol = 192;
    public static boolean midifade = false;
    public static boolean midiFadingIn = false;
    public static boolean midiFadingOut = false;
    public static int midiFadeVol = 0;
    private static MidiPlayer midiPlayer = null;

    public static boolean waveplay;
    public static int wavepos;
    public static String wave = "none";
    public static int wavevol;
    enum Position {
        LEFT, RIGHT, NORMAL
    }
    private final int EXTERNAL_BUFFER_SIZE = 524288;
    private Position curPosition = Position.NORMAL;

    public static boolean reporterror = true;
    public static String errorname = "";

}
