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
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Optional;

@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "SpringMVCViewInspection"})
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

            return "redirect:/login";
        }
        User oldUser = (User) USER_SESSION;
        Optional<User> theUser = userRepo.findById(oldUser.getId()); //get any updates on their portfolio history
        if (theUser.isEmpty()) return "/"; //redirect if error

        JSONObject history = new JSONObject();
        history.put("history", theUser.get().getPortfolioHistory()); //Add the history to a JSON object

        session.setAttribute("USER_SESSION", theUser.get()); //Update the Session
        model.addAttribute("user", USER_SESSION);
        model.addAttribute("portfolioHistory", history);
        this.getLatestCoins(model);
        return "user/portfolio";
    }


    @GetMapping("/buySell")
    public String viewBuySell(Model model, HttpSession session) {
        Object USER_SESSION = session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", USER_SESSION);
        this.getLatestCoins(model);

        return "user/buySell";
    }

    @PostMapping("/buySell")
    public String finalTransaction(Model model, HttpSession session) {
        Object USER_SESSION = session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", USER_SESSION);

        return "user/buySell";
    }

    @GetMapping("/resetPortfolio")
    public String reset(Model model, HttpSession session) {
        Object USER_SESSION = session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", USER_SESSION);
        return "user/resetPortfolio";
    }

    @GetMapping("/confirmReset")
    public String confirm(HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            return "redirect:/login";
        }
        Optional<User> updatedUser = userRepo.findById(USER_SESSION.getId());
        if (updatedUser.isEmpty()) return "/";
        User theUser = updatedUser.get();
        theUser.setGBP(0);
        theUser.setEUR(0);
        theUser.setUSD(0);
        theUser.setCardano(0);
        theUser.setBitcoin(0);
        theUser.setEthereum(0);
        theUser.removePortfolioHistory();

        userRepo.save(theUser);
        session.setAttribute("USER_SESSION", theUser);
        return "redirect:/portfolio";
    }

    private void getLatestCoins(Model model) {
        Optional<Coin> btc = coinRepo.findById("BTC");
        btc.ifPresent(value1 -> model.addAttribute("btc", value1));

        Optional<Coin> eth = coinRepo.findById("ETH");
        eth.ifPresent(value2 -> model.addAttribute("eth", value2));

        Optional<Coin> ada = coinRepo.findById("ADA");
        ada.ifPresent(value3 -> model.addAttribute("ada", value3));
    }

}
