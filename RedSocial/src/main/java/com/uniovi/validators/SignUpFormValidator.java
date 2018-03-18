package com.uniovi.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

/**
 *Clase que permite validar los campos del formulario de 
 * registro de un usuario
 * 
 * @author UO231379, UO239718
 * 
 */
@Component
public class SignUpFormValidator  implements Validator{
	
	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}
	
	/**
	 * Metodo que comprueba cada dato del formulario.
	 * En caso de que exista algún error en el, se registra la informacion
	 * sobre este en el objeto errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
		Matcher mather = pattern.matcher(user.getEmail());
		if (user.getEmail().length() < 3 || user.getEmail().length() > 24) {
			errors.rejectValue("email", "Error.signup.email.length");
		}
		if(mather.find() == false) {
			errors.rejectValue("email","Error.signup.email.incorrect.format");
		}
		if (usersService.getUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Error.signup.email.duplicate");
		}
		if (user.getName().length() < 3 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
		}
	}

}
