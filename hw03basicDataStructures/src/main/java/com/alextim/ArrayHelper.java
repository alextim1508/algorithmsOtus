package com.alextim;

public class ArrayHelper {

    private ArrayHelper() {
    }

    public static void move(Object[] array, int index, int count, String direction) {
        if(direction.equals("left")) {
            System.arraycopy(
                    array, index + count,
                    array, index, array.length - count - index);
        } else if(direction.equals("right")) {
            System.arraycopy(
                    array, index,
                    array, index + count, array.length - index - count );
        } else
            throw new IllegalArgumentException("Unknown " + direction);
    }

    public static <T> T[] resize (T[] array, int delta) {
        T[] newArray = (T[])new Object[array.length + delta];
        System.arraycopy(array, 0, newArray, 0, array.length + (delta > 0 ? 0 : delta));

        return newArray;
    }
}
