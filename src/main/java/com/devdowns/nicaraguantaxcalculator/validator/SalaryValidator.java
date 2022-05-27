package com.devdowns.nicaraguantaxcalculator.validator;

import com.devdowns.nicaraguantaxcalculator.Salary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
@Component
public class SalaryValidator {

    Predicate<BigDecimal> isZero = value -> (value.compareTo(BigDecimal.ZERO)==0);
    Predicate<BigDecimal> isNegative = value -> (value.compareTo(BigDecimal.ZERO) < 0);


    public boolean isValid(Salary salary){

       List<Object> salaryFields = List.of(

                                    salary.getMonthlySalaryInUSD(),
                                    salary.getUSDToNIOExchangeRate(),
                                    salary.getIsPayingForINSS());

       boolean isNotNull = salaryFields.stream().anyMatch(Objects::isNull);
       boolean isNotNegativeValue = salaryFields.stream()
                                    .filter(field -> field instanceof BigDecimal)
                                    .map(field -> new BigDecimal(((BigDecimal) field).floatValue()))
                                    .anyMatch(value->(isZero.test(value) || isNegative.test(value)));

       return isNotNull || isNotNegativeValue;
    }
}
