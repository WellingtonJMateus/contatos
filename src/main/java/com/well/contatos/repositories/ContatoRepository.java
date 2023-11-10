package com.well.contatos.repositories;

import com.well.contatos.entity.ContatoEntity;
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
public interface ContatoRepository extends JpaRepository<ContatoEntity, UUID> {
   @Query(value = "SELECT c FROM ContatoEntity c WHERE c.idade >= :idade")
   List<ContatoEntity> findContatoModelByIdade(@Param("idade") Integer idade);

   @Query(value = "SELECT c FROM ContatoEntity c")
   List<ContatoEntity> findAllContatoModel(Sort sort);

   @Query(value = "SELECT c FROM ContatoEntity c ORDER BY idContato")
   Page<ContatoEntity> findAllContatoEntityWithPagination(Pageable pageable);

   @Query(value = "SELECT c FROM ContatoEntity c WHERE c.nome LIKE :nome")
   List<ContatoEntity> findAllByNome(@Param("nome") String nome);

}
