import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ec")
public final class Class9 {

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "I")
	private int anInt379;

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "Z")
	private final boolean aBoolean87 = false;

	@OriginalMember(owner = "client!ec", name = "c", descriptor = "B")
	private final byte aByte21 = 12;

	@OriginalMember(owner = "client!ec", name = "d", descriptor = "I")
	private final int anInt380 = 27808;

	@OriginalMember(owner = "client!ec", name = "e", descriptor = "Z")
	private final boolean aBoolean88 = true;

	@OriginalMember(owner = "client!ec", name = "f", descriptor = "I")
	private final int anInt381 = 0;

	@OriginalMember(owner = "client!ec", name = "g", descriptor = "I")
	private final int anInt382 = 0;

	@OriginalMember(owner = "client!ec", name = "h", descriptor = "I")
	private final int anInt383;

	@OriginalMember(owner = "client!ec", name = "i", descriptor = "I")
	private final int anInt384;

	@OriginalMember(owner = "client!ec", name = "j", descriptor = "[[I")
	public final int[][] anIntArrayArray10;

	@OriginalMember(owner = "client!ec", name = "<init>", descriptor = "(III)V")
	public Class9(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		this.anInt383 = arg0;
		this.anInt384 = arg2;
		this.anIntArrayArray10 = new int[this.anInt383][this.anInt384];
		this.method249();
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(B)V")
	public final void method249() {
		for (@Pc(3) int local3 = 0; local3 < this.anInt383; local3++) {
			for (@Pc(7) int local7 = 0; local7 < this.anInt384; local7++) {
				if (local3 == 0 || local7 == 0 || local3 == this.anInt383 - 1 || local7 == this.anInt384 - 1) {
					this.anIntArrayArray10[local3][local7] = 16777215;
				} else {
					this.anIntArrayArray10[local3][local7] = 0;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(ZIIIZI)V")
	public final void method250(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3, @OriginalArg(5) int arg4) {
		@Pc(4) int local4 = arg2 - this.anInt381;
		@Pc(19) int local19 = arg1 - this.anInt382;
		if (arg4 == 0) {
			if (arg0 == 0) {
				this.method253(local4, local19, 128);
				this.method253(local4 - 1, local19, 8);
			}
			if (arg0 == 1) {
				this.method253(local4, local19, 2);
				this.method253(local4, local19 + 1, 32);
			}
			if (arg0 == 2) {
				this.method253(local4, local19, 8);
				this.method253(local4 + 1, local19, 128);
			}
			if (arg0 == 3) {
				this.method253(local4, local19, 32);
				this.method253(local4, local19 - 1, 2);
			}
		}
		if (arg4 == 1 || arg4 == 3) {
			if (arg0 == 0) {
				this.method253(local4, local19, 1);
				this.method253(local4 - 1, local19 + 1, 16);
			}
			if (arg0 == 1) {
				this.method253(local4, local19, 4);
				this.method253(local4 + 1, local19 + 1, 64);
			}
			if (arg0 == 2) {
				this.method253(local4, local19, 16);
				this.method253(local4 + 1, local19 - 1, 1);
			}
			if (arg0 == 3) {
				this.method253(local4, local19, 64);
				this.method253(local4 - 1, local19 - 1, 4);
			}
		}
		if (arg4 == 2) {
			if (arg0 == 0) {
				this.method253(local4, local19, 130);
				this.method253(local4 - 1, local19, 8);
				this.method253(local4, local19 + 1, 32);
			}
			if (arg0 == 1) {
				this.method253(local4, local19, 10);
				this.method253(local4, local19 + 1, 32);
				this.method253(local4 + 1, local19, 128);
			}
			if (arg0 == 2) {
				this.method253(local4, local19, 40);
				this.method253(local4 + 1, local19, 128);
				this.method253(local4, local19 - 1, 2);
			}
			if (arg0 == 3) {
				this.method253(local4, local19, 160);
				this.method253(local4, local19 - 1, 2);
				this.method253(local4 - 1, local19, 8);
			}
		}
		if (!arg3) {
			return;
		}
		if (arg4 == 0) {
			if (arg0 == 0) {
				this.method253(local4, local19, 65536);
				this.method253(local4 - 1, local19, 4096);
			}
			if (arg0 == 1) {
				this.method253(local4, local19, 1024);
				this.method253(local4, local19 + 1, 16384);
			}
			if (arg0 == 2) {
				this.method253(local4, local19, 4096);
				this.method253(local4 + 1, local19, 65536);
			}
			if (arg0 == 3) {
				this.method253(local4, local19, 16384);
				this.method253(local4, local19 - 1, 1024);
			}
		}
		if (arg4 == 1 || arg4 == 3) {
			if (arg0 == 0) {
				this.method253(local4, local19, 512);
				this.method253(local4 - 1, local19 + 1, 8192);
			}
			if (arg0 == 1) {
				this.method253(local4, local19, 2048);
				this.method253(local4 + 1, local19 + 1, 32768);
			}
			if (arg0 == 2) {
				this.method253(local4, local19, 8192);
				this.method253(local4 + 1, local19 - 1, 512);
			}
			if (arg0 == 3) {
				this.method253(local4, local19, 32768);
				this.method253(local4 - 1, local19 - 1, 2048);
			}
		}
		if (arg4 != 2) {
			return;
		}
		if (arg0 == 0) {
			this.method253(local4, local19, 66560);
			this.method253(local4 - 1, local19, 4096);
			this.method253(local4, local19 + 1, 16384);
		}
		if (arg0 == 1) {
			this.method253(local4, local19, 5120);
			this.method253(local4, local19 + 1, 16384);
			this.method253(local4 + 1, local19, 65536);
		}
		if (arg0 == 2) {
			this.method253(local4, local19, 20480);
			this.method253(local4 + 1, local19, 65536);
			this.method253(local4, local19 - 1, 1024);
		}
		if (arg0 == 3) {
			this.method253(local4, local19, 81920);
			this.method253(local4, local19 - 1, 1024);
			this.method253(local4 - 1, local19, 4096);
			return;
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIIZ)V")
	public final void method251(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) boolean arg5) {
		@Pc(3) int local3 = 256;
		if (arg5) {
			local3 = 131328;
		}
		@Pc(14) int local14 = arg3 - this.anInt381;
		@Pc(19) int local19 = arg4 - this.anInt382;
		@Pc(27) int local27;
		if (arg0 == 1 || arg0 == 3) {
			local27 = arg2;
			arg2 = arg1;
			arg1 = local27;
		}
		for (local27 = local14; local27 < local14 + arg2; local27++) {
			if (local27 >= 0 && local27 < this.anInt383) {
				for (@Pc(43) int local43 = local19; local43 < local19 + arg1; local43++) {
					if (local43 >= 0 && local43 < this.anInt384) {
						this.method253(local27, local43, local3);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(BII)V")
	public final void method252(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (this.aByte21 == 12) {
			@Pc(9) int local9 = arg1 - this.anInt381;
			@Pc(14) int local14 = arg0 - this.anInt382;
			this.anIntArrayArray10[local9][local14] |= 0x200000;
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(III)V")
	private void method253(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		this.anIntArrayArray10[arg0][arg1] |= arg2;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(ZIIIII)V")
	public final void method254(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4) {
		@Pc(8) int local8 = arg2 - this.anInt381;
		@Pc(13) int local13 = arg3 - this.anInt382;
		if (arg4 == 0) {
			if (arg1 == 0) {
				this.method256(local13, local8, 128);
				this.method256(local13, local8 - 1, 8);
			}
			if (arg1 == 1) {
				this.method256(local13, local8, 2);
				this.method256(local13 + 1, local8, 32);
			}
			if (arg1 == 2) {
				this.method256(local13, local8, 8);
				this.method256(local13, local8 + 1, 128);
			}
			if (arg1 == 3) {
				this.method256(local13, local8, 32);
				this.method256(local13 - 1, local8, 2);
			}
		}
		if (arg4 == 1 || arg4 == 3) {
			if (arg1 == 0) {
				this.method256(local13, local8, 1);
				this.method256(local13 + 1, local8 - 1, 16);
			}
			if (arg1 == 1) {
				this.method256(local13, local8, 4);
				this.method256(local13 + 1, local8 + 1, 64);
			}
			if (arg1 == 2) {
				this.method256(local13, local8, 16);
				this.method256(local13 - 1, local8 + 1, 1);
			}
			if (arg1 == 3) {
				this.method256(local13, local8, 64);
				this.method256(local13 - 1, local8 - 1, 4);
			}
		}
		if (arg4 == 2) {
			if (arg1 == 0) {
				this.method256(local13, local8, 130);
				this.method256(local13, local8 - 1, 8);
				this.method256(local13 + 1, local8, 32);
			}
			if (arg1 == 1) {
				this.method256(local13, local8, 10);
				this.method256(local13 + 1, local8, 32);
				this.method256(local13, local8 + 1, 128);
			}
			if (arg1 == 2) {
				this.method256(local13, local8, 40);
				this.method256(local13, local8 + 1, 128);
				this.method256(local13 - 1, local8, 2);
			}
			if (arg1 == 3) {
				this.method256(local13, local8, 160);
				this.method256(local13 - 1, local8, 2);
				this.method256(local13, local8 - 1, 8);
			}
		}
		if (!arg0) {
			return;
		}
		if (arg4 == 0) {
			if (arg1 == 0) {
				this.method256(local13, local8, 65536);
				this.method256(local13, local8 - 1, 4096);
			}
			if (arg1 == 1) {
				this.method256(local13, local8, 1024);
				this.method256(local13 + 1, local8, 16384);
			}
			if (arg1 == 2) {
				this.method256(local13, local8, 4096);
				this.method256(local13, local8 + 1, 65536);
			}
			if (arg1 == 3) {
				this.method256(local13, local8, 16384);
				this.method256(local13 - 1, local8, 1024);
			}
		}
		if (arg4 == 1 || arg4 == 3) {
			if (arg1 == 0) {
				this.method256(local13, local8, 512);
				this.method256(local13 + 1, local8 - 1, 8192);
			}
			if (arg1 == 1) {
				this.method256(local13, local8, 2048);
				this.method256(local13 + 1, local8 + 1, 32768);
			}
			if (arg1 == 2) {
				this.method256(local13, local8, 8192);
				this.method256(local13 - 1, local8 + 1, 512);
			}
			if (arg1 == 3) {
				this.method256(local13, local8, 32768);
				this.method256(local13 - 1, local8 - 1, 2048);
			}
		}
		if (arg4 != 2) {
			return;
		}
		if (arg1 == 0) {
			this.method256(local13, local8, 66560);
			this.method256(local13, local8 - 1, 4096);
			this.method256(local13 + 1, local8, 16384);
		}
		if (arg1 == 1) {
			this.method256(local13, local8, 5120);
			this.method256(local13 + 1, local8, 16384);
			this.method256(local13, local8 + 1, 65536);
		}
		if (arg1 == 2) {
			this.method256(local13, local8, 20480);
			this.method256(local13, local8 + 1, 65536);
			this.method256(local13 - 1, local8, 1024);
		}
		if (arg1 == 3) {
			this.method256(local13, local8, 81920);
			this.method256(local13 - 1, local8, 1024);
			this.method256(local13, local8 - 1, 4096);
			return;
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIZZI)V")
	public final void method255(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) boolean arg4, @OriginalArg(6) int arg5) {
		@Pc(3) int local3 = 256;
		if (arg4) {
			local3 = 131328;
		}
		@Pc(14) int local14 = arg1 - this.anInt381;
		@Pc(19) int local19 = arg0 - this.anInt382;
		@Pc(32) int local32;
		if (arg2 == 1 || arg2 == 3) {
			local32 = arg3;
			arg3 = arg5;
			arg5 = local32;
		}
		for (local32 = local14; local32 < local14 + arg3; local32++) {
			if (local32 >= 0 && local32 < this.anInt383) {
				for (@Pc(48) int local48 = local19; local48 < local19 + arg5; local48++) {
					if (local48 >= 0 && local48 < this.anInt384) {
						this.method256(local48, local32, local3);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IBII)V")
	private void method256(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		this.anIntArrayArray10[arg1][arg0] &= 16777215 - arg2;
	}

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "(III)V")
	public final void method257(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(7) int local7 = arg1 - this.anInt381;
		@Pc(12) int local12 = arg0 - this.anInt382;
		this.anIntArrayArray10[local7][local12] &= 0xDFFFFF;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIII)Z")
	public final boolean method258(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		if (arg5 == arg4 && arg3 == arg1) {
			return true;
		}
		@Pc(12) int local12 = arg5 - this.anInt381;
		@Pc(17) int local17 = arg3 - this.anInt382;
		@Pc(29) int local29 = arg4 - this.anInt381;
		@Pc(34) int local34 = arg1 - this.anInt382;
		if (arg2 == 0) {
			if (arg0 == 0) {
				if (local12 == local29 - 1 && local17 == local34) {
					return true;
				}
				if (local12 == local29 && local17 == local34 + 1 && (this.anIntArrayArray10[local12][local17] & 0x280120) == 0) {
					return true;
				}
				if (local12 == local29 && local17 == local34 - 1 && (this.anIntArrayArray10[local12][local17] & 0x280102) == 0) {
					return true;
				}
			} else if (arg0 == 1) {
				if (local12 == local29 && local17 == local34 + 1) {
					return true;
				}
				if (local12 == local29 - 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x280108) == 0) {
					return true;
				}
				if (local12 == local29 + 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x280180) == 0) {
					return true;
				}
			} else if (arg0 == 2) {
				if (local12 == local29 + 1 && local17 == local34) {
					return true;
				}
				if (local12 == local29 && local17 == local34 + 1 && (this.anIntArrayArray10[local12][local17] & 0x280120) == 0) {
					return true;
				}
				if (local12 == local29 && local17 == local34 - 1 && (this.anIntArrayArray10[local12][local17] & 0x280102) == 0) {
					return true;
				}
			} else if (arg0 == 3) {
				if (local12 == local29 && local17 == local34 - 1) {
					return true;
				}
				if (local12 == local29 - 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x280108) == 0) {
					return true;
				}
				if (local12 == local29 + 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x280180) == 0) {
					return true;
				}
			}
		}
		if (arg2 == 2) {
			if (arg0 == 0) {
				if (local12 == local29 - 1 && local17 == local34) {
					return true;
				}
				if (local12 == local29 && local17 == local34 + 1) {
					return true;
				}
				if (local12 == local29 + 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x280180) == 0) {
					return true;
				}
				if (local12 == local29 && local17 == local34 - 1 && (this.anIntArrayArray10[local12][local17] & 0x280102) == 0) {
					return true;
				}
			} else if (arg0 == 1) {
				if (local12 == local29 - 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x280108) == 0) {
					return true;
				}
				if (local12 == local29 && local17 == local34 + 1) {
					return true;
				}
				if (local12 == local29 + 1 && local17 == local34) {
					return true;
				}
				if (local12 == local29 && local17 == local34 - 1 && (this.anIntArrayArray10[local12][local17] & 0x280102) == 0) {
					return true;
				}
			} else if (arg0 == 2) {
				if (local12 == local29 - 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x280108) == 0) {
					return true;
				}
				if (local12 == local29 && local17 == local34 + 1 && (this.anIntArrayArray10[local12][local17] & 0x280120) == 0) {
					return true;
				}
				if (local12 == local29 + 1 && local17 == local34) {
					return true;
				}
				if (local12 == local29 && local17 == local34 - 1) {
					return true;
				}
			} else if (arg0 == 3) {
				if (local12 == local29 - 1 && local17 == local34) {
					return true;
				}
				if (local12 == local29 && local17 == local34 + 1 && (this.anIntArrayArray10[local12][local17] & 0x280120) == 0) {
					return true;
				}
				if (local12 == local29 + 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x280180) == 0) {
					return true;
				}
				if (local12 == local29 && local17 == local34 - 1) {
					return true;
				}
			}
		}
		if (arg2 == 9) {
			if (local12 == local29 && local17 == local34 + 1 && (this.anIntArrayArray10[local12][local17] & 0x20) == 0) {
				return true;
			}
			if (local12 == local29 && local17 == local34 - 1 && (this.anIntArrayArray10[local12][local17] & 0x2) == 0) {
				return true;
			}
			if (local12 == local29 - 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x8) == 0) {
				return true;
			}
			if (local12 == local29 + 1 && local17 == local34 && (this.anIntArrayArray10[local12][local17] & 0x80) == 0) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "(IIIIIII)Z")
	public final boolean method259(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		if (arg2 != this.anInt380) {
			this.anInt379 = -218;
		}
		if (arg3 == arg4 && arg5 == arg6) {
			return true;
		}
		@Pc(19) int local19 = arg3 - this.anInt381;
		@Pc(24) int local24 = arg5 - this.anInt382;
		@Pc(29) int local29 = arg4 - this.anInt381;
		@Pc(34) int local34 = arg6 - this.anInt382;
		if (arg1 == 6 || arg1 == 7) {
			if (arg1 == 7) {
				arg0 = arg0 + 2 & 0x3;
			}
			if (arg0 == 0) {
				if (local19 == local29 + 1 && local24 == local34 && (this.anIntArrayArray10[local19][local24] & 0x80) == 0) {
					return true;
				}
				if (local19 == local29 && local24 == local34 - 1 && (this.anIntArrayArray10[local19][local24] & 0x2) == 0) {
					return true;
				}
			} else if (arg0 == 1) {
				if (local19 == local29 - 1 && local24 == local34 && (this.anIntArrayArray10[local19][local24] & 0x8) == 0) {
					return true;
				}
				if (local19 == local29 && local24 == local34 - 1 && (this.anIntArrayArray10[local19][local24] & 0x2) == 0) {
					return true;
				}
			} else if (arg0 == 2) {
				if (local19 == local29 - 1 && local24 == local34 && (this.anIntArrayArray10[local19][local24] & 0x8) == 0) {
					return true;
				}
				if (local19 == local29 && local24 == local34 + 1 && (this.anIntArrayArray10[local19][local24] & 0x20) == 0) {
					return true;
				}
			} else if (arg0 == 3) {
				if (local19 == local29 + 1 && local24 == local34 && (this.anIntArrayArray10[local19][local24] & 0x80) == 0) {
					return true;
				}
				if (local19 == local29 && local24 == local34 + 1 && (this.anIntArrayArray10[local19][local24] & 0x20) == 0) {
					return true;
				}
			}
		}
		if (arg1 == 8) {
			if (local19 == local29 && local24 == local34 + 1 && (this.anIntArrayArray10[local19][local24] & 0x20) == 0) {
				return true;
			}
			if (local19 == local29 && local24 == local34 - 1 && (this.anIntArrayArray10[local19][local24] & 0x2) == 0) {
				return true;
			}
			if (local19 == local29 - 1 && local24 == local34 && (this.anIntArrayArray10[local19][local24] & 0x8) == 0) {
				return true;
			}
			if (local19 == local29 + 1 && local24 == local34 && (this.anIntArrayArray10[local19][local24] & 0x80) == 0) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIIII)Z")
	public final boolean method260(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(5) int local5 = arg3 + arg6 - 1;
		@Pc(11) int local11 = arg5 + arg1 - 1;
		if (arg2 >= arg3 && arg2 <= local5 && arg0 >= arg5 && arg0 <= local11) {
			return true;
		} else if (arg2 == arg3 - 1 && arg0 >= arg5 && arg0 <= local11 && (this.anIntArrayArray10[arg2 - this.anInt381][arg0 - this.anInt382] & 0x8) == 0 && (arg4 & 0x8) == 0) {
			return true;
		} else if (arg2 == local5 + 1 && arg0 >= arg5 && arg0 <= local11 && (this.anIntArrayArray10[arg2 - this.anInt381][arg0 - this.anInt382] & 0x80) == 0 && (arg4 & 0x2) == 0) {
			return true;
		} else if (arg0 == arg5 - 1 && arg2 >= arg3 && arg2 <= local5 && (this.anIntArrayArray10[arg2 - this.anInt381][arg0 - this.anInt382] & 0x2) == 0 && (arg4 & 0x4) == 0) {
			return true;
		} else {
			return arg0 == local11 + 1 && arg2 >= arg3 && arg2 <= local5 && (this.anIntArrayArray10[arg2 - this.anInt381][arg0 - this.anInt382] & 0x20) == 0 && (arg4 & 0x1) == 0;
		}
	}
}
