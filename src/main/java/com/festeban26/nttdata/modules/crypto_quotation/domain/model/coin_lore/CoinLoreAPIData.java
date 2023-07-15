package com.festeban26.nttdata.modules.crypto_quotation.domain.model.coin_lore;

import java.math.BigDecimal;

public record CoinLoreAPIData(
        BigDecimal currentPrice
) {
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
}
