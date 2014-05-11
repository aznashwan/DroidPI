package com.upt.cti.droidpi.benchmarking.benchmarks;

public interface IBenchmark
{
    //will initialize all data required to run the specific test
    public void initialize();

    //will warm up the processor to handle the upandcoming tests
    public void warmUp();

    //will run the actual test, storing the result as a field in the implementing object
    public void runTest();

    //will return the result of the previously run test
    public long getResult();

    //will return a message with the result of the test neatly expressed
    public String resultMessage();
}
