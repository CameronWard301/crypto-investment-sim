package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String CreateCustomer(@ModelAttribute User signup, Model model, HttpSession session, HttpServletRequest request) {

        List<User> users = userRepo.findByUsername(signup.getUsername());
        if(users.size() >=1) {
            model.addAttribute("Error","Duplicate Users");
            return "signup/signup";
        }


        userRepo.save(signup);

        request.getSession().setAttribute("USER_SESSION", signup);
        return "redirect:/portfolio";
//        return "redirect:/portfolio";
    }
}