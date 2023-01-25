package com.jagex.game.runetek3.graphics;

public abstract class FrameBuffer {

	public int width;
	public int height;
	public int[] pixels;

	public abstract void makeTarget();

	public abstract void drawAt(int x, int y);

}
