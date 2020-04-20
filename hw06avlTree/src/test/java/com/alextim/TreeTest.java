package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.alextim.TreeTestHelper.createKeysTree;
import static com.alextim.TreeTestHelper.toList;

public class TreeTest {

    @Test
    public void insertTest() {
        int[] keysTree = createKeysTree(4, 1000);
        int keyTreeLength = keysTree.length;

        Tree tree = toTree(keysTree);
        tree.insert(new Tree.Node(50, "value50"));
        tree.insert(new Tree.Node(52, "value52"));
        tree.insert(new Tree.Node(53, "value53"));
        tree.insert(new Tree.Node(49, "value49"));

        List<Integer> res = toList(tree);
        Assertions.assertEquals(50, res.get(keyTreeLength).intValue());
        Assertions.assertEquals(49, res.get(keyTreeLength + 1).intValue());
        Assertions.assertEquals(52, res.get(keyTreeLength + 2).intValue());
        Assertions.assertEquals(53, res.get(keyTreeLength + 3).intValue());
    }

    @Test
    public void deleteTest() {
        int[] keysTree = createKeysTree(4, 1000);
        int keyTreeLength = keysTree.length;

        Tree tree = toTree(keysTree);

        tree.insert(new Tree.Node(310, "-"));
        tree.remove(313);

        tree.insert(new Tree.Node(687, "-"));
        tree.remove(680);

        Tree.Node searchNode = tree.search(1500);
        Tree.Node min = tree.findMin(searchNode.right);
        tree.remove(searchNode.key);

        List<Integer> keys = toList(tree);
        Assertions.assertEquals( min.key, keys.get(2).intValue());
        Assertions.assertEquals(keys.size(), keyTreeLength-1);
    }

    private Tree toTree(int[] array) {
        Tree tree = new Tree(new Tree.Node(array[0], "value" + array[0]));
        for (int i = 1; i < array.length; i++) {
            tree.insert(new Tree.Node(array[i], "value" + array[i]));
        }
        return tree;
    }
}
