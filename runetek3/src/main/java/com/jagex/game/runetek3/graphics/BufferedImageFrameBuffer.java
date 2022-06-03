package com.jagex.game.runetek3.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

public class BufferedImageFrameBuffer extends FrameBuffer {

    public BufferedImage image;

    public BufferedImageFrameBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
        makeTarget();
    }

    @Override
    public void makeTarget() {
        Draw2D.prepare(this.width, this.pixels, this.height);
    }

    @Override
    public void drawAt(int x, int y, Graphics g) {
        g.drawImage(this.image, x, y, null);
    }

    @Override
    public void drawAt(int x, int y, int w, int h, Graphics g) {
        g.drawImage(this.image, x, y, w, h, null);
    }

    public void save(String path) throws IOException {
        ImageIO.write(image, "png", new File(path));
    }
}