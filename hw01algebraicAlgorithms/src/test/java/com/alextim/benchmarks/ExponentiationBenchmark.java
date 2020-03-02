package com.alextim.benchmarks;

import com.alextim.Exponentiation;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(warmups = 1, value = 3) @Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
public class ExponentiationBenchmark {

    private Exponentiation exponentiation;
    private double base;
    private int power;

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(ExponentiationBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        exponentiation = new Exponentiation();
        base = 1.000001;
        power = 1_000_000;
    }

    @Benchmark
    public double simple() {
        return exponentiation.simple(base,power);
    }

    @Benchmark
    public double withHelpPowerOf2() {
        return exponentiation.withHelpPowerOf2(base,power);
    }

    @Benchmark
    public double withHelpBinaryDecompositionOfPower() {
        return exponentiation.withHelpBinaryDecompositionOfPower(base,power);
    }
}
