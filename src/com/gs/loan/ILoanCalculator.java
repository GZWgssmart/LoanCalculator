package com.gs.loan;

import java.math.BigDecimal;

/**
 * Created by WangGenshen on 1/14/16.
 */
public interface ILoanCalculator {

    /**
     * 等额本息还款法
     * @param totalLoanMoney 总贷款额
     * @param totalMonth 还款月数
     * @param loanRate 贷款利率
     * @param rateType 可选择年利率或月利率
     * @return
     */
    public Loan calculateOne(BigDecimal totalLoanMoney, int totalMonth, double loanRate, int rateType);

    /**
     * 等额本金还款法
     * @param totalLoanMoney 总贷款额
     * @param totalMonth 还款月数
     * @param loanRate 贷款利率
     * @param rateType 可选择年利率或月利率
     * @return
     */
    public Loan calculateTwo(BigDecimal totalLoanMoney, int totalMonth, double loanRate, int rateType);
}
