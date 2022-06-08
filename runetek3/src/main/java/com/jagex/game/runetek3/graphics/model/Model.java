package com.jagex.game.runetek3.graphics.model;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.utils.CacheableNode;
import com.jagex.game.runetek3.graphics.*;
import com.jagex.game.runetek3.graphics.seq.SeqBase;
import com.jagex.game.runetek3.graphics.seq.SeqFrame;
import com.jagex.game.runetek3.scenegraph.Scene;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!eb")
public class Model extends CacheableNode {

	@OriginalMember(owner = "client!eb", name = "Y", descriptor = "[Lclient!l;")
	private static Metadata[] metadata;

	@OriginalMember(owner = "client!eb", name = "Z", descriptor = "Lclient!kb;")
	private static Buffer head;

	@OriginalMember(owner = "client!eb", name = "ab", descriptor = "Lclient!kb;")
	private static Buffer face1;

	@OriginalMember(owner = "client!eb", name = "bb", descriptor = "Lclient!kb;")
	private static Buffer face2;

	@OriginalMember(owner = "client!eb", name = "cb", descriptor = "Lclient!kb;")
	private static Buffer face3;

	@OriginalMember(owner = "client!eb", name = "db", descriptor = "Lclient!kb;")
	private static Buffer face4;

	@OriginalMember(owner = "client!eb", name = "eb", descriptor = "Lclient!kb;")
	private static Buffer face5;

	@OriginalMember(owner = "client!eb", name = "fb", descriptor = "Lclient!kb;")
	private static Buffer point1;

	@OriginalMember(owner = "client!eb", name = "gb", descriptor = "Lclient!kb;")
	private static Buffer point2;

	@OriginalMember(owner = "client!eb", name = "hb", descriptor = "Lclient!kb;")
	private static Buffer point3;

	@OriginalMember(owner = "client!eb", name = "ib", descriptor = "Lclient!kb;")
	private static Buffer point4;

	@OriginalMember(owner = "client!eb", name = "jb", descriptor = "Lclient!kb;")
	private static Buffer point5;

	@OriginalMember(owner = "client!eb", name = "kb", descriptor = "Lclient!kb;")
	private static Buffer vertex1;

	@OriginalMember(owner = "client!eb", name = "lb", descriptor = "Lclient!kb;")
	private static Buffer vertex2;

	@OriginalMember(owner = "client!eb", name = "mb", descriptor = "Lclient!kb;")
	private static Buffer axis;

	@OriginalMember(owner = "client!eb", name = "Fb", descriptor = "I")
	private static int transformX;

	@OriginalMember(owner = "client!eb", name = "Gb", descriptor = "I")
	private static int transformY;

	@OriginalMember(owner = "client!eb", name = "Hb", descriptor = "I")
	private static int transformZ;

	@OriginalMember(owner = "client!eb", name = "Ib", descriptor = "Z")
	public static boolean allowInput;

	@OriginalMember(owner = "client!eb", name = "Jb", descriptor = "I")
	public static int cursorX;

	@OriginalMember(owner = "client!eb", name = "Kb", descriptor = "I")
	public static int cursorY;

	@OriginalMember(owner = "client!eb", name = "Lb", descriptor = "I")
	public static int resourceCount;

	@OriginalMember(owner = "client!eb", name = "nb", descriptor = "[Z")
	private static boolean[] testTriangleX = new boolean[4096];

	@OriginalMember(owner = "client!eb", name = "ob", descriptor = "[Z")
	private static boolean[] projectTriangle = new boolean[4096];

	@OriginalMember(owner = "client!eb", name = "pb", descriptor = "[I")
	private static int[] vertexScreenX = new int[4096];

	@OriginalMember(owner = "client!eb", name = "qb", descriptor = "[I")
	private static int[] vertexScreenY = new int[4096];

	@OriginalMember(owner = "client!eb", name = "rb", descriptor = "[I")
	private static int[] vertexDepth = new int[4096];

	@OriginalMember(owner = "client!eb", name = "sb", descriptor = "[I")
	private static int[] projectSceneX = new int[4096];

	@OriginalMember(owner = "client!eb", name = "tb", descriptor = "[I")
	private static int[] projectSceneY = new int[4096];

	@OriginalMember(owner = "client!eb", name = "ub", descriptor = "[I")
	private static int[] projectSceneZ = new int[4096];

	@OriginalMember(owner = "client!eb", name = "vb", descriptor = "[I")
	private static int[] depthTriangleCount = new int[1500];

	@OriginalMember(owner = "client!eb", name = "wb", descriptor = "[[I")
	private static int[][] depthTriangles = new int[1500][512];

	@OriginalMember(owner = "client!eb", name = "xb", descriptor = "[I")
	private static int[] priorityTriangleCounts = new int[12];

	@OriginalMember(owner = "client!eb", name = "yb", descriptor = "[[I")
	private static int[][] priorityTriangles = new int[12][2000];

	@OriginalMember(owner = "client!eb", name = "zb", descriptor = "[I")
	private static int[] normalTrianglePriority = new int[2000];

	@OriginalMember(owner = "client!eb", name = "Ab", descriptor = "[I")
	private static int[] highTrianglePriority = new int[2000];

	@OriginalMember(owner = "client!eb", name = "Bb", descriptor = "[I")
	private static int[] lowTrianglePriority = new int[12];

	@OriginalMember(owner = "client!eb", name = "Cb", descriptor = "[I")
	private static final int[] tmpX = new int[10];

	@OriginalMember(owner = "client!eb", name = "Db", descriptor = "[I")
	private static final int[] tmpY = new int[10];

	@OriginalMember(owner = "client!eb", name = "Eb", descriptor = "[I")
	private static final int[] tmpZ = new int[10];

	@OriginalMember(owner = "client!eb", name = "Mb", descriptor = "[I")
	public static final int[] hoveredBitsets = new int[1000];

	@OriginalMember(owner = "client!eb", name = "Nb", descriptor = "[I")
	public static int[] sin = Draw3D.sin;

	@OriginalMember(owner = "client!eb", name = "Ob", descriptor = "[I")
	public static int[] cos = Draw3D.cos;

	@OriginalMember(owner = "client!eb", name = "Pb", descriptor = "[I")
	private static int[] palette = Draw3D.palette;

