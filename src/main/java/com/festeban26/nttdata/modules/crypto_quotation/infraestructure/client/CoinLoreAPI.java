package com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Cryptocurrency;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface CoinLoreAPI {
    Mono<BigDecimal> getCurrentPrice(Cryptocurrency crypto);
}
