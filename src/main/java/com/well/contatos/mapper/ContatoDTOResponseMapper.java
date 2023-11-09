package com.well.contatos.mapper;

import com.well.contatos.dtos.ContatoDTORequest;
import com.well.contatos.dtos.ContatoDTOResponse;
import com.well.contatos.dtos.EnderecoDTOResponse;
import com.well.contatos.models.ContatoModel;

import java.util.ArrayList;
import java.util.List;

public class ContatoDTOResponseMapper {

    public static List<ContatoDTOResponse> mapTo(List<ContatoModel> listaContatoModels){
        List<ContatoDTOResponse> contatoDTOResponseList = new ArrayList<>();
        for (ContatoModel contatoModel: listaContatoModels){
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
            );
        }
        return contatoDTOResponseList;

    }

    public static ContatoDTOResponse mapTo(ContatoModel contatoModel){
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


}
