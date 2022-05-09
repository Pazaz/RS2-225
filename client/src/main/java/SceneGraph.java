import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!c")
public final class SceneGraph {

	@OriginalMember(owner = "client!c", name = "e", descriptor = "I")
	public static int anInt81;

	@OriginalMember(owner = "client!c", name = "f", descriptor = "Z")
	private static boolean aBoolean24;

	@OriginalMember(owner = "client!c", name = "a", descriptor = "Z")
	private static final boolean aBoolean21 = true;

	@OriginalMember(owner = "client!c", name = "d", descriptor = "Z")
	public static boolean aBoolean23 = true;

	@OriginalMember(owner = "client!c", name = "w", descriptor = "[I")
	private static final int[] anIntArray18 = new int[] { 1, 2, 4, 8 };

	@OriginalMember(owner = "client!c", name = "x", descriptor = "[I")
	private static final int[] anIntArray19 = new int[] { 16, 32, 64, 128 };

	@OriginalMember(owner = "client!c", name = "y", descriptor = "[I")
	private static final int[] anIntArray20 = new int[] { 1, 0, -1, 0 };

	@OriginalMember(owner = "client!c", name = "z", descriptor = "[I")
	private static final int[] anIntArray21 = new int[] { 0, -1, 0, 1 };

	@OriginalMember(owner = "client!c", name = "A", descriptor = "I")
	private static int anInt84 = (int) (Math.random() * 17.0D) - 8;

	@OriginalMember(owner = "client!c", name = "B", descriptor = "I")
	private static int anInt85 = (int) (Math.random() * 33.0D) - 16;

	@OriginalMember(owner = "client!c", name = "b", descriptor = "Z")
	private final boolean aBoolean22 = true;

	@OriginalMember(owner = "client!c", name = "c", descriptor = "I")
	private int anInt80 = 8;

	@OriginalMember(owner = "client!c", name = "g", descriptor = "I")
	private final int anInt82;

	@OriginalMember(owner = "client!c", name = "h", descriptor = "I")
	private final int anInt83;

	@OriginalMember(owner = "client!c", name = "i", descriptor = "[[[I")
	private final int[][][] anIntArrayArrayArray1;

	@OriginalMember(owner = "client!c", name = "j", descriptor = "[[[B")
	private final byte[][][] aByteArrayArrayArray1;

	@OriginalMember(owner = "client!c", name = "k", descriptor = "[[[B")
	private final byte[][][] aByteArrayArrayArray2;

	@OriginalMember(owner = "client!c", name = "l", descriptor = "[[[B")
	private final byte[][][] aByteArrayArrayArray3;

	@OriginalMember(owner = "client!c", name = "m", descriptor = "[[[B")
	private final byte[][][] aByteArrayArrayArray4;

	@OriginalMember(owner = "client!c", name = "n", descriptor = "[[[B")
	private final byte[][][] aByteArrayArrayArray5;

	@OriginalMember(owner = "client!c", name = "v", descriptor = "[[[I")
	private final int[][][] anIntArrayArrayArray2;

	@OriginalMember(owner = "client!c", name = "o", descriptor = "[[[B")
	private final byte[][][] aByteArrayArrayArray6;

	@OriginalMember(owner = "client!c", name = "p", descriptor = "[[I")
	private final int[][] anIntArrayArray1;

	@OriginalMember(owner = "client!c", name = "q", descriptor = "[I")
	private final int[] anIntArray13;

	@OriginalMember(owner = "client!c", name = "r", descriptor = "[I")
	private final int[] anIntArray14;

	@OriginalMember(owner = "client!c", name = "s", descriptor = "[I")
	private final int[] anIntArray15;

	@OriginalMember(owner = "client!c", name = "t", descriptor = "[I")
	private final int[] anIntArray16;

