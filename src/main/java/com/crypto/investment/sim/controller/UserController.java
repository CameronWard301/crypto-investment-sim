package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    public UserRepository userRepo;
    @GetMapping("/portfolio")
    public String viewPortfolio(@RequestParam("user") int id, Model model) {
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "user/portfolio";
    }
}
