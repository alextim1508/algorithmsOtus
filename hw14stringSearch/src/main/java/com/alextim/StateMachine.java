package com.alextim;

public class StateMachine {

    public static int search(String text, String pattern) {
        int[][] delta = computeDelta(pattern);
        int p =0;
        for (int i = 0; i < text.length(); i++) {
            p = delta[p][text.charAt(i) - 'A'];
            if(p == pattern.length())
                return i - pattern.length() + 1;
        }

        return -1;
    }

    private static int[][] computeDelta(String pattern) {
        int[][] delta = new int[pattern.length()][4];

        for (int p = 0; p < pattern.length(); p++) {
            for (char a = 'A'; a <= 'D'; a++) {
                String line = left(pattern, p) + a;

                int k = p + 1;
                while (! left(pattern, k).equals(right(line, k))) {
                    k--;
                }

                delta[p][a-'A'] = k;
            }
        }

        return delta;
    }

    static String left(String text, int a) {
        return text.substring(0, a);
    }

    static String right(String text, int a) {
        return text.substring(text.length() - a, text.length());
    }
}
