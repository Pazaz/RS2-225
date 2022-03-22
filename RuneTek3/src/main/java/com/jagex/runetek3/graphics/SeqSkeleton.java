package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

public class SeqSkeleton {

    public static final int OP_BASE = 0;
    public static final int OP_TRANSLATE = 1;
    public static final int OP_ROTATE = 2;
    public static final int OP_SCALE = 3;
    public static final int OP_ALPHA = 5;

    public static SeqSkeleton[] instances;
    public int length;
    public int[] transformTypes;
    public int[][] groupLabels;
    public SeqSkeleton() {
    }

    public static void load(FileArchive fileArchive) {
        Buffer head = new Buffer(fileArchive.read("base_head.dat"));
        Buffer type = new Buffer(fileArchive.read("base_type.dat"));
        Buffer label = new Buffer(fileArchive.read("base_label.dat"));

        int total = head.g2();
        instances = new SeqSkeleton[head.g2() + 1];

        for (int i = 0; i < total; i++) {
            int index = head.g2();

            int length = head.g1();
            int[] transformTypes = new int[length];
            int[][] groupLabels = new int[length][];

            for (int group = 0; group < length; group++) {
                transformTypes[group] = type.g1();

                int groupCount = label.g1();
                groupLabels[group] = new int[groupCount];

                for (int child = 0; child < groupCount; child++) {
                    groupLabels[group][child] = label.g1();
                }
            }

            instances[index] = new SeqSkeleton();
            instances[index].length = length;
            instances[index].transformTypes = transformTypes;
            instances[index].groupLabels = groupLabels;
        }
    }
}
