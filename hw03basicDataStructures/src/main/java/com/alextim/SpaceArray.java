package com.alextim;

public class SpaceArray<T> implements Array<T> {

    private Array<Array<Object>> array;
    private final int len;
    private int size;

    public SpaceArray(Array<Array<Object>> innerArray, int rowLength) {
        this.array = innerArray;
        this.len = rowLength;
        this.size = 0;
    }

    @Override
    public void add(T item, int index) {
        ensureCapacity(size + 1);

        Cell cell = getCell(index);
        array.get(cell.row).add(item, cell.column);
        size++;
    }

    @Override
    public void add(T item) {
        ensureCapacity(size + 1);

        array.get(array.size() - 1).add(item);
        size++;
    }

    @Override @SuppressWarnings("unchecked")
    public T get(int index) {
        Cell cell = getCell(index);
        return (T) array.get(cell.row).get(cell.column);
    }

    @Override @SuppressWarnings("unchecked")
    public T remove(int index) {
        Cell cell = getCell(index);
        size--;
        return (T) array.get(cell.row).remove(cell.column);
    }

    @Override
    public int size() {
        return size;
    }

    private Cell getCell(int index) {
        int row;
        for (row = 0; row < array.size(); row++) {
            index -= array.get(row).size();
            if(index <0) {
                break;
            }
        }
        int column = array.get(row).size() + index;
        return new Cell(row, column);
    }

    protected void ensureCapacity(int targetSize) {
        trim();

        if(array.size()==0 ||
                (targetSize > array.size()* len && array.get(array.size()-1).size() >= len)) {
            array.add(new VectorArray<>(10));
        }
    }

    private void trim() {
        for (int i = 0; i < array.size(); ) {
            if(array.get(i).size() > 2* len)
                split(i);
            else
                i++;
        }
    }

    private void split(int i) {
        array.add(new SingleArray<>() , i+1);
        array.add(new SingleArray<>() , i+2);

        for (int j = 0; j < array.get(i).size()/2; j++) {
            array.get(i+1).add(array.get(i).get(j));
        }
        for (int j = array.get(i).size()/2 ; j < array.get(i).size(); j++) {
            array.get(i+2).add(array.get(i).get(j));
        }
        array.remove(i);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getSimpleName() +"\n");
        for (int i = 0; i < array.size(); i++) {
            builder.append("\t\t[");
            String splitter = "";
            for (int j = 0; j < array.get(i).size(); j++) {
                builder.append(splitter).append(array.get(i).get(j));
                splitter = ", ";
            }
            builder.append("]\n");
        }
        return builder.toString();
    }

    private static class Cell {
        int row;
        int column;

        Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
