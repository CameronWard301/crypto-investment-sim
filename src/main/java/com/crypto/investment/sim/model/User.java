package com.crypto.investment.sim.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String username;
    private String hashPassword;
    private float bitcoin;
    private float ethereum;
    private float cardano;
    private float GBP;
    private float USD;
    private float EUR;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(@NotNull String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public float getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(float bitcoin) {
        this.bitcoin = bitcoin;
    }

    public float getEthereum() {
        return ethereum;
    }

    public void setEthereum(float ethereum) {
        this.ethereum = ethereum;
    }

    public float getCardano() {
        return cardano;
    }

    public void setCardano(float cardano) {
        this.cardano = cardano;
    }

    public double getGBP() {
        return GBP;
    }

    public void setGBP(float GBP) {
        this.GBP = GBP;
    }

    public double getUSD() {
        return USD;
    }

    public void setUSD(float USD) {
        this.USD = USD;
    }

    public double getEUR() {
        return EUR;
    }

    public void setEUR(float EUR) {
        this.EUR = EUR;
    }
}
