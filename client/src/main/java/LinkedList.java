import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ob")
public final class LinkedList {

	@OriginalMember(owner = "client!ob", name = "f", descriptor = "Lclient!u;")
	private Node selected;

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "Z")
	private final boolean flowObfuscator3 = true;

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "B")
	private final byte flowObfuscator1 = 2;

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "I")
	private final int flowObfuscator4 = -546;

	@OriginalMember(owner = "client!ob", name = "d", descriptor = "I")
	private int flowObfuscator2 = -676;

	@OriginalMember(owner = "client!ob", name = "e", descriptor = "Lclient!u;")
	private final Node head = new Node();

	@OriginalMember(owner = "client!ob", name = "<init>", descriptor = "(I)V")
	public LinkedList(@OriginalArg(0) int obfuscator) {
		this.head.prev = this.head;
		this.head.next = this.head;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;)V")
	public final void pushNext(@OriginalArg(0) Node node) {
		if (node.next != null) {
			node.unlink();
		}
		node.next = this.head.next;
		node.prev = this.head;
		node.next.prev = node;
		node.prev.next = node;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;I)V")
	public final void pushPrevious(@OriginalArg(0) Node node) {
		if (node.next != null) {
			node.unlink();
		}
		node.next = this.head;
		node.prev = this.head.prev;
		node.next.prev = node;
		node.prev.next = node;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "()Lclient!u;")
	public final Node poll() {
		@Pc(3) Node node = this.head.prev;
		if (node == this.head) {
			return null;
		} else {
			node.unlink();
			return node;
		}
	}

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "()Lclient!u;")
	public final Node peekPrevious() {
		@Pc(3) Node node = this.head.prev;
		if (node == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = node.prev;
			return node;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(B)Lclient!u;")
	public final Node peekNext() {
		@Pc(3) Node node = this.head.next;
		if (node == this.head) {
			this.selected = null;
			return null;
		}
		this.selected = node.next;
		if (this.flowObfuscator1 != 2) {
			this.flowObfuscator2 = 112;
		}
		return node;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(I)Lclient!u;")
	public final Node getPrevious() {
		@Pc(8) Node node = this.selected;
		if (node == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = node.prev;
			return node;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Z)Lclient!u;")
	public final Node getNext() {
		@Pc(2) Node node = this.selected;
		if (node == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = node.next;
			return node;
		}
	}

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "()V")
	public final void clear() {
		while (true) {
			@Pc(3) Node node = this.head.prev;
			if (node == this.head) {
				return;
			}
			node.unlink();
		}
	}
}
