package com.jagex.runescape.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

public class WordEncoding {

    public static void load(FileArchive fileArchive) {
        Buffer fragments = new Buffer(fileArchive.read("fragmentsenc.txt", null));
        Buffer bad = new Buffer(fileArchive.read("badenc.txt", null));
        Buffer domain = new Buffer(fileArchive.read("domainenc.txt", null));
        Buffer tld = new Buffer(fileArchive.read("tldlist.txt", null));
        unpack(fragments, bad, domain, tld);
    }

    public static void unpack(Buffer fragments, Buffer bad, Buffer domain, Buffer tld) {
        readBad(bad);
        readDomain(domain);
        readFragments(fragments);
        readTld(tld);
    }

    public static void readTld(Buffer buffer) {
        int count = buffer.readDWord();

        tlds = new char[count][];
        tldTypes = new int[count];

        for (int n = 0; n < count; n++) {
            tldTypes[n] = buffer.readByte();

            char[] string = new char[buffer.readByte()];
            for (int k = 0; k < string.length; k++) {
                string[k] = (char) buffer.readByte();
            }

            tlds[n] = string;
        }
    }

    public static void readBad(Buffer buffer) {
        int count = buffer.readDWord();

        bads = new char[count][];
        badCombinations = new byte[count][][];

        readBad(badCombinations, bads, buffer);
    }

    public static void readDomain(Buffer buffer) {
        domains = new char[buffer.readDWord()][];

        readDomain(buffer, domains);
    }

    public static void readFragments(Buffer buffer) {
        fragments = new int[buffer.readDWord()];

        for (int n = 0; n < fragments.length; n++) {
            fragments[n] = buffer.readWord();
        }
    }

    public static void readBad(byte[][][] badCombinations, char[][] bads, Buffer buffer) {
        for (int n = 0; n < bads.length; n++) {
            char[] chars = new char[buffer.readByte()];
            for (int j = 0; j < chars.length; j++) {
                chars[j] = (char) buffer.readByte();
            }
            bads[n] = chars;

            byte[][] combo = new byte[buffer.readByte()][2];

            for (int k = 0; k < combo.length; k++) {
                combo[k][0] = (byte) buffer.readByte();
                combo[k][1] = (byte) buffer.readByte();
            }

            if (combo.length > 0) {
                badCombinations[n] = combo;
            }
        }
    }

    public static void readDomain(Buffer buffer, char[][] domains) {
        for (int n = 0; n < domains.length; n++) {
            char[] string = new char[buffer.readByte()];
            for (int k = 0; k < string.length; k++) {
                string[k] = (char) buffer.readByte();
            }

            domains[n] = string;
        }
    }

    public static void trimWhitespaces(char[] chars) {
        int off = 0;

        for (int n = 0; n < chars.length; n++) {
            if (isValid(chars[n])) {
                chars[off] = chars[n];
            } else {
                chars[off] = ' ';
            }

            if (off == 0 || chars[off] != ' ' || chars[off - 1] != ' ') {
                off++;
            }
        }

        for (int n = off; n < chars.length; n++) {
            chars[n] = ' ';
        }
    }

    public static boolean isValid(char c) {
        return c >= ' ' && c <= 127 || c == ' ' || c == '\n' || c == '\t' || c == '\243' || c == '\u20AC';
    }

    public static String getFiltered(String str) {
        char[] chars = str.toCharArray();
        trimWhitespaces(chars);

        String trimmed = (new String(chars)).trim();
        chars = trimmed.toLowerCase().toCharArray();
        String lower = trimmed.toLowerCase();

        filterTlds(chars);
        filterBad(chars);
        filterDomains(chars);
        filterNumFragments(chars);

        for (int word = 0; word < whitelist.length; word++) {
            for (int index = -1; (index = lower.indexOf(whitelist[word], index + 1)) != -1; ) {
                char[] wchars = whitelist[word].toCharArray();
                System.arraycopy(wchars, 0, chars, 0 + index, wchars.length);
            }
        }

        replaceUppercases(chars, trimmed.toCharArray());
        formatUppercases(chars);
        return (new String(chars)).trim();
    }

    public static void replaceUppercases(char[] to, char[] from) {
        for (int n = 0; n < from.length; n++) {
            if (to[n] != '*' && isUppercaseAlpha(from[n])) {
                to[n] = from[n];
            }
        }
    }

