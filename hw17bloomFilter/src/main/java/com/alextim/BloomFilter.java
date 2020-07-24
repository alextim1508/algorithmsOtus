package com.alextim;

import com.google.common.hash.Hashing;

import java.nio.ByteBuffer;

public class BloomFilter {

    private int hashCount;

    private long byteSize;
    private ByteBuffer bloom;

    public BloomFilter(int maxKeys, double errorRate, int foldFactor) {
        long bitSize = computeBitSize(maxKeys, errorRate);
        this.hashCount = computeHashFunctionCount(maxKeys, bitSize);
        this.byteSize = computeBloomFilterSize(bitSize, foldFactor);

        this.bloom = ByteBuffer.allocate((int) byteSize);
    }

    public static long computeBitSize(long maxKeys, double errorRate) {
        return (long) Math.ceil(maxKeys * (-Math.log(errorRate) / (Math.log(2) * Math.log(2))));
    }

    public static int computeHashFunctionCount(int maxKeys, long bitSize) {
        return (int)Math.ceil(Math.log(2) * bitSize / maxKeys);
    }

    public static int computeBloomFilterSize(long bitSize, int foldFactor) {
        long byteSizeLong = (bitSize + 7) / 8;
        int mask = (1 << foldFactor) - 1;
        if ((mask & byteSizeLong) != 0) {
            byteSizeLong >>= foldFactor;
            ++byteSizeLong;
            byteSizeLong <<= foldFactor;
        }
        return (int) byteSizeLong;
    }

    public void add(byte [] buf) {
        add(buf, 0, buf.length);
    }

    public void add(byte [] buf, int offset, int len) {
        int hash1 = Hashing.murmur3_32().hashBytes(buf, offset, len).asInt();
        int hash2 = Hashing.murmur3_32(hash1).hashBytes(buf, offset, len).asInt();

        for (int i = 0; i < this.hashCount; i++)
            set((int) Math.abs((hash1 + i * hash2) % (byteSize * 8)));
    }

    public boolean contains(byte[] buf) {
        return contains(buf, 0, buf.length);
    }

    public boolean contains(byte[] buf, int offset, int length) {
        int hash1 = Hashing.murmur3_32().hashBytes(buf, offset, length).asInt();
        int hash2 = Hashing.murmur3_32(hash1).hashBytes(buf, offset, length).asInt();

        int compositeHash = hash1;
        for (int i = 0; i < hashCount; i++) {
            if (!get((int) Math.abs(compositeHash % (byteSize * 8))))
                return false;
            compositeHash += hash2;
        }
        return true;
    }

    public void set(int pos) {
        int byteNum = pos >> 3;
        int bitNum = pos & 0x7;
        byte currentByte = bloom.get(byteNum);
        currentByte |= mask[bitNum];
        bloom.put(byteNum, currentByte);
    }

    public boolean get(int pos) {
        int byteNum = pos >> 3;
        int bitNum = pos & 0x7;
        byte currentByte = bloom.get(byteNum);
        currentByte &= mask[bitNum];
        return (currentByte != 0);
    }

    private static final byte [] mask = {
            (byte) 0x01,
            (byte) 0x02,
            (byte) 0x04,
            (byte) 0x08,
            (byte) 0x10,
            (byte) 0x20,
            (byte) 0x40,
            (byte) 0x80
    };
}
