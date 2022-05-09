import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!t")
public final class Hashtable {

	@OriginalMember(owner = "client!t", name = "a", descriptor = "I")
	private final int anInt756 = 4277;

	@OriginalMember(owner = "client!t", name = "b", descriptor = "Z")
	private final boolean aBoolean147 = false;

	@OriginalMember(owner = "client!t", name = "c", descriptor = "I")
	private final int anInt757;

	@OriginalMember(owner = "client!t", name = "d", descriptor = "[Lclient!u;")
	private final Node[] aNode;

	@OriginalMember(owner = "client!t", name = "<init>", descriptor = "(II)V")
	public Hashtable(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.anInt757 = arg1;
		this.aNode = new Node[arg1];
		for (@Pc(30) int local30 = 0; local30 < arg1; local30++) {
			@Pc(40) Node local40 = this.aNode[local30] = new Node();
			local40.aNode_41 = local40;
			local40.aNode_42 = local40;
		}
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(J)Lclient!u;")
	public final Node method530(@OriginalArg(0) long arg0) {
		@Pc(11) Node local11 = this.aNode[(int) (arg0 & (long) (this.anInt757 - 1))];
		for (@Pc(14) Node local14 = local11.aNode_41; local14 != local11; local14 = local14.aNode_41) {
			if (local14.aLong26 == arg0) {
				return local14;
			}
		}
		return null;
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(JILclient!u;)V")
	public final void method531(@OriginalArg(0) long arg0, @OriginalArg(2) Node arg1) {
		if (arg1.aNode_42 != null) {
			arg1.method567();
		}
		@Pc(18) Node local18 = this.aNode[(int) (arg0 & (long) (this.anInt757 - 1))];
		arg1.aNode_42 = local18.aNode_42;
		arg1.aNode_41 = local18;
		arg1.aNode_42.aNode_41 = arg1;
		arg1.aNode_41.aNode_42 = arg1;
		arg1.aLong26 = arg0;
	}
}
