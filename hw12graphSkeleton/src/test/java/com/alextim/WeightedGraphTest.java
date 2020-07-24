package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WeightedGraphTest {

    int[][] adjacencyVector = {
            {1, 3},
            {0, 2, 3, 4},
            {1, 4},
            {0, 1, 4, 5},
            {1, 2, 3, 5, 6},
            {3, 4, 6},
            {4, 5}
    };

    Edge[] edges = {
            new Edge(0, 1, 7),
            new Edge(0, 3, 5),
            new Edge(1, 2, 8),
            new Edge(1, 3, 9),
            new Edge(1, 4, 7),
            new Edge(2, 4, 5),
            new Edge(3, 4, 15),
            new Edge(3, 5, 6),
            new Edge(4, 5, 8),
            new Edge(4, 6, 9),
            new Edge(5, 6, 11),
    };

    List<Edge> expected = Arrays.asList(
            new Edge(0, 3, 5),
            new Edge(2, 4, 5),
            new Edge(3, 5, 6),
            new Edge(1, 4, 7),
            new Edge(0, 1, 7),
            new Edge(4, 6, 9)
            );

    @Test
    public void primTest() {
        Set<Edge> actualEdges = new WeightedGraph(adjacencyVector, edges).getSkeleton("prim").getEdges();
        Assertions.assertEquals(expected.size(), actualEdges.size());
        actualEdges.forEach(edge -> Assertions.assertTrue(expected.contains(edge)));
    }

    @Test
    public void boruvkaTest() {
        Set<Edge> actualEdges = new WeightedGraph(adjacencyVector, edges).getSkeleton("boruvka").getEdges();
        Assertions.assertEquals(expected.size(), actualEdges.size());
        actualEdges.forEach(edge -> Assertions.assertTrue(expected.contains(edge)));
    }

    @Test
    public void kraskalTest() {
        Set<Edge> actualEdges =new WeightedGraph(adjacencyVector, edges).getSkeleton("kraskal").getEdges();
        Assertions.assertEquals(expected.size(), actualEdges.size());
        actualEdges.forEach(edge -> Assertions.assertTrue(expected.contains(edge)));
    }

   
}
