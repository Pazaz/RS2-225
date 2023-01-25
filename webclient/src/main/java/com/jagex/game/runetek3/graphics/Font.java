package com.jagex.game.runetek3.graphics;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.util.Random;

@OriginalClass("client!jb")
public class Font extends Draw2D {

	@OriginalMember(owner = "client!jb", name = "K", descriptor = "[I")
	private static final int[] CHAR_TABLE = new int[256];

	@OriginalMember(owner = "client!jb", name = "B", descriptor = "[[B")
	private final byte[][] pixels = new byte[94][];

	@OriginalMember(owner = "client!jb", name = "C", descriptor = "[I")
	private final int[] charWidth = new int[94];

	@OriginalMember(owner = "client!jb", name = "D", descriptor = "[I")
	private final int[] charHeight = new int[94];

	@OriginalMember(owner = "client!jb", name = "E", descriptor = "[I")
	private final int[] clipX = new int[94];

	@OriginalMember(owner = "client!jb", name = "F", descriptor = "[I")
	private final int[] clipY = new int[94];

	@OriginalMember(owner = "client!jb", name = "G", descriptor = "[I")
	private final int[] charSpace = new int[95];

	@OriginalMember(owner = "client!jb", name = "H", descriptor = "[I")
	private final int[] drawWidth = new int[256];

	@OriginalMember(owner = "client!jb", name = "J", descriptor = "Ljava/util/Random;")
	private final Random random = new Random();

	@OriginalMember(owner = "client!jb", name = "I", descriptor = "I")
	public int fontHeight;

	static {
		@Pc(4) String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";

		for (@Pc(6) int i = 0; i < 256; i++) {
			@Pc(11) int c = s.indexOf(i);
			if (c == -1) {
				c = 74;
			}

			CHAR_TABLE[i] = c;
		}
	}

