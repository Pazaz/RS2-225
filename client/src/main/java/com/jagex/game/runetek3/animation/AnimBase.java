package com.jagex.game.runetek3.animation;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!f")
public class AnimBase {

	@OriginalMember(owner = "client!f", name = "a", descriptor = "[Lclient!f;")
	public static AnimBase[] instances;

	@OriginalMember(owner = "client!f", name = "b", descriptor = "I")
	public int length;

	@OriginalMember(owner = "client!f", name = "c", descriptor = "[I")
	public int[] types;

	@OriginalMember(owner = "client!f", name = "d", descriptor = "[[I")
	public int[][] groupLabels;

	@OriginalMember(owner = "client!f", name = "a", descriptor = "(ZLclient!ub;)V")
	public static void unpack(@OriginalArg(1) FileArchive archive) {
		@Pc(11) Buffer head = new Buffer(archive.read("base_head.dat", null));
		@Pc(21) Buffer type = new Buffer(archive.read("base_type.dat", null));
		@Pc(31) Buffer label = new Buffer(archive.read("base_label.dat", null));

		@Pc(34) int count = head.g2();
		instances = new AnimBase[head.g2() + 1];
		for (@Pc(50) int i = 0; i < count; i++) {
			@Pc(55) int id = head.g2();

			@Pc(58) int length = head.g1();
			@Pc(61) int[] transformTypes = new int[length];
			@Pc(64) int[][] groupLabels = new int[length][];
			for (@Pc(66) int group = 0; group < length; group++) {
				transformTypes[group] = type.g1();

				@Pc(76) int groupCount = label.g1();
				groupLabels[group] = new int[groupCount];

				for (@Pc(83) int child = 0; child < groupCount; child++) {
					groupLabels[group][child] = label.g1();
				}
			}

			instances[id] = new AnimBase();
			instances[id].length = length;
			instances[id].types = transformTypes;
			instances[id].groupLabels = groupLabels;
		}
	}
}
