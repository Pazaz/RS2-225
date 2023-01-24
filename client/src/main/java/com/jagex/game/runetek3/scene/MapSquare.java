package com.jagex.game.runetek3.scene;

import com.jagex.core.util.LinkedList;
import com.jagex.game.runetek3.graphics.Draw2D;
import com.jagex.game.runetek3.graphics.Draw3D;
import com.jagex.game.runetek3.graphics.Model;
import com.jagex.game.runetek3.graphics.VertexNormal;
import com.jagex.game.runetek3.scene.entities.Entity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.util.Arrays;

@OriginalClass("client!r")
public class MapSquare {

	@OriginalMember(owner = "client!r", name = "I", descriptor = "[I")
	public static final int[] DECO_TYPE1_OFFSET_X = new int[] { 53, -53, -53, 53 };

	@OriginalMember(owner = "client!r", name = "J", descriptor = "[I")
	public static final int[] DECO_TYPE1_OFFSET_Z = new int[] { -53, -53, 53, 53 };

	@OriginalMember(owner = "client!r", name = "K", descriptor = "[I")
	public static final int[] DECO_TYPE2_OFFSET_X = new int[] { -45, 45, 45, -45 };

	@OriginalMember(owner = "client!r", name = "L", descriptor = "[I")
	public static final int[] DECO_TYPE2_OFFSET_Z = new int[] { 45, 45, -45, -45 };

	@OriginalMember(owner = "client!r", name = "R", descriptor = "I")
	public static final int MAX_OCCLUDER_LEVELS = 4;

	@OriginalMember(owner = "client!r", name = "V", descriptor = "[Lclient!m;")
	public static final Occluder[] activeOccluders = new Occluder[500];

	@OriginalMember(owner = "client!r", name = "X", descriptor = "[I")
	public static final int[] TILE_WALL_DRAW_FLAGS_0 = new int[] { 19, 55, 38, 155, 255, 110, 137, 205, 76 };

	@OriginalMember(owner = "client!r", name = "Y", descriptor = "[I")
	public static final int[] WALL_DRAW_FLAGS = new int[] { 160, 192, 80, 96, 0, 144, 80, 48, 160 };

	@OriginalMember(owner = "client!r", name = "Z", descriptor = "[I")
	public static final int[] TILE_WALL_DRAW_FLAGS_1 = new int[] { 76, 8, 137, 4, 0, 1, 38, 2, 19 };

	@OriginalMember(owner = "client!r", name = "ab", descriptor = "[I")
	public static final int[] WALL_UNCULL_FLAGS_0 = new int[] { 0, 0, 2, 0, 0, 2, 1, 1, 0 };

	@OriginalMember(owner = "client!r", name = "bb", descriptor = "[I")
	public static final int[] WALL_UNCULL_FLAGS_1 = new int[] { 2, 0, 0, 2, 0, 0, 0, 4, 4 };

	@OriginalMember(owner = "client!r", name = "cb", descriptor = "[I")
	public static final int[] WALL_UNCULL_FLAGS_2 = new int[] { 0, 4, 4, 8, 0, 0, 8, 0, 0 };

	@OriginalMember(owner = "client!r", name = "db", descriptor = "[I")
	public static final int[] WALL_UNCULL_FLAGS_3 = new int[] { 1, 1, 0, 0, 0, 8, 0, 0, 8 };

	@OriginalMember(owner = "client!r", name = "eb", descriptor = "[I")
	public static final int[] TEXTURE_HSL = new int[] { 41, 39248, 41, 4643, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 43086, 41, 41, 41, 41, 41, 41, 41, 8602, 41, 28992, 41, 41, 41, 41, 41, 5056, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 3131, 41, 41, 41 };

	@OriginalMember(owner = "client!r", name = "T", descriptor = "[[Lclient!m;")
	public static Occluder[][] levelOccluders = new Occluder[MAX_OCCLUDER_LEVELS][500];

	@OriginalMember(owner = "client!r", name = "S", descriptor = "[I")
	public static int[] levelOccluderCount = new int[MAX_OCCLUDER_LEVELS];

	@OriginalMember(owner = "client!r", name = "r", descriptor = "I")
	public static int lastTileUpdateCount;

	@OriginalMember(owner = "client!r", name = "s", descriptor = "I")
	public static int tileUpdateCount;

	@OriginalMember(owner = "client!r", name = "t", descriptor = "I")
	public static int activeLevel;

	@OriginalMember(owner = "client!r", name = "u", descriptor = "I")
	public static int minTileX;

	@OriginalMember(owner = "client!r", name = "v", descriptor = "I")
	public static int maxTileX;

	@OriginalMember(owner = "client!r", name = "w", descriptor = "I")
	public static int minTileZ;

	@OriginalMember(owner = "client!r", name = "x", descriptor = "I")
	public static int maxTileZ;

	@OriginalMember(owner = "client!r", name = "y", descriptor = "I")
	public static int screenCenterX;

	@OriginalMember(owner = "client!r", name = "z", descriptor = "I")
	public static int screenCenterY;

	@OriginalMember(owner = "client!r", name = "A", descriptor = "I")
	public static int eyeX;

	@OriginalMember(owner = "client!r", name = "B", descriptor = "I")
	public static int eyeY;

	@OriginalMember(owner = "client!r", name = "C", descriptor = "I")
	public static int eyeZ;

	@OriginalMember(owner = "client!r", name = "D", descriptor = "I")
	public static int pitchsin;

	@OriginalMember(owner = "client!r", name = "E", descriptor = "I")
	public static int pitchcos;

	@OriginalMember(owner = "client!r", name = "F", descriptor = "I")
	public static int yawsin;

	@OriginalMember(owner = "client!r", name = "G", descriptor = "I")
	public static int yawcos;

	@OriginalMember(owner = "client!r", name = "M", descriptor = "Z")
	public static boolean checkClick;

	@OriginalMember(owner = "client!r", name = "N", descriptor = "I")
	public static int clickX;

	@OriginalMember(owner = "client!r", name = "O", descriptor = "I")
	public static int clickZ;

	@OriginalMember(owner = "client!r", name = "U", descriptor = "I")
	public static int activeOccluderCount;

	@OriginalMember(owner = "client!r", name = "lb", descriptor = "[[Z")
	public static boolean[][] visibilityMap;

	@OriginalMember(owner = "client!r", name = "h", descriptor = "Z")
	public static boolean lowMemory = true;

	@OriginalMember(owner = "client!r", name = "H", descriptor = "[Lclient!p;")
	public static Loc[] locBuffer = new Loc[100];

	@OriginalMember(owner = "client!r", name = "P", descriptor = "I")
	public static int clickedTileX = -1;

	@OriginalMember(owner = "client!r", name = "Q", descriptor = "I")
	public static int clickedTileZ = -1;

	@OriginalMember(owner = "client!r", name = "W", descriptor = "Lclient!ob;")
	public static LinkedList tileQueue = new LinkedList();

	@OriginalMember(owner = "client!r", name = "kb", descriptor = "[[[[Z")
	public static boolean[][][][] visibilityMaps = new boolean[8][32][51][51];

	@OriginalMember(owner = "client!r", name = "mb", descriptor = "I")
	private static int viewportCenterX;

	@OriginalMember(owner = "client!r", name = "nb", descriptor = "I")
	private static int viewportCenterY;

	@OriginalMember(owner = "client!r", name = "ob", descriptor = "I")
	private static int viewportLeft;

	@OriginalMember(owner = "client!r", name = "pb", descriptor = "I")
	private static int viewportTop;

	@OriginalMember(owner = "client!r", name = "qb", descriptor = "I")
	private static int viewportRight;

	@OriginalMember(owner = "client!r", name = "rb", descriptor = "I")
	private static int viewportBottom;

	@OriginalMember(owner = "client!r", name = "n", descriptor = "I")
	private int minLevel;

	@OriginalMember(owner = "client!r", name = "o", descriptor = "I")
	private int locCount;

	@OriginalMember(owner = "client!r", name = "hb", descriptor = "I")
	private int normalMergeIndex;

	@OriginalMember(owner = "client!r", name = "p", descriptor = "[Lclient!p;")
	private final Loc[] locs = new Loc[5000];

	@OriginalMember(owner = "client!r", name = "fb", descriptor = "[I")
	private final int[] vertexAMergeIndex = new int[10000];

	@OriginalMember(owner = "client!r", name = "gb", descriptor = "[I")
	private final int[] vertexBMergeIndex = new int[10000];

	@OriginalMember(owner = "client!r", name = "ib", descriptor = "[[I")
	private final int[][] TILE_MASK_2D = new int[][] { new int[16], { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };

	@OriginalMember(owner = "client!r", name = "jb", descriptor = "[[I")
	private final int[][] TILE_ROTATION_2D = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };

	@OriginalMember(owner = "client!r", name = "i", descriptor = "I")
	private final int maxLevel;

	@OriginalMember(owner = "client!r", name = "j", descriptor = "I")
	private final int tileCountX;

	@OriginalMember(owner = "client!r", name = "k", descriptor = "I")
	private final int tileCountZ;

	@OriginalMember(owner = "client!r", name = "m", descriptor = "[[[Lclient!cb;")
	private final Tile[][][] levelTiles;

	@OriginalMember(owner = "client!r", name = "q", descriptor = "[[[I")
	private final int[][][] levelTileCycles;

	@OriginalMember(owner = "client!r", name = "l", descriptor = "[[[I")
	private final int[][][] heightmap;

