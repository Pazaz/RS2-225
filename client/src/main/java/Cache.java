import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!s")
public final class Cache {

	@OriginalMember(owner = "client!s", name = "b", descriptor = "I")
	private static final int anInt725 = 5;

	@OriginalMember(owner = "client!s", name = "a", descriptor = "Z")
	private final boolean aBoolean145 = false;

	@OriginalMember(owner = "client!s", name = "e", descriptor = "Lclient!t;")
	private final Hashtable aHashtable_1 = new Hashtable(9, 1024);

	@OriginalMember(owner = "client!s", name = "f", descriptor = "Lclient!pb;")
	private final Stack aStack_1 = new Stack(anInt725);

	@OriginalMember(owner = "client!s", name = "c", descriptor = "I")
	private final int anInt726;

	@OriginalMember(owner = "client!s", name = "d", descriptor = "I")
	private int anInt727;

	@OriginalMember(owner = "client!s", name = "<init>", descriptor = "(BI)V")
	public Cache(@OriginalArg(0) byte arg0, @OriginalArg(1) int arg1) {
		this.anInt726 = arg1;
		this.anInt727 = arg1;
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "(J)Lclient!db;")
	public final CacheableNode get(@OriginalArg(0) long arg0) {
		@Pc(5) CacheableNode local5 = (CacheableNode) this.aHashtable_1.get(arg0);
		if (local5 != null) {
			this.aStack_1.push(local5);
		}
		return local5;
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "(IJLclient!db;)V")
	public final void put(@OriginalArg(1) long arg0, @OriginalArg(2) CacheableNode arg1) {
		if (this.anInt727 == 0) {
			@Pc(8) CacheableNode local8 = this.aStack_1.pop();
			local8.unlink();
			local8.uncache();
		} else {
			this.anInt727--;
		}
		this.aHashtable_1.put(arg0, arg1);
		this.aStack_1.push(arg1);
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "()V")
	public final void clear() {
		while (true) {
			@Pc(3) CacheableNode local3 = this.aStack_1.pop();
			if (local3 == null) {
				this.anInt727 = this.anInt726;
				return;
			}
			local3.unlink();
			local3.uncache();
		}
	}
}
