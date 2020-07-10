package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.alextim.Task1.task1;

public class Task1Test {

    @Test
    public void test() {
        Assertions.assertEquals("1/20", task1("2/100+3/100"));
    }

}
