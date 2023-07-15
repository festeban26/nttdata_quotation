package com.festeban26.nttdata;

import com.festeban26.nttdata.modules.crypto_quotation.application.service.QuotationService;
import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Cryptocurrency;
import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Quotation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/")
public class NttdataApplicationController {

    private final QuotationService quotationService;

    public NttdataApplicationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @GetMapping()
    public Mono<ResponseEntity<Quotation>> get() {

        String conversionId = "12345"; // Provide a conversion ID
        Cryptocurrency cryptocurrency = Cryptocurrency.BTC; // Provide a cryptocurrency
        BigDecimal value = new BigDecimal("1000"); // Provide a value in USD

        Mono<Quotation> quotationMono = quotationService.getQuotation(conversionId, cryptocurrency, value);

        return quotationMono.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
