import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!gb")
public final class Draw3D extends Draw2D {

	@OriginalMember(owner = "client!gb", name = "H", descriptor = "[I")
	public static int[] reciprical15 = new int[512];

	@OriginalMember(owner = "client!gb", name = "I", descriptor = "[I")
	public static int[] reciprical16 = new int[2048];

	@OriginalMember(owner = "client!gb", name = "J", descriptor = "[I")
	public static int[] sin = new int[2048];

	@OriginalMember(owner = "client!gb", name = "K", descriptor = "[I")
	public static int[] cos = new int[2048];

	@OriginalMember(owner = "client!gb", name = "B", descriptor = "Z")
	public static boolean testX;

	@OriginalMember(owner = "client!gb", name = "E", descriptor = "I")
	public static int alpha;

	@OriginalMember(owner = "client!gb", name = "F", descriptor = "I")
	public static int centerX3D;

	@OriginalMember(owner = "client!gb", name = "G", descriptor = "I")
	public static int centerY3D;

	@OriginalMember(owner = "client!gb", name = "L", descriptor = "[I")
	public static int[] offsets;

	@OriginalMember(owner = "client!gb", name = "U", descriptor = "I")
	public static int cycle;

	@OriginalMember(owner = "client!gb", name = "V", descriptor = "[I")
	public static int[] palette = new int[65536];

	@OriginalMember(owner = "client!gb", name = "A", descriptor = "Z")
	public static boolean lowMemory = true;

	@OriginalMember(owner = "client!gb", name = "D", descriptor = "Z")
	public static boolean jagged = true;

	@OriginalMember(owner = "client!gb", name = "N", descriptor = "[Lclient!ib;")
	public static IndexedSprite[] textures = new IndexedSprite[50];

	@OriginalMember(owner = "client!gb", name = "T", descriptor = "[I")
	public static int[] textureCycles = new int[50];

	@OriginalMember(owner = "client!gb", name = "C", descriptor = "Z")
	private static boolean opaque;

	@OriginalMember(owner = "client!gb", name = "M", descriptor = "I")
	private static int textureCount;

	@OriginalMember(owner = "client!gb", name = "Q", descriptor = "I")
	private static int poolSize;

	@OriginalMember(owner = "client!gb", name = "R", descriptor = "[[I")
	private static int[][] texelPool;

	@OriginalMember(owner = "client!gb", name = "O", descriptor = "[Z")
	private static boolean[] textureHasTransparency = new boolean[50];

	@OriginalMember(owner = "client!gb", name = "P", descriptor = "[I")
	private static int[] textureColors = new int[50];

	@OriginalMember(owner = "client!gb", name = "S", descriptor = "[[I")
	private static int[][] activeTexels = new int[50][];

