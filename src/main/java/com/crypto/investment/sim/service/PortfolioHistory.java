package com.crypto.investment.sim.service;

import com.crypto.investment.sim.model.Coin;
import com.crypto.investment.sim.model.PortfolioBalance;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service that gets each user's total portfolio balance and calculates the value in £. Then stores this
 * in teh DB with a timestamp for each user
 */
@Service
public class PortfolioHistory {

    final Logger logger = LoggerFactory.getLogger(PortfolioHistory.class);

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

//    @Scheduled(fixedRate = 60 * 60 * 1000)
    @Scheduled(fixedRateString = "${portfolio.history.frequency:30000}")
    public void generateHistory(){
        logger.info("Running generate portfolio task");
        long startTime = System.nanoTime();
        List<User> users = (List<User>) userRepo.findAll();
        Optional<Coin> DbUSD = coinRepo.findById("USD");
        Optional<Coin> DbEUR = coinRepo.findById("EUR");
        Optional<Coin> DbBTC = coinRepo.findById("BTC");
        Optional<Coin> DbADA = coinRepo.findById("ADA");
        Optional<Coin> DbETH = coinRepo.findById("ETH");

        if (DbUSD.isEmpty() || DbEUR.isEmpty() || DbBTC.isEmpty() || DbADA.isEmpty() || DbETH.isEmpty()){
            logger.error("Could not generate user portfolio history, they're no coin values in the database");
            return;
        }
        float USD = DbUSD.get().getCurrentPrice();
        float EUR = DbEUR.get().getCurrentPrice();
        float BTC = DbBTC.get().getCurrentPrice();
        float ADA = DbADA.get().getCurrentPrice();
        float ETH = DbETH.get().getCurrentPrice();

        for (User theUser: users){
            float gbp = theUser.getGBP();
            float usd = theUser.getUSD();
            float eur = theUser.getEUR();
            float btc = theUser.getBTC();
            float eth = theUser.getETH();
            float ada = theUser.getADA();
            float total = gbp + usd*USD + eur*EUR + (btc/BTC) + (ada/ADA) + (eth/ETH);
            if (Float.isNaN(total) || total == 0){
                continue; //Don't record history if portfolio is 0
            }
            PortfolioBalance balance = new PortfolioBalance(total);
            List<PortfolioBalance> history = theUser.getPortfolioHistory();
            history.add(balance);
            userRepo.save(theUser);
        }
        long elapsedTime = (System.nanoTime() - startTime)/1000000;
        logger.info("Generated portfolio history for all users in: " + elapsedTime + "ms");
    }

}
