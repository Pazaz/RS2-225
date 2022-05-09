import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!u")
public class Node {

	@OriginalMember(owner = "client!u", name = "a", descriptor = "J")
	public long aLong26;

	@OriginalMember(owner = "client!u", name = "b", descriptor = "Lclient!u;")
	public Node aNode_41;

	@OriginalMember(owner = "client!u", name = "c", descriptor = "Lclient!u;")
	public Node aNode_42;

	@OriginalMember(owner = "client!u", name = "a", descriptor = "()V")
	public final void unlink() {
		if (this.aNode_42 != null) {
			this.aNode_42.aNode_41 = this.aNode_41;
			this.aNode_41.aNode_42 = this.aNode_42;
			this.aNode_41 = null;
			this.aNode_42 = null;
		}
	}
}
