package com.alextim;

import java.util.Arrays;

public class PrefixSuffixSearch {

    static int search0(String text, String pattern) {
        int t = 0;
        while (t <= text.length() - pattern.length()) {
            int p = 0;
            while (p < pattern.length() && text.charAt(t+p) == pattern.charAt(p)) {
                p++;
            }

            if(p == pattern.length())
                return t;
            t = t + 1;
        }
        return -1;
    }

    public static int search1(String text, String pattern) {
        int[] prefix = createPrefix(pattern);
        int t = 0;
        while (t <= text.length() - pattern.length()) {
            int p = pattern.length() -1;

            while (p >=0 && text.charAt(t+p) == pattern.charAt(p)) {
                p--;
            }

            if(p == -1)
                return t;

            t += prefix[text.charAt(t + p)];
        }
        return -1;
    }

    public static int search2(String text, String pattern) {
        int[] prefix = createPrefix(pattern);
        int[] suffix = createSuffix(pattern);
        int t = 0;
        while (t <= text.length() - pattern.length()) {
            int p = pattern.length() -1;
            while (p >=0 && text.charAt(t+p) == pattern.charAt(p)) {
                p--;
            }

            if(p == -1)
                return t;

            t += Math.max(prefix[text.charAt(t + p)], suffix[p + 1]);
        }
        return -1;
    }

    public static int[] createPrefix(String pattern) {
        int[] prefix = new int[128];
        Arrays.fill(prefix, pattern.length());

        for (int i = 0; i < pattern.length() - 1; i++) {
            prefix[pattern.charAt(i)] = pattern.length()-1 -i;
        }
        prefix[pattern.charAt(pattern.length()-1)] = pattern.length();
        return prefix;
    }

    public static int[] createSuffix(String pattern) {
        int[] suffix = new int[pattern.length() + 1];
        Arrays.fill(suffix, pattern.length());
        suffix[pattern.length()] = 1;

        for (int j = pattern.length() -1; j >= 0; j--) {
M:          for (int at = j; at < pattern.length(); at++) {
                String a = pattern.substring(at);
                for (int i = at -1; i >=0 ; i--) {
                    String b = pattern.substring(i, i+a.length());
                    if(a.equals(b)) {
                        suffix[j] = at - 1;
                        break M;
                    }
                }
            }
        }
        return suffix;
    }
}
