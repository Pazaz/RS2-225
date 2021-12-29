// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class14 {

    public static void method218(Class39 class39, Class38_Sub2_Sub2_Sub4 aclass38_sub2_sub2_sub4[], int i,
            Class39 class39_1) {
        i = 17 / i;
        aClass34_321 = new Class34((byte) 0, 50000);
        aClass34_322 = new Class34((byte) 0, 50000);
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, class39_1.method474("data", null, (byte) 2));
        int j = -1;
        int k = class38_sub2_sub3.method448();
        aClass14Array264 = new Class14[k];
        while (class38_sub2_sub3.anInt1329 < class38_sub2_sub3.aByteArray1328.length) {
            int l = class38_sub2_sub3.method448();
            if (l == 65535) {
                j = class38_sub2_sub3.method448();
                l = class38_sub2_sub3.method448();
            }
            Class14 class14 = aClass14Array264[l] = new Class14();
            class14.anInt269 = l;
            class14.anInt270 = j;
            class14.anInt271 = class38_sub2_sub3.method446();
            class14.anInt272 = class38_sub2_sub3.method446();
            class14.anInt273 = class38_sub2_sub3.method448();
            class14.anInt274 = class38_sub2_sub3.method448();
            class14.anInt275 = class38_sub2_sub3.method448();
            class14.anInt281 = class38_sub2_sub3.method446();
            if (class14.anInt281 != 0)
                class14.anInt281 = (class14.anInt281 - 1 << 8) + class38_sub2_sub3.method446();
            else
                class14.anInt281 = -1;
            int j1 = class38_sub2_sub3.method446();
            if (j1 > 0) {
                class14.anIntArray279 = new int[j1];
                class14.anIntArray280 = new int[j1];
                for (int k1 = 0; k1 < j1; k1++) {
                    class14.anIntArray279[k1] = class38_sub2_sub3.method446();
                    class14.anIntArray280[k1] = class38_sub2_sub3.method448();
                }

            }
            int l1 = class38_sub2_sub3.method446();
            if (l1 > 0) {
                class14.anIntArrayArray278 = new int[l1][];
                for (int i2 = 0; i2 < l1; i2++) {
                    int j3 = class38_sub2_sub3.method448();
                    class14.anIntArrayArray278[i2] = new int[j3];
                    for (int i5 = 0; i5 < j3; i5++)
                        class14.anIntArrayArray278[i2][i5] = class38_sub2_sub3.method448();

                }

            }
            if (class14.anInt271 == 0) {
                class14.anInt282 = class38_sub2_sub3.method448();
                class14.aBoolean284 = class38_sub2_sub3.method446() == 1;
                int j2 = class38_sub2_sub3.method446();
                class14.anIntArray285 = new int[j2];
                class14.anIntArray286 = new int[j2];
                class14.anIntArray287 = new int[j2];
                for (int k3 = 0; k3 < j2; k3++) {
                    class14.anIntArray285[k3] = class38_sub2_sub3.method448();
                    class14.anIntArray286[k3] = class38_sub2_sub3.method449();
                    class14.anIntArray287[k3] = class38_sub2_sub3.method449();
                }

            }
            if (class14.anInt271 == 1) {
                class14.anInt288 = class38_sub2_sub3.method448();
                class14.aBoolean289 = class38_sub2_sub3.method446() == 1;
            }
            if (class14.anInt271 == 2) {
                class14.anIntArray265 = new int[class14.anInt274 * class14.anInt275];
                class14.anIntArray266 = new int[class14.anInt274 * class14.anInt275];
                class14.aBoolean290 = class38_sub2_sub3.method446() == 1;
                class14.aBoolean291 = class38_sub2_sub3.method446() == 1;
                class14.aBoolean292 = class38_sub2_sub3.method446() == 1;
                class14.anInt293 = class38_sub2_sub3.method446();
                class14.anInt294 = class38_sub2_sub3.method446();
                class14.anIntArray296 = new int[20];
                class14.anIntArray297 = new int[20];
                class14.aClass38_Sub2_Sub2_Sub2Array295 = new Class38_Sub2_Sub2_Sub2[20];
                for (int k2 = 0; k2 < 20; k2++) {
                    int l3 = class38_sub2_sub3.method446();
                    if (l3 == 1) {
                        class14.anIntArray296[k2] = class38_sub2_sub3.method449();
                        class14.anIntArray297[k2] = class38_sub2_sub3.method449();
                        String s1 = class38_sub2_sub3.method453();
                        if (class39 != null && s1.length() > 0) {
                            int j5 = s1.lastIndexOf(",");
                            class14.aClass38_Sub2_Sub2_Sub2Array295[k2] = method220(class39,
                                    Integer.parseInt(s1.substring(j5 + 1)), s1.substring(0, j5), -36068);
                        }
                    }
                }

                class14.aStringArray298 = new String[5];
                for (int i4 = 0; i4 < 5; i4++) {
                    class14.aStringArray298[i4] = class38_sub2_sub3.method453();
                    if (class14.aStringArray298[i4].length() == 0)
                        class14.aStringArray298[i4] = null;
                }

            }
            if (class14.anInt271 == 3)
                class14.aBoolean299 = class38_sub2_sub3.method446() == 1;
            if (class14.anInt271 == 4 || class14.anInt271 == 1) {
                class14.aBoolean300 = class38_sub2_sub3.method446() == 1;
                int l2 = class38_sub2_sub3.method446();
                if (aclass38_sub2_sub2_sub4 != null)
                    class14.aClass38_Sub2_Sub2_Sub4_302 = aclass38_sub2_sub2_sub4[l2];
                class14.aBoolean301 = class38_sub2_sub3.method446() == 1;
            }
            if (class14.anInt271 == 4) {
                class14.aString303 = class38_sub2_sub3.method453();
                class14.aString304 = class38_sub2_sub3.method453();
            }
            if (class14.anInt271 == 1 || class14.anInt271 == 3 || class14.anInt271 == 4)
                class14.anInt305 = class38_sub2_sub3.method451();
            if (class14.anInt271 == 3 || class14.anInt271 == 4) {
                class14.anInt306 = class38_sub2_sub3.method451();
                class14.anInt307 = class38_sub2_sub3.method451();
            }
            if (class14.anInt271 == 5) {
                String s = class38_sub2_sub3.method453();
                if (class39 != null && s.length() > 0) {
                    int j4 = s.lastIndexOf(",");
                    class14.aClass38_Sub2_Sub2_Sub2_308 = method220(class39, Integer.parseInt(s.substring(j4 + 1)),
                            s.substring(0, j4), -36068);
                }
                s = class38_sub2_sub3.method453();
                if (class39 != null && s.length() > 0) {
                    int k4 = s.lastIndexOf(",");
                    class14.aClass38_Sub2_Sub2_Sub2_309 = method220(class39, Integer.parseInt(s.substring(k4 + 1)),
                            s.substring(0, k4), -36068);
                }
            }
            if (class14.anInt271 == 6) {
                int i1 = class38_sub2_sub3.method446();
                if (i1 != 0)
                    class14.aClass38_Sub2_Sub1_310 = method221(4, (i1 - 1 << 8) + class38_sub2_sub3.method446());
                i1 = class38_sub2_sub3.method446();
                if (i1 != 0)
                    class14.aClass38_Sub2_Sub1_311 = method221(4, (i1 - 1 << 8) + class38_sub2_sub3.method446());
                i1 = class38_sub2_sub3.method446();
                if (i1 != 0)
                    class14.anInt312 = (i1 - 1 << 8) + class38_sub2_sub3.method446();
                else
                    class14.anInt312 = -1;
                i1 = class38_sub2_sub3.method446();
                if (i1 != 0)
                    class14.anInt313 = (i1 - 1 << 8) + class38_sub2_sub3.method446();
                else
                    class14.anInt313 = -1;
                class14.anInt314 = class38_sub2_sub3.method448();
                class14.anInt315 = class38_sub2_sub3.method448();
                class14.anInt316 = class38_sub2_sub3.method448();
            }
            if (class14.anInt271 == 7) {
                class14.anIntArray265 = new int[class14.anInt274 * class14.anInt275];
                class14.anIntArray266 = new int[class14.anInt274 * class14.anInt275];
                class14.aBoolean300 = class38_sub2_sub3.method446() == 1;
                int i3 = class38_sub2_sub3.method446();
                if (aclass38_sub2_sub2_sub4 != null)
                    class14.aClass38_Sub2_Sub2_Sub4_302 = aclass38_sub2_sub2_sub4[i3];
                class14.aBoolean301 = class38_sub2_sub3.method446() == 1;
                class14.anInt305 = class38_sub2_sub3.method451();
                class14.anInt293 = class38_sub2_sub3.method449();
                class14.anInt294 = class38_sub2_sub3.method449();
                class14.aBoolean291 = class38_sub2_sub3.method446() == 1;
                class14.aStringArray298 = new String[5];
                for (int l4 = 0; l4 < 5; l4++) {
                    class14.aStringArray298[l4] = class38_sub2_sub3.method453();
                    if (class14.aStringArray298[l4].length() == 0)
                        class14.aStringArray298[l4] = null;
                }

            }
            if (class14.anInt272 == 2 || class14.anInt271 == 2) {
                class14.aString317 = class38_sub2_sub3.method453();
                class14.aString318 = class38_sub2_sub3.method453();
                class14.anInt319 = class38_sub2_sub3.method448();
            }
            if (class14.anInt272 == 1 || class14.anInt272 == 4 || class14.anInt272 == 5 || class14.anInt272 == 6) {
                class14.aString320 = class38_sub2_sub3.method453();
                if (class14.aString320.length() == 0) {
                    if (class14.anInt272 == 1)
                        class14.aString320 = "Ok";
                    if (class14.anInt272 == 4)
                        class14.aString320 = "Select";
                    if (class14.anInt272 == 5)
                        class14.aString320 = "Select";
                    if (class14.anInt272 == 6)
                        class14.aString320 = "Continue";
                }
            }
        }
        aClass34_321 = null;
        aClass34_322 = null;
    }

    public Class38_Sub2_Sub1 method219(int i, int j, boolean flag) {
        Class38_Sub2_Sub1 class38_sub2_sub1 = aClass38_Sub2_Sub1_310;
        if (flag)
            class38_sub2_sub1 = aClass38_Sub2_Sub1_311;
        if (class38_sub2_sub1 == null)
            return null;
        if (i == -1 && j == -1 && class38_sub2_sub1.anIntArray1236 == null)
            return class38_sub2_sub1;
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = new Class38_Sub2_Sub1(class38_sub2_sub1, true, true, anInt262, false);
        if (i != -1 || j != -1)
            class38_sub2_sub1_1.method357(4);
        if (i != -1)
            class38_sub2_sub1_1.method358(-16599, i);
        if (j != -1)
            class38_sub2_sub1_1.method358(-16599, j);
        class38_sub2_sub1_1.method367(64, 768, -50, -10, -50, true);
        return class38_sub2_sub1_1;
    }

    public static Class38_Sub2_Sub2_Sub2 method220(Class39 class39, int i, String s, int j) {
        long l = (Class40.method477(0, s) << 8) + (long) i;
        Class38_Sub2_Sub2_Sub2 class38_sub2_sub2_sub2 = (Class38_Sub2_Sub2_Sub2) aClass34_321.method341(l);
        if (j != -36068)
            throw new NullPointerException();
        if (class38_sub2_sub2_sub2 != null)
            return class38_sub2_sub2_sub2;
        try {
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(class39, s, i);
            aClass34_321.method342(6, l, class38_sub2_sub2_sub2);
        } catch (Exception _ex) {
            return null;
        }
        return class38_sub2_sub2_sub2;
    }

    public static Class38_Sub2_Sub1 method221(int i, int j) {
        Class38_Sub2_Sub1 class38_sub2_sub1 = (Class38_Sub2_Sub1) aClass34_322.method341(j);
        if (i != 4)
            aBoolean263 = !aBoolean263;
        if (class38_sub2_sub1 != null) {
            return class38_sub2_sub1;
        } else {
            Class38_Sub2_Sub1 class38_sub2_sub1_1 = new Class38_Sub2_Sub1(false, j);
            aClass34_322.method342(6, j, class38_sub2_sub1_1);
            return class38_sub2_sub1_1;
        }
    }

    public Class14() {
    }

    public static int anInt262;
    public static boolean aBoolean263;
    public static Class14 aClass14Array264[];
    public int anIntArray265[];
    public int anIntArray266[];
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
    public int anIntArrayArray278[][];
    public int anIntArray279[];
    public int anIntArray280[];
    public int anInt281;
    public int anInt282;
    public int anInt283;
    public boolean aBoolean284;
    public int anIntArray285[];
    public int anIntArray286[];
    public int anIntArray287[];
    public int anInt288;
    public boolean aBoolean289;
    public boolean aBoolean290;
    public boolean aBoolean291;
    public boolean aBoolean292;
    public int anInt293;
    public int anInt294;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2Array295[];
    public int anIntArray296[];
    public int anIntArray297[];
    public String aStringArray298[];
    public boolean aBoolean299;
    public boolean aBoolean300;
    public boolean aBoolean301;
    public Class38_Sub2_Sub2_Sub4 aClass38_Sub2_Sub2_Sub4_302;
    public String aString303;
    public String aString304;
    public int anInt305;
    public int anInt306;
    public int anInt307;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_308;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_309;
    public Class38_Sub2_Sub1 aClass38_Sub2_Sub1_310;
    public Class38_Sub2_Sub1 aClass38_Sub2_Sub1_311;
    public int anInt312;
    public int anInt313;
    public int anInt314;
    public int anInt315;
    public int anInt316;
    public String aString317;
    public String aString318;
    public int anInt319;
    public String aString320;
    public static Class34 aClass34_321;
    public static Class34 aClass34_322;
}
