package com.crypto.investment.sim.model;

@SuppressWarnings("unused")
public class UserAddFunds {
    private String value;
    private String GBP;
    private String USD;
    private String EUR;
    private String fiat;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGBP() {
        return GBP;
    }

    public void setGBP(String GBP) {
        this.GBP = GBP;
    }

    public String getUSD() {
        return USD;
    }

    public void setUSD(String USD) {
        this.USD = USD;
    }

    public String getEUR() {
        return EUR;
    }

    public void setEUR(String EUR) {
        this.EUR = EUR;
    }

    public String getFiat() {
        return fiat;
    }

    public void setFiat (String fiat) {
        this.fiat = fiat;
    }
}
