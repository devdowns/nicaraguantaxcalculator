package com.devdowns.nicaraguantaxcalculator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
@Data
public class Salary {


    private final BigDecimal monthlySalaryInUSD;
    private final BigDecimal USDToNIOExchangeRate;
    private final Boolean isPayingForINSS;




    //https://www.dgi.gob.ni/FAQ/index.html?retenciones_definitivas.htm
/*
 El mecanismo básicamente es el mismo, usted parte del ingreso mensual menos
 las deducciones que permite la Ley (INSS + Fondos de ahorro), proyecta a 12 meses
 para obtener la expectativa anual, ubica en la tabla según el resultado y le resta
 el ingreso base según el estrato, aplica el porcentaje al excedente de la base, al resultado le suma el impuesto base.
*/
}
