package com.runescape.util;

import java.math.BigInteger;

import net.burtleburtle.bob.rand.IsaacRandom;

public class Buffer extends CacheableNode {

    public static Buffer reserve(int type) {
        synchronized (queueMid) {
            Buffer buffer = null;
            if (type == 0 && queueLowCount > 0) {
                queueLowCount--;
                buffer = (Buffer) queueLow.poll();
            } else if (type == 1 && queueMidCount > 0) {
                queueMidCount--;
                buffer = (Buffer) queueMid.poll();
            } else if (type == 2 && queueHighCount > 0) {
                queueHighCount--;
                buffer = (Buffer) queueHigh.poll();
            }

            if (buffer != null) {
                buffer.offset = 0;
                return buffer;
            }
        }

        Buffer buffer = new Buffer();
        buffer.offset = 0;
        if (type == 0) {
            buffer.data = new byte[100];
        } else if (type == 1) {
            buffer.data = new byte[5000];
        } else {
            buffer.data = new byte[30000];
        }

        return buffer;
    }

    public void release() {
        synchronized (queueMid) {
            offset = 0;
            if (data.length == 100 && queueLowCount < 1000) {
                queueLow.pushNext(this);
                queueLowCount++;
            } if (data.length == 5000 && queueMidCount < 250) {
                queueMid.pushNext(this);
                queueMidCount++;
            } if (data.length == 30000 && queueHighCount < 50) {
                queueHigh.pushNext(this);
                queueHighCount++;
            }
        }
    }

    public Buffer() {
    }

    public Buffer(byte[] src) {
        data = src;
        offset = 0;
    }

    public void writeOpcode(int opcode) {
        data[offset++] = (byte) (opcode + isaacState.nextInt());
    }

    public void writeByte(int value) {
        data[offset++] = (byte) value;
    }

    public void writeWord(int value) {
        data[offset++] = (byte) (value >> 8);
        data[offset++] = (byte) value;
    }

    public void writeWordLE(int value) {
        data[offset++] = (byte) value;
        data[offset++] = (byte) (value >> 8);
    }

    public void writeSWord(int value) {
        data[offset++] = (byte) (value >> 16);
        data[offset++] = (byte) (value >> 8);
        data[offset++] = (byte) value;
    }

    public void writeDWord(int value) {
        data[offset++] = (byte) (value >> 24);
        data[offset++] = (byte) (value >> 16);
        data[offset++] = (byte) (value >> 8);
        data[offset++] = (byte) value;
    }

    public void writeDWordLE(int value) {
        data[offset++] = (byte) value;
        data[offset++] = (byte) (value >> 8);
        data[offset++] = (byte) (value >> 16);
        data[offset++] = (byte) (value >> 24);
    }

    public void writeQWord(long value) {
        data[offset++] = (byte) (int) (value >> 56);
        data[offset++] = (byte) (int) (value >> 48);
        data[offset++] = (byte) (int) (value >> 40);
        data[offset++] = (byte) (int) (value >> 32);
        data[offset++] = (byte) (int) (value >> 24);
        data[offset++] = (byte) (int) (value >> 16);
        data[offset++] = (byte) (int) (value >> 8);
        data[offset++] = (byte) (int) value;
    }

    public void writeString(String str) {
        System.arraycopy(str.getBytes(), 0, data, offset, str.length());
        offset += str.length();
        data[offset++] = 10;
    }

    public void writeBytes(byte[] src, int srcLen, int srcOff) {
        for (int l = srcOff; l < srcOff + srcLen; l++) {
            data[offset++] = src[l];
        }
    }

    public void writeSize(int size) {
        data[offset - size - 1] = (byte) size;
    }

    public int readByte() {
        return data[offset++] & 0xff;
    }

    public byte readByteSigned() {
        return data[offset++];
    }

    public int readWord() {
        offset += 2;
        return ((data[offset - 2] & 0xff) << 8) + (data[offset - 1] & 0xff);
    }

    public int readWordSigned() {
        offset += 2;
        int i = ((data[offset - 2] & 0xff) << 8) + (data[offset - 1] & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int readSWord() {
        offset += 3;
        return ((data[offset - 3] & 0xff) << 16) + ((data[offset - 2] & 0xff) << 8) + (data[offset - 1] & 0xff);
    }

    public int readDWord() {
        offset += 4;
        return ((data[offset - 4] & 0xff) << 24) + ((data[offset - 3] & 0xff) << 16) + ((data[offset - 2] & 0xff) << 8) + (data[offset - 1] & 0xff);
    }

    public long readQWord() {
        long high = (long) readDWord() & 0xffffffffL;
        long low = (long) readDWord() & 0xffffffffL;
        return (high << 32) + low;
    }

    public String readString() {
        int start = offset;
        while (data[offset++] != 10) {}
        return new String(data, start, offset - start - 1);
    }

    public byte[] readStringRaw() {
        int start = offset;
        while (data[offset++] != 10) {}
        byte[] dest = new byte[offset - start - 1];
        System.arraycopy(data, start, dest, 0, offset - 1 - start);
        return dest;
    }

    public void readBytes(int destLen, int destOff, byte[] dest) {
        for (int n = destOff; n < destOff + destLen; n++) {
            dest[n] = data[offset++];
        }
    }

    public void accessBits() {
        bitOffset = offset * 8;
    }

    public int getBits(int bits) {
        int byteOffset = bitOffset >> 3;
        int msb = 8 - (bitOffset & 7);
        int i = 0;

        bitOffset += bits;

        for (; bits > msb; msb = 8) {
            i += (data[byteOffset++] & BITMASK[msb]) << bits - msb;
            bits -= msb;
        }

        if (bits == msb) {
            i += data[byteOffset] & BITMASK[msb];
        } else {
            i += data[byteOffset] >> msb - bits & BITMASK[bits];
        }
        
        return i;
    }

    public void accessBytes() {
        offset = (bitOffset + 7) / 8;
    }

    // range: -16384 to 16383
    public int readSmartSigned() {
        int peek = data[offset] & 0xff;
        if (peek < 128) {
            return readByte() - 64;
        } else {
            return readWord() - 0xc000;
        }
    }

    // range: 0 to 32768
    public int readSmart() {
        int peek = data[offset] & 0xff;
        if (peek < 128) {
            return readByte();
        } else {
            return readWord() - 0x8000;
        }
    }

    public void encryptPublic(BigInteger modulus, BigInteger exponent) {
        int start = offset;
        offset = 0;

        byte[] raw = new byte[start];
        readBytes(start, 0, raw);
        byte[] encrypted = new BigInteger(raw).modPow(exponent, modulus).toByteArray();

        offset = 0;
        writeByte(encrypted.length);
        writeBytes(encrypted, encrypted.length, 0);
    }

    public byte[] data;
    public int offset;
    public int bitOffset;
    public static final int[] BITMASK = {
        0, 1, 3, 7, 15, 31, 63, 127, 255, 511,
        1023, 2047, 4095, 8191, 16383, 32767, 65535, 0x1ffff, 0x3ffff, 0x7ffff,
        0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff,
        0x3fffffff, 0x7fffffff, -1
    };
    public IsaacRandom isaacState;
    public static int queueLowCount;
    public static int queueMidCount;
    public static int queueHighCount;
    public static final LinkedList queueLow = new LinkedList();
    public static final LinkedList queueMid = new LinkedList();
    public static final LinkedList queueHigh = new LinkedList();

}
