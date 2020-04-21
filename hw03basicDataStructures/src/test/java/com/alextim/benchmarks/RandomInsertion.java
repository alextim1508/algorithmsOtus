package com.alextim.benchmarks;

import com.alextim.*;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.alextim.benchmarks.ArrayBenchmark.ARRAY_SIZE_MAX;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RandomInsertion {

    private Array<Integer> singleArray;
    private Array<Integer> vectorArray;
    private Array<Integer> factorArray;
    private Array<Integer> matrixArray;
    private ArrayListWrapper<Integer> arrayList;

    @Setup
    public void setUp() {
        singleArray = new SingleArray<>();
        vectorArray = new VectorArray<>(10);
        factorArray = new FactorArray<>(2);
        matrixArray = new MatrixArray<>(new VectorArray<>(10), 10);
        arrayList = new ArrayListWrapper<>();
    }

    @Benchmark
    public long randomAdditionSingleArray() {
        return randomInsert(singleArray);
    }

    @Benchmark
    public long randomAdditionVectorArray() {
        return randomInsert(vectorArray);
    }

    @Benchmark
    public long randomAdditionFactorArray() {
        return randomInsert(factorArray);
    }

    @Benchmark
    public long randomAdditionMatrixArray() {
        return randomInsert(matrixArray);
    }

    @Benchmark
    public long randomAdditionArrayList() {
        return randomInsert(arrayList);
    }

    private long randomInsert(Array<Integer> array) {
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            array.add(random.nextInt(ARRAY_SIZE_MAX - 1));
        }

        long sum = 0;
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            sum += array.get(i);
        }
        return sum;
    }
}
