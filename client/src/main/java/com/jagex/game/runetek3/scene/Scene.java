package com.jagex.game.runetek3.scene;

import com.jagex.core.io.Buffer;
import com.jagex.core.util.LinkedList;
import com.jagex.game.runetek3.config.FloType;
import com.jagex.game.runetek3.config.LocType;
import com.jagex.game.runetek3.config.SeqType;
import com.jagex.game.runetek3.graphics.Draw3D;
import com.jagex.game.runetek3.graphics.Model;
import com.jagex.game.runetek3.scene.entities.LocEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!c")
public class Scene {

	@OriginalMember(owner = "client!c", name = "w", descriptor = "[I")
	public static final int[] ROTATION_WALL_TYPE = new int[] {
		1 << 0,
		1 << 1,
		1 << 2,
		1 << 3,
	};

	@OriginalMember(owner = "client!c", name = "x", descriptor = "[I")
	public static final int[] ROTATION_WALL_CORNER_TYPE = new int[] {
		1 << 4,
		1 << 5,
		1 << 6,
		1 << 7,
	};

	public static final int[] FRONT_WALL_TYPES = {
			0b00010011, // eyeTileX >  tileX  &&  eyeTileZ <  tileZ
			0b00110111, // eyeTileX == tileX  &&  eyeTileZ <  tileZ
			0b00100110, // eyeTileX <  tileX  &&  eyeTileZ <  tileZ
			0b10011011, // eyeTileX >  tileX  &&  eyeTileZ == tileZ
			0b11111111, // eyeTileX == tileX  &&  eyeTileZ == tileZ
			0b01101110, // eyeTileX <  tileX  &&  eyeTileZ == tileZ
			0b10001001, // eyeTileX >  tileX  &&  eyeTileZ >  tileZ
			0b11001101, // eyeTileX =  tileX  &&  eyeTileZ >  tileZ
			0b01001100, // eyeTileX <  tileX  &&  eyeTileZ >  tileZ
	};
	public static final int[] DIRECTION_ALLOW_WALL_CORNER_TYPE = {
			0b10100000, // eyeTileX >  tileX  &&  eyeTileZ <  tileZ
			0b11000000, // eyeTileX == tileX  &&  eyeTileZ <  tileZ
			0b01010000, // eyeTileX <  tileX  &&  eyeTileZ <  tileZ
			0b01100000, // eyeTileX >  tileX  &&  eyeTileZ == tileZ
			0b00000000, // eyeTileX =  tileX  &&  eyeTileZ == tileZ
			0b10010000, // eyeTileX <  tileX  &&  eyeTileZ == tileZ
			0b01010000, // eyeTileX >  tileX  &&  eyeTileZ >  tileZ
			0b00110000, // eyeTileX =  tileX  &&  eyeTileZ >  tileZ
			0b10100000, // eyeTileX <  tileX  &&  eyeTileZ >  tileZ
	};
	public static final int[] BACK_WALL_TYPES = {
			0b01001100, // eyeTileX >  tileX  &&  eyeTileZ <  tileZ
			0b00001000, // eyeTileX == tileX  &&  eyeTileZ <  tileZ
			0b10001001, // eyeTileX <  tileX  &&  eyeTileZ <  tileZ
			0b00000100, // eyeTileX >  tileX  &&  eyeTileZ == tileZ
			0b00000000, // eyeTileX =  tileX  &&  eyeTileZ == tileZ
			0b00000001, // eyeTileX <  tileX  &&  eyeTileZ == tileZ
			0b00100110, // eyeTileX >  tileX  &&  eyeTileZ >  tileZ
			0b00000010, // eyeTileX =  tileX  &&  eyeTileZ >  tileZ
			0b00010011, // eyeTileX <  tileX  &&  eyeTileZ >  tileZ
	};
	public static final int[] WALL_CORNER_TYPE_16_BLOCK_LOC_SPANS = {
			0b0000, // eyeTileX >  tileX  &&  eyeTileZ <  tileZ
			0b0000, // eyeTileX == tileX  &&  eyeTileZ <  tileZ
			0b0010, // eyeTileX <  tileX  &&  eyeTileZ <  tileZ
			0b0000, // eyeTileX >  tileX  &&  eyeTileZ == tileZ
			0b0000, // eyeTileX =  tileX  &&  eyeTileZ == tileZ
			0b0010, // eyeTileX <  tileX  &&  eyeTileZ == tileZ
			0b0001, // eyeTileX >  tileX  &&  eyeTileZ >  tileZ
			0b0001, // eyeTileX =  tileX  &&  eyeTileZ >  tileZ
			0b0000, // eyeTileX <  tileX  &&  eyeTileZ >  tileZ
	};
	public static final int[] WALL_CORNER_TYPE_32_BLOCK_LOC_SPANS = {
			0b0010, // eyeTileX >  tileX  &&  eyeTileZ <  tileZ
			0b0000, // eyeTileX == tileX  &&  eyeTileZ <  tileZ
			0b0000, // eyeTileX <  tileX  &&  eyeTileZ <  tileZ
			0b0010, // eyeTileX >  tileX  &&  eyeTileZ == tileZ
			0b0000, // eyeTileX =  tileX  &&  eyeTileZ == tileZ
			0b0000, // eyeTileX <  tileX  &&  eyeTileZ == tileZ
			0b0000, // eyeTileX >  tileX  &&  eyeTileZ >  tileZ
			0b0100, // eyeTileX =  tileX  &&  eyeTileZ >  tileZ
			0b0100, // eyeTileX <  tileX  &&  eyeTileZ >  tileZ
	};
	public static final int[] WALL_CORNER_TYPE_64_BLOCK_LOC_SPANS = {
			0b0000, // eyeTileX >  tileX  &&  eyeTileZ <  tileZ
			0b0100, // eyeTileX == tileX  &&  eyeTileZ <  tileZ
			0b0100, // eyeTileX <  tileX  &&  eyeTileZ <  tileZ
			0b1000, // eyeTileX >  tileX  &&  eyeTileZ == tileZ
			0b0000, // eyeTileX =  tileX  &&  eyeTileZ == tileZ
			0b0000, // eyeTileX <  tileX  &&  eyeTileZ == tileZ
			0b1000, // eyeTileX >  tileX  &&  eyeTileZ >  tileZ
			0b0000, // eyeTileX =  tileX  &&  eyeTileZ >  tileZ
			0b0000, // eyeTileX <  tileX  &&  eyeTileZ >  tileZ
	};
	public static final int[] WALL_CORNER_TYPE_128_BLOCK_LOC_SPANS = {
			0b0001, // eyeTileX >  tileX  &&  eyeTileZ <  tileZ
			0b0001, // eyeTileX == tileX  &&  eyeTileZ <  tileZ
			0b0000, // eyeTileX <  tileX  &&  eyeTileZ <  tileZ
			0b0000, // eyeTileX >  tileX  &&  eyeTileZ == tileZ
			0b0000, // eyeTileX =  tileX  &&  eyeTileZ == tileZ
			0b1000, // eyeTileX <  tileX  &&  eyeTileZ == tileZ
			0b0000, // eyeTileX >  tileX  &&  eyeTileZ >  tileZ
			0b0000, // eyeTileX =  tileX  &&  eyeTileZ >  tileZ
			0b1000, // eyeTileX <  tileX  &&  eyeTileZ >  tileZ
	};

