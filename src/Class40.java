// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class40 {

    public static long method475(String s) {
        long l = 0L;
        for (int i = 0; i < s.length() && i < 12; i++) {
            char c = s.charAt(i);
            l *= 37L;
            if (c >= 'A' && c <= 'Z')
                l += (1 + c) - 65;
            else if (c >= 'a' && c <= 'z')
                l += (1 + c) - 97;
            else if (c >= '0' && c <= '9')
                l += (27 + c) - 48;
        }

        for (; l % 37L == 0L && l != 0L; l /= 37L)
            ;
        return l;
    }

    public static String method476(long l, boolean flag) {
        if (l <= 0L || l >= 0x5b5b57f8a98a5dd1L)
            return "invalid_name";
        if (l % 37L == 0L)
            return "invalid_name";
        int i = 0;
        if (flag)
            anInt673 = -363;
        while (l != 0L) {
            long l1 = l;
            l /= 37L;
            aCharArray674[11 - i++] = aCharArray675[(int) (l1 - l * 37L)];
        }
        return new String(aCharArray674, 12 - i, i);
    }

    public static long method477(int i, String s) {
        if (i != 0)
            throw new NullPointerException();
        s = s.toUpperCase();
        long l = 0L;
        for (int j = 0; j < s.length(); j++) {
            l = (l * 61L + (long) s.charAt(j)) - 32L;
            l = l + (l >> 56) & 0xffffffffffffffL;
        }

        return l;
    }

    public static String method478(int i, int j) {
        if (i >= 0)
            aBoolean669 = !aBoolean669;
        return (j >> 24 & 0xff) + "." + (j >> 16 & 0xff) + "." + (j >> 8 & 0xff) + "." + (j & 0xff);
    }

    public static String method479(int i, String s) {
        if (i != 0)
            throw new NullPointerException();
        if (s.length() > 0) {
            char[] ac = s.toCharArray();
            for (int j = 0; j < ac.length; j++)
                if (ac[j] == '_') {
                    ac[j] = ' ';
                    if (j + 1 < ac.length && ac[j + 1] >= 'a' && ac[j + 1] <= 'z')
                        ac[j + 1] = (char) ((ac[j + 1] + 65) - 97);
                }

            if (ac[0] >= 'a' && ac[0] <= 'z')
                ac[0] = (char) ((ac[0] + 65) - 97);
            return new String(ac);
        } else {
            return s;
        }
    }

    public static String method480(String s, int i) {
        s = s.toLowerCase();
        if (i != 0)
            anInt671 = -18;
        char[] ac = s.toCharArray();
        int j = ac.length;
        boolean flag = true;
        for (int k = 0; k < j; k++) {
            char c = ac[k];
            if (flag && c >= 'a' && c <= 'z') {
                ac[k] += '\uFFE0';
                flag = false;
            }
            if (c == '.' || c == '!')
                flag = true;
        }

        return new String(ac);
    }

    public static String method481(int i, String s) {
        String s1 = "";
        if (i != 7)
            throw new NullPointerException();
        for (int j = 0; j < s.length(); j++)
            s1 = s1 + "*";

        return s1;
    }

    public static boolean aBoolean669;
    public static byte aByte670 = 25;
    public static int anInt671 = 3;
    public static boolean aBoolean672 = true;
    public static int anInt673 = 629;
    public static char[] aCharArray674 = new char[12];
    public static char[] aCharArray675 = {
            '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9'
    };

}
