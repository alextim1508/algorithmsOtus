package com.alextim;

@SuppressWarnings("unchecked")
public interface Array<T> {
    void add(T item, int index);
    void add(T item);
    T get(int index);
    T remove(int index);
    int size();
}
