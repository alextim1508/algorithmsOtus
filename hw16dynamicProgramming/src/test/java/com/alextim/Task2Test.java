package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.alextim.Task2.task2;

public class Task2Test {

    @Test
    public void test() {
        List<String> in = Arrays.asList(
                "4",
                "   1",
                "  2 3",
                " 4 5 6",
                "9 8 0 3");

        Assertions.assertEquals("17", task2(in));
    }
}
