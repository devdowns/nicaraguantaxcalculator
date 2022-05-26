package com.devdowns.nicaraguantaxcalculator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class CodeRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        final var taxBracket = TaxBrackets.fifthBracket;
        final var baseSalary = new BigDecimal(2100);
        final var USDToNIOExchangeRate = new BigDecimal(36);
        final var isPayingForINSS = true;

        final var mySalary = new Salary(taxBracket, baseSalary,USDToNIOExchangeRate,isPayingForINSS);
        mySalary.computeTaxes();

    }
}





