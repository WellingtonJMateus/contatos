package com.well.contatos.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoDTOResponse {
    private UUID idContato;
    private String nome;
    private Integer idade;
    private String sexo;
    private EnderecoDTOResponse endereco;
}
