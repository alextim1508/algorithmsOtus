package com.alextim;

import java.util.*;
import java.util.stream.Collectors;

public class maxRectangleTaskDynamic {

    static String task(List<String> strList) {
        List<Object> parse = parse(strList);
        int width = (int) parse.get(0);
        int length = (int) parse.get(1);
        Set<Coord> trees = (Set<Coord>) parse.get(2);

        printMap(trees, width, length);

        int[][] rectangleLengths = getRectangleLengths(trees, width, length);

        int maxRectangle = Integer.MIN_VALUE;
        for (int i = 0; i < rectangleLengths.length; i++) {

            int[] lengths = rectangleLengths[i];
            int[] leftLimit = getLeftLimit(lengths);
            int[] rightLimit = getRightLimit(lengths);

            int maxSquare = Integer.MIN_VALUE;
            for (int j = 0; j < lengths.length; j++) {
                int square = (rightLimit[j] - leftLimit[j] + 1) * lengths[j];
                if(maxSquare < square)
                    maxSquare = square;
            }
            System.out.println("Local square by row: " + maxSquare);
            if(maxRectangle < maxSquare)
                maxRectangle = maxSquare;
        }

        return Integer.toString(maxRectangle);
    }

    static int[] getRightLimit(int[] row) {
        int[] right = new int[row.length];
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < row.length; i++) {
            while (stack.size() != 0) {
                if(row[i] < row[stack.peek()])
                    right[stack.pop()] = i - 1;
                else
                    break;
            }
            stack.push(i);
        }

        while (stack.size() != 0)
            right[stack.pop()] = right.length - 1;

        return right;
    }

    static int[] getLeftLimit(int[] row) {
        int[] left = new int[row.length];
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = row.length - 1; i >= 0; i--) {
            while (stack.size() != 0) {
                if(row[i] < row[stack.peek()])
                    left[stack.pop()] = i + 1;
                else
                    break;
            }
            stack.push(i);
        }

        while (stack.size() != 0)
            left[stack.pop()] = 0;

        return left;
    }

    static int[][] getRectangleLengths(Set<Coord> coords, int areaWidth, int areaLength) {
        int[][] lengths = new int[areaLength][];

        int[] length = new int[areaWidth];;
        for (int i = 0; i < areaLength; i++) {
            writeLengths(coords, i, length);
            lengths[i] = Arrays.copyOf(length, length.length);
        }

        return lengths;
    }

    static void writeLengths(Set<Coord> coords, int areaLength, int[] row) {
        for (int j = 0; j < row.length; j++) {
            if(coords.contains(new Coord(areaLength, j)))
                row[j] = 0;
            else
                row[j] ++;
        }
    }

    private static List<Object> parse(List<String> strList) {
        String[] size = strList.get(0).split(" ");
        int width = Integer.parseInt(size[0]);
        int length = Integer.parseInt(size[1]);

        Set<Coord> trees = strList.stream().skip(1).map(s -> {
            String[] xy = s.split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            return new Coord(x, y);
        }).collect(Collectors.toSet());

        return Arrays.asList(width, length, trees);
    }

    private static void printMap(Set<Coord> coords, int width, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(coords.contains(new Coord(i, j)) ? "*" : ".");
            }
            System.out.println();
        }
    }

    static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return x == coord.x &&
                    y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
