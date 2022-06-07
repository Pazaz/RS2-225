package com.jagex.game.runetek3.scenegraph;

import com.jagex.core.io.Buffer;
import com.jagex.core.utils.LinkedList;
import com.jagex.game.runetek3.config.FloType;
import com.jagex.game.runetek3.config.LocType;
import com.jagex.game.runetek3.config.SeqType;
import com.jagex.game.runetek3.entity.LocEntity;
import com.jagex.game.runetek3.graphics.Draw3D;
import com.jagex.game.runetek3.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!c")
public class Scene {

	public static final int VIEW_DIAMETER = 50;
	public static final int VIEW_RADIUS = VIEW_DIAMETER / 2;
	public static final int SAFE_RADIUS = VIEW_RADIUS + 1;
	public static final int FAR_Z = 140 * VIEW_RADIUS;
	public static final int NEAR_Z = 50;

	@OriginalMember(owner = "client!c", name = "e", descriptor = "I")
	public static int levelBuilt;

	@OriginalMember(owner = "client!c", name = "f", descriptor = "Z")
	private static final boolean fullbright = false;

	@OriginalMember(owner = "client!c", name = "d", descriptor = "Z")
	public static boolean lowMemory = true;

	@OriginalMember(owner = "client!c", name = "w", descriptor = "[I")
	private static final int[] WALL_ROTATION_TYPE1 = new int[] { 0x1, 0x2, 0x4, 0x8 };

	@OriginalMember(owner = "client!c", name = "x", descriptor = "[I")
	private static final int[] WALL_ROTATION_TYPE2 = new int[] { 0x10, 0x20, 0x40, 0x80 };

	@OriginalMember(owner = "client!c", name = "y", descriptor = "[I")
	private static final int[] WALL_DECO_ROT_SIZE_X_DIR = new int[] { 1, 0, -1, 0 };

	@OriginalMember(owner = "client!c", name = "z", descriptor = "[I")
	private static final int[] WALL_DECO_ROT_SIZE_Y_DIR = new int[] { 0, -1, 0, 1 };

	@OriginalMember(owner = "client!c", name = "A", descriptor = "I")
	private static int rand1 = (int) (Math.random() * 17.0D) - 8;

	@OriginalMember(owner = "client!c", name = "B", descriptor = "I")
	private static int rand2 = (int) (Math.random() * 33.0D) - 16;

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
	private final int[] blendedHue;

	@OriginalMember(owner = "client!c", name = "r", descriptor = "[I")
	private final int[] blendedSaturation;

	@OriginalMember(owner = "client!c", name = "s", descriptor = "[I")
	private final int[] blendedLightness;

	@OriginalMember(owner = "client!c", name = "t", descriptor = "[I")
	private final int[] blendedHueMultiplier;

