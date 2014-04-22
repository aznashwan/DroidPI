package com.upt.cti.droidpi.benchmarking.timing;

public class Timer
{
    private long timer;
    private long timeElapsed;

    public Timer()
    {
        this.timer=0;
        this.timeElapsed=0;
    }

    public void start()
    {
        this.timer=0;
        this.timeElapsed=0;
        this.timer=System.nanoTime();
    }

    public long stop()
    {
        long timeElapsed=this.timeElapsed+System.nanoTime()-timer;

        this.timeElapsed=0;
        timer=0;

        return timeElapsed;
    }

    public void resume()
    {
        this.timer=System.nanoTime();
    }

    public long pause()
    {
        this.timeElapsed+=System.nanoTime()-timer;

        return this.timeElapsed;
    }
}