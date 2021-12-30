package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.UserLogin;
import com.crypto.investment.sim.repos.UserRepository;
import com.crypto.investment.sim.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@SuppressWarnings("SameReturnValue")
@Controller
public class LoginController{

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(new LoginValidator(userRepo));
    }

    @GetMapping("/login")
    public String DisplayLogin(@ModelAttribute UserLogin user, Model model, HttpSession session) {
        if (session.getAttribute("message") != null){
            model.addAttribute("bannerColor", "banner-color-green");
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
            model.addAttribute("hidden", "show");
        }
        model.addAttribute("user", new UserLogin());

        return "user/loginform";
    }

    @PostMapping("/addLogin")
    public String login(@Valid @ModelAttribute("user") UserLogin user, BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()){
            model.addAttribute("bannerColor", "banner-color-red");
            model.addAttribute("message", "Please fix the errors and try again");
            model.addAttribute("hidden", "show");
            return "user/loginform";
        }

        request.getSession().setAttribute("USER_SESSION", userRepo.findByUsername(user.getUsername()).get(0));
        return "redirect:/portfolio";

    }
}