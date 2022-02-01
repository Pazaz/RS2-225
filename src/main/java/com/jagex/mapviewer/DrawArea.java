package com.jagex.mapviewer;

import java.awt.*;
import java.awt.image.*;

public class DrawArea
    implements ImageProducer, ImageObserver {

    public void bind() {
        Draw2D.prepare(pixels, width, height);
    }

    public DrawArea(int width, int height, Component component) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        colorModel = new DirectColorModel(32, 0xff0000, 0x00ff00, 0x0000ff);
        image = component.createImage(this);
        setPixels();
        component.prepareImage(image, this);
        setPixels();
        component.prepareImage(image, this);
        setPixels();
        component.prepareImage(image, this);
        bind();
    }

    public boolean imageUpdate(Image image, int i, int j, int k, int i1, int j1) {
        return true;
    }

    public void requestTopDownLeftRightResend(ImageConsumer consumer) {
        System.out.println("TDLR");
    }

    public synchronized boolean isConsumer(ImageConsumer consumer) {
        return this.consumer == consumer;
    }

    public synchronized void removeConsumer(ImageConsumer consumer) {
        if (this.consumer == consumer) {
            this.consumer = null;
        }
    }

    public void drawImage(Graphics g, int i, int j) {
        setPixels();
        g.drawImage(image, i, j, this);
    }

    public void startProduction(ImageConsumer consumer) {
        addConsumer(consumer);
    }

    public synchronized void addConsumer(ImageConsumer consumer) {
        this.consumer = consumer;
        consumer.setDimensions(width, height);
        consumer.setProperties(null);
        consumer.setColorModel(colorModel);
        consumer.setHints(14);
    }

    public synchronized void setPixels() {
        if (consumer != null) {
            consumer.setPixels(0, 0, width, height, colorModel, pixels, 0, width);
            consumer.imageComplete(2);
        }
    }

    public int[] pixels;
    public int width;
    public int height;
    ColorModel colorModel;
    ImageConsumer consumer;
    public Image image;
}
