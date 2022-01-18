package com.runescape;

import com.runescape.graphics.DrawArea;
import com.runescape.graphics.Sprite;
import com.runescape.util.InputTracking;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class GameShell extends Applet
    implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

    public void initFrame(int i, int j) {
        gameWidth = j;
        gameHeight = i;
        frame = new GameFrame(gameHeight, this, gameWidth);
        graphics = getBaseComponent().getGraphics();
        drawArea = new DrawArea(getBaseComponent(), gameWidth, gameHeight);
        startThread(this, 1);
    }

    public void initApplet(int i, int j) {
        gameWidth = j;
        gameHeight = i;
        graphics = getBaseComponent().getGraphics();
        drawArea = new DrawArea(getBaseComponent(), gameWidth, gameHeight);
        startThread(this, 1);
    }

    public void run() {
        getBaseComponent().addMouseListener(this);
        getBaseComponent().addMouseMotionListener(this);
        getBaseComponent().addKeyListener(this);
        getBaseComponent().addFocusListener(this);
        if (frame != null)
            frame.addWindowListener(this);
        showProgress(true, "Loading...", 0);
        load();
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
                    shutdown();
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
                update();
                mouseButton = 0;
                anInt28 = anInt29;
            }

            i1 &= 0xff;
            if (anInt8 > 0)
                anInt11 = (1000 * j) / (anInt8 * 256);
            draw();
        }

        if (anInt7 == -1) {
            shutdown();
        }
    }

    public void shutdown() {
        anInt7 = -2;
        unload();

        try {
            Thread.sleep(1000L);
        } catch (Exception _ex) {
        }

        try {
            System.exit(0);
        } catch (Throwable _ex) {
        }
    }

    public void setLoopRate(int j) {
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
            shutdown();
    }

    public void update(Graphics g) {
        if (graphics == null)
            graphics = g;
        aBoolean18 = true;
        refresh();
    }

    public void paint(Graphics g) {
        if (graphics == null)
            graphics = g;
        aBoolean18 = true;
        refresh();
    }

    public void mousePressed(MouseEvent e) {
        int i = e.getX();
        int j = e.getY();
        if (frame != null) {
            i -= 4;
            j -= 22;
        }
        idleCycles = 0;
        clickX = i;
        clickY = j;
        if (SwingUtilities.isRightMouseButton(e)) {
            mouseButton = 2;
            dragButton = 2;
        } else {
            mouseButton = 1;
            dragButton = 1;
        }
        if (InputTracking.enabled)
            InputTracking.mousePressed(i, e.isMetaDown() ? 1 : 0, j);
    }

    public void mouseReleased(MouseEvent mouseevent) {
        idleCycles = 0;
        dragButton = 0;
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
        idleCycles = 0;
        mouseX = i;
        mouseY = j;
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
        idleCycles = 0;
        mouseX = i;
        mouseY = j;
        if (InputTracking.enabled)
            InputTracking.mouseMoved(j, i);
    }

    public void keyPressed(KeyEvent keyevent) {
        idleCycles = 0;
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
            keyDown[j] = 1;
        if (j > 4) {
            anIntArray27[anInt29] = j;
            anInt29 = anInt29 + 1 & 0x7f;
        }
        if (InputTracking.enabled)
            InputTracking.keyPressed(j);
    }

    public void keyReleased(KeyEvent keyevent) {
        idleCycles = 0;
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
            keyDown[c] = 0;
        if (InputTracking.enabled)
            InputTracking.keyReleased(c);
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void focusGained(FocusEvent focusevent) {
        aBoolean18 = true;
        refresh();
        if (InputTracking.enabled)
            InputTracking.focusGained();
    }

    public void focusLost(FocusEvent focusevent) {
        if (InputTracking.enabled)
            InputTracking.focusLost();
    }

    public int pollKey() {
        int i = -1;
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

    public void load() {
    }

    public void update() {
    }

    public void unload() {
    }

    public void draw() {
    }

    public void refresh() {
    }

    public Component getBaseComponent() {
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
            graphics = getBaseComponent().getGraphics();
            try {
                getBaseComponent().repaint();
            } catch (Exception _ex) {
            }
            try {
                Thread.sleep(1000L);
            } catch (Exception _ex) {
            }
        }
        Font font = new Font("Helvetica", 1, 13);
        FontMetrics fontmetrics = getBaseComponent().getFontMetrics(font);
        Font font1 = new Font("Helvetica", 0, 13);
        getBaseComponent().getFontMetrics(font1);
        if (aBoolean18) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, gameWidth, gameHeight);
            aBoolean18 = false;
        }
        Color color = new Color(140, 17, 17);
        int j = gameHeight / 2 - 18;
        graphics.setColor(color);
        graphics.drawRect(gameWidth / 2 - 152, j, 304, 34);
        graphics.fillRect(gameWidth / 2 - 150, j + 2, i * 3, 30);
        graphics.setColor(Color.black);
        graphics.fillRect((gameWidth / 2 - 150) + i * 3, j + 2, 300 - i * 3, 30);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString(s, (gameWidth - fontmetrics.stringWidth(s)) / 2, j + 22);
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
        keyDown = new int[128];
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
    public int gameWidth;
    public int gameHeight;
    public Graphics graphics;
    public DrawArea drawArea;
    public Sprite[] aClass38_Sub2_Sub2_Sub2Array16;
    public GameFrame frame;
    public boolean aBoolean18;
    public int idleCycles;
    public int dragButton;
    public int mouseX;
    public int mouseY;
    public int mouseButton;
    public int clickX;
    public int clickY;
    public int[] keyDown;
    public int[] anIntArray27;
    public int anInt28;
    public int anInt29;
}
