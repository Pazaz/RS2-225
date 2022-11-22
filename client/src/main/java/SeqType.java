import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!jc")
public final class SeqType {

	@OriginalMember(owner = "client!jc", name = "b", descriptor = "I")
	private static final int anInt541 = 473;

	@OriginalMember(owner = "client!jc", name = "d", descriptor = "[Lclient!jc;")
	public static SeqType[] instances;

	@OriginalMember(owner = "client!jc", name = "c", descriptor = "I")
	private static int anInt542;

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "Z")
	private static boolean aBoolean125 = true;

	@OriginalMember(owner = "client!jc", name = "e", descriptor = "I")
	public int anInt543;

	@OriginalMember(owner = "client!jc", name = "f", descriptor = "[I")
	public int[] anIntArray186;

	@OriginalMember(owner = "client!jc", name = "g", descriptor = "[I")
	public int[] anIntArray187;

	@OriginalMember(owner = "client!jc", name = "h", descriptor = "[I")
	public int[] anIntArray188;

	@OriginalMember(owner = "client!jc", name = "j", descriptor = "[I")
	public int[] anIntArray189;

	@OriginalMember(owner = "client!jc", name = "i", descriptor = "I")
	public int anInt544 = -1;

	@OriginalMember(owner = "client!jc", name = "k", descriptor = "Z")
	public boolean aBoolean126 = false;

	@OriginalMember(owner = "client!jc", name = "l", descriptor = "I")
	public int priority = 5;

	@OriginalMember(owner = "client!jc", name = "m", descriptor = "I")
	public int anInt546 = -1;

	@OriginalMember(owner = "client!jc", name = "n", descriptor = "I")
	public int anInt547 = -1;

	@OriginalMember(owner = "client!jc", name = "o", descriptor = "I")
	public int anInt548 = 99;

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive arg0, @OriginalArg(1) int arg1) {
		try {
			@Pc(9) Buffer local9 = new Buffer(363, arg0.read("seq.dat", null, (byte) 2));
			if (arg1 <= 0) {
				aBoolean125 = !aBoolean125;
			}
			anInt542 = local9.g2();
			if (instances == null) {
				instances = new SeqType[anInt542];
			}
			for (@Pc(27) int local27 = 0; local27 < anInt542; local27++) {
				if (instances[local27] == null) {
					instances[local27] = new SeqType();
				}
				instances[local27].decode(false, local9);
			}
		} catch (@Pc(51) RuntimeException local51) {
			signlink.reporterror("35892, " + arg0 + ", " + arg1 + ", " + local51.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(0) boolean arg0, @OriginalArg(1) Buffer arg1) {
		try {
			@Pc(5) int local5;
			if (arg0) {
				for (local5 = 1; local5 > 0; local5++) {
				}
			}
			while (true) {
				while (true) {
					local5 = arg1.g1();
					if (local5 == 0) {
						if (this.anInt543 == 0) {
							this.anInt543 = 1;
							this.anIntArray186 = new int[1];
							this.anIntArray186[0] = -1;
							this.anIntArray187 = new int[1];
							this.anIntArray187[0] = -1;
							this.anIntArray188 = new int[1];
							this.anIntArray188[0] = -1;
							return;
						}
						return;
					}
					@Pc(40) int local40;
					if (local5 == 1) {
						this.anInt543 = arg1.g1();
						this.anIntArray186 = new int[this.anInt543];
						this.anIntArray187 = new int[this.anInt543];
						this.anIntArray188 = new int[this.anInt543];
						for (local40 = 0; local40 < this.anInt543; local40++) {
							this.anIntArray186[local40] = arg1.g2();
							this.anIntArray187[local40] = arg1.g2();
							if (this.anIntArray187[local40] == 65535) {
								this.anIntArray187[local40] = -1;
							}
							this.anIntArray188[local40] = arg1.g2();
							if (this.anIntArray188[local40] == 0) {
								this.anIntArray188[local40] = SeqFrame.aClass12Array1[this.anIntArray186[local40]].anInt411;
							}
							if (this.anIntArray188[local40] == 0) {
								this.anIntArray188[local40] = 1;
							}
						}
					} else if (local5 == 2) {
						this.anInt544 = arg1.g2();
					} else if (local5 == 3) {
						local40 = arg1.g1();
						this.anIntArray189 = new int[local40 + 1];
						for (@Pc(127) int local127 = 0; local127 < local40; local127++) {
							this.anIntArray189[local127] = arg1.g1();
						}
						this.anIntArray189[local40] = 9999999;
					} else if (local5 == 4) {
						this.aBoolean126 = true;
					} else if (local5 == 5) {
						this.priority = arg1.g1();
					} else if (local5 == 6) {
						this.anInt546 = arg1.g2();
					} else if (local5 == 7) {
						this.anInt547 = arg1.g2();
					} else if (local5 == 8) {
						this.anInt548 = arg1.g1();
					} else {
						System.out.println("Error unrecognised seq config code: " + local5);
					}
				}
			}
		} catch (@Pc(236) RuntimeException local236) {
			signlink.reporterror("38080, " + arg0 + ", " + arg1 + ", " + local236.toString());
			throw new RuntimeException();
		}
	}
}
