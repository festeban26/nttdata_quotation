package com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client;

import com.festeban26.nttdata.modules.crypto_quotation.domain.model.Cryptocurrency;
import com.festeban26.nttdata.modules.crypto_quotation.domain.model.live_coin_watch.LiveCoinWatchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class CoinAPIImpl implements CoinAPI {
    private static final Logger logger = LoggerFactory.getLogger(CoinAPIImpl.class);

    private final WebClient webClient;
    private final String apiUrl;

    public CoinAPIImpl(WebClient webClient, String apiUrl) {
        this.webClient = webClient;
        this.apiUrl = apiUrl;
    }

    @Override
    public Mono<BigDecimal> getCurrentPrice(Cryptocurrency crypto) {
        String uri = buildUri(getApiUrl(), crypto);
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(LiveCoinWatchResponse.class)
                .map(liveCoinWatchResponse -> {
                    logger.info("Quotation received from CoinAPI for crypto: {}", crypto);
                    return liveCoinWatchResponse.data().getCurrentPrice();
                });
    }

    private String buildUri(String apiUrl, Cryptocurrency crypto) {
        return String.format("%s%s/about?currency=USD", apiUrl, crypto.getSymbol());
    }

    public String getApiUrl() {
        return apiUrl;
    }
}