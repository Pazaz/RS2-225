package com.runescape;

public class Node {

    public void unlink() {
        if (next != null) {
            next.prev = prev;
            prev.next = next;
            prev = null;
            next = null;
        }
    }

    public Node() {
    }

    public long id;
    public Node prev;
    public Node next;
}