	@OriginalMember(owner = "client!gb", name = "W", descriptor = "[[I")
	private static int[][] texturePalettes = new int[50][];

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
		offsets = new int[height];
		for (@Pc(5) int line = 0; line < height; line++) {
			offsets[line] = width * line;
		}
		centerX3D = width / 2;
		centerY3D = height / 2;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(III)V")
	public static void prepareOffsets(@OriginalArg(0) int y, @OriginalArg(1) int x) {
		offsets = new int[y];
		for (@Pc(12) int line = 0; line < y; line++) {
			offsets[line] = x * line;
		}
		centerX3D = x / 2;
		centerY3D = y / 2;
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(Z)V")
	public static void clearPools() {
		texelPool = null;
		for (@Pc(6) int i = 0; i < 50; i++) {
			activeTexels[i] = null;
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(II)V")
	public static void setupPools(@OriginalArg(0) int size) {
		if (texelPool != null) {
			return;
		}

		poolSize = size;
		if (lowMemory) {
			texelPool = new int[poolSize][64 * 64 * 4];
		} else {
			texelPool = new int[poolSize][128 * 128 * 4];
		}

		for (@Pc(5) int i = 0; i < 50; i++) {
			activeTexels[i] = null;
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(BLclient!ub;)V")
	public static void unpackTextures(@OriginalArg(1) FileArchive archive) {
		textureCount = 0;

		for (@Pc(9) int i = 0; i < 50; i++) {
			try {
				textures[i] = new IndexedSprite(archive, String.valueOf(i), 0);
				if (lowMemory && textures[i].clipWidth == 128) {
					textures[i].shrink();
				} else {
					textures[i].crop();
				}
				textureCount++;
			} catch (@Pc(47) Exception ignored) {
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(II)I")
	public static int getAverageTextureRgb(@OriginalArg(1) int tex) {
		if (textureColors[tex] != 0) {
			return textureColors[tex];
		}

		@Pc(13) int r = 0;
		@Pc(15) int g = 0;
		@Pc(17) int b = 0;
		@Pc(22) int length = texturePalettes[tex].length;
		for (@Pc(24) int i = 0; i < length; i++) {
			r += texturePalettes[tex][i] >> 16 & 0xFF;
			g += texturePalettes[tex][i] >> 8 & 0xFF;
			b += texturePalettes[tex][i] & 0xFF;
		}

		@Pc(80) int rgb = (r / length << 16) + (g / length << 8) + b / length;
		rgb = powRgb(rgb, 1.4D);
		if (rgb == 0) {
			rgb = 1;
		}

		textureColors[tex] = rgb;
		return rgb;
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(II)V")
	public static void updateTexture(@OriginalArg(0) int id) {
		if (activeTexels[id] != null) {
			texelPool[poolSize++] = activeTexels[id];
			activeTexels[id] = null;
		}
	}

	@OriginalMember(owner = "client!gb", name = "d", descriptor = "(I)[I")
	private static int[] getTexels(@OriginalArg(0) int id) {
		textureCycles[id] = cycle++;
		if (activeTexels[id] != null) {
			return activeTexels[id];
		}

		@Pc(27) int[] texels;
		if (poolSize > 0) {
			texels = texelPool[--poolSize];
			texelPool[poolSize] = null;
		} else {
			@Pc(35) int cycle = 0;
			@Pc(37) int selected = -1;

			for (@Pc(39) int i = 0; i < textureCount; i++) {
				if (activeTexels[i] != null && (textureCycles[i] < cycle || selected == -1)) {
					cycle = textureCycles[i];
					selected = i;
				}
			}

			texels = activeTexels[selected];
			activeTexels[selected] = null;
		}

		activeTexels[id] = texels;
		@Pc(79) IndexedSprite texture = textures[id];
		@Pc(83) int[] palette = texturePalettes[id];

		if (lowMemory) {
			textureHasTransparency[id] = false;
			for (int i = 0; i < 64 * 64; i++) {
				int rgb = texels[i] = palette[texture.pixels[i]] & 0xF8F8FF;
				if (rgb == 0) {
					textureHasTransparency[id] = true;
				}

				texels[i + (64 * 64)] = rgb - (rgb >>> 3) & 0xF8F8FF;
				texels[i + (64 * 64 * 2)] = rgb - (rgb >>> 2) & 0xF8F8FF;
				texels[i + (64 * 64 * 3)] = rgb - (rgb >>> 2) - (rgb >>> 3) & 0xF8F8FF;
			}
		} else {
			if (texture.spriteWidth == 64) {
				// upscale 64x64 textures to 128x128
				for (int y = 0; y < 128; y++) {
					for (int x = 0; x < 128; x++) {
						texels[x + (y << 7)] = palette[texture.pixels[(x >> 1) + (y >> 1 << 6)]];
					}
				}
			} else {
				for (int i = 0; i < 128 * 128; i++) {
					texels[i] = palette[texture.pixels[i]];
				}
			}

			textureHasTransparency[id] = false;
			for (int i = 0; i < 128 * 128; i++) {
				texels[i] &= 0xF8F8FF;

				int rgb = texels[i];
				if (rgb == 0) {
					textureHasTransparency[id] = true;
				}

				texels[i + (128 * 128)] = rgb - (rgb >>> 3) & 0xF8F8FF;
				texels[i + (128 * 128 * 2)] = rgb - (rgb >>> 2) & 0xF8F8FF;
				texels[i + (128 * 128 * 3)] = rgb - (rgb >>> 2) - (rgb >>> 3) & 0xF8F8FF;
			}
		}

		return texels;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(ZD)V")
	public static void setBrightness(@OriginalArg(1) double brightness) {
		brightness += Math.random() * 0.03D - 0.015D;

		@Pc(11) int offset = 0;
		for (@Pc(13) int y = 0; y < 512; y++) {
			@Pc(24) double hue = (double) (y / 8) / 64.0D + 0.0078125D;
			@Pc(33) double saturation = (double) (y & 0x7) / 8.0D + 0.0625D;

			for (@Pc(35) int x = 0; x < 128; x++) {
				@Pc(42) double lightness = (double) x / 128.0D;

				@Pc(44) double r = lightness;
				@Pc(46) double g = lightness;
				@Pc(48) double b = lightness;

				if (saturation != 0.0D) {
					@Pc(62) double q;
					if (lightness < 0.5D) {
						q = lightness * (saturation + 1.0D);
					} else {
						q = lightness + saturation - lightness * saturation;
					}

					@Pc(78) double p = lightness * 2.0D - q;
					@Pc(82) double t = hue + 0.3333333333333333D;
					if (t > 1.0D) {
						t--;
					}

					@Pc(96) double d11 = hue - 0.3333333333333333D;
					if (d11 < 0.0D) {
						d11++;
					}

					if (t * 6.0D < 1.0D) {
						r = p + (q - p) * 6.0D * t;
					} else if (t * 2.0D < 1.0D) {
						r = q;
					} else if (t * 3.0D < 2.0D) {
						r = p + (q - p) * (0.6666666666666666D - t) * 6.0D;
					} else {
						r = p;
					}

					if (hue * 6.0D < 1.0D) {
						g = p + (q - p) * 6.0D * hue;
					} else if (hue * 2.0D < 1.0D) {
						g = q;
					} else if (hue * 3.0D < 2.0D) {
						g = p + (q - p) * (0.6666666666666666D - hue) * 6.0D;
					} else {
						g = p;
					}

					if (d11 * 6.0D < 1.0D) {
						b = p + (q - p) * 6.0D * d11;
					} else if (d11 * 2.0D < 1.0D) {
						b = q;
					} else if (d11 * 3.0D < 2.0D) {
						b = p + (q - p) * (0.6666666666666666D - d11) * 6.0D;
					} else {
						b = p;
					}
				}

				@Pc(259) int intR = (int) (r * 256.0D);
				@Pc(264) int intG = (int) (g * 256.0D);
				@Pc(269) int intB = (int) (b * 256.0D);

				@Pc(279) int rgb = (intR << 16) + (intG << 8) + intB;
				rgb = powRgb(rgb, brightness);
				palette[offset++] = rgb;
			}
		}

		for (@Pc(298) int id = 0; id < 50; id++) {
			if (textures[id] != null) {
				@Pc(309) int[] palette = textures[id].palette;
				texturePalettes[id] = new int[palette.length];

				for (@Pc(317) int i = 0; i < palette.length; i++) {
					texturePalettes[id][i] = powRgb(palette[i], brightness);
				}
			}
		}

		for (@Pc(344) int id = 0; id < 50; id++) {
			updateTexture(id);
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(ID)I")
	private static int powRgb(@OriginalArg(0) int color, @OriginalArg(1) double brightness) {
		@Pc(6) double r = (double) (color >> 16) / 256.0D;
		@Pc(15) double g = (double) (color >> 8 & 0xFF) / 256.0D;
		@Pc(22) double b = (double) (color & 0xFF) / 256.0D;

		r = Math.pow(r, brightness);
		g = Math.pow(g, brightness);
		b = Math.pow(b, brightness);

		@Pc(39) int intR = (int) (r * 256.0D);
		@Pc(44) int intG = (int) (g * 256.0D);
		@Pc(49) int intB = (int) (b * 256.0D);
		return (intR << 16) + (intG << 8) + intB;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIIIIIII)V")
	public static void fillGouraudTriangle(@OriginalArg(0) int yA, @OriginalArg(1) int yB, @OriginalArg(2) int yC, @OriginalArg(3) int xA, @OriginalArg(4) int xB, @OriginalArg(5) int xC, @OriginalArg(6) int colorA, @OriginalArg(7) int colorB, @OriginalArg(8) int colorC) {
		@Pc(3) int xStepAB = 0;
		@Pc(5) int colorStepAB = 0;
		if (yB != yA) {
			xStepAB = (xB - xA << 16) / (yB - yA);
			colorStepAB = (colorB - colorA << 15) / (yB - yA);
		}

		@Pc(30) int xStepBC = 0;
		@Pc(32) int colorStepBC = 0;
		if (yC != yB) {
			xStepBC = (xC - xB << 16) / (yC - yB);
			colorStepBC = (colorC - colorB << 15) / (yC - yB);
		}

		@Pc(57) int xStepAC = 0;
		@Pc(59) int colorStepAC = 0;
		if (yC != yA) {
			xStepAC = (xA - xC << 16) / (yA - yC);
			colorStepAC = (colorA - colorC << 15) / (yA - yC);
		}

		if (yA <= yB && yA <= yC) {
			if (yA >= bottom) {
				return;
			}

			if (yB > bottom) {
				yB = bottom;
			}

			if (yC > bottom) {
				yC = bottom;
			}

			if (yB < yC) {
				xC = xA <<= 0x10;
				colorC = colorA <<= 0xF;
				if (yA < 0) {
					xC -= xStepAC * yA;
					xA -= xStepAB * yA;
					colorC -= colorStepAC * yA;
					colorA -= colorStepAB * yA;
					yA = 0;
				}

				xB <<= 0x10;
				colorB <<= 0xF;
				if (yB < 0) {
					xB -= xStepBC * yB;
					colorB -= colorStepBC * yB;
					yB = 0;
				}

				if (yA != yB && xStepAC < xStepAB || yA == yB && xStepAC > xStepBC) {
					yC -= yB;
					yB -= yA;

					yA = offsets[yA];
					while (--yB >= 0) {
						drawGouraudScanline(data, yA, xC >> 16, xA >> 16, colorC >> 7, colorA >> 7);
						xC += xStepAC;
						xA += xStepAB;
						colorC += colorStepAC;
						colorA += colorStepAB;
					}

					while (--yC >= 0) {
						drawGouraudScanline(data, yA, xC >> 16, xB >> 16, colorC >> 7, colorB >> 7);
						xC += xStepAC;
						xB += xStepBC;
						colorC += colorStepAC;
						colorB += colorStepBC;
						yA += width;
					}
				} else {
					yC -= yB;
					yB -= yA;

					yA = offsets[yA];
					while (--yB >= 0) {
						drawGouraudScanline(data, yA, xA >> 16, xC >> 16, colorA >> 7, colorC >> 7);
						xC += xStepAC;
						xA += xStepAB;
						colorC += colorStepAC;
						colorA += colorStepAB;
						yA += width;
					}

					while (--yC >= 0) {
						drawGouraudScanline(data, yA, xB >> 16, xC >> 16, colorB >> 7, colorC >> 7);
						xC += xStepAC;
						xB += xStepBC;
						colorC += colorStepAC;
						colorB += colorStepBC;
						yA += width;
					}
				}
			} else {
				xB = xA <<= 0x10;
				colorB = colorA <<= 0xF;
				if (yA < 0) {
					xB -= xStepAC * yA;
					xA -= xStepAB * yA;
					colorB -= colorStepAC * yA;
					colorA -= colorStepAB * yA;
					yA = 0;
				}

				xC <<= 0x10;
				colorC <<= 0xF;
				if (yC < 0) {
					xC -= xStepBC * yC;
					colorC -= colorStepBC * yC;
					yC = 0;
				}

				if (yA != yC && xStepAC < xStepAB || yA == yC && xStepBC > xStepAB) {
					yB -= yC;
					yC -= yA;

					yA = offsets[yA];
					while (--yC >= 0) {
						drawGouraudScanline(data, yA, xB >> 16, xA >> 16, colorB >> 7, colorA >> 7);
						xB += xStepAC;
						xA += xStepAB;
						colorB += colorStepAC;
						colorA += colorStepAB;
						yA += width;
					}

					while (--yB >= 0) {
						drawGouraudScanline(data, yA, xC >> 16, xA >> 16, colorC >> 7, colorA >> 7);
						xC += xStepBC;
						xA += xStepAB;
						colorC += colorStepBC;
						colorA += colorStepAB;
						yA += width;
					}
				} else {
					yB -= yC;
					yC -= yA;

					yA = offsets[yA];
					while (--yC >= 0) {
						drawGouraudScanline(data, yA, xA >> 16, xB >> 16, colorA >> 7, colorB >> 7);
						xB += xStepAC;
						xA += xStepAB;
						colorB += colorStepAC;
						colorA += colorStepAB;
						yA += width;
					}

					while (--yB >= 0) {
						drawGouraudScanline(data, yA, xA >> 16, xC >> 16, colorA >> 7, colorC >> 7);
						xC += xStepBC;
						xA += xStepAB;
						colorC += colorStepBC;
						colorA += colorStepAB;
						yA += width;
					}
				}
			}
		} else if (yB <= yC) {
			if (yB >= bottom) {
				return;
			}

			if (yC > bottom) {
				yC = bottom;
			}

			if (yA > bottom) {
				yA = bottom;
			}

			if (yC < yA) {
				xA = xB <<= 0x10;
				colorA = colorB <<= 0xF;
				if (yB < 0) {
					xA -= xStepAB * yB;
					xB -= xStepBC * yB;
					colorA -= colorStepAB * yB;
					colorB -= colorStepBC * yB;
					yB = 0;
				}

				xC <<= 0x10;
				colorC <<= 0xF;
				if (yC < 0) {
					xC -= xStepAC * yC;
					colorC -= colorStepAC * yC;
					yC = 0;
				}

				if (yB != yC && xStepAB < xStepBC || yB == yC && xStepAB > xStepAC) {
					yA -= yC;
					yC -= yB;

					yB = offsets[yB];
					while (--yC >= 0) {
						drawGouraudScanline(data, yB, xA >> 16, xB >> 16, colorA >> 7, colorB >> 7);
						xA += xStepAB;
						xB += xStepBC;
						colorA += colorStepAB;
						colorB += colorStepBC;
						yB += width;
					}

					while (--yA >= 0) {
						drawGouraudScanline(data, yB, xA >> 16, xC >> 16, colorA >> 7, colorC >> 7);
						xA += xStepAB;
						xC += xStepAC;
						colorA += colorStepAB;
						colorC += colorStepAC;
						yB += width;
					}
				} else {
					yA -= yC;
					yC -= yB;

					yB = offsets[yB];
					while (--yC >= 0) {
						drawGouraudScanline(data, yB, xB >> 16, xA >> 16, colorB >> 7, colorA >> 7);
						xA += xStepAB;
						xB += xStepBC;
						colorA += colorStepAB;
						colorB += colorStepBC;
						yB += width;
					}

					while (--yA >= 0) {
						drawGouraudScanline(data, yB, xC >> 16, xA >> 16, colorC >> 7, colorA >> 7);
						xA += xStepAB;
						xC += xStepAC;
						colorA += colorStepAB;
						colorC += colorStepAC;
						yB += width;
					}
				}
			} else {
				xC = xB <<= 0x10;
				colorC = colorB <<= 0xF;
				if (yB < 0) {
					xC -= xStepAB * yB;
					xB -= xStepBC * yB;
					colorC -= colorStepAB * yB;
					colorB -= colorStepBC * yB;
					yB = 0;
				}

				xA <<= 0x10;
				colorA <<= 0xF;
				if (yA < 0) {
					xA -= xStepAC * yA;
					colorA -= colorStepAC * yA;
					yA = 0;
				}

				if (xStepAB < xStepBC) {
					yC -= yA;
					yA -= yB;

					yB = offsets[yB];
					while (--yA >= 0) {
						drawGouraudScanline(data, yB, xC >> 16, xB >> 16, colorC >> 7, colorB >> 7);
						xC += xStepAB;
						xB += xStepBC;
						colorC += colorStepAB;
						colorB += colorStepBC;
						yB += width;
					}

					while (--yC >= 0) {
						drawGouraudScanline(data, yB, xA >> 16, xB >> 16, colorA >> 7, colorB >> 7);
						xA += xStepAC;
						xB += xStepBC;
						colorA += colorStepAC;
						colorB += colorStepBC;
						yB += width;
					}
				} else {
					yC -= yA;
					yA -= yB;

					yB = offsets[yB];
					while (--yA >= 0) {
						drawGouraudScanline(data, yB, xB >> 16, xC >> 16, colorB >> 7, colorC >> 7);
						xC += xStepAB;
						xB += xStepBC;
						colorC += colorStepAB;
						colorB += colorStepBC;
						yB += width;
					}

					while (--yC >= 0) {
						drawGouraudScanline(data, yB, xB >> 16, xA >> 16, colorB >> 7, colorA >> 7);
						xA += xStepAC;
						xB += xStepBC;
						colorA += colorStepAC;
						colorB += colorStepBC;
						yB += width;
					}
				}
			}
		} else if (yC < bottom) {
			if (yA > bottom) {
				yA = bottom;
			}

			if (yB > bottom) {
				yB = bottom;
			}

			if (yA < yB) {
				xB = xC <<= 0x10;
				colorB = colorC <<= 0xF;
				if (yC < 0) {
					xB -= xStepBC * yC;
					xC -= xStepAC * yC;
					colorB -= colorStepBC * yC;
					colorC -= colorStepAC * yC;
					yC = 0;
				}

				xA <<= 0x10;
				colorA <<= 0xF;
				if (yA < 0) {
					xA -= xStepAB * yA;
					colorA -= colorStepAB * yA;
					yA = 0;
				}

				if (xStepBC < xStepAC) {
					yB -= yA;
					yA -= yC;

					yC = offsets[yC];
					while (--yA >= 0) {
						drawGouraudScanline(data, yC, xB >> 16, xC >> 16, colorB >> 7, colorC >> 7);
						xB += xStepBC;
						xC += xStepAC;
						colorB += colorStepBC;
						colorC += colorStepAC;
						yC += width;
					}

					while (--yB >= 0) {
						drawGouraudScanline(data, yC, xB >> 16, xA >> 16, colorB >> 7, colorA >> 7);
						xB += xStepBC;
						xA += xStepAB;
						colorB += colorStepBC;
						colorA += colorStepAB;
						yC += width;
					}
				} else {
					yB -= yA;
					yA -= yC;

					yC = offsets[yC];
					while (--yA >= 0) {
						drawGouraudScanline(data, yC, xC >> 16, xB >> 16, colorC >> 7, colorB >> 7);
						xB += xStepBC;
						xC += xStepAC;
						colorB += colorStepBC;
						colorC += colorStepAC;
						yC += width;
					}

					while (--yB >= 0) {
						drawGouraudScanline(data, yC, xA >> 16, xB >> 16, colorA >> 7, colorB >> 7);
						xB += xStepBC;
						xA += xStepAB;
						colorB += colorStepBC;
						colorA += colorStepAB;
						yC += width;
					}
				}
			} else {
				xA = xC <<= 0x10;
				colorA = colorC <<= 0xF;
				if (yC < 0) {
					xA -= xStepBC * yC;
					xC -= xStepAC * yC;
					colorA -= colorStepBC * yC;
					colorC -= colorStepAC * yC;
					yC = 0;
				}

				xB <<= 0x10;
				colorB <<= 0xF;
				if (yB < 0) {
					xB -= xStepAB * yB;
					colorB -= colorStepAB * yB;
					yB = 0;
				}

				if (xStepBC < xStepAC) {
					yA -= yB;
					yB -= yC;

					yC = offsets[yC];
					while (--yB >= 0) {
						drawGouraudScanline(data, yC, xA >> 16, xC >> 16, colorA >> 7, colorC >> 7);
						xA += xStepBC;
						xC += xStepAC;
						colorA += colorStepBC;
						colorC += colorStepAC;
						yC += width;
					}

					while (--yA >= 0) {
						drawGouraudScanline(data, yC, xB >> 16, xC >> 16, colorB >> 7, colorC >> 7);
						xB += xStepAB;
						xC += xStepAC;
						colorB += colorStepAB;
						colorC += colorStepAC;
						yC += width;
					}
				} else {
					yA -= yB;
					yB -= yC;

					yC = offsets[yC];
					while (--yB >= 0) {
						drawGouraudScanline(data, yC, xC >> 16, xA >> 16, colorC >> 7, colorA >> 7);
						xA += xStepBC;
						xC += xStepAC;
						colorA += colorStepBC;
						colorC += colorStepAC;
						yC += width;
					}

					while (--yA >= 0) {
						drawGouraudScanline(data, yC, xC >> 16, xB >> 16, colorC >> 7, colorB >> 7);
						xB += xStepAB;
						xC += xStepAC;
						colorB += colorStepAB;
						colorC += colorStepAC;
						yC += width;
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
		@Pc(97) int color;
		@Pc(130) int length;

		if (jagged) {
			if (testX) {
				if (rightX - leftX > 3) {
					colorStep = (rightColor - leftColor) / (rightX - leftX);
				} else {
					colorStep = 0;
				}

				if (rightX > safeX) {
					rightX = safeX;
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

				length = rightX - leftX & 0x3;
				if (length > 0) {
					color = palette[leftColor >> 8];
					do {
						pixels[offset++] = color;
						length--;
					} while (length > 0);
				}
			} else {
				alpha = Draw3D.alpha;
				invAlpha = 256 - Draw3D.alpha;

				while (--length >= 0) {
					color = palette[leftColor >> 8];
					leftColor += colorStep;
					color = ((color & 0xFF00FF) * invAlpha >> 8 & 0xFF00FF) + ((color & 0xFF00) * invAlpha >> 8 & 0xFF00);
					pixels[offset] = color + ((pixels[offset] & 0xFF00FF) * alpha >> 8 & 0xFF00FF) + ((pixels[offset] & 0xFF00) * alpha >> 8 & 0xFF00);
					offset++; // post-increment to fix a transparency bug resulting in overlapping lines
					pixels[offset] = color + ((pixels[offset] & 0xFF00FF) * alpha >> 8 & 0xFF00FF) + ((pixels[offset] & 0xFF00) * alpha >> 8 & 0xFF00);
					offset++;
					pixels[offset] = color + ((pixels[offset] & 0xFF00FF) * alpha >> 8 & 0xFF00FF) + ((pixels[offset] & 0xFF00) * alpha >> 8 & 0xFF00);
					offset++;
					pixels[offset] = color + ((pixels[offset] & 0xFF00FF) * alpha >> 8 & 0xFF00FF) + ((pixels[offset] & 0xFF00) * alpha >> 8 & 0xFF00);
					offset++;
				}

				length = rightX - leftX & 0x3;
				if (length > 0) {
					color = palette[leftColor >> 8];
					color = ((color & 0xFF00FF) * invAlpha >> 8 & 0xFF00FF) + ((color & 0xFF00) * invAlpha >> 8 & 0xFF00);
					do {
						pixels[offset] = color + ((pixels[offset] & 0xFF00FF) * alpha >> 8 & 0xFF00FF) + ((pixels[offset] & 0xFF00) * alpha >> 8 & 0xFF00);
						offset++;
					} while (--length > 0);
				}
			}
		} else if (leftX < rightX) {
			colorStep = (rightColor - leftColor) / (rightX - leftX);

			if (testX) {
				if (rightX > safeX) {
					rightX = safeX;
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
					color = ((color & 0xFF00FF) * invAlpha >> 8 & 0xFF00FF) + ((color & 0xFF00) * invAlpha >> 8 & 0xFF00);
					pixels[offset] = color + ((pixels[offset] & 0xFF00FF) * alpha >> 8 & 0xFF00FF) + ((pixels[offset] & 0xFF00) * alpha >> 8 & 0xFF00);
					offset++; // post-increment to fix a transparency bug resulting in overlapping lines
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
			if (arg0 < bottom) {
				if (arg1 > bottom) {
					arg1 = bottom;
				}
				if (arg2 > bottom) {
					arg2 = bottom;
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
									drawScanline(data, arg0, arg6, arg5 >> 16, arg4 >> 16);
									arg5 += local33;
									arg4 += local18;
									arg0 += width;
								}
							}
							drawScanline(data, arg0, arg6, arg5 >> 16, arg3 >> 16);
							arg5 += local33;
							arg3 += local3;
							arg0 += width;
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
									drawScanline(data, arg0, arg6, arg4 >> 16, arg5 >> 16);
									arg5 += local33;
									arg4 += local18;
									arg0 += width;
								}
							}
							drawScanline(data, arg0, arg6, arg3 >> 16, arg5 >> 16);
							arg5 += local33;
							arg3 += local3;
							arg0 += width;
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
									drawScanline(data, arg0, arg6, arg5 >> 16, arg3 >> 16);
									arg5 += local18;
									arg3 += local3;
									arg0 += width;
								}
							}
							drawScanline(data, arg0, arg6, arg4 >> 16, arg3 >> 16);
							arg4 += local33;
							arg3 += local3;
							arg0 += width;
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
									drawScanline(data, arg0, arg6, arg3 >> 16, arg5 >> 16);
									arg5 += local18;
									arg3 += local3;
									arg0 += width;
								}
							}
							drawScanline(data, arg0, arg6, arg3 >> 16, arg4 >> 16);
							arg4 += local33;
							arg3 += local3;
							arg0 += width;
						}
					}
				}
			}
		} else if (arg1 <= arg2) {
			if (arg1 < bottom) {
				if (arg2 > bottom) {
					arg2 = bottom;
				}
				if (arg0 > bottom) {
					arg0 = bottom;
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
									drawScanline(data, arg1, arg6, arg3 >> 16, arg5 >> 16);
									arg3 += local3;
									arg5 += local33;
									arg1 += width;
								}
							}
							drawScanline(data, arg1, arg6, arg3 >> 16, arg4 >> 16);
							arg3 += local3;
							arg4 += local18;
							arg1 += width;
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
									drawScanline(data, arg1, arg6, arg5 >> 16, arg3 >> 16);
									arg3 += local3;
									arg5 += local33;
									arg1 += width;
								}
							}
							drawScanline(data, arg1, arg6, arg4 >> 16, arg3 >> 16);
							arg3 += local3;
							arg4 += local18;
							arg1 += width;
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
									drawScanline(data, arg1, arg6, arg3 >> 16, arg4 >> 16);
									arg3 += local33;
									arg4 += local18;
									arg1 += width;
								}
							}
							drawScanline(data, arg1, arg6, arg5 >> 16, arg4 >> 16);
							arg5 += local3;
							arg4 += local18;
							arg1 += width;
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
									drawScanline(data, arg1, arg6, arg4 >> 16, arg3 >> 16);
									arg3 += local33;
									arg4 += local18;
									arg1 += width;
								}
							}
							drawScanline(data, arg1, arg6, arg4 >> 16, arg5 >> 16);
							arg5 += local3;
							arg4 += local18;
							arg1 += width;
						}
					}
				}
			}
		} else if (arg2 < bottom) {
			if (arg0 > bottom) {
				arg0 = bottom;
			}
			if (arg1 > bottom) {
				arg1 = bottom;
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
								drawScanline(data, arg2, arg6, arg4 >> 16, arg3 >> 16);
								arg4 += local18;
								arg3 += local3;
								arg2 += width;
							}
						}
						drawScanline(data, arg2, arg6, arg4 >> 16, arg5 >> 16);
						arg4 += local18;
						arg5 += local33;
						arg2 += width;
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
								drawScanline(data, arg2, arg6, arg3 >> 16, arg4 >> 16);
								arg4 += local18;
								arg3 += local3;
								arg2 += width;
							}
						}
						drawScanline(data, arg2, arg6, arg5 >> 16, arg4 >> 16);
						arg4 += local18;
						arg5 += local33;
						arg2 += width;
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
								drawScanline(data, arg2, arg6, arg4 >> 16, arg5 >> 16);
								arg4 += local3;
								arg5 += local33;
								arg2 += width;
							}
						}
						drawScanline(data, arg2, arg6, arg3 >> 16, arg5 >> 16);
						arg3 += local18;
						arg5 += local33;
						arg2 += width;
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
								drawScanline(data, arg2, arg6, arg5 >> 16, arg4 >> 16);
								arg4 += local3;
								arg5 += local33;
								arg2 += width;
							}
						}
						drawScanline(data, arg2, arg6, arg5 >> 16, arg3 >> 16);
						arg3 += local18;
						arg5 += local33;
						arg2 += width;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([IIIIII)V")
	private static void drawScanline(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		if (testX) {
			if (arg5 > safeX) {
				arg5 = safeX;
			}
			if (arg4 < 0) {
				arg4 = 0;
			}
		}
		if (arg4 >= arg5) {
			return;
		}
		arg1 += arg4;
		@Pc(26) int local26 = arg5 - arg4 >> 2;
		@Pc(33) int local33;
		if (alpha == 0) {
			while (true) {
				local26--;
				if (local26 < 0) {
					local26 = arg5 - arg4 & 0x3;
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
		}

		@Pc(72) int local72 = alpha;
		@Pc(76) int local76 = 256 - alpha;
		@Pc(96) int local96 = ((arg2 & 0xFF00FF) * local76 >> 8 & 0xFF00FF) + ((arg2 & 0xFF00) * local76 >> 8 & 0xFF00);
		while (true) {
			local26--;
			if (local26 < 0) {
				local26 = arg5 - arg4 & 0x3;
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
			if (arg0 < bottom) {
				if (arg1 > bottom) {
					arg1 = bottom;
				}
				if (arg2 > bottom) {
					arg2 = bottom;
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
									drawTexturedScanline(data, local4, 0, 0, arg0, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
									arg5 += local182;
									arg4 += local155;
									arg8 += local184;
									arg7 += local157;
									arg0 += width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(data, local4, 0, 0, arg0, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
							arg5 += local182;
							arg3 += local128;
							arg8 += local184;
							arg6 += local130;
							arg0 += width;
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
									drawTexturedScanline(data, local4, 0, 0, arg0, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
									arg5 += local182;
									arg4 += local155;
									arg8 += local184;
									arg7 += local157;
									arg0 += width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(data, local4, 0, 0, arg0, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
							arg5 += local182;
							arg3 += local128;
							arg8 += local184;
							arg6 += local130;
							arg0 += width;
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
									drawTexturedScanline(data, local4, 0, 0, arg0, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
									arg5 += local155;
									arg3 += local128;
									arg8 += local157;
									arg6 += local130;
									arg0 += width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(data, local4, 0, 0, arg0, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
							arg4 += local182;
							arg3 += local128;
							arg7 += local184;
							arg6 += local130;
							arg0 += width;
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
									drawTexturedScanline(data, local4, 0, 0, arg0, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
									arg5 += local155;
									arg3 += local128;
									arg8 += local157;
									arg6 += local130;
									arg0 += width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(data, local4, 0, 0, arg0, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
							arg4 += local182;
							arg3 += local128;
							arg7 += local184;
							arg6 += local130;
							arg0 += width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					}
				}
			}
		} else if (arg1 <= arg2) {
			if (arg1 < bottom) {
				if (arg2 > bottom) {
					arg2 = bottom;
				}
				if (arg0 > bottom) {
					arg0 = bottom;
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
									drawTexturedScanline(data, local4, 0, 0, arg1, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
									arg3 += local128;
									arg5 += local182;
									arg6 += local130;
									arg8 += local184;
									arg1 += width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(data, local4, 0, 0, arg1, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
							arg3 += local128;
							arg4 += local155;
							arg6 += local130;
							arg7 += local157;
							arg1 += width;
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
									drawTexturedScanline(data, local4, 0, 0, arg1, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
									arg3 += local128;
									arg5 += local182;
									arg6 += local130;
									arg8 += local184;
									arg1 += width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(data, local4, 0, 0, arg1, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
							arg3 += local128;
							arg4 += local155;
							arg6 += local130;
							arg7 += local157;
							arg1 += width;
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
									drawTexturedScanline(data, local4, 0, 0, arg1, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
									arg3 += local182;
									arg4 += local155;
									arg6 += local184;
									arg7 += local157;
									arg1 += width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(data, local4, 0, 0, arg1, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
							arg5 += local128;
							arg4 += local155;
							arg8 += local130;
							arg7 += local157;
							arg1 += width;
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
									drawTexturedScanline(data, local4, 0, 0, arg1, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
									arg3 += local182;
									arg4 += local155;
									arg6 += local184;
									arg7 += local157;
									arg1 += width;
									local46 += local66;
									local76 += local96;
									local106 += local126;
								}
							}
							drawTexturedScanline(data, local4, 0, 0, arg1, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
							arg5 += local128;
							arg4 += local155;
							arg8 += local130;
							arg7 += local157;
							arg1 += width;
							local46 += local66;
							local76 += local96;
							local106 += local126;
						}
					}
				}
			}
		} else if (arg2 < bottom) {
			if (arg0 > bottom) {
				arg0 = bottom;
			}
			if (arg1 > bottom) {
				arg1 = bottom;
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
								drawTexturedScanline(data, local4, 0, 0, arg2, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
								arg4 += local155;
								arg3 += local128;
								arg7 += local157;
								arg6 += local130;
								arg2 += width;
								local46 += local66;
								local76 += local96;
								local106 += local126;
							}
						}
						drawTexturedScanline(data, local4, 0, 0, arg2, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
						arg4 += local155;
						arg5 += local182;
						arg7 += local157;
						arg8 += local184;
						arg2 += width;
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
								drawTexturedScanline(data, local4, 0, 0, arg2, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
								arg4 += local155;
								arg3 += local128;
								arg7 += local157;
								arg6 += local130;
								arg2 += width;
								local46 += local66;
								local76 += local96;
								local106 += local126;
							}
						}
						drawTexturedScanline(data, local4, 0, 0, arg2, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
						arg4 += local155;
						arg5 += local182;
						arg7 += local157;
						arg8 += local184;
						arg2 += width;
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
								drawTexturedScanline(data, local4, 0, 0, arg2, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
								arg4 += local128;
								arg5 += local182;
								arg7 += local130;
								arg8 += local184;
								arg2 += width;
								local46 += local66;
								local76 += local96;
								local106 += local126;
							}
						}
						drawTexturedScanline(data, local4, 0, 0, arg2, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, local46, local76, local106, local56, local86, local116);
						arg3 += local155;
						arg5 += local182;
						arg6 += local157;
						arg8 += local184;
						arg2 += width;
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
								drawTexturedScanline(data, local4, 0, 0, arg2, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, local46, local76, local106, local56, local86, local116);
								arg4 += local128;
								arg5 += local182;
								arg7 += local130;
								arg8 += local184;
								arg2 += width;
								local46 += local66;
								local76 += local96;
								local106 += local126;
							}
						}
						drawTexturedScanline(data, local4, 0, 0, arg2, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, local46, local76, local106, local56, local86, local116);
						arg3 += local155;
						arg5 += local182;
						arg6 += local157;
						arg8 += local184;
						arg2 += width;
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
			if (arg6 > safeX) {
				arg6 = safeX;
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
		} else {
			if (arg6 - arg5 > 7) {
				local40 = arg6 - arg5 >> 3;
				local15 = (arg8 - arg7) * reciprical15[local40] >> 6;
			} else {
				local40 = 0;
				local15 = 0;
			}
		}
		arg7 <<= 0x9;
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
}
