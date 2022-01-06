package com.runescape.cache;

import com.runescape.util.Buffer;
import com.runescape.util.Cache;

public class SpotAnimType {

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("spotanim.dat", null));
        count = buffer.readWord();

        if (instances == null) {
            instances = new SpotAnimType[count];
        }

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
            int opcode = buffer.readByte();

            if (opcode == 0) {
                return;
            } else if (opcode == 1) {
                modelIndex = buffer.readWord();
            } else if (opcode == 2) {
                seqIndex = buffer.readWord();
                if (SeqType.animations != null) {
                    seq = SeqType.animations[seqIndex];
                }
            } else if (opcode == 3) {
                disposeAlpha = true;
            } else if (opcode == 4) {
                breadthScale = buffer.readWord();
            } else if (opcode == 5) {
                depthScale = buffer.readWord();
            } else if (opcode == 6) {
                orientation = buffer.readWord();
            } else if (opcode == 7) {
                ambience = buffer.readByte();
            } else if (opcode == 8) {
                modelShadow = buffer.readByte();
            } else if (opcode >= 40 && opcode < 50) {
                oldColors[opcode - 40] = buffer.readWord();
            } else if (opcode >= 50 && opcode < 60) {
                newColors[opcode - 50] = buffer.readWord();
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

        model = new Model(false, modelIndex);

        for (int n = 0; n < 6; n++) {
            if (oldColors[0] != 0) {
                model.recolor(oldColors[n], newColors[n]);
            }
        }

        models.put(index, model);
        return model;
    }

    public SpotAnimType() {
        seqIndex = -1;
        disposeAlpha = false;
        oldColors = new int[6];
        newColors = new int[6];
        breadthScale = 128;
        depthScale = 128;
    }

    public static int count;
    public static SpotAnimType[] instances;
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
    public static Cache models = new Cache(30);

}
