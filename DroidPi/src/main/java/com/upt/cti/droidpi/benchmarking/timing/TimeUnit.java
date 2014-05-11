package com.upt.cti.droidpi.benchmarking.timing;

public enum TimeUnit
{
    NANO(1), MICRO(2), MILI(3), SEC(4);
    private int n;

    private TimeUnit(int inV)
    {
        this.n=inV;
    }

    public static long convert(long n, TimeUnit toConvert)
    {
        switch(toConvert)
        {
            case NANO:
                return n;
            case MICRO:
                return n/1000;
            case MILI:
                return n/1000000;
            case SEC:
                return n/1000000000;
            default:
                return 0;
        }
    }

    public String toString()
    {
        switch(n)
        {
            case 1:
                return " nanoseconds";
            case 2:
                return " microseconds";
            case 3:
                return " miliseconds";
            case 4:
                return " seconds";
        }

        return "not a valid time unit";
    }
}