	@OriginalMember(owner = "client!r", name = "<init>", descriptor = "(I[[[IIII)V")
	public MapSquare(@OriginalArg(1) int[][][] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		this.maxLevel = arg3;
		this.tileCountX = arg4;
		this.tileCountZ = arg2;
		this.levelTiles = new Tile[arg3][arg4][arg2];
		this.levelTileCycles = new int[arg3][arg4 + 1][arg2 + 1];
		this.heightmap = arg1;
		this.reset();
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Z)V")
	public static void unload() {
		locBuffer = null;
		levelOccluderCount = null;
		levelOccluders = null;
		tileQueue = null;
		visibilityMaps = null;
		visibilityMap = null;
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIIIIII)V")
	public static void addOccluder(@OriginalArg(0) int maxZ, @OriginalArg(1) int minX, @OriginalArg(3) int maxY, @OriginalArg(4) int type, @OriginalArg(5) int maxX, @OriginalArg(6) int plane, @OriginalArg(7) int minY, @OriginalArg(8) int minZ) {
		@Pc(3) Occluder occluder = new Occluder();
		occluder.minTileX = minX / 128;
		occluder.maxTileX = maxX / 128;
		occluder.minTileZ = minZ / 128;
		occluder.maxTileZ = maxZ / 128;
		occluder.type = type;
		occluder.minX = minX;
		occluder.maxX = maxX;
		occluder.minZ = minZ;
		occluder.maxZ = maxZ;
		occluder.minY = minY;
		occluder.maxY = maxY;
		levelOccluders[plane][levelOccluderCount[plane]++] = occluder;
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "([IIIBII)V")
	public static void init(@OriginalArg(0) int[] pitchDistance, @OriginalArg(1) int arg1, @OriginalArg(2) int viewportWidth, @OriginalArg(4) int viewportHeight, @OriginalArg(5) int arg5) {
		viewportLeft = 0;
		viewportTop = 0;
		viewportRight = viewportWidth;
		viewportBottom = viewportHeight;
		viewportCenterX = viewportWidth / 2;
		viewportCenterY = viewportHeight / 2;

		@Pc(28) boolean[][][][] matrix = new boolean[9][32][53][53];
		for (@Pc(30) int pitch = 128; pitch <= 384; pitch += 32) {
			for (int yaw = 0; yaw < 2048; yaw += 64) {
				pitchsin = Model.sin[pitch];
				pitchcos = Model.cos[pitch];
				yawsin = Model.sin[yaw];
				yawcos = Model.cos[yaw];

				int pitchLevel = (pitch - 128) / 32;
				int yawLevel = yaw / 64;

				for (int dx = -26; dx <= 26; dx++) {
					for (@Pc(68) int dz = -26; dz <= 26; dz++) {
						int x = dx * 128;
						int z = dz * 128;

						@Pc(80) boolean visible = false;
						for (@Pc(83) int y = -arg5; y <= arg1; y += 128) {
							if (testPoint(x, z, pitchDistance[pitchLevel] + y)) {
								visible = true;
								break;
							}
						}

						matrix[pitchLevel][yawLevel][dx + 25 + 1][dz + 25 + 1] = visible;
					}
				}
			}
		}

		for (int pitchLevel = 0; pitchLevel < 8; pitchLevel++) {
			for (int yawLevel = 0; yawLevel < 32; yawLevel++) {
				for (int x = -25; x < 25; x++) {
					for (int z = -25; z < 25; z++) {
						@Pc(155) boolean visible = false;

						check_area:
						for (int dx = -1; dx <= 1; dx++) {
							for (int dz = -1; dz <= 1; dz++) {
								if (matrix[pitchLevel][yawLevel][x + dx + 25 + 1][z + dz + 25 + 1]) {
									visible = true;
									break check_area;
								} else if (matrix[pitchLevel][(yawLevel + 1) % 31][x + dx + 25 + 1][z + dz + 25 + 1]) {
									visible = true;
									break check_area;
								} else if (matrix[pitchLevel + 1][yawLevel][x + dx + 25 + 1][z + dz + 25 + 1]) {
									visible = true;
									break check_area;
								} else if (matrix[pitchLevel + 1][(yawLevel + 1) % 31][x + dx + 25 + 1][z + dz + 25 + 1]) {
									visible = true;
									break check_area;
								}
							}
						}

						visibilityMaps[pitchLevel][yawLevel][x + 25][z + 25] = visible;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "h", descriptor = "(IIII)Z")
	private static boolean testPoint(@OriginalArg(0) int x, @OriginalArg(1) int z, @OriginalArg(2) int y) {
		@Pc(11) int px = ((z * yawsin) + (x * yawcos)) >> 16;
		@Pc(21) int tmp = ((z * yawcos) - (x * yawsin)) >> 16;
		@Pc(31) int pz = ((y * pitchsin) + (tmp * pitchcos)) >> 16;
		@Pc(41) int py = ((y * pitchcos) - (tmp * pitchsin)) >> 16;
		if (pz < 50 || pz > 3500) {
			return false;
		}

		@Pc(45) int viewportX = viewportCenterX + ((px << 9) / pz);
		@Pc(76) int viewportY = viewportCenterY + ((py << 9) / pz);

		if (viewportX >= viewportLeft && viewportX <= viewportRight && viewportY >= viewportTop && viewportY <= viewportBottom) {
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(I)V")
	public void reset() {
		for (@Pc(3) int level = 0; level < this.maxLevel; level++) {
			for (int stx = 0; stx < this.tileCountX; stx++) {
				for (int stz = 0; stz < this.tileCountZ; stz++) {
					this.levelTiles[level][stx][stz] = null;
				}
			}
		}

		for (int i = 0; i < MAX_OCCLUDER_LEVELS; i++) {
			for (int j = 0; j < levelOccluderCount[i]; j++) {
				levelOccluders[i][j] = null;
			}

			levelOccluderCount[i] = 0;
		}

		for (int i = 0; i < this.locCount; i++) {
			this.locs[i] = null;
		}

		this.locCount = 0;
		Arrays.fill(locBuffer, null);
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(II)V")
	public void setup(@OriginalArg(1) int level) {
		this.minLevel = level;

		for (@Pc(6) int stx = 0; stx < this.tileCountX; stx++) {
			for (int stz = 0; stz < this.tileCountZ; stz++) {
				this.levelTiles[level][stx][stz] = new Tile(level, stx, stz);
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIB)V")
	public void setBridge(@OriginalArg(0) int stz, @OriginalArg(1) int stx) {
		@Pc(10) Tile ground = this.levelTiles[0][stx][stz];

		for (@Pc(12) int level = 0; level < 3; level++) {
			this.levelTiles[level][stx][stz] = this.levelTiles[level + 1][stx][stz];
			if (this.levelTiles[level][stx][stz] != null) {
				this.levelTiles[level][stx][stz].level--;
			}
		}

		if (this.levelTiles[0][stx][stz] == null) {
			this.levelTiles[0][stx][stz] = new Tile(0, stx, stz);
		}

		this.levelTiles[0][stx][stz].bridge = ground;
		this.levelTiles[3][stx][stz] = null;
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIII)V")
	public void setDrawLevel(@OriginalArg(0) int level, @OriginalArg(1) int stx, @OriginalArg(2) int stz, @OriginalArg(3) int drawLevel) {
		@Pc(8) Tile tile = this.levelTiles[level][stx][stz];

		if (tile != null) {
			this.levelTiles[level][stx][stz].drawLevel = drawLevel;
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIIIIIIIIIIIIIIIII)V")
	public void addTile(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11, @OriginalArg(12) int arg12, @OriginalArg(13) int arg13, @OriginalArg(14) int arg14, @OriginalArg(15) int arg15, @OriginalArg(16) int arg16, @OriginalArg(17) int arg17, @OriginalArg(18) int arg18, @OriginalArg(19) int arg19) {
		@Pc(14) TileUnderlay local14;
		@Pc(16) int local16;
		if (arg3 == 0) {
			local14 = new TileUnderlay(arg10, arg11, arg12, arg13, -1, arg18, false);
			for (local16 = arg0; local16 >= 0; local16--) {
				if (this.levelTiles[local16][arg1][arg2] == null) {
					this.levelTiles[local16][arg1][arg2] = new Tile(local16, arg1, arg2);
				}
			}
			this.levelTiles[arg0][arg1][arg2].underlay = local14;
		} else if (arg3 == 1) {
			local14 = new TileUnderlay(arg14, arg15, arg16, arg17, arg5, arg19, arg6 == arg7 && arg6 == arg8 && arg6 == arg9);
			for (local16 = arg0; local16 >= 0; local16--) {
				if (this.levelTiles[local16][arg1][arg2] == null) {
					this.levelTiles[local16][arg1][arg2] = new Tile(local16, arg1, arg2);
				}
			}
			this.levelTiles[arg0][arg1][arg2].underlay = local14;
		} else {
			@Pc(145) TileOverlay local145 = new TileOverlay(arg1, arg3, arg15, arg7, arg12, arg4, arg10, arg9, arg19, arg14, arg5, arg17, arg18, arg8, arg16, arg13, arg6, arg2, arg11);
			for (local16 = arg0; local16 >= 0; local16--) {
				if (this.levelTiles[local16][arg1][arg2] == null) {
					this.levelTiles[local16][arg1][arg2] = new Tile(local16, arg1, arg2);
				}
			}
			this.levelTiles[arg0][arg1][arg2].overlay = local145;
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Lclient!eb;BIIIIBI)V")
	public void addGroundDecoration(@OriginalArg(0) Model model, @OriginalArg(2) int x, @OriginalArg(3) int bitset, @OriginalArg(4) int z, @OriginalArg(5) int plane, @OriginalArg(6) byte info, @OriginalArg(7) int level) {
		@Pc(3) GroundDecoration decor = new GroundDecoration();
		decor.model = model;
		decor.x = x * 128 + 64;
		decor.z = z * 128 + 64;
		decor.plane = level;
		decor.bitset = bitset;
		decor.info = info;

		if (this.levelTiles[plane][x][z] == null) {
			this.levelTiles[plane][x][z] = new Tile(plane, x, z);
		}

		this.levelTiles[plane][x][z].groundDecoration = decor;
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Lclient!eb;Lclient!eb;IIIIILclient!eb;I)V")
	public void addObject(@OriginalArg(0) Model arg0, @OriginalArg(1) Model arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) Model arg7) {
		@Pc(3) ObjStack local3 = new ObjStack();
		local3.topObj = arg0;
		local3.x = arg6 * 128 + 64;
		local3.z = arg5 * 128 + 64;
		local3.y = arg2;
		local3.bitset = arg4;
		local3.bottomObj = arg1;
		local3.middleObj = arg7;
		@Pc(38) int local38 = 0;
		@Pc(47) Tile local47 = this.levelTiles[arg3][arg6][arg5];
		if (local47 != null) {
			for (@Pc(51) int local51 = 0; local51 < local47.locCount; local51++) {
				@Pc(60) int local60 = local47.locs[local51].model.collisionPoint;
				if (local60 > local38) {
					local38 = local60;
				}
			}
		}
		local3.offsetY = local38;
		if (this.levelTiles[arg3][arg6][arg5] == null) {
			this.levelTiles[arg3][arg6][arg5] = new Tile(arg3, arg6, arg5);
		}
		this.levelTiles[arg3][arg6][arg5].objEntity = local3;
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIILclient!eb;Lclient!eb;IIIB)V")
	public void addWall(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) Model arg5, @OriginalArg(6) Model arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) byte arg10) {
		if (arg5 != null || arg6 != null) {
			@Pc(8) Wall local8 = new Wall();
			local8.bitset = arg8;
			local8.info = arg10;
			local8.x = arg7 * 128 + 64;
			local8.z = arg9 * 128 + 64;
			local8.plane = arg1;
			local8.modelA = arg5;
			local8.modelB = arg6;
			local8.typeA = arg3;
			local8.typeB = arg0;
			for (@Pc(54) int local54 = arg2; local54 >= 0; local54--) {
				if (this.levelTiles[local54][arg7][arg9] == null) {
					this.levelTiles[local54][arg7][arg9] = new Tile(local54, arg7, arg9);
				}
			}
			this.levelTiles[arg2][arg7][arg9].wall = local8;
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIIIIIILclient!eb;BI)V")
	public void addWallDecoration(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) Model arg9, @OriginalArg(10) byte arg10, @OriginalArg(11) int arg11) {
		if (arg9 != null) {
			@Pc(10) WallDecoration local10 = new WallDecoration();
			local10.bitset = arg3;
			local10.info = arg10;
			local10.x = arg8 * 128 + arg7 + 64;
			local10.z = arg1 * 128 + arg2 + 64;
			local10.plane = arg0;
			local10.model = arg9;
			local10.typeA = arg5;
			local10.typeB = arg4;
			for (@Pc(48) int local48 = arg11; local48 >= 0; local48--) {
				if (this.levelTiles[local48][arg8][arg1] == null) {
					this.levelTiles[local48][arg8][arg1] = new Tile(local48, arg8, arg1);
				}
			}
			this.levelTiles[arg11][arg8][arg1].wallDecoration = local10;
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIILclient!w;IIIIBLclient!eb;II)Z")
	public boolean addLocation(@OriginalArg(0) int arg0, @OriginalArg(2) int arg2, @OriginalArg(3) Entity arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) byte arg8, @OriginalArg(9) Model arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11) {
		if (arg9 == null && arg3 == null) {
			return true;
		} else {
			@Pc(17) int local17 = arg6 * 128 + arg7 * 64;
			@Pc(25) int local25 = arg5 * 128 + arg11 * 64;
			return this.add(arg2, arg6, arg5, arg7, arg11, local17, local25, arg0, arg9, arg3, arg10, false, arg4, arg8);
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIIIZLclient!eb;Lclient!w;II)Z")
	public boolean add(@OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) boolean arg6, @OriginalArg(7) Model arg7, @OriginalArg(8) Entity arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10) {
		if (arg7 == null && arg8 == null) {
			return true;
		}
		@Pc(9) int local9 = arg4 - arg2;
		@Pc(13) int local13 = arg1 - arg2;
		@Pc(17) int local17 = arg4 + arg2;
		@Pc(21) int local21 = arg1 + arg2;
		if (arg6) {
			if (arg3 > 640 && arg3 < 1408) {
				local21 += 128;
			}
			if (arg3 > 1152 && arg3 < 1920) {
				local17 += 128;
			}
			if (arg3 > 1664 || arg3 < 384) {
				local13 -= 128;
			}
			if (arg3 > 128 && arg3 < 896) {
				local9 -= 128;
			}
		}
		local9 /= 128;
		local13 /= 128;
		local17 /= 128;
		local21 /= 128;
		return this.add(arg10, local9, local13, local17 + 1 - local9, local21 - local13 + 1, arg4, arg1, arg9, arg7, arg8, arg3, true, arg5, (byte) 0);
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IILclient!eb;IIIIIILclient!w;ZIII)Z")
	public boolean add(@OriginalArg(0) int arg0, @OriginalArg(2) Model arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) Entity arg9, @OriginalArg(11) int arg11, @OriginalArg(12) int arg12, @OriginalArg(13) int arg13) {
		return arg2 == null && arg9 == null ? true : this.add(arg11, arg8, arg7, arg0 + 1 - arg8, arg12 - arg7 + 1, arg13, arg3, arg4, arg2, arg9, arg6, true, arg5, (byte) 0);
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIIIIILclient!eb;Lclient!w;IZIB)Z")
	private boolean add(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) Model arg8, @OriginalArg(9) Entity arg9, @OriginalArg(10) int arg10, @OriginalArg(11) boolean arg11, @OriginalArg(12) int arg12, @OriginalArg(13) byte arg13) {
		if (arg8 == null && arg9 == null) {
			return false;
		}
		for (@Pc(9) int local9 = arg1; local9 < arg1 + arg3; local9++) {
			for (@Pc(13) int local13 = arg2; local13 < arg2 + arg4; local13++) {
				if (local9 < 0 || local13 < 0 || local9 >= this.tileCountX || local13 >= this.tileCountZ) {
					return false;
				}
				@Pc(38) Tile local38 = this.levelTiles[arg0][local9][local13];
				if (local38 != null && local38.locCount >= 5) {
					return false;
				}
			}
		}
		@Pc(62) Loc local62 = new Loc();
		local62.bitset = arg12;
		local62.info = arg13;
		local62.plane = arg0;
		local62.x = arg5;
		local62.z = arg6;
		local62.y = arg7;
		local62.model = arg8;
		local62.entity = arg9;
		local62.yaw = arg10;
		local62.minSceneTileX = arg1;
		local62.minSceneTileZ = arg2;
		local62.maxSceneTileX = arg1 + arg3 - 1;
		local62.maxSceneTileZ = arg2 + arg4 - 1;
		for (@Pc(111) int local111 = arg1; local111 < arg1 + arg3; local111++) {
			for (@Pc(115) int local115 = arg2; local115 < arg2 + arg4; local115++) {
				@Pc(119) int local119 = 0;
				if (local111 > arg1) {
					local119++;
				}
				if (local111 < arg1 + arg3 - 1) {
					local119 += 4;
				}
				if (local115 > arg2) {
					local119 += 8;
				}
				if (local115 < arg2 + arg4 - 1) {
					local119 += 2;
				}
				for (@Pc(145) int local145 = arg0; local145 >= 0; local145--) {
					if (this.levelTiles[local145][local111][local115] == null) {
						this.levelTiles[local145][local111][local115] = new Tile(local145, local111, local115);
					}
				}
				@Pc(182) Tile local182 = this.levelTiles[arg0][local111][local115];
				local182.locs[local182.locCount] = local62;
				local182.locSpan[local182.locCount] = local119;
				local182.locSpans |= local119;
				local182.locCount++;
			}
		}
		if (arg11) {
			this.locs[this.locCount++] = local62;
		}
		return true;
	}

	@OriginalMember(owner = "client!r", name = "b", descriptor = "(I)V")
	public void clearFrameLocs() {
		for (@Pc(1) int local1 = 0; local1 < this.locCount; local1++) {
			@Pc(8) Loc local8 = this.locs[local1];
			this.removeLocation(local8);
			this.locs[local1] = null;
		}
		this.locCount = 0;
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Lclient!p;B)V")
	private void removeLocation(@OriginalArg(0) Loc arg0) {
		for (@Pc(4) int local4 = arg0.minSceneTileX; local4 <= arg0.maxSceneTileX; local4++) {
			for (@Pc(9) int local9 = arg0.minSceneTileZ; local9 <= arg0.maxSceneTileZ; local9++) {
				@Pc(21) Tile local21 = this.levelTiles[arg0.plane][local4][local9];
				if (local21 != null) {
					@Pc(41) int local41;
					for (@Pc(25) int local25 = 0; local25 < local21.locCount; local25++) {
						if (local21.locs[local25] == arg0) {
							local21.locCount--;
							for (local41 = local25; local41 < local21.locCount; local41++) {
								local21.locs[local41] = local21.locs[local41 + 1];
								local21.locSpan[local41] = local21.locSpan[local41 + 1];
							}
							local21.locs[local21.locCount] = null;
							break;
						}
					}
					local21.locSpans = 0;
					for (local41 = 0; local41 < local21.locCount; local41++) {
						local21.locSpans |= local21.locSpan[local41];
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(ILclient!eb;III)V")
	public void setLocModel(@OriginalArg(0) int arg0, @OriginalArg(1) Model arg1, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg1 != null) {
			@Pc(13) Tile local13 = this.levelTiles[arg3][arg0][arg4];
			@Pc(21) int local21;
			if (local13 != null) {
				for (local21 = 0; local21 < local13.locCount; local21++) {
					@Pc(38) Loc local38 = local13.locs[local21];
					if ((local38.bitset >> 29 & 0x3) == 2) {
						local38.model = arg1;
						return;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIB)V")
	public void setWallDecoration(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(8) Tile local8 = this.levelTiles[arg0][arg2][arg1];
		if (local8 != null) {
			@Pc(24) WallDecoration local24 = local8.wallDecoration;
			if (local24 != null) {
				@Pc(33) int local33 = arg2 * 128 + 64;
				@Pc(39) int local39 = arg1 * 128 + 64;
				local24.x = local33 + (local24.x - local33) * arg3 / 16;
				local24.z = local39 + (local24.z - local39) * arg3 / 16;
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIILclient!eb;I)V")
	public void setWallDecorationModel(@OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) Model arg3, @OriginalArg(4) int arg4) {
		if (arg3 != null) {
			@Pc(15) Tile local15 = this.levelTiles[arg4][arg2][arg1];
			if (local15 != null) {
				@Pc(21) WallDecoration local21 = local15.wallDecoration;
				if (local21 != null) {
					local21.model = arg3;
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Lclient!eb;IIII)V")
	public void setGroundDecorationModel(@OriginalArg(0) Model arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg0 != null) {
			@Pc(15) Tile local15 = this.levelTiles[arg4][arg3][arg1];
			if (local15 != null) {
				@Pc(21) GroundDecoration local21 = local15.groundDecoration;
				if (local21 != null) {
					local21.model = arg0;
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "b", descriptor = "(ILclient!eb;III)V")
	public void setWallModel(@OriginalArg(1) Model arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg1 != null) {
			@Pc(21) Tile local21 = this.levelTiles[arg4][arg3][arg2];
			if (local21 != null) {
				@Pc(27) Wall local27 = local21.wall;
				if (local27 != null) {
					local27.modelA = arg1;
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Lclient!eb;Lclient!eb;IZII)V")
	public void setWallModels(@OriginalArg(0) Model arg0, @OriginalArg(1) Model arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		if (arg0 != null) {
			@Pc(11) Tile local11 = this.levelTiles[arg5][arg4][arg2];
			if (local11 != null) {
				@Pc(17) Wall local17 = local11.wall;
				if (local17 != null) {
					local17.modelA = arg0;
					local17.modelB = arg1;
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "b", descriptor = "(IIII)V")
	public void removeWall(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(8) Tile local8 = this.levelTiles[arg1][arg0][arg2];
		if (local8 != null) {
			local8.wall = null;
		}
	}

	@OriginalMember(owner = "client!r", name = "c", descriptor = "(IIII)V")
	public void removeWallDecoration(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg3) {
		@Pc(8) Tile local8 = this.levelTiles[arg0][arg3][arg1];
		if (local8 != null) {
			local8.wallDecoration = null;
		}
	}

	@OriginalMember(owner = "client!r", name = "d", descriptor = "(IIII)V")
	public void removeLocation(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg3) {
		@Pc(10) Tile local10 = this.levelTiles[arg3][arg0][arg1];
		if (local10 != null) {
			for (@Pc(15) int local15 = 0; local15 < local10.locCount; local15++) {
				@Pc(22) Loc local22 = local10.locs[local15];
				if ((local22.bitset >> 29 & 0x3) == 2 && local22.minSceneTileX == arg0 && local22.minSceneTileZ == arg1) {
					this.removeLocation(local22);
					return;
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "e", descriptor = "(IIII)V")
	public void removeGroundDecoration(@OriginalArg(0) int arg0, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(16) Tile local16 = this.levelTiles[arg0][arg2][arg3];
		if (local16 != null) {
			local16.groundDecoration = null;
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(III)V")
	public void removeObject(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(8) Tile local8 = this.levelTiles[arg0][arg1][arg2];
		if (local8 != null) {
			local8.objEntity = null;
		}
	}

	@OriginalMember(owner = "client!r", name = "b", descriptor = "(III)I")
	public int getWallBitset(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(8) Tile local8 = this.levelTiles[arg0][arg1][arg2];
		return local8 == null || local8.wall == null ? 0 : local8.wall.bitset;
	}

	@OriginalMember(owner = "client!r", name = "f", descriptor = "(IIII)I")
	public int getWallDecorationBitset(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg3) {
		@Pc(19) Tile local19 = this.levelTiles[arg0][arg3][arg1];
		return local19 == null || local19.wallDecoration == null ? 0 : local19.wallDecoration.bitset;
	}

	@OriginalMember(owner = "client!r", name = "c", descriptor = "(III)I")
	public int getLocationBitset(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(8) Tile local8 = this.levelTiles[arg0][arg1][arg2];
		if (local8 == null) {
			return 0;
		}
		for (@Pc(14) int local14 = 0; local14 < local8.locCount; local14++) {
			@Pc(21) Loc local21 = local8.locs[local14];
			if ((local21.bitset >> 29 & 0x3) == 2 && local21.minSceneTileX == arg1 && local21.minSceneTileZ == arg2) {
				return local21.bitset;
			}
		}
		return 0;
	}

	@OriginalMember(owner = "client!r", name = "d", descriptor = "(III)I")
	public int getGroundDecorationBitset(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(8) Tile local8 = this.levelTiles[arg0][arg1][arg2];
		return local8 == null || local8.groundDecoration == null ? 0 : local8.groundDecoration.bitset;
	}

	@OriginalMember(owner = "client!r", name = "g", descriptor = "(IIII)I")
	public int getInfo(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(8) Tile local8 = this.levelTiles[arg0][arg1][arg2];
		if (local8 == null) {
			return -1;
		} else if (local8.wall != null && local8.wall.bitset == arg3) {
			return local8.wall.info & 0xFF;
		} else if (local8.wallDecoration != null && local8.wallDecoration.bitset == arg3) {
			return local8.wallDecoration.info & 0xFF;
		} else if (local8.groundDecoration != null && local8.groundDecoration.bitset == arg3) {
			return local8.groundDecoration.info & 0xFF;
		} else {
			for (@Pc(56) int local56 = 0; local56 < local8.locCount; local56++) {
				if (local8.locs[local56].bitset == arg3) {
					return local8.locs[local56].info & 0xFF;
				}
			}
			return -1;
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIIZ)V")
	public void applyLighting(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		@Pc(16) int local16 = (int) Math.sqrt(arg2 * arg2 + arg0 * arg0 + arg4 * arg4);
		@Pc(26) int local26 = arg3 * local16 >> 8;
		for (@Pc(28) int local28 = 0; local28 < this.maxLevel; local28++) {
			for (@Pc(32) int local32 = 0; local32 < this.tileCountX; local32++) {
				for (@Pc(36) int local36 = 0; local36 < this.tileCountZ; local36++) {
					@Pc(47) Tile local47 = this.levelTiles[local28][local32][local36];
					if (local47 != null) {
						@Pc(52) Wall local52 = local47.wall;
						if (local52 != null && local52.modelA != null && local52.modelA.vertexNormal != null) {
							this.mergeLocNormals(local32, 1, 1, local28, local52.modelA, local36);
							if (local52.modelB != null && local52.modelB.vertexNormal != null) {
								this.mergeLocNormals(local32, 1, 1, local28, local52.modelB, local36);
								this.mergeNormals(local52.modelA, local52.modelB, 0, 0, 0, false);
								local52.modelB.applyLighting(arg1, local26, arg2, arg0, arg4);
							}
							local52.modelA.applyLighting(arg1, local26, arg2, arg0, arg4);
						}
						for (@Pc(116) int local116 = 0; local116 < local47.locCount; local116++) {
							@Pc(123) Loc local123 = local47.locs[local116];
							if (local123 != null && local123.model != null && local123.model.vertexNormal != null) {
								this.mergeLocNormals(local32, local123.maxSceneTileX + 1 - local123.minSceneTileX, local123.maxSceneTileZ - local123.minSceneTileZ + 1, local28, local123.model, local36);
								local123.model.applyLighting(arg1, local26, arg2, arg0, arg4);
							}
						}
						@Pc(170) GroundDecoration local170 = local47.groundDecoration;
						if (local170 != null && local170.model.vertexNormal != null) {
							this.mergeGroundDecorationNormals(local28, local36, local170.model, local32);
							local170.model.applyLighting(arg1, local26, arg2, arg0, arg4);
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(BIILclient!eb;I)V")
	private void mergeGroundDecorationNormals(@OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) Model arg3, @OriginalArg(4) int arg4) {
		@Pc(19) Tile local19;
		if (arg4 < this.tileCountX) {
			local19 = this.levelTiles[arg1][arg4 + 1][arg2];
			if (local19 != null && local19.groundDecoration != null && local19.groundDecoration.model.vertexNormal != null) {
				this.mergeNormals(arg3, local19.groundDecoration.model, 128, 0, 0, true);
			}
		}
		if (arg2 < this.tileCountX) {
			local19 = this.levelTiles[arg1][arg4][arg2 + 1];
			if (local19 != null && local19.groundDecoration != null && local19.groundDecoration.model.vertexNormal != null) {
				this.mergeNormals(arg3, local19.groundDecoration.model, 0, 0, 128, true);
			}
		}
		if (arg4 < this.tileCountX && arg2 < this.tileCountZ) {
			local19 = this.levelTiles[arg1][arg4 + 1][arg2 + 1];
			if (local19 != null && local19.groundDecoration != null && local19.groundDecoration.model.vertexNormal != null) {
				this.mergeNormals(arg3, local19.groundDecoration.model, 128, 0, 128, true);
			}
		}
		if (arg4 < this.tileCountX && arg2 > 0) {
			local19 = this.levelTiles[arg1][arg4 + 1][arg2 - 1];
			if (local19 != null && local19.groundDecoration != null && local19.groundDecoration.model.vertexNormal != null) {
				this.mergeNormals(arg3, local19.groundDecoration.model, 128, 0, -128, true);
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIILclient!eb;I)V")
	private void mergeLocNormals(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) Model arg5, @OriginalArg(6) int arg6) {
		@Pc(7) boolean local7 = true;
		@Pc(9) int local9 = arg0;
		@Pc(13) int local13 = arg0 + arg1;
		@Pc(17) int local17 = arg6 - 1;
		@Pc(21) int local21 = arg6 + arg2;
		for (@Pc(23) int local23 = arg3; local23 <= arg3 + 1; local23++) {
			if (local23 != this.maxLevel) {
				for (@Pc(31) int local31 = local9; local31 <= local13; local31++) {
					if (local31 >= 0 && local31 < this.tileCountX) {
						for (@Pc(42) int local42 = local17; local42 <= local21; local42++) {
							if (local42 >= 0 && local42 < this.tileCountZ && (!local7 || local31 >= local13 || local42 >= local21 || local42 < arg6 && local31 != arg0)) {
								@Pc(75) Tile local75 = this.levelTiles[local23][local31][local42];
								if (local75 != null) {
									@Pc(169) int local169 = (this.heightmap[local23][local31][local42] + this.heightmap[local23][local31 + 1][local42] + this.heightmap[local23][local31][local42 + 1] + this.heightmap[local23][local31 + 1][local42 + 1]) / 4 - (this.heightmap[arg3][arg0][arg6] + this.heightmap[arg3][arg0 + 1][arg6] + this.heightmap[arg3][arg0][arg6 + 1] + this.heightmap[arg3][arg0 + 1][arg6 + 1]) / 4;
									@Pc(172) Wall local172 = local75.wall;
									if (local172 != null && local172.modelA != null && local172.modelA.vertexNormal != null) {
										this.mergeNormals(arg5, local172.modelA, (local31 - arg0) * 128 + (1 - arg1) * 64, local169, (local42 - arg6) * 128 + (1 - arg2) * 64, local7);
									}
									if (local172 != null && local172.modelB != null && local172.modelB.vertexNormal != null) {
										this.mergeNormals(arg5, local172.modelB, (local31 - arg0) * 128 + (1 - arg1) * 64, local169, (local42 - arg6) * 128 + (1 - arg2) * 64, local7);
									}
									for (@Pc(250) int local250 = 0; local250 < local75.locCount; local250++) {
										@Pc(257) Loc local257 = local75.locs[local250];
										if (local257 != null && local257.model != null && local257.model.vertexNormal != null) {
											@Pc(274) int local274 = local257.maxSceneTileX + 1 - local257.minSceneTileX;
											@Pc(282) int local282 = local257.maxSceneTileZ + 1 - local257.minSceneTileZ;
											this.mergeNormals(arg5, local257.model, (local257.minSceneTileX - arg0) * 128 + (local274 - arg1) * 64, local169, (local257.minSceneTileZ - arg6) * 128 + (local282 - arg2) * 64, local7);
										}
									}
								}
							}
						}
					}
				}
				local9--;
				local7 = false;
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Lclient!eb;Lclient!eb;IIIZ)V")
	private void mergeNormals(@OriginalArg(0) Model arg0, @OriginalArg(1) Model arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5) {
		this.normalMergeIndex++;
		@Pc(9) int local9 = 0;
		@Pc(12) int[] local12 = arg1.vertexX;
		@Pc(15) int local15 = arg1.vertexCount;
		for (@Pc(17) int local17 = 0; local17 < arg0.vertexCount; local17++) {
			@Pc(24) VertexNormal local24 = arg0.vertexNormal[local17];
			@Pc(29) VertexNormal local29 = arg0.vertexNormalOriginal[local17];
			if (local29.magnitude != 0) {
				@Pc(39) int local39 = arg0.vertexY[local17] - arg3;
				if (local39 <= arg1.minBoundY) {
					@Pc(50) int local50 = arg0.vertexX[local17] - arg2;
					if (local50 >= arg1.minBoundX && local50 <= arg1.maxBoundX) {
						@Pc(66) int local66 = arg0.vertexZ[local17] - arg4;
						if (local66 >= arg1.minBoundZ && local66 <= arg1.maxBoundZ) {
							for (@Pc(77) int local77 = 0; local77 < local15; local77++) {
								@Pc(84) VertexNormal local84 = arg1.vertexNormal[local77];
								@Pc(89) VertexNormal local89 = arg1.vertexNormalOriginal[local77];
								if (local50 == local12[local77] && local66 == arg1.vertexZ[local77] && local39 == arg1.vertexY[local77] && local89.magnitude != 0) {
									local24.x += local89.x;
									local24.y += local89.y;
									local24.z += local89.z;
									local24.magnitude += local89.magnitude;
									local84.x += local29.x;
									local84.y += local29.y;
									local84.z += local29.z;
									local84.magnitude += local29.magnitude;
									local9++;
									this.vertexAMergeIndex[local17] = this.normalMergeIndex;
									this.vertexBMergeIndex[local77] = this.normalMergeIndex;
								}
							}
						}
					}
				}
			}
		}
		if (local9 < 3 || !arg5) {
			return;
		}
		for (@Pc(195) int local195 = 0; local195 < arg0.faceCount; local195++) {
			if (this.vertexAMergeIndex[arg0.faceVertexA[local195]] == this.normalMergeIndex && this.vertexAMergeIndex[arg0.faceVertexB[local195]] == this.normalMergeIndex && this.vertexAMergeIndex[arg0.faceVertexC[local195]] == this.normalMergeIndex) {
				arg0.faceInfo[local195] = -1;
			}
		}
		for (@Pc(239) int local239 = 0; local239 < arg1.faceCount; local239++) {
			if (this.vertexBMergeIndex[arg1.faceVertexA[local239]] == this.normalMergeIndex && this.vertexBMergeIndex[arg1.faceVertexB[local239]] == this.normalMergeIndex && this.vertexBMergeIndex[arg1.faceVertexC[local239]] == this.normalMergeIndex) {
				arg1.faceInfo[local239] = -1;
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "([IIIIII)V")
	public void drawMinimapTile(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(10) Tile local10 = this.levelTiles[arg3][arg4][arg5];
		if (local10 == null) {
			return;
		}
		@Pc(16) TileUnderlay local16 = local10.underlay;
		@Pc(26) int local26;
		if (local16 != null) {
			@Pc(21) int local21 = local16.rgb;
			if (local21 != 0) {
				for (local26 = 0; local26 < 4; local26++) {
					arg0[arg1] = local21;
					arg0[arg1 + 1] = local21;
					arg0[arg1 + 2] = local21;
					arg0[arg1 + 3] = local21;
					arg1 += arg2;
				}
			}
			return;
		}
		@Pc(62) TileOverlay local62 = local10.overlay;
		if (local62 == null) {
			return;
		}
		local26 = local62.shape;
		@Pc(71) int local71 = local62.rotation;
		@Pc(74) int local74 = local62.backgroundRgb;
		@Pc(77) int local77 = local62.foregroundRgb;
		@Pc(82) int[] local82 = this.TILE_MASK_2D[local26];
		@Pc(87) int[] local87 = this.TILE_ROTATION_2D[local71];
		@Pc(89) int local89 = 0;
		@Pc(93) int local93;
		if (local74 != 0) {
			for (local93 = 0; local93 < 4; local93++) {
				arg0[arg1] = local82[local87[local89++]] == 0 ? local74 : local77;
				arg0[arg1 + 1] = local82[local87[local89++]] == 0 ? local74 : local77;
				arg0[arg1 + 2] = local82[local87[local89++]] == 0 ? local74 : local77;
				arg0[arg1 + 3] = local82[local87[local89++]] == 0 ? local74 : local77;
				arg1 += arg2;
			}
			return;
		}
		for (local93 = 0; local93 < 4; local93++) {
			if (local82[local87[local89++]] != 0) {
				arg0[arg1] = local77;
			}
			if (local82[local87[local89++]] != 0) {
				arg0[arg1 + 1] = local77;
			}
			if (local82[local87[local89++]] != 0) {
				arg0[arg1 + 2] = local77;
			}
			if (local82[local87[local89++]] != 0) {
				arg0[arg1 + 3] = local77;
			}
			arg1 += arg2;
		}
	}

	@OriginalMember(owner = "client!r", name = "e", descriptor = "(III)V")
	public void setClick(@OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		checkClick = true;
		clickX = arg2;
		clickZ = arg1;
		clickedTileX = -1;
		clickedTileZ = -1;
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIIII)V")
	public void draw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		if (arg1 < 0) {
			arg1 = 0;
		} else if (arg1 >= this.tileCountX * 128) {
			arg1 = this.tileCountX * 128 - 1;
		}
		if (arg5 < 0) {
			arg5 = 0;
		} else if (arg5 >= this.tileCountZ * 128) {
			arg5 = this.tileCountZ * 128 - 1;
		}
		activeLevel++;
		pitchsin = Model.sin[arg3];
		pitchcos = Model.cos[arg3];
		yawsin = Model.sin[arg0];
		yawcos = Model.cos[arg0];
		visibilityMap = visibilityMaps[(arg3 - 128) / 32][arg0 / 64];
		eyeX = arg1;
		eyeY = arg4;
		eyeZ = arg5;
		screenCenterX = arg1 / 128;
		screenCenterY = arg5 / 128;
		tileUpdateCount = arg2;
		minTileX = screenCenterX - 25;
		if (minTileX < 0) {
			minTileX = 0;
		}
		minTileZ = screenCenterY - 25;
		if (minTileZ < 0) {
			minTileZ = 0;
		}
		maxTileX = screenCenterX + 25;
		if (arg6 != 0) {
		}
		if (maxTileX > this.tileCountX) {
			maxTileX = this.tileCountX;
		}
		maxTileZ = screenCenterY + 25;
		if (maxTileZ > this.tileCountZ) {
			maxTileZ = this.tileCountZ;
		}
		this.updateOccluders();
		lastTileUpdateCount = 0;
		@Pc(147) int local147;
		@Pc(151) int local151;
		for (@Pc(138) int local138 = this.minLevel; local138 < this.maxLevel; local138++) {
			@Pc(145) Tile[][] local145 = this.levelTiles[local138];
			for (local147 = minTileX; local147 < maxTileX; local147++) {
				for (local151 = minTileZ; local151 < maxTileZ; local151++) {
					@Pc(159) Tile local159 = local145[local147][local151];
					if (local159 != null) {
						if (local159.drawLevel <= arg2 && (visibilityMap[local147 + 25 - screenCenterX][local151 + 25 - screenCenterY] || this.heightmap[local138][local147][local151] - arg4 >= 2000)) {
							local159.draw = true;
							local159.isVisible = true;
							if (local159.locCount > 0) {
								local159.drawLocs = true;
							} else {
								local159.drawLocs = false;
							}
							lastTileUpdateCount++;
						} else {
							local159.draw = false;
							local159.isVisible = false;
							local159.checkLocSpans = 0;
						}
					}
				}
			}
		}
		@Pc(258) int local258;
		@Pc(266) int local266;
		@Pc(272) int local272;
		@Pc(276) int local276;
		@Pc(254) int local254;
		for (@Pc(239) int local239 = this.minLevel; local239 < this.maxLevel; local239++) {
			@Pc(246) Tile[][] local246 = this.levelTiles[local239];
			for (local151 = -25; local151 <= 0; local151++) {
				local254 = screenCenterX + local151;
				local258 = screenCenterX - local151;
				if (local254 >= minTileX || local258 < maxTileX) {
					for (local266 = -25; local266 <= 0; local266++) {
						local272 = screenCenterY + local266;
						local276 = screenCenterY - local266;
						@Pc(288) Tile local288;
						if (local254 >= minTileX) {
							if (local272 >= minTileZ) {
								local288 = local246[local254][local272];
								if (local288 != null && local288.draw) {
									this.draw(local288, true);
								}
							}
							if (local276 < maxTileZ) {
								local288 = local246[local254][local276];
								if (local288 != null && local288.draw) {
									this.draw(local288, true);
								}
							}
						}
						if (local258 < maxTileX) {
							if (local272 >= minTileZ) {
								local288 = local246[local258][local272];
								if (local288 != null && local288.draw) {
									this.draw(local288, true);
								}
							}
							if (local276 < maxTileZ) {
								local288 = local246[local258][local276];
								if (local288 != null && local288.draw) {
									this.draw(local288, true);
								}
							}
						}
						if (lastTileUpdateCount == 0) {
							checkClick = false;
							return;
						}
					}
				}
			}
		}
		for (local147 = this.minLevel; local147 < this.maxLevel; local147++) {
			@Pc(380) Tile[][] local380 = this.levelTiles[local147];
			for (local254 = -25; local254 <= 0; local254++) {
				local258 = screenCenterX + local254;
				local266 = screenCenterX - local254;
				if (local258 >= minTileX || local266 < maxTileX) {
					for (local272 = -25; local272 <= 0; local272++) {
						local276 = screenCenterY + local272;
						@Pc(410) int local410 = screenCenterY - local272;
						@Pc(422) Tile local422;
						if (local258 >= minTileX) {
							if (local276 >= minTileZ) {
								local422 = local380[local258][local276];
								if (local422 != null && local422.draw) {
									this.draw(local422, false);
								}
							}
							if (local410 < maxTileZ) {
								local422 = local380[local258][local410];
								if (local422 != null && local422.draw) {
									this.draw(local422, false);
								}
							}
						}
						if (local266 < maxTileX) {
							if (local276 >= minTileZ) {
								local422 = local380[local266][local276];
								if (local422 != null && local422.draw) {
									this.draw(local422, false);
								}
							}
							if (local410 < maxTileZ) {
								local422 = local380[local266][local410];
								if (local422 != null && local422.draw) {
									this.draw(local422, false);
								}
							}
						}
						if (lastTileUpdateCount == 0) {
							checkClick = false;
							return;
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Lclient!cb;Z)V")
	private void draw(@OriginalArg(0) Tile arg0, @OriginalArg(1) boolean arg1) {
		tileQueue.pushNext(arg0);
		while (true) {
			@Pc(8) Tile local8;
			@Pc(17) int local17;
			@Pc(20) int local20;
			@Pc(23) int local23;
			@Pc(26) int local26;
			@Pc(31) Tile[][] local31;
			@Pc(49) Tile local49;
			@Pc(253) int local253;
			@Pc(599) int local599;
			@Pc(604) int local604;
			@Pc(609) int local609;
			@Pc(612) int local612;
			@Pc(621) int local621;
			@Pc(963) Wall local963;
			@Pc(1144) int local1144;
			@Pc(1023) int local1023;
			do {
				do {
					do {
						do {
							do {
								do {
									while (true) {
										@Pc(260) Loc var12;
										@Pc(349) int var22;
										@Pc(301) boolean var23;
										@Pc(846) Tile var35;
										while (true) {
											do {
												local8 = (Tile) tileQueue.poll();
												if (local8 == null) {
													return;
												}
											} while (!local8.isVisible);
											local17 = local8.x;
											local20 = local8.z;
											local23 = local8.level;
											local26 = local8.occludeLevel;
											local31 = this.levelTiles[local23];
											if (!local8.draw) {
												break;
											}
											if (arg1) {
												if (local23 > 0) {
													local49 = this.levelTiles[local23 - 1][local17][local20];
													if (local49 != null && local49.isVisible) {
														continue;
													}
												}
												if (local17 <= screenCenterX && local17 > minTileX) {
													local49 = local31[local17 - 1][local20];
													if (local49 != null && local49.isVisible && (local49.draw || (local8.locSpans & 0x1) == 0)) {
														continue;
													}
												}
												if (local17 >= screenCenterX && local17 < maxTileX - 1) {
													local49 = local31[local17 + 1][local20];
													if (local49 != null && local49.isVisible && (local49.draw || (local8.locSpans & 0x4) == 0)) {
														continue;
													}
												}
												if (local20 <= screenCenterY && local20 > minTileZ) {
													local49 = local31[local17][local20 - 1];
													if (local49 != null && local49.isVisible && (local49.draw || (local8.locSpans & 0x8) == 0)) {
														continue;
													}
												}
												if (local20 >= screenCenterY && local20 < maxTileZ - 1) {
													local49 = local31[local17][local20 + 1];
													if (local49 != null && local49.isVisible && (local49.draw || (local8.locSpans & 0x2) == 0)) {
														continue;
													}
												}
											} else {
												arg1 = true;
											}
											local8.draw = false;
											if (local8.bridge != null) {
												local49 = local8.bridge;
												if (local49.underlay == null) {
													if (local49.overlay != null && !this.isTileVisible(0, local17, local20)) {
														this.drawTileOverlay(yawsin, local20, local49.overlay, local17, pitchcos, pitchsin, yawcos);
													}
												} else if (!this.isTileVisible(0, local17, local20)) {
													this.drawTileUnderlay(local49.underlay, 0, pitchsin, pitchcos, yawsin, yawcos, local17, local20);
												}
												@Pc(227) Wall local227 = local49.wall;
												if (local227 != null) {
													local227.modelA.draw(0, pitchsin, pitchcos, yawsin, yawcos, local227.x - eyeX, local227.plane - eyeY, local227.z - eyeZ, local227.bitset);
												}
												for (local253 = 0; local253 < local49.locCount; local253++) {
													var12 = local49.locs[local253];
													if (var12 != null) {
														@Pc(265) Model local265 = var12.model;
														if (local265 == null) {
															local265 = var12.entity.getDrawMethod();
														}
														local265.draw(var12.yaw, pitchsin, pitchcos, yawsin, yawcos, var12.x - eyeX, var12.y - eyeY, var12.z - eyeZ, var12.bitset);
													}
												}
											}
											var23 = false;
											if (local8.underlay == null) {
												if (local8.overlay != null && !this.isTileVisible(local26, local17, local20)) {
													var23 = true;
													this.drawTileOverlay(yawsin, local20, local8.overlay, local17, pitchcos, pitchsin, yawcos);
												}
											} else if (!this.isTileVisible(local26, local17, local20)) {
												var23 = true;
												this.drawTileUnderlay(local8.underlay, local26, pitchsin, pitchcos, yawsin, yawcos, local17, local20);
											}
											var22 = 0;
											local253 = 0;
											@Pc(354) Wall local354 = local8.wall;
											@Pc(357) WallDecoration local357 = local8.wallDecoration;
											if (local354 != null || local357 != null) {
												if (screenCenterX == local17) {
													var22++;
												} else if (screenCenterX < local17) {
													var22 += 2;
												}
												if (screenCenterY == local20) {
													var22 += 3;
												} else if (screenCenterY > local20) {
													var22 += 6;
												}
												local253 = TILE_WALL_DRAW_FLAGS_0[var22];
												local8.backWallTypes = TILE_WALL_DRAW_FLAGS_1[var22];
											}
											if (local354 != null) {
												if ((local354.typeA & WALL_DRAW_FLAGS[var22]) == 0) {
													local8.checkLocSpans = 0;
												} else if (local354.typeA == 16) {
													local8.checkLocSpans = 3;
													local8.blockLocSpans = WALL_UNCULL_FLAGS_0[var22];
													local8.invBlockLocSpans = 3 - local8.blockLocSpans;
												} else if (local354.typeA == 32) {
													local8.checkLocSpans = 6;
													local8.blockLocSpans = WALL_UNCULL_FLAGS_1[var22];
													local8.invBlockLocSpans = 6 - local8.blockLocSpans;
												} else if (local354.typeA == 64) {
													local8.checkLocSpans = 12;
													local8.blockLocSpans = WALL_UNCULL_FLAGS_2[var22];
													local8.invBlockLocSpans = 12 - local8.blockLocSpans;
												} else {
													local8.checkLocSpans = 9;
													local8.blockLocSpans = WALL_UNCULL_FLAGS_3[var22];
													local8.invBlockLocSpans = 9 - local8.blockLocSpans;
												}
												if ((local354.typeA & local253) != 0 && !this.isWallVisible(local26, local17, local20, local354.typeA)) {
													local354.modelA.draw(0, pitchsin, pitchcos, yawsin, yawcos, local354.x - eyeX, local354.plane - eyeY, local354.z - eyeZ, local354.bitset);
												}
												if ((local354.typeB & local253) != 0 && !this.isWallVisible(local26, local17, local20, local354.typeB)) {
													local354.modelB.draw(0, pitchsin, pitchcos, yawsin, yawcos, local354.x - eyeX, local354.plane - eyeY, local354.z - eyeZ, local354.bitset);
												}
											}
											if (local357 != null && !this.isOccluded(local26, local17, local20, local357.model.maxBoundY)) {
												if ((local357.typeA & local253) != 0) {
													local357.model.draw(local357.typeB, pitchsin, pitchcos, yawsin, yawcos, local357.x - eyeX, local357.plane - eyeY, local357.z - eyeZ, local357.bitset);
												} else if ((local357.typeA & 0x300) != 0) {
													local599 = local357.x - eyeX;
													local604 = local357.plane - eyeY;
													local609 = local357.z - eyeZ;
													local612 = local357.typeB;
													if (local612 == 1 || local612 == 2) {
														local621 = -local599;
													} else {
														local621 = local599;
													}
													@Pc(634) int local634;
													if (local612 == 2 || local612 == 3) {
														local634 = -local609;
													} else {
														local634 = local609;
													}
													@Pc(652) int local652;
													@Pc(658) int local658;
													if ((local357.typeA & 0x100) != 0 && local634 < local621) {
														local652 = local599 + DECO_TYPE1_OFFSET_X[local612];
														local658 = local609 + DECO_TYPE1_OFFSET_Z[local612];
														local357.model.draw(local612 * 512 + 256, pitchsin, pitchcos, yawsin, yawcos, local652, local604, local658, local357.bitset);
													}
													if ((local357.typeA & 0x200) != 0 && local634 > local621) {
														local652 = local599 + DECO_TYPE2_OFFSET_X[local612];
														local658 = local609 + DECO_TYPE2_OFFSET_Z[local612];
														local357.model.draw(local612 * 512 + 1280 & 0x7FF, pitchsin, pitchcos, yawsin, yawcos, local652, local604, local658, local357.bitset);
													}
												}
											}
											if (var23) {
												@Pc(719) GroundDecoration local719 = local8.groundDecoration;
												if (local719 != null) {
													local719.model.draw(0, pitchsin, pitchcos, yawsin, yawcos, local719.x - eyeX, local719.plane - eyeY, local719.z - eyeZ, local719.bitset);
												}
												@Pc(746) ObjStack local746 = local8.objEntity;
												if (local746 != null && local746.offsetY == 0) {
													if (local746.bottomObj != null) {
														local746.bottomObj.draw(0, pitchsin, pitchcos, yawsin, yawcos, local746.x - eyeX, local746.y - eyeY, local746.z - eyeZ, local746.bitset);
													}
													if (local746.middleObj != null) {
														local746.middleObj.draw(0, pitchsin, pitchcos, yawsin, yawcos, local746.x - eyeX, local746.y - eyeY, local746.z - eyeZ, local746.bitset);
													}
													if (local746.topObj != null) {
														local746.topObj.draw(0, pitchsin, pitchcos, yawsin, yawcos, local746.x - eyeX, local746.y - eyeY, local746.z - eyeZ, local746.bitset);
													}
												}
											}
											local599 = local8.locSpans;
											if (local599 != 0) {
												if (local17 < screenCenterX && (local599 & 0x4) != 0) {
													var35 = local31[local17 + 1][local20];
													if (var35 != null && var35.isVisible) {
														tileQueue.pushNext(var35);
													}
												}
												if (local20 < screenCenterY && (local599 & 0x2) != 0) {
													var35 = local31[local17][local20 + 1];
													if (var35 != null && var35.isVisible) {
														tileQueue.pushNext(var35);
													}
												}
												if (local17 > screenCenterX && (local599 & 0x1) != 0) {
													var35 = local31[local17 - 1][local20];
													if (var35 != null && var35.isVisible) {
														tileQueue.pushNext(var35);
													}
												}
												if (local20 > screenCenterY && (local599 & 0x8) != 0) {
													var35 = local31[local17][local20 - 1];
													if (var35 != null && var35.isVisible) {
														tileQueue.pushNext(var35);
													}
												}
											}
											break;
										}
										if (local8.checkLocSpans != 0) {
											var23 = true;
											for (var22 = 0; var22 < local8.locCount; var22++) {
												if (local8.locs[var22].cycle != activeLevel && (local8.locSpan[var22] & local8.checkLocSpans) == local8.blockLocSpans) {
													var23 = false;
													break;
												}
											}
											if (var23) {
												local963 = local8.wall;
												if (!this.isWallVisible(local26, local17, local20, local963.typeA)) {
													local963.modelA.draw(0, pitchsin, pitchcos, yawsin, yawcos, local963.x - eyeX, local963.plane - eyeY, local963.z - eyeZ, local963.bitset);
												}
												local8.checkLocSpans = 0;
											}
										}
										if (!local8.drawLocs) {
											break;
										}
										@Pc(1002) int local1002 = local8.locCount;
										local8.drawLocs = false;
										var22 = 0;
										label559:
										for (local253 = 0; local253 < local1002; local253++) {
											var12 = local8.locs[local253];
											if (var12.cycle != activeLevel) {
												for (local1023 = var12.minSceneTileX; local1023 <= var12.maxSceneTileX; local1023++) {
													for (local599 = var12.minSceneTileZ; local599 <= var12.maxSceneTileZ; local599++) {
														var35 = local31[local1023][local599];
														if (var35.draw) {
															local8.drawLocs = true;
															continue label559;
														}
														if (var35.checkLocSpans != 0) {
															local609 = 0;
															if (local1023 > var12.minSceneTileX) {
																local609++;
															}
															if (local1023 < var12.maxSceneTileX) {
																local609 += 4;
															}
															if (local599 > var12.minSceneTileZ) {
																local609 += 8;
															}
															if (local599 < var12.maxSceneTileZ) {
																local609 += 2;
															}
															if ((local609 & var35.checkLocSpans) == local8.invBlockLocSpans) {
																local8.drawLocs = true;
																continue label559;
															}
														}
													}
												}
												locBuffer[var22++] = var12;
												local599 = screenCenterX - var12.minSceneTileX;
												local604 = var12.maxSceneTileX - screenCenterX;
												if (local604 > local599) {
													local599 = local604;
												}
												local609 = screenCenterY - var12.minSceneTileZ;
												local612 = var12.maxSceneTileZ - screenCenterY;
												if (local612 > local609) {
													var12.distance = local599 + local612;
												} else {
													var12.distance = local599 + local609;
												}
											}
										}
										while (var22 > 0) {
											local1144 = -50;
											local1023 = -1;
											@Pc(1154) Loc local1154;
											for (local599 = 0; local599 < var22; local599++) {
												local1154 = locBuffer[local599];
												if (local1154.distance > local1144 && local1154.cycle != activeLevel) {
													local1144 = local1154.distance;
													local1023 = local599;
												}
											}
											if (local1023 == -1) {
												break;
											}
											local1154 = locBuffer[local1023];
											local1154.cycle = activeLevel;
											@Pc(1184) Model local1184 = local1154.model;
											if (local1184 == null) {
												local1184 = local1154.entity.getDrawMethod();
											}
											if (!this.isAreaOccluded(local26, local1154.minSceneTileX, local1154.maxSceneTileX, local1154.minSceneTileZ, local1154.maxSceneTileZ, local1184.maxBoundY)) {
												local1184.draw(local1154.yaw, pitchsin, pitchcos, yawsin, yawcos, local1154.x - eyeX, local1154.y - eyeY, local1154.z - eyeZ, local1154.bitset);
											}
											for (local612 = local1154.minSceneTileX; local612 <= local1154.maxSceneTileX; local612++) {
												for (local621 = local1154.minSceneTileZ; local621 <= local1154.maxSceneTileZ; local621++) {
													@Pc(1243) Tile local1243 = local31[local612][local621];
													if (local1243.checkLocSpans != 0) {
														tileQueue.pushNext(local1243);
													} else if ((local612 != local17 || local621 != local20) && local1243.isVisible) {
														tileQueue.pushNext(local1243);
													}
												}
											}
										}
										if (!local8.drawLocs) {
											break;
										}
									}
								} while (!local8.isVisible);
							} while (local8.checkLocSpans != 0);
							if (local17 > screenCenterX || local17 <= minTileX) {
								break;
							}
							local49 = local31[local17 - 1][local20];
						} while (local49 != null && local49.isVisible);
						if (local17 < screenCenterX || local17 >= maxTileX - 1) {
							break;
						}
						local49 = local31[local17 + 1][local20];
					} while (local49 != null && local49.isVisible);
					if (local20 > screenCenterY || local20 <= minTileZ) {
						break;
					}
					local49 = local31[local17][local20 - 1];
				} while (local49 != null && local49.isVisible);
				if (local20 < screenCenterY || local20 >= maxTileZ - 1) {
					break;
				}
				local49 = local31[local17][local20 + 1];
			} while (local49 != null && local49.isVisible);
			local8.isVisible = false;
			lastTileUpdateCount--;
			@Pc(1379) ObjStack local1379 = local8.objEntity;
			if (local1379 != null && local1379.offsetY != 0) {
				if (local1379.bottomObj != null) {
					local1379.bottomObj.draw(0, pitchsin, pitchcos, yawsin, yawcos, local1379.x - eyeX, local1379.y - eyeY - local1379.offsetY, local1379.z - eyeZ, local1379.bitset);
				}
				if (local1379.middleObj != null) {
					local1379.middleObj.draw(0, pitchsin, pitchcos, yawsin, yawcos, local1379.x - eyeX, local1379.y - eyeY - local1379.offsetY, local1379.z - eyeZ, local1379.bitset);
				}
				if (local1379.topObj != null) {
					local1379.topObj.draw(0, pitchsin, pitchcos, yawsin, yawcos, local1379.x - eyeX, local1379.y - eyeY - local1379.offsetY, local1379.z - eyeZ, local1379.bitset);
				}
			}
			if (local8.backWallTypes != 0) {
				@Pc(1474) WallDecoration local1474 = local8.wallDecoration;
				if (local1474 != null && !this.isOccluded(local26, local17, local20, local1474.model.maxBoundY)) {
					if ((local1474.typeA & local8.backWallTypes) != 0) {
						local1474.model.draw(local1474.typeB, pitchsin, pitchcos, yawsin, yawcos, local1474.x - eyeX, local1474.plane - eyeY, local1474.z - eyeZ, local1474.bitset);
					} else if ((local1474.typeA & 0x300) != 0) {
						local253 = local1474.x - eyeX;
						local1144 = local1474.plane - eyeY;
						local1023 = local1474.z - eyeZ;
						local599 = local1474.typeB;
						if (local599 == 1 || local599 == 2) {
							local604 = -local253;
						} else {
							local604 = local253;
						}
						if (local599 == 2 || local599 == 3) {
							local609 = -local1023;
						} else {
							local609 = local1023;
						}
						if ((local1474.typeA & 0x100) != 0 && local609 >= local604) {
							local612 = local253 + DECO_TYPE1_OFFSET_X[local599];
							local621 = local1023 + DECO_TYPE1_OFFSET_Z[local599];
							local1474.model.draw(local599 * 512 + 256, pitchsin, pitchcos, yawsin, yawcos, local612, local1144, local621, local1474.bitset);
						}
						if ((local1474.typeA & 0x200) != 0 && local609 <= local604) {
							local612 = local253 + DECO_TYPE2_OFFSET_X[local599];
							local621 = local1023 + DECO_TYPE2_OFFSET_Z[local599];
							local1474.model.draw(local599 * 512 + 1280 & 0x7FF, pitchsin, pitchcos, yawsin, yawcos, local612, local1144, local621, local1474.bitset);
						}
					}
				}
				local963 = local8.wall;
				if (local963 != null) {
					if ((local963.typeB & local8.backWallTypes) != 0 && !this.isWallVisible(local26, local17, local20, local963.typeB)) {
						local963.modelB.draw(0, pitchsin, pitchcos, yawsin, yawcos, local963.x - eyeX, local963.plane - eyeY, local963.z - eyeZ, local963.bitset);
					}
					if ((local963.typeA & local8.backWallTypes) != 0 && !this.isWallVisible(local26, local17, local20, local963.typeA)) {
						local963.modelA.draw(0, pitchsin, pitchcos, yawsin, yawcos, local963.x - eyeX, local963.plane - eyeY, local963.z - eyeZ, local963.bitset);
					}
				}
			}
			@Pc(1735) Tile local1735;
			if (local23 < this.maxLevel - 1) {
				local1735 = this.levelTiles[local23 + 1][local17][local20];
				if (local1735 != null && local1735.isVisible) {
					tileQueue.pushNext(local1735);
				}
			}
			if (local17 < screenCenterX) {
				local1735 = local31[local17 + 1][local20];
				if (local1735 != null && local1735.isVisible) {
					tileQueue.pushNext(local1735);
				}
			}
			if (local20 < screenCenterY) {
				local1735 = local31[local17][local20 + 1];
				if (local1735 != null && local1735.isVisible) {
					tileQueue.pushNext(local1735);
				}
			}
			if (local17 > screenCenterX) {
				local1735 = local31[local17 - 1][local20];
				if (local1735 != null && local1735.isVisible) {
					tileQueue.pushNext(local1735);
				}
			}
			if (local20 > screenCenterY) {
				local1735 = local31[local17][local20 - 1];
				if (local1735 != null && local1735.isVisible) {
					tileQueue.pushNext(local1735);
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(Lclient!o;IIIIIII)V")
	private void drawTileUnderlay(@OriginalArg(0) TileUnderlay arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		@Pc(8) int local8;
		@Pc(9) int local9 = local8 = (arg6 << 7) - eyeX;
		@Pc(16) int local16;
		@Pc(17) int local17 = local16 = (arg7 << 7) - eyeZ;
		@Pc(22) int local22;
		@Pc(23) int local23 = local22 = local9 + 128;
		@Pc(28) int local28;
		@Pc(29) int local29 = local28 = local17 + 128;
		@Pc(40) int local40 = this.heightmap[arg1][arg6][arg7] - eyeY;
		@Pc(53) int local53 = this.heightmap[arg1][arg6 + 1][arg7] - eyeY;
		@Pc(68) int local68 = this.heightmap[arg1][arg6 + 1][arg7 + 1] - eyeY;
		@Pc(81) int local81 = this.heightmap[arg1][arg6][arg7 + 1] - eyeY;
		@Pc(91) int local91 = local17 * arg4 + local9 * arg5 >> 16;
		@Pc(101) int local101 = local17 * arg5 - local9 * arg4 >> 16;
		@Pc(103) int local103 = local91;
		@Pc(113) int local113 = local40 * arg3 - local101 * arg2 >> 16;
		@Pc(123) int local123 = local40 * arg2 + local101 * arg3 >> 16;
		@Pc(125) int local125 = local113;
		if (local123 < 50) {
			return;
		}
		local91 = local16 * arg4 + local23 * arg5 >> 16;
		@Pc(149) int local149 = local16 * arg5 - local23 * arg4 >> 16;
		local23 = local91;
		local91 = local53 * arg3 - local149 * arg2 >> 16;
		@Pc(171) int local171 = local53 * arg2 + local149 * arg3 >> 16;
		local53 = local91;
		if (local171 < 50) {
			return;
		}
		local91 = local29 * arg4 + local22 * arg5 >> 16;
		local29 = local29 * arg5 - local22 * arg4 >> 16;
		@Pc(199) int local199 = local91;
		local91 = local68 * arg3 - local29 * arg2 >> 16;
		local29 = local68 * arg2 + local29 * arg3 >> 16;
		local68 = local91;
		if (local29 < 50) {
			return;
		}
		local91 = local28 * arg4 + local8 * arg5 >> 16;
		@Pc(245) int local245 = local28 * arg5 - local8 * arg4 >> 16;
		@Pc(247) int local247 = local91;
		local91 = local81 * arg3 - local245 * arg2 >> 16;
		@Pc(267) int local267 = local81 * arg2 + local245 * arg3 >> 16;
		if (local267 < 50) {
			return;
		}
		@Pc(281) int local281 = Draw3D.centerX3D + (local103 << 9) / local123;
		@Pc(289) int local289 = Draw3D.centerY3D + (local125 << 9) / local123;
		@Pc(297) int local297 = Draw3D.centerX3D + (local23 << 9) / local171;
		@Pc(305) int local305 = Draw3D.centerY3D + (local53 << 9) / local171;
		@Pc(313) int local313 = Draw3D.centerX3D + (local199 << 9) / local29;
		@Pc(321) int local321 = Draw3D.centerY3D + (local68 << 9) / local29;
		@Pc(329) int local329 = Draw3D.centerX3D + (local247 << 9) / local267;
		@Pc(337) int local337 = Draw3D.centerY3D + (local91 << 9) / local267;
		Draw3D.alpha = 0;
		@Pc(476) int local476;
		if ((local313 - local329) * (local305 - local337) - (local321 - local337) * (local297 - local329) > 0) {
			Draw3D.clipX = false;
			if (local313 < 0 || local329 < 0 || local297 < 0 || local313 > Draw2D.boundX || local329 > Draw2D.boundX || local297 > Draw2D.boundX) {
				Draw3D.clipX = true;
			}
			if (checkClick && this.withinTriangle(clickX, clickZ, local321, local337, local305, local313, local329, local297)) {
				clickedTileX = arg6;
				clickedTileZ = arg7;
			}
			if (arg0.textureId == -1) {
				if (arg0.neColor != 12345678) {
					Draw3D.fillGouraudTriangle(local321, local337, local305, local313, local329, local297, arg0.neColor, arg0.nwColor, arg0.seColor);
				}
			} else if (lowMemory) {
				local476 = TEXTURE_HSL[arg0.textureId];
				Draw3D.fillGouraudTriangle(local321, local337, local305, local313, local329, local297, this.adjustHslLightness(arg0.neColor, local476), this.adjustHslLightness(arg0.nwColor, local476), this.adjustHslLightness(arg0.seColor, local476));
			} else if (arg0.flat) {
				Draw3D.fillTexturedTriangle(local321, local337, local305, local313, local329, local297, arg0.neColor, arg0.nwColor, arg0.seColor, local103, local23, local247, local125, local53, local91, local123, local171, local267, arg0.textureId);
			} else {
				Draw3D.fillTexturedTriangle(local321, local337, local305, local313, local329, local297, arg0.neColor, arg0.nwColor, arg0.seColor, local199, local247, local23, local68, local91, local53, local29, local267, local171, arg0.textureId);
			}
		}
		if ((local281 - local297) * (local337 - local305) - (local289 - local305) * (local329 - local297) <= 0) {
			return;
		}
		Draw3D.clipX = false;
		if (local281 < 0 || local297 < 0 || local329 < 0 || local281 > Draw2D.boundX || local297 > Draw2D.boundX || local329 > Draw2D.boundX) {
			Draw3D.clipX = true;
		}
		if (checkClick && this.withinTriangle(clickX, clickZ, local289, local305, local337, local281, local297, local329)) {
			clickedTileX = arg6;
			clickedTileZ = arg7;
		}
		if (arg0.textureId != -1) {
			if (!lowMemory) {
				Draw3D.fillTexturedTriangle(local289, local305, local337, local281, local297, local329, arg0.swColor, arg0.seColor, arg0.nwColor, local103, local23, local247, local125, local53, local91, local123, local171, local267, arg0.textureId);
				return;
			}
			local476 = TEXTURE_HSL[arg0.textureId];
			Draw3D.fillGouraudTriangle(local289, local305, local337, local281, local297, local329, this.adjustHslLightness(arg0.swColor, local476), this.adjustHslLightness(arg0.seColor, local476), this.adjustHslLightness(arg0.nwColor, local476));
		} else if (arg0.swColor != 12345678) {
			Draw3D.fillGouraudTriangle(local289, local305, local337, local281, local297, local329, arg0.swColor, arg0.seColor, arg0.nwColor);
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IILclient!i;IIIIZ)V")
	private void drawTileOverlay(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) TileOverlay arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(5) int local5 = arg2.vertexX.length;
		@Pc(16) int local16;
		@Pc(23) int local23;
		@Pc(30) int local30;
		@Pc(40) int local40;
		for (@Pc(7) int local7 = 0; local7 < local5; local7++) {
			local16 = arg2.vertexX[local7] - eyeX;
			local23 = arg2.vertexY[local7] - eyeY;
			local30 = arg2.vertexZ[local7] - eyeZ;
			local40 = local30 * arg0 + local16 * arg6 >> 16;
			@Pc(50) int local50 = local30 * arg6 - local16 * arg0 >> 16;
			@Pc(62) int local62 = local23 * arg4 - local50 * arg5 >> 16;
			@Pc(72) int local72 = local23 * arg5 + local50 * arg4 >> 16;
			if (local72 < 50) {
				return;
			}
			if (arg2.triangleTextureIds != null) {
				TileOverlay.vertexSceneX[local7] = local40;
				TileOverlay.vertexSceneY[local7] = local62;
				TileOverlay.vertexSceneZ[local7] = local72;
			}
			TileOverlay.tmpScreenX[local7] = Draw3D.centerX3D + (local40 << 9) / local72;
			TileOverlay.tmpScreenY[local7] = Draw3D.centerY3D + (local62 << 9) / local72;
		}
		Draw3D.alpha = 0;
		local5 = arg2.triangleColorA.length;
		for (local16 = 0; local16 < local5; local16++) {
			local23 = arg2.triangleColorA[local16];
			local30 = arg2.triangleColorB[local16];
			local40 = arg2.triangleColorC[local16];
			@Pc(149) int local149 = TileOverlay.tmpScreenX[local23];
			@Pc(153) int local153 = TileOverlay.tmpScreenX[local30];
			@Pc(157) int local157 = TileOverlay.tmpScreenX[local40];
			@Pc(161) int local161 = TileOverlay.tmpScreenY[local23];
			@Pc(165) int local165 = TileOverlay.tmpScreenY[local30];
			@Pc(169) int local169 = TileOverlay.tmpScreenY[local40];
			if ((local149 - local153) * (local169 - local165) - (local161 - local165) * (local157 - local153) > 0) {
				Draw3D.clipX = false;
				if (local149 < 0 || local153 < 0 || local157 < 0 || local149 > Draw2D.boundX || local153 > Draw2D.boundX || local157 > Draw2D.boundX) {
					Draw3D.clipX = true;
				}
				if (checkClick && this.withinTriangle(clickX, clickZ, local161, local165, local169, local149, local153, local157)) {
					clickedTileX = arg3;
					clickedTileZ = arg1;
				}
				if (arg2.triangleTextureIds == null || arg2.triangleTextureIds[local16] == -1) {
					if (arg2.triangleVertexA[local16] != 12345678) {
						Draw3D.fillGouraudTriangle(local161, local165, local169, local149, local153, local157, arg2.triangleVertexA[local16], arg2.triangleVertexB[local16], arg2.triangleVertexC[local16]);
					}
				} else if (lowMemory) {
					@Pc(373) int local373 = TEXTURE_HSL[arg2.triangleTextureIds[local16]];
					Draw3D.fillGouraudTriangle(local161, local165, local169, local149, local153, local157, this.adjustHslLightness(arg2.triangleVertexA[local16], local373), this.adjustHslLightness(arg2.triangleVertexB[local16], local373), this.adjustHslLightness(arg2.triangleVertexC[local16], local373));
				} else if (arg2.flat) {
					Draw3D.fillTexturedTriangle(local161, local165, local169, local149, local153, local157, arg2.triangleVertexA[local16], arg2.triangleVertexB[local16], arg2.triangleVertexC[local16], TileOverlay.vertexSceneX[0], TileOverlay.vertexSceneX[1], TileOverlay.vertexSceneX[3], TileOverlay.vertexSceneY[0], TileOverlay.vertexSceneY[1], TileOverlay.vertexSceneY[3], TileOverlay.vertexSceneZ[0], TileOverlay.vertexSceneZ[1], TileOverlay.vertexSceneZ[3], arg2.triangleTextureIds[local16]);
				} else {
					Draw3D.fillTexturedTriangle(local161, local165, local169, local149, local153, local157, arg2.triangleVertexA[local16], arg2.triangleVertexB[local16], arg2.triangleVertexC[local16], TileOverlay.vertexSceneX[local23], TileOverlay.vertexSceneX[local30], TileOverlay.vertexSceneX[local40], TileOverlay.vertexSceneY[local23], TileOverlay.vertexSceneY[local30], TileOverlay.vertexSceneY[local40], TileOverlay.vertexSceneZ[local23], TileOverlay.vertexSceneZ[local30], TileOverlay.vertexSceneZ[local40], arg2.triangleTextureIds[local16]);
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "f", descriptor = "(III)I")
	private int adjustHslLightness(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(3) int local3 = 127 - arg0;
		arg0 = local3 * (arg1 & 0x7F) / 160;
		if (arg0 < 2) {
			arg0 = 2;
		} else if (arg0 > 126) {
			arg0 = 126;
		}
		return (arg1 & 0xFF80) + arg0;
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIIIII)Z")
	private boolean withinTriangle(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		if (arg1 < arg2 && arg1 < arg3 && arg1 < arg4) {
			return false;
		} else if (arg1 > arg2 && arg1 > arg3 && arg1 > arg4) {
			return false;
		} else if (arg0 < arg5 && arg0 < arg6 && arg0 < arg7) {
			return false;
		} else if (arg0 > arg5 && arg0 > arg6 && arg0 > arg7) {
			return false;
		} else {
			@Pc(59) int local59 = (arg1 - arg2) * (arg6 - arg5) - (arg0 - arg5) * (arg3 - arg2);
			@Pc(75) int local75 = (arg1 - arg4) * (arg5 - arg7) - (arg0 - arg7) * (arg2 - arg4);
			@Pc(91) int local91 = (arg1 - arg3) * (arg7 - arg6) - (arg0 - arg6) * (arg4 - arg3);
			return local59 * local91 > 0 && local91 * local75 > 0;
		}
	}

	@OriginalMember(owner = "client!r", name = "b", descriptor = "(Z)V")
	private void updateOccluders() {
		activeOccluderCount = 0;

		@Pc(5) int count = levelOccluderCount[tileUpdateCount];
		@Pc(9) Occluder[] occluders = levelOccluders[tileUpdateCount];

		for (@Pc(13) int i = 0; i < count; i++) {
			@Pc(19) Occluder occluder = occluders[i];

			if (occluder.type == Occluder.TYPE_WALL_X) {
				int distanceMinTileX = occluder.minTileX + 25 - screenCenterX;
				if (distanceMinTileX < 0 || distanceMinTileX > 50) {
					continue;
				}

				int minZ = occluder.minTileZ + 25 - screenCenterY;
				if (minZ < 0) {
					minZ = 0;
				}

				int maxZ = occluder.maxTileZ + 25 - screenCenterY;
				if (maxZ > 50) {
					maxZ = 50;
				}

				boolean state = false;
				while (minZ <= maxZ) {
					if (visibilityMap[distanceMinTileX][minZ++]) {
						state = true;
						break;
					}
				}

				if (!state) {
					continue;
				}

				int deltaMinX = eyeX - occluder.minX;
				if (deltaMinX > 32) {
					occluder.mode = 1;
				} else {
					if (deltaMinX >= -32) {
						continue;
					}

					occluder.mode = 2;
					deltaMinX = -deltaMinX;
				}

				occluder.minDeltaZ = (occluder.minZ - eyeZ << 8) / deltaMinX;
				occluder.maxDeltaZ = (occluder.maxZ - eyeZ << 8) / deltaMinX;
				occluder.minDeltaY = (occluder.minY - eyeY << 8) / deltaMinX;
				occluder.maxDeltaY = (occluder.maxY - eyeY << 8) / deltaMinX;
				activeOccluders[activeOccluderCount++] = occluder;
			} else if (occluder.type == Occluder.TYPE_WALL_Z) {
				int distanceMinTileZ = occluder.minTileZ + 25 - screenCenterY;
				if (distanceMinTileZ < 0 || distanceMinTileZ > 50) {
					continue;
				}

				int distanceMinTileX = occluder.minTileX + 25 - screenCenterX;
				if (distanceMinTileX < 0) {
					distanceMinTileX = 0;
				}

				int distanceMaxTileX = occluder.maxTileX + 25 - screenCenterX;
				if (distanceMaxTileX > 50) {
					distanceMaxTileX = 50;
				}

				boolean state = false;
				while (distanceMinTileX <= distanceMaxTileX) {
					if (visibilityMap[distanceMinTileX++][distanceMinTileZ]) {
						state = true;
						break;
					}
				}

				if (!state) {
					continue;
				}

				int deltaMinZ = eyeZ - occluder.minZ;
				if (deltaMinZ > 32) {
					occluder.mode = 3;
				} else {
					if (deltaMinZ >= -32) {
						continue;
					}

					occluder.mode = 4;
					deltaMinZ = -deltaMinZ;
				}

				occluder.minDeltaX = (occluder.minX - eyeX << 8) / deltaMinZ;
				occluder.maxDeltaX = (occluder.maxX - eyeX << 8) / deltaMinZ;
				occluder.minDeltaY = (occluder.minY - eyeY << 8) / deltaMinZ;
				occluder.maxDeltaY = (occluder.maxY - eyeY << 8) / deltaMinZ;
				activeOccluders[activeOccluderCount++] = occluder;
			} else if (occluder.type == Occluder.TYPE_GROUND) {
				int deltaMaxY = occluder.minY - eyeY;
				if (deltaMaxY <= 128) {
					continue;
				}

				int deltaMinTileZ = occluder.minTileZ + 25 - screenCenterY;
				if (deltaMinTileZ < 0) {
					deltaMinTileZ = 0;
				}

				int deltaMaxTileZ = occluder.maxTileZ + 25 - screenCenterY;
				if (deltaMaxTileZ > 50) {
					deltaMaxTileZ = 50;
				}

				if (deltaMinTileZ > deltaMaxTileZ) {
					continue;
				}

				@Pc(330) int deltaMinTileX = occluder.minTileX + 25 - screenCenterX;
				if (deltaMinTileX < 0) {
					deltaMinTileX = 0;
				}

				int deltaMaxTileX = occluder.maxTileX + 25 - screenCenterX;
				if (deltaMaxTileX > 50) {
					deltaMaxTileX = 50;
				}

				@Pc(348) boolean state = false;

				find_visible_tile:
				for (@Pc(350) int x = deltaMinTileX; x <= deltaMaxTileX; x++) {
					for (@Pc(354) int z = deltaMinTileZ; z <= deltaMaxTileZ; z++) {
						if (visibilityMap[x][z]) {
							state = true;
							break find_visible_tile;
						}
					}
				}

				if (state) {
					occluder.mode = 5;
					occluder.minDeltaX = (occluder.minX - eyeX << 8) / deltaMaxY;
					occluder.maxDeltaX = (occluder.maxX - eyeX << 8) / deltaMaxY;
					occluder.minDeltaZ = (occluder.minZ - eyeZ << 8) / deltaMaxY;
					occluder.maxDeltaZ = (occluder.maxZ - eyeZ << 8) / deltaMaxY;
					activeOccluders[activeOccluderCount++] = occluder;
				}
			}
		}
	}

	@OriginalMember(owner = "client!r", name = "g", descriptor = "(III)Z")
	private boolean isTileVisible(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
		@Pc(8) int cycle = this.levelTileCycles[plane][x][z];
		if (cycle == -activeLevel) {
			return false;
		}

		if (cycle == activeLevel) {
			return true;
		}
		
		@Pc(23) int sx = x << 7;
		@Pc(27) int sz = z << 7;

		if (this.isOccluded(sx + 1, this.heightmap[plane][x][z], sz + 1) && this.isOccluded(sx + 128 - 1, this.heightmap[plane][x + 1][z], sz + 1) && this.isOccluded(sx + 128 - 1, this.heightmap[plane][x + 1][z + 1], sz + 128 - 1) && this.isOccluded(sx + 1, this.heightmap[plane][x][z + 1], sz + 128 - 1)) {
			this.levelTileCycles[plane][x][z] = activeLevel;
			return true;
		} else {
			this.levelTileCycles[plane][x][z] = -activeLevel;
			return false;
		}
	}

	@OriginalMember(owner = "client!r", name = "i", descriptor = "(IIII)Z")
	private boolean isWallVisible(@OriginalArg(0) int plane, @OriginalArg(1) int tileX, @OriginalArg(2) int tileZ, @OriginalArg(3) int type) {
		if (!this.isTileVisible(plane, tileX, tileZ)) {
			return false;
		}

		@Pc(11) int sceneX = tileX << 7;
		@Pc(15) int sceneZ = tileZ << 7;
		@Pc(26) int sceneY = this.heightmap[plane][tileX][tileZ] - 1;

		@Pc(30) int y0 = sceneY - 120;
		@Pc(34) int y1 = sceneY - 230;
		@Pc(38) int y2 = sceneY - 238;

		if (type < 16) {
			if (type == 1) {
				if (sceneX > eyeX) {
					if (!this.isOccluded(sceneX, sceneY, sceneZ)) {
						return false;
					}

					if (!this.isOccluded(sceneX, sceneY, sceneZ + 128)) {
						return false;
					}
				}

				if (plane > 0) {
					if (!this.isOccluded(sceneX, y0, sceneZ)) {
						return false;
					}

					if (!this.isOccluded(sceneX, y0, sceneZ + 128)) {
						return false;
					}
				}

				if (!this.isOccluded(sceneX, y1, sceneZ)) {
					return false;
				}

				if (!this.isOccluded(sceneX, y1, sceneZ + 128)) {
					return false;
				}

				return true;
			}

			if (type == 2) {
				if (sceneZ < eyeZ) {
					if (!this.isOccluded(sceneX, sceneY, sceneZ + 128)) {
						return false;
					}

					if (!this.isOccluded(sceneX + 128, sceneY, sceneZ + 128)) {
						return false;
					}
				}

				if (plane > 0) {
					if (!this.isOccluded(sceneX, y0, sceneZ + 128)) {
						return false;
					}

					if (!this.isOccluded(sceneX + 128, y0, sceneZ + 128)) {
						return false;
					}
				}

				if (!this.isOccluded(sceneX, y1, sceneZ + 128)) {
					return false;
				}

				if (!this.isOccluded(sceneX + 128, y1, sceneZ + 128)) {
					return false;
				}

				return true;
			}
			if (type == 4) {
				if (sceneX < eyeX) {
					if (!this.isOccluded(sceneX + 128, sceneY, sceneZ)) {
						return false;
					}

					if (!this.isOccluded(sceneX + 128, sceneY, sceneZ + 128)) {
						return false;
					}
				}

				if (plane > 0) {
					if (!this.isOccluded(sceneX + 128, y0, sceneZ)) {
						return false;
					}

					if (!this.isOccluded(sceneX + 128, y0, sceneZ + 128)) {
						return false;
					}
				}

				if (!this.isOccluded(sceneX + 128, y1, sceneZ)) {
					return false;
				}

				if (!this.isOccluded(sceneX + 128, y1, sceneZ + 128)) {
					return false;
				}

				return true;
			}

			if (type == 8) {
				if (sceneZ > eyeZ) {
					if (!this.isOccluded(sceneX, sceneY, sceneZ)) {
						return false;
					}

					if (!this.isOccluded(sceneX + 128, sceneY, sceneZ)) {
						return false;
					}
				}

				if (plane > 0) {
					if (!this.isOccluded(sceneX, y0, sceneZ)) {
						return false;
					}

					if (!this.isOccluded(sceneX + 128, y0, sceneZ)) {
						return false;
					}
				}

				if (!this.isOccluded(sceneX, y1, sceneZ)) {
					return false;
				}

				if (!this.isOccluded(sceneX + 128, y1, sceneZ)) {
					return false;
				}

				return true;
			}
		}

		if (!this.isOccluded(sceneX + 64, y2, sceneZ + 64)) {
			return false;
		} else if (type == 16) {
			return this.isOccluded(sceneX, y1, sceneZ + 128);
		} else if (type == 32) {
			return this.isOccluded(sceneX + 128, y1, sceneZ + 128);
		} else if (type == 64) {
			return this.isOccluded(sceneX + 128, y1, sceneZ);
		} else if (type == 128) {
			return this.isOccluded(sceneX, y1, sceneZ);
		} else {
			System.out.println("Warning unsupported wall type");
			return true;
		}
	}

	@OriginalMember(owner = "client!r", name = "j", descriptor = "(IIII)Z")
	private boolean isOccluded(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (this.isTileVisible(arg0, arg1, arg2)) {
			@Pc(11) int local11 = arg1 << 7;
			@Pc(15) int local15 = arg2 << 7;
			return this.isOccluded(local11 + 1, this.heightmap[arg0][arg1][arg2] - arg3, local15 + 1) && this.isOccluded(local11 + 128 - 1, this.heightmap[arg0][arg1 + 1][arg2] - arg3, local15 + 1) && this.isOccluded(local11 + 128 - 1, this.heightmap[arg0][arg1 + 1][arg2 + 1] - arg3, local15 + 128 - 1) && this.isOccluded(local11 + 1, this.heightmap[arg0][arg1][arg2 + 1] - arg3, local15 + 128 - 1);
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!r", name = "a", descriptor = "(IIIIII)Z")
	private boolean isAreaOccluded(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(19) int local19;
		@Pc(23) int local23;
		if (arg1 != arg2 || arg3 != arg4) {
			for (local19 = arg1; local19 <= arg2; local19++) {
				for (local23 = arg3; local23 <= arg4; local23++) {
					if (this.levelTileCycles[arg0][local19][local23] == -activeLevel) {
						return false;
					}
				}
			}
			local23 = (arg1 << 7) + 1;
			@Pc(160) int local160 = (arg3 << 7) + 2;
			@Pc(171) int local171 = this.heightmap[arg0][arg1][arg3] - arg5;
			if (!this.isOccluded(local23, local171, local160)) {
				return false;
			}
			@Pc(185) int local185 = (arg2 << 7) - 1;
			if (!this.isOccluded(local185, local171, local160)) {
				return false;
			}
			@Pc(199) int local199 = (arg4 << 7) - 1;
			if (!this.isOccluded(local23, local171, local199)) {
				return false;
			} else if (this.isOccluded(local185, local171, local199)) {
				return true;
			} else {
				return false;
			}
		} else if (this.isTileVisible(arg0, arg1, arg3)) {
			local19 = arg1 << 7;
			local23 = arg3 << 7;
			return this.isOccluded(local19 + 1, this.heightmap[arg0][arg1][arg3] - arg5, local23 + 1) && this.isOccluded(local19 + 128 - 1, this.heightmap[arg0][arg1 + 1][arg3] - arg5, local23 + 1) && this.isOccluded(local19 + 128 - 1, this.heightmap[arg0][arg1 + 1][arg3 + 1] - arg5, local23 + 128 - 1) && this.isOccluded(local19 + 1, this.heightmap[arg0][arg1][arg3 + 1] - arg5, local23 + 128 - 1);
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!r", name = "h", descriptor = "(III)Z")
	private boolean isOccluded(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(1) int local1 = 0; local1 < activeOccluderCount; local1++) {
			@Pc(7) Occluder local7 = activeOccluders[local1];
			@Pc(16) int local16;
			@Pc(28) int local28;
			@Pc(38) int local38;
			@Pc(48) int local48;
			@Pc(58) int local58;
			if (local7.mode == 1) {
				local16 = local7.minX - arg0;
				if (local16 > 0) {
					local28 = local7.minZ + (local7.minDeltaZ * local16 >> 8);
					local38 = local7.maxZ + (local7.maxDeltaZ * local16 >> 8);
					local48 = local7.minY + (local7.minDeltaY * local16 >> 8);
					local58 = local7.maxY + (local7.maxDeltaY * local16 >> 8);
					if (arg2 >= local28 && arg2 <= local38 && arg1 >= local48 && arg1 <= local58) {
						return true;
					}
				}
			} else if (local7.mode == 2) {
				local16 = arg0 - local7.minX;
				if (local16 > 0) {
					local28 = local7.minZ + (local7.minDeltaZ * local16 >> 8);
					local38 = local7.maxZ + (local7.maxDeltaZ * local16 >> 8);
					local48 = local7.minY + (local7.minDeltaY * local16 >> 8);
					local58 = local7.maxY + (local7.maxDeltaY * local16 >> 8);
					if (arg2 >= local28 && arg2 <= local38 && arg1 >= local48 && arg1 <= local58) {
						return true;
					}
				}
			} else if (local7.mode == 3) {
				local16 = local7.minZ - arg2;
				if (local16 > 0) {
					local28 = local7.minX + (local7.minDeltaX * local16 >> 8);
					local38 = local7.maxX + (local7.maxDeltaX * local16 >> 8);
					local48 = local7.minY + (local7.minDeltaY * local16 >> 8);
					local58 = local7.maxY + (local7.maxDeltaY * local16 >> 8);
					if (arg0 >= local28 && arg0 <= local38 && arg1 >= local48 && arg1 <= local58) {
						return true;
					}
				}
			} else if (local7.mode == 4) {
				local16 = arg2 - local7.minZ;
				if (local16 > 0) {
					local28 = local7.minX + (local7.minDeltaX * local16 >> 8);
					local38 = local7.maxX + (local7.maxDeltaX * local16 >> 8);
					local48 = local7.minY + (local7.minDeltaY * local16 >> 8);
					local58 = local7.maxY + (local7.maxDeltaY * local16 >> 8);
					if (arg0 >= local28 && arg0 <= local38 && arg1 >= local48 && arg1 <= local58) {
						return true;
					}
				}
			} else if (local7.mode == 5) {
				local16 = arg1 - local7.minY;
				if (local16 > 0) {
					local28 = local7.minX + (local7.minDeltaX * local16 >> 8);
					local38 = local7.maxX + (local7.maxDeltaX * local16 >> 8);
					local48 = local7.minZ + (local7.minDeltaZ * local16 >> 8);
					local58 = local7.maxZ + (local7.maxDeltaZ * local16 >> 8);
					if (arg0 >= local28 && arg0 <= local38 && arg2 >= local48 && arg2 <= local58) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
