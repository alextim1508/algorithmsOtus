package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.alextim.Rle.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RleTest {

    @Test
    public void simpleRleTest() {
        byte[] source = {1, 1, 1, 2, 3, 4, 4, 4, 5, 6, 7, 7, 8, 9, 9, 0, 4, 3, 7, 7, 7};

        byte[] expectedRle = new byte[] {3, 1, 1, 2, 1, 3, 3, 4, 1, 5, 1, 6, 2, 7, 1, 8, 2, 9, 1, 0, 1, 4, 1, 3, 3, 7};

        byte[] rle = simpleRleConvert(source, source.length);
        Assertions.assertArrayEquals(expectedRle, rle);

        Assertions.assertArrayEquals(source, simpleRleConvertBack(rle, rle.length));
    }


    @Test
    public void compressedSimpleRleWithReplayCountMax1() throws NotFullyProcessedException {
        byte[] source = {1, 1, 1, 2, 3, 4, 4, 4, 5, 6, 7, 7, 8, 9, 9, 0, 4, 3, 7, 7, 7};

        byte[] simpleRle = {3, 1, 1, 2, 1, 3, 3, 4, 1, 5, 1, 6, 2, 7, 1, 8, 2, 9, 1, 0, 1, 4, 1, 3, 3, 7};
        byte[] expectedCompressedRle = new byte[] {3, 1, -2, 2, 3, 3, 4, -2, 5, 6, 2, 7, -1, 8, 2, 9, -3, 0, 4, 3, 3, 7};

        byte[] compressedRle = improvedRleConvert(simpleRle, (byte)1);
        Assertions.assertArrayEquals(expectedCompressedRle, compressedRle);

        Assertions.assertArrayEquals(source, improvedRleConvertBack(compressedRle, compressedRle.length));
    }

    @Test
    public void compressedSimpleRleWithReplayCountMax2() throws NotFullyProcessedException {
        byte[] source = {1, 1, 1, 2, 3, 4, 4, 4, 5, 6, 7, 7, 8, 9, 9, 0,     4, 3, 7, 7, 7};

        byte[] simpleRle = {3, 1, 1, 2, 1, 3, 3, 4, 1, 5, 1, 6, 2, 7, 1, 8, 2, 9, 1, 0, 1, 4, 1, 3, 3, 7};
        byte[] expectedCompressedRle = new byte[] {3, 1, -2, 2, 3, 3, 4, -10, 5, 6, 7, 7, 8, 9, 9, 0, 4, 3, 3, 7};

        byte[] compressedRle = improvedRleConvert(simpleRle, (byte) 2);
        Assertions.assertArrayEquals(expectedCompressedRle, compressedRle);

        Assertions.assertArrayEquals(source, improvedRleConvertBack(compressedRle, compressedRle.length));
    }


    @Test
    public void getNotFullyProcessedExceptionByNegativeHeaderTest()  {
        byte[] expectedFragmentSource = {1, 1, 1, 2, 3, 4, 4, 4};

        byte[] compressedRleFragment = new byte[] {3, 1, -2, 2, 3, 3, 4, -10, 5, 6, 7, 7, 8, 9, 9, 0, 0};

        NotFullyProcessedException thrown = assertThrows(NotFullyProcessedException.class, () -> {
            improvedRleConvertBack(compressedRleFragment, compressedRleFragment.length);
        });

        Assertions.assertArrayEquals(expectedFragmentSource, thrown.processedArray);
        Assertions.assertEquals(7, thrown.compressedRleArrayIndex);
    }

    @Test
    public void getNotFullyProcessedExceptionByPositiveHeaderTest()  {
        byte[] expectedFragmentSource = {1, 1, 1, 2, 3};

        byte[] compressedRleFragment = new byte[] {3, 1, -2, 2, 3, 3};

        NotFullyProcessedException thrown = assertThrows(NotFullyProcessedException.class, () -> {
            improvedRleConvertBack(compressedRleFragment, compressedRleFragment.length);
        });

        Assertions.assertArrayEquals(expectedFragmentSource, thrown.processedArray);
        Assertions.assertEquals(5, thrown.compressedRleArrayIndex);
    }
}
