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
	public IndexedSprite(@OriginalArg(0) FileArchive arg0, @OriginalArg(1) String arg1, @OriginalArg(2) int arg2) {
		@Pc(32) Buffer local32 = new Buffer(arg0.read(arg1 + ".dat", null));
		@Pc(42) Buffer local42 = new Buffer(arg0.read("index.dat", null));
		local42.pos = local32.g2();
		this.clipWidth = local42.g2();
		this.clipHeight = local42.g2();
		@Pc(57) int local57 = local42.g1();
		this.palette = new int[local57];
		for (@Pc(63) int local63 = 0; local63 < local57 - 1; local63++) {
			this.palette[local63 + 1] = local42.g3();
		}
		for (@Pc(81) int local81 = 0; local81 < arg2; local81++) {
			local42.pos += 2;
			local32.pos += local42.g2() * local42.g2();
			local42.pos++;
		}
		this.clipX = local42.g1();
		this.clipY = local42.g1();
		this.spriteWidth = local42.g2();
		this.spriteHeight = local42.g2();
		@Pc(128) int local128 = local42.g1();
		@Pc(134) int local134 = this.spriteWidth * this.spriteHeight;
		this.pixels = new byte[local134];
		@Pc(142) int local142;
		if (local128 == 0) {
			for (local142 = 0; local142 < local134; local142++) {
				this.pixels[local142] = local32.g1b();
			}
		} else if (local128 == 1) {
			for (local142 = 0; local142 < this.spriteWidth; local142++) {
				for (@Pc(164) int local164 = 0; local164 < this.spriteHeight; local164++) {
					this.pixels[local142 + local164 * this.spriteWidth] = local32.g1b();
				}
			}
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(Z)V")
	public void shrink() {
		this.clipWidth /= 2;
		this.clipHeight /= 2;
		@Pc(20) byte[] local20 = new byte[this.clipWidth * this.clipHeight];
		@Pc(22) int local22 = 0;
		for (@Pc(24) int local24 = 0; local24 < this.spriteHeight; local24++) {
			for (@Pc(28) int local28 = 0; local28 < this.spriteWidth; local28++) {
				local20[(local28 + this.clipX >> 1) + (local24 + this.clipY >> 1) * this.clipWidth] = this.pixels[local22++];
			}
		}
		this.pixels = local20;
		this.spriteWidth = this.clipWidth;
		this.spriteHeight = this.clipHeight;
		this.clipX = 0;
		this.clipY = 0;
	}

	@OriginalMember(owner = "client!ib", name = "c", descriptor = "(I)V")
	public void crop() {
		if (this.spriteWidth != this.clipWidth || this.spriteHeight != this.clipHeight) {
			@Pc(19) byte[] local19 = new byte[this.clipWidth * this.clipHeight];
			@Pc(21) int local21 = 0;
			for (@Pc(33) int local33 = 0; local33 < this.spriteHeight; local33++) {
				for (@Pc(37) int local37 = 0; local37 < this.spriteWidth; local37++) {
					local19[local37 + this.clipX + (local33 + this.clipY) * this.clipWidth] = this.pixels[local21++];
				}
			}
			this.pixels = local19;
			this.spriteWidth = this.clipWidth;
			this.spriteHeight = this.clipHeight;
			this.clipX = 0;
			this.clipY = 0;
		}
	}

	@OriginalMember(owner = "client!ib", name = "d", descriptor = "(I)V")
	public void flipHorizontally() {
		@Pc(8) byte[] local8 = new byte[this.spriteWidth * this.spriteHeight];
		@Pc(10) int local10 = 0;
		for (@Pc(12) int local12 = 0; local12 < this.spriteHeight; local12++) {
			for (@Pc(19) int local19 = this.spriteWidth - 1; local19 >= 0; local19--) {
				local8[local10++] = this.pixels[local19 + local12 * this.spriteWidth];
			}
		}
		this.pixels = local8;
		this.clipX = this.clipWidth - this.spriteWidth - this.clipX;
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(B)V")
	public void flipVertically() {
		@Pc(8) byte[] local8 = new byte[this.spriteWidth * this.spriteHeight];
		@Pc(13) int local13 = 0;
		for (@Pc(25) int local25 = this.spriteHeight - 1; local25 >= 0; local25--) {
			for (@Pc(29) int local29 = 0; local29 < this.spriteWidth; local29++) {
				local8[local13++] = this.pixels[local29 + local25 * this.spriteWidth];
			}
		}
		this.pixels = local8;
		this.clipY = this.clipHeight - this.spriteHeight - this.clipY;
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(IIIZ)V")
	public void translate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(14) int local14;
		for (@Pc(3) int local3 = 0; local3 < this.palette.length; local3++) {
			local14 = this.palette[local3] >> 16 & 0xFF;
			local14 += arg0;
			if (local14 < 0) {
				local14 = 0;
			} else if (local14 > 255) {
				local14 = 255;
			}
			@Pc(38) int local38 = this.palette[local3] >> 8 & 0xFF;
			local38 += arg1;
			if (local38 < 0) {
				local38 = 0;
			} else if (local38 > 255) {
				local38 = 255;
			}
			@Pc(60) int local60 = this.palette[local3] & 0xFF;
			local60 += arg2;
			if (local60 < 0) {
				local60 = 0;
			} else if (local60 > 255) {
				local60 = 255;
			}
			this.palette[local3] = (local14 << 16) + (local38 << 8) + local60;
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(IIZ)V")
	public void draw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		arg1 += this.clipX;
		arg0 += this.clipY;
		@Pc(15) int local15 = arg1 + arg0 * Draw2D.width;
		@Pc(17) int local17 = 0;
		@Pc(20) int local20 = this.spriteHeight;
		@Pc(23) int local23 = this.spriteWidth;
		@Pc(27) int local27 = Draw2D.width - local23;
		@Pc(29) int local29 = 0;
		@Pc(36) int local36;
		if (arg0 < Draw2D.top) {
			local36 = Draw2D.top - arg0;
			local20 -= local36;
			arg0 = Draw2D.top;
			local17 += local36 * local23;
			local15 += local36 * Draw2D.width;
		}
		if (arg0 + local20 > Draw2D.bottom) {
			local20 -= arg0 + local20 - Draw2D.bottom;
		}
		if (arg1 < Draw2D.left) {
			local36 = Draw2D.left - arg1;
			local23 -= local36;
			arg1 = Draw2D.left;
			local17 += local36;
			local15 += local36;
			local29 += local36;
			local27 += local36;
		}
		if (arg1 + local23 > Draw2D.right) {
			local36 = arg1 + local23 - Draw2D.right;
			local23 -= local36;
			local29 += local36;
			local27 += local36;
		}
		if (local23 > 0 && local20 > 0) {
			this.copyImage(Draw2D.data, local17, local29, this.pixels, local20, local23, local15, local27, this.palette);
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "([III[BIIIII[I)V")
	private void copyImage(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) byte[] arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int[] arg9) {
		@Pc(6) int local6 = -(arg6 >> 2);
		@Pc(11) int local11 = -(arg6 & 0x3);
		for (@Pc(17) int local17 = -arg4; local17 < 0; local17++) {
			for (@Pc(21) int local21 = local6; local21 < 0; local21++) {
				@Pc(28) byte local28 = arg3[arg1++];
				if (local28 == 0) {
					arg7++;
				} else {
					arg0[arg7++] = arg9[local28 & 0xFF];
				}
				local28 = arg3[arg1++];
				if (local28 == 0) {
					arg7++;
				} else {
					arg0[arg7++] = arg9[local28 & 0xFF];
				}
				local28 = arg3[arg1++];
				if (local28 == 0) {
					arg7++;
				} else {
					arg0[arg7++] = arg9[local28 & 0xFF];
				}
				local28 = arg3[arg1++];
				if (local28 == 0) {
					arg7++;
				} else {
					arg0[arg7++] = arg9[local28 & 0xFF];
				}
			}
			for (@Pc(104) int local104 = local11; local104 < 0; local104++) {
				@Pc(111) byte local111 = arg3[arg1++];
				if (local111 == 0) {
					arg7++;
				} else {
					arg0[arg7++] = arg9[local111 & 0xFF];
				}
			}
			arg7 += arg8;
			arg1 += arg2;
		}
	}
}
