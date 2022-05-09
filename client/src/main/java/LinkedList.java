import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ob")
public final class LinkedList {

	@OriginalMember(owner = "client!ob", name = "f", descriptor = "Lclient!u;")
	private Node aNode_34;

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "Z")
	private final boolean aBoolean139 = true;

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "B")
	private final byte aByte32 = 2;

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "I")
	private final int anInt664 = -546;

	@OriginalMember(owner = "client!ob", name = "d", descriptor = "I")
	private int anInt665 = -676;

	@OriginalMember(owner = "client!ob", name = "e", descriptor = "Lclient!u;")
	private final Node aNode_33 = new Node();

	@OriginalMember(owner = "client!ob", name = "<init>", descriptor = "(I)V")
	public LinkedList(@OriginalArg(0) int arg0) {
		this.aNode_33.aNode_41 = this.aNode_33;
		this.aNode_33.aNode_42 = this.aNode_33;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;)V")
	public final void pushNext(@OriginalArg(0) Node arg0) {
		if (arg0.aNode_42 != null) {
			arg0.unlink();
		}
		arg0.aNode_42 = this.aNode_33.aNode_42;
		arg0.aNode_41 = this.aNode_33;
		arg0.aNode_42.aNode_41 = arg0;
		arg0.aNode_41.aNode_42 = arg0;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;I)V")
	public final void pushPrevious(@OriginalArg(0) Node arg0) {
		if (arg0.aNode_42 != null) {
			arg0.unlink();
		}
		arg0.aNode_42 = this.aNode_33;
		arg0.aNode_41 = this.aNode_33.aNode_41;
		arg0.aNode_42.aNode_41 = arg0;
		arg0.aNode_41.aNode_42 = arg0;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "()Lclient!u;")
	public final Node poll() {
		@Pc(3) Node local3 = this.aNode_33.aNode_41;
		if (local3 == this.aNode_33) {
			return null;
		} else {
			local3.unlink();
			return local3;
		}
	}

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "()Lclient!u;")
	public final Node peekPrevious() {
		@Pc(3) Node local3 = this.aNode_33.aNode_41;
		if (local3 == this.aNode_33) {
			this.aNode_34 = null;
			return null;
		} else {
			this.aNode_34 = local3.aNode_41;
			return local3;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(B)Lclient!u;")
	public final Node peekNext() {
		@Pc(3) Node local3 = this.aNode_33.aNode_42;
		if (local3 == this.aNode_33) {
			this.aNode_34 = null;
			return null;
		}
		this.aNode_34 = local3.aNode_42;
		if (this.aByte32 != 2) {
			this.anInt665 = 112;
		}
		return local3;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(I)Lclient!u;")
	public final Node getPrevious() {
		@Pc(8) Node local8 = this.aNode_34;
		if (local8 == this.aNode_33) {
			this.aNode_34 = null;
			return null;
		} else {
			this.aNode_34 = local8.aNode_41;
			return local8;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Z)Lclient!u;")
	public final Node getNext() {
		@Pc(2) Node local2 = this.aNode_34;
		if (local2 == this.aNode_33) {
			this.aNode_34 = null;
			return null;
		} else {
			this.aNode_34 = local2.aNode_42;
			return local2;
		}
	}

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "()V")
	public final void clear() {
		while (true) {
			@Pc(3) Node local3 = this.aNode_33.aNode_41;
			if (local3 == this.aNode_33) {
				return;
			}
			local3.unlink();
		}
	}
}
