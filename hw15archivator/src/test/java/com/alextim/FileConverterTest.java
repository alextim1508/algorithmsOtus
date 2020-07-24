package com.alextim;

import com.google.common.io.Files;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FileConverterTest {
    File fileIn = new File(".\\1.jpg");
    File fileOut = new File(".\\1.jpg.rle");
    File fileBack = new File( ".\\1.jpg_");

    FileConverter converter = new FileConverter();

    @BeforeEach
    void setUp() {
        if(fileOut.exists())
            fileOut.delete();

        if(fileBack.exists())
            fileBack.delete();
    }

    @AfterEach
    void tearDown() {
        fileOut.delete();
        fileBack.delete();
    }

    @Test
    public void simpleRleFileConvertTest() throws IOException {
        converter.simpleRleFileConvert(fileIn, fileOut);
        converter.fromSimpleRleFileConvert(fileOut, fileBack);

        Assertions.assertTrue(Files.equal(fileIn, fileBack));
    }


    @Test
    public void improvedRleFileConvertWithReplayCountMax1Test() throws IOException {
        converter.improvedRleFileConvert(fileIn, fileOut, (byte) 1);
        converter.fromImprovedRleFileConvert(fileOut, fileBack);

        Assertions.assertTrue(Files.equal(fileIn, fileBack));
    }

    @Test
    public void improvedRleFileConvertWithReplayCountMax2Test() throws IOException {
        converter.improvedRleFileConvert(fileIn, fileOut, (byte) 2);
        converter.fromImprovedRleFileConvert(fileOut, fileBack);

        Assertions.assertTrue(Files.equal(fileIn, fileBack));
    }
}
