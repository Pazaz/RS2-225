import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!cc")
public final class ObjType {

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "I")
	private static int anInt98;

	@OriginalMember(owner = "client!cc", name = "e", descriptor = "I")
	private static int anInt101;

	@OriginalMember(owner = "client!cc", name = "f", descriptor = "[I")
	private static int[] anIntArray23;

	@OriginalMember(owner = "client!cc", name = "g", descriptor = "Lclient!kb;")
	private static Buffer aClass1_Sub3_Sub3_3;

	@OriginalMember(owner = "client!cc", name = "h", descriptor = "[Lclient!cc;")
	private static ObjType[] aObjTypeArray1;

	@OriginalMember(owner = "client!cc", name = "i", descriptor = "I")
	private static int anInt102;

	@OriginalMember(owner = "client!cc", name = "c", descriptor = "I")
	private static int anInt99 = -192;

	@OriginalMember(owner = "client!cc", name = "j", descriptor = "Z")
	public static boolean aBoolean29 = true;

	@OriginalMember(owner = "client!cc", name = "T", descriptor = "Lclient!s;")
	public static Cache aCache_4 = new Cache((byte) 0, 50);

	@OriginalMember(owner = "client!cc", name = "U", descriptor = "Lclient!s;")
	public static Cache aCache_5 = new Cache((byte) 0, 200);

	@OriginalMember(owner = "client!cc", name = "l", descriptor = "I")
	private int anInt104;

	@OriginalMember(owner = "client!cc", name = "m", descriptor = "Ljava/lang/String;")
	public String aString3;

	@OriginalMember(owner = "client!cc", name = "n", descriptor = "[B")
	public byte[] aByteArray3;

	@OriginalMember(owner = "client!cc", name = "o", descriptor = "[I")
	private int[] anIntArray24;

	@OriginalMember(owner = "client!cc", name = "p", descriptor = "[I")
	private int[] anIntArray25;

	@OriginalMember(owner = "client!cc", name = "q", descriptor = "I")
	public int anInt105;

	@OriginalMember(owner = "client!cc", name = "r", descriptor = "I")
	public int anInt106;

	@OriginalMember(owner = "client!cc", name = "s", descriptor = "I")
	public int anInt107;

	@OriginalMember(owner = "client!cc", name = "t", descriptor = "I")
	private int anInt108;

	@OriginalMember(owner = "client!cc", name = "u", descriptor = "I")
	private int anInt109;

	@OriginalMember(owner = "client!cc", name = "v", descriptor = "I")
	private int anInt110;

	@OriginalMember(owner = "client!cc", name = "w", descriptor = "Z")
	private boolean aBoolean30;

	@OriginalMember(owner = "client!cc", name = "x", descriptor = "I")
	private int anInt111;

	@OriginalMember(owner = "client!cc", name = "y", descriptor = "Z")
	public boolean aBoolean31;

	@OriginalMember(owner = "client!cc", name = "z", descriptor = "I")
	public int anInt112;

	@OriginalMember(owner = "client!cc", name = "A", descriptor = "Z")
	private boolean aBoolean32;

	@OriginalMember(owner = "client!cc", name = "B", descriptor = "[Ljava/lang/String;")
	public String[] aStringArray3;

	@OriginalMember(owner = "client!cc", name = "C", descriptor = "[Ljava/lang/String;")
	public String[] aStringArray4;

	@OriginalMember(owner = "client!cc", name = "D", descriptor = "I")
	private int anInt113;

	@OriginalMember(owner = "client!cc", name = "E", descriptor = "I")
	private int anInt114;

	@OriginalMember(owner = "client!cc", name = "F", descriptor = "B")
	private byte aByte5;

	@OriginalMember(owner = "client!cc", name = "G", descriptor = "I")
	private int anInt115;

	@OriginalMember(owner = "client!cc", name = "H", descriptor = "I")
	private int anInt116;

	@OriginalMember(owner = "client!cc", name = "I", descriptor = "B")
	private byte aByte6;

	@OriginalMember(owner = "client!cc", name = "J", descriptor = "I")
	private int anInt117;

	@OriginalMember(owner = "client!cc", name = "K", descriptor = "I")
	private int anInt118;

	@OriginalMember(owner = "client!cc", name = "L", descriptor = "I")
	private int anInt119;

	@OriginalMember(owner = "client!cc", name = "M", descriptor = "I")
	private int anInt120;

	@OriginalMember(owner = "client!cc", name = "N", descriptor = "I")
	private int anInt121;

	@OriginalMember(owner = "client!cc", name = "O", descriptor = "I")
	private int anInt122;

	@OriginalMember(owner = "client!cc", name = "P", descriptor = "[I")
	private int[] anIntArray26;

	@OriginalMember(owner = "client!cc", name = "Q", descriptor = "[I")
	private int[] anIntArray27;

	@OriginalMember(owner = "client!cc", name = "R", descriptor = "I")
	private int anInt123;

	@OriginalMember(owner = "client!cc", name = "S", descriptor = "I")
	private int anInt124;

	@OriginalMember(owner = "client!cc", name = "b", descriptor = "Z")
	private final boolean aBoolean28 = false;

	@OriginalMember(owner = "client!cc", name = "d", descriptor = "I")
	private final int anInt100 = -22246;

	@OriginalMember(owner = "client!cc", name = "k", descriptor = "I")
	public int anInt103 = -1;

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(Lclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive arg0) {
		aClass1_Sub3_Sub3_3 = new Buffer(363, arg0.read("obj.dat", null));
		@Pc(21) Buffer local21 = new Buffer(363, arg0.read("obj.idx", null));
		anInt101 = local21.g2();
		anIntArray23 = new int[anInt101];
		@Pc(29) int local29 = 2;
		for (@Pc(31) int local31 = 0; local31 < anInt101; local31++) {
			anIntArray23[local31] = local29;
			local29 += local21.g2();
		}
		aObjTypeArray1 = new ObjType[10];
		for (@Pc(51) int local51 = 0; local51 < 10; local51++) {
			aObjTypeArray1[local51] = new ObjType();
		}
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(Z)V")
	public static void unload() {
		aCache_4 = null;
		aCache_5 = null;
		anIntArray23 = null;
		aObjTypeArray1 = null;
		aClass1_Sub3_Sub3_3 = null;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(I)Lclient!cc;")
	public static ObjType get(@OriginalArg(0) int arg0) {
		for (@Pc(1) int local1 = 0; local1 < 10; local1++) {
			if (aObjTypeArray1[local1].anInt103 == arg0) {
				return aObjTypeArray1[local1];
			}
		}
		anInt102 = (anInt102 + 1) % 10;
		@Pc(27) ObjType local27 = aObjTypeArray1[anInt102];
		aClass1_Sub3_Sub3_3.offset = anIntArray23[arg0];
		local27.anInt103 = arg0;
		local27.reset();
		local27.decode(aClass1_Sub3_Sub3_3);
		if (local27.anInt124 != -1) {
			local27.toCertificate();
		}
		if (!aBoolean29 && local27.aBoolean32) {
			local27.aString3 = "Members Object";
			local27.aByteArray3 = "Login to a members' server to use this object.".getBytes();
			local27.aStringArray3 = null;
			local27.aStringArray4 = null;
		}
		return local27;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(III)Lclient!hb;")
	public static Sprite getSprite(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(7) Sprite local7 = (Sprite) aCache_5.get((long) arg0);
		if (local7 != null && local7.anInt466 != arg1 && local7.anInt466 != -1) {
			local7.unlink();
			local7 = null;
		}
		if (local7 != null) {
			return local7;
		}
		@Pc(28) ObjType local28 = get(arg0);
		if (local28.anIntArray26 == null) {
			arg1 = -1;
		}
		@Pc(38) int local38;
		@Pc(40) int local40;
		if (arg1 > 1) {
			local38 = -1;
			for (local40 = 0; local40 < 10; local40++) {
				if (arg1 >= local28.anIntArray27[local40] && local28.anIntArray27[local40] != 0) {
					local38 = local28.anIntArray26[local40];
				}
			}
			if (local38 != -1) {
				local28 = get(local38);
			}
		}
		local7 = new Sprite(32, 32);
		local38 = Draw3D.anInt430;
		local40 = Draw3D.anInt431;
		@Pc(80) int[] local80 = Draw3D.anIntArray139;
		@Pc(82) int[] local82 = Draw2D.anIntArray178;
		@Pc(84) int local84 = Draw2D.anInt528;
		@Pc(86) int local86 = Draw2D.anInt529;
		@Pc(88) int local88 = Draw2D.anInt532;
		@Pc(90) int local90 = Draw2D.anInt533;
		@Pc(92) int local92 = Draw2D.anInt530;
		@Pc(94) int local94 = Draw2D.anInt531;
		Draw3D.aBoolean99 = false;
		Draw2D.prepare(32, local7.anIntArray148, 32);
		Draw2D.fillRect(0, 0, 0, 32, 32);
		Draw3D.prepareOffsets(anInt99);
		@Pc(115) Model local115 = local28.getModel(1);
		@Pc(125) int local125 = Draw3D.anIntArray137[local28.anInt106] * local28.anInt105 >> 16;
		@Pc(135) int local135 = Draw3D.anIntArray138[local28.anInt106] * local28.anInt105 >> 16;
		local115.draw(local28.anInt107, local28.anInt108, local28.anInt106, local28.anInt109, local125 + local115.maxBoundY / 2 + local28.anInt110, local135 + local28.anInt110);
		for (@Pc(168) int local168 = 31; local168 >= 0; local168--) {
			for (local135 = 31; local135 >= 0; local135--) {
				if (local7.anIntArray148[local168 + local135 * 32] == 0) {
					if (local168 > 0 && local7.anIntArray148[local168 + local135 * 32 - 1] > 1) {
						local7.anIntArray148[local168 + local135 * 32] = 1;
					} else if (local135 > 0 && local7.anIntArray148[local168 + (local135 - 1) * 32] > 1) {
						local7.anIntArray148[local168 + local135 * 32] = 1;
					} else if (local168 < 31 && local7.anIntArray148[local168 + local135 * 32 + 1] > 1) {
						local7.anIntArray148[local168 + local135 * 32] = 1;
					} else if (local135 < 31 && local7.anIntArray148[local168 + (local135 + 1) * 32] > 1) {
						local7.anIntArray148[local168 + local135 * 32] = 1;
					}
				}
			}
		}
		for (@Pc(291) int local291 = 31; local291 >= 0; local291--) {
			for (local135 = 31; local135 >= 0; local135--) {
				if (local7.anIntArray148[local291 + local135 * 32] == 0 && local291 > 0 && local135 > 0 && local7.anIntArray148[local291 + (local135 - 1) * 32 - 1] > 0) {
					local7.anIntArray148[local291 + local135 * 32] = 3153952;
				}
			}
		}
		if (local28.anInt124 != -1) {
			@Pc(348) Sprite local348 = getSprite(local28.anInt123, 10);
			@Pc(351) int local351 = local348.anInt465;
			@Pc(354) int local354 = local348.anInt466;
			local348.anInt465 = 32;
			local348.anInt466 = 32;
			local348.draw();
			local348.anInt465 = local351;
			local348.anInt466 = local354;
		}
		aCache_5.put((long) arg0, local7);
		Draw2D.prepare(local84, local82, local86);
		Draw2D.setBounds(local94, local92, local90, local88);
		Draw3D.anInt430 = local38;
		Draw3D.anInt431 = local40;
		Draw3D.anIntArray139 = local80;
		Draw3D.aBoolean99 = true;
		if (local28.aBoolean31) {
			local7.anInt465 = 33;
		} else {
			local7.anInt465 = 32;
		}
		local7.anInt466 = arg1;
		return local7;
	}

	@OriginalMember(owner = "client!cc", name = "<init>", descriptor = "()V")
	private ObjType() {
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "()V")
	private void reset() {
		this.anInt104 = 0;
		this.aString3 = null;
		this.aByteArray3 = null;
		this.anIntArray24 = null;
		this.anIntArray25 = null;
		this.anInt105 = 2000;
		this.anInt106 = 0;
		this.anInt107 = 0;
		this.anInt108 = 0;
		this.anInt109 = 0;
		this.anInt110 = 0;
		this.aBoolean30 = false;
		this.anInt111 = -1;
		this.aBoolean31 = false;
		this.anInt112 = 1;
		this.aBoolean32 = false;
		this.aStringArray3 = null;
		this.aStringArray4 = null;
		this.anInt113 = -1;
		this.anInt114 = -1;
		this.aByte5 = 0;
		this.anInt115 = -1;
		this.anInt116 = -1;
		this.aByte6 = 0;
		this.anInt117 = -1;
		this.anInt118 = -1;
		this.anInt119 = -1;
		this.anInt120 = -1;
		this.anInt121 = -1;
		this.anInt122 = -1;
		this.anIntArray26 = null;
		this.anIntArray27 = null;
		this.anInt123 = -1;
		this.anInt124 = -1;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer arg0) {
		while (true) {
			@Pc(10) int local10 = arg0.g1();
			if (local10 == 0) {
				return;
			}
			if (local10 == 1) {
				this.anInt104 = arg0.g2();
			} else if (local10 == 2) {
				this.aString3 = arg0.gjstr();
			} else if (local10 == 3) {
				this.aByteArray3 = arg0.gjstrBytes();
			} else if (local10 == 4) {
				this.anInt105 = arg0.g2();
			} else if (local10 == 5) {
				this.anInt106 = arg0.g2();
			} else if (local10 == 6) {
				this.anInt107 = arg0.g2();
			} else if (local10 == 7) {
				this.anInt109 = arg0.g2();
				if (this.anInt109 > 32767) {
					this.anInt109 -= 65536;
				}
			} else if (local10 == 8) {
				this.anInt110 = arg0.g2();
				if (this.anInt110 > 32767) {
					this.anInt110 -= 65536;
				}
			} else if (local10 == 9) {
				this.aBoolean30 = true;
			} else if (local10 == 10) {
				this.anInt111 = arg0.g2();
			} else if (local10 == 11) {
				this.aBoolean31 = true;
			} else if (local10 == 12) {
				this.anInt112 = arg0.g4();
			} else if (local10 == 16) {
				this.aBoolean32 = true;
			} else if (local10 == 23) {
				this.anInt113 = arg0.g2();
				this.aByte5 = arg0.g1s();
			} else if (local10 == 24) {
				this.anInt114 = arg0.g2();
			} else if (local10 == 25) {
				this.anInt115 = arg0.g2();
				this.aByte6 = arg0.g1s();
			} else if (local10 == 26) {
				this.anInt116 = arg0.g2();
			} else if (local10 >= 30 && local10 < 35) {
				if (this.aStringArray3 == null) {
					this.aStringArray3 = new String[5];
				}
				this.aStringArray3[local10 - 30] = arg0.gjstr();
				if (this.aStringArray3[local10 - 30].equalsIgnoreCase("hidden")) {
					this.aStringArray3[local10 - 30] = null;
				}
			} else if (local10 >= 35 && local10 < 40) {
				if (this.aStringArray4 == null) {
					this.aStringArray4 = new String[5];
				}
				this.aStringArray4[local10 - 35] = arg0.gjstr();
			} else if (local10 == 40) {
				@Pc(260) int local260 = arg0.g1();
				this.anIntArray24 = new int[local260];
				this.anIntArray25 = new int[local260];
				for (@Pc(270) int local270 = 0; local270 < local260; local270++) {
					this.anIntArray24[local270] = arg0.g2();
					this.anIntArray25[local270] = arg0.g2();
				}
			} else if (local10 == 78) {
				this.anInt117 = arg0.g2();
			} else if (local10 == 79) {
				this.anInt118 = arg0.g2();
			} else if (local10 == 90) {
				this.anInt119 = arg0.g2();
			} else if (local10 == 91) {
				this.anInt121 = arg0.g2();
			} else if (local10 == 92) {
				this.anInt120 = arg0.g2();
			} else if (local10 == 93) {
				this.anInt122 = arg0.g2();
			} else if (local10 == 95) {
				this.anInt108 = arg0.g2();
			} else if (local10 == 97) {
				this.anInt123 = arg0.g2();
			} else if (local10 == 98) {
				this.anInt124 = arg0.g2();
			} else if (local10 >= 100 && local10 < 110) {
				if (this.anIntArray26 == null) {
					this.anIntArray26 = new int[10];
					this.anIntArray27 = new int[10];
				}
				this.anIntArray26[local10 - 100] = arg0.g2();
				this.anIntArray27[local10 - 100] = arg0.g2();
			}
		}
	}

	@OriginalMember(owner = "client!cc", name = "b", descriptor = "(I)V")
	private void toCertificate() {
		@Pc(3) ObjType local3 = get(this.anInt124);
		this.anInt104 = local3.anInt104;
		this.anInt105 = local3.anInt105;
		this.anInt106 = local3.anInt106;
		this.anInt107 = local3.anInt107;
		this.anInt108 = local3.anInt108;
		this.anInt109 = local3.anInt109;
		this.anInt110 = local3.anInt110;
		this.anIntArray24 = local3.anIntArray24;
		this.anIntArray25 = local3.anIntArray25;
		@Pc(55) ObjType local55 = get(this.anInt123);
		this.aString3 = local55.aString3;
		this.aBoolean32 = local55.aBoolean32;
		this.anInt112 = local55.anInt112;
		@Pc(69) String local69 = "a";
		@Pc(74) char local74 = local55.aString3.charAt(0);
		if (local74 == 'A' || local74 == 'E' || local74 == 'I' || local74 == 'O' || local74 == 'U') {
			local69 = "an";
		}
		this.aByteArray3 = ("Swap this note at any bank for " + local69 + " " + local55.aString3 + ".").getBytes();
		this.aBoolean31 = true;
	}

	@OriginalMember(owner = "client!cc", name = "c", descriptor = "(I)Lclient!eb;")
	public final Model getModel(@OriginalArg(0) int arg0) {
		@Pc(11) int local11;
		if (this.anIntArray26 != null && arg0 > 1) {
			@Pc(9) int local9 = -1;
			for (local11 = 0; local11 < 10; local11++) {
				if (arg0 >= this.anIntArray27[local11] && this.anIntArray27[local11] != 0) {
					local9 = this.anIntArray26[local11];
				}
			}
			if (local9 != -1) {
				return get(local9).getModel(1);
			}
		}
		@Pc(48) Model local48 = (Model) aCache_4.get((long) this.anInt103);
		if (local48 != null) {
			return local48;
		}
		local48 = new Model(false, this.anInt104);
		if (this.anIntArray24 != null) {
			for (local11 = 0; local11 < this.anIntArray24.length; local11++) {
				local48.recolor(this.anIntArray24[local11], this.anIntArray25[local11]);
			}
		}
		local48.applyLighting(64, 768, -50, -10, -50, true);
		local48.pickable = true;
		aCache_4.put((long) this.anInt103, local48);
		return local48;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(BI)Lclient!eb;")
	public final Model getWornModel(@OriginalArg(1) int arg0) {
		@Pc(4) int local4 = this.anInt113;
		if (arg0 == 1) {
			local4 = this.anInt115;
		}
		if (local4 == -1) {
			return null;
		}
		@Pc(25) int local25 = this.anInt114;
		@Pc(28) int local28 = this.anInt117;
		if (arg0 == 1) {
			local25 = this.anInt116;
			local28 = this.anInt118;
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
		if (arg0 == 0 && this.aByte5 != 0) {
			local43.translate(this.aByte5, 0, 0);
		}
		if (arg0 == 1 && this.aByte6 != 0) {
			local43.translate(this.aByte6, 0, 0);
		}
		if (this.anIntArray24 != null) {
			for (@Pc(139) int local139 = 0; local139 < this.anIntArray24.length; local139++) {
				local43.recolor(this.anIntArray24[local139], this.anIntArray25[local139]);
			}
		}
		return local43;
	}

	@OriginalMember(owner = "client!cc", name = "a", descriptor = "(II)Lclient!eb;")
	public final Model getHeadModel(@OriginalArg(1) int arg0) {
		@Pc(2) int local2 = this.anInt119;
		if (this.anInt100 != -22246) {
			anInt99 = 205;
		}
		if (arg0 == 1) {
			local2 = this.anInt121;
		}
		if (local2 == -1) {
			return null;
		}
		@Pc(22) int local22 = this.anInt120;
		if (arg0 == 1) {
			local22 = this.anInt122;
		}
		@Pc(34) Model local34 = new Model(false, local2);
		if (local22 != -1) {
			@Pc(43) Model local43 = new Model(false, local22);
			@Pc(54) Model[] local54 = new Model[] { local34, local43 };
			local34 = new Model(0, local54, 2);
		}
		if (this.anIntArray24 != null) {
			for (@Pc(66) int local66 = 0; local66 < this.anIntArray24.length; local66++) {
				local34.recolor(this.anIntArray24[local66], this.anIntArray25[local66]);
			}
		}
		return local34;
	}
}
