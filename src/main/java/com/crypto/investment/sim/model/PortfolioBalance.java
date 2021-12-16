package com.crypto.investment.sim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
public class PortfolioBalance implements Serializable {
    @Id
    @GeneratedValue
    private int balanceID;
    private float portfolioTotal;
    private Date timestamp;

    public PortfolioBalance(){
        this.timestamp = new Date();
        this.portfolioTotal = 0;
    }

    public PortfolioBalance(float total){
        this.timestamp = new Date();
        this.portfolioTotal = total;
    }

    public int getBalanceID() {
        return balanceID;
    }

    public void setBalanceID(int balanceID) {
        this.balanceID = balanceID;
    }

    public float getPortfolioTotal() {
        return portfolioTotal;
    }

    public void setPortfolioTotal(float portfolioTotal) {
        this.portfolioTotal = portfolioTotal;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
