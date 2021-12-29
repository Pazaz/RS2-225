package com.runescape.util;

public class Hashtable {

    public Hashtable(int i, int j) {
        anInt644 = 4277;
        aBoolean645 = false;
        anInt646 = j;
        aClass38Array647 = new Node[j];
        if (i < 9 || i > 9)
            aBoolean645 = !aBoolean645;
        for (int k = 0; k < j; k++) {
            Node class38 = aClass38Array647[k] = new Node();
            class38.aClass38_655 = class38;
            class38.aClass38_656 = class38;
        }

    }

    public Node method344(long l) {
        Node class38 = aClass38Array647[(int) (l & (long) (anInt646 - 1))];
        for (Node class38_1 = class38.aClass38_655; class38_1 != class38; class38_1 = class38_1.aClass38_655)
            if (class38_1.aLong654 == l)
                return class38_1;

        return null;
    }

    public void method345(long l, int i, Node class38) {
        if (class38.aClass38_656 != null)
            class38.method349();
        Node class38_1 = aClass38Array647[(int) (l & (long) (anInt646 - 1))];
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
    public Node[] aClass38Array647;
}
