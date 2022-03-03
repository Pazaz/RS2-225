package com.jagex.runetek3.scene;

import com.jagex.runetek3.graphics.Model;
import com.jagex.runetek3.formats.SpotAnimType;

public class ProjectileEntity extends Entity {

    public ProjectileEntity(int baseZ, int elevationPitch, int sourceY, int lastCycle, int level, int targetIndex, int firstCycle,
                            int arcScale, int sourceZ, int spotAnim, int sourceX) {
        this.spotAnim = SpotAnimType.instances[spotAnim];
        this.level = level;
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.sourceZ = sourceZ;
        this.firstCycle = firstCycle;
        this.lastCycle = lastCycle;
        this.elevationPitch = elevationPitch;
        this.arcScale = arcScale;
        this.targetIndex = targetIndex;
        this.baseZ = baseZ;
        isMobile = false;
    }

    public void setTarget(int destZ, int destY, int destX, int curTick) {
        if (!isMobile) {
            double dx = destX - sourceX;
            double dy = destY - sourceY;
            double d = Math.sqrt(dx * dx + dy * dy);
            x = (double) sourceX + (dx * (double) arcScale) / d;
            y = (double) sourceY + (dy * (double) arcScale) / d;
            z = sourceZ;
        }

        double dt = (lastCycle + 1) - curTick;
        velocityX = ((double) destX - x) / dt;
        velocityY = ((double) destY - y) / dt;
        velocity = Math.sqrt(velocityX * velocityX + velocityY * velocityY);

        if (!isMobile) {
            velocityZ = -velocity * Math.tan((double) elevationPitch * 0.02454369D);
        }

        accelerationZ = (2D * ((double) destZ - z - velocityZ * dt)) / (dt * dt);
    }

    public void update(int cycle) {
        isMobile = true;
        x += velocityX * (double) cycle;
        y += velocityY * (double) cycle;
        z += velocityZ * (double) cycle + 0.5D * accelerationZ * (double) cycle * (double) cycle;
        velocityZ += accelerationZ * (double) cycle;
        yaw = (int) (Math.atan2(velocityX, velocityY) * 325.94900000000001D) + 1024 & 0x7ff;
        pitch = (int) (Math.atan2(velocityZ, velocity) * 325.94900000000001D) & 0x7ff;
        if (spotAnim.seq != null) {
            for (frameCycle += cycle; frameCycle > spotAnim.seq.frameDelay[seqFrame]; ) {
                frameCycle -= spotAnim.seq.frameDelay[seqFrame] + 1;
                seqFrame++;
                if (seqFrame >= spotAnim.seq.frameCount) {
                    seqFrame = 0;
                }
            }
        }
    }

    @Override
    public Model getDrawMethod() {
        Model sam = spotAnim.getModel();
        Model m = new Model(sam, true, !spotAnim.disposeAlpha, false);

        if (spotAnim.seq != null) {
            m.applyGroups();
            m.applyFrame(spotAnim.seq.primaryFrames[seqFrame]);
            m.skinTriangle = null;
            m.labelVertices = null;
        }

        if (spotAnim.breadthScale != 128 || spotAnim.depthScale != 128) {
            m.scale(spotAnim.breadthScale, spotAnim.depthScale, spotAnim.breadthScale);
        }

        m.rotatePitch(pitch);
        m.applyLighting(64 + spotAnim.ambience, 850 + spotAnim.modelShadow, -30, -50, -30, true);
        return m;
    }

    public SpotAnimType spotAnim;
    public int level;
    public int sourceX;
    public int sourceY;
    public int sourceZ;
    public int baseZ;
    public int firstCycle;
    public int lastCycle;
    public int elevationPitch;
    public int arcScale;
    public int targetIndex;
    public boolean isMobile;
    public double x;
    public double y;
    public double z;
    public double velocityX;
    public double velocityY;
    public double velocity;
    public double velocityZ;
    public double accelerationZ;
    public int yaw;
    public int pitch;
    public int seqFrame;
    public int frameCycle;
}
