package com.jagex.game.runetek3.entity;

import com.jagex.core.utils.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!v")
public class ObjStackEntity extends Node {

	@OriginalMember(owner = "client!v", name = "e", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!v", name = "f", descriptor = "I")
	public int amount;
}
