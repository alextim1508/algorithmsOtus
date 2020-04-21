package com.alextim.benchmarks;

import com.alextim.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.alextim.benchmarks.ArrayBenchmark.ARRAY_SIZE_MAX;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class EndInsertion {

    private Array<Integer> singleArray;
    private Array<Integer> vectorArray;
    private Array<Integer> factorArray;
    private Array<Integer> matrixArray;
    private ArrayListWrapper<Integer> arrayList;

    @Setup
    public void setUp() {
        System.out.println("end setUp");
        singleArray = new SingleArray<>();
        vectorArray = new VectorArray<>(10);
        factorArray = new FactorArray<>(2);
        matrixArray = new MatrixArray<>(new VectorArray<>(10), 10);
        arrayList = new ArrayListWrapper<>();
    }

    @Benchmark
    public long endAdditionSingleArray() {
        return endInsert(singleArray);
    }

    @Benchmark
    public long endAdditionVectorArray() {
        return endInsert(vectorArray);
    }

    @Benchmark
    public long endAdditionFactorArray() {
        return endInsert(factorArray);
    }

    @Benchmark
    public long endAdditionMatrixArray() {
        return endInsert(matrixArray);
    }

    @Benchmark
    public long endAdditionArrayList() {
        return endInsert(arrayList);
    }

    private long endInsert(Array<Integer> array) {
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            array.add(i);
        }

        long sum = 0;
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            sum += array.get(i);
        }
        return sum;
    }
}
