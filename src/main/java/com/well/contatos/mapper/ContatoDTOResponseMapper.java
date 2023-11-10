package com.well.contatos.mapper;

import com.well.contatos.dtos.ContatoDTOResponse;
import com.well.contatos.dtos.EnderecoDTOResponse;
import com.well.contatos.entity.ContatoEntity;

import java.util.ArrayList;
import java.util.List;

public class ContatoDTOResponseMapper {

    private ContatoDTOResponseMapper(){
        throw new IllegalStateException("Mapper Class");
    }

    public static ContatoDTOResponse mapTo(ContatoEntity contatoModel) {
        return ContatoDTOResponse
                .builder()
                .idContato(contatoModel.getIdContato())
                .nome(contatoModel.getNome())
                .idade(contatoModel.getIdade())
                .sexo(contatoModel.getSexo())
                .endereco(
                        EnderecoDTOResponse
                                .builder()
                                .rua(contatoModel.getEndereco().getRua())
                                .numero(contatoModel.getEndereco().getNumero())
                                .bairro(contatoModel.getEndereco().getBairro())
                                .cep(contatoModel.getEndereco().getCep())
                                .cidade(contatoModel.getEndereco().getCidade())
                                .estado(contatoModel.getEndereco().getEstado())
                                .build()
                )
                .build();

    }

    public static List<ContatoDTOResponse> mapTo(List<ContatoEntity> listaContatoModels) {
        List<ContatoDTOResponse> contatoDTOResponseList = new ArrayList<>();
        listaContatoModels.forEach(contatoModel ->
                contatoDTOResponseList.add(
                        ContatoDTOResponse
                                .builder()
                                .idContato(contatoModel.getIdContato())
                                .nome(contatoModel.getNome())
                                .idade(contatoModel.getIdade())
                                .sexo(contatoModel.getSexo())
                                .endereco(
                                        EnderecoDTOResponse
                                                .builder()
                                                .rua(contatoModel.getEndereco().getRua())
                                                .numero(contatoModel.getEndereco().getNumero())
                                                .bairro(contatoModel.getEndereco().getBairro())
                                                .cep(contatoModel.getEndereco().getCep())
                                                .cidade(contatoModel.getEndereco().getCidade())
                                                .estado(contatoModel.getEndereco().getEstado())
                                                .build()
                                )
                                .build()
                ));
        return contatoDTOResponseList;
    }


}
