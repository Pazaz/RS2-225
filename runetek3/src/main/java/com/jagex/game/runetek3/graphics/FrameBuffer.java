package com.jagex.game.runetek3.graphics;

import java.awt.*;

public abstract class FrameBuffer {

    public int width;
    public int height;
    public int[] pixels;
    public Component component;
    public Image image;

    public abstract void makeTarget();

    public abstract void drawAt(int x, int y, Graphics g);

    public abstract void drawAt(int x, int y, int w, int h, Graphics g);

}