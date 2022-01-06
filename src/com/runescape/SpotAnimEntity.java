package com.runescape;

public class SpotAnimEntity extends Entity {

    public SpotAnimEntity(int i, int j, boolean flag, int k, int l, int i1, int j1,
                          int k1) {
        aBoolean1377 = false;
        spotAnimType = SpotAnimType.spotAnimTypes[j];
        anInt1371 = j1;
        if (flag)
            anInt1368 = -147;
        anInt1372 = i;
        anInt1373 = k;
        anInt1374 = i1;
        anInt1370 = k1 + l;
        aBoolean1377 = false;
    }

    public void method465(int i, int j) {
        if (j != 0)
            anInt1368 = -255;
        for (anInt1376 += i; anInt1376 > spotAnimType.seq.anIntArray368[anInt1375]; ) {
            anInt1376 -= spotAnimType.seq.anIntArray368[anInt1375] + 1;
            anInt1375++;
            if (anInt1375 >= spotAnimType.seq.anInt365
                    && (anInt1375 < 0 || anInt1375 >= spotAnimType.seq.anInt365)) {
                anInt1375 = 0;
                aBoolean1377 = true;
            }
        }

    }

    @Override
    public Class38_Sub2_Sub1 getDrawMethod() {
        Class38_Sub2_Sub1 class38_sub2_sub1 = spotAnimType.getModel();
        Class38_Sub2_Sub1 class38_sub2_sub1_1 = new Class38_Sub2_Sub1(class38_sub2_sub1, true,
                !spotAnimType.disposeAlpha, anInt1367, false);
        if (!aBoolean1377) {
            class38_sub2_sub1_1.applyGroups(4);
            class38_sub2_sub1_1.applyFrame(-16599, spotAnimType.seq.primaryFrames[anInt1375]);
            class38_sub2_sub1_1.skinTriangle = null;
            class38_sub2_sub1_1.labelVertices = null;
        }
        if (spotAnimType.anInt395 != 128 || spotAnimType.anInt396 != 128)
            class38_sub2_sub1_1.scale(spotAnimType.anInt395, 2, spotAnimType.anInt396, spotAnimType.anInt395);
        if (spotAnimType.anInt397 != 0) {
            if (spotAnimType.anInt397 == 90)
                class38_sub2_sub1_1.method361(0);
            if (spotAnimType.anInt397 == 180) {
                class38_sub2_sub1_1.method361(0);
                class38_sub2_sub1_1.method361(0);
            }
            if (spotAnimType.anInt397 == 270) {
                class38_sub2_sub1_1.method361(0);
                class38_sub2_sub1_1.method361(0);
                class38_sub2_sub1_1.method361(0);
            }
        }
        class38_sub2_sub1_1.applyLighting(64 + spotAnimType.anInt398, 850 + spotAnimType.anInt399, -30, -50, -30, true);
        return class38_sub2_sub1_1;
    }

    public int anInt1367;
    public int anInt1368;
    public SpotAnimType spotAnimType;
    public int anInt1370;
    public int anInt1371;
    public int anInt1372;
    public int anInt1373;
    public int anInt1374;
    public int anInt1375;
    public int anInt1376;
    public boolean aBoolean1377;
}
