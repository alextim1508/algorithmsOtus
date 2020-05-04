package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

    int[][] vectors = {
            {2, 12},
            {12},
            {},
            {2},
            {2, 8, 9},
            {3, 10, 11},
            {10},
            {1, 3, 5, 6},
            {0, 13},
            {0, 11},
            {2},
            {},
            {2},
            {5}
    };

    private Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph(vectors);
    }

    @Test
    public void demucronTest() {
        int[][] expected = {
                {4, 7},
                {1, 6, 8, 9},
                {0, 13},
                {5, 12},
                {3, 10, 11},
                {2}
        };

        int[][] sorted = graph.sort("Demucron");

        for (int i = 0; i < expected.length; i++) {
            Assertions.assertArrayEquals(expected[i], sorted[i]);
        }
    }
}
