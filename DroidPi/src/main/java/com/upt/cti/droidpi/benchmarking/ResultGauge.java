package com.upt.cti.droidpi.benchmarking;

public class ResultGauge
{
    //the baseline values measured for a Samsung Galaxy S4
    private static final long pitest_baseline=6000;
    private static final long threadstest_baseline=100;
    private static final long recursiontest_baseline=1;
    private static final long arithmetictest_baseline=500;

    public static int arithmeticScore(long inputResult)
    {
        return (int) ((arithmetictest_baseline*100)/inputResult);
    }

    public static int PIScore(long inputResult)
    {
        return (int) ((pitest_baseline*100)/inputResult);
    }

    public static int threadsScore(long inputResult)
    {
        return (int) ((threadstest_baseline*100)/inputResult);
    }

    public static int recursionScore(long inputResult)
    {
        return (int) ((recursiontest_baseline*100)/inputResult);
    }
}
