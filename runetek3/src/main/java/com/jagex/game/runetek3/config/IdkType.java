package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!gc")
public class IdkType {

	@OriginalMember(owner = "client!gc", name = "d", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!gc", name = "e", descriptor = "[Lclient!gc;")
	public static IdkType[] instances;

	@OriginalMember(owner = "client!gc", name = "g", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!gc", name = "f", descriptor = "I")
	public int type = -1;

	@OriginalMember(owner = "client!gc", name = "h", descriptor = "[I")
	private final int[] recol_s = new int[6];

	@OriginalMember(owner = "client!gc", name = "i", descriptor = "[I")
	private final int[] recol_d = new int[6];

	@OriginalMember(owner = "client!gc", name = "j", descriptor = "[I")
	private final int[] headModels = new int[] { -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!gc", name = "k", descriptor = "Z")
	public boolean disable = false;

	public int id;

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer buffer = new Buffer(archive.read("idk.dat", null));
		count = buffer.g2();
		if (instances == null) {
			instances = new IdkType[count];
		}

		for (@Pc(19) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new IdkType();
			}

			instances[i].id = i;
			instances[i].decode(buffer);
		}
	}

	@OriginalMember(owner = "client!gc", name = "<init>", descriptor = "()V")
	private IdkType() {
	}

	public static int findPart(int type, int index) {
		int part = 0;
		for (int i = 0; i < count; ++i) {
			if (instances[i].type == type) {
				if (index == part) {
					return i;
				}
				part++;
			}
		}
		return -1;
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(8) int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}

			if (opcode == 1) {
				this.type = buffer.g1();
			} else if (opcode == 2) {
				@Pc(26) int count = buffer.g1();
				this.models = new int[count];
				for (@Pc(32) int i = 0; i < count; i++) {
					this.models[i] = buffer.g2();
				}
			} else if (opcode == 3) {
				this.disable = true;
			} else if (opcode >= 40 && opcode < 50) {
				this.recol_s[opcode - 40] = buffer.g2();
			} else if (opcode >= 50 && opcode < 60) {
				this.recol_d[opcode - 50] = buffer.g2();
			} else if (opcode >= 60 && opcode < 70) {
				this.headModels[opcode - 60] = buffer.g2();
			} else {
				System.out.println("Error unrecognised config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "()Lclient!eb;")
	public final Model getModel() {
		if (this.models == null) {
			return null;
		}
		@Pc(11) Model[] models = new Model[this.models.length];
		for (@Pc(13) int i = 0; i < this.models.length; i++) {
			models[i] = new Model(this.models[i]);
		}
		@Pc(40) Model body;
		if (models.length == 1) {
			body = models[0];
		} else {
			body = new Model(models, models.length);
		}
		for (@Pc(52) int i = 0; i < 6 && this.recol_s[i] != 0; i++) {
			body.recolor(this.recol_s[i], this.recol_d[i]);
		}
		return body;
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Z)Lclient!eb;")
	public final Model getHeadModel() {
		@Pc(4) Model[] models = new Model[5];
		@Pc(6) int count = 0;
		for (@Pc(8) int i = 0; i < 5; i++) {
			if (this.headModels[i] != -1) {
				models[count++] = new Model(this.headModels[i]);
			}
		}
		@Pc(39) Model head = new Model(models, count);
		for (@Pc(41) int i = 0; i < 6 && this.recol_s[i] != 0; i++) {
			head.recolor(this.recol_s[i], this.recol_d[i]);
		}
		return head;
	}


	public String toJagConfig() {
		StringBuilder builder = new StringBuilder();

		builder.append("[idk_").append(this.id).append("]\n");

		if (this.type != -1) {
			String typeName = Integer.toString(this.type);
			switch (this.type) {
				case 0:
					typeName = "BODYPART_MALE_HAIR";
					break;
				case 1:
					typeName = "BODYPART_MALE_JAW";
					break;
				case 2:
					typeName = "BODYPART_MALE_TORSO";
					break;
				case 3:
					typeName = "BODYPART_MALE_ARMS";
					break;
				case 4:
					typeName = "BODYPART_MALE_HANDS";
					break;
				case 5:
					typeName = "BODYPART_MALE_LEGS";
					break;
				case 6:
					typeName = "BODYPART_MALE_FEET";
					break;
				case 7:
					typeName = "BODYPART_FEMALE_HAIR";
					break;
				// 8 is probably FEMALE_JAW but it isn't used
				case 9:
					typeName = "BODYPART_FEMALE_TORSO";
					break;
				case 10:
					typeName = "BODYPART_FEMALE_ARMS";
					break;
				case 11:
					typeName = "BODYPART_FEMALE_HANDS";
					break;
				case 12:
					typeName = "BODYPART_FEMALE_LEGS";
					break;
				case 13:
					typeName = "BODYPART_FEMALE_FEET";
					break;
			}
			builder.append("bodypart=^").append(typeName).append("\n");
		}

		if (this.disable) {
			builder.append("disable=yes\n");
		}

		if (this.models != null) {
			for (int i = 0; i < this.models.length; ++i) {
				builder.append("model").append(i + 1).append("=model_").append(this.models[i]).append("\n");
			}
		}

		for (int i = 0; i < this.headModels.length; ++i) {
			if (this.headModels[i] == -1) {
				continue;
			}

			builder.append("head").append(i + 1).append("=model_").append(this.headModels[i]).append("\n");
		}

		for (int i = 0; i < this.recol_s.length; ++i) {
			if (this.recol_s[i] == 0) {
				continue;
			}

			builder.append("recol").append(i + 1).append("s=").append(this.recol_s[i]).append("\n");
			builder.append("recol").append(i + 1).append("d=").append(this.recol_d[i]).append("\n");
		}

		return builder.toString();
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "I")
	private static int flowObfuscator1;

	@OriginalMember(owner = "client!gc", name = "b", descriptor = "I")
	private static final int flowObfuscator2 = 473;

	@OriginalMember(owner = "client!gc", name = "c", descriptor = "Z")
	private final boolean flowObfuscator3 = false;

}
