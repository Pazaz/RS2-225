package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.utils.Cache;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ac")
public class LocType {

	public static final int WALL_STRAIGHT = 0;
	public static final int WALL_DIAGONALCORNER = 1;
	public static final int WALL_L = 2;
	public static final int WALL_SQUARECORNER = 3;
	public static final int WALL_DIAGONAL = 9;

	public static final int WALLDECOR_STRAIGHT_XOFFSET = 4;
	public static final int WALLDECOR_STRAIGHT_ZOFFSET = 5;
	public static final int WALLDECOR_DIAGONAL_XOFFSET = 6;
	public static final int WALLDECOR_DIAGONAL_ZOFFSET = 7;
	public static final int WALLDECOR_DIAGONAL_BOTH = 8;

	public static final int ROOF_STRAIGHT = 12;
	public static final int ROOF_DIAGONAL_WITH_ROOFEDGE = 13;
	public static final int ROOF_DIAGONAL = 14;
	public static final int ROOF_L_CONCAVE = 15;
	public static final int ROOF_L_CONVEX = 16;
	public static final int ROOF_FLAT = 17;

	public static final int ROOFEDGE_STRAIGHT = 18;
	public static final int ROOFEDGE_DIAGONALCORNER = 19;
	public static final int ROOFEDGE_L = 20;
	public static final int ROOFEDGE_SQUARECORNER = 21;

	public static final int CENTREPIECE_STRAIGHT = 10;
	public static final int CENTREPIECE_DIAGONAL = 11;
	public static final int GROUNDDECOR = 22;

	@OriginalMember(owner = "client!ac", name = "c", descriptor = "Z")
	private static boolean reset;

