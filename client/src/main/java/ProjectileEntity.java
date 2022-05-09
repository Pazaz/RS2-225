import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ab")
public final class ProjectileEntity extends Entity {

	@OriginalMember(owner = "client!ab", name = "e", descriptor = "I")
	private int anInt22;

	@OriginalMember(owner = "client!ab", name = "s", descriptor = "D")
	public double aDouble1;

	@OriginalMember(owner = "client!ab", name = "t", descriptor = "D")
	public double aDouble2;

	@OriginalMember(owner = "client!ab", name = "u", descriptor = "D")
	public double aDouble3;

	@OriginalMember(owner = "client!ab", name = "v", descriptor = "D")
	private double aDouble4;

	@OriginalMember(owner = "client!ab", name = "w", descriptor = "D")
	private double aDouble5;

	@OriginalMember(owner = "client!ab", name = "x", descriptor = "D")
	private double aDouble6;

	@OriginalMember(owner = "client!ab", name = "y", descriptor = "D")
	private double aDouble7;

	@OriginalMember(owner = "client!ab", name = "z", descriptor = "D")
	private double aDouble8;

	@OriginalMember(owner = "client!ab", name = "A", descriptor = "I")
	public int anInt34;

	@OriginalMember(owner = "client!ab", name = "B", descriptor = "I")
	private int anInt35;

	@OriginalMember(owner = "client!ab", name = "C", descriptor = "I")
	private int anInt36;

	@OriginalMember(owner = "client!ab", name = "D", descriptor = "I")
	private int anInt37;

	@OriginalMember(owner = "client!ab", name = "f", descriptor = "I")
	private final int anInt23 = -159;

	@OriginalMember(owner = "client!ab", name = "r", descriptor = "Z")
	private boolean aBoolean5 = false;

	@OriginalMember(owner = "client!ab", name = "g", descriptor = "Lclient!kc;")
	private final SpotAnimType aSpotAnimType_1;

	@OriginalMember(owner = "client!ab", name = "h", descriptor = "I")
	public final int anInt24;

	@OriginalMember(owner = "client!ab", name = "i", descriptor = "I")
	private final int anInt25;

	@OriginalMember(owner = "client!ab", name = "j", descriptor = "I")
	private final int anInt26;

	@OriginalMember(owner = "client!ab", name = "k", descriptor = "I")
	private final int anInt27;

	@OriginalMember(owner = "client!ab", name = "m", descriptor = "I")
	public final int anInt29;

	@OriginalMember(owner = "client!ab", name = "n", descriptor = "I")
	public final int anInt30;

	@OriginalMember(owner = "client!ab", name = "o", descriptor = "I")
	private final int anInt31;

	@OriginalMember(owner = "client!ab", name = "p", descriptor = "I")
	private final int anInt32;

	@OriginalMember(owner = "client!ab", name = "q", descriptor = "I")
	public final int anInt33;

	@OriginalMember(owner = "client!ab", name = "l", descriptor = "I")
	public final int anInt28;

	@OriginalMember(owner = "client!ab", name = "<init>", descriptor = "(IIIIIIIIIIII)V")
	public ProjectileEntity(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11) {
		this.aSpotAnimType_1 = SpotAnimType.aSpotAnimTypeArray1[arg10];
		this.anInt24 = arg4;
		this.anInt25 = arg11;
		this.anInt26 = arg2;
		this.anInt27 = arg9;
		this.anInt29 = arg6;
		this.anInt30 = arg3;
		this.anInt31 = arg1;
		this.anInt32 = arg7;
		this.anInt33 = arg5;
		this.anInt28 = arg0;
		this.aBoolean5 = false;
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(IIIII)V")
	public final void method19(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(8) double local8;
		if (!this.aBoolean5) {
			local8 = arg2 - this.anInt25;
			@Pc(14) double local14 = (double) (arg1 - this.anInt26);
			@Pc(23) double local23 = Math.sqrt(local8 * local8 + local14 * local14);
			this.aDouble1 = (double) this.anInt25 + local8 * (double) this.anInt32 / local23;
			this.aDouble2 = (double) this.anInt26 + local14 * (double) this.anInt32 / local23;
			this.aDouble3 = this.anInt27;
		}
		local8 = this.anInt30 + 1 - arg3;
		this.aDouble4 = ((double) arg2 - this.aDouble1) / local8;
		this.aDouble5 = ((double) arg1 - this.aDouble2) / local8;
		this.aDouble6 = Math.sqrt(this.aDouble4 * this.aDouble4 + this.aDouble5 * this.aDouble5);
		if (!this.aBoolean5) {
			this.aDouble7 = -this.aDouble6 * Math.tan((double) this.anInt31 * 0.02454369D);
		}
		this.aDouble8 = ((double) arg0 - this.aDouble3 - this.aDouble7 * local8) * 2.0D / (local8 * local8);
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(BI)V")
	public final void method20(@OriginalArg(1) int arg0) {
		this.aBoolean5 = true;
		this.aDouble1 += this.aDouble4 * (double) arg0;
		this.aDouble2 += this.aDouble5 * (double) arg0;
		this.aDouble3 += this.aDouble7 * (double) arg0 + this.aDouble8 * 0.5D * (double) arg0 * (double) arg0;
		this.aDouble7 += this.aDouble8 * (double) arg0;
		this.anInt34 = (int) (Math.atan2(this.aDouble4, this.aDouble5) * 325.949D) + 1024 & 0x7FF;
		this.anInt35 = (int) (Math.atan2(this.aDouble7, this.aDouble6) * 325.949D) & 0x7FF;
		if (this.aSpotAnimType_1.aSeqType_1 == null) {
			return;
		}
		this.anInt37 += arg0;
		while (this.anInt37 > this.aSpotAnimType_1.aSeqType_1.anIntArray188[this.anInt36]) {
			this.anInt37 -= this.aSpotAnimType_1.aSeqType_1.anIntArray188[this.anInt36] + 1;
			this.anInt36++;
			if (this.anInt36 >= this.aSpotAnimType_1.aSeqType_1.anInt543) {
				this.anInt36 = 0;
			}
		}
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public final Model method568() {
		@Pc(3) Model local3 = this.aSpotAnimType_1.method409();
		@Pc(19) Model local19 = new Model(local3, true, !this.aSpotAnimType_1.aBoolean131, this.anInt22, false);
		if (this.aSpotAnimType_1.aSeqType_1 != null) {
			local19.method230();
			local19.method231(this.aSpotAnimType_1.aSeqType_1.anIntArray186[this.anInt36]);
			local19.anIntArrayArray7 = null;
			local19.anIntArrayArray6 = null;
		}
		if (this.aSpotAnimType_1.anInt571 != 128 || this.aSpotAnimType_1.anInt572 != 128) {
			local19.method239(this.aSpotAnimType_1.anInt571, this.aSpotAnimType_1.anInt572, this.aSpotAnimType_1.anInt571);
		}
		local19.method235(this.anInt35);
		local19.method240(this.aSpotAnimType_1.anInt574 + 64, this.aSpotAnimType_1.anInt575 + 850, -30, -50, -30, true);
		return local19;
	}
}
