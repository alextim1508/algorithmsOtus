package com.alextim;

import static com.alextim.ArrayHelper.move;

public class MatrixArray<T> implements Array<T> {

    private Array<Object[]> array;
    private final int len;
    private int size;

    public MatrixArray(Array<Object[]> innerArray, int rowLength) {
        this.array = innerArray;
        this.len = rowLength;
        this.size = 0;
    }

    @Override
    public void add(T item, int index) {
        ensureCapacity(size + 1);
        int row = index/len;
        int column = index%len;

        for (int i = array.size()-1; i > row; i--) {
            move(array.get(i), 0, 1,  "right");
            array.get(i)[0] = array.get(i-1)[len -1];
        }
        move(array.get(row), column, 1,  "right");

        array.get(row)[column] = item;
        size++;
    }

    @Override
    public void add(T item) {
        ensureCapacity(size + 1);

        array.get(size/len)[size%len] = item;
        size++;
    }

    @Override @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array.get(index/len)[index%len];
    }

    @Override @SuppressWarnings("unchecked")
    public T remove(int index) {
        int row = index/len;
        int column = index%len;

        move(array.get(row), column, 1,  "left");

        for (int i = row +1; i < array.size(); i++) {
            array.get(i-1)[len-1] = array.get(i)[0];
            move(array.get(i), 0, 1,  "left");
        }

        size--;

        clearEnd();
        return (T) array.get(row)[column];
    }

    private void clearEnd() {
        if(len * array.size() - size >= len) {
            array.remove(array.size());
        }
    }

    @Override
    public int size() {
        return size;
    }

    protected void ensureCapacity(int targetSize) {
        if(targetSize > array.size() * len) {
            array.add(new Object[len]);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getSimpleName() +"\n");
        for (int i = 0; i < array.size(); i++) {
            builder.append("\t\t[");
            String splitter = "";
            for (int j = 0; j < len && !(i == array.size()-1 && j>=size%len); j++) {
                builder.append(splitter).append(array.get(i)[j]);
                splitter = ", ";
            }
            builder.append("]\n");
        }
        return builder.toString();
    }
}
