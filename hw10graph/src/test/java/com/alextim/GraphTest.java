package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class GraphTest {

    private final int[][] vectors = {
            {1},
            {2, 4, 5},
            {3, 6},
            {2, 7},
            {0, 5},
            {6},
            {5},
            {3, 6}
    };

    private Graph graph;

    @BeforeEach
    public void test() {
        graph = new Graph(vectors);
    }

    @Test
    public void strongConnectivityComponentsSearchTest() {
        int[][] expected = {
                {6, 5},
                {7, 3, 2},
                {4, 1, 0}
        };

        int[][] strongConnectivityComponents = graph.strongConnectivityComponentsSearch("Kosarayu");

        for (int i = 0; i < expected.length; i++) {
            Assertions.assertArrayEquals(expected[i], strongConnectivityComponents[i]);
        }
    }

    @Test
    public void depthFirstSearchTest() {
        AtomicInteger count = new AtomicInteger();
        graph.depthFirstSearch(0, i -> count.incrementAndGet());
        Assertions.assertEquals(vectors.length, count.get());
    }

    @Test
    public void breadthFirstSearchTest() {
        AtomicInteger count = new AtomicInteger();
        graph.breadthFirstSearch(0, i -> count.incrementAndGet());
        Assertions.assertEquals(vectors.length, count.get());
    }

    @Test
    public void getVectorInvert() {
        int[][] exactedInvertVector = {
                {4},
                {0},
                {1, 3},
                {2, 7},
                {1},
                {1, 4, 6},
                {2, 5, 7},
                {3}
        };

        int[][] invert = graph.getAdjacencyVectorInvert();

        for (int i = 0; i < invert.length; i++) {
            Assertions.assertArrayEquals(exactedInvertVector[i], invert[i]);
        }
    }
}
