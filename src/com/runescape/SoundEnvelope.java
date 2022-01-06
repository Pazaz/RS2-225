package com.runescape;

public class SoundEnvelope {

    public void readShape(Class38_Sub2_Sub3 buffer) {
        form = buffer.method446();
        start = buffer.method451();
        end = buffer.method451();
        length = buffer.method446();
        shapeDelta = new int[length];
        shapePeak = new int[length];
        for (int j = 0; j < length; j++) {
            shapeDelta[j] = buffer.method448();
            shapePeak[j] = buffer.method448();
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

    public SoundEnvelope() {
    }

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
}
