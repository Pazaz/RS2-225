package com.jagex.runetek3.sound;

import com.jagex.runetek3.util.Buffer;

public class SoundTone {

    public static int[] buffer;
    public static int[] noise;
    public static int[] sin;
    public static int[] tmpPhases = new int[5];
    public static int[] tmpDelays = new int[5];
    public static int[] tmpVolumes = new int[5];
    public static int[] tmpSemitones = new int[5];
    public static int[] tmpStarts = new int[5];
    public SoundEnvelope frequencyBase;
    public SoundEnvelope amplitudeBase;
    public SoundEnvelope frequencyModRate;
    public SoundEnvelope frequencyModRange;
    public SoundEnvelope amplitudeModRate;
    public SoundEnvelope amplitudeModRange;
    public SoundEnvelope release;
    public SoundEnvelope attack;
    public int[] harmonicVolume;
    public int[] harmonicSemitone;
    public int[] harmonicDelay;
    public int reverbDelay;
    public int reverbVolume;
    public int length;
    public int start;

    public SoundTone() {
        harmonicVolume = new int[5];
        harmonicSemitone = new int[5];
        harmonicDelay = new int[5];
        reverbVolume = 100;
        length = 500;
    }

    public static void init() {
        noise = new int[32768];
        for (int i = 0; i < 32768; i++) {
            if (Math.random() > 0.5D) {
                noise[i] = 1;
            } else {
                noise[i] = -1;
            }
        }

        sin = new int[32768];
        for (int j = 0; j < 32768; j++) {
            sin[j] = (int) (Math.sin((double) j / 5215.1903000000002D) * 16384D);
        }

        // 22050 kHz sample rate, 10 seconds
        buffer = new int[22050 * 10];
    }

    public int[] generate(int sampleCount, int length) {
        for (int sample = 0; sample < sampleCount; sample++) {
            buffer[sample] = 0;
        }

        if (length < 10) {
            return buffer;
        }

        double samplesPerStep = (double) sampleCount / ((double) length + 0.0D);

        frequencyBase.reset();
        amplitudeBase.reset();

        int frequencyStart = 0;
        int frequencyDuration = 0;
        int frequencyPhase = 0;

        if (frequencyModRate != null) {
            frequencyModRate.reset();
            frequencyModRange.reset();
            frequencyStart = (int) (((double) (frequencyModRate.end - frequencyModRate.start) * 32.768000000000001D) / samplesPerStep);
            frequencyDuration = (int) (((double) frequencyModRate.start * 32.768000000000001D) / samplesPerStep);
        }

        int amplitudeStart = 0;
        int amplitudeDuration = 0;
        int amplitudePhase = 0;

        if (amplitudeModRate != null) {
            amplitudeModRate.reset();
            amplitudeModRange.reset();
            amplitudeStart = (int) (((double) (amplitudeModRate.end - amplitudeModRate.start) * 32.768000000000001D) / samplesPerStep);
            amplitudeDuration = (int) (((double) amplitudeModRate.start * 32.768000000000001D) / samplesPerStep);
        }

        for (int harmonic = 0; harmonic < 5; harmonic++) {
            if (harmonicVolume[harmonic] != 0) {
                tmpPhases[harmonic] = 0;
                tmpDelays[harmonic] = (int) ((double) harmonicDelay[harmonic] * samplesPerStep);
                tmpVolumes[harmonic] = (harmonicVolume[harmonic] << 14) / 100;
                tmpSemitones[harmonic] = (int) (((double) (frequencyBase.end - frequencyBase.start)
                    * 32.768000000000001D * Math.pow(1.0057929410678534D, harmonicSemitone[harmonic])) / samplesPerStep);
                tmpStarts[harmonic] = (int) (((double) frequencyBase.start * 32.768000000000001D) / samplesPerStep);
            }
        }

        for (int sample = 0; sample < sampleCount; sample++) {
            int frequency = frequencyBase.evaluate(sampleCount);
            int amplitude = amplitudeBase.evaluate(sampleCount);

            if (frequencyModRate != null) {
                int rate = frequencyModRate.evaluate(sampleCount);
                int range = frequencyModRange.evaluate(sampleCount);
                frequency += generate(range, frequencyPhase, frequencyModRate.form) >> 1;
                frequencyPhase += (rate * frequencyStart >> 16) + frequencyDuration;
            }

            if (amplitudeModRate != null) {
                int rate = amplitudeModRate.evaluate(sampleCount);
                int range = amplitudeModRange.evaluate(sampleCount);
                amplitude = amplitude * ((generate(range, amplitudePhase, amplitudeModRate.form) >> 1) + 32768) >> 15;
                amplitudePhase += (rate * amplitudeStart >> 16) + amplitudeDuration;
            }

            for (int harmonic = 0; harmonic < 5; harmonic++) {
                if (harmonicVolume[harmonic] != 0) {
                    int position = sample + tmpDelays[harmonic];
                    if (position < sampleCount) {
                        buffer[position] += generate(amplitude * tmpVolumes[harmonic] >> 15, tmpPhases[harmonic],
                            frequencyBase.form);
                        tmpPhases[harmonic] += (frequency * tmpSemitones[harmonic] >> 16) + tmpStarts[harmonic];
                    }
                }
            }
        }

        if (release != null) {
            release.reset();
            attack.reset();

            int counter = 0;
            boolean muted = true;

            for (int sample = 0; sample < sampleCount; sample++) {
                int releaseValue = release.evaluate(sampleCount);
                int attackValue = attack.evaluate(sampleCount);
                int threshold;

                if (muted) {
                    threshold = release.start + ((release.end - release.start) * releaseValue >> 8);
                } else {
                    threshold = release.start + ((release.end - release.start) * attackValue >> 8);
                }

                if ((counter += 256) >= threshold) {
                    counter = 0;
                    muted = !muted;
                }

                if (muted) {
                    buffer[sample] = 0;
                }
            }
        }

        if (reverbDelay > 0 && reverbVolume > 0) {
            int start = (int) ((double) reverbDelay * samplesPerStep);
            for (int sample = start; sample < sampleCount; sample++) {
                buffer[sample] += (buffer[sample - start] * reverbVolume) / 100;
            }
        }

        for (int sample = 0; sample < sampleCount; sample++) {
            if (buffer[sample] < -32768) {
                buffer[sample] = -32768;
            }
            if (buffer[sample] > 32767) {
                buffer[sample] = 32767;
            }
        }

        return buffer;
    }

