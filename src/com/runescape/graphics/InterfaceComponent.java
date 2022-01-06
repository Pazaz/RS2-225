package com.runescape.graphics;

import com.runescape.cache.FileArchive;
import com.runescape.cache.Model;
import com.runescape.util.Buffer;
import com.runescape.util.Cache;
import com.runescape.util.StringUtils;

public class InterfaceComponent {

    public static void method218(FileArchive fileArchive, IndexedFont[] aclass38_sub2_sub2_sub4, int i,
                                 FileArchive fileArchive_1) {
        i = 17 / i;
        cache1 = new Cache(50000);
        cache2 = new Cache(50000);
        Buffer class38_sub2_sub3 = new Buffer(fileArchive_1.read("data", null));
        int j = -1;
        int k = class38_sub2_sub3.readWord();
        interfaceComponentArray = new InterfaceComponent[k];
        while (class38_sub2_sub3.offset < class38_sub2_sub3.data.length) {
            int l = class38_sub2_sub3.readWord();
            if (l == 65535) {
                j = class38_sub2_sub3.readWord();
                l = class38_sub2_sub3.readWord();
            }
            InterfaceComponent interfaceComponent = interfaceComponentArray[l] = new InterfaceComponent();
            interfaceComponent.anInt269 = l;
            interfaceComponent.anInt270 = j;
            interfaceComponent.anInt271 = class38_sub2_sub3.readByte();
            interfaceComponent.anInt272 = class38_sub2_sub3.readByte();
            interfaceComponent.anInt273 = class38_sub2_sub3.readWord();
            interfaceComponent.anInt274 = class38_sub2_sub3.readWord();
            interfaceComponent.anInt275 = class38_sub2_sub3.readWord();
            interfaceComponent.anInt281 = class38_sub2_sub3.readByte();
            if (interfaceComponent.anInt281 != 0)
                interfaceComponent.anInt281 = (interfaceComponent.anInt281 - 1 << 8) + class38_sub2_sub3.readByte();
            else
                interfaceComponent.anInt281 = -1;
            int j1 = class38_sub2_sub3.readByte();
            if (j1 > 0) {
                interfaceComponent.anIntArray279 = new int[j1];
                interfaceComponent.anIntArray280 = new int[j1];
                for (int k1 = 0; k1 < j1; k1++) {
                    interfaceComponent.anIntArray279[k1] = class38_sub2_sub3.readByte();
                    interfaceComponent.anIntArray280[k1] = class38_sub2_sub3.readWord();
                }

            }
            int l1 = class38_sub2_sub3.readByte();
            if (l1 > 0) {
                interfaceComponent.anIntArrayArray278 = new int[l1][];
                for (int i2 = 0; i2 < l1; i2++) {
                    int j3 = class38_sub2_sub3.readWord();
                    interfaceComponent.anIntArrayArray278[i2] = new int[j3];
                    for (int i5 = 0; i5 < j3; i5++)
                        interfaceComponent.anIntArrayArray278[i2][i5] = class38_sub2_sub3.readWord();

                }

            }
            if (interfaceComponent.anInt271 == 0) {
                interfaceComponent.anInt282 = class38_sub2_sub3.readWord();
                interfaceComponent.aBoolean284 = class38_sub2_sub3.readByte() == 1;
                int j2 = class38_sub2_sub3.readByte();
                interfaceComponent.anIntArray285 = new int[j2];
                interfaceComponent.anIntArray286 = new int[j2];
                interfaceComponent.anIntArray287 = new int[j2];
                for (int k3 = 0; k3 < j2; k3++) {
                    interfaceComponent.anIntArray285[k3] = class38_sub2_sub3.readWord();
                    interfaceComponent.anIntArray286[k3] = class38_sub2_sub3.readWordSigned();
                    interfaceComponent.anIntArray287[k3] = class38_sub2_sub3.readWordSigned();
                }

            }
            if (interfaceComponent.anInt271 == 1) {
                interfaceComponent.anInt288 = class38_sub2_sub3.readWord();
                interfaceComponent.aBoolean289 = class38_sub2_sub3.readByte() == 1;
            }
            if (interfaceComponent.anInt271 == 2) {
                interfaceComponent.anIntArray265 = new int[interfaceComponent.anInt274 * interfaceComponent.anInt275];
                interfaceComponent.anIntArray266 = new int[interfaceComponent.anInt274 * interfaceComponent.anInt275];
                interfaceComponent.aBoolean290 = class38_sub2_sub3.readByte() == 1;
                interfaceComponent.aBoolean291 = class38_sub2_sub3.readByte() == 1;
                interfaceComponent.aBoolean292 = class38_sub2_sub3.readByte() == 1;
                interfaceComponent.anInt293 = class38_sub2_sub3.readByte();
                interfaceComponent.anInt294 = class38_sub2_sub3.readByte();
                interfaceComponent.anIntArray296 = new int[20];
                interfaceComponent.anIntArray297 = new int[20];
                interfaceComponent.aClass38_Sub2_Sub2_Sub2Array295 = new Sprite[20];
                for (int k2 = 0; k2 < 20; k2++) {
                    int l3 = class38_sub2_sub3.readByte();
                    if (l3 == 1) {
                        interfaceComponent.anIntArray296[k2] = class38_sub2_sub3.readWordSigned();
                        interfaceComponent.anIntArray297[k2] = class38_sub2_sub3.readWordSigned();
                        String s1 = class38_sub2_sub3.readString();
                        if (fileArchive != null && s1.length() > 0) {
                            int j5 = s1.lastIndexOf(",");
                            interfaceComponent.aClass38_Sub2_Sub2_Sub2Array295[k2] = method220(fileArchive,
                                    Integer.parseInt(s1.substring(j5 + 1)), s1.substring(0, j5), -36068);
                        }
                    }
                }

                interfaceComponent.aStringArray298 = new String[5];
                for (int i4 = 0; i4 < 5; i4++) {
                    interfaceComponent.aStringArray298[i4] = class38_sub2_sub3.readString();
                    if (interfaceComponent.aStringArray298[i4].length() == 0)
                        interfaceComponent.aStringArray298[i4] = null;
                }

            }
            if (interfaceComponent.anInt271 == 3)
                interfaceComponent.aBoolean299 = class38_sub2_sub3.readByte() == 1;
            if (interfaceComponent.anInt271 == 4 || interfaceComponent.anInt271 == 1) {
                interfaceComponent.aBoolean300 = class38_sub2_sub3.readByte() == 1;
                int l2 = class38_sub2_sub3.readByte();
                if (aclass38_sub2_sub2_sub4 != null)
                    interfaceComponent.indexedFont = aclass38_sub2_sub2_sub4[l2];
                interfaceComponent.aBoolean301 = class38_sub2_sub3.readByte() == 1;
            }
            if (interfaceComponent.anInt271 == 4) {
                interfaceComponent.aString303 = class38_sub2_sub3.readString();
                interfaceComponent.aString304 = class38_sub2_sub3.readString();
            }
            if (interfaceComponent.anInt271 == 1 || interfaceComponent.anInt271 == 3 || interfaceComponent.anInt271 == 4)
                interfaceComponent.anInt305 = class38_sub2_sub3.readDWord();
            if (interfaceComponent.anInt271 == 3 || interfaceComponent.anInt271 == 4) {
                interfaceComponent.anInt306 = class38_sub2_sub3.readDWord();
                interfaceComponent.anInt307 = class38_sub2_sub3.readDWord();
            }
            if (interfaceComponent.anInt271 == 5) {
                String s = class38_sub2_sub3.readString();
                if (fileArchive != null && s.length() > 0) {
                    int j4 = s.lastIndexOf(",");
                    interfaceComponent.aClass38_Sub2_Sub2_Sub2_308 = method220(fileArchive, Integer.parseInt(s.substring(j4 + 1)),
                            s.substring(0, j4), -36068);
                }
                s = class38_sub2_sub3.readString();
                if (fileArchive != null && s.length() > 0) {
                    int k4 = s.lastIndexOf(",");
                    interfaceComponent.aClass38_Sub2_Sub2_Sub2_309 = method220(fileArchive, Integer.parseInt(s.substring(k4 + 1)),
                            s.substring(0, k4), -36068);
                }
            }
            if (interfaceComponent.anInt271 == 6) {
                int i1 = class38_sub2_sub3.readByte();
                if (i1 != 0)
                    interfaceComponent.aClass38_Sub2_Sub1_310 = method221(4, (i1 - 1 << 8) + class38_sub2_sub3.readByte());
                i1 = class38_sub2_sub3.readByte();
                if (i1 != 0)
                    interfaceComponent.aClass38_Sub2_Sub1_311 = method221(4, (i1 - 1 << 8) + class38_sub2_sub3.readByte());
                i1 = class38_sub2_sub3.readByte();
                if (i1 != 0)
                    interfaceComponent.anInt312 = (i1 - 1 << 8) + class38_sub2_sub3.readByte();
                else
                    interfaceComponent.anInt312 = -1;
                i1 = class38_sub2_sub3.readByte();
                if (i1 != 0)
                    interfaceComponent.anInt313 = (i1 - 1 << 8) + class38_sub2_sub3.readByte();
                else
                    interfaceComponent.anInt313 = -1;
                interfaceComponent.anInt314 = class38_sub2_sub3.readWord();
                interfaceComponent.anInt315 = class38_sub2_sub3.readWord();
                interfaceComponent.anInt316 = class38_sub2_sub3.readWord();
            }
            if (interfaceComponent.anInt271 == 7) {
                interfaceComponent.anIntArray265 = new int[interfaceComponent.anInt274 * interfaceComponent.anInt275];
                interfaceComponent.anIntArray266 = new int[interfaceComponent.anInt274 * interfaceComponent.anInt275];
                interfaceComponent.aBoolean300 = class38_sub2_sub3.readByte() == 1;
                int i3 = class38_sub2_sub3.readByte();
                if (aclass38_sub2_sub2_sub4 != null)
                    interfaceComponent.indexedFont = aclass38_sub2_sub2_sub4[i3];
                interfaceComponent.aBoolean301 = class38_sub2_sub3.readByte() == 1;
                interfaceComponent.anInt305 = class38_sub2_sub3.readDWord();
                interfaceComponent.anInt293 = class38_sub2_sub3.readWordSigned();
                interfaceComponent.anInt294 = class38_sub2_sub3.readWordSigned();
                interfaceComponent.aBoolean291 = class38_sub2_sub3.readByte() == 1;
                interfaceComponent.aStringArray298 = new String[5];
                for (int l4 = 0; l4 < 5; l4++) {
                    interfaceComponent.aStringArray298[l4] = class38_sub2_sub3.readString();
                    if (interfaceComponent.aStringArray298[l4].length() == 0)
                        interfaceComponent.aStringArray298[l4] = null;
                }

            }
            if (interfaceComponent.anInt272 == 2 || interfaceComponent.anInt271 == 2) {
                interfaceComponent.aString317 = class38_sub2_sub3.readString();
                interfaceComponent.aString318 = class38_sub2_sub3.readString();
                interfaceComponent.anInt319 = class38_sub2_sub3.readWord();
            }
            if (interfaceComponent.anInt272 == 1 || interfaceComponent.anInt272 == 4 || interfaceComponent.anInt272 == 5 || interfaceComponent.anInt272 == 6) {
                interfaceComponent.aString320 = class38_sub2_sub3.readString();
                if (interfaceComponent.aString320.length() == 0) {
                    if (interfaceComponent.anInt272 == 1)
                        interfaceComponent.aString320 = "Ok";
                    if (interfaceComponent.anInt272 == 4)
                        interfaceComponent.aString320 = "Select";
                    if (interfaceComponent.anInt272 == 5)
                        interfaceComponent.aString320 = "Select";
                    if (interfaceComponent.anInt272 == 6)
                        interfaceComponent.aString320 = "Continue";
                }
            }
        }
        cache1 = null;
        cache2 = null;
    }

