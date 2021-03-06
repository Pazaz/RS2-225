package com.jagex.game.runetek3.entity;

import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!k")
public class ObjEntity {

	@OriginalMember(owner = "client!k", name = "a", descriptor = "I")
	public int y;

	@OriginalMember(owner = "client!k", name = "b", descriptor = "I")
	public int x;

	@OriginalMember(owner = "client!k", name = "c", descriptor = "I")
	public int z;

	@OriginalMember(owner = "client!k", name = "d", descriptor = "Lclient!eb;")
	public Model model0;

	@OriginalMember(owner = "client!k", name = "e", descriptor = "Lclient!eb;")
	public Model model1;

	@OriginalMember(owner = "client!k", name = "f", descriptor = "Lclient!eb;")
	public Model model2;

	@OriginalMember(owner = "client!k", name = "g", descriptor = "I")
	public int bitset;

	@OriginalMember(owner = "client!k", name = "h", descriptor = "I")
	public int offsetY;
}
