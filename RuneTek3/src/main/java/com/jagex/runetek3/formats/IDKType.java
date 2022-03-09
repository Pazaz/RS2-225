package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.graphics.Model;
import com.jagex.runetek3.util.Buffer;

public class IDKType {

    public static int count;
    public static IDKType[] instances;
    public int type;
    public int[] modelIndices;
    public int[] oldColors;
    public int[] newColors;
    public int[] headModelIndices = {
        -1, -1, -1, -1, -1
    };
    public boolean validStyle;
    public IDKType() {
        type = -1;
        oldColors = new int[6];
        newColors = new int[6];
    }

    public static void load(FileArchive fileArchive) {
        Buffer buffer = new Buffer(fileArchive.read("idk.dat", null));
        count = buffer.g2();
        instances = new IDKType[count];

        for (int j = 0; j < count; j++) {
            if (instances[j] == null) {
                instances[j] = new IDKType();
            }

            instances[j].read(buffer);
        }
    }

    public void read(Buffer buffer) {
        do {
            int opcode = buffer.g1();

            if (opcode == 0) {
                return;
            } else if (opcode == 1) {
                type = buffer.g1();
            } else if (opcode == 2) {
                int n = buffer.g1();
                modelIndices = new int[n];
                for (int k = 0; k < n; k++) {
                    modelIndices[k] = buffer.g2();
                }
            } else if (opcode == 3) {
                validStyle = true;
            } else if (opcode >= 40 && opcode < 50) {
                oldColors[opcode - 40] = buffer.g2();
            } else if (opcode >= 50 && opcode < 60) {
                newColors[opcode - 50] = buffer.g2();
            } else if (opcode >= 60 && opcode < 70) {
                headModelIndices[opcode - 60] = buffer.g2();
            } else {
                System.out.println("Error unrecognised config code: " + opcode);
            }
        } while (true);
    }

    public Model getModel() {
        if (modelIndices == null) {
            return null;
        }

        Model[] models = new Model[modelIndices.length];
        for (int i = 0; i < modelIndices.length; i++) {
            models[i] = new Model(modelIndices[i]);
        }

        Model model;
        if (models.length == 1) {
            model = models[0];
        } else {
            model = new Model(models, models.length);
        }

        for (int j = 0; j < 6; j++) {
            if (oldColors[j] == 0) {
                break;
            }

            model.recolor(oldColors[j], newColors[j]);
        }

        return model;
    }

    public Model getHeadModel() {
        Model[] models = new Model[5];
        int count = 0;

        for (int n = 0; n < 5; n++) {
            if (headModelIndices[n] != -1) {
                models[count++] = new Model(headModelIndices[n]);
            }
        }

        Model model = new Model(models, count);
        for (int n = 0; n < 6; n++) {
            if (oldColors[n] == 0) {
                break;
            }

            model.recolor(oldColors[n], newColors[n]);
        }

        return model;
    }

}