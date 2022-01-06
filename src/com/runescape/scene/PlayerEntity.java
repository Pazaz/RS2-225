package com.runescape.scene;

import com.runescape.Game;
import com.runescape.cache.SpotAnimType;
import com.runescape.util.StringUtils;
import com.runescape.cache.IdkType;
import com.runescape.cache.Model;
import com.runescape.cache.ObjType;
import com.runescape.cache.SeqType;
import com.runescape.util.Buffer;
import com.runescape.util.Cache;

public class PlayerEntity extends PathingEntity {

    public void method470(boolean flag, Buffer class38_sub2_sub3) {
        class38_sub2_sub3.offset = 0;
        anInt1507 = class38_sub2_sub3.readByte();
        anInt1508 = class38_sub2_sub3.readByte();
        for (int i = 0; i < 12; i++) {
            int j = class38_sub2_sub3.readByte();
            if (j == 0) {
                anIntArray1509[i] = 0;
            } else {
                int l = class38_sub2_sub3.readByte();
                anIntArray1509[i] = (j << 8) + l;
            }
        }

        for (int k = 0; k < 5; k++) {
            int i1 = class38_sub2_sub3.readByte();
            if (i1 < 0 || i1 >= Game.anIntArrayArray942[k].length)
                i1 = 0;
            anIntArray1510[k] = i1;
        }

        if (flag)
            return;
        super.standSeq = class38_sub2_sub3.readWord();
        if (super.standSeq == 65535)
            super.standSeq = -1;
        super.anInt1386 = class38_sub2_sub3.readWord();
        if (super.anInt1386 == 65535)
            super.anInt1386 = -1;
        super.anInt1387 = class38_sub2_sub3.readWord();
        if (super.anInt1387 == 65535)
            super.anInt1387 = -1;
        super.anInt1388 = class38_sub2_sub3.readWord();
        if (super.anInt1388 == 65535)
            super.anInt1388 = -1;
        super.anInt1389 = class38_sub2_sub3.readWord();
        if (super.anInt1389 == 65535)
            super.anInt1389 = -1;
        super.anInt1390 = class38_sub2_sub3.readWord();
        if (super.anInt1390 == 65535)
            super.anInt1390 = -1;
        super.anInt1391 = class38_sub2_sub3.readWord();
        if (super.anInt1391 == 65535)
            super.anInt1391 = -1;
        aString1505 = StringUtils.formatName(StringUtils.fromBase37(class38_sub2_sub3.readQWord()));
        anInt1511 = class38_sub2_sub3.readByte();
        aBoolean1506 = true;
        aLong1512 = 0L;
        for (int j1 = 0; j1 < 12; j1++) {
            aLong1512 <<= 4;
            if (anIntArray1509[j1] >= 256)
                aLong1512 += anIntArray1509[j1] - 256;
        }

        if (anIntArray1509[0] >= 256)
            aLong1512 += anIntArray1509[0] - 256 >> 4;
        if (anIntArray1509[1] >= 256)
            aLong1512 += anIntArray1509[1] - 256 >> 8;
        for (int k1 = 0; k1 < 5; k1++) {
            aLong1512 <<= 3;
            aLong1512 += anIntArray1510[k1];
        }

        aLong1512 <<= 1;
        aLong1512 += anInt1507;
    }

