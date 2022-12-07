import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ac")
public final class LocType {

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
	public static void unpack(@OriginalArg(0) FileArchive arg0) {
		dat = new Buffer(arg0.read("loc.dat", null));
		@Pc(21) Buffer local21 = new Buffer(arg0.read("loc.idx", null));
		count = local21.g2();
		offsets = new int[count];
		@Pc(29) int local29 = 2;
		for (@Pc(31) int local31 = 0; local31 < count; local31++) {
			offsets[local31] = local29;
			local29 += local21.g2();
		}
		cache = new LocType[10];
		for (@Pc(51) int local51 = 0; local51 < 10; local51++) {
			cache[local51] = new LocType();
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
	public static LocType get(@OriginalArg(0) int arg0) {
		for (@Pc(1) int local1 = 0; local1 < 10; local1++) {
			if (cache[local1].id == arg0) {
				return cache[local1];
			}
		}
		offset = (offset + 1) % 10;
		@Pc(27) LocType local27 = cache[offset];
		dat.pos = offsets[arg0];
		local27.id = arg0;
		local27.reset();
		local27.decode(dat);
		return local27;
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
	public void decode(@OriginalArg(1) Buffer arg1) {
		@Pc(5) int local5 = -1;

		while (true) {
			@Pc(15) int local15 = arg1.g1();
			if (local15 == 0) {
				if (this.shapes == null) {
					this.shapes = new int[0];
				}
				if (local5 == -1) {
					this.interactable = false;
					if (this.shapes.length > 0 && this.shapes[0] == 10) {
						this.interactable = true;
					}
					if (this.ops != null) {
						this.interactable = true;
						return;
					}
				}
				return;
			}
			@Pc(23) int local23;
			@Pc(33) int local33;
			if (local15 == 1) {
				local23 = arg1.g1();
				this.shapes = new int[local23];
				this.models = new int[local23];
				for (local33 = 0; local33 < local23; local33++) {
					this.models[local33] = arg1.g2();
					this.shapes[local33] = arg1.g1();
				}
			} else if (local15 == 2) {
				this.name = arg1.gstr();
			} else if (local15 == 3) {
				this.desc = arg1.gstrbyte();
			} else if (local15 == 14) {
				this.width = arg1.g1();
			} else if (local15 == 15) {
				this.length = arg1.g1();
			} else if (local15 == 17) {
				this.blockwalk = false;
			} else if (local15 == 18) {
				this.blockrange = false;
			} else if (local15 == 19) {
				local5 = arg1.g1();
				if (local5 == 1) {
					this.interactable = true;
				}
			} else if (local15 == 21) {
				this.hillskew = true;
			} else if (local15 == 22) {
				this.computeVertexColors = true;
			} else if (local15 == 23) {
				this.occlude = true;
			} else if (local15 == 24) {
				this.anim = arg1.g2();
				if (this.anim == 65535) {
					this.anim = -1;
				}
			} else if (local15 == 25) {
				this.disposeAlpha = true;
			} else if (local15 == 28) {
				this.walloff = arg1.g1();
			} else if (local15 == 29) {
				this.ambient = arg1.g1b();
			} else if (local15 == 39) {
				this.contrast = arg1.g1b();
			} else if (local15 >= 30 && local15 < 39) {
				if (this.ops == null) {
					this.ops = new String[5];
				}
				this.ops[local15 - 30] = arg1.gstr();
				if (this.ops[local15 - 30].equalsIgnoreCase("hidden")) {
					this.ops[local15 - 30] = null;
				}
			} else if (local15 == 40) {
				local23 = arg1.g1();
				this.recol_s = new int[local23];
				this.recol_d = new int[local23];
				for (local33 = 0; local33 < local23; local33++) {
					this.recol_s[local33] = arg1.g2();
					this.recol_d[local33] = arg1.g2();
				}
			} else if (local15 == 60) {
				this.mapfunction = arg1.g2();
			} else if (local15 == 62) {
				this.mirror = true;
			} else if (local15 == 64) {
				this.active = false;
			} else if (local15 == 65) {
				this.resizex = arg1.g2();
			} else if (local15 == 66) {
				this.resizey = arg1.g2();
			} else if (local15 == 67) {
				this.resizez = arg1.g2();
			} else if (local15 == 68) {
				this.mapscene = arg1.g2();
			} else if (local15 == 69) {
				this.blocksides = arg1.g1();
			} else if (local15 == 70) {
				this.xoff = arg1.g2b();
			} else if (local15 == 71) {
				this.yoff = arg1.g2b();
			} else if (local15 == 72) {
				this.zoff = arg1.g2b();
			} else if (local15 == 73) {
				this.forcedecor = true;
			}
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(IIIIIII)Lclient!eb;")
	public Model getModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(3) int local3 = -1;
		for (@Pc(5) int local5 = 0; local5 < this.shapes.length; local5++) {
			if (this.shapes[local5] == arg0) {
				local3 = local5;
				break;
			}
		}
		if (local3 == -1) {
			return null;
		}
		@Pc(47) long local47 = (long) (((long) this.id << 6) + ((long) local3 << 3) + arg1) + ((long) (arg6 + 1) << 32);
		if (reset) {
			local47 = 0L;
		}
		@Pc(56) Model local56 = (Model) modelCacheBuilt.get(local47);
		@Pc(91) int local91;
		@Pc(141) int local141;
		if (local56 == null) {
			if (local3 >= this.models.length) {
				return null;
			}
			local91 = this.models[local3];
			if (local91 == -1) {
				return null;
			}
			@Pc(188) boolean local188 = this.mirror ^ arg1 > 3;
			if (local188) {
				local91 += 65536;
			}
			@Pc(200) Model local200 = (Model) modelCache.get(local91);
			if (local200 == null) {
				local200 = new Model(false, local91 & 0xFFFF);
				if (local188) {
					local200.flipBackwards();
				}
				modelCache.put(local91, local200);
			}
			@Pc(235) boolean local235;
			if (this.resizex == 128 && this.resizey == 128 && this.resizez == 128) {
				local235 = false;
			} else {
				local235 = true;
			}
			@Pc(250) boolean local250;
			if (this.xoff == 0 && this.yoff == 0 && this.zoff == 0) {
				local250 = false;
			} else {
				local250 = true;
			}
			@Pc(284) Model local284 = new Model(local200, this.recol_s == null, !this.disposeAlpha, arg1 == 0 && arg6 == -1 && !local235 && !local250);
			if (arg6 != -1) {
				local284.applyGroup();
				local284.applyFrame(arg6);
				local284.skinTriangle = null;
				local284.labelVertices = null;
			}
			while (arg1-- > 0) {
				local284.rotateCounterClockwise();
			}
			if (this.recol_s != null) {
				for (local141 = 0; local141 < this.recol_s.length; local141++) {
					local284.recolor(this.recol_s[local141], this.recol_d[local141]);
				}
			}
			if (local235) {
				local284.scale(this.resizez, this.resizey, this.resizex);
			}
			if (local250) {
				local284.translate(this.yoff, this.xoff, this.zoff);
			}
			local284.applyLighting(this.ambient + 64, this.contrast * 5 + 768, -50, -10, -50, !this.computeVertexColors);
			if (this.blockwalk) {
				local284.collisionPoint = local284.maxBoundY;
			}
			modelCacheBuilt.put(local47, local284);
			if (this.hillskew || this.computeVertexColors) {
				local284 = new Model(local284, this.hillskew, this.computeVertexColors);
			}
			if (this.hillskew) {
				local141 = (arg2 + arg3 + arg4 + arg5) / 4;
				for (@Pc(417) int local417 = 0; local417 < local284.vertexCount; local417++) {
					@Pc(424) int local424 = local284.vertexX[local417];
					@Pc(429) int local429 = local284.vertexZ[local417];
					@Pc(441) int local441 = arg2 + (arg3 - arg2) * (local424 + 64) / 128;
					@Pc(453) int local453 = arg5 + (arg4 - arg5) * (local424 + 64) / 128;
					@Pc(465) int local465 = local441 + (local453 - local441) * (local429 + 64) / 128;
					local284.vertexY[local417] += local465 - local141;
				}
				local284.calculateYBoundaries2();
			}
			return local284;
		} else if (reset) {
			return local56;
		} else {
			if (this.hillskew || this.computeVertexColors) {
				local56 = new Model(local56, this.hillskew, this.computeVertexColors);
			}
			if (this.hillskew) {
				local91 = (arg2 + arg3 + arg4 + arg5) / 4;
				for (@Pc(93) int local93 = 0; local93 < local56.vertexCount; local93++) {
					@Pc(100) int local100 = local56.vertexX[local93];
					@Pc(105) int local105 = local56.vertexZ[local93];
					@Pc(117) int local117 = arg2 + (arg3 - arg2) * (local100 + 64) / 128;
					@Pc(129) int local129 = arg5 + (arg4 - arg5) * (local100 + 64) / 128;
					local141 = local117 + (local129 - local117) * (local105 + 64) / 128;
					local56.vertexY[local93] += local141 - local91;
				}
				local56.calculateYBoundaries2();
			}
			return local56;
		}
	}
}
