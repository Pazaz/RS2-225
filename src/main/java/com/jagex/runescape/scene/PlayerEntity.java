package com.jagex.runescape.scene;

import com.jagex.runescape.Game;
import com.jagex.runescape.cache.*;
import com.jagex.runescape.util.Buffer;
import com.jagex.runescape.util.Cache;
import com.jagex.runescape.util.StringUtils;

public class PlayerEntity extends PathingEntity {

    public void read(Buffer buffer) {
        buffer.offset = 0;
        gender = buffer.readByte();
        headicons = buffer.readByte();

        for (int n = 0; n < 12; n++) {
            int msb = buffer.readByte();

            if (msb == 0) {
                appearanceIndices[n] = 0;
            } else {
                appearanceIndices[n] = (msb << 8) + buffer.readByte();
            }
        }

        for (int n = 0; n < 5; n++) {
            int i = buffer.readByte();

            if (i < 0 || i >= Game.APPEARANCE_COLORS[n].length) {
                i = 0;
            }

            appearanceColors[n] = i;
        }

        super.standSeq = buffer.readWord();
        if (super.standSeq == 65535) {
            super.standSeq = -1;
        }

        super.turnSeq = buffer.readWord();
        if (super.turnSeq == 65535) {
            super.turnSeq = -1;
        }

        super.runSeq = buffer.readWord();
        if (super.runSeq == 65535) {
            super.runSeq = -1;
        }

        super.walkSeq = buffer.readWord();
        if (super.walkSeq == 65535) {
            super.walkSeq = -1;
        }

        super.turnAroundSeq = buffer.readWord();
        if (super.turnAroundSeq == 65535) {
            super.turnAroundSeq = -1;
        }

        super.turnRightSeq = buffer.readWord();
        if (super.turnRightSeq == 65535) {
            super.turnRightSeq = -1;
        }

        super.turnLeftSeq = buffer.readWord();
        if (super.turnLeftSeq == 65535) {
            super.turnLeftSeq = -1;
        }

        name = StringUtils.formatName(StringUtils.fromBase37(buffer.readQWord()));
        combatLevel = buffer.readByte();

        visible = true;
        uid = 0L;

        for (int n = 0; n < 12; n++) {
            uid <<= 4;

            if (appearanceIndices[n] >= 256) {
                uid += appearanceIndices[n] - 256;
            }
        }

        if (appearanceIndices[0] >= 256) {
            uid += appearanceIndices[0] - 256 >> 4;
        }

        if (appearanceIndices[1] >= 256) {
            uid += appearanceIndices[1] - 256 >> 8;
        }

        for (int n = 0; n < 5; n++) {
            uid <<= 3;
            uid += appearanceColors[n];
        }

        uid <<= 1;
        uid += gender;
    }

    @Override
    public Model getDrawMethod() {
        if (!visible) {
            return null;
        }

        Model model = getModel();
        super.height = model.maxBoundY;
        model.pickable = true;

        if (lowMemory) {
            return model;
        }

        if (super.spotAnimIndex != -1 && super.spotAnimFrame != -1) {
            SpotAnimType s = SpotAnimType.instances[super.spotAnimIndex];
            Model m = new Model(s.getModel(), true, !s.disposeAlpha, false);
            m.translate(-super.spotAnimOffsetY, 0, 0);
            m.applyGroups();
            m.applyFrame(s.seq.primaryFrames[super.spotAnimFrame]);
            m.skinTriangle = null;
            m.labelVertices = null;
            if (s.breadthScale != 128 || s.depthScale != 128) {
                m.scale(s.breadthScale, s.depthScale, s.breadthScale);
            }
            m.applyLighting(64 + s.ambience, 850 + s.modelShadow, -30, -50, -30, true);
            Model[] models = {model, m};
            model = new Model(models, (byte) -31, 2);
        }

        if (locModel != null) {
            if (Game.clientClock >= locLastCycle) {
                locModel = null;
            }

            if (Game.clientClock >= locFirstCycle && Game.clientClock < locLastCycle) {
                Model m = locModel;
                m.translate(locSceneY - y, locSceneX - super.x, locSceneZ - super.z);

                if (super.dstYaw == 512) {
                    m.rotateCounterClockwise();
                    m.rotateCounterClockwise();
                    m.rotateCounterClockwise();
                } else if (super.dstYaw == 1024) {
                    m.rotateCounterClockwise();
                    m.rotateCounterClockwise();
                } else if (super.dstYaw == 1536) {
                    m.rotateCounterClockwise();
                }

                Model[] models = {model, m};
                model = new Model(models, (byte) -31, 2);

                if (super.dstYaw == 512)
                    m.rotateCounterClockwise();
                else if (super.dstYaw == 1024) {
                    m.rotateCounterClockwise();
                    m.rotateCounterClockwise();
                } else if (super.dstYaw == 1536) {
                    m.rotateCounterClockwise();
                    m.rotateCounterClockwise();
                    m.rotateCounterClockwise();
                }

                m.translate(y - locSceneY, super.x - locSceneX, super.z - locSceneZ);
            }
        }

        model.pickable = true;
        return model;
    }

