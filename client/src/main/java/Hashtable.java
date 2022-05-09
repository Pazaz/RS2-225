import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!t")
public final class Hashtable {

	@OriginalMember(owner = "client!t", name = "a", descriptor = "I")
	private final int flowObfuscator1 = 4277;

	@OriginalMember(owner = "client!t", name = "b", descriptor = "Z")
	private final boolean flowObfuscator2 = false;

	@OriginalMember(owner = "client!t", name = "c", descriptor = "I")
	private final int size;

	@OriginalMember(owner = "client!t", name = "d", descriptor = "[Lclient!u;")
	private final Node[] nodes;

	@OriginalMember(owner = "client!t", name = "<init>", descriptor = "(II)V")
	public Hashtable(@OriginalArg(0) int obfuscator, @OriginalArg(1) int size) {
		this.size = size;
		this.nodes = new Node[size];
		for (@Pc(30) int i = 0; i < size; i++) {
			@Pc(40) Node node = this.nodes[i] = new Node();
			node.prev = node;
			node.next = node;
		}
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(J)Lclient!u;")
	public final Node get(@OriginalArg(0) long key) {
		@Pc(11) Node start = this.nodes[(int) (key & (long) (this.size - 1))];
		for (@Pc(14) Node last = start.prev; last != start; last = last.prev) {
			if (last.key == key) {
				return last;
			}
		}
		return null;
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(JILclient!u;)V")
	public final void put(@OriginalArg(0) long key, @OriginalArg(2) Node node) {
		if (node.next != null) {
			node.unlink();
		}
		@Pc(18) Node old = this.nodes[(int) (key & (long) (this.size - 1))];
		node.next = old.next;
		node.prev = old;
		node.next.prev = node;
		node.prev.next = node;
		node.key = key;
	}
}
