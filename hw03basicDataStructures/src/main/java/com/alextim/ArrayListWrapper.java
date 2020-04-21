package com.alextim;

import java.util.ArrayList;

public class ArrayListWrapper<T> implements Array<T> {

    private ArrayList<T> arrayList = new ArrayList<>();

    @Override
    public void add(T item, int index) {
        arrayList.add(index, item);
    }

    @Override
    public void add(T item) {
        arrayList.add(item);
    }

    @Override
    public T get(int index) {
        return arrayList.get(index);
    }

    @Override
    public T remove(int index) {
        return arrayList.remove(index);
    }

    @Override
    public int size() {
        return arrayList.size();
    }
}
