package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.graphics.seq.SeqFrame;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!jc")
public class SeqType {

	@OriginalMember(owner = "client!jc", name = "c", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!jc", name = "d", descriptor = "[Lclient!jc;")
	public static SeqType[] instances;

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

	public int id;

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer buffer = new Buffer(archive.read("seq.dat", null));
		count = buffer.g2();
		if (instances == null) {
			instances = new SeqType[count];
		}

		for (@Pc(27) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new SeqType();
			}

			instances[i].id = i;
			instances[i].decode(buffer);
		}
	}

	@OriginalMember(owner = "client!jc", name = "<init>", descriptor = "()V")
	private SeqType() {
	}

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(13) int opcode = buffer.g1();
			if (opcode == 0) {
				break;
			}

			@Pc(40) int i;
			if (opcode == 1) {
				this.framecount = buffer.g1();
				this.primaryFrames = new int[this.framecount];
				this.secondaryFrames = new int[this.framecount];
				this.frameDelay = new int[this.framecount];
				for (i = 0; i < this.framecount; i++) {
					this.primaryFrames[i] = buffer.g2();
					this.secondaryFrames[i] = buffer.g2();
					if (this.secondaryFrames[i] == 65535) {
						this.secondaryFrames[i] = -1;
					}
					this.frameDelay[i] = buffer.g2();
					if (this.frameDelay[i] == 0) {
						this.frameDelay[i] = SeqFrame.instances[this.primaryFrames[i]].delay;
					}
					if (this.frameDelay[i] == 0) {
						this.frameDelay[i] = 1;
					}
				}
			} else if (opcode == 2) {
				this.replayoff = buffer.g2();
			} else if (opcode == 3) {
				i = buffer.g1();
				this.labelGroups = new int[i + 1];
				for (@Pc(127) int j = 0; j < i; j++) {
					this.labelGroups[j] = buffer.g1();
				}
				this.labelGroups[i] = 9999999;
			} else if (opcode == 4) {
				this.stretches = true;
			} else if (opcode == 5) {
				this.priority = buffer.g1();
			} else if (opcode == 6) {
				this.mainhand = buffer.g2();
			} else if (opcode == 7) {
				this.offhand = buffer.g2();
			} else if (opcode == 8) {
				this.replaycount = buffer.g1();
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

	public String toJagConfig() {
		StringBuilder builder = new StringBuilder();

		builder.append("[seq_").append(this.id).append("]\n");

		if (this.framecount != 0) {
			builder.append("framecount=").append(this.framecount).append("\n");
		}

		if (this.priority != 5) {
			builder.append("priority=").append(this.priority).append("\n");
		}

		if (this.replayoff != -1) {
			builder.append("replayoff=").append(this.replayoff).append("\n");
		}

		if (this.stretches) {
			builder.append("stretches=yes\n");
		}

		if (this.replaycount != 99) {
			builder.append("replaycount=").append(this.replaycount).append("\n");
		}

		if (this.mainhand != -1) {
			builder.append("mainhand=").append(this.mainhand).append("\n");
		}

		if (this.offhand != -1) {
			builder.append("offhand=").append(this.offhand).append("\n");
		}

		// not real names, these might be able to go into a "framegroup" definition
		if (this.framecount != 0) {
			for (int i = 0; i < this.primaryFrames.length; ++i) {
				if (this.primaryFrames[i] == -1) {
					continue;
				}

				builder.append("frame").append(i + 1).append("=").append(this.primaryFrames[i]).append("\n");
			}

			for (int i = 0; i < this.secondaryFrames.length; ++i) {
				if (this.secondaryFrames[i] == -1) {
					continue;
				}

				builder.append("frame_b").append(i + 1).append("=").append(this.secondaryFrames[i]).append("\n");
			}

			for (int i = 0; i < this.frameDelay.length; ++i) {
				if (this.frameDelay[i] == -1) {
					continue;
				}

				builder.append("framedel").append(i + 1).append("=").append(this.frameDelay[i]).append("\n");
			}
		}

		if (this.labelGroups != null) {
			for (int i = 0; i < this.labelGroups.length; ++i) {
				if (this.labelGroups[i] == 9999999) {
					continue;
				}

				builder.append("label").append(i + 1).append("=").append(this.labelGroups[i]).append("\n");
			}
		}

		return builder.toString();
	}

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "Z")
	private static final boolean flowObfuscator1 = true;

	@OriginalMember(owner = "client!jc", name = "b", descriptor = "I")
	private static final int flowObfuscator2 = 473;

}
