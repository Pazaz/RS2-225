package com.jagex.game.runetek3.scene.entities;

import com.jagex.core.io.Buffer;
import com.jagex.core.stringtools.StringUtils;
import com.jagex.core.util.Cache;
import com.jagex.game.runetek3.config.IdkType;
import com.jagex.game.runetek3.config.ObjType;
import com.jagex.game.runetek3.config.SeqType;
import com.jagex.game.runetek3.config.SpotAnimType;
import com.jagex.game.runetek3.graphics.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!z")
public class PlayerEntity extends PathingEntity {

	@OriginalMember(owner = "client!client", name = "Oe", descriptor = "[[I")
	public static final int[][] APPEARANCE_COLORS = new int[][] {
		{ 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983, 54193 }, // Head
		{ 8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003, 25239 }, // Torso
		{ 25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003 }, // Legs
		{ 4626, 11146, 6439, 12, 4758, 10270 }, // Feet
		{ 4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574 } // Skin
	};

	@OriginalMember(owner = "client!client", name = "qh", descriptor = "[I")
	public static final int[] BEARD_COLORS = new int[] {
		9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 58654, 5027, 1457, 16565, 34991, 25486
	};

	@OriginalMember(owner = "client!z", name = "Cb", descriptor = "Lclient!s;")
	public static Cache models = new Cache(200);

	@OriginalMember(owner = "client!z", name = "ib", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!z", name = "kb", descriptor = "I")
	private int gender;

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
	private final int[] body = new int[12];

	@OriginalMember(owner = "client!z", name = "nb", descriptor = "[I")
	private final int[] colors = new int[5];

	@OriginalMember(owner = "client!z", name = "Bb", descriptor = "Z")
	public boolean lowMemory = false;

	@OriginalMember(owner = "client!z", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer buf) {
		buf.pos = 0;
		this.gender = buf.g1();
		this.headicons = buf.g1();

		for (@Pc(14) int i = 0; i < 12; i++) {
			int msb = buf.g1();
			if (msb == 0) {
				this.body[i] = 0;
			} else {
				int lsb = buf.g1();
				this.body[i] = (msb << 8) + lsb;
			}
		}

		for (int i = 0; i < 5; i++) {
			int j = buf.g1();
			if (j < 0 || j >= APPEARANCE_COLORS[i].length) {
				j = 0;
			}

			this.colors[i] = j;
		}

		super.standSeq = buf.g2();
		if (super.standSeq == 65535) {
			super.standSeq = -1;
		}

		super.turnSeq = buf.g2();
		if (super.turnSeq == 65535) {
			super.turnSeq = -1;
		}

		super.runSeq = buf.g2();
		if (super.runSeq == 65535) {
			super.runSeq = -1;
		}

		super.walkSeq = buf.g2();
		if (super.walkSeq == 65535) {
			super.walkSeq = -1;
		}

		super.turnAroundSeq = buf.g2();
		if (super.turnAroundSeq == 65535) {
			super.turnAroundSeq = -1;
		}

		super.turnRightSeq = buf.g2();
		if (super.turnRightSeq == 65535) {
			super.turnRightSeq = -1;
		}

		super.turnLeftSeq = buf.g2();
		if (super.turnLeftSeq == 65535) {
			super.turnLeftSeq = -1;
		}

		this.name = StringUtils.formatName(StringUtils.fromBase37(buf.g8()));
		this.combatLevel = buf.g1();

		this.visible = true;
		this.uid = 0L;

		for (int i = 0; i < 12; i++) {
			this.uid <<= 0x4;

			if (this.body[i] >= 256) {
				this.uid += this.body[i] - 256;
			}
		}

		if (this.body[0] >= 256) {
			this.uid += this.body[0] - 256 >> 4;
		}

		if (this.body[1] >= 256) {
			this.uid += this.body[1] - 256 >> 8;
		}

		for (@Pc(243) int i = 0; i < 5; i++) {
			this.uid <<= 0x3;
			this.uid += this.colors[i];
		}

		this.uid <<= 0x1;
		this.uid += this.gender;
	}

	@OriginalMember(owner = "client!z", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public Model getDrawMethod(int currentCycle) {
		if (!this.visible) {
			return null;
		}

		@Pc(10) Model model = this.getModel();
		super.height = model.maxBoundY;
		model.pickable = true;

		if (this.lowMemory) {
			return model;
		}

		if (super.spotAnimIndex != -1 && super.spotAnimFrame != -1) {
			@Pc(35) SpotAnimType spot = SpotAnimType.instances[super.spotAnimIndex];
			@Pc(51) Model m = new Model(spot.getModel(), true, !spot.disposeAlpha, false);
			m.translate(-super.spotAnimOffsetY, 0, 0);
			m.createLabelReferences();
			m.applyTransform(spot.seq.primaryFrames[super.spotAnimFrame]);
			m.labelFaces = null;
			m.labelVertices = null;
			if (spot.resizeh != 128 || spot.resizev != 128) {
				m.scale(spot.resizeh, spot.resizev, spot.resizeh);
			}
			m.calculateNormals(spot.ambient + 64, spot.contrast + 850, -30, -50, -30, true);

			@Pc(119) Model[] models = new Model[] { model, m };
			model = new Model(models, 2, true);
		}

		if (this.model != null) {
			if (currentCycle >= this.lastCycle) {
				this.model = null;
			}

			if (currentCycle >= this.firstCycle && currentCycle < this.lastCycle) {
				@Pc(148) Model m = this.model;
				m.translate(this.sceneY - this.plane, this.sceneX - super.x, this.sceneZ - super.z);
				if (super.dstYaw == 512) {
					m.rotateY90();
					m.rotateY90();
					m.rotateY90();
				} else if (super.dstYaw == 1024) {
					m.rotateY90();
					m.rotateY90();
				} else if (super.dstYaw == 1536) {
					m.rotateY90();
				}

				@Pc(211) Model[] models = new Model[] { model, m };
				model = new Model(models, 2, true);

				if (super.dstYaw == 512) {
					m.rotateY90();
				} else if (super.dstYaw == 1024) {
					m.rotateY90();
					m.rotateY90();
				} else if (super.dstYaw == 1536) {
					m.rotateY90();
					m.rotateY90();
					m.rotateY90();
				}
				m.translate(this.plane - this.sceneY, super.x - this.sceneX, super.z - this.sceneZ);
			}
		}

		model.pickable = true;
		return model;
	}

	@OriginalMember(owner = "client!z", name = "c", descriptor = "(Z)Lclient!eb;")
	private Model getModel() {
		@Pc(4) long bitset = this.uid;
		@Pc(6) int primaryFrame = -1;
		@Pc(8) int secondaryFrame = -1;
		@Pc(10) int shieldOverride = -1;
		@Pc(12) int weaponOverride = -1;
		if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
			@Pc(23) SeqType seq = SeqType.instances[super.primarySeq];
			primaryFrame = seq.primaryFrames[super.primarySeqFrame];

			if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq) {
				secondaryFrame = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
			}

			if (seq.mainhand >= 0) {
				shieldOverride = seq.mainhand;
				bitset += (long) shieldOverride - this.body[5] << 8;
			}

			if (seq.offhand >= 0) {
				weaponOverride = seq.offhand;
				bitset += (long) weaponOverride - this.body[3] << 16;
			}
		} else if (super.secondarySeq >= 0) {
			primaryFrame = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
		}

		@Pc(101) Model m = (Model) models.get(bitset);
		if (m == null) {
			@Pc(106) Model[] models = new Model[12];
			
			@Pc(108) int count = 0;
			for (@Pc(110) int part = 0; part < 12; part++) {
				int index = this.body[part];
				if (weaponOverride >= 0 && part == 3) {
					index = weaponOverride;
				}

				if (shieldOverride >= 0 && part == 5) {
					index = shieldOverride;
				}

				if (index >= 0x100 && index < 0x200) {
					models[count++] = IdkType.instances[index - 0x100].getModel();
				}

				if (index >= 0x200) {
					@Pc(155) ObjType obj = ObjType.get(index - 0x200);
					@Pc(161) Model worn = obj.getWornModel(this.gender);

					if (worn != null) {
						models[count++] = worn;
					}
				}
			}

			m = new Model(models, count);
			for (int part = 0; part < 5; part++) {
				if (this.colors[part] != 0) {
					m.recolor(APPEARANCE_COLORS[part][0], APPEARANCE_COLORS[part][this.colors[part]]);

					if (part == 1) {
						m.recolor(BEARD_COLORS[0], BEARD_COLORS[this.colors[part]]);
					}
				}
			}

			m.createLabelReferences();
			m.calculateNormals(64, 850, -30, -50, -30, true);
			PlayerEntity.models.put(bitset, m);
		}

		if (this.lowMemory) {
			return m;
		}

		@Pc(249) Model m2 = new Model(m, true);
		if (primaryFrame != -1 && secondaryFrame != -1) {
			m2.applyTransforms(secondaryFrame, primaryFrame, SeqType.instances[super.primarySeq].labelGroups);
		} else if (primaryFrame != -1) {
			m2.applyTransform(primaryFrame);
		}
		m2.calculateBoundsCylinder();
		m2.labelFaces = null;
		m2.labelVertices = null;
		return m2;
	}

