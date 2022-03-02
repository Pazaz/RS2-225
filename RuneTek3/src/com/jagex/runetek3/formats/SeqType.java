package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Signlink;

public class SeqType {

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("seq.dat", null));
        count = buffer.readWord();
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
            int opcode = buffer.readByte();
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
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
                        frameDelay[k] = SeqFrame.instances[primaryFrames[k]].delay;
                    }

                    if (frameDelay[k] == 0) {
                        frameDelay[k] = 1;
                    }
                }
            } else if (opcode == 2) {
                delay = buffer.readWord();
            } else if (opcode == 3) {
                int n = buffer.readByte();
                labelGroups = new int[n + 1];
                for (int m = 0; m < n; m++) {
                    labelGroups[m] = buffer.readByte();
                }
                labelGroups[n] = 9999999;
            } else if (opcode == 4) {
                if (Signlink.clientversion > 194) {
                    renderPadding = true;
                } else {
                    renderPadding = buffer.readWord() > 0;
                }
            } else if (opcode == 5) {
                priority = buffer.readByte();
            } else if (opcode == 6) {
                shieldOverride = buffer.readWord();
            } else if (opcode == 7) {
                weaponOverride = buffer.readWord();
            } else if (opcode == 8) {
                replays = buffer.readByte();
            } else if (opcode >= 9 && opcode <= 11) {
                buffer.readByte(); // newer revision opcode
            } else if (opcode == 12) {
                buffer.readDWord(); // newer revision opcode
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
        delay = -1;
        renderPadding = false;
        priority = 5;
        shieldOverride = -1;
        weaponOverride = -1;
        replays = 99;
    }

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

}