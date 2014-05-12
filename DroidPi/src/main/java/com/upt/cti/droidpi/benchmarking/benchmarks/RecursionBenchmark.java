package com.upt.cti.droidpi.benchmarking.benchmarks;


import com.upt.cti.droidpi.benchmarking.logging.Logger;
import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;
import com.upt.cti.droidpi.benchmarking.timing.Timer;

public class RecursionBenchmark implements IBenchmark
{
    private static final int nPrimes=10000;

    private Timer t;
    private long result;

    @Override
    public void initialize()
    {
        t=new Timer();
    }

    @Override
    public void warmUp()
    {
        this.recursivePrimeSum(2, 20);
    }

    private int recursivePrimeSum(int i, int n)
    {
        int prime=findNextPrime(i);
        if(prime>=n) return 0;
        try
        {
            return prime+recursivePrimeSum(prime+1, n);
        }
        catch(StackOverflowError e)
        {
            return 0;
        }
    }

    private int findNextPrime(int i)
    {
        for(; ; i++)
            if(isPrime(i)) return i;
    }

    private boolean isPrime(int i)
    {
        int j;
        for(j=2; j<=Math.sqrt((double) i); j=j+1)
            if(i%j==0) return false;
        return true;
    }

    @Override
    public void runTest()
    {
        this.initialize();
        this.warmUp();

        t.start();
        this.recursivePrimeSum(2, nPrimes);
        result=t.stop();
    }

    @Override
    public long getResult()
    {
        return result;
    }

    @Override
    public String resultMessage()
    {
        return Logger.write("The sum of the first 10000 primes was computed recursively in ", TimeUnit.convert(this.result, TimeUnit.MICRO), TimeUnit.MICRO);
    }
}
