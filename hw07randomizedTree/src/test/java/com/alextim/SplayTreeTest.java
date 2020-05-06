package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.alextim.TreeTestHelper.toList;

public class SplayTreeTest {

    private final int SIZE = 10_000;

    private SplayTree tree;

    @BeforeEach
    public void insertTest() {
        Random random = new Random();
        Tree.Node root = new Tree.Node(SIZE / 2, "-");
        tree = new SplayTree(root);
        for (int i = 0; i < SIZE; i++) {
            int key = random.nextInt(SIZE);
            tree.insert(new Tree.Node(key, ""));

            Assertions.assertEquals(key, toList(tree).get(0).intValue());
        }
    }

    @Test
    public void searchTest() {
        List<Integer> key = toList(tree);
        Collections.shuffle(key);

        key.forEach(i -> {
            Tree.Node search = tree.search(i);
            Assertions.assertEquals(i.intValue(), search.key);
            Assertions.assertEquals(i.intValue(), toList(tree).get(0).intValue());
        });

    }

    @Test
    public void removeTest() {
        List<Integer> key = toList(tree);
        Collections.shuffle(key);

        key.forEach(i -> tree.remove(i));
        Assertions.assertNull(tree.root);
    }

}
