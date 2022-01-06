package com.runescape.cache;

import com.runescape.util.Buffer;

public class SeqFrame {

    public static void load(FileArchive fileArchive) {
        Buffer head = new Buffer(fileArchive.read("frame_head.dat", null));
        Buffer tran1 = new Buffer(fileArchive.read("frame_tran1.dat", null));
        Buffer tran2 = new Buffer(fileArchive.read("frame_tran2.dat", null));
        Buffer del = new Buffer(fileArchive.read("frame_del.dat", null));

        int frameCount = head.readWord();
        int totalFrames = head.readWord();

        instance = new SeqFrame[totalFrames + 1];

        int[] labels = new int[500];
        int[] x = new int[500];
        int[] y = new int[500];
        int[] z = new int[500];

        for (int i = 0; i < frameCount; i++) {
            SeqFrame frame = instance[head.readWord()] = new SeqFrame();
            frame.delay = del.readByte();
            
            SeqBase base = SeqBase.instance[head.readWord()];
            frame.transform = base;

            int groupCount = head.readByte();
            int lastGroup = -1;
            int count = 0;

            for (int n = 0; n < groupCount; n++) {
                int flags = tran1.readByte();

                if (flags > 0) {
                    if (base.types[n] != 0) {
                        for (int group = n - 1; group > lastGroup; group--) {
                            if (base.types[group] != 0) {
                                continue;
                            }

                            labels[count] = group;
                            x[count] = 0;
                            y[count] = 0;
                            z[count] = 0;
                            count++;
                            break;
                        }
                    }

                    labels[count] = n;

                    int defaultValue = 0;
                    if (base.types[labels[count]] == 3) {
                        defaultValue = 128;
                    }

                    if ((flags & 1) != 0) {
                        x[count] = tran2.readSmartSigned();
                    } else {
                        x[count] = defaultValue;
                    }

                    if ((flags & 2) != 0) {
                        y[count] = tran2.readSmartSigned();
                    } else {
                        y[count] = defaultValue;
                    }

                    if ((flags & 4) != 0) {
                        z[count] = tran2.readSmartSigned();
                    } else {
                        z[count] = defaultValue;
                    }

                    lastGroup = n;
                    count++;
                }
            }

            frame.groupCount = count;
            frame.groups = new int[count];
            frame.x = new int[count];
            frame.y = new int[count];
            frame.z = new int[count];

            for (int j = 0; j < count; j++) {
                frame.groups[j] = labels[j];
                frame.x[j] = x[j];
                frame.y[j] = y[j];
                frame.z[j] = z[j];
            }
        }
    }

    public SeqFrame() {
    }

    public static SeqFrame[] instance;
    public int delay;
    public SeqBase transform;
    public int groupCount;
    public int[] groups;
    public int[] x;
    public int[] y;
    public int[] z;
}
