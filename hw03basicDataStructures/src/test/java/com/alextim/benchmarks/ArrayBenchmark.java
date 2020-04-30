package com.alextim.benchmarks;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ArrayBenchmark {

    public static final int ARRAY_SIZE_MAX = 100_000;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BeginInsertion.class.getSimpleName())
                .include(EndInsertion.class.getSimpleName())
                .include(RandomInsertion.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

}
