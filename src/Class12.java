// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.io.PrintStream;
import sign.signlink;

public class Class12 {

    public static void method214(Class39 class39, int i) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, class39.method474("idk.dat", null, (byte) 2));
        anInt246 = class38_sub2_sub3.method448();
        if (aClass12Array247 == null)
            aClass12Array247 = new Class12[anInt246];
        for (int j = 0; j < anInt246; j++) {
            if (aClass12Array247[j] == null)
                aClass12Array247[j] = new Class12();
            aClass12Array247[j].method215(false, class38_sub2_sub3);
        }

        i = 87 / i;
    }

    public void method215(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        if (flag)
            anInt243 = 65;
        do {
            int i = class38_sub2_sub3.method446();
            if (i == 0)
                return;
            if (i == 1)
                anInt248 = class38_sub2_sub3.method446();
            else if (i == 2) {
                int j = class38_sub2_sub3.method446();
                anIntArray249 = new int[j];
                for (int k = 0; k < j; k++)
                    anIntArray249[k] = class38_sub2_sub3.method448();

            } else if (i == 3)
                aBoolean253 = true;
            else if (i >= 40 && i < 50)
                anIntArray250[i - 40] = class38_sub2_sub3.method448();
            else if (i >= 50 && i < 60)
                anIntArray251[i - 50] = class38_sub2_sub3.method448();
            else if (i >= 60 && i < 70)
                anIntArray252[i - 60] = class38_sub2_sub3.method448();
            else
                System.out.println("Error unrecognised config code: " + i);
        } while (true);
    }

    public Class38_Sub2_Sub1 method216() {
        if (anIntArray249 == null)
            return null;
        Class38_Sub2_Sub1 aclass38_sub2_sub1[] = new Class38_Sub2_Sub1[anIntArray249.length];
        for (int i = 0; i < anIntArray249.length; i++)
            aclass38_sub2_sub1[i] = new Class38_Sub2_Sub1(false, anIntArray249[i]);

        Class38_Sub2_Sub1 class38_sub2_sub1;
        if (aclass38_sub2_sub1.length == 1)
            class38_sub2_sub1 = aclass38_sub2_sub1[0];
        else
            class38_sub2_sub1 = new Class38_Sub2_Sub1(0, aclass38_sub2_sub1, aclass38_sub2_sub1.length);
        for (int j = 0; j < 6; j++) {
            if (anIntArray250[j] == 0)
                break;
            class38_sub2_sub1.method364(anIntArray250[j], anIntArray251[j]);
        }

        return class38_sub2_sub1;
    }

    public Class38_Sub2_Sub1 method217(boolean flag) {
        Class38_Sub2_Sub1 aclass38_sub2_sub1[] = new Class38_Sub2_Sub1[5];
        int i = 0;
        for (int j = 0; j < 5; j++)
            if (anIntArray252[j] != -1)
                aclass38_sub2_sub1[i++] = new Class38_Sub2_Sub1(false, anIntArray252[j]);

        Class38_Sub2_Sub1 class38_sub2_sub1 = new Class38_Sub2_Sub1(0, aclass38_sub2_sub1, i);
        for (int k = 0; k < 6; k++) {
            if (anIntArray250[k] == 0)
                break;
            class38_sub2_sub1.method364(anIntArray250[k], anIntArray251[k]);
        }

        if (flag) {
            for (int l = 1; l > 0; l++)
                ;
        }
        return class38_sub2_sub1;
    }

    public Class12() {
        aBoolean245 = false;
        anInt248 = -1;
        anIntArray250 = new int[6];
        anIntArray251 = new int[6];
        aBoolean253 = false;
    }

    public static int anInt243;
    public static int anInt244 = 473;
    public boolean aBoolean245;
    public static int anInt246;
    public static Class12 aClass12Array247[];
    public int anInt248;
    public int anIntArray249[];
    public int anIntArray250[];
    public int anIntArray251[];
    public int anIntArray252[] = {
            -1, -1, -1, -1, -1
    };
    public boolean aBoolean253;

}
