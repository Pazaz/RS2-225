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
	private int[] modelIndices;

	@OriginalMember(owner = "client!gc", name = "f", descriptor = "I")
	public int type = -1;

	@OriginalMember(owner = "client!gc", name = "h", descriptor = "[I")
	private final int[] recol_s = new int[6];

	@OriginalMember(owner = "client!gc", name = "i", descriptor = "[I")
	private final int[] recol_d = new int[6];

	@OriginalMember(owner = "client!gc", name = "j", descriptor = "[I")
	private final int[] headModelIndices = new int[] { -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!gc", name = "k", descriptor = "Z")
	public boolean validStyle = false;

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
				this.modelIndices = new int[count];
				for (@Pc(32) int i = 0; i < count; i++) {
					this.modelIndices[i] = buffer.g2();
				}
			} else if (opcode == 3) {
				this.validStyle = true;
			} else if (opcode >= 40 && opcode < 50) {
				this.recol_s[opcode - 40] = buffer.g2();
			} else if (opcode >= 50 && opcode < 60) {
				this.recol_d[opcode - 50] = buffer.g2();
			} else if (opcode >= 60 && opcode < 70) {
				this.headModelIndices[opcode - 60] = buffer.g2();
			} else {
				System.out.println("Error unrecognised config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "()Lclient!eb;")
	public final Model getModel() {
		if (this.modelIndices == null) {
			return null;
		}
		@Pc(11) Model[] models = new Model[this.modelIndices.length];
		for (@Pc(13) int i = 0; i < this.modelIndices.length; i++) {
			models[i] = new Model(this.modelIndices[i]);
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
			if (this.headModelIndices[i] != -1) {
				models[count++] = new Model(this.headModelIndices[i]);
			}
		}
		@Pc(39) Model head = new Model(models, count);
		for (@Pc(41) int i = 0; i < 6 && this.recol_s[i] != 0; i++) {
			head.recolor(this.recol_s[i], this.recol_d[i]);
		}
		return head;
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "I")
	private static int flowObfuscator1;

	@OriginalMember(owner = "client!gc", name = "b", descriptor = "I")
	private static final int flowObfuscator2 = 473;

	@OriginalMember(owner = "client!gc", name = "c", descriptor = "Z")
	private final boolean flowObfuscator3 = false;

}
