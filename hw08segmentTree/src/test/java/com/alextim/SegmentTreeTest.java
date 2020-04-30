package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SegmentTreeTest {

    @Test
    public void test1() {
        SegmentTree tree = new SegmentTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, Integer::sum);
        Assertions.assertEquals(25, tree.rmqUup(3, 7) );
    }

    @Test
    public void test2() {
        SegmentTree tree = new SegmentTree(new int[]{0, 0, 0, 0, 0}, Integer::sum);
        tree.update(2,2);
        tree.update(3,1);
        tree.update(4,2);
        tree.update(2,2);

        Assertions.assertEquals(0, tree.rmqUup(1, 1));
        Assertions.assertEquals(2, tree.rmqUup(2, 2));
        Assertions.assertEquals(1, tree.rmqUup(3, 3));
        Assertions.assertEquals(2, tree.rmqUup(4, 4));
        Assertions.assertEquals(0, tree.rmqUup(5, 5));
        Assertions.assertEquals(5, tree.rmqUup(1, 5));
    }
}
