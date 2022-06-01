package com.jagex.game.runetek3.entity;

import com.jagex.game.runetek3.config.SpotAnimType;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!bb")
public class SpotAnimEntity extends Entity {

	@OriginalMember(owner = "client!bb", name = "m", descriptor = "I")
	private int seqFrame;

	@OriginalMember(owner = "client!bb", name = "n", descriptor = "I")
	private int frameCycle;

	@OriginalMember(owner = "client!bb", name = "o", descriptor = "Z")
	public boolean finished = false;

	@OriginalMember(owner = "client!bb", name = "g", descriptor = "Lclient!kc;")
	private final SpotAnimType spotanim;

	@OriginalMember(owner = "client!bb", name = "i", descriptor = "I")
	public final int level;

	@OriginalMember(owner = "client!bb", name = "j", descriptor = "I")
	public final int x;

	@OriginalMember(owner = "client!bb", name = "k", descriptor = "I")
	public final int z;

	@OriginalMember(owner = "client!bb", name = "l", descriptor = "I")
	public final int y;

	@OriginalMember(owner = "client!bb", name = "h", descriptor = "I")
	public final int firstCycle;

	@OriginalMember(owner = "client!bb", name = "<init>", descriptor = "(IIZIIIII)V")
	public SpotAnimEntity(@OriginalArg(0) int x, @OriginalArg(1) int spotanimIndex, @OriginalArg(3) int z, @OriginalArg(4) int duration, @OriginalArg(5) int y, @OriginalArg(6) int level, @OriginalArg(7) int startCycle) {
		this.spotanim = SpotAnimType.instances[spotanimIndex];
		this.level = level;
		this.x = x;
		this.z = z;
		this.y = y;
		this.firstCycle = startCycle + duration;
		this.finished = false;
	}

	@OriginalMember(owner = "client!bb", name = "a", descriptor = "(II)V")
	public void update(@OriginalArg(0) int cycle) {
		this.frameCycle += cycle;
		while (true) {
			do {
				do {
					if (this.frameCycle <= this.spotanim.seq.frameDelay[this.seqFrame]) {
						return;
					}
					this.frameCycle -= this.spotanim.seq.frameDelay[this.seqFrame] + 1;
					this.seqFrame++;
				} while (this.seqFrame < this.spotanim.seq.frameCount);
			} while (this.seqFrame >= 0 && this.seqFrame < this.spotanim.seq.frameCount);
			this.seqFrame = 0;
			this.finished = true;
		}
	}

	@OriginalMember(owner = "client!bb", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public final Model getDrawMethod(int cycle) {
		@Pc(3) Model spotanimModel = this.spotanim.getModel();
		@Pc(19) Model m = new Model(spotanimModel, true, !this.spotanim.disposeAlpha, false);
		if (!this.finished) {
			m.applyGroup();
			m.applyFrame(this.spotanim.seq.primaryFrames[this.seqFrame]);
			m.skinTriangle = null;
			m.labelVertices = null;
		}
		if (this.spotanim.breadthScale != 128 || this.spotanim.depthScale != 128) {
			m.scale(this.spotanim.breadthScale, this.spotanim.depthScale, this.spotanim.breadthScale);
		}
		if (this.spotanim.orientation != 0) {
			if (this.spotanim.orientation == 90) {
				m.rotateCounterClockwise();
			}
			if (this.spotanim.orientation == 180) {
				m.rotateCounterClockwise();
				m.rotateCounterClockwise();
			}
			if (this.spotanim.orientation == 270) {
				m.rotateCounterClockwise();
				m.rotateCounterClockwise();
				m.rotateCounterClockwise();
			}
		}
		m.applyLighting(this.spotanim.ambience + 64, this.spotanim.modelShadow + 850, -30, -50, -30, true);
		return m;
	}

	@OriginalMember(owner = "client!bb", name = "e", descriptor = "I")
	private int flowObfuscator1;

	@OriginalMember(owner = "client!bb", name = "f", descriptor = "I")
	private int flowObfuscator2;

}
