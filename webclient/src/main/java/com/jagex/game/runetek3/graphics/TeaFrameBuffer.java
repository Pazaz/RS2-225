package com.jagex.game.runetek3.graphics;

import org.teavm.jso.canvas.ImageData;
import org.teavm.jso.typedarrays.Uint8ClampedArray;
import rs2.webclient.Game;

public class TeaFrameBuffer {

	public int width;
	public int height;
	public int[] pixels;

	private final ImageData imageData;
	private final Uint8ClampedArray rgbPixels;

	public TeaFrameBuffer(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.imageData = Game.instance.context.createImageData(width, height);
		this.rgbPixels = this.imageData.getData();
		makeTarget();
	}

	public void makeTarget() {
		Draw2D.prepare(this.width, this.pixels, this.height);
	}

	public void drawAt(int y, int x) {
		for (int i = 0; i < width * height * 4; i += 4) {
			int pixel = this.pixels[i / 4];
			this.rgbPixels.set(i, (pixel >> 16) & 255);
			this.rgbPixels.set(i + 1, (pixel >> 8) & 255);
			this.rgbPixels.set(i + 2, pixel & 255);
			this.rgbPixels.set(i + 3, 255);
		}

		Game.instance.context.putImageData(imageData, x, y);
	}

}
