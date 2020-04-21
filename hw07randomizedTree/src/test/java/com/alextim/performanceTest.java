package com.alextim;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.alextim.TreeTestHelper.toList;


public class performanceTest {

    private final int SIZE = 1_000_000;
    private final Random random = new Random();

    @Test
    public void randomizedTreeLinearTest() {
        Tree tree = new RandomizedTree(new RandomizedTree.RandomizedNode(SIZE/2, "-"));
        for (int i = 0; i < SIZE; i++)
            tree.insert(new RandomizedTree.RandomizedNode(i, "-"));
        test(tree);
    }

    @Test
    public void randomizedTreeRandomTest() {
        Tree tree = new RandomizedTree(new RandomizedTree.RandomizedNode(SIZE/2, "-"));
        for (int i = 0; i < SIZE; i++)
            tree.insert(new RandomizedTree.RandomizedNode(random.nextInt(SIZE), "-"));
        test(tree);
    }

    @Test
    public void splayTreeLinearTest() {
        Tree tree = new SplayTree(new Tree.Node(SIZE/2, "-"));
        for (int i = 0; i < SIZE; i++)
            tree.insert(new Tree.Node(i, "-"));
        test(tree);
    }

    @Test
    public void splayTreeRandomTest() {
        Tree tree = new SplayTree(new Tree.Node(SIZE/2, "-"));
        for (int i = 0; i < SIZE; i++)
            tree.insert(new Tree.Node(random.nextInt(SIZE), "-"));
        test(tree);
    }

    private void test(Tree tree) {
        int count = SIZE/10;
        List<Integer> list = toList(tree);
        Collections.shuffle(list);

        while (count-- != 0) {
            Tree.Node search = tree.search(list.remove(list.size() - 1));
            tree.remove(search.key);
        }
    }
}