package com.alextim;

import java.util.Random;

public class ArrayHelper {

    public static int[] createRandomArray(int size, int bound) {
        return new Random().ints(size, -1*bound, bound).toArray();
    }

    public static int[] merge(int[] arrayA, int[] arrayB) {
        int a = 0;
        int b = 0;
        int r = 0;
        int [] result = new int[arrayA.length + arrayB.length];
        while (a < arrayA.length && b < arrayB.length) {
            if(arrayA[a]<arrayB[b])
                result[r++] = arrayA[a++];
            else
                result[r++] = arrayB[b++];
        }
        while (a<arrayA.length)
            result[r++] = arrayA[a++];
        while (b<arrayB.length)
            result[r++] = arrayB[b++];

        return result;
    }
}
