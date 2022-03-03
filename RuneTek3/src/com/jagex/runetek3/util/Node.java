package com.jagex.runetek3.util;

public class Node {

    public long id;
    public Node prev;
    public Node next;
    public Node() {
    }

    public void unlink() {
        if (next != null) {
            next.prev = prev;
            prev.next = next;
            prev = null;
            next = null;
        }
    }
}
