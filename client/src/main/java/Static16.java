import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static16 {

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "I")
	private static int anInt435;

	@OriginalMember(owner = "client!gc", name = "d", descriptor = "I")
	public static int anInt437;

	@OriginalMember(owner = "client!gc", name = "e", descriptor = "[Lclient!gc;")
	public static Class13[] aClass13Array1;

	@OriginalMember(owner = "client!gc", name = "b", descriptor = "I")
	private static final int anInt436 = 473;

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void method304(@OriginalArg(0) Class39 arg0) {
		@Pc(9) Class1_Sub3_Sub3 local9 = new Class1_Sub3_Sub3(363, arg0.method536("idk.dat", null));
		anInt437 = local9.method393();
		if (aClass13Array1 == null) {
			aClass13Array1 = new Class13[anInt437];
		}
		for (@Pc(19) int local19 = 0; local19 < anInt437; local19++) {
			if (aClass13Array1[local19] == null) {
				aClass13Array1[local19] = new Class13();
			}
			aClass13Array1[local19].method305(local9);
		}
	}
}
