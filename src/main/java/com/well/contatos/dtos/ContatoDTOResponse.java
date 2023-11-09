package com.well.contatos.dtos;

import com.well.contatos.models.ContatoModel;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoDTOResponse  {
    private UUID idContato;
    private String nome;
    private Integer idade;
    private String sexo;
    private EnderecoDTOResponse endereco;
}
