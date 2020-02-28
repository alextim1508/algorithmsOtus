package com.alextim;

import java.util.Random;

public class Sort {

    public static int[] createRandomArray(int size, int bound) {
        return new Random().ints(size, -1*bound, bound).toArray();
    }

    public static void bubbleSort(int[] array) {
        boolean isSorted = true;
        for (int i=0; i< array.length; i++) {
            for (int j = 0; j < array.length -1 -i; j++) {
                if(array[j] > array[j+1]) {
                    swap(array, j, j+1);
                    isSorted = false;
                }
            }
            if(isSorted)
                return;
        }
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if(array[j] < array[min])
                    min = j;
            }
            if(min != i)
                swap(array, i, min);
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insert = array[i];
            int j = i-1;
            for (; j>=0 && array[j]>insert; j--) {
                array[j+1] = array[j];
            }
            array[j+1] = insert;
        }
    }

    public static void shellSort(int[] array) {
        for (int g = array.length/2; g > 0; g/=2) {
            for (int i = 0; i < array.length - g; i++) {
                int j = i + g;
                int insert = array[j];

                for (;j - g >= 0 && array[j - g] > insert; j -=g) {
                    array[j] = array[j - g];
                }
                array[j] = insert;
            }
        }
    }

    public static void heapSort(int[] array) {
        int lastParent = array.length / 2 - 1;
        for (int i = lastParent; i >=0; i--)
            heapify(array, array.length, i);

        for (int i = array.length-1; i >0 ; i--) {
            swap(array, 0, i);
            heapify(array,  i, 0);
        }
    }

    private static void heapify(int[] array, int size, int node) {
        int largest  = node;
        int left = 2*node + 1;
        int right = left + 1;

        if(right < size && array[right] > array[largest]) {
            largest = right;
        }
        if(left < size && array[left] > array[largest]) {
            largest = left;
        }
        if(largest != node) {
            swap(array, node, largest);
            heapify(array, size, largest);
        }
    }

    private static void swap(int[] a, int i1, int i2) {
        a[i1] = a[i1] + a[i2];
        a[i2] = a[i1] - a[i2];
        a[i1] = a[i1] - a[i2];
    }
}


















