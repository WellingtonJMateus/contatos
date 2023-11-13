package com.well.contatos.controllers.exception;

public class ContatoNotFoundException extends RuntimeException{

    public ContatoNotFoundException(String message){
        super(message);
    }

}
