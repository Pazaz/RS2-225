package com.jagex.runetek3.util;

public class LinkedList {

    public Node head;
    public Node selected;

    public LinkedList() {
        head = new Node();
        head.prev = head;
        head.next = head;
    }

    public void pushNext(Node node) {
        if (node.next != null) {
            node.unlink();
        }
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        node.prev.next = node;
    }

    public void pushLast(Node node) {
        if (node.next != null) {
            node.unlink();
        }
        node.next = head;
        node.prev = head.prev;
        node.next.prev = node;
        node.prev.next = node;
    }

    public Node poll() {
        Node node = head.prev;
        if (node == head) {
            return null;
        }
        node.unlink();
        return node;
    }

    public Node peekLast() {
        Node node = head.prev;
        if (node == head) {
            selected = null;
            return null;
        }
        selected = node.prev;
        return node;
    }

    public Node peekFirst() {
        Node node = head.next;
        if (node == head) {
            selected = null;
            return null;
        }
        selected = node.next;
        return node;
    }

    public Node getPrevious() {
        Node node = selected;
        if (node == head) {
            selected = null;
            return null;
        }
        selected = node.prev;
        return node;
    }

    public Node getNext() {
        Node node = selected;
        if (node == head) {
            selected = null;
            return null;
        }
        selected = node.next;
        return node;
    }

    public void clear() {
        do {
            Node node = head.prev;
            if (node == head) {
                return;
            }
            node.unlink();
        } while (true);
    }
}
