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
        Buffer buffer = new Buffer(fileArchive.read("varp.dat"));
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
            } else if (opcode == 5) {
                type = buffer.g2();
            } else {
                System.out.println("Error unrecognised config code: " + opcode);
            }
        } while (true);
    }

}
