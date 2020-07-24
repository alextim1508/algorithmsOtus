package com.alextim;

import static com.alextim.StateMachine.left;
import static com.alextim.StateMachine.right;

public class KMP {

    public static int search(String text, String pattern) {
        int[] pi = computePiFast(pattern);

        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            while (q >0 && text.charAt(i) != pattern.charAt(q))
                q = pi[q - 1];
            if(text.charAt(i) == pattern.charAt(q))
                q++;
            if(q == pattern.length())
                return i - pattern.length() +1;

        }
        return -1;
    }


    static int[] computePiSlow(String pattern) {
        int[] pi = new int[pattern.length()];
        for (int i = 0; i < pattern.length(); i++) {
            for (int q = 0; q <= i; q++) {
                if(left(pattern, q).equals(right(left(pattern, i + 1), q)))
                    pi[i] = q;
            }
        }
        return pi;
    }

    static int[] computePiFast(String pattern) {
        int[] pi = new int[pattern.length()];
        pi[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int q = pi[i -1];
            while (q > 0 && pattern.charAt(i) != pattern.charAt(q))
                q = pi[q - 1];
            if(pattern.charAt(i) == pattern.charAt(q))
                q++;
            pi[i] = q;
        }
        return pi;
    }
}
