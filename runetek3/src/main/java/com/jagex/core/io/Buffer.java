package com.jagex.core.io;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.zip.CRC32;

import com.jagex.core.utils.CacheableNode;
import com.jagex.core.isaac.IsaacRandom;
import com.jagex.core.utils.LinkedList;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kb")
public class Buffer extends CacheableNode {

	@OriginalMember(owner = "client!kb", name = "w", descriptor = "I")
	private static int queueMinCount;

	@OriginalMember(owner = "client!kb", name = "x", descriptor = "I")
	private static int queueMidCount;

	@OriginalMember(owner = "client!kb", name = "y", descriptor = "I")
	private static int queueMaxCount;

	@OriginalMember(owner = "client!kb", name = "t", descriptor = "[I")
	private static final int[] crctable = new int[256];

	private static final int CRC32_POLYNOMIAL = 0xEDB88320;

	@OriginalMember(owner = "client!kb", name = "u", descriptor = "[I")
	private static final int[] BITMASK = new int[] {
		0, 1, 3, 7, 0xf, 0x1f, 0x3f, 0x7f, 0xff,
		0x1ff, 0x3ff, 0x7ff, 0xfff, 0x1fff, 0x3fff, 0x7fff, 0xffff,
		0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff,
		0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, 0xffffffff
	};

	@OriginalMember(owner = "client!kb", name = "z", descriptor = "Lclient!ob;")
	private static final LinkedList queueMin = new LinkedList();

	@OriginalMember(owner = "client!kb", name = "A", descriptor = "Lclient!ob;")
	private static final LinkedList queueMid = new LinkedList();

	@OriginalMember(owner = "client!kb", name = "B", descriptor = "Lclient!ob;")
	private static final LinkedList queueMax = new LinkedList();

	@OriginalMember(owner = "client!kb", name = "q", descriptor = "[B")
	public byte[] data;

	@OriginalMember(owner = "client!kb", name = "r", descriptor = "I")
	public int pos;

	@OriginalMember(owner = "client!kb", name = "s", descriptor = "I")
	public int bitOffset;

	@OriginalMember(owner = "client!kb", name = "v", descriptor = "Lclient!tb;")
	public IsaacRandom isaac;

	public int length;

	private static final CRC32 crc = new CRC32();

	static {
		for (@Pc(8) int i = 0; i < 256; i++) {
			@Pc(11) int temp = i;
			for (@Pc(13) int j = 0; j < 8; j++) {
				if ((temp & 1) == 1) {
					temp = temp >>> 1 ^ CRC32_POLYNOMIAL;
				} else {
					temp >>>= 1;
				}
			}
			crctable[i] = temp;
		}
	}

