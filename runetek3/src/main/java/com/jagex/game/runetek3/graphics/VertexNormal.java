package com.jagex.game.runetek3.graphics;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!n")
public class VertexNormal {

	@OriginalMember(owner = "client!n", name = "a", descriptor = "I")
	public int x;

	@OriginalMember(owner = "client!n", name = "b", descriptor = "I")
	public int y;

	@OriginalMember(owner = "client!n", name = "c", descriptor = "I")
	public int z;

	@OriginalMember(owner = "client!n", name = "d", descriptor = "I")
	public int magnitude;
}
