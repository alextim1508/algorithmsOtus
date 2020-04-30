package com.alextim;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.alextim.TreeTestHelper.toList;


public class performanceTest {

    private final int SIZE = 1_000_000;
    private final Random random = new Random();

    @Test
    public void avlTreeLinearTest() {
        AvlTree tree = new AvlTree(new AvlTree.AvlNode(SIZE/2, "-"));
        for (int i = 0; i < SIZE; i++)
            tree.insert(new AvlTree.AvlNode(i, "-"));
        test(tree);
    }

    @Test
    public void avlTreeRandomTest() {
        AvlTree tree = new AvlTree(new AvlTree.AvlNode(SIZE/2, "-"));
        for (int i = 0; i < SIZE; i++)
            tree.insert(new AvlTree.AvlNode(random.nextInt(SIZE), "-"));
        test(tree);
    }

    @Test
    public void treeLinearTest() {
        Tree tree = new Tree(new Tree.Node(SIZE/2, "-"));
        for (int i = 0; i < SIZE; i++)
            tree.insert(new Tree.Node(i, "-"));
        test(tree);
    }

    @Test
    public void treeRandomTest() {
        Tree tree = new Tree(new Tree.Node(SIZE/2, "-"));
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
