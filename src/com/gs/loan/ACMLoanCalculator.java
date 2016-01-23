package com.gs.loan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 等额本金还款法
 *
 * Created by WangGenshen on 1/23/16.
 */
public class ACMLoanCalculator extends LoanCalculatorAdapter {

    @Override
    public Loan calLoan(BigDecimal totalLoanMoney, int totalMonth, double loanRate, int rateType) {
        Loan loan = new Loan();
        BigDecimal loanRateMonth = rateType == LoanUtil.RATE_TYPE_YEAR ? new BigDecimal(loanRate / 100 / 12) : new BigDecimal(loanRate / 100);
        loan.setTotalMonth(totalMonth);
        loan.setTotalLoanMoney(totalLoanMoney);
        BigDecimal payPrincipal = totalLoanMoney.divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal totalPayedPrincipal = new BigDecimal(0);//累积所还本金
        BigDecimal totalInterest = new BigDecimal(0); //总利息
        BigDecimal totalRepayment = new BigDecimal(0); // 已还款总数
        List<LoanByMonth> loanByMonthList = new ArrayList<>();
        int year = 0;
        int monthInYear = 0;
        for (int i = 0; i < totalMonth; i++) {
            LoanByMonth loanByMonth = new LoanByMonth();
            loanByMonth.setMonth(i + 1);
            loanByMonth.setYear(year + 1);
            loanByMonth.setMonthInYear(++monthInYear);
            if ((i + 1) % 12 == 0) {
                year++;
                monthInYear = 0;
            }
            totalPayedPrincipal = totalPayedPrincipal.add(payPrincipal);
            loanByMonth.setPayPrincipal(payPrincipal);
            BigDecimal interest = totalLoanMoney.subtract(totalPayedPrincipal).multiply(loanRateMonth).setScale(2, BigDecimal.ROUND_HALF_UP);
            loanByMonth.setInterest(interest);
            totalInterest = totalInterest.add(interest);
            loanByMonth.setRepayment(payPrincipal.add(interest));
            if (i == 0) {
                loan.setFirstRepayment(loanByMonth.getRepayment());
            }
            totalRepayment = totalRepayment.add(loanByMonth.getRepayment());
            loanByMonth.setRemainPrincipal(totalLoanMoney.subtract(totalPayedPrincipal));
            loanByMonthList.add(loanByMonth);
        }
        loan.setTotalRepayment(totalRepayment);
        loan.setAvgRepayment(totalRepayment.divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_HALF_UP));
        loan.setTotalInterest(totalInterest);
        BigDecimal totalPayedRepayment = new BigDecimal(0);
        for (LoanByMonth loanByMonth : loanByMonthList) {
            totalPayedRepayment = totalPayedRepayment.add(loanByMonth.getRepayment());
            loanByMonth.setRemainTotal(totalRepayment.subtract(totalPayedRepayment));
        }
        loan.setAllLoans(loanByMonthList);
        return loan;
    }

}
