package com.runescape.cache;

import com.runescape.graphics.Sprite;
import com.runescape.graphics.Draw2D;
import com.runescape.graphics.Draw3D;
import com.runescape.util.Buffer;
import com.runescape.util.Cache;

public class ObjType {

    public static void method167(FileArchive fileArchive) {
        aClass38_Sub2_Sub3_139 = new Buffer(363, fileArchive.read("obj.dat", null));
        Buffer class38_sub2_sub3 = new Buffer(363, fileArchive.read("obj.idx", null));
        anInt137 = class38_sub2_sub3.method448();
        anIntArray138 = new int[anInt137];
        int i = 2;
        for (int j = 0; j < anInt137; j++) {
            anIntArray138[j] = i;
            i += class38_sub2_sub3.method448();
        }

        objTypes = new ObjType[10];
        for (int k = 0; k < 10; k++)
            objTypes[k] = new ObjType();

    }

    public static void method168(boolean flag) {
        cache1 = null;
        cache2 = null;
        anIntArray138 = null;
        objTypes = null;
        aClass38_Sub2_Sub3_139 = null;
        if (!flag)
            anInt133 = -296;
    }

    public static ObjType method169(int i) {
        for (int j = 0; j < 10; j++)
            if (objTypes[j].anInt143 == i)
                return objTypes[j];

        anInt141 = (anInt141 + 1) % 10;
        ObjType objType = objTypes[anInt141];
        aClass38_Sub2_Sub3_139.offset = anIntArray138[i];
        objType.anInt143 = i;
        objType.method170();
        objType.method171(false, aClass38_Sub2_Sub3_139);
        if (objType.anInt177 != -1)
            objType.method172(-856);
        if (!aBoolean142 && objType.aBoolean159) {
            objType.aString145 = "Members Object";
            objType.aByteArray146 = "Login to a members' server to use this object.".getBytes();
            objType.aStringArray160 = null;
            objType.aStringArray161 = null;
        }
        return objType;
    }

    public void method170() {
        anInt144 = 0;
        aString145 = null;
        aByteArray146 = null;
        anIntArray147 = null;
        anIntArray148 = null;
        anInt149 = 2000;
        anInt150 = 0;
        anInt151 = 0;
        anInt152 = 0;
        anInt153 = 0;
        anInt154 = 0;
        aBoolean155 = false;
        anInt156 = -1;
        aBoolean157 = false;
        anInt158 = 1;
        aBoolean159 = false;
        aStringArray160 = null;
        aStringArray161 = null;
        anInt162 = -1;
        anInt163 = -1;
        aByte164 = 0;
        anInt165 = -1;
        anInt166 = -1;
        aByte167 = 0;
        anInt168 = -1;
        anInt169 = -1;
        anInt170 = -1;
        anInt171 = -1;
        anInt172 = -1;
        anInt173 = -1;
        anIntArray174 = null;
        anIntArray175 = null;
        anInt176 = -1;
        anInt177 = -1;
    }

