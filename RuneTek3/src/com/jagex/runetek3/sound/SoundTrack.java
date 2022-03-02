package com.jagex.runetek3.sound;

import com.jagex.runetek3.util.Buffer;

public class SoundTrack {

    public static void load(Buffer src) {
        bbuf = new byte[441000];

        buffer = new Buffer(bbuf);
        SoundTone.init();

        do {
            int id = src.readWord();
            if (id == 65535) {
                return;
            }
            tracks[id] = new SoundTrack();
            tracks[id].read(src);
            delays[id] = tracks[id].trim();
        } while (true);
    }

    public static Buffer generate(int loopCount, int id) {
        if (tracks[id] != null) {
            SoundTrack soundTrack = tracks[id];
            return soundTrack.getWaveform(loopCount);
        } else {
            return null;
        }
    }

    public void read(Buffer buffer) {
        for (int tone = 0; tone < 10; tone++) {
            if (buffer.readByte() != 0) {
                buffer.offset--;
                tones[tone] = new SoundTone();
                tones[tone].read(buffer);
            }
        }

        loopBegin = buffer.readWord();
        loopEnd = buffer.readWord();
    }

    public int trim() {
        int start = 9999999;
        for (int j = 0; j < 10; j++) {
            if (tones[j] != null && tones[j].start / 20 < start) {
                start = tones[j].start / 20;
            }
        }

        if (loopBegin < loopEnd && loopBegin / 20 < start) {
            start = loopBegin / 20;
        }

        if (start == 9999999 || start == 0) {
            return 0;
        }

        for (int tone = 0; tone < 10; tone++) {
            if (tones[tone] != null) {
                tones[tone].start -= start * 20;
            }
        }

        if (loopBegin < loopEnd) {
            loopBegin -= start * 20;
            loopEnd -= start * 20;
        }

        return start;
    }

    public Buffer getWaveform(int loopCount) {
        int j = generate(loopCount);
        buffer.offset = 0;
        buffer.writeDWord(0x52494646);
        buffer.writeDWordLE(36 + j);
        buffer.writeDWord(0x57415645);
        buffer.writeDWord(0x666d7420);
        buffer.writeDWordLE(16);
        buffer.writeWordLE(1);
        buffer.writeWordLE(1);
        buffer.writeDWordLE(22050);
        buffer.writeDWordLE(22050);
        buffer.writeWordLE(1);
        buffer.writeWordLE(8);
        buffer.writeDWord(0x64617461);
        buffer.writeDWordLE(j);
        buffer.offset += j;
        return buffer;
    }

    public int generate(int loopCount) {
        int duration = 0;

        for (int tone = 0; tone < 10; tone++) {
            if (tones[tone] != null && tones[tone].length + tones[tone].start > duration) {
                duration = tones[tone].length + tones[tone].start;
            }
        }

        if (duration == 0) {
            return 0;
        }

        int sampleCount = (22050 * duration) / 1000;
        int loopStart = (22050 * loopBegin) / 1000;
        int loopStop = (22050 * loopEnd) / 1000;

        if (loopStart < 0 || loopStart > sampleCount || loopStop < 0 || loopStop > sampleCount || loopStart >= loopStop) {
            loopCount = 0;
        }

        int totalSampleCount = sampleCount + (loopStop - loopStart) * (loopCount - 1);

        for (int sample = 44; sample < totalSampleCount + 44; sample++) {
            bbuf[sample] = -128;
        }

        for (int tone = 0; tone < 10; tone++) {
            if (tones[tone] != null) {
                int toneSampleCount = (tones[tone].length * 22050) / 1000;
                int start = (tones[tone].start * 22050) / 1000;
                int[] samples = tones[tone].generate(toneSampleCount, tones[tone].length);

                for (int sample = 0; sample < toneSampleCount; sample++) {
                    bbuf[sample + start + 44] += (byte) (samples[sample] >> 8);
                }
            }
        }

        if (loopCount > 1) {
            loopStart += 44;
            loopStop += 44;
            sampleCount += 44;

            int endOffset = (totalSampleCount += 44) - sampleCount;
            System.arraycopy(bbuf, loopStop, bbuf, loopStop + endOffset, sampleCount - loopStop);

            for (int loop = 1; loop < loopCount; loop++) {
                int offset = (loopStop - loopStart) * loop;
                System.arraycopy(bbuf, loopStart, bbuf, loopStart + offset, loopStop - loopStart);
            }

            totalSampleCount -= 44;
        }

        return totalSampleCount;
    }

    public SoundTrack() {
        tones = new SoundTone[10];
    }

    public static SoundTrack[] tracks = new SoundTrack[1000];
    public static int[] delays = new int[1000];
    public static byte[] bbuf;
    public static Buffer buffer;
    public SoundTone[] tones;
    public int loopBegin;
    public int loopEnd;

}
