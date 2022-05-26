package com.devdowns.nicaraguantaxcalculator;

import java.math.BigDecimal;
//refer to this link for the table https://www.dgi.gob.ni/FAQ/index.html?periodo_fiscal_y_tarifa_del_im.htm
public interface TaxBrackets {
     NIOTaxBracket firstBracket = new NIOTaxBracket(new BigDecimal(0.01), new BigDecimal(100_000.00), BigDecimal.ZERO,BigDecimal.ZERO);
     NIOTaxBracket secondBracket = new NIOTaxBracket(new BigDecimal(100_000.01), new BigDecimal(200_000.00), BigDecimal.ZERO, new BigDecimal(0.15));
     NIOTaxBracket thirdBracket = new NIOTaxBracket(new BigDecimal(200_000.01), new BigDecimal(350_000.00), new BigDecimal(15_000.00), new BigDecimal(0.20));
     NIOTaxBracket fourthBracket = new NIOTaxBracket(new BigDecimal(350_000.01), new BigDecimal(500_000.00), new BigDecimal(45_000.00), new BigDecimal(0.25));
     NIOTaxBracket fifthBracket = new NIOTaxBracket(new BigDecimal(500_000.01), new BigDecimal(100_000_000.00), new BigDecimal(82_500.00), new BigDecimal(0.30));
}
