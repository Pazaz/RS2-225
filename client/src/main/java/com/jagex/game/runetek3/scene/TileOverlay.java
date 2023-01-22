package com.jagex.game.runetek3.scene;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!i")
public class TileOverlay {

	@OriginalMember(owner = "client!i", name = "x", descriptor = "[[I")
	public static final int[][] SHAPE_VERTICES = {
		{ 1, 3, 5, 7 },
		{ 1, 3, 5, 7 },
		{ 1, 3, 5, 7 },
		{ 1, 3, 5, 7, 6 },
		{ 1, 3, 5, 7, 6 },
		{ 1, 3, 5, 7, 6 },
		{ 1, 3, 5, 7, 6 },
		{ 1, 3, 5, 7, 2, 6 },
		{ 1, 3, 5, 7, 2, 8 },
		{ 1, 3, 5, 7, 2, 8 },
		{ 1, 3, 5, 7, 11, 12 },
		{ 1, 3, 5, 7, 11, 12 },
		{ 1, 3, 5, 7, 13, 14 }
	};

	@OriginalMember(owner = "client!i", name = "y", descriptor = "[[I")
	public static final int[][] SHAPE_PATHS = {
		{ 0, 1, 2, 3, 0, 0, 1, 3 },
		{ 1, 1, 2, 3, 1, 0, 1, 3 },
		{ 0, 1, 2, 3, 1, 0, 1, 3 },
		{ 0, 0, 1, 2, 0, 0, 2, 4, 1, 0, 4, 3 },
		{ 0, 0, 1, 4, 0, 0, 4, 3, 1, 1, 2, 4 },
		{ 0, 0, 4, 3, 1, 0, 1, 2, 1, 0, 2, 4 },
		{ 0, 1, 2, 4, 1, 0, 1, 4, 1, 0, 4, 3 },
		{ 0, 4, 1, 2, 0, 4, 2, 5, 1, 0, 4, 5, 1, 0, 5, 3 },
		{ 0, 4, 1, 2, 0, 4, 2, 3, 0, 4, 3, 5, 1, 0, 4, 5 },
		{ 0, 0, 4, 5, 1, 4, 1, 2, 1, 4, 2, 3, 1, 4, 3, 5 },
		{ 0, 0, 1, 5, 0, 1, 4, 5, 0, 1, 2, 4, 1, 0, 5, 3, 1, 5, 4, 3, 1, 4, 2, 3 },
		{ 1, 0, 1, 5, 1, 1, 4, 5, 1, 1, 2, 4, 0, 0, 5, 3, 0, 5, 4, 3, 0, 4, 2, 3 },
		{ 1, 0, 5, 4, 1, 0, 1, 5, 0, 0, 4, 3, 0, 4, 5, 3, 0, 5, 2, 3, 0, 1, 2, 5 }
	};

	@OriginalMember(owner = "client!i", name = "p", descriptor = "[I")
	public static final int[] tmpScreenX = new int[6];

	@OriginalMember(owner = "client!i", name = "q", descriptor = "[I")
	public static final int[] tmpScreenY = new int[6];

	@OriginalMember(owner = "client!i", name = "r", descriptor = "[I")
	public static final int[] vertexSceneX = new int[6];

	@OriginalMember(owner = "client!i", name = "s", descriptor = "[I")
	public static final int[] vertexSceneY = new int[6];

	@OriginalMember(owner = "client!i", name = "t", descriptor = "[I")
	public static final int[] vertexSceneZ = new int[6];

	@OriginalMember(owner = "client!i", name = "u", descriptor = "[I")
	private static final int[] intArr1 = new int[] { 1, 0 };

	@OriginalMember(owner = "client!i", name = "v", descriptor = "[I")
	private static final int[] intArr2 = new int[] { 2, 1 };

	@OriginalMember(owner = "client!i", name = "w", descriptor = "[I")
	private static final int[] intArr3 = new int[] { 3, 3 };

	@OriginalMember(owner = "client!i", name = "k", descriptor = "Z")
	public boolean isFlat = true;

	@OriginalMember(owner = "client!i", name = "l", descriptor = "I")
	public final int shape;

	@OriginalMember(owner = "client!i", name = "m", descriptor = "I")
	public final int orientation;

	@OriginalMember(owner = "client!i", name = "n", descriptor = "I")
	public final int underlayRgb;

	@OriginalMember(owner = "client!i", name = "o", descriptor = "I")
	public final int overlayRgb;

	@OriginalMember(owner = "client!i", name = "a", descriptor = "[I")
	public final int[] vertexX;

	@OriginalMember(owner = "client!i", name = "b", descriptor = "[I")
	public final int[] vertexY;

