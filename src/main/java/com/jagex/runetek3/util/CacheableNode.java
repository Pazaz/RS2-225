package com.jagex.runetek3.util;

public class CacheableNode extends Node {

    public void uncache() {
        if (prevCacheable != null) {
            prevCacheable.nextCacheable = nextCacheable;
            nextCacheable.prevCacheable = prevCacheable;
            nextCacheable = null;
            prevCacheable = null;
        }
    }

    public CacheableNode() {
    }

    public CacheableNode nextCacheable;
    public CacheableNode prevCacheable;
}
