package com.jagex.mapviewer;

import com.jagex.runetek3.graphics.DrawArea;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class GameShell extends Applet
    implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

    public void stop() {
        if (state >= 0)
            state = 4000 / deltime;
    }

    public void refresh() {
    }

    public void showProgress(int i, String s) {
        while (graphics == null) {
            graphics = getBaseComponent().getGraphics();
            try {
                getBaseComponent().repaint();
            } catch (Exception exception) {
            }
            try {
                Thread.sleep(1000L);
            } catch (Exception exception1) {
            }
        }
        Font font = new Font("Helvetica", 1, 13);
        FontMetrics fontmetrics = getBaseComponent().getFontMetrics(font);
        Font font1 = new Font("Helvetica", 0, 13);
        if (refresh) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, width, height);
            refresh = false;
        }
        Color color = new Color(140, 17, 17);
        int j = height / 2 - 18;
        graphics.setColor(color);
        graphics.drawRect(width / 2 - 152, j, 304, 34);
        graphics.fillRect(width / 2 - 150, j + 2, i * 3, 30);
        graphics.setColor(Color.black);
        graphics.fillRect((width / 2 - 150) + i * 3, j + 2, 300 - i * 3, 30);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString(s, (width - fontmetrics.stringWidth(s)) / 2, j + 22);
    }

    public void mouseReleased(MouseEvent mouseevent) {
        idleCycles = 0;
        mouseButton = 0;
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
            actionKey[j] = 1;
        if (j > 4) {
            pressedKeys[unprocessedKeyCount] = j;
            unprocessedKeyCount = unprocessedKeyCount + 1 & 0x7f;
        }
    }

    public void startThread(Runnable runnable, int i) {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(i);
    }

    public void windowClosing(WindowEvent windowevent) {
        destroy();
    }

    public void shutdown() {
        state = -2;
        unload();
        if (frame != null) {
            try {
                Thread.sleep(1000L);
            } catch (Exception exception) {
            }
            try {
                System.exit(0);
            } catch (Throwable throwable) {
            }
        }
    }

    public void afi(Graphics g1) {
        if (graphics == null)
            graphics = g1;
        refresh = true;
        refresh();
    }

    public void mouseEntered(MouseEvent mouseevent) {
    }

    public void mouseExited(MouseEvent mouseevent) {
        idleCycles = 0;
        mouseX = -1;
        mouseY = -1;
    }

    public void windowOpened(WindowEvent windowevent) {
    }

    public void windowDeiconified(WindowEvent windowevent) {
    }

    public void windowActivated(WindowEvent windowevent) {
    }

    public void load() {
    }

    public void start() {
        if (state >= 0)
            state = 0;
    }

    public void initFrame(int width, int height) {
        this.width = width;
        this.height = height;
        frame = new GameFrame(this, this.width, this.height);
        graphics = getBaseComponent().getGraphics();
        drawArea = new DrawArea(getBaseComponent(), this.width, this.height);
        startThread(this, 1);
    }

    public int pollKey() {
        int key = -1;
        if (unprocessedKeyCount != lastProcessedKey) {
            key = pressedKeys[lastProcessedKey];
            lastProcessedKey = lastProcessedKey + 1 & 0x7f;
        }
        return key;
    }

    public void draw() {
    }

    public Component getBaseComponent() {
        if (frame != null)
            return frame;
        else
            return this;
    }

    public void mouseClicked(MouseEvent mouseevent) {
    }

    public void mousePressed(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        if (frame != null) {
            x -= 4;
            y -= 22;
        }

        idleCycles = 0;
        lastMousePressX = x;
        lastMousePressY = y;
        lastMousePressTime = System.currentTimeMillis();
        if (event.isMetaDown()) {
            lastMousePressButton = 2;
            mouseButton = 2;
        } else {
            lastMousePressButton = 1;
            mouseButton = 1;
        }
    }

    public void mouseDragged(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        if (frame != null) {
            x -= 4;
            y -= 22;
        }
        idleCycles = 0;
        mouseX = x;
        mouseY = y;
    }

    public void initApplet(int width, int height) {
        this.width = width;
        this.height = height;
        graphics = getBaseComponent().getGraphics();
        drawArea = new DrawArea(getBaseComponent(), this.width, this.height);
        startThread(this, 1);
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
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void windowDeactivated(WindowEvent windowevent) {
    }

    public void agn(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }

        refresh = true;
        refresh();
    }

    public void destroy() {
        state = -1;

        try {
            Thread.sleep(5000L);
        } catch (Exception exception) {
        }

        if (state == -1) {
            shutdown();
        }
    }

    public void unload() {
    }

    public GameShell() {
    }

    public void update() {
    }

    public void focusLost(FocusEvent focusevent) {
        hasFocus = false;
        for (int i = 0; i < 128; i++) {
            actionKey[i] = 0;
        }
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
            actionKey[c] = 0;
    }

    public void windowClosed(WindowEvent windowevent) {
    }

    public void run() {
        getBaseComponent().addMouseListener(this);
        getBaseComponent().addMouseMotionListener(this);
        getBaseComponent().addKeyListener(this);
        getBaseComponent().addFocusListener(this);
        if (frame != null)
            frame.addWindowListener(this);
        showProgress(0, "Loading...");
        load();

        int opos = 0;
        int ratio = 256;
        int delta = 1;
        int count = 0;
        int intex = 0;

        for (int n = 0; n < 10; n++) {
            otim[n] = System.currentTimeMillis();
        }

        do {
            if (state < 0) {
                break;
            }

            if (state > 0) {
                state--;
                if (state == 0) {
                    shutdown();
                    return;
                }
            }

            int lastRatio = ratio;
            int lastDelta = delta;
            ratio = 300;
            delta = 1;

            long ntime = System.currentTimeMillis();
            if (otim[opos] == 0L) {
                ratio = lastRatio;
                delta = lastDelta;
            } else if (ntime > otim[opos])
                ratio = (int) ((2560L * deltime) / (ntime - otim[opos]));
            if (ratio < 25)
                ratio = 25;
            if (ratio > 256) {
                ratio = 256;
                delta = (int) ((long) deltime - (ntime - otim[opos]) / 10L);
            }
            
            if (delta > deltime) {
                delta = deltime;
            }

            otim[opos] = ntime;
            opos = (opos + 1) % 10;
            if (delta > 1) {
                for (int k2 = 0; k2 < 10; k2++) {
                    if (otim[k2] != 0L) {
                        otim[k2] += delta;
                    }
                }
            }

            if (delta < mindel) {
                delta = mindel;
            }

            try {
                Thread.sleep(delta);
            } catch (InterruptedException interruptedexception) {
                intex++;
            }

            for (; count < 256; count += ratio) {
                mousePressButton = lastMousePressButton;
                mousePressX = lastMousePressX;
                mousePressY = lastMousePressY;
                mousePressTime = lastMousePressTime;
                lastMousePressButton = 0;
                update();
                lastProcessedKey = unprocessedKeyCount;
            }

            count &= 0xff;
            if (deltime > 0) {
                fps = (1000 * ratio) / (deltime * 256);
            }
            
            draw();
            if (debug) {
                System.out.println((new StringBuilder()).append("ntime:").append(ntime));
                for (int i = 0; i < 10; i++) {
                    int o = ((opos - i - 1) + 20) % 10;
                    System.out.println((new StringBuilder()).append("otim").append(o).append(":").append(otim[o]));
                }

                System.out.println((new StringBuilder()).append("fps:").append(fps).append(" ratio:").append(ratio).append(" count:").append(count));
                System.out.println((new StringBuilder()).append("del:").append(delta).append(" deltime:").append(deltime).append(" mindel:").append(mindel));
                System.out.println((new StringBuilder()).append("intex:").append(intex).append(" opos:").append(opos));
                debug = false;
                intex = 0;
            }
        } while (true);

        if (state == -1) {
            shutdown();
        }
    }

    public void focusGained(FocusEvent focusevent) {
        hasFocus = true;
        refresh = true;
        refresh();
    }

    public void windowIconified(WindowEvent windowevent) {
    }

    private int state = 0;
    private final int deltime = 20;
    public int mindel = 1;
    private final long[] otim = new long[10];
    public int fps = 0;
    public boolean debug = false;
    public int width;
    public int height;
    public Graphics graphics;
    public DrawArea drawArea;
    public GameFrame frame;
    public boolean refresh = true;
    public boolean hasFocus = true;
    public int idleCycles = 0;
    public int mouseButton = 0;
    public int mouseX = 0;
    public int mouseY = 0;
    public int lastMousePressButton = 0;
    public int lastMousePressX = 0;
    public int lastMousePressY = 0;
    public long lastMousePressTime = 0L;
    public int mousePressButton = 0;
    public int mousePressX = 0;
    public int mousePressY = 0;
    public long mousePressTime = 0L;
    public int[] actionKey = new int[128];
    private final int[] pressedKeys = new int[128];
    private int lastProcessedKey = 0;
    private int unprocessedKeyCount = 0;
}
