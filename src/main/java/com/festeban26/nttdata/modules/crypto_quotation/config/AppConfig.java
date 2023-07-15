package com.festeban26.nttdata.modules.crypto_quotation.config;

import com.festeban26.nttdata.modules.crypto_quotation.application.service.QuotationService;
import com.festeban26.nttdata.modules.crypto_quotation.application.service.QuotationServiceImpl;
import com.festeban26.nttdata.modules.crypto_quotation.domain.repository.QuotationRepository;
import com.festeban26.nttdata.modules.crypto_quotation.domain.repository.QuotationRepositoryImpl;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.cache.QuotationCache;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.cache.QuotationCacheImpl;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client.CoinAPI;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client.CoinAPIImpl;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client.CoinLoreAPI;
import com.festeban26.nttdata.modules.crypto_quotation.infraestructure.client.CoinLoreAPIImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    private static final String coinApiUrl = "https://http-api.livecoinwatch.com/coins/";
    private static final String coinLoreApiUrl = "https://api.coinlore.net/api/ticker/?id=";

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public CoinAPI coinAPI(WebClient webClient) {
        return new CoinAPIImpl(webClient, coinApiUrl);
    }

    @Bean
    public CoinLoreAPI coinLoreAPI(WebClient webClient) {
        return new CoinLoreAPIImpl(webClient, coinLoreApiUrl);
    }

    @Bean
    public QuotationCache quotationCache() {
        return new QuotationCacheImpl();
    }

    @Bean
    public QuotationRepository quotationRepository() {
        return new QuotationRepositoryImpl();
    }

    @Bean
    public QuotationService quotationService(CoinAPI coinAPI, CoinLoreAPI coinLoreAPI,
                                             QuotationCache quotationCache, QuotationRepository quotationRepository) {
        return new QuotationServiceImpl(coinAPI, coinLoreAPI, quotationCache, quotationRepository);
    }
}
