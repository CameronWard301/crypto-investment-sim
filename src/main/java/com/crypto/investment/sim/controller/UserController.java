package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.Coin;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    public UserRepository userRepo;
    @Autowired
    public CoinRepository coinRepo;

    @GetMapping("/portfolio")
    public String viewPortfolio(@RequestParam("user") int id, Model model) {
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));

        Optional<Coin> btc = coinRepo.findById("BTC");
        btc.ifPresent(value -> model.addAttribute("btc", value));

        Optional<Coin> eth = coinRepo.findById("ETH");
        eth.ifPresent(value -> model.addAttribute("eth", value));

        Optional<Coin> ada = coinRepo.findById("ADA");
        ada.ifPresent(value -> model.addAttribute("ada", value));

        return "user/portfolio";
    }
}
