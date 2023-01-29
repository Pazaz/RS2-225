package com.jagex.core.stringtools;

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
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(11) Buffer fragments = new Buffer(archive.read("fragmentsenc.txt", null));
		@Pc(21) Buffer bad = new Buffer(archive.read("badenc.txt", null));
		@Pc(31) Buffer domain = new Buffer(archive.read("domainenc.txt", null));
		@Pc(41) Buffer tld = new Buffer(archive.read("tldlist.txt", null));

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
	private static void readTld(@OriginalArg(1) Buffer tld) {
		@Pc(4) int count = tld.g4();
		tlds = new char[count][];
		tldTypes = new int[count];

		for (@Pc(15) int i = 0; i < count; i++) {
			tldTypes[i] = tld.g1();

			@Pc(26) char[] string = new char[tld.g1()];
			for (@Pc(28) int j = 0; j < string.length; j++) {
				string[j] = (char) tld.g1();
			}

			tlds[i] = string;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(ILclient!kb;)V")
	private static void readBad(@OriginalArg(1) Buffer buf) {
		@Pc(2) int count = buf.g4();
		bads = new char[count][];
		badCombinations = new byte[count][][];

		readBad(badCombinations, bads, buf);
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(Lclient!kb;I)V")
	private static void readDomain(@OriginalArg(0) Buffer buf) {
		@Pc(2) int count = buf.g4();
		domains = new char[count][];

		readDomain(buf, domains);
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(ILclient!kb;)V")
	private static void readFragments(@OriginalArg(1) Buffer buf) {
		int count = buf.g4();
		fragments = new int[count];

		for (@Pc(5) int i = 0; i < fragments.length; i++) {
			fragments[i] = buf.g2();
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([[[B[[CLclient!kb;B)V")
	private static void readBad(@OriginalArg(0) byte[][][] badCombinations, @OriginalArg(1) char[][] bads, @OriginalArg(2) Buffer buf) {
		for (@Pc(11) int i = 0; i < bads.length; i++) {
			@Pc(17) char[] chars = new char[buf.g1()];
			for (@Pc(19) int j = 0; j < chars.length; j++) {
				chars[j] = (char) buf.g1();
			}

			bads[i] = chars;

			@Pc(41) byte[][] combo = new byte[buf.g1()][2];
			for (@Pc(43) int k = 0; k < combo.length; k++) {
				combo[k][0] = (byte) buf.g1();
				combo[k][1] = (byte) buf.g1();
			}

			if (combo.length > 0) {
				badCombinations[i] = combo;
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(ILclient!kb;[[C)V")
	private static void readDomain(@OriginalArg(1) Buffer buf, @OriginalArg(2) char[][] domains) {
		for (@Pc(14) int i = 0; i < domains.length; i++) {
			@Pc(20) char[] string = new char[buf.g1()];
			for (@Pc(22) int j = 0; j < string.length; j++) {
				string[j] = (char) buf.g1();
			}

			domains[i] = string;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CI)V")
	private static void trimWhitespaces(@OriginalArg(0) char[] chars) {
		@Pc(3) int off = 0;
		for (@Pc(5) int i = 0; i < chars.length; i++) {
			if (isValid(chars[i])) {
				chars[off] = chars[i];
			} else {
				chars[off] = ' ';
			}

			if (off == 0 || chars[off] != ' ' || chars[off - 1] != ' ') {
				off++;
			}
		}

		for (@Pc(55) int i = off; i < chars.length; i++) {
			chars[i] = ' ';
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(IC)Z")
	private static boolean isValid(@OriginalArg(1) char c) {
		return c >= ' ' && c <= 127 || c == '\n' || c == '\t' || c == '£' || c == '€';
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(Ljava/lang/String;I)Ljava/lang/String;")
	public static String getFiltered(@OriginalArg(0) String str) {
		// @Pc(3) long start = System.currentTimeMillis();
		@Pc(6) char[] chars = str.toCharArray();
		trimWhitespaces(chars);

		@Pc(15) String trimmed = (new String(chars)).trim();
		chars = trimmed.toLowerCase().toCharArray();
		@Pc(22) String lower = trimmed.toLowerCase();

		filterTlds(chars);
		filterBad(chars);
		filterDomains(chars);
		filterNumFragments(chars);

		for (@Pc(36) int word = 0; word < whitelist.length; word++) {
			for (@Pc(45) int index = -1; (index = lower.indexOf(whitelist[word], index + 1)) != -1;) {
				@Pc(52) char[] wchars = whitelist[word].toCharArray();
				System.arraycopy(wchars, 0, chars, index, wchars.length);
			}
		}

		replaceUppercases(chars, trimmed.toCharArray());
		formatUppercases(chars);
		// @Pc(105) long end = System.currentTimeMillis();
		return (new String(chars)).trim();
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CI[C)V")
	private static void replaceUppercases(@OriginalArg(0) char[] to, @OriginalArg(2) char[] from) {
		for (@Pc(1) int i = 0; i < from.length; i++) {
			if (to[i] != '*' && isUppercaseAlpha(from[i])) {
				to[i] = from[i];
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(B[C)V")
	private static void formatUppercases(@OriginalArg(1) char[] chars) {
		@Pc(3) boolean flag = true;

		for (@Pc(13) int i = 0; i < chars.length; i++) {
			@Pc(19) char c = chars[i];

			if (isAlpha(c)) {
				if (flag) {
					if (isLowercaseAlpha(c)) {
						flag = false;
					}
				} else if (isUppercaseAlpha(c)) {
					chars[i] = (char) (c + 'a' - 65);
				}
			} else {
				flag = true;
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(Z[C)V")
	private static void filterBad(@OriginalArg(1) char[] chars) {
		for (@Pc(3) int i = 0; i < 2; i++) {
			for (@Pc(10) int j = bads.length - 1; j >= 0; j--) {
				filterBad(badCombinations[j], chars, bads[j]);
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(B[C)V")
	private static void filterDomains(@OriginalArg(1) char[] chars) {
		@Pc(3) char[] filteredAts = chars.clone();
		@Pc(18) char[] ac2 = new char[] { '(', 'a', ')' };
		filterBad(null, filteredAts, ac2);

		@Pc(27) char[] filteredDot = (char[]) chars.clone();
		@Pc(42) char[] ac4 = new char[] { 'd', 'o', 't' };
		filterBad(null, filteredDot, ac4);

		for (@Pc(56) int i = domains.length - 1; i >= 0; i--) {
			filterDomain(filteredDot, filteredAts, domains[i], chars);
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CI[C[C[C)V")
	private static void filterDomain(@OriginalArg(0) char[] filteredDot, @OriginalArg(2) char[] filteredAts, @OriginalArg(3) char[] domain, @OriginalArg(4) char[] chars) {
		if (domain.length > chars.length) {
			return;
		}

		@Pc(23) int stride;
		for (@Pc(15) int start = 0; start <= chars.length - domain.length; start += stride) {
			@Pc(19) int end = start;
			@Pc(21) int off = 0;
			stride = 1;

			while (end < chars.length) {
				@Pc(31) char b = chars[end];
				@Pc(33) char c = 0;
				if (end + 1 < chars.length) {
					c = chars[end + 1];
				}

				@Pc(58) int charLen;
				if (off < domain.length && (charLen = getEmulatedDomainCharLen(c, domain[off], b)) > 0) {
					end += charLen;
					off++;
					continue;
				}

				if (off == 0) {
					break;
				}

				if ((charLen = getEmulatedDomainCharLen(c, domain[off - 1], b)) > 0) {
					end += charLen;
					if (off == 1) {
						stride++;
					}

					continue;
				}

				if (off >= domain.length || !isSymbol(b)) {
					break;
				}

				end++;
			}

			if (off >= domain.length) {
				@Pc(110) boolean bad = false;
				@Pc(116) int status0 = getDomainAtFilterStatus(start, chars, filteredAts);
				@Pc(124) int status1 = getDomainDotFilterStatus(filteredDot, chars, end - 1);

				if (status0 > 2 || status1 > 2) {
					bad = true;
				}

				if (bad) {
					for (@Pc(136) int i = start; i < end; i++) {
						chars[i] = '*';
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(I[CB[C)I")
	private static int getDomainAtFilterStatus(@OriginalArg(0) int end, @OriginalArg(1) char[] a, @OriginalArg(3) char[] b) {
		if (end == 0) {
			return 2;
		}

		for (@Pc(9) int i = end - 1; i >= 0 && isSymbol(a[i]); i--) {
			if (a[i] == '@') {
				return 3;
			}
		}

		@Pc(38) int asteriskCount = 0;
		for (@Pc(42) int i = end - 1; i >= 0 && isSymbol(b[i]); i--) {
			if (b[i] == '*') {
				asteriskCount++;
			}
		}

		if (asteriskCount >= 3) {
			return 4;
		}

		return isSymbol(a[end - 1]) ? 1 : 0;
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([C[CII)I")
	private static int getDomainDotFilterStatus(@OriginalArg(0) char[] b, @OriginalArg(1) char[] a, @OriginalArg(2) int start) {
		if (start + 1 == a.length) {
			return 2;
		}

		for (@Pc(17) int i = start + 1; i < a.length && isSymbol(a[i]); i++) {
			if (a[i] == '.' || a[i] != ',') {
				return 3;
			}
		}

		@Pc(44) int asteriskCount = 0;
		for (@Pc(48) int j = start + 1; j < a.length && isSymbol(b[j]); j++) {
			if (b[j] == '*') {
				asteriskCount++;
			}
		}

		if (asteriskCount >= 3) {
			return 4;
		}

		return isSymbol(a[start + 1]) ? 1 : 0;
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "([CI)V")
	private static void filterTlds(@OriginalArg(0) char[] chars) {
		@Pc(3) char[] filteredDot = chars.clone();
		@Pc(18) char[] ac2 = new char[] { 'd', 'o', 't' };
		filterBad(null, filteredDot, ac2);

		@Pc(27) char[] filteredSlash = chars.clone();
		@Pc(50) char[] ac4 = new char[] { 's', 'l', 'a', 's', 'h' };
		filterBad(null, filteredSlash, ac4);

		for (@Pc(65) int i = 0; i < tlds.length; i++) {
			filterTld(filteredSlash, tldTypes[i], chars, tlds[i], filteredDot);
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CIZ[C[C[C)V")
	private static void filterTld(@OriginalArg(0) char[] filteredSlash, @OriginalArg(1) int i, @OriginalArg(3) char[] chars, @OriginalArg(4) char[] tld, @OriginalArg(5) char[] filteredDot) {
		if (tld.length > chars.length) {
			return;
		}

		@Pc(28) int stride;
		for (@Pc(20) int start = 0; start <= chars.length - tld.length; start += stride) {
			@Pc(24) int end = start;
			@Pc(26) int off = 0;
			stride = 1;

			while (end < chars.length) {
				@Pc(36) char b = chars[end];
				@Pc(38) char c = 0;
				if (end + 1 < chars.length) {
					c = chars[end + 1];
				}

				@Pc(63) int charLen;
				if (off < tld.length && (charLen = getEmulatedDomainCharLen(c, tld[off], b)) > 0) {
					end += charLen;
					off++;
					continue;
				}

				if (off == 0) {
					break;
				}

				if ((charLen = getEmulatedDomainCharLen(c, tld[off - 1], b)) > 0) {
					end += charLen;

					if (off == 1) {
						stride++;
					}

					continue;
				}

				if (off >= tld.length || !isSymbol(b)) {
					break;
				}

				end++;
			}

			if (off >= tld.length) {
				@Pc(115) boolean bad = false;
				@Pc(121) int status0 = getTldDotFilterStatus(chars, filteredDot, start);
				@Pc(129) int status1 = getTldSlashFilterStatus(filteredSlash, end - 1, chars);

				if (i == 1 && status0 > 0 && status1 > 0) {
					bad = true;
				} else if (i == 2 && (status0 > 2 && status1 > 0 || status0 > 0 && status1 > 2)) {
					bad = true;
				} else if (i == 3 && status0 > 0 && status1 > 2) {
					bad = true;
				}

				/*@Pc(172) boolean local172;
				if (i == 3 && status0 > 2 && status1 > 0) {
					local172 = true;
				} else {
					local172 = false;
				}*/

				if (bad) {
					@Pc(179) int first = start;
					@Pc(183) int last = end - 1;

					if (status0 > 2) {
						if (status0 == 4) {
							boolean findStart = false;

							for (int n = start - 1; n >= 0; n--) {
								if (findStart) {
									if (filteredDot[n] != '*') {
										break;
									}

									first = n;
								} else if (filteredDot[n] == '*') {
									first = n;
									findStart = true;
								}
							}
						}

						boolean findStart = false;
						for (int n = first - 1; n >= 0; n--) {
							if (findStart) {
								if (isSymbol(chars[n])) {
									break;
								}

								first = n;
							} else if (!isSymbol(chars[n])) {
								findStart = true;
								first = n;
							}
						}
					}

					if (status1 > 2) {
						if (status1 == 4) {
							boolean findStart = false;
							for (int n = last + 1; n < chars.length; n++) {
								if (findStart) {
									if (filteredSlash[n] != '*') {
										break;
									}

									last = n;
								} else if (filteredSlash[n] == '*') {
									last = n;
									findStart = true;
								}
							}
						}

						boolean findStart = false;
						for (int n = last + 1; n < chars.length; n++) {
							if (findStart) {
								if (isSymbol(chars[n])) {
									break;
								}

								last = n;
							} else if (!isSymbol(chars[n])) {
								findStart = true;
								last = n;
							}
						}
					}

					for (@Pc(329) int j = first; j <= last; j++) {
						chars[j] = '*';
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CZ[CI)I")
	private static int getTldDotFilterStatus(@OriginalArg(0) char[] chars, @OriginalArg(2) char[] filteredDot, @OriginalArg(3) int start) {
		if (start == 0) {
			return 2;
		}

		for (int i = start - 1; i >= 0; i--) {
			if (!isSymbol(chars[i])) {
				break;
			}

			if (chars[i] == ',' || chars[i] == '.') {
				return 3;
			}
		}

		@Pc(34) int asteriskCount = 0;
		for (@Pc(47) int i = start - 1; i >= 0 && isSymbol(filteredDot[i]); i--) {
			if (filteredDot[i] == '*') {
				asteriskCount++;
			}
		}

		if (asteriskCount >= 3) {
			return 4;
		}

		return isSymbol(chars[start - 1]) ? 1 : 0;
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "([CII[C)I")
	private static int getTldSlashFilterStatus(@OriginalArg(0) char[] filteredSlash, @OriginalArg(2) int end, @OriginalArg(3) char[] chars) {
		if (end + 1 == chars.length) {
			return 2;
		}

		for (int i = end + 1; i < chars.length; i++) {
			if (!isSymbol(chars[i])) {
				break;
			}

			if (chars[i] == '\\' || chars[i] == '/') {
				return 3;
			}
		}

		@Pc(40) int asteriskCount = 0;
		for (@Pc(44) int i = end + 1; i < chars.length && isSymbol(filteredSlash[i]); i++) {
			if (filteredSlash[i] == '*') {
				asteriskCount++;
			}
		}

		if (asteriskCount >= 5) {
			return 4;
		}

		return isSymbol(chars[end + 1]) ? 1 : 0;
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(B[[B[C[C)V")
	private static void filterBad(@OriginalArg(1) byte[][] badCombinations, @OriginalArg(2) char[] chars, @OriginalArg(3) char[] fragment) {
		if (fragment.length > chars.length) {
			return;
		}

		@Pc(26) int stride;
		for (@Pc(16) int start = 0; start <= chars.length - fragment.length; start += stride) {
			@Pc(20) int end = start;
			@Pc(22) int fragOff = 0;
			@Pc(24) int iterations = 0;
			stride = 1;

			@Pc(28) boolean isSymbol = false;
			@Pc(30) boolean isEmulated = false;
			@Pc(32) boolean isNumeral = false;

			@Pc(40) char b;
			@Pc(42) char c;
			while (end < chars.length && (!isEmulated || !isNumeral)) {
				b = chars[end];
				c = 0;

				if (end + 1 < chars.length) {
					c = chars[end + 1];
				}

				@Pc(67) int charLen;
				if (fragOff < fragment.length && (charLen = getEmulatedBadCharLen(c, fragment[fragOff], b)) > 0) {
					if (charLen == 1 && isNumeral(b)) {
						isEmulated = true;
					}

					if (charLen == 2 && (isNumeral(b) || isNumeral(c))) {
						isEmulated = true;
					}

					end += charLen;
					fragOff++;
					continue;
				}

				if (fragOff == 0) {
					break;
				}

				if ((charLen = getEmulatedBadCharLen(c, fragment[fragOff - 1], b)) > 0) {
					end += charLen;

					if (fragOff == 1) {
						stride++;
					}

					continue;
				}

				if (fragOff >= fragment.length || !isNotLowercaseAlpha(b)) {
					break;
				}

				if (isSymbol(b) && b != '\'') {
					isSymbol = true;
				}

				if (isNumeral(b)) {
					isNumeral = true;
				}

				end++;
				iterations++;

				if (iterations * 100 / (end - start) > 90) {
					break;
				}
			}

			if (fragOff >= fragment.length && (!isEmulated || !isNumeral)) {
				@Pc(175) boolean bad = true;

				if (isSymbol) {
					@Pc(221) boolean badCurrent = false;
					@Pc(223) boolean badNext = false;

					if (start - 1 < 0 || isSymbol(chars[start - 1]) && chars[start - 1] != '\'') {
						badCurrent = true;
					}

					if (end >= chars.length || isSymbol(chars[end]) && chars[end] != '\'') {
						badNext = true;
					}

					if (!badCurrent || !badNext) {
						@Pc(267) boolean good = false;
						@Pc(271) int cur = start - 2;
						if (badCurrent) {
							cur = start;
						}

						for (; !good && cur < end; cur++) {
							if (cur >= 0 && (!isSymbol(chars[cur]) || chars[cur] == '\'')) {
								@Pc(293) char[] frag = new char[3];
								@Pc(295) int off;
								for (off = 0; off < 3 && cur + off < chars.length && (!isSymbol(chars[cur + off]) || chars[cur + off] == '\''); off++) {
									frag[off] = chars[cur + off];
								}

								@Pc(333) boolean valid = off != 0;
								if (off < 3 && cur - 1 >= 0 && (!isSymbol(chars[cur - 1]) || chars[cur - 1] == '\'')) {
									valid = false;
								}

								if (valid && !isBadFragment(frag)) {
									good = true;
								}
							}
						}

						if (!good) {
							bad = false;
						}
					}
				} else {
					b = ' ';
					if (start - 1 >= 0) {
						b = chars[start - 1];
					}

					c = ' ';
					if (end < chars.length) {
						c = chars[end];
					}

					if (badCombinations != null && comboMatches(getIndex(b), badCombinations, getIndex(c))) {
						bad = false;
					}
				}

				if (bad) {
					@Pc(383) int numeralCount = 0;
					@Pc(385) int alphaCount = 0;

					for (@Pc(387) int i = start; i < end; i++) {
						if (isNumeral(chars[i])) {
							numeralCount++;
						} else if (isAlpha(chars[i])) {
							alphaCount++;
						}
					}

					if (numeralCount <= alphaCount) {
						for (int i = start; i < end; i++) {
							chars[i] = '*';
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(IB[[BB)Z")
	private static boolean comboMatches(@OriginalArg(1) byte a, @OriginalArg(2) byte[][] combos, @OriginalArg(3) byte b) {
		@Pc(9) int first = 0;
		if (combos[0][0] == a && combos[0][1] == b) {
			return true;
		}

		@Pc(30) int last = combos.length - 1;
		if (combos[last][0] == a && combos[last][1] == b) {
			return true;
		}

		do {
			@Pc(52) int middle = (first + last) / 2;
			if (combos[middle][0] == a && combos[middle][1] == b) {
				return true;
			}

			if (a < combos[middle][0] || a == combos[middle][0] && b < combos[middle][1]) {
				last = middle;
			} else {
				first = middle;
			}
		} while (first != last && first + 1 != last);

		return false;
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(ICCC)I")
	private static int getEmulatedDomainCharLen(@OriginalArg(1) char c, @OriginalArg(2) char a, @OriginalArg(3) char b) {
		if (a == b) {
			return 1;
		} else if (a == 'o' && b == '0') {
			return 1;
		} else if (a == 'o' && b == '(' && c == ')') {
			return 2;
		} else if (a == 'c' && (b == '(' || b == '<' || b == '[')) {
			return 1;
		} else if (a == 'e' && b == '€') {
			return 1;
		} else if (a == 's' && b == '$') {
			return 1;
		} else if (a == 'l' && b == 'i') {
			return 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(CCCI)I")
	private static int getEmulatedBadCharLen(@OriginalArg(0) char c, @OriginalArg(1) char a, @OriginalArg(2) char b) {
		if (a == b) {
			return 1;
		}

		if (a >= 'a' && a <= 'm') {
			if (a == 'a') {
				if (b != '4' && b != '@' && b != '^') {
					if (b == '/' && c == '\\') {
						return 2;
					}

					return 0;
				}

				return 1;
			} else if (a == 'b') {
				if (b != '6' && b != '8') {
					if (b == '1' && c == '3') {
						return 2;
					}

					return 0;
				}

				return 1;
			} else if (a == 'c') {
				if (b != '(' && b != '<' && b != '{' && b != '[') {
					return 0;
				}

				return 1;
			} else if (a == 'd') {
				if (b == '[' && c == ')') {
					return 2;
				}

				return 0;
			} else if (a == 'e') {
				if (b != '3' && b != '€') {
					return 0;
				}

				return 1;
			} else if (a == 'f') {
				if (b == 'p' && c == 'h') {
					return 2;
				}

				if (b == '£') {
					return 1;
				}

				return 0;
			} else if (a == 'g') {
				if (b != '9' && b != '6') {
					return 0;
				}

				return 1;
			} else if (a == 'h') {
				if (b == '#') {
					return 1;
				}

				return 0;
			} else if (a == 'i') {
				if (b != 'y' && b != 'l' && b != 'j' && b != '1' && b != '!' && b != ':' && b != ';' && b != '|') {
					return 0;
				}

				return 1;
			} else if (a == 'j') {
				return 0;
			} else if (a == 'k') {
				return 0;
			} else if (a == 'l') {
				if (b != '1' && b != '|' && b != 'i') {
					return 0;
				}

				return 1;
			} else if (a == 'm') {
				return 0;
			}
		} else if (a >= 'n' && a <= 'z') {
			if (a == 'n') {
				return 0;
			} else if (a == 'o') {
				if (b != '0' && b != '*') {
					if ((b != '(' || c != ')') && (b != '[' || c != ']') && (b != '{' || c != '}') && (b != '<' || c != '>')) {
						return 0;
					}

					return 2;
				}

				return 1;
			} else if (a == 'p') {
				return 0;
			} else if (a == 'q') {
				return 0;
			} else if (a == 'r') {
				return 0;
			} else if (a == 's') {
				if (b != '5' && b != 'z' && b != '$' && b != '2') {
					return 0;
				}

				return 1;
			} else if (a == 't') {
				if (b != '7' && b != '+') {
					return 0;
				}

				return 1;
			} else if (a == 'u') {
				if (b == 'v') {
					return 1;
				}

				if ((b != '\\' || c != '/') && (b != '\\' || c != '|') && (b != '|' || c != '/')) {
					return 0;
				}

				return 2;
			} else if (a == 'v') {
				if ((b != '\\' || c != '/') && (b != '\\' || c != '|') && (b != '|' || c != '/')) {
					return 0;
				}

				return 2;
			} else if (a == 'w') {
				if (b == 'v' && c == 'v') {
					return 2;
				}

				return 0;
			} else if (a == 'x') {
				if ((b != ')' || c != '(') && (b != '}' || c != '{') && (b != ']' || c != '[') && (b != '>' || c != '<')) {
					return 0;
				}

				return 2;
			} else if (a == 'y') {
				return 0;
			} else if (a == 'z') {
				return 0;
			}
		} else if (a >= '0' && a <= '9') {
			if (a == '0') {
				if (b == 'o' || b == 'O') {
					return 1;
				} else if ((b != '(' || c != ')') && (b != '{' || c != '}') && (b != '[' || c != ']')) {
					return 0;
				} else {
					return 2;
				}
			} else if (a == '1') {
				return b == 'l' ? 1 : 0;
			} else {
				return 0;
			}
		} else if (a == ',') {
			return b == '.' ? 1 : 0;
		} else if (a == '.') {
			return b == ',' ? 1 : 0;
		} else if (a == '!') {
			return b == 'i' ? 1 : 0;
		}

		return 0;
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(IC)B")
	private static byte getIndex(@OriginalArg(1) char c) {
		if (c >= 'a' && c <= 'z') {
			return (byte) (c + 1 - 'a');
		} else if (c == '\'') {
			return 28;
		} else if (c >= '0' && c <= '9') {
			return (byte) (c + 29 - '0');
		} else {
			return 27;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(I[C)V")
	private static void filterNumFragments(@OriginalArg(1) char[] chars) {
		@Pc(5) int end = 0;
		@Pc(11) int count = 0;
		@Pc(13) int start = 0;

		int index;
		while ((index = indexOfNumber(chars, end)) != -1) {
			@Pc(17) boolean foundLowercase = false;
			for (@Pc(19) int i = end; i >= 0 && i < index && !foundLowercase; i++) {
				if (!isSymbol(chars[i]) && !isNotLowercaseAlpha(chars[i])) {
					foundLowercase = true;
				}
			}

			if (foundLowercase) {
				count = 0;
			}

			if (count == 0) {
				start = index;
			}

			end = indexOfNonNumber(index, chars);

			@Pc(58) int value = 0;
			for (@Pc(60) int i = index; i < end; i++) {
				value = value * 10 + chars[i] - 48;
			}

			if (value <= 255 && end - index <= 8) {
				count++;
			} else {
				count = 0;
			}
		}

		if (count == 4) {
			for (@Pc(94) int i = start; i < end; i++) {
				chars[i] = '*';
			}

			count = 0;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(I[CI)I")
	private static int indexOfNumber(@OriginalArg(1) char[] chars, @OriginalArg(2) int off) {
		for (@Pc(5) int n = off; n < chars.length && n >= 0; n++) {
			if (chars[n] >= '0' && chars[n] <= '9') {
				return n;
			}
		}

		return -1;
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(II[C)I")
	private static int indexOfNonNumber(@OriginalArg(1) int off, @OriginalArg(2) char[] chars) {
		@Pc(6) int n = off;
		while (true) {
			if (n < chars.length && n >= 0) {
				if (chars[n] >= '0' && chars[n] <= '9') {
					n++;
					continue;
				}

				return n;
			}

			return chars.length;
		}
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(CI)Z")
	private static boolean isSymbol(@OriginalArg(0) char c) {
		return !isAlpha(c) && !isNumeral(c);
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(CB)Z")
	private static boolean isNotLowercaseAlpha(@OriginalArg(0) char c) {
		if (c >= 'a' && c <= 'z') {
			return c == 'v' || c == 'x' || c == 'j' || c == 'q' || c == 'z';
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!mc", name = "c", descriptor = "(IC)Z")
	private static boolean isAlpha(@OriginalArg(1) char c) {
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(CI)Z")
	private static boolean isNumeral(@OriginalArg(0) char c) {
		return c >= '0' && c <= '9';
	}

	@OriginalMember(owner = "client!mc", name = "a", descriptor = "(BC)Z")
	private static boolean isLowercaseAlpha(@OriginalArg(1) char c) {
		return c >= 'a' && c <= 'z';
	}

	@OriginalMember(owner = "client!mc", name = "d", descriptor = "(IC)Z")
	private static boolean isUppercaseAlpha(@OriginalArg(1) char c) {
		return c >= 'A' && c <= 'Z';
	}

	@OriginalMember(owner = "client!mc", name = "c", descriptor = "([CI)Z")
	private static boolean isBadFragment(@OriginalArg(0) char[] chars) {
		@Pc(3) boolean skip = true;

		for (@Pc(5) int i = 0; i < chars.length; i++) {
			if (!isNumeral(chars[i]) && chars[i] != 0) {
				skip = false;
				break;
			}
		}

		if (skip) {
			return true;
		}

		@Pc(32) int i = getInteger(chars);
		@Pc(34) int start = 0;
		@Pc(49) int end = fragments.length - 1;
		if (i == fragments[0] || i == fragments[end]) {
			return true;
		}

		do {
			@Pc(67) int middle = (start + end) / 2;
			if (i == fragments[middle]) {
				return true;
			}

			if (i < fragments[middle]) {
				end = middle;
			} else {
				start = middle;
			}
		} while (start != end && start + 1 != end);

		return false;
	}

	@OriginalMember(owner = "client!mc", name = "b", descriptor = "(I[C)I")
	private static int getInteger(@OriginalArg(1) char[] chars) {
		if (chars.length > 6) {
			return 0;
		}

		@Pc(9) int value = 0;
		for (@Pc(11) int i = 0; i < chars.length; i++) {
			@Pc(22) char c = chars[chars.length - i - 1];
			if (c >= 'a' && c <= 'z') {
				value = value * 38 + c + 1 - 'a';
			} else if (c == '\'') {
				value = value * 38 + 27;
			} else if (c >= '0' && c <= '9') {
				value = value * 38 + c + 28 - '0';
			} else if (c != 0) {
				return 0;
			}
		}

		return value;
	}
}
