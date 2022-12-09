import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!pb")
public final class Stack {

	@OriginalMember(owner = "client!pb", name = "d", descriptor = "Lclient!db;")
	private final CacheableNode head = new CacheableNode();

	@OriginalMember(owner = "client!pb", name = "<init>", descriptor = "(I)V")
	public Stack() {
		this.head.nextCacheable = this.head;
		this.head.prevCacheable = this.head;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Lclient!db;)V")
	public void push(@OriginalArg(0) CacheableNode node) {
		if (node.prevCacheable != null) {
			node.uncache();
		}

		node.prevCacheable = this.head.prevCacheable;
		node.nextCacheable = this.head;
		node.prevCacheable.nextCacheable = node;
		node.nextCacheable.prevCacheable = node;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "()Lclient!db;")
	public CacheableNode pop() {
		@Pc(3) CacheableNode node = this.head.nextCacheable;
		if (node == this.head) {
			return null;
		}

		node.uncache();
		return node;
	}
}
