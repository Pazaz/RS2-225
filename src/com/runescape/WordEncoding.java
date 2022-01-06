package com.runescape;

public class WordEncoding {

    public static void method229(FileArchive fileArchive) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363,
                fileArchive.read("fragmentsenc.txt", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_1 = new Class38_Sub2_Sub3(363,
                fileArchive.read("badenc.txt", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_2 = new Class38_Sub2_Sub3(363,
                fileArchive.read("domainenc.txt", null));
        Class38_Sub2_Sub3 class38_sub2_sub3_3 = new Class38_Sub2_Sub3(363,
                fileArchive.read("tldlist.txt", null));
        method230(class38_sub2_sub3, class38_sub2_sub3_1, class38_sub2_sub3_2, class38_sub2_sub3_3);
    }

    public static void method230(Class38_Sub2_Sub3 class38_sub2_sub3, Class38_Sub2_Sub3 class38_sub2_sub3_1,
                                 Class38_Sub2_Sub3 class38_sub2_sub3_2, Class38_Sub2_Sub3 class38_sub2_sub3_3) {
        method232(-33152, class38_sub2_sub3_1);
        method233(class38_sub2_sub3_2, -717);
        method234(24882, class38_sub2_sub3);
        method231(true, class38_sub2_sub3_3);
    }

    public static void method231(boolean flag, Class38_Sub2_Sub3 class38_sub2_sub3) {
        int i = class38_sub2_sub3.method451();
        aCharArrayArray463 = new char[i][];
        anIntArray464 = new int[i];
        if (!flag)
            return;
        for (int j = 0; j < i; j++) {
            anIntArray464[j] = class38_sub2_sub3.method446();
            char[] ac = new char[class38_sub2_sub3.method446()];
            for (int k = 0; k < ac.length; k++)
                ac[k] = (char) class38_sub2_sub3.method446();

            aCharArrayArray463[j] = ac;
        }

    }

    public static void method232(int i, Class38_Sub2_Sub3 class38_sub2_sub3) {
        int j = class38_sub2_sub3.method451();
        if (i != -33152) {
            for (int k = 1; k > 0; k++)
                ;
        }
        aCharArrayArray460 = new char[j][];
        aByteArrayArrayArray461 = new byte[j][][];
        method235(aByteArrayArrayArray461, aCharArrayArray460, class38_sub2_sub3, (byte) 1);
    }

    public static void method233(Class38_Sub2_Sub3 class38_sub2_sub3, int i) {
        int j = class38_sub2_sub3.method451();
        if (i >= 0) {
            return;
        } else {
            aCharArrayArray462 = new char[j][];
            method236(-178, class38_sub2_sub3, aCharArrayArray462);
            return;
        }
    }

    public static void method234(int i, Class38_Sub2_Sub3 class38_sub2_sub3) {
        anIntArray459 = new int[class38_sub2_sub3.method451()];
        for (int j = 0; j < anIntArray459.length; j++)
            anIntArray459[j] = class38_sub2_sub3.method448();

        if (i != anInt451)
            aBoolean450 = !aBoolean450;
    }

    public static void method235(byte[][][] abyte0, char[][] ac, Class38_Sub2_Sub3 class38_sub2_sub3, byte byte0) {
        if (byte0 == 1)
            byte0 = 0;
        else
            return;
        for (int i = 0; i < ac.length; i++) {
            char[] ac1 = new char[class38_sub2_sub3.method446()];
            for (int j = 0; j < ac1.length; j++)
                ac1[j] = (char) class38_sub2_sub3.method446();

            ac[i] = ac1;
            byte[][] abyte1 = new byte[class38_sub2_sub3.method446()][2];
            for (int k = 0; k < abyte1.length; k++) {
                abyte1[k][0] = (byte) class38_sub2_sub3.method446();
                abyte1[k][1] = (byte) class38_sub2_sub3.method446();
            }

            if (abyte1.length > 0)
                abyte0[i] = abyte1;
        }

    }

    public static void method236(int i, Class38_Sub2_Sub3 class38_sub2_sub3, char[][] ac) {
        while (i >= 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        for (int k = 0; k < ac.length; k++) {
            char[] ac1 = new char[class38_sub2_sub3.method446()];
            for (int l = 0; l < ac1.length; l++)
                ac1[l] = (char) class38_sub2_sub3.method446();

            ac[k] = ac1;
        }

    }

    public static void method237(char[] ac, int i) {
        int j = 0;
        for (int k = 0; k < ac.length; k++) {
            if (method238(16180, ac[k]))
                ac[j] = ac[k];
            else
                ac[j] = ' ';
            if (j == 0 || ac[j] != ' ' || ac[j - 1] != ' ')
                j++;
        }

        if (i != 0)
            aBoolean450 = !aBoolean450;
        for (int l = j; l < ac.length; l++)
            ac[l] = ' ';

    }

    public static boolean method238(int i, char c) {
        if (i != anInt454)
            throw new NullPointerException();
        return c >= ' ' && c <= '\177' || c == ' ' || c == '\n' || c == '\t' || c == '\243' || c == '\u20AC';
    }

    public static String method239(String s, int i) {
        long l = System.currentTimeMillis();
        char[] ac = s.toCharArray();
        method237(ac, 0);
        String s1 = (new String(ac)).trim();
        ac = s1.toLowerCase().toCharArray();
        String s2 = s1.toLowerCase();
        method247(ac, 0);
        method242(true, ac);
        method243((byte) 120, ac);
        method256(8, ac);
        for (int j = 0; j < aStringArray465.length; j++) {
            for (int k = -1; (k = s2.indexOf(aStringArray465[j], k + 1)) != -1; ) {
                char[] ac1 = aStringArray465[j].toCharArray();
                for (int j1 = 0; j1 < ac1.length; j1++)
                    ac[j1 + k] = ac1[j1];

            }

        }

        if (i != 0) {
            for (int i1 = 1; i1 > 0; i1++)
                ;
        }
        method240(ac, 135, s1.toCharArray());
        method241((byte) 6, ac);
        long l1 = System.currentTimeMillis();
        return (new String(ac)).trim();
    }

    public static void method240(char[] ac, int i, char[] ac1) {
        for (int j = 0; j < ac1.length; j++)
            if (ac[j] != '*' && method264(0, ac1[j]))
                ac[j] = ac1[j];

        i = 76 / i;
    }

    public static void method241(byte byte0, char[] ac) {
        boolean flag = true;
        if (byte0 == 6)
            byte0 = 0;
        else
            return;
        for (int i = 0; i < ac.length; i++) {
            char c = ac[i];
            if (method261(-175, c)) {
                if (flag) {
                    if (method263((byte) 0, c))
                        flag = false;
                } else if (method264(0, c))
                    ac[i] = (char) ((c + 97) - 65);
            } else {
                flag = true;
            }
        }

    }

    public static void method242(boolean flag, char[] ac) {
        for (int i = 0; i < 2; i++) {
            for (int j = aCharArrayArray460.length - 1; j >= 0; j--)
                method251((byte) -102, aByteArrayArrayArray461[j], ac, aCharArrayArray460[j]);

        }

        if (flag)
            ;
    }

    public static void method243(byte byte0, char[] ac) {
        char[] ac1 = ac.clone();
        char[] ac2 = {
                '(', 'a', ')'
        };
        method251((byte) -102, null, ac1, ac2);
        char[] ac3 = ac.clone();
        char[] ac4 = {
                'd', 'o', 't'
        };
        method251((byte) -102, null, ac3, ac4);
        if (byte0 != 120)
            return;
        for (int i = aCharArrayArray462.length - 1; i >= 0; i--)
            method244(ac3, -706, ac1, aCharArrayArray462[i], ac);

    }

    public static void method244(char[] ac, int i, char[] ac1, char[] ac2, char[] ac3) {
        if (ac2.length > ac3.length)
            return;
        boolean flag = true;
        if (i >= 0)
            anInt454 = -499;
        int j;
        for (int k = 0; k <= ac3.length - ac2.length; k += j) {
            int l = k;
            int i1 = 0;
            j = 1;
            while (l < ac3.length) {
                int j1 = 0;
                char c = ac3[l];
                char c1 = '\0';
                if (l + 1 < ac3.length)
                    c1 = ac3[l + 1];
                if (i1 < ac2.length && (j1 = method253(-81, c1, ac2[i1], c)) > 0) {
                    l += j1;
                    i1++;
                    continue;
                }
                if (i1 == 0)
                    break;
                if ((j1 = method253(-81, c1, ac2[i1 - 1], c)) > 0) {
                    l += j1;
                    if (i1 == 1)
                        j++;
                    continue;
                }
                if (i1 >= ac2.length || !method259(c, 2))
                    break;
                l++;
            }
            if (i1 >= ac2.length) {
                boolean flag1 = false;
                int k1 = method245(k, ac3, (byte) 6, ac1);
                int l1 = method246(ac, ac3, l - 1, -808);
                if (k1 > 2 || l1 > 2)
                    flag1 = true;
                if (flag1) {
                    for (int i2 = k; i2 < l; i2++)
                        ac3[i2] = '*';

                }
            }
        }

    }

    public static int method245(int i, char[] ac, byte byte0, char[] ac1) {
        if (i == 0)
            return 2;
        for (int j = i - 1; j >= 0; j--) {
            if (!method259(ac[j], 2))
                break;
            if (ac[j] == '@')
                return 3;
        }

        if (byte0 == aByte456)
            byte0 = 0;
        else
            return anInt452;
        int k = 0;
        for (int l = i - 1; l >= 0; l--) {
            if (!method259(ac1[l], 2))
                break;
            if (ac1[l] == '*')
                k++;
        }

        if (k >= 3)
            return 4;
        return !method259(ac[i - 1], 2) ? 0 : 1;
    }

    public static int method246(char[] ac, char[] ac1, int i, int j) {
        if (j >= 0)
            return anInt453;
        if (i + 1 == ac1.length)
            return 2;
        for (int k = i + 1; k < ac1.length; k++) {
            if (!method259(ac1[k], 2))
                break;
            if (ac1[k] == '.' || ac1[k] == ',')
                return 3;
        }

        int l = 0;
        for (int i1 = i + 1; i1 < ac1.length; i1++) {
            if (!method259(ac[i1], 2))
                break;
            if (ac[i1] == '*')
                l++;
        }

        if (l >= 3)
            return 4;
        return !method259(ac1[i + 1], 2) ? 0 : 1;
    }

    public static void method247(char[] ac, int i) {
        char[] ac1 = ac.clone();
        char[] ac2 = {
                'd', 'o', 't'
        };
        method251((byte) -102, null, ac1, ac2);
        char[] ac3 = ac.clone();
        char[] ac4 = {
                's', 'l', 'a', 's', 'h'
        };
        if (i != 0)
            aBoolean450 = !aBoolean450;
        method251((byte) -102, null, ac3, ac4);
        for (int j = 0; j < aCharArrayArray463.length; j++)
            method248(ac3, anIntArray464[j], true, ac, aCharArrayArray463[j], ac1);

    }

    public static void method248(char[] ac, int i, boolean flag, char[] ac1, char[] ac2, char[] ac3) {
        if (!flag) {
            for (int j = 1; j > 0; j++)
                ;
        }
        if (ac2.length > ac1.length)
            return;
        boolean flag1 = true;
        int k;
        for (int l = 0; l <= ac1.length - ac2.length; l += k) {
            int i1 = l;
            int j1 = 0;
            k = 1;
            while (i1 < ac1.length) {
                int k1 = 0;
                char c = ac1[i1];
                char c1 = '\0';
                if (i1 + 1 < ac1.length)
                    c1 = ac1[i1 + 1];
                if (j1 < ac2.length && (k1 = method253(-81, c1, ac2[j1], c)) > 0) {
                    i1 += k1;
                    j1++;
                    continue;
                }
                if (j1 == 0)
                    break;
                if ((k1 = method253(-81, c1, ac2[j1 - 1], c)) > 0) {
                    i1 += k1;
                    if (j1 == 1)
                        k++;
                    continue;
                }
                if (j1 >= ac2.length || !method259(c, 2))
                    break;
                i1++;
            }
            if (j1 >= ac2.length) {
                boolean flag2 = false;
                int l1 = method249(ac1, false, ac3, l);
                int i2 = method250(ac, -678, i1 - 1, ac1);
                if (i == 1 && l1 > 0 && i2 > 0)
                    flag2 = true;
                if (i == 2 && (l1 > 2 && i2 > 0 || l1 > 0 && i2 > 2))
                    flag2 = true;
                if (i == 3 && l1 > 0 && i2 > 2)
                    flag2 = true;
                boolean _tmp = i == 3 && l1 > 2 && i2 > 0;
                if (flag2) {
                    int j2 = l;
                    int k2 = i1 - 1;
                    if (l1 > 2) {
                        if (l1 == 4) {
                            boolean flag3 = false;
                            for (int i3 = j2 - 1; i3 >= 0; i3--)
                                if (flag3) {
                                    if (ac3[i3] != '*')
                                        break;
                                    j2 = i3;
                                } else if (ac3[i3] == '*') {
                                    j2 = i3;
                                    flag3 = true;
                                }

                        }
                        boolean flag4 = false;
                        for (int j3 = j2 - 1; j3 >= 0; j3--)
                            if (flag4) {
                                if (method259(ac1[j3], 2))
                                    break;
                                j2 = j3;
                            } else if (!method259(ac1[j3], 2)) {
                                flag4 = true;
                                j2 = j3;
                            }

                    }
                    if (i2 > 2) {
                        if (i2 == 4) {
                            boolean flag5 = false;
                            for (int k3 = k2 + 1; k3 < ac1.length; k3++)
                                if (flag5) {
                                    if (ac[k3] != '*')
                                        break;
                                    k2 = k3;
                                } else if (ac[k3] == '*') {
                                    k2 = k3;
                                    flag5 = true;
                                }

                        }
                        boolean flag6 = false;
                        for (int l3 = k2 + 1; l3 < ac1.length; l3++)
                            if (flag6) {
                                if (method259(ac1[l3], 2))
                                    break;
                                k2 = l3;
                            } else if (!method259(ac1[l3], 2)) {
                                flag6 = true;
                                k2 = l3;
                            }

                    }
                    for (int l2 = j2; l2 <= k2; l2++)
                        ac1[l2] = '*';

                }
            }
        }

    }

    public static int method249(char[] ac, boolean flag, char[] ac1, int i) {
        if (i == 0)
            return 2;
        for (int j = i - 1; j >= 0; j--) {
            if (!method259(ac[j], 2))
                break;
            if (ac[j] == ',' || ac[j] == '.')
                return 3;
        }

        int k = 0;
        if (flag) {
            for (int l = 1; l > 0; l++)
                ;
        }
        for (int i1 = i - 1; i1 >= 0; i1--) {
            if (!method259(ac1[i1], 2))
                break;
            if (ac1[i1] == '*')
                k++;
        }

        if (k >= 3)
            return 4;
        return !method259(ac[i - 1], 2) ? 0 : 1;
    }

    public static int method250(char[] ac, int i, int j, char[] ac1) {
        if (j + 1 == ac1.length)
            return 2;
        for (int k = j + 1; k < ac1.length; k++) {
            if (!method259(ac1[k], 2))
                break;
            if (ac1[k] == '\\' || ac1[k] == '/')
                return 3;
        }

        int l = 0;
        for (int i1 = j + 1; i1 < ac1.length; i1++) {
            if (!method259(ac[i1], 2))
                break;
            if (ac[i1] == '*')
                l++;
        }

        if (i >= 0)
            return 3;
        if (l >= 5)
            return 4;
        return !method259(ac1[j + 1], 2) ? 0 : 1;
    }

    public static void method251(byte byte0, byte[][] abyte0, char[] ac, char[] ac1) {
        if (ac1.length > ac.length)
            return;
        boolean flag = true;
        if (byte0 != -102)
            anInt455 = -198;
        int i;
        for (int j = 0; j <= ac.length - ac1.length; j += i) {
            int k = j;
            int l = 0;
            int i1 = 0;
            i = 1;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            while (k < ac.length && (!flag2 || !flag3)) {
                int j1 = 0;
                char c = ac[k];
                char c2 = '\0';
                if (k + 1 < ac.length)
                    c2 = ac[k + 1];
                if (l < ac1.length && (j1 = method254(c2, ac1[l], c, 7326)) > 0) {
                    if (j1 == 1 && method262(c, 10361))
                        flag2 = true;
                    if (j1 == 2 && (method262(c, 10361) || method262(c2, 10361)))
                        flag2 = true;
                    k += j1;
                    l++;
                    continue;
                }
                if (l == 0)
                    break;
                if ((j1 = method254(c2, ac1[l - 1], c, 7326)) > 0) {
                    k += j1;
                    if (l == 1)
                        i++;
                    continue;
                }
                if (l >= ac1.length || !method260(c, (byte) 13))
                    break;
                if (method259(c, 2) && c != '\'')
                    flag1 = true;
                if (method262(c, 10361))
                    flag3 = true;
                k++;
                if ((++i1 * 100) / (k - j) > 90)
                    break;
            }
            if (l >= ac1.length && (!flag2 || !flag3)) {
                boolean flag4 = true;
                if (!flag1) {
                    char c1 = ' ';
                    if (j - 1 >= 0)
                        c1 = ac[j - 1];
                    char c3 = ' ';
                    if (k < ac.length)
                        c3 = ac[k];
                    byte byte1 = method255(0, c1);
                    byte byte2 = method255(0, c3);
                    if (abyte0 != null && method252(841, byte1, abyte0, byte2))
                        flag4 = false;
                } else {
                    boolean flag5 = false;
                    boolean flag6 = false;
                    if (j - 1 < 0 || method259(ac[j - 1], 2) && ac[j - 1] != '\'')
                        flag5 = true;
                    if (k >= ac.length || method259(ac[k], 2) && ac[k] != '\'')
                        flag6 = true;
                    if (!flag5 || !flag6) {
                        boolean flag7 = false;
                        int j2 = j - 2;
                        if (flag5)
                            j2 = j;
                        for (; !flag7 && j2 < k; j2++)
                            if (j2 >= 0 && (!method259(ac[j2], 2) || ac[j2] == '\'')) {
                                char[] ac2 = new char[3];
                                int l2;
                                for (l2 = 0; l2 < 3; l2++) {
                                    if (j2 + l2 >= ac.length || method259(ac[j2 + l2], 2) && ac[j2 + l2] != '\'')
                                        break;
                                    ac2[l2] = ac[j2 + l2];
                                }

                                boolean flag8 = l2 != 0;
                                if (l2 < 3 && j2 - 1 >= 0 && (!method259(ac[j2 - 1], 2) || ac[j2 - 1] == '\''))
                                    flag8 = false;
                                if (flag8 && !method265(ac2, 6))
                                    flag7 = true;
                            }

                        if (!flag7)
                            flag4 = false;
                    }
                }
                if (flag4) {
                    int k1 = 0;
                    int l1 = 0;
                    for (int i2 = j; i2 < k; i2++)
                        if (method262(ac[i2], 10361))
                            k1++;
                        else if (method261(-175, ac[i2]))
                            l1++;

                    if (k1 <= l1) {
                        for (int k2 = j; k2 < k; k2++)
                            ac[k2] = '*';

                    }
                }
            }
        }

    }

    public static boolean method252(int i, byte byte0, byte[][] abyte0, byte byte1) {
        if (i <= 0)
            aBoolean458 = !aBoolean458;
        int j = 0;
        if (abyte0[j][0] == byte0 && abyte0[j][1] == byte1)
            return true;
        int k = abyte0.length - 1;
        if (abyte0[k][0] == byte0 && abyte0[k][1] == byte1)
            return true;
        do {
            int l = (j + k) / 2;
            if (abyte0[l][0] == byte0 && abyte0[l][1] == byte1)
                return true;
            if (byte0 < abyte0[l][0] || byte0 == abyte0[l][0] && byte1 < abyte0[l][1])
                k = l;
            else
                j = l;
        } while (j != k && j + 1 != k);
        return false;
    }

    public static int method253(int i, char c, char c1, char c2) {
        while (i >= 0) {
            for (int j = 1; j > 0; j++)
                ;
        }
        if (c1 == c2)
            return 1;
        if (c1 == 'o' && c2 == '0')
            return 1;
        if (c1 == 'o' && c2 == '(' && c == ')')
            return 2;
        if (c1 == 'c' && (c2 == '(' || c2 == '<' || c2 == '['))
            return 1;
        if (c1 == 'e' && c2 == '\u20AC')
            return 1;
        if (c1 == 's' && c2 == '$')
            return 1;
        return c1 != 'l' || c2 != 'i' ? 0 : 1;
    }

    public static int method254(char c, char c1, char c2, int i) {
        if (i != 7326) {
            for (int j = 1; j > 0; j++)
                ;
        }
        if (c1 == c2)
            return 1;
        if (c1 >= 'a' && c1 <= 'm') {
            if (c1 == 'a') {
                if (c2 == '4' || c2 == '@' || c2 == '^')
                    return 1;
                return c2 != '/' || c != '\\' ? 0 : 2;
            }
            if (c1 == 'b') {
                if (c2 == '6' || c2 == '8')
                    return 1;
                return c2 != '1' || c != '3' ? 0 : 2;
            }
            if (c1 == 'c')
                return c2 != '(' && c2 != '<' && c2 != '{' && c2 != '[' ? 0 : 1;
            if (c1 == 'd')
                return c2 != '[' || c != ')' ? 0 : 2;
            if (c1 == 'e')
                return c2 != '3' && c2 != '\u20AC' ? 0 : 1;
            if (c1 == 'f') {
                if (c2 == 'p' && c == 'h')
                    return 2;
                return c2 != '\243' ? 0 : 1;
            }
            if (c1 == 'g')
                return c2 != '9' && c2 != '6' ? 0 : 1;
            if (c1 == 'h')
                return c2 != '#' ? 0 : 1;
            if (c1 == 'i')
                return c2 != 'y' && c2 != 'l' && c2 != 'j' && c2 != '1' && c2 != '!' && c2 != ':' && c2 != ';'
                        && c2 != '|' ? 0 : 1;
            if (c1 == 'j')
                return 0;
            if (c1 == 'k')
                return 0;
            if (c1 == 'l')
                return c2 != '1' && c2 != '|' && c2 != 'i' ? 0 : 1;
            if (c1 == 'm')
                return 0;
        }
        if (c1 >= 'n' && c1 <= 'z') {
            if (c1 == 'n')
                return 0;
            if (c1 == 'o') {
                if (c2 == '0' || c2 == '*')
                    return 1;
                return (c2 != '(' || c != ')') && (c2 != '[' || c != ']') && (c2 != '{' || c != '}')
                        && (c2 != '<' || c != '>') ? 0 : 2;
            }
            if (c1 == 'p')
                return 0;
            if (c1 == 'q')
                return 0;
            if (c1 == 'r')
                return 0;
            if (c1 == 's')
                return c2 != '5' && c2 != 'z' && c2 != '$' && c2 != '2' ? 0 : 1;
            if (c1 == 't')
                return c2 != '7' && c2 != '+' ? 0 : 1;
            if (c1 == 'u') {
                if (c2 == 'v')
                    return 1;
                return (c2 != '\\' || c != '/') && (c2 != '\\' || c != '|') && (c2 != '|' || c != '/') ? 0 : 2;
            }
            if (c1 == 'v')
                return (c2 != '\\' || c != '/') && (c2 != '\\' || c != '|') && (c2 != '|' || c != '/') ? 0 : 2;
            if (c1 == 'w')
                return c2 != 'v' || c != 'v' ? 0 : 2;
            if (c1 == 'x')
                return (c2 != ')' || c != '(') && (c2 != '}' || c != '{') && (c2 != ']' || c != '[')
                        && (c2 != '>' || c != '<') ? 0 : 2;
            if (c1 == 'y')
                return 0;
            if (c1 == 'z')
                return 0;
        }
        if (c1 >= '0' && c1 <= '9') {
            if (c1 == '0') {
                if (c2 == 'o' || c2 == 'O')
                    return 1;
                return (c2 != '(' || c != ')') && (c2 != '{' || c != '}') && (c2 != '[' || c != ']') ? 0 : 2;
            }
            if (c1 == '1')
                return c2 != 'l' ? 0 : 1;
            else
                return 0;
        }
        if (c1 == ',')
            return c2 != '.' ? 0 : 1;
        if (c1 == '.')
            return c2 != ',' ? 0 : 1;
        if (c1 == '!')
            return c2 != 'i' ? 0 : 1;
        else
            return 0;
    }

    public static byte method255(int i, char c) {
        if (i != 0)
            throw new NullPointerException();
        if (c >= 'a' && c <= 'z')
            return (byte) ((c - 97) + 1);
        if (c == '\'')
            return 28;
        if (c >= '0' && c <= '9')
            return (byte) ((c - 48) + 29);
        else
            return 27;
    }

    public static void method256(int i, char[] ac) {
        int j = 0;
        int k = 0;
        if (i != 8)
            return;
        int l = 0;
        int i1 = 0;
        while ((j = method257(149, ac, k)) != -1) {
            boolean flag = false;
            for (int j1 = k; j1 >= 0 && j1 < j && !flag; j1++)
                if (!method259(ac[j1], 2) && !method260(ac[j1], (byte) 13))
                    flag = true;

            if (flag)
                l = 0;
            if (l == 0)
                i1 = j;
            k = method258(6, j, ac);
            int k1 = 0;
            for (int l1 = j; l1 < k; l1++)
                k1 = (k1 * 10 + ac[l1]) - 48;

            if (k1 > 255 || k - j > 8)
                l = 0;
            else
                l++;
            if (l == 4) {
                for (int i2 = i1; i2 < k; i2++)
                    ac[i2] = '*';

                l = 0;
            }
        }
    }

    public static int method257(int i, char[] ac, int j) {
        i = 66 / i;
        for (int k = j; k < ac.length && k >= 0; k++)
            if (ac[k] >= '0' && ac[k] <= '9')
                return k;

        return -1;
    }

    public static int method258(int i, int j, char[] ac) {
        if (i != 6)
            return 4;
        for (int k = j; k < ac.length && k >= 0; k++)
            if (ac[k] < '0' || ac[k] > '9')
                return k;

        return ac.length;
    }

    public static boolean method259(char c, int i) {
        if (i != 2)
            throw new NullPointerException();
        return !method261(-175, c) && !method262(c, 10361);
    }

    public static boolean method260(char c, byte byte0) {
        if (byte0 != 13)
            throw new NullPointerException();
        if (c < 'a' || c > 'z')
            return true;
        return c == 'v' || c == 'x' || c == 'j' || c == 'q' || c == 'z';
    }

    public static boolean method261(int i, char c) {
        if (i >= 0)
            throw new NullPointerException();
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    public static boolean method262(char c, int i) {
        if (i != 10361)
            anInt451 = -124;
        return c >= '0' && c <= '9';
    }

    public static boolean method263(byte byte0, char c) {
        if (byte0 != 0)
            anInt452 = -254;
        return c >= 'a' && c <= 'z';
    }

    public static boolean method264(int i, char c) {
        if (i < 0 || i > 0)
            aBoolean450 = !aBoolean450;
        return c >= 'A' && c <= 'Z';
    }

    public static boolean method265(char[] ac, int i) {
        boolean flag = true;
        for (int j = 0; j < ac.length; j++)
            if (!method262(ac[j], 10361) && ac[j] != 0)
                flag = false;

        if (flag)
            return true;
        int k = method266(5, ac);
        int l = 0;
        if (i != 6) {
            for (int i1 = 1; i1 > 0; i1++)
                ;
        }
        int j1 = anIntArray459.length - 1;
        if (k == anIntArray459[l] || k == anIntArray459[j1])
            return true;
        do {
            int k1 = (l + j1) / 2;
            if (k == anIntArray459[k1])
                return true;
            if (k < anIntArray459[k1])
                j1 = k1;
            else
                l = k1;
        } while (l != j1 && l + 1 != j1);
        return false;
    }

    public static int method266(int i, char[] ac) {
        if (ac.length > 6)
            return 0;
        int j = 0;
        for (int k = 0; k < ac.length; k++) {
            char c = ac[ac.length - k - 1];
            if (c >= 'a' && c <= 'z')
                j = j * 38 + ((c - 97) + 1);
            else if (c == '\'')
                j = j * 38 + 27;
            else if (c >= '0' && c <= '9')
                j = j * 38 + ((c - 48) + 28);
            else if (c != 0)
                return 0;
        }

        if (i < 5 || i > 5) {
            for (int l = 1; l > 0; l++)
                ;
        }
        return j;
    }

    public static boolean aBoolean450;
    public static int anInt451 = 24882;
    public static int anInt452 = -178;
    public static int anInt453;
    public static int anInt454 = 16180;
    public static int anInt455 = 383;
    public static byte aByte456 = 6;
    public static int anInt457 = -81;
    public static boolean aBoolean458;
    public static int[] anIntArray459;
    public static char[][] aCharArrayArray460;
    public static byte[][][] aByteArrayArrayArray461;
    public static char[][] aCharArrayArray462;
    public static char[][] aCharArrayArray463;
    public static int[] anIntArray464;
    public static final String[] aStringArray465 = {
            "cook", "cook's", "cooks", "seeks", "sheet"
    };
    public static int anInt466;

}
