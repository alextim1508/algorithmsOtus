package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.alextim.Task4.task4;

public class Task4Test {

    @Test
    public void test() {
        List<String> map = Arrays.asList(
                "4",
                "11111",
                "10011",
                "00000",
                "10011",
                "00001"
        );
        Assertions.assertEquals("3", task4(map));
    }
}