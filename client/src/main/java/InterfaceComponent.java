import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!hc")
public final class InterfaceComponent {

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "I")
	private static int flowObfuscator1;

	@OriginalMember(owner = "client!hc", name = "b", descriptor = "Z")
	private static boolean flowObfuscator2;

	@OriginalMember(owner = "client!hc", name = "c", descriptor = "[Lclient!hc;")
	public static InterfaceComponent[] instances;

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
	public int buttonType;

	@OriginalMember(owner = "client!hc", name = "l", descriptor = "I")
	public int contentType;

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
	public int hoverParentIndex;

	@OriginalMember(owner = "client!hc", name = "u", descriptor = "I")
	public int scrollHeight;

	@OriginalMember(owner = "client!hc", name = "v", descriptor = "I")
	public int scrollY;

	@OriginalMember(owner = "client!hc", name = "w", descriptor = "Z")
	public boolean hidden;

	@OriginalMember(owner = "client!hc", name = "x", descriptor = "[I")
	public int[] children;

	@OriginalMember(owner = "client!hc", name = "y", descriptor = "[I")
	public int[] childX;

	@OriginalMember(owner = "client!hc", name = "z", descriptor = "[I")
	public int[] childY;

	@OriginalMember(owner = "client!hc", name = "A", descriptor = "I")
	private int unusedInt;

	@OriginalMember(owner = "client!hc", name = "B", descriptor = "Z")
	private boolean unusedBoolean;

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
	public boolean center;

	@OriginalMember(owner = "client!hc", name = "N", descriptor = "Z")
	public boolean shadow;

	@OriginalMember(owner = "client!hc", name = "O", descriptor = "Lclient!jb;")
	public IndexedFont font;

	@OriginalMember(owner = "client!hc", name = "P", descriptor = "Ljava/lang/String;")
	public String text;

	@OriginalMember(owner = "client!hc", name = "Q", descriptor = "Ljava/lang/String;")
	public String activeText;

	@OriginalMember(owner = "client!hc", name = "R", descriptor = "I")
	public int color;

	@OriginalMember(owner = "client!hc", name = "S", descriptor = "I")
	public int colorEnabled;

	@OriginalMember(owner = "client!hc", name = "T", descriptor = "I")
	public int hoverColor;

	@OriginalMember(owner = "client!hc", name = "U", descriptor = "Lclient!hb;")
	public Sprite sprite;

	@OriginalMember(owner = "client!hc", name = "V", descriptor = "Lclient!hb;")
	public Sprite activeSprite;

	@OriginalMember(owner = "client!hc", name = "W", descriptor = "Lclient!eb;")
	public Model modelDisabled;

	@OriginalMember(owner = "client!hc", name = "X", descriptor = "Lclient!eb;")
	private Model modelEnabled;

	@OriginalMember(owner = "client!hc", name = "Y", descriptor = "I")
	public int seqId;

	@OriginalMember(owner = "client!hc", name = "Z", descriptor = "I")
	public int activeSeqId;

	@OriginalMember(owner = "client!hc", name = "ab", descriptor = "I")
	public int modelZoom;

	@OriginalMember(owner = "client!hc", name = "bb", descriptor = "I")
	public int modelEyePitch;

	@OriginalMember(owner = "client!hc", name = "cb", descriptor = "I")
	public int modelYaw;

	@OriginalMember(owner = "client!hc", name = "db", descriptor = "Ljava/lang/String;")
	public String optionCircumfix;

	@OriginalMember(owner = "client!hc", name = "eb", descriptor = "Ljava/lang/String;")
	public String optionSuffix;

	@OriginalMember(owner = "client!hc", name = "fb", descriptor = "I")
	public int optionFlags;

	@OriginalMember(owner = "client!hc", name = "gb", descriptor = "Ljava/lang/String;")
	public String option;

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!ub;[Lclient!jb;ILclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive media, @OriginalArg(1) IndexedFont[] fonts, @OriginalArg(3) FileArchive interfaces) {
		spriteCache = new Cache((byte) 0, 50000);
		modelCache = new Cache((byte) 0, 50000);
		@Pc(27) Buffer buffer = new Buffer(363, interfaces.read("data", null));
		@Pc(29) int local29 = -1;
		@Pc(32) int local32 = buffer.g2();
		instances = new InterfaceComponent[local32];
		while (true) {
			@Pc(62) InterfaceComponent c;
			do {
				if (buffer.offset >= buffer.data.length) {
					spriteCache = null;
					modelCache = null;
					return;
				}
				@Pc(45) int local45 = buffer.g2();
				if (local45 == 65535) {
					local29 = buffer.g2();
					local45 = buffer.g2();
				}
				c = instances[local45] = new InterfaceComponent();
				c.id = local45;
				c.parent = local29;
				c.type = buffer.g1();
				c.buttonType = buffer.g1();
				c.contentType = buffer.g2();
				c.width = buffer.g2();
				c.height = buffer.g2();
				c.hoverParentIndex = buffer.g1();
				if (c.hoverParentIndex == 0) {
					c.hoverParentIndex = -1;
				} else {
					c.hoverParentIndex = (c.hoverParentIndex - 1 << 8) + buffer.g1();
				}
				@Pc(114) int local114 = buffer.g1();
				@Pc(126) int local126;
				if (local114 > 0) {
					c.scriptCompareType = new int[local114];
					c.scriptCompareValue = new int[local114];
					for (local126 = 0; local126 < local114; local126++) {
						c.scriptCompareType[local126] = buffer.g1();
						c.scriptCompareValue[local126] = buffer.g2();
					}
				}
				local126 = buffer.g1();
				@Pc(155) int local155;
				@Pc(160) int local160;
				if (local126 > 0) {
					c.script = new int[local126][];
					for (local155 = 0; local155 < local126; local155++) {
						local160 = buffer.g2();
						c.script[local155] = new int[local160];
						for (@Pc(168) int local168 = 0; local168 < local160; local168++) {
							c.script[local155][local168] = buffer.g2();
						}
					}
				}
				if (c.type == 0) {
					c.scrollHeight = buffer.g2();
					c.hidden = buffer.g1() == 1;
					local155 = buffer.g1();
					c.children = new int[local155];
					c.childX = new int[local155];
					c.childY = new int[local155];
					for (local160 = 0; local160 < local155; local160++) {
						c.children[local160] = buffer.g2();
						c.childX[local160] = buffer.g2s();
						c.childY[local160] = buffer.g2s();
					}
				}
				if (c.type == 1) {
					c.unusedInt = buffer.g2();
					c.unusedBoolean = buffer.g1() == 1;
				}
				if (c.type == 2) {
					c.inventoryIndices = new int[c.width * c.height];
					c.inventoryAmount = new int[c.width * c.height];
					c.inventoryDummy = buffer.g1() == 1;
					c.inventoryHasOptions = buffer.g1() == 1;
					c.inventoryIsUsable = buffer.g1() == 1;
					c.inventoryMarginX = buffer.g1();
					c.inventoryMarginY = buffer.g1();
					c.inventoryOffsetX = new int[20];
					c.inventoryOffsetY = new int[20];
					c.inventorySprite = new Sprite[20];
					for (local155 = 0; local155 < 20; local155++) {
						local160 = buffer.g1();
						if (local160 == 1) {
							c.inventoryOffsetX[local155] = buffer.g2s();
							c.inventoryOffsetY[local155] = buffer.g2s();
							@Pc(352) String local352 = buffer.gjstr();
							if (media != null && local352.length() > 0) {
								@Pc(361) int local361 = local352.lastIndexOf(",");
								c.inventorySprite[local155] = getSprite(media, Integer.parseInt(local352.substring(local361 + 1)), local352.substring(0, local361));
							}
						}
					}
					c.inventoryOptions = new String[5];
					for (local160 = 0; local160 < 5; local160++) {
						c.inventoryOptions[local160] = buffer.gjstr();
						if (c.inventoryOptions[local160].length() == 0) {
							c.inventoryOptions[local160] = null;
						}
					}
				}
				if (c.type == 3) {
					c.fill = buffer.g1() == 1;
				}
				if (c.type == 4 || c.type == 1) {
					c.center = buffer.g1() == 1;
					local155 = buffer.g1();
					if (fonts != null) {
						c.font = fonts[local155];
					}
					c.shadow = buffer.g1() == 1;
				}
				if (c.type == 4) {
					c.text = buffer.gjstr();
					c.activeText = buffer.gjstr();
				}
				if (c.type == 1 || c.type == 3 || c.type == 4) {
					c.color = buffer.g4();
				}
				if (c.type == 3 || c.type == 4) {
					c.colorEnabled = buffer.g4();
					c.hoverColor = buffer.g4();
				}
				if (c.type == 5) {
					@Pc(511) String local511 = buffer.gjstr();
					if (media != null && local511.length() > 0) {
						local160 = local511.lastIndexOf(",");
						c.sprite = getSprite(media, Integer.parseInt(local511.substring(local160 + 1)), local511.substring(0, local160));
					}
					local511 = buffer.gjstr();
					if (media != null && local511.length() > 0) {
						local160 = local511.lastIndexOf(",");
						c.activeSprite = getSprite(media, Integer.parseInt(local511.substring(local160 + 1)), local511.substring(0, local160));
					}
				}
				if (c.type == 6) {
					local45 = buffer.g1();
					if (local45 != 0) {
						c.modelDisabled = getModel((local45 - 1 << 8) + buffer.g1());
					}
					local45 = buffer.g1();
					if (local45 != 0) {
						c.modelEnabled = getModel((local45 - 1 << 8) + buffer.g1());
					}
					local45 = buffer.g1();
					if (local45 == 0) {
						c.seqId = -1;
					} else {
						c.seqId = (local45 - 1 << 8) + buffer.g1();
					}
					local45 = buffer.g1();
					if (local45 == 0) {
						c.activeSeqId = -1;
					} else {
						c.activeSeqId = (local45 - 1 << 8) + buffer.g1();
					}
					c.modelZoom = buffer.g2();
					c.modelEyePitch = buffer.g2();
					c.modelYaw = buffer.g2();
				}
				if (c.type == 7) {
					c.inventoryIndices = new int[c.width * c.height];
					c.inventoryAmount = new int[c.width * c.height];
					c.center = buffer.g1() == 1;
					local155 = buffer.g1();
					if (fonts != null) {
						c.font = fonts[local155];
					}
					c.shadow = buffer.g1() == 1;
					c.color = buffer.g4();
					c.inventoryMarginX = buffer.g2s();
					c.inventoryMarginY = buffer.g2s();
					c.inventoryHasOptions = buffer.g1() == 1;
					c.inventoryOptions = new String[5];
					for (local160 = 0; local160 < 5; local160++) {
						c.inventoryOptions[local160] = buffer.gjstr();
						if (c.inventoryOptions[local160].length() == 0) {
							c.inventoryOptions[local160] = null;
						}
					}
				}
				if (c.buttonType == 2 || c.type == 2) {
					c.optionCircumfix = buffer.gjstr();
					c.optionSuffix = buffer.gjstr();
					c.optionFlags = buffer.g2();
				}
			} while (c.buttonType != 1 && c.buttonType != 4 && c.buttonType != 5 && c.buttonType != 6);
			c.option = buffer.gjstr();
			if (c.option.length() == 0) {
				if (c.buttonType == 1) {
					c.option = "Ok";
				}
				if (c.buttonType == 4) {
					c.option = "Select";
				}
				if (c.buttonType == 5) {
					c.option = "Select";
				}
				if (c.buttonType == 6) {
					c.option = "Continue";
				}
			}
		}
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!ub;ILjava/lang/String;I)Lclient!hb;")
	private static Sprite getSprite(@OriginalArg(0) FileArchive arg0, @OriginalArg(1) int arg1, @OriginalArg(2) String arg2) {
		@Pc(8) long local8 = (StringUtils.genHash(arg2) << 8) + (long) arg1;
		@Pc(13) Sprite local13 = (Sprite) spriteCache.get(local8);
		if (local13 != null) {
			return local13;
		}
		try {
			local13 = new Sprite(arg0, arg2, arg1);
			spriteCache.put(local8, local13);
			return local13;
		} catch (@Pc(38) Exception local38) {
			return null;
		}
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(II)Lclient!eb;")
	private static Model getModel(@OriginalArg(1) int arg0) {
		@Pc(5) Model local5 = (Model) modelCache.get((long) arg0);
		if (local5 == null) {
			local5 = new Model(false, arg0);
			modelCache.put((long) arg0, local5);
			return local5;
		} else {
			return local5;
		}
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(IIZ)Lclient!eb;")
	public final Model getModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2) {
		@Pc(2) Model local2 = this.modelDisabled;
		if (arg2) {
			local2 = this.modelEnabled;
		}
		if (local2 == null) {
			return null;
		} else if (arg0 == -1 && arg1 == -1 && local2.unmodifiedTriangleColor == null) {
			return local2;
		} else {
			@Pc(31) Model local31 = new Model(local2, true, true, flowObfuscator1, false);
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
