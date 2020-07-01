package com.alextim;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistancedGraphTest {

    int[][] adjacencyVector = {
            {1, 2, 5},
            {0, 2, 3},
            {0, 1, 3, 5},
            {1, 2, 4},
            {3, 5},
            {0, 2, 4}
    };

    Edge[] edges = {
            new Edge(0, 1, 7),
            new Edge(0, 2, 9),
            new Edge(0, 5, 14),
            new Edge(1, 2, 10),
            new Edge(1, 3, 15),
            new Edge(2, 3, 11),
            new Edge(2, 5, 2),
            new Edge(3, 4, 6),
            new Edge(4, 5, 9)
    };

    Edge[] expected = {
            new Edge(4, 5, 9),
            new Edge(5, 2, 2),
            new Edge(2, 0, 9)
    };

    @Test
    public void getShortestWayTest() {
        Edge[] shortestWay = new DistancedGraph(adjacencyVector, edges).getShortestWay(0, 4);
        Assertions.assertArrayEquals(shortestWay, expected);
    }
}
