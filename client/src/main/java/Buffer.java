import java.math.BigInteger;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kb")
public final class Buffer extends CacheableNode {

	@OriginalMember(owner = "client!kb", name = "w", descriptor = "I")
	private static int queueMinCount;

	@OriginalMember(owner = "client!kb", name = "x", descriptor = "I")
	private static int queueMidCount;

	@OriginalMember(owner = "client!kb", name = "y", descriptor = "I")
	private static int queueMaxCount;

	@OriginalMember(owner = "client!kb", name = "h", descriptor = "I")
	private static final int flowObfuscator5 = 40946;

	@OriginalMember(owner = "client!kb", name = "o", descriptor = "Z")
	private static boolean flowObfuscator1 = true;

	@OriginalMember(owner = "client!kb", name = "t", descriptor = "[I")
	private static final int[] CRC32_TABLE = new int[256];

	@OriginalMember(owner = "client!kb", name = "u", descriptor = "[I")
	private static final int[] BITMASK;

	@OriginalMember(owner = "client!kb", name = "z", descriptor = "Lclient!ob;")
	private static final LinkedList queueMin;

	@OriginalMember(owner = "client!kb", name = "A", descriptor = "Lclient!ob;")
	private static final LinkedList queueMid;

	@OriginalMember(owner = "client!kb", name = "B", descriptor = "Lclient!ob;")
	private static final LinkedList queueMax;

	@OriginalMember(owner = "client!kb", name = "q", descriptor = "[B")
	public byte[] data;

	@OriginalMember(owner = "client!kb", name = "r", descriptor = "I")
	public int offset;

	@OriginalMember(owner = "client!kb", name = "s", descriptor = "I")
	public int bitOffset;

	@OriginalMember(owner = "client!kb", name = "v", descriptor = "Lclient!tb;")
	public IsaacRandom isaac;

	@OriginalMember(owner = "client!kb", name = "i", descriptor = "B")
	private final byte flowObfuscator2 = -34;

	@OriginalMember(owner = "client!kb", name = "j", descriptor = "B")
	private final byte flowObfuscator3 = -106;

	@OriginalMember(owner = "client!kb", name = "k", descriptor = "I")
	private final int flowObfuscator6 = 3;

	@OriginalMember(owner = "client!kb", name = "l", descriptor = "I")
	private final int flowObfuscator7 = -506;

	@OriginalMember(owner = "client!kb", name = "m", descriptor = "Z")
	private final boolean flowObfuscator8 = true;

	@OriginalMember(owner = "client!kb", name = "n", descriptor = "I")
	private final int flowObfuscator4 = 4277;

	@OriginalMember(owner = "client!kb", name = "p", descriptor = "I")
	private final int flowObfuscator9 = -178;

	static {
		for (@Pc(8) int i = 0; i < 256; i++) {
			@Pc(11) int temp = i;
			for (@Pc(13) int local13 = 0; local13 < 8; local13++) {
				if ((temp & 0x1) == 1) {
					temp = temp >>> 1 ^ 0xEDB88320;
				} else {
					temp >>>= 0x1;
				}
			}
			CRC32_TABLE[i] = temp;
		}
		BITMASK = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };
		queueMin = new LinkedList(0);
		queueMid = new LinkedList(0);
		queueMax = new LinkedList(0);
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(II)Lclient!kb;")
	public static Buffer reserve() {
		@Pc(3) LinkedList queue = queueMid;
		synchronized (queueMid) {
			@Pc(7) Buffer queueBuffer = null;
			if (queueMidCount > 0) {
				queueMidCount--;
				queueBuffer = (Buffer) queueMid.poll();
			}
			if (queueBuffer != null) {
				queueBuffer.offset = 0;
				return queueBuffer;
			}
		}
		@Pc(77) Buffer buffer = new Buffer(flowObfuscator5);
		buffer.offset = 0;
		buffer.data = new byte[5000];
		return buffer;
	}

	@OriginalMember(owner = "client!kb", name = "<init>", descriptor = "(I)V")
	private Buffer(@OriginalArg(0) int obfuscator) {
		if (obfuscator != 40946) {
			flowObfuscator1 = !flowObfuscator1;
		}
	}

