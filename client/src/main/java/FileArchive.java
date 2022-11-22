import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

@OriginalClass("client!ub")
public final class FileArchive {

	@OriginalMember(owner = "client!ub", name = "e", descriptor = "[B")
	private byte[] data;

	@OriginalMember(owner = "client!ub", name = "f", descriptor = "I")
	private int fileCount;

	@OriginalMember(owner = "client!ub", name = "g", descriptor = "[I")
	private int[] fileHash;

	@OriginalMember(owner = "client!ub", name = "h", descriptor = "[I")
	private int[] fileUnpackedSize;

	@OriginalMember(owner = "client!ub", name = "i", descriptor = "[I")
	private int[] filePackedSize;

	@OriginalMember(owner = "client!ub", name = "j", descriptor = "[I")
	private int[] fileOffset;

	@OriginalMember(owner = "client!ub", name = "k", descriptor = "Z")
	private boolean isCompressedWhole;

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "Z")
	private final boolean aBoolean148 = false;

	@OriginalMember(owner = "client!ub", name = "b", descriptor = "B")
	private final byte aByte39 = 2;

	@OriginalMember(owner = "client!ub", name = "c", descriptor = "I")
	private final int anInt762 = 40267;

	@OriginalMember(owner = "client!ub", name = "d", descriptor = "Z")
	private final boolean aBoolean149 = false;

	@OriginalMember(owner = "client!ub", name = "<init>", descriptor = "([BZ)V")
	public FileArchive(@OriginalArg(0) byte[] arg0, @OriginalArg(1) boolean arg1) {
		try {
			if (arg1) {
				for (@Pc(17) int local17 = 1; local17 > 0; local17++) {
				}
			}
			this.parse(true, arg0);
		} catch (@Pc(28) RuntimeException local28) {
			signlink.reporterror("94335, " + arg0 + ", " + arg1 + ", " + local28.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "(Z[B)V")
	private void parse(@OriginalArg(0) boolean arg0, @OriginalArg(1) byte[] arg1) {
		try {
			@Pc(7) Buffer local7 = new Buffer(363, arg1);
			@Pc(10) int local10 = local7.g3();
			@Pc(13) int local13 = local7.g3();
			if (local13 == local10) {
				this.data = arg1;
				this.isCompressedWhole = false;
			} else {
				@Pc(19) byte[] local19 = new byte[local10];
				BZip2InputStream.read(local19, local10, arg1, local13, 6);
				this.data = local19;
				local7 = new Buffer(363, this.data);
				this.isCompressedWhole = true;
			}
			this.fileCount = local7.g2();
			this.fileHash = new int[this.fileCount];
			this.fileUnpackedSize = new int[this.fileCount];
			this.filePackedSize = new int[this.fileCount];
			this.fileOffset = new int[this.fileCount];
			if (arg0) {
				@Pc(82) int local82 = local7.pos + this.fileCount * 10;
				for (@Pc(84) int local84 = 0; local84 < this.fileCount; local84++) {
					this.fileHash[local84] = local7.g4();
					this.fileUnpackedSize[local84] = local7.g3();
					this.filePackedSize[local84] = local7.g3();
					this.fileOffset[local84] = local82;
					local82 += this.filePackedSize[local84];
				}
			}
		} catch (@Pc(123) RuntimeException local123) {
			signlink.reporterror("40193, " + arg0 + ", " + arg1 + ", " + local123.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "(Ljava/lang/String;[BB)[B")
	public byte[] read(@OriginalArg(0) String arg0, @OriginalArg(1) byte[] arg1, @OriginalArg(2) byte arg2) {
		try {
			@Pc(3) int local3 = 0;
			@Pc(6) String local6 = arg0.toUpperCase();
			for (@Pc(8) int local8 = 0; local8 < local6.length(); local8++) {
				local3 = local3 * 61 + local6.charAt(local8) - 32;
			}
			for (@Pc(27) int local27 = 0; local27 < this.fileCount; local27++) {
				if (this.fileHash[local27] == local3) {
					if (arg1 == null) {
						arg1 = new byte[this.fileUnpackedSize[local27]];
					}
					if (this.isCompressedWhole) {
						for (@Pc(67) int local67 = 0; local67 < this.fileUnpackedSize[local27]; local67++) {
							arg1[local67] = this.data[this.fileOffset[local27] + local67];
						}
					} else {
						BZip2InputStream.read(arg1, this.fileUnpackedSize[local27], this.data, this.filePackedSize[local27], this.fileOffset[local27]);
					}
					return arg1;
				}
			}
			if (arg2 != this.aByte39) {
				throw new NullPointerException();
			}
			return null;
		} catch (@Pc(106) RuntimeException local106) {
			signlink.reporterror("98811, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + local106.toString());
			throw new RuntimeException();
		}
	}
}
