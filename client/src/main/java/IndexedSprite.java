import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ib")
public final class IndexedSprite extends Draw2D {

	@OriginalMember(owner = "client!ib", name = "v", descriptor = "Z")
	private final boolean aBoolean116 = false;

	@OriginalMember(owner = "client!ib", name = "w", descriptor = "I")
	private int anInt512 = 45861;

	@OriginalMember(owner = "client!ib", name = "x", descriptor = "B")
	private final byte aByte24 = 5;

	@OriginalMember(owner = "client!ib", name = "y", descriptor = "Z")
	private final boolean aBoolean117 = true;

	@OriginalMember(owner = "client!ib", name = "F", descriptor = "I")
	public int anInt517;

	@OriginalMember(owner = "client!ib", name = "G", descriptor = "I")
	private int anInt518;

	@OriginalMember(owner = "client!ib", name = "A", descriptor = "[I")
	public final int[] anIntArray177;

	@OriginalMember(owner = "client!ib", name = "D", descriptor = "I")
	public int anInt515;

	@OriginalMember(owner = "client!ib", name = "E", descriptor = "I")
	public int anInt516;

	@OriginalMember(owner = "client!ib", name = "B", descriptor = "I")
	public int anInt513;

	@OriginalMember(owner = "client!ib", name = "C", descriptor = "I")
	public int anInt514;

	@OriginalMember(owner = "client!ib", name = "z", descriptor = "[B")
	public byte[] aByteArray6;

