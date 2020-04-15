package com.alextim.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.alextim.Sort.*;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class NearlySortingBenchmark {

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }

    @State(Scope.Benchmark)
    public static class Data {

        @Param({"1000000"})
        int size;

        int[] array;

        @Setup
        public void setup() {
            array = createNearlySortedArray(size, 5);
        }
    }

    @State(Scope.Thread)
    public static class DataCopy {
        int[] copy;

        @Setup(Level.Invocation)
        public void setup(Data d) {
            copy = Arrays.copyOf(d.array, d.array.length);
        }
    }

    @Benchmark
    public int[] shellSortGap1NearlySorting(DataCopy d) {
        shellSort(d.copy, k -> 2*d.copy.length/(int) Math.pow(2, k+1) + 1, 1, 1);
        return d.copy;
    }

    @Benchmark
    public int[] shellSortGap2NearlySorting(DataCopy d) {
        shellSort(d.copy, k -> d.copy.length/(int) Math.pow(2, k+1), 1, 1);
        return d.copy;
    }

    @Benchmark
    public int[] shellSortGap3NearlySorting(DataCopy d) {
        shellSort(d.copy,  k -> (int) Math.pow(2, k) - 1, 10, -1);
        return d.copy;
    }

    @Benchmark
    public int[] shellSortGap4NearlySorting(DataCopy d) {
        shellSort(d.copy,  k -> (int) Math.pow(2, k) + 1, 10, -1);
        return d.copy;
    }

    @Benchmark
    public int[] shellSortGap5NearlySorting(DataCopy d) {
        shellSort(d.copy,  k ->  d.copy.length/(int)(Math.pow(2, k)), 1, 1);
        return d.copy;
    }

    @Benchmark
    public int[] heapSortNearlySorting(DataCopy d) {
        heapSort(d.copy);
        return d.copy;
    }
}
