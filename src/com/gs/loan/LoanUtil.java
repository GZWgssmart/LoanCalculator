package com.gs.loan;

import java.math.BigDecimal;

/**
 * Created by WangGenshen on 1/23/16.
 */
public class LoanUtil {

    public static final int RATE_TYPE_YEAR = 10;
    public static final int RATE_TYPE_MONTH = 11;

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
