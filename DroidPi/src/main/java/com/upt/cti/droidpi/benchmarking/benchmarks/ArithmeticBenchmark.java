package com.upt.cti.droidpi.benchmarking.benchmarks;

import com.upt.cti.droidpi.benchmarking.logging.Logger;
import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;
import com.upt.cti.droidpi.benchmarking.timing.Timer;

public class ArithmeticBenchmark implements IBenchmark
{
    private int a, b, c, i, v[];
    private static final int totalRuns=10000000;
    private long result;
    private Timer t;

    public void initialize()
    {
        t=new Timer();

        a=0;
        b=0;
        c=0;

        v=new int[totalRuns];
        for(i=0; i<totalRuns; i++)
            v[i]=(int) (Math.random());
    }

    public void warmUp()
    {
        this.branching(50);
        this.arithmetics(50);
        this.arrays(50);
    }

    private long branching(int n)
    {
        a=3;

        t.start();
        for(i=0; i<n; i++)
        {
            if(a>5&&a<12)
                a=4;
            else if(a<20&&a>15)
                a=15;
            else if(a>40)
                a=7;
            else if(a<0)
                a=50;
            else a=0;
        }
        return t.stop();
    }

    private long arithmetics(int n)
    {
        a=5;
        b=8;

        t.start();
        for(i=0; i<n; i++)
        {
            a=b*c;
            b=b*2;
            c=1<<b;
            a=b+c;
            c=b-2;
            a=18*c;
            b=a/81;
        }
        return t.stop();
    }

    private long arrays(int n)
    {
        t.start();
        for(i=0; i<n; i++)
        {
            v[i]=v[(i+300)%n];
        }
        return t.stop();
    }

    public void runTest()
    {
        this.initialize();
        this.warmUp();

        this.result=this.branching(totalRuns)+this.arithmetics(totalRuns)+this.arrays(totalRuns);
    }

    public long getResult()
    {
        return this.result;
    }

    public String resultMessage()
    {
        return Logger.write("Your device's computation time was ", TimeUnit.convert(this.result, TimeUnit.MILI), TimeUnit.MILI);
    }
}
