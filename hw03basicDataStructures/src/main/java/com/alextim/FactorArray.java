package com.alextim;

import java.util.Arrays;

public class FactorArray<T> extends OneDimensionalArray<T> {

    private final int factor;

    public FactorArray(int factor) {
        super();
        if(factor < 2)
            throw new IllegalArgumentException("Factor must be > 1");
        this.factor = factor;
    }

    protected void ensureCapacity(int targetSize) {
        if(targetSize > array.length) {
            int newSize = factor * array.length;
            array = Arrays.copyOf(array, targetSize > newSize ? targetSize : newSize);
        }
    }
}
