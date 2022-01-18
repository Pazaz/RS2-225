package com.runescape.graphics;

import com.runescape.cache.FileArchive;
import com.runescape.cache.Model;
import com.runescape.util.Buffer;
import com.runescape.util.Cache;
import com.runescape.util.StringUtils;

public class InterfaceComponent {

    public static void load(FileArchive fileArchive, IndexedFont[] aclass38_sub2_sub2_sub4, int i,
                            FileArchive fileArchive_1) {
        i = 17 / i;
        cache1 = new Cache(50000);
        cache2 = new Cache(50000);
        Buffer b = new Buffer(fileArchive_1.read("data", null));
        int j = -1;
        int k = b.readWord();
        instances = new InterfaceComponent[k];
        while (b.offset < b.data.length) {
            int l = b.readWord();
            if (l == 65535) {
                j = b.readWord();
                l = b.readWord();
            }
            InterfaceComponent w = instances[l] = new InterfaceComponent();
            w.id = l;
            w.parent = j;
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
            int j1 = b.readByte();
            if (j1 > 0) {
                w.scriptCompareType = new int[j1];
                w.scriptCompareValue = new int[j1];
                for (int k1 = 0; k1 < j1; k1++) {
                    w.scriptCompareType[k1] = b.readByte();
                    w.scriptCompareValue[k1] = b.readWord();
                }
            }
            int l1 = b.readByte();
            if (l1 > 0) {
                w.script = new int[l1][];
                for (int i2 = 0; i2 < l1; i2++) {
                    int j3 = b.readWord();
                    w.script[i2] = new int[j3];
                    for (int i5 = 0; i5 < j3; i5++)
                        w.script[i2][i5] = b.readWord();
                }
            }

            if (w.type == 0) {
                w.scrollHeight = b.readWord();
                w.hidden = b.readByte() == 1;
                int j2 = b.readByte();
                w.children = new int[j2];
                w.childX = new int[j2];
                w.childY = new int[j2];
                for (int k3 = 0; k3 < j2; k3++) {
                    w.children[k3] = b.readWord();
                    w.childX[k3] = b.readWordSigned();
                    w.childY[k3] = b.readWordSigned();
                }
            }
            if (w.type == 1) {
                w.anInt288 = b.readWord();
                w.aBoolean289 = b.readByte() == 1;
            }
            if (w.type == 2) {
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

                for (int k2 = 0; k2 < 20; k2++) {
                    int l3 = b.readByte();
                    if (l3 == 1) {
                        w.inventoryOffsetX[k2] = b.readWordSigned();
                        w.inventoryOffsetY[k2] = b.readWordSigned();
                        String s1 = b.readString();
                        if (fileArchive != null && s1.length() > 0) {
                            int j5 = s1.lastIndexOf(",");
                            w.inventorySprite[k2] = getSprite(fileArchive,
                                    Integer.parseInt(s1.substring(j5 + 1)), s1.substring(0, j5), -36068);
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
            if (w.type == 3)
                w.fill = b.readByte() == 1;
            if (w.type == 4 || w.type == 1) {
                w.center = b.readByte() == 1;
                int l2 = b.readByte();
                if (aclass38_sub2_sub2_sub4 != null)
                    w.font = aclass38_sub2_sub2_sub4[l2];
                w.shadow = b.readByte() == 1;
            }
            if (w.type == 4) {
                w.text = b.readString();
                w.activeText = b.readString();
            }
            if (w.type == 1 || w.type == 3 || w.type == 4)
                w.color = b.readDWord();
            if (w.type == 3 || w.type == 4) {
                w.colorEnabled = b.readDWord();
                w.hoverColor = b.readDWord();
            }
            if (w.type == 5) {
                String s = b.readString();
                if (fileArchive != null && s.length() > 0) {
                    int j4 = s.lastIndexOf(",");
                    w.image = getSprite(fileArchive, Integer.parseInt(s.substring(j4 + 1)),
                            s.substring(0, j4), -36068);
                }
                s = b.readString();
                if (fileArchive != null && s.length() > 0) {
                    int k4 = s.lastIndexOf(",");
                    w.activeImage = getSprite(fileArchive, Integer.parseInt(s.substring(k4 + 1)),
                            s.substring(0, k4), -36068);
                }
            }
            if (w.type == 6) {
                int i1 = b.readByte();
                if (i1 != 0)
                    w.modelDisabled = getModel(4, (i1 - 1 << 8) + b.readByte());
                i1 = b.readByte();
                if (i1 != 0)
                    w.modelEnabled = getModel(4, (i1 - 1 << 8) + b.readByte());
                i1 = b.readByte();
                if (i1 != 0)
                    w.seqId = (i1 - 1 << 8) + b.readByte();
                else
                    w.seqId = -1;
                i1 = b.readByte();
                if (i1 != 0)
                    w.activeSeqId = (i1 - 1 << 8) + b.readByte();
                else
                    w.activeSeqId = -1;
                w.modelZoom = b.readWord();
                w.modelEyePitch = b.readWord();
                w.modelYaw = b.readWord();
            }
            if (w.type == 7) {
                w.inventoryIndices = new int[w.width * w.height];
                w.inventoryAmount = new int[w.width * w.height];
                w.center = b.readByte() == 1;
                int i3 = b.readByte();
                if (aclass38_sub2_sub2_sub4 != null)
                    w.font = aclass38_sub2_sub2_sub4[i3];
                w.shadow = b.readByte() == 1;
                w.color = b.readDWord();
                w.inventoryMarginX = b.readWordSigned();
                w.inventoryMarginY = b.readWordSigned();
                w.inventoryHasOptions = b.readByte() == 1;
                w.inventoryOptions = new String[5];
                for (int l4 = 0; l4 < 5; l4++) {
                    w.inventoryOptions[l4] = b.readString();
                    if (w.inventoryOptions[l4].length() == 0)
                        w.inventoryOptions[l4] = null;
                }

            }
            if (w.buttonType == 2 || w.type == 2) {
                w.optionCircumfix = b.readString();
                w.optionSuffix = b.readString();
                w.optionFlags = b.readWord();
            }
            if (w.buttonType == 1 || w.buttonType == 4 || w.buttonType == 5 || w.buttonType == 6) {
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
        cache1 = null;
        cache2 = null;
    }

    public Model getModel(int i, int j, boolean flag) {
        Model class38_sub2_sub1 = modelDisabled;
        if (flag)
            class38_sub2_sub1 = modelEnabled;
        if (class38_sub2_sub1 == null)
            return null;
        if (i == -1 && j == -1 && class38_sub2_sub1.unmodifiedTriangleColor == null)
            return class38_sub2_sub1;
        Model class38_sub2_sub1_1 = new Model(class38_sub2_sub1, true, true, false);
        if (i != -1 || j != -1)
            class38_sub2_sub1_1.applyGroups();
        if (i != -1)
            class38_sub2_sub1_1.applyFrame(i);
        if (j != -1)
            class38_sub2_sub1_1.applyFrame(j);
        class38_sub2_sub1_1.applyLighting(64, 768, -50, -10, -50, true);
        return class38_sub2_sub1_1;
    }

    public static Sprite getSprite(FileArchive fileArchive, int i, String name, int j) {
        long uid = (StringUtils.genHash(name) << 8) + (long) i;
        Sprite s = (Sprite) cache1.get(uid);
        if (s != null)
            return s;
        try {
            s = new Sprite(fileArchive, name, i);
            cache1.put(uid, s);
        } catch (Exception _ex) {
            return null;
        }
        return s;
    }

    public static Model getModel(int i, int j) {
        Model m = (Model) cache2.get(j);
        if (m != null) {
            return m;
        }

        m = new Model(j);
        cache2.put(j, m);
        return m;
    }

    public InterfaceComponent() {
    }

    public static InterfaceComponent[] instances;
    public int[] inventoryIndices;
    public int[] inventoryAmount;
    public int seqFrame;
    public int anInt268;
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
    public int anInt288;
    public boolean aBoolean289;
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
    public static Cache cache1;
    public static Cache cache2;
}
