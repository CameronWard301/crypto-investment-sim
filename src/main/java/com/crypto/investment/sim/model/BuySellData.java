package com.crypto.investment.sim.model;

@SuppressWarnings("unused")
public class BuySellData {
    private String convertFrom;
    private String convertTo;
    private float convertFromAmount;
    private float estimatedExchange;

    public String getConvertFrom() {
        return convertFrom;
    }

    public void setConvertFrom(String convertFrom) {
        this.convertFrom = convertFrom;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }

    public float getConvertFromAmount() {
        return convertFromAmount;
    }

    public void setConvertFromAmount(float convertFromAmount) {
        this.convertFromAmount = convertFromAmount;
    }

    public float getEstimatedExchange() {
        return estimatedExchange;
    }

    public void setEstimatedExchange(float estimatedExchange) {
        this.estimatedExchange = estimatedExchange;
    }
}
