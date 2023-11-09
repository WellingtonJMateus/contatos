package com.well.contatos.repositories;

import com.well.contatos.models.ContatoModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface ContatoPaginacaoRepository extends PagingAndSortingRepository<ContatoModel, UUID> {


}
