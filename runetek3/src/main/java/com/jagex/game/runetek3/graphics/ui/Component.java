package com.jagex.game.runetek3.graphics.ui;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.stringutils.StringUtils;
import com.jagex.core.utils.Cache;
import com.jagex.game.runetek3.graphics.Sprite;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!hc")
public class Component {

	public static final int TYPE_PARENT = 0;
	public static final int TYPE_UNUSED = 1;
	public static final int TYPE_INVENTORY = 2;
	public static final int TYPE_RECT = 3;
	public static final int TYPE_TEXT = 4;
	public static final int TYPE_SPRITE = 5;
	public static final int TYPE_MODEL = 6;
	public static final int TYPE_INVENTORY_TEXT = 7;

	public static final int NO_BUTTON = 0;
	public static final int BUTTON = 1;
	public static final int TARGET_BUTTON = 2;
	public static final int CLOSE_BUTTON = 3;
	public static final int TOGGLE_BUTTON = 4;
	public static final int SELECT_BUTTON = 5;
	public static final int PAUSE_BUTTON = 6;

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
	public int buttonType;

	@OriginalMember(owner = "client!hc", name = "l", descriptor = "I")
	public int clientCode;

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
	public Font font;

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
	public static void decode(@OriginalArg(0) FileArchive media, @OriginalArg(1) Font[] fonts, @OriginalArg(3) FileArchive interfaces) {
		spriteCache = new Cache(50000);
		modelCache = new Cache(50000);

		@Pc(27) Buffer buffer = new Buffer(interfaces.read("data", null));
		@Pc(32) int count = buffer.g2();
		instances = new Component[count];

		@Pc(29) int parent = -1;
		while (true) {
			@Pc(62) Component c;
			do {
				if (buffer.pos >= buffer.data.length) {
					spriteCache = null;
					modelCache = null;
					return;
				}

				@Pc(45) int id = buffer.g2();
				if (id == 65535) {
					parent = buffer.g2();
					id = buffer.g2();
				}

				c = instances[id] = new Component();
				c.id = id;
				c.parent = parent;
				c.type = buffer.g1();
				c.buttonType = buffer.g1();
				c.clientCode = buffer.g2();
				c.width = buffer.g2();
				c.height = buffer.g2();
				c.hoverParentIndex = buffer.g1();
				if (c.hoverParentIndex == 0) {
					c.hoverParentIndex = -1;
				} else {
					c.hoverParentIndex = (c.hoverParentIndex - 1 << 8) + buffer.g1();
				}

				@Pc(114) int comparatorCount = buffer.g1();
				if (comparatorCount > 0) {
					c.scriptCompareType = new int[comparatorCount];
					c.scriptCompareValue = new int[comparatorCount];
					for (int i = 0; i < comparatorCount; i++) {
						c.scriptCompareType[i] = buffer.g1();
						c.scriptCompareValue[i] = buffer.g2();
					}
				}

				int scriptCount = buffer.g1();
				if (scriptCount > 0) {
					c.script = new int[scriptCount][];
					for (int i = 0; i < scriptCount; i++) {
						int opcodeCount = buffer.g2();
						c.script[i] = new int[opcodeCount];
						for (@Pc(168) int local168 = 0; local168 < opcodeCount; local168++) {
							c.script[i][local168] = buffer.g2();
						}
					}
				}

				if (c.type == Component.TYPE_PARENT) {
					c.scrollHeight = buffer.g2();
					c.hidden = buffer.g1() == 1;

					int n = buffer.g1();
					c.children = new int[n];
					c.childX = new int[n];
					c.childY = new int[n];

					for (int m = 0; m < n; m++) {
						c.children[m] = buffer.g2();
						c.childX[m] = buffer.g2b();
						c.childY[m] = buffer.g2b();
					}
				}

				if (c.type == Component.TYPE_UNUSED) {
					c.unusedInt = buffer.g2();
					c.unusedBoolean = buffer.g1() == 1;
				}

				if (c.type == Component.TYPE_INVENTORY) {
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
					for (int i = 0; i < 20; i++) {
						int hasSprite = buffer.g1();
						if (hasSprite == 1) {
							c.inventoryOffsetX[i] = buffer.g2b();
							c.inventoryOffsetY[i] = buffer.g2b();
							@Pc(352) String local352 = buffer.gstr();
							if (media != null && local352.length() > 0) {
								@Pc(361) int local361 = local352.lastIndexOf(",");
								c.inventorySprite[i] = getSprite(media, Integer.parseInt(local352.substring(local361 + 1)), local352.substring(0, local361));
							}
						}
					}
					c.inventoryOptions = new String[5];
					for (int i = 0; i < 5; i++) {
						c.inventoryOptions[i] = buffer.gstr();
						if (c.inventoryOptions[i].length() == 0) {
							c.inventoryOptions[i] = null;
						}
					}
				}

				if (c.type == Component.TYPE_RECT) {
					c.fill = buffer.g1() == 1;
				}

				if (c.type == Component.TYPE_TEXT || c.type == Component.TYPE_UNUSED) {
					c.center = buffer.g1() == 1;
					int i = buffer.g1();
					if (fonts != null) {
						c.font = fonts[i];
					}
					c.shadow = buffer.g1() == 1;
				}

				if (c.type == Component.TYPE_TEXT) {
					c.text = buffer.gstr();
					c.activeText = buffer.gstr();
				}

				if (c.type == Component.TYPE_UNUSED || c.type == Component.TYPE_RECT || c.type == Component.TYPE_TEXT) {
					c.color = buffer.g4();
				}

				if (c.type == Component.TYPE_RECT || c.type == Component.TYPE_TEXT) {
					c.colorEnabled = buffer.g4();
					c.hoverColor = buffer.g4();
				}

				if (c.type == Component.TYPE_SPRITE) {
					@Pc(511) String s = buffer.gstr();
					if (media != null && s.length() > 0) {
						int i = s.lastIndexOf(",");
						c.sprite = getSprite(media, Integer.parseInt(s.substring(i + 1)), s.substring(0, i));
					}
					s = buffer.gstr();
					if (media != null && s.length() > 0) {
						int i = s.lastIndexOf(",");
						c.activeSprite = getSprite(media, Integer.parseInt(s.substring(i + 1)), s.substring(0, i));
					}
				}

				if (c.type == Component.TYPE_MODEL) {
					id = buffer.g1();
					if (id != 0) {
						c.modelDisabled = getModel((id - 1 << 8) + buffer.g1());
					}
					id = buffer.g1();
					if (id != 0) {
						c.modelEnabled = getModel((id - 1 << 8) + buffer.g1());
					}
					id = buffer.g1();
					if (id == 0) {
						c.seqId = -1;
					} else {
						c.seqId = (id - 1 << 8) + buffer.g1();
					}
					id = buffer.g1();
					if (id == 0) {
						c.activeSeqId = -1;
					} else {
						c.activeSeqId = (id - 1 << 8) + buffer.g1();
					}
					c.modelZoom = buffer.g2();
					c.modelEyePitch = buffer.g2();
					c.modelYaw = buffer.g2();
				}

				if (c.type == Component.TYPE_INVENTORY_TEXT) {
					c.inventoryIndices = new int[c.width * c.height];
					c.inventoryAmount = new int[c.width * c.height];
					c.center = buffer.g1() == 1;
					int font = buffer.g1();
					if (fonts != null) {
						c.font = fonts[font];
					}
					c.shadow = buffer.g1() == 1;
					c.color = buffer.g4();
					c.inventoryMarginX = buffer.g2b();
					c.inventoryMarginY = buffer.g2b();
					c.inventoryHasOptions = buffer.g1() == 1;
					c.inventoryOptions = new String[5];
					for (int i = 0; i < 5; i++) {
						c.inventoryOptions[i] = buffer.gstr();
						if (c.inventoryOptions[i].length() == 0) {
							c.inventoryOptions[i] = null;
						}
					}
				}

				if (c.buttonType == Component.TARGET_BUTTON || c.type == Component.TYPE_INVENTORY) {
					c.optionCircumfix = buffer.gstr();
					c.optionSuffix = buffer.gstr();
					c.optionFlags = buffer.g2();
				}
			} while (c.buttonType != Component.BUTTON && c.buttonType != Component.TOGGLE_BUTTON && c.buttonType != Component.SELECT_BUTTON && c.buttonType != Component.PAUSE_BUTTON);

			c.option = buffer.gstr();
			if (c.option.length() == 0) {
				if (c.buttonType == Component.BUTTON) {
					c.option = "Ok";
				}

				if (c.buttonType == Component.TOGGLE_BUTTON) {
					c.option = "Select";
				}

				if (c.buttonType == Component.SELECT_BUTTON) {
					c.option = "Select";
				}

				if (c.buttonType == Component.PAUSE_BUTTON) {
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
			local5 = new Model(arg0);
			modelCache.put((long) arg0, local5);
			return local5;
		} else {
			return local5;
		}
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(IIZ)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2) {
		@Pc(2) Model local2 = this.modelDisabled;
		if (arg2) {
			local2 = this.modelEnabled;
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

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "I")
	private static int flowObfuscator1;

	@OriginalMember(owner = "client!hc", name = "b", descriptor = "Z")
	private static boolean flowObfuscator2;

}
