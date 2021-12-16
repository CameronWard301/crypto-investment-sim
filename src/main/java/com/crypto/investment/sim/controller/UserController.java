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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        if (USER_SESSION == null){

            return "redirect:/login";
        }
        User theUser = (User) USER_SESSION;
        JSONObject history = new JSONObject();
        history.put("history",theUser.getPortfolioHistory());

        model.addAttribute("user", USER_SESSION);
        model.addAttribute("portfolioHistory", history);
        this.getLatestCoins(model);
        return "user/portfolio";
    }


    @GetMapping("/buySell")
    public String viewBuySell(Model model, HttpSession session) {
        Object USER_SESSION = session.getAttribute("USER_SESSION");
        if (USER_SESSION == null){
            return "redirect:/login";
        }
        model.addAttribute("user", USER_SESSION);
        this.getLatestCoins(model);

        return "user/buySell";
    }

    @PostMapping("/buySell")
    public String finalTransaction(Model model, HttpSession session) {
        Object USER_SESSION = session.getAttribute("USER_SESSION");
        if (USER_SESSION == null){
            return "redirect:/login";
        }
        model.addAttribute("user", USER_SESSION);

        return "user/buySell";
    }

    @RequestMapping("/doAddRemoveCurrency")
    public String addRemove(@RequestParam(value="fiat", required = false) Optional<Integer> id, @RequestParam int gbp, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null){
            return "redirect:/login";
        }
        model.addAttribute("user", USER_SESSION);


        if (id.isEmpty()) {
            //If no parameter show default markets in £££
            model.addAttribute("fiat", 1508);
        } else if (id.get() == 1505) {
            //Show markets in $$$
            model.addAttribute("fiat", 1505);
        } else if (id.get() == 1506) {
            //Show markets in €€€
            model.addAttribute("fiat", 1506);
        } else {
            //If a different param value is passed fall back on £££
            model.addAttribute("fiat", 1508);
        }

        USER_SESSION.setGBP(USER_SESSION.getGBP() +gbp);
        userRepo.save(USER_SESSION);
        session.setAttribute("USER_SESSION", USER_SESSION);
        return "user/addRemoveCurrency";
    }

    @RequestMapping("/addRemoveCurrency")
    public String currency(Model model, HttpSession session){
        Object USER_SESSION = session.getAttribute("USER_SESSION");
        if (USER_SESSION == null){
            return "redirect:/login";
        }
        model.addAttribute("user", USER_SESSION);
        return "user/addRemoveCurrency";
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
