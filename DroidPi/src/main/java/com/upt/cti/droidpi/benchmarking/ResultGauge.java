package com.upt.cti.droidpi.benchmarking;

import com.upt.cti.droidpi.benchmarking.benchmarks.ArithmeticBenchmark;

public class ResultGauge
{
    private static final long pitest_baseline=1000;
    private static final long threadstest_baseline=1000;
    private static final long arithmetictest_baseline=30000000;

    public static int arithmeticScore(long inputResult)
    {
        return (int) ((inputResult*100)/arithmetictest_baseline);
    }

    public static int PIScore(long inputResult)
    {
        return (int) ((inputResult*100)/pitest_baseline);
    }

    public static int threadsScore(long inputResult)
    {
        return (int) ((inputResult*100)/threadstest_baseline);
    }
}
