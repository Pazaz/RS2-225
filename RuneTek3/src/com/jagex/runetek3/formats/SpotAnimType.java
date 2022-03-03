package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.graphics.Model;
import com.jagex.runetek3.graphics.SeqType;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Cache;

public class SpotAnimType {

    public static int count;
    public static SpotAnimType[] instances;
    public static Cache models = new Cache(30);
    public int index;
    public int modelIndex;
    public int seqIndex;
    public SeqType seq;
    public boolean disposeAlpha;
    public int[] oldColors;
    public int[] newColors;
    public int breadthScale;
    public int depthScale;
    public int orientation;
    public int ambience;
    public int modelShadow;
    public SpotAnimType() {
        seqIndex = -1;
        disposeAlpha = false;
        oldColors = new int[6];
        newColors = new int[6];
        breadthScale = 128;
        depthScale = 128;
    }

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("spotanim.dat", null));
        count = buffer.g2();
        instances = new SpotAnimType[count];

        for (int n = 0; n < count; n++) {
            if (instances[n] == null) {
                instances[n] = new SpotAnimType();
            }

            instances[n].index = n;
            instances[n].read(buffer);
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.g1();

            if (opcode == 0) {
                return;
            } else if (opcode == 1) {
                modelIndex = buffer.g2();
            } else if (opcode == 2) {
                seqIndex = buffer.g2();
                if (SeqType.instances != null) {
                    seq = SeqType.instances[seqIndex];
                }
            } else if (opcode == 3) {
                disposeAlpha = true;
            } else if (opcode == 4) {
                breadthScale = buffer.g2();
            } else if (opcode == 5) {
                depthScale = buffer.g2();
            } else if (opcode == 6) {
                orientation = buffer.g2();
            } else if (opcode == 7) {
                ambience = buffer.g1();
            } else if (opcode == 8) {
                modelShadow = buffer.g1();
            } else if (opcode >= 40 && opcode < 50) {
                oldColors[opcode - 40] = buffer.g2();
            } else if (opcode >= 50 && opcode < 60) {
                newColors[opcode - 50] = buffer.g2();
            } else {
                System.out.println("Error unrecognised spotanim config code: " + opcode);
            }
        } while (true);
    }

    public Model getModel() {
        Model model = (Model) models.get(index);

        if (model != null) {
            return model;
        }

        model = new Model(modelIndex);

        for (int n = 0; n < 6; n++) {
            if (oldColors[0] != 0) {
                model.recolor(oldColors[n], newColors[n]);
            }
        }

        models.put(index, model);
        return model;
    }

}
