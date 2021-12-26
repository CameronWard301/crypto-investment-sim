package com.crypto.investment.sim.validator;

import com.crypto.investment.sim.model.BuySellData;
import com.crypto.investment.sim.model.Coin;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ExchangeValidator implements Validator {

    private final UserRepository userRepo;
    private final CoinRepository coinRepo;

    public ExchangeValidator(UserRepository userRepo, CoinRepository coinRepo){
        this.userRepo = userRepo;
        this.coinRepo = coinRepo;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BuySellData.class.equals(clazz) || User.class.equals(clazz) || Coin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "convertFrom", "", "Please select a coin to convert from");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "convertTo", "", "Please select a coin to convert to");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "convertFromAmount", "", "Please enter an amount to convert from");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estimatedExchange", "", "Unknown error, please logout and try again");

        if (errors.hasErrors()){
            return;
        }
    }
}
