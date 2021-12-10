package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignupController{
    @Autowired
    public UserRepository userRepo;

    @RequestMapping("/signupform")
    public String signupform(@ModelAttribute User user,Model model) {
        model.addAttribute("User",new User());
        return "signup/signup";
    }

    @RequestMapping ("/signup")
    public String CreateCustomer(@ModelAttribute User signup) {


        userRepo.save(signup);
        return "signup/success";
//        return "redirect:/portfolio";
    }
}