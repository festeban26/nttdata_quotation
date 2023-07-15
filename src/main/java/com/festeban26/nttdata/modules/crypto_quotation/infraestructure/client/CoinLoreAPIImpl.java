package com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.coin_lore.CoinLoreAPIData;
import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Cryptocurrency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class CoinLoreAPIImpl implements CoinLoreAPI {
    private static final Logger logger = LoggerFactory.getLogger(CoinLoreAPIImpl.class);

    private final WebClient webClient;
    private final String apiUrl;

    public CoinLoreAPIImpl(WebClient webClient, String apiUrl) {
        this.webClient = webClient;
        this.apiUrl = apiUrl;
    }

    @Override
    public Mono<BigDecimal> getCurrentPrice(Cryptocurrency crypto) {
        return webClient.get()
                .uri(apiUrl + crypto)
                .retrieve()
                .bodyToMono(CoinLoreAPIData.class)
                .map(data -> {
                    logger.info("Quotation received from CoinLoreAPI for crypto: {}", crypto);
                    return data.getCurrentPrice();
                });
    }
}
