package com.crypto.investment.sim.validator;

import com.crypto.investment.sim.controller.UserController;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.model.UserAddFunds;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Objects;

public class AddFundsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserAddFunds.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "", "Value Required!");

        if (errors.hasErrors()){
            return;
        }

        UserAddFunds user = (UserAddFunds) target;

        if (user.getFiat().isEmpty()){
            if ((user.getGBP()+user.getValue())<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }
        else if (user.getFiat().get().equals(1505)){
            if ((user.getUSD()+user.getValue())<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }
        else if (user.getFiat().get().equals(1506)){
            if ((user.getEUR()+user.getValue())<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }
        else{
            if ((user.getGBP()+user.getValue())<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }

    }
}
