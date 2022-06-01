package com.jagex.game.runetek3.graphics;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!qb")
public class ImageProducerFrameBuffer implements ImageProducer, ImageObserver {

	@OriginalMember(owner = "client!qb", name = "f", descriptor = "Ljava/awt/image/ImageConsumer;")
	private ImageConsumer consumer;

	@OriginalMember(owner = "client!qb", name = "c", descriptor = "I")
	private final int width;

	@OriginalMember(owner = "client!qb", name = "d", descriptor = "I")
	private final int height;

	@OriginalMember(owner = "client!qb", name = "b", descriptor = "[I")
	public final int[] pixels;

	@OriginalMember(owner = "client!qb", name = "e", descriptor = "Ljava/awt/image/ColorModel;")
	private final ColorModel colorModel;

	@OriginalMember(owner = "client!qb", name = "g", descriptor = "Ljava/awt/Image;")
	private final Image image;

	@OriginalMember(owner = "client!qb", name = "<init>", descriptor = "(Ljava/awt/Component;III)V")
	public ImageProducerFrameBuffer(@OriginalArg(0) Component component, @OriginalArg(1) int width, @OriginalArg(3) int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.colorModel = new DirectColorModel(32, 0xff0000, 0xff00, 0xff);
		this.image = component.createImage(this);

		this.setPixels();
		component.prepareImage(this.image, this);

		this.setPixels();
		component.prepareImage(this.image, this);

		this.setPixels();
		component.prepareImage(this.image, this);

		this.makeTarget();
	}

	@OriginalMember(owner = "client!qb", name = "a", descriptor = "(B)V")
	public final void makeTarget() {
		Draw2D.prepare(this.width, this.pixels, this.height);
	}

	@OriginalMember(owner = "client!qb", name = "a", descriptor = "(ILjava/awt/Graphics;II)V")
	public void drawAt(@OriginalArg(2) int x, @OriginalArg(0) int y, @OriginalArg(1) Graphics g) {
		this.setPixels();
		g.drawImage(this.image, x, y, this);
	}

	public void drawAt(@OriginalArg(2) int x, @OriginalArg(0) int y, int width, int height, @OriginalArg(1) Graphics g) {
		this.setPixels();
		g.drawImage(this.image, x, y, width, height, this);
	}

	@OriginalMember(owner = "client!qb", name = "addConsumer", descriptor = "(Ljava/awt/image/ImageConsumer;)V")
	@Override
	public synchronized void addConsumer(@OriginalArg(0) ImageConsumer consumer) {
		this.consumer = consumer;
		consumer.setDimensions(this.width, this.height);
		consumer.setProperties(null);
		consumer.setColorModel(this.colorModel);
		consumer.setHints(14);
	}

	@OriginalMember(owner = "client!qb", name = "isConsumer", descriptor = "(Ljava/awt/image/ImageConsumer;)Z")
	@Override
	public synchronized boolean isConsumer(@OriginalArg(0) ImageConsumer consumer) {
		return this.consumer == consumer;
	}

	@OriginalMember(owner = "client!qb", name = "removeConsumer", descriptor = "(Ljava/awt/image/ImageConsumer;)V")
	@Override
	public synchronized void removeConsumer(@OriginalArg(0) ImageConsumer consumer) {
		if (this.consumer == consumer) {
			this.consumer = null;
		}
	}

	@OriginalMember(owner = "client!qb", name = "startProduction", descriptor = "(Ljava/awt/image/ImageConsumer;)V")
	@Override
	public void startProduction(@OriginalArg(0) ImageConsumer consumer) {
		this.addConsumer(consumer);
	}

	@OriginalMember(owner = "client!qb", name = "requestTopDownLeftRightResend", descriptor = "(Ljava/awt/image/ImageConsumer;)V")
	@Override
	public void requestTopDownLeftRightResend(@OriginalArg(0) ImageConsumer consumer) {
		System.out.println("TDLR");
	}

	@OriginalMember(owner = "client!qb", name = "a", descriptor = "()V")
	private synchronized void setPixels() {
		if (this.consumer != null) {
			this.consumer.setPixels(0, 0, this.width, this.height, this.colorModel, this.pixels, 0, this.width);
			this.consumer.imageComplete(2);
		}
	}

	@OriginalMember(owner = "client!qb", name = "imageUpdate", descriptor = "(Ljava/awt/Image;IIIII)Z")
	@Override
	public boolean imageUpdate(@OriginalArg(0) Image image, @OriginalArg(1) int infoflags, @OriginalArg(2) int x, @OriginalArg(3) int y, @OriginalArg(4) int width, @OriginalArg(5) int height) {
		return true;
	}

	@OriginalMember(owner = "client!qb", name = "a", descriptor = "I")
	private final int flowObfuscator1 = 299;

}
