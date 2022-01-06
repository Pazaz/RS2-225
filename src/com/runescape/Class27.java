package com.runescape;

public class Class27 {

    public Class27(int i) {
        aBoolean478 = true;
        aByte479 = 2;
        anInt480 = -546;
        anInt481 = -676;
        aClass38_482 = new Class38();
        if (i != 0)
            aBoolean478 = !aBoolean478;
        aClass38_482.aClass38_655 = aClass38_482;
        aClass38_482.aClass38_656 = aClass38_482;
    }

    public void method267(Class38 class38) {
        if (class38.aClass38_656 != null)
            class38.method349();
        class38.aClass38_656 = aClass38_482.aClass38_656;
        class38.aClass38_655 = aClass38_482;
        class38.aClass38_656.aClass38_655 = class38;
        class38.aClass38_655.aClass38_656 = class38;
    }

    public void method268(Class38 class38, int i) {
        if (class38.aClass38_656 != null)
            class38.method349();
        class38.aClass38_656 = aClass38_482;
        if (i != -26173) {
            return;
        } else {
            class38.aClass38_655 = aClass38_482.aClass38_655;
            class38.aClass38_656.aClass38_655 = class38;
            class38.aClass38_655.aClass38_656 = class38;
            return;
        }
    }

    public Class38 method269() {
        Class38 class38 = aClass38_482.aClass38_655;
        if (class38 == aClass38_482) {
            return null;
        } else {
            class38.method349();
            return class38;
        }
    }

    public Class38 method270() {
        Class38 class38 = aClass38_482.aClass38_655;
        if (class38 == aClass38_482) {
            aClass38_483 = null;
            return null;
        } else {
            aClass38_483 = class38.aClass38_655;
            return class38;
        }
    }

    public Class38 method271(byte byte0) {
        Class38 class38 = aClass38_482.aClass38_656;
        if (class38 == aClass38_482) {
            aClass38_483 = null;
            return null;
        }
        aClass38_483 = class38.aClass38_656;
        if (byte0 != aByte479)
            anInt481 = 112;
        return class38;
    }

    public Class38 method272(int i) {
        if (i <= 0)
            throw new NullPointerException();
        Class38 class38 = aClass38_483;
        if (class38 == aClass38_482) {
            aClass38_483 = null;
            return null;
        } else {
            aClass38_483 = class38.aClass38_655;
            return class38;
        }
    }

    public Class38 method273(boolean flag) {
        Class38 class38 = aClass38_483;
        if (flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        if (class38 == aClass38_482) {
            aClass38_483 = null;
            return null;
        } else {
            aClass38_483 = class38.aClass38_656;
            return class38;
        }
    }

    public void method274() {
        do {
            Class38 class38 = aClass38_482.aClass38_655;
            if (class38 == aClass38_482)
                return;
            class38.method349();
        } while (true);
    }

    public boolean aBoolean478;
    public byte aByte479;
    public int anInt480;
    public int anInt481;
    public Class38 aClass38_482;
    public Class38 aClass38_483;
}
