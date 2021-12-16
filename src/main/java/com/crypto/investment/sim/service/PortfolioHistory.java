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

/**
 * Service that gets each user's total portfolio balance and calculates the value in £. Then stores this
 * in teh DB with a timestamp for each user
 */
@Service
public class PortfolioHistory {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

//    @Scheduled(fixedRate = 60 * 60 * 1000)
    @Scheduled(fixedRate = 300000)
    public void generateHistory(){
        List<User> users = (List<User>) userRepo.findAll();
        Optional<Coin> DbUSD = coinRepo.findById("USD");
        Optional<Coin> DbEUR = coinRepo.findById("EUR");
        Optional<Coin> DbBTC = coinRepo.findById("BTC");
        Optional<Coin> DbADA = coinRepo.findById("ADA");
        Optional<Coin> DbETH = coinRepo.findById("ETH");

        if (DbUSD.isEmpty() || DbEUR.isEmpty() || DbBTC.isEmpty() || DbADA.isEmpty() || DbETH.isEmpty()){
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
            float btc = theUser.getBitcoin();
            float eth = theUser.getEthereum();
            float ada = theUser.getCardano();
            float total = gbp + usd*USD + eur*EUR + btc*BTC + ada*ADA + eth*ETH;
            PortfolioBalance balance = new PortfolioBalance(total);
            List<PortfolioBalance> history = theUser.getPortfolioHistory();
            history.add(balance);
            userRepo.save(theUser);

        }
    }

}
