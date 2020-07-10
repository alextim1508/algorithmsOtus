package com.alextim;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

public class WeightedGraph extends SortedGraph {

    private Skeleton skeleton;
    private final Edge[] edges;

    public WeightedGraph(int[][] adjacencyVector, Edge[] edges) {
        super(adjacencyVector);
        this.edges = edges;
    }

    public Skeleton getSkeleton(String algorithm) {
        if(algorithm.equals("kraskal")) {
            return kraskal();
        } else if(algorithm.equals("boruvka")) {
            return boruvka();
        } else if(algorithm.equals("prim")) {
            return prim(0);
        } else {
            throw new RuntimeException("Unknown algorithm");
        }
    }

    Skeleton kraskal() {
        int[][] v0 = adjacencyVector;

        Skeleton skeleton = new Skeleton();
        Arrays.sort(edges, Comparator.comparingInt(edge -> edge.weight));

        skeleton.addEdge(edges[0]);

        for (int i=1; skeleton.getVertexes().size() != adjacencyVector.length; i++) {
            adjacencyVector = skeleton.getAdjacencyVector(adjacencyVector.length);

            Edge edge = edges[i];
            AtomicBoolean isCycle = new AtomicBoolean(false);
            breadthFirstSearch(edge.vertex1,
                    j-> {
                        if(j == edge.vertex2)
                            isCycle.set(true);
                    });

            if(!isCycle.get()) {
                skeleton.addEdge(edge);
            }
        }

        adjacencyVector = v0;
        return skeleton;
    }

    Skeleton boruvka() {
        Skeleton skeleton = new Skeleton();
        skeleton.setRoots(new int[adjacencyVector.length]);
        Arrays.sort(edges, Comparator.comparingInt(edge -> edge.weight));

        for (int i=0; skeleton.getVertexes().size() != adjacencyVector.length; i++) {
            Edge edge = edges[i];

            if(skeleton.isRoot(edge.vertex1) && skeleton.isRoot(edge.vertex2)) {
                skeleton.joinRoot(edge.vertex2, edge.vertex1);
                skeleton.addEdge(edge);
            } else if(!skeleton.isRoot(edge.vertex1) && skeleton.isRoot(edge.vertex2)) {
                skeleton.joinRoot(edge.vertex2, edge.vertex1);
                skeleton.addEdge(edge);
            } else if(skeleton.isRoot(edge.vertex1) && !skeleton.isRoot(edge.vertex2)) {
                skeleton.joinRoot(edge.vertex1, edge.vertex2);
                skeleton.addEdge(edge);
            } else if(skeleton.getRoot(edge.vertex1) != skeleton.getRoot(edge.vertex2)) {
                skeleton.join(edge.vertex2, edge.vertex1);
                skeleton.addEdge(edge);
            }
        }
        return skeleton;
    }

    Skeleton prim(int vertex) {
        skeleton = new Skeleton();
        skeleton.addEdge(getEdgeWithMinWeight(vertex, adjacencyVector[vertex]));

        while (skeleton.getVertexes().size() != adjacencyVector.length) {
            Edge edgeWithMinWeight = new Edge(-1, -1, Integer.MAX_VALUE);

            skeleton.getVertexes().forEach(v -> {
                Edge edge = getEdgeWithMinWeight(v, adjacencyVector[v]);
                if(edge != null && edge.weight < edgeWithMinWeight.weight) {
                    edgeWithMinWeight.copyBy(edge);
                }
            });

            skeleton.addEdge(edgeWithMinWeight);
        }
        return skeleton;
    }

    Edge getEdgeWithMinWeight(int v0, int[] vertexes) {
        Edge edgeWithMinWeight = new Edge(v0, -1, Integer.MAX_VALUE);
        for (int v: vertexes) {
            if(!skeleton.isVertexContains(v)) {
                int weight = getWeight(v0, v);
                if(edgeWithMinWeight.weight > weight) {
                    edgeWithMinWeight.weight = weight;
                    edgeWithMinWeight.vertex2 = v;
                }
            }
        }
        return edgeWithMinWeight.vertex2 == -1 ? null : edgeWithMinWeight;
    }

    int getWeight(int v1, int v2) {
        for (Edge e: edges) {
            if((e.vertex1 == v1 && e.vertex2 == v2) || (e.vertex1 == v2 && e.vertex2 == v1))
                return e.weight;
        }
        throw new RuntimeException("Edge with vertexes "  + v1 + " " + v2 + " does not exist");
    }
}
