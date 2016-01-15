package com.gs.loan;

import java.math.BigDecimal;

/**
 * Created by WangGenshen on 1/14/16.
 */
public class LoanByMonth {

    private int month; // 第几个月份
    private BigDecimal repayment; // 该月还款额
    private BigDecimal payPrincipal; // 该月所还本金
    private BigDecimal interest; // 该月利息
    private BigDecimal remainTotal; // 剩余贷款
    private BigDecimal remainPrincipal; // 剩余总本金

    private int year; // 第几年
    private int monthInYear; // 年里的第几月

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getRepayment() {
        return repayment;
    }

    public void setRepayment(BigDecimal repayment) {
        this.repayment = repayment;
    }

    public BigDecimal getPayPrincipal() {
        return payPrincipal;
    }

    public void setPayPrincipal(BigDecimal payPrincipal) {
        this.payPrincipal = payPrincipal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getRemainTotal() {
        return remainTotal;
    }

    public void setRemainTotal(BigDecimal remainTotal) {
        this.remainTotal = remainTotal;
    }

    public BigDecimal getRemainPrincipal() {
        return remainPrincipal;
    }

    public void setRemainPrincipal(BigDecimal remainPrincipal) {
        this.remainPrincipal = remainPrincipal;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthInYear() {
        return monthInYear;
    }

    public void setMonthInYear(int monthInYear) {
        this.monthInYear = monthInYear;
    }
}
