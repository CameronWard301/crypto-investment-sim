package com.crypto.investment.sim.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "crypto.api.key")
public class API_KEYS {
    private String freecurrencyAPI;
    private String coinmarketcapAPI;

    public String getFreecurrencyAPI() {
        return freecurrencyAPI;
    }

    public void setFreecurrencyAPI(String freecurrencyAPI) {
        this.freecurrencyAPI = freecurrencyAPI;
    }

    public String getCoinmarketcapAPI() {
        return coinmarketcapAPI;
    }

    public void setCoinmarketcapAPI(String coinmarketcapAPI) {
        this.coinmarketcapAPI = coinmarketcapAPI;
    }
}
