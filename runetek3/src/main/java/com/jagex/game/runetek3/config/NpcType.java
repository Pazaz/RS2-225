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
	private static int count;

	@OriginalMember(owner = "client!bc", name = "c", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!bc", name = "d", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!bc", name = "e", descriptor = "[Lclient!bc;")
	private static NpcType[] cache;

	@OriginalMember(owner = "client!bc", name = "f", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!bc", name = "C", descriptor = "Lclient!s;")
	public static Cache models = new Cache(30);

	@OriginalMember(owner = "client!bc", name = "h", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!bc", name = "i", descriptor = "[B")
	public byte[] description;

	@OriginalMember(owner = "client!bc", name = "k", descriptor = "[I")
	private int[] modelIndices;

	@OriginalMember(owner = "client!bc", name = "l", descriptor = "[I")
	private int[] headModelIndices;

	@OriginalMember(owner = "client!bc", name = "s", descriptor = "[I")
	private int[] recol_s;

	@OriginalMember(owner = "client!bc", name = "t", descriptor = "[I")
	private int[] recol_d;

	@OriginalMember(owner = "client!bc", name = "u", descriptor = "[Ljava/lang/String;")
	public String[] op;

	@OriginalMember(owner = "client!bc", name = "g", descriptor = "J")
	private long id = -1L;

	@OriginalMember(owner = "client!bc", name = "j", descriptor = "B")
	public byte size = 1;

	@OriginalMember(owner = "client!bc", name = "m", descriptor = "I")
	public int standSeq = -1;

	@OriginalMember(owner = "client!bc", name = "n", descriptor = "I")
	public int walkSeq = -1;

	@OriginalMember(owner = "client!bc", name = "o", descriptor = "I")
	public int turnAroundSeq = -1;

	@OriginalMember(owner = "client!bc", name = "p", descriptor = "I")
	public int turnRightSeq = -1;

	@OriginalMember(owner = "client!bc", name = "q", descriptor = "I")
	public int turnLeftSeq = -1;

	@OriginalMember(owner = "client!bc", name = "r", descriptor = "Z")
	private boolean disposeAlpha = false;

	@OriginalMember(owner = "client!bc", name = "v", descriptor = "I")
	private int opcode90 = -1;

	@OriginalMember(owner = "client!bc", name = "w", descriptor = "I")
	private int opcode91 = -1;

	@OriginalMember(owner = "client!bc", name = "x", descriptor = "I")
	private int opcode92 = -1;

	@OriginalMember(owner = "client!bc", name = "y", descriptor = "Z")
	public boolean showOnMinimap = true;

	@OriginalMember(owner = "client!bc", name = "z", descriptor = "I")
	public int level = -1;

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
		for (@Pc(51) int i = 0; i < 20; i++) {
			cache[i] = new NpcType();
		}
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(Z)V")
	public static void unload() {
		models = null;
		offsets = null;
		cache = null;
		dat = null;
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(I)Lclient!bc;")
	public static NpcType get(@OriginalArg(0) int id) {
		for (@Pc(1) int i = 0; i < 20; i++) {
			if (cache[i].id == (long) id) {
				return cache[i];
			}
		}
		offset = (offset + 1) % 20;
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
				this.modelIndices = new int[count];
				for (i = 0; i < count; i++) {
					this.modelIndices[i] = buffer.g2();
				}
			} else if (opcode == 2) {
				this.name = buffer.gstr();
			} else if (opcode == 3) {
				this.description = buffer.gstrbyte();
			} else if (opcode == 12) {
				this.size = buffer.g1b();
			} else if (opcode == 13) {
				this.standSeq = buffer.g2();
			} else if (opcode == 14) {
				this.walkSeq = buffer.g2();
			} else if (opcode == 16) {
				this.disposeAlpha = true;
			} else if (opcode == 17) {
				this.walkSeq = buffer.g2();
				this.turnAroundSeq = buffer.g2();
				this.turnRightSeq = buffer.g2();
				this.turnLeftSeq = buffer.g2();
			} else if (opcode >= 30 && opcode < 40) {
				if (this.op == null) {
					this.op = new String[5];
				}
				this.op[opcode - 30] = buffer.gstr();
				if (this.op[opcode - 30].equalsIgnoreCase("hidden")) {
					this.op[opcode - 30] = null;
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
				this.headModelIndices = new int[count];
				for (i = 0; i < count; i++) {
					this.headModelIndices[i] = buffer.g2();
				}
			} else if (opcode == 90) {
				this.opcode90 = buffer.g2();
			} else if (opcode == 91) {
				this.opcode91 = buffer.g2();
			} else if (opcode == 92) {
				this.opcode92 = buffer.g2();
			} else if (opcode == 93) {
				this.showOnMinimap = false;
			} else if (opcode == 95) {
				this.level = buffer.g2();
			} else if (opcode == 97) {
				this.resizex = buffer.g2();
			} else if (opcode == 98) {
				this.resizez = buffer.g2();
			}
		}
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(II[I)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[] arg2) {
		@Pc(9) Model local9 = (Model) models.get(this.id);
		if (local9 == null) {
			@Pc(16) Model[] local16 = new Model[this.modelIndices.length];
			for (@Pc(18) int local18 = 0; local18 < this.modelIndices.length; local18++) {
				local16[local18] = new Model(this.modelIndices[local18]);
			}
			if (local16.length == 1) {
				local9 = local16[0];
			} else {
				local9 = new Model(local16, local16.length);
			}
			if (this.recol_s != null) {
				for (@Pc(60) int local60 = 0; local60 < this.recol_s.length; local60++) {
					local9.recolor(this.recol_s[local60], this.recol_d[local60]);
				}
			}
			local9.applyGroup();
			local9.applyLighting(64, 850, -30, -50, -30, true);
			models.put(this.id, local9);
		}
		@Pc(107) Model local107 = new Model(local9, !this.disposeAlpha);
		if (arg0 != -1 && arg1 != -1) {
			local107.applyFrames(arg1, arg0, arg2);
		} else if (arg0 != -1) {
			local107.applyFrame(arg0);
		}
		if (this.resizex != 128 || this.resizez != 128) {
			local107.scale(this.resizex, this.resizez, this.resizex);
		}
		local107.calculateYBoundaries();
		local107.skinTriangle = null;
		local107.labelVertices = null;
		if (this.size == 1) {
			local107.pickable = true;
		}
		return local107;
	}

	@OriginalMember(owner = "client!bc", name = "b", descriptor = "(Z)Lclient!eb;")
	public final Model getHeadModel() {
		if (this.headModelIndices == null) {
			return null;
		}
		@Pc(17) Model[] local17 = new Model[this.headModelIndices.length];
		for (@Pc(19) int local19 = 0; local19 < this.headModelIndices.length; local19++) {
			local17[local19] = new Model(this.headModelIndices[local19]);
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

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "Z")
	private final boolean flowObfuscator1 = false;

}
