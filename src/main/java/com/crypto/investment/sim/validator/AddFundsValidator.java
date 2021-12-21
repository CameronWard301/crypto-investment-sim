package com.crypto.investment.sim.validator;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.model.UserAddFunds;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpSession;

public class AddFundsValidator implements Validator {

    private final UserRepository userRepo;
    private final HttpSession session;

    public AddFundsValidator(UserRepository userRepo, HttpSession session){
        this.userRepo = userRepo;
        this.session = session;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserAddFunds.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "", "Value Required!");
        User sessionUser = (User) session.getAttribute("USER_SESSION");
        User DbUser = userRepo.findByUsername(sessionUser.getUsername()).get(0);

        if (errors.hasErrors()){
            return;
        }

        UserAddFunds userFormInput = (UserAddFunds) target;

        float userInput;
        try{
            userInput = Float.parseFloat(userFormInput.getValue());
        } catch (NumberFormatException ex){
            errors.rejectValue("value", "", "Please only enter floating point numbers");
            return;
        }

        if (userFormInput.getFiat().equals("USD")){
            if ((DbUser.getUSD()+userInput)<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }
        else if (userFormInput.getFiat().equals("EUR")){
            if ((DbUser.getEUR()+userInput)<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }
        else{
            if ((DbUser.getGBP()+userInput)<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }

    }
}
