package com.alextim;

import com.google.common.primitives.Bytes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Rle {

    static byte[] simpleRleConvert(byte[] array, int length) {
        if (array.length > Byte.MAX_VALUE - 1)
            throw new RuntimeException("length must be less 127");

        List<Byte> rle = new ArrayList<>();

        for (byte i = 0; i < length; ) {
            byte j = (byte) (i + 1);

            while (j < length) {
                if (array[i] == array[j])
                    j++;
                else
                    break;
            }

            rle.add((byte) (j - i));
            rle.add(array[i]);
            i = j;
        }

        return Bytes.toArray(rle);
    }

    static byte[] simpleRleConvertBack(byte[] rleArray, int length) {
        if (rleArray.length % 2 != 0)
            throw new RuntimeException("length must be even");

        List<Byte> back = new ArrayList<>();
        for (int i = 0; i < length; ) {
            int replayCount = rleArray[i];
            while (replayCount-- != 0) {
                back.add(rleArray[i + 1]);
            }
            i += 2;
        }
        return Bytes.toArray(back);
    }

    static byte[] improvedRleConvert(byte[] simpleRleArray, byte replayCountMax) {
        List<Byte> compressedRle = new ArrayList<>();

        for (int i = 0; i < simpleRleArray.length; ) {

            int j = i;
            Queue<Byte> replays = new LinkedList<>();
            int header = 0;
            while (j < simpleRleArray.length && simpleRleArray[j] <= replayCountMax) {
                header += simpleRleArray[j];
                replays.add(simpleRleArray[j]);
                j += 2;
            }

            if (i == j) {
                compressedRle.add(simpleRleArray[i]);
                compressedRle.add(simpleRleArray[i + 1]);
                i += 2;
            } else {
                compressedRle.add((byte) ((-1) * header));
                for (int k = i + 1; k <= j; k += 2) {
                    byte replayCount = replays.poll();
                    while (replayCount-- != 0) {
                        compressedRle.add(simpleRleArray[k]);
                    }
                }
                i = j;
            }
        }
        return Bytes.toArray(compressedRle);
    }

    static byte[] improvedRleConvertBack(byte[] compressedRleArray, int length) throws NotFullyProcessedException {
        ArrayList<Byte> back = new ArrayList<>();

        for (int i = 0; i < length; ) {
            int replayCount = compressedRleArray[i];

            if (replayCount > 0) {
                if(i == length - 1)
                    throw new NotFullyProcessedException(i, Bytes.toArray(back));

                while (replayCount-- != 0) {
                    back.add(compressedRleArray[i + 1]);
                }
                i += 2;
            } else {
                if(Math.abs(replayCount) > length - i -1)
                    throw new NotFullyProcessedException(i, Bytes.toArray(back));

                while (replayCount++ != 0) {
                    back.add(compressedRleArray[i++ + 1]);
                }
                i++;
            }
        }
        return Bytes.toArray(back);
    }

    public static class NotFullyProcessedException extends Exception {
        int compressedRleArrayIndex;
        byte[] processedArray;

        public NotFullyProcessedException(int compressedRleArrayIndex, byte[] processedArray) {
            this.compressedRleArrayIndex = compressedRleArrayIndex;
            this.processedArray = processedArray;
        }
    }
}
