package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ExponentiationTest {

    private static Exponentiation exponentiation;

    @BeforeAll
    public static void setUp() {
        exponentiation = new Exponentiation();
    }

    @Test
    public void simpleTest(){
        Assertions.assertEquals(Math.pow(3, 13), exponentiation.simple(3, 13));
        Assertions.assertEquals(Math.pow(1.000001, 1_000_000), exponentiation.simple(1.000001, 1_000_000), 0.00001);
        Assertions.assertEquals(Math.pow(1.000001, 1_000_000), exponentiation.simple(1.0000001, 10_000_000), 0.00001);
    }

    @Test
    public void withHelpPowerOf2Test(){
        Assertions.assertEquals(Math.pow(3, 13), exponentiation.withHelpPowerOf2(3, 13));
        Assertions.assertEquals(Math.pow(1.000001, 1_000_000), exponentiation.simple(1.000001, 1_000_000), 0.00001);
        Assertions.assertEquals(Math.pow(1.000001, 1_000_000), exponentiation.simple(1.0000001, 10_000_000), 0.00001);
    }

    @Test
    public void withHelpBinaryDecompositionOfPowerTest(){
        Assertions.assertEquals(Math.pow(3, 13), exponentiation.withHelpBinaryDecompositionOfPower(3, 13));
        Assertions.assertEquals(Math.pow(1.000001, 1_000_000), exponentiation.withHelpBinaryDecompositionOfPower(1.000001, 1_000_000), 0.00001);
        Assertions.assertEquals(Math.pow(1.0000001, 10_000_000), exponentiation.withHelpBinaryDecompositionOfPower(1.0000001, 10_000_000), 0.00001);
    }
}
