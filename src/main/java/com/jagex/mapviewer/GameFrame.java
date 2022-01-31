package com.jagex.mapviewer;

import java.awt.Frame;
import java.awt.Graphics;

public final class GameFrame extends Frame
{

    public GameFrame(GameShell a1, int i, int j)
    {
        ahj = a1;
        setTitle("Jagex");
        setResizable(false);
        show();
        toFront();
        resize(i + 8, j + 28);
    }

    public final void adj(Graphics g)
    {
        ahj.agn(g);
    }

    public final void adk(Graphics g)
    {
        ahj.afi(g);
    }

    public Graphics getGraphics()
    {
        Graphics g = super.getGraphics();
        g.translate(4, 24);
        return g;
    }

    GameShell ahj;
}
