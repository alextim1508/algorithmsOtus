package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ArrayTest {

    private static int size = 100;

    @ParameterizedTest
    @MethodSource("getArgs")
    public void endAdditionTest(Array<Integer> array) {
        array.add(9);
        Assertions.assertEquals(new Integer(9), array.get(array.size()-1));
        Assertions.assertEquals(size+1, array.size());
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    public void midAdditionTest(Array<Integer> array) {
        array.add(0, 3);
        Assertions.assertEquals(new Integer(0), array.get(3));
        Assertions.assertEquals(new Integer(3), array.get(4));

        array.add(1, 13);
        Assertions.assertEquals(new Integer(1), array.get(13));
        Assertions.assertEquals(new Integer(12), array.get(14));

        array.add(9, 93);
        Assertions.assertEquals(new Integer(9), array.get(93));
        Assertions.assertEquals(new Integer(91), array.get(94));
        Assertions.assertEquals(size + 3, array.size());
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    public void removeTest(Array<Integer> array) {
        array.remove(4);
        Assertions.assertEquals(new Integer(5), array.get(4));
        Assertions.assertEquals( size - 1, array.size());
    }

    public static Stream<Arguments> getArgs() {
        Array<Integer> singleArray = new SingleArray<>();
        Array<Integer> vectorArray = new VectorArray<>(3);
        Array<Integer> factorArray = new FactorArray<>(3);
        Array<Integer> matrixArray = new MatrixArray<>(new VectorArray<>(10), 10);
        Array<Object> spaceArray = new SpaceArray<>(new VectorArray<>(10), 10);
        for (int i = 0; i < size; i++) {
            singleArray.add(i);
            vectorArray.add(i);
            factorArray.add(i);
            matrixArray.add(i);
            spaceArray.add(i);
        }

        return Stream.of(
                arguments(singleArray),
                arguments(vectorArray),
                arguments(factorArray),
                arguments(matrixArray),
                arguments(spaceArray));
    }
}
