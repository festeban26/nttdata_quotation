package com.festeban26.nttdata.modules.crypto_quotation.application.service;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Cryptocurrency;
import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Quotation;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface QuotationService {
    Mono<Quotation> getQuotation(String conversionId, Cryptocurrency cryptocurrency, BigDecimal value);
}
