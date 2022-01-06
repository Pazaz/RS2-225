package com.runescape;

public class LinkedList {

    public LinkedList() {
        head = new Node();
        head.prev = head;
        head.next = head;
    }

    public void method267(Node node) {
        if (node.next != null) {
            node.unlink();
        }
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        node.prev.next = node;
    }

    public void method268(Node node) {
        if (node.next != null) {
            node.unlink();
        }
        node.next = head;
        node.prev = head.prev;
        node.next.prev = node;
        node.prev.next = node;
    }

    public Node method269() {
        Node node = head.prev;
        if (node == head) {
            return null;
        }
        node.unlink();
        return node;
    }

    public Node method270() {
        Node node = head.prev;
        if (node == head) {
            selected = null;
            return null;
        }
        selected = node.prev;
        return node;
    }

    public Node method271() {
        Node node = head.next;
        if (node == head) {
            selected = null;
            return null;
        }
        selected = node.next;
        return node;
    }

    public Node method272() {
        Node node = selected;
        if (node == head) {
            selected = null;
            return null;
        }
        selected = node.prev;
        return node;
    }

    public Node method273() {
        Node node = selected;
        if (node == head) {
            selected = null;
            return null;
        }
        selected = node.next;
        return node;
    }

    public void method274() {
        do {
            Node node = head.prev;
            if (node == head) {
                return;
            }
            node.unlink();
        } while (true);
    }

    public Node head;
    public Node selected;
}
