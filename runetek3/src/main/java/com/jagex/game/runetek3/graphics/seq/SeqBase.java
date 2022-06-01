package com.jagex.game.runetek3.graphics.seq;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!f")
public class SeqBase {

	@OriginalMember(owner = "client!f", name = "a", descriptor = "[Lclient!f;")
	public static SeqBase[] instances;

	@OriginalMember(owner = "client!f", name = "b", descriptor = "I")
	private int length;

	@OriginalMember(owner = "client!f", name = "c", descriptor = "[I")
	public int[] types;

	@OriginalMember(owner = "client!f", name = "d", descriptor = "[[I")
	public int[][] groupLabels;

	@OriginalMember(owner = "client!f", name = "a", descriptor = "(ZLclient!ub;)V")
	public static void decode(@OriginalArg(1) FileArchive archive) {
		@Pc(11) Buffer head = new Buffer(archive.read("base_head.dat", null));
		@Pc(21) Buffer type = new Buffer(archive.read("base_type.dat", null));
		@Pc(31) Buffer label = new Buffer(archive.read("base_label.dat", null));
		@Pc(34) int total = head.g2();
		@Pc(37) int count = head.g2();
		instances = new SeqBase[count + 1];
		for (@Pc(50) int i = 0; i < total; i++) {
			@Pc(55) int index = head.g2();
			@Pc(58) int length = head.g1();
			@Pc(61) int[] transformTypes = new int[length];
			@Pc(64) int[][] groups = new int[length][];
			for (@Pc(66) int j = 0; j < length; j++) {
				transformTypes[j] = type.g1();
				@Pc(76) int groupCount = label.g1();
				groups[j] = new int[groupCount];
				for (@Pc(83) int k = 0; k < groupCount; k++) {
					groups[j][k] = label.g1();
				}
			}
			instances[index] = new SeqBase();
			instances[index].length = length;
			instances[index].types = transformTypes;
			instances[index].groupLabels = groups;
		}
	}

	@OriginalMember(owner = "client!f", name = "<init>", descriptor = "()V")
	private SeqBase() {
	}
}
