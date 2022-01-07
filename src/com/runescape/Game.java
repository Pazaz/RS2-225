package com.runescape;

import com.runescape.cache.*;
import com.runescape.graphics.*;
import com.runescape.scene.*;
import com.runescape.sound.SoundTrack;
import com.runescape.util.*;
import net.burtleburtle.bob.rand.IsaacRandom;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.zip.CRC32;

public class Game extends GameShell {

    public void method14(int i, String s, int j) {
        if (s == null) {
            return;
        }

        synchronized (anObject1123) {
            aString1064 = s;
            anInt963 = i;
            anInt726 = j;
        }
    }

    public void method15(int i) {
        anInt1091 = 0;
        while (i >= 0)
            anInt883 = isaacState.nextInt();
        for (int j = -1; j < entityCount + npcCount; j++) {
            Object obj;
            if (j == -1)
                obj = self;
            else if (j < entityCount)
                obj = playerEntities[playerIndices[j]];
            else
                obj = npcEntities[npcIndices[j - entityCount]];
            if (obj != null && ((PathingEntity) (obj)).isValid(false)) {
                if (j < entityCount) {
                    int l = 30;
                    PlayerEntity playerEntity = (PlayerEntity) obj;
                    if (playerEntity.headicons != 0) {
                        method90(((PathingEntity) (obj)).height + 15, aBoolean860, ((PathingEntity) (obj)));
                        if (anInt1019 > -1) {
                            for (int l1 = 0; l1 < 8; l1++)
                                if ((playerEntity.headicons & 1 << l1) != 0) {
                                    aClass38_Sub2_Sub2_Sub2Array956[l1].draw(anInt1020 - l, anInt1019 - 12);
                                    l -= 25;
                                }

                        }
                    }
                    if (j >= 0 && anInt911 == 10 && anInt1076 == playerIndices[j]) {
                        method90(((PathingEntity) (obj)).height + 15, aBoolean860, ((PathingEntity) (obj)));
                        if (anInt1019 > -1)
                            aClass38_Sub2_Sub2_Sub2Array956[7].draw(anInt1020 - l, anInt1019 - 12);
                    }
                } else if (anInt911 == 1 && anInt801 == npcIndices[j - entityCount] && clientClock % 20 < 10) {
                    method90(((PathingEntity) (obj)).height + 15, aBoolean860, ((PathingEntity) (obj)));
                    if (anInt1019 > -1)
                        aClass38_Sub2_Sub2_Sub2Array956[2].draw(anInt1020 - 28, anInt1019 - 12);
                }
                if (((PathingEntity) (obj)).spoken != null && (j >= entityCount || anInt976 == 0 || anInt976 == 3
                        || anInt976 == 1 && method138(-20, ((PlayerEntity) obj).name))) {
                    method90(((PathingEntity) (obj)).height, aBoolean860, ((PathingEntity) (obj)));
                    if (anInt1019 > -1 && anInt1091 < anInt1092) {
                        anIntArray1096[anInt1091] = fontBold12.stringWidth(
                                ((PathingEntity) (obj)).spoken) / 2;
                        anIntArray1095[anInt1091] = fontBold12.height;
                        anIntArray1093[anInt1091] = anInt1019;
                        anIntArray1094[anInt1091] = anInt1020;
                        anIntArray1097[anInt1091] = ((PathingEntity) (obj)).spokenColor;
                        anIntArray1098[anInt1091] = ((PathingEntity) (obj)).spokenEffect;
                        anIntArray1099[anInt1091] = ((PathingEntity) (obj)).textCycle;
                        aStringArray1100[anInt1091++] = ((PathingEntity) (obj)).spoken;
                        if (anInt800 == 0 && ((PathingEntity) (obj)).spokenEffect == 1) {
                            anIntArray1095[anInt1091] += 10;
                            anIntArray1094[anInt1091] += 5;
                        }
                        if (anInt800 == 0 && ((PathingEntity) (obj)).spokenEffect == 2)
                            anIntArray1096[anInt1091] = 60;
                    }
                }
                if (((PathingEntity) (obj)).cycleStatus > clientClock + 100) {
                    method90(((PathingEntity) (obj)).height + 15, aBoolean860, ((PathingEntity) (obj)));
                    if (anInt1019 > -1) {
                        int i1 = (((PathingEntity) (obj)).currentHealth * 30) / ((PathingEntity) (obj)).maxHealth;
                        if (i1 > 30)
                            i1 = 30;
                        Draw2D.fillRect(anInt1020 - 3, anInt1019 - 15, 65280, i1, 5);
                        Draw2D.fillRect(anInt1020 - 3, (anInt1019 - 15) + i1, 0xff0000, 30 - i1,
                                5);
                    }
                }
                if (((PathingEntity) (obj)).cycleStatus > clientClock + 330) {
                    method90(((PathingEntity) (obj)).height / 2, aBoolean860, ((PathingEntity) (obj)));
                    if (anInt1019 > -1) {
                        aClass38_Sub2_Sub2_Sub2Array776[((PathingEntity) (obj)).damageType].draw(anInt1020 - 12,
                                anInt1019 - 12);
                        indexedFont1.drawRightAligned(anInt1020 + 4, 0,
                                String.valueOf(((PathingEntity) (obj)).damageTaken), anInt1019);
                        indexedFont1.drawRightAligned(anInt1020 + 3, 0xffffff,
                                String.valueOf(((PathingEntity) (obj)).damageTaken), anInt1019 - 1);
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
                    fontBold12.drawRightAligned(anInt1020 + 1, 0, s, anInt1019);
                    fontBold12.drawRightAligned(anInt1020, l2, s, anInt1019);
                }
                if (anIntArray1098[k] == 1) {
                    fontBold12.drawCenteredWave(anInt837, anInt1019, anInt1020 + 1, 0, s);
                    fontBold12.drawCenteredWave(anInt837, anInt1019, anInt1020, l2, s);
                }
                if (anIntArray1098[k] == 2) {
                    int l3 = fontBold12.stringWidth(s);
                    int i4 = ((150 - anIntArray1099[k]) * (l3 + 100)) / 150;
                    Draw2D.setBounds(334, 0, anInt1019 + 50, anInt1019 - 50);
                    fontBold12.draw((anInt1019 + 50) - i4, anInt1020 + 1, 0, s);
                    fontBold12.draw((anInt1019 + 50) - i4, anInt1020, l2, s);
                    Draw2D.resetBounds();
                }
            } else {
                fontBold12.drawRightAligned(anInt1020 + 1, 0, s, anInt1019);
                fontBold12.drawRightAligned(anInt1020, 0xffff00, s, anInt1019);
            }
        }

    }

    public void method16(byte byte0) {
        if (byte0 != -60)
            objects = null;
        outBuffer.writeOpcode(231);
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

    public void midistop() {
        Signlink.midifade = 0;
        Signlink.midi = "stop";
    }

    public void method18(int i) {
        int j = (self.x >> 7) + baseTileX;
        int k = (self.z >> 7) + baseTileZ;
        if (i != 39734)
            aBoolean912 = !aBoolean912;
        if (j >= 2944 && j < 3392 && k >= 3520 && k < 6400)
            wildernessLevel = 1 + (k - 3520) / 8;
        else if (j >= 2944 && j < 3392 && k >= 9920 && k < 12800)
            wildernessLevel = 1 + (k - 9920) / 8;
        else
            wildernessLevel = 0;
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
        IndexedFont indexedFont = indexedFont2;
        int j = 0;
        if (anInt957 != 0)
            j = 1;
        for (int k = 0; k < 100; k++)
            if (messageText[k] != null) {
                int l = anIntArray896[k];
                if ((l == 3 || l == 7)
                        && (l == 7 || anInt755 == 0 || anInt755 == 1 && method138(-20, aStringArray897[k]))) {
                    int i1 = 329 - j * 13;
                    indexedFont.draw(4, i1, 0,
                            "From " + aStringArray897[k] + ": " + messageText[k]);
                    indexedFont.draw(4, i1 - 1, 65535,
                            "From " + aStringArray897[k] + ": " + messageText[k]);
                    if (++j >= 5)
                        return;
                }
                if (l == 5 && anInt755 < 2) {
                    int j1 = 329 - j * 13;
                    indexedFont.draw(4, j1, 0, messageText[k]);
                    indexedFont.draw(4, j1 - 1, 65535, messageText[k]);
                    if (++j >= 5)
                        return;
                }
                if (l == 6 && anInt755 < 2) {
                    int k1 = 329 - j * 13;
                    indexedFont.draw(4, k1, 0,
                            "To " + aStringArray897[k] + ": " + messageText[k]);
                    indexedFont.draw(4, k1 - 1, 65535,
                            "To " + aStringArray897[k] + ": " + messageText[k]);
                    if (++j >= 5)
                        return;
                }
            }

    }

    public void updateNpcMasks(Buffer buffer) {
        for (int n = 0; n < updateCount; n++) {
            int nid = entityUpdateIndices[n];
            NpcEntity npc = npcEntities[nid];

            int mask = buffer.readByte();
            if ((mask & 2) == 2) {
                int j1 = buffer.readWord();
                if (j1 == 65535) {
                    j1 = -1;
                }

                if (j1 == npc.primarySeq) {
                    npc.primarySeqPlays = 0;
                }

                int l1 = buffer.readByte();
                if (j1 == -1 || npc.primarySeq == -1
                        || SeqType.animations[j1].priority > SeqType.animations[npc.primarySeq].priority
                        || SeqType.animations[npc.primarySeq].priority == 0) {
                    npc.primarySeq = j1;
                    npc.primarySeqFrame = 0;
                    npc.primarySeqCycle = 0;
                    npc.primarySeqDelay = l1;
                    npc.primarySeqPlays = 0;
                }
            }

            if ((mask & 4) == 4) {
                npc.targetEntity = buffer.readWord();
                if (npc.targetEntity == 65535) {
                    npc.targetEntity = -1;
                }
            }

            if ((mask & 8) == 8) {
                npc.spoken = buffer.readString();
                npc.textCycle = 100;
            }

            if ((mask & 0x10) == 16) {
                npc.damageTaken = buffer.readByte();
                npc.damageType = buffer.readByte();
                npc.cycleStatus = clientClock + 400;
                npc.currentHealth = buffer.readByte();
                npc.maxHealth = buffer.readByte();
            }

            if ((mask & 0x20) == 32) {
                npc.info = NpcType.get(buffer.readWord());
                npc.runSeq = npc.info.walkSeq;
                npc.walkSeq = npc.info.turnAroundSeq;
                npc.turnAroundSeq = npc.info.turnRightSeq;
                npc.turnRightSeq = npc.info.turnLeftSeq;
                npc.standSeq = npc.info.standSeq;
            }

            if ((mask & 0x40) == 64) {
                npc.spotAnimIndex = buffer.readWord();
                int k1 = buffer.readDWord();
                npc.spotAnimOffsetY = k1 >> 16;
                npc.lastSpotAnimCycle = clientClock + (k1 & 0xffff);
                npc.spotAnimFrame = 0;
                npc.spotAnimCycle = 0;
                if (npc.lastSpotAnimCycle > clientClock) {
                    npc.spotAnimFrame = -1;
                }

                if (npc.spotAnimIndex == 65535) {
                    npc.spotAnimIndex = -1;
                }
            }

            if ((mask & 0x80) == 128) {
                npc.focusX = buffer.readWord();
                npc.focusY = buffer.readWord();
            }
        }
    }

    public void method21(long l, byte byte0) {
        if (l == 0L)
            return;
        if (ignoreCount >= 100) {
            addMessage(0, "Your ignore list is full. Max of 100 hit", (byte) 4, "");
            return;
        }
        String s = StringUtils.formatName(StringUtils.fromBase37(l));
        for (int i = 0; i < ignoreCount; i++)
            if (ignoreName37[i] == l) {
                addMessage(0, s + " is already on your ignore list", (byte) 4, "");
                return;
            }

        for (int j = 0; j < friendCount; j++)
            if (friendName37[j] == l) {
                addMessage(0, "Please remove " + s + " from your friend list first", (byte) 4, "");
                return;
            }

        ignoreName37[ignoreCount++] = l;
        aBoolean964 = true;
        if (byte0 != 3) {
            return;
        } else {
            outBuffer.writeOpcode(79);
            outBuffer.writeQWord(l);
            return;
        }
    }

    public void method22(byte byte0, Buffer class38_sub2_sub3, int i) {
        if (byte0 != -45)
            method6();
        if (i == 59 || i == 76) {
            int j = class38_sub2_sub3.readByte();
            int k2 = anInt862 + (j >> 4 & 7);
            int l4 = anInt863 + (j & 7);
            int i7 = class38_sub2_sub3.readByte();
            int j9 = i7 >> 2;
            int j11 = i7 & 3;
            int i13 = anIntArray1107[j9];
            int i14;
            if (i == 76)
                i14 = -1;
            else
                i14 = class38_sub2_sub3.readWord();
            if (k2 >= 0 && l4 >= 0 && k2 < 104 && l4 < 104) {
                SpawnedLoc spawnedLoc = null;
                for (SpawnedLoc spawnedLoc_1 = (SpawnedLoc) spawnedLocations
                        .peekLast(); spawnedLoc_1 != null; spawnedLoc_1 = (SpawnedLoc) spawnedLocations
                        .getPrevious()) {
                    if (spawnedLoc_1.level != currentLevel || spawnedLoc_1.tileX != k2
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
                        i16 = scene.getWallBitset(currentLevel, k2, l4);
                    if (i13 == 1)
                        i16 = scene.getWallDecorationBitset(currentLevel, l4, k2);
                    if (i13 == 2)
                        i16 = scene.getLocationBitset(currentLevel, k2, l4);
                    if (i13 == 3)
                        i16 = scene.getGroundDecorationBitset(currentLevel, k2, l4);
                    if (i16 != 0) {
                        int i18 = scene.getInfo(currentLevel, k2, l4, i16);
                        l16 = i16 >> 14 & 0x7fff;
                        j17 = i18 & 0x1f;
                        l17 = i18 >> 6;
                    }
                    spawnedLoc = new SpawnedLoc();
                    spawnedLoc.level = currentLevel;
                    spawnedLoc.classType = i13;
                    spawnedLoc.tileX = k2;
                    spawnedLoc.tileZ = l4;
                    spawnedLoc.lastLocIndex = l16;
                    spawnedLoc.lastType = j17;
                    spawnedLoc.lastRotation = l17;
                    spawnedLocations.pushNext(spawnedLoc);
                }
                spawnedLoc.locIndex = i14;
                spawnedLoc.type = j9;
                spawnedLoc.rotation = j11;
                method99(j11, k2, l4, i13, i14, j9, -27819, currentLevel);
            }
            return;
        }
        if (i == 42) {
            int k = class38_sub2_sub3.readByte();
            int l2 = anInt862 + (k >> 4 & 7);
            int i5 = anInt863 + (k & 7);
            int j7 = class38_sub2_sub3.readByte();
            int k9 = j7 >> 2;
            int k11 = anIntArray1107[k9];
            int j13 = class38_sub2_sub3.readWord();
            if (l2 >= 0 && i5 >= 0 && l2 < 104 && i5 < 104) {
                int j14 = 0;
                if (k11 == 0)
                    j14 = scene.getWallBitset(currentLevel, l2, i5);
                if (k11 == 1)
                    j14 = scene.getWallDecorationBitset(currentLevel, i5, l2);
                if (k11 == 2)
                    j14 = scene.getLocationBitset(currentLevel, l2, i5);
                if (k11 == 3)
                    j14 = scene.getGroundDecorationBitset(currentLevel, l2, i5);
                if (j14 != 0) {
                    LocEntity locEntity = new LocEntity(false, j14 >> 14 & 0x7fff, currentLevel, k11,
                            SeqType.animations[j13], i5, l2);
                    linkedList2.pushNext(locEntity);
                }
            }
            return;
        }
        if (i == 223) {
            int l = class38_sub2_sub3.readByte();
            int i3 = anInt862 + (l >> 4 & 7);
            int j5 = anInt863 + (l & 7);
            int k7 = class38_sub2_sub3.readWord();
            int l9 = class38_sub2_sub3.readWord();
            if (i3 >= 0 && j5 >= 0 && i3 < 104 && j5 < 104) {
                ObjStackEntity objStackEntity = new ObjStackEntity();
                objStackEntity.model = k7;
                objStackEntity.amount = l9;
                if (objects[currentLevel][i3][j5] == null)
                    objects[currentLevel][i3][j5] = new LinkedList();
                objects[currentLevel][i3][j5].pushNext(objStackEntity);
                method123(i3, j5);
            }
            return;
        }
        if (i == 49) {
            int i1 = class38_sub2_sub3.readByte();
            int j3 = anInt862 + (i1 >> 4 & 7);
            int k5 = anInt863 + (i1 & 7);
            int l7 = class38_sub2_sub3.readWord();
            if (j3 >= 0 && k5 >= 0 && j3 < 104 && k5 < 104) {
                LinkedList linkedList = objects[currentLevel][j3][k5];
                if (linkedList != null) {
                    for (ObjStackEntity objStackEntity_1 = (ObjStackEntity) linkedList
                            .peekLast(); objStackEntity_1 != null; objStackEntity_1 = (ObjStackEntity) linkedList
                            .getPrevious()) {
                        if (objStackEntity_1.model != (l7 & 0x7fff))
                            continue;
                        objStackEntity_1.unlink();
                        break;
                    }

                    if (linkedList.peekLast() == null)
                        objects[currentLevel][j3][k5] = null;
                    method123(j3, k5);
                }
            }
            return;
        }
        if (i == 69) {
            int j1 = class38_sub2_sub3.readByte();
            int k3 = anInt862 + (j1 >> 4 & 7);
            int l5 = anInt863 + (j1 & 7);
            int i8 = k3 + class38_sub2_sub3.readByteSigned();
            int i10 = l5 + class38_sub2_sub3.readByteSigned();
            int l11 = class38_sub2_sub3.readWordSigned();
            int k13 = class38_sub2_sub3.readWord();
            int k14 = class38_sub2_sub3.readByte();
            int i15 = class38_sub2_sub3.readByte();
            int k15 = class38_sub2_sub3.readWord();
            int j16 = class38_sub2_sub3.readWord();
            int i17 = class38_sub2_sub3.readByte();
            int k17 = class38_sub2_sub3.readByte();
            if (k3 >= 0 && l5 >= 0 && k3 < 104 && l5 < 104 && i8 >= 0 && i10 >= 0 && i8 < 104 && i10 < 104) {
                k3 = k3 * 128 + 64;
                l5 = l5 * 128 + 64;
                i8 = i8 * 128 + 64;
                i10 = i10 * 128 + 64;
                ProjectileEntity projectileEntity = new ProjectileEntity(i15, i17, l5, j16 + clientClock, currentLevel, l11,
                        k15 + clientClock, k17, method33(currentLevel, k3, (byte) 5, l5) - k14, k13, k3);
                projectileEntity.setTarget(method33(currentLevel, i8, (byte) 5, i10) - i15, i10, i8, k15 + clientClock);
                projectiles.pushNext(projectileEntity);
            }
            return;
        }
        if (i == 191) {
            int k1 = class38_sub2_sub3.readByte();
            int l3 = anInt862 + (k1 >> 4 & 7);
            int i6 = anInt863 + (k1 & 7);
            int j8 = class38_sub2_sub3.readWord();
            int j10 = class38_sub2_sub3.readByte();
            int i12 = class38_sub2_sub3.readWord();
            if (l3 >= 0 && i6 >= 0 && l3 < 104 && i6 < 104) {
                l3 = l3 * 128 + 64;
                i6 = i6 * 128 + 64;
                SpotAnimEntity spotAnimEntity = new SpotAnimEntity(l3, j8, i6, i12,
                        method33(currentLevel, l3, (byte) 5, i6) - j10, currentLevel, clientClock);
                spotanims.pushNext(spotAnimEntity);
            }
            return;
        }
        if (i == 50) {
            int l1 = class38_sub2_sub3.readByte();
            int i4 = anInt862 + (l1 >> 4 & 7);
            int j6 = anInt863 + (l1 & 7);
            int k8 = class38_sub2_sub3.readWord();
            int k10 = class38_sub2_sub3.readWord();
            int j12 = class38_sub2_sub3.readWord();
            if (i4 >= 0 && j6 >= 0 && i4 < 104 && j6 < 104 && j12 != anInt734) {
                ObjStackEntity objStackEntity_2 = new ObjStackEntity();
                objStackEntity_2.model = k8;
                objStackEntity_2.amount = k10;
                if (objects[currentLevel][i4][j6] == null)
                    objects[currentLevel][i4][j6] = new LinkedList();
                objects[currentLevel][i4][j6].pushNext(objStackEntity_2);
                method123(i4, j6);
            }
            return;
        }
        if (i == 23) {
            int i2 = class38_sub2_sub3.readByte();
            int j4 = anInt862 + (i2 >> 4 & 7);
            int k6 = anInt863 + (i2 & 7);
            int l8 = class38_sub2_sub3.readByte();
            int l10 = l8 >> 2;
            int k12 = l8 & 3;
            int l13 = anIntArray1107[l10];
            int l14 = class38_sub2_sub3.readWord();
            int j15 = class38_sub2_sub3.readWord();
            int l15 = class38_sub2_sub3.readWord();
            int k16 = class38_sub2_sub3.readWord();
            byte byte1 = class38_sub2_sub3.readByteSigned();
            byte byte2 = class38_sub2_sub3.readByteSigned();
            byte byte3 = class38_sub2_sub3.readByteSigned();
            byte byte4 = class38_sub2_sub3.readByteSigned();
            PlayerEntity playerEntity;
            if (k16 == anInt734)
                playerEntity = self;
            else
                playerEntity = playerEntities[k16];
            if (playerEntity != null) {
                TemporaryLoc temporaryLoc = new TemporaryLoc(currentLevel, k12, k6, j15 + clientClock, l10, -1, j4, l13);
                temporaryLocs.pushNext(temporaryLoc);
                TemporaryLoc temporaryLoc_1 = new TemporaryLoc(currentLevel, k12, k6, l15 + clientClock, l10, l14, j4, l13);
                temporaryLocs.pushNext(temporaryLoc_1);
                int j18 = anIntArrayArrayArray794[currentLevel][j4][k6];
                int k18 = anIntArrayArrayArray794[currentLevel][j4 + 1][k6];
                int l18 = anIntArrayArrayArray794[currentLevel][j4 + 1][k6 + 1];
                int i19 = anIntArrayArrayArray794[currentLevel][j4][k6 + 1];
                LocType locType = LocType.get(l14);
                playerEntity.locFirstCycle = j15 + clientClock;
                playerEntity.locLastCycle = l15 + clientClock;
                playerEntity.locModel = locType.getModel(l10, k12, j18, k18, l18, i19, -1);
                int j19 = locType.sizeX;
                int k19 = locType.sizeZ;
                if (k12 == 1 || k12 == 3) {
                    j19 = locType.sizeZ;
                    k19 = locType.sizeX;
                }
                playerEntity.locSceneX = j4 * 128 + j19 * 64;
                playerEntity.locSceneZ = k6 * 128 + k19 * 64;
                playerEntity.locSceneY = method33(currentLevel, playerEntity.locSceneX, (byte) 5,
                        playerEntity.locSceneZ);
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
                playerEntity.locMinTileX = j4 + byte1;
                playerEntity.locMaxTileX = j4 + byte3;
                playerEntity.locMinTileZ = k6 + byte2;
                playerEntity.locMaxTileZ = k6 + byte4;
            }
        }
        if (i == 151) {
            int j2 = class38_sub2_sub3.readByte();
            int k4 = anInt862 + (j2 >> 4 & 7);
            int l6 = anInt863 + (j2 & 7);
            int i9 = class38_sub2_sub3.readWord();
            int i11 = class38_sub2_sub3.readWord();
            int l12 = class38_sub2_sub3.readWord();
            if (k4 >= 0 && l6 >= 0 && k4 < 104 && l6 < 104) {
                LinkedList linkedList_1 = objects[currentLevel][k4][l6];
                if (linkedList_1 != null) {
                    for (ObjStackEntity objStackEntity_3 = (ObjStackEntity) linkedList_1
                            .peekLast(); objStackEntity_3 != null; objStackEntity_3 = (ObjStackEntity) linkedList_1
                            .getPrevious()) {
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
            anInt780 = inBuffer.readByte();
        int i = 3;
        if (anInt1114 < 310) {
            int j = anInt1111 >> 7;
            int k = anInt1113 >> 7;
            int l = self.x >> 7;
            int i1 = self.z >> 7;
            if ((aByteArrayArrayArray840[currentLevel][j][k] & 4) != 0)
                i = currentLevel;
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
                    if ((aByteArrayArrayArray840[currentLevel][j][k] & 4) != 0)
                        i = currentLevel;
                    j2 += l1;
                    if (j2 >= 0x10000) {
                        j2 -= 0x10000;
                        if (k < i1)
                            k++;
                        else if (k > i1)
                            k--;
                        if ((aByteArrayArrayArray840[currentLevel][j][k] & 4) != 0)
                            i = currentLevel;
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
                    if ((aByteArrayArrayArray840[currentLevel][j][k] & 4) != 0)
                        i = currentLevel;
                    k2 += i2;
                    if (k2 >= 0x10000) {
                        k2 -= 0x10000;
                        if (j < l)
                            j++;
                        else if (j > l)
                            j--;
                        if ((aByteArrayArrayArray840[currentLevel][j][k] & 4) != 0)
                            i = currentLevel;
                    }
                }
            }
        }
        if ((aByteArrayArrayArray840[currentLevel][self.x >> 7][self.z >> 7]
                & 4) != 0)
            i = currentLevel;
        return i;
    }

    public int method24(int i) {
        int j = method33(currentLevel, anInt1111, (byte) 5, anInt1113);
        anInt779 += i;
        if (j - anInt1112 < 800 && (aByteArrayArrayArray840[currentLevel][anInt1111 >> 7][anInt1113 >> 7] & 4) != 0)
            return currentLevel;
        else
            return 3;
    }

    public void method25(int i) {
        anInt837++;
        method32(284);
        method53(false);
        anInt779 += i;
        method86((byte) -26);
        method105();
        method129(254);
        if (!aBoolean968) {
            int j = anInt816;
            if (anInt932 / 256 > j)
                j = anInt932 / 256;
            if (aBooleanArray754[4] && anIntArray966[4] + 128 > j)
                j = anIntArray966[4] + 128;
            int l = cameraYaw + cameraAnticheatAngle & 0x7ff;
            method39(
                    method33(currentLevel, self.x, (byte) 5,
                            self.z) - 50,
                    anInt914, l, j, 16418, anInt915, 600 + j * 3);
            anInt804++;
            if (anInt804 > 1802) {
                anInt804 = 0;
                outBuffer.writeOpcode(146);
                outBuffer.writeByte(0);
                int j1 = outBuffer.offset;
                outBuffer.writeWord(29711);
                outBuffer.writeByte(70);
                outBuffer.writeByte((int) (Math.random() * 256D));
                outBuffer.writeByte(242);
                outBuffer.writeByte(186);
                outBuffer.writeByte(39);
                outBuffer.writeByte(61);
                if ((int) (Math.random() * 2D) == 0)
                    outBuffer.writeByte(13);
                if ((int) (Math.random() * 2D) == 0)
                    outBuffer.writeWord(57856);
                outBuffer.writeWord((int) (Math.random() * 65536D));
                outBuffer.writeSize(outBuffer.offset - j1);
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

        int i3 = Draw3D.cycle;
        Model.aBoolean1295 = true;
        Model.anInt1298 = 0;
        Model.anInt1296 = super.anInt21 - 8;
        Model.anInt1297 = super.anInt22 - 11;
        Draw2D.clear();
        scene.draw(anInt1115, anInt1111, k, anInt1114, anInt1112, anInt1113);
        scene.clearFrameLocs();
        method15(anInt805);
        method133();
        method41(i3, true);
        method84(9);
        drawArea22.drawImage(11, super.graphics, 8);
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
                byte[] abyte0 = Signlink.cacheload(s + ".mid");
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
                        Signlink.cachesave(s + ".mid", abyte0);
                    } catch (Exception _ex) {
                    }
                if (abyte0 == null)
                    return;
                int l = (new Buffer(abyte0)).readDWord();
                byte[] abyte1 = new byte[l];
                BZip2InputStream.read(abyte1, l, abyte0, j, 4);
                method52(abyte1, 625, l, true);
            }
        }
    }

    public static void setLowMemory() {
        Scene.lowMemory = true;
        Draw3D.lowMemory = true;
        lowMemory = true;
        SceneBuilder.lowMemory = true;
    }

    public void method28(boolean flag) {
        char c = '\u0100';
        if (anInt874 > 0) {
            for (int i = 0; i < 256; i++)
                if (anInt874 > 768)
                    flameGradient[i] = method88(flameGradient0[i], 1024 - anInt874, flameGradient1[i], 997);
                else if (anInt874 > 256)
                    flameGradient[i] = flameGradient1[i];
                else
                    flameGradient[i] = method88(flameGradient1[i], 256 - anInt874, flameGradient0[i], 997);

        } else if (anInt875 > 0) {
            for (int j = 0; j < 256; j++)
                if (anInt875 > 768)
                    flameGradient[j] = method88(flameGradient0[j], 1024 - anInt875, flameGradient2[j], 997);
                else if (anInt875 > 256)
                    flameGradient[j] = flameGradient2[j];
                else
                    flameGradient[j] = method88(flameGradient2[j], 256 - anInt875, flameGradient0[j], 997);

        } else {
            for (int k = 0; k < 256; k++)
                flameGradient[k] = flameGradient0[k];

        }
        for (int l = 0; l < 33920; l++)
            imageTitle0.pixels[l] = imageFlamesLeft.pixels[l];

        int i1 = 0;
        int j1 = 1152;
        for (int k1 = 1; k1 < c - 1; k1++) {
            int l1 = (anIntArray850[k1] * (c - k1)) / c;
            int j2 = 22 + l1;
            if (j2 < 0)
                j2 = 0;
            i1 += j2;
            for (int l2 = j2; l2 < 128; l2++) {
                int j3 = flameBuffer3[i1++];
                if (j3 != 0) {
                    int l3 = j3;
                    int j4 = 256 - j3;
                    j3 = flameGradient[j3];
                    int l4 = imageTitle0.pixels[j1];
                    imageTitle0.pixels[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00)
                            + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            j1 += j2;
        }

        imageTitle0.drawImage(0, super.graphics, 0);
        for (int i2 = 0; i2 < 33920; i2++)
            imageTitle1.pixels[i2] = imageFlamesRight.pixels[i2];

        i1 = 0;
        j1 = 1176;
        for (int k2 = 1; k2 < c - 1; k2++) {
            int i3 = (anIntArray850[k2] * (c - k2)) / c;
            int k3 = 103 - i3;
            j1 += i3;
            for (int i4 = 0; i4 < k3; i4++) {
                int k4 = flameBuffer3[i1++];
                if (k4 != 0) {
                    int i5 = k4;
                    int j5 = 256 - k4;
                    k4 = flameGradient[k4];
                    int k5 = imageTitle1.pixels[j1];
                    imageTitle1.pixels[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00)
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
        imageTitle1.drawImage(0, super.graphics, 661);
    }

    public void method29(int i, int j, int k, InterfaceComponent interfaceComponent, int l, int i1, int j1) {
        if (interfaceComponent.anInt271 != 0 || interfaceComponent.anIntArray285 == null || interfaceComponent.aBoolean284)
            return;
        if (j < i1 || i < k || j > i1 + interfaceComponent.anInt274 || i > k + interfaceComponent.anInt275)
            return;
        int k1 = interfaceComponent.anIntArray285.length;
        if (l != 5082)
            anInt780 = inBuffer.readByte();
        for (int l1 = 0; l1 < k1; l1++) {
            int i2 = interfaceComponent.anIntArray286[l1] + i1;
            int j2 = (interfaceComponent.anIntArray287[l1] + k) - j1;
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[interfaceComponent.anIntArray285[l1]];
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
                        options[optionCount] = interfaceComponent_1.aString320;
                        actions[optionCount] = 951;
                        paramB[optionCount] = interfaceComponent_1.anInt269;
                        optionCount++;
                    }
                }
                if (interfaceComponent_1.anInt272 == 2 && selectedSpell == 0 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    String s = interfaceComponent_1.aString317;
                    if (s.indexOf(" ") != -1)
                        s = s.substring(0, s.indexOf(" "));
                    options[optionCount] = s + " @gre@" + interfaceComponent_1.aString318;
                    actions[optionCount] = 930;
                    paramB[optionCount] = interfaceComponent_1.anInt269;
                    optionCount++;
                }
                if (interfaceComponent_1.anInt272 == 3 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    options[optionCount] = "Close";
                    actions[optionCount] = 947;
                    paramB[optionCount] = interfaceComponent_1.anInt269;
                    optionCount++;
                }
                if (interfaceComponent_1.anInt272 == 4 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    options[optionCount] = interfaceComponent_1.aString320;
                    actions[optionCount] = 465;
                    paramB[optionCount] = interfaceComponent_1.anInt269;
                    optionCount++;
                }
                if (interfaceComponent_1.anInt272 == 5 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    options[optionCount] = interfaceComponent_1.aString320;
                    actions[optionCount] = 960;
                    paramB[optionCount] = interfaceComponent_1.anInt269;
                    optionCount++;
                }
                if (interfaceComponent_1.anInt272 == 6 && !aBoolean872 && j >= i2 && i >= j2 && j < i2 + interfaceComponent_1.anInt274
                        && i < j2 + interfaceComponent_1.anInt275) {
                    options[optionCount] = interfaceComponent_1.aString320;
                    actions[optionCount] = 44;
                    paramB[optionCount] = interfaceComponent_1.anInt269;
                    optionCount++;
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
                                    ObjType objType = ObjType.get(interfaceComponent_1.anIntArray265[k2] - 1);
                                    if (selectedObject == 1 && interfaceComponent_1.aBoolean291) {
                                        if (interfaceComponent_1.anInt269 != anInt1004 || k2 != anInt1003) {
                                            options[optionCount] = "Use " + selectedObjName + " with @lre@"
                                                    + objType.name;
                                            actions[optionCount] = 881;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = k2;
                                            paramB[optionCount] = interfaceComponent_1.anInt269;
                                            optionCount++;
                                        }
                                    } else if (selectedSpell == 1 && interfaceComponent_1.aBoolean291) {
                                        if ((selectedFlags & 0x10) == 16) {
                                            options[optionCount] = selectedSpellPrefix + " @lre@" + objType.name;
                                            actions[optionCount] = 391;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = k2;
                                            paramB[optionCount] = interfaceComponent_1.anInt269;
                                            optionCount++;
                                        }
                                    } else {
                                        if (interfaceComponent_1.aBoolean291) {
                                            for (int l3 = 4; l3 >= 3; l3--)
                                                if (objType.options != null
                                                        && objType.options[l3] != null) {
                                                    options[optionCount] = objType.options[l3] + " @lre@"
                                                            + objType.name;
                                                    if (l3 == 3)
                                                        actions[optionCount] = 478;
                                                    if (l3 == 4)
                                                        actions[optionCount] = 347;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = k2;
                                                    paramB[optionCount] = interfaceComponent_1.anInt269;
                                                    optionCount++;
                                                } else if (l3 == 4) {
                                                    options[optionCount] = "Drop @lre@" + objType.name;
                                                    actions[optionCount] = 347;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = k2;
                                                    paramB[optionCount] = interfaceComponent_1.anInt269;
                                                    optionCount++;
                                                }

                                        }
                                        if (interfaceComponent_1.aBoolean292) {
                                            options[optionCount] = "Use @lre@" + objType.name;
                                            actions[optionCount] = 188;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = k2;
                                            paramB[optionCount] = interfaceComponent_1.anInt269;
                                            optionCount++;
                                        }
                                        if (interfaceComponent_1.aBoolean291 && objType.options != null) {
                                            for (int i4 = 2; i4 >= 0; i4--)
                                                if (objType.options[i4] != null) {
                                                    options[optionCount] = objType.options[i4] + " @lre@"
                                                            + objType.name;
                                                    if (i4 == 0)
                                                        actions[optionCount] = 405;
                                                    if (i4 == 1)
                                                        actions[optionCount] = 38;
                                                    if (i4 == 2)
                                                        actions[optionCount] = 422;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = k2;
                                                    paramB[optionCount] = interfaceComponent_1.anInt269;
                                                    optionCount++;
                                                }

                                        }
                                        if (interfaceComponent_1.aStringArray298 != null) {
                                            for (int j4 = 4; j4 >= 0; j4--)
                                                if (interfaceComponent_1.aStringArray298[j4] != null) {
                                                    options[optionCount] = interfaceComponent_1.aStringArray298[j4]
                                                            + " @lre@" + objType.name;
                                                    if (j4 == 0)
                                                        actions[optionCount] = 602;
                                                    if (j4 == 1)
                                                        actions[optionCount] = 596;
                                                    if (j4 == 2)
                                                        actions[optionCount] = 22;
                                                    if (j4 == 3)
                                                        actions[optionCount] = 892;
                                                    if (j4 == 4)
                                                        actions[optionCount] = 415;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = k2;
                                                    paramB[optionCount] = interfaceComponent_1.anInt269;
                                                    optionCount++;
                                                }

                                        }
                                        options[optionCount] = "Examine @lre@" + objType.name;
                                        actions[optionCount] = 1773;
                                        paramC[optionCount] = objType.index;
                                        paramB[optionCount] = interfaceComponent_1.anIntArray266[k2];
                                        optionCount++;
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
                outBuffer.writeOpcode(244);
                outBuffer.writeByte(anInt976);
                outBuffer.writeByte(anInt755);
                outBuffer.writeByte(anInt885);
            }
            if (super.anInt24 >= 137 && super.anInt24 <= 237 && super.anInt25 >= 490 && super.anInt25 <= 522) {
                anInt755 = (anInt755 + 1) % 3;
                aBoolean921 = true;
                aBoolean965 = true;
                outBuffer.writeOpcode(244);
                outBuffer.writeByte(anInt976);
                outBuffer.writeByte(anInt755);
                outBuffer.writeByte(anInt885);
            }
            if (super.anInt24 >= 275 && super.anInt24 <= 375 && super.anInt25 >= 490 && super.anInt25 <= 522) {
                anInt885 = (anInt885 + 1) % 3;
                aBoolean921 = true;
                aBoolean965 = true;
                outBuffer.writeOpcode(244);
                outBuffer.writeByte(anInt976);
                outBuffer.writeByte(anInt755);
                outBuffer.writeByte(anInt885);
            }
            if (super.anInt24 >= 416 && super.anInt24 <= 516 && super.anInt25 >= 490 && super.anInt25 <= 522) {
                method16((byte) -60);
                aString970 = "";
                aBoolean881 = false;
                for (int j = 0; j < InterfaceComponent.instances.length; j++)
                    if (InterfaceComponent.instances[j] != null && InterfaceComponent.instances[j].anInt273 == 600) {
                        anInt907 = anInt971 = InterfaceComponent.instances[j].anInt270;
                        return;
                    }

            }
        }
    }

    public void method31(int i, int j, int k) {
        int l = 0;
        for (int i1 = 0; i1 < 100; i1++) {
            if (messageText[i1] == null)
                continue;
            int j1 = anIntArray896[i1];
            int k1 = (70 - l * 14) + anInt977 + 4;
            if (k1 < -20)
                break;
            if (j1 == 0)
                l++;
            if ((j1 == 1 || j1 == 2)
                    && (j1 == 1 || anInt976 == 0 || anInt976 == 1 && method138(-20, aStringArray897[i1]))) {
                if (i > k1 - 14 && i <= k1 && !aStringArray897[i1].equals(self.name)) {
                    if (rights) {
                        options[optionCount] = "Report abuse @whi@" + aStringArray897[i1];
                        actions[optionCount] = 34;
                        optionCount++;
                    }
                    options[optionCount] = "Add ignore @whi@" + aStringArray897[i1];
                    actions[optionCount] = 436;
                    optionCount++;
                    options[optionCount] = "Add friend @whi@" + aStringArray897[i1];
                    actions[optionCount] = 406;
                    optionCount++;
                }
                l++;
            }
            if ((j1 == 3 || j1 == 7) && anInt833 == 0
                    && (j1 == 7 || anInt755 == 0 || anInt755 == 1 && method138(-20, aStringArray897[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    if (rights) {
                        options[optionCount] = "Report abuse @whi@" + aStringArray897[i1];
                        actions[optionCount] = 34;
                        optionCount++;
                    }
                    options[optionCount] = "Add ignore @whi@" + aStringArray897[i1];
                    actions[optionCount] = 436;
                    optionCount++;
                    options[optionCount] = "Add friend @whi@" + aStringArray897[i1];
                    actions[optionCount] = 406;
                    optionCount++;
                }
                l++;
            }
            if (j1 == 4 && (anInt885 == 0 || anInt885 == 1 && method138(-20, aStringArray897[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    options[optionCount] = "Accept trade @whi@" + aStringArray897[i1];
                    actions[optionCount] = 903;
                    optionCount++;
                }
                l++;
            }
            if ((j1 == 5 || j1 == 6) && anInt833 == 0 && anInt755 < 2)
                l++;
            if (j1 == 8 && (anInt885 == 0 || anInt885 == 1 && method138(-20, aStringArray897[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    options[optionCount] = "Accept duel @whi@" + aStringArray897[i1];
                    actions[optionCount] = 363;
                    optionCount++;
                }
                l++;
            }
        }

        anInt779 += j;
    }

    public void method32(int i) {
        if (self.x >> 7 == flagTileX
                && self.z >> 7 == flagTileY)
            flagTileX = 0;
        for (int j = -1; j < entityCount; j++) {
            PlayerEntity playerEntity;
            int k;
            if (j == -1) {
                playerEntity = self;
                k = LOCAL_PLAYER_INDEX << 14;
            } else {
                playerEntity = playerEntities[playerIndices[j]];
                k = playerIndices[j] << 14;
            }
            if (playerEntity == null || !playerEntity.isValid(false))
                continue;
            playerEntity.lowMemory = (lowMemory && entityCount > 50 || entityCount > 200) && j != -1
                    && playerEntity.secondarySeq == playerEntity.standSeq;
            int l = playerEntity.x >> 7;
            int i1 = playerEntity.z >> 7;
            if (l < 0 || l >= 104 || i1 < 0 || i1 >= 104)
                continue;
            if (playerEntity.locModel != null && clientClock >= playerEntity.locFirstCycle
                    && clientClock < playerEntity.locLastCycle) {
                playerEntity.lowMemory = false;
                playerEntity.y = method33(currentLevel,
                        playerEntity.x, (byte) 5,
                        playerEntity.z);
                scene.add(playerEntity.locMaxTileX, null,
                        playerEntity.z, playerEntity.y, k,
                        playerEntity.animationDelay, playerEntity.locMinTileZ,
                        playerEntity.locMinTileX, playerEntity, currentLevel,
                        playerEntity.locMaxTileZ, playerEntity.x);
                continue;
            }
            if ((playerEntity.x & 0x7f) == 64
                    && (playerEntity.z & 0x7f) == 64) {
                if (anIntArrayArray920[l][i1] == anInt837)
                    continue;
                anIntArrayArray920[l][i1] = anInt837;
            }
            playerEntity.y = method33(currentLevel,
                    playerEntity.x, (byte) 5,
                    playerEntity.z);
            scene.add(playerEntity.z, 60,
                    playerEntity.animationDelay,
                    playerEntity.x, k,
                    playerEntity.animationStretches, null, playerEntity,
                    playerEntity.y, currentLevel);
        }

        if (i <= 0)
            outBuffer.writeByte(210);
    }

    public int method33(int i, int j, byte byte0, int k) {
        if (byte0 != 5)
            anInt780 = inBuffer.readByte();
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
        if (optionCount >= 400)
            return;
        String s = npcType.name;
        if (npcType.level != 0)
            s = s + method72(self.combatLevel, false, npcType.level) + " (level-"
                    + npcType.level + ")";
        if (selectedObject == 1) {
            options[optionCount] = "Use " + selectedObjName + " with @yel@" + s;
            actions[optionCount] = 900;
            paramC[optionCount] = l;
            paramA[optionCount] = k;
            paramB[optionCount] = j;
            optionCount++;
            return;
        }
        if (selectedSpell == 1) {
            if ((selectedFlags & 2) == 2) {
                options[optionCount] = selectedSpellPrefix + " @yel@" + s;
                actions[optionCount] = 265;
                paramC[optionCount] = l;
                paramA[optionCount] = k;
                paramB[optionCount] = j;
                optionCount++;
                return;
            }
        } else {
            if (npcType.options != null) {
                for (int i1 = 4; i1 >= 0; i1--)
                    if (npcType.options[i1] != null && !npcType.options[i1].equalsIgnoreCase("attack")) {
                        options[optionCount] = npcType.options[i1] + " @yel@" + s;
                        if (i1 == 0)
                            actions[optionCount] = 728;
                        if (i1 == 1)
                            actions[optionCount] = 542;
                        if (i1 == 2)
                            actions[optionCount] = 6;
                        if (i1 == 3)
                            actions[optionCount] = 963;
                        if (i1 == 4)
                            actions[optionCount] = 245;
                        paramC[optionCount] = l;
                        paramA[optionCount] = k;
                        paramB[optionCount] = j;
                        optionCount++;
                    }

            }
            if (npcType.options != null) {
                for (int j1 = 4; j1 >= 0; j1--)
                    if (npcType.options[j1] != null && npcType.options[j1].equalsIgnoreCase("attack")) {
                        char c = '\0';
                        if (npcType.level > self.combatLevel)
                            c = '\u07D0';
                        options[optionCount] = npcType.options[j1] + " @yel@" + s;
                        if (j1 == 0)
                            actions[optionCount] = 728 + c;
                        if (j1 == 1)
                            actions[optionCount] = 542 + c;
                        if (j1 == 2)
                            actions[optionCount] = 6 + c;
                        if (j1 == 3)
                            actions[optionCount] = 963 + c;
                        if (j1 == 4)
                            actions[optionCount] = 245 + c;
                        paramC[optionCount] = l;
                        paramA[optionCount] = k;
                        paramB[optionCount] = j;
                        optionCount++;
                    }

            }
            options[optionCount] = "Examine @yel@" + s;
            actions[optionCount] = 1607;
            paramC[optionCount] = l;
            paramA[optionCount] = k;
            paramB[optionCount] = j;
            optionCount++;
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
                    if (anInt760 == 2 && friendCount > 0) {
                        long l1 = StringUtils.toBase37(aString765);
                        method113(43808, l1);
                    }
                    if (anInt760 == 3 && aString765.length() > 0) {
                        outBuffer.writeOpcode(148);
                        outBuffer.writeByte(0);
                        int k = outBuffer.offset;
                        outBuffer.writeQWord(aLong900);
                        TextEncoder.write(outBuffer, aString765);
                        outBuffer.writeSize(outBuffer.offset - k);
                        aString765 = StringUtils.toSentence(aString765);
                        aString765 = WordEncoding.getFiltered(aString765);
                        addMessage(6, aString765, (byte) 4, StringUtils.formatName(StringUtils.fromBase37(aLong900)));
                        if (anInt755 == 2) {
                            anInt755 = 1;
                            aBoolean921 = true;
                            outBuffer.writeOpcode(244);
                            outBuffer.writeByte(anInt976);
                            outBuffer.writeByte(anInt755);
                            outBuffer.writeByte(anInt885);
                        }
                    }
                    if (anInt760 == 4 && ignoreCount < 100) {
                        long l2 = StringUtils.toBase37(aString765);
                        method21(l2, (byte) 3);
                    }
                    if (anInt760 == 5 && ignoreCount > 0) {
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
                        outBuffer.writeOpcode(237);
                        outBuffer.writeDWord(i1);
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
                        method121();
                    else if (aString1137.startsWith("::")) {
                        outBuffer.writeOpcode(4);
                        outBuffer.writeByte(aString1137.length() - 1);
                        outBuffer.writeString(aString1137.substring(2));
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
                        outBuffer.writeOpcode(158);
                        outBuffer.writeByte(0);
                        int i2 = outBuffer.offset;
                        outBuffer.writeByte(j1);
                        outBuffer.writeByte(k1);
                        TextEncoder.write(outBuffer, aString1137);
                        outBuffer.writeSize(outBuffer.offset - i2);
                        aString1137 = StringUtils.toSentence(aString1137);
                        aString1137 = WordEncoding.getFiltered(aString1137);
                        self.spoken = aString1137;
                        self.spokenColor = j1;
                        self.spokenEffect = k1;
                        self.textCycle = 150;
                        addMessage(2, self.spoken, (byte) 4,
                                self.name);
                        if (anInt976 == 2) {
                            anInt976 = 3;
                            aBoolean921 = true;
                            outBuffer.writeOpcode(244);
                            outBuffer.writeByte(anInt976);
                            outBuffer.writeByte(anInt755);
                            outBuffer.writeByte(anInt885);
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
            drawTitleScreen(4);
        else
            method69(29510);
        anInt934 = 0;
    }

    public void method36(byte byte0) {
        if (byte0 != -70)
            objects = null;
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
                loginMessage0 = "";
                loginMessage1 = "Enter your username & password.";
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
                    login(aString1066, aString1067, false);
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
        byte[] abyte0 = Signlink.cacheload(s1);
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
            showProgress(true, "Requesting " + s, j);
            try {
                int j1 = 0;
                DataInputStream datainputstream = method94(s1 + i);
                byte[] abyte1 = new byte[6];
                datainputstream.readFully(abyte1, 0, 6);
                Buffer class38_sub2_sub3 = new Buffer(abyte1);
                class38_sub2_sub3.offset = 3;
                int i2 = class38_sub2_sub3.readSWord() + 6;
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
                        showProgress(true, "Loading " + s + " - " + i3 + "%", j);
                    j1 = i3;
                }
                datainputstream.close();
            } catch (IOException _ex) {
                abyte0 = null;
                for (int k1 = l; k1 > 0; k1--) {
                    showProgress(true, "Error loading - Will retry in " + k1 + " secs.", j);
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
        Signlink.cachesave(s1, abyte0);
        if (k != 0) {
            for (int l1 = 1; l1 > 0; l1++)
                ;
        }
        FileArchive fileArchive_1 = new FileArchive(abyte0);
        return fileArchive_1;
    }

    public void disposeTitleComponents(boolean flag) {
        flameActive = false;
        while (aBoolean1121) {
            flameActive = false;
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
        indexedSprite8 = null;
        indexedSprite9 = null;
        imageRunes = null;
        flameGradient = null;
        flameGradient0 = null;
        flameGradient1 = null;
        flameGradient2 = null;
        flameBuffer0 = null;
        flameBuffer1 = null;
        if (!flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        flameBuffer3 = null;
        flameBuffer2 = null;
        imageFlamesLeft = null;
        imageFlamesRight = null;
    }

    public void method39(int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = 2048 - l & 0x7ff;
        int i2 = 2048 - k & 0x7ff;
        int j2 = 0;
        int k2 = 0;
        int l2 = k1;
        if (l1 != 0) {
            int i3 = Model.sin[l1];
            int k3 = Model.cos[l1];
            int i4 = k2 * k3 - l2 * i3 >> 16;
            l2 = k2 * i3 + l2 * k3 >> 16;
            k2 = i4;
        }
        if (i2 != 0) {
            int j3 = Model.sin[i2];
            int l3 = Model.cos[i2];
            int j4 = l2 * j3 + j2 * l3 >> 16;
            l2 = l2 * l3 - j2 * j3 >> 16;
            j2 = j4;
        }
        anInt1111 = j - j2;
        anInt1112 = i - k2;
        anInt1113 = j1 - l2;
        anInt1114 = l;
        if (i1 != 16418)
            objects = null;
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
        if (!lowMemory) {
            if (Draw3D.textureCycles[17] >= i) {
                IndexedSprite indexedSprite = Draw3D.indexedSpritesArray[17];
                int j = indexedSprite.width * indexedSprite.height - 1;
                int l = indexedSprite.width * anInt969 * 2;
                byte[] abyte0 = indexedSprite.pixels;
                byte[] abyte2 = aByteArray1069;
                for (int j1 = 0; j1 <= j; j1++)
                    abyte2[j1] = abyte0[j1 - l & j];

                indexedSprite.pixels = abyte2;
                aByteArray1069 = abyte0;
                Draw3D.updateTexture(17);
            }
            if (Draw3D.textureCycles[24] >= i) {
                IndexedSprite indexedSprite_1 = Draw3D.indexedSpritesArray[24];
                int k = indexedSprite_1.width * indexedSprite_1.height - 1;
                int i1 = indexedSprite_1.width * anInt969 * 2;
                byte[] abyte1 = indexedSprite_1.pixels;
                byte[] abyte3 = aByteArray1069;
                for (int k1 = 0; k1 <= k; k1++)
                    abyte3[k1] = abyte1[k1 - i1 & k];

                indexedSprite_1.pixels = abyte3;
                aByteArray1069 = abyte1;
                Draw3D.updateTexture(24);
            }
        }
    }

    public void method42(boolean flag) {
        char c = '\u0100';
        for (int i = 10; i < 117; i++) {
            int j = (int) (Math.random() * 100D);
            if (j < 50)
                flameBuffer3[i + (c - 2 << 7)] = 255;
        }

        for (int k = 0; k < 100; k++) {
            int l = (int) (Math.random() * 124D) + 2;
            int j1 = (int) (Math.random() * 128D) + 128;
            int j2 = l + (j1 << 7);
            flameBuffer3[j2] = 192;
        }

        if (!flag)
            anInt780 = inBuffer.readByte();
        for (int i1 = 1; i1 < c - 1; i1++) {
            for (int k1 = 1; k1 < 127; k1++) {
                int k2 = k1 + (i1 << 7);
                flameBuffer2[k2] = (flameBuffer3[k2 - 1] + flameBuffer3[k2 + 1] + flameBuffer3[k2 - 128]
                        + flameBuffer3[k2 + 128]) / 4;
            }

        }

        anInt1156 += 128;
        if (anInt1156 > flameBuffer0.length) {
            anInt1156 -= flameBuffer0.length;
            int l1 = (int) (Math.random() * 12D);
            updateFlameBuffer(578, imageRunes[l1]);
        }
        for (int i2 = 1; i2 < c - 1; i2++) {
            for (int l2 = 1; l2 < 127; l2++) {
                int j3 = l2 + (i2 << 7);
                int l3 = flameBuffer2[j3 + 128] - flameBuffer0[j3 + anInt1156 & flameBuffer0.length - 1] / 5;
                if (l3 < 0)
                    l3 = 0;
                flameBuffer3[j3] = l3;
            }

        }

        for (int i3 = 0; i3 < c - 1; i3++)
            anIntArray850[i3] = anIntArray850[i3 + 1];

        anIntArray850[c - 1] = (int) (Math.sin((double) clientClock / 14D) * 16D + Math.sin((double) clientClock / 15D) * 14D
                + Math.sin((double) clientClock / 16D) * 12D);
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
        drawArea21.bind();
        if (byte0 != -46)
            method6();
        int i = cameraYaw + minimapAnticheatAngle & 0x7ff;
        int j = 48 + self.x / 32;
        int l1 = 464 - self.z / 32;
        aClass38_Sub2_Sub2_Sub2_1053.drawRotatedMasked(i, 146, anIntArray1133, 151, l1, 256 + minimapZoom, j, 21, 9,
                anIntArray953);
        aClass38_Sub2_Sub2_Sub2_1145.drawRotatedMasked(cameraYaw, 33, anIntArray856, 33, 25, 256, 25, 0, 0,
                anIntArray1117);
        for (int j3 = 0; j3 < anInt917; j3++) {
            int k = (anIntArray918[j3] * 4 + 2) - self.x / 32;
            int i2 = (anIntArray919[j3] * 4 + 2) - self.z / 32;
            method87(i2, 4, aClass38_Sub2_Sub2_Sub2Array791[j3], k);
        }

        for (int k3 = 0; k3 < 104; k3++) {
            for (int l3 = 0; l3 < 104; l3++) {
                LinkedList linkedList = objects[currentLevel][k3][l3];
                if (linkedList != null) {
                    int l = (k3 * 4 + 2) - self.x / 32;
                    int j2 = (l3 * 4 + 2) - self.z / 32;
                    method87(j2, 4, aClass38_Sub2_Sub2_Sub2_1057, l);
                }
            }

        }

        for (int i4 = 0; i4 < npcCount; i4++) {
            NpcEntity npcEntity = npcEntities[npcIndices[i4]];
            if (npcEntity != null && npcEntity.isValid(false)
                    && npcEntity.info.showOnMinimap) {
                int i1 = npcEntity.x / 32
                        - self.x / 32;
                int k2 = npcEntity.z / 32
                        - self.z / 32;
                method87(k2, 4, aClass38_Sub2_Sub2_Sub2_1058, i1);
            }
        }

        for (int j4 = 0; j4 < entityCount; j4++) {
            PlayerEntity playerEntity = playerEntities[playerIndices[j4]];
            if (playerEntity != null && playerEntity.isValid(false)) {
                int j1 = playerEntity.x / 32
                        - self.x / 32;
                int l2 = playerEntity.z / 32
                        - self.z / 32;
                boolean flag = false;
                long l4 = StringUtils.toBase37(playerEntity.name);
                for (int k4 = 0; k4 < friendCount; k4++) {
                    if (l4 != friendName37[k4] || friendWorld[k4] == 0)
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

        if (flagTileX != 0) {
            int k1 = (flagTileX * 4 + 2) - self.x / 32;
            int i3 = (flagTileY * 4 + 2) - self.z / 32;
            method87(i3, 4, aClass38_Sub2_Sub2_Sub2_997, k1);
        }
        Draw2D.fillRect(82, 93, 0xffffff, 3, 3);
        drawArea22.bind();
    }

    public Component method11(byte byte0) {
        if (byte0 != 3)
            anInt759 = 260;
        if (Signlink.mainapp != null)
            return Signlink.mainapp;
        if (super.frame != null)
            return super.frame;
        else
            return this;
    }

    public void method44(int i) {
        if (i <= 0)
            method6();
        if (anInt1078 == 2) {
            for (TemporaryLoc temporaryLoc = (TemporaryLoc) temporaryLocs
                    .peekLast(); temporaryLoc != null; temporaryLoc = (TemporaryLoc) temporaryLocs.getPrevious())
                if (clientClock >= temporaryLoc.lastCycle) {
                    method99(temporaryLoc.rotation, temporaryLoc.tileX, temporaryLoc.tileZ,
                            temporaryLoc.classType, temporaryLoc.locIndex, temporaryLoc.type, -27819,
                            temporaryLoc.level);
                    temporaryLoc.unlink();
                }

            anInt1108++;
            if (anInt1108 > 85) {
                anInt1108 = 0;
                outBuffer.writeOpcode(85);
            }
        }
    }

    public void method45(int i, int j) {
        int[] ai = aClass38_Sub2_Sub2_Sub2_1053.pixels;
        if (j >= 0)
            return;
        int k = ai.length;
        for (int l = 0; l < k; l++)
            ai[l] = 0;

        for (int i1 = 1; i1 < 103; i1++) {
            int j1 = 24628 + (103 - i1) * 512 * 4;
            for (int l1 = 1; l1 < 103; l1++) {
                if ((aByteArrayArrayArray840[i][l1][i1] & 0x18) == 0)
                    scene.drawMinimapTile(ai, j1, 512, i, l1, i1);
                if (i < 3 && (aByteArrayArrayArray840[i + 1][l1][i1] & 8) != 0)
                    scene.drawMinimapTile(ai, j1, 512, i + 1, l1, i1);
                j1 += 4;
            }

        }

        int k1 = ((238 + (int) (Math.random() * 20D)) - 10 << 16) + ((238 + (int) (Math.random() * 20D)) - 10 << 8)
                + ((238 + (int) (Math.random() * 20D)) - 10);
        int i2 = (238 + (int) (Math.random() * 20D)) - 10 << 16;
        aClass38_Sub2_Sub2_Sub2_1053.prepare();
        for (int j2 = 1; j2 < 103; j2++) {
            for (int k2 = 1; k2 < 103; k2++) {
                if ((aByteArrayArrayArray840[i][k2][j2] & 0x18) == 0)
                    method46(anInt899, i, k1, k2, i2, j2);
                if (i < 3 && (aByteArrayArrayArray840[i + 1][k2][j2] & 8) != 0)
                    method46(anInt899, i + 1, k1, k2, i2, j2);
            }

        }

        drawArea22.bind();
        anInt917 = 0;
        for (int l2 = 0; l2 < 104; l2++) {
            for (int i3 = 0; i3 < 104; i3++) {
                int j3 = scene.getGroundDecorationBitset(currentLevel, l2, i3);
                if (j3 != 0) {
                    j3 = j3 >> 14 & 0x7fff;
                    int k3 = LocType.get(j3).mapfunction;
                    if (k3 >= 0) {
                        int l3 = l2;
                        int i4 = i3;
                        if (k3 != 22 && k3 != 29 && k3 != 34 && k3 != 36 && k3 != 46 && k3 != 47 && k3 != 48) {
                            byte byte0 = 104;
                            byte byte1 = 104;
                            int[][] ai1 = collisionMaps[currentLevel].flags;
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
        int k1 = scene.getWallBitset(j, l, j1);
        if (k1 != 0) {
            int l1 = scene.getInfo(j, l, j1, k1);
            int k2 = l1 >> 6 & 3;
            int i3 = l1 & 0x1f;
            int k3 = k;
            if (k1 > 0)
                k3 = i1;
            int[] ai = aClass38_Sub2_Sub2_Sub2_1053.pixels;
            int k4 = 24624 + l * 4 + (103 - j1) * 512 * 4;
            int i5 = k1 >> 14 & 0x7fff;
            LocType locType_2 = LocType.get(i5);
            if (locType_2.mapscene != -1) {
                IndexedSprite class38_sub2_sub2_sub3_2 = indexedSpritesArray1[locType_2.mapscene];
                if (class38_sub2_sub2_sub3_2 != null) {
                    int i6 = (locType_2.sizeX * 4 - class38_sub2_sub2_sub3_2.width) / 2;
                    int j6 = (locType_2.sizeZ * 4 - class38_sub2_sub2_sub3_2.height) / 2;
                    class38_sub2_sub2_sub3_2.draw(48 + (104 - j1 - locType_2.sizeZ) * 4 + j6, 48 + l * 4 + i6
                    );
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
        k1 = scene.getLocationBitset(j, l, j1);
        i = 56 / i;
        if (k1 != 0) {
            int i2 = scene.getInfo(j, l, j1, k1);
            int l2 = i2 >> 6 & 3;
            int j3 = i2 & 0x1f;
            int l3 = k1 >> 14 & 0x7fff;
            LocType locType = LocType.get(l3);
            if (locType.mapscene != -1) {
                IndexedSprite indexedSprite_1 = indexedSpritesArray1[locType.mapscene];
                if (indexedSprite_1 != null) {
                    int j5 = (locType.sizeX * 4 - indexedSprite_1.width) / 2;
                    int k5 = (locType.sizeZ * 4 - indexedSprite_1.height) / 2;
                    indexedSprite_1.draw(48 + (104 - j1 - locType.sizeZ) * 4 + k5, 48 + l * 4 + j5
                    );
                }
            } else if (j3 == 9) {
                int l4 = 0xeeeeee;
                if (k1 > 0)
                    l4 = 0xee0000;
                int[] ai1 = aClass38_Sub2_Sub2_Sub2_1053.pixels;
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
        k1 = scene.getGroundDecorationBitset(j, l, j1);
        if (k1 != 0) {
            int j2 = k1 >> 14 & 0x7fff;
            LocType locType = LocType.get(j2);
            if (locType.mapscene != -1) {
                IndexedSprite indexedSprite = indexedSpritesArray1[locType.mapscene];
                if (indexedSprite != null) {
                    int i4 = (locType.sizeX * 4 - indexedSprite.width) / 2;
                    int j4 = (locType.sizeZ * 4 - indexedSprite.height) / 2;
                    indexedSprite.draw(48 + (104 - j1 - locType.sizeZ) * 4 + j4, 48 + l * 4 + i4);
                }
            }
        }
    }

    public void updateNpcs(Buffer buffer, int size) {
        deadEntityCount = 0;
        updateCount = 0;

        updateNpcList(buffer);
        updateLocalNpcs(buffer, size);
        updateNpcMasks(buffer);

        for (int j = 0; j < deadEntityCount; j++) {
            int k = deadEntityIndices[j];
            if (npcEntities[k].remove != clientClock) {
                npcEntities[k].info = null;
                npcEntities[k] = null;
            }
        }

        if (buffer.offset != size) {
            Signlink.reporterror(aString1066 + " size mismatch in getnpcpos - pos:" + buffer.offset + " psize:" + size);
            throw new RuntimeException("eek");
        }

        for (int pos = 0; pos < npcCount; pos++) {
            if (npcEntities[npcIndices[pos]] == null) {
                Signlink.reporterror(aString1066 + " null entry in npc list - pos:" + pos + " size:" + npcCount);
                throw new RuntimeException("eek");
            }
        }
    }

    public void startThread(Runnable task, int priority) {
        if (Signlink.mainapp != null) {
            Signlink.startthread(task, priority);
        } else {
            super.startThread(task, priority);
        }
    }

    public void createTitleImages() {
        indexedSprite8 = new IndexedSprite(fileArchive, "titlebox", 0);
        indexedSprite9 = new IndexedSprite(fileArchive, "titlebutton", 0);

        imageRunes = new IndexedSprite[12];
        for (int j = 0; j < 12; j++) {
            imageRunes[j] = new IndexedSprite(fileArchive, "runes", j);
        }

        imageFlamesLeft = new Sprite(128, 265);
        imageFlamesRight = new Sprite(128, 265);
        System.arraycopy(imageTitle0.pixels, 0, imageFlamesLeft.pixels, 0, 33920);
        System.arraycopy(imageTitle1.pixels, 0, imageFlamesRight.pixels, 0, 33920);

        flameGradient0 = new int[256];
        for (int i1 = 0; i1 < 64; i1++) {
            flameGradient0[i1] = i1 * 0x40000;
        }
        for (int j1 = 0; j1 < 64; j1++) {
            flameGradient0[j1 + 64] = 0xff0000 + 1024 * j1;
        }
        for (int k1 = 0; k1 < 64; k1++) {
            flameGradient0[k1 + 128] = 0xffff00 + 4 * k1;
        }
        for (int l1 = 0; l1 < 64; l1++) {
            flameGradient0[l1 + 192] = 0xffffff;
        }

        flameGradient1 = new int[256];
        for (int i2 = 0; i2 < 64; i2++) {
            flameGradient1[i2] = i2 * 1024;
        }
        for (int j2 = 0; j2 < 64; j2++) {
            flameGradient1[j2 + 64] = 65280 + 4 * j2;
        }
        for (int k2 = 0; k2 < 64; k2++) {
            flameGradient1[k2 + 128] = 65535 + 0x40000 * k2;
        }
        for (int l2 = 0; l2 < 64; l2++) {
            flameGradient1[l2 + 192] = 0xffffff;
        }

        flameGradient2 = new int[256];
        for (int i3 = 0; i3 < 64; i3++) {
            flameGradient2[i3] = i3 * 4;
        }
        for (int j3 = 0; j3 < 64; j3++) {
            flameGradient2[j3 + 64] = 255 + 0x40000 * j3;
        }
        for (int k3 = 0; k3 < 64; k3++) {
            flameGradient2[k3 + 128] = 0xff00ff + 1024 * k3;
        }
        for (int l3 = 0; l3 < 64; l3++) {
            flameGradient2[l3 + 192] = 0xffffff;
        }

        flameGradient = new int[256];
        flameBuffer0 = new int[32768];
        flameBuffer1 = new int[32768];
        updateFlameBuffer(578, null);
        flameBuffer3 = new int[32768];
        flameBuffer2 = new int[32768];
        showProgress(true, "Connecting to fileserver", 10);

        if (!flameActive) {
            flamesThread = true;
            flameActive = true;
            startThread(this, 2);
        }
    }

    public void updateOtherPlayers(Buffer buffer) {
        int k = buffer.getBits(8);

        if (k < entityCount) {
            for (int i1 = k; i1 < entityCount; i1++) {
                deadEntityIndices[deadEntityCount++] = playerIndices[i1];
            }
        }
        
        if (k > entityCount) {
            Signlink.reporterror(aString1066 + " Too many players");
            throw new RuntimeException("eek");
        }
        
        entityCount = 0;
        for (int n = 0; n < k; n++) {
            int index = playerIndices[n];
            PlayerEntity p = playerEntities[index];

            int shouldRemove = buffer.getBits(1);
            if (shouldRemove == 0) {
                playerIndices[entityCount++] = index;
                p.remove = clientClock;
            } else {
                int type = buffer.getBits(2);
                if (type == 0) {
                    playerIndices[entityCount++] = index;
                    p.remove = clientClock;
                    entityUpdateIndices[updateCount++] = index;
                } else if (type == 1) {
                    playerIndices[entityCount++] = index;
                    p.remove = clientClock;
                    int j2 = buffer.getBits(3);
                    p.walk(false, j2);
                    int l2 = buffer.getBits(1);
                    if (l2 == 1) {
                        entityUpdateIndices[updateCount++] = index;
                    }
                } else if (type == 2) {
                    playerIndices[entityCount++] = index;
                    p.remove = clientClock;
                    int k2 = buffer.getBits(3);
                    p.walk(true, k2);
                    int i3 = buffer.getBits(3);
                    p.walk(true, i3);
                    int j3 = buffer.getBits(1);
                    if (j3 == 1) {
                        entityUpdateIndices[updateCount++] = index;
                    }
                } else if (type == 3) {
                    deadEntityIndices[deadEntityCount++] = index;
                }
            }
        }
    }

    public void method50(int i, int j, int k, int l, int i1, int j1) {
        indexedSprite6.draw(k, j);
        indexedSprite7.draw((k + j1) - 16, j);
        Draw2D.fillRect(k + 16, j, anInt1050, 16, j1 - 32);
        int k1 = ((j1 - 32) * j1) / i1;
        if (k1 < 8)
            k1 = 8;
        int l1 = ((j1 - 32 - k1) * l) / (i1 - j1);
        if (i <= 0)
            anInt780 = -1;
        Draw2D.fillRect(k + 16 + l1, j, anInt1158, 16, k1);
        Draw2D.drawVerticalLine(anInt993, k + 16 + l1, k1, j);
        Draw2D.drawVerticalLine(anInt993, k + 16 + l1, k1, j + 1);
        Draw2D.drawHorizontalLine(anInt993, k + 16 + l1, 16, j);
        Draw2D.drawHorizontalLine(anInt993, k + 17 + l1, 16, j);
        Draw2D.drawVerticalLine(anInt980, k + 16 + l1, k1, j + 15);
        Draw2D.drawVerticalLine(anInt980, k + 17 + l1, k1 - 1, j + 14);
        Draw2D.drawHorizontalLine(anInt980, k + 15 + l1 + k1, 16, j);
        Draw2D.drawHorizontalLine(anInt980, k + 14 + l1 + k1, 15, j + 1);
    }

    public void resetCharacterDesign(byte byte0) {
        aBoolean788 = true;
        if (byte0 != -6)
            objects = null;
        for (int i = 0; i < 7; i++) {
            anIntArray789[i] = -1;
            for (int j = 0; j < IdkType.count; j++) {
                if (IdkType.instances[j].validStyle
                        || IdkType.instances[j].type != i + (characterDesignIsMale ? 0 : 7))
                    continue;
                anIntArray789[i] = j;
                break;
            }

        }

    }

    public void method52(byte[] abyte0, int i, int j, boolean flag) {
        if (i <= 0)
            aBoolean849 = !aBoolean849;
        Signlink.midifade = flag ? 1 : 0;
        Signlink.midisave(abyte0, j);
    }

    public void method53(boolean flag) {
        for (int i = 0; i < npcCount; i++) {
            NpcEntity npcEntity = npcEntities[npcIndices[i]];
            int j = 0x20000000 + (npcIndices[i] << 14);
            if (npcEntity == null || !npcEntity.isValid(false))
                continue;
            int k = npcEntity.x >> 7;
            int l = npcEntity.z >> 7;
            if (k < 0 || k >= 104 || l < 0 || l >= 104)
                continue;
            if (npcEntity.index == 1
                    && (npcEntity.x & 0x7f) == 64
                    && (npcEntity.z & 0x7f) == 64) {
                if (anIntArrayArray920[k][l] == anInt837)
                    continue;
                anIntArrayArray920[k][l] = anInt837;
            }
            scene.add(npcEntity.z,
                    (npcEntity.index - 1) * 64 + 60,
                    npcEntity.animationDelay,
                    npcEntity.x, j,
                    npcEntity.animationStretches, null, npcEntity,
                    method33(currentLevel, npcEntity.x, (byte) 5,
                            npcEntity.z),
                    currentLevel);
        }

        if (flag)
            anInt780 = -1;
    }

    public void method54(int i, int j, boolean flag) {
        Signlink.midivol = j;
        anInt779 += i;
        if (flag)
            Signlink.midi = "voladjust";
    }

    public void drawTitleScreen(int i) {
        prepareTitleScreen((byte) 99);
        imageTitle4.bind();
        if (i < 4 || i > 4)
            return;
        indexedSprite8.draw(0, 0);
        char c = '\u0168';
        char c1 = '\310';
        if (anInt1109 == 0) {
            int j = c1 / 2 - 20;
            fontBold12.drawCentered(c / 2, 0xffff00, true, j, "Welcome to RuneScape");
            j += 30;
            int i1 = c / 2 - 80;
            int l1 = c1 / 2 + 20;
            indexedSprite9.draw(l1 - 20, i1 - 73);
            fontBold12.drawCentered(i1, 0xffffff, true, l1 + 5, "New user");
            i1 = c / 2 + 80;
            indexedSprite9.draw(l1 - 20, i1 - 73);
            fontBold12.drawCentered(i1, 0xffffff, true, l1 + 5, "Existing User");
        }
        if (anInt1109 == 2) {
            int k = c1 / 2 - 40;
            if (loginMessage0.length() > 0) {
                fontBold12.drawCentered(c / 2, 0xffff00, true, k - 15, loginMessage0);
                fontBold12.drawCentered(c / 2, 0xffff00, true, k, loginMessage1);
                k += 30;
            } else {
                fontBold12.drawCentered(c / 2, 0xffff00, true, k - 7, loginMessage1);
                k += 30;
            }
            fontBold12.draw(c / 2 - 90, k,
                    "Username: " + aString1066 + ((anInt972 == 0) & (clientClock % 40 < 20) ? "@yel@|" : ""), true,
                    0xffffff);
            k += 15;
            fontBold12.draw(c / 2 - 88, k, "Password: " + StringUtils.toAsterisks(aString1067)
                    + ((anInt972 == 1) & (clientClock % 40 < 20) ? "@yel@|" : ""), true, 0xffffff);
            k += 15;
            int j1 = c / 2 - 80;
            int i2 = c1 / 2 + 50;
            indexedSprite9.draw(i2 - 20, j1 - 73);
            fontBold12.drawCentered(j1, 0xffffff, true, i2 + 5, "Login");
            j1 = c / 2 + 80;
            indexedSprite9.draw(i2 - 20, j1 - 73);
            fontBold12.drawCentered(j1, 0xffffff, true, i2 + 5, "Cancel");
        }
        if (anInt1109 == 3) {
            fontBold12.drawCentered(c / 2, 0xffff00, true, c1 / 2 - 60, "Create a free account");
            int l = c1 / 2 - 35;
            fontBold12.drawCentered(c / 2, 0xffffff, true, l, "To create a new account you need to");
            l += 15;
            fontBold12.drawCentered(c / 2, 0xffffff, true, l, "go back to the main RuneScape webpage");
            l += 15;
            fontBold12.drawCentered(c / 2, 0xffffff, true, l, "and choose the red 'create account'");
            l += 15;
            fontBold12.drawCentered(c / 2, 0xffffff, true, l, "button at the top right of that page.");
            l += 15;
            int k1 = c / 2;
            int j2 = c1 / 2 + 50;
            indexedSprite9.draw(j2 - 20, k1 - 73);
            fontBold12.drawCentered(k1, 0xffffff, true, j2 + 5, "Cancel");
        }
        imageTitle4.drawImage(186, super.graphics, 214);
        if (redrawTitleBackground) {
            redrawTitleBackground = false;
            imageTitle2.drawImage(0, super.graphics, 128);
            imageTitle3.drawImage(386, super.graphics, 214);
            imageTitle5.drawImage(265, super.graphics, 0);
            imageTitle6.drawImage(265, super.graphics, 574);
            imageTitle7.drawImage(186, super.graphics, 128);
            imageTitle8.drawImage(186, super.graphics, 574);
        }
    }

    public void prepareGameScreen(int i) {
        if (drawArea23 != null)
            return;
        disposeTitleComponents(true);
        super.drawArea = null;
        imageTitle2 = null;
        imageTitle3 = null;
        imageTitle4 = null;
        imageTitle0 = null;
        if (i != -7185) {
            return;
        } else {
            imageTitle1 = null;
            imageTitle5 = null;
            imageTitle6 = null;
            imageTitle7 = null;
            imageTitle8 = null;
            drawArea23 = new DrawArea(method11(aByte1116), 479, 96);
            drawArea21 = new DrawArea(method11(aByte1116), 168, 160);
            Draw2D.clear();
            indexedSprite19.draw(0, 0);
            drawArea20 = new DrawArea(method11(aByte1116), 190, 261);
            drawArea22 = new DrawArea(method11(aByte1116), 512, 334);
            Draw2D.clear();
            drawArea24 = new DrawArea(method11(aByte1116), 501, 61);
            drawArea25 = new DrawArea(method11(aByte1116), 288, 40);
            drawArea26 = new DrawArea(method11(aByte1116), 269, 66);
            redrawTitleBackground = true;
            return;
        }
    }

    public void updateNewPlayers(int size, Buffer buffer) {
        do {
            if (buffer.bitOffset + 10 >= size * 8) {
                break;
            }

            int pid = buffer.getBits(11);
            if (pid == 2047) {
                break;
            }
            
            if (playerEntities[pid] == null) {
                playerEntities[pid] = new PlayerEntity();
                if (playerBuffers[pid] != null) {
                    playerEntities[pid].read(playerBuffers[pid]);
                }
            }
            
            playerIndices[entityCount++] = pid;
            PlayerEntity p = playerEntities[pid];
            p.remove = clientClock;
            
            int x = buffer.getBits(5);
            if (x > 15) {
                x -= 32;
            }
            
            int y = buffer.getBits(5);
            if (y > 15) {
                y -= 32;
            }
            
            int teleport = buffer.getBits(1);
            p.move(teleport == 1, self.pathTileX[0] + x, self.pathTileZ[0] + y);

            int hasUpdate = buffer.getBits(1);
            if (hasUpdate == 1) {
                entityUpdateIndices[updateCount++] = pid;
            }
        } while (true);

        buffer.accessBytes();
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
        InputTracking.method183((byte) 65);
        method83(aByte843);
        while (i >= 0)
            return;
        scene.reset();
        for (int j = 0; j < 4; j++)
            collisionMaps[j].reset();

        System.gc();
        midistop();
        aString1119 = null;
        anInt744 = 0;
    }

    public void method59(int i, int j, int k, InterfaceComponent interfaceComponent, int l) {
        if (interfaceComponent.anInt271 != 0 || interfaceComponent.anIntArray285 == null)
            return;
        if (interfaceComponent.aBoolean284 && anInt1063 != interfaceComponent.anInt269 && anInt941 != interfaceComponent.anInt269
                && anInt859 != interfaceComponent.anInt269)
            return;
        int i1 = Draw2D.left;
        int j1 = Draw2D.top;
        int k1 = Draw2D.right;
        int l1 = Draw2D.bottom;
        if (k != 38682)
            anInt780 = -1;
        Draw2D.setBounds(i + interfaceComponent.anInt275, i, j + interfaceComponent.anInt274, j);
        int i2 = interfaceComponent.anIntArray285.length;
        for (int j2 = 0; j2 < i2; j2++) {
            int k2 = interfaceComponent.anIntArray286[j2] + j;
            int l2 = (interfaceComponent.anIntArray287[j2] + i) - l;
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[interfaceComponent.anIntArray285[j2]];
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
                                    Sprite class38_sub2_sub2_sub2_2 = ObjType.getSprite(i9,
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
                                        class38_sub2_sub2_sub2_2.draw(128, j5 + k6, i6 + k8);
                                    } else if (anInt947 != 0 && anInt946 == i3 && anInt945 == interfaceComponent_1.anInt269)
                                        class38_sub2_sub2_sub2_2.draw(128, j5, i6);
                                    else
                                        class38_sub2_sub2_sub2_2.draw(i6, j5);
                                    if (class38_sub2_sub2_sub2_2.cropW == 33 || interfaceComponent_1.anIntArray266[i3] != 1) {
                                        int k9 = interfaceComponent_1.anIntArray266[i3];
                                        indexedFont1.draw(j5 + 1 + k6, i6 + 10 + k8, 0,
                                                formatObjAmount(k9));
                                        indexedFont1.draw(j5 + k6, i6 + 9 + k8, 0xffff00,
                                                formatObjAmount(k9));
                                    }
                                }
                            } else if (interfaceComponent_1.aClass38_Sub2_Sub2_Sub2Array295 != null && i3 < 20) {
                                Sprite class38_sub2_sub2_sub2_1 = interfaceComponent_1.aClass38_Sub2_Sub2_Sub2Array295[i3];
                                if (class38_sub2_sub2_sub2_1 != null)
                                    class38_sub2_sub2_sub2_1.draw(i6, j5);
                            }
                            i3++;
                        }

                    }

                } else if (interfaceComponent_1.anInt271 == 3) {
                    if (interfaceComponent_1.aBoolean299)
                        Draw2D.fillRect(l2, k2, interfaceComponent_1.anInt305, interfaceComponent_1.anInt274,
                                interfaceComponent_1.anInt275);
                    else
                        Draw2D.drawRect(k2, interfaceComponent_1.anInt305, interfaceComponent_1.anInt275, l2,
                                interfaceComponent_1.anInt274);
                } else if (interfaceComponent_1.anInt271 == 4) {
                    IndexedFont indexedFont = interfaceComponent_1.indexedFont;
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
                    for (int j6 = l2 + indexedFont.height; s
                            .length() > 0; j6 += indexedFont.height) {
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
                            indexedFont.drawCentered(k2 + interfaceComponent_1.anInt274 / 2, l3, interfaceComponent_1.aBoolean301, j6,
                                    s1);
                        else
                            indexedFont.draw(k2, j6, s1, interfaceComponent_1.aBoolean301, l3);
                    }

                } else if (interfaceComponent_1.anInt271 == 5) {
                    Sprite class38_sub2_sub2_sub2;
                    if (method114(interfaceComponent_1, 65))
                        class38_sub2_sub2_sub2 = interfaceComponent_1.aClass38_Sub2_Sub2_Sub2_309;
                    else
                        class38_sub2_sub2_sub2 = interfaceComponent_1.aClass38_Sub2_Sub2_Sub2_308;
                    if (class38_sub2_sub2_sub2 != null)
                        class38_sub2_sub2_sub2.draw(l2, k2);
                } else if (interfaceComponent_1.anInt271 == 6) {
                    int j3 = Draw3D.centerX;
                    int i4 = Draw3D.centerY;
                    Draw3D.centerX = k2 + interfaceComponent_1.anInt274 / 2;
                    Draw3D.centerY = l2 + interfaceComponent_1.anInt275 / 2;
                    int l4 = Draw3D.sin[interfaceComponent_1.anInt315] * interfaceComponent_1.anInt314 >> 16;
                    int k5 = Draw3D.cos[interfaceComponent_1.anInt315] * interfaceComponent_1.anInt314 >> 16;
                    boolean flag = method114(interfaceComponent_1, 65);
                    int j8;
                    if (flag)
                        j8 = interfaceComponent_1.anInt313;
                    else
                        j8 = interfaceComponent_1.anInt312;
                    Model class38_sub2_sub1;
                    if (j8 == -1) {
                        class38_sub2_sub1 = interfaceComponent_1.method219(-1, -1, flag);
                    } else {
                        SeqType seqType = SeqType.animations[j8];
                        class38_sub2_sub1 = interfaceComponent_1.method219(seqType.primaryFrames[interfaceComponent_1.anInt267],
                                seqType.secondaryFrames[interfaceComponent_1.anInt267], flag);
                    }
                    if (class38_sub2_sub1 != null)
                        class38_sub2_sub1.drawSimple(0, interfaceComponent_1.anInt316, 0, interfaceComponent_1.anInt315, 0, l4, k5);
                    Draw3D.centerX = j3;
                    Draw3D.centerY = i4;
                } else if (interfaceComponent_1.anInt271 == 7) {
                    IndexedFont indexedFont_1 = interfaceComponent_1.indexedFont;
                    int j4 = 0;
                    for (int i5 = 0; i5 < interfaceComponent_1.anInt275; i5++) {
                        for (int l5 = 0; l5 < interfaceComponent_1.anInt274; l5++) {
                            if (interfaceComponent_1.anIntArray265[j4] > 0) {
                                ObjType objType = ObjType.get(interfaceComponent_1.anIntArray265[j4] - 1);
                                String s2 = objType.name;
                                if (objType.stackable || interfaceComponent_1.anIntArray266[j4] != 1)
                                    s2 = s2 + " x" + method40(interfaceComponent_1.anIntArray266[j4], 0);
                                int l8 = k2 + l5 * (115 + interfaceComponent_1.anInt293);
                                int j9 = l2 + i5 * (12 + interfaceComponent_1.anInt294);
                                if (interfaceComponent_1.aBoolean300)
                                    indexedFont_1.drawCentered(l8 + interfaceComponent_1.anInt274 / 2, interfaceComponent_1.anInt305,
                                            interfaceComponent_1.aBoolean301, j9, s2);
                                else
                                    indexedFont_1.draw(l8, j9, s2, interfaceComponent_1.aBoolean301,
                                            interfaceComponent_1.anInt305);
                            }
                            j4++;
                        }

                    }

                }
        }

        Draw2D.setBounds(l1, j1, k1, i1);
    }

    public void updatePlayerMasks(Buffer buffer) {
        for (int j = 0; j < updateCount; j++) {
            int k = entityUpdateIndices[j];
            PlayerEntity player = playerEntities[k];
            int l = buffer.readByte();
            if ((l & 0x80) == 128) {
                l += buffer.readByte() << 8;
            }
            updatePlayerMask(k, l, buffer, player);
        }
    }

    public void method61(int i, int j) {
        int k = VarpType.instances[i].type;
        if (k == 0)
            return;
        int l = anIntArray938[i];
        if (k == 1) {
            if (l == 1)
                Draw3D.setBrightness(0.90000000000000002D);
            if (l == 2)
                Draw3D.setBrightness(0.80000000000000004D);
            if (l == 3)
                Draw3D.setBrightness(0.69999999999999996D);
            if (l == 4)
                Draw3D.setBrightness(0.59999999999999998D);
            ObjType.iconCache.clear();
            redrawTitleBackground = true;
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
                    method14(anInt1110, aString1119, anInt1155);
                else
                    midistop();
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
        for (int i = 0; i < npcCount; i++) {
            int j = npcIndices[i];
            NpcEntity npcEntity = npcEntities[j];
            if (npcEntity != null)
                method63(npcEntity, (byte) -128, npcEntity.info.size);
        }

    }

    public void method63(PathingEntity pathingEntity, byte byte0, int i) {
        if (pathingEntity.x < 128 || pathingEntity.z < 128
                || pathingEntity.x >= 13184 || pathingEntity.z >= 13184) {
            pathingEntity.primarySeq = -1;
            pathingEntity.spotAnimIndex = -1;
            pathingEntity.firstMoveCycle = 0;
            pathingEntity.lastMoveCycle = 0;
            pathingEntity.x = pathingEntity.pathTileX[0] * 128 + pathingEntity.index * 64;
            pathingEntity.z = pathingEntity.pathTileZ[0] * 128 + pathingEntity.index * 64;
            pathingEntity.pathRemaining = 0;
        }
        if (pathingEntity == self
                && (pathingEntity.x < 1536 || pathingEntity.z < 1536
                || pathingEntity.x >= 11776 || pathingEntity.z >= 11776)) {
            pathingEntity.primarySeq = -1;
            pathingEntity.spotAnimIndex = -1;
            pathingEntity.firstMoveCycle = 0;
            pathingEntity.lastMoveCycle = 0;
            pathingEntity.x = pathingEntity.pathTileX[0] * 128 + pathingEntity.index * 64;
            pathingEntity.z = pathingEntity.pathTileZ[0] * 128 + pathingEntity.index * 64;
            pathingEntity.pathRemaining = 0;
        }
        if (pathingEntity.firstMoveCycle > clientClock)
            method64(pathingEntity, -25115);
        else if (pathingEntity.lastMoveCycle >= clientClock)
            method65(pathingEntity, 0);
        else
            method66(598, pathingEntity);
        method67(pathingEntity, (byte) 117);
        if (byte0 != -128)
            outBuffer.writeByte(11);
        method68(aBoolean787, pathingEntity);
    }

    public void method64(PathingEntity pathingEntity, int i) {
        int j = pathingEntity.firstMoveCycle - clientClock;
        int k = pathingEntity.srcTileX * 128 + pathingEntity.index * 64;
        int l = pathingEntity.srcTileY * 128 + pathingEntity.index * 64;
        pathingEntity.x += (k - pathingEntity.x) / j;
        if (i != -25115)
            anInt733 = isaacState.nextInt();
        pathingEntity.z += (l - pathingEntity.z) / j;
        pathingEntity.anInt1431 = 0;
        if (pathingEntity.faceDirection == 0)
            pathingEntity.dstYaw = 1024;
        if (pathingEntity.faceDirection == 1)
            pathingEntity.dstYaw = 1536;
        if (pathingEntity.faceDirection == 2)
            pathingEntity.dstYaw = 0;
        if (pathingEntity.faceDirection == 3)
            pathingEntity.dstYaw = 512;
    }

    public void method65(PathingEntity pathingEntity, int i) {
        anInt779 += i;
        if (pathingEntity.lastMoveCycle == clientClock || pathingEntity.primarySeq == -1
                || pathingEntity.primarySeqDelay != 0 || pathingEntity.primarySeqCycle
                + 1 > SeqType.animations[pathingEntity.primarySeq].frameDelay[pathingEntity.primarySeqFrame]) {
            int j = pathingEntity.lastMoveCycle - pathingEntity.firstMoveCycle;
            int k = clientClock - pathingEntity.firstMoveCycle;
            int l = pathingEntity.srcTileX * 128 + pathingEntity.index * 64;
            int i1 = pathingEntity.srcTileY * 128 + pathingEntity.index * 64;
            int j1 = pathingEntity.dstTileX * 128 + pathingEntity.index * 64;
            int k1 = pathingEntity.dstTileY * 128 + pathingEntity.index * 64;
            pathingEntity.x = (l * (j - k) + j1 * k) / j;
            pathingEntity.z = (i1 * (j - k) + k1 * k) / j;
        }
        pathingEntity.anInt1431 = 0;
        if (pathingEntity.faceDirection == 0)
            pathingEntity.dstYaw = 1024;
        if (pathingEntity.faceDirection == 1)
            pathingEntity.dstYaw = 1536;
        if (pathingEntity.faceDirection == 2)
            pathingEntity.dstYaw = 0;
        if (pathingEntity.faceDirection == 3)
            pathingEntity.dstYaw = 512;
        pathingEntity.animationDelay = pathingEntity.dstYaw;
    }

    public void method66(int i, PathingEntity pathingEntity) {
        pathingEntity.secondarySeq = pathingEntity.standSeq;
        i = 56 / i;
        if (pathingEntity.pathRemaining == 0) {
            pathingEntity.anInt1431 = 0;
            return;
        }
        if (pathingEntity.primarySeq != -1 && pathingEntity.primarySeqDelay == 0) {
            SeqType seqType = SeqType.animations[pathingEntity.primarySeq];
            if (seqType.labelGroups == null) {
                pathingEntity.anInt1431++;
                return;
            }
        }
        int j = pathingEntity.x;
        int k = pathingEntity.z;
        int l = pathingEntity.pathTileX[pathingEntity.pathRemaining - 1] * 128
                + pathingEntity.index * 64;
        int i1 = pathingEntity.pathTileZ[pathingEntity.pathRemaining - 1] * 128
                + pathingEntity.index * 64;
        if (l - j > 256 || l - j < -256 || i1 - k > 256 || i1 - k < -256) {
            pathingEntity.x = l;
            pathingEntity.z = i1;
            return;
        }
        if (j < l) {
            if (k < i1)
                pathingEntity.dstYaw = 1280;
            else if (k > i1)
                pathingEntity.dstYaw = 1792;
            else
                pathingEntity.dstYaw = 1536;
        } else if (j > l) {
            if (k < i1)
                pathingEntity.dstYaw = 768;
            else if (k > i1)
                pathingEntity.dstYaw = 256;
            else
                pathingEntity.dstYaw = 512;
        } else if (k < i1)
            pathingEntity.dstYaw = 1024;
        else
            pathingEntity.dstYaw = 0;
        int j1 = pathingEntity.dstYaw - pathingEntity.animationDelay & 0x7ff;
        if (j1 > 1024)
            j1 -= 2048;
        int k1 = pathingEntity.walkSeq;
        if (j1 >= -256 && j1 <= 256)
            k1 = pathingEntity.runSeq;
        else if (j1 >= 256 && j1 < 768)
            k1 = pathingEntity.turnRightSeq;
        else if (j1 >= -768 && j1 <= -256)
            k1 = pathingEntity.turnAroundSeq;
        if (k1 == -1)
            k1 = pathingEntity.runSeq;
        pathingEntity.secondarySeq = k1;
        int l1 = 4;
        if (pathingEntity.animationDelay != pathingEntity.dstYaw && pathingEntity.targetEntity == -1)
            l1 = 2;
        if (pathingEntity.pathRemaining > 2)
            l1 = 6;
        if (pathingEntity.pathRemaining > 3)
            l1 = 8;
        if (pathingEntity.anInt1431 > 0 && pathingEntity.pathRemaining > 1) {
            l1 = 8;
            pathingEntity.anInt1431--;
        }
        if (pathingEntity.pathRunning[pathingEntity.pathRemaining - 1])
            l1 <<= 1;
        if (l1 >= 8 && pathingEntity.secondarySeq == pathingEntity.runSeq && pathingEntity.turnLeftSeq != -1)
            pathingEntity.secondarySeq = pathingEntity.turnLeftSeq;
        if (j < l) {
            pathingEntity.x += l1;
            if (pathingEntity.x > l)
                pathingEntity.x = l;
        } else if (j > l) {
            pathingEntity.x -= l1;
            if (pathingEntity.x < l)
                pathingEntity.x = l;
        }
        if (k < i1) {
            pathingEntity.z += l1;
            if (pathingEntity.z > i1)
                pathingEntity.z = i1;
        } else if (k > i1) {
            pathingEntity.z -= l1;
            if (pathingEntity.z < i1)
                pathingEntity.z = i1;
        }
        if (pathingEntity.x == l && pathingEntity.z == i1)
            pathingEntity.pathRemaining--;
    }

    public void method67(PathingEntity pathingEntity, byte byte0) {
        if (byte0 != 117)
            outBuffer.writeByte(89);
        if (pathingEntity.targetEntity != -1 && pathingEntity.targetEntity < 32768) {
            NpcEntity npcEntity = npcEntities[pathingEntity.targetEntity];
            if (npcEntity != null) {
                int l = pathingEntity.x - npcEntity.x;
                int j1 = pathingEntity.z - npcEntity.z;
                if (l != 0 || j1 != 0)
                    pathingEntity.dstYaw = (int) (Math.atan2(l, j1) * 325.94900000000001D) & 0x7ff;
            }
        }
        if (pathingEntity.targetEntity >= 32768) {
            int i = pathingEntity.targetEntity - 32768;
            if (i == anInt734)
                i = LOCAL_PLAYER_INDEX;
            PlayerEntity playerEntity = playerEntities[i];
            if (playerEntity != null) {
                int k1 = pathingEntity.x - playerEntity.x;
                int l1 = pathingEntity.z - playerEntity.z;
                if (k1 != 0 || l1 != 0)
                    pathingEntity.dstYaw = (int) (Math.atan2(k1, l1) * 325.94900000000001D) & 0x7ff;
            }
        }
        if ((pathingEntity.focusX != 0 || pathingEntity.focusY != 0)
                && (pathingEntity.pathRemaining == 0 || pathingEntity.anInt1431 > 0)) {
            int j = pathingEntity.x - (pathingEntity.focusX - baseTileX - baseTileX) * 64;
            int i1 = pathingEntity.z - (pathingEntity.focusY - baseTileZ - baseTileZ) * 64;
            if (j != 0 || i1 != 0)
                pathingEntity.dstYaw = (int) (Math.atan2(j, i1) * 325.94900000000001D) & 0x7ff;
            pathingEntity.focusX = 0;
            pathingEntity.focusY = 0;
        }
        int k = pathingEntity.dstYaw - pathingEntity.animationDelay & 0x7ff;
        if (k != 0) {
            if (k < 32 || k > 2016)
                pathingEntity.animationDelay = pathingEntity.dstYaw;
            else if (k > 1024)
                pathingEntity.animationDelay -= 32;
            else
                pathingEntity.animationDelay += 32;
            pathingEntity.animationDelay &= 0x7ff;
            if (pathingEntity.secondarySeq == pathingEntity.standSeq
                    && pathingEntity.animationDelay != pathingEntity.dstYaw) {
                if (pathingEntity.turnSeq != -1) {
                    pathingEntity.secondarySeq = pathingEntity.turnSeq;
                    return;
                }
                pathingEntity.secondarySeq = pathingEntity.runSeq;
            }
        }
    }

    public void method68(boolean flag, PathingEntity pathingEntity) {
        if (!flag)
            return;
        pathingEntity.animationStretches = false;
        if (pathingEntity.secondarySeq != -1) {
            SeqType seqType = SeqType.animations[pathingEntity.secondarySeq];
            pathingEntity.anInt1406++;
            if (pathingEntity.secondarySeqFrame < seqType.frameCount
                    && pathingEntity.anInt1406 > seqType.frameDelay[pathingEntity.secondarySeqFrame]) {
                pathingEntity.anInt1406 = 0;
                pathingEntity.secondarySeqFrame++;
            }
            if (pathingEntity.secondarySeqFrame >= seqType.frameCount) {
                pathingEntity.anInt1406 = 0;
                pathingEntity.secondarySeqFrame = 0;
            }
        }
        if (pathingEntity.primarySeq != -1 && pathingEntity.primarySeqDelay == 0) {
            SeqType seqType_1 = SeqType.animations[pathingEntity.primarySeq];
            for (pathingEntity.primarySeqCycle++; pathingEntity.primarySeqFrame < seqType_1.frameCount
                    && pathingEntity.primarySeqCycle > seqType_1.frameDelay[pathingEntity.primarySeqFrame]; pathingEntity.primarySeqFrame++)
                pathingEntity.primarySeqCycle -= seqType_1.frameDelay[pathingEntity.primarySeqFrame];

            if (pathingEntity.primarySeqFrame >= seqType_1.frameCount) {
                pathingEntity.primarySeqFrame -= seqType_1.delta;
                pathingEntity.primarySeqPlays++;
                if (pathingEntity.primarySeqPlays >= seqType_1.replays)
                    pathingEntity.primarySeq = -1;
                if (pathingEntity.primarySeqFrame < 0 || pathingEntity.primarySeqFrame >= seqType_1.frameCount)
                    pathingEntity.primarySeq = -1;
            }
            pathingEntity.animationStretches = seqType_1.renderPadding;
        }
        if (pathingEntity.primarySeqDelay > 0)
            pathingEntity.primarySeqDelay--;
        if (pathingEntity.spotAnimIndex != -1 && clientClock >= pathingEntity.lastSpotAnimCycle) {
            if (pathingEntity.spotAnimFrame < 0)
                pathingEntity.spotAnimFrame = 0;
            SeqType seqType_2 = SpotAnimType.instances[pathingEntity.spotAnimIndex].seq;
            for (pathingEntity.spotAnimCycle++; pathingEntity.spotAnimFrame < seqType_2.frameCount
                    && pathingEntity.spotAnimCycle > seqType_2.frameDelay[pathingEntity.spotAnimFrame]; pathingEntity.spotAnimFrame++)
                pathingEntity.spotAnimCycle -= seqType_2.frameDelay[pathingEntity.spotAnimFrame];

            if (pathingEntity.spotAnimFrame >= seqType_2.frameCount
                    && (pathingEntity.spotAnimFrame < 0 || pathingEntity.spotAnimFrame >= seqType_2.frameCount))
                pathingEntity.spotAnimIndex = -1;
        }
    }

    public void method69(int i) {
        if (i != 29510)
            anInt1132 = 411;
        if (redrawTitleBackground) {
            redrawTitleBackground = false;
            drawArea1.drawImage(11, super.graphics, 0);
            drawArea2.drawImage(375, super.graphics, 0);
            drawArea3.drawImage(5, super.graphics, 729);
            drawArea4.drawImage(231, super.graphics, 752);
            drawArea5.drawImage(0, super.graphics, 0);
            drawArea6.drawImage(0, super.graphics, 561);
            drawArea7.drawImage(11, super.graphics, 520);
            drawArea8.drawImage(231, super.graphics, 520);
            drawArea9.drawImage(375, super.graphics, 501);
            drawArea10.drawImage(345, super.graphics, 0);
            aBoolean964 = true;
            aBoolean965 = true;
            aBoolean1080 = true;
            aBoolean921 = true;
            if (anInt1078 != 2) {
                drawArea22.drawImage(11, super.graphics, 8);
                drawArea21.drawImage(5, super.graphics, 561);
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
            drawArea21.drawImage(5, super.graphics, 561);
        }
        if (anInt1128 != -1)
            aBoolean1080 = true;
        if (aBoolean1080) {
            if (anInt1128 != -1 && anInt1128 == selectedTab) {
                anInt1128 = -1;
                outBuffer.writeOpcode(175);
                outBuffer.writeByte(selectedTab);
            }
            aBoolean1080 = false;
            drawArea26.bind();
            indexedSprite17.draw(0, 0);
            if (anInt1129 == -1) {
                if (anIntArray861[selectedTab] != -1) {
                    if (selectedTab == 0)
                        indexedSprite10.draw(30, 29);
                    if (selectedTab == 1)
                        indexedSprite11.draw(29, 59);
                    if (selectedTab == 2)
                        indexedSprite11.draw(29, 87);
                    if (selectedTab == 3)
                        indexedSprite12.draw(29, 115);
                    if (selectedTab == 4)
                        indexedSprite14.draw(29, 156);
                    if (selectedTab == 5)
                        indexedSprite14.draw(29, 184);
                    if (selectedTab == 6)
                        indexedSprite13.draw(30, 212);
                }
                if (anIntArray861[0] != -1 && (anInt1128 != 0 || clientClock % 20 < 10))
                    indexedSpritesArray2[0].draw(34, 35);
                if (anIntArray861[1] != -1 && (anInt1128 != 1 || clientClock % 20 < 10))
                    indexedSpritesArray2[1].draw(32, 59);
                if (anIntArray861[2] != -1 && (anInt1128 != 2 || clientClock % 20 < 10))
                    indexedSpritesArray2[2].draw(32, 86);
                if (anIntArray861[3] != -1 && (anInt1128 != 3 || clientClock % 20 < 10))
                    indexedSpritesArray2[3].draw(33, 121);
                if (anIntArray861[4] != -1 && (anInt1128 != 4 || clientClock % 20 < 10))
                    indexedSpritesArray2[4].draw(34, 157);
                if (anIntArray861[5] != -1 && (anInt1128 != 5 || clientClock % 20 < 10))
                    indexedSpritesArray2[5].draw(32, 185);
                if (anIntArray861[6] != -1 && (anInt1128 != 6 || clientClock % 20 < 10))
                    indexedSpritesArray2[6].draw(34, 212);
            }
            drawArea26.drawImage(165, super.graphics, 520);
            drawArea25.bind();
            indexedSprite16.draw(0, 0);
            if (anInt1129 == -1) {
                if (anIntArray861[selectedTab] != -1) {
                    if (selectedTab == 7)
                        indexedSprite1.draw(0, 49);
                    if (selectedTab == 8)
                        indexedSprite2.draw(0, 81);
                    if (selectedTab == 9)
                        indexedSprite2.draw(0, 108);
                    if (selectedTab == 10)
                        indexedSprite3.draw(1, 136);
                    if (selectedTab == 11)
                        indexedSprite5.draw(0, 178);
                    if (selectedTab == 12)
                        indexedSprite5.draw(0, 205);
                    if (selectedTab == 13)
                        indexedSprite4.draw(0, 233);
                }
                if (anIntArray861[8] != -1 && (anInt1128 != 8 || clientClock % 20 < 10))
                    indexedSpritesArray2[7].draw(2, 80);
                if (anIntArray861[9] != -1 && (anInt1128 != 9 || clientClock % 20 < 10))
                    indexedSpritesArray2[8].draw(3, 107);
                if (anIntArray861[10] != -1 && (anInt1128 != 10 || clientClock % 20 < 10))
                    indexedSpritesArray2[9].draw(4, 142);
                if (anIntArray861[11] != -1 && (anInt1128 != 11 || clientClock % 20 < 10))
                    indexedSpritesArray2[10].draw(2, 179);
                if (anIntArray861[12] != -1 && (anInt1128 != 12 || clientClock % 20 < 10))
                    indexedSpritesArray2[11].draw(2, 206);
                if (anIntArray861[13] != -1 && (anInt1128 != 13 || clientClock % 20 < 10))
                    indexedSpritesArray2[12].draw(2, 230);
            }
            drawArea25.drawImage(492, super.graphics, 501);
            drawArea22.bind();
        }
        if (aBoolean921) {
            aBoolean921 = false;
            drawArea24.bind();
            indexedSprite15.draw(0, 0);
            indexedFont2.drawCentered(57, 0xffffff, true, 33, "Public chat");
            if (anInt976 == 0)
                indexedFont2.drawCentered(57, 65280, true, 46, "On");
            if (anInt976 == 1)
                indexedFont2.drawCentered(57, 0xffff00, true, 46, "Friends");
            if (anInt976 == 2)
                indexedFont2.drawCentered(57, 0xff0000, true, 46, "Off");
            if (anInt976 == 3)
                indexedFont2.drawCentered(57, 65535, true, 46, "Hide");
            indexedFont2.drawCentered(186, 0xffffff, true, 33, "Private chat");
            if (anInt755 == 0)
                indexedFont2.drawCentered(186, 65280, true, 46, "On");
            if (anInt755 == 1)
                indexedFont2.drawCentered(186, 0xffff00, true, 46, "Friends");
            if (anInt755 == 2)
                indexedFont2.drawCentered(186, 0xff0000, true, 46, "Off");
            indexedFont2.drawCentered(326, 0xffffff, true, 33, "Trade/duel");
            if (anInt885 == 0)
                indexedFont2.drawCentered(326, 65280, true, 46, "On");
            if (anInt885 == 1)
                indexedFont2.drawCentered(326, 0xffff00, true, 46, "Friends");
            if (anInt885 == 2)
                indexedFont2.drawCentered(326, 0xff0000, true, 46, "Off");
            indexedFont2.drawCentered(462, 0xffffff, true, 38, "Report abuse");
            drawArea24.drawImage(471, super.graphics, 0);
            drawArea22.bind();
        }
        anInt969 = 0;
    }

    public boolean method70(int i, int j) {
        if (j < 0)
            return false;
        int k = actions[j];
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
        int k = paramA[j];
        int l = paramB[j];
        int i1 = actions[j];
        int j1 = paramC[j];
        if (i1 >= 2000)
            i1 -= 2000;
        if (i1 == 903 || i1 == 363) {
            String s = options[j];
            int l1 = s.indexOf("@whi@");
            if (l1 != -1) {
                s = s.substring(l1 + 5).trim();
                String s8 = StringUtils.formatName(StringUtils.fromBase37(StringUtils.toBase37(s)));
                boolean flag4 = false;
                for (int k3 = 0; k3 < entityCount; k3++) {
                    PlayerEntity class38_sub7_sub3_sub2_3 = playerEntities[playerIndices[k3]];
                    if (class38_sub7_sub3_sub2_3 == null || class38_sub7_sub3_sub2_3.name == null
                            || !class38_sub7_sub3_sub2_3.name.equalsIgnoreCase(s8))
                        continue;
                    moveTo(self.pathTileX[0], 1, false,
                            class38_sub7_sub3_sub2_3.pathTileX[0],
                            self.pathTileZ[0], 2, 1,
                            class38_sub7_sub3_sub2_3.pathTileZ[0], 0, 0, 0);
                    if (i1 == 903)
                        outBuffer.writeOpcode(206);
                    if (i1 == 363)
                        outBuffer.writeOpcode(164);
                    outBuffer.writeWord(playerIndices[k3]);
                    flag4 = true;
                    break;
                }

                if (!flag4)
                    addMessage(0, "Unable to find " + s8, (byte) 4, "");
            }
        }
        if (i1 == 450 && method92(75, k, l, j1, true)) {
            outBuffer.writeWord(anInt1005);
            outBuffer.writeWord(anInt1003);
            outBuffer.writeWord(anInt1004);
        }
        if (i1 == 405 || i1 == 38 || i1 == 422 || i1 == 478 || i1 == 347) {
            if (i1 == 478) {
                if ((k & 3) == 0)
                    anInt724++;
                if (anInt724 >= 90)
                    outBuffer.writeOpcode(220);
                outBuffer.writeOpcode(157);
            }
            if (i1 == 347)
                outBuffer.writeOpcode(211);
            if (i1 == 422)
                outBuffer.writeOpcode(133);
            if (i1 == 405) {
                anInt806 += j1;
                if (anInt806 >= 97) {
                    outBuffer.writeOpcode(30);
                    outBuffer.writeSWord(0xe42d58);
                }
                outBuffer.writeOpcode(195);
            }
            if (i1 == 38)
                outBuffer.writeOpcode(71);
            outBuffer.writeWord(j1);
            outBuffer.writeWord(k);
            outBuffer.writeWord(l);
            anInt944 = 0;
            anInt945 = l;
            anInt946 = k;
            anInt947 = 2;
            if (InterfaceComponent.instances[l].anInt270 == anInt971)
                anInt947 = 1;
            if (InterfaceComponent.instances[l].anInt270 == anInt1001)
                anInt947 = 3;
        }
        if (i1 == 728 || i1 == 542 || i1 == 6 || i1 == 963 || i1 == 245) {
            NpcEntity npcEntity = npcEntities[j1];
            if (npcEntity != null) {
                moveTo(self.pathTileX[0], 1, false,
                        npcEntity.pathTileX[0],
                        self.pathTileZ[0], 2, 1,
                        npcEntity.pathTileZ[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                if (i1 == 542)
                    outBuffer.writeOpcode(8);
                if (i1 == 6) {
                    if ((j1 & 3) == 0)
                        anInt867++;
                    if (anInt867 >= 124) {
                        outBuffer.writeOpcode(88);
                        outBuffer.writeDWord(0);
                    }
                    outBuffer.writeOpcode(27);
                }
                if (i1 == 963)
                    outBuffer.writeOpcode(113);
                if (i1 == 728)
                    outBuffer.writeOpcode(194);
                if (i1 == 245) {
                    if ((j1 & 3) == 0)
                        anInt797++;
                    if (anInt797 >= 85) {
                        outBuffer.writeOpcode(176);
                        outBuffer.writeWord(39596);
                    }
                    outBuffer.writeOpcode(100);
                }
                outBuffer.writeWord(j1);
            }
        }
        if (i1 == 217) {
            boolean flag = moveTo(self.pathTileX[0], 0, false, k,
                    self.pathTileZ[0], 2, 0, l, 0, 0, 0);
            if (!flag)
                flag = moveTo(self.pathTileX[0], 1, false, k,
                        self.pathTileZ[0], 2, 1, l, 0, 0, 0);
            anInt738 = super.anInt24;
            anInt739 = super.anInt25;
            anInt741 = 2;
            anInt740 = 0;
            outBuffer.writeOpcode(239);
            outBuffer.writeWord(k + baseTileX);
            outBuffer.writeWord(l + baseTileZ);
            outBuffer.writeWord(j1);
            outBuffer.writeWord(anInt1005);
            outBuffer.writeWord(anInt1003);
            outBuffer.writeWord(anInt1004);
        }
        if (i1 == 1175) {
            int k1 = j1 >> 14 & 0x7fff;
            LocType locType = LocType.get(k1);
            String s9;
            if (locType.description != null)
                s9 = new String(locType.description);
            else
                s9 = "It's a " + locType.name + ".";
            addMessage(0, s9, (byte) 4, "");
        }
        if (i1 == 285)
            method92(245, k, l, j1, true);
        if (i1 == 881) {
            outBuffer.writeOpcode(130);
            outBuffer.writeWord(j1);
            outBuffer.writeWord(k);
            outBuffer.writeWord(l);
            outBuffer.writeWord(anInt1005);
            outBuffer.writeWord(anInt1003);
            outBuffer.writeWord(anInt1004);
            anInt944 = 0;
            anInt945 = l;
            anInt946 = k;
            anInt947 = 2;
            if (InterfaceComponent.instances[l].anInt270 == anInt971)
                anInt947 = 1;
            if (InterfaceComponent.instances[l].anInt270 == anInt1001)
                anInt947 = 3;
        }
        if (i1 == 391) {
            outBuffer.writeOpcode(48);
            outBuffer.writeWord(j1);
            outBuffer.writeWord(k);
            outBuffer.writeWord(l);
            outBuffer.writeWord(anInt1026);
            anInt944 = 0;
            anInt945 = l;
            anInt946 = k;
            anInt947 = 2;
            if (InterfaceComponent.instances[l].anInt270 == anInt971)
                anInt947 = 1;
            if (InterfaceComponent.instances[l].anInt270 == anInt1001)
                anInt947 = 3;
        }
        if (i1 == 660)
            if (!aBoolean879)
                scene.setClick(4, super.anInt25 - 11, super.anInt24 - 8);
            else
                scene.setClick(4, l - 11, k - 8);
        if (i1 == 188) {
            selectedObject = 1;
            anInt1003 = k;
            anInt1004 = l;
            anInt1005 = j1;
            selectedObjName = ObjType.get(j1).name;
            selectedSpell = 0;
            return;
        }
        if (i1 == 44 && !aBoolean872) {
            outBuffer.writeOpcode(235);
            outBuffer.writeWord(l);
            aBoolean872 = true;
        }
        if (i1 == 1773) {
            ObjType objType = ObjType.get(j1);
            String s4;
            if (l >= 0x186a0)
                s4 = l + " x " + objType.name;
            else if (objType.description != null)
                s4 = new String(objType.description);
            else
                s4 = "It's a " + objType.name + ".";
            addMessage(0, s4, (byte) 4, "");
        }
        if (i1 == 900) {
            NpcEntity npcEntity_1 = npcEntities[j1];
            if (npcEntity_1 != null) {
                moveTo(self.pathTileX[0], 1, false,
                        npcEntity_1.pathTileX[0],
                        self.pathTileZ[0], 2, 1,
                        npcEntity_1.pathTileZ[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                outBuffer.writeOpcode(202);
                outBuffer.writeWord(j1);
                outBuffer.writeWord(anInt1005);
                outBuffer.writeWord(anInt1003);
                outBuffer.writeWord(anInt1004);
            }
        }
        if (i1 == 1373 || i1 == 1544 || i1 == 151 || i1 == 1101) {
            PlayerEntity playerEntity = playerEntities[j1];
            if (playerEntity != null) {
                moveTo(self.pathTileX[0], 1, false,
                        playerEntity.pathTileX[0],
                        self.pathTileZ[0], 2, 1,
                        playerEntity.pathTileZ[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                if (i1 == 1101)
                    outBuffer.writeOpcode(164);
                if (i1 == 151) {
                    anInt890++;
                    if (anInt890 >= 90) {
                        outBuffer.writeOpcode(2);
                        outBuffer.writeWord(31114);
                    }
                    outBuffer.writeOpcode(53);
                }
                if (i1 == 1373)
                    outBuffer.writeOpcode(206);
                if (i1 == 1544)
                    outBuffer.writeOpcode(185);
                outBuffer.writeWord(j1);
            }
        }
        if (i1 == 265) {
            NpcEntity npcEntity_2 = npcEntities[j1];
            if (npcEntity_2 != null) {
                moveTo(self.pathTileX[0], 1, false,
                        npcEntity_2.pathTileX[0],
                        self.pathTileZ[0], 2, 1,
                        npcEntity_2.pathTileZ[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                outBuffer.writeOpcode(134);
                outBuffer.writeWord(j1);
                outBuffer.writeWord(anInt1026);
            }
        }
        if (i1 == 679) {
            String s1 = options[j];
            int i2 = s1.indexOf("@whi@");
            if (i2 != -1) {
                long l3 = StringUtils.toBase37(s1.substring(i2 + 5).trim());
                int i4 = -1;
                for (int j4 = 0; j4 < friendCount; j4++) {
                    if (friendName37[j4] != l3)
                        continue;
                    i4 = j4;
                    break;
                }

                if (i4 != -1 && friendWorld[i4] > 0) {
                    aBoolean965 = true;
                    aBoolean1055 = false;
                    aBoolean869 = true;
                    aString765 = "";
                    anInt760 = 3;
                    aLong900 = friendName37[i4];
                    aString775 = "Enter message to send to " + friendName[i4];
                }
            }
        }
        if (i1 == 55 && method92(9, k, l, j1, true))
            outBuffer.writeWord(anInt1026);
        if (i1 == 224 || i1 == 993 || i1 == 99 || i1 == 746 || i1 == 877) {
            boolean flag1 = moveTo(self.pathTileX[0], 0, false,
                    k, self.pathTileZ[0], 2, 0, l, 0, 0, 0);
            if (!flag1)
                flag1 = moveTo(self.pathTileX[0], 1, false, k,
                        self.pathTileZ[0], 2, 1, l, 0, 0, 0);
            anInt738 = super.anInt24;
            anInt739 = super.anInt25;
            anInt741 = 2;
            anInt740 = 0;
            if (i1 == 224)
                outBuffer.writeOpcode(140);
            if (i1 == 746)
                outBuffer.writeOpcode(178);
            if (i1 == 877)
                outBuffer.writeOpcode(247);
            if (i1 == 99)
                outBuffer.writeOpcode(200);
            if (i1 == 993)
                outBuffer.writeOpcode(40);
            outBuffer.writeWord(k + baseTileX);
            outBuffer.writeWord(l + baseTileZ);
            outBuffer.writeWord(j1);
        }
        if (i1 == 1607) {
            NpcEntity class38_sub7_sub3_sub1_3 = npcEntities[j1];
            if (class38_sub7_sub3_sub1_3 != null) {
                String s5;
                if (class38_sub7_sub3_sub1_3.info.description != null)
                    s5 = new String(class38_sub7_sub3_sub1_3.info.description);
                else
                    s5 = "It's a " + class38_sub7_sub3_sub1_3.info.name + ".";
                addMessage(0, s5, (byte) 4, "");
            }
        }
        if (i1 == 504)
            method92(172, k, l, j1, true);
        if (i1 == 930) {
            InterfaceComponent interfaceComponent = InterfaceComponent.instances[l];
            selectedSpell = 1;
            anInt1026 = l;
            selectedFlags = interfaceComponent.anInt319;
            selectedObject = 0;
            String s6 = interfaceComponent.aString317;
            if (s6.indexOf(" ") != -1)
                s6 = s6.substring(0, s6.indexOf(" "));
            String s10 = interfaceComponent.aString317;
            if (s10.indexOf(" ") != -1)
                s10 = s10.substring(s10.indexOf(" ") + 1);
            selectedSpellPrefix = s6 + " " + interfaceComponent.aString318 + " " + s10;
            if (selectedFlags == 16) {
                aBoolean964 = true;
                selectedTab = 3;
                aBoolean1080 = true;
            }
            return;
        }
        if (i1 == 951) {
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[l];
            boolean flag3 = true;
            if (interfaceComponent_1.anInt273 > 0)
                flag3 = method81(false, interfaceComponent_1);
            if (flag3) {
                outBuffer.writeOpcode(155);
                outBuffer.writeWord(l);
            }
        }
        if (i1 == 602 || i1 == 596 || i1 == 22 || i1 == 892 || i1 == 415) {
            if (i1 == 22)
                outBuffer.writeOpcode(212);
            if (i1 == 415) {
                if ((l & 3) == 0)
                    anInt937++;
                if (anInt937 >= 55) {
                    outBuffer.writeOpcode(17);
                    outBuffer.writeDWord(0);
                }
                outBuffer.writeOpcode(6);
            }
            if (i1 == 602)
                outBuffer.writeOpcode(31);
            if (i1 == 892) {
                if ((k & 3) == 0)
                    anInt876++;
                if (anInt876 >= 130) {
                    outBuffer.writeOpcode(238);
                    outBuffer.writeByte(177);
                }
                outBuffer.writeOpcode(38);
            }
            if (i1 == 596)
                outBuffer.writeOpcode(59);
            outBuffer.writeWord(j1);
            outBuffer.writeWord(k);
            outBuffer.writeWord(l);
            anInt944 = 0;
            anInt945 = l;
            anInt946 = k;
            anInt947 = 2;
            if (InterfaceComponent.instances[l].anInt270 == anInt971)
                anInt947 = 1;
            if (InterfaceComponent.instances[l].anInt270 == anInt1001)
                anInt947 = 3;
        }
        if (i1 == 581) {
            if ((j1 & 3) == 0)
                anInt772++;
            if (anInt772 >= 99) {
                outBuffer.writeOpcode(7);
                outBuffer.writeDWord(0);
            }
            method92(97, k, l, j1, true);
        }
        if (i1 == 965) {
            boolean flag2 = moveTo(self.pathTileX[0], 0, false,
                    k, self.pathTileZ[0], 2, 0, l, 0, 0, 0);
            if (!flag2)
                flag2 = moveTo(self.pathTileX[0], 1, false, k,
                        self.pathTileZ[0], 2, 1, l, 0, 0, 0);
            anInt738 = super.anInt24;
            anInt739 = super.anInt25;
            anInt741 = 2;
            anInt740 = 0;
            outBuffer.writeOpcode(138);
            outBuffer.writeWord(k + baseTileX);
            outBuffer.writeWord(l + baseTileZ);
            outBuffer.writeWord(j1);
            outBuffer.writeWord(anInt1026);
        }
        if (i1 == 1501) {
            anInt857 += baseTileZ;
            if (anInt857 >= 92) {
                outBuffer.writeOpcode(66);
                outBuffer.writeDWord(0);
            }
            method92(116, k, l, j1, true);
        }
        if (i1 == 364)
            method92(96, k, l, j1, true);
        if (i1 == 1102) {
            ObjType objType_1 = ObjType.get(j1);
            String s7;
            if (objType_1.description != null)
                s7 = new String(objType_1.description);
            else
                s7 = "It's a " + objType_1.name + ".";
            addMessage(0, s7, (byte) 4, "");
        }
        if (i1 == 960) {
            outBuffer.writeOpcode(155);
            outBuffer.writeWord(l);
            InterfaceComponent interfaceComponent_2 = InterfaceComponent.instances[l];
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
            String s2 = options[j];
            int k2 = s2.indexOf("@whi@");
            if (k2 != -1) {
                method16((byte) -60);
                aString970 = s2.substring(k2 + 5).trim();
                aBoolean881 = false;
                for (int j3 = 0; j3 < InterfaceComponent.instances.length; j3++) {
                    if (InterfaceComponent.instances[j3] == null || InterfaceComponent.instances[j3].anInt273 != 600)
                        continue;
                    anInt907 = anInt971 = InterfaceComponent.instances[j3].anInt270;
                    break;
                }

            }
        }
        if (i1 == 947)
            method16((byte) -60);
        if (i1 == 367) {
            PlayerEntity playerEntity_1 = playerEntities[j1];
            if (playerEntity_1 != null) {
                moveTo(self.pathTileX[0], 1, false,
                        playerEntity_1.pathTileX[0],
                        self.pathTileZ[0], 2, 1,
                        playerEntity_1.pathTileZ[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                outBuffer.writeOpcode(248);
                outBuffer.writeWord(j1);
                outBuffer.writeWord(anInt1005);
                outBuffer.writeWord(anInt1003);
                outBuffer.writeWord(anInt1004);
            }
        }
        if (i1 == 465) {
            outBuffer.writeOpcode(155);
            outBuffer.writeWord(l);
            InterfaceComponent interfaceComponent_3 = InterfaceComponent.instances[l];
            if (interfaceComponent_3.anIntArrayArray278 != null && interfaceComponent_3.anIntArrayArray278[0][0] == 5) {
                int l2 = interfaceComponent_3.anIntArrayArray278[0][1];
                anIntArray938[l2] = 1 - anIntArray938[l2];
                method61(l2, 49);
                aBoolean964 = true;
            }
        }
        if (i1 == 406 || i1 == 436 || i1 == 557 || i1 == 556) {
            String s3 = options[j];
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
            PlayerEntity playerEntity = playerEntities[j1];
            if (playerEntity != null) {
                moveTo(self.pathTileX[0], 1, false,
                        playerEntity.pathTileX[0],
                        self.pathTileZ[0], 2, 1,
                        playerEntity.pathTileZ[0], 0, 0, 0);
                anInt738 = super.anInt24;
                anInt739 = super.anInt25;
                anInt741 = 2;
                anInt740 = 0;
                outBuffer.writeOpcode(177);
                outBuffer.writeWord(j1);
                outBuffer.writeWord(anInt1026);
            }
        }
        selectedObject = 0;
        if (i != 6412)
            method6();
        selectedSpell = 0;
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
            outBuffer.writeByte(216);
        if (Signlink.mainapp != null)
            return Signlink.mainapp.getDocumentBase().getHost().toLowerCase();
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
        Draw2D.fillRect(k, j, j1, l, i1);
        Draw2D.fillRect(k + 1, j + 1, 0, l - 2, 16);
        Draw2D.drawRect(j + 1, 0, i1 - 19, k + 18, l - 2);
        fontBold12.draw(j + 3, k + 14, j1, "Choose Option");
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
        for (int i2 = 0; i2 < optionCount; i2++) {
            int j2 = k + 31 + (optionCount - 1 - i2) * 15;
            int k2 = 0xffffff;
            if (k1 > j && k1 < j + l && l1 > j2 - 13 && l1 < j2 + 3)
                k2 = 0xffff00;
            fontBold12.draw(j + 3, j2, options[i2], true, k2);
        }

        if (i >= 0)
            anInt780 = inBuffer.readByte();
    }

    public void method75(int i, int j, int k) {
        if (anInt833 == 0)
            return;
        int l = 0;
        if (anInt957 != 0)
            l = 1;
        for (int i1 = 0; i1 < 100; i1++)
            if (messageText[i1] != null) {
                int j1 = anIntArray896[i1];
                if ((j1 == 3 || j1 == 7)
                        && (j1 == 7 || anInt755 == 0 || anInt755 == 1 && method138(-20, aStringArray897[i1]))) {
                    int k1 = 329 - l * 13;
                    if (super.anInt21 > 8 && super.anInt21 < 520 && k - 11 > k1 - 10 && k - 11 <= k1 + 3) {
                        if (rights) {
                            options[optionCount] = "Report abuse @whi@" + aStringArray897[i1];
                            actions[optionCount] = 2034;
                            optionCount++;
                        }
                        options[optionCount] = "Add ignore @whi@" + aStringArray897[i1];
                        actions[optionCount] = 2436;
                        optionCount++;
                        options[optionCount] = "Add friend @whi@" + aStringArray897[i1];
                        actions[optionCount] = 2406;
                        optionCount++;
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
            if (--j >= friendCount) {
                interfaceComponent.aString303 = "";
                interfaceComponent.anInt272 = 0;
                return;
            } else {
                interfaceComponent.aString303 = friendName[j];
                interfaceComponent.anInt272 = 1;
                return;
            }
        if (j >= 101 && j <= 200) {
            if ((j -= 101) >= friendCount) {
                interfaceComponent.aString303 = "";
                interfaceComponent.anInt272 = 0;
                return;
            }
            if (friendWorld[j] == 0)
                interfaceComponent.aString303 = "@red@Offline";
            else if (friendWorld[j] == nodeid)
                interfaceComponent.aString303 = "@gre@World-" + (friendWorld[j] - 9);
            else
                interfaceComponent.aString303 = "@yel@World-" + (friendWorld[j] - 9);
            interfaceComponent.anInt272 = 1;
            return;
        }
        if (j == 203) {
            interfaceComponent.anInt282 = friendCount * 15 + 20;
            if (interfaceComponent.anInt282 <= interfaceComponent.anInt275)
                interfaceComponent.anInt282 = interfaceComponent.anInt275 + 1;
            return;
        }
        if (j >= 401 && j <= 500)
            if ((j -= 401) >= ignoreCount) {
                interfaceComponent.aString303 = "";
                interfaceComponent.anInt272 = 0;
                return;
            } else {
                interfaceComponent.aString303 = StringUtils.formatName(StringUtils.fromBase37(ignoreName37[j]));
                interfaceComponent.anInt272 = 1;
                return;
            }
        if (j == 503) {
            interfaceComponent.anInt282 = ignoreCount * 15 + 20;
            if (interfaceComponent.anInt282 <= interfaceComponent.anInt275)
                interfaceComponent.anInt282 = interfaceComponent.anInt275 + 1;
            return;
        }
        if (j == 327) {
            interfaceComponent.anInt315 = 150;
            interfaceComponent.anInt316 = (int) (Math.sin((double) clientClock / 40D) * 256D) & 0x7ff;
            if (aBoolean788) {
                aBoolean788 = false;
                Model[] aclass38_sub2_sub1 = new Model[7];
                int k = 0;
                for (int l = 0; l < 7; l++) {
                    int i1 = anIntArray789[l];
                    if (i1 >= 0)
                        aclass38_sub2_sub1[k++] = IdkType.instances[i1].getModel();
                }

                Model class38_sub2_sub1 = new Model(aclass38_sub2_sub1, k);
                for (int j1 = 0; j1 < 5; j1++)
                    if (characterDesignColors[j1] != 0) {
                        class38_sub2_sub1.recolor(APPEARANCE_COLORS[j1][0],
                                APPEARANCE_COLORS[j1][characterDesignColors[j1]]);
                        if (j1 == 1)
                            class38_sub2_sub1.recolor(BEARD_COLORS[0], BEARD_COLORS[characterDesignColors[j1]]);
                    }

                class38_sub2_sub1.applyGroups();
                class38_sub2_sub1.applyFrame(
                        SeqType.animations[self.standSeq].primaryFrames[0]);
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
            if (characterDesignIsMale) {
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
            if (characterDesignIsMale) {
                interfaceComponent.aClass38_Sub2_Sub2_Sub2_308 = aClass38_Sub2_Sub2_Sub2_961;
                return;
            } else {
                interfaceComponent.aClass38_Sub2_Sub2_Sub2_308 = aClass38_Sub2_Sub2_Sub2_962;
                return;
            }
        }
        if (j == 600) {
            interfaceComponent.aString303 = aString970;
            if (clientClock % 20 < 10) {
                interfaceComponent.aString303 += "|";
                return;
            } else {
                interfaceComponent.aString303 += " ";
                return;
            }
        }
        if (j == 613)
            if (rights) {
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
                interfaceComponent.aString303 = "You last logged in " + s + " from: " + Signlink.dns;
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
            return Signlink.wavesave(abyte0, i);
    }

    public boolean method78(int i) {
        if (i <= 0)
            outBuffer.writeByte(77);
        return Signlink.wavereplay();
    }

    public void method79(int i, int j) {
        if (j != 0)
            objects = null;
        Signlink.wavevol = i;
    }

    public void updateLocalNpcs(Buffer buffer, int i) {
        do {
            if (buffer.bitOffset + 21 >= i * 8) {
                break;
            }

            int j = buffer.getBits(13);
            if (j == 8191) {
                break;
            }

            if (npcEntities[j] == null) {
                npcEntities[j] = new NpcEntity();
            }

            NpcEntity npc = npcEntities[j];
            npcIndices[npcCount++] = j;
            npc.remove = clientClock;
            npc.info = NpcType.get(buffer.getBits(11));
            npc.index = npc.info.size;
            npc.runSeq = npc.info.walkSeq;
            npc.walkSeq = npc.info.turnAroundSeq;
            npc.turnAroundSeq = npc.info.turnRightSeq;
            npc.turnRightSeq = npc.info.turnLeftSeq;
            npc.standSeq = npc.info.standSeq;

            int x = buffer.getBits(5);
            if (x > 15) {
                x -= 32;
            }

            int z = buffer.getBits(5);
            if (z > 15) {
                z -= 32;
            }

            npc.move(false, self.pathTileX[0] + x, self.pathTileZ[0] + z);

            int hasUpdate = buffer.getBits(1);
            if (hasUpdate == 1) {
                entityUpdateIndices[updateCount++] = j;
            }
        } while (true);

        buffer.accessBytes();
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
                        l1 = IdkType.count - 1;
                    if (i1 == 1 && ++l1 >= IdkType.count)
                        l1 = 0;
                } while (IdkType.instances[l1].validStyle
                        || IdkType.instances[l1].type != j + (characterDesignIsMale ? 0 : 7));
                anIntArray789[j] = l1;
                aBoolean788 = true;
            }
        }
        if (i >= 314 && i <= 323) {
            int k = (i - 314) / 2;
            int j1 = i & 1;
            int i2 = characterDesignColors[k];
            if (j1 == 0 && --i2 < 0)
                i2 = APPEARANCE_COLORS[k].length - 1;
            if (j1 == 1 && ++i2 >= APPEARANCE_COLORS[k].length)
                i2 = 0;
            characterDesignColors[k] = i2;
            aBoolean788 = true;
        }
        if (i == 324 && !characterDesignIsMale) {
            characterDesignIsMale = true;
            resetCharacterDesign((byte) -6);
        }
        if (i == 325 && characterDesignIsMale) {
            characterDesignIsMale = false;
            resetCharacterDesign((byte) -6);
        }
        if (i == 326) {
            outBuffer.writeOpcode(52);
            outBuffer.writeByte(characterDesignIsMale ? 0 : 1);
            for (int l = 0; l < 7; l++)
                outBuffer.writeByte(anIntArray789[l]);

            for (int k1 = 0; k1 < 5; k1++)
                outBuffer.writeByte(characterDesignColors[k1]);

            return true;
        }
        if (i == 613)
            aBoolean881 = !aBoolean881;
        if (i >= 601 && i <= 612) {
            method16((byte) -60);
            if (aString970.length() > 0) {
                outBuffer.writeOpcode(190);
                outBuffer.writeQWord(StringUtils.toBase37(aString970));
                outBuffer.writeByte(i - 601);
                outBuffer.writeByte(aBoolean881 ? 1 : 0);
            }
        }
        return false;
    }

    public void method6() {
        if (Signlink.sunjava)
            super.anInt9 = 5;
        if (!lowMemory) {
            aBoolean799 = true;
            aBoolean812 = true;
            startThread(this, 2);
            method14(0xbc614e, "scape_main", 40000);
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
                showProgress(true, "Connecting to fileserver", 10);
                try {
                    DataInputStream datainputstream = method94("crc" + (int) (Math.random() * 99999999D));
                    Buffer class38_sub2_sub3 = new Buffer(new byte[36]);
                    datainputstream.readFully(class38_sub2_sub3.data, 0, 36);
                    for (int k = 0; k < 9; k++)
                        anIntArray811[k] = class38_sub2_sub3.readDWord();

                    datainputstream.close();
                } catch (IOException _ex) {
                    for (int j = i; j > 0; j--) {
                        showProgress(true, "Error loading - Will retry in " + j + " secs.", 10);
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
            indexedFont1 = new IndexedFont(this.fileArchive, "p11", 530);
            indexedFont2 = new IndexedFont(this.fileArchive, "p12", 530);
            fontBold12 = new IndexedFont(this.fileArchive, "b12", 530);
            indexedFont4 = new IndexedFont(this.fileArchive, "q8", 530);
            method128((byte) 5);
            createTitleImages();
            FileArchive fileArchive = method37("config", anIntArray811[2], "config", 15, 0);
            FileArchive fileArchive_1 = method37("interface", anIntArray811[3], "interface", 20, 0);
            FileArchive fileArchive_2 = method37("2d graphics", anIntArray811[4], "media", 30, 0);
            FileArchive fileArchive_3 = method37("3d graphics", anIntArray811[5], "models", 40, 0);
            FileArchive fileArchive_4 = method37("textures", anIntArray811[6], "textures", 60, 0);
            FileArchive fileArchive_5 = method37("chat system", anIntArray811[7], "wordenc", 65, 0);
            FileArchive fileArchive_6 = method37("sound effects", anIntArray811[8], "sounds", 70, 0);
            aByteArrayArrayArray840 = new byte[4][104][104];
            anIntArrayArrayArray794 = new int[4][105][105];
            scene = new Scene(anIntArrayArrayArray794, 104, 4, 104);
            for (int l = 0; l < 4; l++)
                collisionMaps[l] = new CollisionMap(104, 104);

            aClass38_Sub2_Sub2_Sub2_1053 = new Sprite(512, 512);
            showProgress(true, "Unpacking media", 75);
            indexedSprite18 = new IndexedSprite(fileArchive_2, "invback", 0);
            indexedSprite20 = new IndexedSprite(fileArchive_2, "chatback", 0);
            indexedSprite19 = new IndexedSprite(fileArchive_2, "mapback", 0);
            indexedSprite15 = new IndexedSprite(fileArchive_2, "backbase1", 0);
            indexedSprite16 = new IndexedSprite(fileArchive_2, "backbase2", 0);
            indexedSprite17 = new IndexedSprite(fileArchive_2, "backhmid1", 0);
            for (int i1 = 0; i1 < 13; i1++)
                indexedSpritesArray2[i1] = new IndexedSprite(fileArchive_2, "sideicons", i1);

            aClass38_Sub2_Sub2_Sub2_1145 = new Sprite(fileArchive_2, "compass", 0);
            try {
                for (int j1 = 0; j1 < 50; j1++)
                    indexedSpritesArray1[j1] = new IndexedSprite(fileArchive_2, "mapscene", j1);

            } catch (Exception _ex) {
            }
            try {
                for (int k1 = 0; k1 < 50; k1++)
                    aClass38_Sub2_Sub2_Sub2Array1138[k1] = new Sprite(fileArchive_2, "mapfunction", k1);

            } catch (Exception _ex) {
            }
            try {
                for (int l1 = 0; l1 < 20; l1++)
                    aClass38_Sub2_Sub2_Sub2Array776[l1] = new Sprite(fileArchive_2, "hitmarks", l1);

            } catch (Exception _ex) {
            }
            try {
                for (int i2 = 0; i2 < 20; i2++)
                    aClass38_Sub2_Sub2_Sub2Array956[i2] = new Sprite(fileArchive_2, "headicons", i2);

            } catch (Exception _ex) {
            }
            aClass38_Sub2_Sub2_Sub2_997 = new Sprite(fileArchive_2, "mapflag", 0);
            for (int j2 = 0; j2 < 8; j2++)
                aClass38_Sub2_Sub2_Sub2Array1120[j2] = new Sprite(fileArchive_2, "cross", j2);

            aClass38_Sub2_Sub2_Sub2_1057 = new Sprite(fileArchive_2, "mapdots", 0);
            aClass38_Sub2_Sub2_Sub2_1058 = new Sprite(fileArchive_2, "mapdots", 1);
            aClass38_Sub2_Sub2_Sub2_1059 = new Sprite(fileArchive_2, "mapdots", 2);
            aClass38_Sub2_Sub2_Sub2_1060 = new Sprite(fileArchive_2, "mapdots", 3);
            indexedSprite6 = new IndexedSprite(fileArchive_2, "scrollbar", 0);
            indexedSprite7 = new IndexedSprite(fileArchive_2, "scrollbar", 1);
            indexedSprite10 = new IndexedSprite(fileArchive_2, "redstone1", 0);
            indexedSprite11 = new IndexedSprite(fileArchive_2, "redstone2", 0);
            indexedSprite12 = new IndexedSprite(fileArchive_2, "redstone3", 0);
            indexedSprite13 = new IndexedSprite(fileArchive_2, "redstone1", 0);
            indexedSprite13.flipHorizontally();
            indexedSprite14 = new IndexedSprite(fileArchive_2, "redstone2", 0);
            indexedSprite14.flipHorizontally();
            indexedSprite1 = new IndexedSprite(fileArchive_2, "redstone1", 0);
            indexedSprite1.flipVertically();
            indexedSprite2 = new IndexedSprite(fileArchive_2, "redstone2", 0);
            indexedSprite2.flipVertically();
            indexedSprite3 = new IndexedSprite(fileArchive_2, "redstone3", 0);
            indexedSprite3.flipVertically();
            indexedSprite4 = new IndexedSprite(fileArchive_2, "redstone1", 0);
            indexedSprite4.flipHorizontally();
            indexedSprite4.flipVertically();
            indexedSprite5 = new IndexedSprite(fileArchive_2, "redstone2", 0);
            indexedSprite5.flipHorizontally();
            indexedSprite5.flipVertically();
            Sprite class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backleft1", 0);
            drawArea1 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backleft2", 0);
            drawArea2 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backright1", 0);
            drawArea3 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backright2", 0);
            drawArea4 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backtop1", 0);
            drawArea5 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backtop2", 0);
            drawArea6 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backvmid1", 0);
            drawArea7 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backvmid2", 0);
            drawArea8 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backvmid3", 0);
            drawArea9 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            class38_sub2_sub2_sub2 = new Sprite(fileArchive_2, "backhmid2", 0);
            drawArea10 = new DrawArea(method11(aByte1116), class38_sub2_sub2_sub2.width,
                    class38_sub2_sub2_sub2.height);
            class38_sub2_sub2_sub2.drawOpaque(0, 0);
            int k2 = (int) (Math.random() * 21D) - 10;
            int l2 = (int) (Math.random() * 21D) - 10;
            int i3 = (int) (Math.random() * 21D) - 10;
            int j3 = (int) (Math.random() * 41D) - 20;
            for (int k3 = 0; k3 < 50; k3++) {
                if (aClass38_Sub2_Sub2_Sub2Array1138[k3] != null)
                    aClass38_Sub2_Sub2_Sub2Array1138[k3].translate(k2 + j3, l2 + j3, i3 + j3);
                if (indexedSpritesArray1[k3] != null)
                    indexedSpritesArray1[k3].translate(k2 + j3, l2 + j3, i3 + j3);
            }

            showProgress(true, "Unpacking textures", 80);
            Draw3D.unpackTextures(fileArchive_4);
            Draw3D.setBrightness(0.80000000000000004D);
            Draw3D.setupPools(20);
            showProgress(true, "Unpacking models", 83);
            Model.load(fileArchive_3);
            SeqBase.load(fileArchive_3);
            SeqFrame.load(fileArchive_3);
            showProgress(true, "Unpacking config", 86);
            SeqType.load(fileArchive);
            LocType.load(fileArchive);
            FloType.load(fileArchive);
            ObjType.load(fileArchive);
            NpcType.load(fileArchive);
            IdkType.load(fileArchive);
            SpotAnimType.load(fileArchive);
            VarpType.load(fileArchive);
            ObjType.isMember = members;
            if (!lowMemory) {
                showProgress(true, "Unpacking sounds", 90);
                byte[] abyte0 = fileArchive_6.read("sounds.dat", null);
                Buffer class38_sub2_sub3_1 = new Buffer(abyte0);
                SoundTrack.load(class38_sub2_sub3_1);
            }
            showProgress(true, "Unpacking interfaces", 92);
            IndexedFont[] aclass38_sub2_sub2_sub4 = {
                    indexedFont1, indexedFont2, fontBold12,
                    indexedFont4
            };
            InterfaceComponent.method218(fileArchive_2, aclass38_sub2_sub2_sub4, 30, fileArchive_1);
            showProgress(true, "Preparing game engine", 97);
            for (int l3 = 0; l3 < 33; l3++) {
                int i4 = 999;
                int k4 = 0;
                for (int i5 = 0; i5 < 35; i5++) {
                    if (indexedSprite19.pixels[i5
                            + l3 * indexedSprite19.width] == 0) {
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
                    if (indexedSprite19.pixels[l5 + j4 * indexedSprite19.width] == 0
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

            Draw3D.prepareOffsets(96, 479);
            anIntArray735 = Draw3D.offsets;
            Draw3D.prepareOffsets(261, 190);
            anIntArray736 = Draw3D.offsets;
            Draw3D.prepareOffsets(334, 512);
            anIntArray737 = Draw3D.offsets;
            int[] ai = new int[9];
            for (int k5 = 0; k5 < 9; k5++) {
                int i6 = 128 + k5 * 32 + 15;
                int j6 = 600 + i6 * 3;
                int k6 = Draw3D.sin[i6];
                ai[k5] = j6 * k6 >> 16;
            }

            Scene.init(ai, 800, 512, 334, 500);
            WordEncoding.load(fileArchive_5);
            return;
        } catch (Exception exception) {
            aBoolean865 = true;
        }
    }

    public void method82(int i) {
        if (anInt846 != 0)
            return;
        options[0] = "Cancel";
        actions[0] = 1252;
        optionCount = 1;
        method75(super.anInt21, 27078, super.anInt22);
        anInt868 = 0;
        i = 12 / i;
        if (super.anInt21 > 8 && super.anInt22 > 11 && super.anInt21 < 520 && super.anInt22 < 345)
            if (anInt971 != -1)
                method29(super.anInt22, super.anInt21, 11, InterfaceComponent.instances[anInt971], 5082, 8, 0);
            else
                method131((byte) 2);
        if (anInt868 != anInt1063)
            anInt1063 = anInt868;
        anInt868 = 0;
        if (super.anInt21 > 562 && super.anInt22 > 231 && super.anInt21 < 752 && super.anInt22 < 492)
            if (anInt1129 != -1)
                method29(super.anInt22, super.anInt21, 231, InterfaceComponent.instances[anInt1129], 5082, 562, 0);
            else if (anIntArray861[selectedTab] != -1)
                method29(super.anInt22, super.anInt21, 231, InterfaceComponent.instances[anIntArray861[selectedTab]], 5082,
                        562, 0);
        if (anInt868 != anInt941) {
            aBoolean964 = true;
            anInt941 = anInt868;
        }
        anInt868 = 0;
        if (super.anInt21 > 22 && super.anInt22 > 375 && super.anInt21 < 431 && super.anInt22 < 471)
            if (anInt1001 != -1)
                method29(super.anInt22, super.anInt21, 375, InterfaceComponent.instances[anInt1001], 5082, 22, 0);
            else
                method31(super.anInt22 - 375, 0, super.anInt21 - 22);
        if (anInt1001 != -1 && anInt868 != anInt859) {
            aBoolean965 = true;
            anInt859 = anInt868;
        }
        for (boolean flag = false; !flag; ) {
            flag = true;
            for (int j = 0; j < optionCount - 1; j++)
                if (actions[j] < 1000 && actions[j + 1] > 1000) {
                    String s = options[j];
                    options[j] = options[j + 1];
                    options[j + 1] = s;
                    int k = actions[j];
                    actions[j] = actions[j + 1];
                    actions[j + 1] = k;
                    k = paramA[j];
                    paramA[j] = paramA[j + 1];
                    paramA[j + 1] = k;
                    k = paramB[j];
                    paramB[j] = paramB[j + 1];
                    paramB[j + 1] = k;
                    k = paramC[j];
                    paramC[j] = paramC[j + 1];
                    paramC[j + 1] = k;
                    flag = false;
                }

        }

    }

    public void method83(byte byte0) {
        LocType.models.clear();
        LocType.builtModels.clear();
        NpcType.models.clear();
        ObjType.modelCache.clear();
        ObjType.iconCache.clear();
        PlayerEntity.models.clear();
        SpotAnimType.models.clear();
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
            aClass38_Sub2_Sub2_Sub2Array1120[anInt740 / 100].draw(anInt739 - 8 - 11, anInt738 - 8 - 8);
        if (anInt741 == 2)
            aClass38_Sub2_Sub2_Sub2Array1120[4 + anInt740 / 100].draw(anInt739 - 8 - 11, anInt738 - 8 - 8);
        if (anInt971 != -1) {
            method110(anInt971, anInt969, 623);
            method59(0, 0, 38682, InterfaceComponent.instances[anInt971], 0);
        }
        method18(39734);
        if (!aBoolean879) {
            method82(26);
            drawTooltip();
        } else if (anInt1148 == 0)
            method74(-961);
        if (inMultizone == 1)
            if (wildernessLevel > 0 || anInt933 == 1)
                aClass38_Sub2_Sub2_Sub2Array956[1].draw(258, 472);
            else
                aClass38_Sub2_Sub2_Sub2Array956[1].draw(296, 472);
        if (wildernessLevel > 0) {
            aClass38_Sub2_Sub2_Sub2Array956[0].draw(296, 472);
            indexedFont2.drawRightAligned(329, 0xffff00, "Level: " + wildernessLevel, 484);
        }
        if (anInt933 == 1) {
            aClass38_Sub2_Sub2_Sub2Array956[6].draw(296, 472);
            indexedFont2.drawRightAligned(329, 0xffff00, "Arena", 484);
        }
        if (anInt957 != 0) {
            int k = anInt957 / 50;
            int l = k / 60;
            k %= 60;
            if (k < 10) {
                indexedFont2.draw(4, 329, 0xffff00, "System update in: " + l + ":0" + k);
                return;
            }
            indexedFont2.draw(4, 329, 0xffff00, "System update in: " + l + ":" + k);
        }
    }

    public void method85(int i) {
        int j = self.x + cameraAnticheatOffsetX;
        int k = self.z + cameraAnticheatOffsetZ;
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
        cameraYaw = cameraYaw + anInt818 / 2 & 0x7ff;
        anInt779 += i;
        anInt816 += anInt819 / 2;
        if (anInt816 < 128)
            anInt816 = 128;
        if (anInt816 > 383)
            anInt816 = 383;
        int l = anInt914 >> 7;
        int i1 = anInt915 >> 7;
        int j1 = method33(currentLevel, anInt914, (byte) 5, anInt915);
        int k1 = 0;
        if (l > 3 && i1 > 3 && l < 100 && i1 < 100) {
            for (int l1 = l - 4; l1 <= l + 4; l1++) {
                for (int j2 = i1 - 4; j2 <= i1 + 4; j2++) {
                    int k2 = currentLevel;
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
        for (ProjectileEntity projectileEntity = (ProjectileEntity) projectiles
                .peekLast(); projectileEntity != null; projectileEntity = (ProjectileEntity) projectiles
                .getPrevious())
            if (projectileEntity.level != currentLevel || clientClock > projectileEntity.lastCycle)
                projectileEntity.unlink();
            else if (clientClock >= projectileEntity.firstCycle) {
                if (projectileEntity.targetIndex > 0) {
                    NpcEntity npcEntity = npcEntities[projectileEntity.targetIndex
                            - 1];
                    if (npcEntity != null)
                        projectileEntity.setTarget(
                                method33(projectileEntity.level,
                                        npcEntity.x, (byte) 5,
                                        npcEntity.z)
                                        - projectileEntity.baseZ,
                                npcEntity.z,
                                npcEntity.x, clientClock);
                }
                if (projectileEntity.targetIndex < 0) {
                    int i = -projectileEntity.targetIndex - 1;
                    PlayerEntity playerEntity;
                    if (i == anInt734)
                        playerEntity = self;
                    else
                        playerEntity = playerEntities[i];
                    if (playerEntity != null)
                        projectileEntity.setTarget(
                                method33(projectileEntity.level,
                                        playerEntity.x, (byte) 5,
                                        playerEntity.z)
                                        - projectileEntity.baseZ,
                                playerEntity.z,
                                playerEntity.x, clientClock);
                }
                projectileEntity.update(anInt969);
                scene.add((int) projectileEntity.y, 60, projectileEntity.yaw,
                        (int) projectileEntity.x, -1, false, null, projectileEntity,
                        (int) projectileEntity.z, currentLevel);
            }

    }

    public void method10(int i) {
        if (i != 3)
            aBoolean870 = !aBoolean870;
        redrawTitleBackground = true;
    }

    public void method87(int i, int j, Sprite class38_sub2_sub2_sub2, int k) {
        int l = cameraYaw + minimapAnticheatAngle & 0x7ff;
        int i1 = k * k + i * i;
        if (j != 4)
            aBoolean860 = !aBoolean860;
        if (i1 > 6400)
            return;
        int j1 = Model.sin[l];
        int k1 = Model.cos[l];
        j1 = (j1 * 256) / (minimapZoom + 256);
        k1 = (k1 * 256) / (minimapZoom + 256);
        int l1 = i * j1 + k * k1 >> 16;
        int i2 = i * k1 - k * j1 >> 16;
        if (i1 > 2500) {
            class38_sub2_sub2_sub2.drawMasked(indexedSprite19,
                    83 - i2 - class38_sub2_sub2_sub2.cropH / 2, (94 + l1) - class38_sub2_sub2_sub2.cropW / 2
            );
            return;
        } else {
            class38_sub2_sub2_sub2.draw(83 - i2 - class38_sub2_sub2_sub2.cropH / 2,
                    (94 + l1) - class38_sub2_sub2_sub2.cropW / 2);
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

    public void method90(int i, boolean flag, PathingEntity pathingEntity) {
        method91(pathingEntity.z, pathingEntity.x, anInt1105, i);
        if (flag)
            anInt780 = -1;
    }

    public void method91(int i, int j, int k, int l) {
        if (j < 128 || i < 128 || j > 13056 || i > 13056) {
            anInt1019 = -1;
            anInt1020 = -1;
            return;
        }
        int i1 = method33(currentLevel, j, (byte) 5, i) - l;
        j -= anInt1111;
        i1 -= anInt1112;
        i -= anInt1113;
        int j1 = Model.sin[anInt1114];
        int k1 = Model.cos[anInt1114];
        int l1 = Model.sin[anInt1115];
        int i2 = Model.cos[anInt1115];
        int j2 = i * l1 + j * i2 >> 16;
        i = i * i2 - j * l1 >> 16;
        if (k >= 0)
            outBuffer.writeByte(131);
        j = j2;
        j2 = i1 * k1 - i * j1 >> 16;
        i = i1 * j1 + i * k1 >> 16;
        i1 = j2;
        if (i >= 50) {
            anInt1019 = Draw3D.centerX + (j << 9) / i;
            anInt1020 = Draw3D.centerY + (i1 << 9) / i;
            return;
        } else {
            anInt1019 = -1;
            anInt1020 = -1;
            return;
        }
    }

    public boolean method92(int i, int j, int k, int l, boolean flag) {
        int i1 = l >> 14 & 0x7fff;
        int j1 = scene.getInfo(currentLevel, j, k, l);
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
            moveTo(self.pathTileX[0], i2, false, j,
                    self.pathTileZ[0], 2, j2, k, 0, 0, k2);
        } else {
            moveTo(self.pathTileX[0], 0, false, j,
                    self.pathTileZ[0], 2, 0, k, l1, k1 + 1, 0);
        }
        anInt738 = super.anInt24;
        anInt739 = super.anInt25;
        anInt741 = 2;
        anInt740 = 0;
        outBuffer.writeOpcode(i);
        outBuffer.writeWord(j + baseTileX);
        outBuffer.writeWord(k + baseTileZ);
        outBuffer.writeWord(i1);
        if (!flag)
            throw new NullPointerException();
        else
            return true;
    }

    public void method93(int i) {
        int j = fontBold12.stringWidth("Choose Option");
        for (int k = 0; k < optionCount; k++) {
            int l = fontBold12.stringWidth(options[k]);
            if (l > j)
                j = l;
        }

        j += 8;
        int i1 = 15 * optionCount + 21;
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
            anInt1152 = 15 * optionCount + 22;
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
            anInt1152 = 15 * optionCount + 22;
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
            anInt1152 = 15 * optionCount + 22;
        }
    }

    public DataInputStream method94(String s)
            throws IOException {
        if (Signlink.mainapp != null)
            return Signlink.openurl(s);
        else
            return new DataInputStream((new URL(getCodeBase(), s)).openStream());
    }

    public void prepareTitleScreen(byte byte0) {
        if (imageTitle2 != null)
            return;
        super.drawArea = null;
        drawArea23 = null;
        drawArea21 = null;
        drawArea20 = null;
        drawArea22 = null;
        drawArea24 = null;
        drawArea25 = null;
        drawArea26 = null;
        imageTitle0 = new DrawArea(method11(aByte1116), 128, 265);
        Draw2D.clear();
        imageTitle1 = new DrawArea(method11(aByte1116), 128, 265);
        Draw2D.clear();
        imageTitle2 = new DrawArea(method11(aByte1116), 533, 186);
        Draw2D.clear();
        imageTitle3 = new DrawArea(method11(aByte1116), 360, 146);
        Draw2D.clear();
        imageTitle4 = new DrawArea(method11(aByte1116), 360, 200);
        Draw2D.clear();
        imageTitle5 = new DrawArea(method11(aByte1116), 214, 267);
        Draw2D.clear();
        imageTitle6 = new DrawArea(method11(aByte1116), 215, 267);
        Draw2D.clear();
        imageTitle7 = new DrawArea(method11(aByte1116), 86, 79);
        if (byte0 != aByte866)
            outBuffer.writeByte(73);
        Draw2D.clear();
        imageTitle8 = new DrawArea(method11(aByte1116), 87, 79);
        Draw2D.clear();
        if (fileArchive != null) {
            method128((byte) 5);
            createTitleImages();
        }
        redrawTitleBackground = true;
    }

    public void runFlames(int i) {
        aBoolean1121 = true;
        try {
            long l = System.currentTimeMillis();
            int j = 0;
            int k = 20;
            while (flameActive) {
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
        if (flamesThread) {
            runFlames(-33833);
            return;
        }

        if (aBoolean799) {
            method26(true);
        } else {
            super.run();
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

    public void login(String username, String password, boolean reconnect) {
        Signlink.errorname = username;

        try {
            if (!reconnect) {
                loginMessage0 = "";
                loginMessage1 = "Connecting to server...";
                drawTitleScreen(4);
            }

            bufferedStream = new BufferedStream(this, opensocket(43594 + portoff));
            bufferedStream.read(inBuffer.data, 0, 8);
            inBuffer.offset = 0;

            serverSeed = inBuffer.readQWord();
            int[] isaacSeed = new int[4];
            isaacSeed[0] = (int) (Math.random() * 99999999D);
            isaacSeed[1] = (int) (Math.random() * 99999999D);
            isaacSeed[2] = (int) (serverSeed >> 32);
            isaacSeed[3] = (int) serverSeed;

            outBuffer.offset = 0;
            outBuffer.writeByte(10);
            outBuffer.writeDWord(isaacSeed[0]);
            outBuffer.writeDWord(isaacSeed[1]);
            outBuffer.writeDWord(isaacSeed[2]);
            outBuffer.writeDWord(isaacSeed[3]);
            outBuffer.writeDWord(Signlink.uid);
            outBuffer.writeString(username);
            outBuffer.writeString(password);
            outBuffer.encryptPublic(modulus, exponent);

            loginBuffer.offset = 0;
            if (reconnect) {
                loginBuffer.writeByte(18);
            } else {
                loginBuffer.writeByte(16);
            }
            loginBuffer.writeByte(outBuffer.offset + 36 + 1 + 1);
            loginBuffer.writeByte(225);
            loginBuffer.writeByte(lowMemory ? 1 : 0);
            for (int i = 0; i < 9; i++) {
                loginBuffer.writeDWord(anIntArray811[i]);
            }
            loginBuffer.writeBytes(outBuffer.data, outBuffer.offset, 0);

            outBuffer.isaacState = new IsaacRandom(isaacSeed);
            for (int j = 0; j < 4; j++) {
                isaacSeed[j] += 50;
            }
            isaacState = new IsaacRandom(isaacSeed);

            bufferedStream.write(loginBuffer.data, loginBuffer.offset, 0);

            int response = bufferedStream.read();
            if (response == 1) {
                try {
                    Thread.sleep(2000L);
                } catch (Exception _ex) {
                }
                login(username, password, reconnect);
                return;
            }

            if (response == 2 || response == 18) {
                rights = response == 18;
                InputTracking.method183((byte) 65);
                aBoolean974 = true;
                outBuffer.offset = 0;
                inBuffer.offset = 0;
                anInt780 = -1;
                anInt828 = -1;
                anInt829 = -1;
                anInt830 = -1;
                anInt779 = 0;
                anInt781 = 0;
                anInt957 = 0;
                anInt783 = 0;
                anInt911 = 0;
                optionCount = 0;
                aBoolean879 = false;
                super.anInt19 = 0;
                for (int l = 0; l < 100; l++) {
                    messageText[l] = null;
                }
                selectedObject = 0;
                selectedSpell = 0;
                anInt1078 = 0;
                anInt1018 = 0;
                cameraAnticheatOffsetX = (int) (Math.random() * 100D) - 50;
                cameraAnticheatOffsetZ = (int) (Math.random() * 110D) - 55;
                cameraAnticheatAngle = (int) (Math.random() * 80D) - 40;
                minimapAnticheatAngle = (int) (Math.random() * 120D) - 60;
                minimapZoom = (int) (Math.random() * 30D) - 20;
                cameraYaw = (int) (Math.random() * 20D) - 10 & 0x7ff;
                lastSceneLevel = -1;
                flagTileX = 0;
                flagTileY = 0;
                entityCount = 0;
                npcCount = 0;
                for (int i1 = 0; i1 < MAX_PLAYER_COUNT; i1++) {
                    playerEntities[i1] = null;
                    playerBuffers[i1] = null;
                }
                for (int j1 = 0; j1 < 8192; j1++) {
                    npcEntities[j1] = null;
                }
                self = playerEntities[LOCAL_PLAYER_INDEX] = new PlayerEntity();
                projectiles.clear();
                spotanims.clear();
                temporaryLocs.clear();
                for (int k1 = 0; k1 < 4; k1++) {
                    for (int l1 = 0; l1 < 104; l1++) {
                        for (int j2 = 0; j2 < 104; j2++) {
                            objects[k1][l1][j2] = null;
                        }
                    }
                }
                spawnedLocations = new LinkedList();
                friendCount = 0;
                anInt1021 = -1;
                anInt1001 = -1;
                anInt971 = -1;
                anInt1129 = -1;
                aBoolean872 = false;
                selectedTab = 3;
                aBoolean1055 = false;
                aBoolean879 = false;
                aBoolean869 = false;
                aString936 = null;
                inMultizone = 0;
                anInt1128 = -1;
                characterDesignIsMale = true;
                resetCharacterDesign((byte) -6);
                for (int i2 = 0; i2 < 5; i2++) {
                    characterDesignColors[i2] = 0;
                }
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
                prepareGameScreen(-7185);
                return;
            }
            if (response == 3) {
                loginMessage0 = "";
                loginMessage1 = "Invalid username or password.";
                return;
            }
            if (response == 4) {
                loginMessage0 = "Your account has been disabled.";
                loginMessage1 = "Please check your message-centre for details.";
                return;
            }
            if (response == 5) {
                loginMessage0 = "Your account is already logged in.";
                loginMessage1 = "Try again in 60 secs...";
                return;
            }
            if (response == 6) {
                loginMessage0 = "RuneScape has been updated!";
                loginMessage1 = "Please reload this page.";
                return;
            }
            if (response == 7) {
                loginMessage0 = "This world is full.";
                loginMessage1 = "Please use a different world.";
                return;
            }
            if (response == 8) {
                loginMessage0 = "Unable to connect.";
                loginMessage1 = "Login server offline.";
                return;
            }
            if (response == 9) {
                loginMessage0 = "Login limit exceeded.";
                loginMessage1 = "Too many connections from your address.";
                return;
            }
            if (response == 10) {
                loginMessage0 = "Unable to connect.";
                loginMessage1 = "Bad session id.";
                return;
            }
            if (response == 11) {
                loginMessage0 = "Login server rejected session.";
                loginMessage1 = "Please try again.";
                return;
            }
            if (response == 12) {
                loginMessage0 = "You need a members account to login to this world.";
                loginMessage1 = "Please subscribe, or use a different world.";
                return;
            }
            if (response == 13) {
                loginMessage0 = "Could not complete login.";
                loginMessage1 = "Please try using a different world.";
                return;
            }
            if (response == 14) {
                loginMessage0 = "The server is being updated.";
                loginMessage1 = "Please wait 1 minute and try again.";
                return;
            }
            if (response == 15) {
                aBoolean974 = true;
                outBuffer.offset = 0;
                inBuffer.offset = 0;
                anInt780 = -1;
                anInt828 = -1;
                anInt829 = -1;
                anInt830 = -1;
                anInt779 = 0;
                anInt781 = 0;
                anInt957 = 0;
                optionCount = 0;
                aBoolean879 = false;
                return;
            }
            if (response == 16) {
                loginMessage0 = "Login attempts exceeded.";
                loginMessage1 = "Please wait 1 minute and try again.";
                return;
            }
            if (response == 17) {
                loginMessage0 = "You are standing in a members-only area.";
                loginMessage1 = "To play on this world move to a free area first";
                return;
            }
        } catch (IOException _ex) {
            loginMessage0 = "";
            loginMessage1 = "Error connecting to server.";
        }
    }

    public void method99(int i, int j, int k, int l, int i1, int j1, int k1,
                         int l1) {
        if (k1 != -27819)
            method6();
        if (j >= 1 && k >= 1 && j <= 102 && k <= 102) {
            if (lowMemory && l1 != currentLevel)
                return;
            int i2 = 0;
            byte byte0 = -1;
            boolean flag = false;
            boolean flag1 = false;
            if (l == 0)
                i2 = scene.getWallBitset(l1, j, k);
            if (l == 1)
                i2 = scene.getWallDecorationBitset(l1, k, j);
            if (l == 2)
                i2 = scene.getLocationBitset(l1, j, k);
            if (l == 3)
                i2 = scene.getGroundDecorationBitset(l1, j, k);
            if (i2 != 0) {
                int i3 = scene.getInfo(l1, j, k, i2);
                int j2 = i2 >> 14 & 0x7fff;
                int k2 = i3 & 0x1f;
                int l2 = i3 >> 6;
                if (l == 0) {
                    scene.removeWall(j, l1, k);
                    LocType locType = LocType.get(j2);
                    if (locType.hasCollision)
                        collisionMaps[l1].removeWall(locType.isSolid, l2, j, k, k2);
                }
                if (l == 1)
                    scene.removeWallDecoration(l1, k, j);
                if (l == 2) {
                    scene.removeLocations(j, k, l1);
                    LocType locType = LocType.get(j2);
                    if (j + locType.sizeX > 103 || k + locType.sizeX > 103 || j + locType.sizeZ > 103
                            || k + locType.sizeZ > 103)
                        return;
                    if (locType.hasCollision)
                        collisionMaps[l1].removeLoc(k, j, l2, locType.sizeX, locType.isSolid,
                                locType.sizeZ);
                }
                if (l == 3) {
                    scene.removeGroundDecoration(l1, j, k);
                    LocType locType_2 = LocType.get(j2);
                    if (locType_2.hasCollision && locType_2.interactable)
                        collisionMaps[l1].removeBlock(k, j);
                }
            }
            if (i1 >= 0) {
                int j3 = l1;
                if (j3 < 3 && (aByteArrayArrayArray840[1][j][k] & 2) == 2)
                    j3++;
                SceneBuilder.addLoc(j, linkedList2, collisionMaps[l1], k, i, anIntArrayArrayArray794, l1, i1, j1,
                        scene, j3);
            }
        }
    }

    public void method100(long l, int i) {
        if (l == 0L)
            return;
        if (friendCount >= 100) {
            addMessage(0, "Your friends list is full. Max of 100 hit", (byte) 4, "");
            return;
        }
        String s = StringUtils.formatName(StringUtils.fromBase37(l));
        for (int j = 0; j < friendCount; j++)
            if (friendName37[j] == l) {
                addMessage(0, s + " is already on your friend list", (byte) 4, "");
                return;
            }

        for (int k = 0; k < ignoreCount; k++)
            if (ignoreName37[k] == l) {
                addMessage(0, "Please remove " + s + " from your ignore list first", (byte) 4, "");
                return;
            }

        if (s.equals(self.name))
            return;
        friendName[friendCount] = s;
        friendName37[friendCount] = l;
        friendWorld[friendCount] = 0;
        friendCount++;
        if (i >= 0)
            anInt1039 = isaacState.nextInt();
        aBoolean964 = true;
        outBuffer.writeOpcode(118);
        outBuffer.writeQWord(l);
    }

    public void unload(byte byte0) {
        Signlink.reporterror = false;

        try {
            if (bufferedStream != null) {
                bufferedStream.close();
            }
        } catch (Exception _ex) {
        }

        bufferedStream = null;
        midistop();
        aBoolean812 = false;
        outBuffer = null;
        loginBuffer = null;
        inBuffer = null;
        anIntArray925 = null;
        aByteArrayArray770 = null;
        aByteArrayArray1000 = null;
        anIntArrayArrayArray794 = null;
        aByteArrayArrayArray840 = null;
        scene = null;
        collisionMaps = null;
        pathWaypoint = null;
        pathDistance = null;
        waypointX = null;
        waypointY = null;
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
        indexedSprite18 = null;
        indexedSprite19 = null;
        indexedSprite20 = null;
        indexedSprite15 = null;
        indexedSprite16 = null;
        indexedSprite17 = null;
        indexedSpritesArray2 = null;
        indexedSprite10 = null;
        indexedSprite11 = null;
        indexedSprite12 = null;
        indexedSprite13 = null;
        indexedSprite14 = null;
        indexedSprite1 = null;
        indexedSprite2 = null;
        indexedSprite3 = null;
        indexedSprite4 = null;
        indexedSprite5 = null;
        aClass38_Sub2_Sub2_Sub2_1145 = null;
        aClass38_Sub2_Sub2_Sub2Array776 = null;
        aClass38_Sub2_Sub2_Sub2Array956 = null;
        aClass38_Sub2_Sub2_Sub2Array1120 = null;
        aClass38_Sub2_Sub2_Sub2_1057 = null;
        aClass38_Sub2_Sub2_Sub2_1058 = null;
        aClass38_Sub2_Sub2_Sub2_1059 = null;
        aClass38_Sub2_Sub2_Sub2_1060 = null;
        indexedSpritesArray1 = null;
        aClass38_Sub2_Sub2_Sub2Array1138 = null;
        anIntArrayArray920 = null;
        playerEntities = null;
        playerIndices = null;
        entityUpdateIndices = null;
        playerBuffers = null;
        deadEntityIndices = null;
        npcEntities = null;
        npcIndices = null;
        objects = null;
        spawnedLocations = null;
        temporaryLocs = null;
        projectiles = null;
        spotanims = null;
        linkedList2 = null;
        paramA = null;
        paramB = null;
        actions = null;
        paramC = null;
        options = null;
        anIntArray938 = null;
        anIntArray918 = null;
        anIntArray919 = null;
        aClass38_Sub2_Sub2_Sub2Array791 = null;
        aClass38_Sub2_Sub2_Sub2_1053 = null;
        friendName = null;
        friendName37 = null;
        friendWorld = null;
        imageTitle0 = null;
        imageTitle1 = null;
        imageTitle2 = null;
        imageTitle3 = null;
        imageTitle4 = null;
        imageTitle5 = null;
        imageTitle6 = null;
        imageTitle7 = null;
        imageTitle8 = null;
        disposeTitleComponents(true);
        LocType.unload();
        NpcType.unload();
        ObjType.unload();
        FloType.instances = null;
        IdkType.instances = null;
        InterfaceComponent.instances = null;
        SeqType.animations = null;
        SpotAnimType.instances = null;
        SpotAnimType.models = null;
        VarpType.instances = null;
        super.drawArea = null;
        PlayerEntity.models = null;
        Draw3D.unload();
        Scene.unload();
        Model.unload();
        SeqBase.instance = null;
        SeqFrame.instance = null;
        System.gc();
    }

    public Socket opensocket(int port) throws IOException {
        if (Signlink.mainapp != null) {
            return Signlink.opensocket(port);
        } else {
            return new Socket(InetAddress.getByName(getCodeBase().getHost()), port);
        }
    }

    public void method102(int i, int j, PlayerEntity player, int k) {
        if (player == self) {
            return;
        }

        if (optionCount >= 400) {
            return;
        }

        String s = player.name
                + method72(self.combatLevel, false, player.combatLevel) + " (level-"
                + player.combatLevel + ")";
        if (selectedObject == 1) {
            options[optionCount] = "Use " + selectedObjName + " with @whi@" + s;
            actions[optionCount] = 367;
            paramC[optionCount] = j;
            paramA[optionCount] = k;
            paramB[optionCount] = i;
            optionCount++;
        } else if (selectedSpell == 1) {
            if ((selectedFlags & 8) == 8) {
                options[optionCount] = selectedSpellPrefix + " @whi@" + s;
                actions[optionCount] = 651;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
        } else {
            options[optionCount] = "Follow @whi@" + s;
            actions[optionCount] = 1544;
            paramC[optionCount] = j;
            paramA[optionCount] = k;
            paramB[optionCount] = i;
            optionCount++;
            if (anInt802 == 0) {
                options[optionCount] = "Trade with @whi@" + s;
                actions[optionCount] = 1373;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
            if (wildernessLevel > 0) {
                options[optionCount] = "Attack @whi@" + s;
                if (self.combatLevel >= player.combatLevel)
                    actions[optionCount] = 151;
                else
                    actions[optionCount] = 2151;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
            if (anInt933 == 1) {
                options[optionCount] = "Fight @whi@" + s;
                actions[optionCount] = 151;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
            if (anInt933 == 2) {
                options[optionCount] = "Duel-with @whi@" + s;
                actions[optionCount] = 1101;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
        }

        for (int l = 0; l < optionCount; l++) {
            if (actions[l] == 660) {
                options[l] = "Walk here @whi@" + s;
                return;
            }
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
            anInt780 = inBuffer.readByte();
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
                        Buffer class38_sub2_sub3_1 = SoundTrack.generate(anIntArray809[j],
                                anIntArray1124[j]);
                        if (System.currentTimeMillis() + (long) (class38_sub2_sub3_1.offset / 22) > aLong777
                                + (long) (anInt815 / 22)) {
                            anInt815 = class38_sub2_sub3_1.offset;
                            aLong777 = System.currentTimeMillis();
                            if (method77(class38_sub2_sub3_1.data, class38_sub2_sub3_1.offset, 0)) {
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
            if (anInt744 == 0 && aBoolean835 && !lowMemory)
                method14(anInt1110, aString1119, anInt1155);
        }
        Buffer class38_sub2_sub3 = InputTracking.method184(-809);
        if (class38_sub2_sub3 != null) {
            outBuffer.writeOpcode(81);
            outBuffer.writeWord(class38_sub2_sub3.offset);
            outBuffer.writeBytes(class38_sub2_sub3.data, class38_sub2_sub3.offset, 0
            );
            class38_sub2_sub3.release();
        }
        anInt781++;
        if (anInt781 > 750)
            method121();
        method132(true);
        method62(true);
        method125(aByte1122);
        method44(anInt771);
        if ((super.anIntArray26[1] == 1 || super.anIntArray26[2] == 1 || super.anIntArray26[3] == 1
                || super.anIntArray26[4] == 1) && anInt916++ > 5) {
            anInt916 = 0;
            outBuffer.writeOpcode(189);
            outBuffer.writeWord(anInt816);
            outBuffer.writeWord(cameraYaw);
            outBuffer.writeByte(minimapAnticheatAngle);
            outBuffer.writeByte(minimapZoom);
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
                        InterfaceComponent interfaceComponent = InterfaceComponent.instances[anInt844];
                        int j1 = interfaceComponent.anIntArray265[anInt1087];
                        interfaceComponent.anIntArray265[anInt1087] = interfaceComponent.anIntArray265[anInt845];
                        interfaceComponent.anIntArray265[anInt845] = j1;
                        j1 = interfaceComponent.anIntArray266[anInt1087];
                        interfaceComponent.anIntArray266[anInt1087] = interfaceComponent.anIntArray266[anInt845];
                        interfaceComponent.anIntArray266[anInt845] = j1;
                        outBuffer.writeOpcode(159);
                        outBuffer.writeWord(anInt844);
                        outBuffer.writeWord(anInt845);
                        outBuffer.writeWord(anInt1087);
                    }
                } else if ((anInt810 == 1 || method70(145, optionCount - 1)) && optionCount > 2)
                    method93(-386);
                else if (optionCount > 0)
                    method71(6412, optionCount - 1);
                anInt944 = 10;
                super.anInt23 = 0;
            }
        }
        anInt998++;
        if (anInt998 > 127) {
            anInt998 = 0;
            outBuffer.writeOpcode(215);
            outBuffer.writeSWord(0x4c2b2c);
        }
        if (Scene.clickedTileX != -1) {
            int l = Scene.clickedTileX;
            int k1 = Scene.clickedTileZ;
            boolean flag2 = moveTo(self.pathTileX[0], 0, true, l,
                    self.pathTileZ[0], 0, 0, k1, 0, 0, 0);
            Scene.clickedTileX = -1;
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
            outBuffer.writeOpcode(70);
        }
        anInt785++;
        if (anInt785 > 500) {
            anInt785 = 0;
            int l1 = (int) (Math.random() * 8D);
            if ((l1 & 1) == 1)
                cameraAnticheatOffsetX += anInt1126;
            if ((l1 & 2) == 2)
                cameraAnticheatOffsetZ += anInt1131;
            if ((l1 & 4) == 4)
                cameraAnticheatAngle += anInt1135;
        }
        if (cameraAnticheatOffsetX < -50)
            anInt1126 = 2;
        if (cameraAnticheatOffsetX > 50)
            anInt1126 = -2;
        if (cameraAnticheatOffsetZ < -55)
            anInt1131 = 2;
        if (cameraAnticheatOffsetZ > 55)
            anInt1131 = -2;
        if (cameraAnticheatAngle < -40)
            anInt1135 = 1;
        if (cameraAnticheatAngle > 40)
            anInt1135 = -1;
        anInt750++;
        if (anInt750 > 500) {
            anInt750 = 0;
            int i2 = (int) (Math.random() * 8D);
            if ((i2 & 1) == 1)
                minimapAnticheatAngle += anInt1086;
            if ((i2 & 2) == 2)
                minimapZoom += anInt931;
        }
        if (minimapAnticheatAngle < -60)
            anInt1086 = 2;
        if (minimapAnticheatAngle > 60)
            anInt1086 = -2;
        if (minimapZoom < -20)
            anInt931 = 1;
        if (minimapZoom > 10)
            anInt931 = -1;
        anInt1090++;
        if (anInt1090 > 110) {
            anInt1090 = 0;
            outBuffer.writeOpcode(236);
            outBuffer.writeDWord(0);
        }
        anInt782++;
        if (anInt782 > 50)
            outBuffer.writeOpcode(108);
        try {
            if (bufferedStream != null && outBuffer.offset > 0) {
                bufferedStream.write(outBuffer.data, outBuffer.offset, 0);
                outBuffer.offset = 0;
                anInt782 = 0;
                return;
            }
        } catch (IOException _ex) {
            method121();
            return;
        } catch (Exception exception1) {
            method58(-780);
        }
    }

    public void drawTooltip() {
        if (optionCount < 2 && selectedObject == 0 && selectedSpell == 0) {
            return;
        }

        String s;

        if (selectedObject == 1 && optionCount < 2) {
            s = "Use " + selectedObjName + " with...";
        } else if (selectedSpell == 1 && optionCount < 2) {
            s = selectedSpellPrefix + "...";
        } else {
            s = options[optionCount - 1];
        }

        if (optionCount > 2) {
            s = s + "@whi@ / " + (optionCount - 2) + " more options";
        }

        fontBold12.drawTooltip(clientClock / 1000, true, 15, 0xffffff, s, 4);
    }

    public void method105() {
        for (SpotAnimEntity spotAnimEntity = (SpotAnimEntity) spotanims
                .peekLast(); spotAnimEntity != null; spotAnimEntity = (SpotAnimEntity) spotanims
                .getPrevious())
            if (spotAnimEntity.level != currentLevel || spotAnimEntity.finished)
                spotAnimEntity.unlink();
            else if (clientClock >= spotAnimEntity.firstCycle) {
                spotAnimEntity.update(anInt969);
                if (spotAnimEntity.finished)
                    spotAnimEntity.unlink();
                else
                    scene.add(spotAnimEntity.z, 60, 0, spotAnimEntity.x, -1,
                            false, null, spotAnimEntity, spotAnimEntity.y, spotAnimEntity.level);
            }

    }

    public URL getCodeBase() {
        if (Signlink.mainapp != null)
            return Signlink.mainapp.getCodeBase();
        try {
            if (super.frame != null)
                return new URL("http://127.0.0.1:" + (80 + portoff));
        } catch (Exception _ex) {
        }
        return super.getCodeBase();
    }

    public static void setHighMemory() {
        Scene.lowMemory = false;
        Draw3D.lowMemory = false;
        lowMemory = false;
        SceneBuilder.lowMemory = false;
    }

    public boolean moveTo(int i, int j, boolean flag, int k, int l, int type,
                          int k1, int l1, int i2, int j2, int k2) {
        byte byte0 = 104;
        byte byte1 = 104;
        for (int l2 = 0; l2 < byte0; l2++) {
            for (int i3 = 0; i3 < byte1; i3++) {
                pathWaypoint[l2][i3] = 0;
                pathDistance[l2][i3] = 0x5f5e0ff;
            }
        }

        int j3 = i;
        int k3 = l;
        pathWaypoint[i][l] = 99;
        pathDistance[i][l] = 0;

        int l3 = 0;
        int current = 0;
        waypointX[l3] = i;
        waypointY[l3++] = l;

        boolean reached = false;
        int j4 = waypointX.length;
        int[][] flags = collisionMaps[currentLevel].flags;

        while (current != l3) {
            j3 = waypointX[current];
            k3 = waypointY[current];
            current = (current + 1) % j4;
            if (j3 == k && k3 == l1) {
                reached = true;
                break;
            }
            if (j2 != 0) {
                if ((j2 < 5 || j2 == 10) && collisionMaps[currentLevel].reachedWall(i2, l1, j2 - 1, k3, k, j3)) {
                    reached = true;
                    break;
                }
                if (j2 < 10 && collisionMaps[currentLevel].reachedDecoration(i2, j2 - 1, j3, k, k3, l1)) {
                    reached = true;
                    break;
                }
            }
            if (j != 0 && k1 != 0 && collisionMaps[currentLevel].reachedObject(k3, k1, j3, k, k2, l1, j)) {
                reached = true;
                break;
            }
            int l4 = pathDistance[j3][k3] + 1;
            if (j3 > 0 && pathWaypoint[j3 - 1][k3] == 0 && (flags[j3 - 1][k3] & 0x280108) == 0) {
                waypointX[l3] = j3 - 1;
                waypointY[l3] = k3;
                l3 = (l3 + 1) % j4;
                pathWaypoint[j3 - 1][k3] = 2;
                pathDistance[j3 - 1][k3] = l4;
            }
            if (j3 < byte0 - 1 && pathWaypoint[j3 + 1][k3] == 0 && (flags[j3 + 1][k3] & 0x280180) == 0) {
                waypointX[l3] = j3 + 1;
                waypointY[l3] = k3;
                l3 = (l3 + 1) % j4;
                pathWaypoint[j3 + 1][k3] = 8;
                pathDistance[j3 + 1][k3] = l4;
            }
            if (k3 > 0 && pathWaypoint[j3][k3 - 1] == 0 && (flags[j3][k3 - 1] & 0x280102) == 0) {
                waypointX[l3] = j3;
                waypointY[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                pathWaypoint[j3][k3 - 1] = 1;
                pathDistance[j3][k3 - 1] = l4;
            }
            if (k3 < byte1 - 1 && pathWaypoint[j3][k3 + 1] == 0 && (flags[j3][k3 + 1] & 0x280120) == 0) {
                waypointX[l3] = j3;
                waypointY[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                pathWaypoint[j3][k3 + 1] = 4;
                pathDistance[j3][k3 + 1] = l4;
            }
            if (j3 > 0 && k3 > 0 && pathWaypoint[j3 - 1][k3 - 1] == 0 && (flags[j3 - 1][k3 - 1] & 0x28010e) == 0
                    && (flags[j3 - 1][k3] & 0x280108) == 0 && (flags[j3][k3 - 1] & 0x280102) == 0) {
                waypointX[l3] = j3 - 1;
                waypointY[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                pathWaypoint[j3 - 1][k3 - 1] = 3;
                pathDistance[j3 - 1][k3 - 1] = l4;
            }
            if (j3 < byte0 - 1 && k3 > 0 && pathWaypoint[j3 + 1][k3 - 1] == 0
                    && (flags[j3 + 1][k3 - 1] & 0x280183) == 0 && (flags[j3 + 1][k3] & 0x280180) == 0
                    && (flags[j3][k3 - 1] & 0x280102) == 0) {
                waypointX[l3] = j3 + 1;
                waypointY[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                pathWaypoint[j3 + 1][k3 - 1] = 9;
                pathDistance[j3 + 1][k3 - 1] = l4;
            }
            if (j3 > 0 && k3 < byte1 - 1 && pathWaypoint[j3 - 1][k3 + 1] == 0
                    && (flags[j3 - 1][k3 + 1] & 0x280138) == 0 && (flags[j3 - 1][k3] & 0x280108) == 0
                    && (flags[j3][k3 + 1] & 0x280120) == 0) {
                waypointX[l3] = j3 - 1;
                waypointY[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                pathWaypoint[j3 - 1][k3 + 1] = 6;
                pathDistance[j3 - 1][k3 + 1] = l4;
            }
            if (j3 < byte0 - 1 && k3 < byte1 - 1 && pathWaypoint[j3 + 1][k3 + 1] == 0
                    && (flags[j3 + 1][k3 + 1] & 0x2801e0) == 0 && (flags[j3 + 1][k3] & 0x280180) == 0
                    && (flags[j3][k3 + 1] & 0x280120) == 0) {
                waypointX[l3] = j3 + 1;
                waypointY[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                pathWaypoint[j3 + 1][k3 + 1] = 12;
                pathDistance[j3 + 1][k3 + 1] = l4;
            }
        }

        anInt989 = 0;
        if (!reached) {
            if (flag) {
                int i5 = 100;
                for (int k5 = 1; k5 < 2; k5++) {
                    for (int i6 = k - k5; i6 <= k + k5; i6++) {
                        for (int k6 = l1 - k5; k6 <= l1 + k5; k6++) {
                            if (i6 >= 0 && k6 >= 0 && i6 < 104 && k6 < 104 && pathDistance[i6][k6] < i5) {
                                i5 = pathDistance[i6][k6];
                                j3 = i6;
                                k3 = k6;
                                anInt989 = 1;
                                reached = true;
                            }
                        }
                    }

                    if (reached) {
                        break;
                    }
                }
            }
            if (!reached) {
                return false;
            }
        }

        current = 0;
        waypointX[current] = j3;
        waypointY[current++] = k3;

        int l5;
        for (int j5 = l5 = pathWaypoint[j3][k3]; j3 != i || k3 != l; j5 = pathWaypoint[j3][k3]) {
            if (j5 != l5) {
                l5 = j5;
                waypointX[current] = j3;
                waypointY[current++] = k3;
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

        if (current > 0) {
            int count = current;
            if (count > 25)
                count = 25;
            current--;
            int startX = waypointX[current];
            int startZ = waypointY[current];
            if (type == 0) {
                outBuffer.writeOpcode(181);
                outBuffer.writeByte(count + count + 3);
            } else if (type == 1) {
                outBuffer.writeOpcode(165);
                outBuffer.writeByte(count + count + 3 + 14);
            } else if (type == 2) {
                outBuffer.writeOpcode(93);
                outBuffer.writeByte(count + count + 3);
            }

            if (super.anIntArray26[5] == 1) {
                outBuffer.writeByte(1);
            } else {
                outBuffer.writeByte(0);
            }

            outBuffer.writeWord(startX + baseTileX);
            outBuffer.writeWord(startZ + baseTileZ);

            flagTileX = waypointX[0];
            flagTileY = waypointY[0];

            for (int n = 1; n < count; n++) {
                current--;
                outBuffer.writeByte(waypointX[current] - startX);
                outBuffer.writeByte(waypointY[current] - startZ);
            }

            return true;
        }

        return type != 1;
    }

    public static String formatObjAmount(int amount) {
        if (amount < 100000) {
            return String.valueOf(amount);
        }

        if (amount < 10000000) {
            return amount / 1000 + "K";
        } else {
            return amount / 1000000 + "M";
        }
    }

    public void updatePlayers(Buffer buffer, int size) {
        deadEntityCount = 0;
        updateCount = 0;

        updateLocalPlayers(buffer);
        updateOtherPlayers(buffer);
        updateNewPlayers(size, buffer);
        updatePlayerMasks(buffer);

        for (int n = 0; n < deadEntityCount; n++) {
            int l = deadEntityIndices[n];
            if (playerEntities[l].remove != clientClock) {
                playerEntities[l] = null;
            }
        }

        if (buffer.offset != size) {
            Signlink.reporterror("Error packet size mismatch in getplayer pos:" + buffer.offset + " psize:" + size);
            throw new RuntimeException("eek");
        }

        for (int n = 0; n < entityCount; n++) {
            if (playerEntities[playerIndices[n]] == null) {
                Signlink.reporterror(aString1066 + " null entry in pl list - pos:" + n + " size:" + entityCount);
                throw new RuntimeException("eek");
            }
        }
    }

    public boolean method110(int i, int j, int k) {
        boolean flag = false;
        InterfaceComponent interfaceComponent = InterfaceComponent.instances[i];
        for (int l = 0; l < interfaceComponent.anIntArray285.length; l++) {
            if (interfaceComponent.anIntArray285[l] == -1)
                break;
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[interfaceComponent.anIntArray285[l]];
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
                    SeqType seqType = SeqType.animations[i1];
                    for (interfaceComponent_1.anInt268 += j; interfaceComponent_1.anInt268 > seqType.frameDelay[interfaceComponent_1.anInt267]; ) {
                        interfaceComponent_1.anInt268 -= seqType.frameDelay[interfaceComponent_1.anInt267] + 1;
                        interfaceComponent_1.anInt267++;
                        if (interfaceComponent_1.anInt267 >= seqType.frameCount) {
                            interfaceComponent_1.anInt267 -= seqType.delta;
                            if (interfaceComponent_1.anInt267 < 0 || interfaceComponent_1.anInt267 >= seqType.frameCount)
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

    public void addMessage(int i, String s, byte byte0, String s1) {
        if (i == 0 && anInt1021 != -1) {
            aString936 = s;
            super.anInt23 = 0;
        }
        if (anInt1001 == -1)
            aBoolean965 = true;
        for (int j = 99; j > 0; j--) {
            anIntArray896[j] = anIntArray896[j - 1];
            aStringArray897[j] = aStringArray897[j - 1];
            messageText[j] = messageText[j - 1];
        }

        anIntArray896[0] = i;
        aStringArray897[0] = s1;
        if (byte0 != 4)
            aBoolean912 = !aBoolean912;
        messageText[0] = s;
    }

    public void method112(int i, int j) {
        InterfaceComponent interfaceComponent = InterfaceComponent.instances[j];
        for (int k = 0; k < interfaceComponent.anIntArray285.length; k++) {
            if (interfaceComponent.anIntArray285[k] == -1)
                break;
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[interfaceComponent.anIntArray285[k]];
            if (interfaceComponent_1.anInt271 == 1)
                method112(-321, interfaceComponent_1.anInt269);
            interfaceComponent_1.anInt267 = 0;
            interfaceComponent_1.anInt268 = 0;
        }

        if (i >= 0)
            objects = null;
    }

    public void method113(int i, long l) {
        if (i != 43808) {
            for (int j = 1; j > 0; j++)
                ;
        }
        if (l == 0L)
            return;
        for (int k = 0; k < friendCount; k++)
            if (friendName37[k] == l) {
                friendCount--;
                aBoolean964 = true;
                for (int i1 = k; i1 < friendCount; i1++) {
                    friendName[i1] = friendName[i1 + 1];
                    friendWorld[i1] = friendWorld[i1 + 1];
                    friendName37[i1] = friendName37[i1 + 1];
                }

                outBuffer.writeOpcode(11);
                outBuffer.writeQWord(l);
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
            outBuffer.writeByte(82);
        return true;
    }

    public void method115(byte byte0) {
        if (byte0 != aByte1068)
            objects = null;
        if (super.anInt23 == 1) {
            int i = super.anInt24 - 21 - 561;
            int j = super.anInt25 - 9 - 5;
            if (i >= 0 && j >= 0 && i < 146 && j < 151) {
                i -= 73;
                j -= 75;
                int k = cameraYaw + minimapAnticheatAngle & 0x7ff;
                int l = Draw3D.sin[k];
                int i1 = Draw3D.cos[k];
                l = l * (minimapZoom + 256) >> 8;
                i1 = i1 * (minimapZoom + 256) >> 8;
                int j1 = j * l + i * i1 >> 11;
                int k1 = j * i1 - i * l >> 11;
                int l1 = self.x + j1 >> 7;
                int i2 = self.z - k1 >> 7;
                boolean flag = moveTo(self.pathTileX[0], 0, true,
                        l1, self.pathTileZ[0], 1, 0, i2, 0, 0,
                        0);
                if (flag) {
                    outBuffer.writeByte(i);
                    outBuffer.writeByte(j);
                    outBuffer.writeWord(cameraYaw);
                    outBuffer.writeByte(57);
                    outBuffer.writeByte(minimapAnticheatAngle);
                    outBuffer.writeByte(minimapZoom);
                    outBuffer.writeByte(89);
                    outBuffer.writeWord(self.x);
                    outBuffer.writeWord(self.z);
                    outBuffer.writeByte(anInt989);
                    outBuffer.writeByte(63);
                }
            }
        }
    }

    public void method116(byte byte0) {
        if (byte0 != 3)
            anInt780 = inBuffer.readByte();
        if (anInt846 != 0)
            return;
        int i = super.anInt23;
        if (selectedSpell == 1 && super.anInt24 >= 520 && super.anInt25 >= 165 && super.anInt24 <= 788
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
                for (int i3 = 0; i3 < optionCount; i3++) {
                    int j3 = j1 + 31 + (optionCount - 1 - i3) * 15;
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
            if (i == 1 && optionCount > 0) {
                int l = actions[optionCount - 1];
                if (l == 602 || l == 596 || l == 22 || l == 892 || l == 415 || l == 405 || l == 38 || l == 422
                        || l == 478 || l == 347 || l == 188) {
                    int k1 = paramA[optionCount - 1];
                    int i2 = paramB[optionCount - 1];
                    InterfaceComponent interfaceComponent = InterfaceComponent.instances[i2];
                    if (interfaceComponent.aBoolean290) {
                        aBoolean960 = false;
                        anInt924 = 0;
                        anInt844 = i2;
                        anInt845 = k1;
                        anInt846 = 2;
                        anInt847 = super.anInt24;
                        anInt848 = super.anInt25;
                        if (InterfaceComponent.instances[i2].anInt270 == anInt971)
                            anInt846 = 1;
                        if (InterfaceComponent.instances[i2].anInt270 == anInt1001)
                            anInt846 = 3;
                        return;
                    }
                }
            }
            if (i == 1 && (anInt810 == 1 || method70(145, optionCount - 1)) && optionCount > 2)
                i = 2;
            if (i == 1 && optionCount > 0)
                method71(6412, optionCount - 1);
            if (i == 2 && optionCount > 0)
                method93(-386);
        }
    }

    public void method117(byte byte0) {
        int i = anInt728 * 128 + 64;
        int j = anInt729 * 128 + 64;
        int k = method33(currentLevel, anInt728, (byte) 5, anInt729) - anInt730;
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
        k = method33(currentLevel, anInt948, (byte) 5, anInt949) - anInt950;
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
                selectedTab = 0;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 579 && super.anInt24 <= 609 && super.anInt25 >= 194 && super.anInt25 < 231
                    && anIntArray861[1] != -1) {
                aBoolean964 = true;
                selectedTab = 1;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 607 && super.anInt24 <= 637 && super.anInt25 >= 194 && super.anInt25 < 231
                    && anIntArray861[2] != -1) {
                aBoolean964 = true;
                selectedTab = 2;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 635 && super.anInt24 <= 679 && super.anInt25 >= 194 && super.anInt25 < 229
                    && anIntArray861[3] != -1) {
                aBoolean964 = true;
                selectedTab = 3;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 676 && super.anInt24 <= 706 && super.anInt25 >= 194 && super.anInt25 < 231
                    && anIntArray861[4] != -1) {
                aBoolean964 = true;
                selectedTab = 4;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 704 && super.anInt24 <= 734 && super.anInt25 >= 194 && super.anInt25 < 231
                    && anIntArray861[5] != -1) {
                aBoolean964 = true;
                selectedTab = 5;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 732 && super.anInt24 <= 766 && super.anInt25 >= 195 && super.anInt25 < 231
                    && anIntArray861[6] != -1) {
                aBoolean964 = true;
                selectedTab = 6;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 550 && super.anInt24 <= 584 && super.anInt25 >= 492 && super.anInt25 < 528
                    && anIntArray861[7] != -1) {
                aBoolean964 = true;
                selectedTab = 7;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 582 && super.anInt24 <= 612 && super.anInt25 >= 492 && super.anInt25 < 529
                    && anIntArray861[8] != -1) {
                aBoolean964 = true;
                selectedTab = 8;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 609 && super.anInt24 <= 639 && super.anInt25 >= 492 && super.anInt25 < 529
                    && anIntArray861[9] != -1) {
                aBoolean964 = true;
                selectedTab = 9;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 637 && super.anInt24 <= 681 && super.anInt25 >= 493 && super.anInt25 < 528
                    && anIntArray861[10] != -1) {
                aBoolean964 = true;
                selectedTab = 10;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 679 && super.anInt24 <= 709 && super.anInt25 >= 492 && super.anInt25 < 529
                    && anIntArray861[11] != -1) {
                aBoolean964 = true;
                selectedTab = 11;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 706 && super.anInt24 <= 736 && super.anInt25 >= 492 && super.anInt25 < 529
                    && anIntArray861[12] != -1) {
                aBoolean964 = true;
                selectedTab = 12;
                aBoolean1080 = true;
            }
            if (super.anInt24 >= 734 && super.anInt24 <= 768 && super.anInt25 >= 492 && super.anInt25 < 528
                    && anIntArray861[13] != -1) {
                aBoolean964 = true;
                selectedTab = 13;
                aBoolean1080 = true;
            }
            anInt882++;
            if (anInt882 > 150) {
                anInt882 = 0;
                outBuffer.writeOpcode(233);
                outBuffer.writeByte(43);
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
            options[optionCount] = "Remove @whi@" + friendName[i];
            actions[optionCount] = 557;
            optionCount++;
            options[optionCount] = "Message @whi@" + friendName[i];
            actions[optionCount] = 679;
            optionCount++;
            return true;
        }
        if (i >= 401 && i <= 500) {
            options[optionCount] = "Remove @whi@" + interfaceComponent.aString303;
            actions[optionCount] = 556;
            optionCount++;
            return true;
        } else {
            return false;
        }
    }

    public void updateNpcList(Buffer buffer) {
        buffer.accessBits();

        int count = buffer.getBits(8);
        if (count < npcCount) {
            for (int l = count; l < npcCount; l++) {
                deadEntityIndices[deadEntityCount++] = npcIndices[l];
            }
        }

        if (count > npcCount) {
            Signlink.reporterror(aString1066 + " Too many npcs");
            throw new RuntimeException("eek");
        }

        npcCount = 0;
        for (int n = 0; n < count; n++) {
            int nid = npcIndices[n];
            NpcEntity npc = npcEntities[nid];

            int shouldRemove = buffer.getBits(1);
            if (shouldRemove == 0) {
                npcIndices[npcCount++] = nid;
                npc.remove = clientClock;
            } else {
                int type = buffer.getBits(2);
                if (type == 0) {
                    npcIndices[npcCount++] = nid;
                    npc.remove = clientClock;
                    entityUpdateIndices[updateCount++] = nid;
                } else if (type == 1) {
                    npcIndices[npcCount++] = nid;
                    npc.remove = clientClock;
                    int i2 = buffer.getBits(3);
                    npc.walk(false, i2);
                    int k2 = buffer.getBits(1);
                    if (k2 == 1) {
                        entityUpdateIndices[updateCount++] = nid;
                    }
                } else if (type == 2) {
                    npcIndices[npcCount++] = nid;
                    npc.remove = clientClock;
                    int j2 = buffer.getBits(3);
                    npc.walk(true, j2);
                    int l2 = buffer.getBits(3);
                    npc.walk(true, l2);
                    int i3 = buffer.getBits(1);
                    if (i3 == 1) {
                        entityUpdateIndices[updateCount++] = nid;
                    }
                } else if (type == 3) {
                    deadEntityIndices[deadEntityCount++] = nid;
                }
            }
        }
    }

    public String getParameter(String s) {
        if (Signlink.mainapp != null) {
            return Signlink.mainapp.getParameter(s);
        } else {
            return super.getParameter(s);
        }
    }

    public void method121() {
        if (anInt783 > 0) {
            method58(-780);
            return;
        }
        drawArea22.bind();
        indexedFont2.drawRightAligned(144, 0, "Connection lost", 257);
        indexedFont2.drawRightAligned(143, 0xffffff, "Connection lost", 256);
        indexedFont2.drawRightAligned(159, 0, "Please wait - attempting to reestablish", 257);
        indexedFont2.drawRightAligned(158, 0xffffff, "Please wait - attempting to reestablish", 256);
        drawArea22.drawImage(11, super.graphics, 8);
        flagTileX = 0;
        BufferedStream bufferedStream = this.bufferedStream;
        aBoolean974 = false;
        login(aString1066, aString1067, true);
        if (!aBoolean974)
            method58(-780);
        try {
            bufferedStream.close();
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void updateFlameBuffer(int i, IndexedSprite indexedSprite) {
        int j = 256;
        for (int k = 0; k < flameBuffer0.length; k++)
            flameBuffer0[k] = 0;

        for (int l = 0; l < 5000; l++) {
            int i1 = (int) (Math.random() * 128D * (double) j);
            flameBuffer0[i1] = (int) (Math.random() * 256D);
        }

        for (int j1 = 0; j1 < 20; j1++) {
            for (int k1 = 1; k1 < j - 1; k1++) {
                for (int i2 = 1; i2 < 127; i2++) {
                    int k2 = i2 + (k1 << 7);
                    flameBuffer1[k2] = (flameBuffer0[k2 - 1] + flameBuffer0[k2 + 1] + flameBuffer0[k2 - 128]
                            + flameBuffer0[k2 + 128]) / 4;
                }

            }

            int[] ai = flameBuffer0;
            flameBuffer0 = flameBuffer1;
            flameBuffer1 = ai;
        }

        if (i <= 0)
            aBoolean912 = !aBoolean912;
        if (indexedSprite != null) {
            int l1 = 0;
            for (int j2 = 0; j2 < indexedSprite.height; j2++) {
                for (int l2 = 0; l2 < indexedSprite.width; l2++)
                    if (indexedSprite.pixels[l1++] != 0) {
                        int i3 = l2 + 16 + indexedSprite.clipX;
                        int j3 = j2 + 16 + indexedSprite.clipY;
                        int k3 = i3 + (j3 << 7);
                        flameBuffer0[k3] = 0;
                    }

            }

        }
    }

    public void method123(int i, int j) {
        LinkedList linkedList = objects[currentLevel][i][j];
        if (linkedList == null) {
            scene.removeObject(currentLevel, i, j);
            return;
        }
        int k = 0xfa0a1f01;
        Object obj = null;
        for (ObjStackEntity objStackEntity = (ObjStackEntity) linkedList
                .peekLast(); objStackEntity != null; objStackEntity = (ObjStackEntity) linkedList.getPrevious()) {
            ObjType objType = ObjType.get(objStackEntity.model);
            int i1 = objType.value;
            if (objType.stackable)
                i1 *= objStackEntity.amount + 1;
            if (i1 > k) {
                k = i1;
                obj = objStackEntity;
            }
        }

        linkedList.pushLast(((Node) (obj)));
        int l = -1;
        int j1 = -1;
        int k1 = 0;
        int l1 = 0;
        for (ObjStackEntity objStackEntity_1 = (ObjStackEntity) linkedList
                .peekLast(); objStackEntity_1 != null; objStackEntity_1 = (ObjStackEntity) linkedList.getPrevious()) {
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

        Model class38_sub2_sub1 = null;
        if (l != -1)
            class38_sub2_sub1 = ObjType.get(l).getModel(k1);
        Model class38_sub2_sub1_1 = null;
        if (j1 != -1)
            class38_sub2_sub1_1 = ObjType.get(j1).getModel(l1);
        int i2 = i + (j << 7) + 0x60000000;
        ObjType objType_1 = ObjType.get(((ObjStackEntity) (obj)).model);
        scene.addObject(objType_1.getModel(((ObjStackEntity) (obj)).amount), class38_sub2_sub1,
                method33(currentLevel, i * 128 + 64, (byte) 5, j * 128 + 64), currentLevel, i2, j, i, class38_sub2_sub1_1);
    }

    public void method124(int i) {
        try {
            lastSceneLevel = -1;
            temporaryLocs.clear();
            linkedList2.clear();
            spotanims.clear();
            projectiles.clear();
            Draw3D.clearPools();
            method83(aByte843);
            scene.reset();
            for (int j = 0; j < 4; j++)
                collisionMaps[j].reset();

            System.gc();
            SceneBuilder sceneBuilder = new SceneBuilder(104, aByteArrayArrayArray840, 104, anIntArrayArrayArray794);
            byte[] abyte0 = new byte[0x186a0];
            int k = aByteArrayArray770.length;
            SceneBuilder.lowMemory = Scene.lowMemory;
            for (int l = 0; l < k; l++) {
                int i1 = anIntArray925[l] >> 8;
                int k1 = anIntArray925[l] & 0xff;
                if (i1 == 33 && k1 >= 71 && k1 <= 73)
                    SceneBuilder.lowMemory = false;
            }

            if (SceneBuilder.lowMemory)
                scene.setup(currentLevel);
            else
                scene.setup(0);
            outBuffer.writeOpcode(108);
            for (int j1 = 0; j1 < k; j1++) {
                int l1 = (anIntArray925[j1] >> 8) * 64 - baseTileX;
                int j2 = (anIntArray925[j1] & 0xff) * 64 - baseTileZ;
                byte[] abyte2 = aByteArrayArray770[j1];
                if (abyte2 != null) {
                    int i3 = (new Buffer(abyte2)).readDWord();
                    BZip2InputStream.read(abyte0, i3, abyte2, abyte2.length - 4, 4);
                    sceneBuilder.readLandscape(abyte0, (anInt838 - 6) * 8, j2, l1, (anInt839 - 6) * 8);
                } else if (anInt839 < 800)
                    sceneBuilder.clearLandscape(l1, j2, 64, 64);
            }

            outBuffer.writeOpcode(108);
            for (int i2 = 0; i2 < k; i2++) {
                byte[] abyte1 = aByteArrayArray1000[i2];
                if (abyte1 != null) {
                    int k2 = (new Buffer(abyte1)).readDWord();
                    BZip2InputStream.read(abyte0, k2, abyte1, abyte1.length - 4, 4);
                    int j3 = (anIntArray925[i2] >> 8) * 64 - baseTileX;
                    int l3 = (anIntArray925[i2] & 0xff) * 64 - baseTileZ;
                    sceneBuilder.readLocs(abyte0, scene, collisionMaps, linkedList2, l3, j3);
                }
            }

            outBuffer.writeOpcode(108);
            sceneBuilder.buildLandscape(scene, collisionMaps);
            drawArea22.bind();
            outBuffer.writeOpcode(108);
            for (LocEntity locEntity = (LocEntity) linkedList2
                    .peekLast(); locEntity != null; locEntity = (LocEntity) linkedList2.getPrevious())
                if ((aByteArrayArrayArray840[1][locEntity.anInt1208][locEntity.anInt1209] & 2) == 2) {
                    locEntity.anInt1206--;
                    if (locEntity.anInt1206 < 0)
                        locEntity.unlink();
                }

            for (int l2 = 0; l2 < 104; l2++) {
                for (int k3 = 0; k3 < 104; k3++)
                    method123(l2, k3);

            }

            for (SpawnedLoc spawnedLoc = (SpawnedLoc) spawnedLocations
                    .peekLast(); spawnedLoc != null; spawnedLoc = (SpawnedLoc) spawnedLocations.getPrevious())
                method99(spawnedLoc.rotation, spawnedLoc.tileX, spawnedLoc.tileZ, spawnedLoc.classType,
                        spawnedLoc.locIndex, spawnedLoc.type, -27819, spawnedLoc.level);

        } catch (Exception exception) {
        }
        LocType.models.clear();
        if (i <= 0)
            aBoolean1147 = !aBoolean1147;
        System.gc();
        Draw3D.setupPools(20);
    }

    public void method7(int i) {
        if (aBoolean1070 || aBoolean865 || aBoolean923)
            return;
        clientClock++;
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
        for (int i = -1; i < entityCount; i++) {
            int j;
            if (i == -1)
                j = LOCAL_PLAYER_INDEX;
            else
                j = playerIndices[i];
            PlayerEntity playerEntity = playerEntities[j];
            if (playerEntity != null && playerEntity.textCycle > 0) {
                playerEntity.textCycle--;
                if (playerEntity.textCycle == 0)
                    playerEntity.spoken = null;
            }
        }

        for (int k = 0; k < npcCount; k++) {
            int l = npcIndices[k];
            NpcEntity npcEntity = npcEntities[l];
            if (npcEntity != null && npcEntity.textCycle > 0) {
                npcEntity.textCycle--;
                if (npcEntity.textCycle == 0)
                    npcEntity.spoken = null;
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
                        InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[ai[k++]];
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
                        j += self.combatLevel;
                    if (l == 9) {
                        for (int i1 = 0; i1 < 19; i1++) {
                            if (i1 == 18)
                                i1 = 20;
                            j += anIntArray926[i1];
                        }

                    }
                    if (l == 10) {
                        InterfaceComponent interfaceComponent_2 = InterfaceComponent.instances[ai[k++]];
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
            flameActive = false;
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
            flameActive = false;
            g.setFont(new Font("Helvetica", 1, 20));
            g.setColor(Color.white);
            g.drawString("Error - unable to load game!", 50, 50);
            g.drawString("To play RuneScape make sure you play from", 50, 100);
            g.drawString("http://www.runescape.com", 50, 150);
        }
        if (aBoolean1070) {
            flameActive = false;
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
        Sprite class38_sub2_sub2_sub2 = new Sprite(abyte0, this);
        imageTitle0.bind();
        class38_sub2_sub2_sub2.drawOpaque(0, 0);
        imageTitle1.bind();
        class38_sub2_sub2_sub2.drawOpaque(-661, 0);
        imageTitle2.bind();
        class38_sub2_sub2_sub2.drawOpaque(-128, 0);
        imageTitle3.bind();
        class38_sub2_sub2_sub2.drawOpaque(-214, -386);
        imageTitle4.bind();
        class38_sub2_sub2_sub2.drawOpaque(-214, -186);
        imageTitle5.bind();
        class38_sub2_sub2_sub2.drawOpaque(0, -265);
        imageTitle6.bind();
        class38_sub2_sub2_sub2.drawOpaque(-574, -265);
        imageTitle7.bind();
        if (byte0 != 5)
            aBoolean912 = !aBoolean912;
        class38_sub2_sub2_sub2.drawOpaque(-128, -186);
        imageTitle8.bind();
        class38_sub2_sub2_sub2.drawOpaque(-574, -186);
        int[] ai = new int[class38_sub2_sub2_sub2.width];
        for (int i = 0; i < class38_sub2_sub2_sub2.height; i++) {
            for (int j = 0; j < class38_sub2_sub2_sub2.width; j++)
                ai[j] = class38_sub2_sub2_sub2.pixels[(class38_sub2_sub2_sub2.width - j - 1)
                        + class38_sub2_sub2_sub2.width * i];

            for (int k = 0; k < class38_sub2_sub2_sub2.width; k++)
                class38_sub2_sub2_sub2.pixels[k + class38_sub2_sub2_sub2.width * i] = ai[k];

        }

        imageTitle0.bind();
        class38_sub2_sub2_sub2.drawOpaque(394, 0);
        imageTitle1.bind();
        class38_sub2_sub2_sub2.drawOpaque(-267, 0);
        imageTitle2.bind();
        class38_sub2_sub2_sub2.drawOpaque(266, 0);
        imageTitle3.bind();
        class38_sub2_sub2_sub2.drawOpaque(180, -386);
        imageTitle4.bind();
        class38_sub2_sub2_sub2.drawOpaque(180, -186);
        imageTitle5.bind();
        class38_sub2_sub2_sub2.drawOpaque(394, -265);
        imageTitle6.bind();
        class38_sub2_sub2_sub2.drawOpaque(-180, -265);
        imageTitle7.bind();
        class38_sub2_sub2_sub2.drawOpaque(212, -186);
        imageTitle8.bind();
        class38_sub2_sub2_sub2.drawOpaque(-180, -186);
        class38_sub2_sub2_sub2 = new Sprite(fileArchive, "logo", 0);
        imageTitle2.bind();
        class38_sub2_sub2_sub2.draw(18, super.anInt12 / 2 - class38_sub2_sub2_sub2.width / 2 - 128);
        class38_sub2_sub2_sub2 = null;
        abyte0 = null;
        ai = null;
        System.gc();
    }

    public void method129(int i) {
        i = 30 / i;
        for (LocEntity locEntity = (LocEntity) linkedList2
                .peekLast(); locEntity != null; locEntity = (LocEntity) linkedList2.getPrevious()) {
            boolean flag = false;
            locEntity.currentFrameDuration += anInt969;
            if (locEntity.currentFrameId == -1) {
                locEntity.currentFrameId = 0;
                flag = true;
            }
            while (locEntity.currentFrameDuration > locEntity.seq.frameDelay[locEntity.currentFrameId]) {
                locEntity.currentFrameDuration -= locEntity.seq.frameDelay[locEntity.currentFrameId] + 1;
                locEntity.currentFrameId++;
                flag = true;
                if (locEntity.currentFrameId < locEntity.seq.frameCount)
                    continue;
                locEntity.currentFrameId -= locEntity.seq.delta;
                if (locEntity.currentFrameId >= 0 && locEntity.currentFrameId < locEntity.seq.frameCount)
                    continue;
                locEntity.unlink();
                flag = false;
                break;
            }
            if (flag) {
                int j = locEntity.anInt1206;
                int k = locEntity.anInt1208;
                int l = locEntity.anInt1209;
                int i1 = 0;
                if (locEntity.anInt1207 == 0)
                    i1 = scene.getWallBitset(j, k, l);
                if (locEntity.anInt1207 == 1)
                    i1 = scene.getWallDecorationBitset(j, l, k);
                if (locEntity.anInt1207 == 2)
                    i1 = scene.getLocationBitset(j, k, l);
                if (locEntity.anInt1207 == 3)
                    i1 = scene.getGroundDecorationBitset(j, k, l);
                if (i1 == 0 || (i1 >> 14 & 0x7fff) != locEntity.anInt1210) {
                    locEntity.unlink();
                } else {
                    int j1 = anIntArrayArrayArray794[j][k][l];
                    int k1 = anIntArrayArrayArray794[j][k + 1][l];
                    int l1 = anIntArrayArrayArray794[j][k + 1][l + 1];
                    int i2 = anIntArrayArrayArray794[j][k][l + 1];
                    LocType locType = LocType.get(locEntity.anInt1210);
                    int j2 = -1;
                    if (locEntity.currentFrameId != -1)
                        j2 = locEntity.seq.primaryFrames[locEntity.currentFrameId];
                    if (locEntity.anInt1207 == 2) {
                        int k2 = scene.getInfo(j, k, l, i1);
                        int j3 = k2 & 0x1f;
                        int i4 = k2 >> 6;
                        if (j3 == 11)
                            j3 = 10;
                        Model class38_sub2_sub1_2 = locType.getModel(j3, i4, j1, k1, l1, i2, j2);
                        scene.setLocModel(k, class38_sub2_sub1_2, j, l);
                    } else if (locEntity.anInt1207 == 1) {
                        Model class38_sub2_sub1 = locType.getModel(4, 0, j1, k1, l1, i2, j2);
                        scene.setWallDecorationModel(l, k, class38_sub2_sub1, j);
                    } else if (locEntity.anInt1207 == 0) {
                        int l2 = scene.getInfo(j, k, l, i1);
                        int k3 = l2 & 0x1f;
                        int j4 = l2 >> 6;
                        if (k3 == 2) {
                            int k4 = j4 + 1 & 3;
                            Model class38_sub2_sub1_4 = locType.getModel(2, 4 + j4, j1, k1, l1, i2, j2);
                            Model class38_sub2_sub1_5 = locType.getModel(2, k4, j1, k1, l1, i2, j2);
                            scene.setWallModels(class38_sub2_sub1_4, class38_sub2_sub1_5, l, k, j);
                        } else {
                            Model class38_sub2_sub1_3 = locType.getModel(k3, j4, j1, k1, l1, i2, j2);
                            scene.setWallModel(class38_sub2_sub1_3, l, k, j);
                        }
                    } else if (locEntity.anInt1207 == 3) {
                        int i3 = scene.getInfo(j, k, l, i1);
                        int l3 = i3 >> 6;
                        Model class38_sub2_sub1_1 = locType.getModel(22, l3, j1, k1, l1, i2, j2);
                        scene.setGroundDecorationModel(class38_sub2_sub1_1, l, k, j);
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
        for (int j = 0; j < ignoreCount; j++)
            if (ignoreName37[j] == l) {
                ignoreCount--;
                aBoolean964 = true;
                for (int k = j; k < ignoreCount; k++)
                    ignoreName37[k] = ignoreName37[k + 1];

                outBuffer.writeOpcode(171);
                outBuffer.writeQWord(l);
                return;
            }

    }

    public void method131(byte byte0) {
        if (selectedObject == 0 && selectedSpell == 0) {
            options[optionCount] = "Walk here";
            actions[optionCount] = 660;
            paramA[optionCount] = super.anInt21;
            paramB[optionCount] = super.anInt22;
            optionCount++;
        }
        int i = -1;
        if (byte0 != 2)
            aBoolean870 = !aBoolean870;
        for (int j = 0; j < Model.anInt1298; j++) {
            int k = Model.hoveredBitsets[j];
            int l = k & 0x7f;
            int i1 = k >> 7 & 0x7f;
            int j1 = k >> 29 & 3;
            int k1 = k >> 14 & 0x7fff;
            if (k != i) {
                i = k;
                if (j1 == 2 && scene.getInfo(currentLevel, l, i1, k) >= 0) {
                    LocType locType = LocType.get(k1);
                    if (selectedObject == 1) {
                        options[optionCount] = "Use " + selectedObjName + " with @cya@" + locType.name;
                        actions[optionCount] = 450;
                        paramC[optionCount] = k;
                        paramA[optionCount] = l;
                        paramB[optionCount] = i1;
                        optionCount++;
                    } else if (selectedSpell == 1) {
                        if ((selectedFlags & 4) == 4) {
                            options[optionCount] = selectedSpellPrefix + " @cya@" + locType.name;
                            actions[optionCount] = 55;
                            paramC[optionCount] = k;
                            paramA[optionCount] = l;
                            paramB[optionCount] = i1;
                            optionCount++;
                        }
                    } else {
                        if (locType.actions != null) {
                            for (int l1 = 4; l1 >= 0; l1--)
                                if (locType.actions[l1] != null) {
                                    options[optionCount] = locType.actions[l1] + " @cya@"
                                            + locType.name;
                                    if (l1 == 0)
                                        actions[optionCount] = 285;
                                    if (l1 == 1)
                                        actions[optionCount] = 504;
                                    if (l1 == 2)
                                        actions[optionCount] = 364;
                                    if (l1 == 3)
                                        actions[optionCount] = 581;
                                    if (l1 == 4)
                                        actions[optionCount] = 1501;
                                    paramC[optionCount] = k;
                                    paramA[optionCount] = l;
                                    paramB[optionCount] = i1;
                                    optionCount++;
                                }

                        }
                        options[optionCount] = "Examine @cya@" + locType.name;
                        actions[optionCount] = 1175;
                        paramC[optionCount] = k;
                        paramA[optionCount] = l;
                        paramB[optionCount] = i1;
                        optionCount++;
                    }
                }
                if (j1 == 1) {
                    NpcEntity npcEntity = npcEntities[k1];
                    if (npcEntity.info.size == 1
                            && (npcEntity.x & 0x7f) == 64
                            && (npcEntity.z & 0x7f) == 64) {
                        for (int i2 = 0; i2 < npcCount; i2++) {
                            NpcEntity npcEntity_1 = npcEntities[npcIndices[i2]];
                            if (npcEntity_1 != null && npcEntity_1 != npcEntity
                                    && npcEntity_1.info.size == 1
                                    && npcEntity_1.x == npcEntity.x
                                    && npcEntity_1.z == npcEntity.z)
                                method34(npcEntity_1.info, -641, i1, l, npcIndices[i2]);
                        }

                    }
                    method34(npcEntity.info, -641, i1, l, k1);
                }
                if (j1 == 0) {
                    PlayerEntity playerEntity = playerEntities[k1];
                    if ((playerEntity.x & 0x7f) == 64
                            && (playerEntity.z & 0x7f) == 64) {
                        for (int j2 = 0; j2 < npcCount; j2++) {
                            NpcEntity npcEntity_2 = npcEntities[npcIndices[j2]];
                            if (npcEntity_2 != null && npcEntity_2.info.size == 1
                                    && npcEntity_2.x == playerEntity.x
                                    && npcEntity_2.z == playerEntity.z)
                                method34(npcEntity_2.info, -641, i1, l, npcIndices[j2]);
                        }

                        for (int k2 = 0; k2 < entityCount; k2++) {
                            PlayerEntity playerEntity_1 = playerEntities[playerIndices[k2]];
                            if (playerEntity_1 != null && playerEntity_1 != playerEntity
                                    && playerEntity_1.x == playerEntity.x
                                    && playerEntity_1.z == playerEntity.z)
                                method102(i1, playerIndices[k2], playerEntity_1, l);
                        }

                    }
                    method102(i1, k1, playerEntity, l);
                }
                if (j1 == 3) {
                    LinkedList linkedList = objects[currentLevel][l][i1];
                    if (linkedList != null) {
                        for (ObjStackEntity objStackEntity = (ObjStackEntity) linkedList
                                .peekFirst(); objStackEntity != null; objStackEntity = (ObjStackEntity) linkedList
                                .getNext()) {
                            ObjType objType = ObjType.get(objStackEntity.model);
                            if (selectedObject == 1) {
                                options[optionCount] = "Use " + selectedObjName + " with @lre@" + objType.name;
                                actions[optionCount] = 217;
                                paramC[optionCount] = objStackEntity.model;
                                paramA[optionCount] = l;
                                paramB[optionCount] = i1;
                                optionCount++;
                            } else if (selectedSpell == 1) {
                                if ((selectedFlags & 1) == 1) {
                                    options[optionCount] = selectedSpellPrefix + " @lre@" + objType.name;
                                    actions[optionCount] = 965;
                                    paramC[optionCount] = objStackEntity.model;
                                    paramA[optionCount] = l;
                                    paramB[optionCount] = i1;
                                    optionCount++;
                                }
                            } else {
                                for (int l2 = 4; l2 >= 0; l2--)
                                    if (objType.groundOptions != null && objType.groundOptions[l2] != null) {
                                        options[optionCount] = objType.groundOptions[l2] + " @lre@"
                                                + objType.name;
                                        if (l2 == 0)
                                            actions[optionCount] = 224;
                                        if (l2 == 1)
                                            actions[optionCount] = 993;
                                        if (l2 == 2)
                                            actions[optionCount] = 99;
                                        if (l2 == 3)
                                            actions[optionCount] = 746;
                                        if (l2 == 4)
                                            actions[optionCount] = 877;
                                        paramC[optionCount] = objStackEntity.model;
                                        paramA[optionCount] = l;
                                        paramB[optionCount] = i1;
                                        optionCount++;
                                    } else if (l2 == 2) {
                                        options[optionCount] = "Take @lre@" + objType.name;
                                        actions[optionCount] = 99;
                                        paramC[optionCount] = objStackEntity.model;
                                        paramA[optionCount] = l;
                                        paramB[optionCount] = i1;
                                        optionCount++;
                                    }

                                options[optionCount] = "Examine @lre@" + objType.name;
                                actions[optionCount] = 1102;
                                paramC[optionCount] = objStackEntity.model;
                                paramA[optionCount] = l;
                                paramB[optionCount] = i1;
                                optionCount++;
                            }
                        }

                    }
                }
            }
        }

    }

    public void method132(boolean flag) {
        aBoolean974 &= flag;
        for (int i = -1; i < entityCount; i++) {
            int j;
            if (i == -1)
                j = LOCAL_PLAYER_INDEX;
            else
                j = playerIndices[i];
            PlayerEntity playerEntity = playerEntities[j];
            if (playerEntity != null)
                method63(playerEntity, (byte) -128, 1);
        }

        anInt913++;
        if (anInt913 > 1406) {
            anInt913 = 0;
            outBuffer.writeOpcode(219);
            outBuffer.writeByte(0);
            int k = outBuffer.offset;
            outBuffer.writeByte(162);
            outBuffer.writeByte(22);
            if ((int) (Math.random() * 2D) == 0)
                outBuffer.writeByte(84);
            outBuffer.writeWord(31824);
            outBuffer.writeWord(13490);
            if ((int) (Math.random() * 2D) == 0)
                outBuffer.writeByte(123);
            if ((int) (Math.random() * 2D) == 0)
                outBuffer.writeByte(134);
            outBuffer.writeByte(100);
            outBuffer.writeByte(94);
            outBuffer.writeWord(35521);
            outBuffer.writeSize(outBuffer.offset - k);
        }
    }

    public void method133() {
        if (anInt911 != 2) {
            return;
        }

        method91((anInt746 - baseTileZ << 7) + anInt749, (anInt745 - baseTileX << 7) + anInt748, anInt1105, anInt747 * 2);
        if (anInt1019 > -1 && clientClock % 20 < 10) {
            aClass38_Sub2_Sub2_Sub2Array956[2].draw(anInt1020 - 28, anInt1019 - 12);
        }
    }

    public void updateLocalPlayers(Buffer buffer) {
        buffer.accessBits();

        int hasUpdate = buffer.getBits(1);
        if (hasUpdate == 0) {
            return;
        }

        int type = buffer.getBits(2);
        if (type == 0) {
            entityUpdateIndices[updateCount++] = LOCAL_PLAYER_INDEX;
        } else if (type == 1) {
            int i1 = buffer.getBits(3);
            self.walk(false, i1);
            int l1 = buffer.getBits(1);
            if (l1 == 1) {
                entityUpdateIndices[updateCount++] = LOCAL_PLAYER_INDEX;
            }
        } else if (type == 2) {
            int j1 = buffer.getBits(3);
            self.walk(true, j1);
            int i2 = buffer.getBits(3);
            self.walk(true, i2);
            int k2 = buffer.getBits(1);
            if (k2 == 1) {
                entityUpdateIndices[updateCount++] = LOCAL_PLAYER_INDEX;
            }
        } else if (type == 3) {
            currentLevel = buffer.getBits(2);
            int k1 = buffer.getBits(7);
            int j2 = buffer.getBits(7);
            int l2 = buffer.getBits(1);
            self.move(l2 == 1, k1, j2);
            int i3 = buffer.getBits(1);
            if (i3 == 1) {
                entityUpdateIndices[updateCount++] = LOCAL_PLAYER_INDEX;
            }
        }
    }

    public void method135(boolean flag) {
        if (flag)
            anInt780 = -1;
        drawArea23.bind();
        Draw3D.offsets = anIntArray735;
        indexedSprite20.draw(0, 0);
        if (aBoolean869) {
            fontBold12.drawRightAligned(40, 0, aString775, 239);
            fontBold12.drawRightAligned(60, 128, aString765 + "*", 239);
        } else if (aBoolean1055) {
            fontBold12.drawRightAligned(40, 0, "Enter amount:", 239);
            fontBold12.drawRightAligned(60, 128, aString784 + "*", 239);
        } else if (aString936 != null) {
            fontBold12.drawRightAligned(40, 0, aString936, 239);
            fontBold12.drawRightAligned(60, 128, "Click to continue", 239);
        } else if (anInt1001 != -1)
            method59(0, 0, 38682, InterfaceComponent.instances[anInt1001], 0);
        else if (anInt1021 != -1) {
            method59(0, 0, 38682, InterfaceComponent.instances[anInt1021], 0);
        } else {
            IndexedFont indexedFont = indexedFont2;
            int i = 0;
            Draw2D.setBounds(77, 0, 463, 0);
            for (int j = 0; j < 100; j++)
                if (messageText[j] != null) {
                    int k = anIntArray896[j];
                    int l = (70 - i * 14) + anInt977;
                    if (k == 0) {
                        if (l > 0 && l < 110)
                            indexedFont.draw(4, l, 0, messageText[j]);
                        i++;
                    }
                    if (k == 1) {
                        if (l > 0 && l < 110) {
                            indexedFont.draw(4, l, 0xffffff, aStringArray897[j] + ":");
                            indexedFont.draw(
                                    12 + indexedFont.stringWidth(aStringArray897[j]), l, 255,
                                    messageText[j]);
                        }
                        i++;
                    }
                    if (k == 2 && (anInt976 == 0 || anInt976 == 1 && method138(-20, aStringArray897[j]))) {
                        if (l > 0 && l < 110) {
                            indexedFont.draw(4, l, 0, aStringArray897[j] + ":");
                            indexedFont.draw(
                                    12 + indexedFont.stringWidth(aStringArray897[j]), l, 255,
                                    messageText[j]);
                        }
                        i++;
                    }
                    if ((k == 3 || k == 7) && anInt833 == 0
                            && (k == 7 || anInt755 == 0 || anInt755 == 1 && method138(-20, aStringArray897[j]))) {
                        if (l > 0 && l < 110) {
                            indexedFont.draw(4, l, 0, "From " + aStringArray897[j] + ":");
                            indexedFont.draw(
                                    12 + indexedFont.stringWidth("From " + aStringArray897[j]), l,
                                    0x800000, messageText[j]);
                        }
                        i++;
                    }
                    if (k == 4 && (anInt885 == 0 || anInt885 == 1 && method138(-20, aStringArray897[j]))) {
                        if (l > 0 && l < 110)
                            indexedFont.draw(4, l, 0x800080,
                                    aStringArray897[j] + " " + messageText[j]);
                        i++;
                    }
                    if (k == 5 && anInt833 == 0 && anInt755 < 2) {
                        if (l > 0 && l < 110)
                            indexedFont.draw(4, l, 0x800000, messageText[j]);
                        i++;
                    }
                    if (k == 6 && anInt833 == 0 && anInt755 < 2) {
                        if (l > 0 && l < 110) {
                            indexedFont.draw(4, l, 0, "To " + aStringArray897[j] + ":");
                            indexedFont.draw(
                                    12 + indexedFont.stringWidth("To " + aStringArray897[j]), l,
                                    0x800000, messageText[j]);
                        }
                        i++;
                    }
                    if (k == 8 && (anInt885 == 0 || anInt885 == 1 && method138(-20, aStringArray897[j]))) {
                        if (l > 0 && l < 110)
                            indexedFont.draw(4, l, 0xcbb789,
                                    aStringArray897[j] + " " + messageText[j]);
                        i++;
                    }
                }

            Draw2D.resetBounds();
            anInt792 = i * 14 + 7;
            if (anInt792 < 78)
                anInt792 = 78;
            method50(anInt803, 463, 0, anInt792 - anInt977 - 77, anInt792, 77);
            indexedFont.draw(4, 90, 0, StringUtils.formatName(aString1066) + ":");
            indexedFont.draw(6 + indexedFont.stringWidth(aString1066 + ": "), 90,
                    255, aString1137 + "*");
            Draw2D.drawHorizontalLine(0, 77, 479, 0);
        }
        if (aBoolean879 && anInt1148 == 2)
            method74(-961);
        drawArea23.drawImage(375, super.graphics, 22);
        drawArea22.bind();
        Draw3D.offsets = anIntArray737;
    }

    public boolean method136(boolean flag) {
        if (flag)
            objects = null;
        if (bufferedStream == null)
            return false;
        try {
            int i = bufferedStream.available();
            if (i == 0)
                return false;
            if (anInt780 == -1) {
                bufferedStream.read(inBuffer.data, 0, 1);
                anInt780 = inBuffer.data[0] & 0xff;
                if (isaacState != null)
                    anInt780 = anInt780 - isaacState.nextInt() & 0xff;
                anInt779 = Packet.packetLengths[anInt780];
                i--;
            }
            if (anInt779 == -1)
                if (i > 0) {
                    bufferedStream.read(inBuffer.data, 0, 1);
                    anInt779 = inBuffer.data[0] & 0xff;
                    i--;
                } else {
                    return false;
                }
            if (anInt779 == -2)
                if (i > 1) {
                    bufferedStream.read(inBuffer.data, 0, 2);
                    inBuffer.offset = 0;
                    anInt779 = inBuffer.readWord();
                    i -= 2;
                } else {
                    return false;
                }
            if (i < anInt779)
                return false;
            inBuffer.offset = 0;
            bufferedStream.read(inBuffer.data, 0, anInt779);
            anInt781 = 0;
            anInt830 = anInt829;
            anInt829 = anInt828;
            anInt828 = anInt780;
            if (anInt780 == 150) {
                int j = inBuffer.readWord();
                byte byte0 = inBuffer.readByteSigned();
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
                long l = inBuffer.readQWord();
                int j16 = inBuffer.readByte();
                String s6 = StringUtils.formatName(StringUtils.fromBase37(l));
                for (int l24 = 0; l24 < friendCount; l24++) {
                    if (l != friendName37[l24])
                        continue;
                    if (friendWorld[l24] != j16) {
                        friendWorld[l24] = j16;
                        aBoolean964 = true;
                        if (j16 > 0)
                            addMessage(5, s6 + " has logged in.", (byte) 4, "");
                        if (j16 == 0)
                            addMessage(5, s6 + " has logged out.", (byte) 4, "");
                    }
                    s6 = null;
                    break;
                }

                if (s6 != null && friendCount < 100) {
                    friendName37[friendCount] = l;
                    friendName[friendCount] = s6;
                    friendWorld[friendCount] = j16;
                    friendCount++;
                    aBoolean964 = true;
                }
                for (boolean flag5 = false; !flag5; ) {
                    flag5 = true;
                    for (int i29 = 0; i29 < friendCount - 1; i29++)
                        if (friendWorld[i29] != nodeid && friendWorld[i29 + 1] == nodeid
                                || friendWorld[i29] == 0 && friendWorld[i29 + 1] != 0) {
                            int j30 = friendWorld[i29];
                            friendWorld[i29] = friendWorld[i29 + 1];
                            friendWorld[i29 + 1] = j30;
                            String s8 = friendName[i29];
                            friendName[i29] = friendName[i29 + 1];
                            friendName[i29 + 1] = s8;
                            long l31 = friendName37[i29];
                            friendName37[i29] = friendName37[i29 + 1];
                            friendName37[i29 + 1] = l31;
                            aBoolean964 = true;
                            flag5 = false;
                        }

                }

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 43) {
                anInt957 = inBuffer.readWord() * 30;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 80) {
                int k = inBuffer.readByte();
                int i10 = inBuffer.readByte();
                int k16 = -1;
                for (int k21 = 0; k21 < anIntArray925.length; k21++)
                    if (anIntArray925[k21] == (k << 8) + i10)
                        k16 = k21;

                if (k16 != -1) {
                    Signlink.cachesave("m" + k + "_" + i10, aByteArrayArray770[k16]);
                    anInt1078 = 1;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 1) {
                updateNpcs(inBuffer, anInt779);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 237) {
                int i1 = inBuffer.readWord();
                int j10 = inBuffer.readWord();
                if (anInt838 == i1 && anInt839 == j10 && anInt1078 != 0) {
                    anInt780 = -1;
                    return true;
                }
                anInt838 = i1;
                anInt839 = j10;
                baseTileX = (anInt838 - 6) * 8;
                baseTileZ = (anInt839 - 6) * 8;
                anInt1078 = 1;
                drawArea22.bind();
                indexedFont2.drawRightAligned(151, 0, "Loading - please wait.", 257);
                indexedFont2.drawRightAligned(150, 0xffffff, "Loading - please wait.", 256);
                drawArea22.drawImage(11, super.graphics, 8);
                Signlink.looprate(5);
                int l16 = (anInt779 - 2) / 10;
                aByteArrayArray770 = new byte[l16][];
                aByteArrayArray1000 = new byte[l16][];
                anIntArray925 = new int[l16];
                outBuffer.writeOpcode(150);
                outBuffer.writeByte(0);
                int i22 = 0;
                for (int i25 = 0; i25 < l16; i25++) {
                    int i27 = inBuffer.readByte();
                    int j29 = inBuffer.readByte();
                    int k30 = inBuffer.readDWord();
                    int i31 = inBuffer.readDWord();
                    anIntArray925[i25] = (i27 << 8) + j29;
                    if (k30 != 0) {
                        byte[] abyte1 = Signlink.cacheload("m" + i27 + "_" + j29);
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
                            outBuffer.writeByte(0);
                            outBuffer.writeByte(i27);
                            outBuffer.writeByte(j29);
                            i22 += 3;
                        }
                    }
                    if (i31 != 0) {
                        byte[] abyte2 = Signlink.cacheload("l" + i27 + "_" + j29);
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
                            outBuffer.writeByte(1);
                            outBuffer.writeByte(i27);
                            outBuffer.writeByte(j29);
                            i22 += 3;
                        }
                    }
                }

                outBuffer.writeSize(i22);
                Signlink.looprate(50);
                drawArea22.bind();
                if (anInt1078 == 0) {
                    indexedFont2.drawRightAligned(166, 0,
                            "Map area updated since last visit, so load will take longer this time only", 257);
                    indexedFont2.drawRightAligned(165, 0xffffff,
                            "Map area updated since last visit, so load will take longer this time only", 256);
                }
                drawArea22.drawImage(11, super.graphics, 8);
                int j27 = baseTileX - anInt763;
                int k29 = baseTileZ - anInt764;
                anInt763 = baseTileX;
                anInt764 = baseTileZ;
                for (int l30 = 0; l30 < 8192; l30++) {
                    NpcEntity npcEntity = npcEntities[l30];
                    if (npcEntity != null) {
                        for (int k31 = 0; k31 < 10; k31++) {
                            npcEntity.pathTileX[k31] -= j27;
                            npcEntity.pathTileZ[k31] -= k29;
                        }

                        npcEntity.x -= j27 * 128;
                        npcEntity.z -= k29 * 128;
                    }
                }

                for (int j31 = 0; j31 < MAX_PLAYER_COUNT; j31++) {
                    PlayerEntity playerEntity = playerEntities[j31];
                    if (playerEntity != null) {
                        for (int i32 = 0; i32 < 10; i32++) {
                            playerEntity.pathTileX[i32] -= j27;
                            playerEntity.pathTileZ[i32] -= k29;
                        }

                        playerEntity.x -= j27 * 128;
                        playerEntity.z -= k29 * 128;
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
                                objects[j33][j32][k32] = objects[j33][l32][i33];
                            else
                                objects[j33][j32][k32] = null;

                    }

                }

                for (SpawnedLoc spawnedLoc_1 = (SpawnedLoc) spawnedLocations
                        .peekLast(); spawnedLoc_1 != null; spawnedLoc_1 = (SpawnedLoc) spawnedLocations
                        .getPrevious()) {
                    spawnedLoc_1.tileX -= j27;
                    spawnedLoc_1.tileZ -= k29;
                    if (spawnedLoc_1.tileX < 0 || spawnedLoc_1.tileZ < 0 || spawnedLoc_1.tileX >= 104
                            || spawnedLoc_1.tileZ >= 104)
                        spawnedLoc_1.unlink();
                }

                if (flagTileX != 0) {
                    flagTileX -= j27;
                    flagTileY -= k29;
                }
                aBoolean968 = false;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 197) {
                int j1 = inBuffer.readWord();
                InterfaceComponent.instances[j1].aClass38_Sub2_Sub1_310 = self.getHeadModel();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 25) {
                anInt911 = inBuffer.readByte();
                if (anInt911 == 1)
                    anInt801 = inBuffer.readWord();
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
                    anInt745 = inBuffer.readWord();
                    anInt746 = inBuffer.readWord();
                    anInt747 = inBuffer.readByte();
                }
                if (anInt911 == 10)
                    anInt1076 = inBuffer.readWord();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 54) {
                String s = inBuffer.readString();
                int k10 = inBuffer.readDWord();
                int i17 = inBuffer.readDWord();
                if (!s.equals(aString1119) && aBoolean835 && !lowMemory)
                    method14(k10, s, i17);
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
                int k1 = inBuffer.readByte();
                int l10 = inBuffer.readByte();
                int j17 = -1;
                for (int j22 = 0; j22 < anIntArray925.length; j22++)
                    if (anIntArray925[j22] == (k1 << 8) + l10)
                        j17 = j22;

                if (j17 != -1) {
                    Signlink.cachesave("l" + k1 + "_" + l10, aByteArrayArray1000[j17]);
                    anInt1078 = 1;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 19) {
                flagTileX = 0;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 139) {
                anInt734 = inBuffer.readWord();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 151 || anInt780 == 23 || anInt780 == 50 || anInt780 == 191 || anInt780 == 69
                    || anInt780 == 49 || anInt780 == 223 || anInt780 == 42 || anInt780 == 76 || anInt780 == 59) {
                method22((byte) -45, inBuffer, anInt780);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 28) {
                int l1 = inBuffer.readWord();
                int i11 = inBuffer.readWord();
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
                int i2 = inBuffer.readWord();
                int j11 = inBuffer.readDWord();
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
                int j2 = inBuffer.readWord();
                int k11 = inBuffer.readWord();
                InterfaceComponent.instances[j2].anInt312 = k11;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 167) {
                int k2 = inBuffer.readWord();
                int l11 = inBuffer.readByte();
                if (k2 == 65535)
                    k2 = -1;
                anIntArray861[l11] = k2;
                aBoolean964 = true;
                aBoolean1080 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 220) {
                int l2 = inBuffer.readByte();
                int i12 = inBuffer.readByte();
                int k17 = inBuffer.readWord();
                int k22 = inBuffer.readWord();
                int j25 = -1;
                for (int k27 = 0; k27 < anIntArray925.length; k27++)
                    if (anIntArray925[k27] == (l2 << 8) + i12)
                        j25 = k27;

                if (j25 != -1) {
                    if (aByteArrayArray1000[j25] == null || aByteArrayArray1000[j25].length != k22)
                        aByteArrayArray1000[j25] = new byte[k22];
                    inBuffer.readBytes(anInt779 - 6, k17, aByteArrayArray1000[j25]);
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 133) {
                Buffer class38_sub2_sub3 = InputTracking.method185(854);
                if (class38_sub2_sub3 != null) {
                    outBuffer.writeOpcode(81);
                    outBuffer.writeWord(class38_sub2_sub3.offset);
                    outBuffer.writeBytes(class38_sub2_sub3.data, class38_sub2_sub3.offset, 0
                    );
                    class38_sub2_sub3.release();
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 98) {
                aBoolean964 = true;
                int i3 = inBuffer.readWord();
                InterfaceComponent interfaceComponent = InterfaceComponent.instances[i3];
                int l17 = inBuffer.readByte();
                for (int l22 = 0; l22 < l17; l22++) {
                    interfaceComponent.anIntArray265[l22] = inBuffer.readWord();
                    int k25 = inBuffer.readByte();
                    if (k25 == 255)
                        k25 = inBuffer.readDWord();
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
                InputTracking.method182(-31717);
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
                int j3 = inBuffer.readWord();
                InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[j3];
                for (int i18 = 0; i18 < interfaceComponent_1.anIntArray265.length; i18++) {
                    interfaceComponent_1.anIntArray265[i18] = -1;
                    interfaceComponent_1.anIntArray265[i18] = 0;
                }

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 140) {
                anInt1061 = inBuffer.readDWord();
                anInt873 = inBuffer.readWord();
                anInt901 = inBuffer.readByte();
                anInt1054 = inBuffer.readWord();
                if (anInt1061 != 0 && anInt971 == -1) {
                    Signlink.dnslookup(StringUtils.fromIPv4(anInt1061));
                    method16((byte) -60);
                    char c = '\u028A';
                    if (anInt901 != 201)
                        c = '\u028F';
                    aString970 = "";
                    aBoolean881 = false;
                    for (int j12 = 0; j12 < InterfaceComponent.instances.length; j12++) {
                        if (InterfaceComponent.instances[j12] == null || InterfaceComponent.instances[j12].anInt273 != c)
                            continue;
                        anInt971 = InterfaceComponent.instances[j12].anInt270;
                        break;
                    }

                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 126) {
                anInt1128 = inBuffer.readByte();
                if (anInt1128 == selectedTab) {
                    if (anInt1128 == 3)
                        selectedTab = 1;
                    else
                        selectedTab = 3;
                    aBoolean964 = true;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 212) {
                if (aBoolean835 && !lowMemory) {
                    int k3 = inBuffer.readWord();
                    int k12 = inBuffer.readDWord();
                    int j18 = anInt779 - 6;
                    byte[] abyte0 = new byte[k12];
                    BZip2InputStream.read(abyte0, k12, inBuffer.data, j18,
                            inBuffer.offset);
                    method52(abyte0, 625, k12, false);
                    anInt744 = k3;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 254) {
                inMultizone = inBuffer.readByte();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 12) {
                int l3 = inBuffer.readWord();
                int l12 = inBuffer.readByte();
                int k18 = inBuffer.readWord();
                if (aBoolean1153 && !lowMemory && anInt1018 < 50) {
                    anIntArray1124[anInt1018] = l3;
                    anIntArray809[anInt1018] = l12;
                    anIntArray858[anInt1018] = k18 + SoundTrack.delays[l3];
                    anInt1018++;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 204) {
                int i4 = inBuffer.readWord();
                int i13 = inBuffer.readWord();
                NpcType npcType = NpcType.get(i13);
                InterfaceComponent.instances[i4].aClass38_Sub2_Sub1_310 = npcType.getHeadModel();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 7) {
                anInt862 = inBuffer.readByte();
                anInt863 = inBuffer.readByte();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 103) {
                int j4 = inBuffer.readWord();
                int j13 = inBuffer.readWord();
                int l18 = inBuffer.readWord();
                InterfaceComponent interfaceComponent_3 = InterfaceComponent.instances[j4];
                Model class38_sub2_sub1 = interfaceComponent_3.aClass38_Sub2_Sub1_310;
                if (class38_sub2_sub1 != null)
                    class38_sub2_sub1.recolor(j13, l18);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 32) {
                anInt976 = inBuffer.readByte();
                anInt755 = inBuffer.readByte();
                anInt885 = inBuffer.readByte();
                aBoolean921 = true;
                aBoolean965 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 195) {
                int k4 = inBuffer.readWord();
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
                int l4 = inBuffer.readWord();
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
                int i5 = inBuffer.readWord();
                int k13 = inBuffer.readWordSigned();
                int i19 = inBuffer.readWordSigned();
                InterfaceComponent interfaceComponent_4 = InterfaceComponent.instances[i5];
                interfaceComponent_4.anInt276 = k13;
                interfaceComponent_4.anInt277 = i19;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 3) {
                aBoolean968 = true;
                anInt728 = inBuffer.readByte();
                anInt729 = inBuffer.readByte();
                anInt730 = inBuffer.readWord();
                anInt731 = inBuffer.readByte();
                anInt732 = inBuffer.readByte();
                if (anInt732 >= 100) {
                    anInt1111 = anInt728 * 128 + 64;
                    anInt1113 = anInt729 * 128 + 64;
                    anInt1112 = method33(currentLevel, anInt728, (byte) 5, anInt729) - anInt730;
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 135) {
                anInt862 = inBuffer.readByte();
                anInt863 = inBuffer.readByte();
                for (int j5 = anInt862; j5 < anInt862 + 8; j5++) {
                    for (int l13 = anInt863; l13 < anInt863 + 8; l13++)
                        if (objects[currentLevel][j5][l13] != null) {
                            objects[currentLevel][j5][l13] = null;
                            method123(j5, l13);
                        }

                }

                for (SpawnedLoc spawnedLoc = (SpawnedLoc) spawnedLocations
                        .peekLast(); spawnedLoc != null; spawnedLoc = (SpawnedLoc) spawnedLocations.getPrevious())
                    if (spawnedLoc.tileX >= anInt862 && spawnedLoc.tileX < anInt862 + 8
                            && spawnedLoc.tileZ >= anInt863 && spawnedLoc.tileZ < anInt863 + 8
                            && spawnedLoc.level == currentLevel) {
                        method99(spawnedLoc.lastRotation, spawnedLoc.tileX, spawnedLoc.tileZ,
                                spawnedLoc.classType, spawnedLoc.lastLocIndex, spawnedLoc.lastType, -27819,
                                spawnedLoc.level);
                        spawnedLoc.unlink();
                    }

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 132) {
                int k5 = inBuffer.readByte();
                int i14 = inBuffer.readByte();
                int j19 = inBuffer.readWord();
                int i23 = inBuffer.readWord();
                int i26 = -1;
                for (int l27 = 0; l27 < anIntArray925.length; l27++)
                    if (anIntArray925[l27] == (k5 << 8) + i14)
                        i26 = l27;

                if (i26 != -1) {
                    if (aByteArrayArray770[i26] == null || aByteArrayArray770[i26].length != i23)
                        aByteArrayArray770[i26] = new byte[i23];
                    inBuffer.readBytes(anInt779 - 6, j19, aByteArrayArray770[i26]);
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 41) {
                long l5 = inBuffer.readQWord();
                int k19 = inBuffer.readDWord();
                int j23 = inBuffer.readByte();
                boolean flag2 = false;
                for (int i28 = 0; i28 < 100; i28++) {
                    if (anIntArray878[i28] != k19)
                        continue;
                    flag2 = true;
                    break;
                }

                if (j23 <= 1) {
                    for (int l29 = 0; l29 < ignoreCount; l29++) {
                        if (ignoreName37[l29] != l5)
                            continue;
                        flag2 = true;
                        break;
                    }

                }
                if (!flag2 && anInt802 == 0)
                    try {
                        anIntArray878[anInt855] = k19;
                        anInt855 = (anInt855 + 1) % 100;
                        String s7 = TextEncoder.read(inBuffer, anInt779 - 13);
                        s7 = WordEncoding.getFiltered(s7);
                        if (j23 > 1)
                            addMessage(7, s7, (byte) 4, StringUtils.formatName(StringUtils.fromBase37(l5)));
                        else
                            addMessage(3, s7, (byte) 4, StringUtils.formatName(StringUtils.fromBase37(l5)));
                    } catch (Exception exception1) {
                        Signlink.reporterror("cde1");
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
                int j6 = inBuffer.readWord();
                int j14 = inBuffer.readWord();
                InterfaceComponent.instances[j6].aClass38_Sub2_Sub1_310 = new Model(j14);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 185) {
                int k6 = inBuffer.readWordSigned();
                anInt1021 = k6;
                aBoolean965 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 68) {
                if (selectedTab == 12)
                    aBoolean964 = true;
                anInt1072 = inBuffer.readByte();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 74) {
                aBoolean968 = true;
                anInt948 = inBuffer.readByte();
                anInt949 = inBuffer.readByte();
                anInt950 = inBuffer.readWord();
                anInt951 = inBuffer.readByte();
                anInt952 = inBuffer.readByte();
                if (anInt952 >= 100) {
                    int l6 = anInt948 * 128 + 64;
                    int k14 = anInt949 * 128 + 64;
                    int l19 = method33(currentLevel, anInt948, (byte) 5, anInt949) - anInt950;
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
                selectedTab = inBuffer.readByte();
                aBoolean964 = true;
                aBoolean1080 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 4) {
                String s1 = inBuffer.readString();
                if (s1.endsWith(":tradereq:")) {
                    String s3 = s1.substring(0, s1.indexOf(":"));
                    long l20 = StringUtils.toBase37(s3);
                    boolean flag3 = false;
                    for (int k28 = 0; k28 < ignoreCount; k28++) {
                        if (ignoreName37[k28] != l20)
                            continue;
                        flag3 = true;
                        break;
                    }

                    if (!flag3 && anInt802 == 0)
                        addMessage(4, "wishes to trade with you.", (byte) 4, s3);
                } else if (s1.endsWith(":duelreq:")) {
                    String s4 = s1.substring(0, s1.indexOf(":"));
                    long l21 = StringUtils.toBase37(s4);
                    boolean flag4 = false;
                    for (int l28 = 0; l28 < ignoreCount; l28++) {
                        if (ignoreName37[l28] != l21)
                            continue;
                        flag4 = true;
                        break;
                    }

                    if (!flag4 && anInt802 == 0)
                        addMessage(8, "wishes to duel with you.", (byte) 4, s4);
                } else {
                    addMessage(0, s1, (byte) 4, "");
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 46) {
                int i7 = inBuffer.readWord();
                int l14 = inBuffer.readWord();
                int i20 = inBuffer.readWord();
                ObjType objType = ObjType.get(l14);
                InterfaceComponent.instances[i7].aClass38_Sub2_Sub1_310 = objType.getModel(50);
                InterfaceComponent.instances[i7].anInt315 = objType.iconCameraPitch;
                InterfaceComponent.instances[i7].anInt316 = objType.iconYaw;
                InterfaceComponent.instances[i7].anInt314 = (objType.iconZoom * 100) / i20;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 168) {
                int j7 = inBuffer.readWord();
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
                int k7 = inBuffer.readWord();
                int i15 = inBuffer.readWord();
                int j20 = i15 >> 10 & 0x1f;
                int l23 = i15 >> 5 & 0x1f;
                int k26 = i15 & 0x1f;
                InterfaceComponent.instances[k7].anInt305 = (j20 << 19) + (l23 << 11) + (k26 << 3);
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 136) {
                for (int l7 = 0; l7 < playerEntities.length; l7++)
                    if (playerEntities[l7] != null)
                        playerEntities[l7].primarySeq = -1;

                for (int j15 = 0; j15 < npcEntities.length; j15++)
                    if (npcEntities[j15] != null)
                        npcEntities[j15].primarySeq = -1;

                anInt780 = -1;
                return true;
            }
            if (anInt780 == 26) {
                int i8 = inBuffer.readWord();
                boolean flag1 = inBuffer.readByte() == 1;
                InterfaceComponent.instances[i8].aBoolean284 = flag1;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 21) {
                ignoreCount = anInt779 / 8;
                for (int j8 = 0; j8 < ignoreCount; j8++)
                    ignoreName37[j8] = inBuffer.readQWord();

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
                int l8 = inBuffer.readWord();
                String s5 = inBuffer.readString();
                InterfaceComponent.instances[l8].aString303 = s5;
                if (InterfaceComponent.instances[l8].anInt270 == anIntArray861[selectedTab])
                    aBoolean964 = true;
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 44) {
                aBoolean964 = true;
                int i9 = inBuffer.readByte();
                int k15 = inBuffer.readDWord();
                int k20 = inBuffer.readByte();
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
                anInt862 = inBuffer.readByte();
                anInt863 = inBuffer.readByte();
                while (inBuffer.offset < anInt779) {
                    int j9 = inBuffer.readByte();
                    method22((byte) -45, inBuffer, j9);
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 22) {
                if (selectedTab == 12)
                    aBoolean964 = true;
                anInt769 = inBuffer.readWordSigned();
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 13) {
                int k9 = inBuffer.readByte();
                int l15 = inBuffer.readByte();
                int i21 = inBuffer.readByte();
                int j24 = inBuffer.readByte();
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
                int l9 = inBuffer.readWord();
                InterfaceComponent interfaceComponent_2 = InterfaceComponent.instances[l9];
                while (inBuffer.offset < anInt779) {
                    int j21 = inBuffer.readByte();
                    int k24 = inBuffer.readWord();
                    int l26 = inBuffer.readByte();
                    if (l26 == 255)
                        l26 = inBuffer.readDWord();
                    if (j21 >= 0 && j21 < interfaceComponent_2.anIntArray265.length) {
                        interfaceComponent_2.anIntArray265[j21] = k24;
                        interfaceComponent_2.anIntArray266[j21] = l26;
                    }
                }
                anInt780 = -1;
                return true;
            }
            if (anInt780 == 184) {
                updatePlayers(inBuffer, anInt779);
                if (anInt1078 == 1) {
                    anInt1078 = 2;
                    SceneBuilder.levelBuilt = currentLevel;
                    method124(869);
                }
                if (lowMemory && anInt1078 == 2 && SceneBuilder.levelBuilt != currentLevel) {
                    drawArea22.bind();
                    indexedFont2.drawRightAligned(151, 0, "Loading - please wait.", 257);
                    indexedFont2.drawRightAligned(150, 0xffffff, "Loading - please wait.", 256);
                    drawArea22.drawImage(11, super.graphics, 8);
                    SceneBuilder.levelBuilt = currentLevel;
                    method124(869);
                }
                if (currentLevel != lastSceneLevel && anInt1078 == 2) {
                    lastSceneLevel = currentLevel;
                    method45(currentLevel, -153);
                }
                anInt780 = -1;
                return true;
            }
            Signlink.reporterror("T1 - " + anInt780 + "," + anInt779 + " - " + anInt829 + "," + anInt830);
            method58(-780);
        } catch (IOException _ex) {
            method121();
        } catch (Exception exception) {
            String s2 = "T2 - " + anInt780 + "," + anInt829 + "," + anInt830 + " - " + anInt779 + ","
                    + (baseTileX + self.pathTileX[0]) + ","
                    + (baseTileZ + self.pathTileZ[0]) + " - ";
            for (int i16 = 0; i16 < anInt779 && i16 < 50; i16++)
                s2 = s2 + inBuffer.data[i16] + ",";

            Signlink.reporterror(s2);
            method58(-780);
        }
        return true;
    }

    public void method137(byte byte0) {
        drawArea20.bind();
        Draw3D.offsets = anIntArray736;
        indexedSprite18.draw(0, 0);
        if (anInt1129 != -1)
            method59(0, 0, 38682, InterfaceComponent.instances[anInt1129], 0);
        else if (anIntArray861[selectedTab] != -1)
            method59(0, 0, 38682, InterfaceComponent.instances[anIntArray861[selectedTab]], 0);
        if (aBoolean879 && anInt1148 == 1)
            method74(-961);
        drawArea20.drawImage(231, super.graphics, 562);
        drawArea22.bind();
        Draw3D.offsets = anIntArray737;
    }

    public boolean method138(int i, String s) {
        if (s == null)
            return false;
        for (int j = 0; j < friendCount; j++)
            if (s.equalsIgnoreCase(friendName[j]))
                return true;

        return s.equalsIgnoreCase(self.name);
    }

    public void init() {
        nodeid = Integer.parseInt(getParameter("nodeid"));
        portoff = Integer.parseInt(getParameter("portoff"));
        String s = getParameter("lowmem");
        if (s != null && s.equals("1")) {
            setLowMemory();
        } else {
            setHighMemory();
        }
        String s1 = getParameter("free");
        members = s1 == null || !s1.equals("1");
        initApplet(532, false, 789);
    }

    public void updatePlayerMask(int i, int mask, Buffer b, PlayerEntity p) {
        if ((mask & 1) == 1) {
            int k = b.readByte();
            byte[] data = new byte[k];
            Buffer buffer = new Buffer(data);
            b.readBytes(k, 0, data);
            playerBuffers[i] = buffer;
            p.read(buffer);
        }

        if ((mask & 2) == 2) {
            int l = b.readWord();
            if (l == 65535)
                l = -1;
            if (l == p.primarySeq)
                p.primarySeqPlays = 0;
            int k1 = b.readByte();
            if (l == -1 || p.primarySeq == -1
                    || SeqType.animations[l].priority > SeqType.animations[p.primarySeq].priority
                    || SeqType.animations[p.primarySeq].priority == 0) {
                p.primarySeq = l;
                p.primarySeqFrame = 0;
                p.primarySeqCycle = 0;
                p.primarySeqDelay = k1;
                p.primarySeqPlays = 0;
            }
        }
        
        if ((mask & 4) == 4) {
            p.targetEntity = b.readWord();
            if (p.targetEntity == 65535) {
                p.targetEntity = -1;
            }
        }
        
        if ((mask & 8) == 0x8) {
            p.spoken = b.readString();
            p.spokenColor = 0;
            p.spokenEffect = 0;
            p.textCycle = 150;
            addMessage(2, p.spoken, (byte) 4, p.name);
        }
        
        if ((mask & 0x10) == 0x10) {
            p.damageTaken = b.readByte();
            p.damageType = b.readByte();
            p.cycleStatus = clientClock + 400;
            p.currentHealth = b.readByte();
            p.maxHealth = b.readByte();
        }

        if ((mask & 0x20) == 0x20) {
            p.focusX = b.readWord();
            p.focusY = b.readWord();
        }

        if ((mask & 0x40) == 0x40) {
            int i1 = b.readWord();
            int l1 = b.readByte();
            int i2 = b.readByte();
            int j2 = b.offset;
            if (p.name != null) {
                long l2 = StringUtils.toBase37(p.name);
                boolean ignore = false;
                if (l1 <= 1) {
                    for (int k2 = 0; k2 < ignoreCount; k2++) {
                        if (ignoreName37[k2] != l2)
                            continue;
                        ignore = true;
                        break;
                    }

                }
                if (!ignore && anInt802 == 0)
                    try {
                        String s = TextEncoder.read(b, i2);
                        s = WordEncoding.getFiltered(s);
                        p.spoken = s;
                        p.spokenColor = i1 >> 8;
                        p.spokenEffect = i1 & 0xff;
                        p.textCycle = 150;
                        if (l1 > 1)
                            addMessage(1, s, (byte) 4, p.name);
                        else
                            addMessage(2, s, (byte) 4, p.name);
                    } catch (Exception exception) {
                        Signlink.reporterror("cde2");
                    }
            }
            b.offset = j2 + i2;
        }

        if ((mask & 0x100) == 0x100) {
            p.spotAnimIndex = b.readWord();
            int j1 = b.readDWord();
            p.spotAnimOffsetY = j1 >> 16;
            p.lastSpotAnimCycle = clientClock + (j1 & 0xffff);
            p.spotAnimFrame = 0;
            p.spotAnimCycle = 0;
            if (p.lastSpotAnimCycle > clientClock)
                p.spotAnimFrame = -1;
            if (p.spotAnimIndex == 65535)
                p.spotAnimIndex = -1;
        }

        if ((mask & 0x200) == 0x200) {
            p.srcTileX = b.readByte();
            p.srcTileY = b.readByte();
            p.dstTileX = b.readByte();
            p.dstTileY = b.readByte();
            p.firstMoveCycle = b.readWord() + clientClock;
            p.lastMoveCycle = b.readWord() + clientClock;
            p.faceDirection = b.readByte();
            p.pathRemaining = 0;
            p.pathTileX[0] = p.dstTileX;
            p.pathTileZ[0] = p.dstTileY;
        }
    }

    public void showProgress(boolean flag, String s, int i) {
        prepareTitleScreen((byte) 99);
        if (fileArchive == null) {
            super.showProgress(true, s, i);
            return;
        }

        char c = '\u0168';
        char c1 = '\310';
        byte byte0 = 20;

        imageTitle4.bind();
        fontBold12.drawRightAligned(c1 / 2 - 26 - byte0, 0xffffff, "RuneScape is loading - please wait...", c / 2);

        int j = c1 / 2 - 18 - byte0;
        Draw2D.drawRect(c / 2 - 152, 0x8c1111, 34, j, 304);
        Draw2D.drawRect(c / 2 - 151, 0, 32, j + 1, 302);
        Draw2D.fillRect(j + 2, c / 2 - 150, 0x8c1111, i * 3, 30);
        Draw2D.fillRect(j + 2, (c / 2 - 150) + i * 3, 0, 300 - i * 3, 30);

        fontBold12.drawRightAligned((c1 / 2 + 5) - byte0, 0xffffff, s, c / 2);
        imageTitle4.drawImage(186, super.graphics, 214);

        if (redrawTitleBackground) {
            redrawTitleBackground = false;

            if (!flameActive) {
                imageTitle0.drawImage(0, super.graphics, 0);
                imageTitle1.drawImage(0, super.graphics, 661);
            }

            imageTitle2.drawImage(0, super.graphics, 128);
            imageTitle3.drawImage(386, super.graphics, 214);
            imageTitle5.drawImage(265, super.graphics, 0);
            imageTitle6.drawImage(265, super.graphics, 574);
            imageTitle7.drawImage(186, super.graphics, 128);
            imageTitle8.drawImage(186, super.graphics, 574);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("RS2 user client - release #" + 225);

            if (args.length != 4) {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
                return;
            }

            nodeid = Integer.parseInt(args[0]);
            portoff = Integer.parseInt(args[1]);

            if (args[2].equals("lowmem")) {
                setLowMemory();
            } else if (args[2].equals("highmem")) {
                setHighMemory();
            } else {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
                return;
            }

            if (args[3].equals("free")) {
                members = false;
            } else if (args[3].equals("members")) {
                members = true;
            } else {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
                return;
            }

            Signlink.startpriv(InetAddress.getLocalHost());
            Game game = new Game();
            game.method1(532, 789, 0);
        } catch (Exception exception) {
        }
    }

    public Game() {
        anInt733 = 24676;
        anInt734 = -1;
        characterDesignColors = new int[5];
        loginBuffer = Buffer.reserve(1);
        redrawTitleBackground = false;
        linkedList2 = new LinkedList();
        aBooleanArray754 = new boolean[5];
        anInt756 = 9;
        selectedTab = 3;
        pathDistance = new int[104][104];
        anInt759 = 997;
        aString765 = "";
        aByte766 = 106;
        temporaryLocs = new LinkedList();
        ignoreName37 = new long[100];
        anInt771 = 723;
        friendWorld = new int[100];
        lastSceneLevel = -1;
        aString775 = "";
        aClass38_Sub2_Sub2_Sub2Array776 = new Sprite[20];
        anInt778 = 332;
        aString784 = "";
        anInt786 = -1;
        aBoolean787 = true;
        aBoolean788 = false;
        anIntArray789 = new int[7];
        aByte790 = 8;
        aClass38_Sub2_Sub2_Sub2Array791 = new Sprite[1000];
        anInt792 = 78;
        inBuffer = Buffer.reserve(1);
        outBuffer = Buffer.reserve(1);
        aBoolean799 = false;
        anInt803 = 3;
        anInt805 = -655;
        anIntArray807 = new int[50];
        interfaceComponent = new InterfaceComponent();
        anIntArray809 = new int[50];
        anIntArray811 = new int[9];
        aBoolean812 = true;
        anInt813 = 4277;
        indexedSpritesArray2 = new IndexedSprite[13];
        anInt816 = 128;
        MAX_PLAYER_COUNT = 2048;
        LOCAL_PLAYER_INDEX = 2047;
        playerEntities = new PlayerEntity[MAX_PLAYER_COUNT];
        playerIndices = new int[MAX_PLAYER_COUNT];
        entityUpdateIndices = new int[MAX_PLAYER_COUNT];
        playerBuffers = new Buffer[MAX_PLAYER_COUNT];
        projectiles = new LinkedList();
        options = new String[500];
        aBoolean835 = true;
        characterDesignIsMale = true;
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
        spawnedLocations = new LinkedList();
        anIntArray896 = new int[100];
        aStringArray897 = new String[100];
        messageText = new String[100];
        flameActive = false;
        anInt907 = -1;
        aBoolean912 = false;
        anIntArray918 = new int[1000];
        anIntArray919 = new int[1000];
        anIntArrayArray920 = new int[104][104];
        aBoolean921 = false;
        aBoolean923 = false;
        anIntArray926 = new int[50];
        npcEntities = new NpcEntity[8192];
        npcIndices = new int[8192];
        anInt931 = 1;
        aByte935 = -46;
        anIntArray938 = new int[2000];
        deadEntityIndices = new int[1000];
        friendName37 = new long[100];
        anIntArray953 = new int[151];
        collisionMaps = new CollisionMap[4];
        aClass38_Sub2_Sub2_Sub2Array956 = new Sprite[20];
        anIntArray959 = new int[5];
        aBoolean960 = false;
        aBoolean964 = false;
        aBoolean965 = false;
        anIntArray966 = new int[5];
        aBoolean968 = false;
        aString970 = "";
        anInt971 = -1;
        aBoolean974 = false;
        flamesThread = false;
        anInt980 = 0x332d25;
        aBoolean990 = false;
        anInt993 = 0x766654;
        waypointX = new int[4000];
        waypointY = new int[4000];
        aCRC32_996 = new CRC32();
        anInt1001 = -1;
        anInt1019 = -1;
        anInt1020 = -1;
        anInt1021 = -1;
        rights = false;
        anIntArray1024 = new int[5];
        indexedSpritesArray1 = new IndexedSprite[50];
        anInt1039 = 27808;
        anInt1050 = 0x23201b;
        aBoolean1055 = false;
        spotanims = new LinkedList();
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
        loginMessage0 = "";
        loginMessage1 = "";
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
        pathWaypoint = new int[104][104];
        aClass38_Sub2_Sub2_Sub2Array1120 = new Sprite[8];
        aBoolean1121 = false;
        aByte1122 = 94;
        anObject1123 = new Object();
        anIntArray1124 = new int[50];
        anInt1126 = 2;
        friendName = new String[100];
        anInt1128 = -1;
        anInt1129 = -1;
        anInt1131 = 2;
        anInt1132 = 29286;
        anIntArray1133 = new int[151];
        anInt1135 = 1;
        aString1137 = "";
        aClass38_Sub2_Sub2_Sub2Array1138 = new Sprite[50];
        paramA = new int[500];
        paramB = new int[500];
        actions = new int[500];
        paramC = new int[500];
        anInt1143 = 701;
        aBoolean1144 = false;
        aBoolean1147 = true;
        aBoolean1153 = true;
        objects = new LinkedList[4][104][104];
        anInt1158 = 0x4d4233;
        anIntArray1159 = new int[5];
        anInt1160 = -676;
    }

    public static int anInt724;
    public static String aString725 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
    public int anInt726;
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
    public int[] characterDesignColors;
    public Buffer loginBuffer;
    public int anInt744;
    public int anInt745;
    public int anInt746;
    public int anInt747;
    public int anInt748;
    public int anInt749;
    public int anInt750;
    public boolean redrawTitleBackground;
    public LinkedList linkedList2;
    public IsaacRandom isaacState;
    public boolean[] aBooleanArray754;
    public int anInt755;
    public int anInt756;
    public int selectedTab;
    public int[][] pathDistance;
    public int anInt759;
    public int anInt760;
    public int baseTileX;
    public int baseTileZ;
    public int anInt763;
    public int anInt764;
    public String aString765;
    public byte aByte766;
    public LinkedList temporaryLocs;
    public long[] ignoreName37;
    public int anInt769;
    public byte[][] aByteArrayArray770;
    public int anInt771;
    public static int anInt772;
    public int[] friendWorld;
    public int lastSceneLevel;
    public String aString775;
    public Sprite[] aClass38_Sub2_Sub2_Sub2Array776;
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
    public Sprite[] aClass38_Sub2_Sub2_Sub2Array791;
    public int anInt792;
    public int ignoreCount;
    public int[][][] anIntArrayArrayArray794;
    public Buffer inBuffer;
    public int anInt796;
    public static int anInt797;
    public Buffer outBuffer;
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
    public IndexedSprite[] indexedSpritesArray2;
    public int anInt815;
    public int anInt816;
    public int cameraYaw;
    public int anInt818;
    public int anInt819;
    public int MAX_PLAYER_COUNT;
    public int LOCAL_PLAYER_INDEX;
    public PlayerEntity[] playerEntities;
    public int entityCount;
    public int[] playerIndices;
    public int updateCount;
    public int[] entityUpdateIndices;
    public Buffer[] playerBuffers;
    public int anInt828;
    public int anInt829;
    public int anInt830;
    public Scene scene;
    public LinkedList projectiles;
    public int anInt833;
    public String[] options;
    public boolean aBoolean835;
    public boolean characterDesignIsMale;
    public int anInt837;
    public int anInt838;
    public int anInt839;
    public byte[][][] aByteArrayArrayArray840;
    public int[] flameBuffer0;
    public int[] flameBuffer1;
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
    public boolean aBoolean872;
    public int anInt873;
    public int anInt874;
    public int anInt875;
    public static int anInt876;
    public int[] anIntArray878;
    public boolean aBoolean879;
    public int currentLevel;
    public boolean aBoolean881;
    public static int anInt882;
    public int anInt883;
    public LinkedList spawnedLocations;
    public int anInt885;
    public static int nodeid = 10;
    public static int portoff;
    public static boolean members = true;
    public static boolean lowMemory;
    public static int anInt890;
    public IndexedSprite indexedSprite10;
    public IndexedSprite indexedSprite11;
    public IndexedSprite indexedSprite12;
    public IndexedSprite indexedSprite13;
    public IndexedSprite indexedSprite14;
    public int[] anIntArray896;
    public String[] aStringArray897;
    public String[] messageText;
    public static int anInt899 = 629;
    public long aLong900;
    public int anInt901;
    public boolean flameActive;
    public int[] flameGradient;
    public int[] flameGradient0;
    public int[] flameGradient1;
    public int[] flameGradient2;
    public int anInt907;
    public IndexedSprite indexedSprite15;
    public IndexedSprite indexedSprite16;
    public IndexedSprite indexedSprite17;
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
    public static BigInteger exponent = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");
    public boolean aBoolean923;
    public int anInt924;
    public int[] anIntArray925;
    public int[] anIntArray926;
    public NpcEntity[] npcEntities;
    public int npcCount;
    public int[] npcIndices;
    public int minimapZoom;
    public int anInt931;
    public int anInt932;
    public int anInt933;
    public int anInt934;
    public byte aByte935;
    public String aString936;
    public static int anInt937;
    public int[] anIntArray938;
    public int deadEntityCount;
    public int[] deadEntityIndices;
    public int anInt941;
    public static final int[][] APPEARANCE_COLORS = {
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
    public long[] friendName37;
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
    public CollisionMap[] collisionMaps;
    public static int clientClock;
    public Sprite[] aClass38_Sub2_Sub2_Sub2Array956;
    public int anInt957;
    public static int anInt958 = 3;
    public int[] anIntArray959;
    public boolean aBoolean960;
    public Sprite aClass38_Sub2_Sub2_Sub2_961;
    public Sprite aClass38_Sub2_Sub2_Sub2_962;
    public int anInt963;
    public boolean aBoolean964;
    public boolean aBoolean965;
    public int[] anIntArray966;
    public PlayerEntity self;
    public boolean aBoolean968;
    public int anInt969;
    public String aString970;
    public int anInt971;
    public int anInt972;
    public IndexedSprite[] imageRunes;
    public boolean aBoolean974;
    public boolean flamesThread;
    public int anInt976;
    public int anInt977;
    public Sprite imageFlamesLeft;
    public Sprite imageFlamesRight;
    public int anInt980;
    public IndexedSprite indexedSprite18;
    public IndexedSprite indexedSprite19;
    public IndexedSprite indexedSprite20;
    public int inMultizone;
    public IndexedFont indexedFont1;
    public IndexedFont indexedFont2;
    public IndexedFont fontBold12;
    public IndexedFont indexedFont4;
    public int anInt989;
    public boolean aBoolean990;
    public int[] flameBuffer3;
    public int[] flameBuffer2;
    public int anInt993;
    public int[] waypointX;
    public int[] waypointY;
    public CRC32 aCRC32_996;
    public Sprite aClass38_Sub2_Sub2_Sub2_997;
    public static int anInt998;
    public BufferedStream bufferedStream;
    public byte[][] aByteArrayArray1000;
    public int anInt1001;
    public int selectedObject;
    public int anInt1003;
    public int anInt1004;
    public int anInt1005;
    public String selectedObjName;
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
    public boolean rights;
    public int[] anIntArray1024;
    public int selectedSpell;
    public int anInt1026;
    public int selectedFlags;
    public String selectedSpellPrefix;
    public DrawArea imageTitle2;
    public DrawArea imageTitle3;
    public DrawArea imageTitle4;
    public DrawArea imageTitle0;
    public DrawArea imageTitle1;
    public DrawArea imageTitle5;
    public DrawArea imageTitle6;
    public DrawArea imageTitle7;
    public DrawArea imageTitle8;
    public IndexedSprite[] indexedSpritesArray1;
    public int anInt1039;
    public IndexedSprite indexedSprite1;
    public IndexedSprite indexedSprite2;
    public IndexedSprite indexedSprite3;
    public IndexedSprite indexedSprite4;
    public IndexedSprite indexedSprite5;
    public int[] anIntArray1045 = {
        0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff
    };
    public DrawArea drawArea20;
    public DrawArea drawArea21;
    public DrawArea drawArea22;
    public DrawArea drawArea23;
    public int anInt1050;
    public int flagTileX;
    public int flagTileY;
    public Sprite aClass38_Sub2_Sub2_Sub2_1053;
    public int anInt1054;
    public boolean aBoolean1055;
    public LinkedList spotanims;
    public Sprite aClass38_Sub2_Sub2_Sub2_1057;
    public Sprite aClass38_Sub2_Sub2_Sub2_1058;
    public Sprite aClass38_Sub2_Sub2_Sub2_1059;
    public Sprite aClass38_Sub2_Sub2_Sub2_1060;
    public int anInt1061;
    public static BigInteger modulus = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
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
    public static final int[] BEARD_COLORS = {
        9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145,
        58654, 5027, 1457, 16565, 34991, 25486
    };
    public int optionCount;
    public int[] anIntArray1075;
    public int anInt1076;
    public int anInt1078;
    public int[] anIntArray1079;
    public boolean aBoolean1080;
    public IndexedSprite indexedSprite6;
    public IndexedSprite indexedSprite7;
    public String loginMessage0;
    public String loginMessage1;
    public int minimapAnticheatAngle;
    public int anInt1086;
    public int anInt1087;
    public int anInt1088;
    public int friendCount;
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
    public int wildernessLevel;
    public static boolean aBoolean1102;
    public IndexedSprite indexedSprite8;
    public IndexedSprite indexedSprite9;
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
    public int[][] pathWaypoint;
    public String aString1119;
    public Sprite[] aClass38_Sub2_Sub2_Sub2Array1120;
    public boolean aBoolean1121;
    public byte aByte1122;
    public Object anObject1123;
    public int[] anIntArray1124;
    public int cameraAnticheatOffsetX;
    public int anInt1126;
    public String[] friendName;
    public int anInt1128;
    public int anInt1129;
    public int cameraAnticheatOffsetZ;
    public int anInt1131;
    public int anInt1132;
    public int[] anIntArray1133;
    public int cameraAnticheatAngle;
    public int anInt1135;
    public FileArchive fileArchive;
    public String aString1137;
    public Sprite[] aClass38_Sub2_Sub2_Sub2Array1138;
    public int[] paramA;
    public int[] paramB;
    public int[] actions;
    public int[] paramC;
    public int anInt1143;
    public boolean aBoolean1144;
    public Sprite aClass38_Sub2_Sub2_Sub2_1145;
    public long serverSeed;
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
    public LinkedList[][][] objects;
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
