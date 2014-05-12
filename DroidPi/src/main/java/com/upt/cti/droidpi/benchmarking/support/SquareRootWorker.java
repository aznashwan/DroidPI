package com.upt.cti.droidpi.benchmarking.support;

public class SquareRootWorker implements Runnable
{
    private int start, end;

    public SquareRootWorker(int start, int end)
    {
        this.start=start;
        this.end=end;
    }

    public void setSE(int start, int end)
    {
        this.start=start;
        this.end=end;
    }

    public void run()
    {
        int i;
        for(i=start; i<end; i++)
            computeSquareRoot(i);
    }

    public double computeSquareRoot(double c)
    {
        if(c<0) return Double.NaN;
        double EPS=1E-15;
        double t=c;
        while(Math.abs(t-c/t)>EPS*t)
            t=(c/t+t)/2.0;
        return t;
    }
}