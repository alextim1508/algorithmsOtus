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
        Assertions.assertEquals((int)Math.pow(3, 13), exponentiation.simple(3, 13));
    }

    @Test
    public void withHelpPowerOf2Test(){
        Assertions.assertEquals((int)Math.pow(3, 13), exponentiation.withHelpPowerOf2(3, 13));
    }

    @Test
    public void withHelpBinaryDecompositionOfPowerTest(){
        Assertions.assertEquals((int)Math.pow(3, 13), exponentiation.withHelpBinaryDecompositionOfPower(3, 13));
    }
}
