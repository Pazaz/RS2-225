import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!yb")
public final class SoundTrack {

	@OriginalMember(owner = "client!yb", name = "d", descriptor = "[I")
	public static final int[] delays = new int[1000];

	@OriginalMember(owner = "client!yb", name = "c", descriptor = "[Lclient!yb;")
	private static final SoundTrack[] tracks = new SoundTrack[1000];

	@OriginalMember(owner = "client!yb", name = "e", descriptor = "[B")
	public static byte[] bbuf;

	@OriginalMember(owner = "client!yb", name = "f", descriptor = "Lclient!kb;")
	public static Buffer buffer;

	@OriginalMember(owner = "client!yb", name = "b", descriptor = "I")
	public static int flowObfuscator2 = 473;

	@OriginalMember(owner = "client!yb", name = "h", descriptor = "I")
	private int loopBegin;

	@OriginalMember(owner = "client!yb", name = "i", descriptor = "I")
	private int loopEnd;

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "Z")
	private final boolean flowObfuscator1 = true;

	@OriginalMember(owner = "client!yb", name = "g", descriptor = "[Lclient!zb;")
	private final SoundTone[] tones = new SoundTone[10];

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(Lclient!kb;I)V")
	public static void load(@OriginalArg(0) Buffer arg0, @OriginalArg(1) int arg1) {
		try {
			bbuf = new byte[441000];
			buffer = new Buffer(bbuf);
			@Pc(12) int local12 = 87 / arg1;
			SoundTone.init();
			while (true) {
				@Pc(16) int local16 = arg0.g2();
				if (local16 == 65535) {
					return;
				}
				tracks[local16] = new SoundTrack();
				tracks[local16].read(false, arg0);
				delays[local16] = tracks[local16].trim((byte) 7);
			}
		} catch (@Pc(42) RuntimeException local42) {
			signlink.reporterror("6214, " + arg0 + ", " + arg1 + ", " + local42.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(BII)Lclient!kb;")
	public static Buffer generate(@OriginalArg(0) byte arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		try {
			if (arg0 != -16) {
				flowObfuscator2 = -83;
			}
			if (tracks[arg2] == null) {
				return null;
			} else {
				@Pc(12) SoundTrack local12 = tracks[arg2];
				return local12.toWav(true, arg1);
			}
		} catch (@Pc(20) RuntimeException local20) {
			signlink.reporterror("72905, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + local20.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(ZLclient!kb;)V")
	public void read(@OriginalArg(0) boolean arg0, @OriginalArg(1) Buffer arg1) {
		try {
			for (@Pc(1) int local1 = 0; local1 < 10; local1++) {
				@Pc(6) int local6 = arg1.g1();
				if (local6 != 0) {
					arg1.pos--;
					this.tones[local1] = new SoundTone();
					this.tones[local1].read(false, arg1);
				}
			}
			if (arg0) {
				flowObfuscator2 = -307;
			}
			this.loopBegin = arg1.g2();
			this.loopEnd = arg1.g2();
		} catch (@Pc(46) RuntimeException local46) {
			signlink.reporterror("58220, " + arg0 + ", " + arg1 + ", " + local46.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(B)I")
	public int trim(@OriginalArg(0) byte arg0) {
		try {
			@Pc(3) int local3 = 9999999;
			for (@Pc(5) int local5 = 0; local5 < 10; local5++) {
				if (this.tones[local5] != null && this.tones[local5].start / 20 < local3) {
					local3 = this.tones[local5].start / 20;
				}
			}
			if (arg0 == 7) {
				@Pc(38) boolean local38 = false;
			} else {
				flowObfuscator2 = -8;
			}
			if (this.loopBegin < this.loopEnd && this.loopBegin / 20 < local3) {
				local3 = this.loopBegin / 20;
			}
			if (local3 == 9999999 || local3 == 0) {
				return 0;
			}
			for (@Pc(67) int local67 = 0; local67 < 10; local67++) {
				if (this.tones[local67] != null) {
					this.tones[local67].start -= local3 * 20;
				}
			}
			if (this.loopBegin < this.loopEnd) {
				this.loopBegin -= local3 * 20;
				this.loopEnd -= local3 * 20;
			}
			return local3;
		} catch (@Pc(113) RuntimeException local113) {
			signlink.reporterror("49328, " + arg0 + ", " + local113.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(ZI)Lclient!kb;")
	public Buffer toWav(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1) {
		try {
			@Pc(3) int local3 = this.generate(arg1);
			buffer.pos = 0;
			buffer.p4(1380533830);
			buffer.ip4(local3 + 36);
			buffer.p4(1463899717);
			buffer.p4(1718449184);
			buffer.ip4(16);
			if (!arg0) {
				for (@Pc(29) int local29 = 1; local29 > 0; local29++) {
				}
			}
			buffer.ip2(1);
			buffer.ip2(1);
			buffer.ip4(22050);
			buffer.ip4(22050);
			buffer.ip2(1);
			buffer.ip2(8);
			buffer.p4(1684108385);
			buffer.ip4(local3);
			buffer.pos += local3;
			return buffer;
		} catch (@Pc(78) RuntimeException local78) {
			signlink.reporterror("83597, " + arg0 + ", " + arg1 + ", " + local78.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(I)I")
	private int generate(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = 0;
		for (@Pc(5) int local5 = 0; local5 < 10; local5++) {
			if (this.tones[local5] != null && this.tones[local5].delay + this.tones[local5].start > local3) {
				local3 = this.tones[local5].delay + this.tones[local5].start;
			}
		}
		if (local3 == 0) {
			return 0;
		}
		@Pc(51) int local51 = local3 * 22050 / 1000;
		@Pc(58) int local58 = this.loopBegin * 22050 / 1000;
		@Pc(65) int local65 = this.loopEnd * 22050 / 1000;
		if (local58 < 0 || local58 > local51 || local65 < 0 || local65 > local51 || local58 >= local65) {
			arg0 = 0;
		}
		@Pc(90) int local90 = local51 + (local65 - local58) * (arg0 - 1);
		for (@Pc(92) int local92 = 44; local92 < local90 + 44; local92++) {
			bbuf[local92] = -128;
		}
		@Pc(123) int local123;
		@Pc(133) int local133;
		@Pc(147) int local147;
		for (@Pc(106) int local106 = 0; local106 < 10; local106++) {
			if (this.tones[local106] != null) {
				local123 = this.tones[local106].delay * 22050 / 1000;
				local133 = this.tones[local106].start * 22050 / 1000;
				@Pc(145) int[] local145 = this.tones[local106].generate(local123, this.tones[local106].delay);
				for (local147 = 0; local147 < local123; local147++) {
					bbuf[local147 + local133 + 44] += (byte) (local145[local147] >> 8);
				}
			}
		}
		if (arg0 > 1) {
			local58 += 44;
			local65 += 44;
			local51 += 44;
			local90 += 44;
			local123 = local90 - local51;
			for (local133 = local51 - 1; local133 >= local65; local133--) {
				bbuf[local133 + local123] = bbuf[local133];
			}
			for (@Pc(205) int local205 = 1; local205 < arg0; local205++) {
				local123 = (local65 - local58) * local205;
				for (local147 = local58; local147 < local65; local147++) {
					bbuf[local147 + local123] = bbuf[local147];
				}
			}
			local90 -= 44;
		}
		return local90;
	}
}
