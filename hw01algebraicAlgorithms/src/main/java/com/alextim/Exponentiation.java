package com.alextim;

public class Exponentiation {

    public int simple(int base, int power) {
        int a = 1;
        for (int i = 0; i<power; i++)
            a *= base;
        return a;
    }

    public int withHelpPowerOf2(int base, int power) {
        final int base0 = base;
        int powerOf2 = (int)(Math.log(power) / Math.log(2));

        for (int i = 0; i<powerOf2; i++)
            base *= base;

        double deltaPower = power - (int)Math.pow(2, powerOf2);

        for(int i=0; i<deltaPower; i++) {
            base *= base0;
        }
        return base;
    }

    public int withHelpBinaryDecompositionOfPower(int base, int power) {
        int res = 1;
        while (power != 0) {
            if(power % 2 != 0) {
                res *= base;
            }
            base *= base;
            power /=2;
        }
        return res;
    }
}
