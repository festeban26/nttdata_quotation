package com.festeban26.nttdata.modules.crypto_quotation.domain.model;

public enum Cryptocurrency {
    BTC("BTC"),
    ETH("ETH");

    private final String symbol;

    Cryptocurrency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Cryptocurrency fromSymbol(String symbol) {
        for (Cryptocurrency cryptocurrency : Cryptocurrency.values()) {
            if (cryptocurrency.getSymbol().equalsIgnoreCase(symbol)) {
                return cryptocurrency;
            }
        }
        return null;
    }
}
