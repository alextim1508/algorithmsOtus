package com.alextim;

import java.util.Arrays;
import java.util.List;

public class Task4 {

    static String task4(List<String> strList) {
        int islands= 0;
        int[][] map = parse(strList);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 1) {
                    islands++;
                    drown(i, j, map);
                }
            }
        }
        return String.format("%d", islands);
    }

    private static void drown(int i, int j, int[][] map) {
        if(i < 0 || i >= map.length)
            return;
        if(j < 0 || j >= map[i].length)
            return;

        if(map[i][j] != 0) {
            map[i][j] = 0;
            drown(i - 1, j, map);
            drown(i + 1, j, map);
            drown(i , j - 1, map);
            drown(i , j + 1, map);
        }
    }

    private static int[][] parse(List<String> strList) {
        return strList.stream().skip(1).map(
                    s -> Arrays.stream(s.split("")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
    }
}
