package com.jagex.game.runetek3.client;

import java.awt.*;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!b")
public class GameFrame extends Frame {

	@OriginalMember(owner = "client!b", name = "b", descriptor = "Lclient!a;")
	private final GameShell shell;

	@OriginalMember(owner = "client!b", name = "<init>", descriptor = "(IILclient!a;I)V")
	public GameFrame(@OriginalArg(2) GameShell shell) {
		this.shell = shell;
		this.setTitle("Jagex");
		this.setResizable(false);

		this.setLayout(new BorderLayout());
		this.add(shell);
		this.pack();

		this.setVisible(true);
		this.toFront();
	}

	@OriginalMember(owner = "client!b", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public void update(@OriginalArg(0) Graphics g) {
		this.shell.update(g);
	}

	@OriginalMember(owner = "client!b", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public void paint(@OriginalArg(0) Graphics g) {
		this.shell.paint(g);
	}

	@OriginalMember(owner = "client!b", name = "a", descriptor = "I")
	private final int flowObfuscator1 = 8;

}
