package com.well.contatos.repositories;

import com.well.contatos.models.ContatoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, UUID> {
   @Query(value = "SELECT c FROM ContatoModel c WHERE c.idade >= :idade")
   List<ContatoModel> findContatoModelByIdade(@Param("idade") Integer idade);

   @Query(value = "SELECT c FROM ContatoModel c")
   List<ContatoModel> findAllContatoModel(Sort sort);

   @Query(value = "SELECT c FROM ContatoModel c ORDER BY idContato")
   Page<ContatoModel> findAllContatoModelWithPagination(Pageable pageable);

}
