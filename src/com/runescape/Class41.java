package com.runescape;

public class Class41 {

    public static String method482(Class38_Sub2_Sub3 class38_sub2_sub3, int i, int j) {
        int k = 0;
        int l = -1;
        i = 43 / i;
        for (int i1 = 0; i1 < j; i1++) {
            int j1 = class38_sub2_sub3.method446();
            int k1 = j1 >> 4 & 0xf;
            if (l == -1) {
                if (k1 < 13)
                    aCharArray676[k++] = aCharArray677[k1];
                else
                    l = k1;
            } else {
                aCharArray676[k++] = aCharArray677[((l << 4) + k1) - 195];
                l = -1;
            }
            k1 = j1 & 0xf;
            if (l == -1) {
                if (k1 < 13)
                    aCharArray676[k++] = aCharArray677[k1];
                else
                    l = k1;
            } else {
                aCharArray676[k++] = aCharArray677[((l << 4) + k1) - 195];
                l = -1;
            }
        }

        boolean flag = true;
        for (int l1 = 0; l1 < k; l1++) {
            char c = aCharArray676[l1];
            if (flag && c >= 'a' && c <= 'z') {
                aCharArray676[l1] += '\uFFE0';
                flag = false;
            }
            if (c == '.' || c == '!')
                flag = true;
        }

        return new String(aCharArray676, 0, k);
    }

    public static void method483(Class38_Sub2_Sub3 class38_sub2_sub3, boolean flag, String s) {
        if (s.length() > 80)
            s = s.substring(0, 80);
        s = s.toLowerCase();
        int i = -1;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            int k = 0;
            for (int l = 0; l < aCharArray677.length; l++) {
                if (c != aCharArray677[l])
                    continue;
                k = l;
                break;
            }

            if (k > 12)
                k += 195;
            if (i == -1) {
                if (k < 13)
                    i = k;
                else
                    class38_sub2_sub3.method436(k);
            } else if (k < 13) {
                class38_sub2_sub3.method436((i << 4) + k);
                i = -1;
            } else {
                class38_sub2_sub3.method436((i << 4) + (k >> 4));
                i = k & 0xf;
            }
        }

        if (!flag)
            return;
        if (i != -1)
            class38_sub2_sub3.method436(i << 4);
    }

    public static char[] aCharArray676 = new char[100];
    public static char[] aCharArray677 = {
            ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n', 's', 'r',
            'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p',
            'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', ' ', '!', '?',
            '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\',
            '\'', '@', '#', '+', '=', '\243', '$', '%', '"', '[',
            ']'
    };

}