	@OriginalMember(owner = "client!kb", name = "<init>", descriptor = "(I[B)V")
	public Buffer(@OriginalArg(0) int obfuscator, @OriginalArg(1) byte[] src) {
		this.data = src;
		this.offset = 0;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(B)V")
	public final void release() {
		@Pc(1) LinkedList queue = queueMid;
		synchronized (queueMid) {
			this.offset = 0;
			if (this.data.length == 100 && queueMinCount < 1000) {
				queueMin.pushNext(this);
				queueMinCount++;
			} else if (this.data.length == 5000 && queueMidCount < 250) {
				queueMid.pushNext(this);
				queueMidCount++;
			} else if (this.data.length == 30000 && queueMaxCount < 50) {
				queueMax.pushNext(this);
				queueMaxCount++;
			}
		}
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(BI)V")
	public final void p1isaac(@OriginalArg(1) int opcode) {
		this.data[this.offset++] = (byte) (opcode + this.isaac.nextInt());
		if (this.flowObfuscator2 != -34) {
			flowObfuscator1 = !flowObfuscator1;
		}
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(I)V")
	public final void p1(@OriginalArg(0) int value) {
		this.data[this.offset++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(I)V")
	public final void p2(@OriginalArg(0) int value) {
		this.data[this.offset++] = (byte) (value >> 8);
		this.data[this.offset++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(ZI)V")
	public final void p2le(@OriginalArg(0) boolean obfuscator, @OriginalArg(1) int value) {
		if (!obfuscator) {
			flowObfuscator1 = !flowObfuscator1;
		}
		this.data[this.offset++] = (byte) value;
		this.data[this.offset++] = 0;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "(I)V")
	public final void p3(@OriginalArg(0) int value) {
		this.data[this.offset++] = (byte) (value >> 16);
		this.data[this.offset++] = (byte) (value >> 8);
		this.data[this.offset++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "d", descriptor = "(I)V")
	public final void p4(@OriginalArg(0) int value) {
		this.data[this.offset++] = (byte) (value >> 24);
		this.data[this.offset++] = (byte) (value >> 16);
		this.data[this.offset++] = (byte) (value >> 8);
		this.data[this.offset++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(ZI)V")
	public final void p4le(@OriginalArg(1) int value) {
		this.data[this.offset++] = (byte) value;
		this.data[this.offset++] = (byte) (value >> 8);
		this.data[this.offset++] = (byte) (value >> 16);
		this.data[this.offset++] = (byte) (value >> 24);
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(ZJ)V")
	public final void p8(@OriginalArg(1) long value) {
		this.data[this.offset++] = (byte) (value >> 56);
		this.data[this.offset++] = (byte) (value >> 48);
		this.data[this.offset++] = (byte) (value >> 40);
		this.data[this.offset++] = (byte) (value >> 32);
		this.data[this.offset++] = (byte) (value >> 24);
		this.data[this.offset++] = (byte) (value >> 16);
		this.data[this.offset++] = (byte) (value >> 8);
		this.data[this.offset++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(Ljava/lang/String;)V")
	public final void pjstr(@OriginalArg(0) String str) {
		str.getBytes(0, str.length(), this.data, this.offset);
		this.offset += str.length();
		this.data[this.offset++] = 10;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "([BIIB)V")
	public final void pBytes(@OriginalArg(0) byte[] bytes, @OriginalArg(1) int length) {
		@Pc(7) int i;
		if (this.flowObfuscator3 != -106) {
			for (i = 1; i > 0; i++) {
			}
		}
		for (i = 0; i < length + 0; i++) {
			this.data[this.offset++] = bytes[i];
		}
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(II)V")
	public final void p1len(@OriginalArg(1) int length) {
		this.data[this.offset - length - 1] = (byte) length;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "()I")
	public final int g1() {
		return this.data[this.offset++] & 0xFF;
	}

	@OriginalMember(owner = "client!kb", name = "d", descriptor = "()B")
	public final byte g1s() {
		return this.data[this.offset++];
	}

	@OriginalMember(owner = "client!kb", name = "e", descriptor = "()I")
	public final int g2() {
		this.offset += 2;
		return ((this.data[this.offset - 2] & 0xFF) << 8) + (this.data[this.offset - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "f", descriptor = "()I")
	public final int g2s() {
		this.offset += 2;
		@Pc(27) int value = ((this.data[this.offset - 2] & 0xFF) << 8) + (this.data[this.offset - 1] & 0xFF);
		if (value > 32767) {
			value -= 65536;
		}
		return value;
	}

	@OriginalMember(owner = "client!kb", name = "g", descriptor = "()I")
	public final int g3() {
		this.offset += 3;
		return ((this.data[this.offset - 3] & 0xFF) << 16) + ((this.data[this.offset - 2] & 0xFF) << 8) + (this.data[this.offset - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "h", descriptor = "()I")
	public final int g4() {
		this.offset += 4;
		return ((this.data[this.offset - 4] & 0xFF) << 24) + ((this.data[this.offset - 3] & 0xFF) << 16) + ((this.data[this.offset - 2] & 0xFF) << 8) + (this.data[this.offset - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "e", descriptor = "(I)J")
	public final long g8() {
		@Pc(5) long high = (long) this.g4() & 0xFFFFFFFFL;
		@Pc(15) long low = (long) this.g4() & 0xFFFFFFFFL;
		return (high << 32) + low;
	}

	@OriginalMember(owner = "client!kb", name = "i", descriptor = "()Ljava/lang/String;")
	public final String gjstr() {
		@Pc(2) int start = this.offset;
		while (this.data[this.offset++] != 10) {
		}
		return new String(this.data, start, this.offset - start - 1);
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(B)[B")
	public final byte[] gjstrBytes() {
		@Pc(2) int start = this.offset;
		while (this.data[this.offset++] != 10) {
		}
		@Pc(29) byte[] bytes = new byte[this.offset - start - 1];
		for (@Pc(31) int i = start; i < this.offset - 1; i++) {
			bytes[i - start] = this.data[i];
		}
		return bytes;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(III[B)V")
	public final void gBytes(@OriginalArg(0) int length, @OriginalArg(2) int offset, @OriginalArg(3) byte[] dest) {
		for (@Pc(6) int i = offset; i < offset + length; i++) {
			dest[i] = this.data[this.offset++];
		}
	}

	@OriginalMember(owner = "client!kb", name = "f", descriptor = "(I)V")
	public final void accessBits() {
		this.bitOffset = this.offset * 8;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "(II)I")
	public final int gBit(@OriginalArg(1) int n) {
		@Pc(15) int byteOffset = this.bitOffset >> 3;
		@Pc(22) int msb = 8 - (this.bitOffset & 0x7);
		@Pc(24) int i = 0;
		this.bitOffset += n;
		while (n > msb) {
			i += (this.data[byteOffset++] & BITMASK[msb]) << n - msb;
			n -= msb;
			msb = 8;
		}
		if (n == msb) {
			i += this.data[byteOffset] & BITMASK[msb];
		} else {
			i += this.data[byteOffset] >> msb - n & BITMASK[n];
		}
		return i;
	}

	@OriginalMember(owner = "client!kb", name = "g", descriptor = "(I)V")
	public final void accessBytes(@OriginalArg(0) int obfuscator) {
		if (obfuscator != this.flowObfuscator4) {
			for (@Pc(5) int i = 1; i > 0; i++) {
			}
		}
		this.offset = (this.bitOffset + 7) / 8;
	}

	@OriginalMember(owner = "client!kb", name = "j", descriptor = "()I")
	public final int gSmart1or2s() {
		@Pc(7) int peek = this.data[this.offset] & 0xFF;
		return peek < 128 ? this.g1() - 64 : this.g2() - 49152;
	}

	@OriginalMember(owner = "client!kb", name = "k", descriptor = "()I")
	public final int gSmart1or2() {
		@Pc(7) int peek = this.data[this.offset] & 0xFF;
		return peek < 128 ? this.g1() : this.g2() - 32768;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(Ljava/math/BigInteger;Ljava/math/BigInteger;I)V")
	public final void encryptRsa(@OriginalArg(0) BigInteger mod, @OriginalArg(1) BigInteger exp, @OriginalArg(2) int obfuscator) {
		@Pc(2) int start = this.offset;
		this.offset = 0;
		@Pc(8) byte[] raw = new byte[start];
		this.gBytes(start, 0, raw);
		@Pc(19) BigInteger bigRaw = new BigInteger(raw);
		@Pc(24) BigInteger bigEnc = bigRaw.modPow(exp, mod);
		@Pc(27) byte[] enc = bigEnc.toByteArray();
		this.offset = 0;
		if (obfuscator == 24676) {
			this.p1(enc.length);
			this.pBytes(enc, enc.length);
		}
	}
}
