package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.model.UserSignUp;
import com.crypto.investment.sim.repos.UserRepository;
import com.crypto.investment.sim.validator.SignupValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class SignupController{

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(new SignupValidator(userRepo));
    }

    @GetMapping("/sign-up")
    public String SignUpForm(@ModelAttribute UserSignUp userSignUp, Model model) {
        model.addAttribute("userSignUp", new UserSignUp());
        return "user/signup";
    }

    @PostMapping ("/createAccount")
    public String CreateCustomer(
            @Valid
            @ModelAttribute UserSignUp userSignUp,
            BindingResult result,
            HttpServletRequest request
    ) {

        if (result.hasErrors()){
            return "user/signup";
        }

        String generatedSecuredPasswordHash = BCrypt.hashpw(userSignUp.getPassword1(), BCrypt.gensalt(12));
        User user = new User();
        user.setUsername(userSignUp.getUsername());
        user.setFirstName(userSignUp.getFirstName());
        user.setLastName(userSignUp.getLastName());
        user.setHashPassword(generatedSecuredPasswordHash);

        userRepo.save(user);
        request.getSession().setAttribute("USER_SESSION", user);
        return "redirect:/portfolio";

    }
}