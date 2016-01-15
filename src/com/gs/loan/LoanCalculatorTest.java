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
        System.out.println("每月还款: " + loan.getAvgRepayment() + "\t总利息: " + loan.getTotalInterest() +
                "\t还款总额：" + loan.getTotalRepayment() + "\t首月还款: " + loan.getFirstRepayment());
        List<LoanByMonth> allLoans = loan.getAllLoans();
        for (LoanByMonth loanByMonth : allLoans) {
            System.out.println("月份: " + loanByMonth.getMonth() + "\t第" + loanByMonth.getYear() + "年\t第" +
                    loanByMonth.getMonthInYear() + "月\t" + "月供: " + loanByMonth.getRepayment() +
                    "\t本金: " + loanByMonth.getPayPrincipal() + "\t利息: " + loanByMonth.getInterest() +
                    "\t剩余贷款: " + loanByMonth.getRemainTotal());
        }
    }
}
