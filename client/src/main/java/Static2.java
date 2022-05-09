import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static2 {

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "I")
	public static int anInt38;

	@OriginalMember(owner = "client!ac", name = "b", descriptor = "I")
	public static int anInt39;

	@OriginalMember(owner = "client!ac", name = "c", descriptor = "Z")
	public static boolean aBoolean6;

	@OriginalMember(owner = "client!ac", name = "d", descriptor = "I")
	private static int anInt40;

	@OriginalMember(owner = "client!ac", name = "e", descriptor = "[I")
	private static int[] anIntArray3;

	@OriginalMember(owner = "client!ac", name = "f", descriptor = "Lclient!kb;")
	private static Class1_Sub3_Sub3 aClass1_Sub3_Sub3_1;

	@OriginalMember(owner = "client!ac", name = "g", descriptor = "[Lclient!ac;")
	private static Class2[] aClass2Array1;

	@OriginalMember(owner = "client!ac", name = "h", descriptor = "I")
	private static int anInt41;

	@OriginalMember(owner = "client!ac", name = "P", descriptor = "Lclient!s;")
	public static Class35 aClass35_1 = new Class35((byte) 0, 500);

	@OriginalMember(owner = "client!ac", name = "Q", descriptor = "Lclient!s;")
	public static Class35 aClass35_2 = new Class35((byte) 0, 30);

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(Lclient!ub;)V")
	public static void method21(@OriginalArg(0) Class39 arg0) {
		aClass1_Sub3_Sub3_1 = new Class1_Sub3_Sub3(363, arg0.method536("loc.dat", null));
		@Pc(21) Class1_Sub3_Sub3 local21 = new Class1_Sub3_Sub3(363, arg0.method536("loc.idx", null));
		anInt40 = local21.method393();
		anIntArray3 = new int[anInt40];
		@Pc(29) int local29 = 2;
		for (@Pc(31) int local31 = 0; local31 < anInt40; local31++) {
			anIntArray3[local31] = local29;
			local29 += local21.method393();
		}
		aClass2Array1 = new Class2[10];
		for (@Pc(51) int local51 = 0; local51 < 10; local51++) {
			aClass2Array1[local51] = new Class2();
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(Z)V")
	public static void method22() {
		aClass35_1 = null;
		aClass35_2 = null;
		anIntArray3 = null;
		aClass2Array1 = null;
		aClass1_Sub3_Sub3_1 = null;
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(I)Lclient!ac;")
	public static Class2 method23(@OriginalArg(0) int arg0) {
		for (@Pc(1) int local1 = 0; local1 < 10; local1++) {
			if (aClass2Array1[local1].anInt42 == arg0) {
				return aClass2Array1[local1];
			}
		}
		anInt41 = (anInt41 + 1) % 10;
		@Pc(27) Class2 local27 = aClass2Array1[anInt41];
		aClass1_Sub3_Sub3_1.anInt561 = anIntArray3[arg0];
		local27.anInt42 = arg0;
		local27.method24();
		local27.method25(aClass1_Sub3_Sub3_1);
		return local27;
	}
}
