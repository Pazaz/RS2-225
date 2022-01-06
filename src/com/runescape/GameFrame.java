package com.runescape;

import java.awt.*;

public class GameFrame extends Frame {

    public GameFrame(int height, GameShell applet, int width) {
        this.applet = applet;
        setTitle("Jagex");
        setResizable(false);
        setVisible(true);
        toFront();
        setSize(width + 8, height + 28);
    }

    public Graphics getGraphics() {
        Graphics g = super.getGraphics();
        g.translate(4, 24);
        return g;
    }

    public void update(Graphics g) {
        applet.update(g);
    }

    public void paint(Graphics g) {
        applet.paint(g);
    }

    public GameShell applet;
}
