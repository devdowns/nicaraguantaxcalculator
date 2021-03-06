package com.devdowns.nicaraguantaxcalculator.service;

import com.devdowns.nicaraguantaxcalculator.NIOTaxBracket;
import com.devdowns.nicaraguantaxcalculator.SalaryInformation;
import com.devdowns.nicaraguantaxcalculator.SalaryDetail;
import com.devdowns.nicaraguantaxcalculator.validator.SalaryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryDetailService {

    private final SalaryValidator salaryValidator;
    private final BigDecimal USDToNIOExchangeRate = new BigDecimal(35.77);

    public NIOTaxBracket findTaxBracket(SalaryInformation salary){
        final var baseSalary = salary.getBaseUSDSalary();
        for(NIOTaxBracket bracket : NIOTaxBracket.getTaxBrackets()){
            if(baseSalary.compareTo(bracket.getLowEnd()) >=0 && baseSalary.compareTo(bracket.getHighEnd()) <= 0){
                log.info("bracket selected base tax {}", bracket.getBaseTax().toString());
                return bracket;
            }
        }
        return null;
    }

    public SalaryDetail getSalaryDetails(SalaryInformation salary){
//        if(!salaryValidator.isValid(salary)){
//            throw new RuntimeException("You forgot to add some fields we need monthlySalaryInUSD, USDToNIOExchangeRate & isPayingForINNS");
//        }
        log.info("hit service");
        final var taxBracket = NIOTaxBracket.getTaxBrackets().get(4);
        log.info(taxBracket.getBaseTax().toString());
        final var INSS = salary.getIsPayingForINSS() ? new BigDecimal(0.0625) : BigDecimal.ZERO;
        final var monthsPerYear = new BigDecimal(12);
        final var monthlySalaryInNIO = salary.getBaseUSDSalary().multiply(USDToNIOExchangeRate);
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

        final var monthlyTakeHomeSalaryInUSD = salary.getBaseUSDSalary()
                .subtract(monthlyTaxToPayInUSD)
                .subtract(INSSMonthlyQuotaInUSD);

        return new SalaryDetail(salary.getBaseUSDSalary(), monthlyTaxToPayInUSD, INSSMonthlyQuotaInUSD, monthlyTakeHomeSalaryInUSD);
    }
}
