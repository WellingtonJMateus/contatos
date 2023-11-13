package com.well.contatos.controllers.exception;

public class ContatoNotSaveException extends RuntimeException{
    public ContatoNotSaveException(String message){
        super(message);
    }
}
