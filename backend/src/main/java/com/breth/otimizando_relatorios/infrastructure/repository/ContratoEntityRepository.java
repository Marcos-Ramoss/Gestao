package com.breth.otimizando_relatorios.infrastructure.repository;

import com.breth.otimizando_relatorios.infrastructure.entity.ContratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoEntityRepository extends JpaRepository<ContratoEntity, String> {
} 