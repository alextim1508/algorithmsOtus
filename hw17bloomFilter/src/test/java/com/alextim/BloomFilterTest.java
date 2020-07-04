package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class BloomFilterTest {

    private final float ERROR_RATE = 0.01f;
    private final int MAX_KEYS = 1_000_000;

    Set<byte[]> dataSet = new HashSet<>();

    @BeforeEach
    public void setUp() {
        Random random = new Random();
        for (int i = 0; i < MAX_KEYS; i++) {
            byte[] arr = new byte[random.nextInt(100)];
            random.nextBytes(arr);
            dataSet.add(arr);
        }
    }

    @Test
    public void testBasicBloom() {
        BloomFilter bloomFilter = new BloomFilter(MAX_KEYS, ERROR_RATE, 0);
        AtomicInteger falsePositiveCount = new AtomicInteger();

        dataSet.forEach(element -> {
            if(bloomFilter.contains(element))
                falsePositiveCount.incrementAndGet();

            bloomFilter.add(element);
        });

        System.out.println("falsePositiveCount = " + falsePositiveCount.get());
        Assertions.assertTrue(falsePositiveCount.get() < MAX_KEYS * ERROR_RATE);
    }
}
