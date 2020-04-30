package com.alextim;

import java.util.Random;
import java.util.function.Function;

public class Sort {

    public static int[] createRandomArray(int size, int bound) {
        return new Random().ints(size, -1*bound, bound).toArray();
    }

    public static int[] createNearlySortedArray(int size, double percent) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < Math.round(percent*size/100); i++) {
            array[random.nextInt(size)] = random.nextInt(size);
        }
        return array;
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
            if(j != i-1)
                array[j+1] = insert;
        }
    }

    public static void shellSort(int[] array, Function<Integer, Integer> getGap, int k, int delta) {
        int g;
        do {
            g = getGap.apply(k);
            for (int i = 0; i < array.length - g; i++) {
                int j = i + g;
                int insert = array[j];

                for (;j - g >= 0 && array[j - g] > insert; j -=g) {
                    array[j] = array[j - g];
                }
                if(j != i+g)
                    array[j] = insert;
            }
            k+= delta;
        } while (g!=1);
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


















