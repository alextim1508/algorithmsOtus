package com.alextim;

import java.util.Arrays;

public class SingleArray<T> extends OneDimensionalArray<T> {

    protected void ensureCapacity(int targetSize) {
        if(targetSize > array.length) {
            int newSize = array.length + 1;
            array = Arrays.copyOf(array, targetSize > newSize ? targetSize : newSize);
        }
    }
}
