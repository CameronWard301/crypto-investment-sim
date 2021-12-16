package com.crypto.investment.sim.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("unused")
@Entity
public class Coin {

    @Id
    private String CoinCode;
    private String Name;
    private float CurrentPrice; // in pounds

    public Coin() {
    }
    public Coin(String coinCode, String name) {
        CoinCode = coinCode;
        Name = name;
    }

    public Coin(String coinCode, String name, float currentPrice){
        CoinCode = coinCode;
        Name = name;
        CurrentPrice = currentPrice;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public float getCurrentPrice() {
        return CurrentPrice;
    }
    public void setCurrentPrice(float currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getCoinCode() {
        return CoinCode;
    }

    public void setCoinCode(String coinCode) {
        CoinCode = coinCode;
    }
}