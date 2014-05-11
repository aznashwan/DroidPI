package com.upt.cti.droidpi.benchmarking.benchmarks;


public class RecursionBenchmark implements IBenchmark
{
    private int size;
    private int savedI;

    private int recursivePrimeSum(int i, int l, int n)
    {
        int k,prime=i-1,S=0;
        if(i>=n) return 0;
        for(k=0;k<l;k++)
        {
            prime=findNextPrime(prime+1);
            if(prime<n) S=S+prime;
        }
        try
        {
            return S+recursivePrimeSum(prime+1,l,n);
        }catch(StackOverflowError e)
        {
            savedI=i;

            return 0;
        }
    }

    private int recursivePrimeSum(int i, int n)
    {
        int prime=findNextPrime(i);
        if(prime>=n) return 0;
        try
        {
            return prime+recursivePrimeSum(prime+1,n);
        }catch(StackOverflowError e)
        {
            System.out.println("Crashed at "+i+".");
            return 0;
        }
    }

    private int findNextPrime(int i)
    {
        for(;;i++)
            if(isPrime(i)) return i;
    }

    private boolean isPrime(int i)
    {
        int j;
        for(j=2;j<=Math.sqrt((double)i);j=j+1)
            if(i%j==0) return false;
        return true;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void runTest()
    {

    }

    @Override
    public long getResult()
    {
        return 0;
    }

    @Override
    public String resultMessage()
    {
        return null;
    }
}
