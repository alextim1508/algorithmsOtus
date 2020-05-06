package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.alextim.TreeTestHelper.toList;

public class RandomTreeTest {

    private final int SIZE = 100_000;

    @Test
    public void insertRootTest() {
        Random random = new Random();
        RandomizedTree.RandomizedNode root = new RandomizedTree.RandomizedNode(SIZE/2, "=");
        RandomizedTree tree = new RandomizedTree(root) {
            @Override
            boolean lottery(RandomizedNode node) {
                return false;
            }
        };
        for (int i = 0; i < SIZE; i++) {
            tree.insert(new RandomizedTree.RandomizedNode(random.nextInt(SIZE), "-"));
        }

        int key = random.nextInt(SIZE);
        tree.root = tree.insertRoot(new RandomizedTree.RandomizedNode(key, "="), root);

        List<Integer> keys = toList(tree);
        Assertions.assertEquals(key, keys.get(0).intValue());
   }

    @Test
    public void balanceTest() {
        RandomizedTree tree = new RandomizedTree(new RandomizedTree.RandomizedNode(SIZE/2, "="));
        for (int i = 0; i < SIZE; i++) {
            tree.insert(new RandomizedTree.RandomizedNode(i, "-"));
        }

        int leafCount = getLeafCountBySizeNode(tree);
        Assertions.assertTrue(leafCount < SIZE/3);
    }

    private int getLeafCountBySizeNode(RandomizedTree tree) {
        AtomicInteger leafCount = new AtomicInteger();
        tree.levelOrder(node -> {
            if(((RandomizedTree.RandomizedNode) node).size == 1)
                leafCount.incrementAndGet();
        });
        return leafCount.get();
    }
}
