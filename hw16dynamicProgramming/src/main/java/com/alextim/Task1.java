package com.alextim;

public class Task1 {

    static String task1(String str) {
        int[] in = parse(str);
        int a = in[0];
        int b = in[1];
        int c = in[2];
        int d = in[3];

        int div = b * d;
        int sum = a * d + b * c;
        int gcd = gcd(div, sum);

        return String.format("%d/%d",  sum/gcd, div/gcd);
    }

    private static int[] parse(String str) {
        String[] split = str.split("[/\\+]");
        return new int[] {
                Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2]),
                Integer.parseInt(split[3])
        };
    }

    static int gcd(int a, int b) {
        return b ==0 ? a : gcd(b, a%b);
    }
}
