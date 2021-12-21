package com.crypto.investment.sim.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "coin")
public class CoinRequestSettings {
    private boolean requests;
    private int ADA_BTC;
    private int USD_EUR_ETH;

    public boolean isRequests() {
        return !requests;
    }

    public void setRequests(boolean requests) {
        this.requests = requests;
    }

    public int getADA_BTC() {
        return ADA_BTC;
    }

    public void setADA_BTC(int ADA_BTC) {
        this.ADA_BTC = ADA_BTC;
    }

    public int getUSD_EUR_ETH() {
        return USD_EUR_ETH;
    }

    public void setUSD_EUR_ETH(int USD_EUR_ETH) {
        this.USD_EUR_ETH = USD_EUR_ETH;
    }
}
