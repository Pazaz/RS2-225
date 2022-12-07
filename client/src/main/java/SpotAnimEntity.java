import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!bb")
public final class SpotAnimEntity extends Entity {

	@OriginalMember(owner = "client!bb", name = "m", descriptor = "I")
	private int seqFrame;

	@OriginalMember(owner = "client!bb", name = "n", descriptor = "I")
	private int frameCycle;

	@OriginalMember(owner = "client!bb", name = "o", descriptor = "Z")
	public boolean finished = false;

	@OriginalMember(owner = "client!bb", name = "g", descriptor = "Lclient!kc;")
	private final SpotAnimType spotanim;

	@OriginalMember(owner = "client!bb", name = "i", descriptor = "I")
	public final int plane;

	@OriginalMember(owner = "client!bb", name = "j", descriptor = "I")
	public final int x;

	@OriginalMember(owner = "client!bb", name = "k", descriptor = "I")
	public final int z;

	@OriginalMember(owner = "client!bb", name = "l", descriptor = "I")
	public final int height;

	@OriginalMember(owner = "client!bb", name = "h", descriptor = "I")
	public final int firstCycle;

	@OriginalMember(owner = "client!bb", name = "<init>", descriptor = "(IIZIIIII)V")
	public SpotAnimEntity(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		this.spotanim = SpotAnimType.instances[arg1];
		this.plane = arg6;
		this.x = arg0;
		this.z = arg3;
		this.height = arg5;
		this.firstCycle = arg7 + arg4;
		this.finished = false;
	}

	@OriginalMember(owner = "client!bb", name = "a", descriptor = "(II)V")
	public void update(@OriginalArg(0) int arg0) {
		this.frameCycle += arg0;
		while (true) {
			do {
				do {
					if (this.frameCycle <= this.spotanim.seq.frameDelay[this.seqFrame]) {
						return;
					}
					this.frameCycle -= this.spotanim.seq.frameDelay[this.seqFrame] + 1;
					this.seqFrame++;
				} while (this.seqFrame < this.spotanim.seq.framecount);
			} while (this.seqFrame >= 0 && this.seqFrame < this.spotanim.seq.framecount);
			this.seqFrame = 0;
			this.finished = true;
		}
	}

	@OriginalMember(owner = "client!bb", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public Model getDrawMethod() {
		@Pc(3) Model local3 = this.spotanim.getModel();
		@Pc(19) Model local19 = new Model(local3, true, !this.spotanim.disposeAlpha, false);
		if (!this.finished) {
			local19.applyGroup();
			local19.applyFrame(this.spotanim.seq.primaryFrames[this.seqFrame]);
			local19.skinTriangle = null;
			local19.labelVertices = null;
		}
		if (this.spotanim.resizeh != 128 || this.spotanim.resizev != 128) {
			local19.scale(this.spotanim.resizeh, this.spotanim.resizev, this.spotanim.resizeh);
		}
		if (this.spotanim.orientation != 0) {
			if (this.spotanim.orientation == 90) {
				local19.rotateCounterClockwise();
			}
			if (this.spotanim.orientation == 180) {
				local19.rotateCounterClockwise();
				local19.rotateCounterClockwise();
			}
			if (this.spotanim.orientation == 270) {
				local19.rotateCounterClockwise();
				local19.rotateCounterClockwise();
				local19.rotateCounterClockwise();
			}
		}
		local19.applyLighting(this.spotanim.ambient + 64, this.spotanim.contrast + 850, -30, -50, -30, true);
		return local19;
	}
}
