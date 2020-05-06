package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.alextim.TreeTestHelper.createKeysTree;
import static com.alextim.TreeTestHelper.toList;

public class AvlTreeTest {

    @Test
    public void rotateRightTest() {
        int[] keysTree = createKeysTree(4, 1000);
        int keyTreeLength = keysTree.length;

        AvlTree tree = toTree(keysTree);
        tree.insert(new AvlTree.AvlNode(60, "value60"));
        tree.insert(new AvlTree.AvlNode(50, "value50"));

        List<Integer> res = toList(tree);
        Assertions.assertEquals(60, res.get(15).intValue());
        Assertions.assertEquals(50, res.get(keyTreeLength).intValue());
        Assertions.assertEquals(187, res.get(keyTreeLength + 1).intValue());
    }

    @Test
    public void rotateLeftTest() {
        int[] keysTree = createKeysTree(4, 1000);
        int keyTreeLength = keysTree.length;

        AvlTree tree = toTree(keysTree);
        tree.insert(new AvlTree.AvlNode(65, "value65"));
        tree.insert(new AvlTree.AvlNode(70, "value70"));

        List<Integer> res = toList(tree);
        Assertions.assertEquals(63, res.get(15).intValue());
        Assertions.assertEquals(70, res.get(keyTreeLength).intValue());
        Assertions.assertEquals(187, res.get(keyTreeLength + 1).intValue());
    }

    @Test
    public void rotateBigRightTest() {
        int[] keysTree = createKeysTree(4, 1000);
        int keyTreeLength = keysTree.length;

        AvlTree tree = toTree(keysTree);
        tree.insert(new AvlTree.AvlNode(60, "value60"));
        tree.insert(new AvlTree.AvlNode(65, "value65"));
        tree.insert(new AvlTree.AvlNode(67, "value67"));

        List<Integer> res = toList(tree);
        Assertions.assertEquals(res.get(7).intValue(), 65);
        Assertions.assertEquals(res.get(15).intValue(), 63);
        Assertions.assertEquals(res.get(16).intValue(), 125);
        Assertions.assertEquals(res.get(keyTreeLength).intValue(), 60);
        Assertions.assertEquals(res.get(keyTreeLength + 1).intValue(), 67);
        Assertions.assertEquals(res.get(keyTreeLength + 2).intValue(), 187);
    }

    @Test
    public void rotateBigLeftTest() {
        int[] keysTree = createKeysTree(4, 1000);
        int keyTreeLength = keysTree.length;

        AvlTree tree = toTree(keysTree);
        tree.insert(new AvlTree.AvlNode(190, "value190"));
        tree.insert(new AvlTree.AvlNode(180, "value180"));
        tree.insert(new AvlTree.AvlNode(170, "value170"));

        List<Integer> res = toList(tree);
        Assertions.assertEquals(res.get(7).intValue(), 180);
        Assertions.assertEquals(res.get(15).intValue(), 125);
        Assertions.assertEquals(res.get(16).intValue(), 187);
        Assertions.assertEquals(res.get(keyTreeLength).intValue(), 63);
        Assertions.assertEquals(res.get(keyTreeLength + 1).intValue(), 170);
        Assertions.assertEquals(res.get(keyTreeLength + 2).intValue(), 190);
    }

    @Test
    public void balanceTreeTest() {
        AvlTree tree = new AvlTree(new AvlTree.AvlNode(100, "-"));
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            tree.insert(new AvlTree.AvlNode(random.nextInt(200), "-"));
        }

        int key;
        while (true) {
            AvlTree.AvlNode searchNode = (AvlTree.AvlNode)tree.search(random.nextInt(200));
            if(searchNode != null) {
                key = searchNode.key;
                break;
            }
        }
        tree.remove(key);

        AtomicBoolean isBalance = new AtomicBoolean(true);
        tree.levelOrder(node -> {
            AvlTree.AvlNode left = (AvlTree.AvlNode)node.left;
            AvlTree.AvlNode right = (AvlTree.AvlNode)node.right;
            if(Math.abs(tree.height(left) - tree.height(right)) > 1) {
                isBalance.set(false);
            }
        });

        Assertions.assertEquals(true, isBalance.get());
    }

    private AvlTree toTree(int[] array) {
        AvlTree tree = new AvlTree(new AvlTree.AvlNode(array[0], "value" + array[0]));
        for (int i = 1; i < array.length; i++) {
            tree.insert(new AvlTree.AvlNode(array[i], "value" + array[i]));
        }
        return tree;
    }
}
