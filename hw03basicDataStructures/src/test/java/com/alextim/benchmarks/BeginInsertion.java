package com.alextim.benchmarks;

import com.alextim.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.alextim.benchmarks.ArrayBenchmark.ARRAY_SIZE_MAX;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BeginInsertion {

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
    public long startAdditionSingleArray() {
        return beginInsert(singleArray);
    }

    @Benchmark
    public long startAdditionVectorArray() {
        return beginInsert(vectorArray);
    }

    @Benchmark
    public long startAdditionFactorArray() {
        return beginInsert(factorArray);
    }

    @Benchmark
    public long startAdditionMatrixArray() {
        return beginInsert(matrixArray);
    }

    @Benchmark
    public long startAdditionArrayList() {
        return beginInsert(arrayList);
    }

    private long beginInsert(Array<Integer> array) {
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            array.add(i, 0);
        }

        long sum = 0;
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            sum += array.get(i);
        }
        return sum;
    }
}
