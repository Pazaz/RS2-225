import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!y")
public final class NpcEntity extends PathingEntity {

	@OriginalMember(owner = "client!y", name = "ib", descriptor = "Lclient!bc;")
	public NpcType type;

	@OriginalMember(owner = "client!y", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public Model getDrawMethod() {
		if (this.type == null) {
			return null;
		} else if (super.spotAnimIndex == -1 || super.spotAnimFrame == -1) {
			return this.getModel();
		} else {
			@Pc(20) Model local20 = this.getModel();
			@Pc(25) SpotAnimType local25 = SpotAnimType.instances[super.spotAnimIndex];
			@Pc(41) Model local41 = new Model(local25.getModel(), true, !local25.disposeAlpha, false);
			local41.translate(-super.spotAnimOffsetY, 0, 0);
			local41.applyGroup();
			local41.applyFrame(local25.seq.primaryFrames[super.spotAnimFrame]);
			local41.skinTriangle = null;
			local41.labelVertices = null;
			if (local25.resizeh != 128 || local25.resizev != 128) {
				local41.scale(local25.resizeh, local25.resizev, local25.resizeh);
			}
			local41.applyLighting(local25.ambient + 64, local25.contrast + 850, -30, -50, -30, true);
			@Pc(115) Model[] local115 = new Model[] { local20, local41 };
			@Pc(123) Model local123 = new Model(local115, 2, true);
			if (this.type.size == 1) {
				local123.pickable = true;
			}
			return local123;
		}
	}

	@OriginalMember(owner = "client!y", name = "c", descriptor = "(Z)Lclient!eb;")
	private Model getModel() {
		@Pc(14) int local14;
		if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
			local14 = SeqType.instances[super.primarySeq].primaryFrames[super.primarySeqFrame];
			@Pc(16) int local16 = -1;
			if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq) {
				local16 = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
			}
			return this.type.getModel(local14, local16, SeqType.instances[super.primarySeq].labelGroups);
		}
		local14 = -1;
		if (super.secondarySeq >= 0) {
			local14 = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
		}
		@Pc(71) Model local71 = this.type.getModel(local14, -1, null);
		super.height = local71.maxBoundY;
		return local71;
	}

	@OriginalMember(owner = "client!y", name = "b", descriptor = "(Z)Z")
	@Override
	public boolean isValid() {
		return this.type != null;
	}
}
