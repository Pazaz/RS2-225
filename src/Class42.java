// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class42 {

    public void method484(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        anInt683 = class38_sub2_sub3.method446();
        anInt681 = class38_sub2_sub3.method451();
        anInt682 = class38_sub2_sub3.method451();
        anInt678 = class38_sub2_sub3.method446();
        anIntArray679 = new int[anInt678];
        anIntArray680 = new int[anInt678];
        if (flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        for (int j = 0; j < anInt678; j++) {
            anIntArray679[j] = class38_sub2_sub3.method448();
            anIntArray680[j] = class38_sub2_sub3.method448();
        }

    }

    public void method485(int i) {
        anInt684 = 0;
        anInt685 = 0;
        anInt686 = 0;
        anInt687 = 0;
        if (i < 8 || i > 8) {
            return;
        } else {
            anInt688 = 0;
            return;
        }
    }

    public int method486(boolean flag, int i) {
        if (!flag) {
            for (int j = 1; j > 0; j++)
                ;
        }
        if (anInt688 >= anInt684) {
            anInt687 = anIntArray680[anInt685++] << 15;
            if (anInt685 >= anInt678)
                anInt685 = anInt678 - 1;
            anInt684 = (int) (((double) anIntArray679[anInt685] / 65536D) * (double) i);
            if (anInt684 > anInt688)
                anInt686 = ((anIntArray680[anInt685] << 15) - anInt687) / (anInt684 - anInt688);
        }
        anInt687 += anInt686;
        anInt688++;
        return anInt687 - anInt686 >> 15;
    }

    public Class42() {
    }

    public int anInt678;
    public int[] anIntArray679;
    public int[] anIntArray680;
    public int anInt681;
    public int anInt682;
    public int anInt683;
    public int anInt684;
    public int anInt685;
    public int anInt686;
    public int anInt687;
    public int anInt688;
}