    public Model method219(int i, int j, boolean flag) {
        Model class38_sub2_sub1 = aClass38_Sub2_Sub1_310;
        if (flag)
            class38_sub2_sub1 = aClass38_Sub2_Sub1_311;
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

    public static Sprite method220(FileArchive fileArchive, int i, String s, int j) {
        long l = (StringUtils.genHash(s) << 8) + (long) i;
        Sprite class38_sub2_sub2_sub2 = (Sprite) cache1.get(l);
        if (j != -36068)
            throw new NullPointerException();
        if (class38_sub2_sub2_sub2 != null)
            return class38_sub2_sub2_sub2;
        try {
            class38_sub2_sub2_sub2 = new Sprite(fileArchive, s, i);
            cache1.put(l, class38_sub2_sub2_sub2);
        } catch (Exception _ex) {
            return null;
        }
        return class38_sub2_sub2_sub2;
    }

    public static Model method221(int i, int j) {
        Model class38_sub2_sub1 = (Model) cache2.get(j);
        if (i != 4)
            aBoolean263 = !aBoolean263;
        if (class38_sub2_sub1 != null) {
            return class38_sub2_sub1;
        } else {
            Model class38_sub2_sub1_1 = new Model(j);
            cache2.put(j, class38_sub2_sub1_1);
            return class38_sub2_sub1_1;
        }
    }

    public InterfaceComponent() {
    }

    public static int anInt262;
    public static boolean aBoolean263;
    public static InterfaceComponent[] interfaceComponentArray;
    public int[] anIntArray265;
    public int[] anIntArray266;
    public int anInt267;
    public int anInt268;
    public int anInt269;
    public int anInt270;
    public int anInt271;
    public int anInt272;
    public int anInt273;
    public int anInt274;
    public int anInt275;
    public int anInt276;
    public int anInt277;
    public int[][] anIntArrayArray278;
    public int[] anIntArray279;
    public int[] anIntArray280;
    public int anInt281;
    public int anInt282;
    public int anInt283;
    public boolean aBoolean284;
    public int[] anIntArray285;
    public int[] anIntArray286;
    public int[] anIntArray287;
    public int anInt288;
    public boolean aBoolean289;
    public boolean aBoolean290;
    public boolean aBoolean291;
    public boolean aBoolean292;
    public int anInt293;
    public int anInt294;
    public Sprite[] aClass38_Sub2_Sub2_Sub2Array295;
    public int[] anIntArray296;
    public int[] anIntArray297;
    public String[] aStringArray298;
    public boolean aBoolean299;
    public boolean aBoolean300;
    public boolean aBoolean301;
    public IndexedFont indexedFont;
    public String aString303;
    public String aString304;
    public int anInt305;
    public int anInt306;
    public int anInt307;
    public Sprite aClass38_Sub2_Sub2_Sub2_308;
    public Sprite aClass38_Sub2_Sub2_Sub2_309;
    public Model aClass38_Sub2_Sub1_310;
    public Model aClass38_Sub2_Sub1_311;
    public int anInt312;
    public int anInt313;
    public int anInt314;
    public int anInt315;
    public int anInt316;
    public String aString317;
    public String aString318;
    public int anInt319;
    public String aString320;
    public static Cache cache1;
    public static Cache cache2;
}
