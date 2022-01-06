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
        } else  if (direction == 6) {
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
        turnSpeed = -1;
        runSeq = -1;
        anInt1388 = -1;
        anInt1389 = -1;
        anInt1390 = -1;
        anInt1391 = -1;
        textCycle = 100;
        cycleStatus = -1000;
        anInt1401 = -1;
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
    public int turnSpeed;
    public int runSeq;
    public int anInt1388;
    public int anInt1389;
    public int anInt1390;
    public int anInt1391;
    public String spoken;
    public int textCycle;
    public int anInt1394;
    public int anInt1395;
    public int anInt1396;
    public int anInt1397;
    public int cycleStatus;
    public int anInt1399;
    public int anInt1400;
    public int anInt1401;
    public int anInt1402;
    public int anInt1403;
    public int secondarySeq;
    public int secondarySeqFrame;
    public int anInt1406;
    public int primarySeq;
    public int primarySeqFrame;
    public int anInt1409;
    public int primarySeqDelay;
    public int anInt1411;
    public int spotAnimIndex;
    public int spotAnimFrame;
    public int anInt1414;
    public int anInt1415;
    public int spotanimOffsetY;
    public int anInt1417;
    public int anInt1418;
    public int anInt1419;
    public int anInt1420;
    public int anInt1421;
    public int anInt1422;
    public int anInt1423;
    public int anInt1424;
    public int height;
    public int anInt1426;
    public int pathRemaining;
    public int[] pathTileX;
    public int[] pathTileZ;
    public boolean[] pathRunning;
    public int anInt1431;
}
