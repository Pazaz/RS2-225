import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ic")
public final class DeadClass {

	@OriginalMember(owner = "client!ic", name = "b", descriptor = "[Lclient!ic;")
	public static DeadClass[] aDeadClassArray1;

	@OriginalMember(owner = "client!ic", name = "a", descriptor = "I")
	private static final int anInt519 = 473;

	@OriginalMember(owner = "client!ic", name = "c", descriptor = "Z")
	private boolean aBoolean118;

	@OriginalMember(owner = "client!ic", name = "d", descriptor = "Z")
	private boolean aBoolean119;

	@OriginalMember(owner = "client!ic", name = "e", descriptor = "Z")
	private boolean aBoolean120;

	@OriginalMember(owner = "client!ic", name = "f", descriptor = "Z")
	private boolean aBoolean121;

	@OriginalMember(owner = "client!ic", name = "g", descriptor = "Z")
	private boolean aBoolean122;
}
