package com.festeban26.nttdata.modules.crypto_quotation.infraestructure.cache;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuotationCacheImpl implements QuotationCache {
    private static final Logger logger = LoggerFactory.getLogger(QuotationCacheImpl.class);

    private final Map<String, Quotation> cache;

    public QuotationCacheImpl() {
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public void saveQuotation(String conversionId, Quotation quotation) {
        cache.put(conversionId, quotation);
        logger.info("Quotation saved in cache for conversionId: {}", conversionId);
    }

    @Override
    public Quotation getQuotation(String conversionId) {
        Quotation quotation = cache.get(conversionId);
        if (quotation != null) {
            logger.info("Quotation retrieved from cache for conversionId: {}", conversionId);
        }
        return quotation;
    }
}
