import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ob")
public final class Class28 {

	@OriginalMember(owner = "client!ob", name = "f", descriptor = "Lclient!u;")
	private Class1 aClass1_34;

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "Z")
	private final boolean aBoolean139 = true;

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "B")
	private final byte aByte32 = 2;

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "I")
	private final int anInt664 = -546;

	@OriginalMember(owner = "client!ob", name = "d", descriptor = "I")
	private int anInt665 = -676;

	@OriginalMember(owner = "client!ob", name = "e", descriptor = "Lclient!u;")
	private final Class1 aClass1_33 = new Class1();

	@OriginalMember(owner = "client!ob", name = "<init>", descriptor = "(I)V")
	public Class28(@OriginalArg(0) int arg0) {
		this.aClass1_33.aClass1_41 = this.aClass1_33;
		this.aClass1_33.aClass1_42 = this.aClass1_33;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;)V")
	public final void method453(@OriginalArg(0) Class1 arg0) {
		if (arg0.aClass1_42 != null) {
			arg0.method567();
		}
		arg0.aClass1_42 = this.aClass1_33.aClass1_42;
		arg0.aClass1_41 = this.aClass1_33;
		arg0.aClass1_42.aClass1_41 = arg0;
		arg0.aClass1_41.aClass1_42 = arg0;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;I)V")
	public final void method454(@OriginalArg(0) Class1 arg0) {
		if (arg0.aClass1_42 != null) {
			arg0.method567();
		}
		arg0.aClass1_42 = this.aClass1_33;
		arg0.aClass1_41 = this.aClass1_33.aClass1_41;
		arg0.aClass1_42.aClass1_41 = arg0;
		arg0.aClass1_41.aClass1_42 = arg0;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "()Lclient!u;")
	public final Class1 method455() {
		@Pc(3) Class1 local3 = this.aClass1_33.aClass1_41;
		if (local3 == this.aClass1_33) {
			return null;
		} else {
			local3.method567();
			return local3;
		}
	}

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "()Lclient!u;")
	public final Class1 method456() {
		@Pc(3) Class1 local3 = this.aClass1_33.aClass1_41;
		if (local3 == this.aClass1_33) {
			this.aClass1_34 = null;
			return null;
		} else {
			this.aClass1_34 = local3.aClass1_41;
			return local3;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(B)Lclient!u;")
	public final Class1 method457() {
		@Pc(3) Class1 local3 = this.aClass1_33.aClass1_42;
		if (local3 == this.aClass1_33) {
			this.aClass1_34 = null;
			return null;
		}
		this.aClass1_34 = local3.aClass1_42;
		if (this.aByte32 != 2) {
			this.anInt665 = 112;
		}
		return local3;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(I)Lclient!u;")
	public final Class1 method458() {
		@Pc(8) Class1 local8 = this.aClass1_34;
		if (local8 == this.aClass1_33) {
			this.aClass1_34 = null;
			return null;
		} else {
			this.aClass1_34 = local8.aClass1_41;
			return local8;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Z)Lclient!u;")
	public final Class1 method459() {
		@Pc(2) Class1 local2 = this.aClass1_34;
		if (local2 == this.aClass1_33) {
			this.aClass1_34 = null;
			return null;
		} else {
			this.aClass1_34 = local2.aClass1_42;
			return local2;
		}
	}

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "()V")
	public final void method460() {
		while (true) {
			@Pc(3) Class1 local3 = this.aClass1_33.aClass1_41;
			if (local3 == this.aClass1_33) {
				return;
			}
			local3.method567();
		}
	}
}
