// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class36 {

    public Class36(int i, int j) {
        anInt644 = 4277;
        aBoolean645 = false;
        anInt646 = j;
        aClass38Array647 = new Class38[j];
        if (i < 9 || i > 9)
            aBoolean645 = !aBoolean645;
        for (int k = 0; k < j; k++) {
            Class38 class38 = aClass38Array647[k] = new Class38();
            class38.aClass38_655 = class38;
            class38.aClass38_656 = class38;
        }

    }

    public Class38 method344(long l) {
        Class38 class38 = aClass38Array647[(int) (l & (long) (anInt646 - 1))];
        for (Class38 class38_1 = class38.aClass38_655; class38_1 != class38; class38_1 = class38_1.aClass38_655)
            if (class38_1.aLong654 == l)
                return class38_1;

        return null;
    }

    public void method345(long l, int i, Class38 class38) {
        if (class38.aClass38_656 != null)
            class38.method349();
        Class38 class38_1 = aClass38Array647[(int) (l & (long) (anInt646 - 1))];
        class38.aClass38_656 = class38_1.aClass38_656;
        if (i >= 0) {
            return;
        } else {
            class38.aClass38_655 = class38_1;
            class38.aClass38_656.aClass38_655 = class38;
            class38.aClass38_655.aClass38_656 = class38;
            class38.aLong654 = l;
            return;
        }
    }

    public int anInt644;
    public boolean aBoolean645;
    public int anInt646;
    public Class38 aClass38Array647[];
}
