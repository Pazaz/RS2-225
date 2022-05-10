import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!cc")
public final class ObjType {

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "I")
	private static int flowObfuscator3;

	@OriginalMember(owner = "client!cc", name = "e", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!cc", name = "f", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!cc", name = "g", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!cc", name = "h", descriptor = "[Lclient!cc;")
	private static ObjType[] types;

	@OriginalMember(owner = "client!cc", name = "i", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!cc", name = "c", descriptor = "I")
	private static int flowObfuscator2 = -192;

	@OriginalMember(owner = "client!cc", name = "j", descriptor = "Z")
	public static boolean isMember = true;

	@OriginalMember(owner = "client!cc", name = "T", descriptor = "Lclient!s;")
	public static Cache models = new Cache((byte) 0, 50);

	@OriginalMember(owner = "client!cc", name = "U", descriptor = "Lclient!s;")
	public static Cache icons = new Cache((byte) 0, 200);

	@OriginalMember(owner = "client!cc", name = "l", descriptor = "I")
	private int modelIndex;

	@OriginalMember(owner = "client!cc", name = "m", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!cc", name = "n", descriptor = "[B")
	public byte[] description;

	@OriginalMember(owner = "client!cc", name = "o", descriptor = "[I")
	private int[] oldColors;

	@OriginalMember(owner = "client!cc", name = "p", descriptor = "[I")
	private int[] newColors;

	@OriginalMember(owner = "client!cc", name = "q", descriptor = "I")
	public int iconZoom;

	@OriginalMember(owner = "client!cc", name = "r", descriptor = "I")
	public int iconPitch;

	@OriginalMember(owner = "client!cc", name = "s", descriptor = "I")
	public int iconYaw;

	@OriginalMember(owner = "client!cc", name = "t", descriptor = "I")
	private int iconRoll;

	@OriginalMember(owner = "client!cc", name = "u", descriptor = "I")
	private int iconX;

	@OriginalMember(owner = "client!cc", name = "v", descriptor = "I")
	private int iconY;

	@OriginalMember(owner = "client!cc", name = "w", descriptor = "Z")
	private boolean opcode9;

	@OriginalMember(owner = "client!cc", name = "x", descriptor = "I")
	private int opcode10;

	@OriginalMember(owner = "client!cc", name = "y", descriptor = "Z")
	public boolean stackable;

	@OriginalMember(owner = "client!cc", name = "z", descriptor = "I")
	public int value;

	@OriginalMember(owner = "client!cc", name = "A", descriptor = "Z")
	private boolean members;

	@OriginalMember(owner = "client!cc", name = "B", descriptor = "[Ljava/lang/String;")
	public String[] groundOptions;

	@OriginalMember(owner = "client!cc", name = "C", descriptor = "[Ljava/lang/String;")
	public String[] options;

	@OriginalMember(owner = "client!cc", name = "D", descriptor = "I")
	private int maleModel0;

	@OriginalMember(owner = "client!cc", name = "E", descriptor = "I")
	private int maleModel1;

	@OriginalMember(owner = "client!cc", name = "F", descriptor = "B")
	private byte maleOffsetY;

	@OriginalMember(owner = "client!cc", name = "G", descriptor = "I")
	private int femaleModel0;

	@OriginalMember(owner = "client!cc", name = "H", descriptor = "I")
	private int femaleModel1;

	@OriginalMember(owner = "client!cc", name = "I", descriptor = "B")
	private byte femaleOffsetY;

	@OriginalMember(owner = "client!cc", name = "J", descriptor = "I")
	private int maleModel2;

	@OriginalMember(owner = "client!cc", name = "K", descriptor = "I")
	private int femaleModel2;

	@OriginalMember(owner = "client!cc", name = "L", descriptor = "I")
	private int maleHeadModel0;

	@OriginalMember(owner = "client!cc", name = "M", descriptor = "I")
	private int maleHeadModel1;

	@OriginalMember(owner = "client!cc", name = "N", descriptor = "I")
	private int femaleHeadModel0;

	@OriginalMember(owner = "client!cc", name = "O", descriptor = "I")
	private int femaleHeadModel1;

	@OriginalMember(owner = "client!cc", name = "P", descriptor = "[I")
	private int[] stackId;

	@OriginalMember(owner = "client!cc", name = "Q", descriptor = "[I")
	private int[] stackAmount;

	@OriginalMember(owner = "client!cc", name = "R", descriptor = "I")
	private int linkedId;

	@OriginalMember(owner = "client!cc", name = "S", descriptor = "I")
	private int certificateId;

	@OriginalMember(owner = "client!cc", name = "b", descriptor = "Z")
	private final boolean flowObfuscator4 = false;

	@OriginalMember(owner = "client!cc", name = "d", descriptor = "I")
	private final int flowObfuscator1 = -22246;

	@OriginalMember(owner = "client!cc", name = "k", descriptor = "I")
	public int id = -1;

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(Lclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		dat = new Buffer(363, archive.read("obj.dat", null));
		@Pc(21) Buffer idx = new Buffer(363, archive.read("obj.idx", null));
		count = idx.g2();
		offsets = new int[count];
		@Pc(29) int offset = 2;
		for (@Pc(31) int i = 0; i < count; i++) {
			offsets[i] = offset;
			offset += idx.g2();
		}
		types = new ObjType[10];
		for (@Pc(51) int i = 0; i < 10; i++) {
			types[i] = new ObjType();
		}
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(Z)V")
	public static void unload() {
		models = null;
		icons = null;
		offsets = null;
		types = null;
		dat = null;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(I)Lclient!cc;")
	public static ObjType get(@OriginalArg(0) int arg0) {
		for (@Pc(1) int local1 = 0; local1 < 10; local1++) {
			if (types[local1].id == arg0) {
				return types[local1];
			}
		}
		offset = (offset + 1) % 10;
		@Pc(27) ObjType local27 = types[offset];
		dat.offset = offsets[arg0];
		local27.id = arg0;
		local27.reset();
		local27.decode(dat);
		if (local27.certificateId != -1) {
			local27.toCertificate();
		}
		if (!isMember && local27.members) {
			local27.name = "Members Object";
			local27.description = "Login to a members' server to use this object.".getBytes();
			local27.groundOptions = null;
			local27.options = null;
		}
		return local27;
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
		if (local28.stackId == null) {
			arg1 = -1;
		}
		@Pc(38) int local38;
		@Pc(40) int local40;
		if (arg1 > 1) {
			local38 = -1;
			for (local40 = 0; local40 < 10; local40++) {
				if (arg1 >= local28.stackAmount[local40] && local28.stackAmount[local40] != 0) {
					local38 = local28.stackId[local40];
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
		Draw2D.fillRect(0, 0, 0, 32, 32);
		Draw3D.prepareOffsets(flowObfuscator2);
		@Pc(115) Model local115 = local28.getModel(1);
		@Pc(125) int local125 = Draw3D.sin[local28.iconPitch] * local28.iconZoom >> 16;
		@Pc(135) int local135 = Draw3D.cos[local28.iconPitch] * local28.iconZoom >> 16;
		local115.draw(local28.iconYaw, local28.iconRoll, local28.iconPitch, local28.iconX, local125 + local115.maxBoundY / 2 + local28.iconY, local135 + local28.iconY);
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
		if (local28.certificateId != -1) {
			@Pc(348) Sprite local348 = getSprite(local28.linkedId, 10);
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
		Draw2D.setBounds(local94, local92, local90, local88);
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
		this.modelIndex = 0;
		this.name = null;
		this.description = null;
		this.oldColors = null;
		this.newColors = null;
		this.iconZoom = 2000;
		this.iconPitch = 0;
		this.iconYaw = 0;
		this.iconRoll = 0;
		this.iconX = 0;
		this.iconY = 0;
		this.opcode9 = false;
		this.opcode10 = -1;
		this.stackable = false;
		this.value = 1;
		this.members = false;
		this.groundOptions = null;
		this.options = null;
		this.maleModel0 = -1;
		this.maleModel1 = -1;
		this.maleOffsetY = 0;
		this.femaleModel0 = -1;
		this.femaleModel1 = -1;
		this.femaleOffsetY = 0;
		this.maleModel2 = -1;
		this.femaleModel2 = -1;
		this.maleHeadModel0 = -1;
		this.maleHeadModel1 = -1;
		this.femaleHeadModel0 = -1;
		this.femaleHeadModel1 = -1;
		this.stackId = null;
		this.stackAmount = null;
		this.linkedId = -1;
		this.certificateId = -1;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(10) int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}
			if (opcode == 1) {
				this.modelIndex = buffer.g2();
			} else if (opcode == 2) {
				this.name = buffer.gjstr();
			} else if (opcode == 3) {
				this.description = buffer.gjstrBytes();
			} else if (opcode == 4) {
				this.iconZoom = buffer.g2();
			} else if (opcode == 5) {
				this.iconPitch = buffer.g2();
			} else if (opcode == 6) {
				this.iconYaw = buffer.g2();
			} else if (opcode == 7) {
				this.iconX = buffer.g2();
				if (this.iconX > 32767) {
					this.iconX -= 65536;
				}
			} else if (opcode == 8) {
				this.iconY = buffer.g2();
				if (this.iconY > 32767) {
					this.iconY -= 65536;
				}
			} else if (opcode == 9) {
				this.opcode9 = true;
			} else if (opcode == 10) {
				this.opcode10 = buffer.g2();
			} else if (opcode == 11) {
				this.stackable = true;
			} else if (opcode == 12) {
				this.value = buffer.g4();
			} else if (opcode == 16) {
				this.members = true;
			} else if (opcode == 23) {
				this.maleModel0 = buffer.g2();
				this.maleOffsetY = buffer.g1s();
			} else if (opcode == 24) {
				this.maleModel1 = buffer.g2();
			} else if (opcode == 25) {
				this.femaleModel0 = buffer.g2();
				this.femaleOffsetY = buffer.g1s();
			} else if (opcode == 26) {
				this.femaleModel1 = buffer.g2();
			} else if (opcode >= 30 && opcode < 35) {
				if (this.groundOptions == null) {
					this.groundOptions = new String[5];
				}
				this.groundOptions[opcode - 30] = buffer.gjstr();
				if (this.groundOptions[opcode - 30].equalsIgnoreCase("hidden")) {
					this.groundOptions[opcode - 30] = null;
				}
			} else if (opcode >= 35 && opcode < 40) {
				if (this.options == null) {
					this.options = new String[5];
				}
				this.options[opcode - 35] = buffer.gjstr();
			} else if (opcode == 40) {
				@Pc(260) int count = buffer.g1();
				this.oldColors = new int[count];
				this.newColors = new int[count];
				for (@Pc(270) int i = 0; i < count; i++) {
					this.oldColors[i] = buffer.g2();
					this.newColors[i] = buffer.g2();
				}
			} else if (opcode == 78) {
				this.maleModel2 = buffer.g2();
			} else if (opcode == 79) {
				this.femaleModel2 = buffer.g2();
			} else if (opcode == 90) {
				this.maleHeadModel0 = buffer.g2();
			} else if (opcode == 91) {
				this.femaleHeadModel0 = buffer.g2();
			} else if (opcode == 92) {
				this.maleHeadModel1 = buffer.g2();
			} else if (opcode == 93) {
				this.femaleHeadModel1 = buffer.g2();
			} else if (opcode == 95) {
				this.iconRoll = buffer.g2();
			} else if (opcode == 97) {
				this.linkedId = buffer.g2();
			} else if (opcode == 98) {
				this.certificateId = buffer.g2();
			} else if (opcode >= 100 && opcode < 110) {
				if (this.stackId == null) {
					this.stackId = new int[10];
					this.stackAmount = new int[10];
				}
				this.stackId[opcode - 100] = buffer.g2();
				this.stackAmount[opcode - 100] = buffer.g2();
			}
		}
	}

	@OriginalMember(owner = "client!cc", name = "b", descriptor = "(I)V")
	private void toCertificate() {
		@Pc(3) ObjType local3 = get(this.certificateId);
		this.modelIndex = local3.modelIndex;
		this.iconZoom = local3.iconZoom;
		this.iconPitch = local3.iconPitch;
		this.iconYaw = local3.iconYaw;
		this.iconRoll = local3.iconRoll;
		this.iconX = local3.iconX;
		this.iconY = local3.iconY;
		this.oldColors = local3.oldColors;
		this.newColors = local3.newColors;
		@Pc(55) ObjType local55 = get(this.linkedId);
		this.name = local55.name;
		this.members = local55.members;
		this.value = local55.value;
		@Pc(69) String local69 = "a";
		@Pc(74) char local74 = local55.name.charAt(0);
		if (local74 == 'A' || local74 == 'E' || local74 == 'I' || local74 == 'O' || local74 == 'U') {
			local69 = "an";
		}
		this.description = ("Swap this note at any bank for " + local69 + " " + local55.name + ".").getBytes();
		this.stackable = true;
	}

	@OriginalMember(owner = "client!cc", name = "c", descriptor = "(I)Lclient!eb;")
	public final Model getModel(@OriginalArg(0) int arg0) {
		@Pc(11) int local11;
		if (this.stackId != null && arg0 > 1) {
			@Pc(9) int local9 = -1;
			for (local11 = 0; local11 < 10; local11++) {
				if (arg0 >= this.stackAmount[local11] && this.stackAmount[local11] != 0) {
					local9 = this.stackId[local11];
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
		local48 = new Model(false, this.modelIndex);
		if (this.oldColors != null) {
			for (local11 = 0; local11 < this.oldColors.length; local11++) {
				local48.recolor(this.oldColors[local11], this.newColors[local11]);
			}
		}
		local48.applyLighting(64, 768, -50, -10, -50, true);
		local48.pickable = true;
		models.put((long) this.id, local48);
		return local48;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(BI)Lclient!eb;")
	public final Model getWornModel(@OriginalArg(1) int arg0) {
		@Pc(4) int local4 = this.maleModel0;
		if (arg0 == 1) {
			local4 = this.femaleModel0;
		}
		if (local4 == -1) {
			return null;
		}
		@Pc(25) int local25 = this.maleModel1;
		@Pc(28) int local28 = this.maleModel2;
		if (arg0 == 1) {
			local25 = this.femaleModel1;
			local28 = this.femaleModel2;
		}
		@Pc(43) Model local43 = new Model(false, local4);
		if (local25 != -1) {
			@Pc(55) Model local55;
			if (local28 == -1) {
				local55 = new Model(false, local25);
				@Pc(102) Model[] local102 = new Model[] { local43, local55 };
				local43 = new Model(0, local102, 2);
			} else {
				local55 = new Model(false, local25);
				@Pc(61) Model local61 = new Model(false, local28);
				@Pc(76) Model[] local76 = new Model[] { local43, local55, local61 };
				local43 = new Model(0, local76, 3);
			}
		}
		if (arg0 == 0 && this.maleOffsetY != 0) {
			local43.translate(this.maleOffsetY, 0, 0);
		}
		if (arg0 == 1 && this.femaleOffsetY != 0) {
			local43.translate(this.femaleOffsetY, 0, 0);
		}
		if (this.oldColors != null) {
			for (@Pc(139) int local139 = 0; local139 < this.oldColors.length; local139++) {
				local43.recolor(this.oldColors[local139], this.newColors[local139]);
			}
		}
		return local43;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(II)Lclient!eb;")
	public final Model getHeadModel(@OriginalArg(1) int arg0) {
		@Pc(2) int local2 = this.maleHeadModel0;
		if (this.flowObfuscator1 != -22246) {
			flowObfuscator2 = 205;
		}
		if (arg0 == 1) {
			local2 = this.femaleHeadModel0;
		}
		if (local2 == -1) {
			return null;
		}
		@Pc(22) int local22 = this.maleHeadModel1;
		if (arg0 == 1) {
			local22 = this.femaleHeadModel1;
		}
		@Pc(34) Model local34 = new Model(false, local2);
		if (local22 != -1) {
			@Pc(43) Model local43 = new Model(false, local22);
			@Pc(54) Model[] local54 = new Model[] { local34, local43 };
			local34 = new Model(0, local54, 2);
		}
		if (this.oldColors != null) {
			for (@Pc(66) int local66 = 0; local66 < this.oldColors.length; local66++) {
				local34.recolor(this.oldColors[local66], this.newColors[local66]);
			}
		}
		return local34;
	}
}
