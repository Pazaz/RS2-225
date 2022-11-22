import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!pb")
public final class Stack {

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "I")
	private int anInt678 = 679;

	@OriginalMember(owner = "client!pb", name = "b", descriptor = "B")
	private final byte aByte34 = 2;

	@OriginalMember(owner = "client!pb", name = "c", descriptor = "Z")
	private final boolean aBoolean140 = true;

	@OriginalMember(owner = "client!pb", name = "d", descriptor = "Lclient!db;")
	private final CacheableNode aClass1_Sub3_17 = new CacheableNode();

	@OriginalMember(owner = "client!pb", name = "<init>", descriptor = "(I)V")
	public Stack(@OriginalArg(0) int arg0) {
		try {
			this.aClass1_Sub3_17.aClass1_Sub3_15 = this.aClass1_Sub3_17;
			if (arg0 < 5 || arg0 > 5) {
				this.anInt678 = -426;
			}
			this.aClass1_Sub3_17.aClass1_Sub3_16 = this.aClass1_Sub3_17;
		} catch (@Pc(36) RuntimeException local36) {
			signlink.reporterror("27606, " + arg0 + ", " + local36.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Lclient!db;)V")
	public void push(@OriginalArg(0) CacheableNode arg0) {
		if (arg0.aClass1_Sub3_16 != null) {
			arg0.uncache();
		}
		arg0.aClass1_Sub3_16 = this.aClass1_Sub3_17.aClass1_Sub3_16;
		arg0.aClass1_Sub3_15 = this.aClass1_Sub3_17;
		arg0.aClass1_Sub3_16.aClass1_Sub3_15 = arg0;
		arg0.aClass1_Sub3_15.aClass1_Sub3_16 = arg0;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "()Lclient!db;")
	public CacheableNode pop() {
		@Pc(3) CacheableNode local3 = this.aClass1_Sub3_17.aClass1_Sub3_15;
		if (local3 == this.aClass1_Sub3_17) {
			return null;
		} else {
			local3.uncache();
			return local3;
		}
	}
}
