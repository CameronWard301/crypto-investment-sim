package com.crypto.investment.sim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coin {
    @Id
//    @GeneratedValue
//    private int CoinID;
    private String CoinCode;
    private String Name;
    private double CurrentPrice; // in pounds

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
//    public int getCoinID() {
//        return CoinID;
//    }
//    public void setCoinID(int coinID) {
//        CoinID = coinID;
//    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public double getCurrentPrice() {
        return CurrentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getCoinCode() {
        return CoinCode;
    }

    public void setCoinCode(String coinCode) {
        CoinCode = coinCode;
    }
}