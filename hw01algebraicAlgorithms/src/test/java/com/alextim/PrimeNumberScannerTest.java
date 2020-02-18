package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.alextim.PrimeNumberScanner.getExponentNumber;
import static com.alextim.PrimeNumberScanner.getPrimeNumberMaxCount;


public class PrimeNumberScannerTest {

    private static PrimeNumberScanner scanner;

    @BeforeAll
    public static void setUp() {
        scanner = new PrimeNumberScanner(1_000);
    }

    @Test
    public void getExponentNumberTest() {
        Assertions.assertEquals(8,  getExponentNumber(100_000_000));
    }

    @Test
    public void getPrimeNumberMaxCountTest() {
        Assertions.assertTrue(getPrimeNumberMaxCount(10) >= 4);
        Assertions.assertTrue(getPrimeNumberMaxCount(100) >= 25);
        Assertions.assertTrue(getPrimeNumberMaxCount(1_000) >= 168);
        Assertions.assertTrue(getPrimeNumberMaxCount(10_000) >= 1_229);
        Assertions.assertTrue(getPrimeNumberMaxCount(100_000) >= 9_592);
        Assertions.assertTrue(getPrimeNumberMaxCount(1_000_000) >= 7_8498);
        Assertions.assertTrue(getPrimeNumberMaxCount(10_000_000) >= 664_579);
        Assertions.assertTrue(getPrimeNumberMaxCount(100_000_000) >= 5_761_455);
        Assertions.assertTrue(getPrimeNumberMaxCount(1_000_000_000) >= 50_847_534);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv")
    public void isPrimeNumberWithOptimizationsTest(int number, boolean isPrime) {
        Assertions.assertEquals(scanner.isPrimeNumberWithOptimizations(number), isPrime);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv")
    public void isPrimeNumberTest(int number, boolean isPrime) {
        Assertions.assertEquals(scanner.isPrimeNumber(number), isPrime);
    }

    @Test
    public void getPrimeNumberCountTest() {
        scanner.clearPrimeNumbers();
        Assertions.assertEquals(168, scanner.getPrimeNumberCount());
    }


}