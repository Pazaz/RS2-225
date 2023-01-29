package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.animation.SeqFrame;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!jc")
public class SeqType {

	@OriginalMember(owner = "client!jc", name = "d", descriptor = "[Lclient!jc;")
	public static SeqType[] instances;

	@OriginalMember(owner = "client!jc", name = "c", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!jc", name = "e", descriptor = "I")
	public int framecount;

	@OriginalMember(owner = "client!jc", name = "f", descriptor = "[I")
	public int[] primaryFrames;

	@OriginalMember(owner = "client!jc", name = "g", descriptor = "[I")
	public int[] secondaryFrames;

	@OriginalMember(owner = "client!jc", name = "h", descriptor = "[I")
	public int[] frameDelay;

	@OriginalMember(owner = "client!jc", name = "j", descriptor = "[I")
	public int[] labelGroups;

	@OriginalMember(owner = "client!jc", name = "i", descriptor = "I")
	public int replayoff = -1;

	@OriginalMember(owner = "client!jc", name = "k", descriptor = "Z")
	public boolean stretches = false;

	@OriginalMember(owner = "client!jc", name = "l", descriptor = "I")
	public int priority = 5;

	@OriginalMember(owner = "client!jc", name = "m", descriptor = "I")
	public int mainhand = -1;

	@OriginalMember(owner = "client!jc", name = "n", descriptor = "I")
	public int offhand = -1;

	@OriginalMember(owner = "client!jc", name = "o", descriptor = "I")
	public int replaycount = 99;

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer dat = new Buffer(archive.read("seq.dat", null));
		count = dat.g2();

		if (instances == null) {
			instances = new SeqType[count];
		}

		for (@Pc(27) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new SeqType();
			}

			instances[i].decode(dat);
		}
	}

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer dat) {
		while (true) {
			@Pc(5) int opcode = dat.g1();
			if (opcode == 0) {
				break;
			}

			if (opcode == 1) {
				this.framecount = dat.g1();
				this.primaryFrames = new int[this.framecount];
				this.secondaryFrames = new int[this.framecount];
				this.frameDelay = new int[this.framecount];

				for (@Pc(40) int i = 0; i < this.framecount; i++) {
					this.primaryFrames[i] = dat.g2();
					this.secondaryFrames[i] = dat.g2();
					if (this.secondaryFrames[i] == 65535) {
						this.secondaryFrames[i] = -1;
					}

					this.frameDelay[i] = dat.g2();
					if (this.frameDelay[i] == 0) {
						this.frameDelay[i] = SeqFrame.instances[this.primaryFrames[i]].delay;
					}

					if (this.frameDelay[i] == 0) {
						this.frameDelay[i] = 1;
					}
				}
			} else if (opcode == 2) {
				this.replayoff = dat.g2();
			} else if (opcode == 3) {
				int count = dat.g1();
				this.labelGroups = new int[count + 1];
				for (@Pc(127) int i = 0; i < count; i++) {
					this.labelGroups[i] = dat.g1();
				}
				this.labelGroups[count] = 9999999;
			} else if (opcode == 4) {
				this.stretches = true;
			} else if (opcode == 5) {
				this.priority = dat.g1();
			} else if (opcode == 6) {
				this.mainhand = dat.g2();
			} else if (opcode == 7) {
				this.offhand = dat.g2();
			} else if (opcode == 8) {
				this.replaycount = dat.g1();
			} else {
				System.out.println("Error unrecognised seq config code: " + opcode);
			}
		}

		if (this.framecount == 0) {
			this.framecount = 1;
			this.primaryFrames = new int[1];
			this.primaryFrames[0] = -1;
			this.secondaryFrames = new int[1];
			this.secondaryFrames[0] = -1;
			this.frameDelay = new int[1];
			this.frameDelay[0] = -1;
		}
	}
}