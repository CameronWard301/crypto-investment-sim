package com.crypto.investment.sim.model;

import java.util.Optional;

public class UserAddFunds {
    private float value;
    private float GBP;
    private float USD;
    private float EUR;
    private Optional<Integer> fiat;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getGBP() {
        return GBP;
    }

    public void setGBP(float GBP) {
        this.GBP = GBP;
    }

    public float getUSD() {
        return USD;
    }

    public void setUSD(float USD) {
        this.USD = USD;
    }

    public float getEUR() {
        return EUR;
    }

    public void setEUR(float EUR) {
        this.EUR = EUR;
    }

    public Optional<Integer> getFiat() {
        return fiat;
    }

    public void setFiat(Optional<Integer> fiat) {
        this.fiat = fiat;
    }
}
