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
import java.util.Objects;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class AddFundsController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new AddFundsValidator());
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    @PostMapping("/updateCurrency")
    public String addRemove(@Valid @ModelAttribute UserAddFunds userAddFunds, BindingResult result, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            return "redirect:/login";
        }
        this.addAttributes(model, USER_SESSION);
        model.addAttribute("selected", userAddFunds.getFiat());
        if (result.hasErrors()) {
            return "user/addRemoveCurrency";
        }

        if (Objects.equals(userAddFunds.getFiat(), "USD")) {
            //$$$
            USER_SESSION.setUSD(USER_SESSION.getUSD() + Float.parseFloat(userAddFunds.getValue())); //update the session object
            model.addAttribute("selected", "USD"); //select the dollar button on page
            model.addAttribute("USD", USER_SESSION.getUSD()); //add the new value of USD to the page
            userRepo.save(USER_SESSION); //save the user in the database
            session.setAttribute("USER_SESSION", USER_SESSION); //overwrite session
        } else if (Objects.equals(userAddFunds.getFiat(), "EUR")) {
            //€€€
            USER_SESSION.setEUR(USER_SESSION.getEUR() + Float.parseFloat(userAddFunds.getValue()));
            model.addAttribute("selected", "EUR");
            model.addAttribute("EUR", USER_SESSION.getEUR());
            userRepo.save(USER_SESSION);
            session.setAttribute("USER_SESSION", USER_SESSION);
        } else {
            //£££
            USER_SESSION.setGBP(USER_SESSION.getGBP() + Float.parseFloat(userAddFunds.getValue()));
            model.addAttribute("selected", "GBP");
            model.addAttribute("GBP", USER_SESSION.getGBP());
            userRepo.save(USER_SESSION);
            session.setAttribute("USER_SESSION", USER_SESSION);
        }
        return "user/addRemoveCurrency";
    }

    @GetMapping("/addRemoveCurrency")
    public String currency(@ModelAttribute UserAddFunds userAddFunds, Model model, HttpSession session) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null) {
            return "redirect:/login";
        }
        this.addAttributes(model, USER_SESSION);
        model.addAttribute("userAddFunds", new UserAddFunds());
        model.addAttribute("selected", "GBP");

        return "user/addRemoveCurrency";
    }

    private void addAttributes(Model model, User USER_SESSION) {
        model.addAttribute("GBP", USER_SESSION.getGBP());
        model.addAttribute("USD", USER_SESSION.getUSD());
        model.addAttribute("EUR", USER_SESSION.getEUR());

    }
}
