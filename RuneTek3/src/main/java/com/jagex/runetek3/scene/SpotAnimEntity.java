package com.jagex.runetek3.scene;

import com.jagex.runetek3.formats.SpotAnimType;
import com.jagex.runetek3.graphics.Model;

public class SpotAnimEntity extends Entity {

    public SpotAnimType spotanim;
    public int firstCycle;
    public int level;
    public int x;
    public int z;
    public int y;
    public int seqFrame;
    public int frameCycle;
    public boolean finished;
    public SpotAnimEntity(int x, int y, int z, int spotanimIndex, int duration, int level, int startCycle) {
        spotanim = SpotAnimType.instances[spotanimIndex];
        this.level = level;
        this.x = x;
        this.z = z;
        this.y = y;
        firstCycle = startCycle + duration;
        finished = false;
    }

    public void update(int cycle) {
        for (frameCycle += cycle; frameCycle > spotanim.seq.frameDelay[seqFrame]; ) {
            frameCycle -= spotanim.seq.frameDelay[seqFrame] + 1;
            seqFrame++;

            if (seqFrame >= spotanim.seq.frameCount && (seqFrame < 0 || seqFrame >= spotanim.seq.frameCount)) {
                seqFrame = 0;
                finished = true;
            }
        }
    }

    @Override
    public Model getDrawMethod() {
        Model m = new Model(spotanim.getModel(), true, !spotanim.disposeAlpha, false);

        if (!finished) {
            m.applyGroups();
            m.applyFrame(spotanim.seq.primaryFrames[seqFrame]);
            m.skinTriangle = null;
            m.labelVertices = null;
        }

        if (spotanim.breadthScale != 128 || spotanim.depthScale != 128) {
            m.scale(spotanim.breadthScale, spotanim.depthScale, spotanim.breadthScale);
        }

        if (spotanim.orientation != 0) {
            if (spotanim.orientation == 90) {
                m.rotateCounterClockwise();
            }

            if (spotanim.orientation == 180) {
                m.rotateCounterClockwise();
                m.rotateCounterClockwise();
            }

            if (spotanim.orientation == 270) {
                m.rotateCounterClockwise();
                m.rotateCounterClockwise();
                m.rotateCounterClockwise();
            }
        }
        m.applyLighting(64 + spotanim.ambience, 850 + spotanim.modelShadow, -30, -50, -30, true);
        return m;
    }
}
