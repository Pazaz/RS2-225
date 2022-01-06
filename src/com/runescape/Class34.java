package com.runescape;

public class Class34 {

    public Class34(byte byte0, int i) {
        aBoolean590 = false;
        hashtable = new Hashtable(1024);
        stack = new Stack();
        anInt592 = i;
        if (byte0 != 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        anInt593 = i;
    }

    public CacheableNode method341(long l) {
        CacheableNode cacheableNode = (CacheableNode) hashtable.get(l);
        if (cacheableNode != null)
            stack.push(cacheableNode);
        return cacheableNode;
    }

    public void method342(int i, long l, CacheableNode cacheableNode) {
        if (anInt593 == 0) {
            CacheableNode cacheableNode_1 = stack.pop();
            cacheableNode_1.unlink();
            cacheableNode_1.uncache();
        } else {
            anInt593--;
        }
        hashtable.put(l, cacheableNode);
        if (i < 6 || i > 6)
            aBoolean590 = !aBoolean590;
        stack.push(cacheableNode);
    }

    public void method343() {
        do {
            CacheableNode cacheableNode = stack.pop();
            if (cacheableNode != null) {
                cacheableNode.unlink();
                cacheableNode.uncache();
            } else {
                anInt593 = anInt592;
                return;
            }
        } while (true);
    }

    public boolean aBoolean590;
    public static int anInt591 = 5;
    public int anInt592;
    public int anInt593;
    public Hashtable hashtable;
    public Stack stack;

}
