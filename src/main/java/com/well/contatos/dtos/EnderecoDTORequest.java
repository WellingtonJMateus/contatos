package com.well.contatos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTORequest {
    private String rua;
    private String bairro;
    private String numero;
    private String cidade;
    private String cep;
    private String estado;
}