	@OriginalMember(owner = "client!eb", name = "Qb", descriptor = "[I")
	private static int[] oneOverFixed1616 = Draw3D.reciprical16;

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
	public int anInt372;

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
		oneOverFixed1616 = null;
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(ILclient!ub;)V")
	public static void decode(@OriginalArg(1) FileArchive archive) {
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
			@Pc(172) int count = head.g2();
			metadata = new Metadata[count + 100];
			@Pc(179) int local179 = 0;
			@Pc(181) int local181 = 0;
			@Pc(183) int local183 = 0;
			@Pc(185) int local185 = 0;
			@Pc(187) int local187 = 0;
			@Pc(189) int local189 = 0;
			@Pc(191) int local191 = 0;
			for (@Pc(193) int n = 0; n < count; n++) {
				@Pc(198) int index = head.g2();
				@Pc(206) Metadata meta = metadata[index] = new Metadata();
				meta.vertexCount = head.g2();
				meta.triangleCount = head.g2();
				meta.texturedCount = head.g1();
				meta.vertexFlagDataOffset = point1.pos;
				meta.vertexXDataOffset = point2.pos;
				meta.vertexYDataOffset = point3.pos;
				meta.vertexZDataOffset = point4.pos;
				meta.vertexIndexDataOffset = vertex1.pos;
				meta.triangleTypeDataOffset = vertex2.pos;
				@Pc(245) int local245 = head.g1();
				@Pc(248) int local248 = head.g1();
				@Pc(251) int local251 = head.g1();
				@Pc(254) int local254 = head.g1();
				@Pc(257) int local257 = head.g1();
				@Pc(264) int local264;
				for (@Pc(259) int local259 = 0; local259 < meta.vertexCount; local259++) {
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
				for (local264 = 0; local264 < meta.triangleCount; local264++) {
					@Pc(297) int local297 = vertex2.g1();
					if (local297 == 1) {
						vertex1.gsmart();
						vertex1.gsmart();
					}
					vertex1.gsmart();
				}
				meta.triangleColorDataOffset = local183;
				local183 += meta.triangleCount * 2;
				if (local245 == 1) {
					meta.triangleInfoDataOffset = local185;
					local185 += meta.triangleCount;
				} else {
					meta.triangleInfoDataOffset = -1;
				}
				if (local248 == 255) {
					meta.trianglePriorityDataOffset = local187;
					local187 += meta.triangleCount;
				} else {
					meta.trianglePriorityDataOffset = -local248 - 1;
				}
				if (local251 == 1) {
					meta.triangleAlphaDataOffset = local189;
					local189 += meta.triangleCount;
				} else {
					meta.triangleAlphaDataOffset = -1;
				}
				if (local254 == 1) {
					meta.triangleSkinDataOffset = local191;
					local191 += meta.triangleCount;
				} else {
					meta.triangleSkinDataOffset = -1;
				}
				if (local257 == 1) {
					meta.vertexLabelDataOffset = local181;
					local181 += meta.vertexCount;
				} else {
					meta.vertexLabelDataOffset = -1;
				}
				meta.triangleTextureDataOffset = local179;
				local179 += meta.texturedCount;
			}
		} catch (@Pc(421) Exception ex) {
			System.out.println("Error loading model index");
			ex.printStackTrace();
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(III)I")
	private static int adjustHslLightness(@OriginalArg(0) int hsl, @OriginalArg(1) int lightness, @OriginalArg(2) int type) {
		if ((type & 0x2) == 2) {
			if (lightness < 0) {
				lightness = 0;
			} else if (lightness > 127) {
				lightness = 127;
			}
			return 127 - lightness;
		}
		lightness = lightness * (hsl & 0x7F) >> 7;
		if (lightness < 2) {
			lightness = 2;
		} else if (lightness > 126) {
			lightness = 126;
		}
		return (hsl & 0xFF80) + lightness;
	}

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "(ZI)V")
	public Model(@OriginalArg(1) int id) {
		if (metadata != null) {
			@Pc(28) Metadata meta = metadata[id];
			if (meta == null) {
				System.out.println("Error model:" + id + " not found!");
			} else {
				this.vertexCount = meta.vertexCount;
				this.triangleCount = meta.triangleCount;
				this.texturedCount = meta.texturedCount;
				this.vertexX = new int[this.vertexCount];
				this.vertexY = new int[this.vertexCount];
				this.vertexZ = new int[this.vertexCount];
				this.triangleVertexA = new int[this.triangleCount];
				this.triangleVertexB = new int[this.triangleCount];
				this.triangleVertexC = new int[this.triangleCount];
				this.textureVertexA = new int[this.texturedCount];
				this.textureVertexB = new int[this.texturedCount];
				this.textureVertexC = new int[this.texturedCount];
				if (meta.vertexLabelDataOffset >= 0) {
					this.vertexLabel = new int[this.vertexCount];
				}
				if (meta.triangleInfoDataOffset >= 0) {
					this.triangleInfo = new int[this.triangleCount];
				}
				if (meta.trianglePriorityDataOffset >= 0) {
					this.trianglePriorities = new int[this.triangleCount];
				} else {
					this.priority = -meta.trianglePriorityDataOffset - 1;
				}
				if (meta.triangleAlphaDataOffset >= 0) {
					this.triangleAlpha = new int[this.triangleCount];
				}
				if (meta.triangleSkinDataOffset >= 0) {
					this.triangleSkin = new int[this.triangleCount];
				}
				this.unmodifiedTriangleColor = new int[this.triangleCount];
				point1.pos = meta.vertexFlagDataOffset;
				point2.pos = meta.vertexXDataOffset;
				point3.pos = meta.vertexYDataOffset;
				point4.pos = meta.vertexZDataOffset;
				point5.pos = meta.vertexLabelDataOffset;
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
				face1.pos = meta.triangleColorDataOffset;
				face2.pos = meta.triangleInfoDataOffset;
				face3.pos = meta.trianglePriorityDataOffset;
				face4.pos = meta.triangleAlphaDataOffset;
				face5.pos = meta.triangleSkinDataOffset;
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
				vertex1.pos = meta.vertexIndexDataOffset;
				vertex2.pos = meta.triangleTypeDataOffset;
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
				axis.pos = meta.triangleTextureDataOffset * 6;
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
	public Model(@OriginalArg(0) Model[] arg0, @OriginalArg(2) int arg2, @OriginalArg(3) boolean obfuscator2) {
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
		for (@Pc(234) int local234 = 0; local234 < arg2; local234++) {
			@Pc(240) Model local240 = arg0[local234];
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
	public Model(@OriginalArg(0) Model from, @OriginalArg(1) boolean shareColors, @OriginalArg(2) boolean shareAlpha, @OriginalArg(4) boolean shareVertices) {
		this.vertexCount = from.vertexCount;
		this.triangleCount = from.triangleCount;
		this.texturedCount = from.texturedCount;

		@Pc(66) int i;
		if (shareVertices) {
			this.vertexX = from.vertexX;
			this.vertexY = from.vertexY;
			this.vertexZ = from.vertexZ;
		} else {
			this.vertexX = new int[this.vertexCount];
			this.vertexY = new int[this.vertexCount];
			this.vertexZ = new int[this.vertexCount];
			for (i = 0; i < this.vertexCount; i++) {
				this.vertexX[i] = from.vertexX[i];
				this.vertexY[i] = from.vertexY[i];
				this.vertexZ[i] = from.vertexZ[i];
			}
		}

		if (shareColors) {
			this.unmodifiedTriangleColor = from.unmodifiedTriangleColor;
		} else {
			this.unmodifiedTriangleColor = new int[this.triangleCount];
			for (i = 0; i < this.triangleCount; i++) {
				this.unmodifiedTriangleColor[i] = from.unmodifiedTriangleColor[i];
			}
		}

		if (shareAlpha) {
			this.triangleAlpha = from.triangleAlpha;
		} else {
			this.triangleAlpha = new int[this.triangleCount];
			if (from.triangleAlpha == null) {
				for (i = 0; i < this.triangleCount; i++) {
					this.triangleAlpha[i] = 0;
				}
			} else {
				for (i = 0; i < this.triangleCount; i++) {
					this.triangleAlpha[i] = from.triangleAlpha[i];
				}
			}
		}

		this.vertexLabel = from.vertexLabel;
		this.triangleSkin = from.triangleSkin;
		this.triangleInfo = from.triangleInfo;
		this.triangleVertexA = from.triangleVertexA;
		this.triangleVertexB = from.triangleVertexB;
		this.triangleVertexC = from.triangleVertexC;
		this.trianglePriorities = from.trianglePriorities;
		this.priority = from.priority;
		this.textureVertexA = from.textureVertexA;
		this.textureVertexB = from.textureVertexB;
		this.textureVertexC = from.textureVertexC;
	}

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "(Lclient!eb;BZZ)V")
	public Model(@OriginalArg(0) Model from, @OriginalArg(2) boolean arg2, @OriginalArg(3) boolean arg3) {
		this.vertexCount = from.vertexCount;
		this.triangleCount = from.triangleCount;
		this.texturedCount = from.texturedCount;
		@Pc(42) int local42;
		if (arg2) {
			this.vertexY = new int[this.vertexCount];
			for (local42 = 0; local42 < this.vertexCount; local42++) {
				this.vertexY[local42] = from.vertexY[local42];
			}
		} else {
			this.vertexY = from.vertexY;
		}
		if (arg3) {
			this.colorA = new int[this.triangleCount];
			this.colorB = new int[this.triangleCount];
			this.colorC = new int[this.triangleCount];
			for (local42 = 0; local42 < this.triangleCount; local42++) {
				this.colorA[local42] = from.colorA[local42];
				this.colorB[local42] = from.colorB[local42];
				this.colorC[local42] = from.colorC[local42];
			}
			this.triangleInfo = new int[this.triangleCount];
			@Pc(122) int local122;
			if (from.triangleInfo == null) {
				for (local122 = 0; local122 < this.triangleCount; local122++) {
					this.triangleInfo[local122] = 0;
				}
			} else {
				for (local122 = 0; local122 < this.triangleCount; local122++) {
					this.triangleInfo[local122] = from.triangleInfo[local122];
				}
			}
			this.vertexNormals = new VertexNormal[this.vertexCount];
			for (local122 = 0; local122 < this.vertexCount; local122++) {
				@Pc(170) VertexNormal local170 = this.vertexNormals[local122] = new VertexNormal();
				@Pc(175) VertexNormal local175 = from.vertexNormals[local122];
				local170.x = local175.x;
				local170.y = local175.y;
				local170.z = local175.z;
				local170.magnitude = local175.magnitude;
			}
			this.vertexNormalOriginal = from.vertexNormalOriginal;
		} else {
			this.colorA = from.colorA;
			this.colorB = from.colorB;
			this.colorC = from.colorC;
			this.triangleInfo = from.triangleInfo;
		}
		this.vertexX = from.vertexX;
		this.vertexZ = from.vertexZ;
		this.unmodifiedTriangleColor = from.unmodifiedTriangleColor;
		this.triangleAlpha = from.triangleAlpha;
		this.trianglePriorities = from.trianglePriorities;
		this.priority = from.priority;
		this.triangleVertexA = from.triangleVertexA;
		this.triangleVertexB = from.triangleVertexB;
		this.triangleVertexC = from.triangleVertexC;
		this.textureVertexA = from.textureVertexA;
		this.textureVertexB = from.textureVertexB;
		this.textureVertexC = from.textureVertexC;
		this.maxBoundY = from.maxBoundY;
		this.minBoundY = from.minBoundY;
		this.lengthXZ = from.lengthXZ;
		this.minDepth = from.minDepth;
		this.maxDepth = from.maxDepth;
		this.minBoundX = from.minBoundX;
		this.maxBoundZ = from.maxBoundZ;
		this.minBoundZ = from.minBoundZ;
		this.maxBoundX = from.maxBoundX;
	}

	@OriginalMember(owner = "client!eb", name = "<init>", descriptor = "(ILclient!eb;Z)V")
	public Model(@OriginalArg(1) Model from, @OriginalArg(2) boolean shareAlpha) {
		this.vertexCount = from.vertexCount;
		this.triangleCount = from.triangleCount;
		this.texturedCount = from.texturedCount;
		this.vertexX = new int[this.vertexCount];
		this.vertexY = new int[this.vertexCount];
		this.vertexZ = new int[this.vertexCount];
		for (@Pc(50) int local50 = 0; local50 < this.vertexCount; local50++) {
			this.vertexX[local50] = from.vertexX[local50];
			this.vertexY[local50] = from.vertexY[local50];
			this.vertexZ[local50] = from.vertexZ[local50];
		}
		if (shareAlpha) {
			this.triangleAlpha = from.triangleAlpha;
		} else {
			this.triangleAlpha = new int[this.triangleCount];
			@Pc(99) int local99;
			if (from.triangleAlpha == null) {
				for (local99 = 0; local99 < this.triangleCount; local99++) {
					this.triangleAlpha[local99] = 0;
				}
			} else {
				for (local99 = 0; local99 < this.triangleCount; local99++) {
					this.triangleAlpha[local99] = from.triangleAlpha[local99];
				}
			}
		}
		this.triangleInfo = from.triangleInfo;
		this.unmodifiedTriangleColor = from.unmodifiedTriangleColor;
		this.trianglePriorities = from.trianglePriorities;
		this.priority = from.priority;
		this.skinTriangle = from.skinTriangle;
		this.labelVertices = from.labelVertices;
		this.triangleVertexA = from.triangleVertexA;
		this.triangleVertexB = from.triangleVertexB;
		this.triangleVertexC = from.triangleVertexC;
		this.colorA = from.colorA;
		this.colorB = from.colorB;
		this.colorC = from.colorC;
		this.textureVertexA = from.textureVertexA;
		this.textureVertexB = from.textureVertexB;
		this.textureVertexC = from.textureVertexC;
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(Lclient!eb;I)I")
	private int copyVertex(@OriginalArg(0) Model from, @OriginalArg(1) int arg1) {
		@Pc(3) int local3 = -1;
		@Pc(8) int local8 = from.vertexX[arg1];
		@Pc(13) int local13 = from.vertexY[arg1];
		@Pc(18) int local18 = from.vertexZ[arg1];
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
			if (from.vertexLabel != null) {
				this.vertexLabel[this.vertexCount] = from.vertexLabel[arg1];
			}
			local3 = this.vertexCount++;
		}
		return local3;
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(I)V")
	public final void calculateYBoundaries() {
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
		this.lengthXZ = (int) (Math.sqrt((double) this.lengthXZ) + 0.99D);
		this.minDepth = (int) (Math.sqrt((double) (this.lengthXZ * this.lengthXZ + this.maxBoundY * this.maxBoundY)) + 0.99D);
		this.maxDepth = this.minDepth + (int) (Math.sqrt((double) (this.lengthXZ * this.lengthXZ + this.minBoundY * this.minBoundY)) + 0.99D);
	}

	@OriginalMember(owner = "client!eb", name = "b", descriptor = "(I)V")
	public final void calculateYBoundaries2() {
		this.maxBoundY = 0;
		@Pc(8) int local8;
		this.minBoundY = 0;
		for (local8 = 0; local8 < this.vertexCount; local8++) {
			@Pc(25) int local25 = this.vertexY[local8];
			if (-local25 > this.maxBoundY) {
				this.maxBoundY = -local25;
			}
			if (local25 > this.minBoundY) {
				this.minBoundY = local25;
			}
		}
		this.minDepth = (int) (Math.sqrt((double) (this.lengthXZ * this.lengthXZ + this.maxBoundY * this.maxBoundY)) + 0.99D);
		this.maxDepth = this.minDepth + (int) (Math.sqrt((double) (this.lengthXZ * this.lengthXZ + this.minBoundY * this.minBoundY)) + 0.99D);
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
		this.lengthXZ = (int) Math.sqrt((double) this.lengthXZ);
		this.minDepth = (int) Math.sqrt((double) (this.lengthXZ * this.lengthXZ + this.maxBoundY * this.maxBoundY));
		this.maxDepth = this.minDepth + (int) Math.sqrt((double) (this.lengthXZ * this.lengthXZ + this.minBoundY * this.minBoundY));
	}

	@OriginalMember(owner = "client!eb", name = "c", descriptor = "(I)V")
	public final void applyGroup() {
		@Pc(11) int[] local11;
		@Pc(13) int local13;
		@Pc(26) int local26;
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
		if (this.triangleSkin == null) {
			return;
		}
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

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(II)V")
	public void applyFrame(@OriginalArg(1) int arg0) {
		if (this.labelVertices == null || arg0 == -1) {
			return;
		}
		@Pc(11) SeqFrame local11 = SeqFrame.instances[arg0];
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
	public void applyFrames(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int[] arg2) {
		if (arg1 == -1) {
			return;
		}
		if (arg2 == null || arg0 == -1) {
			this.applyFrame(arg1);
			return;
		}
		@Pc(19) SeqFrame local19 = SeqFrame.instances[arg1];
		@Pc(32) SeqFrame local32 = SeqFrame.instances[arg0];
		@Pc(35) SeqBase local35 = local19.transform;
		transformX = 0;
		transformY = 0;
		transformZ = 0;
		@Pc(43) byte local43 = 0;
		@Pc(46) int local46 = local43 + 1;
		@Pc(48) int local48 = arg2[0];
		@Pc(57) int local57;
		for (@Pc(50) int local50 = 0; local50 < local19.groupCount; local50++) {
			local57 = local19.groups[local50];
			while (local57 > local48) {
				local48 = arg2[local46++];
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
		local48 = arg2[0];
		for (local57 = 0; local57 < local32.groupCount; local57++) {
			@Pc(124) int local124 = local32.groups[local57];
			while (local124 > local48) {
				local48 = arg2[local46++];
			}
			if (local124 == local48 || local35.types[local124] == 0) {
				this.transform(local35.types[local124], local35.groupLabels[local124], local32.x[local57], local32.y[local57], local32.z[local57]);
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
	public final void rotateCounterClockwise() {
		for (@Pc(6) int local6 = 0; local6 < this.vertexCount; local6++) {
			@Pc(13) int local13 = this.vertexX[local6];
			this.vertexX[local6] = this.vertexZ[local6];
			this.vertexZ[local6] = -local13;
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(BI)V")
	public void rotatePitch(@OriginalArg(1) int arg0) {
		@Pc(3) int local3 = sin[arg0];
		@Pc(7) int local7 = cos[arg0];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(27) int local27 = this.vertexY[local9] * local7 - this.vertexZ[local9] * local3 >> 16;
			this.vertexZ[local9] = this.vertexY[local9] * local3 + this.vertexZ[local9] * local7 >> 16;
			this.vertexY[local9] = local27;
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIII)V")
	public void translate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		for (@Pc(11) int local11 = 0; local11 < this.vertexCount; local11++) {
			this.vertexX[local11] += arg1;
			this.vertexY[local11] += arg0;
			this.vertexZ[local11] += arg2;
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
	public final void flipBackwards() {
		for (@Pc(3) int local3 = 0; local3 < this.vertexCount; local3++) {
			this.vertexZ[local3] = -this.vertexZ[local3];
		}
		for (@Pc(21) int local21 = 0; local21 < this.triangleCount; local21++) {
			@Pc(28) int local28 = this.triangleVertexA[local21];
			this.triangleVertexA[local21] = this.triangleVertexC[local21];
			this.triangleVertexC[local21] = local28;
		}
	}

	@OriginalMember(owner = "client!eb", name = "b", descriptor = "(IIII)V")
	public void scale(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			this.vertexX[local1] = this.vertexX[local1] * arg2 / 128;
			this.vertexY[local1] = this.vertexY[local1] * arg1 / 128;
			this.vertexZ[local1] = this.vertexZ[local1] * arg0 / 128;
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIZ)V")
	public void applyLighting(@OriginalArg(0) int lightAmbient, @OriginalArg(1) int lightAttenuation, @OriginalArg(2) int lightSrcX, @OriginalArg(3) int lightSrcY, @OriginalArg(4) int lightSrcZ, @OriginalArg(5) boolean computeVertexColors) {
		@Pc(16) int lightMagnitude = (int) Math.sqrt(lightSrcX * lightSrcX + lightSrcY * lightSrcY + lightSrcZ * lightSrcZ);
		@Pc(22) int attenuation = lightAttenuation * lightMagnitude >> 8;

		if (this.colorA == null) {
			this.colorA = new int[this.triangleCount];
			this.colorB = new int[this.triangleCount];
			this.colorC = new int[this.triangleCount];
		}

		@Pc(50) int t;
		if (this.vertexNormals == null) {
			this.vertexNormals = new VertexNormal[this.vertexCount];
			for (t = 0; t < this.vertexCount; t++) {
				this.vertexNormals[t] = new VertexNormal();
			}
		}

		@Pc(73) int a;
		for (t = 0; t < this.triangleCount; t++) {
			a = this.triangleVertexA[t];
			@Pc(78) int b = this.triangleVertexB[t];
			@Pc(83) int c = this.triangleVertexC[t];

			@Pc(93) int local93 = this.vertexX[b] - this.vertexX[a];
			@Pc(103) int local103 = this.vertexY[b] - this.vertexY[a];
			@Pc(113) int local113 = this.vertexZ[b] - this.vertexZ[a];
			@Pc(123) int local123 = this.vertexX[c] - this.vertexX[a];
			@Pc(133) int local133 = this.vertexY[c] - this.vertexY[a];
			@Pc(143) int local143 = this.vertexZ[c] - this.vertexZ[a];
			@Pc(151) int local151 = local103 * local143 - local133 * local113;
			@Pc(159) int local159 = local113 * local123 - local143 * local93;
			@Pc(167) int local167;
			for (local167 = local93 * local133 - local123 * local103; local151 > 8192 || local159 > 8192 || local167 > 8192 || local151 < -8192 || local159 < -8192 || local167 < -8192; local167 >>= 0x1) {
				local151 >>= 0x1;
				local159 >>= 0x1;
			}
			@Pc(214) int local214 = (int) Math.sqrt((double) (local151 * local151 + local159 * local159 + local167 * local167));
			if (local214 <= 0) {
				local214 = 1;
			}
			local151 = local151 * 256 / local214;
			local159 = local159 * 256 / local214;
			local167 = local167 * 256 / local214;
			if (this.triangleInfo == null || (this.triangleInfo[t] & 0x1) == 0) {
				@Pc(251) VertexNormal local251 = this.vertexNormals[a];
				local251.x += local151;
				local251.y += local159;
				local251.z += local167;
				local251.magnitude++;
				@Pc(280) VertexNormal local280 = this.vertexNormals[b];
				local280.x += local151;
				local280.y += local159;
				local280.z += local167;
				local280.magnitude++;
				@Pc(309) VertexNormal local309 = this.vertexNormals[c];
				local309.x += local151;
				local309.y += local159;
				local309.z += local167;
				local309.magnitude++;
			} else {
				@Pc(355) int local355 = lightAmbient + (lightSrcX * local151 + lightSrcY * local159 + lightSrcZ * local167) / (attenuation + attenuation / 2);
				this.colorA[t] = adjustHslLightness(this.unmodifiedTriangleColor[t], local355, this.triangleInfo[t]);
			}
		}
		if (computeVertexColors) {
			this.calculateLighting(lightAmbient, attenuation, lightSrcX, lightSrcY, lightSrcZ);
		} else {
			this.vertexNormalOriginal = new VertexNormal[this.vertexCount];
			for (a = 0; a < this.vertexCount; a++) {
				@Pc(399) VertexNormal local399 = this.vertexNormals[a];
				@Pc(408) VertexNormal local408 = this.vertexNormalOriginal[a] = new VertexNormal();
				local408.x = local399.x;
				local408.y = local399.y;
				local408.z = local399.z;
				local408.magnitude = local399.magnitude;
			}
		}
		if (computeVertexColors) {
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
	public void draw(@OriginalArg(1) int yaw, @OriginalArg(2) int roll, @OriginalArg(3) int cameraPitch, @OriginalArg(4) int cameraX, @OriginalArg(5) int cameraY, @OriginalArg(6) int cameraZ) {
		@Pc(1) int centerX = Draw3D.centerX3D;
		@Pc(3) int centerY = Draw3D.centerY3D;
		@Pc(7) int pitchsin = sin[0];
		@Pc(11) int pitchcos = cos[0];
		@Pc(15) int yawsin = sin[yaw];
		@Pc(19) int yawcos = cos[yaw];
		@Pc(23) int rollsin = sin[roll];
		@Pc(27) int rollcos = cos[roll];
		@Pc(31) int cpitchsin = sin[cameraPitch];
		@Pc(35) int cpitchcos = cos[cameraPitch];
		@Pc(45) int depth = cameraY * cpitchsin + cameraZ * cpitchcos >> 16;
		for (@Pc(47) int v = 0; v < this.vertexCount; v++) {
			@Pc(54) int x = this.vertexX[v];
			@Pc(59) int y = this.vertexY[v];
			@Pc(64) int z = this.vertexZ[v];
			@Pc(76) int z0;
			if (roll != 0) {
				z0 = y * rollsin + x * rollcos >> 16;
				y = y * rollcos - x * rollsin >> 16;
				x = z0;
			}
			if (yaw != 0) {
				z0 = z * yawsin + x * yawcos >> 16;
				z = z * yawcos - x * yawsin >> 16;
				x = z0;
			}
			x += cameraX;
			y += cameraY;
			z += cameraZ;
			z0 = y * cpitchcos - z * cpitchsin >> 16;
			z = y * cpitchsin + z * cpitchcos >> 16;
			vertexDepth[v] = z - depth;
			vertexScreenX[v] = centerX + (x << 9) / z;
			vertexScreenY[v] = centerY + (z0 << 9) / z;
			if (this.texturedCount > 0) {
				projectSceneX[v] = x;
				projectSceneY[v] = z0;
				projectSceneZ[v] = z;
			}
		}
		try {
			this.draw(false, false, 0);
		} catch (@Pc(223) Exception ignored) {
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIIIII)V")
	public void draw(@OriginalArg(0) int yaw, @OriginalArg(1) int sinCameraPitch, @OriginalArg(2) int cosCameraPitch, @OriginalArg(3) int sinCameraYaw, @OriginalArg(4) int cosCameraYaw, @OriginalArg(5) int sceneX, @OriginalArg(6) int sceneY, @OriginalArg(7) int sceneZ, @OriginalArg(8) int key) {
		@Pc(11) int a = sceneZ * cosCameraYaw - sceneX * sinCameraYaw >> 16;
		@Pc(21) int b = sceneY * sinCameraPitch + a * cosCameraPitch >> 16;
		@Pc(28) int c = this.lengthXZ * cosCameraPitch >> 16;
		@Pc(32) int d = b + c;
		if (d <= Scene.NEAR_Z || b >= Scene.FAR_Z) {
			return;
		}
		@Pc(49) int e = sceneZ * sinCameraYaw + sceneX * cosCameraYaw >> 16;
		@Pc(56) int minScreenX = e - this.lengthXZ << 9;
		if (minScreenX / d >= Draw2D.centerX) {
			return;
		}
		@Pc(69) int maxScreenX = e + this.lengthXZ << 9;
		if (maxScreenX / d <= -Draw2D.centerX) {
			return;
		}
		@Pc(86) int f = sceneY * cosCameraPitch - a * sinCameraPitch >> 16;
		@Pc(93) int g = this.lengthXZ * sinCameraPitch >> 16;
		@Pc(99) int maxScreenY = f + g << 9;
		if (maxScreenY / d <= -Draw2D.centerY) {
			return;
		}
		@Pc(115) int h = g + (this.maxBoundY * cosCameraPitch >> 16);
		@Pc(121) int minScreenY = f - h << 9;
		if (minScreenY / d >= Draw2D.centerY) {
			return;
		}
		@Pc(136) int i = c + (this.maxBoundY * sinCameraPitch >> 16);
		@Pc(138) boolean project = false;
		if (b - i <= Scene.NEAR_Z) {
			project = true;
		}
		@Pc(147) boolean hasInput = false;
		@Pc(155) int cx;
		@Pc(204) int cy;
		@Pc(208) int yawsin;
		if (key > 0 && allowInput) {
			cx = b - c;
			if (cx <= Scene.NEAR_Z) {
				cx = Scene.NEAR_Z;
			}
			if (e > 0) {
				minScreenX /= d;
				maxScreenX /= cx;
			} else {
				maxScreenX /= d;
				minScreenX /= cx;
			}
			if (f > 0) {
				minScreenY /= d;
				maxScreenY /= cx;
			} else {
				maxScreenY /= d;
				minScreenY /= cx;
			}
			cy = cursorX - Draw3D.centerX3D;
			yawsin = cursorY - Draw3D.centerY3D;
			if (cy > minScreenX && cy < maxScreenX && yawsin > minScreenY && yawsin < maxScreenY) {
				if (this.pickable) {
					hoveredBitsets[resourceCount++] = key;
				} else {
					hasInput = true;
				}
			}
		}
		cx = Draw3D.centerX3D;
		cy = Draw3D.centerY3D;
		yawsin = 0;
		@Pc(243) int yawcos = 0;
		if (yaw != 0) {
			yawsin = sin[yaw];
			yawcos = cos[yaw];
		}
		for (@Pc(255) int v = 0; v < this.vertexCount; v++) {
			@Pc(262) int x = this.vertexX[v];
			@Pc(267) int y = this.vertexY[v];
			@Pc(272) int z = this.vertexZ[v];
			@Pc(284) int w;
			if (yaw != 0) {
				w = z * yawsin + x * yawcos >> 16;
				z = z * yawcos - x * yawsin >> 16;
				x = w;
			}
			x += sceneX;
			y += sceneY;
			z += sceneZ;
			w = z * sinCameraYaw + x * cosCameraYaw >> 16;
			z = z * cosCameraYaw - x * sinCameraYaw >> 16;
			x = w;
			w = y * cosCameraPitch - z * sinCameraPitch >> 16;
			z = y * sinCameraPitch + z * cosCameraPitch >> 16;
			vertexDepth[v] = z - b;
			if (z >= Scene.NEAR_Z) {
				vertexScreenX[v] = cx + (x << 9) / z;
				vertexScreenY[v] = cy + (w << 9) / z;
			} else {
				vertexScreenX[v] = -5000;
				project = true;
			}
			if (project || this.texturedCount > 0) {
				projectSceneX[v] = x;
				projectSceneY[v] = w;
				projectSceneZ[v] = z;
			}
		}
		try {
			this.draw(project, hasInput, key);
		} catch (@Pc(418) Exception ignored) {
		}
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(ZZI)V")
	private void draw(@OriginalArg(0) boolean projected, @OriginalArg(1) boolean hasInput, @OriginalArg(2) int bitset) {
		for (@Pc(3) int i = 0; i < this.maxDepth; i++) {
			depthTriangleCount[i] = 0;
		}
		@Pc(32) int i;
		@Pc(37) int d;
		@Pc(42) int minPriority;
		@Pc(46) int halfPriority;
		@Pc(50) int maxPriority;
		@Pc(54) int pri;
		@Pc(86) int t;
		for (@Pc(16) int j = 0; j < this.triangleCount; j++) {
			if (this.triangleInfo == null || this.triangleInfo[j] != -1) {
				i = this.triangleVertexA[j];
				d = this.triangleVertexB[j];
				minPriority = this.triangleVertexC[j];
				halfPriority = vertexScreenX[i];
				maxPriority = vertexScreenX[d];
				pri = vertexScreenX[minPriority];
				if (projected && (halfPriority == -5000 || maxPriority == -5000 || pri == -5000)) {
					projectTriangle[j] = true;
					t = (vertexDepth[i] + vertexDepth[d] + vertexDepth[minPriority]) / 3 + this.minDepth;
					depthTriangles[t][depthTriangleCount[t]++] = j;
				} else {
					if (hasInput && this.pointWithinTriangle(cursorX, cursorY, vertexScreenY[i], vertexScreenY[d], vertexScreenY[minPriority], halfPriority, maxPriority, pri)) {
						hoveredBitsets[resourceCount++] = bitset;
						hasInput = false;
					}
					if ((halfPriority - maxPriority) * (vertexScreenY[minPriority] - vertexScreenY[d]) - (vertexScreenY[i] - vertexScreenY[d]) * (pri - maxPriority) > 0) {
						projectTriangle[j] = false;
						if (halfPriority >= 0 && maxPriority >= 0 && pri >= 0 && halfPriority <= Draw2D.safeX && maxPriority <= Draw2D.safeX && pri <= Draw2D.safeX) {
							testTriangleX[j] = false;
						} else {
							testTriangleX[j] = true;
						}
						t = (vertexDepth[i] + vertexDepth[d] + vertexDepth[minPriority]) / 3 + this.minDepth;
						depthTriangles[t][depthTriangleCount[t]++] = j;
					}
				}
			}
		}
		if (this.trianglePriorities == null) {
			for (i = this.maxDepth - 1; i >= 0; i--) {
				d = depthTriangleCount[i];
				if (d > 0) {
					@Pc(238) int[] triangles = depthTriangles[i];
					for (halfPriority = 0; halfPriority < d; halfPriority++) {
						this.drawTriangle(triangles[halfPriority]);
					}
				}
			}
			return;
		}
		for (i = 0; i < 12; i++) {
			priorityTriangleCounts[i] = 0;
			lowTrianglePriority[i] = 0;
		}
		@Pc(310) int priorityTriangleCount;
		for (d = this.maxDepth - 1; d >= 0; d--) {
			minPriority = depthTriangleCount[d];
			if (minPriority > 0) {
				@Pc(288) int[] triangles = depthTriangles[d];
				for (maxPriority = 0; maxPriority < minPriority; maxPriority++) {
					pri = triangles[maxPriority];
					t = this.trianglePriorities[pri];
					priorityTriangleCount = priorityTriangleCounts[t]++;
					priorityTriangles[t][priorityTriangleCount] = pri;
					if (t < 10) {
						lowTrianglePriority[t] += d;
					} else if (t == 10) {
						normalTrianglePriority[priorityTriangleCount] = d;
					} else {
						highTrianglePriority[priorityTriangleCount] = d;
					}
				}
			}
		}
		minPriority = 0;
		if (priorityTriangleCounts[1] > 0 || priorityTriangleCounts[2] > 0) {
			minPriority = (lowTrianglePriority[1] + lowTrianglePriority[2]) / (priorityTriangleCounts[1] + priorityTriangleCounts[2]);
		}
		halfPriority = 0;
		if (priorityTriangleCounts[3] > 0 || priorityTriangleCounts[4] > 0) {
			halfPriority = (lowTrianglePriority[3] + lowTrianglePriority[4]) / (priorityTriangleCounts[3] + priorityTriangleCounts[4]);
		}
		maxPriority = 0;
		if (priorityTriangleCounts[6] > 0 || priorityTriangleCounts[8] > 0) {
			maxPriority = (lowTrianglePriority[6] + lowTrianglePriority[8]) / (priorityTriangleCounts[6] + priorityTriangleCounts[8]);
		}
		t = 0;
		priorityTriangleCount = priorityTriangleCounts[10];
		@Pc(436) int[] triangles = priorityTriangles[10];
		@Pc(438) int[] priorities = normalTrianglePriority;
		if (priorityTriangleCount == 0) {
			t = 0;
			priorityTriangleCount = priorityTriangleCounts[11];
			triangles = priorityTriangles[11];
			priorities = highTrianglePriority;
		}
		if (priorityTriangleCount > 0) {
			pri = priorities[0];
		} else {
			pri = -1000;
		}
		for (@Pc(466) int p = 0; p < 10; p++) {
			while (p == 0 && pri > minPriority) {
				this.drawTriangle(triangles[t++]);
				if (t == priorityTriangleCount && triangles != priorityTriangles[11]) {
					t = 0;
					priorityTriangleCount = priorityTriangleCounts[11];
					triangles = priorityTriangles[11];
					priorities = highTrianglePriority;
				}
				if (t < priorityTriangleCount) {
					pri = priorities[t];
				} else {
					pri = -1000;
				}
			}
			while (p == 3 && pri > halfPriority) {
				this.drawTriangle(triangles[t++]);
				if (t == priorityTriangleCount && triangles != priorityTriangles[11]) {
					t = 0;
					priorityTriangleCount = priorityTriangleCounts[11];
					triangles = priorityTriangles[11];
					priorities = highTrianglePriority;
				}
				if (t < priorityTriangleCount) {
					pri = priorities[t];
				} else {
					pri = -1000;
				}
			}
			while (p == 5 && pri > maxPriority) {
				this.drawTriangle(triangles[t++]);
				if (t == priorityTriangleCount && triangles != priorityTriangles[11]) {
					t = 0;
					priorityTriangleCount = priorityTriangleCounts[11];
					triangles = priorityTriangles[11];
					priorities = highTrianglePriority;
				}
				if (t < priorityTriangleCount) {
					pri = priorities[t];
				} else {
					pri = -1000;
				}
			}
			@Pc(604) int n = priorityTriangleCounts[p];
			@Pc(608) int[] tris = priorityTriangles[p];
			for (@Pc(610) int m = 0; m < n; m++) {
				this.drawTriangle(tris[m]);
			}
		}
		while (pri != -1000) {
			this.drawTriangle(triangles[t++]);
			if (t == priorityTriangleCount && triangles != priorityTriangles[11]) {
				t = 0;
				triangles = priorityTriangles[11];
				priorityTriangleCount = priorityTriangleCounts[11];
				priorities = highTrianglePriority;
			}
			if (t < priorityTriangleCount) {
				pri = priorities[t];
			} else {
				pri = -1000;
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "f", descriptor = "(I)V")
	private void drawTriangle(@OriginalArg(0) int index) {
		if (projectTriangle[index]) {
			this.drawProjectedTriangle(index);
			return;
		}
		@Pc(14) int a = this.triangleVertexA[index];
		@Pc(19) int b = this.triangleVertexB[index];
		@Pc(24) int c = this.triangleVertexC[index];
		Draw3D.testX = testTriangleX[index];
		if (this.triangleAlpha == null) {
			Draw3D.alpha = 0;
		} else {
			Draw3D.alpha = this.triangleAlpha[index];
		}
		@Pc(45) int type;
		if (this.triangleInfo == null) {
			type = 0;
		} else {
			type = this.triangleInfo[index] & 0x3;
		}
		if (type == 0) {
			Draw3D.fillGouraudTriangle(vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], vertexScreenX[a], vertexScreenX[b], vertexScreenX[c], this.colorA[index], this.colorB[index], this.colorC[index]);
		} else if (type == 1) {
			Draw3D.fillTriangle(vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], vertexScreenX[a], vertexScreenX[b], vertexScreenX[c], palette[this.colorA[index]]);
		} else {
			@Pc(127) int t;
			@Pc(132) int tA;
			@Pc(137) int tB;
			@Pc(142) int tC;
			if (type == 2) {
				t = this.triangleInfo[index] >> 2;
				tA = this.textureVertexA[t];
				tB = this.textureVertexB[t];
				tC = this.textureVertexC[t];
				Draw3D.fillTexturedTriangle(vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], vertexScreenX[a], vertexScreenX[b], vertexScreenX[c], this.colorA[index], this.colorB[index], this.colorC[index], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], this.unmodifiedTriangleColor[index]);
			} else if (type == 3) {
				t = this.triangleInfo[index] >> 2;
				tA = this.textureVertexA[t];
				tB = this.textureVertexB[t];
				tC = this.textureVertexC[t];
				Draw3D.fillTexturedTriangle(vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], vertexScreenX[a], vertexScreenX[b], vertexScreenX[c], this.colorA[index], this.colorA[index], this.colorA[index], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], this.unmodifiedTriangleColor[index]);
			}
		}
	}

	@OriginalMember(owner = "client!eb", name = "g", descriptor = "(I)V")
	private void drawProjectedTriangle(@OriginalArg(0) int index) {
		@Pc(3) int centerX = Draw3D.centerX3D;
		@Pc(5) int centerY = Draw3D.centerY3D;
		@Pc(7) int n = 0;
		@Pc(12) int a = this.triangleVertexA[index];
		@Pc(17) int b = this.triangleVertexB[index];
		@Pc(22) int c = this.triangleVertexC[index];
		@Pc(26) int zA = projectSceneZ[a];
		@Pc(30) int zB = projectSceneZ[b];
		@Pc(34) int zC = projectSceneZ[c];
		@Pc(63) int xA;
		@Pc(67) int xB;
		@Pc(72) int xC;
		@Pc(85) int yA;
		if (zA >= Scene.NEAR_Z) {
			tmpX[0] = vertexScreenX[a];
			tmpY[0] = vertexScreenY[a];
			n++;
			tmpZ[0] = this.colorA[index];
		} else {
			xA = projectSceneX[a];
			xB = projectSceneY[a];
			xC = this.colorA[index];
			if (zC >= Scene.NEAR_Z) {
				yA = (Scene.NEAR_Z - zA) * oneOverFixed1616[zC - zA];
				tmpX[0] = centerX + (xA + ((projectSceneX[c] - xA) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpY[0] = centerY + (xB + ((projectSceneY[c] - xB) * yA >> 16) << 9) / Scene.NEAR_Z;
				n++;
				tmpZ[0] = xC + ((this.colorC[index] - xC) * yA >> 16);
			}
			if (zB >= Scene.NEAR_Z) {
				yA = (Scene.NEAR_Z - zA) * oneOverFixed1616[zB - zA];
				tmpX[n] = centerX + (xA + ((projectSceneX[b] - xA) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpY[n] = centerY + (xB + ((projectSceneY[b] - xB) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpZ[n++] = xC + ((this.colorB[index] - xC) * yA >> 16);
			}
		}
		if (zB >= Scene.NEAR_Z) {
			tmpX[n] = vertexScreenX[b];
			tmpY[n] = vertexScreenY[b];
			tmpZ[n++] = this.colorB[index];
		} else {
			xA = projectSceneX[b];
			xB = projectSceneY[b];
			xC = this.colorB[index];
			if (zA >= Scene.NEAR_Z) {
				yA = (Scene.NEAR_Z - zB) * oneOverFixed1616[zA - zB];
				tmpX[n] = centerX + (xA + ((projectSceneX[a] - xA) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpY[n] = centerY + (xB + ((projectSceneY[a] - xB) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpZ[n++] = xC + ((this.colorA[index] - xC) * yA >> 16);
			}
			if (zC >= Scene.NEAR_Z) {
				yA = (Scene.NEAR_Z - zB) * oneOverFixed1616[zC - zB];
				tmpX[n] = centerX + (xA + ((projectSceneX[c] - xA) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpY[n] = centerY + (xB + ((projectSceneY[c] - xB) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpZ[n++] = xC + ((this.colorC[index] - xC) * yA >> 16);
			}
		}
		if (zC >= Scene.NEAR_Z) {
			tmpX[n] = vertexScreenX[c];
			tmpY[n] = vertexScreenY[c];
			tmpZ[n++] = this.colorC[index];
		} else {
			xA = projectSceneX[c];
			xB = projectSceneY[c];
			xC = this.colorC[index];
			if (zB >= Scene.NEAR_Z) {
				yA = (Scene.NEAR_Z - zC) * oneOverFixed1616[zB - zC];
				tmpX[n] = centerX + (xA + ((projectSceneX[b] - xA) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpY[n] = centerY + (xB + ((projectSceneY[b] - xB) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpZ[n++] = xC + ((this.colorB[index] - xC) * yA >> 16);
			}
			if (zA >= Scene.NEAR_Z) {
				yA = (Scene.NEAR_Z - zC) * oneOverFixed1616[zA - zC];
				tmpX[n] = centerX + (xA + ((projectSceneX[a] - xA) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpY[n] = centerY + (xB + ((projectSceneY[a] - xB) * yA >> 16) << 9) / Scene.NEAR_Z;
				tmpZ[n++] = xC + ((this.colorA[index] - xC) * yA >> 16);
			}
		}
		xA = tmpX[0];
		xB = tmpX[1];
		xC = tmpX[2];
		yA = tmpY[0];
		@Pc(582) int yB = tmpY[1];
		@Pc(586) int yC = tmpY[2];
		if ((xA - xB) * (yC - yB) - (yA - yB) * (xC - xB) <= 0) {
			return;
		}
		Draw3D.testX = false;
		@Pc(629) int type;
		@Pc(686) int t;
		@Pc(691) int tA;
		@Pc(696) int tB;
		@Pc(701) int tC;
		if (n == 3) {
			if (xA < 0 || xB < 0 || xC < 0 || xA > Draw2D.safeX || xB > Draw2D.safeX || xC > Draw2D.safeX) {
				Draw3D.testX = true;
			}
			if (this.triangleInfo == null) {
				type = 0;
			} else {
				type = this.triangleInfo[index] & 0x3;
			}
			if (type == 0) {
				Draw3D.fillGouraudTriangle(yA, yB, yC, xA, xB, xC, tmpZ[0], tmpZ[1], tmpZ[2]);
			} else if (type == 1) {
				Draw3D.fillTriangle(yA, yB, yC, xA, xB, xC, palette[this.colorA[index]]);
			} else if (type == 2) {
				t = this.triangleInfo[index] >> 2;
				tA = this.textureVertexA[t];
				tB = this.textureVertexB[t];
				tC = this.textureVertexC[t];
				Draw3D.fillTexturedTriangle(yA, yB, yC, xA, xB, xC, tmpZ[0], tmpZ[1], tmpZ[2], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], this.unmodifiedTriangleColor[index]);
			} else if (type == 3) {
				t = this.triangleInfo[index] >> 2;
				tA = this.textureVertexA[t];
				tB = this.textureVertexB[t];
				tC = this.textureVertexC[t];
				Draw3D.fillTexturedTriangle(yA, yB, yC, xA, xB, xC, this.colorA[index], this.colorA[index], this.colorA[index], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], this.unmodifiedTriangleColor[index]);
			}
		}
		if (n != 4) {
			return;
		}
		if (xA < 0 || xB < 0 || xC < 0 || xA > Draw2D.safeX || xB > Draw2D.safeX || xC > Draw2D.safeX || tmpX[3] < 0 || tmpX[3] > Draw2D.safeX) {
			Draw3D.testX = true;
		}
		if (this.triangleInfo == null) {
			type = 0;
		} else {
			type = this.triangleInfo[index] & 0x3;
		}
		if (type == 0) {
			Draw3D.fillGouraudTriangle(yA, yB, yC, xA, xB, xC, tmpZ[0], tmpZ[1], tmpZ[2]);
			Draw3D.fillGouraudTriangle(yA, yC, tmpY[3], xA, xC, tmpX[3], tmpZ[0], tmpZ[2], tmpZ[3]);
			return;
		}
		if (type == 1) {
			t = palette[this.colorA[index]];
			Draw3D.fillTriangle(yA, yB, yC, xA, xB, xC, t);
			Draw3D.fillTriangle(yA, yC, tmpY[3], xA, xC, tmpX[3], t);
			return;
		}
		if (type == 2) {
			t = this.triangleInfo[index] >> 2;
			tA = this.textureVertexA[t];
			tB = this.textureVertexB[t];
			tC = this.textureVertexC[t];
			Draw3D.fillTexturedTriangle(yA, yB, yC, xA, xB, xC, tmpZ[0], tmpZ[1], tmpZ[2], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], this.unmodifiedTriangleColor[index]);
			Draw3D.fillTexturedTriangle(yA, yC, tmpY[3], xA, xC, tmpX[3], tmpZ[0], tmpZ[2], tmpZ[3], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], this.unmodifiedTriangleColor[index]);
			return;
		}
		if (type != 3) {
			return;
		}
		t = this.triangleInfo[index] >> 2;
		tA = this.textureVertexA[t];
		tB = this.textureVertexB[t];
		tC = this.textureVertexC[t];
		Draw3D.fillTexturedTriangle(yA, yB, yC, xA, xB, xC, this.colorA[index], this.colorA[index], this.colorA[index], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], this.unmodifiedTriangleColor[index]);
		Draw3D.fillTexturedTriangle(yA, yC, tmpY[3], xA, xC, tmpX[3], this.colorA[index], this.colorA[index], this.colorA[index], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], this.unmodifiedTriangleColor[index]);
	}

	@OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIIII)Z")
	private boolean pointWithinTriangle(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int yA, @OriginalArg(3) int yB, @OriginalArg(4) int yC, @OriginalArg(5) int xA, @OriginalArg(6) int xB, @OriginalArg(7) int xC) {
		if (y < yA && y < yB && y < yC) {
			return false;
		} else if (y > yA && y > yB && y > yC) {
			return false;
		} else if (x < xA && x < xB && x < xC) {
			return false;
		} else {
			return x <= xA || x <= xB || x <= xC;
		}
	}

	@OriginalMember(owner = "client!eb", name = "h", descriptor = "Z")
	private static boolean flowObfuscator2;

	@OriginalMember(owner = "client!eb", name = "i", descriptor = "I")
	private final int flowObfuscator1 = 45861;

	@OriginalMember(owner = "client!eb", name = "j", descriptor = "B")
	private final byte flowObfuscator4 = 47;

	@OriginalMember(owner = "client!eb", name = "k", descriptor = "B")
	private final byte flowObfuscator3 = 47;

	@OriginalMember(owner = "client!eb", name = "l", descriptor = "I")
	private final int flowObfuscator5 = 5;

	@OriginalMember(owner = "client!eb", name = "m", descriptor = "Z")
	private final boolean flowObfuscator6 = false;

}
