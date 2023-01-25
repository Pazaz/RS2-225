package com.jagex.game.runetek3.scene.entities;

import com.jagex.core.util.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!v")
public class ObjEntity extends Node {

	@OriginalMember(owner = "client!v", name = "e", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!v", name = "f", descriptor = "I")
	public int count;
}