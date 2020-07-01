package com.alextim;

import java.util.*;

public class LinearSort {

    public static void countingSort(int[] array) {
        int[] counts = new int[2*(Short.MAX_VALUE+1)];
        fillCounts(counts, array);

        sumPrevious(counts);

        int[] sortedArray = getSortedArray(counts, array);
        System.arraycopy(sortedArray, 0, array, 0, sortedArray.length);
    }

    private static void fillCounts(int[]counts, int[] array) {
        for (int i = 0; i < array.length; i++) {
            counts[array[i] + Short.MAX_VALUE + 1]++;
        }
    }

    private static void sumPrevious(int[]counts) {
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i-1] + counts[i];
        }
    }

    private static int[] getSortedArray(int[] counts, int[] array) {
        int[] sortedArray = new int[array.length];
        for (int i = array.length -1; i >=0; i--) {
            int index = --counts[array[i] + Short.MAX_VALUE + 1];
            sortedArray[index] = array[i];
        }
        return sortedArray;
    }

    public static void bucketSort(int[] array, int bucketSize) {
        List<List<Integer>> buckets = getBuckets(bucketSize);
        for (int i = 0; i < array.length; i++) {
            int bucketIndex = getBucketIndex(array[i], bucketSize);
            buckets.get(bucketIndex).add(array[i]);
        }
        copy(buckets, array);
    }

    private static List<List<Integer>> getBuckets(int bucketSize) {
        List<List<Integer>> buckets = new ArrayList<>(bucketSize);
        for (int i = 0; i < bucketSize; i++) {
            buckets.add(new ArrayList<>());
        }
        return buckets;
    }

    private static void copy(List<List<Integer>> fromBuckets, int[] array) {
        int index=0;
        for (List<Integer> bucket :fromBuckets) {
            Collections.sort(bucket);
            for (Integer value : bucket) {
                array[index++] = value;
            }
        }
    }

    private static int getBucketIndex(int value, int bucketSize) {
        int correctionValue = value + Short.MAX_VALUE + 1;
        int valuesSize = 2 * (Short.MAX_VALUE + 1);
        return bucketSize * (correctionValue / valuesSize);
    }

    public static void radixSort(int[] array) {
        radixSort(array, 10);
    }

    private static void radixSort(int[] array, int N) {
        if(N > Math.pow(10, digitsCount(Short.MAX_VALUE)))
            return;

        int[] counts = new int[19];

        for (int i = 0; i < array.length; i++) {
            int index = (array[i] % N)/(N/10);
            counts[index+9]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i-1] + counts[i];
        }

        int[] sortedArray = new int[array.length];
        for (int i = array.length -1 ; i >=0 ; i--) {
            int index = --counts[(array[i] % N)/(N/10)+9];
            sortedArray[index] = array[i];
        }

        System.arraycopy(sortedArray, 0, array, 0, sortedArray.length);
        radixSort(array, 10*N);
    }

    private static int digitsCount(int value) {
        int count =0;
        while (value!=0) {
            value/= 10;
            count++;
        }
        return count;
    }
}
