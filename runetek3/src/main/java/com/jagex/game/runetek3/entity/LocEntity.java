package com.jagex.game.runetek3.entity;

import com.jagex.core.utils.Node;
import com.jagex.game.runetek3.config.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!nb")
public class LocEntity extends Node {

	@OriginalMember(owner = "client!nb", name = "e", descriptor = "I")
	public int level;

	@OriginalMember(owner = "client!nb", name = "f", descriptor = "I")
	public final int classType;

	@OriginalMember(owner = "client!nb", name = "g", descriptor = "I")
	public final int x;

	@OriginalMember(owner = "client!nb", name = "h", descriptor = "I")
	public final int z;

	@OriginalMember(owner = "client!nb", name = "i", descriptor = "I")
	public final int locIndex;

	@OriginalMember(owner = "client!nb", name = "j", descriptor = "Lclient!jc;")
	public final SeqType seq;

	@OriginalMember(owner = "client!nb", name = "k", descriptor = "I")
	public int seqFrame;

	@OriginalMember(owner = "client!nb", name = "l", descriptor = "I")
	public int seqDelay;

	@OriginalMember(owner = "client!nb", name = "<init>", descriptor = "(ZIIIILclient!jc;II)V")
	public LocEntity(@OriginalArg(0) boolean animated, @OriginalArg(1) int locIndex, @OriginalArg(2) int level, @OriginalArg(3) int arg3, @OriginalArg(4) int classType, @OriginalArg(5) SeqType seq, @OriginalArg(6) int z, @OriginalArg(7) int x) {
		this.level = level;
		this.classType = classType;
		this.x = x;
		this.z = z;
		this.locIndex = locIndex;
		this.seq = seq;
		if (animated && seq.delay != -1) {
			this.seqFrame = (int) (Math.random() * (double) this.seq.frameCount);
			this.seqDelay = (int) (Math.random() * (double) this.seq.frameDelay[this.seqFrame]);
		} else {
			this.seqFrame = -1;
			this.seqDelay = 0;
		}
	}
}
