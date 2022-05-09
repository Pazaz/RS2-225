import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static13 {

	@OriginalMember(owner = "client!fc", name = "c", descriptor = "I")
	public static int anInt402;

	@OriginalMember(owner = "client!fc", name = "d", descriptor = "[Lclient!fc;")
	public static Class11[] aClass11Array1;

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "I")
	private static final int anInt400 = 473;

	@OriginalMember(owner = "client!fc", name = "b", descriptor = "I")
	public static final int anInt401 = -546;

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void method272(@OriginalArg(0) Class39 arg0) {
		@Pc(9) Class1_Sub3_Sub3 local9 = new Class1_Sub3_Sub3(363, arg0.method536("flo.dat", null));
		anInt402 = local9.method393();
		if (aClass11Array1 == null) {
			aClass11Array1 = new Class11[anInt402];
		}
		for (@Pc(23) int local23 = 0; local23 < anInt402; local23++) {
			if (aClass11Array1[local23] == null) {
				aClass11Array1[local23] = new Class11();
			}
			aClass11Array1[local23].method273(local9);
		}
	}
}
