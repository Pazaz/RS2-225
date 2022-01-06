package com.runescape;

public class Class38_Sub7_Sub3_Sub1 extends Class38_Sub7_Sub3 {

    @Override
    public Class38_Sub2_Sub1 getDrawMethod() {
        if (npcType == null)
            return null;
        if (super.anInt1412 == -1 || super.anInt1413 == -1)
            return method469(false);
        Class38_Sub2_Sub1 class38_sub2_sub1 = method469(false);
        SpotAnimType spotAnimType = SpotAnimType.spotAnimTypes[super.anInt1412];
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = new Class38_Sub2_Sub1(spotAnimType.getModel(), true, !spotAnimType.disposeAlpha,
                anInt1500, false);
        class38_sub2_sub1_1.method363(-super.anInt1416, 0, -122, 0);
        class38_sub2_sub1_1.applyGroups(4);
        class38_sub2_sub1_1.applyFrame(-16599, spotAnimType.seq.primaryFrames[super.anInt1413]);
        class38_sub2_sub1_1.skinTriangle = null;
        class38_sub2_sub1_1.labelVertices = null;
        if (spotAnimType.anInt395 != 128 || spotAnimType.anInt396 != 128)
            class38_sub2_sub1_1.scale(spotAnimType.anInt395, 2, spotAnimType.anInt396, spotAnimType.anInt395);
        class38_sub2_sub1_1.applyLighting(64 + spotAnimType.anInt398, 850 + spotAnimType.anInt399, -30, -50, -30, true);
        Class38_Sub2_Sub1[] aclass38_sub2_sub1 = {
                class38_sub2_sub1, class38_sub2_sub1_1
        };
        Class38_Sub2_Sub1 class38_sub2_sub1_2 = new Class38_Sub2_Sub1(aclass38_sub2_sub1, (byte) -31, 2, true);
        if (npcType.aByte85 == 1)
            class38_sub2_sub1_2.aBoolean1256 = true;
        return class38_sub2_sub1_2;
    }

    public Class38_Sub2_Sub1 method469(boolean flag) {
        if (super.anInt1407 >= 0 && super.anInt1410 == 0) {
            int i = SeqType.seqTypes[super.anInt1407].primaryFrames[super.anInt1408];
            int k = -1;
            if (super.anInt1404 >= 0 && super.anInt1404 != super.anInt1385)
                k = SeqType.seqTypes[super.anInt1404].primaryFrames[super.anInt1405];
            return npcType.method150(i, k, SeqType.seqTypes[super.anInt1407].anIntArray370);
        }
        int j = -1;
        if (flag)
            throw new NullPointerException();
        if (super.anInt1404 >= 0)
            j = SeqType.seqTypes[super.anInt1404].primaryFrames[super.anInt1405];
        Class38_Sub2_Sub1 class38_sub2_sub1 = npcType.method150(j, -1, null);
        super.anInt1425 = class38_sub2_sub1.anInt1247;
        return class38_sub2_sub1;
    }

    public boolean method468(boolean flag) {
        if (flag)
            aBoolean1501 = !aBoolean1501;
        return npcType != null;
    }

    public Class38_Sub7_Sub3_Sub1() {
        aBoolean1501 = false;
    }

    public int anInt1500;
    public boolean aBoolean1501;
    public NpcType npcType;
}
