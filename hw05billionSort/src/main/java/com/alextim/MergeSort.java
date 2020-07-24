package com.alextim;

public class MergeSort {

    public static void mergeSort(int[] array) {
        mergeSort(array, 0 , array.length-1);
    }

    private static void mergeSort(int[] array, int left, int right) {
        if(left>=right)
            return;
        int center = (left + right) /2;
        mergeSort(array, left, center);
        mergeSort(array, center+1, right);

        merge(array, left, center, right);
    }

    private static void merge(int[] array, int left, int center, int right) {
        int a = left;
        int b = center+1;
        int r = 0;
        int [] result = new int[right-left+1];
        while (a <= center && b <= right) {
            if(array[a]<array[b])
                result[r++] = array[a++];
            else
                result[r++] = array[b++];
        }
        while (a<=center)
            result[r++] = array[a++];
        while (b<=right)
            result[r++] = array[b++];

        System.arraycopy(result, 0, array, left, right + 1 - left);
    }
}
