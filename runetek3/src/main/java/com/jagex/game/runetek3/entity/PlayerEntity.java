package com.jagex.game.runetek3.entity;

import com.jagex.core.io.Buffer;
import com.jagex.core.stringutils.StringUtils;
import com.jagex.core.utils.Cache;
import com.jagex.game.runetek3.config.IdkType;
import com.jagex.game.runetek3.config.ObjType;
import com.jagex.game.runetek3.config.SeqType;
import com.jagex.game.runetek3.config.SpotAnimType;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!z")
public class PlayerEntity extends PathingEntity {

	@OriginalMember(owner = "client!client", name = "Oe", descriptor = "[[I")
	public static final int[][] APPEARANCE_COLORS = new int[][] { { 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983, 54193 }, { 8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003, 25239 }, { 25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003 }, { 4626, 11146, 6439, 12, 4758, 10270 }, { 4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574 } };

	@OriginalMember(owner = "client!client", name = "qh", descriptor = "[I")
	public static final int[] BEARD_COLORS = new int[] { 9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 58654, 5027, 1457, 16565, 34991, 25486 };

	@OriginalMember(owner = "client!z", name = "Cb", descriptor = "Lclient!s;")
	public static Cache models = new Cache(200);

	@OriginalMember(owner = "client!z", name = "ib", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!z", name = "kb", descriptor = "I")
	public int gender;

	@OriginalMember(owner = "client!z", name = "lb", descriptor = "I")
	public int headicons;

	@OriginalMember(owner = "client!z", name = "ob", descriptor = "I")
	public int combatLevel;

	@OriginalMember(owner = "client!z", name = "pb", descriptor = "J")
	private long uid;

	@OriginalMember(owner = "client!z", name = "qb", descriptor = "I")
	public int plane;

	@OriginalMember(owner = "client!z", name = "rb", descriptor = "I")
	public int firstCycle;

	@OriginalMember(owner = "client!z", name = "sb", descriptor = "I")
	public int lastCycle;

	@OriginalMember(owner = "client!z", name = "tb", descriptor = "I")
	public int sceneX;

	@OriginalMember(owner = "client!z", name = "ub", descriptor = "I")
	public int sceneY;

	@OriginalMember(owner = "client!z", name = "vb", descriptor = "I")
	public int sceneZ;

	@OriginalMember(owner = "client!z", name = "wb", descriptor = "Lclient!eb;")
	public Model model;

	@OriginalMember(owner = "client!z", name = "xb", descriptor = "I")
	public int minTileX;

	@OriginalMember(owner = "client!z", name = "yb", descriptor = "I")
	public int minTileZ;

	@OriginalMember(owner = "client!z", name = "zb", descriptor = "I")
	public int maxTileX;

	@OriginalMember(owner = "client!z", name = "Ab", descriptor = "I")
	public int maxTileZ;

	@OriginalMember(owner = "client!z", name = "jb", descriptor = "Z")
	private boolean visible = false;

	@OriginalMember(owner = "client!z", name = "mb", descriptor = "[I")
	public final int[] body = new int[12];

	@OriginalMember(owner = "client!z", name = "nb", descriptor = "[I")
	public final int[] colors = new int[5];

	@OriginalMember(owner = "client!z", name = "Bb", descriptor = "Z")
	public boolean lowMemory = false;

	@OriginalMember(owner = "client!z", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer arg0) {
		arg0.pos = 0;
		this.gender = arg0.g1();
		this.headicons = arg0.g1();
		@Pc(19) int local19;
		@Pc(31) int local31;
		for (@Pc(14) int local14 = 0; local14 < 12; local14++) {
			local19 = arg0.g1();
			if (local19 == 0) {
				this.body[local14] = 0;
			} else {
				local31 = arg0.g1();
				this.body[local14] = (local19 << 8) + local31;
			}
		}
		for (local19 = 0; local19 < 5; local19++) {
			local31 = arg0.g1();
			if (local31 < 0 || local31 >= APPEARANCE_COLORS[local19].length) {
				local31 = 0;
			}
			this.colors[local19] = local31;
		}
		super.standSeq = arg0.g2();
		if (super.standSeq == 65535) {
			super.standSeq = -1;
		}
		super.turnSeq = arg0.g2();
		if (super.turnSeq == 65535) {
			super.turnSeq = -1;
		}
		super.runSeq = arg0.g2();
		if (super.runSeq == 65535) {
			super.runSeq = -1;
		}
		super.walkSeq = arg0.g2();
		if (super.walkSeq == 65535) {
			super.walkSeq = -1;
		}
		super.turnAroundSeq = arg0.g2();
		if (super.turnAroundSeq == 65535) {
			super.turnAroundSeq = -1;
		}
		super.turnRightSeq = arg0.g2();
		if (super.turnRightSeq == 65535) {
			super.turnRightSeq = -1;
		}
		super.turnLeftSeq = arg0.g2();
		if (super.turnLeftSeq == 65535) {
			super.turnLeftSeq = -1;
		}
		this.name = StringUtils.formatName(StringUtils.fromBase37(arg0.g8()));
		this.combatLevel = arg0.g1();
		this.visible = true;
		this.uid = 0L;
		for (local31 = 0; local31 < 12; local31++) {
			this.uid <<= 0x4;
			if (this.body[local31] >= 256) {
				this.uid += this.body[local31] - 256;
			}
		}
		if (this.body[0] >= 256) {
			this.uid += this.body[0] - 256 >> 4;
		}
		if (this.body[1] >= 256) {
			this.uid += this.body[1] - 256 >> 8;
		}
		for (@Pc(243) int local243 = 0; local243 < 5; local243++) {
			this.uid <<= 0x3;
			this.uid += this.colors[local243];
		}
		this.uid <<= 0x1;
		this.uid += this.gender;
	}

	@OriginalMember(owner = "client!z", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public final Model getDrawMethod(int cycle) {
		if (!this.visible) {
			return null;
		}
		@Pc(10) Model local10 = this.getModel();
		super.height = local10.maxBoundY;
		local10.pickable = true;
		if (this.lowMemory) {
			return local10;
		}
		if (super.spotAnimIndex != -1 && super.spotAnimFrame != -1) {
			@Pc(35) SpotAnimType local35 = SpotAnimType.instances[super.spotAnimIndex];
			@Pc(51) Model local51 = new Model(local35.getModel(), true, !local35.disposeAlpha, false);
			local51.translate(-super.spotAnimOffsetY, 0, 0);
			local51.applyGroup();
			local51.applyFrame(local35.seq.primaryFrames[super.spotAnimFrame]);
			local51.skinTriangle = null;
			local51.labelVertices = null;
			if (local35.resizeh != 128 || local35.resizev != 128) {
				local51.scale(local35.resizeh, local35.resizev, local35.resizeh);
			}
			local51.applyLighting(local35.ambient + 64, local35.contrast + 850, -30, -50, -30, true);
			@Pc(119) Model[] local119 = new Model[] { local10, local51 };
			local10 = new Model(local119, 2, true);
		}
		if (this.model != null) {
			if (cycle >= this.lastCycle) {
				this.model = null;
			}
			if (cycle >= this.firstCycle && cycle < this.lastCycle) {
				@Pc(148) Model local148 = this.model;
				local148.translate(this.sceneY - this.plane, this.sceneX - super.x, this.sceneZ - super.z);
				if (super.dstYaw == 512) {
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
				} else if (super.dstYaw == 1024) {
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
				} else if (super.dstYaw == 1536) {
					local148.rotateCounterClockwise();
				}
				@Pc(211) Model[] local211 = new Model[] { local10, local148 };
				local10 = new Model(local211, 2, true);
				if (super.dstYaw == 512) {
					local148.rotateCounterClockwise();
				} else if (super.dstYaw == 1024) {
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
				} else if (super.dstYaw == 1536) {
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
				}
				local148.translate(this.plane - this.sceneY, super.x - this.sceneX, super.z - this.sceneZ);
			}
		}
		local10.pickable = true;
		return local10;
	}

	@OriginalMember(owner = "client!z", name = "c", descriptor = "(Z)Lclient!eb;")
	private Model getModel() {
		@Pc(4) long local4 = this.uid;
		@Pc(6) int local6 = -1;
		@Pc(8) int local8 = -1;
		@Pc(10) int local10 = -1;
		@Pc(12) int local12 = -1;
		if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
			@Pc(23) SeqType local23 = SeqType.instances[super.primarySeq];
			local6 = local23.primaryFrames[super.primarySeqFrame];
			if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq) {
				local8 = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
			}
			if (local23.mainhand >= 0) {
				local10 = local23.mainhand;
				local4 += local10 - this.body[5] << 8;
			}
			if (local23.offhand >= 0) {
				local12 = local23.offhand;
				local4 += local12 - this.body[3] << 16;
			}
		} else if (super.secondarySeq >= 0) {
			local6 = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
		}
		@Pc(101) Model local101 = (Model) models.get(local4);
		if (local101 == null) {
			@Pc(106) Model[] local106 = new Model[12];
			@Pc(108) int local108 = 0;
			@Pc(117) int local117;
			for (@Pc(110) int local110 = 0; local110 < 12; local110++) {
				local117 = this.body[local110];
				if (local12 >= 0 && local110 == 3) {
					local117 = local12;
				}
				if (local10 >= 0 && local110 == 5) {
					local117 = local10;
				}
				if (local117 >= 256 && local117 < 512) {
					local106[local108++] = IdkType.instances[local117 - 256].getModel();
				}
				if (local117 >= 512) {
					@Pc(155) ObjType local155 = ObjType.get(local117 - 512);
					@Pc(161) Model local161 = local155.getWornModel(this.gender);
					if (local161 != null) {
						local106[local108++] = local161;
					}
				}
			}
			local101 = new Model(local106, local108);
			for (local117 = 0; local117 < 5; local117++) {
				if (this.colors[local117] != 0) {
					local101.recolor(APPEARANCE_COLORS[local117][0], APPEARANCE_COLORS[local117][this.colors[local117]]);
					if (local117 == 1) {
						local101.recolor(BEARD_COLORS[0], BEARD_COLORS[this.colors[local117]]);
					}
				}
			}
			local101.applyGroup();
			local101.applyLighting(64, 850, -30, -50, -30, true);
			models.put(local4, local101);
		}
		if (this.lowMemory) {
			return local101;
		}
		@Pc(249) Model local249 = new Model(local101, true);
		if (local6 != -1 && local8 != -1) {
			local249.applyFrames(local8, local6, SeqType.instances[super.primarySeq].labelGroups);
		} else if (local6 != -1) {
			local249.applyFrame(local6);
		}
		local249.calculateYBoundaries();
		local249.skinTriangle = null;
		local249.labelVertices = null;
		return local249;
	}

