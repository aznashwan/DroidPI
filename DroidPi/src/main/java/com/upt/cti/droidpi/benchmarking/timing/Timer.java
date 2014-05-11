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

    //sets the internal measured time to the current system time
    public void start()
    {
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

    //resumes the timer in case of measuring of segmented sequences of code with a single timer
    @Deprecated
    public void resume()
    {
        this.timer=System.nanoTime();
    }

    //pauses the timer and returns the time recorded from the last start/resume
    @Deprecated
    public long pause()
    {
        this.timeElapsed+=System.nanoTime()-timer;

        return this.timeElapsed;
    }
}