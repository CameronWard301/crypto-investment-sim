package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController{
    @Autowired
    public UserRepository userRepo;

    @GetMapping ("/login")
    public String Displaylogin(@ModelAttribute User user,Model model) {
        return "user/loginform";
    }

    @RequestMapping   ("/addlogin")
    public String login(String Password,String username,Model model) {
        List<User> users = userRepo.findByUsername(username);
        if (users.size() == 1) {
            // the user was found
            User foundUser = users.get(0);
            String DBpassword = foundUser.getHashPassword();

            if (DBpassword == Password){
                String tempusername = foundUser.getUsername();
                String tempfirstname = foundUser.getFirstName();
                String templastname = foundUser.getLastName();

                return "redirect:/portfolio";
            }

        } else {
            // username not found
            model.addAttribute("Error","Username not found");
            return "user/loginform";


        }
        return null;
    }
}