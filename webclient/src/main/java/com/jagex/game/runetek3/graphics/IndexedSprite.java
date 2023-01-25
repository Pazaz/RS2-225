package com.jagex.game.runetek3.graphics;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ib")
public class IndexedSprite extends Draw2D {

	@OriginalMember(owner = "client!ib", name = "F", descriptor = "I")
	public int clipWidth;

	@OriginalMember(owner = "client!ib", name = "G", descriptor = "I")
	private int clipHeight;

	@OriginalMember(owner = "client!ib", name = "A", descriptor = "[I")
	public final int[] palette;

	@OriginalMember(owner = "client!ib", name = "D", descriptor = "I")
	public int clipX;

	@OriginalMember(owner = "client!ib", name = "E", descriptor = "I")
	public int clipY;

	@OriginalMember(owner = "client!ib", name = "B", descriptor = "I")
	public int spriteWidth;

	@OriginalMember(owner = "client!ib", name = "C", descriptor = "I")
	public int spriteHeight;

	@OriginalMember(owner = "client!ib", name = "z", descriptor = "[B")
	public byte[] pixels;

	@OriginalMember(owner = "client!ib", name = "<init>", descriptor = "(Lclient!ub;Ljava/lang/String;I)V")
	public IndexedSprite(@OriginalArg(0) FileArchive archive, @OriginalArg(1) String name, @OriginalArg(2) int id) {
		@Pc(32) Buffer dat = new Buffer(archive.read(name + ".dat", null));
		@Pc(42) Buffer index = new Buffer(archive.read("index.dat", null));

		index.pos = dat.g2();
		this.clipWidth = index.g2();
		this.clipHeight = index.g2();

		@Pc(57) int count = index.g1();
		this.palette = new int[count];
		for (@Pc(63) int i = 0; i < count - 1; i++) {
			this.palette[i + 1] = index.g3();
		}

		for (@Pc(81) int i = 0; i < id; i++) {
			index.pos += 2;
			dat.pos += index.g2() * index.g2();
			index.pos++;
		}

		this.clipX = index.g1();
		this.clipY = index.g1();
		this.spriteWidth = index.g2();
		this.spriteHeight = index.g2();

		@Pc(128) int order = index.g1();
		@Pc(134) int len = this.spriteWidth * this.spriteHeight;
		this.pixels = new byte[len];

		if (order == 0) {
			for (int i = 0; i < len; i++) {
				this.pixels[i] = dat.g1b();
			}
		} else if (order == 1) {
			for (int x = 0; x < this.spriteWidth; x++) {
				for (@Pc(164) int y = 0; y < this.spriteHeight; y++) {
					this.pixels[x + y * this.spriteWidth] = dat.g1b();
				}
			}
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(Z)V")
	public void shrink() {
		this.clipWidth /= 2;
		this.clipHeight /= 2;

		@Pc(20) byte[] tmp = new byte[this.clipWidth * this.clipHeight];
		@Pc(22) int i = 0;
		for (@Pc(24) int y = 0; y < this.spriteHeight; y++) {
			for (@Pc(28) int x = 0; x < this.spriteWidth; x++) {
				tmp[(x + this.clipX >> 1) + (y + this.clipY >> 1) * this.clipWidth] = this.pixels[i++];
			}
		}

		this.pixels = tmp;
		this.spriteWidth = this.clipWidth;
		this.spriteHeight = this.clipHeight;
		this.clipX = 0;
		this.clipY = 0;
	}

	@OriginalMember(owner = "client!ib", name = "c", descriptor = "(I)V")
	public void crop() {
		if (this.spriteWidth == this.clipWidth && this.spriteHeight == this.clipHeight) {
			return;
		}

		@Pc(19) byte[] tmp = new byte[this.clipWidth * this.clipHeight];
		@Pc(21) int i = 0;
		for (@Pc(33) int y = 0; y < this.spriteHeight; y++) {
			for (@Pc(37) int x = 0; x < this.spriteWidth; x++) {
				tmp[x + this.clipX + (y + this.clipY) * this.clipWidth] = this.pixels[i++];
			}
		}

		this.pixels = tmp;
		this.spriteWidth = this.clipWidth;
		this.spriteHeight = this.clipHeight;
		this.clipX = 0;
		this.clipY = 0;
	}

	@OriginalMember(owner = "client!ib", name = "d", descriptor = "(I)V")
	public void flipHorizontally() {
		@Pc(8) byte[] tmp = new byte[this.spriteWidth * this.spriteHeight];
		@Pc(10) int i = 0;
		for (@Pc(12) int y = 0; y < this.spriteHeight; y++) {
			for (@Pc(19) int x = this.spriteWidth - 1; x >= 0; x--) {
				tmp[i++] = this.pixels[x + y * this.spriteWidth];
			}
		}

		this.pixels = tmp;
		this.clipX = this.clipWidth - this.spriteWidth - this.clipX;
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(B)V")
	public void flipVertically() {
		@Pc(8) byte[] tmp = new byte[this.spriteWidth * this.spriteHeight];
		@Pc(13) int i = 0;
		for (@Pc(25) int y = this.spriteHeight - 1; y >= 0; y--) {
			for (@Pc(29) int x = 0; x < this.spriteWidth; x++) {
				tmp[i++] = this.pixels[x + y * this.spriteWidth];
			}
		}

		this.pixels = tmp;
		this.clipY = this.clipHeight - this.spriteHeight - this.clipY;
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(IIIZ)V")
	public void translate(@OriginalArg(0) int r, @OriginalArg(1) int g, @OriginalArg(2) int b) {
		for (@Pc(3) int i = 0; i < this.palette.length; i++) {
			int red = this.palette[i] >> 16 & 0xFF;
			red += r;
			if (red < 0) {
				red = 0;
			} else if (red > 255) {
				red = 255;
			}

			@Pc(38) int green = this.palette[i] >> 8 & 0xFF;
			green += g;
			if (green < 0) {
				green = 0;
			} else if (green > 255) {
				green = 255;
			}

			@Pc(60) int blue = this.palette[i] & 0xFF;
			blue += b;
			if (blue < 0) {
				blue = 0;
			} else if (blue > 255) {
				blue = 255;
			}

			this.palette[i] = (red << 16) + (green << 8) + blue;
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(IIZ)V")
	public void draw(@OriginalArg(0) int y, @OriginalArg(1) int x) {
		x += this.clipX;
		y += this.clipY;

		@Pc(15) int dstOff = x + y * Draw2D.width;
		@Pc(17) int srcOff = 0;

		@Pc(20) int h = this.spriteHeight;
		@Pc(23) int w = this.spriteWidth;

		@Pc(27) int dstStep = Draw2D.width - w;
		@Pc(29) int srcStep = 0;

		if (y < Draw2D.top) {
			int cutoff = Draw2D.top - y;
			h -= cutoff;
			y = Draw2D.top;
			srcOff += cutoff * w;
			dstOff += cutoff * Draw2D.width;
		}

		if (y + h > Draw2D.bottom) {
			h -= y + h - Draw2D.bottom;
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

		if (x + w > Draw2D.right) {
			int cutoff = x + w - Draw2D.right;
			w -= cutoff;
			srcStep += cutoff;
			dstStep += cutoff;
		}

		if (w > 0 && h > 0) {
			this.copyImage(Draw2D.data, srcOff, srcStep, this.pixels, h, w, dstOff, dstStep, this.palette);
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "([III[BIIIII[I)V")
	private void copyImage(@OriginalArg(0) int[] dst, @OriginalArg(1) int srcOff, @OriginalArg(2) int srcStep, @OriginalArg(3) byte[] src, @OriginalArg(4) int h, @OriginalArg(6) int w, @OriginalArg(7) int dstOff, @OriginalArg(8) int dstStep, @OriginalArg(9) int[] palette) {
		@Pc(6) int hw = -(w >> 2);
		w = -(w & 0x3);

		for (@Pc(17) int y = -h; y < 0; y++) {
			for (@Pc(21) int x = hw; x < 0; x++) {
				@Pc(28) byte p = src[srcOff++];
				if (p != 0) {
					dst[dstOff++] = palette[p & 0xFF];
				} else {
					dstOff++;
				}

				p = src[srcOff++];
				if (p != 0) {
					dst[dstOff++] = palette[p & 0xFF];
				} else {
					dstOff++;
				}

				p = src[srcOff++];
				if (p != 0) {
					dst[dstOff++] = palette[p & 0xFF];
				} else {
					dstOff++;
				}

				p = src[srcOff++];
				if (p != 0) {
					dst[dstOff++] = palette[p & 0xFF];
				} else {
					dstOff++;
				}
			}

			for (@Pc(104) int x = w; x < 0; x++) {
				@Pc(111) byte p = src[srcOff++];
				if (p != 0) {
					dst[dstOff++] = palette[p & 0xFF];
				} else {
					dstOff++;
				}
			}

			dstOff += dstStep;
			srcOff += srcStep;
		}
	}
}
