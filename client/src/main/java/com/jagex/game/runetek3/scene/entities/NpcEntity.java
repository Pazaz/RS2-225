package com.jagex.game.runetek3.scene.entities;

import com.jagex.game.runetek3.config.NpcType;
import com.jagex.game.runetek3.config.SeqType;
import com.jagex.game.runetek3.config.SpotAnimType;
import com.jagex.game.runetek3.graphics.Model;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!y")
public class NpcEntity extends PathingEntity {

	@OriginalMember(owner = "client!y", name = "ib", descriptor = "Lclient!bc;")
	public NpcType type;

	@OriginalMember(owner = "client!y", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public Model getDrawMethod() {
		if (this.type == null) {
			return null;
		}

		if (super.spotAnimIndex == -1 || super.spotAnimFrame == -1) {
			return this.getModel();
		}

		@Pc(20) Model self = this.getModel();
		@Pc(25) SpotAnimType spotanim = SpotAnimType.instances[super.spotAnimIndex];
		@Pc(41) Model spot = new Model(spotanim.getModel(), true, !spotanim.disposeAlpha, false);
		spot.translate(-super.spotAnimOffsetY, 0, 0);
		spot.applyGroup();
		spot.applyFrame(spotanim.seq.primaryFrames[super.spotAnimFrame]);
		spot.skinTriangle = null;
		spot.labelVertices = null;

		if (spotanim.resizeh != 128 || spotanim.resizev != 128) {
			spot.scale(spotanim.resizeh, spotanim.resizev, spotanim.resizeh);
		}

		spot.applyLighting(spotanim.ambient + 64, spotanim.contrast + 850, -30, -50, -30, true);
		@Pc(115) Model[] models = new Model[] { self, spot };
		@Pc(123) Model model = new Model(models, 2, true);

		if (this.type.size == 1) {
			model.pickable = true;
		}

		return model;
	}

	@OriginalMember(owner = "client!y", name = "c", descriptor = "(Z)Lclient!eb;")
	private Model getModel() {
		if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
			int frame1 = SeqType.instances[super.primarySeq].primaryFrames[super.primarySeqFrame];
			@Pc(16) int frame2 = -1;
			if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq) {
				frame2 = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
			}
			return this.type.getModel(frame1, frame2, SeqType.instances[super.primarySeq].labelGroups);
		}

		@Pc(14) int frame = -1;
		if (super.secondarySeq >= 0) {
			frame = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
		}

		@Pc(71) Model model = this.type.getModel(frame, -1, null);
		super.height = model.maxBoundY;
		return model;
	}

	@OriginalMember(owner = "client!y", name = "b", descriptor = "(Z)Z")
	@Override
	public boolean isValid() {
		return this.type != null;
	}
}
