package com.alextim;

public class Task3 {

    static String task3(String str) {
        int n = Integer.parseInt(str);
        if(n == 1)
            return String.format("%d", 2);
        else if(n == 2)
            return String.format("%d", 4);
        else {
            long f55 =1, f59 =1, f95 =1, f99 =1;
            for (int i = 3; i <= n; i++) {

                long n55 = f59;
                long n59 = f95 + f99;
                long n95 = f55 + f59;
                long n99 = f95;

                f55 = n55;
                f59 = n59;
                f95 = n95;
                f99 = n99;
            }
            return String.format("%d", f55 + f59 + f95 + f99);
        }
    }
}
