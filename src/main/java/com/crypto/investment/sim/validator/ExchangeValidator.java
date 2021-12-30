package com.crypto.investment.sim.validator;

import com.crypto.investment.sim.model.BuySellData;
import com.crypto.investment.sim.model.Coin;
import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ExchangeValidator implements Validator {

    private final UserRepository userRepo;
    private final CoinRepository coinRepo;
    private final HttpSession session;

    public ExchangeValidator(UserRepository userRepo, CoinRepository coinRepo, HttpSession session){
        this.userRepo = userRepo;
        this.coinRepo = coinRepo;
        this.session = session;
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

        if (errors.hasErrors()){
            return;
        }
        BuySellData inputData = (BuySellData) target;
        List<String> coinOptions = Arrays.asList("GBP", "USD", "EUR", "BTC", "ETH", "ADA");

        if (!coinOptions.contains(inputData.getConvertFrom())){
            errors.rejectValue("convertFrom", "", "Please choose from GBP, USD, EUR, BTC, ETH or ADA");
        }
        if (!coinOptions.contains(inputData.getConvertTo())){
            errors.rejectValue("convertTo", "", "Please choose from GBP, USD, EUR, BTC, ETH or ADA");
        }

        if (errors.hasErrors()){
            return;
        }

        if (Objects.equals(inputData.getConvertFrom(), inputData.getConvertTo())){
            errors.rejectValue("convertFrom", "", "Cannot exchange the same coin");
            errors.rejectValue("convertTo", "", "Cannot exchange the same coin");

        }

        if (errors.hasErrors()){
            return;
        }

        float convertFromAmount = 0;
        float estimatedExchange = 0;

        try {
            convertFromAmount = Float.parseFloat(inputData.getConvertFromAmount());
        } catch (NumberFormatException ex) {
            errors.rejectValue("convertFromAmount", "", "Please only enter numbers");
        }

        if (convertFromAmount <= 0){
            errors.rejectValue("convertFromAmount", "", "Please enter an amount greater than 0");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estimatedExchange", "", "Unknown error, please logout and try again");

        if (errors.hasErrors()){
            return;
        }

        try {
            estimatedExchange = Float.parseFloat(inputData.getEstimatedExchange());
        } catch (NumberFormatException ex) {
            errors.rejectValue("estimatedExchange", "", "Please only enter numbers");
        }

        if (estimatedExchange <= 0 ){
            errors.rejectValue("estimatedExchange", "", "Cannot be zero, please increase the amount that you are selling");
        }

        if (errors.hasErrors()){
            return;
        }

        User user = (User) session.getAttribute("USER_SESSION");
        Optional<User> usersAvailableCoin = userRepo.findById(user.getId());
        if (usersAvailableCoin.isEmpty()){
            errors.rejectValue("estimatedExchange", "", "User not found, please log out and try again");
        }

        Method getFromCoinMethod = null;
        try {
            getFromCoinMethod = User.class.getDeclaredMethod("get"+inputData.getConvertFrom());
        } catch (NoSuchMethodException e) {
            errors.rejectValue("convertFrom", "", "Please select a valid currency");
        }
        try {
            assert getFromCoinMethod != null;
            if (convertFromAmount > (float) getFromCoinMethod.invoke(user)){
                errors.rejectValue("convertFromAmount", "", "Can't exceed your available balance");
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        Optional<Coin> fromCoinObject = coinRepo.findById(inputData.getConvertFrom());
        if (fromCoinObject.isEmpty()) {
            errors.rejectValue("convertFrom", "", "Could not locate coin in DB please try again later");
        }

        Optional<Coin> toCoinObject = coinRepo.findById(inputData.getConvertTo());
        if (toCoinObject.isEmpty()) {
            errors.rejectValue("convertTo", "", "Could not locate coin in DB please try again later");
        }


    }
}
