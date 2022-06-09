package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.utils.Cache;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!bc")
public class NpcType {

	@OriginalMember(owner = "client!bc", name = "b", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!bc", name = "c", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!bc", name = "d", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!bc", name = "e", descriptor = "[Lclient!bc;")
	private static NpcType[] cache;

	@OriginalMember(owner = "client!bc", name = "f", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!bc", name = "C", descriptor = "Lclient!s;")
	public static Cache builtModels = new Cache(30);

	@OriginalMember(owner = "client!bc", name = "h", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!bc", name = "i", descriptor = "[B")
	public String desc;

	@OriginalMember(owner = "client!bc", name = "k", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!bc", name = "l", descriptor = "[I")
	private int[] headModels;

	@OriginalMember(owner = "client!bc", name = "s", descriptor = "[I")
	private int[] recol_s;

	@OriginalMember(owner = "client!bc", name = "t", descriptor = "[I")
	private int[] recol_d;

	@OriginalMember(owner = "client!bc", name = "u", descriptor = "[Ljava/lang/String;")
	public String[] ops;

	@OriginalMember(owner = "client!bc", name = "g", descriptor = "J")
	private long id = -1L;

	@OriginalMember(owner = "client!bc", name = "j", descriptor = "B")
	public byte size = 1;

	@OriginalMember(owner = "client!bc", name = "m", descriptor = "I")
	public int readyanim = -1;

	@OriginalMember(owner = "client!bc", name = "n", descriptor = "I")
	public int walkanim = -1;

	@OriginalMember(owner = "client!bc", name = "o", descriptor = "I")
	public int walkanim_b = -1;

	@OriginalMember(owner = "client!bc", name = "p", descriptor = "I")
	public int walkanim_r = -1;

	@OriginalMember(owner = "client!bc", name = "q", descriptor = "I")
	public int walkanim_l = -1;

	@OriginalMember(owner = "client!bc", name = "r", descriptor = "Z")
	private boolean disposeAlpha = false;

	@OriginalMember(owner = "client!bc", name = "v", descriptor = "I")
	private int opcode90 = -1;

	@OriginalMember(owner = "client!bc", name = "w", descriptor = "I")
	private int opcode91 = -1;

	@OriginalMember(owner = "client!bc", name = "x", descriptor = "I")
	private int opcode92 = -1;

	@OriginalMember(owner = "client!bc", name = "y", descriptor = "Z")
	public boolean visonmap = true;

	@OriginalMember(owner = "client!bc", name = "z", descriptor = "I")
	public int vislevel = -1;

	@OriginalMember(owner = "client!bc", name = "A", descriptor = "I")
	private int resizex = 128;

	@OriginalMember(owner = "client!bc", name = "B", descriptor = "I")
	private int resizez = 128;

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(Lclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive arg0) {
		dat = new Buffer(arg0.read("npc.dat", null));
		@Pc(21) Buffer idx = new Buffer(arg0.read("npc.idx", null));
		count = idx.g2();
		offsets = new int[count];
		@Pc(29) int offset = 2;
		for (@Pc(31) int i = 0; i < count; i++) {
			offsets[i] = offset;
			offset += idx.g2();
		}
		cache = new NpcType[20];
		for (@Pc(51) int i = 0; i < cache.length; i++) {
			cache[i] = new NpcType();
		}
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(Z)V")
	public static void unload() {
		builtModels = null;
		offsets = null;
		cache = null;
		dat = null;
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(I)Lclient!bc;")
	public static NpcType get(@OriginalArg(0) int id) {
		for (@Pc(1) int i = 0; i < cache.length; i++) {
			if (cache[i].id == (long) id) {
				return cache[i];
			}
		}
		offset = (offset + 1) % cache.length;
		@Pc(33) NpcType type = cache[offset] = new NpcType();
		dat.pos = offsets[id];
		type.id = id;
		type.decode(dat);
		return type;
	}

	@OriginalMember(owner = "client!bc", name = "<init>", descriptor = "()V")
	private NpcType() {
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(10) int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}
			@Pc(19) int count;
			@Pc(25) int i;
			if (opcode == 1) {
				count = buffer.g1();
				this.models = new int[count];
				for (i = 0; i < count; i++) {
					this.models[i] = buffer.g2();
				}
			} else if (opcode == 2) {
				this.name = buffer.gstr();
			} else if (opcode == 3) {
				this.desc = buffer.gstr();
			} else if (opcode == 12) {
				this.size = buffer.g1b();
			} else if (opcode == 13) {
				this.readyanim = buffer.g2();
			} else if (opcode == 14) {
				this.walkanim = buffer.g2();
			} else if (opcode == 16) {
				this.disposeAlpha = true;
			} else if (opcode == 17) {
				this.walkanim = buffer.g2();
				this.walkanim_b = buffer.g2();
				this.walkanim_r = buffer.g2();
				this.walkanim_l = buffer.g2();
			} else if (opcode >= 30 && opcode < 40) {
				if (this.ops == null) {
					this.ops = new String[5];
				}
				this.ops[opcode - 30] = buffer.gstr();
				if (this.ops[opcode - 30].equalsIgnoreCase("hidden")) {
					this.ops[opcode - 30] = null;
				}
			} else if (opcode == 40) {
				count = buffer.g1();
				this.recol_s = new int[count];
				this.recol_d = new int[count];
				for (i = 0; i < count; i++) {
					this.recol_s[i] = buffer.g2();
					this.recol_d[i] = buffer.g2();
				}
			} else if (opcode == 60) {
				count = buffer.g1();
				this.headModels = new int[count];
				for (i = 0; i < count; i++) {
					this.headModels[i] = buffer.g2();
				}
			} else if (opcode == 90) {
				this.opcode90 = buffer.g2();
			} else if (opcode == 91) {
				this.opcode91 = buffer.g2();
			} else if (opcode == 92) {
				this.opcode92 = buffer.g2();
			} else if (opcode == 93) {
				this.visonmap = false;
			} else if (opcode == 95) {
				this.vislevel = buffer.g2();
			} else if (opcode == 97) {
				this.resizex = buffer.g2();
			} else if (opcode == 98) {
				this.resizez = buffer.g2();
			}
		}
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(II[I)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[] arg2) {
		@Pc(9) Model m = (Model) builtModels.get(this.id);
		if (m == null) {
			@Pc(16) Model[] local16 = new Model[this.models.length];
			for (@Pc(18) int local18 = 0; local18 < this.models.length; local18++) {
				local16[local18] = new Model(this.models[local18]);
			}
			if (local16.length == 1) {
				m = local16[0];
			} else {
				m = new Model(local16, local16.length);
			}
			if (this.recol_s != null) {
				for (@Pc(60) int local60 = 0; local60 < this.recol_s.length; local60++) {
					m.recolor(this.recol_s[local60], this.recol_d[local60]);
				}
			}
			m.applyGroup();
			m.applyLighting(64, 850, -30, -50, -30, true);
			builtModels.put(this.id, m);
		}
		@Pc(107) Model m2 = new Model(m, !this.disposeAlpha);
		if (arg0 != -1 && arg1 != -1) {
			m2.applyFrames(arg1, arg0, arg2);
		} else if (arg0 != -1) {
			m2.applyFrame(arg0);
		}
		if (this.resizex != 128 || this.resizez != 128) {
			m2.scale(this.resizex, this.resizez, this.resizex);
		}
		m2.calculateYBoundaries();
		m2.skinTriangle = null;
		m2.labelVertices = null;
		if (this.size == 1) {
			m2.pickable = true;
		}
		return m2;
	}

	@OriginalMember(owner = "client!bc", name = "b", descriptor = "(Z)Lclient!eb;")
	public final Model getHeadModel() {
		if (this.headModels == null) {
			return null;
		}
		@Pc(17) Model[] local17 = new Model[this.headModels.length];
		for (@Pc(19) int local19 = 0; local19 < this.headModels.length; local19++) {
			local17[local19] = new Model(this.headModels[local19]);
		}
		@Pc(46) Model local46;
		if (local17.length == 1) {
			local46 = local17[0];
		} else {
			local46 = new Model(local17, local17.length);
		}
		if (this.recol_s != null) {
			for (@Pc(61) int local61 = 0; local61 < this.recol_s.length; local61++) {
				local46.recolor(this.recol_s[local61], this.recol_d[local61]);
			}
		}
		return local46;
	}

	public String toJagConfig() {
		StringBuilder builder = new StringBuilder();

		builder.append("[npc_").append(this.id).append("]\n");

		if (this.name != null) {
			builder.append("name=").append(this.name).append("\n");
		}

		if (this.desc != null) {
			builder.append("desc=").append(this.desc).append("\n");
		}

		if (this.models != null) {
			for (int i = 0; i < this.models.length; ++i) {
				builder.append("model").append(i + 1).append("=model_").append(this.models[i]).append("\n");
			}
		}

		if (this.headModels != null) {
			for (int i = 0; i < this.headModels.length; ++i) {
				builder.append("head").append(i + 1).append("=model_").append(this.headModels[i]).append("\n");
			}
		}

		if (this.readyanim != -1) {
			builder.append("readyanim=anim_").append(this.readyanim).append("\n");
		}

		if (this.walkanim != -1) {
			builder.append("walkanim=anim_").append(this.walkanim).append("\n");
		}

		if (this.walkanim_b != -1) {
			builder.append("walkanim_b=anim_").append(this.walkanim_b).append("\n");
		}

		if (this.walkanim_r != -1) {
			builder.append("walkanim_r=anim_").append(this.walkanim_r).append("\n");
		}

		if (this.walkanim_l != -1) {
			builder.append("walkanim_l=anim_").append(this.walkanim_l).append("\n");
		}

		if (this.size != 1) {
			builder.append("size=").append(this.size).append("\n");
		}

		// this might be able to be automatically determined if the models have readable metadata
		// about alpha properties during the cache packing process
//		if (this.disposeAlpha) {
//			builder.append("disposeAlpha=yes").append("\n");
//		}

		// none of these are used
//		if (this.opcode90 != -1) {
//			builder.append("opcode90=").append(this.opcode90).append("\n");
//		}
//
//		if (this.opcode91 != -1) {
//			builder.append("opcode91=").append(this.opcode91).append("\n");
//		}
//
//		if (this.opcode92 != -1) {
//			builder.append("opcode92=").append(this.opcode92).append("\n");
//		}

		if (this.resizex != 128) {
			builder.append("resizex=").append(this.resizex).append("\n");
		}

		if (this.resizez != 128) {
			builder.append("resizez=").append(this.resizez).append("\n");
		}

		if (this.recol_s != null) {
			for (int i = 0; i < this.recol_s.length; ++i) {
				builder.append("recol").append(i + 1).append("s=").append(this.recol_s[i]).append("\n");
				builder.append("recol").append(i + 1).append("d=").append(this.recol_d[i]).append("\n");
			}
		}

		if (this.ops != null) {
			for (int i = 0; i < this.ops.length; ++i) {
				if (this.ops[i] == null) {
					continue;
				}

				builder.append("op").append(i + 1).append("=").append(this.ops[i]).append("\n");
			}
		}

		if (this.vislevel != -1) {
			builder.append("vislevel=").append(this.vislevel).append("\n");
		}

		if (!this.visonmap) {
			builder.append("visonmap=no\n");
		}

		return builder.toString();
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "Z")
	private final boolean flowObfuscator1 = false;

}
