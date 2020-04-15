package com.alextim.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.alextim.Sort.*;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RandomSortingBenchmark {

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
            array = createRandomArray(size, 1000);
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
    public int[] shellSortGap1Random(DataCopy d) {
        shellSort(d.copy, k -> 2*d.copy.length/(int) Math.pow(2, k+1) + 1, 1, 1);
        return d.copy;
    }

    @Benchmark
    public int[] shellSortGap2Random(DataCopy d) {
        shellSort(d.copy, k -> d.copy.length/(int) Math.pow(2, k+1), 1, 1);
        return d.copy;
    }

    @Benchmark
    public int[] shellSortGap3Random(DataCopy d) {
        shellSort(d.copy,  k -> (int) Math.pow(2, k) - 1, 10, -1);
        return d.copy;
    }

    @Benchmark
    public int[] shellSortGap4Random(DataCopy d) {
        shellSort(d.copy,  k -> (int) Math.pow(2, k) + 1, 10, -1);
        return d.copy;
    }

    @Benchmark
    public int[] shellSortGap5Random(DataCopy d) {
        shellSort(d.copy,  k ->  d.copy.length/(int)(Math.pow(2, k)), 1, 1);
        return d.copy;
    }

    @Benchmark
    public int[] heapSortRandom(DataCopy d) {
        heapSort(d.copy);
        return d.copy;
    }
}
