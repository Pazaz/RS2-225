package com.jagex.game.runetek3.graphics;

import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!gb")
public class Draw3D extends Draw2D {

	@OriginalMember(owner = "client!gb", name = "B", descriptor = "Z")
	public static boolean testX;

	@OriginalMember(owner = "client!gb", name = "C", descriptor = "Z")
	private static boolean opaque;

	@OriginalMember(owner = "client!gb", name = "E", descriptor = "I")
	public static int alpha;

	@OriginalMember(owner = "client!gb", name = "F", descriptor = "I")
	public static int centerX3D;

	@OriginalMember(owner = "client!gb", name = "G", descriptor = "I")
	public static int centerY3D;

	@OriginalMember(owner = "client!gb", name = "L", descriptor = "[I")
	public static int[] offsets;

	@OriginalMember(owner = "client!gb", name = "M", descriptor = "I")
	private static int textureCount;

	@OriginalMember(owner = "client!gb", name = "Q", descriptor = "I")
	private static int poolSize;

	@OriginalMember(owner = "client!gb", name = "R", descriptor = "[[I")
	private static int[][] texelPool;

	@OriginalMember(owner = "client!gb", name = "U", descriptor = "I")
	public static int cycle;

	@OriginalMember(owner = "client!gb", name = "A", descriptor = "Z")
	public static boolean lowMemory = true;

	@OriginalMember(owner = "client!gb", name = "D", descriptor = "Z")
	public static boolean jagged = true;

	@OriginalMember(owner = "client!gb", name = "H", descriptor = "[I")
	private static int[] reciprical15 = new int[512];

	@OriginalMember(owner = "client!gb", name = "I", descriptor = "[I")
	public static int[] reciprical16 = new int[2048];

	@OriginalMember(owner = "client!gb", name = "J", descriptor = "[I")
	public static int[] sin = new int[2048];

	@OriginalMember(owner = "client!gb", name = "K", descriptor = "[I")
	public static int[] cos = new int[2048];

	@OriginalMember(owner = "client!gb", name = "N", descriptor = "[Lclient!ib;")
	public static IndexedSprite[] textures;

	@OriginalMember(owner = "client!gb", name = "O", descriptor = "[Z")
	private static boolean[] textureHasTransparency;

	@OriginalMember(owner = "client!gb", name = "P", descriptor = "[I")
	private static int[] textureColors;

	@OriginalMember(owner = "client!gb", name = "S", descriptor = "[[I")
	private static int[][] activeTexels;

	@OriginalMember(owner = "client!gb", name = "T", descriptor = "[I")
	public static int[] textureCycles;

	@OriginalMember(owner = "client!gb", name = "V", descriptor = "[I")
	public static int[] palette;

	@OriginalMember(owner = "client!gb", name = "W", descriptor = "[[I")
	private static int[][] texturePalettes;

	public static int renderWidth = 512;
	public static int renderHeight = 334;