	@OriginalMember(owner = "client!c", name = "y", descriptor = "[I")
	public static final int[] WALL_DECO_ROT_SIZE_X_DIR = new int[] { 1, 0, -1, 0 };

	@OriginalMember(owner = "client!c", name = "z", descriptor = "[I")
	public static final int[] WALL_DECO_ROT_SIZE_Y_DIR = new int[] { 0, -1, 0, 1 };

	@OriginalMember(owner = "client!c", name = "e", descriptor = "I")
	public static int levelBuilt;

	@OriginalMember(owner = "client!c", name = "f", descriptor = "Z")
	public static boolean fullbright;

	@OriginalMember(owner = "client!c", name = "d", descriptor = "Z")
	public static boolean lowMemory = true;

	@OriginalMember(owner = "client!c", name = "A", descriptor = "I")
	public static int randomHueOffset = (int) (Math.random() * 17.0D) - 8;

	@OriginalMember(owner = "client!c", name = "B", descriptor = "I")
	public static int randomLightnessOffset = (int) (Math.random() * 33.0D) - 16;

	@OriginalMember(owner = "client!c", name = "g", descriptor = "I")
	private final int tileCountX;

	@OriginalMember(owner = "client!c", name = "h", descriptor = "I")
	private final int tileCountZ;

	@OriginalMember(owner = "client!c", name = "i", descriptor = "[[[I")
	private final int[][][] heightmap;

	@OriginalMember(owner = "client!c", name = "j", descriptor = "[[[B")
	private final byte[][][] renderFlags;

	@OriginalMember(owner = "client!c", name = "k", descriptor = "[[[B")
	private final byte[][][] planeUnderlayFloorIndices;

	@OriginalMember(owner = "client!c", name = "l", descriptor = "[[[B")
	private final byte[][][] planeOverlayFloorIndices;

	@OriginalMember(owner = "client!c", name = "m", descriptor = "[[[B")
	private final byte[][][] planeOverlayTypes;

	@OriginalMember(owner = "client!c", name = "n", descriptor = "[[[B")
	private final byte[][][] planeOverlayRotations;

	@OriginalMember(owner = "client!c", name = "v", descriptor = "[[[I")
	private final int[][][] occludeFlags;

	@OriginalMember(owner = "client!c", name = "o", descriptor = "[[[B")
	private final byte[][][] shadowmap;

	@OriginalMember(owner = "client!c", name = "p", descriptor = "[[I")
	private final int[][] lightmap;

	@OriginalMember(owner = "client!c", name = "q", descriptor = "[I")
	private final int[] blendChroma;

	@OriginalMember(owner = "client!c", name = "r", descriptor = "[I")
	private final int[] blendSaturation;

	@OriginalMember(owner = "client!c", name = "s", descriptor = "[I")
	private final int[] blendLightness;

	@OriginalMember(owner = "client!c", name = "t", descriptor = "[I")
	private final int[] blendLuminance;

	@OriginalMember(owner = "client!c", name = "u", descriptor = "[I")
	private final int[] blendMagnitude;

