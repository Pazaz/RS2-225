package com.jagex.runetek3.cache;

import com.jagex.runetek3.util.BZip2InputStream;
import com.jagex.runetek3.util.Buffer;

public class FileArchive {

    public FileArchive(byte[] src) {
        parse(src);
    }

    public void parse(byte[] src) {
        Buffer buffer = new Buffer(src);

        int unpackedSize = buffer.readSWord();
        int packedSize = buffer.readSWord();

        if (packedSize != unpackedSize) {
            byte[] temp = new byte[unpackedSize];
            BZip2InputStream.read(temp, unpackedSize, src, packedSize, 6);
            data = temp;
            buffer = new Buffer(data);
            isCompressedWhole = true;
        } else {
            data = src;
            isCompressedWhole = false;
        }

        fileCount = buffer.readWord();
        fileHash = new int[fileCount];
        fileUnpackedSize = new int[fileCount];
        filePackedSize = new int[fileCount];
        fileOffset = new int[fileCount];

        int offset = buffer.offset + fileCount * 10;
        for (int l = 0; l < fileCount; l++) {
            fileHash[l] = buffer.readDWord();
            fileUnpackedSize[l] = buffer.readSWord();
            filePackedSize[l] = buffer.readSWord();
            fileOffset[l] = offset;
            offset += filePackedSize[l];
        }
    }

    public byte[] read(String name, byte[] dest) {
        int hash = 0;
        name = name.toUpperCase();
        for (int j = 0; j < name.length(); j++) {
            hash = (hash * 61 + name.charAt(j)) - 32;
        }

        for (int k = 0; k < fileCount; k++) {
            if (fileHash[k] == hash) {
                if (dest == null) {
                    dest = new byte[fileUnpackedSize[k]];
                }

                if (!isCompressedWhole) {
                    BZip2InputStream.read(dest, fileUnpackedSize[k], data, filePackedSize[k], fileOffset[k]);
                } else {
                    System.arraycopy(data, fileOffset[k] + 0, dest, 0, fileUnpackedSize[k]);
                }

                return dest;
            }
        }

        return null;
    }

    public byte[] data;
    public int fileCount;
    public int[] fileHash;
    public int[] fileUnpackedSize;
    public int[] filePackedSize;
    public int[] fileOffset;
    public boolean isCompressedWhole;
}
