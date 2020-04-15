package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.Random;

public class EratosthenSieveTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/primeNumberCounts.csv")
    public void getPrimeNumberCountTest(int range, int primeNumberCount) {
        EratosthenSieve primeNumbersEratosthen  = new EratosthenSieve(range);
        Assertions.assertEquals(primeNumberCount, primeNumbersEratosthen.getPrimeNumberCount());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/primeNumberCounts.csv")
    public void getPrimeNumberCountWithMemoryOptimizationsTest(int range, int primeNumberCount) {
        EratosthenSieve primeNumbersEratosthen  = new EratosthenSieve(range);
        Assertions.assertEquals(primeNumberCount, primeNumbersEratosthen.getPrimeNumberCountWithMemoryOptimizations());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/primeNumberCounts.csv")
    public void getPrimeNumberCountWithLineComplexityTest(int range, int primeNumberCount) {
        EratosthenSieve primeNumbersEratosthen  = new EratosthenSieve(range);
        Assertions.assertEquals(primeNumberCount, primeNumbersEratosthen.getPrimeNumberCountWithLineComplexity());
    }

    @Test
    public void test() {
        EratosthenSieve primeNumbersEratosthen  = new EratosthenSieve(10_000);
        Assertions.assertEquals(1229, primeNumbersEratosthen.getPrimeNumberCountWithLineComplexity());
    }
}
