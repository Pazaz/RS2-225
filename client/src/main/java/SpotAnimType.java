import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!kc")
public final class SpotAnimType {

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "I")
	private static final int anInt566 = 473;

	@OriginalMember(owner = "client!kc", name = "c", descriptor = "[Lclient!kc;")
	public static SpotAnimType[] instances;

	@OriginalMember(owner = "client!kc", name = "p", descriptor = "Lclient!s;")
	public static Cache aClass35_8 = new Cache((byte) 0, 30);

	@OriginalMember(owner = "client!kc", name = "b", descriptor = "I")
	private static int anInt567;

	@OriginalMember(owner = "client!kc", name = "d", descriptor = "I")
	public int anInt568;

	@OriginalMember(owner = "client!kc", name = "e", descriptor = "I")
	private int anInt569;

	@OriginalMember(owner = "client!kc", name = "g", descriptor = "Lclient!jc;")
	public SeqType aClass19_1;

	@OriginalMember(owner = "client!kc", name = "m", descriptor = "I")
	public int anInt573;

	@OriginalMember(owner = "client!kc", name = "n", descriptor = "I")
	public int anInt574;

	@OriginalMember(owner = "client!kc", name = "o", descriptor = "I")
	public int anInt575;

	@OriginalMember(owner = "client!kc", name = "f", descriptor = "I")
	private int anInt570 = -1;

	@OriginalMember(owner = "client!kc", name = "h", descriptor = "Z")
	public boolean aBoolean131 = false;

	@OriginalMember(owner = "client!kc", name = "i", descriptor = "[I")
	private final int[] anIntArray192 = new int[6];

	@OriginalMember(owner = "client!kc", name = "j", descriptor = "[I")
	private final int[] anIntArray193 = new int[6];

	@OriginalMember(owner = "client!kc", name = "k", descriptor = "I")
	public int anInt571 = 128;

	@OriginalMember(owner = "client!kc", name = "l", descriptor = "I")
	public int anInt572 = 128;

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive arg0, @OriginalArg(1) int arg1) {
		try {
			@Pc(3) int local3 = 91 / arg1;
			@Pc(13) Buffer local13 = new Buffer(363, arg0.read("spotanim.dat", null, (byte) 2));
			anInt567 = local13.g2();
			if (instances == null) {
				instances = new SpotAnimType[anInt567];
			}
			for (@Pc(23) int local23 = 0; local23 < anInt567; local23++) {
				if (instances[local23] == null) {
					instances[local23] = new SpotAnimType();
				}
				instances[local23].anInt568 = local23;
				instances[local23].decode(false, local13);
			}
		} catch (@Pc(52) RuntimeException local52) {
			signlink.reporterror("26561, " + arg0 + ", " + arg1 + ", " + local52.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(ZLclient!kb;)V")
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
						return;
					}
					if (local5 == 1) {
						this.anInt569 = arg1.g2();
					} else if (local5 == 2) {
						this.anInt570 = arg1.g2();
						if (SeqType.instances != null) {
							this.aClass19_1 = SeqType.instances[this.anInt570];
						}
					} else if (local5 == 3) {
						this.aBoolean131 = true;
					} else if (local5 == 4) {
						this.anInt571 = arg1.g2();
					} else if (local5 == 5) {
						this.anInt572 = arg1.g2();
					} else if (local5 == 6) {
						this.anInt573 = arg1.g2();
					} else if (local5 == 7) {
						this.anInt574 = arg1.g1();
					} else if (local5 == 8) {
						this.anInt575 = arg1.g1();
					} else if (local5 >= 40 && local5 < 50) {
						this.anIntArray192[local5 - 40] = arg1.g2();
					} else if (local5 >= 50 && local5 < 60) {
						this.anIntArray193[local5 - 50] = arg1.g2();
					} else {
						System.out.println("Error unrecognised spotanim config code: " + local5);
					}
				}
			}
		} catch (@Pc(138) RuntimeException local138) {
			signlink.reporterror("42060, " + arg0 + ", " + arg1 + ", " + local138.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "()Lclient!eb;")
	public Model getModel() {
		@Pc(6) Model local6 = (Model) aClass35_8.get((long) this.anInt568);
		if (local6 != null) {
			return local6;
		}
		local6 = new Model(false, this.anInt569);
		for (@Pc(19) int local19 = 0; local19 < 6; local19++) {
			if (this.anIntArray192[0] != 0) {
				local6.recolor(this.anIntArray192[local19], this.anIntArray193[local19]);
			}
		}
		aClass35_8.put(6, (long) this.anInt568, local6);
		return local6;
	}
}
