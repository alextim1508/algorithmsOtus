package com.alextim;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static com.alextim.Rle.*;

public class FileConverter {

    public void simpleRleFileConvert(File inFile, File outFile) throws IOException {
        try (RandomAccessFile in = new RandomAccessFile(inFile, "r");
             RandomAccessFile out = new RandomAccessFile(outFile, "rw")) {
            byte[] buffer = new byte[Byte.MAX_VALUE - 1];
            int length;
            while ((length = in.read(buffer, 0, buffer.length)) != -1) {
                byte[] simpleRle = simpleRleConvert(buffer, length);
                out.seek(out.length());
                out.write(simpleRle);
            }
        }
    }

    public void fromSimpleRleFileConvert(File inFile, File outFile) throws IOException {
        try (RandomAccessFile in = new RandomAccessFile(inFile, "r");
             RandomAccessFile out = new RandomAccessFile(outFile, "rw")) {
            byte[] buffer = new byte[Byte.MAX_VALUE - 1];
            int length;

            while ((length = in.read(buffer, 0, buffer.length)) != -1) {
                byte[] back = simpleRleConvertBack(buffer, length);
                out.seek(out.length());
                out.write(back);
            }
        }
    }

    public void improvedRleFileConvert(File inFile, File outFile, byte replayCountMax) throws IOException {
        try (RandomAccessFile in = new RandomAccessFile(inFile, "r");
             RandomAccessFile out = new RandomAccessFile(outFile, "rw")){
            byte[] buffer = new byte[Byte.MAX_VALUE - 1];
            int length;
            while ((length = in.read(buffer, 0, buffer.length)) != -1) {
                byte[] simpleRle = simpleRleConvert(buffer, length);
                byte[] compressedRle = improvedRleConvert(simpleRle, replayCountMax);
                out.seek(out.length());
                out.write(compressedRle);
            }
        }
    }


    void fromImprovedRleFileConvert(File inFile, File outFile) throws IOException {
        try (RandomAccessFile in = new RandomAccessFile(inFile, "r");
             RandomAccessFile out = new RandomAccessFile(outFile, "rw")){
             byte[] buffer = new byte[Byte.MAX_VALUE];
             int length;
             int off = 0;
             int len = buffer.length;

             while ((length = in.read(buffer, off, len)) != -1) {
                 length += off;

                 byte[] back;
                 try {
                     back = improvedRleConvertBack(buffer, length);
                     off = 0;
                     len = buffer.length;
                 } catch (NotFullyProcessedException e) {
                     back = e.processedArray;
                     off = e.compressedRleArrayIndex;

                     System.arraycopy(buffer, off , buffer, 0, length - off);

                     off = length - off ;
                     len = buffer.length - off ;
                 }

                 out.seek(out.length());
                 out.write(back);
             }
        }
    }


}
