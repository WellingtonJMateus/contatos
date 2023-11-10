package com.well.contatos.mapper;

import com.well.contatos.dtos.ContatoDTORequest;
import com.well.contatos.entity.ContatoEntity;
import com.well.contatos.entity.EnderecoEntity;

public class ContatoDTORequestMapper {

    private ContatoDTORequestMapper(){
        throw new IllegalStateException("Mapper Class");
    }

    public static ContatoEntity mapTo(ContatoDTORequest contatoDTORequest){
        return ContatoEntity
                .builder()
                .nome(contatoDTORequest.getNome())
                .idade(contatoDTORequest.getIdade())
                .sexo(contatoDTORequest.getSexo())
                .endereco(
                        EnderecoEntity
                                .builder()
                                .rua(contatoDTORequest.getEndereco().getRua())
                                .numero(contatoDTORequest.getEndereco().getNumero())
                                .bairro(contatoDTORequest.getEndereco().getBairro())
                                .cep(contatoDTORequest.getEndereco().getCep())
                                .cidade(contatoDTORequest.getEndereco().getCidade())
                                .estado(contatoDTORequest.getEndereco().getEstado())
                                .build()
                )
                .build();
    }
}
