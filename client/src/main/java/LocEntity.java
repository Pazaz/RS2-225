import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!nb")
public final class LocEntity extends Node {

	@OriginalMember(owner = "client!nb", name = "e", descriptor = "I")
	public int level;

	@OriginalMember(owner = "client!nb", name = "f", descriptor = "I")
	public final int classType;

	@OriginalMember(owner = "client!nb", name = "g", descriptor = "I")
	public final int x;

	@OriginalMember(owner = "client!nb", name = "h", descriptor = "I")
	public final int z;

	@OriginalMember(owner = "client!nb", name = "i", descriptor = "I")
	public final int locIndex;

	@OriginalMember(owner = "client!nb", name = "j", descriptor = "Lclient!jc;")
	public final SeqType seq;

	@OriginalMember(owner = "client!nb", name = "k", descriptor = "I")
	public int seqFrame;

	@OriginalMember(owner = "client!nb", name = "l", descriptor = "I")
	public int seqDelay;

	@OriginalMember(owner = "client!nb", name = "<init>", descriptor = "(ZIIIILclient!jc;II)V")
	public LocEntity(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg4, @OriginalArg(5) SeqType arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		this.level = arg2;
		this.classType = arg4;
		this.x = arg7;
		this.z = arg6;
		this.locIndex = arg1;
		this.seq = arg5;
		if (arg0 && arg5.replayoff != -1) {
			this.seqFrame = (int) (Math.random() * (double) this.seq.framecount);
			this.seqDelay = (int) (Math.random() * (double) this.seq.frameDelay[this.seqFrame]);
		} else {
			this.seqFrame = -1;
			this.seqDelay = 0;
		}
	}
}
