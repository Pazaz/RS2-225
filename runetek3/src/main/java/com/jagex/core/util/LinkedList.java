package com.jagex.core.util;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ob")
public class LinkedList {

	@OriginalMember(owner = "client!ob", name = "f", descriptor = "Lclient!u;")
	private Node selected;

	@OriginalMember(owner = "client!ob", name = "e", descriptor = "Lclient!u;")
	private final Node head = new Node();

	@OriginalMember(owner = "client!ob", name = "<init>", descriptor = "(I)V")
	public LinkedList() {
		this.head.prev = this.head;
		this.head.next = this.head;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;)V")
	public void pushNext(@OriginalArg(0) Node node) {
		if (node.next != null) {
			node.unlink();
		}

		node.next = this.head.next;
		node.prev = this.head;
		node.next.prev = node;
		node.prev.next = node;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Lclient!u;I)V")
	public void pushPrevious(@OriginalArg(0) Node node) {
		if (node.next != null) {
			node.unlink();
		}

		node.next = this.head;
		node.prev = this.head.prev;
		node.next.prev = node;
		node.prev.next = node;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "()Lclient!u;")
	public Node poll() {
		@Pc(3) Node node = this.head.prev;
		if (node == this.head) {
			return null;
		} else {
			node.unlink();
			return node;
		}
	}

	@OriginalMember(owner = "client!ob", name = "b", descriptor = "()Lclient!u;")
	public Node peekPrevious() {
		@Pc(3) Node node = this.head.prev;
		if (node == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = node.prev;
			return node;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(B)Lclient!u;")
	public Node peekNext() {
		@Pc(3) Node node = this.head.next;
		if (node == this.head) {
			this.selected = null;
			return null;
		}
		this.selected = node.next;
		return node;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(I)Lclient!u;")
	public Node getPrevious() {
		@Pc(8) Node node = this.selected;
		if (node == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = node.prev;
			return node;
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(Z)Lclient!u;")
	public Node getNext() {
		@Pc(2) Node node = this.selected;
		if (node == this.head) {
			this.selected = null;
			return null;
		} else {
			this.selected = node.next;
			return node;
		}
	}

	@OriginalMember(owner = "client!ob", name = "c", descriptor = "()V")
	public void clear() {
		while (true) {
			@Pc(3) Node node = this.head.prev;
			if (node == this.head) {
				return;
			}
			node.unlink();
		}
	}
}
