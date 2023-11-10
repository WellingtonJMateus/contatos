package com.well.contatos.repositories;

import com.well.contatos.entity.ContatoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ContatoPaginacaoRepository extends PagingAndSortingRepository<ContatoEntity, UUID> {


}
