package com.gs.loan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGenshen on 1/14/16.
 */
public class LoanCalculator implements ILoanCalculator {

    public static final int RATE_TYPE_YEAR = 10;
    public static final int RATE_TYPE_MONTH = 11;

    public Loan calculateOne(BigDecimal totalLoanMoney, int totalMonth, double loanRate, int rateType) {
        Loan loan = new Loan();
        BigDecimal loanRateMonth = rateType == RATE_TYPE_YEAR ? new BigDecimal(loanRate / 100 / 12) : new BigDecimal(loanRate / 100);
        BigDecimal factor = new BigDecimal(Math.pow(1 + loanRateMonth.doubleValue(), totalMonth));
        BigDecimal avgRepayment = totalLoanMoney.multiply(loanRateMonth).multiply(factor).divide(factor.subtract(new BigDecimal(1)), 2, BigDecimal.ROUND_HALF_UP);
        loan.setLoanRate(loanRate);
        loan.setTotalLoanMoney(totalLoanMoney);
        loan.setTotalMonth(totalMonth);
        loan.setAvgRepayment(avgRepayment);
        loan.setTotalRepayment(avgRepayment.multiply(new BigDecimal(totalMonth)));
        loan.setFirstRepayment(avgRepayment);

        BigDecimal totalPayedPrincipal = new BigDecimal(0);//累积所还本金
        BigDecimal totalInterest = new BigDecimal(0); //总利息
        BigDecimal totalRepayment = new BigDecimal(0); // 已还款总数
        List<LoanByMonth> loanByMonthList = new ArrayList<>();
        int year = 0;
        int monthInYear = 0;
        for (int i = 0; i < totalMonth; i++) {
            LoanByMonth loanByMonth = new LoanByMonth();
            BigDecimal remainPrincipal = totalLoanMoney.subtract(totalPayedPrincipal);
            BigDecimal interest = remainPrincipal.multiply(loanRateMonth).setScale(2, BigDecimal.ROUND_HALF_UP);
            totalInterest = totalInterest.add(interest);
            BigDecimal principal = loan.getAvgRepayment().subtract(interest);
            totalPayedPrincipal = totalPayedPrincipal.add(principal);
            loanByMonth.setMonth(i + 1);
            loanByMonth.setYear(year + 1);
            loanByMonth.setMonthInYear(++monthInYear);
            if ((i + 1) % 12 == 0) {
                year++;
                monthInYear = 0;
            }
            loanByMonth.setInterest(interest);
            loanByMonth.setPayPrincipal(principal);
            loanByMonth.setRepayment(loan.getAvgRepayment());
            totalRepayment = totalRepayment.add(loanByMonth.getRepayment());
            loanByMonth.setRemainPrincipal(remainPrincipal);
            loanByMonth.setRemainTotal(loan.getTotalRepayment().subtract(totalRepayment));
            loanByMonthList.add(loanByMonth);
        }
        loan.setTotalInterest(totalInterest);
        loan.setAllLoans(loanByMonthList);
        return loan;
    }

    public Loan calculateTwo(BigDecimal totalLoanMoney, int totalMonth, double loanRate, int rateType) {
        Loan loan = new Loan();
        BigDecimal loanRateMonth = rateType == RATE_TYPE_YEAR ? new BigDecimal(loanRate / 100 / 12) : new BigDecimal(loanRate / 100);
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

    public static BigDecimal totalMoney(double area, BigDecimal price, double discount) {
        return price.multiply(new BigDecimal(area)).multiply(new BigDecimal(discount)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal totalLoanMoney(BigDecimal totalMoney, double percent) {
        return totalMoney.multiply(new BigDecimal(1 - percent)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal totalLoanMoney(double area, BigDecimal price, double discount, double percent) {
        return totalLoanMoney(totalMoney(area, price, discount), percent);
    }

    public static double rate(double rate, double discount) {
        return rate * discount;
    }

    public static int totalMonth(int year) {
        return 12 * year;
    }

}
