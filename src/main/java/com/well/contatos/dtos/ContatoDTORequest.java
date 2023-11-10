package com.well.contatos.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContatoDTORequest {
    private String nome;
    private Integer idade;
    private String sexo;
    private EnderecoDTORequest endereco;
}
