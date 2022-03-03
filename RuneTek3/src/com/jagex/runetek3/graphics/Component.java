package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Cache;
import com.jagex.runetek3.util.StringUtils;

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

    public static void load(FileArchive media, Font[] fonts, FileArchive interfaces) {
        spriteCache = new Cache(50000);
        modelCache = new Cache(50000);

        Buffer b = new Buffer(interfaces.read("data", null));
        instances = new Component[b.g2()];

        int parent = -1;
        while (b.offset < b.data.length) {
            int index = b.g2();
            if (index == 65535) {
                parent = b.g2();
                index = b.g2();
            }

            Component w = instances[index] = new Component();
            w.id = index;
            w.parent = parent;
            w.type = b.g1();
            w.buttonType = b.g1();
            w.contentType = b.g2();
            w.width = b.g2();
            w.height = b.g2();
            w.hoverParentIndex = b.g1();

            if (w.hoverParentIndex != 0)
                w.hoverParentIndex = (w.hoverParentIndex - 1 << 8) + b.g1();
            else
                w.hoverParentIndex = -1;

            int comparatorCount = b.g1();
            if (comparatorCount > 0) {
                w.scriptCompareType = new int[comparatorCount];
                w.scriptCompareValue = new int[comparatorCount];
                for (int n = 0; n < comparatorCount; n++) {
                    w.scriptCompareType[n] = b.g1();
                    w.scriptCompareValue[n] = b.g2();
                }
            }

            int scriptCount = b.g1();
            if (scriptCount > 0) {
                w.script = new int[scriptCount][];
                for (int script = 0; script < scriptCount; script++) {
                    int opcodeCount = b.g2();
                    w.script[script] = new int[opcodeCount];
                    for (int opcode = 0; opcode < opcodeCount; opcode++)
                        w.script[script][opcode] = b.g2();
                }
            }

            if (w.type == TYPE_PARENT) {
                w.scrollHeight = b.g2();
                w.hidden = b.g1() == 1;
                int n = b.g1();
                w.children = new int[n];
                w.childX = new int[n];
                w.childY = new int[n];
                for (int m = 0; m < n; m++) {
                    w.children[m] = b.g2();
                    w.childX[m] = b.g2s();
                    w.childY[m] = b.g2s();
                }
            }
            if (w.type == TYPE_UNUSED) {
                w.unusedInt = b.g2();
                w.unusedBoolean = b.g1() == 1;
            }
            if (w.type == TYPE_INVENTORY) {
                w.inventoryIndices = new int[w.width * w.height];
                w.inventoryAmount = new int[w.width * w.height];

                w.inventoryDummy = b.g1() == 1;
                w.inventoryHasOptions = b.g1() == 1;
                w.inventoryIsUsable = b.g1() == 1;
                w.inventoryMarginX = b.g1();
                w.inventoryMarginY = b.g1();
                w.inventoryOffsetX = new int[20];
                w.inventoryOffsetY = new int[20];
                w.inventorySprite = new Sprite[20];

                for (int n = 0; n < 20; n++) {
                    if (b.g1() == 1) {
                        w.inventoryOffsetX[n] = b.g2s();
                        w.inventoryOffsetY[n] = b.g2s();
                        String s = b.gString();
                        if (media != null && s.length() > 0) {
                            int j = s.lastIndexOf(",");
                            w.inventorySprite[n] = getSprite(media, Integer.parseInt(s.substring(j + 1)), s.substring(0, j));
                        }
                    }
                }

                w.inventoryOptions = new String[5];
                for (int n = 0; n < 5; n++) {
                    w.inventoryOptions[n] = b.gString();
                    if (w.inventoryOptions[n].length() == 0)
                        w.inventoryOptions[n] = null;
                }
            }
            if (w.type == TYPE_RECT)
                w.fill = b.g1() == 1;
            if (w.type == TYPE_TEXT || w.type == TYPE_UNUSED) {
                w.center = b.g1() == 1;
                int font = b.g1();
                if (fonts != null)
                    w.font = fonts[font];
                w.shadow = b.g1() == 1;
            }
            if (w.type == TYPE_TEXT) {
                w.text = b.gString();
                w.activeText = b.gString();
            }
            if (w.type == TYPE_UNUSED || w.type == TYPE_RECT || w.type == TYPE_TEXT)
                w.color = b.g4();
            if (w.type == TYPE_RECT || w.type == TYPE_TEXT) {
                w.colorEnabled = b.g4();
                w.hoverColor = b.g4();
            }
            if (w.type == TYPE_SPRITE) {
                String s = b.gString();
                if (media != null && s.length() > 0) {
                    int j = s.lastIndexOf(",");
                    w.image = getSprite(media, Integer.parseInt(s.substring(j + 1)), s.substring(0, j));
                }

                s = b.gString();
                if (media != null && s.length() > 0) {
                    int j = s.lastIndexOf(",");
                    w.activeImage = getSprite(media, Integer.parseInt(s.substring(j + 1)), s.substring(0, j));
                }
            }
            if (w.type == TYPE_MODEL) {
                index = b.g1();
                if (index != 0)
                    w.modelDisabled = getModel((index - 1 << 8) + b.g1());
                index = b.g1();
                if (index != 0)
                    w.modelEnabled = getModel((index - 1 << 8) + b.g1());
                index = b.g1();
                if (index != 0)
                    w.seqId = (index - 1 << 8) + b.g1();
                else
                    w.seqId = -1;
                index = b.g1();
                if (index != 0)
                    w.activeSeqId = (index - 1 << 8) + b.g1();
                else
                    w.activeSeqId = -1;
                w.modelZoom = b.g2();
                w.modelEyePitch = b.g2();
                w.modelYaw = b.g2();
            }
            if (w.type == TYPE_INVENTORY_TEXT) {
                w.inventoryIndices = new int[w.width * w.height];
                w.inventoryAmount = new int[w.width * w.height];
                w.center = b.g1() == 1;
                int font = b.g1();
                if (fonts != null)
                    w.font = fonts[font];
                w.shadow = b.g1() == 1;
                w.color = b.g4();
                w.inventoryMarginX = b.g2s();
                w.inventoryMarginY = b.g2s();
                w.inventoryHasOptions = b.g1() == 1;
                w.inventoryOptions = new String[5];
                for (int n = 0; n < 5; n++) {
                    w.inventoryOptions[n] = b.gString();
                    if (w.inventoryOptions[n].length() == 0)
                        w.inventoryOptions[n] = null;
                }
            }
            if (w.buttonType == TARGET_BUTTON || w.type == TYPE_INVENTORY) {
                w.optionCircumfix = b.gString();
                w.optionSuffix = b.gString();
                w.optionFlags = b.g2();
            }
            if (w.buttonType == BUTTON || w.buttonType == TOGGLE_BUTTON || w.buttonType == SELECT_BUTTON || w.buttonType == PAUSE_BUTTON) {
                w.option = b.gString();
                if (w.option.length() == 0) {
                    if (w.buttonType == BUTTON)
                        w.option = "Ok";
                    if (w.buttonType == TOGGLE_BUTTON)
                        w.option = "Select";
                    if (w.buttonType == SELECT_BUTTON)
                        w.option = "Select";
                    if (w.buttonType == PAUSE_BUTTON)
                        w.option = "Continue";
                }
            }
        }
        spriteCache = null;
        modelCache = null;
    }

    public Model getModel(int primaryFrame, int secondaryFrame, boolean enabled) {
        Model m = modelDisabled;
        if (enabled) {
            m = modelEnabled;
        }

        if (m == null) {
            return null;
        }

        if (primaryFrame == -1 && secondaryFrame == -1 && m.unmodifiedTriangleColor == null) {
            return m;
        }

        m = new Model(m, true, true, false);
        if (primaryFrame != -1 || secondaryFrame != -1) {
            m.applyGroups();
        }
        if (primaryFrame != -1) {
            m.applyFrame(primaryFrame);
        }
        if (secondaryFrame != -1) {
            m.applyFrame(secondaryFrame);
        }
        m.applyLighting(64, 768, -50, -10, -50, true);
        return m;
    }

    public static Sprite getSprite(FileArchive media, int index, String name) {
        long uid = (StringUtils.genHash(name) << 8) + (long) index;
        Sprite s = (Sprite) spriteCache.get(uid);
        if (s != null)
            return s;
        try {
            s = new Sprite(media, name, index);
            spriteCache.put(uid, s);
        } catch (Exception _ex) {
            return null;
        }
        return s;
    }

    public static Model getModel(int index) {
        Model m = (Model) modelCache.get(index);
        if (m != null) {
            return m;
        }

        m = new Model(index);
        modelCache.put(index, m);
        return m;
    }

    public Component() {
    }

    public static Component[] instances;
    public int[] inventoryIndices;
    public int[] inventoryAmount;
    public int seqFrame;
    public int seqCycle;
    public int id;
    public int parent;
    public int type;
    public int buttonType;
    public int contentType;
    public int width;
    public int height;
    public int x;
    public int y;
    public int[][] script;
    public int[] scriptCompareType;
    public int[] scriptCompareValue;
    public int hoverParentIndex;
    public int scrollHeight;
    public int scrollY;
    public boolean hidden;
    public int[] children;
    public int[] childX;
    public int[] childY;
    public int unusedInt;
    public boolean unusedBoolean;
    public boolean inventoryDummy;
    public boolean inventoryHasOptions;
    public boolean inventoryIsUsable;
    public int inventoryMarginX;
    public int inventoryMarginY;
    public Sprite[] inventorySprite;
    public int[] inventoryOffsetX;
    public int[] inventoryOffsetY;
    public String[] inventoryOptions;
    public boolean fill;
    public boolean center;
    public boolean shadow;
    public Font font;
    public String text;
    public String activeText;
    public int color;
    public int colorEnabled;
    public int hoverColor;
    public Sprite image;
    public Sprite activeImage;
    public Model modelDisabled;
    public Model modelEnabled;
    public int seqId;
    public int activeSeqId;
    public int modelZoom;
    public int modelEyePitch;
    public int modelYaw;
    public String optionCircumfix;
    public String optionSuffix;
    public int optionFlags;
    public String option;
    public static Cache spriteCache;
    public static Cache modelCache;
}
