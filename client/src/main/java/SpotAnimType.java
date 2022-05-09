import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kc")
public final class SpotAnimType {

	@OriginalMember(owner = "client!kc", name = "b", descriptor = "I")
	private static int anInt567;

	@OriginalMember(owner = "client!kc", name = "c", descriptor = "[Lclient!kc;")
	public static SpotAnimType[] aSpotAnimTypeArray1;

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "I")
	private static final int anInt566 = 473;

	@OriginalMember(owner = "client!kc", name = "p", descriptor = "Lclient!s;")
	public static Cache aCache_8 = new Cache((byte) 0, 30);

	@OriginalMember(owner = "client!kc", name = "d", descriptor = "I")
	private int anInt568;

	@OriginalMember(owner = "client!kc", name = "e", descriptor = "I")
	private int anInt569;

	@OriginalMember(owner = "client!kc", name = "g", descriptor = "Lclient!jc;")
	public SeqType aSeqType_1;

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
	public static void method407(@OriginalArg(0) FileArchive arg0) {
		@Pc(13) Buffer local13 = new Buffer(363, arg0.method536("spotanim.dat", null));
		anInt567 = local13.method393();
		if (aSpotAnimTypeArray1 == null) {
			aSpotAnimTypeArray1 = new SpotAnimType[anInt567];
		}
		for (@Pc(23) int local23 = 0; local23 < anInt567; local23++) {
			if (aSpotAnimTypeArray1[local23] == null) {
				aSpotAnimTypeArray1[local23] = new SpotAnimType();
			}
			aSpotAnimTypeArray1[local23].anInt568 = local23;
			aSpotAnimTypeArray1[local23].method408(local13);
		}
	}

	@OriginalMember(owner = "client!kc", name = "<init>", descriptor = "()V")
	private SpotAnimType() {
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void method408(@OriginalArg(1) Buffer arg0) {
		while (true) {
			@Pc(13) int local13 = arg0.method391();
			if (local13 == 0) {
				return;
			}
			if (local13 == 1) {
				this.anInt569 = arg0.method393();
			} else if (local13 == 2) {
				this.anInt570 = arg0.method393();
				if (SeqType.aSeqTypeArray1 != null) {
					this.aSeqType_1 = SeqType.aSeqTypeArray1[this.anInt570];
				}
			} else if (local13 == 3) {
				this.aBoolean131 = true;
			} else if (local13 == 4) {
				this.anInt571 = arg0.method393();
			} else if (local13 == 5) {
				this.anInt572 = arg0.method393();
			} else if (local13 == 6) {
				this.anInt573 = arg0.method393();
			} else if (local13 == 7) {
				this.anInt574 = arg0.method391();
			} else if (local13 == 8) {
				this.anInt575 = arg0.method391();
			} else if (local13 >= 40 && local13 < 50) {
				this.anIntArray192[local13 - 40] = arg0.method393();
			} else if (local13 >= 50 && local13 < 60) {
				this.anIntArray193[local13 - 50] = arg0.method393();
			} else {
				System.out.println("Error unrecognised spotanim config code: " + local13);
			}
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "()Lclient!eb;")
	public final Model method409() {
		@Pc(6) Model local6 = (Model) aCache_8.method527((long) this.anInt568);
		if (local6 != null) {
			return local6;
		}
		local6 = new Model(false, this.anInt569);
		for (@Pc(19) int local19 = 0; local19 < 6; local19++) {
			if (this.anIntArray192[0] != 0) {
				local6.method237(this.anIntArray192[local19], this.anIntArray193[local19]);
			}
		}
		aCache_8.method528((long) this.anInt568, local6);
		return local6;
	}
}
