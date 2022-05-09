import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!t")
public final class Class37 {

	@OriginalMember(owner = "client!t", name = "a", descriptor = "I")
	private final int anInt756 = 4277;

	@OriginalMember(owner = "client!t", name = "b", descriptor = "Z")
	private final boolean aBoolean147 = false;

	@OriginalMember(owner = "client!t", name = "c", descriptor = "I")
	private final int anInt757;

	@OriginalMember(owner = "client!t", name = "d", descriptor = "[Lclient!u;")
	private final Class1[] aClass1Array1;

	@OriginalMember(owner = "client!t", name = "<init>", descriptor = "(II)V")
	public Class37(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.anInt757 = arg1;
		this.aClass1Array1 = new Class1[arg1];
		for (@Pc(30) int local30 = 0; local30 < arg1; local30++) {
			@Pc(40) Class1 local40 = this.aClass1Array1[local30] = new Class1();
			local40.aClass1_41 = local40;
			local40.aClass1_42 = local40;
		}
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(J)Lclient!u;")
	public final Class1 method530(@OriginalArg(0) long arg0) {
		@Pc(11) Class1 local11 = this.aClass1Array1[(int) (arg0 & (long) (this.anInt757 - 1))];
		for (@Pc(14) Class1 local14 = local11.aClass1_41; local14 != local11; local14 = local14.aClass1_41) {
			if (local14.aLong26 == arg0) {
				return local14;
			}
		}
		return null;
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(JILclient!u;)V")
	public final void method531(@OriginalArg(0) long arg0, @OriginalArg(2) Class1 arg1) {
		if (arg1.aClass1_42 != null) {
			arg1.method567();
		}
		@Pc(18) Class1 local18 = this.aClass1Array1[(int) (arg0 & (long) (this.anInt757 - 1))];
		arg1.aClass1_42 = local18.aClass1_42;
		arg1.aClass1_41 = local18;
		arg1.aClass1_42.aClass1_41 = arg1;
		arg1.aClass1_41.aClass1_42 = arg1;
		arg1.aLong26 = arg0;
	}
}
