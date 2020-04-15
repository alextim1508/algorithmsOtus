package com.alextim.benchmarks;

import com.alextim.EratosthenSieve;
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
public class EratosthenSieveBenchmark {

    private final int RANGE = 10_000;
    private EratosthenSieve eratosthenSieve;

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(EratosthenSieveBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        eratosthenSieve = new EratosthenSieve(RANGE);
    }

    @Benchmark
    public int getPrimeNumberCount(){
        return eratosthenSieve.getPrimeNumberCount();
    }

    @Benchmark
    public int getPrimeNumberCountWithMemoryOptimizations(){
        return eratosthenSieve.getPrimeNumberCountWithMemoryOptimizations();
    }

    @Benchmark
    public int getPrimeNumberCountWithLineComplexity() {
        return eratosthenSieve.getPrimeNumberCountWithLineComplexity();
    }
}
