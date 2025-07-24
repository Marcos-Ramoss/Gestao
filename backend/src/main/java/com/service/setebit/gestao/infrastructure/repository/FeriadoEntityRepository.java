package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.infrastructure.entity.FeriadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface FeriadoEntityRepository extends JpaRepository<FeriadoEntity, Long> {

    Optional<FeriadoEntity> findByData(LocalDate data);
    void deleteByData(LocalDate data);
} 