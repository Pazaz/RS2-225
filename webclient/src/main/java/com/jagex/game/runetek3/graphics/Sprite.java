package com.jagex.game.runetek3.graphics;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.browser.Window;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.canvas.ImageData;
import org.teavm.jso.dom.html.HTMLImageElement;
import org.teavm.jso.typedarrays.Uint8Array;
import org.teavm.jso.typedarrays.Uint8ClampedArray;
import rs2.client.GlobalConfig;
import rs2.webclient.Game;

@OriginalClass("client!hb")
public class Sprite extends Draw2D {

	@OriginalMember(owner = "client!hb", name = "A", descriptor = "[I")
	public int[] pixels;

	@OriginalMember(owner = "client!hb", name = "F", descriptor = "I")
	public int clipWidth;

	@OriginalMember(owner = "client!hb", name = "B", descriptor = "I")
	public int spriteWidth;

	@OriginalMember(owner = "client!hb", name = "G", descriptor = "I")
	public int clipHeight;

	@OriginalMember(owner = "client!hb", name = "C", descriptor = "I")
	public int spriteHeight;

	@OriginalMember(owner = "client!hb", name = "E", descriptor = "I")
	private int clipY;

	@OriginalMember(owner = "client!hb", name = "D", descriptor = "I")
	private int clipX;

	@OriginalMember(owner = "client!hb", name = "<init>", descriptor = "(II)V")
	public Sprite(@OriginalArg(0) int w, @OriginalArg(1) int h) {
		this.pixels = new int[w * h];
		this.spriteWidth = this.clipWidth = w;
		this.spriteHeight = this.clipHeight = h;
		this.clipX = this.clipY = 0;
	}

	@JSBody(params = {"arr", "type"}, script = "return new Blob([arr], {type:type});")
	public static native JSObject blobify(Uint8Array arr, String type);

	@JSBody(params = {"blob"}, script = "return (window.URL || window.webkitURL).createObjectURL(blob);")
	public static native String createObjectUrl(JSObject blob);

	@JSBody(params = {"url"}, script = "return (window.URL || window.webkitURL).revokeObjectURL(url);")
	public static native void revokeObjectURL(String url);

	@Async
	private static native HTMLImageElement load(String url);
	private static void load(String url, AsyncCallback<HTMLImageElement> callback) {
		HTMLImageElement img = Window.current().getDocument().createElement("img").cast();
		img.addEventListener("load", evt -> callback.complete(img));
		img.setSrc(url);
	}

	public Sprite(byte[] src) {
		try {
			Uint8Array arr = Uint8Array.create(src.length);
			arr.set(src);
			JSObject blob = blobify(arr, "image/jpeg");
			String objUrl = createObjectUrl(blob);
			HTMLImageElement img = load(objUrl);
			CanvasRenderingContext2D ctx = Game.instance.context.cast();
			ctx.drawImage(img, 0, 0);
			ImageData rawData = ctx.getImageData(0, 0, img.getWidth(), img.getHeight());
			revokeObjectURL(objUrl);
			Uint8ClampedArray raw = rawData.getData();

			this.spriteWidth = img.getWidth();
			this.spriteHeight = img.getHeight();
			this.clipWidth = spriteWidth;
			this.clipHeight = spriteHeight;
			this.clipX = 0;
			this.clipY = 0;
			this.pixels = new int[spriteWidth * spriteHeight];
			for (int i = 0, off = 0; i < img.getWidth() * img.getHeight() * 4; i += 4) {
				int r = raw.get(i);
				int g = raw.get(i + 1);
				int b = raw.get(i + 2);
				this.pixels[off++] = (r << 16) | (g << 8) | b;
			}
		} catch (@Pc(81) Exception ignored) {
			System.out.println("Error converting jpg");
		}
	}

