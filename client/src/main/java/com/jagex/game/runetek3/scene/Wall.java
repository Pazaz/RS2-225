package com.jagex.game.runetek3.scene;

import com.jagex.game.runetek3.graphics.Model;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!q")
public class Wall {

	@OriginalMember(owner = "client!q", name = "a", descriptor = "I")
	public int plane;

	@OriginalMember(owner = "client!q", name = "b", descriptor = "I")
	public int x;

	@OriginalMember(owner = "client!q", name = "c", descriptor = "I")
	public int z;

	@OriginalMember(owner = "client!q", name = "d", descriptor = "I")
	public int type0;

	@OriginalMember(owner = "client!q", name = "e", descriptor = "I")
	public int type1;

	@OriginalMember(owner = "client!q", name = "f", descriptor = "Lclient!eb;")
	public Model model0;

	@OriginalMember(owner = "client!q", name = "g", descriptor = "Lclient!eb;")
	public Model model1;

	@OriginalMember(owner = "client!q", name = "h", descriptor = "I")
	public int bitset;

	@OriginalMember(owner = "client!q", name = "i", descriptor = "B")
	public byte info;
}
