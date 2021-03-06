package com.jagex.game.runetek3.entity;

import com.jagex.core.utils.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!mb")
public class TemporaryLoc extends Node {

	@OriginalMember(owner = "client!mb", name = "f", descriptor = "I")
	public final int level;

	@OriginalMember(owner = "client!mb", name = "g", descriptor = "I")
	public final int classType;

	@OriginalMember(owner = "client!mb", name = "h", descriptor = "I")
	public final int tileX;

	@OriginalMember(owner = "client!mb", name = "i", descriptor = "I")
	public final int tileZ;

	@OriginalMember(owner = "client!mb", name = "j", descriptor = "I")
	public final int locIndex;

	@OriginalMember(owner = "client!mb", name = "k", descriptor = "I")
	public final int orientation;

	@OriginalMember(owner = "client!mb", name = "l", descriptor = "I")
	public final int type;

	@OriginalMember(owner = "client!mb", name = "m", descriptor = "I")
	public final int lastCycle;

	@OriginalMember(owner = "client!mb", name = "<init>", descriptor = "(IIIIIIIII)V")
	public TemporaryLoc(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8) {
		this.level = arg0;
		this.classType = arg8;
		this.tileX = arg7;
		this.tileZ = arg2;
		this.locIndex = arg6;
		this.orientation = arg1;
		this.type = arg5;
		this.lastCycle = arg3;
	}

	@OriginalMember(owner = "client!mb", name = "e", descriptor = "I")
	private final int flowObfuscator1 = 27808;

}
