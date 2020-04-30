package com.alextim;

import java.util.Map;
import java.util.Objects;

class MyNode<K, V> implements Map.Entry<K, V> {
    final int hash;
    private final K key;
    private V value;
    MyNode<K, V> next;

    public MyNode(int hash, K key, V value, MyNode<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public final String toString() {
        return this.key + "=" + this.value;
    }

}
