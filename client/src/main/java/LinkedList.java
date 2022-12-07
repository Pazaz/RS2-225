import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ob")
public final class LinkedList {

	@OriginalMember(owner = "client!ob", name = "f", descriptor = "Lclient!u;")
	private Node selected;

	@OriginalMember(owner = "client!ob", name = "e", descriptor = "Lclient!u;")
	private final Node head = new Node();

	@OriginalMember(owner = "client!ob", name = "<init>", descriptor = "(I)V")
	public LinkedList() {
		this.head.prev = this.head;
		this.head.next = this.head;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;)V")
	public void pushNext(@OriginalArg(0) Node arg0) {
		if (arg0.next != null) {
			arg0.unlink();
		}
		arg0.next = this.head.next;
		arg0.prev = this.head;
		arg0.next.prev = arg0;
		arg0.prev.next = arg0;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;I)V")
	public void pushPrevious(@OriginalArg(0) Node arg0) {
		if (arg0.next != null) {
			arg0.unlink();
		}
		arg0.next = this.head;
		arg0.prev = this.head.prev;
		arg0.next.prev = arg0;
		arg0.prev.next = arg0;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "()Lclient!u;")
	public Node poll() {
		@Pc(3) Node local3 = this.head.prev;
		if (local3 == this.head) {
			return null;
		} else {
			local3.unlink();
			return local3;
		}
	}

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "()Lclient!u;")
	public Node peekPrevious() {
		@Pc(3) Node local3 = this.head.prev;
		if (local3 == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = local3.prev;
			return local3;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(B)Lclient!u;")
	public Node peekNext() {
		@Pc(3) Node local3 = this.head.next;
		if (local3 == this.head) {
			this.selected = null;
			return null;
		}
		this.selected = local3.next;
		return local3;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(I)Lclient!u;")
	public Node getPrevious() {
		@Pc(8) Node local8 = this.selected;
		if (local8 == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = local8.prev;
			return local8;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Z)Lclient!u;")
	public Node getNext() {
		@Pc(2) Node local2 = this.selected;
		if (local2 == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = local2.next;
			return local2;
		}
	}

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "()V")
	public void clear() {
		while (true) {
			@Pc(3) Node local3 = this.head.prev;
			if (local3 == this.head) {
				return;
			}
			local3.unlink();
		}
	}
}