    public int generate(int amplitude, int phase, int form) {
        if (form == 1) {
            if ((phase & 0x7fff) < 16384) {
                return amplitude;
            } else {
                return -amplitude;
            }
        } else if (form == 2) {
            return sin[phase & 0x7fff] * amplitude >> 14;
        } else if (form == 3) {
            return ((phase & 0x7fff) * amplitude >> 14) - amplitude;
        } else if (form == 4) {
            return noise[phase / 2607 & 0x7fff] * amplitude;
        } else {
            return 0;
        }
    }

    public void read(Buffer buffer) {
        frequencyBase = new SoundEnvelope();
        frequencyBase.readShape(buffer);
        amplitudeBase = new SoundEnvelope();
        amplitudeBase.readShape(buffer);

        if (buffer.g1() != 0) {
            buffer.offset--;
            frequencyModRate = new SoundEnvelope();
            frequencyModRate.readShape(buffer);
            frequencyModRange = new SoundEnvelope();
            frequencyModRange.readShape(buffer);
        }

        if (buffer.g1() != 0) {
            buffer.offset--;
            amplitudeModRate = new SoundEnvelope();
            amplitudeModRate.readShape(buffer);
            amplitudeModRange = new SoundEnvelope();
            amplitudeModRange.readShape(buffer);
        }

        if (buffer.g1() != 0) {
            buffer.offset--;
            release = new SoundEnvelope();
            release.readShape(buffer);
            attack = new SoundEnvelope();
            attack.readShape(buffer);
        }

        for (int j = 0; j < 10; j++) {
            int volume = buffer.gSmart1or2();

            if (volume == 0) {
                break;
            }

            harmonicVolume[j] = volume;
            harmonicSemitone[j] = buffer.gSmart1or2s();
            harmonicDelay[j] = buffer.gSmart1or2();
        }

        reverbDelay = buffer.gSmart1or2();
        reverbVolume = buffer.gSmart1or2();

        length = buffer.g2();
        start = buffer.g2();
    }

}
