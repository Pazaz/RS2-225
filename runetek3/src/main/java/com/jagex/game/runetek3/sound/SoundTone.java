package com.jagex.game.runetek3.sound;

import com.jagex.core.io.Buffer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!zb")
public class SoundTone {

	@OriginalMember(owner = "client!zb", name = "r", descriptor = "[I")
	private static int[] buffer;

	@OriginalMember(owner = "client!zb", name = "s", descriptor = "[I")
	private static int[] noise;

	@OriginalMember(owner = "client!zb", name = "t", descriptor = "[I")
	private static int[] sin;

	@OriginalMember(owner = "client!zb", name = "u", descriptor = "[I")
	private static final int[] tmpPhases = new int[5];

	@OriginalMember(owner = "client!zb", name = "v", descriptor = "[I")
	private static final int[] tmpDelays = new int[5];

	@OriginalMember(owner = "client!zb", name = "w", descriptor = "[I")
	private static final int[] tmpVolumes = new int[5];

	@OriginalMember(owner = "client!zb", name = "x", descriptor = "[I")
	private static final int[] tmpSemitones = new int[5];

	@OriginalMember(owner = "client!zb", name = "y", descriptor = "[I")
	private static final int[] tmpStarts = new int[5];

	@OriginalMember(owner = "client!zb", name = "c", descriptor = "Lclient!xb;")
	private SoundEnvelope frequencyBase;

	@OriginalMember(owner = "client!zb", name = "d", descriptor = "Lclient!xb;")
	private SoundEnvelope amplitudeBase;

	@OriginalMember(owner = "client!zb", name = "e", descriptor = "Lclient!xb;")
	private SoundEnvelope frequencyModRate;

	@OriginalMember(owner = "client!zb", name = "f", descriptor = "Lclient!xb;")
	private SoundEnvelope frequencyModRange;

	@OriginalMember(owner = "client!zb", name = "g", descriptor = "Lclient!xb;")
	private SoundEnvelope amplitudeModRate;

	@OriginalMember(owner = "client!zb", name = "h", descriptor = "Lclient!xb;")
	private SoundEnvelope amplitudeModRange;

	@OriginalMember(owner = "client!zb", name = "i", descriptor = "Lclient!xb;")
	private SoundEnvelope release;

	@OriginalMember(owner = "client!zb", name = "j", descriptor = "Lclient!xb;")
	private SoundEnvelope attack;

	@OriginalMember(owner = "client!zb", name = "n", descriptor = "I")
	private int reverbDelay;

	@OriginalMember(owner = "client!zb", name = "q", descriptor = "I")
	public int start;

	@OriginalMember(owner = "client!zb", name = "k", descriptor = "[I")
	private final int[] harmonicVolume = new int[5];

	@OriginalMember(owner = "client!zb", name = "l", descriptor = "[I")
	private final int[] harmonicSemitone = new int[5];

	@OriginalMember(owner = "client!zb", name = "m", descriptor = "[I")
	private final int[] harmonicDelay = new int[5];

	@OriginalMember(owner = "client!zb", name = "o", descriptor = "I")
	private int reverbVolume = 100;

