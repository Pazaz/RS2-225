package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.graphics.Model;
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
	private final int[] heads = new int[] { -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!gc", name = "k", descriptor = "Z")
	public boolean disable = false;

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer dat = new Buffer(archive.read("idk.dat", null));
		count = dat.g2();
		if (instances == null) {
			instances = new IdkType[count];
		}

		for (@Pc(19) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new IdkType();
			}

			instances[i].decode(dat);
		}
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer dat) {
		while (true) {
			@Pc(8) int opcode = dat.g1();
			if (opcode == 0) {
				break;
			}

			if (opcode == 1) {
				this.type = dat.g1();
			} else if (opcode == 2) {
				@Pc(26) int count = dat.g1();
				this.models = new int[count];
				for (@Pc(32) int i = 0; i < count; i++) {
					this.models[i] = dat.g2();
				}
			} else if (opcode == 3) {
				this.disable = true;
			} else if (opcode >= 40 && opcode < 50) {
				this.recol_s[opcode - 40] = dat.g2();
			} else if (opcode >= 50 && opcode < 60) {
				this.recol_d[opcode - 50] = dat.g2();
			} else if (opcode >= 60 && opcode < 70) {
				this.heads[opcode - 60] = dat.g2();
			} else {
				System.out.println("Error unrecognised config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "()Lclient!eb;")
	public Model getModel() {
		if (this.models == null) {
			return null;
		}

		@Pc(11) Model[] models = new Model[this.models.length];
		for (@Pc(13) int i = 0; i < this.models.length; i++) {
			models[i] = new Model(false, this.models[i]);
		}

		@Pc(40) Model m;
		if (models.length == 1) {
			m = models[0];
		} else {
			m = new Model(models, models.length);
		}

		for (@Pc(52) int i = 0; i < 6 && this.recol_s[i] != 0; i++) {
			m.recolor(this.recol_s[i], this.recol_d[i]);
		}

		return m;
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Z)Lclient!eb;")
	public Model getHeadModel() {
		@Pc(4) Model[] models = new Model[5];
		@Pc(6) int count = 0;
		for (@Pc(8) int i = 0; i < 5; i++) {
			if (this.heads[i] != -1) {
				models[count++] = new Model(false, this.heads[i]);
			}
		}

		@Pc(39) Model m = new Model(models, count);
		for (@Pc(41) int i = 0; i < 6 && this.recol_s[i] != 0; i++) {
			m.recolor(this.recol_s[i], this.recol_d[i]);
		}

		return m;
	}
}
