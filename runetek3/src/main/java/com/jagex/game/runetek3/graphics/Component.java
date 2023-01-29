package com.jagex.game.runetek3.graphics;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.stringtools.StringUtils;
import com.jagex.core.util.Cache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!hc")
public class Component {

	public static final int IFTYPE_LAYER = 0;
	public static final int IFTYPE_UNUSED = 1;
	public static final int IFTYPE_INVENTORY = 2;
	public static final int IFTYPE_RECTANGLE = 3;
	public static final int IFTYPE_TEXT = 4;
	public static final int IFTYPE_GRAPHIC = 5;
	public static final int IFTYPE_MODEL = 6;
	public static final int IFTYPE_INVENTORY_TEXT = 7;

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
	public static void unpack(@OriginalArg(0) FileArchive mediaArchive, @OriginalArg(1) Font[] fonts, @OriginalArg(3) FileArchive interfaceArchive) {
		spriteCache = new Cache(50000);
		modelCache = new Cache(50000);

		@Pc(27) Buffer buf = new Buffer(interfaceArchive.read("data", null));
		@Pc(32) int count = buf.g2();
		instances = new Component[count];

		@Pc(29) int parent = -1;
		while (buf.pos < buf.data.length) {
			@Pc(45) int id = buf.g2();
			if (id == 65535) {
				parent = buf.g2();
				id = buf.g2();
			}

			@Pc(62) Component w = instances[id] = new Component();
			w.id = id;
			w.parent = parent;
			w.type = buf.g1();
			w.buttontype = buf.g1();
			w.clientcode = buf.g2();
			w.width = buf.g2();
			w.height = buf.g2();
			w.overlayer = buf.g1();

			if (w.overlayer == 0) {
				w.overlayer = -1;
			} else {
				w.overlayer = (w.overlayer - 1 << 8) + buf.g1();
			}

			@Pc(114) int comparatorCount = buf.g1();
			if (comparatorCount > 0) {
				w.scriptCompareType = new int[comparatorCount];
				w.scriptCompareValue = new int[comparatorCount];

				for (int i = 0; i < comparatorCount; i++) {
					w.scriptCompareType[i] = buf.g1();
					w.scriptCompareValue[i] = buf.g2();
				}
			}

			int scriptCount = buf.g1();
			if (scriptCount > 0) {
				w.script = new int[scriptCount][];

				for (int i = 0; i < scriptCount; i++) {
					int opcodeCount = buf.g2();
					w.script[i] = new int[opcodeCount];

					for (@Pc(168) int op = 0; op < opcodeCount; op++) {
						w.script[i][op] = buf.g2();
					}
				}
			}

			if (w.type == IFTYPE_LAYER) {
				w.scrollHeight = buf.g2();
				w.hide = buf.g1() == 1;

				int childrenCount = buf.g1();
				w.children = new int[childrenCount];
				w.childX = new int[childrenCount];
				w.childY = new int[childrenCount];

				for (int i = 0; i < childrenCount; i++) {
					w.children[i] = buf.g2();
					w.childX[i] = buf.g2b();
					w.childY[i] = buf.g2b();
				}
			}

			if (w.type == IFTYPE_UNUSED) {
				w.int1 = buf.g2();
				w.bool1 = buf.g1() == 1;
			}

			if (w.type == IFTYPE_INVENTORY) {
				w.inventoryIndices = new int[w.width * w.height];
				w.inventoryAmount = new int[w.width * w.height];
				w.inventoryDummy = buf.g1() == 1;
				w.inventoryHasOptions = buf.g1() == 1;
				w.inventoryIsUsable = buf.g1() == 1;
				w.inventoryMarginX = buf.g1();
				w.inventoryMarginY = buf.g1();

				w.inventoryOffsetX = new int[20];
				w.inventoryOffsetY = new int[20];
				w.inventorySprite = new Sprite[20];

				for (int i = 0; i < 20; i++) {
					int hasSprite = buf.g1();
					if (hasSprite == 1) {
						w.inventoryOffsetX[i] = buf.g2b();
						w.inventoryOffsetY[i] = buf.g2b();
						@Pc(352) String local352 = buf.gstr();
						if (mediaArchive != null && local352.length() > 0) {
							@Pc(361) int local361 = local352.lastIndexOf(",");
							w.inventorySprite[i] = getSprite(mediaArchive, Integer.parseInt(local352.substring(local361 + 1)), local352.substring(0, local361));
						}
					}
				}

				w.inventoryOptions = new String[5];

				for (int i = 0; i < 5; i++) {
					w.inventoryOptions[i] = buf.gstr();

					if (w.inventoryOptions[i].length() == 0) {
						w.inventoryOptions[i] = null;
					}
				}
			}

			if (w.type == IFTYPE_RECTANGLE) {
				w.fill = buf.g1() == 1;
			}

			if (w.type == IFTYPE_TEXT || w.type == IFTYPE_UNUSED) {
				w.halign = buf.g1() == 1;

				int fontId = buf.g1();
				if (fonts != null) {
					w.font = fonts[fontId];
				}

				w.shadowed = buf.g1() == 1;
			}

			if (w.type == IFTYPE_TEXT) {
				w.text = buf.gstr();
				w.activetext = buf.gstr();
			}

			if (w.type == IFTYPE_UNUSED || w.type == IFTYPE_RECTANGLE || w.type == IFTYPE_TEXT) {
				w.colour = buf.g4();
			}

			if (w.type == IFTYPE_RECTANGLE || w.type == IFTYPE_TEXT) {
				w.activecolour = buf.g4();
				w.overcolour = buf.g4();
			}

			if (w.type == IFTYPE_GRAPHIC) {
				@Pc(511) String inactive = buf.gstr();
				if (mediaArchive != null && inactive.length() > 0) {
					int i = inactive.lastIndexOf(",");
					w.graphic = getSprite(mediaArchive, Integer.parseInt(inactive.substring(i + 1)), inactive.substring(0, i));
				}

				String active = buf.gstr();
				if (mediaArchive != null && active.length() > 0) {
					int i = active.lastIndexOf(",");
					w.activegraphic = getSprite(mediaArchive, Integer.parseInt(active.substring(i + 1)), active.substring(0, i));
				}
			}

			if (w.type == IFTYPE_MODEL) {
				id = buf.g1();
				if (id != 0) {
					w.model = getModel((id - 1 << 8) + buf.g1());
				}

				id = buf.g1();
				if (id != 0) {
					w.activemodel = getModel((id - 1 << 8) + buf.g1());
				}

				id = buf.g1();
				if (id == 0) {
					w.anim = -1;
				} else {
					w.anim = (id - 1 << 8) + buf.g1();
				}

				id = buf.g1();
				if (id == 0) {
					w.activeanim = -1;
				} else {
					w.activeanim = (id - 1 << 8) + buf.g1();
				}

				w.zoom = buf.g2();
				w.xan = buf.g2();
				w.yan = buf.g2();
			}

			if (w.type == IFTYPE_INVENTORY_TEXT) {
				w.inventoryIndices = new int[w.width * w.height];
				w.inventoryAmount = new int[w.width * w.height];
				w.halign = buf.g1() == 1;

				int fontId = buf.g1();
				if (fonts != null) {
					w.font = fonts[fontId];
				}

				w.shadowed = buf.g1() == 1;
				w.colour = buf.g4();
				w.inventoryMarginX = buf.g2b();
				w.inventoryMarginY = buf.g2b();
				w.inventoryHasOptions = buf.g1() == 1;

				w.inventoryOptions = new String[5];

				for (int i = 0; i < 5; i++) {
					w.inventoryOptions[i] = buf.gstr();

					if (w.inventoryOptions[i].length() == 0) {
						w.inventoryOptions[i] = null;
					}
				}
			}

			if (w.buttontype == TARGET_BUTTON || w.type == IFTYPE_INVENTORY) {
				w.optionCircumfix = buf.gstr();
				w.optionSuffix = buf.gstr();
				w.optionFlags = buf.g2();
			}

			if (w.buttontype == BUTTON || w.buttontype == TOGGLE_BUTTON || w.buttontype == SELECT_BUTTON || w.buttontype == PAUSE_BUTTON) {
				w.option = buf.gstr();

				if (w.option.length() == 0) {
					if (w.buttontype == BUTTON) {
						w.option = "Ok";
					}

					if (w.buttontype == TOGGLE_BUTTON) {
						w.option = "Select";
					}

					if (w.buttontype == SELECT_BUTTON) {
						w.option = "Select";
					}

					if (w.buttontype == PAUSE_BUTTON) {
						w.option = "Continue";
					}
				}
			}
		}

		spriteCache = null;
		modelCache = null;
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!ub;ILjava/lang/String;I)Lclient!hb;")
	private static Sprite getSprite(@OriginalArg(0) FileArchive media, @OriginalArg(1) int id, @OriginalArg(2) String name) {
		@Pc(8) long key = (StringUtils.genHash(name) << 8) + (long) id;

		@Pc(13) Sprite sprite = (Sprite) spriteCache.get(key);
		if (sprite != null) {
			return sprite;
		}

		try {
			sprite = new Sprite(media, name, id);
			spriteCache.put(key, sprite);
			return sprite;
		} catch (@Pc(38) Exception ex) {
			return null;
		}
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(II)Lclient!eb;")
	private static Model getModel(@OriginalArg(1) int id) {
		@Pc(5) Model m = (Model) modelCache.get(id);
		if (m != null) {
			return m;
		}

		m = new Model(id);
		modelCache.put(id, m);
		return m;
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(IIZ)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int primaryFrame, @OriginalArg(1) int secondaryFrame, @OriginalArg(2) boolean active) {
		@Pc(2) Model m = this.model;
		if (active) {
			m = this.activemodel;
		}

		if (m == null) {
			return null;
		}

		if (primaryFrame == -1 && secondaryFrame == -1 && m.faceColor == null) {
			return m;
		}
		
		m = new Model(m, true, true, false);
		if (primaryFrame != -1 || secondaryFrame != -1) {
			m.createLabelReferences();
		}

		if (primaryFrame != -1) {
			m.applyTransform(primaryFrame);
		}

		if (secondaryFrame != -1) {
			m.applyTransform(secondaryFrame);
		}

		m.calculateNormals(64, 768, -50, -10, -50, true);
		return m;
	}
}
