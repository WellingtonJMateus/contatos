package com.well.contatos.mapper;

import com.well.contatos.dtos.ContatoDTORequest;
import com.well.contatos.models.ContatoModel;
import com.well.contatos.models.EnderecoModel;

public class ContatoDTORequestMapper {

    public static ContatoModel mapTo(ContatoDTORequest contatoDTORequest){
        ContatoModel contatoModel = new ContatoModel();
        EnderecoModel enderecoModel = new EnderecoModel();
        contatoModel.setNome(contatoDTORequest.getNome());
        contatoModel.setIdade(contatoDTORequest.getIdade());
        contatoModel.setSexo(contatoDTORequest.getSexo());
        enderecoModel.setRua(contatoDTORequest.getEndereco().getRua());
        enderecoModel.setNumero(contatoDTORequest.getEndereco().getNumero());
        enderecoModel.setBairro(contatoDTORequest.getEndereco().getBairro());
        enderecoModel.setCep(contatoDTORequest.getEndereco().getCep());
        enderecoModel.setCidade(contatoDTORequest.getEndereco().getCidade());
        enderecoModel.setEstado(contatoDTORequest.getEndereco().getEstado());
        contatoModel.setEndereco(enderecoModel);
        return contatoModel;
    }
}
