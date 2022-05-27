package com.devdowns.nicaraguantaxcalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class SalaryDetail {
    private final BigDecimal baseSalary;
    private final BigDecimal monthlyTax;
    private final BigDecimal monthlyINSS;
    private final BigDecimal monthlyTakeHomeSalary;
}
