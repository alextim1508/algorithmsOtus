package com.alextim.benchmarks;

import com.alextim.Exponentiation;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@BenchmarkMode(Mode.AverageTime)
@State(value = Scope.Benchmark)
@OutputTimeUnit(NANOSECONDS)
public class ExponentiationBenchmark {

    private Exponentiation exponentiation;
    private int base;
    private int power;

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }

    @Setup
    public void setup() {
        exponentiation = new Exponentiation();
        base = 12;
        power = 3;
    }

    @Benchmark
    public long simple() {
        return exponentiation.simple(base,power);
    }

    @Benchmark
    public long withHelpPowerOf2() {
        return exponentiation.withHelpPowerOf2(base,power);
    }

    @Benchmark
    public long withHelpBinaryDecompositionOfPower() {
        return exponentiation.withHelpBinaryDecompositionOfPower(base,power);
    }
}
