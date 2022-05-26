package com.devdowns.nicaraguantaxcalculator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@AllArgsConstructor
public class Salary {

    private final NIOTaxBracket taxBracket;
    private final BigDecimal MonthlySalaryInUSD;
    private final BigDecimal USDToNIOExchangeRate;
    private final Boolean isPayingForINSS;


    public void computeTaxes(){

        final var INSS = isPayingForINSS ? new BigDecimal(0.0625) : BigDecimal.ZERO;
        final var monthsPerYear = new BigDecimal(12);
        final var monthlySalaryInNIO = MonthlySalaryInUSD.multiply(USDToNIOExchangeRate);
        final var INSSMonthlyQuota = monthlySalaryInNIO.multiply(INSS);
        final var YearlySalaryProjection = monthlySalaryInNIO.subtract(INSSMonthlyQuota).multiply(monthsPerYear);

        final var amountExceedingBracket = YearlySalaryProjection
                .subtract(taxBracket.getLowEnd())
                .subtract(new BigDecimal(100_000.00));

        final var taxToPayPerYear = amountExceedingBracket
                .multiply(taxBracket.getPercentageApplicable())
                .add(taxBracket.getBaseTax());

        final var monthlyTaxToPayInUSD = taxToPayPerYear
                .divide(USDToNIOExchangeRate, 2, RoundingMode.HALF_UP)
                .divide(monthsPerYear,2, RoundingMode.HALF_UP);

        final var INSSMonthlyQuotaInUSD = INSSMonthlyQuota.divide(USDToNIOExchangeRate,2, RoundingMode.HALF_UP);

        final var monthlyTakeHomeSalaryInUSD = MonthlySalaryInUSD
                .subtract(monthlyTaxToPayInUSD)
                .subtract(INSSMonthlyQuotaInUSD);


        log.info("Rip monies, out of ${} per month",
                MonthlySalaryInUSD);
        log.info("You have to pay an IR tax of ${} & ${} of INSS",
                monthlyTaxToPayInUSD, INSSMonthlyQuotaInUSD);
        log.info("You only get to take home ${}",monthlyTakeHomeSalaryInUSD);
    }

    //https://www.dgi.gob.ni/FAQ/index.html?retenciones_definitivas.htm
/*
 El mecanismo básicamente es el mismo, usted parte del ingreso mensual menos
 las deducciones que permite la Ley (INSS + Fondos de ahorro), proyecta a 12 meses
 para obtener la expectativa anual, ubica en la tabla según el resultado y le resta
 el ingreso base según el estrato, aplica el porcentaje al excedente de la base, al resultado le suma el impuesto base.
*/
}
