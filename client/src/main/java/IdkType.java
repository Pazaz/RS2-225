import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!gc")
public final class IdkType {

	@OriginalMember(owner = "client!gc", name = "b", descriptor = "I")
	private static final int flowObfuscator2 = 473;

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "I")
	public static int flowObfuscator1;

	@OriginalMember(owner = "client!gc", name = "d", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!gc", name = "e", descriptor = "[Lclient!gc;")
	public static IdkType[] instances;

	@OriginalMember(owner = "client!gc", name = "g", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!gc", name = "c", descriptor = "Z")
	private final boolean flowObfuscator3 = false;

	@OriginalMember(owner = "client!gc", name = "f", descriptor = "I")
	public int type = -1;

	@OriginalMember(owner = "client!gc", name = "h", descriptor = "[I")
	private final int[] recol_s = new int[6];

	@OriginalMember(owner = "client!gc", name = "i", descriptor = "[I")
	private final int[] recol_d = new int[6];

	@OriginalMember(owner = "client!gc", name = "j", descriptor = "[I")
	private final int[] heads = new int[] { -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!gc", name = "k", descriptor = "Z")
	public boolean disable = false;

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive arg0, @OriginalArg(1) int arg1) {
		try {
			@Pc(9) Buffer local9 = new Buffer(arg0.read("idk.dat", null));
			count = local9.g2();
			if (instances == null) {
				instances = new IdkType[count];
			}
			for (@Pc(19) int local19 = 0; local19 < count; local19++) {
				if (instances[local19] == null) {
					instances[local19] = new IdkType();
				}
				instances[local19].decode(false, local9);
			}
			@Pc(45) int local45 = 87 / arg1;
		} catch (@Pc(47) RuntimeException local47) {
			signlink.reporterror("89502, " + arg0 + ", " + arg1 + ", " + local47.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(0) boolean arg0, @OriginalArg(1) Buffer arg1) {
		try {
			if (arg0) {
				flowObfuscator1 = 65;
			}
			while (true) {
				while (true) {
					@Pc(8) int local8 = arg1.g1();
					if (local8 == 0) {
						return;
					}
					if (local8 == 1) {
						this.type = arg1.g1();
					} else if (local8 == 2) {
						@Pc(26) int local26 = arg1.g1();
						this.models = new int[local26];
						for (@Pc(32) int local32 = 0; local32 < local26; local32++) {
							this.models[local32] = arg1.g2();
						}
					} else if (local8 == 3) {
						this.disable = true;
					} else if (local8 >= 40 && local8 < 50) {
						this.recol_s[local8 - 40] = arg1.g2();
					} else if (local8 >= 50 && local8 < 60) {
						this.recol_d[local8 - 50] = arg1.g2();
					} else if (local8 >= 60 && local8 < 70) {
						this.heads[local8 - 60] = arg1.g2();
					} else {
						System.out.println("Error unrecognised config code: " + local8);
					}
				}
			}
		} catch (@Pc(113) RuntimeException local113) {
			signlink.reporterror("61151, " + arg0 + ", " + arg1 + ", " + local113.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "()Lclient!eb;")
	public Model getModel() {
		if (this.models == null) {
			return null;
		}
		@Pc(11) Model[] local11 = new Model[this.models.length];
		for (@Pc(13) int local13 = 0; local13 < this.models.length; local13++) {
			local11[local13] = new Model(false, this.models[local13]);
		}
		@Pc(40) Model local40;
		if (local11.length == 1) {
			local40 = local11[0];
		} else {
			local40 = new Model(0, local11, local11.length);
		}
		for (@Pc(52) int local52 = 0; local52 < 6 && this.recol_s[local52] != 0; local52++) {
			local40.recolor(this.recol_s[local52], this.recol_d[local52]);
		}
		return local40;
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Z)Lclient!eb;")
	public Model getHeadModel(@OriginalArg(0) boolean arg0) {
		try {
			@Pc(4) Model[] local4 = new Model[5];
			@Pc(6) int local6 = 0;
			for (@Pc(8) int local8 = 0; local8 < 5; local8++) {
				if (this.heads[local8] != -1) {
					local4[local6++] = new Model(false, this.heads[local8]);
				}
			}
			@Pc(39) Model local39 = new Model(0, local4, local6);
			for (@Pc(41) int local41 = 0; local41 < 6 && this.recol_s[local41] != 0; local41++) {
				local39.recolor(this.recol_s[local41], this.recol_d[local41]);
			}
			if (arg0) {
				for (@Pc(66) int local66 = 1; local66 > 0; local66++) {
				}
			}
			return local39;
		} catch (@Pc(74) RuntimeException local74) {
			signlink.reporterror("19364, " + arg0 + ", " + local74.toString());
			throw new RuntimeException();
		}
	}
}
