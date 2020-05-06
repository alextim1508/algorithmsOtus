package com.alextim;

import static com.alextim.BitArithmetic.*;

public class Main {

    public static void main(String[] args) {
        int position = Integer.valueOf(args[1]);
        long mask;

        if(args[0].equals("v1")) {
            mask = getKingMask(position);
        }
        else if(args[0].equals("v2")) {
            mask = getKnightMask(position);
        }
        else {
            System.out.println("Unknown key: " + args[1]);
            return;
        }

        int movesCount = countOne(mask);

        System.out.println(movesCount);
        System.out.println(Long.toUnsignedString(mask));
    }


}
