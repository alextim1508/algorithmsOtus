package com.alextim;

import com.alextim.maxRectangleTaskDynamic.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.alextim.maxRectangleTaskDynamic.*;


public class maxRectangleTaskDynamicTest {

    @Test
    public void taskTest() {
        List<String> in = Arrays.asList(
        "10 10",
        "1 7",
        "0 7",
        "0 9",
        "2 9",
        "3 4",
        "3 5",
        "5 0",
        "6 8",
        "3 7",
        "1 2",
        "2 1",
        "3 0",
        "1 6",
        "6 9",
        "0 5",
        "0 6",
        "2 8",
        "9 9",
        "4 4",
        "7 2");

        task(in);
    }


    @Test
    public void test1() {
        Set<maxRectangleTaskDynamic.Coord> coord = Set.of(
                new Coord(1, 7),
                new Coord(0, 7),
                new Coord(0, 9),
                new Coord(2, 9),
                new Coord(3, 4),
                new Coord(3, 5),
                new Coord(5, 0),
                new Coord(6, 8),
                new Coord(3, 7),
                new Coord(1, 2),
                new Coord(2, 1),
                new Coord(3,0),
                new Coord(1, 6),
                new Coord(6, 9),
                new Coord(0, 5),
                new Coord(0, 6),
                new Coord(2, 8),
                new Coord(9, 9),
                new Coord(4, 4),
                new Coord(7, 2));

        Assertions.assertArrayEquals(new int[] {4, 7, 2, 10, 5, 6, 8, 6, 3, 0}, getRectangleLengths(coord, 10, 10)[9]);
    }


    @Test
    public void getLeftLimitTest() {
        int[] row = new int[] {1, 1, 1, 4, 4, 2, 3, 3, 2, 2};
        Assertions.assertArrayEquals(new int[]{0, 0, 0, 3, 3, 3, 6, 6, 3, 3}, getLeftLimit(row));
    }

    @Test
    public void getRightLimitTest() {
        int[] row = new int[] {1, 1, 1, 4, 4, 2, 3, 3, 2, 2};
        Assertions.assertArrayEquals(new int[]{9, 9, 9, 4, 4, 9, 7, 7, 9, 9}, getRightLimit(row));
    }

}
