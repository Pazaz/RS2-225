package com.runescape.scene;

import com.runescape.cache.SpotAnimType;
import com.runescape.cache.Model;

public class SpotAnimEntity extends Entity {

    public SpotAnimEntity(int i, int j, boolean flag, int k, int l, int i1, int j1,
                          int k1) {
        aBoolean1377 = false;
        spotAnimType = SpotAnimType.instances[j];
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
        for (anInt1376 += i; anInt1376 > spotAnimType.seq.frameDelay[anInt1375]; ) {
            anInt1376 -= spotAnimType.seq.frameDelay[anInt1375] + 1;
            anInt1375++;
            if (anInt1375 >= spotAnimType.seq.frameCount
                    && (anInt1375 < 0 || anInt1375 >= spotAnimType.seq.frameCount)) {
                anInt1375 = 0;
                aBoolean1377 = true;
            }
        }

    }

    @Override
    public Model getDrawMethod() {
        Model class38_sub2_sub1 = spotAnimType.getModel();
        Model class38_sub2_sub1_1 = new Model(class38_sub2_sub1, true,
                !spotAnimType.disposeAlpha, false);
        if (!aBoolean1377) {
            class38_sub2_sub1_1.applyGroups();
            class38_sub2_sub1_1.applyFrame(spotAnimType.seq.primaryFrames[anInt1375]);
            class38_sub2_sub1_1.skinTriangle = null;
            class38_sub2_sub1_1.labelVertices = null;
        }
        if (spotAnimType.breadthScale != 128 || spotAnimType.depthScale != 128)
            class38_sub2_sub1_1.scale(spotAnimType.breadthScale, spotAnimType.depthScale, spotAnimType.breadthScale);
        if (spotAnimType.orientation != 0) {
            if (spotAnimType.orientation == 90)
                class38_sub2_sub1_1.rotateCounterClockwise();
            if (spotAnimType.orientation == 180) {
                class38_sub2_sub1_1.rotateCounterClockwise();
                class38_sub2_sub1_1.rotateCounterClockwise();
            }
            if (spotAnimType.orientation == 270) {
                class38_sub2_sub1_1.rotateCounterClockwise();
                class38_sub2_sub1_1.rotateCounterClockwise();
                class38_sub2_sub1_1.rotateCounterClockwise();
            }
        }
        class38_sub2_sub1_1.applyLighting(64 + spotAnimType.ambience, 850 + spotAnimType.modelShadow, -30, -50, -30, true);
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
