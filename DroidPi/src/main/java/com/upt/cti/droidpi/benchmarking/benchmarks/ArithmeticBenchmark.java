package com.upt.cti.droidpi.benchmarking.benchmarks;

import com.upt.cti.droidpi.benchmarking.logging.Logger;
import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;
import com.upt.cti.droidpi.benchmarking.timing.Timer;

public class ArithmeticBenchmark implements IBenchmark
{
    private int a,b,c,i,v[];
    private static final int n=1000;
    private long result;
    private Timer t;

    public void initialize()
    {
        t=new Timer();

        a=0;
        b=0;
        c=0;

        v=new int[n];
        for(i=0;i<n;i++)
            v[i]=(int)(Math.random());
    }

    private long branching()
    {
        a=3;

        t.start();
        for(i=0;i<n;i++)
        {
            if(a>5&&a<12)
                a=4;
            else if(a<20&&a>15)
                a=15;
            else if(a>40)
                a=7;
            else if(a<0)
                a=50;
            else	a=0;
        }
        return	t.stop();
    }

    private long arithmetics()
    {
        a=5;
        b=8;

        t.start();
        for(i=0;i<n;i++)
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

    private long array()
    {
        t.start();
        for(i=0;i<n;i++)
        {
            v[i]=v[(i+300)%n];
        }
        return t.stop();
    }

    public void runTest()
    {
        this.initialize();

        this.result = this.branching()+this.arithmetics()+this.array();
    }

    public long getResult()
    {
        return this.result;
    }

    public String resultMessage()
    {
        return Logger.write("Your device's computation time was:", TimeUnit.convert(this.result,TimeUnit.MILI),TimeUnit.MILI);
    }
}
