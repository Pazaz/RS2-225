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

import java.util.Arrays;

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

	public String[] inventorySpriteStr;

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
	public int fontId;

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
	public String graphicstr;

	@OriginalMember(owner = "client!hc", name = "V", descriptor = "Lclient!hb;")
	public Sprite activegraphic;
	public String activegraphicstr;

	@OriginalMember(owner = "client!hc", name = "W", descriptor = "Lclient!eb;")
	public Model model;

	@OriginalMember(owner = "client!hc", name = "X", descriptor = "Lclient!eb;")
	private Model activemodel;

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

	public static int count;

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!ub;[Lclient!jb;ILclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive media, @OriginalArg(1) Font[] fonts, @OriginalArg(3) FileArchive interfaces) {
		spriteCache = new Cache(50000);
		modelCache = new Cache(50000);

		@Pc(27) Buffer buffer = new Buffer(interfaces.read("data", null));
		count = buffer.g2();
		instances = new Component[count];
		newIds = new int[count];
		Arrays.fill(newIds, -1);

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
				c.buttontype = buffer.g1();
				c.clientcode = buffer.g2();
				c.width = buffer.g2();
				c.height = buffer.g2();
				c.overlayer = buffer.g1();
				if (c.overlayer == 0) {
					c.overlayer = -1;
				} else {
					c.overlayer = (c.overlayer - 1 << 8) + buffer.g1();
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
						for (@Pc(168) int op = 0; op < opcodeCount; op++) {
							c.script[i][op] = buffer.g2();
						}
					}
				}

				if (c.type == Component.IFTYPE_LAYER) {
					c.scrollHeight = buffer.g2();
					c.hide = buffer.g1() == 1;

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

				if (c.type == Component.IFTYPE_UNUSED) {
					c.unusedInt = buffer.g2();
					c.unusedBoolean = buffer.g1() == 1;
				}

				if (c.type == Component.IFTYPE_INVENTORY) {
					c.inventoryIndices = new int[c.width * c.height];
					c.inventoryAmount = new int[c.width * c.height];
					c.inventoryDummy = buffer.g1() == 1;
					c.inventoryHasOptions = buffer.g1() == 1;
					c.inventoryIsUsable = buffer.g1() == 1;
					c.inventoryMarginX = buffer.g1();
					c.inventoryMarginY = buffer.g1();
					c.inventoryOffsetX = new int[20];
					c.inventoryOffsetY = new int[20];
					c.inventorySpriteStr = new String[20];
					c.inventorySprite = new Sprite[20];
					for (int i = 0; i < 20; i++) {
						int hasSprite = buffer.g1();
						if (hasSprite == 1) {
							c.inventoryOffsetX[i] = buffer.g2b();
							c.inventoryOffsetY[i] = buffer.g2b();
							c.inventorySpriteStr[i] = buffer.gstr();
							if (media != null && c.inventorySpriteStr[i].length() > 0) {
								@Pc(361) int local361 = c.inventorySpriteStr[i].lastIndexOf(",");
								c.inventorySprite[i] = getSprite(media, Integer.parseInt(c.inventorySpriteStr[i].substring(local361 + 1)), c.inventorySpriteStr[i].substring(0, local361));
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

				if (c.type == Component.IFTYPE_RECTANGLE) {
					c.fill = buffer.g1() == 1;
				}

				if (c.type == Component.IFTYPE_TEXT || c.type == Component.IFTYPE_UNUSED) {
					c.halign = buffer.g1() == 1;
					c.fontId = buffer.g1();
					if (fonts != null) {
						c.font = fonts[c.fontId];
					}
					c.shadowed = buffer.g1() == 1;
				}

				if (c.type == Component.IFTYPE_TEXT) {
					c.text = buffer.gstr();
					c.activetext = buffer.gstr();
				}

				if (c.type == Component.IFTYPE_UNUSED || c.type == Component.IFTYPE_RECTANGLE || c.type == Component.IFTYPE_TEXT) {
					c.colour = buffer.g4();
				}

				if (c.type == Component.IFTYPE_RECTANGLE || c.type == Component.IFTYPE_TEXT) {
					c.activecolour = buffer.g4();
					c.overcolour = buffer.g4();
				}

				if (c.type == Component.IFTYPE_GRAPHIC) {
					c.graphicstr = buffer.gstr();
					if (media != null && c.graphicstr.length() > 0) {
						int i = c.graphicstr.lastIndexOf(",");
						c.graphic = getSprite(media, Integer.parseInt(c.graphicstr.substring(i + 1)), c.graphicstr.substring(0, i));
					}

					c.activegraphicstr = buffer.gstr();
					if (media != null && c.activegraphicstr.length() > 0) {
						int i = c.activegraphicstr.lastIndexOf(",");
						c.activegraphic = getSprite(media, Integer.parseInt(c.activegraphicstr.substring(i + 1)), c.activegraphicstr.substring(0, i));
					}
				}

				if (c.type == Component.IFTYPE_MODEL) {
					id = buffer.g1();
					if (id != 0) {
						c.model = getModel((id - 1 << 8) + buffer.g1());
					}
					id = buffer.g1();
					if (id != 0) {
						c.activemodel = getModel((id - 1 << 8) + buffer.g1());
					}
					id = buffer.g1();
					if (id == 0) {
						c.anim = -1;
					} else {
						c.anim = (id - 1 << 8) + buffer.g1();
					}
					id = buffer.g1();
					if (id == 0) {
						c.activeanim = -1;
					} else {
						c.activeanim = (id - 1 << 8) + buffer.g1();
					}
					c.zoom = buffer.g2();
					c.xan = buffer.g2();
					c.yan = buffer.g2();
				}

				if (c.type == Component.IFTYPE_INVENTORY_TEXT) {
					c.inventoryIndices = new int[c.width * c.height];
					c.inventoryAmount = new int[c.width * c.height];
					c.halign = buffer.g1() == 1;
					c.fontId = buffer.g1();
					if (fonts != null) {
						c.font = fonts[c.fontId];
					}
					c.shadowed = buffer.g1() == 1;
					c.colour = buffer.g4();
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

				if (c.buttontype == Component.TARGET_BUTTON || c.type == Component.IFTYPE_INVENTORY) {
					c.optionCircumfix = buffer.gstr();
					c.optionSuffix = buffer.gstr();
					c.optionFlags = buffer.g2();
				}
			} while (c.buttontype != Component.BUTTON && c.buttontype != Component.TOGGLE_BUTTON && c.buttontype != Component.SELECT_BUTTON && c.buttontype != Component.PAUSE_BUTTON);

			c.option = buffer.gstr();
			if (c.option.length() == 0) {
				if (c.buttontype == Component.BUTTON) {
					c.option = "Ok";
				}

				if (c.buttontype == Component.TOGGLE_BUTTON) {
					c.option = "Select";
				}

				if (c.buttontype == Component.SELECT_BUTTON) {
					c.option = "Select";
				}

				if (c.buttontype == Component.PAUSE_BUTTON) {
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

	public static int[] newIds;
	public boolean isWidget = false;

	public void generateNewIdsInner(int widgetId) {
		if (this.type == IFTYPE_LAYER) {
			for (int i = 0; i < this.children.length; ++i) {
				Component child = instances[this.children[i]];
				newIds[child.id] = lastChildId;
				child.id = lastChildId;
				lastChildId++;
				child.generateNewIdsInner(widgetId);
			}
		}
	}

	public void generateNewIds(int id) {
		newIds[this.id] = this.id;
		this.id = id;
		lastChildId = 0;
		generateNewIdsInner(id);
	}

	public void componentToJagConfig(int widgetId, int layerId, StringBuilder builder) {
		if (this.id != widgetId) {
			String typeName = "";
			switch (this.type) {
				case 0:
					typeName = "IFTYPE_LAYER";
					break;
				case 1:
					typeName = "IFTYPE_UNUSED";
					break;
				case 2:
					typeName = "IFTYPE_INVENTORY";
					break;
				case 3:
					typeName = "IFTYPE_RECTANGLE";
					break;
				case 4:
					typeName = "IFTYPE_TEXT";
					break;
				case 5:
					typeName = "IFTYPE_GRAPHIC";
					break;
				case 6:
					typeName = "IFTYPE_MODEL";
					break;
				case 7:
					typeName = "IFTYPE_INVENTORY_TEXT";
					break;
			}
			builder.append("type=^").append(typeName).append("\n");

			if (this.buttontype != NO_BUTTON) {
				String buttonName = "";
				switch (this.buttontype) {
					case 1:
						buttonName = "BUTTON";
						break;
					case 2:
						buttonName = "TARGET";
						break;
					case 3:
						buttonName = "CLOSE";
						break;
					case 4:
						buttonName = "TOGGLE";
						break;
					case 5:
						buttonName = "SELECT";
						break;
					case 6:
						buttonName = "PAUSE";
						break;
				}
				builder.append("buttontype=^").append(buttonName).append("\n");
			}

			if (buttontype == TARGET_BUTTON || type == IFTYPE_INVENTORY) {
				if (optionCircumfix.length() > 0) {
					builder.append("circumfix=\"").append(optionCircumfix).append("\"\n");
				}

				if (optionSuffix.length() > 0) {
					builder.append("suffix=\"").append(optionSuffix).append("\"\n");
				}

				if (optionFlags > 0) {
					builder.append("optionflags=").append(optionFlags).append("\n");
				}
			}

			if (this.clientcode != 0) {
				builder.append("clientcode=").append(this.clientcode).append("\n");
			}

			if (this.width != 0) {
				builder.append("width=").append(this.width).append("\n");
			}

			if (this.height != 0) {
				builder.append("height=").append(this.height).append("\n");
			}

			if (widgetId != layerId) {
				builder.append("layer=interface_").append(widgetId).append(":").append(layerId).append("\n");
			}

			if (this.overlayer != -1) {
				// todo: generate new ID -before- iterating through everything, links to another layer
				builder.append("overlayer=interface_").append(widgetId).append(":").append(newIds[overlayer]).append("\n");
			}

			if (this.scriptCompareType != null) {
				for (int i = 0; i < scriptCompareType.length; ++i) {
					builder.append("scriptc").append(i + 1).append("=").append(this.scriptCompareType[i]).append("\n");
					builder.append("scriptv").append(i + 1).append("=").append(this.scriptCompareValue[i]).append("\n");
				}
			}

			if (this.script != null) {
				for (int i = 0; i < script.length; ++i) {
					for (int j = 0; j < script[i].length; ++j) {
						builder.append("script").append(i + 1).append("op").append(j + 1).append("=").append(this.script[i][j]).append("\n");
					}
				}
			}

			if (this.option != null) {
				if (!this.option.equals("Ok") && !this.option.equals("Select") && !this.option.equals("Continue")) {
					builder.append("option=\"").append(this.option).append("\"\n");
				}
			}

			if (this.type == IFTYPE_GRAPHIC) {
				if (graphicstr.length() > 0) {
					builder.append("graphic=\"").append(graphicstr).append("\"\n");
				}

				if (activegraphicstr.length() > 0) {
					builder.append("activegraphic=\"").append(activegraphicstr).append("\"\n");
				}
			}

			if (this.type == IFTYPE_TEXT) {
				if (halign) {
					builder.append("halign=^SETTEXTALIGN_CENTRE\n");
				}

				if (font != null) {
					String fontName = "";
					switch (fontId) {
						case 0:
							fontName = "PLAIN_SMALL"; // p11
							break;
						case 1:
							fontName = "PLAIN"; // p12
							break;
						case 2:
							fontName = "BOLD"; // b12
							break;
						case 3:
							fontName = "QUILL"; // q8
							break;
					}
					builder.append("font=^").append(fontName).append("\n");
				}

				if (text.length() > 0) {
					builder.append("text=\"").append(text).append("\"\n");
				}

				if (activetext.length() > 0) {
					builder.append("activetext=").append(activetext).append("\n");
				}

				if (colour > 0) {
					builder.append("colour=0x").append(String.format("%06X", colour)).append("\n");
				}

				if (activecolour > 0) {
					builder.append("activecolour=0x").append(String.format("%06X", activecolour)).append("\n");
				}

				if (overcolour > 0) {
					builder.append("overcolour=0x").append(String.format("%06X", overcolour)).append("\n");
				}

				if (shadowed) {
					builder.append("shadowed=yes\n");
				}
			}

			if (this.type == IFTYPE_RECTANGLE) {
				if (this.fill) {
					builder.append("fill=yes\n");
				}

				if (colour > 0) {
					builder.append("colour=0x").append(String.format("%06X", colour)).append("\n");
				}

				if (activecolour > 0) {
					builder.append("activecolour=0x").append(String.format("%06X", activecolour)).append("\n");
				}

				if (overcolour > 0) {
					builder.append("overcolour=0x").append(String.format("%06X", overcolour)).append("\n");
				}
			}

			if (type == IFTYPE_MODEL) {
				if (model != null) {
					builder.append("model=").append(model.id).append("\n");
				}

				if (activemodel != null) {
					builder.append("activemodel=").append(activemodel.id).append("\n");
				}

				if (anim != -1) {
					builder.append("anim=seq_").append(anim).append("\n");
				}

				if (activeanim != -1) {
					builder.append("activeanim=seq_").append(activeanim).append("\n");
				}

				if (zoom > 0) {
					builder.append("zoom=").append(zoom).append("\n");
				}

				if (xan > 0) {
					builder.append("xan=").append(xan).append("\n");
				}

				if (yan > 0) {
					builder.append("yan=").append(yan).append("\n");
				}
			}

			if (type == IFTYPE_INVENTORY) {
				if (inventoryDummy) {
					builder.append("invdummy=yes\n");
				}

				if (inventoryHasOptions) {
					builder.append("hasoptions=yes\n");
				}

				if (inventoryIsUsable) {
					builder.append("usable=yes\n");
				}

				if (inventoryMarginX != 0) {
					builder.append("xof=").append(inventoryMarginX).append("\n");
				}

				if (inventoryMarginY != 0) {
					builder.append("yof=").append(inventoryMarginY).append("\n");
				}

				for (int i = 0; i < inventoryOffsetX.length; ++i) {
					if (inventorySpriteStr[i] != null && inventorySpriteStr[i].length() > 0) {
						builder.append("invsprite").append(i + 1).append("=").append(inventorySpriteStr[i]).append("\n");
					}

					if (inventoryOffsetX[i] != 0) {
						builder.append("xof").append(i + 1).append("=").append(inventoryOffsetX[i]).append("\n");
					}

					if (inventoryOffsetY[i] != 0) {
						builder.append("yof").append(i + 1).append("=").append(inventoryOffsetY[i]).append("\n");
					}
				}

				for (int i = 0; i < inventoryOptions.length; ++i) {
					if (inventoryOptions[i] == null) {
						continue;
					}

					builder.append("invoption").append(i + 1).append("=\"").append(inventoryOptions[i]).append("\"\n");
				}
			}

			if (type == IFTYPE_INVENTORY_TEXT) {
				if (halign) {
					builder.append("halign=^SETTEXTALIGN_CENTRE\n");
				}

				if (font != null) {
					String fontName = "";
					switch (fontId) {
						case 0:
							fontName = "PLAIN_SMALL"; // p11
							break;
						case 1:
							fontName = "PLAIN"; // p12
							break;
						case 2:
							fontName = "BOLD"; // b12
							break;
						case 3:
							fontName = "QUILL"; // q8
							break;
					}
					builder.append("font=^").append(fontName).append("\n");
				}

				if (colour > 0) {
					builder.append("colour=0x").append(String.format("%06X", colour)).append("\n");
				}

				if (shadowed) {
					builder.append("shadowed=yes\n");
				}

				if (inventoryMarginX != 0) {
					builder.append("marginx=").append(inventoryMarginX).append("\n");
				}

				if (inventoryMarginY != 0) {
					builder.append("marginy=").append(inventoryMarginY).append("\n");
				}

				if (inventoryHasOptions) {
					builder.append("hasoptions=yes\n");
				}

				for (int i = 0; i < inventoryOptions.length; ++i) {
					if (inventoryOptions[i] == null) {
						continue;
					}

					builder.append("invoption").append(i + 1).append("=").append(inventoryOptions[i]).append("\n");
				}
			}
		}

		if (this.type == IFTYPE_LAYER) {
			if (this.scrollHeight > 0) {
				builder.append("scrollheight=").append(this.scrollHeight).append("\n");
			}

			if (this.hide) {
				builder.append("hide=yes\n");
			}

			// if this is the first entry, add a new line before reading the children
			if (builder.length() > 1) {
				builder.append("\n");
			}

			for (int i = 0; i < this.children.length; ++i) {
				builder.append("[interface_").append(widgetId).append(":").append(lastChildId).append("]\n");
				Component child = instances[this.children[i]];
				newIds[child.id] = lastChildId;
				child.id = lastChildId;
				if (this.childX[i] > 0) {
					builder.append("x=").append(this.childX[i]).append("\n");
				}
				if (this.childY[i] > 0) {
					builder.append("y=").append(this.childY[i]).append("\n");
				}
				lastChildId++;
				child.componentToJagConfig(widgetId, this.id, builder);
			}
		} else {
			builder.append("\n");
		}
	}

	public static int lastChildId = 0;

	public String toJagConfig(int id) {
		StringBuilder builder = new StringBuilder();

		newIds[this.id] = id;
		this.id = id;
		lastChildId = 0;
		componentToJagConfig(id, id, builder);

		return builder.toString();
	}

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "I")
	private static int flowObfuscator1;

	@OriginalMember(owner = "client!hc", name = "b", descriptor = "Z")
	private static boolean flowObfuscator2;

}
