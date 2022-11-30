import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.math.BigInteger;

@OriginalClass("client!kb")
public final class Buffer extends CacheableNode {

	@OriginalMember(owner = "client!kb", name = "u", descriptor = "[I")
	public static final int[] BITMASK = new int[] { 0, 0x1, 0x3, 0x7, 0xf, 0x1f, 0x3f, 0x7f, 0xff, 0x1ff, 0x3ff, 0x7ff, 0xfff, 0x1fff, 0x3fff, 0x7fff, 0xffff, 0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, 0xffffffff};

	@OriginalMember(owner = "client!kb", name = "z", descriptor = "Lclient!ob;")
	public static final LinkedList queueMin = new LinkedList(0);

	@OriginalMember(owner = "client!kb", name = "A", descriptor = "Lclient!ob;")
	public static final LinkedList queueMid = new LinkedList(0);

	@OriginalMember(owner = "client!kb", name = "B", descriptor = "Lclient!ob;")
	public static final LinkedList queueMax = new LinkedList(0);

	@OriginalMember(owner = "client!kb", name = "t", descriptor = "[I")
	private static final int[] crctable = new int[256];

	@OriginalMember(owner = "client!kb", name = "w", descriptor = "I")
	public static int queueMinCount;

	@OriginalMember(owner = "client!kb", name = "x", descriptor = "I")
	public static int queueMidCount;

	@OriginalMember(owner = "client!kb", name = "y", descriptor = "I")
	public static int queueMaxCount;

	@OriginalMember(owner = "client!kb", name = "q", descriptor = "[B")
	public byte[] data;

	@OriginalMember(owner = "client!kb", name = "r", descriptor = "I")
	public int pos;

	@OriginalMember(owner = "client!kb", name = "s", descriptor = "I")
	public int bitOffset;

	@OriginalMember(owner = "client!kb", name = "v", descriptor = "Lclient!tb;")
	public IsaacRandom isaac;

	static {
		for (@Pc(8) int j = 0; j < 256; j++) {
			@Pc(11) int i = j;
			for (@Pc(13) int k = 0; k < 8; k++) {
				if ((i & 0x1) == 1) {
					i = i >>> 1 ^ 0xEDB88320;
				} else {
					i >>>= 0x1;
				}
			}
			crctable[j] = i;
		}
	}

	@OriginalMember(owner = "client!kb", name = "<init>", descriptor = "(I)V")
	public Buffer() {
	}

