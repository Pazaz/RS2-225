// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.awt.*;
import java.awt.image.*;
import java.io.PrintStream;
import sign.signlink;

public class Class31
        implements ImageProducer, ImageObserver {

    public Class31(Component component, int i, int j, int k) {
        anInt512 = 299;
        anInt514 = i;
        anInt515 = k;
        anIntArray513 = new int[i * k];
        aColorModel516 = new DirectColorModel(32, 0xff0000, 65280, 255);
        anImage518 = component.createImage(this);
        method279();
        component.prepareImage(anImage518, this);
        method279();
        component.prepareImage(anImage518, this);
        method279();
        j = 96 / j;
        component.prepareImage(anImage518, this);
        method277((byte) 62);
    }

    public void method277(byte byte0) {
        if (byte0 != 62)
            anInt512 = -283;
        Class38_Sub2_Sub2.method376(anInt514, anIntArray513, -657, anInt515);
    }

    public void method278(int i, Graphics g, int j, int k) {
        if (k != 5193) {
            return;
        } else {
            method279();
            g.drawImage(anImage518, j, i, this);
            return;
        }
    }

    public synchronized void addConsumer(ImageConsumer imageconsumer) {
        anImageConsumer517 = imageconsumer;
        imageconsumer.setDimensions(anInt514, anInt515);
        imageconsumer.setProperties(null);
        imageconsumer.setColorModel(aColorModel516);
        imageconsumer.setHints(14);
    }

    public synchronized boolean isConsumer(ImageConsumer imageconsumer) {
        return anImageConsumer517 == imageconsumer;
    }

    public synchronized void removeConsumer(ImageConsumer imageconsumer) {
        if (anImageConsumer517 == imageconsumer)
            anImageConsumer517 = null;
    }

    public void startProduction(ImageConsumer imageconsumer) {
        addConsumer(imageconsumer);
    }

    public void requestTopDownLeftRightResend(ImageConsumer imageconsumer) {
        System.out.println("TDLR");
    }

    public synchronized void method279() {
        if (anImageConsumer517 == null) {
            return;
        } else {
            anImageConsumer517.setPixels(0, 0, anInt514, anInt515, aColorModel516, anIntArray513, 0, anInt514);
            anImageConsumer517.imageComplete(2);
            return;
        }
    }

    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1) {
        return true;
    }

    public int anInt512;
    public int anIntArray513[];
    public int anInt514;
    public int anInt515;
    public ColorModel aColorModel516;
    public ImageConsumer anImageConsumer517;
    public Image anImage518;
}
