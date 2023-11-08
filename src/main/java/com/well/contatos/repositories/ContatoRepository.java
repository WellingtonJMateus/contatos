package com.well.contatos.repositories;

import com.well.contatos.models.ContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, UUID> {
}