	static {
		for (@Pc(23) int i = 1; i < 512; i++) {
			reciprical15[i] = 32768 / i;
		}
		for (@Pc(36) int i = 1; i < 2048; i++) {
			reciprical16[i] = 65536 / i;
		}
		for (@Pc(49) int i = 0; i < 2048; i++) {
			sin[i] = (int) (Math.sin((double) i * 0.0030679615D) * 65536.0D);
			cos[i] = (int) (Math.cos((double) i * 0.0030679615D) * 65536.0D);
		}
		textures = new IndexedSprite[50];
		textureHasTransparency = new boolean[50];
		textureColors = new int[50];
		activeTexels = new int[50][];
		textureCycles = new int[50];
		palette = new int[65536];
		texturePalettes = new int[50][];
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(Z)V")
	public static void unload() {
		reciprical15 = null;
		reciprical16 = null;
		sin = null;
		cos = null;
		offsets = null;
		textures = null;
		textureHasTransparency = null;
		textureColors = null;
		texelPool = null;
		activeTexels = null;
		textureCycles = null;
		palette = null;
		texturePalettes = null;
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(I)V")
	public static void prepareOffsets() {
		@Pc(5) int local5;
		offsets = new int[Draw2D.height];
		for (local5 = 0; local5 < Draw2D.height; local5++) {
			offsets[local5] = Draw2D.width * local5;
		}
		centerX3D = Draw2D.width / 2;
		centerY3D = Draw2D.height / 2;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(III)V")
	public static void prepareOffsets(@OriginalArg(1) int width, @OriginalArg(0) int height) {
		offsets = new int[height];
		for (@Pc(12) int local12 = 0; local12 < height; local12++) {
			offsets[local12] = width * local12;
		}
		centerX3D = width / 2;
		centerY3D = height / 2;
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(Z)V")
	public static void clearPools() {
		texelPool = null;
		for (@Pc(6) int local6 = 0; local6 < 50; local6++) {
			activeTexels[local6] = null;
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(II)V")
	public static void setupPools() {
		if (texelPool != null) {
			return;
		}
		poolSize = 20;
		if (lowMemory) {
			texelPool = new int[poolSize][16384];
		} else {
			texelPool = new int[poolSize][65536];
		}
		for (@Pc(30) int local30 = 0; local30 < 50; local30++) {
			activeTexels[local30] = null;
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(BLclient!ub;)V")
	public static void unpackTextures(@OriginalArg(1) FileArchive arg0) {
		textureCount = 0;
		for (@Pc(9) int local9 = 0; local9 < 50; local9++) {
			try {
				textures[local9] = new IndexedSprite(arg0, String.valueOf(local9), 0);
				if (lowMemory && textures[local9].clipWidth == 128) {
					textures[local9].shrink();
				} else {
					textures[local9].crop();
				}
				textureCount++;
			} catch (@Pc(47) Exception ignored) {
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(II)I")
	public static int getAverageTextureRgb(@OriginalArg(1) int arg0) {
		if (textureColors[arg0] != 0) {
			return textureColors[arg0];
		}
		@Pc(13) int local13 = 0;
		@Pc(15) int local15 = 0;
		@Pc(17) int local17 = 0;
		@Pc(22) int local22 = texturePalettes[arg0].length;
		for (@Pc(24) int local24 = 0; local24 < local22; local24++) {
			local13 += texturePalettes[arg0][local24] >> 16 & 0xFF;
			local15 += texturePalettes[arg0][local24] >> 8 & 0xFF;
			local17 += texturePalettes[arg0][local24] & 0xFF;
		}
		@Pc(80) int local80 = (local13 / local22 << 16) + (local15 / local22 << 8) + local17 / local22;
		local80 = powRgb(local80, 1.4D);
		if (local80 == 0) {
			local80 = 1;
		}
		textureColors[arg0] = local80;
		return local80;
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(II)V")
	public static void updateTexture(@OriginalArg(0) int arg0) {
		if (activeTexels[arg0] != null) {
			texelPool[poolSize++] = activeTexels[arg0];
			activeTexels[arg0] = null;
		}
	}

	@OriginalMember(owner = "client!gb", name = "d", descriptor = "(I)[I")
	private static int[] getTexels(@OriginalArg(0) int arg0) {
		textureCycles[arg0] = cycle++;
		if (activeTexels[arg0] != null) {
			return activeTexels[arg0];
		}
		@Pc(27) int[] local27;
		@Pc(39) int local39;
		if (poolSize > 0) {
			local27 = texelPool[--poolSize];
			texelPool[poolSize] = null;
		} else {
			@Pc(35) int local35 = 0;
			@Pc(37) int local37 = -1;
			for (local39 = 0; local39 < textureCount; local39++) {
				if (activeTexels[local39] != null && (textureCycles[local39] < local35 || local37 == -1)) {
					local35 = textureCycles[local39];
					local37 = local39;
				}
			}
			local27 = activeTexels[local37];
			activeTexels[local37] = null;
		}
		activeTexels[arg0] = local27;
		@Pc(79) IndexedSprite local79 = textures[arg0];
		@Pc(83) int[] local83 = texturePalettes[arg0];
		@Pc(106) int local106;
		if (lowMemory) {
			textureHasTransparency[arg0] = false;
			for (local39 = 0; local39 < 4096; local39++) {
				local106 = local27[local39] = local83[local79.pixels[local39]] & 0xF8F8FF;
				if (local106 == 0) {
					textureHasTransparency[arg0] = true;
				}
				local27[local39 + 4096] = local106 - (local106 >>> 3) & 0xF8F8FF;
				local27[local39 + 8192] = local106 - (local106 >>> 2) & 0xF8F8FF;
				local27[local39 + 12288] = local106 - (local106 >>> 2) - (local106 >>> 3) & 0xF8F8FF;
			}
		} else {
			if (local79.spriteWidth == 64) {
				for (local39 = 0; local39 < 128; local39++) {
					for (local106 = 0; local106 < 128; local106++) {
						local27[local106 + (local39 << 7)] = local83[local79.pixels[(local106 >> 1) + (local39 >> 1 << 6)]];
					}
				}
			} else {
				for (local39 = 0; local39 < 16384; local39++) {
					local27[local39] = local83[local79.pixels[local39]];
				}
			}
			textureHasTransparency[arg0] = false;
			for (local39 = 0; local39 < 16384; local39++) {
				local27[local39] &= 0xF8F8FF;
				local106 = local27[local39];
				if (local106 == 0) {
					textureHasTransparency[arg0] = true;
				}
				local27[local39 + 16384] = local106 - (local106 >>> 3) & 0xF8F8FF;
				local27[local39 + 32768] = local106 - (local106 >>> 2) & 0xF8F8FF;
				local27[local39 + 49152] = local106 - (local106 >>> 2) - (local106 >>> 3) & 0xF8F8FF;
			}
		}
		return local27;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(ZD)V")
	public static void setBrightness(@OriginalArg(1) double arg0) {
		@Pc(9) double local9 = arg0 + Math.random() * 0.03D - 0.015D;
		@Pc(11) int local11 = 0;
		for (@Pc(13) int local13 = 0; local13 < 512; local13++) {
			@Pc(24) double local24 = (double) (local13 / 8) / 64.0D + 0.0078125D;
			@Pc(33) double local33 = (double) (local13 & 0x7) / 8.0D + 0.0625D;
			for (@Pc(35) int local35 = 0; local35 < 128; local35++) {
				@Pc(42) double local42 = (double) local35 / 128.0D;
				@Pc(44) double local44 = local42;
				@Pc(46) double local46 = local42;
				@Pc(48) double local48 = local42;
				if (local33 != 0.0D) {
					@Pc(62) double local62;
					if (local42 < 0.5D) {
						local62 = local42 * (local33 + 1.0D);
					} else {
						local62 = local42 + local33 - local42 * local33;
					}
					@Pc(78) double local78 = local42 * 2.0D - local62;
					@Pc(82) double local82 = local24 + 0.3333333333333333D;
					if (local82 > 1.0D) {
						local82--;
					}
					@Pc(96) double local96 = local24 - 0.3333333333333333D;
					if (local96 < 0.0D) {
						local96++;
					}
					if (local82 * 6.0D < 1.0D) {
						local44 = local78 + (local62 - local78) * 6.0D * local82;
					} else if (local82 * 2.0D < 1.0D) {
						local44 = local62;
					} else if (local82 * 3.0D < 2.0D) {
						local44 = local78 + (local62 - local78) * (0.6666666666666666D - local82) * 6.0D;
					} else {
						local44 = local78;
					}
					if (local24 * 6.0D < 1.0D) {
						local46 = local78 + (local62 - local78) * 6.0D * local24;
					} else if (local24 * 2.0D < 1.0D) {
						local46 = local62;
					} else if (local24 * 3.0D < 2.0D) {
						local46 = local78 + (local62 - local78) * (0.6666666666666666D - local24) * 6.0D;
					} else {
						local46 = local78;
					}
					if (local96 * 6.0D < 1.0D) {
						local48 = local78 + (local62 - local78) * 6.0D * local96;
					} else if (local96 * 2.0D < 1.0D) {
						local48 = local62;
					} else if (local96 * 3.0D < 2.0D) {
						local48 = local78 + (local62 - local78) * (0.6666666666666666D - local96) * 6.0D;
					} else {
						local48 = local78;
					}
				}
				@Pc(259) int local259 = (int) (local44 * 256.0D);
				@Pc(264) int local264 = (int) (local46 * 256.0D);
				@Pc(269) int local269 = (int) (local48 * 256.0D);
				@Pc(279) int local279 = (local259 << 16) + (local264 << 8) + local269;
				@Pc(283) int local283 = powRgb(local279, local9);
				palette[local11++] = local283;
			}
		}
		for (@Pc(298) int local298 = 0; local298 < 50; local298++) {
			if (textures[local298] != null) {
				@Pc(309) int[] local309 = textures[local298].palette;
				texturePalettes[local298] = new int[local309.length];
				for (@Pc(317) int local317 = 0; local317 < local309.length; local317++) {
					texturePalettes[local298][local317] = powRgb(local309[local317], local9);
				}
			}
		}
		for (@Pc(344) int local344 = 0; local344 < 50; local344++) {
			updateTexture(local344);
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(ID)I")
	private static int powRgb(@OriginalArg(0) int arg0, @OriginalArg(1) double arg1) {
		@Pc(6) double local6 = (double) (arg0 >> 16) / 256.0D;
		@Pc(15) double local15 = (double) (arg0 >> 8 & 0xFF) / 256.0D;
		@Pc(22) double local22 = (double) (arg0 & 0xFF) / 256.0D;
		@Pc(26) double local26 = Math.pow(local6, arg1);
		@Pc(30) double local30 = Math.pow(local15, arg1);
		@Pc(34) double local34 = Math.pow(local22, arg1);
		@Pc(39) int local39 = (int) (local26 * 256.0D);
		@Pc(44) int local44 = (int) (local30 * 256.0D);
		@Pc(49) int local49 = (int) (local34 * 256.0D);
		return (local39 << 16) + (local44 << 8) + local49;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIIIIIII)V")
	public static void fillGouraudTriangle(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8) {
		@Pc(3) int local3 = 0;
		@Pc(5) int local5 = 0;
		if (arg1 != arg0) {
			local3 = (arg4 - arg3 << 16) / (arg1 - arg0);
			local5 = (arg7 - arg6 << 15) / (arg1 - arg0);
		}
		@Pc(30) int local30 = 0;
		@Pc(32) int local32 = 0;
		if (arg2 != arg1) {
			local30 = (arg5 - arg4 << 16) / (arg2 - arg1);
			local32 = (arg8 - arg7 << 15) / (arg2 - arg1);
		}
		@Pc(57) int local57 = 0;
		@Pc(59) int local59 = 0;
		if (arg2 != arg0) {
			local57 = (arg3 - arg5 << 16) / (arg0 - arg2);
			local59 = (arg6 - arg8 << 15) / (arg0 - arg2);
		}
		if (arg0 <= arg1 && arg0 <= arg2) {
			if (arg0 < Draw2D.bottom) {
				if (arg1 > Draw2D.bottom) {
					arg1 = Draw2D.bottom;
				}
				if (arg2 > Draw2D.bottom) {
					arg2 = Draw2D.bottom;
				}
				if (arg1 < arg2) {
					arg5 = arg3 <<= 0x10;
					arg8 = arg6 <<= 0xF;
					if (arg0 < 0) {
						arg5 -= local57 * arg0;
						arg3 -= local3 * arg0;
						arg8 -= local59 * arg0;
						arg6 -= local5 * arg0;
						arg0 = 0;
					}
					arg4 <<= 0x10;
					arg7 <<= 0xF;
					if (arg1 < 0) {
						arg4 -= local30 * arg1;
						arg7 -= local32 * arg1;
						arg1 = 0;
					}
					if (arg0 != arg1 && local57 < local3 || arg0 == arg1 && local57 > local30) {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawGouraudScanline(Draw2D.data, arg0, arg5 >> 16, arg4 >> 16, arg8 >> 7, arg7 >> 7);
									arg5 += local57;
									arg4 += local30;
									arg8 += local59;
									arg7 += local32;
									arg0 += Draw2D.width;
								}
							}
							drawGouraudScanline(Draw2D.data, arg0, arg5 >> 16, arg3 >> 16, arg8 >> 7, arg6 >> 7);
							arg5 += local57;
							arg3 += local3;
							arg8 += local59;
							arg6 += local5;
							arg0 += Draw2D.width;
						}
					} else {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawGouraudScanline(Draw2D.data, arg0, arg4 >> 16, arg5 >> 16, arg7 >> 7, arg8 >> 7);
									arg5 += local57;
									arg4 += local30;
									arg8 += local59;
									arg7 += local32;
									arg0 += Draw2D.width;
								}
							}
							drawGouraudScanline(Draw2D.data, arg0, arg3 >> 16, arg5 >> 16, arg6 >> 7, arg8 >> 7);
							arg5 += local57;
							arg3 += local3;
							arg8 += local59;
							arg6 += local5;
							arg0 += Draw2D.width;
						}
					}
				} else {
					arg4 = arg3 <<= 0x10;
					arg7 = arg6 <<= 0xF;
					if (arg0 < 0) {
						arg4 -= local57 * arg0;
						arg3 -= local3 * arg0;
						arg7 -= local59 * arg0;
						arg6 -= local5 * arg0;
						arg0 = 0;
					}
					arg5 <<= 0x10;
					arg8 <<= 0xF;
					if (arg2 < 0) {
						arg5 -= local30 * arg2;
						arg8 -= local32 * arg2;
						arg2 = 0;
					}
					if (arg0 != arg2 && local57 < local3 || arg0 == arg2 && local30 > local3) {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									drawGouraudScanline(Draw2D.data, arg0, arg5 >> 16, arg3 >> 16, arg8 >> 7, arg6 >> 7);
									arg5 += local30;
									arg3 += local3;
									arg8 += local32;
									arg6 += local5;
									arg0 += Draw2D.width;
								}
							}
							drawGouraudScanline(Draw2D.data, arg0, arg4 >> 16, arg3 >> 16, arg7 >> 7, arg6 >> 7);
							arg4 += local57;
							arg3 += local3;
							arg7 += local59;
							arg6 += local5;
							arg0 += Draw2D.width;
						}
					} else {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									drawGouraudScanline(Draw2D.data, arg0, arg3 >> 16, arg5 >> 16, arg6 >> 7, arg8 >> 7);
									arg5 += local30;
									arg3 += local3;
									arg8 += local32;
									arg6 += local5;
									arg0 += Draw2D.width;
								}
							}
							drawGouraudScanline(Draw2D.data, arg0, arg3 >> 16, arg4 >> 16, arg6 >> 7, arg7 >> 7);
							arg4 += local57;
							arg3 += local3;
							arg7 += local59;
							arg6 += local5;
							arg0 += Draw2D.width;
						}
					}
				}
			}
		} else if (arg1 <= arg2) {
			if (arg1 < Draw2D.bottom) {
				if (arg2 > Draw2D.bottom) {
					arg2 = Draw2D.bottom;
				}
				if (arg0 > Draw2D.bottom) {
					arg0 = Draw2D.bottom;
				}
				if (arg2 < arg0) {
					arg3 = arg4 <<= 0x10;
					arg6 = arg7 <<= 0xF;
					if (arg1 < 0) {
						arg3 -= local3 * arg1;
						arg4 -= local30 * arg1;
						arg6 -= local5 * arg1;
						arg7 -= local32 * arg1;
						arg1 = 0;
					}
					arg5 <<= 0x10;
					arg8 <<= 0xF;
					if (arg2 < 0) {
						arg5 -= local57 * arg2;
						arg8 -= local59 * arg2;
						arg2 = 0;
					}
					if (arg1 != arg2 && local3 < local30 || arg1 == arg2 && local3 > local57) {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									drawGouraudScanline(Draw2D.data, arg1, arg3 >> 16, arg5 >> 16, arg6 >> 7, arg8 >> 7);
									arg3 += local3;
									arg5 += local57;
									arg6 += local5;
									arg8 += local59;
									arg1 += Draw2D.width;
								}
							}
							drawGouraudScanline(Draw2D.data, arg1, arg3 >> 16, arg4 >> 16, arg6 >> 7, arg7 >> 7);
							arg3 += local3;
							arg4 += local30;
							arg6 += local5;
							arg7 += local32;
							arg1 += Draw2D.width;
						}
					} else {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									drawGouraudScanline(Draw2D.data, arg1, arg5 >> 16, arg3 >> 16, arg8 >> 7, arg6 >> 7);
									arg3 += local3;
									arg5 += local57;
									arg6 += local5;
									arg8 += local59;
									arg1 += Draw2D.width;
								}
							}
							drawGouraudScanline(Draw2D.data, arg1, arg4 >> 16, arg3 >> 16, arg7 >> 7, arg6 >> 7);
							arg3 += local3;
							arg4 += local30;
							arg6 += local5;
							arg7 += local32;
							arg1 += Draw2D.width;
						}
					}
				} else {
					arg5 = arg4 <<= 0x10;
					arg8 = arg7 <<= 0xF;
					if (arg1 < 0) {
						arg5 -= local3 * arg1;
						arg4 -= local30 * arg1;
						arg8 -= local5 * arg1;
						arg7 -= local32 * arg1;
						arg1 = 0;
					}
					arg3 <<= 0x10;
					arg6 <<= 0xF;
					if (arg0 < 0) {
						arg3 -= local57 * arg0;
						arg6 -= local59 * arg0;
						arg0 = 0;
					}
					if (local3 < local30) {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawGouraudScanline(Draw2D.data, arg1, arg3 >> 16, arg4 >> 16, arg6 >> 7, arg7 >> 7);
									arg3 += local57;
									arg4 += local30;
									arg6 += local59;
									arg7 += local32;
									arg1 += Draw2D.width;
								}
							}
							drawGouraudScanline(Draw2D.data, arg1, arg5 >> 16, arg4 >> 16, arg8 >> 7, arg7 >> 7);
							arg5 += local3;
							arg4 += local30;
							arg8 += local5;
							arg7 += local32;
							arg1 += Draw2D.width;
						}
					} else {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawGouraudScanline(Draw2D.data, arg1, arg4 >> 16, arg3 >> 16, arg7 >> 7, arg6 >> 7);
									arg3 += local57;
									arg4 += local30;
									arg6 += local59;
									arg7 += local32;
									arg1 += Draw2D.width;
								}
							}
							drawGouraudScanline(Draw2D.data, arg1, arg4 >> 16, arg5 >> 16, arg7 >> 7, arg8 >> 7);
							arg5 += local3;
							arg4 += local30;
							arg8 += local5;
							arg7 += local32;
							arg1 += Draw2D.width;
						}
					}
				}
			}
		} else if (arg2 < Draw2D.bottom) {
			if (arg0 > Draw2D.bottom) {
				arg0 = Draw2D.bottom;
			}
			if (arg1 > Draw2D.bottom) {
				arg1 = Draw2D.bottom;
			}
			if (arg0 < arg1) {
				arg4 = arg5 <<= 0x10;
				arg7 = arg8 <<= 0xF;
				if (arg2 < 0) {
					arg4 -= local30 * arg2;
					arg5 -= local57 * arg2;
					arg7 -= local32 * arg2;
					arg8 -= local59 * arg2;
					arg2 = 0;
				}
				arg3 <<= 0x10;
				arg6 <<= 0xF;
				if (arg0 < 0) {
					arg3 -= local3 * arg0;
					arg6 -= local5 * arg0;
					arg0 = 0;
				}
				if (local30 < local57) {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								drawGouraudScanline(Draw2D.data, arg2, arg4 >> 16, arg3 >> 16, arg7 >> 7, arg6 >> 7);
								arg4 += local30;
								arg3 += local3;
								arg7 += local32;
								arg6 += local5;
								arg2 += Draw2D.width;
							}
						}
						drawGouraudScanline(Draw2D.data, arg2, arg4 >> 16, arg5 >> 16, arg7 >> 7, arg8 >> 7);
						arg4 += local30;
						arg5 += local57;
						arg7 += local32;
						arg8 += local59;
						arg2 += Draw2D.width;
					}
				} else {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								drawGouraudScanline(Draw2D.data, arg2, arg3 >> 16, arg4 >> 16, arg6 >> 7, arg7 >> 7);
								arg4 += local30;
								arg3 += local3;
								arg7 += local32;
								arg6 += local5;
								arg2 += Draw2D.width;
							}
						}
						drawGouraudScanline(Draw2D.data, arg2, arg5 >> 16, arg4 >> 16, arg8 >> 7, arg7 >> 7);
						arg4 += local30;
						arg5 += local57;
						arg7 += local32;
						arg8 += local59;
						arg2 += Draw2D.width;
					}
				}
			} else {
				arg3 = arg5 <<= 0x10;
				arg6 = arg8 <<= 0xF;
				if (arg2 < 0) {
					arg3 -= local30 * arg2;
					arg5 -= local57 * arg2;
					arg6 -= local32 * arg2;
					arg8 -= local59 * arg2;
					arg2 = 0;
				}
				arg4 <<= 0x10;
				arg7 <<= 0xF;
				if (arg1 < 0) {
					arg4 -= local3 * arg1;
					arg7 -= local5 * arg1;
					arg1 = 0;
				}
				if (local30 < local57) {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								drawGouraudScanline(Draw2D.data, arg2, arg4 >> 16, arg5 >> 16, arg7 >> 7, arg8 >> 7);
								arg4 += local3;
								arg5 += local57;
								arg7 += local5;
								arg8 += local59;
								arg2 += Draw2D.width;
							}
						}
						drawGouraudScanline(Draw2D.data, arg2, arg3 >> 16, arg5 >> 16, arg6 >> 7, arg8 >> 7);
						arg3 += local30;
						arg5 += local57;
						arg6 += local32;
						arg8 += local59;
						arg2 += Draw2D.width;
					}
				} else {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								drawGouraudScanline(Draw2D.data, arg2, arg5 >> 16, arg4 >> 16, arg8 >> 7, arg7 >> 7);
								arg4 += local3;
								arg5 += local57;
								arg7 += local5;
								arg8 += local59;
								arg2 += Draw2D.width;
							}
						}
						drawGouraudScanline(Draw2D.data, arg2, arg5 >> 16, arg3 >> 16, arg8 >> 7, arg6 >> 7);
						arg3 += local30;
						arg5 += local57;
						arg6 += local32;
						arg8 += local59;
						arg2 += Draw2D.width;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([IIIIIIII)V")
	private static void drawGouraudScanline(@OriginalArg(0) int[] pixels, @OriginalArg(1) int offset, @OriginalArg(4) int leftX, @OriginalArg(5) int rightX, @OriginalArg(6) int leftColor, @OriginalArg(7) int rightColor) {
		@Pc(18) int colorStep;
		@Pc(149) int alpha;
		@Pc(153) int invAlpha;
		@Pc(51) int length;
		@Pc(97) int color;

		if (jagged) {
			if (testX) {
				if (rightX - leftX > 3) {
					colorStep = (rightColor - leftColor) / (rightX - leftX);
				} else {
					colorStep = 0;
				}

				if (rightX > Draw2D.safeX) {
					rightX = Draw2D.safeX;
				}

				if (leftX < 0) {
					leftColor -= leftX * colorStep;
					leftX = 0;
				}

				if (leftX >= rightX) {
					return;
				}

				offset += leftX;
				length = rightX - leftX >> 2;
				colorStep <<= 0x2;
			} else if (leftX < rightX) {
				offset += leftX;
				length = rightX - leftX >> 2;
				if (length > 0) {
					colorStep = (rightColor - leftColor) * reciprical15[length] >> 15;
				} else {
					colorStep = 0;
				}
			} else {
				return;
			}

			if (Draw3D.alpha == 0) {
				while (--length >= 0) {
					color = palette[leftColor >> 8];
					leftColor += colorStep;
					pixels[offset++] = color;
					pixels[offset++] = color;
					pixels[offset++] = color;
					pixels[offset++] = color;
				}

				length = rightX - leftX & 3;
				if (length > 0) {
					color = palette[leftColor >> 8];
					do {
						pixels[offset++] = color;
					} while (--length > 0);
				}
			} else {
				alpha = Draw3D.alpha;
				invAlpha = 256 - Draw3D.alpha;

				while (--length >= 0) {
					color = palette[leftColor >> 8];
					leftColor += colorStep;
					color = ((color & 0xff00ff) * invAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * invAlpha >> 8 & 0xff00);
					for (int i = 0; i < 4; ++i) {
						pixels[offset] = color + ((pixels[offset] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((pixels[offset] & 0xff00) * alpha >> 8 & 0xff00);
						offset++; // incrementing here instead fixes a transparency bug
					}
				}

				length = rightX - leftX & 3;
				if (length > 0) {
					color = palette[leftColor >> 8];
					color = ((color & 0xff00ff) * invAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * invAlpha >> 8 & 0xff00);
					do {
						pixels[offset] = color + ((pixels[offset] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((pixels[offset] & 0xff00) * alpha >> 8 & 0xff00);
						offset++; // incrementing here instead fixes a transparency bug
					} while (--length > 0);
				}
			}
		} else if (leftX < rightX) {
			colorStep = (rightColor - leftColor) / (rightX - leftX);

			if (testX) {
				if (rightX > Draw2D.safeX) {
					rightX = Draw2D.safeX;
				}

				if (leftX < 0) {
					leftColor -= leftX * colorStep;
					leftX = 0;
				}

				if (leftX >= rightX) {
					return;
				}
			}

			offset += leftX;
			length = rightX - leftX;
			if (Draw3D.alpha == 0) {
				do {
					pixels[offset++] = palette[leftColor >> 8];
					leftColor += colorStep;
				} while (--length > 0);
			} else {
				alpha = Draw3D.alpha;
				invAlpha = 256 - Draw3D.alpha;

				do {
					color = palette[leftColor >> 8];
					leftColor += colorStep;
					color = ((color & 0xff00ff) * invAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * invAlpha >> 8 & 0xff00);
					pixels[offset] = color + ((pixels[offset] & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((pixels[offset] & 0xff00) * alpha >> 8 & 0xff00);
					offset++; // incrementing here instead fixes a transparency bug
				} while (--length > 0);
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIIIII)V")
	public static void fillTriangle(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(3) int local3 = 0;
		if (arg1 != arg0) {
			local3 = (arg4 - arg3 << 16) / (arg1 - arg0);
		}
		@Pc(18) int local18 = 0;
		if (arg2 != arg1) {
			local18 = (arg5 - arg4 << 16) / (arg2 - arg1);
		}
		@Pc(33) int local33 = 0;
		if (arg2 != arg0) {
			local33 = (arg3 - arg5 << 16) / (arg0 - arg2);
		}
		if (arg0 <= arg1 && arg0 <= arg2) {
			if (arg0 < Draw2D.bottom) {
				if (arg1 > Draw2D.bottom) {
					arg1 = Draw2D.bottom;
				}
				if (arg2 > Draw2D.bottom) {
					arg2 = Draw2D.bottom;
				}
				if (arg1 < arg2) {
					arg5 = arg3 <<= 0x10;
					if (arg0 < 0) {
						arg5 -= local33 * arg0;
						arg3 -= local3 * arg0;
						arg0 = 0;
					}
					arg4 <<= 0x10;
					if (arg1 < 0) {
						arg4 -= local18 * arg1;
						arg1 = 0;
					}
					if (arg0 != arg1 && local33 < local3 || arg0 == arg1 && local33 > local18) {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawScanline(Draw2D.data, arg0, arg6, arg5 >> 16, arg4 >> 16);
									arg5 += local33;
									arg4 += local18;
									arg0 += Draw2D.width;
								}
							}
							drawScanline(Draw2D.data, arg0, arg6, arg5 >> 16, arg3 >> 16);
							arg5 += local33;
							arg3 += local3;
							arg0 += Draw2D.width;
						}
					} else {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawScanline(Draw2D.data, arg0, arg6, arg4 >> 16, arg5 >> 16);
									arg5 += local33;
									arg4 += local18;
									arg0 += Draw2D.width;
								}
							}
							drawScanline(Draw2D.data, arg0, arg6, arg3 >> 16, arg5 >> 16);
							arg5 += local33;
							arg3 += local3;
							arg0 += Draw2D.width;
						}
					}
				} else {
					arg4 = arg3 <<= 0x10;
					if (arg0 < 0) {
						arg4 -= local33 * arg0;
						arg3 -= local3 * arg0;
						arg0 = 0;
					}
					arg5 <<= 0x10;
					if (arg2 < 0) {
						arg5 -= local18 * arg2;
						arg2 = 0;
					}
					if (arg0 != arg2 && local33 < local3 || arg0 == arg2 && local18 > local3) {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									drawScanline(Draw2D.data, arg0, arg6, arg5 >> 16, arg3 >> 16);
									arg5 += local18;
									arg3 += local3;
									arg0 += Draw2D.width;
								}
							}
							drawScanline(Draw2D.data, arg0, arg6, arg4 >> 16, arg3 >> 16);
							arg4 += local33;
							arg3 += local3;
							arg0 += Draw2D.width;
						}
					} else {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									drawScanline(Draw2D.data, arg0, arg6, arg3 >> 16, arg5 >> 16);
									arg5 += local18;
									arg3 += local3;
									arg0 += Draw2D.width;
								}
							}
							drawScanline(Draw2D.data, arg0, arg6, arg3 >> 16, arg4 >> 16);
							arg4 += local33;
							arg3 += local3;
							arg0 += Draw2D.width;
						}
					}
				}
			}
		} else if (arg1 <= arg2) {
			if (arg1 < Draw2D.bottom) {
				if (arg2 > Draw2D.bottom) {
					arg2 = Draw2D.bottom;
				}
				if (arg0 > Draw2D.bottom) {
					arg0 = Draw2D.bottom;
				}
				if (arg2 < arg0) {
					arg3 = arg4 <<= 0x10;
					if (arg1 < 0) {
						arg3 -= local3 * arg1;
						arg4 -= local18 * arg1;
						arg1 = 0;
					}
					arg5 <<= 0x10;
					if (arg2 < 0) {
						arg5 -= local33 * arg2;
						arg2 = 0;
					}
					if (arg1 != arg2 && local3 < local18 || arg1 == arg2 && local3 > local33) {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									drawScanline(Draw2D.data, arg1, arg6, arg3 >> 16, arg5 >> 16);
									arg3 += local3;
									arg5 += local33;
									arg1 += Draw2D.width;
								}
							}
							drawScanline(Draw2D.data, arg1, arg6, arg3 >> 16, arg4 >> 16);
							arg3 += local3;
							arg4 += local18;
							arg1 += Draw2D.width;
						}
					} else {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									drawScanline(Draw2D.data, arg1, arg6, arg5 >> 16, arg3 >> 16);
									arg3 += local3;
									arg5 += local33;
									arg1 += Draw2D.width;
								}
							}
							drawScanline(Draw2D.data, arg1, arg6, arg4 >> 16, arg3 >> 16);
							arg3 += local3;
							arg4 += local18;
							arg1 += Draw2D.width;
						}
					}
				} else {
					arg5 = arg4 <<= 0x10;
					if (arg1 < 0) {
						arg5 -= local3 * arg1;
						arg4 -= local18 * arg1;
						arg1 = 0;
					}
					arg3 <<= 0x10;
					if (arg0 < 0) {
						arg3 -= local33 * arg0;
						arg0 = 0;
					}
					if (local3 < local18) {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawScanline(Draw2D.data, arg1, arg6, arg3 >> 16, arg4 >> 16);
									arg3 += local33;
									arg4 += local18;
									arg1 += Draw2D.width;
								}
							}
							drawScanline(Draw2D.data, arg1, arg6, arg5 >> 16, arg4 >> 16);
							arg5 += local3;
							arg4 += local18;
							arg1 += Draw2D.width;
						}
					} else {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawScanline(Draw2D.data, arg1, arg6, arg4 >> 16, arg3 >> 16);
									arg3 += local33;
									arg4 += local18;
									arg1 += Draw2D.width;
								}
							}
							drawScanline(Draw2D.data, arg1, arg6, arg4 >> 16, arg5 >> 16);
							arg5 += local3;
							arg4 += local18;
							arg1 += Draw2D.width;
						}
					}
				}
			}
		} else if (arg2 < Draw2D.bottom) {
			if (arg0 > Draw2D.bottom) {
				arg0 = Draw2D.bottom;
			}
			if (arg1 > Draw2D.bottom) {
				arg1 = Draw2D.bottom;
			}
			if (arg0 < arg1) {
				arg4 = arg5 <<= 0x10;
				if (arg2 < 0) {
					arg4 -= local18 * arg2;
					arg5 -= local33 * arg2;
					arg2 = 0;
				}
				arg3 <<= 0x10;
				if (arg0 < 0) {
					arg3 -= local3 * arg0;
					arg0 = 0;
				}
				if (local18 < local33) {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								drawScanline(Draw2D.data, arg2, arg6, arg4 >> 16, arg3 >> 16);
								arg4 += local18;
								arg3 += local3;
								arg2 += Draw2D.width;
							}
						}
						drawScanline(Draw2D.data, arg2, arg6, arg4 >> 16, arg5 >> 16);
						arg4 += local18;
						arg5 += local33;
						arg2 += Draw2D.width;
					}
				} else {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								drawScanline(Draw2D.data, arg2, arg6, arg3 >> 16, arg4 >> 16);
								arg4 += local18;
								arg3 += local3;
								arg2 += Draw2D.width;
							}
						}
						drawScanline(Draw2D.data, arg2, arg6, arg5 >> 16, arg4 >> 16);
						arg4 += local18;
						arg5 += local33;
						arg2 += Draw2D.width;
					}
				}
			} else {
				arg3 = arg5 <<= 0x10;
				if (arg2 < 0) {
					arg3 -= local18 * arg2;
					arg5 -= local33 * arg2;
					arg2 = 0;
				}
				arg4 <<= 0x10;
				if (arg1 < 0) {
					arg4 -= local3 * arg1;
					arg1 = 0;
				}
				if (local18 < local33) {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								drawScanline(Draw2D.data, arg2, arg6, arg4 >> 16, arg5 >> 16);
								arg4 += local3;
								arg5 += local33;
								arg2 += Draw2D.width;
							}
						}
						drawScanline(Draw2D.data, arg2, arg6, arg3 >> 16, arg5 >> 16);
						arg3 += local18;
						arg5 += local33;
						arg2 += Draw2D.width;
					}
				} else {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								drawScanline(Draw2D.data, arg2, arg6, arg5 >> 16, arg4 >> 16);
								arg4 += local3;
								arg5 += local33;
								arg2 += Draw2D.width;
							}
						}
						drawScanline(Draw2D.data, arg2, arg6, arg5 >> 16, arg3 >> 16);
						arg3 += local18;
						arg5 += local33;
						arg2 += Draw2D.width;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([IIIIII)V")
	private static void drawScanline(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		if (testX) {
			if (arg4 > Draw2D.safeX) {
				arg4 = Draw2D.safeX;
			}
			if (arg3 < 0) {
				arg3 = 0;
			}
		}
		if (arg3 >= arg4) {
			return;
		}
		arg1 += arg3;
		@Pc(26) int local26 = arg4 - arg3 >> 2;
		@Pc(33) int local33;
		if (alpha == 0) {
			while (true) {
				local26--;
				if (local26 < 0) {
					local26 = arg4 - arg3 & 0x3;
					while (true) {
						local26--;
						if (local26 < 0) {
							return;
						}
						arg0[arg1++] = arg2;
					}
				}
				local33 = arg1 + 1;
				arg0[arg1] = arg2;
				arg0[local33++] = arg2;
				arg0[local33++] = arg2;
				arg1 = local33 + 1;
				arg0[local33] = arg2;
			}
		} else {
			@Pc(72) int local72 = alpha;
			@Pc(76) int local76 = 256 - alpha;
			@Pc(96) int local96 = ((arg2 & 0xFF00FF) * local76 >> 8 & 0xFF00FF) + ((arg2 & 0xFF00) * local76 >> 8 & 0xFF00);
			while (true) {
				local26--;
				if (local26 < 0) {
					local26 = arg4 - arg3 & 0x3;
					while (true) {
						local26--;
						if (local26 < 0) {
							return;
						}
						arg0[arg1++] = local96 + ((arg0[arg1] & 0xFF00FF) * local72 >> 8 & 0xFF00FF) + ((arg0[arg1] & 0xFF00) * local72 >> 8 & 0xFF00);
					}
				}
				local33 = arg1 + 1;
				arg0[arg1] = local96 + ((arg0[local33] & 0xFF00FF) * local72 >> 8 & 0xFF00FF) + ((arg0[local33] & 0xFF00) * local72 >> 8 & 0xFF00);
				@Pc(130) int local130 = local33 + 1;
				arg0[local33] = local96 + ((arg0[local130] & 0xFF00FF) * local72 >> 8 & 0xFF00FF) + ((arg0[local130] & 0xFF00) * local72 >> 8 & 0xFF00);
				@Pc(159) int local159 = local130 + 1;
				arg0[local130] = local96 + ((arg0[local159] & 0xFF00FF) * local72 >> 8 & 0xFF00FF) + ((arg0[local159] & 0xFF00) * local72 >> 8 & 0xFF00);
				arg1 = local159 + 1;
				arg0[local159] = local96 + ((arg0[arg1] & 0xFF00FF) * local72 >> 8 & 0xFF00FF) + ((arg0[arg1] & 0xFF00) * local72 >> 8 & 0xFF00);
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIIIIIIIIIIIIIIIII)V")
	public static void fillTexturedTriangle(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11, @OriginalArg(12) int arg12, @OriginalArg(13) int arg13, @OriginalArg(14) int arg14, @OriginalArg(15) int arg15, @OriginalArg(16) int arg16, @OriginalArg(17) int arg17, @OriginalArg(18) int arg18) {
		@Pc(4) int[] local4 = getTexels(arg18);
		opaque = !textureHasTransparency[arg18];
		@Pc(16) int local16 = arg9 - arg10;
		@Pc(20) int local20 = arg12 - arg13;
		@Pc(24) int local24 = arg15 - arg16;
		@Pc(28) int local28 = arg11 - arg9;
		@Pc(32) int local32 = arg14 - arg12;
		@Pc(36) int local36 = arg17 - arg15;
		@Pc(46) int local46 = local28 * arg12 - local32 * arg9 << 14;
		@Pc(56) int local56 = local32 * arg15 - local36 * arg12 << 8;
		@Pc(66) int local66 = local36 * arg9 - local28 * arg15 << 5;
		@Pc(76) int local76 = local16 * arg12 - local20 * arg9 << 14;
		@Pc(86) int local86 = local20 * arg15 - local24 * arg12 << 8;
		@Pc(96) int local96 = local24 * arg9 - local16 * arg15 << 5;
		@Pc(106) int local106 = local20 * local28 - local16 * local32 << 14;
		@Pc(116) int local116 = local24 * local32 - local20 * local36 << 8;
		@Pc(126) int local126 = local16 * local36 - local24 * local28 << 5;
		@Pc(128) int local128 = 0;
		@Pc(130) int local130 = 0;
		if (arg1 != arg0) {
			local128 = (arg4 - arg3 << 16) / (arg1 - arg0);
			local130 = (arg7 - arg6 << 16) / (arg1 - arg0);
		}
		@Pc(155) int local155 = 0;
		@Pc(157) int local157 = 0;
		if (arg2 != arg1) {
			local155 = (arg5 - arg4 << 16) / (arg2 - arg1);
			local157 = (arg8 - arg7 << 16) / (arg2 - arg1);
		}
		@Pc(182) int local182 = 0;
		@Pc(184) int local184 = 0;
		if (arg2 != arg0) {
			local182 = (arg3 - arg5 << 16) / (arg0 - arg2);
			local184 = (arg6 - arg8 << 16) / (arg0 - arg2);
		}
		@Pc(298) int local298;
		if (arg0 <= arg1 && arg0 <= arg2) {
			if (arg0 < Draw2D.bottom) {
				if (arg1 > Draw2D.bottom) {
					arg1 = Draw2D.bottom;
				}
				if (arg2 > Draw2D.bottom) {
					arg2 = Draw2D.bottom;
				}
				if (arg1 < arg2) {
					arg5 = arg3 <<= 0x10;
					arg8 = arg6 <<= 0x10;
					if (arg0 < 0) {
						arg5 -= local182 * arg0;
						arg3 -= local128 * arg0;
						arg8 -= local184 * arg0;
						arg6 -= local130 * arg0;
						arg0 = 0;
					}
					arg4 <<= 0x10;
					arg7 <<= 0x10;
					if (arg1 < 0) {
						arg4 -= local155 * arg1;
						arg7 -= local157 * arg1;
						arg1 = 0;
					}
					local298 = arg0 - centerY3D;
					local46 += local66 * local298;
					local76 += local96 * local298;
					local106 += local126 * local298;
					if (arg0 != arg1 && local182 < local128 || arg0 == arg1 && local182 > local155) {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawTexturedScanline(Draw2D.data, local4, 0, 0, arg0, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
									arg5 += local182;
									arg4 += local155;
									arg8 += local184;
									arg7 += local157;
									arg0 += Draw2D.width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(Draw2D.data, local4, 0, 0, arg0, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
							arg5 += local182;
							arg3 += local128;
							arg8 += local184;
							arg6 += local130;
							arg0 += Draw2D.width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					} else {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawTexturedScanline(Draw2D.data, local4, 0, 0, arg0, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
									arg5 += local182;
									arg4 += local155;
									arg8 += local184;
									arg7 += local157;
									arg0 += Draw2D.width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(Draw2D.data, local4, 0, 0, arg0, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
							arg5 += local182;
							arg3 += local128;
							arg8 += local184;
							arg6 += local130;
							arg0 += Draw2D.width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					}
				} else {
					arg4 = arg3 <<= 0x10;
					arg7 = arg6 <<= 0x10;
					if (arg0 < 0) {
						arg4 -= local182 * arg0;
						arg3 -= local128 * arg0;
						arg7 -= local184 * arg0;
						arg6 -= local130 * arg0;
						arg0 = 0;
					}
					arg5 <<= 0x10;
					arg8 <<= 0x10;
					if (arg2 < 0) {
						arg5 -= local155 * arg2;
						arg8 -= local157 * arg2;
						arg2 = 0;
					}
					local298 = arg0 - centerY3D;
					local46 += local66 * local298;
					local76 += local96 * local298;
					local106 += local126 * local298;
					if ((arg0 == arg2 || local182 >= local128) && (arg0 != arg2 || local155 <= local128)) {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									drawTexturedScanline(Draw2D.data, local4, 0, 0, arg0, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
									arg5 += local155;
									arg3 += local128;
									arg8 += local157;
									arg6 += local130;
									arg0 += Draw2D.width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(Draw2D.data, local4, 0, 0, arg0, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
							arg4 += local182;
							arg3 += local128;
							arg7 += local184;
							arg6 += local130;
							arg0 += Draw2D.width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					} else {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = offsets[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									drawTexturedScanline(Draw2D.data, local4, 0, 0, arg0, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
									arg5 += local155;
									arg3 += local128;
									arg8 += local157;
									arg6 += local130;
									arg0 += Draw2D.width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(Draw2D.data, local4, 0, 0, arg0, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
							arg4 += local182;
							arg3 += local128;
							arg7 += local184;
							arg6 += local130;
							arg0 += Draw2D.width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					}
				}
			}
		} else if (arg1 <= arg2) {
			if (arg1 < Draw2D.bottom) {
				if (arg2 > Draw2D.bottom) {
					arg2 = Draw2D.bottom;
				}
				if (arg0 > Draw2D.bottom) {
					arg0 = Draw2D.bottom;
				}
				if (arg2 < arg0) {
					arg3 = arg4 <<= 0x10;
					arg6 = arg7 <<= 0x10;
					if (arg1 < 0) {
						arg3 -= local128 * arg1;
						arg4 -= local155 * arg1;
						arg6 -= local130 * arg1;
						arg7 -= local157 * arg1;
						arg1 = 0;
					}
					arg5 <<= 0x10;
					arg8 <<= 0x10;
					if (arg2 < 0) {
						arg5 -= local182 * arg2;
						arg8 -= local184 * arg2;
						arg2 = 0;
					}
					local298 = arg1 - centerY3D;
					local46 += local66 * local298;
					local76 += local96 * local298;
					local106 += local126 * local298;
					if (arg1 != arg2 && local128 < local155 || arg1 == arg2 && local128 > local182) {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									drawTexturedScanline(Draw2D.data, local4, 0, 0, arg1, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
									arg3 += local128;
									arg5 += local182;
									arg6 += local130;
									arg8 += local184;
									arg1 += Draw2D.width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(Draw2D.data, local4, 0, 0, arg1, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
							arg3 += local128;
							arg4 += local155;
							arg6 += local130;
							arg7 += local157;
							arg1 += Draw2D.width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					} else {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									drawTexturedScanline(Draw2D.data, local4, 0, 0, arg1, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
									arg3 += local128;
									arg5 += local182;
									arg6 += local130;
									arg8 += local184;
									arg1 += Draw2D.width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(Draw2D.data, local4, 0, 0, arg1, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
							arg3 += local128;
							arg4 += local155;
							arg6 += local130;
							arg7 += local157;
							arg1 += Draw2D.width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					}
				} else {
					arg5 = arg4 <<= 0x10;
					arg8 = arg7 <<= 0x10;
					if (arg1 < 0) {
						arg5 -= local128 * arg1;
						arg4 -= local155 * arg1;
						arg8 -= local130 * arg1;
						arg7 -= local157 * arg1;
						arg1 = 0;
					}
					arg3 <<= 0x10;
					arg6 <<= 0x10;
					if (arg0 < 0) {
						arg3 -= local182 * arg0;
						arg6 -= local184 * arg0;
						arg0 = 0;
					}
					local298 = arg1 - centerY3D;
					local46 += local66 * local298;
					local76 += local96 * local298;
					local106 += local126 * local298;
					if (local128 < local155) {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawTexturedScanline(Draw2D.data, local4, 0, 0, arg1, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
									arg3 += local182;
									arg4 += local155;
									arg6 += local184;
									arg7 += local157;
									arg1 += Draw2D.width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(Draw2D.data, local4, 0, 0, arg1, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
							arg5 += local128;
							arg4 += local155;
							arg8 += local130;
							arg7 += local157;
							arg1 += Draw2D.width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					} else {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = offsets[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									drawTexturedScanline(Draw2D.data, local4, 0, 0, arg1, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
									arg3 += local182;
									arg4 += local155;
									arg6 += local184;
									arg7 += local157;
									arg1 += Draw2D.width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(Draw2D.data, local4, 0, 0, arg1, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
							arg5 += local128;
							arg4 += local155;
							arg8 += local130;
							arg7 += local157;
							arg1 += Draw2D.width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					}
				}
			}
		} else if (arg2 < Draw2D.bottom) {
			if (arg0 > Draw2D.bottom) {
				arg0 = Draw2D.bottom;
			}
			if (arg1 > Draw2D.bottom) {
				arg1 = Draw2D.bottom;
			}
			if (arg0 < arg1) {
				arg4 = arg5 <<= 0x10;
				arg7 = arg8 <<= 0x10;
				if (arg2 < 0) {
					arg4 -= local155 * arg2;
					arg5 -= local182 * arg2;
					arg7 -= local157 * arg2;
					arg8 -= local184 * arg2;
					arg2 = 0;
				}
				arg3 <<= 0x10;
				arg6 <<= 0x10;
				if (arg0 < 0) {
					arg3 -= local128 * arg0;
					arg6 -= local130 * arg0;
					arg0 = 0;
				}
				local298 = arg2 - centerY3D;
				local46 += local66 * local298;
				local76 += local96 * local298;
				local106 += local126 * local298;
				if (local155 < local182) {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								drawTexturedScanline(Draw2D.data, local4, 0, 0, arg2, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
								arg4 += local155;
								arg3 += local128;
								arg7 += local157;
								arg6 += local130;
								arg2 += Draw2D.width;
								local46 += local66;
								local76 += local96;
								local106 += local126;
							}
						}
						drawTexturedScanline(Draw2D.data, local4, 0, 0, arg2, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
						arg4 += local155;
						arg5 += local182;
						arg7 += local157;
						arg8 += local184;
						arg2 += Draw2D.width;
						local46 += local66;
						local76 += local96;
						local106 += local126;
					}
				} else {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								drawTexturedScanline(Draw2D.data, local4, 0, 0, arg2, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
								arg4 += local155;
								arg3 += local128;
								arg7 += local157;
								arg6 += local130;
								arg2 += Draw2D.width;
								local46 += local66;
								local76 += local96;
								local106 += local126;
							}
						}
						drawTexturedScanline(Draw2D.data, local4, 0, 0, arg2, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
						arg4 += local155;
						arg5 += local182;
						arg7 += local157;
						arg8 += local184;
						arg2 += Draw2D.width;
						local46 += local66;
						local76 += local96;
						local106 += local126;
					}
				}
			} else {
				arg3 = arg5 <<= 0x10;
				arg6 = arg8 <<= 0x10;
				if (arg2 < 0) {
					arg3 -= local155 * arg2;
					arg5 -= local182 * arg2;
					arg6 -= local157 * arg2;
					arg8 -= local184 * arg2;
					arg2 = 0;
				}
				arg4 <<= 0x10;
				arg7 <<= 0x10;
				if (arg1 < 0) {
					arg4 -= local128 * arg1;
					arg7 -= local130 * arg1;
					arg1 = 0;
				}
				local298 = arg2 - centerY3D;
				local46 += local66 * local298;
				local76 += local96 * local298;
				local106 += local126 * local298;
				if (local155 < local182) {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								drawTexturedScanline(Draw2D.data, local4, 0, 0, arg2, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
								arg4 += local128;
								arg5 += local182;
								arg7 += local130;
								arg8 += local184;
								arg2 += Draw2D.width;
								local46 += local66;
								local76 += local96;
								local106 += local126;
							}
						}
						drawTexturedScanline(Draw2D.data, local4, 0, 0, arg2, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
						arg3 += local155;
						arg5 += local182;
						arg6 += local157;
						arg8 += local184;
						arg2 += Draw2D.width;
						local46 += local66;
						local76 += local96;
						local106 += local126;
					}
				} else {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = offsets[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								drawTexturedScanline(Draw2D.data, local4, 0, 0, arg2, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
								arg4 += local128;
								arg5 += local182;
								arg7 += local130;
								arg8 += local184;
								arg2 += Draw2D.width;
								local46 += local66;
								local76 += local96;
								local106 += local126;
							}
						}
						drawTexturedScanline(Draw2D.data, local4, 0, 0, arg2, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
						arg3 += local155;
						arg5 += local182;
						arg6 += local157;
						arg8 += local184;
						arg2 += Draw2D.width;
						local46 += local66;
						local76 += local96;
						local106 += local126;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([I[IIIIIIIIIIIIII)V")
	private static void drawTexturedScanline(@OriginalArg(0) int[] arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11, @OriginalArg(12) int arg12, @OriginalArg(13) int arg13, @OriginalArg(14) int arg14) {
		if (arg5 >= arg6) {
			return;
		}
		@Pc(15) int local15;
		@Pc(40) int local40;
		if (testX) {
			local15 = (arg8 - arg7) / (arg6 - arg5);
			if (arg6 > Draw2D.safeX) {
				arg6 = Draw2D.safeX;
			}
			if (arg5 < 0) {
				arg7 -= arg5 * local15;
				arg5 = 0;
			}
			if (arg5 >= arg6) {
				return;
			}
			local40 = arg6 - arg5 >> 3;
			local15 <<= 0xC;
			arg7 <<= 0x9;
		} else {
			if (arg6 - arg5 > 7) {
				local40 = arg6 - arg5 >> 3;
				local15 = (arg8 - arg7) * reciprical15[local40] >> 6;
			} else {
				local40 = 0;
				local15 = 0;
			}
			arg7 <<= 0x9;
		}
		arg4 += arg5;
		@Pc(89) int local89;
		@Pc(91) int local91;
		@Pc(123) int local123;
		@Pc(95) int local95;
		@Pc(188) int local188;
		@Pc(194) int local194;
		@Pc(206) int local206;
		@Pc(213) int local213;
		@Pc(103) int local103;
		@Pc(111) int local111;
		@Pc(119) int local119;
		if (lowMemory) {
			local89 = 0;
			local91 = 0;
			local95 = arg5 - centerX3D;
			local103 = arg9 + (arg12 >> 3) * local95;
			local111 = arg10 + (arg13 >> 3) * local95;
			local119 = arg11 + (arg14 >> 3) * local95;
			local123 = local119 >> 12;
			if (local123 != 0) {
				arg2 = local103 / local123;
				arg3 = local111 / local123;
				if (arg2 < 0) {
					arg2 = 0;
				} else if (arg2 > 4032) {
					arg2 = 4032;
				}
			}
			arg9 = local103 + arg12;
			arg10 = local111 + arg13;
			arg11 = local119 + arg14;
			local123 = arg11 >> 12;
			if (local123 != 0) {
				local89 = arg9 / local123;
				local91 = arg10 / local123;
				if (local89 < 7) {
					local89 = 7;
				} else if (local89 > 4032) {
					local89 = 4032;
				}
			}
			local188 = local89 - arg2 >> 3;
			local194 = local91 - arg3 >> 3;
			arg2 += arg7 >> 3 & 0xC0000;
			local206 = arg7 >> 23;
			if (opaque) {
				while (local40-- > 0) {
					local213 = arg4 + 1;
					arg0[arg4] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 += local188;
					arg3 += local194;
					arg0[local213++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 += local188;
					arg3 += local194;
					arg0[local213++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 += local188;
					arg3 += local194;
					arg0[local213++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 += local188;
					arg3 += local194;
					arg0[local213++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 += local188;
					arg3 += local194;
					arg0[local213++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 += local188;
					arg3 += local194;
					arg0[local213++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 += local188;
					arg3 += local194;
					arg4 = local213 + 1;
					arg0[local213] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 = local89;
					arg3 = local91;
					arg9 += arg12;
					arg10 += arg13;
					arg11 += arg14;
					local123 = arg11 >> 12;
					if (local123 != 0) {
						local89 = arg9 / local123;
						local91 = arg10 / local123;
						if (local89 < 7) {
							local89 = 7;
						} else if (local89 > 4032) {
							local89 = 4032;
						}
					}
					local188 = local89 - arg2 >> 3;
					local194 = local91 - arg3 >> 3;
					arg7 += local15;
					arg2 += arg7 >> 3 & 0xC0000;
					local206 = arg7 >> 23;
				}
				local40 = arg6 - arg5 & 0x7;
				while (local40-- > 0) {
					arg0[arg4++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206;
					arg2 += local188;
					arg3 += local194;
				}
			} else {
				while (local40-- > 0) {
					@Pc(507) int local507;
					if ((local507 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[arg4] = local507;
					}
					local213 = arg4 + 1;
					arg2 += local188;
					arg3 += local194;
					@Pc(534) int local534;
					if ((local534 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[local213] = local534;
					}
					local213++;
					arg2 += local188;
					arg3 += local194;
					@Pc(561) int local561;
					if ((local561 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[local213] = local561;
					}
					local213++;
					arg2 += local188;
					arg3 += local194;
					@Pc(588) int local588;
					if ((local588 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[local213] = local588;
					}
					local213++;
					arg2 += local188;
					arg3 += local194;
					@Pc(615) int local615;
					if ((local615 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[local213] = local615;
					}
					local213++;
					arg2 += local188;
					arg3 += local194;
					@Pc(642) int local642;
					if ((local642 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[local213] = local642;
					}
					local213++;
					arg2 += local188;
					arg3 += local194;
					@Pc(669) int local669;
					if ((local669 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[local213] = local669;
					}
					local213++;
					arg2 += local188;
					arg3 += local194;
					@Pc(696) int local696;
					if ((local696 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[local213] = local696;
					}
					arg4 = local213 + 1;
					arg2 = local89;
					arg3 = local91;
					arg9 += arg12;
					arg10 += arg13;
					arg11 += arg14;
					local123 = arg11 >> 12;
					if (local123 != 0) {
						local89 = arg9 / local123;
						local91 = arg10 / local123;
						if (local89 < 7) {
							local89 = 7;
						} else if (local89 > 4032) {
							local89 = 4032;
						}
					}
					local188 = local89 - arg2 >> 3;
					local194 = local91 - arg3 >> 3;
					arg7 += local15;
					arg2 += arg7 >> 3 & 0xC0000;
					local206 = arg7 >> 23;
				}
				local40 = arg6 - arg5 & 0x7;
				while (local40-- > 0) {
					@Pc(796) int local796;
					if ((local796 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> local206) != 0) {
						arg0[arg4] = local796;
					}
					arg4++;
					arg2 += local188;
					arg3 += local194;
				}
			}
			return;
		}
		local89 = 0;
		local91 = 0;
		local95 = arg5 - centerX3D;
		local103 = arg9 + (arg12 >> 3) * local95;
		local111 = arg10 + (arg13 >> 3) * local95;
		local119 = arg11 + (arg14 >> 3) * local95;
		local123 = local119 >> 14;
		if (local123 != 0) {
			arg2 = local103 / local123;
			arg3 = local111 / local123;
			if (arg2 < 0) {
				arg2 = 0;
			} else if (arg2 > 16256) {
				arg2 = 16256;
			}
		}
		arg9 = local103 + arg12;
		arg10 = local111 + arg13;
		arg11 = local119 + arg14;
		local123 = arg11 >> 14;
		if (local123 != 0) {
			local89 = arg9 / local123;
			local91 = arg10 / local123;
			if (local89 < 7) {
				local89 = 7;
			} else if (local89 > 16256) {
				local89 = 16256;
			}
		}
		local188 = local89 - arg2 >> 3;
		local194 = local91 - arg3 >> 3;
		arg2 += arg7 & 0x600000;
		local206 = arg7 >> 23;
		if (opaque) {
			while (local40-- > 0) {
				local213 = arg4 + 1;
				arg0[arg4] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 += local188;
				arg3 += local194;
				@Pc(961) int local961 = local213 + 1;
				arg0[local213] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 += local188;
				arg3 += local194;
				@Pc(984) int local984 = local961 + 1;
				arg0[local961] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 += local188;
				arg3 += local194;
				@Pc(1007) int local1007 = local984 + 1;
				arg0[local984] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 += local188;
				arg3 += local194;
				@Pc(1030) int local1030 = local1007 + 1;
				arg0[local1007] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 += local188;
				arg3 += local194;
				@Pc(1053) int local1053 = local1030 + 1;
				arg0[local1030] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 += local188;
				arg3 += local194;
				@Pc(1076) int local1076 = local1053 + 1;
				arg0[local1053] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 += local188;
				arg3 += local194;
				arg4 = local1076 + 1;
				arg0[local1076] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 = local89;
				arg3 = local91;
				arg9 += arg12;
				arg10 += arg13;
				arg11 += arg14;
				local123 = arg11 >> 14;
				if (local123 != 0) {
					local89 = arg9 / local123;
					local91 = arg10 / local123;
					if (local89 < 7) {
						local89 = 7;
					} else if (local89 > 16256) {
						local89 = 16256;
					}
				}
				local188 = local89 - arg2 >> 3;
				local194 = local91 - arg3 >> 3;
				arg7 += local15;
				arg2 += arg7 & 0x600000;
				local206 = arg7 >> 23;
			}
			local40 = arg6 - arg5 & 0x7;
			while (local40-- > 0) {
				arg0[arg4++] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206;
				arg2 += local188;
				arg3 += local194;
			}
			return;
		}
		while (local40-- > 0) {
			@Pc(1230) int local1230;
			if ((local1230 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[arg4] = local1230;
			}
			local213 = arg4 + 1;
			arg2 += local188;
			arg3 += local194;
			@Pc(1257) int local1257;
			if ((local1257 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[local213] = local1257;
			}
			local213++;
			arg2 += local188;
			arg3 += local194;
			@Pc(1284) int local1284;
			if ((local1284 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[local213] = local1284;
			}
			local213++;
			arg2 += local188;
			arg3 += local194;
			@Pc(1311) int local1311;
			if ((local1311 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[local213] = local1311;
			}
			local213++;
			arg2 += local188;
			arg3 += local194;
			@Pc(1338) int local1338;
			if ((local1338 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[local213] = local1338;
			}
			local213++;
			arg2 += local188;
			arg3 += local194;
			@Pc(1365) int local1365;
			if ((local1365 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[local213] = local1365;
			}
			local213++;
			arg2 += local188;
			arg3 += local194;
			@Pc(1392) int local1392;
			if ((local1392 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[local213] = local1392;
			}
			local213++;
			arg2 += local188;
			arg3 += local194;
			@Pc(1419) int local1419;
			if ((local1419 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[local213] = local1419;
			}
			arg4 = local213 + 1;
			arg2 = local89;
			arg3 = local91;
			arg9 += arg12;
			arg10 += arg13;
			arg11 += arg14;
			local123 = arg11 >> 14;
			if (local123 != 0) {
				local89 = arg9 / local123;
				local91 = arg10 / local123;
				if (local89 < 7) {
					local89 = 7;
				} else if (local89 > 16256) {
					local89 = 16256;
				}
			}
			local188 = local89 - arg2 >> 3;
			local194 = local91 - arg3 >> 3;
			arg7 += local15;
			arg2 += arg7 & 0x600000;
			local206 = arg7 >> 23;
		}
		local40 = arg6 - arg5 & 0x7;
		while (local40-- > 0) {
			@Pc(1517) int local1517;
			if ((local1517 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> local206) != 0) {
				arg0[arg4] = local1517;
			}
			arg4++;
			arg2 += local188;
			arg3 += local194;
		}
	}

	@OriginalMember(owner = "client!gb", name = "v", descriptor = "Z")
	private static boolean flowObfuscator1;

	@OriginalMember(owner = "client!gb", name = "x", descriptor = "Z")
	private static boolean flowObfuscator2;

	@OriginalMember(owner = "client!gb", name = "w", descriptor = "I")
	private static final int flowObfuscator3 = 787;

	@OriginalMember(owner = "client!gb", name = "y", descriptor = "I")
	private static final int flowObfuscator4 = 473;

	@OriginalMember(owner = "client!gb", name = "z", descriptor = "Z")
	private static final boolean flowObfuscator5 = true;

}
