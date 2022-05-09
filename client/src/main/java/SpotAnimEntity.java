import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!bb")
public final class SpotAnimEntity extends Entity {

	@OriginalMember(owner = "client!bb", name = "e", descriptor = "I")
	private int anInt58;

	@OriginalMember(owner = "client!bb", name = "m", descriptor = "I")
	private int anInt65;

	@OriginalMember(owner = "client!bb", name = "n", descriptor = "I")
	private int anInt66;

	@OriginalMember(owner = "client!bb", name = "o", descriptor = "Z")
	public boolean aBoolean17 = false;

	@OriginalMember(owner = "client!bb", name = "g", descriptor = "Lclient!kc;")
	private final SpotAnimType aSpotAnimType_2;

	@OriginalMember(owner = "client!bb", name = "i", descriptor = "I")
	public final int anInt61;

	@OriginalMember(owner = "client!bb", name = "f", descriptor = "I")
	private int anInt59;

	@OriginalMember(owner = "client!bb", name = "j", descriptor = "I")
	public final int anInt62;

	@OriginalMember(owner = "client!bb", name = "k", descriptor = "I")
	public final int anInt63;

	@OriginalMember(owner = "client!bb", name = "l", descriptor = "I")
	public final int anInt64;

	@OriginalMember(owner = "client!bb", name = "h", descriptor = "I")
	public final int anInt60;

	@OriginalMember(owner = "client!bb", name = "<init>", descriptor = "(IIZIIIII)V")
	public SpotAnimEntity(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		this.aSpotAnimType_2 = SpotAnimType.aSpotAnimTypeArray1[arg1];
		this.anInt61 = arg6;
		this.anInt62 = arg0;
		this.anInt63 = arg3;
		this.anInt64 = arg5;
		this.anInt60 = arg7 + arg4;
		this.aBoolean17 = false;
	}

	@OriginalMember(owner = "client!bb", name = "a", descriptor = "(II)V")
	public final void method29(@OriginalArg(0) int arg0) {
		this.anInt66 += arg0;
		while (true) {
			do {
				do {
					if (this.anInt66 <= this.aSpotAnimType_2.aSeqType_1.anIntArray188[this.anInt65]) {
						return;
					}
					this.anInt66 -= this.aSpotAnimType_2.aSeqType_1.anIntArray188[this.anInt65] + 1;
					this.anInt65++;
				} while (this.anInt65 < this.aSpotAnimType_2.aSeqType_1.anInt543);
			} while (this.anInt65 >= 0 && this.anInt65 < this.aSpotAnimType_2.aSeqType_1.anInt543);
			this.anInt65 = 0;
			this.aBoolean17 = true;
		}
	}

	@OriginalMember(owner = "client!bb", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public final Model method568() {
		@Pc(3) Model local3 = this.aSpotAnimType_2.method409();
		@Pc(19) Model local19 = new Model(local3, true, !this.aSpotAnimType_2.aBoolean131, this.anInt58, false);
		if (!this.aBoolean17) {
			local19.method230();
			local19.method231(this.aSpotAnimType_2.aSeqType_1.anIntArray186[this.anInt65]);
			local19.anIntArrayArray7 = null;
			local19.anIntArrayArray6 = null;
		}
		if (this.aSpotAnimType_2.anInt571 != 128 || this.aSpotAnimType_2.anInt572 != 128) {
			local19.method239(this.aSpotAnimType_2.anInt571, this.aSpotAnimType_2.anInt572, this.aSpotAnimType_2.anInt571);
		}
		if (this.aSpotAnimType_2.anInt573 != 0) {
			if (this.aSpotAnimType_2.anInt573 == 90) {
				local19.method234();
			}
			if (this.aSpotAnimType_2.anInt573 == 180) {
				local19.method234();
				local19.method234();
			}
			if (this.aSpotAnimType_2.anInt573 == 270) {
				local19.method234();
				local19.method234();
				local19.method234();
			}
		}
		local19.method240(this.aSpotAnimType_2.anInt574 + 64, this.aSpotAnimType_2.anInt575 + 850, -30, -50, -30, true);
		return local19;
	}
}
