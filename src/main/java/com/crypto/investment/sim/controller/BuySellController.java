package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.BuySellData;
import com.crypto.investment.sim.model.Coin;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import com.crypto.investment.sim.validator.ExchangeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BuySellController {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

    @InitBinder
    protected void initBinder(WebDataBinder binder, HttpSession session){
        binder.addValidators(new ExchangeValidator(userRepo, coinRepo, session));
    }

    @GetMapping("/buySell")
    public String viewBuySell(@ModelAttribute BuySellData formData, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            session.setAttribute("message", "Please login and try again");
            return "redirect:/login";
        }
        model.addAttribute("buySellData", new BuySellData());
        model.addAttribute("user", USER_SESSION);
        this.getLatestCoins(model);

        return "user/buySell";
    }

    @PostMapping("/convertRequest")
    public String finalTransaction(@Valid @ModelAttribute BuySellData buySellData, BindingResult result, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (result.hasErrors()){
            model.addAttribute("user", USER_SESSION);
            this.getLatestCoins(model);
            return "user/buySell";
        }

        Optional<Coin> fromCoinObject = coinRepo.findById(buySellData.getConvertFrom());
        Optional<Coin> toCoinObject = coinRepo.findById(buySellData.getConvertTo());

        if (fromCoinObject.isEmpty() || toCoinObject.isEmpty()) {
            return "redirect:/buySell";
        }

        float convertAmount = Float.parseFloat(buySellData.getConvertFromAmount());
        float exchangedCoin = buySellData.calculateExchange(buySellData.getConvertFrom(), buySellData.getConvertTo(), convertAmount, fromCoinObject.get().getCurrentPrice(), toCoinObject.get().getCurrentPrice());

        return "redirect:/buySell";
    }

    private void getLatestCoins(Model model) {
        Optional<Coin> btc = coinRepo.findById("BTC");
        btc.ifPresent(value1 -> model.addAttribute("btc", value1));

        Optional<Coin> eth = coinRepo.findById("ETH");
        eth.ifPresent(value2 -> model.addAttribute("eth", value2));

        Optional<Coin> ada = coinRepo.findById("ADA");
        ada.ifPresent(value3 -> model.addAttribute("ada", value3));

        Optional<Coin> gbp = coinRepo.findById("GBP");
        gbp.ifPresent(value4 -> model.addAttribute("gbp", value4));

        Optional<Coin> eur = coinRepo.findById("EUR");
        eur.ifPresent(value5 -> model.addAttribute("eur", value5));

        Optional<Coin> usd = coinRepo.findById("USD");
        usd.ifPresent(value6 -> model.addAttribute("usd", value6));
    }

}