    public void method171(boolean flag, Buffer class38_sub2_sub3) {
        if (flag)
            throw new NullPointerException();
        do {
            int i = class38_sub2_sub3.method446();
            if (i == 0)
                return;
            if (i == 1)
                anInt144 = class38_sub2_sub3.method448();
            else if (i == 2)
                aString145 = class38_sub2_sub3.method453();
            else if (i == 3)
                aByteArray146 = class38_sub2_sub3.method454((byte) 31);
            else if (i == 4)
                anInt149 = class38_sub2_sub3.method448();
            else if (i == 5)
                anInt150 = class38_sub2_sub3.method448();
            else if (i == 6)
                anInt151 = class38_sub2_sub3.method448();
            else if (i == 7) {
                anInt153 = class38_sub2_sub3.method448();
                if (anInt153 > 32767)
                    anInt153 -= 0x10000;
            } else if (i == 8) {
                anInt154 = class38_sub2_sub3.method448();
                if (anInt154 > 32767)
                    anInt154 -= 0x10000;
            } else if (i == 9)
                aBoolean155 = true;
            else if (i == 10)
                anInt156 = class38_sub2_sub3.method448();
            else if (i == 11)
                aBoolean157 = true;
            else if (i == 12)
                anInt158 = class38_sub2_sub3.method451();
            else if (i == 16)
                aBoolean159 = true;
            else if (i == 23) {
                anInt162 = class38_sub2_sub3.method448();
                aByte164 = class38_sub2_sub3.method447();
            } else if (i == 24)
                anInt163 = class38_sub2_sub3.method448();
            else if (i == 25) {
                anInt165 = class38_sub2_sub3.method448();
                aByte167 = class38_sub2_sub3.method447();
            } else if (i == 26)
                anInt166 = class38_sub2_sub3.method448();
            else if (i >= 30 && i < 35) {
                if (aStringArray160 == null)
                    aStringArray160 = new String[5];
                aStringArray160[i - 30] = class38_sub2_sub3.method453();
                if (aStringArray160[i - 30].equalsIgnoreCase("hidden"))
                    aStringArray160[i - 30] = null;
            } else if (i >= 35 && i < 40) {
                if (aStringArray161 == null)
                    aStringArray161 = new String[5];
                aStringArray161[i - 35] = class38_sub2_sub3.method453();
            } else if (i == 40) {
                int j = class38_sub2_sub3.method446();
                anIntArray147 = new int[j];
                anIntArray148 = new int[j];
                for (int k = 0; k < j; k++) {
                    anIntArray147[k] = class38_sub2_sub3.method448();
                    anIntArray148[k] = class38_sub2_sub3.method448();
                }

            } else if (i == 78)
                anInt168 = class38_sub2_sub3.method448();
            else if (i == 79)
                anInt169 = class38_sub2_sub3.method448();
            else if (i == 90)
                anInt170 = class38_sub2_sub3.method448();
            else if (i == 91)
                anInt172 = class38_sub2_sub3.method448();
            else if (i == 92)
                anInt171 = class38_sub2_sub3.method448();
            else if (i == 93)
                anInt173 = class38_sub2_sub3.method448();
            else if (i == 95)
                anInt152 = class38_sub2_sub3.method448();
            else if (i == 97)
                anInt176 = class38_sub2_sub3.method448();
            else if (i == 98)
                anInt177 = class38_sub2_sub3.method448();
            else if (i >= 100 && i < 110) {
                if (anIntArray174 == null) {
                    anIntArray174 = new int[10];
                    anIntArray175 = new int[10];
                }
                anIntArray174[i - 100] = class38_sub2_sub3.method448();
                anIntArray175[i - 100] = class38_sub2_sub3.method448();
            }
        } while (true);
    }

    public void method172(int i) {
        ObjType objType = method169(anInt177);
        anInt144 = objType.anInt144;
        anInt149 = objType.anInt149;
        anInt150 = objType.anInt150;
        while (i >= 0)
            aBoolean134 = !aBoolean134;
        anInt151 = objType.anInt151;
        anInt152 = objType.anInt152;
        anInt153 = objType.anInt153;
        anInt154 = objType.anInt154;
        anIntArray147 = objType.anIntArray147;
        anIntArray148 = objType.anIntArray148;
        ObjType objType_1 = method169(anInt176);
        aString145 = objType_1.aString145;
        aBoolean159 = objType_1.aBoolean159;
        anInt158 = objType_1.anInt158;
        String s = "a";
        char c = objType_1.aString145.charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
            s = "an";
        aByteArray146 = ("Swap this note at any bank for " + s + " " + objType_1.aString145 + ".").getBytes();
        aBoolean157 = true;
    }

