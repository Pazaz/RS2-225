package com.jagex.runescape.util;

public class InputTracking {

    public static synchronized void setEnabled() {
        outBuffer = Buffer.reserve(1);
        oldBuffer = null;
        lastTime = System.currentTimeMillis();
        enabled = true;
    }

    public static synchronized void setDisabled() {
        enabled = false;
        outBuffer = null;
        oldBuffer = null;
    }

    public static synchronized Buffer flushAndContinue() {
        Buffer b = null;
        if (oldBuffer != null && enabled) {
            b = oldBuffer;
        }
        oldBuffer = null;
        return b;
    }

    public static synchronized Buffer flushAndDisable() {
        Buffer b = null;
        if (outBuffer != null && outBuffer.offset > 0 && enabled) {
            b = outBuffer;
        }
        setDisabled();
        return b;
    }

    public static synchronized void resizeIfNeeded(int bytes) {
        if (outBuffer.offset + bytes >= 500) {
            Buffer b = outBuffer;
            outBuffer = Buffer.reserve(1);
            oldBuffer = b;
        }
    }

    public static synchronized void mousePressed(int y, int button, int x) {
        if (!enabled) {
            return;
        }

        if (y < 0 || y >= 789 || x < 0 || x >= 532) {
            return;
        }

        trackedCount++;

        long l = System.currentTimeMillis();
        long l1 = (l - lastTime) / 10L;
        if (l1 > 250L) {
            l1 = 250L;
        }

        lastTime = l;

        resizeIfNeeded(5);
        if (button == 1) {
            outBuffer.writeByte(1);
        } else {
            outBuffer.writeByte(2);
        }
        outBuffer.writeByte((int) l1);
        outBuffer.writeSWord(y + (x << 10));
    }

    public static synchronized void mouseReleased(int button) {
        if (!enabled) {
            return;
        }

        trackedCount++;

        long currentTime = System.currentTimeMillis();
        long deltaTime = (currentTime - lastTime) / 10L;
        if (deltaTime > 250L) {
            deltaTime = 250L;
        }
        lastTime = currentTime;

        resizeIfNeeded(2);
        if (button == 1) {
            outBuffer.writeByte(3);
        } else {
            outBuffer.writeByte(4);
        }
        outBuffer.writeByte((int) deltaTime);
    }

    public static synchronized void mouseMoved(int x, int y) {
        if (!enabled) {
            return;
        }

        if (y < 0 || y >= 789 || x < 0 || x >= 532) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - oldTime >= 50L) {
            oldTime = currentTime;
            trackedCount++;

            long deltaTime = (currentTime - lastTime) / 10L;
            if (deltaTime > 250L) {
                deltaTime = 250L;
            }

            lastTime = currentTime;
            if (y - lastY < 8 && y - lastY >= -8 && x - lastX < 8 && x - lastX >= -8) {
                resizeIfNeeded(3);
                outBuffer.writeByte(5);
                outBuffer.writeByte((int) deltaTime);
                outBuffer.writeByte((y - lastY) + 8 + ((x - lastX) + 8 << 4));
            } else if (y - lastY < 128 && y - lastY >= -128 && x - lastX < 128 && x - lastX >= -128) {
                resizeIfNeeded(4);
                outBuffer.writeByte(6);
                outBuffer.writeByte((int) deltaTime);
                outBuffer.writeByte((y - lastY) + 128);
                outBuffer.writeByte((x - lastX) + 128);
            } else {
                resizeIfNeeded(5);
                outBuffer.writeByte(7);
                outBuffer.writeByte((int) deltaTime);
                outBuffer.writeSWord(y + (x << 10));
            }

            lastY = y;
            lastX = x;
        }
    }

    public static synchronized void keyPressed(int key) {
        if (!enabled) {
            return;
        }

        trackedCount++;

        long currentTime = System.currentTimeMillis();
        long deltaTime = (currentTime - lastTime) / 10L;
        if (deltaTime > 250L) {
            deltaTime = 250L;
        }

        lastTime = currentTime;
        if (key == 1000) {
            key = 11;
        } else if (key == 1001) {
            key = 12;
        } else if (key == 1002) {
            key = 14;
        } else if (key == 1003) {
            key = 15;
        } else if (key >= 1008) {
            key -= 992;
        }

        resizeIfNeeded(3);
        outBuffer.writeByte(8);
        outBuffer.writeByte((int) deltaTime);
        outBuffer.writeByte(key);
    }

    public static synchronized void keyReleased(int key) {
        if (!enabled) {
            return;
        }

        trackedCount++;

        long currentTime = System.currentTimeMillis();
        long deltaTime = (currentTime - lastTime) / 10L;
        if (deltaTime > 250L) {
            deltaTime = 250L;
        }

        lastTime = currentTime;
        if (key == 1000) {
            key = 11;
        } else if (key == 1001) {
            key = 12;
        } else if (key == 1002) {
            key = 14;
        } else if (key == 1003) {
            key = 15;
        } else if (key >= 1008) {
            key -= 992;
        }

        resizeIfNeeded(3);
        outBuffer.writeByte(9);
        outBuffer.writeByte((int) deltaTime);
        outBuffer.writeByte(key);
    }

    public static synchronized void focusGained() {
        if (!enabled) {
            return;
        }

        trackedCount++;

        long currentTime = System.currentTimeMillis();
        long deltaTime = (currentTime - lastTime) / 10L;
        if (deltaTime > 250L) {
            deltaTime = 250L;
        }

        lastTime = currentTime;
        resizeIfNeeded(2);
        outBuffer.writeByte(10);
        outBuffer.writeByte((int) deltaTime);
    }

    public static synchronized void focusLost() {
        if (!enabled) {
            return;
        }

        trackedCount++;

        long currentTime = System.currentTimeMillis();
        long deltaTime = (currentTime - lastTime) / 10L;
        if (deltaTime > 250L) {
            deltaTime = 250L;
        }

        lastTime = currentTime;
        resizeIfNeeded(2);
        outBuffer.writeByte(11);
        outBuffer.writeByte((int) deltaTime);
    }

    public static synchronized void mouseEntered() {
        if (!enabled) {
            return;
        }

        trackedCount++;

        long currentTime = System.currentTimeMillis();
        long deltaTime = (currentTime - lastTime) / 10L;
        if (deltaTime > 250L) {
            deltaTime = 250L;
        }

        lastTime = currentTime;
        resizeIfNeeded(2);
        outBuffer.writeByte(12);
        outBuffer.writeByte((int) deltaTime);
    }

    public static synchronized void mouseExited() {
        if (!enabled) {
            return;
        }

        trackedCount++;

        long currentTime = System.currentTimeMillis();
        long deltaTime = (currentTime - lastTime) / 10L;
        if (deltaTime > 250L) {
            deltaTime = 250L;
        }

        lastTime = currentTime;
        resizeIfNeeded(2);
        outBuffer.writeByte(13);
        outBuffer.writeByte((int) deltaTime);
    }

    public static boolean enabled;
    public static Buffer outBuffer = null;
    public static Buffer oldBuffer = null;
    public static long lastTime;
    public static int trackedCount;
    public static long oldTime;
    public static int lastY;
    public static int lastX;

}
