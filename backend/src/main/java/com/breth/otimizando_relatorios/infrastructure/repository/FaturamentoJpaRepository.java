package com.breth.otimizando_relatorios.infrastructure.repository;

import com.breth.otimizando_relatorios.infrastructure.entity.FaturamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturamentoJpaRepository extends JpaRepository<FaturamentoEntity, Long> {
} 