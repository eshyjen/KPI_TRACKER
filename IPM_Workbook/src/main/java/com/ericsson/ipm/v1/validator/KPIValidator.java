package com.ericsson.ipm.v1.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ericsson.ipm.v1.dto.KPIDTO;

@Component("kpiValidator")
public class KPIValidator implements Validator {

	// https://github.com/eugenp/tutorials/blob/master/spring-security-login-and-registration/src/main/java/org/baeldung/web/controller/RegistrationController.java
	
	// http://www.baeldung.com/registration-with-spring-mvc-and-spring-security
	// http://www.raistudies.com/spring/spring-mvc/ajax-form-validation-using-spring-mvc-and-jquery/
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return KPIDTO.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) 
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kpiName", "error.kpiName", "KPI Name can not be blank.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kpiDisplayName", "error.kpiDisplayName", "KPI Display Name can not be blank.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userSelectedRoles", "error.role", "Please select role.");
		
		if(target instanceof KPIDTO){
			KPIDTO kpidto = (KPIDTO)target;
			if(kpidto.getKpiVaueSameForAllRoles()){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kpiValue", "error.kpiValue", "KPI value can not be blank.");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kpiDescription", "error.kpiDescription", "KPI Description can not be blank.");
			}
		}

		
	}
}
