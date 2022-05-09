import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!bc")
public final class Class3 {

	@OriginalMember(owner = "client!bc", name = "h", descriptor = "Ljava/lang/String;")
	public String aString2;

	@OriginalMember(owner = "client!bc", name = "i", descriptor = "[B")
	public byte[] aByteArray2;

	@OriginalMember(owner = "client!bc", name = "k", descriptor = "[I")
	private int[] anIntArray9;

	@OriginalMember(owner = "client!bc", name = "l", descriptor = "[I")
	private int[] anIntArray10;

	@OriginalMember(owner = "client!bc", name = "s", descriptor = "[I")
	private int[] anIntArray11;

	@OriginalMember(owner = "client!bc", name = "t", descriptor = "[I")
	private int[] anIntArray12;

	@OriginalMember(owner = "client!bc", name = "u", descriptor = "[Ljava/lang/String;")
	public String[] aStringArray2;

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "Z")
	private final boolean aBoolean18 = false;

	@OriginalMember(owner = "client!bc", name = "g", descriptor = "J")
	public long aLong5 = -1L;

	@OriginalMember(owner = "client!bc", name = "j", descriptor = "B")
	public byte aByte4 = 1;

	@OriginalMember(owner = "client!bc", name = "m", descriptor = "I")
	public int anInt69 = -1;

	@OriginalMember(owner = "client!bc", name = "n", descriptor = "I")
	public int anInt70 = -1;

	@OriginalMember(owner = "client!bc", name = "o", descriptor = "I")
	public int anInt71 = -1;

	@OriginalMember(owner = "client!bc", name = "p", descriptor = "I")
	public int anInt72 = -1;

	@OriginalMember(owner = "client!bc", name = "q", descriptor = "I")
	public int anInt73 = -1;

	@OriginalMember(owner = "client!bc", name = "r", descriptor = "Z")
	private boolean aBoolean19 = false;

	@OriginalMember(owner = "client!bc", name = "v", descriptor = "I")
	private int anInt74 = -1;

	@OriginalMember(owner = "client!bc", name = "w", descriptor = "I")
	private int anInt75 = -1;

	@OriginalMember(owner = "client!bc", name = "x", descriptor = "I")
	private int anInt76 = -1;

	@OriginalMember(owner = "client!bc", name = "y", descriptor = "Z")
	public boolean aBoolean20 = true;

	@OriginalMember(owner = "client!bc", name = "z", descriptor = "I")
	public int anInt77 = -1;

	@OriginalMember(owner = "client!bc", name = "A", descriptor = "I")
	private int anInt78 = 128;

	@OriginalMember(owner = "client!bc", name = "B", descriptor = "I")
	private int anInt79 = 128;

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(ZLclient!kb;)V")
	public final void method33(@OriginalArg(1) Class1_Sub3_Sub3 arg0) {
		while (true) {
			@Pc(10) int local10 = arg0.method391();
			if (local10 == 0) {
				return;
			}
			@Pc(19) int local19;
			@Pc(25) int local25;
			if (local10 == 1) {
				local19 = arg0.method391();
				this.anIntArray9 = new int[local19];
				for (local25 = 0; local25 < local19; local25++) {
					this.anIntArray9[local25] = arg0.method393();
				}
			} else if (local10 == 2) {
				this.aString2 = arg0.method398();
			} else if (local10 == 3) {
				this.aByteArray2 = arg0.method399();
			} else if (local10 == 12) {
				this.aByte4 = arg0.method392();
			} else if (local10 == 13) {
				this.anInt69 = arg0.method393();
			} else if (local10 == 14) {
				this.anInt70 = arg0.method393();
			} else if (local10 == 16) {
				this.aBoolean19 = true;
			} else if (local10 == 17) {
				this.anInt70 = arg0.method393();
				this.anInt71 = arg0.method393();
				this.anInt72 = arg0.method393();
				this.anInt73 = arg0.method393();
			} else if (local10 >= 30 && local10 < 40) {
				if (this.aStringArray2 == null) {
					this.aStringArray2 = new String[5];
				}
				this.aStringArray2[local10 - 30] = arg0.method398();
				if (this.aStringArray2[local10 - 30].equalsIgnoreCase("hidden")) {
					this.aStringArray2[local10 - 30] = null;
				}
			} else if (local10 == 40) {
				local19 = arg0.method391();
				this.anIntArray11 = new int[local19];
				this.anIntArray12 = new int[local19];
				for (local25 = 0; local25 < local19; local25++) {
					this.anIntArray11[local25] = arg0.method393();
					this.anIntArray12[local25] = arg0.method393();
				}
			} else if (local10 == 60) {
				local19 = arg0.method391();
				this.anIntArray10 = new int[local19];
				for (local25 = 0; local25 < local19; local25++) {
					this.anIntArray10[local25] = arg0.method393();
				}
			} else if (local10 == 90) {
				this.anInt74 = arg0.method393();
			} else if (local10 == 91) {
				this.anInt75 = arg0.method393();
			} else if (local10 == 92) {
				this.anInt76 = arg0.method393();
			} else if (local10 == 93) {
				this.aBoolean20 = false;
			} else if (local10 == 95) {
				this.anInt77 = arg0.method393();
			} else if (local10 == 97) {
				this.anInt78 = arg0.method393();
			} else if (local10 == 98) {
				this.anInt79 = arg0.method393();
			}
		}
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(II[I)Lclient!eb;")
	public final Class1_Sub3_Sub1 method34(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[] arg2) {
		@Pc(9) Class1_Sub3_Sub1 local9 = (Class1_Sub3_Sub1) Static3.aClass35_3.method527(this.aLong5);
		if (local9 == null) {
			@Pc(16) Class1_Sub3_Sub1[] local16 = new Class1_Sub3_Sub1[this.anIntArray9.length];
			for (@Pc(18) int local18 = 0; local18 < this.anIntArray9.length; local18++) {
				local16[local18] = new Class1_Sub3_Sub1(false, this.anIntArray9[local18]);
			}
			if (local16.length == 1) {
				local9 = local16[0];
			} else {
				local9 = new Class1_Sub3_Sub1(0, local16, local16.length);
			}
			if (this.anIntArray11 != null) {
				for (@Pc(60) int local60 = 0; local60 < this.anIntArray11.length; local60++) {
					local9.method237(this.anIntArray11[local60], this.anIntArray12[local60]);
				}
			}
			local9.method230();
			local9.method240(64, 850, -30, -50, -30, true);
			Static3.aClass35_3.method528(this.aLong5, local9);
		}
		@Pc(107) Class1_Sub3_Sub1 local107 = new Class1_Sub3_Sub1(0, local9, !this.aBoolean19);
		if (arg0 != -1 && arg1 != -1) {
			local107.method232(arg1, arg0, arg2);
		} else if (arg0 != -1) {
			local107.method231(arg0);
		}
		if (this.anInt78 != 128 || this.anInt79 != 128) {
			local107.method239(this.anInt78, this.anInt79, this.anInt78);
		}
		local107.method227();
		local107.anIntArrayArray7 = null;
		local107.anIntArrayArray6 = null;
		if (this.aByte4 == 1) {
			local107.aBoolean84 = true;
		}
		return local107;
	}

	@OriginalMember(owner = "client!bc", name = "b", descriptor = "(Z)Lclient!eb;")
	public final Class1_Sub3_Sub1 method35() {
		if (this.anIntArray10 == null) {
			return null;
		}
		@Pc(17) Class1_Sub3_Sub1[] local17 = new Class1_Sub3_Sub1[this.anIntArray10.length];
		for (@Pc(19) int local19 = 0; local19 < this.anIntArray10.length; local19++) {
			local17[local19] = new Class1_Sub3_Sub1(false, this.anIntArray10[local19]);
		}
		@Pc(46) Class1_Sub3_Sub1 local46;
		if (local17.length == 1) {
			local46 = local17[0];
		} else {
			local46 = new Class1_Sub3_Sub1(0, local17, local17.length);
		}
		if (this.anIntArray11 != null) {
			for (@Pc(61) int local61 = 0; local61 < this.anIntArray11.length; local61++) {
				local46.method237(this.anIntArray11[local61], this.anIntArray12[local61]);
			}
		}
		return local46;
	}
}
