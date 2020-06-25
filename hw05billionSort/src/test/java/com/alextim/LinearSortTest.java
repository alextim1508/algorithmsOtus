package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.alextim.ArrayHelper.createRandomArray;
import static com.alextim.LinearSort.*;
import static com.alextim.MergeSort.mergeSort;
import static com.alextim.QuickSort.quickSort;

public class LinearSortTest {

    private int[] actual;
    private int[] expected;

    private final int SIZE = 10_000_000;

    @BeforeEach
    public void setUp() {
        actual = createRandomArray(SIZE, Short.MAX_VALUE);

        expected = Arrays.copyOf(actual, actual.length);
        Arrays.sort(expected);
    }

    @Test
    public void mergeSortTest() {
        mergeSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void quickSortTest() {
        quickSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void countingSortTest() {
        countingSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void bucketSortTest() {
        bucketSort(actual, actual.length/100);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void radixSortTest() {
        radixSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }
}