	@OriginalMember(owner = "client!ac", name = "d", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!ac", name = "e", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!ac", name = "f", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!ac", name = "g", descriptor = "[Lclient!ac;")
	private static LocType[] cache;

	@OriginalMember(owner = "client!ac", name = "h", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!ac", name = "P", descriptor = "Lclient!s;")
	public static Cache modelCache = new Cache(500);

	@OriginalMember(owner = "client!ac", name = "Q", descriptor = "Lclient!s;")
	public static Cache modelCacheBuilt = new Cache(30);

	@OriginalMember(owner = "client!ac", name = "j", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!ac", name = "k", descriptor = "[I")
	public int[] shapes;

	@OriginalMember(owner = "client!ac", name = "l", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!ac", name = "m", descriptor = "[B")
	public String desc;

	@OriginalMember(owner = "client!ac", name = "n", descriptor = "[I")
	private int[] recol_s;

	@OriginalMember(owner = "client!ac", name = "o", descriptor = "[I")
	private int[] recol_d;

	@OriginalMember(owner = "client!ac", name = "p", descriptor = "I")
	public int width;

	@OriginalMember(owner = "client!ac", name = "q", descriptor = "I")
	public int length;

	@OriginalMember(owner = "client!ac", name = "r", descriptor = "Z")
	public boolean blockwalk;

	@OriginalMember(owner = "client!ac", name = "s", descriptor = "Z")
	public boolean blockrange;

	@OriginalMember(owner = "client!ac", name = "t", descriptor = "Z")
	public boolean interactable;

	@OriginalMember(owner = "client!ac", name = "u", descriptor = "Z")
	public boolean hillskew;

	@OriginalMember(owner = "client!ac", name = "v", descriptor = "Z")
	public boolean computeVertexColors;

	@OriginalMember(owner = "client!ac", name = "w", descriptor = "Z")
	public boolean occlude;

	@OriginalMember(owner = "client!ac", name = "x", descriptor = "I")
	public int anim;

	@OriginalMember(owner = "client!ac", name = "y", descriptor = "I")
	public int walloff;

	@OriginalMember(owner = "client!ac", name = "z", descriptor = "B")
	public byte ambient;

	@OriginalMember(owner = "client!ac", name = "A", descriptor = "B")
	public byte contrast;

	@OriginalMember(owner = "client!ac", name = "B", descriptor = "[Ljava/lang/String;")
	public String[] ops;

	@OriginalMember(owner = "client!ac", name = "C", descriptor = "Z")
	public boolean disposeAlpha;

	@OriginalMember(owner = "client!ac", name = "D", descriptor = "I")
	public int mapfunction;

	@OriginalMember(owner = "client!ac", name = "E", descriptor = "I")
	public int mapscene;

	@OriginalMember(owner = "client!ac", name = "F", descriptor = "Z")
	public boolean mirror;

	@OriginalMember(owner = "client!ac", name = "G", descriptor = "Z")
	public boolean active;

	@OriginalMember(owner = "client!ac", name = "H", descriptor = "I")
	private int resizex;

	@OriginalMember(owner = "client!ac", name = "I", descriptor = "I")
	private int resizey;

	@OriginalMember(owner = "client!ac", name = "J", descriptor = "I")
	private int resizez;

	@OriginalMember(owner = "client!ac", name = "K", descriptor = "I")
	private int xoff;

	@OriginalMember(owner = "client!ac", name = "L", descriptor = "I")
	private int yoff;

	@OriginalMember(owner = "client!ac", name = "M", descriptor = "I")
	private int zoff;

	@OriginalMember(owner = "client!ac", name = "N", descriptor = "I")
	public int blocksides;

	@OriginalMember(owner = "client!ac", name = "O", descriptor = "Z")
	public boolean forcedecor;

	@OriginalMember(owner = "client!ac", name = "i", descriptor = "I")
	private int id = -1;

	public String identifier;

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(Lclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		dat = new Buffer(archive.read("loc.dat", null));

		@Pc(21) Buffer idx = new Buffer(archive.read("loc.idx", null));
		count = idx.g2();
		offsets = new int[count];
		@Pc(29) int offset = 2;
		for (@Pc(31) int i = 0; i < count; i++) {
			offsets[i] = offset;
			offset += idx.g2();
		}

		cache = new LocType[count];
		for (@Pc(51) int i = 0; i < cache.length; i++) {
			cache[i] = new LocType();
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(Z)V")
	public static void unload() {
		modelCache = null;
		modelCacheBuilt = null;
		offsets = null;
		cache = null;
		dat = null;
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(I)Lclient!ac;")
	public static LocType get(@OriginalArg(0) int id) {
		for (@Pc(1) int i = 0; i < cache.length; i++) {
			if (cache[i].id == id) {
				return cache[i];
			}
		}

		offset = (offset + 1) % cache.length;
		@Pc(27) LocType type = cache[offset];
		dat.pos = offsets[id];
		type.id = id;
		type.reset();
		type.decode(dat);
		return type;
	}

	@OriginalMember(owner = "client!ac", name = "<init>", descriptor = "()V")
	private LocType() {
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "()V")
	private void reset() {
		this.models = null;
		this.shapes = null;
		this.name = null;
		this.desc = null;
		this.recol_s = null;
		this.recol_d = null;
		this.width = 1;
		this.length = 1;
		this.blockwalk = true;
		this.blockrange = true;
		this.interactable = false;
		this.hillskew = false;
		this.computeVertexColors = false;
		this.occlude = false;
		this.anim = -1;
		this.walloff = 16;
		this.ambient = 0;
		this.contrast = 0;
		this.ops = null;
		this.disposeAlpha = false;
		this.mapfunction = -1;
		this.mapscene = -1;
		this.mirror = false;
		this.active = true;
		this.resizex = 128;
		this.resizey = 128;
		this.resizez = 128;
		this.blocksides = 0;
		this.xoff = 0;
		this.yoff = 0;
		this.zoff = 0;
		this.forcedecor = false;
		this.identifier = null;
	}

	public static class Opcodes {
		public static final int model = 1;
		public static final int name = 2;
		public static final int description = 3;
		public static final int width = 14;
		public static final int length = 15;
		public static final int blockwalk_no = 17;
		public static final int blockrange_no = 18;
		public static final int hillskew = 21;
		public static final int sharelight = 22;
		public static final int occlude = 23;
		public static final int anim = 24;
		public static final int walloff = 28;
		public static final int ambient = 29;
		public static final int op1 = 30;
		public static final int op5 = 34;
		public static final int contrast = 39;
		public static final int recol = 40;
		public static final int mapfunction = 60;
		public static final int mirror = 62;
		public static final int active = 64;
		public static final int resizex = 65;
		public static final int resizey = 66;
		public static final int resizez = 67;
		public static final int mapscene = 68;
		public static final int blocksides = 69;
		public static final int xoff = 70;
		public static final int yoff = 71;
		public static final int zoff = 72;
		public static final int forcedecor = 73;
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		@Pc(12) int interactive = -1;

		while (true) {
			@Pc(15) int opcode = buffer.g1();

			if (opcode == 0) {
				break;
			}

			@Pc(23) int count;
			@Pc(33) int i;
			if (opcode == Opcodes.model) { // 1
				count = buffer.g1();
				this.shapes = new int[count];
				this.models = new int[count];
				for (i = 0; i < count; i++) {
					this.models[i] = buffer.g2();
					this.shapes[i] = buffer.g1();
				}
			} else if (opcode == Opcodes.name) { // 2
				this.name = buffer.gstr();
			} else if (opcode == Opcodes.description) { // 3
				this.desc = buffer.gstr();
			} else if (opcode == Opcodes.width) { // 14
				this.width = buffer.g1();
			} else if (opcode == Opcodes.length) { // 15
				this.length = buffer.g1();
			} else if (opcode == Opcodes.blockwalk_no) { // 17
				this.blockwalk = false;
			} else if (opcode == Opcodes.blockrange_no) { // 18
				this.blockrange = false;
			} else if (opcode == 19) {
				interactive = buffer.g1();
				if (interactive == 1) {
					this.interactable = true;
				}
			} else if (opcode == Opcodes.hillskew) { // 21
				this.hillskew = true;
			} else if (opcode == Opcodes.sharelight) { // 22
				this.computeVertexColors = true;
			} else if (opcode == Opcodes.occlude) { // 23
				this.occlude = true;
			} else if (opcode == Opcodes.anim) { // 24
				this.anim = buffer.g2();
				if (this.anim == 65535) {
					this.anim = -1;
				}
			} else if (opcode == 25) {
				this.disposeAlpha = true;
			} else if (opcode == Opcodes.walloff) { // 28
				this.walloff = buffer.g1();
			} else if (opcode == Opcodes.ambient) { // 29
				this.ambient = buffer.g1b();
			} else if (opcode == Opcodes.contrast) { // 39
				this.contrast = buffer.g1b();
			} else if (opcode >= Opcodes.op1 && opcode <= Opcodes.op5) { // >= 30 && <= 34
				if (this.ops == null) {
					this.ops = new String[5];
				}

				this.ops[opcode - Opcodes.op1] = buffer.gstr();
				if (this.ops[opcode - Opcodes.op1].equalsIgnoreCase("hidden")) {
					this.ops[opcode - Opcodes.op1] = null;
				}
			} else if (opcode == Opcodes.recol) { // 40
				count = buffer.g1();
				this.recol_s = new int[count];
				this.recol_d = new int[count];
				for (i = 0; i < count; i++) {
					this.recol_s[i] = buffer.g2();
					this.recol_d[i] = buffer.g2();
				}
			} else if (opcode == Opcodes.mapfunction) { // 60
				this.mapfunction = buffer.g2();
			} else if (opcode == Opcodes.mirror) { // 62
				this.mirror = true;
			} else if (opcode == Opcodes.active) { // 64
				this.active = false;
			} else if (opcode == Opcodes.resizex) { // 65
				this.resizex = buffer.g2();
			} else if (opcode == Opcodes.resizey) { // 66
				this.resizey = buffer.g2();
			} else if (opcode == Opcodes.resizez) { // 67
				this.resizez = buffer.g2();
			} else if (opcode == Opcodes.mapscene) { // 68
				this.mapscene = buffer.g2();
			} else if (opcode == Opcodes.blocksides) { // 69
				this.blocksides = buffer.g1();
			} else if (opcode == Opcodes.xoff) { // 70
				this.xoff = buffer.g2b();
			} else if (opcode == Opcodes.yoff) { // 71
				this.yoff = buffer.g2b();
			} else if (opcode == Opcodes.zoff) { // 72
				this.zoff = buffer.g2b();
			} else if (opcode == Opcodes.forcedecor) { // 73
				this.forcedecor = true;
			}
		}

		if (this.shapes == null) {
			this.shapes = new int[0];
		}

		if (interactive == -1) {
			this.interactable = false;
			if (this.shapes.length > 0 && this.shapes[0] == CENTREPIECE_STRAIGHT) {
				this.interactable = true;
			}

			if (this.ops != null) {
				this.interactable = true;
			}
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(IIIIIII)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int type, @OriginalArg(1) int orientation, @OriginalArg(2) int southwestY, @OriginalArg(3) int southeastY, @OriginalArg(4) int northwestY, @OriginalArg(5) int northeastY, @OriginalArg(6) int seqFrame) {
		@Pc(3) int modelType = -1;
		for (@Pc(5) int i = 0; i < this.shapes.length; i++) {
			if (this.shapes[i] == type) {
				modelType = i;
				break;
			}
		}
		if (modelType == -1) {
			return null;
		}
		@Pc(47) long key = (long) ((this.id << 6) + (modelType << 3) + orientation) + ((long) (seqFrame + 1) << 32);
		if (reset) {
			key = 0L;
		}
		@Pc(56) Model m = (Model) modelCacheBuilt.get(key);
		@Pc(91) int modelIndex;
		@Pc(141) int n;
		if (m == null) {
			if (modelType >= this.models.length) {
				return null;
			}
			modelIndex = this.models[modelType];
			if (modelIndex == -1) {
				return null;
			}
			@Pc(188) boolean flipBackwards = this.mirror ^ orientation > 3;
			if (flipBackwards) {
				modelIndex += 65536;
			}
			@Pc(200) Model m2 = (Model) modelCache.get((long) modelIndex);
			if (m2 == null) {
				m2 = new Model(modelIndex & 0xFFFF);
				if (flipBackwards) {
					m2.flipBackwards();
				}
				modelCache.put((long) modelIndex, m2);
			}
			@Pc(235) boolean rescale;
			if (this.resizex == 128 && this.resizey == 128 && this.resizez == 128) {
				rescale = false;
			} else {
				rescale = true;
			}

			@Pc(250) boolean move;
			if (this.xoff == 0 && this.yoff == 0 && this.zoff == 0) {
				move = false;
			} else {
				move = true;
			}

			// disposeAlpha could become seqFrame != -1 to simplify this and remove an opcode
			@Pc(284) Model m3 = new Model(m2, this.recol_s == null, !disposeAlpha, orientation == 0 && seqFrame == -1 && !rescale && !move);
			if (seqFrame != -1) {
				m3.applyGroup();
				m3.applyFrame(seqFrame);
				m3.skinTriangle = null;
				m3.labelVertices = null;
			}

			while (orientation-- > 0) {
				m3.rotateCounterClockwise();
			}

			if (this.recol_s != null) {
				for (n = 0; n < this.recol_s.length; n++) {
					m3.recolor(this.recol_s[n], this.recol_d[n]);
				}
			}
			if (rescale) {
				m3.scale(this.resizez, this.resizey, this.resizex);
			}
			if (move) {
				m3.translate(this.yoff, this.xoff, this.zoff);
			}
			m3.applyLighting(this.ambient + 64, this.contrast * 5 + 768, -50, -10, -50, !this.computeVertexColors);
			if (this.blockwalk) {
				m3.anInt372 = m3.maxBoundY;
			}
			modelCacheBuilt.put(key, m3);
			if (this.hillskew || this.computeVertexColors) {
				m3 = new Model(m3, this.hillskew, this.computeVertexColors);
			}
			if (this.hillskew) {
				n = (southwestY + southeastY + northwestY + northeastY) / 4;
				for (@Pc(417) int v = 0; v < m3.vertexCount; v++) {
					@Pc(424) int x = m3.vertexX[v];
					@Pc(429) int z = m3.vertexZ[v];
					@Pc(441) int averageY1 = southwestY + (southeastY - southwestY) * (x + 64) / 128;
					@Pc(453) int averageY2 = northeastY + (northwestY - northeastY) * (x + 64) / 128;
					@Pc(465) int average = averageY1 + (averageY2 - averageY1) * (z + 64) / 128;
					m3.vertexY[v] += average - n;
				}
				m3.calculateYBoundaries2();
			}
			return m3;
		} else if (reset) {
			return m;
		} else {
			if (this.hillskew || this.computeVertexColors) {
				m = new Model(m, this.hillskew, this.computeVertexColors);
			}
			if (this.hillskew) {
				modelIndex = (southwestY + southeastY + northwestY + northeastY) / 4;
				for (@Pc(93) int v = 0; v < m.vertexCount; v++) {
					@Pc(100) int x = m.vertexX[v];
					@Pc(105) int z = m.vertexZ[v];
					@Pc(117) int averageY1 = southwestY + (southeastY - southwestY) * (x + 64) / 128;
					@Pc(129) int averageY2 = northeastY + (northwestY - northeastY) * (x + 64) / 128;
					n = averageY1 + (averageY2 - averageY1) * (z + 64) / 128;
					m.vertexY[v] += n - modelIndex;
				}
				m.calculateYBoundaries2();
			}
			return m;
		}
	}

	public String toJagConfig() {
		StringBuilder builder = new StringBuilder();

		if (this.identifier != null) {
			builder.append("[").append(this.identifier).append("]\n");
		} else {
			builder.append("[loc_").append(this.id).append("]\n");
		}

		if (this.name != null) {
			builder.append("name=").append(this.name).append("\n");
		}

		if (this.desc != null) {
			builder.append("desc=").append(this.desc).append("\n");
		}

		if (this.models != null) {
			for (int i = 0; i < this.models.length; ++i) {
				String name;

				// wish there was a nicer way to do this
				switch (this.shapes[i]) {
				case WALL_STRAIGHT:
					name = "WALL_STRAIGHT";
					break;
				case WALL_DIAGONALCORNER:
					name = "WALL_DIAGONALCORNER";
					break;
				case WALL_L:
					name = "WALL_L";
					break;
				case WALL_SQUARECORNER:
					name = "WALL_SQUARECORNER";
					break;
				case WALLDECOR_STRAIGHT_XOFFSET:
					name = "WALLDECOR_STRAIGHT_XOFFSET";
					break;
				case WALLDECOR_STRAIGHT_ZOFFSET:
					name = "WALLDECOR_STRAIGHT_ZOFFSET";
					break;
				case WALLDECOR_DIAGONAL_XOFFSET:
					name = "WALLDECOR_DIAGONAL_XOFFSET";
					break;
				case WALLDECOR_DIAGONAL_ZOFFSET:
					name = "WALLDECOR_DIAGONAL_ZOFFSET";
					break;
				case WALLDECOR_DIAGONAL_BOTH:
					name = "WALLDECOR_DIAGONAL_BOTH";
					break;
				case WALL_DIAGONAL:
					name = "WALL_DIAGONAL";
					break;
				default:
				case CENTREPIECE_STRAIGHT:
					name = "CENTREPIECE_STRAIGHT";
					break;
				case CENTREPIECE_DIAGONAL:
					name = "CENTREPIECE_DIAGONAL";
					break;
				case ROOF_STRAIGHT:
					name = "ROOF_STRAIGHT";
					break;
				case ROOF_DIAGONAL_WITH_ROOFEDGE:
					name = "ROOF_DIAGONAL_WITH_ROOFEDGE";
					break;
				case ROOF_DIAGONAL:
					name = "ROOF_DIAGONAL";
					break;
				case ROOF_L_CONCAVE:
					name = "ROOF_L_CONCAVE";
					break;
				case ROOF_L_CONVEX:
					name = "ROOF_L_CONVEX";
					break;
				case ROOF_FLAT:
					name = "ROOF_FLAT";
					break;
				case ROOFEDGE_STRAIGHT:
					name = "ROOFEDGE_STRAIGHT";
					break;
				case ROOFEDGE_DIAGONALCORNER:
					name = "ROOFEDGE_DIAGONALCORNER";
					break;
				case ROOFEDGE_L:
					name = "ROOFEDGE_L";
					break;
				case ROOFEDGE_SQUARECORNER:
					name = "ROOFEDGE_SQUARECORNER";
					break;
				case GROUNDDECOR:
					name = "GROUNDDECOR";
					break;
				}

				if (this.shapes[i] == CENTREPIECE_STRAIGHT) {
					// this is the default when no shape is in the definition
					builder.append("model").append(i + 1).append("=model_").append(this.models[i]).append("\n");
				} else {
					builder.append("model").append(i + 1).append("=model_").append(this.models[i]).append(",^").append(name).append("\n");
				}
			}
		}

		if (this.width != 1) {
			builder.append("width=").append(this.width).append("\n");
		}

		if (this.length != 1) {
			builder.append("length=").append(this.length).append("\n");
		}

		if (!this.blockwalk) {
			builder.append("blockwalk=no\n");
		}

		if (!this.blockrange) {
			builder.append("blockrange=no\n");
		}

		if (this.hillskew) {
			// align to terrain heightmap
			builder.append("hillskew=yes\n");
		}

		if (this.occlude) {
			builder.append("occlude=yes\n");
		}

		if (this.anim != -1) {
			builder.append("anim=seq_").append(this.anim).append("\n");
			// when anim gets serialized, opcode 22 (disposeAlpha) should follow it, so alpha data isn't shared between frames.
			// we don't need to write it here because it's implied from anim=
		}

		if (this.computeVertexColors) {
			builder.append("sharelight=yes\n");
		}

		if (this.ambient != 0) {
			builder.append("ambient=").append(this.ambient).append("\n");
		}

		if (this.contrast != 0) {
			builder.append("contrast=").append(this.contrast).append("\n");
		}

		if (this.mapfunction != -1) {
			builder.append("mapfunction=").append(this.mapfunction).append("\n");
		}

		if (this.mapscene != -1) {
			builder.append("mapscene=").append(this.mapscene).append("\n");
		}

		if (this.mirror) {
			builder.append("mirror=yes\n");
		}

		if (!this.active) {
			builder.append("active=no\n");
		}

		if (this.resizex != 128) {
			builder.append("resizex=").append(this.resizex).append("\n");
		}

		if (this.resizey != 128) {
			builder.append("resizey=").append(this.resizey).append("\n");
		}

		if (this.resizez != 128) {
			builder.append("resizez=").append(this.resizez).append("\n");
		}

		if (this.blocksides != 0) {
			builder.append("blocksides=").append(this.blocksides).append("\n");
		}

		if (this.xoff != 0) {
			builder.append("xoff=").append(this.xoff).append("\n");
		}

		if (this.yoff != 0) {
			builder.append("yoff=").append(this.yoff).append("\n");
		}

		if (this.zoff != 0) {
			builder.append("zoff=").append(this.zoff).append("\n");
		}

		if (this.forcedecor) {
			builder.append("forcedecor=yes\n");
		}

		if (this.recol_s != null) {
			for (int i = 0; i < this.recol_s.length; ++i) {
				builder.append("recol").append(i + 1).append("s=").append(this.recol_s[i]).append("\n");
				builder.append("recol").append(i + 1).append("d=").append(this.recol_d[i]).append("\n");
			}
		}

		if (this.ops != null) {
			for (int i = 0; i < this.ops.length; ++i) {
				if (this.ops[i] == null) {
					continue;
				}

				builder.append("op").append(i + 1).append("=").append(this.ops[i]).append("\n");
			}
		}

		return builder.toString();
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "I")
	private static int flowObfuscator3;

	@OriginalMember(owner = "client!ac", name = "b", descriptor = "I")
	private static int flowObfuscator1;

}
