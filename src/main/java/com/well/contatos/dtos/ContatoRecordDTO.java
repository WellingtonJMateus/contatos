package com.well.contatos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContatoRecordDTO ( String nome, Integer idade, String sexo, String rua,
                                String bairro, String numero,String cidade, String cep,
                                String estado){}

