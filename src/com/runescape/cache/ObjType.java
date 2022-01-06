package com.runescape.cache;

import com.runescape.graphics.Sprite;
import com.runescape.graphics.Draw2D;
import com.runescape.graphics.Draw3D;
import com.runescape.util.Buffer;
import com.runescape.util.Cache;

public class ObjType {

    public static void load(FileArchive fileArchive) {
        data = new Buffer(363, fileArchive.read("obj.dat", null));
        Buffer idx = new Buffer(363, fileArchive.read("obj.idx", null));
        count = idx.method448();
        offsets = new int[count];

        int off = 2;
        for (int n = 0; n < count; n++) {
            offsets[n] = off;
            off += idx.method448();
        }

        objTypes = new ObjType[10];
        for (int n = 0; n < 10; n++) {
            objTypes[n] = new ObjType();
        }
    }

    public static void unload() {
        modelCache = null;
        iconCache = null;
        offsets = null;
        objTypes = null;
        data = null;
    }

    public static ObjType get(int index) {
        for (int n = 0; n < 10; n++) {
            if (objTypes[n].index == index) {
                return objTypes[n];
            }
        }

        cacheIndex = (cacheIndex + 1) % 10;
        ObjType objType = objTypes[cacheIndex];
        data.offset = offsets[index];
        objType.index = index;
        objType.reset();
        objType.read(data);

        if (objType.certificateId != -1) {
            objType.toCertificate();
        }

        if (!isMember && objType.members) {
            objType.name = "Members Object";
            objType.description = "Login to a members' server to use this object.".getBytes();
            objType.groundOptions = null;
            objType.options = null;
        }

        return objType;
    }

