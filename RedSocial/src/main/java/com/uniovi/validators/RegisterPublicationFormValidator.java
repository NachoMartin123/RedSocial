package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Publication;

/**
 * Clase que permite validar los campos del formulario de 
 * creacion de una nueva publicacion
 * 
 * @author UO231379, UO239718
 * 
 */
@Component
public class RegisterPublicationFormValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Publication.class.equals(aClass);
	}
	
	/**
	 * Metodo que comprueba cada dato del formulario.
	 * En caso de que exista algún error en el, se registra la informacion
	 * sobre este en el objeto errors
	 */
	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "texto", "Error.empty");
		
		
	}

}
