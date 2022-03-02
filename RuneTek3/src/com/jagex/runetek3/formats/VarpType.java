package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

public class VarpType {

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("varp.dat", null));
        count = buffer.readWord();
        instances = new VarpType[count];

        for (int n = 0; n < count; n++) {
            if (instances[n] == null) {
                instances[n] = new VarpType();
            }

            instances[n].read(buffer);
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.readByte();

            if (opcode == 0) {
                return;
            } else if (opcode == 1 || opcode == 2) {
                buffer.readByte();
            } else if (opcode == 5) {
                type = buffer.readWord();
            } else if (opcode == 7) {
                buffer.readDWord();
            } else if (opcode == 10) {
                buffer.readString();
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
