package com.runescape.sound;

import com.runescape.util.Buffer;

public class SoundEnvelope {

    public void readShape(Buffer buffer) {
        form = buffer.readByte();
        start = buffer.readDWord();
        end = buffer.readDWord();
        length = buffer.readByte();
        shapeDelta = new int[length];
        shapePeak = new int[length];
        for (int j = 0; j < length; j++) {
            shapeDelta[j] = buffer.readWord();
            shapePeak[j] = buffer.readWord();
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