	@OriginalMember(owner = "client!c", name = "u", descriptor = "[I")
	private final int[] blendDirectionTracker;

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(II)I")
	private static int getPerlinNoise(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(32) int local32 = getSmoothNoise(arg0 + 45365, arg1 + 91923, 4) + (getSmoothNoise(arg0 + 10294, arg1 + 37821, 2) - 128 >> 1) + (getSmoothNoise(arg0, arg1, 1) - 128 >> 2) - 128;
		local32 = (int) ((double) local32 * 0.3D) + 35;
		if (local32 < 10) {
			local32 = 10;
		} else if (local32 > 60) {
			local32 = 60;
		}
		return local32;
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(III)I")
	private static int getSmoothNoise(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(3) int local3 = arg0 / arg2;
		@Pc(9) int local9 = arg0 & arg2 - 1;
		@Pc(13) int local13 = arg1 / arg2;
		@Pc(19) int local19 = arg1 & arg2 - 1;
		@Pc(23) int local23 = getSmoothNoise2D(local3, local13);
		@Pc(29) int local29 = getSmoothNoise2D(local3 + 1, local13);
		@Pc(35) int local35 = getSmoothNoise2D(local3, local13 + 1);
		@Pc(43) int local43 = getSmoothNoise2D(local3 + 1, local13 + 1);
		@Pc(49) int local49 = getCosineLerp(local23, local29, local9, arg2);
		@Pc(55) int local55 = getCosineLerp(local35, local43, local9, arg2);
		return getCosineLerp(local49, local55, local19, arg2);
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(IIII)I")
	private static int getCosineLerp(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(11) int local11 = 65536 - Draw3D.cos[arg2 * 1024 / arg3] >> 1;
		return (arg0 * (65536 - local11) >> 16) + (arg1 * local11 >> 16);
	}

	@OriginalMember(owner = "client!c", name = "b", descriptor = "(II)I")
	private static int getSmoothNoise2D(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(31) int local31 = getNoise(arg0 - 1, arg1 - 1) + getNoise(arg0 + 1, arg1 - 1) + getNoise(arg0 - 1, arg1 + 1) + getNoise(arg0 + 1, arg1 + 1);
		@Pc(55) int local55 = getNoise(arg0 - 1, arg1) + getNoise(arg0 + 1, arg1) + getNoise(arg0, arg1 - 1) + getNoise(arg0, arg1 + 1);
		@Pc(59) int local59 = getNoise(arg0, arg1);
		return local31 / 16 + local55 / 8 + local59 / 4;
	}

	@OriginalMember(owner = "client!c", name = "c", descriptor = "(II)I")
	private static int getNoise(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(5) int local5 = arg0 + arg1 * 57;
		@Pc(11) int local11 = local5 << 13 ^ local5;
		@Pc(25) int local25 = local11 * (local11 * local11 * 15731 + 789221) + 1376312589 & Integer.MAX_VALUE;
		return local25 >> 19 & 0xFF;
	}

	@OriginalMember(owner = "client!c", name = "d", descriptor = "(II)I")
	private static int adjustHslLightness1(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (arg0 == -1) {
			return 12345678;
		}
		arg1 = arg1 * (arg0 & 0x7F) / 128;
		if (arg1 < 2) {
			arg1 = 2;
		} else if (arg1 > 126) {
			arg1 = 126;
		}
		return (arg0 & 0xFF80) + arg1;
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(ILclient!ob;Lclient!ec;II[[[IIIIILclient!r;I)V")
	public static void addLoc(@OriginalArg(0) int arg0, @OriginalArg(1) LinkedList arg1, @OriginalArg(2) CollisionMap arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int[][][] arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) MapSquare arg9, @OriginalArg(11) int arg10) {
		@Pc(15) int local15 = arg5[arg10][arg0][arg3];
		@Pc(25) int local25 = arg5[arg10][arg0 + 1][arg3];
		@Pc(37) int local37 = arg5[arg10][arg0 + 1][arg3 + 1];
		@Pc(47) int local47 = arg5[arg10][arg0][arg3 + 1];
		@Pc(57) int local57 = local15 + local25 + local37 + local47 >> 2;
		@Pc(60) LocType local60 = LocType.get(arg7);
		@Pc(72) int local72 = arg0 + (arg3 << 7) + (arg7 << 14) + 1073741824;
		if (!local60.interactable) {
			local72 += Integer.MIN_VALUE;
		}
		@Pc(86) byte local86 = (byte) ((arg4 << 6) + arg8);
		@Pc(99) Model local99;
		if (arg8 == 22) {
			local99 = local60.getModel(22, arg4, local15, local25, local37, local47, -1);
			arg9.addGroundDecoration(local99, arg0, local72, arg3, arg6, local86, local57);
			if (local60.blockwalk && local60.interactable) {
				arg2.setBlocked(arg3, arg0);
			}
			if (local60.anim != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 3, SeqType.instances[local60.anim], arg3, arg0));
			}
			return;
		}
		@Pc(174) int local174;
		if (arg8 == 10 || arg8 == 11) {
			local99 = local60.getModel(10, arg4, local15, local25, local37, local47, -1);
			if (local99 != null) {
				@Pc(161) int local161 = 0;
				if (arg8 == 11) {
					local161 += 256;
				}
				@Pc(177) int local177;
				if (arg4 == 1 || arg4 == 3) {
					local174 = local60.length;
					local177 = local60.width;
				} else {
					local174 = local60.width;
					local177 = local60.length;
				}
				arg9.addLocation(local57, arg6, null, local72, arg3, arg0, local174, local86, local99, local161, local177);
			}
			if (local60.blockwalk) {
				arg2.setLoc(arg4, local60.length, local60.width, arg0, arg3, local60.blockrange);
			}
			if (local60.anim != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 2, SeqType.instances[local60.anim], arg3, arg0));
			}
		} else if (arg8 >= 12) {
			local99 = local60.getModel(arg8, arg4, local15, local25, local37, local47, -1);
			arg9.addLocation(local57, arg6, null, local72, arg3, arg0, 1, local86, local99, 0, 1);
			if (local60.blockwalk) {
				arg2.setLoc(arg4, local60.length, local60.width, arg0, arg3, local60.blockrange);
			}
			if (local60.anim != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 2, SeqType.instances[local60.anim], arg3, arg0));
			}
		} else if (arg8 == 0) {
			local99 = local60.getModel(0, arg4, local15, local25, local37, local47, -1);
			arg9.addWall(0, local57, arg6, WALL_ROTATION_TYPE1[arg4], local99, null, arg0, local72, arg3, local86);
			if (local60.blockwalk) {
				arg2.setWall(arg4, arg3, arg0, local60.blockrange, arg8);
			}
			if (local60.anim != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 0, SeqType.instances[local60.anim], arg3, arg0));
			}
		} else if (arg8 == 1) {
			local99 = local60.getModel(1, arg4, local15, local25, local37, local47, -1);
			arg9.addWall(0, local57, arg6, WALL_ROTATION_TYPE2[arg4], local99, null, arg0, local72, arg3, local86);
			if (local60.blockwalk) {
				arg2.setWall(arg4, arg3, arg0, local60.blockrange, arg8);
			}
			if (local60.anim != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 0, SeqType.instances[local60.anim], arg3, arg0));
			}
		} else {
			@Pc(430) int local430;
			@Pc(452) Model local452;
			if (arg8 == 2) {
				local430 = arg4 + 1 & 0x3;
				@Pc(442) Model local442 = local60.getModel(2, arg4 + 4, local15, local25, local37, local47, -1);
				local452 = local60.getModel(2, local430, local15, local25, local37, local47, -1);
				arg9.addWall(WALL_ROTATION_TYPE1[local430], local57, arg6, WALL_ROTATION_TYPE1[arg4], local442, local452, arg0, local72, arg3, local86);
				if (local60.blockwalk) {
					arg2.setWall(arg4, arg3, arg0, local60.blockrange, arg8);
				}
				if (local60.anim != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 0, SeqType.instances[local60.anim], arg3, arg0));
				}
			} else if (arg8 == 3) {
				local99 = local60.getModel(3, arg4, local15, local25, local37, local47, -1);
				arg9.addWall(0, local57, arg6, WALL_ROTATION_TYPE2[arg4], local99, null, arg0, local72, arg3, local86);
				if (local60.blockwalk) {
					arg2.setWall(arg4, arg3, arg0, local60.blockrange, arg8);
				}
				if (local60.anim != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 0, SeqType.instances[local60.anim], arg3, arg0));
				}
			} else if (arg8 == 9) {
				local99 = local60.getModel(arg8, arg4, local15, local25, local37, local47, -1);
				arg9.addLocation(local57, arg6, null, local72, arg3, arg0, 1, local86, local99, 0, 1);
				if (local60.blockwalk) {
					arg2.setLoc(arg4, local60.length, local60.width, arg0, arg3, local60.blockrange);
				}
				if (local60.anim != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 2, SeqType.instances[local60.anim], arg3, arg0));
				}
			} else if (arg8 == 4) {
				local99 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, 0, local72, arg4 * 512, WALL_ROTATION_TYPE1[arg4], 0, arg0, local99, local86, arg6);
				if (local60.anim != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anim], arg3, arg0));
				}
			} else if (arg8 == 5) {
				local430 = 16;
				local174 = arg9.getWallBitset(arg6, arg0, arg3);
				if (local174 > 0) {
					local430 = LocType.get(local174 >> 14 & 0x7FFF).walloff;
				}
				local452 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, WALL_DECO_ROT_SIZE_Y_DIR[arg4] * local430, local72, arg4 * 512, WALL_ROTATION_TYPE1[arg4], WALL_DECO_ROT_SIZE_X_DIR[arg4] * local430, arg0, local452, local86, arg6);
				if (local60.anim != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anim], arg3, arg0));
				}
			} else if (arg8 == 6) {
				local99 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, 0, local72, arg4, 256, 0, arg0, local99, local86, arg6);
				if (local60.anim != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anim], arg3, arg0));
				}
			} else if (arg8 == 7) {
				local99 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, 0, local72, arg4, 512, 0, arg0, local99, local86, arg6);
				if (local60.anim != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anim], arg3, arg0));
				}
			} else if (arg8 == 8) {
				local99 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, 0, local72, arg4, 768, 0, arg0, local99, local86, arg6);
				if (local60.anim != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anim], arg3, arg0));
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "<init>", descriptor = "(I[[[BI[[[II)V")
	public Scene(@OriginalArg(0) int z, @OriginalArg(1) byte[][][] flags, @OriginalArg(2) int x, @OriginalArg(3) int[][][] heightmap) {
		this.tileCountX = x;
		this.tileCountZ = z;
		this.heightmap = heightmap;
		this.renderFlags = flags;
		this.planeUnderlayFloorIndices = new byte[4][this.tileCountX][this.tileCountZ];
		this.planeOverlayFloorIndices = new byte[4][this.tileCountX][this.tileCountZ];
		this.planeOverlayTypes = new byte[4][this.tileCountX][this.tileCountZ];
		this.planeOverlayRotations = new byte[4][this.tileCountX][this.tileCountZ];
		this.occludeFlags = new int[4][this.tileCountX + 1][this.tileCountZ + 1];
		this.shadowmap = new byte[4][this.tileCountX + 1][this.tileCountZ + 1];
		this.lightmap = new int[this.tileCountX + 1][this.tileCountZ + 1];
		this.blendedHue = new int[this.tileCountZ];
		this.blendedSaturation = new int[this.tileCountZ];
		this.blendedLightness = new int[this.tileCountZ];
		this.blendedHueMultiplier = new int[this.tileCountZ];
		this.blendDirectionTracker = new int[this.tileCountZ];
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(IIIII)V")
	public void clearLandscape(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(3) byte local3 = 0;
		for (@Pc(11) int local11 = 0; local11 < FloType.count; local11++) {
			if (FloType.instances[local11].name.equalsIgnoreCase("water")) {
				local3 = (byte) (local11 + 1);
				break;
			}
		}
		for (@Pc(33) int local33 = arg1; local33 < arg1 + 64; local33++) {
			for (@Pc(37) int local37 = arg0; local37 < arg0 + 64; local37++) {
				if (local37 >= 0 && local37 < this.tileCountX && local33 >= 0 && local33 < this.tileCountZ) {
					this.planeOverlayFloorIndices[0][local37][local33] = local3;
					for (@Pc(62) int local62 = 0; local62 < 4; local62++) {
						this.heightmap[local62][local37][local33] = 0;
						this.renderFlags[local62][local37][local33] = 0;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "([BIIIII)V")
	public void readLandscape(@OriginalArg(0) byte[] src, @OriginalArg(1) int arg1, @OriginalArg(3) int srcZ, @OriginalArg(4) int srcX, @OriginalArg(5) int arg4, boolean reuseHeightmap) {
		@Pc(7) Buffer b = new Buffer(src);
		for (@Pc(20) int plane = 0; plane < 4; plane++) {
			for (@Pc(24) int x = 0; x < 64; x++) {
				for (@Pc(28) int z = 0; z < 64; z++) {
					@Pc(34) int tileX = x + srcX;
					@Pc(38) int tileZ = z + srcZ;
					@Pc(60) int opcode;
					if (tileX >= 0 && tileX < 104 && tileZ >= 0 && tileZ < 104) {
						this.renderFlags[plane][tileX][tileZ] = 0;
						while (true) {
							opcode = b.g1();
							if (opcode == 0) {
								if (plane == 0) {
									this.heightmap[0][tileX][tileZ] = -getPerlinNoise(tileX + arg1 + 932731, tileZ + 556238 + arg4) * 8;
								} else {
									this.heightmap[plane][tileX][tileZ] = this.heightmap[plane - 1][tileX][tileZ] - 240;
								}
								break;
							}
							if (opcode == 1) {
								@Pc(116) int height = b.g1();
								if (reuseHeightmap) {
									this.heightmap[plane][tileX][tileZ] = this.heightmap[plane][tileX][tileZ];
								} else {
									if (height == 1) {
										height = 0;
									}
									if (plane == 0) {
										this.heightmap[0][tileX][tileZ] = -height * 8;
									} else {
										this.heightmap[plane][tileX][tileZ] = this.heightmap[plane - 1][tileX][tileZ] - height * 8;
									}
								}
								break;
							}
							if (opcode <= 49) {
								this.planeOverlayFloorIndices[plane][tileX][tileZ] = b.g1b();
								this.planeOverlayTypes[plane][tileX][tileZ] = (byte) ((opcode - 2) / 4);
								this.planeOverlayRotations[plane][tileX][tileZ] = (byte) (opcode - 2 & 0x3);
							} else if (opcode <= 81) {
								this.renderFlags[plane][tileX][tileZ] = (byte) (opcode - 49);
							} else {
								this.planeUnderlayFloorIndices[plane][tileX][tileZ] = (byte) (opcode - 81);
							}
						}
					} else {
						while (true) {
							opcode = b.g1();
							if (opcode == 0) {
								break;
							}
							if (opcode == 1) {
								b.g1();
								break;
							}
							if (opcode <= 49) {
								b.g1();
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "([BLclient!r;[Lclient!ec;Lclient!ob;ZII)V")
	public void readLocs(@OriginalArg(0) byte[] arg0, @OriginalArg(1) MapSquare arg1, @OriginalArg(2) CollisionMap[] arg2, @OriginalArg(3) LinkedList arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(7) Buffer local7 = new Buffer(arg0);
		@Pc(19) int local19 = -1;
		while (true) {
			@Pc(22) int local22 = local7.gsmarts();
			if (local22 == 0) {
				return;
			}
			local19 += local22;
			@Pc(30) int local30 = 0;
			while (true) {
				@Pc(33) int local33 = local7.gsmarts();
				if (local33 == 0) {
					break;
				}
				local30 += local33 - 1;
				@Pc(45) int local45 = local30 & 0x3F;
				@Pc(51) int local51 = local30 >> 6 & 0x3F;
				@Pc(55) int local55 = local30 >> 12;
				@Pc(58) int local58 = local7.g1();
				@Pc(62) int local62 = local58 >> 2;
				@Pc(66) int local66 = local58 & 0x3;
				@Pc(70) int local70 = local51 + arg5;
				@Pc(74) int local74 = local45 + arg4;
				if (local70 > 0 && local74 > 0 && local70 < 103 && local74 < 103) {
					@Pc(86) int local86 = local55;
					if ((this.renderFlags[1][local70][local74] & 0x2) == 2) {
						local86 = local55 - 1;
					}
					@Pc(101) CollisionMap local101 = null;
					if (local86 >= 0) {
						local101 = arg2[local86];
					}
					this.addLoc(local101, local55, local74, local66, local62, arg1, arg3, local19, local70);
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(Lclient!ec;ZIIIILclient!r;Lclient!ob;II)V")
	private void addLoc(@OriginalArg(0) CollisionMap arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) MapSquare arg5, @OriginalArg(7) LinkedList arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8) {
		if (lowMemory) {
			if ((this.renderFlags[arg1][arg8][arg2] & 0x10) != 0) {
				return;
			}
			if (this.getRenderLevel(arg1, arg8, arg2) != levelBuilt) {
				return;
			}
		}
		@Pc(36) int local36 = this.heightmap[arg1][arg8][arg2];
		@Pc(47) int local47 = this.heightmap[arg1][arg8 + 1][arg2];
		@Pc(60) int local60 = this.heightmap[arg1][arg8 + 1][arg2 + 1];
		@Pc(71) int local71 = this.heightmap[arg1][arg8][arg2 + 1];
		@Pc(81) int local81 = local36 + local47 + local60 + local71 >> 2;
		@Pc(84) LocType local84 = LocType.get(arg7);
		@Pc(96) int local96 = arg8 + (arg2 << 7) + (arg7 << 14) + 1073741824;
		if (!local84.interactable) {
			local96 += Integer.MIN_VALUE;
		}
		@Pc(110) byte local110 = (byte) ((arg3 << 6) + arg4);
		@Pc(132) Model local132;
		if (arg4 != 22) {
			@Pc(209) int local209;
			if (arg4 == 10 || arg4 == 11) {
				local132 = local84.getModel(10, arg3, local36, local47, local60, local71, -1);
				if (local132 != null) {
					@Pc(196) int local196 = 0;
					if (arg4 == 11) {
						local196 += 256;
					}
					@Pc(212) int local212;
					if (arg3 == 1 || arg3 == 3) {
						local209 = local84.length;
						local212 = local84.width;
					} else {
						local209 = local84.width;
						local212 = local84.length;
					}
					if (arg5.addLocation(local81, arg1, null, local96, arg2, arg8, local209, local110, local132, local196, local212) && local84.active) {
						for (@Pc(240) int local240 = 0; local240 <= local209; local240++) {
							for (@Pc(244) int local244 = 0; local244 <= local212; local244++) {
								@Pc(251) int local251 = local132.lengthXZ / 4;
								if (local251 > 30) {
									local251 = 30;
								}
								if (local251 > this.shadowmap[arg1][arg8 + local240][arg2 + local244]) {
									this.shadowmap[arg1][arg8 + local240][arg2 + local244] = (byte) local251;
								}
							}
						}
					}
				}
				if (local84.blockwalk && arg0 != null) {
					arg0.setLoc(arg3, local84.length, local84.width, arg8, arg2, local84.blockrange);
				}
				if (local84.anim != -1) {
					arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 2, SeqType.instances[local84.anim], arg2, arg8));
				}
			} else if (arg4 >= 12) {
				local132 = local84.getModel(arg4, arg3, local36, local47, local60, local71, -1);
				arg5.addLocation(local81, arg1, null, local96, arg2, arg8, 1, local110, local132, 0, 1);
				if (arg4 >= 12 && arg4 <= 17 && arg4 != 13 && arg1 > 0) {
					this.occludeFlags[arg1][arg8][arg2] |= 0x924;
				}
				if (local84.blockwalk && arg0 != null) {
					arg0.setLoc(arg3, local84.length, local84.width, arg8, arg2, local84.blockrange);
				}
				if (local84.anim != -1) {
					arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 2, SeqType.instances[local84.anim], arg2, arg8));
				}
			} else if (arg4 == 0) {
				local132 = local84.getModel(0, arg3, local36, local47, local60, local71, -1);
				arg5.addWall(0, local81, arg1, WALL_ROTATION_TYPE1[arg3], local132, null, arg8, local96, arg2, local110);
				if (arg3 == 0) {
					if (local84.active) {
						this.shadowmap[arg1][arg8][arg2] = 50;
						this.shadowmap[arg1][arg8][arg2 + 1] = 50;
					}
					if (local84.occlude) {
						this.occludeFlags[arg1][arg8][arg2] |= 0x249;
					}
				} else if (arg3 == 1) {
					if (local84.active) {
						this.shadowmap[arg1][arg8][arg2 + 1] = 50;
						this.shadowmap[arg1][arg8 + 1][arg2 + 1] = 50;
					}
					if (local84.occlude) {
						this.occludeFlags[arg1][arg8][arg2 + 1] |= 0x492;
					}
				} else if (arg3 == 2) {
					if (local84.active) {
						this.shadowmap[arg1][arg8 + 1][arg2] = 50;
						this.shadowmap[arg1][arg8 + 1][arg2 + 1] = 50;
					}
					if (local84.occlude) {
						this.occludeFlags[arg1][arg8 + 1][arg2] |= 0x249;
					}
				} else if (arg3 == 3) {
					if (local84.active) {
						this.shadowmap[arg1][arg8][arg2] = 50;
						this.shadowmap[arg1][arg8 + 1][arg2] = 50;
					}
					if (local84.occlude) {
						this.occludeFlags[arg1][arg8][arg2] |= 0x492;
					}
				}
				if (local84.blockwalk && arg0 != null) {
					arg0.setWall(arg3, arg2, arg8, local84.blockrange, arg4);
				}
				if (local84.anim != -1) {
					arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 0, SeqType.instances[local84.anim], arg2, arg8));
				}
				if (local84.walloff != 16) {
					arg5.setWallDecoration(arg1, arg2, arg8, local84.walloff);
				}
			} else if (arg4 == 1) {
				local132 = local84.getModel(1, arg3, local36, local47, local60, local71, -1);
				arg5.addWall(0, local81, arg1, WALL_ROTATION_TYPE2[arg3], local132, null, arg8, local96, arg2, local110);
				if (local84.active) {
					if (arg3 == 0) {
						this.shadowmap[arg1][arg8][arg2 + 1] = 50;
					} else if (arg3 == 1) {
						this.shadowmap[arg1][arg8 + 1][arg2 + 1] = 50;
					} else if (arg3 == 2) {
						this.shadowmap[arg1][arg8 + 1][arg2] = 50;
					} else if (arg3 == 3) {
						this.shadowmap[arg1][arg8][arg2] = 50;
					}
				}
				if (local84.blockwalk && arg0 != null) {
					arg0.setWall(arg3, arg2, arg8, local84.blockrange, arg4);
				}
				if (local84.anim != -1) {
					arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 0, SeqType.instances[local84.anim], arg2, arg8));
				}
			} else {
				@Pc(810) int local810;
				@Pc(832) Model local832;
				if (arg4 == 2) {
					local810 = arg3 + 1 & 0x3;
					@Pc(822) Model local822 = local84.getModel(2, arg3 + 4, local36, local47, local60, local71, -1);
					local832 = local84.getModel(2, local810, local36, local47, local60, local71, -1);
					arg5.addWall(WALL_ROTATION_TYPE1[local810], local81, arg1, WALL_ROTATION_TYPE1[arg3], local822, local832, arg8, local96, arg2, local110);
					if (local84.occlude) {
						if (arg3 == 0) {
							this.occludeFlags[arg1][arg8][arg2] |= 0x249;
							this.occludeFlags[arg1][arg8][arg2 + 1] |= 0x492;
						} else if (arg3 == 1) {
							this.occludeFlags[arg1][arg8][arg2 + 1] |= 0x492;
							this.occludeFlags[arg1][arg8 + 1][arg2] |= 0x249;
						} else if (arg3 == 2) {
							this.occludeFlags[arg1][arg8 + 1][arg2] |= 0x249;
							this.occludeFlags[arg1][arg8][arg2] |= 0x492;
						} else if (arg3 == 3) {
							this.occludeFlags[arg1][arg8][arg2] |= 0x492;
							this.occludeFlags[arg1][arg8][arg2] |= 0x249;
						}
					}
					if (local84.blockwalk && arg0 != null) {
						arg0.setWall(arg3, arg2, arg8, local84.blockrange, arg4);
					}
					if (local84.anim != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 0, SeqType.instances[local84.anim], arg2, arg8));
					}
					if (local84.walloff != 16) {
						arg5.setWallDecoration(arg1, arg2, arg8, local84.walloff);
					}
				} else if (arg4 == 3) {
					local132 = local84.getModel(3, arg3, local36, local47, local60, local71, -1);
					arg5.addWall(0, local81, arg1, WALL_ROTATION_TYPE2[arg3], local132, null, arg8, local96, arg2, local110);
					if (local84.active) {
						if (arg3 == 0) {
							this.shadowmap[arg1][arg8][arg2 + 1] = 50;
						} else if (arg3 == 1) {
							this.shadowmap[arg1][arg8 + 1][arg2 + 1] = 50;
						} else if (arg3 == 2) {
							this.shadowmap[arg1][arg8 + 1][arg2] = 50;
						} else if (arg3 == 3) {
							this.shadowmap[arg1][arg8][arg2] = 50;
						}
					}
					if (local84.blockwalk && arg0 != null) {
						arg0.setWall(arg3, arg2, arg8, local84.blockrange, arg4);
					}
					if (local84.anim != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 0, SeqType.instances[local84.anim], arg2, arg8));
					}
				} else if (arg4 == 9) {
					local132 = local84.getModel(arg4, arg3, local36, local47, local60, local71, -1);
					arg5.addLocation(local81, arg1, null, local96, arg2, arg8, 1, local110, local132, 0, 1);
					if (local84.blockwalk && arg0 != null) {
						arg0.setLoc(arg3, local84.length, local84.width, arg8, arg2, local84.blockrange);
					}
					if (local84.anim != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 2, SeqType.instances[local84.anim], arg2, arg8));
					}
				} else if (arg4 == 4) {
					local132 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, 0, local96, arg3 * 512, WALL_ROTATION_TYPE1[arg3], 0, arg8, local132, local110, arg1);
					if (local84.anim != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anim], arg2, arg8));
					}
				} else if (arg4 == 5) {
					local810 = 16;
					local209 = arg5.getWallBitset(arg1, arg8, arg2);
					if (local209 > 0) {
						local810 = LocType.get(local209 >> 14 & 0x7FFF).walloff;
					}
					local832 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, WALL_DECO_ROT_SIZE_Y_DIR[arg3] * local810, local96, arg3 * 512, WALL_ROTATION_TYPE1[arg3], WALL_DECO_ROT_SIZE_X_DIR[arg3] * local810, arg8, local832, local110, arg1);
					if (local84.anim != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anim], arg2, arg8));
					}
				} else if (arg4 == 6) {
					local132 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, 0, local96, arg3, 256, 0, arg8, local132, local110, arg1);
					if (local84.anim != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anim], arg2, arg8));
					}
				} else if (arg4 == 7) {
					local132 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, 0, local96, arg3, 512, 0, arg8, local132, local110, arg1);
					if (local84.anim != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anim], arg2, arg8));
					}
				} else if (arg4 == 8) {
					local132 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, 0, local96, arg3, 768, 0, arg8, local132, local110, arg1);
					if (local84.anim != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anim], arg2, arg8));
					}
				}
			}
		} else if (!lowMemory || local84.interactable || local84.forcedecor) {
			local132 = local84.getModel(22, arg3, local36, local47, local60, local71, -1);
			arg5.addGroundDecoration(local132, arg8, local96, arg2, arg1, local110, local81);
			if (local84.blockwalk && local84.interactable && arg0 != null) {
				arg0.setBlocked(arg2, arg8);
			}
			if (local84.anim != -1) {
				arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 3, SeqType.instances[local84.anim], arg2, arg8));
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(Lclient!r;I[Lclient!ec;)V")
	public void buildLandscape(@OriginalArg(0) MapSquare arg0, @OriginalArg(2) CollisionMap[] arg1) {
		@Pc(7) int local7;
		@Pc(11) int local11;
		@Pc(27) int local27;
		for (@Pc(3) int local3 = 0; local3 < 4; local3++) {
			for (local7 = 0; local7 < 104; local7++) {
				for (local11 = 0; local11 < 104; local11++) {
					if ((this.renderFlags[local3][local7][local11] & 0x1) == 1) {
						local27 = local3;
						if ((this.renderFlags[1][local7][local11] & 0x2) == 2) {
							local27 = local3 - 1;
						}
						if (local27 >= 0) {
							arg1[local27].setBlocked(local11, local7);
						}
					}
				}
			}
		}
		rand1 += (int) (Math.random() * 5.0D) - 2;
		if (rand1 < -8) {
			rand1 = -8;
		}
		if (rand1 > 8) {
			rand1 = 8;
		}
		rand2 += (int) (Math.random() * 5.0D) - 2;
		if (rand2 < -16) {
			rand2 = -16;
		}
		if (rand2 > 16) {
			rand2 = 16;
		}
		@Pc(133) int local133;
		@Pc(139) int local139;
		@Pc(141) int local141;
		@Pc(145) int local145;
		@Pc(169) int local169;
		@Pc(191) int local191;
		@Pc(204) int local204;
		@Pc(210) int local210;
		@Pc(214) int local214;
		@Pc(220) int local220;
		@Pc(236) int local236;
		@Pc(284) int local284;
		for (local7 = 0; local7 < 4; local7++) {
			@Pc(108) byte[][] local108 = this.shadowmap[local7];
			local133 = (int) Math.sqrt((double) 5100);
			local139 = local133 * 768 >> 8;
			for (local141 = 1; local141 < this.tileCountZ - 1; local141++) {
				for (local145 = 1; local145 < this.tileCountX - 1; local145++) {
					local169 = this.heightmap[local7][local145 + 1][local141] - this.heightmap[local7][local145 - 1][local141];
					local191 = this.heightmap[local7][local145][local141 + 1] - this.heightmap[local7][local145][local141 - 1];
					local204 = (int) Math.sqrt((double) (local169 * local169 + local191 * local191 + 65536));
					local210 = (local169 << 8) / local204;
					local214 = 65536 / local204;
					local220 = (local191 << 8) / local204;
					local236 = (local210 * -50 + local214 * -10 + local220 * -50) / local139 + 96;
					local284 = (local108[local145 - 1][local141] >> 2) + (local108[local145 + 1][local141] >> 3) + (local108[local145][local141 - 1] >> 2) + (local108[local145][local141 + 1] >> 3) + (local108[local145][local141] >> 1);
					this.lightmap[local145][local141] = local236 - local284;
				}
			}
			for (local145 = 0; local145 < this.tileCountZ; local145++) {
				this.blendedHue[local145] = 0;
				this.blendedSaturation[local145] = 0;
				this.blendedLightness[local145] = 0;
				this.blendedHueMultiplier[local145] = 0;
				this.blendDirectionTracker[local145] = 0;
			}
			for (local169 = -5; local169 < this.tileCountX + 5; local169++) {
				for (local191 = 0; local191 < this.tileCountZ; local191++) {
					local204 = local169 + 5;
					@Pc(419) int local419;
					if (local204 >= 0 && local204 < this.tileCountX) {
						local210 = this.planeUnderlayFloorIndices[local7][local204][local191] & 0xFF;
						if (local210 > 0) {
							@Pc(378) FloType local378 = FloType.instances[local210 - 1];
							this.blendedHue[local191] += local378.blendHue;
							this.blendedSaturation[local191] += local378.saturation;
							this.blendedLightness[local191] += local378.lightness;
							this.blendedHueMultiplier[local191] += local378.hsl16;
							local419 = this.blendDirectionTracker[local191]++;
						}
					}
					local210 = local169 - 5;
					if (local210 >= 0 && local210 < this.tileCountX) {
						local214 = this.planeUnderlayFloorIndices[local7][local210][local191] & 0xFF;
						if (local214 > 0) {
							@Pc(451) FloType local451 = FloType.instances[local214 - 1];
							this.blendedHue[local191] -= local451.blendHue;
							this.blendedSaturation[local191] -= local451.saturation;
							this.blendedLightness[local191] -= local451.lightness;
							this.blendedHueMultiplier[local191] -= local451.hsl16;
							local419 = this.blendDirectionTracker[local191]--;
						}
					}
				}
				if (local169 >= 1 && local169 < this.tileCountX - 1) {
					local204 = 0;
					local210 = 0;
					local214 = 0;
					local220 = 0;
					local236 = 0;
					for (local284 = -5; local284 < this.tileCountZ + 5; local284++) {
						@Pc(527) int local527 = local284 + 5;
						if (local527 >= 0 && local527 < this.tileCountZ) {
							local204 += this.blendedHue[local527];
							local210 += this.blendedSaturation[local527];
							local214 += this.blendedLightness[local527];
							local220 += this.blendedHueMultiplier[local527];
							local236 += this.blendDirectionTracker[local527];
						}
						@Pc(572) int local572 = local284 - 5;
						if (local572 >= 0 && local572 < this.tileCountZ) {
							local204 -= this.blendedHue[local572];
							local210 -= this.blendedSaturation[local572];
							local214 -= this.blendedLightness[local572];
							local220 -= this.blendedHueMultiplier[local572];
							local236 -= this.blendDirectionTracker[local572];
						}
						if (local284 >= 1 && local284 < this.tileCountZ - 1 && (!lowMemory || (this.renderFlags[local7][local169][local284] & 0x10) == 0 && this.getRenderLevel(local7, local169, local284) == levelBuilt)) {
							@Pc(655) int local655 = this.planeUnderlayFloorIndices[local7][local169][local284] & 0xFF;
							@Pc(666) int local666 = this.planeOverlayFloorIndices[local7][local169][local284] & 0xFF;
							if (local655 > 0 || local666 > 0) {
								@Pc(679) int local679 = this.heightmap[local7][local169][local284];
								@Pc(690) int local690 = this.heightmap[local7][local169 + 1][local284];
								@Pc(703) int local703 = this.heightmap[local7][local169 + 1][local284 + 1];
								@Pc(714) int local714 = this.heightmap[local7][local169][local284 + 1];
								@Pc(721) int local721 = this.lightmap[local169][local284];
								@Pc(730) int local730 = this.lightmap[local169 + 1][local284];
								@Pc(741) int local741 = this.lightmap[local169 + 1][local284 + 1];
								@Pc(750) int local750 = this.lightmap[local169][local284 + 1];
								@Pc(752) int local752 = -1;
								@Pc(754) int local754 = -1;
								@Pc(762) int local762;
								@Pc(766) int local766;
								if (local655 > 0) {
									local762 = local204 * 256 / local220;
									local766 = local210 / local236;
									@Pc(770) int local770 = local214 / local236;
									local752 = this.hsl24To16(local762, local766, local770);
									@Pc(782) int local782 = local762 + rand1 & 0xFF;
									local770 += rand2;
									if (local770 < 0) {
										local770 = 0;
									} else if (local770 > 255) {
										local770 = 255;
									}
									local754 = this.hsl24To16(local782, local766, local770);
								}
								if (local7 > 0) {
									@Pc(807) boolean local807 = true;
									if (local655 == 0 && this.planeOverlayTypes[local7][local169][local284] != 0) {
										local807 = false;
									}
									if (local666 > 0 && !FloType.instances[local666 - 1].occlude) {
										local807 = false;
									}
									if (local807 && local679 == local690 && local679 == local703 && local679 == local714) {
										this.occludeFlags[local7][local169][local284] |= 0x924;
									}
								}
								local762 = 0;
								if (local752 != -1) {
									local762 = Draw3D.palette[adjustHslLightness1(local754, 96)];
								}
								if (local666 == 0) {
									arg0.addTile(local7, local169, local284, 0, 0, -1, local679, local690, local703, local714, adjustHslLightness1(local752, local721), adjustHslLightness1(local752, local730), adjustHslLightness1(local752, local741), adjustHslLightness1(local752, local750), 0, 0, 0, 0, local762, 0);
								} else {
									local766 = this.planeOverlayTypes[local7][local169][local284] + 1;
									@Pc(919) byte local919 = this.planeOverlayRotations[local7][local169][local284];
									@Pc(925) FloType local925 = FloType.instances[local666 - 1];
									@Pc(928) int local928 = local925.textureIndex;
									@Pc(936) int local936;
									@Pc(934) int local934;
									if (local928 >= 0) {
										local934 = Draw3D.getAverageTextureRgb(local928);
										local936 = -1;
									} else if (local925.rgb == 16711935) {
										local934 = 0;
										local936 = -2;
										local928 = -1;
									} else {
										local936 = this.hsl24To16(local925.hue, local925.saturation, local925.lightness);
										local934 = Draw3D.palette[this.adjustHslLightness0(local925.blendHueMultiplier, 96)];
									}
									arg0.addTile(local7, local169, local284, local766, local919, local928, local679, local690, local703, local714, adjustHslLightness1(local752, local721), adjustHslLightness1(local752, local730), adjustHslLightness1(local752, local741), adjustHslLightness1(local752, local750), this.adjustHslLightness0(local936, local721), this.adjustHslLightness0(local936, local730), this.adjustHslLightness0(local936, local741), this.adjustHslLightness0(local936, local750), local762, local934);
								}
							}
						}
					}
				}
			}
			for (local191 = 1; local191 < this.tileCountZ - 1; local191++) {
				for (local204 = 1; local204 < this.tileCountX - 1; local204++) {
					arg0.setPhysicalLevel(local7, local204, local191, this.getRenderLevel(local7, local204, local191));
				}
			}
		}
		if (!fullbright) {
			arg0.applyLighting();
		}
		for (local11 = 0; local11 < this.tileCountX; local11++) {
			for (local27 = 0; local27 < this.tileCountZ; local27++) {
				if ((this.renderFlags[1][local11][local27] & 0x2) == 2) {
					arg0.setBridge(local27, local11);
				}
			}
		}
		if (fullbright) {
			return;
		}
		local27 = 1;
		@Pc(1123) int local1123 = 2;
		@Pc(1125) int local1125 = 4;
		for (@Pc(1127) int local1127 = 0; local1127 < 4; local1127++) {
			if (local1127 > 0) {
				local27 <<= 0x3;
				local1123 <<= 0x3;
				local1125 <<= 0x3;
			}
			for (@Pc(1145) int local1145 = 0; local1145 <= local1127; local1145++) {
				for (local133 = 0; local133 <= this.tileCountZ; local133++) {
					for (local139 = 0; local139 <= this.tileCountX; local139++) {
						if ((this.occludeFlags[local1145][local139][local133] & local27) != 0) {
							local141 = local133;
							local145 = local133;
							local169 = local1145;
							local191 = local1145;
							while (local141 > 0 && (this.occludeFlags[local1145][local139][local141 - 1] & local27) != 0) {
								local141--;
							}
							while (local145 < this.tileCountZ && (this.occludeFlags[local1145][local139][local145 + 1] & local27) != 0) {
								local145++;
							}
							label329: while (local169 > 0) {
								for (local204 = local141; local204 <= local145; local204++) {
									if ((this.occludeFlags[local169 - 1][local139][local204] & local27) == 0) {
										break label329;
									}
								}
								local169--;
							}
							label318: while (local191 < local1127) {
								for (local204 = local141; local204 <= local145; local204++) {
									if ((this.occludeFlags[local191 + 1][local139][local204] & local27) == 0) {
										break label318;
									}
								}
								local191++;
							}
							local204 = (local191 + 1 - local169) * (local145 - local141 + 1);
							if (local204 >= 8) {
								local214 = this.heightmap[local191][local139][local141] - 240;
								local220 = this.heightmap[local169][local139][local141];
								MapSquare.addOclude(local145 * 128 + 128, local139 * 128, local220, 1, local139 * 128, local1127, local214, local141 * 128);
								for (local236 = local169; local236 <= local191; local236++) {
									for (local284 = local141; local284 <= local145; local284++) {
										this.occludeFlags[local236][local139][local284] &= ~local27;
									}
								}
							}
						}
						if ((this.occludeFlags[local1145][local139][local133] & local1123) != 0) {
							local141 = local139;
							local145 = local139;
							local169 = local1145;
							local191 = local1145;
							while (local141 > 0 && (this.occludeFlags[local1145][local141 - 1][local133] & local1123) != 0) {
								local141--;
							}
							while (local145 < this.tileCountX && (this.occludeFlags[local1145][local145 + 1][local133] & local1123) != 0) {
								local145++;
							}
							label382: while (local169 > 0) {
								for (local204 = local141; local204 <= local145; local204++) {
									if ((this.occludeFlags[local169 - 1][local204][local133] & local1123) == 0) {
										break label382;
									}
								}
								local169--;
							}
							label371: while (local191 < local1127) {
								for (local204 = local141; local204 <= local145; local204++) {
									if ((this.occludeFlags[local191 + 1][local204][local133] & local1123) == 0) {
										break label371;
									}
								}
								local191++;
							}
							local204 = (local191 + 1 - local169) * (local145 - local141 + 1);
							if (local204 >= 8) {
								local214 = this.heightmap[local191][local141][local133] - 240;
								local220 = this.heightmap[local169][local141][local133];
								MapSquare.addOclude(local133 * 128, local141 * 128, local220, 2, local145 * 128 + 128, local1127, local214, local133 * 128);
								for (local236 = local169; local236 <= local191; local236++) {
									for (local284 = local141; local284 <= local145; local284++) {
										this.occludeFlags[local236][local284][local133] &= ~local1123;
									}
								}
							}
						}
						if ((this.occludeFlags[local1145][local139][local133] & local1125) != 0) {
							local141 = local139;
							local145 = local139;
							local169 = local133;
							local191 = local133;
							while (local169 > 0 && (this.occludeFlags[local1145][local139][local169 - 1] & local1125) != 0) {
								local169--;
							}
							while (local191 < this.tileCountZ && (this.occludeFlags[local1145][local139][local191 + 1] & local1125) != 0) {
								local191++;
							}
							label435: while (local141 > 0) {
								for (local204 = local169; local204 <= local191; local204++) {
									if ((this.occludeFlags[local1145][local141 - 1][local204] & local1125) == 0) {
										break label435;
									}
								}
								local141--;
							}
							label424: while (local145 < this.tileCountX) {
								for (local204 = local169; local204 <= local191; local204++) {
									if ((this.occludeFlags[local1145][local145 + 1][local204] & local1125) == 0) {
										break label424;
									}
								}
								local145++;
							}
							if ((local145 + 1 - local141) * (local191 - local169 + 1) >= 4) {
								local204 = this.heightmap[local1145][local141][local169];
								MapSquare.addOclude(local191 * 128 + 128, local141 * 128, local204, 4, local145 * 128 + 128, local1127, local204, local169 * 128);
								for (local210 = local141; local210 <= local145; local210++) {
									for (local214 = local169; local214 <= local191; local214++) {
										this.occludeFlags[local1145][local210][local214] &= ~local1125;
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
	private int getRenderLevel(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if ((this.renderFlags[arg0][arg1][arg2] & 0x8) == 0) {
			return arg0 <= 0 || (this.renderFlags[1][arg1][arg2] & 0x2) == 0 ? arg0 : arg0 - 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!c", name = "e", descriptor = "(II)I")
	private int adjustHslLightness0(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (arg0 == -2) {
			return 12345678;
		} else if (arg0 == -1) {
			if (arg1 < 0) {
				arg1 = 0;
			} else if (arg1 > 127) {
				arg1 = 127;
			}
			return 127 - arg1;
		} else {
			arg1 = arg1 * (arg0 & 0x7F) / 128;
			if (arg1 < 2) {
				arg1 = 2;
			} else if (arg1 > 126) {
				arg1 = 126;
			}
			return (arg0 & 0xFF80) + arg1;
		}
	}

	@OriginalMember(owner = "client!c", name = "b", descriptor = "(III)I")
	private int hsl24To16(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (arg2 > 179) {
			arg1 /= 2;
		}
		if (arg2 > 192) {
			arg1 /= 2;
		}
		if (arg2 > 217) {
			arg1 /= 2;
		}
		if (arg2 > 243) {
			arg1 /= 2;
		}
		return (arg0 / 4 << 10) + (arg1 / 32 << 7) + arg2 / 2;
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "Z")
	private static final boolean flowObfuscator3 = true;

	@OriginalMember(owner = "client!c", name = "b", descriptor = "Z")
	private final boolean flowObfuscator2 = true;

	@OriginalMember(owner = "client!c", name = "c", descriptor = "I")
	private int flowObfuscator1 = 8;

}
