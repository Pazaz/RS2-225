package com.runescape.util;

public class LinkedList {

    public LinkedList(int i) {
        aBoolean478 = true;
        aByte479 = 2;
        anInt480 = -546;
        anInt481 = -676;
        aClass38_482 = new Node();
        if (i != 0)
            aBoolean478 = !aBoolean478;
        aClass38_482.aClass38_655 = aClass38_482;
        aClass38_482.aClass38_656 = aClass38_482;
    }

    public void method267(Node class38) {
        if (class38.aClass38_656 != null)
            class38.method349();
        class38.aClass38_656 = aClass38_482.aClass38_656;
        class38.aClass38_655 = aClass38_482;
        class38.aClass38_656.aClass38_655 = class38;
        class38.aClass38_655.aClass38_656 = class38;
    }

    public void method268(Node class38, int i) {
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

    public Node method269() {
        Node class38 = aClass38_482.aClass38_655;
        if (class38 == aClass38_482) {
            return null;
        } else {
            class38.method349();
            return class38;
        }
    }

    public Node method270() {
        Node class38 = aClass38_482.aClass38_655;
        if (class38 == aClass38_482) {
            aClass38_483 = null;
            return null;
        } else {
            aClass38_483 = class38.aClass38_655;
            return class38;
        }
    }

    public Node method271(byte byte0) {
        Node class38 = aClass38_482.aClass38_656;
        if (class38 == aClass38_482) {
            aClass38_483 = null;
            return null;
        }
        aClass38_483 = class38.aClass38_656;
        if (byte0 != aByte479)
            anInt481 = 112;
        return class38;
    }

    public Node method272(int i) {
        if (i <= 0)
            throw new NullPointerException();
        Node class38 = aClass38_483;
        if (class38 == aClass38_482) {
            aClass38_483 = null;
            return null;
        } else {
            aClass38_483 = class38.aClass38_655;
            return class38;
        }
    }

    public Node method273(boolean flag) {
        Node class38 = aClass38_483;
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
            Node class38 = aClass38_482.aClass38_655;
            if (class38 == aClass38_482)
                return;
            class38.method349();
        } while (true);
    }

    public boolean aBoolean478;
    public byte aByte479;
    public int anInt480;
    public int anInt481;
    public Node aClass38_482;
    public Node aClass38_483;
}
