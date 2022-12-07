import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!eb")
public final class Model extends CacheableNode {

	@OriginalMember(owner = "client!eb", name = "Cb", descriptor = "[I")
	public static final int[] tmpX = new int[10];

	@OriginalMember(owner = "client!eb", name = "Db", descriptor = "[I")
	public static final int[] tmpY = new int[10];

	@OriginalMember(owner = "client!eb", name = "Eb", descriptor = "[I")
	public static final int[] tmpZ = new int[10];

	@OriginalMember(owner = "client!eb", name = "Mb", descriptor = "[I")
	public static final int[] hoveredBitsets = new int[1000];

	@OriginalMember(owner = "client!eb", name = "Y", descriptor = "[Lclient!l;")
	public static Metadata[] metadata;

	@OriginalMember(owner = "client!eb", name = "ab", descriptor = "Lclient!kb;")
	public static Buffer face1;

	@OriginalMember(owner = "client!eb", name = "bb", descriptor = "Lclient!kb;")
	public static Buffer face2;

	@OriginalMember(owner = "client!eb", name = "cb", descriptor = "Lclient!kb;")
	public static Buffer face3;

	@OriginalMember(owner = "client!eb", name = "db", descriptor = "Lclient!kb;")
	public static Buffer face4;

	@OriginalMember(owner = "client!eb", name = "eb", descriptor = "Lclient!kb;")
	public static Buffer face5;

	@OriginalMember(owner = "client!eb", name = "fb", descriptor = "Lclient!kb;")
	public static Buffer point1;

	@OriginalMember(owner = "client!eb", name = "gb", descriptor = "Lclient!kb;")
	public static Buffer point2;

	@OriginalMember(owner = "client!eb", name = "hb", descriptor = "Lclient!kb;")
	public static Buffer point3;

	@OriginalMember(owner = "client!eb", name = "ib", descriptor = "Lclient!kb;")
	public static Buffer point4;

	@OriginalMember(owner = "client!eb", name = "jb", descriptor = "Lclient!kb;")
	public static Buffer point5;

	@OriginalMember(owner = "client!eb", name = "kb", descriptor = "Lclient!kb;")
	public static Buffer vertex1;

	@OriginalMember(owner = "client!eb", name = "lb", descriptor = "Lclient!kb;")
	public static Buffer vertex2;

	@OriginalMember(owner = "client!eb", name = "mb", descriptor = "Lclient!kb;")
	public static Buffer axis;

	@OriginalMember(owner = "client!eb", name = "Fb", descriptor = "I")
	public static int transformX;

	@OriginalMember(owner = "client!eb", name = "Gb", descriptor = "I")
	public static int transformY;

	@OriginalMember(owner = "client!eb", name = "Hb", descriptor = "I")
	public static int transformZ;

	@OriginalMember(owner = "client!eb", name = "Ib", descriptor = "Z")
	public static boolean allowInput;

	@OriginalMember(owner = "client!eb", name = "Jb", descriptor = "I")
	public static int cursorX;

	@OriginalMember(owner = "client!eb", name = "Kb", descriptor = "I")
	public static int cursorY;

	@OriginalMember(owner = "client!eb", name = "Lb", descriptor = "I")
	public static int resourceCount;

	@OriginalMember(owner = "client!eb", name = "nb", descriptor = "[Z")
	public static boolean[] testTriangleX = new boolean[4096];

	@OriginalMember(owner = "client!eb", name = "ob", descriptor = "[Z")
	public static boolean[] projectTriangle = new boolean[4096];

	@OriginalMember(owner = "client!eb", name = "pb", descriptor = "[I")
	public static int[] vertexScreenX = new int[4096];

	@OriginalMember(owner = "client!eb", name = "qb", descriptor = "[I")
	public static int[] vertexScreenY = new int[4096];

	@OriginalMember(owner = "client!eb", name = "rb", descriptor = "[I")
	public static int[] vertexDepth = new int[4096];

	@OriginalMember(owner = "client!eb", name = "sb", descriptor = "[I")
	public static int[] projectSceneX = new int[4096];

	@OriginalMember(owner = "client!eb", name = "tb", descriptor = "[I")
	public static int[] projectSceneY = new int[4096];

	@OriginalMember(owner = "client!eb", name = "ub", descriptor = "[I")
	public static int[] projectSceneZ = new int[4096];

	@OriginalMember(owner = "client!eb", name = "vb", descriptor = "[I")
	public static int[] depthTriangleCount = new int[1500];

	@OriginalMember(owner = "client!eb", name = "wb", descriptor = "[[I")
	public static int[][] depthTriangles = new int[1500][512];

	@OriginalMember(owner = "client!eb", name = "xb", descriptor = "[I")
	public static int[] priorityTriangleCounts = new int[12];

	@OriginalMember(owner = "client!eb", name = "yb", descriptor = "[[I")
	public static int[][] priorityTriangles = new int[12][2000];

	@OriginalMember(owner = "client!eb", name = "zb", descriptor = "[I")
	public static int[] normalTrianglePriority = new int[2000];

	@OriginalMember(owner = "client!eb", name = "Ab", descriptor = "[I")
	public static int[] highTrianglePriority = new int[2000];

	@OriginalMember(owner = "client!eb", name = "Bb", descriptor = "[I")
	public static int[] lowTrianglePriority = new int[12];

	@OriginalMember(owner = "client!eb", name = "Nb", descriptor = "[I")
	public static int[] sin = Draw3D.sin;

	@OriginalMember(owner = "client!eb", name = "Ob", descriptor = "[I")
	public static int[] cos = Draw3D.cos;

	@OriginalMember(owner = "client!eb", name = "Pb", descriptor = "[I")
	public static int[] palette = Draw3D.palette;

	@OriginalMember(owner = "client!eb", name = "Qb", descriptor = "[I")
	public static int[] reciprical16 = Draw3D.reciprical16;

	@OriginalMember(owner = "client!eb", name = "Z", descriptor = "Lclient!kb;")
	private static Buffer head;

	@OriginalMember(owner = "client!eb", name = "v", descriptor = "[I")
	private int[] colorA;

	@OriginalMember(owner = "client!eb", name = "w", descriptor = "[I")
	private int[] colorB;

	@OriginalMember(owner = "client!eb", name = "x", descriptor = "[I")
	private int[] colorC;

	@OriginalMember(owner = "client!eb", name = "H", descriptor = "I")
	public int minBoundX;

	@OriginalMember(owner = "client!eb", name = "I", descriptor = "I")
	public int maxBoundX;

	@OriginalMember(owner = "client!eb", name = "J", descriptor = "I")
	public int maxBoundZ;

	@OriginalMember(owner = "client!eb", name = "K", descriptor = "I")
	public int minBoundZ;

	@OriginalMember(owner = "client!eb", name = "L", descriptor = "I")
	public int lengthXZ;

	@OriginalMember(owner = "client!eb", name = "M", descriptor = "I")
	public int maxBoundY;

	@OriginalMember(owner = "client!eb", name = "N", descriptor = "I")
	public int minBoundY;

	@OriginalMember(owner = "client!eb", name = "O", descriptor = "I")
	private int maxDepth;

	@OriginalMember(owner = "client!eb", name = "P", descriptor = "I")
	private int minDepth;

	@OriginalMember(owner = "client!eb", name = "Q", descriptor = "I")
	public int collisionPoint;

	@OriginalMember(owner = "client!eb", name = "T", descriptor = "[[I")
	public int[][] labelVertices;

	@OriginalMember(owner = "client!eb", name = "U", descriptor = "[[I")
	public int[][] skinTriangle;

	@OriginalMember(owner = "client!eb", name = "W", descriptor = "[Lclient!n;")
	public VertexNormal[] vertexNormals;

	@OriginalMember(owner = "client!eb", name = "X", descriptor = "[Lclient!n;")
	public VertexNormal[] vertexNormalOriginal;

	@OriginalMember(owner = "client!eb", name = "V", descriptor = "Z")
	public boolean pickable = false;

	@OriginalMember(owner = "client!eb", name = "n", descriptor = "I")
	public int vertexCount;

	@OriginalMember(owner = "client!eb", name = "r", descriptor = "I")
	public int triangleCount;

	@OriginalMember(owner = "client!eb", name = "D", descriptor = "I")
	private int texturedCount;

	@OriginalMember(owner = "client!eb", name = "o", descriptor = "[I")
	public int[] vertexX;

	@OriginalMember(owner = "client!eb", name = "p", descriptor = "[I")
	public int[] vertexY;

	@OriginalMember(owner = "client!eb", name = "q", descriptor = "[I")
	public int[] vertexZ;

	@OriginalMember(owner = "client!eb", name = "s", descriptor = "[I")
	public int[] triangleVertexA;

	@OriginalMember(owner = "client!eb", name = "t", descriptor = "[I")
	public int[] triangleVertexB;

	@OriginalMember(owner = "client!eb", name = "u", descriptor = "[I")
	public int[] triangleVertexC;

	@OriginalMember(owner = "client!eb", name = "E", descriptor = "[I")
	private int[] textureVertexA;

	@OriginalMember(owner = "client!eb", name = "F", descriptor = "[I")
	private int[] textureVertexB;

	@OriginalMember(owner = "client!eb", name = "G", descriptor = "[I")
	private int[] textureVertexC;

	@OriginalMember(owner = "client!eb", name = "R", descriptor = "[I")
	private int[] vertexLabel;

	@OriginalMember(owner = "client!eb", name = "y", descriptor = "[I")
	public int[] triangleInfo;

	@OriginalMember(owner = "client!eb", name = "z", descriptor = "[I")
	private int[] trianglePriorities;

	@OriginalMember(owner = "client!eb", name = "C", descriptor = "I")
	private int priority;

	@OriginalMember(owner = "client!eb", name = "A", descriptor = "[I")
	private int[] triangleAlpha;

	@OriginalMember(owner = "client!eb", name = "S", descriptor = "[I")
	private int[] triangleSkin;

