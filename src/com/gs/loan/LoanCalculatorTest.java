package com.gs.loan;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by WangGenshen on 1/14/16.
 */
public class LoanCalculatorTest {

    @Test
    public void testCalculate() {
        int totalMonth = 360;
        BigDecimal totalMoney = new BigDecimal(510000);
        double percent = 0.3;
        double rate = 4.9;
        double rateDiscount = 0.85;

        LoanCalculator calculator = new LoanCalculator();
        Loan loan = calculator.calculateOne(
                LoanCalculator.totalLoanMoney(totalMoney, percent),
                totalMonth,
                LoanCalculator.rate(rate, rateDiscount),
                LoanCalculator.RATE_TYPE_YEAR);
        System.out.println(loan);
    }
}
