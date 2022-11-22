import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!ob")
public final class LinkedList {

	@OriginalMember(owner = "client!ob", name = "f", descriptor = "Lclient!u;")
	private Node selected;

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "Z")
	private boolean aBoolean139 = true;

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "B")
	private final byte aByte32 = 2;

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "I")
	private final int anInt664 = -546;

	@OriginalMember(owner = "client!ob", name = "d", descriptor = "I")
	private int anInt665 = -676;

	@OriginalMember(owner = "client!ob", name = "e", descriptor = "Lclient!u;")
	private final Node head = new Node();

	@OriginalMember(owner = "client!ob", name = "<init>", descriptor = "(I)V")
	public LinkedList(@OriginalArg(0) int arg0) {
		try {
			if (arg0 != 0) {
				this.aBoolean139 = !this.aBoolean139;
			}
			this.head.prev = this.head;
			this.head.next = this.head;
		} catch (@Pc(40) RuntimeException local40) {
			signlink.reporterror("59838, " + arg0 + ", " + local40.toString());
			throw new RuntimeException();
		}
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
	public void pushPrevious(@OriginalArg(0) Node arg0, @OriginalArg(1) int arg1) {
		try {
			if (arg0.next != null) {
				arg0.unlink();
			}
			arg0.next = this.head;
			if (arg1 == -26173) {
				arg0.prev = this.head.prev;
				arg0.next.prev = arg0;
				arg0.prev.next = arg0;
			}
		} catch (@Pc(27) RuntimeException local27) {
			signlink.reporterror("2399, " + arg0 + ", " + arg1 + ", " + local27.toString());
			throw new RuntimeException();
		}
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
	public Node peekNext(@OriginalArg(0) byte arg0) {
		try {
			@Pc(3) Node local3 = this.head.next;
			if (local3 == this.head) {
				this.selected = null;
				return null;
			}
			this.selected = local3.next;
			if (arg0 != this.aByte32) {
				this.anInt665 = 112;
			}
			return local3;
		} catch (@Pc(26) RuntimeException local26) {
			signlink.reporterror("37919, " + arg0 + ", " + local26.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(I)Lclient!u;")
	public Node getPrevious(@OriginalArg(0) int arg0) {
		try {
			if (arg0 <= 0) {
				throw new NullPointerException();
			}
			@Pc(8) Node local8 = this.selected;
			if (local8 == this.head) {
				this.selected = null;
				return null;
			} else {
				this.selected = local8.prev;
				return local8;
			}
		} catch (@Pc(24) RuntimeException local24) {
			signlink.reporterror("89761, " + arg0 + ", " + local24.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Z)Lclient!u;")
	public Node getNext(@OriginalArg(0) boolean arg0) {
		try {
			@Pc(2) Node local2 = this.selected;
			if (arg0) {
				for (@Pc(6) int local6 = 1; local6 > 0; local6++) {
				}
			}
			if (local2 == this.head) {
				this.selected = null;
				return null;
			} else {
				this.selected = local2.next;
				return local2;
			}
		} catch (@Pc(27) RuntimeException local27) {
			signlink.reporterror("58136, " + arg0 + ", " + local27.toString());
			throw new RuntimeException();
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
