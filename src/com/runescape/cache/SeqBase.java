package com.runescape.cache;

import com.runescape.util.Buffer;

public class SeqBase {

    public static void load(FileArchive fileArchive) {
        Buffer head = new Buffer(fileArchive.read("base_head.dat", null));
        Buffer type = new Buffer(fileArchive.read("base_type.dat", null));
        Buffer label = new Buffer(fileArchive.read("base_label.dat", null));

        int total = head.readWord();
        instance = new SeqBase[head.readWord() + 1];

        for (int i = 0; i < total; i++) {
            int index = head.readWord();
            
            int length = head.readByte();
            int[] transformTypes = new int[length];
            int[][] groups = new int[length][];
            
            for (int j = 0; j < length; j++) {
                transformTypes[j] = type.readByte();

                int groupCount = label.readByte();
                groups[j] = new int[groupCount];

                for (int k = 0; k < groupCount; k++) {
                    groups[j][k] = label.readByte();
                }
            }

            instance[index] = new SeqBase();
            instance[index].length = length;
            instance[index].types = transformTypes;
            instance[index].groupLabels = groups;
        }

    }

    public SeqBase() {
    }

    public static SeqBase[] instance;
    public int length;
    public int[] types;
    public int[][] groupLabels;
}
