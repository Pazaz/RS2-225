package com.jagex.game.runetek3.scene.entities;

import com.jagex.game.runetek3.config.SpotAnimType;
import com.jagex.game.runetek3.graphics.Model;
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
	public final int plane;

	@OriginalMember(owner = "client!bb", name = "j", descriptor = "I")
	public final int x;

	@OriginalMember(owner = "client!bb", name = "k", descriptor = "I")
	public final int z;

	@OriginalMember(owner = "client!bb", name = "l", descriptor = "I")
	public final int height;

	@OriginalMember(owner = "client!bb", name = "h", descriptor = "I")
	public final int startCycle;

	@OriginalMember(owner = "client!bb", name = "<init>", descriptor = "(IIZIIIII)V")
	public SpotAnimEntity(@OriginalArg(0) int x, @OriginalArg(1) int id, @OriginalArg(3) int z, @OriginalArg(4) int duration, @OriginalArg(5) int height, @OriginalArg(6) int plane, @OriginalArg(7) int startCycle) {
		this.spotanim = SpotAnimType.instances[id];
		this.plane = plane;
		this.x = x;
		this.z = z;
		this.height = height;
		this.startCycle = startCycle + duration;
	}

	@OriginalMember(owner = "client!bb", name = "a", descriptor = "(II)V")
	public void update(@OriginalArg(0) int cycle) {
		this.frameCycle += cycle;

		while (this.frameCycle > this.spotanim.seq.frameDelay[this.seqFrame]) {
			this.frameCycle -= this.spotanim.seq.frameDelay[this.seqFrame] + 1;
			this.seqFrame++;

			if (this.seqFrame >= this.spotanim.seq.framecount) {
				this.seqFrame = 0;
				this.finished = true;
			}
		}
	}

	@OriginalMember(owner = "client!bb", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public Model getDrawMethod(int currentCycle) {
		@Pc(19) Model m = new Model(this.spotanim.getModel(), true, !this.spotanim.disposeAlpha, false);

		if (!this.finished) {
			m.createLabelReferences();
			m.applyTransform(this.spotanim.seq.primaryFrames[this.seqFrame]);
			m.labelFaces = null;
			m.labelVertices = null;
		}

		if (this.spotanim.resizeh != 128 || this.spotanim.resizev != 128) {
			m.scale(this.spotanim.resizeh, this.spotanim.resizev, this.spotanim.resizeh);
		}

		if (this.spotanim.orientation != 0) {
			if (this.spotanim.orientation == 90) {
				m.rotateY90();
			} else if (this.spotanim.orientation == 180) {
				m.rotateY90();
				m.rotateY90();
			} else if (this.spotanim.orientation == 270) {
				m.rotateY90();
				m.rotateY90();
				m.rotateY90();
			}
		}

		m.calculateNormals(this.spotanim.ambient + 64, this.spotanim.contrast + 850, -30, -50, -30, true);
		return m;
	}
}
