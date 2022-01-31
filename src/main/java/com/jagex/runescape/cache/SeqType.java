package com.jagex.runescape.cache;

import com.jagex.runescape.util.Buffer;

public class SeqType {

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("seq.dat", null));
        count = buffer.readWord();

        if (animations == null) {
            animations = new SeqType[count];
        }

        for (int n = 0; n < count; n++) {
            if (animations[n] == null) {
                animations[n] = new SeqType();
            }

            animations[n].read(buffer);
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.readByte();

            if (opcode == 0) {
                break;
            } else if (opcode == 1) {
                frameCount = buffer.readByte();
                primaryFrames = new int[frameCount];
                secondaryFrames = new int[frameCount];
                frameDelay = new int[frameCount];

                for (int k = 0; k < frameCount; k++) {
                    primaryFrames[k] = buffer.readWord();
                    secondaryFrames[k] = buffer.readWord();

                    if (secondaryFrames[k] == 65535) {
                        secondaryFrames[k] = -1;
                    }

                    frameDelay[k] = buffer.readWord();

                    if (frameDelay[k] == 0) {
                        frameDelay[k] = SeqFrame.instance[primaryFrames[k]].delay;
                    }

                    if (frameDelay[k] == 0) {
                        frameDelay[k] = 1;
                    }
                }
            } else if (opcode == 2) {
                delta = buffer.readWord();
            } else if (opcode == 3) {
                int n = buffer.readByte();
                labelGroups = new int[n + 1];
                for (int m = 0; m < n; m++) {
                    labelGroups[m] = buffer.readByte();
                }
                labelGroups[n] = 9999999;
            } else if (opcode == 4) {
                renderPadding = true;
            } else if (opcode == 5) {
                priority = buffer.readByte();
            } else if (opcode == 6) {
                shieldOverride = buffer.readWord();
            } else if (opcode == 7) {
                weaponOverride = buffer.readWord();
            } else if (opcode == 8) {
                replays = buffer.readByte();
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

    public SeqType() {
        delta = -1;
        renderPadding = false;
        priority = 5;
        shieldOverride = -1;
        weaponOverride = -1;
        replays = 99;
    }

    public static int count;
    public static SeqType[] animations;
    public int frameCount;
    public int[] primaryFrames;
    public int[] secondaryFrames;
    public int[] frameDelay;
    public int delta;
    public int[] labelGroups;
    public boolean renderPadding;
    public int priority;
    public int shieldOverride;
    public int weaponOverride;
    public int replays;

}
