package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EratosthenSieveTest {

    private EratosthenSieve primeNumbersEratosthen = new EratosthenSieve(1_000);
    private int expected = 168;


    @Test
    public void getPrimeNumberCountTest() {
        Assertions.assertEquals(expected, primeNumbersEratosthen.getPrimeNumberCount());
    }

    @Test
    public void getPrimeNumberCountWithMemoryOptimizationsTest() {
        Assertions.assertEquals(expected, primeNumbersEratosthen.getPrimeNumberCountWithMemoryOptimizations());
    }

    @Test
    public void getPrimeNumberCountWithLineComplexityTest() {
        Assertions.assertEquals(expected, primeNumbersEratosthen.getPrimeNumberCountWithLineComplexity());

    }
}