	@OriginalMember(owner = "client!z", name = "a", descriptor = "(I)Lclient!eb;")
	public Model getHeadModel() {
		if (!this.visible) {
			return null;
		}

		@Pc(9) Model[] models = new Model[12];
		@Pc(11) int count = 0;
		for (@Pc(13) int i = 0; i < 12; i++) {
			@Pc(20) int index = this.body[i];

			if (index >= 0x100 && index < 0x200) {
				models[count++] = IdkType.instances[index - 0x100].getHeadModel();
			}

			if (index >= 0x200) {
				@Pc(49) Model m = ObjType.get(index - 0x200).getHeadModel(this.gender);
				if (m != null) {
					models[count++] = m;
				}
			}
		}

		@Pc(67) Model m = new Model(models, count);
		for (@Pc(69) int i = 0; i < 5; i++) {
			if (this.colors[i] != 0) {
				m.recolor(APPEARANCE_COLORS[i][0], APPEARANCE_COLORS[i][this.colors[i]]);

				if (i == 1) {
					m.recolor(BEARD_COLORS[0], BEARD_COLORS[this.colors[i]]);
				}
			}
		}

		return m;
	}

	@OriginalMember(owner = "client!z", name = "b", descriptor = "(Z)Z")
	@Override
	public boolean isVisible() {
		return this.visible;
	}

}
