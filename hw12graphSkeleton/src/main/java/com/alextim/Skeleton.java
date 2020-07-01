package com.alextim;

import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Skeleton {

    private Set<Edge> skeleton = new HashSet<>();

    private int[] roots;

    public int[][] getAdjacencyVector(int adjacencyVectorSize) {
        List<Set<Integer>> adjacencyVector = new ArrayList<>(adjacencyVectorSize);
        for (int i = 0; i < adjacencyVectorSize; i++) {
            adjacencyVector.add(new HashSet<>());
        }

        getEdges().forEach(edge -> {
            adjacencyVector.get(edge.vertex1).add(edge.vertex2);
            adjacencyVector.get(edge.vertex2).add(edge.vertex1);
        });


        return adjacencyVector.stream().map(Ints::toArray).toArray(int[][]::new);
    }

    public void setRoots(int[] roots) {
        this.roots = roots;

        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
    }

    public boolean isRoot(int i) {
        return roots[i] == i;
    }

    public void joinRoot(int root, int newRoot) {
        roots[root] = roots[newRoot];
        for (int i = 0; i < roots.length; i++) {
            if(roots[i] == roots[root])
                roots[i] = roots[newRoot];
        }
    }

    public void join(int i, int newRoot) {
        while (!isRoot(i)) {
            int old = roots[i];
            roots[i] = roots[newRoot];
            i = old;
        }
        roots[i] = roots[newRoot];
    }

    public int getRoot(int i) {
        return roots[i];
    }

    public void addEdge(Edge edge) {
        skeleton.add(edge);
    }

    public Set<Edge> getEdges() {
        Set<Edge> skeletonClone = new HashSet<>();
        skeleton.forEach(edge -> skeletonClone.add(new Edge(edge)));
        return skeletonClone;
    }

    public boolean isVertexContains(int vertex) {
        AtomicBoolean isContains = new AtomicBoolean(false);
        skeleton.forEach(edge -> {
            if(edge.vertex1 == vertex || edge.vertex2 == vertex)
                isContains.set(true);
        });
        return isContains.get();
    }

    public Set<Integer> getVertexes() {
        Set<Integer> vertexes = new HashSet<>();
        skeleton.forEach(edge -> {
            vertexes.add(edge.vertex1);
            vertexes.add(edge.vertex2);
        });
        return vertexes;
    }

}
