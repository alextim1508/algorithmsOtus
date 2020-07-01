package com.alextim;

import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.alextim.FileSort.generateRandomNumbersFile;
import static com.alextim.FileSort.sort;
import static com.alextim.QuickSort.quickSort;

public class LongSortTest {

    private int AMOUNT = 10_000_000;
    private String fileIn = ".//in.txt";
    private String fileOut = ".//out.txt";

    @Test
    public void generateFile() throws IOException {
        generateRandomNumbersFile(fileIn, AMOUNT);
    }

    @Test
    public void sortTest() throws IOException {
        sort(new File(fileIn), new File(fileOut), 4 * 1_00, array -> quickSort(array));
    }

    @Test
    public void checkSortedFile() throws IOException {
        try (FileInputStream in = new FileInputStream(fileOut)){
            byte[] buf = new byte[Integer.BYTES];
            int count = 0;
            int previous = Integer.MIN_VALUE;
            while (in.read(buf) != -1){
                int current = Ints.fromByteArray(new byte[]{buf[0], buf[1],buf[2],buf[3]});
                Assertions.assertTrue(previous <= current);
                previous = current;
                count++;
            }
            Assertions.assertEquals(AMOUNT, count);
        }
    }
}

