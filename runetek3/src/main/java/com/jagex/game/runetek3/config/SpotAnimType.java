package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.utils.Cache;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kc")
public class SpotAnimType {

	@OriginalMember(owner = "client!kc", name = "b", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!kc", name = "c", descriptor = "[Lclient!kc;")
	public static SpotAnimType[] instances;

	@OriginalMember(owner = "client!kc", name = "p", descriptor = "Lclient!s;")
	public static Cache models = new Cache(30);

	@OriginalMember(owner = "client!kc", name = "d", descriptor = "I")
	private int index;

	@OriginalMember(owner = "client!kc", name = "e", descriptor = "I")
	private int modelIndex;

	@OriginalMember(owner = "client!kc", name = "g", descriptor = "Lclient!jc;")
	public SeqType seq;

	@OriginalMember(owner = "client!kc", name = "m", descriptor = "I")
	public int orientation;

	@OriginalMember(owner = "client!kc", name = "n", descriptor = "I")
	public int ambience;

	@OriginalMember(owner = "client!kc", name = "o", descriptor = "I")
	public int modelShadow;

	@OriginalMember(owner = "client!kc", name = "f", descriptor = "I")
	private int seqIndex = -1;

	@OriginalMember(owner = "client!kc", name = "h", descriptor = "Z")
	public boolean disposeAlpha = false;

	@OriginalMember(owner = "client!kc", name = "i", descriptor = "[I")
	private final int[] recol_s = new int[6];

	@OriginalMember(owner = "client!kc", name = "j", descriptor = "[I")
	private final int[] recol_d = new int[6];

	@OriginalMember(owner = "client!kc", name = "k", descriptor = "I")
	public int breadthScale = 128;

	@OriginalMember(owner = "client!kc", name = "l", descriptor = "I")
	public int depthScale = 128;

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(13) Buffer buffer = new Buffer(archive.read("spotanim.dat", null));
		count = buffer.g2();
		if (instances == null) {
			instances = new SpotAnimType[count];
		}
		for (@Pc(23) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new SpotAnimType();
			}
			instances[i].index = i;
			instances[i].decode(buffer);
		}
	}

	@OriginalMember(owner = "client!kc", name = "<init>", descriptor = "()V")
	private SpotAnimType() {
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(13) int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}
			if (opcode == 1) {
				this.modelIndex = buffer.g2();
			} else if (opcode == 2) {
				this.seqIndex = buffer.g2();
				if (SeqType.instances != null) {
					this.seq = SeqType.instances[this.seqIndex];
				}
			} else if (opcode == 3) {
				this.disposeAlpha = true;
			} else if (opcode == 4) {
				this.breadthScale = buffer.g2();
			} else if (opcode == 5) {
				this.depthScale = buffer.g2();
			} else if (opcode == 6) {
				this.orientation = buffer.g2();
			} else if (opcode == 7) {
				this.ambience = buffer.g1();
			} else if (opcode == 8) {
				this.modelShadow = buffer.g1();
			} else if (opcode >= 40 && opcode < 50) {
				this.recol_s[opcode - 40] = buffer.g2();
			} else if (opcode >= 50 && opcode < 60) {
				this.recol_d[opcode - 50] = buffer.g2();
			} else {
				System.out.println("Error unrecognised spotanim config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "()Lclient!eb;")
	public final Model getModel() {
		@Pc(6) Model m = (Model) models.get((long) this.index);
		if (m != null) {
			return m;
		}
		m = new Model(this.modelIndex);
		for (@Pc(19) int i = 0; i < 6; i++) {
			if (this.recol_s[0] != 0) {
				m.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}
		models.put((long) this.index, m);
		return m;
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "I")
	private static final int flowObfuscator1 = 473;

}