	@OriginalMember(owner = "client!c", name = "<init>", descriptor = "(I[[[BI[[[II)V")
	public Scene(@OriginalArg(0) int sizeZ, @OriginalArg(1) byte[][][] renderFlags, @OriginalArg(2) int sizeX, @OriginalArg(3) int[][][] heightmap) {
		this.tileCountX = sizeX;
		this.tileCountZ = sizeZ;
		this.heightmap = heightmap;
		this.renderFlags = renderFlags;
		this.planeUnderlayFloorIndices = new byte[4][this.tileCountX][this.tileCountZ];
		this.planeOverlayFloorIndices = new byte[4][this.tileCountX][this.tileCountZ];
		this.planeOverlayTypes = new byte[4][this.tileCountX][this.tileCountZ];
		this.planeOverlayRotations = new byte[4][this.tileCountX][this.tileCountZ];
		this.occludeFlags = new int[4][this.tileCountX + 1][this.tileCountZ + 1];
		this.shadowmap = new byte[4][this.tileCountX + 1][this.tileCountZ + 1];
		this.lightmap = new int[this.tileCountX + 1][this.tileCountZ + 1];
		this.blendChroma = new int[this.tileCountZ];
		this.blendSaturation = new int[this.tileCountZ];
		this.blendLightness = new int[this.tileCountZ];
		this.blendLuminance = new int[this.tileCountZ];
		this.blendMagnitude = new int[this.tileCountZ];
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(II)I")
	public static int perlin(@OriginalArg(0) int x, @OriginalArg(1) int z) {
		@Pc(32) int value = perlin(x + 45365, z + 91923, 4) + (perlin(x + 10294, z + 37821, 2) - 128 >> 1) + (perlin(x, z, 1) - 128 >> 2) - 128;
		value = (int) ((double) value * 0.3D) + 35;

		if (value < 10) {
			value = 10;
		} else if (value > 60) {
			value = 60;
		}

		return value;
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(III)I")
	private static int perlin(@OriginalArg(0) int x, @OriginalArg(1) int z, @OriginalArg(2) int scale) {
		@Pc(3) int intX = x / scale;
		@Pc(9) int fracX = x & scale - 1;
		@Pc(13) int intZ = z / scale;
		@Pc(19) int fracZ = z & scale - 1;
		@Pc(23) int v1 = smoothNoise(intX, intZ);
		@Pc(29) int v2 = smoothNoise(intX + 1, intZ);
		@Pc(35) int v3 = smoothNoise(intX, intZ + 1);
		@Pc(43) int v4 = smoothNoise(intX + 1, intZ + 1);
		@Pc(49) int i1 = interpolate(v1, v2, fracX, scale);
		@Pc(55) int i2 = interpolate(v3, v4, fracX, scale);
		return interpolate(i1, i2, fracZ, scale);
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(IIII)I")
	private static int interpolate(@OriginalArg(0) int a, @OriginalArg(1) int b, @OriginalArg(2) int x, @OriginalArg(3) int scale) {
		@Pc(11) int f = 65536 - Draw3D.cos[x * 1024 / scale] >> 1;
		return (a * (65536 - f) >> 16) + (b * f >> 16);
	}

	@OriginalMember(owner = "client!c", name = "b", descriptor = "(II)I")
	private static int smoothNoise(@OriginalArg(0) int x, @OriginalArg(1) int z) {
		@Pc(31) int corners = noise(x - 1, z - 1) + noise(x + 1, z - 1) + noise(x - 1, z + 1) + noise(x + 1, z + 1);
		@Pc(55) int sides = noise(x - 1, z) + noise(x + 1, z) + noise(x, z - 1) + noise(x, z + 1);
		@Pc(59) int corner = noise(x, z);
		return corners / 16 + sides / 8 + corner / 4;
	}

	@OriginalMember(owner = "client!c", name = "c", descriptor = "(II)I")
	private static int noise(@OriginalArg(0) int x, @OriginalArg(1) int y) {
		@Pc(5) int n = x + y * 57;
		n = n << 13 ^ n;
		n = n * (n * n * 15731 + 789221) + 1376312589 & Integer.MAX_VALUE;
		return n >> 19 & 0xFF;
	}

	@OriginalMember(owner = "client!c", name = "d", descriptor = "(II)I")
	public static int mulHSL(@OriginalArg(0) int hsl, @OriginalArg(1) int lightness) {
		if (hsl == -1) {
			return 12345678;
		}

		lightness = lightness * (hsl & 0x7F) / 128;

		if (lightness < 2) {
			lightness = 2;
		} else if (lightness > 126) {
			lightness = 126;
		}

		return (hsl & 0xFF80) + lightness;
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(ILclient!ob;Lclient!ec;II[[[IIIIILclient!r;I)V")
	public static void addLoc(@OriginalArg(0) int x, @OriginalArg(1) LinkedList anims, @OriginalArg(2) CollisionMap collision, @OriginalArg(3) int z, @OriginalArg(4) int orientation, @OriginalArg(5) int[][][] levelHeightmap, @OriginalArg(7) int plane, @OriginalArg(8) int locId, @OriginalArg(9) int type, @OriginalArg(10) MapSquare mapSquare, @OriginalArg(11) int level) {
		@Pc(15) int heightSW = levelHeightmap[level][x][z];
		@Pc(25) int heightSE = levelHeightmap[level][x + 1][z];
		@Pc(37) int heightNE = levelHeightmap[level][x + 1][z + 1];
		@Pc(47) int heightNW = levelHeightmap[level][x][z + 1];
		@Pc(57) int y = heightSW + heightSE + heightNE + heightNW >> 2;

		@Pc(60) LocType loc = LocType.get(locId);
		@Pc(72) int bitset = x + (z << 7) + (locId << 14) + 0x40000000;

		if (!loc.interactable) {
			bitset += 0x80000000;
		}

		@Pc(86) byte info = (byte) ((orientation << 6) + type);
		if (type == LocType.GROUNDDECOR) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addGroundDecoration(model, x, bitset, z, plane, info, y);

			if (loc.blockwalk && loc.interactable) {
				collision.setBlocked(z, x);
			}

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 3, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.CENTREPIECE_STRAIGHT || type == LocType.CENTREPIECE_DIAGONAL) {
			Model model = loc.getModel(LocType.CENTREPIECE_STRAIGHT, orientation, heightSW, heightSE, heightNE, heightNW, -1);

			if (model != null) {
				@Pc(161) int yaw = 0;
				if (type == LocType.CENTREPIECE_DIAGONAL) {
					yaw += 256;
				}

				int width;
				int height;
				if (orientation == CollisionMap.NORTH_EAST || orientation == CollisionMap.SOUTH_WEST) {
					width = loc.sizeZ;
					height = loc.sizeX;
				} else {
					width = loc.sizeX;
					height = loc.sizeZ;
				}

				mapSquare.addLocation(y, plane, null, bitset, z, x, width, info, model, yaw, height);
			}

			if (loc.blockwalk) {
				collision.setLoc(orientation, loc.sizeZ, loc.sizeX, x, z, loc.blockrange);
			}

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 2, SeqType.instances[loc.anim], z, x));
			}
		} else if (type >= LocType.ROOF_STRAIGHT) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addLocation(y, plane, null, bitset, z, x, 1, info, model, 0, 1);

			if (loc.blockwalk) {
				collision.setLoc(orientation, loc.sizeZ, loc.sizeX, x, z, loc.blockrange);
			}

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 2, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALL_STRAIGHT) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWall(0, y, plane, ROTATION_WALL_TYPE[orientation], model, null, x, bitset, z, info);

			if (loc.blockwalk) {
				collision.setWall(orientation, z, x, loc.blockrange, type);
			}

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 0, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALL_DIAGONALCORNER) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWall(0, y, plane, ROTATION_WALL_CORNER_TYPE[orientation], model, null, x, bitset, z, info);

			if (loc.blockwalk) {
				collision.setWall(orientation, z, x, loc.blockrange, type);
			}

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 0, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALL_L) {
			int nextOrientation = orientation + 1 & 0x3;

			@Pc(442) Model model1 = loc.getModel(type, orientation + 4, heightSW, heightSE, heightNE, heightNW, -1);
			Model model2 = loc.getModel(2, nextOrientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWall(ROTATION_WALL_TYPE[nextOrientation], y, plane, ROTATION_WALL_TYPE[orientation], model1, model2, x, bitset, z, info);

			if (loc.blockwalk) {
				collision.setWall(orientation, z, x, loc.blockrange, type);
			}

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 0, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALL_SQUARECORNER) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWall(0, y, plane, ROTATION_WALL_CORNER_TYPE[orientation], model, null, x, bitset, z, info);

			if (loc.blockwalk) {
				collision.setWall(orientation, z, x, loc.blockrange, type);
			}

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 0, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALL_DIAGONAL) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addLocation(y, plane, null, bitset, z, x, 1, info, model, 0, 1);

			if (loc.blockwalk) {
				collision.setLoc(orientation, loc.sizeZ, loc.sizeX, x, z, loc.blockrange);
			}

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 2, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_STRAIGHT) {
			Model model = loc.getModel(type, CollisionMap.NORTH_WEST, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, 0, bitset, orientation * 512, ROTATION_WALL_TYPE[orientation], 0, x, model, info, plane);

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 1, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_STRAIGHT_OFFSET) {
			int wallOffset = 16;
			int wallBitset = mapSquare.getWallBitset(plane, x, z);

			if (wallBitset > 0) {
				wallOffset = LocType.get(wallBitset >> 14 & 0x7FFF).walloff;
			}

			Model model2 = loc.getModel(LocType.WALLDECOR_STRAIGHT, CollisionMap.NORTH_WEST, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, WALL_DECO_ROT_SIZE_Y_DIR[orientation] * wallOffset, bitset, orientation * 512, ROTATION_WALL_TYPE[orientation], WALL_DECO_ROT_SIZE_X_DIR[orientation] * wallOffset, x, model2, info, plane);

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 1, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_DIAGONAL_NOOFFSET) {
			Model model = loc.getModel(LocType.WALLDECOR_STRAIGHT, CollisionMap.NORTH_WEST, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, 0, bitset, orientation, 256, 0, x, model, info, plane);

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 1, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_DIAGONAL_OFFSET) {
			Model model = loc.getModel(LocType.WALLDECOR_STRAIGHT, CollisionMap.NORTH_WEST, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, 0, bitset, orientation, 512, 0, x, model, info, plane);

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 1, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_DIAGONAL_BOTH) {
			Model model = loc.getModel(LocType.WALLDECOR_STRAIGHT, CollisionMap.NORTH_WEST, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, 0, bitset, orientation, 768, 0, x, model, info, plane);

			if (loc.anim != -1) {
				anims.pushNext(new LocEntity(true, locId, plane, 1, SeqType.instances[loc.anim], z, x));
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(IIIII)V")
	public void clearLandscape(@OriginalArg(0) int startX, @OriginalArg(1) int startZ, @OriginalArg(3) int endX, @OriginalArg(4) int endZ) {
		@Pc(3) byte flo = 0;
		for (@Pc(11) int i = 0; i < FloType.count; i++) {
			if (FloType.instances[i].name.equalsIgnoreCase("water")) {
				flo = (byte) (i + 1);
				break;
			}
		}

		for (@Pc(33) int z = startZ; z < startZ + endZ; z++) {
			for (@Pc(37) int x = startX; x < startX + endX; x++) {
				if (x >= 0 && x < this.tileCountX && z >= 0 && z < this.tileCountZ) {
					this.planeOverlayFloorIndices[0][x][z] = flo;

					for (@Pc(62) int plane = 0; plane < 4; plane++) {
						this.heightmap[plane][x][z] = 0;
						this.renderFlags[plane][x][z] = 0;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "([BIIIII)V")
	public void readLandscape(@OriginalArg(0) byte[] src, @OriginalArg(1) int originX, @OriginalArg(3) int startZ, @OriginalArg(4) int startX, @OriginalArg(5) int originZ) {
		@Pc(7) Buffer buf = new Buffer(src);

		for (@Pc(20) int plane = 0; plane < 4; plane++) {
			for (@Pc(24) int x = 0; x < 64; x++) {
				for (@Pc(28) int z = 0; z < 64; z++) {
					@Pc(34) int localX = x + startX;
					@Pc(38) int localZ = z + startZ;

					if (localX >= 0 && localX < 104 && localZ >= 0 && localZ < 104) {
						this.renderFlags[plane][localX][localZ] = 0;

						while (true) {
							int opcode = buf.g1();

							if (opcode == 0) {
								if (plane == 0) {
									this.heightmap[0][localX][localZ] = -perlin(932731 + localX + originX, 556238 + localZ + originZ) * 8;
								} else {
									this.heightmap[plane][localX][localZ] = this.heightmap[plane - 1][localX][localZ] - 240;
								}
								break;
							}

							if (opcode == 1) {
								@Pc(116) int height = buf.g1();

								if (height == 1) {
									height = 0;
								}

								if (plane == 0) {
									this.heightmap[0][localX][localZ] = -height * 8;
								} else {
									this.heightmap[plane][localX][localZ] = this.heightmap[plane - 1][localX][localZ] - height * 8;
								}

								break;
							}

							if (opcode <= 49) {
								this.planeOverlayFloorIndices[plane][localX][localZ] = buf.g1b();
								this.planeOverlayTypes[plane][localX][localZ] = (byte) ((opcode - 2) / 4);
								this.planeOverlayRotations[plane][localX][localZ] = (byte) (opcode - 2 & 0x3);
							} else if (opcode <= 81) {
								this.renderFlags[plane][localX][localZ] = (byte) (opcode - 49);
							} else {
								this.planeUnderlayFloorIndices[plane][localX][localZ] = (byte) (opcode - 81);
							}
						}
					} else {
						while (true) {
							int opcode = buf.g1();
							if (opcode == 0) {
								break;
							}

							if (opcode == 1) {
								buf.g1();
								break;
							}

							if (opcode <= 49) {
								buf.g1();
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "([BLclient!r;[Lclient!ec;Lclient!ob;ZII)V")
	public void readLocs(@OriginalArg(0) byte[] src, @OriginalArg(1) MapSquare arg1, @OriginalArg(2) CollisionMap[] collisionMaps, @OriginalArg(3) LinkedList arg3, @OriginalArg(4) boolean arg4, @OriginalArg(5) int startZ, @OriginalArg(6) int startX) {
		@Pc(7) Buffer buf = new Buffer(src);

		@Pc(19) int locId = -1;
		while (true) {
			@Pc(22) int deltaId = buf.gsmarts();
			if (deltaId == 0) {
				return;
			}

			locId += deltaId;

			@Pc(30) int locData = 0;
			while (true) {
				@Pc(33) int deltaData = buf.gsmarts();
				if (deltaData == 0) {
					break;
				}

				locData += deltaData - 1;
				@Pc(45) int locX = locData & 0x3F;
				@Pc(51) int locZ = locData >> 6 & 0x3F;
				@Pc(55) int locLevel = locData >> 12;
				@Pc(58) int locInfo = buf.g1();
				@Pc(62) int locType = locInfo >> 2; // e.g. WALL_STRAIGHT
				@Pc(66) int locRotation = locInfo & 0x3;
				@Pc(70) int x = locZ + startX;
				@Pc(74) int z = locX + startZ;

				if (x > 0 && z > 0 && x < 103 && z < 103) {
					@Pc(86) int collisionLevel = locLevel;
					if ((this.renderFlags[1][x][z] & 0x2) == 2) {
						collisionLevel = locLevel - 1;
					}

					@Pc(101) CollisionMap collision = null;
					if (collisionLevel >= 0) {
						collision = collisionMaps[collisionLevel];
					}

					this.addLoc(collision, locLevel, z, locRotation, locType, arg1, arg3, locId, x);
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(Lclient!ec;ZIIIILclient!r;Lclient!ob;II)V")
	private void addLoc(@OriginalArg(0) CollisionMap collision, @OriginalArg(2) int level, @OriginalArg(3) int z, @OriginalArg(4) int orientation, @OriginalArg(5) int type, @OriginalArg(6) MapSquare mapSquare, @OriginalArg(7) LinkedList anim, @OriginalArg(8) int locId, @OriginalArg(9) int x) {
		if (lowMemory) {
			if ((this.renderFlags[level][x][z] & 0x10) != 0) {
				return;
			}

			if (this.getRenderLevel(level, x, z) != levelBuilt) {
				return;
			}
		}

		@Pc(36) int heightSW = this.heightmap[level][x][z];
		@Pc(47) int heightSE = this.heightmap[level][x + 1][z];
		@Pc(60) int heightNE = this.heightmap[level][x + 1][z + 1];
		@Pc(71) int heightNW = this.heightmap[level][x][z + 1];
		@Pc(81) int y = heightSW + heightSE + heightNE + heightNW >> 2;

		@Pc(84) LocType loc = LocType.get(locId);
		@Pc(96) int bitset = x + (z << 7) + (locId << 14) + 0x40000000;

		if (!loc.interactable) {
			bitset += 0x80000000;
		}

		@Pc(110) byte info = (byte) ((orientation << 6) + type);
		if (type == LocType.GROUNDDECOR) {
			if (!lowMemory || loc.interactable || loc.forcedecor) {
				Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
				mapSquare.addGroundDecoration(model, x, bitset, z, level, info, y);

				if (loc.blockwalk && loc.interactable && collision != null) {
					collision.setBlocked(z, x);
				}

				if (loc.anim != -1) {
					anim.pushNext(new LocEntity(true, locId, level, 3, SeqType.instances[loc.anim], z, x));
				}
			}
		} else if (type == LocType.CENTREPIECE_STRAIGHT || type == LocType.CENTREPIECE_DIAGONAL) {
			Model model = loc.getModel(LocType.CENTREPIECE_STRAIGHT, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			if (model != null) {
				@Pc(196) int yaw = 0;
				if (type == LocType.CENTREPIECE_DIAGONAL) {
					yaw += 256;
				}

				int width;
				int height;
				if (orientation == CollisionMap.NORTH_EAST || orientation == CollisionMap.SOUTH_WEST) {
					width = loc.sizeZ;
					height = loc.sizeX;
				} else {
					width = loc.sizeX;
					height = loc.sizeZ;
				}

				if (mapSquare.addLocation(y, level, null, bitset, z, x, width, info, model, yaw, height) && loc.active) {
					for (@Pc(240) int offX = 0; offX <= width; offX++) {
						for (@Pc(244) int offZ = 0; offZ <= height; offZ++) {
							@Pc(251) int shadow = model.lengthXZ / 4;
							if (shadow > 30) {
								shadow = 30;
							}

							if (shadow > this.shadowmap[level][x + offX][z + offZ]) {
								this.shadowmap[level][x + offX][z + offZ] = (byte) shadow;
							}
						}
					}
				}
			}

			if (loc.blockwalk && collision != null) {
				collision.setLoc(orientation, loc.sizeZ, loc.sizeX, x, z, loc.blockrange);
			}

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 2, SeqType.instances[loc.anim], z, x));
			}
		} else if (type >= LocType.ROOF_STRAIGHT) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addLocation(y, level, null, bitset, z, x, 1, info, model, 0, 1);

			if (type <= LocType.ROOF_FLAT && type != LocType.ROOF_DIAGONAL_WITH_ROOFEDGE && level > 0) {
				this.occludeFlags[level][x][z] |= 0x924;
			}

			if (loc.blockwalk && collision != null) {
				collision.setLoc(orientation, loc.sizeZ, loc.sizeX, x, z, loc.blockrange);
			}

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 2, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALL_STRAIGHT) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWall(0, y, level, ROTATION_WALL_TYPE[orientation], model, null, x, bitset, z, info);

			if (orientation == CollisionMap.NORTH_WEST) {
				if (loc.active) {
					this.shadowmap[level][x][z] = 50;
					this.shadowmap[level][x][z + 1] = 50;
				}

				if (loc.occlude) {
					this.occludeFlags[level][x][z] |= 0x249;
				}
			} else if (orientation == CollisionMap.NORTH_EAST) {
				if (loc.active) {
					this.shadowmap[level][x][z + 1] = 50;
					this.shadowmap[level][x + 1][z + 1] = 50;
				}

				if (loc.occlude) {
					this.occludeFlags[level][x][z + 1] |= 0x492;
				}
			} else if (orientation == CollisionMap.SOUTH_EAST) {
				if (loc.active) {
					this.shadowmap[level][x + 1][z] = 50;
					this.shadowmap[level][x + 1][z + 1] = 50;
				}

				if (loc.occlude) {
					this.occludeFlags[level][x + 1][z] |= 0x249;
				}
			} else if (orientation == CollisionMap.SOUTH_WEST) {
				if (loc.active) {
					this.shadowmap[level][x][z] = 50;
					this.shadowmap[level][x + 1][z] = 50;
				}

				if (loc.occlude) {
					this.occludeFlags[level][x][z] |= 0x492;
				}
			}

			if (loc.blockwalk && collision != null) {
				collision.setWall(orientation, z, x, loc.blockrange, type);
			}

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 0, SeqType.instances[loc.anim], z, x));
			}

			if (loc.walloff != 16) {
				mapSquare.setWallDecoration(level, z, x, loc.walloff);
			}
		} else if (type == LocType.WALL_DIAGONALCORNER) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWall(0, y, level, ROTATION_WALL_CORNER_TYPE[orientation], model, null, x, bitset, z, info);

			if (loc.active) {
				if (orientation == CollisionMap.NORTH_WEST) {
					this.shadowmap[level][x][z + 1] = 50;
				} else if (orientation == CollisionMap.NORTH_EAST) {
					this.shadowmap[level][x + 1][z + 1] = 50;
				} else if (orientation == CollisionMap.SOUTH_EAST) {
					this.shadowmap[level][x + 1][z] = 50;
				} else if (orientation == CollisionMap.SOUTH_WEST) {
					this.shadowmap[level][x][z] = 50;
				}
			}

			if (loc.blockwalk && collision != null) {
				collision.setWall(orientation, z, x, loc.blockrange, type);
			}

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 0, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALL_L) {
			int nextOrientation = orientation + 1 & 0x3;

			@Pc(822) Model model1 = loc.getModel(type, orientation + 4, heightSW, heightSE, heightNE, heightNW, -1);
			Model model2 = loc.getModel(type, nextOrientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWall(ROTATION_WALL_TYPE[nextOrientation], y, level, ROTATION_WALL_TYPE[orientation], model1, model2, x, bitset, z, info);

			if (loc.occlude) {
				if (orientation == CollisionMap.NORTH_WEST) {
					this.occludeFlags[level][x][z] |= 0x249;
					this.occludeFlags[level][x][z + 1] |= 0x492;
				} else if (orientation == CollisionMap.NORTH_EAST) {
					this.occludeFlags[level][x][z + 1] |= 0x492;
					this.occludeFlags[level][x + 1][z] |= 0x249;
				} else if (orientation == CollisionMap.SOUTH_EAST) {
					this.occludeFlags[level][x + 1][z] |= 0x249;
					this.occludeFlags[level][x][z] |= 0x492;
				} else if (orientation == CollisionMap.SOUTH_WEST) {
					this.occludeFlags[level][x][z] |= 0x492;
					this.occludeFlags[level][x][z] |= 0x249;
				}
			}

			if (loc.blockwalk && collision != null) {
				collision.setWall(orientation, z, x, loc.blockrange, type);
			}

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 0, SeqType.instances[loc.anim], z, x));
			}

			if (loc.walloff != 16) {
				mapSquare.setWallDecoration(level, z, x, loc.walloff);
			}
		} else if (type == LocType.WALL_SQUARECORNER) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWall(0, y, level, ROTATION_WALL_CORNER_TYPE[orientation], model, null, x, bitset, z, info);

			if (loc.active) {
				if (orientation == CollisionMap.NORTH_WEST) {
					this.shadowmap[level][x][z + 1] = 50;
				} else if (orientation == CollisionMap.NORTH_EAST) {
					this.shadowmap[level][x + 1][z + 1] = 50;
				} else if (orientation == CollisionMap.SOUTH_EAST) {
					this.shadowmap[level][x + 1][z] = 50;
				} else if (orientation == CollisionMap.SOUTH_WEST) {
					this.shadowmap[level][x][z] = 50;
				}
			}

			if (loc.blockwalk && collision != null) {
				collision.setWall(orientation, z, x, loc.blockrange, type);
			}

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 0, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALL_DIAGONAL) {
			Model model = loc.getModel(type, orientation, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addLocation(y, level, null, bitset, z, x, 1, info, model, 0, 1);

			if (loc.blockwalk && collision != null) {
				collision.setLoc(orientation, loc.sizeZ, loc.sizeX, x, z, loc.blockrange);
			}

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 2, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_STRAIGHT) {
			Model model = loc.getModel(type, CollisionMap.NORTH_WEST, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, 0, bitset, orientation * 512, ROTATION_WALL_TYPE[orientation], 0, x, model, info, level);

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 1, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_STRAIGHT_OFFSET) {
			int wallOffset = 16;
			int wallBitset = mapSquare.getWallBitset(level, x, z);

			if (wallBitset > 0) {
				wallOffset = LocType.get(wallBitset >> 14 & 0x7FFF).walloff;
			}

			Model model2 = loc.getModel(LocType.WALLDECOR_STRAIGHT, 0, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, WALL_DECO_ROT_SIZE_Y_DIR[orientation] * wallOffset, bitset, orientation * 512, ROTATION_WALL_TYPE[orientation], WALL_DECO_ROT_SIZE_X_DIR[orientation] * wallOffset, x, model2, info, level);

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 1, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_DIAGONAL_NOOFFSET) {
			Model model = loc.getModel(LocType.WALLDECOR_STRAIGHT, 0, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, 0, bitset, orientation, 256, 0, x, model, info, level);

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 1, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_DIAGONAL_OFFSET) {
			Model model = loc.getModel(LocType.WALLDECOR_STRAIGHT, 0, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, 0, bitset, orientation, 512, 0, x, model, info, level);

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 1, SeqType.instances[loc.anim], z, x));
			}
		} else if (type == LocType.WALLDECOR_DIAGONAL_BOTH) {
			Model model = loc.getModel(LocType.WALLDECOR_STRAIGHT, 0, heightSW, heightSE, heightNE, heightNW, -1);
			mapSquare.addWallDecoration(y, z, 0, bitset, orientation, 768, 0, x, model, info, level);

			if (loc.anim != -1) {
				anim.pushNext(new LocEntity(true, locId, level, 1, SeqType.instances[loc.anim], z, x));
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(Lclient!r;I[Lclient!ec;)V")
	public void buildLandscape(@OriginalArg(0) MapSquare mapSquare, @OriginalArg(2) CollisionMap[] collisionMaps) {
		for (@Pc(3) int plane = 0; plane < 4; plane++) {
			for (int x = 0; x < 104; x++) {
				for (int z = 0; z < 104; z++) {
					if ((this.renderFlags[plane][x][z] & 0x1) == 1) {
						int truePlane = plane;

						// bridge
						if ((this.renderFlags[1][x][z] & 0x2) == 2) {
							truePlane = plane - 1;
						}

						if (truePlane >= 0) {
							collisionMaps[truePlane].setBlocked(z, x);
						}
					}
				}
			}
		}

		randomHueOffset += (int) (Math.random() * 5.0D) - 2;
		if (randomHueOffset < -8) {
			randomHueOffset = -8;
		} else if (randomHueOffset > 8) {
			randomHueOffset = 8;
		}

		randomLightnessOffset += (int) (Math.random() * 5.0D) - 2;
		if (randomLightnessOffset < -16) {
			randomLightnessOffset = -16;
		} else if (randomLightnessOffset > 16) {
			randomLightnessOffset = 16;
		}

		for (int plane = 0; plane < 4; plane++) {
			@Pc(108) byte[][] shadowmap = this.shadowmap[plane];
			@Pc(110) byte lightAmbient = 96;
			@Pc(112) short lightAttenuation = 768;
			@Pc(114) byte lightX = -50;
			@Pc(116) byte lightY = -10;
			@Pc(118) byte lightZ = -50;
			int lightMagnitude = (lightAttenuation * (int) Math.sqrt(lightX * lightX + lightY * lightY + lightZ * lightZ)) >> 8;

			for (int z = 1; z < this.tileCountZ - 1; z++) {
				for (int x = 1; x < this.tileCountX - 1; x++) {
					int dx = this.heightmap[plane][x + 1][z] - this.heightmap[plane][x - 1][z];
					int dz = this.heightmap[plane][x][z + 1] - this.heightmap[plane][x][z - 1];
					int len = (int) Math.sqrt(dx * dx + dz * dz + 65536);
					int normalX = (dx << 8) / len;
					int normalY = 65536 / len;
					int normalZ = (dz << 8) / len;
					int light = lightAmbient + (lightX * normalX + lightY * normalY + lightZ * normalZ) / lightMagnitude;
					int shade = (shadowmap[x - 1][z] >> 2) + (shadowmap[x + 1][z] >> 3) + (shadowmap[x][z - 1] >> 2) + (shadowmap[x][z + 1] >> 3) + (shadowmap[x][z] >> 1);
					this.lightmap[x][z] = light - shade;
				}
			}

			for (int z = 0; z < this.tileCountZ; z++) {
				this.blendChroma[z] = 0;
				this.blendSaturation[z] = 0;
				this.blendLightness[z] = 0;
				this.blendLuminance[z] = 0;
				this.blendMagnitude[z] = 0;
			}

			for (int x0 = -5; x0 < this.tileCountX + 5; x0++) {
				for (int z0 = 0; z0 < this.tileCountZ; z0++) {
					int x1 = x0 + 5;

					if (x1 >= 0 && x1 < this.tileCountX) {
						int floId = this.planeUnderlayFloorIndices[plane][x1][z0] & 0xFF;

						if (floId > 0) {
							@Pc(378) FloType flo = FloType.instances[floId - 1];
							this.blendChroma[z0] += flo.chroma;
							this.blendSaturation[z0] += flo.saturation;
							this.blendLightness[z0] += flo.lightness;
							this.blendLuminance[z0] += flo.luminance;
							this.blendMagnitude[z0]++;
						}
					}

					int x2 = x0 - 5;
					if (x2 >= 0 && x2 < this.tileCountX) {
						int floId = this.planeUnderlayFloorIndices[plane][x2][z0] & 0xFF;

						if (floId > 0) {
							@Pc(451) FloType flo = FloType.instances[floId - 1];
							this.blendChroma[z0] -= flo.chroma;
							this.blendSaturation[z0] -= flo.saturation;
							this.blendLightness[z0] -= flo.lightness;
							this.blendLuminance[z0] -= flo.luminance;
							this.blendMagnitude[z0]--;
						}
					}
				}

				if (x0 >= 1 && x0 < this.tileCountX - 1) {
					int hueAccumulator = 0;
					int saturationAccumulator = 0;
					int lightnessAccumulator = 0;
					int luminanceAccumulator = 0;
					int magnitudeAccumulator = 0;

					for (int z0 = -5; z0 < this.tileCountZ + 5; z0++) {
						@Pc(527) int dz1 = z0 + 5;

						if (dz1 >= 0 && dz1 < this.tileCountZ) {
							hueAccumulator += this.blendChroma[dz1];
							saturationAccumulator += this.blendSaturation[dz1];
							lightnessAccumulator += this.blendLightness[dz1];
							luminanceAccumulator += this.blendLuminance[dz1];
							magnitudeAccumulator += this.blendMagnitude[dz1];
						}

						@Pc(572) int dz2 = z0 - 5;
						if (dz2 >= 0 && dz2 < this.tileCountZ) {
							hueAccumulator -= this.blendChroma[dz2];
							saturationAccumulator -= this.blendSaturation[dz2];
							lightnessAccumulator -= this.blendLightness[dz2];
							luminanceAccumulator -= this.blendLuminance[dz2];
							magnitudeAccumulator -= this.blendMagnitude[dz2];
						}

						if (z0 >= 1 && z0 < this.tileCountZ - 1 && (!lowMemory || (this.renderFlags[plane][x0][z0] & 0x10) == 0 && this.getRenderLevel(plane, x0, z0) == levelBuilt)) {
							@Pc(655) int underlayId = this.planeUnderlayFloorIndices[plane][x0][z0] & 0xFF;
							@Pc(666) int overlayId = this.planeOverlayFloorIndices[plane][x0][z0] & 0xFF;

							if (underlayId <= 0 && overlayId <= 0) {
								continue;
							}

							@Pc(679) int heightSW = this.heightmap[plane][x0][z0];
							@Pc(690) int heightSE = this.heightmap[plane][x0 + 1][z0];
							@Pc(703) int heightNE = this.heightmap[plane][x0 + 1][z0 + 1];
							@Pc(714) int heightNW = this.heightmap[plane][x0][z0 + 1];

							@Pc(721) int lightSW = this.lightmap[x0][z0];
							@Pc(730) int lightSE = this.lightmap[x0 + 1][z0];
							@Pc(741) int lightNE = this.lightmap[x0 + 1][z0 + 1];
							@Pc(750) int lightNW = this.lightmap[x0][z0 + 1];

							@Pc(752) int baseColor = -1;
							@Pc(754) int tintColor = -1;

							if (underlayId > 0) {
								int hue = hueAccumulator * 256 / luminanceAccumulator;
								int saturation = saturationAccumulator / magnitudeAccumulator;
								@Pc(770) int lightness = lightnessAccumulator / magnitudeAccumulator;

								baseColor = this.hsl24To16(hue, saturation, lightness);

								hue += randomHueOffset & 0xFF;
								lightness += randomLightnessOffset;

								if (lightness < 0) {
									lightness = 0;
								} else if (lightness > 255) {
									lightness = 255;
								}

								tintColor = this.hsl24To16(hue, saturation, lightness);
							}

							if (plane > 0) {
								@Pc(807) boolean occludes = true;

								if (underlayId == 0 && this.planeOverlayTypes[plane][x0][z0] != 0) {
									occludes = false;
								}

								if (overlayId > 0 && !FloType.instances[overlayId - 1].occlude) {
									occludes = false;
								}

								if (occludes && heightSW == heightSE && heightSW == heightNE && heightSW == heightNW) {
									this.occludeFlags[plane][x0][z0] |= 0x924;
								}
							}

							int shadeColor = 0;
							if (baseColor != -1) {
								shadeColor = Draw3D.palette[mulHSL(tintColor, 96)];
							}

							if (overlayId == 0) {
								mapSquare.addTile(plane, x0, z0, 0, 0, -1, heightSW, heightSE, heightNE, heightNW, mulHSL(baseColor, lightSW), mulHSL(baseColor, lightSE), mulHSL(baseColor, lightNE), mulHSL(baseColor, lightNW), 0, 0, 0, 0, shadeColor, 0);
							} else {
								int shape = this.planeOverlayTypes[plane][x0][z0] + 1;
								@Pc(919) byte rotation = this.planeOverlayRotations[plane][x0][z0];
								@Pc(925) FloType flo = FloType.instances[overlayId - 1];
								@Pc(928) int textureId = flo.texture;
								@Pc(936) int hsl;
								@Pc(934) int rgb;

								if (textureId >= 0) {
									rgb = Draw3D.getAverageTextureRgb(textureId);
									hsl = -1;
								} else if (flo.rgb == 16711935) {
									rgb = 0;
									hsl = -2;
									textureId = -1;
								} else {
									hsl = this.hsl24To16(flo.hue, flo.saturation, flo.lightness);
									rgb = Draw3D.palette[this.adjustLightness(flo.blendHueMultiplier, 96)];
								}

								mapSquare.addTile(plane, x0, z0, shape, rotation, textureId, heightSW, heightSE, heightNE, heightNW, mulHSL(baseColor, lightSW), mulHSL(baseColor, lightSE), mulHSL(baseColor, lightNE), mulHSL(baseColor, lightNW), this.adjustLightness(hsl, lightSW), this.adjustLightness(hsl, lightSE), this.adjustLightness(hsl, lightNE), this.adjustLightness(hsl, lightNW), shadeColor, rgb);
							}
						}
					}
				}
			}

			for (int z = 1; z < this.tileCountZ - 1; z++) {
				for (int x = 1; x < this.tileCountX - 1; x++) {
					mapSquare.setPhysicalLevel(plane, x, z, this.getRenderLevel(plane, x, z));
				}
			}
		}

		if (!fullbright) {
			mapSquare.applyLighting(-10, 64, -50, 768, -50);
		}

		for (int x = 0; x < this.tileCountX; x++) {
			for (int z = 0; z < this.tileCountZ; z++) {
				if ((this.renderFlags[1][x][z] & 0x2) == 2) {
					mapSquare.setBridge(z, x);
				}
			}
		}

		if (!fullbright) {
			int wall0 = 0b001;
			int wall1 = 0b010;
			int floor = 0b100;

			for (@Pc(1127) int topLevel = 0; topLevel < 4; topLevel++) {
				if (topLevel > 0) {
					wall0 <<= 0x3;
					wall1 <<= 0x3;
					floor <<= 0x3;
				}

				for (@Pc(1145) int level = 0; level <= topLevel; level++) {
					for (int z = 0; z <= this.tileCountZ; z++) {
						for (int x = 0; x <= this.tileCountX; x++) {
							// buildWallOccludersX
							if ((this.occludeFlags[level][x][z] & wall0) != 0) {
								int minTileZ = z;
								int maxTileZ = z;
								int minLevel = level;
								int maxLevel = level;

								while (minTileZ > 0 && (this.occludeFlags[level][x][minTileZ - 1] & wall0) != 0) {
									minTileZ--;
								}

								while (maxTileZ < this.tileCountZ && (this.occludeFlags[level][x][maxTileZ + 1] & wall0) != 0) {
									maxTileZ++;
								}

								find_min_level:
								while (minLevel > 0) {
									for (int len = minTileZ; len <= maxTileZ; len++) {
										if ((this.occludeFlags[minLevel - 1][x][len] & wall0) == 0) {
											break find_min_level;
										}
									}

									minLevel--;
								}

								find_max_level:
								while (maxLevel < topLevel) {
									for (int len = minTileZ; len <= maxTileZ; len++) {
										if ((this.occludeFlags[maxLevel + 1][x][len] & wall0) == 0) {
											break find_max_level;
										}
									}

									maxLevel++;
								}

								int area = (maxLevel + 1 - minLevel) * (maxTileZ + 1 - minTileZ);
								if (area >= 8) {
									int minY = this.heightmap[maxLevel][x][minTileZ] - 240;
									int maxY = this.heightmap[minLevel][x][minTileZ];

									MapSquare.addOcclude(maxTileZ * 128 + 128, x * 128, maxY, 1, x * 128, topLevel, minY, minTileZ * 128);

									for (int tileLevel = minLevel; tileLevel <= maxLevel; tileLevel++) {
										for (int tileZ = minTileZ; tileZ <= maxTileZ; tileZ++) {
											this.occludeFlags[tileLevel][x][tileZ] &= ~wall0;
										}
									}
								}
							}

							// buildWallOccludersZ
							if ((this.occludeFlags[level][x][z] & wall1) != 0) {
								int minTileX = x;
								int maxTileX = x;
								int minLevel = level;
								int maxLevel = level;

								while (minTileX > 0 && (this.occludeFlags[level][minTileX - 1][z] & wall1) != 0) {
									minTileX--;
								}

								while (maxTileX < this.tileCountX && (this.occludeFlags[level][maxTileX + 1][z] & wall1) != 0) {
									maxTileX++;
								}

								find_min_level2:
								while (minLevel > 0) {
									for (int len = minTileX; len <= maxTileX; len++) {
										if ((this.occludeFlags[minLevel - 1][len][z] & wall1) == 0) {
											break find_min_level2;
										}
									}

									minLevel--;
								}

								find_max_level2:
								while (maxLevel < topLevel) {
									for (int len = minTileX; len <= maxTileX; len++) {
										if ((this.occludeFlags[maxLevel + 1][len][z] & wall1) == 0) {
											break find_max_level2;
										}
									}

									maxLevel++;
								}

								int area = (maxLevel + 1 - minLevel) * (maxTileX + 1 - minTileX);
								if (area >= 8) {
									int normalY = this.heightmap[maxLevel][minTileX][z] - 240;
									int normalZ = this.heightmap[minLevel][minTileX][z];

									MapSquare.addOcclude(z * 128, minTileX * 128, normalZ, 2, maxTileX * 128 + 128, topLevel, normalY, z * 128);

									for (int tileLevel = minLevel; tileLevel <= maxLevel; tileLevel++) {
										for (int tileX = minTileX; tileX <= maxTileX; tileX++) {
											this.occludeFlags[tileLevel][tileX][z] &= ~wall1;
										}
									}
								}
							}

							// buildFloorOccluders
							if ((this.occludeFlags[level][x][z] & floor) != 0) {
								int minTileX = x;
								int maxTileX = x;
								int minTileZ = z;
								int maxTileZ = z;

								while (minTileZ > 0 && (this.occludeFlags[level][x][minTileZ - 1] & floor) != 0) {
									minTileZ--;
								}

								while (maxTileZ < this.tileCountZ && (this.occludeFlags[level][x][maxTileZ + 1] & floor) != 0) {
									maxTileZ++;
								}

								find_min_tile_x:
								while (minTileX > 0) {
									for (int len = minTileZ; len <= maxTileZ; len++) {
										if ((this.occludeFlags[level][minTileX - 1][len] & floor) == 0) {
											break find_min_tile_x;
										}
									}

									minTileX--;
								}

								find_max_tile_x:
								while (maxTileX < this.tileCountX) {
									for (int len = minTileZ; len <= maxTileZ; len++) {
										if ((this.occludeFlags[level][maxTileX + 1][len] & floor) == 0) {
											break find_max_tile_x;
										}
									}

									maxTileX++;
								}

								if ((maxTileX + 1 - minTileX) * (maxTileZ + 1 - minTileZ) >= 4) {
									int y = this.heightmap[level][minTileX][minTileZ];

									MapSquare.addOcclude(maxTileZ * 128 + 128, minTileX * 128, y, 4, maxTileX * 128 + 128, topLevel, y, minTileZ * 128);

									for (int tileX = minTileX; tileX <= maxTileX; tileX++) {
										for (int tileZ = minTileZ; tileZ <= maxTileZ; tileZ++) {
											this.occludeFlags[level][tileX][tileZ] &= ~floor;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(IBII)I")
	private int getRenderLevel(@OriginalArg(0) int plane, @OriginalArg(2) int x, @OriginalArg(3) int z) {
		if ((this.renderFlags[plane][x][z] & 0x8) == 0) {
			return plane <= 0 || (this.renderFlags[1][x][z] & 0x2) == 0 ? plane : plane - 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!c", name = "e", descriptor = "(II)I")
	private int adjustLightness(@OriginalArg(0) int hsl, @OriginalArg(1) int scalar) {
		if (hsl == -2) {
			return 12345678;
		}

		if (hsl == -1) {
			if (scalar < 0) {
				scalar = 0;
			} else if (scalar > 127) {
				scalar = 127;
			}

			return 127 - scalar;
		} else {
			scalar = scalar * (hsl & 0x7F) / 128;

			if (scalar < 2) {
				scalar = 2;
			} else if (scalar > 126) {
				scalar = 126;
			}

			return (hsl & 0xFF80) + scalar;
		}
	}

	@OriginalMember(owner = "client!c", name = "b", descriptor = "(III)I")
	private int hsl24To16(@OriginalArg(0) int hue, @OriginalArg(1) int saturation, @OriginalArg(2) int lightness) {
		if (lightness > 179) {
			saturation /= 2;
		}

		if (lightness > 192) {
			saturation /= 2;
		}

		if (lightness > 217) {
			saturation /= 2;
		}

		if (lightness > 243) {
			saturation /= 2;
		}

		return (hue / 4 << 10) + (saturation / 32 << 7) + lightness / 2;
	}
}
