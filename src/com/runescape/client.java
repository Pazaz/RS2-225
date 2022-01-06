package com.runescape;

import com.runescape.sign.signlink;
import net.burtleburtle.bob.rand.IsaacRandom;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.zip.CRC32;

public class client extends Applet_Sub1 {

    public void method14(boolean flag, int i, String s, int j) {
        if (s == null)
            return;
        synchronized (anObject1123) {
            aString1064 = s;
            anInt963 = i;
            anInt726 = j;
        }
        if (flag)
            anInt780 = -1;
    }

    public void method15(int i) {
        anInt1091 = 0;
        while (i >= 0)
            anInt883 = isaacState.nextInt();
        for (int j = -1; j < anInt823 + anInt928; j++) {
            Object obj;
            if (j == -1)
                obj = aClass38_Sub7_Sub3_Sub2_967;
            else if (j < anInt823)
                obj = aClass38_Sub7_Sub3_Sub2Array822[anIntArray824[j]];
            else
                obj = aClass38_Sub7_Sub3_Sub1Array927[anIntArray929[j - anInt823]];
            if (obj != null && ((Class38_Sub7_Sub3) (obj)).method468(false)) {
                if (j < anInt823) {
                    int l = 30;
                    Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = (Class38_Sub7_Sub3_Sub2) obj;
                    if (class38_sub7_sub3_sub2.anInt1508 != 0) {
                        method90(((Class38_Sub7_Sub3) (obj)).anInt1425 + 15, aBoolean860, ((Class38_Sub7_Sub3) (obj)));
                        if (anInt1019 > -1) {
                            for (int l1 = 0; l1 < 8; l1++)
                                if ((class38_sub7_sub3_sub2.anInt1508 & 1 << l1) != 0) {
                                    aClass38_Sub2_Sub2_Sub2Array956[l1].method405(anInt1020 - l, anInt1019 - 12, false);
                                    l -= 25;
                                }

                        }
                    }
                    if (j >= 0 && anInt911 == 10 && anInt1076 == anIntArray824[j]) {
                        method90(((Class38_Sub7_Sub3) (obj)).anInt1425 + 15, aBoolean860, ((Class38_Sub7_Sub3) (obj)));
                        if (anInt1019 > -1)
                            aClass38_Sub2_Sub2_Sub2Array956[7].method405(anInt1020 - l, anInt1019 - 12, false);
                    }
                } else if (anInt911 == 1 && anInt801 == anIntArray929[j - anInt823] && anInt955 % 20 < 10) {
                    method90(((Class38_Sub7_Sub3) (obj)).anInt1425 + 15, aBoolean860, ((Class38_Sub7_Sub3) (obj)));
                    if (anInt1019 > -1)
                        aClass38_Sub2_Sub2_Sub2Array956[2].method405(anInt1020 - 28, anInt1019 - 12, false);
                }
                if (((Class38_Sub7_Sub3) (obj)).aString1392 != null && (j >= anInt823 || anInt976 == 0 || anInt976 == 3
                        || anInt976 == 1 && method138(-20, ((Class38_Sub7_Sub3_Sub2) obj).aString1505))) {
                    method90(((Class38_Sub7_Sub3) (obj)).anInt1425, aBoolean860, ((Class38_Sub7_Sub3) (obj)));
                    if (anInt1019 > -1 && anInt1091 < anInt1092) {
                        anIntArray1096[anInt1091] = aClass38_Sub2_Sub2_Sub4_987.method423(false,
                                ((Class38_Sub7_Sub3) (obj)).aString1392) / 2;
                        anIntArray1095[anInt1091] = aClass38_Sub2_Sub2_Sub4_987.anInt1497;
                        anIntArray1093[anInt1091] = anInt1019;
                        anIntArray1094[anInt1091] = anInt1020;
                        anIntArray1097[anInt1091] = ((Class38_Sub7_Sub3) (obj)).anInt1394;
                        anIntArray1098[anInt1091] = ((Class38_Sub7_Sub3) (obj)).anInt1395;
                        anIntArray1099[anInt1091] = ((Class38_Sub7_Sub3) (obj)).anInt1393;
                        aStringArray1100[anInt1091++] = ((Class38_Sub7_Sub3) (obj)).aString1392;
                        if (anInt800 == 0 && ((Class38_Sub7_Sub3) (obj)).anInt1395 == 1) {
                            anIntArray1095[anInt1091] += 10;
                            anIntArray1094[anInt1091] += 5;
                        }
                        if (anInt800 == 0 && ((Class38_Sub7_Sub3) (obj)).anInt1395 == 2)
                            anIntArray1096[anInt1091] = 60;
                    }
                }
                if (((Class38_Sub7_Sub3) (obj)).anInt1398 > anInt955 + 100) {
                    method90(((Class38_Sub7_Sub3) (obj)).anInt1425 + 15, aBoolean860, ((Class38_Sub7_Sub3) (obj)));
                    if (anInt1019 > -1) {
                        int i1 = (((Class38_Sub7_Sub3) (obj)).anInt1399 * 30) / ((Class38_Sub7_Sub3) (obj)).anInt1400;
                        if (i1 > 30)
                            i1 = 30;
                        Class38_Sub2_Sub2.method380(anInt1020 - 3, anInt1019 - 15, 65280, (byte) 93, i1, 5);
                        Class38_Sub2_Sub2.method380(anInt1020 - 3, (anInt1019 - 15) + i1, 0xff0000, (byte) 93, 30 - i1,
                                5);
                    }
                }
                if (((Class38_Sub7_Sub3) (obj)).anInt1398 > anInt955 + 330) {
                    method90(((Class38_Sub7_Sub3) (obj)).anInt1425 / 2, aBoolean860, ((Class38_Sub7_Sub3) (obj)));
                    if (anInt1019 > -1) {
                        aClass38_Sub2_Sub2_Sub2Array776[((Class38_Sub7_Sub3) (obj)).anInt1397].method405(anInt1020 - 12,
                                anInt1019 - 12, false);
                        aClass38_Sub2_Sub2_Sub4_985.method421(anInt1020 + 4, (byte) 6, 0,
                                String.valueOf(((Class38_Sub7_Sub3) (obj)).anInt1396), anInt1019);
                        aClass38_Sub2_Sub2_Sub4_985.method421(anInt1020 + 3, (byte) 6, 0xffffff,
                                String.valueOf(((Class38_Sub7_Sub3) (obj)).anInt1396), anInt1019 - 1);
                    }
                }
            }
        }

        for (int k = 0; k < anInt1091; k++) {
            int j1 = anIntArray1093[k];
            int k1 = anIntArray1094[k];
            int i2 = anIntArray1096[k];
            int j2 = anIntArray1095[k];
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int k2 = 0; k2 < k; k2++)
                    if (k1 + 2 > anIntArray1094[k2] - anIntArray1095[k2] && k1 - j2 < anIntArray1094[k2] + 2
                            && j1 - i2 < anIntArray1093[k2] + anIntArray1096[k2]
                            && j1 + i2 > anIntArray1093[k2] - anIntArray1096[k2]
                            && anIntArray1094[k2] - anIntArray1095[k2] < k1) {
                        k1 = anIntArray1094[k2] - anIntArray1095[k2];
                        flag = true;
                    }

            }
            anInt1019 = anIntArray1093[k];
            anInt1020 = anIntArray1094[k] = k1;
            String s = aStringArray1100[k];
            if (anInt800 == 0) {
                int l2 = 0xffff00;
                if (anIntArray1097[k] < 6)
                    l2 = anIntArray1045[anIntArray1097[k]];
                if (anIntArray1097[k] == 6)
                    l2 = anInt837 % 20 >= 10 ? 0xffff00 : 0xff0000;
                if (anIntArray1097[k] == 7)
                    l2 = anInt837 % 20 >= 10 ? 65535 : 255;
                if (anIntArray1097[k] == 8)
                    l2 = anInt837 % 20 >= 10 ? 0x80ff80 : 45056;
                if (anIntArray1097[k] == 9) {
                    int i3 = 150 - anIntArray1099[k];
                    if (i3 < 50)
                        l2 = 0xff0000 + 1280 * i3;
                    else if (i3 < 100)
                        l2 = 0xffff00 - 0x50000 * (i3 - 50);
                    else if (i3 < 150)
                        l2 = 65280 + 5 * (i3 - 100);
                }
                if (anIntArray1097[k] == 10) {
                    int j3 = 150 - anIntArray1099[k];
                    if (j3 < 50)
                        l2 = 0xff0000 + 5 * j3;
                    else if (j3 < 100)
                        l2 = 0xff00ff - 0x50000 * (j3 - 50);
                    else if (j3 < 150)
                        l2 = (255 + 0x50000 * (j3 - 100)) - 5 * (j3 - 100);
                }
                if (anIntArray1097[k] == 11) {
                    int k3 = 150 - anIntArray1099[k];
                    if (k3 < 50)
                        l2 = 0xffffff - 0x50005 * k3;
                    else if (k3 < 100)
                        l2 = 65280 + 0x50005 * (k3 - 50);
                    else if (k3 < 150)
                        l2 = 0xffffff - 0x50000 * (k3 - 100);
                }
                if (anIntArray1098[k] == 0) {
                    aClass38_Sub2_Sub2_Sub4_987.method421(anInt1020 + 1, (byte) 6, 0, s, anInt1019);
                    aClass38_Sub2_Sub2_Sub4_987.method421(anInt1020, (byte) 6, l2, s, anInt1019);
                }
                if (anIntArray1098[k] == 1) {
                    aClass38_Sub2_Sub2_Sub4_987.method425(anInt837, (byte) 8, anInt1019, anInt1020 + 1, 0, s);
                    aClass38_Sub2_Sub2_Sub4_987.method425(anInt837, (byte) 8, anInt1019, anInt1020, l2, s);
                }
                if (anIntArray1098[k] == 2) {
                    int l3 = aClass38_Sub2_Sub2_Sub4_987.method423(false, s);
                    int i4 = ((150 - anIntArray1099[k]) * (l3 + 100)) / 150;
                    Class38_Sub2_Sub2.method378(334, 0, anInt1019 + 50, 789, anInt1019 - 50);
                    aClass38_Sub2_Sub2_Sub4_987.method424((anInt1019 + 50) - i4, anInt1020 + 1, false, 0, s);
                    aClass38_Sub2_Sub2_Sub4_987.method424((anInt1019 + 50) - i4, anInt1020, false, l2, s);
                    Class38_Sub2_Sub2.method377(0);
                }
            } else {
                aClass38_Sub2_Sub2_Sub4_987.method421(anInt1020 + 1, (byte) 6, 0, s, anInt1019);
                aClass38_Sub2_Sub2_Sub4_987.method421(anInt1020, (byte) 6, 0xffff00, s, anInt1019);
            }
        }

    }

    public void method16(byte byte0) {
        if (byte0 != -60)
            linkedList3dArray = null;
        aClass38_Sub2_Sub3_798.method435((byte) -34, 231);
        if (anInt1129 != -1) {
            anInt1129 = -1;
            aBoolean964 = true;
            aBoolean872 = false;
            aBoolean1080 = true;
        }
        if (anInt1001 != -1) {
            anInt1001 = -1;
            aBoolean965 = true;
            aBoolean872 = false;
        }
        anInt971 = -1;
    }

    public void method17(int i) {
        if (i != 0)
            method6();
        signlink.midifade = 0;
        signlink.midi = "stop";
    }

    public void method18(int i) {
        int j = (aClass38_Sub7_Sub3_Sub2_967.anInt1380 >> 7) + anInt761;
        int k = (aClass38_Sub7_Sub3_Sub2_967.anInt1381 >> 7) + anInt762;
        if (i != 39734)
            aBoolean912 = !aBoolean912;
        if (j >= 2944 && j < 3392 && k >= 3520 && k < 6400)
            anInt1101 = 1 + (k - 3520) / 8;
        else if (j >= 2944 && j < 3392 && k >= 9920 && k < 12800)
            anInt1101 = 1 + (k - 9920) / 8;
        else
            anInt1101 = 0;
        anInt933 = 0;
        if (j >= 3328 && j < 3392 && k >= 3200 && k < 3264) {
            int l = j & 0x3f;
            int i1 = k & 0x3f;
            if (l >= 4 && l <= 29 && i1 >= 44 && i1 <= 58)
                anInt933 = 1;
            if (l >= 36 && l <= 61 && i1 >= 44 && i1 <= 58)
                anInt933 = 1;
            if (l >= 4 && l <= 29 && i1 >= 25 && i1 <= 39)
                anInt933 = 1;
            if (l >= 36 && l <= 61 && i1 >= 25 && i1 <= 39)
                anInt933 = 1;
            if (l >= 4 && l <= 29 && i1 >= 6 && i1 <= 20)
                anInt933 = 1;
            if (l >= 36 && l <= 61 && i1 >= 6 && i1 <= 20)
                anInt933 = 1;
        }
        if (anInt933 == 0 && j >= 3328 && j <= 3393 && k >= 3203 && k <= 3325)
            anInt933 = 2;
        anInt802 = 0;
        if (j >= 3053 && j <= 3156 && k >= 3056 && k <= 3136)
            anInt802 = 1;
        if (j >= 3072 && j <= 3118 && k >= 9492 && k <= 9535)
            anInt802 = 1;
        if (anInt802 == 1 && j >= 3139 && j <= 3199 && k >= 3008 && k <= 3062)
            anInt802 = 0;
    }

    public void method19(int i) {
        if (i <= 0)
            return;
        if (anInt833 == 0)
            return;
        Class38_Sub2_Sub2_Sub4 class38_sub2_sub2_sub4 = aClass38_Sub2_Sub2_Sub4_986;
        int j = 0;
        if (anInt957 != 0)
            j = 1;
        for (int k = 0; k < 100; k++)
            if (aStringArray898[k] != null) {
                int l = anIntArray896[k];
                if ((l == 3 || l == 7)
                        && (l == 7 || anInt755 == 0 || anInt755 == 1 && method138(-20, aStringArray897[k]))) {
                    int i1 = 329 - j * 13;
                    class38_sub2_sub2_sub4.method424(4, i1, false, 0,
                            "From " + aStringArray897[k] + ": " + aStringArray898[k]);
                    class38_sub2_sub2_sub4.method424(4, i1 - 1, false, 65535,
                            "From " + aStringArray897[k] + ": " + aStringArray898[k]);
                    if (++j >= 5)
                        return;
                }
                if (l == 5 && anInt755 < 2) {
                    int j1 = 329 - j * 13;
                    class38_sub2_sub2_sub4.method424(4, j1, false, 0, aStringArray898[k]);
                    class38_sub2_sub2_sub4.method424(4, j1 - 1, false, 65535, aStringArray898[k]);
                    if (++j >= 5)
                        return;
                }
                if (l == 6 && anInt755 < 2) {
                    int k1 = 329 - j * 13;
                    class38_sub2_sub2_sub4.method424(4, k1, false, 0,
                            "To " + aStringArray897[k] + ": " + aStringArray898[k]);
                    class38_sub2_sub2_sub4.method424(4, k1 - 1, false, 65535,
                            "To " + aStringArray897[k] + ": " + aStringArray898[k]);
                    if (++j >= 5)
                        return;
                }
            }

    }

    public void method20(Class38_Sub2_Sub3 class38_sub2_sub3, int i, int j) {
        for (int k = 0; k < anInt825; k++) {
            int l = anIntArray826[k];
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[l];
            int i1 = class38_sub2_sub3.method446();
            if ((i1 & 2) == 2) {
                int j1 = class38_sub2_sub3.method448();
                if (j1 == 65535)
                    j1 = -1;
                if (j1 == class38_sub7_sub3_sub1.anInt1407)
                    class38_sub7_sub3_sub1.anInt1411 = 0;
                int l1 = class38_sub2_sub3.method446();
                if (j1 == -1 || class38_sub7_sub3_sub1.anInt1407 == -1
                        || SeqType.seqTypes[j1].anInt372 > SeqType.seqTypes[class38_sub7_sub3_sub1.anInt1407].anInt372
                        || SeqType.seqTypes[class38_sub7_sub3_sub1.anInt1407].anInt372 == 0) {
                    class38_sub7_sub3_sub1.anInt1407 = j1;
                    class38_sub7_sub3_sub1.anInt1408 = 0;
                    class38_sub7_sub3_sub1.anInt1409 = 0;
                    class38_sub7_sub3_sub1.anInt1410 = l1;
                    class38_sub7_sub3_sub1.anInt1411 = 0;
                }
            }
            if ((i1 & 4) == 4) {
                class38_sub7_sub3_sub1.anInt1401 = class38_sub2_sub3.method448();
                if (class38_sub7_sub3_sub1.anInt1401 == 65535)
                    class38_sub7_sub3_sub1.anInt1401 = -1;
            }
            if ((i1 & 8) == 8) {
                class38_sub7_sub3_sub1.aString1392 = class38_sub2_sub3.method453();
                class38_sub7_sub3_sub1.anInt1393 = 100;
            }
            if ((i1 & 0x10) == 16) {
                class38_sub7_sub3_sub1.anInt1396 = class38_sub2_sub3.method446();
                class38_sub7_sub3_sub1.anInt1397 = class38_sub2_sub3.method446();
                class38_sub7_sub3_sub1.anInt1398 = anInt955 + 400;
                class38_sub7_sub3_sub1.anInt1399 = class38_sub2_sub3.method446();
                class38_sub7_sub3_sub1.anInt1400 = class38_sub2_sub3.method446();
            }
            if ((i1 & 0x20) == 32) {
                class38_sub7_sub3_sub1.npcType = NpcType.method148(class38_sub2_sub3.method448());
                class38_sub7_sub3_sub1.anInt1387 = class38_sub7_sub3_sub1.npcType.anInt89;
                class38_sub7_sub3_sub1.anInt1388 = class38_sub7_sub3_sub1.npcType.anInt90;
                class38_sub7_sub3_sub1.anInt1389 = class38_sub7_sub3_sub1.npcType.anInt91;
                class38_sub7_sub3_sub1.anInt1390 = class38_sub7_sub3_sub1.npcType.anInt92;
                class38_sub7_sub3_sub1.anInt1385 = class38_sub7_sub3_sub1.npcType.anInt88;
            }
            if ((i1 & 0x40) == 64) {
                class38_sub7_sub3_sub1.anInt1412 = class38_sub2_sub3.method448();
                int k1 = class38_sub2_sub3.method451();
                class38_sub7_sub3_sub1.anInt1416 = k1 >> 16;
                class38_sub7_sub3_sub1.anInt1415 = anInt955 + (k1 & 0xffff);
                class38_sub7_sub3_sub1.anInt1413 = 0;
                class38_sub7_sub3_sub1.anInt1414 = 0;
                if (class38_sub7_sub3_sub1.anInt1415 > anInt955)
                    class38_sub7_sub3_sub1.anInt1413 = -1;
                if (class38_sub7_sub3_sub1.anInt1412 == 65535)
                    class38_sub7_sub3_sub1.anInt1412 = -1;
            }
            if ((i1 & 0x80) == 128) {
                class38_sub7_sub3_sub1.anInt1402 = class38_sub2_sub3.method448();
                class38_sub7_sub3_sub1.anInt1403 = class38_sub2_sub3.method448();
            }
        }

        if (j <= 0)
            anInt756 = isaacState.nextInt();
    }

    public void method21(long l, byte byte0) {
        if (l == 0L)
            return;
        if (anInt793 >= 100) {
            method111(0, "Your ignore list is full. Max of 100 hit", (byte) 4, "");
            return;
        }
        String s = StringUtils.formatName(StringUtils.fromBase37(l));
        for (int i = 0; i < anInt793; i++)
            if (aLongArray768[i] == l) {
                method111(0, s + " is already on your ignore list", (byte) 4, "");
                return;
            }

        for (int j = 0; j < anInt1089; j++)
            if (aLongArray943[j] == l) {
                method111(0, "Please remove " + s + " from your friend list first", (byte) 4, "");
                return;
            }

        aLongArray768[anInt793++] = l;
        aBoolean964 = true;
        if (byte0 != 3) {
            return;
        } else {
            aClass38_Sub2_Sub3_798.method435((byte) -34, 79);
            aClass38_Sub2_Sub3_798.method442(true, l);
            return;
        }
    }

    public void method22(byte byte0, Class38_Sub2_Sub3 class38_sub2_sub3, int i) {
        if (byte0 != -45)
            method6();
        if (i == 59 || i == 76) {
            int j = class38_sub2_sub3.method446();
            int k2 = anInt862 + (j >> 4 & 7);
            int l4 = anInt863 + (j & 7);
            int i7 = class38_sub2_sub3.method446();
            int j9 = i7 >> 2;
            int j11 = i7 & 3;
            int i13 = anIntArray1107[j9];
            int i14;
            if (i == 76)
                i14 = -1;
            else
                i14 = class38_sub2_sub3.method448();
            if (k2 >= 0 && l4 >= 0 && k2 < 104 && l4 < 104) {
                SpawnedLoc spawnedLoc = null;
                for (SpawnedLoc spawnedLoc_1 = (SpawnedLoc) linkedList5
                        .method270(); spawnedLoc_1 != null; spawnedLoc_1 = (SpawnedLoc) linkedList5
                        .method272()) {
                    if (spawnedLoc_1.level != anInt880 || spawnedLoc_1.tileX != k2
                            || spawnedLoc_1.tileZ != l4 || spawnedLoc_1.classType != i13)
                        continue;
                    spawnedLoc = spawnedLoc_1;
                    break;
                }

                if (spawnedLoc == null) {
                    int i16 = 0;
                    int l16 = -1;
                    int j17 = 0;
                    int l17 = 0;
                    if (i13 == 0)
                        i16 = aClass32_831.method308(anInt880, k2, l4);
                    if (i13 == 1)
                        i16 = aClass32_831.method309(anInt880, l4, 3, k2);
                    if (i13 == 2)
                        i16 = aClass32_831.method310(anInt880, k2, l4);
                    if (i13 == 3)
                        i16 = aClass32_831.method311(anInt880, k2, l4);
                    if (i16 != 0) {
                        int i18 = aClass32_831.method312(anInt880, k2, l4, i16);
                        l16 = i16 >> 14 & 0x7fff;
                        j17 = i18 & 0x1f;
                        l17 = i18 >> 6;
                    }
                    spawnedLoc = new SpawnedLoc();
                    spawnedLoc.level = anInt880;
                    spawnedLoc.classType = i13;
                    spawnedLoc.tileX = k2;
                    spawnedLoc.tileZ = l4;
                    spawnedLoc.lastLocIndex = l16;
                    spawnedLoc.lastType = j17;
                    spawnedLoc.lastRotation = l17;
                    linkedList5.method267(spawnedLoc);
                }
                spawnedLoc.locIndex = i14;
                spawnedLoc.type = j9;
                spawnedLoc.rotation = j11;
                method99(j11, k2, l4, i13, i14, j9, -27819, anInt880);
            }
            return;
        }
        if (i == 42) {
            int k = class38_sub2_sub3.method446();
            int l2 = anInt862 + (k >> 4 & 7);
            int i5 = anInt863 + (k & 7);
            int j7 = class38_sub2_sub3.method446();
            int k9 = j7 >> 2;
            int k11 = anIntArray1107[k9];
            int j13 = class38_sub2_sub3.method448();
            if (l2 >= 0 && i5 >= 0 && l2 < 104 && i5 < 104) {
                int j14 = 0;
                if (k11 == 0)
                    j14 = aClass32_831.method308(anInt880, l2, i5);
                if (k11 == 1)
                    j14 = aClass32_831.method309(anInt880, i5, 3, l2);
                if (k11 == 2)
                    j14 = aClass32_831.method310(anInt880, l2, i5);
                if (k11 == 3)
                    j14 = aClass32_831.method311(anInt880, l2, i5);
                if (j14 != 0) {
                    Class38_Sub5 class38_sub5 = new Class38_Sub5(false, j14 >> 14 & 0x7fff, anInt880, 0, k11,
                            SeqType.seqTypes[j13], i5, l2);
                    linkedList2.method267(class38_sub5);
                }
            }
            return;
        }
        if (i == 223) {
            int l = class38_sub2_sub3.method446();
            int i3 = anInt862 + (l >> 4 & 7);
            int j5 = anInt863 + (l & 7);
            int k7 = class38_sub2_sub3.method448();
            int l9 = class38_sub2_sub3.method448();
            if (i3 >= 0 && j5 >= 0 && i3 < 104 && j5 < 104) {
                ObjStackEntity objStackEntity = new ObjStackEntity();
                objStackEntity.model = k7;
                objStackEntity.amount = l9;
                if (linkedList3dArray[anInt880][i3][j5] == null)
                    linkedList3dArray[anInt880][i3][j5] = new LinkedList();
                linkedList3dArray[anInt880][i3][j5].method267(objStackEntity);
                method123(i3, j5);
            }
            return;
        }
        if (i == 49) {
            int i1 = class38_sub2_sub3.method446();
            int j3 = anInt862 + (i1 >> 4 & 7);
            int k5 = anInt863 + (i1 & 7);
            int l7 = class38_sub2_sub3.method448();
            if (j3 >= 0 && k5 >= 0 && j3 < 104 && k5 < 104) {
                LinkedList linkedList = linkedList3dArray[anInt880][j3][k5];
                if (linkedList != null) {
                    for (ObjStackEntity objStackEntity_1 = (ObjStackEntity) linkedList
                            .method270(); objStackEntity_1 != null; objStackEntity_1 = (ObjStackEntity) linkedList
                            .method272()) {
                        if (objStackEntity_1.model != (l7 & 0x7fff))
                            continue;
                        objStackEntity_1.unlink();
                        break;
                    }

                    if (linkedList.method270() == null)
                        linkedList3dArray[anInt880][j3][k5] = null;
                    method123(j3, k5);
                }
            }
            return;
        }
        if (i == 69) {
            int j1 = class38_sub2_sub3.method446();
            int k3 = anInt862 + (j1 >> 4 & 7);
            int l5 = anInt863 + (j1 & 7);
            int i8 = k3 + class38_sub2_sub3.method447();
            int i10 = l5 + class38_sub2_sub3.method447();
            int l11 = class38_sub2_sub3.method449();
            int k13 = class38_sub2_sub3.method448();
            int k14 = class38_sub2_sub3.method446();
            int i15 = class38_sub2_sub3.method446();
            int k15 = class38_sub2_sub3.method448();
            int j16 = class38_sub2_sub3.method448();
            int i17 = class38_sub2_sub3.method446();
            int k17 = class38_sub2_sub3.method446();
            if (k3 >= 0 && l5 >= 0 && k3 < 104 && l5 < 104 && i8 >= 0 && i10 >= 0 && i8 < 104 && i10 < 104) {
                k3 = k3 * 128 + 64;
                l5 = l5 * 128 + 64;
                i8 = i8 * 128 + 64;
                i10 = i10 * 128 + 64;
                ProjectileEntity projectileEntity = new ProjectileEntity(i15, i17, l5, j16 + anInt955, anInt880, l11,
                        k15 + anInt955, k17, method33(anInt880, k3, (byte) 5, l5) - k14, k13, k3);
                projectileEntity.setTarget(method33(anInt880, i8, (byte) 5, i10) - i15, i10, i8, k15 + anInt955);
                linkedList4.method267(projectileEntity);
            }
            return;
        }
        if (i == 191) {
            int k1 = class38_sub2_sub3.method446();
            int l3 = anInt862 + (k1 >> 4 & 7);
            int i6 = anInt863 + (k1 & 7);
            int j8 = class38_sub2_sub3.method448();
            int j10 = class38_sub2_sub3.method446();
            int i12 = class38_sub2_sub3.method448();
            if (l3 >= 0 && i6 >= 0 && l3 < 104 && i6 < 104) {
                l3 = l3 * 128 + 64;
                i6 = i6 * 128 + 64;
                Class38_Sub7_Sub2 class38_sub7_sub2 = new Class38_Sub7_Sub2(l3, j8, false, i6, i12,
                        method33(anInt880, l3, (byte) 5, i6) - j10, anInt880, anInt955);
                linkedList1.method267(class38_sub7_sub2);
            }
            return;
        }
        if (i == 50) {
            int l1 = class38_sub2_sub3.method446();
            int i4 = anInt862 + (l1 >> 4 & 7);
            int j6 = anInt863 + (l1 & 7);
            int k8 = class38_sub2_sub3.method448();
            int k10 = class38_sub2_sub3.method448();
            int j12 = class38_sub2_sub3.method448();
            if (i4 >= 0 && j6 >= 0 && i4 < 104 && j6 < 104 && j12 != anInt734) {
                ObjStackEntity objStackEntity_2 = new ObjStackEntity();
                objStackEntity_2.model = k8;
                objStackEntity_2.amount = k10;
                if (linkedList3dArray[anInt880][i4][j6] == null)
                    linkedList3dArray[anInt880][i4][j6] = new LinkedList();
                linkedList3dArray[anInt880][i4][j6].method267(objStackEntity_2);
                method123(i4, j6);
            }
            return;
        }
        if (i == 23) {
            int i2 = class38_sub2_sub3.method446();
            int j4 = anInt862 + (i2 >> 4 & 7);
            int k6 = anInt863 + (i2 & 7);
            int l8 = class38_sub2_sub3.method446();
            int l10 = l8 >> 2;
            int k12 = l8 & 3;
            int l13 = anIntArray1107[l10];
            int l14 = class38_sub2_sub3.method448();
            int j15 = class38_sub2_sub3.method448();
            int l15 = class38_sub2_sub3.method448();
            int k16 = class38_sub2_sub3.method448();
            byte byte1 = class38_sub2_sub3.method447();
            byte byte2 = class38_sub2_sub3.method447();
            byte byte3 = class38_sub2_sub3.method447();
            byte byte4 = class38_sub2_sub3.method447();
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2;
            if (k16 == anInt734)
                class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2_967;
            else
                class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[k16];
            if (class38_sub7_sub3_sub2 != null) {
                TemporaryLoc temporaryLoc = new TemporaryLoc(anInt880, k12, k6, j15 + anInt955, l10, -1, j4, l13);
                linkedList3.method267(temporaryLoc);
                TemporaryLoc temporaryLoc_1 = new TemporaryLoc(anInt880, k12, k6, l15 + anInt955, l10, l14, j4, l13);
                linkedList3.method267(temporaryLoc_1);
                int j18 = anIntArrayArrayArray794[anInt880][j4][k6];
                int k18 = anIntArrayArrayArray794[anInt880][j4 + 1][k6];
                int l18 = anIntArrayArrayArray794[anInt880][j4 + 1][k6 + 1];
                int i19 = anIntArrayArrayArray794[anInt880][j4][k6 + 1];
                LocType locType = LocType.get(l14);
                class38_sub7_sub3_sub2.anInt1514 = j15 + anInt955;
                class38_sub7_sub3_sub2.anInt1515 = l15 + anInt955;
                class38_sub7_sub3_sub2.aClass38_Sub2_Sub1_1519 = locType.getModel(l10, k12, j18, k18, l18, i19, -1);
                int j19 = locType.sizeX;
                int k19 = locType.sizeZ;
                if (k12 == 1 || k12 == 3) {
                    j19 = locType.sizeZ;
                    k19 = locType.sizeX;
                }
                class38_sub7_sub3_sub2.anInt1516 = j4 * 128 + j19 * 64;
                class38_sub7_sub3_sub2.anInt1518 = k6 * 128 + k19 * 64;
                class38_sub7_sub3_sub2.anInt1517 = method33(anInt880, class38_sub7_sub3_sub2.anInt1516, (byte) 5,
                        class38_sub7_sub3_sub2.anInt1518);
                if (byte1 > byte3) {
                    byte byte5 = byte1;
                    byte1 = byte3;
                    byte3 = byte5;
                }
                if (byte2 > byte4) {
                    byte byte6 = byte2;
                    byte2 = byte4;
                    byte4 = byte6;
                }
                class38_sub7_sub3_sub2.anInt1520 = j4 + byte1;
                class38_sub7_sub3_sub2.anInt1522 = j4 + byte3;
                class38_sub7_sub3_sub2.anInt1521 = k6 + byte2;
                class38_sub7_sub3_sub2.anInt1523 = k6 + byte4;
            }
        }
        if (i == 151) {
            int j2 = class38_sub2_sub3.method446();
            int k4 = anInt862 + (j2 >> 4 & 7);
            int l6 = anInt863 + (j2 & 7);
            int i9 = class38_sub2_sub3.method448();
            int i11 = class38_sub2_sub3.method448();
            int l12 = class38_sub2_sub3.method448();
            if (k4 >= 0 && l6 >= 0 && k4 < 104 && l6 < 104) {
                LinkedList linkedList_1 = linkedList3dArray[anInt880][k4][l6];
                if (linkedList_1 != null) {
                    for (ObjStackEntity objStackEntity_3 = (ObjStackEntity) linkedList_1
                            .method270(); objStackEntity_3 != null; objStackEntity_3 = (ObjStackEntity) linkedList_1
                            .method272()) {
                        if (objStackEntity_3.model != (i9 & 0x7fff) || objStackEntity_3.amount != i11)
                            continue;
                        objStackEntity_3.amount = l12;
                        break;
                    }

                    method123(k4, l6);
                }
            }
        }
    }

    public int method23(byte byte0) {
        if (byte0 != 106)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
        int i = 3;
        if (anInt1114 < 310) {
            int j = anInt1111 >> 7;
            int k = anInt1113 >> 7;
            int l = aClass38_Sub7_Sub3_Sub2_967.anInt1380 >> 7;
            int i1 = aClass38_Sub7_Sub3_Sub2_967.anInt1381 >> 7;
            if ((aByteArrayArrayArray840[anInt880][j][k] & 4) != 0)
                i = anInt880;
            int j1;
            if (l > j)
                j1 = l - j;
            else
                j1 = j - l;
            int k1;
            if (i1 > k)
                k1 = i1 - k;
            else
                k1 = k - i1;
            if (j1 > k1) {
                int l1 = (k1 * 0x10000) / j1;
                int j2 = 32768;
                while (j != l) {
                    if (j < l)
                        j++;
                    else if (j > l)
                        j--;
                    if ((aByteArrayArrayArray840[anInt880][j][k] & 4) != 0)
                        i = anInt880;
                    j2 += l1;
                    if (j2 >= 0x10000) {
                        j2 -= 0x10000;
                        if (k < i1)
                            k++;
                        else if (k > i1)
                            k--;
                        if ((aByteArrayArrayArray840[anInt880][j][k] & 4) != 0)
                            i = anInt880;
                    }
                }
            } else {
                int i2 = (j1 * 0x10000) / k1;
                int k2 = 32768;
                while (k != i1) {
                    if (k < i1)
                        k++;
                    else if (k > i1)
                        k--;
                    if ((aByteArrayArrayArray840[anInt880][j][k] & 4) != 0)
                        i = anInt880;
                    k2 += i2;
                    if (k2 >= 0x10000) {
                        k2 -= 0x10000;
                        if (j < l)
                            j++;
                        else if (j > l)
                            j--;
                        if ((aByteArrayArrayArray840[anInt880][j][k] & 4) != 0)
                            i = anInt880;
                    }
                }
            }
        }
        if ((aByteArrayArrayArray840[anInt880][aClass38_Sub7_Sub3_Sub2_967.anInt1380 >> 7][aClass38_Sub7_Sub3_Sub2_967.anInt1381 >> 7]
                & 4) != 0)
            i = anInt880;
        return i;
    }

    public int method24(int i) {
        int j = method33(anInt880, anInt1111, (byte) 5, anInt1113);
        anInt779 += i;
        if (j - anInt1112 < 800 && (aByteArrayArrayArray840[anInt880][anInt1111 >> 7][anInt1113 >> 7] & 4) != 0)
            return anInt880;
        else
            return 3;
    }

    public void method25(int i) {
        anInt837++;
        method32(284);
        method53(false);
        anInt779 += i;
        method86((byte) -26);
        method105((byte) 106);
        method129(254);
        if (!aBoolean968) {
            int j = anInt816;
            if (anInt932 / 256 > j)
                j = anInt932 / 256;
            if (aBooleanArray754[4] && anIntArray966[4] + 128 > j)
                j = anIntArray966[4] + 128;
            int l = anInt817 + anInt1134 & 0x7ff;
            method39(
                    method33(anInt880, aClass38_Sub7_Sub3_Sub2_967.anInt1380, (byte) 5,
                            aClass38_Sub7_Sub3_Sub2_967.anInt1381) - 50,
                    anInt914, l, j, 16418, anInt915, 600 + j * 3);
            anInt804++;
            if (anInt804 > 1802) {
                anInt804 = 0;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 146);
                aClass38_Sub2_Sub3_798.method436(0);
                int j1 = aClass38_Sub2_Sub3_798.offset;
                aClass38_Sub2_Sub3_798.method437(29711);
                aClass38_Sub2_Sub3_798.method436(70);
                aClass38_Sub2_Sub3_798.method436((int) (Math.random() * 256D));
                aClass38_Sub2_Sub3_798.method436(242);
                aClass38_Sub2_Sub3_798.method436(186);
                aClass38_Sub2_Sub3_798.method436(39);
                aClass38_Sub2_Sub3_798.method436(61);
                if ((int) (Math.random() * 2D) == 0)
                    aClass38_Sub2_Sub3_798.method436(13);
                if ((int) (Math.random() * 2D) == 0)
                    aClass38_Sub2_Sub3_798.method437(57856);
                aClass38_Sub2_Sub3_798.method437((int) (Math.random() * 65536D));
                aClass38_Sub2_Sub3_798.method445(0, aClass38_Sub2_Sub3_798.offset - j1);
            }
        }
        int k;
        if (!aBoolean968)
            k = method23((byte) 106);
        else
            k = method24(0);
        int i1 = anInt1111;
        int k1 = anInt1112;
        int l1 = anInt1113;
        int i2 = anInt1114;
        int j2 = anInt1115;
        for (int k2 = 0; k2 < 5; k2++)
            if (aBooleanArray754[k2]) {
                int l2 = (int) ((Math.random() * (double) (anIntArray959[k2] * 2 + 1) - (double) anIntArray959[k2])
                        + Math.sin((double) anIntArray1024[k2] * ((double) anIntArray1159[k2] / 100D))
                        * (double) anIntArray966[k2]);
                if (k2 == 0)
                    anInt1111 += l2;
                if (k2 == 1)
                    anInt1112 += l2;
                if (k2 == 2)
                    anInt1113 += l2;
                if (k2 == 3)
                    anInt1115 = anInt1115 + l2 & 0x7ff;
                if (k2 == 4) {
                    anInt1114 += l2;
                    if (anInt1114 < 128)
                        anInt1114 = 128;
                    if (anInt1114 > 383)
                        anInt1114 = 383;
                }
            }

        int i3 = Class38_Sub2_Sub2_Sub1.anInt1457;
        Class38_Sub2_Sub1.aBoolean1295 = true;
        Class38_Sub2_Sub1.anInt1298 = 0;
        Class38_Sub2_Sub1.anInt1296 = super.anInt21 - 8;
        Class38_Sub2_Sub1.anInt1297 = super.anInt22 - 11;
        Class38_Sub2_Sub2.method379(anInt1143);
        aClass32_831.method321(anInt1115, anInt1111, k, anInt1114, anInt1112, anInt1113, 0);
        aClass32_831.method295(0);
        method15(anInt805);
        method133((byte) -11);
        method41(i3, true);
        method84(9);
        drawArea22.drawImage(11, super.aGraphics14, 8);
        anInt1111 = i1;
        anInt1112 = k1;
        anInt1113 = l1;
        anInt1114 = i2;
        anInt1115 = j2;
    }

    public void method26(boolean flag) {
        aBoolean799 = false;
        if (!flag)
            return;
        while (aBoolean812) {
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
            String s;
            int i;
            int j;
            synchronized (anObject1123) {
                s = aString1064;
                i = anInt963;
                j = anInt726;
                aString1064 = null;
                anInt963 = 0;
                anInt726 = 0;
            }
            if (s != null) {
                byte[] abyte0 = signlink.cacheload(s + ".mid");
                if (abyte0 != null && i != 0xbc614e) {
                    aCRC32_996.reset();
                    aCRC32_996.update(abyte0);
                    int k = (int) aCRC32_996.getValue();
                    if (k != i)
                        abyte0 = null;
                }
                if (abyte0 == null)
                    try {
                        DataInputStream datainputstream = method94(s + "_" + i + ".mid");
                        abyte0 = new byte[j];
                        int j1;
                        for (int i1 = 0; i1 < j; i1 += j1) {
                            j1 = datainputstream.read(abyte0, i1, j - i1);
                            if (j1 != -1)
                                continue;
                            byte[] abyte2 = new byte[i1];
                            for (int k1 = 0; k1 < i1; k1++)
                                abyte2[k1] = abyte0[k1];

                            abyte0 = abyte2;
                            j = i1;
                            break;
                        }

                        datainputstream.close();
                        signlink.cachesave(s + ".mid", abyte0);
                    } catch (Exception _ex) {
                    }
                if (abyte0 == null)
                    return;
                int l = (new Class38_Sub2_Sub3(363, abyte0)).method451();
                byte[] abyte1 = new byte[l];
                BZip2InputStream.read(abyte1, l, abyte0, j, 4);
                method52(abyte1, 625, l, true);
            }
        }
    }

    public static void method27(boolean flag) {
        if (!flag)
            aBoolean870 = !aBoolean870;
        Class32.aBoolean526 = true;
        Class38_Sub2_Sub2_Sub1.aBoolean1437 = true;
        aBoolean889 = true;
        Class3.aBoolean108 = true;
    }

    public void method28(boolean flag) {
        char c = '\u0100';
        if (anInt874 > 0) {
            for (int i = 0; i < 256; i++)
                if (anInt874 > 768)
                    anIntArray903[i] = method88(anIntArray904[i], 1024 - anInt874, anIntArray905[i], 997);
                else if (anInt874 > 256)
                    anIntArray903[i] = anIntArray905[i];
                else
                    anIntArray903[i] = method88(anIntArray905[i], 256 - anInt874, anIntArray904[i], 997);

        } else if (anInt875 > 0) {
            for (int j = 0; j < 256; j++)
                if (anInt875 > 768)
                    anIntArray903[j] = method88(anIntArray904[j], 1024 - anInt875, anIntArray906[j], 997);
                else if (anInt875 > 256)
                    anIntArray903[j] = anIntArray906[j];
                else
                    anIntArray903[j] = method88(anIntArray906[j], 256 - anInt875, anIntArray904[j], 997);

        } else {
            for (int k = 0; k < 256; k++)
                anIntArray903[k] = anIntArray904[k];

        }
        for (int l = 0; l < 33920; l++)
            drawArea14.pixels[l] = aClass38_Sub2_Sub2_Sub2_978.anIntArray1465[l];

        int i1 = 0;
        int j1 = 1152;
        for (int k1 = 1; k1 < c - 1; k1++) {
            int l1 = (anIntArray850[k1] * (c - k1)) / c;
            int j2 = 22 + l1;
            if (j2 < 0)
                j2 = 0;
            i1 += j2;
            for (int l2 = j2; l2 < 128; l2++) {
                int j3 = anIntArray991[i1++];
                if (j3 != 0) {
                    int l3 = j3;
                    int j4 = 256 - j3;
                    j3 = anIntArray903[j3];
                    int l4 = drawArea14.pixels[j1];
                    drawArea14.pixels[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00)
                            + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            j1 += j2;
        }

        drawArea14.drawImage(0, super.aGraphics14, 0);
        for (int i2 = 0; i2 < 33920; i2++)
            drawArea15.pixels[i2] = aClass38_Sub2_Sub2_Sub2_979.anIntArray1465[i2];

        i1 = 0;
        j1 = 1176;
        for (int k2 = 1; k2 < c - 1; k2++) {
            int i3 = (anIntArray850[k2] * (c - k2)) / c;
            int k3 = 103 - i3;
            j1 += i3;
            for (int i4 = 0; i4 < k3; i4++) {
                int k4 = anIntArray991[i1++];
                if (k4 != 0) {
                    int i5 = k4;
                    int j5 = 256 - k4;
                    k4 = anIntArray903[k4];
                    int k5 = drawArea15.pixels[j1];
                    drawArea15.pixels[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00)
                            + ((k4 & 0xff00) * i5 + (k5 & 0xff00) * j5 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            i1 += 128 - k3;
            j1 += 128 - k3 - i3;
        }

        if (!flag)
            anInt958 = 281;
        drawArea15.drawImage(0, super.aGraphics14, 661);
    }

    public void method29(int i, int j, int k, InterfaceComponent interfaceComponent, int l, int i1, int j1) {
        if (interfaceComponent.anInt271 != 0 || interfaceComponent.anIntArray285 == null || interfaceComponent.aBoolean284)
            return;
        if (j < i1 || i < k || j > i1 + interfaceComponent.anInt274 || i > k + interfaceComponent.anInt275)
            return;
        int k1 = interfaceComponent.anIntArray285.length;
        if (l != 5082)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
        for (int l1 = 0; l1 < k1; l1++) {
            int i2 = interfaceComponent.anIntArray286[l1] + i1;
            int j2 = (interfaceComponent.anIntArray287[l1] + k) - j1;
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.interfaceComponentArray[interfaceComponent.anIntArray285[l1]];
            i2 += interfaceComponent_1.anInt276;
            j2 += interfaceComponent_1.anInt277;
            if ((interfaceComponent_1.anInt281 >= 0 || interfaceComponent_1.anInt307 != 0) && j >= i2 && i >= j2
                    && j < i2 + interfaceComponent_1.anInt274 && i < j2 + interfaceComponent_1.anInt275)
                if (interfaceComponent_1.anInt281 >= 0)
                    anInt868 = interfaceComponent_1.anInt281;
                else
                    anInt868 = interfaceComponent_1.anInt269;
            if (interfaceComponent_1.anInt271 == 0) {
                method29(i, j, j2, interfaceComponent_1, 5082, i2, interfaceComponent_1.anInt283);
                if (interfaceComponent_1.anInt282 > interfaceComponent_1.anInt275)
                    method97(j, 0, i, interfaceComponent_1.anInt282, interfaceComponent_1.anInt275, true, i2 + interfaceComponent_1.anInt274, j2,
                            interfaceComponent_1);
            } else {
                if (interfaceComponent_1.anInt272 == 1 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    boolean flag = false;
                    if (interfaceComponent_1.anInt273 != 0)
                        flag = method119(interfaceComponent_1, (byte) 8);
                    if (!flag) {
                        aStringArray834[anInt1074] = interfaceComponent_1.aString320;
                        anIntArray1141[anInt1074] = 951;
                        anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                        anInt1074++;
                    }
                }
                if (interfaceComponent_1.anInt272 == 2 && anInt1025 == 0 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    String s = interfaceComponent_1.aString317;
                    if (s.indexOf(" ") != -1)
                        s = s.substring(0, s.indexOf(" "));
                    aStringArray834[anInt1074] = s + " @gre@" + interfaceComponent_1.aString318;
                    anIntArray1141[anInt1074] = 930;
                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                    anInt1074++;
                }
                if (interfaceComponent_1.anInt272 == 3 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    aStringArray834[anInt1074] = "Close";
                    anIntArray1141[anInt1074] = 947;
                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                    anInt1074++;
                }
                if (interfaceComponent_1.anInt272 == 4 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    aStringArray834[anInt1074] = interfaceComponent_1.aString320;
                    anIntArray1141[anInt1074] = 465;
                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                    anInt1074++;
                }
                if (interfaceComponent_1.anInt272 == 5 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    aStringArray834[anInt1074] = interfaceComponent_1.aString320;
                    anIntArray1141[anInt1074] = 960;
                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                    anInt1074++;
                }
                if (interfaceComponent_1.anInt272 == 6 && !aBoolean872 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    aStringArray834[anInt1074] = interfaceComponent_1.aString320;
                    anIntArray1141[anInt1074] = 44;
                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                    anInt1074++;
                }
                if (interfaceComponent_1.anInt271 == 2) {
                    int k2 = 0;
                    for (int l2 = 0; l2 < interfaceComponent_1.anInt275; l2++) {
                        for (int i3 = 0; i3 < interfaceComponent_1.anInt274; i3++) {
                            int j3 = i2 + i3 * (32 + interfaceComponent_1.anInt293);
                            int k3 = j2 + l2 * (32 + interfaceComponent_1.anInt294);
                            if (k2 < 20) {
                                j3 += interfaceComponent_1.anIntArray296[k2];
                                k3 += interfaceComponent_1.anIntArray297[k2];
                            }
                            if (j >= j3 && i >= k3 && j < j3 + 32 && i < k3 + 32) {
                                anInt1087 = k2;
                                anInt1088 = interfaceComponent_1.anInt269;
                                if (interfaceComponent_1.anIntArray265[k2] > 0) {
                                    ObjType objType = ObjType.method169(interfaceComponent_1.anIntArray265[k2] - 1);
                                    if (anInt1002 == 1 && interfaceComponent_1.aBoolean291) {
                                        if (interfaceComponent_1.anInt269 != anInt1004 || k2 != anInt1003) {
                                            aStringArray834[anInt1074] = "Use " + aString1006 + " with @lre@"
                                                    + objType.aString145;
                                            anIntArray1141[anInt1074] = 881;
                                            anIntArray1142[anInt1074] = objType.anInt143;
                                            anIntArray1139[anInt1074] = k2;
                                            anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                                            anInt1074++;
                                        }
                                    } else if (anInt1025 == 1 && interfaceComponent_1.aBoolean291) {
                                        if ((anInt1027 & 0x10) == 16) {
                                            aStringArray834[anInt1074] = aString1028 + " @lre@" + objType.aString145;
                                            anIntArray1141[anInt1074] = 391;
                                            anIntArray1142[anInt1074] = objType.anInt143;
                                            anIntArray1139[anInt1074] = k2;
                                            anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                                            anInt1074++;
                                        }
                                    } else {
                                        if (interfaceComponent_1.aBoolean291) {
                                            for (int l3 = 4; l3 >= 3; l3--)
                                                if (objType.aStringArray161 != null
                                                        && objType.aStringArray161[l3] != null) {
                                                    aStringArray834[anInt1074] = objType.aStringArray161[l3] + " @lre@"
                                                            + objType.aString145;
                                                    if (l3 == 3)
                                                        anIntArray1141[anInt1074] = 478;
                                                    if (l3 == 4)
                                                        anIntArray1141[anInt1074] = 347;
                                                    anIntArray1142[anInt1074] = objType.anInt143;
                                                    anIntArray1139[anInt1074] = k2;
                                                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                                                    anInt1074++;
                                                } else if (l3 == 4) {
                                                    aStringArray834[anInt1074] = "Drop @lre@" + objType.aString145;
                                                    anIntArray1141[anInt1074] = 347;
                                                    anIntArray1142[anInt1074] = objType.anInt143;
                                                    anIntArray1139[anInt1074] = k2;
                                                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                                                    anInt1074++;
                                                }

                                        }
                                        if (interfaceComponent_1.aBoolean292) {
                                            aStringArray834[anInt1074] = "Use @lre@" + objType.aString145;
                                            anIntArray1141[anInt1074] = 188;
                                            anIntArray1142[anInt1074] = objType.anInt143;
                                            anIntArray1139[anInt1074] = k2;
                                            anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                                            anInt1074++;
                                        }
                                        if (interfaceComponent_1.aBoolean291 && objType.aStringArray161 != null) {
                                            for (int i4 = 2; i4 >= 0; i4--)
                                                if (objType.aStringArray161[i4] != null) {
                                                    aStringArray834[anInt1074] = objType.aStringArray161[i4] + " @lre@"
                                                            + objType.aString145;
                                                    if (i4 == 0)
                                                        anIntArray1141[anInt1074] = 405;
                                                    if (i4 == 1)
                                                        anIntArray1141[anInt1074] = 38;
                                                    if (i4 == 2)
                                                        anIntArray1141[anInt1074] = 422;
                                                    anIntArray1142[anInt1074] = objType.anInt143;
                                                    anIntArray1139[anInt1074] = k2;
                                                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                                                    anInt1074++;
                                                }

                                        }
                                        if (interfaceComponent_1.aStringArray298 != null) {
                                            for (int j4 = 4; j4 >= 0; j4--)
                                                if (interfaceComponent_1.aStringArray298[j4] != null) {
                                                    aStringArray834[anInt1074] = interfaceComponent_1.aStringArray298[j4]
                                                            + " @lre@" + objType.aString145;
                                                    if (j4 == 0)
                                                        anIntArray1141[anInt1074] = 602;
                                                    if (j4 == 1)
                                                        anIntArray1141[anInt1074] = 596;
                                                    if (j4 == 2)
                                                        anIntArray1141[anInt1074] = 22;
                                                    if (j4 == 3)
                                                        anIntArray1141[anInt1074] = 892;
                                                    if (j4 == 4)
                                                        anIntArray1141[anInt1074] = 415;
                                                    anIntArray1142[anInt1074] = objType.anInt143;
                                                    anIntArray1139[anInt1074] = k2;
                                                    anIntArray1140[anInt1074] = interfaceComponent_1.anInt269;
                                                    anInt1074++;
                                                }

                                        }
                                        aStringArray834[anInt1074] = "Examine @lre@" + objType.aString145;
                                        anIntArray1141[anInt1074] = 1773;
                                        anIntArray1142[anInt1074] = objType.anInt143;
                                        anIntArray1140[anInt1074] = interfaceComponent_1.anIntArray266[k2];
                                        anInt1074++;
                                    }
                                }
                            }
                            k2++;
                        }

                    }

                }
            }
        }

    }

    public void method30(int i) {
        anInt779 += i;
        if (super.anInt23 == 1) {
            if (super.anInt24 >= 8 && super.anInt24 <= 108 && super.anInt25 >= 490 && super.anInt25 <= 522) {
                anInt976 = (anInt976 + 1) % 4;
                aBoolean921 = true;
                aBoolean965 = true;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 244);
                aClass38_Sub2_Sub3_798.method436(anInt976);
                aClass38_Sub2_Sub3_798.method436(anInt755);
                aClass38_Sub2_Sub3_798.method436(anInt885);
            }
            if (super.anInt24 >= 137 && super.anInt24 <= 237 && super.anInt25 >= 490 && super.anInt25 <= 522) {
                anInt755 = (anInt755 + 1) % 3;
                aBoolean921 = true;
                aBoolean965 = true;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 244);
                aClass38_Sub2_Sub3_798.method436(anInt976);
                aClass38_Sub2_Sub3_798.method436(anInt755);
                aClass38_Sub2_Sub3_798.method436(anInt885);
            }
            if (super.anInt24 >= 275 && super.anInt24 <= 375 && super.anInt25 >= 490 && super.anInt25 <= 522) {
                anInt885 = (anInt885 + 1) % 3;
                aBoolean921 = true;
                aBoolean965 = true;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 244);
                aClass38_Sub2_Sub3_798.method436(anInt976);
                aClass38_Sub2_Sub3_798.method436(anInt755);
                aClass38_Sub2_Sub3_798.method436(anInt885);
            }
            if (super.anInt24 >= 416 && super.anInt24 <= 516 && super.anInt25 >= 490 && super.anInt25 <= 522) {
                method16((byte) -60);
                aString970 = "";
                aBoolean881 = false;
                for (int j = 0; j < InterfaceComponent.interfaceComponentArray.length; j++)
                    if (InterfaceComponent.interfaceComponentArray[j] != null && InterfaceComponent.interfaceComponentArray[j].anInt273 == 600) {
                        anInt907 = anInt971 = InterfaceComponent.interfaceComponentArray[j].anInt270;
                        return;
                    }

            }
        }
    }

    public void method31(int i, int j, int k) {
        int l = 0;
        for (int i1 = 0; i1 < 100; i1++) {
            if (aStringArray898[i1] == null)
                continue;
            int j1 = anIntArray896[i1];
            int k1 = (70 - l * 14) + anInt977 + 4;
            if (k1 < -20)
                break;
            if (j1 == 0)
                l++;
            if ((j1 == 1 || j1 == 2)
                    && (j1 == 1 || anInt976 == 0 || anInt976 == 1 && method138(-20, aStringArray897[i1]))) {
                if (i > k1 - 14 && i <= k1 && !aStringArray897[i1].equals(aClass38_Sub7_Sub3_Sub2_967.aString1505)) {
                    if (aBoolean1023) {
                        aStringArray834[anInt1074] = "Report abuse @whi@" + aStringArray897[i1];
                        anIntArray1141[anInt1074] = 34;
                        anInt1074++;
                    }
                    aStringArray834[anInt1074] = "Add ignore @whi@" + aStringArray897[i1];
                    anIntArray1141[anInt1074] = 436;
                    anInt1074++;
                    aStringArray834[anInt1074] = "Add friend @whi@" + aStringArray897[i1];
                    anIntArray1141[anInt1074] = 406;
                    anInt1074++;
                }
                l++;
            }
            if ((j1 == 3 || j1 == 7) && anInt833 == 0
                    && (j1 == 7 || anInt755 == 0 || anInt755 == 1 && method138(-20, aStringArray897[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    if (aBoolean1023) {
                        aStringArray834[anInt1074] = "Report abuse @whi@" + aStringArray897[i1];
                        anIntArray1141[anInt1074] = 34;
                        anInt1074++;
                    }
                    aStringArray834[anInt1074] = "Add ignore @whi@" + aStringArray897[i1];
                    anIntArray1141[anInt1074] = 436;
                    anInt1074++;
                    aStringArray834[anInt1074] = "Add friend @whi@" + aStringArray897[i1];
                    anIntArray1141[anInt1074] = 406;
                    anInt1074++;
                }
                l++;
            }
            if (j1 == 4 && (anInt885 == 0 || anInt885 == 1 && method138(-20, aStringArray897[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    aStringArray834[anInt1074] = "Accept trade @whi@" + aStringArray897[i1];
                    anIntArray1141[anInt1074] = 903;
                    anInt1074++;
                }
                l++;
            }
            if ((j1 == 5 || j1 == 6) && anInt833 == 0 && anInt755 < 2)
                l++;
            if (j1 == 8 && (anInt885 == 0 || anInt885 == 1 && method138(-20, aStringArray897[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    aStringArray834[anInt1074] = "Accept duel @whi@" + aStringArray897[i1];
                    anIntArray1141[anInt1074] = 363;
                    anInt1074++;
                }
                l++;
            }
        }

        anInt779 += j;
    }

    public void method32(int i) {
        if (aClass38_Sub7_Sub3_Sub2_967.anInt1380 >> 7 == anInt1051
                && aClass38_Sub7_Sub3_Sub2_967.anInt1381 >> 7 == anInt1052)
            anInt1051 = 0;
        for (int j = -1; j < anInt823; j++) {
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2;
            int k;
            if (j == -1) {
                class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2_967;
                k = anInt821 << 14;
            } else {
                class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[anIntArray824[j]];
                k = anIntArray824[j] << 14;
            }
            if (class38_sub7_sub3_sub2 == null || !class38_sub7_sub3_sub2.method468(false))
                continue;
            class38_sub7_sub3_sub2.aBoolean1524 = (aBoolean889 && anInt823 > 50 || anInt823 > 200) && j != -1
                    && class38_sub7_sub3_sub2.anInt1404 == class38_sub7_sub3_sub2.anInt1385;
            int l = class38_sub7_sub3_sub2.anInt1380 >> 7;
            int i1 = class38_sub7_sub3_sub2.anInt1381 >> 7;
            if (l < 0 || l >= 104 || i1 < 0 || i1 >= 104)
                continue;
            if (class38_sub7_sub3_sub2.aClass38_Sub2_Sub1_1519 != null && anInt955 >= class38_sub7_sub3_sub2.anInt1514
                    && anInt955 < class38_sub7_sub3_sub2.anInt1515) {
                class38_sub7_sub3_sub2.aBoolean1524 = false;
                class38_sub7_sub3_sub2.anInt1513 = method33(anInt880,
                        class38_sub7_sub3_sub2.anInt1380, (byte) 5,
                        class38_sub7_sub3_sub2.anInt1381);
                aClass32_831.method293(class38_sub7_sub3_sub2.anInt1522, 60, null,
                        class38_sub7_sub3_sub2.anInt1381, class38_sub7_sub3_sub2.anInt1513, k,
                        class38_sub7_sub3_sub2.anInt1382, class38_sub7_sub3_sub2.anInt1521,
                        class38_sub7_sub3_sub2.anInt1520, class38_sub7_sub3_sub2, false, anInt880,
                        class38_sub7_sub3_sub2.anInt1523, class38_sub7_sub3_sub2.anInt1380);
                continue;
            }
            if ((class38_sub7_sub3_sub2.anInt1380 & 0x7f) == 64
                    && (class38_sub7_sub3_sub2.anInt1381 & 0x7f) == 64) {
                if (anIntArrayArray920[l][i1] == anInt837)
                    continue;
                anIntArrayArray920[l][i1] = anInt837;
            }
            class38_sub7_sub3_sub2.anInt1513 = method33(anInt880,
                    class38_sub7_sub3_sub2.anInt1380, (byte) 5,
                    class38_sub7_sub3_sub2.anInt1381);
            aClass32_831.method292(-44713, class38_sub7_sub3_sub2.anInt1381, 60,
                    class38_sub7_sub3_sub2.anInt1382,
                    class38_sub7_sub3_sub2.anInt1380, k,
                    class38_sub7_sub3_sub2.aBoolean1383, null, class38_sub7_sub3_sub2,
                    class38_sub7_sub3_sub2.anInt1513, anInt880);
        }

        if (i <= 0)
            aClass38_Sub2_Sub3_798.method436(210);
    }

    public int method33(int i, int j, byte byte0, int k) {
        if (byte0 != 5)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
        int l = j >> 7;
        int i1 = k >> 7;
        int j1 = i;
        if (j1 < 3 && (aByteArrayArrayArray840[1][l][i1] & 2) == 2)
            j1++;
        int k1 = j & 0x7f;
        int l1 = k & 0x7f;
        int i2 = anIntArrayArrayArray794[j1][l][i1] * (128 - k1) + anIntArrayArrayArray794[j1][l + 1][i1] * k1 >> 7;
        int j2 = anIntArrayArrayArray794[j1][l][i1 + 1] * (128 - k1)
                + anIntArrayArrayArray794[j1][l + 1][i1 + 1] * k1 >> 7;
        return i2 * (128 - l1) + j2 * l1 >> 7;
    }

    public void method34(NpcType npcType, int i, int j, int k, int l) {
        while (i >= 0)
            anInt780 = -1;
        if (anInt1074 >= 400)
            return;
        String s = npcType.aString83;
        if (npcType.anInt101 != 0)
            s = s + method72(aClass38_Sub7_Sub3_Sub2_967.anInt1511, false, npcType.anInt101) + " (level-"
                    + npcType.anInt101 + ")";
        if (anInt1002 == 1) {
            aStringArray834[anInt1074] = "Use " + aString1006 + " with @yel@" + s;
            anIntArray1141[anInt1074] = 900;
            anIntArray1142[anInt1074] = l;
            anIntArray1139[anInt1074] = k;
            anIntArray1140[anInt1074] = j;
            anInt1074++;
            return;
        }
        if (anInt1025 == 1) {
            if ((anInt1027 & 2) == 2) {
                aStringArray834[anInt1074] = aString1028 + " @yel@" + s;
                anIntArray1141[anInt1074] = 265;
                anIntArray1142[anInt1074] = l;
                anIntArray1139[anInt1074] = k;
                anIntArray1140[anInt1074] = j;
                anInt1074++;
                return;
            }
        } else {
            if (npcType.aStringArray96 != null) {
                for (int i1 = 4; i1 >= 0; i1--)
                    if (npcType.aStringArray96[i1] != null && !npcType.aStringArray96[i1].equalsIgnoreCase("attack")) {
                        aStringArray834[anInt1074] = npcType.aStringArray96[i1] + " @yel@" + s;
                        if (i1 == 0)
                            anIntArray1141[anInt1074] = 728;
                        if (i1 == 1)
                            anIntArray1141[anInt1074] = 542;
                        if (i1 == 2)
                            anIntArray1141[anInt1074] = 6;
                        if (i1 == 3)
                            anIntArray1141[anInt1074] = 963;
                        if (i1 == 4)
                            anIntArray1141[anInt1074] = 245;
                        anIntArray1142[anInt1074] = l;
                        anIntArray1139[anInt1074] = k;
                        anIntArray1140[anInt1074] = j;
                        anInt1074++;
                    }

            }
            if (npcType.aStringArray96 != null) {
                for (int j1 = 4; j1 >= 0; j1--)
                    if (npcType.aStringArray96[j1] != null && npcType.aStringArray96[j1].equalsIgnoreCase("attack")) {
                        char c = '\0';
                        if (npcType.anInt101 > aClass38_Sub7_Sub3_Sub2_967.anInt1511)
                            c = '\u07D0';
                        aStringArray834[anInt1074] = npcType.aStringArray96[j1] + " @yel@" + s;
                        if (j1 == 0)
                            anIntArray1141[anInt1074] = 728 + c;
                        if (j1 == 1)
                            anIntArray1141[anInt1074] = 542 + c;
                        if (j1 == 2)
                            anIntArray1141[anInt1074] = 6 + c;
                        if (j1 == 3)
                            anIntArray1141[anInt1074] = 963 + c;
                        if (j1 == 4)
                            anIntArray1141[anInt1074] = 245 + c;
                        anIntArray1142[anInt1074] = l;
                        anIntArray1139[anInt1074] = k;
                        anIntArray1140[anInt1074] = j;
                        anInt1074++;
                    }

            }
            aStringArray834[anInt1074] = "Examine @yel@" + s;
            anIntArray1141[anInt1074] = 1607;
            anIntArray1142[anInt1074] = l;
            anIntArray1139[anInt1074] = k;
            anIntArray1140[anInt1074] = j;
            anInt1074++;
        }
    }

    public void method35(int i) {
        if (i != 7)
            anInt1132 = isaacState.nextInt();
        do {
            int j = method5(false);
            if (j == -1)
                break;
            if (anInt971 != -1 && anInt971 == anInt907) {
                if (j == 8 && aString970.length() > 0)
                    aString970 = aString970.substring(0, aString970.length() - 1);
                if ((j >= 97 && j <= 122 || j >= 65 && j <= 90 || j >= 48 && j <= 57 || j == 32)
                        && aString970.length() < 12)
                    aString970 += (char) j;
            } else if (aBoolean869) {
                if (j >= 32 && j <= 122 && aString765.length() < 80) {
                    aString765 += (char) j;
                    aBoolean965 = true;
                }
                if (j == 8 && aString765.length() > 0) {
                    aString765 = aString765.substring(0, aString765.length() - 1);
                    aBoolean965 = true;
                }
                if (j == 13 || j == 10) {
                    aBoolean869 = false;
                    aBoolean965 = true;
                    if (anInt760 == 1) {
                        long l = StringUtils.toBase37(aString765);
                        method100(l, -460);
                    }
                    if (anInt760 == 2 && anInt1089 > 0) {
                        long l1 = StringUtils.toBase37(aString765);
                        method113(43808, l1);
                    }
                    if (anInt760 == 3 && aString765.length() > 0) {
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 148);
                        aClass38_Sub2_Sub3_798.method436(0);
                        int k = aClass38_Sub2_Sub3_798.offset;
                        aClass38_Sub2_Sub3_798.method442(true, aLong900);
                        TextEncoder.write(aClass38_Sub2_Sub3_798, aString765);
                        aClass38_Sub2_Sub3_798.method445(0, aClass38_Sub2_Sub3_798.offset - k);
                        aString765 = StringUtils.toSentence(aString765);
                        aString765 = Class24.method239(aString765, 0);
                        method111(6, aString765, (byte) 4, StringUtils.formatName(StringUtils.fromBase37(aLong900)));
                        if (anInt755 == 2) {
                            anInt755 = 1;
                            aBoolean921 = true;
                            aClass38_Sub2_Sub3_798.method435((byte) -34, 244);
                            aClass38_Sub2_Sub3_798.method436(anInt976);
                            aClass38_Sub2_Sub3_798.method436(anInt755);
                            aClass38_Sub2_Sub3_798.method436(anInt885);
                        }
                    }
                    if (anInt760 == 4 && anInt793 < 100) {
                        long l2 = StringUtils.toBase37(aString765);
                        method21(l2, (byte) 3);
                    }
                    if (anInt760 == 5 && anInt793 > 0) {
                        long l3 = StringUtils.toBase37(aString765);
                        method130(1, l3);
                    }
                }
            } else if (aBoolean1055) {
                if (j >= 48 && j <= 57 && aString784.length() < 10) {
                    aString784 += (char) j;
                    aBoolean965 = true;
                }
                if (j == 8 && aString784.length() > 0) {
                    aString784 = aString784.substring(0, aString784.length() - 1);
                    aBoolean965 = true;
                }
                if (j == 13 || j == 10) {
                    if (aString784.length() > 0) {
                        int i1 = 0;
                        try {
                            i1 = Integer.parseInt(aString784);
                        } catch (Exception _ex) {
                        }
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 237);
                        aClass38_Sub2_Sub3_798.method440(i1);
                    }
                    aBoolean1055 = false;
                    aBoolean965 = true;
                }
            } else if (anInt1001 == -1) {
                if (j >= 32 && j <= 122 && aString1137.length() < 80) {
                    aString1137 += (char) j;
                    aBoolean965 = true;
                }
                if (j == 8 && aString1137.length() > 0) {
                    aString1137 = aString1137.substring(0, aString1137.length() - 1);
                    aBoolean965 = true;
                }
                if ((j == 13 || j == 10) && aString1137.length() > 0) {
                    if (aString1137.equals("::clientdrop")
                            && (super.frame != null || method73(-7437).indexOf("192.168.1.") != -1))
                        method121(false);
                    else if (aString1137.startsWith("::")) {
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 4);
                        aClass38_Sub2_Sub3_798.method436(aString1137.length() - 1);
                        aClass38_Sub2_Sub3_798.method443(aString1137.substring(2));
                    } else {
                        int j1 = 0;
                        if (aString1137.startsWith("yellow:")) {
                            j1 = 0;
                            aString1137 = aString1137.substring(7);
                        }
                        if (aString1137.startsWith("red:")) {
                            j1 = 1;
                            aString1137 = aString1137.substring(4);
                        }
                        if (aString1137.startsWith("green:")) {
                            j1 = 2;
                            aString1137 = aString1137.substring(6);
                        }
                        if (aString1137.startsWith("cyan:")) {
                            j1 = 3;
                            aString1137 = aString1137.substring(5);
                        }
                        if (aString1137.startsWith("purple:")) {
                            j1 = 4;
                            aString1137 = aString1137.substring(7);
                        }
                        if (aString1137.startsWith("white:")) {
                            j1 = 5;
                            aString1137 = aString1137.substring(6);
                        }
                        if (aString1137.startsWith("flash1:")) {
                            j1 = 6;
                            aString1137 = aString1137.substring(7);
                        }
                        if (aString1137.startsWith("flash2:")) {
                            j1 = 7;
                            aString1137 = aString1137.substring(7);
                        }
                        if (aString1137.startsWith("flash3:")) {
                            j1 = 8;
                            aString1137 = aString1137.substring(7);
                        }
                        if (aString1137.startsWith("glow1:")) {
                            j1 = 9;
                            aString1137 = aString1137.substring(6);
                        }
                        if (aString1137.startsWith("glow2:")) {
                            j1 = 10;
                            aString1137 = aString1137.substring(6);
                        }
                        if (aString1137.startsWith("glow3:")) {
                            j1 = 11;
                            aString1137 = aString1137.substring(6);
                        }
                        int k1 = 0;
                        if (aString1137.startsWith("wave:")) {
                            k1 = 1;
                            aString1137 = aString1137.substring(5);
                        }
                        if (aString1137.startsWith("scroll:")) {
                            k1 = 2;
                            aString1137 = aString1137.substring(7);
                        }
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 158);
                        aClass38_Sub2_Sub3_798.method436(0);
                        int i2 = aClass38_Sub2_Sub3_798.offset;
                        aClass38_Sub2_Sub3_798.method436(j1);
                        aClass38_Sub2_Sub3_798.method436(k1);
                        TextEncoder.write(aClass38_Sub2_Sub3_798, aString1137);
                        aClass38_Sub2_Sub3_798.method445(0, aClass38_Sub2_Sub3_798.offset - i2);
                        aString1137 = StringUtils.toSentence(aString1137);
                        aString1137 = Class24.method239(aString1137, 0);
                        aClass38_Sub7_Sub3_Sub2_967.aString1392 = aString1137;
                        aClass38_Sub7_Sub3_Sub2_967.anInt1394 = j1;
                        aClass38_Sub7_Sub3_Sub2_967.anInt1395 = k1;
                        aClass38_Sub7_Sub3_Sub2_967.anInt1393 = 150;
                        method111(2, aClass38_Sub7_Sub3_Sub2_967.aString1392, (byte) 4,
                                aClass38_Sub7_Sub3_Sub2_967.aString1505);
                        if (anInt976 == 2) {
                            anInt976 = 3;
                            aBoolean921 = true;
                            aClass38_Sub2_Sub3_798.method435((byte) -34, 244);
                            aClass38_Sub2_Sub3_798.method436(anInt976);
                            aClass38_Sub2_Sub3_798.method436(anInt755);
                            aClass38_Sub2_Sub3_798.method436(anInt885);
                        }
                    }
                    aString1137 = "";
                    aBoolean965 = true;
                }
            }
        } while (true);
    }

    public void method9(boolean flag) {
        if (flag)
            anInt796 = -398;
        if (aBoolean1070 || aBoolean865 || aBoolean923) {
            method127(false);
            return;
        }
        if (!aBoolean974)
            method55(4);
        else
            method69(29510);
        anInt934 = 0;
    }

    public void method36(byte byte0) {
        if (byte0 != -70)
            linkedList3dArray = null;
        if (anInt1109 == 0) {
            int i = super.anInt12 / 2 - 80;
            int l = super.anInt13 / 2 + 20;
            l += 20;
            if (super.anInt23 == 1 && super.anInt24 >= i - 75 && super.anInt24 <= i + 75 && super.anInt25 >= l - 20
                    && super.anInt25 <= l + 20) {
                anInt1109 = 3;
                anInt972 = 0;
            }
            i = super.anInt12 / 2 + 80;
            if (super.anInt23 == 1 && super.anInt24 >= i - 75 && super.anInt24 <= i + 75 && super.anInt25 >= l - 20
                    && super.anInt25 <= l + 20) {
                aString1083 = "";
                aString1084 = "Enter your username & password.";
                anInt1109 = 2;
                anInt972 = 0;
                return;
            }
        } else {
            if (anInt1109 == 2) {
                int j = super.anInt13 / 2 - 40;
                j += 30;
                j += 25;
                if (super.anInt23 == 1 && super.anInt25 >= j - 15 && super.anInt25 < j)
                    anInt972 = 0;
                j += 15;
                if (super.anInt23 == 1 && super.anInt25 >= j - 15 && super.anInt25 < j)
                    anInt972 = 1;
                j += 15;
                int i1 = super.anInt12 / 2 - 80;
                int k1 = super.anInt13 / 2 + 50;
                k1 += 20;
                if (super.anInt23 == 1 && super.anInt24 >= i1 - 75 && super.anInt24 <= i1 + 75
                        && super.anInt25 >= k1 - 20 && super.anInt25 <= k1 + 20)
                    method98(aString1066, aString1067, false);
                i1 = super.anInt12 / 2 + 80;
                if (super.anInt23 == 1 && super.anInt24 >= i1 - 75 && super.anInt24 <= i1 + 75
                        && super.anInt25 >= k1 - 20 && super.anInt25 <= k1 + 20) {
                    anInt1109 = 0;
                    aString1066 = "";
                    aString1067 = "";
                }
                do {
                    int l1 = method5(false);
                    if (l1 == -1)
                        break;
                    boolean flag = false;
                    for (int i2 = 0; i2 < aString725.length(); i2++) {
                        if (l1 != aString725.charAt(i2))
                            continue;
                        flag = true;
                        break;
                    }

                    if (anInt972 == 0) {
                        if (l1 == 8 && aString1066.length() > 0)
                            aString1066 = aString1066.substring(0, aString1066.length() - 1);
                        if (l1 == 9 || l1 == 10 || l1 == 13)
                            anInt972 = 1;
                        if (flag)
                            aString1066 += (char) l1;
                        if (aString1066.length() > 12)
                            aString1066 = aString1066.substring(0, 12);
                    } else if (anInt972 == 1) {
                        if (l1 == 8 && aString1067.length() > 0)
                            aString1067 = aString1067.substring(0, aString1067.length() - 1);
                        if (l1 == 9 || l1 == 10 || l1 == 13)
                            anInt972 = 0;
                        if (flag)
                            aString1067 += (char) l1;
                        if (aString1067.length() > 20)
                            aString1067 = aString1067.substring(0, 20);
                    }
                } while (true);
                return;
            }
            if (anInt1109 == 3) {
                int k = super.anInt12 / 2;
                int j1 = super.anInt13 / 2 + 50;
                j1 += 20;
                if (super.anInt23 == 1 && super.anInt24 >= k - 75 && super.anInt24 <= k + 75 && super.anInt25 >= j1 - 20
                        && super.anInt25 <= j1 + 20)
                    anInt1109 = 0;
            }
        }
    }

    public FileArchive method37(String s, int i, String s1, int j, int k) {
        int l = 5;
        byte[] abyte0 = signlink.cacheload(s1);
        if (abyte0 != null) {
            aCRC32_996.reset();
            aCRC32_996.update(abyte0);
            int i1 = (int) aCRC32_996.getValue();
            if (i1 != i)
                abyte0 = null;
        }
        if (abyte0 != null) {
            FileArchive fileArchive = new FileArchive(abyte0);
            return fileArchive;
        }
        while (abyte0 == null) {
            method13(true, "Requesting " + s, j);
            try {
                int j1 = 0;
                DataInputStream datainputstream = method94(s1 + i);
                byte[] abyte1 = new byte[6];
                datainputstream.readFully(abyte1, 0, 6);
                Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, abyte1);
                class38_sub2_sub3.offset = 3;
                int i2 = class38_sub2_sub3.method450() + 6;
                int j2 = 6;
                abyte0 = new byte[i2];
                for (int k2 = 0; k2 < 6; k2++)
                    abyte0[k2] = abyte1[k2];

                while (j2 < i2) {
                    int l2 = i2 - j2;
                    if (l2 > 1000)
                        l2 = 1000;
                    j2 += datainputstream.read(abyte0, j2, l2);
                    int i3 = (j2 * 100) / i2;
                    if (i3 != j1)
                        method13(true, "Loading " + s + " - " + i3 + "%", j);
                    j1 = i3;
                }
                datainputstream.close();
            } catch (IOException _ex) {
                abyte0 = null;
                for (int k1 = l; k1 > 0; k1--) {
                    method13(true, "Error loading - Will retry in " + k1 + " secs.", j);
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception _ex2) {
                    }
                }

                l *= 2;
                if (l > 60)
                    l = 60;
            }
        }
        signlink.cachesave(s1, abyte0);
        if (k != 0) {
            for (int l1 = 1; l1 > 0; l1++)
                ;
        }
        FileArchive fileArchive_1 = new FileArchive(abyte0);
        return fileArchive_1;
    }

    public void method38(boolean flag) {
        aBoolean902 = false;
        while (aBoolean1121) {
            aBoolean902 = false;
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
        aClass38_Sub2_Sub2_Sub3_1103 = null;
        aClass38_Sub2_Sub2_Sub3_1104 = null;
        aClass38_Sub2_Sub2_Sub3Array973 = null;
        anIntArray903 = null;
        anIntArray904 = null;
        anIntArray905 = null;
        anIntArray906 = null;
        anIntArray841 = null;
        anIntArray842 = null;
        if (!flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        anIntArray991 = null;
        anIntArray992 = null;
        aClass38_Sub2_Sub2_Sub2_978 = null;
        aClass38_Sub2_Sub2_Sub2_979 = null;
    }

    public void method39(int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = 2048 - l & 0x7ff;
        int i2 = 2048 - k & 0x7ff;
        int j2 = 0;
        int k2 = 0;
        int l2 = k1;
        if (l1 != 0) {
            int i3 = Class38_Sub2_Sub1.anIntArray1300[l1];
            int k3 = Class38_Sub2_Sub1.anIntArray1301[l1];
            int i4 = k2 * k3 - l2 * i3 >> 16;
            l2 = k2 * i3 + l2 * k3 >> 16;
            k2 = i4;
        }
        if (i2 != 0) {
            int j3 = Class38_Sub2_Sub1.anIntArray1300[i2];
            int l3 = Class38_Sub2_Sub1.anIntArray1301[i2];
            int j4 = l2 * j3 + j2 * l3 >> 16;
            l2 = l2 * l3 - j2 * j3 >> 16;
            j2 = j4;
        }
        anInt1111 = j - j2;
        anInt1112 = i - k2;
        anInt1113 = j1 - l2;
        anInt1114 = l;
        if (i1 != 16418)
            linkedList3dArray = null;
        anInt1115 = k;
    }

    public static String method40(int i, int j) {
        String s = String.valueOf(i);
        for (int k = s.length() - 3; k > 0; k -= 3)
            s = s.substring(0, k) + "," + s.substring(k);

        if (j != 0)
            throw new NullPointerException();
        if (s.length() > 8)
            s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
        else if (s.length() > 4)
            s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
        return " " + s;
    }

    public void method41(int i, boolean flag) {
        if (!flag)
            anInt780 = -1;
        if (!aBoolean889) {
            if (Class38_Sub2_Sub2_Sub1.anIntArray1456[17] >= i) {
                Class38_Sub2_Sub2_Sub3 class38_sub2_sub2_sub3 = Class38_Sub2_Sub2_Sub1.aClass38_Sub2_Sub2_Sub3Array1450[17];
                int j = class38_sub2_sub2_sub3.anInt1478 * class38_sub2_sub2_sub3.anInt1479 - 1;
                int l = class38_sub2_sub2_sub3.anInt1478 * anInt969 * 2;
                byte[] abyte0 = class38_sub2_sub2_sub3.aByteArray1476;
                byte[] abyte2 = aByteArray1069;
                for (int j1 = 0; j1 <= j; j1++)
                    abyte2[j1] = abyte0[j1 - l & j];

                class38_sub2_sub2_sub3.aByteArray1476 = abyte2;
                aByteArray1069 = abyte0;
                Class38_Sub2_Sub2_Sub1.method391(17, 150);
            }
            if (Class38_Sub2_Sub2_Sub1.anIntArray1456[24] >= i) {
                Class38_Sub2_Sub2_Sub3 class38_sub2_sub2_sub3_1 = Class38_Sub2_Sub2_Sub1.aClass38_Sub2_Sub2_Sub3Array1450[24];
                int k = class38_sub2_sub2_sub3_1.anInt1478 * class38_sub2_sub2_sub3_1.anInt1479 - 1;
                int i1 = class38_sub2_sub2_sub3_1.anInt1478 * anInt969 * 2;
                byte[] abyte1 = class38_sub2_sub2_sub3_1.aByteArray1476;
                byte[] abyte3 = aByteArray1069;
                for (int k1 = 0; k1 <= k; k1++)
                    abyte3[k1] = abyte1[k1 - i1 & k];

                class38_sub2_sub2_sub3_1.aByteArray1476 = abyte3;
                aByteArray1069 = abyte1;
                Class38_Sub2_Sub2_Sub1.method391(24, 150);
            }
        }
    }

    public void method42(boolean flag) {
        char c = '\u0100';
        for (int i = 10; i < 117; i++) {
            int j = (int) (Math.random() * 100D);
            if (j < 50)
                anIntArray991[i + (c - 2 << 7)] = 255;
        }

        for (int k = 0; k < 100; k++) {
            int l = (int) (Math.random() * 124D) + 2;
            int j1 = (int) (Math.random() * 128D) + 128;
            int j2 = l + (j1 << 7);
            anIntArray991[j2] = 192;
        }

        if (!flag)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
        for (int i1 = 1; i1 < c - 1; i1++) {
            for (int k1 = 1; k1 < 127; k1++) {
                int k2 = k1 + (i1 << 7);
                anIntArray992[k2] = (anIntArray991[k2 - 1] + anIntArray991[k2 + 1] + anIntArray991[k2 - 128]
                        + anIntArray991[k2 + 128]) / 4;
            }

        }

        anInt1156 += 128;
        if (anInt1156 > anIntArray841.length) {
            anInt1156 -= anIntArray841.length;
            int l1 = (int) (Math.random() * 12D);
            method122(578, aClass38_Sub2_Sub2_Sub3Array973[l1]);
        }
        for (int i2 = 1; i2 < c - 1; i2++) {
            for (int l2 = 1; l2 < 127; l2++) {
                int j3 = l2 + (i2 << 7);
                int l3 = anIntArray992[j3 + 128] - anIntArray841[j3 + anInt1156 & anIntArray841.length - 1] / 5;
                if (l3 < 0)
                    l3 = 0;
                anIntArray991[j3] = l3;
            }

        }

        for (int i3 = 0; i3 < c - 1; i3++)
            anIntArray850[i3] = anIntArray850[i3 + 1];

        anIntArray850[c - 1] = (int) (Math.sin((double) anInt955 / 14D) * 16D + Math.sin((double) anInt955 / 15D) * 14D
                + Math.sin((double) anInt955 / 16D) * 12D);
        if (anInt874 > 0)
            anInt874 -= 4;
        if (anInt875 > 0)
            anInt875 -= 4;
        if (anInt874 == 0 && anInt875 == 0) {
            int k3 = (int) (Math.random() * 2000D);
            if (k3 == 0)
                anInt874 = 1024;
            if (k3 == 1)
                anInt875 = 1024;
        }
    }

    public void method43(byte byte0) {
        drawArea21.init2D();
        if (byte0 != -46)
            method6();
        int i = anInt817 + anInt1085 & 0x7ff;
        int j = 48 + aClass38_Sub7_Sub3_Sub2_967.anInt1380 / 32;
        int l1 = 464 - aClass38_Sub7_Sub3_Sub2_967.anInt1381 / 32;
        aClass38_Sub2_Sub2_Sub2_1053.method411(i, 146, anIntArray1133, 151, l1, 256 + anInt930, j, 21, 9, false,
                anIntArray953);
        aClass38_Sub2_Sub2_Sub2_1145.method411(anInt817, 33, anIntArray856, 33, 25, 256, 25, 0, 0, false,
                anIntArray1117);
        for (int j3 = 0; j3 < anInt917; j3++) {
            int k = (anIntArray918[j3] * 4 + 2) - aClass38_Sub7_Sub3_Sub2_967.anInt1380 / 32;
            int i2 = (anIntArray919[j3] * 4 + 2) - aClass38_Sub7_Sub3_Sub2_967.anInt1381 / 32;
            method87(i2, 4, aClass38_Sub2_Sub2_Sub2Array791[j3], k);
        }

        for (int k3 = 0; k3 < 104; k3++) {
            for (int l3 = 0; l3 < 104; l3++) {
                LinkedList linkedList = linkedList3dArray[anInt880][k3][l3];
                if (linkedList != null) {
                    int l = (k3 * 4 + 2) - aClass38_Sub7_Sub3_Sub2_967.anInt1380 / 32;
                    int j2 = (l3 * 4 + 2) - aClass38_Sub7_Sub3_Sub2_967.anInt1381 / 32;
                    method87(j2, 4, aClass38_Sub2_Sub2_Sub2_1057, l);
                }
            }

        }

        for (int i4 = 0; i4 < anInt928; i4++) {
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[anIntArray929[i4]];
            if (class38_sub7_sub3_sub1 != null && class38_sub7_sub3_sub1.method468(false)
                    && class38_sub7_sub3_sub1.npcType.aBoolean100) {
                int i1 = class38_sub7_sub3_sub1.anInt1380 / 32
                        - aClass38_Sub7_Sub3_Sub2_967.anInt1380 / 32;
                int k2 = class38_sub7_sub3_sub1.anInt1381 / 32
                        - aClass38_Sub7_Sub3_Sub2_967.anInt1381 / 32;
                method87(k2, 4, aClass38_Sub2_Sub2_Sub2_1058, i1);
            }
        }

        for (int j4 = 0; j4 < anInt823; j4++) {
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[anIntArray824[j4]];
            if (class38_sub7_sub3_sub2 != null && class38_sub7_sub3_sub2.method468(false)) {
                int j1 = class38_sub7_sub3_sub2.anInt1380 / 32
                        - aClass38_Sub7_Sub3_Sub2_967.anInt1380 / 32;
                int l2 = class38_sub7_sub3_sub2.anInt1381 / 32
                        - aClass38_Sub7_Sub3_Sub2_967.anInt1381 / 32;
                boolean flag = false;
                long l4 = StringUtils.toBase37(class38_sub7_sub3_sub2.aString1505);
                for (int k4 = 0; k4 < anInt1089; k4++) {
                    if (l4 != aLongArray943[k4] || anIntArray773[k4] == 0)
                        continue;
                    flag = true;
                    break;
                }

                if (flag)
                    method87(l2, 4, aClass38_Sub2_Sub2_Sub2_1060, j1);
                else
                    method87(l2, 4, aClass38_Sub2_Sub2_Sub2_1059, j1);
            }
        }

        if (anInt1051 != 0) {
            int k1 = (anInt1051 * 4 + 2) - aClass38_Sub7_Sub3_Sub2_967.anInt1380 / 32;
            int i3 = (anInt1052 * 4 + 2) - aClass38_Sub7_Sub3_Sub2_967.anInt1381 / 32;
            method87(i3, 4, aClass38_Sub2_Sub2_Sub2_997, k1);
        }
        Class38_Sub2_Sub2.method380(82, 93, 0xffffff, (byte) 93, 3, 3);
        drawArea22.init2D();
    }

    public Component method11(byte byte0) {
        if (byte0 != 3)
            anInt759 = 260;
        if (signlink.mainapp != null)
            return signlink.mainapp;
        if (super.frame != null)
            return super.frame;
        else
            return this;
    }

    public void method44(int i) {
        if (i <= 0)
            method6();
        if (anInt1078 == 2) {
            for (TemporaryLoc temporaryLoc = (TemporaryLoc) linkedList3
                    .method270(); temporaryLoc != null; temporaryLoc = (TemporaryLoc) linkedList3.method272())
                if (anInt955 >= temporaryLoc.lastCycle) {
                    method99(temporaryLoc.rotation, temporaryLoc.tileX, temporaryLoc.tileZ,
                            temporaryLoc.classType, temporaryLoc.locIndex, temporaryLoc.type, -27819,
                            temporaryLoc.level);
                    temporaryLoc.unlink();
                }

            anInt1108++;
            if (anInt1108 > 85) {
                anInt1108 = 0;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 85);
            }
        }
    }

    public void method45(int i, int j) {
        int[] ai = aClass38_Sub2_Sub2_Sub2_1053.anIntArray1465;
        if (j >= 0)
            return;
        int k = ai.length;
        for (int l = 0; l < k; l++)
            ai[l] = 0;

        for (int i1 = 1; i1 < 103; i1++) {
            int j1 = 24628 + (103 - i1) * 512 * 4;
            for (int l1 = 1; l1 < 103; l1++) {
                if ((aByteArrayArrayArray840[i][l1][i1] & 0x18) == 0)
                    aClass32_831.method317(ai, j1, 512, i, l1, i1);
                if (i < 3 && (aByteArrayArrayArray840[i + 1][l1][i1] & 8) != 0)
                    aClass32_831.method317(ai, j1, 512, i + 1, l1, i1);
                j1 += 4;
            }

        }

        int k1 = ((238 + (int) (Math.random() * 20D)) - 10 << 16) + ((238 + (int) (Math.random() * 20D)) - 10 << 8)
                + ((238 + (int) (Math.random() * 20D)) - 10);
        int i2 = (238 + (int) (Math.random() * 20D)) - 10 << 16;
        aClass38_Sub2_Sub2_Sub2_1053.method401((byte) 62);
        for (int j2 = 1; j2 < 103; j2++) {
            for (int k2 = 1; k2 < 103; k2++) {
                if ((aByteArrayArrayArray840[i][k2][j2] & 0x18) == 0)
                    method46(anInt899, i, k1, k2, i2, j2);
                if (i < 3 && (aByteArrayArrayArray840[i + 1][k2][j2] & 8) != 0)
                    method46(anInt899, i + 1, k1, k2, i2, j2);
            }

        }

        drawArea22.init2D();
        anInt917 = 0;
        for (int l2 = 0; l2 < 104; l2++) {
            for (int i3 = 0; i3 < 104; i3++) {
                int j3 = aClass32_831.method311(anInt880, l2, i3);
                if (j3 != 0) {
                    j3 = j3 >> 14 & 0x7fff;
                    int k3 = LocType.get(j3).mapfunction;
                    if (k3 >= 0) {
                        int l3 = l2;
                        int i4 = i3;
                        if (k3 != 22 && k3 != 29 && k3 != 34 && k3 != 36 && k3 != 46 && k3 != 47 && k3 != 48) {
                            byte byte0 = 104;
                            byte byte1 = 104;
                            int[][] ai1 = aClass8Array954[anInt880].anIntArrayArray215;
                            for (int j4 = 0; j4 < 10; j4++) {
                                int k4 = (int) (Math.random() * 4D);
                                if (k4 == 0 && l3 > 0 && l3 > l2 - 3 && (ai1[l3 - 1][i4] & 0x280108) == 0)
                                    l3--;
                                if (k4 == 1 && l3 < byte0 - 1 && l3 < l2 + 3 && (ai1[l3 + 1][i4] & 0x280180) == 0)
                                    l3++;
                                if (k4 == 2 && i4 > 0 && i4 > i3 - 3 && (ai1[l3][i4 - 1] & 0x280102) == 0)
                                    i4--;
                                if (k4 == 3 && i4 < byte1 - 1 && i4 < i3 + 3 && (ai1[l3][i4 + 1] & 0x280120) == 0)
                                    i4++;
                            }

                        }
                        aClass38_Sub2_Sub2_Sub2Array791[anInt917] = aClass38_Sub2_Sub2_Sub2Array1138[k3];
                        anIntArray918[anInt917] = l3;
                        anIntArray919[anInt917] = i4;
                        anInt917++;
                    }
                }
            }

        }

    }

    public void method46(int i, int j, int k, int l, int i1, int j1) {
        int k1 = aClass32_831.method308(j, l, j1);
        if (k1 != 0) {
            int l1 = aClass32_831.method312(j, l, j1, k1);
            int k2 = l1 >> 6 & 3;
            int i3 = l1 & 0x1f;
            int k3 = k;
            if (k1 > 0)
                k3 = i1;
            int[] ai = aClass38_Sub2_Sub2_Sub2_1053.anIntArray1465;
            int k4 = 24624 + l * 4 + (103 - j1) * 512 * 4;
            int i5 = k1 >> 14 & 0x7fff;
            LocType locType_2 = LocType.get(i5);
            if (locType_2.mapscene != -1) {
                Class38_Sub2_Sub2_Sub3 class38_sub2_sub2_sub3_2 = aClass38_Sub2_Sub2_Sub3Array1038[locType_2.mapscene];
                if (class38_sub2_sub2_sub3_2 != null) {
                    int i6 = (locType_2.sizeX * 4 - class38_sub2_sub2_sub3_2.anInt1478) / 2;
                    int j6 = (locType_2.sizeZ * 4 - class38_sub2_sub2_sub3_2.anInt1479) / 2;
                    class38_sub2_sub2_sub3_2.method419(48 + (104 - j1 - locType_2.sizeZ) * 4 + j6, 48 + l * 4 + i6,
                            false);
                }
            } else {
                if (i3 == 0 || i3 == 2)
                    if (k2 == 0) {
                        ai[k4] = k3;
                        ai[k4 + 512] = k3;
                        ai[k4 + 1024] = k3;
                        ai[k4 + 1536] = k3;
                    } else if (k2 == 1) {
                        ai[k4] = k3;
                        ai[k4 + 1] = k3;
                        ai[k4 + 2] = k3;
                        ai[k4 + 3] = k3;
                    } else if (k2 == 2) {
                        ai[k4 + 3] = k3;
                        ai[k4 + 3 + 512] = k3;
                        ai[k4 + 3 + 1024] = k3;
                        ai[k4 + 3 + 1536] = k3;
                    } else if (k2 == 3) {
                        ai[k4 + 1536] = k3;
                        ai[k4 + 1536 + 1] = k3;
                        ai[k4 + 1536 + 2] = k3;
                        ai[k4 + 1536 + 3] = k3;
                    }
                if (i3 == 3)
                    if (k2 == 0)
                        ai[k4] = k3;
                    else if (k2 == 1)
                        ai[k4 + 3] = k3;
                    else if (k2 == 2)
                        ai[k4 + 3 + 1536] = k3;
                    else if (k2 == 3)
                        ai[k4 + 1536] = k3;
                if (i3 == 2)
                    if (k2 == 3) {
                        ai[k4] = k3;
                        ai[k4 + 512] = k3;
                        ai[k4 + 1024] = k3;
                        ai[k4 + 1536] = k3;
                    } else if (k2 == 0) {
                        ai[k4] = k3;
                        ai[k4 + 1] = k3;
                        ai[k4 + 2] = k3;
                        ai[k4 + 3] = k3;
                    } else if (k2 == 1) {
                        ai[k4 + 3] = k3;
                        ai[k4 + 3 + 512] = k3;
                        ai[k4 + 3 + 1024] = k3;
                        ai[k4 + 3 + 1536] = k3;
                    } else if (k2 == 2) {
                        ai[k4 + 1536] = k3;
                        ai[k4 + 1536 + 1] = k3;
                        ai[k4 + 1536 + 2] = k3;
                        ai[k4 + 1536 + 3] = k3;
                    }
            }
        }
        k1 = aClass32_831.method310(j, l, j1);
        i = 56 / i;
        if (k1 != 0) {
            int i2 = aClass32_831.method312(j, l, j1, k1);
            int l2 = i2 >> 6 & 3;
            int j3 = i2 & 0x1f;
            int l3 = k1 >> 14 & 0x7fff;
            LocType locType = LocType.get(l3);
            if (locType.mapscene != -1) {
                Class38_Sub2_Sub2_Sub3 class38_sub2_sub2_sub3_1 = aClass38_Sub2_Sub2_Sub3Array1038[locType.mapscene];
                if (class38_sub2_sub2_sub3_1 != null) {
                    int j5 = (locType.sizeX * 4 - class38_sub2_sub2_sub3_1.anInt1478) / 2;
                    int k5 = (locType.sizeZ * 4 - class38_sub2_sub2_sub3_1.anInt1479) / 2;
                    class38_sub2_sub2_sub3_1.method419(48 + (104 - j1 - locType.sizeZ) * 4 + k5, 48 + l * 4 + j5,
                            false);
                }
            } else if (j3 == 9) {
                int l4 = 0xeeeeee;
                if (k1 > 0)
                    l4 = 0xee0000;
                int[] ai1 = aClass38_Sub2_Sub2_Sub2_1053.anIntArray1465;
                int l5 = 24624 + l * 4 + (103 - j1) * 512 * 4;
                if (l2 == 0 || l2 == 2) {
                    ai1[l5 + 1536] = l4;
                    ai1[l5 + 1024 + 1] = l4;
                    ai1[l5 + 512 + 2] = l4;
                    ai1[l5 + 3] = l4;
                } else {
                    ai1[l5] = l4;
                    ai1[l5 + 512 + 1] = l4;
                    ai1[l5 + 1024 + 2] = l4;
                    ai1[l5 + 1536 + 3] = l4;
                }
            }
        }
        k1 = aClass32_831.method311(j, l, j1);
        if (k1 != 0) {
            int j2 = k1 >> 14 & 0x7fff;
            LocType locType = LocType.get(j2);
            if (locType.mapscene != -1) {
                Class38_Sub2_Sub2_Sub3 class38_sub2_sub2_sub3 = aClass38_Sub2_Sub2_Sub3Array1038[locType.mapscene];
                if (class38_sub2_sub2_sub3 != null) {
                    int i4 = (locType.sizeX * 4 - class38_sub2_sub2_sub3.anInt1478) / 2;
                    int j4 = (locType.sizeZ * 4 - class38_sub2_sub2_sub3.anInt1479) / 2;
                    class38_sub2_sub2_sub3.method419(48 + (104 - j1 - locType.sizeZ) * 4 + j4, 48 + l * 4 + i4, false);
                }
            }
        }
    }

    public void method47(Class38_Sub2_Sub3 class38_sub2_sub3, int i, boolean flag) {
        anInt939 = 0;
        anInt825 = 0;
        method120(-542, class38_sub2_sub3, i);
        method80(false, class38_sub2_sub3, i);
        method20(class38_sub2_sub3, i, anInt1071);
        if (flag)
            anInt958 = 338;
        for (int j = 0; j < anInt939; j++) {
            int k = anIntArray940[j];
            if (aClass38_Sub7_Sub3_Sub1Array927[k].anInt1424 != anInt955) {
                aClass38_Sub7_Sub3_Sub1Array927[k].npcType = null;
                aClass38_Sub7_Sub3_Sub1Array927[k] = null;
            }
        }

        if (class38_sub2_sub3.offset != i) {
            signlink.reporterror(
                    aString1066 + " size mismatch in getnpcpos - pos:" + class38_sub2_sub3.offset + " psize:" + i);
            throw new RuntimeException("eek");
        }
        for (int l = 0; l < anInt928; l++)
            if (aClass38_Sub7_Sub3_Sub1Array927[anIntArray929[l]] == null) {
                signlink.reporterror(aString1066 + " null entry in npc list - pos:" + l + " size:" + anInt928);
                throw new RuntimeException("eek");
            }

    }

    public void method12(Runnable runnable, int i) {
        if (signlink.mainapp != null) {
            signlink.startthread(runnable, i);
            return;
        } else {
            super.method12(runnable, i);
            return;
        }
    }

    public void method48(int i) {
        aClass38_Sub2_Sub2_Sub3_1103 = new Class38_Sub2_Sub2_Sub3(fileArchive, "titlebox", 0);
        aClass38_Sub2_Sub2_Sub3_1104 = new Class38_Sub2_Sub2_Sub3(fileArchive, "titlebutton", 0);
        if (i != 0)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
        aClass38_Sub2_Sub2_Sub3Array973 = new Class38_Sub2_Sub2_Sub3[12];
        for (int j = 0; j < 12; j++)
            aClass38_Sub2_Sub2_Sub3Array973[j] = new Class38_Sub2_Sub2_Sub3(fileArchive, "runes", j);

        aClass38_Sub2_Sub2_Sub2_978 = new Class38_Sub2_Sub2_Sub2(128, 265);
        aClass38_Sub2_Sub2_Sub2_979 = new Class38_Sub2_Sub2_Sub2(128, 265);
        for (int k = 0; k < 33920; k++)
            aClass38_Sub2_Sub2_Sub2_978.anIntArray1465[k] = drawArea14.pixels[k];

        for (int l = 0; l < 33920; l++)
            aClass38_Sub2_Sub2_Sub2_979.anIntArray1465[l] = drawArea15.pixels[l];

        anIntArray904 = new int[256];
        for (int i1 = 0; i1 < 64; i1++)
            anIntArray904[i1] = i1 * 0x40000;

        for (int j1 = 0; j1 < 64; j1++)
            anIntArray904[j1 + 64] = 0xff0000 + 1024 * j1;

        for (int k1 = 0; k1 < 64; k1++)
            anIntArray904[k1 + 128] = 0xffff00 + 4 * k1;

        for (int l1 = 0; l1 < 64; l1++)
            anIntArray904[l1 + 192] = 0xffffff;

        anIntArray905 = new int[256];
        for (int i2 = 0; i2 < 64; i2++)
            anIntArray905[i2] = i2 * 1024;

        for (int j2 = 0; j2 < 64; j2++)
            anIntArray905[j2 + 64] = 65280 + 4 * j2;

        for (int k2 = 0; k2 < 64; k2++)
            anIntArray905[k2 + 128] = 65535 + 0x40000 * k2;

        for (int l2 = 0; l2 < 64; l2++)
            anIntArray905[l2 + 192] = 0xffffff;

        anIntArray906 = new int[256];
        for (int i3 = 0; i3 < 64; i3++)
            anIntArray906[i3] = i3 * 4;

        for (int j3 = 0; j3 < 64; j3++)
            anIntArray906[j3 + 64] = 255 + 0x40000 * j3;

        for (int k3 = 0; k3 < 64; k3++)
            anIntArray906[k3 + 128] = 0xff00ff + 1024 * k3;

        for (int l3 = 0; l3 < 64; l3++)
            anIntArray906[l3 + 192] = 0xffffff;

        anIntArray903 = new int[256];
        anIntArray841 = new int[32768];
        anIntArray842 = new int[32768];
        method122(578, null);
        anIntArray991 = new int[32768];
        anIntArray992 = new int[32768];
        method13(true, "Connecting to fileserver", 10);
        if (!aBoolean902) {
            aBoolean975 = true;
            aBoolean902 = true;
            method12(this, 2);
        }
    }

    public void method49(int i, Class38_Sub2_Sub3 class38_sub2_sub3, int j) {
        int k = class38_sub2_sub3.method457(9, 8);
        if (j != 0) {
            for (int l = 1; l > 0; l++)
                ;
        }
        if (k < anInt823) {
            for (int i1 = k; i1 < anInt823; i1++)
                anIntArray940[anInt939++] = anIntArray824[i1];

        }
        if (k > anInt823) {
            signlink.reporterror(aString1066 + " Too many players");
            throw new RuntimeException("eek");
        }
        anInt823 = 0;
        for (int j1 = 0; j1 < k; j1++) {
            int k1 = anIntArray824[j1];
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[k1];
            int l1 = class38_sub2_sub3.method457(9, 1);
            if (l1 == 0) {
                anIntArray824[anInt823++] = k1;
                class38_sub7_sub3_sub2.anInt1424 = anInt955;
            } else {
                int i2 = class38_sub2_sub3.method457(9, 2);
                if (i2 == 0) {
                    anIntArray824[anInt823++] = k1;
                    class38_sub7_sub3_sub2.anInt1424 = anInt955;
                    anIntArray826[anInt825++] = k1;
                } else if (i2 == 1) {
                    anIntArray824[anInt823++] = k1;
                    class38_sub7_sub3_sub2.anInt1424 = anInt955;
                    int j2 = class38_sub2_sub3.method457(9, 3);
                    class38_sub7_sub3_sub2.method467(false, j2, (byte) 6);
                    int l2 = class38_sub2_sub3.method457(9, 1);
                    if (l2 == 1)
                        anIntArray826[anInt825++] = k1;
                } else if (i2 == 2) {
                    anIntArray824[anInt823++] = k1;
                    class38_sub7_sub3_sub2.anInt1424 = anInt955;
                    int k2 = class38_sub2_sub3.method457(9, 3);
                    class38_sub7_sub3_sub2.method467(true, k2, (byte) 6);
                    int i3 = class38_sub2_sub3.method457(9, 3);
                    class38_sub7_sub3_sub2.method467(true, i3, (byte) 6);
                    int j3 = class38_sub2_sub3.method457(9, 1);
                    if (j3 == 1)
                        anIntArray826[anInt825++] = k1;
                } else if (i2 == 3)
                    anIntArray940[anInt939++] = k1;
            }
        }

    }

    public void method50(int i, int j, int k, int l, int i1, int j1) {
        aClass38_Sub2_Sub2_Sub3_1081.method419(k, j, false);
        aClass38_Sub2_Sub2_Sub3_1082.method419((k + j1) - 16, j, false);
        Class38_Sub2_Sub2.method380(k + 16, j, anInt1050, (byte) 93, 16, j1 - 32);
        int k1 = ((j1 - 32) * j1) / i1;
        if (k1 < 8)
            k1 = 8;
        int l1 = ((j1 - 32 - k1) * l) / (i1 - j1);
        if (i <= 0)
            anInt780 = -1;
        Class38_Sub2_Sub2.method380(k + 16 + l1, j, anInt1158, (byte) 93, 16, k1);
        Class38_Sub2_Sub2.method383(anInt993, anInt727, k + 16 + l1, k1, j);
        Class38_Sub2_Sub2.method383(anInt993, anInt727, k + 16 + l1, k1, j + 1);
        Class38_Sub2_Sub2.method382(anInt993, 0, k + 16 + l1, 16, j);
        Class38_Sub2_Sub2.method382(anInt993, 0, k + 17 + l1, 16, j);
        Class38_Sub2_Sub2.method383(anInt980, anInt727, k + 16 + l1, k1, j + 15);
        Class38_Sub2_Sub2.method383(anInt980, anInt727, k + 17 + l1, k1 - 1, j + 14);
        Class38_Sub2_Sub2.method382(anInt980, 0, k + 15 + l1 + k1, 16, j);
        Class38_Sub2_Sub2.method382(anInt980, 0, k + 14 + l1 + k1, 15, j + 1);
    }

    public void method51(byte byte0) {
        aBoolean788 = true;
        if (byte0 != -6)
            linkedList3dArray = null;
        for (int i = 0; i < 7; i++) {
            anIntArray789[i] = -1;
            for (int j = 0; j < IdkType.anInt246; j++) {
                if (IdkType.idkTypes[j].aBoolean253
                        || IdkType.idkTypes[j].anInt248 != i + (aBoolean836 ? 0 : 7))
                    continue;
                anIntArray789[i] = j;
                break;
            }

        }

    }

    public void method52(byte[] abyte0, int i, int j, boolean flag) {
        if (i <= 0)
            aBoolean849 = !aBoolean849;
        signlink.midifade = flag ? 1 : 0;
        signlink.midisave(abyte0, j);
    }

    public void method53(boolean flag) {
        for (int i = 0; i < anInt928; i++) {
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[anIntArray929[i]];
            int j = 0x20000000 + (anIntArray929[i] << 14);
            if (class38_sub7_sub3_sub1 == null || !class38_sub7_sub3_sub1.method468(false))
                continue;
            int k = class38_sub7_sub3_sub1.anInt1380 >> 7;
            int l = class38_sub7_sub3_sub1.anInt1381 >> 7;
            if (k < 0 || k >= 104 || l < 0 || l >= 104)
                continue;
            if (class38_sub7_sub3_sub1.anInt1384 == 1
                    && (class38_sub7_sub3_sub1.anInt1380 & 0x7f) == 64
                    && (class38_sub7_sub3_sub1.anInt1381 & 0x7f) == 64) {
                if (anIntArrayArray920[k][l] == anInt837)
                    continue;
                anIntArrayArray920[k][l] = anInt837;
            }
            aClass32_831.method292(-44713, class38_sub7_sub3_sub1.anInt1381,
                    (class38_sub7_sub3_sub1.anInt1384 - 1) * 64 + 60,
                    class38_sub7_sub3_sub1.anInt1382,
                    class38_sub7_sub3_sub1.anInt1380, j,
                    class38_sub7_sub3_sub1.aBoolean1383, null, class38_sub7_sub3_sub1,
                    method33(anInt880, class38_sub7_sub3_sub1.anInt1380, (byte) 5,
                            class38_sub7_sub3_sub1.anInt1381),
                    anInt880);
        }

        if (flag)
            anInt780 = -1;
    }

    public void method54(int i, int j, boolean flag) {
        signlink.midivol = j;
        anInt779 += i;
        if (flag)
            signlink.midi = "voladjust";
    }

    public void method55(int i) {
        method95((byte) 99);
        drawArea13.init2D();
        if (i < 4 || i > 4)
            return;
        aClass38_Sub2_Sub2_Sub3_1103.method419(0, 0, false);
        char c = '\u0168';
        char c1 = '\310';
        if (anInt1109 == 0) {
            int j = c1 / 2 - 20;
            aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffff00, true, j, "Welcome to RuneScape", 0);
            j += 30;
            int i1 = c / 2 - 80;
            int l1 = c1 / 2 + 20;
            aClass38_Sub2_Sub2_Sub3_1104.method419(l1 - 20, i1 - 73, false);
            aClass38_Sub2_Sub2_Sub4_987.method422(i1, 0xffffff, true, l1 + 5, "New user", 0);
            i1 = c / 2 + 80;
            aClass38_Sub2_Sub2_Sub3_1104.method419(l1 - 20, i1 - 73, false);
            aClass38_Sub2_Sub2_Sub4_987.method422(i1, 0xffffff, true, l1 + 5, "Existing User", 0);
        }
        if (anInt1109 == 2) {
            int k = c1 / 2 - 40;
            if (aString1083.length() > 0) {
                aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffff00, true, k - 15, aString1083, 0);
                aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffff00, true, k, aString1084, 0);
                k += 30;
            } else {
                aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffff00, true, k - 7, aString1084, 0);
                k += 30;
            }
            aClass38_Sub2_Sub2_Sub4_987.method426(c / 2 - 90, 6, k,
                    "Username: " + aString1066 + ((anInt972 == 0) & (anInt955 % 40 < 20) ? "@yel@|" : ""), true,
                    0xffffff);
            k += 15;
            aClass38_Sub2_Sub2_Sub4_987.method426(c / 2 - 88, 6, k, "Password: " + StringUtils.toAsterisks(aString1067)
                    + ((anInt972 == 1) & (anInt955 % 40 < 20) ? "@yel@|" : ""), true, 0xffffff);
            k += 15;
            int j1 = c / 2 - 80;
            int i2 = c1 / 2 + 50;
            aClass38_Sub2_Sub2_Sub3_1104.method419(i2 - 20, j1 - 73, false);
            aClass38_Sub2_Sub2_Sub4_987.method422(j1, 0xffffff, true, i2 + 5, "Login", 0);
            j1 = c / 2 + 80;
            aClass38_Sub2_Sub2_Sub3_1104.method419(i2 - 20, j1 - 73, false);
            aClass38_Sub2_Sub2_Sub4_987.method422(j1, 0xffffff, true, i2 + 5, "Cancel", 0);
        }
        if (anInt1109 == 3) {
            aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffff00, true, c1 / 2 - 60, "Create a free account", 0);
            int l = c1 / 2 - 35;
            aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffffff, true, l, "To create a new account you need to", 0);
            l += 15;
            aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffffff, true, l, "go back to the main RuneScape webpage", 0);
            l += 15;
            aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffffff, true, l, "and choose the red 'create account'", 0);
            l += 15;
            aClass38_Sub2_Sub2_Sub4_987.method422(c / 2, 0xffffff, true, l, "button at the top right of that page.", 0);
            l += 15;
            int k1 = c / 2;
            int j2 = c1 / 2 + 50;
            aClass38_Sub2_Sub2_Sub3_1104.method419(j2 - 20, k1 - 73, false);
            aClass38_Sub2_Sub2_Sub4_987.method422(k1, 0xffffff, true, j2 + 5, "Cancel", 0);
        }
        drawArea13.drawImage(186, super.aGraphics14, 214);
        if (aBoolean751) {
            aBoolean751 = false;
            drawArea11.drawImage(0, super.aGraphics14, 128);
            drawArea12.drawImage(386, super.aGraphics14, 214);
            drawArea16.drawImage(265, super.aGraphics14, 0);
            drawArea17.drawImage(265, super.aGraphics14, 574);
            drawArea18.drawImage(186, super.aGraphics14, 128);
            drawArea19.drawImage(186, super.aGraphics14, 574);
        }
    }

    public void method56(int i) {
        if (drawArea23 != null)
            return;
        method38(true);
        super.drawArea = null;
        drawArea11 = null;
        drawArea12 = null;
        drawArea13 = null;
        drawArea14 = null;
        if (i != -7185) {
            return;
        } else {
            drawArea15 = null;
            drawArea16 = null;
            drawArea17 = null;
            drawArea18 = null;
            drawArea19 = null;
            drawArea23 = new DrawArea(method11(aByte1116), 479, 96);
            drawArea21 = new DrawArea(method11(aByte1116), 168, 160);
            Class38_Sub2_Sub2.method379(anInt1143);
            aClass38_Sub2_Sub2_Sub3_982.method419(0, 0, false);
            drawArea20 = new DrawArea(method11(aByte1116), 190, 261);
            drawArea22 = new DrawArea(method11(aByte1116), 512, 334);
            Class38_Sub2_Sub2.method379(anInt1143);
            drawArea24 = new DrawArea(method11(aByte1116), 501, 61);
            drawArea25 = new DrawArea(method11(aByte1116), 288, 40);
            drawArea26 = new DrawArea(method11(aByte1116), 269, 66);
            aBoolean751 = true;
            return;
        }
    }

    public void method57(int i, int j, Class38_Sub2_Sub3 class38_sub2_sub3) {
        if (i < 2 || i > 2) {
            for (int k = 1; k > 0; k++)
                ;
        }
        do {
            if (class38_sub2_sub3.anInt1330 + 10 >= j * 8)
                break;
            int l = class38_sub2_sub3.method457(9, 11);
            if (l == 2047)
                break;
            if (aClass38_Sub7_Sub3_Sub2Array822[l] == null) {
                aClass38_Sub7_Sub3_Sub2Array822[l] = new Class38_Sub7_Sub3_Sub2();
                if (aClass38_Sub2_Sub3Array827[l] != null)
                    aClass38_Sub7_Sub3_Sub2Array822[l].method470(false, aClass38_Sub2_Sub3Array827[l]);
            }
            anIntArray824[anInt823++] = l;
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[l];
            class38_sub7_sub3_sub2.anInt1424 = anInt955;
            int i1 = class38_sub2_sub3.method457(9, 5);
            if (i1 > 15)
                i1 -= 32;
            int j1 = class38_sub2_sub3.method457(9, 5);
            if (j1 > 15)
                j1 -= 32;
            int k1 = class38_sub2_sub3.method457(9, 1);
            class38_sub7_sub3_sub2.method466(false, k1 == 1,
                    aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0] + i1,
                    aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0] + j1);
            int l1 = class38_sub2_sub3.method457(9, 1);
            if (l1 == 1)
                anIntArray826[anInt825++] = l;
        } while (true);
        class38_sub2_sub3.method458(anInt813);
    }

    public void method58(int i) {
        try {
            if (bufferedStream != null)
                bufferedStream.close();
        } catch (Exception _ex) {
        }
        bufferedStream = null;
        aBoolean974 = false;
        anInt1109 = 0;
        aString1066 = "";
        aString1067 = "";
        Class7.method183((byte) 65);
        method83(aByte843);
        while (i >= 0)
            return;
        aClass32_831.method281(742);
        for (int j = 0; j < 4; j++)
            aClass8Array954[j].method196((byte) 74);

        System.gc();
        method17(0);
        aString1119 = null;
        anInt744 = 0;
    }

    public void method59(int i, int j, int k, InterfaceComponent interfaceComponent, int l) {
        if (interfaceComponent.anInt271 != 0 || interfaceComponent.anIntArray285 == null)
            return;
        if (interfaceComponent.aBoolean284 && anInt1063 != interfaceComponent.anInt269 && anInt941 != interfaceComponent.anInt269
                && anInt859 != interfaceComponent.anInt269)
            return;
        int i1 = Class38_Sub2_Sub2.anInt1313;
        int j1 = Class38_Sub2_Sub2.anInt1311;
        int k1 = Class38_Sub2_Sub2.anInt1314;
        int l1 = Class38_Sub2_Sub2.anInt1312;
        if (k != 38682)
            anInt780 = -1;
        Class38_Sub2_Sub2.method378(i + interfaceComponent.anInt275, i, j + interfaceComponent.anInt274, 789, j);
        int i2 = interfaceComponent.anIntArray285.length;
        for (int j2 = 0; j2 < i2; j2++) {
            int k2 = interfaceComponent.anIntArray286[j2] + j;
            int l2 = (interfaceComponent.anIntArray287[j2] + i) - l;
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.interfaceComponentArray[interfaceComponent.anIntArray285[j2]];
            k2 += interfaceComponent_1.anInt276;
            l2 += interfaceComponent_1.anInt277;
            if (interfaceComponent_1.anInt273 > 0)
                method76(0, interfaceComponent_1);
            if (interfaceComponent_1.anInt271 == 0) {
                if (interfaceComponent_1.anInt283 > interfaceComponent_1.anInt282 - interfaceComponent_1.anInt275)
                    interfaceComponent_1.anInt283 = interfaceComponent_1.anInt282 - interfaceComponent_1.anInt275;
                if (interfaceComponent_1.anInt283 < 0)
                    interfaceComponent_1.anInt283 = 0;
                method59(l2, k2, 38682, interfaceComponent_1, interfaceComponent_1.anInt283);
                if (interfaceComponent_1.anInt282 > interfaceComponent_1.anInt275)
                    method50(anInt803, k2 + interfaceComponent_1.anInt274, l2, interfaceComponent_1.anInt283, interfaceComponent_1.anInt282,
                            interfaceComponent_1.anInt275);
            } else if (interfaceComponent_1.anInt271 != 1)
                if (interfaceComponent_1.anInt271 == 2) {
                    int i3 = 0;
                    for (int k3 = 0; k3 < interfaceComponent_1.anInt275; k3++) {
                        for (int k4 = 0; k4 < interfaceComponent_1.anInt274; k4++) {
                            int j5 = k2 + k4 * (32 + interfaceComponent_1.anInt293);
                            int i6 = l2 + k3 * (32 + interfaceComponent_1.anInt294);
                            if (i3 < 20) {
                                j5 += interfaceComponent_1.anIntArray296[i3];
                                i6 += interfaceComponent_1.anIntArray297[i3];
                            }
                            if (interfaceComponent_1.anIntArray265[i3] > 0) {
                                int k6 = 0;
                                int k8 = 0;
                                int i9 = interfaceComponent_1.anIntArray265[i3] - 1;
                                if (j5 >= -32 && j5 <= 512 && i6 >= -32 && i6 <= 334
                                        || anInt846 != 0 && anInt845 == i3) {
                                    Class38_Sub2_Sub2_Sub2 class38_sub2_sub2_sub2_2 = ObjType.method174(i9, 24638,
                                            interfaceComponent_1.anIntArray266[i3]);
                                    if (anInt846 != 0 && anInt845 == i3 && anInt844 == interfaceComponent_1.anInt269) {
                                        k6 = super.anInt21 - anInt847;
                                        k8 = super.anInt22 - anInt848;
                                        if (k6 < 5 && k6 > -5)
                                            k6 = 0;
                                        if (k8 < 5 && k8 > -5)
                                            k8 = 0;
                                        if (anInt924 < 5) {
                                            k6 = 0;
                                            k8 = 0;
                                        }
                                        class38_sub2_sub2_sub2_2.method409(128, j5 + k6, i6 + k8, (byte) -26);
                                    } else if (anInt947 != 0 && anInt946 == i3 && anInt945 == interfaceComponent_1.anInt269)
                                        class38_sub2_sub2_sub2_2.method409(128, j5, i6, (byte) -26);
                                    else
                                        class38_sub2_sub2_sub2_2.method405(i6, j5, false);
                                    if (class38_sub2_sub2_sub2_2.anInt1470 == 33 || interfaceComponent_1.anIntArray266[i3] != 1) {
                                        int k9 = interfaceComponent_1.anIntArray266[i3];
                                        aClass38_Sub2_Sub2_Sub4_985.method424(j5 + 1 + k6, i6 + 10 + k8, false, 0,
                                                method108(true, k9));
                                        aClass38_Sub2_Sub2_Sub4_985.method424(j5 + k6, i6 + 9 + k8, false, 0xffff00,
                                                method108(true, k9));
                                    }
                                }
                            } else if (interfaceComponent_1.aClass38_Sub2_Sub2_Sub2Array295 != null && i3 < 20) {
                                Class38_Sub2_Sub2_Sub2 class38_sub2_sub2_sub2_1 = interfaceComponent_1.aClass38_Sub2_Sub2_Sub2Array295[i3];
                                if (class38_sub2_sub2_sub2_1 != null)
                                    class38_sub2_sub2_sub2_1.method405(i6, j5, false);
                            }
                            i3++;
                        }

                    }

                } else if (interfaceComponent_1.anInt271 == 3) {
                    if (interfaceComponent_1.aBoolean299)
                        Class38_Sub2_Sub2.method380(l2, k2, interfaceComponent_1.anInt305, (byte) 93, interfaceComponent_1.anInt274,
                                interfaceComponent_1.anInt275);
                    else
                        Class38_Sub2_Sub2.method381(3, k2, interfaceComponent_1.anInt305, interfaceComponent_1.anInt275, l2,
                                interfaceComponent_1.anInt274);
                } else if (interfaceComponent_1.anInt271 == 4) {
                    Class38_Sub2_Sub2_Sub4 class38_sub2_sub2_sub4 = interfaceComponent_1.aClass38_Sub2_Sub2_Sub4_302;
                    int l3 = interfaceComponent_1.anInt305;
                    String s = interfaceComponent_1.aString303;
                    if ((anInt859 == interfaceComponent_1.anInt269 || anInt941 == interfaceComponent_1.anInt269
                            || anInt1063 == interfaceComponent_1.anInt269) && interfaceComponent_1.anInt307 != 0)
                        l3 = interfaceComponent_1.anInt307;
                    if (method114(interfaceComponent_1, 65)) {
                        l3 = interfaceComponent_1.anInt306;
                        if (interfaceComponent_1.aString304.length() > 0)
                            s = interfaceComponent_1.aString304;
                    }
                    if (interfaceComponent_1.anInt272 == 6 && aBoolean872) {
                        s = "Please wait...";
                        l3 = interfaceComponent_1.anInt305;
                    }
                    for (int j6 = l2 + class38_sub2_sub2_sub4.anInt1497; s
                            .length() > 0; j6 += class38_sub2_sub2_sub4.anInt1497) {
                        if (s.indexOf("%") != -1) {
                            do {
                                int l6 = s.indexOf("%1");
                                if (l6 == -1)
                                    break;
                                s = s.substring(0, l6) + method89(method126(interfaceComponent_1, false, 0), -676)
                                        + s.substring(l6 + 2);
                            } while (true);
                            do {
                                int i7 = s.indexOf("%2");
                                if (i7 == -1)
                                    break;
                                s = s.substring(0, i7) + method89(method126(interfaceComponent_1, false, 1), -676)
                                        + s.substring(i7 + 2);
                            } while (true);
                            do {
                                int j7 = s.indexOf("%3");
                                if (j7 == -1)
                                    break;
                                s = s.substring(0, j7) + method89(method126(interfaceComponent_1, false, 2), -676)
                                        + s.substring(j7 + 2);
                            } while (true);
                            do {
                                int k7 = s.indexOf("%4");
                                if (k7 == -1)
                                    break;
                                s = s.substring(0, k7) + method89(method126(interfaceComponent_1, false, 3), -676)
                                        + s.substring(k7 + 2);
                            } while (true);
                            do {
                                int l7 = s.indexOf("%5");
                                if (l7 == -1)
                                    break;
                                s = s.substring(0, l7) + method89(method126(interfaceComponent_1, false, 4), -676)
                                        + s.substring(l7 + 2);
                            } while (true);
                        }
                        int i8 = s.indexOf("\\n");
                        String s1;
                        if (i8 != -1) {
                            s1 = s.substring(0, i8);
                            s = s.substring(i8 + 2);
                        } else {
                            s1 = s;
                            s = "";
                        }
                        if (interfaceComponent_1.aBoolean300)
                            class38_sub2_sub2_sub4.method422(k2 + interfaceComponent_1.anInt274 / 2, l3, interfaceComponent_1.aBoolean301, j6,
                                    s1, 0);
                        else
                            class38_sub2_sub2_sub4.method426(k2, 6, j6, s1, interfaceComponent_1.aBoolean301, l3);
                    }

                } else if (interfaceComponent_1.anInt271 == 5) {
                    Class38_Sub2_Sub2_Sub2 class38_sub2_sub2_sub2;
                    if (method114(interfaceComponent_1, 65))
                        class38_sub2_sub2_sub2 = interfaceComponent_1.aClass38_Sub2_Sub2_Sub2_309;
                    else
                        class38_sub2_sub2_sub2 = interfaceComponent_1.aClass38_Sub2_Sub2_Sub2_308;
                    if (class38_sub2_sub2_sub2 != null)
                        class38_sub2_sub2_sub2.method405(l2, k2, false);
                } else if (interfaceComponent_1.anInt271 == 6) {
                    int j3 = Class38_Sub2_Sub2_Sub1.anInt1442;
                    int i4 = Class38_Sub2_Sub2_Sub1.anInt1443;
                    Class38_Sub2_Sub2_Sub1.anInt1442 = k2 + interfaceComponent_1.anInt274 / 2;
                    Class38_Sub2_Sub2_Sub1.anInt1443 = l2 + interfaceComponent_1.anInt275 / 2;
                    int l4 = Class38_Sub2_Sub2_Sub1.anIntArray1446[interfaceComponent_1.anInt315] * interfaceComponent_1.anInt314 >> 16;
                    int k5 = Class38_Sub2_Sub2_Sub1.anIntArray1447[interfaceComponent_1.anInt315] * interfaceComponent_1.anInt314 >> 16;
                    boolean flag = method114(interfaceComponent_1, 65);
                    int j8;
                    if (flag)
                        j8 = interfaceComponent_1.anInt313;
                    else
                        j8 = interfaceComponent_1.anInt312;
                    Class38_Sub2_Sub1 class38_sub2_sub1;
                    if (j8 == -1) {
                        class38_sub2_sub1 = interfaceComponent_1.method219(-1, -1, flag);
                    } else {
                        SeqType seqType = SeqType.seqTypes[j8];
                        class38_sub2_sub1 = interfaceComponent_1.method219(seqType.primaryFrames[interfaceComponent_1.anInt267],
                                seqType.anIntArray367[interfaceComponent_1.anInt267], flag);
                    }
                    if (class38_sub2_sub1 != null)
                        class38_sub2_sub1.method370(0, interfaceComponent_1.anInt316, 0, interfaceComponent_1.anInt315, 0, l4, k5);
                    Class38_Sub2_Sub2_Sub1.anInt1442 = j3;
                    Class38_Sub2_Sub2_Sub1.anInt1443 = i4;
                } else if (interfaceComponent_1.anInt271 == 7) {
                    Class38_Sub2_Sub2_Sub4 class38_sub2_sub2_sub4_1 = interfaceComponent_1.aClass38_Sub2_Sub2_Sub4_302;
                    int j4 = 0;
                    for (int i5 = 0; i5 < interfaceComponent_1.anInt275; i5++) {
                        for (int l5 = 0; l5 < interfaceComponent_1.anInt274; l5++) {
                            if (interfaceComponent_1.anIntArray265[j4] > 0) {
                                ObjType objType = ObjType.method169(interfaceComponent_1.anIntArray265[j4] - 1);
                                String s2 = objType.aString145;
                                if (objType.aBoolean157 || interfaceComponent_1.anIntArray266[j4] != 1)
                                    s2 = s2 + " x" + method40(interfaceComponent_1.anIntArray266[j4], 0);
                                int l8 = k2 + l5 * (115 + interfaceComponent_1.anInt293);
                                int j9 = l2 + i5 * (12 + interfaceComponent_1.anInt294);
                                if (interfaceComponent_1.aBoolean300)
                                    class38_sub2_sub2_sub4_1.method422(l8 + interfaceComponent_1.anInt274 / 2, interfaceComponent_1.anInt305,
                                            interfaceComponent_1.aBoolean301, j9, s2, 0);
                                else
                                    class38_sub2_sub2_sub4_1.method426(l8, 6, j9, s2, interfaceComponent_1.aBoolean301,
                                            interfaceComponent_1.anInt305);
                            }
                            j4++;
                        }

                    }

                }
        }

        Class38_Sub2_Sub2.method378(l1, j1, k1, 789, i1);
    }

    public void method60(boolean flag, int i, Class38_Sub2_Sub3 class38_sub2_sub3) {
        for (int j = 0; j < anInt825; j++) {
            int k = anIntArray826[j];
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[k];
            int l = class38_sub2_sub3.method446();
            if ((l & 0x80) == 128)
                l += class38_sub2_sub3.method446() << 8;
            method139(true, k, l, class38_sub2_sub3, class38_sub7_sub3_sub2);
        }

        aBoolean974 &= flag;
    }

    public void method61(int i, int j) {
        j = 19 / j;
        int k = VarpType.varpTypes[i].anInt428;
        if (k == 0)
            return;
        int l = anIntArray938[i];
        if (k == 1) {
            if (l == 1)
                Class38_Sub2_Sub2_Sub1.method393(true, 0.90000000000000002D);
            if (l == 2)
                Class38_Sub2_Sub2_Sub1.method393(true, 0.80000000000000004D);
            if (l == 3)
                Class38_Sub2_Sub2_Sub1.method393(true, 0.69999999999999996D);
            if (l == 4)
                Class38_Sub2_Sub2_Sub1.method393(true, 0.59999999999999998D);
            ObjType.aClass34_179.method343();
            aBoolean751 = true;
        }
        if (k == 3) {
            boolean flag = aBoolean835;
            if (l == 0) {
                method54(0, 0, aBoolean835);
                aBoolean835 = true;
            }
            if (l == 1) {
                method54(0, -400, aBoolean835);
                aBoolean835 = true;
            }
            if (l == 2) {
                method54(0, -800, aBoolean835);
                aBoolean835 = true;
            }
            if (l == 3) {
                method54(0, -1200, aBoolean835);
                aBoolean835 = true;
            }
            if (l == 4)
                aBoolean835 = false;
            if (aBoolean835 != flag) {
                if (aBoolean835)
                    method14(false, anInt1110, aString1119, anInt1155);
                else
                    method17(0);
                anInt744 = 0;
            }
        }
        if (k == 4) {
            if (l == 0) {
                aBoolean1153 = true;
                method79(0, 0);
            }
            if (l == 1) {
                aBoolean1153 = true;
                method79(-400, 0);
            }
            if (l == 2) {
                aBoolean1153 = true;
                method79(-800, 0);
            }
            if (l == 3) {
                aBoolean1153 = true;
                method79(-1200, 0);
            }
            if (l == 4)
                aBoolean1153 = false;
        }
        if (k == 5)
            anInt810 = l;
        if (k == 6)
            anInt800 = l;
        if (k == 8) {
            anInt833 = l;
            aBoolean965 = true;
        }
    }

    public void method62(boolean flag) {
        aBoolean974 &= flag;
        for (int i = 0; i < anInt928; i++) {
            int j = anIntArray929[i];
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[j];
            if (class38_sub7_sub3_sub1 != null)
                method63(class38_sub7_sub3_sub1, (byte) -128, class38_sub7_sub3_sub1.npcType.aByte85);
        }

    }

    public void method63(Class38_Sub7_Sub3 class38_sub7_sub3, byte byte0, int i) {
        if (class38_sub7_sub3.anInt1380 < 128 || class38_sub7_sub3.anInt1381 < 128
                || class38_sub7_sub3.anInt1380 >= 13184 || class38_sub7_sub3.anInt1381 >= 13184) {
            class38_sub7_sub3.anInt1407 = -1;
            class38_sub7_sub3.anInt1412 = -1;
            class38_sub7_sub3.anInt1421 = 0;
            class38_sub7_sub3.anInt1422 = 0;
            class38_sub7_sub3.anInt1380 = class38_sub7_sub3.anIntArray1428[0] * 128 + class38_sub7_sub3.anInt1384 * 64;
            class38_sub7_sub3.anInt1381 = class38_sub7_sub3.anIntArray1429[0] * 128 + class38_sub7_sub3.anInt1384 * 64;
            class38_sub7_sub3.anInt1427 = 0;
        }
        if (class38_sub7_sub3 == aClass38_Sub7_Sub3_Sub2_967
                && (class38_sub7_sub3.anInt1380 < 1536 || class38_sub7_sub3.anInt1381 < 1536
                || class38_sub7_sub3.anInt1380 >= 11776 || class38_sub7_sub3.anInt1381 >= 11776)) {
            class38_sub7_sub3.anInt1407 = -1;
            class38_sub7_sub3.anInt1412 = -1;
            class38_sub7_sub3.anInt1421 = 0;
            class38_sub7_sub3.anInt1422 = 0;
            class38_sub7_sub3.anInt1380 = class38_sub7_sub3.anIntArray1428[0] * 128 + class38_sub7_sub3.anInt1384 * 64;
            class38_sub7_sub3.anInt1381 = class38_sub7_sub3.anIntArray1429[0] * 128 + class38_sub7_sub3.anInt1384 * 64;
            class38_sub7_sub3.anInt1427 = 0;
        }
        if (class38_sub7_sub3.anInt1421 > anInt955)
            method64(class38_sub7_sub3, -25115);
        else if (class38_sub7_sub3.anInt1422 >= anInt955)
            method65(class38_sub7_sub3, 0);
        else
            method66(598, class38_sub7_sub3);
        method67(class38_sub7_sub3, (byte) 117);
        if (byte0 != -128)
            aClass38_Sub2_Sub3_798.method436(11);
        method68(aBoolean787, class38_sub7_sub3);
    }

    public void method64(Class38_Sub7_Sub3 class38_sub7_sub3, int i) {
        int j = class38_sub7_sub3.anInt1421 - anInt955;
        int k = class38_sub7_sub3.anInt1417 * 128 + class38_sub7_sub3.anInt1384 * 64;
        int l = class38_sub7_sub3.anInt1419 * 128 + class38_sub7_sub3.anInt1384 * 64;
        class38_sub7_sub3.anInt1380 += (k - class38_sub7_sub3.anInt1380) / j;
        if (i != -25115)
            anInt733 = isaacState.nextInt();
        class38_sub7_sub3.anInt1381 += (l - class38_sub7_sub3.anInt1381) / j;
        class38_sub7_sub3.anInt1431 = 0;
        if (class38_sub7_sub3.anInt1423 == 0)
            class38_sub7_sub3.anInt1426 = 1024;
        if (class38_sub7_sub3.anInt1423 == 1)
            class38_sub7_sub3.anInt1426 = 1536;
        if (class38_sub7_sub3.anInt1423 == 2)
            class38_sub7_sub3.anInt1426 = 0;
        if (class38_sub7_sub3.anInt1423 == 3)
            class38_sub7_sub3.anInt1426 = 512;
    }

    public void method65(Class38_Sub7_Sub3 class38_sub7_sub3, int i) {
        anInt779 += i;
        if (class38_sub7_sub3.anInt1422 == anInt955 || class38_sub7_sub3.anInt1407 == -1
                || class38_sub7_sub3.anInt1410 != 0 || class38_sub7_sub3.anInt1409
                + 1 > SeqType.seqTypes[class38_sub7_sub3.anInt1407].anIntArray368[class38_sub7_sub3.anInt1408]) {
            int j = class38_sub7_sub3.anInt1422 - class38_sub7_sub3.anInt1421;
            int k = anInt955 - class38_sub7_sub3.anInt1421;
            int l = class38_sub7_sub3.anInt1417 * 128 + class38_sub7_sub3.anInt1384 * 64;
            int i1 = class38_sub7_sub3.anInt1419 * 128 + class38_sub7_sub3.anInt1384 * 64;
            int j1 = class38_sub7_sub3.anInt1418 * 128 + class38_sub7_sub3.anInt1384 * 64;
            int k1 = class38_sub7_sub3.anInt1420 * 128 + class38_sub7_sub3.anInt1384 * 64;
            class38_sub7_sub3.anInt1380 = (l * (j - k) + j1 * k) / j;
            class38_sub7_sub3.anInt1381 = (i1 * (j - k) + k1 * k) / j;
        }
        class38_sub7_sub3.anInt1431 = 0;
        if (class38_sub7_sub3.anInt1423 == 0)
            class38_sub7_sub3.anInt1426 = 1024;
        if (class38_sub7_sub3.anInt1423 == 1)
            class38_sub7_sub3.anInt1426 = 1536;
        if (class38_sub7_sub3.anInt1423 == 2)
            class38_sub7_sub3.anInt1426 = 0;
        if (class38_sub7_sub3.anInt1423 == 3)
            class38_sub7_sub3.anInt1426 = 512;
        class38_sub7_sub3.anInt1382 = class38_sub7_sub3.anInt1426;
    }

    public void method66(int i, Class38_Sub7_Sub3 class38_sub7_sub3) {
        class38_sub7_sub3.anInt1404 = class38_sub7_sub3.anInt1385;
        i = 56 / i;
        if (class38_sub7_sub3.anInt1427 == 0) {
            class38_sub7_sub3.anInt1431 = 0;
            return;
        }
        if (class38_sub7_sub3.anInt1407 != -1 && class38_sub7_sub3.anInt1410 == 0) {
            SeqType seqType = SeqType.seqTypes[class38_sub7_sub3.anInt1407];
            if (seqType.anIntArray370 == null) {
                class38_sub7_sub3.anInt1431++;
                return;
            }
        }
        int j = class38_sub7_sub3.anInt1380;
        int k = class38_sub7_sub3.anInt1381;
        int l = class38_sub7_sub3.anIntArray1428[class38_sub7_sub3.anInt1427 - 1] * 128
                + class38_sub7_sub3.anInt1384 * 64;
        int i1 = class38_sub7_sub3.anIntArray1429[class38_sub7_sub3.anInt1427 - 1] * 128
                + class38_sub7_sub3.anInt1384 * 64;
        if (l - j > 256 || l - j < -256 || i1 - k > 256 || i1 - k < -256) {
            class38_sub7_sub3.anInt1380 = l;
            class38_sub7_sub3.anInt1381 = i1;
            return;
        }
        if (j < l) {
            if (k < i1)
                class38_sub7_sub3.anInt1426 = 1280;
            else if (k > i1)
                class38_sub7_sub3.anInt1426 = 1792;
            else
                class38_sub7_sub3.anInt1426 = 1536;
        } else if (j > l) {
            if (k < i1)
                class38_sub7_sub3.anInt1426 = 768;
            else if (k > i1)
                class38_sub7_sub3.anInt1426 = 256;
            else
                class38_sub7_sub3.anInt1426 = 512;
        } else if (k < i1)
            class38_sub7_sub3.anInt1426 = 1024;
        else
            class38_sub7_sub3.anInt1426 = 0;
        int j1 = class38_sub7_sub3.anInt1426 - class38_sub7_sub3.anInt1382 & 0x7ff;
        if (j1 > 1024)
            j1 -= 2048;
        int k1 = class38_sub7_sub3.anInt1388;
        if (j1 >= -256 && j1 <= 256)
            k1 = class38_sub7_sub3.anInt1387;
        else if (j1 >= 256 && j1 < 768)
            k1 = class38_sub7_sub3.anInt1390;
        else if (j1 >= -768 && j1 <= -256)
            k1 = class38_sub7_sub3.anInt1389;
        if (k1 == -1)
            k1 = class38_sub7_sub3.anInt1387;
        class38_sub7_sub3.anInt1404 = k1;
        int l1 = 4;
        if (class38_sub7_sub3.anInt1382 != class38_sub7_sub3.anInt1426 && class38_sub7_sub3.anInt1401 == -1)
            l1 = 2;
        if (class38_sub7_sub3.anInt1427 > 2)
            l1 = 6;
        if (class38_sub7_sub3.anInt1427 > 3)
            l1 = 8;
        if (class38_sub7_sub3.anInt1431 > 0 && class38_sub7_sub3.anInt1427 > 1) {
            l1 = 8;
            class38_sub7_sub3.anInt1431--;
        }
        if (class38_sub7_sub3.aBooleanArray1430[class38_sub7_sub3.anInt1427 - 1])
            l1 <<= 1;
        if (l1 >= 8 && class38_sub7_sub3.anInt1404 == class38_sub7_sub3.anInt1387 && class38_sub7_sub3.anInt1391 != -1)
            class38_sub7_sub3.anInt1404 = class38_sub7_sub3.anInt1391;
        if (j < l) {
            class38_sub7_sub3.anInt1380 += l1;
            if (class38_sub7_sub3.anInt1380 > l)
                class38_sub7_sub3.anInt1380 = l;
        } else if (j > l) {
            class38_sub7_sub3.anInt1380 -= l1;
            if (class38_sub7_sub3.anInt1380 < l)
                class38_sub7_sub3.anInt1380 = l;
        }
        if (k < i1) {
            class38_sub7_sub3.anInt1381 += l1;
            if (class38_sub7_sub3.anInt1381 > i1)
                class38_sub7_sub3.anInt1381 = i1;
        } else if (k > i1) {
            class38_sub7_sub3.anInt1381 -= l1;
            if (class38_sub7_sub3.anInt1381 < i1)
                class38_sub7_sub3.anInt1381 = i1;
        }
        if (class38_sub7_sub3.anInt1380 == l && class38_sub7_sub3.anInt1381 == i1)
            class38_sub7_sub3.anInt1427--;
    }

    public void method67(Class38_Sub7_Sub3 class38_sub7_sub3, byte byte0) {
        if (byte0 != 117)
            aClass38_Sub2_Sub3_798.method436(89);
        if (class38_sub7_sub3.anInt1401 != -1 && class38_sub7_sub3.anInt1401 < 32768) {
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[class38_sub7_sub3.anInt1401];
            if (class38_sub7_sub3_sub1 != null) {
                int l = class38_sub7_sub3.anInt1380 - class38_sub7_sub3_sub1.anInt1380;
                int j1 = class38_sub7_sub3.anInt1381 - class38_sub7_sub3_sub1.anInt1381;
                if (l != 0 || j1 != 0)
                    class38_sub7_sub3.anInt1426 = (int) (Math.atan2(l, j1) * 325.94900000000001D) & 0x7ff;
            }
        }
        if (class38_sub7_sub3.anInt1401 >= 32768) {
            int i = class38_sub7_sub3.anInt1401 - 32768;
            if (i == anInt734)
                i = anInt821;
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[i];
            if (class38_sub7_sub3_sub2 != null) {
                int k1 = class38_sub7_sub3.anInt1380 - class38_sub7_sub3_sub2.anInt1380;
                int l1 = class38_sub7_sub3.anInt1381 - class38_sub7_sub3_sub2.anInt1381;
                if (k1 != 0 || l1 != 0)
                    class38_sub7_sub3.anInt1426 = (int) (Math.atan2(k1, l1) * 325.94900000000001D) & 0x7ff;
            }
        }
        if ((class38_sub7_sub3.anInt1402 != 0 || class38_sub7_sub3.anInt1403 != 0)
                && (class38_sub7_sub3.anInt1427 == 0 || class38_sub7_sub3.anInt1431 > 0)) {
            int j = class38_sub7_sub3.anInt1380 - (class38_sub7_sub3.anInt1402 - anInt761 - anInt761) * 64;
            int i1 = class38_sub7_sub3.anInt1381 - (class38_sub7_sub3.anInt1403 - anInt762 - anInt762) * 64;
            if (j != 0 || i1 != 0)
                class38_sub7_sub3.anInt1426 = (int) (Math.atan2(j, i1) * 325.94900000000001D) & 0x7ff;
            class38_sub7_sub3.anInt1402 = 0;
            class38_sub7_sub3.anInt1403 = 0;
        }
        int k = class38_sub7_sub3.anInt1426 - class38_sub7_sub3.anInt1382 & 0x7ff;
        if (k != 0) {
            if (k < 32 || k > 2016)
                class38_sub7_sub3.anInt1382 = class38_sub7_sub3.anInt1426;
            else if (k > 1024)
                class38_sub7_sub3.anInt1382 -= 32;
            else
                class38_sub7_sub3.anInt1382 += 32;
            class38_sub7_sub3.anInt1382 &= 0x7ff;
            if (class38_sub7_sub3.anInt1404 == class38_sub7_sub3.anInt1385
                    && class38_sub7_sub3.anInt1382 != class38_sub7_sub3.anInt1426) {
                if (class38_sub7_sub3.anInt1386 != -1) {
                    class38_sub7_sub3.anInt1404 = class38_sub7_sub3.anInt1386;
                    return;
                }
                class38_sub7_sub3.anInt1404 = class38_sub7_sub3.anInt1387;
            }
        }
    }

    public void method68(boolean flag, Class38_Sub7_Sub3 class38_sub7_sub3) {
        if (!flag)
            return;
        class38_sub7_sub3.aBoolean1383 = false;
        if (class38_sub7_sub3.anInt1404 != -1) {
            SeqType seqType = SeqType.seqTypes[class38_sub7_sub3.anInt1404];
            class38_sub7_sub3.anInt1406++;
            if (class38_sub7_sub3.anInt1405 < seqType.anInt365
                    && class38_sub7_sub3.anInt1406 > seqType.anIntArray368[class38_sub7_sub3.anInt1405]) {
                class38_sub7_sub3.anInt1406 = 0;
                class38_sub7_sub3.anInt1405++;
            }
            if (class38_sub7_sub3.anInt1405 >= seqType.anInt365) {
                class38_sub7_sub3.anInt1406 = 0;
                class38_sub7_sub3.anInt1405 = 0;
            }
        }
        if (class38_sub7_sub3.anInt1407 != -1 && class38_sub7_sub3.anInt1410 == 0) {
            SeqType seqType_1 = SeqType.seqTypes[class38_sub7_sub3.anInt1407];
            for (class38_sub7_sub3.anInt1409++; class38_sub7_sub3.anInt1408 < seqType_1.anInt365
                    && class38_sub7_sub3.anInt1409 > seqType_1.anIntArray368[class38_sub7_sub3.anInt1408]; class38_sub7_sub3.anInt1408++)
                class38_sub7_sub3.anInt1409 -= seqType_1.anIntArray368[class38_sub7_sub3.anInt1408];

            if (class38_sub7_sub3.anInt1408 >= seqType_1.anInt365) {
                class38_sub7_sub3.anInt1408 -= seqType_1.anInt369;
                class38_sub7_sub3.anInt1411++;
                if (class38_sub7_sub3.anInt1411 >= seqType_1.anInt375)
                    class38_sub7_sub3.anInt1407 = -1;
                if (class38_sub7_sub3.anInt1408 < 0 || class38_sub7_sub3.anInt1408 >= seqType_1.anInt365)
                    class38_sub7_sub3.anInt1407 = -1;
            }
            class38_sub7_sub3.aBoolean1383 = seqType_1.aBoolean371;
        }
        if (class38_sub7_sub3.anInt1410 > 0)
            class38_sub7_sub3.anInt1410--;
        if (class38_sub7_sub3.anInt1412 != -1 && anInt955 >= class38_sub7_sub3.anInt1415) {
            if (class38_sub7_sub3.anInt1413 < 0)
                class38_sub7_sub3.anInt1413 = 0;
            SeqType seqType_2 = SpotAnimType.spotAnimTypes[class38_sub7_sub3.anInt1412].seq;
            for (class38_sub7_sub3.anInt1414++; class38_sub7_sub3.anInt1413 < seqType_2.anInt365
                    && class38_sub7_sub3.anInt1414 > seqType_2.anIntArray368[class38_sub7_sub3.anInt1413]; class38_sub7_sub3.anInt1413++)
                class38_sub7_sub3.anInt1414 -= seqType_2.anIntArray368[class38_sub7_sub3.anInt1413];

            if (class38_sub7_sub3.anInt1413 >= seqType_2.anInt365
                    && (class38_sub7_sub3.anInt1413 < 0 || class38_sub7_sub3.anInt1413 >= seqType_2.anInt365))
                class38_sub7_sub3.anInt1412 = -1;
        }
    }

    public void method69(int i) {
        if (i != 29510)
            anInt1132 = 411;
        if (aBoolean751) {
            aBoolean751 = false;
            drawArea1.drawImage(11, super.aGraphics14, 0);
            drawArea2.drawImage(375, super.aGraphics14, 0);
            drawArea3.drawImage(5, super.aGraphics14, 729);
            drawArea4.drawImage(231, super.aGraphics14, 752);
            drawArea5.drawImage(0, super.aGraphics14, 0);
            drawArea6.drawImage(0, super.aGraphics14, 561);
            drawArea7.drawImage(11, super.aGraphics14, 520);
            drawArea8.drawImage(231, super.aGraphics14, 520);
            drawArea9.drawImage(375, super.aGraphics14, 501);
            drawArea10.drawImage(345, super.aGraphics14, 0);
            aBoolean964 = true;
            aBoolean965 = true;
            aBoolean1080 = true;
            aBoolean921 = true;
            if (anInt1078 != 2) {
                drawArea22.drawImage(11, super.aGraphics14, 8);
                drawArea21.drawImage(5, super.aGraphics14, 561);
            }
        }
        if (anInt1078 == 2)
            method25(0);
        if (aBoolean879 && anInt1148 == 1)
            aBoolean964 = true;
        if (anInt1129 != -1) {
            boolean flag = method110(anInt1129, anInt969, 623);
            if (flag)
                aBoolean964 = true;
        }
        if (anInt947 == 2)
            aBoolean964 = true;
        if (anInt846 == 2)
            aBoolean964 = true;
        if (aBoolean964) {
            method137((byte) 2);
            aBoolean964 = false;
        }
        if (anInt1001 == -1) {
            interfaceComponent.anInt283 = anInt792 - anInt977 - 77;
            if (super.anInt21 > 453 && super.anInt21 < 565 && super.anInt22 > 350)
                method97(super.anInt21 - 22, 0, super.anInt22 - 375, anInt792, 77, false, 463, 0, interfaceComponent);
            int j = anInt792 - 77 - interfaceComponent.anInt283;
            if (j < 0)
                j = 0;
            if (j > anInt792 - 77)
                j = anInt792 - 77;
            if (anInt977 != j) {
                anInt977 = j;
                aBoolean965 = true;
            }
        }
        if (anInt1001 != -1) {
            boolean flag1 = method110(anInt1001, anInt969, 623);
            if (flag1)
                aBoolean965 = true;
        }
        if (anInt947 == 3)
            aBoolean965 = true;
        if (anInt846 == 3)
            aBoolean965 = true;
        if (aString936 != null)
            aBoolean965 = true;
        if (aBoolean879 && anInt1148 == 2)
            aBoolean965 = true;
        if (aBoolean965) {
            method135(false);
            aBoolean965 = false;
        }
        if (anInt1078 == 2) {
            method43(aByte935);
            drawArea21.drawImage(5, super.aGraphics14, 561);
        }
        if (anInt1128 != -1)
            aBoolean1080 = true;
        if (aBoolean1080) {
            if (anInt1128 != -1 && anInt1128 == anInt757) {
                anInt1128 = -1;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 175);
                aClass38_Sub2_Sub3_798.method436(anInt757);
            }
            aBoolean1080 = false;
            drawArea26.init2D();
            aClass38_Sub2_Sub2_Sub3_910.method419(0, 0, false);
            if (anInt1129 == -1) {
                if (anIntArray861[anInt757] != -1) {
                    if (anInt757 == 0)
                        aClass38_Sub2_Sub2_Sub3_891.method419(30, 29, false);
                    if (anInt757 == 1)
                        aClass38_Sub2_Sub2_Sub3_892.method419(29, 59, false);
                    if (anInt757 == 2)
                        aClass38_Sub2_Sub2_Sub3_892.method419(29, 87, false);
                    if (anInt757 == 3)
                        aClass38_Sub2_Sub2_Sub3_893.method419(29, 115, false);
                    if (anInt757 == 4)
                        aClass38_Sub2_Sub2_Sub3_895.method419(29, 156, false);
                    if (anInt757 == 5)
                        aClass38_Sub2_Sub2_Sub3_895.method419(29, 184, false);
                    if (anInt757 == 6)
                        aClass38_Sub2_Sub2_Sub3_894.method419(30, 212, false);
                }
                if (anIntArray861[0] != -1 && (anInt1128 != 0 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[0].method419(34, 35, false);
                if (anIntArray861[1] != -1 && (anInt1128 != 1 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[1].method419(32, 59, false);
                if (anIntArray861[2] != -1 && (anInt1128 != 2 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[2].method419(32, 86, false);
                if (anIntArray861[3] != -1 && (anInt1128 != 3 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[3].method419(33, 121, false);
                if (anIntArray861[4] != -1 && (anInt1128 != 4 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[4].method419(34, 157, false);
                if (anIntArray861[5] != -1 && (anInt1128 != 5 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[5].method419(32, 185, false);
                if (anIntArray861[6] != -1 && (anInt1128 != 6 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[6].method419(34, 212, false);
            }
            drawArea26.drawImage(165, super.aGraphics14, 520);
            drawArea25.init2D();
            aClass38_Sub2_Sub2_Sub3_909.method419(0, 0, false);
            if (anInt1129 == -1) {
                if (anIntArray861[anInt757] != -1) {
                    if (anInt757 == 7)
                        aClass38_Sub2_Sub2_Sub3_1040.method419(0, 49, false);
                    if (anInt757 == 8)
                        aClass38_Sub2_Sub2_Sub3_1041.method419(0, 81, false);
                    if (anInt757 == 9)
                        aClass38_Sub2_Sub2_Sub3_1041.method419(0, 108, false);
                    if (anInt757 == 10)
                        aClass38_Sub2_Sub2_Sub3_1042.method419(1, 136, false);
                    if (anInt757 == 11)
                        aClass38_Sub2_Sub2_Sub3_1044.method419(0, 178, false);
                    if (anInt757 == 12)
                        aClass38_Sub2_Sub2_Sub3_1044.method419(0, 205, false);
                    if (anInt757 == 13)
                        aClass38_Sub2_Sub2_Sub3_1043.method419(0, 233, false);
                }
                if (anIntArray861[8] != -1 && (anInt1128 != 8 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[7].method419(2, 80, false);
                if (anIntArray861[9] != -1 && (anInt1128 != 9 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[8].method419(3, 107, false);
                if (anIntArray861[10] != -1 && (anInt1128 != 10 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[9].method419(4, 142, false);
                if (anIntArray861[11] != -1 && (anInt1128 != 11 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[10].method419(2, 179, false);
                if (anIntArray861[12] != -1 && (anInt1128 != 12 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[11].method419(2, 206, false);
                if (anIntArray861[13] != -1 && (anInt1128 != 13 || anInt955 % 20 < 10))
                    aClass38_Sub2_Sub2_Sub3Array814[12].method419(2, 230, false);
            }
            drawArea25.drawImage(492, super.aGraphics14, 501);
            drawArea22.init2D();
        }
        if (aBoolean921) {
            aBoolean921 = false;
            drawArea24.init2D();
            aClass38_Sub2_Sub2_Sub3_908.method419(0, 0, false);
            aClass38_Sub2_Sub2_Sub4_986.method422(57, 0xffffff, true, 33, "Public chat", 0);
            if (anInt976 == 0)
                aClass38_Sub2_Sub2_Sub4_986.method422(57, 65280, true, 46, "On", 0);
            if (anInt976 == 1)
                aClass38_Sub2_Sub2_Sub4_986.method422(57, 0xffff00, true, 46, "Friends", 0);
            if (anInt976 == 2)
                aClass38_Sub2_Sub2_Sub4_986.method422(57, 0xff0000, true, 46, "Off", 0);
            if (anInt976 == 3)
                aClass38_Sub2_Sub2_Sub4_986.method422(57, 65535, true, 46, "Hide", 0);
            aClass38_Sub2_Sub2_Sub4_986.method422(186, 0xffffff, true, 33, "Private chat", 0);
            if (anInt755 == 0)
                aClass38_Sub2_Sub2_Sub4_986.method422(186, 65280, true, 46, "On", 0);
            if (anInt755 == 1)
                aClass38_Sub2_Sub2_Sub4_986.method422(186, 0xffff00, true, 46, "Friends", 0);
            if (anInt755 == 2)
                aClass38_Sub2_Sub2_Sub4_986.method422(186, 0xff0000, true, 46, "Off", 0);
            aClass38_Sub2_Sub2_Sub4_986.method422(326, 0xffffff, true, 33, "Trade/duel", 0);
            if (anInt885 == 0)
                aClass38_Sub2_Sub2_Sub4_986.method422(326, 65280, true, 46, "On", 0);
            if (anInt885 == 1)
                aClass38_Sub2_Sub2_Sub4_986.method422(326, 0xffff00, true, 46, "Friends", 0);
            if (anInt885 == 2)
                aClass38_Sub2_Sub2_Sub4_986.method422(326, 0xff0000, true, 46, "Off", 0);
            aClass38_Sub2_Sub2_Sub4_986.method422(462, 0xffffff, true, 38, "Report abuse", 0);
            drawArea24.drawImage(471, super.aGraphics14, 0);
            drawArea22.init2D();
        }
        anInt969 = 0;
    }

    public boolean method70(int i, int j) {
        if (j < 0)
            return false;
        int k = anIntArray1141[j];
        if (i <= 0)
            throw new NullPointerException();
        if (k >= 2000)
            k -= 2000;
        return k == 406;
    }

    public void method71(int i, int j) {
        if (j < 0)
            return;
        if (aBoolean1055) {
            aBoolean1055 = false;
            aBoolean965 = true;
        }
        int k = anIntArray1139[j];
        int l = anIntArray1140[j];
        int i1 = anIntArray1141[j];
        int j1 = anIntArray1142[j];
        if (i1 >= 2000)
            i1 -= 2000;
        if (i1 == 903 || i1 == 363) {
            String s = aStringArray834[j];
            int l1 = s.indexOf("@whi@");
            if (l1 != -1) {
                s = s.substring(l1 + 5).trim();
                String s8 = StringUtils.formatName(StringUtils.fromBase37(StringUtils.toBase37(s)));
                boolean flag4 = false;
                for (int k3 = 0; k3 < anInt823; k3++) {
                    Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2_3 = aClass38_Sub7_Sub3_Sub2Array822[anIntArray824[k3]];
                    if (class38_sub7_sub3_sub2_3 == null || class38_sub7_sub3_sub2_3.aString1505 == null
                            || !class38_sub7_sub3_sub2_3.aString1505.equalsIgnoreCase(s8))
                        continue;
                    method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false,
                            class38_sub7_sub3_sub2_3.anIntArray1428[0],
                            aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1,
                            class38_sub7_sub3_sub2_3.anIntArray1429[0], 0, 0, 0);
                    if (i1 == 903)
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 206);
                    if (i1 == 363)
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 164);
                    aClass38_Sub2_Sub3_798.method437(anIntArray824[k3]);
                    flag4 = true;
                    break;
                }

                if (!flag4)
                    method111(0, "Unable to find " + s8, (byte) 4, "");
            }
        }
        if (i1 == 450 && method92(75, k, l, j1, true)) {
            aClass38_Sub2_Sub3_798.method437(anInt1005);
            aClass38_Sub2_Sub3_798.method437(anInt1003);
            aClass38_Sub2_Sub3_798.method437(anInt1004);
        }
        if (i1 == 405 || i1 == 38 || i1 == 422 || i1 == 478 || i1 == 347) {
            if (i1 == 478) {
                if ((k & 3) == 0)
                    anInt724++;
                if (anInt724 >= 90)
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 220);
                aClass38_Sub2_Sub3_798.method435((byte) -34, 157);
            }
            if (i1 == 347)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 211);
            if (i1 == 422)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 133);
            if (i1 == 405) {
                anInt806 += j1;
                if (anInt806 >= 97) {
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 30);
                    aClass38_Sub2_Sub3_798.method439(0xe42d58);
                }
                aClass38_Sub2_Sub3_798.method435((byte) -34, 195);
            }
            if (i1 == 38)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 71);
            aClass38_Sub2_Sub3_798.method437(j1);
            aClass38_Sub2_Sub3_798.method437(k);
            aClass38_Sub2_Sub3_798.method437(l);
            anInt944 = 0;
            anInt945 = l;
            anInt946 = k;
            anInt947 = 2;
            if (InterfaceComponent.interfaceComponentArray[l].anInt270 == anInt971)
                anInt947 = 1;
            if (InterfaceComponent.interfaceComponentArray[l].anInt270 == anInt1001)
                anInt947 = 3;
        }
        if (i1 == 728 || i1 == 542 || i1 == 6 || i1 == 963 || i1 == 245) {
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[j1];
            if (class38_sub7_sub3_sub1 != null) {
                method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false,
                        class38_sub7_sub3_sub1.anIntArray1428[0],
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1,
                        class38_sub7_sub3_sub1.anIntArray1429[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                if (i1 == 542)
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 8);
                if (i1 == 6) {
                    if ((j1 & 3) == 0)
                        anInt867++;
                    if (anInt867 >= 124) {
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 88);
                        aClass38_Sub2_Sub3_798.method440(0);
                    }
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 27);
                }
                if (i1 == 963)
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 113);
                if (i1 == 728)
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 194);
                if (i1 == 245) {
                    if ((j1 & 3) == 0)
                        anInt797++;
                    if (anInt797 >= 85) {
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 176);
                        aClass38_Sub2_Sub3_798.method437(39596);
                    }
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 100);
                }
                aClass38_Sub2_Sub3_798.method437(j1);
            }
        }
        if (i1 == 217) {
            boolean flag = method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 0, false, k,
                    aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 0, l, 0, 0, 0);
            if (!flag)
                flag = method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false, k,
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1, l, 0, 0, 0);
            anInt738 = super.anInt24;
            anInt739 = super.anInt25;
            anInt741 = 2;
            anInt740 = 0;
            aClass38_Sub2_Sub3_798.method435((byte) -34, 239);
            aClass38_Sub2_Sub3_798.method437(k + anInt761);
            aClass38_Sub2_Sub3_798.method437(l + anInt762);
            aClass38_Sub2_Sub3_798.method437(j1);
            aClass38_Sub2_Sub3_798.method437(anInt1005);
            aClass38_Sub2_Sub3_798.method437(anInt1003);
            aClass38_Sub2_Sub3_798.method437(anInt1004);
        }
        if (i1 == 1175) {
            int k1 = j1 >> 14 & 0x7fff;
            LocType locType = LocType.get(k1);
            String s9;
            if (locType.description != null)
                s9 = new String(locType.description);
            else
                s9 = "It's a " + locType.name + ".";
            method111(0, s9, (byte) 4, "");
        }
        if (i1 == 285)
            method92(245, k, l, j1, true);
        if (i1 == 881) {
            aClass38_Sub2_Sub3_798.method435((byte) -34, 130);
            aClass38_Sub2_Sub3_798.method437(j1);
            aClass38_Sub2_Sub3_798.method437(k);
            aClass38_Sub2_Sub3_798.method437(l);
            aClass38_Sub2_Sub3_798.method437(anInt1005);
            aClass38_Sub2_Sub3_798.method437(anInt1003);
            aClass38_Sub2_Sub3_798.method437(anInt1004);
            anInt944 = 0;
            anInt945 = l;
            anInt946 = k;
            anInt947 = 2;
            if (InterfaceComponent.interfaceComponentArray[l].anInt270 == anInt971)
                anInt947 = 1;
            if (InterfaceComponent.interfaceComponentArray[l].anInt270 == anInt1001)
                anInt947 = 3;
        }
        if (i1 == 391) {
            aClass38_Sub2_Sub3_798.method435((byte) -34, 48);
            aClass38_Sub2_Sub3_798.method437(j1);
            aClass38_Sub2_Sub3_798.method437(k);
            aClass38_Sub2_Sub3_798.method437(l);
            aClass38_Sub2_Sub3_798.method437(anInt1026);
            anInt944 = 0;
            anInt945 = l;
            anInt946 = k;
            anInt947 = 2;
            if (InterfaceComponent.interfaceComponentArray[l].anInt270 == anInt971)
                anInt947 = 1;
            if (InterfaceComponent.interfaceComponentArray[l].anInt270 == anInt1001)
                anInt947 = 3;
        }
        if (i1 == 660)
            if (!aBoolean879)
                aClass32_831.method320(4, super.anInt25 - 11, super.anInt24 - 8);
            else
                aClass32_831.method320(4, l - 11, k - 8);
        if (i1 == 188) {
            anInt1002 = 1;
            anInt1003 = k;
            anInt1004 = l;
            anInt1005 = j1;
            aString1006 = ObjType.method169(j1).aString145;
            anInt1025 = 0;
            return;
        }
        if (i1 == 44 && !aBoolean872) {
            aClass38_Sub2_Sub3_798.method435((byte) -34, 235);
            aClass38_Sub2_Sub3_798.method437(l);
            aBoolean872 = true;
        }
        if (i1 == 1773) {
            ObjType objType = ObjType.method169(j1);
            String s4;
            if (l >= 0x186a0)
                s4 = l + " x " + objType.aString145;
            else if (objType.aByteArray146 != null)
                s4 = new String(objType.aByteArray146);
            else
                s4 = "It's a " + objType.aString145 + ".";
            method111(0, s4, (byte) 4, "");
        }
        if (i1 == 900) {
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1_1 = aClass38_Sub7_Sub3_Sub1Array927[j1];
            if (class38_sub7_sub3_sub1_1 != null) {
                method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false,
                        class38_sub7_sub3_sub1_1.anIntArray1428[0],
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1,
                        class38_sub7_sub3_sub1_1.anIntArray1429[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 202);
                aClass38_Sub2_Sub3_798.method437(j1);
                aClass38_Sub2_Sub3_798.method437(anInt1005);
                aClass38_Sub2_Sub3_798.method437(anInt1003);
                aClass38_Sub2_Sub3_798.method437(anInt1004);
            }
        }
        if (i1 == 1373 || i1 == 1544 || i1 == 151 || i1 == 1101) {
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[j1];
            if (class38_sub7_sub3_sub2 != null) {
                method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false,
                        class38_sub7_sub3_sub2.anIntArray1428[0],
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1,
                        class38_sub7_sub3_sub2.anIntArray1429[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                if (i1 == 1101)
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 164);
                if (i1 == 151) {
                    anInt890++;
                    if (anInt890 >= 90) {
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 2);
                        aClass38_Sub2_Sub3_798.method437(31114);
                    }
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 53);
                }
                if (i1 == 1373)
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 206);
                if (i1 == 1544)
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 185);
                aClass38_Sub2_Sub3_798.method437(j1);
            }
        }
        if (i1 == 265) {
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1_2 = aClass38_Sub7_Sub3_Sub1Array927[j1];
            if (class38_sub7_sub3_sub1_2 != null) {
                method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false,
                        class38_sub7_sub3_sub1_2.anIntArray1428[0],
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1,
                        class38_sub7_sub3_sub1_2.anIntArray1429[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 134);
                aClass38_Sub2_Sub3_798.method437(j1);
                aClass38_Sub2_Sub3_798.method437(anInt1026);
            }
        }
        if (i1 == 679) {
            String s1 = aStringArray834[j];
            int i2 = s1.indexOf("@whi@");
            if (i2 != -1) {
                long l3 = StringUtils.toBase37(s1.substring(i2 + 5).trim());
                int i4 = -1;
                for (int j4 = 0; j4 < anInt1089; j4++) {
                    if (aLongArray943[j4] != l3)
                        continue;
                    i4 = j4;
                    break;
                }

                if (i4 != -1 && anIntArray773[i4] > 0) {
                    aBoolean965 = true;
                    aBoolean1055 = false;
                    aBoolean869 = true;
                    aString765 = "";
                    anInt760 = 3;
                    aLong900 = aLongArray943[i4];
                    aString775 = "Enter message to send to " + aStringArray1127[i4];
                }
            }
        }
        if (i1 == 55 && method92(9, k, l, j1, true))
            aClass38_Sub2_Sub3_798.method437(anInt1026);
        if (i1 == 224 || i1 == 993 || i1 == 99 || i1 == 746 || i1 == 877) {
            boolean flag1 = method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 0, false,
                    k, aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 0, l, 0, 0, 0);
            if (!flag1)
                flag1 = method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false, k,
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1, l, 0, 0, 0);
            anInt738 = super.anInt24;
            anInt739 = super.anInt25;
            anInt741 = 2;
            anInt740 = 0;
            if (i1 == 224)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 140);
            if (i1 == 746)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 178);
            if (i1 == 877)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 247);
            if (i1 == 99)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 200);
            if (i1 == 993)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 40);
            aClass38_Sub2_Sub3_798.method437(k + anInt761);
            aClass38_Sub2_Sub3_798.method437(l + anInt762);
            aClass38_Sub2_Sub3_798.method437(j1);
        }
        if (i1 == 1607) {
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1_3 = aClass38_Sub7_Sub3_Sub1Array927[j1];
            if (class38_sub7_sub3_sub1_3 != null) {
                String s5;
                if (class38_sub7_sub3_sub1_3.npcType.aByteArray84 != null)
                    s5 = new String(class38_sub7_sub3_sub1_3.npcType.aByteArray84);
                else
                    s5 = "It's a " + class38_sub7_sub3_sub1_3.npcType.aString83 + ".";
                method111(0, s5, (byte) 4, "");
            }
        }
        if (i1 == 504)
            method92(172, k, l, j1, true);
        if (i1 == 930) {
            InterfaceComponent interfaceComponent = InterfaceComponent.interfaceComponentArray[l];
            anInt1025 = 1;
            anInt1026 = l;
            anInt1027 = interfaceComponent.anInt319;
            anInt1002 = 0;
            String s6 = interfaceComponent.aString317;
            if (s6.indexOf(" ") != -1)
                s6 = s6.substring(0, s6.indexOf(" "));
            String s10 = interfaceComponent.aString317;
            if (s10.indexOf(" ") != -1)
                s10 = s10.substring(s10.indexOf(" ") + 1);
            aString1028 = s6 + " " + interfaceComponent.aString318 + " " + s10;
            if (anInt1027 == 16) {
                aBoolean964 = true;
                anInt757 = 3;
                aBoolean1080 = true;
            }
            return;
        }
        if (i1 == 951) {
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.interfaceComponentArray[l];
            boolean flag3 = true;
            if (interfaceComponent_1.anInt273 > 0)
                flag3 = method81(false, interfaceComponent_1);
            if (flag3) {
                aClass38_Sub2_Sub3_798.method435((byte) -34, 155);
                aClass38_Sub2_Sub3_798.method437(l);
            }
        }
        if (i1 == 602 || i1 == 596 || i1 == 22 || i1 == 892 || i1 == 415) {
            if (i1 == 22)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 212);
            if (i1 == 415) {
                if ((l & 3) == 0)
                    anInt937++;
                if (anInt937 >= 55) {
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 17);
                    aClass38_Sub2_Sub3_798.method440(0);
                }
                aClass38_Sub2_Sub3_798.method435((byte) -34, 6);
            }
            if (i1 == 602)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 31);
            if (i1 == 892) {
                if ((k & 3) == 0)
                    anInt876++;
                if (anInt876 >= 130) {
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 238);
                    aClass38_Sub2_Sub3_798.method436(177);
                }
                aClass38_Sub2_Sub3_798.method435((byte) -34, 38);
            }
            if (i1 == 596)
                aClass38_Sub2_Sub3_798.method435((byte) -34, 59);
            aClass38_Sub2_Sub3_798.method437(j1);
            aClass38_Sub2_Sub3_798.method437(k);
            aClass38_Sub2_Sub3_798.method437(l);
            anInt944 = 0;
            anInt945 = l;
            anInt946 = k;
            anInt947 = 2;
            if (InterfaceComponent.interfaceComponentArray[l].anInt270 == anInt971)
                anInt947 = 1;
            if (InterfaceComponent.interfaceComponentArray[l].anInt270 == anInt1001)
                anInt947 = 3;
        }
        if (i1 == 581) {
            if ((j1 & 3) == 0)
                anInt772++;
            if (anInt772 >= 99) {
                aClass38_Sub2_Sub3_798.method435((byte) -34, 7);
                aClass38_Sub2_Sub3_798.method440(0);
            }
            method92(97, k, l, j1, true);
        }
        if (i1 == 965) {
            boolean flag2 = method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 0, false,
                    k, aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 0, l, 0, 0, 0);
            if (!flag2)
                flag2 = method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false, k,
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1, l, 0, 0, 0);
            anInt738 = super.anInt24;
            anInt739 = super.anInt25;
            anInt741 = 2;
            anInt740 = 0;
            aClass38_Sub2_Sub3_798.method435((byte) -34, 138);
            aClass38_Sub2_Sub3_798.method437(k + anInt761);
            aClass38_Sub2_Sub3_798.method437(l + anInt762);
            aClass38_Sub2_Sub3_798.method437(j1);
            aClass38_Sub2_Sub3_798.method437(anInt1026);
        }
        if (i1 == 1501) {
            anInt857 += anInt762;
            if (anInt857 >= 92) {
                aClass38_Sub2_Sub3_798.method435((byte) -34, 66);
                aClass38_Sub2_Sub3_798.method440(0);
            }
            method92(116, k, l, j1, true);
        }
        if (i1 == 364)
            method92(96, k, l, j1, true);
        if (i1 == 1102) {
            ObjType objType_1 = ObjType.method169(j1);
            String s7;
            if (objType_1.aByteArray146 != null)
                s7 = new String(objType_1.aByteArray146);
            else
                s7 = "It's a " + objType_1.aString145 + ".";
            method111(0, s7, (byte) 4, "");
        }
        if (i1 == 960) {
            aClass38_Sub2_Sub3_798.method435((byte) -34, 155);
            aClass38_Sub2_Sub3_798.method437(l);
            InterfaceComponent interfaceComponent_2 = InterfaceComponent.interfaceComponentArray[l];
            if (interfaceComponent_2.anIntArrayArray278 != null && interfaceComponent_2.anIntArrayArray278[0][0] == 5) {
                int j2 = interfaceComponent_2.anIntArrayArray278[0][1];
                if (anIntArray938[j2] != interfaceComponent_2.anIntArray280[0]) {
                    anIntArray938[j2] = interfaceComponent_2.anIntArray280[0];
                    method61(j2, 49);
                    aBoolean964 = true;
                }
            }
        }
        if (i1 == 34) {
            String s2 = aStringArray834[j];
            int k2 = s2.indexOf("@whi@");
            if (k2 != -1) {
                method16((byte) -60);
                aString970 = s2.substring(k2 + 5).trim();
                aBoolean881 = false;
                for (int j3 = 0; j3 < InterfaceComponent.interfaceComponentArray.length; j3++) {
                    if (InterfaceComponent.interfaceComponentArray[j3] == null || InterfaceComponent.interfaceComponentArray[j3].anInt273 != 600)
                        continue;
                    anInt907 = anInt971 = InterfaceComponent.interfaceComponentArray[j3].anInt270;
                    break;
                }

            }
        }
        if (i1 == 947)
            method16((byte) -60);
        if (i1 == 367) {
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2_1 = aClass38_Sub7_Sub3_Sub2Array822[j1];
            if (class38_sub7_sub3_sub2_1 != null) {
                method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false,
                        class38_sub7_sub3_sub2_1.anIntArray1428[0],
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1,
                        class38_sub7_sub3_sub2_1.anIntArray1429[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 248);
                aClass38_Sub2_Sub3_798.method437(j1);
                aClass38_Sub2_Sub3_798.method437(anInt1005);
                aClass38_Sub2_Sub3_798.method437(anInt1003);
                aClass38_Sub2_Sub3_798.method437(anInt1004);
            }
        }
        if (i1 == 465) {
            aClass38_Sub2_Sub3_798.method435((byte) -34, 155);
            aClass38_Sub2_Sub3_798.method437(l);
            InterfaceComponent interfaceComponent_3 = InterfaceComponent.interfaceComponentArray[l];
            if (interfaceComponent_3.anIntArrayArray278 != null && interfaceComponent_3.anIntArrayArray278[0][0] == 5) {
                int l2 = interfaceComponent_3.anIntArrayArray278[0][1];
                anIntArray938[l2] = 1 - anIntArray938[l2];
                method61(l2, 49);
                aBoolean964 = true;
            }
        }
        if (i1 == 406 || i1 == 436 || i1 == 557 || i1 == 556) {
            String s3 = aStringArray834[j];
            int i3 = s3.indexOf("@whi@");
            if (i3 != -1) {
                long l4 = StringUtils.toBase37(s3.substring(i3 + 5).trim());
                if (i1 == 406)
                    method100(l4, -460);
                if (i1 == 436)
                    method21(l4, (byte) 3);
                if (i1 == 557)
                    method113(43808, l4);
                if (i1 == 556)
                    method130(1, l4);
            }
        }
        if (i1 == 651) {
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2_2 = aClass38_Sub7_Sub3_Sub2Array822[j1];
            if (class38_sub7_sub3_sub2_2 != null) {
                method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 1, false,
                        class38_sub7_sub3_sub2_2.anIntArray1428[0],
                        aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 1,
                        class38_sub7_sub3_sub2_2.anIntArray1429[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 177);
                aClass38_Sub2_Sub3_798.method437(j1);
                aClass38_Sub2_Sub3_798.method437(anInt1026);
            }
        }
        anInt1002 = 0;
        if (i != 6412)
            method6();
        anInt1025 = 0;
    }

    public static String method72(int i, boolean flag, int j) {
        if (flag)
            throw new NullPointerException();
        int k = i - j;
        if (k < -9)
            return "@red@";
        if (k < -6)
            return "@or3@";
        if (k < -3)
            return "@or2@";
        if (k < 0)
            return "@or1@";
        if (k > 9)
            return "@gre@";
        if (k > 6)
            return "@gr3@";
        if (k > 3)
            return "@gr2@";
        if (k > 0)
            return "@gr1@";
        else
            return "@yel@";
    }

    public String method73(int i) {
        if (i != -7437)
            aClass38_Sub2_Sub3_798.method436(216);
        if (signlink.mainapp != null)
            return signlink.mainapp.getDocumentBase().getHost().toLowerCase();
        if (super.frame != null)
            return "runescape.com";
        else
            return super.getDocumentBase().getHost().toLowerCase();
    }

    public void method74(int i) {
        int j = anInt1149;
        int k = anInt1150;
        int l = anInt1151;
        int i1 = anInt1152;
        int j1 = 0x5d5447;
        Class38_Sub2_Sub2.method380(k, j, j1, (byte) 93, l, i1);
        Class38_Sub2_Sub2.method380(k + 1, j + 1, 0, (byte) 93, l - 2, 16);
        Class38_Sub2_Sub2.method381(3, j + 1, 0, i1 - 19, k + 18, l - 2);
        aClass38_Sub2_Sub2_Sub4_987.method424(j + 3, k + 14, false, j1, "Choose Option");
        int k1 = super.anInt21;
        int l1 = super.anInt22;
        if (anInt1148 == 0) {
            k1 -= 8;
            l1 -= 11;
        }
        if (anInt1148 == 1) {
            k1 -= 562;
            l1 -= 231;
        }
        if (anInt1148 == 2) {
            k1 -= 22;
            l1 -= 375;
        }
        for (int i2 = 0; i2 < anInt1074; i2++) {
            int j2 = k + 31 + (anInt1074 - 1 - i2) * 15;
            int k2 = 0xffffff;
            if (k1 > j && k1 < j + l && l1 > j2 - 13 && l1 < j2 + 3)
                k2 = 0xffff00;
            aClass38_Sub2_Sub2_Sub4_987.method426(j + 3, 6, j2, aStringArray834[i2], true, k2);
        }

        if (i >= 0)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
    }

    public void method75(int i, int j, int k) {
        if (anInt833 == 0)
            return;
        int l = 0;
        if (anInt957 != 0)
            l = 1;
        for (int i1 = 0; i1 < 100; i1++)
            if (aStringArray898[i1] != null) {
                int j1 = anIntArray896[i1];
                if ((j1 == 3 || j1 == 7)
                        && (j1 == 7 || anInt755 == 0 || anInt755 == 1 && method138(-20, aStringArray897[i1]))) {
                    int k1 = 329 - l * 13;
                    if (super.anInt21 > 8 && super.anInt21 < 520 && k - 11 > k1 - 10 && k - 11 <= k1 + 3) {
                        if (aBoolean1023) {
                            aStringArray834[anInt1074] = "Report abuse @whi@" + aStringArray897[i1];
                            anIntArray1141[anInt1074] = 2034;
                            anInt1074++;
                        }
                        aStringArray834[anInt1074] = "Add ignore @whi@" + aStringArray897[i1];
                        anIntArray1141[anInt1074] = 2436;
                        anInt1074++;
                        aStringArray834[anInt1074] = "Add friend @whi@" + aStringArray897[i1];
                        anIntArray1141[anInt1074] = 2406;
                        anInt1074++;
                    }
                    if (++l >= 5)
                        return;
                }
                if ((j1 == 5 || j1 == 6) && anInt755 < 2 && ++l >= 5)
                    return;
            }

        if (j != 27078)
            aBoolean787 = !aBoolean787;
    }

    public void method76(int i, InterfaceComponent interfaceComponent) {
        int j = interfaceComponent.anInt273;
        if (i != 0)
            anInt958 = -68;
        if (j >= 1 && j <= 100)
            if (--j >= anInt1089) {
                interfaceComponent.aString303 = "";
                interfaceComponent.anInt272 = 0;
                return;
            } else {
                interfaceComponent.aString303 = aStringArray1127[j];
                interfaceComponent.anInt272 = 1;
                return;
            }
        if (j >= 101 && j <= 200) {
            if ((j -= 101) >= anInt1089) {
                interfaceComponent.aString303 = "";
                interfaceComponent.anInt272 = 0;
                return;
            }
            if (anIntArray773[j] == 0)
                interfaceComponent.aString303 = "@red@Offline";
            else if (anIntArray773[j] == anInt886)
                interfaceComponent.aString303 = "@gre@World-" + (anIntArray773[j] - 9);
            else
                interfaceComponent.aString303 = "@yel@World-" + (anIntArray773[j] - 9);
            interfaceComponent.anInt272 = 1;
            return;
        }
        if (j == 203) {
            interfaceComponent.anInt282 = anInt1089 * 15 + 20;
            if (interfaceComponent.anInt282 <= interfaceComponent.anInt275)
                interfaceComponent.anInt282 = interfaceComponent.anInt275 + 1;
            return;
        }
        if (j >= 401 && j <= 500)
            if ((j -= 401) >= anInt793) {
                interfaceComponent.aString303 = "";
                interfaceComponent.anInt272 = 0;
                return;
            } else {
                interfaceComponent.aString303 = StringUtils.formatName(StringUtils.fromBase37(aLongArray768[j]));
                interfaceComponent.anInt272 = 1;
                return;
            }
        if (j == 503) {
            interfaceComponent.anInt282 = anInt793 * 15 + 20;
            if (interfaceComponent.anInt282 <= interfaceComponent.anInt275)
                interfaceComponent.anInt282 = interfaceComponent.anInt275 + 1;
            return;
        }
        if (j == 327) {
            interfaceComponent.anInt315 = 150;
            interfaceComponent.anInt316 = (int) (Math.sin((double) anInt955 / 40D) * 256D) & 0x7ff;
            if (aBoolean788) {
                aBoolean788 = false;
                Class38_Sub2_Sub1[] aclass38_sub2_sub1 = new Class38_Sub2_Sub1[7];
                int k = 0;
                for (int l = 0; l < 7; l++) {
                    int i1 = anIntArray789[l];
                    if (i1 >= 0)
                        aclass38_sub2_sub1[k++] = IdkType.idkTypes[i1].method216();
                }

                Class38_Sub2_Sub1 class38_sub2_sub1 = new Class38_Sub2_Sub1(0, aclass38_sub2_sub1, k);
                for (int j1 = 0; j1 < 5; j1++)
                    if (anIntArray742[j1] != 0) {
                        class38_sub2_sub1.method364(anIntArrayArray942[j1][0],
                                anIntArrayArray942[j1][anIntArray742[j1]]);
                        if (j1 == 1)
                            class38_sub2_sub1.method364(anIntArray1073[0], anIntArray1073[anIntArray742[j1]]);
                    }

                class38_sub2_sub1.applyGroups(4);
                class38_sub2_sub1.applyFrame(-16599,
                        SeqType.seqTypes[aClass38_Sub7_Sub3_Sub2_967.anInt1385].primaryFrames[0]);
                class38_sub2_sub1.applyLighting(64, 850, -30, -50, -30, true);
                interfaceComponent.aClass38_Sub2_Sub1_310 = class38_sub2_sub1;
            }
            return;
        }
        if (j == 324) {
            if (aClass38_Sub2_Sub2_Sub2_961 == null) {
                aClass38_Sub2_Sub2_Sub2_961 = interfaceComponent.aClass38_Sub2_Sub2_Sub2_308;
                aClass38_Sub2_Sub2_Sub2_962 = interfaceComponent.aClass38_Sub2_Sub2_Sub2_309;
            }
            if (aBoolean836) {
                interfaceComponent.aClass38_Sub2_Sub2_Sub2_308 = aClass38_Sub2_Sub2_Sub2_962;
                return;
            } else {
                interfaceComponent.aClass38_Sub2_Sub2_Sub2_308 = aClass38_Sub2_Sub2_Sub2_961;
                return;
            }
        }
        if (j == 325) {
            if (aClass38_Sub2_Sub2_Sub2_961 == null) {
                aClass38_Sub2_Sub2_Sub2_961 = interfaceComponent.aClass38_Sub2_Sub2_Sub2_308;
                aClass38_Sub2_Sub2_Sub2_962 = interfaceComponent.aClass38_Sub2_Sub2_Sub2_309;
            }
            if (aBoolean836) {
                interfaceComponent.aClass38_Sub2_Sub2_Sub2_308 = aClass38_Sub2_Sub2_Sub2_961;
                return;
            } else {
                interfaceComponent.aClass38_Sub2_Sub2_Sub2_308 = aClass38_Sub2_Sub2_Sub2_962;
                return;
            }
        }
        if (j == 600) {
            interfaceComponent.aString303 = aString970;
            if (anInt955 % 20 < 10) {
                interfaceComponent.aString303 += "|";
                return;
            } else {
                interfaceComponent.aString303 += " ";
                return;
            }
        }
        if (j == 613)
            if (aBoolean1023) {
                if (aBoolean881) {
                    interfaceComponent.anInt305 = 0xff0000;
                    interfaceComponent.aString303 = "Moderator option: Mute player for 48 hours: <ON>";
                } else {
                    interfaceComponent.anInt305 = 0xffffff;
                    interfaceComponent.aString303 = "Moderator option: Mute player for 48 hours: <OFF>";
                }
            } else {
                interfaceComponent.aString303 = "";
            }
        if (j == 650 || j == 655)
            if (anInt1061 != 0) {
                String s;
                if (anInt873 == 0)
                    s = "earlier today";
                else if (anInt873 == 1)
                    s = "yesterday";
                else
                    s = anInt873 + " days ago";
                interfaceComponent.aString303 = "You last logged in " + s + " from: " + signlink.dns;
            } else {
                interfaceComponent.aString303 = "";
            }
        if (j == 651) {
            if (anInt1054 == 0) {
                interfaceComponent.aString303 = "0 unread messages";
                interfaceComponent.anInt305 = 0xffff00;
            }
            if (anInt1054 == 1) {
                interfaceComponent.aString303 = "1 unread message";
                interfaceComponent.anInt305 = 65280;
            }
            if (anInt1054 > 1) {
                interfaceComponent.aString303 = anInt1054 + " unread messages";
                interfaceComponent.anInt305 = 65280;
            }
        }
        if (j == 652)
            if (anInt901 == 201)
                interfaceComponent.aString303 = "";
            else if (anInt901 == 200) {
                interfaceComponent.aString303 = "You have not yet set any password recovery questions.";
            } else {
                String s1;
                if (anInt901 == 0)
                    s1 = "Earlier today";
                else if (anInt901 == 1)
                    s1 = "Yesterday";
                else
                    s1 = anInt901 + " days ago";
                interfaceComponent.aString303 = s1 + " you changed your recovery questions";
            }
        if (j == 653)
            if (anInt901 == 201)
                interfaceComponent.aString303 = "";
            else if (anInt901 == 200)
                interfaceComponent.aString303 = "We strongly recommend you do so now to secure your account.";
            else
                interfaceComponent.aString303 = "If you do not remember making this change then cancel it immediately";
        if (j == 654) {
            if (anInt901 == 201) {
                interfaceComponent.aString303 = "";
                return;
            }
            if (anInt901 == 200) {
                interfaceComponent.aString303 = "Do this from the 'account management' area on our front webpage";
                return;
            }
            interfaceComponent.aString303 = "Do this from the 'account management' area on our front webpage";
        }
    }

    public boolean method77(byte[] abyte0, int i, int j) {
        if (j != 0)
            anInt780 = -1;
        if (abyte0 == null)
            return true;
        else
            return signlink.wavesave(abyte0, i);
    }

    public boolean method78(int i) {
        if (i <= 0)
            aClass38_Sub2_Sub3_798.method436(77);
        return signlink.wavereplay();
    }

    public void method79(int i, int j) {
        if (j != 0)
            linkedList3dArray = null;
        signlink.wavevol = i;
    }

    public void method80(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3, int i) {
        if (flag)
            method6();
        do {
            if (class38_sub2_sub3.anInt1330 + 21 >= i * 8)
                break;
            int j = class38_sub2_sub3.method457(9, 13);
            if (j == 8191)
                break;
            if (aClass38_Sub7_Sub3_Sub1Array927[j] == null)
                aClass38_Sub7_Sub3_Sub1Array927[j] = new Class38_Sub7_Sub3_Sub1();
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[j];
            anIntArray929[anInt928++] = j;
            class38_sub7_sub3_sub1.anInt1424 = anInt955;
            class38_sub7_sub3_sub1.npcType = NpcType.method148(class38_sub2_sub3.method457(9, 11));
            class38_sub7_sub3_sub1.anInt1384 = class38_sub7_sub3_sub1.npcType.aByte85;
            class38_sub7_sub3_sub1.anInt1387 = class38_sub7_sub3_sub1.npcType.anInt89;
            class38_sub7_sub3_sub1.anInt1388 = class38_sub7_sub3_sub1.npcType.anInt90;
            class38_sub7_sub3_sub1.anInt1389 = class38_sub7_sub3_sub1.npcType.anInt91;
            class38_sub7_sub3_sub1.anInt1390 = class38_sub7_sub3_sub1.npcType.anInt92;
            class38_sub7_sub3_sub1.anInt1385 = class38_sub7_sub3_sub1.npcType.anInt88;
            int k = class38_sub2_sub3.method457(9, 5);
            if (k > 15)
                k -= 32;
            int l = class38_sub2_sub3.method457(9, 5);
            if (l > 15)
                l -= 32;
            class38_sub7_sub3_sub1.method466(false, false,
                    aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0] + k,
                    aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0] + l);
            int i1 = class38_sub2_sub3.method457(9, 1);
            if (i1 == 1)
                anIntArray826[anInt825++] = j;
        } while (true);
        class38_sub2_sub3.method458(anInt813);
    }

    public boolean method81(boolean flag, InterfaceComponent interfaceComponent) {
        int i = interfaceComponent.anInt273;
        if (flag)
            method6();
        if (i == 201) {
            aBoolean965 = true;
            aBoolean1055 = false;
            aBoolean869 = true;
            aString765 = "";
            anInt760 = 1;
            aString775 = "Enter name of friend to add to list";
        }
        if (i == 202) {
            aBoolean965 = true;
            aBoolean1055 = false;
            aBoolean869 = true;
            aString765 = "";
            anInt760 = 2;
            aString775 = "Enter name of friend to delete from list";
        }
        if (i == 205) {
            anInt783 = 250;
            return true;
        }
        if (i == 501) {
            aBoolean965 = true;
            aBoolean1055 = false;
            aBoolean869 = true;
            aString765 = "";
            anInt760 = 4;
            aString775 = "Enter name of player to add to list";
        }
        if (i == 502) {
            aBoolean965 = true;
            aBoolean1055 = false;
            aBoolean869 = true;
            aString765 = "";
            anInt760 = 5;
            aString775 = "Enter name of player to delete from list";
        }
        if (i >= 300 && i <= 313) {
            int j = (i - 300) / 2;
            int i1 = i & 1;
            int l1 = anIntArray789[j];
            if (l1 != -1) {
                do {
                    if (i1 == 0 && --l1 < 0)
                        l1 = IdkType.anInt246 - 1;
                    if (i1 == 1 && ++l1 >= IdkType.anInt246)
                        l1 = 0;
                } while (IdkType.idkTypes[l1].aBoolean253
                        || IdkType.idkTypes[l1].anInt248 != j + (aBoolean836 ? 0 : 7));
                anIntArray789[j] = l1;
                aBoolean788 = true;
            }
        }
        if (i >= 314 && i <= 323) {
            int k = (i - 314) / 2;
            int j1 = i & 1;
            int i2 = anIntArray742[k];
            if (j1 == 0 && --i2 < 0)
                i2 = anIntArrayArray942[k].length - 1;
            if (j1 == 1 && ++i2 >= anIntArrayArray942[k].length)
                i2 = 0;
            anIntArray742[k] = i2;
            aBoolean788 = true;
        }
        if (i == 324 && !aBoolean836) {
            aBoolean836 = true;
            method51((byte) -6);
        }
        if (i == 325 && aBoolean836) {
            aBoolean836 = false;
            method51((byte) -6);
        }
        if (i == 326) {
            aClass38_Sub2_Sub3_798.method435((byte) -34, 52);
            aClass38_Sub2_Sub3_798.method436(aBoolean836 ? 0 : 1);
            for (int l = 0; l < 7; l++)
                aClass38_Sub2_Sub3_798.method436(anIntArray789[l]);

            for (int k1 = 0; k1 < 5; k1++)
                aClass38_Sub2_Sub3_798.method436(anIntArray742[k1]);

            return true;
        }
        if (i == 613)
            aBoolean881 = !aBoolean881;
        if (i >= 601 && i <= 612) {
            method16((byte) -60);
            if (aString970.length() > 0) {
                aClass38_Sub2_Sub3_798.method435((byte) -34, 190);
                aClass38_Sub2_Sub3_798.method442(true, StringUtils.toBase37(aString970));
                aClass38_Sub2_Sub3_798.method436(i - 601);
                aClass38_Sub2_Sub3_798.method436(aBoolean881 ? 1 : 0);
            }
        }
        return false;
    }

    public void method6() {
        if (signlink.sunjava)
            super.anInt9 = 5;
        if (!aBoolean889) {
            aBoolean799 = true;
            aBoolean812 = true;
            method12(this, 2);
            method14(false, 0xbc614e, "scape_main", 40000);
        }
        if (aBoolean1102) {
            aBoolean1070 = true;
            return;
        }
        aBoolean1102 = true;
        boolean flag = false;
        String s = method73(-7437);
        if (s.endsWith("jagex.com"))
            flag = true;
        if (s.endsWith("runescape.com"))
            flag = true;
        if (s.endsWith("192.168.1.2"))
            flag = true;
        if (s.endsWith("192.168.1.249"))
            flag = true;
        if (s.endsWith("192.168.1.252"))
            flag = true;
        if (s.endsWith("192.168.1.253"))
            flag = true;
        if (s.endsWith("192.168.1.254"))
            flag = true;
        if (s.endsWith("127.0.0.1"))
            flag = true;
        if (!flag) {
            aBoolean923 = true;
            return;
        }
        try {
            int i = 5;
            for (anIntArray811[8] = 0; anIntArray811[8] == 0; ) {
                method13(true, "Connecting to fileserver", 10);
                try {
                    DataInputStream datainputstream = method94("crc" + (int) (Math.random() * 99999999D));
                    Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, new byte[36]);
                    datainputstream.readFully(class38_sub2_sub3.aByteArray1328, 0, 36);
                    for (int k = 0; k < 9; k++)
                        anIntArray811[k] = class38_sub2_sub3.method451();

                    datainputstream.close();
                } catch (IOException _ex) {
                    for (int j = i; j > 0; j--) {
                        method13(true, "Error loading - Will retry in " + j + " secs.", 10);
                        try {
                            Thread.sleep(1000L);
                        } catch (Exception _ex2) {
                        }
                    }

                    i *= 2;
                    if (i > 60)
                        i = 60;
                }
            }

            this.fileArchive = method37("title screen", anIntArray811[1], "title", 10, 0);
            aClass38_Sub2_Sub2_Sub4_985 = new Class38_Sub2_Sub2_Sub4(this.fileArchive, "p11", 530);
            aClass38_Sub2_Sub2_Sub4_986 = new Class38_Sub2_Sub2_Sub4(this.fileArchive, "p12", 530);
            aClass38_Sub2_Sub2_Sub4_987 = new Class38_Sub2_Sub2_Sub4(this.fileArchive, "b12", 530);
            aClass38_Sub2_Sub2_Sub4_988 = new Class38_Sub2_Sub2_Sub4(this.fileArchive, "q8", 530);
            method128((byte) 5);
            method48(0);
            FileArchive fileArchive = method37("config", anIntArray811[2], "config", 15, 0);
            FileArchive fileArchive_1 = method37("interface", anIntArray811[3], "interface", 20, 0);
            FileArchive fileArchive_2 = method37("2d graphics", anIntArray811[4], "media", 30, 0);
            FileArchive fileArchive_3 = method37("3d graphics", anIntArray811[5], "models", 40, 0);
            FileArchive fileArchive_4 = method37("textures", anIntArray811[6], "textures", 60, 0);
            FileArchive fileArchive_5 = method37("chat system", anIntArray811[7], "wordenc", 65, 0);
            FileArchive fileArchive_6 = method37("sound effects", anIntArray811[8], "sounds", 70, 0);
            aByteArrayArrayArray840 = new byte[4][104][104];
            anIntArrayArrayArray794 = new int[4][105][105];
            aClass32_831 = new Class32(415, anIntArrayArrayArray794, 104, 4, 104);
            for (int l = 0; l < 4; l++)
                aClass8Array954[l] = new Class8(104, -708, 104);

            aClass38_Sub2_Sub2_Sub2_1053 = new Class38_Sub2_Sub2_Sub2(512, 512);
            method13(true, "Unpacking media", 75);
            aClass38_Sub2_Sub2_Sub3_981 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "invback", 0);
            aClass38_Sub2_Sub2_Sub3_983 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "chatback", 0);
            aClass38_Sub2_Sub2_Sub3_982 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "mapback", 0);
            aClass38_Sub2_Sub2_Sub3_908 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "backbase1", 0);
            aClass38_Sub2_Sub2_Sub3_909 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "backbase2", 0);
            aClass38_Sub2_Sub2_Sub3_910 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "backhmid1", 0);
            for (int i1 = 0; i1 < 13; i1++)
                aClass38_Sub2_Sub2_Sub3Array814[i1] = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "sideicons", i1);

            aClass38_Sub2_Sub2_Sub2_1145 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "compass", 0);
            try {
                for (int j1 = 0; j1 < 50; j1++)
                    aClass38_Sub2_Sub2_Sub3Array1038[j1] = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "mapscene", j1);

            } catch (Exception _ex) {
            }
            try {
                for (int k1 = 0; k1 < 50; k1++)
                    aClass38_Sub2_Sub2_Sub2Array1138[k1] = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "mapfunction", k1);

            } catch (Exception _ex) {
            }
            try {
                for (int l1 = 0; l1 < 20; l1++)
                    aClass38_Sub2_Sub2_Sub2Array776[l1] = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "hitmarks", l1);

            } catch (Exception _ex) {
            }
            try {
                for (int i2 = 0; i2 < 20; i2++)
                    aClass38_Sub2_Sub2_Sub2Array956[i2] = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "headicons", i2);

            } catch (Exception _ex) {
            }
            aClass38_Sub2_Sub2_Sub2_997 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "mapflag", 0);
            for (int j2 = 0; j2 < 8; j2++)
                aClass38_Sub2_Sub2_Sub2Array1120[j2] = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "cross", j2);

            aClass38_Sub2_Sub2_Sub2_1057 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "mapdots", 0);
            aClass38_Sub2_Sub2_Sub2_1058 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "mapdots", 1);
            aClass38_Sub2_Sub2_Sub2_1059 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "mapdots", 2);
            aClass38_Sub2_Sub2_Sub2_1060 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "mapdots", 3);
            aClass38_Sub2_Sub2_Sub3_1081 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "scrollbar", 0);
            aClass38_Sub2_Sub2_Sub3_1082 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "scrollbar", 1);
            aClass38_Sub2_Sub2_Sub3_891 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone1", 0);
            aClass38_Sub2_Sub2_Sub3_892 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone2", 0);
            aClass38_Sub2_Sub2_Sub3_893 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone3", 0);
            aClass38_Sub2_Sub2_Sub3_894 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone1", 0);
            aClass38_Sub2_Sub2_Sub3_894.method416(-725);
            aClass38_Sub2_Sub2_Sub3_895 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone2", 0);
            aClass38_Sub2_Sub2_Sub3_895.method416(-725);
            aClass38_Sub2_Sub2_Sub3_1040 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone1", 0);
            aClass38_Sub2_Sub2_Sub3_1040.method417((byte) -74);
            aClass38_Sub2_Sub2_Sub3_1041 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone2", 0);
            aClass38_Sub2_Sub2_Sub3_1041.method417((byte) -74);
            aClass38_Sub2_Sub2_Sub3_1042 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone3", 0);
            aClass38_Sub2_Sub2_Sub3_1042.method417((byte) -74);
            aClass38_Sub2_Sub2_Sub3_1043 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone1", 0);
            aClass38_Sub2_Sub2_Sub3_1043.method416(-725);
            aClass38_Sub2_Sub2_Sub3_1043.method417((byte) -74);
            aClass38_Sub2_Sub2_Sub3_1044 = new Class38_Sub2_Sub2_Sub3(fileArchive_2, "redstone2", 0);
            aClass38_Sub2_Sub2_Sub3_1044.method416(-725);
            aClass38_Sub2_Sub2_Sub3_1044.method417((byte) -74);
            Class38_Sub2_Sub2_Sub2 class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backleft1", 0);
            drawArea1 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backleft2", 0);
            drawArea2 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backright1", 0);
            drawArea3 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backright2", 0);
            drawArea4 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backtop1", 0);
            drawArea5 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backtop2", 0);
            drawArea6 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backvmid1", 0);
            drawArea7 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backvmid2", 0);
            drawArea8 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backvmid3", 0);
            drawArea9 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive_2, "backhmid2", 0);
            drawArea10 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.anInt1466,
                    class38_sub2_sub2_sub2.anInt1467);
            class38_sub2_sub2_sub2.method403(34676, 0, 0);
            int k2 = (int) (Math.random() * 21D) - 10;
            int l2 = (int) (Math.random() * 21D) - 10;
            int i3 = (int) (Math.random() * 21D) - 10;
            int j3 = (int) (Math.random() * 41D) - 20;
            for (int k3 = 0; k3 < 50; k3++) {
                if (aClass38_Sub2_Sub2_Sub2Array1138[k3] != null)
                    aClass38_Sub2_Sub2_Sub2Array1138[k3].method402(k2 + j3, l2 + j3, i3 + j3, true);
                if (aClass38_Sub2_Sub2_Sub3Array1038[k3] != null)
                    aClass38_Sub2_Sub2_Sub3Array1038[k3].method418(k2 + j3, l2 + j3, i3 + j3, true);
            }

            method13(true, "Unpacking textures", 80);
            Class38_Sub2_Sub2_Sub1.method389((byte) 2, fileArchive_4);
            Class38_Sub2_Sub2_Sub1.method393(true, 0.80000000000000004D);
            Class38_Sub2_Sub2_Sub1.method388(20, -20);
            method13(true, "Unpacking models", 83);
            Class38_Sub2_Sub1.method352(anInt958, fileArchive_3);
            SeqBase.method208(false, fileArchive_3);
            SeqFrame.method213(false, fileArchive_3);
            method13(true, "Unpacking config", 86);
            SeqType.method222(fileArchive, 473);
            LocType.load(fileArchive);
            FloType.method209(fileArchive, 473);
            ObjType.method167(fileArchive);
            NpcType.method146(fileArchive);
            IdkType.method214(fileArchive, 473);
            SpotAnimType.method224(fileArchive, 473);
            VarpType.method227(fileArchive, 473);
            ObjType.aBoolean142 = aBoolean888;
            if (!aBoolean889) {
                method13(true, "Unpacking sounds", 90);
                byte[] abyte0 = fileArchive_6.read("sounds.dat", null);
                Class38_Sub2_Sub3 class38_sub2_sub3_1 = new Class38_Sub2_Sub3(363, abyte0);
                SoundTrack.load(class38_sub2_sub3_1);
            }
            method13(true, "Unpacking interfaces", 92);
            Class38_Sub2_Sub2_Sub4[] aclass38_sub2_sub2_sub4 = {
                    aClass38_Sub2_Sub2_Sub4_985, aClass38_Sub2_Sub2_Sub4_986, aClass38_Sub2_Sub2_Sub4_987,
                    aClass38_Sub2_Sub2_Sub4_988
            };
            InterfaceComponent.method218(fileArchive_2, aclass38_sub2_sub2_sub4, 30, fileArchive_1);
            method13(true, "Preparing game engine", 97);
            for (int l3 = 0; l3 < 33; l3++) {
                int i4 = 999;
                int k4 = 0;
                for (int i5 = 0; i5 < 35; i5++) {
                    if (aClass38_Sub2_Sub2_Sub3_982.aByteArray1476[i5
                            + l3 * aClass38_Sub2_Sub2_Sub3_982.anInt1478] == 0) {
                        if (i4 == 999)
                            i4 = i5;
                        continue;
                    }
                    if (i4 == 999)
                        continue;
                    k4 = i5;
                    break;
                }

                anIntArray856[l3] = i4;
                anIntArray1117[l3] = k4 - i4;
            }

            for (int j4 = 9; j4 < 160; j4++) {
                int l4 = 999;
                int j5 = 0;
                for (int l5 = 10; l5 < 168; l5++) {
                    if (aClass38_Sub2_Sub2_Sub3_982.aByteArray1476[l5 + j4 * aClass38_Sub2_Sub2_Sub3_982.anInt1478] == 0
                            && (l5 > 34 || j4 > 34)) {
                        if (l4 == 999)
                            l4 = l5;
                        continue;
                    }
                    if (l4 == 999)
                        continue;
                    j5 = l5;
                    break;
                }

                anIntArray1133[j4 - 9] = l4 - 21;
                anIntArray953[j4 - 9] = j5 - l4;
            }

            Class38_Sub2_Sub2_Sub1.method386(96, 479, 0);
            anIntArray735 = Class38_Sub2_Sub2_Sub1.anIntArray1448;
            Class38_Sub2_Sub2_Sub1.method386(261, 190, 0);
            anIntArray736 = Class38_Sub2_Sub2_Sub1.anIntArray1448;
            Class38_Sub2_Sub2_Sub1.method386(334, 512, 0);
            anIntArray737 = Class38_Sub2_Sub2_Sub1.anIntArray1448;
            int[] ai = new int[9];
            for (int k5 = 0; k5 < 9; k5++) {
                int i6 = 128 + k5 * 32 + 15;
                int j6 = 600 + i6 * 3;
                int k6 = Class38_Sub2_Sub2_Sub1.anIntArray1446[i6];
                ai[k5] = j6 * k6 >> 16;
            }

            Class32.method318(ai, 800, 512, aByte871, 334, 500);
            Class24.method229(fileArchive_5);
            return;
        } catch (Exception exception) {
            aBoolean865 = true;
        }
    }

    public void method82(int i) {
        if (anInt846 != 0)
            return;
        aStringArray834[0] = "Cancel";
        anIntArray1141[0] = 1252;
        anInt1074 = 1;
        method75(super.anInt21, 27078, super.anInt22);
        anInt868 = 0;
        i = 12 / i;
        if (super.anInt21 > 8 && super.anInt22 > 11 && super.anInt21 < 520 && super.anInt22 < 345)
            if (anInt971 != -1)
                method29(super.anInt22, super.anInt21, 11, InterfaceComponent.interfaceComponentArray[anInt971], 5082, 8, 0);
            else
                method131((byte) 2);
        if (anInt868 != anInt1063)
            anInt1063 = anInt868;
        anInt868 = 0;
        if (super.anInt21 > 562 && super.anInt22 > 231 && super.anInt21 < 752 && super.anInt22 < 492)
            if (anInt1129 != -1)
                method29(super.anInt22, super.anInt21, 231, InterfaceComponent.interfaceComponentArray[anInt1129], 5082, 562, 0);
            else if (anIntArray861[anInt757] != -1)
                method29(super.anInt22, super.anInt21, 231, InterfaceComponent.interfaceComponentArray[anIntArray861[anInt757]], 5082,
                        562, 0);
        if (anInt868 != anInt941) {
            aBoolean964 = true;
            anInt941 = anInt868;
        }
        anInt868 = 0;
        if (super.anInt21 > 22 && super.anInt22 > 375 && super.anInt21 < 431 && super.anInt22 < 471)
            if (anInt1001 != -1)
                method29(super.anInt22, super.anInt21, 375, InterfaceComponent.interfaceComponentArray[anInt1001], 5082, 22, 0);
            else
                method31(super.anInt22 - 375, 0, super.anInt21 - 22);
        if (anInt1001 != -1 && anInt868 != anInt859) {
            aBoolean965 = true;
            anInt859 = anInt868;
        }
        for (boolean flag = false; !flag; ) {
            flag = true;
            for (int j = 0; j < anInt1074 - 1; j++)
                if (anIntArray1141[j] < 1000 && anIntArray1141[j + 1] > 1000) {
                    String s = aStringArray834[j];
                    aStringArray834[j] = aStringArray834[j + 1];
                    aStringArray834[j + 1] = s;
                    int k = anIntArray1141[j];
                    anIntArray1141[j] = anIntArray1141[j + 1];
                    anIntArray1141[j + 1] = k;
                    k = anIntArray1139[j];
                    anIntArray1139[j] = anIntArray1139[j + 1];
                    anIntArray1139[j + 1] = k;
                    k = anIntArray1140[j];
                    anIntArray1140[j] = anIntArray1140[j + 1];
                    anIntArray1140[j + 1] = k;
                    k = anIntArray1142[j];
                    anIntArray1142[j] = anIntArray1142[j + 1];
                    anIntArray1142[j + 1] = k;
                    flag = false;
                }

        }

    }

    public void method83(byte byte0) {
        LocType.models.method343();
        LocType.builtModels.method343();
        NpcType.aClass34_104.method343();
        ObjType.aClass34_178.method343();
        ObjType.aClass34_179.method343();
        Class38_Sub7_Sub3_Sub2.aClass34_1525.method343();
        SpotAnimType.aClass34_400.method343();
        if (byte0 == aByte843) {
            byte0 = 0;
            return;
        } else {
            aBoolean1147 = !aBoolean1147;
            return;
        }
    }

    public void method84(int i) {
        if (i != anInt756) {
            for (int j = 1; j > 0; j++)
                ;
        }
        method19(222);
        if (anInt741 == 1)
            aClass38_Sub2_Sub2_Sub2Array1120[anInt740 / 100].method405(anInt739 - 8 - 11, anInt738 - 8 - 8, false);
        if (anInt741 == 2)
            aClass38_Sub2_Sub2_Sub2Array1120[4 + anInt740 / 100].method405(anInt739 - 8 - 11, anInt738 - 8 - 8, false);
        if (anInt971 != -1) {
            method110(anInt971, anInt969, 623);
            method59(0, 0, 38682, InterfaceComponent.interfaceComponentArray[anInt971], 0);
        }
        method18(39734);
        if (!aBoolean879) {
            method82(26);
            method104(true);
        } else if (anInt1148 == 0)
            method74(-961);
        if (anInt984 == 1)
            if (anInt1101 > 0 || anInt933 == 1)
                aClass38_Sub2_Sub2_Sub2Array956[1].method405(258, 472, false);
            else
                aClass38_Sub2_Sub2_Sub2Array956[1].method405(296, 472, false);
        if (anInt1101 > 0) {
            aClass38_Sub2_Sub2_Sub2Array956[0].method405(296, 472, false);
            aClass38_Sub2_Sub2_Sub4_986.method421(329, (byte) 6, 0xffff00, "Level: " + anInt1101, 484);
        }
        if (anInt933 == 1) {
            aClass38_Sub2_Sub2_Sub2Array956[6].method405(296, 472, false);
            aClass38_Sub2_Sub2_Sub4_986.method421(329, (byte) 6, 0xffff00, "Arena", 484);
        }
        if (anInt957 != 0) {
            int k = anInt957 / 50;
            int l = k / 60;
            k %= 60;
            if (k < 10) {
                aClass38_Sub2_Sub2_Sub4_986.method424(4, 329, false, 0xffff00, "System update in: " + l + ":0" + k);
                return;
            }
            aClass38_Sub2_Sub2_Sub4_986.method424(4, 329, false, 0xffff00, "System update in: " + l + ":" + k);
        }
    }

    public void method85(int i) {
        int j = aClass38_Sub7_Sub3_Sub2_967.anInt1380 + anInt1125;
        int k = aClass38_Sub7_Sub3_Sub2_967.anInt1381 + anInt1130;
        if (anInt914 - j < -500 || anInt914 - j > 500 || anInt915 - k < -500 || anInt915 - k > 500) {
            anInt914 = j;
            anInt915 = k;
        }
        if (anInt914 != j)
            anInt914 += (j - anInt914) / 16;
        if (anInt915 != k)
            anInt915 += (k - anInt915) / 16;
        if (super.anIntArray26[1] == 1)
            anInt818 += (-24 - anInt818) / 2;
        else if (super.anIntArray26[2] == 1)
            anInt818 += (24 - anInt818) / 2;
        else
            anInt818 /= 2;
        if (super.anIntArray26[3] == 1)
            anInt819 += (12 - anInt819) / 2;
        else if (super.anIntArray26[4] == 1)
            anInt819 += (-12 - anInt819) / 2;
        else
            anInt819 /= 2;
        anInt817 = anInt817 + anInt818 / 2 & 0x7ff;
        anInt779 += i;
        anInt816 += anInt819 / 2;
        if (anInt816 < 128)
            anInt816 = 128;
        if (anInt816 > 383)
            anInt816 = 383;
        int l = anInt914 >> 7;
        int i1 = anInt915 >> 7;
        int j1 = method33(anInt880, anInt914, (byte) 5, anInt915);
        int k1 = 0;
        if (l > 3 && i1 > 3 && l < 100 && i1 < 100) {
            for (int l1 = l - 4; l1 <= l + 4; l1++) {
                for (int j2 = i1 - 4; j2 <= i1 + 4; j2++) {
                    int k2 = anInt880;
                    if (k2 < 3 && (aByteArrayArrayArray840[1][l1][j2] & 2) == 2)
                        k2++;
                    int l2 = j1 - anIntArrayArrayArray794[k2][l1][j2];
                    if (l2 > k1)
                        k1 = l2;
                }

            }

        }
        int i2 = k1 * 192;
        if (i2 > 0x17f00)
            i2 = 0x17f00;
        if (i2 < 32768)
            i2 = 32768;
        if (i2 > anInt932) {
            anInt932 += (i2 - anInt932) / 24;
            return;
        }
        if (i2 < anInt932)
            anInt932 += (i2 - anInt932) / 80;
    }

    public void method86(byte byte0) {
        if (byte0 != -26)
            anInt780 = -1;
        for (ProjectileEntity projectileEntity = (ProjectileEntity) linkedList4
                .method270(); projectileEntity != null; projectileEntity = (ProjectileEntity) linkedList4
                .method272())
            if (projectileEntity.level != anInt880 || anInt955 > projectileEntity.lastCycle)
                projectileEntity.unlink();
            else if (anInt955 >= projectileEntity.firstCycle) {
                if (projectileEntity.targetIndex > 0) {
                    Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[projectileEntity.targetIndex
                            - 1];
                    if (class38_sub7_sub3_sub1 != null)
                        projectileEntity.setTarget(
                                method33(projectileEntity.level,
                                        class38_sub7_sub3_sub1.anInt1380, (byte) 5,
                                        class38_sub7_sub3_sub1.anInt1381)
                                        - projectileEntity.baseZ,
                                class38_sub7_sub3_sub1.anInt1381,
                                class38_sub7_sub3_sub1.anInt1380, anInt955);
                }
                if (projectileEntity.targetIndex < 0) {
                    int i = -projectileEntity.targetIndex - 1;
                    Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2;
                    if (i == anInt734)
                        class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2_967;
                    else
                        class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[i];
                    if (class38_sub7_sub3_sub2 != null)
                        projectileEntity.setTarget(
                                method33(projectileEntity.level,
                                        class38_sub7_sub3_sub2.anInt1380, (byte) 5,
                                        class38_sub7_sub3_sub2.anInt1381)
                                        - projectileEntity.baseZ,
                                class38_sub7_sub3_sub2.anInt1381,
                                class38_sub7_sub3_sub2.anInt1380, anInt955);
                }
                projectileEntity.update(anInt969);
                aClass32_831.method292(-44713, (int) projectileEntity.y, 60, projectileEntity.yaw,
                        (int) projectileEntity.x, -1, false, null, projectileEntity,
                        (int) projectileEntity.z, anInt880);
            }

    }

    public void method10(int i) {
        if (i != 3)
            aBoolean870 = !aBoolean870;
        aBoolean751 = true;
    }

    public void method87(int i, int j, Class38_Sub2_Sub2_Sub2 class38_sub2_sub2_sub2, int k) {
        int l = anInt817 + anInt1085 & 0x7ff;
        int i1 = k * k + i * i;
        if (j != 4)
            aBoolean860 = !aBoolean860;
        if (i1 > 6400)
            return;
        int j1 = Class38_Sub2_Sub1.anIntArray1300[l];
        int k1 = Class38_Sub2_Sub1.anIntArray1301[l];
        j1 = (j1 * 256) / (anInt930 + 256);
        k1 = (k1 * 256) / (anInt930 + 256);
        int l1 = i * j1 + k * k1 >> 16;
        int i2 = i * k1 - k * j1 >> 16;
        if (i1 > 2500) {
            class38_sub2_sub2_sub2.method412(aClass38_Sub2_Sub2_Sub3_982,
                    83 - i2 - class38_sub2_sub2_sub2.anInt1471 / 2, (94 + l1) - class38_sub2_sub2_sub2.anInt1470 / 2,
                    (byte) -15);
            return;
        } else {
            class38_sub2_sub2_sub2.method405(83 - i2 - class38_sub2_sub2_sub2.anInt1471 / 2,
                    (94 + l1) - class38_sub2_sub2_sub2.anInt1470 / 2, false);
            return;
        }
    }

    public int method88(int i, int j, int k, int l) {
        int i1 = 256 - j;
        if (l <= 0)
            aBoolean870 = !aBoolean870;
        return ((i & 0xff00ff) * i1 + (k & 0xff00ff) * j & 0xff00ff00)
                + ((i & 0xff00) * i1 + (k & 0xff00) * j & 0xff0000) >> 8;
    }

    public String method89(int i, int j) {
        if (j >= 0)
            anInt1132 = -430;
        if (i < 0x3b9ac9ff)
            return String.valueOf(i);
        else
            return "*";
    }

    public void method90(int i, boolean flag, Class38_Sub7_Sub3 class38_sub7_sub3) {
        method91(class38_sub7_sub3.anInt1381, class38_sub7_sub3.anInt1380, anInt1105, i);
        if (flag)
            anInt780 = -1;
    }

    public void method91(int i, int j, int k, int l) {
        if (j < 128 || i < 128 || j > 13056 || i > 13056) {
            anInt1019 = -1;
            anInt1020 = -1;
            return;
        }
        int i1 = method33(anInt880, j, (byte) 5, i) - l;
        j -= anInt1111;
        i1 -= anInt1112;
        i -= anInt1113;
        int j1 = Class38_Sub2_Sub1.anIntArray1300[anInt1114];
        int k1 = Class38_Sub2_Sub1.anIntArray1301[anInt1114];
        int l1 = Class38_Sub2_Sub1.anIntArray1300[anInt1115];
        int i2 = Class38_Sub2_Sub1.anIntArray1301[anInt1115];
        int j2 = i * l1 + j * i2 >> 16;
        i = i * i2 - j * l1 >> 16;
        if (k >= 0)
            aClass38_Sub2_Sub3_798.method436(131);
        j = j2;
        j2 = i1 * k1 - i * j1 >> 16;
        i = i1 * j1 + i * k1 >> 16;
        i1 = j2;
        if (i >= 50) {
            anInt1019 = Class38_Sub2_Sub2_Sub1.anInt1442 + (j << 9) / i;
            anInt1020 = Class38_Sub2_Sub2_Sub1.anInt1443 + (i1 << 9) / i;
            return;
        } else {
            anInt1019 = -1;
            anInt1020 = -1;
            return;
        }
    }

    public boolean method92(int i, int j, int k, int l, boolean flag) {
        int i1 = l >> 14 & 0x7fff;
        int j1 = aClass32_831.method312(anInt880, j, k, l);
        if (j1 == -1)
            return false;
        int k1 = j1 & 0x1f;
        int l1 = j1 >> 6 & 3;
        if (k1 == 10 || k1 == 11 || k1 == 22) {
            LocType locType = LocType.get(i1);
            int i2;
            int j2;
            if (l1 == 0 || l1 == 2) {
                i2 = locType.sizeX;
                j2 = locType.sizeZ;
            } else {
                i2 = locType.sizeZ;
                j2 = locType.sizeX;
            }
            int k2 = locType.interactionSideFlags;
            if (l1 != 0)
                k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
            method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], i2, false, j,
                    aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, j2, k, 0, 0, k2);
        } else {
            method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 0, false, j,
                    aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 2, 0, k, l1, k1 + 1, 0);
        }
        anInt738 = super.anInt24;
        anInt739 = super.anInt25;
        anInt741 = 2;
        anInt740 = 0;
        aClass38_Sub2_Sub3_798.method435((byte) -34, i);
        aClass38_Sub2_Sub3_798.method437(j + anInt761);
        aClass38_Sub2_Sub3_798.method437(k + anInt762);
        aClass38_Sub2_Sub3_798.method437(i1);
        if (!flag)
            throw new NullPointerException();
        else
            return true;
    }

    public void method93(int i) {
        int j = aClass38_Sub2_Sub2_Sub4_987.method423(false, "Choose Option");
        for (int k = 0; k < anInt1074; k++) {
            int l = aClass38_Sub2_Sub2_Sub4_987.method423(false, aStringArray834[k]);
            if (l > j)
                j = l;
        }

        j += 8;
        int i1 = 15 * anInt1074 + 21;
        if (i >= 0)
            return;
        if (super.anInt24 > 8 && super.anInt25 > 11 && super.anInt24 < 520 && super.anInt25 < 345) {
            int j1 = super.anInt24 - 8 - j / 2;
            if (j1 + j > 512)
                j1 = 512 - j;
            if (j1 < 0)
                j1 = 0;
            int i2 = super.anInt25 - 11;
            if (i2 + i1 > 334)
                i2 = 334 - i1;
            if (i2 < 0)
                i2 = 0;
            aBoolean879 = true;
            anInt1148 = 0;
            anInt1149 = j1;
            anInt1150 = i2;
            anInt1151 = j;
            anInt1152 = 15 * anInt1074 + 22;
        }
        if (super.anInt24 > 562 && super.anInt25 > 231 && super.anInt24 < 752 && super.anInt25 < 492) {
            int k1 = super.anInt24 - 562 - j / 2;
            if (k1 < 0)
                k1 = 0;
            else if (k1 + j > 190)
                k1 = 190 - j;
            int j2 = super.anInt25 - 231;
            if (j2 < 0)
                j2 = 0;
            else if (j2 + i1 > 261)
                j2 = 261 - i1;
            aBoolean879 = true;
            anInt1148 = 1;
            anInt1149 = k1;
            anInt1150 = j2;
            anInt1151 = j;
            anInt1152 = 15 * anInt1074 + 22;
        }
        if (super.anInt24 > 22 && super.anInt25 > 375 && super.anInt24 < 501 && super.anInt25 < 471) {
            int l1 = super.anInt24 - 22 - j / 2;
            if (l1 < 0)
                l1 = 0;
            else if (l1 + j > 479)
                l1 = 479 - j;
            int k2 = super.anInt25 - 375;
            if (k2 < 0)
                k2 = 0;
            else if (k2 + i1 > 96)
                k2 = 96 - i1;
            aBoolean879 = true;
            anInt1148 = 2;
            anInt1149 = l1;
            anInt1150 = k2;
            anInt1151 = j;
            anInt1152 = 15 * anInt1074 + 22;
        }
    }

    public DataInputStream method94(String s)
            throws IOException {
        if (signlink.mainapp != null)
            return signlink.openurl(s);
        else
            return new DataInputStream((new URL(getCodeBase(), s)).openStream());
    }

    public void method95(byte byte0) {
        if (drawArea11 != null)
            return;
        super.drawArea = null;
        drawArea23 = null;
        drawArea21 = null;
        drawArea20 = null;
        drawArea22 = null;
        drawArea24 = null;
        drawArea25 = null;
        drawArea26 = null;
        drawArea14 = new DrawArea(method11(aByte1116), 128, 265);
        Class38_Sub2_Sub2.method379(anInt1143);
        drawArea15 = new DrawArea(method11(aByte1116), 128, 265);
        Class38_Sub2_Sub2.method379(anInt1143);
        drawArea11 = new DrawArea(method11(aByte1116), 533, 186);
        Class38_Sub2_Sub2.method379(anInt1143);
        drawArea12 = new DrawArea(method11(aByte1116), 360, 146);
        Class38_Sub2_Sub2.method379(anInt1143);
        drawArea13 = new DrawArea(method11(aByte1116), 360, 200);
        Class38_Sub2_Sub2.method379(anInt1143);
        drawArea16 = new DrawArea(method11(aByte1116), 214, 267);
        Class38_Sub2_Sub2.method379(anInt1143);
        drawArea17 = new DrawArea(method11(aByte1116), 215, 267);
        Class38_Sub2_Sub2.method379(anInt1143);
        drawArea18 = new DrawArea(method11(aByte1116), 86, 79);
        if (byte0 != aByte866)
            aClass38_Sub2_Sub3_798.method436(73);
        Class38_Sub2_Sub2.method379(anInt1143);
        drawArea19 = new DrawArea(method11(aByte1116), 87, 79);
        Class38_Sub2_Sub2.method379(anInt1143);
        if (fileArchive != null) {
            method128((byte) 5);
            method48(0);
        }
        aBoolean751 = true;
    }

    public void method96(int i) {
        aBoolean1121 = true;
        try {
            long l = System.currentTimeMillis();
            int j = 0;
            int k = 20;
            while (aBoolean902) {
                method42(true);
                method42(true);
                method28(true);
                if (++j > 10) {
                    long l1 = System.currentTimeMillis();
                    int i1 = (int) (l1 - l) / 10 - k;
                    k = 40 - i1;
                    if (k < 5)
                        k = 5;
                    j = 0;
                    l = l1;
                }
                try {
                    Thread.sleep(k);
                } catch (Exception _ex) {
                }
            }
        } catch (Exception _ex) {
        }
        aBoolean1121 = false;
        if (i != -33833)
            aBoolean870 = !aBoolean870;
    }

    public void run() {
        if (aBoolean975) {
            method96(-33833);
            return;
        }
        if (aBoolean799) {
            method26(true);
            return;
        } else {
            super.run();
            return;
        }
    }

    public void method97(int i, int j, int k, int l, int i1, boolean flag, int j1,
                         int k1, InterfaceComponent interfaceComponent) {
        if (aBoolean1144)
            anInt1154 = 32;
        else
            anInt1154 = 0;
        aBoolean1144 = false;
        anInt779 += j;
        if (i >= j1 && i < j1 + 16 && k >= k1 && k < k1 + 16) {
            interfaceComponent.anInt283 -= anInt934 * 4;
            if (flag) {
                aBoolean964 = true;
                return;
            }
        } else if (i >= j1 && i < j1 + 16 && k >= (k1 + i1) - 16 && k < k1 + i1) {
            interfaceComponent.anInt283 += anInt934 * 4;
            if (flag) {
                aBoolean964 = true;
                return;
            }
        } else if (i >= j1 - anInt1154 && i < j1 + 16 + anInt1154 && k >= k1 + 16 && k < (k1 + i1) - 16
                && anInt934 > 0) {
            int l1 = ((i1 - 32) * i1) / l;
            if (l1 < 8)
                l1 = 8;
            int i2 = k - k1 - 16 - l1 / 2;
            int j2 = i1 - 32 - l1;
            interfaceComponent.anInt283 = ((l - i1) * i2) / j2;
            if (flag)
                aBoolean964 = true;
            aBoolean1144 = true;
        }
    }

    public void method98(String s, String s1, boolean flag) {
        signlink.errorname = s;
        try {
            if (!flag) {
                aString1083 = "";
                aString1084 = "Connecting to server...";
                method55(4);
            }
            bufferedStream = new BufferedStream(this, method101(43594 + anInt887));
            bufferedStream.read(aClass38_Sub2_Sub3_795.aByteArray1328, 0, 8);
            aClass38_Sub2_Sub3_795.offset = 0;
            aLong1146 = aClass38_Sub2_Sub3_795.method452(603);
            int[] ai = new int[4];
            ai[0] = (int) (Math.random() * 99999999D);
            ai[1] = (int) (Math.random() * 99999999D);
            ai[2] = (int) (aLong1146 >> 32);
            ai[3] = (int) aLong1146;
            aClass38_Sub2_Sub3_798.offset = 0;
            aClass38_Sub2_Sub3_798.method436(10);
            aClass38_Sub2_Sub3_798.method440(ai[0]);
            aClass38_Sub2_Sub3_798.method440(ai[1]);
            aClass38_Sub2_Sub3_798.method440(ai[2]);
            aClass38_Sub2_Sub3_798.method440(ai[3]);
            aClass38_Sub2_Sub3_798.method440(signlink.uid);
            aClass38_Sub2_Sub3_798.method443(s);
            aClass38_Sub2_Sub3_798.method443(s1);
            aClass38_Sub2_Sub3_798.method461(aBigInteger1062, aBigInteger922, anInt733);
            aClass38_Sub2_Sub3_743.offset = 0;
            if (flag)
                aClass38_Sub2_Sub3_743.method436(18);
            else
                aClass38_Sub2_Sub3_743.method436(16);
            aClass38_Sub2_Sub3_743.method436(aClass38_Sub2_Sub3_798.offset + 36 + 1 + 1);
            aClass38_Sub2_Sub3_743.method436(225);
            aClass38_Sub2_Sub3_743.method436(aBoolean889 ? 1 : 0);
            for (int i = 0; i < 9; i++)
                aClass38_Sub2_Sub3_743.method440(anIntArray811[i]);

            aClass38_Sub2_Sub3_743.method444(aClass38_Sub2_Sub3_798.aByteArray1328, aClass38_Sub2_Sub3_798.offset, 0,
                    (byte) -106);
            aClass38_Sub2_Sub3_798.isaacState = new IsaacRandom(ai);
            for (int j = 0; j < 4; j++)
                ai[j] += 50;

            isaacState = new IsaacRandom(ai);
            bufferedStream.write(aClass38_Sub2_Sub3_743.aByteArray1328, aClass38_Sub2_Sub3_743.offset, 0);
            int k = bufferedStream.read();
            if (k == 1) {
                try {
                    Thread.sleep(2000L);
                } catch (Exception _ex) {
                }
                method98(s, s1, flag);
                return;
            }
            if (k == 2 || k == 18) {
                aBoolean1023 = k == 18;
                Class7.method183((byte) 65);
                aBoolean974 = true;
                aClass38_Sub2_Sub3_798.offset = 0;
                aClass38_Sub2_Sub3_795.offset = 0;
                anInt780 = -1;
                anInt828 = -1;
                anInt829 = -1;
                anInt830 = -1;
                anInt779 = 0;
                anInt781 = 0;
                anInt957 = 0;
                anInt783 = 0;
                anInt911 = 0;
                anInt1074 = 0;
                aBoolean879 = false;
                super.anInt19 = 0;
                for (int l = 0; l < 100; l++)
                    aStringArray898[l] = null;

                anInt1002 = 0;
                anInt1025 = 0;
                anInt1078 = 0;
                anInt1018 = 0;
                anInt1125 = (int) (Math.random() * 100D) - 50;
                anInt1130 = (int) (Math.random() * 110D) - 55;
                anInt1134 = (int) (Math.random() * 80D) - 40;
                anInt1085 = (int) (Math.random() * 120D) - 60;
                anInt930 = (int) (Math.random() * 30D) - 20;
                anInt817 = (int) (Math.random() * 20D) - 10 & 0x7ff;
                anInt774 = -1;
                anInt1051 = 0;
                anInt1052 = 0;
                anInt823 = 0;
                anInt928 = 0;
                for (int i1 = 0; i1 < anInt820; i1++) {
                    aClass38_Sub7_Sub3_Sub2Array822[i1] = null;
                    aClass38_Sub2_Sub3Array827[i1] = null;
                }

                for (int j1 = 0; j1 < 8192; j1++)
                    aClass38_Sub7_Sub3_Sub1Array927[j1] = null;

                aClass38_Sub7_Sub3_Sub2_967 = aClass38_Sub7_Sub3_Sub2Array822[anInt821] = new Class38_Sub7_Sub3_Sub2();
                linkedList4.method274();
                linkedList1.method274();
                linkedList3.method274();
                for (int k1 = 0; k1 < 4; k1++) {
                    for (int l1 = 0; l1 < 104; l1++) {
                        for (int j2 = 0; j2 < 104; j2++)
                            linkedList3dArray[k1][l1][j2] = null;

                    }

                }

                linkedList5 = new LinkedList();
                anInt1089 = 0;
                anInt1021 = -1;
                anInt1001 = -1;
                anInt971 = -1;
                anInt1129 = -1;
                aBoolean872 = false;
                anInt757 = 3;
                aBoolean1055 = false;
                aBoolean879 = false;
                aBoolean869 = false;
                aString936 = null;
                anInt984 = 0;
                anInt1128 = -1;
                aBoolean836 = true;
                method51((byte) -6);
                for (int i2 = 0; i2 < 5; i2++)
                    anIntArray742[i2] = 0;

                anInt772 = 0;
                anInt867 = 0;
                anInt806 = 0;
                anInt797 = 0;
                anInt724 = 0;
                anInt857 = 0;
                anInt937 = 0;
                anInt890 = 0;
                anInt876 = 0;
                anInt1017 = 0;
                method56(-7185);
                return;
            }
            if (k == 3) {
                aString1083 = "";
                aString1084 = "Invalid username or password.";
                return;
            }
            if (k == 4) {
                aString1083 = "Your account has been disabled.";
                aString1084 = "Please check your message-centre for details.";
                return;
            }
            if (k == 5) {
                aString1083 = "Your account is already logged in.";
                aString1084 = "Try again in 60 secs...";
                return;
            }
            if (k == 6) {
                aString1083 = "RuneScape has been updated!";
                aString1084 = "Please reload this page.";
                return;
            }
            if (k == 7) {
                aString1083 = "This world is full.";
                aString1084 = "Please use a different world.";
                return;
            }
            if (k == 8) {
                aString1083 = "Unable to connect.";
                aString1084 = "Login server offline.";
                return;
            }
            if (k == 9) {
                aString1083 = "Login limit exceeded.";
                aString1084 = "Too many connections from your address.";
                return;
            }
            if (k == 10) {
                aString1083 = "Unable to connect.";
                aString1084 = "Bad session id.";
                return;
            }
            if (k == 11) {
                aString1084 = "Login server rejected session.";
                aString1084 = "Please try again.";
                return;
            }
            if (k == 12) {
                aString1083 = "You need a members account to login to this world.";
                aString1084 = "Please subscribe, or use a different world.";
                return;
            }
            if (k == 13) {
                aString1083 = "Could not complete login.";
                aString1084 = "Please try using a different world.";
                return;
            }
            if (k == 14) {
                aString1083 = "The server is being updated.";
                aString1084 = "Please wait 1 minute and try again.";
                return;
            }
            if (k == 15) {
                aBoolean974 = true;
                aClass38_Sub2_Sub3_798.offset = 0;
                aClass38_Sub2_Sub3_795.offset = 0;
                anInt780 = -1;
                anInt828 = -1;
                anInt829 = -1;
                anInt830 = -1;
                anInt779 = 0;
                anInt781 = 0;
                anInt957 = 0;
                anInt1074 = 0;
                aBoolean879 = false;
                return;
            }
            if (k == 16) {
                aString1083 = "Login attempts exceeded.";
                aString1084 = "Please wait 1 minute and try again.";
                return;
            }
            if (k == 17) {
                aString1083 = "You are standing in a members-only area.";
                aString1084 = "To play on this world move to a free area first";
                return;
            }
        } catch (IOException _ex) {
            aString1083 = "";
            aString1084 = "Error connecting to server.";
        }
    }

    public void method99(int i, int j, int k, int l, int i1, int j1, int k1,
                         int l1) {
        if (k1 != -27819)
            method6();
        if (j >= 1 && k >= 1 && j <= 102 && k <= 102) {
            if (aBoolean889 && l1 != anInt880)
                return;
            int i2 = 0;
            byte byte0 = -1;
            boolean flag = false;
            boolean flag1 = false;
            if (l == 0)
                i2 = aClass32_831.method308(l1, j, k);
            if (l == 1)
                i2 = aClass32_831.method309(l1, k, 3, j);
            if (l == 2)
                i2 = aClass32_831.method310(l1, j, k);
            if (l == 3)
                i2 = aClass32_831.method311(l1, j, k);
            if (i2 != 0) {
                int i3 = aClass32_831.method312(l1, j, k, i2);
                int j2 = i2 >> 14 & 0x7fff;
                int k2 = i3 & 0x1f;
                int l2 = i3 >> 6;
                if (l == 0) {
                    aClass32_831.method303(j, l1, k, 1);
                    LocType locType = LocType.get(j2);
                    if (locType.hasCollision)
                        aClass8Array954[l1].method201(locType.isSolid, l2, j, k, 323, k2);
                }
                if (l == 1)
                    aClass32_831.method304(l1, k, anInt1077, j);
                if (l == 2) {
                    aClass32_831.method305(j, k, -54, l1);
                    LocType locType = LocType.get(j2);
                    if (j + locType.sizeX > 103 || k + locType.sizeX > 103 || j + locType.sizeZ > 103
                            || k + locType.sizeZ > 103)
                        return;
                    if (locType.hasCollision)
                        aClass8Array954[l1].method202(k, j, l2, locType.sizeX, true, locType.isSolid,
                                locType.sizeZ);
                }
                if (l == 3) {
                    aClass32_831.method306(l1, anInt1022, j, k);
                    LocType locType_2 = LocType.get(j2);
                    if (locType_2.hasCollision && locType_2.interactable)
                        aClass8Array954[l1].method204(k, j, 0);
                }
            }
            if (i1 >= 0) {
                int j3 = l1;
                if (j3 < 3 && (aByteArrayArrayArray840[1][j][k] & 2) == 2)
                    j3++;
                Class3.method166(j, linkedList2, aClass8Array954[l1], k, i, anIntArrayArrayArray794, 0, l1, i1, j1,
                        aClass32_831, j3);
            }
        }
    }

    public void method100(long l, int i) {
        if (l == 0L)
            return;
        if (anInt1089 >= 100) {
            method111(0, "Your friends list is full. Max of 100 hit", (byte) 4, "");
            return;
        }
        String s = StringUtils.formatName(StringUtils.fromBase37(l));
        for (int j = 0; j < anInt1089; j++)
            if (aLongArray943[j] == l) {
                method111(0, s + " is already on your friend list", (byte) 4, "");
                return;
            }

        for (int k = 0; k < anInt793; k++)
            if (aLongArray768[k] == l) {
                method111(0, "Please remove " + s + " from your ignore list first", (byte) 4, "");
                return;
            }

        if (s.equals(aClass38_Sub7_Sub3_Sub2_967.aString1505))
            return;
        aStringArray1127[anInt1089] = s;
        aLongArray943[anInt1089] = l;
        anIntArray773[anInt1089] = 0;
        anInt1089++;
        if (i >= 0)
            anInt1039 = isaacState.nextInt();
        aBoolean964 = true;
        aClass38_Sub2_Sub3_798.method435((byte) -34, 118);
        aClass38_Sub2_Sub3_798.method442(true, l);
    }

    public void method8(byte byte0) {
        signlink.reporterror = false;
        try {
            if (bufferedStream != null)
                bufferedStream.close();
        } catch (Exception _ex) {
        }
        bufferedStream = null;
        method17(0);
        aBoolean812 = false;
        aClass38_Sub2_Sub3_798 = null;
        aClass38_Sub2_Sub3_743 = null;
        aClass38_Sub2_Sub3_795 = null;
        anIntArray925 = null;
        aByteArrayArray770 = null;
        aByteArrayArray1000 = null;
        anIntArrayArrayArray794 = null;
        aByteArrayArrayArray840 = null;
        aClass32_831 = null;
        aClass8Array954 = null;
        anIntArrayArray1118 = null;
        anIntArrayArray758 = null;
        anIntArray994 = null;
        anIntArray995 = null;
        aByteArray1069 = null;
        drawArea20 = null;
        drawArea21 = null;
        drawArea22 = null;
        drawArea23 = null;
        drawArea24 = null;
        drawArea25 = null;
        drawArea26 = null;
        drawArea1 = null;
        drawArea2 = null;
        drawArea3 = null;
        drawArea4 = null;
        drawArea5 = null;
        drawArea6 = null;
        drawArea7 = null;
        drawArea8 = null;
        drawArea9 = null;
        drawArea10 = null;
        aClass38_Sub2_Sub2_Sub3_981 = null;
        aClass38_Sub2_Sub2_Sub3_982 = null;
        aClass38_Sub2_Sub2_Sub3_983 = null;
        aClass38_Sub2_Sub2_Sub3_908 = null;
        aClass38_Sub2_Sub2_Sub3_909 = null;
        aClass38_Sub2_Sub2_Sub3_910 = null;
        aClass38_Sub2_Sub2_Sub3Array814 = null;
        aClass38_Sub2_Sub2_Sub3_891 = null;
        aClass38_Sub2_Sub2_Sub3_892 = null;
        aClass38_Sub2_Sub2_Sub3_893 = null;
        aClass38_Sub2_Sub2_Sub3_894 = null;
        aClass38_Sub2_Sub2_Sub3_895 = null;
        aClass38_Sub2_Sub2_Sub3_1040 = null;
        aClass38_Sub2_Sub2_Sub3_1041 = null;
        aClass38_Sub2_Sub2_Sub3_1042 = null;
        aClass38_Sub2_Sub2_Sub3_1043 = null;
        aClass38_Sub2_Sub2_Sub3_1044 = null;
        aClass38_Sub2_Sub2_Sub2_1145 = null;
        aClass38_Sub2_Sub2_Sub2Array776 = null;
        aClass38_Sub2_Sub2_Sub2Array956 = null;
        aClass38_Sub2_Sub2_Sub2Array1120 = null;
        aClass38_Sub2_Sub2_Sub2_1057 = null;
        aClass38_Sub2_Sub2_Sub2_1058 = null;
        aClass38_Sub2_Sub2_Sub2_1059 = null;
        aClass38_Sub2_Sub2_Sub2_1060 = null;
        aClass38_Sub2_Sub2_Sub3Array1038 = null;
        if (byte0 != -28)
            method6();
        aClass38_Sub2_Sub2_Sub2Array1138 = null;
        anIntArrayArray920 = null;
        aClass38_Sub7_Sub3_Sub2Array822 = null;
        anIntArray824 = null;
        anIntArray826 = null;
        aClass38_Sub2_Sub3Array827 = null;
        anIntArray940 = null;
        aClass38_Sub7_Sub3_Sub1Array927 = null;
        anIntArray929 = null;
        linkedList3dArray = null;
        linkedList5 = null;
        linkedList3 = null;
        linkedList4 = null;
        linkedList1 = null;
        linkedList2 = null;
        anIntArray1139 = null;
        anIntArray1140 = null;
        anIntArray1141 = null;
        anIntArray1142 = null;
        aStringArray834 = null;
        anIntArray938 = null;
        anIntArray918 = null;
        anIntArray919 = null;
        aClass38_Sub2_Sub2_Sub2Array791 = null;
        aClass38_Sub2_Sub2_Sub2_1053 = null;
        aStringArray1127 = null;
        aLongArray943 = null;
        anIntArray773 = null;
        drawArea14 = null;
        drawArea15 = null;
        drawArea11 = null;
        drawArea12 = null;
        drawArea13 = null;
        drawArea16 = null;
        drawArea17 = null;
        drawArea18 = null;
        drawArea19 = null;
        method38(true);
        LocType.unload();
        NpcType.method147(true);
        ObjType.method168(true);
        FloType.floTypes = null;
        IdkType.idkTypes = null;
        InterfaceComponent.interfaceComponentArray = null;
        SeqType.seqTypes = null;
        SpotAnimType.spotAnimTypes = null;
        SpotAnimType.aClass34_400 = null;
        VarpType.varpTypes = null;
        super.drawArea = null;
        Class38_Sub7_Sub3_Sub2.aClass34_1525 = null;
        Class38_Sub2_Sub2_Sub1.method384(true);
        Class32.method280(true);
        Class38_Sub2_Sub1.method351(true);
        SeqBase.seqBaseArray = null;
        SeqFrame.seqFrames = null;
        System.gc();
    }

    public Socket method101(int i)
            throws IOException {
        if (signlink.mainapp != null)
            return signlink.opensocket(i);
        else
            return new Socket(InetAddress.getByName(getCodeBase().getHost()), i);
    }

    public void method102(boolean flag, int i, int j, Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2, int k) {
        if (flag)
            anInt1022 = isaacState.nextInt();
        if (class38_sub7_sub3_sub2 == aClass38_Sub7_Sub3_Sub2_967)
            return;
        if (anInt1074 >= 400)
            return;
        String s = class38_sub7_sub3_sub2.aString1505
                + method72(aClass38_Sub7_Sub3_Sub2_967.anInt1511, false, class38_sub7_sub3_sub2.anInt1511) + " (level-"
                + class38_sub7_sub3_sub2.anInt1511 + ")";
        if (anInt1002 == 1) {
            aStringArray834[anInt1074] = "Use " + aString1006 + " with @whi@" + s;
            anIntArray1141[anInt1074] = 367;
            anIntArray1142[anInt1074] = j;
            anIntArray1139[anInt1074] = k;
            anIntArray1140[anInt1074] = i;
            anInt1074++;
        } else if (anInt1025 == 1) {
            if ((anInt1027 & 8) == 8) {
                aStringArray834[anInt1074] = aString1028 + " @whi@" + s;
                anIntArray1141[anInt1074] = 651;
                anIntArray1142[anInt1074] = j;
                anIntArray1139[anInt1074] = k;
                anIntArray1140[anInt1074] = i;
                anInt1074++;
            }
        } else {
            aStringArray834[anInt1074] = "Follow @whi@" + s;
            anIntArray1141[anInt1074] = 1544;
            anIntArray1142[anInt1074] = j;
            anIntArray1139[anInt1074] = k;
            anIntArray1140[anInt1074] = i;
            anInt1074++;
            if (anInt802 == 0) {
                aStringArray834[anInt1074] = "Trade with @whi@" + s;
                anIntArray1141[anInt1074] = 1373;
                anIntArray1142[anInt1074] = j;
                anIntArray1139[anInt1074] = k;
                anIntArray1140[anInt1074] = i;
                anInt1074++;
            }
            if (anInt1101 > 0) {
                aStringArray834[anInt1074] = "Attack @whi@" + s;
                if (aClass38_Sub7_Sub3_Sub2_967.anInt1511 >= class38_sub7_sub3_sub2.anInt1511)
                    anIntArray1141[anInt1074] = 151;
                else
                    anIntArray1141[anInt1074] = 2151;
                anIntArray1142[anInt1074] = j;
                anIntArray1139[anInt1074] = k;
                anIntArray1140[anInt1074] = i;
                anInt1074++;
            }
            if (anInt933 == 1) {
                aStringArray834[anInt1074] = "Fight @whi@" + s;
                anIntArray1141[anInt1074] = 151;
                anIntArray1142[anInt1074] = j;
                anIntArray1139[anInt1074] = k;
                anIntArray1140[anInt1074] = i;
                anInt1074++;
            }
            if (anInt933 == 2) {
                aStringArray834[anInt1074] = "Duel-with @whi@" + s;
                anIntArray1141[anInt1074] = 1101;
                anIntArray1142[anInt1074] = j;
                anIntArray1139[anInt1074] = k;
                anIntArray1140[anInt1074] = i;
                anInt1074++;
            }
        }
        for (int l = 0; l < anInt1074; l++)
            if (anIntArray1141[l] == 660) {
                aStringArray834[l] = "Walk here @whi@" + s;
                return;
            }

    }

    public void method103(boolean flag) {
        if (anInt957 > 1)
            anInt957--;
        if (anInt783 > 0)
            anInt783--;
        for (int i = 0; i < 5; i++)
            if (!method136(false))
                break;

        if (!flag)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
        if (!aBoolean974)
            return;
        for (int j = 0; j < anInt1018; j++)
            if (anIntArray858[j] <= 0) {
                boolean flag1 = false;
                try {
                    if (anIntArray1124[j] == anInt786 && anIntArray809[j] == anInt1065) {
                        if (!method78(anInt778))
                            flag1 = true;
                    } else {
                        Class38_Sub2_Sub3 class38_sub2_sub3_1 = SoundTrack.generate(anIntArray809[j],
                                anIntArray1124[j]);
                        if (System.currentTimeMillis() + (long) (class38_sub2_sub3_1.offset / 22) > aLong777
                                + (long) (anInt815 / 22)) {
                            anInt815 = class38_sub2_sub3_1.offset;
                            aLong777 = System.currentTimeMillis();
                            if (method77(class38_sub2_sub3_1.aByteArray1328, class38_sub2_sub3_1.offset, 0)) {
                                anInt786 = anIntArray1124[j];
                                anInt1065 = anIntArray809[j];
                            } else {
                                flag1 = true;
                            }
                        }
                    }
                } catch (Exception exception) {
                }
                if (!flag1 || anIntArray858[j] == -5) {
                    anInt1018--;
                    for (int k = j; k < anInt1018; k++) {
                        anIntArray1124[k] = anIntArray1124[k + 1];
                        anIntArray809[k] = anIntArray809[k + 1];
                        anIntArray858[k] = anIntArray858[k + 1];
                    }

                    j--;
                } else {
                    anIntArray858[j] = -5;
                }
            } else {
                anIntArray858[j]--;
            }

        if (anInt744 > 0) {
            anInt744 -= 20;
            if (anInt744 < 0)
                anInt744 = 0;
            if (anInt744 == 0 && aBoolean835 && !aBoolean889)
                method14(false, anInt1110, aString1119, anInt1155);
        }
        Class38_Sub2_Sub3 class38_sub2_sub3 = Class7.method184(-809);
        if (class38_sub2_sub3 != null) {
            aClass38_Sub2_Sub3_798.method435((byte) -34, 81);
            aClass38_Sub2_Sub3_798.method437(class38_sub2_sub3.offset);
            aClass38_Sub2_Sub3_798.method444(class38_sub2_sub3.aByteArray1328, class38_sub2_sub3.offset, 0,
                    (byte) -106);
            class38_sub2_sub3.method434((byte) 8);
        }
        anInt781++;
        if (anInt781 > 750)
            method121(false);
        method132(true);
        method62(true);
        method125(aByte1122);
        method44(anInt771);
        if ((super.anIntArray26[1] == 1 || super.anIntArray26[2] == 1 || super.anIntArray26[3] == 1
                || super.anIntArray26[4] == 1) && anInt916++ > 5) {
            anInt916 = 0;
            aClass38_Sub2_Sub3_798.method435((byte) -34, 189);
            aClass38_Sub2_Sub3_798.method437(anInt816);
            aClass38_Sub2_Sub3_798.method437(anInt817);
            aClass38_Sub2_Sub3_798.method436(anInt1085);
            aClass38_Sub2_Sub3_798.method436(anInt930);
        }
        anInt969++;
        if (anInt741 != 0) {
            anInt740 += 20;
            if (anInt740 >= 400)
                anInt741 = 0;
        }
        if (anInt947 != 0) {
            anInt944++;
            if (anInt944 >= 15) {
                if (anInt947 == 2)
                    aBoolean964 = true;
                if (anInt947 == 3)
                    aBoolean965 = true;
                anInt947 = 0;
            }
        }
        if (anInt846 != 0) {
            anInt924++;
            if (super.anInt21 > anInt847 + 5 || super.anInt21 < anInt847 - 5 || super.anInt22 > anInt848 + 5
                    || super.anInt22 < anInt848 - 5)
                aBoolean960 = true;
            if (super.anInt20 == 0) {
                if (anInt846 == 2)
                    aBoolean964 = true;
                if (anInt846 == 3)
                    aBoolean965 = true;
                anInt846 = 0;
                if (aBoolean960 && anInt924 >= 5) {
                    anInt1088 = -1;
                    method82(26);
                    if (anInt1088 == anInt844 && anInt1087 != anInt845) {
                        InterfaceComponent interfaceComponent = InterfaceComponent.interfaceComponentArray[anInt844];
                        int j1 = interfaceComponent.anIntArray265[anInt1087];
                        interfaceComponent.anIntArray265[anInt1087] = interfaceComponent.anIntArray265[anInt845];
                        interfaceComponent.anIntArray265[anInt845] = j1;
                        j1 = interfaceComponent.anIntArray266[anInt1087];
                        interfaceComponent.anIntArray266[anInt1087] = interfaceComponent.anIntArray266[anInt845];
                        interfaceComponent.anIntArray266[anInt845] = j1;
                        aClass38_Sub2_Sub3_798.method435((byte) -34, 159);
                        aClass38_Sub2_Sub3_798.method437(anInt844);
                        aClass38_Sub2_Sub3_798.method437(anInt845);
                        aClass38_Sub2_Sub3_798.method437(anInt1087);
                    }
                } else if ((anInt810 == 1 || method70(145, anInt1074 - 1)) && anInt1074 > 2)
                    method93(-386);
                else if (anInt1074 > 0)
                    method71(6412, anInt1074 - 1);
                anInt944 = 10;
                super.anInt23 = 0;
            }
        }
        anInt998++;
        if (anInt998 > 127) {
            anInt998 = 0;
            aClass38_Sub2_Sub3_798.method435((byte) -34, 215);
            aClass38_Sub2_Sub3_798.method439(0x4c2b2c);
        }
        if (Class32.anInt560 != -1) {
            int l = Class32.anInt560;
            int k1 = Class32.anInt561;
            boolean flag2 = method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 0, true, l,
                    aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 0, 0, k1, 0, 0, 0);
            Class32.anInt560 = -1;
            if (flag2) {
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 1;
                anInt740 = 0;
            }
        }
        if (super.anInt23 == 1 && aString936 != null) {
            aString936 = null;
            aBoolean965 = true;
            super.anInt23 = 0;
        }
        method116((byte) 3);
        method115((byte) 7);
        method118(anInt1132);
        method30(0);
        if (super.anInt20 == 1 || super.anInt23 == 1)
            anInt934++;
        if (anInt1078 == 2)
            method85(0);
        if (anInt1078 == 2 && aBoolean968)
            method117(aByte851);
        for (int i1 = 0; i1 < 5; i1++)
            anIntArray1024[i1]++;

        method35(7);
        super.anInt19++;
        if (super.anInt19 > 4500) {
            anInt783 = 250;
            super.anInt19 -= 500;
            aClass38_Sub2_Sub3_798.method435((byte) -34, 70);
        }
        anInt785++;
        if (anInt785 > 500) {
            anInt785 = 0;
            int l1 = (int) (Math.random() * 8D);
            if ((l1 & 1) == 1)
                anInt1125 += anInt1126;
            if ((l1 & 2) == 2)
                anInt1130 += anInt1131;
            if ((l1 & 4) == 4)
                anInt1134 += anInt1135;
        }
        if (anInt1125 < -50)
            anInt1126 = 2;
        if (anInt1125 > 50)
            anInt1126 = -2;
        if (anInt1130 < -55)
            anInt1131 = 2;
        if (anInt1130 > 55)
            anInt1131 = -2;
        if (anInt1134 < -40)
            anInt1135 = 1;
        if (anInt1134 > 40)
            anInt1135 = -1;
        anInt750++;
        if (anInt750 > 500) {
            anInt750 = 0;
            int i2 = (int) (Math.random() * 8D);
            if ((i2 & 1) == 1)
                anInt1085 += anInt1086;
            if ((i2 & 2) == 2)
                anInt930 += anInt931;
        }
        if (anInt1085 < -60)
            anInt1086 = 2;
        if (anInt1085 > 60)
            anInt1086 = -2;
        if (anInt930 < -20)
            anInt931 = 1;
        if (anInt930 > 10)
            anInt931 = -1;
        anInt1090++;
        if (anInt1090 > 110) {
            anInt1090 = 0;
            aClass38_Sub2_Sub3_798.method435((byte) -34, 236);
            aClass38_Sub2_Sub3_798.method440(0);
        }
        anInt782++;
        if (anInt782 > 50)
            aClass38_Sub2_Sub3_798.method435((byte) -34, 108);
        try {
            if (bufferedStream != null && aClass38_Sub2_Sub3_798.offset > 0) {
                bufferedStream.write(aClass38_Sub2_Sub3_798.aByteArray1328, aClass38_Sub2_Sub3_798.offset, 0);
                aClass38_Sub2_Sub3_798.offset = 0;
                anInt782 = 0;
                return;
            }
        } catch (IOException _ex) {
            method121(false);
            return;
        } catch (Exception exception1) {
            method58(-780);
        }
    }

    public void method104(boolean flag) {
        if (anInt1074 < 2 && anInt1002 == 0 && anInt1025 == 0)
            return;
        String s;
        if (anInt1002 == 1 && anInt1074 < 2)
            s = "Use " + aString1006 + " with...";
        else if (anInt1025 == 1 && anInt1074 < 2)
            s = aString1028 + "...";
        else
            s = aStringArray834[anInt1074 - 1];
        if (anInt1074 > 2)
            s = s + "@whi@ / " + (anInt1074 - 2) + " more options";
        aClass38_Sub2_Sub2_Sub4_987.method427(anInt955 / 1000, true, (byte) -121, 15, 0xffffff, s, 4);
        if (!flag)
            anInt780 = -1;
    }

    public void method105(byte byte0) {
        if (byte0 != aByte766)
            anInt805 = -357;
        for (Class38_Sub7_Sub2 class38_sub7_sub2 = (Class38_Sub7_Sub2) linkedList1
                .method270(); class38_sub7_sub2 != null; class38_sub7_sub2 = (Class38_Sub7_Sub2) linkedList1
                .method272())
            if (class38_sub7_sub2.anInt1371 != anInt880 || class38_sub7_sub2.aBoolean1377)
                class38_sub7_sub2.unlink();
            else if (anInt955 >= class38_sub7_sub2.anInt1370) {
                class38_sub7_sub2.method465(anInt969, 0);
                if (class38_sub7_sub2.aBoolean1377)
                    class38_sub7_sub2.unlink();
                else
                    aClass32_831.method292(-44713, class38_sub7_sub2.anInt1373, 60, 0, class38_sub7_sub2.anInt1372, -1,
                            false, null, class38_sub7_sub2, class38_sub7_sub2.anInt1374, class38_sub7_sub2.anInt1371);
            }

    }

    public URL getCodeBase() {
        if (signlink.mainapp != null)
            return signlink.mainapp.getCodeBase();
        try {
            if (super.frame != null)
                return new URL("http://127.0.0.1:" + (80 + anInt887));
        } catch (Exception _ex) {
        }
        return super.getCodeBase();
    }

    public static void method106(int i) {
        Class32.aBoolean526 = false;
        Class38_Sub2_Sub2_Sub1.aBoolean1437 = false;
        if (i != 9) {
            return;
        } else {
            aBoolean889 = false;
            Class3.aBoolean108 = false;
            return;
        }
    }

    public boolean method107(int i, int j, boolean flag, int k, int l, int i1, int j1,
                             int k1, int l1, int i2, int j2, int k2) {
        byte byte0 = 104;
        byte byte1 = 104;
        for (int l2 = 0; l2 < byte0; l2++) {
            for (int i3 = 0; i3 < byte1; i3++) {
                anIntArrayArray1118[l2][i3] = 0;
                anIntArrayArray758[l2][i3] = 0x5f5e0ff;
            }

        }

        int j3 = i;
        int k3 = l;
        anIntArrayArray1118[i][l] = 99;
        anIntArrayArray758[i][l] = 0;
        int l3 = 0;
        int i4 = 0;
        anIntArray994[l3] = i;
        anIntArray995[l3++] = l;
        boolean flag1 = false;
        int j4 = anIntArray994.length;
        int[][] ai = aClass8Array954[anInt880].anIntArrayArray215;
        while (i4 != l3) {
            j3 = anIntArray994[i4];
            k3 = anIntArray995[i4];
            i4 = (i4 + 1) % j4;
            if (j3 == k && k3 == l1) {
                flag1 = true;
                break;
            }
            if (j2 != 0) {
                if ((j2 < 5 || j2 == 10) && aClass8Array954[anInt880].method205(-7517, i2, l1, j2 - 1, k3, k, j3)) {
                    flag1 = true;
                    break;
                }
                if (j2 < 10 && aClass8Array954[anInt880].method206(i2, j2 - 1, anInt1039, j3, k, k3, l1)) {
                    flag1 = true;
                    break;
                }
            }
            if (j != 0 && k1 != 0 && aClass8Array954[anInt880].method207(k3, k1, j3, k, k2, l1, j, 168)) {
                flag1 = true;
                break;
            }
            int l4 = anIntArrayArray758[j3][k3] + 1;
            if (j3 > 0 && anIntArrayArray1118[j3 - 1][k3] == 0 && (ai[j3 - 1][k3] & 0x280108) == 0) {
                anIntArray994[l3] = j3 - 1;
                anIntArray995[l3] = k3;
                l3 = (l3 + 1) % j4;
                anIntArrayArray1118[j3 - 1][k3] = 2;
                anIntArrayArray758[j3 - 1][k3] = l4;
            }
            if (j3 < byte0 - 1 && anIntArrayArray1118[j3 + 1][k3] == 0 && (ai[j3 + 1][k3] & 0x280180) == 0) {
                anIntArray994[l3] = j3 + 1;
                anIntArray995[l3] = k3;
                l3 = (l3 + 1) % j4;
                anIntArrayArray1118[j3 + 1][k3] = 8;
                anIntArrayArray758[j3 + 1][k3] = l4;
            }
            if (k3 > 0 && anIntArrayArray1118[j3][k3 - 1] == 0 && (ai[j3][k3 - 1] & 0x280102) == 0) {
                anIntArray994[l3] = j3;
                anIntArray995[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray1118[j3][k3 - 1] = 1;
                anIntArrayArray758[j3][k3 - 1] = l4;
            }
            if (k3 < byte1 - 1 && anIntArrayArray1118[j3][k3 + 1] == 0 && (ai[j3][k3 + 1] & 0x280120) == 0) {
                anIntArray994[l3] = j3;
                anIntArray995[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray1118[j3][k3 + 1] = 4;
                anIntArrayArray758[j3][k3 + 1] = l4;
            }
            if (j3 > 0 && k3 > 0 && anIntArrayArray1118[j3 - 1][k3 - 1] == 0 && (ai[j3 - 1][k3 - 1] & 0x28010e) == 0
                    && (ai[j3 - 1][k3] & 0x280108) == 0 && (ai[j3][k3 - 1] & 0x280102) == 0) {
                anIntArray994[l3] = j3 - 1;
                anIntArray995[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray1118[j3 - 1][k3 - 1] = 3;
                anIntArrayArray758[j3 - 1][k3 - 1] = l4;
            }
            if (j3 < byte0 - 1 && k3 > 0 && anIntArrayArray1118[j3 + 1][k3 - 1] == 0
                    && (ai[j3 + 1][k3 - 1] & 0x280183) == 0 && (ai[j3 + 1][k3] & 0x280180) == 0
                    && (ai[j3][k3 - 1] & 0x280102) == 0) {
                anIntArray994[l3] = j3 + 1;
                anIntArray995[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray1118[j3 + 1][k3 - 1] = 9;
                anIntArrayArray758[j3 + 1][k3 - 1] = l4;
            }
            if (j3 > 0 && k3 < byte1 - 1 && anIntArrayArray1118[j3 - 1][k3 + 1] == 0
                    && (ai[j3 - 1][k3 + 1] & 0x280138) == 0 && (ai[j3 - 1][k3] & 0x280108) == 0
                    && (ai[j3][k3 + 1] & 0x280120) == 0) {
                anIntArray994[l3] = j3 - 1;
                anIntArray995[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray1118[j3 - 1][k3 + 1] = 6;
                anIntArrayArray758[j3 - 1][k3 + 1] = l4;
            }
            if (j3 < byte0 - 1 && k3 < byte1 - 1 && anIntArrayArray1118[j3 + 1][k3 + 1] == 0
                    && (ai[j3 + 1][k3 + 1] & 0x2801e0) == 0 && (ai[j3 + 1][k3] & 0x280180) == 0
                    && (ai[j3][k3 + 1] & 0x280120) == 0) {
                anIntArray994[l3] = j3 + 1;
                anIntArray995[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray1118[j3 + 1][k3 + 1] = 12;
                anIntArrayArray758[j3 + 1][k3 + 1] = l4;
            }
        }
        anInt989 = 0;
        if (!flag1) {
            if (flag) {
                int i5 = 100;
                for (int k5 = 1; k5 < 2; k5++) {
                    for (int i6 = k - k5; i6 <= k + k5; i6++) {
                        for (int k6 = l1 - k5; k6 <= l1 + k5; k6++)
                            if (i6 >= 0 && k6 >= 0 && i6 < 104 && k6 < 104 && anIntArrayArray758[i6][k6] < i5) {
                                i5 = anIntArrayArray758[i6][k6];
                                j3 = i6;
                                k3 = k6;
                                anInt989 = 1;
                                flag1 = true;
                            }

                    }

                    if (flag1)
                        break;
                }

            }
            if (!flag1)
                return false;
        }
        i4 = 0;
        anIntArray994[i4] = j3;
        if (i1 != 0)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
        anIntArray995[i4++] = k3;
        int l5;
        for (int j5 = l5 = anIntArrayArray1118[j3][k3]; j3 != i || k3 != l; j5 = anIntArrayArray1118[j3][k3]) {
            if (j5 != l5) {
                l5 = j5;
                anIntArray994[i4] = j3;
                anIntArray995[i4++] = k3;
            }
            if ((j5 & 2) != 0)
                j3++;
            else if ((j5 & 8) != 0)
                j3--;
            if ((j5 & 1) != 0)
                k3++;
            else if ((j5 & 4) != 0)
                k3--;
        }

        if (i4 > 0) {
            int k4 = i4;
            if (k4 > 25)
                k4 = 25;
            i4--;
            int j6 = anIntArray994[i4];
            int l6 = anIntArray995[i4];
            if (j1 == 0) {
                aClass38_Sub2_Sub3_798.method435((byte) -34, 181);
                aClass38_Sub2_Sub3_798.method436(k4 + k4 + 3);
            }
            if (j1 == 1) {
                aClass38_Sub2_Sub3_798.method435((byte) -34, 165);
                aClass38_Sub2_Sub3_798.method436(k4 + k4 + 3 + 14);
            }
            if (j1 == 2) {
                aClass38_Sub2_Sub3_798.method435((byte) -34, 93);
                aClass38_Sub2_Sub3_798.method436(k4 + k4 + 3);
            }
            if (super.anIntArray26[5] == 1)
                aClass38_Sub2_Sub3_798.method436(1);
            else
                aClass38_Sub2_Sub3_798.method436(0);
            aClass38_Sub2_Sub3_798.method437(j6 + anInt761);
            aClass38_Sub2_Sub3_798.method437(l6 + anInt762);
            anInt1051 = anIntArray994[0];
            anInt1052 = anIntArray995[0];
            for (int i7 = 1; i7 < k4; i7++) {
                i4--;
                aClass38_Sub2_Sub3_798.method436(anIntArray994[i4] - j6);
                aClass38_Sub2_Sub3_798.method436(anIntArray995[i4] - l6);
            }

            return true;
        }
        return j1 != 1;
    }

    public static String method108(boolean flag, int i) {
        if (!flag)
            anInt899 = 434;
        if (i < 0x186a0)
            return String.valueOf(i);
        if (i < 0x989680)
            return i / 1000 + "K";
        else
            return i / 0xf4240 + "M";
    }

    public void method109(Class38_Sub2_Sub3 class38_sub2_sub3, int i, int j) {
        anInt939 = 0;
        anInt825 = 0;
        method134(11522, i, class38_sub2_sub3);
        method49(i, class38_sub2_sub3, 0);
        method57(2, i, class38_sub2_sub3);
        method60(true, i, class38_sub2_sub3);
        for (int k = 0; k < anInt939; k++) {
            int l = anIntArray940[k];
            if (aClass38_Sub7_Sub3_Sub2Array822[l].anInt1424 != anInt955)
                aClass38_Sub7_Sub3_Sub2Array822[l] = null;
        }

        if (j <= 0)
            return;
        if (class38_sub2_sub3.offset != i) {
            signlink.reporterror(
                    "Error packet size mismatch in getplayer pos:" + class38_sub2_sub3.offset + " psize:" + i);
            throw new RuntimeException("eek");
        }
        for (int i1 = 0; i1 < anInt823; i1++)
            if (aClass38_Sub7_Sub3_Sub2Array822[anIntArray824[i1]] == null) {
                signlink.reporterror(aString1066 + " null entry in pl list - pos:" + i1 + " size:" + anInt823);
                throw new RuntimeException("eek");
            }

    }

    public boolean method110(int i, int j, int k) {
        boolean flag = false;
        InterfaceComponent interfaceComponent = InterfaceComponent.interfaceComponentArray[i];
        for (int l = 0; l < interfaceComponent.anIntArray285.length; l++) {
            if (interfaceComponent.anIntArray285[l] == -1)
                break;
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.interfaceComponentArray[interfaceComponent.anIntArray285[l]];
            if (interfaceComponent_1.anInt271 == 1)
                flag |= method110(interfaceComponent_1.anInt269, j, 623);
            if (interfaceComponent_1.anInt271 == 6 && (interfaceComponent_1.anInt312 != -1 || interfaceComponent_1.anInt313 != -1)) {
                boolean flag1 = method114(interfaceComponent_1, 65);
                int i1;
                if (flag1)
                    i1 = interfaceComponent_1.anInt313;
                else
                    i1 = interfaceComponent_1.anInt312;
                if (i1 != -1) {
                    SeqType seqType = SeqType.seqTypes[i1];
                    for (interfaceComponent_1.anInt268 += j; interfaceComponent_1.anInt268 > seqType.anIntArray368[interfaceComponent_1.anInt267]; ) {
                        interfaceComponent_1.anInt268 -= seqType.anIntArray368[interfaceComponent_1.anInt267] + 1;
                        interfaceComponent_1.anInt267++;
                        if (interfaceComponent_1.anInt267 >= seqType.anInt365) {
                            interfaceComponent_1.anInt267 -= seqType.anInt369;
                            if (interfaceComponent_1.anInt267 < 0 || interfaceComponent_1.anInt267 >= seqType.anInt365)
                                interfaceComponent_1.anInt267 = 0;
                        }
                        flag = true;
                    }

                }
            }
        }

        if (k <= 0)
            throw new NullPointerException();
        else
            return flag;
    }

    public void method111(int i, String s, byte byte0, String s1) {
        if (i == 0 && anInt1021 != -1) {
            aString936 = s;
            super.anInt23 = 0;
        }
        if (anInt1001 == -1)
            aBoolean965 = true;
        for (int j = 99; j > 0; j--) {
            anIntArray896[j] = anIntArray896[j - 1];
            aStringArray897[j] = aStringArray897[j - 1];
            aStringArray898[j] = aStringArray898[j - 1];
        }

        anIntArray896[0] = i;
        aStringArray897[0] = s1;
        if (byte0 != 4)
            aBoolean912 = !aBoolean912;
        aStringArray898[0] = s;
    }

    public void method112(int i, int j) {
        InterfaceComponent interfaceComponent = InterfaceComponent.interfaceComponentArray[j];
        for (int k = 0; k < interfaceComponent.anIntArray285.length; k++) {
            if (interfaceComponent.anIntArray285[k] == -1)
                break;
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.interfaceComponentArray[interfaceComponent.anIntArray285[k]];
            if (interfaceComponent_1.anInt271 == 1)
                method112(-321, interfaceComponent_1.anInt269);
            interfaceComponent_1.anInt267 = 0;
            interfaceComponent_1.anInt268 = 0;
        }

        if (i >= 0)
            linkedList3dArray = null;
    }

    public void method113(int i, long l) {
        if (i != 43808) {
            for (int j = 1; j > 0; j++)
                ;
        }
        if (l == 0L)
            return;
        for (int k = 0; k < anInt1089; k++)
            if (aLongArray943[k] == l) {
                anInt1089--;
                aBoolean964 = true;
                for (int i1 = k; i1 < anInt1089; i1++) {
                    aStringArray1127[i1] = aStringArray1127[i1 + 1];
                    anIntArray773[i1] = anIntArray773[i1 + 1];
                    aLongArray943[i1] = aLongArray943[i1 + 1];
                }

                aClass38_Sub2_Sub3_798.method435((byte) -34, 11);
                aClass38_Sub2_Sub3_798.method442(true, l);
                return;
            }

    }

    public boolean method114(InterfaceComponent interfaceComponent, int i) {
        if (interfaceComponent.anIntArray279 == null)
            return false;
        for (int j = 0; j < interfaceComponent.anIntArray279.length; j++) {
            int k = method126(interfaceComponent, false, j);
            int l = interfaceComponent.anIntArray280[j];
            if (interfaceComponent.anIntArray279[j] == 2) {
                if (k >= l)
                    return false;
            } else if (interfaceComponent.anIntArray279[j] == 3) {
                if (k <= l)
                    return false;
            } else if (interfaceComponent.anIntArray279[j] == 4) {
                if (k == l)
                    return false;
            } else if (k != l)
                return false;
        }

        if (i <= 0)
            aClass38_Sub2_Sub3_798.method436(82);
        return true;
    }

    public void method115(byte byte0) {
        if (byte0 != aByte1068)
            linkedList3dArray = null;
        if (super.anInt23 == 1) {
            int i = super.anInt24 - 21 - 561;
            int j = super.anInt25 - 9 - 5;
            if (i >= 0 && j >= 0 && i < 146 && j < 151) {
                i -= 73;
                j -= 75;
                int k = anInt817 + anInt1085 & 0x7ff;
                int l = Class38_Sub2_Sub2_Sub1.anIntArray1446[k];
                int i1 = Class38_Sub2_Sub2_Sub1.anIntArray1447[k];
                l = l * (anInt930 + 256) >> 8;
                i1 = i1 * (anInt930 + 256) >> 8;
                int j1 = j * l + i * i1 >> 11;
                int k1 = j * i1 - i * l >> 11;
                int l1 = aClass38_Sub7_Sub3_Sub2_967.anInt1380 + j1 >> 7;
                int i2 = aClass38_Sub7_Sub3_Sub2_967.anInt1381 - k1 >> 7;
                boolean flag = method107(aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0], 0, true,
                        l1, aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0], 0, 1, 0, i2, 0, 0,
                        0);
                if (flag) {
                    aClass38_Sub2_Sub3_798.method436(i);
                    aClass38_Sub2_Sub3_798.method436(j);
                    aClass38_Sub2_Sub3_798.method437(anInt817);
                    aClass38_Sub2_Sub3_798.method436(57);
                    aClass38_Sub2_Sub3_798.method436(anInt1085);
                    aClass38_Sub2_Sub3_798.method436(anInt930);
                    aClass38_Sub2_Sub3_798.method436(89);
                    aClass38_Sub2_Sub3_798.method437(aClass38_Sub7_Sub3_Sub2_967.anInt1380);
                    aClass38_Sub2_Sub3_798.method437(aClass38_Sub7_Sub3_Sub2_967.anInt1381);
                    aClass38_Sub2_Sub3_798.method436(anInt989);
                    aClass38_Sub2_Sub3_798.method436(63);
                }
            }
        }
    }

    public void method116(byte byte0) {
        if (byte0 != 3)
            anInt780 = aClass38_Sub2_Sub3_795.method446();
        if (anInt846 != 0)
            return;
        int i = super.anInt23;
        if (anInt1025 == 1 && super.anInt24 >= 520 && super.anInt25 >= 165 && super.anInt24 <= 788
                && super.anInt25 <= 230)
            i = 0;
        if (aBoolean879) {
            if (i != 1) {
                int j = super.anInt21;
                int i1 = super.anInt22;
                if (anInt1148 == 0) {
                    j -= 8;
                    i1 -= 11;
                }
                if (anInt1148 == 1) {
                    j -= 562;
                    i1 -= 231;
                }
                if (anInt1148 == 2) {
                    j -= 22;
                    i1 -= 375;
                }
                if (j < anInt1149 - 10 || j > anInt1149 + anInt1151 + 10 || i1 < anInt1150 - 10
                        || i1 > anInt1150 + anInt1152 + 10) {
                    aBoolean879 = false;
                    if (anInt1148 == 1)
                        aBoolean964 = true;
                    if (anInt1148 == 2)
                        aBoolean965 = true;
                }
            }
            if (i == 1) {
                int k = anInt1149;
                int j1 = anInt1150;
                int l1 = anInt1151;
                int j2 = super.anInt24;
                int k2 = super.anInt25;
                if (anInt1148 == 0) {
                    j2 -= 8;
                    k2 -= 11;
                }
                if (anInt1148 == 1) {
                    j2 -= 562;
                    k2 -= 231;
                }
                if (anInt1148 == 2) {
                    j2 -= 22;
                    k2 -= 375;
                }
                int l2 = -1;
                for (int i3 = 0; i3 < anInt1074; i3++) {
                    int j3 = j1 + 31 + (anInt1074 - 1 - i3) * 15;
                    if (j2 > k && j2 < k + l1 && k2 > j3 - 13 && k2 < j3 + 3)
                        l2 = i3;
                }

                if (l2 != -1)
                    method71(6412, l2);
                aBoolean879 = false;
                if (anInt1148 == 1)
                    aBoolean964 = true;
                if (anInt1148 == 2) {
                    aBoolean965 = true;
                    return;
                }
            }
        } else {
            if (i == 1 && anInt1074 > 0) {
                int l = anIntArray1141[anInt1074 - 1];
                if (l == 602 || l == 596 || l == 22 || l == 892 || l == 415 || l == 405 || l == 38 || l == 422
                        || l == 478 || l == 347 || l == 188) {
                    int k1 = anIntArray1139[anInt1074 - 1];
                    int i2 = anIntArray1140[anInt1074 - 1];
                    InterfaceComponent interfaceComponent = InterfaceComponent.interfaceComponentArray[i2];
                    if (interfaceComponent.aBoolean290) {
                        aBoolean960 = false;
                        anInt924 = 0;
                        anInt844 = i2;
                        anInt845 = k1;
                        anInt846 = 2;
                        anInt847 = super.anInt24;
                        anInt848 = super.anInt25;
                        if (InterfaceComponent.interfaceComponentArray[i2].anInt270 == anInt971)
                            anInt846 = 1;
                        if (InterfaceComponent.interfaceComponentArray[i2].anInt270 == anInt1001)
                            anInt846 = 3;
                        return;
                    }
                }
            }
            if (i == 1 && (anInt810 == 1 || method70(145, anInt1074 - 1)) && anInt1074 > 2)
                i = 2;
            if (i == 1 && anInt1074 > 0)
                method71(6412, anInt1074 - 1);
            if (i == 2 && anInt1074 > 0)
                method93(-386);
        }
    }

    public void method117(byte byte0) {
        int i = anInt728 * 128 + 64;
        int j = anInt729 * 128 + 64;
        int k = method33(anInt880, anInt728, (byte) 5, anInt729) - anInt730;
        if (anInt1111 < i) {
            anInt1111 += anInt731 + ((i - anInt1111) * anInt732) / 1000;
            if (anInt1111 > i)
                anInt1111 = i;
        }
        if (anInt1111 > i) {
            anInt1111 -= anInt731 + ((anInt1111 - i) * anInt732) / 1000;
            if (anInt1111 < i)
                anInt1111 = i;
        }
        if (anInt1112 < k) {
            anInt1112 += anInt731 + ((k - anInt1112) * anInt732) / 1000;
            if (anInt1112 > k)
                anInt1112 = k;
        }
        if (anInt1112 > k) {
            anInt1112 -= anInt731 + ((anInt1112 - k) * anInt732) / 1000;
            if (anInt1112 < k)
                anInt1112 = k;
        }
        if (anInt1113 < j) {
            anInt1113 += anInt731 + ((j - anInt1113) * anInt732) / 1000;
            if (anInt1113 > j)
                anInt1113 = j;
        }
        if (anInt1113 > j) {
            anInt1113 -= anInt731 + ((anInt1113 - j) * anInt732) / 1000;
            if (anInt1113 < j)
                anInt1113 = j;
        }
        i = anInt948 * 128 + 64;
        j = anInt949 * 128 + 64;
        k = method33(anInt880, anInt948, (byte) 5, anInt949) - anInt950;
        int l = i - anInt1111;
        int i1 = k - anInt1112;
        int j1 = j - anInt1113;
        int k1 = (int) Math.sqrt(l * l + j1 * j1);
        int l1 = (int) (Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
        int i2 = (int) (Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;
        if (byte0 != 2)
            return;
        if (l1 < 128)
            l1 = 128;
        if (l1 > 383)
            l1 = 383;
        if (anInt1114 < l1) {
            anInt1114 += anInt951 + ((l1 - anInt1114) * anInt952) / 1000;
            if (anInt1114 > l1)
                anInt1114 = l1;
        }
        if (anInt1114 > l1) {
            anInt1114 -= anInt951 + ((anInt1114 - l1) * anInt952) / 1000;
            if (anInt1114 < l1)
                anInt1114 = l1;
        }
        int j2 = i2 - anInt1115;
        if (j2 > 1024)
            j2 -= 2048;
        if (j2 < -1024)
            j2 += 2048;
        if (j2 > 0) {
            anInt1115 += anInt951 + (j2 * anInt952) / 1000;
            anInt1115 &= 0x7ff;
        }
        if (j2 < 0) {
            anInt1115 -= anInt951 + (-j2 * anInt952) / 1000;
            anInt1115 &= 0x7ff;
        }
        int k2 = i2 - anInt1115;
        if (k2 > 1024)
            k2 -= 2048;
        if (k2 < -1024)
            k2 += 2048;
        if (k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0)
            anInt1115 = i2;
    }

    public void method118(int i) {
        if (i != 29286)
            anInt771 = isaacState.nextInt();
        if (super.anInt23 == 1) {
            if (super.anInt24 >= 549 && super.anInt24 <= 583 && super.anInt25 >= 195 && super.anInt25 < 231
                    && anIntArray861[0] != -1) {
                aBoolean964 = true;
                anInt757 = 0;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 579 && super.anInt24 <= 609 && super.anInt25 >= 194 && super.anInt25 < 231
                    && anIntArray861[1] != -1) {
                aBoolean964 = true;
                anInt757 = 1;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 607 && super.anInt24 <= 637 && super.anInt25 >= 194 && super.anInt25 < 231
                    && anIntArray861[2] != -1) {
                aBoolean964 = true;
                anInt757 = 2;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 635 && super.anInt24 <= 679 && super.anInt25 >= 194 && super.anInt25 < 229
                    && anIntArray861[3] != -1) {
                aBoolean964 = true;
                anInt757 = 3;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 676 && super.anInt24 <= 706 && super.anInt25 >= 194 && super.anInt25 < 231
                    && anIntArray861[4] != -1) {
                aBoolean964 = true;
                anInt757 = 4;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 704 && super.anInt24 <= 734 && super.anInt25 >= 194 && super.anInt25 < 231
                    && anIntArray861[5] != -1) {
                aBoolean964 = true;
                anInt757 = 5;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 732 && super.anInt24 <= 766 && super.anInt25 >= 195 && super.anInt25 < 231
                    && anIntArray861[6] != -1) {
                aBoolean964 = true;
                anInt757 = 6;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 550 && super.anInt24 <= 584 && super.anInt25 >= 492 && super.anInt25 < 528
                    && anIntArray861[7] != -1) {
                aBoolean964 = true;
                anInt757 = 7;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 582 && super.anInt24 <= 612 && super.anInt25 >= 492 && super.anInt25 < 529
                    && anIntArray861[8] != -1) {
                aBoolean964 = true;
                anInt757 = 8;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 609 && super.anInt24 <= 639 && super.anInt25 >= 492 && super.anInt25 < 529
                    && anIntArray861[9] != -1) {
                aBoolean964 = true;
                anInt757 = 9;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 637 && super.anInt24 <= 681 && super.anInt25 >= 493 && super.anInt25 < 528
                    && anIntArray861[10] != -1) {
                aBoolean964 = true;
                anInt757 = 10;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 679 && super.anInt24 <= 709 && super.anInt25 >= 492 && super.anInt25 < 529
                    && anIntArray861[11] != -1) {
                aBoolean964 = true;
                anInt757 = 11;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 706 && super.anInt24 <= 736 && super.anInt25 >= 492 && super.anInt25 < 529
                    && anIntArray861[12] != -1) {
                aBoolean964 = true;
                anInt757 = 12;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 734 && super.anInt24 <= 768 && super.anInt25 >= 492 && super.anInt25 < 528
                    && anIntArray861[13] != -1) {
                aBoolean964 = true;
                anInt757 = 13;
                aBoolean1080 = true;
            }
            anInt882++;
            if (anInt882 > 150) {
                anInt882 = 0;
                aClass38_Sub2_Sub3_798.method435((byte) -34, 233);
                aClass38_Sub2_Sub3_798.method436(43);
            }
        }
    }

    public boolean method119(InterfaceComponent interfaceComponent, byte byte0) {
        int i = interfaceComponent.anInt273;
        if (byte0 != aByte790)
            anInt1039 = isaacState.nextInt();
        if (i >= 1 && i <= 200) {
            if (i >= 101)
                i -= 101;
            else
                i--;
            aStringArray834[anInt1074] = "Remove @whi@" + aStringArray1127[i];
            anIntArray1141[anInt1074] = 557;
            anInt1074++;
            aStringArray834[anInt1074] = "Message @whi@" + aStringArray1127[i];
            anIntArray1141[anInt1074] = 679;
            anInt1074++;
            return true;
        }
        if (i >= 401 && i <= 500) {
            aStringArray834[anInt1074] = "Remove @whi@" + interfaceComponent.aString303;
            anIntArray1141[anInt1074] = 556;
            anInt1074++;
            return true;
        } else {
            return false;
        }
    }

    public void method120(int i, Class38_Sub2_Sub3 class38_sub2_sub3, int j) {
        while (i >= 0)
            return;
        class38_sub2_sub3.method456(223);
        int k = class38_sub2_sub3.method457(9, 8);
        if (k < anInt928) {
            for (int l = k; l < anInt928; l++)
                anIntArray940[anInt939++] = anIntArray929[l];

        }
        if (k > anInt928) {
            signlink.reporterror(aString1066 + " Too many npcs");
            throw new RuntimeException("eek");
        }
        anInt928 = 0;
        for (int i1 = 0; i1 < k; i1++) {
            int j1 = anIntArray929[i1];
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[j1];
            int k1 = class38_sub2_sub3.method457(9, 1);
            if (k1 == 0) {
                anIntArray929[anInt928++] = j1;
                class38_sub7_sub3_sub1.anInt1424 = anInt955;
            } else {
                int l1 = class38_sub2_sub3.method457(9, 2);
                if (l1 == 0) {
                    anIntArray929[anInt928++] = j1;
                    class38_sub7_sub3_sub1.anInt1424 = anInt955;
                    anIntArray826[anInt825++] = j1;
                } else if (l1 == 1) {
                    anIntArray929[anInt928++] = j1;
                    class38_sub7_sub3_sub1.anInt1424 = anInt955;
                    int i2 = class38_sub2_sub3.method457(9, 3);
                    class38_sub7_sub3_sub1.method467(false, i2, (byte) 6);
                    int k2 = class38_sub2_sub3.method457(9, 1);
                    if (k2 == 1)
                        anIntArray826[anInt825++] = j1;
                } else if (l1 == 2) {
                    anIntArray929[anInt928++] = j1;
                    class38_sub7_sub3_sub1.anInt1424 = anInt955;
                    int j2 = class38_sub2_sub3.method457(9, 3);
                    class38_sub7_sub3_sub1.method467(true, j2, (byte) 6);
                    int l2 = class38_sub2_sub3.method457(9, 3);
                    class38_sub7_sub3_sub1.method467(true, l2, (byte) 6);
                    int i3 = class38_sub2_sub3.method457(9, 1);
                    if (i3 == 1)
                        anIntArray826[anInt825++] = j1;
                } else if (l1 == 3)
                    anIntArray940[anInt939++] = j1;
            }
        }

    }

    public String getParameter(String s) {
        if (signlink.mainapp != null)
            return signlink.mainapp.getParameter(s);
        else
            return super.getParameter(s);
    }

    public void method121(boolean flag) {
        if (anInt783 > 0) {
            method58(-780);
            return;
        }
        drawArea22.init2D();
        if (flag)
            method6();
        aClass38_Sub2_Sub2_Sub4_986.method421(144, (byte) 6, 0, "Connection lost", 257);
        aClass38_Sub2_Sub2_Sub4_986.method421(143, (byte) 6, 0xffffff, "Connection lost", 256);
        aClass38_Sub2_Sub2_Sub4_986.method421(159, (byte) 6, 0, "Please wait - attempting to reestablish", 257);
        aClass38_Sub2_Sub2_Sub4_986.method421(158, (byte) 6, 0xffffff, "Please wait - attempting to reestablish", 256);
        drawArea22.drawImage(11, super.aGraphics14, 8);
        anInt1051 = 0;
        BufferedStream bufferedStream = this.bufferedStream;
        aBoolean974 = false;
        method98(aString1066, aString1067, true);
        if (!aBoolean974)
            method58(-780);
        try {
            bufferedStream.close();
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void method122(int i, Class38_Sub2_Sub2_Sub3 class38_sub2_sub2_sub3) {
        int j = 256;
        for (int k = 0; k < anIntArray841.length; k++)
            anIntArray841[k] = 0;

        for (int l = 0; l < 5000; l++) {
            int i1 = (int) (Math.random() * 128D * (double) j);
            anIntArray841[i1] = (int) (Math.random() * 256D);
        }

        for (int j1 = 0; j1 < 20; j1++) {
            for (int k1 = 1; k1 < j - 1; k1++) {
                for (int i2 = 1; i2 < 127; i2++) {
                    int k2 = i2 + (k1 << 7);
                    anIntArray842[k2] = (anIntArray841[k2 - 1] + anIntArray841[k2 + 1] + anIntArray841[k2 - 128]
                            + anIntArray841[k2 + 128]) / 4;
                }

            }

            int[] ai = anIntArray841;
            anIntArray841 = anIntArray842;
            anIntArray842 = ai;
        }

        if (i <= 0)
            aBoolean912 = !aBoolean912;
        if (class38_sub2_sub2_sub3 != null) {
            int l1 = 0;
            for (int j2 = 0; j2 < class38_sub2_sub2_sub3.anInt1479; j2++) {
                for (int l2 = 0; l2 < class38_sub2_sub2_sub3.anInt1478; l2++)
                    if (class38_sub2_sub2_sub3.aByteArray1476[l1++] != 0) {
                        int i3 = l2 + 16 + class38_sub2_sub2_sub3.anInt1480;
                        int j3 = j2 + 16 + class38_sub2_sub2_sub3.anInt1481;
                        int k3 = i3 + (j3 << 7);
                        anIntArray841[k3] = 0;
                    }

            }

        }
    }

    public void method123(int i, int j) {
        LinkedList linkedList = linkedList3dArray[anInt880][i][j];
        if (linkedList == null) {
            aClass32_831.method307(anInt880, i, j);
            return;
        }
        int k = 0xfa0a1f01;
        Object obj = null;
        for (ObjStackEntity objStackEntity = (ObjStackEntity) linkedList
                .method270(); objStackEntity != null; objStackEntity = (ObjStackEntity) linkedList.method272()) {
            ObjType objType = ObjType.method169(objStackEntity.model);
            int i1 = objType.anInt158;
            if (objType.aBoolean157)
                i1 *= objStackEntity.amount + 1;
            if (i1 > k) {
                k = i1;
                obj = objStackEntity;
            }
        }

        linkedList.method268(((Node) (obj)));
        int l = -1;
        int j1 = -1;
        int k1 = 0;
        int l1 = 0;
        for (ObjStackEntity objStackEntity_1 = (ObjStackEntity) linkedList
                .method270(); objStackEntity_1 != null; objStackEntity_1 = (ObjStackEntity) linkedList.method272()) {
            if (objStackEntity_1.model != ((ObjStackEntity) (obj)).model && l == -1) {
                l = objStackEntity_1.model;
                k1 = objStackEntity_1.amount;
            }
            if (objStackEntity_1.model != ((ObjStackEntity) (obj)).model && objStackEntity_1.model != l
                    && j1 == -1) {
                j1 = objStackEntity_1.model;
                l1 = objStackEntity_1.amount;
            }
        }

        Class38_Sub2_Sub1 class38_sub2_sub1 = null;
        if (l != -1)
            class38_sub2_sub1 = ObjType.method169(l).method173(k1);
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = null;
        if (j1 != -1)
            class38_sub2_sub1_1 = ObjType.method169(j1).method173(l1);
        int i2 = i + (j << 7) + 0x60000000;
        ObjType objType_1 = ObjType.method169(((ObjStackEntity) (obj)).model);
        aClass32_831.method288(objType_1.method173(((ObjStackEntity) (obj)).amount), class38_sub2_sub1,
                method33(anInt880, i * 128 + 64, (byte) 5, j * 128 + 64), anInt880, i2, j, i, class38_sub2_sub1_1, 429);
    }

    public void method124(int i) {
        try {
            anInt774 = -1;
            linkedList3.method274();
            linkedList2.method274();
            linkedList1.method274();
            linkedList4.method274();
            Class38_Sub2_Sub2_Sub1.method387(false);
            method83(aByte843);
            aClass32_831.method281(742);
            for (int j = 0; j < 4; j++)
                aClass8Array954[j].method196((byte) 74);

            System.gc();
            Class3 class3 = new Class3(104, aByteArrayArrayArray840, 104, anIntArrayArrayArray794, anInt1106);
            byte[] abyte0 = new byte[0x186a0];
            int k = aByteArrayArray770.length;
            Class3.aBoolean108 = Class32.aBoolean526;
            for (int l = 0; l < k; l++) {
                int i1 = anIntArray925[l] >> 8;
                int k1 = anIntArray925[l] & 0xff;
                if (i1 == 33 && k1 >= 71 && k1 <= 73)
                    Class3.aBoolean108 = false;
            }

            if (Class3.aBoolean108)
                aClass32_831.method282(0, anInt880);
            else
                aClass32_831.method282(0, 0);
            aClass38_Sub2_Sub3_798.method435((byte) -34, 108);
            for (int j1 = 0; j1 < k; j1++) {
                int l1 = (anIntArray925[j1] >> 8) * 64 - anInt761;
                int j2 = (anIntArray925[j1] & 0xff) * 64 - anInt762;
                byte[] abyte2 = aByteArrayArray770[j1];
                if (abyte2 != null) {
                    int i3 = (new Class38_Sub2_Sub3(363, abyte2)).method451();
                    BZip2InputStream.read(abyte0, i3, abyte2, abyte2.length - 4, 4);
                    class3.method153(abyte0, (anInt838 - 6) * 8, 1, j2, l1, (anInt839 - 6) * 8);
                } else if (anInt839 < 800)
                    class3.method152(l1, j2, 3, 64, 64);
            }

            aClass38_Sub2_Sub3_798.method435((byte) -34, 108);
            for (int i2 = 0; i2 < k; i2++) {
                byte[] abyte1 = aByteArrayArray1000[i2];
                if (abyte1 != null) {
                    int k2 = (new Class38_Sub2_Sub3(363, abyte1)).method451();
                    BZip2InputStream.read(abyte0, k2, abyte1, abyte1.length - 4, 4);
                    int j3 = (anIntArray925[i2] >> 8) * 64 - anInt761;
                    int l3 = (anIntArray925[i2] & 0xff) * 64 - anInt762;
                    class3.method154(abyte0, aClass32_831, aClass8Array954, linkedList2, true, l3, j3);
                }
            }

            aClass38_Sub2_Sub3_798.method435((byte) -34, 108);
            class3.method156(aClass32_831, -270, aClass8Array954);
            drawArea22.init2D();
            aClass38_Sub2_Sub3_798.method435((byte) -34, 108);
            for (Class38_Sub5 class38_sub5 = (Class38_Sub5) linkedList2
                    .method270(); class38_sub5 != null; class38_sub5 = (Class38_Sub5) linkedList2.method272())
                if ((aByteArrayArrayArray840[1][class38_sub5.anInt1208][class38_sub5.anInt1209] & 2) == 2) {
                    class38_sub5.anInt1206--;
                    if (class38_sub5.anInt1206 < 0)
                        class38_sub5.unlink();
                }

            for (int l2 = 0; l2 < 104; l2++) {
                for (int k3 = 0; k3 < 104; k3++)
                    method123(l2, k3);

            }

            for (SpawnedLoc spawnedLoc = (SpawnedLoc) linkedList5
                    .method270(); spawnedLoc != null; spawnedLoc = (SpawnedLoc) linkedList5.method272())
                method99(spawnedLoc.rotation, spawnedLoc.tileX, spawnedLoc.tileZ, spawnedLoc.classType,
                        spawnedLoc.locIndex, spawnedLoc.type, -27819, spawnedLoc.level);

        } catch (Exception exception) {
        }
        LocType.models.method343();
        if (i <= 0)
            aBoolean1147 = !aBoolean1147;
        System.gc();
        Class38_Sub2_Sub2_Sub1.method388(20, -20);
    }

    public void method7(int i) {
        if (aBoolean1070 || aBoolean865 || aBoolean923)
            return;
        anInt955++;
        if (i <= 0)
            return;
        if (!aBoolean974) {
            method36((byte) -70);
            return;
        } else {
            method103(true);
            return;
        }
    }

    public void method125(byte byte0) {
        if (byte0 != 94)
            method6();
        for (int i = -1; i < anInt823; i++) {
            int j;
            if (i == -1)
                j = anInt821;
            else
                j = anIntArray824[i];
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[j];
            if (class38_sub7_sub3_sub2 != null && class38_sub7_sub3_sub2.anInt1393 > 0) {
                class38_sub7_sub3_sub2.anInt1393--;
                if (class38_sub7_sub3_sub2.anInt1393 == 0)
                    class38_sub7_sub3_sub2.aString1392 = null;
            }
        }

        for (int k = 0; k < anInt928; k++) {
            int l = anIntArray929[k];
            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[l];
            if (class38_sub7_sub3_sub1 != null && class38_sub7_sub3_sub1.anInt1393 > 0) {
                class38_sub7_sub3_sub1.anInt1393--;
                if (class38_sub7_sub3_sub1.anInt1393 == 0)
                    class38_sub7_sub3_sub1.aString1392 = null;
            }
        }

    }

    public int method126(InterfaceComponent interfaceComponent, boolean flag, int i) {
        if (flag)
            aBoolean849 = !aBoolean849;
        if (interfaceComponent.anIntArrayArray278 == null || i >= interfaceComponent.anIntArrayArray278.length)
            return -2;
        try {
            int[] ai = interfaceComponent.anIntArrayArray278[i];
            int j = 0;
            int k = 0;
            do {
                int l;
                do {
                    l = ai[k++];
                    if (l == 0)
                        return j;
                    if (l == 1)
                        j += anIntArray807[ai[k++]];
                    if (l == 2)
                        j += anIntArray926[ai[k++]];
                    if (l == 3)
                        j += anIntArray1079[ai[k++]];
                    if (l == 4) {
                        InterfaceComponent interfaceComponent_1 = InterfaceComponent.interfaceComponentArray[ai[k++]];
                        int k1 = ai[k++] + 1;
                        for (int j2 = 0; j2 < interfaceComponent_1.anIntArray265.length; j2++)
                            if (interfaceComponent_1.anIntArray265[j2] == k1)
                                j += interfaceComponent_1.anIntArray266[j2];

                    }
                    if (l == 5)
                        j += anIntArray938[ai[k++]];
                    if (l == 6)
                        j += anIntArray864[anIntArray926[ai[k++]] - 1];
                    if (l == 7)
                        j += (anIntArray938[ai[k++]] * 100) / 46875;
                    if (l == 8)
                        j += aClass38_Sub7_Sub3_Sub2_967.anInt1511;
                    if (l == 9) {
                        for (int i1 = 0; i1 < 19; i1++) {
                            if (i1 == 18)
                                i1 = 20;
                            j += anIntArray926[i1];
                        }

                    }
                    if (l == 10) {
                        InterfaceComponent interfaceComponent_2 = InterfaceComponent.interfaceComponentArray[ai[k++]];
                        int l1 = ai[k++] + 1;
                        for (int k2 = 0; k2 < interfaceComponent_2.anIntArray265.length; k2++) {
                            if (interfaceComponent_2.anIntArray265[k2] != l1)
                                continue;
                            j += 0x3b9ac9ff;
                            break;
                        }

                    }
                    if (l == 11)
                        j += anInt1072;
                    if (l == 12)
                        j += anInt769;
                } while (l != 13);
                int j1 = anIntArray938[ai[k++]];
                int i2 = ai[k++];
                j += (j1 & 1 << i2) == 0 ? 0 : 1;
            } while (true);
        } catch (Exception _ex) {
            return -1;
        }
    }

    public void method127(boolean flag) {
        Graphics g = method11(aByte1116).getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 789, 532);
        method4(386, 1);
        if (flag)
            method6();
        if (aBoolean865) {
            aBoolean902 = false;
            g.setFont(new Font("Helvetica", 1, 16));
            g.setColor(Color.yellow);
            int i = 35;
            g.drawString("Sorry, an error has occured whilst loading RuneScape", 30, i);
            i += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, i);
            i += 50;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, i);
            i += 30;
            g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, i);
            i += 30;
            g.drawString("3: Try using a different game-world", 30, i);
            i += 30;
            g.drawString("4: Try rebooting your computer", 30, i);
            i += 30;
            g.drawString("5: Try selecting a different version of Java from the play-game menu", 30, i);
        }
        if (aBoolean923) {
            aBoolean902 = false;
            g.setFont(new Font("Helvetica", 1, 20));
            g.setColor(Color.white);
            g.drawString("Error - unable to load game!", 50, 50);
            g.drawString("To play RuneScape make sure you play from", 50, 100);
            g.drawString("http://www.runescape.com", 50, 150);
        }
        if (aBoolean1070) {
            aBoolean902 = false;
            g.setColor(Color.yellow);
            int j = 35;
            g.drawString("Error a copy of RuneScape already appears to be loaded", 30, j);
            j += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, j);
            j += 50;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, j);
            j += 30;
            g.drawString("2: Try rebooting your computer, and reloading", 30, j);
            j += 30;
        }
    }

    public void method128(byte byte0) {
        byte[] abyte0 = fileArchive.read("title.dat", null);
        Class38_Sub2_Sub2_Sub2 class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(abyte0, this);
        drawArea14.init2D();
        class38_sub2_sub2_sub2.method403(34676, 0, 0);
        drawArea15.init2D();
        class38_sub2_sub2_sub2.method403(34676, -661, 0);
        drawArea11.init2D();
        class38_sub2_sub2_sub2.method403(34676, -128, 0);
        drawArea12.init2D();
        class38_sub2_sub2_sub2.method403(34676, -214, -386);
        drawArea13.init2D();
        class38_sub2_sub2_sub2.method403(34676, -214, -186);
        drawArea16.init2D();
        class38_sub2_sub2_sub2.method403(34676, 0, -265);
        drawArea17.init2D();
        class38_sub2_sub2_sub2.method403(34676, -574, -265);
        drawArea18.init2D();
        if (byte0 != 5)
            aBoolean912 = !aBoolean912;
        class38_sub2_sub2_sub2.method403(34676, -128, -186);
        drawArea19.init2D();
        class38_sub2_sub2_sub2.method403(34676, -574, -186);
        int[] ai = new int[class38_sub2_sub2_sub2.anInt1466];
        for (int i = 0; i < class38_sub2_sub2_sub2.anInt1467; i++) {
            for (int j = 0; j < class38_sub2_sub2_sub2.anInt1466; j++)
                ai[j] = class38_sub2_sub2_sub2.anIntArray1465[(class38_sub2_sub2_sub2.anInt1466 - j - 1)
                        + class38_sub2_sub2_sub2.anInt1466 * i];

            for (int k = 0; k < class38_sub2_sub2_sub2.anInt1466; k++)
                class38_sub2_sub2_sub2.anIntArray1465[k + class38_sub2_sub2_sub2.anInt1466 * i] = ai[k];

        }

        drawArea14.init2D();
        class38_sub2_sub2_sub2.method403(34676, 394, 0);
        drawArea15.init2D();
        class38_sub2_sub2_sub2.method403(34676, -267, 0);
        drawArea11.init2D();
        class38_sub2_sub2_sub2.method403(34676, 266, 0);
        drawArea12.init2D();
        class38_sub2_sub2_sub2.method403(34676, 180, -386);
        drawArea13.init2D();
        class38_sub2_sub2_sub2.method403(34676, 180, -186);
        drawArea16.init2D();
        class38_sub2_sub2_sub2.method403(34676, 394, -265);
        drawArea17.init2D();
        class38_sub2_sub2_sub2.method403(34676, -180, -265);
        drawArea18.init2D();
        class38_sub2_sub2_sub2.method403(34676, 212, -186);
        drawArea19.init2D();
        class38_sub2_sub2_sub2.method403(34676, -180, -186);
        class38_sub2_sub2_sub2 = new Class38_Sub2_Sub2_Sub2(fileArchive, "logo", 0);
        drawArea11.init2D();
        class38_sub2_sub2_sub2.method405(18, super.anInt12 / 2 - class38_sub2_sub2_sub2.anInt1466 / 2 - 128, false);
        class38_sub2_sub2_sub2 = null;
        abyte0 = null;
        ai = null;
        System.gc();
    }

    public void method129(int i) {
        i = 30 / i;
        for (Class38_Sub5 class38_sub5 = (Class38_Sub5) linkedList2
                .method270(); class38_sub5 != null; class38_sub5 = (Class38_Sub5) linkedList2.method272()) {
            boolean flag = false;
            class38_sub5.anInt1213 += anInt969;
            if (class38_sub5.anInt1212 == -1) {
                class38_sub5.anInt1212 = 0;
                flag = true;
            }
            while (class38_sub5.anInt1213 > class38_sub5.seqType.anIntArray368[class38_sub5.anInt1212]) {
                class38_sub5.anInt1213 -= class38_sub5.seqType.anIntArray368[class38_sub5.anInt1212] + 1;
                class38_sub5.anInt1212++;
                flag = true;
                if (class38_sub5.anInt1212 < class38_sub5.seqType.anInt365)
                    continue;
                class38_sub5.anInt1212 -= class38_sub5.seqType.anInt369;
                if (class38_sub5.anInt1212 >= 0 && class38_sub5.anInt1212 < class38_sub5.seqType.anInt365)
                    continue;
                class38_sub5.unlink();
                flag = false;
                break;
            }
            if (flag) {
                int j = class38_sub5.anInt1206;
                int k = class38_sub5.anInt1208;
                int l = class38_sub5.anInt1209;
                int i1 = 0;
                if (class38_sub5.anInt1207 == 0)
                    i1 = aClass32_831.method308(j, k, l);
                if (class38_sub5.anInt1207 == 1)
                    i1 = aClass32_831.method309(j, l, 3, k);
                if (class38_sub5.anInt1207 == 2)
                    i1 = aClass32_831.method310(j, k, l);
                if (class38_sub5.anInt1207 == 3)
                    i1 = aClass32_831.method311(j, k, l);
                if (i1 == 0 || (i1 >> 14 & 0x7fff) != class38_sub5.anInt1210) {
                    class38_sub5.unlink();
                } else {
                    int j1 = anIntArrayArrayArray794[j][k][l];
                    int k1 = anIntArrayArrayArray794[j][k + 1][l];
                    int l1 = anIntArrayArrayArray794[j][k + 1][l + 1];
                    int i2 = anIntArrayArrayArray794[j][k][l + 1];
                    LocType locType = LocType.get(class38_sub5.anInt1210);
                    int j2 = -1;
                    if (class38_sub5.anInt1212 != -1)
                        j2 = class38_sub5.seqType.primaryFrames[class38_sub5.anInt1212];
                    if (class38_sub5.anInt1207 == 2) {
                        int k2 = aClass32_831.method312(j, k, l, i1);
                        int j3 = k2 & 0x1f;
                        int i4 = k2 >> 6;
                        if (j3 == 11)
                            j3 = 10;
                        Class38_Sub2_Sub1 class38_sub2_sub1_2 = locType.getModel(j3, i4, j1, k1, l1, i2, j2);
                        aClass32_831.method297(k, class38_sub2_sub1_2, 1, j, l);
                    } else if (class38_sub5.anInt1207 == 1) {
                        Class38_Sub2_Sub1 class38_sub2_sub1 = locType.getModel(4, 0, j1, k1, l1, i2, j2);
                        aClass32_831.method299(266, l, k, class38_sub2_sub1, j);
                    } else if (class38_sub5.anInt1207 == 0) {
                        int l2 = aClass32_831.method312(j, k, l, i1);
                        int k3 = l2 & 0x1f;
                        int j4 = l2 >> 6;
                        if (k3 == 2) {
                            int k4 = j4 + 1 & 3;
                            Class38_Sub2_Sub1 class38_sub2_sub1_4 = locType.getModel(2, 4 + j4, j1, k1, l1, i2, j2);
                            Class38_Sub2_Sub1 class38_sub2_sub1_5 = locType.getModel(2, k4, j1, k1, l1, i2, j2);
                            aClass32_831.method302(class38_sub2_sub1_4, class38_sub2_sub1_5, l, aBoolean849, k, j);
                        } else {
                            Class38_Sub2_Sub1 class38_sub2_sub1_3 = locType.getModel(k3, j4, j1, k1, l1, i2, j2);
                            aClass32_831.method301(35568, class38_sub2_sub1_3, l, k, j);
                        }
                    } else if (class38_sub5.anInt1207 == 3) {
                        int i3 = aClass32_831.method312(j, k, l, i1);
                        int l3 = i3 >> 6;
                        Class38_Sub2_Sub1 class38_sub2_sub1_1 = locType.getModel(22, l3, j1, k1, l1, i2, j2);
                        aClass32_831.method300(class38_sub2_sub1_1, l, -48639, k, j);
                    }
                }
            }
        }

    }

    public void method130(int i, long l) {
        if (i != 1)
            anInt780 = -1;
        if (l == 0L)
            return;
        for (int j = 0; j < anInt793; j++)
            if (aLongArray768[j] == l) {
                anInt793--;
                aBoolean964 = true;
                for (int k = j; k < anInt793; k++)
                    aLongArray768[k] = aLongArray768[k + 1];

                aClass38_Sub2_Sub3_798.method435((byte) -34, 171);
                aClass38_Sub2_Sub3_798.method442(true, l);
                return;
            }

    }

    public void method131(byte byte0) {
        if (anInt1002 == 0 && anInt1025 == 0) {
            aStringArray834[anInt1074] = "Walk here";
            anIntArray1141[anInt1074] = 660;
            anIntArray1139[anInt1074] = super.anInt21;
            anIntArray1140[anInt1074] = super.anInt22;
            anInt1074++;
        }
        int i = -1;
        if (byte0 != 2)
            aBoolean870 = !aBoolean870;
        for (int j = 0; j < Class38_Sub2_Sub1.anInt1298; j++) {
            int k = Class38_Sub2_Sub1.anIntArray1299[j];
            int l = k & 0x7f;
            int i1 = k >> 7 & 0x7f;
            int j1 = k >> 29 & 3;
            int k1 = k >> 14 & 0x7fff;
            if (k != i) {
                i = k;
                if (j1 == 2 && aClass32_831.method312(anInt880, l, i1, k) >= 0) {
                    LocType locType = LocType.get(k1);
                    if (anInt1002 == 1) {
                        aStringArray834[anInt1074] = "Use " + aString1006 + " with @cya@" + locType.name;
                        anIntArray1141[anInt1074] = 450;
                        anIntArray1142[anInt1074] = k;
                        anIntArray1139[anInt1074] = l;
                        anIntArray1140[anInt1074] = i1;
                        anInt1074++;
                    } else if (anInt1025 == 1) {
                        if ((anInt1027 & 4) == 4) {
                            aStringArray834[anInt1074] = aString1028 + " @cya@" + locType.name;
                            anIntArray1141[anInt1074] = 55;
                            anIntArray1142[anInt1074] = k;
                            anIntArray1139[anInt1074] = l;
                            anIntArray1140[anInt1074] = i1;
                            anInt1074++;
                        }
                    } else {
                        if (locType.actions != null) {
                            for (int l1 = 4; l1 >= 0; l1--)
                                if (locType.actions[l1] != null) {
                                    aStringArray834[anInt1074] = locType.actions[l1] + " @cya@"
                                            + locType.name;
                                    if (l1 == 0)
                                        anIntArray1141[anInt1074] = 285;
                                    if (l1 == 1)
                                        anIntArray1141[anInt1074] = 504;
                                    if (l1 == 2)
                                        anIntArray1141[anInt1074] = 364;
                                    if (l1 == 3)
                                        anIntArray1141[anInt1074] = 581;
                                    if (l1 == 4)
                                        anIntArray1141[anInt1074] = 1501;
                                    anIntArray1142[anInt1074] = k;
                                    anIntArray1139[anInt1074] = l;
                                    anIntArray1140[anInt1074] = i1;
                                    anInt1074++;
                                }

                        }
                        aStringArray834[anInt1074] = "Examine @cya@" + locType.name;
                        anIntArray1141[anInt1074] = 1175;
                        anIntArray1142[anInt1074] = k;
                        anIntArray1139[anInt1074] = l;
                        anIntArray1140[anInt1074] = i1;
                        anInt1074++;
                    }
                }
                if (j1 == 1) {
                    Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[k1];
                    if (class38_sub7_sub3_sub1.npcType.aByte85 == 1
                            && (class38_sub7_sub3_sub1.anInt1380 & 0x7f) == 64
                            && (class38_sub7_sub3_sub1.anInt1381 & 0x7f) == 64) {
                        for (int i2 = 0; i2 < anInt928; i2++) {
                            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1_1 = aClass38_Sub7_Sub3_Sub1Array927[anIntArray929[i2]];
                            if (class38_sub7_sub3_sub1_1 != null && class38_sub7_sub3_sub1_1 != class38_sub7_sub3_sub1
                                    && class38_sub7_sub3_sub1_1.npcType.aByte85 == 1
                                    && class38_sub7_sub3_sub1_1.anInt1380 == class38_sub7_sub3_sub1.anInt1380
                                    && class38_sub7_sub3_sub1_1.anInt1381 == class38_sub7_sub3_sub1.anInt1381)
                                method34(class38_sub7_sub3_sub1_1.npcType, -641, i1, l, anIntArray929[i2]);
                        }

                    }
                    method34(class38_sub7_sub3_sub1.npcType, -641, i1, l, k1);
                }
                if (j1 == 0) {
                    Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[k1];
                    if ((class38_sub7_sub3_sub2.anInt1380 & 0x7f) == 64
                            && (class38_sub7_sub3_sub2.anInt1381 & 0x7f) == 64) {
                        for (int j2 = 0; j2 < anInt928; j2++) {
                            Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1_2 = aClass38_Sub7_Sub3_Sub1Array927[anIntArray929[j2]];
                            if (class38_sub7_sub3_sub1_2 != null && class38_sub7_sub3_sub1_2.npcType.aByte85 == 1
                                    && class38_sub7_sub3_sub1_2.anInt1380 == class38_sub7_sub3_sub2.anInt1380
                                    && class38_sub7_sub3_sub1_2.anInt1381 == class38_sub7_sub3_sub2.anInt1381)
                                method34(class38_sub7_sub3_sub1_2.npcType, -641, i1, l, anIntArray929[j2]);
                        }

                        for (int k2 = 0; k2 < anInt823; k2++) {
                            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2_1 = aClass38_Sub7_Sub3_Sub2Array822[anIntArray824[k2]];
                            if (class38_sub7_sub3_sub2_1 != null && class38_sub7_sub3_sub2_1 != class38_sub7_sub3_sub2
                                    && class38_sub7_sub3_sub2_1.anInt1380 == class38_sub7_sub3_sub2.anInt1380
                                    && class38_sub7_sub3_sub2_1.anInt1381 == class38_sub7_sub3_sub2.anInt1381)
                                method102(false, i1, anIntArray824[k2], class38_sub7_sub3_sub2_1, l);
                        }

                    }
                    method102(false, i1, k1, class38_sub7_sub3_sub2, l);
                }
                if (j1 == 3) {
                    LinkedList linkedList = linkedList3dArray[anInt880][l][i1];
                    if (linkedList != null) {
                        for (ObjStackEntity objStackEntity = (ObjStackEntity) linkedList
                                .method271(); objStackEntity != null; objStackEntity = (ObjStackEntity) linkedList
                                .method273()) {
                            ObjType objType = ObjType.method169(objStackEntity.model);
                            if (anInt1002 == 1) {
                                aStringArray834[anInt1074] = "Use " + aString1006 + " with @lre@" + objType.aString145;
                                anIntArray1141[anInt1074] = 217;
                                anIntArray1142[anInt1074] = objStackEntity.model;
                                anIntArray1139[anInt1074] = l;
                                anIntArray1140[anInt1074] = i1;
                                anInt1074++;
                            } else if (anInt1025 == 1) {
                                if ((anInt1027 & 1) == 1) {
                                    aStringArray834[anInt1074] = aString1028 + " @lre@" + objType.aString145;
                                    anIntArray1141[anInt1074] = 965;
                                    anIntArray1142[anInt1074] = objStackEntity.model;
                                    anIntArray1139[anInt1074] = l;
                                    anIntArray1140[anInt1074] = i1;
                                    anInt1074++;
                                }
                            } else {
                                for (int l2 = 4; l2 >= 0; l2--)
                                    if (objType.aStringArray160 != null && objType.aStringArray160[l2] != null) {
                                        aStringArray834[anInt1074] = objType.aStringArray160[l2] + " @lre@"
                                                + objType.aString145;
                                        if (l2 == 0)
                                            anIntArray1141[anInt1074] = 224;
                                        if (l2 == 1)
                                            anIntArray1141[anInt1074] = 993;
                                        if (l2 == 2)
                                            anIntArray1141[anInt1074] = 99;
                                        if (l2 == 3)
                                            anIntArray1141[anInt1074] = 746;
                                        if (l2 == 4)
                                            anIntArray1141[anInt1074] = 877;
                                        anIntArray1142[anInt1074] = objStackEntity.model;
                                        anIntArray1139[anInt1074] = l;
                                        anIntArray1140[anInt1074] = i1;
                                        anInt1074++;
                                    } else if (l2 == 2) {
                                        aStringArray834[anInt1074] = "Take @lre@" + objType.aString145;
                                        anIntArray1141[anInt1074] = 99;
                                        anIntArray1142[anInt1074] = objStackEntity.model;
                                        anIntArray1139[anInt1074] = l;
                                        anIntArray1140[anInt1074] = i1;
                                        anInt1074++;
                                    }

                                aStringArray834[anInt1074] = "Examine @lre@" + objType.aString145;
                                anIntArray1141[anInt1074] = 1102;
                                anIntArray1142[anInt1074] = objStackEntity.model;
                                anIntArray1139[anInt1074] = l;
                                anIntArray1140[anInt1074] = i1;
                                anInt1074++;
                            }
                        }

                    }
                }
            }
        }

    }

    public void method132(boolean flag) {
        aBoolean974 &= flag;
        for (int i = -1; i < anInt823; i++) {
            int j;
            if (i == -1)
                j = anInt821;
            else
                j = anIntArray824[i];
            Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[j];
            if (class38_sub7_sub3_sub2 != null)
                method63(class38_sub7_sub3_sub2, (byte) -128, 1);
        }

        anInt913++;
        if (anInt913 > 1406) {
            anInt913 = 0;
            aClass38_Sub2_Sub3_798.method435((byte) -34, 219);
            aClass38_Sub2_Sub3_798.method436(0);
            int k = aClass38_Sub2_Sub3_798.offset;
            aClass38_Sub2_Sub3_798.method436(162);
            aClass38_Sub2_Sub3_798.method436(22);
            if ((int) (Math.random() * 2D) == 0)
                aClass38_Sub2_Sub3_798.method436(84);
            aClass38_Sub2_Sub3_798.method437(31824);
            aClass38_Sub2_Sub3_798.method437(13490);
            if ((int) (Math.random() * 2D) == 0)
                aClass38_Sub2_Sub3_798.method436(123);
            if ((int) (Math.random() * 2D) == 0)
                aClass38_Sub2_Sub3_798.method436(134);
            aClass38_Sub2_Sub3_798.method436(100);
            aClass38_Sub2_Sub3_798.method436(94);
            aClass38_Sub2_Sub3_798.method437(35521);
            aClass38_Sub2_Sub3_798.method445(0, aClass38_Sub2_Sub3_798.offset - k);
        }
    }

    public void method133(byte byte0) {
        if (anInt911 != 2)
            return;
        method91((anInt746 - anInt762 << 7) + anInt749, (anInt745 - anInt761 << 7) + anInt748, anInt1105, anInt747 * 2);
        if (byte0 != -11)
            aBoolean1147 = !aBoolean1147;
        if (anInt1019 > -1 && anInt955 % 20 < 10)
            aClass38_Sub2_Sub2_Sub2Array956[2].method405(anInt1020 - 28, anInt1019 - 12, false);
    }

    public void method134(int i, int j, Class38_Sub2_Sub3 class38_sub2_sub3) {
        class38_sub2_sub3.method456(223);
        int k = class38_sub2_sub3.method457(9, 1);
        if (i != 11522)
            linkedList3dArray = null;
        if (k == 0)
            return;
        int l = class38_sub2_sub3.method457(9, 2);
        if (l == 0) {
            anIntArray826[anInt825++] = anInt821;
            return;
        }
        if (l == 1) {
            int i1 = class38_sub2_sub3.method457(9, 3);
            aClass38_Sub7_Sub3_Sub2_967.method467(false, i1, (byte) 6);
            int l1 = class38_sub2_sub3.method457(9, 1);
            if (l1 == 1)
                anIntArray826[anInt825++] = anInt821;
            return;
        }
        if (l == 2) {
            int j1 = class38_sub2_sub3.method457(9, 3);
            aClass38_Sub7_Sub3_Sub2_967.method467(true, j1, (byte) 6);
            int i2 = class38_sub2_sub3.method457(9, 3);
            aClass38_Sub7_Sub3_Sub2_967.method467(true, i2, (byte) 6);
            int k2 = class38_sub2_sub3.method457(9, 1);
            if (k2 == 1)
                anIntArray826[anInt825++] = anInt821;
            return;
        }
        if (l == 3) {
            anInt880 = class38_sub2_sub3.method457(9, 2);
            int k1 = class38_sub2_sub3.method457(9, 7);
            int j2 = class38_sub2_sub3.method457(9, 7);
            int l2 = class38_sub2_sub3.method457(9, 1);
            aClass38_Sub7_Sub3_Sub2_967.method466(false, l2 == 1, k1, j2);
            int i3 = class38_sub2_sub3.method457(9, 1);
            if (i3 == 1)
                anIntArray826[anInt825++] = anInt821;
        }
    }

    public void method135(boolean flag) {
        if (flag)
            anInt780 = -1;
        drawArea23.init2D();
        Class38_Sub2_Sub2_Sub1.anIntArray1448 = anIntArray735;
        aClass38_Sub2_Sub2_Sub3_983.method419(0, 0, false);
        if (aBoolean869) {
            aClass38_Sub2_Sub2_Sub4_987.method421(40, (byte) 6, 0, aString775, 239);
            aClass38_Sub2_Sub2_Sub4_987.method421(60, (byte) 6, 128, aString765 + "*", 239);
        } else if (aBoolean1055) {
            aClass38_Sub2_Sub2_Sub4_987.method421(40, (byte) 6, 0, "Enter amount:", 239);
            aClass38_Sub2_Sub2_Sub4_987.method421(60, (byte) 6, 128, aString784 + "*", 239);
        } else if (aString936 != null) {
            aClass38_Sub2_Sub2_Sub4_987.method421(40, (byte) 6, 0, aString936, 239);
            aClass38_Sub2_Sub2_Sub4_987.method421(60, (byte) 6, 128, "Click to continue", 239);
        } else if (anInt1001 != -1)
            method59(0, 0, 38682, InterfaceComponent.interfaceComponentArray[anInt1001], 0);
        else if (anInt1021 != -1) {
            method59(0, 0, 38682, InterfaceComponent.interfaceComponentArray[anInt1021], 0);
        } else {
            Class38_Sub2_Sub2_Sub4 class38_sub2_sub2_sub4 = aClass38_Sub2_Sub2_Sub4_986;
            int i = 0;
            Class38_Sub2_Sub2.method378(77, 0, 463, 789, 0);
            for (int j = 0; j < 100; j++)
                if (aStringArray898[j] != null) {
                    int k = anIntArray896[j];
                    int l = (70 - i * 14) + anInt977;
                    if (k == 0) {
                        if (l > 0 && l < 110)
                            class38_sub2_sub2_sub4.method424(4, l, false, 0, aStringArray898[j]);
                        i++;
                    }
                    if (k == 1) {
                        if (l > 0 && l < 110) {
                            class38_sub2_sub2_sub4.method424(4, l, false, 0xffffff, aStringArray897[j] + ":");
                            class38_sub2_sub2_sub4.method424(
                                    12 + class38_sub2_sub2_sub4.method423(false, aStringArray897[j]), l, false, 255,
                                    aStringArray898[j]);
                        }
                        i++;
                    }
                    if (k == 2 && (anInt976 == 0 || anInt976 == 1 && method138(-20, aStringArray897[j]))) {
                        if (l > 0 && l < 110) {
                            class38_sub2_sub2_sub4.method424(4, l, false, 0, aStringArray897[j] + ":");
                            class38_sub2_sub2_sub4.method424(
                                    12 + class38_sub2_sub2_sub4.method423(false, aStringArray897[j]), l, false, 255,
                                    aStringArray898[j]);
                        }
                        i++;
                    }
                    if ((k == 3 || k == 7) && anInt833 == 0
                            && (k == 7 || anInt755 == 0 || anInt755 == 1 && method138(-20, aStringArray897[j]))) {
                        if (l > 0 && l < 110) {
                            class38_sub2_sub2_sub4.method424(4, l, false, 0, "From " + aStringArray897[j] + ":");
                            class38_sub2_sub2_sub4.method424(
                                    12 + class38_sub2_sub2_sub4.method423(false, "From " + aStringArray897[j]), l,
                                    false, 0x800000, aStringArray898[j]);
                        }
                        i++;
                    }
                    if (k == 4 && (anInt885 == 0 || anInt885 == 1 && method138(-20, aStringArray897[j]))) {
                        if (l > 0 && l < 110)
                            class38_sub2_sub2_sub4.method424(4, l, false, 0x800080,
                                    aStringArray897[j] + " " + aStringArray898[j]);
                        i++;
                    }
                    if (k == 5 && anInt833 == 0 && anInt755 < 2) {
                        if (l > 0 && l < 110)
                            class38_sub2_sub2_sub4.method424(4, l, false, 0x800000, aStringArray898[j]);
                        i++;
                    }
                    if (k == 6 && anInt833 == 0 && anInt755 < 2) {
                        if (l > 0 && l < 110) {
                            class38_sub2_sub2_sub4.method424(4, l, false, 0, "To " + aStringArray897[j] + ":");
                            class38_sub2_sub2_sub4.method424(
                                    12 + class38_sub2_sub2_sub4.method423(false, "To " + aStringArray897[j]), l, false,
                                    0x800000, aStringArray898[j]);
                        }
                        i++;
                    }
                    if (k == 8 && (anInt885 == 0 || anInt885 == 1 && method138(-20, aStringArray897[j]))) {
                        if (l > 0 && l < 110)
                            class38_sub2_sub2_sub4.method424(4, l, false, 0xcbb789,
                                    aStringArray897[j] + " " + aStringArray898[j]);
                        i++;
                    }
                }

            Class38_Sub2_Sub2.method377(0);
            anInt792 = i * 14 + 7;
            if (anInt792 < 78)
                anInt792 = 78;
            method50(anInt803, 463, 0, anInt792 - anInt977 - 77, anInt792, 77);
            class38_sub2_sub2_sub4.method424(4, 90, false, 0, StringUtils.formatName(aString1066) + ":");
            class38_sub2_sub2_sub4.method424(6 + class38_sub2_sub2_sub4.method423(false, aString1066 + ": "), 90, false,
                    255, aString1137 + "*");
            Class38_Sub2_Sub2.method382(0, 0, 77, 479, 0);
        }
        if (aBoolean879 && anInt1148 == 2)
            method74(-961);
        drawArea23.drawImage(375, super.aGraphics14, 22);
        drawArea22.init2D();
        Class38_Sub2_Sub2_Sub1.anIntArray1448 = anIntArray737;
    }

    public boolean method136(boolean flag) {
        if (flag)
            linkedList3dArray = null;
        if (bufferedStream == null)
            return false;
        try {
            int i = bufferedStream.available();
            if (i == 0)
                return false;
            if (anInt780 == -1) {
                bufferedStream.read(aClass38_Sub2_Sub3_795.aByteArray1328, 0, 1);
                anInt780 = aClass38_Sub2_Sub3_795.aByteArray1328[0] & 0xff;
                if (isaacState != null)
                    anInt780 = anInt780 - isaacState.nextInt() & 0xff;
                anInt779 = Packet.packetLengths[anInt780];
                i--;
            }
            if (anInt779 == -1)
                if (i > 0) {
                    bufferedStream.read(aClass38_Sub2_Sub3_795.aByteArray1328, 0, 1);
                    anInt779 = aClass38_Sub2_Sub3_795.aByteArray1328[0] & 0xff;
                    i--;
                } else {
                    return false;
                }
            if (anInt779 == -2)
                if (i > 1) {
                    bufferedStream.read(aClass38_Sub2_Sub3_795.aByteArray1328, 0, 2);
                    aClass38_Sub2_Sub3_795.offset = 0;
                    anInt779 = aClass38_Sub2_Sub3_795.method448();
                    i -= 2;
                } else {
                    return false;
                }
            if (i < anInt779)
                return false;
            aClass38_Sub2_Sub3_795.offset = 0;
            bufferedStream.read(aClass38_Sub2_Sub3_795.aByteArray1328, 0, anInt779);
            anInt781 = 0;
            anInt830 = anInt829;
            anInt829 = anInt828;
            anInt828 = anInt780;
            if (anInt780 == 150) {
                int j = aClass38_Sub2_Sub3_795.method448();
                byte byte0 = aClass38_Sub2_Sub3_795.method447();
                anIntArray1075[j] = byte0;
                if (anIntArray938[j] != byte0) {
                    anIntArray938[j] = byte0;
                    method61(j, 49);
                    aBoolean964 = true;
                    if (anInt1021 != -1)
                        aBoolean965 = true;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 152) {
                long l = aClass38_Sub2_Sub3_795.method452(603);
                int j16 = aClass38_Sub2_Sub3_795.method446();
                String s6 = StringUtils.formatName(StringUtils.fromBase37(l));
                for (int l24 = 0; l24 < anInt1089; l24++) {
                    if (l != aLongArray943[l24])
                        continue;
                    if (anIntArray773[l24] != j16) {
                        anIntArray773[l24] = j16;
                        aBoolean964 = true;
                        if (j16 > 0)
                            method111(5, s6 + " has logged in.", (byte) 4, "");
                        if (j16 == 0)
                            method111(5, s6 + " has logged out.", (byte) 4, "");
                    }
                    s6 = null;
                    break;
                }

                if (s6 != null && anInt1089 < 100) {
                    aLongArray943[anInt1089] = l;
                    aStringArray1127[anInt1089] = s6;
                    anIntArray773[anInt1089] = j16;
                    anInt1089++;
                    aBoolean964 = true;
                }
                for (boolean flag5 = false; !flag5; ) {
                    flag5 = true;
                    for (int i29 = 0; i29 < anInt1089 - 1; i29++)
                        if (anIntArray773[i29] != anInt886 && anIntArray773[i29 + 1] == anInt886
                                || anIntArray773[i29] == 0 && anIntArray773[i29 + 1] != 0) {
                            int j30 = anIntArray773[i29];
                            anIntArray773[i29] = anIntArray773[i29 + 1];
                            anIntArray773[i29 + 1] = j30;
                            String s8 = aStringArray1127[i29];
                            aStringArray1127[i29] = aStringArray1127[i29 + 1];
                            aStringArray1127[i29 + 1] = s8;
                            long l31 = aLongArray943[i29];
                            aLongArray943[i29] = aLongArray943[i29 + 1];
                            aLongArray943[i29 + 1] = l31;
                            aBoolean964 = true;
                            flag5 = false;
                        }

                }

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 43) {
                anInt957 = aClass38_Sub2_Sub3_795.method448() * 30;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 80) {
                int k = aClass38_Sub2_Sub3_795.method446();
                int i10 = aClass38_Sub2_Sub3_795.method446();
                int k16 = -1;
                for (int k21 = 0; k21 < anIntArray925.length; k21++)
                    if (anIntArray925[k21] == (k << 8) + i10)
                        k16 = k21;

                if (k16 != -1) {
                    signlink.cachesave("m" + k + "_" + i10, aByteArrayArray770[k16]);
                    anInt1078 = 1;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 1) {
                method47(aClass38_Sub2_Sub3_795, anInt779, false);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 237) {
                int i1 = aClass38_Sub2_Sub3_795.method448();
                int j10 = aClass38_Sub2_Sub3_795.method448();
                if (anInt838 == i1 && anInt839 == j10 && anInt1078 != 0) {
                    anInt780 = -1;
                    return true;
                }
                anInt838 = i1;
                anInt839 = j10;
                anInt761 = (anInt838 - 6) * 8;
                anInt762 = (anInt839 - 6) * 8;
                anInt1078 = 1;
                drawArea22.init2D();
                aClass38_Sub2_Sub2_Sub4_986.method421(151, (byte) 6, 0, "Loading - please wait.", 257);
                aClass38_Sub2_Sub2_Sub4_986.method421(150, (byte) 6, 0xffffff, "Loading - please wait.", 256);
                drawArea22.drawImage(11, super.aGraphics14, 8);
                signlink.looprate(5);
                int l16 = (anInt779 - 2) / 10;
                aByteArrayArray770 = new byte[l16][];
                aByteArrayArray1000 = new byte[l16][];
                anIntArray925 = new int[l16];
                aClass38_Sub2_Sub3_798.method435((byte) -34, 150);
                aClass38_Sub2_Sub3_798.method436(0);
                int i22 = 0;
                for (int i25 = 0; i25 < l16; i25++) {
                    int i27 = aClass38_Sub2_Sub3_795.method446();
                    int j29 = aClass38_Sub2_Sub3_795.method446();
                    int k30 = aClass38_Sub2_Sub3_795.method451();
                    int i31 = aClass38_Sub2_Sub3_795.method451();
                    anIntArray925[i25] = (i27 << 8) + j29;
                    if (k30 != 0) {
                        byte[] abyte1 = signlink.cacheload("m" + i27 + "_" + j29);
                        if (abyte1 != null) {
                            aCRC32_996.reset();
                            aCRC32_996.update(abyte1);
                            if ((int) aCRC32_996.getValue() != k30)
                                abyte1 = null;
                        }
                        if (abyte1 != null) {
                            aByteArrayArray770[i25] = abyte1;
                        } else {
                            anInt1078 = 0;
                            aClass38_Sub2_Sub3_798.method436(0);
                            aClass38_Sub2_Sub3_798.method436(i27);
                            aClass38_Sub2_Sub3_798.method436(j29);
                            i22 += 3;
                        }
                    }
                    if (i31 != 0) {
                        byte[] abyte2 = signlink.cacheload("l" + i27 + "_" + j29);
                        if (abyte2 != null) {
                            aCRC32_996.reset();
                            aCRC32_996.update(abyte2);
                            if ((int) aCRC32_996.getValue() != i31)
                                abyte2 = null;
                        }
                        if (abyte2 != null) {
                            aByteArrayArray1000[i25] = abyte2;
                        } else {
                            anInt1078 = 0;
                            aClass38_Sub2_Sub3_798.method436(1);
                            aClass38_Sub2_Sub3_798.method436(i27);
                            aClass38_Sub2_Sub3_798.method436(j29);
                            i22 += 3;
                        }
                    }
                }

                aClass38_Sub2_Sub3_798.method445(0, i22);
                signlink.looprate(50);
                drawArea22.init2D();
                if (anInt1078 == 0) {
                    aClass38_Sub2_Sub2_Sub4_986.method421(166, (byte) 6, 0,
                            "Map area updated since last visit, so load will take longer this time only", 257);
                    aClass38_Sub2_Sub2_Sub4_986.method421(165, (byte) 6, 0xffffff,
                            "Map area updated since last visit, so load will take longer this time only", 256);
                }
                drawArea22.drawImage(11, super.aGraphics14, 8);
                int j27 = anInt761 - anInt763;
                int k29 = anInt762 - anInt764;
                anInt763 = anInt761;
                anInt764 = anInt762;
                for (int l30 = 0; l30 < 8192; l30++) {
                    Class38_Sub7_Sub3_Sub1 class38_sub7_sub3_sub1 = aClass38_Sub7_Sub3_Sub1Array927[l30];
                    if (class38_sub7_sub3_sub1 != null) {
                        for (int k31 = 0; k31 < 10; k31++) {
                            class38_sub7_sub3_sub1.anIntArray1428[k31] -= j27;
                            class38_sub7_sub3_sub1.anIntArray1429[k31] -= k29;
                        }

                        class38_sub7_sub3_sub1.anInt1380 -= j27 * 128;
                        class38_sub7_sub3_sub1.anInt1381 -= k29 * 128;
                    }
                }

                for (int j31 = 0; j31 < anInt820; j31++) {
                    Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2 = aClass38_Sub7_Sub3_Sub2Array822[j31];
                    if (class38_sub7_sub3_sub2 != null) {
                        for (int i32 = 0; i32 < 10; i32++) {
                            class38_sub7_sub3_sub2.anIntArray1428[i32] -= j27;
                            class38_sub7_sub3_sub2.anIntArray1429[i32] -= k29;
                        }

                        class38_sub7_sub3_sub2.anInt1380 -= j27 * 128;
                        class38_sub7_sub3_sub2.anInt1381 -= k29 * 128;
                    }
                }

                byte byte1 = 0;
                byte byte2 = 104;
                byte byte3 = 1;
                if (j27 < 0) {
                    byte1 = 103;
                    byte2 = -1;
                    byte3 = -1;
                }
                byte byte4 = 0;
                byte byte5 = 104;
                byte byte6 = 1;
                if (k29 < 0) {
                    byte4 = 103;
                    byte5 = -1;
                    byte6 = -1;
                }
                for (int j32 = byte1; j32 != byte2; j32 += byte3) {
                    for (int k32 = byte4; k32 != byte5; k32 += byte6) {
                        int l32 = j32 + j27;
                        int i33 = k32 + k29;
                        for (int j33 = 0; j33 < 4; j33++)
                            if (l32 >= 0 && i33 >= 0 && l32 < 104 && i33 < 104)
                                linkedList3dArray[j33][j32][k32] = linkedList3dArray[j33][l32][i33];
                            else
                                linkedList3dArray[j33][j32][k32] = null;

                    }

                }

                for (SpawnedLoc spawnedLoc_1 = (SpawnedLoc) linkedList5
                        .method270(); spawnedLoc_1 != null; spawnedLoc_1 = (SpawnedLoc) linkedList5
                        .method272()) {
                    spawnedLoc_1.tileX -= j27;
                    spawnedLoc_1.tileZ -= k29;
                    if (spawnedLoc_1.tileX < 0 || spawnedLoc_1.tileZ < 0 || spawnedLoc_1.tileX >= 104
                            || spawnedLoc_1.tileZ >= 104)
                        spawnedLoc_1.unlink();
                }

                if (anInt1051 != 0) {
                    anInt1051 -= j27;
                    anInt1052 -= k29;
                }
                aBoolean968 = false;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 197) {
                int j1 = aClass38_Sub2_Sub3_795.method448();
                InterfaceComponent.interfaceComponentArray[j1].aClass38_Sub2_Sub1_310 = aClass38_Sub7_Sub3_Sub2_967.method472(-718);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 25) {
                anInt911 = aClass38_Sub2_Sub3_795.method446();
                if (anInt911 == 1)
                    anInt801 = aClass38_Sub2_Sub3_795.method448();
                if (anInt911 >= 2 && anInt911 <= 6) {
                    if (anInt911 == 2) {
                        anInt748 = 64;
                        anInt749 = 64;
                    }
                    if (anInt911 == 3) {
                        anInt748 = 0;
                        anInt749 = 64;
                    }
                    if (anInt911 == 4) {
                        anInt748 = 128;
                        anInt749 = 64;
                    }
                    if (anInt911 == 5) {
                        anInt748 = 64;
                        anInt749 = 0;
                    }
                    if (anInt911 == 6) {
                        anInt748 = 64;
                        anInt749 = 128;
                    }
                    anInt911 = 2;
                    anInt745 = aClass38_Sub2_Sub3_795.method448();
                    anInt746 = aClass38_Sub2_Sub3_795.method448();
                    anInt747 = aClass38_Sub2_Sub3_795.method446();
                }
                if (anInt911 == 10)
                    anInt1076 = aClass38_Sub2_Sub3_795.method448();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 54) {
                String s = aClass38_Sub2_Sub3_795.method453();
                int k10 = aClass38_Sub2_Sub3_795.method451();
                int i17 = aClass38_Sub2_Sub3_795.method451();
                if (!s.equals(aString1119) && aBoolean835 && !aBoolean889)
                    method14(false, k10, s, i17);
                aString1119 = s;
                anInt1110 = k10;
                anInt1155 = i17;
                anInt744 = 0;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 142) {
                method58(-780);
                anInt780 = -1;
                return false;
            }
            if (anInt780 == 20) {
                int k1 = aClass38_Sub2_Sub3_795.method446();
                int l10 = aClass38_Sub2_Sub3_795.method446();
                int j17 = -1;
                for (int j22 = 0; j22 < anIntArray925.length; j22++)
                    if (anIntArray925[j22] == (k1 << 8) + l10)
                        j17 = j22;

                if (j17 != -1) {
                    signlink.cachesave("l" + k1 + "_" + l10, aByteArrayArray1000[j17]);
                    anInt1078 = 1;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 19) {
                anInt1051 = 0;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 139) {
                anInt734 = aClass38_Sub2_Sub3_795.method448();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 151 || anInt780 == 23 || anInt780 == 50 || anInt780 == 191 || anInt780 == 69
                    || anInt780 == 49 || anInt780 == 223 || anInt780 == 42 || anInt780 == 76 || anInt780 == 59) {
                method22((byte) -45, aClass38_Sub2_Sub3_795, anInt780);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 28) {
                int l1 = aClass38_Sub2_Sub3_795.method448();
                int i11 = aClass38_Sub2_Sub3_795.method448();
                if (anInt1001 != -1) {
                    anInt1001 = -1;
                    aBoolean965 = true;
                }
                if (aBoolean1055) {
                    aBoolean1055 = false;
                    aBoolean965 = true;
                }
                anInt971 = l1;
                anInt1129 = i11;
                aBoolean964 = true;
                aBoolean1080 = true;
                aBoolean872 = false;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 175) {
                int i2 = aClass38_Sub2_Sub3_795.method448();
                int j11 = aClass38_Sub2_Sub3_795.method451();
                anIntArray1075[i2] = j11;
                if (anIntArray938[i2] != j11) {
                    anIntArray938[i2] = j11;
                    method61(i2, 49);
                    aBoolean964 = true;
                    if (anInt1021 != -1)
                        aBoolean965 = true;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 146) {
                int j2 = aClass38_Sub2_Sub3_795.method448();
                int k11 = aClass38_Sub2_Sub3_795.method448();
                InterfaceComponent.interfaceComponentArray[j2].anInt312 = k11;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 167) {
                int k2 = aClass38_Sub2_Sub3_795.method448();
                int l11 = aClass38_Sub2_Sub3_795.method446();
                if (k2 == 65535)
                    k2 = -1;
                anIntArray861[l11] = k2;
                aBoolean964 = true;
                aBoolean1080 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 220) {
                int l2 = aClass38_Sub2_Sub3_795.method446();
                int i12 = aClass38_Sub2_Sub3_795.method446();
                int k17 = aClass38_Sub2_Sub3_795.method448();
                int k22 = aClass38_Sub2_Sub3_795.method448();
                int j25 = -1;
                for (int k27 = 0; k27 < anIntArray925.length; k27++)
                    if (anIntArray925[k27] == (l2 << 8) + i12)
                        j25 = k27;

                if (j25 != -1) {
                    if (aByteArrayArray1000[j25] == null || aByteArrayArray1000[j25].length != k22)
                        aByteArrayArray1000[j25] = new byte[k22];
                    aClass38_Sub2_Sub3_795.method455(anInt779 - 6, -110, k17, aByteArrayArray1000[j25]);
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 133) {
                Class38_Sub2_Sub3 class38_sub2_sub3 = Class7.method185(854);
                if (class38_sub2_sub3 != null) {
                    aClass38_Sub2_Sub3_798.method435((byte) -34, 81);
                    aClass38_Sub2_Sub3_798.method437(class38_sub2_sub3.offset);
                    aClass38_Sub2_Sub3_798.method444(class38_sub2_sub3.aByteArray1328, class38_sub2_sub3.offset, 0,
                            (byte) -106);
                    class38_sub2_sub3.method434((byte) 8);
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 98) {
                aBoolean964 = true;
                int i3 = aClass38_Sub2_Sub3_795.method448();
                InterfaceComponent interfaceComponent = InterfaceComponent.interfaceComponentArray[i3];
                int l17 = aClass38_Sub2_Sub3_795.method446();
                for (int l22 = 0; l22 < l17; l22++) {
                    interfaceComponent.anIntArray265[l22] = aClass38_Sub2_Sub3_795.method448();
                    int k25 = aClass38_Sub2_Sub3_795.method446();
                    if (k25 == 255)
                        k25 = aClass38_Sub2_Sub3_795.method451();
                    interfaceComponent.anIntArray266[l22] = k25;
                }

                for (int l25 = l17; l25 < interfaceComponent.anIntArray265.length; l25++) {
                    interfaceComponent.anIntArray265[l25] = 0;
                    interfaceComponent.anIntArray266[l25] = 0;
                }

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 226) {
                Class7.method182(-31717);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 243) {
                aBoolean869 = false;
                aBoolean1055 = true;
                aString784 = "";
                aBoolean965 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 15) {
                int j3 = aClass38_Sub2_Sub3_795.method448();
                InterfaceComponent interfaceComponent_1 = InterfaceComponent.interfaceComponentArray[j3];
                for (int i18 = 0; i18 < interfaceComponent_1.anIntArray265.length; i18++) {
                    interfaceComponent_1.anIntArray265[i18] = -1;
                    interfaceComponent_1.anIntArray265[i18] = 0;
                }

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 140) {
                anInt1061 = aClass38_Sub2_Sub3_795.method451();
                anInt873 = aClass38_Sub2_Sub3_795.method448();
                anInt901 = aClass38_Sub2_Sub3_795.method446();
                anInt1054 = aClass38_Sub2_Sub3_795.method448();
                if (anInt1061 != 0 && anInt971 == -1) {
                    signlink.dnslookup(StringUtils.fromIPv4(anInt1061));
                    method16((byte) -60);
                    char c = '\u028A';
                    if (anInt901 != 201)
                        c = '\u028F';
                    aString970 = "";
                    aBoolean881 = false;
                    for (int j12 = 0; j12 < InterfaceComponent.interfaceComponentArray.length; j12++) {
                        if (InterfaceComponent.interfaceComponentArray[j12] == null || InterfaceComponent.interfaceComponentArray[j12].anInt273 != c)
                            continue;
                        anInt971 = InterfaceComponent.interfaceComponentArray[j12].anInt270;
                        break;
                    }

                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 126) {
                anInt1128 = aClass38_Sub2_Sub3_795.method446();
                if (anInt1128 == anInt757) {
                    if (anInt1128 == 3)
                        anInt757 = 1;
                    else
                        anInt757 = 3;
                    aBoolean964 = true;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 212) {
                if (aBoolean835 && !aBoolean889) {
                    int k3 = aClass38_Sub2_Sub3_795.method448();
                    int k12 = aClass38_Sub2_Sub3_795.method451();
                    int j18 = anInt779 - 6;
                    byte[] abyte0 = new byte[k12];
                    BZip2InputStream.read(abyte0, k12, aClass38_Sub2_Sub3_795.aByteArray1328, j18,
                            aClass38_Sub2_Sub3_795.offset);
                    method52(abyte0, 625, k12, false);
                    anInt744 = k3;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 254) {
                anInt984 = aClass38_Sub2_Sub3_795.method446();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 12) {
                int l3 = aClass38_Sub2_Sub3_795.method448();
                int l12 = aClass38_Sub2_Sub3_795.method446();
                int k18 = aClass38_Sub2_Sub3_795.method448();
                if (aBoolean1153 && !aBoolean889 && anInt1018 < 50) {
                    anIntArray1124[anInt1018] = l3;
                    anIntArray809[anInt1018] = l12;
                    anIntArray858[anInt1018] = k18 + SoundTrack.delays[l3];
                    anInt1018++;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 204) {
                int i4 = aClass38_Sub2_Sub3_795.method448();
                int i13 = aClass38_Sub2_Sub3_795.method448();
                NpcType npcType = NpcType.method148(i13);
                InterfaceComponent.interfaceComponentArray[i4].aClass38_Sub2_Sub1_310 = npcType.method151(false);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 7) {
                anInt862 = aClass38_Sub2_Sub3_795.method446();
                anInt863 = aClass38_Sub2_Sub3_795.method446();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 103) {
                int j4 = aClass38_Sub2_Sub3_795.method448();
                int j13 = aClass38_Sub2_Sub3_795.method448();
                int l18 = aClass38_Sub2_Sub3_795.method448();
                InterfaceComponent interfaceComponent_3 = InterfaceComponent.interfaceComponentArray[j4];
                Class38_Sub2_Sub1 class38_sub2_sub1 = interfaceComponent_3.aClass38_Sub2_Sub1_310;
                if (class38_sub2_sub1 != null)
                    class38_sub2_sub1.method364(j13, l18);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 32) {
                anInt976 = aClass38_Sub2_Sub3_795.method446();
                anInt755 = aClass38_Sub2_Sub3_795.method446();
                anInt885 = aClass38_Sub2_Sub3_795.method446();
                aBoolean921 = true;
                aBoolean965 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 195) {
                int k4 = aClass38_Sub2_Sub3_795.method448();
                method112(-321, k4);
                if (anInt1001 != -1) {
                    anInt1001 = -1;
                    aBoolean965 = true;
                }
                if (aBoolean1055) {
                    aBoolean1055 = false;
                    aBoolean965 = true;
                }
                anInt1129 = k4;
                aBoolean964 = true;
                aBoolean1080 = true;
                anInt971 = -1;
                aBoolean872 = false;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 14) {
                int l4 = aClass38_Sub2_Sub3_795.method448();
                method112(-321, l4);
                if (anInt1129 != -1) {
                    anInt1129 = -1;
                    aBoolean964 = true;
                    aBoolean1080 = true;
                }
                anInt1001 = l4;
                aBoolean965 = true;
                anInt971 = -1;
                aBoolean872 = false;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 209) {
                int i5 = aClass38_Sub2_Sub3_795.method448();
                int k13 = aClass38_Sub2_Sub3_795.method449();
                int i19 = aClass38_Sub2_Sub3_795.method449();
                InterfaceComponent interfaceComponent_4 = InterfaceComponent.interfaceComponentArray[i5];
                interfaceComponent_4.anInt276 = k13;
                interfaceComponent_4.anInt277 = i19;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 3) {
                aBoolean968 = true;
                anInt728 = aClass38_Sub2_Sub3_795.method446();
                anInt729 = aClass38_Sub2_Sub3_795.method446();
                anInt730 = aClass38_Sub2_Sub3_795.method448();
                anInt731 = aClass38_Sub2_Sub3_795.method446();
                anInt732 = aClass38_Sub2_Sub3_795.method446();
                if (anInt732 >= 100) {
                    anInt1111 = anInt728 * 128 + 64;
                    anInt1113 = anInt729 * 128 + 64;
                    anInt1112 = method33(anInt880, anInt728, (byte) 5, anInt729) - anInt730;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 135) {
                anInt862 = aClass38_Sub2_Sub3_795.method446();
                anInt863 = aClass38_Sub2_Sub3_795.method446();
                for (int j5 = anInt862; j5 < anInt862 + 8; j5++) {
                    for (int l13 = anInt863; l13 < anInt863 + 8; l13++)
                        if (linkedList3dArray[anInt880][j5][l13] != null) {
                            linkedList3dArray[anInt880][j5][l13] = null;
                            method123(j5, l13);
                        }

                }

                for (SpawnedLoc spawnedLoc = (SpawnedLoc) linkedList5
                        .method270(); spawnedLoc != null; spawnedLoc = (SpawnedLoc) linkedList5.method272())
                    if (spawnedLoc.tileX >= anInt862 && spawnedLoc.tileX < anInt862 + 8
                            && spawnedLoc.tileZ >= anInt863 && spawnedLoc.tileZ < anInt863 + 8
                            && spawnedLoc.level == anInt880) {
                        method99(spawnedLoc.lastRotation, spawnedLoc.tileX, spawnedLoc.tileZ,
                                spawnedLoc.classType, spawnedLoc.lastLocIndex, spawnedLoc.lastType, -27819,
                                spawnedLoc.level);
                        spawnedLoc.unlink();
                    }

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 132) {
                int k5 = aClass38_Sub2_Sub3_795.method446();
                int i14 = aClass38_Sub2_Sub3_795.method446();
                int j19 = aClass38_Sub2_Sub3_795.method448();
                int i23 = aClass38_Sub2_Sub3_795.method448();
                int i26 = -1;
                for (int l27 = 0; l27 < anIntArray925.length; l27++)
                    if (anIntArray925[l27] == (k5 << 8) + i14)
                        i26 = l27;

                if (i26 != -1) {
                    if (aByteArrayArray770[i26] == null || aByteArrayArray770[i26].length != i23)
                        aByteArrayArray770[i26] = new byte[i23];
                    aClass38_Sub2_Sub3_795.method455(anInt779 - 6, -110, j19, aByteArrayArray770[i26]);
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 41) {
                long l5 = aClass38_Sub2_Sub3_795.method452(603);
                int k19 = aClass38_Sub2_Sub3_795.method451();
                int j23 = aClass38_Sub2_Sub3_795.method446();
                boolean flag2 = false;
                for (int i28 = 0; i28 < 100; i28++) {
                    if (anIntArray878[i28] != k19)
                        continue;
                    flag2 = true;
                    break;
                }

                if (j23 <= 1) {
                    for (int l29 = 0; l29 < anInt793; l29++) {
                        if (aLongArray768[l29] != l5)
                            continue;
                        flag2 = true;
                        break;
                    }

                }
                if (!flag2 && anInt802 == 0)
                    try {
                        anIntArray878[anInt855] = k19;
                        anInt855 = (anInt855 + 1) % 100;
                        String s7 = TextEncoder.read(aClass38_Sub2_Sub3_795, anInt779 - 13);
                        s7 = Class24.method239(s7, 0);
                        if (j23 > 1)
                            method111(7, s7, (byte) 4, StringUtils.formatName(StringUtils.fromBase37(l5)));
                        else
                            method111(3, s7, (byte) 4, StringUtils.formatName(StringUtils.fromBase37(l5)));
                    } catch (Exception exception1) {
                        signlink.reporterror("cde1");
                    }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 193) {
                for (int i6 = 0; i6 < anIntArray938.length; i6++)
                    if (anIntArray938[i6] != anIntArray1075[i6]) {
                        anIntArray938[i6] = anIntArray1075[i6];
                        method61(i6, 49);
                        aBoolean964 = true;
                    }

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 87) {
                int j6 = aClass38_Sub2_Sub3_795.method448();
                int j14 = aClass38_Sub2_Sub3_795.method448();
                InterfaceComponent.interfaceComponentArray[j6].aClass38_Sub2_Sub1_310 = new Class38_Sub2_Sub1(false, j14);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 185) {
                int k6 = aClass38_Sub2_Sub3_795.method449();
                anInt1021 = k6;
                aBoolean965 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 68) {
                if (anInt757 == 12)
                    aBoolean964 = true;
                anInt1072 = aClass38_Sub2_Sub3_795.method446();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 74) {
                aBoolean968 = true;
                anInt948 = aClass38_Sub2_Sub3_795.method446();
                anInt949 = aClass38_Sub2_Sub3_795.method446();
                anInt950 = aClass38_Sub2_Sub3_795.method448();
                anInt951 = aClass38_Sub2_Sub3_795.method446();
                anInt952 = aClass38_Sub2_Sub3_795.method446();
                if (anInt952 >= 100) {
                    int l6 = anInt948 * 128 + 64;
                    int k14 = anInt949 * 128 + 64;
                    int l19 = method33(anInt880, anInt948, (byte) 5, anInt949) - anInt950;
                    int k23 = l6 - anInt1111;
                    int j26 = l19 - anInt1112;
                    int j28 = k14 - anInt1113;
                    int i30 = (int) Math.sqrt(k23 * k23 + j28 * j28);
                    anInt1114 = (int) (Math.atan2(j26, i30) * 325.94900000000001D) & 0x7ff;
                    anInt1115 = (int) (Math.atan2(k23, j28) * -325.94900000000001D) & 0x7ff;
                    if (anInt1114 < 128)
                        anInt1114 = 128;
                    if (anInt1114 > 383)
                        anInt1114 = 383;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 84) {
                anInt757 = aClass38_Sub2_Sub3_795.method446();
                aBoolean964 = true;
                aBoolean1080 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 4) {
                String s1 = aClass38_Sub2_Sub3_795.method453();
                if (s1.endsWith(":tradereq:")) {
                    String s3 = s1.substring(0, s1.indexOf(":"));
                    long l20 = StringUtils.toBase37(s3);
                    boolean flag3 = false;
                    for (int k28 = 0; k28 < anInt793; k28++) {
                        if (aLongArray768[k28] != l20)
                            continue;
                        flag3 = true;
                        break;
                    }

                    if (!flag3 && anInt802 == 0)
                        method111(4, "wishes to trade with you.", (byte) 4, s3);
                } else if (s1.endsWith(":duelreq:")) {
                    String s4 = s1.substring(0, s1.indexOf(":"));
                    long l21 = StringUtils.toBase37(s4);
                    boolean flag4 = false;
                    for (int l28 = 0; l28 < anInt793; l28++) {
                        if (aLongArray768[l28] != l21)
                            continue;
                        flag4 = true;
                        break;
                    }

                    if (!flag4 && anInt802 == 0)
                        method111(8, "wishes to duel with you.", (byte) 4, s4);
                } else {
                    method111(0, s1, (byte) 4, "");
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 46) {
                int i7 = aClass38_Sub2_Sub3_795.method448();
                int l14 = aClass38_Sub2_Sub3_795.method448();
                int i20 = aClass38_Sub2_Sub3_795.method448();
                ObjType objType = ObjType.method169(l14);
                InterfaceComponent.interfaceComponentArray[i7].aClass38_Sub2_Sub1_310 = objType.method173(50);
                InterfaceComponent.interfaceComponentArray[i7].anInt315 = objType.anInt150;
                InterfaceComponent.interfaceComponentArray[i7].anInt316 = objType.anInt151;
                InterfaceComponent.interfaceComponentArray[i7].anInt314 = (objType.anInt149 * 100) / i20;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 168) {
                int j7 = aClass38_Sub2_Sub3_795.method448();
                method112(-321, j7);
                if (anInt1129 != -1) {
                    anInt1129 = -1;
                    aBoolean964 = true;
                    aBoolean1080 = true;
                }
                if (anInt1001 != -1) {
                    anInt1001 = -1;
                    aBoolean965 = true;
                }
                if (aBoolean1055) {
                    aBoolean1055 = false;
                    aBoolean965 = true;
                }
                anInt971 = j7;
                aBoolean872 = false;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 2) {
                int k7 = aClass38_Sub2_Sub3_795.method448();
                int i15 = aClass38_Sub2_Sub3_795.method448();
                int j20 = i15 >> 10 & 0x1f;
                int l23 = i15 >> 5 & 0x1f;
                int k26 = i15 & 0x1f;
                InterfaceComponent.interfaceComponentArray[k7].anInt305 = (j20 << 19) + (l23 << 11) + (k26 << 3);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 136) {
                for (int l7 = 0; l7 < aClass38_Sub7_Sub3_Sub2Array822.length; l7++)
                    if (aClass38_Sub7_Sub3_Sub2Array822[l7] != null)
                        aClass38_Sub7_Sub3_Sub2Array822[l7].anInt1407 = -1;

                for (int j15 = 0; j15 < aClass38_Sub7_Sub3_Sub1Array927.length; j15++)
                    if (aClass38_Sub7_Sub3_Sub1Array927[j15] != null)
                        aClass38_Sub7_Sub3_Sub1Array927[j15].anInt1407 = -1;

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 26) {
                int i8 = aClass38_Sub2_Sub3_795.method448();
                boolean flag1 = aClass38_Sub2_Sub3_795.method446() == 1;
                InterfaceComponent.interfaceComponentArray[i8].aBoolean284 = flag1;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 21) {
                anInt793 = anInt779 / 8;
                for (int j8 = 0; j8 < anInt793; j8++)
                    aLongArray768[j8] = aClass38_Sub2_Sub3_795.method452(603);

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 239) {
                aBoolean968 = false;
                for (int k8 = 0; k8 < 5; k8++)
                    aBooleanArray754[k8] = false;

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 129) {
                if (anInt1129 != -1) {
                    anInt1129 = -1;
                    aBoolean964 = true;
                    aBoolean1080 = true;
                }
                if (anInt1001 != -1) {
                    anInt1001 = -1;
                    aBoolean965 = true;
                }
                if (aBoolean1055) {
                    aBoolean1055 = false;
                    aBoolean965 = true;
                }
                anInt971 = -1;
                aBoolean872 = false;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 201) {
                int l8 = aClass38_Sub2_Sub3_795.method448();
                String s5 = aClass38_Sub2_Sub3_795.method453();
                InterfaceComponent.interfaceComponentArray[l8].aString303 = s5;
                if (InterfaceComponent.interfaceComponentArray[l8].anInt270 == anIntArray861[anInt757])
                    aBoolean964 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 44) {
                aBoolean964 = true;
                int i9 = aClass38_Sub2_Sub3_795.method446();
                int k15 = aClass38_Sub2_Sub3_795.method451();
                int k20 = aClass38_Sub2_Sub3_795.method446();
                anIntArray1079[i9] = k15;
                anIntArray807[i9] = k20;
                anIntArray926[i9] = 1;
                for (int i24 = 0; i24 < 98; i24++)
                    if (k15 >= anIntArray864[i24])
                        anIntArray926[i9] = i24 + 2;

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 162) {
                anInt862 = aClass38_Sub2_Sub3_795.method446();
                anInt863 = aClass38_Sub2_Sub3_795.method446();
                while (aClass38_Sub2_Sub3_795.offset < anInt779) {
                    int j9 = aClass38_Sub2_Sub3_795.method446();
                    method22((byte) -45, aClass38_Sub2_Sub3_795, j9);
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 22) {
                if (anInt757 == 12)
                    aBoolean964 = true;
                anInt769 = aClass38_Sub2_Sub3_795.method449();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 13) {
                int k9 = aClass38_Sub2_Sub3_795.method446();
                int l15 = aClass38_Sub2_Sub3_795.method446();
                int i21 = aClass38_Sub2_Sub3_795.method446();
                int j24 = aClass38_Sub2_Sub3_795.method446();
                aBooleanArray754[k9] = true;
                anIntArray959[k9] = l15;
                anIntArray966[k9] = i21;
                anIntArray1159[k9] = j24;
                anIntArray1024[k9] = 0;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 213) {
                aBoolean964 = true;
                int l9 = aClass38_Sub2_Sub3_795.method448();
                InterfaceComponent interfaceComponent_2 = InterfaceComponent.interfaceComponentArray[l9];
                while (aClass38_Sub2_Sub3_795.offset < anInt779) {
                    int j21 = aClass38_Sub2_Sub3_795.method446();
                    int k24 = aClass38_Sub2_Sub3_795.method448();
                    int l26 = aClass38_Sub2_Sub3_795.method446();
                    if (l26 == 255)
                        l26 = aClass38_Sub2_Sub3_795.method451();
                    if (j21 >= 0 && j21 < interfaceComponent_2.anIntArray265.length) {
                        interfaceComponent_2.anIntArray265[j21] = k24;
                        interfaceComponent_2.anIntArray266[j21] = l26;
                    }
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 184) {
                method109(aClass38_Sub2_Sub3_795, anInt779, 822);
                if (anInt1078 == 1) {
                    anInt1078 = 2;
                    Class3.anInt109 = anInt880;
                    method124(869);
                }
                if (aBoolean889 && anInt1078 == 2 && Class3.anInt109 != anInt880) {
                    drawArea22.init2D();
                    aClass38_Sub2_Sub2_Sub4_986.method421(151, (byte) 6, 0, "Loading - please wait.", 257);
                    aClass38_Sub2_Sub2_Sub4_986.method421(150, (byte) 6, 0xffffff, "Loading - please wait.", 256);
                    drawArea22.drawImage(11, super.aGraphics14, 8);
                    Class3.anInt109 = anInt880;
                    method124(869);
                }
                if (anInt880 != anInt774 && anInt1078 == 2) {
                    anInt774 = anInt880;
                    method45(anInt880, -153);
                }
                anInt780 = -1;
                return true;
            }
            signlink.reporterror("T1 - " + anInt780 + "," + anInt779 + " - " + anInt829 + "," + anInt830);
            method58(-780);
        } catch (IOException _ex) {
            method121(false);
        } catch (Exception exception) {
            String s2 = "T2 - " + anInt780 + "," + anInt829 + "," + anInt830 + " - " + anInt779 + ","
                    + (anInt761 + aClass38_Sub7_Sub3_Sub2_967.anIntArray1428[0]) + ","
                    + (anInt762 + aClass38_Sub7_Sub3_Sub2_967.anIntArray1429[0]) + " - ";
            for (int i16 = 0; i16 < anInt779 && i16 < 50; i16++)
                s2 = s2 + aClass38_Sub2_Sub3_795.aByteArray1328[i16] + ",";

            signlink.reporterror(s2);
            method58(-780);
        }
        return true;
    }

    public void method137(byte byte0) {
        drawArea20.init2D();
        Class38_Sub2_Sub2_Sub1.anIntArray1448 = anIntArray736;
        aClass38_Sub2_Sub2_Sub3_981.method419(0, 0, false);
        if (anInt1129 != -1)
            method59(0, 0, 38682, InterfaceComponent.interfaceComponentArray[anInt1129], 0);
        else if (anIntArray861[anInt757] != -1)
            method59(0, 0, 38682, InterfaceComponent.interfaceComponentArray[anIntArray861[anInt757]], 0);
        if (aBoolean879 && anInt1148 == 1)
            method74(-961);
        drawArea20.drawImage(231, super.aGraphics14, 562);
        if (byte0 == 2)
            byte0 = 0;
        else
            return;
        drawArea22.init2D();
        Class38_Sub2_Sub2_Sub1.anIntArray1448 = anIntArray737;
    }

    public boolean method138(int i, String s) {
        while (i >= 0)
            aClass38_Sub2_Sub3_798.method436(74);
        if (s == null)
            return false;
        for (int j = 0; j < anInt1089; j++)
            if (s.equalsIgnoreCase(aStringArray1127[j]))
                return true;

        return s.equalsIgnoreCase(aClass38_Sub7_Sub3_Sub2_967.aString1505);
    }

    public void init() {
        anInt886 = Integer.parseInt(getParameter("nodeid"));
        anInt887 = Integer.parseInt(getParameter("portoff"));
        String s = getParameter("lowmem");
        if (s != null && s.equals("1"))
            method27(true);
        else
            method106(9);
        String s1 = getParameter("free");
        aBoolean888 = s1 == null || !s1.equals("1");
        method2(532, false, 789);
    }

    public void method139(boolean flag, int i, int j, Class38_Sub2_Sub3 class38_sub2_sub3,
                          Class38_Sub7_Sub3_Sub2 class38_sub7_sub3_sub2) {
        if (!flag)
            aBoolean849 = !aBoolean849;
        if ((j & 1) == 1) {
            int k = class38_sub2_sub3.method446();
            byte[] abyte0 = new byte[k];
            Class38_Sub2_Sub3 class38_sub2_sub3_1 = new Class38_Sub2_Sub3(363, abyte0);
            class38_sub2_sub3.method455(k, -110, 0, abyte0);
            aClass38_Sub2_Sub3Array827[i] = class38_sub2_sub3_1;
            class38_sub7_sub3_sub2.method470(false, class38_sub2_sub3_1);
        }
        if ((j & 2) == 2) {
            int l = class38_sub2_sub3.method448();
            if (l == 65535)
                l = -1;
            if (l == class38_sub7_sub3_sub2.anInt1407)
                class38_sub7_sub3_sub2.anInt1411 = 0;
            int k1 = class38_sub2_sub3.method446();
            if (l == -1 || class38_sub7_sub3_sub2.anInt1407 == -1
                    || SeqType.seqTypes[l].anInt372 > SeqType.seqTypes[class38_sub7_sub3_sub2.anInt1407].anInt372
                    || SeqType.seqTypes[class38_sub7_sub3_sub2.anInt1407].anInt372 == 0) {
                class38_sub7_sub3_sub2.anInt1407 = l;
                class38_sub7_sub3_sub2.anInt1408 = 0;
                class38_sub7_sub3_sub2.anInt1409 = 0;
                class38_sub7_sub3_sub2.anInt1410 = k1;
                class38_sub7_sub3_sub2.anInt1411 = 0;
            }
        }
        if ((j & 4) == 4) {
            class38_sub7_sub3_sub2.anInt1401 = class38_sub2_sub3.method448();
            if (class38_sub7_sub3_sub2.anInt1401 == 65535)
                class38_sub7_sub3_sub2.anInt1401 = -1;
        }
        if ((j & 8) == 8) {
            class38_sub7_sub3_sub2.aString1392 = class38_sub2_sub3.method453();
            class38_sub7_sub3_sub2.anInt1394 = 0;
            class38_sub7_sub3_sub2.anInt1395 = 0;
            class38_sub7_sub3_sub2.anInt1393 = 150;
            method111(2, class38_sub7_sub3_sub2.aString1392, (byte) 4,
                    class38_sub7_sub3_sub2.aString1505);
        }
        if ((j & 0x10) == 16) {
            class38_sub7_sub3_sub2.anInt1396 = class38_sub2_sub3.method446();
            class38_sub7_sub3_sub2.anInt1397 = class38_sub2_sub3.method446();
            class38_sub7_sub3_sub2.anInt1398 = anInt955 + 400;
            class38_sub7_sub3_sub2.anInt1399 = class38_sub2_sub3.method446();
            class38_sub7_sub3_sub2.anInt1400 = class38_sub2_sub3.method446();
        }
        if ((j & 0x20) == 32) {
            class38_sub7_sub3_sub2.anInt1402 = class38_sub2_sub3.method448();
            class38_sub7_sub3_sub2.anInt1403 = class38_sub2_sub3.method448();
        }
        if ((j & 0x40) == 64) {
            int i1 = class38_sub2_sub3.method448();
            int l1 = class38_sub2_sub3.method446();
            int i2 = class38_sub2_sub3.method446();
            int j2 = class38_sub2_sub3.offset;
            if (class38_sub7_sub3_sub2.aString1505 != null) {
                long l2 = StringUtils.toBase37(class38_sub7_sub3_sub2.aString1505);
                boolean flag1 = false;
                if (l1 <= 1) {
                    for (int k2 = 0; k2 < anInt793; k2++) {
                        if (aLongArray768[k2] != l2)
                            continue;
                        flag1 = true;
                        break;
                    }

                }
                if (!flag1 && anInt802 == 0)
                    try {
                        String s = TextEncoder.read(class38_sub2_sub3, i2);
                        s = Class24.method239(s, 0);
                        class38_sub7_sub3_sub2.aString1392 = s;
                        class38_sub7_sub3_sub2.anInt1394 = i1 >> 8;
                        class38_sub7_sub3_sub2.anInt1395 = i1 & 0xff;
                        class38_sub7_sub3_sub2.anInt1393 = 150;
                        if (l1 > 1)
                            method111(1, s, (byte) 4, class38_sub7_sub3_sub2.aString1505);
                        else
                            method111(2, s, (byte) 4, class38_sub7_sub3_sub2.aString1505);
                    } catch (Exception exception) {
                        signlink.reporterror("cde2");
                    }
            }
            class38_sub2_sub3.offset = j2 + i2;
        }
        if ((j & 0x100) == 256) {
            class38_sub7_sub3_sub2.anInt1412 = class38_sub2_sub3.method448();
            int j1 = class38_sub2_sub3.method451();
            class38_sub7_sub3_sub2.anInt1416 = j1 >> 16;
            class38_sub7_sub3_sub2.anInt1415 = anInt955 + (j1 & 0xffff);
            class38_sub7_sub3_sub2.anInt1413 = 0;
            class38_sub7_sub3_sub2.anInt1414 = 0;
            if (class38_sub7_sub3_sub2.anInt1415 > anInt955)
                class38_sub7_sub3_sub2.anInt1413 = -1;
            if (class38_sub7_sub3_sub2.anInt1412 == 65535)
                class38_sub7_sub3_sub2.anInt1412 = -1;
        }
        if ((j & 0x200) == 512) {
            class38_sub7_sub3_sub2.anInt1417 = class38_sub2_sub3.method446();
            class38_sub7_sub3_sub2.anInt1419 = class38_sub2_sub3.method446();
            class38_sub7_sub3_sub2.anInt1418 = class38_sub2_sub3.method446();
            class38_sub7_sub3_sub2.anInt1420 = class38_sub2_sub3.method446();
            class38_sub7_sub3_sub2.anInt1421 = class38_sub2_sub3.method448() + anInt955;
            class38_sub7_sub3_sub2.anInt1422 = class38_sub2_sub3.method448() + anInt955;
            class38_sub7_sub3_sub2.anInt1423 = class38_sub2_sub3.method446();
            class38_sub7_sub3_sub2.anInt1427 = 0;
            class38_sub7_sub3_sub2.anIntArray1428[0] = class38_sub7_sub3_sub2.anInt1418;
            class38_sub7_sub3_sub2.anIntArray1429[0] = class38_sub7_sub3_sub2.anInt1420;
        }
    }

    public void method13(boolean flag, String s, int i) {
        method95((byte) 99);
        if (fileArchive == null) {
            super.method13(true, s, i);
            return;
        }
        drawArea13.init2D();
        char c = '\u0168';
        char c1 = '\310';
        byte byte0 = 20;
        aClass38_Sub2_Sub2_Sub4_987.method421(c1 / 2 - 26 - byte0, (byte) 6, 0xffffff,
                "RuneScape is loading - please wait...", c / 2);
        aBoolean974 &= flag;
        int j = c1 / 2 - 18 - byte0;
        Class38_Sub2_Sub2.method381(3, c / 2 - 152, 0x8c1111, 34, j, 304);
        Class38_Sub2_Sub2.method381(3, c / 2 - 151, 0, 32, j + 1, 302);
        Class38_Sub2_Sub2.method380(j + 2, c / 2 - 150, 0x8c1111, (byte) 93, i * 3, 30);
        Class38_Sub2_Sub2.method380(j + 2, (c / 2 - 150) + i * 3, 0, (byte) 93, 300 - i * 3, 30);
        aClass38_Sub2_Sub2_Sub4_987.method421((c1 / 2 + 5) - byte0, (byte) 6, 0xffffff, s, c / 2);
        drawArea13.drawImage(186, super.aGraphics14, 214);
        if (aBoolean751) {
            aBoolean751 = false;
            if (!aBoolean902) {
                drawArea14.drawImage(0, super.aGraphics14, 0);
                drawArea15.drawImage(0, super.aGraphics14, 661);
            }
            drawArea11.drawImage(0, super.aGraphics14, 128);
            drawArea12.drawImage(386, super.aGraphics14, 214);
            drawArea16.drawImage(265, super.aGraphics14, 0);
            drawArea17.drawImage(265, super.aGraphics14, 574);
            drawArea18.drawImage(186, super.aGraphics14, 128);
            drawArea19.drawImage(186, super.aGraphics14, 574);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("RS2 user client - release #" + 225);
            if (args.length != 4) {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
                return;
            }
            anInt886 = Integer.parseInt(args[0]);
            anInt887 = Integer.parseInt(args[1]);
            if (args[2].equals("lowmem"))
                method27(true);
            else if (args[2].equals("highmem")) {
                method106(9);
            } else {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
                return;
            }
            if (args[3].equals("free"))
                aBoolean888 = false;
            else if (args[3].equals("members")) {
                aBoolean888 = true;
            } else {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
                return;
            }
            signlink.startpriv(InetAddress.getLocalHost());
            client client1 = new client();
            client1.method1(532, 789, 0);
            return;
        } catch (Exception exception) {
            return;
        }
    }

    public client() {
        anInt733 = 24676;
        anInt734 = -1;
        anIntArray742 = new int[5];
        aClass38_Sub2_Sub3_743 = Class38_Sub2_Sub3.method433(1, -737);
        aBoolean751 = false;
        linkedList2 = new LinkedList();
        aBooleanArray754 = new boolean[5];
        anInt756 = 9;
        anInt757 = 3;
        anIntArrayArray758 = new int[104][104];
        anInt759 = 997;
        aString765 = "";
        aByte766 = 106;
        linkedList3 = new LinkedList();
        aLongArray768 = new long[100];
        anInt771 = 723;
        anIntArray773 = new int[100];
        anInt774 = -1;
        aString775 = "";
        aClass38_Sub2_Sub2_Sub2Array776 = new Class38_Sub2_Sub2_Sub2[20];
        anInt778 = 332;
        aString784 = "";
        anInt786 = -1;
        aBoolean787 = true;
        aBoolean788 = false;
        anIntArray789 = new int[7];
        aByte790 = 8;
        aClass38_Sub2_Sub2_Sub2Array791 = new Class38_Sub2_Sub2_Sub2[1000];
        anInt792 = 78;
        aClass38_Sub2_Sub3_795 = Class38_Sub2_Sub3.method433(1, -737);
        aClass38_Sub2_Sub3_798 = Class38_Sub2_Sub3.method433(1, -737);
        aBoolean799 = false;
        anInt803 = 3;
        anInt805 = -655;
        anIntArray807 = new int[50];
        interfaceComponent = new InterfaceComponent();
        anIntArray809 = new int[50];
        anIntArray811 = new int[9];
        aBoolean812 = true;
        anInt813 = 4277;
        aClass38_Sub2_Sub2_Sub3Array814 = new Class38_Sub2_Sub2_Sub3[13];
        anInt816 = 128;
        anInt820 = 2048;
        anInt821 = 2047;
        aClass38_Sub7_Sub3_Sub2Array822 = new Class38_Sub7_Sub3_Sub2[anInt820];
        anIntArray824 = new int[anInt820];
        anIntArray826 = new int[anInt820];
        aClass38_Sub2_Sub3Array827 = new Class38_Sub2_Sub3[anInt820];
        linkedList4 = new LinkedList();
        aStringArray834 = new String[500];
        aBoolean835 = true;
        aBoolean836 = true;
        aByte843 = 4;
        aBoolean849 = false;
        anIntArray850 = new int[256];
        aByte851 = 2;
        anIntArray856 = new int[33];
        anIntArray858 = new int[50];
        aBoolean860 = false;
        aBoolean865 = false;
        aByte866 = 99;
        aBoolean869 = false;
        aBoolean872 = false;
        anIntArray878 = new int[100];
        aBoolean879 = false;
        aBoolean881 = false;
        anInt883 = -655;
        linkedList5 = new LinkedList();
        anIntArray896 = new int[100];
        aStringArray897 = new String[100];
        aStringArray898 = new String[100];
        aBoolean902 = false;
        anInt907 = -1;
        aBoolean912 = false;
        anIntArray918 = new int[1000];
        anIntArray919 = new int[1000];
        anIntArrayArray920 = new int[104][104];
        aBoolean921 = false;
        aBoolean923 = false;
        anIntArray926 = new int[50];
        aClass38_Sub7_Sub3_Sub1Array927 = new Class38_Sub7_Sub3_Sub1[8192];
        anIntArray929 = new int[8192];
        anInt931 = 1;
        aByte935 = -46;
        anIntArray938 = new int[2000];
        anIntArray940 = new int[1000];
        aLongArray943 = new long[100];
        anIntArray953 = new int[151];
        aClass8Array954 = new Class8[4];
        aClass38_Sub2_Sub2_Sub2Array956 = new Class38_Sub2_Sub2_Sub2[20];
        anIntArray959 = new int[5];
        aBoolean960 = false;
        aBoolean964 = false;
        aBoolean965 = false;
        anIntArray966 = new int[5];
        aBoolean968 = false;
        aString970 = "";
        anInt971 = -1;
        aBoolean974 = false;
        aBoolean975 = false;
        anInt980 = 0x332d25;
        aBoolean990 = false;
        anInt993 = 0x766654;
        anIntArray994 = new int[4000];
        anIntArray995 = new int[4000];
        aCRC32_996 = new CRC32();
        anInt1001 = -1;
        anInt1019 = -1;
        anInt1020 = -1;
        anInt1021 = -1;
        aBoolean1023 = false;
        anIntArray1024 = new int[5];
        aClass38_Sub2_Sub2_Sub3Array1038 = new Class38_Sub2_Sub2_Sub3[50];
        anInt1039 = 27808;
        anInt1050 = 0x23201b;
        aBoolean1055 = false;
        linkedList1 = new LinkedList();
        anInt1065 = -1;
        aString1066 = "";
        aString1067 = "";
        aByte1068 = 7;
        aByteArray1069 = new byte[16384];
        aBoolean1070 = false;
        anInt1071 = 242;
        anIntArray1075 = new int[2000];
        anIntArray1079 = new int[50];
        aBoolean1080 = false;
        aString1083 = "";
        aString1084 = "";
        anInt1086 = 2;
        anInt1092 = 50;
        anIntArray1093 = new int[anInt1092];
        anIntArray1094 = new int[anInt1092];
        anIntArray1095 = new int[anInt1092];
        anIntArray1096 = new int[anInt1092];
        anIntArray1097 = new int[anInt1092];
        anIntArray1098 = new int[anInt1092];
        anIntArray1099 = new int[anInt1092];
        aStringArray1100 = new String[anInt1092];
        anInt1105 = -986;
        anInt1106 = -35388;
        anIntArray1117 = new int[33];
        anIntArrayArray1118 = new int[104][104];
        aClass38_Sub2_Sub2_Sub2Array1120 = new Class38_Sub2_Sub2_Sub2[8];
        aBoolean1121 = false;
        aByte1122 = 94;
        anObject1123 = new Object();
        anIntArray1124 = new int[50];
        anInt1126 = 2;
        aStringArray1127 = new String[100];
        anInt1128 = -1;
        anInt1129 = -1;
        anInt1131 = 2;
        anInt1132 = 29286;
        anIntArray1133 = new int[151];
        anInt1135 = 1;
        aString1137 = "";
        aClass38_Sub2_Sub2_Sub2Array1138 = new Class38_Sub2_Sub2_Sub2[50];
        anIntArray1139 = new int[500];
        anIntArray1140 = new int[500];
        anIntArray1141 = new int[500];
        anIntArray1142 = new int[500];
        anInt1143 = 701;
        aBoolean1144 = false;
        aBoolean1147 = true;
        aBoolean1153 = true;
        linkedList3dArray = new LinkedList[4][104][104];
        anInt1158 = 0x4d4233;
        anIntArray1159 = new int[5];
        anInt1160 = -676;
    }

    public static int anInt724;
    public static String aString725 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
    public int anInt726;
    public int anInt727;
    public int anInt728;
    public int anInt729;
    public int anInt730;
    public int anInt731;
    public int anInt732;
    public int anInt733;
    public int anInt734;
    public int[] anIntArray735;
    public int[] anIntArray736;
    public int[] anIntArray737;
    public int anInt738;
    public int anInt739;
    public int anInt740;
    public int anInt741;
    public int[] anIntArray742;
    public Class38_Sub2_Sub3 aClass38_Sub2_Sub3_743;
    public int anInt744;
    public int anInt745;
    public int anInt746;
    public int anInt747;
    public int anInt748;
    public int anInt749;
    public int anInt750;
    public boolean aBoolean751;
    public LinkedList linkedList2;
    public IsaacRandom isaacState;
    public boolean[] aBooleanArray754;
    public int anInt755;
    public int anInt756;
    public int anInt757;
    public int[][] anIntArrayArray758;
    public int anInt759;
    public int anInt760;
    public int anInt761;
    public int anInt762;
    public int anInt763;
    public int anInt764;
    public String aString765;
    public byte aByte766;
    public LinkedList linkedList3;
    public long[] aLongArray768;
    public int anInt769;
    public byte[][] aByteArrayArray770;
    public int anInt771;
    public static int anInt772;
    public int[] anIntArray773;
    public int anInt774;
    public String aString775;
    public Class38_Sub2_Sub2_Sub2[] aClass38_Sub2_Sub2_Sub2Array776;
    public long aLong777;
    public int anInt778;
    public int anInt779;
    public int anInt780;
    public int anInt781;
    public int anInt782;
    public int anInt783;
    public String aString784;
    public int anInt785;
    public int anInt786;
    public boolean aBoolean787;
    public boolean aBoolean788;
    public int[] anIntArray789;
    public byte aByte790;
    public Class38_Sub2_Sub2_Sub2[] aClass38_Sub2_Sub2_Sub2Array791;
    public int anInt792;
    public int anInt793;
    public int[][][] anIntArrayArrayArray794;
    public Class38_Sub2_Sub3 aClass38_Sub2_Sub3_795;
    public int anInt796;
    public static int anInt797;
    public Class38_Sub2_Sub3 aClass38_Sub2_Sub3_798;
    public boolean aBoolean799;
    public int anInt800;
    public int anInt801;
    public int anInt802;
    public int anInt803;
    public static int anInt804;
    public int anInt805;
    public static int anInt806;
    public int[] anIntArray807;
    public InterfaceComponent interfaceComponent;
    public int[] anIntArray809;
    public int anInt810;
    public int[] anIntArray811;
    public boolean aBoolean812;
    public int anInt813;
    public Class38_Sub2_Sub2_Sub3[] aClass38_Sub2_Sub2_Sub3Array814;
    public int anInt815;
    public int anInt816;
    public int anInt817;
    public int anInt818;
    public int anInt819;
    public int anInt820;
    public int anInt821;
    public Class38_Sub7_Sub3_Sub2[] aClass38_Sub7_Sub3_Sub2Array822;
    public int anInt823;
    public int[] anIntArray824;
    public int anInt825;
    public int[] anIntArray826;
    public Class38_Sub2_Sub3[] aClass38_Sub2_Sub3Array827;
    public int anInt828;
    public int anInt829;
    public int anInt830;
    public Class32 aClass32_831;
    public LinkedList linkedList4;
    public int anInt833;
    public String[] aStringArray834;
    public boolean aBoolean835;
    public boolean aBoolean836;
    public int anInt837;
    public int anInt838;
    public int anInt839;
    public byte[][][] aByteArrayArrayArray840;
    public int[] anIntArray841;
    public int[] anIntArray842;
    public byte aByte843;
    public int anInt844;
    public int anInt845;
    public int anInt846;
    public int anInt847;
    public int anInt848;
    public boolean aBoolean849;
    public int[] anIntArray850;
    public byte aByte851;
    public DrawArea drawArea24;
    public DrawArea drawArea25;
    public DrawArea drawArea26;
    public int anInt855;
    public int[] anIntArray856;
    public static int anInt857;
    public int[] anIntArray858;
    public int anInt859;
    public boolean aBoolean860;
    public int[] anIntArray861 = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1
    };
    public int anInt862;
    public int anInt863;
    public static int[] anIntArray864;
    public boolean aBoolean865;
    public byte aByte866;
    public static int anInt867;
    public int anInt868;
    public boolean aBoolean869;
    public static boolean aBoolean870 = true;
    public static byte aByte871 = 15;
    public boolean aBoolean872;
    public int anInt873;
    public int anInt874;
    public int anInt875;
    public static int anInt876;
    public final int anInt877 = 100;
    public int[] anIntArray878;
    public boolean aBoolean879;
    public int anInt880;
    public boolean aBoolean881;
    public static int anInt882;
    public int anInt883;
    public LinkedList linkedList5;
    public int anInt885;
    public static int anInt886 = 10;
    public static int anInt887;
    public static boolean aBoolean888 = true;
    public static boolean aBoolean889;
    public static int anInt890;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_891;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_892;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_893;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_894;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_895;
    public int[] anIntArray896;
    public String[] aStringArray897;
    public String[] aStringArray898;
    public static int anInt899 = 629;
    public long aLong900;
    public int anInt901;
    public boolean aBoolean902;
    public int[] anIntArray903;
    public int[] anIntArray904;
    public int[] anIntArray905;
    public int[] anIntArray906;
    public int anInt907;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_908;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_909;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_910;
    public int anInt911;
    public boolean aBoolean912;
    public static int anInt913;
    public int anInt914;
    public int anInt915;
    public int anInt916;
    public int anInt917;
    public int[] anIntArray918;
    public int[] anIntArray919;
    public int[][] anIntArrayArray920;
    public boolean aBoolean921;
    public static BigInteger aBigInteger922 = new BigInteger(
            "58778699976184461502525193738213253649000149147835990136706041084440742975821");
    public boolean aBoolean923;
    public int anInt924;
    public int[] anIntArray925;
    public int[] anIntArray926;
    public Class38_Sub7_Sub3_Sub1[] aClass38_Sub7_Sub3_Sub1Array927;
    public int anInt928;
    public int[] anIntArray929;
    public int anInt930;
    public int anInt931;
    public int anInt932;
    public int anInt933;
    public int anInt934;
    public byte aByte935;
    public String aString936;
    public static int anInt937;
    public int[] anIntArray938;
    public int anInt939;
    public int[] anIntArray940;
    public int anInt941;
    public static final int[][] anIntArrayArray942 = {
            {
                    6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433,
                    2983, 54193
            },
            {
                    8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153,
                    56621, 4783, 1341, 16578, 35003, 25239
            },
            {
                    25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094,
                    10153, 56621, 4783, 1341, 16578, 35003
            },
            {
                    4626, 11146, 6439, 12, 4758, 10270
            },
            {
                    4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574
            }
    };
    public long[] aLongArray943;
    public int anInt944;
    public int anInt945;
    public int anInt946;
    public int anInt947;
    public int anInt948;
    public int anInt949;
    public int anInt950;
    public int anInt951;
    public int anInt952;
    public int[] anIntArray953;
    public Class8[] aClass8Array954;
    public static int anInt955;
    public Class38_Sub2_Sub2_Sub2[] aClass38_Sub2_Sub2_Sub2Array956;
    public int anInt957;
    public static int anInt958 = 3;
    public int[] anIntArray959;
    public boolean aBoolean960;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_961;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_962;
    public int anInt963;
    public boolean aBoolean964;
    public boolean aBoolean965;
    public int[] anIntArray966;
    public Class38_Sub7_Sub3_Sub2 aClass38_Sub7_Sub3_Sub2_967;
    public boolean aBoolean968;
    public int anInt969;
    public String aString970;
    public int anInt971;
    public int anInt972;
    public Class38_Sub2_Sub2_Sub3[] aClass38_Sub2_Sub2_Sub3Array973;
    public boolean aBoolean974;
    public boolean aBoolean975;
    public int anInt976;
    public int anInt977;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_978;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_979;
    public int anInt980;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_981;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_982;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_983;
    public int anInt984;
    public Class38_Sub2_Sub2_Sub4 aClass38_Sub2_Sub2_Sub4_985;
    public Class38_Sub2_Sub2_Sub4 aClass38_Sub2_Sub2_Sub4_986;
    public Class38_Sub2_Sub2_Sub4 aClass38_Sub2_Sub2_Sub4_987;
    public Class38_Sub2_Sub2_Sub4 aClass38_Sub2_Sub2_Sub4_988;
    public int anInt989;
    public boolean aBoolean990;
    public int[] anIntArray991;
    public int[] anIntArray992;
    public int anInt993;
    public int[] anIntArray994;
    public int[] anIntArray995;
    public CRC32 aCRC32_996;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_997;
    public static int anInt998;
    public BufferedStream bufferedStream;
    public byte[][] aByteArrayArray1000;
    public int anInt1001;
    public int anInt1002;
    public int anInt1003;
    public int anInt1004;
    public int anInt1005;
    public String aString1006;
    public DrawArea drawArea1;
    public DrawArea drawArea2;
    public DrawArea drawArea3;
    public DrawArea drawArea4;
    public DrawArea drawArea5;
    public DrawArea drawArea6;
    public DrawArea drawArea7;
    public DrawArea drawArea8;
    public DrawArea drawArea9;
    public DrawArea drawArea10;
    public static int anInt1017;
    public int anInt1018;
    public int anInt1019;
    public int anInt1020;
    public int anInt1021;
    public int anInt1022;
    public boolean aBoolean1023;
    public int[] anIntArray1024;
    public int anInt1025;
    public int anInt1026;
    public int anInt1027;
    public String aString1028;
    public DrawArea drawArea11;
    public DrawArea drawArea12;
    public DrawArea drawArea13;
    public DrawArea drawArea14;
    public DrawArea drawArea15;
    public DrawArea drawArea16;
    public DrawArea drawArea17;
    public DrawArea drawArea18;
    public DrawArea drawArea19;
    public Class38_Sub2_Sub2_Sub3[] aClass38_Sub2_Sub2_Sub3Array1038;
    public int anInt1039;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1040;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1041;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1042;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1043;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1044;
    public int[] anIntArray1045 = {
            0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff
    };
    public DrawArea drawArea20;
    public DrawArea drawArea21;
    public DrawArea drawArea22;
    public DrawArea drawArea23;
    public int anInt1050;
    public int anInt1051;
    public int anInt1052;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_1053;
    public int anInt1054;
    public boolean aBoolean1055;
    public LinkedList linkedList1;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_1057;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_1058;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_1059;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_1060;
    public int anInt1061;
    public static BigInteger aBigInteger1062 = new BigInteger(
            "7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
    public int anInt1063;
    public String aString1064;
    public int anInt1065;
    public String aString1066;
    public String aString1067;
    public byte aByte1068;
    public byte[] aByteArray1069;
    public boolean aBoolean1070;
    public int anInt1071;
    public int anInt1072;
    public static final int[] anIntArray1073 = {
            9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145,
            58654, 5027, 1457, 16565, 34991, 25486
    };
    public int anInt1074;
    public int[] anIntArray1075;
    public int anInt1076;
    public int anInt1077;
    public int anInt1078;
    public int[] anIntArray1079;
    public boolean aBoolean1080;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1081;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1082;
    public String aString1083;
    public String aString1084;
    public int anInt1085;
    public int anInt1086;
    public int anInt1087;
    public int anInt1088;
    public int anInt1089;
    public static int anInt1090;
    public int anInt1091;
    public int anInt1092;
    public int[] anIntArray1093;
    public int[] anIntArray1094;
    public int[] anIntArray1095;
    public int[] anIntArray1096;
    public int[] anIntArray1097;
    public int[] anIntArray1098;
    public int[] anIntArray1099;
    public String[] aStringArray1100;
    public int anInt1101;
    public static boolean aBoolean1102;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1103;
    public Class38_Sub2_Sub2_Sub3 aClass38_Sub2_Sub2_Sub3_1104;
    public int anInt1105;
    public int anInt1106;
    public final int[] anIntArray1107 = {
            0, 0, 0, 0, 1, 1, 1, 1, 1, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 3
    };
    public static int anInt1108;
    public int anInt1109;
    public int anInt1110;
    public int anInt1111;
    public int anInt1112;
    public int anInt1113;
    public int anInt1114;
    public int anInt1115;
    public static byte aByte1116 = 3;
    public int[] anIntArray1117;
    public int[][] anIntArrayArray1118;
    public String aString1119;
    public Class38_Sub2_Sub2_Sub2[] aClass38_Sub2_Sub2_Sub2Array1120;
    public boolean aBoolean1121;
    public byte aByte1122;
    public Object anObject1123;
    public int[] anIntArray1124;
    public int anInt1125;
    public int anInt1126;
    public String[] aStringArray1127;
    public int anInt1128;
    public int anInt1129;
    public int anInt1130;
    public int anInt1131;
    public int anInt1132;
    public int[] anIntArray1133;
    public int anInt1134;
    public int anInt1135;
    public FileArchive fileArchive;
    public String aString1137;
    public Class38_Sub2_Sub2_Sub2[] aClass38_Sub2_Sub2_Sub2Array1138;
    public int[] anIntArray1139;
    public int[] anIntArray1140;
    public int[] anIntArray1141;
    public int[] anIntArray1142;
    public int anInt1143;
    public boolean aBoolean1144;
    public Class38_Sub2_Sub2_Sub2 aClass38_Sub2_Sub2_Sub2_1145;
    public long aLong1146;
    public boolean aBoolean1147;
    public int anInt1148;
    public int anInt1149;
    public int anInt1150;
    public int anInt1151;
    public int anInt1152;
    public boolean aBoolean1153;
    public int anInt1154;
    public int anInt1155;
    public int anInt1156;
    public LinkedList[][][] linkedList3dArray;
    public int anInt1158;
    public int[] anIntArray1159;
    public int anInt1160;

    static {
        anIntArray864 = new int[99];
        int i = 0;
        for (int j = 0; j < 99; j++) {
            int k = j + 1;
            int l = (int) ((double) k + 300D * Math.pow(2D, (double) k / 7D));
            i += l;
            anIntArray864[j] = i / 4;
        }

    }
}
