package com.jagex.core.stringutils;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WordPack {

	@OriginalMember(owner = "client!mc", name = "j", descriptor = "[I")
	private static int[] fragments;

	@OriginalMember(owner = "client!mc", name = "k", descriptor = "[[C")
	private static char[][] bads;

	@OriginalMember(owner = "client!mc", name = "l", descriptor = "[[[B")
	private static byte[][][] badCombinations;

	@OriginalMember(owner = "client!mc", name = "m", descriptor = "[[C")
	private static char[][] domains;

	@OriginalMember(owner = "client!mc", name = "n", descriptor = "[[C")
	private static char[][] tlds;

	@OriginalMember(owner = "client!mc", name = "o", descriptor = "[I")
	private static int[] tldTypes;

	@OriginalMember(owner = "client!mc", name = "p", descriptor = "[Ljava/lang/String;")
	private static final String[] whitelist = new String[] { "cook", "cook's", "cooks", "seeks", "sheet" };

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(Lclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive arg0) {
		@Pc(11) Buffer fragments = new Buffer(arg0.read("fragmentsenc.txt", null));
		@Pc(21) Buffer bad = new Buffer(arg0.read("badenc.txt", null));
		@Pc(31) Buffer domain = new Buffer(arg0.read("domainenc.txt", null));
		@Pc(41) Buffer tld = new Buffer(arg0.read("tldlist.txt", null));
		decode(fragments, bad, domain, tld);
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(Lclient!kb;Lclient!kb;Lclient!kb;Lclient!kb;)V")
	private static void decode(@OriginalArg(0) Buffer fragments, @OriginalArg(1) Buffer bad, @OriginalArg(2) Buffer domain, @OriginalArg(3) Buffer tld) {
		readBad(bad);
		readDomain(domain);
		readFragments(fragments);
		readTld(tld);
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(ZLclient!kb;)V")
	private static void readTld(@OriginalArg(1) Buffer arg0) {
		@Pc(4) int local4 = arg0.g4();
		tlds = new char[local4][];
		tldTypes = new int[local4];
		for (@Pc(15) int local15 = 0; local15 < local4; local15++) {
			tldTypes[local15] = arg0.g1();
			@Pc(26) char[] local26 = new char[arg0.g1()];
			for (@Pc(28) int local28 = 0; local28 < local26.length; local28++) {
				local26[local28] = (char) arg0.g1();
			}
			tlds[local15] = local26;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(ILclient!kb;)V")
	private static void readBad(@OriginalArg(1) Buffer arg0) {
		@Pc(2) int local2 = arg0.g4();
		bads = new char[local2][];
		badCombinations = new byte[local2][][];
		readBad(badCombinations, bads, arg0);
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(Lclient!kb;I)V")
	private static void readDomain(@OriginalArg(0) Buffer arg0) {
		@Pc(2) int local2 = arg0.g4();
		domains = new char[local2][];
		readDomain(arg0, domains);
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(ILclient!kb;)V")
	private static void readFragments(@OriginalArg(1) Buffer arg0) {
		fragments = new int[arg0.g4()];
		for (@Pc(5) int local5 = 0; local5 < fragments.length; local5++) {
			fragments[local5] = arg0.g2();
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([[[B[[CLclient!kb;B)V")
	private static void readBad(@OriginalArg(0) byte[][][] arg0, @OriginalArg(1) char[][] arg1, @OriginalArg(2) Buffer arg2) {
		for (@Pc(11) int local11 = 0; local11 < arg1.length; local11++) {
			@Pc(17) char[] local17 = new char[arg2.g1()];
			for (@Pc(19) int local19 = 0; local19 < local17.length; local19++) {
				local17[local19] = (char) arg2.g1();
			}
			arg1[local11] = local17;
			@Pc(41) byte[][] local41 = new byte[arg2.g1()][2];
			for (@Pc(43) int local43 = 0; local43 < local41.length; local43++) {
				local41[local43][0] = (byte) arg2.g1();
				local41[local43][1] = (byte) arg2.g1();
			}
			if (local41.length > 0) {
				arg0[local11] = local41;
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(ILclient!kb;[[C)V")
	private static void readDomain(@OriginalArg(1) Buffer arg0, @OriginalArg(2) char[][] arg1) {
		for (@Pc(14) int local14 = 0; local14 < arg1.length; local14++) {
			@Pc(20) char[] local20 = new char[arg0.g1()];
			for (@Pc(22) int local22 = 0; local22 < local20.length; local22++) {
				local20[local22] = (char) arg0.g1();
			}
			arg1[local14] = local20;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CI)V")
	private static void trimWhitespaces(@OriginalArg(0) char[] arg0) {
		@Pc(3) int local3 = 0;
		for (@Pc(5) int local5 = 0; local5 < arg0.length; local5++) {
			if (isValid(arg0[local5])) {
				arg0[local3] = arg0[local5];
			} else {
				arg0[local3] = ' ';
			}
			if (local3 == 0 || arg0[local3] != ' ' || arg0[local3 - 1] != ' ') {
				local3++;
			}
		}
		for (@Pc(55) int local55 = local3; local55 < arg0.length; local55++) {
			arg0[local55] = ' ';
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(IC)Z")
	private static boolean isValid(@OriginalArg(1) char arg0) {
		return arg0 >= ' ' && arg0 <= '\u007f' || arg0 == ' ' || arg0 == '\n' || arg0 == '\t' || arg0 == '£' || arg0 == '€';
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(Ljava/lang/String;I)Ljava/lang/String;")
	public static String getFiltered(@OriginalArg(0) String arg0) {
		@Pc(3) long local3 = System.currentTimeMillis();
		@Pc(6) char[] local6 = arg0.toCharArray();
		trimWhitespaces(local6);
		@Pc(15) String local15 = (new String(local6)).trim();
		@Pc(19) char[] local19 = local15.toLowerCase().toCharArray();
		@Pc(22) String local22 = local15.toLowerCase();
		filterTlds(local19);
		filterBad(local19);
		filterDomains(local19);
		filterNumFragments(local19);
		for (@Pc(36) int local36 = 0; local36 < whitelist.length; local36++) {
			@Pc(45) int local45 = -1;
			while ((local45 = local22.indexOf(whitelist[local36], local45 + 1)) != -1) {
				@Pc(52) char[] local52 = whitelist[local36].toCharArray();
				for (@Pc(54) int local54 = 0; local54 < local52.length; local54++) {
					local19[local54 + local45] = local52[local54];
				}
			}
		}
		replaceUppercases(local19, local15.toCharArray());
		formatUppercases(local19);
		@Pc(105) long local105 = System.currentTimeMillis();
		return (new String(local19)).trim();
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CI[C)V")
	private static void replaceUppercases(@OriginalArg(0) char[] arg0, @OriginalArg(2) char[] arg1) {
		for (@Pc(1) int local1 = 0; local1 < arg1.length; local1++) {
			if (arg0[local1] != '*' && isUppercaseAlpha(arg1[local1])) {
				arg0[local1] = arg1[local1];
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(B[C)V")
	private static void formatUppercases(@OriginalArg(1) char[] arg0) {
		@Pc(3) boolean local3 = true;
		for (@Pc(13) int local13 = 0; local13 < arg0.length; local13++) {
			@Pc(19) char local19 = arg0[local13];
			if (!isAlpha(local19)) {
				local3 = true;
			} else if (local3) {
				if (isLowercaseAlpha(local19)) {
					local3 = false;
				}
			} else if (isUppercaseAlpha(local19)) {
				arg0[local13] = (char) (local19 + 'a' - 65);
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(Z[C)V")
	private static void filterBad(@OriginalArg(1) char[] arg0) {
		for (@Pc(3) int local3 = 0; local3 < 2; local3++) {
			for (@Pc(10) int local10 = bads.length - 1; local10 >= 0; local10--) {
				filterBad(badCombinations[local10], arg0, bads[local10]);
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(B[C)V")
	private static void filterDomains(@OriginalArg(1) char[] arg0) {
		@Pc(3) char[] local3 = (char[]) arg0.clone();
		@Pc(18) char[] local18 = new char[] { '(', 'a', ')' };
		filterBad(null, local3, local18);
		@Pc(27) char[] local27 = (char[]) arg0.clone();
		@Pc(42) char[] local42 = new char[] { 'd', 'o', 't' };
		filterBad(null, local27, local42);
		for (@Pc(56) int local56 = domains.length - 1; local56 >= 0; local56--) {
			filterDomain(local27, local3, domains[local56], arg0);
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CI[C[C[C)V")
	private static void filterDomain(@OriginalArg(0) char[] arg0, @OriginalArg(2) char[] arg1, @OriginalArg(3) char[] arg2, @OriginalArg(4) char[] arg3) {
		if (arg2.length > arg3.length) {
			return;
		}
		@Pc(23) int local23;
		for (@Pc(15) int local15 = 0; local15 <= arg3.length - arg2.length; local15 += local23) {
			@Pc(19) int local19 = local15;
			@Pc(21) int local21 = 0;
			local23 = 1;
			label55: while (true) {
				while (true) {
					if (local19 >= arg3.length) {
						break label55;
					}
					@Pc(31) char local31 = arg3[local19];
					@Pc(33) char local33 = 0;
					if (local19 + 1 < arg3.length) {
						local33 = arg3[local19 + 1];
					}
					@Pc(58) int local58;
					if (local21 < arg2.length && (local58 = getEmulatedDomainCharLen(local33, arg2[local21], local31)) > 0) {
						local19 += local58;
						local21++;
					} else {
						if (local21 == 0) {
							break label55;
						}
						@Pc(79) int local79;
						if ((local79 = getEmulatedDomainCharLen(local33, arg2[local21 - 1], local31)) > 0) {
							local19 += local79;
							if (local21 == 1) {
								local23++;
							}
						} else {
							if (local21 >= arg2.length || !isSymbol(local31)) {
								break label55;
							}
							local19++;
						}
					}
				}
			}
			if (local21 >= arg2.length) {
				@Pc(110) boolean local110 = false;
				@Pc(116) int local116 = getDomainAtFilterStatus(local15, arg3, arg1);
				@Pc(124) int local124 = getDomainDotFilterStatus(arg0, arg3, local19 - 1);
				if (local116 > 2 || local124 > 2) {
					local110 = true;
				}
				if (local110) {
					for (@Pc(136) int local136 = local15; local136 < local19; local136++) {
						arg3[local136] = '*';
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(I[CB[C)I")
	private static int getDomainAtFilterStatus(@OriginalArg(0) int arg0, @OriginalArg(1) char[] arg1, @OriginalArg(3) char[] arg2) {
		if (arg0 == 0) {
			return 2;
		}
		for (@Pc(9) int local9 = arg0 - 1; local9 >= 0 && isSymbol(arg1[local9]); local9--) {
			if (arg1[local9] == '@') {
				return 3;
			}
		}
		@Pc(38) int local38 = 0;
		for (@Pc(42) int local42 = arg0 - 1; local42 >= 0 && isSymbol(arg2[local42]); local42--) {
			if (arg2[local42] == '*') {
				local38++;
			}
		}
		if (local38 >= 3) {
			return 4;
		} else if (isSymbol(arg1[arg0 - 1])) {
			return 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([C[CII)I")
	private static int getDomainDotFilterStatus(@OriginalArg(0) char[] arg0, @OriginalArg(1) char[] arg1, @OriginalArg(2) int arg2) {
		if (arg2 + 1 == arg1.length) {
			return 2;
		}
		@Pc(17) int local17 = arg2 + 1;
		while (true) {
			if (local17 < arg1.length && isSymbol(arg1[local17])) {
				if (arg1[local17] != '.' && arg1[local17] != ',') {
					local17++;
					continue;
				}
				return 3;
			}
			@Pc(44) int local44 = 0;
			for (@Pc(48) int local48 = arg2 + 1; local48 < arg1.length && isSymbol(arg0[local48]); local48++) {
				if (arg0[local48] == '*') {
					local44++;
				}
			}
			if (local44 >= 3) {
				return 4;
			}
			if (isSymbol(arg1[arg2 + 1])) {
				return 1;
			}
			return 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "([CI)V")
	private static void filterTlds(@OriginalArg(0) char[] arg0) {
		@Pc(3) char[] local3 = (char[]) arg0.clone();
		@Pc(18) char[] local18 = new char[] { 'd', 'o', 't' };
		filterBad(null, local3, local18);
		@Pc(27) char[] local27 = (char[]) arg0.clone();
		@Pc(50) char[] local50 = new char[] { 's', 'l', 'a', 's', 'h' };
		filterBad(null, local27, local50);
		for (@Pc(65) int local65 = 0; local65 < tlds.length; local65++) {
			filterTld(local27, tldTypes[local65], arg0, tlds[local65], local3);
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CIZ[C[C[C)V")
	private static void filterTld(@OriginalArg(0) char[] arg0, @OriginalArg(1) int arg1, @OriginalArg(3) char[] arg2, @OriginalArg(4) char[] arg3, @OriginalArg(5) char[] arg4) {
		if (arg3.length > arg2.length) {
			return;
		}
		@Pc(28) int local28;
		for (@Pc(20) int local20 = 0; local20 <= arg2.length - arg3.length; local20 += local28) {
			@Pc(24) int local24 = local20;
			@Pc(26) int local26 = 0;
			local28 = 1;
			label117: while (true) {
				while (true) {
					if (local24 >= arg2.length) {
						break label117;
					}
					@Pc(36) char local36 = arg2[local24];
					@Pc(38) char local38 = 0;
					if (local24 + 1 < arg2.length) {
						local38 = arg2[local24 + 1];
					}
					@Pc(63) int local63;
					if (local26 < arg3.length && (local63 = getEmulatedDomainCharLen(local38, arg3[local26], local36)) > 0) {
						local24 += local63;
						local26++;
					} else {
						if (local26 == 0) {
							break label117;
						}
						@Pc(84) int local84;
						if ((local84 = getEmulatedDomainCharLen(local38, arg3[local26 - 1], local36)) > 0) {
							local24 += local84;
							if (local26 == 1) {
								local28++;
							}
						} else {
							if (local26 >= arg3.length || !isSymbol(local36)) {
								break label117;
							}
							local24++;
						}
					}
				}
			}
			if (local26 >= arg3.length) {
				@Pc(115) boolean local115 = false;
				@Pc(121) int local121 = getTldDotFilterStatus(arg2, arg4, local20);
				@Pc(129) int local129 = getTldSlashFilterStatus(arg0, local24 - 1, arg2);
				if (arg1 == 1 && local121 > 0 && local129 > 0) {
					local115 = true;
				}
				if (arg1 == 2 && (local121 > 2 && local129 > 0 || local121 > 0 && local129 > 2)) {
					local115 = true;
				}
				if (arg1 == 3 && local121 > 0 && local129 > 2) {
					local115 = true;
				}
				@Pc(172) boolean local172;
				if (arg1 == 3 && local121 > 2 && local129 > 0) {
					local172 = true;
				} else {
					local172 = false;
				}
				if (local115) {
					@Pc(179) int local179 = local20;
					@Pc(183) int local183 = local24 - 1;
					@Pc(191) boolean local191;
					@Pc(195) int local195;
					if (local121 > 2) {
						if (local121 == 4) {
							local191 = false;
							for (local195 = local20 - 1; local195 >= 0; local195--) {
								if (local191) {
									if (arg4[local195] != '*') {
										break;
									}
									local179 = local195;
								} else if (arg4[local195] == '*') {
									local179 = local195;
									local191 = true;
								}
							}
						}
						local191 = false;
						for (local195 = local179 - 1; local195 >= 0; local195--) {
							if (local191) {
								if (isSymbol(arg2[local195])) {
									break;
								}
								local179 = local195;
							} else if (!isSymbol(arg2[local195])) {
								local191 = true;
								local179 = local195;
							}
						}
					}
					if (local129 > 2) {
						if (local129 == 4) {
							local191 = false;
							for (local195 = local183 + 1; local195 < arg2.length; local195++) {
								if (local191) {
									if (arg0[local195] != '*') {
										break;
									}
									local183 = local195;
								} else if (arg0[local195] == '*') {
									local183 = local195;
									local191 = true;
								}
							}
						}
						local191 = false;
						for (local195 = local183 + 1; local195 < arg2.length; local195++) {
							if (local191) {
								if (isSymbol(arg2[local195])) {
									break;
								}
								local183 = local195;
							} else if (!isSymbol(arg2[local195])) {
								local191 = true;
								local183 = local195;
							}
						}
					}
					for (@Pc(329) int local329 = local179; local329 <= local183; local329++) {
						arg2[local329] = '*';
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CZ[CI)I")
	private static int getTldDotFilterStatus(@OriginalArg(0) char[] arg0, @OriginalArg(2) char[] arg1, @OriginalArg(3) int arg2) {
		if (arg2 == 0) {
			return 2;
		}
		@Pc(9) int local9 = arg2 - 1;
		while (true) {
			if (local9 >= 0 && isSymbol(arg0[local9])) {
				if (arg0[local9] != ',' && arg0[local9] != '.') {
					local9--;
					continue;
				}
				return 3;
			}
			@Pc(34) int local34 = 0;
			for (@Pc(47) int local47 = arg2 - 1; local47 >= 0 && isSymbol(arg1[local47]); local47--) {
				if (arg1[local47] == '*') {
					local34++;
				}
			}
			if (local34 >= 3) {
				return 4;
			}
			if (isSymbol(arg0[arg2 - 1])) {
				return 1;
			}
			return 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CII[C)I")
	private static int getTldSlashFilterStatus(@OriginalArg(0) char[] arg0, @OriginalArg(2) int arg1, @OriginalArg(3) char[] arg2) {
		if (arg1 + 1 == arg2.length) {
			return 2;
		}
		@Pc(13) int local13 = arg1 + 1;
		while (true) {
			if (local13 < arg2.length && isSymbol(arg2[local13])) {
				if (arg2[local13] != '\\' && arg2[local13] != '/') {
					local13++;
					continue;
				}
				return 3;
			}
			@Pc(40) int local40 = 0;
			for (@Pc(44) int local44 = arg1 + 1; local44 < arg2.length && isSymbol(arg0[local44]); local44++) {
				if (arg0[local44] == '*') {
					local40++;
				}
			}
			if (local40 >= 5) {
				return 4;
			}
			if (isSymbol(arg2[arg1 + 1])) {
				return 1;
			}
			return 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(B[[B[C[C)V")
	private static void filterBad(@OriginalArg(1) byte[][] arg0, @OriginalArg(2) char[] arg1, @OriginalArg(3) char[] arg2) {
		if (arg2.length > arg1.length) {
			return;
		}
		@Pc(26) int local26;
		for (@Pc(16) int local16 = 0; local16 <= arg1.length - arg2.length; local16 += local26) {
			@Pc(20) int local20 = local16;
			@Pc(22) int local22 = 0;
			@Pc(24) int local24 = 0;
			local26 = 1;
			@Pc(28) boolean local28 = false;
			@Pc(30) boolean local30 = false;
			@Pc(32) boolean local32 = false;
			@Pc(40) char var12;
			@Pc(42) char var13;
			label156: while (true) {
				while (true) {
					if (local20 >= arg1.length || local30 && local32) {
						break label156;
					}
					var12 = arg1[local20];
					var13 = '\u0000';
					if (local20 + 1 < arg1.length) {
						var13 = arg1[local20 + 1];
					}
					@Pc(67) int local67;
					if (local22 < arg2.length && (local67 = getEmulatedBadCharLen(var13, arg2[local22], var12)) > 0) {
						if (local67 == 1 && isNumeral(var12)) {
							local30 = true;
						}
						if (local67 == 2 && (isNumeral(var12) || isNumeral(var13))) {
							local30 = true;
						}
						local20 += local67;
						local22++;
					} else {
						if (local22 == 0) {
							break label156;
						}
						@Pc(110) int local110;
						if ((local110 = getEmulatedBadCharLen(var13, arg2[local22 - 1], var12)) > 0) {
							local20 += local110;
							if (local22 == 1) {
								local26++;
							}
						} else {
							if (local22 >= arg2.length || !isNotLowercaseAlpha(var12)) {
								break label156;
							}
							if (isSymbol(var12) && var12 != '\'') {
								local28 = true;
							}
							if (isNumeral(var12)) {
								local32 = true;
							}
							local20++;
							local24++;
							if (local24 * 100 / (local20 - local16) > 90) {
								break label156;
							}
						}
					}
				}
			}
			if (local22 >= arg2.length && (!local30 || !local32)) {
				@Pc(175) boolean local175 = true;
				@Pc(271) int local271;
				if (local28) {
					@Pc(221) boolean local221 = false;
					@Pc(223) boolean local223 = false;
					if (local16 - 1 < 0 || isSymbol(arg1[local16 - 1]) && arg1[local16 - 1] != '\'') {
						local221 = true;
					}
					if (local20 >= arg1.length || isSymbol(arg1[local20]) && arg1[local20] != '\'') {
						local223 = true;
					}
					if (!local221 || !local223) {
						@Pc(267) boolean local267 = false;
						local271 = local16 - 2;
						if (local221) {
							local271 = local16;
						}
						while (!local267 && local271 < local20) {
							if (local271 >= 0 && (!isSymbol(arg1[local271]) || arg1[local271] == '\'')) {
								@Pc(293) char[] local293 = new char[3];
								@Pc(295) int local295;
								for (local295 = 0; local295 < 3 && local271 + local295 < arg1.length && (!isSymbol(arg1[local271 + local295]) || arg1[local271 + local295] == '\''); local295++) {
									local293[local295] = arg1[local271 + local295];
								}
								@Pc(333) boolean local333 = true;
								if (local295 == 0) {
									local333 = false;
								}
								if (local295 < 3 && local271 - 1 >= 0 && (!isSymbol(arg1[local271 - 1]) || arg1[local271 - 1] == '\'')) {
									local333 = false;
								}
								if (local333 && !isBadFragment(local293)) {
									local267 = true;
								}
							}
							local271++;
						}
						if (!local267) {
							local175 = false;
						}
					}
				} else {
					var12 = ' ';
					if (local16 - 1 >= 0) {
						var12 = arg1[local16 - 1];
					}
					var13 = ' ';
					if (local20 < arg1.length) {
						var13 = arg1[local20];
					}
					@Pc(203) byte local203 = getIndex(var12);
					@Pc(207) byte local207 = getIndex(var13);
					if (arg0 != null && comboMatches(local203, arg0, local207)) {
						local175 = false;
					}
				}
				if (local175) {
					@Pc(383) int local383 = 0;
					@Pc(385) int local385 = 0;
					for (@Pc(387) int local387 = local16; local387 < local20; local387++) {
						if (isNumeral(arg1[local387])) {
							local383++;
						} else if (isAlpha(arg1[local387])) {
							local385++;
						}
					}
					if (local383 <= local385) {
						for (local271 = local16; local271 < local20; local271++) {
							arg1[local271] = '*';
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(IB[[BB)Z")
	private static boolean comboMatches(@OriginalArg(1) byte arg0, @OriginalArg(2) byte[][] arg1, @OriginalArg(3) byte arg2) {
		@Pc(9) int local9 = 0;
		if (arg1[0][0] == arg0 && arg1[0][1] == arg2) {
			return true;
		}
		@Pc(30) int local30 = arg1.length - 1;
		if (arg1[local30][0] == arg0 && arg1[local30][1] == arg2) {
			return true;
		}
		do {
			@Pc(52) int local52 = (local9 + local30) / 2;
			if (arg1[local52][0] == arg0 && arg1[local52][1] == arg2) {
				return true;
			}
			if (arg0 < arg1[local52][0] || arg0 == arg1[local52][0] && arg2 < arg1[local52][1]) {
				local30 = local52;
			} else {
				local9 = local52;
			}
		} while (local9 != local30 && local9 + 1 != local30);
		return false;
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(ICCC)I")
	private static int getEmulatedDomainCharLen(@OriginalArg(1) char arg0, @OriginalArg(2) char arg1, @OriginalArg(3) char arg2) {
		if (arg1 == arg2) {
			return 1;
		} else if (arg1 == 'o' && arg2 == '0') {
			return 1;
		} else if (arg1 == 'o' && arg2 == '(' && arg0 == ')') {
			return 2;
		} else if (arg1 == 'c' && (arg2 == '(' || arg2 == '<' || arg2 == '[')) {
			return 1;
		} else if (arg1 == 'e' && arg2 == '€') {
			return 1;
		} else if (arg1 == 's' && arg2 == '$') {
			return 1;
		} else if (arg1 == 'l' && arg2 == 'i') {
			return 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(CCCI)I")
	private static int getEmulatedBadCharLen(@OriginalArg(0) char arg0, @OriginalArg(1) char arg1, @OriginalArg(2) char arg2) {
		if (arg1 == arg2) {
			return 1;
		}
		if (arg1 >= 'a' && arg1 <= 'm') {
			if (arg1 == 'a') {
				if (arg2 != '4' && arg2 != '@' && arg2 != '^') {
					if (arg2 == '/' && arg0 == '\\') {
						return 2;
					}
					return 0;
				}
				return 1;
			}
			if (arg1 == 'b') {
				if (arg2 != '6' && arg2 != '8') {
					if (arg2 == '1' && arg0 == '3') {
						return 2;
					}
					return 0;
				}
				return 1;
			}
			if (arg1 == 'c') {
				if (arg2 != '(' && arg2 != '<' && arg2 != '{' && arg2 != '[') {
					return 0;
				}
				return 1;
			}
			if (arg1 == 'd') {
				if (arg2 == '[' && arg0 == ')') {
					return 2;
				}
				return 0;
			}
			if (arg1 == 'e') {
				if (arg2 != '3' && arg2 != '€') {
					return 0;
				}
				return 1;
			}
			if (arg1 == 'f') {
				if (arg2 == 'p' && arg0 == 'h') {
					return 2;
				}
				if (arg2 == '£') {
					return 1;
				}
				return 0;
			}
			if (arg1 == 'g') {
				if (arg2 != '9' && arg2 != '6') {
					return 0;
				}
				return 1;
			}
			if (arg1 == 'h') {
				if (arg2 == '#') {
					return 1;
				}
				return 0;
			}
			if (arg1 == 'i') {
				if (arg2 != 'y' && arg2 != 'l' && arg2 != 'j' && arg2 != '1' && arg2 != '!' && arg2 != ':' && arg2 != ';' && arg2 != '|') {
					return 0;
				}
				return 1;
			}
			if (arg1 == 'j') {
				return 0;
			}
			if (arg1 == 'k') {
				return 0;
			}
			if (arg1 == 'l') {
				if (arg2 != '1' && arg2 != '|' && arg2 != 'i') {
					return 0;
				}
				return 1;
			}
			if (arg1 == 'm') {
				return 0;
			}
		}
		if (arg1 >= 'n' && arg1 <= 'z') {
			if (arg1 == 'n') {
				return 0;
			}
			if (arg1 == 'o') {
				if (arg2 != '0' && arg2 != '*') {
					if ((arg2 != '(' || arg0 != ')') && (arg2 != '[' || arg0 != ']') && (arg2 != '{' || arg0 != '}') && (arg2 != '<' || arg0 != '>')) {
						return 0;
					}
					return 2;
				}
				return 1;
			}
			if (arg1 == 'p') {
				return 0;
			}
			if (arg1 == 'q') {
				return 0;
			}
			if (arg1 == 'r') {
				return 0;
			}
			if (arg1 == 's') {
				if (arg2 != '5' && arg2 != 'z' && arg2 != '$' && arg2 != '2') {
					return 0;
				}
				return 1;
			}
			if (arg1 == 't') {
				if (arg2 != '7' && arg2 != '+') {
					return 0;
				}
				return 1;
			}
			if (arg1 == 'u') {
				if (arg2 == 'v') {
					return 1;
				}
				if ((arg2 != '\\' || arg0 != '/') && (arg2 != '\\' || arg0 != '|') && (arg2 != '|' || arg0 != '/')) {
					return 0;
				}
				return 2;
			}
			if (arg1 == 'v') {
				if ((arg2 != '\\' || arg0 != '/') && (arg2 != '\\' || arg0 != '|') && (arg2 != '|' || arg0 != '/')) {
					return 0;
				}
				return 2;
			}
			if (arg1 == 'w') {
				if (arg2 == 'v' && arg0 == 'v') {
					return 2;
				}
				return 0;
			}
			if (arg1 == 'x') {
				if ((arg2 != ')' || arg0 != '(') && (arg2 != '}' || arg0 != '{') && (arg2 != ']' || arg0 != '[') && (arg2 != '>' || arg0 != '<')) {
					return 0;
				}
				return 2;
			}
			if (arg1 == 'y') {
				return 0;
			}
			if (arg1 == 'z') {
				return 0;
			}
		}
		if (arg1 >= '0' && arg1 <= '9') {
			if (arg1 == '0') {
				if (arg2 == 'o' || arg2 == 'O') {
					return 1;
				} else if ((arg2 != '(' || arg0 != ')') && (arg2 != '{' || arg0 != '}') && (arg2 != '[' || arg0 != ']')) {
					return 0;
				} else {
					return 2;
				}
			} else if (arg1 == '1') {
				return arg2 == 'l' ? 1 : 0;
			} else {
				return 0;
			}
		} else if (arg1 == ',') {
			return arg2 == '.' ? 1 : 0;
		} else if (arg1 == '.') {
			return arg2 == ',' ? 1 : 0;
		} else if (arg1 == '!') {
			return arg2 == 'i' ? 1 : 0;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(IC)B")
	private static byte getIndex(@OriginalArg(1) char arg0) {
		if (arg0 >= 'a' && arg0 <= 'z') {
			return (byte) (arg0 + 1 - 'a');
		} else if (arg0 == '\'') {
			return 28;
		} else if (arg0 >= '0' && arg0 <= '9') {
			return (byte) (arg0 + 29 - '0');
		} else {
			return 27;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(I[C)V")
	private static void filterNumFragments(@OriginalArg(1) char[] arg0) {
		@Pc(5) int local5 = 0;
		@Pc(11) int local11 = 0;
		@Pc(13) int local13 = 0;
		while (true) {
			do {
				@Pc(112) int local112;
				if ((local112 = indexOfNumber(arg0, local5)) == -1) {
					return;
				}
				@Pc(17) boolean local17 = false;
				for (@Pc(19) int local19 = local5; local19 >= 0 && local19 < local112 && !local17; local19++) {
					if (!isSymbol(arg0[local19]) && !isNotLowercaseAlpha(arg0[local19])) {
						local17 = true;
					}
				}
				if (local17) {
					local11 = 0;
				}
				if (local11 == 0) {
					local13 = local112;
				}
				local5 = indexOfNonNumber(local112, arg0);
				@Pc(58) int local58 = 0;
				for (@Pc(60) int local60 = local112; local60 < local5; local60++) {
					local58 = local58 * 10 + arg0[local60] - 48;
				}
				if (local58 <= 255 && local5 - local112 <= 8) {
					local11++;
				} else {
					local11 = 0;
				}
			} while (local11 != 4);
			for (@Pc(94) int local94 = local13; local94 < local5; local94++) {
				arg0[local94] = '*';
			}
			local11 = 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(I[CI)I")
	private static int indexOfNumber(@OriginalArg(1) char[] arg0, @OriginalArg(2) int arg1) {
		for (@Pc(5) int local5 = arg1; local5 < arg0.length && local5 >= 0; local5++) {
			if (arg0[local5] >= '0' && arg0[local5] <= '9') {
				return local5;
			}
		}
		return -1;
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(II[C)I")
	private static int indexOfNonNumber(@OriginalArg(1) int arg0, @OriginalArg(2) char[] arg1) {
		@Pc(6) int local6 = arg0;
		while (true) {
			if (local6 < arg1.length && local6 >= 0) {
				if (arg1[local6] >= '0' && arg1[local6] <= '9') {
					local6++;
					continue;
				}
				return local6;
			}
			return arg1.length;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(CI)Z")
	private static boolean isSymbol(@OriginalArg(0) char arg0) {
		return !isAlpha(arg0) && !isNumeral(arg0);
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(CB)Z")
	private static boolean isNotLowercaseAlpha(@OriginalArg(0) char arg0) {
		if (arg0 >= 'a' && arg0 <= 'z') {
			return arg0 == 'v' || arg0 == 'x' || arg0 == 'j' || arg0 == 'q' || arg0 == 'z';
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!mc", name = "c", descriptor = "(IC)Z")
	private static boolean isAlpha(@OriginalArg(1) char arg0) {
		return arg0 >= 'a' && arg0 <= 'z' || arg0 >= 'A' && arg0 <= 'Z';
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(CI)Z")
	private static boolean isNumeral(@OriginalArg(0) char arg0) {
		return arg0 >= '0' && arg0 <= '9';
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(BC)Z")
	private static boolean isLowercaseAlpha(@OriginalArg(1) char arg0) {
		return arg0 >= 'a' && arg0 <= 'z';
	}

	@OriginalMember(owner = "client!mc", name = "d", descriptor = "(IC)Z")
	private static boolean isUppercaseAlpha(@OriginalArg(1) char arg0) {
		return arg0 >= 'A' && arg0 <= 'Z';
	}

	@OriginalMember(owner = "client!mc", name = "c", descriptor = "([CI)Z")
	private static boolean isBadFragment(@OriginalArg(0) char[] arg0) {
		@Pc(3) boolean local3 = true;
		for (@Pc(5) int local5 = 0; local5 < arg0.length; local5++) {
			if (!isNumeral(arg0[local5]) && arg0[local5] != '\u0000') {
				local3 = false;
			}
		}
		if (local3) {
			return true;
		}
		@Pc(32) int local32 = getInteger(arg0);
		@Pc(34) int local34 = 0;
		@Pc(49) int local49 = fragments.length - 1;
		if (local32 == fragments[0] || local32 == fragments[local49]) {
			return true;
		}
		do {
			@Pc(67) int local67 = (local34 + local49) / 2;
			if (local32 == fragments[local67]) {
				return true;
			}
			if (local32 < fragments[local67]) {
				local49 = local67;
			} else {
				local34 = local67;
			}
		} while (local34 != local49 && local34 + 1 != local49);
		return false;
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(I[C)I")
	private static int getInteger(@OriginalArg(1) char[] arg0) {
		if (arg0.length > 6) {
			return 0;
		}
		@Pc(9) int local9 = 0;
		for (@Pc(11) int local11 = 0; local11 < arg0.length; local11++) {
			@Pc(22) char local22 = arg0[arg0.length - local11 - 1];
			if (local22 >= 'a' && local22 <= 'z') {
				local9 = local9 * 38 + local22 + 1 - 'a';
			} else if (local22 == '\'') {
				local9 = local9 * 38 + 27;
			} else if (local22 >= '0' && local22 <= '9') {
				local9 = local9 * 38 + local22 + 28 - '0';
			} else if (local22 != '\u0000') {
				return 0;
			}
		}
		return local9;
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "Z")
	private static boolean flowObfuscator7;

	@OriginalMember(owner = "client!mc", name = "d", descriptor = "I")
	private static int flowObfuscator8;

	@OriginalMember(owner = "client!mc", name = "i", descriptor = "Z")
	private static boolean flowObfuscator9;

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "I")
	private static final int flowObfuscator1 = 24882;

	@OriginalMember(owner = "client!mc", name = "c", descriptor = "I")
	private static final int flowObfuscator2 = -178;

	@OriginalMember(owner = "client!mc", name = "e", descriptor = "I")
	private static final int flowObfuscator3 = 16180;

	@OriginalMember(owner = "client!mc", name = "f", descriptor = "I")
	private static final int flowObfuscator4 = 383;

	@OriginalMember(owner = "client!mc", name = "g", descriptor = "B")
	private static final byte flowObfuscator5 = 6;

	@OriginalMember(owner = "client!mc", name = "h", descriptor = "I")
	private static final int flowObfuscator6 = -81;

}
