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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    protected void initBinder(WebDataBinder binder, HttpSession session) {
        binder.addValidators(new ExchangeValidator(userRepo, coinRepo, session));
    }

    @GetMapping("/buySell")
    public String viewBuySell(@ModelAttribute BuySellData formData, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            session.setAttribute("message", "Please login and try again");
            return "redirect:/login";
        }
        if (session.getAttribute("message") != null) {
            model.addAttribute("bannerColor", session.getAttribute("banner-color"));
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
            model.addAttribute("hidden", "show");
        }
        model.addAttribute("buySellData", new BuySellData());
        model.addAttribute("user", USER_SESSION);
        this.getLatestCoins(model);

        return "user/buySell";
    }

    @PostMapping("/convertRequest")
    public String finalTransaction(@Valid @ModelAttribute BuySellData buySellData, BindingResult result, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            session.setAttribute("message", "You have automatically been logged out. Please login and try again");
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", USER_SESSION);
            model.addAttribute("bannerColor", "banner-color-red");
            model.addAttribute("message", "Please review the errors below and try again");
            session.removeAttribute("message");
            model.addAttribute("hidden", "show");
            this.getLatestCoins(model);
            return "user/buySell";
        }

        Optional<Coin> fromCoinObject = coinRepo.findById(buySellData.getConvertFrom());
        Optional<Coin> toCoinObject = coinRepo.findById(buySellData.getConvertTo());
        Optional<User> dbUser = userRepo.findById(USER_SESSION.getId());

        if (fromCoinObject.isEmpty() || toCoinObject.isEmpty() || dbUser.isEmpty()) {
            session.setAttribute("message", "Could not resolve coin objects, please log out and try again");
            session.setAttribute("banner-color", "banner-color-red");
            return "redirect:/buySell";
        }

        float convertAmount = Float.parseFloat(buySellData.getConvertFromAmount());
        float exchangedCoin = buySellData.calculateExchange(buySellData.getConvertFrom(), buySellData.getConvertTo(), convertAmount, fromCoinObject.get().getCurrentPrice(), toCoinObject.get().getCurrentPrice());

        Method setFromCoin;
        Method setToCoin;
        Method getFromCoin;
        Method getToCoin;

        try {
            setFromCoin = User.class.getDeclaredMethod("set" + buySellData.getConvertFrom(), float.class);
            setToCoin = User.class.getDeclaredMethod("set" + buySellData.getConvertTo(), float.class);
            getFromCoin = User.class.getDeclaredMethod("get" + buySellData.getConvertFrom());
            getToCoin = User.class.getDeclaredMethod("get" + buySellData.getConvertTo());
            float currentFromCoinBalance = (float) getFromCoin.invoke(dbUser.get());
            float currentToCoinBalance = (float) getToCoin.invoke(dbUser.get());
            setFromCoin.invoke(dbUser.get(), currentFromCoinBalance - convertAmount);
            setToCoin.invoke(dbUser.get(), currentToCoinBalance + exchangedCoin);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            session.setAttribute("message", "Could not update user, please log out and try again");
            session.setAttribute("banner-color", "banner-color-red");
            return "redirect:/buySell";
        }

        User updatedUser = userRepo.save(dbUser.get());
        session.setAttribute("USER_SESSION", updatedUser);

        if (Float.parseFloat(buySellData.getEstimatedExchange()) != exchangedCoin) {
            session.setAttribute("message", "Success! You have traded currencies.\nPlease note the markets updated during the transaction/viewing this page\nInstead of receiving " +
                    buySellData.getEstimatedExchange() + " " + buySellData.getConvertTo() + " You actually received: " + exchangedCoin + " " + buySellData.getConvertTo());
        } else {
            session.setAttribute("message", "Success! You have traded currencies");
        }
        session.setAttribute("banner-color", "banner-color-green");


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
