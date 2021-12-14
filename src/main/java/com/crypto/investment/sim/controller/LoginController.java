package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class LoginController{

    private static final Object USER_OBJECT = null;
    @Autowired
    public UserRepository userRepo;

    @GetMapping("/login")
    public String Displaylogin(@ModelAttribute User user,Model model) {
        return "user/loginform";
    }

    @RequestMapping("/addLogin")
    public String login(String password, String username, Model model, HttpSession session, HttpServletRequest request) {
        List<User> users = userRepo.findByUsername(username);
        if (users.size() == 1) {
            // the user was found
            User foundUser = users.get(0);
            String DBpassword = foundUser.getHashPassword();

            if (BCrypt.checkpw(password, DBpassword)) {
                request.getSession().setAttribute("USER_SESSION", foundUser);
                return "redirect:/portfolio";
            }

        }
        // username not found
        model.addAttribute("Error","Username not found");
        return "user/loginform";

    }
}