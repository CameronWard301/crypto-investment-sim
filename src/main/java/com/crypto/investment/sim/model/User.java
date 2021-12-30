package com.crypto.investment.sim.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Iterator;
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

    @OneToMany (fetch = FetchType.EAGER,  orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn
    private List<PortfolioBalance> portfolioHistory;

    private float BTC;
    private float ETH;
    private float ADA;
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

    public float getBTC() {
        return BTC;
    }

    public void setBTC(float bitcoin) {
        this.BTC = bitcoin;
    }

    public float getETH() {
        return ETH;
    }

    public void setETH(float ethereum) {
        this.ETH = ethereum;
    }

    public float getADA() {
        return ADA;
    }

    public void setADA(float cardano) {
        this.ADA = cardano;
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

    /**
     * Iterates through the portfolioHistory and removes each element from the list
     */
    public void removePortfolioHistory(){
        for (Iterator<PortfolioBalance> balanceIterator = portfolioHistory.iterator(); balanceIterator.hasNext();){
            PortfolioBalance balance = balanceIterator.next();
            balanceIterator.remove();
        }
    }
}