    public static void formatUppercases(char[] chars) {
        boolean flag = true;

        for (int n = 0; n < chars.length; n++) {
            char c = chars[n];

            if (isAlpha(c)) {
                if (flag) {
                    if (isLowercaseAlpha(c)) {
                        flag = false;
                    }
                } else if (isUppercaseAlpha(c)) {
                    chars[n] = (char) ((c + 97) - 65);
                }
            } else {
                flag = true;
            }
        }
    }

    public static void filterBad(char[] chars) {
        for (int i = 0; i < 2; i++) {
            for (int n = bads.length - 1; n >= 0; n--) {
                filterBad(badCombinations[n], chars, bads[n]);
            }
        }
    }

    public static void filterDomains(char[] chars) {
        char[] filteredAts = chars.clone();
        char[] ac2 = {'(', 'a', ')'};
        filterBad(null, filteredAts, ac2);

        char[] filteredDot = chars.clone();
        char[] ac4 = {'d', 'o', 't'};
        filterBad(null, filteredDot, ac4);

        for (int n = domains.length - 1; n >= 0; n--) {
            filterDomain(filteredDot, filteredAts, domains[n], chars);
        }
    }

    public static void filterDomain(char[] filteredDot, char[] filteredAts, char[] domain, char[] chars) {
        if (domain.length > chars.length) {
            return;
        }

        int stride;
        for (int start = 0; start <= chars.length - domain.length; start += stride) {
            int end = start;
            int off = 0;
            stride = 1;

            while (end < chars.length) {
                int charLen;
                char b = chars[end];
                char c = '\0';

                if (end + 1 < chars.length) {
                    c = chars[end + 1];
                }

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
                boolean bad = false;
                int status0 = getDomainAtFilterStatus(start, chars, filteredAts);
                int status1 = getDomainDotFilterStatus(filteredDot, chars, end - 1);

                if (status0 > 2 || status1 > 2) {
                    bad = true;
                }

                if (bad) {
                    for (int n = start; n < end; n++) {
                        chars[n] = '*';
                    }
                }
            }
        }
    }

    public static int getDomainAtFilterStatus(int end, char[] a, char[] b) {
        if (end == 0) {
            return 2;
        }

        for (int n = end - 1; n >= 0; n--) {
            if (!isSymbol(a[n])) {
                break;
            }

            if (a[n] == '@') {
                return 3;
            }
        }

        int asteriskCount = 0;
        for (int n = end - 1; n >= 0; n--) {
            if (!isSymbol(b[n])) {
                break;
            }

            if (b[n] == '*') {
                asteriskCount++;
            }
        }

        if (asteriskCount >= 3) {
            return 4;
        }

        return !isSymbol(a[end - 1]) ? 0 : 1;
    }

    public static int getDomainDotFilterStatus(char[] b, char[] a, int start) {
        if (start + 1 == a.length) {
            return 2;
        }

        for (int n = start + 1; n < a.length; n++) {
            if (!isSymbol(a[n])) {
                break;
            }

            if (a[n] == '.' || a[n] == ',') {
                return 3;
            }
        }

        int asteriskCount = 0;
        for (int n = start + 1; n < a.length; n++) {
            if (!isSymbol(b[n])) {
                break;
            }

            if (b[n] == '*') {
                asteriskCount++;
            }
        }

        if (asteriskCount >= 3) {
            return 4;
        }

        return !isSymbol(a[start + 1]) ? 0 : 1;
    }

    public static void filterTlds(char[] chars) {
        char[] filteredDot = chars.clone();
        char[] ac2 = {'d', 'o', 't'};
        filterBad(null, filteredDot, ac2);

        char[] filteredSlash = chars.clone();
        char[] ac4 = {'s', 'l', 'a', 's', 'h'};
        filterBad(null, filteredSlash, ac4);

        for (int j = 0; j < tlds.length; j++) {
            filterTld(filteredSlash, tldTypes[j], chars, tlds[j], filteredDot);
        }
    }

