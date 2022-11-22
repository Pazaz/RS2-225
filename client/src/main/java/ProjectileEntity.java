import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!ab")
public final class ProjectileEntity extends Entity {

	@OriginalMember(owner = "client!ab", name = "e", descriptor = "I")
	private int anInt22;

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

	@OriginalMember(owner = "client!ab", name = "f", descriptor = "I")
	private int anInt23 = -159;

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
	public ProjectileEntity(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11) {
		try {
			this.spotAnim = SpotAnimType.instances[arg10];
			if (arg8 != 0) {
				throw new NullPointerException();
			}
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
		} catch (@Pc(53) RuntimeException local53) {
			signlink.reporterror("57613, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + arg3 + ", " + arg4 + ", " + arg5 + ", " + arg6 + ", " + arg7 + ", " + arg8 + ", " + arg9 + ", " + arg10 + ", " + arg11 + ", " + local53.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(IIIII)V")
	public void setTarget(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		try {
			@Pc(8) double local8;
			if (!this.isMobile) {
				local8 = arg2 - this.sourceX;
				@Pc(14) double local14 = (double) (arg1 - this.sourceY);
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
			if (arg3 < 0) {
				;
			}
		} catch (@Pc(131) RuntimeException local131) {
			signlink.reporterror("18544, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + arg3 + ", " + arg4 + ", " + local131.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(BI)V")
	public void update(@OriginalArg(0) byte arg0, @OriginalArg(1) int arg1) {
		try {
			if (arg0 != -30) {
				this.anInt23 = -454;
			}
			this.isMobile = true;
			this.x += this.velocityX * (double) arg1;
			this.y += this.velocityY * (double) arg1;
			this.z += this.velocityZ * (double) arg1 + this.accelerationZ * 0.5D * (double) arg1 * (double) arg1;
			this.velocityZ += this.accelerationZ * (double) arg1;
			this.yaw = (int) (Math.atan2(this.velocityX, this.velocityY) * 325.949D) + 1024 & 0x7FF;
			this.pitch = (int) (Math.atan2(this.velocityZ, this.velocity) * 325.949D) & 0x7FF;
			if (this.spotAnim.aClass19_1 != null) {
				this.frameCycle += arg1;
				while (this.frameCycle > this.spotAnim.aClass19_1.anIntArray188[this.seqFrame]) {
					this.frameCycle -= this.spotAnim.aClass19_1.anIntArray188[this.seqFrame] + 1;
					this.seqFrame++;
					if (this.seqFrame >= this.spotAnim.aClass19_1.anInt543) {
						this.seqFrame = 0;
					}
				}
			}
		} catch (@Pc(139) RuntimeException local139) {
			signlink.reporterror("86911, " + arg0 + ", " + arg1 + ", " + local139.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public Model getDrawMethod(@OriginalArg(0) boolean arg0) {
		try {
			@Pc(3) Model local3 = this.spotAnim.getModel();
			@Pc(19) Model local19 = new Model(local3, true, !this.spotAnim.aBoolean131, this.anInt22, false);
			if (!arg0) {
				for (@Pc(23) int local23 = 1; local23 > 0; local23++) {
				}
			}
			if (this.spotAnim.aClass19_1 != null) {
				local19.applyGroup(4);
				local19.applyFrame(-16599, this.spotAnim.aClass19_1.anIntArray186[this.seqFrame]);
				local19.anIntArrayArray7 = null;
				local19.anIntArrayArray6 = null;
			}
			if (this.spotAnim.anInt571 != 128 || this.spotAnim.anInt572 != 128) {
				local19.scale(this.spotAnim.anInt571, 2, this.spotAnim.anInt572, this.spotAnim.anInt571);
			}
			local19.rotatePitch((byte) 7, this.pitch);
			local19.applyLighting(this.spotAnim.anInt574 + 64, this.spotAnim.anInt575 + 850, -30, -50, -30, true);
			return local19;
		} catch (@Pc(97) RuntimeException local97) {
			signlink.reporterror("99718, " + arg0 + ", " + local97.toString());
			throw new RuntimeException();
		}
	}
}
