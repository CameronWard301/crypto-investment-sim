package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.model.UserSignUp;
import com.crypto.investment.sim.repos.UserRepository;
import com.crypto.investment.sim.validator.SignupValidator;
import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import com.github.mkopylec.recaptcha.validation.ValidationResult;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("SameReturnValue")
@Controller
public class SignupController{

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public UserRepository userRepo;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private RecaptchaValidator recaptchaValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(new SignupValidator(userRepo));
    }

    @GetMapping("/sign-up")
    public String SignUpForm(@ModelAttribute UserSignUp userSignUp, Model model) {
        model.addAttribute("userSignUp", new UserSignUp());
        model.addAttribute("captchaMessage", "");
        return "user/signup";
    }

    @PostMapping ("/createAccount")
    public String CreateCustomer(
            @Valid
            @ModelAttribute UserSignUp userSignUp,
            BindingResult result,
            HttpServletRequest request,
            Model model
    ) {
        ValidationResult captchaResult = recaptchaValidator.validate(request);

        if (captchaResult.isFailure()){
            model.addAttribute("captchaMessage", "Please complete the captcha");
        }

        if (result.hasErrors() || captchaResult.isFailure()){
            model.addAttribute("bannerColor", "banner-color-red");
            model.addAttribute("message", "Please fix the errors and try again");
            model.addAttribute("hidden", "show");
            return "user/signup";
        }

        String generatedSecuredPasswordHash = BCrypt.hashpw(userSignUp.getPassword1(), BCrypt.gensalt(12));
        User user = new User();
        user.setUsername(userSignUp.getUsername());
        user.setFirstName(userSignUp.getFirstName());
        user.setLastName(userSignUp.getLastName());
        user.setGBP(0);
        user.setEUR(0);
        user.setUSD(0);
        user.setBTC(0);
        user.setETH(0);
        user.setADA(0);
        user.setHashPassword(generatedSecuredPasswordHash);

        userRepo.save(user);
        HttpSession theSession = request.getSession();
        theSession.setAttribute("USER_SESSION", user);
        theSession.setAttribute("message", "Success! Account created. Time to add some funds to your account!");
        return "redirect:/portfolio";

    }
}