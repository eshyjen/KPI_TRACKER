package com.ericsson.ipm.v1.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ericsson.ipm.v1.dto.RegistrationDTO;

@Component("userValidator")
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userFristName", "message.firstName", "Firstname is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userLastName", "message.lastName", "LastName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "message.username", "UserName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.password", "Password required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matchingPassword", "message.matchingPassword", "Password required");

        /*if(obj instanceof RegistrationDTO){
        	RegistrationDTO dto = (RegistrationDTO)obj;
        	if(StringUtils.isNotBlank(dto.getPassword()) && StringUtils.isNotBlank(dto.getMatchingPassword())){
        		if(!dto.getPassword().equals(dto.getMatchingPassword())){
            		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matchingPassword", "message.notMatching", "Password required");
            	}
        	}
        }*/







        RegistrationDTO dto = (RegistrationDTO)obj ;

    		if(!(dto.getPassword().equals(dto.getMatchingPassword()))){
    			errors.rejectValue("matchingPassword", "message.notMatching", "Password required");
    		}

    	}

 //   }

    }


