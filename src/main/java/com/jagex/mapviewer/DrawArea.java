package com.jagex.mapviewer;

import java.awt.*;
import java.awt.image.*;

public final class DrawArea
    implements ImageProducer, ImageObserver
{

    public void acm()
    {
        Draw2D.ajj(ahd, ahe, ahf);
    }

    public DrawArea(int i, int j, Component component)
    {
        ahe = i;
        ahf = j;
        ahd = new int[i * j];
        ahg = new DirectColorModel(32, 0xff0000, 65280, 255);
        ahi = component.createImage(this);
        adh();
        component.prepareImage(ahi, this);
        adh();
        component.prepareImage(ahi, this);
        adh();
        component.prepareImage(ahi, this);
        acm();
    }

    public boolean imageUpdate(Image image, int i, int j, int k, int i1, int j1)
    {
        return true;
    }

    public void requestTopDownLeftRightResend(ImageConsumer imageconsumer)
    {
        System.out.println("TDLR");
    }

    public synchronized boolean isConsumer(ImageConsumer imageconsumer)
    {
        return ahh == imageconsumer;
    }

    public synchronized void removeConsumer(ImageConsumer imageconsumer)
    {
        if(ahh == imageconsumer)
            ahh = null;
    }

    public void ade(Graphics g, int i, int j)
    {
        adh();
        g.drawImage(ahi, i, j, this);
    }

    public void startProduction(ImageConsumer imageconsumer)
    {
        addConsumer(imageconsumer);
    }

    public synchronized void addConsumer(ImageConsumer imageconsumer)
    {
        ahh = imageconsumer;
        imageconsumer.setDimensions(ahe, ahf);
        imageconsumer.setProperties(null);
        imageconsumer.setColorModel(ahg);
        imageconsumer.setHints(14);
    }

    public synchronized void adh()
    {
        if(ahh == null)
        {
            return;
        } else
        {
            ahh.setPixels(0, 0, ahe, ahf, ahg, ahd, 0, ahe);
            ahh.imageComplete(2);
            return;
        }
    }

    public int ahd[];
    public int ahe;
    public int ahf;
    ColorModel ahg;
    ImageConsumer ahh;
    public Image ahi;
}