    public static void filterTld(char[] filteredSlash, int i, char[] chars, char[] tld, char[] filteredDot) {
        if (tld.length > chars.length) {
            return;
        }

        int stride;
        for (int start = 0; start <= chars.length - tld.length; start += stride) {
            int end = start;
            int off = 0;
            stride = 1;

            while (end < chars.length) {
                int charLen = 0;
                char b = chars[end];
                char c = '\0';

                if (end + 1 < chars.length) {
                    c = chars[end + 1];
                }

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
                boolean bad = false;
                int status0 = getTldDotFilterStatus(chars, filteredDot, start);
                int status1 = getTldSlashFilterStatus(filteredSlash, end - 1, chars);

                if (i == 1 && status0 > 0 && status1 > 0) {
                    bad = true;
                }

                if (i == 2 && (status0 > 2 && status1 > 0 || status0 > 0 && status1 > 2)) {
                    bad = true;
                }

                if (i == 3 && status0 > 0 && status1 > 2) {
                    bad = true;
                }

                if (bad) {
                    int first = start;
                    int last = end - 1;

                    if (status0 > 2) {
                        if (status0 == 4) {
                            boolean findStart = false;
                            for (int n = first - 1; n >= 0; n--) {
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
                            boolean findLast = false;
                            for (int n = last + 1; n < chars.length; n++) {
                                if (findLast) {
                                    if (filteredSlash[n] != '*') {
                                        break;
                                    }

                                    last = n;
                                } else if (filteredSlash[n] == '*') {
                                    last = n;
                                    findLast = true;
                                }
                            }
                        }

                        boolean findLast = false;
                        for (int n = last + 1; n < chars.length; n++) {
                            if (findLast) {
                                if (isSymbol(chars[n])) {
                                    break;
                                }

                                last = n;
                            } else if (!isSymbol(chars[n])) {
                                findLast = true;
                                last = n;
                            }
                        }
                    }

                    for (int n = first; n <= last; n++) {
                        chars[n] = '*';
                    }
                }
            }
        }
    }

    public static int getTldDotFilterStatus(char[] chars, char[] filteredDot, int start) {
        if (start == 0) {
            return 2;
        }

        for (int n = start - 1; n >= 0; n--) {
            if (!isSymbol(chars[n])) {
                break;
            }

            if (chars[n] == ',' || chars[n] == '.') {
                return 3;
            }
        }

        int asteriskCount = 0;
        for (int n = start - 1; n >= 0; n--) {
            if (!isSymbol(filteredDot[n])) {
                break;
            }

            if (filteredDot[n] == '*') {
                asteriskCount++;
            }
        }

        if (asteriskCount >= 3) {
            return 4;
        }

        return !isSymbol(chars[start - 1]) ? 0 : 1;
    }

    public static int getTldSlashFilterStatus(char[] filteredSlash, int end, char[] chars) {
        if (end + 1 == chars.length) {
            return 2;
        }

        for (int n = end + 1; n < chars.length; n++) {
            if (!isSymbol(chars[n])) {
                break;
            }

            if (chars[n] == '\\' || chars[n] == '/') {
                return 3;
            }
        }

        int asteriskCount = 0;
        for (int n = end + 1; n < chars.length; n++) {
            if (!isSymbol(filteredSlash[n])) {
                break;
            }

            if (filteredSlash[n] == '*') {
                asteriskCount++;
            }
        }

        if (asteriskCount >= 5) {
            return 4;
        }

        return !isSymbol(chars[end + 1]) ? 0 : 1;
    }

    public static void filterBad(byte[][] badCombinations, char[] chars, char[] fragment) {
        if (fragment.length > chars.length) {
            return;
        }

        int stride;
        for (int start = 0; start <= chars.length - fragment.length; start += stride) {
            int end = start;
            int fragOff = 0;
            int iterations = 0;
            stride = 1;

            boolean isSymbol = false;
            boolean isEmulated = false;
            boolean isNumeral = false;

            while (end < chars.length && (!isEmulated || !isNumeral)) {
                int charLen;
                char b = chars[end];
                char c = '\0';

                if (end + 1 < chars.length) {
                    c = chars[end + 1];
                }

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

                if ((++iterations * 100) / (end - start) > 90) {
                    break;
                }
            }

            if (fragOff >= fragment.length && (!isEmulated || !isNumeral)) {
                boolean bad = true;

                if (!isSymbol) {
                    char a = ' ';
                    if (start - 1 >= 0) {
                        a = chars[start - 1];
                    }

                    char b = ' ';
                    if (end < chars.length) {
                        b = chars[end];
                    }

                    if (badCombinations != null && comboMatches(getIndex(a), badCombinations, getIndex(b))) {
                        bad = false;
                    }
                } else {
                    boolean badCurrent = false;
                    boolean badNext = false;

                    if (start - 1 < 0 || isSymbol(chars[start - 1]) && chars[start - 1] != '\'') {
                        badCurrent = true;
                    }

                    if (end >= chars.length || isSymbol(chars[end]) && chars[end] != '\'') {
                        badNext = true;
                    }

                    if (!badCurrent || !badNext) {
                        boolean good = false;
                        int cur = start - 2;
                        if (badCurrent) {
                            cur = start;
                        }

                        for (; !good && cur < end; cur++) {
                            if (cur >= 0 && (!isSymbol(chars[cur]) || chars[cur] == '\'')) {
                                char[] frag = new char[3];
                                int off;
                                for (off = 0; off < 3; off++) {
                                    if (cur + off >= chars.length || isSymbol(chars[cur + off]) && chars[cur + off] != '\'') {
                                        break;
                                    }

                                    frag[off] = chars[cur + off];
                                }

                                boolean valid = off != 0;
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
                }

                if (bad) {
                    int numeralCount = 0;
                    int alphaCount = 0;

                    for (int n = start; n < end; n++) {
                        if (isNumeral(chars[n])) {
                            numeralCount++;
                        } else if (isAlpha(chars[n])) {
                            alphaCount++;
                        }
                    }

                    if (numeralCount <= alphaCount) {
                        for (int n = start; n < end; n++) {
                            chars[n] = '*';
                        }
                    }
                }
            }
        }
    }

    public static boolean comboMatches(byte a, byte[][] combos, byte b) {
        int first = 0;
        if (combos[first][0] == a && combos[first][1] == b) {
            return true;
        }

        int last = combos.length - 1;
        if (combos[last][0] == a && combos[last][1] == b) {
            return true;
        }

        do {
            int middle = (first + last) / 2;
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

    public static int getEmulatedDomainCharLen(char c, char a, char b) {
        if (a == b) {
            return 1;
        }

        if (a == 'o' && b == '0') {
            return 1;
        }

        if (a == 'o' && b == '(' && c == ')') {
            return 2;
        }

        if (a == 'c' && (b == '(' || b == '<' || b == '[')) {
            return 1;
        }

        if (a == 'e' && b == '\u20AC') {
            return 1;
        }

        if (a == 's' && b == '$') {
            return 1;
        }

        return a != 'l' || b != 'i' ? 0 : 1;
    }

    public static int getEmulatedBadCharLen(char c, char a, char b) {
        if (a == b) {
            return 1;
        }

        if (a >= 'a' && a <= 'm') {
            if (a == 'a') {
                if (b == '4' || b == '@' || b == '^') {
                    return 1;
                }

                return b != '/' || c != '\\' ? 0 : 2;
            }

            if (a == 'b') {
                if (b == '6' || b == '8') {
                    return 1;
                }

                return b != '1' || c != '3' ? 0 : 2;
            }

            if (a == 'c') {
                return b != '(' && b != '<' && b != '{' && b != '[' ? 0 : 1;
            }

            if (a == 'd') {
                return b != '[' || c != ')' ? 0 : 2;
            }

            if (a == 'e') {
                return b != '3' && b != '\u20AC' ? 0 : 1;
            }

            if (a == 'f') {
                if (b == 'p' && c == 'h') {
                    return 2;
                }

                return b != '\243' ? 0 : 1;
            }

            if (a == 'g') {
                return b != '9' && b != '6' ? 0 : 1;
            }

            if (a == 'h') {
                return b != '#' ? 0 : 1;
            }

            if (a == 'i') {
                return b != 'y' && b != 'l' && b != 'j' && b != '1' && b != '!' && b != ':' && b != ';' && b != '|' ? 0 : 1;
            }

            if (a == 'j') {
                return 0;
            }

            if (a == 'k') {
                return 0;
            }

            if (a == 'l') {
                return b != '1' && b != '|' && b != 'i' ? 0 : 1;
            }

            if (a == 'm') {
                return 0;
            }
        }

        if (a >= 'n' && a <= 'z') {
            if (a == 'n') {
                return 0;
            }

            if (a == 'o') {
                if (b == '0' || b == '*') {
                    return 1;
                }

                return (b != '(' || c != ')') && (b != '[' || c != ']') && (b != '{' || c != '}') && (b != '<' || c != '>') ? 0 : 2;
            }

            if (a == 'p') {
                return 0;
            }

            if (a == 'q') {
                return 0;
            }

            if (a == 'r') {
                return 0;
            }

            if (a == 's') {
                return b != '5' && b != 'z' && b != '$' && b != '2' ? 0 : 1;
            }

            if (a == 't') {
                return b != '7' && b != '+' ? 0 : 1;
            }

            if (a == 'u') {
                if (b == 'v') {
                    return 1;
                }

                return (b != '\\' || c != '/') && (b != '\\' || c != '|') && (b != '|' || c != '/') ? 0 : 2;
            }

            if (a == 'v') {
                return (b != '\\' || c != '/') && (b != '\\' || c != '|') && (b != '|' || c != '/') ? 0 : 2;
            }

            if (a == 'w') {
                return b != 'v' || c != 'v' ? 0 : 2;
            }

            if (a == 'x') {
                return (b != ')' || c != '(') && (b != '}' || c != '{') && (b != ']' || c != '[') && (b != '>' || c != '<') ? 0 : 2;
            }

            if (a == 'y') {
                return 0;
            }

            if (a == 'z') {
                return 0;
            }
        }

        if (a >= '0' && a <= '9') {
            if (a == '0') {
                if (b == 'o' || b == 'O') {
                    return 1;
                }

                return (b != '(' || c != ')') && (b != '{' || c != '}') && (b != '[' || c != ']') ? 0 : 2;
            }

            if (a == '1') {
                return b != 'l' ? 0 : 1;
            }

            return 0;
        }

        if (a == ',') {
            return b != '.' ? 0 : 1;
        }

        if (a == '.') {
            return b != ',' ? 0 : 1;
        }

        if (a == '!') {
            return b != 'i' ? 0 : 1;
        }

        return 0;
    }

    public static byte getIndex(char c) {
        if (c >= 'a' && c <= 'z') {
            return (byte) ((c - 97) + 1);
        }

        if (c == '\'') {
            return 28;
        }

        if (c >= '0' && c <= '9') {
            return (byte) ((c - 48) + 29);
        }

        return 27;
    }

    public static void filterNumFragments(char[] chars) {
        int index;
        int end = 0;
        int count = 0;
        int start = 0;

        while ((index = indexOfNumber(chars, end)) != -1) {
            boolean foundLowercase = false;
            for (int j1 = end; j1 >= 0 && j1 < index && !foundLowercase; j1++) {
                if (!isSymbol(chars[j1]) && !isNotLowercaseAlpha(chars[j1])) {
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

            int value = 0;
            for (int l1 = index; l1 < end; l1++) {
                value = (value * 10 + chars[l1]) - 48;
            }

            if (value > 255 || end - index > 8) {
                count = 0;
            } else {
                count++;
            }

            if (count == 4) {
                for (int n = start; n < end; n++) {
                    chars[n] = '*';
                }

                count = 0;
            }
        }
    }

    public static int indexOfNumber(char[] chars, int off) {
        for (int n = off; n < chars.length && n >= 0; n++) {
            if (chars[n] >= '0' && chars[n] <= '9') {
                return n;
            }
        }

        return -1;
    }

    public static int indexOfNonNumber(int off, char[] chars) {
        for (int n = off; n < chars.length && n >= 0; n++) {
            if (chars[n] < '0' || chars[n] > '9') {
                return n;
            }
        }

        return chars.length;
    }

    public static boolean isSymbol(char c) {
        return !isAlpha(c) && !isNumeral(c);
    }

    public static boolean isNotLowercaseAlpha(char c) {
        if (c < 'a' || c > 'z') {
            return true;
        }

        return c == 'v' || c == 'x' || c == 'j' || c == 'q' || c == 'z';
    }

    public static boolean isAlpha(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    public static boolean isNumeral(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isLowercaseAlpha(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isUppercaseAlpha(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isBadFragment(char[] chars) {
        boolean skip = true;

        for (char c : chars) {
            if (!isNumeral(c) && c != 0) {
                skip = false;
                break;
            }
        }

        if (skip) {
            return true;
        }

        int i = getInteger(chars);
        int start = 0;
        int end = fragments.length - 1;

        if (i == fragments[start] || i == fragments[end]) {
            return true;
        }

        do {
            int middle = (start + end) / 2;
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

    public static int getInteger(char[] chars) {
        if (chars.length > 6) {
            return 0;
        }

        int value = 0;
        for (int n = 0; n < chars.length; n++) {
            char c = chars[chars.length - n - 1];

            if (c >= 'a' && c <= 'z') {
                value = value * 38 + ((c - 97) + 1);
            } else if (c == '\'') {
                value = value * 38 + 27;
            } else if (c >= '0' && c <= '9') {
                value = value * 38 + ((c - 48) + 28);
            } else if (c != 0) {
                return 0;
            }
        }

        return value;
    }

    public static int[] fragments;
    public static char[][] bads;
    public static byte[][][] badCombinations;
    public static char[][] domains;
    public static char[][] tlds;
    public static int[] tldTypes;
    public static final String[] whitelist = {"cook", "cook's", "cooks", "seeks", "sheet"};

}
