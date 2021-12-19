package com.crypto.investment.sim.validator;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.model.UserLogin;
import com.crypto.investment.sim.repos.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class LoginValidator implements Validator {

    private final UserRepository userRepo;

    public LoginValidator(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserLogin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password cannot be blank");

        if (errors.hasErrors()){
            return;
        }

        UserLogin theUser = (UserLogin) target;

        List<User> users = userRepo.findByUsername(theUser.getUsername());
        if (users.size() != 1) { //user not found
            errors.rejectValue("password", "", "Invalid username and or password");
            return;
        }

        // the user was found
        User foundUser = users.get(0);
        String DbPassword = foundUser.getHashPassword();
        if (!BCrypt.checkpw(theUser.getPassword(), DbPassword)) {
            errors.rejectValue("password", "", "Invalid username and or password");
        }
    }
}
