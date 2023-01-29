package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.util.Cache;
import com.jagex.game.runetek3.graphics.Draw2D;
import com.jagex.game.runetek3.graphics.Draw3D;
import com.jagex.game.runetek3.graphics.Model;
import com.jagex.game.runetek3.graphics.Sprite;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!cc")
public class ObjType {

	@OriginalMember(owner = "client!cc", name = "j", descriptor = "Z")
	public static boolean isMember = true;

	@OriginalMember(owner = "client!cc", name = "T", descriptor = "Lclient!s;")
	public static Cache models = new Cache(50);

	@OriginalMember(owner = "client!cc", name = "U", descriptor = "Lclient!s;")
	public static Cache icons = new Cache(200);

	@OriginalMember(owner = "client!cc", name = "e", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!cc", name = "f", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!cc", name = "g", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!cc", name = "h", descriptor = "[Lclient!cc;")
	private static ObjType[] instances;

	@OriginalMember(owner = "client!cc", name = "i", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!cc", name = "l", descriptor = "I")
	private int model;

	@OriginalMember(owner = "client!cc", name = "m", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!cc", name = "n", descriptor = "[B")
	public byte[] desc;

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
	public int zan2d;

	@OriginalMember(owner = "client!cc", name = "u", descriptor = "I")
	public int xof2d;

	@OriginalMember(owner = "client!cc", name = "v", descriptor = "I")
	public int yof2d;

	@OriginalMember(owner = "client!cc", name = "w", descriptor = "Z")
	private boolean opcode9;

	@OriginalMember(owner = "client!cc", name = "x", descriptor = "I")
	private int opcode10;

	@OriginalMember(owner = "client!cc", name = "y", descriptor = "Z")
	public boolean stackable;

	@OriginalMember(owner = "client!cc", name = "z", descriptor = "I")
	public int cost;

	@OriginalMember(owner = "client!cc", name = "A", descriptor = "Z")
	public boolean members;

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
	public int[] countobj;

	@OriginalMember(owner = "client!cc", name = "Q", descriptor = "[I")
	public int[] countco;

	@OriginalMember(owner = "client!cc", name = "R", descriptor = "I")
	public int certlink;

	@OriginalMember(owner = "client!cc", name = "S", descriptor = "I")
	public int certtemplate;

	@OriginalMember(owner = "client!cc", name = "k", descriptor = "I")
	public int id = -1;

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(Lclient!ub;)V")
	public static void unpack(@OriginalArg(0) FileArchive archive) {
		dat = new Buffer(archive.read("obj.dat", null));
		@Pc(21) Buffer idx = new Buffer(archive.read("obj.idx", null));
		count = idx.g2();

		offsets = new int[count];
		@Pc(29) int offset = 2;
		for (@Pc(31) int i = 0; i < count; i++) {
			offsets[i] = offset;
			offset += idx.g2();
		}

		instances = new ObjType[10];
		for (@Pc(51) int i = 0; i < 10; i++) {
			instances[i] = new ObjType();
		}
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(Z)V")
	public static void unload() {
		models = null;
		icons = null;
		offsets = null;
		instances = null;
		dat = null;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(I)Lclient!cc;")
	public static ObjType get(@OriginalArg(0) int id) {
		for (@Pc(1) int i = 0; i < 10; i++) {
			if (instances[i].id == id) {
				return instances[i];
			}
		}

		offset = (offset + 1) % 10;
		@Pc(27) ObjType obj = instances[offset];
		dat.pos = offsets[id];
		obj.id = id;
		obj.reset();
		obj.decode(dat);

		if (obj.certtemplate != -1) {
			obj.toCertificate();
		}

		if (!isMember && obj.members) {
			obj.name = "Members Object";
			obj.desc = "Login to a members' server to use this object.".getBytes();
			obj.ops = null;
			obj.iops = null;
		}

		return obj;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(III)Lclient!hb;")
	public static Sprite getSprite(@OriginalArg(0) int id, @OriginalArg(2) int amount) {
		@Pc(7) Sprite icon = (Sprite) icons.get(id);
		if (icon != null && icon.clipHeight != amount && icon.clipHeight != -1) {
			icon.unlink();
			icon = null;
		}

		if (icon != null) {
			return icon;
		}

		@Pc(28) ObjType obj = get(id);
		if (obj.countobj == null) {
			amount = -1;
		}

		@Pc(38) int centerX;
		@Pc(40) int cenetrY;
		if (amount > 1) {
			centerX = -1;
			for (cenetrY = 0; cenetrY < 10; cenetrY++) {
				if (amount >= obj.countco[cenetrY] && obj.countco[cenetrY] != 0) {
					centerX = obj.countobj[cenetrY];
				}
			}
			if (centerX != -1) {
				obj = get(centerX);
			}
		}

		icon = new Sprite(32, 32);
		centerX = Draw3D.centerX3D;
		cenetrY = Draw3D.centerY3D;

		@Pc(80) int[] offsets = Draw3D.lineOffsets;
		@Pc(82) int[] data = Draw2D.data;
		@Pc(84) int width = Draw2D.width;
		@Pc(86) int height = Draw2D.height;
		@Pc(88) int left = Draw2D.left;
		@Pc(90) int right = Draw2D.right;
		@Pc(92) int top = Draw2D.top;
		@Pc(94) int bottom = Draw2D.bottom;

		Draw3D.jagged = false;
		Draw2D.prepare(32, icon.pixels, 32);
		Draw2D.fillRect(0, 0, 0, 32, 32);
		Draw3D.init2D();

		@Pc(115) Model m = obj.getModel(1);
		@Pc(125) int cameraY = Draw3D.sin[obj.xan2d] * obj.zoom2d >> 16;
		@Pc(135) int cameraZ = Draw3D.cos[obj.xan2d] * obj.zoom2d >> 16;
		m.drawSimple(0, obj.yan2d, obj.zan2d, obj.xan2d, obj.xof2d, cameraY + m.maxBoundY / 2 + obj.yof2d, cameraZ + obj.yof2d);

		for (@Pc(168) int x = 31; x >= 0; x--) {
			for (int y = 31; y >= 0; y--) {
				if (icon.pixels[x + y * 32] == 0) {
					if (x > 0 && icon.pixels[x + y * 32 - 1] > 1) {
						icon.pixels[x + y * 32] = 1;
					} else if (y > 0 && icon.pixels[x + (y - 1) * 32] > 1) {
						icon.pixels[x + y * 32] = 1;
					} else if (x < 31 && icon.pixels[x + y * 32 + 1] > 1) {
						icon.pixels[x + y * 32] = 1;
					} else if (y < 31 && icon.pixels[x + (y + 1) * 32] > 1) {
						icon.pixels[x + y * 32] = 1;
					}
				}
			}
		}

		for (@Pc(291) int x = 31; x >= 0; x--) {
			for (int y = 31; y >= 0; y--) {
				if (icon.pixels[x + y * 32] == 0 && x > 0 && y > 0 && icon.pixels[x + (y - 1) * 32 - 1] > 0) {
					icon.pixels[x + y * 32] = 0x302020;
				}
			}
		}

		if (obj.certtemplate != -1) {
			@Pc(348) Sprite mini = getSprite(obj.certlink, 10);
			@Pc(351) int tempW = mini.clipWidth;
			@Pc(354) int tempH = mini.clipHeight;
			mini.clipWidth = 32;
			mini.clipHeight = 32;
			mini.crop(22, 5, 22, 5);
			mini.clipWidth = tempW;
			mini.clipHeight = tempH;
		}

		icons.put(id, icon);
		Draw2D.prepare(width, data, height);
		Draw2D.setBounds(bottom, top, right, left);
		Draw3D.centerX3D = centerX;
		Draw3D.centerY3D = cenetrY;
		Draw3D.lineOffsets = offsets;
		Draw3D.jagged = true;
		if (obj.stackable) {
			icon.clipWidth = 33;
		} else {
			icon.clipWidth = 32;
		}
		icon.clipHeight = amount;
		return icon;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "()V")
	public void reset() {
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
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer dat) {
		while (true) {
			@Pc(10) int opcode = dat.g1();
			if (opcode == 0) {
				return;
			}

			if (opcode == 1) {
				this.model = dat.g2();
			} else if (opcode == 2) {
				this.name = dat.gstr();
			} else if (opcode == 3) {
				this.desc = dat.gstrbyte();
			} else if (opcode == 4) {
				this.zoom2d = dat.g2();
			} else if (opcode == 5) {
				this.xan2d = dat.g2();
			} else if (opcode == 6) {
				this.yan2d = dat.g2();
			} else if (opcode == 7) {
				this.xof2d = dat.g2();
				if (this.xof2d > 32767) {
					this.xof2d -= 65536;
				}
			} else if (opcode == 8) {
				this.yof2d = dat.g2();
				if (this.yof2d > 32767) {
					this.yof2d -= 65536;
				}
			} else if (opcode == 9) {
				this.opcode9 = true;
			} else if (opcode == 10) {
				this.opcode10 = dat.g2();
			} else if (opcode == 11) {
				this.stackable = true;
			} else if (opcode == 12) {
				this.cost = dat.g4();
			} else if (opcode == 16) {
				this.members = true;
			} else if (opcode == 23) {
				this.manwear = dat.g2();
				this.manwearOffsetY = dat.g1b();
			} else if (opcode == 24) {
				this.manwear2 = dat.g2();
			} else if (opcode == 25) {
				this.womanwear = dat.g2();
				this.womanwearOffsetY = dat.g1b();
			} else if (opcode == 26) {
				this.womanwear2 = dat.g2();
			} else if (opcode >= 30 && opcode < 35) {
				if (this.ops == null) {
					this.ops = new String[5];
				}

				this.ops[opcode - 30] = dat.gstr();
				if (this.ops[opcode - 30].equalsIgnoreCase("hidden")) {
					this.ops[opcode - 30] = null;
				}
			} else if (opcode >= 35 && opcode < 40) {
				if (this.iops == null) {
					this.iops = new String[5];
				}

				this.iops[opcode - 35] = dat.gstr();
			} else if (opcode == 40) {
				@Pc(260) int count = dat.g1();
				this.recol_s = new int[count];
				this.recol_d = new int[count];
				for (@Pc(270) int i = 0; i < count; i++) {
					this.recol_s[i] = dat.g2();
					this.recol_d[i] = dat.g2();
				}
			} else if (opcode == 78) {
				this.manwear3 = dat.g2();
			} else if (opcode == 79) {
				this.womanwear3 = dat.g2();
			} else if (opcode == 90) {
				this.manhead = dat.g2();
			} else if (opcode == 91) {
				this.womanhead = dat.g2();
			} else if (opcode == 92) {
				this.manhead2 = dat.g2();
			} else if (opcode == 93) {
				this.womanhead2 = dat.g2();
			} else if (opcode == 95) {
				this.zan2d = dat.g2();
			} else if (opcode == 97) {
				this.certlink = dat.g2();
			} else if (opcode == 98) {
				this.certtemplate = dat.g2();
			} else if (opcode >= 100 && opcode < 110) {
				if (this.countobj == null) {
					this.countobj = new int[10];
					this.countco = new int[10];
				}

				this.countobj[opcode - 100] = dat.g2();
				this.countco[opcode - 100] = dat.g2();
			}
		}
	}

	@OriginalMember(owner = "client!cc", name = "b", descriptor = "(I)V")
	public void toCertificate() {
		@Pc(3) ObjType obj = get(this.certtemplate);
		this.model = obj.model;
		this.zoom2d = obj.zoom2d;
		this.xan2d = obj.xan2d;
		this.yan2d = obj.yan2d;
		this.zan2d = obj.zan2d;
		this.xof2d = obj.xof2d;
		this.yof2d = obj.yof2d;
		this.recol_s = obj.recol_s;
		this.recol_d = obj.recol_d;

		@Pc(55) ObjType linked = get(this.certlink);
		this.name = linked.name;
		this.members = linked.members;
		this.cost = linked.cost;

		@Pc(69) String article = "a";
		@Pc(74) char vowel = linked.name.charAt(0);
		if (vowel == 'A' || vowel == 'E' || vowel == 'I' || vowel == 'O' || vowel == 'U') {
			article = "an";
		}
		this.desc = ("Swap this note at any bank for " + article + " " + linked.name + ".").getBytes();
		this.stackable = true;
	}

	@OriginalMember(owner = "client!cc", name = "c", descriptor = "(I)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int amount) {
		if (this.countobj != null && amount > 1) {
			@Pc(9) int newId = -1;
			for (@Pc(11) int i = 0; i < 10; i++) {
				if (amount >= this.countco[i] && this.countco[i] != 0) {
					newId = this.countobj[i];
				}
			}

			if (newId != -1) {
				return get(newId).getModel(1);
			}
		}

		@Pc(48) Model m = (Model) models.get(this.id);
		if (m != null) {
			return m;
		}

		m = new Model(this.model);
		if (this.recol_s != null) {
			for (int i = 0; i < this.recol_s.length; i++) {
				m.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}
		m.calculateNormals(64, 768, -50, -10, -50, true);
		m.pickable = true;
		models.put(this.id, m);
		return m;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(BI)Lclient!eb;")
	public Model getWornModel(@OriginalArg(1) int gender) {
		@Pc(4) int m0 = this.manwear;
		@Pc(25) int m1 = this.manwear2;
		@Pc(28) int m2 = this.manwear3;

		if (gender == 1) {
			m0 = this.womanwear;
			m1 = this.womanwear2;
			m2 = this.womanwear3;
		}

		if (m0 == -1) {
			return null;
		}

		@Pc(43) Model m = new Model(m0);
		if (m1 != -1) {
			@Pc(55) Model model1;
			if (m2 == -1) {
				model1 = new Model(m1);
				@Pc(102) Model[] models = new Model[] { m, model1 };
				m = new Model(models, 2);
			} else {
				model1 = new Model(m1);
				@Pc(61) Model model2 = new Model(m2);
				@Pc(76) Model[] models = new Model[] { m, model1, model2 };
				m = new Model(models, 3);
			}
		}

		if (gender == 0 && this.manwearOffsetY != 0) {
			m.translate(this.manwearOffsetY, 0, 0);
		}

		if (gender == 1 && this.womanwearOffsetY != 0) {
			m.translate(this.womanwearOffsetY, 0, 0);
		}

		if (this.recol_s != null) {
			for (@Pc(139) int i = 0; i < this.recol_s.length; i++) {
				m.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}

		return m;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(II)Lclient!eb;")
	public Model getHeadModel(@OriginalArg(1) int gender) {
		@Pc(2) int m0 = this.manhead;
		@Pc(22) int m1 = this.manhead2;
		if (gender == 1) {
			m0 = this.womanhead;
			m1 = this.womanhead2;
		}

		if (m0 == -1) {
			return null;
		}

		@Pc(34) Model model1 = new Model(m0);
		if (m1 != -1) {
			@Pc(43) Model model2 = new Model(m1);
			@Pc(54) Model[] models = new Model[] { model1, model2 };
			model1 = new Model(models, 2);
		}

		if (this.recol_s != null) {
			for (@Pc(66) int i = 0; i < this.recol_s.length; i++) {
				model1.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}

		return model1;
	}
}
