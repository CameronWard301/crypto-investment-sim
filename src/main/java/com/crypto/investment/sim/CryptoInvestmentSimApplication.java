package com.crypto.investment.sim;

import com.crypto.investment.sim.model.Coin;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoInvestmentSimApplication implements ApplicationRunner {

    public static Coin BTC = new Coin("BTC", "Bitcoin");
    public static Coin ETH = new Coin("ETC", "Ethereum");
    public static Coin ADA = new Coin("ADA", "Cardano");
    public static Coin GBP = new Coin("GBP", "Pounds", 1);
    public static Coin EUR = new Coin("EUR", "Euros");
    public static Coin USD = new Coin("USD", "Dollars");

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    @Autowired
    public UserRepository userRepo;


    public static void main(String[] args){

        SpringApplication.run(CryptoInvestmentSimApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        BTC = coinRepo.save(BTC);
        ETH = coinRepo.save(ETH);
        ADA = coinRepo.save(ADA);
        GBP = coinRepo.save(GBP);
        EUR = coinRepo.save(EUR);
        USD = coinRepo.save(USD);



        User testUser = new User();
        testUser.setUsername("testUsername");
        testUser.setEUR(50);
        testUser.setBitcoin((float) 21.5674444);
        userRepo.save(testUser);
    }
}
