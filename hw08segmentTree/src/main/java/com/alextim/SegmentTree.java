package com.alextim;

import java.util.function.BiFunction;

public class SegmentTree {

    private int[] segmentTree;
    private BiFunction<Integer, Integer, Integer> fun;

    public SegmentTree(int[] source, BiFunction<Integer, Integer, Integer> fun) {
        this.fun = fun;
        build(source);
    }

    private void build(int[] source) {
        int size = roundUpPowerOf2(source.length);
        segmentTree = new int[2 * size];

        for (int i = 0; i < source.length; i++) {
            segmentTree[size + i] = source[i];
        }

        for (int i = size - 1; i > 0; i--)
            segmentTree[i] = fun.apply(segmentTree[2 * i], segmentTree[2 * i + 1]);
    }

    private int roundUpPowerOf2(int a) {
        int power = (int) (Math.log(a - 1) / Math.log(2));
        return 1 << (power + 1);
    }


    public void update(int i, int newValue) {
        i += (segmentTree.length / 2 - 1);
        segmentTree[i] = newValue;

        while (i != 0) {
            i /= 2;
            segmentTree[i] = fun.apply(segmentTree[2*i], segmentTree[2*i + 1]);
        }
    }

    public int rmqUup(int left, int right) {
        int segment = 0;
        left += segmentTree.length / 2 - 1;
        right += segmentTree.length / 2 - 1;

        while (left <= right) {
            if ((left & 1) != 0)
                segment = fun.apply(segment, segmentTree[left]);

            if ((right & 1) == 0)
                segment = fun.apply(segment, segmentTree[right]);

            left = (left + 1) / 2;
            right = (right - 1) / 2;
        }
        return segment;
    }
}
