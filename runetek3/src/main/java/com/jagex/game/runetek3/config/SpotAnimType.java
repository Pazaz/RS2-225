package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.util.Cache;
import com.jagex.game.runetek3.graphics.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kc")
public class SpotAnimType {

	@OriginalMember(owner = "client!kc", name = "c", descriptor = "[Lclient!kc;")
	public static SpotAnimType[] instances;

	@OriginalMember(owner = "client!kc", name = "p", descriptor = "Lclient!s;")
	public static Cache models = new Cache(30);

	@OriginalMember(owner = "client!kc", name = "b", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!kc", name = "d", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!kc", name = "e", descriptor = "I")
	private int model;

	@OriginalMember(owner = "client!kc", name = "g", descriptor = "Lclient!jc;")
	public SeqType seq;

	@OriginalMember(owner = "client!kc", name = "m", descriptor = "I")
	public int orientation;

	@OriginalMember(owner = "client!kc", name = "n", descriptor = "I")
	public int ambient;

	@OriginalMember(owner = "client!kc", name = "o", descriptor = "I")
	public int contrast;

	@OriginalMember(owner = "client!kc", name = "f", descriptor = "I")
	private int anim = -1;

	@OriginalMember(owner = "client!kc", name = "h", descriptor = "Z")
	public boolean disposeAlpha = false;

	@OriginalMember(owner = "client!kc", name = "i", descriptor = "[I")
	private final int[] recol_s = new int[6];

	@OriginalMember(owner = "client!kc", name = "j", descriptor = "[I")
	private final int[] recol_d = new int[6];

	@OriginalMember(owner = "client!kc", name = "k", descriptor = "I")
	public int resizeh = 128;

	@OriginalMember(owner = "client!kc", name = "l", descriptor = "I")
	public int resizev = 128;

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive archive) {
		@Pc(13) Buffer dat = new Buffer(archive.read("spotanim.dat", null));
		count = dat.g2();

		if (instances == null) {
			instances = new SpotAnimType[count];
		}

		for (@Pc(23) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new SpotAnimType();
			}

			instances[i].id = i;
			instances[i].decode(dat);
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer dat) {
		while (true) {
			@Pc(5) int opcode = dat.g1();
			if (opcode == 0) {
				break;
			}

			if (opcode == 1) {
				this.model = dat.g2();
			} else if (opcode == 2) {
				this.anim = dat.g2();

				if (SeqType.instances != null) {
					this.seq = SeqType.instances[this.anim];
				}
			} else if (opcode == 3) {
				this.disposeAlpha = true;
			} else if (opcode == 4) {
				this.resizeh = dat.g2();
			} else if (opcode == 5) {
				this.resizev = dat.g2();
			} else if (opcode == 6) {
				this.orientation = dat.g2();
			} else if (opcode == 7) {
				this.ambient = dat.g1();
			} else if (opcode == 8) {
				this.contrast = dat.g1();
			} else if (opcode >= 40 && opcode < 50) {
				this.recol_s[opcode - 40] = dat.g2();
			} else if (opcode >= 50 && opcode < 60) {
				this.recol_d[opcode - 50] = dat.g2();
			} else {
				System.out.println("Error unrecognised spotanim config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "()Lclient!eb;")
	public Model getModel() {
		@Pc(6) Model m = (Model) models.get(this.id);
		if (m != null) {
			return m;
		}

		m = new Model(this.model);
		for (@Pc(19) int i = 0; i < 6; i++) {
			if (this.recol_s[0] != 0) {
				m.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}

		models.put(this.id, m);
		return m;
	}
}
