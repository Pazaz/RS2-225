package com.jagex.runetek3.util;

import java.util.Arrays;

public class StringUtils {

    public static char[] builder = new char[12];
    public static char[] BASE37_LOOKUP = {
        '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
        'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
        't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
        '3', '4', '5', '6', '7', '8', '9'
    };

    public static long toBase37(String str) {
        long value = 0L;

        for (int n = 0; n < str.length() && n < 12; n++) {
            char c = str.charAt(n);
            value *= 37L;

            if (c >= 'A' && c <= 'Z') {
                value += (1 + c) - 65;
            } else if (c >= 'a' && c <= 'z') {
                value += (1 + c) - 97;
            } else if (c >= '0' && c <= '9') {
                value += (27 + c) - 48;
            }
        }

        while (((value % 37L) == 0L) && (value != 0L)) {
            value /= 37L;
        }

        return value;
    }

    public static String fromBase37(long value) {
        if (value <= 0L || value >= 0x5b5b57f8a98a5dd1L || value % 37L == 0) {
            return "invalid_name";
        }

        int len = 0;
        while (value != 0L) {
            long oldValue = value;
            value /= 37L;
            builder[11 - len++] = BASE37_LOOKUP[(int) (oldValue - value * 37L)];
        }

        return new String(builder, 12 - len, len);
    }

    public static long genHash(String str) {
        str = str.toUpperCase();
        long hash = 0L;
        for (int n = 0; n < str.length(); n++) {
            hash = (hash * 61L + (long) str.charAt(n)) - 32L;
            hash = hash + (hash >> 56) & 0xffffffffffffffL;
        }
        return hash;
    }

    public static String fromIPv4(int ip) {
        return String.format("%d.%d.%d.%d", (ip >> 24) & 0xff, (ip >> 16) & 0xff, (ip >> 8) & 0xff, ip & 0xff);
    }

    public static String formatName(String str) {
        if (str.length() > 0) {
            char[] chars = str.toCharArray();

            for (int n = 0; n < chars.length; n++) {
                if (chars[n] == '_') {
                    chars[n] = ' ';

                    if (n + 1 < chars.length && chars[n + 1] >= 'a' && chars[n + 1] <= 'z') {
                        chars[n + 1] = (char) ((chars[n + 1] + 65) - 97);
                    }
                }
            }

            if (chars[0] >= 'a' && chars[0] <= 'z') {
                chars[0] = (char) ((chars[0] + 65) - 97);
            }

            return new String(chars);
        }

        return str;
    }

    public static String toSentence(String str) {
        str = str.toLowerCase();
        char[] chars = str.toCharArray();

        boolean capitalize = true;
        for (int n = 0; n < chars.length; n++) {
            char c = chars[n];

            if (capitalize && c >= 'a' && c <= 'z') {
                chars[n] += '\uFFE0';
                capitalize = false;
            }

            if (c == '.' || c == '!') {
                capitalize = true;
            }
        }

        return new String(chars);
    }

    public static String toAsterisks(String str) {
        char[] c = new char[str.length()];
        Arrays.fill(c, '*');
        return new String(c);
    }

}
