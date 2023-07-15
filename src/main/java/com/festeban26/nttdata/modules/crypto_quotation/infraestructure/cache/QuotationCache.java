package com.festeban26.nttdata.modules.crypto_quotation.infraestructure.cache;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Quotation;


/**
 * Purpose: The QuotationCache interface and its implementation, QuotationCacheImpl,
 * are responsible for caching quotations in memory.
 * <p>
 * Functionality: The cache is used to store recently retrieved quotations, reducing
 * the need to fetch them from external sources repeatedly. It helps improve performance
 * by providing quick access to frequently accessed data.
 * <p>
 * Lifetime: The cache typically has a shorter lifespan and may hold a limited number
 * of entries. It's suitable for storing data that is frequently accessed and expected
 * to change frequently.
 */
public interface QuotationCache {
    void saveQuotation(String conversionId, Quotation quotation);

    Quotation getQuotation(String conversionId);
}
