package com.devdowns.nicaraguantaxcalculator;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public final class NIOTaxBracket {
    private final BigDecimal lowEnd;
    private final BigDecimal highEnd;
    private final BigDecimal baseTax;
    private final BigDecimal percentageApplicable;

    private NIOTaxBracket(BigDecimal lowEnd, BigDecimal highEnd, BigDecimal baseTax, BigDecimal percentageApplicable) {
        this.lowEnd = lowEnd;
        this.highEnd = highEnd;
        this.baseTax = baseTax;
        this.percentageApplicable = percentageApplicable;
    }

    public static final List<NIOTaxBracket> getTaxBrackets() {
        return List.of(
                new NIOTaxBracket(new BigDecimal(0.01), new BigDecimal(100_000.00), BigDecimal.ZERO, BigDecimal.ZERO),
                new NIOTaxBracket(new BigDecimal(100_000.01), new BigDecimal(200_000.00), BigDecimal.ZERO, new BigDecimal(0.15)),
                new NIOTaxBracket(new BigDecimal(200_000.01), new BigDecimal(350_000.00), new BigDecimal(15_000.00), new BigDecimal(0.20)),
                new NIOTaxBracket(new BigDecimal(350_000.01), new BigDecimal(500_000.00), new BigDecimal(45_000.00), new BigDecimal(0.25)),
                new NIOTaxBracket(new BigDecimal(500_000.01), new BigDecimal(100_000_000.00), new BigDecimal(82_500.00), new BigDecimal(0.30))
        );
    }
}
