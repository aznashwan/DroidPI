package com.upt.cti.droidpi.benchmarking.benchmarks;

import com.upt.cti.droidpi.benchmarking.benchmarks.IBenchmark;
import com.upt.cti.droidpi.benchmarking.logging.Logger;
import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;
import com.upt.cti.droidpi.benchmarking.timing.Timer;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class PIBenchmark implements IBenchmark
{
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal FOUR = new BigDecimal("4");
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal TWO_THIRTY_NINE = new BigDecimal("239");
    private static final int calcSize=1000;
    private static final int size=1000;

    private BigDecimal p;

    private long result;
    private Timer t;

    @Override
    public void initialize()
    {
        p=new BigDecimal(0);
        t=new Timer();
    }

    private BigDecimal PI()
    {
        p=FOUR.multiply((FOUR.multiply(arccot(FIVE, calcSize)))
                .subtract(arccot(TWO_THIRTY_NINE, calcSize)))
                .setScale(size, RoundingMode.DOWN);
        return p;
    }

    private BigDecimal arccot(BigDecimal x, int numDigits)
    {

        BigDecimal unity = BigDecimal.ONE.setScale(numDigits,
                RoundingMode.DOWN);
        BigDecimal sum = unity.divide(x, RoundingMode.DOWN);
        BigDecimal xpower = new BigDecimal(sum.toString());
        BigDecimal term = null;

        boolean add = false;

        for(BigDecimal n = new BigDecimal("3"); term == null ||
                term.compareTo(BigDecimal.ZERO)!=0; n = n.add(TWO))
        {
            xpower = xpower.divide(x.pow(2), RoundingMode.DOWN);
            term = xpower.divide(n, RoundingMode.DOWN);
            sum = add ? sum.add(term) : sum.subtract(term);
            add = ! add;
        }
        return sum;
    }

    @Override
    public void runTest()
    {
        this.initialize();

        t.start();
        this.PI();
        this.result=t.stop();
    }

    @Override
    public long getResult()
    {
        return this.result;
    }

    @Override
    public String resultMessage()
    {
        return Logger.write("1000 digits of PI have been computed, it took: ", TimeUnit.convert(this.result,TimeUnit.MILI), TimeUnit.MILI);
    }
}