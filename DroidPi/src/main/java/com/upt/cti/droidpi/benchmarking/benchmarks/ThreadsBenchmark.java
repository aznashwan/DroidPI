package com.upt.cti.droidpi.benchmarking.benchmarks;

import com.upt.cti.droidpi.benchmarking.logging.Logger;
import com.upt.cti.droidpi.benchmarking.support.SquareRootWorker;
import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;
import com.upt.cti.droidpi.benchmarking.timing.Timer;

public class ThreadsBenchmark implements IBenchmark
{
    private Thread threads[];
    private final int n=100;
    private final int nThreads=25;

    private Timer t;
    private long result;

    @Override
    public void initialize()
    {
        t=new Timer();
    }

    @Override
    public void runTest()
    {
        this.threads = new Thread[this.nThreads];

        t.start();
        for (int i = 0; i < this.nThreads; i++)
        {
            SquareRootWorker sqrtWorker = new SquareRootWorker(i*n/nThreads,(i+1)*n/nThreads);
            threads[i] = new Thread(sqrtWorker);
            threads[i].start();
        }

        for (int i = 0; i < this.nThreads; i++)
        {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
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
        return Logger.write("25 threads have been created, merged, and demised. The whole process took: ", TimeUnit.convert(this.result,TimeUnit.MILI), TimeUnit.MILI);
    }
}
