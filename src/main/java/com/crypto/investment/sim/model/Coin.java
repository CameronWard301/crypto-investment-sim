package com.crypto.investment.sim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coin {
    @Id
    @GeneratedValue
    private int CoinID;
    private String CoinCode;
    private String Name;
    private float CurrentPrice;

    public int getCoinID() {
        return CoinID;
    }
    public void setCoinID(int coinID) {
        CoinID = coinID;
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