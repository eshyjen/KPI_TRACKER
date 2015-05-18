package com.ericsson.ipm.v1.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.ericsson.ipm.v1.dto.RegistrationDTO;

@Component("passwordMatchesValidator")
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        RegistrationDTO user = (RegistrationDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
