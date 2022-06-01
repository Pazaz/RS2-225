package com.jagex.core.utils;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!s")
public class Cache {

	@OriginalMember(owner = "client!s", name = "e", descriptor = "Lclient!t;")
	private final Hashtable hashtable = new Hashtable(1024);

	@OriginalMember(owner = "client!s", name = "f", descriptor = "Lclient!pb;")
	private final Stack history = new Stack();

	@OriginalMember(owner = "client!s", name = "c", descriptor = "I")
	private final int capacity;

	@OriginalMember(owner = "client!s", name = "d", descriptor = "I")
	private int available;

	@OriginalMember(owner = "client!s", name = "<init>", descriptor = "(BI)V")
	public Cache(@OriginalArg(1) int length) {
		this.capacity = length;
		this.available = length;
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "(J)Lclient!db;")
	public CacheableNode get(@OriginalArg(0) long key) {
		@Pc(5) CacheableNode node = (CacheableNode) this.hashtable.get(key);
		if (node != null) {
			this.history.push(node);
		}
		return node;
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "(IJLclient!db;)V")
	public void put(@OriginalArg(1) long key, @OriginalArg(2) CacheableNode node) {
		if (this.available == 0) {
			@Pc(8) CacheableNode last = this.history.pop();
			last.unlink();
			last.uncache();
		} else {
			this.available--;
		}
		this.hashtable.put(key, node);
		this.history.push(node);
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "()V")
	public final void clear() {
		while (true) {
			@Pc(3) CacheableNode last = this.history.pop();
			if (last == null) {
				this.available = this.capacity;
				return;
			}
			last.unlink();
			last.uncache();
		}
	}

	@OriginalMember(owner = "client!s", name = "b", descriptor = "I")
	private static final int flowObfuscator1 = 5;

	@OriginalMember(owner = "client!s", name = "a", descriptor = "Z")
	private final boolean flowObfuscator2 = false;

}
