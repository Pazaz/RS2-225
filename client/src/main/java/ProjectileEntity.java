import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ab")
public final class ProjectileEntity extends Entity {

	@OriginalMember(owner = "client!ab", name = "s", descriptor = "D")
	public double x;

	@OriginalMember(owner = "client!ab", name = "t", descriptor = "D")
	public double y;

	@OriginalMember(owner = "client!ab", name = "u", descriptor = "D")
	public double z;

	@OriginalMember(owner = "client!ab", name = "v", descriptor = "D")
	private double velocityX;

	@OriginalMember(owner = "client!ab", name = "w", descriptor = "D")
	private double velocityY;

	@OriginalMember(owner = "client!ab", name = "x", descriptor = "D")
	private double velocity;

	@OriginalMember(owner = "client!ab", name = "y", descriptor = "D")
	private double velocityZ;

	@OriginalMember(owner = "client!ab", name = "z", descriptor = "D")
	private double accelerationZ;

	@OriginalMember(owner = "client!ab", name = "A", descriptor = "I")
	public int yaw;

	@OriginalMember(owner = "client!ab", name = "B", descriptor = "I")
	private int pitch;

	@OriginalMember(owner = "client!ab", name = "C", descriptor = "I")
	private int seqFrame;

	@OriginalMember(owner = "client!ab", name = "D", descriptor = "I")
	private int frameCycle;

	@OriginalMember(owner = "client!ab", name = "r", descriptor = "Z")
	private boolean isMobile = false;

	@OriginalMember(owner = "client!ab", name = "g", descriptor = "Lclient!kc;")
	private final SpotAnimType spotAnim;

	@OriginalMember(owner = "client!ab", name = "h", descriptor = "I")
	public final int level;

	@OriginalMember(owner = "client!ab", name = "i", descriptor = "I")
	private final int sourceX;

	@OriginalMember(owner = "client!ab", name = "j", descriptor = "I")
	private final int sourceY;

	@OriginalMember(owner = "client!ab", name = "k", descriptor = "I")
	private final int sourceZ;

	@OriginalMember(owner = "client!ab", name = "m", descriptor = "I")
	public final int firstCycle;

	@OriginalMember(owner = "client!ab", name = "n", descriptor = "I")
	public final int lastCycle;

	@OriginalMember(owner = "client!ab", name = "o", descriptor = "I")
	private final int elevationPitch;

	@OriginalMember(owner = "client!ab", name = "p", descriptor = "I")
	private final int arcScale;

	@OriginalMember(owner = "client!ab", name = "q", descriptor = "I")
	public final int targetIndex;

	@OriginalMember(owner = "client!ab", name = "l", descriptor = "I")
	public final int baseZ;

	@OriginalMember(owner = "client!ab", name = "<init>", descriptor = "(IIIIIIIIIIII)V")
	public ProjectileEntity(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11) {
		this.spotAnim = SpotAnimType.instances[arg10];
		this.level = arg4;
		this.sourceX = arg11;
		this.sourceY = arg2;
		this.sourceZ = arg9;
		this.firstCycle = arg6;
		this.lastCycle = arg3;
		this.elevationPitch = arg1;
		this.arcScale = arg7;
		this.targetIndex = arg5;
		this.baseZ = arg0;
		this.isMobile = false;
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(IIIII)V")
	public void setTarget(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg4) {
		@Pc(8) double local8;
		if (!this.isMobile) {
			local8 = arg2 - this.sourceX;
			@Pc(14) double local14 = arg1 - this.sourceY;
			@Pc(23) double local23 = Math.sqrt(local8 * local8 + local14 * local14);
			this.x = (double) this.sourceX + local8 * (double) this.arcScale / local23;
			this.y = (double) this.sourceY + local14 * (double) this.arcScale / local23;
			this.z = this.sourceZ;
		}
		local8 = this.lastCycle + 1 - arg4;
		this.velocityX = ((double) arg2 - this.x) / local8;
		this.velocityY = ((double) arg1 - this.y) / local8;
		this.velocity = Math.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY);
		if (!this.isMobile) {
			this.velocityZ = -this.velocity * Math.tan((double) this.elevationPitch * 0.02454369D);
		}
		this.accelerationZ = ((double) arg0 - this.z - this.velocityZ * local8) * 2.0D / (local8 * local8);
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(BI)V")
	public void update(@OriginalArg(1) int arg1) {
		this.isMobile = true;
		this.x += this.velocityX * (double) arg1;
		this.y += this.velocityY * (double) arg1;
		this.z += this.velocityZ * (double) arg1 + this.accelerationZ * 0.5D * (double) arg1 * (double) arg1;
		this.velocityZ += this.accelerationZ * (double) arg1;
		this.yaw = (int) (Math.atan2(this.velocityX, this.velocityY) * 325.949D) + 1024 & 0x7FF;
		this.pitch = (int) (Math.atan2(this.velocityZ, this.velocity) * 325.949D) & 0x7FF;
		if (this.spotAnim.seq != null) {
			this.frameCycle += arg1;
			while (this.frameCycle > this.spotAnim.seq.frameDelay[this.seqFrame]) {
				this.frameCycle -= this.spotAnim.seq.frameDelay[this.seqFrame] + 1;
				this.seqFrame++;
				if (this.seqFrame >= this.spotAnim.seq.framecount) {
					this.seqFrame = 0;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public Model getDrawMethod() {
		@Pc(3) Model local3 = this.spotAnim.getModel();
		@Pc(19) Model local19 = new Model(local3, true, !this.spotAnim.disposeAlpha, false);
		if (this.spotAnim.seq != null) {
			local19.applyGroup();
			local19.applyFrame(this.spotAnim.seq.primaryFrames[this.seqFrame]);
			local19.skinTriangle = null;
			local19.labelVertices = null;
		}
		if (this.spotAnim.resizeh != 128 || this.spotAnim.resizev != 128) {
			local19.scale(this.spotAnim.resizeh, this.spotAnim.resizev, this.spotAnim.resizeh);
		}
		local19.rotatePitch(this.pitch);
		local19.applyLighting(this.spotAnim.ambient + 64, this.spotAnim.contrast + 850, -30, -50, -30, true);
		return local19;
	}
}
