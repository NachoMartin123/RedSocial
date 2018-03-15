package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Publication;


@Component
public class RegisterPublicationFormValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Publication.class.equals(aClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Publication publication = (Publication) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "texto", "Error.empty");
		
		
	}

}
