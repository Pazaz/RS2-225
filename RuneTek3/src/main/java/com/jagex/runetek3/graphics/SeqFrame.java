package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

public class SeqFrame {

    public static SeqFrame[] instances;
    public int delay;
    public SeqSkeleton transform;
    public int groupCount;
    public int[] groups;
    public int[] x;
    public int[] y;
    public int[] z;
    public SeqFrame() {
    }

    public static void load(FileArchive fileArchive) {
        Buffer head = new Buffer(fileArchive.read("frame_head.dat"));
        Buffer tran1 = new Buffer(fileArchive.read("frame_tran1.dat"));
        Buffer tran2 = new Buffer(fileArchive.read("frame_tran2.dat"));
        Buffer del = new Buffer(fileArchive.read("frame_del.dat"));

        int frameCount = head.g2();
        int totalFrames = head.g2();

        instances = new SeqFrame[totalFrames + 1];

        int[] labels = new int[500];
        int[] x = new int[500];
        int[] y = new int[500];
        int[] z = new int[500];

        for (int i = 0; i < frameCount; i++) {
            SeqFrame frame = instances[head.g2()] = new SeqFrame();
            frame.delay = del.g1();

            SeqSkeleton base = SeqSkeleton.instances[head.g2()];
            frame.transform = base;

            int groupCount = head.g1();
            int lastGroup = -1;
            int count = 0;

            for (int n = 0; n < groupCount; n++) {
                int flags = tran1.g1();

                if (flags > 0) {
                    if (base.transformTypes[n] != SeqSkeleton.OP_BASE) {
                        for (int group = n - 1; group > lastGroup; group--) {
                            if (base.transformTypes[group] != SeqSkeleton.OP_BASE) {
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
                    if (base.transformTypes[labels[count]] == SeqSkeleton.OP_SCALE) {
                        defaultValue = 128;
                    }

                    if ((flags & 1) != 0) {
                        x[count] = tran2.gSmart1or2s();
                    } else {
                        x[count] = defaultValue;
                    }

                    if ((flags & 2) != 0) {
                        y[count] = tran2.gSmart1or2s();
                    } else {
                        y[count] = defaultValue;
                    }

                    if ((flags & 4) != 0) {
                        z[count] = tran2.gSmart1or2s();
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
}
