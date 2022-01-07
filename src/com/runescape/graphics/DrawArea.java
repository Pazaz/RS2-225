package com.runescape.graphics;

import java.awt.*;
import java.awt.image.*;

public class DrawArea
        implements ImageProducer, ImageObserver {

    public DrawArea(Component component, int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        colorModel = new DirectColorModel(24, 0xff0000, 0x00ff00, 0x0000ff);
        image = component.createImage(this);
        setPixels();
        component.prepareImage(image, this);
        setPixels();
        component.prepareImage(image, this);
        setPixels();
        component.prepareImage(image, this);
        bind();
    }

    public void bind() {
        Draw2D.prepare(width, pixels, height);
    }

    public void drawImage(int y, Graphics g, int x) {
        setPixels();
        g.drawImage(image, x, y, this);
    }

    public synchronized void addConsumer(ImageConsumer consumer) {
        this.consumer = consumer;
        consumer.setDimensions(width, height);
        consumer.setProperties(null);
        consumer.setColorModel(colorModel);
        consumer.setHints(14);
    }

    public synchronized boolean isConsumer(ImageConsumer consumer) {
        return this.consumer == consumer;
    }

    public synchronized void removeConsumer(ImageConsumer consumer) {
        if (this.consumer == consumer) {
            this.consumer = null;
        }
    }

    public void startProduction(ImageConsumer consumer) {
        addConsumer(consumer);
    }

    public void requestTopDownLeftRightResend(ImageConsumer consumer) {
        System.out.println("TDLR");
    }

    public synchronized void setPixels() {
        if (consumer != null) {
            consumer.setPixels(0, 0, width, height, colorModel, pixels, 0, width);
            consumer.imageComplete(2);
        }
    }

    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1) {
        return true;
    }

    public int[] pixels;
    public int width;
    public int height;
    public ColorModel colorModel;
    public ImageConsumer consumer;
    public Image image;
}
