package com.upt.cti.droidpi.benchmarking.benchmarks;

import com.upt.cti.droidpi.benchmarking.logging.Logger;
import com.upt.cti.droidpi.benchmarking.support.SquareRootWorker;
import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;
import com.upt.cti.droidpi.benchmarking.timing.Timer;

public class ThreadsBenchmark implements IBenchmark
{
    private Thread threads[];
    private final int n=1000;
    private final int nThreads=250;

    private Timer t;
    private long result;

    @Override
    public void initialize()
    {
        t=new Timer();
        this.threads=new Thread[this.nThreads];
    }

    @Override
    public void warmUp()
    {
        String s="There is no point in warming this test up";

        s=s+". I just added these to avoid all the annoying warnings lol";
    }

    @Override
    public void runTest()
    {
        this.initialize();

        t.start();
        for(int i=0; i<this.nThreads; i++)
        {
            SquareRootWorker sqrtWorker=new SquareRootWorker(i*n/nThreads, (i+1)*n/nThreads);
            threads[i]=new Thread(sqrtWorker);
            threads[i].start();
        }

        for(int i=0; i<this.nThreads; i++)
        {
            try
            {
                threads[i].join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        result=t.stop();
    }

    @Override
    public long getResult()
    {
        return this.result;
    }

    @Override
    public String resultMessage()
    {
        return Logger.write("250 threads have been created, merged, and demised. The whole process took ", TimeUnit.convert(this.result, TimeUnit.MILI), TimeUnit.MILI);
    }
}
