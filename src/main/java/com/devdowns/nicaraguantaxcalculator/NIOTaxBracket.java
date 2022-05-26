package com.devdowns.nicaraguantaxcalculator;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data @AllArgsConstructor
public final class NIOTaxBracket {
    private final BigDecimal lowEnd;
    private final BigDecimal highEnd;
    private final BigDecimal baseTax;
    private final BigDecimal percentageApplicable;
}
