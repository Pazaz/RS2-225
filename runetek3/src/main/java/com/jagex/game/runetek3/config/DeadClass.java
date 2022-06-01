package com.jagex.game.runetek3.config;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ic")
public class DeadClass {

	@OriginalMember(owner = "client!ic", name = "b", descriptor = "[Lclient!ic;")
	public static DeadClass[] deadArray;

	@OriginalMember(owner = "client!ic", name = "a", descriptor = "I")
	private static final int flowObfuscator1 = 473;

	@OriginalMember(owner = "client!ic", name = "c", descriptor = "Z")
	private boolean deadBool1;

	@OriginalMember(owner = "client!ic", name = "d", descriptor = "Z")
	private boolean deadBool2;

	@OriginalMember(owner = "client!ic", name = "e", descriptor = "Z")
	private boolean deadBool3;

	@OriginalMember(owner = "client!ic", name = "f", descriptor = "Z")
	private boolean deadBool4;

	@OriginalMember(owner = "client!ic", name = "g", descriptor = "Z")
	private boolean deadBool5;
}
