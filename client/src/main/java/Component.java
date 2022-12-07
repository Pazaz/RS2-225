import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!hc")
public final class Component {

	@OriginalMember(owner = "client!hc", name = "c", descriptor = "[Lclient!hc;")
	public static Component[] instances;

	@OriginalMember(owner = "client!hc", name = "hb", descriptor = "Lclient!s;")
	private static Cache spriteCache;

	@OriginalMember(owner = "client!hc", name = "ib", descriptor = "Lclient!s;")
	private static Cache modelCache;

	@OriginalMember(owner = "client!hc", name = "d", descriptor = "[I")
	public int[] inventoryIndices;

	@OriginalMember(owner = "client!hc", name = "e", descriptor = "[I")
	public int[] inventoryAmount;

	@OriginalMember(owner = "client!hc", name = "f", descriptor = "I")
	public int seqFrame;

	@OriginalMember(owner = "client!hc", name = "g", descriptor = "I")
	public int seqCycle;

	@OriginalMember(owner = "client!hc", name = "h", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!hc", name = "i", descriptor = "I")
	public int parent;

	@OriginalMember(owner = "client!hc", name = "j", descriptor = "I")
	public int type;

	@OriginalMember(owner = "client!hc", name = "k", descriptor = "I")
	public int buttontype;

	@OriginalMember(owner = "client!hc", name = "l", descriptor = "I")
	public int clientcode;

	@OriginalMember(owner = "client!hc", name = "m", descriptor = "I")
	public int width;

	@OriginalMember(owner = "client!hc", name = "n", descriptor = "I")
	public int height;

	@OriginalMember(owner = "client!hc", name = "o", descriptor = "I")
	public int x;

	@OriginalMember(owner = "client!hc", name = "p", descriptor = "I")
	public int y;

	@OriginalMember(owner = "client!hc", name = "q", descriptor = "[[I")
	public int[][] script;

	@OriginalMember(owner = "client!hc", name = "r", descriptor = "[I")
	public int[] scriptCompareType;

	@OriginalMember(owner = "client!hc", name = "s", descriptor = "[I")
	public int[] scriptCompareValue;

	@OriginalMember(owner = "client!hc", name = "t", descriptor = "I")
	public int overlayer;

	@OriginalMember(owner = "client!hc", name = "u", descriptor = "I")
	public int scrollHeight;

	@OriginalMember(owner = "client!hc", name = "v", descriptor = "I")
	public int scrollY;

	@OriginalMember(owner = "client!hc", name = "w", descriptor = "Z")
	public boolean hide;

	@OriginalMember(owner = "client!hc", name = "x", descriptor = "[I")
	public int[] children;

	@OriginalMember(owner = "client!hc", name = "y", descriptor = "[I")
	public int[] childX;

	@OriginalMember(owner = "client!hc", name = "z", descriptor = "[I")
	public int[] childY;

	@OriginalMember(owner = "client!hc", name = "A", descriptor = "I")
	public int int1;

	@OriginalMember(owner = "client!hc", name = "B", descriptor = "Z")
	public boolean bool1;

	@OriginalMember(owner = "client!hc", name = "C", descriptor = "Z")
	public boolean inventoryDummy;

	@OriginalMember(owner = "client!hc", name = "D", descriptor = "Z")
	public boolean inventoryHasOptions;

	@OriginalMember(owner = "client!hc", name = "E", descriptor = "Z")
	public boolean inventoryIsUsable;

	@OriginalMember(owner = "client!hc", name = "F", descriptor = "I")
	public int inventoryMarginX;

	@OriginalMember(owner = "client!hc", name = "G", descriptor = "I")
	public int inventoryMarginY;

	@OriginalMember(owner = "client!hc", name = "H", descriptor = "[Lclient!hb;")
	public Sprite[] inventorySprite;

	@OriginalMember(owner = "client!hc", name = "I", descriptor = "[I")
	public int[] inventoryOffsetX;

	@OriginalMember(owner = "client!hc", name = "J", descriptor = "[I")
	public int[] inventoryOffsetY;

	@OriginalMember(owner = "client!hc", name = "K", descriptor = "[Ljava/lang/String;")
	public String[] inventoryOptions;

	@OriginalMember(owner = "client!hc", name = "L", descriptor = "Z")
	public boolean fill;

	@OriginalMember(owner = "client!hc", name = "M", descriptor = "Z")
	public boolean halign;

	@OriginalMember(owner = "client!hc", name = "N", descriptor = "Z")
	public boolean shadowed;

	@OriginalMember(owner = "client!hc", name = "O", descriptor = "Lclient!jb;")
	public Font font;

	@OriginalMember(owner = "client!hc", name = "P", descriptor = "Ljava/lang/String;")
	public String text;

	@OriginalMember(owner = "client!hc", name = "Q", descriptor = "Ljava/lang/String;")
	public String activetext;

	@OriginalMember(owner = "client!hc", name = "R", descriptor = "I")
	public int colour;

	@OriginalMember(owner = "client!hc", name = "S", descriptor = "I")
	public int activecolour;

	@OriginalMember(owner = "client!hc", name = "T", descriptor = "I")
	public int overcolour;

	@OriginalMember(owner = "client!hc", name = "U", descriptor = "Lclient!hb;")
	public Sprite graphic;

	@OriginalMember(owner = "client!hc", name = "V", descriptor = "Lclient!hb;")
	public Sprite activegraphic;

	@OriginalMember(owner = "client!hc", name = "W", descriptor = "Lclient!eb;")
	public Model model;

	@OriginalMember(owner = "client!hc", name = "X", descriptor = "Lclient!eb;")
	public Model activemodel;

	@OriginalMember(owner = "client!hc", name = "Y", descriptor = "I")
	public int anim;

	@OriginalMember(owner = "client!hc", name = "Z", descriptor = "I")
	public int activeanim;

	@OriginalMember(owner = "client!hc", name = "ab", descriptor = "I")
	public int zoom;

	@OriginalMember(owner = "client!hc", name = "bb", descriptor = "I")
	public int xan;

	@OriginalMember(owner = "client!hc", name = "cb", descriptor = "I")
	public int yan;

	@OriginalMember(owner = "client!hc", name = "db", descriptor = "Ljava/lang/String;")
	public String optionCircumfix;

	@OriginalMember(owner = "client!hc", name = "eb", descriptor = "Ljava/lang/String;")
	public String optionSuffix;

	@OriginalMember(owner = "client!hc", name = "fb", descriptor = "I")
	public int optionFlags;

	@OriginalMember(owner = "client!hc", name = "gb", descriptor = "Ljava/lang/String;")
	public String option;

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!ub;[Lclient!jb;ILclient!ub;)V")
	public static void unpack(@OriginalArg(0) FileArchive mediaArchive, @OriginalArg(1) Font[] arg1, @OriginalArg(3) FileArchive interfaceArchive) {
		spriteCache = new Cache(50000);
		modelCache = new Cache(50000);
		@Pc(27) Buffer local27 = new Buffer(interfaceArchive.read("data", null));
		@Pc(29) int local29 = -1;
		@Pc(32) int local32 = local27.g2();
		instances = new Component[local32];
		while (true) {
			@Pc(62) Component local62;
			do {
				if (local27.pos >= local27.data.length) {
					spriteCache = null;
					modelCache = null;
					return;
				}
				@Pc(45) int local45 = local27.g2();
				if (local45 == 65535) {
					local29 = local27.g2();
					local45 = local27.g2();
				}
				local62 = instances[local45] = new Component();
				local62.id = local45;
				local62.parent = local29;
				local62.type = local27.g1();
				local62.buttontype = local27.g1();
				local62.clientcode = local27.g2();
				local62.width = local27.g2();
				local62.height = local27.g2();
				local62.overlayer = local27.g1();
				if (local62.overlayer == 0) {
					local62.overlayer = -1;
				} else {
					local62.overlayer = (local62.overlayer - 1 << 8) + local27.g1();
				}
				@Pc(114) int local114 = local27.g1();
				@Pc(126) int local126;
				if (local114 > 0) {
					local62.scriptCompareType = new int[local114];
					local62.scriptCompareValue = new int[local114];
					for (local126 = 0; local126 < local114; local126++) {
						local62.scriptCompareType[local126] = local27.g1();
						local62.scriptCompareValue[local126] = local27.g2();
					}
				}
				local126 = local27.g1();
				@Pc(155) int local155;
				@Pc(160) int local160;
				if (local126 > 0) {
					local62.script = new int[local126][];
					for (local155 = 0; local155 < local126; local155++) {
						local160 = local27.g2();
						local62.script[local155] = new int[local160];
						for (@Pc(168) int local168 = 0; local168 < local160; local168++) {
							local62.script[local155][local168] = local27.g2();
						}
					}
				}
				if (local62.type == 0) {
					local62.scrollHeight = local27.g2();
					local62.hide = local27.g1() == 1;
					local155 = local27.g1();
					local62.children = new int[local155];
					local62.childX = new int[local155];
					local62.childY = new int[local155];
					for (local160 = 0; local160 < local155; local160++) {
						local62.children[local160] = local27.g2();
						local62.childX[local160] = local27.g2b();
						local62.childY[local160] = local27.g2b();
					}
				}
				if (local62.type == 1) {
					local62.int1 = local27.g2();
					local62.bool1 = local27.g1() == 1;
				}
				if (local62.type == 2) {
					local62.inventoryIndices = new int[local62.width * local62.height];
					local62.inventoryAmount = new int[local62.width * local62.height];
					local62.inventoryDummy = local27.g1() == 1;
					local62.inventoryHasOptions = local27.g1() == 1;
					local62.inventoryIsUsable = local27.g1() == 1;
					local62.inventoryMarginX = local27.g1();
					local62.inventoryMarginY = local27.g1();
					local62.inventoryOffsetX = new int[20];
					local62.inventoryOffsetY = new int[20];
					local62.inventorySprite = new Sprite[20];
					for (local155 = 0; local155 < 20; local155++) {
						local160 = local27.g1();
						if (local160 == 1) {
							local62.inventoryOffsetX[local155] = local27.g2b();
							local62.inventoryOffsetY[local155] = local27.g2b();
							@Pc(352) String local352 = local27.gstr();
							if (mediaArchive != null && local352.length() > 0) {
								@Pc(361) int local361 = local352.lastIndexOf(",");
								local62.inventorySprite[local155] = getSprite(mediaArchive, Integer.parseInt(local352.substring(local361 + 1)), local352.substring(0, local361));
							}
						}
					}
					local62.inventoryOptions = new String[5];
					for (local160 = 0; local160 < 5; local160++) {
						local62.inventoryOptions[local160] = local27.gstr();
						if (local62.inventoryOptions[local160].length() == 0) {
							local62.inventoryOptions[local160] = null;
						}
					}
				}
				if (local62.type == 3) {
					local62.fill = local27.g1() == 1;
				}
				if (local62.type == 4 || local62.type == 1) {
					local62.halign = local27.g1() == 1;
					local155 = local27.g1();
					if (arg1 != null) {
						local62.font = arg1[local155];
					}
					local62.shadowed = local27.g1() == 1;
				}
				if (local62.type == 4) {
					local62.text = local27.gstr();
					local62.activetext = local27.gstr();
				}
				if (local62.type == 1 || local62.type == 3 || local62.type == 4) {
					local62.colour = local27.g4();
				}
				if (local62.type == 3 || local62.type == 4) {
					local62.activecolour = local27.g4();
					local62.overcolour = local27.g4();
				}
				if (local62.type == 5) {
					@Pc(511) String local511 = local27.gstr();
					if (mediaArchive != null && local511.length() > 0) {
						local160 = local511.lastIndexOf(",");
						local62.graphic = getSprite(mediaArchive, Integer.parseInt(local511.substring(local160 + 1)), local511.substring(0, local160));
					}
					local511 = local27.gstr();
					if (mediaArchive != null && local511.length() > 0) {
						local160 = local511.lastIndexOf(",");
						local62.activegraphic = getSprite(mediaArchive, Integer.parseInt(local511.substring(local160 + 1)), local511.substring(0, local160));
					}
				}
				if (local62.type == 6) {
					local45 = local27.g1();
					if (local45 != 0) {
						local62.model = getModel((local45 - 1 << 8) + local27.g1());
					}
					local45 = local27.g1();
					if (local45 != 0) {
						local62.activemodel = getModel((local45 - 1 << 8) + local27.g1());
					}
					local45 = local27.g1();
					if (local45 == 0) {
						local62.anim = -1;
					} else {
						local62.anim = (local45 - 1 << 8) + local27.g1();
					}
					local45 = local27.g1();
					if (local45 == 0) {
						local62.activeanim = -1;
					} else {
						local62.activeanim = (local45 - 1 << 8) + local27.g1();
					}
					local62.zoom = local27.g2();
					local62.xan = local27.g2();
					local62.yan = local27.g2();
				}
				if (local62.type == 7) {
					local62.inventoryIndices = new int[local62.width * local62.height];
					local62.inventoryAmount = new int[local62.width * local62.height];
					local62.halign = local27.g1() == 1;
					local155 = local27.g1();
					if (arg1 != null) {
						local62.font = arg1[local155];
					}
					local62.shadowed = local27.g1() == 1;
					local62.colour = local27.g4();
					local62.inventoryMarginX = local27.g2b();
					local62.inventoryMarginY = local27.g2b();
					local62.inventoryHasOptions = local27.g1() == 1;
					local62.inventoryOptions = new String[5];
					for (local160 = 0; local160 < 5; local160++) {
						local62.inventoryOptions[local160] = local27.gstr();
						if (local62.inventoryOptions[local160].length() == 0) {
							local62.inventoryOptions[local160] = null;
						}
					}
				}
				if (local62.buttontype == 2 || local62.type == 2) {
					local62.optionCircumfix = local27.gstr();
					local62.optionSuffix = local27.gstr();
					local62.optionFlags = local27.g2();
				}
			} while (local62.buttontype != 1 && local62.buttontype != 4 && local62.buttontype != 5 && local62.buttontype != 6);
			local62.option = local27.gstr();
			if (local62.option.length() == 0) {
				if (local62.buttontype == 1) {
					local62.option = "Ok";
				}
				if (local62.buttontype == 4) {
					local62.option = "Select";
				}
				if (local62.buttontype == 5) {
					local62.option = "Select";
				}
				if (local62.buttontype == 6) {
					local62.option = "Continue";
				}
			}
		}
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!ub;ILjava/lang/String;I)Lclient!hb;")
	private static Sprite getSprite(@OriginalArg(0) FileArchive arg0, @OriginalArg(1) int arg1, @OriginalArg(2) String arg2) {
		@Pc(8) long local8 = (StringUtils.genHash(arg2) << 8) + (long) arg1;
		@Pc(13) Sprite local13 = (Sprite) spriteCache.get(local8);
		if (local13 == null) {
			try {
				local13 = new Sprite(arg0, arg2, arg1);
				spriteCache.put(local8, local13);
				return local13;
			} catch (@Pc(38) Exception local38) {
				return null;
			}
		} else {
			return local13;
		}
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(II)Lclient!eb;")
	private static Model getModel(@OriginalArg(1) int arg1) {
		@Pc(5) Model local5 = (Model) modelCache.get(arg1);
		if (local5 == null) {
			local5 = new Model(false, arg1);
			modelCache.put(arg1, local5);
		}
		return local5;
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(IIZ)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2) {
		@Pc(2) Model local2 = this.model;
		if (arg2) {
			local2 = this.activemodel;
		}
		if (local2 == null) {
			return null;
		} else if (arg0 == -1 && arg1 == -1 && local2.unmodifiedTriangleColor == null) {
			return local2;
		} else {
			@Pc(31) Model local31 = new Model(local2, true, true, false);
			if (arg0 != -1 || arg1 != -1) {
				local31.applyGroup();
			}
			if (arg0 != -1) {
				local31.applyFrame(arg0);
			}
			if (arg1 != -1) {
				local31.applyFrame(arg1);
			}
			local31.applyLighting(64, 768, -50, -10, -50, true);
			return local31;
		}
	}
}
