package com.alextim;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Integer> in = Arrays.stream(args).skip(1).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        if(args[0].equals("v11")) {
            Exponentiation exponentiation = new Exponentiation();
            System.out.print(exponentiation.simple(in.get(0), in.get(1)));
        }
        else if(args[0].equals("v12")) {
            Exponentiation exponentiation = new Exponentiation();
            System.out.print(exponentiation.withHelpPowerOf2(in.get(0), in.get(1)));
        }
        else if(args[0].equals("v13")) {
            Exponentiation exponentiation = new Exponentiation();
            System.out.print(exponentiation.withHelpBinaryDecompositionOfPower(in.get(0), in.get(1)));
        }
        else if(args[0].equals("v21")) {
            int range = in.get(0);

            PrimeNumberScanner primeNumberScanner = new PrimeNumberScanner(range);
            System.out.print(primeNumberScanner.getPrimeNumberCount());
        }
        else if(args[0].equals("v22")) {
            int range = in.get(0);

            PrimeNumberScanner primeNumberScanner = new PrimeNumberScanner(range);
            System.out.print(primeNumberScanner.getPrimeNumberCountWithOptimizations());
        }
        else if(args[0].equals("v23")) {
            int range = in.get(0);

            EratosthenSieve eratosthenSieve = new EratosthenSieve(range);
            System.out.print(eratosthenSieve.getPrimeNumberCount());
        }
        else if(args[0].equals("v24")) {
            int range = in.get(0);

            EratosthenSieve eratosthenSieve = new EratosthenSieve(range);
            System.out.print(eratosthenSieve.getPrimeNumberCountWithMemoryOptimizations());
        }
        else if(args[0].equals("v25")) {
            int range = Integer.parseInt(args[1]);

            EratosthenSieve eratosthenSieve = new EratosthenSieve(range);
            System.out.print(eratosthenSieve.getPrimeNumberCountWithLineComplexity());
        }
        else {
            System.out.println("Unknown key: " + args[0]);
        }
    }
}

