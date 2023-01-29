package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.util.Cache;
import com.jagex.game.runetek3.graphics.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!bc")
public class NpcType {

	@OriginalMember(owner = "client!bc", name = "C", descriptor = "Lclient!s;")
	public static Cache builtModels = new Cache(30);

	@OriginalMember(owner = "client!bc", name = "b", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!bc", name = "c", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!bc", name = "d", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!bc", name = "e", descriptor = "[Lclient!bc;")
	private static NpcType[] instances;

	@OriginalMember(owner = "client!bc", name = "f", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!bc", name = "h", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!bc", name = "i", descriptor = "[B")
	public byte[] desc;

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
	public long id = -1L;

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
	public static void unpack(@OriginalArg(0) FileArchive archive) {
		dat = new Buffer(archive.read("npc.dat", null));
		@Pc(21) Buffer idx = new Buffer(archive.read("npc.idx", null));
		count = idx.g2();
		offsets = new int[count];

		@Pc(29) int offset = 2;
		for (@Pc(31) int i = 0; i < count; i++) {
			offsets[i] = offset;
			offset += idx.g2();
		}

		instances = new NpcType[20];
		for (@Pc(51) int i = 0; i < 20; i++) {
			instances[i] = new NpcType();
		}
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(Z)V")
	public static void unload() {
		builtModels = null;
		offsets = null;
		instances = null;
		dat = null;
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(I)Lclient!bc;")
	public static NpcType get(@OriginalArg(0) int id) {
		for (@Pc(1) int i = 0; i < 20; i++) {
			if (instances[i].id == (long) id) {
				return instances[i];
			}
		}

		offset = (offset + 1) % 20;
		@Pc(33) NpcType npc = instances[offset] = new NpcType();
		dat.pos = offsets[id];
		npc.id = id;
		npc.decode(dat);
		return npc;
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer dat) {
		while (true) {
			@Pc(10) int opcode = dat.g1();
			if (opcode == 0) {
				break;
			}

			if (opcode == 1) {
				@Pc(19) int count = dat.g1();
				this.models = new int[count];
				for (@Pc(25) int i = 0; i < count; i++) {
					this.models[i] = dat.g2();
				}
			} else if (opcode == 2) {
				this.name = dat.gstr();
			} else if (opcode == 3) {
				this.desc = dat.gstrbyte();
			} else if (opcode == 12) {
				this.size = dat.g1b();
			} else if (opcode == 13) {
				this.readyanim = dat.g2();
			} else if (opcode == 14) {
				this.walkanim = dat.g2();
			} else if (opcode == 16) {
				this.disposeAlpha = true;
			} else if (opcode == 17) {
				this.walkanim = dat.g2();
				this.walkanim_b = dat.g2();
				this.walkanim_r = dat.g2();
				this.walkanim_l = dat.g2();
			} else if (opcode >= 30 && opcode < 40) {
				if (this.ops == null) {
					this.ops = new String[5];
				}

				this.ops[opcode - 30] = dat.gstr();
				if (this.ops[opcode - 30].equalsIgnoreCase("hidden")) {
					this.ops[opcode - 30] = null;
				}
			} else if (opcode == 40) {
				int count = dat.g1();
				this.recol_s = new int[count];
				this.recol_d = new int[count];
				for (int i = 0; i < count; i++) {
					this.recol_s[i] = dat.g2();
					this.recol_d[i] = dat.g2();
				}
			} else if (opcode == 60) {
				int count = dat.g1();
				this.headModels = new int[count];
				for (int i = 0; i < count; i++) {
					this.headModels[i] = dat.g2();
				}
			} else if (opcode == 90) {
				this.opcode90 = dat.g2();
			} else if (opcode == 91) {
				this.opcode91 = dat.g2();
			} else if (opcode == 92) {
				this.opcode92 = dat.g2();
			} else if (opcode == 93) {
				this.visonmap = false;
			} else if (opcode == 95) {
				this.vislevel = dat.g2();
			} else if (opcode == 97) {
				this.resizex = dat.g2();
			} else if (opcode == 98) {
				this.resizez = dat.g2();
			}
		}
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(II[I)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int scaleX, @OriginalArg(1) int scaleY, @OriginalArg(2) int[] scaleZ) {
		@Pc(9) Model model = (Model) builtModels.get(this.id);
		if (model == null) {
			@Pc(16) Model[] models = new Model[this.models.length];
			for (@Pc(18) int i = 0; i < this.models.length; i++) {
				models[i] = new Model(this.models[i]);
			}

			if (models.length == 1) {
				model = models[0];
			} else {
				model = new Model(models, models.length);
			}

			if (this.recol_s != null) {
				for (@Pc(60) int i = 0; i < this.recol_s.length; i++) {
					model.recolor(this.recol_s[i], this.recol_d[i]);
				}
			}

			model.createLabelReferences();
			model.calculateNormals(64, 850, -30, -50, -30, true);
			builtModels.put(this.id, model);
		}

		model = new Model(model, !this.disposeAlpha);
		if (scaleX != -1 && scaleY != -1) {
			model.applyTransforms(scaleY, scaleX, scaleZ);
		} else if (scaleX != -1) {
			model.applyTransform(scaleX);
		}

		if (this.resizex != 128 || this.resizez != 128) {
			model.scale(this.resizex, this.resizez, this.resizex);
		}

		model.calculateBoundsCylinder();
		model.labelFaces = null;
		model.labelVertices = null;

		if (this.size == 1) {
			model.pickable = true;
		}

		return model;
	}

	@OriginalMember(owner = "client!bc", name = "b", descriptor = "(Z)Lclient!eb;")
	public Model getHeadModel() {
		if (this.headModels == null) {
			return null;
		}

		@Pc(17) Model[] models = new Model[this.headModels.length];
		for (@Pc(19) int i = 0; i < this.headModels.length; i++) {
			models[i] = new Model(this.headModels[i]);
		}

		@Pc(46) Model model;
		if (models.length == 1) {
			model = models[0];
		} else {
			model = new Model(models, models.length);
		}

		if (this.recol_s != null) {
			for (@Pc(61) int i = 0; i < this.recol_s.length; i++) {
				model.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}

		return model;
	}
}