	@OriginalMember(owner = "client!i", name = "c", descriptor = "[I")
	public final int[] vertexZ;

	@OriginalMember(owner = "client!i", name = "g", descriptor = "[I")
	public final int[] triangleColorA;

	@OriginalMember(owner = "client!i", name = "h", descriptor = "[I")
	public final int[] triangleColorB;

	@OriginalMember(owner = "client!i", name = "i", descriptor = "[I")
	public final int[] triangleColorC;

	@OriginalMember(owner = "client!i", name = "d", descriptor = "[I")
	public final int[] triangleVertexA;

	@OriginalMember(owner = "client!i", name = "e", descriptor = "[I")
	public final int[] triangleVertexB;

	@OriginalMember(owner = "client!i", name = "f", descriptor = "[I")
	public final int[] triangleVertexC;

	@OriginalMember(owner = "client!i", name = "j", descriptor = "[I")
	public int[] triangleTextureIndex;

	@OriginalMember(owner = "client!i", name = "<init>", descriptor = "(IIIIIIIIIIIIIIIIIIII)V")
	public TileOverlay(@OriginalArg(0) int tileX, @OriginalArg(1) int shape, @OriginalArg(2) int southeastColor2, @OriginalArg(3) int southeastY, @OriginalArg(4) int northeastColor1, @OriginalArg(5) int orientation, @OriginalArg(6) int southwestColor1, @OriginalArg(7) int northwestY, @OriginalArg(8) int overlayRgb, @OriginalArg(9) int southwestColor2, @OriginalArg(10) int texture, @OriginalArg(11) int northwestColor2, @OriginalArg(12) int underlayRgb, @OriginalArg(13) int northeastY, @OriginalArg(14) int northeastColor2, @OriginalArg(15) int northwestColor1, @OriginalArg(17) int southwestY, @OriginalArg(18) int tileZ, @OriginalArg(19) int southeastColor1) {
		if (southwestY != southeastY || southwestY != northeastY || southwestY != northwestY) {
			this.isFlat = false;
		}

		this.shape = shape;
		this.orientation = orientation;
		this.underlayRgb = underlayRgb;
		this.overlayRgb = overlayRgb;

		@Pc(32) short tileSize = 128;
		@Pc(36) int halfSize = tileSize / 2;
		@Pc(40) int quarterSize = tileSize / 4;
		@Pc(46) int threeQuarterSize = (tileSize * 3) / 4;

		@Pc(50) int[] vertices = SHAPE_VERTICES[shape];
		@Pc(53) int vertexCount = vertices.length;

		this.vertexX = new int[vertexCount];
		this.vertexY = new int[vertexCount];
		this.vertexZ = new int[vertexCount];

		@Pc(68) int[] colors1 = new int[vertexCount];
		@Pc(71) int[] colors2 = new int[vertexCount];

		@Pc(75) int sceneX = tileX * tileSize;
		@Pc(79) int sceneZ = tileZ * tileSize;

		for (@Pc(81) int v = 0; v < vertexCount; v++) {
			@Pc(87) int vertex = vertices[v];
			if ((vertex & 0x1) == 0 && vertex <= 8) {
				vertex = (vertex - orientation - orientation - 1 & 0x7) + 1;
			}

			if (vertex > 8 && vertex <= 12) {
				vertex = (vertex - orientation - 9 & 0x3) + 9;
			}

			if (vertex > 12 && vertex <= 16) {
				vertex = (vertex - orientation - 13 & 0x3) + 13;
			}

			@Pc(143) int x;
			@Pc(145) int z;
			@Pc(147) int y;
			@Pc(149) int color1;
			@Pc(151) int color2;
			if (vertex == 1) {
				x = sceneX;
				z = sceneZ;
				y = southwestY;
				color1 = southwestColor1;
				color2 = southwestColor2;
			} else if (vertex == 2) {
				x = sceneX + halfSize;
				z = sceneZ;
				y = southwestY + southeastY >> 1;
				color1 = southwestColor1 + southeastColor1 >> 1;
				color2 = southwestColor2 + southeastColor2 >> 1;
			} else if (vertex == 3) {
				x = sceneX + tileSize;
				z = sceneZ;
				y = southeastY;
				color1 = southeastColor1;
				color2 = southeastColor2;
			} else if (vertex == 4) {
				x = sceneX + tileSize;
				z = sceneZ + halfSize;
				y = southeastY + northeastY >> 1;
				color1 = southeastColor1 + northeastColor1 >> 1;
				color2 = southeastColor2 + northeastColor2 >> 1;
			} else if (vertex == 5) {
				x = sceneX + tileSize;
				z = sceneZ + tileSize;
				y = northeastY;
				color1 = northeastColor1;
				color2 = northeastColor2;
			} else if (vertex == 6) {
				x = sceneX + halfSize;
				z = sceneZ + tileSize;
				y = northeastY + northwestY >> 1;
				color1 = northeastColor1 + northwestColor1 >> 1;
				color2 = northeastColor2 + northwestColor2 >> 1;
			} else if (vertex == 7) {
				x = sceneX;
				z = sceneZ + tileSize;
				y = northwestY;
				color1 = northwestColor1;
				color2 = northwestColor2;
			} else if (vertex == 8) {
				x = sceneX;
				z = sceneZ + halfSize;
				y = northwestY + southwestY >> 1;
				color1 = northwestColor1 + southwestColor1 >> 1;
				color2 = northwestColor2 + southwestColor2 >> 1;
			} else if (vertex == 9) {
				x = sceneX + halfSize;
				z = sceneZ + quarterSize;
				y = southwestY + southeastY >> 1;
				color1 = southwestColor1 + southeastColor1 >> 1;
				color2 = southwestColor2 + southeastColor2 >> 1;
			} else if (vertex == 10) {
				x = sceneX + threeQuarterSize;
				z = sceneZ + halfSize;
				y = southeastY + northeastY >> 1;
				color1 = southeastColor1 + northeastColor1 >> 1;
				color2 = southeastColor2 + northeastColor2 >> 1;
			} else if (vertex == 11) {
				x = sceneX + halfSize;
				z = sceneZ + threeQuarterSize;
				y = northeastY + northwestY >> 1;
				color1 = northeastColor1 + northwestColor1 >> 1;
				color2 = northeastColor2 + northwestColor2 >> 1;
			} else if (vertex == 12) {
				x = sceneX + quarterSize;
				z = sceneZ + halfSize;
				y = northwestY + southwestY >> 1;
				color1 = northwestColor1 + southwestColor1 >> 1;
				color2 = northwestColor2 + southwestColor2 >> 1;
			} else if (vertex == 13) {
				x = sceneX + quarterSize;
				z = sceneZ + quarterSize;
				y = southwestY;
				color1 = southwestColor1;
				color2 = southwestColor2;
			} else if (vertex == 14) {
				x = sceneX + threeQuarterSize;
				z = sceneZ + quarterSize;
				y = southeastY;
				color1 = southeastColor1;
				color2 = southeastColor2;
			} else if (vertex == 15) {
				x = sceneX + threeQuarterSize;
				z = sceneZ + threeQuarterSize;
				y = northeastY;
				color1 = northeastColor1;
				color2 = northeastColor2;
			} else {
				x = sceneX + quarterSize;
				z = sceneZ + threeQuarterSize;
				y = northwestY;
				color1 = northwestColor1;
				color2 = northwestColor2;
			}

			this.vertexX[v] = x;
			this.vertexY[v] = y;
			this.vertexZ[v] = z;

			colors1[v] = color1;
			colors2[v] = color2;
		}

		@Pc(552) int[] paths = SHAPE_PATHS[shape];
		int triangleCount = paths.length / 4;

		this.triangleColorA = new int[triangleCount];
		this.triangleColorB = new int[triangleCount];
		this.triangleColorC = new int[triangleCount];
		this.triangleVertexA = new int[triangleCount];
		this.triangleVertexB = new int[triangleCount];
		this.triangleVertexC = new int[triangleCount];

		if (texture != -1) {
			this.triangleTextureIndex = new int[triangleCount];
		}

		int index = 0;
		for (int i = 0; i < triangleCount; i++) {
			int path = paths[index];
			int a = paths[index + 1];
			@Pc(617) int b = paths[index + 2];
			@Pc(623) int c = paths[index + 3];

			index += 4;

			if (a < 4) {
				a = a - orientation & 0x3;
			}

			if (b < 4) {
				b = b - orientation & 0x3;
			}

			if (c < 4) {
				c = c - orientation & 0x3;
			}

			this.triangleColorA[i] = a;
			this.triangleColorB[i] = b;
			this.triangleColorC[i] = c;

			if (path == 0) {
				this.triangleVertexA[i] = colors1[a];
				this.triangleVertexB[i] = colors1[b];
				this.triangleVertexC[i] = colors1[c];

				if (this.triangleTextureIndex != null) {
					this.triangleTextureIndex[i] = -1;
				}
			} else {
				this.triangleVertexA[i] = colors2[a];
				this.triangleVertexB[i] = colors2[b];
				this.triangleVertexC[i] = colors2[c];

				if (this.triangleTextureIndex != null) {
					this.triangleTextureIndex[i] = texture;
				}
			}
		}
	}

}
