package com.well.contatos.dtos;

import com.well.contatos.models.EnderecoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDTORequest {
    private String nome;
    private Integer idade;
    private String sexo;
    private EnderecoDTORequest endereco;
}
