package com.well.contatos.controllers.exception;

import java.util.List;

import lombok.Getter;

public class ApiErrors {

    @Getter
    private List<String> erros;

    public ApiErrors(String mensagemErro) {
        super();
        this.erros = List.of(mensagemErro);

    }

    public ApiErrors(List<String> erros) {
        super();
        this.erros = erros;
    }

}
