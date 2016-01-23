package com.gs.loan;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by WangGenshen on 1/14/16.
 */
public class LoanCalculatorTest extends TestCase {

    private int totalMonth;
    private BigDecimal totalMoney;
    private double percent;
    private double rate;
    private double rateDiscount;

    @Override
    protected void setUp() throws Exception {
        totalMonth = 36;
        totalMoney = new BigDecimal(50000);
        percent = 0;
        rate = 10.8;
        rateDiscount = 1;
    }

    @Test
    public void testACPIMCalculate() {
        ACPIMLoanCalculator calculator = new ACPIMLoanCalculator();
        Loan loan = calculator.calLoan(
                LoanUtil.totalLoanMoney(totalMoney, percent),
                totalMonth,
                LoanUtil.rate(rate, rateDiscount),
                LoanUtil.RATE_TYPE_YEAR);
        System.out.println(loan);
    }

    @Test
    public void testACMCalculate() {
        ACMLoanCalculator calculator = new ACMLoanCalculator();
        Loan loan = calculator.calLoan(
                LoanUtil.totalLoanMoney(totalMoney, percent),
                totalMonth,
                LoanUtil.rate(rate, rateDiscount),
                LoanUtil.RATE_TYPE_YEAR);
        System.out.println(loan);
    }

}
