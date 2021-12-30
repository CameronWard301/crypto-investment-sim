package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.Coin;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection"})
@Controller
public class UserController implements Serializable {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public CoinRepository coinRepo;


    @GetMapping("/portfolio")
    public String viewPortfolio(Model model, HttpSession session) {
        Object USER_SESSION = session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            session.setAttribute("message", "Please login and try again");
            return "redirect:/login";
        }

        //Get the User from the database
        User oldUser = (User) USER_SESSION;
        Optional<User> theUser = userRepo.findById(oldUser.getId()); //get any updates on their portfolio history
        if (theUser.isEmpty()) return "redirect:/"; //redirect if error

        JSONObject history = new JSONObject();
        history.put("history", theUser.get().getPortfolioHistory()); //Add the history to a JSON object

        List<Coin> coinValues = this.getLatestCoins();
        float BTCAmount = theUser.get().getBTC();
        float ETHAmount = theUser.get().getETH();
        float ADAAmount = theUser.get().getADA();
        float GBPAmount = theUser.get().getGBP();
        float USDAmount = theUser.get().getUSD();
        float EURAmount = theUser.get().getEUR();
        float BTCValue = BTCAmount/coinValues.get(0).getCurrentPrice();
        float ETHValue = ETHAmount/coinValues.get(1).getCurrentPrice();
        float ADAValue = ADAAmount/coinValues.get(2).getCurrentPrice();
        float BTCPercentage = (BTCValue / (BTCValue + ETHValue + ADAValue))*100;
        float ETHPercentage = (ETHValue / (BTCValue + ETHValue + ADAValue))*100;
        float ADAPercentage = (ADAValue / (BTCValue + ETHValue + ADAValue))*100;

        //check if divide by zero occurred. If so set the values to 0 rather than NaN
        if (BTCValue + ETHValue + ADAValue == 0){
            BTCPercentage = 0;
            ETHPercentage = 0;
            ADAPercentage = 0;
        }

        model.addAttribute("BTCAmount", BTCAmount);
        model.addAttribute("ETHAmount", ETHAmount);
        model.addAttribute("ADAAmount", ADAAmount);
        model.addAttribute("GBPAmount", this.roundFloat(GBPAmount));
        model.addAttribute("USDAmount", this.roundFloat(USDAmount));
        model.addAttribute("EURAmount", this.roundFloat(EURAmount));
        model.addAttribute("BTCValue", this.roundFloat(BTCValue));
        model.addAttribute("ETHValue", this.roundFloat(ETHValue));
        model.addAttribute("ADAValue", this.roundFloat(ADAValue));
        model.addAttribute("BTCPercentage", this.roundFloat(BTCPercentage));
        model.addAttribute("ETHPercentage", this.roundFloat(ETHPercentage));
        model.addAttribute("ADAPercentage", this.roundFloat(ADAPercentage));
        model.addAttribute("portfolioHistory", history);
        model.addAttribute("firstName", theUser.get().getFirstName());
        session.setAttribute("USER_SESSION", theUser.get()); //Update the Session


        //Check if there account has just been created or have just reset portfolio
        if (session.getAttribute("message") != null){
            model.addAttribute("bannerColor", "banner-color-green");
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
            model.addAttribute("hidden", "show");
        }

        return "user/portfolio";
    }

    @GetMapping("/resetPortfolio")
    public String reset(Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            session.setAttribute("message", "Please login and try again");
            return "redirect:/login";
        }

        model.addAttribute("firstName", USER_SESSION.getFirstName());
        return "user/resetPortfolio";
    }

    @GetMapping("/confirmReset")
    public String confirm(HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            session.setAttribute("message", "Please login and try again");
            return "redirect:/login";
        }
        Optional<User> updatedUser = userRepo.findById(USER_SESSION.getId());
        if (updatedUser.isEmpty()) return "redirect:/";
        User theUser = updatedUser.get();
        theUser.setGBP(0);
        theUser.setEUR(0);
        theUser.setUSD(0);
        theUser.setADA(0);
        theUser.setBTC(0);
        theUser.setETH(0);
        theUser.removePortfolioHistory();

        userRepo.save(theUser);
        session.setAttribute("USER_SESSION", theUser);
        session.setAttribute("message", "Success! Your account has been reset");
        return "redirect:/portfolio";
    }

    private List<Coin> getLatestCoins() {
        List<Coin> coinValues = new ArrayList<>();
        Optional<Coin> btc = coinRepo.findById("BTC");
        btc.ifPresent(coinValues::add);

        Optional<Coin> eth = coinRepo.findById("ETH");
        eth.ifPresent(coinValues::add);

        Optional<Coin> ada = coinRepo.findById("ADA");
        ada.ifPresent(coinValues::add);
        return coinValues;
    }

    private String roundFloat(float f){
        return String.format("%.2f", f);
    }

}
