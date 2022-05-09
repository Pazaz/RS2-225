import java.math.BigInteger;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kb")
public final class Buffer extends CacheableNode {

	@OriginalMember(owner = "client!kb", name = "w", descriptor = "I")
	private static int anInt563;

	@OriginalMember(owner = "client!kb", name = "x", descriptor = "I")
	private static int anInt564;

	@OriginalMember(owner = "client!kb", name = "y", descriptor = "I")
	private static int anInt565;

	@OriginalMember(owner = "client!kb", name = "h", descriptor = "I")
	private static final int anInt556 = 40946;

	@OriginalMember(owner = "client!kb", name = "o", descriptor = "Z")
	private static boolean aBoolean129 = true;

	@OriginalMember(owner = "client!kb", name = "t", descriptor = "[I")
	private static final int[] anIntArray190 = new int[256];

	@OriginalMember(owner = "client!kb", name = "u", descriptor = "[I")
	private static final int[] anIntArray191;

	@OriginalMember(owner = "client!kb", name = "z", descriptor = "Lclient!ob;")
	private static final LinkedList A_LINKED_LIST___6;

	@OriginalMember(owner = "client!kb", name = "A", descriptor = "Lclient!ob;")
	private static final LinkedList A_LINKED_LIST___7;

	@OriginalMember(owner = "client!kb", name = "B", descriptor = "Lclient!ob;")
	private static final LinkedList A_LINKED_LIST___8;

	@OriginalMember(owner = "client!kb", name = "q", descriptor = "[B")
	public byte[] aByteArray7;

	@OriginalMember(owner = "client!kb", name = "r", descriptor = "I")
	public int anInt561;

	@OriginalMember(owner = "client!kb", name = "s", descriptor = "I")
	public int anInt562;

	@OriginalMember(owner = "client!kb", name = "v", descriptor = "Lclient!tb;")
	public IsaacRandom aIsaacRandom_2;

	@OriginalMember(owner = "client!kb", name = "i", descriptor = "B")
	private final byte aByte29 = -34;

	@OriginalMember(owner = "client!kb", name = "j", descriptor = "B")
	private final byte aByte30 = -106;

	@OriginalMember(owner = "client!kb", name = "k", descriptor = "I")
	private final int anInt557 = 3;

	@OriginalMember(owner = "client!kb", name = "l", descriptor = "I")
	private final int anInt558 = -506;

	@OriginalMember(owner = "client!kb", name = "m", descriptor = "Z")
	private final boolean aBoolean128 = true;

	@OriginalMember(owner = "client!kb", name = "n", descriptor = "I")
	private final int anInt559 = 4277;

	@OriginalMember(owner = "client!kb", name = "p", descriptor = "I")
	private final int anInt560 = -178;

	static {
		for (@Pc(8) int local8 = 0; local8 < 256; local8++) {
			@Pc(11) int local11 = local8;
			for (@Pc(13) int local13 = 0; local13 < 8; local13++) {
				if ((local11 & 0x1) == 1) {
					local11 = local11 >>> 1 ^ 0xEDB88320;
				} else {
					local11 >>>= 0x1;
				}
			}
			anIntArray190[local8] = local11;
		}
		anIntArray191 = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };
		A_LINKED_LIST___6 = new LinkedList(0);
		A_LINKED_LIST___7 = new LinkedList(0);
		A_LINKED_LIST___8 = new LinkedList(0);
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(II)Lclient!kb;")
	public static Buffer method378() {
		@Pc(3) LinkedList local3 = A_LINKED_LIST___7;
		synchronized (A_LINKED_LIST___7) {
			@Pc(7) Buffer local7 = null;
			if (anInt564 > 0) {
				anInt564--;
				local7 = (Buffer) A_LINKED_LIST___7.method455();
			}
			if (local7 != null) {
				local7.anInt561 = 0;
				return local7;
			}
		}
		@Pc(77) Buffer local77 = new Buffer(anInt556);
		local77.anInt561 = 0;
		local77.aByteArray7 = new byte[5000];
		return local77;
	}

	@OriginalMember(owner = "client!kb", name = "<init>", descriptor = "(I)V")
	private Buffer(@OriginalArg(0) int arg0) {
		if (arg0 != 40946) {
			aBoolean129 = !aBoolean129;
		}
	}

