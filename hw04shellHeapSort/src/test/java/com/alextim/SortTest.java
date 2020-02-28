package com.alextim;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.alextim.Sort.*;

public class SortTest {

    private int[] actual;
    private int[] expected;

    @BeforeEach
    public void setUp() {
        actual = createRandomArray(100_000, 1000);
        expected = Arrays.copyOf(actual, actual.length);
        Arrays.sort(expected);
    }

    @Test
    public void bubbleSortTest() {
        bubbleSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void selectionSortTest() {
        selectionSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void insertionSortTest() {
        insertionSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shellSortTest() {
        shellSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void heapSortTest() {
        heapSort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }
}
