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
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class CryptoInvestmentSimApplication implements ApplicationRunner, WebMvcConfigurer {

    public static Coin BTC = new Coin("BTC", "Bitcoin");
    public static Coin ETH = new Coin("ETH", "Ethereum");
    public static Coin ADA = new Coin("ADA", "Cardano");
    public static Coin GBP = new Coin("GBP", "Pounds", 1);
    public static Coin EUR = new Coin("EUR", "Euros");
    public static Coin USD = new Coin("USD", "Dollars");

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;


    public static void main(String[] args){

        SpringApplication.run(CryptoInvestmentSimApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Register resource handler for images
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS).cachePublic()); //TODO: Change cache timeout

        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS).cachePublic()); //TODO: Change cache timeout
    }

    @Override
    public void run(ApplicationArguments args) {
        //Check if coins have already been stored in the DB, if not then store the initial values
        Optional<Coin> coins = coinRepo.findById("BTC");
        if (coins.isEmpty()){
            BTC = coinRepo.save(BTC);
            ETH = coinRepo.save(ETH);
            ADA = coinRepo.save(ADA);
            GBP = coinRepo.save(GBP);
            EUR = coinRepo.save(EUR);
            USD = coinRepo.save(USD);
        }

//        User testUser = new User();
//        testUser.setName("MrTestUser");
//        testUser.setUsername("testUsername");
//        testUser.setEUR(50);
//        testUser.setBitcoin((float) 21.5674444);
//        userRepo.save(testUser);
    }
}
