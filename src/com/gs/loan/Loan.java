package com.gs.loan;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by WangGenshen on 1/14/16.
 */
public class Loan {

    private BigDecimal totalLoanMoney; //贷款总额
    private int totalMonth; //还款月份
    private double loanRate; //贷款年利率

    private BigDecimal totalInterest; // 总利息数
    private BigDecimal totalRepayment; // 还款总额
    private BigDecimal firstRepayment; // 首月还款额
    private BigDecimal avgRepayment; // 月均还款额

    private List<LoanByMonth> allLoans; // 所有月份的还款情况

    public BigDecimal getTotalLoanMoney() {
        return totalLoanMoney;
    }

    public void setTotalLoanMoney(BigDecimal totalLoanMoney) {
        this.totalLoanMoney = totalLoanMoney;
    }

    public int getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(int totalMonth) {
        this.totalMonth = totalMonth;
    }

    public double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(double loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public BigDecimal getFirstRepayment() {
        return firstRepayment;
    }

    public void setFirstRepayment(BigDecimal firstRepayment) {
        this.firstRepayment = firstRepayment;
    }

    public BigDecimal getAvgRepayment() {
        return avgRepayment;
    }

    public void setAvgRepayment(BigDecimal avgRepayment) {
        this.avgRepayment = avgRepayment;
    }

    public List<LoanByMonth> getAllLoans() {
        return allLoans;
    }

    public void setAllLoans(List<LoanByMonth> allLoans) {
        this.allLoans = allLoans;
    }

    @Override
    public String toString() {
        String allLoansStr = "";
        if (allLoans != null) {
            for (LoanByMonth loanByMonth : allLoans) {
                String lbmStr = "月份: " + loanByMonth.getMonth() + "\t第" + loanByMonth.getYear() + "年\t第" +
                        loanByMonth.getMonthInYear() + "月\t" + "月供: " + loanByMonth.getRepayment() +
                        "\t本金: " + loanByMonth.getPayPrincipal() + "\t利息: " + loanByMonth.getInterest() +
                        "\t剩余贷款: " + loanByMonth.getRemainTotal();
                if (allLoansStr.equals("")) {
                    allLoansStr = lbmStr;
                } else {
                    allLoansStr += "\n" + lbmStr;
                }
            }
        }
        return "每月还款: " + getAvgRepayment() + "\t总利息: " + getTotalInterest() +
                "\t还款总额：" + getTotalRepayment() + "\t首月还款: " + getFirstRepayment() + "\n" + allLoansStr;
    }
}
