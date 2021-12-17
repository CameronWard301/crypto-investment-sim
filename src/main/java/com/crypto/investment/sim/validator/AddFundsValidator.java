package com.crypto.investment.sim.validator;

import com.crypto.investment.sim.model.UserAddFunds;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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

        float userInput;
        try{
            userInput = Float.parseFloat(user.getValue());
        } catch (NumberFormatException ex){
            errors.rejectValue("value", "", "Please only enter floating point numbers");
            return;
        }

        if (user.getFiat().equals("USD")){
            if ((Float.parseFloat(user.getUSD())+userInput)<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }
        else if (user.getFiat().equals("EUR")){
            if ((Float.parseFloat(user.getEUR())+userInput)<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }
        else{
            if ((Float.parseFloat(user.getGBP())+userInput)<0){
                errors.rejectValue("value", "", "Cannot have an account value less than 0!");
            }
        }

    }
}
