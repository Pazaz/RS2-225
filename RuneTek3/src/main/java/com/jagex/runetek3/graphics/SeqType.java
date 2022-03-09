package com.jagex.runetek3.graphics;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Signlink;

public class SeqType {

    public static int count;
    public static SeqType[] instances;
    public int frameCount;
    public int[] primaryFrames;
    public int[] secondaryFrames;
    public int[] frameDelay;
    public int delay;
    public int[] labelGroups;
    public boolean renderPadding;
    public int priority;
    public int shieldOverride;
    public int weaponOverride;
    public int replays;
    public SeqType() {
        delay = -1;
        renderPadding = false;
        priority = 5;
        shieldOverride = -1;
        weaponOverride = -1;
        replays = 99;
    }

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("seq.dat", null));
        count = buffer.g2();
        instances = new SeqType[count];

        for (int n = 0; n < count; n++) {
            if (instances[n] == null) {
                instances[n] = new SeqType();
            }

            instances[n].read(buffer);
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.g1();
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                frameCount = buffer.g1();
                primaryFrames = new int[frameCount];
                secondaryFrames = new int[frameCount];
                frameDelay = new int[frameCount];

                for (int k = 0; k < frameCount; k++) {
                    primaryFrames[k] = buffer.g2();
                    secondaryFrames[k] = buffer.g2();

                    if (secondaryFrames[k] == 65535) {
                        secondaryFrames[k] = -1;
                    }

                    frameDelay[k] = buffer.g2();

                    if (frameDelay[k] == 0) {
                        frameDelay[k] = SeqFrame.instances[primaryFrames[k]].delay;
                    }

                    if (frameDelay[k] == 0) {
                        frameDelay[k] = 1;
                    }
                }
            } else if (opcode == 2) {
                delay = buffer.g2();
            } else if (opcode == 3) {
                int n = buffer.g1();
                labelGroups = new int[n + 1];
                for (int m = 0; m < n; m++) {
                    labelGroups[m] = buffer.g1();
                }
                labelGroups[n] = 9999999;
            } else if (opcode == 4) {
                if (Signlink.clientversion > 194) {
                    renderPadding = true;
                } else {
                    renderPadding = buffer.g2() > 0;
                }
            } else if (opcode == 5) {
                priority = buffer.g1();
            } else if (opcode == 6) {
                shieldOverride = buffer.g2();
            } else if (opcode == 7) {
                weaponOverride = buffer.g2();
            } else if (opcode == 8) {
                replays = buffer.g1();
            } else if (opcode >= 9 && opcode <= 11) {
                buffer.g1(); // newer revision opcode
            } else if (opcode == 12) {
                buffer.g4(); // newer revision opcode
            } else {
                System.out.println("Error unrecognised seq config code: " + opcode);
            }
        } while (true);

        if (frameCount == 0) {
            frameCount = 1;
            primaryFrames = new int[1];
            primaryFrames[0] = -1;
            secondaryFrames = new int[1];
            secondaryFrames[0] = -1;
            frameDelay = new int[1];
            frameDelay[0] = -1;
        }
    }

}
