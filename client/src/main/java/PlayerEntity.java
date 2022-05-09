import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!z")
public final class PlayerEntity extends PathingEntity {

	@OriginalMember(owner = "client!z", name = "Cb", descriptor = "Lclient!s;")
	public static Cache aCache_9 = new Cache((byte) 0, 200);

	@OriginalMember(owner = "client!z", name = "gb", descriptor = "I")
	private int anInt929;

	@OriginalMember(owner = "client!z", name = "ib", descriptor = "Ljava/lang/String;")
	public String aString29;

	@OriginalMember(owner = "client!z", name = "kb", descriptor = "I")
	private int anInt930;

	@OriginalMember(owner = "client!z", name = "lb", descriptor = "I")
	public int anInt931;

	@OriginalMember(owner = "client!z", name = "ob", descriptor = "I")
	public int anInt932;

	@OriginalMember(owner = "client!z", name = "pb", descriptor = "J")
	private long aLong27;

	@OriginalMember(owner = "client!z", name = "qb", descriptor = "I")
	public int anInt933;

	@OriginalMember(owner = "client!z", name = "rb", descriptor = "I")
	public int anInt934;

	@OriginalMember(owner = "client!z", name = "sb", descriptor = "I")
	public int anInt935;

	@OriginalMember(owner = "client!z", name = "tb", descriptor = "I")
	public int anInt936;

	@OriginalMember(owner = "client!z", name = "ub", descriptor = "I")
	public int anInt937;

	@OriginalMember(owner = "client!z", name = "vb", descriptor = "I")
	public int anInt938;

	@OriginalMember(owner = "client!z", name = "wb", descriptor = "Lclient!eb;")
	public Model aModel_11;

	@OriginalMember(owner = "client!z", name = "xb", descriptor = "I")
	public int anInt939;

	@OriginalMember(owner = "client!z", name = "yb", descriptor = "I")
	public int anInt940;

	@OriginalMember(owner = "client!z", name = "zb", descriptor = "I")
	public int anInt941;

	@OriginalMember(owner = "client!z", name = "Ab", descriptor = "I")
	public int anInt942;

	@OriginalMember(owner = "client!z", name = "hb", descriptor = "Z")
	private final boolean aBoolean161 = false;

	@OriginalMember(owner = "client!z", name = "jb", descriptor = "Z")
	private boolean aBoolean162 = false;

	@OriginalMember(owner = "client!z", name = "mb", descriptor = "[I")
	private final int[] anIntArray234 = new int[12];

	@OriginalMember(owner = "client!z", name = "nb", descriptor = "[I")
	private final int[] anIntArray235 = new int[5];

	@OriginalMember(owner = "client!z", name = "Bb", descriptor = "Z")
	public boolean aBoolean163 = false;

