package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyHashMapTest {

    @Test
    public void insertRemoveTest() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, String.valueOf(i));
        }

        for (int i = 19; i >= 0; i--) {
            Assertions.assertEquals(map.remove(i), String.valueOf(i));
        }

        Assertions.assertEquals(0, map.size());
    }
}
