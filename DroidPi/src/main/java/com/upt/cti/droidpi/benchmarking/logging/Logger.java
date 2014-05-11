package com.upt.cti.droidpi.benchmarking.logging;

import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;

public class Logger
{

    //simply writes the times on stdout; used for debugging purposes
    @Deprecated
    public static void write(long inputTime)
    {
        System.out.println(inputTime);
    }

    //sequentially outputs a string with all the objects given as input
    @Deprecated
    public static String write(Object... values)
    {
        String s="";

        for(Object o : values)
            s=s+o+" ";

        return s;
    }

    //simply outputs an input message together with an input time value
    @Deprecated
    public static String write(String inS, long inT)
    {
        String s="";

        s=s+" "+inT;

        return s;
    }

    //returns a string with an input message, an input time and the associated time unit
    public static String write(String inputString, long inputTime, TimeUnit inputTimeUnit)
    {
        String s=" ";

        s=s+inputString+" "+inputTime+" "+inputTimeUnit;

        return s;
    }
}
