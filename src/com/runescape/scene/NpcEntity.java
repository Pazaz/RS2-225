package com.runescape.scene;

import com.runescape.cache.SpotAnimType;
import com.runescape.cache.Model;
import com.runescape.cache.NpcType;
import com.runescape.cache.SeqType;

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
        
        Model spotAnimModel = new Model(spotAnim.getModel(), true, !spotAnim.disposeAlpha, anInt1500, false);
        spotAnimModel.translate(-super.spotanimOffsetY, 0, -122, 0);

        spotAnimModel.applyGroups(4);
        spotAnimModel.applyFrame(-16599, spotAnim.seq.primaryFrames[super.spotAnimFrame]);

        spotAnimModel.skinTriangle = null;
        spotAnimModel.labelVertices = null;

        if (spotAnim.breadthScale != 128 || spotAnim.depthScale != 128) {
            spotAnimModel.scale(spotAnim.breadthScale, 2, spotAnim.depthScale, spotAnim.breadthScale);
        }

        spotAnimModel.applyLighting(64 + spotAnim.ambience, 850 + spotAnim.modelShadow, -30, -50, -30, true);

        Model[] models = { model, spotAnimModel };
        Model animated = new Model(models, (byte) -31, 2, true);

        if (info.size == 1) {
            animated.pickable = true;
        }

        return animated;
    }

    public Model getModel() {
        if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
            int frame1 = SeqType.seqTypes[super.primarySeq].primaryFrames[super.primarySeqFrame];
            int frame2 = -1;
            
            if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq) {
                frame2 = SeqType.seqTypes[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
            }
            
            return info.getModel(frame1, frame2, SeqType.seqTypes[super.primarySeq].labelGroups);
        }

        int frame = -1;

        if (super.secondarySeq >= 0) {
            frame = SeqType.seqTypes[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
        }

        Model model = info.getModel(frame, -1, null);
        super.height = model.minY;
        return model;
    }

    public boolean isValid(boolean flag) {
        return info != null;
    }

    public NpcEntity() {
    }

    public int anInt1500;
    public NpcType info;
}
