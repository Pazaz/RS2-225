package com.jagex.game.runetek3.graphics.ui;

import java.util.Random;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.graphics.Draw2D;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

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
	private final int[] charOffsetX = new int[94];

	@OriginalMember(owner = "client!jb", name = "F", descriptor = "[I")
	private final int[] charOffsetY = new int[94];

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
		for (@Pc(6) int local6 = 0; local6 < 256; local6++) {
			@Pc(11) int local11 = s.indexOf(local6);
			if (local11 == -1) {
				local11 = 74;
			}
			CHAR_TABLE[local6] = local11;
		}
	}

	@OriginalMember(owner = "client!jb", name = "<init>", descriptor = "(Lclient!ub;Ljava/lang/String;I)V")
	public Font(@OriginalArg(0) FileArchive archive, @OriginalArg(1) String name) {
		@Pc(71) Buffer dat = new Buffer(archive.read(name + ".dat", null));
		@Pc(81) Buffer idx = new Buffer(archive.read("index.dat", null));
		idx.pos = dat.g2() + 4;
		@Pc(90) int off = idx.g1();
		if (off > 0) {
			idx.pos += (off - 1) * 3;
		}
		@Pc(131) int local131;
		for (@Pc(104) int local104 = 0; local104 < 94; local104++) {
			this.charOffsetX[local104] = idx.g1();
			this.charOffsetY[local104] = idx.g1();
			local131 = this.charWidth[local104] = idx.g2();
			@Pc(139) int local139 = this.charHeight[local104] = idx.g2();
			@Pc(142) int local142 = idx.g1();
			@Pc(146) int local146 = local131 * local139;
			this.pixels[local104] = new byte[local146];
			@Pc(156) int local156;
			@Pc(181) int local181;
			if (local142 == 0) {
				for (local156 = 0; local156 < local146; local156++) {
					this.pixels[local104][local156] = dat.g1b();
				}
			} else if (local142 == 1) {
				for (local156 = 0; local156 < local131; local156++) {
					for (local181 = 0; local181 < local139; local181++) {
						this.pixels[local104][local156 + local181 * local131] = dat.g1b();
					}
				}
			}
			if (local139 > this.fontHeight) {
				this.fontHeight = local139;
			}
			this.charOffsetX[local104] = 1;
			this.charSpace[local104] = local131 + 2;
			local156 = 0;
			for (local181 = local139 / 7; local181 < local139; local181++) {
				local156 += this.pixels[local104][local181 * local131];
			}
			@Pc(255) int local255;
			if (local156 <= local139 / 7) {
				local255 = this.charSpace[local104]--;
				this.charOffsetX[local104] = 0;
			}
			local156 = 0;
			for (@Pc(269) int local269 = local139 / 7; local269 < local139; local269++) {
				local156 += this.pixels[local104][local131 + local269 * local131 - 1];
			}
			if (local156 <= local139 / 7) {
				local255 = this.charSpace[local104]--;
			}
		}
		this.charSpace[94] = this.charSpace[8];
		for (local131 = 0; local131 < 256; local131++) {
			this.drawWidth[local131] = this.charSpace[CHAR_TABLE[local131]];
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IBILjava/lang/String;I)V")
	public void drawCentered(@OriginalArg(3) String str, @OriginalArg(4) int x, @OriginalArg(0) int y, @OriginalArg(2) int color) {
		this.draw(x - this.stringWidth(str) / 2, y, color, str);
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IIZILjava/lang/String;I)V")
	public void drawCentered(@OriginalArg(4) String str, @OriginalArg(0) int x, @OriginalArg(3) int y, @OriginalArg(1) int rgb, @OriginalArg(2) boolean shadow) {
		this.draw(str, x - this.stringWidth(str) / 2, y, rgb, shadow);
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(ZLjava/lang/String;)I")
	public int stringWidth(@OriginalArg(1) String str) {
		if (str == null) {
			return 0;
		}
		@Pc(7) int width = 0;
		for (@Pc(14) int c = 0; c < str.length(); c++) {
			if (str.charAt(c) == '@' && c + 4 < str.length() && str.charAt(c + 4) == '@') {
				c += 4;
			} else {
				width += this.drawWidth[str.charAt(c)];
			}
		}
		return width;
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IIZILjava/lang/String;)V")
	public void draw(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(3) int rgb, @OriginalArg(4) String str) {
		if (str == null) {
			return;
		}
		@Pc(7) int local7 = y - this.fontHeight;
		for (@Pc(19) int local19 = 0; local19 < str.length(); local19++) {
			@Pc(27) int local27 = CHAR_TABLE[str.charAt(local19)];
			if (local27 != 94) {
				this.fillMaskedRect(this.pixels[local27], x + this.charOffsetX[local27], local7 + this.charOffsetY[local27], this.charWidth[local27], this.charHeight[local27], rgb);
			}
			x += this.charSpace[local27];
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IBIIILjava/lang/String;)V")
	public void drawCenteredWave(@OriginalArg(5) String str, @OriginalArg(0) int dt, @OriginalArg(2) int x, @OriginalArg(3) int y, @OriginalArg(4) int rgb) {
		if (str == null) {
			return;
		}
		x -= this.stringWidth(str) / 2;
		@Pc(18) int local18 = y - this.fontHeight;
		@Pc(24) int local24;
		for (local24 = 0; local24 < str.length(); local24++) {
			@Pc(39) int local39 = CHAR_TABLE[str.charAt(local24)];
			if (local39 != 94) {
				this.fillMaskedRect(this.pixels[local39], x + this.charOffsetX[local39], local18 + this.charOffsetY[local39] + (int) (Math.sin((double) local24 / 2.0D + (double) dt / 5.0D) * 5.0D), this.charWidth[local39], this.charHeight[local39], rgb);
			}
			x += this.charSpace[local39];
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IIILjava/lang/String;ZI)V")
	public void draw(@OriginalArg(3) String str, @OriginalArg(0) int x, @OriginalArg(2) int y, @OriginalArg(5) int rgb, @OriginalArg(4) boolean shadow) {
		if (str == null) {
			return;
		}
		@Pc(9) int local9 = y - this.fontHeight;
		for (@Pc(11) int local11 = 0; local11 < str.length(); local11++) {
			if (str.charAt(local11) == '@' && local11 + 4 < str.length() && str.charAt(local11 + 4) == '@') {
				rgb = this.evaluateTag(str.substring(local11 + 1, local11 + 4));
				local11 += 4;
			} else {
				@Pc(52) int local52 = CHAR_TABLE[str.charAt(local11)];
				if (local52 != 94) {
					if (shadow) {
						this.fillMaskedRect(this.pixels[local52], x + this.charOffsetX[local52] + 1, local9 + this.charOffsetY[local52] + 1, this.charWidth[local52], this.charHeight[local52], 0);
					}
					this.fillMaskedRect(this.pixels[local52], x + this.charOffsetX[local52], local9 + this.charOffsetY[local52], this.charWidth[local52], this.charHeight[local52], rgb);
				}
				x += this.charSpace[local52];
			}
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IZBIILjava/lang/String;I)V")
	public void drawTooltip(@OriginalArg(5) String str, @OriginalArg(6) int x, @OriginalArg(4) int rgb, @OriginalArg(0) int randSeed) {
		if (str == null) {
			return;
		}
		this.random.setSeed((long) randSeed);
		@Pc(17) int local17 = (this.random.nextInt() & 0x1F) + 192;
		@Pc(22) int local22 = 15 - this.fontHeight;
		for (@Pc(30) int local30 = 0; local30 < str.length(); local30++) {
			if (str.charAt(local30) == '@' && local30 + 4 < str.length() && str.charAt(local30 + 4) == '@') {
				rgb = this.evaluateTag(str.substring(local30 + 1, local30 + 4));
				local30 += 4;
			} else {
				@Pc(71) int local71 = CHAR_TABLE[str.charAt(local30)];
				if (local71 != 94) {
					this.fillMaskedRect(this.pixels[local71], x + this.charOffsetX[local71] + 1, this.charHeight[local71], 0, local22 + this.charOffsetY[local71] + 1, 192, this.charWidth[local71]);
					this.fillMaskedRect(this.pixels[local71], x + this.charOffsetX[local71], this.charHeight[local71], rgb, local22 + this.charOffsetY[local71], local17, this.charWidth[local71]);
				}
				x += this.charSpace[local71];
				if ((this.random.nextInt() & 0x3) == 0) {
					x++;
				}
			}
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(ILjava/lang/String;)I")
	private int evaluateTag(@OriginalArg(1) String tag) {
		if (tag.equals("red")) {
			return 0xff0000;
		} else if (tag.equals("gre")) {
			return 0xff00;
		} else if (tag.equals("blu")) {
			return 0xff;
		} else if (tag.equals("yel")) {
			return 0xffff00;
		} else if (tag.equals("cya")) {
			return 0xffff;
		} else if (tag.equals("mag")) {
			return 0xff00ff;
		} else if (tag.equals("whi")) {
			return 0xffffff;
		} else if (tag.equals("bla")) {
			return 0;
		} else if (tag.equals("lre")) {
			return 0xff9040;
		} else if (tag.equals("dre")) {
			return 0x800000;
		} else if (tag.equals("dbl")) {
			return 0x80;
		} else if (tag.equals("or1")) {
			return 0xffb000;
		} else if (tag.equals("or2")) {
			return 0xff7000;
		} else if (tag.equals("or3")) {
			return 0xff3000;
		} else if (tag.equals("gr1")) {
			return 0xc0ff00;
		} else if (tag.equals("gr2")) {
			return 0x80ff00;
		} else if (tag.equals("gr3")) {
			return 0x40ff00;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "([BIIIII)V")
	private void fillMaskedRect(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(5) int local5 = arg1 + arg2 * Draw2D.width;
		@Pc(9) int local9 = Draw2D.width - arg3;
		@Pc(11) int local11 = 0;
		@Pc(13) int local13 = 0;
		@Pc(20) int local20;
		if (arg2 < Draw2D.top) {
			local20 = Draw2D.top - arg2;
			arg4 -= local20;
			arg2 = Draw2D.top;
			local13 = local20 * arg3 + 0;
			local5 += local20 * Draw2D.width;
		}
		if (arg2 + arg4 >= Draw2D.bottom) {
			arg4 -= arg2 + arg4 + 1 - Draw2D.bottom;
		}
		if (arg1 < Draw2D.left) {
			local20 = Draw2D.left - arg1;
			arg3 -= local20;
			arg1 = Draw2D.left;
			local13 += local20;
			local5 += local20;
			local11 = local20 + 0;
			local9 += local20;
		}
		if (arg1 + arg3 >= Draw2D.right) {
			local20 = arg1 + arg3 + 1 - Draw2D.right;
			arg3 -= local20;
			local11 += local20;
			local9 += local20;
		}
		if (arg3 > 0 && arg4 > 0) {
			this.fillMaskedRect(Draw2D.data, arg0, arg5, local13, local5, arg3, arg4, local9, local11);
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "([I[BIIIIIII)V")
	private void fillMaskedRect(@OriginalArg(0) int[] arg0, @OriginalArg(1) byte[] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8) {
		@Pc(6) int local6 = -(arg5 >> 2);
		@Pc(11) int local11 = -(arg5 & 0x3);
		for (@Pc(14) int local14 = -arg6; local14 < 0; local14++) {
			for (@Pc(18) int local18 = local6; local18 < 0; local18++) {
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
			}
			for (@Pc(77) int local77 = local11; local77 < 0; local77++) {
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
			}
			arg4 += arg7;
			arg3 += arg8;
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "([BBIIIIII)V")
	private void fillMaskedRect(@OriginalArg(0) byte[] arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		@Pc(10) int local10 = arg1 + arg4 * Draw2D.width;
		@Pc(14) int local14 = Draw2D.width - arg6;
		@Pc(16) int local16 = 0;
		@Pc(18) int local18 = 0;
		@Pc(25) int local25;
		if (arg4 < Draw2D.top) {
			local25 = Draw2D.top - arg4;
			arg2 -= local25;
			arg4 = Draw2D.top;
			local18 = local25 * arg6 + 0;
			local10 += local25 * Draw2D.width;
		}
		if (arg4 + arg2 >= Draw2D.bottom) {
			arg2 -= arg4 + arg2 + 1 - Draw2D.bottom;
		}
		if (arg1 < Draw2D.left) {
			local25 = Draw2D.left - arg1;
			arg6 -= local25;
			arg1 = Draw2D.left;
			local18 += local25;
			local10 += local25;
			local16 = local25 + 0;
			local14 += local25;
		}
		if (arg1 + arg6 >= Draw2D.right) {
			local25 = arg1 + arg6 + 1 - Draw2D.right;
			arg6 -= local25;
			local16 += local25;
			local14 += local25;
		}
		if (arg6 > 0 && arg2 > 0) {
			this.fillMaskedRect(arg2, local10, arg6, Draw2D.data, arg0, arg5, local18, local14, local16, arg3);
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(III[I[BIIIIBI)V")
	private void fillMaskedRect(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int[] arg3, @OriginalArg(4) byte[] arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(10) int arg9) {
		@Pc(19) int local19 = ((arg9 & 0xFF00FF) * arg5 & 0xFF00FF00) + ((arg9 & 0xFF00) * arg5 & 0xFF0000) >> 8;
		@Pc(29) int local29;
		@Pc(38) int local38 = 256 - arg5;
		for (local29 = -arg0; local29 < 0; local29++) {
			for (@Pc(46) int local46 = -arg2; local46 < 0; local46++) {
				if (arg4[arg6++] == 0) {
					arg1++;
				} else {
					@Pc(57) int local57 = arg3[arg1];
					arg3[arg1++] = (((local57 & 0xFF00FF) * local38 & 0xFF00FF00) + ((local57 & 0xFF00) * local38 & 0xFF0000) >> 8) + local19;
				}
			}
			arg1 += arg7;
			arg6 += arg8;
		}
	}

	@OriginalMember(owner = "client!jb", name = "v", descriptor = "Z")
	private final boolean flowObfuscator7 = true;

	@OriginalMember(owner = "client!jb", name = "w", descriptor = "B")
	private final byte flowObfuscator3 = 8;

	@OriginalMember(owner = "client!jb", name = "x", descriptor = "B")
	private final byte flowObfuscator4 = 6;

	@OriginalMember(owner = "client!jb", name = "y", descriptor = "B")
	private final byte flowObfuscator5 = 2;

	@OriginalMember(owner = "client!jb", name = "z", descriptor = "I")
	private final int flowObfuscator1 = -708;

	@OriginalMember(owner = "client!jb", name = "A", descriptor = "I")
	private final int flowObfuscator2 = 997;

}
