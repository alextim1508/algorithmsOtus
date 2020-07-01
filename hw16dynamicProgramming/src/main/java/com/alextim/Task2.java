package com.alextim;

import java.util.List;

public class Task2 {

    static String task2(List<String> strList) {
        int[][] pyramid = parse(strList);

        for (int i = pyramid.length - 1; i >0 ; i--) {
            for (int j = 0; j < i; j++) {
                int sumLeft = pyramid[i-1][j] + pyramid[i][j];
                int sumRight =  pyramid[i-1][j] + pyramid[i][j+1];
                pyramid[i-1][j] = Math.max(sumLeft, sumRight);
            }
        }

        return String.format("%d",  pyramid[0][0]);
    }

    private static int[][] parse(List<String> strList) {
        int height = Integer.parseInt(strList.get(0));
        int[][] pyramid = new int[height][];

        for (int i = 0; i < height; i++) {
            String[] split = strList.get(i + 1).trim().split(" ");
            pyramid[i] = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                pyramid[i][j] = Integer.parseInt(split[j]);
            }
        }
        return pyramid;
    }
}
