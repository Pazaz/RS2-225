package com.runescape;

import java.awt.*;

public class GameFrame extends Frame {

    public GameFrame(int i, int j, GameShell applet_sub1, int k) {
        anInt31 = 8;
        if (j != 35731)
            anInt31 = -475;
        anApplet_Sub1_32 = applet_sub1;
        setTitle("Jagex");
        setResizable(false);
        show();
        toFront();
        resize(k + 8, i + 28);
    }

    public Graphics getGraphics() {
        Graphics g = super.getGraphics();
        g.translate(4, 24);
        return g;
    }

    public void update(Graphics g) {
        anApplet_Sub1_32.update(g);
    }

    public void paint(Graphics g) {
        anApplet_Sub1_32.paint(g);
    }

    public int anInt31;
    public GameShell anApplet_Sub1_32;
}
