package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!lc")
public class VarpType {

	@OriginalMember(owner = "client!lc", name = "c", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!lc", name = "d", descriptor = "[Lclient!lc;")
	public static VarpType[] instances;

	@OriginalMember(owner = "client!lc", name = "e", descriptor = "I")
	private static int opcode3Count;

	@OriginalMember(owner = "client!lc", name = "f", descriptor = "[I")
	private static int[] opcode3Array;

	@OriginalMember(owner = "client!lc", name = "g", descriptor = "Ljava/lang/String;")
	private String opcode9;

	@OriginalMember(owner = "client!lc", name = "h", descriptor = "I")
	private int opcode1;

	@OriginalMember(owner = "client!lc", name = "i", descriptor = "I")
	private int opcode2;

	@OriginalMember(owner = "client!lc", name = "l", descriptor = "I")
	public int type;

	@OriginalMember(owner = "client!lc", name = "n", descriptor = "I")
	private int opcode7;

	@OriginalMember(owner = "client!lc", name = "j", descriptor = "Z")
	private boolean opcode3 = false;

	@OriginalMember(owner = "client!lc", name = "k", descriptor = "Z")
	private boolean opcode4 = true;

	@OriginalMember(owner = "client!lc", name = "m", descriptor = "Z")
	private boolean opcode6 = false;

	@OriginalMember(owner = "client!lc", name = "o", descriptor = "Z")
	private boolean opcode8 = false;

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer buffer = new Buffer(archive.read("varp.dat", null));
		opcode3Count = 0;
		count = buffer.g2();
		if (instances == null) {
			instances = new VarpType[count];
		}
		if (opcode3Array == null) {
			opcode3Array = new int[count];
		}
		for (@Pc(30) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new VarpType();
			}
			instances[i].decode(i, buffer);
		}
	}

	@OriginalMember(owner = "client!lc", name = "<init>", descriptor = "()V")
	private VarpType() {
	}

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(IILclient!kb;)V")
	private void decode(@OriginalArg(1) int id, @OriginalArg(2) Buffer buffer) {
		while (true) {
			@Pc(8) int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}
			if (opcode == 1) {
				this.opcode1 = buffer.g1();
			} else if (opcode == 2) {
				this.opcode2 = buffer.g1();
			} else if (opcode == 3) {
				this.opcode3 = true;
				opcode3Array[opcode3Count++] = id;
			} else if (opcode == 4) {
				this.opcode4 = false;
			} else if (opcode == 5) {
				this.type = buffer.g2();
			} else if (opcode == 6) {
				this.opcode6 = true;
			} else if (opcode == 7) {
				this.opcode7 = buffer.g4();
			} else if (opcode == 8) {
				this.opcode8 = true;
			} else if (opcode == 10) {
				this.opcode9 = buffer.gstr();
			} else {
				System.out.println("Error unrecognised config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "I")
	private static final int flowObfuscator2 = 473;

	@OriginalMember(owner = "client!lc", name = "b", descriptor = "I")
	private static final int flowObfuscator1 = 13703;

}
