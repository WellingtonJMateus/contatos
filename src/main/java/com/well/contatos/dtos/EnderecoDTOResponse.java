package com.well.contatos.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {
    private String rua;
    private String bairro;
    private String numero;
    private String cidade;
    private String cep;
    private String estado;
}
