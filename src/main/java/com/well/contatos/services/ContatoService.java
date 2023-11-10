package com.well.contatos.services;


import com.well.contatos.dtos.ContatoDTOResponse;
import com.well.contatos.mapper.ContatoDTOResponseMapper;
import com.well.contatos.entity.ContatoEntity;
import com.well.contatos.repositories.ContatoPaginacaoRepository;
import com.well.contatos.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ContatoService {


    private final ContatoRepository contatoRepository;
    private final ContatoPaginacaoRepository contatoPaginacaoRepository;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository, ContatoPaginacaoRepository contatoPaginacaoRepository) {
        this.contatoRepository = contatoRepository;
        this.contatoPaginacaoRepository = contatoPaginacaoRepository;
    }

    public List<ContatoDTOResponse> listAll() {
        return ContatoDTOResponseMapper.mapTo(contatoRepository.findAll());
    }

    public ContatoDTOResponse save(ContatoEntity contatoModel) {
        return ContatoDTOResponseMapper.mapTo(contatoRepository.save(contatoModel));
    }

    public Optional<ContatoEntity> get(UUID uuid) {

        return contatoRepository.findById(uuid);
    }

    public void delete(UUID uuid) {
        contatoRepository.deleteById(uuid);
    }

    public long getnumberOfRows() {
        return contatoRepository.count();
    }

    public List<ContatoDTOResponse> getContatoByAge(Integer age) {
        return ContatoDTOResponseMapper.mapTo(contatoRepository.findContatoModelByIdade(age));
    }

    public List<ContatoDTOResponse> getContatoOrdernadoPor(String campo) {
        return ContatoDTOResponseMapper.mapTo(contatoRepository.findAllContatoModel(Sort.by(campo)));
    }

    public List<ContatoDTOResponse> getPaginacao(int pageNumber, int pageSize, String sortBy) {
        Pageable paging  = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<ContatoEntity> pagedResult  = contatoPaginacaoRepository.findAll(paging );
        if(pagedResult.hasContent()) {
            return ContatoDTOResponseMapper.mapTo(pagedResult.getContent());
        } else {
            return new ArrayList<>();
        }
    }

    public List<ContatoDTOResponse> getContatoPorNomeInicializaCom(String nome){
        return ContatoDTOResponseMapper.mapTo(contatoRepository.findAllByNome("%"+nome+"%"));
    }


}