    @Override
    public Model getDrawMethod() {
        if (!aBoolean1506)
            return null;
        Model class38_sub2_sub1 = method471(false);
        super.height = class38_sub2_sub1.minY;
        class38_sub2_sub1.pickable = true;
        if (aBoolean1524)
            return class38_sub2_sub1;
        if (super.spotAnimIndex != -1 && super.spotAnimFrame != -1) {
            SpotAnimType spotAnimType = SpotAnimType.instances[super.spotAnimIndex];
            Model class38_sub2_sub1_2 = new Model(spotAnimType.getModel(), true,
                    !spotAnimType.disposeAlpha, anInt1503, false);
            class38_sub2_sub1_2.translate(-super.spotanimOffsetY, 0, -122, 0);
            class38_sub2_sub1_2.applyGroups(4);
            class38_sub2_sub1_2.applyFrame(-16599, spotAnimType.seq.primaryFrames[super.spotAnimFrame]);
            class38_sub2_sub1_2.skinTriangle = null;
            class38_sub2_sub1_2.labelVertices = null;
            if (spotAnimType.breadthScale != 128 || spotAnimType.depthScale != 128)
                class38_sub2_sub1_2.scale(spotAnimType.breadthScale, 2, spotAnimType.depthScale, spotAnimType.breadthScale);
            class38_sub2_sub1_2.applyLighting(64 + spotAnimType.ambience, 850 + spotAnimType.modelShadow, -30, -50, -30, true);
            Model[] aclass38_sub2_sub1_1 = {
                    class38_sub2_sub1, class38_sub2_sub1_2
            };
            class38_sub2_sub1 = new Model(aclass38_sub2_sub1_1, (byte) -31, 2, true);
        }
        if (aClass38_Sub2_Sub1_1519 != null) {
            if (Game.anInt955 >= anInt1515)
                aClass38_Sub2_Sub1_1519 = null;
            if (Game.anInt955 >= anInt1514 && Game.anInt955 < anInt1515) {
                Model class38_sub2_sub1_1 = aClass38_Sub2_Sub1_1519;
                class38_sub2_sub1_1.translate(anInt1517 - anInt1513, anInt1516 - super.anInt1380, -122,
                        anInt1518 - super.anInt1381);
                if (super.anInt1426 == 512) {
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                } else if (super.anInt1426 == 1024) {
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                } else if (super.anInt1426 == 1536)
                    class38_sub2_sub1_1.method361(0);
                Model[] aclass38_sub2_sub1 = {
                        class38_sub2_sub1, class38_sub2_sub1_1
                };
                class38_sub2_sub1 = new Model(aclass38_sub2_sub1, (byte) -31, 2, true);
                if (super.anInt1426 == 512)
                    class38_sub2_sub1_1.method361(0);
                else if (super.anInt1426 == 1024) {
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                } else if (super.anInt1426 == 1536) {
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                    class38_sub2_sub1_1.method361(0);
                }
                class38_sub2_sub1_1.translate(anInt1513 - anInt1517, super.anInt1380 - anInt1516, -122,
                        super.anInt1381 - anInt1518);
            }
        }
        class38_sub2_sub1.pickable = true;
        return class38_sub2_sub1;
    }

