package com.jagex.game.runetek3.audio;

import com.jagex.core.io.Buffer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!yb")
public class SoundTrack {

	@OriginalMember(owner = "client!yb", name = "d", descriptor = "[I")
	public static final int[] delays = new int[1000];

	@OriginalMember(owner = "client!yb", name = "c", descriptor = "[Lclient!yb;")
	private static final SoundTrack[] tracks = new SoundTrack[1000];

	@OriginalMember(owner = "client!yb", name = "e", descriptor = "[B")
	public static byte[] bbuf;

	@OriginalMember(owner = "client!yb", name = "f", descriptor = "Lclient!kb;")
	public static Buffer buffer;

	@OriginalMember(owner = "client!yb", name = "h", descriptor = "I")
	private int loopBegin;

	@OriginalMember(owner = "client!yb", name = "i", descriptor = "I")
	private int loopEnd;

	@OriginalMember(owner = "client!yb", name = "g", descriptor = "[Lclient!zb;")
	private final SoundTone[] tones = new SoundTone[10];

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(Lclient!kb;I)V")
	public static void unpack(@OriginalArg(0) Buffer archive) {
		bbuf = new byte[441000];
		buffer = new Buffer(bbuf);

		SoundTone.init();

		while (true) {
			@Pc(16) int id = archive.g2();
			if (id == 65535) {
				return;
			}

			tracks[id] = new SoundTrack();
			tracks[id].read(archive);
			delays[id] = tracks[id].trim();
		}
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(BII)Lclient!kb;")
	public static Buffer generate(@OriginalArg(1) int loops, @OriginalArg(2) int id) {
		if (tracks[id] == null) {
			return null;
		} else {
			@Pc(12) SoundTrack track = tracks[id];
			return track.toWav(loops);
		}
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(ZLclient!kb;)V")
	public void read(@OriginalArg(1) Buffer buf) {
		for (@Pc(1) int tone = 0; tone < 10; tone++) {
			@Pc(6) int hasTone = buf.g1();

			if (hasTone != 0) {
				buf.pos--;
				this.tones[tone] = new SoundTone();
				this.tones[tone].read(buf);
			}
		}

		this.loopBegin = buf.g2();
		this.loopEnd = buf.g2();
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(B)I")
	public int trim() {
		@Pc(3) int start = 9999999;
		for (@Pc(5) int i = 0; i < 10; i++) {
			if (this.tones[i] != null && this.tones[i].start / 20 < start) {
				start = this.tones[i].start / 20;
			}
		}

		if (this.loopBegin < this.loopEnd && this.loopBegin / 20 < start) {
			start = this.loopBegin / 20;
		}

		if (start == 9999999 || start == 0) {
			return 0;
		}

		for (@Pc(67) int i = 0; i < 10; i++) {
			if (this.tones[i] != null) {
				this.tones[i].start -= start * 20;
			}
		}

		if (this.loopBegin < this.loopEnd) {
			this.loopBegin -= start * 20;
			this.loopEnd -= start * 20;
		}

		return start;
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(ZI)Lclient!kb;")
	public Buffer toWav(@OriginalArg(1) int loops) {
		@Pc(3) int size = this.generate(loops);

		buffer.pos = 0;
		// RIFF header
		buffer.p4(0x52494646); // Chunk ID
		buffer.ip4(size + 36); // Chunk Size
		buffer.p4(0x57415645); // Format
		// WAVE format
		// Fmt subchunk
		buffer.p4(0x666d7420); // Subchunk1 ID
		buffer.ip4(16); // Subchunk1 Size
		buffer.ip2(1); // Audio Format (PCM)
		buffer.ip2(1); // Num Channels (Mono)
		buffer.ip4(22050); // Sample Rate
		buffer.ip4(22050); // Byte Rate
		buffer.ip2(1); // Block Align
		buffer.ip2(8); // Bits Per Sample
		// Data subchunk
		buffer.p4(0x64617461); // Subchunk2 ID
		buffer.ip4(size); // Subchunk2 Size
		buffer.pos += size;
		return buffer;
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(I)I")
	private int generate(@OriginalArg(0) int loops) {
		@Pc(3) int duration = 0;
		for (@Pc(5) int i = 0; i < 10; i++) {
			if (this.tones[i] != null && this.tones[i].delay + this.tones[i].start > duration) {
				duration = this.tones[i].delay + this.tones[i].start;
			}
		}

		if (duration == 0) {
			return 0;
		}

		@Pc(51) int sampleCount = duration * 22050 / 1000;
		@Pc(58) int loopStart = this.loopBegin * 22050 / 1000;
		@Pc(65) int loopEnd = this.loopEnd * 22050 / 1000;
		if (loopStart < 0 || loopStart > sampleCount || loopEnd < 0 || loopEnd > sampleCount || loopStart >= loopEnd) {
			loops = 0;
		}

		@Pc(90) int totalSampleCount = sampleCount + (loopEnd - loopStart) * (loops - 1);
		for (@Pc(92) int i = 44; i < totalSampleCount + 44; i++) {
			bbuf[i] = -128;
		}

		@Pc(123) int toneLength;
		@Pc(133) int toneStart;
		@Pc(147) int toneSample;
		for (@Pc(106) int i = 0; i < 10; i++) {
			if (this.tones[i] != null) {
				toneLength = this.tones[i].delay * 22050 / 1000;
				toneStart = this.tones[i].start * 22050 / 1000;

				@Pc(145) int[] samples = this.tones[i].generate(toneLength, this.tones[i].delay);
				for (toneSample = 0; toneSample < toneLength; toneSample++) {
					bbuf[toneSample + toneStart + 44] += (byte) (samples[toneSample] >> 8);
				}
			}
		}

		if (loops > 1) {
			loopStart += 44;
			loopEnd += 44;
			sampleCount += 44;
			totalSampleCount += 44;
			toneLength = totalSampleCount - sampleCount;

			for (toneStart = sampleCount - 1; toneStart >= loopEnd; toneStart--) {
				bbuf[toneStart + toneLength] = bbuf[toneStart];
			}

			for (@Pc(205) int i = 1; i < loops; i++) {
				toneLength = (loopEnd - loopStart) * i;
				for (toneSample = loopStart; toneSample < loopEnd; toneSample++) {
					bbuf[toneSample + toneLength] = bbuf[toneSample];
				}
			}

			totalSampleCount -= 44;
		}

		return totalSampleCount;
	}
}
