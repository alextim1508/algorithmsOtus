package com.alextim;

import com.google.common.primitives.Booleans;

import java.util.*;

public class DistancedGraph extends WeightedGraph {

    public DistancedGraph(int[][] adjacencyVector, Edge[] edges) {
        super(adjacencyVector, edges);
    }

    public Edge[] getShortestWay(int i1, int i2) {
        List<Edge> edges = new ArrayList<>();
        int[] ways = getWays(i1);

        int i = i2;
        while (true) {
            int previous = ways[i];
            edges.add(new Edge(i, previous, getWeight(i, previous)));
            if(previous == i1)
                break;
            i = previous;
        }

        return edges.toArray(new Edge[0]);
    }

    int[] getWays(int in) {
        int[] weights = new int[adjacencyVector.length];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[in] = 0;

        int[] ways = new int[adjacencyVector.length];
        Arrays.fill(ways, -1);

        while(Booleans.contains(isUsed, false)) {

            int indexOfMin = getIndexOfMin(weights);

            for (int vertex: adjacencyVector[indexOfMin]) {
                if(!isUsed[vertex]) {
                    int weight = getWeight(indexOfMin, vertex);

                    if(weights[vertex] > weight + weights[indexOfMin]) {
                        weights[vertex] = weight + weights[indexOfMin];
                        ways[vertex] = indexOfMin;
                    }
                }
            }

            isUsed[indexOfMin] = true;
        }

        return ways;
    }

    int getIndexOfMin(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if(!isUsed[i]) {
                map.put(i, array[i]);
            }
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        return list.get(0).getKey();
    }
}
