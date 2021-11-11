package com.bolsadeideas.springboot.web.form.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsadeideas.springboot.web.form.models.domain.Usuario;

@Component
public class UsuarioValidador implements Validator {

	/*
	 * Este Validador valida * solo * instancias de Persona
	 */

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Usuario usuario = (Usuario)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "requerido.usuario.nombre");

		// if(!usuario.getIdentificador().matches("[0-9]{3}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}"))
		// {
		// errors.rejectValue("identificador", "Pattern.user.identificador");
	}
}
