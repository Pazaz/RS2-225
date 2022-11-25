import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ic")
public final class DeadClass {

	@OriginalMember(owner = "client!ic", name = "a", descriptor = "I")
	private static final int flowObfuscator1 = 473;

	@OriginalMember(owner = "client!ic", name = "b", descriptor = "[Lclient!ic;")
	public static DeadClass[] instances;

	@OriginalMember(owner = "client!ic", name = "c", descriptor = "Z")
	private final boolean bool1 = true;

	@OriginalMember(owner = "client!ic", name = "d", descriptor = "Z")
	private final boolean bool2 = false;

	@OriginalMember(owner = "client!ic", name = "e", descriptor = "Z")
	private final boolean bool3 = true;

	@OriginalMember(owner = "client!ic", name = "f", descriptor = "Z")
	private final boolean bool4 = true;

	@OriginalMember(owner = "client!ic", name = "g", descriptor = "Z")
	private final boolean bool5 = false;

	@OriginalMember(owner = "client!ic", name = "<init>", descriptor = "()V")
	private DeadClass() {
	}
}
