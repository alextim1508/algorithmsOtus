package com.alextim;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    private final int DEFAULT_CAPACITY = 16;

    MyNode<K, V>[] table;
    private int size;
    private int threshold;
    private float loadFactor;
    private int capacity;

    public MyHashMap() {
        this.loadFactor = 0.75f;
        this.capacity = DEFAULT_CAPACITY;
        this.threshold = (int) (capacity * loadFactor);
        initTable(DEFAULT_CAPACITY);
    }

    public MyHashMap(float loadFactory, int capacity) {
        this.loadFactor = loadFactory;
        this.capacity = capacity;
        this.threshold = (int) (capacity * loadFactory);
        initTable(capacity);
    }

    private void initTable(int capacity) {
        this.table = new MyNode[capacity];
        size = 0;
    }

    private int indexFor(int h, int length) {
        return h % length;
    }

    private int hash(Object object) {
        return object.hashCode();
    }

    private void resize(int capacity) {
        this.capacity = capacity;
        this.threshold = (int)(capacity * loadFactor);

        MyNode<K, V>[] oldTable = table;
        initTable(capacity);
        transfer(oldTable);
    }

    private void transfer(MyNode<K, V>[] oldTable) {
        for(MyNode<K, V> e : oldTable) {
            while (e != null) {
                put(e.getKey(), e.getValue());
                e = e.next;
            }
        }
    }

    private void checkTable() {
        if(size > threshold) {
            resize(2*capacity);
        }
    }

    @Override
    public V put(K key, V value) {
        checkTable();

        int hash = hash(key);
        int index = indexFor(hash, table.length);
        MyNode<K, V> node = table[index];

        while (node!=null) {
            if(hash == node.hash && key.equals(node.getKey())) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
            node = node.next;
        }

        node = table[index];
        if(node == null) {
            table[index] = new MyNode(hash, key, value, null);
        } else {
            table[index] = new MyNode(hash, key, value, node);
        }
        size++;
        return null;
    }

    @Override
    public V get(Object key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        MyNode<K, V> node = table[index];

        while (node!= null) {
            if(hash == node.hash && key.equals(node.getKey())) {
                return node.getValue();
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        MyNode<K, V> node = table[index];
        MyNode<K, V> removed;
        MyNode<K, V> previous = null;

        while (node!= null) {
            if(hash == node.hash && key.equals(node.getKey())) {
                 removed = node;
                 if(previous == null)
                     table[index] = node.next;
                 else
                    previous.next = node.next;
                 size--;
                 return removed.getValue();
            }
            previous = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public void print() {
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + ") ");
            MyNode<K, V> node = table[i];
            while (node != null) {
                System.out.print(node + " ");
                node = node.next;
            }
            System.out.println();
        }
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new RuntimeException("not supported");
    }

    @Override
    public Set<K> keySet() {
        throw new RuntimeException("not supported");
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("not supported");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new RuntimeException("not supported");
    }

    @Override
    public boolean containsKey(Object o) {
        throw new RuntimeException("not supported");
    }

    @Override
    public boolean containsValue(Object o) {
        throw new RuntimeException("not supported");
    }
}