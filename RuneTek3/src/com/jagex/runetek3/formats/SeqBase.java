package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

public class SeqBase {

    public static void load(FileArchive fileArchive) {
        Buffer head = new Buffer(fileArchive.read("base_head.dat", null));
        Buffer type = new Buffer(fileArchive.read("base_type.dat", null));
        Buffer label = new Buffer(fileArchive.read("base_label.dat", null));

        int total = head.g2();
        instances = new SeqBase[head.g2() + 1];

        for (int i = 0; i < total; i++) {
            int index = head.g2();

            int length = head.g1();
            int[] transformTypes = new int[length];
            int[][] groups = new int[length][];

            for (int j = 0; j < length; j++) {
                transformTypes[j] = type.g1();

                int groupCount = label.g1();
                groups[j] = new int[groupCount];

                for (int k = 0; k < groupCount; k++) {
                    groups[j][k] = label.g1();
                }
            }

            instances[index] = new SeqBase();
            instances[index].length = length;
            instances[index].types = transformTypes;
            instances[index].groupLabels = groups;
        }
    }

    public SeqBase() {
    }

    public static SeqBase[] instances;
    public int length;
    public int[] types;
    public int[][] groupLabels;
}
