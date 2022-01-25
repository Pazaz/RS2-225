package com.runescape.scene;

import com.runescape.cache.SeqType;

public class PathingEntity extends Entity {

    public void move(boolean teleport, int x, int z) {
        if (primarySeq != -1 && SeqType.animations[primarySeq].priority <= 1) {
            primarySeq = -1;
        }

        if (!teleport) {
            int dx = x - pathTileX[0];
            int dz = z - pathTileZ[0];

            if (dx >= -8 && dx <= 8 && dz >= -8 && dz <= 8) {
                if (pathRemaining < 9) {
                    pathRemaining++;
                }

                for (int n = pathRemaining; n > 0; n--) {
                    pathTileX[n] = pathTileX[n - 1];
                    pathTileZ[n] = pathTileZ[n - 1];
                    pathRunning[n] = pathRunning[n - 1];
                }

                pathTileX[0] = x;
                pathTileZ[0] = z;
                pathRunning[0] = false;
                return;
            }
        }

        pathRemaining = 0;
        anInt1431 = 0;
        pathTileX[0] = x;
        pathTileZ[0] = z;
        this.x = pathTileX[0] * 128 + index * 64;
        this.z = pathTileZ[0] * 128 + index * 64;
    }

    public void walk(boolean run, int direction) {
        int x = pathTileX[0];
        int z = pathTileZ[0];

        if (direction == 0) {
            x--;
            z++;
        } else if (direction == 1) {
            z++;
        } else if (direction == 2) {
            x++;
            z++;
        } else if (direction == 3) {
            x--;
        } else if (direction == 4) {
            x++;
        } else if (direction == 5) {
            x--;
            z--;
        } else if (direction == 6) {
            z--;
        } else if (direction == 7) {
            x++;
            z--;
        }

        if (primarySeq != -1 && SeqType.animations[primarySeq].priority <= 1) {
            primarySeq = -1;
        }
        if (pathRemaining < 9) {
            pathRemaining++;
        }

        for (int n = pathRemaining; n > 0; n--) {
            pathTileX[n] = pathTileX[n - 1];
            pathTileZ[n] = pathTileZ[n - 1];
            pathRunning[n] = pathRunning[n - 1];
        }

        pathTileX[0] = x;
        pathTileZ[0] = z;
        pathRunning[0] = run;
    }

    public boolean isValid(boolean flag) {
        return false;
    }

    public PathingEntity() {
        animationStretches = false;
        index = 1;
        standSeq = -1;
        turnSeq = -1;
        runSeq = -1;
        walkSeq = -1;
        turnAroundSeq = -1;
        turnRightSeq = -1;
        turnLeftSeq = -1;
        textCycle = 100;
        lastCombatCycle = -1000;
        targetEntity = -1;
        secondarySeq = -1;
        primarySeq = -1;
        spotAnimIndex = -1;
        pathTileX = new int[10];
        pathTileZ = new int[10];
        pathRunning = new boolean[10];
    }

    public int x;
    public int z;
    public int animationDelay;
    public boolean animationStretches;
    public int index;
    public int standSeq;
    public int turnSeq;
    public int runSeq;
    public int walkSeq;
    public int turnAroundSeq;
    public int turnRightSeq;
    public int turnLeftSeq;
    public String spoken;
    public int textCycle;
    public int spokenColor;
    public int spokenEffect;
    public int damageTaken;
    public int damageType;
    public int lastCombatCycle;
    public int currentHealth;
    public int maxHealth;
    public int targetEntity;
    public int focusX;
    public int focusY;
    public int secondarySeq;
    public int secondarySeqFrame;
    public int anInt1406;
    public int primarySeq;
    public int primarySeqFrame;
    public int primarySeqCycle;
    public int primarySeqDelay;
    public int primarySeqPlays;
    public int spotAnimIndex;
    public int spotAnimFrame;
    public int spotAnimCycle;
    public int lastSpotAnimCycle;
    public int spotAnimOffsetY;
    public int srcTileX;
    public int dstTileX;
    public int srcTileY;
    public int dstTileY;
    public int firstMoveCycle;
    public int lastMoveCycle;
    public int faceDirection;
    public int remove;
    public int height;
    public int dstYaw;
    public int pathRemaining;
    public int[] pathTileX;
    public int[] pathTileZ;
    public boolean[] pathRunning;
    public int anInt1431;
}
