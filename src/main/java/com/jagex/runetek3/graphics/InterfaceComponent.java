package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.formats.Model;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Cache;
import com.jagex.runetek3.util.StringUtils;

public class InterfaceComponent {

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

    public static void load(FileArchive media, IndexedFont[] fonts, FileArchive interfaces) {
        spriteCache = new Cache(50000);
        modelCache = new Cache(50000);

        Buffer b = new Buffer(interfaces.read("data", null));
        instances = new InterfaceComponent[b.readWord()];

        int parent = -1;
        while (b.offset < b.data.length) {
            int index = b.readWord();
            if (index == 65535) {
                parent = b.readWord();
                index = b.readWord();
            }
            
            InterfaceComponent w = instances[index] = new InterfaceComponent();
            w.id = index;
            w.parent = parent;
            w.type = b.readByte();
            w.buttonType = b.readByte();
            w.contentType = b.readWord();
            w.width = b.readWord();
            w.height = b.readWord();
            w.hoverParentIndex = b.readByte();
            
            if (w.hoverParentIndex != 0)
                w.hoverParentIndex = (w.hoverParentIndex - 1 << 8) + b.readByte();
            else
                w.hoverParentIndex = -1;
            
            int comparatorCount = b.readByte();
            if (comparatorCount > 0) {
                w.scriptCompareType = new int[comparatorCount];
                w.scriptCompareValue = new int[comparatorCount];
                for (int n = 0; n < comparatorCount; n++) {
                    w.scriptCompareType[n] = b.readByte();
                    w.scriptCompareValue[n] = b.readWord();
                }
            }
            
            int scriptCount = b.readByte();
            if (scriptCount > 0) {
                w.script = new int[scriptCount][];
                for (int script = 0; script < scriptCount; script++) {
                    int opcodeCount = b.readWord();
                    w.script[script] = new int[opcodeCount];
                    for (int opcode = 0; opcode < opcodeCount; opcode++)
                        w.script[script][opcode] = b.readWord();
                }
            }

            if (w.type == TYPE_PARENT) {
                w.scrollHeight = b.readWord();
                w.hidden = b.readByte() == 1;
                int n = b.readByte();
                w.children = new int[n];
                w.childX = new int[n];
                w.childY = new int[n];
                for (int m = 0; m < n; m++) {
                    w.children[m] = b.readWord();
                    w.childX[m] = b.readWordSigned();
                    w.childY[m] = b.readWordSigned();
                }
            }
            if (w.type == TYPE_UNUSED) {
                w.unusedInt = b.readWord();
                w.unusedBoolean = b.readByte() == 1;
            }
            if (w.type == TYPE_INVENTORY) {
                w.inventoryIndices = new int[w.width * w.height];
                w.inventoryAmount = new int[w.width * w.height];

                w.inventoryDummy = b.readByte() == 1;
                w.inventoryHasOptions = b.readByte() == 1;
                w.inventoryIsUsable = b.readByte() == 1;
                w.inventoryMarginX = b.readByte();
                w.inventoryMarginY = b.readByte();
                w.inventoryOffsetX = new int[20];
                w.inventoryOffsetY = new int[20];
                w.inventorySprite = new Sprite[20];

                for (int n = 0; n < 20; n++) {
                    if (b.readByte() == 1) {
                        w.inventoryOffsetX[n] = b.readWordSigned();
                        w.inventoryOffsetY[n] = b.readWordSigned();
                        String s = b.readString();
                        if (media != null && s.length() > 0) {
                            int j = s.lastIndexOf(",");
                            w.inventorySprite[n] = getSprite(media, Integer.parseInt(s.substring(j + 1)), s.substring(0, j));
                        }
                    }
                }

                w.inventoryOptions = new String[5];
                for (int i4 = 0; i4 < 5; i4++) {
                    w.inventoryOptions[i4] = b.readString();
                    if (w.inventoryOptions[i4].length() == 0)
                        w.inventoryOptions[i4] = null;
                }
            }
            if (w.type == TYPE_RECT)
                w.fill = b.readByte() == 1;
            if (w.type == TYPE_TEXT || w.type == TYPE_UNUSED) {
                w.center = b.readByte() == 1;
                int font = b.readByte();
                if (fonts != null)
                    w.font = fonts[font];
                w.shadow = b.readByte() == 1;
            }
            if (w.type == TYPE_TEXT) {
                w.text = b.readString();
                w.activeText = b.readString();
            }
            if (w.type == TYPE_UNUSED || w.type == TYPE_RECT || w.type == TYPE_TEXT)
                w.color = b.readDWord();
            if (w.type == TYPE_RECT || w.type == TYPE_TEXT) {
                w.colorEnabled = b.readDWord();
                w.hoverColor = b.readDWord();
            }
            if (w.type == TYPE_SPRITE) {
                String s = b.readString();
                if (media != null && s.length() > 0) {
                    int j = s.lastIndexOf(",");
                    w.image = getSprite(media, Integer.parseInt(s.substring(j + 1)), s.substring(0, j));
                }
                s = b.readString();
                if (media != null && s.length() > 0) {
                    int j = s.lastIndexOf(",");
                    w.activeImage = getSprite(media, Integer.parseInt(s.substring(j + 1)), s.substring(0, j));
                }
            }
            if (w.type == TYPE_MODEL) {
                index = b.readByte();
                if (index != 0)
                    w.modelDisabled = getModel((index - 1 << 8) + b.readByte());
                index = b.readByte();
                if (index != 0)
                    w.modelEnabled = getModel((index - 1 << 8) + b.readByte());
                index = b.readByte();
                if (index != 0)
                    w.seqId = (index - 1 << 8) + b.readByte();
                else
                    w.seqId = -1;
                index = b.readByte();
                if (index != 0)
                    w.activeSeqId = (index - 1 << 8) + b.readByte();
                else
                    w.activeSeqId = -1;
                w.modelZoom = b.readWord();
                w.modelEyePitch = b.readWord();
                w.modelYaw = b.readWord();
            }
            if (w.type == TYPE_INVENTORY_TEXT) {
                w.inventoryIndices = new int[w.width * w.height];
                w.inventoryAmount = new int[w.width * w.height];
                w.center = b.readByte() == 1;
                int font = b.readByte();
                if (fonts != null)
                    w.font = fonts[font];
                w.shadow = b.readByte() == 1;
                w.color = b.readDWord();
                w.inventoryMarginX = b.readWordSigned();
                w.inventoryMarginY = b.readWordSigned();
                w.inventoryHasOptions = b.readByte() == 1;
                w.inventoryOptions = new String[5];
                for (int n = 0; n < 5; n++) {
                    w.inventoryOptions[n] = b.readString();
                    if (w.inventoryOptions[n].length() == 0)
                        w.inventoryOptions[n] = null;
                }
            }
            if (w.buttonType == TARGET_BUTTON || w.type == TYPE_INVENTORY) {
                w.optionCircumfix = b.readString();
                w.optionSuffix = b.readString();
                w.optionFlags = b.readWord();
            }
            if (w.buttonType == BUTTON || w.buttonType == TOGGLE_BUTTON || w.buttonType == SELECT_BUTTON || w.buttonType == PAUSE_BUTTON) {
                w.option = b.readString();
                if (w.option.length() == 0) {
                    if (w.buttonType == 1)
                        w.option = "Ok";
                    if (w.buttonType == 4)
                        w.option = "Select";
                    if (w.buttonType == 5)
                        w.option = "Select";
                    if (w.buttonType == 6)
                        w.option = "Continue";
                }
            }
        }
        spriteCache = null;
        modelCache = null;
    }

    public Model getModel(int primaryFrame, int secondaryFrame, boolean enabled) {
        Model m = modelDisabled;
        if (enabled)
            m = modelEnabled;
        if (m == null)
            return null;
        if (primaryFrame == -1 && secondaryFrame == -1 && m.unmodifiedTriangleColor == null)
            return m;

        m = new Model(m, true, true, false);
        if (primaryFrame != -1 || secondaryFrame != -1)
            m.applyGroups();
        if (primaryFrame != -1)
            m.applyFrame(primaryFrame);
        if (secondaryFrame != -1)
            m.applyFrame(secondaryFrame);
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

    public InterfaceComponent() {
    }

    public static InterfaceComponent[] instances;
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
    public IndexedFont font;
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