	@OriginalMember(owner = "client!jb", name = "<init>", descriptor = "(Lclient!ub;Ljava/lang/String;I)V")
	public Font(@OriginalArg(0) FileArchive archive, @OriginalArg(1) String name) {
		@Pc(71) Buffer dat = new Buffer(archive.read(name + ".dat", null));
		@Pc(81) Buffer index = new Buffer(archive.read("index.dat", null));

		index.pos = dat.g2() + 4; // skipping clipWidth and clipHeight

		@Pc(90) int paletteCount = index.g1();
		if (paletteCount > 0) {
			// color palette is simply on (white) or off (black) here, so we skip it
			index.pos += (paletteCount - 1) * 3;
		}

		for (@Pc(104) int c = 0; c < 94; c++) {
			this.clipX[c] = index.g1();
			this.clipY[c] = index.g1();

			@Pc(131) int width = this.charWidth[c] = index.g2();
			@Pc(139) int height = this.charHeight[c] = index.g2();

			@Pc(142) int order = index.g1();
			@Pc(146) int len = width * height;
			this.pixels[c] = new byte[len];

			if (order == 0) {
				for (int i = 0; i < len; i++) {
					this.pixels[c][i] = dat.g1b();
				}
			} else if (order == 1) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						this.pixels[c][x + y * width] = dat.g1b();
					}
				}
			}

			if (height > this.fontHeight) {
				this.fontHeight = height;
			}

			this.clipX[c] = 1;
			this.charSpace[c] = width + 2;

			{
				int i = 0;
				for (int y = height / 7; y < height; y++) {
					i += this.pixels[c][y * width];
				}

				if (i <= height / 7) {
					this.charSpace[c]--;
					this.clipX[c] = 0;
				}
			}

			{
				int i = 0;
				for (int y = height / 7; y < height; y++) {
					i += this.pixels[c][width + y * width - 1];
				}

				if (i <= height / 7) {
					this.charSpace[c]--;
				}
			}
		}

		this.charSpace[94] = this.charSpace[8];
		for (int i = 0; i < 256; i++) {
			this.drawWidth[i] = this.charSpace[CHAR_TABLE[i]];
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IBILjava/lang/String;I)V")
	public void drawCentered(@OriginalArg(0) int y, @OriginalArg(2) int color, @OriginalArg(3) String str, @OriginalArg(4) int x) {
		this.draw(x - this.stringWidth(str) / 2, y, color, str);
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IIZILjava/lang/String;I)V")
	public void drawCentered(@OriginalArg(0) int x, @OriginalArg(1) int color, @OriginalArg(2) boolean shadow, @OriginalArg(3) int y, @OriginalArg(4) String str) {
		this.draw(x - this.stringWidth(str) / 2, y, str, shadow, color);
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(ZLjava/lang/String;)I")
	public int stringWidth(@OriginalArg(1) String str) {
		if (str == null) {
			return 0;
		}

		@Pc(7) int w = 0;
		for (@Pc(14) int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '@' && i + 4 < str.length() && str.charAt(i + 4) == '@') {
				i += 4;
			} else {
				w += this.drawWidth[str.charAt(i)];
			}
		}

		return w;
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IIZILjava/lang/String;)V")
	public void draw(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(3) int color, @OriginalArg(4) String str) {
		if (str == null) {
			return;
		}

		y -= this.fontHeight;
		for (@Pc(19) int i = 0; i < str.length(); i++) {
			@Pc(27) int c = CHAR_TABLE[str.charAt(i)];

			if (c != 94) {
				this.fillMaskedRect(this.pixels[c], x + this.clipX[c], y + this.clipY[c], this.charWidth[c], this.charHeight[c], color);
			}

			x += this.charSpace[c];
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IBIIILjava/lang/String;)V")
	public void drawCenteredWave(@OriginalArg(0) int cycle, @OriginalArg(2) int x, @OriginalArg(3) int y, @OriginalArg(4) int color, @OriginalArg(5) String str) {
		if (str == null) {
			return;
		}

		x -= this.stringWidth(str) / 2;
		y -= this.fontHeight;

		for (int i = 0; i < str.length(); i++) {
			@Pc(39) int c = CHAR_TABLE[str.charAt(i)];

			if (c != 94) {
				this.fillMaskedRect(this.pixels[c], x + this.clipX[c], y + this.clipY[c] + (int) (Math.sin((double) i / 2.0D + (double) cycle / 5.0D) * 5.0D), this.charWidth[c], this.charHeight[c], color);
			}

			x += this.charSpace[c];
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IIILjava/lang/String;ZI)V")
	public void draw(@OriginalArg(0) int x, @OriginalArg(2) int y, @OriginalArg(3) String str, @OriginalArg(4) boolean shadow, @OriginalArg(5) int color) {
		if (str == null) {
			return;
		}

		y -= this.fontHeight;
		for (@Pc(11) int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '@' && i + 4 < str.length() && str.charAt(i + 4) == '@') {
				color = this.evaluateTag(str.substring(i + 1, i + 4));
				i += 4;
			} else {
				@Pc(52) int c = CHAR_TABLE[str.charAt(i)];
				if (c != 94) {
					if (shadow) {
						this.fillMaskedRect(this.pixels[c], x + this.clipX[c] + 1, y + this.clipY[c] + 1, this.charWidth[c], this.charHeight[c], 0);
					}

					this.fillMaskedRect(this.pixels[c], x + this.clipX[c], y + this.clipY[c], this.charWidth[c], this.charHeight[c], color);
				}

				x += this.charSpace[c];
			}
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IZBIILjava/lang/String;I)V")
	public void drawTooltip(@OriginalArg(0) int seed, @OriginalArg(1) boolean shadow, @OriginalArg(3) int y, @OriginalArg(4) int color, @OriginalArg(5) String str, @OriginalArg(6) int x) {
		if (str == null) {
			return;
		}

		this.random.setSeed(seed);
		@Pc(17) int alpha = (this.random.nextInt() & 0x1F) + 192;

		y -= this.fontHeight;
		for (@Pc(30) int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '@' && i + 4 < str.length() && str.charAt(i + 4) == '@') {
				color = this.evaluateTag(str.substring(i + 1, i + 4));
				i += 4;
			} else {
				@Pc(71) int c = CHAR_TABLE[str.charAt(i)];
				if (c != 94) {
					if (shadow) {
						this.fillMaskedRect(this.pixels[c], x + this.clipX[c] + 1, this.charHeight[c], 0, y + this.clipY[c] + 1, 192, this.charWidth[c]);
					}

					this.fillMaskedRect(this.pixels[c], x + this.clipX[c], this.charHeight[c], color, y + this.clipY[c], alpha, this.charWidth[c]);
				}

				x += this.charSpace[c];

				if ((this.random.nextInt() & 0x3) == 0) {
					x++;
				}
			}
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(ILjava/lang/String;)I")
	private int evaluateTag(@OriginalArg(1) String str) {
		switch (str) {
			case "red":
				return 0xff0000;
			case "gre":
				return 0xff00;
			case "blu":
				return 0xff;
			case "yel":
				return 0xffff00;
			case "cya":
				return 0xffff;
			case "mag":
				return 0xff00ff;
			case "whi":
				return 0xffffff;
			case "lre":
				return 0xff9040;
			case "dre":
				return 0x800000;
			case "dbl":
				return 0x80;
			case "or1":
				return 0xffb000;
			case "or2":
				return 0xff7000;
			case "or3":
				return 0xff3000;
			case "gr1":
				return 0xc0ff00;
			case "gr2":
				return 0x80ff00;
			case "gr3":
				return 0x40ff00;
			case "bla":
			default:
				return 0;
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "([BIIIII)V")
	private void fillMaskedRect(@OriginalArg(0) byte[] data, @OriginalArg(1) int x, @OriginalArg(2) int y, @OriginalArg(3) int w, @OriginalArg(4) int h, @OriginalArg(5) int color) {
		@Pc(5) int dstOff = x + y * Draw2D.width;
		@Pc(9) int dstStep = Draw2D.width - w;
		@Pc(11) int srcStep = 0;
		@Pc(13) int srcOff = 0;

		if (y < Draw2D.top) {
			int cutoff = Draw2D.top - y;
			h -= cutoff;
			y = Draw2D.top;
			srcOff += cutoff * w;
			dstOff += cutoff * Draw2D.width;
		}

		if (y + h >= Draw2D.bottom) {
			h -= y + h + 1 - Draw2D.bottom;
		}

		if (x < Draw2D.left) {
			int cutoff = Draw2D.left - x;
			w -= cutoff;
			x = Draw2D.left;
			srcOff += cutoff;
			dstOff += cutoff;
			srcStep += cutoff;
			dstStep += cutoff;
		}

		if (x + w >= Draw2D.right) {
			int cutoff = x + w + 1 - Draw2D.right;
			w -= cutoff;
			srcStep += cutoff;
			dstStep += cutoff;
		}

		if (w > 0 && h > 0) {
			this.fillMaskedRect(Draw2D.data, data, color, srcOff, dstOff, w, h, dstStep, srcStep);
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "([I[BIIIIIII)V")
	private void fillMaskedRect(@OriginalArg(0) int[] dst, @OriginalArg(1) byte[] src, @OriginalArg(2) int color, @OriginalArg(3) int srcOff, @OriginalArg(4) int dstOff, @OriginalArg(5) int w, @OriginalArg(6) int h, @OriginalArg(7) int dstStep, @OriginalArg(8) int srcStep) {
		@Pc(6) int hw = -(w >> 2);
		w = -(w & 0x3);

		for (@Pc(14) int y = -h; y < 0; y++) {
			for (@Pc(18) int x = hw; x < 0; x++) {
				if (src[srcOff++] != 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}

				if (src[srcOff++] != 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}

				if (src[srcOff++] != 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}

				if (src[srcOff++] != 0) {
					dst[dstOff++] = color;
				} else {
					dstOff++;
				}
			}

			for (@Pc(77) int x = w; x < 0; x++) {
				if (src[srcOff++] == 0) {
					dstOff++;
				} else {
					dst[dstOff++] = color;
				}
			}

			dstOff += dstStep;
			srcOff += srcStep;
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "([BBIIIIII)V")
	private void fillMaskedRect(@OriginalArg(0) byte[] mask, @OriginalArg(2) int x, @OriginalArg(3) int h, @OriginalArg(4) int color, @OriginalArg(5) int y, @OriginalArg(6) int alpha, @OriginalArg(7) int w) {
		@Pc(10) int dstOff = x + y * Draw2D.width;
		@Pc(14) int dstStep = Draw2D.width - w;
		@Pc(16) int maskStep = 0;
		@Pc(18) int maskOff = 0;

		if (y < Draw2D.top) {
			int cutoff = Draw2D.top - y;
			h -= cutoff;
			y = Draw2D.top;
			maskOff += cutoff * w;
			dstOff += cutoff * Draw2D.width;
		}

		if (y + h >= Draw2D.bottom) {
			h -= y + h + 1 - Draw2D.bottom;
		}

		if (x < Draw2D.left) {
			int cutoff = Draw2D.left - x;
			w -= cutoff;
			x = Draw2D.left;
			maskOff += cutoff;
			dstOff += cutoff;
			maskStep += cutoff;
			dstStep += cutoff;
		}

		if (x + w >= Draw2D.right) {
			int cutoff = x + w + 1 - Draw2D.right;
			w -= cutoff;
			maskStep += cutoff;
			dstStep += cutoff;
		}

		if (w > 0 && h > 0) {
			this.fillMaskedRect(h, dstOff, w, Draw2D.data, mask, alpha, maskOff, dstStep, maskStep, color);
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(III[I[BIIIIBI)V")
	private void fillMaskedRect(@OriginalArg(0) int h, @OriginalArg(1) int dstOff, @OriginalArg(2) int w, @OriginalArg(3) int[] dst, @OriginalArg(4) byte[] mask, @OriginalArg(5) int alpha, @OriginalArg(6) int maskOff, @OriginalArg(7) int dstStep, @OriginalArg(8) int maskStep, @OriginalArg(10) int color) {
		color = ((color & 0xFF00FF) * alpha & 0xFF00FF00) + ((color & 0xFF00) * alpha & 0xFF0000) >> 8;
		@Pc(38) int invAlpha = 256 - alpha;

		for (int y = -h; y < 0; y++) {
			for (@Pc(46) int x = -w; x < 0; x++) {
				if (mask[maskOff++] != 0) {
					@Pc(57) int dstColor = dst[dstOff];
					dst[dstOff++] = (((dstColor & 0xFF00FF) * invAlpha & 0xFF00FF00) + ((dstColor & 0xFF00) * invAlpha & 0xFF0000) >> 8) + color;
				} else {
					dstOff++;
				}
			}

			dstOff += dstStep;
			maskOff += maskStep;
		}
	}
}
