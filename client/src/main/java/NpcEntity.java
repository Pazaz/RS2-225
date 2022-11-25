import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!y")
public final class NpcEntity extends PathingEntity {

	@OriginalMember(owner = "client!y", name = "gb", descriptor = "I")
	private int flowObfuscator1;

	@OriginalMember(owner = "client!y", name = "ib", descriptor = "Lclient!bc;")
	public NpcType type;

	@OriginalMember(owner = "client!y", name = "hb", descriptor = "Z")
	private boolean flowObfuscator2 = false;

	@OriginalMember(owner = "client!y", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public Model getDrawMethod(@OriginalArg(0) boolean arg0) {
		try {
			if (this.type == null) {
				return null;
			} else if (super.spotAnimIndex == -1 || super.spotAnimFrame == -1) {
				return this.getModel(false);
			} else {
				@Pc(20) Model local20 = this.getModel(false);
				@Pc(25) SpotAnimType local25 = SpotAnimType.instances[super.spotAnimIndex];
				@Pc(41) Model local41 = new Model(local25.getModel(), true, !local25.disposeAlpha, this.flowObfuscator1, false);
				local41.translate(-super.spotAnimOffsetY, 0, -122, 0);
				local41.applyGroup(4);
				local41.applyFrame(-16599, local25.seq.primaryFrames[super.spotAnimFrame]);
				local41.skinTriangle = null;
				local41.labelVertices = null;
				if (!arg0) {
					throw new NullPointerException();
				}
				if (local25.resizeh != 128 || local25.resizev != 128) {
					local41.scale(local25.resizeh, 2, local25.resizev, local25.resizeh);
				}
				local41.applyLighting(local25.ambient + 64, local25.contrast + 850, -30, -50, -30, true);
				@Pc(115) Model[] local115 = new Model[] { local20, local41 };
				@Pc(123) Model local123 = new Model(local115, (byte) -31, 2, true);
				if (this.type.size == 1) {
					local123.pickable = true;
				}
				return local123;
			}
		} catch (@Pc(134) RuntimeException local134) {
			signlink.reporterror("64857, " + arg0 + ", " + local134.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!y", name = "c", descriptor = "(Z)Lclient!eb;")
	private Model getModel(@OriginalArg(0) boolean arg0) {
		try {
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
			if (arg0) {
				throw new NullPointerException();
			}
			if (super.secondarySeq >= 0) {
				local14 = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
			}
			@Pc(71) Model local71 = this.type.getModel(local14, -1, null);
			super.height = local71.maxBoundY;
			return local71;
		} catch (@Pc(78) RuntimeException local78) {
			signlink.reporterror("9268, " + arg0 + ", " + local78.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!y", name = "b", descriptor = "(Z)Z")
	@Override
	public boolean isValid(@OriginalArg(0) boolean arg0) {
		try {
			if (arg0) {
				this.flowObfuscator2 = !this.flowObfuscator2;
			}
			return this.type != null;
		} catch (@Pc(17) RuntimeException local17) {
			signlink.reporterror("55429, " + arg0 + ", " + local17.toString());
			throw new RuntimeException();
		}
	}
}
