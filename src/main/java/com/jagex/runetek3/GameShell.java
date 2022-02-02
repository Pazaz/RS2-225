package com.jagex.runetek3;

import com.jagex.runetek3.graphics.DrawArea;
import com.jagex.runetek3.util.InputTracking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameShell extends JApplet
    implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

    public void initFrame(int height, int width, String title) {
        gameWidth = width;
        gameHeight = height;
        setSize(gameWidth, gameHeight);
        setPreferredSize(getSize());
        frame = new GameFrame(this, title);
        graphics = getBaseComponent().getGraphics();
        drawArea = new DrawArea(getBaseComponent(), gameWidth, gameHeight);
        startThread(this, 1);
        requestFocus();
    }

    public void initApplet(int height, int width) {
        gameWidth = width;
        gameHeight = height;
        graphics = getBaseComponent().getGraphics();
        drawArea = new DrawArea(getBaseComponent(), gameWidth, gameHeight);
        startThread(this, 1);
    }

    public void run() {
        getBaseComponent().addMouseListener(this);
        getBaseComponent().addMouseMotionListener(this);
        getBaseComponent().addKeyListener(this);
        getBaseComponent().addFocusListener(this);
        if (frame != null) {
            frame.addWindowListener(this);
        }
        showProgress("Loading...", 0);
        load();

        int opos = 0;
        int ratio = 256;
        int delta = 1;
        int count = 0;
        for (int n = 0; n < 10; n++) {
            otim[n] = System.currentTimeMillis();
        }

        while (state >= 0) {
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
            } else if (ntime > otim[opos]) {
                ratio = (int) ((2560L * deltime) / (ntime - otim[opos]));
            }

            if (ratio < 25) {
                ratio = 25;
            } else if (ratio > 256) {
                ratio = 256;
                delta = (int) ((long) deltime - (ntime - otim[opos]) / 10L);
            }

            otim[opos] = ntime;
            opos = (opos + 1) % 10;
            if (delta > 1) {
                for (int n = 0; n < 10; n++) {
                    if (otim[n] != 0L) {
                        otim[n] += delta;
                    }
                }
            }

            if (delta < mindel) {
                delta = mindel;
            }

            try {
                Thread.sleep(delta);
            } catch (InterruptedException _ex) {
            }

            for (; count < 256; count += ratio) {
                update();
                mouseButton = 0;
                lastProcessedKey = unprocessedKeyCount;
            }

            count &= 0xff;
            if (deltime > 0) {
                fps = (1000 * ratio) / (deltime * 256);
            }

            draw();
        }

        if (state == -1) {
            shutdown();
        }
    }

    public void shutdown() {
        state = -2;
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

    public void setLoopRate(int rate) {
        deltime = 1000 / rate;
    }

    public void start() {
        if (state >= 0) {
            state = 0;
        }
    }

    public void stop() {
        if (state >= 0) {
            state = 4000 / deltime;
        }
    }

    public void destroy() {
        state = -1;

        try {
            Thread.sleep(5000L);
        } catch (Exception _ex) {
        }

        if (state == -1) {
            shutdown();
        }
    }

    public void update(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }

        refresh = true;
        refresh();
    }

    public void paint(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }

        refresh = true;
        refresh();
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        idleCycles = 0;
        clickX = x;
        clickY = y;

        if (SwingUtilities.isRightMouseButton(e)) {
            mouseButton = 2;
            dragButton = 2;
        } else {
            mouseButton = 1;
            dragButton = 1;
        }

        if (InputTracking.enabled) {
            InputTracking.mousePressed(x, e.isMetaDown() ? 1 : 0, y);
        }
    }

    public void mouseReleased(MouseEvent e) {
        idleCycles = 0;
        dragButton = 0;

        if (InputTracking.enabled) {
            InputTracking.mouseReleased(e.isMetaDown() ? 1 : 0);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        if (InputTracking.enabled) {
            InputTracking.mouseEntered();
        }
    }

    public void mouseExited(MouseEvent e) {
        if (InputTracking.enabled) {
            InputTracking.mouseExited();
        }
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        idleCycles = 0;
        mouseX = x;
        mouseY = y;

        if (InputTracking.enabled) {
            InputTracking.mouseMoved(y, x);
        }
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        idleCycles = 0;
        mouseX = x;
        mouseY = y;

        if (InputTracking.enabled) {
            InputTracking.mouseMoved(y, x);
        }
    }

    public void keyPressed(KeyEvent e) {
        idleCycles = 0;

        int code = e.getKeyCode();
        int ch = e.getKeyChar();

        if (ch < 30) {
            ch = 0;
        }

        if (code == 37) {
            ch = 1;
        } else if (code == 39) {
            ch = 2;
        } else if (code == 38) {
            ch = 3;
        } else if (code == 40) {
            ch = 4;
        } else if (code == 17) {
            ch = 5;
        } else if (code == 8) {
            ch = 8;
        } else if (code == 127) {
            ch = 8;
        } else if (code == 9) {
            ch = 9;
        } else if (code == 10) {
            ch = 10;
        } else if (code >= 112 && code <= 123) {
            ch = (1008 + code) - 112;
        } else if (code == 36) {
            ch = 1000;
        } else if (code == 35) {
            ch = 1001;
        } else if (code == 33) {
            ch = 1002;
        } else if (code == 34) {
            ch = 1003;
        }

        if (ch > 0 && ch < 128) {
            keyDown[ch] = 1;
        }

        if (ch > 4) {
            pressedKeys[unprocessedKeyCount] = ch;
            unprocessedKeyCount = unprocessedKeyCount + 1 & 0x7f;
        }

        if (InputTracking.enabled) {
            InputTracking.keyPressed(ch);
        }
    }

    public void keyReleased(KeyEvent keyevent) {
        idleCycles = 0;

        int code = keyevent.getKeyCode();
        char ch = keyevent.getKeyChar();

        if (ch < 30) {
            ch = 0;
        }

        if (code == 37) {
            ch = 1;
        } else if (code == 39) {
            ch = 2;
        } else if (code == 38) {
            ch = 3;
        } else if (code == 40) {
            ch = 4;
        } else if (code == 17) {
            ch = 5;
        } else if (code == 8) {
            ch = 8;
        } else if (code == 127) {
            ch = 8;
        } else if (code == 9) {
            ch = 9;
        } else if (code == 10) {
            ch = 10;
        }

        if (ch > 0 && ch < 128) {
            keyDown[ch] = 0;
        }

        if (InputTracking.enabled) {
            InputTracking.keyReleased(ch);
        }
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void focusGained(FocusEvent focusevent) {
        refresh = true;
        refresh();

        if (InputTracking.enabled) {
            InputTracking.focusGained();
        }
    }

    public void focusLost(FocusEvent focusevent) {
        if (InputTracking.enabled) {
            InputTracking.focusLost();
        }
    }

    public int pollKey() {
        int key = -1;
        if (unprocessedKeyCount != lastProcessedKey) {
            key = pressedKeys[lastProcessedKey];
            lastProcessedKey = lastProcessedKey + 1 & 0x7f;
        }
        return key;
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        destroy();
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
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
        return this;
    }

    public void startThread(Runnable runnable, int i) {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(i);
    }

    public void showProgress(String str, int progress) {
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

        Font helvetica = new Font("Helvetica", Font.BOLD, 13);
        FontMetrics metrics = getBaseComponent().getFontMetrics(helvetica);

        if (refresh) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, gameWidth, gameHeight);
            refresh = false;
        }

        int y = gameHeight / 2 - 18;

        graphics.setColor(new Color(140, 17, 17));
        graphics.drawRect(gameWidth / 2 - 152, y, 304, 34);
        graphics.fillRect(gameWidth / 2 - 150, y + 2, progress * 3, 30);

        graphics.setColor(Color.black);
        graphics.fillRect((gameWidth / 2 - 150) + progress * 3, y + 2, 300 - progress * 3, 30);

        graphics.setFont(helvetica);
        graphics.setColor(Color.white);
        graphics.drawString(str, (gameWidth - metrics.stringWidth(str)) / 2, y + 22);
    }

    public GameShell() {
    }

    public int state;
    public int deltime = 20;
    public int mindel = 1;
    public long[] otim = new long[10];
    public int fps;
    public int gameWidth;
    public int gameHeight;
    public Graphics graphics;
    public DrawArea drawArea;
    public GameFrame frame;
    public boolean refresh = true;
    public int idleCycles;
    public int dragButton;
    public int mouseX;
    public int mouseY;
    public int mouseButton;
    public int clickX;
    public int clickY;
    public int[] keyDown = new int[128];
    public int[] pressedKeys = new int[128];
    public int lastProcessedKey;
    public int unprocessedKeyCount;
}
