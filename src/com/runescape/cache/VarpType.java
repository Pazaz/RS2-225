package com.runescape.cache;

import com.runescape.util.Buffer;

public class VarpType {

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(363, fileArchive.read("varp.dat", null));

        count = buffer.method448();

        if (instances == null) {
            instances = new VarpType[count];
        }

        for (int n = 0; n < count; n++) {
            if (instances[n] == null) {
                instances[n] = new VarpType();
            }

            instances[n].read(buffer);
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.method446();

            if (opcode == 0) {
                return;
            } else if (opcode == 1 || opcode == 2) {
                buffer.method446();
            } else if (opcode == 5) {
                type = buffer.method448();
            } else if (opcode == 7) {
                buffer.method451();
            } else if (opcode == 10) {
                buffer.method453();
            } else if (opcode == 3 || opcode == 4 || opcode == 6 || opcode == 8) {
            } else {
                System.out.println("Error unrecognised config code: " + opcode);
            }
        } while (true);
    }

    public VarpType() {
    }

    public static int count;
    public static VarpType[] instances;
    public int type;

}
