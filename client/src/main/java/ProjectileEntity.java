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
	public ProjectileEntity(@OriginalArg(0) int baseZ, @OriginalArg(1) int elevationPitch, @OriginalArg(2) int sourceY, @OriginalArg(3) int lastCycle, @OriginalArg(4) int level, @OriginalArg(5) int targetIndex, @OriginalArg(6) int firstCycle, @OriginalArg(7) int arcScale, @OriginalArg(9) int sourceZ, @OriginalArg(10) int spotanim, @OriginalArg(11) int sourceX) {
		this.spotAnim = SpotAnimType.instances[spotanim];
		this.level = level;
		this.sourceX = sourceX;
		this.sourceY = sourceY;
		this.sourceZ = sourceZ;
		this.firstCycle = firstCycle;
		this.lastCycle = lastCycle;
		this.elevationPitch = elevationPitch;
		this.arcScale = arcScale;
		this.targetIndex = targetIndex;
		this.baseZ = baseZ;
		this.isMobile = false;
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(IIIII)V")
	public void setTarget(@OriginalArg(0) int destZ, @OriginalArg(1) int destY, @OriginalArg(2) int destX, @OriginalArg(4) int cycle) {
		if (!this.isMobile) {
			@Pc(8) double dx = destX - this.sourceX;
			@Pc(14) double dy = destY - this.sourceY;
			@Pc(23) double d = Math.sqrt(dx * dx + dy * dy);
			this.x = (double) this.sourceX + dx * (double) this.arcScale / d;
			this.y = (double) this.sourceY + dy * (double) this.arcScale / d;
			this.z = this.sourceZ;
		}

		double dt = this.lastCycle + 1 - cycle;
		this.velocityX = ((double) destX - this.x) / dt;
		this.velocityY = ((double) destY - this.y) / dt;
		this.velocity = Math.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY);

		if (!this.isMobile) {
			this.velocityZ = -this.velocity * Math.tan((double) this.elevationPitch * 0.02454369D);
		}

		this.accelerationZ = ((double) destZ - this.z - this.velocityZ * dt) * 2.0D / (dt * dt);
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(BI)V")
	public void update(@OriginalArg(1) int cycle) {
		this.isMobile = true;

		this.x += this.velocityX * (double) cycle;
		this.y += this.velocityY * (double) cycle;
		this.z += this.velocityZ * (double) cycle + this.accelerationZ * 0.5D * (double) cycle * (double) cycle;

		this.velocityZ += this.accelerationZ * (double) cycle;
		this.yaw = (int) (Math.atan2(this.velocityX, this.velocityY) * 325.949D) + 1024 & 0x7FF;
		this.pitch = (int) (Math.atan2(this.velocityZ, this.velocity) * 325.949D) & 0x7FF;

		if (this.spotAnim.seq != null) {
			this.frameCycle += cycle;
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
		@Pc(3) Model model = this.spotAnim.getModel();

		@Pc(19) Model m = new Model(model, true, !this.spotAnim.disposeAlpha, false);
		if (this.spotAnim.seq != null) {
			m.applyGroup();
			m.applyFrame(this.spotAnim.seq.primaryFrames[this.seqFrame]);
			m.skinTriangle = null;
			m.labelVertices = null;
		}

		if (this.spotAnim.resizeh != 128 || this.spotAnim.resizev != 128) {
			m.scale(this.spotAnim.resizeh, this.spotAnim.resizev, this.spotAnim.resizeh);
		}

		m.rotatePitch(this.pitch);
		m.applyLighting(this.spotAnim.ambient + 64, this.spotAnim.contrast + 850, -30, -50, -30, true);
		return m;
	}
}