	@OriginalMember(owner = "client!kb", name = "<init>", descriptor = "(I[B)V")
	public Buffer(@OriginalArg(1) byte[] src) {
		this.data = src;
		this.pos = 0;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(II)Lclient!kb;")
	public static Buffer reserve(@OriginalArg(0) int type) {
		synchronized (queueMid) {
			@Pc(7) Buffer buf = null;
			if (type == 0 && queueMinCount > 0) {
				queueMinCount--;
				buf = (Buffer) queueMin.poll();
			} else if (type == 1 && queueMidCount > 0) {
				queueMidCount--;
				buf = (Buffer) queueMid.poll();
			} else if (type == 2 && queueMaxCount > 0) {
				queueMaxCount--;
				buf = (Buffer) queueMax.poll();
			}

			if (buf != null) {
				buf.pos = 0;
				return buf;
			}
		}

		@Pc(77) Buffer buf = new Buffer();
		buf.pos = 0;
		if (type == 0) {
			buf.data = new byte[100];
		} else if (type == 1) {
			buf.data = new byte[5000];
		} else {
			buf.data = new byte[30000];
		}
		return buf;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(B)V")
	public void release() {
		synchronized (queueMid) {
			this.pos = 0;
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
	public void p1isaac(@OriginalArg(1) int op) {
		this.data[this.pos++] = (byte) (op + this.isaac.nextInt());
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(I)V")
	public void p1(@OriginalArg(0) int val) {
		this.data[this.pos++] = (byte) val;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(I)V")
	public void p2(@OriginalArg(0) int val) {
		this.data[this.pos++] = (byte) (val >> 8);
		this.data[this.pos++] = (byte) val;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(ZI)V")
	public void ip2(@OriginalArg(1) int val) {
		this.data[this.pos++] = (byte) val;
		this.data[this.pos++] = (byte) (val >> 8);
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "(I)V")
	public void p3(@OriginalArg(0) int val) {
		this.data[this.pos++] = (byte) (val >> 16);
		this.data[this.pos++] = (byte) (val >> 8);
		this.data[this.pos++] = (byte) val;
	}

	@OriginalMember(owner = "client!kb", name = "d", descriptor = "(I)V")
	public void p4(@OriginalArg(0) int val) {
		this.data[this.pos++] = (byte) (val >> 24);
		this.data[this.pos++] = (byte) (val >> 16);
		this.data[this.pos++] = (byte) (val >> 8);
		this.data[this.pos++] = (byte) val;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(ZI)V")
	public void ip4(@OriginalArg(1) int val) {
		this.data[this.pos++] = (byte) val;
		this.data[this.pos++] = (byte) (val >> 8);
		this.data[this.pos++] = (byte) (val >> 16);
		this.data[this.pos++] = (byte) (val >> 24);
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(ZJ)V")
	public void p8(@OriginalArg(1) long val) {
		this.data[this.pos++] = (byte) (val >> 56);
		this.data[this.pos++] = (byte) (val >> 48);
		this.data[this.pos++] = (byte) (val >> 40);
		this.data[this.pos++] = (byte) (val >> 32);
		this.data[this.pos++] = (byte) (val >> 24);
		this.data[this.pos++] = (byte) (val >> 16);
		this.data[this.pos++] = (byte) (val >> 8);
		this.data[this.pos++] = (byte) val;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(Ljava/lang/String;)V")
	public void pjstr(@OriginalArg(0) String str) {
		System.arraycopy(str.getBytes(), 0, this.data, this.pos, str.length());
		this.pos += str.length();
		this.data[this.pos++] = 10;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "([BIIB)V")
	public void pdata(@OriginalArg(0) byte[] src, @OriginalArg(1) int len, @OriginalArg(2) int off) {
		System.arraycopy(src, off, this.data, this.pos, len);
		this.pos += len;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(II)V")
	public void psize1(@OriginalArg(1) int len) {
		this.data[this.pos - len - 1] = (byte) len;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "()I")
	public int g1() {
		return this.data[this.pos++] & 0xFF;
	}

	@OriginalMember(owner = "client!kb", name = "d", descriptor = "()B")
	public byte g1b() {
		return this.data[this.pos++];
	}

	@OriginalMember(owner = "client!kb", name = "e", descriptor = "()I")
	public int g2() {
		this.pos += 2;
		return ((this.data[this.pos - 2] & 0xFF) << 8) + (this.data[this.pos - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "f", descriptor = "()I")
	public int g2b() {
		this.pos += 2;
		@Pc(27) int val = ((this.data[this.pos - 2] & 0xFF) << 8) + (this.data[this.pos - 1] & 0xFF);
		if (val > 32767) {
			val -= 65536;
		}
		return val;
	}

	@OriginalMember(owner = "client!kb", name = "g", descriptor = "()I")
	public int g3() {
		this.pos += 3;
		return ((this.data[this.pos - 3] & 0xFF) << 16) + ((this.data[this.pos - 2] & 0xFF) << 8) + (this.data[this.pos - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "h", descriptor = "()I")
	public int g4() {
		this.pos += 4;
		return ((this.data[this.pos - 4] & 0xFF) << 24) + ((this.data[this.pos - 3] & 0xFF) << 16) + ((this.data[this.pos - 2] & 0xFF) << 8) + (this.data[this.pos - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "e", descriptor = "(I)J")
	public long g8() {
		@Pc(5) long high = (long) this.g4() & 0xFFFFFFFFL;
		@Pc(15) long low = (long) this.g4() & 0xFFFFFFFFL;
		return (high << 32) + low;
	}

	@OriginalMember(owner = "client!kb", name = "i", descriptor = "()Ljava/lang/String;")
	public String gstr() {
		@Pc(2) int start = this.pos;
		while (this.data[this.pos++] != 10) ;
		return new String(this.data, start, this.pos - start - 1);
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(B)[B")
	public byte[] gstrbyte() {
		@Pc(2) int start = this.pos;
		while (this.data[this.pos++] != 10) ;
		@Pc(29) byte[] raw = new byte[this.pos - start - 1];
		System.arraycopy(this.data, start, raw, 0, this.pos - 1 - start);
		return raw;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(III[B)V")
	public void gdata(@OriginalArg(0) int len, @OriginalArg(2) int off, @OriginalArg(3) byte[] dst) {
		System.arraycopy(this.data, this.pos, dst, off, len);
		this.pos += len;
	}

	@OriginalMember(owner = "client!kb", name = "f", descriptor = "(I)V")
	public void accessBits() {
		this.bitOffset = this.pos * 8;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "(II)I")
	public int gBit(@OriginalArg(1) int n) {
		@Pc(15) int byteOffset = this.bitOffset >> 3;
		@Pc(22) int msb = 8 - (this.bitOffset & 0x7);
		@Pc(24) int val = 0;
		this.bitOffset += n;

		while (n > msb) {
			val += (this.data[byteOffset++] & BITMASK[msb]) << n - msb;
			n -= msb;
			msb = 8;
		}

		if (n == msb) {
			val += this.data[byteOffset] & BITMASK[msb];
		} else {
			val += this.data[byteOffset] >> msb - n & BITMASK[n];
		}
		return val;
	}

	@OriginalMember(owner = "client!kb", name = "g", descriptor = "(I)V")
	public void accessBytes() {
		this.pos = (this.bitOffset + 7) / 8;
	}

	@OriginalMember(owner = "client!kb", name = "j", descriptor = "()I")
	public int gsmart() {
		@Pc(7) int val = this.data[this.pos] & 0xFF;
		return val < 128 ? this.g1() - 64 : this.g2() - 0xc000;
	}

	@OriginalMember(owner = "client!kb", name = "k", descriptor = "()I")
	public int gsmarts() {
		@Pc(7) int val = this.data[this.pos] & 0xFF;
		return val < 128 ? this.g1() : this.g2() - 0x8000;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(Ljava/math/BigInteger;Ljava/math/BigInteger;I)V")
	public void rsaenc(@OriginalArg(0) BigInteger mod, @OriginalArg(1) BigInteger exp) {
		@Pc(2) int len = this.pos;
		this.pos = 0;

		@Pc(8) byte[] raw = new byte[len];
		this.gdata(len, 0, raw);
		@Pc(19) BigInteger dec = new BigInteger(raw);
		@Pc(24) BigInteger enc = dec.modPow(exp, mod);
		@Pc(27) byte[] encRaw = enc.toByteArray();

		this.pos = 0;
		this.p1(encRaw.length);
		this.pdata(encRaw, encRaw.length, 0);
	}
}
