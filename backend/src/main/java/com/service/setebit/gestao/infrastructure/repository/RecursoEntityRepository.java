package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.infrastructure.entity.RecursoEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecursoEntityRepository extends JpaRepository<RecursoEntity, Long> {
    Optional<RecursoEntity> findFirstByNomeIgnoreCase(String nome);
}