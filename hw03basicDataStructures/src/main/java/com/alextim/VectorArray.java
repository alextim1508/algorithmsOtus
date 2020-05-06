package com.alextim;

import java.util.Arrays;

public class VectorArray<T> extends OneDimensionalArray<T> {

    private final int vector;

    public VectorArray(int vector) {
        super();
        if(vector < 2)
            throw new IllegalArgumentException("Vector must be > 1");
        this.vector = vector;
    }

    protected void ensureCapacity(int targetSize) {
        if(targetSize > array.length) {
            int newSize = array.length + vector;
            array = Arrays.copyOf(array, targetSize > newSize ? targetSize : newSize);
        }
    }
}