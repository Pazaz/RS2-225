package com.runescape;

public class Class36 {

    public Class36(int i, int j) {
        anInt644 = 4277;
        aBoolean645 = false;
        anInt646 = j;
        nodes = new Node[j];
        if (i < 9 || i > 9)
            aBoolean645 = !aBoolean645;
        for (int k = 0; k < j; k++) {
            Node node = nodes[k] = new Node();
            node.prev = node;
            node.next = node;
        }

    }

    public Node method344(long l) {
        Node node = nodes[(int) (l & (long) (anInt646 - 1))];
        for (Node node_1 = node.prev; node_1 != node; node_1 = node_1.prev)
            if (node_1.id == l)
                return node_1;

        return null;
    }

    public void method345(long l, int i, Node node) {
        if (node.next != null)
            node.unlink();
        Node node_1 = nodes[(int) (l & (long) (anInt646 - 1))];
        node.next = node_1.next;
        if (i >= 0) {
            return;
        } else {
            node.prev = node_1;
            node.next.prev = node;
            node.prev.next = node;
            node.id = l;
            return;
        }
    }

    public int anInt644;
    public boolean aBoolean645;
    public int anInt646;
    public Node[] nodes;
}
