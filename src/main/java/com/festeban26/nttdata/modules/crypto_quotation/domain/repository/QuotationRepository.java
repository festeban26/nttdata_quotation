package com.festeban26.nttdata.modules.crypto_quotation.domain.repository;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Quotation;

/**
 * Purpose: The QuotationRepository interface and its implementation, QuotationRepositoryImpl,
 * are responsible for persisting quotations and retrieving them when needed.
 * <p>
 * Functionality: The repository provides a layer of abstraction for interacting with a data store,
 * such as a database or a file system. It ensures that the application can save quotations
 * and retrieve them from a reliable and consistent source.
 * <p>
 * Lifetime: The repository typically has a longer lifespan and maintains data integrity.
 * It is designed to handle various data access operations, such as CRUD (Create, Read, Update, Delete) operations,
 * and may involve additional logic for data management.
 */
public interface QuotationRepository {
    Quotation save(Quotation quotation);

    Quotation findById(String conversionId);
}