	@OriginalMember(owner = "client!z", name = "a", descriptor = "(ZLclient!kb;)V")
	public final void decode(@OriginalArg(1) Buffer arg0) {
		arg0.offset = 0;
		this.anInt930 = arg0.g1();
		this.anInt931 = arg0.g1();
		@Pc(19) int local19;
		@Pc(31) int local31;
		for (@Pc(14) int local14 = 0; local14 < 12; local14++) {
			local19 = arg0.g1();
			if (local19 == 0) {
				this.anIntArray234[local14] = 0;
			} else {
				local31 = arg0.g1();
				this.anIntArray234[local14] = (local19 << 8) + local31;
			}
		}
		for (local19 = 0; local19 < 5; local19++) {
			local31 = arg0.g1();
			if (local31 < 0 || local31 >= client.anIntArrayArray4[local19].length) {
				local31 = 0;
			}
			this.anIntArray235[local19] = local31;
		}
		super.standSeq = arg0.g2();
		if (super.standSeq == 65535) {
			super.standSeq = -1;
		}
		super.anInt887 = arg0.g2();
		if (super.anInt887 == 65535) {
			super.anInt887 = -1;
		}
		super.anInt888 = arg0.g2();
		if (super.anInt888 == 65535) {
			super.anInt888 = -1;
		}
		super.anInt889 = arg0.g2();
		if (super.anInt889 == 65535) {
			super.anInt889 = -1;
		}
		super.anInt890 = arg0.g2();
		if (super.anInt890 == 65535) {
			super.anInt890 = -1;
		}
		super.anInt891 = arg0.g2();
		if (super.anInt891 == 65535) {
			super.anInt891 = -1;
		}
		super.anInt892 = arg0.g2();
		if (super.anInt892 == 65535) {
			super.anInt892 = -1;
		}
		this.aString29 = StringUtils.formatName(StringUtils.fromBase37(arg0.g8()));
		this.anInt932 = arg0.g1();
		this.aBoolean162 = true;
		this.aLong27 = 0L;
		for (local31 = 0; local31 < 12; local31++) {
			this.aLong27 <<= 0x4;
			if (this.anIntArray234[local31] >= 256) {
				this.aLong27 += this.anIntArray234[local31] - 256;
			}
		}
		if (this.anIntArray234[0] >= 256) {
			this.aLong27 += this.anIntArray234[0] - 256 >> 4;
		}
		if (this.anIntArray234[1] >= 256) {
			this.aLong27 += this.anIntArray234[1] - 256 >> 8;
		}
		for (@Pc(243) int local243 = 0; local243 < 5; local243++) {
			this.aLong27 <<= 0x3;
			this.aLong27 += this.anIntArray235[local243];
		}
		this.aLong27 <<= 0x1;
		this.aLong27 += this.anInt930;
	}

	@OriginalMember(owner = "client!z", name = "a", descriptor = "(Z)Lclient!eb;")
	@Override
	public final Model getDrawMethod() {
		if (!this.aBoolean162) {
			return null;
		}
		@Pc(10) Model local10 = this.getModel();
		super.height = local10.maxBoundY;
		local10.pickable = true;
		if (this.aBoolean163) {
			return local10;
		}
		if (super.spotAnimIndex != -1 && super.spotAnimFrame != -1) {
			@Pc(35) SpotAnimType local35 = SpotAnimType.instances[super.spotAnimIndex];
			@Pc(51) Model local51 = new Model(local35.getModel(), true, !local35.disposeAlpha, this.anInt929, false);
			local51.translate(-super.spotAnimOffsetY, 0, 0);
			local51.applyGroup();
			local51.applyFrame(local35.seq.primaryFrames[super.spotAnimFrame]);
			local51.skinTriangle = null;
			local51.labelVertices = null;
			if (local35.breadthScale != 128 || local35.depthScale != 128) {
				local51.scale(local35.breadthScale, local35.depthScale, local35.breadthScale);
			}
			local51.applyLighting(local35.ambience + 64, local35.modelShadow + 850, -30, -50, -30, true);
			@Pc(119) Model[] local119 = new Model[] { local10, local51 };
			local10 = new Model(local119, (byte) -31, 2, true);
		}
		if (this.aModel_11 != null) {
			if (client.anInt266 >= this.anInt935) {
				this.aModel_11 = null;
			}
			if (client.anInt266 >= this.anInt934 && client.anInt266 < this.anInt935) {
				@Pc(148) Model local148 = this.aModel_11;
				local148.translate(this.anInt937 - this.anInt933, this.anInt936 - super.anInt882, this.anInt938 - super.anInt883);
				if (super.anInt926 == 512) {
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
				} else if (super.anInt926 == 1024) {
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
				} else if (super.anInt926 == 1536) {
					local148.rotateCounterClockwise();
				}
				@Pc(211) Model[] local211 = new Model[] { local10, local148 };
				local10 = new Model(local211, (byte) -31, 2, true);
				if (super.anInt926 == 512) {
					local148.rotateCounterClockwise();
				} else if (super.anInt926 == 1024) {
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
				} else if (super.anInt926 == 1536) {
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
					local148.rotateCounterClockwise();
				}
				local148.translate(this.anInt933 - this.anInt937, super.anInt882 - this.anInt936, super.anInt883 - this.anInt938);
			}
		}
		local10.pickable = true;
		return local10;
	}

