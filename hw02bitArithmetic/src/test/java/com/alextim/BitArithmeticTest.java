package com.alextim;

import org.joou.ULong;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.alextim.BitArithmetic.countOne;
import static com.alextim.BitArithmetic.getKingMask;
import static com.alextim.BitArithmetic.getKnightMask;

public class BitArithmeticTest {

    @Test
    public void kingTest(){
        long mask = getKingMask(1);
        Assertions.assertEquals(1797, mask);
        Assertions.assertEquals(5, countOne(mask));

        mask = getKingMask(7);
        Assertions.assertEquals(49216, mask);
        Assertions.assertEquals(3, countOne(mask));

        mask = getKingMask(8);
        Assertions.assertEquals(197123, mask);
        Assertions.assertEquals(5, countOne(mask));

        mask = getKingMask(10);
        Assertions.assertEquals(920078, mask);
        Assertions.assertEquals(8, countOne(mask));

        mask = getKingMask(54);
        Assertions.assertEquals(ULong.valueOf("16186183351374184448").longValue(), mask);
        Assertions.assertEquals(8, countOne(mask));

        mask = getKingMask(55);
        Assertions.assertEquals(ULong.valueOf("13853283560024178688").longValue(), mask);
        Assertions.assertEquals(5, countOne(mask));

        mask = getKingMask(56);
        Assertions.assertEquals(ULong.valueOf("144959613005987840").longValue(), mask);
        Assertions.assertEquals(3, countOne(mask));

        mask = getKingMask(63);
        Assertions.assertEquals(ULong.valueOf("4665729213955833856").longValue(), mask);
        Assertions.assertEquals(3, countOne(mask));
    }

    @Test
    public void knighTest(){
        long mask = getKnightMask(0);
        Assertions.assertEquals(132096, mask);
        Assertions.assertEquals(2, countOne(mask));

        mask = getKnightMask(1);
        Assertions.assertEquals(329728, mask);
        Assertions.assertEquals(3, countOne(mask));

        mask = getKnightMask(2);
        Assertions.assertEquals(659712, mask);
        Assertions.assertEquals(4, countOne(mask));

        mask = getKnightMask(36);
        Assertions.assertEquals(ULong.valueOf("11333767002587136").longValue(), mask);
        Assertions.assertEquals(8, countOne(mask));

        mask = getKnightMask(47);
        Assertions.assertEquals(ULong.valueOf("4620693356194824192").longValue(), mask);
        Assertions.assertEquals(4, countOne(mask));

        mask = getKnightMask(48);
        Assertions.assertEquals(ULong.valueOf("288234782788157440").longValue(), mask);
        Assertions.assertEquals(3, countOne(mask));

        mask = getKnightMask(54);
        Assertions.assertEquals(ULong.valueOf("1152939783987658752").longValue(), mask);
        Assertions.assertEquals(4, countOne(mask));

        mask = getKnightMask(55);
        Assertions.assertEquals(ULong.valueOf("2305878468463689728").longValue(), mask);
        Assertions.assertEquals(3, countOne(mask));

        mask = getKnightMask(56);
        Assertions.assertEquals(ULong.valueOf("1128098930098176").longValue(), mask);
        Assertions.assertEquals(2, countOne(mask));

        mask = getKnightMask(63);
        Assertions.assertEquals(ULong.valueOf("9077567998918656").longValue(), mask);
        Assertions.assertEquals(2, countOne(mask));
    }
}
