package com.jagex.game.runetek3.scene.entities;

import com.jagex.core.util.Node;
import com.jagex.game.runetek3.graphics.Model;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!w")
public class Entity extends Node {

	@OriginalMember(owner = "client!w", name = "a", descriptor = "(Z)Lclient!eb;")
	public Model getDrawMethod(int currentCycle) {
		return null;
	}
}