	@OriginalMember(owner = "client!z", name = "a", descriptor = "(I)Lclient!eb;")
	public final Model getHeadModel() {
		if (!this.visible) {
			return null;
		}
		@Pc(9) Model[] local9 = new Model[12];
		@Pc(11) int local11 = 0;
		for (@Pc(13) int local13 = 0; local13 < 12; local13++) {
			@Pc(20) int local20 = this.body[local13];
			if (local20 >= 256 && local20 < 512) {
				local9[local11++] = IdkType.instances[local20 - 256].getHeadModel();
			}
			if (local20 >= 512) {
				@Pc(49) Model local49 = ObjType.get(local20 - 512).getHeadModel(this.gender);
				if (local49 != null) {
					local9[local11++] = local49;
				}
			}
		}
		@Pc(67) Model local67 = new Model(local9, local11);
		for (@Pc(69) int local69 = 0; local69 < 5; local69++) {
			if (this.colors[local69] != 0) {
				local67.recolor(APPEARANCE_COLORS[local69][0], APPEARANCE_COLORS[local69][this.colors[local69]]);
				if (local69 == 1) {
					local67.recolor(BEARD_COLORS[0], BEARD_COLORS[this.colors[local69]]);
				}
			}
		}
		return local67;
	}

	@OriginalMember(owner = "client!z", name = "b", descriptor = "(Z)Z")
	@Override
	public final boolean isValid() {
		return this.visible;
	}

	@OriginalMember(owner = "client!z", name = "gb", descriptor = "I")
	private int flowObfuscator1;

	@OriginalMember(owner = "client!z", name = "hb", descriptor = "Z")
	private final boolean flowObfuscator2 = false;

}
