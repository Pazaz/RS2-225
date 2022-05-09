import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static23 {

	@OriginalMember(owner = "client!lc", name = "c", descriptor = "I")
	private static int anInt605;

	@OriginalMember(owner = "client!lc", name = "d", descriptor = "[Lclient!lc;")
	public static Class23[] aClass23Array1;

	@OriginalMember(owner = "client!lc", name = "e", descriptor = "I")
	public static int anInt606;

	@OriginalMember(owner = "client!lc", name = "f", descriptor = "[I")
	public static int[] anIntArray194;

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "I")
	private static final int anInt603 = 473;

	@OriginalMember(owner = "client!lc", name = "b", descriptor = "I")
	private static final int anInt604 = 13703;

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void method411(@OriginalArg(0) Class39 arg0) {
		@Pc(9) Class1_Sub3_Sub3 local9 = new Class1_Sub3_Sub3(363, arg0.method536("varp.dat", null));
		anInt606 = 0;
		anInt605 = local9.method393();
		if (aClass23Array1 == null) {
			aClass23Array1 = new Class23[anInt605];
		}
		if (anIntArray194 == null) {
			anIntArray194 = new int[anInt605];
		}
		for (@Pc(30) int local30 = 0; local30 < anInt605; local30++) {
			if (aClass23Array1[local30] == null) {
				aClass23Array1[local30] = new Class23();
			}
			aClass23Array1[local30].method412(anInt604, local30, local9);
		}
	}
}