	@OriginalMember(owner = "client!ib", name = "<init>", descriptor = "(Lclient!ub;Ljava/lang/String;I)V")
	public IndexedSprite(@OriginalArg(0) FileArchive arg0, @OriginalArg(1) String arg1, @OriginalArg(2) int arg2) {
		@Pc(32) Buffer local32 = new Buffer(363, arg0.read(arg1 + ".dat", null));
		@Pc(42) Buffer local42 = new Buffer(363, arg0.read("index.dat", null));
		local42.anInt561 = local32.g2();
		this.anInt517 = local42.g2();
		this.anInt518 = local42.g2();
		@Pc(57) int local57 = local42.g1();
		this.anIntArray177 = new int[local57];
		for (@Pc(63) int local63 = 0; local63 < local57 - 1; local63++) {
			this.anIntArray177[local63 + 1] = local42.g3();
		}
		for (@Pc(81) int local81 = 0; local81 < arg2; local81++) {
			local42.anInt561 += 2;
			local32.anInt561 += local42.g2() * local42.g2();
			local42.anInt561++;
		}
		this.anInt515 = local42.g1();
		this.anInt516 = local42.g1();
		this.anInt513 = local42.g2();
		this.anInt514 = local42.g2();
		@Pc(128) int local128 = local42.g1();
		@Pc(134) int local134 = this.anInt513 * this.anInt514;
		this.aByteArray6 = new byte[local134];
		@Pc(142) int local142;
		if (local128 == 0) {
			for (local142 = 0; local142 < local134; local142++) {
				this.aByteArray6[local142] = local32.g1s();
			}
		} else if (local128 == 1) {
			for (local142 = 0; local142 < this.anInt513; local142++) {
				for (@Pc(164) int local164 = 0; local164 < this.anInt514; local164++) {
					this.aByteArray6[local142 + local164 * this.anInt513] = local32.g1s();
				}
			}
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(Z)V")
	public final void shrink(@OriginalArg(0) boolean arg0) {
		this.anInt517 /= 2;
		this.anInt518 /= 2;
		@Pc(20) byte[] local20 = new byte[this.anInt517 * this.anInt518];
		@Pc(22) int local22 = 0;
		for (@Pc(24) int local24 = 0; local24 < this.anInt514; local24++) {
			for (@Pc(28) int local28 = 0; local28 < this.anInt513; local28++) {
				local20[(local28 + this.anInt515 >> 1) + (local24 + this.anInt516 >> 1) * this.anInt517] = this.aByteArray6[local22++];
			}
		}
		if (arg0) {
			this.anInt512 = 197;
		}
		this.aByteArray6 = local20;
		this.anInt513 = this.anInt517;
		this.anInt514 = this.anInt518;
		this.anInt515 = 0;
		this.anInt516 = 0;
	}

	@OriginalMember(owner = "client!ib", name = "c", descriptor = "(I)V")
	public final void crop() {
		if (this.anInt513 == this.anInt517 && this.anInt514 == this.anInt518) {
			return;
		}
		@Pc(19) byte[] local19 = new byte[this.anInt517 * this.anInt518];
		@Pc(21) int local21 = 0;
		for (@Pc(33) int local33 = 0; local33 < this.anInt514; local33++) {
			for (@Pc(37) int local37 = 0; local37 < this.anInt513; local37++) {
				local19[local37 + this.anInt515 + (local33 + this.anInt516) * this.anInt517] = this.aByteArray6[local21++];
			}
		}
		this.aByteArray6 = local19;
		this.anInt513 = this.anInt517;
		this.anInt514 = this.anInt518;
		this.anInt515 = 0;
		this.anInt516 = 0;
	}

	@OriginalMember(owner = "client!ib", name = "d", descriptor = "(I)V")
	public final void flipHorizontally() {
		@Pc(8) byte[] local8 = new byte[this.anInt513 * this.anInt514];
		@Pc(10) int local10 = 0;
		for (@Pc(12) int local12 = 0; local12 < this.anInt514; local12++) {
			for (@Pc(19) int local19 = this.anInt513 - 1; local19 >= 0; local19--) {
				local8[local10++] = this.aByteArray6[local19 + local12 * this.anInt513];
			}
		}
		this.aByteArray6 = local8;
		this.anInt515 = this.anInt517 - this.anInt513 - this.anInt515;
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(B)V")
	public final void flipVertically() {
		@Pc(8) byte[] local8 = new byte[this.anInt513 * this.anInt514];
		@Pc(20) int local20 = 0;
		for (@Pc(25) int local25 = this.anInt514 - 1; local25 >= 0; local25--) {
			for (@Pc(29) int local29 = 0; local29 < this.anInt513; local29++) {
				local8[local20++] = this.aByteArray6[local29 + local25 * this.anInt513];
			}
		}
		this.aByteArray6 = local8;
		this.anInt516 = this.anInt518 - this.anInt514 - this.anInt516;
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(IIIZ)V")
	public final void translate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(3) int local3 = 0; local3 < this.anIntArray177.length; local3++) {
			@Pc(14) int local14 = this.anIntArray177[local3] >> 16 & 0xFF;
			local14 += arg0;
			if (local14 < 0) {
				local14 = 0;
			} else if (local14 > 255) {
				local14 = 255;
			}
			@Pc(38) int local38 = this.anIntArray177[local3] >> 8 & 0xFF;
			local38 += arg1;
			if (local38 < 0) {
				local38 = 0;
			} else if (local38 > 255) {
				local38 = 255;
			}
			@Pc(60) int local60 = this.anIntArray177[local3] & 0xFF;
			local60 += arg2;
			if (local60 < 0) {
				local60 = 0;
			} else if (local60 > 255) {
				local60 = 255;
			}
			this.anIntArray177[local3] = (local14 << 16) + (local38 << 8) + local60;
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(IIZ)V")
	public final void draw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		arg1 += this.anInt515;
		arg0 += this.anInt516;
		@Pc(15) int local15 = arg1 + arg0 * Draw2D.anInt528;
		@Pc(17) int local17 = 0;
		@Pc(20) int local20 = this.anInt514;
		@Pc(23) int local23 = this.anInt513;
		@Pc(27) int local27 = Draw2D.anInt528 - local23;
		@Pc(29) int local29 = 0;
		@Pc(36) int local36;
		if (arg0 < Draw2D.anInt530) {
			local36 = Draw2D.anInt530 - arg0;
			local20 -= local36;
			arg0 = Draw2D.anInt530;
			local17 = local36 * local23 + 0;
			local15 += local36 * Draw2D.anInt528;
		}
		if (arg0 + local20 > Draw2D.anInt531) {
			local20 -= arg0 + local20 - Draw2D.anInt531;
		}
		if (arg1 < Draw2D.anInt532) {
			local36 = Draw2D.anInt532 - arg1;
			local23 -= local36;
			arg1 = Draw2D.anInt532;
			local17 += local36;
			local15 += local36;
			local29 = local36 + 0;
			local27 += local36;
		}
		if (arg1 + local23 > Draw2D.anInt533) {
			local36 = arg1 + local23 - Draw2D.anInt533;
			local23 -= local36;
			local29 += local36;
			local27 += local36;
		}
		if (local23 > 0 && local20 > 0) {
			this.copyImage(Draw2D.anIntArray178, local17, local29, this.aByteArray6, local20, local23, local15, local27, this.anIntArray177);
		}
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "([III[BIIIII[I)V")
	private void copyImage(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) byte[] arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int[] arg8) {
		@Pc(6) int local6 = -(arg5 >> 2);
		@Pc(11) int local11 = -(arg5 & 0x3);
		for (@Pc(17) int local17 = -arg4; local17 < 0; local17++) {
			for (@Pc(21) int local21 = local6; local21 < 0; local21++) {
				@Pc(28) byte local28 = arg3[arg1++];
				if (local28 == 0) {
					arg6++;
				} else {
					arg0[arg6++] = arg8[local28 & 0xFF];
				}
				local28 = arg3[arg1++];
				if (local28 == 0) {
					arg6++;
				} else {
					arg0[arg6++] = arg8[local28 & 0xFF];
				}
				local28 = arg3[arg1++];
				if (local28 == 0) {
					arg6++;
				} else {
					arg0[arg6++] = arg8[local28 & 0xFF];
				}
				local28 = arg3[arg1++];
				if (local28 == 0) {
					arg6++;
				} else {
					arg0[arg6++] = arg8[local28 & 0xFF];
				}
			}
			for (@Pc(104) int local104 = local11; local104 < 0; local104++) {
				@Pc(111) byte local111 = arg3[arg1++];
				if (local111 == 0) {
					arg6++;
				} else {
					arg0[arg6++] = arg8[local111 & 0xFF];
				}
			}
			arg6 += arg7;
			arg1 += arg2;
		}
	}
}
