package com.alextim;

import com.google.common.primitives.Ints;


import java.util.*;

public class SortedGraph extends Graph {

    public SortedGraph(int[][] adjacencyVector) {
        super(adjacencyVector);
    }

    public int[][] sort(String algorithm) {
        if(algorithm.equals("Demucron")) {
            return getLayers().stream().map(Ints::toArray).toArray(int[][]::new);
        }
        return null;
    }

    private List<List<Integer>> getLayers() {
        List<List<Integer>> layers = new ArrayList<>();
        Set<Integer> isSorted = new HashSet<>();

        while (isSorted.size() != adjacencyVector.length) {

            for (int i = 0; i < adjacencyVector.length; i++)
                if(!isSorted.contains(i))
                    for (int j = 0; j < adjacencyVector[i].length; j++)
                        isUsed[ adjacencyVector[i][j] ] = true;

            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < isUsed.length; i++) {
                if(!isUsed[i] && !isSorted.contains(i)) {
                    layer.add(i);
                    isSorted.add(i);
                }
            }

            layers.add(layer);
            Arrays.fill(isUsed, false);
        }
        return layers;
    }
}
