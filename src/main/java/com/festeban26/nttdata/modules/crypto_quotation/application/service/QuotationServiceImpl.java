package com.festeban26.nttdata.modules.crypto_quotation.application.service;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Cryptocurrency;
import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Quotation;
import com.festeban26.nttdata.modules.crypto_quotation.domain.repository.QuotationRepository;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.cache.QuotationCache;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client.CoinAPI;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client.CoinLoreAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class QuotationServiceImpl implements QuotationService {
    private static final Logger logger = LoggerFactory.getLogger(QuotationServiceImpl.class);

    private final CoinAPI coinAPI;
    private final CoinLoreAPI coinLoreAPI;
    private final QuotationCache quotationCache;
    private final QuotationRepository quotationRepository;

    public QuotationServiceImpl(CoinAPI coinAPI, CoinLoreAPI coinLoreAPI,
                                QuotationCache quotationCache, QuotationRepository quotationRepository) {
        this.coinAPI = coinAPI;
        this.coinLoreAPI = coinLoreAPI;
        this.quotationCache = quotationCache;
        this.quotationRepository = quotationRepository;
    }

    @Override
    public Mono<Quotation> getQuotation(String conversionId, Cryptocurrency crypto, BigDecimal value) {

        Quotation cachedQuotation = quotationCache.getQuotation(conversionId);

        if (cachedQuotation != null) {
            logger.info("Retrieving quotation from cache for conversionId: {}", conversionId);
            return Mono.just(cachedQuotation);
        }

        logger.info("Fetching quotation from API for conversionId: {}", conversionId);
        return fetchQuotationFromAPI(crypto)
                .map(price -> createQuotation(conversionId, crypto, value, price))
                .map(quotation -> {
                    quotationCache.saveQuotation(conversionId, quotation);
                    quotationRepository.save(quotation);
                    return quotation;
                });
    }


    private Mono<BigDecimal> fetchQuotationFromAPI(Cryptocurrency crypto) {
        return coinAPI.getCurrentPrice(crypto)
                .doOnNext(price -> logger.info("Quotation fetched from CoinAPI for {}: {}", crypto, price))
                .onErrorResume(throwable -> {
                    logger.info("Fetching quotation from CoinAPI failed for {}. Trying CoinLoreAPI...", crypto);
                    return coinLoreAPI.getCurrentPrice(crypto)
                            .doOnNext(price -> logger.info("Quotation fetched from CoinLoreAPI for {}: {}", crypto, price));
                })
                .doOnError(throwable -> logger.error("Error while fetching quotation from API for crypto: {}", crypto));
    }

    private Quotation createQuotation(String conversionId, Cryptocurrency crypto, BigDecimal value, BigDecimal price) {
        BigDecimal convertedValue = value.divide(price, 2, RoundingMode.HALF_UP);
        return new Quotation(conversionId, crypto, value, convertedValue);
    }
}