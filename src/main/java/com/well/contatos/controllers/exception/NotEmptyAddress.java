package com.well.contatos.controllers.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyAddressValidator.class)
public @interface NotEmptyAddress {
    String message() default "Todos os campos de Endereço são obrigatórios.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