    public Model method471(boolean flag) {
        long l = aLong1512;
        int i = -1;
        int j = -1;
        int k = -1;
        int i1 = -1;
        if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
            SeqType seqType = SeqType.seqTypes[super.primarySeq];
            i = seqType.primaryFrames[super.primarySeqFrame];
            if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq)
                j = SeqType.seqTypes[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
            if (seqType.anInt373 >= 0) {
                k = seqType.anInt373;
                l += k - anIntArray1509[5] << 40;
            }
            if (seqType.anInt374 >= 0) {
                i1 = seqType.anInt374;
                l += i1 - anIntArray1509[3] << 48;
            }
        } else if (super.secondarySeq >= 0)
            i = SeqType.seqTypes[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
        Model class38_sub2_sub1 = (Model) cache.get(l);
        if (class38_sub2_sub1 == null) {
            Model[] aclass38_sub2_sub1 = new Model[12];
            int j1 = 0;
            for (int k1 = 0; k1 < 12; k1++) {
                int l1 = anIntArray1509[k1];
                if (i1 >= 0 && k1 == 3)
                    l1 = i1;
                if (k >= 0 && k1 == 5)
                    l1 = k;
                if (l1 >= 256 && l1 < 512)
                    aclass38_sub2_sub1[j1++] = IdkType.instances[l1 - 256].getModel();
                if (l1 >= 512) {
                    ObjType objType = ObjType.get(l1 - 512);
                    Model class38_sub2_sub1_2 = objType.getWornModel(anInt1507);
                    if (class38_sub2_sub1_2 != null)
                        aclass38_sub2_sub1[j1++] = class38_sub2_sub1_2;
                }
            }

            class38_sub2_sub1 = new Model(0, aclass38_sub2_sub1, j1);
            for (int i2 = 0; i2 < 5; i2++)
                if (anIntArray1510[i2] != 0) {
                    class38_sub2_sub1.recolor(Game.anIntArrayArray942[i2][0],
                            Game.anIntArrayArray942[i2][anIntArray1510[i2]]);
                    if (i2 == 1)
                        class38_sub2_sub1.recolor(Game.anIntArray1073[0],
                                Game.anIntArray1073[anIntArray1510[i2]]);
                }

            class38_sub2_sub1.applyGroups(4);
            class38_sub2_sub1.applyLighting(64, 850, -30, -50, -30, true);
            cache.put(l, class38_sub2_sub1);
        }
        if (aBoolean1524)
            return class38_sub2_sub1;
        Model class38_sub2_sub1_1 = new Model(0, class38_sub2_sub1, true);
        if (flag)
            aBoolean1504 = !aBoolean1504;
        if (i != -1 && j != -1)
            class38_sub2_sub1_1.method359(j, 3, i, SeqType.seqTypes[super.primarySeq].labelGroups);
        else if (i != -1)
            class38_sub2_sub1_1.applyFrame(-16599, i);
        class38_sub2_sub1_1.calculateYBoundaries(2992);
        class38_sub2_sub1_1.skinTriangle = null;
        class38_sub2_sub1_1.labelVertices = null;
        return class38_sub2_sub1_1;
    }

    public Model method472(int i) {
        if (!aBoolean1506)
            return null;
        Model[] aclass38_sub2_sub1 = new Model[12];
        int j = 0;
        for (int k = 0; k < 12; k++) {
            int l = anIntArray1509[k];
            if (l >= 256 && l < 512)
                aclass38_sub2_sub1[j++] = IdkType.instances[l - 256].getHeadModel();
            if (l >= 512) {
                Model class38_sub2_sub1_1 = ObjType.get(l - 512).getHeadModel(anInt1507);
                if (class38_sub2_sub1_1 != null)
                    aclass38_sub2_sub1[j++] = class38_sub2_sub1_1;
            }
        }

        Model class38_sub2_sub1 = new Model(0, aclass38_sub2_sub1, j);
        for (int i1 = 0; i1 < 5; i1++)
            if (anIntArray1510[i1] != 0) {
                class38_sub2_sub1.recolor(Game.anIntArrayArray942[i1][0],
                        Game.anIntArrayArray942[i1][anIntArray1510[i1]]);
                if (i1 == 1)
                    class38_sub2_sub1.recolor(Game.anIntArray1073[0], Game.anIntArray1073[anIntArray1510[i1]]);
            }

        while (i >= 0)
            throw new NullPointerException();
        return class38_sub2_sub1;
    }

    public boolean isValid(boolean flag) {
        if (flag)
            aBoolean1504 = !aBoolean1504;
        return aBoolean1506;
    }

    public PlayerEntity() {
        aBoolean1504 = false;
        aBoolean1506 = false;
        anIntArray1509 = new int[12];
        anIntArray1510 = new int[5];
        aBoolean1524 = false;
    }

    public int anInt1503;
    public boolean aBoolean1504;
    public String aString1505;
    public boolean aBoolean1506;
    public int anInt1507;
    public int anInt1508;
    public int[] anIntArray1509;
    public int[] anIntArray1510;
    public int anInt1511;
    public long aLong1512;
    public int anInt1513;
    public int anInt1514;
    public int anInt1515;
    public int anInt1516;
    public int anInt1517;
    public int anInt1518;
    public Model aClass38_Sub2_Sub1_1519;
    public int anInt1520;
    public int anInt1521;
    public int anInt1522;
    public int anInt1523;
    public boolean aBoolean1524;
    public static Cache cache = new Cache(200);

}
