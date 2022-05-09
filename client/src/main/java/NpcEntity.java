import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!y")
public final class NpcEntity extends PathingEntity {

	@OriginalMember(owner = "client!y", name = "gb", descriptor = "I")
	private int anInt876;

	@OriginalMember(owner = "client!y", name = "ib", descriptor = "Lclient!bc;")
	public NpcType aNpcType_1;

	@OriginalMember(owner = "client!y", name = "hb", descriptor = "Z")
	private final boolean aBoolean157 = false;

	@OriginalMember(owner = "client!y", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public final Model getDrawMethod() {
		if (this.aNpcType_1 == null) {
			return null;
		} else if (super.anInt912 == -1 || super.anInt913 == -1) {
			return this.getModel();
		} else {
			@Pc(20) Model local20 = this.getModel();
			@Pc(25) SpotAnimType local25 = SpotAnimType.aSpotAnimTypeArray1[super.anInt912];
			@Pc(41) Model local41 = new Model(local25.getModel(), true, !local25.aBoolean131, this.anInt876, false);
			local41.translate(-super.anInt916, 0, 0);
			local41.applyGroup();
			local41.applyFrame(local25.aSeqType_1.anIntArray186[super.anInt913]);
			local41.anIntArrayArray7 = null;
			local41.anIntArrayArray6 = null;
			if (local25.anInt571 != 128 || local25.anInt572 != 128) {
				local41.scale(local25.anInt571, local25.anInt572, local25.anInt571);
			}
			local41.applyLighting(local25.anInt574 + 64, local25.anInt575 + 850, -30, -50, -30, true);
			@Pc(115) Model[] local115 = new Model[] { local20, local41 };
			@Pc(123) Model local123 = new Model(local115, (byte) -31, 2, true);
			if (this.aNpcType_1.aByte4 == 1) {
				local123.aBoolean84 = true;
			}
			return local123;
		}
	}

	@OriginalMember(owner = "client!y", name = "c", descriptor = "(Z)Lclient!eb;")
	private Model getModel() {
		@Pc(14) int local14;
		if (super.anInt907 >= 0 && super.anInt910 == 0) {
			local14 = SeqType.aSeqTypeArray1[super.anInt907].anIntArray186[super.anInt908];
			@Pc(16) int local16 = -1;
			if (super.anInt904 >= 0 && super.anInt904 != super.anInt886) {
				local16 = SeqType.aSeqTypeArray1[super.anInt904].anIntArray186[super.anInt905];
			}
			return this.aNpcType_1.getModel(local14, local16, SeqType.aSeqTypeArray1[super.anInt907].anIntArray189);
		}
		local14 = -1;
		if (super.anInt904 >= 0) {
			local14 = SeqType.aSeqTypeArray1[super.anInt904].anIntArray186[super.anInt905];
		}
		@Pc(71) Model local71 = this.aNpcType_1.getModel(local14, -1, null);
		super.anInt925 = local71.anInt368;
		return local71;
	}

	@OriginalMember(owner = "client!y", name = "b", descriptor = "(Z)Z")
	@Override
	public final boolean isValid() {
		return this.aNpcType_1 != null;
	}
}
