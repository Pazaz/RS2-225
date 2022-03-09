package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;

public class VarType {

    public static int count;
    public static VarType[] instances;
    public int type;

    public VarType() {
    }

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("varp.dat", null));
        count = buffer.g2();
        instances = new VarType[count];

        for (int n = 0; n < count; n++) {
            if (instances[n] == null) {
                instances[n] = new VarType();
            }

            instances[n].read(buffer);
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.g1();

            if (opcode == 0) {
                return;
            } else if (opcode == 1 || opcode == 2) {
                buffer.g1();
            } else if (opcode == 5) {
                type = buffer.g2();
            } else if (opcode == 7) {
                buffer.g4();
            } else if (opcode == 10) {
                buffer.gString();
            } else if (opcode == 3 || opcode == 4 || opcode == 6 || opcode == 8) {
            } else {
                System.out.println("Error unrecognised config code: " + opcode);
            }
        } while (true);
    }

}
