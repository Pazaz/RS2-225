package com.runescape;

import com.runescape.graphics.DrawArea;
import com.runescape.graphics.Sprite;
import com.runescape.util.InputTracking;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class GameShell extends Applet
        implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

    public void initFrame(int i, int j, int k) {
        anInt12 = j;
        anInt13 = i;
        frame = new GameFrame(anInt13, this, anInt12);
        graphics = method11(aByte5).getGraphics();
        drawArea = new DrawArea(method11(aByte5), anInt12, anInt13);
        startThread(this, 1);
    }

    public void initApplet(int i, boolean flag, int j) {
        anInt12 = j;
        anInt13 = i;
        graphics = method11(aByte5).getGraphics();
        drawArea = new DrawArea(method11(aByte5), anInt12, anInt13);
        startThread(this, 1);
    }

    public void run() {
        method11(aByte5).addMouseListener(this);
        method11(aByte5).addMouseMotionListener(this);
        method11(aByte5).addKeyListener(this);
        method11(aByte5).addFocusListener(this);
        if (frame != null)
            frame.addWindowListener(this);
        showProgress(true, "Loading...", 0);
        method6();
        int i = 0;
        int j = 256;
        int k = 1;
        int i1 = 0;
        for (int j1 = 0; j1 < 10; j1++)
            aLongArray10[j1] = System.currentTimeMillis();

        long l = System.currentTimeMillis();
        while (anInt7 >= 0) {
            if (anInt7 > 0) {
                anInt7--;
                if (anInt7 == 0) {
                    method3(-652);
                    return;
                }
            }
            int k1 = j;
            int i2 = k;
            j = 300;
            k = 1;
            long l1 = System.currentTimeMillis();
            if (aLongArray10[i] == 0L) {
                j = k1;
                k = i2;
            } else if (l1 > aLongArray10[i])
                j = (int) ((long) (2560 * anInt8) / (l1 - aLongArray10[i]));
            if (j < 25)
                j = 25;
            if (j > 256) {
                j = 256;
                k = (int) ((long) anInt8 - (l1 - aLongArray10[i]) / 10L);
            }
            aLongArray10[i] = l1;
            i = (i + 1) % 10;
            if (k > 1) {
                for (int j2 = 0; j2 < 10; j2++)
                    if (aLongArray10[j2] != 0L)
                        aLongArray10[j2] += k;

            }
            if (k < anInt9)
                k = anInt9;
            try {
                Thread.sleep(k);
            } catch (InterruptedException _ex) {
            }
            for (; i1 < 256; i1 += j) {
                method7(437);
                anInt23 = 0;
                anInt28 = anInt29;
            }

            i1 &= 0xff;
            if (anInt8 > 0)
                anInt11 = (1000 * j) / (anInt8 * 256);
            method9(false);
        }
        if (anInt7 == -1)
            method3(-652);
    }

    public void method3(int i) {
        while (i >= 0)
            aBoolean4 = !aBoolean4;
        anInt7 = -2;
        unload((byte) -28);
        try {
            Thread.sleep(1000L);
        } catch (Exception _ex) {
        }
        try {
            System.exit(0);
            return;
        } catch (Throwable _ex) {
            return;
        }
    }

    public void method4(int j) {
        anInt8 = 1000 / j;
    }

    public void start() {
        if (anInt7 >= 0)
            anInt7 = 0;
    }

    public void stop() {
        if (anInt7 >= 0)
            anInt7 = 4000 / anInt8;
    }

    public void destroy() {
        anInt7 = -1;
        try {
            Thread.sleep(5000L);
        } catch (Exception _ex) {
        }
        if (anInt7 == -1)
            method3(-652);
    }

    public void update(Graphics g) {
        if (graphics == null)
            graphics = g;
        aBoolean18 = true;
        method10(3);
    }

    public void paint(Graphics g) {
        if (graphics == null)
            graphics = g;
        aBoolean18 = true;
        method10(3);
    }

    public void mousePressed(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (frame != null) {
            i -= 4;
            j -= 22;
        }
        anInt19 = 0;
        clickX = i;
        clickY = j;
        if (mouseevent.isMetaDown()) {
            anInt23 = 2;
            anInt20 = 2;
        } else {
            anInt23 = 1;
            anInt20 = 1;
        }
        if (InputTracking.enabled)
            InputTracking.mousePressed(i, mouseevent.isMetaDown() ? 1 : 0, j);
    }

    public void mouseReleased(MouseEvent mouseevent) {
        anInt19 = 0;
        anInt20 = 0;
        if (InputTracking.enabled)
            InputTracking.mouseReleased(mouseevent.isMetaDown() ? 1 : 0);
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mouseEntered(MouseEvent mouseevent) {
        if (InputTracking.enabled)
            InputTracking.mouseEntered();
    }

    public void mouseExited(MouseEvent mouseevent) {
        if (InputTracking.enabled)
            InputTracking.mouseExited();
    }

    public void mouseDragged(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (frame != null) {
            i -= 4;
            j -= 22;
        }
        anInt19 = 0;
        anInt21 = i;
        anInt22 = j;
        if (InputTracking.enabled)
            InputTracking.mouseMoved(j, i);
    }

    public void mouseMoved(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (frame != null) {
            i -= 4;
            j -= 22;
        }
        anInt19 = 0;
        anInt21 = i;
        anInt22 = j;
        if (InputTracking.enabled)
            InputTracking.mouseMoved(j, i);
    }

    public void keyPressed(KeyEvent keyevent) {
        anInt19 = 0;
        int i = keyevent.getKeyCode();
        int j = keyevent.getKeyChar();
        if (j < 30)
            j = 0;
        if (i == 37)
            j = 1;
        if (i == 39)
            j = 2;
        if (i == 38)
            j = 3;
        if (i == 40)
            j = 4;
        if (i == 17)
            j = 5;
        if (i == 8)
            j = 8;
        if (i == 127)
            j = 8;
        if (i == 9)
            j = 9;
        if (i == 10)
            j = 10;
        if (i >= 112 && i <= 123)
            j = (1008 + i) - 112;
        if (i == 36)
            j = 1000;
        if (i == 35)
            j = 1001;
        if (i == 33)
            j = 1002;
        if (i == 34)
            j = 1003;
        if (j > 0 && j < 128)
            anIntArray26[j] = 1;
        if (j > 4) {
            anIntArray27[anInt29] = j;
            anInt29 = anInt29 + 1 & 0x7f;
        }
        if (InputTracking.enabled)
            InputTracking.keyPressed(j);
    }

    public void keyReleased(KeyEvent keyevent) {
        anInt19 = 0;
        int i = keyevent.getKeyCode();
        char c = keyevent.getKeyChar();
        if (c < '\036')
            c = '\0';
        if (i == 37)
            c = '\001';
        if (i == 39)
            c = '\002';
        if (i == 38)
            c = '\003';
        if (i == 40)
            c = '\004';
        if (i == 17)
            c = '\005';
        if (i == 8)
            c = '\b';
        if (i == 127)
            c = '\b';
        if (i == 9)
            c = '\t';
        if (i == 10)
            c = '\n';
        if (c > 0 && c < '\200')
            anIntArray26[c] = 0;
        if (InputTracking.enabled)
            InputTracking.keyReleased(c);
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void focusGained(FocusEvent focusevent) {
        aBoolean18 = true;
        method10(3);
        if (InputTracking.enabled)
            InputTracking.focusGained();
    }

    public void focusLost(FocusEvent focusevent) {
        if (InputTracking.enabled)
            InputTracking.focusLost();
    }

    public int method5(boolean flag) {
        int i = -1;
        if (flag)
            return 2;
        if (anInt29 != anInt28) {
            i = anIntArray27[anInt28];
            anInt28 = anInt28 + 1 & 0x7f;
        }
        return i;
    }

    public void windowActivated(WindowEvent windowevent) {
    }

    public void windowClosed(WindowEvent windowevent) {
    }

    public void windowClosing(WindowEvent windowevent) {
        destroy();
    }

    public void windowDeactivated(WindowEvent windowevent) {
    }

    public void windowDeiconified(WindowEvent windowevent) {
    }

    public void windowIconified(WindowEvent windowevent) {
    }

    public void windowOpened(WindowEvent windowevent) {
    }

    public void method6() {
    }

    public void method7(int i) {
        if (i <= 0)
            aBoolean2 = !aBoolean2;
    }

    public void unload(byte byte0) {
    }

    public void method9(boolean flag) {
    }

    public void method10(int i) {
    }

    public Component method11(byte byte0) {
        if (frame != null)
            return frame;
        else
            return this;
    }

    public void startThread(Runnable runnable, int i) {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(i);
    }

    public void showProgress(boolean flag, String s, int i) {
        while (graphics == null) {
            graphics = method11(aByte5).getGraphics();
            try {
                method11(aByte5).repaint();
            } catch (Exception _ex) {
            }
            try {
                Thread.sleep(1000L);
            } catch (Exception _ex) {
            }
        }
        Font font = new Font("Helvetica", 1, 13);
        FontMetrics fontmetrics = method11(aByte5).getFontMetrics(font);
        Font font1 = new Font("Helvetica", 0, 13);
        method11(aByte5).getFontMetrics(font1);
        if (aBoolean18) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, anInt12, anInt13);
            aBoolean18 = false;
        }
        Color color = new Color(140, 17, 17);
        int j = anInt13 / 2 - 18;
        graphics.setColor(color);
        graphics.drawRect(anInt12 / 2 - 152, j, 304, 34);
        graphics.fillRect(anInt12 / 2 - 150, j + 2, i * 3, 30);
        graphics.setColor(Color.black);
        graphics.fillRect((anInt12 / 2 - 150) + i * 3, j + 2, 300 - i * 3, 30);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString(s, (anInt12 - fontmetrics.stringWidth(s)) / 2, j + 22);
    }

    public GameShell() {
        aBoolean1 = false;
        aBoolean2 = false;
        aBoolean4 = false;
        aByte5 = 3;
        anInt6 = 27808;
        anInt8 = 20;
        anInt9 = 1;
        aLongArray10 = new long[10];
        aClass38_Sub2_Sub2_Sub2Array16 = new Sprite[6];
        aBoolean18 = true;
        anIntArray26 = new int[128];
        anIntArray27 = new int[128];
    }

    public boolean aBoolean1;
    public boolean aBoolean2;
    public boolean aBoolean4;
    public byte aByte5;
    public int anInt6;
    public int anInt7;
    public int anInt8;
    public int anInt9;
    public long[] aLongArray10;
    public int anInt11;
    public int anInt12;
    public int anInt13;
    public Graphics graphics;
    public DrawArea drawArea;
    public Sprite[] aClass38_Sub2_Sub2_Sub2Array16;
    public GameFrame frame;
    public boolean aBoolean18;
    public int anInt19;
    public int anInt20;
    public int anInt21;
    public int anInt22;
    public int anInt23;
    public int clickX;
    public int clickY;
    public int[] anIntArray26;
    public int[] anIntArray27;
    public int anInt28;
    public int anInt29;
}
