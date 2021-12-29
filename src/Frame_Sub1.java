// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.awt.*;
import sign.signlink;

public class Frame_Sub1 extends Frame {

    public Frame_Sub1(int i, int j, Applet_Sub1 applet_sub1, int k) {
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
    public Applet_Sub1 anApplet_Sub1_32;
}
