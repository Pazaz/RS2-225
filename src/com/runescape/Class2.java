package com.runescape;

public class Class2 {

    public static void method146(Class39 class39) {
        aClass38_Sub2_Sub3_79 = new Class38_Sub2_Sub3(363, class39.method474("npc.dat", null, (byte) 2));
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, class39.method474("npc.idx", null, (byte) 2));
        anInt77 = class38_sub2_sub3.method448();
        anIntArray78 = new int[anInt77];
        int i = 2;
        for (int j = 0; j < anInt77; j++) {
            anIntArray78[j] = i;
            i += class38_sub2_sub3.method448();
        }

        aClass2Array80 = new Class2[20];
        for (int k = 0; k < 20; k++)
            aClass2Array80[k] = new Class2();

    }

    public static void method147(boolean flag) {
        aClass34_104 = null;
        anIntArray78 = null;
        aClass2Array80 = null;
        if (!flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        aClass38_Sub2_Sub3_79 = null;
    }

    public static Class2 method148(int i) {
        for (int j = 0; j < 20; j++)
            if (aClass2Array80[j].aLong82 == (long) i)
                return aClass2Array80[j];

        anInt81 = (anInt81 + 1) % 20;
        Class2 class2 = aClass2Array80[anInt81] = new Class2();
        aClass38_Sub2_Sub3_79.anInt1329 = anIntArray78[i];
        class2.aLong82 = i;
        class2.method149(false, aClass38_Sub2_Sub3_79);
        return class2;
    }

    public void method149(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        if (flag)
            throw new NullPointerException();
        do {
            int i = class38_sub2_sub3.method446();
            if (i == 0)
                return;
            if (i == 1) {
                int j = class38_sub2_sub3.method446();
                anIntArray86 = new int[j];
                for (int i1 = 0; i1 < j; i1++)
                    anIntArray86[i1] = class38_sub2_sub3.method448();

            } else if (i == 2)
                aString83 = class38_sub2_sub3.method453();
            else if (i == 3)
                aByteArray84 = class38_sub2_sub3.method454((byte) 31);
            else if (i == 12)
                aByte85 = class38_sub2_sub3.method447();
            else if (i == 13)
                anInt88 = class38_sub2_sub3.method448();
            else if (i == 14)
                anInt89 = class38_sub2_sub3.method448();
            else if (i == 16)
                aBoolean93 = true;
            else if (i == 17) {
                anInt89 = class38_sub2_sub3.method448();
                anInt90 = class38_sub2_sub3.method448();
                anInt91 = class38_sub2_sub3.method448();
                anInt92 = class38_sub2_sub3.method448();
            } else if (i >= 30 && i < 40) {
                if (aStringArray96 == null)
                    aStringArray96 = new String[5];
                aStringArray96[i - 30] = class38_sub2_sub3.method453();
                if (aStringArray96[i - 30].equalsIgnoreCase("hidden"))
                    aStringArray96[i - 30] = null;
            } else if (i == 40) {
                int k = class38_sub2_sub3.method446();
                anIntArray94 = new int[k];
                anIntArray95 = new int[k];
                for (int j1 = 0; j1 < k; j1++) {
                    anIntArray94[j1] = class38_sub2_sub3.method448();
                    anIntArray95[j1] = class38_sub2_sub3.method448();
                }

            } else if (i == 60) {
                int l = class38_sub2_sub3.method446();
                anIntArray87 = new int[l];
                for (int k1 = 0; k1 < l; k1++)
                    anIntArray87[k1] = class38_sub2_sub3.method448();

            } else if (i == 90)
                anInt97 = class38_sub2_sub3.method448();
            else if (i == 91)
                anInt98 = class38_sub2_sub3.method448();
            else if (i == 92)
                anInt99 = class38_sub2_sub3.method448();
            else if (i == 93)
                aBoolean100 = false;
            else if (i == 95)
                anInt101 = class38_sub2_sub3.method448();
            else if (i == 97)
                anInt102 = class38_sub2_sub3.method448();
            else if (i == 98)
                anInt103 = class38_sub2_sub3.method448();
        } while (true);
    }

    public Class38_Sub2_Sub1 method150(int i, int j, int[] ai) {
        Class38_Sub2_Sub1 class38_sub2_sub1 = null;
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = (Class38_Sub2_Sub1) aClass34_104.method341(aLong82);
        if (class38_sub2_sub1_1 == null) {
            Class38_Sub2_Sub1[] aclass38_sub2_sub1 = new Class38_Sub2_Sub1[anIntArray86.length];
            for (int k = 0; k < anIntArray86.length; k++)
                aclass38_sub2_sub1[k] = new Class38_Sub2_Sub1(false, anIntArray86[k]);

            if (aclass38_sub2_sub1.length == 1)
                class38_sub2_sub1_1 = aclass38_sub2_sub1[0];
            else
                class38_sub2_sub1_1 = new Class38_Sub2_Sub1(0, aclass38_sub2_sub1, aclass38_sub2_sub1.length);
            if (anIntArray94 != null) {
                for (int l = 0; l < anIntArray94.length; l++)
                    class38_sub2_sub1_1.method364(anIntArray94[l], anIntArray95[l]);

            }
            class38_sub2_sub1_1.method357(4);
            class38_sub2_sub1_1.method367(64, 850, -30, -50, -30, true);
            aClass34_104.method342(6, aLong82, class38_sub2_sub1_1);
        }
        class38_sub2_sub1 = new Class38_Sub2_Sub1(0, class38_sub2_sub1_1, !aBoolean93);
        if (i != -1 && j != -1)
            class38_sub2_sub1.method359(j, 3, i, ai);
        else if (i != -1)
            class38_sub2_sub1.method358(-16599, i);
        if (anInt102 != 128 || anInt103 != 128)
            class38_sub2_sub1.method366(anInt102, 2, anInt103, anInt102);
        class38_sub2_sub1.method354(2992);
        class38_sub2_sub1.anIntArrayArray1255 = null;
        class38_sub2_sub1.anIntArrayArray1254 = null;
        if (aByte85 == 1)
            class38_sub2_sub1.aBoolean1256 = true;
        return class38_sub2_sub1;
    }

    public Class38_Sub2_Sub1 method151(boolean flag) {
        if (flag)
            throw new NullPointerException();
        if (anIntArray87 == null)
            return null;
        Class38_Sub2_Sub1[] aclass38_sub2_sub1 = new Class38_Sub2_Sub1[anIntArray87.length];
        for (int i = 0; i < anIntArray87.length; i++)
            aclass38_sub2_sub1[i] = new Class38_Sub2_Sub1(false, anIntArray87[i]);

        Class38_Sub2_Sub1 class38_sub2_sub1;
        if (aclass38_sub2_sub1.length == 1)
            class38_sub2_sub1 = aclass38_sub2_sub1[0];
        else
            class38_sub2_sub1 = new Class38_Sub2_Sub1(0, aclass38_sub2_sub1, aclass38_sub2_sub1.length);
        if (anIntArray94 != null) {
            for (int j = 0; j < anIntArray94.length; j++)
                class38_sub2_sub1.method364(anIntArray94[j], anIntArray95[j]);

        }
        return class38_sub2_sub1;
    }

    public Class2() {
        aBoolean76 = false;
        aLong82 = -1L;
        aByte85 = 1;
        anInt88 = -1;
        anInt89 = -1;
        anInt90 = -1;
        anInt91 = -1;
        anInt92 = -1;
        aBoolean93 = false;
        anInt97 = -1;
        anInt98 = -1;
        anInt99 = -1;
        aBoolean100 = true;
        anInt101 = -1;
        anInt102 = 128;
        anInt103 = 128;
    }

    public boolean aBoolean76;
    public static int anInt77;
    public static int[] anIntArray78;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_79;
    public static Class2[] aClass2Array80;
    public static int anInt81;
    public long aLong82;
    public String aString83;
    public byte[] aByteArray84;
    public byte aByte85;
    public int[] anIntArray86;
    public int[] anIntArray87;
    public int anInt88;
    public int anInt89;
    public int anInt90;
    public int anInt91;
    public int anInt92;
    public boolean aBoolean93;
    public int[] anIntArray94;
    public int[] anIntArray95;
    public String[] aStringArray96;
    public int anInt97;
    public int anInt98;
    public int anInt99;
    public boolean aBoolean100;
    public int anInt101;
    public int anInt102;
    public int anInt103;
    public static Class34 aClass34_104 = new Class34((byte) 0, 30);

}
