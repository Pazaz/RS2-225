import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!lc")
public final class VarpType {

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "I")
	private static final int anInt603 = 473;

	@OriginalMember(owner = "client!lc", name = "b", descriptor = "I")
	private static final int anInt604 = 13703;

	@OriginalMember(owner = "client!lc", name = "d", descriptor = "[Lclient!lc;")
	public static VarpType[] instances;

	@OriginalMember(owner = "client!lc", name = "e", descriptor = "I")
	public static int opcode3Count;

	@OriginalMember(owner = "client!lc", name = "f", descriptor = "[I")
	public static int[] opcode3Array;

	@OriginalMember(owner = "client!lc", name = "c", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!lc", name = "g", descriptor = "Ljava/lang/String;")
	private String opcode9;

	@OriginalMember(owner = "client!lc", name = "h", descriptor = "I")
	private int opcode1;

	@OriginalMember(owner = "client!lc", name = "i", descriptor = "I")
	private int opcode2;

	@OriginalMember(owner = "client!lc", name = "l", descriptor = "I")
	public int clientcode;

	@OriginalMember(owner = "client!lc", name = "n", descriptor = "I")
	private int opcode7;

	@OriginalMember(owner = "client!lc", name = "j", descriptor = "Z")
	private boolean opcode3 = false;

	@OriginalMember(owner = "client!lc", name = "k", descriptor = "Z")
	private boolean opcode4 = true;

	@OriginalMember(owner = "client!lc", name = "m", descriptor = "Z")
	private boolean opcode6 = false;

	@OriginalMember(owner = "client!lc", name = "o", descriptor = "Z")
	private boolean opcode8 = false;

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive arg0, @OriginalArg(1) int arg1) {
		try {
			@Pc(9) Buffer local9 = new Buffer(363, arg0.read("varp.dat", null, (byte) 2));
			opcode3Count = 0;
			@Pc(15) int local15 = 28 / arg1;
			count = local9.g2();
			if (instances == null) {
				instances = new VarpType[count];
			}
			if (opcode3Array == null) {
				opcode3Array = new int[count];
			}
			for (@Pc(30) int local30 = 0; local30 < count; local30++) {
				if (instances[local30] == null) {
					instances[local30] = new VarpType();
				}
				instances[local30].decode(anInt604, local30, local9);
			}
		} catch (@Pc(55) RuntimeException local55) {
			signlink.reporterror("45283, " + arg0 + ", " + arg1 + ", " + local55.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(IILclient!kb;)V")
	public void decode(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Buffer arg2) {
		try {
			if (arg0 == 13703) {
				while (true) {
					@Pc(8) int local8 = arg2.g1();
					if (local8 == 0) {
						return;
					}
					if (local8 == 1) {
						this.opcode1 = arg2.g1();
					} else if (local8 == 2) {
						this.opcode2 = arg2.g1();
					} else if (local8 == 3) {
						this.opcode3 = true;
						opcode3Array[opcode3Count++] = arg1;
					} else if (local8 == 4) {
						this.opcode4 = false;
					} else if (local8 == 5) {
						this.clientcode = arg2.g2();
					} else if (local8 == 6) {
						this.opcode6 = true;
					} else if (local8 == 7) {
						this.opcode7 = arg2.g4();
					} else if (local8 == 8) {
						this.opcode8 = true;
					} else if (local8 == 10) {
						this.opcode9 = arg2.gstr();
					} else {
						System.out.println("Error unrecognised config code: " + local8);
					}
				}
			}
		} catch (@Pc(107) RuntimeException local107) {
			signlink.reporterror("67426, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + local107.toString());
			throw new RuntimeException();
		}
	}
}
