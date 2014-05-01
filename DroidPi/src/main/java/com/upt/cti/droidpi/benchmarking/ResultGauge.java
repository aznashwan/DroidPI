package com.upt.cti.droidpi.benchmarking;

import com.upt.cti.droidpi.benchmarking.benchmarks.ArithmeticBenchmark;

public class ResultGauge
{
    private static final long pitest_baseline=1000;
    private static final long threadstest_baseline=1000;
    private static final long arithmetictest_baseline=1000;
    private ArithmeticBenchmark arithmeticBenchmark;

    public ResultGauge()
    {
        arithmeticBenchmark=new ArithmeticBenchmark();
    }

    public int arithmeticScore()
    {
        return (int) (arithmeticBenchmark.getResult()*100/arithmetictest_baseline);
    }
}
