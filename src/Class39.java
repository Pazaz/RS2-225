// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class39 {

    public Class39(byte[] abyte0, boolean flag) {
        aBoolean658 = false;
        aByte659 = 2;
        anInt660 = 40267;
        aBoolean661 = false;
        if (flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        method473(true, abyte0);
    }

    public void method473(boolean flag, byte[] abyte0) {
        Class38_Sub2_Sub3 class38_sub2_sub3 = new Class38_Sub2_Sub3(363, abyte0);
        int i = class38_sub2_sub3.method450();
        int j = class38_sub2_sub3.method450();
        if (j != i) {
            byte[] abyte1 = new byte[i];
            Class33.method333(abyte1, i, abyte0, j, 6);
            aByteArray662 = abyte1;
            class38_sub2_sub3 = new Class38_Sub2_Sub3(363, aByteArray662);
            aBoolean668 = true;
        } else {
            aByteArray662 = abyte0;
            aBoolean668 = false;
        }
        anInt663 = class38_sub2_sub3.method448();
        anIntArray664 = new int[anInt663];
        anIntArray665 = new int[anInt663];
        anIntArray666 = new int[anInt663];
        anIntArray667 = new int[anInt663];
        if (!flag)
            return;
        int k = class38_sub2_sub3.anInt1329 + anInt663 * 10;
        for (int l = 0; l < anInt663; l++) {
            anIntArray664[l] = class38_sub2_sub3.method451();
            anIntArray665[l] = class38_sub2_sub3.method450();
            anIntArray666[l] = class38_sub2_sub3.method450();
            anIntArray667[l] = k;
            k += anIntArray666[l];
        }

    }

    public byte[] method474(String s, byte[] abyte0, byte byte0) {
        int i = 0;
        s = s.toUpperCase();
        for (int j = 0; j < s.length(); j++)
            i = (i * 61 + s.charAt(j)) - 32;

        for (int k = 0; k < anInt663; k++)
            if (anIntArray664[k] == i) {
                if (abyte0 == null)
                    abyte0 = new byte[anIntArray665[k]];
                if (!aBoolean668) {
                    Class33.method333(abyte0, anIntArray665[k], aByteArray662, anIntArray666[k], anIntArray667[k]);
                } else {
                    for (int l = 0; l < anIntArray665[k]; l++)
                        abyte0[l] = aByteArray662[anIntArray667[k] + l];

                }
                return abyte0;
            }

        if (byte0 != aByte659)
            throw new NullPointerException();
        else
            return null;
    }

    public boolean aBoolean658;
    public byte aByte659;
    public int anInt660;
    public boolean aBoolean661;
    public byte[] aByteArray662;
    public int anInt663;
    public int[] anIntArray664;
    public int[] anIntArray665;
    public int[] anIntArray666;
    public int[] anIntArray667;
    public boolean aBoolean668;
}
