package com.jagex.runetek3.util;

public class Cache {

    public int capacity;
    public int available;
    public Hashtable table;
    public Stack history;

    public Cache(int length) {
        table = new Hashtable(1024);
        history = new Stack();
        capacity = length;
        available = length;
    }

    public CacheableNode get(long key) {
        CacheableNode node = (CacheableNode) table.get(key);
        if (node != null) {
            history.push(node);
        }

        return node;
    }

    public void put(long key, CacheableNode node) {
        if (available == 0) {
            CacheableNode last = history.pop();
            last.unlink();
            last.uncache();
        } else {
            available--;
        }

        table.put(key, node);
        history.push(node);
    }

    public void clear() {
        do {
            CacheableNode last = history.pop();
            if (last != null) {
                last.unlink();
                last.uncache();
            } else {
                available = capacity;
                return;
            }
        } while (true);
    }

}
