package com.well.contatos.controllers.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ApiErrors(erros);
    }

    @ExceptionHandler(ContatoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleContatoNotFoundException(ContatoNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(ContatoNotSaveException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ApiErrors handleContatoNotSaveException(ContatoNotSaveException ex) {
        return new ApiErrors(ex.getMessage());
    }

}