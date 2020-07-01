package com.alextim;

public class QuickSort {

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length-1);
    }

    private static void quickSort(int[] array, int left, int right) {
        if(left <right) {
            int center = partition(array, left, right);

            quickSort(array, left, center-1);
            quickSort(array, center+1, right);
        }
    }

    private static int partition(int[]  array, int left, int right) {
        int i = left -1;
        int pivot = array[right];
        for (int j = left; j <= right-1; j++) {
            if(array[j]<pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1 , right);
        return i+1;
    }

    private static void swap(int[] array, int a, int b) {
        if(a!=b) {
            array[a] = array[a] + array[b];
            array[b] = array[a] - array[b];
            array[a] = array[a] - array[b];
        }
    }
}
