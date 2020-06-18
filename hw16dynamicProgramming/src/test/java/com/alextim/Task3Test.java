package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.alextim.Task3.task3;

public class Task3Test {

    @Test
    public void test() {
        Assertions.assertEquals("2", task3("1"));
        Assertions.assertEquals("4", task3("2"));
        Assertions.assertEquals("6", task3("3"));
        Assertions.assertEquals("16", task3("5"));
        Assertions.assertEquals("178", task3("10"));
        Assertions.assertEquals("6832909245813414", task3("75"));
        Assertions.assertEquals("3559958832009428378", task3("88"));

    }
}
