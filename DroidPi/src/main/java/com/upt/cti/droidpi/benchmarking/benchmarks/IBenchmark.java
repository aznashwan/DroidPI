package com.upt.cti.droidpi.benchmarking.benchmarks;

public interface IBenchmark
{
    public void initialize();
    public void runTest();
    public long getResult();
    public String resultMessage();
}
