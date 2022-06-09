package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.utils.Cache;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kc")
public class SpotAnimType {

	@OriginalMember(owner = "client!kc", name = "b", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!kc", name = "c", descriptor = "[Lclient!kc;")
	public static SpotAnimType[] instances;

	@OriginalMember(owner = "client!kc", name = "p", descriptor = "Lclient!s;")
	public static Cache models = new Cache(30);

	@OriginalMember(owner = "client!kc", name = "d", descriptor = "I")
	private int id;

	@OriginalMember(owner = "client!kc", name = "e", descriptor = "I")
	private int model;

	@OriginalMember(owner = "client!kc", name = "g", descriptor = "Lclient!jc;")
	public SeqType seq;

	@OriginalMember(owner = "client!kc", name = "m", descriptor = "I")
	public int rotation;

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
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(13) Buffer buffer = new Buffer(archive.read("spotanim.dat", null));
		count = buffer.g2();
		if (instances == null) {
			instances = new SpotAnimType[count];
		}
		for (@Pc(23) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new SpotAnimType();
			}
			instances[i].id = i;
			instances[i].decode(buffer);
		}
	}

	@OriginalMember(owner = "client!kc", name = "<init>", descriptor = "()V")
	private SpotAnimType() {
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(13) int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}

			if (opcode == 1) {
				this.model = buffer.g2();
			} else if (opcode == 2) {
				this.anim = buffer.g2();
				if (SeqType.instances != null) {
					this.seq = SeqType.instances[this.anim];
				}
			} else if (opcode == 3) {
				this.disposeAlpha = true;
			} else if (opcode == 4) {
				this.resizeh = buffer.g2();
			} else if (opcode == 5) {
				this.resizev = buffer.g2();
			} else if (opcode == 6) {
				this.rotation = buffer.g2();
			} else if (opcode == 7) {
				this.ambient = buffer.g1();
			} else if (opcode == 8) {
				this.contrast = buffer.g1();
			} else if (opcode >= 40 && opcode < 50) {
				this.recol_s[opcode - 40] = buffer.g2();
			} else if (opcode >= 50 && opcode < 60) {
				this.recol_d[opcode - 50] = buffer.g2();
			} else {
				System.out.println("Error unrecognised spotanim config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "()Lclient!eb;")
	public final Model getModel() {
		@Pc(6) Model m = (Model) models.get((long) this.id);
		if (m != null) {
			return m;
		}
		m = new Model(this.model);
		for (@Pc(19) int i = 0; i < 6; i++) {
			if (this.recol_s[0] != 0) {
				m.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}
		models.put((long) this.id, m);
		return m;
	}

	public String toJagConfig() {
		StringBuilder builder = new StringBuilder();

		builder.append("[spotanim_").append(this.id).append("]\n");

		if (this.model != 0) {
			builder.append("model=model_").append(this.model).append("\n");
		}

		if (this.anim != -1) {
			builder.append("anim=anim_").append(this.anim).append("\n");
		}

		// most are gonna require disposeAlpha, but there may be a way to automatically determine this
//		if (this.disposeAlpha) {
//			builder.append("disposeAlpha=yes\n");
//		}

		if (this.resizeh != 128) {
			builder.append("resizeh=").append(this.resizeh).append("\n");
		}

		if (this.resizev != 128) {
			builder.append("resizev=").append(this.resizev).append("\n");
		}

		if (this.rotation != 0) {
			builder.append("rotation=").append(this.rotation).append("\n");
		}

		if (this.ambient != 0) {
			builder.append("ambient=").append(this.ambient).append("\n");
		}

		if (this.contrast != 0) {
			builder.append("contrast=").append(this.contrast).append("\n");
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

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "I")
	private static final int flowObfuscator1 = 473;

}
