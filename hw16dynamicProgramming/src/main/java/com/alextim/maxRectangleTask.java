package com.alextim;

import java.util.Arrays;
import java.util.List;

public class maxRectangleTask {

    static String task(List<String> strList) {
        int maxSquare = Integer.MIN_VALUE;
        int[][] map = parse(strList);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 0) {
                    int square = findMaxRectangleSquare(map, i, j);
                    if(maxSquare < square)
                        maxSquare = square;
                }
            }
        }
        return Integer.toString(maxSquare);
    }

    private static int findMaxRectangleSquare(int[][] map, int i, int j) {
        int maxSquare = Integer.MIN_VALUE;
        int limit = Integer.MAX_VALUE;

        for (int column = i; column < map.length; column++) {
            int wayLength = findWayLength(map, column, j);
            if(limit > wayLength)
                limit = wayLength;

            int square = limit * (column - i + 1);
            if(square > maxSquare) {
                maxSquare = square;
            }
        }
        return maxSquare;
    }

    private static int findWayLength(int[][] map, int i, int j) {
        for (int row = j; row < map[i].length; row++) {
            if(map[i][row] == 1) {
                return row - j;
            }
        }
        return map[i].length - j;
    }

    private static int[][] parse(List<String> strList) {
        return strList.stream().map(
                s -> Arrays.stream(s.split("")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
    }
}
