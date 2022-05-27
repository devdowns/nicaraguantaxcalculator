package com.devdowns.nicaraguantaxcalculator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
@Data
public class SalaryInformation {
    private final BigDecimal baseUSDSalary;
    private final Boolean isPayingForINSS;
}
