// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class29 {

    public Class29(int i) {
        anInt499 = 679;
        aByte500 = 2;
        aBoolean501 = true;
        aClass38_Sub2_502 = new Class38_Sub2();
        aClass38_Sub2_502.aClass38_Sub2_1184 = aClass38_Sub2_502;
        if (i < 5 || i > 5)
            anInt499 = -426;
        aClass38_Sub2_502.aClass38_Sub2_1185 = aClass38_Sub2_502;
    }

    public void method275(Class38_Sub2 class38_sub2) {
        if (class38_sub2.aClass38_Sub2_1185 != null)
            class38_sub2.method350();
        class38_sub2.aClass38_Sub2_1185 = aClass38_Sub2_502.aClass38_Sub2_1185;
        class38_sub2.aClass38_Sub2_1184 = aClass38_Sub2_502;
        class38_sub2.aClass38_Sub2_1185.aClass38_Sub2_1184 = class38_sub2;
        class38_sub2.aClass38_Sub2_1184.aClass38_Sub2_1185 = class38_sub2;
    }

    public Class38_Sub2 method276() {
        Class38_Sub2 class38_sub2 = aClass38_Sub2_502.aClass38_Sub2_1184;
        if (class38_sub2 == aClass38_Sub2_502) {
            return null;
        } else {
            class38_sub2.method350();
            return class38_sub2;
        }
    }

    public int anInt499;
    public byte aByte500;
    public boolean aBoolean501;
    public Class38_Sub2 aClass38_Sub2_502;
}
