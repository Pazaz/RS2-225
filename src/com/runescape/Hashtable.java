package com.runescape;

public class Hashtable {

    public Hashtable(int size) {
        this.size = size;
        nodes = new Node[size];

        for (int k = 0; k < size; k++) {
            Node node = nodes[k] = new Node();
            node.prev = node;
            node.next = node;
        }
    }

    public Node get(long key) {
        Node start = nodes[(int) (key & (long) (size - 1))];
        for (Node node = start.prev; node != start; node = node.prev) {
            if (node.id == key) {
                return node;
            }
        }

        return null;
    }

    public void put(long key, Node value) {
        if (value.next != null) {
            value.unlink();
        }

        Node node = nodes[(int) (key & (long) (size - 1))];
        value.next = node.next;
        value.prev = node;
        value.next.prev = value;
        value.prev.next = value;
        value.id = key;
    }

    public int size;
    public Node[] nodes;
}
