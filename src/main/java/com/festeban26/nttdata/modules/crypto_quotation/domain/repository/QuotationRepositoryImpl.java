package com.festeban26.nttdata.modules.crypto_quotation.domain.repository;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Quotation;

import java.util.HashMap;
import java.util.Map;

public class QuotationRepositoryImpl implements QuotationRepository {
    private final Map<String, Quotation> quotationMap;

    public QuotationRepositoryImpl() {
        this.quotationMap = new HashMap<>();
    }

    @Override
    public Quotation save(Quotation quotation) {
        quotationMap.put(quotation.conversionId(), quotation);
        return quotation;
    }

    @Override
    public Quotation findById(String conversionId) {
        return quotationMap.get(conversionId);
    }
}