    public Model getModel() {
        long bitset = uid;
        int primaryFrame = -1;
        int secondaryFrame = -1;
        int shieldOverride = -1;
        int weaponOverride = -1;

        if (super.primarySeq >= 0 && super.primarySeqDelay == 0) {
            SeqType s = SeqType.animations[super.primarySeq];
            primaryFrame = s.primaryFrames[super.primarySeqFrame];

            if (super.secondarySeq >= 0 && super.secondarySeq != super.standSeq) {
                secondaryFrame = SeqType.animations[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
            }

            if (s.shieldOverride >= 0) {
                shieldOverride = s.shieldOverride;
                bitset += shieldOverride - appearanceIndices[5] << 40;
            }

            if (s.weaponOverride >= 0) {
                weaponOverride = s.weaponOverride;
                bitset += weaponOverride - appearanceIndices[3] << 48;
            }
        } else if (super.secondarySeq >= 0) {
            primaryFrame = SeqType.animations[super.secondarySeq].primaryFrames[super.secondarySeqFrame];
        }

        Model m = (Model) models.get(bitset);
        if (m == null) {
            Model[] models = new Model[12];
            int n = 0;

            for (int i = 0; i < 12; i++) {
                int index = appearanceIndices[i];

                if (weaponOverride >= 0 && i == 3) {
                    index = weaponOverride;
                }

                if (shieldOverride >= 0 && i == 5) {
                    index = shieldOverride;
                }

                if (index >= 256 && index < 512) {
                    models[n++] = IdkType.instances[index - 256].getModel();
                }

                if (index >= 512) {
                    ObjType o = ObjType.get(index - 512);
                    Model model = o.getWornModel(gender);
                    if (model != null) {
                        models[n++] = model;
                    }
                }
            }

            m = new Model(models, n);
            for (int part = 0; part < 5; part++) {
                if (appearanceColors[part] != 0) {
                    m.recolor(Game.APPEARANCE_COLORS[part][0], Game.APPEARANCE_COLORS[part][appearanceColors[part]]);

                    if (part == 1) {
                        m.recolor(Game.BEARD_COLORS[0], Game.BEARD_COLORS[appearanceColors[part]]);
                    }
                }
            }

            m.applyGroups();
            m.applyLighting(64, 850, -30, -50, -30, true);
            PlayerEntity.models.put(bitset, m);
        }

        if (lowMemory) {
            return m;
        }

        m = new Model(m, true);

        if (primaryFrame != -1 && secondaryFrame != -1) {
            m.applyFrames(secondaryFrame, primaryFrame, SeqType.animations[super.primarySeq].labelGroups);
        } else if (primaryFrame != -1) {
            m.applyFrame(primaryFrame);
        }

        m.calculateYBoundaries();
        m.skinTriangle = null;
        m.labelVertices = null;
        return m;
    }

    public Model getHeadModel() {
        if (!visible) {
            return null;
        }

        Model[] models = new Model[12];
        int count = 0;
        for (int n = 0; n < 12; n++) {
            int i = appearanceIndices[n];
            if (i >= 256 && i < 512) {
                models[count++] = IdkType.instances[i - 256].getHeadModel();
            }

            if (i >= 512) {
                Model m = ObjType.get(i - 512).getHeadModel(gender);
                if (m != null) {
                    models[count++] = m;
                }
            }
        }

        Model m = new Model(models, count);
        for (int n = 0; n < 5; n++) {
            if (appearanceColors[n] != 0) {
                m.recolor(Game.APPEARANCE_COLORS[n][0], Game.APPEARANCE_COLORS[n][appearanceColors[n]]);
                if (n == 1) {
                    m.recolor(Game.BEARD_COLORS[0], Game.BEARD_COLORS[appearanceColors[n]]);
                }
            }
        }

        return m;
    }

    public boolean isValid(boolean flag) {
        return visible;
    }

    public PlayerEntity() {
        visible = false;
        appearanceIndices = new int[12];
        appearanceColors = new int[5];
        lowMemory = false;
    }

    public String name;
    public boolean visible;
    public int gender;
    public int headicons;
    public int[] appearanceIndices;
    public int[] appearanceColors;
    public int combatLevel;
    public long uid;
    public int y;
    public int locFirstCycle;
    public int locLastCycle;
    public int locSceneX;
    public int locSceneY;
    public int locSceneZ;
    public Model locModel;
    public int locMinTileX;
    public int locMinTileZ;
    public int locMaxTileX;
    public int locMaxTileZ;
    public boolean lowMemory;
    public static Cache models = new Cache(200);

}
