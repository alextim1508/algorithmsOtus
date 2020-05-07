package com.alextim;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Graph {

    final int[][] adjacencyVector;
    final boolean[] isUsed;

    public Graph(int[][] adjacencyVector) {
        this.adjacencyVector = adjacencyVector;
        isUsed = new boolean[adjacencyVector.length];
    }


    public int[][] strongConnectivityComponentsSearch(String algorithm) {
        if(algorithm.equals("Kosarayu")) {

            int[][] invert = getAdjacencyVectorInvert();
            boolean[] isUsed = new boolean[adjacencyVector.length];

            List<Integer> vertices = new ArrayList<>(adjacencyVector.length);
            while (true) {
                int vertex = Booleans.indexOf(isUsed, false);
                if(vertex == -1) break;

                List<Integer> temp = new ArrayList<>(adjacencyVector.length);
                depthFirstSearch(invert, isUsed, vertex, i -> temp.add(0, i));
                vertices.addAll(temp);
            }

            isUsed = new boolean[adjacencyVector.length];
            List<List<Integer>> strongConnectivityComponents = new ArrayList<>();
            while (Booleans.contains(isUsed, false)) {
                int vertex = vertices.remove(vertices.size() - 1);

                List<Integer> strongConnectivityComponent = new ArrayList<>(adjacencyVector.length);
                depthFirstSearch(adjacencyVector, isUsed, vertex, i -> strongConnectivityComponent.add(0, i));
                if(!strongConnectivityComponent.isEmpty())
                    strongConnectivityComponents.add(strongConnectivityComponent);
            }

            int[][] result = new int[strongConnectivityComponents.size()][];
            AtomicInteger count = new AtomicInteger();
            strongConnectivityComponents.forEach(i -> result[count.getAndIncrement()] = Ints.toArray(i));
            return result;
        } else {
            throw new RuntimeException("unknown algorithm");
        }
    }

    int[][] getAdjacencyVectorInvert() {
        List<Integer>[] adjacencyVectorInvert = new ArrayList[adjacencyVector.length];
        for (int i = 0; i < adjacencyVectorInvert.length; i++) {
            adjacencyVectorInvert[i] = new ArrayList<>(adjacencyVector.length);
        }

        for (int i = 0; i < adjacencyVector.length; i++) {
            for (int vec : adjacencyVector[i]) {
                adjacencyVectorInvert[vec].add(i);
            }
        }

        int[][] result = new int[adjacencyVector.length][];
        for (int i = 0; i < adjacencyVectorInvert.length; i++) {
            result[i] = Ints.toArray(adjacencyVectorInvert[i]);
        }
        return result;
    }

    public void breadthFirstSearch(Consumer<Integer> consumer) {
        Arrays.fill(isUsed, false);
        breadthFirstSearch(adjacencyVector, isUsed, 0, consumer);
    }

    private void breadthFirstSearch(int[][] vector, boolean[] isUsed, int vertex, Consumer<Integer> consumer) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);

        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            if(!isUsed[v]) {
                isUsed[v] = true;
                consumer.accept(v);
                for (int i = 0; i < vector[v].length; i++) {
                    queue.add(vector[v][i]);
                }
            }
        }
    }

    public void depthFirstSearch(int index, Consumer<Integer> consumer) {
        Arrays.fill(isUsed, false);
        depthFirstSearch(adjacencyVector, isUsed, index, consumer);
    }

    private void depthFirstSearch(int[][] vector, boolean[] isUsed, int vertex, Consumer<Integer> consumer) {
        if(!isUsed[vertex]) {
            isUsed[vertex] = true;
            consumer.accept(vertex);
            for (int i = 0; i < vector[vertex].length; i++) {
                depthFirstSearch(vector, isUsed, vector[vertex][i], consumer);
            }
        }
    }

}
