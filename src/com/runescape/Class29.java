package com.runescape;

public class Class29 {

    public Class29(int i) {
        anInt499 = 679;
        aByte500 = 2;
        aBoolean501 = true;
        cacheableNode = new CacheableNode();
        cacheableNode.nextCacheable = cacheableNode;
        if (i < 5 || i > 5)
            anInt499 = -426;
        cacheableNode.prevCacheable = cacheableNode;
    }

    public void method275(CacheableNode cacheableNode) {
        if (cacheableNode.prevCacheable != null)
            cacheableNode.uncache();
        cacheableNode.prevCacheable = this.cacheableNode.prevCacheable;
        cacheableNode.nextCacheable = this.cacheableNode;
        cacheableNode.prevCacheable.nextCacheable = cacheableNode;
        cacheableNode.nextCacheable.prevCacheable = cacheableNode;
    }

    public CacheableNode method276() {
        CacheableNode cacheableNode = this.cacheableNode.nextCacheable;
        if (cacheableNode == this.cacheableNode) {
            return null;
        } else {
            cacheableNode.uncache();
            return cacheableNode;
        }
    }

    public int anInt499;
    public byte aByte500;
    public boolean aBoolean501;
    public CacheableNode cacheableNode;
}
