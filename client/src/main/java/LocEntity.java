import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!nb")
public final class LocEntity extends Node {

	@OriginalMember(owner = "client!nb", name = "e", descriptor = "I")
	public int anInt651;

	@OriginalMember(owner = "client!nb", name = "f", descriptor = "I")
	public final int anInt652;

	@OriginalMember(owner = "client!nb", name = "g", descriptor = "I")
	public final int anInt653;

	@OriginalMember(owner = "client!nb", name = "h", descriptor = "I")
	public final int anInt654;

	@OriginalMember(owner = "client!nb", name = "i", descriptor = "I")
	public final int anInt655;

	@OriginalMember(owner = "client!nb", name = "j", descriptor = "Lclient!jc;")
	public final SeqType aSeqType_2;

	@OriginalMember(owner = "client!nb", name = "k", descriptor = "I")
	public int anInt656;

	@OriginalMember(owner = "client!nb", name = "l", descriptor = "I")
	public int anInt657;

	@OriginalMember(owner = "client!nb", name = "<init>", descriptor = "(ZIIIILclient!jc;II)V")
	public LocEntity(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) SeqType arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		this.anInt651 = arg2;
		this.anInt652 = arg4;
		this.anInt653 = arg7;
		this.anInt654 = arg6;
		this.anInt655 = arg1;
		this.aSeqType_2 = arg5;
		if (arg0 && arg5.delay != -1) {
			this.anInt656 = (int) (Math.random() * (double) this.aSeqType_2.frameCount);
			this.anInt657 = (int) (Math.random() * (double) this.aSeqType_2.frameDelay[this.anInt656]);
		} else {
			this.anInt656 = -1;
			this.anInt657 = 0;
		}
	}
}
