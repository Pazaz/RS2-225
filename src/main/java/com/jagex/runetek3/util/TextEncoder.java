package com.jagex.runetek3.util;

public class TextEncoder {

    public static String read(Buffer buffer, int len) {
        int pos = 0;
        int last = -1;

        for (int n = 0; n < len; n++) {
            int c = buffer.readByte();
            int value = c >> 4 & 0xf;

            if (last == -1) {
                if (value < 13) {
                    builder[pos++] = CHAR_TABLE[value];
                } else {
                    last = value;
                }
            } else {
                builder[pos++] = CHAR_TABLE[((last << 4) + value) - 195];
                last = -1;
            }

            value = c & 0xf;
            if (last == -1) {
                if (value < 13)
                    builder[pos++] = CHAR_TABLE[value];
                else
                    last = value;
            } else {
                builder[pos++] = CHAR_TABLE[((last << 4) + value) - 195];
                last = -1;
            }
        }

        boolean capitalize = true;
        for (int l1 = 0; l1 < pos; l1++) {
            char c = builder[l1];
            if (capitalize && c >= 'a' && c <= 'z') {
                builder[l1] += '\uFFE0';
                capitalize = false;
            }
            if (c == '.' || c == '!') {
                capitalize = true;
            }
        }

        return new String(builder, 0, pos);
    }

    public static void write(Buffer buffer, String str) {
        if (str.length() > 80) {
            str = str.substring(0, 80);
        }

        str = str.toLowerCase();

        int msb = -1;
        for (int j = 0; j < str.length(); j++) {
            char c = str.charAt(j);
            int lsb = 0;

            for (int m = 0; m < CHAR_TABLE.length; m++) {
                if (c == CHAR_TABLE[m]) {
                    lsb = m;
                    break;
                }
            }

            if (lsb > 12) {
                lsb += 195;
            }

            if (msb != -1) {
                if (lsb < 13) {
                    buffer.writeByte((msb << 4) + lsb);
                    msb = -1;
                } else {
                    buffer.writeByte((msb << 4) + (lsb >> 4));
                    msb = lsb & 0xf;
                }
            } else {
                if (lsb < 13) {
                    msb = lsb;
                } else {
                    buffer.writeByte(lsb);
                }
            }
        }

        if (msb != -1) {
            buffer.writeByte(msb << 4);
        }
    }

    public static char[] builder = new char[100];
    public static char[] CHAR_TABLE = {
        ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n', 's', 'r',
        'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p',
        'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2',
        '3', '4', '5', '6', '7', '8', '9', ' ', '!', '?',
        '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\',
        '\'', '@', '#', '+', '=', '\243', '$', '%', '"', '[',
        ']'
    };

}
