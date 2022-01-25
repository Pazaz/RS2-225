package com.runescape.scene;

import com.runescape.cache.Model;
import com.runescape.cache.NpcType;
import com.runescape.cache.SeqType;
import com.runescape.cache.SpotAnimType;

public class NpcEntity extends PathingEntity {

    @Override
    public Model getDrawMethod() {
        if (info == null) {
            return null;
        }

        if (super.spotAnimIndex == -1 || super.spotAnimFrame == -1) {
            return getModel();
        }

        Model model = getModel();
        SpotAnimType spotAnim = SpotAnimType.instances[super.spotAnimIndex];

        Model spotAnimModel = new Model(spotAnim.getModel(), true, !spotAnim.disposeAlpha, false);
        spotAnimModel.translate(-super.spotAnimOffsetY, 0, 0);

        spotAnimModel.applyGroups();
        spotAnimModel.applyFrame(spotAnim.seq.primaryFrames[super.spotAnimFrame]);

        spotAnimModel.skinTriangle = null;
        spotAnimModel.labelVertices = null;

        if (spotAnim.breadthScale != 128 || spotAnim.depthScale != 128) {
            spotAnimModel.scale(spotAnim.breadthScale, spotAnim.depthScale, spotAnim.breadthScale);
        }

        spotAnimModel.applyLighting(64 + spotAnim.ambience, 850 + spotAnim.modelShadow, -30, -50, -30, true);

        Model[] models = {model, spotAnimModel};
        Model animated = new Model(models, (byte) -31, 2);

        if (info.size == 1) {
            animated.pickable = true;
        }

        return animated;
    }

    public Model getModel() {
        if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
            int frame1 = SeqType.animations[super.primarySeq].primaryFrames[super.primarySeqFrame];
            int frame2 = -1;

            if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq) {
                frame2 = SeqType.animations[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
            }

            return info.getModel(frame1, frame2, SeqType.animations[super.primarySeq].labelGroups);
        }

        int frame = -1;

        if (super.secondarySeq >= 0) {
            frame = SeqType.animations[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
        }

        Model model = info.getModel(frame, -1, null);
        super.height = model.maxBoundY;
        return model;
    }

    public boolean isValid(boolean flag) {
        return info != null;
    }

    public NpcEntity() {
    }

    public NpcType info;
}
