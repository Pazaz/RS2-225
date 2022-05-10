import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class BZip2InputStream {

	@OriginalMember(owner = "client!rb", name = "a", descriptor = "Lclient!sb;")
	private static final BZip2Context context = new BZip2Context();

	@OriginalMember(owner = "client!rb", name = "a", descriptor = "([BI[BII)I")
	public static int read(@OriginalArg(0) byte[] output, @OriginalArg(1) int length, @OriginalArg(2) byte[] compressed, @OriginalArg(3) int decompressedLength, @OriginalArg(4) int minLen) {
		@Pc(3) BZip2Context local3 = context;
		synchronized (context) {
			context.compressed = compressed;
			context.nextIn = minLen;
			context.decompressed = output;
			context.nextOut = 0;
			context.decompressedLength = decompressedLength;
			context.length = length;
			context.bsLive = 0;
			context.bsBuff = 0;
			context.totalInLo32 = 0;
			context.totalInHi32 = 0;
			context.totalOutLo32 = 0;
			context.totalOutHigh32 = 0;
			context.currentBlock = 0;
			decompress(context);
			return length - context.length;
		}
	}

	@OriginalMember(owner = "client!rb", name = "a", descriptor = "(Lclient!sb;)V")
	private static void finish(@OriginalArg(0) BZip2Context arg0) {
		@Pc(4) byte local4 = arg0.stateOutCh;
		@Pc(7) int local7 = arg0.stateOutLen;
		@Pc(10) int local10 = arg0.usedBlocks;
		@Pc(13) int local13 = arg0.k0;
		@Pc(15) int[] local15 = BZip2Context.tt;
		@Pc(18) int local18 = arg0.tPos;
		@Pc(21) byte[] local21 = arg0.decompressed;
		@Pc(24) int local24 = arg0.nextOut;
		@Pc(27) int local27 = arg0.length;
		@Pc(29) int local29 = local27;
		@Pc(34) int local34 = arg0.count + 1;
		label67: while (true) {
			if (local7 > 0) {
				while (true) {
					if (local27 == 0) {
						break label67;
					}
					if (local7 == 1) {
						if (local27 == 0) {
							local7 = 1;
							break label67;
						}
						local21[local24] = local4;
						local24++;
						local27--;
						break;
					}
					local21[local24] = local4;
					local7--;
					local24++;
					local27--;
				}
			}
			@Pc(64) boolean local64 = true;
			@Pc(87) byte local87;
			while (local64) {
				local64 = false;
				if (local10 == local34) {
					local7 = 0;
					break label67;
				}
				local4 = (byte) local13;
				local18 = local15[local18];
				local87 = (byte) (local18 & 0xFF);
				local18 >>= 0x8;
				local10++;
				if (local87 != local13) {
					local13 = local87;
					if (local27 == 0) {
						local7 = 1;
						break label67;
					}
					local21[local24] = local4;
					local24++;
					local27--;
					local64 = true;
				} else if (local10 == local34) {
					if (local27 == 0) {
						local7 = 1;
						break label67;
					}
					local21[local24] = local4;
					local24++;
					local27--;
					local64 = true;
				}
			}
			local7 = 2;
			local18 = local15[local18];
			local87 = (byte) (local18 & 0xFF);
			local18 >>= 0x8;
			local10++;
			if (local10 != local34) {
				if (local87 == local13) {
					local7 = 3;
					local18 = local15[local18];
					local87 = (byte) (local18 & 0xFF);
					local18 >>= 0x8;
					local10++;
					if (local10 != local34) {
						if (local87 == local13) {
							local18 = local15[local18];
							local87 = (byte) (local18 & 0xFF);
							local18 >>= 0x8;
							local10++;
							local7 = (local87 & 0xFF) + 4;
							local18 = local15[local18];
							local13 = (byte) (local18 & 0xFF);
							local18 >>= 0x8;
							local10++;
						} else {
							local13 = local87;
						}
					}
				} else {
					local13 = local87;
				}
			}
		}
		@Pc(224) int local224 = arg0.totalOutLo32;
		arg0.totalOutLo32 += local29 - local27;
		if (arg0.totalOutLo32 < local224) {
			arg0.totalOutHigh32++;
		}
		arg0.stateOutCh = local4;
		arg0.stateOutLen = local7;
		arg0.usedBlocks = local10;
		arg0.k0 = local13;
		BZip2Context.tt = local15;
		arg0.tPos = local18;
		arg0.decompressed = local21;
		arg0.nextOut = local24;
		arg0.length = local27;
	}

	@OriginalMember(owner = "client!rb", name = "b", descriptor = "(Lclient!sb;)V")
	private static void decompress(@OriginalArg(0) BZip2Context context) {
		context.unusedMultiplier = 1;
		if (BZip2Context.tt == null) {
			BZip2Context.tt = new int[context.unusedMultiplier * 100000];
		}
		@Pc(60) boolean local60 = true;
		while (true) {
			while (local60) {
				@Pc(64) byte uc = getUnsignedChar(context);
				if (uc == 23) {
					return;
				}
				uc = getUnsignedChar(context);
				uc = getUnsignedChar(context);
				uc = getUnsignedChar(context);
				uc = getUnsignedChar(context);
				uc = getUnsignedChar(context);
				context.currentBlock++;
				uc = getUnsignedChar(context);
				uc = getUnsignedChar(context);
				uc = getUnsignedChar(context);
				uc = getUnsignedChar(context);
				uc = getBit(context);
				if (uc == 0) {
					context.randomized = false;
				} else {
					context.randomized = true;
				}
				if (context.randomized) {
					System.out.println("PANIC! RANDOMISED BLOCK!");
				}
				context.origPtr = 0;
				uc = getUnsignedChar(context);
				context.origPtr = context.origPtr << 8 | uc & 0xFF;
				uc = getUnsignedChar(context);
				context.origPtr = context.origPtr << 8 | uc & 0xFF;
				uc = getUnsignedChar(context);
				context.origPtr = context.origPtr << 8 | uc & 0xFF;
				@Pc(164) int i;
				for (i = 0; i < 16; i++) {
					uc = getBit(context);
					if (uc == 1) {
						context.inUse16[i] = true;
					} else {
						context.inUse16[i] = false;
					}
				}
				for (i = 0; i < 256; i++) {
					context.inUse[i] = false;
				}
				@Pc(212) int count;
				for (i = 0; i < 16; i++) {
					if (context.inUse16[i]) {
						for (count = 0; count < 16; count++) {
							uc = getBit(context);
							if (uc == 1) {
								context.inUse[i * 16 + count] = true;
							}
						}
					}
				}
				makeMaps(context);
				@Pc(244) int alphabetSize = context.nInUse + 2;
				@Pc(248) int huffmanTableCount = getBits(3, context);
				@Pc(252) int swapCount = getBits(15, context);
				for (i = 0; i < swapCount; i++) {
					count = 0;
					while (true) {
						uc = getBit(context);
						if (uc == 0) {
							context.selectorMtf[i] = (byte) count;
							break;
						}
						count++;
					}
				}
				@Pc(279) byte[] pos = new byte[6];
				@Pc(281) byte v = 0;
				while (v < huffmanTableCount) {
					pos[v] = v++;
				}
				for (i = 0; i < swapCount; i++) {
					v = context.selectorMtf[i];
					@Pc(308) byte local308 = pos[v];
					while (v > 0) {
						pos[v] = pos[v - 1];
						v--;
					}
					pos[0] = local308;
					context.selector[i] = local308;
				}
				@Pc(340) int t;
				for (t = 0; t < huffmanTableCount; t++) {
					@Pc(346) int read = getBits(5, context);
					for (i = 0; i < alphabetSize; i++) {
						while (true) {
							uc = getBit(context);
							if (uc == 0) {
								context.len[t][i] = (byte) read;
								break;
							}
							uc = getBit(context);
							if (uc == 0) {
								read++;
							} else {
								read--;
							}
						}
					}
				}
				for (t = 0; t < huffmanTableCount; t++) {
					@Pc(388) byte minLen = 32;
					@Pc(390) byte maxLen = 0;
					for (i = 0; i < alphabetSize; i++) {
						if (context.len[t][i] > maxLen) {
							maxLen = context.len[t][i];
						}
						if (context.len[t][i] < minLen) {
							minLen = context.len[t][i];
						}
					}
					createDecodeTables(context.limit[t], context.base[t], context.perm[t], context.len[t], minLen, maxLen, alphabetSize);
					context.minLens[t] = minLen;
				}
				@Pc(462) int local462 = context.nInUse + 1;
				@Pc(467) int local467 = context.unusedMultiplier * 100000;
				@Pc(469) byte local469 = -1;
				for (i = 0; i <= 255; i++) {
					context.unzftab[i] = 0;
				}
				@Pc(486) int kk = 4095;
				for (@Pc(488) int ii = 15; ii >= 0; ii--) {
					for (@Pc(492) int jj = 15; jj >= 0; jj--) {
						context.mtfa[kk] = (byte) (ii * 16 + jj);
						kk--;
					}
					context.mtfbase[ii] = kk + 1;
				}
				@Pc(520) int nblock = 0;
				@Pc(523) int local523 = local469 + 1;
				@Pc(525) byte local525 = 50;
				@Pc(530) byte local530 = context.selector[0];
				@Pc(535) int local535 = context.minLens[local530];
				@Pc(540) int[] local540 = context.limit[local530];
				@Pc(545) int[] local545 = context.perm[local530];
				@Pc(550) int[] local550 = context.base[local530];
				@Pc(551) int local551 = local525 - 1;
				@Pc(553) int local553 = local535;
				@Pc(557) int local557;
				@Pc(566) byte local566;
				for (local557 = getBits(local535, context); local557 > local540[local553]; local557 = local557 << 1 | local566) {
					local553++;
					local566 = getBit(context);
				}
				@Pc(582) int local582 = local545[local557 - local550[local553]];
				while (true) {
					while (local582 != local462) {
						if (local582 == 0 || local582 == 1) {
							@Pc(592) int local592 = -1;
							@Pc(594) int local594 = 1;
							do {
								if (local582 == 0) {
									local592 += local594;
								} else if (local582 == 1) {
									local592 += local594 * 2;
								}
								local594 *= 2;
								if (local551 == 0) {
									local523++;
									local551 = 50;
									local530 = context.selector[local523];
									local535 = context.minLens[local530];
									local540 = context.limit[local530];
									local545 = context.perm[local530];
									local550 = context.base[local530];
								}
								local551--;
								local553 = local535;
								for (local557 = getBits(local535, context); local557 > local540[local553]; local557 = local557 << 1 | local566) {
									local553++;
									local566 = getBit(context);
								}
								local582 = local545[local557 - local550[local553]];
							} while (local582 == 0 || local582 == 1);
							local592++;
							uc = context.seqToUnseq[context.mtfa[context.mtfbase[0]] & 0xFF];
							context.unzftab[uc & 0xFF] += local592;
							while (local592 > 0) {
								BZip2Context.tt[nblock] = uc & 0xFF;
								nblock++;
								local592--;
							}
						} else {
							@Pc(724) int local724 = local582 - 1;
							@Pc(732) int local732;
							if (local724 < 16) {
								local732 = context.mtfbase[0];
								uc = context.mtfa[local732 + local724];
								while (local724 > 3) {
									@Pc(745) int local745 = local732 + local724;
									context.mtfa[local745] = context.mtfa[local745 - 1];
									context.mtfa[local745 - 1] = context.mtfa[local745 - 2];
									context.mtfa[local745 - 2] = context.mtfa[local745 - 3];
									context.mtfa[local745 - 3] = context.mtfa[local745 - 4];
									local724 -= 4;
								}
								while (local724 > 0) {
									context.mtfa[local732 + local724] = context.mtfa[local732 + local724 - 1];
									local724--;
								}
								context.mtfa[local732] = uc;
							} else {
								@Pc(825) int local825 = local724 / 16;
								@Pc(829) int local829 = local724 % 16;
								local732 = context.mtfbase[local825] + local829;
								uc = context.mtfa[local732];
								while (local732 > context.mtfbase[local825]) {
									context.mtfa[local732] = context.mtfa[local732 - 1];
									local732--;
								}
								@Pc(865) int local865 = context.mtfbase[local825]++;
								while (local825 > 0) {
									local865 = context.mtfbase[local825]--;
									context.mtfa[context.mtfbase[local825]] = context.mtfa[context.mtfbase[local825 - 1] + 16 - 1];
									local825--;
								}
								local865 = context.mtfbase[0]--;
								context.mtfa[context.mtfbase[0]] = uc;
								if (context.mtfbase[0] == 0) {
									@Pc(924) int local924 = 4095;
									for (@Pc(926) int local926 = 15; local926 >= 0; local926--) {
										for (@Pc(930) int local930 = 15; local930 >= 0; local930--) {
											context.mtfa[local924] = context.mtfa[context.mtfbase[local926] + local930];
											local924--;
										}
										context.mtfbase[local926] = local924 + 1;
									}
								}
							}
							context.unzftab[context.seqToUnseq[uc & 0xFF] & 0xFF]++;
							BZip2Context.tt[nblock] = context.seqToUnseq[uc & 0xFF] & 0xFF;
							nblock++;
							if (local551 == 0) {
								local523++;
								local551 = 50;
								local530 = context.selector[local523];
								local535 = context.minLens[local530];
								local540 = context.limit[local530];
								local545 = context.perm[local530];
								local550 = context.base[local530];
							}
							local551--;
							local553 = local535;
							for (local557 = getBits(local535, context); local557 > local540[local553]; local557 = local557 << 1 | local566) {
								local553++;
								local566 = getBit(context);
							}
							local582 = local545[local557 - local550[local553]];
						}
					}
					context.stateOutLen = 0;
					context.stateOutCh = 0;
					context.cftab[0] = 0;
					for (i = 1; i <= 256; i++) {
						context.cftab[i] = context.unzftab[i - 1];
					}
					for (i = 1; i <= 256; i++) {
						context.cftab[i] += context.cftab[i - 1];
					}
					for (i = 0; i < nblock; i++) {
						uc = (byte) (BZip2Context.tt[i] & 0xFF);
						BZip2Context.tt[context.cftab[uc & 0xFF]] |= i << 8;
						context.cftab[uc & 0xFF]++;
					}
					context.tPos = BZip2Context.tt[context.origPtr] >> 8;
					context.usedBlocks = 0;
					context.tPos = BZip2Context.tt[context.tPos];
					context.k0 = (byte) (context.tPos & 0xFF);
					context.tPos >>= 0x8;
					context.usedBlocks++;
					context.count = nblock;
					finish(context);
					if (context.usedBlocks == context.count + 1 && context.stateOutLen == 0) {
						local60 = true;
						break;
					}
					local60 = false;
					break;
				}
			}
			return;
		}
	}

	@OriginalMember(owner = "client!rb", name = "c", descriptor = "(Lclient!sb;)B")
	private static byte getUnsignedChar(@OriginalArg(0) BZip2Context arg0) {
		return (byte) getBits(8, arg0);
	}

	@OriginalMember(owner = "client!rb", name = "d", descriptor = "(Lclient!sb;)B")
	private static byte getBit(@OriginalArg(0) BZip2Context arg0) {
		return (byte) getBits(1, arg0);
	}

	@OriginalMember(owner = "client!rb", name = "a", descriptor = "(ILclient!sb;)I")
	private static int getBits(@OriginalArg(0) int arg0, @OriginalArg(1) BZip2Context arg1) {
		while (arg1.bsLive < arg0) {
			arg1.bsBuff = arg1.bsBuff << 8 | arg1.compressed[arg1.nextIn] & 0xFF;
			arg1.bsLive += 8;
			arg1.nextIn++;
			arg1.decompressedLength--;
			arg1.totalInLo32++;
			if (arg1.totalInLo32 == 0) {
				arg1.totalInHi32++;
			}
		}
		@Pc(17) int local17 = arg1.bsBuff >> arg1.bsLive - arg0 & (0x1 << arg0) - 1;
		arg1.bsLive -= arg0;
		return local17;
	}

	@OriginalMember(owner = "client!rb", name = "e", descriptor = "(Lclient!sb;)V")
	private static void makeMaps(@OriginalArg(0) BZip2Context arg0) {
		arg0.nInUse = 0;
		for (@Pc(4) int local4 = 0; local4 < 256; local4++) {
			if (arg0.inUse[local4]) {
				arg0.seqToUnseq[arg0.nInUse] = (byte) local4;
				arg0.nInUse++;
			}
		}
	}

	@OriginalMember(owner = "client!rb", name = "a", descriptor = "([I[I[I[BIII)V")
	private static void createDecodeTables(@OriginalArg(0) int[] arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) int[] arg2, @OriginalArg(3) byte[] arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(3) int local3 = 0;
		@Pc(5) int local5;
		for (local5 = arg4; local5 <= arg5; local5++) {
			for (@Pc(9) int local9 = 0; local9 < arg6; local9++) {
				if (arg3[local9] == local5) {
					arg2[local3] = local9;
					local3++;
				}
			}
		}
		for (local5 = 0; local5 < 23; local5++) {
			arg1[local5] = 0;
		}
		for (local5 = 0; local5 < arg6; local5++) {
			arg1[arg3[local5] + 1]++;
		}
		for (local5 = 1; local5 < 23; local5++) {
			arg1[local5] += arg1[local5 - 1];
		}
		for (local5 = 0; local5 < 23; local5++) {
			arg0[local5] = 0;
		}
		@Pc(93) int local93 = 0;
		for (local5 = arg4; local5 <= arg5; local5++) {
			local93 += arg1[local5 + 1] - arg1[local5];
			arg0[local5] = local93 - 1;
			local93 <<= 0x1;
		}
		for (local5 = arg4 + 1; local5 <= arg5; local5++) {
			arg1[local5] = (arg0[local5 - 1] + 1 << 1) - arg1[local5];
		}
	}
}
