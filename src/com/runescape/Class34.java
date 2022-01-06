package com.runescape;

public class Class34 {

    public Class34(byte byte0, int i) {
        aBoolean590 = false;
        aClass36_594 = new Class36(9, 1024);
        aClass29_595 = new Class29(anInt591);
        anInt592 = i;
        if (byte0 != 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        anInt593 = i;
    }

    public Class38_Sub2 method341(long l) {
        Class38_Sub2 class38_sub2 = (Class38_Sub2) aClass36_594.method344(l);
        if (class38_sub2 != null)
            aClass29_595.method275(class38_sub2);
        return class38_sub2;
    }

    public void method342(int i, long l, Class38_Sub2 class38_sub2) {
        if (anInt593 == 0) {
            Class38_Sub2 class38_sub2_1 = aClass29_595.method276();
            class38_sub2_1.unlink();
            class38_sub2_1.method350();
        } else {
            anInt593--;
        }
        aClass36_594.method345(l, -566, class38_sub2);
        if (i < 6 || i > 6)
            aBoolean590 = !aBoolean590;
        aClass29_595.method275(class38_sub2);
    }

    public void method343() {
        do {
            Class38_Sub2 class38_sub2 = aClass29_595.method276();
            if (class38_sub2 != null) {
                class38_sub2.unlink();
                class38_sub2.method350();
            } else {
                anInt593 = anInt592;
                return;
            }
        } while (true);
    }

    public boolean aBoolean590;
    public static int anInt591 = 5;
    public int anInt592;
    public int anInt593;
    public Class36 aClass36_594;
    public Class29 aClass29_595;

}
