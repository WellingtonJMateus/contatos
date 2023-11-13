package com.well.contatos.dtos;

import com.well.contatos.controllers.exception.NotEmptyAddress;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContatoDTORequest {
    @NotEmpty(message = "{field.nome}")
    private String nome;
    private Integer idade;
    private String sexo;
    @NotEmptyAddress(message = "{field.endereco}")
    private EnderecoDTORequest endereco;
}
