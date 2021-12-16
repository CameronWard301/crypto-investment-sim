package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class LoginController{

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

    @GetMapping("/login")
    public String DisplayLogin(@ModelAttribute User user) {
        return "user/loginform";
    }

    @RequestMapping("/addLogin")
    public String login(String password, String username, Model model, HttpServletRequest request) {
        List<User> users = userRepo.findByUsername(username);
        if (users.size() == 1) {
            // the user was found
            User foundUser = users.get(0);
            String DbPassword = foundUser.getHashPassword();

            if (BCrypt.checkpw(password, DbPassword)) {
                request.getSession().setAttribute("USER_SESSION", foundUser);
                return "redirect:/portfolio";
            }

        }
        // username not found
        model.addAttribute("Error","Username not found");
        return "user/loginform";

    }
}