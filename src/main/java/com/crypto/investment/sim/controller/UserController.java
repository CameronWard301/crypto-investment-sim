package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.Coin;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "SpringMVCViewInspection"})
@Controller
public class UserController {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public CoinRepository coinRepo;

    @GetMapping("/portfolio")
    public String viewPortfolio(@RequestParam("user") int id, Model model, HttpSession session, HttpServletRequest request) {
        //TODO: Implement when login system is working
        /* Object USER_SESSION = session.getAttribute("USER_SESSION");
        if (USER_SESSION == null){
            return "redirect:/"; //TODO Change to login page
        }*/
        // TODO: USE request.getSession().setAttribute("USER_SESSION", USER_OBJECT); to set set the user object on login
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        this.getLatestCoins(model);
        return "user/portfolio";
    }


    @GetMapping("/buySell")
    public String viewBuySell(@RequestParam("user") int id, Model model) {
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        this.getLatestCoins(model);

        return "user/buySell";
    }

    @PostMapping("/buySell")
    public String finalTransaction(@RequestParam("user") int id, Model model) {

        Optional<User> user = userRepo.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));

        return "user/buySell";
    }

    private void getLatestCoins(Model model){
        Optional<Coin> btc = coinRepo.findById("BTC");
        btc.ifPresent(value1 -> model.addAttribute("btc", value1));

        Optional<Coin> eth = coinRepo.findById("ETH");
        eth.ifPresent(value2 -> model.addAttribute("eth", value2));

        Optional<Coin> ada = coinRepo.findById("ADA");
        ada.ifPresent(value3 -> model.addAttribute("ada", value3));
    }

}



