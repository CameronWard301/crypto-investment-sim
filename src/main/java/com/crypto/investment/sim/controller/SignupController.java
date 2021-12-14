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
import java.util.List;

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
            Model model
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


        System.out.println(generatedSecuredPasswordHash);

//        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
//        System.out.println(matched);
//
//        matched = SCryptUtil.check("passwordno", generatedSecuredPasswordHash);
//        System.out.println(matched);

        userRepo.save(user);
        return "redirect:/portfolio";
    }
}