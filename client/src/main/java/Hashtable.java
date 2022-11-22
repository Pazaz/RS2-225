import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!t")
public final class Hashtable {

	@OriginalMember(owner = "client!t", name = "a", descriptor = "I")
	private final int anInt756 = 4277;

	@OriginalMember(owner = "client!t", name = "b", descriptor = "Z")
	private boolean aBoolean147 = false;

	@OriginalMember(owner = "client!t", name = "c", descriptor = "I")
	private final int size;

	@OriginalMember(owner = "client!t", name = "d", descriptor = "[Lclient!u;")
	private final Node[] nodes;

	@OriginalMember(owner = "client!t", name = "<init>", descriptor = "(II)V")
	public Hashtable(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		try {
			this.size = arg1;
			this.nodes = new Node[arg1];
			if (arg0 < 9 || arg0 > 9) {
				this.aBoolean147 = !this.aBoolean147;
			}
			for (@Pc(30) int local30 = 0; local30 < arg1; local30++) {
				@Pc(40) Node local40 = this.nodes[local30] = new Node();
				local40.prev = local40;
				local40.next = local40;
			}
		} catch (@Pc(52) RuntimeException local52) {
			signlink.reporterror("73766, " + arg0 + ", " + arg1 + ", " + local52.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(J)Lclient!u;")
	public Node get(@OriginalArg(0) long arg0) {
		@Pc(11) Node local11 = this.nodes[(int) (arg0 & (long) (this.size - 1))];
		for (@Pc(14) Node local14 = local11.prev; local14 != local11; local14 = local14.prev) {
			if (local14.key == arg0) {
				return local14;
			}
		}
		return null;
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(JILclient!u;)V")
	public void put(@OriginalArg(0) long arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Node arg2) {
		try {
			if (arg2.next != null) {
				arg2.unlink();
			}
			@Pc(18) Node local18 = this.nodes[(int) (arg0 & (long) (this.size - 1))];
			arg2.next = local18.next;
			if (arg1 < 0) {
				arg2.prev = local18;
				arg2.next.prev = arg2;
				arg2.prev.next = arg2;
				arg2.key = arg0;
			}
		} catch (@Pc(46) RuntimeException local46) {
			signlink.reporterror("49205, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + local46.toString());
			throw new RuntimeException();
		}
	}
}