	@OriginalMember(owner = "client!hb", name = "<init>", descriptor = "(Lclient!ub;Ljava/lang/String;I)V")
	public Sprite(@OriginalArg(0) FileArchive archive, @OriginalArg(1) String name, @OriginalArg(2) int id) {
		@Pc(32) Buffer dat = new Buffer(archive.read(name + ".dat", null));
		@Pc(42) Buffer index = new Buffer(archive.read("index.dat", null));

		index.pos = dat.g2();
		this.clipWidth = index.g2();
		this.clipHeight = index.g2();

		@Pc(57) int count = index.g1();
		@Pc(60) int[] palette = new int[count];
		for (@Pc(62) int i = 0; i < count - 1; i++) {
			palette[i + 1] = index.g3();

			if (palette[i + 1] == 0) {
				palette[i + 1] = 1;
			}
		}

		for (@Pc(91) int i = 0; i < id; i++) {
			index.pos += 2;
			dat.pos += index.g2() * index.g2();
			index.pos++;
		}

		this.clipX = index.g1();
		this.clipY = index.g1();
		this.spriteWidth = index.g2();
		this.spriteHeight = index.g2();

		@Pc(138) int order = index.g1();
		@Pc(144) int len = this.spriteWidth * this.spriteHeight;
		this.pixels = new int[len];

		if (order == 0) {
			for (@Pc(152) int i = 0; i < len; i++) {
				this.pixels[i] = palette[dat.g1()];
			}
		} else if (order == 1) {
			for (int x = 0; x < this.spriteWidth; x++) {
				for (@Pc(176) int y = 0; y < this.spriteHeight; y++) {
					this.pixels[x + y * this.spriteWidth] = palette[dat.g1()];
				}
			}
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(B)V")
	public void prepare() {
		Draw2D.prepare(this.spriteWidth, this.pixels, this.spriteHeight);
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(IIIZ)V")
	public void translate(@OriginalArg(0) int r, @OriginalArg(1) int g, @OriginalArg(2) int b) {
		for (@Pc(3) int i = 0; i < this.pixels.length; i++) {
			@Pc(10) int color = this.pixels[i];
			if (color != 0) {
				@Pc(18) int red = color >> 16 & 0xFF;
				red += r;
				if (red < 1) {
					red = 1;
				} else if (red > 255) {
					red = 255;
				}

				@Pc(40) int green = color >> 8 & 0xFF;
				green += g;
				if (green < 1) {
					green = 1;
				} else if (green > 255) {
					green = 255;
				}

				@Pc(60) int blue = color & 0xFF;
				blue += b;
				if (blue < 1) {
					blue = 1;
				} else if (blue > 255) {
					blue = 255;
				}

				this.pixels[i] = (red << 16) + (green << 8) + blue;
			}
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(III)V")
	public void drawOpaque(@OriginalArg(1) int x, @OriginalArg(2) int y) {
		x += this.clipX;
		y += this.clipY;

		@Pc(15) int dstOff = x + y * Draw2D.width;
		@Pc(17) int srcOff = 0;
		@Pc(20) int h = this.spriteHeight;
		@Pc(23) int w = this.spriteWidth;
		@Pc(27) int dstStep = Draw2D.width - w;
		@Pc(29) int srcStep = 0;

		@Pc(36) int trim;
		if (y < Draw2D.top) {
			trim = Draw2D.top - y;
			h -= trim;
			y = Draw2D.top;
			srcOff += trim * w;
			dstOff += trim * Draw2D.width;
		}

		if (y + h > Draw2D.bottom) {
			h -= y + h - Draw2D.bottom;
		}

		if (x < Draw2D.left) {
			trim = Draw2D.left - x;
			w -= trim;
			x = Draw2D.left;
			srcOff += trim;
			dstOff += trim;
			srcStep += trim;
			dstStep += trim;
		}

		if (x + w > Draw2D.right) {
			trim = x + w - Draw2D.right;
			w -= trim;
			srcStep += trim;
			dstStep += trim;
		}

		if (w > 0 && h > 0) {
			this.drawOpaquePixels(this.pixels, dstStep, h, srcOff, srcStep, dstOff, w, Draw2D.data);
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(I[IIIIIII[I)V")
	private void drawOpaquePixels(@OriginalArg(1) int[] dst, @OriginalArg(2) int srcStep, @OriginalArg(3) int h, @OriginalArg(4) int dstOff, @OriginalArg(5) int dstStep, @OriginalArg(6) int srcOff, @OriginalArg(7) int w, @OriginalArg(8) int[] src) {
		@Pc(6) int quarterW = -(w >> 2);
		w = -(w & 0x3);

		for (@Pc(14) int y = -h; y < 0; y++) {
			for (@Pc(18) int x = quarterW; x < 0; x++) {
				src[srcOff++] = dst[dstOff++];
				src[srcOff++] = dst[dstOff++];
				src[srcOff++] = dst[dstOff++];
				src[srcOff++] = dst[dstOff++];
			}

			for (@Pc(57) int x = w; x < 0; x++) {
				src[srcOff++] = dst[dstOff++];
			}

			srcOff += srcStep;
			dstOff += dstStep;
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(IIZ)V")
	public void draw(@OriginalArg(0) int y, @OriginalArg(1) int x) {
		x += this.clipX;
		y += this.clipY;

		@Pc(20) int dstOff = x + y * Draw2D.width;
		@Pc(22) int srcOff = 0;
		@Pc(25) int h = this.spriteHeight;
		@Pc(28) int w = this.spriteWidth;
		@Pc(32) int dstStep = Draw2D.width - w;
		@Pc(34) int srcStep = 0;

		@Pc(41) int trim;
		if (y < Draw2D.top) {
			trim = Draw2D.top - y;
			h -= trim;
			y = Draw2D.top;
			srcOff += trim * w;
			dstOff += trim * Draw2D.width;
		}

		if (y + h > Draw2D.bottom) {
			h -= y + h - Draw2D.bottom;
		}

		if (x < Draw2D.left) {
			trim = Draw2D.left - x;
			w -= trim;
			x = Draw2D.left;
			srcOff += trim;
			dstOff += trim;
			srcStep += trim;
			dstStep += trim;
		}

		if (x + w > Draw2D.right) {
			trim = x + w - Draw2D.right;
			w -= trim;
			srcStep += trim;
			dstStep += trim;
		}

		if (w > 0 && h > 0) {
			this.drawPixels(Draw2D.data, this.pixels, srcOff, dstOff, w, h, dstStep, srcStep);
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "([I[IIIIIIII)V")
	private void drawPixels(@OriginalArg(0) int[] dst, @OriginalArg(1) int[] src, @OriginalArg(3) int srcOff, @OriginalArg(4) int dstOff, @OriginalArg(5) int w, @OriginalArg(6) int h, @OriginalArg(7) int dstStep, @OriginalArg(8) int srcStep) {
		@Pc(6) int quarterW = -(w >> 2);
		w = -(w & 0x3);

		for (@Pc(14) int y = -h; y < 0; y++) {
			@Pc(25) int color;
			for (@Pc(18) int x = quarterW; x < 0; x++) {
				color = src[srcOff++];
				if (color == 0) {
					dstOff++;
				} else {
					dst[dstOff++] = color;
				}

				color = src[srcOff++];
				if (color == 0) {
					dstOff++;
				} else {
					dst[dstOff++] = color;
				}

				color = src[srcOff++];
				if (color == 0) {
					dstOff++;
				} else {
					dst[dstOff++] = color;
				}

				color = src[srcOff++];
				if (color == 0) {
					dstOff++;
				} else {
					dst[dstOff++] = color;
				}
			}

			for (@Pc(85) int x = w; x < 0; x++) {
				color = src[srcOff++];
				if (color == 0) {
					dstOff++;
				} else {
					dst[dstOff++] = color;
				}
			}

			dstOff += dstStep;
			srcOff += srcStep;
		}
	}

	// TODO: some local variables left
	@OriginalMember(owner = "client!hb", name = "d", descriptor = "(IIIII)V")
	public void crop(@OriginalArg(0) int bottom, @OriginalArg(1) int top, @OriginalArg(2) int right, @OriginalArg(4) int left) {
		try {
			@Pc(6) int w = this.spriteWidth;
			@Pc(9) int h = this.spriteHeight;
			@Pc(11) int local11 = 0;
			@Pc(13) int local13 = 0;
			@Pc(19) int local19 = (w << 16) / right;
			@Pc(25) int local25 = (h << 16) / bottom;

			@Pc(28) int cropW = this.clipWidth;
			@Pc(31) int cropH = this.clipHeight;
			@Pc(37) int local37 = (cropW << 16) / right;
			@Pc(43) int local43 = (cropH << 16) / bottom;

			left += (this.clipX * right + cropW - 1) / cropW;
			top += (this.clipY * bottom + cropH - 1) / cropH;

			if (this.clipX * right % cropW != 0) {
				local11 = (cropW - this.clipX * right % cropW << 16) / right;
			}

			if (this.clipY * bottom % cropH != 0) {
				local13 = (cropH - this.clipY * bottom % cropH << 16) / bottom;
			}

			right = right * (this.spriteWidth - (local11 >> 16)) / cropW;
			bottom = bottom * (this.spriteHeight - (local13 >> 16)) / cropH;
			@Pc(137) int dstOff = left + top * Draw2D.width;

			@Pc(141) int dstStep = Draw2D.width - right;
			@Pc(148) int trim;
			if (top < Draw2D.top) {
				trim = Draw2D.top - top;
				bottom -= trim;
				top = 0;
				dstOff += trim * Draw2D.width;
				local13 += local43 * trim;
			}

			if (top + bottom > Draw2D.bottom) {
				bottom -= top + bottom - Draw2D.bottom;
			}

			if (left < Draw2D.left) {
				trim = Draw2D.left - left;
				right -= trim;
				left = 0;
				dstOff += trim;
				local11 += local37 * trim;
				dstStep += trim;
			}

			if (left + right > Draw2D.right) {
				trim = left + right - Draw2D.right;
				right -= trim;
				dstStep += trim;
			}

			this.plotScale(local11, local37, Draw2D.data, local43, local13, this.pixels, dstStep, dstOff, bottom, w, right);
		} catch (@Pc(243) Exception ignored) {
			System.out.println("error in sprite clipping routine");
		}
	}

	// TODO: some parameters/local variables left
	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(II[IIIII[IIIIII)V")
	private void plotScale(@OriginalArg(0) int srcOff, @OriginalArg(1) int srcStep, @OriginalArg(2) int[] dst, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int[] src, @OriginalArg(8) int dstStep, @OriginalArg(9) int dstOff, @OriginalArg(10) int h, @OriginalArg(11) int arg11, @OriginalArg(12) int w) {
		try {
			@Pc(5) int startOff = srcOff;
			for (@Pc(15) int y = -h; y < 0; y++) {
				@Pc(23) int local23 = (arg5 >> 16) * arg11;
				for (@Pc(26) int x = -w; x < 0; x++) {
					@Pc(36) int color = src[(srcOff >> 16) + local23];
					if (color == 0) {
						dstOff++;
					} else {
						dst[dstOff++] = color;
					}
					srcOff += srcStep;
				}

				arg5 += arg4;
				srcOff = startOff;
				dstOff += dstStep;
			}
		} catch (@Pc(68) Exception ignored) {
			System.out.println("error in plot_scale");
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(IIIB)V")
	public void drawAlpha(@OriginalArg(0) int alpha, @OriginalArg(1) int x, @OriginalArg(2) int y) {
		x += this.clipX;
		y += this.clipY;

		@Pc(4) int dstOff = x + y * Draw2D.width;
		@Pc(27) int srcOff = 0;
		@Pc(30) int h = this.spriteHeight;
		@Pc(33) int w = this.spriteWidth;
		@Pc(37) int dstStep = Draw2D.width - w;
		@Pc(39) int srcStep = 0;

		@Pc(46) int trim;
		if (y < Draw2D.top) {
			trim = Draw2D.top - y;
			h -= trim;
			y = Draw2D.top;
			srcOff += trim * w;
			dstOff += trim * Draw2D.width;
		}

		if (y + h > Draw2D.bottom) {
			h -= y + h - Draw2D.bottom;
		}

		if (x < Draw2D.left) {
			trim = Draw2D.left - x;
			w -= trim;
			x = Draw2D.left;
			srcOff += trim;
			dstOff += trim;
			srcStep += trim;
			dstStep += trim;
		}

		if (x + w > Draw2D.right) {
			trim = x + w - Draw2D.right;
			w -= trim;
			srcStep += trim;
			dstStep += trim;
		}

		if (w > 0 && h > 0) {
			this.drawAlphaPixels(dstOff, this.pixels, alpha, h, Draw2D.data, srcOff, w, dstStep, srcStep);
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(II[III[IIBIII)V")
	private void drawAlphaPixels(@OriginalArg(0) int dstOff, @OriginalArg(2) int[] src, @OriginalArg(3) int alpha, @OriginalArg(4) int h, @OriginalArg(5) int[] dst, @OriginalArg(6) int srcOff, @OriginalArg(8) int w, @OriginalArg(9) int dstStep, @OriginalArg(10) int srcStep) {
		@Pc(5) int invAlpha = 256 - alpha;
		for (@Pc(19) int y = -h; y < 0; y++) {
			for (@Pc(24) int x = -w; x < 0; x++) {
				@Pc(31) int srcColor = src[srcOff++];
				if (srcColor != 0) {
					@Pc(37) int dstColor = dst[dstOff];
					dst[dstOff++] = ((srcColor & 0xFF00FF) * alpha + (dstColor & 0xFF00FF) * invAlpha & 0xFF00FF00) + ((srcColor & 0xFF00) * alpha + (dstColor & 0xFF00) * invAlpha & 0xFF0000) >> 8;
				} else {
					dstOff++;
				}
			}

			dstOff += dstStep;
			srcOff += srcStep;
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(II[IIIIIIIZ[I)V")
	public void drawRotatedMasked(@OriginalArg(0) int theta, @OriginalArg(1) int w, @OriginalArg(2) int[] lineStart, @OriginalArg(3) int h, @OriginalArg(4) int anchorY, @OriginalArg(5) int zoom, @OriginalArg(6) int anchorX, @OriginalArg(7) int x, @OriginalArg(8) int y, @OriginalArg(10) int[] lineWidth) {
		try {
			@Pc(16) int centerX = -w / 2;
			@Pc(21) int centerY = -h / 2;

			@Pc(30) int sin = (int) (Math.sin((double) theta / 326.11D) * 65536.0D);
			@Pc(39) int cos = (int) (Math.cos((double) theta / 326.11D) * 65536.0D);
			sin = sin * zoom >> 8;
			cos = cos * zoom >> 8;

			@Pc(63) int originX = (anchorX << 16) + centerY * sin + centerX * cos;
			@Pc(75) int originY = (anchorY << 16) + (centerY * cos - centerX * sin);
			@Pc(81) int origin = x + y * Draw2D.width;

			for (y = 0; y < h; y++) {
				@Pc(89) int start = lineStart[y];
				@Pc(93) int dstOff = origin + start;
				@Pc(99) int dstX = originX + cos * start;
				@Pc(105) int dstY = originY - sin * start;

				for (x = -lineWidth[y]; x < 0; x++) {
					if (GlobalConfig.MINIMAP_BILINEAR_FILTERING) {
						int x1 = dstX >> 16;
						int y1 = dstY >> 16;
						int x2 = x1 + 1;
						int y2 = y1 + 1;
						int sampleColor1 = pixels[x1 + y1 * this.spriteWidth];
						int sampleColor2 = pixels[x2 + y1 * this.spriteWidth];
						int sampleColor3 = pixels[x1 + y2 * this.spriteWidth];
						int sampleColor4 = pixels[x2 + y2 * this.spriteWidth];
						int x1Distance = (dstX >> 8) - (x1 << 8);
						int y1Distance = (dstY >> 8) - (y1 << 8);
						int x2Distance = (x2 << 8) - (dstX >> 8);
						int y2Distance = (y2 << 8) - (dstY >> 8);
						int sampleAlpha1 = x2Distance * y2Distance;
						int sampleAlpha2 = x1Distance * y2Distance;
						int sampleAlpha3 = x2Distance * y1Distance;
						int sampleAlpha4 = x1Distance * y1Distance;
						int red = (sampleColor1 >> 16 & 0xff) * sampleAlpha1 + (sampleColor2 >> 16 & 0xff) * sampleAlpha2 + (sampleColor3 >> 16 & 0xff) * sampleAlpha3 + (sampleColor4 >> 16 & 0xff) * sampleAlpha4 & 0xff0000;
						int green = (sampleColor1 >> 8 & 0xff) * sampleAlpha1 + (sampleColor2 >> 8 & 0xff) * sampleAlpha2 + (sampleColor3 >> 8 & 0xff) * sampleAlpha3 + (sampleColor4 >> 8 & 0xff) * sampleAlpha4 >> 8 & 0xff00;
						int blue = (sampleColor1 & 0xff) * sampleAlpha1 + (sampleColor2 & 0xff) * sampleAlpha2 + (sampleColor3 & 0xff) * sampleAlpha3 + (sampleColor4 & 0xff) * sampleAlpha4 >> 16;
						Draw2D.data[dstOff++] = red | green | blue;
					} else {
						Draw2D.data[dstOff++] = this.pixels[(dstX >> 16) + (dstY >> 16) * this.spriteWidth];
					}

					dstX += cos;
					dstY -= sin;
				}

				originX += sin;
				originY += cos;
				origin += Draw2D.width;
			}
		} catch (@Pc(158) Exception ignored) {
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(Lclient!ib;IIB)V")
	public void drawMasked(@OriginalArg(0) IndexedSprite mask, @OriginalArg(1) int y, @OriginalArg(2) int x) {
		x += this.clipX;
		y += this.clipY;

		@Pc(15) int dstOff = x + y * Draw2D.width;
		@Pc(17) int srcOff = 0;
		@Pc(24) int h = this.spriteHeight;
		@Pc(27) int w = this.spriteWidth;
		@Pc(31) int dstStep = Draw2D.width - w;
		@Pc(33) int srcStep = 0;

		@Pc(40) int trim;
		if (y < Draw2D.top) {
			trim = Draw2D.top - y;
			h -= trim;
			y = Draw2D.top;
			srcOff += trim * w;
			dstOff += trim * Draw2D.width;
		}

		if (y + h > Draw2D.bottom) {
			h -= y + h - Draw2D.bottom;
		}

		if (x < Draw2D.left) {
			trim = Draw2D.left - x;
			w -= trim;
			x = Draw2D.left;
			srcOff += trim;
			dstOff += trim;
			srcStep += trim;
			dstStep += trim;
		}

		if (x + w > Draw2D.right) {
			trim = x + w - Draw2D.right;
			w -= trim;
			srcStep += trim;
			dstStep += trim;
		}

		if (w > 0 && h > 0) {
			this.drawMaskedPixels(w, srcStep, h, srcOff, Draw2D.data, this.pixels, dstOff, mask.pixels, dstStep);
		}
	}

	@OriginalMember(owner = "client!hb", name = "a", descriptor = "(IIIIII[I[II[BI)V")
	private void drawMaskedPixels(@OriginalArg(0) int w, @OriginalArg(1) int srcStep, @OriginalArg(4) int h, @OriginalArg(5) int srcOff, @OriginalArg(6) int[] dst, @OriginalArg(7) int[] src, @OriginalArg(8) int dstOff, @OriginalArg(9) byte[] mask, @OriginalArg(10) int dstStep) {
		@Pc(9) int quarterW = -(w >> 2);
		w = -(w & 0x3);

		for (@Pc(17) int y = -h; y < 0; y++) {
			@Pc(28) int color;
			for (@Pc(21) int x = quarterW; x < 0; x++) {
				color = src[srcOff++];
				if (color != 0 && mask[dstOff] == 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}

				color = src[srcOff++];
				if (color != 0 && mask[dstOff] == 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}

				color = src[srcOff++];
				if (color != 0 && mask[dstOff] == 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}

				color = src[srcOff++];
				if (color != 0 && mask[dstOff] == 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}
			}

			for (@Pc(104) int x = w; x < 0; x++) {
				color = src[srcOff++];
				if (color != 0 && mask[dstOff] == 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}
			}

			dstOff += dstStep;
			srcOff += srcStep;
		}
	}
}