	@OriginalMember(owner = "client!kb", name = "<init>", descriptor = "(I[B)V")
	public Buffer(@OriginalArg(0) int arg0, @OriginalArg(1) byte[] arg1) {
		this.aByteArray7 = arg1;
		this.anInt561 = 0;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(B)V")
	public final void method379() {
		@Pc(1) LinkedList local1 = A_LINKED_LIST___7;
		synchronized (A_LINKED_LIST___7) {
			this.anInt561 = 0;
			if (this.aByteArray7.length == 100 && anInt563 < 1000) {
				A_LINKED_LIST___6.method453(this);
				anInt563++;
			} else if (this.aByteArray7.length == 5000 && anInt564 < 250) {
				A_LINKED_LIST___7.method453(this);
				anInt564++;
			} else if (this.aByteArray7.length == 30000 && anInt565 < 50) {
				A_LINKED_LIST___8.method453(this);
				anInt565++;
			}
		}
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(BI)V")
	public final void method380(@OriginalArg(1) int arg0) {
		this.aByteArray7[this.anInt561++] = (byte) (arg0 + this.aIsaacRandom_2.method532());
		if (this.aByte29 != -34) {
			aBoolean129 = !aBoolean129;
		}
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(I)V")
	public final void method381(@OriginalArg(0) int arg0) {
		this.aByteArray7[this.anInt561++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(I)V")
	public final void method382(@OriginalArg(0) int arg0) {
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 8);
		this.aByteArray7[this.anInt561++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(ZI)V")
	public final void method383(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1) {
		if (!arg0) {
			aBoolean129 = !aBoolean129;
		}
		this.aByteArray7[this.anInt561++] = (byte) arg1;
		this.aByteArray7[this.anInt561++] = 0;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "(I)V")
	public final void method384(@OriginalArg(0) int arg0) {
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 16);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 8);
		this.aByteArray7[this.anInt561++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!kb", name = "d", descriptor = "(I)V")
	public final void method385(@OriginalArg(0) int arg0) {
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 24);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 16);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 8);
		this.aByteArray7[this.anInt561++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(ZI)V")
	public final void method386(@OriginalArg(1) int arg0) {
		this.aByteArray7[this.anInt561++] = (byte) arg0;
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 8);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 16);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 24);
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(ZJ)V")
	public final void method387(@OriginalArg(1) long arg0) {
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 56);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 48);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 40);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 32);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 24);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 16);
		this.aByteArray7[this.anInt561++] = (byte) (arg0 >> 8);
		this.aByteArray7[this.anInt561++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(Ljava/lang/String;)V")
	public final void method388(@OriginalArg(0) String arg0) {
		arg0.getBytes(0, arg0.length(), this.aByteArray7, this.anInt561);
		this.anInt561 += arg0.length();
		this.aByteArray7[this.anInt561++] = 10;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "([BIIB)V")
	public final void method389(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1) {
		@Pc(7) int local7;
		if (this.aByte30 != -106) {
			for (local7 = 1; local7 > 0; local7++) {
			}
		}
		for (local7 = 0; local7 < arg1 + 0; local7++) {
			this.aByteArray7[this.anInt561++] = arg0[local7];
		}
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(II)V")
	public final void method390(@OriginalArg(1) int arg0) {
		this.aByteArray7[this.anInt561 - arg0 - 1] = (byte) arg0;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "()I")
	public final int method391() {
		return this.aByteArray7[this.anInt561++] & 0xFF;
	}

	@OriginalMember(owner = "client!kb", name = "d", descriptor = "()B")
	public final byte method392() {
		return this.aByteArray7[this.anInt561++];
	}

	@OriginalMember(owner = "client!kb", name = "e", descriptor = "()I")
	public final int method393() {
		this.anInt561 += 2;
		return ((this.aByteArray7[this.anInt561 - 2] & 0xFF) << 8) + (this.aByteArray7[this.anInt561 - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "f", descriptor = "()I")
	public final int method394() {
		this.anInt561 += 2;
		@Pc(27) int local27 = ((this.aByteArray7[this.anInt561 - 2] & 0xFF) << 8) + (this.aByteArray7[this.anInt561 - 1] & 0xFF);
		if (local27 > 32767) {
			local27 -= 65536;
		}
		return local27;
	}

	@OriginalMember(owner = "client!kb", name = "g", descriptor = "()I")
	public final int method395() {
		this.anInt561 += 3;
		return ((this.aByteArray7[this.anInt561 - 3] & 0xFF) << 16) + ((this.aByteArray7[this.anInt561 - 2] & 0xFF) << 8) + (this.aByteArray7[this.anInt561 - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "h", descriptor = "()I")
	public final int method396() {
		this.anInt561 += 4;
		return ((this.aByteArray7[this.anInt561 - 4] & 0xFF) << 24) + ((this.aByteArray7[this.anInt561 - 3] & 0xFF) << 16) + ((this.aByteArray7[this.anInt561 - 2] & 0xFF) << 8) + (this.aByteArray7[this.anInt561 - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!kb", name = "e", descriptor = "(I)J")
	public final long method397() {
		@Pc(5) long local5 = (long) this.method396() & 0xFFFFFFFFL;
		@Pc(15) long local15 = (long) this.method396() & 0xFFFFFFFFL;
		return (local5 << 32) + local15;
	}

	@OriginalMember(owner = "client!kb", name = "i", descriptor = "()Ljava/lang/String;")
	public final String method398() {
		@Pc(2) int local2 = this.anInt561;
		while (this.aByteArray7[this.anInt561++] != 10) {
		}
		return new String(this.aByteArray7, local2, this.anInt561 - local2 - 1);
	}

	@OriginalMember(owner = "client!kb", name = "b", descriptor = "(B)[B")
	public final byte[] method399() {
		@Pc(2) int local2 = this.anInt561;
		while (this.aByteArray7[this.anInt561++] != 10) {
		}
		@Pc(29) byte[] local29 = new byte[this.anInt561 - local2 - 1];
		for (@Pc(31) int local31 = local2; local31 < this.anInt561 - 1; local31++) {
			local29[local31 - local2] = this.aByteArray7[local31];
		}
		return local29;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(III[B)V")
	public final void method400(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) byte[] arg2) {
		for (@Pc(6) int local6 = arg1; local6 < arg1 + arg0; local6++) {
			arg2[local6] = this.aByteArray7[this.anInt561++];
		}
	}

	@OriginalMember(owner = "client!kb", name = "f", descriptor = "(I)V")
	public final void method401() {
		this.anInt562 = this.anInt561 * 8;
	}

	@OriginalMember(owner = "client!kb", name = "c", descriptor = "(II)I")
	public final int method402(@OriginalArg(1) int arg0) {
		@Pc(15) int local15 = this.anInt562 >> 3;
		@Pc(22) int local22 = 8 - (this.anInt562 & 0x7);
		@Pc(24) int local24 = 0;
		this.anInt562 += arg0;
		while (arg0 > local22) {
			local24 += (this.aByteArray7[local15++] & anIntArray191[local22]) << arg0 - local22;
			arg0 -= local22;
			local22 = 8;
		}
		if (arg0 == local22) {
			local24 += this.aByteArray7[local15] & anIntArray191[local22];
		} else {
			local24 += this.aByteArray7[local15] >> local22 - arg0 & anIntArray191[arg0];
		}
		return local24;
	}

	@OriginalMember(owner = "client!kb", name = "g", descriptor = "(I)V")
	public final void method403(@OriginalArg(0) int arg0) {
		if (arg0 != this.anInt559) {
			for (@Pc(5) int local5 = 1; local5 > 0; local5++) {
			}
		}
		this.anInt561 = (this.anInt562 + 7) / 8;
	}

	@OriginalMember(owner = "client!kb", name = "j", descriptor = "()I")
	public final int method404() {
		@Pc(7) int local7 = this.aByteArray7[this.anInt561] & 0xFF;
		return local7 < 128 ? this.method391() - 64 : this.method393() - 49152;
	}

	@OriginalMember(owner = "client!kb", name = "k", descriptor = "()I")
	public final int method405() {
		@Pc(7) int local7 = this.aByteArray7[this.anInt561] & 0xFF;
		return local7 < 128 ? this.method391() : this.method393() - 32768;
	}

	@OriginalMember(owner = "client!kb", name = "a", descriptor = "(Ljava/math/BigInteger;Ljava/math/BigInteger;I)V")
	public final void method406(@OriginalArg(0) BigInteger arg0, @OriginalArg(1) BigInteger arg1, @OriginalArg(2) int arg2) {
		@Pc(2) int local2 = this.anInt561;
		this.anInt561 = 0;
		@Pc(8) byte[] local8 = new byte[local2];
		this.method400(local2, 0, local8);
		@Pc(19) BigInteger local19 = new BigInteger(local8);
		@Pc(24) BigInteger local24 = local19.modPow(arg1, arg0);
		@Pc(27) byte[] local27 = local24.toByteArray();
		this.anInt561 = 0;
		if (arg2 == 24676) {
			this.method381(local27.length);
			this.method389(local27, local27.length);
		}
	}
}
