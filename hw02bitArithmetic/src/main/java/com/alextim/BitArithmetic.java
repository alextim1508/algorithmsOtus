package com.alextim;

public class BitArithmetic {

    public static int countOne(long n) {
        int count = 0;
        for(int i=0; i<64; i++) {
            if((1 & (n>>i)) == 1)
                count++;
        }
        return count;
    }

    public static long getKingMask(int position) {
        long k = 1L <<position;

        long notA = 0xFEFEFEFEFEFEFEFEL;
        long notH = 0x7F7F7F7F7F7F7F7FL;

        long p9 = (k & notH) << 9;
        long p8 = k << 8;
        long p7 = (k & notA) << 7;
        long p6 = (k & notH) << 1;
        long p5 = 0;
        long p4 = (k & notA) >>> 1;
        long p3 = (k & notH)>>> 7;
        long p2 = k >>> 8;
        long p1 = (k & notA) >>> 9;

        return
                p7 | p8 | p9 |
                        p4 | p5 | p6 |
                        p1 | p2 | p3 ;
    }

    public static long getKnightMask(int position) {
        long k = 1L <<position;

        long notAB = 0xFCFCFCFCFCFCFCFCL;
        long notGH = 0x3F3F3F3F3F3F3F3FL;
        long notA = 0xFEFEFEFEFEFEFEFEL;
        long notH = 0x7F7F7F7F7F7F7F7FL;

        long p24 = (k & notH) << 17;
        long p22 = (k & notA) << 15;
        long p20 = (k & notGH) << 10;
        long p16 = (k & notAB) << 6;
        long p13 = 0;
        long p10 = (k & notGH) >>> 6;
        long p6 = (k & notAB) >>> 10;
        long p4 = (k & notH) >>> 15;
        long p2 = (k & notA) >>> 17;

        return
                /**/ p22| /**/ p24| /**/
                p16| /**/ /**/ /**/ p20|
                /**/ /**/ p13| /**/ /**/
                p6 | /**/ /**/ /**/ p10|
                /**/ p2 | /**/ p4; /**/
    }
}