	public static int crc32(byte[] data) {
		crc.reset();
		crc.update(data, 0, data.length);
		return (int) crc.getValue();
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(II)Lclient!kb;")
	public static Buffer reserve(int type) {
		synchronized (queueMid) {
			@Pc(7) Buffer queueBuffer = null;
			if (type == 0 && queueMinCount > 0) {
				queueMinCount--;
				queueBuffer = (Buffer) queueMin.poll();
			} else if (type == 1 && queueMidCount > 0) {
				queueMidCount--;
				queueBuffer = (Buffer) queueMid.poll();
			} else if (type == 2 && queueMaxCount > 0) {
				queueMaxCount--;
				queueBuffer = (Buffer) queueMax.poll();
			}

			if (queueBuffer != null) {
				queueBuffer.pos = 0;
				return queueBuffer;
			}
		}

		@Pc(77) Buffer buffer = new Buffer();
		buffer.pos = 0;
		if (type == 0) {
			buffer.data = new byte[1000];
		} else if (type == 1) {
			buffer.data = new byte[5000];
		} else {
			buffer.data = new byte[30000];
		}
		buffer.length = buffer.data.length;
		return buffer;
	}

	@OriginalMember(owner = "client!kb", name = "<init>", descriptor = "(I)V")
	private Buffer() {
	}

	@OriginalMember(owner = "client!kb", name = "<init>", descriptor = "(I[B)V")
	public Buffer(@OriginalArg(1) byte[] src) {
		this.data = src;
		this.length = src.length;
		this.pos = 0;
	}

	public int available() {
		return this.length - this.pos;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(B)V")
	public void release() {
		synchronized (queueMid) {
			this.pos = 0;
			if (this.length == 100 && queueMinCount < 1000) {
				queueMin.pushNext(this);
				queueMinCount++;
			} else if (this.length == 5000 && queueMidCount < 250) {
				queueMid.pushNext(this);
				queueMidCount++;
			} else if (this.length == 30000 && queueMaxCount < 50) {
				queueMax.pushNext(this);
				queueMaxCount++;
			}
		}
	}

	// returns the current buffer array up to the offset, and resets the offset
	public byte[] take() {
		int size = this.pos;
		this.pos = 0;
		return Arrays.copyOfRange(this.data, 0, size);
	}

	public Buffer slice(int offset, int length) {
		return new Buffer(Arrays.copyOfRange(this.data, offset, offset + length));
	}

	public byte[] sliceBytes(int offset, int length) {
		return Arrays.copyOfRange(this.data, offset, offset + length);
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(BI)V")
	public void p1isaac(@OriginalArg(1) int opcode) {
		this.data[this.pos++] = (byte) (opcode + this.isaac.nextInt());
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(I)V")
	public void p1(@OriginalArg(0) int value) {
		this.data[this.pos++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(I)V")
	public void p2(@OriginalArg(0) int value) {
		this.data[this.pos++] = (byte) (value >> 8);
		this.data[this.pos++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(ZI)V")
	public void ip2(@OriginalArg(1) int value) {
		this.data[this.pos++] = (byte) value;
		this.data[this.pos++] = (byte) (value >> 8);
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "(I)V")
	public void p3(@OriginalArg(0) int value) {
		this.data[this.pos++] = (byte) (value >> 16);
		this.data[this.pos++] = (byte) (value >> 8);
		this.data[this.pos++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "d", descriptor = "(I)V")
	public void p4(@OriginalArg(0) int value) {
		this.data[this.pos++] = (byte) (value >> 24);
		this.data[this.pos++] = (byte) (value >> 16);
		this.data[this.pos++] = (byte) (value >> 8);
		this.data[this.pos++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(ZI)V")
	public void ip4(@OriginalArg(1) int value) {
		this.data[this.pos++] = (byte) value;
		this.data[this.pos++] = (byte) (value >> 8);
		this.data[this.pos++] = (byte) (value >> 16);
		this.data[this.pos++] = (byte) (value >> 24);
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(ZJ)V")
	public void p8(@OriginalArg(1) long value) {
		this.data[this.pos++] = (byte) (value >> 56);
		this.data[this.pos++] = (byte) (value >> 48);
		this.data[this.pos++] = (byte) (value >> 40);
		this.data[this.pos++] = (byte) (value >> 32);
		this.data[this.pos++] = (byte) (value >> 24);
		this.data[this.pos++] = (byte) (value >> 16);
		this.data[this.pos++] = (byte) (value >> 8);
		this.data[this.pos++] = (byte) value;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(Ljava/lang/String;)V")
	public void pjstr(@OriginalArg(0) String str) {
		str.getBytes(0, str.length(), this.data, this.pos);
		this.pos += str.length();
		this.data[this.pos++] = 10;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "([BIIB)V")
	public void pdata(@OriginalArg(0) byte[] bytes, int offset, @OriginalArg(1) int length) {
		for (@Pc(7) int i = offset; i < offset + length; i++) {
			this.data[this.pos++] = bytes[i];
		}
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(II)V")
	public void psize1(@OriginalArg(1) int length) {
		this.data[this.pos - length - 1] = (byte) length;
	}

	public void psize2(int length) {
		this.data[this.pos - length - 2] = (byte) (length >> 8);
		this.data[this.pos - length - 1] = (byte) length;
	}

	public void psize4(int length) {
		this.data[this.pos - length - 4] = (byte) (length >> 24);
		this.data[this.pos - length - 3] = (byte) (length >> 16);
		this.data[this.pos - length - 2] = (byte) (length >> 8);
		this.data[this.pos - length - 1] = (byte) length;
	}

	public int g1isaac() {
		return (this.data[this.pos++] - this.isaac.nextInt()) & 0xFF;
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
		@Pc(27) int value = ((this.data[this.pos - 2] & 0xFF) << 8) + (this.data[this.pos - 1] & 0xFF);
		if (value > 32767) {
			value -= 65536;
		}
		return value;
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

	public String fastgstr() {
		if (data[pos] == 10) {
			pos++;
			return null;
		} else {
			return gstr();
		}
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
		@Pc(29) byte[] bytes = new byte[this.pos - start - 1];
		for (@Pc(31) int i = start; i < this.pos - 1; i++) {
			bytes[i - start] = this.data[i];
		}
		return bytes;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(III[B)V")
	public void gdata(@OriginalArg(3) byte[] dest, @OriginalArg(2) int offset, @OriginalArg(0) int length) {
		for (@Pc(6) int i = offset; i < offset + length && i < dest.length; i++) {
			dest[i] = this.data[this.pos++];
		}
	}

	@OriginalMember(owner = "client!kb", name = "f", descriptor = "(I)V")
	public void accessBits() {
		this.bitOffset = this.pos * 8;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "(II)I")
	public int gBit(@OriginalArg(1) int n) {
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

	public void pBit(int n, int value) {
		int byteOffset = this.bitOffset >> 3;
		int remaining = 8 - (this.bitOffset & 0x7);
		this.bitOffset += n;

		for (; n > remaining; remaining = 8) {
			this.data[byteOffset] &= ~BITMASK[remaining];
			this.data[byteOffset++] |= (value >>> (n - remaining)) & BITMASK[remaining];
			n -= remaining;
		}

		if (n == remaining) {
			this.data[byteOffset] &= ~BITMASK[remaining];
			this.data[byteOffset] |= value & BITMASK[remaining];
		} else {
			this.data[byteOffset] &= ~BITMASK[n] << (remaining - n);
			this.data[byteOffset] |= (value & BITMASK[n]) << (remaining - n);
		}
	}

	@OriginalMember(owner = "client!kb", name = "g", descriptor = "(I)V")
	public void accessBytes() {
		this.pos = (this.bitOffset + 7) / 8;
	}

	@OriginalMember(owner = "client!kb", name = "j", descriptor = "()I")
	public int gsmart() {
		@Pc(7) int peek = this.data[this.pos] & 0xFF;
		return peek < 128 ? this.g1() - 64 : this.g2() - 0xc000;
	}

	@OriginalMember(owner = "client!kb", name = "k", descriptor = "()I")
	public int gsmarts() {
		@Pc(7) int peek = this.data[this.pos] & 0xFF;
		return peek < 128 ? this.g1() : this.g2() - 0x8000;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(Ljava/math/BigInteger;Ljava/math/BigInteger;I)V")
	public void rsaenc(@OriginalArg(0) BigInteger mod, @OriginalArg(1) BigInteger exp) {
		@Pc(2) int start = this.pos;
		this.pos = 0;
		@Pc(8) byte[] raw = new byte[start];
		this.gdata(raw, 0, start);
		@Pc(19) BigInteger bigRaw = new BigInteger(raw);
		@Pc(24) BigInteger bigEnc = bigRaw.modPow(exp, mod);
		@Pc(27) byte[] enc = bigEnc.toByteArray();
		this.pos = 0;
		this.p1(enc.length);
		this.pdata(enc, 0, enc.length);
	}

	public void rsadec(BigInteger mod, BigInteger exp) {
		int length = this.g1();
		byte[] enc = new byte[length];
		this.gdata(enc, 0, length);
		BigInteger bigEnc = new BigInteger(enc);
		BigInteger bigRaw = bigEnc.modPow(exp, mod);
		byte[] dec = bigRaw.toByteArray();
		this.pos = 0;
		pdata(dec, 0, dec.length);
		this.pos = 0; // reset afterwards to read the new data
	}

	public int addcrc() {
		int crc = -1;
		for (int j = 0; j < pos; j++) {
			crc = crc >>> 8 ^ crctable[(crc ^ data[j]) & 0xff];
		}

		crc = ~crc;
		p4(crc);
		return crc;
	}

	@OriginalMember(owner = "client!kb", name = "h", descriptor = "I")
	private static final int flowObfuscator5 = 40946;

	@OriginalMember(owner = "client!kb", name = "o", descriptor = "Z")
	private static final boolean flowObfuscator1 = true;

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

}
