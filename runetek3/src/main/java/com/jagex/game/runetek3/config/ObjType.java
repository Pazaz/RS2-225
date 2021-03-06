package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.utils.Cache;
import com.jagex.game.runetek3.graphics.Sprite;
import com.jagex.game.runetek3.graphics.Draw2D;
import com.jagex.game.runetek3.graphics.Draw3D;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!cc")
public class ObjType {

	@OriginalMember(owner = "client!cc", name = "e", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!cc", name = "f", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!cc", name = "g", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!cc", name = "h", descriptor = "[Lclient!cc;")
	private static ObjType[] cache;

	@OriginalMember(owner = "client!cc", name = "i", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!cc", name = "j", descriptor = "Z")
	public static boolean isMember = true;

	@OriginalMember(owner = "client!cc", name = "T", descriptor = "Lclient!s;")
	public static Cache models = new Cache(50);

	@OriginalMember(owner = "client!cc", name = "U", descriptor = "Lclient!s;")
	public static Cache icons = new Cache(200);

	@OriginalMember(owner = "client!cc", name = "l", descriptor = "I")
	private int model;

	@OriginalMember(owner = "client!cc", name = "m", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!cc", name = "n", descriptor = "[B")
	public String desc;

	@OriginalMember(owner = "client!cc", name = "o", descriptor = "[I")
	private int[] recol_s;

	@OriginalMember(owner = "client!cc", name = "p", descriptor = "[I")
	private int[] recol_d;

	@OriginalMember(owner = "client!cc", name = "q", descriptor = "I")
	public int zoom2d;

	@OriginalMember(owner = "client!cc", name = "r", descriptor = "I")
	public int xan2d;

	@OriginalMember(owner = "client!cc", name = "s", descriptor = "I")
	public int yan2d;

	@OriginalMember(owner = "client!cc", name = "t", descriptor = "I")
	private int zan2d;

	@OriginalMember(owner = "client!cc", name = "u", descriptor = "I")
	private int xof2d;

	@OriginalMember(owner = "client!cc", name = "v", descriptor = "I")
	private int yof2d;

	@OriginalMember(owner = "client!cc", name = "w", descriptor = "Z")
	private boolean opcode9;

	@OriginalMember(owner = "client!cc", name = "x", descriptor = "I")
	private int opcode10;

	@OriginalMember(owner = "client!cc", name = "y", descriptor = "Z")
	public boolean stackable;

	@OriginalMember(owner = "client!cc", name = "z", descriptor = "I")
	public int cost;

	@OriginalMember(owner = "client!cc", name = "A", descriptor = "Z")
	private boolean members;

	@OriginalMember(owner = "client!cc", name = "B", descriptor = "[Ljava/lang/String;")
	public String[] ops;

	@OriginalMember(owner = "client!cc", name = "C", descriptor = "[Ljava/lang/String;")
	public String[] iops;

	@OriginalMember(owner = "client!cc", name = "D", descriptor = "I")
	private int manwear;

	@OriginalMember(owner = "client!cc", name = "E", descriptor = "I")
	private int manwear2;

	@OriginalMember(owner = "client!cc", name = "F", descriptor = "B")
	private byte manwearOffsetY;

	@OriginalMember(owner = "client!cc", name = "G", descriptor = "I")
	private int womanwear;

	@OriginalMember(owner = "client!cc", name = "H", descriptor = "I")
	private int womanwear2;

	@OriginalMember(owner = "client!cc", name = "I", descriptor = "B")
	private byte womanwearOffsetY;

	@OriginalMember(owner = "client!cc", name = "J", descriptor = "I")
	private int manwear3;

	@OriginalMember(owner = "client!cc", name = "K", descriptor = "I")
	private int womanwear3;

	@OriginalMember(owner = "client!cc", name = "L", descriptor = "I")
	private int manhead;

	@OriginalMember(owner = "client!cc", name = "M", descriptor = "I")
	private int manhead2;

	@OriginalMember(owner = "client!cc", name = "N", descriptor = "I")
	private int womanhead;

	@OriginalMember(owner = "client!cc", name = "O", descriptor = "I")
	private int womanhead2;

	@OriginalMember(owner = "client!cc", name = "P", descriptor = "[I")
	private int[] countobj;

	@OriginalMember(owner = "client!cc", name = "Q", descriptor = "[I")
	private int[] countco;

	@OriginalMember(owner = "client!cc", name = "R", descriptor = "I")
	public int certlink;

	@OriginalMember(owner = "client!cc", name = "S", descriptor = "I")
	public int certtemplate;

	@OriginalMember(owner = "client!cc", name = "k", descriptor = "I")
	public int id = -1;

	public String identifier;

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(Lclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		dat = new Buffer(archive.read("obj.dat", null));
		@Pc(21) Buffer idx = new Buffer(archive.read("obj.idx", null));
		count = idx.g2();
		offsets = new int[count];
		@Pc(29) int offset = 2;
		for (@Pc(31) int i = 0; i < count; i++) {
			offsets[i] = offset;
			offset += idx.g2();
		}

		cache = new ObjType[10];
		for (@Pc(51) int i = 0; i < cache.length; i++) {
			cache[i] = new ObjType();
		}
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(Z)V")
	public static void unload() {
		models = null;
		icons = null;
		offsets = null;
		cache = null;
		dat = null;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(I)Lclient!cc;")
	public static ObjType get(@OriginalArg(0) int id) {
		for (@Pc(1) int i = 0; i < cache.length; i++) {
			if (cache[i].id == id) {
				return cache[i];
			}
		}

		offset = (offset + 1) % cache.length;
		@Pc(27) ObjType obj = cache[offset];
		dat.pos = offsets[id];
		obj.id = id;
		obj.reset();
		obj.decode(dat);
		if (obj.certtemplate != -1) {
			obj.toCertificate();
		}
		if (!isMember && obj.members) {
			obj.name = "Members Object";
			obj.desc = "Login to a members' server to use this object.";
			obj.ops = null;
			obj.iops = null;
		}
		return obj;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(III)Lclient!hb;")
	public static Sprite getSprite(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(7) Sprite local7 = (Sprite) icons.get((long) arg0);
		if (local7 != null && local7.cropH != arg1 && local7.cropH != -1) {
			local7.unlink();
			local7 = null;
		}
		if (local7 != null) {
			return local7;
		}
		@Pc(28) ObjType local28 = get(arg0);
		if (local28.countobj == null) {
			arg1 = -1;
		}
		@Pc(38) int local38;
		@Pc(40) int local40;
		if (arg1 > 1) {
			local38 = -1;
			for (local40 = 0; local40 < 10; local40++) {
				if (arg1 >= local28.countco[local40] && local28.countco[local40] != 0) {
					local38 = local28.countobj[local40];
				}
			}
			if (local38 != -1) {
				local28 = get(local38);
			}
		}
		local7 = new Sprite(32, 32);
		local38 = Draw3D.centerX3D;
		local40 = Draw3D.centerY3D;
		@Pc(80) int[] local80 = Draw3D.offsets;
		@Pc(82) int[] local82 = Draw2D.data;
		@Pc(84) int local84 = Draw2D.width;
		@Pc(86) int local86 = Draw2D.height;
		@Pc(88) int local88 = Draw2D.left;
		@Pc(90) int local90 = Draw2D.right;
		@Pc(92) int local92 = Draw2D.top;
		@Pc(94) int local94 = Draw2D.bottom;
		Draw3D.jagged = false;
		Draw2D.prepare(32, local7.pixels, 32);
		Draw2D.fillRect(0, 0, 32, 32, 0);
		Draw3D.prepareOffsets();
		@Pc(115) Model local115 = local28.getModel(1);
		@Pc(125) int local125 = Draw3D.sin[local28.xan2d] * local28.zoom2d >> 16;
		@Pc(135) int local135 = Draw3D.cos[local28.xan2d] * local28.zoom2d >> 16;
		local115.draw(local28.yan2d, local28.zan2d, local28.xan2d, local28.xof2d, local125 + local115.maxBoundY / 2 + local28.yof2d, local135 + local28.yof2d);
		for (@Pc(168) int local168 = 31; local168 >= 0; local168--) {
			for (local135 = 31; local135 >= 0; local135--) {
				if (local7.pixels[local168 + local135 * 32] == 0) {
					if (local168 > 0 && local7.pixels[local168 + local135 * 32 - 1] > 1) {
						local7.pixels[local168 + local135 * 32] = 1;
					} else if (local135 > 0 && local7.pixels[local168 + (local135 - 1) * 32] > 1) {
						local7.pixels[local168 + local135 * 32] = 1;
					} else if (local168 < 31 && local7.pixels[local168 + local135 * 32 + 1] > 1) {
						local7.pixels[local168 + local135 * 32] = 1;
					} else if (local135 < 31 && local7.pixels[local168 + (local135 + 1) * 32] > 1) {
						local7.pixels[local168 + local135 * 32] = 1;
					}
				}
			}
		}
		for (@Pc(291) int local291 = 31; local291 >= 0; local291--) {
			for (local135 = 31; local135 >= 0; local135--) {
				if (local7.pixels[local291 + local135 * 32] == 0 && local291 > 0 && local135 > 0 && local7.pixels[local291 + (local135 - 1) * 32 - 1] > 0) {
					local7.pixels[local291 + local135 * 32] = 3153952;
				}
			}
		}
		if (local28.certtemplate != -1) {
			@Pc(348) Sprite local348 = getSprite(local28.certlink, 10);
			@Pc(351) int local351 = local348.cropW;
			@Pc(354) int local354 = local348.cropH;
			local348.cropW = 32;
			local348.cropH = 32;
			local348.draw();
			local348.cropW = local351;
			local348.cropH = local354;
		}
		icons.put((long) arg0, local7);
		Draw2D.prepare(local84, local82, local86);
		Draw2D.setBounds(local88, local90, local92, local94);
		Draw3D.centerX3D = local38;
		Draw3D.centerY3D = local40;
		Draw3D.offsets = local80;
		Draw3D.jagged = true;
		if (local28.stackable) {
			local7.cropW = 33;
		} else {
			local7.cropW = 32;
		}
		local7.cropH = arg1;
		return local7;
	}

	@OriginalMember(owner = "client!cc", name = "<init>", descriptor = "()V")
	private ObjType() {
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "()V")
	private void reset() {
		this.model = 0;
		this.name = null;
		this.desc = null;
		this.recol_s = null;
		this.recol_d = null;
		this.zoom2d = 2000;
		this.xan2d = 0;
		this.yan2d = 0;
		this.zan2d = 0;
		this.xof2d = 0;
		this.yof2d = 0;
		this.opcode9 = false;
		this.opcode10 = -1;
		this.stackable = false;
		this.cost = 1;
		this.members = false;
		this.ops = null;
		this.iops = null;
		this.manwear = -1;
		this.manwear2 = -1;
		this.manwearOffsetY = 0;
		this.womanwear = -1;
		this.womanwear2 = -1;
		this.womanwearOffsetY = 0;
		this.manwear3 = -1;
		this.womanwear3 = -1;
		this.manhead = -1;
		this.manhead2 = -1;
		this.womanhead = -1;
		this.womanhead2 = -1;
		this.countobj = null;
		this.countco = null;
		this.certlink = -1;
		this.certtemplate = -1;
		this.identifier = null;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(10) int opcode = buffer.g1();

			if (opcode == 0) {
				return;
			}

			if (opcode == 1) {
				this.model = buffer.g2();
			} else if (opcode == 2) {
				this.name = buffer.gstr();
			} else if (opcode == 3) {
				this.desc = buffer.gstr();
			} else if (opcode == 4) {
				this.zoom2d = buffer.g2();
			} else if (opcode == 5) {
				this.xan2d = buffer.g2();
			} else if (opcode == 6) {
				this.yan2d = buffer.g2();
			} else if (opcode == 7) {
				this.xof2d = buffer.g2();
				if (this.xof2d > 32767) {
					this.xof2d -= 65536;
				}
			} else if (opcode == 8) {
				this.yof2d = buffer.g2();
				if (this.yof2d > 32767) {
					this.yof2d -= 65536;
				}
			} else if (opcode == 9) {
				this.opcode9 = true;
			} else if (opcode == 10) {
				this.opcode10 = buffer.g2();
			} else if (opcode == 11) {
				this.stackable = true;
			} else if (opcode == 12) {
				this.cost = buffer.g4();
			} else if (opcode == 16) {
				this.members = true;
			} else if (opcode == 23) {
				this.manwear = buffer.g2();
				this.manwearOffsetY = buffer.g1b();
			} else if (opcode == 24) {
				this.manwear2 = buffer.g2();
			} else if (opcode == 25) {
				this.womanwear = buffer.g2();
				this.womanwearOffsetY = buffer.g1b();
			} else if (opcode == 26) {
				this.womanwear2 = buffer.g2();
			} else if (opcode >= 30 && opcode < 35) {
				if (this.ops == null) {
					this.ops = new String[5];
				}
				this.ops[opcode - 30] = buffer.gstr();
				if (this.ops[opcode - 30].equalsIgnoreCase("hidden")) {
					this.ops[opcode - 30] = null;
				}
			} else if (opcode >= 35 && opcode < 40) {
				if (this.iops == null) {
					this.iops = new String[5];
				}
				this.iops[opcode - 35] = buffer.gstr();
			} else if (opcode == 40) {
				@Pc(260) int count = buffer.g1();
				this.recol_s = new int[count];
				this.recol_d = new int[count];
				for (@Pc(270) int i = 0; i < count; i++) {
					this.recol_s[i] = buffer.g2();
					this.recol_d[i] = buffer.g2();
				}
			} else if (opcode == 78) {
				this.manwear3 = buffer.g2();
			} else if (opcode == 79) {
				this.womanwear3 = buffer.g2();
			} else if (opcode == 90) {
				this.manhead = buffer.g2();
			} else if (opcode == 91) {
				this.womanhead = buffer.g2();
			} else if (opcode == 92) {
				this.manhead2 = buffer.g2();
			} else if (opcode == 93) {
				this.womanhead2 = buffer.g2();
			} else if (opcode == 95) {
				this.zan2d = buffer.g2();
			} else if (opcode == 97) {
				this.certlink = buffer.g2();
			} else if (opcode == 98) {
				this.certtemplate = buffer.g2();
			} else if (opcode >= 100 && opcode < 110) {
				if (this.countobj == null) {
					this.countobj = new int[10];
					this.countco = new int[10];
				}

				this.countobj[opcode - 100] = buffer.g2();
				this.countco[opcode - 100] = buffer.g2();
			}
		}
	}

	@OriginalMember(owner = "client!cc", name = "b", descriptor = "(I)V")
	private void toCertificate() {
		@Pc(3) ObjType template = get(this.certtemplate);
		this.model = template.model;
		this.zoom2d = template.zoom2d;
		this.xan2d = template.xan2d;
		this.yan2d = template.yan2d;
		this.zan2d = template.zan2d;
		this.xof2d = template.xof2d;
		this.yof2d = template.yof2d;
		this.recol_s = template.recol_s;
		this.recol_d = template.recol_d;
		@Pc(55) ObjType link = get(this.certlink);
		this.name = link.name;
		this.members = link.members;
		this.cost = link.cost;
		@Pc(69) String article = "a";
		@Pc(74) char firstChar = link.name.charAt(0);
		if (firstChar == 'A' || firstChar == 'E' || firstChar == 'I' || firstChar == 'O' || firstChar == 'U') {
			article = "an";
		}
		this.desc = "Swap this note at any bank for " + article + " " + link.name + ".";
		this.stackable = true;
	}

	@OriginalMember(owner = "client!cc", name = "c", descriptor = "(I)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int arg0) {
		@Pc(11) int local11;
		if (this.countobj != null && arg0 > 1) {
			@Pc(9) int local9 = -1;
			for (local11 = 0; local11 < 10; local11++) {
				if (arg0 >= this.countco[local11] && this.countco[local11] != 0) {
					local9 = this.countobj[local11];
				}
			}
			if (local9 != -1) {
				return get(local9).getModel(1);
			}
		}
		@Pc(48) Model local48 = (Model) models.get((long) this.id);
		if (local48 != null) {
			return local48;
		}
		local48 = new Model(this.model);
		if (this.recol_s != null) {
			for (local11 = 0; local11 < this.recol_s.length; local11++) {
				local48.recolor(this.recol_s[local11], this.recol_d[local11]);
			}
		}
		local48.applyLighting(64, 768, -50, -10, -50, true);
		local48.pickable = true;
		models.put((long) this.id, local48);
		return local48;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(BI)Lclient!eb;")
	public Model getWornModel(@OriginalArg(1) int arg0) {
		@Pc(4) int local4 = this.manwear;
		if (arg0 == 1) {
			local4 = this.womanwear;
		}
		if (local4 == -1) {
			return null;
		}
		@Pc(25) int local25 = this.manwear2;
		@Pc(28) int local28 = this.manwear3;
		if (arg0 == 1) {
			local25 = this.womanwear2;
			local28 = this.womanwear3;
		}
		@Pc(43) Model local43 = new Model(local4);
		if (local25 != -1) {
			@Pc(55) Model local55;
			if (local28 == -1) {
				local55 = new Model(local25);
				@Pc(102) Model[] local102 = new Model[] { local43, local55 };
				local43 = new Model(local102, 2);
			} else {
				local55 = new Model(local25);
				@Pc(61) Model local61 = new Model(local28);
				@Pc(76) Model[] local76 = new Model[] { local43, local55, local61 };
				local43 = new Model(local76, 3);
			}
		}
		if (arg0 == 0 && this.manwearOffsetY != 0) {
			local43.translate(this.manwearOffsetY, 0, 0);
		}
		if (arg0 == 1 && this.womanwearOffsetY != 0) {
			local43.translate(this.womanwearOffsetY, 0, 0);
		}
		if (this.recol_s != null) {
			for (@Pc(139) int local139 = 0; local139 < this.recol_s.length; local139++) {
				local43.recolor(this.recol_s[local139], this.recol_d[local139]);
			}
		}
		return local43;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(II)Lclient!eb;")
	public Model getHeadModel(@OriginalArg(1) int gender) {
		@Pc(2) int head = this.manhead;
		if (gender == 1) {
			head = this.womanhead;
		}
		if (head == -1) {
			return null;
		}

		@Pc(22) int head2 = this.manhead2;
		if (gender == 1) {
			head2 = this.womanhead2;
		}

		@Pc(34) Model m = new Model(head);
		if (head2 != -1) {
			@Pc(43) Model other = new Model(head2);
			@Pc(54) Model[] both = new Model[] { m, other };
			m = new Model(both, 2);
		}

		if (this.recol_s != null) {
			for (@Pc(66) int i = 0; i < this.recol_s.length; i++) {
				m.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}
		return m;
	}

	public String toJagConfig() {
		StringBuilder builder = new StringBuilder();

		builder.append("[");
		if (this.certlink != -1) {
			builder.append("cert_");
		}
		builder.append("obj_").append(this.id).append("]\n");

		if (this.certlink != -1) {
			builder.append("certlink=obj_").append(this.certlink).append("\n");
			builder.append("certtemplate=obj_").append(this.certtemplate).append("\n");
			return builder.toString();
		}

		if (this.name != null) {
			builder.append("name=").append(this.name).append("\n");
		}

		if (this.desc != null) {
			builder.append("desc=").append(this.desc).append("\n");
		}

		if (this.model != 0) {
			builder.append("model=model_").append(this.model).append("\n");
		}

		if (this.manwear != -1) {
			builder.append("manwear=model_").append(this.manwear);

			if (this.manwearOffsetY != 0) {
				builder.append(",").append(this.manwearOffsetY);
			}

			builder.append("\n");
		}

		if (this.manwear2 != -1) {
			builder.append("manwear2=model_").append(this.manwear2).append("\n");
		}

		if (this.manwear3 != -1) {
			builder.append("manwear3=model_").append(this.manwear3).append("\n");
		}

		if (this.womanwear != -1) {
			builder.append("womanwear=model_").append(this.womanwear);

			if (this.womanwearOffsetY != 0) {
				builder.append(",").append(this.womanwearOffsetY);
			}

			builder.append("\n");
		}

		if (this.womanwear2 != -1) {
			builder.append("womanwear2=model_").append(this.womanwear2).append("\n");
		}

		if (this.womanwear3 != -1) {
			builder.append("womanwear3=model_").append(this.womanwear3).append("\n");
		}

		if (this.manhead != -1) {
			builder.append("manhead=").append(this.manhead).append("\n");
		}

		if (this.manhead2 != -1) {
			builder.append("manhead2=").append(this.manhead2).append("\n");
		}

		if (this.womanhead != -1) {
			builder.append("womanhead=").append(this.womanhead).append("\n");
		}

		if (this.womanhead2 != -1) {
			builder.append("womanhead2=").append(this.womanhead2).append("\n");
		}

		if (this.cost != 1) {
			builder.append("cost=").append(this.cost).append("\n");
		}

		if (this.zoom2d != 2000) {
			builder.append("2dzoom=").append(this.zoom2d).append("\n");
		}

		if (this.xof2d != 0) {
			builder.append("2dxof=").append(this.xof2d).append("\n");
		}

		if (this.yof2d != 0) {
			builder.append("2dyof=").append(this.yof2d).append("\n");
		}

		if (this.xan2d != 0) {
			builder.append("2dxan=").append(this.xan2d).append("\n");
		}

		if (this.yan2d != 0) {
			builder.append("2dyan=").append(this.yan2d).append("\n");
		}

		if (this.zan2d != 0) {
			builder.append("2dzan=").append(this.zan2d).append("\n");
		}

//		if (this.opcode9) {
//			builder.append("opcode9=yes").append("\n");
//		}
//
//		if (this.opcode10 != -1) {
//			builder.append("opcode10=").append(this.opcode10).append("\n");
//		}

		if (this.stackable) {
			builder.append("stackable=yes").append("\n");
		}

		if (this.members) {
			builder.append("members=yes").append("\n");
		}

		if (this.countobj != null) {
			for (int i = 0; i < this.countobj.length; ++i) {
				if (this.countobj[i] == 0) {
					continue;
				}

				builder.append("count").append(i + 1).append("=obj_").append(this.countobj[i]).append(",").append(this.countco[i]).append("\n");
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

		if (this.iops != null) {
			for (int i = 0; i < this.iops.length; ++i) {
				if (this.iops[i] == null) {
					continue;
				}

				builder.append("iop").append(i + 1).append("=").append(this.iops[i]).append("\n");
			}
		}

		if (this.recol_s != null) {
			for (int i = 0; i < this.recol_s.length; ++i) {
				builder.append("recol").append(i + 1).append("s=").append(this.recol_s[i]).append("\n");
				builder.append("recol").append(i + 1).append("d=").append(this.recol_d[i]).append("\n");
			}
		}

		return builder.toString();
	}

	@OriginalMember(owner = "client!cc", name = "c", descriptor = "I")
	private static int flowObfuscator2 = -192;

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "I")
	private static int flowObfuscator3;

	@OriginalMember(owner = "client!cc", name = "b", descriptor = "Z")
	private final boolean flowObfuscator4 = false;

	@OriginalMember(owner = "client!cc", name = "d", descriptor = "I")
	private final int flowObfuscator1 = -22246;

}
