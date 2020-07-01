package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.alextim.maxRectangleTask.task;

public class maxRectangleTaskTest {

    @Test
    public void test1() {
        List<String> map = Arrays.asList(
                "1000",
                "0100",
                "0001",
                "0000");

        Assertions.assertEquals("6", task(map));
    }

    @Test
    public void test2() {
        List<String> map = Arrays.asList(
                "0000",
                "0000",
                "0000",
                "0000");

        Assertions.assertEquals("16", task(map));
    }

    @Test
    public void test3() {
        List<String> map = Arrays.asList(
                "0101",
                "1010",
                "0101",
                "1010");

        Assertions.assertEquals("1", task(map));
    }


}