	@OriginalMember(owner = "client!eb", name = "B", descriptor = "[I")
	public int[] unmodifiedTriangleColor;

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "(ZI)V")
	public Model(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1) {
		if (metadata != null) {
			@Pc(28) Metadata local28 = metadata[arg1];
			if (local28 == null) {
				System.out.println("Error model:" + arg1 + " not found!");
			} else {
				this.vertexCount = local28.vertexCount;
				this.triangleCount = local28.triangleCount;
				this.texturedCount = local28.texturedCount;
				this.vertexX = new int[this.vertexCount];
				this.vertexY = new int[this.vertexCount];
				this.vertexZ = new int[this.vertexCount];
				this.triangleVertexA = new int[this.triangleCount];
				this.triangleVertexB = new int[this.triangleCount];
				this.triangleVertexC = new int[this.triangleCount];
				this.textureVertexA = new int[this.texturedCount];
				this.textureVertexB = new int[this.texturedCount];
				this.textureVertexC = new int[this.texturedCount];
				if (local28.vertexLabelDataOffset >= 0) {
					this.vertexLabel = new int[this.vertexCount];
				}
				if (local28.triangleInfoDataOffset >= 0) {
					this.triangleInfo = new int[this.triangleCount];
				}
				if (local28.trianglePriorityDataOffset >= 0) {
					this.trianglePriorities = new int[this.triangleCount];
				} else {
					this.priority = -local28.trianglePriorityDataOffset - 1;
				}
				if (local28.triangleAlphaDataOffset >= 0) {
					this.triangleAlpha = new int[this.triangleCount];
				}
				if (local28.triangleSkinDataOffset >= 0) {
					this.triangleSkin = new int[this.triangleCount];
				}
				this.unmodifiedTriangleColor = new int[this.triangleCount];
				point1.pos = local28.vertexFlagDataOffset;
				point2.pos = local28.vertexXDataOffset;
				point3.pos = local28.vertexYDataOffset;
				point4.pos = local28.vertexZDataOffset;
				point5.pos = local28.vertexLabelDataOffset;
				@Pc(175) int local175 = 0;
				@Pc(177) int local177 = 0;
				@Pc(179) int local179 = 0;
				@Pc(186) int local186;
				@Pc(188) int local188;
				@Pc(197) int local197;
				@Pc(206) int local206;
				for (@Pc(181) int local181 = 0; local181 < this.vertexCount; local181++) {
					local186 = point1.g1();
					local188 = 0;
					if ((local186 & 0x1) != 0) {
						local188 = point2.gsmart();
					}
					local197 = 0;
					if ((local186 & 0x2) != 0) {
						local197 = point3.gsmart();
					}
					local206 = 0;
					if ((local186 & 0x4) != 0) {
						local206 = point4.gsmart();
					}
					this.vertexX[local181] = local175 + local188;
					this.vertexY[local181] = local177 + local197;
					this.vertexZ[local181] = local179 + local206;
					local175 = this.vertexX[local181];
					local177 = this.vertexY[local181];
					local179 = this.vertexZ[local181];
					if (this.vertexLabel != null) {
						this.vertexLabel[local181] = point5.g1();
					}
				}
				face1.pos = local28.triangleColorDataOffset;
				face2.pos = local28.triangleInfoDataOffset;
				face3.pos = local28.trianglePriorityDataOffset;
				face4.pos = local28.triangleAlphaDataOffset;
				face5.pos = local28.triangleSkinDataOffset;
				for (local186 = 0; local186 < this.triangleCount; local186++) {
					this.unmodifiedTriangleColor[local186] = face1.g2();
					if (this.triangleInfo != null) {
						this.triangleInfo[local186] = face2.g1();
					}
					if (this.trianglePriorities != null) {
						this.trianglePriorities[local186] = face3.g1();
					}
					if (this.triangleAlpha != null) {
						this.triangleAlpha[local186] = face4.g1();
					}
					if (this.triangleSkin != null) {
						this.triangleSkin[local186] = face5.g1();
					}
				}
				vertex1.pos = local28.vertexIndexDataOffset;
				vertex2.pos = local28.triangleTypeDataOffset;
				local188 = 0;
				local197 = 0;
				local206 = 0;
				@Pc(350) int local350 = 0;
				@Pc(357) int local357;
				for (@Pc(352) int local352 = 0; local352 < this.triangleCount; local352++) {
					local357 = vertex2.g1();
					if (local357 == 1) {
						local188 = vertex1.gsmart() + local350;
						local197 = vertex1.gsmart() + local188;
						local206 = vertex1.gsmart() + local197;
						local350 = local206;
						this.triangleVertexA[local352] = local188;
						this.triangleVertexB[local352] = local197;
						this.triangleVertexC[local352] = local206;
					}
					if (local357 == 2) {
						local188 = local188;
						local197 = local206;
						local206 = vertex1.gsmart() + local350;
						local350 = local206;
						this.triangleVertexA[local352] = local188;
						this.triangleVertexB[local352] = local197;
						this.triangleVertexC[local352] = local206;
					}
					if (local357 == 3) {
						local188 = local206;
						local197 = local197;
						local206 = vertex1.gsmart() + local350;
						local350 = local206;
						this.triangleVertexA[local352] = local188;
						this.triangleVertexB[local352] = local197;
						this.triangleVertexC[local352] = local206;
					}
					if (local357 == 4) {
						@Pc(459) int local459 = local188;
						local188 = local197;
						local197 = local459;
						local206 = vertex1.gsmart() + local350;
						local350 = local206;
						this.triangleVertexA[local352] = local188;
						this.triangleVertexB[local352] = local459;
						this.triangleVertexC[local352] = local206;
					}
				}
				if (arg0) {
					throw new NullPointerException();
				}
				axis.pos = local28.triangleTextureDataOffset * 6;
				for (local357 = 0; local357 < this.texturedCount; local357++) {
					this.textureVertexA[local357] = axis.g2();
					this.textureVertexB[local357] = axis.g2();
					this.textureVertexC[local357] = axis.g2();
				}
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "(I[Lclient!eb;I)V")
	public Model(@OriginalArg(1) Model[] arg1, @OriginalArg(2) int arg2) {
		@Pc(23) boolean local23 = false;
		@Pc(25) boolean local25 = false;
		@Pc(27) boolean local27 = false;
		@Pc(29) boolean local29 = false;
		this.vertexCount = 0;
		this.triangleCount = 0;
		this.texturedCount = 0;
		this.priority = -1;
		for (@Pc(43) int local43 = 0; local43 < arg2; local43++) {
			@Pc(49) Model local49 = arg1[local43];
			if (local49 != null) {
				this.vertexCount += local49.vertexCount;
				this.triangleCount += local49.triangleCount;
				this.texturedCount += local49.texturedCount;
				local23 |= local49.triangleInfo != null;
				if (local49.trianglePriorities == null) {
					if (this.priority == -1) {
						this.priority = local49.priority;
					}
					if (this.priority != local49.priority) {
						local25 = true;
					}
				} else {
					local25 = true;
				}
				local27 |= local49.triangleAlpha != null;
				local29 |= local49.triangleSkin != null;
			}
		}
		this.vertexX = new int[this.vertexCount];
		this.vertexY = new int[this.vertexCount];
		this.vertexZ = new int[this.vertexCount];
		this.vertexLabel = new int[this.vertexCount];
		this.triangleVertexA = new int[this.triangleCount];
		this.triangleVertexB = new int[this.triangleCount];
		this.triangleVertexC = new int[this.triangleCount];
		this.textureVertexA = new int[this.texturedCount];
		this.textureVertexB = new int[this.texturedCount];
		this.textureVertexC = new int[this.texturedCount];
		if (local23) {
			this.triangleInfo = new int[this.triangleCount];
		}
		if (local25) {
			this.trianglePriorities = new int[this.triangleCount];
		}
		if (local27) {
			this.triangleAlpha = new int[this.triangleCount];
		}
		if (local29) {
			this.triangleSkin = new int[this.triangleCount];
		}
		this.unmodifiedTriangleColor = new int[this.triangleCount];
		this.vertexCount = 0;
		this.triangleCount = 0;
		this.texturedCount = 0;
		for (@Pc(225) int local225 = 0; local225 < arg2; local225++) {
			@Pc(231) Model local231 = arg1[local225];
			if (local231 != null) {
				for (@Pc(235) int local235 = 0; local235 < local231.triangleCount; local235++) {
					if (local23) {
						if (local231.triangleInfo == null) {
							this.triangleInfo[this.triangleCount] = 0;
						} else {
							this.triangleInfo[this.triangleCount] = local231.triangleInfo[local235];
						}
					}
					if (local25) {
						if (local231.trianglePriorities == null) {
							this.trianglePriorities[this.triangleCount] = local231.priority;
						} else {
							this.trianglePriorities[this.triangleCount] = local231.trianglePriorities[local235];
						}
					}
					if (local27) {
						if (local231.triangleAlpha == null) {
							this.triangleAlpha[this.triangleCount] = 0;
						} else {
							this.triangleAlpha[this.triangleCount] = local231.triangleAlpha[local235];
						}
					}
					if (local29 && local231.triangleSkin != null) {
						this.triangleSkin[this.triangleCount] = local231.triangleSkin[local235];
					}
					this.unmodifiedTriangleColor[this.triangleCount] = local231.unmodifiedTriangleColor[local235];
					this.triangleVertexA[this.triangleCount] = this.copyVertex(local231, local231.triangleVertexA[local235]);
					this.triangleVertexB[this.triangleCount] = this.copyVertex(local231, local231.triangleVertexB[local235]);
					this.triangleVertexC[this.triangleCount] = this.copyVertex(local231, local231.triangleVertexC[local235]);
					this.triangleCount++;
				}
				for (@Pc(376) int local376 = 0; local376 < local231.texturedCount; local376++) {
					this.textureVertexA[this.texturedCount] = this.copyVertex(local231, local231.textureVertexA[local376]);
					this.textureVertexB[this.texturedCount] = this.copyVertex(local231, local231.textureVertexB[local376]);
					this.textureVertexC[this.texturedCount] = this.copyVertex(local231, local231.textureVertexC[local376]);
					this.texturedCount++;
				}
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "([Lclient!eb;BIZ)V")
	public Model(@OriginalArg(0) Model[] arg0, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3) {
		@Pc(23) boolean local23 = false;
		@Pc(25) boolean local25 = false;
		@Pc(27) boolean local27 = false;
		@Pc(29) boolean local29 = false;
		this.vertexCount = 0;
		this.triangleCount = 0;
		this.texturedCount = 0;
		this.priority = -1;
		for (@Pc(43) int local43 = 0; local43 < arg2; local43++) {
			@Pc(49) Model local49 = arg0[local43];
			if (local49 != null) {
				this.vertexCount += local49.vertexCount;
				this.triangleCount += local49.triangleCount;
				this.texturedCount += local49.texturedCount;
				local23 |= local49.triangleInfo != null;
				if (local49.trianglePriorities == null) {
					if (this.priority == -1) {
						this.priority = local49.priority;
					}
					if (this.priority != local49.priority) {
						local25 = true;
					}
				} else {
					local25 = true;
				}
				local27 |= local49.triangleAlpha != null;
				local29 |= local49.unmodifiedTriangleColor != null;
			}
		}
		this.vertexX = new int[this.vertexCount];
		this.vertexY = new int[this.vertexCount];
		this.vertexZ = new int[this.vertexCount];
		this.triangleVertexA = new int[this.triangleCount];
		this.triangleVertexB = new int[this.triangleCount];
		this.triangleVertexC = new int[this.triangleCount];
		this.colorA = new int[this.triangleCount];
		this.colorB = new int[this.triangleCount];
		this.colorC = new int[this.triangleCount];
		this.textureVertexA = new int[this.texturedCount];
		this.textureVertexB = new int[this.texturedCount];
		this.textureVertexC = new int[this.texturedCount];
		if (local23) {
			this.triangleInfo = new int[this.triangleCount];
		}
		if (local25) {
			this.trianglePriorities = new int[this.triangleCount];
		}
		if (local27) {
			this.triangleAlpha = new int[this.triangleCount];
		}
		if (local29) {
			this.unmodifiedTriangleColor = new int[this.triangleCount];
		}
		this.vertexCount = 0;
		this.triangleCount = 0;
		this.texturedCount = 0;
		@Pc(227) int local227;
		for (local227 = 0; local227 < arg2; local227++) {
			@Pc(240) Model local240 = arg0[local227];
			if (local240 != null) {
				@Pc(245) int local245 = this.vertexCount;
				for (@Pc(247) int local247 = 0; local247 < local240.vertexCount; local247++) {
					this.vertexX[this.vertexCount] = local240.vertexX[local247];
					this.vertexY[this.vertexCount] = local240.vertexY[local247];
					this.vertexZ[this.vertexCount] = local240.vertexZ[local247];
					this.vertexCount++;
				}
				for (@Pc(289) int local289 = 0; local289 < local240.triangleCount; local289++) {
					this.triangleVertexA[this.triangleCount] = local240.triangleVertexA[local289] + local245;
					this.triangleVertexB[this.triangleCount] = local240.triangleVertexB[local289] + local245;
					this.triangleVertexC[this.triangleCount] = local240.triangleVertexC[local289] + local245;
					this.colorA[this.triangleCount] = local240.colorA[local289];
					this.colorB[this.triangleCount] = local240.colorB[local289];
					this.colorC[this.triangleCount] = local240.colorC[local289];
					if (local23) {
						if (local240.triangleInfo == null) {
							this.triangleInfo[this.triangleCount] = 0;
						} else {
							this.triangleInfo[this.triangleCount] = local240.triangleInfo[local289];
						}
					}
					if (local25) {
						if (local240.trianglePriorities == null) {
							this.trianglePriorities[this.triangleCount] = local240.priority;
						} else {
							this.trianglePriorities[this.triangleCount] = local240.trianglePriorities[local289];
						}
					}
					if (local27) {
						if (local240.triangleAlpha == null) {
							this.triangleAlpha[this.triangleCount] = 0;
						} else {
							this.triangleAlpha[this.triangleCount] = local240.triangleAlpha[local289];
						}
					}
					if (local29 && local240.unmodifiedTriangleColor != null) {
						this.unmodifiedTriangleColor[this.triangleCount] = local240.unmodifiedTriangleColor[local289];
					}
					this.triangleCount++;
				}
				for (@Pc(445) int local445 = 0; local445 < local240.texturedCount; local445++) {
					this.textureVertexA[this.texturedCount] = local240.textureVertexA[local445] + local245;
					this.textureVertexB[this.texturedCount] = local240.textureVertexB[local445] + local245;
					this.textureVertexC[this.texturedCount] = local240.textureVertexC[local445] + local245;
					this.texturedCount++;
				}
			}
		}
		this.calculateYBoundaries();
	}

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "(Lclient!eb;ZZIZ)V")
	public Model(@OriginalArg(0) Model arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) boolean arg2, @OriginalArg(4) boolean arg4) {
		this.vertexCount = arg0.vertexCount;
		this.triangleCount = arg0.triangleCount;
		this.texturedCount = arg0.texturedCount;
		@Pc(66) int local66;
		if (arg4) {
			this.vertexX = arg0.vertexX;
			this.vertexY = arg0.vertexY;
			this.vertexZ = arg0.vertexZ;
		} else {
			this.vertexX = new int[this.vertexCount];
			this.vertexY = new int[this.vertexCount];
			this.vertexZ = new int[this.vertexCount];
			for (local66 = 0; local66 < this.vertexCount; local66++) {
				this.vertexX[local66] = arg0.vertexX[local66];
				this.vertexY[local66] = arg0.vertexY[local66];
				this.vertexZ[local66] = arg0.vertexZ[local66];
			}
		}
		if (arg1) {
			this.unmodifiedTriangleColor = arg0.unmodifiedTriangleColor;
		} else {
			this.unmodifiedTriangleColor = new int[this.triangleCount];
			for (local66 = 0; local66 < this.triangleCount; local66++) {
				this.unmodifiedTriangleColor[local66] = arg0.unmodifiedTriangleColor[local66];
			}
		}
		if (arg2) {
			this.triangleAlpha = arg0.triangleAlpha;
		} else {
			this.triangleAlpha = new int[this.triangleCount];
			if (arg0.triangleAlpha == null) {
				for (local66 = 0; local66 < this.triangleCount; local66++) {
					this.triangleAlpha[local66] = 0;
				}
			} else {
				for (local66 = 0; local66 < this.triangleCount; local66++) {
					this.triangleAlpha[local66] = arg0.triangleAlpha[local66];
				}
			}
		}
		this.vertexLabel = arg0.vertexLabel;
		this.triangleSkin = arg0.triangleSkin;
		this.triangleInfo = arg0.triangleInfo;
		this.triangleVertexA = arg0.triangleVertexA;
		this.triangleVertexB = arg0.triangleVertexB;
		this.triangleVertexC = arg0.triangleVertexC;
		this.trianglePriorities = arg0.trianglePriorities;
		this.priority = arg0.priority;
		this.textureVertexA = arg0.textureVertexA;
		this.textureVertexB = arg0.textureVertexB;
		this.textureVertexC = arg0.textureVertexC;
	}

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "(Lclient!eb;BZZ)V")
	public Model(@OriginalArg(0) Model arg0, @OriginalArg(2) boolean arg2, @OriginalArg(3) boolean arg3) {
		this.vertexCount = arg0.vertexCount;
		this.triangleCount = arg0.triangleCount;
		this.texturedCount = arg0.texturedCount;
		@Pc(42) int local42;
		if (arg2) {
			this.vertexY = new int[this.vertexCount];
			for (local42 = 0; local42 < this.vertexCount; local42++) {
				this.vertexY[local42] = arg0.vertexY[local42];
			}
		} else {
			this.vertexY = arg0.vertexY;
		}
		if (arg3) {
			this.colorA = new int[this.triangleCount];
			this.colorB = new int[this.triangleCount];
			this.colorC = new int[this.triangleCount];
			for (local42 = 0; local42 < this.triangleCount; local42++) {
				this.colorA[local42] = arg0.colorA[local42];
				this.colorB[local42] = arg0.colorB[local42];
				this.colorC[local42] = arg0.colorC[local42];
			}
			this.triangleInfo = new int[this.triangleCount];
			@Pc(122) int local122;
			if (arg0.triangleInfo == null) {
				for (local122 = 0; local122 < this.triangleCount; local122++) {
					this.triangleInfo[local122] = 0;
				}
			} else {
				for (local122 = 0; local122 < this.triangleCount; local122++) {
					this.triangleInfo[local122] = arg0.triangleInfo[local122];
				}
			}
			this.vertexNormals = new VertexNormal[this.vertexCount];
			for (local122 = 0; local122 < this.vertexCount; local122++) {
				@Pc(170) VertexNormal local170 = this.vertexNormals[local122] = new VertexNormal();
				@Pc(175) VertexNormal local175 = arg0.vertexNormals[local122];
				local170.x = local175.x;
				local170.y = local175.y;
				local170.z = local175.z;
				local170.magnitude = local175.magnitude;
			}
			this.vertexNormalOriginal = arg0.vertexNormalOriginal;
		} else {
			this.colorA = arg0.colorA;
			this.colorB = arg0.colorB;
			this.colorC = arg0.colorC;
			this.triangleInfo = arg0.triangleInfo;
		}
		this.vertexX = arg0.vertexX;
		this.vertexZ = arg0.vertexZ;
		this.unmodifiedTriangleColor = arg0.unmodifiedTriangleColor;
		this.triangleAlpha = arg0.triangleAlpha;
		this.trianglePriorities = arg0.trianglePriorities;
		this.priority = arg0.priority;
		this.triangleVertexA = arg0.triangleVertexA;
		this.triangleVertexB = arg0.triangleVertexB;
		this.triangleVertexC = arg0.triangleVertexC;
		this.textureVertexA = arg0.textureVertexA;
		this.textureVertexB = arg0.textureVertexB;
		this.textureVertexC = arg0.textureVertexC;
		this.maxBoundY = arg0.maxBoundY;
		this.minBoundY = arg0.minBoundY;
		this.lengthXZ = arg0.lengthXZ;
		this.minDepth = arg0.minDepth;
		this.maxDepth = arg0.maxDepth;
		this.minBoundX = arg0.minBoundX;
		this.maxBoundZ = arg0.maxBoundZ;
		this.minBoundZ = arg0.minBoundZ;
		this.maxBoundX = arg0.maxBoundX;
	}

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "(ILclient!eb;Z)V")
	public Model(@OriginalArg(1) Model arg1, @OriginalArg(2) boolean arg2) {
		this.vertexCount = arg1.vertexCount;
		this.triangleCount = arg1.triangleCount;
		this.texturedCount = arg1.texturedCount;
		this.vertexX = new int[this.vertexCount];
		this.vertexY = new int[this.vertexCount];
		this.vertexZ = new int[this.vertexCount];
		for (@Pc(50) int local50 = 0; local50 < this.vertexCount; local50++) {
			this.vertexX[local50] = arg1.vertexX[local50];
			this.vertexY[local50] = arg1.vertexY[local50];
			this.vertexZ[local50] = arg1.vertexZ[local50];
		}
		if (arg2) {
			this.triangleAlpha = arg1.triangleAlpha;
		} else {
			this.triangleAlpha = new int[this.triangleCount];
			@Pc(99) int local99;
			if (arg1.triangleAlpha == null) {
				for (local99 = 0; local99 < this.triangleCount; local99++) {
					this.triangleAlpha[local99] = 0;
				}
			} else {
				for (local99 = 0; local99 < this.triangleCount; local99++) {
					this.triangleAlpha[local99] = arg1.triangleAlpha[local99];
				}
			}
		}
		this.triangleInfo = arg1.triangleInfo;
		this.unmodifiedTriangleColor = arg1.unmodifiedTriangleColor;
		this.trianglePriorities = arg1.trianglePriorities;
		this.priority = arg1.priority;
		this.skinTriangle = arg1.skinTriangle;
		this.labelVertices = arg1.labelVertices;
		this.triangleVertexA = arg1.triangleVertexA;
		this.triangleVertexB = arg1.triangleVertexB;
		this.triangleVertexC = arg1.triangleVertexC;
		this.colorA = arg1.colorA;
		this.colorB = arg1.colorB;
		this.colorC = arg1.colorC;
		this.textureVertexA = arg1.textureVertexA;
		this.textureVertexB = arg1.textureVertexB;
		this.textureVertexC = arg1.textureVertexC;
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(Z)V")
	public static void unload() {
		metadata = null;
		head = null;
		face1 = null;
		face2 = null;
		face3 = null;
		face4 = null;
		face5 = null;
		point1 = null;
		point2 = null;
		point3 = null;
		point4 = null;
		point5 = null;
		vertex1 = null;
		vertex2 = null;
		axis = null;
		testTriangleX = null;
		projectTriangle = null;
		vertexScreenX = null;
		vertexScreenY = null;
		vertexDepth = null;
		projectSceneX = null;
		projectSceneY = null;
		projectSceneZ = null;
		depthTriangleCount = null;
		depthTriangles = null;
		priorityTriangleCounts = null;
		priorityTriangles = null;
		normalTrianglePriority = null;
		highTrianglePriority = null;
		lowTrianglePriority = null;
		sin = null;
		cos = null;
		palette = null;
		reciprical16 = null;
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(ILclient!ub;)V")
	public static void unpack(@OriginalArg(1) FileArchive archive) {
		try {
			head = new Buffer(archive.read("ob_head.dat", null));
			face1 = new Buffer(archive.read("ob_face1.dat", null));
			face2 = new Buffer(archive.read("ob_face2.dat", null));
			face3 = new Buffer(archive.read("ob_face3.dat", null));
			face4 = new Buffer(archive.read("ob_face4.dat", null));
			face5 = new Buffer(archive.read("ob_face5.dat", null));
			point1 = new Buffer(archive.read("ob_point1.dat", null));
			point2 = new Buffer(archive.read("ob_point2.dat", null));
			point3 = new Buffer(archive.read("ob_point3.dat", null));
			point4 = new Buffer(archive.read("ob_point4.dat", null));
			point5 = new Buffer(archive.read("ob_point5.dat", null));
			vertex1 = new Buffer(archive.read("ob_vertex1.dat", null));
			vertex2 = new Buffer(archive.read("ob_vertex2.dat", null));
			axis = new Buffer(archive.read("ob_axis.dat", null));
			head.pos = 0;
			point1.pos = 0;
			point2.pos = 0;
			point3.pos = 0;
			point4.pos = 0;
			vertex1.pos = 0;
			vertex2.pos = 0;
			@Pc(172) int local172 = head.g2();
			metadata = new Metadata[local172 + 100];
			@Pc(179) int local179 = 0;
			@Pc(181) int local181 = 0;
			@Pc(183) int local183 = 0;
			@Pc(185) int local185 = 0;
			@Pc(187) int local187 = 0;
			@Pc(189) int local189 = 0;
			@Pc(191) int local191 = 0;
			for (@Pc(193) int local193 = 0; local193 < local172; local193++) {
				@Pc(198) int local198 = head.g2();
				@Pc(206) Metadata local206 = metadata[local198] = new Metadata();
				local206.vertexCount = head.g2();
				local206.triangleCount = head.g2();
				local206.texturedCount = head.g1();
				local206.vertexFlagDataOffset = point1.pos;
				local206.vertexXDataOffset = point2.pos;
				local206.vertexYDataOffset = point3.pos;
				local206.vertexZDataOffset = point4.pos;
				local206.vertexIndexDataOffset = vertex1.pos;
				local206.triangleTypeDataOffset = vertex2.pos;
				@Pc(245) int local245 = head.g1();
				@Pc(248) int local248 = head.g1();
				@Pc(251) int local251 = head.g1();
				@Pc(254) int local254 = head.g1();
				@Pc(257) int local257 = head.g1();
				@Pc(264) int local264;
				for (@Pc(259) int local259 = 0; local259 < local206.vertexCount; local259++) {
					local264 = point1.g1();
					if ((local264 & 0x1) != 0) {
						point2.gsmart();
					}
					if ((local264 & 0x2) != 0) {
						point3.gsmart();
					}
					if ((local264 & 0x4) != 0) {
						point4.gsmart();
					}
				}
				for (local264 = 0; local264 < local206.triangleCount; local264++) {
					@Pc(297) int local297 = vertex2.g1();
					if (local297 == 1) {
						vertex1.gsmart();
						vertex1.gsmart();
					}
					vertex1.gsmart();
				}
				local206.triangleColorDataOffset = local183;
				local183 += local206.triangleCount * 2;
				if (local245 == 1) {
					local206.triangleInfoDataOffset = local185;
					local185 += local206.triangleCount;
				} else {
					local206.triangleInfoDataOffset = -1;
				}
				if (local248 == 255) {
					local206.trianglePriorityDataOffset = local187;
					local187 += local206.triangleCount;
				} else {
					local206.trianglePriorityDataOffset = -local248 - 1;
				}
				if (local251 == 1) {
					local206.triangleAlphaDataOffset = local189;
					local189 += local206.triangleCount;
				} else {
					local206.triangleAlphaDataOffset = -1;
				}
				if (local254 == 1) {
					local206.triangleSkinDataOffset = local191;
					local191 += local206.triangleCount;
				} else {
					local206.triangleSkinDataOffset = -1;
				}
				if (local257 == 1) {
					local206.vertexLabelDataOffset = local181;
					local181 += local206.vertexCount;
				} else {
					local206.vertexLabelDataOffset = -1;
				}
				local206.triangleTextureDataOffset = local179;
				local179 += local206.texturedCount;
			}
		} catch (@Pc(421) Exception local421) {
			System.out.println("Error loading model index");
			local421.printStackTrace();
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(III)I")
	public static int adjustHslLightness(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if ((arg2 & 0x2) == 2) {
			if (arg1 < 0) {
				arg1 = 0;
			} else if (arg1 > 127) {
				arg1 = 127;
			}
			return 127 - arg1;
		}
		arg1 = arg1 * (arg0 & 0x7F) >> 7;
		if (arg1 < 2) {
			arg1 = 2;
		} else if (arg1 > 126) {
			arg1 = 126;
		}
		return (arg0 & 0xFF80) + arg1;
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(Lclient!eb;I)I")
	private int copyVertex(@OriginalArg(0) Model arg0, @OriginalArg(1) int arg1) {
		@Pc(3) int local3 = -1;
		@Pc(8) int local8 = arg0.vertexX[arg1];
		@Pc(13) int local13 = arg0.vertexY[arg1];
		@Pc(18) int local18 = arg0.vertexZ[arg1];
		for (@Pc(20) int local20 = 0; local20 < this.vertexCount; local20++) {
			if (local8 == this.vertexX[local20] && local13 == this.vertexY[local20] && local18 == this.vertexZ[local20]) {
				local3 = local20;
				break;
			}
		}
		if (local3 == -1) {
			this.vertexX[this.vertexCount] = local8;
			this.vertexY[this.vertexCount] = local13;
			this.vertexZ[this.vertexCount] = local18;
			if (arg0.vertexLabel != null) {
				this.vertexLabel[this.vertexCount] = arg0.vertexLabel[arg1];
			}
			local3 = this.vertexCount++;
		}
		return local3;
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(I)V")
	public void calculateYBoundaries() {
		this.maxBoundY = 0;
		this.lengthXZ = 0;
		this.minBoundY = 0;
		for (@Pc(14) int local14 = 0; local14 < this.vertexCount; local14++) {
			@Pc(21) int local21 = this.vertexX[local14];
			@Pc(26) int local26 = this.vertexY[local14];
			@Pc(31) int local31 = this.vertexZ[local14];
			if (-local26 > this.maxBoundY) {
				this.maxBoundY = -local26;
			}
			if (local26 > this.minBoundY) {
				this.minBoundY = local26;
			}
			@Pc(55) int local55 = local21 * local21 + local31 * local31;
			if (local55 > this.lengthXZ) {
				this.lengthXZ = local55;
			}
		}
		this.lengthXZ = (int) (Math.sqrt(this.lengthXZ) + 0.99D);
		this.minDepth = (int) (Math.sqrt(this.lengthXZ * this.lengthXZ + this.maxBoundY * this.maxBoundY) + 0.99D);
		this.maxDepth = this.minDepth + (int) (Math.sqrt(this.lengthXZ * this.lengthXZ + this.minBoundY * this.minBoundY) + 0.99D);
	}

	@OriginalMember(owner = "client!eb", name = "b", descriptor = "(I)V")
	public void calculateYBoundaries2() {
		this.maxBoundY = 0;
		this.minBoundY = 0;
		for (@Pc(8) int local8 = 0; local8 < this.vertexCount; local8++) {
			@Pc(25) int local25 = this.vertexY[local8];
			if (-local25 > this.maxBoundY) {
				this.maxBoundY = -local25;
			}
			if (local25 > this.minBoundY) {
				this.minBoundY = local25;
			}
		}
		this.minDepth = (int) (Math.sqrt(this.lengthXZ * this.lengthXZ + this.maxBoundY * this.maxBoundY) + 0.99D);
		this.maxDepth = this.minDepth + (int) (Math.sqrt(this.lengthXZ * this.lengthXZ + this.minBoundY * this.minBoundY) + 0.99D);
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(B)V")
	private void calculateBoundaries() {
		this.maxBoundY = 0;
		this.lengthXZ = 0;
		this.minBoundY = 0;
		this.minBoundX = 999999;
		this.maxBoundX = -999999;
		this.maxBoundZ = -99999;
		this.minBoundZ = 99999;
		for (@Pc(27) int local27 = 0; local27 < this.vertexCount; local27++) {
			@Pc(34) int local34 = this.vertexX[local27];
			@Pc(39) int local39 = this.vertexY[local27];
			@Pc(44) int local44 = this.vertexZ[local27];
			if (local34 < this.minBoundX) {
				this.minBoundX = local34;
			}
			if (local34 > this.maxBoundX) {
				this.maxBoundX = local34;
			}
			if (local44 < this.minBoundZ) {
				this.minBoundZ = local44;
			}
			if (local44 > this.maxBoundZ) {
				this.maxBoundZ = local44;
			}
			if (-local39 > this.maxBoundY) {
				this.maxBoundY = -local39;
			}
			if (local39 > this.minBoundY) {
				this.minBoundY = local39;
			}
			@Pc(96) int local96 = local34 * local34 + local44 * local44;
			if (local96 > this.lengthXZ) {
				this.lengthXZ = local96;
			}
		}
		this.lengthXZ = (int) Math.sqrt(this.lengthXZ);
		this.minDepth = (int) Math.sqrt(this.lengthXZ * this.lengthXZ + this.maxBoundY * this.maxBoundY);
		this.maxDepth = this.minDepth + (int) Math.sqrt(this.lengthXZ * this.lengthXZ + this.minBoundY * this.minBoundY);
	}

	@OriginalMember(owner = "client!eb", name = "c", descriptor = "(I)V")
	public void applyGroup() {
		@Pc(11) int[] local11;
		@Pc(26) int local26;
		@Pc(13) int local13;
		@Pc(15) int local15;
		@Pc(22) int local22;
		@Pc(67) int local67;
		@Pc(74) int local74;
		if (this.vertexLabel != null) {
			local11 = new int[256];
			local13 = 0;
			for (local15 = 0; local15 < this.vertexCount; local15++) {
				local22 = this.vertexLabel[local15];
				local26 = local11[local22]++;
				if (local22 > local13) {
					local13 = local22;
				}
			}
			this.labelVertices = new int[local13 + 1][];
			for (local22 = 0; local22 <= local13; local22++) {
				this.labelVertices[local22] = new int[local11[local22]];
				local11[local22] = 0;
			}
			local67 = 0;
			while (local67 < this.vertexCount) {
				local74 = this.vertexLabel[local67];
				this.labelVertices[local74][local11[local74]++] = local67++;
			}
			this.vertexLabel = null;
		}
		if (this.triangleSkin != null) {
			local11 = new int[256];
			local13 = 0;
			for (local15 = 0; local15 < this.triangleCount; local15++) {
				local22 = this.triangleSkin[local15];
				local26 = local11[local22]++;
				if (local22 > local13) {
					local13 = local22;
				}
			}
			this.skinTriangle = new int[local13 + 1][];
			for (local22 = 0; local22 <= local13; local22++) {
				this.skinTriangle[local22] = new int[local11[local22]];
				local11[local22] = 0;
			}
			local67 = 0;
			while (local67 < this.triangleCount) {
				local74 = this.triangleSkin[local67];
				this.skinTriangle[local74][local11[local74]++] = local67++;
			}
			this.triangleSkin = null;
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(II)V")
	public void applyFrame(@OriginalArg(1) int arg1) {
		if (this.labelVertices == null || arg1 == -1) {
			return;
		}

		@Pc(11) SeqFrame local11 = SeqFrame.instances[arg1];
		@Pc(14) SeqBase local14 = local11.transform;
		transformX = 0;
		transformY = 0;
		transformZ = 0;
		for (@Pc(22) int local22 = 0; local22 < local11.groupCount; local22++) {
			@Pc(29) int local29 = local11.groups[local22];
			this.transform(local14.types[local29], local14.groupLabels[local29], local11.x[local22], local11.y[local22], local11.z[local22]);
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(III[I)V")
	public void applyFrames(@OriginalArg(0) int arg0, @OriginalArg(2) int arg2, @OriginalArg(3) int[] arg3) {
		if (arg3 == null || arg0 == -1) {
			this.applyFrame(arg2);
		} else {
			@Pc(19) SeqFrame local19 = SeqFrame.instances[arg2];
			@Pc(32) SeqFrame local32 = SeqFrame.instances[arg0];
			@Pc(35) SeqBase local35 = local19.transform;
			transformX = 0;
			transformY = 0;
			transformZ = 0;
			@Pc(43) byte local43 = 0;
			@Pc(46) int local46 = local43 + 1;
			@Pc(48) int local48 = arg3[local43];
			@Pc(57) int local57;
			for (@Pc(50) int local50 = 0; local50 < local19.groupCount; local50++) {
				local57 = local19.groups[local50];
				while (local57 > local48) {
					local48 = arg3[local46++];
				}
				if (local57 != local48 || local35.types[local57] == 0) {
					this.transform(local35.types[local57], local35.groupLabels[local57], local19.x[local50], local19.y[local50], local19.z[local50]);
				}
			}
			transformX = 0;
			transformY = 0;
			transformZ = 0;
			local43 = 0;
			local46 = local43 + 1;
			local48 = arg3[local43];
			for (local57 = 0; local57 < local32.groupCount; local57++) {
				@Pc(124) int local124 = local32.groups[local57];
				while (local124 > local48) {
					local48 = arg3[local46++];
				}
				if (local124 == local48 || local35.types[local124] == 0) {
					this.transform(local35.types[local124], local35.groupLabels[local124], local32.x[local57], local32.y[local57], local32.z[local57]);
				}
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(I[IIII)V")
	private void transform(@OriginalArg(0) int arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		@Pc(4) int local4 = arg1.length;
		@Pc(8) int local8;
		@Pc(16) int local16;
		@Pc(34) int local34;
		@Pc(40) int local40;
		if (arg0 == 0) {
			local8 = 0;
			transformX = 0;
			transformY = 0;
			transformZ = 0;
			for (local16 = 0; local16 < local4; local16++) {
				@Pc(22) int local22 = arg1[local16];
				if (local22 < this.labelVertices.length) {
					@Pc(32) int[] local32 = this.labelVertices[local22];
					for (local34 = 0; local34 < local32.length; local34++) {
						local40 = local32[local34];
						transformX += this.vertexX[local40];
						transformY += this.vertexY[local40];
						transformZ += this.vertexZ[local40];
						local8++;
					}
				}
			}
			if (local8 > 0) {
				transformX = transformX / local8 + arg2;
				transformY = transformY / local8 + arg3;
				transformZ = transformZ / local8 + arg4;
			} else {
				transformX = arg2;
				transformY = arg3;
				transformZ = arg4;
			}
			return;
		}
		@Pc(120) int[] local120;
		@Pc(122) int local122;
		if (arg0 == 1) {
			for (local8 = 0; local8 < local4; local8++) {
				local16 = arg1[local8];
				if (local16 < this.labelVertices.length) {
					local120 = this.labelVertices[local16];
					for (local122 = 0; local122 < local120.length; local122++) {
						local34 = local120[local122];
						this.vertexX[local34] += arg2;
						this.vertexY[local34] += arg3;
						this.vertexZ[local34] += arg4;
					}
				}
			}
		} else if (arg0 == 2) {
			for (local8 = 0; local8 < local4; local8++) {
				local16 = arg1[local8];
				if (local16 < this.labelVertices.length) {
					local120 = this.labelVertices[local16];
					for (local122 = 0; local122 < local120.length; local122++) {
						local34 = local120[local122];
						this.vertexX[local34] -= transformX;
						this.vertexY[local34] -= transformY;
						this.vertexZ[local34] -= transformZ;
						local40 = (arg2 & 0xFF) * 8;
						@Pc(227) int local227 = (arg3 & 0xFF) * 8;
						@Pc(233) int local233 = (arg4 & 0xFF) * 8;
						@Pc(239) int local239;
						@Pc(243) int local243;
						@Pc(259) int local259;
						if (local233 != 0) {
							local239 = sin[local233];
							local243 = cos[local233];
							local259 = this.vertexY[local34] * local239 + this.vertexX[local34] * local243 >> 16;
							this.vertexY[local34] = this.vertexY[local34] * local243 - this.vertexX[local34] * local239 >> 16;
							this.vertexX[local34] = local259;
						}
						if (local40 != 0) {
							local239 = sin[local40];
							local243 = cos[local40];
							local259 = this.vertexY[local34] * local243 - this.vertexZ[local34] * local239 >> 16;
							this.vertexZ[local34] = this.vertexY[local34] * local239 + this.vertexZ[local34] * local243 >> 16;
							this.vertexY[local34] = local259;
						}
						if (local227 != 0) {
							local239 = sin[local227];
							local243 = cos[local227];
							local259 = this.vertexZ[local34] * local239 + this.vertexX[local34] * local243 >> 16;
							this.vertexZ[local34] = this.vertexZ[local34] * local243 - this.vertexX[local34] * local239 >> 16;
							this.vertexX[local34] = local259;
						}
						this.vertexX[local34] += transformX;
						this.vertexY[local34] += transformY;
						this.vertexZ[local34] += transformZ;
					}
				}
			}
		} else if (arg0 == 3) {
			for (local8 = 0; local8 < local4; local8++) {
				local16 = arg1[local8];
				if (local16 < this.labelVertices.length) {
					local120 = this.labelVertices[local16];
					for (local122 = 0; local122 < local120.length; local122++) {
						local34 = local120[local122];
						this.vertexX[local34] -= transformX;
						this.vertexY[local34] -= transformY;
						this.vertexZ[local34] -= transformZ;
						this.vertexX[local34] = this.vertexX[local34] * arg2 / 128;
						this.vertexY[local34] = this.vertexY[local34] * arg3 / 128;
						this.vertexZ[local34] = this.vertexZ[local34] * arg4 / 128;
						this.vertexX[local34] += transformX;
						this.vertexY[local34] += transformY;
						this.vertexZ[local34] += transformZ;
					}
				}
			}
		} else if (arg0 == 5 && (this.skinTriangle != null && this.triangleAlpha != null)) {
			for (local8 = 0; local8 < local4; local8++) {
				local16 = arg1[local8];
				if (local16 < this.skinTriangle.length) {
					local120 = this.skinTriangle[local16];
					for (local122 = 0; local122 < local120.length; local122++) {
						local34 = local120[local122];
						this.triangleAlpha[local34] += arg2 * 8;
						if (this.triangleAlpha[local34] < 0) {
							this.triangleAlpha[local34] = 0;
						}
						if (this.triangleAlpha[local34] > 255) {
							this.triangleAlpha[local34] = 255;
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "d", descriptor = "(I)V")
	public void rotateCounterClockwise() {
		for (@Pc(6) int local6 = 0; local6 < this.vertexCount; local6++) {
			@Pc(13) int local13 = this.vertexX[local6];
			this.vertexX[local6] = this.vertexZ[local6];
			this.vertexZ[local6] = -local13;
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(BI)V")
	public void rotatePitch(@OriginalArg(1) int arg1) {
		@Pc(3) int local3 = sin[arg1];
		@Pc(7) int local7 = cos[arg1];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(27) int local27 = this.vertexY[local9] * local7 - this.vertexZ[local9] * local3 >> 16;
			this.vertexZ[local9] = this.vertexY[local9] * local3 + this.vertexZ[local9] * local7 >> 16;
			this.vertexY[local9] = local27;
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIII)V")
	public void translate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg3) {
		for (@Pc(11) int local11 = 0; local11 < this.vertexCount; local11++) {
			this.vertexX[local11] += arg1;
			this.vertexY[local11] += arg0;
			this.vertexZ[local11] += arg3;
		}
	}

	@OriginalMember(owner = "client!eb", name = "b", descriptor = "(II)V")
	public void recolor(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		for (@Pc(1) int local1 = 0; local1 < this.triangleCount; local1++) {
			if (this.unmodifiedTriangleColor[local1] == arg0) {
				this.unmodifiedTriangleColor[local1] = arg1;
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "e", descriptor = "(I)V")
	public void flipBackwards() {
		for (@Pc(3) int local3 = 0; local3 < this.vertexCount; local3++) {
			this.vertexZ[local3] = -this.vertexZ[local3];
		}
		@Pc(28) int local28;
		for (@Pc(21) int local21 = 0; local21 < this.triangleCount; local21++) {
			local28 = this.triangleVertexA[local21];
			this.triangleVertexA[local21] = this.triangleVertexC[local21];
			this.triangleVertexC[local21] = local28;
		}
	}

	@OriginalMember(owner = "client!eb", name = "b", descriptor = "(IIII)V")
	public void scale(@OriginalArg(0) int arg0, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			this.vertexX[local1] = this.vertexX[local1] * arg3 / 128;
			this.vertexY[local1] = this.vertexY[local1] * arg2 / 128;
			this.vertexZ[local1] = this.vertexZ[local1] * arg0 / 128;
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIZ)V")
	public void applyLighting(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5) {
		@Pc(16) int local16 = (int) Math.sqrt(arg2 * arg2 + arg3 * arg3 + arg4 * arg4);
		@Pc(22) int local22 = arg1 * local16 >> 8;
		if (this.colorA == null) {
			this.colorA = new int[this.triangleCount];
			this.colorB = new int[this.triangleCount];
			this.colorC = new int[this.triangleCount];
		}
		@Pc(50) int local50;
		if (this.vertexNormals == null) {
			this.vertexNormals = new VertexNormal[this.vertexCount];
			for (local50 = 0; local50 < this.vertexCount; local50++) {
				this.vertexNormals[local50] = new VertexNormal();
			}
		}
		@Pc(73) int local73;
		for (local50 = 0; local50 < this.triangleCount; local50++) {
			local73 = this.triangleVertexA[local50];
			@Pc(78) int local78 = this.triangleVertexB[local50];
			@Pc(83) int local83 = this.triangleVertexC[local50];
			@Pc(93) int local93 = this.vertexX[local78] - this.vertexX[local73];
			@Pc(103) int local103 = this.vertexY[local78] - this.vertexY[local73];
			@Pc(113) int local113 = this.vertexZ[local78] - this.vertexZ[local73];
			@Pc(123) int local123 = this.vertexX[local83] - this.vertexX[local73];
			@Pc(133) int local133 = this.vertexY[local83] - this.vertexY[local73];
			@Pc(143) int local143 = this.vertexZ[local83] - this.vertexZ[local73];
			@Pc(151) int local151 = local103 * local143 - local133 * local113;
			@Pc(159) int local159 = local113 * local123 - local143 * local93;
			@Pc(167) int local167;
			for (local167 = local93 * local133 - local123 * local103; local151 > 8192 || local159 > 8192 || local167 > 8192 || local151 < -8192 || local159 < -8192 || local167 < -8192; local167 >>= 0x1) {
				local151 >>= 0x1;
				local159 >>= 0x1;
			}
			@Pc(214) int local214 = (int) Math.sqrt(local151 * local151 + local159 * local159 + local167 * local167);
			if (local214 <= 0) {
				local214 = 1;
			}
			local151 = local151 * 256 / local214;
			local159 = local159 * 256 / local214;
			local167 = local167 * 256 / local214;
			if (this.triangleInfo == null || (this.triangleInfo[local50] & 0x1) == 0) {
				@Pc(251) VertexNormal local251 = this.vertexNormals[local73];
				local251.x += local151;
				local251.y += local159;
				local251.z += local167;
				local251.magnitude++;
				@Pc(280) VertexNormal local280 = this.vertexNormals[local78];
				local280.x += local151;
				local280.y += local159;
				local280.z += local167;
				local280.magnitude++;
				@Pc(309) VertexNormal local309 = this.vertexNormals[local83];
				local309.x += local151;
				local309.y += local159;
				local309.z += local167;
				local309.magnitude++;
			} else {
				@Pc(355) int local355 = arg0 + (arg2 * local151 + arg3 * local159 + arg4 * local167) / (local22 + local22 / 2);
				this.colorA[local50] = adjustHslLightness(this.unmodifiedTriangleColor[local50], local355, this.triangleInfo[local50]);
			}
		}
		if (arg5) {
			this.calculateLighting(arg0, local22, arg2, arg3, arg4);
		} else {
			this.vertexNormalOriginal = new VertexNormal[this.vertexCount];
			for (local73 = 0; local73 < this.vertexCount; local73++) {
				@Pc(399) VertexNormal local399 = this.vertexNormals[local73];
				@Pc(408) VertexNormal local408 = this.vertexNormalOriginal[local73] = new VertexNormal();
				local408.x = local399.x;
				local408.y = local399.y;
				local408.z = local399.z;
				local408.magnitude = local399.magnitude;
			}
		}
		if (arg5) {
			this.calculateYBoundaries();
		} else {
			this.calculateBoundaries();
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIII)V")
	public void calculateLighting(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		@Pc(10) int local10;
		for (@Pc(3) int local3 = 0; local3 < this.triangleCount; local3++) {
			local10 = this.triangleVertexA[local3];
			@Pc(15) int local15 = this.triangleVertexB[local3];
			@Pc(20) int local20 = this.triangleVertexC[local3];
			@Pc(33) VertexNormal local33;
			@Pc(55) int local55;
			@Pc(28) int local28;
			if (this.triangleInfo == null) {
				local28 = this.unmodifiedTriangleColor[local3];
				local33 = this.vertexNormals[local10];
				local55 = arg0 + (arg2 * local33.x + arg3 * local33.y + arg4 * local33.z) / (arg1 * local33.magnitude);
				this.colorA[local3] = adjustHslLightness(local28, local55, 0);
				@Pc(68) VertexNormal local68 = this.vertexNormals[local15];
				@Pc(90) int local90 = arg0 + (arg2 * local68.x + arg3 * local68.y + arg4 * local68.z) / (arg1 * local68.magnitude);
				this.colorB[local3] = adjustHslLightness(local28, local90, 0);
				@Pc(103) VertexNormal local103 = this.vertexNormals[local20];
				@Pc(125) int local125 = arg0 + (arg2 * local103.x + arg3 * local103.y + arg4 * local103.z) / (arg1 * local103.magnitude);
				this.colorC[local3] = adjustHslLightness(local28, local125, 0);
			} else if ((this.triangleInfo[local3] & 0x1) == 0) {
				local28 = this.unmodifiedTriangleColor[local3];
				@Pc(152) int local152 = this.triangleInfo[local3];
				local33 = this.vertexNormals[local10];
				local55 = arg0 + (arg2 * local33.x + arg3 * local33.y + arg4 * local33.z) / (arg1 * local33.magnitude);
				this.colorA[local3] = adjustHslLightness(local28, local55, local152);
				local33 = this.vertexNormals[local15];
				local55 = arg0 + (arg2 * local33.x + arg3 * local33.y + arg4 * local33.z) / (arg1 * local33.magnitude);
				this.colorB[local3] = adjustHslLightness(local28, local55, local152);
				local33 = this.vertexNormals[local20];
				local55 = arg0 + (arg2 * local33.x + arg3 * local33.y + arg4 * local33.z) / (arg1 * local33.magnitude);
				this.colorC[local3] = adjustHslLightness(local28, local55, local152);
			}
		}
		this.vertexNormals = null;
		this.vertexNormalOriginal = null;
		this.vertexLabel = null;
		this.triangleSkin = null;
		if (this.triangleInfo != null) {
			for (local10 = 0; local10 < this.triangleCount; local10++) {
				if ((this.triangleInfo[local10] & 0x2) == 2) {
					return;
				}
			}
		}
		this.unmodifiedTriangleColor = null;
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIII)V")
	public void draw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(1) int local1 = Draw3D.centerX3D;
		@Pc(3) int local3 = Draw3D.centerY3D;
		@Pc(7) int local7 = sin[arg0];
		@Pc(11) int local11 = cos[arg0];
		@Pc(15) int local15 = sin[arg1];
		@Pc(19) int local19 = cos[arg1];
		@Pc(23) int local23 = sin[arg2];
		@Pc(27) int local27 = cos[arg2];
		@Pc(31) int local31 = sin[arg3];
		@Pc(35) int local35 = cos[arg3];
		@Pc(45) int local45 = arg5 * local31 + arg6 * local35 >> 16;
		for (@Pc(47) int local47 = 0; local47 < this.vertexCount; local47++) {
			@Pc(54) int local54 = this.vertexX[local47];
			@Pc(59) int local59 = this.vertexY[local47];
			@Pc(64) int local64 = this.vertexZ[local47];
			@Pc(76) int local76;
			if (arg2 != 0) {
				local76 = local59 * local23 + local54 * local27 >> 16;
				local59 = local59 * local27 - local54 * local23 >> 16;
				local54 = local76;
			}
			if (arg0 != 0) {
				local76 = local59 * local11 - local64 * local7 >> 16;
				local64 = local59 * local7 + local64 * local11 >> 16;
				local59 = local76;
			}
			if (arg1 != 0) {
				local76 = local64 * local15 + local54 * local19 >> 16;
				local64 = local64 * local19 - local54 * local15 >> 16;
				local54 = local76;
			}
			local54 += arg4;
			local59 += arg5;
			local64 += arg6;
			local76 = local59 * local35 - local64 * local31 >> 16;
			local64 = local59 * local31 + local64 * local35 >> 16;
			vertexDepth[local47] = local64 - local45;
			vertexScreenX[local47] = local1 + (local54 << 9) / local64;
			vertexScreenY[local47] = local3 + (local76 << 9) / local64;
			if (this.texturedCount > 0) {
				projectSceneX[local47] = local54;
				projectSceneY[local47] = local76;
				projectSceneZ[local47] = local64;
			}
		}
		try {
			this.draw(false, false, 0);
		} catch (@Pc(223) Exception local223) {
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIIIII)V")
	public void draw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8) {
		@Pc(11) int local11 = arg7 * arg4 - arg5 * arg3 >> 16;
		@Pc(21) int local21 = arg6 * arg1 + local11 * arg2 >> 16;
		@Pc(28) int local28 = this.lengthXZ * arg2 >> 16;
		@Pc(32) int local32 = local21 + local28;
		if (local32 <= 50 || local21 >= 3500) {
			return;
		}
		@Pc(49) int local49 = arg7 * arg3 + arg5 * arg4 >> 16;
		@Pc(56) int local56 = local49 - this.lengthXZ << 9;
		if (local56 / local32 >= Draw2D.centerX) {
			return;
		}
		@Pc(69) int local69 = local49 + this.lengthXZ << 9;
		if (local69 / local32 <= -Draw2D.centerX) {
			return;
		}
		@Pc(86) int local86 = arg6 * arg2 - local11 * arg1 >> 16;
		@Pc(93) int local93 = this.lengthXZ * arg1 >> 16;
		@Pc(99) int local99 = local86 + local93 << 9;
		if (local99 / local32 <= -Draw2D.centerY) {
			return;
		}
		@Pc(115) int local115 = local93 + (this.maxBoundY * arg2 >> 16);
		@Pc(121) int local121 = local86 - local115 << 9;
		if (local121 / local32 >= Draw2D.centerY) {
			return;
		}
		@Pc(136) int local136 = local28 + (this.maxBoundY * arg1 >> 16);
		@Pc(138) boolean local138 = false;
		if (local21 - local136 <= 50) {
			local138 = true;
		}
		@Pc(147) boolean local147 = false;
		@Pc(155) int local155;
		@Pc(204) int local204;
		@Pc(208) int local208;
		if (arg8 > 0 && allowInput) {
			local155 = local21 - local28;
			if (local155 <= 50) {
				local155 = 50;
			}
			if (local49 > 0) {
				local56 /= local32;
				local69 /= local155;
			} else {
				local69 /= local32;
				local56 /= local155;
			}
			if (local86 > 0) {
				local121 /= local32;
				local99 /= local155;
			} else {
				local99 /= local32;
				local121 /= local155;
			}
			local204 = cursorX - Draw3D.centerX3D;
			local208 = cursorY - Draw3D.centerY3D;
			if (local204 > local56 && local204 < local69 && local208 > local121 && local208 < local99) {
				if (this.pickable) {
					hoveredBitsets[resourceCount++] = arg8;
				} else {
					local147 = true;
				}
			}
		}
		local155 = Draw3D.centerX3D;
		local204 = Draw3D.centerY3D;
		local208 = 0;
		@Pc(243) int local243 = 0;
		if (arg0 != 0) {
			local208 = sin[arg0];
			local243 = cos[arg0];
		}
		for (@Pc(255) int local255 = 0; local255 < this.vertexCount; local255++) {
			@Pc(262) int local262 = this.vertexX[local255];
			@Pc(267) int local267 = this.vertexY[local255];
			@Pc(272) int local272 = this.vertexZ[local255];
			@Pc(284) int local284;
			if (arg0 != 0) {
				local284 = local272 * local208 + local262 * local243 >> 16;
				local272 = local272 * local243 - local262 * local208 >> 16;
				local262 = local284;
			}
			local262 += arg5;
			local267 += arg6;
			local272 += arg7;
			local284 = local272 * arg3 + local262 * arg4 >> 16;
			local272 = local272 * arg4 - local262 * arg3 >> 16;
			local262 = local284;
			local284 = local267 * arg2 - local272 * arg1 >> 16;
			local272 = local267 * arg1 + local272 * arg2 >> 16;
			vertexDepth[local255] = local272 - local21;
			if (local272 >= 50) {
				vertexScreenX[local255] = local155 + (local262 << 9) / local272;
				vertexScreenY[local255] = local204 + (local284 << 9) / local272;
			} else {
				vertexScreenX[local255] = -5000;
				local138 = true;
			}
			if (local138 || this.texturedCount > 0) {
				projectSceneX[local255] = local262;
				projectSceneY[local255] = local284;
				projectSceneZ[local255] = local272;
			}
		}
		try {
			this.draw(local138, local147, arg8);
		} catch (@Pc(418) Exception local418) {
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(ZZI)V")
	private void draw(@OriginalArg(0) boolean arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2) {
		for (@Pc(3) int local3 = 0; local3 < this.maxDepth; local3++) {
			depthTriangleCount[local3] = 0;
		}
		@Pc(32) int local32;
		@Pc(37) int local37;
		@Pc(42) int local42;
		@Pc(46) int local46;
		@Pc(50) int local50;
		@Pc(54) int local54;
		@Pc(86) int local86;
		for (@Pc(16) int local16 = 0; local16 < this.triangleCount; local16++) {
			if (this.triangleInfo == null || this.triangleInfo[local16] != -1) {
				local32 = this.triangleVertexA[local16];
				local37 = this.triangleVertexB[local16];
				local42 = this.triangleVertexC[local16];
				local46 = vertexScreenX[local32];
				local50 = vertexScreenX[local37];
				local54 = vertexScreenX[local42];
				if (arg0 && (local46 == -5000 || local50 == -5000 || local54 == -5000)) {
					projectTriangle[local16] = true;
					local86 = (vertexDepth[local32] + vertexDepth[local37] + vertexDepth[local42]) / 3 + this.minDepth;
					depthTriangles[local86][depthTriangleCount[local86]++] = local16;
				} else {
					if (arg1 && this.pointWithinTriangle(cursorX, cursorY, vertexScreenY[local32], vertexScreenY[local37], vertexScreenY[local42], local46, local50, local54)) {
						hoveredBitsets[resourceCount++] = arg2;
						arg1 = false;
					}
					if ((local46 - local50) * (vertexScreenY[local42] - vertexScreenY[local37]) - (vertexScreenY[local32] - vertexScreenY[local37]) * (local54 - local50) > 0) {
						projectTriangle[local16] = false;
						if (local46 >= 0 && local50 >= 0 && local54 >= 0 && local46 <= Draw2D.safeX && local50 <= Draw2D.safeX && local54 <= Draw2D.safeX) {
							testTriangleX[local16] = false;
						} else {
							testTriangleX[local16] = true;
						}
						local86 = (vertexDepth[local32] + vertexDepth[local37] + vertexDepth[local42]) / 3 + this.minDepth;
						depthTriangles[local86][depthTriangleCount[local86]++] = local16;
					}
				}
			}
		}
		if (this.trianglePriorities == null) {
			for (local32 = this.maxDepth - 1; local32 >= 0; local32--) {
				local37 = depthTriangleCount[local32];
				if (local37 > 0) {
					@Pc(238) int[] local238 = depthTriangles[local32];
					for (local46 = 0; local46 < local37; local46++) {
						this.drawTriangle(local238[local46]);
					}
				}
			}
			return;
		}
		for (local32 = 0; local32 < 12; local32++) {
			priorityTriangleCounts[local32] = 0;
			lowTrianglePriority[local32] = 0;
		}
		@Pc(310) int local310;
		for (local37 = this.maxDepth - 1; local37 >= 0; local37--) {
			local42 = depthTriangleCount[local37];
			if (local42 > 0) {
				@Pc(288) int[] local288 = depthTriangles[local37];
				for (local50 = 0; local50 < local42; local50++) {
					local54 = local288[local50];
					local86 = this.trianglePriorities[local54];
					local310 = priorityTriangleCounts[local86]++;
					priorityTriangles[local86][local310] = local54;
					if (local86 < 10) {
						lowTrianglePriority[local86] += local37;
					} else if (local86 == 10) {
						normalTrianglePriority[local310] = local37;
					} else {
						highTrianglePriority[local310] = local37;
					}
				}
			}
		}
		local42 = 0;
		if (priorityTriangleCounts[1] > 0 || priorityTriangleCounts[2] > 0) {
			local42 = (lowTrianglePriority[1] + lowTrianglePriority[2]) / (priorityTriangleCounts[1] + priorityTriangleCounts[2]);
		}
		local46 = 0;
		if (priorityTriangleCounts[3] > 0 || priorityTriangleCounts[4] > 0) {
			local46 = (lowTrianglePriority[3] + lowTrianglePriority[4]) / (priorityTriangleCounts[3] + priorityTriangleCounts[4]);
		}
		local50 = 0;
		if (priorityTriangleCounts[6] > 0 || priorityTriangleCounts[8] > 0) {
			local50 = (lowTrianglePriority[6] + lowTrianglePriority[8]) / (priorityTriangleCounts[6] + priorityTriangleCounts[8]);
		}
		local86 = 0;
		local310 = priorityTriangleCounts[10];
		@Pc(436) int[] local436 = priorityTriangles[10];
		@Pc(438) int[] local438 = normalTrianglePriority;
		if (local86 == local310) {
			local86 = 0;
			local310 = priorityTriangleCounts[11];
			local436 = priorityTriangles[11];
			local438 = highTrianglePriority;
		}
		if (local86 < local310) {
			local54 = local438[local86];
		} else {
			local54 = -1000;
		}
		for (@Pc(466) int local466 = 0; local466 < 10; local466++) {
			while (local466 == 0 && local54 > local42) {
				this.drawTriangle(local436[local86++]);
				if (local86 == local310 && local436 != priorityTriangles[11]) {
					local86 = 0;
					local310 = priorityTriangleCounts[11];
					local436 = priorityTriangles[11];
					local438 = highTrianglePriority;
				}
				if (local86 < local310) {
					local54 = local438[local86];
				} else {
					local54 = -1000;
				}
			}
			while (local466 == 3 && local54 > local46) {
				this.drawTriangle(local436[local86++]);
				if (local86 == local310 && local436 != priorityTriangles[11]) {
					local86 = 0;
					local310 = priorityTriangleCounts[11];
					local436 = priorityTriangles[11];
					local438 = highTrianglePriority;
				}
				if (local86 < local310) {
					local54 = local438[local86];
				} else {
					local54 = -1000;
				}
			}
			while (local466 == 5 && local54 > local50) {
				this.drawTriangle(local436[local86++]);
				if (local86 == local310 && local436 != priorityTriangles[11]) {
					local86 = 0;
					local310 = priorityTriangleCounts[11];
					local436 = priorityTriangles[11];
					local438 = highTrianglePriority;
				}
				if (local86 < local310) {
					local54 = local438[local86];
				} else {
					local54 = -1000;
				}
			}
			@Pc(604) int local604 = priorityTriangleCounts[local466];
			@Pc(608) int[] local608 = priorityTriangles[local466];
			for (@Pc(610) int local610 = 0; local610 < local604; local610++) {
				this.drawTriangle(local608[local610]);
			}
		}
		while (local54 != -1000) {
			this.drawTriangle(local436[local86++]);
			if (local86 == local310 && local436 != priorityTriangles[11]) {
				local86 = 0;
				local436 = priorityTriangles[11];
				local310 = priorityTriangleCounts[11];
				local438 = highTrianglePriority;
			}
			if (local86 < local310) {
				local54 = local438[local86];
			} else {
				local54 = -1000;
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "f", descriptor = "(I)V")
	private void drawTriangle(@OriginalArg(0) int arg0) {
		if (projectTriangle[arg0]) {
			this.drawProjectedTriangle(arg0);
			return;
		}
		@Pc(14) int local14 = this.triangleVertexA[arg0];
		@Pc(19) int local19 = this.triangleVertexB[arg0];
		@Pc(24) int local24 = this.triangleVertexC[arg0];
		Draw3D.testX = testTriangleX[arg0];
		if (this.triangleAlpha == null) {
			Draw3D.alpha = 0;
		} else {
			Draw3D.alpha = this.triangleAlpha[arg0];
		}
		@Pc(45) int local45;
		if (this.triangleInfo == null) {
			local45 = 0;
		} else {
			local45 = this.triangleInfo[arg0] & 0x3;
		}
		if (local45 == 0) {
			Draw3D.fillGouraudTriangle(vertexScreenY[local14], vertexScreenY[local19], vertexScreenY[local24], vertexScreenX[local14], vertexScreenX[local19], vertexScreenX[local24], this.colorA[arg0], this.colorB[arg0], this.colorC[arg0]);
		} else if (local45 == 1) {
			Draw3D.fillTriangle(vertexScreenY[local14], vertexScreenY[local19], vertexScreenY[local24], vertexScreenX[local14], vertexScreenX[local19], vertexScreenX[local24], palette[this.colorA[arg0]]);
		} else {
			@Pc(127) int local127;
			@Pc(132) int local132;
			@Pc(137) int local137;
			@Pc(142) int local142;
			if (local45 == 2) {
				local127 = this.triangleInfo[arg0] >> 2;
				local132 = this.textureVertexA[local127];
				local137 = this.textureVertexB[local127];
				local142 = this.textureVertexC[local127];
				Draw3D.fillTexturedTriangle(vertexScreenY[local14], vertexScreenY[local19], vertexScreenY[local24], vertexScreenX[local14], vertexScreenX[local19], vertexScreenX[local24], this.colorA[arg0], this.colorB[arg0], this.colorC[arg0], projectSceneX[local132], projectSceneX[local137], projectSceneX[local142], projectSceneY[local132], projectSceneY[local137], projectSceneY[local142], projectSceneZ[local132], projectSceneZ[local137], projectSceneZ[local142], this.unmodifiedTriangleColor[arg0]);
			} else if (local45 == 3) {
				local127 = this.triangleInfo[arg0] >> 2;
				local132 = this.textureVertexA[local127];
				local137 = this.textureVertexB[local127];
				local142 = this.textureVertexC[local127];
				Draw3D.fillTexturedTriangle(vertexScreenY[local14], vertexScreenY[local19], vertexScreenY[local24], vertexScreenX[local14], vertexScreenX[local19], vertexScreenX[local24], this.colorA[arg0], this.colorA[arg0], this.colorA[arg0], projectSceneX[local132], projectSceneX[local137], projectSceneX[local142], projectSceneY[local132], projectSceneY[local137], projectSceneY[local142], projectSceneZ[local132], projectSceneZ[local137], projectSceneZ[local142], this.unmodifiedTriangleColor[arg0]);
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "g", descriptor = "(I)V")
	private void drawProjectedTriangle(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = Draw3D.centerX3D;
		@Pc(5) int local5 = Draw3D.centerY3D;
		@Pc(7) int local7 = 0;
		@Pc(12) int local12 = this.triangleVertexA[arg0];
		@Pc(17) int local17 = this.triangleVertexB[arg0];
		@Pc(22) int local22 = this.triangleVertexC[arg0];
		@Pc(26) int local26 = projectSceneZ[local12];
		@Pc(30) int local30 = projectSceneZ[local17];
		@Pc(34) int local34 = projectSceneZ[local22];
		@Pc(63) int local63;
		@Pc(67) int local67;
		@Pc(72) int local72;
		@Pc(85) int local85;
		if (local26 >= 50) {
			tmpX[local7] = vertexScreenX[local12];
			tmpY[local7] = vertexScreenY[local12];
			tmpZ[local7++] = this.colorA[arg0];
		} else {
			local63 = projectSceneX[local12];
			local67 = projectSceneY[local12];
			local72 = this.colorA[arg0];
			if (local34 >= 50) {
				local85 = (50 - local26) * reciprical16[local34 - local26];
				tmpX[local7] = local3 + (local63 + ((projectSceneX[local22] - local63) * local85 >> 16) << 9) / 50;
				tmpY[local7] = local5 + (local67 + ((projectSceneY[local22] - local67) * local85 >> 16) << 9) / 50;
				tmpZ[local7++] = local72 + ((this.colorC[arg0] - local72) * local85 >> 16);
			}
			if (local30 >= 50) {
				local85 = (50 - local26) * reciprical16[local30 - local26];
				tmpX[local7] = local3 + (local63 + ((projectSceneX[local17] - local63) * local85 >> 16) << 9) / 50;
				tmpY[local7] = local5 + (local67 + ((projectSceneY[local17] - local67) * local85 >> 16) << 9) / 50;
				tmpZ[local7++] = local72 + ((this.colorB[arg0] - local72) * local85 >> 16);
			}
		}
		if (local30 >= 50) {
			tmpX[local7] = vertexScreenX[local17];
			tmpY[local7] = vertexScreenY[local17];
			tmpZ[local7++] = this.colorB[arg0];
		} else {
			local63 = projectSceneX[local17];
			local67 = projectSceneY[local17];
			local72 = this.colorB[arg0];
			if (local26 >= 50) {
				local85 = (50 - local30) * reciprical16[local26 - local30];
				tmpX[local7] = local3 + (local63 + ((projectSceneX[local12] - local63) * local85 >> 16) << 9) / 50;
				tmpY[local7] = local5 + (local67 + ((projectSceneY[local12] - local67) * local85 >> 16) << 9) / 50;
				tmpZ[local7++] = local72 + ((this.colorA[arg0] - local72) * local85 >> 16);
			}
			if (local34 >= 50) {
				local85 = (50 - local30) * reciprical16[local34 - local30];
				tmpX[local7] = local3 + (local63 + ((projectSceneX[local22] - local63) * local85 >> 16) << 9) / 50;
				tmpY[local7] = local5 + (local67 + ((projectSceneY[local22] - local67) * local85 >> 16) << 9) / 50;
				tmpZ[local7++] = local72 + ((this.colorC[arg0] - local72) * local85 >> 16);
			}
		}
		if (local34 >= 50) {
			tmpX[local7] = vertexScreenX[local22];
			tmpY[local7] = vertexScreenY[local22];
			tmpZ[local7++] = this.colorC[arg0];
		} else {
			local63 = projectSceneX[local22];
			local67 = projectSceneY[local22];
			local72 = this.colorC[arg0];
			if (local30 >= 50) {
				local85 = (50 - local34) * reciprical16[local30 - local34];
				tmpX[local7] = local3 + (local63 + ((projectSceneX[local17] - local63) * local85 >> 16) << 9) / 50;
				tmpY[local7] = local5 + (local67 + ((projectSceneY[local17] - local67) * local85 >> 16) << 9) / 50;
				tmpZ[local7++] = local72 + ((this.colorB[arg0] - local72) * local85 >> 16);
			}
			if (local26 >= 50) {
				local85 = (50 - local34) * reciprical16[local26 - local34];
				tmpX[local7] = local3 + (local63 + ((projectSceneX[local12] - local63) * local85 >> 16) << 9) / 50;
				tmpY[local7] = local5 + (local67 + ((projectSceneY[local12] - local67) * local85 >> 16) << 9) / 50;
				tmpZ[local7++] = local72 + ((this.colorA[arg0] - local72) * local85 >> 16);
			}
		}
		local63 = tmpX[0];
		local67 = tmpX[1];
		local72 = tmpX[2];
		local85 = tmpY[0];
		@Pc(582) int local582 = tmpY[1];
		@Pc(586) int local586 = tmpY[2];
		if ((local63 - local67) * (local586 - local582) - (local85 - local582) * (local72 - local67) <= 0) {
			return;
		}
		Draw3D.testX = false;
		@Pc(629) int local629;
		@Pc(686) int local686;
		@Pc(691) int local691;
		@Pc(696) int local696;
		@Pc(701) int local701;
		if (local7 == 3) {
			if (local63 < 0 || local67 < 0 || local72 < 0 || local63 > Draw2D.safeX || local67 > Draw2D.safeX || local72 > Draw2D.safeX) {
				Draw3D.testX = true;
			}
			if (this.triangleInfo == null) {
				local629 = 0;
			} else {
				local629 = this.triangleInfo[arg0] & 0x3;
			}
			if (local629 == 0) {
				Draw3D.fillGouraudTriangle(local85, local582, local586, local63, local67, local72, tmpZ[0], tmpZ[1], tmpZ[2]);
			} else if (local629 == 1) {
				Draw3D.fillTriangle(local85, local582, local586, local63, local67, local72, palette[this.colorA[arg0]]);
			} else if (local629 == 2) {
				local686 = this.triangleInfo[arg0] >> 2;
				local691 = this.textureVertexA[local686];
				local696 = this.textureVertexB[local686];
				local701 = this.textureVertexC[local686];
				Draw3D.fillTexturedTriangle(local85, local582, local586, local63, local67, local72, tmpZ[0], tmpZ[1], tmpZ[2], projectSceneX[local691], projectSceneX[local696], projectSceneX[local701], projectSceneY[local691], projectSceneY[local696], projectSceneY[local701], projectSceneZ[local691], projectSceneZ[local696], projectSceneZ[local701], this.unmodifiedTriangleColor[arg0]);
			} else if (local629 == 3) {
				local686 = this.triangleInfo[arg0] >> 2;
				local691 = this.textureVertexA[local686];
				local696 = this.textureVertexB[local686];
				local701 = this.textureVertexC[local686];
				Draw3D.fillTexturedTriangle(local85, local582, local586, local63, local67, local72, this.colorA[arg0], this.colorA[arg0], this.colorA[arg0], projectSceneX[local691], projectSceneX[local696], projectSceneX[local701], projectSceneY[local691], projectSceneY[local696], projectSceneY[local701], projectSceneZ[local691], projectSceneZ[local696], projectSceneZ[local701], this.unmodifiedTriangleColor[arg0]);
			}
		}
		if (local7 != 4) {
			return;
		}
		if (local63 < 0 || local67 < 0 || local72 < 0 || local63 > Draw2D.safeX || local67 > Draw2D.safeX || local72 > Draw2D.safeX || tmpX[3] < 0 || tmpX[3] > Draw2D.safeX) {
			Draw3D.testX = true;
		}
		if (this.triangleInfo == null) {
			local629 = 0;
		} else {
			local629 = this.triangleInfo[arg0] & 0x3;
		}
		if (local629 == 0) {
			Draw3D.fillGouraudTriangle(local85, local582, local586, local63, local67, local72, tmpZ[0], tmpZ[1], tmpZ[2]);
			Draw3D.fillGouraudTriangle(local85, local586, tmpY[3], local63, local72, tmpX[3], tmpZ[0], tmpZ[2], tmpZ[3]);
			return;
		}
		if (local629 == 1) {
			local686 = palette[this.colorA[arg0]];
			Draw3D.fillTriangle(local85, local582, local586, local63, local67, local72, local686);
			Draw3D.fillTriangle(local85, local586, tmpY[3], local63, local72, tmpX[3], local686);
			return;
		}
		if (local629 == 2) {
			local686 = this.triangleInfo[arg0] >> 2;
			local691 = this.textureVertexA[local686];
			local696 = this.textureVertexB[local686];
			local701 = this.textureVertexC[local686];
			Draw3D.fillTexturedTriangle(local85, local582, local586, local63, local67, local72, tmpZ[0], tmpZ[1], tmpZ[2], projectSceneX[local691], projectSceneX[local696], projectSceneX[local701], projectSceneY[local691], projectSceneY[local696], projectSceneY[local701], projectSceneZ[local691], projectSceneZ[local696], projectSceneZ[local701], this.unmodifiedTriangleColor[arg0]);
			Draw3D.fillTexturedTriangle(local85, local586, tmpY[3], local63, local72, tmpX[3], tmpZ[0], tmpZ[2], tmpZ[3], projectSceneX[local691], projectSceneX[local696], projectSceneX[local701], projectSceneY[local691], projectSceneY[local696], projectSceneY[local701], projectSceneZ[local691], projectSceneZ[local696], projectSceneZ[local701], this.unmodifiedTriangleColor[arg0]);
			return;
		}
		if (local629 != 3) {
			return;
		}
		local686 = this.triangleInfo[arg0] >> 2;
		local691 = this.textureVertexA[local686];
		local696 = this.textureVertexB[local686];
		local701 = this.textureVertexC[local686];
		Draw3D.fillTexturedTriangle(local85, local582, local586, local63, local67, local72, this.colorA[arg0], this.colorA[arg0], this.colorA[arg0], projectSceneX[local691], projectSceneX[local696], projectSceneX[local701], projectSceneY[local691], projectSceneY[local696], projectSceneY[local701], projectSceneZ[local691], projectSceneZ[local696], projectSceneZ[local701], this.unmodifiedTriangleColor[arg0]);
		Draw3D.fillTexturedTriangle(local85, local586, tmpY[3], local63, local72, tmpX[3], this.colorA[arg0], this.colorA[arg0], this.colorA[arg0], projectSceneX[local691], projectSceneX[local696], projectSceneX[local701], projectSceneY[local691], projectSceneY[local696], projectSceneY[local701], projectSceneZ[local691], projectSceneZ[local696], projectSceneZ[local701], this.unmodifiedTriangleColor[arg0]);
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIIII)Z")
	private boolean pointWithinTriangle(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		if (arg1 < arg2 && arg1 < arg3 && arg1 < arg4) {
			return false;
		} else if (arg1 > arg2 && arg1 > arg3 && arg1 > arg4) {
			return false;
		} else if (arg0 < arg5 && arg0 < arg6 && arg0 < arg7) {
			return false;
		} else {
			return arg0 <= arg5 || arg0 <= arg6 || arg0 <= arg7;
		}
	}
}
