package com.crypto.investment.sim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @GetMapping("/portfolio")
    public String viewPortfolio(@RequestParam("user") int id, Model model) {
        model.addAttribute("user",1);
        return "user/portfolio";
    }
}
