package com.crypto.investment.sim.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String hashPassword;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn
    private List<PortfolioBalance> portfolioHistory;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<PortfolioBalance> getPortfolioHistory() {
        return portfolioHistory;
    }

    public void setPortfolioHistory(List<PortfolioBalance> portfolioHistory) {
        this.portfolioHistory = portfolioHistory;
    }
}
