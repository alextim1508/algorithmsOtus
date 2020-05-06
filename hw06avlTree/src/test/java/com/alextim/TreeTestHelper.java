package com.alextim;

import java.util.ArrayList;
import java.util.List;

public class TreeTestHelper {

    static List<Integer> toList(Tree tree) {
        List<Integer> keys = new ArrayList<>();
        tree.levelOrder(node -> keys.add(node.key));
        return keys;
    }

    static int[] createKeysTree(int maxLevel, int keyRoot) {
        int delta = keyRoot;

        int[] mas = new int[(int)Math.pow(2, maxLevel+1) - 1];
        mas[0] = keyRoot;

        for (int i = 0, j=0, l=0; i < (int)Math.pow(2, maxLevel) - 1; i++, j--) {
            if(j == 0) {
                delta/=2;
                j = (int) Math.pow(2, l++);
            }

            keyRoot = mas[i];
            mas[2*i + 1] = keyRoot - delta;
            mas[2*i + 2] = keyRoot + delta;
        }
        return mas;
    }
}
