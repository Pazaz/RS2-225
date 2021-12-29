package com.runescape.scene;

import com.runescape.data.Model;
import com.runescape.data.NPCType;
import com.runescape.data.SeqType;
import com.runescape.data.SpotAnimType;

public class NPCEntity extends PathingEntity {

    public Model method462(boolean flag) {
        if (aClass2_1502 == null)
            return null;
        if (super.anInt1412 == -1 || super.anInt1413 == -1)
            return method469(false);
        Model class38_sub2_sub1 = method469(false);
        SpotAnimType class20 = SpotAnimType.aClass20Array387[super.anInt1412];
        Model class38_sub2_sub1_1 = new Model(class20.method226(), true, !class20.aBoolean392,
                anInt1500, false);
        class38_sub2_sub1_1.method363(-super.anInt1416, 0, -122, 0);
        class38_sub2_sub1_1.method357(4);
        class38_sub2_sub1_1.method358(-16599, class20.aClass18_391.anIntArray366[super.anInt1413]);
        class38_sub2_sub1_1.anIntArrayArray1255 = null;
        class38_sub2_sub1_1.anIntArrayArray1254 = null;
        if (!flag)
            throw new NullPointerException();
        if (class20.anInt395 != 128 || class20.anInt396 != 128)
            class38_sub2_sub1_1.method366(class20.anInt395, 2, class20.anInt396, class20.anInt395);
        class38_sub2_sub1_1.method367(64 + class20.anInt398, 850 + class20.anInt399, -30, -50, -30, true);
        Model[] aclass38_sub2_sub1 = {
                class38_sub2_sub1, class38_sub2_sub1_1
        };
        Model class38_sub2_sub1_2 = new Model(aclass38_sub2_sub1, (byte) -31, 2, true);
        if (aClass2_1502.aByte85 == 1)
            class38_sub2_sub1_2.aBoolean1256 = true;
        return class38_sub2_sub1_2;
    }

    public Model method469(boolean flag) {
        if (super.anInt1407 >= 0 && super.anInt1410 == 0) {
            int i = SeqType.aClass18Array364[super.anInt1407].anIntArray366[super.anInt1408];
            int k = -1;
            if (super.anInt1404 >= 0 && super.anInt1404 != super.anInt1385)
                k = SeqType.aClass18Array364[super.anInt1404].anIntArray366[super.anInt1405];
            return aClass2_1502.method150(i, k, SeqType.aClass18Array364[super.anInt1407].anIntArray370);
        }
        int j = -1;
        if (flag)
            throw new NullPointerException();
        if (super.anInt1404 >= 0)
            j = SeqType.aClass18Array364[super.anInt1404].anIntArray366[super.anInt1405];
        Model class38_sub2_sub1 = aClass2_1502.method150(j, -1, null);
        super.anInt1425 = class38_sub2_sub1.anInt1247;
        return class38_sub2_sub1;
    }

    public boolean method468(boolean flag) {
        if (flag)
            aBoolean1501 = !aBoolean1501;
        return aClass2_1502 != null;
    }

    public NPCEntity() {
        aBoolean1501 = false;
    }

    public int anInt1500;
    public boolean aBoolean1501;
    public NPCType aClass2_1502;
}
