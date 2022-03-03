package com.jagex.runetek3.sound;

import com.jagex.runetek3.util.Buffer;

public class SoundEnvelope {

    public int length;
    public int[] shapeDelta;
    public int[] shapePeak;
    public int start;
    public int end;
    public int form;
    public int threshold;
    public int position;
    public int delta;
    public int amplitude;
    public int ticks;
    public SoundEnvelope() {
    }

    public void readShape(Buffer buffer) {
        form = buffer.g1();
        start = buffer.g4();
        end = buffer.g4();
        length = buffer.g1();
        shapeDelta = new int[length];
        shapePeak = new int[length];
        for (int j = 0; j < length; j++) {
            shapeDelta[j] = buffer.g2();
            shapePeak[j] = buffer.g2();
        }
    }

    public void reset() {
        threshold = 0;
        position = 0;
        delta = 0;
        amplitude = 0;
        ticks = 0;
    }

    public int evaluate(int delta) {
        if (ticks >= threshold) {
            amplitude = shapePeak[position++] << 15;
            if (position >= length)
                position = length - 1;
            threshold = (int) (((double) shapeDelta[position] / 65536D) * (double) delta);
            if (threshold > ticks)
                this.delta = ((shapePeak[position] << 15) - amplitude) / (threshold - ticks);
        }
        amplitude += this.delta;
        ticks++;
        return amplitude - this.delta >> 15;
    }
}
