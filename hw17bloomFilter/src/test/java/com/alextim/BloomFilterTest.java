package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class BloomFilterTest {

    private final float ERROR_RATE = 0.005f;
    private final int MAX_KEYS = 1_000_000;

    Set<byte[]> dataSet = new HashSet<>();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < MAX_KEYS; i++)
            dataSet.add(ByteBuffer.allocate(4).putInt(i).array());
    }

    @Test
    public void testBasicBloom() {
        BloomFilter bloomFilter = new BloomFilter(MAX_KEYS, ERROR_RATE, 0);
        int falsePositiveCount = 0;

        dataSet.forEach(element -> bloomFilter.add(element));

        for (int i = MAX_KEYS + 1; i < 2*MAX_KEYS; i++) {
            if(bloomFilter.contains(ByteBuffer.allocate(4).putInt(i).array()))
                falsePositiveCount++;
        }

        System.out.println("falsePositiveCount = " + falsePositiveCount);
        System.out.println("limit = " + MAX_KEYS * ERROR_RATE);
        Assertions.assertTrue(falsePositiveCount < MAX_KEYS * ERROR_RATE);
    }
}
