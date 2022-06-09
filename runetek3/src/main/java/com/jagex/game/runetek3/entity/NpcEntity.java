package com.jagex.game.runetek3.entity;

import com.jagex.game.runetek3.config.NpcType;
import com.jagex.game.runetek3.config.SeqType;
import com.jagex.game.runetek3.config.SpotAnimType;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!y")
public class NpcEntity extends PathingEntity {

	@OriginalMember(owner = "client!y", name = "ib", descriptor = "Lclient!bc;")
	public NpcType info;

	@OriginalMember(owner = "client!y", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public final Model getDrawMethod(int cycle) {
		if (this.info == null) {
			return null;
		} else if (super.spotAnimIndex == -1 || super.spotAnimFrame == -1) {
			return this.getModel();
		} else {
			@Pc(20) Model m = this.getModel();
			@Pc(25) SpotAnimType spotAnim = SpotAnimType.instances[super.spotAnimIndex];
			@Pc(41) Model spotAnimModel = new Model(spotAnim.getModel(), true, !spotAnim.disposeAlpha, false);
			spotAnimModel.translate(-super.spotAnimOffsetY, 0, 0);
			spotAnimModel.applyGroup();
			spotAnimModel.applyFrame(spotAnim.seq.primaryFrames[super.spotAnimFrame]);
			spotAnimModel.skinTriangle = null;
			spotAnimModel.labelVertices = null;
			if (spotAnim.resizeh != 128 || spotAnim.resizev != 128) {
				spotAnimModel.scale(spotAnim.resizeh, spotAnim.resizev, spotAnim.resizeh);
			}
			spotAnimModel.applyLighting(spotAnim.ambient + 64, spotAnim.contrast + 850, -30, -50, -30, true);
			@Pc(115) Model[] models = new Model[] { m, spotAnimModel };
			@Pc(123) Model animated = new Model(models, 2, true);
			if (this.info.size == 1) {
				animated.pickable = true;
			}
			return animated;
		}
	}

	@OriginalMember(owner = "client!y", name = "c", descriptor = "(Z)Lclient!eb;")
	private Model getModel() {
		@Pc(14) int frame;
		if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
			frame = SeqType.instances[super.primarySeq].primaryFrames[super.primarySeqFrame];
			@Pc(16) int frame2 = -1;
			if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq) {
				frame2 = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
			}
			return this.info.getModel(frame, frame2, SeqType.instances[super.primarySeq].labelGroups);
		}
		frame = -1;
		if (super.secondarySeq >= 0) {
			frame = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
		}
		@Pc(71) Model model = this.info.getModel(frame, -1, null);
		super.height = model.maxBoundY;
		return model;
	}

	@OriginalMember(owner = "client!y", name = "b", descriptor = "(Z)Z")
	@Override
	public final boolean isValid() {
		return this.info != null;
	}

	@OriginalMember(owner = "client!y", name = "gb", descriptor = "I")
	private int flowObfuscator1;

	@OriginalMember(owner = "client!y", name = "hb", descriptor = "Z")
	private final boolean flowObfuscator2 = false;

}
