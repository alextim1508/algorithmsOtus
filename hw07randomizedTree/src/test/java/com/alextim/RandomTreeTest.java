package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.alextim.TreeTestHelper.createKeysTree;
import static com.alextim.TreeTestHelper.toList;

public class RandomTreeTest {

    @Test
    public void insertRootTest() {
        Random random = new Random();
        RandomizedTree.RandomizedNode root = new RandomizedTree.RandomizedNode(200, "=");
        RandomizedTree tree = new RandomizedTree(root) {
            @Override
            boolean lottery(RandomizedNode node) {
                return false;
            }
        };
        for (int i = 0; i < 100; i++) {
            tree.insert(new RandomizedTree.RandomizedNode(random.nextInt(400), "-"));
        }

        int key = random.nextInt(400);
        tree.root = tree.insertRoot(new RandomizedTree.RandomizedNode(key, "="), root);

        List<Integer> keys = toList(tree);
        Assertions.assertEquals( key, keys.get(0).intValue());
   }

    @Test
    public void insertTest() {
        int[] keysTree = createKeysTree(4, 1000);
        Tree tree = toTree(keysTree);

        tree.remove(375);

        List<Integer> res = toList(tree);
        System.out.println("res.size() = " + res.size());

        AtomicInteger maxSize = new AtomicInteger();

        tree.levelOrder(node -> {
            int size = ((RandomizedTree.RandomizedNode) node).size;
            if(size>maxSize.intValue())
                maxSize.set(size);
        });
    }



    private Tree toTree(int[] array) {
        Tree tree = new RandomizedTree(new RandomizedTree.RandomizedNode(array[0], "value" + array[0]));
        for (int i = 1; i < array.length; i++) {
            tree.insert(new RandomizedTree.RandomizedNode(array[i], "value" + array[i]));
        }
        return tree;
    }
}
