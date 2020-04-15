package com.alextim;

public class PrimeNumberScanner {

    private final int RANGE;
    private static final double EXPERIMENTALLY_COEFFICIENT = 1.95;
    private int primeNumbersCount;
    private final int[] primeNumbers;

    public PrimeNumberScanner(int range) {
        RANGE = range;
        primeNumbers = new int[getPrimeNumberMaxCount(range)];
        primeNumbersCount = 0;
    }

    public int getPrimeNumberCount() {
        int count=0;
        for(int i = 2; i<=RANGE; i++) {
            if(isPrimeNumber(i)) {
                count++;
            }
        }
        return count;
    }

    public int getPrimeNumberCountWithOptimizations() {
        int count=0;
        for(int i = 2; i<=RANGE; i++) {
            if(isPrimeNumberWithOptimizations(i)) {
                count++;
            }
        }
        return count;
    }

    private void addPrimeNumberToArray(int primeNumber) {
        primeNumbers[primeNumbersCount++] = primeNumber;
    }

    boolean isPrimeNumberWithOptimizations(int p) {
        if(p == 2) {
            addPrimeNumberToArray(p);
            return true;
        }

        if(p%2 == 0) {
            return false;
        }

        for(int i = 0; primeNumbers[i]*primeNumbers[i]<=p; i++) {
            if (p % primeNumbers[i] == 0) {
                return false;
            }
        }

        addPrimeNumberToArray(p);
        return true;
    }

    boolean isPrimeNumber(int n) {
        for(int i = 2; i<n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static int getPrimeNumberMaxCount(int range) {
        return (int)(range / (getExponentNumber(range) * EXPERIMENTALLY_COEFFICIENT));
    }

    static int getExponentNumber(int i) {
        int exponent = 0;
        while (i / 10 != 0) {
            i /= 10;
            exponent++;
        }
        return exponent;
    }

    void clearPrimeNumbers() {
        primeNumbersCount = 0;
    }
}