    public Model method173(int i) {
        if (anIntArray174 != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++)
                if (i >= anIntArray175[k] && anIntArray175[k] != 0)
                    j = anIntArray174[k];

            if (j != -1)
                return method169(j).method173(1);
        }
        Model class38_sub2_sub1 = (Model) cache1.get(anInt143);
        if (class38_sub2_sub1 != null)
            return class38_sub2_sub1;
        class38_sub2_sub1 = new Model(false, anInt144);
        if (anIntArray147 != null) {
            for (int l = 0; l < anIntArray147.length; l++)
                class38_sub2_sub1.recolor(anIntArray147[l], anIntArray148[l]);

        }
        class38_sub2_sub1.applyLighting(64, 768, -50, -10, -50, true);
        class38_sub2_sub1.aBoolean1256 = true;
        cache1.method342(6, anInt143, class38_sub2_sub1);
        return class38_sub2_sub1;
    }

    public static Sprite method174(int i, int j, int k) {
        Sprite class38_sub2_sub2_sub2 = (Sprite) cache2.get(i);
        if (class38_sub2_sub2_sub2 != null && class38_sub2_sub2_sub2.anInt1471 != k
                && class38_sub2_sub2_sub2.anInt1471 != -1) {
            class38_sub2_sub2_sub2.unlink();
            class38_sub2_sub2_sub2 = null;
        }
        if (class38_sub2_sub2_sub2 != null)
            return class38_sub2_sub2_sub2;
        ObjType objType = method169(i);
        if (objType.anIntArray174 == null)
            k = -1;
        if (k > 1) {
            int l = -1;
            for (int j1 = 0; j1 < 10; j1++)
                if (k >= objType.anIntArray175[j1] && objType.anIntArray175[j1] != 0)
                    l = objType.anIntArray174[j1];

            if (l != -1)
                objType = method169(l);
        }
        class38_sub2_sub2_sub2 = new Sprite(32, 32);
        int i1 = Draw3D.anInt1442;
        int k1 = Draw3D.anInt1443;
        int[] ai = Draw3D.anIntArray1448;
        int[] ai1 = Draw2D.anIntArray1308;
        int l1 = Draw2D.anInt1309;
        int i2 = Draw2D.anInt1310;
        int j2 = Draw2D.anInt1313;
        int k2 = Draw2D.anInt1314;
        int l2 = Draw2D.anInt1311;
        int i3 = Draw2D.anInt1312;
        Draw3D.aBoolean1440 = false;
        Draw2D.init(32, class38_sub2_sub2_sub2.anIntArray1465, -657, 32);
        Draw2D.method380(0, 0, 0, (byte) 93, 32, 32);
        Draw3D.method385(anInt135);
        Model class38_sub2_sub1 = objType.method173(1);
        int j3 = Draw3D.anIntArray1446[objType.anInt150] * objType.anInt149 >> 16;
        int k3 = Draw3D.anIntArray1447[objType.anInt150] * objType.anInt149 >> 16;
        class38_sub2_sub1.method370(0, objType.anInt151, objType.anInt152, objType.anInt150, objType.anInt153,
                j3 + class38_sub2_sub1.anInt1247 / 2 + objType.anInt154, k3 + objType.anInt154);
        if (j != 24638)
            throw new NullPointerException();
        for (int j4 = 31; j4 >= 0; j4--) {
            for (int l3 = 31; l3 >= 0; l3--)
                if (class38_sub2_sub2_sub2.anIntArray1465[j4 + l3 * 32] == 0)
                    if (j4 > 0 && class38_sub2_sub2_sub2.anIntArray1465[(j4 - 1) + l3 * 32] > 1)
                        class38_sub2_sub2_sub2.anIntArray1465[j4 + l3 * 32] = 1;
                    else if (l3 > 0 && class38_sub2_sub2_sub2.anIntArray1465[j4 + (l3 - 1) * 32] > 1)
                        class38_sub2_sub2_sub2.anIntArray1465[j4 + l3 * 32] = 1;
                    else if (j4 < 31 && class38_sub2_sub2_sub2.anIntArray1465[j4 + 1 + l3 * 32] > 1)
                        class38_sub2_sub2_sub2.anIntArray1465[j4 + l3 * 32] = 1;
                    else if (l3 < 31 && class38_sub2_sub2_sub2.anIntArray1465[j4 + (l3 + 1) * 32] > 1)
                        class38_sub2_sub2_sub2.anIntArray1465[j4 + l3 * 32] = 1;

        }

        for (int k4 = 31; k4 >= 0; k4--) {
            for (int i4 = 31; i4 >= 0; i4--)
                if (class38_sub2_sub2_sub2.anIntArray1465[k4 + i4 * 32] == 0 && k4 > 0 && i4 > 0
                        && class38_sub2_sub2_sub2.anIntArray1465[(k4 - 1) + (i4 - 1) * 32] > 0)
                    class38_sub2_sub2_sub2.anIntArray1465[k4 + i4 * 32] = 0x302020;

        }

        if (objType.anInt177 != -1) {
            Sprite class38_sub2_sub2_sub2_1 = method174(objType.anInt176, 24638, 10);
            int l4 = class38_sub2_sub2_sub2_1.anInt1470;
            int i5 = class38_sub2_sub2_sub2_1.anInt1471;
            class38_sub2_sub2_sub2_1.anInt1470 = 32;
            class38_sub2_sub2_sub2_1.anInt1471 = 32;
            class38_sub2_sub2_sub2_1.method407(22, 5, 22, 17713, 5);
            class38_sub2_sub2_sub2_1.anInt1470 = l4;
            class38_sub2_sub2_sub2_1.anInt1471 = i5;
        }
        cache2.method342(6, i, class38_sub2_sub2_sub2);
        Draw2D.init(l1, ai1, -657, i2);
        Draw2D.method378(i3, l2, k2, 789, j2);
        Draw3D.anInt1442 = i1;
        Draw3D.anInt1443 = k1;
        Draw3D.anIntArray1448 = ai;
        Draw3D.aBoolean1440 = true;
        if (objType.aBoolean157)
            class38_sub2_sub2_sub2.anInt1470 = 33;
        else
            class38_sub2_sub2_sub2.anInt1470 = 32;
        class38_sub2_sub2_sub2.anInt1471 = k;
        return class38_sub2_sub2_sub2;
    }

    public Model method175(byte byte0, int i) {
        int j = anInt162;
        if (byte0 != 6)
            throw new NullPointerException();
        if (i == 1)
            j = anInt165;
        if (j == -1)
            return null;
        int k = anInt163;
        int l = anInt168;
        if (i == 1) {
            k = anInt166;
            l = anInt169;
        }
        Model class38_sub2_sub1 = new Model(false, j);
        if (k != -1)
            if (l != -1) {
                Model class38_sub2_sub1_1 = new Model(false, k);
                Model class38_sub2_sub1_3 = new Model(false, l);
                Model[] aclass38_sub2_sub1_1 = {
                        class38_sub2_sub1, class38_sub2_sub1_1, class38_sub2_sub1_3
                };
                class38_sub2_sub1 = new Model(0, aclass38_sub2_sub1_1, 3);
            } else {
                Model class38_sub2_sub1_2 = new Model(false, k);
                Model[] aclass38_sub2_sub1 = {
                        class38_sub2_sub1, class38_sub2_sub1_2
                };
                class38_sub2_sub1 = new Model(0, aclass38_sub2_sub1, 2);
            }
        if (i == 0 && aByte164 != 0)
            class38_sub2_sub1.method363(aByte164, 0, -122, 0);
        if (i == 1 && aByte167 != 0)
            class38_sub2_sub1.method363(aByte167, 0, -122, 0);
        if (anIntArray147 != null) {
            for (int i1 = 0; i1 < anIntArray147.length; i1++)
                class38_sub2_sub1.recolor(anIntArray147[i1], anIntArray148[i1]);

        }
        return class38_sub2_sub1;
    }

    public Model method176(int i, int j) {
        int k = anInt170;
        if (i != anInt136)
            anInt135 = 205;
        if (j == 1)
            k = anInt172;
        if (k == -1)
            return null;
        int l = anInt171;
        if (j == 1)
            l = anInt173;
        Model class38_sub2_sub1 = new Model(false, k);
        if (l != -1) {
            Model class38_sub2_sub1_1 = new Model(false, l);
            Model[] aclass38_sub2_sub1 = {
                    class38_sub2_sub1, class38_sub2_sub1_1
            };
            class38_sub2_sub1 = new Model(0, aclass38_sub2_sub1, 2);
        }
        if (anIntArray147 != null) {
            for (int i1 = 0; i1 < anIntArray147.length; i1++)
                class38_sub2_sub1.recolor(anIntArray147[i1], anIntArray148[i1]);

        }
        return class38_sub2_sub1;
    }

    public ObjType() {
        aBoolean134 = false;
        anInt136 = -22246;
        anInt143 = -1;
    }

    public static int anInt133;
    public boolean aBoolean134;
    public static int anInt135 = -192;
    public int anInt136;
    public static int anInt137;
    public static int[] anIntArray138;
    public static Buffer aClass38_Sub2_Sub3_139;
    public static ObjType[] objTypes;
    public static int anInt141;
    public static boolean aBoolean142 = true;
    public int anInt143;
    public int anInt144;
    public String aString145;
    public byte[] aByteArray146;
    public int[] anIntArray147;
    public int[] anIntArray148;
    public int anInt149;
    public int anInt150;
    public int anInt151;
    public int anInt152;
    public int anInt153;
    public int anInt154;
    public boolean aBoolean155;
    public int anInt156;
    public boolean aBoolean157;
    public int anInt158;
    public boolean aBoolean159;
    public String[] aStringArray160;
    public String[] aStringArray161;
    public int anInt162;
    public int anInt163;
    public byte aByte164;
    public int anInt165;
    public int anInt166;
    public byte aByte167;
    public int anInt168;
    public int anInt169;
    public int anInt170;
    public int anInt171;
    public int anInt172;
    public int anInt173;
    public int[] anIntArray174;
    public int[] anIntArray175;
    public int anInt176;
    public int anInt177;
    public static Cache cache1 = new Cache((byte) 0, 50);
    public static Cache cache2 = new Cache((byte) 0, 200);

}
