package com.upt.cti.droidpi.benchmarking.logging;

import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;

public class Logger
{
    public static void write(long inputTime)
    {
        System.out.println(inputTime);
    }

    public static String write(Object... values)
    {
        String s="";

        for(Object o : values)
            s=s+o+" ";

        return s;
    }

    public static String write(String inS, long inT)
    {
        String s="";

        s=s+" "+inT;

        return s;
    }

    public static String write(String inputString, long inputTime, TimeUnit inputTimeUnit)
    {
        String s=" ";

        s=s+inputString+" "+inputTime+" "+inputTimeUnit;

        return s;
    }
}
