package com.festeban26.nttdata.modules.crypto_quotation.domain.model;

import java.math.BigDecimal;

public record Quotation(String conversionId,
                        Cryptocurrency crypto,
                        BigDecimal value,
                        BigDecimal convertedValue) {
}