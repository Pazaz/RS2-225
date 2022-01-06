package com.runescape.cache;

import com.runescape.util.Buffer;

public class SeqType {

    public static void method222(FileArchive fileArchive, int i) {
        Buffer class38_sub2_sub3 = new Buffer(fileArchive.read("seq.dat", null));
        if (i <= 0)
            aBoolean361 = !aBoolean361;
        anInt363 = class38_sub2_sub3.readWord();
        if (animations == null)
            animations = new SeqType[anInt363];
        for (int j = 0; j < anInt363; j++) {
            if (animations[j] == null)
                animations[j] = new SeqType();
            animations[j].method223(false, class38_sub2_sub3);
        }

    }

    public void method223(boolean flag, Buffer class38_sub2_sub3) {
        if (flag) {
            for (int i = 1; i > 0; i++)
                ;
        }
        do {
            int j = class38_sub2_sub3.readByte();
            if (j == 0)
                break;
            if (j == 1) {
                frameCount = class38_sub2_sub3.readByte();
                primaryFrames = new int[frameCount];
                anIntArray367 = new int[frameCount];
                instances = new int[frameCount];
                for (int k = 0; k < frameCount; k++) {
                    primaryFrames[k] = class38_sub2_sub3.readWord();
                    anIntArray367[k] = class38_sub2_sub3.readWord();
                    if (anIntArray367[k] == 65535)
                        anIntArray367[k] = -1;
                    instances[k] = class38_sub2_sub3.readWord();
                    if (instances[k] == 0)
                        instances[k] = SeqFrame.seqFrames[primaryFrames[k]].anInt236;
                    if (instances[k] == 0)
                        instances[k] = 1;
                }

            } else if (j == 2)
                loopOffset = class38_sub2_sub3.readWord();
            else if (j == 3) {
                int l = class38_sub2_sub3.readByte();
                labelGroups = new int[l + 1];
                for (int i1 = 0; i1 < l; i1++)
                    labelGroups[i1] = class38_sub2_sub3.readByte();

                labelGroups[l] = 0x98967f;
            } else if (j == 4)
                aBoolean371 = true;
            else if (j == 5)
                priority = class38_sub2_sub3.readByte();
            else if (j == 6)
                anInt373 = class38_sub2_sub3.readWord();
            else if (j == 7)
                anInt374 = class38_sub2_sub3.readWord();
            else if (j == 8)
                anInt375 = class38_sub2_sub3.readByte();
            else
                System.out.println("Error unrecognised seq config code: " + j);
        } while (true);
        if (frameCount == 0) {
            frameCount = 1;
            primaryFrames = new int[1];
            primaryFrames[0] = -1;
            anIntArray367 = new int[1];
            anIntArray367[0] = -1;
            instances = new int[1];
            instances[0] = -1;
        }
    }

    public SeqType() {
        loopOffset = -1;
        aBoolean371 = false;
        priority = 5;
        anInt373 = -1;
        anInt374 = -1;
        anInt375 = 99;
    }

    public static boolean aBoolean361 = true;
    public static int anInt362 = 473;
    public static int anInt363;
    public static SeqType[] animations;
    public int frameCount;
    public int[] primaryFrames;
    public int[] anIntArray367;
    public int[] instances;
    public int loopOffset;
    public int[] labelGroups;
    public boolean aBoolean371;
    public int priority;
    public int anInt373;
    public int anInt374;
    public int anInt375;
    public static boolean aBoolean376;

}
