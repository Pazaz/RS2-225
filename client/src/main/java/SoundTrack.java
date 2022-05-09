import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!yb")
public final class SoundTrack {

	@OriginalMember(owner = "client!yb", name = "e", descriptor = "[B")
	private static byte[] bbuf;

	@OriginalMember(owner = "client!yb", name = "f", descriptor = "Lclient!kb;")
	private static Buffer buffer;

	@OriginalMember(owner = "client!yb", name = "b", descriptor = "I")
	private static final int flowObfuscator1 = 473;

	@OriginalMember(owner = "client!yb", name = "c", descriptor = "[Lclient!yb;")
	private static final SoundTrack[] tracks = new SoundTrack[1000];

	@OriginalMember(owner = "client!yb", name = "d", descriptor = "[I")
	public static final int[] delays = new int[1000];

	@OriginalMember(owner = "client!yb", name = "h", descriptor = "I")
	private int loopBegin;

	@OriginalMember(owner = "client!yb", name = "i", descriptor = "I")
	private int loopEnd;

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "Z")
	private final boolean flowObfuscator2 = true;

	@OriginalMember(owner = "client!yb", name = "g", descriptor = "[Lclient!zb;")
	private final SoundTone[] tones = new SoundTone[10];

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(Lclient!kb;I)V")
	public static void load(@OriginalArg(0) Buffer buffer) {
		bbuf = new byte[441000];
		SoundTrack.buffer = new Buffer(363, bbuf);
		SoundTone.init();
		while (true) {
			@Pc(16) int id = buffer.g2();
			if (id == 65535) {
				return;
			}
			tracks[id] = new SoundTrack();
			tracks[id].read(buffer);
			delays[id] = tracks[id].trim();
		}
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(BII)Lclient!kb;")
	public static Buffer generate(@OriginalArg(1) int loopCouont, @OriginalArg(2) int id) {
		if (tracks[id] == null) {
			return null;
		} else {
			@Pc(12) SoundTrack track = tracks[id];
			return track.toWav(loopCouont);
		}
	}

	@OriginalMember(owner = "client!yb", name = "<init>", descriptor = "()V")
	private SoundTrack() {
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(ZLclient!kb;)V")
	private void read(@OriginalArg(1) Buffer buffer) {
		for (@Pc(1) int tone = 0; tone < 10; tone++) {
			@Pc(6) int included = buffer.g1();
			if (included != 0) {
				buffer.offset--;
				this.tones[tone] = new SoundTone();
				this.tones[tone].read(buffer);
			}
		}
		this.loopBegin = buffer.g2();
		this.loopEnd = buffer.g2();
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(B)I")
	private int trim() {
		@Pc(3) int start = 9999999;
		for (@Pc(5) int j = 0; j < 10; j++) {
			if (this.tones[j] != null && this.tones[j].start / 20 < start) {
				start = this.tones[j].start / 20;
			}
		}
		if (this.loopBegin < this.loopEnd && this.loopBegin / 20 < start) {
			start = this.loopBegin / 20;
		}
		if (start == 9999999 || start == 0) {
			return 0;
		}
		for (@Pc(67) int tone = 0; tone < 10; tone++) {
			if (this.tones[tone] != null) {
				this.tones[tone].start -= start * 20;
			}
		}
		if (this.loopBegin < this.loopEnd) {
			this.loopBegin -= start * 20;
			this.loopEnd -= start * 20;
		}
		return start;
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(ZI)Lclient!kb;")
	private Buffer toWav(@OriginalArg(1) int loopCount) {
		@Pc(3) int j = this.generate(loopCount);
		buffer.offset = 0;
		buffer.p4(1380533830);
		buffer.p4le(j + 36);
		buffer.p4(1463899717);
		buffer.p4(1718449184);
		buffer.p4le(16);
		buffer.p2le(this.flowObfuscator2, 1);
		buffer.p2le(this.flowObfuscator2, 1);
		buffer.p4le(22050);
		buffer.p4le(22050);
		buffer.p2le(this.flowObfuscator2, 1);
		buffer.p2le(this.flowObfuscator2, 8);
		buffer.p4(1684108385);
		buffer.p4le(j);
		buffer.offset += j;
		return buffer;
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(I)I")
	private int generate(@OriginalArg(0) int loopCount) {
		@Pc(3) int duration = 0;
		for (@Pc(5) int tone = 0; tone < 10; tone++) {
			if (this.tones[tone] != null && this.tones[tone].length + this.tones[tone].start > duration) {
				duration = this.tones[tone].length + this.tones[tone].start;
			}
		}
		if (duration == 0) {
			return 0;
		}
		@Pc(51) int sampleCount = duration * 22050 / 1000;
		@Pc(58) int loopStart = this.loopBegin * 22050 / 1000;
		@Pc(65) int loopStop = this.loopEnd * 22050 / 1000;
		if (loopStart < 0 || loopStart > sampleCount || loopStop < 0 || loopStop > sampleCount || loopStart >= loopStop) {
			loopCount = 0;
		}
		@Pc(90) int totalSampleCount = sampleCount + (loopStop - loopStart) * (loopCount - 1);
		for (@Pc(92) int sample = 44; sample < totalSampleCount + 44; sample++) {
			bbuf[sample] = -128;
		}
		@Pc(123) int toneLength;
		@Pc(133) int toneStart;
		@Pc(147) int toneSample;
		for (@Pc(106) int tone = 0; tone < 10; tone++) {
			if (this.tones[tone] != null) {
				toneLength = this.tones[tone].length * 22050 / 1000;
				toneStart = this.tones[tone].start * 22050 / 1000;
				@Pc(145) int[] samples = this.tones[tone].generate(toneLength, this.tones[tone].length);
				for (toneSample = 0; toneSample < toneLength; toneSample++) {
					bbuf[toneSample + toneStart + 44] += (byte) (samples[toneSample] >> 8);
				}
			}
		}
		if (loopCount > 1) {
			loopStart += 44;
			loopStop += 44;
			sampleCount += 44;
			totalSampleCount += 44;
			toneLength = totalSampleCount - sampleCount;
			for (toneStart = sampleCount - 1; toneStart >= loopStop; toneStart--) {
				bbuf[toneStart + toneLength] = bbuf[toneStart];
			}
			for (@Pc(205) int local205 = 1; local205 < loopCount; local205++) {
				toneLength = (loopStop - loopStart) * local205;
				for (toneSample = loopStart; toneSample < loopStop; toneSample++) {
					bbuf[toneSample + toneLength] = bbuf[toneSample];
				}
			}
			totalSampleCount -= 44;
		}
		return totalSampleCount;
	}
}
