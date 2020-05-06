package com.alextim;

import static com.alextim.ArrayHelper.move;
import static com.alextim.ArrayHelper.resize;

public abstract class OneDimensionalArray<T> implements Array<T> {

    protected Object[] array;
    private int size;

    protected OneDimensionalArray() {
        array = new Object[0];
        size = 0;
    }

    @Override
    public void add(T item) {
        ensureCapacity(size + 1);
        array[size++] = item;
    }

    @Override
    public void add(T item, int index) {
        ensureCapacity(size + 1);
        move(array, index, 1, "right");
        array[index] = item;
        size++;
    }

    @Override @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override @SuppressWarnings("unchecked")
    public T remove(int index) {
        Object removed = array[index];
        move(array, index, 1,  "left");
        array = resize(array, -1);
        size--;
        return (T)removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getSimpleName());
        builder.append("[");
        String splitter = "";
        for (int i = 0; i < size; i++) {
            builder.append(splitter).append(array[i]);
            splitter = ", ";
        }
        builder.append("]");
        return builder.toString();
    }

    protected abstract void ensureCapacity(int targetSize);
}
