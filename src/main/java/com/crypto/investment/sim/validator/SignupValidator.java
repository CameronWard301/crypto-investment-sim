package com.crypto.investment.sim.validator;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.model.UserSignUp;
import com.crypto.investment.sim.repos.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Objects;

public class SignupValidator implements Validator {

    private final UserRepository userRepo;

    public SignupValidator(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserSignUp.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username Required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First Name Required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last Name Required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password1", "", "Password Required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "", "Password Required!");

        if (errors.hasErrors()){
            return;
        }

        UserSignUp user = (UserSignUp) target;

        if (user.getUsername().length() < 3){
            errors.rejectValue("username", "", "Username is too short");
        }

        if (!Objects.equals(user.getPassword1(), user.getPassword2())){
            errors.rejectValue("password2", "", "Passwords don't match");
            return;
        }

        if (user.getPassword1().length() < 4){
            errors.rejectValue("password1", "", "Password Too Short");
            return;
        }

        List<User> users = userRepo.findByUsername(user.getUsername());

        if(users.size() >=1) {
            errors.rejectValue("username", "", "Username already taken");
        }

    }
}
