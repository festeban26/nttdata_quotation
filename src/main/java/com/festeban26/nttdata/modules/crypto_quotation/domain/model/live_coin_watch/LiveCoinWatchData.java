package com.festeban26.nttdata.modules.crypto_quotation.domain.model.live_coin_watch;

import java.math.BigDecimal;

public record LiveCoinWatchData(BigDecimal lastPrice) {
    public BigDecimal getCurrentPrice() {
        return this.lastPrice;
    }
}
