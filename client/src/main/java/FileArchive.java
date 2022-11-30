import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

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

	@OriginalMember(owner = "client!ub", name = "<init>", descriptor = "([BZ)V")
	public FileArchive(@OriginalArg(0) byte[] src) {
		this.parse(src);
	}

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "(Z[B)V")
	private void parse(@OriginalArg(1) byte[] src) {
		@Pc(7) Buffer buf = new Buffer(src);
		@Pc(10) int uncompressedLength = buf.g3();
		@Pc(13) int compressedLength = buf.g3();

		if (compressedLength == uncompressedLength) {
			this.data = src;
			this.isCompressedWhole = false;
		} else {
			@Pc(19) byte[] raw = new byte[uncompressedLength];
			BZip2InputStream.read(raw, uncompressedLength, src, compressedLength, 6);
			this.data = raw;
			buf = new Buffer(this.data);
			this.isCompressedWhole = true;
		}

		this.fileCount = buf.g2();
		this.fileHash = new int[this.fileCount];
		this.fileUnpackedSize = new int[this.fileCount];
		this.filePackedSize = new int[this.fileCount];
		this.fileOffset = new int[this.fileCount];

		@Pc(82) int offset = buf.pos + this.fileCount * 10;
		for (@Pc(84) int i = 0; i < this.fileCount; i++) {
			this.fileHash[i] = buf.g4();
			this.fileUnpackedSize[i] = buf.g3();
			this.filePackedSize[i] = buf.g3();
			this.fileOffset[i] = offset;
			offset += this.filePackedSize[i];
		}
	}

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "(Ljava/lang/String;[BB)[B")
	public byte[] read(@OriginalArg(0) String name, @OriginalArg(1) byte[] dst) {
		@Pc(3) int hash = 0;
		@Pc(6) String upper = name.toUpperCase();
		for (@Pc(8) int i = 0; i < name.length(); i++) {
			hash = hash * 61 + upper.charAt(i) - 32;
		}

		for (@Pc(27) int i = 0; i < this.fileCount; i++) {
			if (this.fileHash[i] == hash) {
				if (dst == null) {
					dst = new byte[this.fileUnpackedSize[i]];
				}

				if (this.isCompressedWhole) {
					System.arraycopy(this.data, this.fileOffset[i], dst, 0, this.fileUnpackedSize[i]);
				} else {
					BZip2InputStream.read(dst, this.fileUnpackedSize[i], this.data, this.filePackedSize[i], this.fileOffset[i]);
				}

				return dst;
			}
		}
		return null;
	}
}
