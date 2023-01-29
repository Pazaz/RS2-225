package com.jagex.game.runetek3.scene.entities;

import com.jagex.core.util.Node;
import com.jagex.game.runetek3.config.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!nb")
public class LocEntity extends Node {

	@OriginalMember(owner = "client!nb", name = "e", descriptor = "I")
	public int y;

	@OriginalMember(owner = "client!nb", name = "f", descriptor = "I")
	public final int kind;

	@OriginalMember(owner = "client!nb", name = "g", descriptor = "I")
	public final int x;

	@OriginalMember(owner = "client!nb", name = "h", descriptor = "I")
	public final int z;

	@OriginalMember(owner = "client!nb", name = "i", descriptor = "I")
	public final int id;

	@OriginalMember(owner = "client!nb", name = "j", descriptor = "Lclient!jc;")
	public final SeqType seq;

	@OriginalMember(owner = "client!nb", name = "k", descriptor = "I")
	public int seqFrame;

	@OriginalMember(owner = "client!nb", name = "l", descriptor = "I")
	public int seqDelay;

	@OriginalMember(owner = "client!nb", name = "<init>", descriptor = "(ZIIIILclient!jc;II)V")
	public LocEntity(@OriginalArg(0) boolean randomFrame, @OriginalArg(1) int id, @OriginalArg(2) int y, @OriginalArg(4) int kind, @OriginalArg(5) SeqType seq, @OriginalArg(6) int z, @OriginalArg(7) int x) {
		this.y = y;
		this.kind = kind;
		this.x = x;
		this.z = z;
		this.id = id;
		this.seq = seq;

		if (randomFrame && seq.replayoff != -1) {
			this.seqFrame = (int) (Math.random() * (double) this.seq.framecount);
			this.seqDelay = (int) (Math.random() * (double) this.seq.frameDelay[this.seqFrame]);
		} else {
			this.seqFrame = -1;
			this.seqDelay = 0;
		}
	}
}
