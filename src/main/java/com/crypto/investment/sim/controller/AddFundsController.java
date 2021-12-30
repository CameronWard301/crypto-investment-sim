package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.model.UserAddFunds;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import com.crypto.investment.sim.validator.AddFundsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Controller
public class AddFundsController {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    @InitBinder
    protected void initBinder(WebDataBinder binder, HttpSession session) {
        binder.addValidators(new AddFundsValidator(userRepo, session));
    }

    @PostMapping("/updateCurrency")
    public String addRemove(@Valid @ModelAttribute UserAddFunds userAddFunds, BindingResult result, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            session.setAttribute("message", "You have automatically been logged out. Please login and try again");
            return "redirect:/login";
        }
        this.addAttributes(model, USER_SESSION);
        model.addAttribute("selected", userAddFunds.getFiat());
        if (result.hasErrors()) {
            model.addAttribute("message", "Please correct the errors below and try again");
            model.addAttribute("bannerColor", "banner-color-red");
            model.addAttribute("hidden", "show");
            return "user/addRemoveCurrency";
        }

        String symbol;
        if (Objects.equals(userAddFunds.getFiat(), "USD")) {
            //$$$
            USER_SESSION.setUSD(USER_SESSION.getUSD() + roundDown(Float.parseFloat(userAddFunds.getValue()))); //update the session object
            model.addAttribute("selected", "USD"); //select the dollar button on page
            model.addAttribute("USD", roundFloat(USER_SESSION.getUSD())); //add the new value of USD to the page
            userRepo.save(USER_SESSION); //save the user in the database
            session.setAttribute("USER_SESSION", USER_SESSION); //overwrite session
            symbol = "$";
        } else if (Objects.equals(userAddFunds.getFiat(), "EUR")) {
            //€€€
            USER_SESSION.setEUR(USER_SESSION.getEUR() + roundDown(Float.parseFloat(userAddFunds.getValue())));
            model.addAttribute("selected", "EUR");
            model.addAttribute("EUR", roundFloat(USER_SESSION.getEUR()));
            userRepo.save(USER_SESSION);
            session.setAttribute("USER_SESSION", USER_SESSION);
            symbol = "€";
        } else {
            //£££
            USER_SESSION.setGBP(USER_SESSION.getGBP() + roundDown(Float.parseFloat(userAddFunds.getValue())));
            model.addAttribute("selected", "GBP");
            model.addAttribute("GBP", roundFloat(USER_SESSION.getGBP()));
            userRepo.save(USER_SESSION);
            session.setAttribute("USER_SESSION", USER_SESSION);
            symbol = "£";
        }
        session.setAttribute("message", "Success! You have amended your account by: "+symbol + roundFloat(roundDown(Float.parseFloat(userAddFunds.getValue()))));
        session.setAttribute("banner-color", "banner-color-green");
        return "redirect:/addRemoveCurrency";
    }

    @GetMapping("/addRemoveCurrency")
    public String currency(@ModelAttribute UserAddFunds userAddFunds, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            session.setAttribute("message", "Please login and try again");
            return "redirect:/login";
        }
        if (session.getAttribute("message") != null){
            model.addAttribute("bannerColor", session.getAttribute("banner-color"));
            model.addAttribute("message", session.getAttribute("message"));
            model.addAttribute("hidden", "show");
            session.removeAttribute("message");
        }
        this.addAttributes(model, USER_SESSION);
        model.addAttribute("userAddFunds", new UserAddFunds());
        model.addAttribute("selected", "GBP");

        return "user/addRemoveCurrency";
    }

    private void addAttributes(Model model, User USER_SESSION) {
        model.addAttribute("GBP", roundFloat(USER_SESSION.getGBP()));
        model.addAttribute("USD", roundFloat(USER_SESSION.getUSD()));
        model.addAttribute("EUR", roundFloat(USER_SESSION.getEUR()));

    }

    public float roundDown(float amount){
        BigDecimal bd = new BigDecimal(Float.toString(amount));
        bd = bd.setScale(2, RoundingMode.HALF_DOWN);
        return bd.floatValue();
    }

    private String roundFloat(float f){
        return String.format("%.2f", f);
    }
}
