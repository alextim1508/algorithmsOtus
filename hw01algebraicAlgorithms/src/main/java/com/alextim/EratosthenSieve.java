package com.alextim;

import java.util.Arrays;

public class EratosthenSieve {

    private final int RANGE;
    private final boolean isPrimeNumbers[];
    private final int isOptPrimeNumber[];

    private final int[] leastPrime;
    private final int[] primeNumbers;
    private int primeNumbersCount;

    public EratosthenSieve(int range) {
        this.RANGE = range;

        this.isPrimeNumbers = new boolean[range];
        Arrays.fill(isPrimeNumbers, true);

        this.isOptPrimeNumber =  new int[(int)Math.ceil(range/32.)];
        Arrays.fill(isOptPrimeNumber, 0xFFFFFFFF);

        leastPrime = new int[getPrimeNumberCount()*RANGE];
        primeNumbers = new int[getPrimeNumberCount()];
        primeNumbersCount = 0;
    }

    public int getPrimeNumberCount() {
        for(int i = 2; i*i<= RANGE; i++) {
            if(isPrimeNumbers[i]) {
                for(int j = i*i; j< RANGE; j +=i) {
                    isPrimeNumbers[j] = false;
                }
            }
        }

        int count = 0;
        for(int i = 2; i< RANGE; i++) {
            if(isPrimeNumbers[i]){
                count++;
            }
        }
        return count;
    }

    public int getPrimeNumberCountWithMemoryOptimizations() {
        for(int i = 2; i*i<= RANGE; i++) {
            int byteNumber =  i / 32;
            int bitNumber = i % 32;

            if(checkBit(isOptPrimeNumber[byteNumber], bitNumber)) {
                for(int j = i*i; j< RANGE; j +=i) {
                    byteNumber = j / 32;
                    bitNumber = j % 32;

                    isOptPrimeNumber[byteNumber] = resetBit(isOptPrimeNumber[byteNumber], bitNumber);
                }
            }
        }

        int count = 0;
        for(int i = 2; i< RANGE; i++) {
            int byteNumber =  i / 32;
            int bitNumber = i % 32;

            if(checkBit(isOptPrimeNumber[byteNumber], bitNumber)) {
                count++;
            }
        }

        return count;
    }

    public int getPrimeNumberCountWithLineComplexity() {
        for(int i=2; i<RANGE; i++) {
            if(leastPrime[i]==0) {
                 leastPrime[i] = i;
                 primeNumbers[primeNumbersCount++] =i;
            }

            for (int j=0;
                 j< primeNumbersCount && (primeNumbers[j]<= leastPrime[i] && primeNumbers[j]*i<=RANGE);
                 j++) {

                int i1 = primeNumbers[j] * i;
                leastPrime[i1] = primeNumbers[j];
            }
        }

        return primeNumbersCount;
    }

    private boolean checkBit(int number, int bitNumber) {
        return ((number >> bitNumber) & 1) == 1;
    }

    private int resetBit(int number, int bitNumber ){
        number &= ~(1 << bitNumber);
        return number;
    }

}
