package com.jagex.runetek3.util;

public class InputTracking {

    public static boolean enabled;
    public static Buffer outBuffer = null;
    public static Buffer oldBuffer = null;
    public static long lastTime;
    public static int trackedCount;
    public static long oldTime;
    public static int lastY;
    public static int lastX;

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

    public static synchronized void mousePressed(int x, int button, int y) {
        if (!enabled) {
            return;
        }

        if (x < 0 || x >= 789 || y < 0 || y >= 532) {
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
            outBuffer.p1(1);
        } else {
            outBuffer.p1(2);
        }
        outBuffer.p1((int) l1);
        outBuffer.p3(x + (y << 10));
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
            outBuffer.p1(3);
        } else {
            outBuffer.p1(4);
        }
        outBuffer.p1((int) deltaTime);
    }

    public static synchronized void mouseMoved(int y, int x) {
        if (!enabled) {
            return;
        }

        if (x < 0 || x >= 789 || y < 0 || y >= 532) {
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
            if (x - lastY < 8 && x - lastY >= -8 && y - lastX < 8 && y - lastX >= -8) {
                resizeIfNeeded(3);
                outBuffer.p1(5);
                outBuffer.p1((int) deltaTime);
                outBuffer.p1((x - lastY) + 8 + ((y - lastX) + 8 << 4));
            } else if (x - lastY < 128 && x - lastY >= -128 && y - lastX < 128 && y - lastX >= -128) {
                resizeIfNeeded(4);
                outBuffer.p1(6);
                outBuffer.p1((int) deltaTime);
                outBuffer.p1((x - lastY) + 128);
                outBuffer.p1((y - lastX) + 128);
            } else {
                resizeIfNeeded(5);
                outBuffer.p1(7);
                outBuffer.p1((int) deltaTime);
                outBuffer.p3(x + (y << 10));
            }

            lastY = x;
            lastX = y;
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
        outBuffer.p1(8);
        outBuffer.p1((int) deltaTime);
        outBuffer.p1(key);
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
        outBuffer.p1(9);
        outBuffer.p1((int) deltaTime);
        outBuffer.p1(key);
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
        outBuffer.p1(10);
        outBuffer.p1((int) deltaTime);
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
        outBuffer.p1(11);
        outBuffer.p1((int) deltaTime);
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
        outBuffer.p1(12);
        outBuffer.p1((int) deltaTime);
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
        outBuffer.p1(13);
        outBuffer.p1((int) deltaTime);
    }

}