	@OriginalMember(owner = "client!z", name = "c", descriptor = "(Z)Lclient!eb;")
	private Model getModel() {
		@Pc(4) long local4 = this.aLong27;
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
			if (local23.shieldOverride >= 0) {
				local10 = local23.shieldOverride;
				local4 += local10 - this.anIntArray234[5] << 8;
			}
			if (local23.weaponOverride >= 0) {
				local12 = local23.weaponOverride;
				local4 += local12 - this.anIntArray234[3] << 16;
			}
		} else if (super.secondarySeq >= 0) {
			local6 = SeqType.instances[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
		}
		@Pc(101) Model local101 = (Model) aCache_9.get(local4);
		if (local101 == null) {
			@Pc(106) Model[] local106 = new Model[12];
			@Pc(108) int local108 = 0;
			@Pc(117) int local117;
			for (@Pc(110) int local110 = 0; local110 < 12; local110++) {
				local117 = this.anIntArray234[local110];
				if (local12 >= 0 && local110 == 3) {
					local117 = local12;
				}
				if (local10 >= 0 && local110 == 5) {
					local117 = local10;
				}
				if (local117 >= 256 && local117 < 512) {
					local106[local108++] = IdkType.aIdkTypeArray1[local117 - 256].getModel();
				}
				if (local117 >= 512) {
					@Pc(155) ObjType local155 = ObjType.get(local117 - 512);
					@Pc(161) Model local161 = local155.getWornModel(this.anInt930);
					if (local161 != null) {
						local106[local108++] = local161;
					}
				}
			}
			local101 = new Model(0, local106, local108);
			for (local117 = 0; local117 < 5; local117++) {
				if (this.anIntArray235[local117] != 0) {
					local101.recolor(client.anIntArrayArray4[local117][0], client.anIntArrayArray4[local117][this.anIntArray235[local117]]);
					if (local117 == 1) {
						local101.recolor(client.anIntArray70[0], client.anIntArray70[this.anIntArray235[local117]]);
					}
				}
			}
			local101.applyGroup();
			local101.applyLighting(64, 850, -30, -50, -30, true);
			aCache_9.put(local4, local101);
		}
		if (this.aBoolean163) {
			return local101;
		}
		@Pc(249) Model local249 = new Model(0, local101, true);
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
		if (!this.aBoolean162) {
			return null;
		}
		@Pc(9) Model[] local9 = new Model[12];
		@Pc(11) int local11 = 0;
		for (@Pc(13) int local13 = 0; local13 < 12; local13++) {
			@Pc(20) int local20 = this.anIntArray234[local13];
			if (local20 >= 256 && local20 < 512) {
				local9[local11++] = IdkType.aIdkTypeArray1[local20 - 256].getHeadModel();
			}
			if (local20 >= 512) {
				@Pc(49) Model local49 = ObjType.get(local20 - 512).getHeadModel(this.anInt930);
				if (local49 != null) {
					local9[local11++] = local49;
				}
			}
		}
		@Pc(67) Model local67 = new Model(0, local9, local11);
		for (@Pc(69) int local69 = 0; local69 < 5; local69++) {
			if (this.anIntArray235[local69] != 0) {
				local67.recolor(client.anIntArrayArray4[local69][0], client.anIntArrayArray4[local69][this.anIntArray235[local69]]);
				if (local69 == 1) {
					local67.recolor(client.anIntArray70[0], client.anIntArray70[this.anIntArray235[local69]]);
				}
			}
		}
		return local67;
	}

	@OriginalMember(owner = "client!z", name = "b", descriptor = "(Z)Z")
	@Override
	public final boolean isValid() {
		return this.aBoolean162;
	}
}
