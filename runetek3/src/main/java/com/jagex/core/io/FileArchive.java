package com.jagex.core.io;

import com.jagex.core.bzip2.BZip2InputStream;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ub")
public class FileArchive {

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
		@Pc(7) Buffer buffer = new Buffer(src);
		@Pc(10) int unpackedSize = buffer.g3();
		@Pc(13) int packedSize = buffer.g3();
		if (packedSize == unpackedSize) {
			this.data = src;
			this.isCompressedWhole = false;
		} else {
			@Pc(19) byte[] data = new byte[unpackedSize];
			BZip2InputStream.read(data, unpackedSize, src, packedSize, 6);
			this.data = data;
			buffer = new Buffer(this.data);
			this.isCompressedWhole = true;
		}
		this.fileCount = buffer.g2();
		this.fileHash = new int[this.fileCount];
		this.fileUnpackedSize = new int[this.fileCount];
		this.filePackedSize = new int[this.fileCount];
		this.fileOffset = new int[this.fileCount];
		@Pc(82) int offset = buffer.pos + this.fileCount * 10;
		for (@Pc(84) int i = 0; i < this.fileCount; i++) {
			this.fileHash[i] = buffer.g4();
			this.fileUnpackedSize[i] = buffer.g3();
			this.filePackedSize[i] = buffer.g3();
			this.fileOffset[i] = offset;
			offset += this.filePackedSize[i];
		}
	}

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "(Ljava/lang/String;[BB)[B")
	public byte[] read(@OriginalArg(0) String name, @OriginalArg(1) byte[] dest) {
		@Pc(3) int hash = 0;
		@Pc(6) String upperName = name.toUpperCase();
		for (@Pc(8) int i = 0; i < upperName.length(); i++) {
			hash = hash * 61 + upperName.charAt(i) - 32;
		}
		for (@Pc(27) int i = 0; i < this.fileCount; i++) {
			if (this.fileHash[i] == hash) {
				if (dest == null) {
					dest = new byte[this.fileUnpackedSize[i]];
				}
				if (this.isCompressedWhole) {
					System.arraycopy(this.data, this.fileOffset[i], dest, 0, this.fileUnpackedSize[i]);
				} else {
					BZip2InputStream.read(dest, this.fileUnpackedSize[i], this.data, this.filePackedSize[i], this.fileOffset[i]);
				}
				return dest;
			}
		}
		return null;
	}

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "Z")
	private final boolean flowObfuscator2 = false;

	@OriginalMember(owner = "client!ub", name = "b", descriptor = "B")
	private final byte flowObfuscator1 = 2;

	@OriginalMember(owner = "client!ub", name = "c", descriptor = "I")
	private final int flowObfuscator3 = 40267;

	@OriginalMember(owner = "client!ub", name = "d", descriptor = "Z")
	private final boolean flowObfuscator4 = false;

}
