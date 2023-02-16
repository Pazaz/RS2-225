package com.jagex.core.util;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!s")
public final class Cache {

	@OriginalMember(owner = "client!s", name = "b", descriptor = "I")
	public static final int CACHE_SIZE = 5;

	@OriginalMember(owner = "client!s", name = "c", descriptor = "I")
	private final int capacity;

	@OriginalMember(owner = "client!s", name = "d", descriptor = "I")
	private int available;

	@OriginalMember(owner = "client!s", name = "e", descriptor = "Lclient!t;")
	private final Hashtable hashtable = new Hashtable(1024);

	@OriginalMember(owner = "client!s", name = "f", descriptor = "Lclient!pb;")
	private final Stack history = new Stack();

	@OriginalMember(owner = "client!s", name = "<init>", descriptor = "(BI)V")
	public Cache(@OriginalArg(1) int arg1) {
		this.capacity = arg1;
		this.available = arg1;
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "(J)Lclient!db;")
	public CacheableNode get(@OriginalArg(0) long arg0) {
		@Pc(5) CacheableNode local5 = (CacheableNode) this.hashtable.get(arg0);
		if (local5 != null) {
			this.history.push(local5);
		}
		return local5;
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "(IJLclient!db;)V")
	public void put(@OriginalArg(1) long arg1, @OriginalArg(2) CacheableNode arg2) {
		if (this.available == 0) {
			@Pc(8) CacheableNode local8 = this.history.pop();
			local8.unlink();
			local8.uncache();
		} else {
			this.available--;
		}
		this.hashtable.put(arg1, arg2);
		this.history.push(arg2);
	}

	@OriginalMember(owner = "client!s", name = "a", descriptor = "()V")
	public void clear() {
		while (true) {
			@Pc(3) CacheableNode local3 = this.history.pop();
			if (local3 == null) {
				this.available = this.capacity;
				return;
			}
			local3.unlink();
			local3.uncache();
		}
	}
}
