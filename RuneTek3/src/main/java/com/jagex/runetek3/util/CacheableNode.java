package com.jagex.runetek3.util;

public class CacheableNode extends Node {

    public CacheableNode nextCacheable;
    public CacheableNode prevCacheable;

    public CacheableNode() {
    }

    public void uncache() {
        if (prevCacheable != null) {
            prevCacheable.nextCacheable = nextCacheable;
            nextCacheable.prevCacheable = prevCacheable;
            nextCacheable = null;
            prevCacheable = null;
        }
    }
}
