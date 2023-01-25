package com.jagex.game.runetek3.scene;

import com.jagex.game.runetek3.graphics.Model;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!j")
public class GroundDecoration {

	@OriginalMember(owner = "client!j", name = "a", descriptor = "I")
	public int plane;

	@OriginalMember(owner = "client!j", name = "b", descriptor = "I")
	public int x;

	@OriginalMember(owner = "client!j", name = "c", descriptor = "I")
	public int z;

	@OriginalMember(owner = "client!j", name = "d", descriptor = "Lclient!eb;")
	public Model model;

	@OriginalMember(owner = "client!j", name = "e", descriptor = "I")
	public int bitset;

	@OriginalMember(owner = "client!j", name = "f", descriptor = "B")
	public byte info;
}
