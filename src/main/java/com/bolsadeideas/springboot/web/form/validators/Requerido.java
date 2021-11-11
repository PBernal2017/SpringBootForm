package com.bolsadeideas.springboot.web.form.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
@Constraint(validatedBy = RequeridoValidador.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD })
public @interface Requerido {

	String message() default "Campo apellido es requerido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
