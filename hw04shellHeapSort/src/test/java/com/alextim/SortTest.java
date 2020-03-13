package com.alextim;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.alextim.Sort.*;

public class SortTest {

    private int[] actual;
    private int[] expected;

    private final int SIZE = 1000000;

    @BeforeEach
    public void setUp() {
        actual = createRandomArray(SIZE, 100);
        //actual = createNearlySortedArray(SIZE, 5);

        expected = Arrays.copyOf(actual, actual.length);
        Arrays.sort(expected);
    }

    @Test @Disabled
    public void bubbleSortTest() {
        bubbleSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test @Disabled
    public void selectionSortTest() {
        selectionSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test @Disabled
    public void insertionSortTest() {
        insertionSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shellSortGap1Test() {
        shellSort(actual, k -> 2*actual.length/(int) Math.pow(2, k+1) + 1, 1, 1);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shellSortGap2Test() {
        shellSort(actual, k -> actual.length/(int) Math.pow(2, k+1), 1, 1);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shellSortGap3Test() {
        shellSort(actual, k -> (int) Math.pow(2, k) - 1, 10, -1);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shellSortGap4Test() {
        shellSort(actual, k -> (int) Math.pow(2, k) + 1, 10, -1);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shellSortGap5Test() {
        shellSort(actual, k -> actual.length/(int)(Math.pow(2, k)), 1, 1);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void heapSortTest() {
        heapSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }
}