package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.util.Cache;
import com.jagex.game.runetek3.graphics.Model;
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
	public static boolean reset;

	@OriginalMember(owner = "client!ac", name = "P", descriptor = "Lclient!s;")
	public static Cache modelCache = new Cache(500);

	@OriginalMember(owner = "client!ac", name = "Q", descriptor = "Lclient!s;")
	public static Cache modelCacheBuilt = new Cache(30);

	@OriginalMember(owner = "client!ac", name = "d", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!ac", name = "e", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!ac", name = "f", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!ac", name = "g", descriptor = "[Lclient!ac;")
	private static LocType[] cache;

	@OriginalMember(owner = "client!ac", name = "h", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!ac", name = "j", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!ac", name = "k", descriptor = "[I")
	private int[] shapes;

	@OriginalMember(owner = "client!ac", name = "l", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!ac", name = "m", descriptor = "[B")
	public byte[] desc;

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
	private boolean hillskew;

	@OriginalMember(owner = "client!ac", name = "v", descriptor = "Z")
	private boolean computeVertexColors;

	@OriginalMember(owner = "client!ac", name = "w", descriptor = "Z")
	public boolean occlude;

	@OriginalMember(owner = "client!ac", name = "x", descriptor = "I")
	public int anim;

	@OriginalMember(owner = "client!ac", name = "y", descriptor = "I")
	public int walloff;

	@OriginalMember(owner = "client!ac", name = "z", descriptor = "B")
	private byte ambient;

	@OriginalMember(owner = "client!ac", name = "A", descriptor = "B")
	private byte contrast;

	@OriginalMember(owner = "client!ac", name = "B", descriptor = "[Ljava/lang/String;")
	public String[] ops;

	@OriginalMember(owner = "client!ac", name = "C", descriptor = "Z")
	private boolean disposeAlpha;

	@OriginalMember(owner = "client!ac", name = "D", descriptor = "I")
	public int mapfunction;

	@OriginalMember(owner = "client!ac", name = "E", descriptor = "I")
	public int mapscene;

	@OriginalMember(owner = "client!ac", name = "F", descriptor = "Z")
	private boolean mirror;

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
	public int id = -1;

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(Lclient!ub;)V")
	public static void unpack(@OriginalArg(0) FileArchive archive) {
		dat = new Buffer(archive.read("loc.dat", null));
		@Pc(21) Buffer idx = new Buffer(archive.read("loc.idx", null));
		count = idx.g2();
		offsets = new int[count];

		@Pc(29) int offset = 2;
		for (@Pc(31) int i = 0; i < count; i++) {
			offsets[i] = offset;
			offset += idx.g2();
		}

		cache = new LocType[10];
		for (@Pc(51) int i = 0; i < 10; i++) {
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
		for (@Pc(1) int i = 0; i < 10; i++) {
			if (cache[i].id == id) {
				return cache[i];
			}
		}

		offset = (offset + 1) % 10;
		@Pc(27) LocType loc = cache[offset];
		dat.pos = offsets[id];
		loc.id = id;
		loc.reset();
		loc.decode(dat);
		return loc;
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "()V")
	public void reset() {
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
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer dat) {
		@Pc(5) int interactive = -1;

		while (true) {
			@Pc(15) int opcode = dat.g1();
			if (opcode == 0) {
				break;
			}

			if (opcode == 1) {
				@Pc(23) int count = dat.g1();
				this.shapes = new int[count];
				this.models = new int[count];
				for (@Pc(33) int i = 0; i < count; i++) {
					this.models[i] = dat.g2();
					this.shapes[i] = dat.g1();
				}
			} else if (opcode == 2) {
				this.name = dat.gstr();
			} else if (opcode == 3) {
				this.desc = dat.gstrbyte();
			} else if (opcode == 14) {
				this.width = dat.g1();
			} else if (opcode == 15) {
				this.length = dat.g1();
			} else if (opcode == 17) {
				this.blockwalk = false;
			} else if (opcode == 18) {
				this.blockrange = false;
			} else if (opcode == 19) {
				interactive = dat.g1();
				if (interactive == 1) {
					this.interactable = true;
				}
			} else if (opcode == 21) {
				this.hillskew = true;
			} else if (opcode == 22) {
				this.computeVertexColors = true;
			} else if (opcode == 23) {
				this.occlude = true;
			} else if (opcode == 24) {
				this.anim = dat.g2();
				if (this.anim == 65535) {
					this.anim = -1;
				}
			} else if (opcode == 25) {
				this.disposeAlpha = true;
			} else if (opcode == 28) {
				this.walloff = dat.g1();
			} else if (opcode == 29) {
				this.ambient = dat.g1b();
			} else if (opcode == 39) {
				this.contrast = dat.g1b();
			} else if (opcode >= 30 && opcode < 39) {
				if (this.ops == null) {
					this.ops = new String[5];
				}

				this.ops[opcode - 30] = dat.gstr();
				if (this.ops[opcode - 30].equalsIgnoreCase("hidden")) {
					this.ops[opcode - 30] = null;
				}
			} else if (opcode == 40) {
				int count = dat.g1();
				this.recol_s = new int[count];
				this.recol_d = new int[count];
				for (int i = 0; i < count; i++) {
					this.recol_s[i] = dat.g2();
					this.recol_d[i] = dat.g2();
				}
			} else if (opcode == 60) {
				this.mapfunction = dat.g2();
			} else if (opcode == 62) {
				this.mirror = true;
			} else if (opcode == 64) {
				this.active = false;
			} else if (opcode == 65) {
				this.resizex = dat.g2();
			} else if (opcode == 66) {
				this.resizey = dat.g2();
			} else if (opcode == 67) {
				this.resizez = dat.g2();
			} else if (opcode == 68) {
				this.mapscene = dat.g2();
			} else if (opcode == 69) {
				this.blocksides = dat.g1();
			} else if (opcode == 70) {
				this.xoff = dat.g2b();
			} else if (opcode == 71) {
				this.yoff = dat.g2b();
			} else if (opcode == 72) {
				this.zoff = dat.g2b();
			} else if (opcode == 73) {
				this.forcedecor = true;
			}
		}

		if (this.shapes == null) {
			this.shapes = new int[0];
		}

		if (interactive == -1) {
			this.interactable = false;
			if (this.shapes.length > 0 && this.shapes[0] == 10) {
				this.interactable = true;
			}

			if (this.ops != null) {
				this.interactable = true;
			}
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(IIIIIII)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int type, @OriginalArg(1) int orientation, @OriginalArg(2) int swY, @OriginalArg(3) int seY, @OriginalArg(4) int nwY, @OriginalArg(5) int neY, @OriginalArg(6) int seqFrame) {
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

		@Pc(47) long key = ((long) this.id << 6) + ((long) modelType << 3) + orientation + ((long) (seqFrame + 1) << 32);
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

			@Pc(200) Model m2 = (Model) modelCache.get(modelIndex);
			if (m2 == null) {
				m2 = new Model(false, modelIndex & 0xFFFF);
				if (flipBackwards) {
					m2.flipBackwards();
				}
				modelCache.put(modelIndex, m2);
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

			@Pc(284) Model m3 = new Model(m2, this.recol_s == null, !this.disposeAlpha, orientation == 0 && seqFrame == -1 && !rescale && !move);
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
				m3.collisionPoint = m3.maxBoundY;
			}
			modelCacheBuilt.put(key, m3);
			if (this.hillskew || this.computeVertexColors) {
				m3 = new Model(m3, this.hillskew, this.computeVertexColors);
			}

			if (this.hillskew) {
				n = (swY + seY + nwY + neY) / 4;
				for (@Pc(417) int v = 0; v < m3.vertexCount; v++) {
					@Pc(424) int x = m3.vertexX[v];
					@Pc(429) int z = m3.vertexZ[v];
					@Pc(441) int averageY1 = swY + (seY - swY) * (x + 64) / 128;
					@Pc(453) int averageY2 = neY + (nwY - neY) * (x + 64) / 128;
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
				modelIndex = (swY + seY + nwY + neY) / 4;
				for (@Pc(93) int v = 0; v < m.vertexCount; v++) {
					@Pc(100) int x = m.vertexX[v];
					@Pc(105) int z = m.vertexZ[v];
					@Pc(117) int averageY1 = swY + (seY - swY) * (x + 64) / 128;
					@Pc(129) int averageY2 = neY + (nwY - neY) * (x + 64) / 128;
					n = averageY1 + (averageY2 - averageY1) * (z + 64) / 128;
					m.vertexY[v] += n - modelIndex;
				}
				m.calculateYBoundaries2();
			}

			return m;
		}
	}
}
