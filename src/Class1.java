// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class1 {

    public static void method140(Class39 class39) {
        aClass38_Sub2_Sub3_38 = new Class38_Sub2_Sub3(363, class39.method474("loc.dat", null, (byte) 2));
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, class39.method474("loc.idx", null, (byte) 2));
        anInt36 = class38_sub2_sub3.method448();
        anIntArray37 = new int[anInt36];
        int i = 2;
        for (int j = 0; j < anInt36; j++) {
            anIntArray37[j] = i;
            i += class38_sub2_sub3.method448();
        }

        aClass1Array39 = new Class1[10];
        for (int k = 0; k < 10; k++)
            aClass1Array39[k] = new Class1();

    }

    public static void method141(boolean flag) {
        aClass34_74 = null;
        aClass34_75 = null;
        if (!flag) {
            return;
        } else {
            anIntArray37 = null;
            aClass1Array39 = null;
            aClass38_Sub2_Sub3_38 = null;
            return;
        }
    }

    public static Class1 method142(int i) {
        for (int j = 0; j < 10; j++)
            if (aClass1Array39[j].anInt41 == i)
                return aClass1Array39[j];

        anInt40 = (anInt40 + 1) % 10;
        Class1 class1 = aClass1Array39[anInt40];
        aClass38_Sub2_Sub3_38.anInt1329 = anIntArray37[i];
        class1.anInt41 = i;
        class1.method143();
        class1.method144(false, aClass38_Sub2_Sub3_38);
        return class1;
    }

    public void method143() {
        anIntArray42 = null;
        anIntArray43 = null;
        aString44 = null;
        aByteArray45 = null;
        anIntArray46 = null;
        anIntArray47 = null;
        anInt48 = 1;
        anInt49 = 1;
        aBoolean50 = true;
        aBoolean51 = true;
        aBoolean52 = false;
        aBoolean53 = false;
        aBoolean54 = false;
        aBoolean55 = false;
        anInt56 = -1;
        anInt57 = 16;
        aByte58 = 0;
        aByte59 = 0;
        aStringArray60 = null;
        aBoolean61 = false;
        anInt62 = -1;
        anInt63 = -1;
        aBoolean64 = false;
        aBoolean65 = true;
        anInt66 = 128;
        anInt67 = 128;
        anInt68 = 128;
        anInt72 = 0;
        anInt69 = 0;
        anInt70 = 0;
        anInt71 = 0;
        aBoolean73 = false;
    }

    public void method144(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        if (flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        int j = -1;
        do {
            int k = class38_sub2_sub3.method446();
            if (k == 0)
                break;
            if (k == 1) {
                int l = class38_sub2_sub3.method446();
                anIntArray43 = new int[l];
                anIntArray42 = new int[l];
                for (int j1 = 0; j1 < l; j1++) {
                    anIntArray42[j1] = class38_sub2_sub3.method448();
                    anIntArray43[j1] = class38_sub2_sub3.method446();
                }

            } else if (k == 2)
                aString44 = class38_sub2_sub3.method453();
            else if (k == 3)
                aByteArray45 = class38_sub2_sub3.method454((byte) 31);
            else if (k == 14)
                anInt48 = class38_sub2_sub3.method446();
            else if (k == 15)
                anInt49 = class38_sub2_sub3.method446();
            else if (k == 17)
                aBoolean50 = false;
            else if (k == 18)
                aBoolean51 = false;
            else if (k == 19) {
                j = class38_sub2_sub3.method446();
                if (j == 1)
                    aBoolean52 = true;
            } else if (k == 21)
                aBoolean53 = true;
            else if (k == 22)
                aBoolean54 = true;
            else if (k == 23)
                aBoolean55 = true;
            else if (k == 24) {
                anInt56 = class38_sub2_sub3.method448();
                if (anInt56 == 65535)
                    anInt56 = -1;
            } else if (k == 25)
                aBoolean61 = true;
            else if (k == 28)
                anInt57 = class38_sub2_sub3.method446();
            else if (k == 29)
                aByte58 = class38_sub2_sub3.method447();
            else if (k == 39)
                aByte59 = class38_sub2_sub3.method447();
            else if (k >= 30 && k < 39) {
                if (aStringArray60 == null)
                    aStringArray60 = new String[5];
                aStringArray60[k - 30] = class38_sub2_sub3.method453();
                if (aStringArray60[k - 30].equalsIgnoreCase("hidden"))
                    aStringArray60[k - 30] = null;
            } else if (k == 40) {
                int i1 = class38_sub2_sub3.method446();
                anIntArray46 = new int[i1];
                anIntArray47 = new int[i1];
                for (int k1 = 0; k1 < i1; k1++) {
                    anIntArray46[k1] = class38_sub2_sub3.method448();
                    anIntArray47[k1] = class38_sub2_sub3.method448();
                }

            } else if (k == 60)
                anInt62 = class38_sub2_sub3.method448();
            else if (k == 62)
                aBoolean64 = true;
            else if (k == 64)
                aBoolean65 = false;
            else if (k == 65)
                anInt66 = class38_sub2_sub3.method448();
            else if (k == 66)
                anInt67 = class38_sub2_sub3.method448();
            else if (k == 67)
                anInt68 = class38_sub2_sub3.method448();
            else if (k == 68)
                anInt63 = class38_sub2_sub3.method448();
            else if (k == 69)
                anInt72 = class38_sub2_sub3.method446();
            else if (k == 70)
                anInt69 = class38_sub2_sub3.method449();
            else if (k == 71)
                anInt70 = class38_sub2_sub3.method449();
            else if (k == 72)
                anInt71 = class38_sub2_sub3.method449();
            else if (k == 73)
                aBoolean73 = true;
        } while (true);
        if (anIntArray43 == null)
            anIntArray43 = new int[0];
        if (j == -1) {
            aBoolean52 = anIntArray43.length > 0 && anIntArray43[0] == 10;
            if (aStringArray60 != null)
                aBoolean52 = true;
        }
    }

    public Class38_Sub2_Sub1 method145(int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = -1;
        for (int i2 = 0; i2 < anIntArray43.length; i2++) {
            if (anIntArray43[i2] != i)
                continue;
            l1 = i2;
            break;
        }

        if (l1 == -1)
            return null;
        long l2 = (long) ((anInt41 << 6) + (l1 << 3) + j) + ((long) (k1 + 1) << 32);
        if (aBoolean35)
            l2 = 0L;
        Class38_Sub2_Sub1 class38_sub2_sub1 = (Class38_Sub2_Sub1) aClass34_75.method341(l2);
        if (class38_sub2_sub1 != null) {
            if (aBoolean35)
                return class38_sub2_sub1;
            if (aBoolean53 || aBoolean54)
                class38_sub2_sub1 = new Class38_Sub2_Sub1(class38_sub2_sub1, (byte) -31, aBoolean53, aBoolean54);
            if (aBoolean53) {
                int j2 = (k + l + i1 + j1) / 4;
                for (int i3 = 0; i3 < class38_sub2_sub1.anInt1222; i3++) {
                    int j3 = class38_sub2_sub1.anIntArray1223[i3];
                    int k3 = class38_sub2_sub1.anIntArray1225[i3];
                    int l3 = k + ((l - k) * (j3 + 64)) / 128;
                    int i4 = j1 + ((i1 - j1) * (j3 + 64)) / 128;
                    int j4 = l3 + ((i4 - l3) * (k3 + 64)) / 128;
                    class38_sub2_sub1.anIntArray1224[i3] += j4 - j2;
                }

                class38_sub2_sub1.method355(anInt34);
            }
            return class38_sub2_sub1;
        }
        if (l1 >= anIntArray42.length)
            return null;
        int k2 = anIntArray42[l1];
        if (k2 == -1)
            return null;
        boolean flag = aBoolean64 ^ (j > 3);
        if (flag)
            k2 += 0x10000;
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = (Class38_Sub2_Sub1) aClass34_74.method341(k2);
        if (class38_sub2_sub1_1 == null) {
            class38_sub2_sub1_1 = new Class38_Sub2_Sub1(false, k2 & 0xffff);
            if (flag)
                class38_sub2_sub1_1.method365(-725);
            aClass34_74.method342(6, k2, class38_sub2_sub1_1);
        }
        boolean flag1;
        flag1 = anInt66 != 128 || anInt67 != 128 || anInt68 != 128;
        boolean flag2;
        flag2 = anInt69 != 0 || anInt70 != 0 || anInt71 != 0;
        Class38_Sub2_Sub1 class38_sub2_sub1_2 = new Class38_Sub2_Sub1(class38_sub2_sub1_1, anIntArray46 == null,
                !aBoolean61, anInt33, j == 0 && k1 == -1 && !flag1 && !flag2);
        if (k1 != -1) {
            class38_sub2_sub1_2.method357(4);
            class38_sub2_sub1_2.method358(-16599, k1);
            class38_sub2_sub1_2.anIntArrayArray1255 = null;
            class38_sub2_sub1_2.anIntArrayArray1254 = null;
        }
        while (j-- > 0)
            class38_sub2_sub1_2.method361(0);
        if (anIntArray46 != null) {
            for (int k4 = 0; k4 < anIntArray46.length; k4++)
                class38_sub2_sub1_2.method364(anIntArray46[k4], anIntArray47[k4]);

        }
        if (flag1)
            class38_sub2_sub1_2.method366(anInt68, 2, anInt67, anInt66);
        if (flag2)
            class38_sub2_sub1_2.method363(anInt70, anInt69, -122, anInt71);
        class38_sub2_sub1_2.method367(64 + aByte58, 768 + aByte59 * 5, -50, -10, -50, !aBoolean54);
        if (aBoolean50)
            class38_sub2_sub1_2.anInt1251 = class38_sub2_sub1_2.anInt1247;
        aClass34_75.method342(6, l2, class38_sub2_sub1_2);
        if (aBoolean53 || aBoolean54)
            class38_sub2_sub1_2 = new Class38_Sub2_Sub1(class38_sub2_sub1_2, (byte) -31, aBoolean53, aBoolean54);
        if (aBoolean53) {
            int l4 = (k + l + i1 + j1) / 4;
            for (int i5 = 0; i5 < class38_sub2_sub1_2.anInt1222; i5++) {
                int j5 = class38_sub2_sub1_2.anIntArray1223[i5];
                int k5 = class38_sub2_sub1_2.anIntArray1225[i5];
                int l5 = k + ((l - k) * (j5 + 64)) / 128;
                int i6 = j1 + ((i1 - j1) * (j5 + 64)) / 128;
                int j6 = l5 + ((i6 - l5) * (k5 + 64)) / 128;
                class38_sub2_sub1_2.anIntArray1224[i5] += j6 - l4;
            }

            class38_sub2_sub1_2.method355(anInt34);
        }
        return class38_sub2_sub1_2;
    }

    public Class1() {
        anInt41 = -1;
    }

    public static int anInt33;
    public static int anInt34;
    public static boolean aBoolean35;
    public static int anInt36;
    public static int[] anIntArray37;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_38;
    public static Class1[] aClass1Array39;
    public static int anInt40;
    public int anInt41;
    public int[] anIntArray42;
    public int[] anIntArray43;
    public String aString44;
    public byte[] aByteArray45;
    public int[] anIntArray46;
    public int[] anIntArray47;
    public int anInt48;
    public int anInt49;
    public boolean aBoolean50;
    public boolean aBoolean51;
    public boolean aBoolean52;
    public boolean aBoolean53;
    public boolean aBoolean54;
    public boolean aBoolean55;
    public int anInt56;
    public int anInt57;
    public byte aByte58;
    public byte aByte59;
    public String[] aStringArray60;
    public boolean aBoolean61;
    public int anInt62;
    public int anInt63;
    public boolean aBoolean64;
    public boolean aBoolean65;
    public int anInt66;
    public int anInt67;
    public int anInt68;
    public int anInt69;
    public int anInt70;
    public int anInt71;
    public int anInt72;
    public boolean aBoolean73;
    public static Class34 aClass34_74 = new Class34((byte) 0, 500);
    public static Class34 aClass34_75 = new Class34((byte) 0, 30);

}
