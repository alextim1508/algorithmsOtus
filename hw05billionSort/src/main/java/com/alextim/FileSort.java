package com.alextim;

import com.google.common.primitives.Ints;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Consumer;

public class FileSort {

    private static int index;

    public static void sort(File fileIn, File fileOut, int bufferSize, Consumer<int[]> sort) throws IOException {
        if(bufferSize%4 != 0)
            throw new RuntimeException("bufferSize must be a multiple 4");
        index = 0;
        LinkedList<File> queue = new LinkedList<>();

        try (FileInputStream in = new FileInputStream(fileIn)) {
            byte[] buffer = new byte[bufferSize];

            int size;
            while((size = in.read(buffer)) != -1) {
                if (size != buffer.length) {
                    buffer = Arrays.copyOf(buffer, size);
                }

                int[] array = convertToIntArray(buffer, size);
                sort.accept(array);

                File file = new File(fileIn.getParent() + File.separator + "part" + ++index);
                try (FileOutputStream out = new FileOutputStream(file)) {
                    out.write(convertToBytes(array, array.length));
                    queue.add(file);
                }
            }
        }

        while (queue.size() != 1) {
            File file1 = queue.pop();
            File file2 = queue.pop();

            queue.add( mergeFiles(file1, file2));
            if(!file1.delete() || !file2.delete())
                throw new RuntimeException("delete file error");
        }

        if(!queue.pop().renameTo(fileOut))
            throw new RuntimeException("rename file error");
    }

    static File mergeFiles(File file1, File file2) throws IOException {
        File file = new File(file1.getParent() + File.separator + "part" + ++index);

        try (FileInputStream in1 = new FileInputStream(file1)) {
            try (FileInputStream in2 = new FileInputStream(file2)) {
                try (FileOutputStream out = new FileOutputStream(file)) {

                    byte[] buf1 = new byte[Integer.BYTES];
                    byte[] buf2 = new byte[Integer.BYTES];

                    int size1 = in1.read(buf1);
                    int size2 = in2.read(buf2);

                    while (true) {
                        int a = convertToIntArray(buf1, size1)[0];
                        int b = convertToIntArray(buf2, size2)[0];

                        if(a>b) {
                            out.write(buf2);
                            if(in2.read(buf2) == -1) {
                                out.write(buf1);
                                break;
                            }
                        } else {
                            out.write(buf1);
                            if(in1.read(buf1) == -1) {
                                out.write(buf2);
                                break;
                            }
                        }
                    }

                    while (in1.read(buf1) != -1)
                        out.write(buf1);

                    while (in2.read(buf2) != -1)
                        out.write(buf2);
                }
            }
        }

        return file;
    }

    static int[] convertToIntArray(byte[] buffer, int size) {
        int[] ints = new int[size / Integer.BYTES];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Ints.fromBytes(
                                        buffer[Integer.BYTES*i],
                                        buffer[Integer.BYTES*i +1],
                                        buffer[Integer.BYTES*i +2],
                                        buffer[Integer.BYTES*i +3]);
        }
        return ints;
    }

    static byte[] convertToBytes(int[] buffer, int size) {
        byte[] bytes = new byte[size * Integer.BYTES];
        for (int i = 0; i < size; i++) {
            byte[] byteArray = Ints.toByteArray(buffer[i]);
            bytes[Integer.BYTES*i] = byteArray[0];
            bytes[Integer.BYTES*i +1] = byteArray[1];
            bytes[Integer.BYTES*i +2] = byteArray[2];
            bytes[Integer.BYTES*i +3] = byteArray[3];
        }
        return bytes;
    }

    static void generateRandomNumbersFile(String fileName, long count) throws IOException {
        Random random = new Random();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            for (long i = 0; i < count; i++) {
                out.write(Ints.toByteArray((int) (random.nextLong() - Integer.MAX_VALUE)));
            }
        }
    }
}
