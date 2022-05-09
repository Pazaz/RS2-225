import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!lc")
public final class VarpType {

	@OriginalMember(owner = "client!lc", name = "c", descriptor = "I")
	private static int anInt605;

	@OriginalMember(owner = "client!lc", name = "d", descriptor = "[Lclient!lc;")
	public static VarpType[] aVarpTypeArray1;

	@OriginalMember(owner = "client!lc", name = "e", descriptor = "I")
	private static int anInt606;

	@OriginalMember(owner = "client!lc", name = "f", descriptor = "[I")
	private static int[] anIntArray194;

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "I")
	private static final int anInt603 = 473;

	@OriginalMember(owner = "client!lc", name = "b", descriptor = "I")
	private static final int anInt604 = 13703;

	@OriginalMember(owner = "client!lc", name = "g", descriptor = "Ljava/lang/String;")
	private String aString25;

	@OriginalMember(owner = "client!lc", name = "h", descriptor = "I")
	private int anInt607;

	@OriginalMember(owner = "client!lc", name = "i", descriptor = "I")
	private int anInt608;

	@OriginalMember(owner = "client!lc", name = "l", descriptor = "I")
	public int anInt609;

	@OriginalMember(owner = "client!lc", name = "n", descriptor = "I")
	private int anInt610;

	@OriginalMember(owner = "client!lc", name = "j", descriptor = "Z")
	private boolean aBoolean132 = false;

	@OriginalMember(owner = "client!lc", name = "k", descriptor = "Z")
	private boolean aBoolean133 = true;

	@OriginalMember(owner = "client!lc", name = "m", descriptor = "Z")
	private boolean aBoolean134 = false;

	@OriginalMember(owner = "client!lc", name = "o", descriptor = "Z")
	private boolean aBoolean135 = false;

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void decode(@OriginalArg(0) FileArchive arg0) {
		@Pc(9) Buffer local9 = new Buffer(363, arg0.read("varp.dat", null));
		anInt606 = 0;
		anInt605 = local9.g2();
		if (aVarpTypeArray1 == null) {
			aVarpTypeArray1 = new VarpType[anInt605];
		}
		if (anIntArray194 == null) {
			anIntArray194 = new int[anInt605];
		}
		for (@Pc(30) int local30 = 0; local30 < anInt605; local30++) {
			if (aVarpTypeArray1[local30] == null) {
				aVarpTypeArray1[local30] = new VarpType();
			}
			aVarpTypeArray1[local30].decode(anInt604, local30, local9);
		}
	}

	@OriginalMember(owner = "client!lc", name = "<init>", descriptor = "()V")
	private VarpType() {
	}

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(IILclient!kb;)V")
	private void decode(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Buffer arg2) {
		if (arg0 != 13703) {
			return;
		}
		while (true) {
			@Pc(8) int local8 = arg2.g1();
			if (local8 == 0) {
				return;
			}
			if (local8 == 1) {
				this.anInt607 = arg2.g1();
			} else if (local8 == 2) {
				this.anInt608 = arg2.g1();
			} else if (local8 == 3) {
				this.aBoolean132 = true;
				anIntArray194[anInt606++] = arg1;
			} else if (local8 == 4) {
				this.aBoolean133 = false;
			} else if (local8 == 5) {
				this.anInt609 = arg2.g2();
			} else if (local8 == 6) {
				this.aBoolean134 = true;
			} else if (local8 == 7) {
				this.anInt610 = arg2.g4();
			} else if (local8 == 8) {
				this.aBoolean135 = true;
			} else if (local8 == 10) {
				this.aString25 = arg2.gjstr();
			} else {
				System.out.println("Error unrecognised config code: " + local8);
			}
		}
	}
}
