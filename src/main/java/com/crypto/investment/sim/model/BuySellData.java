package com.crypto.investment.sim.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@SuppressWarnings("unused")
public class BuySellData {
    private String convertFrom;
    private String convertTo;
    private String convertFromAmount;
    private String estimatedExchange;

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

    public String  getConvertFromAmount() {
        return convertFromAmount;
    }

    public void setConvertFromAmount(String convertFromAmount) {
        this.convertFromAmount = convertFromAmount;
    }

    public String getEstimatedExchange() {
        return estimatedExchange;
    }

    public void setEstimatedExchange(String estimatedExchange) {
        this.estimatedExchange = estimatedExchange;
    }

    public float calculateExchange(String fromCurrency, String toCurrency, float fromAmount, float fromCoinValue, float toCoinValue){
        float calculatedAmount;
        if (Objects.equals(fromCurrency, "GBP") || Objects.equals(fromCurrency, "USD") || Objects.equals(fromCurrency, "EUR")){
            calculatedAmount = fromAmount*(toCoinValue/fromCoinValue);
        } else {
            calculatedAmount = (fromAmount/fromCoinValue)*toCoinValue;
        }
        if (Objects.equals(toCurrency, "GBP") || Objects.equals(toCurrency, "USD") || Objects.equals(toCurrency, "EUR")){
            return this.roundDown(calculatedAmount);
        }
        return calculatedAmount;
    }

    public float roundDown(float amount){
        BigDecimal bd = new BigDecimal(Float.toString(amount));
        bd = bd.setScale(2, RoundingMode.HALF_DOWN);
        return bd.floatValue();
    }
}