	@OriginalMember(owner = "client!c", name = "u", descriptor = "[I")
	private final int[] anIntArray17;

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
		@Pc(11) int local11 = 65536 - Draw3D.anIntArray138[arg2 * 1024 / arg3] >> 1;
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
		if (!local60.aBoolean9) {
			local72 += Integer.MIN_VALUE;
		}
		@Pc(86) byte local86 = (byte) ((arg4 << 6) + arg8);
		@Pc(99) Model local99;
		if (arg8 == 22) {
			local99 = local60.getModel(22, arg4, local15, local25, local37, local47, -1);
			arg9.addGroundDecoration(local99, arg0, local72, arg3, arg6, local86, local57);
			if (local60.aBoolean7 && local60.aBoolean9) {
				arg2.setBlocoked(arg3, arg0);
			}
			if (local60.anInt45 != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 3, SeqType.instances[local60.anInt45], arg3, arg0));
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
					local174 = local60.anInt44;
					local177 = local60.anInt43;
				} else {
					local174 = local60.anInt43;
					local177 = local60.anInt44;
				}
				arg9.addLocation(local57, arg6, null, local72, arg3, arg0, local174, local86, local99, local161, local177);
			}
			if (local60.aBoolean7) {
				arg2.setLoc(arg4, local60.anInt44, local60.anInt43, arg0, arg3, local60.aBoolean8);
			}
			if (local60.anInt45 != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 2, SeqType.instances[local60.anInt45], arg3, arg0));
			}
		} else if (arg8 >= 12) {
			local99 = local60.getModel(arg8, arg4, local15, local25, local37, local47, -1);
			arg9.addLocation(local57, arg6, null, local72, arg3, arg0, 1, local86, local99, 0, 1);
			if (local60.aBoolean7) {
				arg2.setLoc(arg4, local60.anInt44, local60.anInt43, arg0, arg3, local60.aBoolean8);
			}
			if (local60.anInt45 != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 2, SeqType.instances[local60.anInt45], arg3, arg0));
			}
		} else if (arg8 == 0) {
			local99 = local60.getModel(0, arg4, local15, local25, local37, local47, -1);
			arg9.addWall(0, local57, arg6, anIntArray18[arg4], local99, null, arg0, local72, arg3, local86);
			if (local60.aBoolean7) {
				arg2.setWall(arg4, arg3, arg0, local60.aBoolean8, arg8);
			}
			if (local60.anInt45 != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 0, SeqType.instances[local60.anInt45], arg3, arg0));
			}
		} else if (arg8 == 1) {
			local99 = local60.getModel(1, arg4, local15, local25, local37, local47, -1);
			arg9.addWall(0, local57, arg6, anIntArray19[arg4], local99, null, arg0, local72, arg3, local86);
			if (local60.aBoolean7) {
				arg2.setWall(arg4, arg3, arg0, local60.aBoolean8, arg8);
			}
			if (local60.anInt45 != -1) {
				arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 0, SeqType.instances[local60.anInt45], arg3, arg0));
			}
		} else {
			@Pc(430) int local430;
			@Pc(452) Model local452;
			if (arg8 == 2) {
				local430 = arg4 + 1 & 0x3;
				@Pc(442) Model local442 = local60.getModel(2, arg4 + 4, local15, local25, local37, local47, -1);
				local452 = local60.getModel(2, local430, local15, local25, local37, local47, -1);
				arg9.addWall(anIntArray18[local430], local57, arg6, anIntArray18[arg4], local442, local452, arg0, local72, arg3, local86);
				if (local60.aBoolean7) {
					arg2.setWall(arg4, arg3, arg0, local60.aBoolean8, arg8);
				}
				if (local60.anInt45 != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 0, SeqType.instances[local60.anInt45], arg3, arg0));
				}
			} else if (arg8 == 3) {
				local99 = local60.getModel(3, arg4, local15, local25, local37, local47, -1);
				arg9.addWall(0, local57, arg6, anIntArray19[arg4], local99, null, arg0, local72, arg3, local86);
				if (local60.aBoolean7) {
					arg2.setWall(arg4, arg3, arg0, local60.aBoolean8, arg8);
				}
				if (local60.anInt45 != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 0, SeqType.instances[local60.anInt45], arg3, arg0));
				}
			} else if (arg8 == 9) {
				local99 = local60.getModel(arg8, arg4, local15, local25, local37, local47, -1);
				arg9.addLocation(local57, arg6, null, local72, arg3, arg0, 1, local86, local99, 0, 1);
				if (local60.aBoolean7) {
					arg2.setLoc(arg4, local60.anInt44, local60.anInt43, arg0, arg3, local60.aBoolean8);
				}
				if (local60.anInt45 != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 2, SeqType.instances[local60.anInt45], arg3, arg0));
				}
			} else if (arg8 == 4) {
				local99 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, 0, local72, arg4 * 512, anIntArray18[arg4], 0, arg0, local99, local86, arg6);
				if (local60.anInt45 != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anInt45], arg3, arg0));
				}
			} else if (arg8 == 5) {
				local430 = 16;
				local174 = arg9.getWallBitset(arg6, arg0, arg3);
				if (local174 > 0) {
					local430 = LocType.get(local174 >> 14 & 0x7FFF).anInt46;
				}
				local452 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, anIntArray21[arg4] * local430, local72, arg4 * 512, anIntArray18[arg4], anIntArray20[arg4] * local430, arg0, local452, local86, arg6);
				if (local60.anInt45 != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anInt45], arg3, arg0));
				}
			} else if (arg8 == 6) {
				local99 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, 0, local72, arg4, 256, 0, arg0, local99, local86, arg6);
				if (local60.anInt45 != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anInt45], arg3, arg0));
				}
			} else if (arg8 == 7) {
				local99 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, 0, local72, arg4, 512, 0, arg0, local99, local86, arg6);
				if (local60.anInt45 != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anInt45], arg3, arg0));
				}
			} else if (arg8 == 8) {
				local99 = local60.getModel(4, 0, local15, local25, local37, local47, -1);
				arg9.addWallDecoration(local57, arg3, 0, local72, arg4, 768, 0, arg0, local99, local86, arg6);
				if (local60.anInt45 != -1) {
					arg1.pushNext(new LocEntity(true, arg7, arg6, 0, 1, SeqType.instances[local60.anInt45], arg3, arg0));
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "<init>", descriptor = "(I[[[BI[[[II)V")
	public SceneGraph(@OriginalArg(0) int arg0, @OriginalArg(1) byte[][][] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int[][][] arg3, @OriginalArg(4) int arg4) {
		this.anInt82 = arg2;
		this.anInt83 = arg0;
		this.anIntArrayArrayArray1 = arg3;
		this.aByteArrayArrayArray1 = arg1;
		this.aByteArrayArrayArray2 = new byte[4][this.anInt82][this.anInt83];
		this.aByteArrayArrayArray3 = new byte[4][this.anInt82][this.anInt83];
		this.aByteArrayArrayArray4 = new byte[4][this.anInt82][this.anInt83];
		this.aByteArrayArrayArray5 = new byte[4][this.anInt82][this.anInt83];
		this.anIntArrayArrayArray2 = new int[4][this.anInt82 + 1][this.anInt83 + 1];
		this.aByteArrayArrayArray6 = new byte[4][this.anInt82 + 1][this.anInt83 + 1];
		this.anIntArrayArray1 = new int[this.anInt82 + 1][this.anInt83 + 1];
		this.anIntArray13 = new int[this.anInt83];
		this.anIntArray14 = new int[this.anInt83];
		if (arg4 != -35388) {
			this.anInt80 = 28;
		}
		this.anIntArray15 = new int[this.anInt83];
		this.anIntArray16 = new int[this.anInt83];
		this.anIntArray17 = new int[this.anInt83];
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(IIIII)V")
	public final void clearLandscape(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(3) byte local3 = 0;
		for (@Pc(11) int local11 = 0; local11 < FloType.anInt402; local11++) {
			if (FloType.aFloTypeArray1[local11].aString19.equalsIgnoreCase("water")) {
				local3 = (byte) (local11 + 1);
				break;
			}
		}
		for (@Pc(33) int local33 = arg1; local33 < arg1 + 64; local33++) {
			for (@Pc(37) int local37 = arg0; local37 < arg0 + 64; local37++) {
				if (local37 >= 0 && local37 < this.anInt82 && local33 >= 0 && local33 < this.anInt83) {
					this.aByteArrayArrayArray3[0][local37][local33] = local3;
					for (@Pc(62) int local62 = 0; local62 < 4; local62++) {
						this.anIntArrayArrayArray1[local62][local37][local33] = 0;
						this.aByteArrayArrayArray1[local62][local37][local33] = 0;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "([BIIIII)V")
	public final void readLandscape(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(7) Buffer local7 = new Buffer(363, arg0);
		for (@Pc(20) int local20 = 0; local20 < 4; local20++) {
			for (@Pc(24) int local24 = 0; local24 < 64; local24++) {
				for (@Pc(28) int local28 = 0; local28 < 64; local28++) {
					@Pc(34) int local34 = local24 + arg3;
					@Pc(38) int local38 = local28 + arg2;
					@Pc(60) int local60;
					if (local34 >= 0 && local34 < 104 && local38 >= 0 && local38 < 104) {
						this.aByteArrayArrayArray1[local20][local34][local38] = 0;
						while (true) {
							local60 = local7.g1();
							if (local60 == 0) {
								if (local20 == 0) {
									this.anIntArrayArrayArray1[0][local34][local38] = -getPerlinNoise(local34 + arg1 + 932731, local38 + 556238 + arg4) * 8;
								} else {
									this.anIntArrayArrayArray1[local20][local34][local38] = this.anIntArrayArrayArray1[local20 - 1][local34][local38] - 240;
								}
								break;
							}
							if (local60 == 1) {
								@Pc(116) int local116 = local7.g1();
								if (local116 == 1) {
									local116 = 0;
								}
								if (local20 == 0) {
									this.anIntArrayArrayArray1[0][local34][local38] = -local116 * 8;
								} else {
									this.anIntArrayArrayArray1[local20][local34][local38] = this.anIntArrayArrayArray1[local20 - 1][local34][local38] - local116 * 8;
								}
								break;
							}
							if (local60 <= 49) {
								this.aByteArrayArrayArray3[local20][local34][local38] = local7.g1s();
								this.aByteArrayArrayArray4[local20][local34][local38] = (byte) ((local60 - 2) / 4);
								this.aByteArrayArrayArray5[local20][local34][local38] = (byte) (local60 - 2 & 0x3);
							} else if (local60 <= 81) {
								this.aByteArrayArrayArray1[local20][local34][local38] = (byte) (local60 - 49);
							} else {
								this.aByteArrayArrayArray2[local20][local34][local38] = (byte) (local60 - 81);
							}
						}
					} else {
						while (true) {
							local60 = local7.g1();
							if (local60 == 0) {
								break;
							}
							if (local60 == 1) {
								local7.g1();
								break;
							}
							if (local60 <= 49) {
								local7.g1();
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "([BLclient!r;[Lclient!ec;Lclient!ob;ZII)V")
	public final void readLocs(@OriginalArg(0) byte[] arg0, @OriginalArg(1) MapSquare arg1, @OriginalArg(2) CollisionMap[] arg2, @OriginalArg(3) LinkedList arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(7) Buffer local7 = new Buffer(363, arg0);
		@Pc(19) int local19 = -1;
		while (true) {
			@Pc(22) int local22 = local7.gSmart1or2();
			if (local22 == 0) {
				return;
			}
			local19 += local22;
			@Pc(30) int local30 = 0;
			while (true) {
				@Pc(33) int local33 = local7.gSmart1or2();
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
					if ((this.aByteArrayArrayArray1[1][local70][local74] & 0x2) == 2) {
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
		if (aBoolean23) {
			if ((this.aByteArrayArrayArray1[arg1][arg8][arg2] & 0x10) != 0) {
				return;
			}
			if (this.getRenderLevel(arg1, arg8, arg2) != anInt81) {
				return;
			}
		}
		@Pc(36) int local36 = this.anIntArrayArrayArray1[arg1][arg8][arg2];
		@Pc(47) int local47 = this.anIntArrayArrayArray1[arg1][arg8 + 1][arg2];
		@Pc(60) int local60 = this.anIntArrayArrayArray1[arg1][arg8 + 1][arg2 + 1];
		@Pc(71) int local71 = this.anIntArrayArrayArray1[arg1][arg8][arg2 + 1];
		@Pc(81) int local81 = local36 + local47 + local60 + local71 >> 2;
		@Pc(84) LocType local84 = LocType.get(arg7);
		@Pc(96) int local96 = arg8 + (arg2 << 7) + (arg7 << 14) + 1073741824;
		if (!local84.aBoolean9) {
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
						local209 = local84.anInt44;
						local212 = local84.anInt43;
					} else {
						local209 = local84.anInt43;
						local212 = local84.anInt44;
					}
					if (arg5.addLocation(local81, arg1, null, local96, arg2, arg8, local209, local110, local132, local196, local212) && local84.aBoolean15) {
						for (@Pc(240) int local240 = 0; local240 <= local209; local240++) {
							for (@Pc(244) int local244 = 0; local244 <= local212; local244++) {
								@Pc(251) int local251 = local132.anInt367 / 4;
								if (local251 > 30) {
									local251 = 30;
								}
								if (local251 > this.aByteArrayArrayArray6[arg1][arg8 + local240][arg2 + local244]) {
									this.aByteArrayArrayArray6[arg1][arg8 + local240][arg2 + local244] = (byte) local251;
								}
							}
						}
					}
				}
				if (local84.aBoolean7 && arg0 != null) {
					arg0.setLoc(arg3, local84.anInt44, local84.anInt43, arg8, arg2, local84.aBoolean8);
				}
				if (local84.anInt45 != -1) {
					arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 2, SeqType.instances[local84.anInt45], arg2, arg8));
				}
			} else if (arg4 >= 12) {
				local132 = local84.getModel(arg4, arg3, local36, local47, local60, local71, -1);
				arg5.addLocation(local81, arg1, null, local96, arg2, arg8, 1, local110, local132, 0, 1);
				if (arg4 >= 12 && arg4 <= 17 && arg4 != 13 && arg1 > 0) {
					this.anIntArrayArrayArray2[arg1][arg8][arg2] |= 0x924;
				}
				if (local84.aBoolean7 && arg0 != null) {
					arg0.setLoc(arg3, local84.anInt44, local84.anInt43, arg8, arg2, local84.aBoolean8);
				}
				if (local84.anInt45 != -1) {
					arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 2, SeqType.instances[local84.anInt45], arg2, arg8));
				}
			} else if (arg4 == 0) {
				local132 = local84.getModel(0, arg3, local36, local47, local60, local71, -1);
				arg5.addWall(0, local81, arg1, anIntArray18[arg3], local132, null, arg8, local96, arg2, local110);
				if (arg3 == 0) {
					if (local84.aBoolean15) {
						this.aByteArrayArrayArray6[arg1][arg8][arg2] = 50;
						this.aByteArrayArrayArray6[arg1][arg8][arg2 + 1] = 50;
					}
					if (local84.aBoolean12) {
						this.anIntArrayArrayArray2[arg1][arg8][arg2] |= 0x249;
					}
				} else if (arg3 == 1) {
					if (local84.aBoolean15) {
						this.aByteArrayArrayArray6[arg1][arg8][arg2 + 1] = 50;
						this.aByteArrayArrayArray6[arg1][arg8 + 1][arg2 + 1] = 50;
					}
					if (local84.aBoolean12) {
						this.anIntArrayArrayArray2[arg1][arg8][arg2 + 1] |= 0x492;
					}
				} else if (arg3 == 2) {
					if (local84.aBoolean15) {
						this.aByteArrayArrayArray6[arg1][arg8 + 1][arg2] = 50;
						this.aByteArrayArrayArray6[arg1][arg8 + 1][arg2 + 1] = 50;
					}
					if (local84.aBoolean12) {
						this.anIntArrayArrayArray2[arg1][arg8 + 1][arg2] |= 0x249;
					}
				} else if (arg3 == 3) {
					if (local84.aBoolean15) {
						this.aByteArrayArrayArray6[arg1][arg8][arg2] = 50;
						this.aByteArrayArrayArray6[arg1][arg8 + 1][arg2] = 50;
					}
					if (local84.aBoolean12) {
						this.anIntArrayArrayArray2[arg1][arg8][arg2] |= 0x492;
					}
				}
				if (local84.aBoolean7 && arg0 != null) {
					arg0.setWall(arg3, arg2, arg8, local84.aBoolean8, arg4);
				}
				if (local84.anInt45 != -1) {
					arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 0, SeqType.instances[local84.anInt45], arg2, arg8));
				}
				if (local84.anInt46 != 16) {
					arg5.setWallDecoration(arg1, arg2, arg8, local84.anInt46);
				}
			} else if (arg4 == 1) {
				local132 = local84.getModel(1, arg3, local36, local47, local60, local71, -1);
				arg5.addWall(0, local81, arg1, anIntArray19[arg3], local132, null, arg8, local96, arg2, local110);
				if (local84.aBoolean15) {
					if (arg3 == 0) {
						this.aByteArrayArrayArray6[arg1][arg8][arg2 + 1] = 50;
					} else if (arg3 == 1) {
						this.aByteArrayArrayArray6[arg1][arg8 + 1][arg2 + 1] = 50;
					} else if (arg3 == 2) {
						this.aByteArrayArrayArray6[arg1][arg8 + 1][arg2] = 50;
					} else if (arg3 == 3) {
						this.aByteArrayArrayArray6[arg1][arg8][arg2] = 50;
					}
				}
				if (local84.aBoolean7 && arg0 != null) {
					arg0.setWall(arg3, arg2, arg8, local84.aBoolean8, arg4);
				}
				if (local84.anInt45 != -1) {
					arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 0, SeqType.instances[local84.anInt45], arg2, arg8));
				}
			} else {
				@Pc(810) int local810;
				@Pc(832) Model local832;
				if (arg4 == 2) {
					local810 = arg3 + 1 & 0x3;
					@Pc(822) Model local822 = local84.getModel(2, arg3 + 4, local36, local47, local60, local71, -1);
					local832 = local84.getModel(2, local810, local36, local47, local60, local71, -1);
					arg5.addWall(anIntArray18[local810], local81, arg1, anIntArray18[arg3], local822, local832, arg8, local96, arg2, local110);
					if (local84.aBoolean12) {
						if (arg3 == 0) {
							this.anIntArrayArrayArray2[arg1][arg8][arg2] |= 0x249;
							this.anIntArrayArrayArray2[arg1][arg8][arg2 + 1] |= 0x492;
						} else if (arg3 == 1) {
							this.anIntArrayArrayArray2[arg1][arg8][arg2 + 1] |= 0x492;
							this.anIntArrayArrayArray2[arg1][arg8 + 1][arg2] |= 0x249;
						} else if (arg3 == 2) {
							this.anIntArrayArrayArray2[arg1][arg8 + 1][arg2] |= 0x249;
							this.anIntArrayArrayArray2[arg1][arg8][arg2] |= 0x492;
						} else if (arg3 == 3) {
							this.anIntArrayArrayArray2[arg1][arg8][arg2] |= 0x492;
							this.anIntArrayArrayArray2[arg1][arg8][arg2] |= 0x249;
						}
					}
					if (local84.aBoolean7 && arg0 != null) {
						arg0.setWall(arg3, arg2, arg8, local84.aBoolean8, arg4);
					}
					if (local84.anInt45 != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 0, SeqType.instances[local84.anInt45], arg2, arg8));
					}
					if (local84.anInt46 != 16) {
						arg5.setWallDecoration(arg1, arg2, arg8, local84.anInt46);
					}
				} else if (arg4 == 3) {
					local132 = local84.getModel(3, arg3, local36, local47, local60, local71, -1);
					arg5.addWall(0, local81, arg1, anIntArray19[arg3], local132, null, arg8, local96, arg2, local110);
					if (local84.aBoolean15) {
						if (arg3 == 0) {
							this.aByteArrayArrayArray6[arg1][arg8][arg2 + 1] = 50;
						} else if (arg3 == 1) {
							this.aByteArrayArrayArray6[arg1][arg8 + 1][arg2 + 1] = 50;
						} else if (arg3 == 2) {
							this.aByteArrayArrayArray6[arg1][arg8 + 1][arg2] = 50;
						} else if (arg3 == 3) {
							this.aByteArrayArrayArray6[arg1][arg8][arg2] = 50;
						}
					}
					if (local84.aBoolean7 && arg0 != null) {
						arg0.setWall(arg3, arg2, arg8, local84.aBoolean8, arg4);
					}
					if (local84.anInt45 != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 0, SeqType.instances[local84.anInt45], arg2, arg8));
					}
				} else if (arg4 == 9) {
					local132 = local84.getModel(arg4, arg3, local36, local47, local60, local71, -1);
					arg5.addLocation(local81, arg1, null, local96, arg2, arg8, 1, local110, local132, 0, 1);
					if (local84.aBoolean7 && arg0 != null) {
						arg0.setLoc(arg3, local84.anInt44, local84.anInt43, arg8, arg2, local84.aBoolean8);
					}
					if (local84.anInt45 != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 2, SeqType.instances[local84.anInt45], arg2, arg8));
					}
				} else if (arg4 == 4) {
					local132 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, 0, local96, arg3 * 512, anIntArray18[arg3], 0, arg8, local132, local110, arg1);
					if (local84.anInt45 != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anInt45], arg2, arg8));
					}
				} else if (arg4 == 5) {
					local810 = 16;
					local209 = arg5.getWallBitset(arg1, arg8, arg2);
					if (local209 > 0) {
						local810 = LocType.get(local209 >> 14 & 0x7FFF).anInt46;
					}
					local832 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, anIntArray21[arg3] * local810, local96, arg3 * 512, anIntArray18[arg3], anIntArray20[arg3] * local810, arg8, local832, local110, arg1);
					if (local84.anInt45 != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anInt45], arg2, arg8));
					}
				} else if (arg4 == 6) {
					local132 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, 0, local96, arg3, 256, 0, arg8, local132, local110, arg1);
					if (local84.anInt45 != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anInt45], arg2, arg8));
					}
				} else if (arg4 == 7) {
					local132 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, 0, local96, arg3, 512, 0, arg8, local132, local110, arg1);
					if (local84.anInt45 != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anInt45], arg2, arg8));
					}
				} else if (arg4 == 8) {
					local132 = local84.getModel(4, 0, local36, local47, local60, local71, -1);
					arg5.addWallDecoration(local81, arg2, 0, local96, arg3, 768, 0, arg8, local132, local110, arg1);
					if (local84.anInt45 != -1) {
						arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 1, SeqType.instances[local84.anInt45], arg2, arg8));
					}
				}
			}
		} else if (!aBoolean23 || local84.aBoolean9 || local84.aBoolean16) {
			local132 = local84.getModel(22, arg3, local36, local47, local60, local71, -1);
			arg5.addGroundDecoration(local132, arg8, local96, arg2, arg1, local110, local81);
			if (local84.aBoolean7 && local84.aBoolean9 && arg0 != null) {
				arg0.setBlocoked(arg2, arg8);
			}
			if (local84.anInt45 != -1) {
				arg6.pushNext(new LocEntity(true, arg7, arg1, 0, 3, SeqType.instances[local84.anInt45], arg2, arg8));
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(Lclient!r;I[Lclient!ec;)V")
	public final void buildLandscape(@OriginalArg(0) MapSquare arg0, @OriginalArg(2) CollisionMap[] arg1) {
		@Pc(7) int local7;
		@Pc(11) int local11;
		@Pc(27) int local27;
		for (@Pc(3) int local3 = 0; local3 < 4; local3++) {
			for (local7 = 0; local7 < 104; local7++) {
				for (local11 = 0; local11 < 104; local11++) {
					if ((this.aByteArrayArrayArray1[local3][local7][local11] & 0x1) == 1) {
						local27 = local3;
						if ((this.aByteArrayArrayArray1[1][local7][local11] & 0x2) == 2) {
							local27 = local3 - 1;
						}
						if (local27 >= 0) {
							arg1[local27].setBlocoked(local11, local7);
						}
					}
				}
			}
		}
		anInt84 += (int) (Math.random() * 5.0D) - 2;
		if (anInt84 < -8) {
			anInt84 = -8;
		}
		if (anInt84 > 8) {
			anInt84 = 8;
		}
		anInt85 += (int) (Math.random() * 5.0D) - 2;
		if (anInt85 < -16) {
			anInt85 = -16;
		}
		if (anInt85 > 16) {
			anInt85 = 16;
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
			@Pc(108) byte[][] local108 = this.aByteArrayArrayArray6[local7];
			local133 = (int) Math.sqrt((double) 5100);
			local139 = local133 * 768 >> 8;
			for (local141 = 1; local141 < this.anInt83 - 1; local141++) {
				for (local145 = 1; local145 < this.anInt82 - 1; local145++) {
					local169 = this.anIntArrayArrayArray1[local7][local145 + 1][local141] - this.anIntArrayArrayArray1[local7][local145 - 1][local141];
					local191 = this.anIntArrayArrayArray1[local7][local145][local141 + 1] - this.anIntArrayArrayArray1[local7][local145][local141 - 1];
					local204 = (int) Math.sqrt((double) (local169 * local169 + local191 * local191 + 65536));
					local210 = (local169 << 8) / local204;
					local214 = 65536 / local204;
					local220 = (local191 << 8) / local204;
					local236 = (local210 * -50 + local214 * -10 + local220 * -50) / local139 + 96;
					local284 = (local108[local145 - 1][local141] >> 2) + (local108[local145 + 1][local141] >> 3) + (local108[local145][local141 - 1] >> 2) + (local108[local145][local141 + 1] >> 3) + (local108[local145][local141] >> 1);
					this.anIntArrayArray1[local145][local141] = local236 - local284;
				}
			}
			for (local145 = 0; local145 < this.anInt83; local145++) {
				this.anIntArray13[local145] = 0;
				this.anIntArray14[local145] = 0;
				this.anIntArray15[local145] = 0;
				this.anIntArray16[local145] = 0;
				this.anIntArray17[local145] = 0;
			}
			for (local169 = -5; local169 < this.anInt82 + 5; local169++) {
				for (local191 = 0; local191 < this.anInt83; local191++) {
					local204 = local169 + 5;
					@Pc(419) int local419;
					if (local204 >= 0 && local204 < this.anInt82) {
						local210 = this.aByteArrayArrayArray2[local7][local204][local191] & 0xFF;
						if (local210 > 0) {
							@Pc(378) FloType local378 = FloType.aFloTypeArray1[local210 - 1];
							this.anIntArray13[local191] += local378.anInt408;
							this.anIntArray14[local191] += local378.anInt406;
							this.anIntArray15[local191] += local378.anInt407;
							this.anIntArray16[local191] += local378.anInt409;
							local419 = this.anIntArray17[local191]++;
						}
					}
					local210 = local169 - 5;
					if (local210 >= 0 && local210 < this.anInt82) {
						local214 = this.aByteArrayArrayArray2[local7][local210][local191] & 0xFF;
						if (local214 > 0) {
							@Pc(451) FloType local451 = FloType.aFloTypeArray1[local214 - 1];
							this.anIntArray13[local191] -= local451.anInt408;
							this.anIntArray14[local191] -= local451.anInt406;
							this.anIntArray15[local191] -= local451.anInt407;
							this.anIntArray16[local191] -= local451.anInt409;
							local419 = this.anIntArray17[local191]--;
						}
					}
				}
				if (local169 >= 1 && local169 < this.anInt82 - 1) {
					local204 = 0;
					local210 = 0;
					local214 = 0;
					local220 = 0;
					local236 = 0;
					for (local284 = -5; local284 < this.anInt83 + 5; local284++) {
						@Pc(527) int local527 = local284 + 5;
						if (local527 >= 0 && local527 < this.anInt83) {
							local204 += this.anIntArray13[local527];
							local210 += this.anIntArray14[local527];
							local214 += this.anIntArray15[local527];
							local220 += this.anIntArray16[local527];
							local236 += this.anIntArray17[local527];
						}
						@Pc(572) int local572 = local284 - 5;
						if (local572 >= 0 && local572 < this.anInt83) {
							local204 -= this.anIntArray13[local572];
							local210 -= this.anIntArray14[local572];
							local214 -= this.anIntArray15[local572];
							local220 -= this.anIntArray16[local572];
							local236 -= this.anIntArray17[local572];
						}
						if (local284 >= 1 && local284 < this.anInt83 - 1 && (!aBoolean23 || (this.aByteArrayArrayArray1[local7][local169][local284] & 0x10) == 0 && this.getRenderLevel(local7, local169, local284) == anInt81)) {
							@Pc(655) int local655 = this.aByteArrayArrayArray2[local7][local169][local284] & 0xFF;
							@Pc(666) int local666 = this.aByteArrayArrayArray3[local7][local169][local284] & 0xFF;
							if (local655 > 0 || local666 > 0) {
								@Pc(679) int local679 = this.anIntArrayArrayArray1[local7][local169][local284];
								@Pc(690) int local690 = this.anIntArrayArrayArray1[local7][local169 + 1][local284];
								@Pc(703) int local703 = this.anIntArrayArrayArray1[local7][local169 + 1][local284 + 1];
								@Pc(714) int local714 = this.anIntArrayArrayArray1[local7][local169][local284 + 1];
								@Pc(721) int local721 = this.anIntArrayArray1[local169][local284];
								@Pc(730) int local730 = this.anIntArrayArray1[local169 + 1][local284];
								@Pc(741) int local741 = this.anIntArrayArray1[local169 + 1][local284 + 1];
								@Pc(750) int local750 = this.anIntArrayArray1[local169][local284 + 1];
								@Pc(752) int local752 = -1;
								@Pc(754) int local754 = -1;
								@Pc(762) int local762;
								@Pc(766) int local766;
								if (local655 > 0) {
									local762 = local204 * 256 / local220;
									local766 = local210 / local236;
									@Pc(770) int local770 = local214 / local236;
									local752 = this.hsl24To16(local762, local766, local770);
									@Pc(782) int local782 = local762 + anInt84 & 0xFF;
									local770 += anInt85;
									if (local770 < 0) {
										local770 = 0;
									} else if (local770 > 255) {
										local770 = 255;
									}
									local754 = this.hsl24To16(local782, local766, local770);
								}
								if (local7 > 0) {
									@Pc(807) boolean local807 = true;
									if (local655 == 0 && this.aByteArrayArrayArray4[local7][local169][local284] != 0) {
										local807 = false;
									}
									if (local666 > 0 && !FloType.aFloTypeArray1[local666 - 1].aBoolean91) {
										local807 = false;
									}
									if (local807 && local679 == local690 && local679 == local703 && local679 == local714) {
										this.anIntArrayArrayArray2[local7][local169][local284] |= 0x924;
									}
								}
								local762 = 0;
								if (local752 != -1) {
									local762 = Draw3D.anIntArray142[adjustHslLightness1(local754, 96)];
								}
								if (local666 == 0) {
									arg0.addTile(local7, local169, local284, 0, 0, -1, local679, local690, local703, local714, adjustHslLightness1(local752, local721), adjustHslLightness1(local752, local730), adjustHslLightness1(local752, local741), adjustHslLightness1(local752, local750), 0, 0, 0, 0, local762, 0);
								} else {
									local766 = this.aByteArrayArrayArray4[local7][local169][local284] + 1;
									@Pc(919) byte local919 = this.aByteArrayArrayArray5[local7][local169][local284];
									@Pc(925) FloType local925 = FloType.aFloTypeArray1[local666 - 1];
									@Pc(928) int local928 = local925.anInt404;
									@Pc(936) int local936;
									@Pc(934) int local934;
									if (local928 >= 0) {
										local934 = Draw3D.getAverageTextureRgb(local928);
										local936 = -1;
									} else if (local925.anInt403 == 16711935) {
										local934 = 0;
										local936 = -2;
										local928 = -1;
									} else {
										local936 = this.hsl24To16(local925.anInt405, local925.anInt406, local925.anInt407);
										local934 = Draw3D.anIntArray142[this.adjustHslLightness0(local925.anInt410, 96)];
									}
									arg0.addTile(local7, local169, local284, local766, local919, local928, local679, local690, local703, local714, adjustHslLightness1(local752, local721), adjustHslLightness1(local752, local730), adjustHslLightness1(local752, local741), adjustHslLightness1(local752, local750), this.adjustHslLightness0(local936, local721), this.adjustHslLightness0(local936, local730), this.adjustHslLightness0(local936, local741), this.adjustHslLightness0(local936, local750), local762, local934);
								}
							}
						}
					}
				}
			}
			for (local191 = 1; local191 < this.anInt83 - 1; local191++) {
				for (local204 = 1; local204 < this.anInt82 - 1; local204++) {
					arg0.setPhysicalLevel(local7, local204, local191, this.getRenderLevel(local7, local204, local191));
				}
			}
		}
		if (!aBoolean24) {
			arg0.applyLighting();
		}
		for (local11 = 0; local11 < this.anInt82; local11++) {
			for (local27 = 0; local27 < this.anInt83; local27++) {
				if ((this.aByteArrayArrayArray1[1][local11][local27] & 0x2) == 2) {
					arg0.setBridge(local27, local11);
				}
			}
		}
		if (aBoolean24) {
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
				for (local133 = 0; local133 <= this.anInt83; local133++) {
					for (local139 = 0; local139 <= this.anInt82; local139++) {
						if ((this.anIntArrayArrayArray2[local1145][local139][local133] & local27) != 0) {
							local141 = local133;
							local145 = local133;
							local169 = local1145;
							local191 = local1145;
							while (local141 > 0 && (this.anIntArrayArrayArray2[local1145][local139][local141 - 1] & local27) != 0) {
								local141--;
							}
							while (local145 < this.anInt83 && (this.anIntArrayArrayArray2[local1145][local139][local145 + 1] & local27) != 0) {
								local145++;
							}
							label329: while (local169 > 0) {
								for (local204 = local141; local204 <= local145; local204++) {
									if ((this.anIntArrayArrayArray2[local169 - 1][local139][local204] & local27) == 0) {
										break label329;
									}
								}
								local169--;
							}
							label318: while (local191 < local1127) {
								for (local204 = local141; local204 <= local145; local204++) {
									if ((this.anIntArrayArrayArray2[local191 + 1][local139][local204] & local27) == 0) {
										break label318;
									}
								}
								local191++;
							}
							local204 = (local191 + 1 - local169) * (local145 - local141 + 1);
							if (local204 >= 8) {
								local214 = this.anIntArrayArrayArray1[local191][local139][local141] - 240;
								local220 = this.anIntArrayArrayArray1[local169][local139][local141];
								MapSquare.addOclude(local145 * 128 + 128, local139 * 128, local220, 1, local139 * 128, local1127, local214, local141 * 128);
								for (local236 = local169; local236 <= local191; local236++) {
									for (local284 = local141; local284 <= local145; local284++) {
										this.anIntArrayArrayArray2[local236][local139][local284] &= ~local27;
									}
								}
							}
						}
						if ((this.anIntArrayArrayArray2[local1145][local139][local133] & local1123) != 0) {
							local141 = local139;
							local145 = local139;
							local169 = local1145;
							local191 = local1145;
							while (local141 > 0 && (this.anIntArrayArrayArray2[local1145][local141 - 1][local133] & local1123) != 0) {
								local141--;
							}
							while (local145 < this.anInt82 && (this.anIntArrayArrayArray2[local1145][local145 + 1][local133] & local1123) != 0) {
								local145++;
							}
							label382: while (local169 > 0) {
								for (local204 = local141; local204 <= local145; local204++) {
									if ((this.anIntArrayArrayArray2[local169 - 1][local204][local133] & local1123) == 0) {
										break label382;
									}
								}
								local169--;
							}
							label371: while (local191 < local1127) {
								for (local204 = local141; local204 <= local145; local204++) {
									if ((this.anIntArrayArrayArray2[local191 + 1][local204][local133] & local1123) == 0) {
										break label371;
									}
								}
								local191++;
							}
							local204 = (local191 + 1 - local169) * (local145 - local141 + 1);
							if (local204 >= 8) {
								local214 = this.anIntArrayArrayArray1[local191][local141][local133] - 240;
								local220 = this.anIntArrayArrayArray1[local169][local141][local133];
								MapSquare.addOclude(local133 * 128, local141 * 128, local220, 2, local145 * 128 + 128, local1127, local214, local133 * 128);
								for (local236 = local169; local236 <= local191; local236++) {
									for (local284 = local141; local284 <= local145; local284++) {
										this.anIntArrayArrayArray2[local236][local284][local133] &= ~local1123;
									}
								}
							}
						}
						if ((this.anIntArrayArrayArray2[local1145][local139][local133] & local1125) != 0) {
							local141 = local139;
							local145 = local139;
							local169 = local133;
							local191 = local133;
							while (local169 > 0 && (this.anIntArrayArrayArray2[local1145][local139][local169 - 1] & local1125) != 0) {
								local169--;
							}
							while (local191 < this.anInt83 && (this.anIntArrayArrayArray2[local1145][local139][local191 + 1] & local1125) != 0) {
								local191++;
							}
							label435: while (local141 > 0) {
								for (local204 = local169; local204 <= local191; local204++) {
									if ((this.anIntArrayArrayArray2[local1145][local141 - 1][local204] & local1125) == 0) {
										break label435;
									}
								}
								local141--;
							}
							label424: while (local145 < this.anInt82) {
								for (local204 = local169; local204 <= local191; local204++) {
									if ((this.anIntArrayArrayArray2[local1145][local145 + 1][local204] & local1125) == 0) {
										break label424;
									}
								}
								local145++;
							}
							if ((local145 + 1 - local141) * (local191 - local169 + 1) >= 4) {
								local204 = this.anIntArrayArrayArray1[local1145][local141][local169];
								MapSquare.addOclude(local191 * 128 + 128, local141 * 128, local204, 4, local145 * 128 + 128, local1127, local204, local169 * 128);
								for (local210 = local141; local210 <= local145; local210++) {
									for (local214 = local169; local214 <= local191; local214++) {
										this.anIntArrayArrayArray2[local1145][local210][local214] &= ~local1125;
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
		if ((this.aByteArrayArrayArray1[arg0][arg1][arg2] & 0x8) == 0) {
			return arg0 <= 0 || (this.aByteArrayArrayArray1[1][arg1][arg2] & 0x2) == 0 ? arg0 : arg0 - 1;
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
}
