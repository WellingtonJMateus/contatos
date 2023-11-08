package com.well.contatos.services;


import com.well.contatos.models.ContatoModel;
import com.well.contatos.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public List<ContatoModel> listAll(){
        List<ContatoModel> contatoModelList = contatoRepository.findAll();
        return contatoRepository.findAll();
    }

    public ContatoModel save(ContatoModel contatoModel){
       return contatoRepository.save(contatoModel);
    }

    public Optional<ContatoModel> get(UUID uuid){
        return contatoRepository.findById(uuid);
    }

    public void delete(UUID uuid) {
        contatoRepository.deleteById(uuid);
    }

}
