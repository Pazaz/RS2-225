import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!pb")
public final class Stack {

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "I")
	private int anInt678 = 679;

	@OriginalMember(owner = "client!pb", name = "b", descriptor = "B")
	private final byte aByte34 = 2;

	@OriginalMember(owner = "client!pb", name = "c", descriptor = "Z")
	private final boolean aBoolean140 = true;

	@OriginalMember(owner = "client!pb", name = "d", descriptor = "Lclient!db;")
	private final CacheableNode aCacheableNode_17 = new CacheableNode();

	@OriginalMember(owner = "client!pb", name = "<init>", descriptor = "(I)V")
	public Stack(@OriginalArg(0) int arg0) {
		this.aCacheableNode_17.nextCacheable = this.aCacheableNode_17;
		if (arg0 < 5 || arg0 > 5) {
			this.anInt678 = -426;
		}
		this.aCacheableNode_17.prevCacheable = this.aCacheableNode_17;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Lclient!db;)V")
	public final void push(@OriginalArg(0) CacheableNode arg0) {
		if (arg0.prevCacheable != null) {
			arg0.uncache();
		}
		arg0.prevCacheable = this.aCacheableNode_17.prevCacheable;
		arg0.nextCacheable = this.aCacheableNode_17;
		arg0.prevCacheable.nextCacheable = arg0;
		arg0.nextCacheable.prevCacheable = arg0;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "()Lclient!db;")
	public final CacheableNode pop() {
		@Pc(3) CacheableNode local3 = this.aCacheableNode_17.nextCacheable;
		if (local3 == this.aCacheableNode_17) {
			return null;
		} else {
			local3.uncache();
			return local3;
		}
	}
}