    public void reset() {
        modelIndex = 0;

        name = null;
        description = null;

        oldColors = null;
        newColors = null;

        iconZoom = 2000;
        iconCameraPitch = 0;
        iconYaw = 0;
        iconRoll = 0;
        iconX = 0;
        iconY = 0;

        stackable = false;
        value = 1;
        members = false;

        groundOptions = null;
        options = null;

        maleModel0 = -1;
        maleModel1 = -1;
        maleOffsetY = 0;

        femaleModel0 = -1;
        femaleModel1 = -1;
        femaleOffsetY = 0;

        maleModel2 = -1;
        femaleModel2 = -1;

        maleHeadModelA = -1;
        maleHeadModelB = -1;
        femaleHeadModelA = -1;
        femaleHeadModelB = -1;

        stackId = null;
        stackAmount = null;
        linkedId = -1;
        certificateId = -1;
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.method446();
            if (opcode == 0) {
                return;
            } else if (opcode == 1) {
                modelIndex = buffer.method448();
            } else if (opcode == 2) {
                name = buffer.method453();
            } else if (opcode == 3) {
                description = buffer.method454((byte) 31);
            } else if (opcode == 4) {
                iconZoom = buffer.method448();
            } else if (opcode == 5) {
                iconCameraPitch = buffer.method448();
            } else if (opcode == 6) {
                iconYaw = buffer.method448();
            } else if (opcode == 7) {
                iconX = buffer.method448();

                if (iconX > 32767) {
                    iconX -= 0x10000;
                }
            } else if (opcode == 8) {
                iconY = buffer.method448();

                if (iconY > 32767) {
                    iconY -= 0x10000;
                }
            } else if (opcode == 9) {
            } else if (opcode == 10) {
                buffer.method448();
            } else if (opcode == 11) {
                stackable = true;
            } else if (opcode == 12) {
                value = buffer.method451();
            } else if (opcode == 16) {
                members = true;
            } else if (opcode == 23) {
                maleModel0 = buffer.method448();
                maleOffsetY = buffer.method447();
            } else if (opcode == 24) {
                maleModel1 = buffer.method448();
            } else if (opcode == 25) {
                femaleModel0 = buffer.method448();
                femaleOffsetY = buffer.method447();
            } else if (opcode == 26) {
                femaleModel1 = buffer.method448();
            } else if (opcode >= 30 && opcode < 35) {
                if (groundOptions == null) {
                    groundOptions = new String[5];
                }
                
                groundOptions[opcode - 30] = buffer.method453();
                if (groundOptions[opcode - 30].equalsIgnoreCase("hidden")) {
                    groundOptions[opcode - 30] = null;
                }
            } else if (opcode >= 35 && opcode < 40) {
                if (options == null) {
                    options = new String[5];
                }
                
                options[opcode - 35] = buffer.method453();
            } else if (opcode == 40) {
                int count = buffer.method446();
                oldColors = new int[count];
                newColors = new int[count];
                
                for (int n = 0; n < count; n++) {
                    oldColors[n] = buffer.method448();
                    newColors[n] = buffer.method448();
                }
            } else if (opcode == 78) {
                maleModel2 = buffer.method448();
            } else if (opcode == 79) {
                femaleModel2 = buffer.method448();
            } else if (opcode == 90) {
                maleHeadModelA = buffer.method448();
            } else if (opcode == 91) {
                femaleHeadModelA = buffer.method448();
            } else if (opcode == 92) {
                maleHeadModelB = buffer.method448();
            } else if (opcode == 93) {
                femaleHeadModelB = buffer.method448();
            } else if (opcode == 95) {
                iconRoll = buffer.method448();
            } else if (opcode == 97) {
                linkedId = buffer.method448();
            } else if (opcode == 98) {
                certificateId = buffer.method448();
            } else if (opcode >= 100 && opcode < 110) {
                if (stackId == null) {
                    stackId = new int[10];
                    stackAmount = new int[10];
                }

                stackId[opcode - 100] = buffer.method448();
                stackAmount[opcode - 100] = buffer.method448();
            }
        } while (true);
    }

    public void toCertificate() {
        ObjType objType = get(certificateId);
        modelIndex = objType.modelIndex;
        iconZoom = objType.iconZoom;
        iconCameraPitch = objType.iconCameraPitch;
        iconYaw = objType.iconYaw;
        iconRoll = objType.iconRoll;
        iconX = objType.iconX;
        iconY = objType.iconY;
        oldColors = objType.oldColors;
        newColors = objType.newColors;
        ObjType linked = get(linkedId);
        name = linked.name;
        members = linked.members;
        value = linked.value;
        String prefix = "a";
        char vowel = linked.name.charAt(0);
        if (vowel == 'A' || vowel == 'E' || vowel == 'I' || vowel == 'O' || vowel == 'U')
            prefix = "an";
        description = ("Swap this note at any bank for " + prefix + " " + linked.name + ".").getBytes();
        stackable = true;
    }

    public Model getModel(int amount) {
        if (stackId != null && amount > 1) {
            int newId = -1;
            for (int n = 0; n < 10; n++) {
                if (amount >= stackAmount[n] && stackAmount[n] != 0) {
                    newId = stackId[n];
                }
            }

            if (newId != -1) {
                return get(newId).getModel(1);
            }
        }

        Model model = (Model) modelCache.get(index);
        if (model != null) {
            return model;
        }

        model = new Model(false, modelIndex);
        if (oldColors != null) {
            for (int n = 0; n < oldColors.length; n++) {
                model.recolor(oldColors[n], newColors[n]);
            }
        }

        model.applyLighting(64, 768, -50, -10, -50, true);
        model.pickable = true;
        modelCache.put(6, index, model);
        return model;
    }

    public static Sprite getSprite(int i, int amount) {
        Sprite sprite = (Sprite) iconCache.get(i);

        if (sprite != null && sprite.cropH != amount && sprite.cropH != -1) {
            sprite.unlink();
            sprite = null;
        }

        if (sprite != null) {
            return sprite;
        }

        ObjType info = get(i);
        if (info.stackId == null) {
            amount = -1;
        }

        if (amount > 1) {
            int l = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (amount >= info.stackAmount[j1] && info.stackAmount[j1] != 0) {
                    l = info.stackId[j1];
                }
            }

            if (l != -1) {
                info = get(l);
            }
        }

        sprite = new Sprite(32, 32);
        int centerX = Draw3D.centerX;
        int centerY = Draw3D.centerY;
        int[] offsets = Draw3D.offsets;
        int[] data = Draw2D.dest;
        int width = Draw2D.width;
        int height = Draw2D.height;
        int left = Draw2D.left;
        int right = Draw2D.right;
        int top = Draw2D.top;
        int bottom = Draw2D.bottom;

        Draw3D.jagged = false;
        Draw2D.init(32, sprite.pixels, -657, 32);
        Draw2D.fillRect(0, 0, 0, (byte) 93, 32, 32);
        Draw3D.init2D(-192);

        Model model = info.getModel(1);

        int sinPitch = Draw3D.sin[info.iconCameraPitch] * info.iconZoom >> 16;
        int cosPitch = Draw3D.cos[info.iconCameraPitch] * info.iconZoom >> 16;

        model.drawSimple(0, info.iconYaw, info.iconRoll, info.iconCameraPitch, info.iconX, sinPitch + model.minY / 2 + info.iconY, cosPitch + info.iconY);

        for (int x = 31; x >= 0; x--) {
            for (int y = 31; y >= 0; y--) {
                if (sprite.pixels[x + y * 32] == 0) {
                    if (x > 0 && sprite.pixels[(x - 1) + y * 32] > 1) {
                        sprite.pixels[x + y * 32] = 1;
                    } else if (y > 0 && sprite.pixels[x + (y - 1) * 32] > 1) {
                        sprite.pixels[x + y * 32] = 1;
                    } else if (x < 31 && sprite.pixels[x + 1 + y * 32] > 1) {
                        sprite.pixels[x + y * 32] = 1;
                    } else if (y < 31 && sprite.pixels[x + (y + 1) * 32] > 1) {
                        sprite.pixels[x + y * 32] = 1;
                    }
                }
            }
        }

        for (int x = 31; x >= 0; x--) {
            for (int y = 31; y >= 0; y--) {
                if (sprite.pixels[x + y * 32] == 0 && x > 0 && y > 0 && sprite.pixels[(x - 1) + (y - 1) * 32] > 0) {
                    sprite.pixels[x + y * 32] = 0x302020;
                }
            }
        }

        if (info.certificateId != -1) {
            Sprite originalIcon = getSprite(info.linkedId, 10);
            int w = originalIcon.cropW;
            int h = originalIcon.cropH;
            originalIcon.cropW = 32;
            originalIcon.cropH = 32;
            originalIcon.draw(22, 5, 22, 17713, 5);
            originalIcon.cropW = w;
            originalIcon.cropH = h;
        }

        iconCache.put(6, i, sprite);
        Draw2D.init(width, data, -657, height);
        Draw2D.setBounds(bottom, top, right, 789, left);
        Draw3D.centerX = centerX;
        Draw3D.centerY = centerY;
        Draw3D.offsets = offsets;
        Draw3D.jagged = true;

        if (info.stackable) {
            sprite.cropW = 33;
        } else {
            sprite.cropW = 32;
        }

        sprite.cropH = amount;
        return sprite;
    }

    public Model getWornModel(int gender) {
        int m0 = maleModel0;
        int m1 = maleModel1;
        int m2 = maleModel2;

        if (gender == 1) {
            m0 = femaleModel0;
            m1 = femaleModel1;
            m2 = femaleModel2;
        }

        if (m0 == -1) {
            return null;
        }

        Model model = new Model(false, m0);

        if (m1 != -1) {
            if (m2 != -1) {
                Model model1 = new Model(false, m1);
                Model model2 = new Model(false, m2);
                Model[] models = {
                    model, model1, model2
                };
                model = new Model(0, models, 3);
            } else {
                Model model1 = new Model(false, m1);
                Model[] models = {
                    model, model1
                };
                model = new Model(0, models, 2);
            }
        }

        if (gender == 0 && maleOffsetY != 0) {
            model.translate(maleOffsetY, 0, -122, 0);
        } else if (gender == 1 && femaleOffsetY != 0) {
            model.translate(femaleOffsetY, 0, -122, 0);
        }

        if (oldColors != null) {
            for (int i1 = 0; i1 < oldColors.length; i1++) {
                model.recolor(oldColors[i1], newColors[i1]);
            }
        }

        return model;
    }

    public Model getHeadModel(int gender) {
        int m0 = maleHeadModelA;
        int m1 = maleHeadModelB;

        if (gender == 1) {
            m0 = femaleHeadModelA;
            m1 = femaleHeadModelB;
        }

        if (m0 == -1) {
            return null;
        }
        
        Model model = new Model(false, m0);
        if (m1 != -1) {
            Model model1 = new Model(false, m1);
            Model[] models = {
                model, model1
            };
            model = new Model(0, models, 2);
        }

        if (oldColors != null) {
            for (int n = 0; n < oldColors.length; n++) {
                model.recolor(oldColors[n], newColors[n]);
            }
        }

        return model;
    }

    public ObjType() {
        index = -1;
    }

    public static int count;
    public static int[] offsets;
    public static Buffer data;
    public static ObjType[] objTypes;
    public static int cacheIndex;
    public static boolean isMember = true;

    public int index;
    public int modelIndex;

    public String name;
    public byte[] description;

    public int[] oldColors;
    public int[] newColors;

    public int iconZoom;
    public int iconCameraPitch;
    public int iconYaw;
    public int iconRoll;
    public int iconX;
    public int iconY;

    public boolean stackable;
    public int value;

    public boolean members;

    public String[] groundOptions;
    public String[] options;

    public int maleModel0;
    public int maleModel1;
    public byte maleOffsetY;

    public int femaleModel0;
    public int femaleModel1;
    public byte femaleOffsetY;

    public int maleModel2;
    public int femaleModel2;

    public int maleHeadModelA;
    public int maleHeadModelB;

    public int femaleHeadModelA;
    public int femaleHeadModelB;

    public int[] stackId;
    public int[] stackAmount;
    public int linkedId;
    public int certificateId;

    public static Cache modelCache = new Cache((byte) 0, 50);
    public static Cache iconCache = new Cache((byte) 0, 200);

}
