package com.well.contatos.controllers.exception;

import com.well.contatos.dtos.EnderecoDTORequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyAddressValidator implements ConstraintValidator<NotEmptyAddress, EnderecoDTORequest> {
    @Override
    public void initialize(NotEmptyAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EnderecoDTORequest enderecoDTORequest, ConstraintValidatorContext constraintValidatorContext) {
        return !enderecoDTORequest.getRua().isEmpty() && !enderecoDTORequest.getBairro().isEmpty() &&
                !enderecoDTORequest.getNumero().isEmpty() && !enderecoDTORequest.getCidade().isEmpty() &&
                !enderecoDTORequest.getCep().isEmpty() && !enderecoDTORequest.getEstado().isEmpty();
    }

}
