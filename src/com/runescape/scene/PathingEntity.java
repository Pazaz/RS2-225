package com.runescape.scene;

import com.runescape.cache.SeqType;

public class PathingEntity extends Entity {

    public void method466(boolean flag, boolean flag1, int i, int j) {
        if (primarySeq != -1 && SeqType.seqTypes[primarySeq].anInt372 <= 1)
            primarySeq = -1;
        if (!flag1) {
            int k = i - anIntArray1428[0];
            int l = j - anIntArray1429[0];
            if (k >= -8 && k <= 8 && l >= -8 && l <= 8) {
                if (anInt1427 < 9)
                    anInt1427++;
                for (int i1 = anInt1427; i1 > 0; i1--) {
                    anIntArray1428[i1] = anIntArray1428[i1 - 1];
                    anIntArray1429[i1] = anIntArray1429[i1 - 1];
                    aBooleanArray1430[i1] = aBooleanArray1430[i1 - 1];
                }

                anIntArray1428[0] = i;
                anIntArray1429[0] = j;
                aBooleanArray1430[0] = false;
                return;
            }
        }
        anInt1427 = 0;
        anInt1431 = 0;
        anIntArray1428[0] = i;
        if (flag) {
            return;
        } else {
            anIntArray1429[0] = j;
            anInt1380 = anIntArray1428[0] * 128 + anInt1384 * 64;
            anInt1381 = anIntArray1429[0] * 128 + anInt1384 * 64;
            return;
        }
    }

    public void method467(boolean flag, int i, byte byte0) {
        int j = anIntArray1428[0];
        int k = anIntArray1429[0];
        if (byte0 == 6)
            byte0 = 0;
        else
            anInt1378 = 243;
        if (i == 0) {
            j--;
            k++;
        }
        if (i == 1)
            k++;
        if (i == 2) {
            j++;
            k++;
        }
        if (i == 3)
            j--;
        if (i == 4)
            j++;
        if (i == 5) {
            j--;
            k--;
        }
        if (i == 6)
            k--;
        if (i == 7) {
            j++;
            k--;
        }
        if (primarySeq != -1 && SeqType.seqTypes[primarySeq].anInt372 <= 1)
            primarySeq = -1;
        if (anInt1427 < 9)
            anInt1427++;
        for (int l = anInt1427; l > 0; l--) {
            anIntArray1428[l] = anIntArray1428[l - 1];
            anIntArray1429[l] = anIntArray1429[l - 1];
            aBooleanArray1430[l] = aBooleanArray1430[l - 1];
        }

        anIntArray1428[0] = j;
        anIntArray1429[0] = k;
        aBooleanArray1430[0] = flag;
    }

    public boolean isValid(boolean flag) {
        if (flag)
            throw new NullPointerException();
        else
            return false;
    }

    public PathingEntity() {
        anInt1378 = 332;
        aBoolean1379 = false;
        aBoolean1383 = false;
        anInt1384 = 1;
        standSeq = -1;
        anInt1386 = -1;
        anInt1387 = -1;
        anInt1388 = -1;
        anInt1389 = -1;
        anInt1390 = -1;
        anInt1391 = -1;
        anInt1393 = 100;
        anInt1398 = -1000;
        anInt1401 = -1;
        secondarySeq = -1;
        primarySeq = -1;
        spotAnimIndex = -1;
        anIntArray1428 = new int[10];
        anIntArray1429 = new int[10];
        aBooleanArray1430 = new boolean[10];
    }

    public int anInt1378;
    public boolean aBoolean1379;
    public int anInt1380;
    public int anInt1381;
    public int anInt1382;
    public boolean aBoolean1383;
    public int anInt1384;
    public int standSeq;
    public int anInt1386;
    public int anInt1387;
    public int anInt1388;
    public int anInt1389;
    public int anInt1390;
    public int anInt1391;
    public String aString1392;
    public int anInt1393;
    public int anInt1394;
    public int anInt1395;
    public int anInt1396;
    public int anInt1397;
    public int anInt1398;
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
    public int anInt1427;
    public int[] anIntArray1428;
    public int[] anIntArray1429;
    public boolean[] aBooleanArray1430;
    public int anInt1431;
}
