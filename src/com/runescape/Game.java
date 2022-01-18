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

    public void setMidi(int i, String s, int j) {
        if (s == null) {
            return;
        }

        synchronized (midiSync) {
            midiString = s;
            midiInt1 = i;
            midiInt2 = j;
        }
    }

    public void drawViewport2d() {
        chatCount = 0;
        for (int i = -1; i < playerCount + npcCount; i++) {
            PathingEntity e;

            if (i == -1) {
                e = self;
            } else if (i < playerCount) {
                e = playerEntities[playerIndices[i]];
            } else {
                e = npcEntities[npcIndices[i - playerCount]];
            }

            if (e == null || !e.isValid(false)) {
                continue;
            }

            if (i < playerCount) {
                int l = 30;

                PlayerEntity playerEntity = (PlayerEntity) e;
                if (playerEntity.headicons != 0) {
                    setDrawPos(e.height + 15, e);
                    if (drawX > -1) {
                        for (int l1 = 0; l1 < 8; l1++) {
                            if ((playerEntity.headicons & 1 << l1) != 0) {
                                headicons[l1].draw(drawY - l, drawX - 12);
                                l -= 25;
                            }
                        }
                    }
                }

                if (i >= 0 && hintType == 10 && anInt1076 == playerIndices[i]) {
                    setDrawPos(e.height + 15, e);
                    if (drawX > -1) {
                        headicons[7].draw(drawY - l, drawX - 12);
                    }
                }
            } else if (hintType == 1 && anInt801 == npcIndices[i - playerCount] && clientClock % 20 < 10) {
                setDrawPos(e.height + 15, e);
                if (drawX > -1) {
                    headicons[2].draw(drawY - 28, drawX - 12);
                }
            }

            if (e.spoken != null && (i >= playerCount || chatPublicSetting == 0 || chatPublicSetting == 3 || chatPublicSetting == 1 && isFriend(((PlayerEntity) e).name))) {
                setDrawPos(e.height, e);

                if (drawX > -1 && chatCount < anInt1092) {
                    chatPadding[chatCount] = fontBold12.stringWidth(e.spoken) / 2;
                    chatHeight[chatCount] = fontBold12.height;
                    chatScreenX[chatCount] = drawX;
                    chatScreenY[chatCount] = drawY;
                    chatColors[chatCount] = e.spokenColor;
                    chatStyles[chatCount] = e.spokenEffect;
                    chatTimers[chatCount] = e.textCycle;
                    chatMessages[chatCount++] = e.spoken;

                    if (chatEffects == 0 && e.spokenEffect == 1) {
                        chatHeight[chatCount] += 10;
                        chatScreenY[chatCount] += 5;
                    }

                    if (chatEffects == 0 && e.spokenEffect == 2) {
                        chatPadding[chatCount] = 60;
                    }
                }
            }

            if (e.cycleStatus > clientClock + 100) {
                setDrawPos(e.height + 15, e);
                if (drawX > -1) {
                    int i1 = (e.currentHealth * 30) / e.maxHealth;
                    if (i1 > 30) {
                        i1 = 30;
                    }
                    Draw2D.fillRect(drawY - 3, drawX - 15, 65280, i1, 5);
                    Draw2D.fillRect(drawY - 3, (drawX - 15) + i1, 0xff0000, 30 - i1, 5);
                }
            }

            if (e.cycleStatus > clientClock + 330) {
                setDrawPos(e.height / 2, e);
                if (drawX > -1) {
                    hitmarks[e.damageType].draw(drawY - 12, drawX - 12);
                    fontPlain11.drawRightAligned(drawY + 4, 0, String.valueOf(e.damageTaken), drawX);
                    fontPlain11.drawRightAligned(drawY + 3, 0xffffff, String.valueOf(e.damageTaken), drawX - 1);
                }
            }
        }

        for (int k = 0; k < chatCount; k++) {
            int j1 = chatScreenX[k];
            int k1 = chatScreenY[k];
            int i2 = chatPadding[k];
            int j2 = chatHeight[k];
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int k2 = 0; k2 < k; k2++)
                    if (k1 + 2 > chatScreenY[k2] - chatHeight[k2] && k1 - j2 < chatScreenY[k2] + 2
                        && j1 - i2 < chatScreenX[k2] + chatPadding[k2]
                        && j1 + i2 > chatScreenX[k2] - chatPadding[k2]
                        && chatScreenY[k2] - chatHeight[k2] < k1) {
                        k1 = chatScreenY[k2] - chatHeight[k2];
                        flag = true;
                    }

            }
            drawX = chatScreenX[k];
            drawY = chatScreenY[k] = k1;
            String s = chatMessages[k];
            if (chatEffects == 0) {
                int l2 = 0xffff00;
                if (chatColors[k] < 6)
                    l2 = anIntArray1045[chatColors[k]];
                if (chatColors[k] == 6)
                    l2 = anInt837 % 20 >= 10 ? 0xffff00 : 0xff0000;
                if (chatColors[k] == 7)
                    l2 = anInt837 % 20 >= 10 ? 65535 : 255;
                if (chatColors[k] == 8)
                    l2 = anInt837 % 20 >= 10 ? 0x80ff80 : 45056;
                if (chatColors[k] == 9) {
                    int i3 = 150 - chatTimers[k];
                    if (i3 < 50)
                        l2 = 0xff0000 + 1280 * i3;
                    else if (i3 < 100)
                        l2 = 0xffff00 - 0x50000 * (i3 - 50);
                    else if (i3 < 150)
                        l2 = 65280 + 5 * (i3 - 100);
                }
                if (chatColors[k] == 10) {
                    int j3 = 150 - chatTimers[k];
                    if (j3 < 50)
                        l2 = 0xff0000 + 5 * j3;
                    else if (j3 < 100)
                        l2 = 0xff00ff - 0x50000 * (j3 - 50);
                    else if (j3 < 150)
                        l2 = (255 + 0x50000 * (j3 - 100)) - 5 * (j3 - 100);
                }
                if (chatColors[k] == 11) {
                    int k3 = 150 - chatTimers[k];
                    if (k3 < 50)
                        l2 = 0xffffff - 0x50005 * k3;
                    else if (k3 < 100)
                        l2 = 65280 + 0x50005 * (k3 - 50);
                    else if (k3 < 150)
                        l2 = 0xffffff - 0x50000 * (k3 - 100);
                }
                if (chatStyles[k] == 0) {
                    fontBold12.drawRightAligned(drawY + 1, 0, s, drawX);
                    fontBold12.drawRightAligned(drawY, l2, s, drawX);
                }
                if (chatStyles[k] == 1) {
                    fontBold12.drawCenteredWave(anInt837, drawX, drawY + 1, 0, s);
                    fontBold12.drawCenteredWave(anInt837, drawX, drawY, l2, s);
                }
                if (chatStyles[k] == 2) {
                    int l3 = fontBold12.stringWidth(s);
                    int i4 = ((150 - chatTimers[k]) * (l3 + 100)) / 150;
                    Draw2D.setBounds(334, 0, drawX + 50, drawX - 50);
                    fontBold12.draw((drawX + 50) - i4, drawY + 1, 0, s);
                    fontBold12.draw((drawX + 50) - i4, drawY, l2, s);
                    Draw2D.resetBounds();
                }
            } else {
                fontBold12.drawRightAligned(drawY + 1, 0, s, drawX);
                fontBold12.drawRightAligned(drawY, 0xffff00, s, drawX);
            }
        }

    }

    public void closeInterface() {
        outBuffer.writeOpcode(231);
        if (sidebarInterfaceId != -1) {
            sidebarInterfaceId = -1;
            sidebarRedraw = true;
            chatContinuingDialogue = false;
            sidebarRedrawIcons = true;
        }
        if (chatbackComponentId != -1) {
            chatbackComponentId = -1;
            redrawChatback = true;
            chatContinuingDialogue = false;
        }
        viewportInterfaceIndex = -1;
    }

    public void midistop() {
        Signlink.midifade = 0;
        Signlink.midi = "stop";
    }

    public void drawWildyLevel() {
        int j = (self.x >> 7) + baseTileX;
        int k = (self.z >> 7) + baseTileZ;
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

    public void drawChat() {
        if (anInt833 == 0)
            return;
        IndexedFont font = fontPlain12;
        int j = 0;
        if (systemUpdateTimer != 0)
            j = 1;
        for (int k = 0; k < 100; k++)
            if (chatMessage[k] != null) {
                int l = chatMessageType[k];
                if ((l == 3 || l == 7)
                    && (l == 7 || chatPrivateSetting == 0 || chatPrivateSetting == 1 && isFriend(chatMessagePrefix[k]))) {
                    int i1 = 329 - j * 13;
                    font.draw(4, i1, 0,
                        "From " + chatMessagePrefix[k] + ": " + chatMessage[k]);
                    font.draw(4, i1 - 1, 65535,
                        "From " + chatMessagePrefix[k] + ": " + chatMessage[k]);
                    if (++j >= 5)
                        return;
                }
                if (l == 5 && chatPrivateSetting < 2) {
                    int j1 = 329 - j * 13;
                    font.draw(4, j1, 0, chatMessage[k]);
                    font.draw(4, j1 - 1, 65535, chatMessage[k]);
                    if (++j >= 5)
                        return;
                }
                if (l == 6 && chatPrivateSetting < 2) {
                    int k1 = 329 - j * 13;
                    font.draw(4, k1, 0,
                        "To " + chatMessagePrefix[k] + ": " + chatMessage[k]);
                    font.draw(4, k1 - 1, 65535,
                        "To " + chatMessagePrefix[k] + ": " + chatMessage[k]);
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

    public void addIgnore(long l) {
        if (l == 0L)
            return;
        if (ignoreCount >= 100) {
            addMessage(0, "Your ignore list is full. Max of 100 hit", "");
            return;
        }
        String s = StringUtils.formatName(StringUtils.fromBase37(l));
        for (int i = 0; i < ignoreCount; i++)
            if (ignoreName37[i] == l) {
                addMessage(0, s + " is already on your ignore list", "");
                return;
            }

        for (int j = 0; j < friendCount; j++)
            if (friendName37[j] == l) {
                addMessage(0, "Please remove " + s + " from your friend list first", "");
                return;
            }

        ignoreName37[ignoreCount++] = l;
        sidebarRedraw = true;
        outBuffer.writeOpcode(79);
        outBuffer.writeQWord(l);
    }

    public void readSecondaryPacket(Buffer b, int opcode) {
        if (opcode == Packet.ADD_LOCATION || opcode == Packet.REMOVE_LOCATION) {
            int j = b.readByte();
            int k2 = anInt862 + (j >> 4 & 7);
            int l4 = anInt863 + (j & 7);
            int i7 = b.readByte();
            int j9 = i7 >> 2;
            int j11 = i7 & 3;
            int i13 = anIntArray1107[j9];
            int i14;
            if (opcode == 76)
                i14 = -1;
            else
                i14 = b.readWord();
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
                addLoc(j11, k2, l4, i13, i14, j9, currentLevel);
            }
            return;
        }
        if (opcode == Packet.ADD_ANIMATED_LOCATION) {
            int k = b.readByte();
            int l2 = anInt862 + (k >> 4 & 7);
            int i5 = anInt863 + (k & 7);
            int j7 = b.readByte();
            int k9 = j7 >> 2;
            int k11 = anIntArray1107[k9];
            int j13 = b.readWord();
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
                    list.pushNext(locEntity);
                }
            }
            return;
        }
        if (opcode == Packet.ADD_OBJECT_STACK) {
            int l = b.readByte();
            int i3 = anInt862 + (l >> 4 & 7);
            int j5 = anInt863 + (l & 7);
            int k7 = b.readWord();
            int l9 = b.readWord();
            if (i3 >= 0 && j5 >= 0 && i3 < 104 && j5 < 104) {
                ObjStackEntity objStackEntity = new ObjStackEntity();
                objStackEntity.model = k7;
                objStackEntity.amount = l9;
                if (objects[currentLevel][i3][j5] == null)
                    objects[currentLevel][i3][j5] = new LinkedList();
                objects[currentLevel][i3][j5].pushNext(objStackEntity);
                updateObjectStack(i3, j5);
            }
            return;
        }
        if (opcode == Packet.REMOVE_OBJECT_STACK) {
            int i1 = b.readByte();
            int j3 = anInt862 + (i1 >> 4 & 7);
            int k5 = anInt863 + (i1 & 7);
            int l7 = b.readWord();
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
                    updateObjectStack(j3, k5);
                }
            }
            return;
        }
        if (opcode == Packet.ADD_PROJECTILE) {
            int j1 = b.readByte();
            int k3 = anInt862 + (j1 >> 4 & 7);
            int l5 = anInt863 + (j1 & 7);
            int i8 = k3 + b.readByteSigned();
            int i10 = l5 + b.readByteSigned();
            int l11 = b.readWordSigned();
            int k13 = b.readWord();
            int k14 = b.readByte();
            int i15 = b.readByte();
            int k15 = b.readWord();
            int j16 = b.readWord();
            int i17 = b.readByte();
            int k17 = b.readByte();
            if (k3 >= 0 && l5 >= 0 && k3 < 104 && l5 < 104 && i8 >= 0 && i10 >= 0 && i8 < 104 && i10 < 104) {
                k3 = k3 * 128 + 64;
                l5 = l5 * 128 + 64;
                i8 = i8 * 128 + 64;
                i10 = i10 * 128 + 64;
                ProjectileEntity projectileEntity = new ProjectileEntity(i15, i17, l5, j16 + clientClock, currentLevel, l11,
                    k15 + clientClock, k17, getLandY(currentLevel, k3, l5) - k14, k13, k3);
                projectileEntity.setTarget(getLandY(currentLevel, i8, i10) - i15, i10, i8, k15 + clientClock);
                projectiles.pushNext(projectileEntity);
            }
            return;
        }
        if (opcode == Packet.ADD_SPOT_ANIMATION) {
            int k1 = b.readByte();
            int l3 = anInt862 + (k1 >> 4 & 7);
            int i6 = anInt863 + (k1 & 7);
            int j8 = b.readWord();
            int j10 = b.readByte();
            int i12 = b.readWord();
            if (l3 >= 0 && i6 >= 0 && l3 < 104 && i6 < 104) {
                l3 = l3 * 128 + 64;
                i6 = i6 * 128 + 64;
                SpotAnimEntity spotAnimEntity = new SpotAnimEntity(l3, j8, i6, i12,
                    getLandY(currentLevel, l3, i6) - j10, currentLevel, clientClock);
                spotanims.pushNext(spotAnimEntity);
            }
            return;
        }
        if (opcode == Packet.ADD_PRIVATE_OBJECT_STACK) {
            int l1 = b.readByte();
            int i4 = anInt862 + (l1 >> 4 & 7);
            int j6 = anInt863 + (l1 & 7);
            int k8 = b.readWord();
            int k10 = b.readWord();
            int j12 = b.readWord();
            if (i4 >= 0 && j6 >= 0 && i4 < 104 && j6 < 104 && j12 != anInt734) {
                ObjStackEntity objStackEntity_2 = new ObjStackEntity();
                objStackEntity_2.model = k8;
                objStackEntity_2.amount = k10;
                if (objects[currentLevel][i4][j6] == null)
                    objects[currentLevel][i4][j6] = new LinkedList();
                objects[currentLevel][i4][j6].pushNext(objStackEntity_2);
                updateObjectStack(i4, j6);
            }
            return;
        }
        if (opcode == Packet.ATTACH_TEMPORARY_LOCATION_TO_PLAYER) {
            int i2 = b.readByte();
            int j4 = anInt862 + (i2 >> 4 & 7);
            int k6 = anInt863 + (i2 & 7);
            int l8 = b.readByte();
            int l10 = l8 >> 2;
            int k12 = l8 & 3;
            int l13 = anIntArray1107[l10];
            int l14 = b.readWord();
            int j15 = b.readWord();
            int l15 = b.readWord();
            int k16 = b.readWord();
            byte byte1 = b.readByteSigned();
            byte byte2 = b.readByteSigned();
            byte byte3 = b.readByteSigned();
            byte byte4 = b.readByteSigned();
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
                int j18 = levelHeightMaps[currentLevel][j4][k6];
                int k18 = levelHeightMaps[currentLevel][j4 + 1][k6];
                int l18 = levelHeightMaps[currentLevel][j4 + 1][k6 + 1];
                int i19 = levelHeightMaps[currentLevel][j4][k6 + 1];
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
                playerEntity.locSceneY = getLandY(currentLevel, playerEntity.locSceneX,
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
        if (opcode == Packet.UPDATE_OBJECT_STACK) {
            int j2 = b.readByte();
            int k4 = anInt862 + (j2 >> 4 & 7);
            int l6 = anInt863 + (j2 & 7);
            int i9 = b.readWord();
            int i11 = b.readWord();
            int l12 = b.readWord();
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

                    updateObjectStack(k4, l6);
                }
            }
        }
    }

    public int getTopLevel() {
        int i = 3;
        if (cameraPitch < 310) {
            int j = cameraX >> 7;
            int k = cameraZ >> 7;
            int l = self.x >> 7;
            int i1 = self.z >> 7;
            if ((levelRenderFlags[currentLevel][j][k] & 4) != 0)
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
                    if ((levelRenderFlags[currentLevel][j][k] & 4) != 0)
                        i = currentLevel;
                    j2 += l1;
                    if (j2 >= 0x10000) {
                        j2 -= 0x10000;
                        if (k < i1)
                            k++;
                        else if (k > i1)
                            k--;
                        if ((levelRenderFlags[currentLevel][j][k] & 4) != 0)
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
                    if ((levelRenderFlags[currentLevel][j][k] & 4) != 0)
                        i = currentLevel;
                    k2 += i2;
                    if (k2 >= 0x10000) {
                        k2 -= 0x10000;
                        if (j < l)
                            j++;
                        else if (j > l)
                            j--;
                        if ((levelRenderFlags[currentLevel][j][k] & 4) != 0)
                            i = currentLevel;
                    }
                }
            }
        }
        if ((levelRenderFlags[currentLevel][self.x >> 7][self.z >> 7]
            & 4) != 0)
            i = currentLevel;
        return i;
    }

    public int getCameraPlaneCutscene() {
        int j = getLandY(currentLevel, cameraX, cameraZ);
        if (j - cameraY < 800 && (levelRenderFlags[currentLevel][cameraX >> 7][cameraZ >> 7] & 4) != 0)
            return currentLevel;
        else
            return 3;
    }

    public void drawViewport() {
        anInt837++;
        updateScenePlayers();
        updateSceneNpcs();
        updateSceneProjectiles();
        updateSceneSpotAnims();
        updateSceneSeqLocs();
        if (!cutsceneActive) {
            int j = cameraOrbitPitch;
            if (cameraMaxY / 256 > j)
                j = cameraMaxY / 256;
            if (customCameraActive[4] && cameraAmplitude[4] + 128 > j)
                j = cameraAmplitude[4] + 128;
            int l = cameraYaw + cameraAnticheatAngle & 0x7ff;
            updateCameraOrbit(
                getLandY(currentLevel, self.x,
                    self.z) - 50,
                cameraOrbitX, l, j, cameraOrbitZ, 600 + j * 3);
            drawViewportCounter++;
            if (drawViewportCounter > 1802) {
                drawViewportCounter = 0;
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
        if (!cutsceneActive)
            k = getTopLevel();
        else
            k = getCameraPlaneCutscene();
        int i1 = cameraX;
        int k1 = cameraY;
        int l1 = cameraZ;
        int i2 = cameraPitch;
        int j2 = cameraOrbitYaw;
        for (int k2 = 0; k2 < 5; k2++)
            if (customCameraActive[k2]) {
                int l2 = (int) ((Math.random() * (double) (cameraJitter[k2] * 2 + 1) - (double) cameraJitter[k2])
                    + Math.sin((double) unknownCameraVariable[k2] * ((double) cameraFrequency[k2] / 100D))
                    * (double) cameraAmplitude[k2]);
                if (k2 == 0)
                    cameraX += l2;
                if (k2 == 1)
                    cameraY += l2;
                if (k2 == 2)
                    cameraZ += l2;
                if (k2 == 3)
                    cameraOrbitYaw = cameraOrbitYaw + l2 & 0x7ff;
                if (k2 == 4) {
                    cameraPitch += l2;
                    if (cameraPitch < 128)
                        cameraPitch = 128;
                    if (cameraPitch > 383)
                        cameraPitch = 383;
                }
            }

        int i3 = Draw3D.cycle;
        Model.aBoolean1295 = true;
        Model.resourceCount = 0;
        Model.cursorX = super.mouseX - 8;
        Model.cursorY = super.mouseY - 11;
        Draw2D.clear();
        scene.draw(cameraOrbitYaw, cameraX, k, cameraPitch, cameraY, cameraZ);
        scene.clearFrameLocs();
        drawViewport2d();
        drawTileHint();
        updateAnimatedTextures(i3);
        drawViewport2();
        areaViewport.drawImage(11, super.graphics, 8);
        cameraX = i1;
        cameraY = k1;
        cameraZ = l1;
        cameraPitch = i2;
        cameraOrbitYaw = j2;
    }

    public void loadMidi() {
        aBoolean799 = false;
        while (aBoolean812) {
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
            String s;
            int i;
            int j;
            synchronized (midiSync) {
                s = midiString;
                i = midiInt1;
                j = midiInt2;
                midiString = null;
                midiInt1 = 0;
                midiInt2 = 0;
            }
            if (s != null) {
                byte[] abyte0 = Signlink.cacheload(s + ".mid");
                if (abyte0 != null && i != 0xbc614e) {
                    crc32.reset();
                    crc32.update(abyte0);
                    int k = (int) crc32.getValue();
                    if (k != i)
                        abyte0 = null;
                }
                if (abyte0 == null)
                    try {
                        DataInputStream datainputstream = openStream(s + "_" + i + ".mid");
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
                midisave(abyte1, l, true);
            }
        }
    }

    public static void setLowMemory() {
        Scene.lowMemory = true;
        Draw3D.lowMemory = true;
        lowMemory = true;
        SceneBuilder.lowMemory = true;
    }

    public void drawFlames() {
        char c = '\u0100';
        if (flameCycle1 > 0) {
            for (int i = 0; i < 256; i++)
                if (flameCycle1 > 768)
                    flameGradient[i] = mix(flameGradientRed[i], 1024 - flameCycle1, flameGradientGreen[i]);
                else if (flameCycle1 > 256)
                    flameGradient[i] = flameGradientGreen[i];
                else
                    flameGradient[i] = mix(flameGradientGreen[i], 256 - flameCycle1, flameGradientRed[i]);

        } else if (flameCycle2 > 0) {
            for (int j = 0; j < 256; j++)
                if (flameCycle2 > 768)
                    flameGradient[j] = mix(flameGradientRed[j], 1024 - flameCycle2, flameGradientViolet[j]);
                else if (flameCycle2 > 256)
                    flameGradient[j] = flameGradientViolet[j];
                else
                    flameGradient[j] = mix(flameGradientViolet[j], 256 - flameCycle2, flameGradientRed[j]);

        } else {
            for (int k = 0; k < 256; k++)
                flameGradient[k] = flameGradientRed[k];

        }
        for (int l = 0; l < 33920; l++)
            titleLeft.pixels[l] = imageFlamesLeft.pixels[l];

        int i1 = 0;
        int j1 = 1152;
        for (int k1 = 1; k1 < c - 1; k1++) {
            int l1 = (flameShiftX[k1] * (c - k1)) / c;
            int j2 = 22 + l1;
            if (j2 < 0)
                j2 = 0;
            i1 += j2;
            for (int l2 = j2; l2 < 128; l2++) {
                int j3 = flameIntensity[i1++];
                if (j3 != 0) {
                    int l3 = j3;
                    int j4 = 256 - j3;
                    j3 = flameGradient[j3];
                    int l4 = titleLeft.pixels[j1];
                    titleLeft.pixels[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00)
                        + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            j1 += j2;
        }

        titleLeft.drawImage(0, super.graphics, 0);
        for (int i2 = 0; i2 < 33920; i2++)
            titleRight.pixels[i2] = imageFlamesRight.pixels[i2];

        i1 = 0;
        j1 = 1176;
        for (int k2 = 1; k2 < c - 1; k2++) {
            int i3 = (flameShiftX[k2] * (c - k2)) / c;
            int k3 = 103 - i3;
            j1 += i3;
            for (int i4 = 0; i4 < k3; i4++) {
                int k4 = flameIntensity[i1++];
                if (k4 != 0) {
                    int i5 = k4;
                    int j5 = 256 - k4;
                    k4 = flameGradient[k4];
                    int k5 = titleRight.pixels[j1];
                    titleRight.pixels[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00)
                        + ((k4 & 0xff00) * i5 + (k5 & 0xff00) * j5 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            i1 += 128 - k3;
            j1 += 128 - k3 - i3;
        }

        titleRight.drawImage(0, super.graphics, 661);
    }

    public void updateInterface(int i, int j, int k, InterfaceComponent interfaceComponent, int i1, int j1) {
        if (interfaceComponent.type != 0 || interfaceComponent.children == null || interfaceComponent.hidden)
            return;
        if (j < i1 || i < k || j > i1 + interfaceComponent.width || i > k + interfaceComponent.height)
            return;
        int k1 = interfaceComponent.children.length;
        for (int l1 = 0; l1 < k1; l1++) {
            int i2 = interfaceComponent.childX[l1] + i1;
            int j2 = (interfaceComponent.childY[l1] + k) - j1;
            InterfaceComponent inter = InterfaceComponent.instances[interfaceComponent.children[l1]];
            i2 += inter.x;
            j2 += inter.y;
            if ((inter.hoverParentIndex >= 0 || inter.hoverColor != 0) && j >= i2 && i >= j2
                && j < i2 + inter.width && i < j2 + inter.height)
                if (inter.hoverParentIndex >= 0)
                    hoveredInterfaceIndex = inter.hoverParentIndex;
                else
                    hoveredInterfaceIndex = inter.id;
            if (inter.type == 0) {
                updateInterface(i, j, j2, inter, i2, inter.scrollY);
                if (inter.scrollHeight > inter.height)
                    updateInterfaceScrollbar(j, i, inter.scrollHeight, inter.height, true, i2 + inter.width, j2,
                        inter);
            } else {
                if (inter.buttonType == 1 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    boolean flag = false;
                    if (inter.contentType != 0)
                        flag = updateInterfaceTooltip(inter);
                    if (!flag) {
                        options[optionCount] = inter.option;
                        actions[optionCount] = 951;
                        paramB[optionCount] = inter.id;
                        optionCount++;
                    }
                }
                if (inter.buttonType == 2 && selectedSpell == 0 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    String s = inter.optionCircumfix;
                    if (s.indexOf(" ") != -1)
                        s = s.substring(0, s.indexOf(" "));
                    options[optionCount] = s + " @gre@" + inter.optionSuffix;
                    actions[optionCount] = 930;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.buttonType == 3 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    options[optionCount] = "Close";
                    actions[optionCount] = 947;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.buttonType == 4 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    options[optionCount] = inter.option;
                    actions[optionCount] = 465;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.buttonType == 5 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    options[optionCount] = inter.option;
                    actions[optionCount] = 960;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.buttonType == 6 && !chatContinuingDialogue && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    options[optionCount] = inter.option;
                    actions[optionCount] = 44;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.type == 2) {
                    int k2 = 0;
                    for (int l2 = 0; l2 < inter.height; l2++) {
                        for (int i3 = 0; i3 < inter.width; i3++) {
                            int j3 = i2 + i3 * (32 + inter.inventoryMarginX);
                            int k3 = j2 + l2 * (32 + inter.inventoryMarginY);
                            if (k2 < 20) {
                                j3 += inter.inventoryOffsetX[k2];
                                k3 += inter.inventoryOffsetY[k2];
                            }
                            if (j >= j3 && i >= k3 && j < j3 + 32 && i < k3 + 32) {
                                anInt1087 = k2;
                                anInt1088 = inter.id;
                                if (inter.inventoryIndices[k2] > 0) {
                                    ObjType objType = ObjType.get(inter.inventoryIndices[k2] - 1);
                                    if (selectedObject == 1 && inter.inventoryHasOptions) {
                                        if (inter.id != anInt1004 || k2 != anInt1003) {
                                            options[optionCount] = "Use " + selectedObjName + " with @lre@"
                                                + objType.name;
                                            actions[optionCount] = 881;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = k2;
                                            paramB[optionCount] = inter.id;
                                            optionCount++;
                                        }
                                    } else if (selectedSpell == 1 && inter.inventoryHasOptions) {
                                        if ((selectedFlags & 0x10) == 16) {
                                            options[optionCount] = selectedSpellPrefix + " @lre@" + objType.name;
                                            actions[optionCount] = 391;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = k2;
                                            paramB[optionCount] = inter.id;
                                            optionCount++;
                                        }
                                    } else {
                                        if (inter.inventoryHasOptions) {
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
                                                    paramB[optionCount] = inter.id;
                                                    optionCount++;
                                                } else if (l3 == 4) {
                                                    options[optionCount] = "Drop @lre@" + objType.name;
                                                    actions[optionCount] = 347;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = k2;
                                                    paramB[optionCount] = inter.id;
                                                    optionCount++;
                                                }

                                        }
                                        if (inter.inventoryIsUsable) {
                                            options[optionCount] = "Use @lre@" + objType.name;
                                            actions[optionCount] = 188;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = k2;
                                            paramB[optionCount] = inter.id;
                                            optionCount++;
                                        }
                                        if (inter.inventoryHasOptions && objType.options != null) {
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
                                                    paramB[optionCount] = inter.id;
                                                    optionCount++;
                                                }

                                        }
                                        if (inter.inventoryOptions != null) {
                                            for (int j4 = 4; j4 >= 0; j4--)
                                                if (inter.inventoryOptions[j4] != null) {
                                                    options[optionCount] = inter.inventoryOptions[j4]
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
                                                    paramB[optionCount] = inter.id;
                                                    optionCount++;
                                                }

                                        }
                                        options[optionCount] = "Examine @lre@" + objType.name;
                                        actions[optionCount] = 1773;
                                        paramC[optionCount] = objType.index;
                                        paramB[optionCount] = inter.inventoryAmount[k2];
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

    public void updateChatSettingInput() {
        if (super.mouseButton == 1) {
            if (super.clickX >= 8 && super.clickX <= 108 && super.clickY >= 490 && super.clickY <= 522) {
                chatPublicSetting = (chatPublicSetting + 1) % 4;
                chatRedrawSettings = true;
                redrawChatback = true;
                outBuffer.writeOpcode(244);
                outBuffer.writeByte(chatPublicSetting);
                outBuffer.writeByte(chatPrivateSetting);
                outBuffer.writeByte(chatTradeDuelSetting);
            }
            if (super.clickX >= 137 && super.clickX <= 237 && super.clickY >= 490 && super.clickY <= 522) {
                chatPrivateSetting = (chatPrivateSetting + 1) % 3;
                chatRedrawSettings = true;
                redrawChatback = true;
                outBuffer.writeOpcode(244);
                outBuffer.writeByte(chatPublicSetting);
                outBuffer.writeByte(chatPrivateSetting);
                outBuffer.writeByte(chatTradeDuelSetting);
            }
            if (super.clickX >= 275 && super.clickX <= 375 && super.clickY >= 490 && super.clickY <= 522) {
                chatTradeDuelSetting = (chatTradeDuelSetting + 1) % 3;
                chatRedrawSettings = true;
                redrawChatback = true;
                outBuffer.writeOpcode(244);
                outBuffer.writeByte(chatPublicSetting);
                outBuffer.writeByte(chatPrivateSetting);
                outBuffer.writeByte(chatTradeDuelSetting);
            }
            if (super.clickX >= 416 && super.clickX <= 516 && super.clickY >= 490 && super.clickY <= 522) {
                closeInterface();
                aString970 = "";
                aBoolean881 = false;
                for (int j = 0; j < InterfaceComponent.instances.length; j++)
                    if (InterfaceComponent.instances[j] != null && InterfaceComponent.instances[j].contentType == 600) {
                        anInt907 = viewportInterfaceIndex = InterfaceComponent.instances[j].parent;
                        return;
                    }

            }
        }
    }

    public void updatePlayerTooltip(int i) {
        int l = 0;
        for (int i1 = 0; i1 < 100; i1++) {
            if (chatMessage[i1] == null)
                continue;
            int j1 = chatMessageType[i1];
            int k1 = (70 - l * 14) + anInt977 + 4;
            if (k1 < -20)
                break;
            if (j1 == 0)
                l++;
            if ((j1 == 1 || j1 == 2)
                && (j1 == 1 || chatPublicSetting == 0 || chatPublicSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                if (i > k1 - 14 && i <= k1 && !chatMessagePrefix[i1].equals(self.name)) {
                    if (rights) {
                        options[optionCount] = "Report abuse @whi@" + chatMessagePrefix[i1];
                        actions[optionCount] = 34;
                        optionCount++;
                    }
                    options[optionCount] = "Add ignore @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 436;
                    optionCount++;
                    options[optionCount] = "Add friend @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 406;
                    optionCount++;
                }
                l++;
            }
            if ((j1 == 3 || j1 == 7) && anInt833 == 0
                && (j1 == 7 || chatPrivateSetting == 0 || chatPrivateSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    if (rights) {
                        options[optionCount] = "Report abuse @whi@" + chatMessagePrefix[i1];
                        actions[optionCount] = 34;
                        optionCount++;
                    }
                    options[optionCount] = "Add ignore @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 436;
                    optionCount++;
                    options[optionCount] = "Add friend @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 406;
                    optionCount++;
                }
                l++;
            }
            if (j1 == 4 && (chatTradeDuelSetting == 0 || chatTradeDuelSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    options[optionCount] = "Accept trade @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 903;
                    optionCount++;
                }
                l++;
            }
            if ((j1 == 5 || j1 == 6) && anInt833 == 0 && chatPrivateSetting < 2)
                l++;
            if (j1 == 8 && (chatTradeDuelSetting == 0 || chatTradeDuelSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    options[optionCount] = "Accept duel @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 363;
                    optionCount++;
                }
                l++;
            }
        }
    }

    public void updateScenePlayers() {
        if (self.x >> 7 == flagTileX && self.z >> 7 == flagTileY)
            flagTileX = 0;
        for (int j = -1; j < playerCount; j++) {
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
            playerEntity.lowMemory = (lowMemory && playerCount > 50 || playerCount > 200) && j != -1
                && playerEntity.secondarySeq == playerEntity.standSeq;
            int l = playerEntity.x >> 7;
            int i1 = playerEntity.z >> 7;
            if (l < 0 || l >= 104 || i1 < 0 || i1 >= 104)
                continue;
            if (playerEntity.locModel != null && clientClock >= playerEntity.locFirstCycle
                && clientClock < playerEntity.locLastCycle) {
                playerEntity.lowMemory = false;
                playerEntity.y = getLandY(currentLevel,
                    playerEntity.x,
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
            playerEntity.y = getLandY(currentLevel,
                playerEntity.x,
                playerEntity.z);
            scene.add(playerEntity.z, 60,
                playerEntity.animationDelay,
                playerEntity.x, k,
                playerEntity.animationStretches, null, playerEntity,
                playerEntity.y, currentLevel);
        }
    }

    public int getLandY(int i, int j, int k) {
        int l = j >> 7;
        int i1 = k >> 7;
        int j1 = i;
        if (j1 < 3 && (levelRenderFlags[1][l][i1] & 2) == 2)
            j1++;
        int k1 = j & 0x7f;
        int l1 = k & 0x7f;
        int i2 = levelHeightMaps[j1][l][i1] * (128 - k1) + levelHeightMaps[j1][l + 1][i1] * k1 >> 7;
        int j2 = levelHeightMaps[j1][l][i1 + 1] * (128 - k1)
            + levelHeightMaps[j1][l + 1][i1 + 1] * k1 >> 7;
        return i2 * (128 - l1) + j2 * l1 >> 7;
    }

    public void drawTooltip(NpcType npcType, int j, int k, int l) {
        if (optionCount >= 400)
            return;
        String s = npcType.name;
        if (npcType.level != 0)
            s = s + getLevelColorTag(self.combatLevel, npcType.level) + " (level-"
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

    public void updateKeyboardInput() {
        do {
            int j = pollKey();
            if (j == -1)
                break;
            if (viewportInterfaceIndex != -1 && viewportInterfaceIndex == anInt907) {
                if (j == 8 && aString970.length() > 0)
                    aString970 = aString970.substring(0, aString970.length() - 1);
                if ((j >= 97 && j <= 122 || j >= 65 && j <= 90 || j >= 48 && j <= 57 || j == 32)
                    && aString970.length() < 12)
                    aString970 += (char) j;
            } else if (showSocialInput) {
                if (j >= 32 && j <= 122 && socialInput.length() < 80) {
                    socialInput += (char) j;
                    redrawChatback = true;
                }
                if (j == 8 && socialInput.length() > 0) {
                    socialInput = socialInput.substring(0, socialInput.length() - 1);
                    redrawChatback = true;
                }
                if (j == 13 || j == 10) {
                    showSocialInput = false;
                    redrawChatback = true;
                    if (socialAction == 1) {
                        long l = StringUtils.toBase37(socialInput);
                        addFriend(l);
                    }
                    if (socialAction == 2 && friendCount > 0) {
                        long l1 = StringUtils.toBase37(socialInput);
                        removeFriend(l1);
                    }
                    if (socialAction == 3 && socialInput.length() > 0) {
                        outBuffer.writeOpcode(148);
                        outBuffer.writeByte(0);
                        int k = outBuffer.offset;
                        outBuffer.writeQWord(aLong900);
                        TextEncoder.write(outBuffer, socialInput);
                        outBuffer.writeSize(outBuffer.offset - k);
                        socialInput = StringUtils.toSentence(socialInput);
                        socialInput = WordEncoding.getFiltered(socialInput);
                        addMessage(6, socialInput, StringUtils.formatName(StringUtils.fromBase37(aLong900)));
                        if (chatPrivateSetting == 2) {
                            chatPrivateSetting = 1;
                            chatRedrawSettings = true;
                            outBuffer.writeOpcode(244);
                            outBuffer.writeByte(chatPublicSetting);
                            outBuffer.writeByte(chatPrivateSetting);
                            outBuffer.writeByte(chatTradeDuelSetting);
                        }
                    }
                    if (socialAction == 4 && ignoreCount < 100) {
                        long l2 = StringUtils.toBase37(socialInput);
                        addIgnore(l2);
                    }
                    if (socialAction == 5 && ignoreCount > 0) {
                        long l3 = StringUtils.toBase37(socialInput);
                        removeIgnore(l3);
                    }
                }
            } else if (chatbackInputType) {
                if (j >= 48 && j <= 57 && chatbackInput.length() < 10) {
                    chatbackInput += (char) j;
                    redrawChatback = true;
                }
                if (j == 8 && chatbackInput.length() > 0) {
                    chatbackInput = chatbackInput.substring(0, chatbackInput.length() - 1);
                    redrawChatback = true;
                }
                if (j == 13 || j == 10) {
                    if (chatbackInput.length() > 0) {
                        int i1 = 0;
                        try {
                            i1 = Integer.parseInt(chatbackInput);
                        } catch (Exception _ex) {
                        }
                        outBuffer.writeOpcode(237);
                        outBuffer.writeDWord(i1);
                    }
                    chatbackInputType = false;
                    redrawChatback = true;
                }
            } else if (chatbackComponentId == -1) {
                if (j >= 32 && j <= 122 && aString1137.length() < 80) {
                    aString1137 += (char) j;
                    redrawChatback = true;
                }
                if (j == 8 && aString1137.length() > 0) {
                    aString1137 = aString1137.substring(0, aString1137.length() - 1);
                    redrawChatback = true;
                }
                if ((j == 13 || j == 10) && aString1137.length() > 0) {
                    if (aString1137.equals("::clientdrop")
                        && (super.frame != null || getHost().indexOf("192.168.1.") != -1))
                        reconnect();
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
                        addMessage(2, self.spoken,
                            self.name);
                        if (chatPublicSetting == 2) {
                            chatPublicSetting = 3;
                            chatRedrawSettings = true;
                            outBuffer.writeOpcode(244);
                            outBuffer.writeByte(chatPublicSetting);
                            outBuffer.writeByte(chatPrivateSetting);
                            outBuffer.writeByte(chatTradeDuelSetting);
                        }
                    }
                    aString1137 = "";
                    redrawChatback = true;
                }
            }
        } while (true);
    }

    public void draw() {
        if (errorStarted || errorLoading || errorHost) {
            drawErrorScreen();
            return;
        }

        if (!ingame) {
            drawTitleScreen();
        } else {
            drawGame();
        }

        dragCycle = 0;
    }

    public void updateTitle() {
        if (titleState == 0) {
            int i = super.gameWidth / 2 - 80;
            int l = super.gameHeight / 2 + 20;
            l += 20;
            if (super.mouseButton == 1 && super.clickX >= i - 75 && super.clickX <= i + 75 && super.clickY >= l - 20
                && super.clickY <= l + 20) {
                titleState = 3;
                loginFocusedLine = 0;
            }
            i = super.gameWidth / 2 + 80;
            if (super.mouseButton == 1 && super.clickX >= i - 75 && super.clickX <= i + 75 && super.clickY >= l - 20
                && super.clickY <= l + 20) {
                loginMessage0 = "";
                loginMessage1 = "Enter your username & password.";
                titleState = 2;
                loginFocusedLine = 0;
                return;
            }
        } else {
            if (titleState == 2) {
                int j = super.gameHeight / 2 - 40;
                j += 30;
                j += 25;
                if (super.mouseButton == 1 && super.clickY >= j - 15 && super.clickY < j)
                    loginFocusedLine = 0;
                j += 15;
                if (super.mouseButton == 1 && super.clickY >= j - 15 && super.clickY < j)
                    loginFocusedLine = 1;
                j += 15;
                int i1 = super.gameWidth / 2 - 80;
                int k1 = super.gameHeight / 2 + 50;
                k1 += 20;
                if (super.mouseButton == 1 && super.clickX >= i1 - 75 && super.clickX <= i1 + 75
                    && super.clickY >= k1 - 20 && super.clickY <= k1 + 20)
                    login(username, password, false);
                i1 = super.gameWidth / 2 + 80;
                if (super.mouseButton == 1 && super.clickX >= i1 - 75 && super.clickX <= i1 + 75
                    && super.clickY >= k1 - 20 && super.clickY <= k1 + 20) {
                    titleState = 0;
                    username = "";
                    password = "";
                }
                do {
                    int l1 = pollKey();
                    if (l1 == -1)
                        break;
                    boolean flag = false;
                    for (int i2 = 0; i2 < ASCII_CHARSET.length(); i2++) {
                        if (l1 != ASCII_CHARSET.charAt(i2))
                            continue;
                        flag = true;
                        break;
                    }

                    if (loginFocusedLine == 0) {
                        if (l1 == 8 && username.length() > 0)
                            username = username.substring(0, username.length() - 1);
                        if (l1 == 9 || l1 == 10 || l1 == 13)
                            loginFocusedLine = 1;
                        if (flag)
                            username += (char) l1;
                        if (username.length() > 12)
                            username = username.substring(0, 12);
                    } else if (loginFocusedLine == 1) {
                        if (l1 == 8 && password.length() > 0)
                            password = password.substring(0, password.length() - 1);
                        if (l1 == 9 || l1 == 10 || l1 == 13)
                            loginFocusedLine = 0;
                        if (flag)
                            password += (char) l1;
                        if (password.length() > 20)
                            password = password.substring(0, 20);
                    }
                } while (true);
                return;
            }
            if (titleState == 3) {
                int k = super.gameWidth / 2;
                int j1 = super.gameHeight / 2 + 50;
                j1 += 20;
                if (super.mouseButton == 1 && super.clickX >= k - 75 && super.clickX <= k + 75 && super.clickY >= j1 - 20
                    && super.clickY <= j1 + 20)
                    titleState = 0;
            }
        }
    }

    public FileArchive loadArchive(String s, int i, String s1, int j) {
        int l = 5;
        byte[] data = Signlink.cacheload(s1);
        if (data != null) {
            crc32.reset();
            crc32.update(data);
            int i1 = (int) crc32.getValue();
            if (i1 != i)
                data = null;
        }
        if (data != null) {
            return new FileArchive(data);
        }
        while (data == null) {
            showProgress(true, "Requesting " + s, j);
            try {
                int j1 = 0;
                DataInputStream datainputstream = openStream(s1 + i);
                byte[] abyte1 = new byte[6];
                datainputstream.readFully(abyte1, 0, 6);
                Buffer class38_sub2_sub3 = new Buffer(abyte1);
                class38_sub2_sub3.offset = 3;
                int i2 = class38_sub2_sub3.readSWord() + 6;
                int j2 = 6;
                data = new byte[i2];
                for (int k2 = 0; k2 < 6; k2++)
                    data[k2] = abyte1[k2];

                while (j2 < i2) {
                    int l2 = i2 - j2;
                    if (l2 > 1000)
                        l2 = 1000;
                    j2 += datainputstream.read(data, j2, l2);
                    int i3 = (j2 * 100) / i2;
                    if (i3 != j1)
                        showProgress(true, "Loading " + s + " - " + i3 + "%", j);
                    j1 = i3;
                }
                datainputstream.close();
            } catch (IOException _ex) {
                data = null;
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
        Signlink.cachesave(s1, data);
        return new FileArchive(data);
    }

    public void disposeTitleComponents() {
        flameActive = false;
        while (flameThreadActive) {
            flameActive = false;
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
        imageTitlebox = null;
        imageTitlebutton = null;
        imageRunes = null;
        flameGradient = null;
        flameGradientRed = null;
        flameGradientGreen = null;
        flameGradientViolet = null;
        flameBuffer1 = null;
        flameBuffer2 = null;
        flameIntensity = null;
        flameIntensityBuffer = null;
        imageFlamesLeft = null;
        imageFlamesRight = null;
    }

    public void updateCameraOrbit(int i, int j, int k, int l, int j1, int k1) {
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
        cameraX = j - j2;
        cameraY = i - k2;
        cameraZ = j1 - l2;
        cameraPitch = l;
        cameraOrbitYaw = k;
    }

    public static String formatNumber(int i) {
        String s = String.valueOf(i);
        for (int k = s.length() - 3; k > 0; k -= 3)
            s = s.substring(0, k) + "," + s.substring(k);

        if (s.length() > 8)
            s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
        else if (s.length() > 4)
            s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
        return " " + s;
    }

    public void updateAnimatedTextures(int cycle) {
        if (lowMemory) {
            return;
        }

        if (Draw3D.textureCycles[17] >= cycle) {
            IndexedSprite i = Draw3D.textures[17];
            int j = i.width * i.height - 1;
            int l = i.width * sceneDelta * 2;
            byte[] abyte0 = i.pixels;
            byte[] abyte2 = tmpTexels;
            for (int j1 = 0; j1 <= j; j1++)
                abyte2[j1] = abyte0[j1 - l & j];

            i.pixels = abyte2;
            tmpTexels = abyte0;
            Draw3D.updateTexture(17);
        }

        if (Draw3D.textureCycles[24] >= cycle) {
            IndexedSprite i = Draw3D.textures[24];
            int k = i.width * i.height - 1;
            int i1 = i.width * sceneDelta * 2;
            byte[] abyte1 = i.pixels;
            byte[] abyte3 = tmpTexels;
            for (int k1 = 0; k1 <= k; k1++)
                abyte3[k1] = abyte1[k1 - i1 & k];

            i.pixels = abyte3;
            tmpTexels = abyte1;
            Draw3D.updateTexture(24);
        }
    }

    public void updateFlames() {
        char c = '\u0100';
        for (int i = 10; i < 117; i++) {
            int j = (int) (Math.random() * 100D);
            if (j < 50)
                flameIntensity[i + (c - 2 << 7)] = 255;
        }

        for (int k = 0; k < 100; k++) {
            int l = (int) (Math.random() * 124D) + 2;
            int j1 = (int) (Math.random() * 128D) + 128;
            int j2 = l + (j1 << 7);
            flameIntensity[j2] = 192;
        }

        for (int i1 = 1; i1 < c - 1; i1++) {
            for (int k1 = 1; k1 < 127; k1++) {
                int k2 = k1 + (i1 << 7);
                flameIntensityBuffer[k2] = (flameIntensity[k2 - 1] + flameIntensity[k2 + 1] + flameIntensity[k2 - 128]
                    + flameIntensity[k2 + 128]) / 4;
            }
        }

        flameOffset += 128;

        if (flameOffset > flameBuffer1.length) {
            flameOffset -= flameBuffer1.length;
            int l1 = (int) (Math.random() * 12D);
            updateFlameDissolve(imageRunes[l1]);
        }

        for (int i2 = 1; i2 < c - 1; i2++) {
            for (int l2 = 1; l2 < 127; l2++) {
                int j3 = l2 + (i2 << 7);
                int l3 = flameIntensityBuffer[j3 + 128] - flameBuffer1[j3 + flameOffset & flameBuffer1.length - 1] / 5;
                if (l3 < 0)
                    l3 = 0;
                flameIntensity[j3] = l3;
            }
        }

        for (int i3 = 0; i3 < c - 1; i3++)
            flameShiftX[i3] = flameShiftX[i3 + 1];

        flameShiftX[c - 1] = (int) (Math.sin((double) clientClock / 14D) * 16D + Math.sin((double) clientClock / 15D) * 14D
            + Math.sin((double) clientClock / 16D) * 12D);

        if (flameCycle1 > 0)
            flameCycle1 -= 4;

        if (flameCycle2 > 0)
            flameCycle2 -= 4;

        if (flameCycle1 == 0 && flameCycle2 == 0) {
            int k3 = (int) (Math.random() * 2000D);
            if (k3 == 0)
                flameCycle1 = 1024;
            if (k3 == 1)
                flameCycle2 = 1024;
        }
    }

    public void drawMinimap() {
        areaMapback.bind();

        int i = cameraYaw + minimapAnticheatAngle & 0x7ff;
        int j = 48 + self.x / 32;
        int l1 = 464 - self.z / 32;

        minimap.drawRotatedMasked(i, 146, minimapLeft, 151, l1, 256 + minimapZoom, j, 21, 9, minimapLineWidth);
        compass.drawRotatedMasked(cameraYaw, 33, compassLeft, 33, 25, 256, 25, 0, 0, compassLineWidth);

        for (int j3 = 0; j3 < activeMapFunctionCount; j3++) {
            int k = (activeMapFunctionX[j3] * 4 + 2) - self.x / 32;
            int i2 = (activeMapFunctionZ[j3] * 4 + 2) - self.z / 32;
            drawOnMinimap(i2, activeMapFunctions[j3], k);
        }

        for (int k3 = 0; k3 < 104; k3++) {
            for (int l3 = 0; l3 < 104; l3++) {
                LinkedList stack = objects[currentLevel][k3][l3];
                if (stack != null) {
                    int l = (k3 * 4 + 2) - self.x / 32;
                    int j2 = (l3 * 4 + 2) - self.z / 32;
                    drawOnMinimap(j2, mapdot1, l);
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
                drawOnMinimap(k2, mapdot2, i1);
            }
        }

        for (int j4 = 0; j4 < playerCount; j4++) {
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
                    drawOnMinimap(l2, mapdot4, j1);
                else
                    drawOnMinimap(l2, mapdot3, j1);
            }
        }

        if (flagTileX != 0) {
            int k1 = (flagTileX * 4 + 2) - self.x / 32;
            int i3 = (flagTileY * 4 + 2) - self.z / 32;
            drawOnMinimap(i3, mapflag, k1);
        }
        Draw2D.fillRect(82, 93, 0xffffff, 3, 3);
        areaViewport.bind();
    }

    public Component getBaseComponent() {
        if (Signlink.mainapp != null)
            return Signlink.mainapp;

        if (super.frame != null)
            return super.frame;
        else
            return this;
    }

    public void updateTemporaryLocs() {
        if (sceneState == 2) {
            for (TemporaryLoc loc = (TemporaryLoc) temporaryLocs
                .peekLast(); loc != null; loc = (TemporaryLoc) temporaryLocs.getPrevious())
                if (clientClock >= loc.lastCycle) {
                    addLoc(loc.rotation, loc.tileX, loc.tileZ,
                        loc.classType, loc.locIndex, loc.type,
                        loc.level);
                    loc.unlink();
                }

            updateLocCounter++;
            if (updateLocCounter > 85) {
                updateLocCounter = 0;
                outBuffer.writeOpcode(85);
            }
        }
    }

    public void createMinimap(int i) {
        int[] ai = minimap.pixels;
        int k = ai.length;
        for (int l = 0; l < k; l++)
            ai[l] = 0;

        for (int i1 = 1; i1 < 103; i1++) {
            int j1 = 24628 + (103 - i1) * 512 * 4;
            for (int l1 = 1; l1 < 103; l1++) {
                if ((levelRenderFlags[i][l1][i1] & 0x18) == 0)
                    scene.drawMinimapTile(ai, j1, 512, i, l1, i1);
                if (i < 3 && (levelRenderFlags[i + 1][l1][i1] & 8) != 0)
                    scene.drawMinimapTile(ai, j1, 512, i + 1, l1, i1);
                j1 += 4;
            }
        }

        int k1 = ((238 + (int) (Math.random() * 20D)) - 10 << 16) + ((238 + (int) (Math.random() * 20D)) - 10 << 8)
            + ((238 + (int) (Math.random() * 20D)) - 10);
        int i2 = (238 + (int) (Math.random() * 20D)) - 10 << 16;
        minimap.prepare();
        for (int j2 = 1; j2 < 103; j2++) {
            for (int k2 = 1; k2 < 103; k2++) {
                if ((levelRenderFlags[i][k2][j2] & 0x18) == 0)
                    drawMinimapLoc(i, k1, k2, i2, j2);
                if (i < 3 && (levelRenderFlags[i + 1][k2][j2] & 8) != 0)
                    drawMinimapLoc(i + 1, k1, k2, i2, j2);
            }

        }

        areaViewport.bind();
        activeMapFunctionCount = 0;
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
                        activeMapFunctions[activeMapFunctionCount] = mapfunction[k3];
                        activeMapFunctionX[activeMapFunctionCount] = l3;
                        activeMapFunctionZ[activeMapFunctionCount] = i4;
                        activeMapFunctionCount++;
                    }
                }
            }
        }
    }

    public void drawMinimapLoc(int j, int k, int l, int i1, int j1) {
        int k1 = scene.getWallBitset(j, l, j1);
        if (k1 != 0) {
            int l1 = scene.getInfo(j, l, j1, k1);
            int k2 = l1 >> 6 & 3;
            int i3 = l1 & 0x1f;
            int k3 = k;
            if (k1 > 0)
                k3 = i1;
            int[] ai = minimap.pixels;
            int k4 = 24624 + l * 4 + (103 - j1) * 512 * 4;
            int i5 = k1 >> 14 & 0x7fff;
            LocType locType_2 = LocType.get(i5);
            if (locType_2.mapscene != -1) {
                IndexedSprite s = mapscene[locType_2.mapscene];
                if (s != null) {
                    int i6 = (locType_2.sizeX * 4 - s.width) / 2;
                    int j6 = (locType_2.sizeZ * 4 - s.height) / 2;
                    s.draw(48 + (104 - j1 - locType_2.sizeZ) * 4 + j6, 48 + l * 4 + i6);
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
        if (k1 != 0) {
            int i2 = scene.getInfo(j, l, j1, k1);
            int l2 = i2 >> 6 & 3;
            int j3 = i2 & 0x1f;
            int l3 = k1 >> 14 & 0x7fff;
            LocType locType = LocType.get(l3);
            if (locType.mapscene != -1) {
                IndexedSprite indexedSprite_1 = mapscene[locType.mapscene];
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
                int[] ai1 = minimap.pixels;
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
                IndexedSprite indexedSprite = mapscene[locType.mapscene];
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
            Signlink.reporterror(username + " size mismatch in getnpcpos - pos:" + buffer.offset + " psize:" + size);
            throw new RuntimeException("eek");
        }

        for (int pos = 0; pos < npcCount; pos++) {
            if (npcEntities[npcIndices[pos]] == null) {
                Signlink.reporterror(username + " null entry in npc list - pos:" + pos + " size:" + npcCount);
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

    public void loadTitleForeground() {
        imageTitlebox = new IndexedSprite(titleArchive, "titlebox", 0);
        imageTitlebutton = new IndexedSprite(titleArchive, "titlebutton", 0);

        imageRunes = new IndexedSprite[12];
        for (int j = 0; j < 12; j++) {
            imageRunes[j] = new IndexedSprite(titleArchive, "runes", j);
        }

        imageFlamesLeft = new Sprite(128, 265);
        imageFlamesRight = new Sprite(128, 265);
        System.arraycopy(titleLeft.pixels, 0, imageFlamesLeft.pixels, 0, 33920);
        System.arraycopy(titleRight.pixels, 0, imageFlamesRight.pixels, 0, 33920);

        flameGradientRed = new int[256];
        for (int i1 = 0; i1 < 64; i1++) {
            flameGradientRed[i1] = i1 * 0x40000;
        }
        for (int j1 = 0; j1 < 64; j1++) {
            flameGradientRed[j1 + 64] = 0xff0000 + 1024 * j1;
        }
        for (int k1 = 0; k1 < 64; k1++) {
            flameGradientRed[k1 + 128] = 0xffff00 + 4 * k1;
        }
        for (int l1 = 0; l1 < 64; l1++) {
            flameGradientRed[l1 + 192] = 0xffffff;
        }

        flameGradientGreen = new int[256];
        for (int i2 = 0; i2 < 64; i2++) {
            flameGradientGreen[i2] = i2 * 1024;
        }
        for (int j2 = 0; j2 < 64; j2++) {
            flameGradientGreen[j2 + 64] = 65280 + 4 * j2;
        }
        for (int k2 = 0; k2 < 64; k2++) {
            flameGradientGreen[k2 + 128] = 65535 + 0x40000 * k2;
        }
        for (int l2 = 0; l2 < 64; l2++) {
            flameGradientGreen[l2 + 192] = 0xffffff;
        }

        flameGradientViolet = new int[256];
        for (int i3 = 0; i3 < 64; i3++) {
            flameGradientViolet[i3] = i3 * 4;
        }
        for (int j3 = 0; j3 < 64; j3++) {
            flameGradientViolet[j3 + 64] = 255 + 0x40000 * j3;
        }
        for (int k3 = 0; k3 < 64; k3++) {
            flameGradientViolet[k3 + 128] = 0xff00ff + 1024 * k3;
        }
        for (int l3 = 0; l3 < 64; l3++) {
            flameGradientViolet[l3 + 192] = 0xffffff;
        }

        flameGradient = new int[256];
        flameBuffer1 = new int[32768];
        flameBuffer2 = new int[32768];
        updateFlameDissolve(null);
        flameIntensity = new int[32768];
        flameIntensityBuffer = new int[32768];
        showProgress(true, "Connecting to fileserver", 10);

        if (!flameActive) {
            flamesThread = true;
            flameActive = true;
            startThread(this, 2);
        }
    }

    public void updateOtherPlayers(Buffer buffer) {
        int k = buffer.getBits(8);

        if (k < playerCount) {
            for (int i1 = k; i1 < playerCount; i1++) {
                deadEntityIndices[deadEntityCount++] = playerIndices[i1];
            }
        }

        if (k > playerCount) {
            Signlink.reporterror(username + " Too many players");
            throw new RuntimeException("eek");
        }

        playerCount = 0;
        for (int n = 0; n < k; n++) {
            int index = playerIndices[n];
            PlayerEntity p = playerEntities[index];

            int shouldRemove = buffer.getBits(1);
            if (shouldRemove == 0) {
                playerIndices[playerCount++] = index;
                p.remove = clientClock;
            } else {
                int type = buffer.getBits(2);
                if (type == 0) {
                    playerIndices[playerCount++] = index;
                    p.remove = clientClock;
                    entityUpdateIndices[updateCount++] = index;
                } else if (type == 1) {
                    playerIndices[playerCount++] = index;
                    p.remove = clientClock;
                    int j2 = buffer.getBits(3);
                    p.walk(false, j2);
                    int l2 = buffer.getBits(1);
                    if (l2 == 1) {
                        entityUpdateIndices[updateCount++] = index;
                    }
                } else if (type == 2) {
                    playerIndices[playerCount++] = index;
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

    public void drawScrollbar(int j, int k, int l, int i1, int j1) {
        scrollbar1.draw(k, j);
        scrollbar2.draw((k + j1) - 16, j);
        Draw2D.fillRect(k + 16, j, SCROLLBAR_TRACK, 16, j1 - 32);
        int k1 = ((j1 - 32) * j1) / i1;
        if (k1 < 8)
            k1 = 8;
        int l1 = ((j1 - 32 - k1) * l) / (i1 - j1);
        Draw2D.fillRect(k + 16 + l1, j, SCROLLBAR_GRIP_FOREGROUND, 16, k1);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + l1, k1, j);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + l1, k1, j + 1);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + l1, 16, j);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 17 + l1, 16, j);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 16 + l1, k1, j + 15);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 17 + l1, k1 - 1, j + 14);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 15 + l1 + k1, 16, j);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 14 + l1 + k1, 15, j + 1);
    }

    public void resetCharacterDesign() {
        characterDesignUpdate = true;
        for (int i = 0; i < 7; i++) {
            characterDesigns[i] = -1;
            for (int j = 0; j < IdkType.count; j++) {
                if (IdkType.instances[j].validStyle || IdkType.instances[j].type != i + (characterDesignIsMale ? 0 : 7))
                    continue;
                characterDesigns[i] = j;
                break;
            }
        }
    }

    public void midisave(byte[] abyte0, int j, boolean fade) {
        Signlink.midifade = fade ? 1 : 0;
        Signlink.midisave(abyte0, j);
    }

    public void updateSceneNpcs() {
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
                getLandY(currentLevel, npcEntity.x,
                    npcEntity.z),
                currentLevel);
        }
    }

    public void midivol(int j, boolean flag) {
        Signlink.midivol = j;
        if (flag)
            Signlink.midi = "voladjust";
    }

    public void drawTitleScreen() {
        prepareTitleScreen();
        titleCenter.bind();
        imageTitlebox.draw(0, 0);
        char c = '\u0168';
        char c1 = '\310';
        if (titleState == 0) {
            int j = c1 / 2 - 20;
            fontBold12.drawCentered(c / 2, 0xffff00, true, j, "Welcome to RuneScape");
            j += 30;
            int i1 = c / 2 - 80;
            int l1 = c1 / 2 + 20;
            imageTitlebutton.draw(l1 - 20, i1 - 73);
            fontBold12.drawCentered(i1, 0xffffff, true, l1 + 5, "New user");
            i1 = c / 2 + 80;
            imageTitlebutton.draw(l1 - 20, i1 - 73);
            fontBold12.drawCentered(i1, 0xffffff, true, l1 + 5, "Existing User");
        }
        if (titleState == 2) {
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
                "Username: " + username + ((loginFocusedLine == 0) & (clientClock % 40 < 20) ? "@yel@|" : ""), true,
                0xffffff);
            k += 15;
            fontBold12.draw(c / 2 - 88, k, "Password: " + StringUtils.toAsterisks(password)
                + ((loginFocusedLine == 1) & (clientClock % 40 < 20) ? "@yel@|" : ""), true, 0xffffff);
            k += 15;
            int j1 = c / 2 - 80;
            int i2 = c1 / 2 + 50;
            imageTitlebutton.draw(i2 - 20, j1 - 73);
            fontBold12.drawCentered(j1, 0xffffff, true, i2 + 5, "Login");
            j1 = c / 2 + 80;
            imageTitlebutton.draw(i2 - 20, j1 - 73);
            fontBold12.drawCentered(j1, 0xffffff, true, i2 + 5, "Cancel");
        }
        if (titleState == 3) {
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
            imageTitlebutton.draw(j2 - 20, k1 - 73);
            fontBold12.drawCentered(k1, 0xffffff, true, j2 + 5, "Cancel");
        }
        titleCenter.drawImage(186, super.graphics, 214);
        if (redrawTitleBackground) {
            redrawTitleBackground = false;
            titleTop.drawImage(0, super.graphics, 128);
            titleBottom.drawImage(386, super.graphics, 214);
            titleBottomLeft.drawImage(265, super.graphics, 0);
            titleBottomRight.drawImage(265, super.graphics, 574);
            titleLeftSpace.drawImage(186, super.graphics, 128);
            titleRightSpace.drawImage(186, super.graphics, 574);
        }
    }

    public void prepareGameScreen() {
        if (areaChatback != null)
            return;
        disposeTitleComponents();
        super.drawArea = null;
        titleTop = null;
        titleBottom = null;
        titleCenter = null;
        titleLeft = null;
        titleRight = null;
        titleBottomLeft = null;
        titleBottomRight = null;
        titleLeftSpace = null;
        titleRightSpace = null;
        areaChatback = new DrawArea(getBaseComponent(), 479, 96);
        areaMapback = new DrawArea(getBaseComponent(), 168, 160);
        Draw2D.clear();
        mapback.draw(0, 0);
        areaInvback = new DrawArea(getBaseComponent(), 190, 261);
        areaViewport = new DrawArea(getBaseComponent(), 512, 334);
        Draw2D.clear();
        areaBackbase1 = new DrawArea(getBaseComponent(), 501, 61);
        areaBackbase2 = new DrawArea(getBaseComponent(), 288, 40);
        areaBackhmid1 = new DrawArea(getBaseComponent(), 269, 66);
        redrawTitleBackground = true;
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

            playerIndices[playerCount++] = pid;
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

    public void disconnect() {
        try {
            if (stream != null)
                stream.close();
        } catch (Exception _ex) {
        }
        stream = null;
        ingame = false;
        titleState = 0;
        username = "";
        password = "";
        InputTracking.setDisabled();
        clearCaches();
        scene.reset();
        for (int j = 0; j < 4; j++)
            collisionMaps[j].reset();

        System.gc();
        midistop();
        aString1119 = null;
        anInt744 = 0;
    }

    public void drawInterface(int i, int j, InterfaceComponent interfaceComponent, int l) {
        if (interfaceComponent.type != 0 || interfaceComponent.children == null)
            return;
        if (interfaceComponent.hidden && viewportHoveredInterfaceIndex != interfaceComponent.id && sidebarHoveredInterfaceIndex != interfaceComponent.id
            && chatHoveredInterfaceIndex != interfaceComponent.id)
            return;
        int i1 = Draw2D.left;
        int j1 = Draw2D.top;
        int k1 = Draw2D.right;
        int l1 = Draw2D.bottom;
        Draw2D.setBounds(i + interfaceComponent.height, i, j + interfaceComponent.width, j);
        int i2 = interfaceComponent.children.length;
        for (int j2 = 0; j2 < i2; j2++) {
            int k2 = interfaceComponent.childX[j2] + j;
            int l2 = (interfaceComponent.childY[j2] + i) - l;
            InterfaceComponent child = InterfaceComponent.instances[interfaceComponent.children[j2]];
            k2 += child.x;
            l2 += child.y;
            if (child.contentType > 0)
                updateComponentContent(child);
            if (child.type == 0) {
                if (child.scrollY > child.scrollHeight - child.height)
                    child.scrollY = child.scrollHeight - child.height;
                if (child.scrollY < 0)
                    child.scrollY = 0;
                drawInterface(l2, k2, child, child.scrollY);
                if (child.scrollHeight > child.height)
                    drawScrollbar(k2 + child.width, l2, child.scrollY, child.scrollHeight,
                        child.height);
            } else if (child.type != 1)
                if (child.type == 2) {
                    int i3 = 0;
                    for (int k3 = 0; k3 < child.height; k3++) {
                        for (int k4 = 0; k4 < child.width; k4++) {
                            int j5 = k2 + k4 * (32 + child.inventoryMarginX);
                            int i6 = l2 + k3 * (32 + child.inventoryMarginY);
                            if (i3 < 20) {
                                j5 += child.inventoryOffsetX[i3];
                                i6 += child.inventoryOffsetY[i3];
                            }
                            if (child.inventoryIndices[i3] > 0) {
                                int k6 = 0;
                                int k8 = 0;
                                int i9 = child.inventoryIndices[i3] - 1;
                                if (j5 >= -32 && j5 <= 512 && i6 >= -32 && i6 <= 334
                                    || objDragArea != 0 && objDragSlot == i3) {
                                    Sprite itemIcon = ObjType.getSprite(i9,
                                        child.inventoryAmount[i3]);
                                    if (objDragArea != 0 && objDragSlot == i3 && objDragComponentId == child.id) {
                                        k6 = super.mouseX - objGrabX;
                                        k8 = super.mouseY - objGrabY;
                                        if (k6 < 5 && k6 > -5)
                                            k6 = 0;
                                        if (k8 < 5 && k8 > -5)
                                            k8 = 0;
                                        if (objDragCycles < 5) {
                                            k6 = 0;
                                            k8 = 0;
                                        }
                                        itemIcon.draw(128, j5 + k6, i6 + k8);
                                    } else if (selectedArea != 0 && anInt946 == i3 && anInt945 == child.id)
                                        itemIcon.draw(128, j5, i6);
                                    else
                                        itemIcon.draw(i6, j5);
                                    if (itemIcon.cropW == 33 || child.inventoryAmount[i3] != 1) {
                                        int k9 = child.inventoryAmount[i3];
                                        fontPlain11.draw(j5 + 1 + k6, i6 + 10 + k8, 0,
                                            formatObjAmount(k9));
                                        fontPlain11.draw(j5 + k6, i6 + 9 + k8, 0xffff00,
                                            formatObjAmount(k9));
                                    }
                                }
                            } else if (child.inventorySprite != null && i3 < 20) {
                                Sprite class38_sub2_sub2_sub2_1 = child.inventorySprite[i3];
                                if (class38_sub2_sub2_sub2_1 != null)
                                    class38_sub2_sub2_sub2_1.draw(i6, j5);
                            }
                            i3++;
                        }

                    }

                } else if (child.type == 3) {
                    if (child.fill)
                        Draw2D.fillRect(l2, k2, child.color, child.width,
                            child.height);
                    else
                        Draw2D.drawRect(k2, child.color, child.height, l2,
                            child.width);
                } else if (child.type == 4) {
                    IndexedFont indexedFont = child.font;
                    int l3 = child.color;
                    String s = child.text;
                    if ((chatHoveredInterfaceIndex == child.id || sidebarHoveredInterfaceIndex == child.id
                        || viewportHoveredInterfaceIndex == child.id) && child.hoverColor != 0)
                        l3 = child.hoverColor;
                    if (isInterfaceEnabled(child)) {
                        l3 = child.colorEnabled;
                        if (child.activeText.length() > 0)
                            s = child.activeText;
                    }
                    if (child.buttonType == 6 && chatContinuingDialogue) {
                        s = "Please wait...";
                        l3 = child.color;
                    }
                    for (int j6 = l2 + indexedFont.height; s
                        .length() > 0; j6 += indexedFont.height) {
                        if (s.indexOf("%") != -1) {
                            do {
                                int l6 = s.indexOf("%1");
                                if (l6 == -1)
                                    break;
                                s = s.substring(0, l6) + getIntString(executeInterface(child, 0))
                                    + s.substring(l6 + 2);
                            } while (true);
                            do {
                                int i7 = s.indexOf("%2");
                                if (i7 == -1)
                                    break;
                                s = s.substring(0, i7) + getIntString(executeInterface(child, 1))
                                    + s.substring(i7 + 2);
                            } while (true);
                            do {
                                int j7 = s.indexOf("%3");
                                if (j7 == -1)
                                    break;
                                s = s.substring(0, j7) + getIntString(executeInterface(child, 2))
                                    + s.substring(j7 + 2);
                            } while (true);
                            do {
                                int k7 = s.indexOf("%4");
                                if (k7 == -1)
                                    break;
                                s = s.substring(0, k7) + getIntString(executeInterface(child, 3))
                                    + s.substring(k7 + 2);
                            } while (true);
                            do {
                                int l7 = s.indexOf("%5");
                                if (l7 == -1)
                                    break;
                                s = s.substring(0, l7) + getIntString(executeInterface(child, 4))
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
                        if (child.center)
                            indexedFont.drawCentered(k2 + child.width / 2, l3, child.shadow, j6,
                                s1);
                        else
                            indexedFont.draw(k2, j6, s1, child.shadow, l3);
                    }

                } else if (child.type == 5) {
                    Sprite image;
                    if (isInterfaceEnabled(child))
                        image = child.activeImage;
                    else
                        image = child.image;
                    if (image != null)
                        image.draw(l2, k2);
                } else if (child.type == 6) {
                    int j3 = Draw3D.centerX;
                    int i4 = Draw3D.centerY;
                    Draw3D.centerX = k2 + child.width / 2;
                    Draw3D.centerY = l2 + child.height / 2;
                    int l4 = Draw3D.sin[child.modelEyePitch] * child.modelZoom >> 16;
                    int k5 = Draw3D.cos[child.modelEyePitch] * child.modelZoom >> 16;
                    boolean flag = isInterfaceEnabled(child);
                    int j8;
                    if (flag)
                        j8 = child.activeSeqId;
                    else
                        j8 = child.seqId;
                    Model model;
                    if (j8 == -1) {
                        model = child.getModel(-1, -1, flag);
                    } else {
                        SeqType seqType = SeqType.animations[j8];
                        model = child.getModel(seqType.primaryFrames[child.seqFrame],
                            seqType.secondaryFrames[child.seqFrame], flag);
                    }
                    if (model != null)
                        model.drawSimple(0, child.modelYaw, 0, child.modelEyePitch, 0, l4, k5);
                    Draw3D.centerX = j3;
                    Draw3D.centerY = i4;
                } else if (child.type == 7) {
                    IndexedFont indexedFont_1 = child.font;
                    int j4 = 0;
                    for (int i5 = 0; i5 < child.height; i5++) {
                        for (int l5 = 0; l5 < child.width; l5++) {
                            if (child.inventoryIndices[j4] > 0) {
                                ObjType objType = ObjType.get(child.inventoryIndices[j4] - 1);
                                String s2 = objType.name;
                                if (objType.stackable || child.inventoryAmount[j4] != 1)
                                    s2 = s2 + " x" + formatNumber(child.inventoryAmount[j4]);
                                int l8 = k2 + l5 * (115 + child.inventoryMarginX);
                                int j9 = l2 + i5 * (12 + child.inventoryMarginY);
                                if (child.center)
                                    indexedFont_1.drawCentered(l8 + child.width / 2, child.color,
                                        child.shadow, j9, s2);
                                else
                                    indexedFont_1.draw(l8, j9, s2, child.shadow,
                                        child.color);
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

    public void updateVarp(int i) {
        int k = VarpType.instances[i].type;
        if (k == 0)
            return;
        int l = variables[i];
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
                midivol(0, aBoolean835);
                aBoolean835 = true;
            }
            if (l == 1) {
                midivol(-400, aBoolean835);
                aBoolean835 = true;
            }
            if (l == 2) {
                midivol(-800, aBoolean835);
                aBoolean835 = true;
            }
            if (l == 3) {
                midivol(-1200, aBoolean835);
                aBoolean835 = true;
            }
            if (l == 4)
                aBoolean835 = false;
            if (aBoolean835 != flag) {
                if (aBoolean835)
                    setMidi(anInt1110, aString1119, anInt1155);
                else
                    midistop();
                anInt744 = 0;
            }
        }
        if (k == 4) {
            if (l == 0) {
                aBoolean1153 = true;
                wavevol(0);
            }
            if (l == 1) {
                aBoolean1153 = true;
                wavevol(-400);
            }
            if (l == 2) {
                aBoolean1153 = true;
                wavevol(-800);
            }
            if (l == 3) {
                aBoolean1153 = true;
                wavevol(-1200);
            }
            if (l == 4)
                aBoolean1153 = false;
        }
        if (k == 5)
            anInt810 = l;
        if (k == 6)
            chatEffects = l;
        if (k == 8) {
            anInt833 = l;
            redrawChatback = true;
        }
    }

    public void updateNpcEntity(boolean flag) {
        ingame &= flag;
        for (int i = 0; i < npcCount; i++) {
            int j = npcIndices[i];
            NpcEntity npcEntity = npcEntities[j];
            if (npcEntity != null) {
                updateEntity(npcEntity);
            }
        }
    }

    public void updateEntity(PathingEntity entity) {
        if (entity.x < 128 || entity.z < 128
            || entity.x >= 13184 || entity.z >= 13184) {
            entity.primarySeq = -1;
            entity.spotAnimIndex = -1;
            entity.firstMoveCycle = 0;
            entity.lastMoveCycle = 0;
            entity.x = entity.pathTileX[0] * 128 + entity.index * 64;
            entity.z = entity.pathTileZ[0] * 128 + entity.index * 64;
            entity.pathRemaining = 0;
        }
        if (entity == self
            && (entity.x < 1536 || entity.z < 1536
            || entity.x >= 11776 || entity.z >= 11776)) {
            entity.primarySeq = -1;
            entity.spotAnimIndex = -1;
            entity.firstMoveCycle = 0;
            entity.lastMoveCycle = 0;
            entity.x = entity.pathTileX[0] * 128 + entity.index * 64;
            entity.z = entity.pathTileZ[0] * 128 + entity.index * 64;
            entity.pathRemaining = 0;
        }
        if (entity.firstMoveCycle > clientClock)
            moveEntity1(entity);
        else if (entity.lastMoveCycle >= clientClock)
            moveEntity2(entity);
        else
            moveEntity3(entity);
        updateEntity2(entity);
        updateEntity3(entity);
    }

    public void moveEntity1(PathingEntity pathingEntity) {
        int j = pathingEntity.firstMoveCycle - clientClock;
        int k = pathingEntity.srcTileX * 128 + pathingEntity.index * 64;
        int l = pathingEntity.srcTileY * 128 + pathingEntity.index * 64;
        pathingEntity.x += (k - pathingEntity.x) / j;
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

    public void moveEntity2(PathingEntity pathingEntity) {
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

    public void moveEntity3(PathingEntity pathingEntity) {
        pathingEntity.secondarySeq = pathingEntity.standSeq;
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

    public void updateEntity2(PathingEntity pathingEntity) {
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

    public void updateEntity3(PathingEntity pathingEntity) {
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

    public void drawGame() {
        if (redrawTitleBackground) {
            redrawTitleBackground = false;
            backleft1.drawImage(11, super.graphics, 0);
            backleft2.drawImage(375, super.graphics, 0);
            backright1.drawImage(5, super.graphics, 729);
            backright2.drawImage(231, super.graphics, 752);
            backtop1.drawImage(0, super.graphics, 0);
            backtop2.drawImage(0, super.graphics, 561);
            backvmid1.drawImage(11, super.graphics, 520);
            backvmid2.drawImage(231, super.graphics, 520);
            backvmid3.drawImage(375, super.graphics, 501);
            backhmid2.drawImage(345, super.graphics, 0);
            sidebarRedraw = true;
            redrawChatback = true;
            sidebarRedrawIcons = true;
            chatRedrawSettings = true;
            if (sceneState != 2) {
                areaViewport.drawImage(11, super.graphics, 8);
                areaMapback.drawImage(5, super.graphics, 561);
            }
        }
        if (sceneState == 2)
            drawViewport();
        if (menuVisible && mouseArea == 1)
            sidebarRedraw = true;
        if (sidebarInterfaceId != -1) {
            boolean flag = animateInterface(sidebarInterfaceId, sceneDelta);
            if (flag)
                sidebarRedraw = true;
        }
        if (selectedArea == 2)
            sidebarRedraw = true;
        if (objDragArea == 2)
            sidebarRedraw = true;
        if (sidebarRedraw) {
            drawChat2();
            sidebarRedraw = false;
        }
        if (chatbackComponentId == -1) {
            interfaceComponent.scrollY = anInt792 - anInt977 - 77;
            if (super.mouseX > 453 && super.mouseX < 565 && super.mouseY > 350)
                updateInterfaceScrollbar(super.mouseX - 22, super.mouseY - 375, anInt792, 77, false, 463, 0, interfaceComponent);
            int j = anInt792 - 77 - interfaceComponent.scrollY;
            if (j < 0)
                j = 0;
            if (j > anInt792 - 77)
                j = anInt792 - 77;
            if (anInt977 != j) {
                anInt977 = j;
                redrawChatback = true;
            }
        }
        if (chatbackComponentId != -1) {
            boolean flag1 = animateInterface(chatbackComponentId, sceneDelta);
            if (flag1)
                redrawChatback = true;
        }
        if (selectedArea == 3)
            redrawChatback = true;
        if (objDragArea == 3)
            redrawChatback = true;
        if (chatbackMessage != null)
            redrawChatback = true;
        if (menuVisible && mouseArea == 2)
            redrawChatback = true;
        if (redrawChatback) {
            drawChatback();
            redrawChatback = false;
        }
        if (sceneState == 2) {
            drawMinimap();
            areaMapback.drawImage(5, super.graphics, 561);
        }
        if (anInt1128 != -1)
            sidebarRedrawIcons = true;
        if (sidebarRedrawIcons) {
            if (anInt1128 != -1 && anInt1128 == selectedTab) {
                anInt1128 = -1;
                outBuffer.writeOpcode(175);
                outBuffer.writeByte(selectedTab);
            }
            sidebarRedrawIcons = false;
            areaBackhmid1.bind();
            backhmid1.draw(0, 0);
            if (sidebarInterfaceId == -1) {
                if (tabComponentId[selectedTab] != -1) {
                    if (selectedTab == 0)
                        redstone1.draw(30, 29);
                    if (selectedTab == 1)
                        redstone2.draw(29, 59);
                    if (selectedTab == 2)
                        redstone2.draw(29, 87);
                    if (selectedTab == 3)
                        redstone3.draw(29, 115);
                    if (selectedTab == 4)
                        redstone2h.draw(29, 156);
                    if (selectedTab == 5)
                        redstone2h.draw(29, 184);
                    if (selectedTab == 6)
                        redstone1h.draw(30, 212);
                }
                if (tabComponentId[0] != -1 && (anInt1128 != 0 || clientClock % 20 < 10))
                    sideicons[0].draw(34, 35);
                if (tabComponentId[1] != -1 && (anInt1128 != 1 || clientClock % 20 < 10))
                    sideicons[1].draw(32, 59);
                if (tabComponentId[2] != -1 && (anInt1128 != 2 || clientClock % 20 < 10))
                    sideicons[2].draw(32, 86);
                if (tabComponentId[3] != -1 && (anInt1128 != 3 || clientClock % 20 < 10))
                    sideicons[3].draw(33, 121);
                if (tabComponentId[4] != -1 && (anInt1128 != 4 || clientClock % 20 < 10))
                    sideicons[4].draw(34, 157);
                if (tabComponentId[5] != -1 && (anInt1128 != 5 || clientClock % 20 < 10))
                    sideicons[5].draw(32, 185);
                if (tabComponentId[6] != -1 && (anInt1128 != 6 || clientClock % 20 < 10))
                    sideicons[6].draw(34, 212);
            }
            areaBackhmid1.drawImage(165, super.graphics, 520);
            areaBackbase2.bind();
            backbase2.draw(0, 0);
            if (sidebarInterfaceId == -1) {
                if (tabComponentId[selectedTab] != -1) {
                    if (selectedTab == 7)
                        redstone1v.draw(0, 49);
                    if (selectedTab == 8)
                        redstone2v.draw(0, 81);
                    if (selectedTab == 9)
                        redstone2v.draw(0, 108);
                    if (selectedTab == 10)
                        redstone3v.draw(1, 136);
                    if (selectedTab == 11)
                        redstone2hv.draw(0, 178);
                    if (selectedTab == 12)
                        redstone2hv.draw(0, 205);
                    if (selectedTab == 13)
                        redstone1hv.draw(0, 233);
                }
                if (tabComponentId[8] != -1 && (anInt1128 != 8 || clientClock % 20 < 10))
                    sideicons[7].draw(2, 80);
                if (tabComponentId[9] != -1 && (anInt1128 != 9 || clientClock % 20 < 10))
                    sideicons[8].draw(3, 107);
                if (tabComponentId[10] != -1 && (anInt1128 != 10 || clientClock % 20 < 10))
                    sideicons[9].draw(4, 142);
                if (tabComponentId[11] != -1 && (anInt1128 != 11 || clientClock % 20 < 10))
                    sideicons[10].draw(2, 179);
                if (tabComponentId[12] != -1 && (anInt1128 != 12 || clientClock % 20 < 10))
                    sideicons[11].draw(2, 206);
                if (tabComponentId[13] != -1 && (anInt1128 != 13 || clientClock % 20 < 10))
                    sideicons[12].draw(2, 230);
            }
            areaBackbase2.drawImage(492, super.graphics, 501);
            areaViewport.bind();
        }
        if (chatRedrawSettings) {
            chatRedrawSettings = false;
            areaBackbase1.bind();
            backbase1.draw(0, 0);
            fontPlain12.drawCentered(57, 0xffffff, true, 33, "Public chat");
            if (chatPublicSetting == 0)
                fontPlain12.drawCentered(57, 65280, true, 46, "On");
            if (chatPublicSetting == 1)
                fontPlain12.drawCentered(57, 0xffff00, true, 46, "Friends");
            if (chatPublicSetting == 2)
                fontPlain12.drawCentered(57, 0xff0000, true, 46, "Off");
            if (chatPublicSetting == 3)
                fontPlain12.drawCentered(57, 65535, true, 46, "Hide");
            fontPlain12.drawCentered(186, 0xffffff, true, 33, "Private chat");
            if (chatPrivateSetting == 0)
                fontPlain12.drawCentered(186, 65280, true, 46, "On");
            if (chatPrivateSetting == 1)
                fontPlain12.drawCentered(186, 0xffff00, true, 46, "Friends");
            if (chatPrivateSetting == 2)
                fontPlain12.drawCentered(186, 0xff0000, true, 46, "Off");
            fontPlain12.drawCentered(326, 0xffffff, true, 33, "Trade/duel");
            if (chatTradeDuelSetting == 0)
                fontPlain12.drawCentered(326, 65280, true, 46, "On");
            if (chatTradeDuelSetting == 1)
                fontPlain12.drawCentered(326, 0xffff00, true, 46, "Friends");
            if (chatTradeDuelSetting == 2)
                fontPlain12.drawCentered(326, 0xff0000, true, 46, "Off");
            fontPlain12.drawCentered(462, 0xffffff, true, 38, "Report abuse");
            areaBackbase1.drawImage(471, super.graphics, 0);
            areaViewport.bind();
        }
        sceneDelta = 0;
    }

    public boolean isFriend(int row) {
        if (row < 0)
            return false;
        int actionId = actions[row];
        if (actionId >= 2000)
            actionId -= 2000;
        return actionId == 406;
    }

    public void useMenuOption(int j) {
        if (j < 0)
            return;
        if (chatbackInputType) {
            chatbackInputType = false;
            redrawChatback = true;
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
                for (int k3 = 0; k3 < playerCount; k3++) {
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
                    addMessage(0, "Unable to find " + s8, "");
            }
        }
        if (i1 == 450 && interactWithLoc(75, k, l, j1)) {
            outBuffer.writeWord(anInt1005);
            outBuffer.writeWord(anInt1003);
            outBuffer.writeWord(anInt1004);
        }
        if (i1 == 405 || i1 == 38 || i1 == 422 || i1 == 478 || i1 == 347) {
            if (i1 == 478) {
                if ((k & 3) == 0)
                    anticheatCounter1++;
                if (anticheatCounter1 >= 90)
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
            selectedCycle = 0;
            anInt945 = l;
            anInt946 = k;
            selectedArea = 2;
            if (InterfaceComponent.instances[l].parent == viewportInterfaceIndex)
                selectedArea = 1;
            if (InterfaceComponent.instances[l].parent == chatbackComponentId)
                selectedArea = 3;
        }
        if (i1 == 728 || i1 == 542 || i1 == 6 || i1 == 963 || i1 == 245) {
            NpcEntity npcEntity = npcEntities[j1];
            if (npcEntity != null) {
                moveTo(self.pathTileX[0], 1, false,
                    npcEntity.pathTileX[0],
                    self.pathTileZ[0], 2, 1,
                    npcEntity.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
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
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossCycle = 0;
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
            addMessage(0, s9, "");
        }
        if (i1 == 285)
            interactWithLoc(245, k, l, j1);
        if (i1 == 881) {
            outBuffer.writeOpcode(130);
            outBuffer.writeWord(j1);
            outBuffer.writeWord(k);
            outBuffer.writeWord(l);
            outBuffer.writeWord(anInt1005);
            outBuffer.writeWord(anInt1003);
            outBuffer.writeWord(anInt1004);
            selectedCycle = 0;
            anInt945 = l;
            anInt946 = k;
            selectedArea = 2;
            if (InterfaceComponent.instances[l].parent == viewportInterfaceIndex)
                selectedArea = 1;
            if (InterfaceComponent.instances[l].parent == chatbackComponentId)
                selectedArea = 3;
        }
        if (i1 == 391) {
            outBuffer.writeOpcode(48);
            outBuffer.writeWord(j1);
            outBuffer.writeWord(k);
            outBuffer.writeWord(l);
            outBuffer.writeWord(anInt1026);
            selectedCycle = 0;
            anInt945 = l;
            anInt946 = k;
            selectedArea = 2;
            if (InterfaceComponent.instances[l].parent == viewportInterfaceIndex)
                selectedArea = 1;
            if (InterfaceComponent.instances[l].parent == chatbackComponentId)
                selectedArea = 3;
        }
        if (i1 == 660)
            if (!menuVisible)
                scene.setClick(4, super.clickY - 11, super.clickX - 8);
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
        if (i1 == 44 && !chatContinuingDialogue) {
            outBuffer.writeOpcode(235);
            outBuffer.writeWord(l);
            chatContinuingDialogue = true;
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
            addMessage(0, s4, "");
        }
        if (i1 == 900) {
            NpcEntity npcEntity_1 = npcEntities[j1];
            if (npcEntity_1 != null) {
                moveTo(self.pathTileX[0], 1, false,
                    npcEntity_1.pathTileX[0],
                    self.pathTileZ[0], 2, 1,
                    npcEntity_1.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
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
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
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
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
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
                    redrawChatback = true;
                    chatbackInputType = false;
                    showSocialInput = true;
                    socialInput = "";
                    socialAction = 3;
                    aLong900 = friendName37[i4];
                    socialMessage = "Enter message to send to " + friendName[i4];
                }
            }
        }
        if (i1 == 55 && interactWithLoc(9, k, l, j1))
            outBuffer.writeWord(anInt1026);
        if (i1 == 224 || i1 == 993 || i1 == 99 || i1 == 746 || i1 == 877) {
            boolean flag1 = moveTo(self.pathTileX[0], 0, false,
                k, self.pathTileZ[0], 2, 0, l, 0, 0, 0);
            if (!flag1)
                flag1 = moveTo(self.pathTileX[0], 1, false, k,
                    self.pathTileZ[0], 2, 1, l, 0, 0, 0);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossCycle = 0;
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
                addMessage(0, s5, "");
            }
        }
        if (i1 == 504)
            interactWithLoc(172, k, l, j1);
        if (i1 == 930) {
            InterfaceComponent interfaceComponent = InterfaceComponent.instances[l];
            selectedSpell = 1;
            anInt1026 = l;
            selectedFlags = interfaceComponent.optionFlags;
            selectedObject = 0;
            String s6 = interfaceComponent.optionCircumfix;
            if (s6.indexOf(" ") != -1)
                s6 = s6.substring(0, s6.indexOf(" "));
            String s10 = interfaceComponent.optionCircumfix;
            if (s10.indexOf(" ") != -1)
                s10 = s10.substring(s10.indexOf(" ") + 1);
            selectedSpellPrefix = s6 + " " + interfaceComponent.optionSuffix + " " + s10;
            if (selectedFlags == 16) {
                sidebarRedraw = true;
                selectedTab = 3;
                sidebarRedrawIcons = true;
            }
            return;
        }
        if (i1 == 951) {
            InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[l];
            boolean flag3 = true;
            if (interfaceComponent_1.contentType > 0)
                flag3 = handleComponentAction(interfaceComponent_1);
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
            selectedCycle = 0;
            anInt945 = l;
            anInt946 = k;
            selectedArea = 2;
            if (InterfaceComponent.instances[l].parent == viewportInterfaceIndex)
                selectedArea = 1;
            if (InterfaceComponent.instances[l].parent == chatbackComponentId)
                selectedArea = 3;
        }
        if (i1 == 581) {
            if ((j1 & 3) == 0)
                anInt772++;
            if (anInt772 >= 99) {
                outBuffer.writeOpcode(7);
                outBuffer.writeDWord(0);
            }
            interactWithLoc(97, k, l, j1);
        }
        if (i1 == 965) {
            boolean flag2 = moveTo(self.pathTileX[0], 0, false,
                k, self.pathTileZ[0], 2, 0, l, 0, 0, 0);
            if (!flag2)
                flag2 = moveTo(self.pathTileX[0], 1, false, k,
                    self.pathTileZ[0], 2, 1, l, 0, 0, 0);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossCycle = 0;
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
            interactWithLoc(116, k, l, j1);
        }
        if (i1 == 364)
            interactWithLoc(96, k, l, j1);
        if (i1 == 1102) {
            ObjType objType_1 = ObjType.get(j1);
            String s7;
            if (objType_1.description != null)
                s7 = new String(objType_1.description);
            else
                s7 = "It's a " + objType_1.name + ".";
            addMessage(0, s7, "");
        }
        if (i1 == 960) {
            outBuffer.writeOpcode(155);
            outBuffer.writeWord(l);
            InterfaceComponent interfaceComponent_2 = InterfaceComponent.instances[l];
            if (interfaceComponent_2.script != null && interfaceComponent_2.script[0][0] == 5) {
                int j2 = interfaceComponent_2.script[0][1];
                if (variables[j2] != interfaceComponent_2.scriptCompareValue[0]) {
                    variables[j2] = interfaceComponent_2.scriptCompareValue[0];
                    updateVarp(j2);
                    sidebarRedraw = true;
                }
            }
        }
        if (i1 == 34) {
            String s2 = options[j];
            int k2 = s2.indexOf("@whi@");
            if (k2 != -1) {
                closeInterface();
                aString970 = s2.substring(k2 + 5).trim();
                aBoolean881 = false;
                for (int j3 = 0; j3 < InterfaceComponent.instances.length; j3++) {
                    if (InterfaceComponent.instances[j3] == null || InterfaceComponent.instances[j3].contentType != 600)
                        continue;
                    anInt907 = viewportInterfaceIndex = InterfaceComponent.instances[j3].parent;
                    break;
                }

            }
        }
        if (i1 == 947)
            closeInterface();
        if (i1 == 367) {
            PlayerEntity playerEntity_1 = playerEntities[j1];
            if (playerEntity_1 != null) {
                moveTo(self.pathTileX[0], 1, false,
                    playerEntity_1.pathTileX[0],
                    self.pathTileZ[0], 2, 1,
                    playerEntity_1.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
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
            if (interfaceComponent_3.script != null && interfaceComponent_3.script[0][0] == 5) {
                int l2 = interfaceComponent_3.script[0][1];
                variables[l2] = 1 - variables[l2];
                updateVarp(l2);
                sidebarRedraw = true;
            }
        }
        if (i1 == 406 || i1 == 436 || i1 == 557 || i1 == 556) {
            String s3 = options[j];
            int i3 = s3.indexOf("@whi@");
            if (i3 != -1) {
                long l4 = StringUtils.toBase37(s3.substring(i3 + 5).trim());
                if (i1 == 406)
                    addFriend(l4);
                if (i1 == 436)
                    addIgnore(l4);
                if (i1 == 557)
                    removeFriend(l4);
                if (i1 == 556)
                    removeIgnore(l4);
            }
        }
        if (i1 == 651) {
            PlayerEntity playerEntity = playerEntities[j1];
            if (playerEntity != null) {
                moveTo(self.pathTileX[0], 1, false,
                    playerEntity.pathTileX[0],
                    self.pathTileZ[0], 2, 1,
                    playerEntity.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
                outBuffer.writeOpcode(177);
                outBuffer.writeWord(j1);
                outBuffer.writeWord(anInt1026);
            }
        }
        selectedObject = 0;
        selectedSpell = 0;
    }

    public static String getLevelColorTag(int i, int j) {
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

    public String getHost() {
        if (Signlink.mainapp != null)
            return Signlink.mainapp.getDocumentBase().getHost().toLowerCase();
        if (super.frame != null)
            return "runescape.com";
        else
            return super.getDocumentBase().getHost().toLowerCase();
    }

    public void drawMenu() {
        int j = menuX;
        int k = menuY;
        int l = menuWidth;
        int i1 = menuHeight;
        int j1 = 0x5d5447;
        Draw2D.fillRect(k, j, j1, l, i1);
        Draw2D.fillRect(k + 1, j + 1, 0, l - 2, 16);
        Draw2D.drawRect(j + 1, 0, i1 - 19, k + 18, l - 2);
        fontBold12.draw(j + 3, k + 14, j1, "Choose Option");
        int k1 = super.mouseX;
        int l1 = super.mouseY;
        if (mouseArea == 0) {
            k1 -= 8;
            l1 -= 11;
        }
        if (mouseArea == 1) {
            k1 -= 562;
            l1 -= 231;
        }
        if (mouseArea == 2) {
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
    }

    public void handleChatInput(int k) {
        if (anInt833 == 0)
            return;
        int l = 0;
        if (systemUpdateTimer != 0)
            l = 1;
        for (int i1 = 0; i1 < 100; i1++)
            if (chatMessage[i1] != null) {
                int j1 = chatMessageType[i1];
                if ((j1 == 3 || j1 == 7)
                    && (j1 == 7 || chatPrivateSetting == 0 || chatPrivateSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                    int k1 = 329 - l * 13;
                    if (super.mouseX > 8 && super.mouseX < 520 && k - 11 > k1 - 10 && k - 11 <= k1 + 3) {
                        if (rights) {
                            options[optionCount] = "Report abuse @whi@" + chatMessagePrefix[i1];
                            actions[optionCount] = 2034;
                            optionCount++;
                        }
                        options[optionCount] = "Add ignore @whi@" + chatMessagePrefix[i1];
                        actions[optionCount] = 2436;
                        optionCount++;
                        options[optionCount] = "Add friend @whi@" + chatMessagePrefix[i1];
                        actions[optionCount] = 2406;
                        optionCount++;
                    }
                    if (++l >= 5)
                        return;
                }
                if ((j1 == 5 || j1 == 6) && chatPrivateSetting < 2 && ++l >= 5)
                    return;
            }
    }

    public void updateComponentContent(InterfaceComponent interfaceComponent) {
        int j = interfaceComponent.contentType;
        if (j >= 1 && j <= 100)
            if (--j >= friendCount) {
                interfaceComponent.text = "";
                interfaceComponent.buttonType = 0;
                return;
            } else {
                interfaceComponent.text = friendName[j];
                interfaceComponent.buttonType = 1;
                return;
            }
        if (j >= 101 && j <= 200) {
            if ((j -= 101) >= friendCount) {
                interfaceComponent.text = "";
                interfaceComponent.buttonType = 0;
                return;
            }
            if (friendWorld[j] == 0)
                interfaceComponent.text = "@red@Offline";
            else if (friendWorld[j] == nodeid)
                interfaceComponent.text = "@gre@World-" + (friendWorld[j] - 9);
            else
                interfaceComponent.text = "@yel@World-" + (friendWorld[j] - 9);
            interfaceComponent.buttonType = 1;
            return;
        }
        if (j == 203) {
            interfaceComponent.scrollHeight = friendCount * 15 + 20;
            if (interfaceComponent.scrollHeight <= interfaceComponent.height)
                interfaceComponent.scrollHeight = interfaceComponent.height + 1;
            return;
        }
        if (j >= 401 && j <= 500)
            if ((j -= 401) >= ignoreCount) {
                interfaceComponent.text = "";
                interfaceComponent.buttonType = 0;
                return;
            } else {
                interfaceComponent.text = StringUtils.formatName(StringUtils.fromBase37(ignoreName37[j]));
                interfaceComponent.buttonType = 1;
                return;
            }
        if (j == 503) {
            interfaceComponent.scrollHeight = ignoreCount * 15 + 20;
            if (interfaceComponent.scrollHeight <= interfaceComponent.height)
                interfaceComponent.scrollHeight = interfaceComponent.height + 1;
            return;
        }
        if (j == 327) {
            interfaceComponent.modelEyePitch = 150;
            interfaceComponent.modelYaw = (int) (Math.sin((double) clientClock / 40D) * 256D) & 0x7ff;
            if (characterDesignUpdate) {
                characterDesignUpdate = false;
                Model[] aclass38_sub2_sub1 = new Model[7];
                int k = 0;
                for (int l = 0; l < 7; l++) {
                    int i1 = characterDesigns[l];
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
                interfaceComponent.modelDisabled = class38_sub2_sub1;
            }
            return;
        }
        if (j == 324) {
            if (aClass38_Sub2_Sub2_Sub2_961 == null) {
                aClass38_Sub2_Sub2_Sub2_961 = interfaceComponent.image;
                aClass38_Sub2_Sub2_Sub2_962 = interfaceComponent.activeImage;
            }
            if (characterDesignIsMale) {
                interfaceComponent.image = aClass38_Sub2_Sub2_Sub2_962;
                return;
            } else {
                interfaceComponent.image = aClass38_Sub2_Sub2_Sub2_961;
                return;
            }
        }
        if (j == 325) {
            if (aClass38_Sub2_Sub2_Sub2_961 == null) {
                aClass38_Sub2_Sub2_Sub2_961 = interfaceComponent.image;
                aClass38_Sub2_Sub2_Sub2_962 = interfaceComponent.activeImage;
            }
            if (characterDesignIsMale) {
                interfaceComponent.image = aClass38_Sub2_Sub2_Sub2_961;
                return;
            } else {
                interfaceComponent.image = aClass38_Sub2_Sub2_Sub2_962;
                return;
            }
        }
        if (j == 600) {
            interfaceComponent.text = aString970;
            if (clientClock % 20 < 10) {
                interfaceComponent.text += "|";
                return;
            } else {
                interfaceComponent.text += " ";
                return;
            }
        }
        if (j == 613)
            if (rights) {
                if (aBoolean881) {
                    interfaceComponent.color = 0xff0000;
                    interfaceComponent.text = "Moderator option: Mute player for 48 hours: <ON>";
                } else {
                    interfaceComponent.color = 0xffffff;
                    interfaceComponent.text = "Moderator option: Mute player for 48 hours: <OFF>";
                }
            } else {
                interfaceComponent.text = "";
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
                interfaceComponent.text = "You last logged in " + s + " from: " + Signlink.dns;
            } else {
                interfaceComponent.text = "";
            }
        if (j == 651) {
            if (anInt1054 == 0) {
                interfaceComponent.text = "0 unread messages";
                interfaceComponent.color = 0xffff00;
            }
            if (anInt1054 == 1) {
                interfaceComponent.text = "1 unread message";
                interfaceComponent.color = 65280;
            }
            if (anInt1054 > 1) {
                interfaceComponent.text = anInt1054 + " unread messages";
                interfaceComponent.color = 65280;
            }
        }
        if (j == 652)
            if (anInt901 == 201)
                interfaceComponent.text = "";
            else if (anInt901 == 200) {
                interfaceComponent.text = "You have not yet set any password recovery questions.";
            } else {
                String s1;
                if (anInt901 == 0)
                    s1 = "Earlier today";
                else if (anInt901 == 1)
                    s1 = "Yesterday";
                else
                    s1 = anInt901 + " days ago";
                interfaceComponent.text = s1 + " you changed your recovery questions";
            }
        if (j == 653)
            if (anInt901 == 201)
                interfaceComponent.text = "";
            else if (anInt901 == 200)
                interfaceComponent.text = "We strongly recommend you do so now to secure your account.";
            else
                interfaceComponent.text = "If you do not remember making this change then cancel it immediately";
        if (j == 654) {
            if (anInt901 == 201) {
                interfaceComponent.text = "";
                return;
            }
            if (anInt901 == 200) {
                interfaceComponent.text = "Do this from the 'account management' area on our front webpage";
                return;
            }
            interfaceComponent.text = "Do this from the 'account management' area on our front webpage";
        }
    }

    public boolean wavesave(byte[] abyte0, int i) {
        if (abyte0 == null)
            return true;
        else
            return Signlink.wavesave(abyte0, i);
    }

    public boolean wavereplay() {
        return Signlink.wavereplay();
    }

    public void wavevol(int i) {
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

    public boolean handleComponentAction(InterfaceComponent interfaceComponent) {
        int type = interfaceComponent.contentType;
        if (type == 201) {
            redrawChatback = true;
            chatbackInputType = false;
            showSocialInput = true;
            socialInput = "";
            socialAction = 1;
            socialMessage = "Enter name of friend to add to list";
        }
        if (type == 202) {
            redrawChatback = true;
            chatbackInputType = false;
            showSocialInput = true;
            socialInput = "";
            socialAction = 2;
            socialMessage = "Enter name of friend to delete from list";
        }
        if (type == 205) {
            idleTimeout = 250;
            return true;
        }
        if (type == 501) {
            redrawChatback = true;
            chatbackInputType = false;
            showSocialInput = true;
            socialInput = "";
            socialAction = 4;
            socialMessage = "Enter name of player to add to list";
        }
        if (type == 502) {
            redrawChatback = true;
            chatbackInputType = false;
            showSocialInput = true;
            socialInput = "";
            socialAction = 5;
            socialMessage = "Enter name of player to delete from list";
        }
        if (type >= 300 && type <= 313) {
            int j = (type - 300) / 2;
            int i1 = type & 1;
            int l1 = characterDesigns[j];
            if (l1 != -1) {
                do {
                    if (i1 == 0 && --l1 < 0)
                        l1 = IdkType.count - 1;
                    if (i1 == 1 && ++l1 >= IdkType.count)
                        l1 = 0;
                } while (IdkType.instances[l1].validStyle
                    || IdkType.instances[l1].type != j + (characterDesignIsMale ? 0 : 7));
                characterDesigns[j] = l1;
                characterDesignUpdate = true;
            }
        }
        if (type >= 314 && type <= 323) {
            int k = (type - 314) / 2;
            int j1 = type & 1;
            int i2 = characterDesignColors[k];
            if (j1 == 0 && --i2 < 0)
                i2 = APPEARANCE_COLORS[k].length - 1;
            if (j1 == 1 && ++i2 >= APPEARANCE_COLORS[k].length)
                i2 = 0;
            characterDesignColors[k] = i2;
            characterDesignUpdate = true;
        }
        if (type == 324 && !characterDesignIsMale) {
            characterDesignIsMale = true;
            resetCharacterDesign();
        }
        if (type == 325 && characterDesignIsMale) {
            characterDesignIsMale = false;
            resetCharacterDesign();
        }
        if (type == 326) {
            outBuffer.writeOpcode(52);
            outBuffer.writeByte(characterDesignIsMale ? 0 : 1);
            for (int l = 0; l < 7; l++)
                outBuffer.writeByte(characterDesigns[l]);

            for (int k1 = 0; k1 < 5; k1++)
                outBuffer.writeByte(characterDesignColors[k1]);

            return true;
        }
        if (type == 613)
            aBoolean881 = !aBoolean881;
        if (type >= 601 && type <= 612) {
            closeInterface();
            if (aString970.length() > 0) {
                outBuffer.writeOpcode(190);
                outBuffer.writeQWord(StringUtils.toBase37(aString970));
                outBuffer.writeByte(type - 601);
                outBuffer.writeByte(aBoolean881 ? 1 : 0);
            }
        }
        return false;
    }

    public void load() {
        if (Signlink.sunjava)
            super.anInt9 = 5;
        if (!lowMemory) {
            aBoolean799 = true;
            aBoolean812 = true;
            startThread(this, 2);
            setMidi(0xbc614e, "scape_main", 40000);
        }
        if (aBoolean1102) {
            errorStarted = true;
            return;
        }
        aBoolean1102 = true;
        boolean flag = false;
        String s = getHost();
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
            errorHost = true;
            return;
        }
        try {
            int i = 5;
            for (archiveChecksums[8] = 0; archiveChecksums[8] == 0; ) {
                showProgress(true, "Connecting to fileserver", 10);
                try {
                    DataInputStream datainputstream = openStream("crc" + (int) (Math.random() * 99999999D));
                    Buffer class38_sub2_sub3 = new Buffer(new byte[36]);
                    datainputstream.readFully(class38_sub2_sub3.data, 0, 36);
                    for (int k = 0; k < 9; k++) {
                        archiveChecksums[k] = class38_sub2_sub3.readDWord();
                    }

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
                    if (i > 60) {
                        i = 60;
                    }
                }
            }

            this.titleArchive = loadArchive("title screen", archiveChecksums[1], "title", 10);

            fontPlain11 = new IndexedFont(this.titleArchive, "p11", 530);
            fontPlain12 = new IndexedFont(this.titleArchive, "p12", 530);
            fontBold12 = new IndexedFont(this.titleArchive, "b12", 530);
            fontQuill8 = new IndexedFont(this.titleArchive, "q8", 530);

            loadTitleBackground();
            loadTitleForeground();

            FileArchive configArchive = loadArchive("config", archiveChecksums[2], "config", 15);
            FileArchive interfaceArchive = loadArchive("interface", archiveChecksums[3], "interface", 20);
            FileArchive mediaArchive = loadArchive("2d graphics", archiveChecksums[4], "media", 30);
            FileArchive modelsArchive = loadArchive("3d graphics", archiveChecksums[5], "models", 40);
            FileArchive texturesArchive = loadArchive("textures", archiveChecksums[6], "textures", 60);
            FileArchive wordencArchive = loadArchive("chat system", archiveChecksums[7], "wordenc", 65);
            FileArchive soundsArchive = loadArchive("sound effects", archiveChecksums[8], "sounds", 70);

            levelRenderFlags = new byte[4][104][104];
            levelHeightMaps = new int[4][105][105];
            scene = new Scene(levelHeightMaps, 104, 4, 104);
            for (int l = 0; l < 4; l++) {
                collisionMaps[l] = new CollisionMap(104, 104);
            }
            minimap = new Sprite(512, 512);

            showProgress(true, "Unpacking media", 75);
            inback = new IndexedSprite(mediaArchive, "invback", 0);
            chatback = new IndexedSprite(mediaArchive, "chatback", 0);
            mapback = new IndexedSprite(mediaArchive, "mapback", 0);
            backbase1 = new IndexedSprite(mediaArchive, "backbase1", 0);
            backbase2 = new IndexedSprite(mediaArchive, "backbase2", 0);
            backhmid1 = new IndexedSprite(mediaArchive, "backhmid1", 0);
            for (int i1 = 0; i1 < 13; i1++) {
                sideicons[i1] = new IndexedSprite(mediaArchive, "sideicons", i1);
            }

            compass = new Sprite(mediaArchive, "compass", 0);
            try {
                for (int j1 = 0; j1 < 50; j1++)
                    mapscene[j1] = new IndexedSprite(mediaArchive, "mapscene", j1);
            } catch (Exception _ex) {
            }
            try {
                for (int k1 = 0; k1 < 50; k1++)
                    mapfunction[k1] = new Sprite(mediaArchive, "mapfunction", k1);
            } catch (Exception _ex) {
            }
            try {
                for (int l1 = 0; l1 < 20; l1++)
                    hitmarks[l1] = new Sprite(mediaArchive, "hitmarks", l1);
            } catch (Exception _ex) {
            }
            try {
                for (int i2 = 0; i2 < 20; i2++)
                    headicons[i2] = new Sprite(mediaArchive, "headicons", i2);
            } catch (Exception _ex) {
            }

            mapflag = new Sprite(mediaArchive, "mapflag", 0);
            for (int j2 = 0; j2 < 8; j2++) {
                cross[j2] = new Sprite(mediaArchive, "cross", j2);
            }

            mapdot1 = new Sprite(mediaArchive, "mapdots", 0);
            mapdot2 = new Sprite(mediaArchive, "mapdots", 1);
            mapdot3 = new Sprite(mediaArchive, "mapdots", 2);
            mapdot4 = new Sprite(mediaArchive, "mapdots", 3);
            scrollbar1 = new IndexedSprite(mediaArchive, "scrollbar", 0);
            scrollbar2 = new IndexedSprite(mediaArchive, "scrollbar", 1);
            redstone1 = new IndexedSprite(mediaArchive, "redstone1", 0);
            redstone2 = new IndexedSprite(mediaArchive, "redstone2", 0);
            redstone3 = new IndexedSprite(mediaArchive, "redstone3", 0);

            redstone1h = new IndexedSprite(mediaArchive, "redstone1", 0);
            redstone1h.flipHorizontally();

            redstone2h = new IndexedSprite(mediaArchive, "redstone2", 0);
            redstone2h.flipHorizontally();

            redstone1v = new IndexedSprite(mediaArchive, "redstone1", 0);
            redstone1v.flipVertically();

            redstone2v = new IndexedSprite(mediaArchive, "redstone2", 0);
            redstone2v.flipVertically();

            redstone3v = new IndexedSprite(mediaArchive, "redstone3", 0);
            redstone3v.flipVertically();

            redstone1hv = new IndexedSprite(mediaArchive, "redstone1", 0);
            redstone1hv.flipHorizontally();
            redstone1hv.flipVertically();

            redstone2hv = new IndexedSprite(mediaArchive, "redstone2", 0);
            redstone2hv.flipHorizontally();
            redstone2hv.flipVertically();

            Sprite temp = new Sprite(mediaArchive, "backleft1", 0);
            backleft1 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backleft2", 0);
            backleft2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backright1", 0);
            backright1 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backright2", 0);
            backright2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backtop1", 0);
            backtop1 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backtop2", 0);
            backtop2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backvmid1", 0);
            backvmid1 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backvmid2", 0);
            backvmid2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backvmid3", 0);
            backvmid3 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backhmid2", 0);
            backhmid2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            int k2 = (int) (Math.random() * 21D) - 10;
            int l2 = (int) (Math.random() * 21D) - 10;
            int i3 = (int) (Math.random() * 21D) - 10;
            int j3 = (int) (Math.random() * 41D) - 20;
            for (int k3 = 0; k3 < 50; k3++) {
                if (mapfunction[k3] != null)
                    mapfunction[k3].translate(k2 + j3, l2 + j3, i3 + j3);
                if (mapscene[k3] != null)
                    mapscene[k3].translate(k2 + j3, l2 + j3, i3 + j3);
            }

            showProgress(true, "Unpacking textures", 80);
            Draw3D.unpackTextures(texturesArchive);
            Draw3D.setBrightness(0.80000000000000004D);
            Draw3D.setupPools(20);
            showProgress(true, "Unpacking models", 83);
            Model.load(modelsArchive);
            SeqBase.load(modelsArchive);
            SeqFrame.load(modelsArchive);
            showProgress(true, "Unpacking config", 86);
            SeqType.load(configArchive);
            LocType.load(configArchive);
            FloType.load(configArchive);
            ObjType.load(configArchive);
            NpcType.load(configArchive);
            IdkType.load(configArchive);
            SpotAnimType.load(configArchive);
            VarpType.load(configArchive);
            ObjType.isMember = members;

            if (!lowMemory) {
                showProgress(true, "Unpacking sounds", 90);
                byte[] abyte0 = soundsArchive.read("sounds.dat", null);
                Buffer sounds = new Buffer(abyte0);
                SoundTrack.load(sounds);
            }

            showProgress(true, "Unpacking interfaces", 92);
            IndexedFont[] fonts = {fontPlain11, fontPlain12, fontBold12, fontQuill8};
            InterfaceComponent.load(mediaArchive, fonts, 30, interfaceArchive);
            showProgress(true, "Preparing game engine", 97);
            for (int l3 = 0; l3 < 33; l3++) {
                int i4 = 999;
                int k4 = 0;
                for (int i5 = 0; i5 < 35; i5++) {
                    if (mapback.pixels[i5
                        + l3 * mapback.width] == 0) {
                        if (i4 == 999)
                            i4 = i5;
                        continue;
                    }
                    if (i4 == 999)
                        continue;
                    k4 = i5;
                    break;
                }

                compassLeft[l3] = i4;
                compassLineWidth[l3] = k4 - i4;
            }

            for (int j4 = 9; j4 < 160; j4++) {
                int l4 = 999;
                int j5 = 0;
                for (int l5 = 10; l5 < 168; l5++) {
                    if (mapback.pixels[l5 + j4 * mapback.width] == 0
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

                minimapLeft[j4 - 9] = l4 - 21;
                minimapLineWidth[j4 - 9] = j5 - l4;
            }

            Draw3D.prepareOffsets(96, 479);
            chatOffsets = Draw3D.offsets;

            Draw3D.prepareOffsets(261, 190);
            sidebarOffsets = Draw3D.offsets;

            Draw3D.prepareOffsets(334, 512);
            viewportOffsets = Draw3D.offsets;

            int[] ai = new int[9];
            for (int k5 = 0; k5 < 9; k5++) {
                int i6 = 128 + k5 * 32 + 15;
                int j6 = 600 + i6 * 3;
                int k6 = Draw3D.sin[i6];
                ai[k5] = j6 * k6 >> 16;
            }

            Scene.init(ai, 800, 512, 334, 500);
            WordEncoding.load(wordencArchive);
        } catch (Exception exception) {
            errorLoading = true;
        }
    }

    public void updateInput() {
        if (objDragArea != 0)
            return;
        options[0] = "Cancel";
        actions[0] = 1252;
        optionCount = 1;
        handleChatInput(super.mouseY);
        hoveredInterfaceIndex = 0;
        if (super.mouseX > 8 && super.mouseY > 11 && super.mouseX < 520 && super.mouseY < 345)
            if (viewportInterfaceIndex != -1)
                updateInterface(super.mouseY, super.mouseX, 11, InterfaceComponent.instances[viewportInterfaceIndex], 8, 0);
            else
                updateViewport();
        if (hoveredInterfaceIndex != viewportHoveredInterfaceIndex)
            viewportHoveredInterfaceIndex = hoveredInterfaceIndex;
        hoveredInterfaceIndex = 0;
        if (super.mouseX > 562 && super.mouseY > 231 && super.mouseX < 752 && super.mouseY < 492)
            if (sidebarInterfaceId != -1)
                updateInterface(super.mouseY, super.mouseX, 231, InterfaceComponent.instances[sidebarInterfaceId], 562, 0);
            else if (tabComponentId[selectedTab] != -1)
                updateInterface(super.mouseY, super.mouseX, 231, InterfaceComponent.instances[tabComponentId[selectedTab]], 562, 0);
        if (hoveredInterfaceIndex != sidebarHoveredInterfaceIndex) {
            sidebarRedraw = true;
            sidebarHoveredInterfaceIndex = hoveredInterfaceIndex;
        }
        hoveredInterfaceIndex = 0;
        if (super.mouseX > 22 && super.mouseY > 375 && super.mouseX < 431 && super.mouseY < 471)
            if (chatbackComponentId != -1)
                updateInterface(super.mouseY, super.mouseX, 375, InterfaceComponent.instances[chatbackComponentId], 22, 0);
            else
                updatePlayerTooltip(super.mouseY - 375);
        if (chatbackComponentId != -1 && hoveredInterfaceIndex != chatHoveredInterfaceIndex) {
            redrawChatback = true;
            chatHoveredInterfaceIndex = hoveredInterfaceIndex;
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

    public void clearCaches() {
        LocType.models.clear();
        LocType.builtModels.clear();
        NpcType.models.clear();
        ObjType.modelCache.clear();
        ObjType.iconCache.clear();
        PlayerEntity.models.clear();
        SpotAnimType.models.clear();
    }

    public void drawViewport2() {
        drawChat();
        if (crossType == 1)
            cross[crossCycle / 100].draw(crossY - 8 - 11, crossX - 8 - 8);
        if (crossType == 2)
            cross[4 + crossCycle / 100].draw(crossY - 8 - 11, crossX - 8 - 8);
        if (viewportInterfaceIndex != -1) {
            animateInterface(viewportInterfaceIndex, sceneDelta);
            drawInterface(0, 0, InterfaceComponent.instances[viewportInterfaceIndex], 0);
        }
        drawWildyLevel();
        if (!menuVisible) {
            updateInput();
            drawTooltip();
        } else if (mouseArea == 0)
            drawMenu();
        if (inMultizone == 1)
            if (wildernessLevel > 0 || anInt933 == 1)
                headicons[1].draw(258, 472);
            else
                headicons[1].draw(296, 472);
        if (wildernessLevel > 0) {
            headicons[0].draw(296, 472);
            fontPlain12.drawRightAligned(329, 0xffff00, "Level: " + wildernessLevel, 484);
        }
        if (anInt933 == 1) {
            headicons[6].draw(296, 472);
            fontPlain12.drawRightAligned(329, 0xffff00, "Arena", 484);
        }
        if (systemUpdateTimer != 0) {
            int k = systemUpdateTimer / 50;
            int l = k / 60;
            k %= 60;
            if (k < 10) {
                fontPlain12.draw(4, 329, 0xffff00, "System update in: " + l + ":0" + k);
                return;
            }
            fontPlain12.draw(4, 329, 0xffff00, "System update in: " + l + ":" + k);
        }
    }

    public void updateOrbitCamera() {
        int j = self.x + cameraAnticheatOffsetX;
        int k = self.z + cameraAnticheatOffsetZ;
        if (cameraOrbitX - j < -500 || cameraOrbitX - j > 500 || cameraOrbitZ - k < -500 || cameraOrbitZ - k > 500) {
            cameraOrbitX = j;
            cameraOrbitZ = k;
        }
        if (cameraOrbitX != j)
            cameraOrbitX += (j - cameraOrbitX) / 16;
        if (cameraOrbitZ != k)
            cameraOrbitZ += (k - cameraOrbitZ) / 16;
        if (super.keyDown[1] == 1)
            cameraYawModifier += (-24 - cameraYawModifier) / 2;
        else if (super.keyDown[2] == 1)
            cameraYawModifier += (24 - cameraYawModifier) / 2;
        else
            cameraYawModifier /= 2;
        if (super.keyDown[3] == 1)
            cameraPitchModifier += (12 - cameraPitchModifier) / 2;
        else if (super.keyDown[4] == 1)
            cameraPitchModifier += (-12 - cameraPitchModifier) / 2;
        else
            cameraPitchModifier /= 2;
        cameraYaw = cameraYaw + cameraYawModifier / 2 & 0x7ff;
        cameraOrbitPitch += cameraPitchModifier / 2;
        if (cameraOrbitPitch < 128)
            cameraOrbitPitch = 128;
        if (cameraOrbitPitch > 383)
            cameraOrbitPitch = 383;
        int l = cameraOrbitX >> 7;
        int i1 = cameraOrbitZ >> 7;
        int j1 = getLandY(currentLevel, cameraOrbitX, cameraOrbitZ);
        int k1 = 0;
        if (l > 3 && i1 > 3 && l < 100 && i1 < 100) {
            for (int l1 = l - 4; l1 <= l + 4; l1++) {
                for (int j2 = i1 - 4; j2 <= i1 + 4; j2++) {
                    int k2 = currentLevel;
                    if (k2 < 3 && (levelRenderFlags[1][l1][j2] & 2) == 2)
                        k2++;
                    int l2 = j1 - levelHeightMaps[k2][l1][j2];
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
        if (i2 > cameraMaxY) {
            cameraMaxY += (i2 - cameraMaxY) / 24;
        } else if (i2 < cameraMaxY) {
            cameraMaxY += (i2 - cameraMaxY) / 80;
        }
    }

    public void updateSceneProjectiles() {
        for (ProjectileEntity entity = (ProjectileEntity) projectiles
            .peekLast(); entity != null; entity = (ProjectileEntity) projectiles
            .getPrevious())
            if (entity.level != currentLevel || clientClock > entity.lastCycle)
                entity.unlink();
            else if (clientClock >= entity.firstCycle) {
                if (entity.targetIndex > 0) {
                    NpcEntity npcEntity = npcEntities[entity.targetIndex
                        - 1];
                    if (npcEntity != null)
                        entity.setTarget(
                            getLandY(entity.level,
                                npcEntity.x,
                                npcEntity.z)
                                - entity.baseZ,
                            npcEntity.z,
                            npcEntity.x, clientClock);
                }
                if (entity.targetIndex < 0) {
                    int i = -entity.targetIndex - 1;
                    PlayerEntity playerEntity;
                    if (i == anInt734)
                        playerEntity = self;
                    else
                        playerEntity = playerEntities[i];
                    if (playerEntity != null)
                        entity.setTarget(
                            getLandY(entity.level,
                                playerEntity.x,
                                playerEntity.z)
                                - entity.baseZ,
                            playerEntity.z,
                            playerEntity.x, clientClock);
                }
                entity.update(sceneDelta);
                scene.add((int) entity.y, 60, entity.yaw,
                    (int) entity.x, -1, false, null, entity,
                    (int) entity.z, currentLevel);
            }

    }

    public void refresh() {
        redrawTitleBackground = true;
    }

    public void drawOnMinimap(int dy, Sprite image, int dx) {
        int l = cameraYaw + minimapAnticheatAngle & 0x7ff;
        int i1 = dx * dx + dy * dy;
        if (i1 > 6400)
            return;
        int j1 = Model.sin[l];
        int k1 = Model.cos[l];
        j1 = (j1 * 256) / (minimapZoom + 256);
        k1 = (k1 * 256) / (minimapZoom + 256);
        int l1 = dy * j1 + dx * k1 >> 16;
        int i2 = dy * k1 - dx * j1 >> 16;
        if (i1 > 2500) {
            image.drawMasked(mapback,
                83 - i2 - image.cropH / 2, (94 + l1) - image.cropW / 2
            );
        } else {
            image.draw(83 - i2 - image.cropH / 2,
                (94 + l1) - image.cropW / 2);
        }
    }

    public int mix(int i, int j, int k) {
        int i1 = 256 - j;
        return ((i & 0xff00ff) * i1 + (k & 0xff00ff) * j & 0xff00ff00)
            + ((i & 0xff00) * i1 + (k & 0xff00) * j & 0xff0000) >> 8;
    }

    public String getIntString(int i) {
        if (i < 999999999)
            return String.valueOf(i);
        else
            return "*";
    }

    public void setDrawPos(int i, PathingEntity entity) {
        projectToScreen(entity.z, entity.x, i);
    }

    public void projectToScreen(int i, int j, int l) {
        if (j < 128 || i < 128 || j > 13056 || i > 13056) {
            drawX = -1;
            drawY = -1;
            return;
        }
        int i1 = getLandY(currentLevel, j, i) - l;
        j -= cameraX;
        i1 -= cameraY;
        i -= cameraZ;
        int j1 = Model.sin[cameraPitch];
        int k1 = Model.cos[cameraPitch];
        int l1 = Model.sin[cameraOrbitYaw];
        int i2 = Model.cos[cameraOrbitYaw];
        int j2 = i * l1 + j * i2 >> 16;
        i = i * i2 - j * l1 >> 16;
        j = j2;
        j2 = i1 * k1 - i * j1 >> 16;
        i = i1 * j1 + i * k1 >> 16;
        i1 = j2;
        if (i >= 50) {
            drawX = Draw3D.centerX + (j << 9) / i;
            drawY = Draw3D.centerY + (i1 << 9) / i;
        } else {
            drawX = -1;
            drawY = -1;
        }
    }

    public boolean interactWithLoc(int i, int j, int k, int l) {
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
        crossX = super.clickX;
        crossY = super.clickY;
        crossType = 2;
        crossCycle = 0;
        outBuffer.writeOpcode(i);
        outBuffer.writeWord(j + baseTileX);
        outBuffer.writeWord(k + baseTileZ);
        outBuffer.writeWord(i1);
        return true;
    }

    public void showContextMenu() {
        int j = fontBold12.stringWidth("Choose Option");
        for (int k = 0; k < optionCount; k++) {
            int l = fontBold12.stringWidth(options[k]);
            if (l > j)
                j = l;
        }

        j += 8;
        int i1 = 15 * optionCount + 21;
        if (super.clickX > 8 && super.clickY > 11 && super.clickX < 520 && super.clickY < 345) {
            int j1 = super.clickX - 8 - j / 2;
            if (j1 + j > 512)
                j1 = 512 - j;
            if (j1 < 0)
                j1 = 0;
            int i2 = super.clickY - 11;
            if (i2 + i1 > 334)
                i2 = 334 - i1;
            if (i2 < 0)
                i2 = 0;
            menuVisible = true;
            mouseArea = 0;
            menuX = j1;
            menuY = i2;
            menuWidth = j;
            menuHeight = 15 * optionCount + 22;
        }
        if (super.clickX > 562 && super.clickY > 231 && super.clickX < 752 && super.clickY < 492) {
            int k1 = super.clickX - 562 - j / 2;
            if (k1 < 0)
                k1 = 0;
            else if (k1 + j > 190)
                k1 = 190 - j;
            int j2 = super.clickY - 231;
            if (j2 < 0)
                j2 = 0;
            else if (j2 + i1 > 261)
                j2 = 261 - i1;
            menuVisible = true;
            mouseArea = 1;
            menuX = k1;
            menuY = j2;
            menuWidth = j;
            menuHeight = 15 * optionCount + 22;
        }
        if (super.clickX > 22 && super.clickY > 375 && super.clickX < 501 && super.clickY < 471) {
            int l1 = super.clickX - 22 - j / 2;
            if (l1 < 0)
                l1 = 0;
            else if (l1 + j > 479)
                l1 = 479 - j;
            int k2 = super.clickY - 375;
            if (k2 < 0)
                k2 = 0;
            else if (k2 + i1 > 96)
                k2 = 96 - i1;
            menuVisible = true;
            mouseArea = 2;
            menuX = l1;
            menuY = k2;
            menuWidth = j;
            menuHeight = 15 * optionCount + 22;
        }
    }

    public DataInputStream openStream(String s) throws IOException {
        if (Signlink.mainapp != null)
            return Signlink.openurl(s);
        else
            return new DataInputStream((new URL(getCodeBase(), s)).openStream());
    }

    public void prepareTitleScreen() {
        if (titleTop != null)
            return;
        super.drawArea = null;
        areaChatback = null;
        areaMapback = null;
        areaInvback = null;
        areaViewport = null;
        areaBackbase1 = null;
        areaBackbase2 = null;
        areaBackhmid1 = null;

        titleLeft = new DrawArea(getBaseComponent(), 128, 265);
        Draw2D.clear();

        titleRight = new DrawArea(getBaseComponent(), 128, 265);
        Draw2D.clear();

        titleTop = new DrawArea(getBaseComponent(), 533, 186);
        Draw2D.clear();

        titleBottom = new DrawArea(getBaseComponent(), 360, 146);
        Draw2D.clear();

        titleCenter = new DrawArea(getBaseComponent(), 360, 200);
        Draw2D.clear();

        titleBottomLeft = new DrawArea(getBaseComponent(), 214, 267);
        Draw2D.clear();

        titleBottomRight = new DrawArea(getBaseComponent(), 215, 267);
        Draw2D.clear();

        titleLeftSpace = new DrawArea(getBaseComponent(), 86, 79);
        Draw2D.clear();

        titleRightSpace = new DrawArea(getBaseComponent(), 87, 79);
        Draw2D.clear();

        if (titleArchive != null) {
            loadTitleBackground();
            loadTitleForeground();
        }

        redrawTitleBackground = true;
    }

    public void runFlames() {
        flameThreadActive = true;
        try {
            long l = System.currentTimeMillis();
            int j = 0;
            int k = 20;
            while (flameActive) {
                updateFlames();
                updateFlames();
                drawFlames();
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
        flameThreadActive = false;
    }

    public void run() {
        if (flamesThread) {
            runFlames();
            return;
        }

        if (aBoolean799) {
            loadMidi();
        } else {
            super.run();
        }
    }

    public void updateInterfaceScrollbar(int i, int k, int l, int i1, boolean flag, int j1,
                                         int k1, InterfaceComponent interfaceComponent) {
        if (scrollGripHeld)
            scrollGripInputPadding = 32;
        else
            scrollGripInputPadding = 0;
        scrollGripHeld = false;
        if (i >= j1 && i < j1 + 16 && k >= k1 && k < k1 + 16) {
            interfaceComponent.scrollY -= dragCycle * 4;
            if (flag) {
                sidebarRedraw = true;
                return;
            }
        } else if (i >= j1 && i < j1 + 16 && k >= (k1 + i1) - 16 && k < k1 + i1) {
            interfaceComponent.scrollY += dragCycle * 4;
            if (flag) {
                sidebarRedraw = true;
                return;
            }
        } else if (i >= j1 - scrollGripInputPadding && i < j1 + 16 + scrollGripInputPadding && k >= k1 + 16 && k < (k1 + i1) - 16
            && dragCycle > 0) {
            int l1 = ((i1 - 32) * i1) / l;
            if (l1 < 8)
                l1 = 8;
            int i2 = k - k1 - 16 - l1 / 2;
            int j2 = i1 - 32 - l1;
            interfaceComponent.scrollY = ((l - i1) * i2) / j2;
            if (flag)
                sidebarRedraw = true;
            scrollGripHeld = true;
        }
    }

    public void login(String username, String password, boolean reconnect) {
        Signlink.errorname = username;

        try {
            if (!reconnect) {
                loginMessage0 = "";
                loginMessage1 = "Connecting to server...";
                drawTitleScreen();
            }

            stream = new BufferedStream(this, opensocket(43594 + portoff));
            stream.read(inBuffer.data, 0, 8);
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
                loginBuffer.writeDWord(archiveChecksums[i]);
            }
            loginBuffer.writeBytes(outBuffer.data, outBuffer.offset, 0);

            outBuffer.isaacState = new IsaacRandom(isaacSeed);
            for (int j = 0; j < 4; j++) {
                isaacSeed[j] += 50;
            }
            isaacState = new IsaacRandom(isaacSeed);

            stream.write(loginBuffer.data, loginBuffer.offset, 0);

            int response = stream.read();
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
                InputTracking.setDisabled();
                ingame = true;
                outBuffer.offset = 0;
                inBuffer.offset = 0;
                packetOpcode = -1;
                lastPacketOpcode = -1;
                secondMostRecentOpcode = -1;
                thirdMostRecentOpcode = -1;
                packetLength = 0;
                netIdleCycles = 0;
                systemUpdateTimer = 0;
                idleTimeout = 0;
                hintType = 0;
                optionCount = 0;
                menuVisible = false;
                super.idleCycles = 0;
                for (int l = 0; l < 100; l++) {
                    chatMessage[l] = null;
                }
                selectedObject = 0;
                selectedSpell = 0;
                sceneState = 0;
                waveCount = 0;
                cameraAnticheatOffsetX = (int) (Math.random() * 100D) - 50;
                cameraAnticheatOffsetZ = (int) (Math.random() * 110D) - 55;
                cameraAnticheatAngle = (int) (Math.random() * 80D) - 40;
                minimapAnticheatAngle = (int) (Math.random() * 120D) - 60;
                minimapZoom = (int) (Math.random() * 30D) - 20;
                cameraYaw = (int) (Math.random() * 20D) - 10 & 0x7ff;
                lastSceneLevel = -1;
                flagTileX = 0;
                flagTileY = 0;
                playerCount = 0;
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
                stickyChatbackComponentId = -1;
                chatbackComponentId = -1;
                viewportInterfaceIndex = -1;
                sidebarInterfaceId = -1;
                chatContinuingDialogue = false;
                selectedTab = 3;
                chatbackInputType = false;
                menuVisible = false;
                showSocialInput = false;
                chatbackMessage = null;
                inMultizone = 0;
                anInt1128 = -1;
                characterDesignIsMale = true;
                resetCharacterDesign();
                for (int i2 = 0; i2 < 5; i2++) {
                    characterDesignColors[i2] = 0;
                }
                anInt772 = 0;
                anInt867 = 0;
                anInt806 = 0;
                anInt797 = 0;
                anticheatCounter1 = 0;
                anInt857 = 0;
                anInt937 = 0;
                anInt890 = 0;
                anInt876 = 0;
                anInt1017 = 0;
                prepareGameScreen();
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
                ingame = true;
                outBuffer.offset = 0;
                inBuffer.offset = 0;
                packetOpcode = -1;
                lastPacketOpcode = -1;
                secondMostRecentOpcode = -1;
                thirdMostRecentOpcode = -1;
                packetLength = 0;
                netIdleCycles = 0;
                systemUpdateTimer = 0;
                optionCount = 0;
                menuVisible = false;
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

    public void addLoc(int i, int j, int k, int l, int i1, int j1,
                       int l1) {
        if (j >= 1 && k >= 1 && j <= 102 && k <= 102) {
            if (lowMemory && l1 != currentLevel)
                return;
            int i2 = 0;
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
                if (j3 < 3 && (levelRenderFlags[1][j][k] & 2) == 2)
                    j3++;
                SceneBuilder.addLoc(j, list, collisionMaps[l1], k, i, levelHeightMaps, l1, i1, j1,
                    scene, j3);
            }
        }
    }

    public void addFriend(long l) {
        if (l == 0L)
            return;
        if (friendCount >= 100) {
            addMessage(0, "Your friends list is full. Max of 100 hit", "");
            return;
        }
        String s = StringUtils.formatName(StringUtils.fromBase37(l));
        for (int j = 0; j < friendCount; j++)
            if (friendName37[j] == l) {
                addMessage(0, s + " is already on your friend list", "");
                return;
            }

        for (int k = 0; k < ignoreCount; k++)
            if (ignoreName37[k] == l) {
                addMessage(0, "Please remove " + s + " from your ignore list first", "");
                return;
            }

        if (s.equals(self.name))
            return;
        friendName[friendCount] = s;
        friendName37[friendCount] = l;
        friendWorld[friendCount] = 0;
        friendCount++;
        sidebarRedraw = true;
        outBuffer.writeOpcode(118);
        outBuffer.writeQWord(l);
    }

    public void unload() {
        Signlink.reporterror = false;

        try {
            if (stream != null) {
                stream.close();
            }
        } catch (Exception _ex) {
        }

        stream = null;
        midistop();
        aBoolean812 = false;
        outBuffer = null;
        loginBuffer = null;
        inBuffer = null;
        sceneMapIndex = null;
        sceneMapLandData = null;
        sceneMapLocData = null;
        levelHeightMaps = null;
        levelRenderFlags = null;
        scene = null;
        collisionMaps = null;
        pathWaypoint = null;
        pathDistance = null;
        waypointX = null;
        waypointY = null;
        tmpTexels = null;
        areaInvback = null;
        areaMapback = null;
        areaViewport = null;
        areaChatback = null;
        areaBackbase1 = null;
        areaBackbase2 = null;
        areaBackhmid1 = null;
        backleft1 = null;
        backleft2 = null;
        backright1 = null;
        backright2 = null;
        backtop1 = null;
        backtop2 = null;
        backvmid1 = null;
        backvmid2 = null;
        backvmid3 = null;
        backhmid2 = null;
        inback = null;
        mapback = null;
        chatback = null;
        backbase1 = null;
        backbase2 = null;
        backhmid1 = null;
        sideicons = null;
        redstone1 = null;
        redstone2 = null;
        redstone3 = null;
        redstone1h = null;
        redstone2h = null;
        redstone1v = null;
        redstone2v = null;
        redstone3v = null;
        redstone1hv = null;
        redstone2hv = null;
        compass = null;
        hitmarks = null;
        headicons = null;
        cross = null;
        mapdot1 = null;
        mapdot2 = null;
        mapdot3 = null;
        mapdot4 = null;
        mapscene = null;
        mapfunction = null;
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
        list = null;
        paramA = null;
        paramB = null;
        actions = null;
        paramC = null;
        options = null;
        variables = null;
        activeMapFunctionX = null;
        activeMapFunctionZ = null;
        activeMapFunctions = null;
        minimap = null;
        friendName = null;
        friendName37 = null;
        friendWorld = null;
        titleLeft = null;
        titleRight = null;
        titleTop = null;
        titleBottom = null;
        titleCenter = null;
        titleBottomLeft = null;
        titleBottomRight = null;
        titleLeftSpace = null;
        titleRightSpace = null;
        disposeTitleComponents();
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

    public void addPlayerOptions(int i, int j, PlayerEntity player, int k) {
        if (player == self) {
            return;
        }

        if (optionCount >= 400) {
            return;
        }

        String s = player.name
            + getLevelColorTag(self.combatLevel, player.combatLevel) + " (level-"
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

    public void updateGame() {
        if (systemUpdateTimer > 1) {
            systemUpdateTimer--;
        }

        if (idleTimeout > 0) {
            idleTimeout--;
        }

        for (int i = 0; i < 5; i++) {
            if (!readPacket()) {
                break;
            }
        }

        if (!ingame) {
            return;
        }

        for (int j = 0; j < waveCount; j++) {
            if (waveDelay[j] <= 0) {
                boolean failed = false;
                try {
                    if (waveId[j] == lastWaveId && waveLoops[j] == lastWaveLoops) {
                        if (!wavereplay()) {
                            failed = true;
                        }
                    } else {
                        Buffer b = SoundTrack.generate(waveLoops[j], waveId[j]);
                        if (System.currentTimeMillis() + (long) (b.offset / 22) > lastWaveStartTime + (long) (lastWaveLength / 22)) {
                            lastWaveLength = b.offset;
                            lastWaveStartTime = System.currentTimeMillis();

                            if (wavesave(b.data, b.offset)) {
                                lastWaveId = waveId[j];
                                lastWaveLoops = waveLoops[j];
                            } else {
                                failed = true;
                            }
                        }
                    }
                } catch (Exception exception) {
                }

                if (!failed || waveDelay[j] == -5) {
                    waveCount--;

                    for (int k = j; k < waveCount; k++) {
                        waveId[k] = waveId[k + 1];
                        waveLoops[k] = waveLoops[k + 1];
                        waveDelay[k] = waveDelay[k + 1];
                    }

                    j--;
                } else {
                    waveDelay[j] = -5;
                }
            } else {
                waveDelay[j]--;
            }
        }

        if (anInt744 > 0) {
            anInt744 -= 20;
            if (anInt744 < 0) {
                anInt744 = 0;
            }
            if (anInt744 == 0 && aBoolean835 && !lowMemory) {
                setMidi(anInt1110, aString1119, anInt1155);
            }
        }

        Buffer tracking = InputTracking.flushAndContinue();
        if (tracking != null) {
            outBuffer.writeOpcode(81);
            outBuffer.writeWord(tracking.offset);
            outBuffer.writeBytes(tracking.data, tracking.offset, 0);
            tracking.release();
        }

        netIdleCycles++;
        if (netIdleCycles > 750) {
            reconnect();
        }

        updatePlayers(true);
        updateNpcEntity(true);
        updateEntityVoices();
        updateTemporaryLocs();
        if ((super.keyDown[1] == 1 || super.keyDown[2] == 1 || super.keyDown[3] == 1 || super.keyDown[4] == 1) && anInt916++ > 5) {
            anInt916 = 0;
            outBuffer.writeOpcode(189);
            outBuffer.writeWord(cameraOrbitPitch);
            outBuffer.writeWord(cameraYaw);
            outBuffer.writeByte(minimapAnticheatAngle);
            outBuffer.writeByte(minimapZoom);
        }
        sceneDelta++;

        if (crossType != 0) {
            crossCycle += 20;
            if (crossCycle >= 400) {
                crossType = 0;
            }
        }

        if (selectedArea != 0) {
            selectedCycle++;
            if (selectedCycle >= 15) {
                if (selectedArea == 2)
                    sidebarRedraw = true;
                if (selectedArea == 3)
                    redrawChatback = true;
                selectedArea = 0;
            }
        }

        if (objDragArea != 0) {
            objDragCycles++;
            if (super.mouseX > objGrabX + 5 || super.mouseX < objGrabX - 5 || super.mouseY > objGrabY + 5 || super.mouseY < objGrabY - 5)
                aBoolean960 = true;
            if (super.dragButton == 0) {
                if (objDragArea == 2)
                    sidebarRedraw = true;
                if (objDragArea == 3)
                    redrawChatback = true;
                objDragArea = 0;
                if (aBoolean960 && objDragCycles >= 5) {
                    anInt1088 = -1;
                    updateInput();
                    if (anInt1088 == objDragComponentId && anInt1087 != objDragSlot) {
                        InterfaceComponent interfaceComponent = InterfaceComponent.instances[objDragComponentId];
                        int j1 = interfaceComponent.inventoryIndices[anInt1087];
                        interfaceComponent.inventoryIndices[anInt1087] = interfaceComponent.inventoryIndices[objDragSlot];
                        interfaceComponent.inventoryIndices[objDragSlot] = j1;
                        j1 = interfaceComponent.inventoryAmount[anInt1087];
                        interfaceComponent.inventoryAmount[anInt1087] = interfaceComponent.inventoryAmount[objDragSlot];
                        interfaceComponent.inventoryAmount[objDragSlot] = j1;
                        outBuffer.writeOpcode(159);
                        outBuffer.writeWord(objDragComponentId);
                        outBuffer.writeWord(objDragSlot);
                        outBuffer.writeWord(anInt1087);
                    }
                } else if ((anInt810 == 1 || isFriend(optionCount - 1)) && optionCount > 2)
                    showContextMenu();
                else if (optionCount > 0)
                    useMenuOption(optionCount - 1);
                selectedCycle = 10;
                super.mouseButton = 0;
            }
        }

        updateGameCounter++;
        if (updateGameCounter > 127) {
            updateGameCounter = 0;
            outBuffer.writeOpcode(215);
            outBuffer.writeSWord(0x4c2b2c);
        }

        if (Scene.clickedTileX != -1) {
            int l = Scene.clickedTileX;
            int k1 = Scene.clickedTileZ;
            boolean canMove = moveTo(self.pathTileX[0], 0, true, l, self.pathTileZ[0], 0, 0, k1, 0, 0, 0);
            Scene.clickedTileX = -1;

            if (canMove) {
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 1;
                crossCycle = 0;
            }
        }

        if (super.mouseButton == 1 && chatbackMessage != null) {
            chatbackMessage = null;
            redrawChatback = true;
            super.mouseButton = 0;
        }

        updateMiniMenu();
        updateMinimapInput();
        updateSidebarTabInput();
        updateChatSettingInput();

        if (super.dragButton == 1 || super.mouseButton == 1) {
            dragCycle++;
        }

        if (sceneState == 2) {
            updateOrbitCamera();
        }

        if (sceneState == 2 && cutsceneActive) {
            updateEntity();
        }

        for (int i1 = 0; i1 < 5; i1++) {
            unknownCameraVariable[i1]++;
        }

        updateKeyboardInput();

        super.idleCycles++;
        if (super.idleCycles > 4500) {
            idleTimeout = 250;
            super.idleCycles -= 500;
            outBuffer.writeOpcode(70);
        }

        cameraOffsetCycle++;
        if (cameraOffsetCycle > 500) {
            cameraOffsetCycle = 0;
            int i = (int) (Math.random() * 8D);
            if ((i & 1) == 1)
                cameraAnticheatOffsetX += cameraOffsetXModifier;
            if ((i & 2) == 2)
                cameraAnticheatOffsetZ += cameraOffsetZModifier;
            if ((i & 4) == 4)
                cameraAnticheatAngle += cameraOffsetYawModifier;
        }

        if (cameraAnticheatOffsetX < -50) {
            cameraOffsetXModifier = 2;
        } else if (cameraAnticheatOffsetX > 50) {
            cameraOffsetXModifier = -2;
        }

        if (cameraAnticheatOffsetZ < -55) {
            cameraOffsetZModifier = 2;
        } else if (cameraAnticheatOffsetZ > 55) {
            cameraOffsetZModifier = -2;
        }

        if (cameraAnticheatAngle < -40) {
            cameraOffsetYawModifier = 1;
        } else if (cameraAnticheatAngle > 40) {
            cameraOffsetYawModifier = -1;
        }

        minimapOffsetCycle++;
        if (minimapOffsetCycle > 500) {
            minimapOffsetCycle = 0;
            int i2 = (int) (Math.random() * 8D);
            if ((i2 & 1) == 1) {
                minimapAnticheatAngle += minimapAngleModifier;
            }
            if ((i2 & 2) == 2) {
                minimapZoom += minimapZoomModifier;
            }
        }

        if (minimapAnticheatAngle < -60) {
            minimapAngleModifier = 2;
        } else if (minimapAnticheatAngle > 60) {
            minimapAngleModifier = -2;
        }

        if (minimapZoom < -20) {
            minimapZoomModifier = 1;
        } else if (minimapZoom > 10) {
            minimapZoomModifier = -1;
        }

        anInt1090++;
        if (anInt1090 > 110) {
            anInt1090 = 0;
            outBuffer.writeOpcode(236);
            outBuffer.writeDWord(0);
        }

        anInt782++;
        if (anInt782 > 50) {
            outBuffer.writeOpcode(108);
        }

        try {
            if (stream != null && outBuffer.offset > 0) {
                stream.write(outBuffer.data, outBuffer.offset, 0);
                outBuffer.offset = 0;
                anInt782 = 0;
            }
        } catch (IOException _ex) {
            reconnect();
            return;
        } catch (Exception exception1) {
            disconnect();
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

    public void updateSceneSpotAnims() {
        for (SpotAnimEntity entity = (SpotAnimEntity) spotanims
            .peekLast(); entity != null; entity = (SpotAnimEntity) spotanims
            .getPrevious())
            if (entity.level != currentLevel || entity.finished)
                entity.unlink();
            else if (clientClock >= entity.firstCycle) {
                entity.update(sceneDelta);
                if (entity.finished)
                    entity.unlink();
                else
                    scene.add(entity.z, 60, 0, entity.x, -1,
                        false, null, entity, entity.y, entity.level);
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

        clickedMinimap = 0;
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
                                clickedMinimap = 1;
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

            if (super.keyDown[5] == 1) {
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

        for (int n = 0; n < playerCount; n++) {
            if (playerEntities[playerIndices[n]] == null) {
                Signlink.reporterror(username + " null entry in pl list - pos:" + n + " size:" + playerCount);
                throw new RuntimeException("eek");
            }
        }
    }

    public boolean animateInterface(int i, int j) {
        boolean flag = false;

        InterfaceComponent parent = InterfaceComponent.instances[i];
        for (int l = 0; l < parent.children.length; l++) {
            if (parent.children[l] == -1) {
                break;
            }

            InterfaceComponent child = InterfaceComponent.instances[parent.children[l]];
            if (child.type == 1) {
                flag |= animateInterface(child.id, j);
            }
            if (child.type == 6 && (child.seqId != -1 || child.activeSeqId != -1)) {
                boolean flag1 = isInterfaceEnabled(child);
                int i1;
                if (flag1)
                    i1 = child.activeSeqId;
                else
                    i1 = child.seqId;
                if (i1 != -1) {
                    SeqType seqType = SeqType.animations[i1];
                    for (child.seqCycle += j; child.seqCycle > seqType.frameDelay[child.seqFrame]; ) {
                        child.seqCycle -= seqType.frameDelay[child.seqFrame] + 1;
                        child.seqFrame++;
                        if (child.seqFrame >= seqType.frameCount) {
                            child.seqFrame -= seqType.delta;
                            if (child.seqFrame < 0 || child.seqFrame >= seqType.frameCount)
                                child.seqFrame = 0;
                        }
                        flag = true;
                    }

                }
            }
        }

        return flag;
    }

    public void addMessage(int i, String s, String s1) {
        if (i == 0 && stickyChatbackComponentId != -1) {
            chatbackMessage = s;
            super.mouseButton = 0;
        }

        if (chatbackComponentId == -1) {
            redrawChatback = true;
        }

        for (int j = 99; j > 0; j--) {
            chatMessageType[j] = chatMessageType[j - 1];
            chatMessagePrefix[j] = chatMessagePrefix[j - 1];
            chatMessage[j] = chatMessage[j - 1];
        }

        chatMessageType[0] = i;
        chatMessagePrefix[0] = s1;
        chatMessage[0] = s;
    }

    public void resetParentComponentSeq(int j) {
        InterfaceComponent parent = InterfaceComponent.instances[j];
        for (int k = 0; k < parent.children.length; k++) {
            if (parent.children[k] == -1)
                break;
            InterfaceComponent child = InterfaceComponent.instances[parent.children[k]];
            if (child.type == 1)
                resetParentComponentSeq(child.id);
            child.seqFrame = 0;
            child.seqCycle = 0;
        }
    }

    public void removeFriend(long l) {
        if (l == 0L)
            return;
        for (int k = 0; k < friendCount; k++)
            if (friendName37[k] == l) {
                friendCount--;
                sidebarRedraw = true;
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

    public boolean isInterfaceEnabled(InterfaceComponent interfaceComponent) {
        if (interfaceComponent.scriptCompareType == null)
            return false;
        for (int j = 0; j < interfaceComponent.scriptCompareType.length; j++) {
            int k = executeInterface(interfaceComponent, j);
            int l = interfaceComponent.scriptCompareValue[j];
            if (interfaceComponent.scriptCompareType[j] == 2) {
                if (k >= l)
                    return false;
            } else if (interfaceComponent.scriptCompareType[j] == 3) {
                if (k <= l)
                    return false;
            } else if (interfaceComponent.scriptCompareType[j] == 4) {
                if (k == l)
                    return false;
            } else if (k != l)
                return false;
        }

        return true;
    }

    public void updateMinimapInput() {
        if (super.mouseButton == 1) {
            int i = super.clickX - 21 - 561;
            int j = super.clickY - 9 - 5;
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
                    outBuffer.writeByte(clickedMinimap);
                    outBuffer.writeByte(63);
                }
            }
        }
    }

    public void updateMiniMenu() {
        if (objDragArea != 0)
            return;
        int button = super.mouseButton;
        if (selectedSpell == 1 && super.clickX >= 520 && super.clickY >= 165 && super.clickX <= 788
            && super.clickY <= 230)
            button = 0;
        if (menuVisible) {
            if (button != 1) {
                int j = super.mouseX;
                int i1 = super.mouseY;
                if (mouseArea == 0) {
                    j -= 8;
                    i1 -= 11;
                }
                if (mouseArea == 1) {
                    j -= 562;
                    i1 -= 231;
                }
                if (mouseArea == 2) {
                    j -= 22;
                    i1 -= 375;
                }
                if (j < menuX - 10 || j > menuX + menuWidth + 10 || i1 < menuY - 10
                    || i1 > menuY + menuHeight + 10) {
                    menuVisible = false;
                    if (mouseArea == 1)
                        sidebarRedraw = true;
                    if (mouseArea == 2)
                        redrawChatback = true;
                }
            }
            if (button == 1) {
                int k = menuX;
                int j1 = menuY;
                int l1 = menuWidth;
                int j2 = super.clickX;
                int k2 = super.clickY;
                if (mouseArea == 0) {
                    j2 -= 8;
                    k2 -= 11;
                }
                if (mouseArea == 1) {
                    j2 -= 562;
                    k2 -= 231;
                }
                if (mouseArea == 2) {
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
                    useMenuOption(l2);
                menuVisible = false;
                if (mouseArea == 1)
                    sidebarRedraw = true;
                if (mouseArea == 2) {
                    redrawChatback = true;
                    return;
                }
            }
        } else {
            if (button == 1 && optionCount > 0) {
                int l = actions[optionCount - 1];
                if (l == 602 || l == 596 || l == 22 || l == 892 || l == 415 || l == 405 || l == 38 || l == 422
                    || l == 478 || l == 347 || l == 188) {
                    int k1 = paramA[optionCount - 1];
                    int i2 = paramB[optionCount - 1];
                    InterfaceComponent interfaceComponent = InterfaceComponent.instances[i2];
                    if (interfaceComponent.inventoryDummy) {
                        aBoolean960 = false;
                        objDragCycles = 0;
                        objDragComponentId = i2;
                        objDragSlot = k1;
                        objDragArea = 2;
                        objGrabX = super.clickX;
                        objGrabY = super.clickY;
                        if (InterfaceComponent.instances[i2].parent == viewportInterfaceIndex)
                            objDragArea = 1;
                        if (InterfaceComponent.instances[i2].parent == chatbackComponentId)
                            objDragArea = 3;
                        return;
                    }
                }
            }
            if (button == 1 && (anInt810 == 1 || isFriend(optionCount - 1)) && optionCount > 2)
                button = 2;
            if (button == 1 && optionCount > 0)
                useMenuOption(optionCount - 1);
            if (button == 2 && optionCount > 0)
                showContextMenu();
        }
    }

    public void updateEntity() {
        int i = anInt728 * 128 + 64;
        int j = anInt729 * 128 + 64;
        int k = getLandY(currentLevel, anInt728, anInt729) - anInt730;
        if (cameraX < i) {
            cameraX += anInt731 + ((i - cameraX) * anInt732) / 1000;
            if (cameraX > i)
                cameraX = i;
        }
        if (cameraX > i) {
            cameraX -= anInt731 + ((cameraX - i) * anInt732) / 1000;
            if (cameraX < i)
                cameraX = i;
        }
        if (cameraY < k) {
            cameraY += anInt731 + ((k - cameraY) * anInt732) / 1000;
            if (cameraY > k)
                cameraY = k;
        }
        if (cameraY > k) {
            cameraY -= anInt731 + ((cameraY - k) * anInt732) / 1000;
            if (cameraY < k)
                cameraY = k;
        }
        if (cameraZ < j) {
            cameraZ += anInt731 + ((j - cameraZ) * anInt732) / 1000;
            if (cameraZ > j)
                cameraZ = j;
        }
        if (cameraZ > j) {
            cameraZ -= anInt731 + ((cameraZ - j) * anInt732) / 1000;
            if (cameraZ < j)
                cameraZ = j;
        }
        i = anInt948 * 128 + 64;
        j = anInt949 * 128 + 64;
        k = getLandY(currentLevel, anInt948, anInt949) - anInt950;
        int l = i - cameraX;
        int i1 = k - cameraY;
        int j1 = j - cameraZ;
        int k1 = (int) Math.sqrt(l * l + j1 * j1);
        int l1 = (int) (Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
        int i2 = (int) (Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;
        if (l1 < 128)
            l1 = 128;
        if (l1 > 383)
            l1 = 383;
        if (cameraPitch < l1) {
            cameraPitch += anInt951 + ((l1 - cameraPitch) * anInt952) / 1000;
            if (cameraPitch > l1)
                cameraPitch = l1;
        }
        if (cameraPitch > l1) {
            cameraPitch -= anInt951 + ((cameraPitch - l1) * anInt952) / 1000;
            if (cameraPitch < l1)
                cameraPitch = l1;
        }
        int j2 = i2 - cameraOrbitYaw;
        if (j2 > 1024)
            j2 -= 2048;
        if (j2 < -1024)
            j2 += 2048;
        if (j2 > 0) {
            cameraOrbitYaw += anInt951 + (j2 * anInt952) / 1000;
            cameraOrbitYaw &= 0x7ff;
        }
        if (j2 < 0) {
            cameraOrbitYaw -= anInt951 + (-j2 * anInt952) / 1000;
            cameraOrbitYaw &= 0x7ff;
        }
        int k2 = i2 - cameraOrbitYaw;
        if (k2 > 1024)
            k2 -= 2048;
        if (k2 < -1024)
            k2 += 2048;
        if (k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0)
            cameraOrbitYaw = i2;
    }

    public void updateSidebarTabInput() {
        if (super.mouseButton == 1) {
            if (super.clickX >= 549 && super.clickX <= 583 && super.clickY >= 195 && super.clickY < 231
                && tabComponentId[0] != -1) {
                sidebarRedraw = true;
                selectedTab = 0;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 579 && super.clickX <= 609 && super.clickY >= 194 && super.clickY < 231
                && tabComponentId[1] != -1) {
                sidebarRedraw = true;
                selectedTab = 1;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 607 && super.clickX <= 637 && super.clickY >= 194 && super.clickY < 231
                && tabComponentId[2] != -1) {
                sidebarRedraw = true;
                selectedTab = 2;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 635 && super.clickX <= 679 && super.clickY >= 194 && super.clickY < 229
                && tabComponentId[3] != -1) {
                sidebarRedraw = true;
                selectedTab = 3;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 676 && super.clickX <= 706 && super.clickY >= 194 && super.clickY < 231
                && tabComponentId[4] != -1) {
                sidebarRedraw = true;
                selectedTab = 4;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 704 && super.clickX <= 734 && super.clickY >= 194 && super.clickY < 231
                && tabComponentId[5] != -1) {
                sidebarRedraw = true;
                selectedTab = 5;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 732 && super.clickX <= 766 && super.clickY >= 195 && super.clickY < 231
                && tabComponentId[6] != -1) {
                sidebarRedraw = true;
                selectedTab = 6;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 550 && super.clickX <= 584 && super.clickY >= 492 && super.clickY < 528
                && tabComponentId[7] != -1) {
                sidebarRedraw = true;
                selectedTab = 7;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 582 && super.clickX <= 612 && super.clickY >= 492 && super.clickY < 529
                && tabComponentId[8] != -1) {
                sidebarRedraw = true;
                selectedTab = 8;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 609 && super.clickX <= 639 && super.clickY >= 492 && super.clickY < 529
                && tabComponentId[9] != -1) {
                sidebarRedraw = true;
                selectedTab = 9;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 637 && super.clickX <= 681 && super.clickY >= 493 && super.clickY < 528
                && tabComponentId[10] != -1) {
                sidebarRedraw = true;
                selectedTab = 10;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 679 && super.clickX <= 709 && super.clickY >= 492 && super.clickY < 529
                && tabComponentId[11] != -1) {
                sidebarRedraw = true;
                selectedTab = 11;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 706 && super.clickX <= 736 && super.clickY >= 492 && super.clickY < 529
                && tabComponentId[12] != -1) {
                sidebarRedraw = true;
                selectedTab = 12;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 734 && super.clickX <= 768 && super.clickY >= 492 && super.clickY < 528
                && tabComponentId[13] != -1) {
                sidebarRedraw = true;
                selectedTab = 13;
                sidebarRedrawIcons = true;
            }

            sidebarClickedCounter++;
            if (sidebarClickedCounter > 150) {
                sidebarClickedCounter = 0;
                outBuffer.writeOpcode(233);
                outBuffer.writeByte(43);
            }
        }
    }

    public boolean updateInterfaceTooltip(InterfaceComponent interfaceComponent) {
        int i = interfaceComponent.contentType;
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
            options[optionCount] = "Remove @whi@" + interfaceComponent.text;
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
            Signlink.reporterror(username + " Too many npcs");
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

    public void reconnect() {
        if (idleTimeout > 0) {
            disconnect();
            return;
        }
        areaViewport.bind();
        fontPlain12.drawRightAligned(144, 0, "Connection lost", 257);
        fontPlain12.drawRightAligned(143, 0xffffff, "Connection lost", 256);
        fontPlain12.drawRightAligned(159, 0, "Please wait - attempting to reestablish", 257);
        fontPlain12.drawRightAligned(158, 0xffffff, "Please wait - attempting to reestablish", 256);
        areaViewport.drawImage(11, super.graphics, 8);
        flagTileX = 0;
        BufferedStream bufferedStream = this.stream;
        ingame = false;
        login(username, password, true);
        if (!ingame)
            disconnect();
        try {
            bufferedStream.close();
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void updateFlameDissolve(IndexedSprite indexedSprite) {
        int j = 256;
        for (int k = 0; k < flameBuffer1.length; k++)
            flameBuffer1[k] = 0;

        for (int l = 0; l < 5000; l++) {
            int i1 = (int) (Math.random() * 128D * (double) j);
            flameBuffer1[i1] = (int) (Math.random() * 256D);
        }

        for (int j1 = 0; j1 < 20; j1++) {
            for (int k1 = 1; k1 < j - 1; k1++) {
                for (int i2 = 1; i2 < 127; i2++) {
                    int k2 = i2 + (k1 << 7);
                    flameBuffer2[k2] = (flameBuffer1[k2 - 1] + flameBuffer1[k2 + 1] + flameBuffer1[k2 - 128]
                        + flameBuffer1[k2 + 128]) / 4;
                }

            }

            int[] ai = flameBuffer1;
            flameBuffer1 = flameBuffer2;
            flameBuffer2 = ai;
        }

        if (indexedSprite != null) {
            int l1 = 0;
            for (int j2 = 0; j2 < indexedSprite.height; j2++) {
                for (int l2 = 0; l2 < indexedSprite.width; l2++)
                    if (indexedSprite.pixels[l1++] != 0) {
                        int i3 = l2 + 16 + indexedSprite.clipX;
                        int j3 = j2 + 16 + indexedSprite.clipY;
                        int k3 = i3 + (j3 << 7);
                        flameBuffer1[k3] = 0;
                    }
            }
        }
    }

    public void updateObjectStack(int i, int j) {
        LinkedList stacks = objects[currentLevel][i][j];
        if (stacks == null) {
            scene.removeObject(currentLevel, i, j);
            return;
        }
        int k = -99999999;
        Object obj = null;
        for (ObjStackEntity entity = (ObjStackEntity) stacks
            .peekLast(); entity != null; entity = (ObjStackEntity) stacks.getPrevious()) {
            ObjType objType = ObjType.get(entity.model);
            int i1 = objType.value;
            if (objType.stackable)
                i1 *= entity.amount + 1;
            if (i1 > k) {
                k = i1;
                obj = entity;
            }
        }

        stacks.pushLast(((Node) (obj)));
        int l = -1;
        int j1 = -1;
        int k1 = 0;
        int l1 = 0;
        for (ObjStackEntity objStackEntity_1 = (ObjStackEntity) stacks
            .peekLast(); objStackEntity_1 != null; objStackEntity_1 = (ObjStackEntity) stacks.getPrevious()) {
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
            getLandY(currentLevel, i * 128 + 64, j * 128 + 64), currentLevel, i2, j, i, class38_sub2_sub1_1);
    }

    public void createScene() {
        try {
            lastSceneLevel = -1;
            temporaryLocs.clear();
            list.clear();
            spotanims.clear();
            projectiles.clear();
            Draw3D.clearPools();
            clearCaches();
            scene.reset();
            for (int j = 0; j < 4; j++)
                collisionMaps[j].reset();

            System.gc();
            SceneBuilder sceneBuilder = new SceneBuilder(104, levelRenderFlags, 104, levelHeightMaps);
            byte[] abyte0 = new byte[100000];
            int k = sceneMapLandData.length;
            SceneBuilder.lowMemory = Scene.lowMemory;
            for (int l = 0; l < k; l++) {
                int i1 = sceneMapIndex[l] >> 8;
                int k1 = sceneMapIndex[l] & 0xff;
                if (i1 == 33 && k1 >= 71 && k1 <= 73)
                    SceneBuilder.lowMemory = false;
            }

            if (SceneBuilder.lowMemory)
                scene.setup(currentLevel);
            else
                scene.setup(0);
            outBuffer.writeOpcode(108);
            for (int j1 = 0; j1 < k; j1++) {
                int l1 = (sceneMapIndex[j1] >> 8) * 64 - baseTileX;
                int j2 = (sceneMapIndex[j1] & 0xff) * 64 - baseTileZ;
                byte[] abyte2 = sceneMapLandData[j1];
                if (abyte2 != null) {
                    int i3 = (new Buffer(abyte2)).readDWord();
                    BZip2InputStream.read(abyte0, i3, abyte2, abyte2.length - 4, 4);
                    sceneBuilder.readLandscape(abyte0, (anInt838 - 6) * 8, j2, l1, (anInt839 - 6) * 8);
                } else if (anInt839 < 800)
                    sceneBuilder.clearLandscape(l1, j2, 64, 64);
            }

            outBuffer.writeOpcode(108);
            for (int i2 = 0; i2 < k; i2++) {
                byte[] abyte1 = sceneMapLocData[i2];
                if (abyte1 != null) {
                    int k2 = (new Buffer(abyte1)).readDWord();
                    BZip2InputStream.read(abyte0, k2, abyte1, abyte1.length - 4, 4);
                    int j3 = (sceneMapIndex[i2] >> 8) * 64 - baseTileX;
                    int l3 = (sceneMapIndex[i2] & 0xff) * 64 - baseTileZ;
                    sceneBuilder.readLocs(abyte0, scene, collisionMaps, list, l3, j3);
                }
            }

            outBuffer.writeOpcode(108);
            sceneBuilder.buildLandscape(scene, collisionMaps);
            areaViewport.bind();
            outBuffer.writeOpcode(108);
            for (LocEntity locEntity = (LocEntity) list
                .peekLast(); locEntity != null; locEntity = (LocEntity) list.getPrevious())
                if ((levelRenderFlags[1][locEntity.anInt1208][locEntity.anInt1209] & 2) == 2) {
                    locEntity.anInt1206--;
                    if (locEntity.anInt1206 < 0)
                        locEntity.unlink();
                }

            for (int l2 = 0; l2 < 104; l2++) {
                for (int k3 = 0; k3 < 104; k3++)
                    updateObjectStack(l2, k3);

            }

            for (SpawnedLoc spawnedLoc = (SpawnedLoc) spawnedLocations
                .peekLast(); spawnedLoc != null; spawnedLoc = (SpawnedLoc) spawnedLocations.getPrevious())
                addLoc(spawnedLoc.rotation, spawnedLoc.tileX, spawnedLoc.tileZ, spawnedLoc.classType,
                    spawnedLoc.locIndex, spawnedLoc.type, spawnedLoc.level);

        } catch (Exception exception) {
        }
        LocType.models.clear();
        System.gc();
        Draw3D.setupPools(20);
    }

    public void update() {
        if (errorStarted || errorLoading || errorHost) {
            return;
        }

        clientClock++;
        if (!ingame) {
            updateTitle();
        } else {
            updateGame();
        }
    }

    public void updateEntityVoices() {
        for (int i = -1; i < playerCount; i++) {
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

    public int executeInterface(InterfaceComponent interfaceComponent, int i) {
        if (interfaceComponent.script == null || i >= interfaceComponent.script.length)
            return -2;
        try {
            int[] code = interfaceComponent.script[i];
            int a = 0;
            int position = 0;

            do {
                int opcode;
                do {
                    opcode = code[position++];
                    if (opcode == 0)
                        return a;
                    if (opcode == 1)
                        a += skillLevelReal[code[position++]];
                    if (opcode == 2)
                        a += skillLevel[code[position++]];
                    if (opcode == 3)
                        a += skillExperience[code[position++]];
                    if (opcode == 4) {
                        InterfaceComponent c = InterfaceComponent.instances[code[position++]];
                        int k1 = code[position++] + 1;
                        for (int j2 = 0; j2 < c.inventoryIndices.length; j2++)
                            if (c.inventoryIndices[j2] == k1)
                                a += c.inventoryAmount[j2];
                    }
                    if (opcode == 5)
                        a += variables[code[position++]];
                    if (opcode == 6)
                        a += EXPERIENCE_TABLE[skillLevel[code[position++]] - 1];
                    if (opcode == 7)
                        a += (variables[code[position++]] * 100) / 46875;
                    if (opcode == 8)
                        a += self.combatLevel;
                    if (opcode == 9) {
                        for (int i1 = 0; i1 < 19; i1++) {
                            if (i1 == 18)
                                i1 = 20;
                            a += skillLevel[i1];
                        }
                    }
                    if (opcode == 10) {
                        InterfaceComponent c = InterfaceComponent.instances[code[position++]];
                        int l1 = code[position++] + 1;
                        for (int k2 = 0; k2 < c.inventoryIndices.length; k2++) {
                            if (c.inventoryIndices[k2] != l1)
                                continue;
                            a += 0x3b9ac9ff;
                            break;
                        }
                    }
                    if (opcode == 11)
                        a += energy;
                    if (opcode == 12)
                        a += weightCarried;
                } while (opcode != 13);
                int j1 = variables[code[position++]];
                int i2 = code[position++];
                a += (j1 & 1 << i2) == 0 ? 0 : 1;
            } while (true);
        } catch (Exception _ex) {
            return -1;
        }
    }

    public void drawErrorScreen() {
        Graphics g = getBaseComponent().getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 789, 532);

        setLoopRate(1);

        if (errorLoading) {
            flameActive = false;
            g.setFont(new Font("Helvetica", Font.BOLD, 16));
            g.setColor(Color.yellow);
            int i = 35;
            g.drawString("Sorry, an error has occured whilst loading RuneScape", 30, i);
            i += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, i);
            i += 50;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", Font.BOLD, 12));
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

        if (errorHost) {
            flameActive = false;
            g.setFont(new Font("Helvetica", Font.BOLD, 20));
            g.setColor(Color.white);
            g.drawString("Error - unable to load game!", 50, 50);
            g.drawString("To play RuneScape make sure you play from", 50, 100);
            g.drawString("http://www.runescape.com", 50, 150);
        }

        if (errorStarted) {
            flameActive = false;
            g.setColor(Color.yellow);
            int j = 35;
            g.drawString("Error a copy of RuneScape already appears to be loaded", 30, j);
            j += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, j);
            j += 50;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", Font.BOLD, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, j);
            j += 30;
            g.drawString("2: Try rebooting your computer, and reloading", 30, j);
        }
    }

    public void loadTitleBackground() {
        byte[] abyte0 = titleArchive.read("title.dat", null);
        Sprite temp = new Sprite(abyte0, this);
        titleLeft.bind();
        temp.drawOpaque(0, 0);

        titleRight.bind();
        temp.drawOpaque(-661, 0);

        titleTop.bind();
        temp.drawOpaque(-128, 0);

        titleBottom.bind();
        temp.drawOpaque(-214, -386);

        titleCenter.bind();
        temp.drawOpaque(-214, -186);

        titleBottomLeft.bind();
        temp.drawOpaque(0, -265);

        titleBottomRight.bind();
        temp.drawOpaque(-574, -265);

        titleLeftSpace.bind();
        temp.drawOpaque(-128, -186);

        titleRightSpace.bind();
        temp.drawOpaque(-574, -186);

        int[] ai = new int[temp.width];
        for (int i = 0; i < temp.height; i++) {
            for (int j = 0; j < temp.width; j++)
                ai[j] = temp.pixels[(temp.width - j - 1) + temp.width * i];

            for (int k = 0; k < temp.width; k++)
                temp.pixels[k + temp.width * i] = ai[k];
        }

        titleLeft.bind();
        temp.drawOpaque(394, 0);

        titleRight.bind();
        temp.drawOpaque(-267, 0);

        titleTop.bind();
        temp.drawOpaque(266, 0);

        titleBottom.bind();
        temp.drawOpaque(180, -386);

        titleCenter.bind();
        temp.drawOpaque(180, -186);

        titleBottomLeft.bind();
        temp.drawOpaque(394, -265);

        titleBottomRight.bind();
        temp.drawOpaque(-180, -265);

        titleLeftSpace.bind();
        temp.drawOpaque(212, -186);

        titleRightSpace.bind();
        temp.drawOpaque(-180, -186);

        temp = new Sprite(titleArchive, "logo", 0);
        titleTop.bind();
        temp.draw(18, super.gameWidth / 2 - temp.width / 2 - 128);

        System.gc();
    }

    public void updateSceneSeqLocs() {
        for (LocEntity e = (LocEntity) list
            .peekLast(); e != null; e = (LocEntity) list.getPrevious()) {
            boolean append = false;
            e.currentFrameDuration += sceneDelta;
            if (e.currentFrameId == -1) {
                e.currentFrameId = 0;
                append = true;
            }
            while (e.currentFrameDuration > e.seq.frameDelay[e.currentFrameId]) {
                e.currentFrameDuration -= e.seq.frameDelay[e.currentFrameId] + 1;
                e.currentFrameId++;
                append = true;
                if (e.currentFrameId < e.seq.frameCount)
                    continue;
                e.currentFrameId -= e.seq.delta;
                if (e.currentFrameId >= 0 && e.currentFrameId < e.seq.frameCount)
                    continue;
                e.unlink();
                append = false;
                break;
            }
            if (append) {
                int j = e.anInt1206;
                int k = e.anInt1208;
                int l = e.anInt1209;
                int bitset = 0;
                if (e.classType == 0)
                    bitset = scene.getWallBitset(j, k, l);
                if (e.classType == 1)
                    bitset = scene.getWallDecorationBitset(j, l, k);
                if (e.classType == 2)
                    bitset = scene.getLocationBitset(j, k, l);
                if (e.classType == 3)
                    bitset = scene.getGroundDecorationBitset(j, k, l);
                if (bitset == 0 || (bitset >> 14 & 0x7fff) != e.locIndex) {
                    e.unlink();
                } else {
                    int j1 = levelHeightMaps[j][k][l];
                    int k1 = levelHeightMaps[j][k + 1][l];
                    int l1 = levelHeightMaps[j][k + 1][l + 1];
                    int i2 = levelHeightMaps[j][k][l + 1];
                    LocType locType = LocType.get(e.locIndex);
                    int j2 = -1;
                    if (e.currentFrameId != -1)
                        j2 = e.seq.primaryFrames[e.currentFrameId];
                    if (e.classType == 2) {
                        int k2 = scene.getInfo(j, k, l, bitset);
                        int j3 = k2 & 0x1f;
                        int i4 = k2 >> 6;
                        if (j3 == 11)
                            j3 = 10;
                        Model m = locType.getModel(j3, i4, j1, k1, l1, i2, j2);
                        scene.setLocModel(k, m, j, l);
                    } else if (e.classType == 1) {
                        Model m = locType.getModel(4, 0, j1, k1, l1, i2, j2);
                        scene.setWallDecorationModel(l, k, m, j);
                    } else if (e.classType == 0) {
                        int l2 = scene.getInfo(j, k, l, bitset);
                        int k3 = l2 & 0x1f;
                        int j4 = l2 >> 6;
                        if (k3 == 2) {
                            int k4 = j4 + 1 & 3;
                            Model m1 = locType.getModel(2, 4 + j4, j1, k1, l1, i2, j2);
                            Model m2 = locType.getModel(2, k4, j1, k1, l1, i2, j2);
                            scene.setWallModels(m1, m2, l, k, j);
                        } else {
                            Model m = locType.getModel(k3, j4, j1, k1, l1, i2, j2);
                            scene.setWallModel(m, l, k, j);
                        }
                    } else if (e.classType == 3) {
                        int i3 = scene.getInfo(j, k, l, bitset);
                        int l3 = i3 >> 6;
                        Model m = locType.getModel(22, l3, j1, k1, l1, i2, j2);
                        scene.setGroundDecorationModel(m, l, k, j);
                    }
                }
            }
        }

    }

    public void removeIgnore(long l) {
        if (l == 0L)
            return;
        for (int j = 0; j < ignoreCount; j++) {
            if (ignoreName37[j] == l) {
                ignoreCount--;
                sidebarRedraw = true;
                for (int k = j; k < ignoreCount; k++)
                    ignoreName37[k] = ignoreName37[k + 1];

                outBuffer.writeOpcode(171);
                outBuffer.writeQWord(l);
                return;
            }
        }
    }

    public void updateViewport() {
        if (selectedObject == 0 && selectedSpell == 0) {
            options[optionCount] = "Walk here";
            actions[optionCount] = 660;
            paramA[optionCount] = super.mouseX;
            paramB[optionCount] = super.mouseY;
            optionCount++;
        }

        int i = -1;
        for (int j = 0; j < Model.resourceCount; j++) {
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
                    NpcEntity e = npcEntities[k1];
                    if (e.info.size == 1
                        && (e.x & 0x7f) == 64
                        && (e.z & 0x7f) == 64) {
                        for (int i2 = 0; i2 < npcCount; i2++) {
                            NpcEntity npcEntity_1 = npcEntities[npcIndices[i2]];
                            if (npcEntity_1 != null && npcEntity_1 != e
                                && npcEntity_1.info.size == 1
                                && npcEntity_1.x == e.x
                                && npcEntity_1.z == e.z)
                                drawTooltip(npcEntity_1.info, i1, l, npcIndices[i2]);
                        }

                    }
                    drawTooltip(e.info, i1, l, k1);
                }
                if (j1 == 0) {
                    PlayerEntity e = playerEntities[k1];
                    if ((e.x & 0x7f) == 64
                        && (e.z & 0x7f) == 64) {
                        for (int j2 = 0; j2 < npcCount; j2++) {
                            NpcEntity npcEntity_2 = npcEntities[npcIndices[j2]];
                            if (npcEntity_2 != null && npcEntity_2.info.size == 1
                                && npcEntity_2.x == e.x
                                && npcEntity_2.z == e.z)
                                drawTooltip(npcEntity_2.info, i1, l, npcIndices[j2]);
                        }

                        for (int k2 = 0; k2 < playerCount; k2++) {
                            PlayerEntity playerEntity_1 = playerEntities[playerIndices[k2]];
                            if (playerEntity_1 != null && playerEntity_1 != e
                                && playerEntity_1.x == e.x
                                && playerEntity_1.z == e.z)
                                addPlayerOptions(i1, playerIndices[k2], playerEntity_1, l);
                        }

                    }
                    addPlayerOptions(i1, k1, e, l);
                }
                if (j1 == 3) {
                    LinkedList stack = objects[currentLevel][l][i1];
                    if (stack != null) {
                        for (ObjStackEntity objStackEntity = (ObjStackEntity) stack
                            .peekFirst(); objStackEntity != null; objStackEntity = (ObjStackEntity) stack
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

    public void updatePlayers(boolean flag) {
        ingame &= flag;
        for (int i = -1; i < playerCount; i++) {
            int j;
            if (i == -1)
                j = LOCAL_PLAYER_INDEX;
            else
                j = playerIndices[i];
            PlayerEntity playerEntity = playerEntities[j];
            if (playerEntity != null)
                updateEntity(playerEntity);
        }

        updatePlayersCounter++;
        if (updatePlayersCounter > 1406) {
            updatePlayersCounter = 0;
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

    public void drawTileHint() {
        if (hintType != 2) {
            return;
        }

        projectToScreen((hintTileZ - baseTileZ << 7) + hintOffsetZ, (hintTileX - baseTileX << 7) + hintOffsetX, hintHeight * 2);
        if (drawX > -1 && clientClock % 20 < 10) {
            headicons[2].draw(drawY - 28, drawX - 12);
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

    public void drawChatback() {
        areaChatback.bind();
        Draw3D.offsets = chatOffsets;
        chatback.draw(0, 0);
        if (showSocialInput) {
            fontBold12.drawRightAligned(40, 0, socialMessage, 239);
            fontBold12.drawRightAligned(60, 128, socialInput + "*", 239);
        } else if (chatbackInputType) {
            fontBold12.drawRightAligned(40, 0, "Enter amount:", 239);
            fontBold12.drawRightAligned(60, 128, chatbackInput + "*", 239);
        } else if (chatbackMessage != null) {
            fontBold12.drawRightAligned(40, 0, chatbackMessage, 239);
            fontBold12.drawRightAligned(60, 128, "Click to continue", 239);
        } else if (chatbackComponentId != -1)
            drawInterface(0, 0, InterfaceComponent.instances[chatbackComponentId], 0);
        else if (stickyChatbackComponentId != -1) {
            drawInterface(0, 0, InterfaceComponent.instances[stickyChatbackComponentId], 0);
        } else {
            IndexedFont indexedFont = fontPlain12;
            int i = 0;
            Draw2D.setBounds(77, 0, 463, 0);
            for (int j = 0; j < 100; j++)
                if (chatMessage[j] != null) {
                    int k = chatMessageType[j];
                    int l = (70 - i * 14) + anInt977;
                    if (k == 0) {
                        if (l > 0 && l < 110)
                            indexedFont.draw(4, l, 0, chatMessage[j]);
                        i++;
                    }
                    if (k == 1) {
                        if (l > 0 && l < 110) {
                            indexedFont.draw(4, l, 0xffffff, chatMessagePrefix[j] + ":");
                            indexedFont.draw(
                                12 + indexedFont.stringWidth(chatMessagePrefix[j]), l, 255,
                                chatMessage[j]);
                        }
                        i++;
                    }
                    if (k == 2 && (chatPublicSetting == 0 || chatPublicSetting == 1 && isFriend(chatMessagePrefix[j]))) {
                        if (l > 0 && l < 110) {
                            indexedFont.draw(4, l, 0, chatMessagePrefix[j] + ":");
                            indexedFont.draw(
                                12 + indexedFont.stringWidth(chatMessagePrefix[j]), l, 255,
                                chatMessage[j]);
                        }
                        i++;
                    }
                    if ((k == 3 || k == 7) && anInt833 == 0
                        && (k == 7 || chatPrivateSetting == 0 || chatPrivateSetting == 1 && isFriend(chatMessagePrefix[j]))) {
                        if (l > 0 && l < 110) {
                            indexedFont.draw(4, l, 0, "From " + chatMessagePrefix[j] + ":");
                            indexedFont.draw(
                                12 + indexedFont.stringWidth("From " + chatMessagePrefix[j]), l,
                                0x800000, chatMessage[j]);
                        }
                        i++;
                    }
                    if (k == 4 && (chatTradeDuelSetting == 0 || chatTradeDuelSetting == 1 && isFriend(chatMessagePrefix[j]))) {
                        if (l > 0 && l < 110)
                            indexedFont.draw(4, l, 0x800080,
                                chatMessagePrefix[j] + " " + chatMessage[j]);
                        i++;
                    }
                    if (k == 5 && anInt833 == 0 && chatPrivateSetting < 2) {
                        if (l > 0 && l < 110)
                            indexedFont.draw(4, l, 0x800000, chatMessage[j]);
                        i++;
                    }
                    if (k == 6 && anInt833 == 0 && chatPrivateSetting < 2) {
                        if (l > 0 && l < 110) {
                            indexedFont.draw(4, l, 0, "To " + chatMessagePrefix[j] + ":");
                            indexedFont.draw(
                                12 + indexedFont.stringWidth("To " + chatMessagePrefix[j]), l,
                                0x800000, chatMessage[j]);
                        }
                        i++;
                    }
                    if (k == 8 && (chatTradeDuelSetting == 0 || chatTradeDuelSetting == 1 && isFriend(chatMessagePrefix[j]))) {
                        if (l > 0 && l < 110)
                            indexedFont.draw(4, l, 0xcbb789,
                                chatMessagePrefix[j] + " " + chatMessage[j]);
                        i++;
                    }
                }

            Draw2D.resetBounds();
            anInt792 = i * 14 + 7;
            if (anInt792 < 78)
                anInt792 = 78;
            drawScrollbar(463, 0, anInt792 - anInt977 - 77, anInt792, 77);
            indexedFont.draw(4, 90, 0, StringUtils.formatName(username) + ":");
            indexedFont.draw(6 + indexedFont.stringWidth(username + ": "), 90,
                255, aString1137 + "*");
            Draw2D.drawHorizontalLine(0, 77, 479, 0);
        }
        if (menuVisible && mouseArea == 2)
            drawMenu();
        areaChatback.drawImage(375, super.graphics, 22);
        areaViewport.bind();
        Draw3D.offsets = viewportOffsets;
    }

    public boolean readPacket() {
        if (stream == null) {
            return false;
        }

        try {
            int available = stream.available();
            if (available == 0) {
                return false;
            }

            if (packetOpcode == -1) {
                stream.read(inBuffer.data, 0, 1);
                packetOpcode = inBuffer.data[0] & 0xff;
                if (isaacState != null) {
                    packetOpcode = packetOpcode - isaacState.nextInt() & 0xff;
                }
                packetLength = Packet.PACKET_LENGTH[packetOpcode];
                available--;
            }

            if (packetLength == -1) {
                if (available > 0) {
                    stream.read(inBuffer.data, 0, 1);
                    packetLength = inBuffer.data[0] & 0xff;
                    available--;
                } else {
                    return false;
                }
            }

            if (packetLength == -2) {
                if (available > 1) {
                    stream.read(inBuffer.data, 0, 2);
                    inBuffer.offset = 0;
                    packetLength = inBuffer.readWord();
                    available -= 2;
                } else {
                    return false;
                }
            }

            if (available < packetLength) {
                return false;
            }

            inBuffer.offset = 0;
            stream.read(inBuffer.data, 0, packetLength);

            netIdleCycles = 0;
            thirdMostRecentOpcode = secondMostRecentOpcode;
            secondMostRecentOpcode = lastPacketOpcode;
            lastPacketOpcode = packetOpcode;

            if (packetOpcode == Packet.INTERFACE_SETTING_BYTE) {
                int j = inBuffer.readWord();
                byte byte0 = inBuffer.readByteSigned();
                anIntArray1075[j] = byte0;
                if (variables[j] != byte0) {
                    variables[j] = byte0;
                    updateVarp(j);
                    sidebarRedraw = true;
                    if (stickyChatbackComponentId != -1)
                        redrawChatback = true;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.ADD_FRIEND) {
                long l = inBuffer.readQWord();
                int j16 = inBuffer.readByte();
                String s6 = StringUtils.formatName(StringUtils.fromBase37(l));
                for (int l24 = 0; l24 < friendCount; l24++) {
                    if (l != friendName37[l24])
                        continue;
                    if (friendWorld[l24] != j16) {
                        friendWorld[l24] = j16;
                        sidebarRedraw = true;
                        if (j16 > 0)
                            addMessage(5, s6 + " has logged in.", "");
                        if (j16 == 0)
                            addMessage(5, s6 + " has logged out.", "");
                    }
                    s6 = null;
                    break;
                }

                if (s6 != null && friendCount < 100) {
                    friendName37[friendCount] = l;
                    friendName[friendCount] = s6;
                    friendWorld[friendCount] = j16;
                    friendCount++;
                    sidebarRedraw = true;
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
                            sidebarRedraw = true;
                            flag5 = false;
                        }

                }

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.SYSTEM_UPDATE) {
                systemUpdateTimer = inBuffer.readWord() * 30;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.DATA_LAND_DONE) {
                int k = inBuffer.readByte();
                int i10 = inBuffer.readByte();
                int k16 = -1;
                for (int k21 = 0; k21 < sceneMapIndex.length; k21++)
                    if (sceneMapIndex[k21] == (k << 8) + i10)
                        k16 = k21;

                if (k16 != -1) {
                    Signlink.cachesave("m" + k + "_" + i10, sceneMapLandData[k16]);
                    sceneState = 1;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.UPDATE_NPCS) {
                updateNpcs(inBuffer, packetLength);
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.LOAD_AREA) {
                int i1 = inBuffer.readWord();
                int j10 = inBuffer.readWord();
                if (anInt838 == i1 && anInt839 == j10 && sceneState != 0) {
                    packetOpcode = -1;
                    return true;
                }
                anInt838 = i1;
                anInt839 = j10;
                baseTileX = (anInt838 - 6) * 8;
                baseTileZ = (anInt839 - 6) * 8;
                sceneState = 1;
                areaViewport.bind();
                fontPlain12.drawRightAligned(151, 0, "Loading - please wait.", 257);
                fontPlain12.drawRightAligned(150, 0xffffff, "Loading - please wait.", 256);
                areaViewport.drawImage(11, super.graphics, 8);
                Signlink.looprate(5);
                int l16 = (packetLength - 2) / 10;
                sceneMapLandData = new byte[l16][];
                sceneMapLocData = new byte[l16][];
                sceneMapIndex = new int[l16];
                outBuffer.writeOpcode(150);
                outBuffer.writeByte(0);
                int i22 = 0;
                for (int i25 = 0; i25 < l16; i25++) {
                    int i27 = inBuffer.readByte();
                    int j29 = inBuffer.readByte();
                    int k30 = inBuffer.readDWord();
                    int i31 = inBuffer.readDWord();
                    sceneMapIndex[i25] = (i27 << 8) + j29;
                    if (k30 != 0) {
                        byte[] abyte1 = Signlink.cacheload("m" + i27 + "_" + j29);
                        if (abyte1 != null) {
                            crc32.reset();
                            crc32.update(abyte1);
                            if ((int) crc32.getValue() != k30)
                                abyte1 = null;
                        }
                        if (abyte1 != null) {
                            sceneMapLandData[i25] = abyte1;
                        } else {
                            sceneState = 0;
                            outBuffer.writeByte(0);
                            outBuffer.writeByte(i27);
                            outBuffer.writeByte(j29);
                            i22 += 3;
                        }
                    }
                    if (i31 != 0) {
                        byte[] abyte2 = Signlink.cacheload("l" + i27 + "_" + j29);
                        if (abyte2 != null) {
                            crc32.reset();
                            crc32.update(abyte2);
                            if ((int) crc32.getValue() != i31)
                                abyte2 = null;
                        }
                        if (abyte2 != null) {
                            sceneMapLocData[i25] = abyte2;
                        } else {
                            sceneState = 0;
                            outBuffer.writeByte(1);
                            outBuffer.writeByte(i27);
                            outBuffer.writeByte(j29);
                            i22 += 3;
                        }
                    }
                }

                outBuffer.writeSize(i22);
                Signlink.looprate(50);
                areaViewport.bind();
                if (sceneState == 0) {
                    fontPlain12.drawRightAligned(166, 0,
                        "Map area updated since last visit, so load will take longer this time only", 257);
                    fontPlain12.drawRightAligned(165, 0xffffff,
                        "Map area updated since last visit, so load will take longer this time only", 256);
                }
                areaViewport.drawImage(11, super.graphics, 8);
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
                cutsceneActive = false;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_PLAYERHEAD) {
                int j1 = inBuffer.readWord();
                InterfaceComponent.instances[j1].modelDisabled = self.getHeadModel();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.MOB_HINT) {
                hintType = inBuffer.readByte();
                if (hintType == 1)
                    anInt801 = inBuffer.readWord();
                if (hintType >= 2 && hintType <= 6) {
                    if (hintType == 2) {
                        hintOffsetX = 64;
                        hintOffsetZ = 64;
                    }
                    if (hintType == 3) {
                        hintOffsetX = 0;
                        hintOffsetZ = 64;
                    }
                    if (hintType == 4) {
                        hintOffsetX = 128;
                        hintOffsetZ = 64;
                    }
                    if (hintType == 5) {
                        hintOffsetX = 64;
                        hintOffsetZ = 0;
                    }
                    if (hintType == 6) {
                        hintOffsetX = 64;
                        hintOffsetZ = 128;
                    }
                    hintType = 2;
                    hintTileX = inBuffer.readWord();
                    hintTileZ = inBuffer.readWord();
                    hintHeight = inBuffer.readByte();
                }
                if (hintType == 10)
                    anInt1076 = inBuffer.readWord();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.PLAY_SONG) {
                String s = inBuffer.readString();
                int k10 = inBuffer.readDWord();
                int i17 = inBuffer.readDWord();
                if (!s.equals(aString1119) && aBoolean835 && !lowMemory)
                    setMidi(k10, s, i17);
                aString1119 = s;
                anInt1110 = k10;
                anInt1155 = i17;
                anInt744 = 0;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.LOGOUT) {
                disconnect();
                packetOpcode = -1;
                return false;
            }
            if (packetOpcode == Packet.DATA_LOC_DONE) {
                int k1 = inBuffer.readByte();
                int l10 = inBuffer.readByte();
                int j17 = -1;
                for (int j22 = 0; j22 < sceneMapIndex.length; j22++)
                    if (sceneMapIndex[j22] == (k1 << 8) + l10)
                        j17 = j22;

                if (j17 != -1) {
                    Signlink.cachesave("l" + k1 + "_" + l10, sceneMapLocData[j17]);
                    sceneState = 1;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.CLEAR_WALKING_QUEUE) {
                flagTileX = 0;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.PLAYER_INFO) {
                anInt734 = inBuffer.readWord();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.UPDATE_OBJECT_STACK || packetOpcode == Packet.ATTACH_TEMPORARY_LOCATION_TO_PLAYER || packetOpcode == Packet.ADD_PRIVATE_OBJECT_STACK || packetOpcode == Packet.ADD_SPOT_ANIMATION || packetOpcode == Packet.ADD_PROJECTILE
                || packetOpcode == Packet.REMOVE_OBJECT_STACK || packetOpcode == Packet.ADD_OBJECT_STACK || packetOpcode == Packet.ADD_ANIMATED_LOCATION || packetOpcode == Packet.REMOVE_LOCATION || packetOpcode == Packet.ADD_LOCATION) {
                readSecondaryPacket(inBuffer, packetOpcode);
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_CHILD) {
                int l1 = inBuffer.readWord();
                int i11 = inBuffer.readWord();
                if (chatbackComponentId != -1) {
                    chatbackComponentId = -1;
                    redrawChatback = true;
                }
                if (chatbackInputType) {
                    chatbackInputType = false;
                    redrawChatback = true;
                }
                viewportInterfaceIndex = l1;
                sidebarInterfaceId = i11;
                sidebarRedraw = true;
                sidebarRedrawIcons = true;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_SETTING_DWORD) {
                int i2 = inBuffer.readWord();
                int j11 = inBuffer.readDWord();
                anIntArray1075[i2] = j11;
                if (variables[i2] != j11) {
                    variables[i2] = j11;
                    updateVarp(i2);
                    sidebarRedraw = true;
                    if (stickyChatbackComponentId != -1)
                        redrawChatback = true;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_ANIMATE) {
                int j2 = inBuffer.readWord();
                int k11 = inBuffer.readWord();
                InterfaceComponent.instances[j2].seqId = k11;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_SIDEBAR) {
                int k2 = inBuffer.readWord();
                int l11 = inBuffer.readByte();
                if (k2 == 65535)
                    k2 = -1;
                tabComponentId[l11] = k2;
                sidebarRedraw = true;
                sidebarRedrawIcons = true;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.DATA_LOC) {
                int l2 = inBuffer.readByte();
                int i12 = inBuffer.readByte();
                int k17 = inBuffer.readWord();
                int k22 = inBuffer.readWord();
                int j25 = -1;
                for (int k27 = 0; k27 < sceneMapIndex.length; k27++)
                    if (sceneMapIndex[k27] == (l2 << 8) + i12)
                        j25 = k27;

                if (j25 != -1) {
                    if (sceneMapLocData[j25] == null || sceneMapLocData[j25].length != k22)
                        sceneMapLocData[j25] = new byte[k22];
                    inBuffer.readBytes(packetLength - 6, k17, sceneMapLocData[j25]);
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.FINISH_TRACKING) {
                Buffer buf = InputTracking.flushAndDisable();
                if (buf != null) {
                    outBuffer.writeOpcode(81);
                    outBuffer.writeWord(buf.offset);
                    outBuffer.writeBytes(buf.data, buf.offset, 0
                    );
                    buf.release();
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_ITEM_SLOT) {
                sidebarRedraw = true;
                int i3 = inBuffer.readWord();
                InterfaceComponent interfaceComponent = InterfaceComponent.instances[i3];
                int l17 = inBuffer.readByte();
                for (int l22 = 0; l22 < l17; l22++) {
                    interfaceComponent.inventoryIndices[l22] = inBuffer.readWord();
                    int k25 = inBuffer.readByte();
                    if (k25 == 255)
                        k25 = inBuffer.readDWord();
                    interfaceComponent.inventoryAmount[l22] = k25;
                }

                for (int l25 = l17; l25 < interfaceComponent.inventoryIndices.length; l25++) {
                    interfaceComponent.inventoryIndices[l25] = 0;
                    interfaceComponent.inventoryAmount[l25] = 0;
                }

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.ENABLE_TRACKING) {
                InputTracking.setEnabled();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_AMOUNT) {
                showSocialInput = false;
                chatbackInputType = true;
                chatbackInput = "";
                redrawChatback = true;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_ITEMS_CLEAR) {
                int j3 = inBuffer.readWord();
                InterfaceComponent interfaceComponent_1 = InterfaceComponent.instances[j3];
                for (int i18 = 0; i18 < interfaceComponent_1.inventoryIndices.length; i18++) {
                    interfaceComponent_1.inventoryIndices[i18] = -1;
                    interfaceComponent_1.inventoryIndices[i18] = 0;
                }

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.SHOW_WELCOME) {
                anInt1061 = inBuffer.readDWord();
                anInt873 = inBuffer.readWord();
                anInt901 = inBuffer.readByte();
                anInt1054 = inBuffer.readWord();
                if (anInt1061 != 0 && viewportInterfaceIndex == -1) {
                    Signlink.dnslookup(StringUtils.fromIPv4(anInt1061));
                    closeInterface();
                    char c = '\u028A';
                    if (anInt901 != 201)
                        c = '\u028F';
                    aString970 = "";
                    aBoolean881 = false;
                    for (int j12 = 0; j12 < InterfaceComponent.instances.length; j12++) {
                        if (InterfaceComponent.instances[j12] == null || InterfaceComponent.instances[j12].contentType != c)
                            continue;
                        viewportInterfaceIndex = InterfaceComponent.instances[j12].parent;
                        break;
                    }

                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_SIDEBAR_FLASH) {
                anInt1128 = inBuffer.readByte();
                if (anInt1128 == selectedTab) {
                    if (anInt1128 == 3)
                        selectedTab = 1;
                    else
                        selectedTab = 3;
                    sidebarRedraw = true;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.PLAY_JINGLE) {
                if (aBoolean835 && !lowMemory) {
                    int k3 = inBuffer.readWord();
                    int k12 = inBuffer.readDWord();
                    int j18 = packetLength - 6;
                    byte[] abyte0 = new byte[k12];
                    BZip2InputStream.read(abyte0, k12, inBuffer.data, j18,
                        inBuffer.offset);
                    midisave(abyte0, k12, false);
                    anInt744 = k3;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.DISPLAY_MULTI_ICON) {
                inMultizone = inBuffer.readByte();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.PLAY_SOUND) {
                int l3 = inBuffer.readWord();
                int l12 = inBuffer.readByte();
                int k18 = inBuffer.readWord();
                if (aBoolean1153 && !lowMemory && waveCount < 50) {
                    waveId[waveCount] = l3;
                    waveLoops[waveCount] = l12;
                    waveDelay[waveCount] = k18 + SoundTrack.delays[l3];
                    waveCount++;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_NPCHEAD) {
                int i4 = inBuffer.readWord();
                int i13 = inBuffer.readWord();
                NpcType npcType = NpcType.get(i13);
                InterfaceComponent.instances[i4].modelDisabled = npcType.getHeadModel();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.PLAYER_POSITION) {
                anInt862 = inBuffer.readByte();
                anInt863 = inBuffer.readByte();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_MODEL_RECOLOR) {
                int index = inBuffer.readWord();
                int from = inBuffer.readWord();
                int to = inBuffer.readWord();
                InterfaceComponent c = InterfaceComponent.instances[index];
                Model m = c.modelDisabled;
                if (m != null) {
                    m.recolor(from, to);
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_PRIVACY) {
                chatPublicSetting = inBuffer.readByte();
                chatPrivateSetting = inBuffer.readByte();
                chatTradeDuelSetting = inBuffer.readByte();
                chatRedrawSettings = true;
                redrawChatback = true;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_INVENTORY) {
                int k4 = inBuffer.readWord();
                resetParentComponentSeq(k4);
                if (chatbackComponentId != -1) {
                    chatbackComponentId = -1;
                    redrawChatback = true;
                }
                if (chatbackInputType) {
                    chatbackInputType = false;
                    redrawChatback = true;
                }
                sidebarInterfaceId = k4;
                sidebarRedraw = true;
                sidebarRedrawIcons = true;
                viewportInterfaceIndex = -1;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_CHATBOX) {
                int l4 = inBuffer.readWord();
                resetParentComponentSeq(l4);
                if (sidebarInterfaceId != -1) {
                    sidebarInterfaceId = -1;
                    sidebarRedraw = true;
                    sidebarRedrawIcons = true;
                }
                chatbackComponentId = l4;
                redrawChatback = true;
                viewportInterfaceIndex = -1;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_XY) {
                int i5 = inBuffer.readWord();
                int k13 = inBuffer.readWordSigned();
                int i19 = inBuffer.readWordSigned();
                InterfaceComponent interfaceComponent_4 = InterfaceComponent.instances[i5];
                interfaceComponent_4.x = k13;
                interfaceComponent_4.y = i19;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.CAMERA_SPIN) {
                cutsceneActive = true;
                anInt728 = inBuffer.readByte();
                anInt729 = inBuffer.readByte();
                anInt730 = inBuffer.readWord();
                anInt731 = inBuffer.readByte();
                anInt732 = inBuffer.readByte();
                if (anInt732 >= 100) {
                    cameraX = anInt728 * 128 + 64;
                    cameraZ = anInt729 * 128 + 64;
                    cameraY = getLandY(currentLevel, anInt728, anInt729) - anInt730;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.GROUND_ITEM_REMOVE_ALL) {
                anInt862 = inBuffer.readByte();
                anInt863 = inBuffer.readByte();
                for (int j5 = anInt862; j5 < anInt862 + 8; j5++) {
                    for (int l13 = anInt863; l13 < anInt863 + 8; l13++)
                        if (objects[currentLevel][j5][l13] != null) {
                            objects[currentLevel][j5][l13] = null;
                            updateObjectStack(j5, l13);
                        }

                }

                for (SpawnedLoc spawnedLoc = (SpawnedLoc) spawnedLocations
                    .peekLast(); spawnedLoc != null; spawnedLoc = (SpawnedLoc) spawnedLocations.getPrevious())
                    if (spawnedLoc.tileX >= anInt862 && spawnedLoc.tileX < anInt862 + 8
                        && spawnedLoc.tileZ >= anInt863 && spawnedLoc.tileZ < anInt863 + 8
                        && spawnedLoc.level == currentLevel) {
                        addLoc(spawnedLoc.lastRotation, spawnedLoc.tileX, spawnedLoc.tileZ,
                            spawnedLoc.classType, spawnedLoc.lastLocIndex, spawnedLoc.lastType,
                            spawnedLoc.level);
                        spawnedLoc.unlink();
                    }

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.DATA_LAND) {
                int k5 = inBuffer.readByte();
                int i14 = inBuffer.readByte();
                int j19 = inBuffer.readWord();
                int i23 = inBuffer.readWord();
                int i26 = -1;
                for (int l27 = 0; l27 < sceneMapIndex.length; l27++)
                    if (sceneMapIndex[l27] == (k5 << 8) + i14)
                        i26 = l27;

                if (i26 != -1) {
                    if (sceneMapLandData[i26] == null || sceneMapLandData[i26].length != i23)
                        sceneMapLandData[i26] = new byte[i23];
                    inBuffer.readBytes(packetLength - 6, j19, sceneMapLandData[i26]);
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.PRIVATE_MESSAGE) {
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
                        String s7 = TextEncoder.read(inBuffer, packetLength - 13);
                        s7 = WordEncoding.getFiltered(s7);
                        if (j23 > 1)
                            addMessage(7, s7, StringUtils.formatName(StringUtils.fromBase37(l5)));
                        else
                            addMessage(3, s7, StringUtils.formatName(StringUtils.fromBase37(l5)));
                    } catch (Exception exception1) {
                        Signlink.reporterror("cde1");
                    }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_SETTINGS_RESET) {
                for (int i6 = 0; i6 < variables.length; i6++)
                    if (variables[i6] != anIntArray1075[i6]) {
                        variables[i6] = anIntArray1075[i6];
                        updateVarp(i6);
                        sidebarRedraw = true;
                    }

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_MODEL) {
                int j6 = inBuffer.readWord();
                int j14 = inBuffer.readWord();
                InterfaceComponent.instances[j6].modelDisabled = new Model(j14);
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_DIALOGUE) {
                int k6 = inBuffer.readWordSigned();
                stickyChatbackComponentId = k6;
                redrawChatback = true;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.PLAYER_WEIGHT) {
                if (selectedTab == 12)
                    sidebarRedraw = true;
                energy = inBuffer.readByte();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.CAMERA_CUTSCENE) {
                cutsceneActive = true;
                anInt948 = inBuffer.readByte();
                anInt949 = inBuffer.readByte();
                anInt950 = inBuffer.readWord();
                anInt951 = inBuffer.readByte();
                anInt952 = inBuffer.readByte();
                if (anInt952 >= 100) {
                    int l6 = anInt948 * 128 + 64;
                    int k14 = anInt949 * 128 + 64;
                    int l19 = getLandY(currentLevel, anInt948, anInt949) - anInt950;
                    int k23 = l6 - cameraX;
                    int j26 = l19 - cameraY;
                    int j28 = k14 - cameraZ;
                    int i30 = (int) Math.sqrt(k23 * k23 + j28 * j28);
                    cameraPitch = (int) (Math.atan2(j26, i30) * 325.94900000000001D) & 0x7ff;
                    cameraOrbitYaw = (int) (Math.atan2(k23, j28) * -325.94900000000001D) & 0x7ff;
                    if (cameraPitch < 128)
                        cameraPitch = 128;
                    if (cameraPitch > 383)
                        cameraPitch = 383;
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_SIDEBAR_FOCUS) {
                selectedTab = inBuffer.readByte();
                sidebarRedraw = true;
                sidebarRedrawIcons = true;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.SEND_MESSAGE) {
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
                        addMessage(4, "wishes to trade with you.", s3);
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
                        addMessage(8, "wishes to duel with you.", s4);
                } else {
                    addMessage(0, s1, "");
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_ITEM_MODEL) {
                int i7 = inBuffer.readWord();
                int l14 = inBuffer.readWord();
                int i20 = inBuffer.readWord();
                ObjType objType = ObjType.get(l14);
                InterfaceComponent.instances[i7].modelDisabled = objType.getModel(50);
                InterfaceComponent.instances[i7].modelEyePitch = objType.iconCameraPitch;
                InterfaceComponent.instances[i7].modelYaw = objType.iconYaw;
                InterfaceComponent.instances[i7].modelZoom = (objType.iconZoom * 100) / i20;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_OPEN) {
                int j7 = inBuffer.readWord();
                resetParentComponentSeq(j7);
                if (sidebarInterfaceId != -1) {
                    sidebarInterfaceId = -1;
                    sidebarRedraw = true;
                    sidebarRedrawIcons = true;
                }
                if (chatbackComponentId != -1) {
                    chatbackComponentId = -1;
                    redrawChatback = true;
                }
                if (chatbackInputType) {
                    chatbackInputType = false;
                    redrawChatback = true;
                }
                viewportInterfaceIndex = j7;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_COLOR) {
                int k7 = inBuffer.readWord();
                int i15 = inBuffer.readWord();
                int j20 = i15 >> 10 & 0x1f;
                int l23 = i15 >> 5 & 0x1f;
                int k26 = i15 & 0x1f;
                InterfaceComponent.instances[k7].color = (j20 << 19) + (l23 << 11) + (k26 << 3);
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.RESET_ANIMATIONS) {
                for (int l7 = 0; l7 < playerEntities.length; l7++)
                    if (playerEntities[l7] != null)
                        playerEntities[l7].primarySeq = -1;

                for (int j15 = 0; j15 < npcEntities.length; j15++)
                    if (npcEntities[j15] != null)
                        npcEntities[j15].primarySeq = -1;

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_HOVER) {
                int i8 = inBuffer.readWord();
                boolean flag1 = inBuffer.readByte() == 1;
                InterfaceComponent.instances[i8].hidden = flag1;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.ADD_IGNORE) {
                ignoreCount = packetLength / 8;
                for (int j8 = 0; j8 < ignoreCount; j8++)
                    ignoreName37[j8] = inBuffer.readQWord();

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.CAMERA_RESET) {
                cutsceneActive = false;
                for (int k8 = 0; k8 < 5; k8++)
                    customCameraActive[k8] = false;

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_CLEAR) {
                if (sidebarInterfaceId != -1) {
                    sidebarInterfaceId = -1;
                    sidebarRedraw = true;
                    sidebarRedrawIcons = true;
                }
                if (chatbackComponentId != -1) {
                    chatbackComponentId = -1;
                    redrawChatback = true;
                }
                if (chatbackInputType) {
                    chatbackInputType = false;
                    redrawChatback = true;
                }
                viewportInterfaceIndex = -1;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_TEXT) {
                int l8 = inBuffer.readWord();
                String s5 = inBuffer.readString();
                InterfaceComponent.instances[l8].text = s5;
                if (InterfaceComponent.instances[l8].parent == tabComponentId[selectedTab])
                    sidebarRedraw = true;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.GROUND_ITEM_ADD) {
                sidebarRedraw = true;
                int i9 = inBuffer.readByte();
                int k15 = inBuffer.readDWord();
                int k20 = inBuffer.readByte();
                skillExperience[i9] = k15;
                skillLevelReal[i9] = k20;
                skillLevel[i9] = 1;
                for (int i24 = 0; i24 < 98; i24++)
                    if (k15 >= EXPERIENCE_TABLE[i24])
                        skillLevel[i9] = i24 + 2;

                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.BATCH_PACKETS) {
                anInt862 = inBuffer.readByte();
                anInt863 = inBuffer.readByte();
                while (inBuffer.offset < packetLength) {
                    int j9 = inBuffer.readByte();
                    readSecondaryPacket(inBuffer, j9);
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.PLAYER_ENERGY) {
                if (selectedTab == 12)
                    sidebarRedraw = true;
                weightCarried = inBuffer.readWordSigned();
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.CAMERA_SHAKE) {
                int k9 = inBuffer.readByte();
                int l15 = inBuffer.readByte();
                int i21 = inBuffer.readByte();
                int j24 = inBuffer.readByte();
                customCameraActive[k9] = true;
                cameraJitter[k9] = l15;
                cameraAmplitude[k9] = i21;
                cameraFrequency[k9] = j24;
                unknownCameraVariable[k9] = 0;
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.INTERFACE_ITEM_ARRAY) {
                sidebarRedraw = true;
                int l9 = inBuffer.readWord();
                InterfaceComponent interfaceComponent_2 = InterfaceComponent.instances[l9];
                while (inBuffer.offset < packetLength) {
                    int j21 = inBuffer.readByte();
                    int k24 = inBuffer.readWord();
                    int l26 = inBuffer.readByte();
                    if (l26 == 255)
                        l26 = inBuffer.readDWord();
                    if (j21 >= 0 && j21 < interfaceComponent_2.inventoryIndices.length) {
                        interfaceComponent_2.inventoryIndices[j21] = k24;
                        interfaceComponent_2.inventoryAmount[j21] = l26;
                    }
                }
                packetOpcode = -1;
                return true;
            }
            if (packetOpcode == Packet.UPDATE_PLAYERS) {
                updatePlayers(inBuffer, packetLength);
                if (sceneState == 1) {
                    sceneState = 2;
                    SceneBuilder.levelBuilt = currentLevel;
                    createScene();
                }
                if (lowMemory && sceneState == 2 && SceneBuilder.levelBuilt != currentLevel) {
                    areaViewport.bind();
                    fontPlain12.drawRightAligned(151, 0, "Loading - please wait.", 257);
                    fontPlain12.drawRightAligned(150, 0xffffff, "Loading - please wait.", 256);
                    areaViewport.drawImage(11, super.graphics, 8);
                    SceneBuilder.levelBuilt = currentLevel;
                    createScene();
                }
                if (currentLevel != lastSceneLevel && sceneState == 2) {
                    lastSceneLevel = currentLevel;
                    createMinimap(currentLevel);
                }
                packetOpcode = -1;
                return true;
            }

            Signlink.reporterror("T1 - " + packetOpcode + "," + packetLength + " - " + secondMostRecentOpcode + "," + thirdMostRecentOpcode);
            disconnect();
        } catch (IOException _ex) {
            reconnect();
        } catch (Exception exception) {
            String s2 = "T2 - " + packetOpcode + "," + secondMostRecentOpcode + "," + thirdMostRecentOpcode + " - " + packetLength + ","
                + (baseTileX + self.pathTileX[0]) + ","
                + (baseTileZ + self.pathTileZ[0]) + " - ";
            for (int i16 = 0; i16 < packetLength && i16 < 50; i16++)
                s2 = s2 + inBuffer.data[i16] + ",";

            Signlink.reporterror(s2);
            disconnect();
        }
        return true;
    }

    public void drawChat2() {
        areaInvback.bind();
        Draw3D.offsets = sidebarOffsets;
        inback.draw(0, 0);
        if (sidebarInterfaceId != -1)
            drawInterface(0, 0, InterfaceComponent.instances[sidebarInterfaceId], 0);
        else if (tabComponentId[selectedTab] != -1)
            drawInterface(0, 0, InterfaceComponent.instances[tabComponentId[selectedTab]], 0);
        if (menuVisible && mouseArea == 1)
            drawMenu();
        areaInvback.drawImage(231, super.graphics, 562);
        areaViewport.bind();
        Draw3D.offsets = viewportOffsets;
    }

    public boolean isFriend(String s) {
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
        initApplet(532, 789);
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
            addMessage(2, p.spoken, p.name);
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
                            addMessage(1, s, p.name);
                        else
                            addMessage(2, s, p.name);
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
        prepareTitleScreen();
        if (titleArchive == null) {
            super.showProgress(true, s, i);
            return;
        }

        char c = '\u0168';
        char c1 = '\310';
        byte byte0 = 20;

        titleCenter.bind();
        fontBold12.drawRightAligned(c1 / 2 - 26 - byte0, 0xffffff, "RuneScape is loading - please wait...", c / 2);

        int j = c1 / 2 - 18 - byte0;
        Draw2D.drawRect(c / 2 - 152, 0x8c1111, 34, j, 304);
        Draw2D.drawRect(c / 2 - 151, 0, 32, j + 1, 302);
        Draw2D.fillRect(j + 2, c / 2 - 150, 0x8c1111, i * 3, 30);
        Draw2D.fillRect(j + 2, (c / 2 - 150) + i * 3, 0, 300 - i * 3, 30);

        fontBold12.drawRightAligned((c1 / 2 + 5) - byte0, 0xffffff, s, c / 2);
        titleCenter.drawImage(186, super.graphics, 214);

        if (redrawTitleBackground) {
            redrawTitleBackground = false;

            if (!flameActive) {
                titleLeft.drawImage(0, super.graphics, 0);
                titleRight.drawImage(0, super.graphics, 661);
            }

            titleTop.drawImage(0, super.graphics, 128);
            titleBottom.drawImage(386, super.graphics, 214);
            titleBottomLeft.drawImage(265, super.graphics, 0);
            titleBottomRight.drawImage(265, super.graphics, 574);
            titleLeftSpace.drawImage(186, super.graphics, 128);
            titleRightSpace.drawImage(186, super.graphics, 574);
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
            game.initFrame(532, 789);
        } catch (Exception exception) {
        }
    }

    public Game() {
    }

    public static int anticheatCounter1;
    public static String ASCII_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
    public int midiInt2;
    public int anInt728;
    public int anInt729;
    public int anInt730;
    public int anInt731;
    public int anInt732;
    public int anInt734 = -1;
    public int[] chatOffsets;
    public int[] sidebarOffsets;
    public int[] viewportOffsets;
    public int crossX;
    public int crossY;
    public int crossCycle;
    public int crossType;
    public int[] characterDesignColors = new int[5];
    public Buffer loginBuffer = Buffer.reserve(1);
    public int anInt744;
    public int hintTileX;
    public int hintTileZ;
    public int hintHeight;
    public int hintOffsetX;
    public int hintOffsetZ;
    public int minimapOffsetCycle;
    public boolean redrawTitleBackground = false;
    public LinkedList list = new LinkedList();
    public IsaacRandom isaacState;
    public boolean[] customCameraActive = new boolean[5];
    public int chatPrivateSetting;
    public int selectedTab = 3;
    public int[][] pathDistance = new int[104][104];
    public int socialAction;
    public int baseTileX;
    public int baseTileZ;
    public int anInt763;
    public int anInt764;
    public String socialInput = "";
    public LinkedList temporaryLocs = new LinkedList();
    public long[] ignoreName37 = new long[100];
    public int weightCarried;
    public byte[][] sceneMapLandData;
    public static int anInt772;
    public int[] friendWorld = new int[100];
    public int lastSceneLevel = -1;
    public String socialMessage = "";
    public Sprite[] hitmarks = new Sprite[20];
    public long lastWaveStartTime;
    public int packetLength;
    public int packetOpcode;
    public int netIdleCycles;
    public int anInt782;
    public int idleTimeout;
    public String chatbackInput = "";
    public int cameraOffsetCycle;
    public int lastWaveId = -1;
    public boolean characterDesignUpdate = false;
    public int[] characterDesigns = new int[7];
    public Sprite[] activeMapFunctions = new Sprite[1000];
    public int anInt792 = 78;
    public int ignoreCount;
    public int[][][] levelHeightMaps;
    public Buffer inBuffer = Buffer.reserve(1);
    public static int anInt797;
    public Buffer outBuffer = Buffer.reserve(1);
    public boolean aBoolean799 = false;
    public int chatEffects;
    public int anInt801;
    public int anInt802;
    public static int drawViewportCounter;
    public static int anInt806;
    public int[] skillLevelReal = new int[50];
    public InterfaceComponent interfaceComponent = new InterfaceComponent();
    public int[] waveLoops = new int[50];
    public int anInt810;
    public int[] archiveChecksums = new int[9];
    public boolean aBoolean812 = true;
    public IndexedSprite[] sideicons = new IndexedSprite[13];
    public int lastWaveLength;
    public int cameraOrbitPitch = 128;
    public int cameraYaw;
    public int cameraYawModifier;
    public int cameraPitchModifier;
    public int MAX_PLAYER_COUNT = 2048;
    public int LOCAL_PLAYER_INDEX = 2047;
    public PlayerEntity[] playerEntities = new PlayerEntity[MAX_PLAYER_COUNT];
    public int playerCount;
    public int[] playerIndices = new int[MAX_PLAYER_COUNT];
    public int updateCount;
    public int[] entityUpdateIndices = new int[MAX_PLAYER_COUNT];
    public Buffer[] playerBuffers = new Buffer[MAX_PLAYER_COUNT];
    public int lastPacketOpcode;
    public int secondMostRecentOpcode;
    public int thirdMostRecentOpcode;
    public Scene scene;
    public LinkedList projectiles = new LinkedList();
    public int anInt833;
    public String[] options = new String[500];
    public boolean aBoolean835 = true;
    public boolean characterDesignIsMale = true;
    public int anInt837;
    public int anInt838;
    public int anInt839;
    public byte[][][] levelRenderFlags;
    public int[] flameBuffer1;
    public int[] flameBuffer2;
    public int objDragComponentId;
    public int objDragSlot;
    public int objDragArea;
    public int objGrabX;
    public int objGrabY;
    public int[] flameShiftX = new int[256];
    public DrawArea areaBackbase1;
    public DrawArea areaBackbase2;
    public DrawArea areaBackhmid1;
    public int anInt855;
    public int[] compassLeft = new int[33];
    public static int anInt857;
    public int[] waveDelay = new int[50];
    public int chatHoveredInterfaceIndex;
    public int[] tabComponentId = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1
    };
    public int anInt862;
    public int anInt863;
    public static int[] EXPERIENCE_TABLE;
    public boolean errorLoading = false;
    public static int anInt867;
    public int hoveredInterfaceIndex;
    public boolean showSocialInput = false;
    public boolean chatContinuingDialogue = false;
    public int anInt873;
    public int flameCycle1;
    public int flameCycle2;
    public static int anInt876;
    public int[] anIntArray878 = new int[100];
    public boolean menuVisible = false;
    public int currentLevel;
    public boolean aBoolean881 = false;
    public static int sidebarClickedCounter;
    public LinkedList spawnedLocations = new LinkedList();
    public int chatTradeDuelSetting;
    public static int nodeid = 10;
    public static int portoff;
    public static boolean members = true;
    public static boolean lowMemory;
    public static int anInt890;
    public IndexedSprite redstone1;
    public IndexedSprite redstone2;
    public IndexedSprite redstone3;
    public IndexedSprite redstone1h;
    public IndexedSprite redstone2h;
    public int[] chatMessageType = new int[100];
    public String[] chatMessagePrefix = new String[100];
    public String[] chatMessage = new String[100];
    public long aLong900;
    public int anInt901;
    public boolean flameActive = false;
    public int[] flameGradient;
    public int[] flameGradientRed;
    public int[] flameGradientGreen;
    public int[] flameGradientViolet;
    public int anInt907 = -1;
    public IndexedSprite backbase1;
    public IndexedSprite backbase2;
    public IndexedSprite backhmid1;
    public int hintType;
    public static int updatePlayersCounter;
    public int cameraOrbitX;
    public int cameraOrbitZ;
    public int anInt916;
    public int activeMapFunctionCount;
    public int[] activeMapFunctionX = new int[1000];
    public int[] activeMapFunctionZ = new int[1000];
    public int[][] anIntArrayArray920 = new int[104][104];
    public boolean chatRedrawSettings = false;
    public static BigInteger exponent = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");
    public boolean errorHost = false;
    public int objDragCycles;
    public int[] sceneMapIndex;
    public int[] skillLevel = new int[50];
    public NpcEntity[] npcEntities = new NpcEntity[8192];
    public int npcCount;
    public int[] npcIndices = new int[8192];
    public int minimapZoom;
    public int minimapZoomModifier = 1;
    public int cameraMaxY;
    public int anInt933;
    public int dragCycle;
    public String chatbackMessage;
    public static int anInt937;
    public int[] variables = new int[2000];
    public int deadEntityCount;
    public int[] deadEntityIndices = new int[1000];
    public int sidebarHoveredInterfaceIndex;
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
    public long[] friendName37 = new long[100];
    public int selectedCycle;
    public int anInt945;
    public int anInt946;
    public int selectedArea;
    public int anInt948;
    public int anInt949;
    public int anInt950;
    public int anInt951;
    public int anInt952;
    public int[] minimapLineWidth = new int[151];
    public CollisionMap[] collisionMaps = new CollisionMap[4];
    public static int clientClock;
    public Sprite[] headicons = new Sprite[20];
    public int systemUpdateTimer;
    public int[] cameraJitter = new int[5];
    public boolean aBoolean960 = false;
    public Sprite aClass38_Sub2_Sub2_Sub2_961;
    public Sprite aClass38_Sub2_Sub2_Sub2_962;
    public int midiInt1;
    public boolean sidebarRedraw = false;
    public boolean redrawChatback = false;
    public int[] cameraAmplitude = new int[5];
    public PlayerEntity self;
    public boolean cutsceneActive = false;
    public int sceneDelta;
    public String aString970 = "";
    public int viewportInterfaceIndex = -1;
    public int loginFocusedLine;
    public IndexedSprite[] imageRunes;
    public boolean ingame = false;
    public boolean flamesThread = false;
    public int chatPublicSetting;
    public int anInt977;
    public Sprite imageFlamesLeft;
    public Sprite imageFlamesRight;
    public int SCROLLBAR_GRIP_LOWLIGHT = 0x332d25;
    public IndexedSprite inback;
    public IndexedSprite mapback;
    public IndexedSprite chatback;
    public int inMultizone;
    public IndexedFont fontPlain11;
    public IndexedFont fontPlain12;
    public IndexedFont fontBold12;
    public IndexedFont fontQuill8;
    public int clickedMinimap;
    public int[] flameIntensity;
    public int[] flameIntensityBuffer;
    public int SCROLLBAR_GRIP_HIGHLIGHT = 0x766654;
    public int[] waypointX = new int[4000];
    public int[] waypointY = new int[4000];
    public CRC32 crc32 = new CRC32();
    public Sprite mapflag;
    public static int updateGameCounter;
    public BufferedStream stream;
    public byte[][] sceneMapLocData;
    public int chatbackComponentId = -1;
    public int selectedObject;
    public int anInt1003;
    public int anInt1004;
    public int anInt1005;
    public String selectedObjName;
    public DrawArea backleft1;
    public DrawArea backleft2;
    public DrawArea backright1;
    public DrawArea backright2;
    public DrawArea backtop1;
    public DrawArea backtop2;
    public DrawArea backvmid1;
    public DrawArea backvmid2;
    public DrawArea backvmid3;
    public DrawArea backhmid2;
    public static int anInt1017;
    public int waveCount;
    public int drawX = -1;
    public int drawY = -1;
    public int stickyChatbackComponentId = -1;
    public boolean rights = false;
    public int[] unknownCameraVariable = new int[5];
    public int selectedSpell;
    public int anInt1026;
    public int selectedFlags;
    public String selectedSpellPrefix;
    public DrawArea titleTop;
    public DrawArea titleBottom;
    public DrawArea titleCenter;
    public DrawArea titleLeft;
    public DrawArea titleRight;
    public DrawArea titleBottomLeft;
    public DrawArea titleBottomRight;
    public DrawArea titleLeftSpace;
    public DrawArea titleRightSpace;
    public IndexedSprite[] mapscene = new IndexedSprite[50];
    public IndexedSprite redstone1v;
    public IndexedSprite redstone2v;
    public IndexedSprite redstone3v;
    public IndexedSprite redstone1hv;
    public IndexedSprite redstone2hv;
    public int[] anIntArray1045 = {
        0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff
    };
    public DrawArea areaInvback;
    public DrawArea areaMapback;
    public DrawArea areaViewport;
    public DrawArea areaChatback;
    public int SCROLLBAR_TRACK = 0x23201b;
    public int flagTileX;
    public int flagTileY;
    public Sprite minimap;
    public int anInt1054;
    public boolean chatbackInputType = false;
    public LinkedList spotanims = new LinkedList();
    public Sprite mapdot1;
    public Sprite mapdot2;
    public Sprite mapdot3;
    public Sprite mapdot4;
    public int anInt1061;
    public static BigInteger modulus = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
    public int viewportHoveredInterfaceIndex;
    public String midiString;
    public int lastWaveLoops = -1;
    public String username = "";
    public String password = "";
    public byte[] tmpTexels = new byte[16384];
    public boolean errorStarted = false;
    public int energy;
    public static final int[] BEARD_COLORS = {
        9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145,
        58654, 5027, 1457, 16565, 34991, 25486
    };
    public int optionCount;
    public int[] anIntArray1075 = new int[2000];
    public int anInt1076;
    public int sceneState;
    public int[] skillExperience = new int[50];
    public boolean sidebarRedrawIcons = false;
    public IndexedSprite scrollbar1;
    public IndexedSprite scrollbar2;
    public String loginMessage0 = "";
    public String loginMessage1 = "";
    public int minimapAnticheatAngle;
    public int minimapAngleModifier = 2;
    public int anInt1087;
    public int anInt1088;
    public int friendCount;
    public static int anInt1090;
    public int chatCount;
    public int anInt1092 = 50;
    public int[] chatScreenX = new int[anInt1092];
    public int[] chatScreenY = new int[anInt1092];
    public int[] chatHeight = new int[anInt1092];
    public int[] chatPadding = new int[anInt1092];
    public int[] chatColors = new int[anInt1092];
    public int[] chatStyles = new int[anInt1092];
    public int[] chatTimers = new int[anInt1092];
    public String[] chatMessages = new String[anInt1092];
    public int wildernessLevel;
    public static boolean aBoolean1102;
    public IndexedSprite imageTitlebox;
    public IndexedSprite imageTitlebutton;
    public final int[] anIntArray1107 = {
        0, 0, 0, 0, 1, 1, 1, 1, 1, 2,
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
        2, 2, 3
    };
    public static int updateLocCounter;
    public int titleState;
    public int anInt1110;
    public int cameraX;
    public int cameraY;
    public int cameraZ;
    public int cameraPitch;
    public int cameraOrbitYaw;
    public int[] compassLineWidth = new int[33];
    public int[][] pathWaypoint = new int[104][104];
    public String aString1119;
    public Sprite[] cross = new Sprite[8];
    public boolean flameThreadActive = false;
    public Object midiSync = new Object();
    public int[] waveId = new int[50];
    public int cameraAnticheatOffsetX;
    public int cameraOffsetXModifier = 2;
    public String[] friendName = new String[100];
    public int anInt1128 = -1;
    public int sidebarInterfaceId = -1;
    public int cameraAnticheatOffsetZ;
    public int cameraOffsetZModifier = 2;
    public int[] minimapLeft = new int[151];
    public int cameraAnticheatAngle;
    public int cameraOffsetYawModifier = 1;
    public FileArchive titleArchive;
    public String aString1137 = "";
    public Sprite[] mapfunction = new Sprite[50];
    public int[] paramA = new int[500];
    public int[] paramB = new int[500];
    public int[] actions = new int[500];
    public int[] paramC = new int[500];
    public boolean scrollGripHeld = false;
    public Sprite compass;
    public long serverSeed;
    public int mouseArea;
    public int menuX;
    public int menuY;
    public int menuWidth;
    public int menuHeight;
    public boolean aBoolean1153 = true;
    public int scrollGripInputPadding;
    public int anInt1155;
    public int flameOffset;
    public LinkedList[][][] objects = new LinkedList[4][104][104];
    public int SCROLLBAR_GRIP_FOREGROUND = 0x4d4233;
    public int[] cameraFrequency = new int[5];

    static {
        EXPERIENCE_TABLE = new int[99];
        int i = 0;
        for (int j = 0; j < 99; j++) {
            int k = j + 1;
            int l = (int) ((double) k + 300D * Math.pow(2D, (double) k / 7D));
            i += l;
            EXPERIENCE_TABLE[j] = i / 4;
        }
    }
}
