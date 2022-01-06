// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class38_Sub2_Sub1 extends Class38_Sub2 {

    public static void method351(boolean flag) {
        aClass21Array1259 = null;
        aClass38_Sub2_Sub3_1260 = null;
        aClass38_Sub2_Sub3_1261 = null;
        aClass38_Sub2_Sub3_1262 = null;
        aClass38_Sub2_Sub3_1263 = null;
        aClass38_Sub2_Sub3_1264 = null;
        aClass38_Sub2_Sub3_1265 = null;
        aClass38_Sub2_Sub3_1266 = null;
        aClass38_Sub2_Sub3_1267 = null;
        aClass38_Sub2_Sub3_1268 = null;
        aClass38_Sub2_Sub3_1269 = null;
        aClass38_Sub2_Sub3_1270 = null;
        aClass38_Sub2_Sub3_1271 = null;
        aClass38_Sub2_Sub3_1272 = null;
        aClass38_Sub2_Sub3_1273 = null;
        aBooleanArray1274 = null;
        if (!flag)
            aBoolean1216 = !aBoolean1216;
        aBooleanArray1275 = null;
        anIntArray1276 = null;
        anIntArray1277 = null;
        anIntArray1278 = null;
        anIntArray1279 = null;
        anIntArray1280 = null;
        anIntArray1281 = null;
        anIntArray1282 = null;
        anIntArrayArray1283 = null;
        anIntArray1284 = null;
        anIntArrayArray1285 = null;
        anIntArray1286 = null;
        anIntArray1287 = null;
        anIntArray1288 = null;
        anIntArray1300 = null;
        anIntArray1301 = null;
        anIntArray1302 = null;
        anIntArray1303 = null;
    }

    public static void method352(int i, Class39 class39) {
        if (i < 3 || i > 3)
            return;
        try {
            aClass38_Sub2_Sub3_1260 = new Class38_Sub2_Sub3(363, class39.method474("ob_head.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1261 = new Class38_Sub2_Sub3(363, class39.method474("ob_face1.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1262 = new Class38_Sub2_Sub3(363, class39.method474("ob_face2.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1263 = new Class38_Sub2_Sub3(363, class39.method474("ob_face3.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1264 = new Class38_Sub2_Sub3(363, class39.method474("ob_face4.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1265 = new Class38_Sub2_Sub3(363, class39.method474("ob_face5.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1266 = new Class38_Sub2_Sub3(363, class39.method474("ob_point1.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1267 = new Class38_Sub2_Sub3(363, class39.method474("ob_point2.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1268 = new Class38_Sub2_Sub3(363, class39.method474("ob_point3.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1269 = new Class38_Sub2_Sub3(363, class39.method474("ob_point4.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1270 = new Class38_Sub2_Sub3(363, class39.method474("ob_point5.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1271 = new Class38_Sub2_Sub3(363, class39.method474("ob_vertex1.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1272 = new Class38_Sub2_Sub3(363, class39.method474("ob_vertex2.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1273 = new Class38_Sub2_Sub3(363, class39.method474("ob_axis.dat", null, (byte) 2));
            aClass38_Sub2_Sub3_1260.anInt1329 = 0;
            aClass38_Sub2_Sub3_1266.anInt1329 = 0;
            aClass38_Sub2_Sub3_1267.anInt1329 = 0;
            aClass38_Sub2_Sub3_1268.anInt1329 = 0;
            aClass38_Sub2_Sub3_1269.anInt1329 = 0;
            aClass38_Sub2_Sub3_1271.anInt1329 = 0;
            aClass38_Sub2_Sub3_1272.anInt1329 = 0;
            int j = aClass38_Sub2_Sub3_1260.method448();
            aClass21Array1259 = new Class21[j + 100];
            int k = 0;
            int l = 0;
            int i1 = 0;
            int j1 = 0;
            int k1 = 0;
            int l1 = 0;
            int i2 = 0;
            for (int j2 = 0; j2 < j; j2++) {
                int k2 = aClass38_Sub2_Sub3_1260.method448();
                Class21 class21 = aClass21Array1259[k2] = new Class21();
                class21.anInt401 = aClass38_Sub2_Sub3_1260.method448();
                class21.anInt402 = aClass38_Sub2_Sub3_1260.method448();
                class21.anInt403 = aClass38_Sub2_Sub3_1260.method446();
                class21.anInt404 = aClass38_Sub2_Sub3_1266.anInt1329;
                class21.anInt405 = aClass38_Sub2_Sub3_1267.anInt1329;
                class21.anInt406 = aClass38_Sub2_Sub3_1268.anInt1329;
                class21.anInt407 = aClass38_Sub2_Sub3_1269.anInt1329;
                class21.anInt409 = aClass38_Sub2_Sub3_1271.anInt1329;
                class21.anInt410 = aClass38_Sub2_Sub3_1272.anInt1329;
                int l2 = aClass38_Sub2_Sub3_1260.method446();
                int i3 = aClass38_Sub2_Sub3_1260.method446();
                int j3 = aClass38_Sub2_Sub3_1260.method446();
                int k3 = aClass38_Sub2_Sub3_1260.method446();
                int l3 = aClass38_Sub2_Sub3_1260.method446();
                for (int i4 = 0; i4 < class21.anInt401; i4++) {
                    int j4 = aClass38_Sub2_Sub3_1266.method446();
                    if ((j4 & 1) != 0)
                        aClass38_Sub2_Sub3_1267.method459();
                    if ((j4 & 2) != 0)
                        aClass38_Sub2_Sub3_1268.method459();
                    if ((j4 & 4) != 0)
                        aClass38_Sub2_Sub3_1269.method459();
                }

                for (int k4 = 0; k4 < class21.anInt402; k4++) {
                    int l4 = aClass38_Sub2_Sub3_1272.method446();
                    if (l4 == 1) {
                        aClass38_Sub2_Sub3_1271.method459();
                        aClass38_Sub2_Sub3_1271.method459();
                    }
                    aClass38_Sub2_Sub3_1271.method459();
                }

                class21.anInt411 = i1;
                i1 += class21.anInt402 * 2;
                if (l2 == 1) {
                    class21.anInt412 = j1;
                    j1 += class21.anInt402;
                } else {
                    class21.anInt412 = -1;
                }
                if (i3 == 255) {
                    class21.anInt413 = k1;
                    k1 += class21.anInt402;
                } else {
                    class21.anInt413 = -i3 - 1;
                }
                if (j3 == 1) {
                    class21.anInt414 = l1;
                    l1 += class21.anInt402;
                } else {
                    class21.anInt414 = -1;
                }
                if (k3 == 1) {
                    class21.anInt415 = i2;
                    i2 += class21.anInt402;
                } else {
                    class21.anInt415 = -1;
                }
                if (l3 == 1) {
                    class21.anInt408 = l;
                    l += class21.anInt401;
                } else {
                    class21.anInt408 = -1;
                }
                class21.anInt416 = k;
                k += class21.anInt403;
            }

            return;
        } catch (Exception exception) {
            System.out.println("Error loading model index");
            exception.printStackTrace();
            return;
        }
    }

    public Class38_Sub2_Sub1(boolean flag, int i) {
        anInt1217 = 45861;
        aByte1218 = 47;
        aByte1219 = 47;
        anInt1220 = 5;
        aBoolean1221 = false;
        aBoolean1256 = false;
        if (aClass21Array1259 == null)
            return;
        Class21 class21 = aClass21Array1259[i];
        if (class21 == null) {
            System.out.println("Error model:" + i + " not found!");
            return;
        }
        anInt1222 = class21.anInt401;
        anInt1226 = class21.anInt402;
        anInt1238 = class21.anInt403;
        anIntArray1223 = new int[anInt1222];
        anIntArray1224 = new int[anInt1222];
        anIntArray1225 = new int[anInt1222];
        anIntArray1227 = new int[anInt1226];
        anIntArray1228 = new int[anInt1226];
        anIntArray1229 = new int[anInt1226];
        anIntArray1239 = new int[anInt1238];
        anIntArray1240 = new int[anInt1238];
        anIntArray1241 = new int[anInt1238];
        if (class21.anInt408 >= 0)
            anIntArray1252 = new int[anInt1222];
        if (class21.anInt412 >= 0)
            anIntArray1233 = new int[anInt1226];
        if (class21.anInt413 >= 0)
            anIntArray1234 = new int[anInt1226];
        else
            anInt1237 = -class21.anInt413 - 1;
        if (class21.anInt414 >= 0)
            anIntArray1235 = new int[anInt1226];
        if (class21.anInt415 >= 0)
            anIntArray1253 = new int[anInt1226];
        anIntArray1236 = new int[anInt1226];
        aClass38_Sub2_Sub3_1266.anInt1329 = class21.anInt404;
        aClass38_Sub2_Sub3_1267.anInt1329 = class21.anInt405;
        aClass38_Sub2_Sub3_1268.anInt1329 = class21.anInt406;
        aClass38_Sub2_Sub3_1269.anInt1329 = class21.anInt407;
        aClass38_Sub2_Sub3_1270.anInt1329 = class21.anInt408;
        int j = 0;
        int k = 0;
        int l = 0;
        for (int i1 = 0; i1 < anInt1222; i1++) {
            int j1 = aClass38_Sub2_Sub3_1266.method446();
            int l1 = 0;
            if ((j1 & 1) != 0)
                l1 = aClass38_Sub2_Sub3_1267.method459();
            int j2 = 0;
            if ((j1 & 2) != 0)
                j2 = aClass38_Sub2_Sub3_1268.method459();
            int l2 = 0;
            if ((j1 & 4) != 0)
                l2 = aClass38_Sub2_Sub3_1269.method459();
            anIntArray1223[i1] = j + l1;
            anIntArray1224[i1] = k + j2;
            anIntArray1225[i1] = l + l2;
            j = anIntArray1223[i1];
            k = anIntArray1224[i1];
            l = anIntArray1225[i1];
            if (anIntArray1252 != null)
                anIntArray1252[i1] = aClass38_Sub2_Sub3_1270.method446();
        }

        aClass38_Sub2_Sub3_1261.anInt1329 = class21.anInt411;
        aClass38_Sub2_Sub3_1262.anInt1329 = class21.anInt412;
        aClass38_Sub2_Sub3_1263.anInt1329 = class21.anInt413;
        aClass38_Sub2_Sub3_1264.anInt1329 = class21.anInt414;
        aClass38_Sub2_Sub3_1265.anInt1329 = class21.anInt415;
        for (int k1 = 0; k1 < anInt1226; k1++) {
            anIntArray1236[k1] = aClass38_Sub2_Sub3_1261.method448();
            if (anIntArray1233 != null)
                anIntArray1233[k1] = aClass38_Sub2_Sub3_1262.method446();
            if (anIntArray1234 != null)
                anIntArray1234[k1] = aClass38_Sub2_Sub3_1263.method446();
            if (anIntArray1235 != null)
                anIntArray1235[k1] = aClass38_Sub2_Sub3_1264.method446();
            if (anIntArray1253 != null)
                anIntArray1253[k1] = aClass38_Sub2_Sub3_1265.method446();
        }

        aClass38_Sub2_Sub3_1271.anInt1329 = class21.anInt409;
        aClass38_Sub2_Sub3_1272.anInt1329 = class21.anInt410;
        int i2 = 0;
        int k2 = 0;
        int i3 = 0;
        int j3 = 0;
        for (int k3 = 0; k3 < anInt1226; k3++) {
            int l3 = aClass38_Sub2_Sub3_1272.method446();
            if (l3 == 1) {
                i2 = aClass38_Sub2_Sub3_1271.method459() + j3;
                j3 = i2;
                k2 = aClass38_Sub2_Sub3_1271.method459() + j3;
                j3 = k2;
                i3 = aClass38_Sub2_Sub3_1271.method459() + j3;
                j3 = i3;
                anIntArray1227[k3] = i2;
                anIntArray1228[k3] = k2;
                anIntArray1229[k3] = i3;
            }
            if (l3 == 2) {
                i2 = i2;
                k2 = i3;
                i3 = aClass38_Sub2_Sub3_1271.method459() + j3;
                j3 = i3;
                anIntArray1227[k3] = i2;
                anIntArray1228[k3] = k2;
                anIntArray1229[k3] = i3;
            }
            if (l3 == 3) {
                i2 = i3;
                k2 = k2;
                i3 = aClass38_Sub2_Sub3_1271.method459() + j3;
                j3 = i3;
                anIntArray1227[k3] = i2;
                anIntArray1228[k3] = k2;
                anIntArray1229[k3] = i3;
            }
            if (l3 == 4) {
                int j4 = i2;
                i2 = k2;
                k2 = j4;
                i3 = aClass38_Sub2_Sub3_1271.method459() + j3;
                j3 = i3;
                anIntArray1227[k3] = i2;
                anIntArray1228[k3] = k2;
                anIntArray1229[k3] = i3;
            }
        }

        if (flag)
            throw new NullPointerException();
        aClass38_Sub2_Sub3_1273.anInt1329 = class21.anInt416 * 6;
        for (int i4 = 0; i4 < anInt1238; i4++) {
            anIntArray1239[i4] = aClass38_Sub2_Sub3_1273.method448();
            anIntArray1240[i4] = aClass38_Sub2_Sub3_1273.method448();
            anIntArray1241[i4] = aClass38_Sub2_Sub3_1273.method448();
        }

    }

    public Class38_Sub2_Sub1(int i, Class38_Sub2_Sub1[] aclass38_sub2_sub1, int j) {
        anInt1217 = 45861;
        aByte1218 = 47;
        aByte1219 = 47;
        anInt1220 = 5;
        aBoolean1221 = false;
        aBoolean1256 = false;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        anInt1222 = 0;
        anInt1226 = 0;
        anInt1238 = 0;
        anInt1237 = -1;
        for (int k = 0; k < j; k++) {
            Class38_Sub2_Sub1 class38_sub2_sub1 = aclass38_sub2_sub1[k];
            if (class38_sub2_sub1 != null) {
                anInt1222 += class38_sub2_sub1.anInt1222;
                anInt1226 += class38_sub2_sub1.anInt1226;
                anInt1238 += class38_sub2_sub1.anInt1238;
                flag |= class38_sub2_sub1.anIntArray1233 != null;
                if (class38_sub2_sub1.anIntArray1234 != null) {
                    flag1 = true;
                } else {
                    if (anInt1237 == -1)
                        anInt1237 = class38_sub2_sub1.anInt1237;
                    if (anInt1237 != class38_sub2_sub1.anInt1237)
                        flag1 = true;
                }
                flag2 |= class38_sub2_sub1.anIntArray1235 != null;
                flag3 |= class38_sub2_sub1.anIntArray1253 != null;
            }
        }

        if (i != 0)
            throw new NullPointerException();
        anIntArray1223 = new int[anInt1222];
        anIntArray1224 = new int[anInt1222];
        anIntArray1225 = new int[anInt1222];
        anIntArray1252 = new int[anInt1222];
        anIntArray1227 = new int[anInt1226];
        anIntArray1228 = new int[anInt1226];
        anIntArray1229 = new int[anInt1226];
        anIntArray1239 = new int[anInt1238];
        anIntArray1240 = new int[anInt1238];
        anIntArray1241 = new int[anInt1238];
        if (flag)
            anIntArray1233 = new int[anInt1226];
        if (flag1)
            anIntArray1234 = new int[anInt1226];
        if (flag2)
            anIntArray1235 = new int[anInt1226];
        if (flag3)
            anIntArray1253 = new int[anInt1226];
        anIntArray1236 = new int[anInt1226];
        anInt1222 = 0;
        anInt1226 = 0;
        anInt1238 = 0;
        for (int l = 0; l < j; l++) {
            Class38_Sub2_Sub1 class38_sub2_sub1_1 = aclass38_sub2_sub1[l];
            if (class38_sub2_sub1_1 != null) {
                for (int i1 = 0; i1 < class38_sub2_sub1_1.anInt1226; i1++) {
                    if (flag)
                        if (class38_sub2_sub1_1.anIntArray1233 == null)
                            anIntArray1233[anInt1226] = 0;
                        else
                            anIntArray1233[anInt1226] = class38_sub2_sub1_1.anIntArray1233[i1];
                    if (flag1)
                        if (class38_sub2_sub1_1.anIntArray1234 == null)
                            anIntArray1234[anInt1226] = class38_sub2_sub1_1.anInt1237;
                        else
                            anIntArray1234[anInt1226] = class38_sub2_sub1_1.anIntArray1234[i1];
                    if (flag2)
                        if (class38_sub2_sub1_1.anIntArray1235 == null)
                            anIntArray1235[anInt1226] = 0;
                        else
                            anIntArray1235[anInt1226] = class38_sub2_sub1_1.anIntArray1235[i1];
                    if (flag3 && class38_sub2_sub1_1.anIntArray1253 != null)
                        anIntArray1253[anInt1226] = class38_sub2_sub1_1.anIntArray1253[i1];
                    anIntArray1236[anInt1226] = class38_sub2_sub1_1.anIntArray1236[i1];
                    anIntArray1227[anInt1226] = method353(class38_sub2_sub1_1, class38_sub2_sub1_1.anIntArray1227[i1]);
                    anIntArray1228[anInt1226] = method353(class38_sub2_sub1_1, class38_sub2_sub1_1.anIntArray1228[i1]);
                    anIntArray1229[anInt1226] = method353(class38_sub2_sub1_1, class38_sub2_sub1_1.anIntArray1229[i1]);
                    anInt1226++;
                }

                for (int j1 = 0; j1 < class38_sub2_sub1_1.anInt1238; j1++) {
                    anIntArray1239[anInt1238] = method353(class38_sub2_sub1_1, class38_sub2_sub1_1.anIntArray1239[j1]);
                    anIntArray1240[anInt1238] = method353(class38_sub2_sub1_1, class38_sub2_sub1_1.anIntArray1240[j1]);
                    anIntArray1241[anInt1238] = method353(class38_sub2_sub1_1, class38_sub2_sub1_1.anIntArray1241[j1]);
                    anInt1238++;
                }

            }
        }

    }

    public Class38_Sub2_Sub1(Class38_Sub2_Sub1[] aclass38_sub2_sub1, byte byte0, int i, boolean flag) {
        anInt1217 = 45861;
        aByte1218 = 47;
        aByte1219 = 47;
        anInt1220 = 5;
        aBoolean1221 = false;
        aBoolean1256 = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        anInt1222 = 0;
        anInt1226 = 0;
        anInt1238 = 0;
        anInt1237 = -1;
        for (int j = 0; j < i; j++) {
            Class38_Sub2_Sub1 class38_sub2_sub1 = aclass38_sub2_sub1[j];
            if (class38_sub2_sub1 != null) {
                anInt1222 += class38_sub2_sub1.anInt1222;
                anInt1226 += class38_sub2_sub1.anInt1226;
                anInt1238 += class38_sub2_sub1.anInt1238;
                flag1 |= class38_sub2_sub1.anIntArray1233 != null;
                if (class38_sub2_sub1.anIntArray1234 != null) {
                    flag2 = true;
                } else {
                    if (anInt1237 == -1)
                        anInt1237 = class38_sub2_sub1.anInt1237;
                    if (anInt1237 != class38_sub2_sub1.anInt1237)
                        flag2 = true;
                }
                flag3 |= class38_sub2_sub1.anIntArray1235 != null;
                flag4 |= class38_sub2_sub1.anIntArray1236 != null;
            }
        }

        anIntArray1223 = new int[anInt1222];
        anIntArray1224 = new int[anInt1222];
        anIntArray1225 = new int[anInt1222];
        anIntArray1227 = new int[anInt1226];
        anIntArray1228 = new int[anInt1226];
        anIntArray1229 = new int[anInt1226];
        anIntArray1230 = new int[anInt1226];
        anIntArray1231 = new int[anInt1226];
        anIntArray1232 = new int[anInt1226];
        anIntArray1239 = new int[anInt1238];
        anIntArray1240 = new int[anInt1238];
        anIntArray1241 = new int[anInt1238];
        if (flag1)
            anIntArray1233 = new int[anInt1226];
        if (flag2)
            anIntArray1234 = new int[anInt1226];
        if (flag3)
            anIntArray1235 = new int[anInt1226];
        if (flag4)
            anIntArray1236 = new int[anInt1226];
        anInt1222 = 0;
        anInt1226 = 0;
        anInt1238 = 0;
        if (byte0 != -31) {
            for (int k = 1; k > 0; k++)
                ;
        }
        for (int l = 0; l < i; l++) {
            Class38_Sub2_Sub1 class38_sub2_sub1_1 = aclass38_sub2_sub1[l];
            if (class38_sub2_sub1_1 != null) {
                int i1 = anInt1222;
                for (int j1 = 0; j1 < class38_sub2_sub1_1.anInt1222; j1++) {
                    anIntArray1223[anInt1222] = class38_sub2_sub1_1.anIntArray1223[j1];
                    anIntArray1224[anInt1222] = class38_sub2_sub1_1.anIntArray1224[j1];
                    anIntArray1225[anInt1222] = class38_sub2_sub1_1.anIntArray1225[j1];
                    anInt1222++;
                }

                for (int k1 = 0; k1 < class38_sub2_sub1_1.anInt1226; k1++) {
                    anIntArray1227[anInt1226] = class38_sub2_sub1_1.anIntArray1227[k1] + i1;
                    anIntArray1228[anInt1226] = class38_sub2_sub1_1.anIntArray1228[k1] + i1;
                    anIntArray1229[anInt1226] = class38_sub2_sub1_1.anIntArray1229[k1] + i1;
                    anIntArray1230[anInt1226] = class38_sub2_sub1_1.anIntArray1230[k1];
                    anIntArray1231[anInt1226] = class38_sub2_sub1_1.anIntArray1231[k1];
                    anIntArray1232[anInt1226] = class38_sub2_sub1_1.anIntArray1232[k1];
                    if (flag1)
                        if (class38_sub2_sub1_1.anIntArray1233 == null)
                            anIntArray1233[anInt1226] = 0;
                        else
                            anIntArray1233[anInt1226] = class38_sub2_sub1_1.anIntArray1233[k1];
                    if (flag2)
                        if (class38_sub2_sub1_1.anIntArray1234 == null)
                            anIntArray1234[anInt1226] = class38_sub2_sub1_1.anInt1237;
                        else
                            anIntArray1234[anInt1226] = class38_sub2_sub1_1.anIntArray1234[k1];
                    if (flag3)
                        if (class38_sub2_sub1_1.anIntArray1235 == null)
                            anIntArray1235[anInt1226] = 0;
                        else
                            anIntArray1235[anInt1226] = class38_sub2_sub1_1.anIntArray1235[k1];
                    if (flag4 && class38_sub2_sub1_1.anIntArray1236 != null)
                        anIntArray1236[anInt1226] = class38_sub2_sub1_1.anIntArray1236[k1];
                    anInt1226++;
                }

                for (int l1 = 0; l1 < class38_sub2_sub1_1.anInt1238; l1++) {
                    anIntArray1239[anInt1238] = class38_sub2_sub1_1.anIntArray1239[l1] + i1;
                    anIntArray1240[anInt1238] = class38_sub2_sub1_1.anIntArray1240[l1] + i1;
                    anIntArray1241[anInt1238] = class38_sub2_sub1_1.anIntArray1241[l1] + i1;
                    anInt1238++;
                }

            }
        }

        method354(2992);
    }

    public Class38_Sub2_Sub1(Class38_Sub2_Sub1 class38_sub2_sub1, boolean flag, boolean flag1, int i, boolean flag2) {
        anInt1217 = 45861;
        aByte1218 = 47;
        aByte1219 = 47;
        anInt1220 = 5;
        aBoolean1221 = false;
        aBoolean1256 = false;
        anInt1222 = class38_sub2_sub1.anInt1222;
        anInt1226 = class38_sub2_sub1.anInt1226;
        anInt1238 = class38_sub2_sub1.anInt1238;
        if (flag2) {
            anIntArray1223 = class38_sub2_sub1.anIntArray1223;
            anIntArray1224 = class38_sub2_sub1.anIntArray1224;
            anIntArray1225 = class38_sub2_sub1.anIntArray1225;
        } else {
            anIntArray1223 = new int[anInt1222];
            anIntArray1224 = new int[anInt1222];
            anIntArray1225 = new int[anInt1222];
            for (int j = 0; j < anInt1222; j++) {
                anIntArray1223[j] = class38_sub2_sub1.anIntArray1223[j];
                anIntArray1224[j] = class38_sub2_sub1.anIntArray1224[j];
                anIntArray1225[j] = class38_sub2_sub1.anIntArray1225[j];
            }

        }
        if (flag) {
            anIntArray1236 = class38_sub2_sub1.anIntArray1236;
        } else {
            anIntArray1236 = new int[anInt1226];
            for (int k = 0; k < anInt1226; k++)
                anIntArray1236[k] = class38_sub2_sub1.anIntArray1236[k];

        }
        if (flag1) {
            anIntArray1235 = class38_sub2_sub1.anIntArray1235;
        } else {
            anIntArray1235 = new int[anInt1226];
            if (class38_sub2_sub1.anIntArray1235 == null) {
                for (int l = 0; l < anInt1226; l++)
                    anIntArray1235[l] = 0;

            } else {
                for (int i1 = 0; i1 < anInt1226; i1++)
                    anIntArray1235[i1] = class38_sub2_sub1.anIntArray1235[i1];

            }
        }
        anIntArray1252 = class38_sub2_sub1.anIntArray1252;
        anIntArray1253 = class38_sub2_sub1.anIntArray1253;
        anIntArray1233 = class38_sub2_sub1.anIntArray1233;
        if (i != 0)
            aBoolean1216 = !aBoolean1216;
        anIntArray1227 = class38_sub2_sub1.anIntArray1227;
        anIntArray1228 = class38_sub2_sub1.anIntArray1228;
        anIntArray1229 = class38_sub2_sub1.anIntArray1229;
        anIntArray1234 = class38_sub2_sub1.anIntArray1234;
        anInt1237 = class38_sub2_sub1.anInt1237;
        anIntArray1239 = class38_sub2_sub1.anIntArray1239;
        anIntArray1240 = class38_sub2_sub1.anIntArray1240;
        anIntArray1241 = class38_sub2_sub1.anIntArray1241;
    }

    public Class38_Sub2_Sub1(Class38_Sub2_Sub1 class38_sub2_sub1, byte byte0, boolean flag, boolean flag1) {
        anInt1217 = 45861;
        aByte1218 = 47;
        aByte1219 = 47;
        anInt1220 = 5;
        aBoolean1221 = false;
        aBoolean1256 = false;
        anInt1222 = class38_sub2_sub1.anInt1222;
        anInt1226 = class38_sub2_sub1.anInt1226;
        anInt1238 = class38_sub2_sub1.anInt1238;
        if (flag) {
            anIntArray1224 = new int[anInt1222];
            for (int i = 0; i < anInt1222; i++)
                anIntArray1224[i] = class38_sub2_sub1.anIntArray1224[i];

        } else {
            anIntArray1224 = class38_sub2_sub1.anIntArray1224;
        }
        if (flag1) {
            anIntArray1230 = new int[anInt1226];
            anIntArray1231 = new int[anInt1226];
            anIntArray1232 = new int[anInt1226];
            for (int j = 0; j < anInt1226; j++) {
                anIntArray1230[j] = class38_sub2_sub1.anIntArray1230[j];
                anIntArray1231[j] = class38_sub2_sub1.anIntArray1231[j];
                anIntArray1232[j] = class38_sub2_sub1.anIntArray1232[j];
            }

            anIntArray1233 = new int[anInt1226];
            if (class38_sub2_sub1.anIntArray1233 == null) {
                for (int k = 0; k < anInt1226; k++)
                    anIntArray1233[k] = 0;

            } else {
                for (int l = 0; l < anInt1226; l++)
                    anIntArray1233[l] = class38_sub2_sub1.anIntArray1233[l];

            }
            aClass25Array1257 = new Class25[anInt1222];
            for (int i1 = 0; i1 < anInt1222; i1++) {
                Class25 class25 = aClass25Array1257[i1] = new Class25();
                Class25 class25_1 = class38_sub2_sub1.aClass25Array1257[i1];
                class25.anInt467 = class25_1.anInt467;
                class25.anInt468 = class25_1.anInt468;
                class25.anInt469 = class25_1.anInt469;
                class25.anInt470 = class25_1.anInt470;
            }

            aClass25Array1258 = class38_sub2_sub1.aClass25Array1258;
        } else {
            anIntArray1230 = class38_sub2_sub1.anIntArray1230;
            anIntArray1231 = class38_sub2_sub1.anIntArray1231;
            anIntArray1232 = class38_sub2_sub1.anIntArray1232;
            anIntArray1233 = class38_sub2_sub1.anIntArray1233;
        }
        anIntArray1223 = class38_sub2_sub1.anIntArray1223;
        anIntArray1225 = class38_sub2_sub1.anIntArray1225;
        anIntArray1236 = class38_sub2_sub1.anIntArray1236;
        anIntArray1235 = class38_sub2_sub1.anIntArray1235;
        anIntArray1234 = class38_sub2_sub1.anIntArray1234;
        anInt1237 = class38_sub2_sub1.anInt1237;
        anIntArray1227 = class38_sub2_sub1.anIntArray1227;
        anIntArray1228 = class38_sub2_sub1.anIntArray1228;
        anIntArray1229 = class38_sub2_sub1.anIntArray1229;
        anIntArray1239 = class38_sub2_sub1.anIntArray1239;
        anIntArray1240 = class38_sub2_sub1.anIntArray1240;
        anIntArray1241 = class38_sub2_sub1.anIntArray1241;
        if (byte0 != -31) {
            throw new NullPointerException();
        } else {
            anInt1247 = class38_sub2_sub1.anInt1247;
            anInt1248 = class38_sub2_sub1.anInt1248;
            anInt1246 = class38_sub2_sub1.anInt1246;
            anInt1250 = class38_sub2_sub1.anInt1250;
            anInt1249 = class38_sub2_sub1.anInt1249;
            anInt1242 = class38_sub2_sub1.anInt1242;
            anInt1244 = class38_sub2_sub1.anInt1244;
            anInt1245 = class38_sub2_sub1.anInt1245;
            anInt1243 = class38_sub2_sub1.anInt1243;
            return;
        }
    }

    public Class38_Sub2_Sub1(int i, Class38_Sub2_Sub1 class38_sub2_sub1, boolean flag) {
        anInt1217 = 45861;
        aByte1218 = 47;
        aByte1219 = 47;
        anInt1220 = 5;
        aBoolean1221 = false;
        aBoolean1256 = false;
        anInt1222 = class38_sub2_sub1.anInt1222;
        anInt1226 = class38_sub2_sub1.anInt1226;
        anInt1238 = class38_sub2_sub1.anInt1238;
        anIntArray1223 = new int[anInt1222];
        anIntArray1224 = new int[anInt1222];
        anIntArray1225 = new int[anInt1222];
        for (int j = 0; j < anInt1222; j++) {
            anIntArray1223[j] = class38_sub2_sub1.anIntArray1223[j];
            anIntArray1224[j] = class38_sub2_sub1.anIntArray1224[j];
            anIntArray1225[j] = class38_sub2_sub1.anIntArray1225[j];
        }

        if (flag) {
            anIntArray1235 = class38_sub2_sub1.anIntArray1235;
        } else {
            anIntArray1235 = new int[anInt1226];
            if (class38_sub2_sub1.anIntArray1235 == null) {
                for (int k = 0; k < anInt1226; k++)
                    anIntArray1235[k] = 0;

            } else {
                for (int l = 0; l < anInt1226; l++)
                    anIntArray1235[l] = class38_sub2_sub1.anIntArray1235[l];

            }
        }
        anIntArray1233 = class38_sub2_sub1.anIntArray1233;
        anIntArray1236 = class38_sub2_sub1.anIntArray1236;
        anIntArray1234 = class38_sub2_sub1.anIntArray1234;
        if (i != 0)
            anInt1220 = 213;
        anInt1237 = class38_sub2_sub1.anInt1237;
        anIntArrayArray1255 = class38_sub2_sub1.anIntArrayArray1255;
        anIntArrayArray1254 = class38_sub2_sub1.anIntArrayArray1254;
        anIntArray1227 = class38_sub2_sub1.anIntArray1227;
        anIntArray1228 = class38_sub2_sub1.anIntArray1228;
        anIntArray1229 = class38_sub2_sub1.anIntArray1229;
        anIntArray1230 = class38_sub2_sub1.anIntArray1230;
        anIntArray1231 = class38_sub2_sub1.anIntArray1231;
        anIntArray1232 = class38_sub2_sub1.anIntArray1232;
        anIntArray1239 = class38_sub2_sub1.anIntArray1239;
        anIntArray1240 = class38_sub2_sub1.anIntArray1240;
        anIntArray1241 = class38_sub2_sub1.anIntArray1241;
    }

    public int method353(Class38_Sub2_Sub1 class38_sub2_sub1, int i) {
        int j = -1;
        int k = class38_sub2_sub1.anIntArray1223[i];
        int l = class38_sub2_sub1.anIntArray1224[i];
        int i1 = class38_sub2_sub1.anIntArray1225[i];
        for (int j1 = 0; j1 < anInt1222; j1++) {
            if (k != anIntArray1223[j1] || l != anIntArray1224[j1] || i1 != anIntArray1225[j1])
                continue;
            j = j1;
            break;
        }

        if (j == -1) {
            anIntArray1223[anInt1222] = k;
            anIntArray1224[anInt1222] = l;
            anIntArray1225[anInt1222] = i1;
            if (class38_sub2_sub1.anIntArray1252 != null)
                anIntArray1252[anInt1222] = class38_sub2_sub1.anIntArray1252[i];
            j = anInt1222++;
        }
        return j;
    }

    public void method354(int i) {
        anInt1247 = 0;
        if (i != 2992)
            return;
        anInt1246 = 0;
        anInt1248 = 0;
        for (int j = 0; j < anInt1222; j++) {
            int k = anIntArray1223[j];
            int l = anIntArray1224[j];
            int i1 = anIntArray1225[j];
            if (-l > anInt1247)
                anInt1247 = -l;
            if (l > anInt1248)
                anInt1248 = l;
            int j1 = k * k + i1 * i1;
            if (j1 > anInt1246)
                anInt1246 = j1;
        }

        anInt1246 = (int) (Math.sqrt(anInt1246) + 0.98999999999999999D);
        anInt1250 = (int) (Math.sqrt(anInt1246 * anInt1246 + anInt1247 * anInt1247) + 0.98999999999999999D);
        anInt1249 = anInt1250 + (int) (Math.sqrt(anInt1246 * anInt1246 + anInt1248 * anInt1248) + 0.98999999999999999D);
    }

    public void method355(int i) {
        anInt1247 = 0;
        if (i != 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        anInt1248 = 0;
        for (int k = 0; k < anInt1222; k++) {
            int l = anIntArray1224[k];
            if (-l > anInt1247)
                anInt1247 = -l;
            if (l > anInt1248)
                anInt1248 = l;
        }

        anInt1250 = (int) (Math.sqrt(anInt1246 * anInt1246 + anInt1247 * anInt1247) + 0.98999999999999999D);
        anInt1249 = anInt1250 + (int) (Math.sqrt(anInt1246 * anInt1246 + anInt1248 * anInt1248) + 0.98999999999999999D);
    }

    public void method356(byte byte0) {
        anInt1247 = 0;
        if (byte0 != aByte1219)
            return;
        anInt1246 = 0;
        anInt1248 = 0;
        anInt1242 = 0xf423f;
        anInt1243 = 0xfff0bdc1;
        anInt1244 = 0xfffe7961;
        anInt1245 = 0x1869f;
        for (int i = 0; i < anInt1222; i++) {
            int j = anIntArray1223[i];
            int k = anIntArray1224[i];
            int l = anIntArray1225[i];
            if (j < anInt1242)
                anInt1242 = j;
            if (j > anInt1243)
                anInt1243 = j;
            if (l < anInt1245)
                anInt1245 = l;
            if (l > anInt1244)
                anInt1244 = l;
            if (-k > anInt1247)
                anInt1247 = -k;
            if (k > anInt1248)
                anInt1248 = k;
            int i1 = j * j + l * l;
            if (i1 > anInt1246)
                anInt1246 = i1;
        }

        anInt1246 = (int) Math.sqrt(anInt1246);
        anInt1250 = (int) Math.sqrt(anInt1246 * anInt1246 + anInt1247 * anInt1247);
        anInt1249 = anInt1250 + (int) Math.sqrt(anInt1246 * anInt1246 + anInt1248 * anInt1248);
    }

    public void method357(int i) {
        if (i != 4)
            return;
        if (anIntArray1252 != null) {
            int[] ai = new int[256];
            int j = 0;
            for (int l = 0; l < anInt1222; l++) {
                int j1 = anIntArray1252[l];
                ai[j1]++;
                if (j1 > j)
                    j = j1;
            }

            anIntArrayArray1254 = new int[j + 1][];
            for (int k1 = 0; k1 <= j; k1++) {
                anIntArrayArray1254[k1] = new int[ai[k1]];
                ai[k1] = 0;
            }

            for (int j2 = 0; j2 < anInt1222; j2++) {
                int l2 = anIntArray1252[j2];
                anIntArrayArray1254[l2][ai[l2]++] = j2;
            }

            anIntArray1252 = null;
        }
        if (anIntArray1253 != null) {
            int[] ai1 = new int[256];
            int k = 0;
            for (int i1 = 0; i1 < anInt1226; i1++) {
                int l1 = anIntArray1253[i1];
                ai1[l1]++;
                if (l1 > k)
                    k = l1;
            }

            anIntArrayArray1255 = new int[k + 1][];
            for (int i2 = 0; i2 <= k; i2++) {
                anIntArrayArray1255[i2] = new int[ai1[i2]];
                ai1[i2] = 0;
            }

            for (int k2 = 0; k2 < anInt1226; k2++) {
                int i3 = anIntArray1253[k2];
                anIntArrayArray1255[i3][ai1[i3]++] = k2;
            }

            anIntArray1253 = null;
        }
    }

    public void method358(int i, int j) {
        if (anIntArrayArray1254 == null)
            return;
        if (j == -1)
            return;
        Class11 class11 = Class11.aClass11Array235[j];
        Class9 class9 = class11.aClass9_237;
        anInt1292 = 0;
        anInt1293 = 0;
        anInt1294 = 0;
        for (int k = 0; k < class11.anInt238; k++) {
            int l = class11.anIntArray239[k];
            method360(class9.anIntArray218[l], class9.anIntArrayArray219[l], class11.anIntArray240[k],
                    class11.anIntArray241[k], class11.anIntArray242[k]);
        }

        if (i == -16599)
            ;
    }

    public void method359(int i, int j, int k, int[] ai) {
        if (k == -1)
            return;
        if (ai == null || i == -1) {
            method358(-16599, k);
            return;
        }
        Class11 class11 = Class11.aClass11Array235[k];
        if (j < 3 || j > 3)
            anInt1220 = -162;
        Class11 class11_1 = Class11.aClass11Array235[i];
        Class9 class9 = class11.aClass9_237;
        anInt1292 = 0;
        anInt1293 = 0;
        anInt1294 = 0;
        int l = 0;
        int i1 = ai[l++];
        for (int j1 = 0; j1 < class11.anInt238; j1++) {
            int k1;
            for (k1 = class11.anIntArray239[j1]; k1 > i1; i1 = ai[l++])
                ;
            if (k1 != i1 || class9.anIntArray218[k1] == 0)
                method360(class9.anIntArray218[k1], class9.anIntArrayArray219[k1], class11.anIntArray240[j1],
                        class11.anIntArray241[j1], class11.anIntArray242[j1]);
        }

        anInt1292 = 0;
        anInt1293 = 0;
        anInt1294 = 0;
        l = 0;
        i1 = ai[l++];
        for (int l1 = 0; l1 < class11_1.anInt238; l1++) {
            int i2;
            for (i2 = class11_1.anIntArray239[l1]; i2 > i1; i1 = ai[l++])
                ;
            if (i2 == i1 || class9.anIntArray218[i2] == 0)
                method360(class9.anIntArray218[i2], class9.anIntArrayArray219[i2], class11_1.anIntArray240[l1],
                        class11_1.anIntArray241[l1], class11_1.anIntArray242[l1]);
        }

    }

    public void method360(int i, int[] ai, int j, int k, int l) {
        int i1 = ai.length;
        if (i == 0) {
            int j1 = 0;
            anInt1292 = 0;
            anInt1293 = 0;
            anInt1294 = 0;
            for (int k2 = 0; k2 < i1; k2++) {
                int l3 = ai[k2];
                if (l3 < anIntArrayArray1254.length) {
                    int[] ai5 = anIntArrayArray1254[l3];
                    for (int i5 = 0; i5 < ai5.length; i5++) {
                        int j6 = ai5[i5];
                        anInt1292 += anIntArray1223[j6];
                        anInt1293 += anIntArray1224[j6];
                        anInt1294 += anIntArray1225[j6];
                        j1++;
                    }

                }
            }

            if (j1 > 0) {
                anInt1292 = anInt1292 / j1 + j;
                anInt1293 = anInt1293 / j1 + k;
                anInt1294 = anInt1294 / j1 + l;
                return;
            } else {
                anInt1292 = j;
                anInt1293 = k;
                anInt1294 = l;
                return;
            }
        }
        if (i == 1) {
            for (int k1 = 0; k1 < i1; k1++) {
                int l2 = ai[k1];
                if (l2 < anIntArrayArray1254.length) {
                    int[] ai1 = anIntArrayArray1254[l2];
                    for (int i4 = 0; i4 < ai1.length; i4++) {
                        int j5 = ai1[i4];
                        anIntArray1223[j5] += j;
                        anIntArray1224[j5] += k;
                        anIntArray1225[j5] += l;
                    }

                }
            }

            return;
        }
        if (i == 2) {
            for (int l1 = 0; l1 < i1; l1++) {
                int i3 = ai[l1];
                if (i3 < anIntArrayArray1254.length) {
                    int[] ai2 = anIntArrayArray1254[i3];
                    for (int j4 = 0; j4 < ai2.length; j4++) {
                        int k5 = ai2[j4];
                        anIntArray1223[k5] -= anInt1292;
                        anIntArray1224[k5] -= anInt1293;
                        anIntArray1225[k5] -= anInt1294;
                        int k6 = (j & 0xff) * 8;
                        int l6 = (k & 0xff) * 8;
                        int i7 = (l & 0xff) * 8;
                        if (i7 != 0) {
                            int j7 = anIntArray1300[i7];
                            int i8 = anIntArray1301[i7];
                            int l8 = anIntArray1224[k5] * j7 + anIntArray1223[k5] * i8 >> 16;
                            anIntArray1224[k5] = anIntArray1224[k5] * i8 - anIntArray1223[k5] * j7 >> 16;
                            anIntArray1223[k5] = l8;
                        }
                        if (k6 != 0) {
                            int k7 = anIntArray1300[k6];
                            int j8 = anIntArray1301[k6];
                            int i9 = anIntArray1224[k5] * j8 - anIntArray1225[k5] * k7 >> 16;
                            anIntArray1225[k5] = anIntArray1224[k5] * k7 + anIntArray1225[k5] * j8 >> 16;
                            anIntArray1224[k5] = i9;
                        }
                        if (l6 != 0) {
                            int l7 = anIntArray1300[l6];
                            int k8 = anIntArray1301[l6];
                            int j9 = anIntArray1225[k5] * l7 + anIntArray1223[k5] * k8 >> 16;
                            anIntArray1225[k5] = anIntArray1225[k5] * k8 - anIntArray1223[k5] * l7 >> 16;
                            anIntArray1223[k5] = j9;
                        }
                        anIntArray1223[k5] += anInt1292;
                        anIntArray1224[k5] += anInt1293;
                        anIntArray1225[k5] += anInt1294;
                    }

                }
            }

            return;
        }
        if (i == 3) {
            for (int i2 = 0; i2 < i1; i2++) {
                int j3 = ai[i2];
                if (j3 < anIntArrayArray1254.length) {
                    int[] ai3 = anIntArrayArray1254[j3];
                    for (int k4 = 0; k4 < ai3.length; k4++) {
                        int l5 = ai3[k4];
                        anIntArray1223[l5] -= anInt1292;
                        anIntArray1224[l5] -= anInt1293;
                        anIntArray1225[l5] -= anInt1294;
                        anIntArray1223[l5] = (anIntArray1223[l5] * j) / 128;
                        anIntArray1224[l5] = (anIntArray1224[l5] * k) / 128;
                        anIntArray1225[l5] = (anIntArray1225[l5] * l) / 128;
                        anIntArray1223[l5] += anInt1292;
                        anIntArray1224[l5] += anInt1293;
                        anIntArray1225[l5] += anInt1294;
                    }

                }
            }

            return;
        }
        if (i == 5 && anIntArrayArray1255 != null && anIntArray1235 != null) {
            for (int j2 = 0; j2 < i1; j2++) {
                int k3 = ai[j2];
                if (k3 < anIntArrayArray1255.length) {
                    int[] ai4 = anIntArrayArray1255[k3];
                    for (int l4 = 0; l4 < ai4.length; l4++) {
                        int i6 = ai4[l4];
                        anIntArray1235[i6] += j * 8;
                        if (anIntArray1235[i6] < 0)
                            anIntArray1235[i6] = 0;
                        if (anIntArray1235[i6] > 255)
                            anIntArray1235[i6] = 255;
                    }

                }
            }

        }
    }

    public void method361(int i) {
        if (i != 0)
            anInt1217 = 472;
        for (int j = 0; j < anInt1222; j++) {
            int k = anIntArray1223[j];
            anIntArray1223[j] = anIntArray1225[j];
            anIntArray1225[j] = -k;
        }

    }

    public void method362(byte byte0, int i) {
        int j = anIntArray1300[i];
        int k = anIntArray1301[i];
        for (int l = 0; l < anInt1222; l++) {
            int i1 = anIntArray1224[l] * k - anIntArray1225[l] * j >> 16;
            anIntArray1225[l] = anIntArray1224[l] * j + anIntArray1225[l] * k >> 16;
            anIntArray1224[l] = i1;
        }

        if (byte0 == 7)
            byte0 = 0;
    }

    public void method363(int i, int j, int k, int l) {
        if (k >= 0)
            aBoolean1221 = !aBoolean1221;
        for (int i1 = 0; i1 < anInt1222; i1++) {
            anIntArray1223[i1] += j;
            anIntArray1224[i1] += i;
            anIntArray1225[i1] += l;
        }

    }

    public void method364(int i, int j) {
        for (int k = 0; k < anInt1226; k++)
            if (anIntArray1236[k] == i)
                anIntArray1236[k] = j;

    }

    public void method365(int i) {
        for (int j = 0; j < anInt1222; j++)
            anIntArray1225[j] = -anIntArray1225[j];

        for (int k = 0; k < anInt1226; k++) {
            int l = anIntArray1227[k];
            anIntArray1227[k] = anIntArray1229[k];
            anIntArray1229[k] = l;
        }

        if (i >= 0) {
            for (int i1 = 1; i1 > 0; i1++)
                ;
        }
    }

    public void method366(int i, int j, int k, int l) {
        for (int i1 = 0; i1 < anInt1222; i1++) {
            anIntArray1223[i1] = (anIntArray1223[i1] * l) / 128;
            anIntArray1224[i1] = (anIntArray1224[i1] * k) / 128;
            anIntArray1225[i1] = (anIntArray1225[i1] * i) / 128;
        }

        if (j >= 2)
            if (j <= 2)
                ;
    }

    public void method367(int i, int j, int k, int l, int i1, boolean flag) {
        int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
        int k1 = j * j1 >> 8;
        if (anIntArray1230 == null) {
            anIntArray1230 = new int[anInt1226];
            anIntArray1231 = new int[anInt1226];
            anIntArray1232 = new int[anInt1226];
        }
        if (aClass25Array1257 == null) {
            aClass25Array1257 = new Class25[anInt1222];
            for (int l1 = 0; l1 < anInt1222; l1++)
                aClass25Array1257[l1] = new Class25();

        }
        for (int i2 = 0; i2 < anInt1226; i2++) {
            int j2 = anIntArray1227[i2];
            int l2 = anIntArray1228[i2];
            int i3 = anIntArray1229[i2];
            int j3 = anIntArray1223[l2] - anIntArray1223[j2];
            int k3 = anIntArray1224[l2] - anIntArray1224[j2];
            int l3 = anIntArray1225[l2] - anIntArray1225[j2];
            int i4 = anIntArray1223[i3] - anIntArray1223[j2];
            int j4 = anIntArray1224[i3] - anIntArray1224[j2];
            int k4 = anIntArray1225[i3] - anIntArray1225[j2];
            int l4 = k3 * k4 - j4 * l3;
            int i5 = l3 * i4 - k4 * j3;
            int j5;
            for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192
                    || j5 < -8192; j5 >>= 1) {
                l4 >>= 1;
                i5 >>= 1;
            }

            int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
            if (k5 <= 0)
                k5 = 1;
            l4 = (l4 * 256) / k5;
            i5 = (i5 * 256) / k5;
            j5 = (j5 * 256) / k5;
            if (anIntArray1233 == null || (anIntArray1233[i2] & 1) == 0) {
                Class25 class25_2 = aClass25Array1257[j2];
                class25_2.anInt467 += l4;
                class25_2.anInt468 += i5;
                class25_2.anInt469 += j5;
                class25_2.anInt470++;
                class25_2 = aClass25Array1257[l2];
                class25_2.anInt467 += l4;
                class25_2.anInt468 += i5;
                class25_2.anInt469 += j5;
                class25_2.anInt470++;
                class25_2 = aClass25Array1257[i3];
                class25_2.anInt467 += l4;
                class25_2.anInt468 += i5;
                class25_2.anInt469 += j5;
                class25_2.anInt470++;
            } else {
                int l5 = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
                anIntArray1230[i2] = method369(anIntArray1236[i2], l5, anIntArray1233[i2]);
            }
        }

        if (flag) {
            method368(i, k1, k, l, i1);
        } else {
            aClass25Array1258 = new Class25[anInt1222];
            for (int k2 = 0; k2 < anInt1222; k2++) {
                Class25 class25 = aClass25Array1257[k2];
                Class25 class25_1 = aClass25Array1258[k2] = new Class25();
                class25_1.anInt467 = class25.anInt467;
                class25_1.anInt468 = class25.anInt468;
                class25_1.anInt469 = class25.anInt469;
                class25_1.anInt470 = class25.anInt470;
            }

        }
        if (flag) {
            method354(2992);
            return;
        } else {
            method356(aByte1218);
            return;
        }
    }

    public void method368(int i, int j, int k, int l, int i1) {
        for (int j1 = 0; j1 < anInt1226; j1++) {
            int k1 = anIntArray1227[j1];
            int i2 = anIntArray1228[j1];
            int j2 = anIntArray1229[j1];
            if (anIntArray1233 == null) {
                int i3 = anIntArray1236[j1];
                Class25 class25 = aClass25Array1257[k1];
                int k2 = i + (k * class25.anInt467 + l * class25.anInt468 + i1 * class25.anInt469)
                        / (j * class25.anInt470);
                anIntArray1230[j1] = method369(i3, k2, 0);
                class25 = aClass25Array1257[i2];
                k2 = i + (k * class25.anInt467 + l * class25.anInt468 + i1 * class25.anInt469) / (j * class25.anInt470);
                anIntArray1231[j1] = method369(i3, k2, 0);
                class25 = aClass25Array1257[j2];
                k2 = i + (k * class25.anInt467 + l * class25.anInt468 + i1 * class25.anInt469) / (j * class25.anInt470);
                anIntArray1232[j1] = method369(i3, k2, 0);
            } else if ((anIntArray1233[j1] & 1) == 0) {
                int j3 = anIntArray1236[j1];
                int k3 = anIntArray1233[j1];
                Class25 class25_1 = aClass25Array1257[k1];
                int l2 = i + (k * class25_1.anInt467 + l * class25_1.anInt468 + i1 * class25_1.anInt469)
                        / (j * class25_1.anInt470);
                anIntArray1230[j1] = method369(j3, l2, k3);
                class25_1 = aClass25Array1257[i2];
                l2 = i + (k * class25_1.anInt467 + l * class25_1.anInt468 + i1 * class25_1.anInt469)
                        / (j * class25_1.anInt470);
                anIntArray1231[j1] = method369(j3, l2, k3);
                class25_1 = aClass25Array1257[j2];
                l2 = i + (k * class25_1.anInt467 + l * class25_1.anInt468 + i1 * class25_1.anInt469)
                        / (j * class25_1.anInt470);
                anIntArray1232[j1] = method369(j3, l2, k3);
            }
        }

        aClass25Array1257 = null;
        aClass25Array1258 = null;
        anIntArray1252 = null;
        anIntArray1253 = null;
        if (anIntArray1233 != null) {
            for (int l1 = 0; l1 < anInt1226; l1++)
                if ((anIntArray1233[l1] & 2) == 2)
                    return;

        }
        anIntArray1236 = null;
    }

    public static int method369(int i, int j, int k) {
        if ((k & 2) == 2) {
            if (j < 0)
                j = 0;
            else if (j > 127)
                j = 127;
            j = 127 - j;
            return j;
        }
        j = j * (i & 0x7f) >> 7;
        if (j < 2)
            j = 2;
        else if (j > 126)
            j = 126;
        return (i & 0xff80) + j;
    }

    public void method370(int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = Class38_Sub2_Sub2_Sub1.anInt1442;
        int i2 = Class38_Sub2_Sub2_Sub1.anInt1443;
        int j2 = anIntArray1300[i];
        int k2 = anIntArray1301[i];
        int l2 = anIntArray1300[j];
        int i3 = anIntArray1301[j];
        int j3 = anIntArray1300[k];
        int k3 = anIntArray1301[k];
        int l3 = anIntArray1300[l];
        int i4 = anIntArray1301[l];
        int j4 = j1 * l3 + k1 * i4 >> 16;
        for (int k4 = 0; k4 < anInt1222; k4++) {
            int l4 = anIntArray1223[k4];
            int i5 = anIntArray1224[k4];
            int j5 = anIntArray1225[k4];
            if (k != 0) {
                int k5 = i5 * j3 + l4 * k3 >> 16;
                i5 = i5 * k3 - l4 * j3 >> 16;
                l4 = k5;
            }
            if (i != 0) {
                int l5 = i5 * k2 - j5 * j2 >> 16;
                j5 = i5 * j2 + j5 * k2 >> 16;
                i5 = l5;
            }
            if (j != 0) {
                int i6 = j5 * l2 + l4 * i3 >> 16;
                j5 = j5 * i3 - l4 * l2 >> 16;
                l4 = i6;
            }
            l4 += i1;
            i5 += j1;
            j5 += k1;
            int j6 = i5 * i4 - j5 * l3 >> 16;
            j5 = i5 * l3 + j5 * i4 >> 16;
            i5 = j6;
            anIntArray1278[k4] = j5 - j4;
            anIntArray1276[k4] = l1 + (l4 << 9) / j5;
            anIntArray1277[k4] = i2 + (i5 << 9) / j5;
            if (anInt1238 > 0) {
                anIntArray1279[k4] = l4;
                anIntArray1280[k4] = i5;
                anIntArray1281[k4] = j5;
            }
        }

        try {
            method372(false, false, 0);
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void method371(int i, int j, int k, int l, int i1, int j1, int k1,
                          int l1, int i2) {
        int j2 = l1 * i1 - j1 * l >> 16;
        int k2 = k1 * j + j2 * k >> 16;
        int l2 = anInt1246 * k >> 16;
        int i3 = k2 + l2;
        if (i3 <= 50 || k2 >= 3500)
            return;
        int j3 = l1 * l + j1 * i1 >> 16;
        int k3 = j3 - anInt1246 << 9;
        if (k3 / i3 >= Class38_Sub2_Sub2.anInt1316)
            return;
        int l3 = j3 + anInt1246 << 9;
        if (l3 / i3 <= -Class38_Sub2_Sub2.anInt1316)
            return;
        int i4 = k1 * k - j2 * j >> 16;
        int j4 = anInt1246 * j >> 16;
        int k4 = i4 + j4 << 9;
        if (k4 / i3 <= -Class38_Sub2_Sub2.anInt1317)
            return;
        int l4 = j4 + (anInt1247 * k >> 16);
        int i5 = i4 - l4 << 9;
        if (i5 / i3 >= Class38_Sub2_Sub2.anInt1317)
            return;
        int j5 = l2 + (anInt1247 * j >> 16);
        boolean flag = k2 - j5 <= 50;
        boolean flag1 = false;
        if (i2 > 0 && aBoolean1295) {
            int k5 = k2 - l2;
            if (k5 <= 50)
                k5 = 50;
            if (j3 > 0) {
                k3 /= i3;
                l3 /= k5;
            } else {
                l3 /= i3;
                k3 /= k5;
            }
            if (i4 > 0) {
                i5 /= i3;
                k4 /= k5;
            } else {
                k4 /= i3;
                i5 /= k5;
            }
            int i6 = anInt1296 - Class38_Sub2_Sub2_Sub1.anInt1442;
            int k6 = anInt1297 - Class38_Sub2_Sub2_Sub1.anInt1443;
            if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4)
                if (aBoolean1256)
                    anIntArray1299[anInt1298++] = i2;
                else
                    flag1 = true;
        }
        int l5 = Class38_Sub2_Sub2_Sub1.anInt1442;
        int j6 = Class38_Sub2_Sub2_Sub1.anInt1443;
        int l6 = 0;
        int i7 = 0;
        if (i != 0) {
            l6 = anIntArray1300[i];
            i7 = anIntArray1301[i];
        }
        for (int j7 = 0; j7 < anInt1222; j7++) {
            int k7 = anIntArray1223[j7];
            int l7 = anIntArray1224[j7];
            int i8 = anIntArray1225[j7];
            if (i != 0) {
                int j8 = i8 * l6 + k7 * i7 >> 16;
                i8 = i8 * i7 - k7 * l6 >> 16;
                k7 = j8;
            }
            k7 += j1;
            l7 += k1;
            i8 += l1;
            int k8 = i8 * l + k7 * i1 >> 16;
            i8 = i8 * i1 - k7 * l >> 16;
            k7 = k8;
            k8 = l7 * k - i8 * j >> 16;
            i8 = l7 * j + i8 * k >> 16;
            l7 = k8;
            anIntArray1278[j7] = i8 - k2;
            if (i8 >= 50) {
                anIntArray1276[j7] = l5 + (k7 << 9) / i8;
                anIntArray1277[j7] = j6 + (l7 << 9) / i8;
            } else {
                anIntArray1276[j7] = -5000;
                flag = true;
            }
            if (flag || anInt1238 > 0) {
                anIntArray1279[j7] = k7;
                anIntArray1280[j7] = l7;
                anIntArray1281[j7] = i8;
            }
        }

        try {
            method372(flag, flag1, i2);
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void method372(boolean flag, boolean flag1, int i) {
        for (int j = 0; j < anInt1249; j++)
            anIntArray1282[j] = 0;

        for (int k = 0; k < anInt1226; k++)
            if (anIntArray1233 == null || anIntArray1233[k] != -1) {
                int l = anIntArray1227[k];
                int k1 = anIntArray1228[k];
                int j2 = anIntArray1229[k];
                int i3 = anIntArray1276[l];
                int l3 = anIntArray1276[k1];
                int k4 = anIntArray1276[j2];
                if (flag && (i3 == -5000 || l3 == -5000 || k4 == -5000)) {
                    aBooleanArray1275[k] = true;
                    int j5 = (anIntArray1278[l] + anIntArray1278[k1] + anIntArray1278[j2]) / 3 + anInt1250;
                    anIntArrayArray1283[j5][anIntArray1282[j5]++] = k;
                } else {
                    if (flag1 && method375(anInt1296, anInt1297, anIntArray1277[l], anIntArray1277[k1],
                            anIntArray1277[j2], i3, l3, k4)) {
                        anIntArray1299[anInt1298++] = i;
                        flag1 = false;
                    }
                    if ((i3 - l3) * (anIntArray1277[j2] - anIntArray1277[k1])
                            - (anIntArray1277[l] - anIntArray1277[k1]) * (k4 - l3) > 0) {
                        aBooleanArray1275[k] = false;
                        aBooleanArray1274[k] = i3 < 0 || l3 < 0 || k4 < 0 || i3 > Class38_Sub2_Sub2.anInt1315
                                || l3 > Class38_Sub2_Sub2.anInt1315 || k4 > Class38_Sub2_Sub2.anInt1315;
                        int k5 = (anIntArray1278[l] + anIntArray1278[k1] + anIntArray1278[j2]) / 3 + anInt1250;
                        anIntArrayArray1283[k5][anIntArray1282[k5]++] = k;
                    }
                }
            }

        if (anIntArray1234 == null) {
            for (int i1 = anInt1249 - 1; i1 >= 0; i1--) {
                int l1 = anIntArray1282[i1];
                if (l1 > 0) {
                    int[] ai = anIntArrayArray1283[i1];
                    for (int j3 = 0; j3 < l1; j3++)
                        method373(ai[j3]);

                }
            }

            return;
        }
        for (int j1 = 0; j1 < 12; j1++) {
            anIntArray1284[j1] = 0;
            anIntArray1288[j1] = 0;
        }

        for (int i2 = anInt1249 - 1; i2 >= 0; i2--) {
            int k2 = anIntArray1282[i2];
            if (k2 > 0) {
                int[] ai1 = anIntArrayArray1283[i2];
                for (int i4 = 0; i4 < k2; i4++) {
                    int l4 = ai1[i4];
                    int l5 = anIntArray1234[l4];
                    int j6 = anIntArray1284[l5]++;
                    anIntArrayArray1285[l5][j6] = l4;
                    if (l5 < 10)
                        anIntArray1288[l5] += i2;
                    else if (l5 == 10)
                        anIntArray1286[j6] = i2;
                    else
                        anIntArray1287[j6] = i2;
                }

            }
        }

        int l2 = 0;
        if (anIntArray1284[1] > 0 || anIntArray1284[2] > 0)
            l2 = (anIntArray1288[1] + anIntArray1288[2]) / (anIntArray1284[1] + anIntArray1284[2]);
        int k3 = 0;
        if (anIntArray1284[3] > 0 || anIntArray1284[4] > 0)
            k3 = (anIntArray1288[3] + anIntArray1288[4]) / (anIntArray1284[3] + anIntArray1284[4]);
        int j4 = 0;
        if (anIntArray1284[6] > 0 || anIntArray1284[8] > 0)
            j4 = (anIntArray1288[6] + anIntArray1288[8]) / (anIntArray1284[6] + anIntArray1284[8]);
        int i6 = 0;
        int k6 = anIntArray1284[10];
        int[] ai2 = anIntArrayArray1285[10];
        int[] ai3 = anIntArray1286;
        if (i6 == k6) {
            i6 = 0;
            k6 = anIntArray1284[11];
            ai2 = anIntArrayArray1285[11];
            ai3 = anIntArray1287;
        }
        int i5;
        if (i6 < k6)
            i5 = ai3[i6];
        else
            i5 = -1000;
        for (int l6 = 0; l6 < 10; l6++) {
            while (l6 == 0 && i5 > l2) {
                method373(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1285[11]) {
                    i6 = 0;
                    k6 = anIntArray1284[11];
                    ai2 = anIntArrayArray1285[11];
                    ai3 = anIntArray1287;
                }
                if (i6 < k6)
                    i5 = ai3[i6];
                else
                    i5 = -1000;
            }
            while (l6 == 3 && i5 > k3) {
                method373(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1285[11]) {
                    i6 = 0;
                    k6 = anIntArray1284[11];
                    ai2 = anIntArrayArray1285[11];
                    ai3 = anIntArray1287;
                }
                if (i6 < k6)
                    i5 = ai3[i6];
                else
                    i5 = -1000;
            }
            while (l6 == 5 && i5 > j4) {
                method373(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1285[11]) {
                    i6 = 0;
                    k6 = anIntArray1284[11];
                    ai2 = anIntArrayArray1285[11];
                    ai3 = anIntArray1287;
                }
                if (i6 < k6)
                    i5 = ai3[i6];
                else
                    i5 = -1000;
            }
            int i7 = anIntArray1284[l6];
            int[] ai4 = anIntArrayArray1285[l6];
            for (int j7 = 0; j7 < i7; j7++)
                method373(ai4[j7]);

        }

        while (i5 != -1000) {
            method373(ai2[i6++]);
            if (i6 == k6 && ai2 != anIntArrayArray1285[11]) {
                i6 = 0;
                ai2 = anIntArrayArray1285[11];
                k6 = anIntArray1284[11];
                ai3 = anIntArray1287;
            }
            if (i6 < k6)
                i5 = ai3[i6];
            else
                i5 = -1000;
        }
    }

    public void method373(int i) {
        if (aBooleanArray1275[i]) {
            method374(i);
            return;
        }
        int j = anIntArray1227[i];
        int k = anIntArray1228[i];
        int l = anIntArray1229[i];
        Class38_Sub2_Sub2_Sub1.aBoolean1438 = aBooleanArray1274[i];
        if (anIntArray1235 == null)
            Class38_Sub2_Sub2_Sub1.anInt1441 = 0;
        else
            Class38_Sub2_Sub2_Sub1.anInt1441 = anIntArray1235[i];
        int i1;
        if (anIntArray1233 == null)
            i1 = 0;
        else
            i1 = anIntArray1233[i] & 3;
        if (i1 == 0) {
            Class38_Sub2_Sub2_Sub1.method395(anIntArray1277[j], anIntArray1277[k], anIntArray1277[l], anIntArray1276[j],
                    anIntArray1276[k], anIntArray1276[l], anIntArray1230[i], anIntArray1231[i], anIntArray1232[i]);
            return;
        }
        if (i1 == 1) {
            Class38_Sub2_Sub2_Sub1.method397(anIntArray1277[j], anIntArray1277[k], anIntArray1277[l], anIntArray1276[j],
                    anIntArray1276[k], anIntArray1276[l], anIntArray1302[anIntArray1230[i]]);
            return;
        }
        if (i1 == 2) {
            int j1 = anIntArray1233[i] >> 2;
            int l1 = anIntArray1239[j1];
            int j2 = anIntArray1240[j1];
            int l2 = anIntArray1241[j1];
            Class38_Sub2_Sub2_Sub1.method399(anIntArray1277[j], anIntArray1277[k], anIntArray1277[l], anIntArray1276[j],
                    anIntArray1276[k], anIntArray1276[l], anIntArray1230[i], anIntArray1231[i], anIntArray1232[i],
                    anIntArray1279[l1], anIntArray1279[j2], anIntArray1279[l2], anIntArray1280[l1], anIntArray1280[j2],
                    anIntArray1280[l2], anIntArray1281[l1], anIntArray1281[j2], anIntArray1281[l2], anIntArray1236[i]);
            return;
        }
        if (i1 == 3) {
            int k1 = anIntArray1233[i] >> 2;
            int i2 = anIntArray1239[k1];
            int k2 = anIntArray1240[k1];
            int i3 = anIntArray1241[k1];
            Class38_Sub2_Sub2_Sub1.method399(anIntArray1277[j], anIntArray1277[k], anIntArray1277[l], anIntArray1276[j],
                    anIntArray1276[k], anIntArray1276[l], anIntArray1230[i], anIntArray1230[i], anIntArray1230[i],
                    anIntArray1279[i2], anIntArray1279[k2], anIntArray1279[i3], anIntArray1280[i2], anIntArray1280[k2],
                    anIntArray1280[i3], anIntArray1281[i2], anIntArray1281[k2], anIntArray1281[i3], anIntArray1236[i]);
        }
    }

    public void method374(int i) {
        int j = Class38_Sub2_Sub2_Sub1.anInt1442;
        int k = Class38_Sub2_Sub2_Sub1.anInt1443;
        int l = 0;
        int i1 = anIntArray1227[i];
        int j1 = anIntArray1228[i];
        int k1 = anIntArray1229[i];
        int l1 = anIntArray1281[i1];
        int i2 = anIntArray1281[j1];
        int j2 = anIntArray1281[k1];
        if (l1 >= 50) {
            anIntArray1289[l] = anIntArray1276[i1];
            anIntArray1290[l] = anIntArray1277[i1];
            anIntArray1291[l++] = anIntArray1230[i];
        } else {
            int k2 = anIntArray1279[i1];
            int k3 = anIntArray1280[i1];
            int k4 = anIntArray1230[i];
            if (j2 >= 50) {
                int k5 = (50 - l1) * anIntArray1303[j2 - l1];
                anIntArray1289[l] = j + (k2 + ((anIntArray1279[k1] - k2) * k5 >> 16) << 9) / 50;
                anIntArray1290[l] = k + (k3 + ((anIntArray1280[k1] - k3) * k5 >> 16) << 9) / 50;
                anIntArray1291[l++] = k4 + ((anIntArray1232[i] - k4) * k5 >> 16);
            }
            if (i2 >= 50) {
                int l5 = (50 - l1) * anIntArray1303[i2 - l1];
                anIntArray1289[l] = j + (k2 + ((anIntArray1279[j1] - k2) * l5 >> 16) << 9) / 50;
                anIntArray1290[l] = k + (k3 + ((anIntArray1280[j1] - k3) * l5 >> 16) << 9) / 50;
                anIntArray1291[l++] = k4 + ((anIntArray1231[i] - k4) * l5 >> 16);
            }
        }
        if (i2 >= 50) {
            anIntArray1289[l] = anIntArray1276[j1];
            anIntArray1290[l] = anIntArray1277[j1];
            anIntArray1291[l++] = anIntArray1231[i];
        } else {
            int l2 = anIntArray1279[j1];
            int l3 = anIntArray1280[j1];
            int l4 = anIntArray1231[i];
            if (l1 >= 50) {
                int i6 = (50 - i2) * anIntArray1303[l1 - i2];
                anIntArray1289[l] = j + (l2 + ((anIntArray1279[i1] - l2) * i6 >> 16) << 9) / 50;
                anIntArray1290[l] = k + (l3 + ((anIntArray1280[i1] - l3) * i6 >> 16) << 9) / 50;
                anIntArray1291[l++] = l4 + ((anIntArray1230[i] - l4) * i6 >> 16);
            }
            if (j2 >= 50) {
                int j6 = (50 - i2) * anIntArray1303[j2 - i2];
                anIntArray1289[l] = j + (l2 + ((anIntArray1279[k1] - l2) * j6 >> 16) << 9) / 50;
                anIntArray1290[l] = k + (l3 + ((anIntArray1280[k1] - l3) * j6 >> 16) << 9) / 50;
                anIntArray1291[l++] = l4 + ((anIntArray1232[i] - l4) * j6 >> 16);
            }
        }
        if (j2 >= 50) {
            anIntArray1289[l] = anIntArray1276[k1];
            anIntArray1290[l] = anIntArray1277[k1];
            anIntArray1291[l++] = anIntArray1232[i];
        } else {
            int i3 = anIntArray1279[k1];
            int i4 = anIntArray1280[k1];
            int i5 = anIntArray1232[i];
            if (i2 >= 50) {
                int k6 = (50 - j2) * anIntArray1303[i2 - j2];
                anIntArray1289[l] = j + (i3 + ((anIntArray1279[j1] - i3) * k6 >> 16) << 9) / 50;
                anIntArray1290[l] = k + (i4 + ((anIntArray1280[j1] - i4) * k6 >> 16) << 9) / 50;
                anIntArray1291[l++] = i5 + ((anIntArray1231[i] - i5) * k6 >> 16);
            }
            if (l1 >= 50) {
                int l6 = (50 - j2) * anIntArray1303[l1 - j2];
                anIntArray1289[l] = j + (i3 + ((anIntArray1279[i1] - i3) * l6 >> 16) << 9) / 50;
                anIntArray1290[l] = k + (i4 + ((anIntArray1280[i1] - i4) * l6 >> 16) << 9) / 50;
                anIntArray1291[l++] = i5 + ((anIntArray1230[i] - i5) * l6 >> 16);
            }
        }
        int j3 = anIntArray1289[0];
        int j4 = anIntArray1289[1];
        int j5 = anIntArray1289[2];
        int i7 = anIntArray1290[0];
        int j7 = anIntArray1290[1];
        int k7 = anIntArray1290[2];
        if ((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
            Class38_Sub2_Sub2_Sub1.aBoolean1438 = false;
            if (l == 3) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > Class38_Sub2_Sub2.anInt1315 || j4 > Class38_Sub2_Sub2.anInt1315
                        || j5 > Class38_Sub2_Sub2.anInt1315)
                    Class38_Sub2_Sub2_Sub1.aBoolean1438 = true;
                int l7;
                if (anIntArray1233 == null)
                    l7 = 0;
                else
                    l7 = anIntArray1233[i] & 3;
                if (l7 == 0)
                    Class38_Sub2_Sub2_Sub1.method395(i7, j7, k7, j3, j4, j5, anIntArray1291[0], anIntArray1291[1],
                            anIntArray1291[2]);
                else if (l7 == 1)
                    Class38_Sub2_Sub2_Sub1.method397(i7, j7, k7, j3, j4, j5, anIntArray1302[anIntArray1230[i]]);
                else if (l7 == 2) {
                    int j8 = anIntArray1233[i] >> 2;
                    int k9 = anIntArray1239[j8];
                    int k10 = anIntArray1240[j8];
                    int k11 = anIntArray1241[j8];
                    Class38_Sub2_Sub2_Sub1.method399(i7, j7, k7, j3, j4, j5, anIntArray1291[0], anIntArray1291[1],
                            anIntArray1291[2], anIntArray1279[k9], anIntArray1279[k10], anIntArray1279[k11],
                            anIntArray1280[k9], anIntArray1280[k10], anIntArray1280[k11], anIntArray1281[k9],
                            anIntArray1281[k10], anIntArray1281[k11], anIntArray1236[i]);
                } else if (l7 == 3) {
                    int k8 = anIntArray1233[i] >> 2;
                    int l9 = anIntArray1239[k8];
                    int l10 = anIntArray1240[k8];
                    int l11 = anIntArray1241[k8];
                    Class38_Sub2_Sub2_Sub1.method399(i7, j7, k7, j3, j4, j5, anIntArray1230[i], anIntArray1230[i],
                            anIntArray1230[i], anIntArray1279[l9], anIntArray1279[l10], anIntArray1279[l11],
                            anIntArray1280[l9], anIntArray1280[l10], anIntArray1280[l11], anIntArray1281[l9],
                            anIntArray1281[l10], anIntArray1281[l11], anIntArray1236[i]);
                }
            }
            if (l == 4) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > Class38_Sub2_Sub2.anInt1315 || j4 > Class38_Sub2_Sub2.anInt1315
                        || j5 > Class38_Sub2_Sub2.anInt1315 || anIntArray1289[3] < 0
                        || anIntArray1289[3] > Class38_Sub2_Sub2.anInt1315)
                    Class38_Sub2_Sub2_Sub1.aBoolean1438 = true;
                int i8;
                if (anIntArray1233 == null)
                    i8 = 0;
                else
                    i8 = anIntArray1233[i] & 3;
                if (i8 == 0) {
                    Class38_Sub2_Sub2_Sub1.method395(i7, j7, k7, j3, j4, j5, anIntArray1291[0], anIntArray1291[1],
                            anIntArray1291[2]);
                    Class38_Sub2_Sub2_Sub1.method395(i7, k7, anIntArray1290[3], j3, j5, anIntArray1289[3],
                            anIntArray1291[0], anIntArray1291[2], anIntArray1291[3]);
                    return;
                }
                if (i8 == 1) {
                    int l8 = anIntArray1302[anIntArray1230[i]];
                    Class38_Sub2_Sub2_Sub1.method397(i7, j7, k7, j3, j4, j5, l8);
                    Class38_Sub2_Sub2_Sub1.method397(i7, k7, anIntArray1290[3], j3, j5, anIntArray1289[3], l8);
                    return;
                }
                if (i8 == 2) {
                    int i9 = anIntArray1233[i] >> 2;
                    int i10 = anIntArray1239[i9];
                    int i11 = anIntArray1240[i9];
                    int i12 = anIntArray1241[i9];
                    Class38_Sub2_Sub2_Sub1.method399(i7, j7, k7, j3, j4, j5, anIntArray1291[0], anIntArray1291[1],
                            anIntArray1291[2], anIntArray1279[i10], anIntArray1279[i11], anIntArray1279[i12],
                            anIntArray1280[i10], anIntArray1280[i11], anIntArray1280[i12], anIntArray1281[i10],
                            anIntArray1281[i11], anIntArray1281[i12], anIntArray1236[i]);
                    Class38_Sub2_Sub2_Sub1.method399(i7, k7, anIntArray1290[3], j3, j5, anIntArray1289[3],
                            anIntArray1291[0], anIntArray1291[2], anIntArray1291[3], anIntArray1279[i10],
                            anIntArray1279[i11], anIntArray1279[i12], anIntArray1280[i10], anIntArray1280[i11],
                            anIntArray1280[i12], anIntArray1281[i10], anIntArray1281[i11], anIntArray1281[i12],
                            anIntArray1236[i]);
                    return;
                }
                if (i8 == 3) {
                    int j9 = anIntArray1233[i] >> 2;
                    int j10 = anIntArray1239[j9];
                    int j11 = anIntArray1240[j9];
                    int j12 = anIntArray1241[j9];
                    Class38_Sub2_Sub2_Sub1.method399(i7, j7, k7, j3, j4, j5, anIntArray1230[i], anIntArray1230[i],
                            anIntArray1230[i], anIntArray1279[j10], anIntArray1279[j11], anIntArray1279[j12],
                            anIntArray1280[j10], anIntArray1280[j11], anIntArray1280[j12], anIntArray1281[j10],
                            anIntArray1281[j11], anIntArray1281[j12], anIntArray1236[i]);
                    Class38_Sub2_Sub2_Sub1.method399(i7, k7, anIntArray1290[3], j3, j5, anIntArray1289[3],
                            anIntArray1230[i], anIntArray1230[i], anIntArray1230[i], anIntArray1279[j10],
                            anIntArray1279[j11], anIntArray1279[j12], anIntArray1280[j10], anIntArray1280[j11],
                            anIntArray1280[j12], anIntArray1281[j10], anIntArray1281[j11], anIntArray1281[j12],
                            anIntArray1236[i]);
                }
            }
        }
    }

    public boolean method375(int i, int j, int k, int l, int i1, int j1, int k1,
                             int l1) {
        if (j < k && j < l && j < i1)
            return false;
        if (j > k && j > l && j > i1)
            return false;
        if (i < j1 && i < k1 && i < l1)
            return false;
        return i <= j1 || i <= k1 || i <= l1;
    }

    public static boolean aBoolean1216;
    public int anInt1217;
    public byte aByte1218;
    public byte aByte1219;
    public int anInt1220;
    public boolean aBoolean1221;
    public int anInt1222;
    public int[] anIntArray1223;
    public int[] anIntArray1224;
    public int[] anIntArray1225;
    public int anInt1226;
    public int[] anIntArray1227;
    public int[] anIntArray1228;
    public int[] anIntArray1229;
    public int[] anIntArray1230;
    public int[] anIntArray1231;
    public int[] anIntArray1232;
    public int[] anIntArray1233;
    public int[] anIntArray1234;
    public int[] anIntArray1235;
    public int[] anIntArray1236;
    public int anInt1237;
    public int anInt1238;
    public int[] anIntArray1239;
    public int[] anIntArray1240;
    public int[] anIntArray1241;
    public int anInt1242;
    public int anInt1243;
    public int anInt1244;
    public int anInt1245;
    public int anInt1246;
    public int anInt1247;
    public int anInt1248;
    public int anInt1249;
    public int anInt1250;
    public int anInt1251;
    public int[] anIntArray1252;
    public int[] anIntArray1253;
    public int[][] anIntArrayArray1254;
    public int[][] anIntArrayArray1255;
    public boolean aBoolean1256;
    public Class25[] aClass25Array1257;
    public Class25[] aClass25Array1258;
    public static Class21[] aClass21Array1259;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1260;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1261;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1262;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1263;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1264;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1265;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1266;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1267;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1268;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1269;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1270;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1271;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1272;
    public static Class38_Sub2_Sub3 aClass38_Sub2_Sub3_1273;
    public static boolean[] aBooleanArray1274 = new boolean[4096];
    public static boolean[] aBooleanArray1275 = new boolean[4096];
    public static int[] anIntArray1276 = new int[4096];
    public static int[] anIntArray1277 = new int[4096];
    public static int[] anIntArray1278 = new int[4096];
    public static int[] anIntArray1279 = new int[4096];
    public static int[] anIntArray1280 = new int[4096];
    public static int[] anIntArray1281 = new int[4096];
    public static int[] anIntArray1282 = new int[1500];
    public static int[][] anIntArrayArray1283 = new int[1500][512];
    public static int[] anIntArray1284 = new int[12];
    public static int[][] anIntArrayArray1285 = new int[12][2000];
    public static int[] anIntArray1286 = new int[2000];
    public static int[] anIntArray1287 = new int[2000];
    public static int[] anIntArray1288 = new int[12];
    public static int[] anIntArray1289 = new int[10];
    public static int[] anIntArray1290 = new int[10];
    public static int[] anIntArray1291 = new int[10];
    public static int anInt1292;
    public static int anInt1293;
    public static int anInt1294;
    public static boolean aBoolean1295;
    public static int anInt1296;
    public static int anInt1297;
    public static int anInt1298;
    public static int[] anIntArray1299 = new int[1000];
    public static int[] anIntArray1300;
    public static int[] anIntArray1301;
    public static int[] anIntArray1302;
    public static int[] anIntArray1303;
    public static boolean aBoolean1304;

    static {
        anIntArray1300 = Class38_Sub2_Sub2_Sub1.anIntArray1446;
        anIntArray1301 = Class38_Sub2_Sub2_Sub1.anIntArray1447;
        anIntArray1302 = Class38_Sub2_Sub2_Sub1.anIntArray1458;
        anIntArray1303 = Class38_Sub2_Sub2_Sub1.anIntArray1445;
    }
}
