package com.alextim.benchmarks;

import com.alextim.Exponentiation;
import com.alextim.PrimeNumberScanner;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PrimeNumberBenchmark {

    private final int RANGE = 100_000;
    private PrimeNumberScanner scanner;

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(PrimeNumberBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        scanner = new PrimeNumberScanner(RANGE);
    }

    @Benchmark
    public int getPrimeNumberCount(){
        return scanner.getPrimeNumberCount();
    }

    @Benchmark
    public int getPrimeNumberCountWithOptimizations() {
        return scanner.getPrimeNumberCountWithOptimizations();
    }
}