	@OriginalMember(owner = "client!zb", name = "p", descriptor = "I")
	public int length = 500;

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "()V")
	public static void init() {
		noise = new int[32768];
		for (@Pc(6) int i = 0; i < 32768; i++) {
			if (Math.random() > 0.5D) {
				noise[i] = 1;
			} else {
				noise[i] = -1;
			}
		}
		sin = new int[32768];
		for (@Pc(31) int i = 0; i < 32768; i++) {
			sin[i] = (int) (Math.sin((double) i / 5215.1903D) * 16384.0D);
		}
		buffer = new int[220500];
	}

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "(II)[I")
	public int[] generate(@OriginalArg(0) int sampleCount, @OriginalArg(1) int length) {
		for (@Pc(3) int sample = 0; sample < sampleCount; sample++) {
			buffer[sample] = 0;
		}
		if (length < 10) {
			return buffer;
		}
		@Pc(26) double samplesPerStep = (double) sampleCount / ((double) length + 0.0D);
		this.frequencyBase.reset();
		this.amplitudeBase.reset();
		@Pc(36) int frequencyStart = 0;
		@Pc(38) int frequencyDuration = 0;
		@Pc(40) int frequencyPhase = 0;
		if (this.frequencyModRate != null) {
			this.frequencyModRate.reset();
			this.frequencyModRange.reset();
			frequencyStart = (int) ((double) (this.frequencyModRate.end - this.frequencyModRate.start) * 32.768D / samplesPerStep);
			frequencyDuration = (int) ((double) this.frequencyModRate.start * 32.768D / samplesPerStep);
		}
		@Pc(77) int amplitudeStart = 0;
		@Pc(79) int amplitudeDuration = 0;
		@Pc(81) int amplitudePhase = 0;
		if (this.amplitudeModRate != null) {
			this.amplitudeModRate.reset();
			this.amplitudeModRange.reset();
			amplitudeStart = (int) ((double) (this.amplitudeModRate.end - this.amplitudeModRate.start) * 32.768D / samplesPerStep);
			amplitudeDuration = (int) ((double) this.amplitudeModRate.start * 32.768D / samplesPerStep);
		}
		for (@Pc(118) int harmonic = 0; harmonic < 5; harmonic++) {
			if (this.harmonicVolume[harmonic] != 0) {
				tmpPhases[harmonic] = 0;
				tmpDelays[harmonic] = (int) ((double) this.harmonicDelay[harmonic] * samplesPerStep);
				tmpVolumes[harmonic] = (this.harmonicVolume[harmonic] << 14) / 100;
				tmpSemitones[harmonic] = (int) ((double) (this.frequencyBase.end - this.frequencyBase.start) * 32.768D * Math.pow(1.0057929410678534D, (double) this.harmonicSemitone[harmonic]) / samplesPerStep);
				tmpStarts[harmonic] = (int) ((double) this.frequencyBase.start * 32.768D / samplesPerStep);
			}
		}
		@Pc(201) int frequency;
		@Pc(207) int amplitude;
		@Pc(222) int range;
		for (@Pc(193) int i = 0; i < sampleCount; i++) {
			frequency = this.frequencyBase.evaluate(sampleCount);
			amplitude = this.amplitudeBase.evaluate(sampleCount);
			@Pc(216) int rate;
			if (this.frequencyModRate != null) {
				rate = this.frequencyModRate.evaluate(sampleCount);
				range = this.frequencyModRange.evaluate(sampleCount);
				frequency += this.generate(range, frequencyPhase, this.frequencyModRate.form) >> 1;
				frequencyPhase += (rate * frequencyStart >> 16) + frequencyDuration;
			}
			if (this.amplitudeModRate != null) {
				rate = this.amplitudeModRate.evaluate(sampleCount);
				range = this.amplitudeModRange.evaluate(sampleCount);
				amplitude = amplitude * ((this.generate(range, amplitudePhase, this.amplitudeModRate.form) >> 1) + 32768) >> 15;
				amplitudePhase += (rate * amplitudeStart >> 16) + amplitudeDuration;
			}
			for (rate = 0; rate < 5; rate++) {
				if (this.harmonicVolume[rate] != 0) {
					range = i + tmpDelays[rate];
					if (range < sampleCount) {
						buffer[range] += this.generate(amplitude * tmpVolumes[rate] >> 15, tmpPhases[rate], this.frequencyBase.form);
						tmpPhases[rate] += (frequency * tmpSemitones[rate] >> 16) + tmpStarts[rate];
					}
				}
			}
		}
		if (this.release != null) {
			this.release.reset();
			this.attack.reset();
			frequency = 0;
			@Pc(369) boolean muted = true;
			for (range = 0; range < sampleCount; range++) {
				@Pc(379) int releaseValue = this.release.evaluate(sampleCount);
				@Pc(385) int attackValue = this.attack.evaluate(sampleCount);
				if (muted) {
					amplitude = this.release.start + ((this.release.end - this.release.start) * releaseValue >> 8);
				} else {
					amplitude = this.release.start + ((this.release.end - this.release.start) * attackValue >> 8);
				}
				frequency += 256;
				if (frequency >= amplitude) {
					frequency = 0;
					muted = !muted;
				}
				if (muted) {
					buffer[range] = 0;
				}
			}
		}
		if (this.reverbDelay > 0 && this.reverbVolume > 0) {
			frequency = (int) ((double) this.reverbDelay * samplesPerStep);
			for (amplitude = frequency; amplitude < sampleCount; amplitude++) {
				buffer[amplitude] += buffer[amplitude - frequency] * this.reverbVolume / 100;
			}
		}
		for (frequency = 0; frequency < sampleCount; frequency++) {
			if (buffer[frequency] < -32768) {
				buffer[frequency] = -32768;
			}
			if (buffer[frequency] > 32767) {
				buffer[frequency] = 32767;
			}
		}
		return buffer;
	}

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "(IIII)I")
	private int generate(@OriginalArg(1) int amplitude, @OriginalArg(2) int phase, @OriginalArg(3) int form) {
		if (form == 1) {
			return (phase & 0x7FFF) < 16384 ? amplitude : -amplitude;
		} else if (form == 2) {
			return sin[phase & 0x7FFF] * amplitude >> 14;
		} else if (form == 3) {
			return ((phase & 0x7FFF) * amplitude >> 14) - amplitude;
		} else if (form == 4) {
			return noise[phase / 2607 & 0x7FFF] * amplitude;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "(ZLclient!kb;)V")
	public void read(@OriginalArg(1) Buffer buffer) {
		this.frequencyBase = new SoundEnvelope();
		this.frequencyBase.readShape(buffer);
		this.amplitudeBase = new SoundEnvelope();
		this.amplitudeBase.readShape(buffer);
		@Pc(24) int included = buffer.g1();
		if (included != 0) {
			buffer.pos--;
			this.frequencyModRate = new SoundEnvelope();
			this.frequencyModRate.readShape(buffer);
			this.frequencyModRange = new SoundEnvelope();
			this.frequencyModRange.readShape(buffer);
		}
		included = buffer.g1();
		if (included != 0) {
			buffer.pos--;
			this.amplitudeModRate = new SoundEnvelope();
			this.amplitudeModRate.readShape(buffer);
			this.amplitudeModRange = new SoundEnvelope();
			this.amplitudeModRange.readShape(buffer);
		}
		included = buffer.g1();
		if (included != 0) {
			buffer.pos--;
			this.release = new SoundEnvelope();
			this.release.readShape(buffer);
			this.attack = new SoundEnvelope();
			this.attack.readShape(buffer);
		}
		for (@Pc(122) int i = 0; i < 10; i++) {
			@Pc(132) int volume = buffer.gsmarts();
			if (volume == 0) {
				break;
			}
			this.harmonicVolume[i] = volume;
			this.harmonicSemitone[i] = buffer.gsmart();
			this.harmonicDelay[i] = buffer.gsmarts();
		}
		this.reverbDelay = buffer.gsmarts();
		this.reverbVolume = buffer.gsmarts();
		this.length = buffer.g2();
		this.start = buffer.g2();
	}

	@OriginalMember(owner = "client!zb", name = "b", descriptor = "I")
	private final int flowObfuscator2 = -15143;

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "I")
	private static final int flowObfuscator1 = 8;

}
